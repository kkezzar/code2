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

import dz.gov.mesrs.sii.commons.data.dao.bpm.TacheActionDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.TacheAction;

/**
 * Dao object for domain model class TacheAction.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.TacheAction
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

@Service ("tacheActionDao")
public class TacheActionDaoImpl implements TacheActionDao {

	private static final Logger log = LoggerFactory.getLogger(TacheActionDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.TacheActionDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.TacheAction)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(TacheAction transientInstance) {
		log.debug("persisting TacheAction instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.TacheActionDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.TacheAction)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(TacheAction persistentInstance) {
		log.debug("removing TacheAction instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.TacheActionDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.TacheAction)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TacheAction merge(TacheAction detachedInstance) {
		log.debug("merging TacheAction instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NTacheActionDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public TacheAction findById(int id) {
		log.debug("getting TacheAction instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(TacheAction.class, id);
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
	public List<TacheAction> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<TacheAction> findAll() {
		log.debug("getting all TacheAction instances");
		try {
			Query query = entityManager.createQuery("from TacheAction tacheAction");
			log.debug("findAll 'TacheAction' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'TacheAction' failed", re);
			return new ArrayList<TacheAction>();
		}
	}



	@Override
	public List<TacheAction> findTacheActionByTache(int idTache) {
		log.debug("findTacheActionByTache: ");
		try {
			StringBuilder request = new StringBuilder(
					"select r from TacheAction r where r.tache.id = ").append(idTache);
	
			Query query = entityManager.createQuery(request.toString());
			List<TacheAction> resultList = (List<TacheAction>) query
					.getResultList();
			log.debug("findTacheActionByTache successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findTacheActionByTache failed", re);

			throw re;
		}
	}
}
