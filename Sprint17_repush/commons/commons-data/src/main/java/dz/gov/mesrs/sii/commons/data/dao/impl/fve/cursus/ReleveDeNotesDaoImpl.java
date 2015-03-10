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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.ReleveDeNotesDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.ReleveDeNotes;

/**
 * Dao object for domain model class ReleveDeNotes.
 * @see dz.gov.mesrs.sii.ReleveDeNotesDto.business.model.bo.ReleveDeNotes
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Repository ("releveDeNotesDao")  @Transactional
public class ReleveDeNotesDaoImpl implements ReleveDeNotesDao {

	private static final Logger log = LoggerFactory.getLogger(ReleveDeNotesDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.ReleveDeNotesDao#persist(dz.gov.mesrs.sii.ReleveDeNotesDto.business.model.bo.ReleveDeNotes)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(ReleveDeNotes transientInstance) {
		log.debug("persisting ReleveDeNotes instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.ReleveDeNotesDao#remove(dz.gov.mesrs.sii.ReleveDeNotesDto.business.model.bo.ReleveDeNotes)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(ReleveDeNotes persistentInstance) {
		log.debug("removing ReleveDeNotes instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.ReleveDeNotesDao#merge(dz.gov.mesrs.sii.ReleveDeNotesDto.business.model.bo.ReleveDeNotes)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ReleveDeNotes merge(ReleveDeNotes detachedInstance) {
		log.debug("merging ReleveDeNotes instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NReleveDeNotesDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public ReleveDeNotes findById(int id) {
		log.debug("getting ReleveDeNotes instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(ReleveDeNotes.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<ReleveDeNotes> findAll() {
		log.debug("getting all ReleveDeNotes instances");
		try {
			Query query = entityManager.createQuery("from ReleveDeNotes releveDeNotes");
			log.debug("findAll 'ReleveDeNotes' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'ReleveDeNotes' failed", re);
			return new ArrayList<ReleveDeNotes>();
		}
	}
}
