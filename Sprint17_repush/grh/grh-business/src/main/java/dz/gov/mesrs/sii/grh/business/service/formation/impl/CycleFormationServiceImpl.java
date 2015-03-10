package dz.gov.mesrs.sii.grh.business.service.formation.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.CycleFormationDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.grh.CycleFormation;
import dz.gov.mesrs.sii.grh.business.model.dto.CycleFormationDto;
import dz.gov.mesrs.sii.grh.business.service.formation.CycleFormationService;


@Service("cycleFormationService")
public class CycleFormationServiceImpl extends CommonServiceImpl<CycleFormation , CycleFormationDto,Long> implements CycleFormationService  {
	
	private CycleFormationDao	cycleFormationDao;
	private SituationEntiteDao situationEntiteDao;
	private SituationEntiteOccurrenceDao situationEntiteOccurrenceDao;
	private NomenclatureDao nomenclatureDao;
	
	@Override
	protected CommonDao<CycleFormation, Long> getDao() {
		return cycleFormationDao;
	}


	
	@Override
	public List<CycleFormationDto> findAllCycleFormationCrees(
			 SearchMode searchMode, String searchKeyWords) {
		//applyFilterEtablissment(searchMode, etablissmentId);
		applyFilterCree(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
		
	}

	@Override
	@Transactional
	public CycleFormationDto saveCycleFormation(
			CycleFormationDto cycleFormationDto) {
		CycleFormation cycleFormation = mapper.map(cycleFormationDto, CycleFormation.class);
		SituationEntite situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstants.ENTITE_CYCLE_FORMATION_CODE, UtilConstants.SITUATION_CREEE_CODE);
		cycleFormation.setSituationEntite(situationEntite);
		cycleFormation = cycleFormationDao.save(cycleFormation);
		persistSituationOccurence(cycleFormation);
		return mapper.map(cycleFormation, CycleFormationDto.class);
	}


	@Override
	@Transactional
	public CycleFormationDto publierCycleFormation(
			CycleFormationDto cycleFormationDto) {
		CycleFormation cycleFormation = mapper.map(cycleFormationDto, CycleFormation.class);
		SituationEntite situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstants.ENTITE_CYCLE_FORMATION_CODE, UtilConstants.SITUATION_PUBLIEE_CODE);
		cycleFormation.setSituationEntite(situationEntite);
		cycleFormation = cycleFormationDao.save(cycleFormation);
		persistSituationOccurence(cycleFormation);
		return mapper.map(cycleFormation, CycleFormationDto.class);
	}
	
	private void applyFilterCree(SearchMode searchMode) {
		searchMode.addFilter(new Filter("situationEntite.id",
				situationEntiteDao.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_CYCLE_FORMATION_CODE,
						UtilConstants.SITUATION_CREEE_CODE).getId(),
				FilterMode.EQUALS));
	}
	
	private void applyFilterPubliee(SearchMode searchMode) {
		searchMode.addFilter(new Filter("situationEntite.id",
				situationEntiteDao.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_CYCLE_FORMATION_CODE,
						UtilConstants.SITUATION_PUBLIEE_CODE).getId(),
				FilterMode.EQUALS));
	}
	private void persistSituationOccurence(CycleFormation cycleFormation) {
		SituationEntiteOccurrence occurence = new SituationEntiteOccurrence();
		occurence.setDateSituation(new Date());
		occurence.setIdOccurrence(cycleFormation.getId().intValue());
		occurence.setSituationEntite(cycleFormation.getSituationEntite());
		situationEntiteOccurrenceDao.persist(occurence);
	}
	
	
	

	/**
	 * @return the CycleFormationDao
	 */
	public CycleFormationDao getCycleFormationDao() {
		return cycleFormationDao;
	}

	/**
	 * @param CycleFormationDao  to set
	 */
	@Autowired
	public void setCycleFormationDao(CycleFormationDao cycleFormationDao) {
		this.cycleFormationDao = cycleFormationDao;
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



