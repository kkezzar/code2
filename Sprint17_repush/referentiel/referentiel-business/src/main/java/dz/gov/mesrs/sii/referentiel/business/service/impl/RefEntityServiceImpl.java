/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefEntityServiceImpl.java] 
 * @author BELDI Jamel on : 19 mars 2014  18:42:01
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEntityDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntity;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEntityDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefEntityService;

/**
 * @author BELDI Jamel  on : 19 mars 2014  18:42:01
 */
@Service ("refEntityServiceImpl")
public class RefEntityServiceImpl implements RefEntityService {
	private static final Logger log = LoggerFactory.getLogger(RefEntityServiceImpl.class.getName());
	@Autowired
	@Qualifier("refEntityDaoImpl")
	private RefEntityDao refEntityDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	/**
	 * [RefEntityServiceImpl.RefEntityServiceImpl()] Constructor
	 * @author BELDI Jamel  on : 19 mars 2014  18:42:01	
	 */
	public RefEntityServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefEntityService#findAll()
	 */
	@Override
	public List<RefEntityDto> findAll() {
		List<RefEntityDto> refEntityListDto = new ArrayList<RefEntityDto>();
		try {
			List<RefEntity> refEntityList = refEntityDao.findAll();
			if (refEntityList != null) {
				log.debug("entity list success with size =  {}", new Object[]{refEntityList.size()});
				for (RefEntity currentRefEntity : refEntityList) {
					RefEntityDto refEntityDto = new RefEntityDto();
					mapper.map(currentRefEntity, refEntityDto);
					refEntityListDto.add(refEntityDto);
				}
				return refEntityListDto;
			}
		} catch (RuntimeException e) {
			log.error("get entity list failed", e);
			throw e;
		}
		return null;
	}

	
	@Override
	public RefEntityDto findById(Integer id) {
		RefEntityDto refEntityDto = null;
		try{
			RefEntity refEntity = refEntityDao.findById(id);
			if(refEntity != null){
				refEntityDto = new RefEntityDto();
 				 mapper.map(refEntity, refEntityDto);
				log.info("success get dto: "+refEntityDto.getName());
				return refEntityDto;
			}
		}catch(Exception e){
			log.error("get failed", e);
			
		}
		return refEntityDto;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefEntityService#findByDomain(java.lang.Integer)
	 */
	@Override
	public List<RefEntityDto> findByDomain(Integer idDomain) {
		List<RefEntityDto> refEntityListDto = new ArrayList<RefEntityDto>();
		try {
			List<RefEntity> refEntityList = refEntityDao.findByDomain(idDomain);
			if (refEntityList != null) {
				log.debug("entity list success with size =  {}", new Object[]{refEntityList.size()});
				for (RefEntity currentRefEntity : refEntityList) {
					RefEntityDto refEntityDto = new RefEntityDto();
					mapper.map(currentRefEntity, refEntityDto);
					refEntityListDto.add(refEntityDto);
				}
				return refEntityListDto;
			}
		} catch (RuntimeException e) {
			log.error("get entity list failed", e);
			throw e;
		}
		return refEntityListDto;
	}

	/**
	 * [RefEntityServiceImpl.refEntityDao] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  18:47:37
	 * @return the refEntityDao
	 */
	public RefEntityDao getRefEntityDao() {
		return refEntityDao;
	}

	/**
	 * [RefEntityServiceImpl.refEntityDao] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  18:47:37
	 * @param refEntityDao the refEntityDao to set
	 */
	public void setRefEntityDao(RefEntityDao refEntityDao) {
		this.refEntityDao = refEntityDao;
	}

	/**
	 * [RefEntityServiceImpl.mapper] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  18:47:37
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefEntityServiceImpl.mapper] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  18:47:37
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}
	
	
	

}
