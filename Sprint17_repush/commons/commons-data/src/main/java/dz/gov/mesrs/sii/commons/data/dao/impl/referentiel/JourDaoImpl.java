package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.JourDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Jour;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:41:10
 */
 



@Repository ("jourDao")  @Transactional
public class JourDaoImpl implements JourDao {

	private static final Logger log = LoggerFactory.getLogger(JourDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.JourDao;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.Jour)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(Jour transientInstance) {
		log.debug("persisting Jour instance");
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
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.JourDao#remove(dz.gov.mesrs.sii.JourDto.business.model.bo.cursus.Jour)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Jour persistentInstance) {
		log.debug("removing Jour instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.JourDao#merge(dz.gov.mesrs.sii.JourDto.business.model.bo.cursus.Jour)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Jour merge(Jour detachedInstance) {
		log.debug("merging Jour instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.JourDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public Jour findById(int id) {
		log.debug("getting Jour instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(Jour.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
		
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<Jour> findAll() {
		log.debug("getting all Jour instances");
		try {
			Query query = entityManager.createQuery("from Jour jour");
			log.debug("findAll 'Jour' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Jour' failed", re);
			return new ArrayList<Jour>();
		}
	}
}
