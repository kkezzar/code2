/**
 * [dz.gov.mesrs.sii.commons.data.dao.impl.recherche.ProgrammeDaoImpl.java] 
 * @author rlaib on : 25 janv. 2015  11:14:12
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
import dz.gov.mesrs.sii.commons.data.dao.recherche.ActiviteBudgetDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.ActiviteBudget;
import dz.gov.mesrs.sii.commons.data.model.recherche.DemandeBudget;
import dz.gov.mesrs.sii.commons.data.model.recherche.Programme;

/**
 * @author rlaib  on : 25 janv. 2015  11:14:12
 */
@Service ("activiteBudgetDao")
public class ActiviteBudgetDaoImpl extends GenericFveDaoImpl<ActiviteBudget> implements ActiviteBudgetDao {

	private static final Log log = LogFactory.getLog(ActiviteBudgetDaoImpl.class);
	
	/**
	 * [ActiviteBudgetDaoImpl.findByIdProgramme] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  12:30:14
	 * @param idDemand
	 * @return
	 */
	@Override
	public List<ActiviteBudget> findDemandActivities(Long idDemand) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ActiviteBudget> c = cb.createQuery(ActiviteBudget.class);
			Root<ActiviteBudget> ab = c.from(ActiviteBudget.class);
			Join<DemandeBudget, DemandeBudget> dbp= ab.join("demandeBudget");
			c.select(ab).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(idDemand != null) {
				ParameterExpression<Long> p = cb.parameter(Long.class, "idDemand");
				criteria.add(cb.equal(dbp.get("id"), p));
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));                            
			TypedQuery<ActiviteBudget> q = entityManager.createQuery(c);
			if (idDemand != null) { 
				q.setParameter("idDemand", idDemand);
			}
			List<ActiviteBudget> result = q.getResultList();
			for(ActiviteBudget entity: result){
				if(entityManager.contains(entity)){
						entityManager.refresh(entity);
				}
			}
			return result;
		}
		catch (RuntimeException re) {
			log.error("findDemandActivities 'ActiviteBudget' failed", re);
			throw re;
		}
	}
	
}
