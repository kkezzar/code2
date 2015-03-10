package dz.gov.mesrs.sii.gfc.business.service;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.gfc.ExerciceBudgetaire;
import dz.gov.mesrs.sii.gfc.business.model.dto.ExerciceBudgetaireDto;

/**
 * Service Interface for domain model class ExerciceBudgetaire.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface ExerciceBudgetaireService extends CommonService<ExerciceBudgetaire, ExerciceBudgetaireDto, Integer> {

	/**
	 * for specific method
	 */

	/**
	 * Ouverture d'un nouveau exercice budgetaire
	 * 
	 * @param exerciceBudgetaireDto
	 * @return
	 */
	public ExerciceBudgetaireDto ouvrirExerciceBudgetaire(ExerciceBudgetaireDto exerciceBudgetaireDto);

	/**
	 * Valider l'ouverture de l'exercice budgetaire
	 * 
	 * @param exerciceBudgetaireDto
	 * @return
	 */
	public ExerciceBudgetaireDto validerOuvertureExerciceBudgetaire(ExerciceBudgetaireDto exerciceBudgetaireDto);

	/**
	 * Cloturer l'exercice budgetaire
	 * 
	 * @param exerciceBudgetaireDto
	 * @return
	 */
	public ExerciceBudgetaireDto cloturerExerciceBudgetaire(ExerciceBudgetaireDto exerciceBudgetaireDto);
}