package dz.gov.mesrs.sii.gfc.business.service;

import java.util.List;

import com.google.common.collect.ListMultimap;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.gfc.FicheBesoins;
import dz.gov.mesrs.sii.gfc.business.model.dto.ConsolidationBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsFicheBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.FicheBesoinsDto;

/**
 * Service Interface for domain model class FicheBesoins.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface FicheBesoinsService extends CommonService<FicheBesoins, FicheBesoinsDto, Integer> {

	/**
	 * Notifier les structures du demmarage de la preparation de budget
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	public FicheBesoinsDto notifierStructuresDemPrepBudget(FicheBesoinsDto ficheBesoinsDto);

	/**
	 * Valider la fiche des besoins -> activation du processus d'enregistrement
	 * les demandes budgetaires
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	public FicheBesoinsDto validerFicheBesoins(FicheBesoinsDto ficheBesoinsDto);

	/**
	 * Consolider la fiche des besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 23 déc. 2014 14:27:24
	 * @param ficheBesoinsDto
	 * @return
	 */
	public FicheBesoinsDto consoliderFicheBesoins(FicheBesoinsDto ficheBesoinsDto);

	/**
	 * 
	 * Transferer les montants demandes dans les montants proposes
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	public FicheBesoinsDto transfererMontantsDemProp(FicheBesoinsDto ficheBesoinsDto);

	/**
	 * Valider la consolidation de la fiche des besoins
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	public FicheBesoinsDto validerConsolidationFicheBesoins(FicheBesoinsDto ficheBesoinsDto);

	/**
	 * Valider l'enregistrement de la validation du CA
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	public FicheBesoinsDto validerEnregistrementValidCA(FicheBesoinsDto ficheBesoinsDto);

	/**
	 * Envoyer la fiche des besoins au MESRS
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	public FicheBesoinsDto envoyerFicheBesoinsMESRS(FicheBesoinsDto ficheBesoinsDto);

	/**
	 * Divise la liste de details de la fiche des besoins en deux listes
	 * list<true> recettes, list<false> depenses
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	public ListMultimap<String, DetailsFicheBesoinsDto> splitDetailsFicheBesoinsByTypeOfChapitre(
			FicheBesoinsDto ficheBesoinsDto);

	/**
	 * Divise la liste des consolidations de besoins en deux listes list<true>
	 * recettes, list<false> depenses
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	public ListMultimap<String, ConsolidationBesoinsDto> splitConsolidationsBesoinsByTypeOfChapitre(
			FicheBesoinsDto ficheBesoinsDto);

	public List<Object[]> getTotalOfFicheBesoins(FicheBesoinsDto ficheBesoinsDto);

	public List<Object[]> getTotalOfConsolidationBesoins(FicheBesoinsDto ficheBesoinsDto);
}