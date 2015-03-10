/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefCoordonneeServiceImpl.java] 
 * @author MAKERRI Sofiane on : 15 janv. 2014  12:40:41
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefCoordonneeDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefCoordonnee;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCoordonneeDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefCoordonneeService;

/**
 * @author MAKERRI Sofiane  on : 15 janv. 2014  12:40:41
 */
@Service ("refCoordonneeServiceImpl")
public class RefCoordonneeServiceImpl implements RefCoordonneeService {
	
	private static final Logger log = LoggerFactory.getLogger(RefCoordonneeServiceImpl.class.getName());
	
	@Autowired
	@Qualifier("refCoordonneeDaoImpl")
	private RefCoordonneeDao refCoordonneeDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@PersistenceContext
	private EntityManager entityManager;


	/**
	 * [RefCoordonneeServiceImpl.RefCoordonneeServiceImpl()] Constructor
	 * @author MAKERRI Sofiane  on : 15 janv. 2014  12:40:41	
	 */
	public RefCoordonneeServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Find List of COORDONNEE of INDIVIDU
	 */
	@Override
	public List<RefCoordonneeDto> findByRefIndividuId(Integer id) {
		List<RefCoordonneeDto> refCoordonneeListDto = new ArrayList<RefCoordonneeDto>();
		try {
			//List<RefCoordonnee> refCoordonneeList = refCoordonneeDao.findByRefIndividuId(UtilConstant.COORDONNEE_ADRESSE_TYPE, identifiant);
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao.findByRefIndividuId(id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size {}  ", new Object[]{ refCoordonneeList.size()});

				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefCoordonneeDto refCoordonneeDto = new RefCoordonneeDto();
					mapper.map(currentRefCoordonnee, refCoordonneeDto);
					refCoordonneeListDto.add(refCoordonneeDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get coordonnees list failed", e);
			throw e;
		}
		return refCoordonneeListDto;

	}

	/**
	 * Find List of COORDONNEE of STRUCTURE
	 */
	@Override
	public List<RefCoordonneeDto> findByRefStructureId(Integer id) {
		List<RefCoordonneeDto> refCoordonneeListDto = new ArrayList<RefCoordonneeDto>();
		try {
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao.findByRefStructureId(id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size {}  ", new Object[]{ refCoordonneeList.size()});
				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefCoordonneeDto refCoordonneeDto = new RefCoordonneeDto();
					mapper.map(currentRefCoordonnee, refCoordonneeDto);
					refCoordonneeListDto.add(refCoordonneeDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get coordonnees list failed", e);
			throw e;
		}
		return refCoordonneeListDto;
	}
	
	
	/**
	 * Find List of COORDONNEE of Lieu
	 */
	@Override
	public List<RefCoordonneeDto> findByRefLieuId(Integer id) {
		List<RefCoordonneeDto> refCoordonneeListDto = new ArrayList<RefCoordonneeDto>();
		try {
			//List<RefCoordonnee> refCoordonneeList = refCoordonneeDao.findByRefLieuId(UtilConstant.COORDONNEE_ADRESSE_TYPE, identifiant);
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao.findByRefLieuId(id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size {}  ", new Object[]{ refCoordonneeList.size()});

				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefCoordonneeDto refCoordonneeDto = new RefCoordonneeDto();
					mapper.map(currentRefCoordonnee, refCoordonneeDto);
					refCoordonneeListDto.add(refCoordonneeDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get coordonnees list failed", e);
			throw e;
		}
		return refCoordonneeListDto;

	}


	/**
	 * Save COORDONNEE DTO
	 */
	@Override
	public void save(RefCoordonneeDto refCoordonneeDto) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefCoordonneeService#update(dz.gov.mesrs.sii.referentiel.business.model.dto.RefCoordonneeDto)
	 */
	@Override
	public void update(RefCoordonneeDto refCoordonneeDto) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefCoordonneeService#saveOrUpdate(dz.gov.mesrs.sii.referentiel.business.model.dto.RefCoordonneeDto)
	 */
	@Override
	public void saveOrUpdate(RefCoordonneeDto refCoordonneeDto) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefCoordonneeService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * [RefCoordonneeServiceImpl.refCoordonneeDao] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:57:33
	 * @return the refCoordonneeDao
	 */
	public RefCoordonneeDao getRefCoordonneeDao() {
		return refCoordonneeDao;
	}

	/**
	 * [RefCoordonneeServiceImpl.refCoordonneeDao] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:57:33
	 * @param refCoordonneeDao the refCoordonneeDao to set
	 */
	public void setRefCoordonneeDao(RefCoordonneeDao refCoordonneeDao) {
		this.refCoordonneeDao = refCoordonneeDao;
	}

	/**
	 * [RefCoordonneeServiceImpl.mapper] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:57:33
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefCoordonneeServiceImpl.mapper] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:57:33
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<RefCoordonneeDto> findByRefEtablissementId(Integer id) {
		List<RefCoordonneeDto> refCoordonneeListDto = new ArrayList<RefCoordonneeDto>();
		try {
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao.findByRefEtablissementId(id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size {}  ", new Object[]{ refCoordonneeList.size()});
				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefCoordonneeDto refCoordonneeDto = new RefCoordonneeDto();
					mapper.map(currentRefCoordonnee, refCoordonneeDto);
					refCoordonneeListDto.add(refCoordonneeDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get coordonnees list failed", e);
			throw e;
		}
		return refCoordonneeListDto;
	}
	
	

}
