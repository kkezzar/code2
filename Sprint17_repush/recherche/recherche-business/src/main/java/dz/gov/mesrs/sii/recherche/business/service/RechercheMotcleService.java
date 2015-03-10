/**
 * [dz.gov.mesrs.sii.recherche.business.service.RecherchePartenaireService.java] 
 * @author sRamdane  on : 08 fev 2015   17:33:39
 */
package dz.gov.mesrs.sii.recherche.business.service;

import java.util.List;

import dz.gov.mesrs.sii.recherche.business.model.dto.MotcleDto;

/**
 * @author sRamdane  on : 08 fev 2015  17:33:39
 */
public interface RechercheMotcleService {

	MotcleDto saveMotcle(MotcleDto motcleDto);

	void removeMotcle(Long motcleId);

	MotcleDto findById(Long motcleId);

	List<MotcleDto> findListMotsClesByIdProjet(Long id);
	
	
}
