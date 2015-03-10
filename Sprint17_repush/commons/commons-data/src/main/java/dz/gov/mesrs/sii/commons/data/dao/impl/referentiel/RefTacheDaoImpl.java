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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefTacheDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefTache;

/**
 * Dao object for domain model class RefTache.
 * @see dz.gov.mesrs.sii.RefTacheDto.business.model.bo.RefTache
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Repository ("refTacheDaoImpl")  @Transactional
public class RefTacheDaoImpl implements RefTacheDao {

	private static final Logger log = LoggerFactory.getLogger(RefTacheDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefTacheDao#persist(dz.gov.mesrs.sii.RefTacheDto.business.model.bo.RefTache)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefTache transientInstance) {
		log.debug("persisting RefTache instance");
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
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefTacheDao#remove(dz.gov.mesrs.sii.RefTacheDto.business.model.bo.RefTache)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefTache persistentInstance) {
		log.debug("removing RefTache instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefTacheDao#merge(dz.gov.mesrs.sii.RefTacheDto.business.model.bo.RefTache)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefTache merge(RefTache detachedInstance) {
		log.debug("merging RefTache instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefTacheDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public RefTache findById(int id) {
		log.debug("getting RefTache instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(RefTache.class, id);
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
	public List<RefTache> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<RefTache> findAll() {
		log.debug("getting all RefTache instances");
		try {
			Query query = entityManager.createQuery("from RefTache refTache");
			log.debug("findAll 'RefTache' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefTache' failed", re);
			return new ArrayList<RefTache>();
		}
	}



	@Override
	public List<RefTache> findByEvenement(Integer evenementId) {
		List<RefTache> resultList = new ArrayList();
		log.debug("findByEvenement: getting List of refTache instance : ");
			
		try {
			
			if (evenementId != null) {
				String request = "select r from RefTache r  where r.evenement.id = :evenementId and r.active = :active";
			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("evenementId", evenementId);
			query.setParameter("active", Boolean.TRUE);
			 resultList = (List<RefTache>) query.getResultList();
				
			}
			log.debug("findByEvenement successful");
			return resultList;
		
		} catch (RuntimeException re) {
			log.error("findByEvenement failed", re);
			System.out.println("findByEvenement failed");
			throw re;
		}
		
	}
}
