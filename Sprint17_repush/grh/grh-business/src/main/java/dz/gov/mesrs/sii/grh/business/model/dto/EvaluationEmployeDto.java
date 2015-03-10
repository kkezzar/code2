package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EvaluationEmployeDto implements Serializable {
	private static final long serialVersionUID = -3526321204651005895L;
	private Long id;
	private String appreciation;
	private Double note;
	private Date dateContestation;
	private Date dateCommission;
	private Double noteRevise;
	private String objetContestation;
	private Boolean resultat;
	private String motif;
	private EvaluationPeriodeDto periodeDto;
	private DossierEmployeDto employeDto;
	private List<EvaluationDetailDto> detailDtos;

	public EvaluationEmployeDto() {
		super();
	}
	
	

	public EvaluationEmployeDto(EvaluationPeriodeDto periodeDto) {
		super();
		this.periodeDto = periodeDto;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}

	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
	}

	public Date getDateContestation() {
		return dateContestation;
	}

	public void setDateContestation(Date dateContestation) {
		this.dateContestation = dateContestation;
	}

	public Date getDateCommission() {
		return dateCommission;
	}

	public void setDateCommission(Date dateCommission) {
		this.dateCommission = dateCommission;
	}

	public Double getNoteRevise() {
		return noteRevise;
	}

	public void setNoteRevise(Double noteRevise) {
		this.noteRevise = noteRevise;
	}

	public String getObjetContestation() {
		return objetContestation;
	}

	public void setObjetContestation(String objetContestation) {
		this.objetContestation = objetContestation;
	}

	public Boolean getResultat() {
		return resultat;
	}

	public void setResultat(Boolean resultat) {
		this.resultat = resultat;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public EvaluationPeriodeDto getPeriodeDto() {
		return periodeDto;
	}

	public void setPeriodeDto(EvaluationPeriodeDto periodeDto) {
		this.periodeDto = periodeDto;
	}

	public DossierEmployeDto getEmployeDto() {
		return employeDto;
	}

	public void setEmployeDto(DossierEmployeDto employeDto) {
		this.employeDto = employeDto;
	}

	public List<EvaluationDetailDto> getDetailDtos() {
		if (detailDtos == null) {
			detailDtos = new ArrayList<>();
		}
		return detailDtos;
	}

	public void setDetailDtos(List<EvaluationDetailDto> detailDtos) {
		this.detailDtos = detailDtos;
	}

	public void addEvaluationDetailDto(EvaluationDetailDto dto) {
		this.getDetailDtos().add(dto);
		dto.setEvaluationEmployeDto(this);
	}

	public void removeEvaluationDetailDto(EvaluationDetailDto dto) {
		this.getDetailDtos().remove(dto);
		dto.setEvaluationEmployeDto(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appreciation == null) ? 0 : appreciation.hashCode());
		result = prime * result + ((dateCommission == null) ? 0 : dateCommission.hashCode());
		result = prime * result + ((dateContestation == null) ? 0 : dateContestation.hashCode());
		result = prime * result + ((employeDto == null) ? 0 : employeDto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motif == null) ? 0 : motif.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((noteRevise == null) ? 0 : noteRevise.hashCode());
		result = prime * result + ((objetContestation == null) ? 0 : objetContestation.hashCode());
		result = prime * result + ((periodeDto == null) ? 0 : periodeDto.hashCode());
		result = prime * result + ((resultat == null) ? 0 : resultat.hashCode());
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
		EvaluationEmployeDto other = (EvaluationEmployeDto) obj;
		if (appreciation == null) {
			if (other.appreciation != null)
				return false;
		} else if (!appreciation.equals(other.appreciation))
			return false;
		if (dateCommission == null) {
			if (other.dateCommission != null)
				return false;
		} else if (!dateCommission.equals(other.dateCommission))
			return false;
		if (dateContestation == null) {
			if (other.dateContestation != null)
				return false;
		} else if (!dateContestation.equals(other.dateContestation))
			return false;
		if (employeDto == null) {
			if (other.employeDto != null)
				return false;
		} else if (!employeDto.equals(other.employeDto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motif == null) {
			if (other.motif != null)
				return false;
		} else if (!motif.equals(other.motif))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (noteRevise == null) {
			if (other.noteRevise != null)
				return false;
		} else if (!noteRevise.equals(other.noteRevise))
			return false;
		if (objetContestation == null) {
			if (other.objetContestation != null)
				return false;
		} else if (!objetContestation.equals(other.objetContestation))
			return false;
		if (periodeDto == null) {
			if (other.periodeDto != null)
				return false;
		} else if (!periodeDto.equals(other.periodeDto))
			return false;
		if (resultat == null) {
			if (other.resultat != null)
				return false;
		} else if (!resultat.equals(other.resultat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EvaluationEmployeDto [id=" + id + ", appreciation=" + appreciation + ", note=" + note
				+ ", dateContestation=" + dateContestation + ", dateCommission=" + dateCommission + ", noteRevise="
				+ noteRevise + ", objetContestation=" + objetContestation + ", resultat=" + resultat + ", motif="
				+ motif + ", periodeDto=" + periodeDto + ", employeDto=" + employeDto.getMatricule() + ", detailDtos="
				+ detailDtos + "]";
	}

}
