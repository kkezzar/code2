package dz.gov.mesrs.sii.commons.data.dao.impl.bpm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Situation;

/**
 * Dao object for domain model class Situation.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.Situation
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
@Service ("situationDao")
public class SituationDaoImpl implements SituationDao {

	private static final Logger log = LoggerFactory.getLogger(SituationDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.SituationDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.Situation)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(Situation transientInstance) {
		log.debug("persisting Situation instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.SituationDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.Situation)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Situation persistentInstance) {
		log.debug("removing Situation instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.SituationDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.Situation)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Situation merge(Situation detachedInstance) {
		log.debug("merging Situation instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NSituationDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public Situation findById(int id) {
		log.debug("getting Situation instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(Situation.class, id);
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
	public List<Situation> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<Situation> findAll() {
		log.debug("getting all Situation instances");
		try {
			Query query = entityManager.createQuery("from Situation situation");
			log.debug("findAll 'Situation' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Situation' failed", re);
			return new ArrayList<Situation>();
		}
	}
}
