/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefOccupationLieuServiceImpl.java] 
 * @author BELBRIK Oualid on : 19 02. 2014  17:35:05
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefOccupationLieuDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefOccupationLieu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefReservation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefOccupationLieuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefOccupationLieuService;

/**
 * @author BELBRIK Oualid  on : 19 02. 2014 17:35:05
 */
@Service("refOccupationLieuServiceImpl")
public class RefOccupationLieuServiceImpl implements RefOccupationLieuService {
	
	private static final Logger log = LoggerFactory.getLogger(RefOccupationLieuServiceImpl.class.getName());

	@Autowired
	@Qualifier("refOccupationLieuDaoImpl")
	private RefOccupationLieuDao refOccupationLieuDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefOccupationLieuService#findbyId(java.lang.String, boolean)
	 */
	@Override
	public List<RefOccupationLieuDto> findByIdLieu(Integer value) {
		
		List<RefOccupationLieuDto> refOccupationLieuListDto = new ArrayList<RefOccupationLieuDto>();
		try {
			List<RefOccupationLieu> refOccupationLieuList = (List<RefOccupationLieu>)refOccupationLieuDao.findByIdLieu(value);
			if (refOccupationLieuList != null) {
				log.debug("OccupationLieu list success with size = {}", new Object[]{refOccupationLieuList.size()});
				for (RefOccupationLieu currentRefOccupationLieu : refOccupationLieuList) {
					RefOccupationLieuDto refOccupationLieuDto = new RefOccupationLieuDto();
					mapper.map(currentRefOccupationLieu, refOccupationLieuDto);
					refOccupationLieuListDto.add(refOccupationLieuDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refOccupationLieuListDto;
		
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefOccupationLieuService#save(dz.gov.mesrs.sii.referentiel.business.model.dto.RefOccupationLieuDto, boolean)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void save(RefOccupationLieuDto refOccupationLieuDto) {
		RefOccupationLieu refOccupationLieu = new RefOccupationLieu();
		if (refOccupationLieuDto != null ) 
		try {
			mapper.map(refOccupationLieuDto, refOccupationLieu);
			refOccupationLieuDao.persist(refOccupationLieu);
			log.debug("save OccupationLieu successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefOccupationLieuService#update(dz.gov.mesrs.sii.referentiel.business.model.dto.RefOccupationLieuDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefOccupationLieuDto refOccupationLieuDto) {
		RefOccupationLieu refOccupationLieu = new RefOccupationLieu();
		mapper.map(refOccupationLieuDto, refOccupationLieu);
		try {
			refOccupationLieuDao.merge(refOccupationLieu);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefOccupationLieuService#saveOrUpdate(dz.gov.mesrs.sii.referentiel.business.model.dto.RefOccupationLieuDto)
	 */
	@Override
	public void saveOrUpdate(RefOccupationLieuDto refOccupationLieuDto) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefOccupationLieuService#delete(java.lang.Integer)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefOccupationLieuDto refOccupationLieuDto) {
		RefOccupationLieu refOccupationLieu = new RefOccupationLieu();
		try {
			mapper.map(refOccupationLieuDto, refOccupationLieu);
			refOccupationLieu = refOccupationLieuDao.merge(refOccupationLieu);
			refOccupationLieuDao.remove(refOccupationLieu);
			log.debug("remove OccupationLieu successful");
		} catch (RuntimeException e) {
			log.error("remove failed", e);
			throw e;
		}

	}

	/**
	 * [RefOccupationLieuServiceImpl.refOccupationLieuDao] Getter 
	 * @author BELBRIK Oualid on : 19 02. 2014 17:43:03
	 * @return the refOccupationLieuDao
	 */
	public RefOccupationLieuDao getRefOccupationLieuDao() {
		return refOccupationLieuDao;
	}

	/**
	 * [RefOccupationLieuServiceImpl.refOccupationLieuDao] Setter 
	 * @author BELBRIK Oualid on : 19 02. 2014 17:43:03
	 * @param refOccupationLieuDao the refOccupationLieuDao to set
	 */
	public void setRefOccupationLieuDao(RefOccupationLieuDao refOccupationLieuDao) {
		this.refOccupationLieuDao = refOccupationLieuDao;
	}

	/**
	 * [RefOccupationLieuServiceImpl.mapper] Getter 
	 * @author BELBRIK Oualid on : 19 02. 2014 17:43:03
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefOccupationLieuServiceImpl.mapper] Setter 
	 * @author BELBRIK Oualid on : 19 02. 2014 17:43:03
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<RefOccupationLieuDto> findByIdEquipement(Integer value) {
		
		List<RefOccupationLieuDto> refOccupationLieuListDto = new ArrayList<RefOccupationLieuDto>();
		try {
			List<RefOccupationLieu> refOccupationLieuList = (List<RefOccupationLieu>)refOccupationLieuDao.findByIdEquipement(value);
			if (refOccupationLieuList != null) {
				log.debug("OccupationLieu list success with size = {}", new Object[]{refOccupationLieuList.size()});
				for (RefOccupationLieu currentRefOccupationLieu : refOccupationLieuList) {
					RefOccupationLieuDto refOccupationLieuDto = new RefOccupationLieuDto();
					mapper.map(currentRefOccupationLieu, refOccupationLieuDto);
					refOccupationLieuListDto.add(refOccupationLieuDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refOccupationLieuListDto;
		
	}
	

}
