package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.AffectationEtabChapitreArticleDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.AffectationEtabChapitreArticle;

/**
 * Dao Implementation for domain model class AffectationEtabChapitreArticle.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.AffectationEtabChapitreArticle
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("affectationEtabChapitreArticleDao")
@Transactional
public class AffectationEtabChapitreArticleDaoImpl extends CommonDaoImpl<AffectationEtabChapitreArticle, Integer>
		implements AffectationEtabChapitreArticleDao {

	@Override
	protected Class<AffectationEtabChapitreArticle> getDomainClass() {
		return AffectationEtabChapitreArticle.class;

	}

	// @Override
	// protected void enrirchCriteriaWithExample(Criteria criteria,
	// AffectationEtabChapitreArticle example) {
	// if (example.getChapitre() != null && example.getChapitre().getId() !=
	// null) {
	// criteria.createAlias("chapitre", "chapitre");
	// criteria.add(Restrictions.eq("chapitre.id",
	// example.getChapitre().getId()));
	// }
	//
	// if (example.getArticle() != null && example.getArticle().getId() != null)
	// {
	// criteria.createAlias("article", "article");
	// criteria.add(Restrictions.eq("article.id",
	// example.getArticle().getId()));
	// }
	//
	// if (example.getRefEtablissement() != null &&
	// example.getRefEtablissement().getId() != 0) {
	// criteria.createAlias("refEtablissement", "refEtablissement");
	// criteria.add(Restrictions.eq("refEtablissement.id",
	// example.getRefEtablissement().getId()));
	// }
	//
	// }
	//

	@Override
	protected Criteria getCriteria(AffectationEtabChapitreArticle example) {
		Criteria criteria = super.getCriteria(example);
		if (example.getChapitre() != null) {
			criteria.createAlias("chapitre", "chapitre");

			if (example.getChapitre().getId() != null)
				criteria.add(Restrictions.eq("chapitre.id", example.getChapitre().getId()));

			if (example.getChapitre().isRecetteType() != null)
				criteria.add(Restrictions.eq("chapitre.recetteType", example.getChapitre().isRecetteType()));

			if (example.getChapitre().getIdSection() != null && example.getChapitre().getIdSection().getId() != null) {
				criteria.add(Restrictions.eq("chapitre.idSection", example.getChapitre().getIdSection()));
			}
		}

		if (example.getArticle() != null && example.getArticle().getId() != null) {
			criteria.createAlias("article", "article");
			criteria.add(Restrictions.eq("article.id", example.getArticle().getId()));
		}

		if (example.getRefEtablissement() != null && example.getRefEtablissement().getId() != 0) {
			criteria.createAlias("refEtablissement", "refEtablissement");
			criteria.add(Restrictions.eq("refEtablissement.id", example.getRefEtablissement().getId()));
		}
		return criteria;
	}

}