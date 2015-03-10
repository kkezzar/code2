package dz.gov.mesrs.sii.gfc.business.service.impl;

import java.util.ArrayList;
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
import dz.gov.mesrs.sii.commons.data.dao.gfc.FicheBesoinsDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.FicheBesoins;
import dz.gov.mesrs.sii.gfc.business.model.dto.ConsolidationBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsFicheBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.FicheBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.service.FicheBesoinsService;

/**
 * Service Implementation for domain model class FicheBesoins.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("ficheBesoinsService")
@Transactional
public class FicheBesoinsServiceImpl extends CommonServiceImpl<FicheBesoins, FicheBesoinsDto, Integer> implements
		FicheBesoinsService {

	@Autowired
	@Qualifier("ficheBesoinsDao")
	private FicheBesoinsDao ficheBesoinsDao;

	@Autowired
	@Qualifier("situationService")
	private SituationService situationService;

	@Autowired
	private Mapper mapper;

	public FicheBesoinsServiceImpl() {

	}

	@Override
	protected CommonDao<FicheBesoins, Integer> getDao() {
		return ficheBesoinsDao;
	}

	/**
	 * Notifier les structures du demmarage de la preparation de budget
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	@Override
	public FicheBesoinsDto notifierStructuresDemPrepBudget(FicheBesoinsDto ficheBesoinsDto) {
		// TODO systeme de notification

		// changer la situation de la fiche de besoins
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE, UtilConstants.SITUATION_EN_COURS_PREPARATION_CODE);
		ficheBesoinsDto.setSituation(situation);
		ficheBesoinsDto = save(ficheBesoinsDto);

		situationService.saveSituationOccurrence(situation, ficheBesoinsDto.getId());

		logger.info("Les structures ont ete notifiees du demmarage de la preparation de budget.");

		return ficheBesoinsDto;
	}

	/**
	 * Valider la fiche des besoins -> activation du processus d'enregistrement
	 * les demandes budgetaires
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	@Override
	public FicheBesoinsDto validerFicheBesoins(FicheBesoinsDto ficheBesoinsDto) {
		// changer la situation de la fiche de besoins
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE,
				UtilConstants.SITUATION_EN_COURS_PREPARATION_ENREG_DEMANDES_BUDG_CODE);
		ficheBesoinsDto.setSituation(situation);
		ficheBesoinsDto = save(ficheBesoinsDto);
		situationService.saveSituationOccurrence(situation, ficheBesoinsDto.getId());

		logger.info("Fiche de besoins validee, activation du processus d'enregistrement des demandes budgetaires.");

		return ficheBesoinsDto;
	}

	/**
	 * Consolider la fiche des besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 23 déc. 2014 14:25:11
	 * @param ficheBesoinsDto
	 */
	@Transactional
	@Override
	public FicheBesoinsDto consoliderFicheBesoins(FicheBesoinsDto ficheBesoinsDto) {
		ficheBesoinsDto.getConsolidationBesoins().clear();
		ficheBesoinsDto = save(ficheBesoinsDto);
		FicheBesoins object = mapper.map(ficheBesoinsDto, FicheBesoins.class);
		object = ficheBesoinsDao.consoliderFicheBesoins(object);

		ficheBesoinsDto = mapper.map(object, FicheBesoinsDto.class);
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE, UtilConstants.SITUATION_CONSOLIDE_CODE);

		ficheBesoinsDto.setSituation(situation);
		ficheBesoinsDto = save(ficheBesoinsDto);
		situationService.saveSituationOccurrence(situation, ficheBesoinsDto.getId());

		logger.info("Fiche de besoins consolidee.");

		return ficheBesoinsDto;
	}

	/**
	 * 
	 * Transferer les montants demandes dans les montants proposes
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	@Override
	public FicheBesoinsDto transfererMontantsDemProp(FicheBesoinsDto ficheBesoinsDto) {
		List<ConsolidationBesoinsDto> l = ficheBesoinsDto.getConsolidationBesoins();
		for (ConsolidationBesoinsDto cfb : l) {
			cfb.setMontantPropose(cfb.getMontantDemande());
		}

		// changer la situation de la fiche de besoins
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE,
				UtilConstants.SITUATION_EN_COURS_PREPARATION_ENREG_PROP_BUDG_CODE);
		ficheBesoinsDto.setSituation(situation);
		situationService.saveSituationOccurrence(situation, ficheBesoinsDto.getId());
		ficheBesoinsDto = save(ficheBesoinsDto);

		logger.info("Transfert des montants demandes dans les montants proposes dans la fiche de besoins.");

		return ficheBesoinsDto;

	}

	/**
	 * Valider la consolidation de la fiche des besoins
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	@Override
	public FicheBesoinsDto validerConsolidationFicheBesoins(FicheBesoinsDto ficheBesoinsDto) {
		// changer la situation de la fiche de besoins
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE, UtilConstants.SITUATION_A_VALIDER_CODE);
		ficheBesoinsDto.setSituation(situation);

		ficheBesoinsDto = save(ficheBesoinsDto);
		situationService.saveSituationOccurrence(situation, ficheBesoinsDto.getId());

		logger.info("Validation de la consolidation de la fiche de besoins.");

		return ficheBesoinsDto;
	}

	/**
	 * Valider l'enregistrement de la validation du CA
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	@Override
	public FicheBesoinsDto validerEnregistrementValidCA(FicheBesoinsDto ficheBesoinsDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE, UtilConstants.SITUATION_VALIDEE_CODE);
		ficheBesoinsDto.setSituation(situation);
		ficheBesoinsDto = save(ficheBesoinsDto);

		logger.info("Validation de la consolidation de la fiche de besoins.");

		return ficheBesoinsDto;
	}

	/**
	 * Envoyer la fiche des besoins au MESRS
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	@Override
	public FicheBesoinsDto envoyerFicheBesoinsMESRS(FicheBesoinsDto ficheBesoinsDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE, UtilConstants.SITUATION_ENVOYEE_MESRS_CODE);
		ficheBesoinsDto.setSituation(situation);
		ficheBesoinsDto = save(ficheBesoinsDto);

		logger.info("Validation de la consolidation de la fiche de besoins.");

		return ficheBesoinsDto;

	}

	/**
	 * Divise la liste de details de la fiche des besoins en deux listes
	 * list<true> recettes, list<false> depenses
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	@Override
	public ListMultimap<String, DetailsFicheBesoinsDto> splitDetailsFicheBesoinsByTypeOfChapitre(
			FicheBesoinsDto ficheBesoinsDto) {
		ListMultimap<String, DetailsFicheBesoinsDto> result = ArrayListMultimap.create();
		List<DetailsFicheBesoinsDto> dtos = ficheBesoinsDto.getDetailsFicheBesoins();
		if (dtos != null && !dtos.isEmpty()) {
			for (DetailsFicheBesoinsDto dto : dtos) {
				result.put(dto.getChapitre().isRecetteType().toString(), dto);
			}
		} else {
			result.putAll("true", new ArrayList<DetailsFicheBesoinsDto>());
			result.putAll("false", new ArrayList<DetailsFicheBesoinsDto>());
		}

		return result;
	}

	/**
	 * Divise la liste des consolidations de besoins en deux listes list<true>
	 * recettes, list<false> depenses
	 * 
	 * @param ficheBesoinsDto
	 * @return
	 */
	@Override
	public ListMultimap<String, ConsolidationBesoinsDto> splitConsolidationsBesoinsByTypeOfChapitre(
			FicheBesoinsDto ficheBesoinsDto) {
		ListMultimap<String, ConsolidationBesoinsDto> result = ArrayListMultimap.create();
		List<ConsolidationBesoinsDto> dtos = ficheBesoinsDto.getConsolidationBesoins();
		if (dtos != null && !dtos.isEmpty()) {
			for (ConsolidationBesoinsDto dto : dtos) {
				result.put(dto.getChapitre().isRecetteType().toString(), dto);
			}
		} else {
			result.putAll("true", new ArrayList<ConsolidationBesoinsDto>());
			result.putAll("false", new ArrayList<ConsolidationBesoinsDto>());
		}

		return result;
	}

	@Override
	public List<Object[]> getTotalOfFicheBesoins(FicheBesoinsDto ficheBesoinsDto) {
		FicheBesoins object = mapper.map(ficheBesoinsDto, FicheBesoins.class);
		return ficheBesoinsDao.getTotalOfFicheBesoins(object);
	}

	@Override
	public List<Object[]> getTotalOfConsolidationBesoins(FicheBesoinsDto ficheBesoinsDto) {
		FicheBesoins object = mapper.map(ficheBesoinsDto, FicheBesoins.class);
		return ficheBesoinsDao.getTotalOfConsolidationBesoins(object);
	}
}