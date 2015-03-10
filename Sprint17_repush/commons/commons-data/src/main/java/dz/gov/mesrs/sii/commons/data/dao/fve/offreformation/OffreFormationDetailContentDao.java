/**
 * [dz.gov.mesrs.sii.fve.business.persistence.offreformation.OffreFormationDetailContentDao.java] 
 * @author rlaib on : 6 f�vr. 2014  14:27:13
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationDetailContent;


/**
 * @author  Rafik LAIB  on : 5 avr. 2014  22:05:34
 */
public interface OffreFormationDetailContentDao {

	public  void persist(OffreFormationDetailContent transientInstance);

	public  void remove(OffreFormationDetailContent persistentInstance);

	public  OffreFormationDetailContent merge(OffreFormationDetailContent detachedInstance);

	public  OffreFormationDetailContent findById(int id);
			
	public  List<OffreFormationDetailContent> findAll();

	public  List<OffreFormationDetailContent> findOfContentTreeById(int ofId);

}
