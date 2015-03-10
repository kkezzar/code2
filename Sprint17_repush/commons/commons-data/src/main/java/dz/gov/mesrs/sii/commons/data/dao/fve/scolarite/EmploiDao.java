package dz.gov.mesrs.sii.commons.data.dao.fve.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.Emploi;

/**
 * 
 * @author BELDI Jamel  on : 22 oct. 2014  12:16:53
 */
 

public interface EmploiDao {

	public  void persist(Emploi transientInstance);

	public  void remove(Emploi persistentInstance);

	public  Emploi merge(Emploi detachedInstance);

	public  Emploi findById(int id);
		
	public  List<Emploi> findAll();

	public List<Emploi> findAdvanced(Emploi emploiSearch);

}