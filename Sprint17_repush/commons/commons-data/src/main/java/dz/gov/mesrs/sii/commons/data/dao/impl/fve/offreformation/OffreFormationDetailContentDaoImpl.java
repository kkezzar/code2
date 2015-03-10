/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.offreformation.OffreFormationDetailContentDaoImpl.java] 
 * @author rlaib on : 6 f�vr. 2014  14:32:55
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationDetailContentDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationDetailContent;

/**
 * @author rlaib  on : 6 f�vr. 2014  14:32:55
 */
@Service ("offreFormationDetailContentDao")
public class OffreFormationDetailContentDaoImpl implements 	OffreFormationDetailContentDao {

	private static final Logger log = LoggerFactory.getLogger(OffreFormationDetailContentDaoImpl.class.getName());
	@PersistenceContext
	private EntityManager entityManager;

	
	/**
	 * [OffreFormationDetailContentDaoImpl.persist] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:14:30
	 * @param transientInstance
	 */
	@Override
	public void persist(OffreFormationDetailContent transientInstance) {
		log.debug("persisting OffreFormationDetailContent  instance");
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
	 * [OffreFormationDetailContentDaoImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:14:39
	 * @param persistentInstance
	 */
	@Override 
	public void remove(OffreFormationDetailContent persistentInstance) {
		log.debug("removing OffreFormationDetailContent instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * [OffreFormationDetailContentDaoImpl.merge] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:14:51
	 * @param detachedInstance
	 * @return
	 */
	@Override 
	public OffreFormationDetailContent merge(OffreFormationDetailContent detachedInstance) {
		log.debug("merging OffreFormationDetailContent instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * [OffreFormationDetailContentDaoImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:15:04
	 * @param id
	 * @return
	 */
	@Override 
	public OffreFormationDetailContent findById(int id) {
		log.debug("getting OffreFormationDetailContent instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(OffreFormationDetailContent.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
		
	/**
	 * [OffreFormationDetailContentDaoImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:15:10
	 * @return
	 */
	@SuppressWarnings("unchecked") 
	@Override 
	public List<OffreFormationDetailContent> findAll() {
		log.debug("getting all OffreFormationDetailContent instances");
		try {
			
			Query query = entityManager.createQuery("from  OffreFormationDetailContent offreFormationDetailContent");
			log.debug("findAll 'OffreFormationDetailContent' successful");
			
			return query.getResultList();
		
		} catch (RuntimeException re) {
			log.error("findAll 'OffreFormationDetailContent' failed", re);
			return new ArrayList<OffreFormationDetailContent>();
		}
	}
	
	/**
	 * [OffreFormationDetailContentDaoImpl.findOfContentTreeById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:15:16
	 * @param ofId
	 * @return
	 */
	@Override 
	public List<OffreFormationDetailContent> findOfContentTreeById(int ofId) {
		
		log.debug("Finding OF  tree content detail by Of ID : " + ofId);
		
		try {			
			
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT c from  OffreFormationDetailContent  c");
					queryBuilder.append(" where c.offreFormation.id =  :ofId");
					queryBuilder.append(" ORDER BY c.offreFormationDetail.libelleFr asc");
					
					Query query = entityManager.createQuery(queryBuilder.toString());
					query.setParameter("ofId", ofId);
					
					List<OffreFormationDetailContent> result = (List<OffreFormationDetailContent>) query.getResultList();
					log.debug("Finding OF tree content successful. ");
					return  result;
					
		} catch (RuntimeException re) {
			
					log.error("findAll 'OffreFormationDetailContent' failed", re);
					return new ArrayList<OffreFormationDetailContent>();
		}
	}
}
