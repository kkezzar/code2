package dz.gov.mesrs.sii.grh.business.service.conges.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.CongeEmployeDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.grh.CongeEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.util.UtilDate;
import dz.gov.mesrs.sii.grh.business.exception.CongeDateDebutException;
import dz.gov.mesrs.sii.grh.business.exception.CongeDateDemandeException;
import dz.gov.mesrs.sii.grh.business.exception.CongeDateRepriseException;
import dz.gov.mesrs.sii.grh.business.exception.CongeNbJourException;
import dz.gov.mesrs.sii.grh.business.model.dto.AnneeGrhDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CongeEmployeDto;
import dz.gov.mesrs.sii.grh.business.service.conges.CongeEmployeService;

/**
 * 
 * @author BELDI Jamel
 * 
 */

@Service("congeEmployeService")
public class CongeEmployeServiceImpl extends
		CommonServiceImpl<CongeEmploye, CongeEmployeDto, Long> implements
		CongeEmployeService {

	private CongeEmployeDao congeEmployeDao;
	private SituationEntiteDao situationEntiteDao;
	private SituationEntiteOccurrenceDao situationEntiteOccurrenceDao;
	private NomenclatureDao nomenclatureDao;

	@Override
	protected CommonDao<CongeEmploye, Long> getDao() {
		return congeEmployeDao;
	}

	/**
	 * Calculer le droit de congé total d'un employé pour une année de calcul
	 * 
	 * @param anneeGrhDto
	 * @param employeId
	 * @param dateRecrutement
	 * @return droit congé
	 */
	@Override
	@Transactional
	public double calculateDroitConge(AnneeGrhDto anneeGrhDto, Long employeId,
			Date dateRecrutement) {
		double result;
		Assert.notNull(anneeGrhDto, "AnneeGrhDto doit être non null");
		Assert.notNull(employeId, "employeId doit être non null");
		Assert.notNull(dateRecrutement, "dateRecrutement doit être non null");
		// calcul solde(annee) = 2.5xnbmois-consomation précédentes
		// nbMois en fonction de la date de recrutement
		// 1. Calculer Nombre de mois
		int nbMois = calculateMois(anneeGrhDto.getDateFin(), dateRecrutement);
		// 2. Calculer les consomation précedentes
		double consomation = calculateDaysConsumedBeforeYear(anneeGrhDto,
				employeId, dateRecrutement);
		// 3. Calculer le droit congé
		result = (2.5 * nbMois) - consomation;
		return result;
	}

	@Override
	/**
	 * Permet de verifier si l'employé à un jour de congé entre deux date
	 */
	public boolean isEmployeConges(Long employeId, Date dateDebut, Date dateFin) {
		boolean result = false;
		Assert.notNull(employeId, "employeId doit être non null");
		Assert.notNull(dateDebut, "dateDebut doit être non null");
		Assert.notNull(dateFin, "dateFin doit être non null");
		CongeEmploye congeEmploye = new CongeEmploye();
		congeEmploye.setDossierEmploye(new DossierEmploye(employeId));
		Collection<Integer> collection = new ArrayList<Integer>();
		SituationEntite traitee = situationEntiteDao
				.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE,
						UtilConstants.SITUATION_TRAITEE_CODE);
		SituationEntite cloturee = situationEntiteDao
				.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE,
						UtilConstants.SITUATION_CLOTUREE_CODE);
		collection.add(traitee.getId());

		collection.add(cloturee.getId());
		;

		SearchMode searchMode = new SearchMode();
		searchMode.addFilter(new Filter("situationEntite.id", collection
				.toArray(), FilterMode.IN));
		searchMode.addFilter(new Filter("dateDebutAccepte", dateDebut,
				FilterMode.LESS_OR_EQUAL));
		List<CongeEmploye> resultObjects = getDao().findAllByExample(
				congeEmploye, searchMode);
		if (resultObjects != null && !resultObjects.isEmpty()) {
			for (CongeEmploye conge : resultObjects) {
				Date dateFinConge = conge.getDateRepriseReelle();
				if (conge.getSituationEntite().getId() == traitee.getId()) {
					dateFinConge = UtilDate.addDays(
							conge.getDateDebutAccepte(),
							conge.getNbreJourAccepte());
				}
				if (dateFinConge.before(dateFin)) {
					return true;
				}
			}

		}
		return result;
	}

	/**
	 * Calculer le solde congé annuel d'un employé
	 */
	@Override
	@Transactional
	public double calculateSoldeConge(AnneeGrhDto anneeGrhDto, Long employeId,
			Date dateRecrutement) {
		double result = 0;
		// solde conge = droit - pris
		double droitConge = calculateDroitConge(anneeGrhDto, employeId,
				dateRecrutement);
		double congesTakenInPeriod = calulateCongeTakenInPeriod(anneeGrhDto,
				employeId);
		result = droitConge - congesTakenInPeriod;
		return result;
	}

	/**
	 * calculer les congés d'un employé pris dans la période
	 */

	@Override
	@Transactional
	public double calulateCongeTakenInPeriod(AnneeGrhDto anneeGrhDto,
			Long employeId) {
		double result = 0;
		List<CongeEmployeDto> list = getListEmployeCongeTakenInPeriod(
				anneeGrhDto, employeId);
		if (list != null) {
			for (CongeEmployeDto congeEmployeDto : list) {
				result = result
						+ (congeEmployeDto.getNbreJourReel() != null ? congeEmployeDto
								.getNbreJourReel() : congeEmployeDto
								.getNbreJourAccepte());

			}
		}
		return result;
	}

	/**
	 * les congés d'un employé pris dans la période
	 */

	@Override
	public List<CongeEmployeDto> getListEmployeCongeTakenInPeriod(
			AnneeGrhDto anneeGrhDto, Long employeId) {

		Assert.notNull(employeId, "employeId doit être non null");
		Assert.notNull(anneeGrhDto, "AnneeGrhDto doit être non null");

		CongeEmploye congeEmploye = new CongeEmploye();
		congeEmploye.setDossierEmploye(new DossierEmploye(employeId));
		Collection<Integer> collection = new ArrayList<Integer>();
		SituationEntite traitee = situationEntiteDao
				.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE,
						UtilConstants.SITUATION_TRAITEE_CODE);
		SituationEntite cloturee = situationEntiteDao
				.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE,
						UtilConstants.SITUATION_CLOTUREE_CODE);
		collection.add(traitee.getId());

		collection.add(cloturee.getId());
		;

		SearchMode searchMode = new SearchMode();
		searchMode.addFilter(new Filter("situationEntite.id", collection
				.toArray(), FilterMode.IN));
		searchMode.addFilter(new Filter("dateDebutAccepte", anneeGrhDto
				.getDateDebut(), anneeGrhDto.getDateFin(), FilterMode.BETWEEN));
		List<CongeEmploye> resultObjects = getDao().findAllByExample(
				congeEmploye, searchMode);

		return mapReturn(resultObjects);
	}

	@Override
	@Transactional
	public CongeEmployeDto saveDemande(CongeEmployeDto congeEmployeDto,
			AnneeGrhDto anneeGrhDto) throws CongeDateDemandeException,
			CongeDateDebutException, CongeNbJourException {
		Assert.notNull(congeEmployeDto, "congeEmployeDto is required");
		Assert.notNull(anneeGrhDto, "anneeGrhDto is required");
		if (congeEmployeDto.getDateDemande().before(anneeGrhDto.getDateDebut())
				|| congeEmployeDto.getDateDemande().after(
						anneeGrhDto.getDateFin())) {
			throw new CongeDateDemandeException(
					"R001: La date de la demande doit être comprise dans l'année "
							+ anneeGrhDto.getLibelle());
		}
		if (congeEmployeDto.getDateDebut().before(
				congeEmployeDto.getDateDemande())) {
			throw new CongeDateDebutException(
					"R002: La date de début de congé doit >= à la date de la demande");
		}
		if (congeEmployeDto
				.getNomenclatureByTypeCongeDto()
				.getId()
				.equals(nomenclatureDao.findByCode(
						UtilConstant.NC_GRH_TYPE_CONGE,
						UtilConstant.NC_GRH_TYPE_CONGE_ANNUEL_CODE).getId())
				&& congeEmployeDto.getNbreJourDemande() > congeEmployeDto
						.getSoldeConge()) {
			throw new CongeNbJourException(
					"R003: Pour un  congé annuel Le nb de jours de congé ne doit pas dépasser le solde de congé");
		}
		CongeEmploye congeEmploye = mapper.map(congeEmployeDto,
				CongeEmploye.class);
		SituationEntite situationEntite = situationEntiteDao
				.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_CONGE_EMPLOYE_CODE,
						UtilConstants.SITUATION_CREEE_CODE);
		congeEmploye.setSituationEntite(situationEntite);
		congeEmploye = congeEmployeDao.save(congeEmploye);
		if (congeEmployeDto.getId() == null) {
			persistSituationOccurence(congeEmploye);
		}
		return mapper.map(congeEmploye, CongeEmployeDto.class);
	}

	@Override
	@Transactional
	public CongeEmployeDto saveResultatConge(CongeEmployeDto congeEmployeDto)
			throws CongeDateDebutException, CongeNbJourException {
		Assert.notNull(congeEmployeDto, "congeEmployeDto is required");
		if (congeEmployeDto.getDateDebutAccepte().before(
				congeEmployeDto.getDateDemande())) {
			throw new CongeDateDebutException(
					"R001: La date accéptée de début de congé doit >= à la date de la demande");
		}
		if (congeEmployeDto
				.getNomenclatureByTypeCongeDto()
				.getId()
				.equals(nomenclatureDao.findByCode(
						UtilConstant.NC_GRH_TYPE_CONGE,
						UtilConstant.NC_GRH_TYPE_CONGE_ANNUEL_CODE).getId())
				&& congeEmployeDto.getNbreJourDemande() > congeEmployeDto
						.getSoldeConge()) {
			throw new CongeNbJourException(
					"R003: Pour un  congé annuel Le nb accepté de jours de congé ne doit pas dépasser le solde de congé");
		}
		CongeEmploye congeEmploye = mapper.map(congeEmployeDto,
				CongeEmploye.class);
		SituationEntite situationEntite = situationEntiteDao
				.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_CONGE_EMPLOYE_CODE,
						UtilConstants.SITUATION_TRAITEE_CODE);
		congeEmploye.setSituationEntite(situationEntite);
		congeEmploye = congeEmployeDao.save(congeEmploye);
		persistSituationOccurence(congeEmploye);
		return mapper.map(congeEmploye, CongeEmployeDto.class);
	}

	@Override
	@Transactional
	public void saveAnnulationConge(CongeEmployeDto congeEmployeDto) {
		Assert.notNull(congeEmployeDto, "congeEmployeDto is required");
		CongeEmploye congeEmploye = mapper.map(congeEmployeDto,
				CongeEmploye.class);
		SituationEntite situationEntite = situationEntiteDao
				.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_CONGE_EMPLOYE_CODE,
						UtilConstants.SITUATION_ANNULEE_CODE);
		congeEmploye.setSituationEntite(situationEntite);
		congeEmploye = congeEmployeDao.save(congeEmploye);
		persistSituationOccurence(congeEmploye);
	}

	@Override
	public CongeEmployeDto saveRepriseConge(CongeEmployeDto congeEmployeDto)
			throws CongeDateRepriseException {
		Assert.notNull(congeEmployeDto, "congeEmployeDto is required");
		if (congeEmployeDto.getDateRepriseReelle().after(
				congeEmployeDto.getDateRepriseAccepte())) {
			throw new CongeDateRepriseException(
					"R001: La date de reprise réelle doit être <= à la date de reprise normale");
		}
		CongeEmploye congeEmploye = mapper.map(congeEmployeDto,
				CongeEmploye.class);
		SituationEntite situationEntite = situationEntiteDao
				.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_CONGE_EMPLOYE_CODE,
						UtilConstants.SITUATION_CLOTUREE_CODE);
		congeEmploye.setSituationEntite(situationEntite);
		congeEmploye = congeEmployeDao.save(congeEmploye);
		persistSituationOccurence(congeEmploye);
		return mapper.map(congeEmploye, CongeEmployeDto.class);
	}

	@Override
	public List<CongeEmployeDto> findAllDemandesConges(int etablissmentId,
			SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissment(searchMode, etablissmentId);
		applyFilterCree(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public int countAllDemandesConges(int etablissmentId,
			SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissment(searchMode, etablissmentId);
		applyFilterCree(searchMode);
		return super.countByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public List<CongeEmployeDto> findAllCongesAcceptes(int etablissmentId,
			SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissment(searchMode, etablissmentId);
		applyFilterTraitee(searchMode);
		applyFilterAcceptee(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public int countAllCongesAcceptes(int etablissmentId,
			SearchMode searchMode, String searchKeyWords) {
		//searchMode = new SearchMode();
		applyFilterEtablissment(searchMode, etablissmentId);
		applyFilterTraitee(searchMode);
		applyFilterAcceptee(searchMode);
		return super.countByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public List<CongeEmployeDto> findAllDemandesOrResultsConges(
			int etablissmentId, SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissment(searchMode, etablissmentId);
		applyFilterCreeOrTraitee(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public int countAllDemandesOrResultsConges(int etablissmentId,
			SearchMode searchMode, String searchKeyWords) {

		applyFilterEtablissment(searchMode, etablissmentId);
		applyFilterCreeOrTraitee(searchMode);
		return super.countByKeyWord(searchKeyWords, searchMode);
	}
	
	private void applyFilterCree(SearchMode searchMode) {
		searchMode.addFilter(new Filter("situationEntite.id",
				situationEntiteDao.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_CONGE_EMPLOYE_CODE,
						UtilConstants.SITUATION_CREEE_CODE).getId(),
				FilterMode.EQUALS));
	}

	private void applyFilterTraitee(SearchMode searchMode) {
		searchMode.addFilter(new Filter("situationEntite.id",
				situationEntiteDao.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_CONGE_EMPLOYE_CODE,
						UtilConstants.SITUATION_TRAITEE_CODE).getId(),
				FilterMode.EQUALS));
	}

	private void applyFilterAcceptee(SearchMode searchMode) {
		searchMode.addFilter(new Filter("resultat", true, FilterMode.EQUALS));
	}
	
	private void applyFilterCreeOrTraitee(SearchMode searchMode) {
		Collection<Integer> collection = new ArrayList<Integer>();
		collection.add(situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_CONGE_EMPLOYE_CODE,
						UtilConstants.SITUATION_CREEE_CODE).getId());
		collection.add(situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_CONGE_EMPLOYE_CODE,
						UtilConstants.SITUATION_TRAITEE_CODE).getId());
		searchMode.addFilter(new Filter("situationEntite.id", collection.toArray(), FilterMode.IN));
	}
	
	private void applyFilterEtablissment(SearchMode searchMode, int etablissmentId) {
		searchMode.addFilter(new Filter("refEtablissement.id", etablissmentId, FilterMode.EQUALS));
	}

	/**
	 * Calculer Nombre de mois
	 * 
	 * @param dateFin
	 * @param dateDebut
	 * @return nbre de mois
	 */
	private int calculateMois(Date dateFin, Date dateDebut) {
		int result;
		Calendar cFin = Calendar.getInstance();
		Calendar cDebut = Calendar.getInstance();
		cFin.setTime(dateFin);
		cDebut.setTime(dateDebut);
		int yearFin = cFin.get(Calendar.YEAR);
		int yearDebut = cDebut.get(Calendar.YEAR);
		int monthFin = cFin.get(Calendar.MONTH);
		int monthDebut = cDebut.get(Calendar.MONTH);
		if (yearFin == yearDebut) {
			// result = les mois de l'année de recrutement
			result = monthFin > monthDebut ? monthFin - monthDebut + 1
					: monthDebut - monthFin + 1;// 1 jour vaut mois complet sauf
												// s'il ya une règle
		} else {
			// result=les mois de l'année de recrutement + les autres mois
			// jusqu'à la date de fin
			result = monthFin > monthDebut ? (monthFin - monthDebut + 1)
					+ ((yearFin - (yearDebut + 1)) * 12) : (monthDebut
					- monthFin + 1 + 1)
					+ ((yearFin - (yearDebut + 1)) * 12);
		}

		return result;
	}

	/**
	 * Calculer les jours congés annuel d'un employé consommés pendant les
	 * années qui précèdent l'année de calcul
	 * 
	 * @param anneeGrhDto
	 * @param employeId
	 * @param dateRecrutement
	 * @return nbre de jours
	 */
	private double calculateDaysConsumedBeforeYear(AnneeGrhDto anneeGrhDto,
			Long employeId, Date dateRecrutement) {

		double result = 0;
		CongeEmploye congeEmploye = new CongeEmploye();
		congeEmploye.setDossierEmploye(new DossierEmploye(employeId));
		Nomenclature nomenclatureByTypeConge = null;
		;
		SituationEntite situationEntite = situationEntiteDao
				.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE,
						UtilConstants.SITUATION_CLOTUREE_CODE);
		congeEmploye.setSituationEntite(situationEntite);
		congeEmploye.setNomenclatureByTypeConge(nomenclatureByTypeConge);
		SearchMode searchMode = new SearchMode();
		searchMode.addFilter(new Filter("dateDebutAccepte", dateRecrutement,
				anneeGrhDto.getDateDebut(), FilterMode.BETWEEN));
		List<CongeEmploye> resultObjects = getDao().findAllByExample(
				congeEmploye, searchMode);
		if (resultObjects != null && !resultObjects.isEmpty()) {
			for (CongeEmploye conge : resultObjects) {
				result = result + conge.getNbreJourReel();
			}
		}
		return result;
	}

	/**
	 * 
	 * @param resultObjects
	 * @return
	 */
	private List<CongeEmployeDto> mapReturn(List<CongeEmploye> resultObjects) {
		List<CongeEmployeDto> dtos = null;
		if (resultObjects != null && resultObjects.size() > 0) {
			dtos = new ArrayList<>();
			for (CongeEmploye object : resultObjects) {
				dtos.add(mapper.map(object, CongeEmployeDto.class));
			}
		}
		return dtos;
	}

	/**
	 * 
	 * @param congeEmploye
	 */
	private void persistSituationOccurence(CongeEmploye congeEmploye) {
		SituationEntiteOccurrence occurence = new SituationEntiteOccurrence();
		occurence.setDateSituation(new Date());
		occurence.setIdOccurrence(congeEmploye.getId().intValue());
		occurence.setSituationEntite(congeEmploye.getSituationEntite());
		situationEntiteOccurrenceDao.persist(occurence);
	}

	/**
	 * 
	 * @return the congeEmployeDao
	 */
	public CongeEmployeDao getCongeEmployeDao() {
		return congeEmployeDao;
	}

	/**
	 * 
	 * @param congeEmployeDao
	 *            to set
	 */
	@Autowired
	public void setCongeEmployeDao(CongeEmployeDao congeEmployeDao) {
		this.congeEmployeDao = congeEmployeDao;
	}

	/**
	 * 
	 * @return SituationEntiteDao
	 */
	public SituationEntiteDao getSituationEntiteDao() {
		return situationEntiteDao;
	}

	/**
	 * 
	 * @param situationEntiteDao
	 */
	@Autowired
	public void setSituationEntiteDao(SituationEntiteDao situationEntiteDao) {
		this.situationEntiteDao = situationEntiteDao;
	}

	/**
	 * 
	 * @return situationEntiteOccurrenceDao
	 */
	public SituationEntiteOccurrenceDao getSituationEntiteOccurrenceDao() {
		return situationEntiteOccurrenceDao;
	}

	/**
	 * 
	 * @param situationEntiteOccurrenceDao
	 */
	@Autowired
	public void setSituationEntiteOccurrenceDao(
			SituationEntiteOccurrenceDao situationEntiteOccurrenceDao) {
		this.situationEntiteOccurrenceDao = situationEntiteOccurrenceDao;
	}

	public NomenclatureDao getNomenclatureDao() {
		return nomenclatureDao;
	}

	@Autowired
	public void setNomenclatureDao(NomenclatureDao nomenclatureDao) {
		this.nomenclatureDao = nomenclatureDao;
	}

	

}
