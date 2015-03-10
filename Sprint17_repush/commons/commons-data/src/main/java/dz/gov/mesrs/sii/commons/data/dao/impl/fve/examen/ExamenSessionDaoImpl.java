package dz.gov.mesrs.sii.commons.data.dao.impl.fve.examen;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.ExamenSessionDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus.VoeuEtudiantDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.ExamenSession;

/**
 * 
 * @author BELDI Jamel on : 17 sept. 2014 16:51:24
 */

@Repository("examenSessionDao")
@Transactional
public class ExamenSessionDaoImpl implements ExamenSessionDao {

	private static final Logger log = LoggerFactory
			.getLogger(ExamenSessionDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.ExamenSessionDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.
	 *      ExamenSession)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(ExamenSession transientInstance) {
		log.debug("persisting ExamenSession instance");
		try {
			entityManager.persist(transientInstance);
			entityManager.flush();
			entityManager.refresh(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.ExamenSessionDao#remove(dz.gov.mesrs.sii.commons.data.model.fve.examen.ExamenSessionDto.business.model.bo.cursus.ExamenSession)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(ExamenSession persistentInstance) {
		log.debug("removing ExamenSession instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.ExamenSessionDao#merge(dz.gov.mesrs.sii.commons.data.model.fve.examen.fve.business.model.bo.cursus.ExamenSession)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ExamenSession merge(ExamenSession detachedInstance) {
		log.debug("merging ExamenSession instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.ExamenSessionDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public ExamenSession findById(long id) {
		log.debug("getting ExamenSession instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(ExamenSession.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<ExamenSession> findAll() {
		log.debug("getting all ExamenSession instances");
		try {
			Query query = entityManager
					.createQuery("from ExamenSession examenSession");
			log.debug("findAll 'ExamenSession' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'ExamenSession' failed", re);
			return new ArrayList<ExamenSession>();
		}
	}

	@Override
	public List<ExamenSession> findBySession(long idSession) {
		log.debug("getting all findByYearAndOfAndLevelAndPeriod instances");
		try {
			if (idSession == 0) {
				return null;
			}

			String sqlQuery = "SELECT e FROM ExamenSession e WHERE e.planningSession.id=:idSession order by e.dateExamen ";

			TypedQuery<ExamenSession> query = entityManager.createQuery(
					sqlQuery, ExamenSession.class);
			query.setParameter("idSession", idSession);

			List<ExamenSession> result = query.getResultList();
			if (result != null) {
				for (ExamenSession examenSession : result) {
					entityManager.flush();
					entityManager.refresh(examenSession);
				}
			}
			return result;

		} catch (RuntimeException re) {
			log.error("findByYearAndOfAndLevelAndPeriod failed", re);
			throw re;
		}

	}

	@Override
	public List<ExamenSession> findAdvanced(ExamenSession examenSession) {
		try {
			if (examenSession == null) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(" select e from ExamenSession e ");
			sqlQuery.append(" where (1=1) ");

			if (examenSession.getPlanningSession() != null) {
				if (examenSession.getPlanningSession().getId() != 0) {
					sqlQuery.append(" and e.planningSession.id = "
							+ examenSession.getPlanningSession().getId());
				}
				if (examenSession.getPlanningSession().getAnneeAcademique() != null
						&& examenSession.getPlanningSession()
								.getAnneeAcademique().getId() != 0) {
					sqlQuery.append(" and e.planningSession.anneeAcademique.id = "
							+ examenSession.getPlanningSession()
									.getAnneeAcademique().getId());
				}
				if (examenSession.getPlanningSession().getNcTypeSession() != null
						&& examenSession.getPlanningSession()
								.getNcTypeSession().getId() != 0) {
					sqlQuery.append(" and e.planningSession.ncTypeSession.id = "
							+ examenSession.getPlanningSession()
									.getNcTypeSession().getId());
				}
				if (examenSession.getPlanningSession()
						.getOuvertureOffreFormation() != null
						&& examenSession.getPlanningSession()
								.getOuvertureOffreFormation().getId() != 0) {
					sqlQuery.append(" and e.planningSession.ouvertureOffreFormation.id = "
							+ examenSession.getPlanningSession()
									.getOuvertureOffreFormation().getId());
				}
				if (examenSession.getPlanningSession().getNiveau() != null
						&& examenSession.getPlanningSession().getNiveau()
								.getId() != 0) {
					sqlQuery.append(" and e.planningSession.niveau.id = "
							+ examenSession.getPlanningSession().getNiveau()
									.getId());
				}
				if (examenSession.getPlanningSession().getPeriode() != null
						&& examenSession.getPlanningSession().getPeriode()
								.getId() != 0) {
					sqlQuery.append(" and e.planningSession.periode.id = "
							+ examenSession.getPlanningSession().getPeriode()
									.getId());
				}
			}

			if (examenSession.getRattachementMc() != null) {
				if (examenSession.getRattachementMc().getId() != 0) {
					sqlQuery.append(" and e.rattachementMc.id = "
							+ examenSession.getRattachementMc().getId());
				}

			}

			if (examenSession.getDateExamen() != null) {
				sqlQuery.append(" and e.dateExamen ='"
						+ examenSession.getDateExamen().toString() + "'");
			}

			if (examenSession.getHeureDebut() != null) {
				sqlQuery.append(" and e.heureDebut = :heureDebut");
			}

			if (examenSession.getRefTypeExamen() != null) {
				sqlQuery.append(" and e.refTypeExamen = "
						+ examenSession.getRefTypeExamen());
			}

			sqlQuery.append(" order by e.dateExamen ");

			log.info(sqlQuery.toString());
			TypedQuery<ExamenSession> query = entityManager.createQuery(
					new String(sqlQuery), ExamenSession.class);
			if (examenSession.getHeureDebut() != null) {
				query.setParameter("heureDebut", examenSession.getHeureDebut(),
						TemporalType.TIMESTAMP);
			}

			List<ExamenSession> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			throw re;
		}
	}

	@Override
	public List<ExamenSession> findExamenByPlanning(Long planningId) {
		log.debug("getting all findExamenByPlanning instances");
		try {
			if (planningId == null || planningId == 0) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(" select distinct r from ExamenSession r ");
			sqlQuery.append(" where r.planningSession.id = " + planningId);
			// sqlQuery.append(" order by r.rattachementMc.matiereConstitutive.libelleFr ");
			TypedQuery<ExamenSession> query = entityManager.createQuery(
					new String(sqlQuery), ExamenSession.class);
			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findExamenByPlanning failed", re);
			throw re;
		}
	}
}
