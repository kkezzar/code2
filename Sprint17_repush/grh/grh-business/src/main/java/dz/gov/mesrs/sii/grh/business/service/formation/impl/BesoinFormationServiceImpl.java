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
import dz.gov.mesrs.sii.commons.data.dao.grh.BesoinFormationDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.grh.BesoinFormation;
import dz.gov.mesrs.sii.grh.business.model.dto.BesoinFormationDto;
import dz.gov.mesrs.sii.grh.business.service.formation.BesoinFormationService;


@Service("besoinFormationService")
public class BesoinFormationServiceImpl extends CommonServiceImpl<BesoinFormation , BesoinFormationDto,Integer> implements BesoinFormationService  {
	
	private BesoinFormationDao	besoinFormationDao;
	private SituationEntiteDao situationEntiteDao;
	private SituationEntiteOccurrenceDao situationEntiteOccurrenceDao;
	private NomenclatureDao nomenclatureDao;
	
	@Override
	protected CommonDao<BesoinFormation, Integer> getDao() {
		return besoinFormationDao;
	}

	

	@Override
	public List<BesoinFormationDto> findAllBesoinFormationCrees(
			int etablissmentId, SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissment(searchMode, etablissmentId);
		applyFilterCree(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public int countAllBesoinFormationCrees(int etablissmentId,
			SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissment(searchMode, etablissmentId);
		applyFilterCree(searchMode);
		return super.countByKeyWord(searchKeyWords, searchMode);
	}

	
	@Override
	@Transactional
	public BesoinFormationDto saveBesoinFormation(
			BesoinFormationDto besoinFormationDto) {
		BesoinFormation besoinFormation = mapper.map(besoinFormationDto, BesoinFormation.class);
		SituationEntite situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstants.ENTITE_BESOIN_FORMATION_CODE, UtilConstants.SITUATION_CREEE_CODE);
		besoinFormation.setSituationEntite(situationEntite);
		besoinFormation = besoinFormationDao.save(besoinFormation);
		persistSituationOccurence(besoinFormation);
		return mapper.map(besoinFormation, BesoinFormationDto.class);
	
	}
	
	@Override
	@Transactional
	public BesoinFormationDto saveEvaluationBesoinFormation(
			BesoinFormationDto besoinFormationDto) {
		BesoinFormation besoinFormation = mapper.map(besoinFormationDto, BesoinFormation.class);
		SituationEntite situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstants.ENTITE_BESOIN_FORMATION_CODE, UtilConstants.SITUATION_EVALUEE_CODE);
		besoinFormation.setSituationEntite(situationEntite);
		besoinFormation = besoinFormationDao.save(besoinFormation);
		persistSituationOccurence(besoinFormation);
		return mapper.map(besoinFormation, BesoinFormationDto.class);
	
	}
	

	private void persistSituationOccurence(BesoinFormation besoinFormation) {
		SituationEntiteOccurrence occurence = new SituationEntiteOccurrence();
		occurence.setDateSituation(new Date());
		occurence.setIdOccurrence(besoinFormation.getId());
		occurence.setSituationEntite(besoinFormation.getSituationEntite());
		situationEntiteOccurrenceDao.persist(occurence);
	}
	
	private void applyFilterEtablissment(SearchMode searchMode, int etablissmentId) {
		searchMode.addFilter(new Filter("refEtablissement.id", etablissmentId, FilterMode.EQUALS));
	}
	
	private void applyFilterCree(SearchMode searchMode) {
		searchMode.addFilter(new Filter("situationEntite.id",
				situationEntiteDao.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_BESOIN_FORMATION_CODE,
						UtilConstants.SITUATION_CREEE_CODE).getId(),
				FilterMode.EQUALS));
	}
	
	//Getter et Setter
	
	/**
	 * @return the BesoinFormationDao
	 */
	public BesoinFormationDao getBesoinFormationDao() {
		return besoinFormationDao;
	}

	/**
	 * @param BesoinFormationDao  to set
	 */
	@Autowired
	public void setBesoinFormationDao(BesoinFormationDao besoinFormationDao) {
		this.besoinFormationDao = besoinFormationDao;
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



