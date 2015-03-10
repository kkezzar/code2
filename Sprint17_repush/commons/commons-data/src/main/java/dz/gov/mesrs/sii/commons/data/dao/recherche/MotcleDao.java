package dz.gov.mesrs.sii.commons.data.dao.recherche;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Motcle;

/**
 * @author rlaib  on : 16 d�c. 2014  13:58:17
 */
public interface MotcleDao extends GenericFveDao<Motcle> {

	List<Motcle> findListMotsClesByIdProjet(Long id);

	
}
