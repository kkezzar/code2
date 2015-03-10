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

import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Entite;
import dz.gov.mesrs.sii.commons.data.model.bpm.Situation;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;

/**
 * Dao object for domain model class SituationEntite.
 * 
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.SituationEntite
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Service("situationEntiteDao")
public class SituationEntiteDaoImpl implements SituationEntiteDao {

	private static final Logger log = LoggerFactory.getLogger(SituationEntiteDaoImpl.class.getName());


	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.SituationEntiteDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.SituationEntite)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(SituationEntite transientInstance) {
		log.debug("persisting SituationEntite instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.SituationEntiteDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.SituationEntite)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(SituationEntite persistentInstance) {
		log.debug("removing SituationEntite instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.SituationEntiteDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.SituationEntite)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SituationEntite merge(SituationEntite detachedInstance) {
		log.debug("merging SituationEntite instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NSituationEntiteDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public SituationEntite findById(int id) {
		log.debug("getting SituationEntite instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(SituationEntite.class, id);
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
	public List<SituationEntite> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<SituationEntite> findAll() {
		log.debug("getting all SituationEntite instances");
		try {
			Query query = entityManager
					.createQuery("from SituationEntite situationEntite");
			log.debug("findAll 'SituationEntite' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'SituationEntite' failed", re);
			return new ArrayList<SituationEntite>();
		}
	}


	public SituationEntite findByCodeSituationByCodeEntite(String codeEntite,
			String codeSituation) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<SituationEntite> c = cb
					.createQuery(SituationEntite.class);
			Root<SituationEntite> se = c.from(SituationEntite.class);
			Join<SituationEntite, Situation> ses = se.join("situation");
			Join<SituationEntite, Entite> see = se.join("entite");

			c.select(se).distinct(true);

			List<Predicate> criteria = new ArrayList<Predicate>();

			Predicate condition1 = cb.equal(ses.get("code"), codeSituation);
			Predicate condition2 = cb.equal(see.get("code"), codeEntite);
			criteria.add(condition1);
			criteria.add(condition2);
			c.where(cb.and(criteria.toArray(new Predicate[0])));

			TypedQuery<SituationEntite> q = entityManager.createQuery(c);
			if (q.getResultList() != null && q.getResultList().size() > 0) {
				SituationEntite s = q.getResultList().get(0);
				return s;
			}

			else
				return new SituationEntite();
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error(
					"findByCodeSituationByCodeEntite 'SituationEntite' failed",
					re);
			return null;
		}

	}

	/**
	 * [SituationEntiteDao.findSituationsByEntiteCode] Method 
	 * @author rlaib  on : 10 nov. 2014  15:12:40
	 * @param codeEntite
	 * @return
	 */
	@Override
	public List<SituationEntite> findSituationsByEntiteCode(String codeEntite) {
		// TODO Auto-generated method stub
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<SituationEntite> c = cb
					.createQuery(SituationEntite.class);
			Root<SituationEntite> se = c.from(SituationEntite.class);
			Join<SituationEntite, Entite> see = se.join("entite");
			c.select(se).distinct(true);

			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(see.get("code"), codeEntite);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));

			TypedQuery<SituationEntite> q = entityManager.createQuery(c);
			return q.getResultList();
			
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error("findSituationsByEntiteCode 'SituationEntite' failed",
					re);
			return null;
		}

	}

}
