/**
 * [dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.CycleDiplomeDao.java] 
 * @author rlaib on : 24 nov. 2014  12:20:02
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.CycleDiplome;

/**
 * @author rlaib  on : 24 nov. 2014  12:20:02
 */
public interface CycleDiplomeDao extends GenericFveDao<CycleDiplome> {
	
	List<CycleDiplome> findDiplomesByCycle(Integer cycleId);
}
