package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.LigneReleveDeNotes;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface LigneReleveDeNotesDao {

	public  void persist(LigneReleveDeNotes transientInstance);

	public  void remove(LigneReleveDeNotes persistentInstance);

	public  LigneReleveDeNotes merge(LigneReleveDeNotes detachedInstance);

	public  LigneReleveDeNotes findById(int id);
	
	public List<LigneReleveDeNotes> findAll();
	
	public List<LigneReleveDeNotes> findLignesOfReleve(int idReleve);
	
	public void deleteAllLignesOfReleve(int idReleve);
	
}