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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.bpm.EtapeDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Etape;

/**
 * Dao object for domain model class Etape.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.Etape
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("etapeDao")
public class EtapeDaoImpl implements EtapeDao {

	private static final Logger log = LoggerFactory.getLogger(EtapeDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.EtapeDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.Etape)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(Etape transientInstance) {
		log.debug("persisting Etape instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.EtapeDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.Etape)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Etape persistentInstance) {
		log.debug("removing Etape instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.EtapeDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.Etape)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Etape merge(Etape detachedInstance) {
		log.debug("merging Etape instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NEtapeDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public Etape findById(int id) {
		log.debug("getting Etape instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(Etape.class, id);
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
	public List<Etape> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<Etape> findAll() {
		log.debug("getting all Etape instances");
		try {
			Query query = entityManager.createQuery("from Etape etape");
			log.debug("findAll 'Etape' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Etape' failed", re);
			return new ArrayList<Etape>();
		}
	}

	@Override
	public  Etape findByCode(String codeEtape) {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Etape> c = cb.createQuery(Etape.class);
			Root<Etape> e  = c.from(Etape.class);
			c.select(e).distinct(true);
			Predicate condition = cb.equal(e.get("code"), codeEtape);
			c.where(condition);
			
			TypedQuery<Etape> q = entityManager.createQuery(c);
			return q.getResultList().get(0);
			
	}
	catch (RuntimeException re) {
		
		log.error("findByIdSituationEntite 'SituationI18nDto' failed", re);
		return null;
	}
	}
	

}
