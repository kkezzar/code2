/**
 * [dz.gov.mesrs.sii.fve.business.persistence.scolarite.EnseignantFicheVoeuxDao.java] 
 * @author rlaib on : 12 oct. 2014  15:48:20
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantVoeuLigne;

/**
 * @author rlaib  on : 12 oct. 2014  15:48:20
 */
public interface EnseignantVoeuLigneDao extends GenericFveDao<EnseignantVoeuLigne> {

	public List<EnseignantVoeuLigne> findByIdVoeu(int idVoeu);
	public List<EnseignantVoeuLigne> findByIdFiche(int idFiche);
}
