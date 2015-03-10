package dz.gov.mesrs.sii.gfc.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ProjetBudgetDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.ProjetBudget;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.gfc.business.model.dto.DecisionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsProjetBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.FicheBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ProjetBudgetDto;
import dz.gov.mesrs.sii.gfc.business.service.ProjetBudgetService;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;

/**
 * Service Implementation for domain model class ProjetBudget.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("projetBudgetService")
@Transactional
public class ProjetBudgetServiceImpl extends CommonServiceImpl<ProjetBudget, ProjetBudgetDto, Integer> implements
		ProjetBudgetService {

	@Autowired
	@Qualifier("projetBudgetDao")
	private ProjetBudgetDao projetBudgetDao;

	@Autowired
	@Qualifier("situationService")
	private SituationService situationService;

	@Autowired
	@Qualifier("refEtablissementServiceImpl")
	private RefEtablissementService etablissementService;

	@Autowired
	private Mapper mapper;

	public ProjetBudgetServiceImpl() {

	}

	@Override
	protected CommonDao<ProjetBudget, Integer> getDao() {
		return projetBudgetDao;
	}

	/**
	 * Creer un nouveau projet de budget
	 * 
	 * @param projetBudgetDto
	 * @return
	 */
	@Override
	public ProjetBudgetDto creerNoveauProjetBudget(ProjetBudgetDto projetBudgetDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE, UtilConstants.SITUATION_CREEE_CODE);
		projetBudgetDto.setSituation(situation);
		projetBudgetDto = save(projetBudgetDto);
		situationService.saveSituationOccurrence(situation, projetBudgetDto.getId());

		logger.info("Nouveu projet de budget cree.");
		return projetBudgetDto;
	}

	/**
	 * Valider le projet de budget
	 * 
	 * @param projetBudgetDto
	 * @return
	 */
	@Override
	public ProjetBudgetDto validerProjetBudget(ProjetBudgetDto projetBudgetDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE, UtilConstants.SITUATION_VALIDEE_CODE);
		situation.setIdEntite(projetBudgetDto.getId());
		projetBudgetDto.setSituation(situation);

		if (projetBudgetDto.getProjetBudget().getId() == null) {
			projetBudgetDto.setProjetBudget(null);
		}

		projetBudgetDto = save(projetBudgetDto);

		if (projetBudgetDto.getProjetBudget() == null) {
			projetBudgetDto.setProjetBudget(new ProjetBudgetDto());
		}

		situationService.saveSituationOccurrence(situation, projetBudgetDto.getId());
		logger.info("Projet de budget valide.");
		return projetBudgetDto;
	}

	/**
	 * Notifier les etablissements du demarrage de la preparation des demandes
	 * budgetaires
	 * 
	 * @param projetBudgetDto
	 * @return
	 */
	@Override
	public ProjetBudgetDto notifierEtablissements(ProjetBudgetDto projetBudgetDto) {
		SituationEntiteDto situationCree = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE, UtilConstants.SITUATION_CREEE_CODE);
		List<RefEtablissementDto> listEtablissements = etablissementService.findAll();
		int i = 0;
		for (RefEtablissementDto refEtablissementDto : listEtablissements) {
			i++;
			FicheBesoinsDto ficheBesoinsDto = new FicheBesoinsDto();
			ficheBesoinsDto.setProjetBudget(projetBudgetDto);
			// identite : annee de l'exercice budgetaire + code //
			// etablissement + numero sequentiel format 999
			StringBuffer identite = new StringBuffer();
			identite.append(projetBudgetDto.getExerciceBudgetaire().getAnnee().toString());
			identite.append(refEtablissementDto.getIdentifiant());
			identite.append(String.format("%03d", i));
			ficheBesoinsDto.setIdentite(identite.toString());
			ficheBesoinsDto.setEtablissement(refEtablissementDto);
			ficheBesoinsDto.setSituation(situationCree);
			ficheBesoinsDto.setUniteMonetaire(projetBudgetDto.getUniteMonetaire());
			ficheBesoinsDto.setIntituleFr("Budget " + refEtablissementDto.getLcEtablissementLatin());
			ficheBesoinsDto.setIntituleAr("Budget " + refEtablissementDto.getLcEtablissementArabe());
			projetBudgetDto.getFichesBesoins().add(ficheBesoinsDto);
		}

		// changer la situation
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE, UtilConstants.SITUATION_EN_COURS_PREPARATION_CODE);
		situation.setIdEntite(projetBudgetDto.getId());
		projetBudgetDto.setSituation(situation);

		if (projetBudgetDto.getProjetBudget().getId() == null) {
			projetBudgetDto.setProjetBudget(null);
		}

		projetBudgetDto = save(projetBudgetDto);

		if (projetBudgetDto.getProjetBudget() == null) {
			projetBudgetDto.setProjetBudget(new ProjetBudgetDto());
		}

		situationService.saveSituationOccurrence(situation, projetBudgetDto.getId());

		if (projetBudgetDto.getProjetBudget() == null) {
			projetBudgetDto.setProjetBudget(new ProjetBudgetDto());
		}

		logger.info("Etablissements notifies du demarrage de la preparation des demandes.");
		return projetBudgetDto;
	}

	/**
	 * Demander la preperation de budget
	 * 
	 * @param projetBudgetDto
	 * @return
	 */
	@Override
	public ProjetBudgetDto demanderPreparationBudget(ProjetBudgetDto projetBudgetDto) {
		SituationEntiteDto situationCree = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE, UtilConstants.SITUATION_CREEE_CODE);
		List<RefEtablissementDto> listEtablissements = etablissementService.findAll();
		int i = 0;
		for (RefEtablissementDto refEtablissementDto : listEtablissements) {
			i++;
			FicheBesoinsDto ficheBesoinsDto = new FicheBesoinsDto();
			ficheBesoinsDto.setProjetBudget(projetBudgetDto);
			// identite : annee de l'exercice budgetaire + code //
			// etablissement + numero sequentiel format 999
			StringBuffer identite = new StringBuffer();
			identite.append(projetBudgetDto.getExerciceBudgetaire().getAnnee().toString());
			identite.append(refEtablissementDto.getIdentifiant());
			identite.append(String.format("%03d", i));
			ficheBesoinsDto.setIdentite(identite.toString());
			ficheBesoinsDto.setEtablissement(refEtablissementDto);
			ficheBesoinsDto.setSituation(situationCree);
			ficheBesoinsDto.setUniteMonetaire(projetBudgetDto.getUniteMonetaire());
			ficheBesoinsDto.setIntituleFr("Budget " + refEtablissementDto.getLcEtablissementLatin());
			ficheBesoinsDto.setIntituleAr("Budget " + refEtablissementDto.getLcEtablissementArabe());
			projetBudgetDto.getFichesBesoins().add(ficheBesoinsDto);

			// serviceLocator.getFicheBesoinsService().save(ficheBesoinsDto);
		}

		// changer la situation
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE, UtilConstants.SITUATION_EN_COURS_PREPARATION_CODE);
		situation.setIdEntite(projetBudgetDto.getId());
		projetBudgetDto.setSituation(situation);

		if (projetBudgetDto.getProjetBudget().getId() == null) {
			projetBudgetDto.setProjetBudget(null);
		}

		projetBudgetDto = save(projetBudgetDto);

		if (projetBudgetDto.getProjetBudget() == null) {
			projetBudgetDto.setProjetBudget(new ProjetBudgetDto());
		}

		situationService.saveSituationOccurrence(situation, projetBudgetDto.getId());

		if (projetBudgetDto.getProjetBudget() == null) {
			projetBudgetDto.setProjetBudget(new ProjetBudgetDto());
		}

		logger.info("La preperation de budget a ete demandee.");
		return projetBudgetDto;
	}

	/**
	 * Consolider les consolidations de tous les fiches des besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 23 déc. 2014 14:27:24
	 * @param projetBudgetDto
	 * @return
	 */
	@Transactional
	@Override
	public Boolean consoliderProjetBudget(ProjetBudgetDto projetBudgetDto) {
		ProjetBudget object = mapper.map(projetBudgetDto, ProjetBudget.class);

		Boolean consolide = projetBudgetDao.consoliderProjetBudget(object);
		if (consolide) {
			SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE, UtilConstants.SITUATION_CONSOLIDE_CODE);
			projetBudgetDto.setSituation(situation);
			projetBudgetDto = save(projetBudgetDto);
			situationService.saveSituationOccurrence(situation, projetBudgetDto.getId());
		}
		logger.info("Projet de budget consolide.");
		return consolide;

	}

	/**
	 * 
	 * @param projetBudgetDto
	 * @return
	 */
	@Override
	public ProjetBudgetDto aEnvoyerBudgetMF(ProjetBudgetDto projetBudgetDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE, UtilConstants.SITUATION_A_ENVOYER_MF_CODE);
		projetBudgetDto.setSituation(situation);
		projetBudgetDto = save(projetBudgetDto);
		situationService.saveSituationOccurrence(situation, projetBudgetDto.getId());

		logger.info("Projet de budget a envoye au MF.");

		return projetBudgetDto;
	}

	/**
	 * Envoyer le budget au MF
	 * 
	 * @param projetBudgetDto
	 * @return
	 */
	@Override
	public ProjetBudgetDto envoyerBudgetMF(ProjetBudgetDto projetBudgetDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE, UtilConstants.SITUATION_ENVOYE_MF_CODE);
		projetBudgetDto.setSituation(situation);
		projetBudgetDto = save(projetBudgetDto);

		situationService.saveSituationOccurrence(situation, projetBudgetDto.getId());

		// gerneration des decision budgetaires vides
		prepareDecisionsBudgetaires(projetBudgetDto);

		logger.info("Projet de budget envoye au MF.");

		return projetBudgetDto;
	}

	/**
	 * 
	 */
	@Transactional
	@Override
	public Boolean prepareDecisionsBudgetaires(ProjetBudgetDto projetBudgetDto) {
		projetBudgetDto.getDecisionBudgets().clear();

		SituationEntiteDto situationCree = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_DECISION_NOTIFICATION, UtilConstants.SITUATION_CREEE_CODE);
		List<RefEtablissementDto> listEtablissements = etablissementService.findAll();
		for (RefEtablissementDto refEtablissementDto : listEtablissements) {

			// les decisions budgetaires
			DecisionBudgetDto decisionBudgetDto = new DecisionBudgetDto();
			decisionBudgetDto.setProjetBudget(projetBudgetDto);
			decisionBudgetDto.setEtablissement(refEtablissementDto);
			decisionBudgetDto.setReference("");
			decisionBudgetDto.setIntituleFr("");
			decisionBudgetDto.setIntituleAr("");
			decisionBudgetDto.setUniteMonetaire(projetBudgetDto.getUniteMonetaire());
			decisionBudgetDto.setSituation(situationCree);
			decisionBudgetDto.setMontant(new BigDecimal(0));
			decisionBudgetDto.setDateDecision(new Date());
			projetBudgetDto.getDecisionBudgets().add(decisionBudgetDto);

		}

		ProjetBudget object = mapper.map(projetBudgetDto, ProjetBudget.class);
		projetBudgetDao.save(object);
		return true;
	}

	/**
	 * Divise la liste de details du budget propose au MF en deux listes
	 * list<true> recettes, list<false> depenses
	 * 
	 * @param projetBudgetDto
	 * @return
	 */
	@Override
	public ListMultimap<String, DetailsProjetBudgetDto> splitDetailsBudgetByTypeOfChapitre(
			ProjetBudgetDto projetBudgetDto) {
		ListMultimap<String, DetailsProjetBudgetDto> result = ArrayListMultimap.create();
		List<DetailsProjetBudgetDto> dtos = projetBudgetDto.getDetailsProjetBudgets();
		if (dtos != null && !dtos.isEmpty()) {
			for (DetailsProjetBudgetDto dto : dtos) {
				Boolean isRecette = dto.getChapitre().isRecetteType();
				if (isRecette == null)
					isRecette = false;
				result.put(isRecette.toString(), dto);
			}
		} else {
			result.putAll("true", new ArrayList<DetailsProjetBudgetDto>());
			result.putAll("false", new ArrayList<DetailsProjetBudgetDto>());
		}

		return result;
	}

	@Override
	public List<Object[]> getTotalOfDetailsProjetBudget(ProjetBudgetDto projetBudgetDto) {
		ProjetBudget object = mapper.map(projetBudgetDto, ProjetBudget.class);
		return projetBudgetDao.getTotalOfDetailsProjetBudget(object);
	}

	@Override
	public List<Object[]> getTotalOfDetailsProjetBudgetByEtab(ProjetBudgetDto projetBudgetDto,
			RefEtablissementDto etablissementDto) {
		ProjetBudget object1 = mapper.map(projetBudgetDto, ProjetBudget.class);
		RefEtablissement object2 = mapper.map(etablissementDto, RefEtablissement.class);
		return projetBudgetDao.getTotalOfDetailsProjetBudgetByEtab(object1, object2);
	}

	@Override
	public List<Object[]> getAllTotalOfDetailsProjetBudgetByEtab(ProjetBudgetDto projetBudgetDto) {
		ProjetBudget object = mapper.map(projetBudgetDto, ProjetBudget.class);
		return projetBudgetDao.getAllTotalOfDetailsProjetBudgetByEtab(object);
	}
}