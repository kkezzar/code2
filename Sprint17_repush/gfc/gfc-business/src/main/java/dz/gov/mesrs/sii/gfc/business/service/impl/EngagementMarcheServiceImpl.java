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
import dz.gov.mesrs.sii.commons.data.dao.gfc.EngagementMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.EngagementMarche;
import dz.gov.mesrs.sii.gfc.business.model.dto.EngagementMarcheDto;
import dz.gov.mesrs.sii.gfc.business.service.EngagementMarcheService;

/**
 * Service Implementation for domain model class EngagementMarche.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("engagementMarcheService")
@Transactional
public class EngagementMarcheServiceImpl extends CommonServiceImpl<EngagementMarche, EngagementMarcheDto, Integer>
		implements EngagementMarcheService {

	@Autowired
	@Qualifier("engagementMarcheDao")
	private EngagementMarcheDao engagementMarcheDao;

	@Autowired
	private Mapper mapper;

	@Autowired
	@Qualifier("situationService")
	private SituationService situationService;

	public EngagementMarcheServiceImpl() {

	}

	@Override
	protected CommonDao<EngagementMarche, Integer> getDao() {
		return engagementMarcheDao;
	}

	@Override
	public EngagementMarcheDto enregistrerDemandeEngagement(EngagementMarcheDto engagementMarche) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_ENGAGEMENT_MARCHE, UtilConstants.SITUATION_CREEE_CODE);
		engagementMarche.setSituation(situation);

		// TODO numero de la demande

		engagementMarche = save(engagementMarche);
		situationService.saveSituationOccurrence(situation, engagementMarche.getId());

		logger.info("Nouvelle demande d'engagement cree.");
		return engagementMarche;
	}

	@Override
	public void supprimerDemandeEngagement(EngagementMarcheDto engagementMarche) {
		remove(engagementMarche);
	}

	@Override
	public EngagementMarcheDto validerDemandeEngagement(EngagementMarcheDto engagementMarche) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_ENGAGEMENT_MARCHE, UtilConstants.SITUATION_VALIDEE_CODE);
		situation.setIdEntite(engagementMarche.getId());
		engagementMarche.setSituation(situation);

		engagementMarche = save(engagementMarche);

		situationService.saveSituationOccurrence(situation, engagementMarche.getId());
		logger.info("Demande d'engagement de marche validee.");
		return engagementMarche;
	}

	@Override
	public EngagementMarcheDto envoyerDemandeEngamgent(EngagementMarcheDto engagementMarche) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_ENGAGEMENT_MARCHE, UtilConstants.SITUATION_ENVOYE_CODE);
		situation.setIdEntite(engagementMarche.getId());
		engagementMarche.setSituation(situation);

		engagementMarche = save(engagementMarche);

		situationService.saveSituationOccurrence(situation, engagementMarche.getId());
		logger.info("Demande d'engagement envoyee.");
		return engagementMarche;
	}
}