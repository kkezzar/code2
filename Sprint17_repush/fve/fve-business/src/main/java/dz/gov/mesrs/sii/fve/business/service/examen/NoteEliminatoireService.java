/**
 * [dz.gov.mesrs.sii.fve.business.service.examen.NoteEliminatoireService.java] 
 * @author MAKERRI Sofiane on : 18 janv. 2015  12:34:20
 */
package dz.gov.mesrs.sii.fve.business.service.examen;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.NoteEliminatoireDto;

/**
 * @author MAKERRI Sofiane on : 18 janv. 2015 12:34:20
 */

public interface NoteEliminatoireService {

	public NoteEliminatoireDto insertOrUpdate(
			NoteEliminatoireDto noteEliminatoireDto);

	public void remove(NoteEliminatoireDto noteEliminatoireDto);

	public NoteEliminatoireDto findById(int id);

	public List<NoteEliminatoireDto> findAll();

	public List<NoteEliminatoireDto> findAdvanced(NoteEliminatoireDto searchDto);

}
