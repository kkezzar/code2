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

import dz.gov.mesrs.sii.commons.data.dao.bpm.EntiteDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Entite;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaine;

/**
 * Dao object for domain model class Entite.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.Entite
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("entiteDao")
public class EntiteDaoImpl implements EntiteDao {

	private static final Logger log = LoggerFactory.getLogger(EntiteDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.EntiteDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.Entite)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(Entite transientInstance) {
		log.debug("persisting Entite instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.EntiteDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.Entite)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Entite persistentInstance) {
		log.debug("removing Entite instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.EntiteDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.Entite)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Entite merge(Entite detachedInstance) {
		log.debug("merging Entite instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NEntiteDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public Entite findById(int id) {
		log.debug("getting Entite instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(Entite.class, id);
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
	public List<Entite> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<Entite> findAll() {
		log.debug("getting all Entite instances");
		try {
			Query query = entityManager.createQuery("from Entite entite");
			log.debug("findAll 'Entite' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Entite' failed", re);
			return new ArrayList<Entite>();
		}
	}



	/**
	 * [EntiteDao.findByCode] Method 
	 * Overridden By : @author rlaib  on : 19 janv. 2015  10:25:23
	 * @param codeEntite
	 * @return
	 */
	@Override
	public Entite findByCode(String codeEntite) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Entite> c = cb.createQuery(Entite.class);
			Root<Entite> e = c.from(Entite.class);
			c.select(e).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition = cb.equal(e.get("code"), codeEntite);
			criteria.add(condition);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Entite> q = entityManager.createQuery(c);
			List<Entite> result = q.getResultList();
			if(result != null && result.size() ==1)
				return result.get(0);
			return null;
		}
		catch (RuntimeException re) {

				log.error("findByCode 'Entite' failed", re);
				throw re;
		}
	}



	/**
	 * [EntiteDao.findByCodeDomaine] Method 
	 * Overridden By : @author rlaib  on : 21 janv. 2015  16:21:43
	 * @param codeDomaine
	 * @return
	 */
	@Override
	public List<Entite> findByCodeDomaine(String codeDomaine) {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Entite> c = cb.createQuery(Entite.class);
			Root<Entite> e = c.from(Entite.class);
			Join<Entite,RefDomaine> ed = e.join("refDomaine");
			c.select(e).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition = cb.equal(ed.get("identifiant"), codeDomaine);
			criteria.add(condition);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Entite> q = entityManager.createQuery(c);
			List<Entite> result = q.getResultList();
			return result;
		}
		catch (RuntimeException re) {

				log.error("findByCodeDomaine 'Entite' failed", re);
				throw re;
		}
	}
}
