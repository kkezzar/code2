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
import dz.gov.mesrs.sii.commons.data.dao.gfc.DossierPaiementDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DossierPaiement;
import dz.gov.mesrs.sii.gfc.business.model.dto.DocumentRealisationMarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DossierPaiementDto;
import dz.gov.mesrs.sii.gfc.business.service.DocumentRealisationMarcheService;
import dz.gov.mesrs.sii.gfc.business.service.DossierPaiementService;

/**
 * Service Implementation for domain model class DossierPaiement.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("dossierPaiementService")
@Transactional
public class DossierPaiementServiceImpl extends CommonServiceImpl<DossierPaiement, DossierPaiementDto, Integer>
		implements DossierPaiementService {

	@Autowired
	@Qualifier("dossierPaiementDao")
	private DossierPaiementDao dossierPaiementDao;

	@Autowired
	@Qualifier("documentRealisationMarcheService")
	private DocumentRealisationMarcheService documentRealisationMarcheService;

	@Autowired
	private Mapper mapper;

	@Autowired
	@Qualifier("situationService")
	private SituationService situationService;

	public DossierPaiementServiceImpl() {

	}

	@Override
	protected CommonDao<DossierPaiement, Integer> getDao() {
		return dossierPaiementDao;
	}

	@Override
	public DossierPaiementDto enregistrerDossierPaiement(DossierPaiementDto dossierPaiement) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_DOSSIER_PAIEMENT, UtilConstants.SITUATION_CREEE_CODE);
		dossierPaiement.setSituation(situation);

		// TODO numero de la demande

		dossierPaiement = save(dossierPaiement);
		situationService.saveSituationOccurrence(situation, dossierPaiement.getId());

		logger.info("Nouveau dossier de paiement cree.");
		return dossierPaiement;
	}

	@Override
	public void supprimerDossierPaiement(DossierPaiementDto dossierPaiement) {
		remove(dossierPaiement);

	}

	@Override
	public DossierPaiementDto envoyerDossierPaiement(DossierPaiementDto dossierPaiement) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_DOSSIER_PAIEMENT, UtilConstants.SITUATION_ENVOYE_CODE);
		situation.setIdEntite(dossierPaiement.getId());
		dossierPaiement.setSituation(situation);

		dossierPaiement = save(dossierPaiement);

		// mise a jours de a la situation du document de realisation
		SituationEntiteDto situationDocRealisation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_DOC_REALISATION, UtilConstants.SITUATION_ENVOYE_CODE);
		DocumentRealisationMarcheDto documentRealisationMarche = dossierPaiement.getDocumentRealisationMarche();
		documentRealisationMarche.setSituation(situationDocRealisation);
		documentRealisationMarcheService.save(documentRealisationMarche);

		situationService.saveSituationOccurrence(situation, dossierPaiement.getId());
		logger.info("Dossier de paiement envoye.");
		return dossierPaiement;
	}

	@Override
	public DossierPaiementDto validerDossierPaiement(DossierPaiementDto dossierPaiement) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_DOSSIER_PAIEMENT, UtilConstants.SITUATION_VALIDEE_CODE);
		situation.setIdEntite(dossierPaiement.getId());
		dossierPaiement.setSituation(situation);

		dossierPaiement = save(dossierPaiement);

		situationService.saveSituationOccurrence(situation, dossierPaiement.getId());
		logger.info("Dossier de paiement validee.");
		return dossierPaiement;
	}

}