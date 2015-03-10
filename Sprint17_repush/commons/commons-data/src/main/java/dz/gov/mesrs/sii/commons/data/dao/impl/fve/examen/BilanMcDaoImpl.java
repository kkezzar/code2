/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.BilanUeDaoImpl.java] 
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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.BilanMcDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanMc;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:48:03
 */
@Repository("bilanMcDao")
@Transactional
public class BilanMcDaoImpl implements BilanMcDao {

	/**
	 * [BilanMcDaoImpl.BilanMcDaoImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:48:03
	 */
	private static final Logger log = LoggerFactory.getLogger(BilanMcDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public BilanMcDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(BilanMc transientInstance) {
		log.debug("persisting BilanMc instance");
		try {
			entityManager.persist(transientInstance);
//			entityManager.flush();
//			entityManager.refresh(transientInstance);

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
	 * #remove (dz.gov.mesrs.sii.fve.business.model.bo.cursus.BilanMc)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(BilanMc persistentInstance) {
		log.debug("removing BilanMc instance");
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
	 * #merge (dz.gov.mesrs.sii.fve.business.model.bo.cursus.BilanMc)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public BilanMc merge(BilanMc detachedInstance) {
		log.debug("merging BilanMc instance");
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
	public BilanMc findById(int id) {
		log.debug("getting BilanMc instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(BilanMc.class, id);
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
	public List<BilanMc> findAll() {
		log.debug("getting all BilanMc instances");
		try {
			Query query = entityManager
					.createQuery("from BilanMc bilanControleContinu");
			log.debug("findAll 'BilanMc' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'BilanMc' failed", re);
			return new ArrayList<BilanMc>();
		}
	}

	@Override
	public BilanMc findUnique(int bilanUeId, int rattachementMcId, int type) {
		try {
			StringBuilder request = new StringBuilder(
					"select r from BilanMc r ");
			request.append(" where (1=1) ");

			request.append(" and r.bilanUe.id = " + bilanUeId);
			request.append(" and r.rattachementMc.id = " + rattachementMcId);
			request.append(" and r.bilanUe.bilanSession.type = " + type);
			TypedQuery<BilanMc> query = entityManager.createQuery(new String(
					request), BilanMc.class);
			List<BilanMc> resultList = (List<BilanMc>) query.getResultList();
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
	public List<BilanMc> findByBilanUeId(int bilanUeId) {
		try {
			StringBuilder request = new StringBuilder(
					"select r from BilanMc r ");
			request.append(" where (1=1) ");
			request.append(" and r.bilanUe.id = " + bilanUeId);
			TypedQuery<BilanMc> query = entityManager.createQuery(new String(
					request), BilanMc.class);
			List<BilanMc> resultList = (List<BilanMc>) query.getResultList();
			log.debug("findByBilanUeId successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByBilanUeId failed", re);
			throw re;
		}
	}

	@Override
	public List<BilanMc> findByOofAndNiveau(Integer oofId, Integer niveauId) {
		try {
			if (oofId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from BilanMc r ");

			request.append(" where r.bilanUe.bilanSession.oof.id = " + oofId);
			if (niveauId != null) {
				request.append(" and r.bilanUe.bilanSession.periode.niveau.id = "
						+ niveauId);
			}
			TypedQuery<BilanMc> query = entityManager.createQuery(new String(
					request), BilanMc.class);
			List<BilanMc> resultList = (List<BilanMc>) query.getResultList();
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

}
