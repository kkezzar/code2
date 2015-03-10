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
import dz.gov.mesrs.sii.commons.data.dao.gfc.RepartitionBudgetDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.RepartitionBudget;
import dz.gov.mesrs.sii.gfc.business.model.dto.DecisionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsReparatitionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.RepartitionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.service.DecisionBudgetService;
import dz.gov.mesrs.sii.gfc.business.service.RepartitionBudgetService;

/**
 * Service Implementation for domain model class RepartitionBudget.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("repartitionBudgetService")
@Transactional
public class RepartitionBudgetServiceImpl extends CommonServiceImpl<RepartitionBudget, RepartitionBudgetDto, Integer>
		implements RepartitionBudgetService {

	@Autowired
	@Qualifier("repartitionBudgetDao")
	private RepartitionBudgetDao repartitionBudgetDao;

	@Autowired
	@Qualifier("decisionBudgetService")
	private DecisionBudgetService decisionBudgetService;

	@Autowired
	@Qualifier("situationService")
	private SituationService situationService;

	@Autowired
	private Mapper mapper;

	public RepartitionBudgetServiceImpl() {

	}

	@Override
	protected CommonDao<RepartitionBudget, Integer> getDao() {
		return repartitionBudgetDao;
	}

	/**
	 * Ajouter une nouvelle repartition de budget par article
	 * 
	 * @param repartitionBudgetDto
	 * @return
	 */
	@Override
	public RepartitionBudgetDto addRepartitionBudget(RepartitionBudgetDto repartitionBudgetDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_DECISION_REPARTITION, UtilConstants.SITUATION_CREEE_CODE);
		repartitionBudgetDto.setSituation(situation);
		repartitionBudgetDto = this.save(repartitionBudgetDto);
		// enregistrer situation occurence
		situationService.saveSituationOccurrence(situation, repartitionBudgetDto.getId());

		return repartitionBudgetDto;
	}

	/**
	 * Valider la reperatition de budget par article
	 * 
	 * @param repartitionBudgetDto
	 * @return
	 */
	@Override
	public RepartitionBudgetDto validateRepartitionBudgetArticle(RepartitionBudgetDto repartitionBudgetDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_DECISION_REPARTITION, UtilConstants.SITUATION_VALIDEE_CODE);
		repartitionBudgetDto.setSituation(situation);
		repartitionBudgetDto = this.save(repartitionBudgetDto);
		// enregistrer situation occurence
		situationService.saveSituationOccurrence(situation, repartitionBudgetDto.getId());

		// mettre a jours la situation de la decision de notification

		situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_DECISION_NOTIFICATION, UtilConstants.SITUATION_REPARTI);
		DecisionBudgetDto decisionBudgetDto = repartitionBudgetDto.getDecisionBudget();
		decisionBudgetDto.setSituation(situation);
		decisionBudgetDto = this.decisionBudgetService.save(decisionBudgetDto);
		// enregistrer situation occurence
		situationService.saveSituationOccurrence(situation, decisionBudgetDto.getId());

		return repartitionBudgetDto;
	}

	/**
	 * Enregistrer les donnnes de signature du budget reparti par article
	 * 
	 * @param repartitionBudgetDto
	 * @return
	 */
	@Override
	public RepartitionBudgetDto saveDonneesSignatureBudgetRepartition(RepartitionBudgetDto repartitionBudgetDto) {
		return this.save(repartitionBudgetDto);
	}

	/**
	 * Notifier le MESRS de la signature du budget
	 * 
	 * @param repartitionBudgetDto
	 * @return
	 */
	@Override
	public RepartitionBudgetDto notifyMESRS(RepartitionBudgetDto repartitionBudgetDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_DECISION_REPARTITION, UtilConstants.SITUATION_SIGNEE);
		repartitionBudgetDto.setSituation(situation);
		repartitionBudgetDto = this.save(repartitionBudgetDto);
		// enregistrer situation occurence
		situationService.saveSituationOccurrence(situation, repartitionBudgetDto.getId());

		// mise a jours de la decision de notification

		return repartitionBudgetDto;
	}

	/**
	 * Divise la liste de details des reperatitions en deux listes list<true>
	 * recettes, list<false> depenses
	 * 
	 * @param repartitionBudgetDto
	 * @return
	 */
	@Override
	public ListMultimap<String, DetailsReparatitionBudgetDto> splitDetailsRepartitionByTypeOfChapitre(
			RepartitionBudgetDto repartitionBudgetDto) {
		ListMultimap<String, DetailsReparatitionBudgetDto> result = ArrayListMultimap.create();
		List<DetailsReparatitionBudgetDto> dtos = repartitionBudgetDto.getDetailsReparatitionBudgets();
		if (dtos != null && !dtos.isEmpty()) {
			for (DetailsReparatitionBudgetDto dto : dtos) {
				Boolean isRecette = dto.getChapitre().isRecetteType();
				if (isRecette == null)
					isRecette = false;
				result.put(isRecette.toString(), dto);
			}
		} else {
			result.putAll("true", new ArrayList<DetailsReparatitionBudgetDto>());
			result.putAll("false", new ArrayList<DetailsReparatitionBudgetDto>());
		}

		return result;
	}

	@Override
	public List<Object[]> getTotalOfDetailsRepartitionBudget(RepartitionBudgetDto repartitionBudgetDto) {
		RepartitionBudget object = mapper.map(repartitionBudgetDto, RepartitionBudget.class);
		return repartitionBudgetDao.getTotalOfDetailsRepartitionBudget(object);
	}
}