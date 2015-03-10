package dz.gov.mesrs.sii.gfc.business.service;

import java.util.List;

import com.google.common.collect.ListMultimap;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.gfc.ProjetBudget;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsProjetBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ProjetBudgetDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

/**
 * Service Interface for domain model class ProjetBudget.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface ProjetBudgetService extends CommonService<ProjetBudget, ProjetBudgetDto, Integer> {

	/**
	 * for specific method
	 */

	/**
	 * Creer un nouveau projet de budget
	 * 
	 * @param projetBudgetDto
	 * @return
	 */
	public ProjetBudgetDto creerNoveauProjetBudget(ProjetBudgetDto projetBudgetDto);

	/**
	 * Valider le projet de budget
	 * 
	 * @param projetBudgetDto
	 * @return
	 */
	public ProjetBudgetDto validerProjetBudget(ProjetBudgetDto projetBudgetDto);

	/**
	 * Notifier les etablissements du demarrage de la preparation des demandes
	 * budgetaires
	 * 
	 * @param projetBudgetDto
	 * @return
	 */
	public ProjetBudgetDto notifierEtablissements(ProjetBudgetDto projetBudgetDto);

	/**
	 * Demander la preperation de budget
	 * 
	 * @param projetBudgetDto
	 * @return
	 */
	public ProjetBudgetDto demanderPreparationBudget(ProjetBudgetDto projetBudgetDto);

	/**
	 * Consolider les consolidations de tous les fiches des besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 23 déc. 2014 14:27:24
	 * @param projetBudgetDto
	 * @return
	 */
	public Boolean consoliderProjetBudget(ProjetBudgetDto projetBudgetDto);

	/**
	 * 
	 * @param projetBudgetDto
	 * @return
	 */
	public ProjetBudgetDto aEnvoyerBudgetMF(ProjetBudgetDto projetBudgetDto);

	/**
	 * Envoyer le budget au MF
	 * 
	 * @param projetBudgetDto
	 * @return
	 */
	public ProjetBudgetDto envoyerBudgetMF(ProjetBudgetDto projetBudgetDto);

	/**
	 * Preparer les decisoon budgetaires ( generation )
	 * 
	 * @return
	 */
	public Boolean prepareDecisionsBudgetaires(ProjetBudgetDto projetBudgetDto);

	/**
	 * Divise la liste de details du budget propose au MF en deux listes
	 * list<true> recettes, list<false> depenses
	 * 
	 * @param projetBudgetDto
	 * @return
	 */
	public ListMultimap<String, DetailsProjetBudgetDto> splitDetailsBudgetByTypeOfChapitre(
			ProjetBudgetDto projetBudgetDto);

	public List<Object[]> getTotalOfDetailsProjetBudget(ProjetBudgetDto projetBudgetDto);

	public List<Object[]> getTotalOfDetailsProjetBudgetByEtab(ProjetBudgetDto projetBudgetDto,
			RefEtablissementDto etablissementDto);

	public List<Object[]> getAllTotalOfDetailsProjetBudgetByEtab(ProjetBudgetDto projetBudgetDto);
}