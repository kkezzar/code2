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

import dz.gov.mesrs.sii.commons.data.dao.bpm.DecisionI18nDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.DecisionI18n;

/**
 * Dao object for domain model class DecisionI18n.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.DecisionI18n
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("decisionI18nDao")
public class DecisionI18nDaoImpl implements DecisionI18nDao {

	private static final Logger log = LoggerFactory.getLogger(DecisionI18nDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DecisionI18nDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.DecisionI18n)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(DecisionI18n transientInstance) {
		log.debug("persisting DecisionI18n instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DecisionI18nDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.DecisionI18n)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(DecisionI18n persistentInstance) {
		log.debug("removing DecisionI18n instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DecisionI18nDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.DecisionI18n)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DecisionI18n merge(DecisionI18n detachedInstance) {
		log.debug("merging DecisionI18n instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NDecisionI18nDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public DecisionI18n findById(int id) {
		log.debug("getting DecisionI18n instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(DecisionI18n.class, id);
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
	public List<DecisionI18n> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<DecisionI18n> findAll() {
		log.debug("getting all DecisionI18n instances");
		try {
			Query query = entityManager.createQuery("from DecisionI18n decisionI18n");
			log.debug("findAll 'DecisionI18n' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'DecisionI18n' failed", re);
			return new ArrayList<DecisionI18n>();
		}
	}
}
