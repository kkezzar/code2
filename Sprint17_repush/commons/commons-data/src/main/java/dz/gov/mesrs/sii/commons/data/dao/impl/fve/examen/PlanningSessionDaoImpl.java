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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.PlanningSessionDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus.VoeuEtudiantDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.PlanningSession;

/**
 * 
 * @author BELDI Jamel on : 17 sept. 2014 16:51:50
 */

@Repository("planningSessionDao")
@Transactional
public class PlanningSessionDaoImpl implements PlanningSessionDao {

	private static final Logger log = LoggerFactory.getLogger(PlanningSessionDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.PlanningSessionDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.
	 *      PlanningSession)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(PlanningSession transientInstance) {
		log.debug("persisting PlanningSession instance");
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
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.PlanningSessionDao#remove(dz.gov.mesrs.sii.commons.data.model.fve.examen.PlanningSessionDto.business.model.bo.cursus.PlanningSession)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(PlanningSession persistentInstance) {
		log.debug("removing PlanningSession instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.PlanningSessionDao#merge(dz.gov.mesrs.sii.commons.data.model.fve.examen.fve.business.model.bo.cursus.PlanningSession)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PlanningSession merge(PlanningSession detachedInstance) {
		log.debug("merging PlanningSession instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.PlanningSessionDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public PlanningSession findById(long id) {
		log.debug("getting PlanningSession instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(PlanningSession.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<PlanningSession> findAll() {
		log.debug("getting all PlanningSession instances");
		try {
			Query query = entityManager
					.createQuery("from PlanningSession planningSession");
			log.debug("findAll 'PlanningSession' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'PlanningSession' failed", re);
			return new ArrayList<PlanningSession>();
		}
	}

	@Override
	public PlanningSession findByYearAndOfAndLevelAndPeriodAndTypeSession(
			int idAnneeAcademique, int idOuvertureOf, int idNiveau,
			int idPeriode, int ncTypeSessionId) {
		log.debug("getting all findByYearAndOfAndLevelAndPeriod instances");
		try {
			if (idAnneeAcademique == 0 || idOuvertureOf == 0 || idNiveau == 0
					|| idPeriode == 0 || ncTypeSessionId == 0) {
				return null;
			}
			String sqlQuery = "SELECT p FROM PlanningSession p WHERE p.anneeAcademique.id=:idAnneeAcademique and p.ouvertureOffreFormation.id=:idOuvertureOf and p.niveau.id=:idNiveau and p.periode.id=:idPeriode and p.ncTypeSession.id=:ncTypeSessionId";

			TypedQuery<PlanningSession> query = entityManager.createQuery(
					sqlQuery, PlanningSession.class);
			query.setParameter("idAnneeAcademique", idAnneeAcademique);
			query.setParameter("idOuvertureOf", idOuvertureOf);
			query.setParameter("idNiveau", idNiveau);
			query.setParameter("idPeriode", idPeriode);
			query.setParameter("ncTypeSessionId", ncTypeSessionId);
			List<PlanningSession> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result.get(0);

		} catch (RuntimeException re) {
			log.error("findByYearAndOfAndLevelAndPeriod failed", re);
			throw re;
		}
	}

	@Override
	public List<PlanningSession> findAdvanced(PlanningSession planningSession) {
		log.debug("findAdvanced instances");
		try {
			if (planningSession == null) {
				return null;
			}
			entityManager.flush();
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(" select p from PlanningSession p ");
			sqlQuery.append(" where (1=1) ");
			if (planningSession.getAnneeAcademique() != null
					&& planningSession.getAnneeAcademique().getId() != 0) {
				sqlQuery.append(" and p.anneeAcademique.id = "
						+ planningSession.getAnneeAcademique().getId());
			}
			if (planningSession.getOuvertureOffreFormation() != null) {
				if (planningSession.getOuvertureOffreFormation().getId() != 0) {
					sqlQuery.append(" and p.ouvertureOffreFormation.id = "
							+ planningSession.getOuvertureOffreFormation()
									.getId());
				}
				if (planningSession.getOuvertureOffreFormation()
						.getRefEtablissement() != null && planningSession.getOuvertureOffreFormation()
								.getRefEtablissement().getId() != 0) {
					sqlQuery.append(" and p.ouvertureOffreFormation.refEtablissement.id = '"
							+ planningSession.getOuvertureOffreFormation()
							.getRefEtablissement().getId() );
				}
			}
			if (planningSession.getNiveau() != null
					&& planningSession.getNiveau().getId() != 0) {
				sqlQuery.append(" and p.niveau.id = "
						+ planningSession.getNiveau().getId());
			}
			if (planningSession.getPeriode() != null
					&& planningSession.getPeriode().getId() != 0) {
				sqlQuery.append(" and p.periode.id = "
						+ planningSession.getPeriode().getId());
			}
			if (planningSession.getNcTypeSession() != null && planningSession.getNcTypeSession().getId() != 0) {
				sqlQuery.append(" and p.ncTypeSession.id = " +
						planningSession.getNcTypeSession().getId());
			}
			if (planningSession.getSituationEntite() != null
					&& planningSession.getSituationEntite().getId() != 0) {
				sqlQuery.append(" and p.situationEntite.id = "
						+ planningSession.getSituationEntite().getId());
			}

			sqlQuery.append(" order by p.dateDebut desc ");

			TypedQuery<PlanningSession> query = entityManager.createQuery(
					new String(sqlQuery), PlanningSession.class);
			List<PlanningSession> result = query.getResultList();
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
	public List<PlanningSession> findSessionforStudent(
			int idDossierInsciptionAdministrative) {

		try {
			if (idDossierInsciptionAdministrative == 0) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(" select distinct ie.salleExamen.examenSession.planningSession from InscriptionExamen ie ");
			sqlQuery.append(" where ie.dossierInscriptionAdministrative.id="
					+ idDossierInsciptionAdministrative);

			// sqlQuery.append(" order by p.intitule ");

			TypedQuery<PlanningSession> query = entityManager.createQuery(
					new String(sqlQuery), PlanningSession.class);
			List<PlanningSession> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result;

		} catch (RuntimeException re) {
			log.error("findSessionforStudent failed", re);
			throw re;
		}
	}


}
