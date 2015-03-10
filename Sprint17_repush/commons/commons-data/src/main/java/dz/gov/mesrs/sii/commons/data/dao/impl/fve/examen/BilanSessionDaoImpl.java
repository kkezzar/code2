/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.BilanSessionDaoImpl.java] 
 * @author MAKERRI Sofiane on : 22 oct. 2014  15:48:03
 */
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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.BilanSessionDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanSession;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:48:03
 */
@Repository("bilanSessionDao")
public class BilanSessionDaoImpl implements BilanSessionDao {

	/**
	 * [BilanSessionDaoImpl.BilanSessionDaoImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:48:03
	 */
	private static final Logger log = LoggerFactory
			.getLogger(BilanSessionDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public BilanSessionDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(BilanSession transientInstance) {
		log.debug("persisting BilanSession instance");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.persistence.cursus.BilanControleContinuDao
	 * #remove (dz.gov.mesrs.sii.fve.business.model.bo.cursus.BilanSession)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(BilanSession persistentInstance) {
		log.debug("removing BilanSession instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.persistence.cursus.BilanControleContinuDao
	 * #merge (dz.gov.mesrs.sii.fve.business.model.bo.cursus.BilanSession)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public BilanSession merge(BilanSession detachedInstance) {
		log.debug("merging BilanSession instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.persistence.cursus.BilanControleContinuDao
	 * #findById(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public BilanSession findById(long id) {
		log.debug("getting BilanSession instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(BilanSession.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.persistence.cursus.BilanControleContinuDao
	 * #findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<BilanSession> findAll() {
		log.debug("getting all BilanSession instances");
		try {
			Query query = entityManager
					.createQuery("from BilanSession bilanControleContinu");
			log.debug("findAll 'BilanSession' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'BilanSession' failed", re);
			return new ArrayList<BilanSession>();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public BilanSession findUnique(Long deliberationId, Integer diaId, int type) {
		try {

			if (deliberationId == null || diaId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from BilanSession r ");
			request.append(" where (1=1) ");

			request.append(" and r.deliberationSession.id = " + deliberationId);
			request.append(" and r.dossierInscriptionAdministrative.id = "
					+ diaId);
			request.append(" and r.type = " + type);
			TypedQuery<BilanSession> query = entityManager.createQuery(
					new String(request), BilanSession.class);
			List<BilanSession> resultList = (List<BilanSession>) query
					.getResultList();
			log.debug("findUnique successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList.get(0);

		} catch (RuntimeException re) {
			log.error("findUnique failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<BilanSession> findByDeliberation(Long deliberationId) {
		try {

			if (deliberationId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from BilanSession r ");
			request.append(" where (1=1) ");
			request.append(" and r.deliberationSession.id = " + deliberationId);
			TypedQuery<BilanSession> query = entityManager.createQuery(
					new String(request), BilanSession.class);
			List<BilanSession> resultList = (List<BilanSession>) query
					.getResultList();
			log.debug("findByDeliberation successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByDeliberation failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<BilanSession> findByOofAndNiveau(Integer oofId, Integer niveauId) {
		try {

			if (oofId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from BilanSession r ");
			request.append(" where (1=1) ");
			request.append(" and r.deliberationSession.planningSession.ouvertureOffreFormation.id = "
					+ oofId);
			if (niveauId != null) {
				request.append(" and r.deliberationSession.planningSession.niveau.id = "
						+ niveauId);
			}
			TypedQuery<BilanSession> query = entityManager.createQuery(
					new String(request), BilanSession.class);
			List<BilanSession> resultList = (List<BilanSession>) query
					.getResultList();
			log.debug("findByOofAndNiveau successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByOofAndNiveau failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<BilanSession> findByPeriode(Integer oofId, Integer periodeId) {
		try {
			if (oofId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from BilanSession r ");
			request.append(" where (1=1) ");
			request.append(" and r.oof.id = " + oofId);
			if (periodeId != null) {
				request.append(" and r.periode.id = " + periodeId);
			}
			// request.append(" and r.type = " +
			// UtilConstant.BILAN_TYPE_SESSION);
			TypedQuery<BilanSession> query = entityManager.createQuery(
					new String(request), BilanSession.class);
			List<BilanSession> resultList = (List<BilanSession>) query
					.getResultList();
			log.debug("findByPeriode successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByPeriode failed", re);
			throw re;
		}
	}

	/**
	 * Recherche avancee dans la table des bilans annuel
	 * 
	 * @author Mounir.MESSAOUDI on : 26 oct. 2014 11:29:27
	 * @param searchBo
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BilanSession> findAdvanced(BilanSession searchBo) {
		try {
			if (searchBo == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from BilanSession r ");
			request.append(" where (1=1) ");
			if (searchBo.getOof() != null && searchBo.getOof().getId() != 0) {
				request.append(" and r.oof.id = " + searchBo.getOof().getId());
			}
			if (searchBo.getOof() != null
					&& searchBo.getOof().getAnneeAcademique() != null
					&& searchBo.getOof().getAnneeAcademique().getId() != 0) {
				request.append(" and r.oof.anneeAcademique.id = "
						+ searchBo.getOof().getAnneeAcademique().getId());
			}
			if (searchBo.getPeriode() != null
					&& searchBo.getPeriode().getId() != 0) {
				request.append(" and r.periode.id = "
						+ searchBo.getPeriode().getId());
			}
			if (searchBo.getPeriode() != null
					&& searchBo.getPeriode().getId() != 0) {
				request.append(" and r.periode.id = "
						+ searchBo.getPeriode().getId());
			}

			request.append(" and r.type = " + searchBo.getType());

			if (searchBo.getDeliberationSession() != null
					&& searchBo.getDeliberationSession().getId() != 0) {
				request.append(" and r.deliberationSession.id = "
						+ searchBo.getDeliberationSession().getId());
			}

			if (searchBo.getDeliberationSession() != null
					&& searchBo.getDeliberationSession().getPlanningSession() != null
					&& searchBo.getDeliberationSession().getPlanningSession()
							.getId() != 0) {
				request.append(" and r.deliberationSession.planningSession.id = "
						+ searchBo.getDeliberationSession()
								.getPlanningSession().getId());
			}
			if (searchBo.getDossierInscriptionAdministrative() != null
					&& searchBo.getDossierInscriptionAdministrative().getId() != 0) {
				request.append(" and r.dossierInscriptionAdministrative.id = "
						+ searchBo.getDossierInscriptionAdministrative()
								.getId());
			}
			request.append(" order by r.deliberationSession.planningSession.dateDebut ");
			TypedQuery<BilanSession> query = entityManager.createQuery(
					new String(request), BilanSession.class);
			List<BilanSession> resultList = (List<BilanSession>) query
					.getResultList();
			log.debug("findAdvanced successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			throw re;
		}
	}

	/**
	 * Rechercher le bilan final par id dossier inscription administrative
	 * 
	 * @author Mounir.MESSAOUDI on : 26 oct. 2014 11:33:11
	 * @param diaId
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public BilanSession findBilanFinalByIdDossierInscrAdmin(Integer diaId) {
		try {

			if (diaId == null)
				return null;

			StringBuilder request = new StringBuilder(
					"SELECT r from BilanSession r ");
			request.append(" WHERE r.bilanFinal = TRUE AND r.dossierInscriptionAdministrative.id = "
					+ diaId);
			TypedQuery<BilanSession> query = entityManager.createQuery(
					new String(request), BilanSession.class);
			List<BilanSession> resultList = (List<BilanSession>) query
					.getResultList();
			log.debug("findUnique successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList.get(0);

		} catch (RuntimeException re) {
			log.error("findByDossierInscriptionAdministrative failed", re);

			throw re;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<BilanSession> findBilanPeriodeByNiveau(Integer oofId,
			Integer niveauId) {
		try {
			if (oofId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from BilanSession r ");
			request.append(" where (1=1) ");
			request.append(" and r.oof.id = " + oofId);
			if (niveauId != null) {
				request.append(" and r.periode.niveau.id = " + niveauId);
			}
			request.append(" and r.type = " + UtilConstant.BILAN_TYPE_PERIODE);
			TypedQuery<BilanSession> query = entityManager.createQuery(
					new String(request), BilanSession.class);
			List<BilanSession> resultList = (List<BilanSession>) query
					.getResultList();
			log.debug("findByPeriode successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByPeriode failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<BilanSession> findBilanFinal(Integer oofId, Integer niveauId) {
		try {
			if (oofId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from BilanSession r ");
			request.append(" where (1=1) ");
			request.append(" and r.oof.id = " + oofId);
			if (niveauId != null) {
				request.append(" and r.deliberationSession.planningSession.periode.niveau.id = "
						+ niveauId);
			}
			request.append(" and r.type = " + UtilConstant.BILAN_TYPE_ANNUEL);
			request.append(" and r.bilanFinal = TRUE ");
			request.append(" order by r.deliberationSession.dateDeliberation desc");
			TypedQuery<BilanSession> query = entityManager.createQuery(
					new String(request), BilanSession.class);
			List<BilanSession> resultList = (List<BilanSession>) query
					.getResultList();
			log.debug("findByPeriode successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByPeriode failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<BilanSession> findByOffAnnee(Integer anneeId, Integer oofId) {
		log.debug("getting all BilanSession instances");
		try {
			StringBuilder request = new StringBuilder(
					"select r from BilanSession r ");
			log.debug("findAll 'BilanSession' successful");
			TypedQuery<BilanSession> query = entityManager.createQuery(
					new String(request), BilanSession.class);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'BilanSession' failed", re);
			return new ArrayList<BilanSession>();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<BilanSession> findBilanPeriode(Integer oofId, Integer periodeId) {
		try {
			if (oofId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from BilanSession r ");
			request.append(" where (1=1) ");
			request.append(" and r.oof.id = " + oofId);
			if (periodeId != null) {
				request.append(" and r.periode.id = " + periodeId);
			}
			request.append(" and r.type = " + UtilConstant.BILAN_TYPE_PERIODE);
			TypedQuery<BilanSession> query = entityManager.createQuery(
					new String(request), BilanSession.class);
			List<BilanSession> resultList = (List<BilanSession>) query
					.getResultList();
			log.debug("findByPeriode successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			for (BilanSession _bilanSession : resultList) {
				entityManager.flush();
				entityManager.refresh(_bilanSession);
			}
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByPeriode failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<BilanSession> findBilanFinalDiplomeNonGenerer(Integer oofId,
			Integer niveauId) {
		try {
			if (oofId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from BilanSession r ");
			request.append(" where (1=1) ");
			request.append(" and r.oof.id = " + oofId);
			if (niveauId != null) {
				request.append(" and r.deliberationSession.planningSession.periode.niveau.id = "
						+ niveauId);
			}
			request.append(" and r.type = " + UtilConstant.BILAN_TYPE_ANNUEL);
			request.append(" and r.bilanFinal = TRUE ");
			request.append(" and r.typeDecision.id = 579800 ");
			request.append(" and r.id NOT IN ");
			request.append(" (select g.bilanSession.id from DiplomeFinEtude g) ");
			request.append(" order by r.deliberationSession.dateDeliberation desc");
			TypedQuery<BilanSession> query = entityManager.createQuery(
					new String(request), BilanSession.class);
			List<BilanSession> resultList = (List<BilanSession>) query
					.getResultList();
			log.debug("findByPeriode successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByPeriode failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<BilanSession> findByDeliberationAnDia(Long deliberationId,
			Integer diaId) {
		try {

			if (deliberationId == null || diaId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from BilanSession r ");
			request.append(" where r.dossierInscriptionAdministrative.id = "
					+ diaId);
			request.append(" and r.deliberationSession.id = " + deliberationId);
			TypedQuery<BilanSession> query = entityManager.createQuery(
					new String(request), BilanSession.class);
			List<BilanSession> resultList = (List<BilanSession>) query
					.getResultList();
			log.debug("findByDeliberationAnDia successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByDeliberationAnDia failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.cursus.BilanSessionDao#findBilanSession
	 * (java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BilanSession> findBilanSession(Integer oofId, Integer periodeId) {
		try {
			if (oofId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from BilanSession r ");
			request.append(" where (1=1) ");
			request.append(" and r.oof.id = " + oofId);
			if (periodeId != null) {
				request.append(" and r.periode.id = " + periodeId);
			}
			request.append(" and r.type = " + UtilConstant.BILAN_TYPE_SESSION);
			TypedQuery<BilanSession> query = entityManager.createQuery(
					new String(request), BilanSession.class);
			List<BilanSession> resultList = (List<BilanSession>) query
					.getResultList();
			log.debug("findBilanSession successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList;

		} catch (RuntimeException re) {
			log.error("findBilanSession failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.cursus.BilanSessionDao#findBilanAnnuel
	 * (java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BilanSession> findBilanAnnuel(Integer oofId, Integer niveauId) {
		try {
			if (oofId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from BilanSession r ");
			request.append(" where (1=1) ");
			request.append(" and r.oof.id = " + oofId);
			if (niveauId != null) {
				request.append(" and r.deliberationSession.planningSession.periode.niveau.id = "
						+ niveauId);
			}
			request.append(" and r.type = " + UtilConstant.BILAN_TYPE_ANNUEL);
			request.append(" order by r.deliberationSession.dateDeliberation desc");
			TypedQuery<BilanSession> query = entityManager.createQuery(
					new String(request), BilanSession.class);
			List<BilanSession> resultList = (List<BilanSession>) query
					.getResultList();
			log.debug("findBilanAnnuel successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			for (BilanSession _bilanSession : resultList) {
				entityManager.flush();
				entityManager.refresh(_bilanSession);
			}
			return resultList;

		} catch (RuntimeException re) {
			log.error("findBilanAnnuel failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.BilanSessionDao#
	 * findBilanFinCycle(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BilanSession> findBilanFinCycle(Integer oofId, Integer niveauId) {
		try {
			if (oofId == null || niveauId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from BilanSession r ");
			request.append(" where (1=1) ");
			request.append(" and r.oof.id = " + oofId);
			request.append(" and r.periode.niveau.id = " + niveauId);
			//request.append(" and lower(r.deliberationSession..situation.situation.code)=:situationCode ");
			request.append(" and r.type = " + UtilConstant.BILAN_TYPE_ANNUEL);
			request.append(" and (lower(r.typeDecision.code)=:decisionAdmisCode ");
			request.append(" or lower(r.typeDecision.code)=:decisionVldCycleCode )");
			TypedQuery<BilanSession> query = entityManager.createQuery(
					new String(request), BilanSession.class);
//			query.setParameter("situationCode",
//					UtilConstant.SITUTAION_CLOTUREE_CODE.toLowerCase());
			query.setParameter("decisionAdmisCode",
					UtilConstant.NC_LMD_TYPE_DECISION_ADMISSION_ADMIS_VALUE
							.toLowerCase());
			query.setParameter(
					"decisionVldCycleCode",
					UtilConstant.NC_LMD_TYPE_DECISION_ADMISSION_VALIDATION_CYCLE_VALUE
							.toLowerCase());

			List<BilanSession> resultList = (List<BilanSession>) query
					.getResultList();
			log.debug("findBilanFinCycle successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			for (BilanSession _bilanSession : resultList) {
				entityManager.flush();
				entityManager.refresh(_bilanSession);
			}
			return resultList;

		} catch (RuntimeException re) {
			log.error("findBilanFinCycle failed", re);
			throw re;
		}
	}

}
