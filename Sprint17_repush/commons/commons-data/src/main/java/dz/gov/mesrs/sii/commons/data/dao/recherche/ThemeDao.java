package dz.gov.mesrs.sii.commons.data.dao.recherche;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Theme;

/**
 * @author rlaib  on : 16 déc. 2014  13:58:17
 */
public interface ThemeDao extends GenericFveDao<Theme> {

	List<Theme> findStructureRechercheThemes(Long idStructure);

	List<Theme> findThemesByStructureGroup(Long structureId, Long groupeId);
}
