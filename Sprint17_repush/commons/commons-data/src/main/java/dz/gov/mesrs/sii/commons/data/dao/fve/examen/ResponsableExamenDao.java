package dz.gov.mesrs.sii.commons.data.dao.fve.examen;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.examen.ResponsableExamen;

/**
 * 
 * @author BELDI Jamel  on : 1 oct. 2014  17:34:08
 */
 

public interface ResponsableExamenDao {

	public  void persist(ResponsableExamen transientInstance);

	public  void remove(ResponsableExamen persistentInstance);

	public  ResponsableExamen merge(ResponsableExamen detachedInstance);

	public  ResponsableExamen findById(int id);
		
	public  List<ResponsableExamen> findAll();
	
	public  List<ResponsableExamen> findByExamen(long idExamen);

	public  void removeByExamen(int idExamen);

	public List<ResponsableExamen> findBySalleExamen(int idSalleExamen);
	
	public  ResponsableExamen findByExamenAndIndividu(long idExamen, int idIndividu);
	
}