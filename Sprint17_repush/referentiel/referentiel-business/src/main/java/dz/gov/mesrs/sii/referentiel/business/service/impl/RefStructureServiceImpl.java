/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefStructureServiceImpl.java] 
 * @author MAKERRI Sofiane on : 5 janv. 2014  10:59:45
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
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefStructureDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 5 janv. 2014 10:59:45
 */
@Service("refStructureServiceImpl")
public class RefStructureServiceImpl implements RefStructureService {

	/**
	 * [RefStructureServiceImpl.RefStructureServiceImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 10:59:45
	 */
	private static final Logger log = LoggerFactory.getLogger(RefStructureServiceImpl.class.getName());
	@Autowired
	@Qualifier("refStructureDaoImpl")
	private RefStructureDao refStructureDao;
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
	 * 
	 * @author BELDI Jamel on : 7 avr. 2014 14:59:45
	 * @return the refParametrageServiceImpl
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	/**
	 * [RefEvenementServiceImpl.refParametrageServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 7 avr. 2014 14:59:45
	 * @param refParametrageServiceImpl
	 *            the refParametrageServiceImpl to set
	 */
	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}

	public RefStructureServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<RefStructureDto> findGeneric(String value) {
		List<RefStructureDto> refStructureListDto = new ArrayList<RefStructureDto>();
		try {
			List<RefStructure> refStructureList = refStructureDao
					.findGeneric(value);
			if (refStructureList != null) {
				log.debug("stucture list success with size =  {}", new Object[]{refStructureList.size()});

				for (RefStructure currentRefStructure : refStructureList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefStructure.getId(),
									RefStructure.class.getSimpleName());
					RefStructureDto refStructureDto = new RefStructureDto();
					mapper.map(currentRefStructure, refStructureDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refStructureDto);
					}
					refStructureListDto.add(refStructureDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refStructureListDto;
	}

	public List<RefStructureDto> findAdvanced(RefStructureDto refStructureDto) {
		List<RefStructureDto> refStructureListDto = new ArrayList<RefStructureDto>();
		try {
			RefStructure refStructure = new RefStructure();
			mapper.map(refStructureDto, refStructure);
			List<RefStructure> refStructureList = refStructureDao
					.findAdvanced(refStructure);
			if (refStructureList != null && !refStructureList.isEmpty()) {
				log.debug("stucture list success with size =  {}", new Object[]{refStructureList.size()});

				for (RefStructure currentRefStructure : refStructureList) {
					RefStructureDto currentRefStructureDto = new RefStructureDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefStructure.getId(),
									RefStructure.class.getSimpleName());
					mapper.map(currentRefStructure, currentRefStructureDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefStructureDto);
					}
					refStructureListDto.add(currentRefStructureDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refStructureListDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefStructureDto save(RefStructureDto refStructureDto) {
		log.info("service saveStructure");
		RefStructure refStructure = new RefStructure();
		mapper.map(refStructureDto, refStructure);
		try {
			refStructureDao.persist(refStructure);
			RefSituation refSituation = new RefSituation();
			refSituation.setDateSituation(new Date());
			refSituation.setIdEntite(refStructure.getId());
			refSituation.setNomEntite(RefStructure.class.getSimpleName());
			RefEntiteSituation refEntiteSituation = new RefEntiteSituation();
			refEntiteSituation.setId(4/*Integer.parseInt(refParametrageServiceImpl
					.getParamConfiguration(
							UtilConstant.STRUCTURE_SITUATION_CREE_ID_KEY,
							UtilConstant.CONFIGURATION_UTIL).getValue())*/);
			refSituation.setRefEntiteSituation(refEntiteSituation);
			refSituationDao.persist(refSituation);
			mapper.map(refStructure, refStructureDto);
			log.debug("save successful");
			return refStructureDto;
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefStructureDto refStructureDto) {
		RefStructure refStructure = new RefStructure();
		mapper.map(refStructureDto, refStructure);
		try {
			if (refStructure.getRefStructure() != null
					&& refStructureDto.getIdMotherStructure() == null) {
				refStructure.setRefStructure(null);
			}
			refStructureDao.merge(refStructure);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	@Override
	public void saveOrUpdate(RefStructureDto refStructureDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public RefStructureDto findById(Integer id) {
		try {
			RefStructure refStructure = refStructureDao.findById(id);
			if (refStructure != null) {
				log.debug(" refStructureDao.findById(id) success with id =  {}", new Object[]{refStructure.getIdentifiant()});
				RefStructureDto refStructureDto = new RefStructureDto();
				mapper.map(refStructure, refStructureDto);
				return refStructureDto;
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
	 * [RefStructureServiceImpl.refStructureDao] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:01:53
	 * @return the refStructureDao
	 */
	public RefStructureDao getRefStructureDao() {
		return refStructureDao;
	}

	/**
	 * [RefStructureServiceImpl.refStructureDao] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:01:53
	 * @param refStructureDao
	 *            the refStructureDao to set
	 */
	public void setRefStructureDao(RefStructureDao refStructureDao) {
		this.refStructureDao = refStructureDao;
	}

	/**
	 * [RefStructureServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:01:53
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefStructureServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:01:53
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<RefStructureDto> findAll() {
		List<RefStructureDto> refStructureListDto = new ArrayList<RefStructureDto>();
		try {
			List<RefStructure> refStructureList = refStructureDao.findAll();
			if (refStructureList != null) {
				log.debug("structure list success with size = {}  ", new Object[]{refStructureList.size()});
				for (RefStructure currentRefStructure : refStructureList) {
					RefStructureDto refStructureDto = new RefStructureDto();
					mapper.map(currentRefStructure, refStructureDto);
					refStructureListDto.add(refStructureDto);
				}
				return refStructureListDto;

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;

	}

	/**
	 * [RefStructureServiceImpl.refSituationDao] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:50:08
	 * @return the refSituationDao
	 */
	public RefSituationDao getRefSituationDao() {
		return refSituationDao;
	}

	/**
	 * [RefStructureServiceImpl.refSituationDao] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:50:08
	 * @param refSituationDao
	 *            the refSituationDao to set
	 */
	public void setRefSituationDao(RefSituationDao refSituationDao) {
		this.refSituationDao = refSituationDao;
	}

	@Override
	public List<RefStructureDto> findAll(Integer etabId) {
		List<RefStructureDto> refStructureListDto = new ArrayList<RefStructureDto>();
		try {
			List<RefStructure> refStructureList = refStructureDao
					.findAll(etabId);
			if (refStructureList != null) {
				log.debug("structure list success with size = {}  ", new Object[]{refStructureList.size()});
				for (RefStructure currentRefStructure : refStructureList) {
					RefStructureDto refStructureDto = new RefStructureDto();
					mapper.map(currentRefStructure, refStructureDto);
					refStructureListDto.add(refStructureDto);
				}
				return refStructureListDto;

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;

	}

	@Override
	public List<RefStructureDto> findGeneric(Integer etabId, String value) {
		List<RefStructureDto> refStructureListDto = new ArrayList<RefStructureDto>();
		try {
			List<RefStructure> refStructureList = refStructureDao.findGeneric(
					etabId, value);
			if (refStructureList != null) {
				log.debug("structure list success with size = {}  ", new Object[]{refStructureList.size()});

				for (RefStructure currentRefStructure : refStructureList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefStructure.getId(),
									RefStructure.class.getSimpleName());
					RefStructureDto refStructureDto = new RefStructureDto();
					mapper.map(currentRefStructure, refStructureDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refStructureDto);
					}
					refStructureListDto.add(refStructureDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refStructureListDto;
	}

	@Override
	public List<RefStructureDto> findAdvanced(Integer etabId,
			RefStructureDto refStructureDto) {
		List<RefStructureDto> refStructureListDto = new ArrayList<RefStructureDto>();
		try {
			RefStructure refStructure = new RefStructure();
			mapper.map(refStructureDto, refStructure);
			List<RefStructure> refStructureList = refStructureDao.findAdvanced(
					etabId, refStructure);
			if (refStructureList != null && !refStructureList.isEmpty()) {
				log.debug("structure list success with size = {}  ", new Object[]{refStructureList.size()});

				for (RefStructure currentRefStructure : refStructureList) {
					RefStructureDto currentRefStructureDto = new RefStructureDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefStructure.getId(),
									RefStructure.class.getSimpleName());
					mapper.map(currentRefStructure, currentRefStructureDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefStructureDto);
					}
					refStructureListDto.add(currentRefStructureDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refStructureListDto;
	}

	@Override
	public String generateIdentify(String prefix, int orderLength) {
		try {
			if (prefix == null) {
				return null;
			}

			Integer lastOrder = refStructureDao.findLastOrder(prefix,
					orderLength);
			if (lastOrder == null) {
				return null;
			}
			lastOrder++;
			prefix = prefix
					+ RefUtilConstant.formatOrder(lastOrder.toString(),
							orderLength);
			log.debug("calculateIdentify successful");
		} catch (RuntimeException e) {
			log.error("calculateIdentify failed", e);
			throw e;
		}
		return prefix;	
	}

	/**
	 * [RefStructureServiceImpl.findStrcuturesByTypeByEtab] Method 
	 * @author rlaib  on : 11 oct. 2014  10:46:36
	 * @param etabId
	 * @param typeStructureId
	 * @return
	 */
	@Override
	public List<RefStructureDto> findStructuresByTypeByEtab(Integer etabId, int typeStructureId) {
		
		List<RefStructureDto> refStructureListDto = new ArrayList<RefStructureDto>();
		try {
			List<RefStructure> refStructureList = refStructureDao
					.findStructuresByTypeByEtab(etabId, typeStructureId);
			if (refStructureList != null) {
				log.debug("findStrcuturesByTypeByEtab : structure list success with size =  {}", new Object[]{refStructureList.size()});
				for (RefStructure currentRefStructure : refStructureList) {
					RefStructureDto refStructureDto = new RefStructureDto();
					mapper.map(currentRefStructure, refStructureDto);
					refStructureListDto.add(refStructureDto);
				}
				return refStructureListDto;

			}
		} catch (RuntimeException re) {
			log.error("findStrcuturesByTypeByEtab List<RefStructureDto>  failed", re);
			throw re;
		}
		return null;
	}

	
	/**
	 * [RefStructureService.findStrcuturesByTypeByEtab] Method 
	 * @author Rafik  on : 5 déc. 2014  21:59:02
	 * @param etabId
	 * @param masterStructureId
	 * @return
	 */
	@Override
	public List<RefStructureDto> findStructuresOfMasterStructureByEtab(Integer etabId,
			Integer masterStructureId) {
		
		if (etabId == null || masterStructureId == null) {
			return null;
		}
		List<RefStructure> _refStructuresList = refStructureDao.findStructuresOfMasterStructureByEtab(etabId,masterStructureId);
		if(_refStructuresList == null)
			return null;
		
		List<RefStructureDto> refStructuresListDto = new ArrayList<RefStructureDto>();
		if(_refStructuresList != null && _refStructuresList.size()>0) {
			
			for (RefStructure _refStructure : _refStructuresList) {
				RefStructureDto refStructureDto = new RefStructureDto();
				refStructureDto = mapper.map(_refStructure, RefStructureDto.class);
				refStructuresListDto.add(refStructureDto);
				}
				return refStructuresListDto;
		}
		return null;
	}
}
