package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ChapitreDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Chapitre;

/**
 * Dao Implementation for domain model class Chapitre.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.Chapitre
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("chapitreDao")
@Transactional
public class ChapitreDaoImpl extends CommonDaoImpl<Chapitre, Integer> implements ChapitreDao {

	@Override
	protected Class<Chapitre> getDomainClass() {
		return Chapitre.class;

	}

	// @Override
	// protected void enrirchCriteriaWithExample(Criteria criteria, Chapitre
	// example) {
	// if (example.getIdSection() != null) {
	// criteria.createAlias("idSection", "section");
	// criteria.add(Restrictions.eq("section", example.getIdSection()));
	//
	// }
	// }

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(Chapitre.class);
		//
		// criteria.createAlias("titre", "titre");
		criteria.createAlias("idSection", "section");
		// criteria.createAlias("idSousSection", "sousSection");
		// criteria.createAlias("partie", "partie");

		criteria.add(Restrictions.disjunction()
				.add(Restrictions.like("code", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("intituleAr", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("intituleFr", keyWord, MatchMode.ANYWHERE).ignoreCase())

				.add(Restrictions.like("section.intituleFr", keyWord, MatchMode.ANYWHERE).ignoreCase())
		// .add(Restrictions.like("sousSection.intituleFr", keyWord,
		// MatchMode.ANYWHERE).ignoreCase())
		//
		// .add(Restrictions.like("partie.intituleFr", keyWord,
		// MatchMode.ANYWHERE).ignoreCase())
		// .add(Restrictions.like("titre.intituleFr", keyWord,
		// MatchMode.ANYWHERE).ignoreCase())
		);
		return criteria;
	}

	@Override
	protected Criteria getCriteria(Chapitre example) {
		Criteria criteria = super.getCriteria(example);
		if (example.getIdSection() != null) {
			criteria.createAlias("idSection", "section");
			criteria.add(Restrictions.eq("idSection", example.getIdSection()));
		}

		if (example.isRecetteType() != null) {
			criteria.createAlias("recetteType", "recetteType");
			criteria.add(Restrictions.eq("recetteType", example.isRecetteType()));
		}
		return criteria;
	}
}