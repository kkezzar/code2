/**
 * [dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormation.java] 
 * @author BELDI Jamel on : 12 mai 2014  15:35:34
 */
package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;

/**
 * @author BELDI Jamel on : 12 mai 2014 15:35:34
 */
public interface OuvertureOffreFormationService {

	public OuvertureOffreFormationDto insertOrUpdate(OuvertureOffreFormationDto ouvertureOffreFormationDto);

	public void remove(OuvertureOffreFormationDto ouvertureOffreFormationDto);

	public OuvertureOffreFormationDto findById(int id);

	public List<OuvertureOffreFormationDto> findAll();

	public List<OuvertureOffreFormationDto> findAdvanced(OuvertureOffreFormationDto searchDto);
	
	/**
	 * Cherche les overture d'offre de formation par spécialité, établissemlent et année académique
	 * 
	 * @param refCodeEtablissement
	 * @param refCodeSpecialite
	 * @param idAnneeAcademique
	 * @param estOuverte : true si les ouvertures recherchées sont ouvertes, false sinon.
	 * 
	 * @return la liste des ouvertures d'offre de formation vérifiant les critères de recherche passés en paramètre.
	 */
	public List<OuvertureOffreFormationDto> findAdvanced(
			String refCodeEtablissement, String refCodeSpecialite,
			Integer idAnneeAcademique, Boolean estOuverte);

	
}
