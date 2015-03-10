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

import dz.gov.mesrs.sii.commons.data.dao.bpm.ActionDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Action;
import dz.gov.mesrs.sii.commons.data.util.ConstantHelper;

/**
 * Dao object for domain model class Action.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.Action
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("actionDao")
public class ActionDaoImpl implements ActionDao {

	private static final Logger log = LoggerFactory.getLogger(ActionDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.ActionDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.Action)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(Action transientInstance) {
		log.debug("persisting Action instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.ActionDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.Action)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Action persistentInstance) {
		log.debug("removing Action instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.ActionDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.Action)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Action merge(Action detachedInstance) {
		log.debug("merging Action instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NActionDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public Action findById(int id) {
		log.debug("getting Action instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(Action.class, id);
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
	public List<Action> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<Action> findAll() {
		log.debug("getting all Action instances");
		try {
			Query query = entityManager.createQuery("from Action action");
			log.debug("findAll 'Action' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Action' failed", re);
			return new ArrayList<Action>();
		}
	}



	@Override
	public Action findByCode(String code) {
		log.debug("findByCode");
		if (code == null) {
			return null;
		}
		
		try {
			StringBuilder request = new StringBuilder(
					"select r from Action r where  r.code = ");
			
				request.append(ConstantHelper.quotedString(code));
				
			Query query = entityManager.createQuery(new String(request));
			log.info("request successful " + new String(request));
			List<Action> resultList = (List<Action>) query
					.getResultList();
			
			if(resultList!=null && resultList.size()==1){
				return resultList.get(0);
			}
			 return null;

		} catch (RuntimeException re) {
			log.error("findByCode failed", re);

			throw re;
		}
	}
}
