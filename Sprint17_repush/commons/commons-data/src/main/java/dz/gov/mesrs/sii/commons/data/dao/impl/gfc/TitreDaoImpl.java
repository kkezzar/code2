package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.TitreDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Section;
import dz.gov.mesrs.sii.commons.data.model.gfc.Titre;

/**
 * Dao Implementation for domain model class Titre.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.Titre
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("titreDao")
@Transactional
public class TitreDaoImpl extends CommonDaoImpl<Titre, Integer> implements
		TitreDao {

	@Override
	protected Class<Titre> getDomainClass() {
		return Titre.class;

	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(Titre.class);
//
	criteria.createAlias("section", "section");
	criteria.createAlias("sousSection", "sousSection");

		criteria.add(Restrictions.disjunction()
				.add(Restrictions.like("code", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("intituleAr", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("intituleFr", keyWord, MatchMode.ANYWHERE).ignoreCase())
				
				.add(Restrictions.like("section.intituleFr", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("sousSection.intituleFr", keyWord, MatchMode.ANYWHERE).ignoreCase())
				);
		return criteria;
	}
}