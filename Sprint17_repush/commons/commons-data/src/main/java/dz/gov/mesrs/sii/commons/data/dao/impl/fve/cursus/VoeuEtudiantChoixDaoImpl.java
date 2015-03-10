package dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.VoeuEtudiantChoixDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.VoeuEtudiantChoix;

/**
 * 
 * @author Mounir.MESSAOUDI on : 28 sept. 2014 17:08:04
 */
@Repository("voeuEtudiantChoixDao")
@Transactional
public class VoeuEtudiantChoixDaoImpl implements VoeuEtudiantChoixDao {

	private static final Logger log = LoggerFactory.getLogger(VoeuEtudiantChoixDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(VoeuEtudiantChoix transientInstance) {
		log.debug("persisting VoeuEtudiantChoix transientInstance instance");
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

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(VoeuEtudiantChoix persistentInstance) {
		log.debug("removing VoeuEtudiantChoix instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public VoeuEtudiantChoix merge(VoeuEtudiantChoix detachedInstance) {
		log.debug("merging VoeuEtudiantChoix instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public VoeuEtudiantChoix findById(int id) {
		log.debug("getting VoeuEtudiantChoix instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(VoeuEtudiantChoix.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<VoeuEtudiantChoix> findAdvanced(VoeuEtudiantChoix searchBo) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<VoeuEtudiantChoix> findByQuery(String query) {
		log.debug("findByQuery VoeuEtudiantChoix instances");
		try {
			return entityManager.createQuery(query).getResultList();
		} catch (RuntimeException re) {
			log.error("findByQuery 'AccessWilaya' failed", re);
			return new ArrayList<VoeuEtudiantChoix>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<VoeuEtudiantChoix> findAll() {
		log.debug("getting all VoeuEtudiantChoix instances");
		try {
			Query query = entityManager
					.createQuery("from VoeuEtudiantChoix VoeuEtudiantChoix");
			log.debug("findAll 'VoeuEtudiantChoix' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'AccessWilaya' failed", re);
			return new ArrayList<VoeuEtudiantChoix>();
		}
	}
}