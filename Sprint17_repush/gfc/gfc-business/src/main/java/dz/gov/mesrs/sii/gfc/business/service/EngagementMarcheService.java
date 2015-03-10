package dz.gov.mesrs.sii.gfc.business.service;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.gfc.EngagementMarche;
import dz.gov.mesrs.sii.gfc.business.model.dto.EngagementMarcheDto;

/**
 * Service Interface for domain model class EngagementMarche.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface EngagementMarcheService extends CommonService<EngagementMarche, EngagementMarcheDto, Integer> {

	public EngagementMarcheDto enregistrerDemandeEngagement(EngagementMarcheDto engagementMarche);

	public void supprimerDemandeEngagement(EngagementMarcheDto engagementMarche);

	public EngagementMarcheDto validerDemandeEngagement(EngagementMarcheDto engagementMarche);

	public EngagementMarcheDto envoyerDemandeEngamgent(EngagementMarcheDto marche);
}