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
import dz.gov.mesrs.sii.commons.data.dao.recherche.GrilleEvaluationDetailDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.GrilleEvaluation;
import dz.gov.mesrs.sii.commons.data.model.recherche.GrilleEvaluationDetail;

@Service ("grilleEvaluationDetailDao")
public class GrilleEvaluationDetailDaoImpl extends GenericFveDaoImpl<GrilleEvaluationDetail> implements GrilleEvaluationDetailDao {

	private static final Log log = LogFactory.getLog(GrilleEvaluationDetailDaoImpl.class);

	@Override
	public List<GrilleEvaluationDetail> findGridDetails(Long idEvaluationGrid) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<GrilleEvaluationDetail> c = cb.createQuery(GrilleEvaluationDetail.class);
			Root<GrilleEvaluationDetail> ged = c.from(GrilleEvaluationDetail.class);
			Join<GrilleEvaluationDetail, GrilleEvaluation> gedge = ged.join("grilleEvaluation");
			c.select(ged).distinct(true);
			c.orderBy(cb.asc(ged.get("numero")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(idEvaluationGrid != null) {
				ParameterExpression<Long> p = cb.parameter(Long.class, "idEvaluationGrid");
				criteria.add(cb.equal(gedge.get("id"), p));
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<GrilleEvaluationDetail> q = entityManager.createQuery(c);
			if (idEvaluationGrid != null) { 
				q.setParameter("idEvaluationGrid", idEvaluationGrid);
			}
			List<GrilleEvaluationDetail> result = q.getResultList();
				for(GrilleEvaluationDetail entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
			return result;
		}
		catch (RuntimeException re) {

				log.error("findGridDetails 'GrilleEvaluationDetail' failed", re);
				throw re;
		}
	}
	
}
