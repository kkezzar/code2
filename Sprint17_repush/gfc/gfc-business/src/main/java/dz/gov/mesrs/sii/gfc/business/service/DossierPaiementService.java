package dz.gov.mesrs.sii.gfc.business.service;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.gfc.DossierPaiement;
import dz.gov.mesrs.sii.gfc.business.model.dto.DossierPaiementDto;

/**
 * Service Interface for domain model class DossierPaiementDto.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface DossierPaiementService extends CommonService<DossierPaiement, DossierPaiementDto, Integer> {

	public DossierPaiementDto enregistrerDossierPaiement(DossierPaiementDto dossierPaiement);

	public void supprimerDossierPaiement(DossierPaiementDto dossierPaiement);

	public DossierPaiementDto envoyerDossierPaiement(DossierPaiementDto dossierPaiement);

	public DossierPaiementDto validerDossierPaiement(DossierPaiementDto dossierPaiement);
}