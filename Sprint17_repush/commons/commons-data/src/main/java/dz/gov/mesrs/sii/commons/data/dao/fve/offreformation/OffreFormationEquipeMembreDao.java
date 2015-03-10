/**
 * [dz.gov.mesrs.sii.fve.business.persistence.offreformation.OffreFormationDetailDao.java] 
 * @author rlaib on : 6 f�vr. 2014  13:04:48
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationEquipeMembre;


/**
 * @author  Rafik LAIB  on : 5 avr. 2014  22:06:24
 */
public interface OffreFormationEquipeMembreDao {
	
	public  void persist(OffreFormationEquipeMembre transientInstance);

	public  void remove(int id);

	public  OffreFormationEquipeMembre merge(OffreFormationEquipeMembre detachedInstance);

	public  OffreFormationEquipeMembre findById(int id);
		
	public  List<OffreFormationEquipeMembre> findAll();
	
	public  List<OffreFormationEquipeMembre> findMembersByTeamId(int teamId);

}
