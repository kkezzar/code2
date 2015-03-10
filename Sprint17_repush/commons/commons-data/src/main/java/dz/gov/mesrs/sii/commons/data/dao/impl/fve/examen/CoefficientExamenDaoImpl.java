/**
 * [dz.gov.mesrs.sii.commons.data.dao.impl.fve.notation.ParametrageCoefficientExamenDaoImpl.java] 
 * @author MAKERRI Sofiane on : 5 janv. 2015  09:18:42
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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.CoefficientExamenDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus.VoeuEtudiantDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.CoefficientExamen;

/**
 * @author MAKERRI Sofiane on : 5 janv. 2015 09:18:42
 */
@Repository("coefficientExamenDao")
public class CoefficientExamenDaoImpl implements
		CoefficientExamenDao {

	private static final Logger log = LoggerFactory.getLogger(CoefficientExamenDaoImpl.class.getName());
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.notation.
	 * ParametrageCoefficientExamenDao
	 * #persist(dz.gov.mesrs.sii.commons.data.model
	 * .fve.notation.CoefficientExamen)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(CoefficientExamen transientInstance) {
		log.debug("persisting CoefficientExamen instance");
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
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.notation.
	 * ParametrageCoefficientExamenDao
	 * #remove(dz.gov.mesrs.sii.commons.data.model
	 * .fve.notation.CoefficientExamen)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(CoefficientExamen persistentInstance) {
		log.debug("removing CoefficientExamen instance");
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
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.notation.
	 * ParametrageCoefficientExamenDao
	 * #merge(dz.gov.mesrs.sii.commons.data.model.
	 * fve.notation.CoefficientExamen)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CoefficientExamen merge(
			CoefficientExamen detachedInstance) {
		log.debug("merging CoefficientExamen instance");
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
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.notation.
	 * ParametrageCoefficientExamenDao#findById(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public CoefficientExamen findById(int id) {
		log.debug("getting CoefficientExamen instance with id: "
				+ id);
		try {
			log.debug("get successful");
			return entityManager.find(CoefficientExamen.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.notation.
	 * ParametrageCoefficientExamenDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<CoefficientExamen> findAll() {
		log.debug("getting all CoefficientExamen instances");
		try {
			Query query = entityManager
					.createQuery("from CoefficientExamen parametrageCoefficientExamen");
			log.debug("findAll 'CoefficientExamen' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'CoefficientExamen' failed", re);
			return new ArrayList<CoefficientExamen>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.examen.ParametrageCoefficientExamenDao
	 * #findByOofAndPeriode(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<CoefficientExamen> findByOofAndPeriode(Integer oofId,
			Integer periodeId) {
		try {
			if (oofId == null || periodeId == null) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder(
					"select r from CoefficientExamen r ");
			sqlQuery.append(" where r.periode.id = " + periodeId);
			sqlQuery.append(" and r.ouvertureOffreFormation.id = " + oofId);

			TypedQuery<CoefficientExamen> query = entityManager
					.createQuery(new String(sqlQuery),
							CoefficientExamen.class);
			List<CoefficientExamen> result = query.getResultList();
			return result;

		} catch (RuntimeException re) {
			log.error("findByOofAndPeriode failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.examen.CoefficientExamenDao#findUnique(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public CoefficientExamen findUnique(Integer oofId, Integer periodeId,
			Integer rattachementMcId) {
		try {
			if (oofId == null || periodeId == null && rattachementMcId == null) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder(
					"select r from CoefficientExamen r ");
			sqlQuery.append(" where r.periode.id = " + periodeId);
			sqlQuery.append(" and r.ouvertureOffreFormation.id = " + oofId);
			sqlQuery.append(" and r.rattachementMc.id = " + rattachementMcId);

			TypedQuery<CoefficientExamen> query = entityManager
					.createQuery(new String(sqlQuery),
							CoefficientExamen.class);
			List<CoefficientExamen> result = query.getResultList();
			if(result == null || result.isEmpty()) {
				return null;
			}
			return result.get(0);

		} catch (RuntimeException re) {
			log.error("findUnique failed", re);
			throw re;
		}
	}

}
