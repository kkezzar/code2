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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefConfigurationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefConfiguration;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefConfiguration.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.RefConfiguration
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Repository ("refConfigurationDaoImpl")  @Transactional
public class RefConfigurationDaoImpl implements RefConfigurationDao {

	private static final Logger log = LoggerFactory.getLogger(RefConfigurationDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefConfigurationDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.RefConfiguration)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefConfiguration transientInstance) {
		log.debug("persisting RefConfiguration instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefConfigurationDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.RefConfiguration)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefConfiguration persistentInstance) {
		log.debug("removing RefConfiguration instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefConfigurationDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.RefConfiguration)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefConfiguration merge(RefConfiguration detachedInstance) {
		log.debug("merging RefConfiguration instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefConfigurationDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public RefConfiguration findById(int id) {
		log.debug("getting RefConfiguration instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(RefConfiguration.class, id);
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
	public List<RefConfiguration> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<RefConfiguration> findAll() {
		log.debug("getting all RefConfiguration instances");
		try {
			Query query = entityManager.createQuery("from RefConfiguration refConfiguration");
			log.debug("findAll 'RefConfiguration' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefConfiguration' failed", re);
			return new ArrayList<RefConfiguration>();
		}
	}



	@Override
	public List<RefConfiguration> findByDomain(Integer idDomain) {
		List<RefConfiguration> resultList = new ArrayList();
		log.debug("findByDomain: getting List of refConfiguration instance : ");
		

		String firstOpr = UtilConstant.WHERE_SQL_STR;	
		
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefConfiguration  r  ");
			

			if (idDomain != null ) {
				request.append(firstOpr ).append( " r.refDomaine.id  = " ).append(idDomain);
			}
			

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			 resultList = (List<RefConfiguration>) query.getResultList();
			log.debug("findByDomain successful");
			return resultList;
		
		} catch (RuntimeException re) {
			log.error("findByDomain failed", re);
			throw re;
		}
	}
}
