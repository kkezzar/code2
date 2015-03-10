package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.Date;

public class AffectationPosteDto implements Serializable{

	private static final long serialVersionUID = 7252602442490804503L;
	
	private Long id;
	private PosteEmploiDto posteDto;
	private DossierEmployeDto dossierEmployeDto;
	private Date dateDebutAffectation;
	private Date dateFinAffectation;
	
	public AffectationPosteDto(){
		super();
	}

	public AffectationPosteDto(PosteEmploiDto posteDto, DossierEmployeDto dossierEmployeDto, Date dateDebutAffectation,
			Date dateFinAffectation) {
		super();
		this.posteDto = posteDto;
		this.dossierEmployeDto = dossierEmployeDto;
		this.dateDebutAffectation = dateDebutAffectation;
		this.dateFinAffectation = dateFinAffectation;
	}

	public AffectationPosteDto(PosteEmploiDto posteDto, DossierEmployeDto dossierEmployeDto, Date dateDebutAffectation) {
		super();
		this.posteDto = posteDto;
		this.dossierEmployeDto = dossierEmployeDto;
		this.dateDebutAffectation = dateDebutAffectation;
	}

	public Long getId() {
		return id;
	}

	public PosteEmploiDto getPosteDto() {
		return posteDto;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public Date getDateDebutAffectation() {
		return dateDebutAffectation;
	}

	public Date getDateFinAffectation() {
		return dateFinAffectation;
	}
	

	public void setId(Long id) {
		this.id = id;
	}

	public void setPosteDto(PosteEmploiDto posteDto) {
		this.posteDto = posteDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public void setDateDebutAffectation(Date dateDebutAffectation) {
		this.dateDebutAffectation = dateDebutAffectation;
	}

	public void setDateFinAffectation(Date dateFinAffectation) {
		this.dateFinAffectation = dateFinAffectation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebutAffectation == null) ? 0 : dateDebutAffectation.hashCode());
		result = prime * result + ((dateFinAffectation == null) ? 0 : dateFinAffectation.hashCode());
		result = prime * result + ((dossierEmployeDto == null) ? 0 : dossierEmployeDto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((posteDto == null) ? 0 : posteDto.hashCode());
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
		AffectationPosteDto other = (AffectationPosteDto) obj;
		if (dateDebutAffectation == null) {
			if (other.dateDebutAffectation != null)
				return false;
		} else if (!dateDebutAffectation.equals(other.dateDebutAffectation))
			return false;
		if (dateFinAffectation == null) {
			if (other.dateFinAffectation != null)
				return false;
		} else if (!dateFinAffectation.equals(other.dateFinAffectation))
			return false;
		if (dossierEmployeDto == null) {
			if (other.dossierEmployeDto != null)
				return false;
		} else if (!dossierEmployeDto.equals(other.dossierEmployeDto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (posteDto == null) {
			if (other.posteDto != null)
				return false;
		} else if (!posteDto.equals(other.posteDto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AffectationPosteDto [id=" + id + ", posteDto=" + posteDto.getCode() + ", dossierEmployeDto=" + dossierEmployeDto.getMatricule()
				+ ", dateDebutAffectation=" + dateDebutAffectation + ", dateFinAffectation=" + dateFinAffectation + "]";
	}
	
	
	
	

}
