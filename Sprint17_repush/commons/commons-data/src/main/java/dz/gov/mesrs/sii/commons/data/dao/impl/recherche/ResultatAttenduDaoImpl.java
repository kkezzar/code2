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
import dz.gov.mesrs.sii.commons.data.dao.recherche.ResultatAttenduDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.DemandeBudget;
import dz.gov.mesrs.sii.commons.data.model.recherche.ResultatAttendu;

/**
 * @author rlaib  on : 25 janv. 2015  11:14:12
 */
@Service ("resultatAttenduDao")
public class ResultatAttenduDaoImpl extends GenericFveDaoImpl<ResultatAttendu> implements ResultatAttenduDao {

	private static final Log log = LogFactory.getLog(ResultatAttenduDaoImpl.class);

	
	/**
	 * [ResultatAttenduDaoImpl.findByIdProgramme] Method 
	 * Overridden By : @author rlaib  on : 4 févr. 2015  10:20:03
	 * @param idProgamme
	 * @return
	 */
	@Override
	public List<ResultatAttendu> findByIdDemand(Long idDemand) {
		try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<ResultatAttendu> c = cb.createQuery(ResultatAttendu.class);
				Root<ResultatAttendu> ra = c.from(ResultatAttendu.class);
				Join<ResultatAttendu, DemandeBudget> dcp= ra.join("demandeBudget");
				c.select(ra).distinct(true);
				List<Predicate> criteria = new ArrayList<Predicate>();
				if(idDemand != null) {
						ParameterExpression<Long> p = cb.parameter(Long.class, "idDemand");
						criteria.add(cb.equal(dcp.get("id"), p));
				}
				c.where(cb.and(criteria.toArray(new Predicate[0])));                            
				TypedQuery<ResultatAttendu> q = entityManager.createQuery(c);
				if (idDemand != null) { 
						q.setParameter("idDemand", idDemand);
				}
				List<ResultatAttendu> result = q.getResultList();
				for(ResultatAttendu entity: result){
				if(entityManager.contains(entity)){
						entityManager.refresh(entity);
				}
			}
			return result;
		}
		catch (RuntimeException re) {
			log.error("findByIdProgramme 'ResultatAttendu' failed", re);
			throw re;
		}
	}
	
}
