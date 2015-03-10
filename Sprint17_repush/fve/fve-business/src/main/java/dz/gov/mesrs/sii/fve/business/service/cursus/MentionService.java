/**
 * [dz.gov.mesrs.sii.fve.business.service.cursus.BilanPeriodeService.java] 
 * @author MAKERRI Sofiane on : 22 oct. 2014  15:50:54
 */
package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.MentionDto;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:50:54
 */
public interface MentionService {

	public MentionDto insertOrUpdate(MentionDto mentionDto);

	public void remove(MentionDto mentionDto);

	public MentionDto findById(int id);

	public List<MentionDto> findAll();
	
	public MentionDto findByNote(Integer anneeId, double note);
	
	public List<MentionDto> findByAnneeAcademique(Integer anneeId);
}
