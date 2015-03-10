/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefModuleServiceImpl.java] 
 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:19:04
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefModuleDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefModule;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefModuleDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefModuleService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:19:04
 */
@Service("refModuleServiceImpl")
public class RefModuleServiceImpl implements RefModuleService {

	@Autowired
	@Qualifier("refModuleDaoImpl")
	private RefModuleDao refModuleDaoImpl;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	private static final Logger log = LoggerFactory.getLogger(RefModuleServiceImpl.class.getName());

	/**
	 * [RefModuleServiceImpl.RefModuleServiceImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:19:04
	 */
	public RefModuleServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefModuleService#insertOrUpdate
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefModuleDto)
	 */
	@Override
	public RefModuleDto insertOrUpdate(RefModuleDto refModuleDto) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefModuleService#remove
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefModuleDto)
	 */
	@Override
	public void remove(RefModuleDto refModuleDto) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefModuleService#findById
	 * (int)
	 */
	@Override
	public RefModuleDto findById(int id) {
		RefModuleDto refModuleDto = null;
		try {
			RefModule refModule = refModuleDaoImpl.findById(id);
			if (refModule != null) {
				refModuleDto = new RefModuleDto();
				log.debug("get module found successfully with id = {}", new Object[]{ id});
				mapper.map(refModule, refModuleDto);

			}
		} catch (RuntimeException e) {
			log.error("get module list failed", e);
			throw e;
		}
		return refModuleDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefModuleService#findByQuery
	 * (java.lang.String)
	 */
	@Override
	public List<RefModuleDto> findByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefModuleService#findAll()
	 */
	@Override
	public List<RefModuleDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RefModuleDto> findGeneric(String value) {
		List<RefModuleDto> refModuleDtoListDto = new ArrayList<RefModuleDto>();
		try {
			List<RefModule> refModuleList = refModuleDaoImpl.findGeneric(value);
			if (refModuleList != null) {
				log.debug("Module list success with size = {} ", new Object[]{refModuleList.size()});

				for (RefModule currentRefModule : refModuleList) {
					RefModuleDto refModuleDto = new RefModuleDto();
					mapper.map(currentRefModule, refModuleDto);
					refModuleDtoListDto.add(refModuleDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get domaine list failed", e);
			throw e;
		}
		return refModuleDtoListDto;
	}

	/**
	 * [RefModuleServiceImpl.refModuleDaoImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:26:26
	 * @return the refModuleDaoImpl
	 */
	public RefModuleDao getRefModuleDaoImpl() {
		return refModuleDaoImpl;
	}

	/**
	 * [RefModuleServiceImpl.refModuleDaoImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:26:26
	 * @param refModuleDaoImpl
	 *            the refModuleDaoImpl to set
	 */
	public void setRefModuleDaoImpl(RefModuleDao refModuleDaoImpl) {
		this.refModuleDaoImpl = refModuleDaoImpl;
	}

	/**
	 * [RefModuleServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:26:26
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefModuleServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:26:26
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public RefModuleDto findByIdentifiant(String identifiant) {
		RefModuleDto refModuleDto = null;
		try {
			RefModule refModule = refModuleDaoImpl
					.findByIdentifiant(identifiant);
			if (refModule != null) {
				refModuleDto = new RefModuleDto();
				log.debug("module found successfully with identifiant = {}", new Object[]{identifiant});
				mapper.map(refModule, refModuleDto);

			}
		} catch (RuntimeException e) {
			log.error("get module list failed", e);
			throw e;
		}
		return refModuleDto;
	}

	@Override
	public RefModuleDto findByName(Integer id, String name) {
		RefModuleDto refModuleDto = null;
		try {
			RefModule refModule = refModuleDaoImpl.findByName(id, name);
			if (refModule != null) {
				refModuleDto = new RefModuleDto();
				log.debug("RefModuleDto found successfully with name = {} " , new Object[]{name});
				mapper.map(refModule, refModuleDto);

			}
		} catch (RuntimeException e) {
			log.error("get RefModuleDt list failed", e);
			throw e;
		}
		return refModuleDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefModuleDto save(RefModuleDto refModuleDto) {
		RefModule refModule = new RefModule();
		try {
			mapper.map(refModuleDto, refModule);
			refModuleDaoImpl.persist(refModule);
			mapper.map(refModule, refModuleDto);
			log.debug("save refModuleDto successful");
		} catch (RuntimeException e) {
			log.error("save refModuleDto failed", e);
			throw e;
		}
		return refModuleDto;

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefModuleDto refModuleDto) {
		RefModule refModule = new RefModule();
		mapper.map(refModuleDto, refModule);
		try {
			refModuleDaoImpl.merge(refModule);
			log.debug("update refModule successful");
		} catch (RuntimeException e) {
			log.error("update refModule failed", e);
			throw e;
		}

	}

	@Override
	public int findModuleLastRang(int id) {
		try {
			int rang = refModuleDaoImpl.findModuleLastRang(id);
			log.info("get last rang of module success");
			return rang;
		} catch (RuntimeException e) {
			log.error("get last rang of module failed", e);
			throw e;
		}
	}

	@Override
	public List<RefModuleDto> findModules(int id) {
		List<RefModuleDto> refModuleDtoListDto = new ArrayList<RefModuleDto>();
		try {
			List<RefModule> refModuleList = refModuleDaoImpl.findModules(id);
			if (refModuleList != null) {
				log.debug("Module list success with size =  {}", new Object[]{refModuleList.size()});

				for (RefModule currentRefModule : refModuleList) {
					RefModuleDto refModuleDto = new RefModuleDto();
					mapper.map(currentRefModule, refModuleDto);
					refModuleDtoListDto.add(refModuleDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get domaine list failed", e);
			throw e;
		}
		return refModuleDtoListDto;
	}

	@Override
	public List<RefModuleDto> findModules() {
		List<RefModuleDto> refModuleDtoListDto = new ArrayList<RefModuleDto>();
		try {
			List<RefModule> refModuleList = refModuleDaoImpl.findModules();
			if (refModuleList != null) {
				log.debug("get module list success with size =  {}", new Object[]{refModuleList.size()});

				for (RefModule currentRefModule : refModuleList) {
					RefModuleDto refModuleDto = new RefModuleDto();
					mapper.map(currentRefModule, refModuleDto);
					refModuleDtoListDto.add(refModuleDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get module list failed", e);
			throw e;
		}
		return refModuleDtoListDto;
	}

	@Override
	public String generateIdentify(String prefix, int orderLength) {
		try {
			if (prefix == null) {
				return null;
			}

			Integer lastOrder = refModuleDaoImpl.findLastOrder(prefix,
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

	@Override
	public RefModuleDto findByNameArabe(Integer id, String name) {
		RefModuleDto refModuleDto = null;
		try {
			RefModule refModule = refModuleDaoImpl.findByNameArabe(id, name);
			if (refModule != null) {
				refModuleDto = new RefModuleDto();
				log.debug("RefModuleDto found successfully with name = {} ", new Object[]{ name});
				mapper.map(refModule, refModuleDto);

			}
		} catch (RuntimeException e) {
			log.error("get RefModuleDt list failed", e);
			throw e;
		}
		return refModuleDto;
	}

}
