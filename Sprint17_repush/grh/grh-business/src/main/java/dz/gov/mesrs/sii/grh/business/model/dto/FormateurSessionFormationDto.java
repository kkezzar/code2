package dz.gov.mesrs.sii.grh.business.model.dto;



import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

/**
 * 
 * @author BELDI Jamel
 *
 */

public class FormateurSessionFormationDto implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private RefIndividuDto refIndividuDto;
	private SessionFormationDto sessionFormationDto;

	public FormateurSessionFormationDto() {
	}

	public FormateurSessionFormationDto(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RefIndividuDto getRefIndividuDto() {
		return refIndividuDto;
	}

	public void setRefIndividuDto(RefIndividuDto refIndividuDto) {
		this.refIndividuDto = refIndividuDto;
	}

	public SessionFormationDto getSessionFormationDto() {
		return sessionFormationDto;
	}

	public void setSessionFormationDto(SessionFormationDto sessionFormationDto) {
		this.sessionFormationDto = sessionFormationDto;
	}

	

	
}
