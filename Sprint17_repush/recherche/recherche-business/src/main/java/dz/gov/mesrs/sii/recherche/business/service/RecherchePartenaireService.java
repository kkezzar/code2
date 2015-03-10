/**
 * [dz.gov.mesrs.sii.recherche.business.service.RecherchePartenaireService.java] 
 * @author rlaib on : 14 d�c. 2014  17:33:39
 */
package dz.gov.mesrs.sii.recherche.business.service;
import java.util.List;
import dz.gov.mesrs.sii.recherche.business.model.dto.PartenaireDto;

/**
 * @author rlaib  on : 14 dec. 2014  17:33:39
 */
public interface RecherchePartenaireService {
	
	PartenaireDto insertOrUpdate(PartenaireDto partenaireDto);
	void remove(Long id);
	List<PartenaireDto> findStructureRecherchePartenaires(Long idStructure);
	PartenaireDto findById(Long id);
}

/**
 * [dz.gov.mesrs.sii.recherche.business.service.RecherchePartenaireService.java] 
 * @author rlaib on : 14 d�c. 2014  17:33:39
 */
