package dz.gov.mesrs.sii.gfc.business.service;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.gfc.Marche;
import dz.gov.mesrs.sii.gfc.business.model.dto.MarcheDto;

/**
 * Service Interface for domain model class Marche.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface MarcheService extends CommonService<Marche, MarcheDto, Integer> {

	public MarcheDto enregistrerMarche(MarcheDto marche);

	public void supprimerMarche(MarcheDto marche);

	public MarcheDto validerEnregistrementMarche(MarcheDto marche);

	public MarcheDto cloturerMarche(MarcheDto marche);

}