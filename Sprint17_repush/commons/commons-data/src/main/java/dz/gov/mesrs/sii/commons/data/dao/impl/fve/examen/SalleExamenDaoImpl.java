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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.SalleExamenDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus.VoeuEtudiantDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.SalleExamen;

/**
 * Dao object for domain model class SalleExamen;
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.SalleExamen;
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Repository("salleExamenDao")
@Transactional
public class SalleExamenDaoImpl implements SalleExamenDao {

	private static final Logger log = LoggerFactory
			.getLogger(SalleExamenDaoImpl.class.getName());
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.SalleExamenDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.SalleExamen)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(SalleExamen transientInstance) {
		log.debug("persisting SalleExamen instance");
		try {
			entityManager.persist(transientInstance);
			// entityManager.flush();
			// entityManager.refresh(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.SalleExamenDao#remove(dz.gov.mesrs.sii.commons.data.model.fve.examen.SalleExamenDto.business.model.bo.cursus.SalleExamen)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(SalleExamen persistentInstance) {
		log.debug("removing SalleExamen instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.SalleExamenDao#merge(dz.gov.mesrs.sii.commons.data.model.fve.examen.fve.business.model.bo.cursus.SalleExamen)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SalleExamen merge(SalleExamen detachedInstance) {
		log.debug("merging SalleExamen instance");
		try {
			log.debug("merge successful");
			entityManager.merge(detachedInstance);
			//entityManager.flush();
			//entityManager.refresh(detachedInstance);
			return detachedInstance;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.SalleExamenDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public SalleExamen findById(int id) {
		log.debug("getting SalleExamen instance with id: " + id);
		try {
			log.debug("get successful");

			SalleExamen salleExamen = entityManager.find(SalleExamen.class, id);
			if (salleExamen != null) {
				entityManager.flush();
				entityManager.refresh(salleExamen);
			}
			return salleExamen;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<SalleExamen> findAll() {
		log.debug("getting all SalleExamen instances");
		try {
			Query query = entityManager
					.createQuery("from SalleExamen salleExamen");
			log.debug("findAll 'SalleExamen' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'SalleExamen' failed", re);
			return new ArrayList<SalleExamen>();
		}
	}

	@Override
	public List<SalleExamen> findByExamen(Long idExamen) {
		log.debug("getting all findByExamen instances");
		try {
			if (idExamen == 0) {
				return null;
			}
			String sqlQuery = "SELECT s FROM SalleExamen s WHERE s.examenSession.id=:idExamen";

			TypedQuery<SalleExamen> query = entityManager.createQuery(sqlQuery,
					SalleExamen.class);
			query.setParameter("idExamen", idExamen);

			List<SalleExamen> result = query.getResultList();

			return result;

		} catch (RuntimeException re) {
			log.error("findByExamen failed", re);
			throw re;
		}
	}

	@Override
	public List<SalleExamen> findByExamenAndSalle(Long idExamen, Integer idSalle) {
		try {
			if (idExamen == null || idSalle == null || idExamen == 0
					|| idSalle == 0) {
				return null;
			}
			String sqlQuery = "SELECT s FROM SalleExamen s WHERE s.examenSession.id=:idExamen and s.refLieu.id=:idSalle";

			TypedQuery<SalleExamen> query = entityManager.createQuery(sqlQuery,
					SalleExamen.class);
			query.setParameter("idExamen", idExamen);
			query.setParameter("idSalle", idSalle);
			List<SalleExamen> result = query.getResultList();

			return result;

		} catch (RuntimeException re) {
			log.error("findByExamen failed", re);
			throw re;
		}
	}

	@Override
	public List<SalleExamen> findBySession(long idSession) {
		try {
			if (idSession == 0) {
				return null;
			}
			String sqlQuery = "SELECT s FROM SalleExamen s WHERE s.examenSession.planningSession.id=:idSession";

			TypedQuery<SalleExamen> query = entityManager.createQuery(sqlQuery,
					SalleExamen.class);
			query.setParameter("idSession", idSession);

			List<SalleExamen> result = query.getResultList();

			return result;

		} catch (RuntimeException re) {
			log.error("findBySession failed", re);
			throw re;
		}
	}

}
