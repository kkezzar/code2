package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ParcoursIndividualiseDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ParcoursIndividualiseMatiereDto;

/**
 * @author BELDI Jamel on : 14 juil. 2014 15:32:11
 */
public interface ParcoursIndividualiseService {

	public void insertOrUpdate(
			List<ParcoursIndividualiseMatiereDto> parcoursIndividualiseMatiereList);

	public void remove(
			ParcoursIndividualiseMatiereDto parcoursIndividualiseMatiereDto);

	public ParcoursIndividualiseDto/*List<ParcoursIndividualiseMatiereDto>*/ findById(int id);

	public ParcoursIndividualiseDto /*List<ParcoursIndividualiseMatiereDto>*/findByIdInscriptionAdministrative(
			int idInscriptionAdministrative);

	public List<ParcoursIndividualiseMatiereDto> findAll();

	public List<ParcoursIndividualiseMatiereDto> findAdvanced(
			ParcoursIndividualiseMatiereDto ParcoursIndividualiseMatiereDto);

	public List<ParcoursIndividualiseDto> findParcourstoValidate(String codeEtab);

	public void updateParcoursIndividualiseMatiere(
			ParcoursIndividualiseMatiereDto parcoursIndividualiseMatiereDto);

	public boolean updateSituation(int id, int idSituation);

	public  List<ParcoursIndividualiseDto> findAdvanced(ParcoursIndividualiseDto dto);
	
	public ParcoursIndividualiseDto/*List<ParcoursIndividualiseMatiereDto>*/ findByIdInscriptionAdministrativeAndPeriode(
			int idInscriptionAdministrative, int idPeriode);
	
	public List<DossierInscriptionAdministrativeDto> findDossierInscription(
			DossierInscriptionAdministrativeDto searchDto, GroupePedagogiqueDto groupePedagogiqueDto, Integer idSituation);
	
	public List<DossierInscriptionAdministrativeDto> findDossierInscriptions(Integer idRattachementMc, Integer idPeriode, Integer idAnneeAcademique, Integer idSituation);
	
	public List<DossierInscriptionAdministrativeDto> findDossierInscriptionsNotSubscribedOnExamen(Integer idRattachementMc, Integer idPeriode, Integer idAnneeAcademique, Integer idSituation, Integer idExamen, Integer idNiveau, String refCodeEtablissement);
}
