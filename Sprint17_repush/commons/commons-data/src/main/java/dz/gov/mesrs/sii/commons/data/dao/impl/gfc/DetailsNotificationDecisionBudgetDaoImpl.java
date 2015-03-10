package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailsNotificationDecisionBudgetDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsNotificationDecisionBudget;

/**
 * Dao Implementation for domain model class DetailsNotificationDecisionBudget.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.DetailsNotificationDecisionBudget
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("detailsNotificationDecisionBudgetDao")
@Transactional
public class DetailsNotificationDecisionBudgetDaoImpl extends
		CommonDaoImpl<DetailsNotificationDecisionBudget, Integer> implements
		DetailsNotificationDecisionBudgetDao {

	@Override
	protected Class<DetailsNotificationDecisionBudget> getDomainClass() {
		return DetailsNotificationDecisionBudget.class;

	}

}