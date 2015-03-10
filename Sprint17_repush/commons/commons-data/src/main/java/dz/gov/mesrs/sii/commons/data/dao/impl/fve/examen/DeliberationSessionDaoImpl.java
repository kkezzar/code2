/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.DeliberationSessionDaoImpl.java] 
 * @author MAKERRI Sofiane on : 16 oct. 2014  17:18:26
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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.DeliberationSessionDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus.VoeuEtudiantDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.DeliberationSession;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.PlanningSession;

/**
 * @author MAKERRI Sofiane on : 16 oct. 2014 17:18:26
 */
@Repository("deliberationSessionDao")
@Transactional
public class DeliberationSessionDaoImpl implements DeliberationSessionDao {

	/**
	 * [DeliberationSessionDaoImpl.DeliberationSessionDaoImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:18:26
	 */
	private static final Logger log = LoggerFactory.getLogger(DeliberationSessionDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public DeliberationSessionDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.persistence.cursus.DeliberationSessionDao
	 * #persist
	 * (dz.gov.mesrs.sii.fve.business.model.bo.cursus.DeliberationSession)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(DeliberationSession transientInstance) {
		log.debug("persisting DeliberationSession instance");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.persistence.cursus.DeliberationSessionDao
	 * #remove
	 * (dz.gov.mesrs.sii.fve.business.model.bo.cursus.DeliberationSession)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(DeliberationSession persistentInstance) {
		log.debug("removing DeliberationSession instance");
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
	 * dz.gov.mesrs.sii.fve.business.persistence.cursus.DeliberationSessionDao
	 * #merge
	 * (dz.gov.mesrs.sii.fve.business.model.bo.cursus.DeliberationSession)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DeliberationSession merge(DeliberationSession detachedInstance) {
		log.debug("merging DeliberationSession instance");
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
	 * dz.gov.mesrs.sii.fve.business.persistence.cursus.DeliberationSessionDao
	 * #findById(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public DeliberationSession findById(int id) {
		log.debug("getting DeliberationSession instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(DeliberationSession.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.persistence.cursus.DeliberationSessionDao
	 * #findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<DeliberationSession> findAll() {
		log.debug("getting all DeliberationSession instances");
		try {
			Query query = entityManager.createQuery("from DeliberationSession r");
			log.debug("findAll 'DeliberationSession' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'DeliberationSession' failed", re);
			return new ArrayList<DeliberationSession>();
		}
	}

	@Override
	public DeliberationSession findByPlanningId(Long planningId) {
		try {

			if (planningId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder("select r from DeliberationSession r ");
			request.append(" where r.planningSession.id  = " + planningId);
			TypedQuery<DeliberationSession> query = entityManager.createQuery(new String(request),
					DeliberationSession.class);
			List<DeliberationSession> resultList = (List<DeliberationSession>) query.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findByPlanningId successful");
			return resultList.get(0);

		} catch (RuntimeException re) {
			log.error("findByPlanningId failed", re);
			throw re;
		}
	}

	@Override
	public List<DeliberationSession> findAdvanced(DeliberationSession serachBo) {
		try {

			if (serachBo == null) {
				return null;
			}
			StringBuilder request = new StringBuilder("select r from DeliberationSession r ");
			request.append(" where (1=1)");
			PlanningSession ps = serachBo.getPlanningSession();
			if (ps != null) {
				if (ps.getId() != 0) {
					request.append(" and r.planningSession.id  = " + ps.getId());
				}
				if (ps.getPeriode() != null && ps.getPeriode().getId() != 0) {
					request.append(" and r.planningSession.periode.id  = " + ps.getPeriode().getId());
				}
				if (ps.getAnneeAcademique() != null && ps.getAnneeAcademique().getId() != 0) {
					request.append(" and r.planningSession.anneeAcademique.id  = " + ps.getAnneeAcademique().getId());
				}
				if (ps.getOuvertureOffreFormation() != null && ps.getOuvertureOffreFormation().getId() != 0) {
					request.append(" and r.planningSession.ouvertureOffreFormation.id  = "
							+ ps.getOuvertureOffreFormation().getId());
				}
			}
			if (serachBo.getSituation() != null && serachBo.getSituation().getId() != 0) {
				request.append(" and r.situation.id  != " + serachBo.getSituation().getId());
			}

			if (serachBo.getBilanPeriode()) {
				request.append(" and r.bilanPeriode  = TRUE ");
			}

			if (serachBo.getBilanAnnuel()) {
				request.append(" and r.bilanAnnuel  = TRUE ");
			}

			request.append(" order by r.dateDeliberation asc ");
			TypedQuery<DeliberationSession> query = entityManager.createQuery(new String(request),
					DeliberationSession.class);
			List<DeliberationSession> resultList = (List<DeliberationSession>) query.getResultList();
			log.debug("findByPlanningId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByPlanningId failed", re);
			throw re;
		}
	}

	@Override
	public List<DeliberationSession> findBilanPeriode(Integer anneeId, int oofId, Integer niveauId) {
		try {

			StringBuilder request = new StringBuilder("select r from DeliberationSession r ");
			request.append(" where (1=1)");
			if (niveauId != null) {
				request.append(" and r.planningSession.periode.niveau.id  = " + niveauId);
			}
			if (anneeId != null) {
			request.append(" and r.planningSession.anneeAcademique.id  = " + anneeId);
			}
			request.append(" and r.planningSession.ouvertureOffreFormation.id  = " + oofId);

			request.append(" and r.bilanPeriode  = TRUE");

			TypedQuery<DeliberationSession> query = entityManager.createQuery(new String(request),
					DeliberationSession.class);
			List<DeliberationSession> resultList = (List<DeliberationSession>) query.getResultList();
			log.debug("findBilanPeriode successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findBilanPeriode failed", re);
			throw re;
		}
	}

	@Override
	public DeliberationSession bilanPeriodeExist(int anneeId, int oofId, int periodeId) {
		try {

			StringBuilder request = new StringBuilder("select r from DeliberationSession r ");
			request.append(" where (1=1)");

			request.append(" and r.planningSession.periode.id  = " + periodeId);

			request.append(" and r.planningSession.anneeAcademique.id  = " + anneeId);
			request.append(" and r.planningSession.ouvertureOffreFormation.id  = " + oofId);

			request.append(" and r.bilanPeriode  = TRUE");

			TypedQuery<DeliberationSession> query = entityManager.createQuery(new String(request),
					DeliberationSession.class);
			List<DeliberationSession> resultList = (List<DeliberationSession>) query.getResultList();
			log.debug("bilanPeriodeExist successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList.get(0);

		} catch (RuntimeException re) {
			log.error("bilanPeriodeExist failed", re);
			throw re;

		}

	}

}
