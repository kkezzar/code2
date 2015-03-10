/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.offreformation.OffreFormationDetailDaoImpl.java] 
 * @author rlaib on : 6 f�vr. 2014  13:06:20
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
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

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationEquipeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationEquipe;


@Service ("offreFormationEquipeDao")
public class OffreFormationEquipeDaoImpl implements OffreFormationEquipeDao{

	private static final Logger log = LoggerFactory.getLogger(OffreFormationEquipeDaoImpl.class.getName());
	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	/**
	 * [OffreFormationEquipeDaoImpl.persist] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:17:19
	 * @param transientInstance
	 */
	@Override
	public void persist(OffreFormationEquipe transientInstance) {
		log.debug("persisting OffreFormationEquipe  instance");
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
	 * [OffreFormationEquipeDaoImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:17:25
	 * @param persistentInstance
	 */
	@Override 
	public void remove(OffreFormationEquipe persistentInstance) {
		log.debug("removing OffreFormationEquipe instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");

		} catch (RuntimeException re) {

			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * [OffreFormationEquipeDaoImpl.merge] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:17:31
	 * @param detachedInstance
	 * @return
	 */
	@Override 
	public OffreFormationEquipe merge(OffreFormationEquipe detachedInstance) {
		log.debug("merging OffreFormationEquipe instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * [OffreFormationEquipeDaoImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:17:37
	 * @param id
	 * @return
	 */
	@Override 
	public OffreFormationEquipe findById(int id) {
		log.debug("getting OffreFormationEquipe instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(OffreFormationEquipe.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * [OffreFormationEquipeDaoImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:17:42
	 * @return
	 */
	@SuppressWarnings("unchecked") 
	@Override 
	public List<OffreFormationEquipe> findAll() {
		
			log.debug("getting all OffreFormationEquipe instances");
			try {
			
						Query query = entityManager.createQuery("from  OffreFormationEquipe offreFormationEquipe ORDER BY offreFormationEquipe.libelleFr asc");
						log.debug("findAll 'OffreFormationEquipe' successful");
						return query.getResultList();

			} catch (RuntimeException re) {

						log.error("findAll 'OffreFormationEquipe' failed", re);
						return new ArrayList<OffreFormationEquipe>();
			}
	}

	/**
	 * [OffreFormationEquipeDaoImpl.findOfEquipeById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:17:49
	 * @param ofId
	 * @return
	 */
	@Override 
	public OffreFormationEquipe findOfEquipeById(int ofId) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<OffreFormationEquipe> c = cb.createQuery(OffreFormationEquipe.class);
			Root<OffreFormationEquipe> ofe = c.from(OffreFormationEquipe.class);
			Join<OffreFormationEquipe, OffreFormation> ofeoff = ofe.join("offreFormation");
			c.select(ofe).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(ofeoff.get("id"), ofId);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<OffreFormationEquipe> q = entityManager.createQuery(c);
			List<OffreFormationEquipe> result = q.getResultList();
			for (OffreFormationEquipe entity : result) {
				if (entityManager.contains(entity)) {
					entityManager.refresh(entity);
				}
			}
			if (result != null && result.size() > 0)
				return result.get(0);
			return null;
		} catch (RuntimeException re) {
			log.error("findOfEquipeById 'OffreFormationEquipe' failed", re);
			throw re;
		}
	}

}
