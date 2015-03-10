package dz.gov.mesrs.sii.gfc.business.service;

import java.util.List;

import com.google.common.collect.ListMultimap;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.gfc.RepartitionBudget;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsReparatitionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.RepartitionBudgetDto;

/**
 * Service Interface for domain model class RepartitionBudget.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface RepartitionBudgetService extends CommonService<RepartitionBudget, RepartitionBudgetDto, Integer> {

	/**
	 * for specific method
	 */

	/**
	 * Ajouter une nouvelle repartition de budget par article
	 * 
	 * @param repartitionBudgetDto
	 * @return
	 */
	public RepartitionBudgetDto addRepartitionBudget(RepartitionBudgetDto repartitionBudgetDto);

	/**
	 * Valider la reperatition de budget par article
	 * 
	 * @param repartitionBudgetDto
	 * @return
	 */
	public RepartitionBudgetDto validateRepartitionBudgetArticle(RepartitionBudgetDto repartitionBudgetDto);

	/**
	 * Enregistrer les donnnes de signature du budget reparti paer article
	 * 
	 * @param repartitionBudgetDto
	 * @return
	 */
	public RepartitionBudgetDto saveDonneesSignatureBudgetRepartition(RepartitionBudgetDto repartitionBudgetDto);

	/**
	 * Notifier le MESRS de la signature du budget
	 * 
	 * @param repartitionBudgetDto
	 * @return
	 */
	public RepartitionBudgetDto notifyMESRS(RepartitionBudgetDto repartitionBudgetDto);

	/**
	 * Divise la liste de details des reperatitions en deux listes list<true>
	 * recettes, list<false> depenses
	 * 
	 * @param repartitionBudgetDto
	 * @return
	 */
	public ListMultimap<String, DetailsReparatitionBudgetDto> splitDetailsRepartitionByTypeOfChapitre(
			RepartitionBudgetDto repartitionBudgetDto);

	public List<Object[]> getTotalOfDetailsRepartitionBudget(RepartitionBudgetDto repartitionBudgetDto);
}