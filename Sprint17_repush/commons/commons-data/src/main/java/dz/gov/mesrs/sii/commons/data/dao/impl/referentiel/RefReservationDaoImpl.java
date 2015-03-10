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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefReservationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefReservation;

/**
 * Dao object for domain model class RefReservation.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.RefReservation
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Repository ("refReservationDaoImpl")  @Transactional
public class RefReservationDaoImpl implements RefReservationDao {

	private static final Logger log = LoggerFactory.getLogger(RefReservationDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefReservationDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.RefReservation)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefReservation transientInstance) {
		log.debug("persisting RefReservation instance");
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
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefReservationDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.RefReservation)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefReservation persistentInstance) {
		log.debug("removing RefReservation instance");
		try {
			entityManager.remove(persistentInstance);
			entityManager.flush();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefReservationDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.RefReservation)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefReservation merge(RefReservation detachedInstance) {
		log.debug("merging RefReservation instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefReservationDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public RefReservation findById(int id) {
		log.debug("getting RefReservation instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(RefReservation.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	
	/**
	 * 
	 * @param query
	 */
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<RefReservation> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<RefReservation> findAll() {
		log.debug("getting all RefReservation instances");
		entityManager.flush();
		try {
			Query query = entityManager.createQuery("from RefReservation refReservation");
			log.debug("findAll 'RefReservation' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefReservation' failed", re);
			return new ArrayList<RefReservation>();
		}
	}




	@Override
	public List<RefReservation> findByIdLieu(int idLieu) {
		
		log.debug("getting List of RefReservation instance with : " + idLieu);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefReservation r ");
			request.append("where r.refLieu.id = :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", idLieu);
			List<RefReservation> resultList = (List<RefReservation>) query
					.getResultList();
			log.debug("findByIdLieu successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByIdLieu failed", re);
			throw re;
		}

		
	}
	
	
	@Override
	public List<RefReservation> findByIdEquipement(int idEquipement) {
		
		log.debug("getting List of RefReservation instance with : " + idEquipement);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefReservation r ");
			request.append("where r.refEquipement.id = :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", idEquipement);
			List<RefReservation> resultList = (List<RefReservation>) query
					.getResultList();
			log.debug("findByIdEquipement successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByIdEquipement failed", re);
			throw re;
		}

		
	} 



}
