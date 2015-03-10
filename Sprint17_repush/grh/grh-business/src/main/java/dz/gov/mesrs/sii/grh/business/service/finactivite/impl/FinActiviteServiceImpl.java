package dz.gov.mesrs.sii.grh.business.service.finactivite.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.ChangementPositionDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.DossierEmployeDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.FinActiviteDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefTypePieceConstitutiveDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.PieceConstitutive;
import dz.gov.mesrs.sii.commons.data.model.grh.ChangementPosition;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.FinActivite;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefTypePieceConstitutive;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.FinActiviteDto;
import dz.gov.mesrs.sii.grh.business.service.finactivite.FinActiviteService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */

@Service("finActiviteService")
@Transactional
public class FinActiviteServiceImpl extends CommonServiceImpl<FinActivite, FinActiviteDto, Long> implements
		FinActiviteService {

	private final static Logger logger = LoggerFactory.getLogger(FinActiviteServiceImpl.class.getName());

	private FinActiviteDao finActiviteDao;
	private Mapper mapper;
	// FIXME traitement retraite ne doit
	private int ageRetraite = 30;

	private SituationEntiteDao situationEntiteDao;
	private SituationEntiteOccurrenceDao situationEntiteOccurrenceDao;
	private NomenclatureDao nomenclatureDao;
	private RefTypePieceConstitutiveDao typePieceConstitutiveDao;
	private DossierEmployeDao dossierEmployeDao;
	private ChangementPositionDao changementPositionDao;

	private final static String TYPE_DOSSIER_DEPART_RETRAITE = "DDRET";

	@Override
	public Date calculAgeRetraite(DossierEmployeDto dto) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dto.getRefIndividuDto().getDateNaissance());
		calendar.add(Calendar.YEAR, ageRetraite);
		return calendar.getTime();
	}

	@Override
	public List<FinActiviteDto> findAllAdmisRetraite(Integer idEtablissement, String searchKeyWords,
			SearchMode searchMode) {
		searchMode = applyGenericFilters(idEtablissement, searchMode);
		applyAdmisRetraiteFilters(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public Integer countAllAdmisRetraite(Integer idEtablissement, String searchKeyWords) {
		SearchMode searchMode = applyGenericFilters(idEtablissement, null);
		applyAdmisRetraiteFilters(searchMode);
		return super.countByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public List<FinActiviteDto> findAllPendingDemission(Integer idEtablissement, String searchKeyWords,
			SearchMode searchMode) {
		applyGenericFilters(idEtablissement, searchMode);
		applyPendingDemissionFilter(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public Integer countAllPendingDemission(Integer idEtablissement, String searchKeyWords) {
		SearchMode searchMode = applyGenericFilters(idEtablissement, null);
		applyPendingDemissionFilter(searchMode);
		return super.countByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public List<FinActiviteDto> findAllPendingRetraiteAnticipe(Integer idEtablissement, String searchKeyWords,
			SearchMode searchMode) {
		searchMode = applyGenericFilters(idEtablissement, searchMode);
		applyPendingRetraiteAnticpeFilter(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public Integer countAllPendingRetraiteAnticipe(Integer idEtablissement, String searchKeyWords) {
		SearchMode searchMode = applyGenericFilters(idEtablissement, null);
		applyPendingRetraiteAnticpeFilter(searchMode);
		return super.countByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public List<FinActiviteDto> findAllCessationFinalCree(Integer idEtablissement, String searchKeyWords,
			SearchMode searchMode) {
		searchMode.addFilter(new Filter("etablissement.id", idEtablissement, FilterMode.EQUALS));
		applyCessationFinalCree(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public int countAllCessationFinalCree(Integer idEtablissement, String searchKeyWords) {
		SearchMode searchMode = new SearchMode();
		searchMode.addFilter(new Filter("etablissement.id", idEtablissement, FilterMode.EQUALS));
		applyCessationFinalCree(searchMode);
		return super.countByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public FinActiviteDto saveCessationFinal(FinActiviteDto cessationActiviteDto) {
		FinActivite finActivite = mapper.map(cessationActiviteDto, FinActivite.class);
		String situationCode = UtilConstant.SITUTAION_CREEE_CODE;
		String entiteCode = UtilConstant.ENTITE_CESSATION_ACTIVITE;
		if (finActivite.getSituation() != null) {
			entiteCode = finActivite.getSituation().getEntite().getCode();
			situationCode = finActivite.getSituation().getSituation().getCode();
		} else {
			finActivite.setDateDemande(new Date());
			finActivite.setAccepte(true);
		}

		SituationEntite situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(entiteCode, situationCode);
		finActivite.setSituation(situationEntite);
		// FIXME dirty fix
		Nomenclature nomenclature = nomenclatureDao.findById(finActivite.getNomenclature().getId());
		finActivite.setNomenclature(nomenclature);
		finActivite = finActiviteDao.save(finActivite);
		persistSituationOccurence(finActivite);
		return mapper.map(finActivite, FinActiviteDto.class);

	}

	@Override
	public void validerCessationFinal(FinActiviteDto cessationActiviteDto) {
		FinActivite finActivite = mapper.map(cessationActiviteDto, FinActivite.class);
		String situationCode = UtilConstant.SITUTAION_VALIDEE_CODE;
		String entiteCode = UtilConstant.ENTITE_CESSATION_ACTIVITE;
		if (finActivite.getSituation() != null) {
			entiteCode = finActivite.getSituation().getEntite().getCode();
		}

		SituationEntite situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(entiteCode, situationCode);
		finActivite.setSituation(situationEntite);
		persistSituationOccurence(finActivite);
		createChangementPosition(finActivite);
		finActivite = finActiviteDao.save(finActivite);

	}

	@Override
	public FinActiviteDto saveDemandeDemission(FinActiviteDto dto) {
		return saveDemande(dto, UtilConstant.ENTITE_DEMANDE_DEMISSION,
				UtilConstant.NC_GRH_TYPE_CESSATION_DEMISSION_VALUE, null);
	}

	@Override
	public FinActiviteDto saveDemandeRetraiteAnticipe(FinActiviteDto dto) {
		return saveDemande(dto, UtilConstant.ENTITE_DEMANDE_RETRAITE_ANTICIPE,
				UtilConstant.NC_GRH_TYPE_CESSATION_RETRAITE_VALUE,
				UtilConstant.NC_GRH_TYPE_DEPART_RETRAITE_ANTICIPE_VALUE);
	}

	@Override
	public void saveAdmissionRetraite(List<FinActiviteDto> toSaveDtos, List<FinActiviteDto> toDeleteDtos) {
		if (toSaveDtos != null && !toSaveDtos.isEmpty()) {
			Nomenclature departReraite = nomenclatureDao.findByCode(UtilConstant.NC_GRH_TYPE_CESSATION,
					UtilConstant.NC_GRH_TYPE_CESSATION_RETRAITE_VALUE);
			Nomenclature typdeDepart = nomenclatureDao.findByCode(UtilConstant.NC_GRH_TYPE_DEPART_RETRAITE,
					UtilConstant.NC_GRH_TYPE_DEPART_RETRAITE_NORMAL_VALUE);
			for (FinActiviteDto dto : toSaveDtos) {
				FinActivite finActivite = mapper.map(dto, FinActivite.class);
				finActivite.setNomenclature(departReraite);
				finActivite.setTypeDepartRetraite(typdeDepart);
				finActivite.setDateDemande(new Date());
				finActivite.setAccepte(true);
				generateDossierRetraite(finActivite);
				finActiviteDao.save(finActivite);

			}
		}

		if (toDeleteDtos != null && !toDeleteDtos.isEmpty()) {
			for (FinActiviteDto dto : toDeleteDtos) {
				FinActivite finActivite = finActiviteDao.findById(dto.getId());
				finActiviteDao.remove(finActivite);
			}
		}

	}

	// private

	private void createChangementPosition(FinActivite finActivite) {
		DossierEmploye dossierEmploye = dossierEmployeDao.findById(finActivite.getEmploye().getId());
		ChangementPosition finActivitePosition = new ChangementPosition();
		Nomenclature finActivitePositionNc = nomenclatureDao.findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_FIN_ACTIVITE_CODE);
		finActivitePosition.setDossierEmploye(dossierEmploye);
		finActivitePosition.setNomenclatureByPosition(finActivitePositionNc);
		finActivitePosition.setAcceptee(true);
		finActivitePosition.setDateDebut(new Date());
		SituationEntite situation = situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_VALIDEE_CODE);
		finActivitePosition.setSituation(situation);
		changementPositionDao.save(finActivitePosition);
		dossierEmploye.setCurrentPosition(finActivitePosition);
		dossierEmployeDao.save(dossierEmploye);
	}

	private void generateDossierRetraite(FinActivite finActivite) {
		Dossier dossierRetraite = new Dossier();
		dossierRetraite.setDateCreation(new Date());
		Nomenclature dossierRetraiteType = nomenclatureDao.findByCode(UtilConstant.NC_TYPE_DOSSIER,
				TYPE_DOSSIER_DEPART_RETRAITE);
		dossierRetraite.setTypeDossier(TYPE_DOSSIER_DEPART_RETRAITE);
		List<RefTypePieceConstitutive> typePieceConstitutives = typePieceConstitutiveDao
				.findByIdTypeDossier(dossierRetraiteType.getId());
		if (typePieceConstitutives != null) {
			for (RefTypePieceConstitutive typePieceConstitutive : typePieceConstitutives) {
				PieceConstitutive pieceConstitutive = new PieceConstitutive();
				pieceConstitutive.setDossier(dossierRetraite);
				// pieceConstitutive.setNcTypePieceConstitutive(typePieceConstitutive.getNomenclatureByTypePiece());
				// pieceConstitutive.setObligatoire(typePieceConstitutive.getObligatoire());
				pieceConstitutive.setRefTypePieceConstitutive(typePieceConstitutive);
				dossierRetraite.getPieceConstitutives().add(pieceConstitutive);

			}
		}
		finActivite.setDossieRetraite(dossierRetraite);

	}

	private FinActiviteDto saveDemande(FinActiviteDto dto, String entiteName, String typeCessation, String typeDepart) {

		FinActivite finActivite = mapper.map(dto, FinActivite.class);
		finActivite.setSituation(resolveSituation(entiteName, finActivite));
		finActivite.setNomenclature(nomenclatureDao.findByCode(UtilConstant.NC_GRH_TYPE_CESSATION, typeCessation));
		if (typeDepart != null) {
			finActivite.setTypeDepartRetraite(nomenclatureDao.findByCode(UtilConstant.NC_GRH_TYPE_DEPART_RETRAITE,
					typeDepart));
		}
		finActivite = finActiviteDao.save(finActivite);
		persistSituationOccurence(finActivite);

		return mapper.map(finActivite, FinActiviteDto.class);
	}

	private SituationEntite resolveSituation(String entite, FinActivite finActivite) {
		SituationEntite situationEntite;
		if (finActivite.getId() == null) {
			situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(entite,
					UtilConstant.SITUTAION_CREEE_CODE);
		} else if (finActivite.getAccepte() != null) {
			situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(entite,
					UtilConstant.SITUTAION_TRAITEE_CODE);
		} else {
			situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(entite,
					UtilConstant.SITUTAION_ENREGISTREE_CODE);
		}
		return situationEntite;
	}

	private void applyPendingDemissionFilter(SearchMode searchMode) {
		SituationEntite situtationDemissionTraitee = situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstant.ENTITE_DEMANDE_DEMISSION, UtilConstant.SITUTAION_TRAITEE_CODE);
		Nomenclature demissionCessationType = nomenclatureDao.findByCode(UtilConstant.NC_GRH_TYPE_CESSATION,
				UtilConstant.NC_GRH_TYPE_CESSATION_DEMISSION_VALUE);
		searchMode.addFilter(new Filter("situation.id", situtationDemissionTraitee.getId(), FilterMode.NOT));
		searchMode.addFilter(new Filter("nomenclature.id", demissionCessationType.getId(), FilterMode.EQUALS));
	}

	private void applyCessationFinalCree(SearchMode searchMode) {
		searchMode.addFilter(new Filter("accepte", Boolean.TRUE, FilterMode.EQUALS));
		searchMode.addFilter(new Filter("situationCode.code", UtilConstant.SITUTAION_VALIDEE_CODE, FilterMode.NOT));
		searchMode.addFilter(new Filter("dateCessationFinale", FilterMode.IS_NOT_NULL));
	}

	/**
	 * Filtering by etablissement and still present employe.
	 * 
	 * @param etablissementId
	 * @param searchMode
	 * @return the enriched {@link SearchMode}
	 */
	private SearchMode applyGenericFilters(Integer etablissementId, SearchMode searchMode) {
		if (searchMode == null) {
			searchMode = new SearchMode();
		}
		searchMode.addFilter(new Filter("etablissement.id", etablissementId, FilterMode.EQUALS));
		searchMode.addFilter(new Filter("dateCessationFinale", FilterMode.IS_NULL));
		return searchMode;
	}

	private void applyPendingRetraiteAnticpeFilter(SearchMode searchMode) {
		SituationEntite situtationRetaiteAnticpeTraitee = situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstant.ENTITE_DEMANDE_RETRAITE_ANTICIPE, UtilConstant.SITUTAION_TRAITEE_CODE);
		Nomenclature retraiteCessationType = nomenclatureDao.findByCode(UtilConstant.NC_GRH_TYPE_CESSATION,
				UtilConstant.NC_GRH_TYPE_CESSATION_RETRAITE_VALUE);
		Nomenclature departRetraiteAnticipeType = nomenclatureDao.findByCode(UtilConstant.NC_GRH_TYPE_DEPART_RETRAITE,
				UtilConstant.NC_GRH_TYPE_DEPART_RETRAITE_ANTICIPE_VALUE);
		searchMode.addFilter(new Filter("nomenclature.id", retraiteCessationType.getId(), FilterMode.EQUALS));
		searchMode.addFilter(new Filter("situation.id", situtationRetaiteAnticpeTraitee.getId(), FilterMode.NOT));
		searchMode
				.addFilter(new Filter("typeDepartRetraite.id", departRetraiteAnticipeType.getId(), FilterMode.EQUALS));

	}

	private void applyAdmisRetraiteFilters(SearchMode searchMode) {
		Nomenclature retraiteCessationType = nomenclatureDao.findByCode(UtilConstant.NC_GRH_TYPE_CESSATION,
				UtilConstant.NC_GRH_TYPE_CESSATION_RETRAITE_VALUE);
		Nomenclature departRetraiteNormalType = nomenclatureDao.findByCode(UtilConstant.NC_GRH_TYPE_DEPART_RETRAITE,
				UtilConstant.NC_GRH_TYPE_DEPART_RETRAITE_NORMAL_VALUE);
		searchMode.addFilter(new Filter("nomenclature.id", retraiteCessationType.getId(), FilterMode.EQUALS));
		searchMode.addFilter(new Filter("typeDepartRetraite.id", departRetraiteNormalType.getId(), FilterMode.EQUALS));
		// searchMode.addFilter(new Filter("situation", FilterMode.IS_NULL));

	}

	private void persistSituationOccurence(FinActivite finActivite) {
		SituationEntiteOccurrence occurence = new SituationEntiteOccurrence();
		occurence.setDateSituation(new Date());
		occurence.setIdOccurrence(finActivite.getId().intValue());
		occurence.setSituationEntite(finActivite.getSituation());
		situationEntiteOccurrenceDao.persist(occurence);
	}

	// setters

	@Autowired
	public void setFinActiviteDao(FinActiviteDao finActiviteDao) {
		this.finActiviteDao = finActiviteDao;
	}

	@Autowired
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Autowired
	public void setSituationEntiteDao(SituationEntiteDao situationEntiteDao) {
		this.situationEntiteDao = situationEntiteDao;
	}

	@Autowired
	public void setNomenclatureDao(NomenclatureDao nomenclatureDao) {
		this.nomenclatureDao = nomenclatureDao;
	}

	@Autowired
	public void setSituationEntiteOccurrenceDao(SituationEntiteOccurrenceDao situationEntiteOccurrenceDao) {
		this.situationEntiteOccurrenceDao = situationEntiteOccurrenceDao;
	}

	@Autowired
	public void setTypePieceConstitutiveDao(RefTypePieceConstitutiveDao typePieceConstitutiveDao) {
		this.typePieceConstitutiveDao = typePieceConstitutiveDao;
	}

	@Autowired
	public void setDossierEmployeDao(DossierEmployeDao dossierEmployeDao) {
		this.dossierEmployeDao = dossierEmployeDao;
	}

	@Autowired
	public void setChangementPositionDao(ChangementPositionDao changementPositionDao) {
		this.changementPositionDao = changementPositionDao;
	}

	@Override
	protected CommonDao<FinActivite, Long> getDao() {
		return finActiviteDao;
	}

}
