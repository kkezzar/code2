package dz.gov.mesrs.sii.commons.data.dao.impl.fve.examen;

import java.util.ArrayList;
import java.util.Date;
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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.EvaluationControleContinuDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus.VoeuEtudiantDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AssociationGroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.EvaluationControleContinu;

/**
 * Dao object for domain model class EvaluationControleContinu;
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.EvaluationControleContinu;
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Repository("evaluationControleContinuDao")
@Transactional
public class EvaluationControleContinuDaoImpl implements
		EvaluationControleContinuDao {

	private static final Logger log = LoggerFactory.getLogger(EvaluationControleContinuDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.EvaluationControleContinuDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.
	 *      EvaluationControleContinu)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(EvaluationControleContinu transientInstance) {
		log.debug("persisting EvaluationControleContinu instance");
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
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.EvaluationControleContinuDao#remove(dz.gov.mesrs.sii.commons.data.model.fve.examen.lmd.business.model.bo.cursus.EvaluationControleContinu)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(EvaluationControleContinu persistentInstance) {
		log.debug("removing EvaluationControleContinu instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.EvaluationControleContinuDao#merge(dz.gov.mesrs.sii.commons.data.model.fve.examen.fve.business.model.bo.cursus.EvaluationControleContinu)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public EvaluationControleContinu merge(
			EvaluationControleContinu detachedInstance) {
		log.debug("merging EvaluationControleContinu instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.EvaluationControleContinuDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public EvaluationControleContinu findById(long id) {
		log.debug("getting EvaluationControleContinu instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(EvaluationControleContinu.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<EvaluationControleContinu> findAll() {
		log.debug("getting all EvaluationControleContinu instances");
		try {
			Query query = entityManager
					.createQuery("from EvaluationControleContinu evaluationControleContinu");
			log.debug("findAll 'EvaluationControleContinu' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'EvaluationControleContinu' failed", re);
			return new ArrayList<EvaluationControleContinu>();
		}
	}

	@Override
	public List<EvaluationControleContinu> findAdvanced(
			EvaluationControleContinu evalCC) {
		try {
			if (evalCC == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from EvaluationControleContinu r ");
			request.append(" where (1=1) ");
			if (evalCC.getOof() != null && evalCC.getOof().getId() != 0) {
				request.append(" and r.oof.id = " + evalCC.getOof().getId());
			}
			if (evalCC.getPeriode() != null && evalCC.getPeriode().getId() != 0) {
				request.append(" and r.periode.id = "
						+ evalCC.getPeriode().getId());
			}
			AssociationGroupePedagogique _assoc = evalCC
					.getAssociationGroupePedagogique();
			if (_assoc != null) {
				if (_assoc.getId() != 0) {
					request.append(" and r.associationGroupePedagogique.id = "
							+ _assoc.getId());
				}
				if (_assoc.getGroupePedagogique() != null
						&& _assoc.getGroupePedagogique().getId() != 0) {
					request.append(" and r.associationGroupePedagogique.groupePedagogique.id = "
							+ _assoc.getGroupePedagogique().getId());
				}
				if (_assoc.getAp() != null && _assoc.getAp().getId() != 0) {
					request.append(" and r.associationGroupePedagogique.ap.id = "
							+ _assoc.getAp().getId());
				}
				if (_assoc.getRattachementMc() != null
						&& _assoc.getRattachementMc().getId() != 0) {
					request.append(" and r.associationGroupePedagogique.rattachementMc.id = "
							+ _assoc.getRattachementMc().getId());
				}
			}
			if (evalCC.getNomenclature() != null
					&& evalCC.getNomenclature().getId() != 0) {
				request.append(" and r.nomenclature.id = "
						+ evalCC.getNomenclature().getId());
			}
			if (evalCC.getAnneeAcademique() != null
					&& evalCC.getAnneeAcademique().getId() != 0) {
				request.append(" and r.anneeAcademique.id = "
						+ evalCC.getAnneeAcademique().getId());
			}

			if (evalCC.getSituationEntite() != null
					&& evalCC.getSituationEntite().getId() != 0) {
				request.append(" and r.situationEntite.id = "
						+ evalCC.getSituationEntite().getId());
			}
			request.append(" order by r.dateEvaluation desc");
			TypedQuery<EvaluationControleContinu> query = entityManager
					.createQuery(new String(request),
							EvaluationControleContinu.class);
			List<EvaluationControleContinu> resultList = (List<EvaluationControleContinu>) query
					.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			throw re;
		}
	}

	@Override
	public EvaluationControleContinu findByGpIdAndDateEval(Long evalId,
			Integer gpId, Date dateEval) {
		try {
			if (gpId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from EvaluationControleContinu r ");
			request.append(" where r.associationGroupePedagogique.groupePedagogique.id = "
					+ gpId);
			if (evalId != null) {
				request.append(" and r.id != " + evalId);
			}

			if (dateEval != null) {
				request.append(" and r.dateEvaluation  =:dateEval ");
			}
			TypedQuery<EvaluationControleContinu> query = entityManager
					.createQuery(new String(request),
							EvaluationControleContinu.class);
			query.setParameter("dateEval", dateEval);
			List<EvaluationControleContinu> resultList = (List<EvaluationControleContinu>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findByGpIdAndDateEval successful");
			return resultList.get(0);

		} catch (RuntimeException re) {
			log.error("findByGpIdAndDateEval failed", re);
			throw re;
		}
	}

	@Override
	public List<EvaluationControleContinu> findByGpAssociation(Long evalId,
			Integer gpAssocId) {
		try {
			if (gpAssocId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from EvaluationControleContinu r ");
			request.append(" where r.associationGroupePedagogique.id = "
					+ gpAssocId);
			if (evalId != null) {
				request.append(" and r.id != " + evalId);
			}

			TypedQuery<EvaluationControleContinu> query = entityManager
					.createQuery(new String(request),
							EvaluationControleContinu.class);
			List<EvaluationControleContinu> resultList = (List<EvaluationControleContinu>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findByGpAssociation successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByGpAssociation failed", re);
			throw re;
		}
	}
}
