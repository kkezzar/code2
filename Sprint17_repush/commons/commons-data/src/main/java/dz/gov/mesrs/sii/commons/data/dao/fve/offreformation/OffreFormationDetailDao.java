/**
 * [dz.gov.mesrs.sii.fve.business.persistence.offreformation.OffreFormationDetailDao.java] 
 * @author rlaib on : 6 f�vr. 2014  13:04:48
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationDetail;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 02-02-2014 16:44:07
 */
public interface OffreFormationDetailDao {
	
	public  void persist(OffreFormationDetail transientInstance);

	public  void remove(OffreFormationDetail persistentInstance);

	public  OffreFormationDetail merge(OffreFormationDetail detachedInstance);

	public  OffreFormationDetail findById(int id);
		
	public  List<OffreFormationDetail> findAll();
	

}
