package dz.gov.mesrs.sii.commons.data.dao.fve.bac;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.bac.NoteAccessFiliere;

/**
 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 12:13:34
 */
public interface NoteAccessFiliereDao {

	public void persist(NoteAccessFiliere transientInstance);

	public void remove(NoteAccessFiliere persistentInstance);

	public NoteAccessFiliere merge(NoteAccessFiliere detachedInstance);

	public NoteAccessFiliere findById(int id);

	public List<NoteAccessFiliere> findAll();

}