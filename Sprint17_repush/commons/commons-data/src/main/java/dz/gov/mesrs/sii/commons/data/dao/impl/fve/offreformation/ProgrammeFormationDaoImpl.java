package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.ProgrammeFormationDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ProgrammeFormation;

/**
 * Dao object for domain model class ProgrammeFormation.
 * @see dz.gov.mesrs.sii.fve.business.model.bo.ProgrammeFormation
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Repository ("programmeFormationDao")  @Transactional
public class ProgrammeFormationDaoImpl implements ProgrammeFormationDao {

	private static final Logger log = LoggerFactory.getLogger(ProgrammeFormationDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.ProgrammeFormationDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.ProgrammeFormation)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(ProgrammeFormation transientInstance) {
		log.debug("persisting ProgrammeFormation instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.ProgrammeFormationDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.ProgrammeFormation)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(ProgrammeFormation persistentInstance) {
		log.debug("removing ProgrammeFormation instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.ProgrammeFormationDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.ProgrammeFormation)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProgrammeFormation merge(ProgrammeFormation detachedInstance) {
		log.debug("merging ProgrammeFormation instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NProgrammeFormationDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public ProgrammeFormation findById(int id) {
		log.debug("getting ProgrammeFormation instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(ProgrammeFormation.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	
	/**
	 * 
	 * @param query
	 */
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<ProgrammeFormation> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<ProgrammeFormation> findAll() {
		log.debug("getting all ProgrammeFormation instances");
		try {
			Query query = entityManager.createQuery("from ProgrammeFormation programmeFormation");
			log.debug("findAll 'ProgrammeFormation' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'ProgrammeFormation' failed", re);
			return new ArrayList<ProgrammeFormation>();
		}
	}
}
