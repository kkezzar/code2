package dz.gov.mesrs.sii.grh.business.model.dto;




/**
 * 
 * @author BELDI Jamel
 *
 */

public class GradeCycleFormationDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private GradeDto gradeDto;
	private CycleFormationDto cycleFormationDto;
	private Integer nbCandidat;
	private String observation;

	public GradeCycleFormationDto() {
	}

	public GradeCycleFormationDto(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GradeDto getGradeDto() {
		return gradeDto;
	}

	public void setGradeDto(GradeDto gradeDto) {
		this.gradeDto = gradeDto;
	}

	public CycleFormationDto getCycleFormationDto() {
		return cycleFormationDto;
	}

	public void setCycleFormationDto(CycleFormationDto cycleFormationDto) {
		this.cycleFormationDto = cycleFormationDto;
	}

	public Integer getNbCandidat() {
		return nbCandidat;
	}

	public void setNbCandidat(Integer nbCandidat) {
		this.nbCandidat = nbCandidat;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	

	

}
