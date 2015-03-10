package dz.gov.mesrs.sii.grh.business.service.formation.impl;

import java.util.ArrayList;
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
import dz.gov.mesrs.sii.commons.data.dao.grh.SessionFormationDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.grh.SessionFormation;
import dz.gov.mesrs.sii.grh.business.model.dto.SessionFormationDto;
import dz.gov.mesrs.sii.grh.business.service.formation.SessionFormationService;


@Service("sessionFormationService")
public class SessionFormationServiceImpl extends CommonServiceImpl<SessionFormation , SessionFormationDto,Long> implements SessionFormationService  {
	
	private SessionFormationDao	sessionFormationDao;
	private SituationEntiteDao situationEntiteDao;
	private SituationEntiteOccurrenceDao situationEntiteOccurrenceDao;
	private NomenclatureDao nomenclatureDao;
	
	@Override
	protected CommonDao<SessionFormation, Long> getDao() {
		return sessionFormationDao;
	}
	
	
	@Override
	public List<SessionFormationDto> findAllSessionFormationCrees(
			SearchMode searchMode, String searchKeyWords) {
			
		//applyFilterEtablissment(searchMode, etablissmentId);
		applyFilterCree(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
		
	}
	
	@Override
	public List<SessionFormationDto> findAllSessionFormationCreesAndCycleCree(
			SearchMode searchMode, String searchKeyWords) {
		List<SessionFormationDto> result = null;
		//applyFilterEtablissment(searchMode, etablissmentId);
		applyFilterCree(searchMode);
		List<SessionFormationDto> list = super.findAllByKeyWord(searchKeyWords, searchMode);
		if(list!=null && !list.isEmpty()){
			result = new ArrayList<SessionFormationDto>();
			for (SessionFormationDto sessionFormationDto : list) {
				if(sessionFormationDto.getCycleFormationDto().getSituationEntiteDto().getCodeSituation().equals(UtilConstants.SITUATION_CREEE_CODE)){
					result.add(sessionFormationDto);
				}
				
			}
			
		}
		return result;
	}


	@Override
	public List<SessionFormationDto> findAllSessionFormationCreesAndCyclePublie(
			SearchMode searchMode, String searchKeyWords) {
		List<SessionFormationDto> result = null;
		//applyFilterEtablissment(searchMode, etablissmentId);
		applyFilterCree(searchMode);
		List<SessionFormationDto> list = super.findAllByKeyWord(searchKeyWords, searchMode);
		if(list!=null && !list.isEmpty()){
			result = new ArrayList<SessionFormationDto>();
			for (SessionFormationDto sessionFormationDto : list) {
				if(sessionFormationDto.getCycleFormationDto().getSituationEntiteDto().getCodeSituation().equals(UtilConstants.SITUATION_PUBLIEE_CODE)){
					result.add(sessionFormationDto);
				}
				
			}
			
		}
		return result;
	}

	@Override
	@Transactional
	public SessionFormationDto saveSessionFormation(
			SessionFormationDto sessionFormationDto) {
		SessionFormation sessionFormation = mapper.map(sessionFormationDto, SessionFormation.class);
		SituationEntite situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstants.ENTITE_SESSION_FORMATION_CODE, UtilConstants.SITUATION_CREEE_CODE);
		sessionFormation.setSituationEntite(situationEntite);
		sessionFormation = sessionFormationDao.save(sessionFormation);
		persistSituationOccurence(sessionFormation);
		return mapper.map(sessionFormation, SessionFormationDto.class);
	}

	@Override
	@Transactional
	public SessionFormationDto saveDemandesInsciption(
			SessionFormationDto sessionFormationDto) {
		//Pas de changement de situation
		SessionFormation sessionFormation = mapper.map(sessionFormationDto, SessionFormation.class);
//		SituationEntite situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
//				UtilConstants.ENTITE_SESSION_FORMATION_CODE, UtilConstants.SITUATION_CREEE_CODE);
//		sessionFormation.setSituationEntite(situationEntite);
		sessionFormation = sessionFormationDao.save(sessionFormation);
//		persistSituationOccurence(sessionFormation);
		return mapper.map(sessionFormation, SessionFormationDto.class);
	}

	
	private void applyFilterCree(SearchMode searchMode) {
		searchMode.addFilter(new Filter("situationEntite.id",
				situationEntiteDao.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_SESSION_FORMATION_CODE,
						UtilConstants.SITUATION_CREEE_CODE).getId(),
				FilterMode.EQUALS));
	}
	
	private void persistSituationOccurence(SessionFormation sessionFormation) {
		SituationEntiteOccurrence occurence = new SituationEntiteOccurrence();
		occurence.setDateSituation(new Date());
		occurence.setIdOccurrence(sessionFormation.getId().intValue());
		occurence.setSituationEntite(sessionFormation.getSituationEntite());
		situationEntiteOccurrenceDao.persist(occurence);
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
	
	

	/**
	 * @return the SessionFormationDao
	 */
	public SessionFormationDao getSessionFormationDao() {
		return sessionFormationDao;
	}

	/**
	 * @param SessionFormationDao  to set
	 */
	@Autowired
	public void setSessionFormationDao(SessionFormationDao sessionFormationDao) {
		this.sessionFormationDao = sessionFormationDao;
	}




	

	
	
}



