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

import dz.gov.mesrs.sii.commons.data.dao.fve.bac.NoteAccessFiliereDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.NoteAccessFiliere;

/**
 * Dao object for domain model class NoteAccessFiliere;
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.NoteAccessFiliere;
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 



@Repository ("noteAccessFiliereDao")  @Transactional
public class NoteAccessFiliereDaoImpl implements NoteAccessFiliereDao {

	private static final Logger log = LoggerFactory.getLogger(NoteAccessFiliereDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.NoteAccessFiliereDao;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.NoteAccessFiliere)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(NoteAccessFiliere transientInstance) {
		log.debug("persisting NoteAccessFiliere instance");
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
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.NoteAccessFiliereDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.NoteAccessFiliere)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(NoteAccessFiliere persistentInstance) {
		log.debug("removing NoteAccessFiliere instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.NoteAccessFiliereDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.NoteAccessFiliere)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public NoteAccessFiliere merge(NoteAccessFiliere detachedInstance) {
		log.debug("merging NoteAccessFiliere instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.NoteAccessFiliereDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public NoteAccessFiliere findById(int id) {
		log.debug("getting NoteAccessFiliere instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(NoteAccessFiliere.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
		
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<NoteAccessFiliere> findAll() {
		log.debug("getting all NoteAccessFiliere instances");
		try {
			Query query = entityManager.createQuery("from NoteAccessFiliere noteAccessFiliere");
			log.debug("findAll 'NoteAccessFiliere' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'NoteAccessFiliere' failed", re);
			return new ArrayList<NoteAccessFiliere>();
		}
	}
}
