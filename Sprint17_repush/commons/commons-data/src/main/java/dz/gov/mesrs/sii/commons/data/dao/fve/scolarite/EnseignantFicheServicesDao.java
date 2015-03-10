/**
 * [dz.gov.mesrs.sii.fve.business.persistence.scolarite.EnseignantFicheServicesDao.java] 
 * @author rlaib on : 12 oct. 2014  15:48:20
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantFicheServices;

/**
 * @author rlaib  on : 12 oct. 2014  15:48:20
 */
public interface EnseignantFicheServicesDao extends GenericFveDao<EnseignantFicheServices> {

	public List<EnseignantFicheServices> findFichesServicesByEtabByUserByPeriodeByYear(Integer idEtab, Long idUser,Integer idAnnee, Integer idPeriode);
	
	public List<EnseignantFicheServices> findFichesServicesByEtabBySituation(Integer idEtab, Integer idSituation);
	
}
