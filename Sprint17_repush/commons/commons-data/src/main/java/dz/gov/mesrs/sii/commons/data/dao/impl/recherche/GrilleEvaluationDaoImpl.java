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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.recherche.GrilleEvaluationDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.GrilleEvaluation;
import dz.gov.mesrs.sii.commons.data.model.recherche.Programme;

@Service ("grilleEvaluationDao")
public class GrilleEvaluationDaoImpl extends GenericFveDaoImpl<GrilleEvaluation> implements GrilleEvaluationDao {

	private static final Log log = LogFactory.getLog(GrilleEvaluationDaoImpl.class);

	@Override
	public List<GrilleEvaluation> findByKeyWords(String keyWord) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<GrilleEvaluation> c = cb.createQuery(GrilleEvaluation.class);
			Root<GrilleEvaluation> ge = c.from(GrilleEvaluation.class);
			c.select(ge).distinct(true);
			c.orderBy(cb.asc(ge.get("intituleFr")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(keyWord != null) {
				criteria.add(cb.like(cb.lower(ge.<String>get("code")), "%" + keyWord.toLowerCase() + "%" ));
				criteria.add(cb.like(cb.lower(ge.<String>get("intituleFr")), "%" + keyWord.toLowerCase() + "%" ));
				criteria.add(cb.like(cb.lower(ge.<String>get("intituleAr")), "%" + keyWord.toLowerCase() + "%" ));
			}
			c.where(cb.or(criteria.toArray(new Predicate[0])));
			TypedQuery<GrilleEvaluation> q = entityManager.createQuery(c);
			List<GrilleEvaluation> result = q.getResultList();
				for(GrilleEvaluation entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findByKeyWords 'GrilleEvaluation' failed", re);
				throw re;
		}
	}
	
}
