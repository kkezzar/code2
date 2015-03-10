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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.NoteEvaluationControleContinuDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus.VoeuEtudiantDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AssociationGroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.EvaluationControleContinu;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.NoteEvaluationControleContinu;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AtomePedagogique;
import dz.gov.mesrs.sii.commons.data.util.NoteMoyenneAp;

/**
 * Dao object for domain model class NoteEvaluationControleContinu;
 * 
 * @see dz.gov.mesrs.sii.commons.data.model.fve.examen.fve.business.model.bo.cursus.NoteEvaluationControleContinu
 *      ;
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Repository("noteEvaluationControleContinuDao")
@Transactional
public class NoteEvaluationControleContinuDaoImpl implements
		NoteEvaluationControleContinuDao {

	private static final Logger log = LoggerFactory.getLogger(NoteEvaluationControleContinuDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.NoteEvaluationControleContinuDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.
	 *      NoteEvaluationControleContinu)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(NoteEvaluationControleContinu transientInstance) {
		log.debug("persisting NoteEvaluationControleContinu instance");
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
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.NoteEvaluationControleContinuDao#remove(dz.gov.mesrs.sii.commons.data.model.fve.examen.lmd.business.model.bo.cursus.NoteEvaluationControleContinu)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(NoteEvaluationControleContinu persistentInstance) {
		log.debug("removing NoteEvaluationControleContinu instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.NoteEvaluationControleContinuDao#merge(dz.gov.mesrs.sii.commons.data.model.fve.examen.fve.business.model.bo.cursus.NoteEvaluationControleContinu)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public NoteEvaluationControleContinu merge(
			NoteEvaluationControleContinu detachedInstance) {
		log.debug("merging NoteEvaluationControleContinu instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.fve.business.persistence.jpa.impl.cursus.NoteEvaluationControleContinuDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public NoteEvaluationControleContinu findById(long id) {
		log.debug("getting NoteEvaluationControleContinu instance with id: "
				+ id);
		try {
			log.debug("get successful");
			return entityManager.find(NoteEvaluationControleContinu.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<NoteEvaluationControleContinu> findAll() {
		log.debug("getting all NoteEvaluationControleContinu instances");
		try {
			Query query = entityManager
					.createQuery("from NoteEvaluationControleContinu noteEvaluationControleContinu");
			log.debug("findAll 'NoteEvaluationControleContinu' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'NoteEvaluationControleContinu' failed", re);
			return new ArrayList<NoteEvaluationControleContinu>();
		}
	}

	@Override
	public NoteEvaluationControleContinu findByEvalIdAndAffGp(Long evalId,
			Integer agpId) {
		if (evalId == null) {
			return null;
		}
		log.debug("findByEvalIdAndAffGp with  evalId = " + evalId
				+ " and agpId = " + agpId);
		try {
			StringBuilder request = new StringBuilder(
					"select r from NoteEvaluationControleContinu r ");

			request.append(" where r.evaluationControleContinu.id = " + evalId);
			if (agpId != null) {
				request.append(" and r.affectationGroupePedagogique.id =  "
						+ agpId);
			}
			TypedQuery<NoteEvaluationControleContinu> query = entityManager
					.createQuery(new String(request),
							NoteEvaluationControleContinu.class);

			List<NoteEvaluationControleContinu> resultList = (List<NoteEvaluationControleContinu>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findByEvalIdAndAffGp successful");
			return resultList.get(0);

		} catch (RuntimeException re) {
			log.error("findByEvalIdAndAffGp failed", re);
			throw re;
		}
	}

	@Override
	public List<NoteEvaluationControleContinu> findByEvalId(long evalId) {
		if (evalId == 0) {
			return null;
		}
		log.debug("findByEvalId with  evalId = " + evalId);
		try {
			StringBuilder request = new StringBuilder(
					"select r from NoteEvaluationControleContinu r ");

			request.append(" where r.evaluationControleContinu.id = " + evalId);

			TypedQuery<NoteEvaluationControleContinu> query = entityManager
					.createQuery(new String(request),
							NoteEvaluationControleContinu.class);

			List<NoteEvaluationControleContinu> resultList = (List<NoteEvaluationControleContinu>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findByEvalId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByEvalId failed", re);
			throw re;
		}
	}

	@Override
	public List<NoteMoyenneAp> calculMoyenne(NoteEvaluationControleContinu necc) {
		if (necc == null) {
			return null;
		}
		log.debug("calculMoyenne start");
		EvaluationControleContinu eval = necc.getEvaluationControleContinu();
		try {
			StringBuilder request = new StringBuilder(
					"select r.evaluationControleContinu.associationGroupePedagogique.ap.id, r.evaluationControleContinu.associationGroupePedagogique.ap.code, r.evaluationControleContinu.coefficient  from NoteEvaluationControleContinu r ");

			request.append(" where (1=1)");
			if (eval != null) {
				if (eval.getAnneeAcademique() != null
						&& eval.getAnneeAcademique().getId() != 0) {
					request.append(
							" and r.evaluationControleContinu.anneeAcademique.id = ")
							.append(eval.getAnneeAcademique().getId());
				}
				if (eval.getOof() != null && eval.getOof().getId() != 0) {
					request.append(" and r.evaluationControleContinu.oof.id = ")
							.append(eval.getOof().getId());
				}
				if (eval.getPeriode() != null && eval.getPeriode().getId() != 0) {
					request.append(
							" and r.evaluationControleContinu.periode.id = ")
							.append(eval.getPeriode().getId());
				}
				if (eval.getComptablise() != null) {
					if (eval.getComptablise()) {
						request.append(" and r.evaluationControleContinu.comptablise = TRUE ");
					} else {
						request.append(" and r.evaluationControleContinu.comptablise = FALSE ");
					}
				}
				if (necc.getAffectationGroupePedagogique() != null
						&& necc.getAffectationGroupePedagogique().getId() != 0) {
					request.append(" and r.affectationGroupePedagogique.id = ")
							.append(necc.getAffectationGroupePedagogique()
									.getId());
				}
				if (necc.getAffectationGroupePedagogique() != null
						&& necc.getAffectationGroupePedagogique().getGroupePedagogique() != null  && necc.getAffectationGroupePedagogique().getGroupePedagogique().getId() != 0) {
					request.append(" and r.affectationGroupePedagogique.groupePedagogique.id = ")
							.append(necc.getAffectationGroupePedagogique().getGroupePedagogique().getId());
				}
				AssociationGroupePedagogique assoc = eval
						.getAssociationGroupePedagogique();
				if (assoc != null) {
					if (assoc.getRattachementMc() != null
							&& assoc.getRattachementMc().getId() != 0) {
						request.append(
								" and r.evaluationControleContinu.associationGroupePedagogique.rattachementMc.id = ")
								.append(assoc.getRattachementMc().getId());
					}
				}
				request.append(" group by r.evaluationControleContinu.associationGroupePedagogique.ap.id");
			}
			Query query = entityManager.createQuery(new String(request));
			// TypedQuery<Object[]> query = entityManager.createQuery(
			// new String(request), Object[].class);

			List<Object[]> resultObject = (List<Object[]>) query
					.getResultList();
			// resultList = Arrays.asList(resultList);
			if (resultObject == null || resultObject.isEmpty()) {
				return null;
			}
			List<NoteMoyenneAp> resultList = new ArrayList<NoteMoyenneAp>();
			for (Object[] _item : resultObject) {
				if (_item.length == 4) {
					NoteMoyenneAp nm = new NoteMoyenneAp((Integer) _item[0],
							(Integer) _item[1], (Double) _item[2],
							(Double) _item[3]);
					resultList.add(nm);
				}

			}
			log.debug("calculMoyenne successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("calculMoyenne failed", re);
			throw re;
		}
	}

	@Override
	public List<NoteEvaluationControleContinu> findAdvanced(
			NoteEvaluationControleContinu necc) {
		if (necc == null) {
			return null;
		}
		log.debug("findAdvanced start");
		EvaluationControleContinu eval = necc.getEvaluationControleContinu();
		try {
			StringBuilder request = new StringBuilder(
					"select r from NoteEvaluationControleContinu r ");

			request.append(" where (1=1)");
			if (eval != null) {
				if (eval.getAnneeAcademique() != null
						&& eval.getAnneeAcademique().getId() != 0) {
					request.append(
							" and r.evaluationControleContinu.anneeAcademique.id = ")
							.append(eval.getAnneeAcademique().getId());
				}
				if (eval.getOof() != null && eval.getOof().getId() != 0) {
					request.append(" and r.evaluationControleContinu.oof.id = ")
							.append(eval.getOof().getId());
				}
				if (eval.getPeriode() != null && eval.getPeriode().getId() != 0) {
					request.append(
							" and r.evaluationControleContinu.periode.id = ")
							.append(eval.getPeriode().getId());
				}
				if (eval.getComptablise() != null) {
					if (eval.getComptablise()) {
						request.append(" and r.evaluationControleContinu.comptablise = TRUE ");
					} else {
						request.append(" and r.evaluationControleContinu.comptablise = FALSE ");
					}
				}
				if (necc.getAffectationGroupePedagogique() != null
						&& necc.getAffectationGroupePedagogique().getGroupePedagogique() != null  && necc.getAffectationGroupePedagogique().getGroupePedagogique().getId() != 0) {
					request.append(" and r.affectationGroupePedagogique.groupePedagogique.id = ")
							.append(necc.getAffectationGroupePedagogique().getGroupePedagogique().getId());
				}
				if (eval.getAssociationGroupePedagogique() != null
						&& eval.getAssociationGroupePedagogique()
								.getRattachementMc() != null
						&& eval.getAssociationGroupePedagogique()
								.getRattachementMc().getId() != 0) {
					request.append(
							" and r.evaluationControleContinu.associationGroupePedagogique.rattachementMc.id = ")
							.append(eval.getAssociationGroupePedagogique()
									.getRattachementMc().getId());
				}
				request.append(" order by r.evaluationControleContinu.associationGroupePedagogique.ap.id");
			}

			TypedQuery<NoteEvaluationControleContinu> query = entityManager
					.createQuery(new String(request),
							NoteEvaluationControleContinu.class);

			List<NoteEvaluationControleContinu> resultList = (List<NoteEvaluationControleContinu>) query
					.getResultList();
			// resultList = Arrays.asList(resultList);
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}

			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			throw re;
		}
	}

	@Override
	public List<AtomePedagogique> findApOfCC(NoteEvaluationControleContinu necc) {
		if (necc == null) {
			return null;
		}
		log.debug("findApOfCC start");
		EvaluationControleContinu eval = necc.getEvaluationControleContinu();
		try {
			StringBuilder request = new StringBuilder(
					"select distinct r.evaluationControleContinu.associationGroupePedagogique.ap  from NoteEvaluationControleContinu r ");

			request.append(" where (1=1)");
			if (eval != null) {
				if (eval.getAnneeAcademique() != null
						&& eval.getAnneeAcademique().getId() != 0) {
					request.append(
							" and r.evaluationControleContinu.anneeAcademique.id = ")
							.append(eval.getAnneeAcademique().getId());
				}
				if (eval.getOof() != null && eval.getOof().getId() != 0) {
					request.append(" and r.evaluationControleContinu.oof.id = ")
							.append(eval.getOof().getId());
				}
				if (eval.getPeriode() != null && eval.getPeriode().getId() != 0) {
					request.append(
							" and r.evaluationControleContinu.periode.id = ")
							.append(eval.getPeriode().getId());
				}
				if (eval.getComptablise() != null) {
					if (eval.getComptablise()) {
						request.append(" and r.evaluationControleContinu.comptablise = TRUE ");
					} else {
						request.append(" and r.evaluationControleContinu.comptablise = FALSE ");
					}
				}
				if (necc.getAffectationGroupePedagogique() != null
						&& necc.getAffectationGroupePedagogique().getId() != 0) {
					request.append(" and r.affectationGroupePedagogique.id = ")
							.append(necc.getAffectationGroupePedagogique()
									.getId());
				}
				if (necc.getAffectationGroupePedagogique() != null
						&& necc.getAffectationGroupePedagogique().getGroupePedagogique() != null  && necc.getAffectationGroupePedagogique().getGroupePedagogique().getId() != 0) {
					request.append(" and r.affectationGroupePedagogique.groupePedagogique.id = ")
							.append(necc.getAffectationGroupePedagogique().getGroupePedagogique().getId());
				}
				AssociationGroupePedagogique assoc = eval
						.getAssociationGroupePedagogique();
				if (assoc != null) {
					if (assoc.getRattachementMc() != null
							&& assoc.getRattachementMc().getId() != 0) {
						request.append(
								" and r.evaluationControleContinu.associationGroupePedagogique.rattachementMc.id = ")
								.append(assoc.getRattachementMc().getId());
					}
				}
				//request.append(" group by r.evaluationControleContinu.associationGroupePedagogique.ap");
			}
			//Query query = entityManager.createQuery(new String(request));
			 TypedQuery<AtomePedagogique> query = entityManager.createQuery(
			 new String(request), AtomePedagogique.class);

			List<AtomePedagogique> resultList = (List<AtomePedagogique>) query
					.getResultList();
			
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findApOfCC successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findApOfCC failed", re);
			throw re;
		}
	}
}
