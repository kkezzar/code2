package dz.gov.mesrs.sii.grh.business.model.dto;



import java.util.Date;
import java.util.List;

/**
 * 
 * @author BELDI Jamel
 *
 */

public class ProgrammeFormationGrhDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String intitule;
	private String objectif;
	private Date dateDebut;
	private Date dateFin;
	private String code;
	private List<CycleFormationDto> cycleFormationDtos;

	public ProgrammeFormationGrhDto() {
	}

	public ProgrammeFormationGrhDto(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<CycleFormationDto> getCycleFormationDtos() {
		return cycleFormationDtos;
	}

	public void setCycleFormationDtos(List<CycleFormationDto> cycleFormationDtos) {
		this.cycleFormationDtos = cycleFormationDtos;
	}

	

	

}
