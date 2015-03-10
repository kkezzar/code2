/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.bac.GenerationDaoImpl.java] 
 * @author rlaib on : 30 juin 2014  10:41:41
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.bac;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.bac.GenerationDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.Generation;

/**
 * @author rlaib  on : 30 juin 2014  10:41:41
 */
@Service ("generationDao")
public class GenerationDaoImpl implements GenerationDao {

	private static final Logger log = LoggerFactory.getLogger(GenerationDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	/**
	 * [GenerationDaoImpl.persist] Method 
	 * @author rlaib  on : 30 juin 2014  10:41:41
	 * @param transientInstance
	 */
	@Override
	@Transactional(readOnly = true)
	public void persist(Generation transientInstance) {
		log.debug("persisting Generation instance");
		try {
				if(entityManager.contains(transientInstance))
					entityManager.refresh(transientInstance);
					entityManager.persist(transientInstance);
					log.debug("persist successful");
					
		} catch (RuntimeException re) {
			
					log.error("persist failed", re);
					throw re;
		}


	}

	@Override
	public Generation merge(Generation detachedInstance) {

		log.debug("merging Generation instance");
		
		try {
					log.debug("merge successful");
					return entityManager.merge(detachedInstance);
					
		} catch (RuntimeException re) {
			
					log.error("merge failed", re);
					throw re;
		}
	}
	
	/**
	 * [GenerationDaoImpl.findById] Method 
	 * @author rlaib  on : 30 juin 2014  10:41:41
	 * @param id
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Generation findById(int id) {
			log.debug("getting Generation instance with id: " + id);
		
			try {			
					log.debug("get successful");
					return entityManager.find(Generation.class, id);
					
			} catch (RuntimeException re) {
				
					log.error("get failed", re);
					throw re;
			}
	}

	/**
	 * [GenerationDaoImpl.findAll] Method 
	 * @author rlaib  on : 30 juin 2014  10:41:41
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Generation> findAll() {
				
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Generation> c = cb.createQuery(Generation.class);
			Root<Generation> g = c.from(Generation.class);
			c.select(g).distinct(true);
			TypedQuery<Generation> q = entityManager.createQuery(c);
			List<Generation> result = q.getResultList();
			for(Generation entity: result){
		        if(entityManager.contains(entity)){
		        	entityManager.refresh(entity);
		        }
		    }
			return q.getResultList();
		}
		catch (RuntimeException re) {

			log.error("findAll 'Generation' failed", re);
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public  List<Generation> findByEtab(String codeEtab){
				
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Generation> c = cb.createQuery(Generation.class);
			Root<Generation> g = c.from(Generation.class);
			c.select(g).distinct(true);
			c.orderBy(cb.desc(g.get("anneeAcademique")), cb.asc(g.get("dateDebutGeneration")));

			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(g.get("refCodeEtablissement"), codeEtab);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Generation> q = entityManager.createQuery(c);
			List<Generation> result = q.getResultList();
			for(Generation entity: result){
		        if(entityManager.contains(entity)){
		        	entityManager.refresh(entity);
		        }
		    }
			return q.getResultList();
		}
		catch (RuntimeException re) {

			log.error("findByEtab 'Generation' failed", re);
			throw re;
		}
	}

	/**
	 * [GenerationDaoImpl.findByTypeByYear] Method 
	 * @author rlaib  on : 30 juin 2014  10:41:41
	 * @param codeEtablissement
	 * @param year
	 * @return
	 */
	@Override
	public List<Generation> findByYear(String codeEtablissement,
			String year) {

			try {
						CriteriaBuilder cb = entityManager.getCriteriaBuilder();
						CriteriaQuery<Generation> c = cb.createQuery(Generation.class);
						Root<Generation> g = c.from(Generation.class);
						c.select(g).distinct(true);
				
						List<Predicate> criteria = new ArrayList<Predicate>();
						Predicate condition1 = cb.equal(g.get("anneeAcademique"), year);
						Predicate condition2 = cb.equal(g.get("refCodeEtablissement"), codeEtablissement);
						criteria.add(condition1);
						criteria.add(condition2);
						c.where(cb.and(criteria.toArray(new Predicate[0])));
				
						TypedQuery<Generation> q = entityManager.createQuery(c);
						List<Generation> result = q.getResultList();
						for(Generation entity: result){
					        if(entityManager.contains(entity)){
					        	entityManager.refresh(entity);
					        }
					    }
						return q.getResultList();
		}
		catch (RuntimeException re) {

			log.error("findByYear 'Generation' failed", re);
			throw re;
		}
	}

	/**
	 * [GenerationDaoImpl.getAllYears] Method 
	 * @author rlaib  on : 30 juin 2014  10:41:41
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public List<String> getAllYears() {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<String> c = cb.createQuery(String.class);
			Root<Generation> g = c.from(Generation.class);
			c.select(g.<String>get("anneeBac")).distinct(true);
			c.orderBy(cb.desc(g.get("anneeBac")));
			TypedQuery<String> q = entityManager.createQuery(c);
			return q.getResultList();
		}

		catch (RuntimeException re) {

			log.error("getAllYears 'Generation' failed", re);
			throw re;
		}
	}

	@Override
	public void flushAndClear() {
		
		try {
			entityManager.flush();
			entityManager.clear();
		}
		catch (RuntimeException re) {
			log.error("flushAndClear failed", re);
			throw re;
		}
	}
}
