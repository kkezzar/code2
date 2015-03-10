package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

public class EvaluationPeriodeDto implements Serializable {

	private static final long serialVersionUID = 5109441589825066761L;
	private Long id;
	private Date dateEvaluation;
	private Date dateDebutPeriode;
	private Date dateFinPeriode;
	private List<EvaluationEmployeDto> evaluationEmployeDtos;
	private RefEtablissementDto etablissementDto;

	public EvaluationPeriodeDto() {
		super();
	}

	public EvaluationPeriodeDto(int etablissementId) {
		super();
		RefEtablissementDto etablissementDto = new RefEtablissementDto();
		etablissementDto.setId(etablissementId);
		this.setEtablissementDto(etablissementDto);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateEvaluation() {
		return dateEvaluation;
	}

	public void setDateEvaluation(Date dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	public Date getDateDebutPeriode() {
		return dateDebutPeriode;
	}

	public void setDateDebutPeriode(Date dateDebutPeriode) {
		this.dateDebutPeriode = dateDebutPeriode;
	}

	public Date getDateFinPeriode() {
		return dateFinPeriode;
	}

	public void setDateFinPeriode(Date dateFinPeriode) {
		this.dateFinPeriode = dateFinPeriode;
	}

	public List<EvaluationEmployeDto> getEvaluationEmployeDtos() {
		if (evaluationEmployeDtos == null) {
			evaluationEmployeDtos = new ArrayList<>();
		}
		return evaluationEmployeDtos;
	}

	public void setEvaluationEmployeDtos(List<EvaluationEmployeDto> evaluationEmployeDtos) {
		this.evaluationEmployeDtos = evaluationEmployeDtos;
	}

	public void addEvaluationEmployeDto(EvaluationEmployeDto dto) {
		this.getEvaluationEmployeDtos().add(dto);
		dto.setPeriodeDto(this);
	}

	public void removeEvaluationEmployeDto(EvaluationEmployeDto dto) {
		this.getEvaluationEmployeDtos().remove(dto);
		dto.setPeriodeDto(null);
	}

	public RefEtablissementDto getEtablissementDto() {
		return etablissementDto;
	}

	public void setEtablissementDto(RefEtablissementDto etablissementDto) {
		this.etablissementDto = etablissementDto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebutPeriode == null) ? 0 : dateDebutPeriode.hashCode());
		result = prime * result + ((dateEvaluation == null) ? 0 : dateEvaluation.hashCode());
		result = prime * result + ((dateFinPeriode == null) ? 0 : dateFinPeriode.hashCode());
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
		EvaluationPeriodeDto other = (EvaluationPeriodeDto) obj;
		if (dateDebutPeriode == null) {
			if (other.dateDebutPeriode != null)
				return false;
		} else if (!dateDebutPeriode.equals(other.dateDebutPeriode))
			return false;
		if (dateEvaluation == null) {
			if (other.dateEvaluation != null)
				return false;
		} else if (!dateEvaluation.equals(other.dateEvaluation))
			return false;
		if (dateFinPeriode == null) {
			if (other.dateFinPeriode != null)
				return false;
		} else if (!dateFinPeriode.equals(other.dateFinPeriode))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EvaluationPeriodeDto [id=" + id + ", dateEvaluation=" + dateEvaluation + ", dateDebutPeriode="
				+ dateDebutPeriode + ", dateFinPeriode=" + dateFinPeriode + "]";
	}

}
