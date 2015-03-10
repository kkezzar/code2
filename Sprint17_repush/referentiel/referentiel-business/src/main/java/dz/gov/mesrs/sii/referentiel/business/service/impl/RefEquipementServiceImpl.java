/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefEquipementServiceImpl.java] 
 * @author BELBRIK Oualid on : 06 03. 2014  10:59:45
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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEquipementDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEquipement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEquipementDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefEquipementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;

/**
 * @author BELBRIK Oualid on : 06 03. 2014 10:59:45
 */
@Service("refEquipementServiceImpl")
public class RefEquipementServiceImpl implements RefEquipementService {

	/**
	 * [RefEquipementServiceImpl.RefEquipementServiceImpl()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 06 03. 2014 10:59:45
	 */
	private static final Logger log = LoggerFactory.getLogger(RefEquipementServiceImpl.class.getName());
	@Autowired
	@Qualifier("refEquipementDaoImpl")
	private RefEquipementDao refEquipementDao;
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

	public RefEquipementServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public List<RefEquipementDto> findGeneric(String value) {
		List<RefEquipementDto> refEquipementListDto = new ArrayList<RefEquipementDto>();
		try {
			List<RefEquipement> refEquipementList = refEquipementDao.findGeneric(value);
			if (refEquipementList != null) {
				log.debug("stucture list success with size =  {}", new Object[]{refEquipementList.size()});

				for (RefEquipement currentRefEquipement : refEquipementList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefEquipement.getId(),
									RefEquipement.class.getSimpleName());
					RefEquipementDto refEquipementDto = new RefEquipementDto();
					mapper.map(currentRefEquipement, refEquipementDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refEquipementDto);
					}
					refEquipementListDto.add(refEquipementDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refEquipementListDto;
	}

	
	public List<RefEquipementDto> findAdvanced(RefEquipementDto refEquipementDto) {
		List<RefEquipementDto> refEquipementListDto = new ArrayList<RefEquipementDto>();
		try {
			RefEquipement refEquipement = new RefEquipement();
			mapper.map(refEquipementDto, refEquipement);
			List<RefEquipement> refEquipementList = refEquipementDao
					.findAdvanced(refEquipement);
			if (refEquipementList != null && !refEquipementList.isEmpty()) {
				log.debug("equipement list success with size =  {}", new Object[]{
						refEquipementList.size()});

				for (RefEquipement currentRefEquipement : refEquipementList) {
					RefEquipementDto currentRefEquipementDto = new RefEquipementDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefEquipement.getId(),
									RefEquipement.class.getSimpleName());
					mapper.map(currentRefEquipement, currentRefEquipementDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefEquipementDto);
					}
					refEquipementListDto.add(currentRefEquipementDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refEquipementListDto;
	}

	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefEquipementDto save(RefEquipementDto refEquipementDto) {
		log.info("service saveEquipement");
		RefEquipement refEquipement = new RefEquipement();
		mapper.map(refEquipementDto, refEquipement);
		try {
			refEquipementDao.persist(refEquipement);			
			RefSituation refSituation = new RefSituation();
			refSituation.setDateSituation(new Date());
			refSituation.setIdEntite(refEquipement.getId());
			refSituation.setNomEntite(RefEquipement.class.getSimpleName());
			RefEntiteSituation refEntiteSituation = new RefEntiteSituation();
			refEntiteSituation.setId(24/*Integer.parseInt(refParametrageServiceImpl.getParamConfiguration(UtilConstant.EQUIPEMENT_SITUATION_CREE_ID_KEY, UtilConstant.CONFIGURATION_UTIL).getValue())*/);
			refSituation.setRefEntiteSituation(refEntiteSituation);
			refSituationDao.persist(refSituation);
			mapper.map(refEquipement, refEquipementDto);
			log.debug("save successful");
			return refEquipementDto;
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

	}

	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefEquipementDto refEquipementDto) {
		RefEquipement refEquipement = new RefEquipement();
		mapper.map(refEquipementDto, refEquipement);
		try {
			refEquipementDao.merge(refEquipement);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	
	@Override
	public void saveOrUpdate(RefEquipementDto refEquipementDto) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public RefEquipementDto findById(String id) {
		try {
			RefEquipement refEquipement = refEquipementDao.findById(id);
			if (refEquipement != null) {
				log.debug(" refEquipementDao.findById(id) success with id =  "
						+ refEquipement.getIdentifiant());
				RefEquipementDto refEquipementDto = new RefEquipementDto();
				mapper.map(refEquipement, refEquipementDto);
				return refEquipementDto;
			}

		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}

	
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	/**
	 * [RefEquipementServiceImpl.refEquipementDao] Getter
	 * 
	 * @author BELBRIK Oualid on : 06 03. 2014 11:01:53
	 * @return the refEquipementDao
	 */
	public RefEquipementDao getRefEquipementDao() {
		return refEquipementDao;
	}

	/**
	 * [RefEquipementServiceImpl.refEquipementDao] Setter
	 * 
	 * @author BELBRIK Oualid on : 06 03. 2014 11:01:53
	 * @param refEquipementDao
	 *            the refEquipementDao to set
	 */
	public void setRefEquipementDao(RefEquipementDao refEquipementDao) {
		this.refEquipementDao = refEquipementDao;
	}

	/**
	 * [RefEquipementServiceImpl.mapper] Getter
	 * 
	 * @author BELBRIK Oualid on : 06 03. 2014 11:01:53
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefEquipementServiceImpl.mapper] Setter
	 * 
	 * @author BELBRIK Oualid on : 06 03. 2014 11:01:53
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	
	@Override
	public List<RefEquipementDto> findAll() {
		List<RefEquipementDto> refEquipementListDto = new ArrayList<RefEquipementDto>();
		try {
			List<RefEquipement> refEquipementList = refEquipementDao.findAll();
			if (refEquipementList != null) {
				log.debug("equipement list success with size =  {}",new Object[]{refEquipementList.size()});
				for (RefEquipement currentRefEquipement : refEquipementList) {
					RefEquipementDto refEquipementDto = new RefEquipementDto();
					mapper.map(currentRefEquipement, refEquipementDto);
					refEquipementListDto.add(refEquipementDto);
				}
				return refEquipementListDto;

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;

	}


	public RefSituationDao getRefSituationDao() {
		return refSituationDao;
	}


	public void setRefSituationDao(RefSituationDao refSituationDao) {
		this.refSituationDao = refSituationDao;
	}

	@Override
	public List<RefEquipementDto> findAll(Integer etabId) {
		List<RefEquipementDto> refEquipementListDto = new ArrayList<RefEquipementDto>();
		try {
			List<RefEquipement> refEquipementList = refEquipementDao.findAll(etabId);
			if (refEquipementList != null) {
				log.debug("equipement list success with size = {} ", new Object[]{refEquipementList.size()});
				for (RefEquipement currentRefEquipement : refEquipementList) {
					RefEquipementDto refEquipementDto = new RefEquipementDto();
					mapper.map(currentRefEquipement, refEquipementDto);
					refEquipementListDto.add(refEquipementDto);
				}
				return refEquipementListDto;

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}

	@Override
	public List<RefEquipementDto> findGeneric(Integer etabId, String value) {
		List<RefEquipementDto> refEquipementListDto = new ArrayList<RefEquipementDto>();
		try {
			List<RefEquipement> refEquipementList = refEquipementDao.findGeneric(etabId, value);
			if (refEquipementList != null) {
				log.debug("stucture list success with size =  {}", new Object[]{refEquipementList.size()});

				for (RefEquipement currentRefEquipement : refEquipementList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefEquipement.getId(),
									RefEquipement.class.getSimpleName());
					RefEquipementDto refEquipementDto = new RefEquipementDto();
					mapper.map(currentRefEquipement, refEquipementDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refEquipementDto);
					}
					refEquipementListDto.add(refEquipementDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refEquipementListDto;
	}

	@Override
	public List<RefEquipementDto> findAdvanced(Integer etabId,
			RefEquipementDto refEquipementDto) {
		List<RefEquipementDto> refEquipementListDto = new ArrayList<RefEquipementDto>();
		try {
			RefEquipement refEquipement = new RefEquipement();
			mapper.map(refEquipementDto, refEquipement);
			List<RefEquipement> refEquipementList = refEquipementDao
					.findAdvanced(etabId, refEquipement);
			if (refEquipementList != null && !refEquipementList.isEmpty()) {
				log.debug("equipement list success with size = {}  ", new Object[]{refEquipementList.size()});

				for (RefEquipement currentRefEquipement : refEquipementList) {
					RefEquipementDto currentRefEquipementDto = new RefEquipementDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefEquipement.getId(),
									RefEquipement.class.getSimpleName());
					mapper.map(currentRefEquipement, currentRefEquipementDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefEquipementDto);
					}
					refEquipementListDto.add(currentRefEquipementDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refEquipementListDto;
	}

	
	
	
}
