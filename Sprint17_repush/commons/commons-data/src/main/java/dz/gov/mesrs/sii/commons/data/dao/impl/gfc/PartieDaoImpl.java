package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.PartieDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Partie;
import dz.gov.mesrs.sii.commons.data.model.gfc.SousSection;

/**
 * Dao Implementation for domain model class Partie.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.Partie
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("partieDao")
@Transactional
public class PartieDaoImpl extends CommonDaoImpl<Partie, Integer> implements
		PartieDao {

	@Override
	protected Class<Partie> getDomainClass() {
		return Partie.class;

	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(Partie.class);
//
	criteria.createAlias("titre", "titre");
//		criteria.createAlias("section", "mode");

		criteria.add(Restrictions.disjunction()
				.add(Restrictions.like("code", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("intituleAr", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("intituleFr", keyWord, MatchMode.ANYWHERE).ignoreCase())
		
		.add(Restrictions.like("titre.intituleFr", keyWord, MatchMode.ANYWHERE).ignoreCase()));
		return criteria;
	}

}