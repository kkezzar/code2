/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.offreformation.OffreFormationDetailDaoImpl.java] 
 * @author rlaib on : 6 f�vr. 2014  13:06:20
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationContratDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationContrat;


@Service ("offreFormationContratDao") 
public class OffreFormationContratDaoImpl implements OffreFormationContratDao{

	private static final Logger log = LoggerFactory.getLogger(OffreFormationContratDaoImpl.class.getName());
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * [OffreFormationContratDaoImpl.persist] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:08:35
	 * @param transientInstance
	 */
	@Override
	public void persist(OffreFormationContrat transientInstance) {
		log.debug("persisting OffreFormationContrat  instance");
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
	 * [OffreFormationContratDaoImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:08:41
	 * @param persistentInstance
	 */
	@Override 
	public void remove(OffreFormationContrat persistentInstance) {
		
		log.debug("removing OffreFormationContrat instance");
		
		try {
			
			entityManager.remove(persistentInstance);
			log.debug("remove successful");

		} catch (RuntimeException re) {

			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * [OffreFormationContratDaoImpl.merge] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:08:47
	 * @param detachedInstance
	 * @return
	 */
	@Override 
	public OffreFormationContrat merge(OffreFormationContrat detachedInstance) {
		
		log.debug("merging OffreFormationContrat instance");
		
		try {
			
				log.debug("merge successful");
				return entityManager.merge(detachedInstance);
			
		} catch (RuntimeException re) {
			
				log.error("merge failed", re);
				throw re;
			
		}
	}

	/**
	 * [OffreFormationContratDaoImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:08:54
	 * @param id
	 * @return
	 */
	@Override 
	public OffreFormationContrat findById(int id) {
		log.debug("getting OffreFormationContrat instance with id: " + id);
		try {			
			
			log.debug("get successful");
			return entityManager.find(OffreFormationContrat.class, id);
			
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public  OffreFormationContrat findByRefCode(String refCode){
		
				log.debug("findByRefCode instances");
		
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT c from  OffreFormationContrat  c");
					queryBuilder.append(" WHERE c.refCodeContrat =  :refCode");
	
					Query query = entityManager.createQuery(queryBuilder.toString());
					query.setParameter("refCode", refCode);
	
					List<OffreFormationContrat> result = (List<OffreFormationContrat>) query.getResultList();
					log.debug("findByRefCode  successful. ");
					if(result.size()>0 && result!= null)
							return result.get(0);
					else
						return null;

				} catch (RuntimeException re) {

						log.error("findByRefCode  failed", re);
						return null;
			
				}
	}

	public  List<OffreFormationContrat> findByOfId(int ofId){
		
		log.debug("findByOfId instances");
		
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT c from  OffreFormationContrat  c");
			queryBuilder.append(" WHERE c.offreFormation.id =  :ofId");

			TypedQuery<OffreFormationContrat> query = entityManager.createQuery(queryBuilder.toString(),OffreFormationContrat.class);
			query.setParameter("ofId", ofId);

			log.debug("findByOfId  successful. ");
			return query.getResultList();

		} catch (RuntimeException re) {

				log.error("findByOfId  failed", re);
				return null;
	
		}
	}
	
	/**
	 * [OffreFormationContratDaoImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:09:00
	 * @return
	 */
	@SuppressWarnings("unchecked") 
	@Override 
	public List<OffreFormationContrat> findAll() {
		
			log.debug("getting all OffreFormationContrat instances");
			try {
			
						Query query = entityManager.createQuery("from  OffreFormationContrat offreFormationContrat ORDER BY offreFormationContrat.libelleFr asc");
						log.debug("findAll 'OffreFormationContrat' successful");
						return query.getResultList();

			} catch (RuntimeException re) {

						log.error("findAll 'OffreFormationContrat' failed", re);
						return new ArrayList<OffreFormationContrat>();
			}
	}
	
}
