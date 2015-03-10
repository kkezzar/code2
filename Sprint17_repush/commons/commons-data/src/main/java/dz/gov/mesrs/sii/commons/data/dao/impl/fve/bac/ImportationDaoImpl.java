/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.bac.ImportationDaoImpl.java] 
 * @author  Rafik LAIB on : 26 mai 2014  15:13:03
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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.bac.ImportationDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.Importation;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.TypeImportation;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * @author  Rafik LAIB  on : 26 mai 2014  15:13:03
 */
@Service ("importationDao")
public class ImportationDaoImpl implements ImportationDao {

	private static final Logger log = LoggerFactory.getLogger(ImportationDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * [ImportationDaoImpl.persist] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 26 mai 2014  15:13:03
	 * @param transientInstance
	 */
	@Override
	public void persist(Importation transientInstance) {

		log.debug("persisting Importation instance");
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
	

	/**
	 * [ImportationDaoImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 26 mai 2014  15:13:03
	 * @param persistentInstance
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Importation persistentInstance) {

		log.debug("removing Importation instance");
		try {
					entityManager.remove(persistentInstance);
					log.debug("remove successful");
					
		} catch (RuntimeException re) {
			
					log.error("remove failed", re);
					throw re;
		}

	}

	/**
	 * [ImportationDaoImpl.merge] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 26 mai 2014  15:13:03
	 * @param detachedInstance
	 * @return
	 */
	@Override
	public Importation merge(Importation detachedInstance) {

		log.debug("merging Importation instance");
		
		try {
					log.debug("merge successful");
					return entityManager.merge(detachedInstance);
					
		} catch (RuntimeException re) {
			
					log.error("merge failed", re);
					throw re;
		}
	}

	/**
	 * [ImportationDaoImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 26 mai 2014  15:13:03
	 * @param id
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Importation findById(int id) {

		log.debug("getting Importation instance with id: " + id);
		
		try {			
					log.debug("get successful");
					return entityManager.find(Importation.class, id);
					
		} catch (RuntimeException re) {
				
					log.error("get failed", re);
					throw re;
		}
		
	}
	
	/**
	 * [ImportationDaoImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 26 mai 2014  15:13:03
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Importation> findAll() {

		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<Importation> c = cb.createQuery(Importation.class);
					Root<Importation> i = c.from(Importation.class);
					c.select(i).distinct(true);
					c.orderBy(cb.desc(i.get("anneeBac")), cb.asc(i.get("dateDebutImportation")));

					TypedQuery<Importation> q = entityManager.createQuery(c);
					List<Importation> result = q.getResultList();
					for(Importation entity: result){
				        if(entityManager.contains(entity)){
				        	entityManager.refresh(entity);
				        }
				    }
					return q.getResultList();
		}
		
		catch (RuntimeException re) {

					log.error("findAll 'Importation' failed", re);
					throw re;
		}
		
	}

	/**
	 * [ImportationDaoImpl.findByTypeByYear] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 1 juin 2014  17:25:14
	 * @param typeCode
	 * @param year
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public  List<Importation> findByTypeByYear(String typeCode, String year){
		
			try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<Importation> c = cb.createQuery(Importation.class);
					Root<Importation> imp = c.from(Importation.class);
					Join<Importation, TypeImportation> it = imp.join("typeImportation");
					c.select(imp).distinct(true);
			
					List<Predicate> criteria = new ArrayList<Predicate>();
					Predicate condition1 = cb.equal(imp.get("anneeBac"), year);
					Predicate condition2 = cb.equal(imp.get("situation"), UtilConstant.BAC_IMPORT_STATUS_TERMINATED_CODE);
					Predicate condition3 = cb.equal(it.get("code"), typeCode);
					criteria.add(condition1);
					criteria.add(condition2);
					criteria.add(condition3);
					c.where(cb.and(criteria.toArray(new Predicate[0])));
			
					TypedQuery<Importation> q = entityManager.createQuery(c);
					List<Importation> result = q.getResultList();
					for(Importation entity: result){
				        if(entityManager.contains(entity)){
				        	entityManager.refresh(entity);
				        }
				    }
					return result;
			}
			catch (RuntimeException re) {
		
					log.error("findByTypeByYear 'Importation' failed", re);
					throw re;
			}
	}

	/**
	 * [ImportationDaoImpl.getAllYears] Method 
	 * @author rlaib  on : 23 juin 2014  08:31:40
	 * @return
	 */
	@Override
	public  List<String> getAllYears() {
		
		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<String> c = cb.createQuery(String.class);
					Root<Importation> i = c.from(Importation.class);
					c.select(i.<String>get("anneeBac")).distinct(true);
					c.orderBy(cb.desc(i.get("anneeBac")));
					List<Predicate> criteria = new ArrayList<Predicate>();
					Predicate condition = cb.equal(i.get("situation"), UtilConstant.BAC_IMPORT_STATUS_TERMINATED_CODE);
					criteria.add(condition);
					c.where(cb.and(criteria.toArray(new Predicate[0])));
					TypedQuery<String> q = entityManager.createQuery(c);
					return q.getResultList();
		}

		catch (RuntimeException re) {

			log.error("getAllYears 'Importation' failed", re);
			throw re;
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public  List<Importation> findByYear(String year) {
		
		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<Importation> c = cb.createQuery(Importation.class);
					Root<Importation> imp = c.from(Importation.class);
					c.select(imp).distinct(true);
					List<Predicate> criteria = new ArrayList<Predicate>();
					Predicate condition1 = cb.equal(imp.get("anneeBac"), year);
					Predicate condition2 = cb.equal(imp.get("situation"), UtilConstant.BAC_IMPORT_STATUS_TERMINATED_CODE);
					criteria.add(condition1);
					criteria.add(condition2);
					c.where(cb.and(criteria.toArray(new Predicate[0])));
					TypedQuery<Importation> q = entityManager.createQuery(c);
					List<Importation> result = q.getResultList();
					for(Importation entity: result){
				        if(entityManager.contains(entity)){
				        	entityManager.refresh(entity);
				        }
				    }
					return result;
		}
		catch (RuntimeException re) {

				log.error("findByYear 'Importation' failed", re);
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
