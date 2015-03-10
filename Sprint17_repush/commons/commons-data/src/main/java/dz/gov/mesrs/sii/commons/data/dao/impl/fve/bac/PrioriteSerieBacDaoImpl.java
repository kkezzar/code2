package dz.gov.mesrs.sii.commons.data.dao.impl.fve.bac;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.bac.PrioriteSerieBacDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.PrioriteSerieBac;

/**
 * Dao object for domain model class PrioriteSerieBac;
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.PrioriteSerieBac;
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Repository("prioriteSerieBacDao")
@Transactional
public class PrioriteSerieBacDaoImpl implements PrioriteSerieBacDao {

	private static final Logger log = LoggerFactory.getLogger(PrioriteSerieBacDaoImpl.class.getName());


	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.PrioriteSerieBacDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.
	 *      PrioriteSerieBac)
	 * 
	 * @param transientInstance
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(PrioriteSerieBac transientInstance) {
		log.debug("persisting PrioriteSerieBac instance");
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
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.PrioriteSerieBacDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.PrioriteSerieBac)
	 * @param persistentInstance
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(PrioriteSerieBac persistentInstance) {
		log.debug("removing PrioriteSerieBac instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.PrioriteSerieBacDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.PrioriteSerieBac)
	 * @param detachedInstance
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PrioriteSerieBac merge(PrioriteSerieBac detachedInstance) {
		log.debug("merging PrioriteSerieBac instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.PrioriteSerieBacDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public PrioriteSerieBac findById(int id) {
		log.debug("getting PrioriteSerieBac instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(PrioriteSerieBac.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<PrioriteSerieBac> findAll() {
		log.debug("getting all PrioriteSerieBac instances");
		try {
			Query query = entityManager
					.createQuery("from PrioriteSerieBac prioriteSerieBac");
			log.debug("findAll 'PrioriteSerieBac' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'PrioriteSerieBac' failed", re);
			return new ArrayList<PrioriteSerieBac>();
		}
	}
}
