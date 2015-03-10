package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ReintegrationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 09-07-2014 16:44:07
 */

public interface ReintegrationService {

	public ReintegrationDto insertOrUpdate(ReintegrationDto reintegrationDto);

	public void remove(ReintegrationDto reintegrationDto);

	public ReintegrationDto findById(int id);

	public List<ReintegrationDto> findByQuery(String query);

	public List<ReintegrationDto> findAll();

	public List<ReintegrationDto> findAdvanced(ReintegrationDto searchDto);

	void delete(ReintegrationDto reintegrationDto);

	public List<ReintegrationDto> findGeneric(String value);

	public ReintegrationDto save(ReintegrationDto reintegrationDto);

	public void update(ReintegrationDto reintegrationDto);

	public List<ReintegrationDto> findReintegrationsByIdExclusion(
			int idExclusion);

	/**
	 * @author Mounir.MESSAOUDI on : 21 oct. 2014 11:38:42
	 * @param reintegrationSearchDto
	 * @param refCodeEtablissement
	 * @param searchOuverturesOffreFormationDtos
	 * @return
	 */
	List<ReintegrationDto> findAdvanced(
			ReintegrationDto reintegrationSearchDto,
			String refCodeEtablissement,
			List<OuvertureOffreFormationDto> searchOuverturesOffreFormationDtos);

}
