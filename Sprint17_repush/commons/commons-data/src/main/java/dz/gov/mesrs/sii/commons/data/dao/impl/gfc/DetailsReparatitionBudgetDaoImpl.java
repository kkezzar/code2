package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailsReparatitionBudgetDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsReparatitionBudget;

/**
 * Dao Implementation for domain model class DetailsReparatitionBudget.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.DetailsReparatitionBudget
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("detailsReparatitionBudgetDao")
@Transactional
public class DetailsReparatitionBudgetDaoImpl extends CommonDaoImpl<DetailsReparatitionBudget, Integer> implements
		DetailsReparatitionBudgetDao {

	@Override
	protected Class<DetailsReparatitionBudget> getDomainClass() {
		return DetailsReparatitionBudget.class;

	}

	@Override
	protected Criteria getCriteria(DetailsReparatitionBudget example) {
		Criteria criteria = super.getCriteria(example);

		if (example.getRepartitionBudget() != null && example.getRepartitionBudget().getId() != null) {
			criteria.createAlias("repartitionBudget", "repartitionBudget");
			criteria.add(Restrictions.eq("repartitionBudget", example.getRepartitionBudget()));
		}

		return criteria;
	}

}