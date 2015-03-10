package dz.gov.mesrs.sii.gfc.business.service.impl;

import java.util.List;

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
import dz.gov.mesrs.sii.commons.data.dao.gfc.DocumentRealisationMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DocumentRealisationMarche;
import dz.gov.mesrs.sii.gfc.business.model.dto.DocumentRealisationMarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.MarcheDto;
import dz.gov.mesrs.sii.gfc.business.service.DocumentRealisationMarcheService;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * Service Implementation for domain model class DocumentRealisationMarche.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("documentRealisationMarcheService")
@Transactional
public class DocumentRealisationMarcheServiceImpl extends
		CommonServiceImpl<DocumentRealisationMarche, DocumentRealisationMarcheDto, Integer> implements
		DocumentRealisationMarcheService {

	@Autowired
	@Qualifier("documentRealisationMarcheDao")
	private DocumentRealisationMarcheDao documentRealisationMarcheDao;

	@Autowired
	private Mapper mapper;

	@Autowired
	@Qualifier("situationService")
	private SituationService situationService;

	public DocumentRealisationMarcheServiceImpl() {

	}

	@Override
	protected CommonDao<DocumentRealisationMarche, Integer> getDao() {
		return documentRealisationMarcheDao;
	}

	@Override
	public DocumentRealisationMarcheDto enregistrerDocuementRealisationMarche(
			DocumentRealisationMarcheDto documentRealisationMarcheDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_DOC_REALISATION, UtilConstants.SITUATION_CREEE_CODE);
		documentRealisationMarcheDto.setSituation(situation);
		documentRealisationMarcheDto = save(documentRealisationMarcheDto);
		situationService.saveSituationOccurrence(situation, documentRealisationMarcheDto.getId());

		logger.info("document de realisation de marche cree.");
		return documentRealisationMarcheDto;
	}

	@Override
	public void supprimerDocuementRealisationMarche(DocumentRealisationMarcheDto documentRealisationMarcheDto) {
		this.remove(documentRealisationMarcheDto);

	}

	@Override
	public DocumentRealisationMarcheDto validerEnregistrementDocuementRealisationMarche(
			DocumentRealisationMarcheDto documentRealisationMarcheDto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_DOC_REALISATION, UtilConstants.SITUATION_VALIDEE_CODE);
		situation.setIdEntite(documentRealisationMarcheDto.getId());
		documentRealisationMarcheDto.setSituation(situation);

		documentRealisationMarcheDto = save(documentRealisationMarcheDto);

		situationService.saveSituationOccurrence(situation, documentRealisationMarcheDto.getId());
		logger.info("document de realisation de marche valide.");
		return documentRealisationMarcheDto;
	}

	@Override
	public List<DocumentRealisationMarcheDto> findDocumentsRealisationForPaiement(RefEtablissementDto etablissment,
			RefStructureDto structure) {
		// documents de realisation avec situation valide seulement!
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_DOC_REALISATION, UtilConstants.SITUATION_VALIDEE_CODE);
		DocumentRealisationMarcheDto documentRealisationMarcheDto = new DocumentRealisationMarcheDto();
		documentRealisationMarcheDto.setSituation(situation);

		// marche qui n'est pas encore cloture + dans l'etablissement +
		// structure bien precise
		MarcheDto marche = new MarcheDto();
		SituationEntiteDto situationMarche = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_MARCHE, UtilConstants.SITUATION_VALIDEE_CODE);
		marche.setSituation(situationMarche);
		marche.setEtablissement(etablissment);
		marche.setStructure(structure);
		documentRealisationMarcheDto.setMarche(marche);
		return this.findAllByExample(documentRealisationMarcheDto);

		// DocumentRealisationMarche example =
		// mapper.map(documentRealisationMarcheDto,
		// DocumentRealisationMarche.class);
		// List<DocumentRealisationMarche> results =
		// documentRealisationMarcheDao.findAllByExample(example);
		// return this.mapReturn(results);
	}
}
