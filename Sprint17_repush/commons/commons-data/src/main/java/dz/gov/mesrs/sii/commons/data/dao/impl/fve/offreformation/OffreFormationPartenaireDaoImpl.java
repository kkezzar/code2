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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationPartenaireDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationPartenaire;


@Service("offreFormationPartenaireDao") 
public class OffreFormationPartenaireDaoImpl implements OffreFormationPartenaireDao{

	private static final Logger log = LoggerFactory.getLogger(OffreFormationPartenaireDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * [OffreFormationPartenaireDaoImpl.persist] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:48:59
	 * @param transientInstance
	 */
	@Override
	public void persist(OffreFormationPartenaire transientInstance) {
		
		log.debug("persisting OffreFormationPartenaire  instance");
		
		try {
					entityManager.persist(transientInstance);
					log.debug("persist successful");

		} catch (RuntimeException re) {

					log.error("persist failed", re);
					throw re;
		}
	}

	/**
	 * [OffreFormationPartenaireDaoImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:48:51
	 * @param id
	 */
	@Override 
	public void remove(int id) {
		
		log.debug("removing OffreFormationPartenaire instance");
		
		try {
					//entityManager.remove(persistentInstance);
					OffreFormationPartenaire persistentInstance = entityManager.find(OffreFormationPartenaire.class, id);
					entityManager.remove(entityManager.contains(persistentInstance) ? persistentInstance : entityManager.merge(persistentInstance));
					log.debug("remove successful");

		} catch (RuntimeException re) {

					log.error("remove failed", re);
					throw re;
		}
	}

	/**
	 * [OffreFormationPartenaireDaoImpl.merge] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:48:45
	 * @param detachedInstance
	 * @return
	 */
	@Override 
	public OffreFormationPartenaire merge(OffreFormationPartenaire detachedInstance) {
		
		log.debug("merging OffreFormationPartenaire instance");
		
		try {
					log.debug("merge successful");
					return entityManager.merge(detachedInstance);
					
		} catch (RuntimeException re) {
			
					log.error("merge failed", re);
					throw re;
		}
	}

	/**
	 * [OffreFormationPartenaireDaoImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:48:38
	 * @param id
	 * @return
	 */
	@Override 
	public OffreFormationPartenaire findById(int id) {
		
		log.debug("getting OffreFormationPartenaire instance with id: " + id);
		
		try {			
					log.debug("get successful");
					return entityManager.find(OffreFormationPartenaire.class, id);
					
		} catch (RuntimeException re) {
					
					log.error("get failed", re);
					throw re;
		}
	}

	/**
	 * [OffreFormationPartenaireDaoImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:48:32
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override 
	public List<OffreFormationPartenaire> findAll() {
		
		log.debug("getting all OffreFormationPartenaire instances");
		
		try {
		
					Query query = entityManager.createQuery("from  OffreFormationPartenaire offreFormationPartenaire ORDER BY offreFormationPartenaire.libelleFr asc");
					log.debug("findAll 'OffreFormationPartenaire' successful");
					return query.getResultList();

		} catch (RuntimeException re) {

					log.error("findAll 'OffreFormationPartenaire' failed", re);
					return new ArrayList<OffreFormationPartenaire>();
					
		}
	}

	/**
	 * [OffreFormationPartenaireDaoImpl.findPartnersByContractId] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:48:24
	 * @param contractId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override 
	public  List<OffreFormationPartenaire> findPartnersByContractId(int contractId) {
				
				log.debug("findPartnersByContractId instances");
		
				try {
							StringBuilder queryBuilder = new StringBuilder();
							queryBuilder.append("SELECT p from  OffreFormationPartenaire  p");
							queryBuilder.append(" where p.offreFormationContrat.id =  :contractId");
			
							Query query = entityManager.createQuery(queryBuilder.toString());
							query.setParameter("contractId", contractId);
			
							List<OffreFormationPartenaire> result = (List<OffreFormationPartenaire>) query.getResultList();
							log.debug("findPartnersByContractId  successful. ");
							return result;

				} catch (RuntimeException re) {

							log.error("findPartnersByContractId  failed", re);
							return new ArrayList<OffreFormationPartenaire>();
					
				}
		}

}
