/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.offreformation.OffreFormationStructureDaoImpl.java] 
 * @author rlaib on : 8 oct. 2014  16:18:01
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationStructureDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationStructure;

/**
 * @author rlaib  on : 8 oct. 2014  16:18:01
 */
@Service ("offreFormationStructureDao")
public class OffreFormationStructureDaoImpl implements
		OffreFormationStructureDao {

	private static final Logger log = LoggerFactory.getLogger(OffreFormationStructureDaoImpl.class.getName());

	@PersistenceContext 
	private EntityManager entityManager;
	
	/**
	 * [OffreFormationStructureDaoImpl.persist] Method 
	 * @author rlaib  on : 8 oct. 2014  16:18:01
	 * @param transientInstance
	 */
	@Override
	public void persist(OffreFormationStructure transientInstance) {
		
		log.debug("persisting OffreFormationStructure instance");
		try {
				entityManager.persist(transientInstance);
				log.debug("persist successful");
				
		} catch (RuntimeException re) {
			
				log.error("persist failed", re);
				throw re;
		}
	}

	/**
	 * [OffreFormationStructureDaoImpl.remove] Method 
	 * @author rlaib  on : 8 oct. 2014  16:18:01
	 * @param persistentInstance
	 */
	@Override
	public void remove(int idOffreFormationStructure) {
		log.debug("removing Periode instance");
		try {
					OffreFormationStructure persistentInstance = entityManager.find(OffreFormationStructure.class, idOffreFormationStructure);
					entityManager.remove(persistentInstance);
					log.debug("remove OffreFormationStructure successful");
					
		} catch (RuntimeException re) {
					
					log.error("remove OffreFormationStructure failed", re);
					throw re;
		}
	}

	/**
	 * [OffreFormationStructureDaoImpl.merge] Method 
	 * @author rlaib  on : 8 oct. 2014  16:18:01
	 * @param detachedInstance
	 * @return
	 */
	@Override
	public OffreFormationStructure merge(
			OffreFormationStructure detachedInstance) {
		log.debug("merging OffreFormationStructure instance");
		try {
				log.debug("merge successful");
				return entityManager.merge(detachedInstance);
			
		} catch (RuntimeException re) {
		
				log.error("merge failed", re);
				throw re;
		}
	}

	/**
	 * [OffreFormationStructureDaoImpl.findById] Method 
	 * @author rlaib  on : 8 oct. 2014  16:18:01
	 * @param id
	 * @return
	 */
	@Override
	public OffreFormationStructure findById(int id) {
		log.debug("getting OffreFormationStructure instance with id: " + id);
		try {			
				log.debug("findById successful");
				return entityManager.find(OffreFormationStructure.class, id);
				
		} catch (RuntimeException re) {
					
					log.error("findById failed", re);
					throw re;
		}
	}

	/**
	 * [OffreFormationStructureDaoImpl.findByOfId] Method 
	 * @author rlaib  on : 8 oct. 2014  16:18:01
	 * @param ofId
	 * @return
	 */
	@Override
	public List<OffreFormationStructure> findByOfId(int ofId) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<OffreFormationStructure> c = cb.createQuery(OffreFormationStructure.class);
			Root<OffreFormationStructure> offSructure = c.from(OffreFormationStructure.class);
			Join<OffreFormationStructure, OffreFormation> off = offSructure.join("offreFormation");
			c.select(offSructure).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(off.get("id"), ofId);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<OffreFormationStructure> q = entityManager.createQuery(c);
			List<OffreFormationStructure> result = q.getResultList();
			for(OffreFormationStructure entity: result){
		        if(entityManager.contains(entity)){
		        	entityManager.refresh(entity);
		        }
		    }
			return result;
	}
	catch (RuntimeException re) {
	
			log.error("findByOfId 'OffreFormationStructure' failed", re);
			throw re;
	}
	}
}
