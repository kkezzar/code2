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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.InscriptionExamenDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus.VoeuEtudiantDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.InscriptionExamen;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.SalleExamen;

/**
 * 
 * @author BELDI Jamel on : 17 sept. 2014 16:51:40
 */

@Repository("inscriptionExamenDao")
@Transactional
public class InscriptionExamenDaoImpl implements InscriptionExamenDao {

	private static final Logger log = LoggerFactory.getLogger(InscriptionExamenDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.InscriptionExamenDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.
	 *      InscriptionExamen)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(InscriptionExamen transientInstance) {
		log.debug("persisting InscriptionExamen instance");
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
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.InscriptionExamenDao#remove(dz.gov.mesrs.sii.commons.data.model.fve.examen.InscriptionExamenDto.business.model.bo.cursus.InscriptionExamen)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(InscriptionExamen persistentInstance) {
		log.debug("removing InscriptionExamen instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.InscriptionExamenDao#merge(dz.gov.mesrs.sii.commons.data.model.fve.examen.fve.business.model.bo.cursus.InscriptionExamen)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public InscriptionExamen merge(InscriptionExamen detachedInstance) {
		log.debug("merging InscriptionExamen instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
			// entityManager.flush();
			// entityManager.refresh(detachedInstance);
			// return detachedInstance;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.InscriptionExamenDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public InscriptionExamen findById(long id) {
		log.debug("getting InscriptionExamen instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(InscriptionExamen.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<InscriptionExamen> findAll() {
		log.debug("getting all InscriptionExamen instances");
		try {
			Query query = entityManager
					.createQuery("from InscriptionExamen inscriptionExamen");
			log.debug("findAll 'InscriptionExamen' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'InscriptionExamen' failed", re);
			return new ArrayList<InscriptionExamen>();
		}
	}

	@Override
	public List<InscriptionExamen> findBySalleExamen(int idSalleExamen) {
		log.debug("getting all findByExamen instances");
		try {
			if (idSalleExamen == 0) {
				return null;
			}
			String sqlQuery = "SELECT e FROM InscriptionExamen e WHERE e.salleExamen.id=:idSalleExamen";

			TypedQuery<InscriptionExamen> query = entityManager.createQuery(
					sqlQuery, InscriptionExamen.class);
			query.setParameter("idSalleExamen", idSalleExamen);

			List<InscriptionExamen> result = query.getResultList();

			return result;

		} catch (RuntimeException re) {
			log.error("findByExamen failed", re);
			throw re;
		}
	}

	@Override
	public Double calculAverage(Long idExamen) {
		log.debug("calculAverage");
		try {
			if (idExamen == null) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder(
					"select avg(e.noteSession) from InscriptionExamen e ");
			sqlQuery.append(" where e.salleExamen.examenSession.id=:idExamen ");
			sqlQuery.append(" and e.absent = FALSE ");
			sqlQuery.append(" and e.copieNonRemise = FALSE ");

			TypedQuery<Double> query = entityManager.createQuery(new String(
					sqlQuery), Double.class);
			query.setParameter("idExamen", idExamen);
			return query.getResultList().get(0);

		} catch (RuntimeException re) {
			log.error("calculAverage failed", re);
			throw re;
		}
	}

	@Override
	public List<InscriptionExamen> findFailledStudent(Long idExamen,
			Double noteObtention) {
		log.debug("findFailledStudent");
		try {
			if (idExamen == null) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder(
					"select e from InscriptionExamen e ");
			sqlQuery.append(" where e.examenSession.id=:idExamen ");
			sqlQuery.append(" and e.absent = FALSE ");
			sqlQuery.append(" and e.copieNonRemise = FALSE ");
			if (noteObtention != null) {
				sqlQuery.append(" and e.noteSession <  " + noteObtention);
			}

			TypedQuery<InscriptionExamen> query = entityManager.createQuery(
					new String(sqlQuery), InscriptionExamen.class);
			query.setParameter("idExamen", idExamen);
			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findFailledStudent failed", re);
			throw re;
		}
	}

	@Override
	public List<InscriptionExamen> findByPlanning(Long idPlanning) {
		log.debug("findByPlanning");
		try {
			if (idPlanning == null) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder(
					"select e.dossierInscriptionAdministrative.id ");
			// sqlQuery.append(", sum(case   when   e.noteSession > 10 then		1		else		0		end)  ");
			sqlQuery.append(" from InscriptionExamen e ");
			sqlQuery.append(" where e.salleExamen.examenSession.planningSession.id = "
					+ idPlanning);
			sqlQuery.append(" group by e.dossierInscriptionAdministrative.id");
			// sqlQuery.append(" order by e.dossierInscriptionAdministrative.dossierEtudiant.individu.nomLatin");
			// Query query = entityManager.createQuery(new String(sqlQuery));
			TypedQuery<Integer> query = entityManager.createQuery(new String(
					sqlQuery), Integer.class);
			List<Integer> resultList = (List<Integer>) query.getResultList();
			if (resultList != null) {
				List<InscriptionExamen> result = new ArrayList<InscriptionExamen>();
				for (Integer i : resultList) {
					InscriptionExamen ie = new InscriptionExamen();
					DossierInscriptionAdministrative dia = new DossierInscriptionAdministrative();
					dia.setId(i);
					ie.setDossierInscriptionAdministrative(dia);
					result.add(ie);
				}
				return result;
			}
			return null;

		} catch (RuntimeException re) {
			log.error("findByPlanning failed", re);
			throw re;
		}
	}

	@Override
	public List<InscriptionExamen> findByExamen(Long examenSessionId) {
		try {
			if (examenSessionId == 0) {
				return null;
			}
			String sqlQuery = "SELECT e FROM InscriptionExamen e WHERE e.salleExamen.examenSession.id=:examenSessionId";

			TypedQuery<InscriptionExamen> query = entityManager.createQuery(
					sqlQuery, InscriptionExamen.class);
			query.setParameter("examenSessionId", examenSessionId);

			List<InscriptionExamen> result = query.getResultList();

			return result;

		} catch (RuntimeException re) {
			log.error("findByExamen failed", re);
			throw re;
		}
	}

	@Override
	public List<DossierInscriptionAdministrative> findNotSubscribedOnExamen(
			Long examenSessionId, Integer ouvertureOffreFormationId) {
		log.debug("findEtudiant instances");
		try {
			if (examenSessionId == null || ouvertureOffreFormationId == null) {
				return null;
			}

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select distinct dia from DossierInscriptionAdministrative dia");
			sqlQuery.append(" where (dia.ouvertureOffreFormation.id ="
					+ ouvertureOffreFormationId + ")");
			// sqlQuery.append(" and ie.salleExamen.examenSession.id="
			// + examenSessionId);

			sqlQuery.append(" and dia.id NOT IN");
			sqlQuery.append("(select ie.dossierInscriptionAdministrative.id from InscriptionExamen ie where ie.salleExamen.examenSession.id="
					+ examenSessionId + ")");

			TypedQuery<DossierInscriptionAdministrative> query = entityManager
					.createQuery(new String(sqlQuery),
							DossierInscriptionAdministrative.class);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findDossierInscription failed", re);
			throw re;
		}

	}

	@Override
	public List<InscriptionExamen> findNoteByPlanning(Long idPlanning) {
		log.debug("findNoteByPlanning");
		try {
			if (idPlanning == null) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder(
					"select e from InscriptionExamen e ");
			sqlQuery.append(" where e.salleExamen.examenSession.planningSession.id = "
					+ idPlanning);
			sqlQuery.append(" order by e.salleExamen.examenSession.rattachementMc.id");
			TypedQuery<InscriptionExamen> query = entityManager.createQuery(
					new String(sqlQuery), InscriptionExamen.class);
			List<InscriptionExamen> resultList = (List<InscriptionExamen>) query
					.getResultList();
			return resultList;

		} catch (RuntimeException re) {
			log.error("findNoteByPlanning failed", re);
			throw re;
		}
	}

	@Override
	public List<InscriptionExamen> findByPlanningAndStudent(Long idPlanning,
			Integer idDossierInscriptionAdministrative) {
		try {
			if (idPlanning == null
					|| idDossierInscriptionAdministrative == null) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder(
					"select e from InscriptionExamen e ");
			sqlQuery.append(" where e.salleExamen.examenSession.planningSession.id = "
					+ idPlanning);
			sqlQuery.append("and e.dossierInscriptionAdministrative.id ="
					+ idDossierInscriptionAdministrative);
			sqlQuery.append(" order by e.salleExamen.examenSession.rattachementMc.id");
			TypedQuery<InscriptionExamen> query = entityManager.createQuery(
					new String(sqlQuery), InscriptionExamen.class);
			List<InscriptionExamen> resultList = (List<InscriptionExamen>) query
					.getResultList();
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByPlanningAndStudent failed", re);
			throw re;
		}
	}

	@Override
	public List<DossierInscriptionAdministrative> findAdvancedNotSubscribedOnExamen(
			Long examenSessionId, DossierInscriptionAdministrative search) {
		log.debug("findAdvancedNotSubscribedOnExamen instances");
		try {
			if (examenSessionId == null || search == null) {
				return null;
			}

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select distinct dia from DossierInscriptionAdministrative dia");
			sqlQuery.append(" where (1=1) ");
			if (search.getOuvertureOffreFormation() != null
					&& search.getOuvertureOffreFormation().getId() != 0) {
				sqlQuery.append(" and dia.ouvertureOffreFormation.id = "
						+ search.getOuvertureOffreFormation().getId());
			}
			if (search.getAnneeAcademique() != null
					&& search.getAnneeAcademique().getId() != 0) {
				sqlQuery.append(" and dia.anneeAcademique.id = "
						+ search.getAnneeAcademique().getId());
			}

			if (search.getNiveau() != null && search.getNiveau().getId() != 0) {
				sqlQuery.append(" and dia.niveau.id = "
						+ search.getNiveau().getId());
			}
			if (search.getDossier() != null
					&& search.getDossier().getSituationEntite() != null
					&& search.getDossier().getSituationEntite().getId() != 0) {
				sqlQuery.append(" and dia.dossier.situationEntite.id = "
						+ search.getDossier().getSituationEntite().getId());
			}

			sqlQuery.append(" and dia.id NOT IN");
			sqlQuery.append("(");
			sqlQuery.append(" select ie.dossierInscriptionAdministrative.id from InscriptionExamen ie ");
			sqlQuery.append(" where ie.salleExamen.examenSession.id = "
					+ examenSessionId);
			if (search.getNiveau() != null && search.getNiveau().getId() != 0) {
				sqlQuery.append(" and  ie.salleExamen.examenSession.planningSession.periode.niveau.id = "
						+ search.getNiveau().getId());
			}
			sqlQuery.append(")");

			TypedQuery<DossierInscriptionAdministrative> query = entityManager
					.createQuery(new String(sqlQuery),
							DossierInscriptionAdministrative.class);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAdvancedNotSubscribedOnExamen failed", re);
			throw re;
		}
	}

	@Override
	public List<InscriptionExamen> findAbsentByPlanningAndRattachementMc(
			Long planningId, Integer rattachementMcId, Boolean absenceJustifie) {
		log.debug("findAbsentByPlanningAndRattachementMc instances");
		try {
			if (rattachementMcId == null || planningId == null) {
				return null;
			}

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select distinct r from InscriptionExamen r");
			sqlQuery.append(" where (r.absent = TRUE) ");
			if (absenceJustifie != null && absenceJustifie) {
				sqlQuery.append(" and (r.absenceJustifie = TRUE) ");
			}
			sqlQuery.append(" and r.salleExamen.examenSession.rattachementMc.id = "
					+ rattachementMcId);
			sqlQuery.append(" and r.salleExamen.examenSession.planningSession.id = "
					+ planningId);
			TypedQuery<InscriptionExamen> query = entityManager.createQuery(
					new String(sqlQuery), InscriptionExamen.class);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAbsentByPlanningAndRattachementMc failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.cursus.InscriptionExamenDao#
	 * findAdvanced
	 * (dz.gov.mesrs.sii.commons.data.model.fve.cursus.InscriptionExamen)
	 */
	@Override
	public List<InscriptionExamen> findAdvanced(InscriptionExamen search) {
		log.debug("findAdvanced instances");
		try {
			if (search == null) {
				return null;
			}

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select distinct r from InscriptionExamen r");
			sqlQuery.append(" where (1=1) ");
			if (search.getDossierInscriptionAdministrative() != null
					&& search.getDossierInscriptionAdministrative().getId() != 0) {
				sqlQuery.append(" and r.dossierInscriptionAdministrative.id = "
						+ search.getDossierInscriptionAdministrative().getId());
			}
			SalleExamen salleExamen = search.getSalleExamen();
			if (salleExamen != null) {
				if (salleExamen.getId() != 0) {
					sqlQuery.append(" and r.salleExamen.id = "
							+ salleExamen.getId());
				}
				if (salleExamen.getExamenSession() != null
						&& salleExamen.getExamenSession().getId() != 0) {
					sqlQuery.append(" and r.salleExamen.examenSession.id = "
							+ salleExamen.getExamenSession().getId());
				}
				if (salleExamen.getExamenSession().getPlanningSession() != null
						&& salleExamen.getExamenSession().getPlanningSession()
								.getId() != 0) {
					sqlQuery.append(" and r.salleExamen.examenSession.planningSession.id = "
							+ salleExamen.getExamenSession()
									.getPlanningSession().getId());
				}
				if (salleExamen.getExamenSession() != null
						&& salleExamen.getExamenSession().getPlanningSession()
								.getPeriode() != null
						&& salleExamen.getExamenSession().getPlanningSession()
								.getPeriode().getId() != 0) {
					sqlQuery.append(" and r.salleExamen.examenSession.planningSession.periode.id = "
							+ salleExamen.getExamenSession()
									.getPlanningSession().getPeriode().getId());
				}
				if (salleExamen.getExamenSession() != null
						&& salleExamen.getExamenSession().getPlanningSession()
								.getOuvertureOffreFormation() != null
						&& salleExamen.getExamenSession().getPlanningSession()
								.getOuvertureOffreFormation().getId() != 0) {
					sqlQuery.append(" and r.salleExamen.examenSession.planningSession.offreFormation.id = "
							+ salleExamen.getExamenSession()
									.getPlanningSession()
									.getOuvertureOffreFormation().getId());
				}
				if (salleExamen.getExamenSession().getRattachementMc() != null
						&& salleExamen.getExamenSession().getRattachementMc()
								.getId() != 0) {
					sqlQuery.append(" and r.salleExamen.examenSession.rattachementMc.id = "
							+ salleExamen.getExamenSession()
									.getRattachementMc().getId());
				}
			}

			TypedQuery<InscriptionExamen> query = entityManager.createQuery(
					new String(sqlQuery), InscriptionExamen.class);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.cursus.InscriptionExamenDao#
	 * findDiaByPlanning(java.lang.Integer)
	 */
	@Override
	public List<DossierInscriptionAdministrative> findDiaByPlanning(
			Long idPlanning) {
		log.debug("findByPlanning");
		try {
			if (idPlanning == null) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder(
					"select distinct e.dossierInscriptionAdministrative ");
			sqlQuery.append(" from InscriptionExamen e ");
			sqlQuery.append(" where e.salleExamen.examenSession.planningSession.id = "
					+ idPlanning);
			TypedQuery<DossierInscriptionAdministrative> query = entityManager
					.createQuery(new String(sqlQuery),
							DossierInscriptionAdministrative.class);
			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findDiaByPlanning failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.InscriptionExamenDao#
	 * findByPlanningId(java.lang.Long)
	 */
	@Override
	public List<InscriptionExamen> findByPlanningId(Long idPlanning) {
		log.debug("findByPlanningId");
		try {
			if (idPlanning == null) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder("select e ");
			sqlQuery.append(" from InscriptionExamen e ");
			sqlQuery.append(" where e.salleExamen.examenSession.planningSession.id = "
					+ idPlanning);

			TypedQuery<InscriptionExamen> query = entityManager.createQuery(
					new String(sqlQuery), InscriptionExamen.class);
			List<InscriptionExamen> resultList = (List<InscriptionExamen>) query
					.getResultList();
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByPlanningId failed", re);
			throw re;
		}
	}

}
