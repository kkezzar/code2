/**
 * [dz.gov.mesrs.sii.commons.data.dao.fve.examen.NoteEliminatoireDao.java] 
 * @author MAKERRI Sofiane on : 18 janv. 2015  12:25:32
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.examen;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.examen.NoteEliminatoire;

/**
 * @author MAKERRI Sofiane on : 18 janv. 2015 12:25:32
 */

public interface NoteEliminatoireDao {
	
	public void persist(NoteEliminatoire transientInstance);

	public void remove(NoteEliminatoire persistentInstance);

	public NoteEliminatoire merge(NoteEliminatoire detachedInstance);

	public NoteEliminatoire findById(int id);

	public List<NoteEliminatoire> findAll();

	public List<NoteEliminatoire> findAdvanced(NoteEliminatoire search);
}
