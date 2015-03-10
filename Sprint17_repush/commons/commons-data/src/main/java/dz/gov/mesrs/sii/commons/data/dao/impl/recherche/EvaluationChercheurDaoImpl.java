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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.recherche.EvaluationChercheurDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.EvaluationChercheur;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectDomLmdEtab;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

@Service ("evaluationChercheurDao")
public class EvaluationChercheurDaoImpl extends GenericFveDaoImpl<EvaluationChercheur> implements EvaluationChercheurDao {

	private static final Log log = LogFactory.getLog(EvaluationChercheurDaoImpl.class);

	@Override
	public List<EvaluationChercheur> findByKeyWords(String keyWord) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<EvaluationChercheur> c = cb.createQuery(EvaluationChercheur.class);
			Root<EvaluationChercheur> ec = c.from(EvaluationChercheur.class);
			Join<EvaluationChercheur, RefIndividu> eci = ec.join("refIndividu");
			c.select(ec).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(keyWord != null) {
				criteria.add(cb.like(cb.lower(eci.<String>get("nomLatin")), "%" + keyWord.toLowerCase() + "%" ));
				criteria.add(cb.like(cb.lower(eci.<String>get("prenomLatin")), "%" + keyWord.toLowerCase() + "%" ));
			}
			c.where(cb.or(criteria.toArray(new Predicate[0])));
			TypedQuery<EvaluationChercheur> q = entityManager.createQuery(c);
			List<EvaluationChercheur> result = q.getResultList();
				for(EvaluationChercheur entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findByKeyWords 'EvaluationChercheur' failed", re);
				throw re;
		}
	
	}
	
}
