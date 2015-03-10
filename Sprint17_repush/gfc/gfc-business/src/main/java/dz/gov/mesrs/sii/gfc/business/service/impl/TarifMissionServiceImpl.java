package dz.gov.mesrs.sii.gfc.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.TacheDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.TarifMissionDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.CategorieProfessionnelleDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Tache;
import dz.gov.mesrs.sii.commons.data.model.gfc.TarifMission;
import dz.gov.mesrs.sii.commons.data.model.grh.CategorieProfessionnelle;
import dz.gov.mesrs.sii.gfc.business.model.dto.CategorieProfessionnelleDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.TarifMissionDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.TarifMissionDto;
import dz.gov.mesrs.sii.gfc.business.service.TarifMissionService;

/**
 * Service Implementation for domain model class DetailRealisationPrestation.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("tarifMissionService")
@Transactional
public class TarifMissionServiceImpl extends CommonServiceImpl<TarifMission, TarifMissionDto, Integer> implements
		TarifMissionService {

	@Autowired
	@Qualifier("tarifMissionDao")
	private TarifMissionDao tarifMissionDao;

	@Autowired
	@Qualifier("categorieProfessionnelleDao")
	private CategorieProfessionnelleDao categorieProfessionnelleDao;

	@Autowired
	private Mapper mapper;

	@Autowired
	@Qualifier("situationService")
	private SituationService situationService;

	public TarifMissionServiceImpl() {

	}

	@Override
	protected CommonDao<TarifMission, Integer> getDao() {
		return tarifMissionDao;
	}

	@Override
	public List<CategorieProfessionnelleDto> findListCategorieProf() {

		List<CategorieProfessionnelleDto> categorieProfessionnelleDtos = new ArrayList<CategorieProfessionnelleDto>();

		try {
			List<CategorieProfessionnelle> categoriesProfessionnelle = categorieProfessionnelleDao.findAll();

			for (CategorieProfessionnelle cat : categoriesProfessionnelle) {
				categorieProfessionnelleDtos.add(mapper.map(cat, CategorieProfessionnelleDto.class));
			}
		}
		catch (Exception e){

		}

		return categorieProfessionnelleDtos;
	}
	
	
	@Override
	public TarifMissionDto enregistrerTarif(TarifMissionDto tarifMissionDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_TARIF_MISSION, UtilConstants.SITUATION_CREEE_CODE);
		System.out.println("--situation---"+situation.getId());
		tarifMissionDto.setSituation(situation);
		tarifMissionDto = save(tarifMissionDto);
		situationService.saveSituationOccurrence(situation, tarifMissionDto.getId());

		logger.info("Tarif cree.");
		return tarifMissionDto;
	}

	

	@Override
	public TarifMissionDto validerEnregistrementTarif(TarifMissionDto tarifMissionDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_TARIF_MISSION, UtilConstants.SITUATION_VALIDEE_CODE);
		situation.setIdEntite(tarifMissionDto.getId());
		tarifMissionDto.setSituation(situation);

		tarifMissionDto = save(tarifMissionDto);

		situationService.saveSituationOccurrence(situation, tarifMissionDto.getId());
		logger.info("Tarif valide.");
		return tarifMissionDto;
	}

}