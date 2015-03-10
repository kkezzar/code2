package dz.gov.mesrs.sii.commons.data.dao.gfc;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.RepartitionBudget;

/**
 * Dao Interface for domain model class RepartitionBudget.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.business.model.bo.RepartitionBudget
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface RepartitionBudgetDao extends CommonDao<RepartitionBudget, Integer> {
	/**
	 * for specific method
	 */

	public List<Object[]> getTotalOfDetailsRepartitionBudget(RepartitionBudget repartitionBudget);

}