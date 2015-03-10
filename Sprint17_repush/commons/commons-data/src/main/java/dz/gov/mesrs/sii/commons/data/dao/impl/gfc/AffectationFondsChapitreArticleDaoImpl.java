package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.AffectationFondsChapitreArticleDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.AffectationFondsChapitreArticle;

/**
 * Dao Implementation for domain model class AffectationFondsChapitreArticle.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.AffectationFondsChapitreArticle
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("affectationFondsChapitreArticleDao")
@Transactional
public class AffectationFondsChapitreArticleDaoImpl extends
		CommonDaoImpl<AffectationFondsChapitreArticle, Integer> implements
		AffectationFondsChapitreArticleDao {

	@Override
	protected Class<AffectationFondsChapitreArticle> getDomainClass() {
		return AffectationFondsChapitreArticle.class;

	}

}