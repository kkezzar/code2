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

import dz.gov.mesrs.sii.commons.data.dao.bpm.ProcessusI18nDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.ProcessusI18n;

/**
 * Dao object for domain model class ProcessusI18n.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.ProcessusI18n
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("processusI18nDao")
public class ProcessusI18nDaoImpl implements ProcessusI18nDao {

	private static final Logger log = LoggerFactory.getLogger(ProcessusI18nDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.ProcessusI18nDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.ProcessusI18n)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(ProcessusI18n transientInstance) {
		log.debug("persisting ProcessusI18n instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.ProcessusI18nDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.ProcessusI18n)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(ProcessusI18n persistentInstance) {
		log.debug("removing ProcessusI18n instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.ProcessusI18nDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.ProcessusI18n)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProcessusI18n merge(ProcessusI18n detachedInstance) {
		log.debug("merging ProcessusI18n instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NProcessusI18nDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public ProcessusI18n findById(int id) {
		log.debug("getting ProcessusI18n instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(ProcessusI18n.class, id);
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
	public List<ProcessusI18n> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<ProcessusI18n> findAll() {
		log.debug("getting all ProcessusI18n instances");
		try {
			Query query = entityManager.createQuery("from ProcessusI18n processusI18n");
			log.debug("findAll 'ProcessusI18n' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'ProcessusI18n' failed", re);
			return new ArrayList<ProcessusI18n>();
		}
	}
}
