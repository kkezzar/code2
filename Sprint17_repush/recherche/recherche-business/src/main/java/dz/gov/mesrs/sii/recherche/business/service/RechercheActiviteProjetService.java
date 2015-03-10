/**
 * [dz.gov.mesrs.sii.recherche.business.service.RecherchePartenaireService.java] 
 * @author sRamdane  on : 08 fev 2015   17:33:39
 */
package dz.gov.mesrs.sii.recherche.business.service;

import java.util.List;

import dz.gov.mesrs.sii.recherche.business.model.dto.ActiviteProjetDto;

/**
 * @author sRamdane  on : 08 fev 2015  17:33:39
 */
public interface RechercheActiviteProjetService {

	

	List<ActiviteProjetDto> finListActiviteByProjet(Long projetid);

	ActiviteProjetDto saveActivite(ActiviteProjetDto activiteProjet);		
	

	void removeActiviteProjet(Long activiteProjetId);

	ActiviteProjetDto findById(Long activiteId);
	
	
}



