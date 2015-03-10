package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ExerciceBudgetaireDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.ExerciceBudgetaire;

/**
 * Dao Implementation for domain model class ExerciceBudgetaire.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.ExerciceBudgetaire
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("exerciceBudgetaireDao")
@Transactional
public class ExerciceBudgetaireDaoImpl extends CommonDaoImpl<ExerciceBudgetaire, Integer> implements
		ExerciceBudgetaireDao {

	@Override
	protected Class<ExerciceBudgetaire> getDomainClass() {
		return ExerciceBudgetaire.class;

	}

	// @Override
	// protected void enrirchCriteriaWithExample(Criteria criteria,
	// ExerciceBudgetaire example) {
	// example.getId();
	// // if (example.getSituation() != null && example.getSituation().getId()
	// // != 0) {
	// // criteria.createAlias("situation", "situation");
	// // criteria.add(Restrictions.eq("situation.id",
	// // example.getSituation().getId()));
	// // }
	// }

	@Override
	protected Criteria getCriteria(ExerciceBudgetaire example) {
		Criteria criteria = super.getCriteria(example);
		example.getId();
		if (example.getSituation() != null && example.getSituation().getId() != 0) {
			criteria.createAlias("situation", "situation");
			criteria.add(Restrictions.eq("situation.id", example.getSituation().getId()));
		}
		return criteria;
	}

}