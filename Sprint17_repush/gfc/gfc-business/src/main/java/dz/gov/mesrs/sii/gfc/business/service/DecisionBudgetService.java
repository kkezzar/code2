package dz.gov.mesrs.sii.gfc.business.service;

import java.util.List;

import com.google.common.collect.ListMultimap;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.gfc.DecisionBudget;
import dz.gov.mesrs.sii.gfc.business.model.dto.DecisionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsNotificationDecisionBudgetDto;

/**
 * Service Interface for domain model class DecisionBudget.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface DecisionBudgetService extends CommonService<DecisionBudget, DecisionBudgetDto, Integer> {

	/**
	 * for specific method
	 */

	/**
	 * Divise la liste de details de notification en deux listes list<true>
	 * recettes, list<false> depenses
	 * 
	 * @param decisionBudgetDto
	 * @return
	 */
	public ListMultimap<String, DetailsNotificationDecisionBudgetDto> splitDetailsNotificationsByTypeOfChapitre(
			DecisionBudgetDto decisionBudgetDto);

	public List<Object[]> getTotalOfNotificationsBudget(DecisionBudgetDto decisionBudgetDto);

}