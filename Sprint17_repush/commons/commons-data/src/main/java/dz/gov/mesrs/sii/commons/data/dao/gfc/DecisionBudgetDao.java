package dz.gov.mesrs.sii.commons.data.dao.gfc;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DecisionBudget;

/**
 * Dao Interface for domain model class DecisionBudget.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.business.model.bo.DecisionBudget
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface DecisionBudgetDao extends CommonDao<DecisionBudget, Integer> {
	/**
	 * for specific method
	 */

	public List<Object[]> getTotalOfNotificationsBudget(DecisionBudget decisionBudget);

}