package dz.gov.mesrs.sii.commons.data.dao.impl.bpm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.bpm.EtapeI18nDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.EtapeI18n;

/**
 * Dao object for domain model class EtapeI18n.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.EtapeI18n
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("etapeI18nDao")
public class EtapeI18nDaoImpl implements EtapeI18nDao {

	private static final Logger log = LoggerFactory.getLogger(EtapeI18nDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.EtapeI18nDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.EtapeI18n)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(EtapeI18n transientInstance) {
		log.debug("persisting EtapeI18n instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.EtapeI18nDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.EtapeI18n)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(EtapeI18n persistentInstance) {
		log.debug("removing EtapeI18n instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.EtapeI18nDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.EtapeI18n)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public EtapeI18n merge(EtapeI18n detachedInstance) {
		log.debug("merging EtapeI18n instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NEtapeI18nDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public EtapeI18n findById(int id) {
		log.debug("getting EtapeI18n instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(EtapeI18n.class, id);
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
	public List<EtapeI18n> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<EtapeI18n> findAll() {
		log.debug("getting all EtapeI18n instances");
		try {
			Query query = entityManager.createQuery("from EtapeI18n etapeI18n");
			log.debug("findAll 'EtapeI18n' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'EtapeI18n' failed", re);
			return new ArrayList<EtapeI18n>();
		}
	}



	@Override
	public List<EtapeI18n> findListEtapeI18nByEtape(int idEtape) {
log.debug("findListEtapeI18nByEtape: getting List of EtapeI18n instance : ");
		

		try {
			StringBuilder request = new StringBuilder(
					"select r from EtapeI18n r where r.etape.id = ").append(idEtape);
	
			Query query = entityManager.createQuery(request.toString());
			List<EtapeI18n> resultList = (List<EtapeI18n>) query
					.getResultList();
			log.debug("findListEtapeI18nByEtape successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findListEtapeI18nByEtape failed", re);

			throw re;
		}
	}
}
