/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.BilanControleContinuDaoImpl.java] 
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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.BilanControleContinuDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanControleContinu;

/**
 * @author MAKERRI Sofiane on : 16 oct. 2014 17:18:26
 */
@Repository("bilanControleContinuDao")
@Transactional
public class BilanControleContinuDaoImpl implements BilanControleContinuDao {

	/**
	 * [BilanControleContinuDaoImpl.BilanControleContinuDaoImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:18:26
	 */
	private static final Logger log = LoggerFactory.getLogger(BilanControleContinuDaoImpl.class.getName());
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public BilanControleContinuDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.persistence.cursus.BilanControleContinuDao
	 * #persist
	 * (dz.gov.mesrs.sii.fve.business.model.bo.cursus.BilanControleContinu)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(BilanControleContinu transientInstance) {
		log.debug("persisting BilanControleContinu instance");
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
	 * dz.gov.mesrs.sii.fve.business.persistence.cursus.BilanControleContinuDao
	 * #remove
	 * (dz.gov.mesrs.sii.fve.business.model.bo.cursus.BilanControleContinu)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(BilanControleContinu persistentInstance) {
		log.debug("removing BilanControleContinu instance");
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
	 * #merge
	 * (dz.gov.mesrs.sii.fve.business.model.bo.cursus.BilanControleContinu)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public BilanControleContinu merge(BilanControleContinu detachedInstance) {
		log.debug("merging BilanControleContinu instance");
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
	public BilanControleContinu findById(int id) {
		log.debug("getting BilanControleContinu instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(BilanControleContinu.class, id);
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
	public List<BilanControleContinu> findAll() {
		log.debug("getting all BilanControleContinu instances");
		try {
			Query query = entityManager
					.createQuery("from BilanControleContinu bilanControleContinu");
			log.debug("findAll 'BilanControleContinu' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'BilanControleContinu' failed", re);
			return new ArrayList<BilanControleContinu>();
		}
	}

	@Override
	public BilanControleContinu findUnique(Integer anneeId, Integer oofId,
			Integer periodeId, Integer affGpId, Integer rattachementMcId) {
		try {

			StringBuilder request = new StringBuilder(
					"select r from BilanControleContinu r ");
			request.append(" where (1=1) ");
			if (oofId != null) {
				request.append(" and r.oof.id = " + oofId);
			}
			if (anneeId != null) {
				request.append(" and r.anneeAcademique.id = " + oofId);
			}
			if (periodeId != null) {
				request.append(" and r.periode.id = " + periodeId);
			}
			if (affGpId != null) {
				request.append(" and r.affectationGroupePedagogique.id = "
						+ affGpId);
			}
			if (rattachementMcId != null) {
				request.append(" and r.rattachementMc.id = " + rattachementMcId);
			}
			TypedQuery<BilanControleContinu> query = entityManager.createQuery(
					new String(request), BilanControleContinu.class);
			List<BilanControleContinu> resultList = (List<BilanControleContinu>) query
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
	public BilanControleContinu findUniqueByDiaId(Integer anneeId,
			Integer oofId, Integer periodeId, Integer diaId,
			Integer rattachementMcId) {
		try {

			StringBuilder request = new StringBuilder(
					"select r from BilanControleContinu r,  DossierInscriptionAdministrative d ");
			request.append(" where (1=1) ");
			if (oofId != null) {
				request.append(" and r.oof.id = " + oofId);
			}
			if (anneeId != null) {
				request.append(" and r.anneeAcademique.id = " + anneeId);
			}
			if (periodeId != null) {
				request.append(" and r.periode.id = " + periodeId);
			}
			if (diaId != null) {
				request.append(" and r.affectationGroupePedagogique.dossierInscriptionAdministrative.id = "
						+ diaId);
			}
			if (rattachementMcId != null) {
				request.append(" and r.rattachementMc.id = " + rattachementMcId);
			}
			TypedQuery<BilanControleContinu> query = entityManager.createQuery(
					new String(request), BilanControleContinu.class);
			List<BilanControleContinu> resultList = (List<BilanControleContinu>) query
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.examen.BilanControleContinuDao#
	 * findAdvanced
	 * (dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanControleContinu)
	 */
	@Override
	public List<BilanControleContinu> findAdvanced(BilanControleContinu search) {
		try {

			if (search == null) {
				return null;
			}

			StringBuilder request = new StringBuilder(
					"select r from BilanControleContinu r ");
			request.append(" where (1=1) ");
			if (search.getOof() != null && search.getOof().getId() != 0) {
				request.append(" and r.oof.id = " + search.getOof().getId());
			}
			if (search.getAnneeAcademique() != null
					&& search.getAnneeAcademique().getId() != 0) {
				request.append(" and r.anneeAcademique.id = "
						+ search.getAnneeAcademique().getId());
			}
			if (search.getPeriode() != null && search.getPeriode().getId() != 0) {
				request.append(" and r.periode.id = "
						+ search.getPeriode().getId());
			}
			if (search.getRattachementMc() != null
					&& search.getRattachementMc().getId() != 0) {
				request.append(" and r.rattachementMc.id = "
						+ search.getRattachementMc().getId());
			}

			if (search.getAffectationGroupePedagogique() != null
					&& search.getAffectationGroupePedagogique().getId() != 0) {
				request.append(" and r.affectationGroupePedagogique.id = "
						+ search.getAffectationGroupePedagogique().getId());
			}
			if (search.getAffectationGroupePedagogique() != null
					&& search.getAffectationGroupePedagogique()
							.getDossierInscriptionAdministrative() != null
					&& search.getAffectationGroupePedagogique()
							.getDossierInscriptionAdministrative().getId() != 0) {
				request.append(" and r.affectationGroupePedagogique.dossierInscriptionAdministrative.id = "
						+ search.getAffectationGroupePedagogique()
								.getDossierInscriptionAdministrative().getId());
			}
			TypedQuery<BilanControleContinu> query = entityManager.createQuery(
					new String(request), BilanControleContinu.class);
			List<BilanControleContinu> resultList = (List<BilanControleContinu>) query
					.getResultList();
			log.debug("findAdvanced successful");
			
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			throw re;
		}
	}
}
