/**
 * [dz.gov.mesrs.sii.fve.business.service.cursus.BilanControleContinuService.java] 
 * @author MAKERRI Sofiane on : 16 oct. 2014  17:22:47
 */
package dz.gov.mesrs.sii.fve.business.service.examen;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.DeliberationSessionDto;

/**
 * @author MAKERRI Sofiane on : 16 oct. 2014 17:22:47
 */
public interface DeliberationSessionService {

	public DeliberationSessionDto insertOrUpdate(
			DeliberationSessionDto deliberationSessionDto);

	public void remove(DeliberationSessionDto deliberationSessionDto);

	public DeliberationSessionDto findById(int id);

	public List<DeliberationSessionDto> findAll();
	
	public DeliberationSessionDto  findByPlanningId(Long planningId);
	
	public List<DeliberationSessionDto> findAdvanced(DeliberationSessionDto searchDto);
	
	public List<DeliberationSessionDto> findBilanPeriode(Integer anneeId, int oofId, Integer niveauId);
	
	public DeliberationSessionDto bilanPeriodeExist(int anneeId, int oofId, int periodeId);


}
