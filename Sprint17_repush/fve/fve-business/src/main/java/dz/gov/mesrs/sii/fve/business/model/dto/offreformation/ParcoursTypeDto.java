package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * @author  Rafik LAIB  on : 18 avr. 2014  19:42:45
 */
public class ParcoursTypeDto implements java.io.Serializable {

	
	/**
	 * serialVersionUID 
	 * @author  Rafik LAIB  on : 18 avr. 2014  19:42:06
	 */
	private static final long serialVersionUID = 20523087697462387L;
	private int id;
	private OffreFormationDto offreFormationDto;
	private String code;
	private Map<String,ParcoursTypeI18nDto> i18nDtos = new HashMap<String, ParcoursTypeI18nDto>();

	private Set<RepartitionUeDto> uniteEnseignementDtos = new HashSet<RepartitionUeDto>(0);
	
	
	public ParcoursTypeDto() {
	}

	/**
	 * [ParcoursTypeDto.getId] Method 
	 * @author  Rafik LAIB  on : 18 avr. 2014  19:42:19
	 * @return
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * [ParcoursTypeDto.setId] Method 
	 * @author  Rafik LAIB  on : 18 avr. 2014  19:42:23
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [ParcoursTypeDto.offreFormationDto] Getter 
	 * @author  Rafik LAIB on : 20 avr. 2014  09:32:13
	 * @return the offreFormationDto
	 */
	public OffreFormationDto getOffreFormationDto() {
		return offreFormationDto;
	}

	/**
	 * [ParcoursTypeDto.offreFormationDto] Setter 
	 * @author  Rafik LAIB on : 20 avr. 2014  09:32:13
	 * @param offreFormationDto the offreFormationDto to set
	 */
	public void setOffreFormationDto(OffreFormationDto offreFormationDto) {
		this.offreFormationDto = offreFormationDto;
	}

	/**
	 * [ParcoursTypeDto.code] Getter 
	 * @author  Rafik LAIB on : 20 avr. 2014  09:32:13
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [ParcoursTypeDto.code] Setter 
	 * @author  Rafik LAIB on : 20 avr. 2014  09:32:13
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [ParcoursTypeDto.i18nDtos] Getter 
	 * @author  Rafik LAIB on : 20 avr. 2014  09:32:13
	 * @return the i18nDtos
	 */
	public Map<String,ParcoursTypeI18nDto> getI18nDtos() {
		return i18nDtos;
	}

	/**
	 * [ParcoursTypeDto.i18nDtos] Setter 
	 * @author  Rafik LAIB on : 20 avr. 2014  09:32:13
	 * @param i18nDtos the i18nDtos to set
	 */
	public void setI18nDtos(Map<String,ParcoursTypeI18nDto> i18nDtos) {
		this.i18nDtos = i18nDtos;
	}

	/**
	 * [ParcoursTypeDto.uniteEnseignementDtos] Getter 
	 * @author  Rafik LAIB on : 20 avr. 2014  09:32:13
	 * @return the uniteEnseignementDtos
	 */
	public Set<RepartitionUeDto> getUniteEnseignementDtos() {
		return uniteEnseignementDtos;
	}

	/**
	 * [ParcoursTypeDto.uniteEnseignementDtos] Setter 
	 * @author  Rafik LAIB on : 20 avr. 2014  09:32:13
	 * @param uniteEnseignementDtos the uniteEnseignementDtos to set
	 */
	public void setUniteEnseignementDtos(
			Set<RepartitionUeDto> uniteEnseignementDtos) {
		this.uniteEnseignementDtos = uniteEnseignementDtos;
	}
	
	
	
}
