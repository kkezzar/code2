/**
 * [dz.gov.mesrs.sii.fve.business.service.cursus.BilanPeriodeService.java] 
 * @author MAKERRI Sofiane on : 22 oct. 2014  15:50:54
 */
package dz.gov.mesrs.sii.fve.business.service.examen;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanUeDto;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:50:54
 */
public interface BilanUeService {

	public BilanUeDto insertOrUpdate(BilanUeDto bilanUeDto);

	public void remove(BilanUeDto bilanUeDto);

	public BilanUeDto findById(int id);

	public List<BilanUeDto> findAll();

	public BilanUeDto findUniqueSession(long bilanSessionId, int repartitionUeId);
	
	public BilanUeDto findUniquePeriode(int bilanSessionId, int repartitionUeId);
	
	public List<BilanUeDto> findByBilanSessionId(int bilanSessionId);
	
	public List<BilanUeDto> findByOofAndNiveau(Integer oofId, Integer niveauId);

}
