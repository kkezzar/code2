package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class EvaluationDetailDto implements Serializable {
	private static final long serialVersionUID = 1632438275497576298L;
	private Long id;
	private NomenclatureDto critereDto;
	private EvaluationEmployeDto evaluationEmployeDto;
	private String appreciation;
	private NomenclatureDto niveauDto;

	public EvaluationDetailDto() {
		super();
	}
	
	

	public EvaluationDetailDto(NomenclatureDto critereDto, EvaluationEmployeDto evaluationEmployeDto,
			NomenclatureDto niveauDto) {
		super();
		this.critereDto = critereDto;
		this.evaluationEmployeDto = evaluationEmployeDto;
		this.niveauDto = niveauDto;
	}


	public EvaluationDetailDto(NomenclatureDto critereDto, NomenclatureDto niveauDto) {
		super();
		this.critereDto = critereDto;
		this.niveauDto = niveauDto;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NomenclatureDto getCritereDto() {
		return critereDto;
	}

	public void setCritereDto(NomenclatureDto critereDto) {
		this.critereDto = critereDto;
	}

	public EvaluationEmployeDto getEvaluationEmployeDto() {
		return evaluationEmployeDto;
	}

	public void setEvaluationEmployeDto(EvaluationEmployeDto evaluationEmployeDto) {
		this.evaluationEmployeDto = evaluationEmployeDto;
	}

	public String getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}
	
	public NomenclatureDto getNiveauDto() {
		return niveauDto;
	}
	
	public void setNiveauDto(NomenclatureDto niveauDto) {
		this.niveauDto = niveauDto;
	}

	@Override
	public String toString() {
		return "EvaluationDetailDto [id=" + id + ", critereDto=" + critereDto + ", evaluationEmployeDto="
				+ evaluationEmployeDto.getId() + ", appreciation=" + appreciation + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appreciation == null) ? 0 : appreciation.hashCode());
		result = prime * result + ((critereDto == null) ? 0 : critereDto.hashCode());
		result = prime * result + ((evaluationEmployeDto == null) ? 0 : evaluationEmployeDto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EvaluationDetailDto other = (EvaluationDetailDto) obj;
		if (appreciation == null) {
			if (other.appreciation != null)
				return false;
		} else if (!appreciation.equals(other.appreciation))
			return false;
		if (critereDto == null) {
			if (other.critereDto != null)
				return false;
		} else if (!critereDto.equals(other.critereDto))
			return false;
		if (evaluationEmployeDto == null) {
			if (other.evaluationEmployeDto != null)
				return false;
		} else if (!evaluationEmployeDto.equals(other.evaluationEmployeDto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
