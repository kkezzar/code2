package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.ParcoursTypeI18nDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ParcoursTypeI18n;

@Service ("parcoursTypeI18nDao")
public class ParcoursTypeI18nDaoImpl implements ParcoursTypeI18nDao {

	private static final Logger log = LoggerFactory.getLogger(ParcoursTypeI18nDaoImpl.class.getName());

	@PersistenceContext 
	private EntityManager entityManager;
	
	/**
	 * [OffreFormationI18nDaoImpl.persist] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:12:12
	 * @param transientInstance
	 */
	@Override 
	public void persist(ParcoursTypeI18n transientInstance) {
		
		log.debug("persisting ParcoursTypeI18n instance");
		
		try {
			
				entityManager.persist(transientInstance);
				log.debug("persist successful");
				
		} catch (RuntimeException re) {
			
				log.error("persist failed", re);
				throw re;
		
		}
	}

	/**
	 * [OffreFormationI18nDaoImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:12:18
	 * @param persistentInstance
	 */
	@Override 
	public void remove(ParcoursTypeI18n persistentInstance) {
		
			log.debug("removing ParcoursTypeI18n instance");
			
			try {
					
						entityManager.remove(persistentInstance);
						log.debug("remove successful");
			
			} catch (RuntimeException re) {
			
						log.error("remove failed", re);
						throw re;
		}
	}
 
	/**
	 * [OffreFormationI18nDaoImpl.merge] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:12:24
	 * @param detachedInstance
	 * @return
	 */
	@Override 
	public ParcoursTypeI18n merge(ParcoursTypeI18n detachedInstance) {
				
				log.debug("merging ParcoursTypeI18n instance");
				
				try {
					
						log.debug("merge successful");
						return entityManager.merge(detachedInstance);
					
				} catch (RuntimeException re) {
				
						log.error("merge failed", re);
						throw re;
				}
	}

	/**
	 * [OffreFormationI18nDaoImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:12:31
	 * @param id
	 * @return
	 */
	@Override 
	public ParcoursTypeI18n findById(int id) {
		
			log.debug("getting ParcoursTypeI18n instance with id: " + id);
			
			try {			
					
					log.debug("findById successful");
					return entityManager.find(ParcoursTypeI18n.class, id);
					
					
		} catch (RuntimeException re) {
					
					log.error("findById failed", re);
					throw re;
		}
	}

	/**
	 * [OffreFormationI18nDaoImpl.findByOfId] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:12:36
	 * @param codeNat
	 * @return
	 */
	@Override 
	public List<ParcoursTypeI18n> findByOfId(int ofId) {
		
			log.debug("getting ParcoursTypeI18n List with ofId : " + ofId);
			
			try {			
			
					StringBuilder strQuery = new StringBuilder();
					strQuery.append("SELECT pt FROM ParcoursTypeI18n pt ");
					strQuery.append(" WHERE pt.parcoursType.offreFormation.id = :ofId ");
	
					TypedQuery<ParcoursTypeI18n>  query = entityManager.createQuery(new String(strQuery), ParcoursTypeI18n.class);
					query.setParameter("ofId", ofId);
					
					log.debug("findByOfId successful");
					
					return query.getResultList();
			
			} catch (RuntimeException re) {
					
					log.error("findByOfId failed", re);
					throw re;
			}
	}
	
	
}
