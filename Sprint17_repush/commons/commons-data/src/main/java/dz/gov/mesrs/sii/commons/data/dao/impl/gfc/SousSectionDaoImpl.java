package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.SousSectionDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Section;
import dz.gov.mesrs.sii.commons.data.model.gfc.SousSection;

/**
 * Dao Implementation for domain model class SousSection.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.SousSection
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("sousSectionDao")
@Transactional
public class SousSectionDaoImpl extends CommonDaoImpl<SousSection, Integer>
		implements SousSectionDao {

	@Override
	protected Class<SousSection> getDomainClass() {
		return SousSection.class;

	}
	
	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(SousSection.class);
//
	criteria.createAlias("section", "section");
//		criteria.createAlias("section", "mode");

		criteria.add(Restrictions.disjunction()
				.add(Restrictions.like("code", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("intituleAr", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("intituleFr", keyWord, MatchMode.ANYWHERE).ignoreCase())
		
		.add(Restrictions.like("section.intituleFr", keyWord, MatchMode.ANYWHERE).ignoreCase()));
		return criteria;
	}

}