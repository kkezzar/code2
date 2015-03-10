/**
 * [dz.gov.mesrs.sii.fve.business.persistence.offreformation.OffreFormationDetailDao.java] 
 * @author rlaib on : 6 f�vr. 2014  13:04:48
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationContrat;

/**
 * @author  Rafik LAIB  on : 5 avr. 2014  22:05:04
 */
public interface OffreFormationContratDao {
	
	public  void persist(OffreFormationContrat transientInstance);

	public  void remove(OffreFormationContrat persistentInstance);

	public  OffreFormationContrat merge(OffreFormationContrat detachedInstance);

	public  OffreFormationContrat findById(int id);
		
	public  OffreFormationContrat findByRefCode(String refCode);
	
	public  List<OffreFormationContrat> findByOfId(int ofId);
	
	public  List<OffreFormationContrat> findAll();
	
	

}
