package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ArticleDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Article;

/**
 * Dao Implementation for domain model class Article.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.Article
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("articleDao")
@Transactional
public class ArticleDaoImpl extends CommonDaoImpl<Article, Integer> implements ArticleDao {

	@Override
	protected Class<Article> getDomainClass() {
		return Article.class;

	}

	// @Override
	// protected void enrirchCriteriaWithExample(Criteria criteria, Article
	// example) {
	// if (example.getChapitre() != null) {
	// criteria.createAlias("chapitre", "chapitre");
	// criteria.add(Restrictions.eq("chapitre.id",
	// example.getChapitre().getId()));
	// }
	// }

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(Article.class);
		//
		criteria.createAlias("chapitre", "chapitre");
		// criteria.createAlias("section", "mode");

		criteria.add(Restrictions.disjunction()
				.add(Restrictions.like("code", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("intituleAr", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("intituleFr", keyWord, MatchMode.ANYWHERE).ignoreCase())

				.add(Restrictions.like("chapitre.intituleFr", keyWord, MatchMode.ANYWHERE).ignoreCase()));
		return criteria;
	}

	@Override
	protected Criteria getCriteria(Article example) {
		Criteria criteria = super.getCriteria(example);
		if (example.getChapitre() != null) {
			criteria.createAlias("chapitre", "chapitre");
			criteria.add(Restrictions.eq("chapitre.id", example.getChapitre().getId()));
		}
		return criteria;
	}
}