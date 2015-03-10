/**
 * [dz.gov.mesrs.sii.fve.business.persistence.offreformation.OffreFormationDetailDao.java] 
 * @author rlaib on : 6 f�vr. 2014  13:04:48
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationPartenaire;


/**
 * @author  Rafik LAIB  on : 5 avr. 2014  22:06:38
 */
public interface OffreFormationPartenaireDao {
	
	public  void persist(OffreFormationPartenaire transientInstance);

	public  void remove(int id);

	public  OffreFormationPartenaire merge(OffreFormationPartenaire detachedInstance);

	public  OffreFormationPartenaire findById(int id);
		
	public  List<OffreFormationPartenaire> findAll();
	
	public  List<OffreFormationPartenaire> findPartnersByContractId(int contractId);

}
