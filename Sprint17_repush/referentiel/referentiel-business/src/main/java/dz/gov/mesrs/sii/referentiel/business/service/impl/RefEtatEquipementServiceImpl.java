/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefEtatEquipementServiceImpl.java] 
 * @author BELDI Jamel on : 16 janv. 2014  15:33:05
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEtatEquipementDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtatEquipement;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtatEquipementDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtatEquipementService;

/**
 * @author BELDI Jamel on : 16 janv. 2014 15:33:05
 */
@Service("refEtatEquipementServiceImpl")
public class RefEtatEquipementServiceImpl implements RefEtatEquipementService {
	private static final Logger log = LoggerFactory.getLogger(RefEtatEquipementServiceImpl.class.getName());
	@Autowired
	@Qualifier("refEtatEquipementDaoImpl")
	private RefEtatEquipementDao refEtatEquipementDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * [RefEtatEquipementServiceImpl.refEtatEquipementDao] Getter
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @return the refEtatEquipementDao
	 */
	public RefEtatEquipementDao getRefEtatEquipementDao() {
		return refEtatEquipementDao;
	}

	/**
	 * [RefEtatEquipementServiceImpl.refEtatEquipementDao] Setter
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param refEtatEquipementDao
	 *            the refEtatEquipementDao to set
	 */
	public void setRefEtatEquipementDao(RefEtatEquipementDao refEtatEquipementDao) {
		this.refEtatEquipementDao = refEtatEquipementDao;
	}

	/**
	 * [RefEtatEquipementServiceImpl.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefEtatEquipementServiceImpl.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * @param transientInstance
	 * @see dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEtatEquipementDao#persist(dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtatEquipement)
	 */
	public void persist(RefEtatEquipement transientInstance) {
		refEtatEquipementDao.persist(transientInstance);
	}

	/**
	 * @param persistentInstance
	 * @see dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEtatEquipementDao#remove(dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtatEquipement)
	 */
	public void remove(RefEtatEquipement persistentInstance) {
		refEtatEquipementDao.remove(persistentInstance);
	}

	/**
	 * @param detachedInstance
	 * @return
	 * @see dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEtatEquipementDao#merge(dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtatEquipement)
	 */
	public RefEtatEquipement merge(RefEtatEquipement detachedInstance) {
		return refEtatEquipementDao.merge(detachedInstance);
	}

	/**
	 * [RefEtatEquipementServiceImpl.RefEtatEquipementServiceImpl()] Constructor
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 15:33:05
	 */
	public RefEtatEquipementServiceImpl() {

	}

	/**
	 * [RefEtatEquipementServiceImpl.save] save new RefEtatEquipement
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            RefEtatEquipementDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void save(RefEtatEquipementDto refEtatEquipementDto) {
		RefEtatEquipement refEtatEquipement = new RefEtatEquipement();
		try {
			mapper.map(refEtatEquipementDto, refEtatEquipement);

			refEtatEquipementDao.persist(refEtatEquipement);
			log.debug("save successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

	}

	/**
	 * [RefEtatEquipementServiceImpl.update] update new RefEtatEquipement
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            RefEtatEquipementDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefEtatEquipementDto refEtatEquipementDto) {
		RefEtatEquipement refEtatEquipement = new RefEtatEquipement();
		try {
			mapper.map(refEtatEquipementDto, refEtatEquipement);
			refEtatEquipementDao.merge(refEtatEquipement);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	/**
	 * [RefEtatEquipementServiceImpl.findById] find the RefEtatEquipement by Identity
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            Id
	 * @return the RefEtatEquipementDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefEtatEquipementDto findById(int id) {
		RefEtatEquipementDto refEtatEquipementDto = null;
		try {
			RefEtatEquipement refEtatEquipement = refEtatEquipementDao.findById(id);

			if (refEtatEquipement != null) {

				refEtatEquipementDto = new RefEtatEquipementDto();
				mapper.map(refEtatEquipement, refEtatEquipementDto);

			}

		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refEtatEquipementDto;
		
	}

	/**
	 * [RefEtatEquipementServiceImpl.delete] delete the RefEtatEquipement
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            RefEtatEquipementDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefEtatEquipementDto refEtatEquipementDto) {
		RefEtatEquipement refEtatEquipement = new RefEtatEquipement();
		try {
			mapper.map(refEtatEquipementDto, refEtatEquipement);
			refEtatEquipement = refEtatEquipementDao.merge(refEtatEquipement);
			refEtatEquipementDao.remove(refEtatEquipement);
			log.debug("delete successful");
		} catch (RuntimeException e) {
			log.error("delete failed", e);
			throw e;
		}

	}

	
	@Override
	public List<RefEtatEquipementDto> findByIdEquipement(Integer value) {
		
		List<RefEtatEquipementDto> refEtatEquipementListDto = new ArrayList<RefEtatEquipementDto>();
		try {
			List<RefEtatEquipement> refEtatEquipementList = (List<RefEtatEquipement>)refEtatEquipementDao.findByIdEquipement(value);
			if (refEtatEquipementList != null) {
				log.debug("EtatEquipement list success with size =  {} ", new Object[]{refEtatEquipementList.size()});
				for (RefEtatEquipement currentRefEtatEquipement : refEtatEquipementList) {
					RefEtatEquipementDto refEtatEquipementDto = new RefEtatEquipementDto();
					mapper.map(currentRefEtatEquipement, refEtatEquipementDto);
					refEtatEquipementListDto.add(refEtatEquipementDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refEtatEquipementListDto;
		
	}

	@Override
	public Object findEtat(String idf) {
		// TODO Auto-generated method stub
		return null;
	}


	


}
