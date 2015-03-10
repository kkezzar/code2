/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefParametreConfigurationServiceImpl.java] 
 * @author MAKERRI Sofiane on : 22 avr. 2014  12:47:35
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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefParametreDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefParametre;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParametreDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreService;

/**
 * @author MAKERRI Sofiane on : 22 avr. 2014 12:47:35
 */
@Service("refParametreServiceImpl")
public class RefParametreServiceImpl implements
		RefParametreService {

	private static final Logger log = LoggerFactory.getLogger(RefParametreServiceImpl.class.getName());
	@Autowired
	@Qualifier("refParametreDaoImpl")
	private RefParametreDao refParametreDaoImpl;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;

	/**
	 * [RefParametreConfigurationServiceImpl.
	 * RefParametreConfigurationServiceImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 12:47:35
	 */
	public RefParametreServiceImpl() {
		super();
	}

	@Override
	public List<RefParametreDto> findGeneric(String value) {
		List<RefParametreDto> refParametreConfigurationDtos = new ArrayList<RefParametreDto>();
		try {
			List<RefParametre> refParametreConfigurationList = refParametreDaoImpl
					.findGeneric(value);
			if (refParametreConfigurationList != null) {
				log.debug("configuration parameters  list found success with size =  {}", new Object[]{
						refParametreConfigurationList.size()});

				for (RefParametre current : refParametreConfigurationList) {
					RefParametreDto refParametreConfigurationDto = new RefParametreDto();
					mapper.map(current, refParametreConfigurationDto);
					refParametreConfigurationDtos
							.add(refParametreConfigurationDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refParametreConfigurationDtos;
	}

	
	/**
	 * [RefParametreServiceImpl.refParametreDaoImpl] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  09:24:24
	 * @return the refParametreDaoImpl
	 */
	public RefParametreDao getRefParametreDaoImpl() {
		return refParametreDaoImpl;
	}

	/**
	 * [RefParametreServiceImpl.refParametreDaoImpl] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  09:24:24
	 * @param refParametreDaoImpl the refParametreDaoImpl to set
	 */
	public void setRefParametreDaoImpl(RefParametreDao refParametreDaoImpl) {
		this.refParametreDaoImpl = refParametreDaoImpl;
	}

	/**
	 * [RefParametreConfigurationServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 13:04:10
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefParametreConfigurationServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 13:04:10
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public RefParametreDto findById(Integer id) {
		RefParametreDto refParametreConfigurationDto = null;
		try {
			RefParametre refParametreConfiguration = refParametreDaoImpl
					.findById(id);
			if (refParametreConfiguration != null) {
				refParametreConfigurationDto = new RefParametreDto();
				mapper.map(refParametreConfiguration,
						refParametreConfigurationDto);
				log.info("success findById RefParametreConfigurationDto ");
				return refParametreConfigurationDto;
			}
		} catch (Exception e) {
			log.error("findById failed", e);

		}
		return refParametreConfigurationDto;
	}

	@Override
	public RefParametreDto findByKey(Integer id, String key) {
		RefParametreDto refParametreConfigurationDto = null;
		try {
			RefParametre refParametreConfiguration = refParametreDaoImpl
					.findByKey(id, key);
			if (refParametreConfiguration != null) {
				refParametreConfigurationDto = new RefParametreDto();
				mapper.map(refParametreConfiguration,
						refParametreConfigurationDto);
				log.info("success findByKey RefParametreConfigurationDto ");
				return refParametreConfigurationDto;
			}
		} catch (Exception e) {
			log.error("findByKey failed", e);

		}
		return refParametreConfigurationDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefParametreDto save(
			RefParametreDto refParametreConfigurationDto) {
		RefParametre refParametreConfiguration = new RefParametre();
		mapper.map(refParametreConfigurationDto, refParametreConfiguration);
		try {
			refParametreDaoImpl.persist(refParametreConfiguration);
			log.debug("save successful");
			mapper.map(refParametreConfiguration, refParametreConfigurationDto);

		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
		return refParametreConfigurationDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefParametreDto refParametreConfigurationDto) {
		RefParametre refParametreConfiguration = new RefParametre();
		mapper.map(refParametreConfigurationDto, refParametreConfiguration);
		try {
			refParametreDaoImpl.merge(refParametreConfiguration);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefParametreDto refParametreConfigurationDto) {
		try {
			RefParametre refParametreConfiguration = mapper.map(
					refParametreConfigurationDto,
					RefParametre.class);
			refParametreConfiguration = refParametreDaoImpl
					.merge(refParametreConfiguration);
			refParametreDaoImpl.remove(refParametreConfiguration);
			log.debug("remove refParametreConfigurationDto successful");
		} catch (RuntimeException e) {
			log.error("remove refParametreConfigurationDto failed", e);
			throw e;
		}

	}

	@Override
	public List<RefParametreDto> findAllNotGeneric() {
		List<RefParametreDto> refParametreDtos = new ArrayList<RefParametreDto>();
		try {
			List<RefParametre> refParametreList = refParametreDaoImpl
					.findAllNotGeneric();
			if (refParametreList != null) {
				log.debug("configuration parameters  list found success with size =  {}", new Object[]{
						refParametreList.size()});

				for (RefParametre current : refParametreList) {
					RefParametreDto refParametreDto = new RefParametreDto();
					mapper.map(current, refParametreDto);
					refParametreDtos
							.add(refParametreDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refParametreDtos;
	}

	/*
	@Override
	public String findValueOfKey(String key) {
		try {
			String value = refParametreDaoImpl
					.findValueOfKey(key);
			return value;
		} catch (Exception e) {
			log.error("findByKey failed", e);

		}
		return null;
	}

	@Override
	public RefParametreDto findByKey(Integer id, String idfEtab, String key) {
		RefParametreDto refParametreConfigurationDto = null;
		try {
			RefParametre refParametreConfiguration = refParametreDaoImpl
					.findByKey(id, idfEtab, key);
			if (refParametreConfiguration != null) {
				refParametreConfigurationDto = new RefParametreDto();
				mapper.map(refParametreConfiguration,
						refParametreConfigurationDto);
				log.info("success findByKey RefParametreConfigurationDto ");
				return refParametreConfigurationDto;
			}
		} catch (Exception e) {
			log.error("findByKey failed", e);

		}
		return refParametreConfigurationDto;
	}

	@Override
	public String findValueOfKey(String idfEtab, String key) {
		try {
			String value = refParametreDaoImpl
					.findValueOfKey(idfEtab, key);
			return value;
		} catch (Exception e) {
			log.error("findByKey failed", e);

		}
		return null;
	}*/

}
