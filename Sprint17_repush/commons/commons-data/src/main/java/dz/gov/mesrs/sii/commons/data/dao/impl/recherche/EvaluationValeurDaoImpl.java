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
import dz.gov.mesrs.sii.commons.data.dao.recherche.EvaluationValeurDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.EvaluationChercheur;
import dz.gov.mesrs.sii.commons.data.model.recherche.EvaluationValeur;

@Service ("evaluationValeurDao")
public class EvaluationValeurDaoImpl extends GenericFveDaoImpl<EvaluationValeur> implements EvaluationValeurDao {

	private static final Log log = LogFactory.getLog(EvaluationValeurDaoImpl.class);

	@Override
	public List<EvaluationValeur> findEvaluationValues(Long idEvaluation) {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<EvaluationValeur> c = cb.createQuery(EvaluationValeur.class);
			Root<EvaluationValeur> ev = c.from(EvaluationValeur.class);
			Join<EvaluationValeur, EvaluationChercheur> evec = ev.join("evaluationChercheur");
			c.select(ev).distinct(true);
//			c.orderBy(cb.asc(ged.get("numero")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(idEvaluation != null) {
				ParameterExpression<Long> p = cb.parameter(Long.class, "idEvaluation");
				criteria.add(cb.equal(evec.get("id"), p));
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<EvaluationValeur> q = entityManager.createQuery(c);
			if (idEvaluation != null) { 
				q.setParameter("idEvaluation", idEvaluation);
			}
			List<EvaluationValeur> result = q.getResultList();
				for(EvaluationValeur entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
			return result;
		}
		catch (RuntimeException re) {

				log.error("findEvaluationValues 'EvaluationValeur' failed", re);
				throw re;
		}
	
	}
	
}
