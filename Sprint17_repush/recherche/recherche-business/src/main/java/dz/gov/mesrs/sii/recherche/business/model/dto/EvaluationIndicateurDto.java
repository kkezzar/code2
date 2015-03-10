package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class EvaluationIndicateurDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:54:51
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;	
	private NomenclatureDto typeAppreciationDto;
	private Double valeur;
	private String observation;
	private EvaluationProjetDto evaluationProjetDto;
	private IndEvaluationDto indEvaluationDto;
	
	public EvaluationIndicateurDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NomenclatureDto getTypeAppreciationDto() {
		return typeAppreciationDto;
	}

	public void setTypeAppreciationDto(NomenclatureDto typeAppreciationDto) {
		this.typeAppreciationDto = typeAppreciationDto;
	}

	public Double getValeur() {
		return valeur;
	}

	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public EvaluationProjetDto getEvaluationProjetDto() {
		return evaluationProjetDto;
	}

	public void setEvaluationProjetDto(EvaluationProjetDto evaluationProjetDto) {
		this.evaluationProjetDto = evaluationProjetDto;
	}

	public IndEvaluationDto getIndEvaluationDto() {
		return indEvaluationDto;
	}

	public void setIndEvaluationDto(IndEvaluationDto indEvaluationDto) {
		this.indEvaluationDto = indEvaluationDto;
	}

	
	
	
}
