/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.offreformation.CycleDaoImpl.java] 
 * @author rlaib on : 16 juil. 2014  10:16:27
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.CycleDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Cycle;

/**
 * @author rlaib  on : 16 juil. 2014  10:16:27
 */
@Service ("cycleDao")
public class CycleDaoImpl implements CycleDao {

	private static final Logger log = LoggerFactory.getLogger(CycleDaoImpl.class.getName());
	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	/**
	 * [CycleDaoImpl.persist] Method 
	 * @author rlaib  on : 16 juil. 2014  10:16:27
	 * @param transientInstance
	 */
	@Override
	public void persist(Cycle transientInstance) {
		
		log.debug("persisting Cycle instance");
		try {
					entityManager.persist(transientInstance);
					log.debug("persist successful");
					
		} catch (RuntimeException re) {
					
					log.error("persist Cycle failed", re);
					throw re;
		}

	}

	/**
	 * [CycleDaoImpl.remove] Method 
	 * @author rlaib  on : 16 juil. 2014  10:16:27
	 * @param persistentInstance
	 */
	@Override
	public void remove(int id) {
		
		log.debug("removing Cycle instance");
		try {
					Cycle persistentInstance = entityManager.find(Cycle.class, id);
					entityManager.remove(persistentInstance);
					log.debug("remove successful");
					
		} catch (RuntimeException re) {
					
					log.error("remove Cycle failed", re);
					throw re;
		}

	}

	/**
	 * [CycleDaoImpl.merge] Method 
	 * @author rlaib  on : 16 juil. 2014  10:16:27
	 * @param detachedInstance
	 * @return
	 */
	@Override
	public Cycle merge(Cycle detachedInstance) {
		log.debug("merging Cycle instance");
		try {
						log.debug("merge successful");
						return entityManager.merge(detachedInstance);
						
		} catch (RuntimeException re) {
						log.error("merge failed", re);
						throw re;
		}
	}

	/**
	 * [CycleDaoImpl.findById] Method 
	 * @author rlaib  on : 16 juil. 2014  10:16:27
	 * @param id
	 * @return
	 */
	@Override
	public Cycle findById(int id) {
		log.debug("getting Cycle instance with id: " + id);
		try {			
					log.debug("get successful");
					return entityManager.find(Cycle.class, id);
					
		} catch (RuntimeException re) {
			
					log.error("findById failed", re);
					throw re;
		}
	}

	@Override
	public  Cycle findByCode(String code) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Cycle> c = cb.createQuery(Cycle.class);
			Root<Cycle> cc = c.from(Cycle.class);
			c.select(cc).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition = cb.equal(cc.get("code"), code);
			criteria.add(condition);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Cycle> q = entityManager.createQuery(c);
			List<Cycle> result = q.getResultList();
			for(Cycle entity: result){
				if(entityManager.contains(entity)){
					entityManager.refresh(entity);
				}
			}
			Cycle cycle = null;
			if(result != null && result.size()>0)
				cycle = result.get(0);
			return cycle;
		}
		catch (RuntimeException re) {
			
			log.error("findByCode 'Cycle' failed", re);
			throw re;
		}
	}
	
	
	/**
	 * [CycleDaoImpl.findAll] Method 
	 * @author rlaib  on : 16 juil. 2014  10:16:27
	 * @return
	 */
	@Override
	public List<Cycle> findAll() {

		try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<Cycle> c = cb.createQuery(Cycle.class);
				Root<Cycle> cc = c.from(Cycle.class);
				c.select(cc).distinct(true);
				c.orderBy(cb.asc(cc.get("refCodeTypeFormation")),cb.asc(cc.get("rang")));
				TypedQuery<Cycle> q = entityManager.createQuery(c);
				List<Cycle> result = q.getResultList();
				for(Cycle entity: result){
			        if(entityManager.contains(entity)){
			        	entityManager.refresh(entity);
			        }
			    }
				return result;
		}
		catch (RuntimeException re) {

				log.error("findAll 'Cycle' failed", re);
				throw re;
		}
	}
	
	/**
	 * [CycleDaoImpl.findByTypeFormationCode] Method 
	 * @author rlaib  on : 16 juil. 2014  14:23:25
	 * @param codeTypeFormation
	 * @return
	 */
	@Override
	public List<Cycle> findByTypeFormationCode(String codeTypeFormation) {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Cycle> c = cb.createQuery(Cycle.class);
			Root<Cycle> cc = c.from(Cycle.class);
			c.select(cc).distinct(true);
			c.orderBy(cb.asc(cc.get("refCodeTypeFormation")),cb.asc(cc.get("rang")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition = cb.equal(cc.get("refCodeTypeFormation"), codeTypeFormation);
			criteria.add(condition);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Cycle> q = entityManager.createQuery(c);
			List<Cycle> result = q.getResultList();
			for(Cycle entity: result){
				if(entityManager.contains(entity)){
					entityManager.refresh(entity);
				}
			}
			return result;
		}
		catch (RuntimeException re) {
			
			log.error("findByTypeFormationCode 'Cycle' failed", re);
			throw re;
		}
	}
	
	@Override
	public  Cycle findByTypeFormationByCode(String codeTypeFormation,String codeCycle) {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Cycle> c = cb.createQuery(Cycle.class);
			Root<Cycle> cc = c.from(Cycle.class);
			c.select(cc).distinct(true);
			c.orderBy(cb.asc(cc.get("refCodeTypeFormation")),cb.asc(cc.get("rang")));
			
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(cc.get("refCodeTypeFormation"), codeTypeFormation);
			criteria.add(condition1);
			Predicate condition2 = cb.equal(cc.get("code"), codeCycle);
			criteria.add(condition2);
			
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Cycle> q = entityManager.createQuery(c);
			List<Cycle> result = q.getResultList();
			for(Cycle entity: result){
				if(entityManager.contains(entity)){
					entityManager.refresh(entity);
				}
			}
			if(result != null && result.size() > 0)
				return result.get(0);
			
			return null;
		}
		catch (RuntimeException re) {
			
			log.error("findByTypeFormationByCode 'Cycle' failed", re);
			throw re;
		}
	}
}
