package dz.gov.mesrs.sii.commons.data.dao.fve.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.PlageHoraire;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:52:05
 */
 

public interface PlageHoraireDao {

	public  void persist(PlageHoraire transientInstance);

	public  void remove(PlageHoraire persistentInstance);

	public  PlageHoraire merge(PlageHoraire detachedInstance);

	public  PlageHoraire findById(int id);
		
	public  List<PlageHoraire> findAll();

}