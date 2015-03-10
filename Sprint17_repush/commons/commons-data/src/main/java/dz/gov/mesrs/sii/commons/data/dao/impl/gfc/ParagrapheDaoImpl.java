package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ParagrapheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Paragraphe;

/**
 * Dao Implementation for domain model class Paragraphe.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.Paragraphe
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("paragrapheDao")
@Transactional
public class ParagrapheDaoImpl extends CommonDaoImpl<Paragraphe, Integer>
		implements ParagrapheDao {

	@Override
	protected Class<Paragraphe> getDomainClass() {
		return Paragraphe.class;

	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(Paragraphe.class);
//
	criteria.createAlias("article", "article");
//		criteria.createAlias("section", "mode");

		criteria.add(Restrictions.disjunction()
				.add(Restrictions.like("code", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("intituleAr", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("intituleFr", keyWord, MatchMode.ANYWHERE).ignoreCase())
		
		.add(Restrictions.like("article.intituleFr", keyWord, MatchMode.ANYWHERE).ignoreCase()));
		return criteria;
	}
}