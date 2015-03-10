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

import dz.gov.mesrs.sii.commons.data.dao.bpm.TypeDecisionDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.TypeDecision;

/**
 * Dao object for domain model class TypeDecision.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.TypeDecision
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("typeDecisionDao")
public class TypeDecisionDaoImpl implements TypeDecisionDao {

	private static final Logger log = LoggerFactory.getLogger(TypeDecisionDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.TypeDecisionDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.TypeDecision)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(TypeDecision transientInstance) {
		log.debug("persisting TypeDecision instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.TypeDecisionDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.TypeDecision)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(TypeDecision persistentInstance) {
		log.debug("removing TypeDecision instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.TypeDecisionDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.TypeDecision)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TypeDecision merge(TypeDecision detachedInstance) {
		log.debug("merging TypeDecision instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NTypeDecisionDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public TypeDecision findById(int id) {
		log.debug("getting TypeDecision instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(TypeDecision.class, id);
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
	public List<TypeDecision> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<TypeDecision> findAll() {
		log.debug("getting all TypeDecision instances");
		try {
			Query query = entityManager.createQuery("from TypeDecision typeDecision");
			log.debug("findAll 'TypeDecision' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'TypeDecision' failed", re);
			return new ArrayList<TypeDecision>();
		}
	}
}
