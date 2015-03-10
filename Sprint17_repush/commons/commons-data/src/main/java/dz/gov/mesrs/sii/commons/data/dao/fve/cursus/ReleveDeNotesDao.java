package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.ReleveDeNotes;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface ReleveDeNotesDao {

	public  void persist(ReleveDeNotes transientInstance);

	public  void remove(ReleveDeNotes persistentInstance);

	public  ReleveDeNotes merge(ReleveDeNotes detachedInstance);

	public  ReleveDeNotes findById(int id);
	
	public  List<ReleveDeNotes> findAll();

}