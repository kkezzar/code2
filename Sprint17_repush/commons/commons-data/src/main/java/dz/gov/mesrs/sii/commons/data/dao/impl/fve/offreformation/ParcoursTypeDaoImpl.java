package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.ParcoursTypeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ParcoursType;

/**
 * Dao object for domain model class ParcoursType.
 * @see dz.gov.mesrs.sii.fve.business.model.bo.ParcoursType
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Service ("parcoursTypeDao")
public class ParcoursTypeDaoImpl implements ParcoursTypeDao {

	private static final Logger log = LoggerFactory.getLogger(ParcoursTypeDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;
	 
	/**
	 * [ParcoursTypeDaoImpl.persist] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:02:06
	 * @param transientInstance
	 */
	@Override 
	public void persist(ParcoursType transientInstance) {
		
		log.debug("persisting ParcoursType instance");
		try {
					
					entityManager.persist(transientInstance);
					log.debug("persist successful");
					
		} catch (RuntimeException re) {
					
					log.error("persist failed", re);
					throw re;
		}
	}
	 
	/**
	 * [ParcoursTypeDaoImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:02:11
	 * @param persistentInstance
	 */
	@Override 
	public void remove(ParcoursType persistentInstance) {
		
		log.debug("removing ParcoursType instance");
		
		try {
					entityManager.remove(persistentInstance);
					log.debug("remove successful");
					
		} catch (RuntimeException re) {
					
					log.error("remove failed", re);
					throw re;
		}
	}
	 
	/**
	 * [ParcoursTypeDaoImpl.merge] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:02:17
	 * @param detachedInstance
	 * @return ParcoursType
	 */
	@Override 
	public ParcoursType merge(ParcoursType detachedInstance) {
		
		log.debug("merging ParcoursType instance");
		try {
			
				log.debug("merge successful");
				return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
				
				log.error("merge failed", re);
				throw re;
		}
	}

	/**
	 * [ParcoursTypeDaoImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:02:23
	 * @param id
	 * @return ParcoursType
	 */
	@Override 
	public ParcoursType findById(int id) {
		
		log.debug("getting ParcoursType instance with id: " + id);
		try {			
				
				log.debug("get successful");
				return entityManager.find(ParcoursType.class, id);
				
		} catch (RuntimeException re) {
			
				log.error("get failed", re);
				throw re;
		}
	}
	
	/**
	 * [ParcoursTypeDaoImpl.findByQuery] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:03:34
	 * @param query
	 * @return
	 */
	@Override 
	public List<ParcoursType> findByQuery(String strQuery) {
		
			log.debug("merging ParcoursType instance");
			try {
		
					TypedQuery<ParcoursType>  query = entityManager.createQuery(new String(strQuery), ParcoursType.class);
					return query.getResultList();
			}
			catch (RuntimeException re) {
		
				log.error("merge failed", re);
				throw re;
			}
		
	}
	
	/**
	 * [ParcoursTypeDaoImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:03:41
	 * @return
	 */
	@Override 
	public List<ParcoursType> findAll() {
		
		log.debug("getting all ParcoursType instances");
		
		try {
				
				TypedQuery<ParcoursType> query = entityManager.createQuery("SELECT pt FROM ParcoursType pt", ParcoursType.class);
				log.debug("findAll 'ParcoursType' successful");
				return query.getResultList();
				
		} catch (RuntimeException re) {
				
				log.error("findAll 'ParcoursType' failed", re);
				return new ArrayList<ParcoursType>();	
		}

	}
	
public  List<ParcoursType> findByOfId(int ofId){
		
		log.debug("findByOfId instances");
		
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT p from  ParcoursType  p");
			queryBuilder.append(" WHERE p.offreFormation.id =  :ofId");

			TypedQuery<ParcoursType> query = entityManager.createQuery(queryBuilder.toString(),ParcoursType.class);
			query.setParameter("ofId", ofId);

			log.debug("findByOfId  successful. ");
			return query.getResultList();

		} catch (RuntimeException re) {

				log.error("findByOfId  failed", re);
				return null;
	
		}
	}
}
