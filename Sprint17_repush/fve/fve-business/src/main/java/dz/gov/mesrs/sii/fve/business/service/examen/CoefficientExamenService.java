/**
 * [dz.gov.mesrs.sii.fve.business.service.notation.ParametrageCoefficientExamenService.java] 
 * @author MAKERRI Sofiane on : 5 janv. 2015  09:26:55
 */
package dz.gov.mesrs.sii.fve.business.service.examen;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.CoefficientExamenDto;

/**
 * @author MAKERRI Sofiane on : 5 janv. 2015 09:26:55
 */

public interface CoefficientExamenService {

	public CoefficientExamenDto insertOrUpdate(
			CoefficientExamenDto parametrageCoefficientExamenDto);

	public void remove(
			CoefficientExamenDto parametrageCoefficientExamenDto);

	public CoefficientExamenDto findById(int id);

	public List<CoefficientExamenDto> findAll();
	
	public List<CoefficientExamenDto> findByOofAndPeriode(Integer oofId, Integer periodeId);
	
	public CoefficientExamenDto findUnique(Integer oofId, Integer periodeId, Integer rattachementMcId);

}
