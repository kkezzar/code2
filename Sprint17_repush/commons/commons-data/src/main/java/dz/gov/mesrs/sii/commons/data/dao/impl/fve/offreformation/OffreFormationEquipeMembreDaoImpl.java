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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationEquipeMembreDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationEquipe;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationEquipeMembre;
//


@Service("offreFormationEquipeMembreDao") 
public class OffreFormationEquipeMembreDaoImpl implements OffreFormationEquipeMembreDao {

	private static final Logger log = LoggerFactory.getLogger(OffreFormationEquipeMembreDaoImpl.class.getName());
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * [OffreFormationEquipeMembreDaoImpl.persist] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:18:00
	 * @param transientInstance
	 */
	@Override
	public void persist(OffreFormationEquipeMembre transientInstance) {
		
		log.debug("persisting OffreFormationEquipeMembre  instance");
		
		try {
					entityManager.persist(transientInstance);
					log.debug("persist successful");

		} catch (RuntimeException re) {

					log.error("persist failed", re);
					throw re;
		}
	}

	/**
	 * [OffreFormationEquipeMembreDaoImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:18:06
	 * @param id
	 */
	@Override 
	public void remove(int id) {
		
		log.debug("removing OffreFormationEquipeMembre instance");
		
		try {
					//entityManager.remove(persistentInstance);
					OffreFormationEquipeMembre persistentInstance = entityManager.find(OffreFormationEquipeMembre.class, id);
					entityManager.remove(entityManager.contains(persistentInstance) ? persistentInstance : entityManager.merge(persistentInstance));
					log.debug("remove successful");

		} catch (RuntimeException re) {

					log.error("remove failed", re);
					throw re;
		}
	}

	/**
	 * [OffreFormationEquipeMembreDaoImpl.merge] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:18:12
	 * @param detachedInstance
	 * @return
	 */
	@Override 
	public OffreFormationEquipeMembre merge(OffreFormationEquipeMembre detachedInstance) {
		
		log.debug("merging OffreFormationEquipeMembre instance");
		
		try {
					log.debug("merge successful");
					return entityManager.merge(detachedInstance);
					
		} catch (RuntimeException re) {
			
					log.error("merge failed", re);
					throw re;
		}
	}

	/**
	 * [OffreFormationEquipeMembreDaoImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:18:18
	 * @param id
	 * @return
	 */
	@Override 
	public OffreFormationEquipeMembre findById(int id) {
		
		log.debug("getting OffreFormationEquipeMembre instance with id: " + id);
		
		try {			
					log.debug("get successful");
					return entityManager.find(OffreFormationEquipeMembre.class, id);
					
		} catch (RuntimeException re) {
					
					log.error("get failed", re);
					throw re;
		}
	}

	/**
	 * [OffreFormationEquipeMembreDaoImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:18:25
	 * @return
	 */
	@Override 
	public List<OffreFormationEquipeMembre> findAll() {
		
		log.debug("getting all OffreFormationEquipeMembre instances");
		
		try {
		
					Query query = entityManager.createQuery("from  OffreFormationEquipeMembre offreFormationEquipeMembre ORDER BY offreFormationEquipeMembre.libelleFr asc");
					log.debug("findAll 'OffreFormationEquipeMembre' successful");
					return query.getResultList();

		} catch (RuntimeException re) {

					log.error("findAll 'OffreFormationEquipeMembre' failed", re);
					return new ArrayList<OffreFormationEquipeMembre>();
					
		}
	}

	/**
	 * [OffreFormationEquipeMembreDaoImpl.findMembersByTeamId] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:18:32
	 * @param teamId
	 * @return
	 */
	@Override 
	public  List<OffreFormationEquipeMembre> findMembersByTeamId(int teamId) {
				
				log.debug("findMembersByTeamId instances");
				try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<OffreFormationEquipeMembre> c = cb.createQuery(OffreFormationEquipeMembre.class);
					Root<OffreFormationEquipeMembre> ofem = c.from(OffreFormationEquipeMembre.class);
					Join<OffreFormationEquipeMembre, OffreFormationEquipe> ofemOfe= ofem.join("offreFormationEquipe");
					c.select(ofem).distinct(true);
					List<Predicate> criteria = new ArrayList<Predicate>();
					Predicate condition1 = cb.equal(ofemOfe.get("id"), teamId);
					criteria.add(condition1);
					c.where(cb.and(criteria.toArray(new Predicate[0])));
					TypedQuery<OffreFormationEquipeMembre> q = entityManager.createQuery(c);
					List<OffreFormationEquipeMembre> result = q.getResultList();
					for (OffreFormationEquipeMembre entity : result) {
						if (entityManager.contains(entity)) {
							entityManager.refresh(entity);
						}
					}
					return result;

				} catch (RuntimeException re) {

							log.error("findMembersByTeamId  failed", re);
							throw re;
				}
		}

}
