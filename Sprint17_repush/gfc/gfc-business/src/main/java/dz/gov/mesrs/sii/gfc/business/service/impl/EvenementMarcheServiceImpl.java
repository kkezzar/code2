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
import dz.gov.mesrs.sii.commons.data.dao.gfc.EvenementMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.EvenementMarche;
import dz.gov.mesrs.sii.gfc.business.model.dto.EvenementMarcheDto;
import dz.gov.mesrs.sii.gfc.business.service.EvenementMarcheService;

/**
 * Service Implementation for domain model class EvenementMarche.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("evenementMarcheService")
@Transactional
public class EvenementMarcheServiceImpl extends CommonServiceImpl<EvenementMarche, EvenementMarcheDto, Integer>
		implements EvenementMarcheService {

	@Autowired
	@Qualifier("evenementMarcheDao")
	private EvenementMarcheDao evenementMarcheDao;

	@Autowired
	private Mapper mapper;

	@Autowired
	@Qualifier("situationService")
	private SituationService situationService;

	public EvenementMarcheServiceImpl() {

	}

	@Override
	protected CommonDao<EvenementMarche, Integer> getDao() {
		return evenementMarcheDao;
	}

	@Override
	public EvenementMarcheDto enregistrerEvenementMarche(EvenementMarcheDto evenementMarcheDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_EVENT_MARCHE, UtilConstants.SITUATION_CREEE_CODE);
		evenementMarcheDto.setSituation(situation);
		evenementMarcheDto = save(evenementMarcheDto);
		situationService.saveSituationOccurrence(situation, evenementMarcheDto.getId());

		logger.info("Nouvel evenement de marche cree.");
		return evenementMarcheDto;
	}

	@Override
	public void supprimerEvenementMarche(EvenementMarcheDto evenementMarcheDto) {
		remove(evenementMarcheDto);
	}

	@Override
	public EvenementMarcheDto validerEnregistrementEvenementMarche(EvenementMarcheDto evenementMarcheDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_EVENT_MARCHE, UtilConstants.SITUATION_VALIDEE_CODE);
		situation.setIdEntite(evenementMarcheDto.getId());
		evenementMarcheDto.setSituation(situation);

		evenementMarcheDto = save(evenementMarcheDto);

		situationService.saveSituationOccurrence(situation, evenementMarcheDto.getId());
		logger.info("evenement marche valide.");
		return evenementMarcheDto;
	}

}