/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefLieuServiceImpl.java] 
 * @author BELBRIK Oualid on : 11 02. 2014  10:59:45
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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefLieuDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefLieu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefLieuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefLieuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;

/**
 * @author BELBRIK Oualid on : 11 02. 2014 10:59:45
 */
@Service("refLieuServiceImpl")
public class RefLieuServiceImpl implements RefLieuService {

	/**
	 * [RefLieuServiceImpl.RefLieuServiceImpl()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 10:59:45
	 */
	private static final Logger log = LoggerFactory.getLogger(RefLieuServiceImpl.class.getName());
	@Autowired
	@Qualifier("refLieuDaoImpl")
	private RefLieuDao refLieuDao;
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


	public RefLieuServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public List<RefLieuDto> findGeneric(String value) {
		List<RefLieuDto> refLieuListDto = new ArrayList<RefLieuDto>();
		try {
			List<RefLieu> refLieuList = refLieuDao.findGeneric(value);
			if (refLieuList != null) {
				log.debug("stucture list success with size = {} ", new Object[]{refLieuList.size()});

				for (RefLieu currentRefLieu : refLieuList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefLieu.getId(),
									RefLieu.class.getSimpleName());
					RefLieuDto refLieuDto = new RefLieuDto();
					mapper.map(currentRefLieu, refLieuDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refLieuDto);
					}
					refLieuListDto.add(refLieuDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refLieuListDto;
	}

	
	public List<RefLieuDto> findAdvanced(RefLieuDto refLieuDto) {
		List<RefLieuDto> refLieuListDto = new ArrayList<RefLieuDto>();
		try {
			RefLieu refLieu = new RefLieu();
			mapper.map(refLieuDto, refLieu);
			List<RefLieu> refLieuList = refLieuDao
					.findAdvanced(refLieu);
			if (refLieuList != null && !refLieuList.isEmpty()) {
				log.debug("lieu list success with size =   {}", new Object[]{refLieuList.size()});

				for (RefLieu currentRefLieu : refLieuList) {
					RefLieuDto currentRefLieuDto = new RefLieuDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefLieu.getId(),
									RefLieu.class.getSimpleName());
					mapper.map(currentRefLieu, currentRefLieuDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefLieuDto);
					}
					refLieuListDto.add(currentRefLieuDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refLieuListDto;
	}

	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefLieuDto save(RefLieuDto refLieuDto) {
		log.info("service saveLieu");
		RefLieu refLieu = new RefLieu();
		mapper.map(refLieuDto, refLieu);
		try {
			refLieuDao.persist(refLieu);			
			RefSituation refSituation = new RefSituation();
			refSituation.setDateSituation(new Date());
			refSituation.setIdEntite(refLieu.getId());
			refSituation.setNomEntite(RefLieu.class.getSimpleName());
			RefEntiteSituation refEntiteSituation = new RefEntiteSituation();
			refEntiteSituation.setId(18/*Integer.parseInt(refParametrageServiceImpl.getParamConfiguration(UtilConstant.LIEU_SITUATION_CREE_ID_KEY, UtilConstant.CONFIGURATION_UTIL).getValue())*/);
			refSituation.setRefEntiteSituation(refEntiteSituation);
			refSituationDao.persist(refSituation);
			mapper.map(refLieu, refLieuDto);
			log.debug("save successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
		return refLieuDto;

	}

	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefLieuDto refLieuDto) {
		RefLieu refLieu = new RefLieu();
		mapper.map(refLieuDto, refLieu);
		try {
			if(refLieu.getRefLieu()!=null && refLieu.getRefLieu().getIdentifiant()==null){
				refLieu.setRefLieu(null);
			}
			refLieuDao.merge(refLieu);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	
	@Override
	public void saveOrUpdate(RefLieuDto refLieuDto) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public RefLieuDto findById(Integer id) {
		try {
			RefLieu refLieu = refLieuDao.findById(id);
			if (refLieu != null) {
				log.debug(" refLieuDao.findById(id) success with id =  "
						+ refLieu.getIdentifiant());
				RefLieuDto refLieuDto = new RefLieuDto();
				mapper.map(refLieu, refLieuDto);
				return refLieuDto;
			}

		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}

	
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	/**
	 * [RefLieuServiceImpl.refLieuDao] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @return the refLieuDao
	 */
	public RefLieuDao getRefLieuDao() {
		return refLieuDao;
	}

	/**
	 * [RefLieuServiceImpl.refLieuDao] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @param refLieuDao
	 *            the refLieuDao to set
	 */
	public void setRefLieuDao(RefLieuDao refLieuDao) {
		this.refLieuDao = refLieuDao;
	}

	/**
	 * [RefLieuServiceImpl.mapper] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefLieuServiceImpl.mapper] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	
	@Override
	public List<RefLieuDto> findAll() {
		List<RefLieuDto> refLieuListDto = new ArrayList<RefLieuDto>();
		try {
			List<RefLieu> refLieuList = refLieuDao.findAll();
			if (refLieuList != null) {
				log.debug("lieu list success with size =   {}", new Object[]{refLieuList.size()});
				for (RefLieu currentRefLieu : refLieuList) {
					RefLieuDto refLieuDto = new RefLieuDto();
					mapper.map(currentRefLieu, refLieuDto);
					refLieuListDto.add(refLieuDto);
				}
				return refLieuListDto;

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
	public List<RefLieuDto> findAll(Integer etabId) {
		List<RefLieuDto> refLieuListDto = new ArrayList<RefLieuDto>();
		try {
			List<RefLieu> refLieuList = refLieuDao.findAll(etabId);
			if (refLieuList != null) {
				log.debug("Lieu list success with size =  "
						+ refLieuList.size());
				for (RefLieu currentRefLieu : refLieuList) {
					RefLieuDto refLieuDto = new RefLieuDto();
					mapper.map(currentRefLieu, refLieuDto);
					refLieuListDto.add(refLieuDto);
				}
				return refLieuListDto;

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}

	@Override
	public List<RefLieuDto> findGeneric(Integer etabId, String value) {
		List<RefLieuDto> refLieuListDto = new ArrayList<RefLieuDto>();
		try {
			List<RefLieu> refLieuList = refLieuDao.findGeneric(etabId, value);
			if (refLieuList != null) {
				log.debug("stucture list success with size =  "
						+ refLieuList.size());

				for (RefLieu currentRefLieu : refLieuList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefLieu.getId(),
									RefLieu.class.getSimpleName());
					RefLieuDto refLieuDto = new RefLieuDto();
					mapper.map(currentRefLieu, refLieuDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refLieuDto);
					}
					refLieuListDto.add(refLieuDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refLieuListDto;
	}

	@Override
	public List<RefLieuDto> findAdvanced(Integer etabId,
			RefLieuDto refLieuDto) {
		List<RefLieuDto> refLieuListDto = new ArrayList<RefLieuDto>();
		try {
			RefLieu refLieu = new RefLieu();
			mapper.map(refLieuDto, refLieu);
			List<RefLieu> refLieuList = refLieuDao
					.findAdvanced(etabId, refLieu);
			if (refLieuList != null && !refLieuList.isEmpty()) {
				log.debug("lieu list success with size =  "
						+ refLieuList.size());

				for (RefLieu currentRefLieu : refLieuList) {
					RefLieuDto currentRefLieuDto = new RefLieuDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefLieu.getId(),
									RefLieu.class.getSimpleName());
					mapper.map(currentRefLieu, currentRefLieuDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefLieuDto);
					}
					refLieuListDto.add(currentRefLieuDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refLieuListDto;
	}

	@Override
	public RefLieuDto findByCode(String code) {
		
		try {
			if (code==null){
				return null;
			}
			RefLieu refLieu = refLieuDao.findByCode(code);
			
			if (refLieu != null) {
				log.debug(" refLieuDao.findById(id) success with id =  "
						+ refLieu.getIdentifiant());
				RefLieuDto refLieuDto = new RefLieuDto();
				mapper.map(refLieu, refLieuDto);
				return refLieuDto;
			}

		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}

	
	
	
}
