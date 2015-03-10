/**
 * [dz.gov.mesrs.sii.fve.business.persistence.scolarite.EnseignantChargePedagogiqueDao.java] 
 * @author rlaib on : 12 oct. 2014  15:48:20
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantChargePedagogique;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;

/**
 * @author rlaib  on : 12 oct. 2014  15:48:20
 */
public interface EnseignantChargePedagogiqueDao extends GenericFveDao<EnseignantChargePedagogique> {

	public List<EnseignantChargePedagogique> findByIdFicheServices(int idFiche);
	public DossierEmploye  findEnseignant(Integer idGroupePedagogique, Integer idRattachement, Integer idAp, Integer idNiveau);
	
	public List<Object[]>  findSyntheseChargesEnseignant(
			Integer idEtab, 
			Long idUser, 
			Integer idAnnee,
			Integer idPeriode);
}
