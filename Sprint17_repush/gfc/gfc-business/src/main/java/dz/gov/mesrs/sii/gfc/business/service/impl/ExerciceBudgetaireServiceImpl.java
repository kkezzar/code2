package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ExerciceBudgetaireDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.ExerciceBudgetaire;
import dz.gov.mesrs.sii.gfc.business.model.dto.ExerciceBudgetaireDto;
import dz.gov.mesrs.sii.gfc.business.service.ExerciceBudgetaireService;

/**
 * Service Implementation for domain model class ExerciceBudgetaire.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("exerciceBudgetaireService")
@Transactional
public class ExerciceBudgetaireServiceImpl extends
		CommonServiceImpl<ExerciceBudgetaire, ExerciceBudgetaireDto, Integer> implements ExerciceBudgetaireService {

	@Autowired
	@Qualifier("exerciceBudgetaireDao")
	private ExerciceBudgetaireDao exerciceBudgetaireDao;

	@Autowired
	private Mapper mapper;

	@Autowired
	@Qualifier("situationService")
	private SituationService situationService;

	public ExerciceBudgetaireServiceImpl() {

	}

	@Override
	protected CommonDao<ExerciceBudgetaire, Integer> getDao() {
		return exerciceBudgetaireDao;
	}

	/**
	 * Ouverture d'un nouveau exercice budgetaire
	 * 
	 * @param exerciceBudgetaireDto
	 * @return
	 */
	@Override
	public ExerciceBudgetaireDto ouvrirExerciceBudgetaire(ExerciceBudgetaireDto exerciceBudgetaireDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_EXERCICE_BUDGETAIRE_CODE, UtilConstants.SITUATION_CREEE_CODE);
		exerciceBudgetaireDto.setSituation(situation);

		exerciceBudgetaireDto = this.save(exerciceBudgetaireDto);
		situationService.saveSituationOccurrence(situation, exerciceBudgetaireDto.getId());

		logger.info("Nouveau exercice budgetaire ouvert.");
		return exerciceBudgetaireDto;
	}

	/**
	 * Valider l'ouverture de l'exercice budgetaire
	 * 
	 * @param exerciceBudgetaireDto
	 * @return
	 */
	@Override
	public ExerciceBudgetaireDto validerOuvertureExerciceBudgetaire(ExerciceBudgetaireDto exerciceBudgetaireDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_EXERCICE_BUDGETAIRE_CODE, UtilConstants.SITUATION_VALIDEE_CODE);
		situation.setIdEntite(exerciceBudgetaireDto.getId());
		exerciceBudgetaireDto.setSituation(situation);
		this.save(exerciceBudgetaireDto);

		situationService.saveSituationOccurrence(situation, exerciceBudgetaireDto.getId());
		logger.info("Exercice budgetaire valide.");
		return exerciceBudgetaireDto;
	}

	/**
	 * Cloturer l'exercice budgetaire
	 * 
	 * @param exerciceBudgetaireDto
	 * @return
	 */
	@Override
	public ExerciceBudgetaireDto cloturerExerciceBudgetaire(ExerciceBudgetaireDto exerciceBudgetaireDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_EXERCICE_BUDGETAIRE_CODE, UtilConstants.SITUATION_CLOTUREE_CODE);

		// exerciceBudgetaireDto.setDateCloture(new Date());
		exerciceBudgetaireDto.setSituation(situation);
		this.save(exerciceBudgetaireDto);
		// enregistrer une occurence de situation
		situationService.saveSituationOccurrence(situation, exerciceBudgetaireDto.getId());
		logger.info("Exercice budgetaire cloture.");
		return exerciceBudgetaireDto;
	}

}