package dz.gov.mesrs.sii.commons.data.dao.impl.bpm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.bpm.ActionI18nDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Action;
import dz.gov.mesrs.sii.commons.data.model.bpm.ActionI18n;
import dz.gov.mesrs.sii.commons.data.model.bpm.Etape;
import dz.gov.mesrs.sii.commons.data.model.bpm.EtapeAction;

/**
 * Dao object for domain model class ActionI18n.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.ActionI18n
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("actionI18nDao")
public class ActionI18nDaoImpl implements ActionI18nDao {

	private static final Logger log = LoggerFactory.getLogger(ActionI18nDaoImpl.class.getName());
	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.ActionI18nDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.ActionI18n)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(ActionI18n transientInstance) {
		log.debug("persisting ActionI18n instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.ActionI18nDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.ActionI18n)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(ActionI18n persistentInstance) {
		log.debug("removing ActionI18n instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.ActionI18nDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.ActionI18n)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ActionI18n merge(ActionI18n detachedInstance) {
		log.debug("merging ActionI18n instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NActionI18nDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public ActionI18n findById(int id) {
		log.debug("getting ActionI18n instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(ActionI18n.class, id);
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
	public List<ActionI18n> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<ActionI18n> findAll() {
		log.debug("getting all ActionI18n instances");
		try {
			Query query = entityManager.createQuery("from ActionI18n actionI18n");
			log.debug("findAll 'ActionI18n' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'ActionI18n' failed", re);
			return new ArrayList<ActionI18n>();
		}
	}



	
	@Override 
	public List<ActionI18n> findListActionI18nByAction(int idAction) {
      log.debug("findListActionI18nByAction: getting List of ActionI18n instance : ");
		try {
			StringBuilder request = new StringBuilder(
					"select r from ActionI18n r where r.action.id = ").append(idAction);
	
			Query query = entityManager.createQuery(request.toString());
			List<ActionI18n> resultList = (List<ActionI18n>) query
					.getResultList();
			log.debug("findListActionI18nByAction successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findListActionI18nByAction failed", re);

			throw re;
		}
	}

	@Override 
	public List<ActionI18n> findListActionI18nByCodeEtape(String codeEtape) {
		
		try {
			
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ActionI18n> c = cb.createQuery(ActionI18n.class);
			Root<ActionI18n> ai18 = c.from(ActionI18n.class);
			
			Join<ActionI18n, Action> aa = ai18.join("action");
			Join<Action, EtapeAction> aea = aa.join("action");
			Join<EtapeAction, Etape> eae = aea.join("etape");
			
			c.select(ai18).distinct(true);
			
			List<Predicate> criteria = new ArrayList<Predicate>();
		
			if(codeEtape != null) {
				Predicate condition1 = cb.equal(eae.get("code"), codeEtape);
				criteria.add(condition1);
			}
		
			 c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<ActionI18n> q = entityManager.createQuery(c);
			return q.getResultList();
		}
		catch (RuntimeException re) {
	
			log.error("findListActionI18nByCodeEtape 'DemandeI18n' failed", re);
			throw re;
			
		}
		
	}


}
