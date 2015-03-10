/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefTacheDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefTache;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTacheDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.service.RefTacheService;


/**
 * @author BELDI Jamel  on : 13 f�vr. 2014  12:09:10
 */
@Service ("refTacheServiceImpl")
public class RefTacheServiceImpl implements RefTacheService{
	private static final Logger log = LoggerFactory.getLogger(RefTacheServiceImpl.class.getName());
	@Autowired
	@Qualifier("refTacheDaoImpl")
	private RefTacheDao refTacheDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@Autowired
	@Qualifier("refSituationDaoImpl")
	private RefSituationDao refSituationDao;
	@Autowired
	@Qualifier("refParametrageServiceImpl")
	private RefParametrageService refParametrageServiceImpl;
	

	/**
	 * [RefEvenementServiceImpl.refParametrageServiceImpl] Getter 
	 * @author BELDI Jamel on : 7 avr. 2014  14:59:45
	 * @return the refParametrageServiceImpl
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	/**
	 * [RefEvenementServiceImpl.refParametrageServiceImpl] Setter 
	 * @author BELDI Jamel on : 7 avr. 2014  14:59:45
	 * @param refParametrageServiceImpl the refParametrageServiceImpl to set
	 */
	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}

	/**
	 * @return the refTacheDao
	 */
	public RefTacheDao getRefTacheDao() {
		return refTacheDao;
	}

	/**
	 * @param refTacheDao the refTacheDao to set
	 */
	public void setRefTacheDao(RefTacheDao refTacheDao) {
		this.refTacheDao = refTacheDao;
	}
	
	/**
	 * @return the refSituationDao
	 */
	public RefSituationDao getRefSituationDao() {
		return refSituationDao;
	}

	/**
	 * @param refSituationDao
	 *            the refSituationDao to set
	 */
	public void setRefSituationDao(RefSituationDao refSituationDao) {
		this.refSituationDao = refSituationDao;
	}

	/**
	 * 
	 */
	public RefTacheServiceImpl() {
	}

	@Override
	public RefTacheDto findById(Integer id) {
		RefTacheDto refTacheDto = null;
		try{
			RefTache refTache = refTacheDao.findById(id);
			if(refTache != null){
				refTacheDto = new RefTacheDto();
 				 mapper.map(refTache, refTacheDto);
				log.info("success get dto: "+refTacheDto.getTitre());
				return refTacheDto;
			}
		}catch(Exception e){
			log.error("get failed", e);
			
		}
		return refTacheDto;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefTacheService#findBAll()
	 */
	@Override
	public List<RefTacheDto> findAll() {
		List<RefTacheDto> refTacheListDto = new ArrayList<RefTacheDto>();
		try {
			List<RefTache> refTacheList = refTacheDao.findAll();
			if (refTacheList != null) {
				log.debug("individu list success with size =  {}", new Object[]{refTacheList.size()});
				for (RefTache currentRefTache : refTacheList) {
					RefTacheDto refTacheDto = new RefTacheDto();
					mapper.map(currentRefTache, refTacheDto);
					refTacheListDto.add(refTacheDto);
				}
				return refTacheListDto;
			}
		} catch (RuntimeException e) {
			log.error("get individu list failed", e);
			throw e;
		}

		return null;
	}

	/**
	 * [RefTacheServiceImpl.mapper] Getter 
	 * @author MAKERRI Sofiane on : 6 janv. 2014  17:04:29
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefTacheServiceImpl.mapper] Setter 
	 * @author MAKERRI Sofiane on : 6 janv. 2014  17:04:29
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}
	
	
	
	
	@Override
	public List<RefTacheDto> findByEvenement(Integer evenementId) {

		List<RefTacheDto> refTacheListDto = new ArrayList<RefTacheDto>();
		try {			
			List<RefTache> refTacheList = refTacheDao
					.findByEvenement(evenementId);
			if (refTacheList != null && !refTacheList.isEmpty()) {
				log.debug("contrat list success with size = {}", new Object[]{refTacheList.size()});

				for (RefTache currentRefTache : refTacheList) {
					RefTacheDto currentRefTacheDto = new RefTacheDto();
					mapper.map(currentRefTache, currentRefTacheDto);
					refTacheListDto.add(currentRefTacheDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refTacheListDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void save(RefTacheDto refTacheDto) {
		RefTache refTache = new RefTache();
		mapper.map(refTacheDto, refTache);
		try {
			RefSituation refSituation = new RefSituation();
			refTacheDao.persist(refTache);
			refSituation.setDateSituation(new Date());
			refSituation.setIdEntite(refTache.getId());
			refSituation.setNomEntite(RefTache.class.getSimpleName());
			RefEntiteSituation refEntiteSituation = new RefEntiteSituation();
			refEntiteSituation.setId(16/*Integer.parseInt(refParametrageServiceImpl.getParamConfiguration(UtilConstant.TACHE_SITUATION_CREE_ID_KEY, UtilConstant.CONFIGURATION_UTIL).getValue())*/);
			refSituation.setRefEntiteSituation(refEntiteSituation);
			refSituationDao.persist(refSituation);
			log.debug("save successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
		}
		

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefTacheDto refTacheDto) {
		RefTache refTache = new RefTache();
		mapper.map(refTacheDto, refTache);
		try {
			refTacheDao.merge(refTache);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}
	
	}

	

	@Override
	public void delete(Integer id) {
		
		try {
			RefTache refTache = refTacheDao.findById(id);
			if(refTache!=null){	
				refTache.setActive(false);
			refTacheDao.merge(refTache);
			}
			log.debug("desactiver successful");
		} catch (RuntimeException e) {
			log.error("desactiver failed", e);
			throw e;
		}
	}

	@Override
	public List<RefTacheDto> findByDomaineLMD(String identifiant) {
		// TODO Auto-generated method stub
		return null;
	}



}
