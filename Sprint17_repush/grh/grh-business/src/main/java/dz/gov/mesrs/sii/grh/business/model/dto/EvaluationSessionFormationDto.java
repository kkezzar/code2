package dz.gov.mesrs.sii.grh.business.model.dto;



import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * 
 * @author BELDI Jamel
 *
 */

public class EvaluationSessionFormationDto implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private NomenclatureDto nomenclatureByChapitreDto;
	private NomenclatureDto nomenclatureByCritereDto;
	private NomenclatureDto nomenclatureByAppreciationDto;
	private SessionFormationDto sessionFormationDto;
	private String observation;

	public EvaluationSessionFormationDto() {
	}

	public EvaluationSessionFormationDto(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NomenclatureDto getNomenclatureByChapitreDto() {
		return nomenclatureByChapitreDto;
	}

	public void setNomenclatureByChapitreDto(
			NomenclatureDto nomenclatureByChapitreDto) {
		this.nomenclatureByChapitreDto = nomenclatureByChapitreDto;
	}

	public NomenclatureDto getNomenclatureByCritereDto() {
		return nomenclatureByCritereDto;
	}

	public void setNomenclatureByCritereDto(NomenclatureDto nomenclatureByCritereDto) {
		this.nomenclatureByCritereDto = nomenclatureByCritereDto;
	}

	public NomenclatureDto getNomenclatureByAppreciationDto() {
		return nomenclatureByAppreciationDto;
	}

	public void setNomenclatureByAppreciationDto(
			NomenclatureDto nomenclatureByAppreciationDto) {
		this.nomenclatureByAppreciationDto = nomenclatureByAppreciationDto;
	}

	public SessionFormationDto getSessionFormationDto() {
		return sessionFormationDto;
	}

	public void setSessionFormationDto(SessionFormationDto sessionFormationDto) {
		this.sessionFormationDto = sessionFormationDto;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	

	

}
