/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.offreformation.NiveauDaoImpl.java] 
 * @author rlaib on : 16 juil. 2014  10:26:17
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.NiveauDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Cycle;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Niveau;

/**
 * @author rlaib on : 16 juil. 2014 10:26:17
 */
@Service("niveauDao")
public class NiveauDaoImpl implements NiveauDao {

	private static final Logger log = LoggerFactory.getLogger(NiveauDaoImpl.class.getName());
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * [NiveauDaoImpl.persist] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:26:17
	 * @param transientInstance
	 */
	@Override
	public void persist(Niveau transientInstance) {

		log.debug("persisting Niveau instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");

		} catch (RuntimeException re) {

			log.error("persist Niveau failed", re);
			throw re;
		}
	}

	/**
	 * [NiveauDaoImpl.remove] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:26:17
	 * @param persistentInstance
	 */
	@Override
	public void remove(int id) {

		log.debug("removing Niveau instance");
		try {
			Niveau persistentInstance = entityManager.find(Niveau.class, id);
			entityManager.remove(persistentInstance);
			log.debug("remove successful");

		} catch (RuntimeException re) {

			log.error("remove Niveau failed", re);
			throw re;
		}
	}

	/**
	 * [NiveauDaoImpl.merge] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:26:17
	 * @param detachedInstance
	 * @return
	 */
	@Override
	public Niveau merge(Niveau detachedInstance) {

		log.debug("merging Niveau instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);

		} catch (RuntimeException re) {
			log.error("merge Niveau failed", re);
			throw re;
		}
	}

	/**
	 * [NiveauDaoImpl.findById] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:26:17
	 * @param id
	 * @return
	 */
	@Override
	public Niveau findById(int id) {

		log.debug("getting Niveau instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(Niveau.class, id);

		} catch (RuntimeException re) {

			log.error("findById Niveau failed", re);
			throw re;
		}
	}

	/**
	 * [NiveauDaoImpl.findAll] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:26:17
	 * @return
	 */
	@Override
	public List<Niveau> findAll() {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Niveau> c = cb.createQuery(Niveau.class);
			Root<Niveau> niv = c.from(Niveau.class);
			c.select(niv).distinct(true);
			c.orderBy(cb.asc(niv.get("libelleLongLt")));
			TypedQuery<Niveau> q = entityManager.createQuery(c);
			List<Niveau> result = q.getResultList();
			for (Niveau entity : result) {
				if (entityManager.contains(entity)) {
					entityManager.refresh(entity);
				}
			}
			return result;
		} catch (RuntimeException re) {

			log.error("findAll 'Niveau' failed", re);
			throw re;
		}
	}

	/**
	 * [NiveauDaoImpl.findByCycleId] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:26:17
	 * @param idCycle
	 * @return
	 */
	@Override
	public List<Niveau> findByCycleId(int idCycle) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Niveau> c = cb.createQuery(Niveau.class);
			Root<Niveau> n = c.from(Niveau.class);
			Join<Niveau, Cycle> nc = n.join("cycle");
			c.select(n).distinct(true);
			c.orderBy(cb.asc(n.get("rang")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(nc.get("id"), idCycle);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Niveau> q = entityManager.createQuery(c);
			List<Niveau> result = q.getResultList();
			for (Niveau entity : result) {
				if (entityManager.contains(entity)) {
					entityManager.refresh(entity);
				}
			}
			return result;
		} catch (RuntimeException re) {

			log.error("findByCycleId 'Niveau' failed", re);
			throw re;
		}
	}

	@Override
	public List<Niveau> findByCycleCode(String codeCycle) {
		HibernateEntityManager hem = entityManager
				.unwrap(HibernateEntityManager.class);
		Session session = hem.getSession();
		Criteria criteria = session.createCriteria(Niveau.class, "niveau");
		criteria.createAlias("niveau.cycle", "cycle");
		criteria.add(Restrictions.eq("cycle.code", codeCycle));
		return criteria.list();
	}

	/**
	 * Cette fonction renvoi le prochain niveau du niveau niveauBo
	 * 
	 * @author Mounir.MESSAOUDI on : 26 oct. 2014 15:06:20
	 * @param niveauBo
	 * @return
	 */
	public Niveau findProchainNiveauOf(Niveau niveauBo) {
		try {

			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT p from  Niveau  p");
			queryBuilder.append(" WHERE p.cycle.id = :idCycle");

			queryBuilder.append(" and p.rang = :rang");

			TypedQuery<Niveau> query = entityManager.createQuery(
					queryBuilder.toString(), Niveau.class);
			query.setParameter("idCycle", niveauBo.getCycle().getId());
			int rang = niveauBo.getRang() + 1;
			query.setParameter("rang", rang);

			log.debug("findProchainNiveauOf  successful. ");
			List<Niveau> listeNiveaux = query.getResultList();
			if (listeNiveaux != null && !listeNiveaux.isEmpty())
				return listeNiveaux.get(0);
			else
				return null;

		} catch (RuntimeException re) {

			log.error("findByFirstAndSecondYear  failed", re);
			throw re;
		}
	}

	/**
	 * [NiveauDao.findFirstLevelWithinCycle] Method
	 * 
	 * @author rlaib on : 30 oct. 2014 11:36:02
	 * @param codeCycle
	 * @return
	 */
	@Override
	public Niveau findFirstLevelWithinCycle(String codeCycle) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Niveau> c = cb.createQuery(Niveau.class);
			Root<Niveau> n = c.from(Niveau.class);
			Join<Niveau, Cycle> nc = n.join("cycle");
			c.select(n).distinct(true);
			c.orderBy(cb.asc(n.get("rang")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(nc.get("code"), codeCycle);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Niveau> q = entityManager.createQuery(c);
			List<Niveau> result = q.getResultList();
			for (Niveau entity : result) {
				if (entityManager.contains(entity)) {
					entityManager.refresh(entity);
				}
			}
			if (result != null && result.size() > 0)
				return result.get(0);

			return null;
		} catch (RuntimeException re) {

			log.error("findByCycleId 'Niveau' failed", re);
			throw re;
		}
	}

	/**
	 * Cette fonction renvoi le premier niveau dans un cycle
	 * 
	 * @author Mounir.MESSAOUDI on : 4 nov. 2014 07:59:23
	 * @param cycleId
	 * @return
	 */
	public Niveau findPremierNiveauOf(Integer cycleId) {
		try {
			if (cycleId != null) {
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append("select n from Niveau n ");
				sqlQuery.append("WHERE n.cycle.id = :cycleId ");
				sqlQuery.append("AND n.rang = 1");
				TypedQuery<Niveau> query = entityManager.createQuery(
						new String(sqlQuery), Niveau.class);
				query.setParameter("cycleId", cycleId);
				List<Niveau> results = query.getResultList();

				if (results.size() >= 1) {
					return results.get(0);
				}
			}
		} catch (RuntimeException re) {
			log.error("findPremierNiveauOf failed", re);
			throw re;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.NiveauDao#findLastNiveauOf(java.lang.Integer)
	 */
	@Override
	public Niveau findLastNiveauOf(Integer cycleId) {
		try {
			if (cycleId != null) {
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append(" select n from Niveau n ");
				sqlQuery.append(" WHERE n.cycle.id = :cycleId ");
				sqlQuery.append(" order by n.rang desc");
				TypedQuery<Niveau> query = entityManager.createQuery(
						new String(sqlQuery), Niveau.class);
				query.setParameter("cycleId", cycleId);
				List<Niveau> results = query.getResultList();

				if (results != null && !results.isEmpty()) {
					return results.get(0);
				}
			}
		} catch (RuntimeException re) {
			log.error("findLastNiveauOf failed", re);
			throw re;
		}
		return null;
	}

}
