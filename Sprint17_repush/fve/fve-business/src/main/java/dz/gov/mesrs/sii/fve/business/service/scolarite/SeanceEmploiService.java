package dz.gov.mesrs.sii.fve.business.service.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EmploiDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EmploiModelDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.SeanceEmploiByPlageHoraireDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.SeanceEmploiDto;
/**
 * 
 * @author BELDI Jamel  on : 22 oct. 2014  12:29:44
 */
public interface SeanceEmploiService {

	public SeanceEmploiDto insertOrUpdate(SeanceEmploiDto seanceEmploiDto);

	public void remove(SeanceEmploiDto seanceEmploiDto);

	public SeanceEmploiDto findById(int id);

	public List<SeanceEmploiDto> findAll();
	
	public List<SeanceEmploiByPlageHoraireDto> findByEmploiId(EmploiDto emploiDto);
	
	public List<SeanceEmploiDto> findByEmploiIdAndCelluleId(int idEmploi, int idCellule);
	
	public List<SeanceEmploiByPlageHoraireDto> findbyGroupe(SeanceEmploiDto searchDto);
	
	public List<EmploiModelDto> convertToEmploiModel(List<SeanceEmploiByPlageHoraireDto> seancesList);
	
	public List<SeanceEmploiDto> findAdvanced(SeanceEmploiDto searchDto);

}