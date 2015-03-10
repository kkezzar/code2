/**
 * [dz.gov.mesrs.sii.fve.business.service.cursus.BilanPeriodeService.java] 
 * @author MAKERRI Sofiane on : 22 oct. 2014  15:50:54
 */
package dz.gov.mesrs.sii.fve.business.service.examen;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanMcDto;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:50:54
 */
public interface BilanMcService {

	public BilanMcDto insertOrUpdate(BilanMcDto bilanMcDto);

	public void remove(BilanMcDto bilanMcDto);

	public BilanMcDto findById(int id);

	public List<BilanMcDto> findAll();

	public BilanMcDto findUniqueSession(int bilanUeId, int rattachementMcId);

	public BilanMcDto findUniquePeriode(int bilanUeId, int rattachementMcId);
	
	public List<BilanMcDto> findByBilanUeId(int bilanUeId);
	
	public List<BilanMcDto> findByOofAndNiveau(Integer oofId, Integer niveauId);

}
