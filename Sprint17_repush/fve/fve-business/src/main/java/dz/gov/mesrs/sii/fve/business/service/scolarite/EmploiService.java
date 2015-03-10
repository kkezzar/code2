package dz.gov.mesrs.sii.fve.business.service.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EmploiDto;

/**
 * 
 * @author BELDI Jamel on : 22 oct. 2014 12:29:19
 */

public interface EmploiService {

	public EmploiDto insertOrUpdate(EmploiDto emploiDto);

	public void remove(EmploiDto emploiDto);

	public EmploiDto findById(int id);

	public List<EmploiDto> findAll();

	public List<EmploiDto> findAdvanced(EmploiDto emploiSearchDto);

}