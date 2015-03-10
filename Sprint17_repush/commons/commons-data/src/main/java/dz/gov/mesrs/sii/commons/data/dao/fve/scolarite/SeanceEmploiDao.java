package dz.gov.mesrs.sii.commons.data.dao.fve.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.SeanceEmploi;



/**
 * 
 * @author BELDI Jamel  on : 22 oct. 2014  12:16:43
 */
 

public interface SeanceEmploiDao {

	public  void persist(SeanceEmploi transientInstance);

	public  void remove(SeanceEmploi persistentInstance);

	public  SeanceEmploi merge(SeanceEmploi detachedInstance);

	public  SeanceEmploi findById(int id);
		
	public  List<SeanceEmploi> findAll();

	public List<SeanceEmploi> findByEmploiIdAndCelluleId(int idEmploi, int idCellule);
	
	public List<SeanceEmploi> findAdvanced(SeanceEmploi seanceEmploi);

}