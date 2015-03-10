package dz.gov.mesrs.sii.gfc.business.service;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.gfc.EvenementMarche;
import dz.gov.mesrs.sii.gfc.business.model.dto.EvenementMarcheDto;

/**
 * Service Interface for domain model class EvenementMarche.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface EvenementMarcheService extends CommonService<EvenementMarche, EvenementMarcheDto, Integer> {

	public EvenementMarcheDto enregistrerEvenementMarche(EvenementMarcheDto evenementMarche);

	public void supprimerEvenementMarche(EvenementMarcheDto evenementMarche);

	public EvenementMarcheDto validerEnregistrementEvenementMarche(EvenementMarcheDto evenementMarche);
}