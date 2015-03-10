package dz.gov.mesrs.sii.commons.data.dao.fve.bac;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.bac.NotesBachelier;

/**
 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 12:13:34
 */
public interface NoteBachelierDao {

	public void persist(NotesBachelier transientInstance);

	public void remove(NotesBachelier persistentInstance);

	public NotesBachelier merge(NotesBachelier detachedInstance);

	public NotesBachelier findById(int id);

	public List<NotesBachelier> findAll();

	public List<NotesBachelier> findByIdDossierBachelier(
			String matriculeBachelier);

	public List<NotesBachelier> findByIdDossierBachelierRefCodeMatiere(
			String matriculeBachelier, String refCodeMatiere);
}