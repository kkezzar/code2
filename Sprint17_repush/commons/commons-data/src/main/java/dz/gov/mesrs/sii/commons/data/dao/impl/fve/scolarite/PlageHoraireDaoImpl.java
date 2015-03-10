package dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.PlageHoraireDao;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.PlageHoraire;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:53:08
 */
 



@Repository ("plageHoraireDao")  @Transactional
public class PlageHoraireDaoImpl implements PlageHoraireDao {

	private static final Logger log = LoggerFactory.getLogger(PlageHoraireDaoImpl.class.getName());
	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.PlageHoraireDao;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.PlageHoraire)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(PlageHoraire transientInstance) {
		log.debug("persisting PlageHoraire instance");
		try {
			entityManager.persist(transientInstance);
			/*entityManager.flush();
			entityManager.refresh(transientInstance);*/ 
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.PlageHoraireDao#remove(dz.gov.mesrs.sii.PlageHoraireDto.business.model.bo.cursus.PlageHoraire)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(PlageHoraire persistentInstance) {
		log.debug("removing PlageHoraire instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.PlageHoraireDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.PlageHoraireDto)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PlageHoraire merge(PlageHoraire detachedInstance) {
		log.debug("merging PlageHoraire instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.PlageHoraireDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public PlageHoraire findById(int id) {
		log.debug("getting PlageHoraire instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(PlageHoraire.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
		
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<PlageHoraire> findAll() {
		log.debug("getting all PlageHoraire instances");
		try {
			Query query = entityManager.createQuery("from PlageHoraire plageHoraire");
			log.debug("findAll 'PlageHoraire' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'PlageHoraire' failed", re);
			return new ArrayList<PlageHoraire>();
		}
	}
}
