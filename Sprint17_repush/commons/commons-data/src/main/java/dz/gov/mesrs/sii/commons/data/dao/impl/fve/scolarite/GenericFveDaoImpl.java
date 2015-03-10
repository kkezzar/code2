/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.scolarite.GenericFveDaoImpl.java] 
 * @author rlaib on : 12 oct. 2014  15:34:12
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;


/**
 * @author rlaib  on : 12 oct. 2014  15:34:12
 * @param <T>
 */

public abstract class GenericFveDaoImpl<T> implements GenericFveDao<T> {
	
	private static final Logger log = LoggerFactory.getLogger(GenericFveDaoImpl.class.getName());
	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	public EntityManager entityManager;
	private Class<T> type;
	
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericFveDaoImpl() {
	        Type t = getClass().getGenericSuperclass();
	        ParameterizedType pt = (ParameterizedType) t;
	        type = (Class) pt.getActualTypeArguments()[0];
	    }

	    @Override
	    public T persist(final T t) {
	    	
	    	log.debug("persisting Niveau instance");
			try {
						this.entityManager.persist(t);
//						this.entityManager.flush();
//						this.entityManager.refresh(t);
						log.debug("persist successful");
						return t;
						
			} catch (RuntimeException re) {
						
						log.error("persist Niveau failed", re);
						throw re;
			}
	    }

	    @Override
	    public void remove(final Object id) {
	    	
	    	log.debug("Generic removing instance");
			try {
						 this.entityManager.remove(this.entityManager.getReference(type, id));
						log.debug("Generic remove successful");
						
			} catch (RuntimeException re) {
						
						log.error("Generic remove failed", re);
						throw re;
			}
	       
	    }

	    @Override
	    public T findById(final Object id) {
	       
	    	log.debug("Generic getting instance with id: " + id);
			try {			
						log.debug("Generic get successful");
						 return (T) this.entityManager.find(type, id);
						
			} catch (RuntimeException re) {
				
						log.error("Generic findById failed", re);
						throw re;
			}
	    }

	    @Override
	    public T merge(final T t) {
	          
	        log.debug("Generic merging instance");
			try {
							 this.entityManager.merge(t);
							 return t;
							
			} catch (RuntimeException re) {
							log.error("Generic merge failed", re);
							throw re;
			}
	    }
	
	    @SuppressWarnings("unchecked")
		@Override
		public List<T> findAll() {
			String queryAll = "select o from " +
					type.getSimpleName() + " o order by id";
			Query query = entityManager.createQuery(queryAll);
			List<T> result = query.getResultList();
			for(T entity: result){
		        if(entityManager.contains(entity)){
		        	entityManager.refresh(entity);
		        }
		    }
			return result;
		}
	 
	    @Override
	    public void flushAndClear() {
	    	
	    	log.debug("Generic flushAndRefresh");
			try {
						entityManager.flush();
						entityManager.clear();
						log.debug("Generic flushAndRefresh successful");
						
			} catch (RuntimeException re) {
						
						log.error("Generic remove failed", re);
						throw re;
			}
	       
	    }

}
