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
import dz.gov.mesrs.sii.commons.data.dao.gfc.MarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Marche;
import dz.gov.mesrs.sii.gfc.business.model.dto.MarcheDto;
import dz.gov.mesrs.sii.gfc.business.service.MarcheService;

/**
 * Service Implementation for domain model class Marche.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("marcheService")
@Transactional
public class MarcheServiceImpl extends CommonServiceImpl<Marche, MarcheDto, Integer> implements MarcheService {

	@Autowired
	@Qualifier("marcheDao")
	private MarcheDao marcheDao;

	@Autowired
	private Mapper mapper;

	@Autowired
	@Qualifier("situationService")
	private SituationService situationService;

	public MarcheServiceImpl() {

	}

	@Override
	protected CommonDao<Marche, Integer> getDao() {
		return marcheDao;
	}

	@Override
	public MarcheDto enregistrerMarche(MarcheDto marcheDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_MARCHE, UtilConstants.SITUATION_CREEE_CODE);
		marcheDto.setSituation(situation);
		marcheDto = save(marcheDto);
		situationService.saveSituationOccurrence(situation, marcheDto.getId());

		logger.info("Nouveau marche cree.");
		return marcheDto;
	}

	@Override
	public void supprimerMarche(MarcheDto marche) {
		this.remove(marche);

	}

	@Override
	public MarcheDto validerEnregistrementMarche(MarcheDto marcheDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_MARCHE, UtilConstants.SITUATION_VALIDEE_CODE);
		situation.setIdEntite(marcheDto.getId());
		marcheDto.setSituation(situation);

		marcheDto = save(marcheDto);

		situationService.saveSituationOccurrence(situation, marcheDto.getId());
		logger.info("marche valide.");
		return marcheDto;
	}

	@Override
	public MarcheDto cloturerMarche(MarcheDto marcheDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_MARCHE, UtilConstants.SITUATION_CLOTUREE_CODE);
		situation.setIdEntite(marcheDto.getId());
		marcheDto.setSituation(situation);

		marcheDto = save(marcheDto);

		situationService.saveSituationOccurrence(situation, marcheDto.getId());
		logger.info("marche cloture.");
		return marcheDto;
	}
}