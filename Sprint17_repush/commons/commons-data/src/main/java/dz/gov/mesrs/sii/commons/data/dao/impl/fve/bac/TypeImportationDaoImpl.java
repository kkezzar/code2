/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.bac.TypeImportationDaoImpl.java] 
 * @author  Rafik LAIB on : 25 mai 2014  17:11:41
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

import dz.gov.mesrs.sii.commons.data.dao.fve.bac.TypeImportationDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.bpm.ProcessusI18nDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.TypeImportation;

/**
 * @author  Rafik LAIB  on : 25 mai 2014  17:11:41
 */
@Service ("typeImportationDao")
public class TypeImportationDaoImpl implements TypeImportationDao {

	private static final Logger log = LoggerFactory.getLogger(TypeImportationDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	
	/**
	 * [TypeImportationDaoImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 25 mai 2014  17:11:41
	 * @param id
	 * @return
	 */
	@Override
	public TypeImportation findById(int id) {
		
		log.debug("getting TypeImportation instance with id: " + id);
		
		try {			
					log.debug("get successful");
					return entityManager.find(TypeImportation.class, id);
					
		} catch (RuntimeException re) {
			
					log.error("findById TypeImportation  failed", re);
					throw re;
		}
	}

	/**
	 * [TypeImportationDaoImpl.findByCode] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 25 mai 2014  17:11:41
	 * @param id
	 * @return
	 */
	@Override
	public TypeImportation findByCode(String  code) {
		

		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<TypeImportation> c = cb.createQuery(TypeImportation.class);
					Root<TypeImportation> ti = c.from(TypeImportation.class);
					c.select(ti).distinct(true);
					List<Predicate> criteria = new ArrayList<Predicate>();
					Predicate condition1 = cb.equal(ti.get("code"), code);
					criteria.add(condition1);
					 c.where(cb.and(criteria.toArray(new Predicate[0])));
					TypedQuery<TypeImportation> q = entityManager.createQuery(c);
					return q.getSingleResult();
			}
			catch (RuntimeException re) {
				
					log.error("findByCode 'TypeImportation' failed", re);
					throw re;
			}
	
	}

	/**
	 * [TypeImportationDaoImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 25 mai 2014  17:11:41
	 * @return
	 */
	@Override
	public List<TypeImportation> findAll() {

		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<TypeImportation> c = cb.createQuery(TypeImportation.class);
					Root<TypeImportation> ti = c.from(TypeImportation.class);
					c.select(ti).distinct(true);
					c.orderBy(cb.asc(ti.get("libelleFr")));

					TypedQuery<TypeImportation> q = entityManager.createQuery(c);
					List<TypeImportation> result = q.getResultList();
					for(TypeImportation entity: result){
				        if(entityManager.contains(entity)){
				        	entityManager.refresh(entity);
				        }
				    }
					return q.getResultList();
		}
		catch (RuntimeException re) {
		
					log.error("findAll 'TypeImportation' failed", re);
					throw re;
		}
	}
	
}
