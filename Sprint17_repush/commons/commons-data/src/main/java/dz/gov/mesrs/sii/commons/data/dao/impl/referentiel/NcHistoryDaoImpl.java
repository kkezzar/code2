package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.NcHistoryDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.NcHistory;

/**
 * Dao object for domain model class NcHistory.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.NcHistory
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("ncHistoryDaoImpl")  
public class NcHistoryDaoImpl implements NcHistoryDao {

	private static final Logger log = LoggerFactory.getLogger(NcHistoryDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NcHistoryDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.NcHistory)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(NcHistory transientInstance) {
		log.debug("persisting NcHistory instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NcHistoryDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.NcHistory)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(NcHistory persistentInstance) {
		log.debug("removing NcHistory instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NcHistoryDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.NcHistory)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public NcHistory merge(NcHistory detachedInstance) {
		log.debug("merging NcHistory instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NNcHistoryDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public NcHistory findById(int id) {
		log.debug("getting NcHistory instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(NcHistory.class, id);
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
	public List<NcHistory> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<NcHistory> findAll() {
		log.debug("getting all NcHistory instances");
		try {
			Query query = entityManager.createQuery("from NcHistory ncHistory");
			log.debug("findAll 'NcHistory' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'NcHistory' failed", re);
			return new ArrayList<NcHistory>();
		}
	}



	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.NcHistoryDao#findByIdRef(int)
	 */
	@Override
	public List<NcHistory> findByIdRef(int id) {
		log.debug("getting all NcHistory instances by id "+id);
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select r from NcHistory r");
			buffer.append(" where r.nomenclature.id = "+ id);
			//buffer.append(" order by nomenclature." + ConverterService.NC_VALUES_ORDERBY_LL_FR); //default orderby
			String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			List<NcHistory> resultList = (List<NcHistory>) query
					.getResultList();
			log.debug("findByIdRef by id successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("findByIdRef by id failed", re);
			throw re;
		}
	}
}
