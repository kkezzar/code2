/**
 * [dz.gov.mesrs.sii.fve.business.persistence.cursus.AssociationGroupePedagogiqueDao.java] 
 * @author MAKERRI Sofiane on : 2 oct. 2014  09:52:01
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AssociationGroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.GroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.UniteEnseignement;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

public interface AssociationGroupePedagogiqueDao {

	public void persist(AssociationGroupePedagogique transientInstance);

	public void remove(AssociationGroupePedagogique persistentInstance);

	public AssociationGroupePedagogique merge(
			AssociationGroupePedagogique detachedInstance);

	public AssociationGroupePedagogique findById(int id);

	public AssociationGroupePedagogique findUnique(int gpId,
			int rattachementMc, int apId);

	public List<AssociationGroupePedagogique> findByRattachementMcAndAp(
			Integer rattachementMcId, Integer apId);
	
	public List<GroupePedagogique> findByRattachementMcId(Integer oofId, Integer periodeId,
			Integer rattachementMcId);
	
	public  List<AssociationGroupePedagogique> findAdvanced(Integer rattachementMcId, Integer apId, Integer oofId, Integer periodeId);

	public List<AssociationGroupePedagogique> findAll();

	public List<AssociationGroupePedagogique> findByGroupePedagogiqueId(int gpId);

	public boolean associationExist(Integer id, Integer gpId,
			Integer rattachementMcId, Integer apId);

	public boolean groupeNameExist(Integer oofId, Integer periodeId, String nom,
			AssociationGroupePedagogique associationGroupePedagogique);

	public List<UniteEnseignement> findUeByGroupePedagogiqueId(Integer gpId);

	public List<RattachementMc> findRattachementMcByGroupePedagogiqueId(
			Integer gpId);

}
