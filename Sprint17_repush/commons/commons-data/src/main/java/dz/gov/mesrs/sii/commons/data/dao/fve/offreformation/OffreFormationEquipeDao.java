/**
 * [dz.gov.mesrs.sii.fve.business.persistence.offreformation.OffreFormationDetailDao.java] 
 * @author rlaib on : 6 f�vr. 2014  13:04:48
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationEquipe;


/**
 * @author  Rafik LAIB  on : 5 avr. 2014  22:06:06
 */
public interface OffreFormationEquipeDao {
	
	public  void persist(OffreFormationEquipe transientInstance);

	public  void remove(OffreFormationEquipe persistentInstance);

	public  OffreFormationEquipe merge(OffreFormationEquipe detachedInstance);

	public  OffreFormationEquipe findById(int id);
		
	public  List<OffreFormationEquipe> findAll();
	
	public OffreFormationEquipe findOfEquipeById(int ofId);

}
