/**
 * [dz.gov.mesrs.sii.recherche.business.service.RechercheThemeService.java] 
 * @author rlaib on : 14 d�c. 2014  17:33:39
 */
package dz.gov.mesrs.sii.recherche.business.service;

import java.util.List;
import dz.gov.mesrs.sii.recherche.business.model.dto.ThemeDto;

/**
 * @author rlaib  on : 14 dec. 2014  17:33:39
 */
public interface RechercheThemeService {
	
	ThemeDto insertOrUpdate(ThemeDto themeDto);
	void remove( Long id);
	List<ThemeDto> findStructureRechercheThemes(Long idStructure);
	public  ThemeDto findById(Long id);
	List<ThemeDto> findThemesByStructureGroup(Long structureId, Long groupeId);
}



