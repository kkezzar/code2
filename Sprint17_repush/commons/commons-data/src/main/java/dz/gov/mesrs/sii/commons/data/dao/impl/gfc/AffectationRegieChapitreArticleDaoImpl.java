package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.AffectationRegieChapitreArticleDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.AffectationRegieChapitreArticle;

/**
 * Dao Implementation for domain model class AffectationRegieChapitreArticle.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.AffectationRegieChapitreArticle
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("affectationRegieChapitreArticleDao")
@Transactional
public class AffectationRegieChapitreArticleDaoImpl extends
		CommonDaoImpl<AffectationRegieChapitreArticle, Integer> implements
		AffectationRegieChapitreArticleDao {

	@Override
	protected Class<AffectationRegieChapitreArticle> getDomainClass() {
		return AffectationRegieChapitreArticle.class;

	}

}