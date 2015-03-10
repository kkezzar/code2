/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.offreformation.PeriodeParamDaoImpl.java] 
 * @author rlaib on : 2 oct. 2014  14:14:55
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.ParamTypeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ParamType;

/**
 * @author rlaib  on : 2 oct. 2014  14:14:55
 */
@Service ("paramTypeDao")
public class ParamTypeDaoImpl implements ParamTypeDao {

	private static final Logger log = LoggerFactory.getLogger(ParamTypeDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
		@Override
	public List<ParamType> findAll() {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ParamType> c = cb.createQuery(ParamType.class);
			Root<ParamType> pp = c.from(ParamType.class);
			c.select(pp).distinct(true);
			c.orderBy(cb.asc(pp.get("libelle")));
			TypedQuery<ParamType> q = entityManager.createQuery(c);
			List<ParamType> result = q.getResultList();
			for(ParamType entity: result){
		        if(entityManager.contains(entity)){
		        	entityManager.refresh(entity);
		        }
		    }
			return result;
		}
		catch (RuntimeException re) {

			log.error("findAll 'ParamType' failed", re);
			throw re;
		}
	}

		/**
		 * [PeriodeParamDaoImpl.findParamTypeById] Method 
		 * @author rlaib  on : 7 oct. 2014  09:44:06
		 * @param idType
		 * @return
		 */
		@Override
		public  ParamType findParamTypeById(int idType) {
			log.debug("getting ParamType instance with id: " + idType);
			try {			
						log.debug("get successful");
						return entityManager.find(ParamType.class, idType);
						
			} catch (RuntimeException re) {
				
						log.error("findById ParamType failed", re);
						throw re;
			}
		}
	
}
