package dz.gov.mesrs.sii.gfc.business.service;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.gfc.DocumentRealisationMarche;
import dz.gov.mesrs.sii.gfc.business.model.dto.DocumentRealisationMarcheDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * Service Interface for domain model class DocumentRealisationMarche.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface DocumentRealisationMarcheService extends
		CommonService<DocumentRealisationMarche, DocumentRealisationMarcheDto, Integer> {

	public DocumentRealisationMarcheDto enregistrerDocuementRealisationMarche(
			DocumentRealisationMarcheDto documentRealisationMarcheDto);

	public void supprimerDocuementRealisationMarche(DocumentRealisationMarcheDto documentRealisationMarcheDto);

	public DocumentRealisationMarcheDto validerEnregistrementDocuementRealisationMarche(
			DocumentRealisationMarcheDto documentRealisationMarcheDto);

	public List<DocumentRealisationMarcheDto> findDocumentsRealisationForPaiement(RefEtablissementDto etablissment,
			RefStructureDto structure);
}