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
import dz.gov.mesrs.sii.commons.data.dao.recherche.DemandeOperationDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.DemandeBudget;
import dz.gov.mesrs.sii.commons.data.model.recherche.DemandeOperation;

/**
 * @author rlaib  on : 25 janv. 2015  11:14:12
 */
@Service ("demandeOperationDao")
public class DemandeOperationDaoImpl extends GenericFveDaoImpl<DemandeOperation> implements DemandeOperationDao {

	private static final Log log = LogFactory.getLog(DemandeOperationDaoImpl.class);
	
	@Override
	public List<DemandeOperation> findByIdDemand(Long idDemand) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DemandeOperation> c = cb.createQuery(DemandeOperation.class);
			Root<DemandeOperation> dp = c.from(DemandeOperation.class);
			Join<DemandeOperation, DemandeBudget> dop= dp.join("demandeBudget");
			c.select(dp).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(idDemand != null) {
				ParameterExpression<Long> p = cb.parameter(Long.class, "idDemand");
				criteria.add(cb.equal(dop.get("id"), p));
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));                            
			TypedQuery<DemandeOperation> q = entityManager.createQuery(c);
			if (idDemand != null) { 
				q.setParameter("idDemand", idDemand);
			}
			List<DemandeOperation> result = q.getResultList();
			for(DemandeOperation entity: result){
				if(entityManager.contains(entity)){
						entityManager.refresh(entity);
				}
			}
			return result;
		}
		catch (RuntimeException re) {
			log.error("findByIdProgramme 'DemandeOperation' failed", re);
			throw re;
		}
	}
	
}
