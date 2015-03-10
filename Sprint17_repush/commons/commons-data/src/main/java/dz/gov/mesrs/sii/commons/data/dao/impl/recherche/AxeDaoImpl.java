/**
 * [dz.gov.mesrs.sii.commons.data.dao.impl.recherche.AxeDaoImpl.java] 
 * @author rlaib on : 27 janv. 2015  13:55:41
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.recherche;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.recherche.AxeDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Axe;
import dz.gov.mesrs.sii.commons.data.model.recherche.Programme;

/**
 * @author rlaib  on : 27 janv. 2015  13:55:41
 */
@Service ("axeDao")
public class AxeDaoImpl extends GenericFveDaoImpl<Axe>  implements AxeDao {

	private static final Log log = LogFactory.getLog(AxeDaoImpl.class);
	/**
	 * [AxeDaoImpl.findByIdProgramme] Method 
	 * Overridden By : @author rlaib  on : 27 janv. 2015  13:55:41
	 * @param idProgamme
	 * @return
	 */
	@Override
	public List<Axe> findByIdProgramme(Long idProgamme) {
	
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Axe> c = cb.createQuery(Axe.class);
			Root<Axe> a = c.from(Axe.class);
			Join<Axe, Programme> ap = a.join("programme");
			c.select(a).distinct(true);
			c.orderBy(cb.desc(a.get("id")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(idProgamme != null) {
				ParameterExpression<Long> p = cb.parameter(Long.class, "idProgamme");
				criteria.add(cb.equal(ap.get("id"), p));
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Axe> q = entityManager.createQuery(c);
			if (idProgamme != null) { 
				q.setParameter("idProgamme", idProgamme);
			}
			List<Axe> result = q.getResultList();
				for(Axe entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
			return result;
		}
		catch (RuntimeException re) {

				log.error("findByIdProgramme 'Axe' failed", re);
				throw re;
		}
	}
}
