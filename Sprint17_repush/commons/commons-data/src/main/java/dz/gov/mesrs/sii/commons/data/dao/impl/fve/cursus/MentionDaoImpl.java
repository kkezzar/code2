/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.MentionDaoImpl.java] 
 * @author MAKERRI Sofiane on : 22 oct. 2014  15:48:03
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.MentionDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Mention;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:48:03
 */
@Repository("mentionDao")
@Transactional
public class MentionDaoImpl implements MentionDao {

	/**
	 * [MentionDaoImpl.MentionDaoImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:48:03
	 */
	private static final Logger log = LoggerFactory.getLogger(MentionDaoImpl.class.getName());
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public MentionDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(Mention transientInstance) {
		log.debug("persisting Mention instance");
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
	 * #remove (dz.gov.mesrs.sii.fve.business.model.bo.cursus.Mention)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Mention persistentInstance) {
		log.debug("removing Mention instance");
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
	 * #merge (dz.gov.mesrs.sii.fve.business.model.bo.cursus.Mention)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Mention merge(Mention detachedInstance) {
		log.debug("merging Mention instance");
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
	public Mention findById(int id) {
		log.debug("getting Mention instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(Mention.class, id);
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
	public List<Mention> findAll() {
		log.debug("getting all Mention instances");
		try {
			Query query = entityManager.createQuery("from Mention mention");
			log.debug("findAll 'Mention' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Mention' failed", re);
			return new ArrayList<Mention>();
		}
	}

	@Override
	public Mention findByNote(Integer anneeAcademiqueId, double note) {
		log.debug("getting all findByNote instances");
		try {

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("SELECT r FROM Mention r ");
			sqlQuery.append(" where r.noteMin <= ");
			sqlQuery.append(note);
			sqlQuery.append(" and r.noteMax > ");
			sqlQuery.append(note);
			if (anneeAcademiqueId != null) {
				sqlQuery.append(" and r.anneeAcademique.id = "
						+ anneeAcademiqueId);
			}
			TypedQuery<Mention> query = entityManager.createQuery(
					new String(sqlQuery), Mention.class);

			List<Mention> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result.get(0);

		} catch (RuntimeException re) {
			log.error("findByNote failed", re);
			throw re;
		}
	}

	@Override
	public List<Mention> findByAnneeAcademique(Integer anneeAcademiqueId) {
		log.debug("getting all findByAnneeAcademique instances");
		try {
			if (anneeAcademiqueId == null) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("SELECT r FROM Mention r ");
			sqlQuery.append(" where r.anneeAcademique.id = "
					+ anneeAcademiqueId);
			TypedQuery<Mention> query = entityManager.createQuery(
					new String(sqlQuery), Mention.class);

			List<Mention> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result;

		} catch (RuntimeException re) {
			log.error("findByAnneeAcademique failed", re);
			throw re;
		}
	}

}
