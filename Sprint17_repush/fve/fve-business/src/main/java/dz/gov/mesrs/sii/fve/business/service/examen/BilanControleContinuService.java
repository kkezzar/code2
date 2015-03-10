/**
 * [dz.gov.mesrs.sii.fve.business.service.cursus.BilanControleContinuService.java] 
 * @author MAKERRI Sofiane on : 16 oct. 2014  17:22:47
 */
package dz.gov.mesrs.sii.fve.business.service.examen;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanControleContinuDto;

/**
 * @author MAKERRI Sofiane on : 16 oct. 2014 17:22:47
 */
public interface BilanControleContinuService {
	
	public BilanControleContinuDto insertOrUpdate(
			BilanControleContinuDto bilanControleContinuDto);

	public void remove(BilanControleContinuDto bilanControleContinuDto);

	public BilanControleContinuDto findById(int id);

	public List<BilanControleContinuDto> findAll();
	
	public BilanControleContinuDto findUnique(Integer anneeId, Integer oofId, Integer periodeId, Integer affGpId, Integer rattachementMcId);
	
	public BilanControleContinuDto findUniqueByDiaId(Integer anneeId, Integer oofId, Integer periodeId, Integer diaId, Integer rattachementMcId);
	
	public List<BilanControleContinuDto> findAdvanced(BilanControleContinuDto searchDto);
	


	
}
