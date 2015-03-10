/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefConfigurationServiceImpl.java] 
 * @author BELDI Jamel on : 19 mars 2014  18:43:16
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefConfigurationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefConfiguration;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefConfigurationDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefConfigurationService;

/**
 * @author BELDI Jamel  on : 19 mars 2014  18:43:16
 */
@Service ("refConfigurationServiceImpl")
public class RefConfigurationServiceImpl implements RefConfigurationService {
	private static final Logger log = LoggerFactory.getLogger(RefConfigurationServiceImpl.class.getName());
	@Autowired
	@Qualifier("refConfigurationDaoImpl")
	private RefConfigurationDao refConfigurationDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	/**
	 * [RefConfigurationServiceImpl.RefConfigurationServiceImpl()] Constructor
	 * @author BELDI Jamel  on : 19 mars 2014  18:43:16	
	 */
	public RefConfigurationServiceImpl() {
		
	}

	
	@Override
	public List<RefConfigurationDto> findAll() {
		List<RefConfigurationDto> refConfigurationListDto = new ArrayList<RefConfigurationDto>();
		try {
			List<RefConfiguration> refConfigurationList = refConfigurationDao.findAll();
			if (refConfigurationList != null) {
				log.debug("entity list success with size {}  ", new Object[]{ refConfigurationList.size()});
				for (RefConfiguration currentRefConfiguration : refConfigurationList) {
					RefConfigurationDto refConfigurationDto = new RefConfigurationDto();
					mapper.map(currentRefConfiguration, refConfigurationDto);
					refConfigurationListDto.add(refConfigurationDto);
				}
				return refConfigurationListDto;
			}
		} catch (RuntimeException e) {
			log.error("get entity list failed", e);
			throw e;
		}
		return null;
	}

	
	@Override
	public RefConfigurationDto findById(Integer id) {
		RefConfigurationDto refConfigurationDto = null;
		try{
			RefConfiguration refConfiguration = refConfigurationDao.findById(id);
			if(refConfiguration != null){
				refConfigurationDto = new RefConfigurationDto();
 				 mapper.map(refConfiguration, refConfigurationDto);
				log.info("success get dto: "+refConfigurationDto.getName());
				return refConfigurationDto;
			}
		}catch(Exception e){
			log.error("get failed", e);
			
		}
		return refConfigurationDto;
	}

	
	@Override
	public List<RefConfigurationDto> findByDomain(Integer idDomain) {
		List<RefConfigurationDto> refConfigurationListDto = new ArrayList<RefConfigurationDto>();
		try {
			List<RefConfiguration> refConfigurationList = refConfigurationDao.findByDomain(idDomain);
			if (refConfigurationList != null) {
				log.debug("entity list success with size {}  ", new Object[]{ refConfigurationList.size()});
				for (RefConfiguration currentRefConfiguration : refConfigurationList) {
					RefConfigurationDto refConfigurationDto = new RefConfigurationDto();
					mapper.map(currentRefConfiguration, refConfigurationDto);
					refConfigurationListDto.add(refConfigurationDto);
				}
				return refConfigurationListDto;
			}
		} catch (RuntimeException e) {
			log.error("get entity list failed", e);
			throw e;
		}
		return refConfigurationListDto;
	}

	/**
	 * [RefConfigurationServiceImpl.refConfigurationDao] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  18:47:22
	 * @return the refConfigurationDao
	 */
	public RefConfigurationDao getRefConfigurationDao() {
		return refConfigurationDao;
	}

	/**
	 * [RefConfigurationServiceImpl.refConfigurationDao] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  18:47:22
	 * @param refConfigurationDao the refConfigurationDao to set
	 */
	public void setRefConfigurationDao(RefConfigurationDao refConfigurationDao) {
		this.refConfigurationDao = refConfigurationDao;
	}

	/**
	 * [RefConfigurationServiceImpl.mapper] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  18:47:22
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefConfigurationServiceImpl.mapper] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  18:47:22
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	
	
}
