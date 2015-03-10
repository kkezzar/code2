package dz.gov.mesrs.sii.commons.data.dao.fve.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.CelluleHoraire;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:51:53
 */

public interface CelluleHoraireDao {

	public  void persist(CelluleHoraire transientInstance);

	public  void remove(CelluleHoraire persistentInstance);

	public  CelluleHoraire merge(CelluleHoraire detachedInstance);

	public  CelluleHoraire findById(int id);
		
	public  List<CelluleHoraire> findAll();

	public CelluleHoraire findByJourAndPlageHoraire(int idJour,
			int idPlageHoraire);

}