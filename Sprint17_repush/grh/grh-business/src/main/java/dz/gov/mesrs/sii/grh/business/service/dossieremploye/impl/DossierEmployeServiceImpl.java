package dz.gov.mesrs.sii.grh.business.service.dossieremploye.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
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
import dz.gov.mesrs.sii.commons.data.dao.grh.CarriereDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.DossierEmployeDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.grh.Carriere;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.CarriereDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.service.dossieremploye.DossierEmployeService;

@Service("dossierEmployeService")
public class DossierEmployeServiceImpl extends CommonServiceImpl<DossierEmploye, DossierEmployeDto, Long> implements
		DossierEmployeService {

	private DossierEmployeDao dossierEmployeDao;
	private SituationEntiteDao situationEntiteDao;
	private SituationEntiteOccurrenceDao situationEntiteOccurrenceDao;
	private NomenclatureDao nomenclatureDao;
	private CarriereDao carriereDao;

	// TODO gerer en propriete systeme
	// TODO remove dans finActiviteService !!!
	private int ageRetraite = 30;

	@Autowired
	public void setDossierEmployeDao(DossierEmployeDao dossierEmployeDao) {
		this.dossierEmployeDao = dossierEmployeDao;
	}
	@Transactional
	@Override
	public DossierEmployeDto save(DossierEmployeDto dossierEmployeDto) {
		Assert.notNull(dossierEmployeDto, "can't save a null dto");
		if (dossierEmployeDto.getNomenclatureByTypeCompteDto()!= null
				&& dossierEmployeDto.getNomenclatureByTypeCompteDto().getId() == null) {
			dossierEmployeDto.setNomenclatureByTypeCompteDto(null);
		}
		if (dossierEmployeDto.getNomenclatureByTypePermisDto()!= null
				&& dossierEmployeDto.getNomenclatureByTypePermisDto().getId() == null) {
			dossierEmployeDto.setNomenclatureByTypePermisDto(null);
		}
		if (dossierEmployeDto.getRefStructureDto()!= null
				&& dossierEmployeDto.getRefStructureDto().getId() == null) {
			dossierEmployeDto.setRefStructureDto(null);
		}
		if (dossierEmployeDto.getNiveauCompetenceDto()!= null
				&& dossierEmployeDto.getNiveauCompetenceDto().getId() == null) {
			dossierEmployeDto.setNiveauCompetenceDto(null);
		}
		if (dossierEmployeDto.getNiveauQualificationDto() != null
				&& dossierEmployeDto.getNiveauQualificationDto().getId() == null) {
			dossierEmployeDto.setNiveauQualificationDto(null);
		}
		
		dossierEmployeDto = super.save(dossierEmployeDto);
		

		
		return dossierEmployeDto;
	}

	@Override
	public int countAllAdmissibleRetraite(Date dateDebutPeriode, Date dateFinPeriode) {
		SearchMode searchMode = new SearchMode();

		Calendar calendarDebut = new GregorianCalendar();
		calendarDebut.setTime(dateDebutPeriode);
		calendarDebut.add(Calendar.YEAR, -ageRetraite);

		Calendar calendarFin = new GregorianCalendar();
		calendarFin.setTime(dateFinPeriode);
		calendarFin.add(Calendar.YEAR, -ageRetraite);

		searchMode.addFilter(new Filter("refIndividu.dateNaissance", calendarDebut.getTime(), calendarFin.getTime(),
				FilterMode.BETWEEN));

		DossierEmploye example = new DossierEmploye();
		example.setRefIndividu(new RefIndividu());

		return dossierEmployeDao.countAllByExample(example, searchMode);
	}

	@Override
	public List<DossierEmployeDto> findAllAdmissibleRetraite(DossierEmployeDto dossierEmployeDto,
			SearchMode searchMode, Date dateDebutPeriode, Date dateFinPeriode) {
		DossierEmploye example = mapper.map(dossierEmployeDto, DossierEmploye.class);
		Calendar calendarDebut = new GregorianCalendar();
		calendarDebut.setTime(dateDebutPeriode);
		calendarDebut.add(Calendar.YEAR, -ageRetraite);

		Calendar calendarFin = new GregorianCalendar();
		calendarFin.setTime(dateFinPeriode);
		calendarFin.add(Calendar.YEAR, -ageRetraite);

		searchMode.addFilter(new Filter("refIndividu.dateNaissance", calendarDebut.getTime(), calendarFin.getTime(),
				FilterMode.BETWEEN));

		List<DossierEmployeDto> results = null;
		List<DossierEmploye> entities = dossierEmployeDao.findAllByExample(example, searchMode);
		if (entities != null && !entities.isEmpty()) {
			results = new ArrayList<>();
			for (DossierEmploye dossier : entities) {
				results.add(mapper.map(dossier, DossierEmployeDto.class));
			}
		}

		return results;
	}

	
	
	@Transactional
	public DossierEmployeDto saveTitularisation(Date dateTitularisation, CarriereDto carriereDto) {
		Carriere carriere = mapper.map(carriereDto, Carriere.class);
		carriere = carriereDao.save(carriere);
		DossierEmploye dossierEmploye = carriere.getDossierEmploye();
		dossierEmploye.setCarriereCourante(carriere);
		dossierEmploye.setDateTitularisation(dateTitularisation);
		dossierEmploye.setTitularise(Boolean.TRUE);
		SituationEntite situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE, UtilConstants.SITUATION_TITULARISE_CODE);
		dossierEmploye.setSituationEntite(situationEntite);
		dossierEmploye = dossierEmployeDao.save(dossierEmploye);
		persistSituationOccurence(dossierEmploye);
		return mapper.map(dossierEmploye, DossierEmployeDto.class);
	}
	
	@Override
	@Transactional
	public DossierEmployeDto saveDossierEmployeByCarriere(CarriereDto carriereDto) {
		Carriere carriere = mapper.map(carriereDto, Carriere.class);
		carriere = carriereDao.save(carriere);
		DossierEmploye dossierEmploye = carriere.getDossierEmploye();
		dossierEmploye.setCarriereCourante(carriere);
		dossierEmploye = dossierEmployeDao.save(dossierEmploye);
		persistSituationOccurence(dossierEmploye);
		return mapper.map(dossierEmploye, DossierEmployeDto.class);
	}
	private void persistSituationOccurence(DossierEmploye dossierEmploye) {
		SituationEntiteOccurrence occurence = new SituationEntiteOccurrence();
		occurence.setDateSituation(new Date());
		// FIXME les id occurence doivent etre des Long
		occurence.setIdOccurrence(dossierEmploye.getId().intValue());
		occurence.setSituationEntite(dossierEmploye.getSituationEntite());
		situationEntiteOccurrenceDao.persist(occurence);
	}
	
	@Override
	public List<Long> findListeAptitudePromotionID(List<Object> paramettres,Collection<Integer> listNomenclatureActifDetachementID){
		return dossierEmployeDao.findListeAptitudePromotionID(paramettres, listNomenclatureActifDetachementID);		
	}
	
	@Override
	public List<Long> findListePromotionParModaliteID(List<Object> paramettres,Collection<Integer> listNomenclatureActifDetachementID
			,Collection<Integer> listnomenclatureBytypePromotionModaliteID,Collection<Integer> listNomenclatureModaliteRecrutementID) {
		return dossierEmployeDao.findListePromotionParModaliteID(paramettres,listNomenclatureActifDetachementID,listnomenclatureBytypePromotionModaliteID, listNomenclatureModaliteRecrutementID);		
	}
	
	@Override
	public List<Long> findListePromotionParModalConfID(List<Object> paramettres,Collection<Integer> listNomenclatureActifDetachementID
			,Collection<Integer> listnomenclatureBytypePromotionModaliteID,Collection<Integer> listNomenclatureModaliteRecrutementID) {
		return dossierEmployeDao.findListePromotionParModalConfID(paramettres,listNomenclatureActifDetachementID,listnomenclatureBytypePromotionModaliteID, listNomenclatureModaliteRecrutementID);		
	}
	
	
	
	
	@Override
	public List<DossierEmployeDto> findAllActif(DossierEmployeDto example, SearchMode searchMode) {
		Nomenclature nc = nomenclatureDao.findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_FIN_ACTIVITE_CODE);
		searchMode.addFilter(new Filter("currentPosition.nomenclatureByPosition.id", nc.getId(), FilterMode.NOT));
		return super.findAllByExample(example, searchMode);
	}

	@Override
	public int countAllActif(DossierEmployeDto example) {
		SearchMode searchMode = new SearchMode();
		Nomenclature nc = nomenclatureDao.findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_FIN_ACTIVITE_CODE);
		searchMode.addFilter(new Filter("currentPosition.nomenclatureByPosition.id", nc.getId(), FilterMode.NOT));
		return super.countAllByExample(example, searchMode);
	}
	@Override
	public List<Long> findListePromotionAconfirmarID(List<Object> paramettres,Collection<Integer> listNomenclatureActifDetachementID
			,Collection<Integer> listnomenclatureBytypePromotionModaliteID,Collection<Integer> listNomenclatureModaliteRecrutementID) {
//		return dossierEmployeDao.findListePromotionAconfirmarID(paramettres,listNomenclatureActifDetachementID,listnomenclatureBytypePromotionModaliteID, listNomenclatureModaliteRecrutementID);
		return null;
	}

	@Override
	protected CommonDao<DossierEmploye, Long> getDao() {
		return dossierEmployeDao;
	}

	public DossierEmployeDao getDossierEmployeDao() {
		return dossierEmployeDao;
	}

	public SituationEntiteDao getSituationEntiteDao() {
		return situationEntiteDao;
	}

	@Autowired
	public void setSituationEntiteDao(SituationEntiteDao situationEntiteDao) {
		this.situationEntiteDao = situationEntiteDao;
	}

	public SituationEntiteOccurrenceDao getSituationEntiteOccurrenceDao() {
		return situationEntiteOccurrenceDao;
	}

	@Autowired
	public void setSituationEntiteOccurrenceDao(SituationEntiteOccurrenceDao situationEntiteOccurrenceDao) {
		this.situationEntiteOccurrenceDao = situationEntiteOccurrenceDao;
	}

	public NomenclatureDao getNomenclatureDao() {
		return nomenclatureDao;
	}

	@Autowired
	public void setNomenclatureDao(NomenclatureDao nomenclatureDao) {
		this.nomenclatureDao = nomenclatureDao;
	}

	public CarriereDao getCarriereDao() {
		return carriereDao;
	}

	@Autowired
	public void setCarriereDao(CarriereDao carriereDao) {
		this.carriereDao = carriereDao;
	}

	@Override
	public DossierEmployeDto findByIndividuId(Integer individuId) {
		Assert.notNull(individuId);
		RefIndividu refIndividu = new RefIndividu();
		refIndividu.setId(individuId);
		DossierEmploye example = new DossierEmploye();
		example.setRefIndividu(refIndividu);
		DossierEmploye resultObject = getDao().findUniqueOrNoneByExample(example);
		if (resultObject != null)
			return mapper.map(resultObject, DossierEmployeDto.class);
		else
			return null;
	}

}
