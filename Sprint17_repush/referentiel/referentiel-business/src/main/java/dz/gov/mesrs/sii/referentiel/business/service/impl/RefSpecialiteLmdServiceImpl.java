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
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSpecialiteLmdDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSpecialiteLmd;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;

/**
 * @author BELBRIK Oualid on : 24 04. 2014 10:59:45
 */
@Service("refSpecialiteLmdServiceImpl")
public class RefSpecialiteLmdServiceImpl implements RefSpecialiteLmdService {

	/**
	 * [RefSpecialiteLmdServiceImpl.RefSpecialiteLmdServiceImpl()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 25 04. 2014 10:59:45
	 */
	private static final Logger log = LoggerFactory.getLogger(RefSpecialiteLmdServiceImpl.class.getName());
	
	@Autowired
	@Qualifier("refSpecialiteLmdDaoImpl")
	private RefSpecialiteLmdDao refSpecialiteLmdDao;
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
	 * [RefSpecialiteLmdServiceImpl.getRefParametrageServiceImpl] Method
	 * 
	 * @author BELBRIK Oualid on : 24 avr. 2014 11:41:07
	 * @return
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	/**
	 * [RefSpecialiteLmdServiceImpl.setRefParametrageServiceImpl] Method
	 * 
	 * @author BELBRIK Oualid on : 24 avr. 2014 11:41:11
	 * @param refParametrageServiceImpl
	 */
	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}

	public RefSpecialiteLmdServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<RefSpecialiteLmdDto> findGeneric(String value) {
		List<RefSpecialiteLmdDto> refSpecialiteLmdListDto = new ArrayList<RefSpecialiteLmdDto>();
		try {
			List<RefSpecialiteLmd> refSpecialiteLmdList = refSpecialiteLmdDao
					.findGeneric(value);
			if (refSpecialiteLmdList != null) {
				log.debug("stucture list success with size =  {}", new Object[]{refSpecialiteLmdList.size()});

				for (RefSpecialiteLmd currentRefSpecialiteLmd : refSpecialiteLmdList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefSpecialiteLmd.getId(),
									RefSpecialiteLmd.class.getSimpleName());
					RefSpecialiteLmdDto refSpecialiteLmdDto = new RefSpecialiteLmdDto();
					mapper.map(currentRefSpecialiteLmd, refSpecialiteLmdDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refSpecialiteLmdDto);
					}
					refSpecialiteLmdListDto.add(refSpecialiteLmdDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refSpecialiteLmdListDto;
	}

	public List<RefSpecialiteLmdDto> findAdvanced(
			RefSpecialiteLmdDto refSpecialiteLmdDto) {
		List<RefSpecialiteLmdDto> refSpecialiteLmdListDto = new ArrayList<RefSpecialiteLmdDto>();
		try {
			RefSpecialiteLmd refSpecialiteLmd = new RefSpecialiteLmd();
			mapper.map(refSpecialiteLmdDto, refSpecialiteLmd);
			List<RefSpecialiteLmd> refSpecialiteLmdList = refSpecialiteLmdDao
					.findAdvanced(refSpecialiteLmd);
			if (refSpecialiteLmdList != null && !refSpecialiteLmdList.isEmpty()) {
				log.debug("stucture list success with size =  {}", new Object[]{refSpecialiteLmdList.size()});


				for (RefSpecialiteLmd currentRefSpecialiteLmd : refSpecialiteLmdList) {
					RefSpecialiteLmdDto currentRefSpecialiteLmdDto = new RefSpecialiteLmdDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefSpecialiteLmd.getId(),
									RefSpecialiteLmd.class.getSimpleName());
					mapper.map(currentRefSpecialiteLmd,
							currentRefSpecialiteLmdDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation,
								currentRefSpecialiteLmdDto);
					}
					refSpecialiteLmdListDto.add(currentRefSpecialiteLmdDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refSpecialiteLmdListDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefSpecialiteLmdDto save(RefSpecialiteLmdDto refSpecialiteLmdDto) {
		log.info("service saveSpecialite");
		RefSpecialiteLmd refSpecialiteLmd = new RefSpecialiteLmd();
		mapper.map(refSpecialiteLmdDto, refSpecialiteLmd);
		try {
			refSpecialiteLmdDao.persist(refSpecialiteLmd);
			RefSituation refSituation = new RefSituation();
			refSituation.setDateSituation(new Date());
			refSituation.setIdEntite(refSpecialiteLmd.getId());
			refSituation.setNomEntite(RefSpecialiteLmd.class.getSimpleName());
			RefEntiteSituation refEntiteSituation = new RefEntiteSituation();
			refEntiteSituation.setId(39/*Integer.parseInt(refParametrageServiceImpl
					.getParamConfiguration(
							UtilConstant.SPECIALITELMD_SITUATION_CREE_ID_KEY,
							UtilConstant.CONFIGURATION_UTIL).getValue())*/);
			refSituation.setRefEntiteSituation(refEntiteSituation);
			refSituationDao.persist(refSituation);
			mapper.map(refSpecialiteLmd, refSpecialiteLmdDto);
			log.debug("save successful");
			return refSpecialiteLmdDto;
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefSpecialiteLmdDto refSpecialiteLmdDto) {
		RefSpecialiteLmd refSpecialiteLmd = new RefSpecialiteLmd();
		mapper.map(refSpecialiteLmdDto, refSpecialiteLmd);
		try {

			refSpecialiteLmdDao.merge(refSpecialiteLmd);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	@Override
	public void saveOrUpdate(RefSpecialiteLmdDto refSpecialiteLmdDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public RefSpecialiteLmdDto findById(int id) {
		try {
			RefSpecialiteLmd refSpecialiteLmd = refSpecialiteLmdDao
					.findById(id);
			if (refSpecialiteLmd != null) {
				log.debug(" refSpecialiteLmdDao.findById(id) success with id = {} ", new Object[]{refSpecialiteLmd.getIdentifiant()});
				RefSpecialiteLmdDto refSpecialiteLmdDto = new RefSpecialiteLmdDto();
				mapper.map(refSpecialiteLmd, refSpecialiteLmdDto);
				return refSpecialiteLmdDto;
			}

		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}

	@Override
	public RefSpecialiteLmdDto findByIdentifiant(String identifiant) {
		try {
			RefSpecialiteLmd refSpecialiteLmd = refSpecialiteLmdDao
					.findByIdentifiant(identifiant);
			
			if (refSpecialiteLmd != null) {
				log.debug(" findByIdentifiant success with id = {} ", new Object[]{refSpecialiteLmd.getIdentifiant()});
				
				RefSpecialiteLmdDto refSpecialiteLmdDto = mapper.map(
						refSpecialiteLmd, RefSpecialiteLmdDto.class);
			
				return refSpecialiteLmdDto;
			}

		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefSpecialiteLmdDto refSpecialiteLmdDto) {
		RefSpecialiteLmd refSpecialiteLmd = new RefSpecialiteLmd();
		try {
			mapper.map(refSpecialiteLmdDto, refSpecialiteLmd);
			refSpecialiteLmd = refSpecialiteLmdDao.merge(refSpecialiteLmd);
			refSpecialiteLmdDao.remove(refSpecialiteLmd);
			log.debug("remove SpecialiteLmd successful");
		} catch (RuntimeException e) {
			log.error("remove failed", e);
			throw e;
		}

	}

	/**
	 * [RefSpecialiteLmdServiceImpl.refSpecialiteLmdDao] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @return the refSpecialiteLmdDao
	 */
	public RefSpecialiteLmdDao getRefSpecialiteLmdDao() {
		return refSpecialiteLmdDao;
	}

	/**
	 * [RefSpecialiteLmdServiceImpl.refSpecialiteLmdDao] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @param refSpecialiteLmdDao
	 *            the refSpecialiteLmdDao to set
	 */
	public void setRefSpecialiteLmdDao(RefSpecialiteLmdDao refSpecialiteLmdDao) {
		this.refSpecialiteLmdDao = refSpecialiteLmdDao;
	}

	/**
	 * [RefSpecialiteLmdServiceImpl.mapper] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefSpecialiteLmdServiceImpl.mapper] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<RefSpecialiteLmdDto> findAll() {
		List<RefSpecialiteLmdDto> refSpecialiteLmdListDto = new ArrayList<RefSpecialiteLmdDto>();
		try {
			List<RefSpecialiteLmd> refSpecialiteLmdList = refSpecialiteLmdDao
					.findAll();
			if (refSpecialiteLmdList != null) {
				log.debug("Specialite list success with size = {} ", new Object[]{refSpecialiteLmdList.size()});
				for (RefSpecialiteLmd currentRefSpecialiteLmd : refSpecialiteLmdList) {
					RefSpecialiteLmdDto refSpecialiteLmdDto = new RefSpecialiteLmdDto();
					mapper.map(currentRefSpecialiteLmd, refSpecialiteLmdDto);
					refSpecialiteLmdListDto.add(refSpecialiteLmdDto);
				}
				return refSpecialiteLmdListDto;

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
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RefSpecialiteLmdDto> findByCodeFilierelmd(String value) {

		List<RefSpecialiteLmdDto> refSpecialiteLmdListDto = new ArrayList<RefSpecialiteLmdDto>();
		try {
			List<RefSpecialiteLmd> refSpecialiteLmdList = (List<RefSpecialiteLmd>) refSpecialiteLmdDao
					.findByCodeFilierelmd(value);
			if (refSpecialiteLmdList != null) {
				log.debug("SpecialiteLmd list success with size =  {}", new Object[]{refSpecialiteLmdList.size()});
				for (RefSpecialiteLmd currentRefSpecialiteLmd : refSpecialiteLmdList) {
					RefSpecialiteLmdDto refSpecialiteLmdDto = new RefSpecialiteLmdDto();
					mapper.map(currentRefSpecialiteLmd, refSpecialiteLmdDto);
					refSpecialiteLmdListDto.add(refSpecialiteLmdDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refSpecialiteLmdListDto;

	}

	@Override
	public List<RefSpecialiteLmdDto> findByIdFilierelmd(Integer value) {
		List<RefSpecialiteLmdDto> refSpecialiteLmdListDto = new ArrayList<RefSpecialiteLmdDto>();
		try {
			List<RefSpecialiteLmd> refSpecialiteLmdList = (List<RefSpecialiteLmd>) refSpecialiteLmdDao
					.findByIdFilierelmd(value);
			if (refSpecialiteLmdList != null) {
				log.debug("SpecialiteLmd list success with size =  {}", new Object[]{refSpecialiteLmdList.size()});
				for (RefSpecialiteLmd currentRefSpecialiteLmd : refSpecialiteLmdList) {
					RefSpecialiteLmdDto refSpecialiteLmdDto = new RefSpecialiteLmdDto();
					mapper.map(currentRefSpecialiteLmd, refSpecialiteLmdDto);
					refSpecialiteLmdListDto.add(refSpecialiteLmdDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refSpecialiteLmdListDto;
	}

}
