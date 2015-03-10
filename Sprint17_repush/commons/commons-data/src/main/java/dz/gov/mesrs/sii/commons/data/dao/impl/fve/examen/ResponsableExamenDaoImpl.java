package dz.gov.mesrs.sii.commons.data.dao.impl.fve.examen;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.ResponsableExamenDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus.VoeuEtudiantDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.ResponsableExamen;

/**
 * Dao object for domain model class ResponsableExamen;
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.ResponsableExamen;
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Repository("responsableExamenDao")
@Transactional
public class ResponsableExamenDaoImpl implements ResponsableExamenDao {

	private static final Logger log = LoggerFactory.getLogger(ResponsableExamenDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.AbsenceResponsableDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.
	 *      ResponsableExamen)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(ResponsableExamen transientInstance) {
		log.debug("persisting ResponsableExamen instance");
		try {
			entityManager.persist(transientInstance);
			/*
			 * entityManager.flush(); entityManager.refresh(transientInstance);
			 */
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.ResponsableExamenDao#remove(dz.gov.mesrs.sii.commons.data.model.fve.examen.ResponsableExamen.business.model.bo.cursus.ResponsableExamen)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(ResponsableExamen persistentInstance) {
		log.debug("removing ResponsableExamen instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.ResponsableExamenDao#merge(dz.gov.mesrs.sii.commons.data.model.fve.examen.fve.business.model.bo.cursus.ResponsableExamen)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponsableExamen merge(ResponsableExamen detachedInstance) {
		log.debug("merging ResponsableExamen instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.ResponsableExamenDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public ResponsableExamen findById(int id) {
		log.debug("getting ResponsableExamen instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(ResponsableExamen.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<ResponsableExamen> findAll() {
		log.debug("getting all ResponsableExamen instances");
		try {
			Query query = entityManager.createQuery("from ResponsableExamen absenceResponsable");
			log.debug("findAll 'ResponsableExamen' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'ResponsableExamen' failed", re);
			return new ArrayList<ResponsableExamen>();
		}
	}

	@Override
	public List<ResponsableExamen> findByExamen(long idExamen) {
		log.debug("getting all findByExamen instances");
		try {
			if (idExamen == 0) {
				return null;
			}
			String sqlQuery = "SELECT e FROM ResponsableExamen e WHERE e.salleExamen.examenSession.id=:idExamen";

			TypedQuery<ResponsableExamen> query = entityManager.createQuery(sqlQuery, ResponsableExamen.class);
			query.setParameter("idExamen", idExamen);

			List<ResponsableExamen> result = query.getResultList();

			return result;

		} catch (RuntimeException re) {
			log.error("findByExamen failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeByExamen(int idExamen) {
		log.debug("getting all findByExamen instances");
		try {
			if (idExamen == 0) {
				return;
			}
			List<ResponsableExamen> list = findByExamen(idExamen);
			if (list == null || list.isEmpty()) {
				return;
			}
			for (ResponsableExamen absenceResponsable : list) {
				entityManager.remove(absenceResponsable);
			}

			// String sqlQuery =
			// "delete FROM ResponsableExamen e WHERE e.examenSession.id=:idExamen";
			//
			// TypedQuery<ResponsableExamen> query = entityManager.createQuery(
			// sqlQuery, ResponsableExamen.class);
			// query.setParameter("idExamen", idExamen);
			// query.executeUpdate();

		} catch (RuntimeException re) {
			log.error("findByExamen failed", re);
			throw re;
		}

	}

	@Override
	public List<ResponsableExamen> findBySalleExamen(int idSalleExamen) {
		try {
			if (idSalleExamen == 0) {
				return null;
			}
			String sqlQuery = "SELECT e FROM ResponsableExamen e WHERE e.salleExamen.id=:idSalleExamen";

			TypedQuery<ResponsableExamen> query = entityManager.createQuery(sqlQuery, ResponsableExamen.class);
			query.setParameter("idSalleExamen", idSalleExamen);

			List<ResponsableExamen> result = query.getResultList();

			return result;
		} catch (RuntimeException re) {
			log.error("findByExamen failed", re);
			throw re;
		}

	}

	@Override
	public ResponsableExamen findByExamenAndIndividu(long idExamen, int idIndividu) {
		try {
			if (idExamen == 0 || idIndividu == 0) {
				return null;
			}
			String sqlQuery = "SELECT e FROM ResponsableExamen e WHERE e.salleExamen.examenSession.id=:idExamen and e.refIndividu.id=:idIndividu";

			TypedQuery<ResponsableExamen> query = entityManager.createQuery(sqlQuery, ResponsableExamen.class);
			query.setParameter("idExamen", idExamen);
			query.setParameter("idIndividu", idIndividu);
			List<ResponsableExamen> result = query.getResultList();

			if (result == null || result.isEmpty()) {
				return null;
			}
			return result.get(0);

		} catch (RuntimeException re) {
			throw re;
		}
	}

}
