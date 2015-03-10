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

import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Entite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;

/**
 * Dao object for domain model class SituationEntiteOccurrence.
 * 
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.SituationEntiteOccurrence
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Transactional
@Service("situationEntiteOccurrenceDao")
public class SituationEntiteOccurrenceDaoImpl implements SituationEntiteOccurrenceDao {

	private static final Logger log = LoggerFactory.getLogger(SituationEntiteOccurrenceDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.SituationEntiteOccurrenceDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.SituationEntiteOccurrence)
	 * 
	 * @param transientInstance
	 */

	@Transactional
	@Override
	public void persist(SituationEntiteOccurrence transientInstance) {
		log.debug("persisting SituationEntiteOccurrence instance");
		try {
			entityManager.persist(transientInstance);
			this.entityManager.flush();
			this.entityManager.refresh(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.SituationEntiteOccurrenceDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.SituationEntiteOccurrence)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(SituationEntiteOccurrence persistentInstance) {
		log.debug("removing SituationEntiteOccurrence instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.SituationEntiteOccurrenceDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.SituationEntiteOccurrence)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SituationEntiteOccurrence merge(SituationEntiteOccurrence detachedInstance) {
		log.debug("merging SituationEntiteOccurrence instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NSituationEntiteOccurrenceDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public SituationEntiteOccurrence findById(int id) {
		log.debug("getting SituationEntiteOccurrence instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(SituationEntiteOccurrence.class, id);
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
	public List<SituationEntiteOccurrence> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<SituationEntiteOccurrence> findAll() {
		log.debug("getting all SituationEntiteOccurrence instances");
		try {
			Query query = entityManager.createQuery("from SituationEntiteOccurrence situationEntiteOccurrence");
			log.debug("findAll 'SituationEntiteOccurrence' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'SituationEntiteOccurrence' failed", re);
			return new ArrayList<SituationEntiteOccurrence>();
		}
	}

	@SuppressWarnings("unused")
	@Override
	public List<SituationEntiteOccurrence> getEntityOccurrenceHistory(String entiteCode, Integer entiteOccurrenceId) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<SituationEntiteOccurrence> c = cb.createQuery(SituationEntiteOccurrence.class);
			Root<SituationEntiteOccurrence> seo = c.from(SituationEntiteOccurrence.class);
			Join<SituationEntiteOccurrence, SituationEntite> se = seo.join("situationEntite");
			Join<SituationEntite, Entite> see = se.join("entite");
			c.select(seo).distinct(true).orderBy(cb.desc(seo.get("dateSituation")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			if (entiteCode != null) {
				Predicate condition1 = cb.equal(see.get("code"), entiteCode);
				criteria.add(condition1);
			}
			if (entiteOccurrenceId != null) {
				Predicate condition2 = cb.equal(seo.get("idOccurrence"), entiteOccurrenceId);
				criteria.add(condition2);
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<SituationEntiteOccurrence> q = entityManager.createQuery(c);
			return q.getResultList();
		}

		catch (RuntimeException re) {

			log.error("getEntityOccurrenceHistory 'SituationEntiteOccurrence' failed", re);
			throw re;
		}
	}

}
