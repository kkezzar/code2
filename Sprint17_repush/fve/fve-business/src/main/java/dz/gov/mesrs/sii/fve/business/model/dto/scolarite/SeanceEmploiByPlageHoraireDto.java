package dz.gov.mesrs.sii.fve.business.model.dto.scolarite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author BELDI Jamel  on : 25 oct. 2014  11:16:52
 */


public class SeanceEmploiByPlageHoraireDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 22 oct. 2014  12:11:50
	 */
	private static final long serialVersionUID = 1L;
	//Plage Horaire
	PlageHoraireDto plageHoraireDto = new PlageHoraireDto();
	//private Map<String,SeanceEmploiDto> seanceEmploiDtos = new HashMap<String, SeanceEmploiDto>();
	private Map<String,List<SeanceEmploiDto>> seanceEmploiDtos = new HashMap<String, List<SeanceEmploiDto>>();
	private Map<String,Boolean> affectations = new HashMap<String, Boolean>();
	

	public SeanceEmploiByPlageHoraireDto() {
	}

	public SeanceEmploiByPlageHoraireDto(PlageHoraireDto plageHoraireDto,  Map<String,List<SeanceEmploiDto>> seanceEmploiDtos) {
		this.plageHoraireDto = plageHoraireDto;
		this.seanceEmploiDtos = seanceEmploiDtos;
	}

	/**
	 * [SeanceEmploiByPlageHoraireDto.plageHoraireDto] Getter 
	 * @author BELDI Jamel on : 25 oct. 2014  11:25:35
	 * @return the plageHoraireDto
	 */
	public PlageHoraireDto getPlageHoraireDto() {
		return plageHoraireDto;
	}



	/**
	 * [SeanceEmploiByPlageHoraireDto.plageHoraireDto] Setter 
	 * @author BELDI Jamel on : 25 oct. 2014  11:25:35
	 * @param plageHoraireDto the plageHoraireDto to set
	 */
	public void setPlageHoraireDto(PlageHoraireDto plageHoraireDto) {
		this.plageHoraireDto = plageHoraireDto;
	}



	/**
	 * [SeanceEmploiByPlageHoraireDto.seanceEmploiDtos] Getter 
	 * @author BELDI Jamel on : 25 oct. 2014  11:25:35
	 * @return the seanceEmploiDtos
	 */
	public Map<String,List<SeanceEmploiDto>>getSeanceEmploiDtos() {
		return seanceEmploiDtos;
	}



	/**
	 * [SeanceEmploiByPlageHoraireDto.seanceEmploiDtos] Setter 
	 * @author BELDI Jamel on : 25 oct. 2014  11:25:35
	 * @param seanceEmploiDtos the seanceEmploiDtos to set
	 */
	public void setSeanceEmploiDtos(Map<String,List<SeanceEmploiDto>> seanceEmploiDtos) {
		this.seanceEmploiDtos = seanceEmploiDtos;
	}

	/**
	 * [SeanceEmploiByPlageHoraireDto.affectations] Getter 
	 * @author BELDI Jamel on : 2 nov. 2014  18:23:21
	 * @return the affectations
	 */
	public Map<String, Boolean> getAffectations() {
		return affectations;
	}

	/**
	 * [SeanceEmploiByPlageHoraireDto.affectations] Setter 
	 * @author BELDI Jamel on : 2 nov. 2014  18:23:21
	 * @param affectations the affectations to set
	 */
	public void setAffectations(Map<String, Boolean> affectations) {
		this.affectations = affectations;
	}

	

	




	
	

}
