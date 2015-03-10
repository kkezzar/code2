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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.BilanUeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanUe;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:48:03
 */
@Repository("bilanUeDao")
@Transactional
public class BilanUeDaoImpl implements BilanUeDao {

	/**
	 * [BilanUeDaoImpl.BilanUeDaoImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:48:03
	 */
	private static final Logger log = LoggerFactory.getLogger(BilanUeDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public BilanUeDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(BilanUe transientInstance) {
		log.debug("persisting BilanUe instance");
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
	 * #remove (dz.gov.mesrs.sii.fve.business.model.bo.cursus.BilanUe)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(BilanUe persistentInstance) {
		log.debug("removing BilanUe instance");
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
	 * #merge (dz.gov.mesrs.sii.fve.business.model.bo.cursus.BilanUe)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public BilanUe merge(BilanUe detachedInstance) {
		log.debug("merging BilanUe instance");
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
	public BilanUe findById(int id) {
		log.debug("getting BilanUe instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(BilanUe.class, id);
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
	public List<BilanUe> findAll() {
		log.debug("getting all BilanUe instances");
		try {
			Query query = entityManager
					.createQuery("from BilanUe bilanControleContinu");
			log.debug("findAll 'BilanUe' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'BilanUe' failed", re);
			return new ArrayList<BilanUe>();
		}
	}

	@Override
	public BilanUe findUnique(long bilanSessionId, int repartitionUeId, int type) {
		try {
			StringBuilder request = new StringBuilder(
					"select r from BilanUe r ");
			request.append(" where (1=1) ");

			request.append(" and r.bilanSession.id = " + bilanSessionId);
			request.append(" and r.repartitionUe.id = " + repartitionUeId);
			request.append(" and r.bilanSession.type = " + type);
			TypedQuery<BilanUe> query = entityManager.createQuery(new String(
					request), BilanUe.class);
			List<BilanUe> resultList = (List<BilanUe>) query.getResultList();
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
	public List<BilanUe> findByBilanSessionId(int bilanSessionId) {
		try {
			StringBuilder request = new StringBuilder(
					"select r from BilanUe r ");
			request.append(" where (1=1) ");
			request.append(" and r.bilanSession.id = " + bilanSessionId);
			TypedQuery<BilanUe> query = entityManager.createQuery(new String(
					request), BilanUe.class);
			List<BilanUe> resultList = (List<BilanUe>) query.getResultList();
			log.debug("findByBilanSessionId successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByBilanSessionId failed", re);
			throw re;
		}
	}

	@Override
	public List<BilanUe> findByOofAndNiveau(Integer oofId, Integer niveauId) {
		try {
			if (oofId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from BilanUe r ");
			request.append(" where r.bilanSession.oof.id = " + oofId);
			if (niveauId != null) {
				request.append(" and r.bilanSession.periode.niveau.id = "
						+ niveauId);
			}
			TypedQuery<BilanUe> query = entityManager.createQuery(new String(
					request), BilanUe.class);
			List<BilanUe> resultList = (List<BilanUe>) query.getResultList();
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
