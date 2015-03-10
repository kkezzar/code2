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

import dz.gov.mesrs.sii.commons.data.dao.bpm.DecisionDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Decision;

/**
 * Dao object for domain model class Decision.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.Decision
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("decisionDao")
public class DecisionDaoImpl implements DecisionDao {

	private static final Logger log = LoggerFactory.getLogger(DecisionDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DecisionDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.Decision)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(Decision transientInstance) {
		log.debug("persisting Decision instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DecisionDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.Decision)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Decision persistentInstance) {
		log.debug("removing Decision instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DecisionDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.Decision)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Decision merge(Decision detachedInstance) {
		log.debug("merging Decision instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NDecisionDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public Decision findById(int id) {
		log.debug("getting Decision instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(Decision.class, id);
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
	public List<Decision> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<Decision> findAll() {
		log.debug("getting all Decision instances");
		try {
			Query query = entityManager.createQuery("from Decision decision");
			log.debug("findAll 'Decision' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Decision' failed", re);
			return new ArrayList<Decision>();
		}
	}
}
