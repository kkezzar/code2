/**
 * [dz.gov.mesrs.sii.recherche.business.service.RecherchePartenaireService.java] 
 * @author sRamdane  on : 08 fev 2015   17:33:39
 */
package dz.gov.mesrs.sii.recherche.business.service;

import java.util.List;

import dz.gov.mesrs.sii.recherche.business.model.dto.ActiviteProjetDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ProjetPartenaireDto;

/**
 * @author sRamdane  on : 08 fev 2015  17:33:39
 */
public interface RechercheProjetPartenaireService {

	void removeProjetPartenaire(Long partenaireId);

	ProjetPartenaireDto findById(Long partenaireId);

	List<ProjetPartenaireDto> findListPartenaireByIdProjet(Long id);

	ProjetPartenaireDto savePartenaire(ProjetPartenaireDto partenaireProjet);
	
	
}
