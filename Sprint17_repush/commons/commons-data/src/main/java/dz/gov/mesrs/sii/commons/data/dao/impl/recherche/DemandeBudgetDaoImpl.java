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
import javax.persistence.criteria.Subquery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.recherche.DemandeBudgetDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Etape;
import dz.gov.mesrs.sii.commons.data.model.bpm.Processus;
import dz.gov.mesrs.sii.commons.data.model.bpm.Situation;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.gfc.Article;
import dz.gov.mesrs.sii.commons.data.model.gfc.Chapitre;
import dz.gov.mesrs.sii.commons.data.model.recherche.DemandeBudget;
import dz.gov.mesrs.sii.commons.data.model.recherche.Programme;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectDomLmdEtab;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * @author rlaib  on : 25 janv. 2015  11:14:12
 */
@Service ("demandeBudgetDao")
public class DemandeBudgetDaoImpl extends GenericFveDaoImpl<DemandeBudget> implements DemandeBudgetDao {

	private static final Log log = LogFactory.getLog(DemandeBudgetDaoImpl.class);

	/**
	 * [DemandeBudgetDao.findByIdProgramme] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  11:48:59
	 * @param idProgamme
	 * @return
	 */
	@Override
	public List<DemandeBudget> findByIdProgramme(Long idProgamme) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DemandeBudget> c = cb.createQuery(DemandeBudget.class);
			Root<DemandeBudget> db = c.from(DemandeBudget.class);
			Join<DemandeBudget, Programme> dbp= db.join("programme");
			c.select(db).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(idProgamme != null) {
				ParameterExpression<Long> p = cb.parameter(Long.class, "idProgamme");
				criteria.add(cb.equal(dbp.get("id"), p));
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));                            
			TypedQuery<DemandeBudget> q = entityManager.createQuery(c);
			List<DemandeBudget> result = null;
			if (idProgamme != null) { 
				q.setParameter("idProgamme", idProgamme);
				result = q.getResultList();
				for(DemandeBudget entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
			}
			return result;
		}
		catch (RuntimeException re) {
			log.error("findByIdProgramme 'DemandeBudget' failed", re);
			throw re;
		}
	}

	/**
	 * [DemandeBudgetDao.findByKeyWords] Method 
	 * Overridden By : @author rlaib  on : 3 févr. 2015  14:06:19
	 * @param keyWord
	 * @return
	 */
	@Override
	public List<DemandeBudget> findByKeyWords(String keyWord) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DemandeBudget> c = cb.createQuery(DemandeBudget.class);
			Root<DemandeBudget> db = c.from(DemandeBudget.class);
			c.select(db).distinct(true);
			c.orderBy(cb.desc(db.get("dateDemande")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(keyWord != null) {
				criteria.add(cb.like(cb.lower(db.<String>get("description")), "%" + keyWord.toLowerCase() + "%" ));
				criteria.add(cb.like(cb.lower(db.<String>get("connaissance")), "%" + keyWord.toLowerCase() + "%" ));
				criteria.add(cb.like(cb.lower(db.<String>get("information")), "%" + keyWord.toLowerCase() + "%" ));
				criteria.add(cb.like(cb.lower(db.<String>get("reseauRecherche")), "%" + keyWord.toLowerCase() + "%" ));
				criteria.add(cb.like(cb.lower(db.<String>get("objectif")), "%" + keyWord.toLowerCase() + "%" ));
				criteria.add(cb.like(cb.lower(db.<String>get("observation")), "%" + keyWord.toLowerCase() + "%" ));
			}
			c.where(cb.or(criteria.toArray(new Predicate[0])));
			TypedQuery<DemandeBudget> q = entityManager.createQuery(c);
			List<DemandeBudget> result = q.getResultList();
				for(DemandeBudget entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findByKeyWords 'DemandeBudget' failed", re);
				throw re;
		}
	}

	@Override
	public List<Chapitre> findChapters() {
	
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Chapitre> c = cb.createQuery(Chapitre.class);
			Root<Chapitre> cp = c.from(Chapitre.class);
			c.select(cp).distinct(true);
			c.orderBy(cb.asc(cp.get("intituleFr")));
			TypedQuery<Chapitre> q = entityManager.createQuery(c);
			List<Chapitre> result = q.getResultList();
			for(Chapitre entity: result){
				if(entityManager.contains(entity)){
						entityManager.refresh(entity);
				}
			}
			return result;
		}
		catch (RuntimeException re) {
			log.error("findChapters 'Chapitre' failed", re);
			throw re;
		}
	}

	@Override
	public List<Article> findArticlesByChapterId(Integer chapterId) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Article> c = cb.createQuery(Article.class);
			Root<Article> a = c.from(Article.class);
			Join<Article, Chapitre> ac= a.join("chapitre");
			c.select(a).distinct(true);
			c.orderBy(cb.asc(a.get("intituleFr")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(chapterId != null) {
				ParameterExpression<Integer> p = cb.parameter(Integer.class, "chapterId");
				criteria.add(cb.equal(ac.get("id"), p));
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));                            
			TypedQuery<Article> q = entityManager.createQuery(c);
			if (chapterId != null) { 
				q.setParameter("chapterId", chapterId);
			}
			List<Article> result = q.getResultList();
			for(Article entity: result){
				if(entityManager.contains(entity)){
						entityManager.refresh(entity);
				}
			}
			return result;
		}
		catch (RuntimeException re) {
			log.error("findArticlesByChapterId 'Article' failed", re);
			throw re;
		}
	}

	@Override
	public List<DemandeBudget> findDemandsToValidate(String roleCode) {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DemandeBudget> c = cb.createQuery(DemandeBudget.class);
			Root<DemandeBudget> db = c.from(DemandeBudget.class);
			Join<DemandeBudget, Programme> dbp =  db.join("programme");
			Join<Programme,Processus> dbpp = dbp.join("circuitValidation");
			Join<DemandeBudget, SituationEntite> dbes = db.join("situationEntite");
			Join<SituationEntite, Situation> dbess = dbes.join("situation");
			
			List<Predicate> criteria = new ArrayList<Predicate>();
			
			if(roleCode != null) {
				Subquery<Etape> subquery = c.subquery(Etape.class);
				Root<Etape> e = subquery.from(Etape.class);
				Join<Etape,Nomenclature> en = e.join("roleGroupe");
				subquery.select(e);
				List<Predicate> criteriaSubQuery = new ArrayList<Predicate>();
				Predicate p1 = cb.equal(e.get("processus"),dbpp);
				criteriaSubQuery.add(p1);
				Predicate p2 = cb.equal(en.get("code"),roleCode);
				criteriaSubQuery.add(p2);
				subquery.where(cb.and(criteriaSubQuery.toArray(new Predicate[0])));
				criteria.add(cb.exists(subquery)); 
			}
			
			c.select(db).distinct(true);
			c.orderBy(cb.desc(db.get("dateDemande")));
			String _codeSituationSoumise = UtilConstant.SITUTAION_SOUMISE_VALIDATION_CODE;
			if(_codeSituationSoumise != null) {
				ParameterExpression<String> p = cb.parameter(String.class, "code1");
				criteria.add(cb.equal(dbess.get("code"), p));
			}
			String _codeSituationValidee = UtilConstant.SITUTAION_VALIDEE_CODE;
			if(_codeSituationValidee != null) {
				ParameterExpression<String> p = cb.parameter(String.class, "code2");
				criteria.add(cb.equal(dbess.get("code"), p));
			}
			c.where(cb.or(criteria.toArray(new Predicate[0])));                            
			TypedQuery<DemandeBudget> q = entityManager.createQuery(c);
			List<DemandeBudget> result = null;
			if (_codeSituationSoumise != null) { 
				q.setParameter("code1", _codeSituationSoumise);
				q.setParameter("code2", _codeSituationValidee);
				result = q.getResultList();
				for(DemandeBudget entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
			}
			return result;
		}
		catch (RuntimeException re) {

				log.error("findDemandsToValidate 'DemandeBudget' failed", re);
				throw re;
		}
	}
	
}
