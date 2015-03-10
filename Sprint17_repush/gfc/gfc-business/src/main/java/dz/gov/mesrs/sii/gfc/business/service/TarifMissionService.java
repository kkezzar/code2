package dz.gov.mesrs.sii.gfc.business.service;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.gfc.TarifMission;
import dz.gov.mesrs.sii.gfc.business.model.dto.CategorieProfessionnelleDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.TarifMissionDto;

/**
 * Service Interface for domain model class DossierMission.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface TarifMissionService extends CommonService<TarifMission, TarifMissionDto, Integer> {

	/**
	 * for specific method
	 */
	public List<CategorieProfessionnelleDto>  findListCategorieProf ();
	public TarifMissionDto enregistrerTarif(TarifMissionDto tarifMission);

	public TarifMissionDto validerEnregistrementTarif(TarifMissionDto tarifMission);
}