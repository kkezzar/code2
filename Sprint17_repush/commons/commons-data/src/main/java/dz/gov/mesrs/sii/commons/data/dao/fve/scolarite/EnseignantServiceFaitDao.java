/**
 * [dz.gov.mesrs.sii.fve.business.persistence.scolarite.EnseignantFicheVoeuxDao.java] 
 * @author rlaib on : 12 oct. 2014  15:48:20
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantServiceFait;

/**
 * @author rlaib  on : 12 oct. 2014  15:48:20
 */
public interface EnseignantServiceFaitDao extends GenericFveDao<EnseignantServiceFait> {

	public List<EnseignantServiceFait> findByEtabByUserByOfByPeriodeByYearByCharge(Integer idEtab, Long idUser,Integer idAnnee, Integer idOf, Integer idPeriode, Integer idCharge);

}
