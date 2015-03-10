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
import dz.gov.mesrs.sii.commons.data.dao.recherche.DemandeCreditDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.DemandeBudget;
import dz.gov.mesrs.sii.commons.data.model.recherche.DemandeCredit;

/**
 * @author rlaib  on : 25 janv. 2015  11:14:12
 */
@Service ("demandeCreditDao")
public class DemandeCreditDaoImpl extends GenericFveDaoImpl<DemandeCredit> implements DemandeCreditDao {

	private static final Log log = LogFactory.getLog(DemandeCreditDaoImpl.class);

	
	/**
	 * [DemandeCreditDaoImpl.findByIdProgramme] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  12:36:00
	 * @param idDemand
	 * @return
	 */
	@Override
	public List<DemandeCredit> findByIdDemand(Long idDemand) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DemandeCredit> c = cb.createQuery(DemandeCredit.class);
			Root<DemandeCredit> dc = c.from(DemandeCredit.class);
			Join<DemandeCredit, DemandeBudget> dcp= dc.join("demandeBudget");
			c.select(dc).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(idDemand != null) {
				ParameterExpression<Long> p = cb.parameter(Long.class, "idDemand");
				criteria.add(cb.equal(dcp.get("id"), p));
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));                            
			TypedQuery<DemandeCredit> q = entityManager.createQuery(c);
			if (idDemand != null) { 
				q.setParameter("idDemand", idDemand);
			}
			List<DemandeCredit> result = q.getResultList();
			for(DemandeCredit entity: result){
				if(entityManager.contains(entity)){
						entityManager.refresh(entity);
				}
			}
			return result;
		}
		catch (RuntimeException re) {
			log.error("findByIdDemand 'DemandeCredit' failed", re);
			throw re;
		}
	}
	
}
