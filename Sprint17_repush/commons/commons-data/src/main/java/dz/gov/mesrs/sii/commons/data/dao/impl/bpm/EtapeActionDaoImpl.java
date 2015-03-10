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

import dz.gov.mesrs.sii.commons.data.dao.bpm.EtapeActionDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Action;
import dz.gov.mesrs.sii.commons.data.model.bpm.Etape;
import dz.gov.mesrs.sii.commons.data.model.bpm.EtapeAction;

/**
 * Dao object for domain model class EtapeAction.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.EtapeAction
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("etapeActionDao")
public class EtapeActionDaoImpl implements EtapeActionDao {

	private static final Logger log = LoggerFactory.getLogger(EtapeActionDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.EtapeActionDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.EtapeAction)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(EtapeAction transientInstance) {
		log.debug("persisting EtapeAction instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.EtapeActionDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.EtapeAction)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(EtapeAction persistentInstance) {
		log.debug("removing EtapeAction instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.EtapeActionDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.EtapeAction)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public EtapeAction merge(EtapeAction detachedInstance) {
		log.debug("merging EtapeAction instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NEtapeActionDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public EtapeAction findById(int id) {
		log.debug("getting EtapeAction instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(EtapeAction.class, id);
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
	public List<EtapeAction> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<EtapeAction> findAll() {
		log.debug("getting all EtapeAction instances");
		try {
			Query query = entityManager.createQuery("from EtapeAction etapeAction");
			log.debug("findAll 'EtapeAction' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'EtapeAction' failed", re);
			return new ArrayList<EtapeAction>();
		}
	}



	/**
	 * [EtapeActionServiceImpl.findByEtapeId] Method  find list action by etape
	 * @author  Jamel BELDI  
	 * On : 4 mai 2014  12:39:12
	 * @param Etape Id
	 * @return EtapeAction
	 */
	@Override
	public List<EtapeAction> findByEtapeId(int etapeId) {
       log.debug("findByEtapeId: getting List of DemandeI18 instance : ");
		

		try {
			StringBuilder request = new StringBuilder(
					"select r from EtapeAction r where r.etape.id = ").append(etapeId);
	
			Query query = entityManager.createQuery(request.toString());
			List<EtapeAction> resultList = (List<EtapeAction>) query
					.getResultList();
			log.debug("findByEtapeId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByEtapeId failed", re);

			throw re;
		}
		
	}

	@Override
	public  EtapeAction findByCodeEtapeByCodeAction(String codeEtape, String codeAction) {
		
		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<EtapeAction> c = cb.createQuery(EtapeAction.class);
					Root<EtapeAction> ea = c.from(EtapeAction.class);
					Join<EtapeAction, Etape> eae = ea.join("etape");
					Join<EtapeAction, Action> eaa = ea.join("action");
					
					c.select(ea).distinct(true);
					List<Predicate> criteria = new ArrayList<Predicate>();
		
					if(codeEtape != null) {
						
							Predicate condition1 = cb.equal(eae.get("code"), codeEtape);
							criteria.add(condition1);
					}
			
					if(codeAction != null) {
							
							Predicate condition2 = cb.equal(eaa.get("code"), codeAction);
							criteria.add(condition2);
					}
					 
					c.where(cb.and(criteria.toArray(new Predicate[0])));
					TypedQuery<EtapeAction> q = entityManager.createQuery(c);
					
					return q.getResultList().get(0);
			
		}
		catch (RuntimeException re) {
		
				log.error("findByCodeEtapeByCodeAction 'SituationI18nDto' failed", re);
				throw re;
		}
	}

}
