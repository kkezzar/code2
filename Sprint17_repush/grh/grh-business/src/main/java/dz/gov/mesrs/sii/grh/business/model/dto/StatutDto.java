package dz.gov.mesrs.sii.grh.business.model.dto;

import java.util.Date;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class StatutDto implements java.io.Serializable {

	private static final long serialVersionUID = 2849080564304140793L;
	private Integer id;
	private StatutDto statutParentDto;
	private Date dateEffet;
	private Date dateStatut;
	private Date datePublication;
	private Date dateFinValidation;
	private String intitule;
	private NomenclatureDto typeStatutDto;
	private String numero;
	private Integer annee;

	public StatutDto() {
		super();
	}

	public StatutDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatutDto getStatutParentDto() {
		return statutParentDto;
	}

	public void setStatutParentDto(StatutDto statutParent) {
		this.statutParentDto = statutParent;
	}

	public Date getDateEffet() {
		return dateEffet;
	}

	public void setDateEffet(Date dateEffet) {
		this.dateEffet = dateEffet;
	}

	public Date getDateStatut() {
		return dateStatut;
	}

	public void setDateStatut(Date dateStatut) {
		this.dateStatut = dateStatut;
	}

	public Date getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	public Date getDateFinValidation() {
		return dateFinValidation;
	}

	public void setDateFinValidation(Date dateFinValidation) {
		this.dateFinValidation = dateFinValidation;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public NomenclatureDto getTypeStatutDto() {
		return typeStatutDto;
	}

	public void setTypeStatutDto(NomenclatureDto typeStatutDto) {
		this.typeStatutDto = typeStatutDto;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((annee == null) ? 0 : annee.hashCode());
		result = prime * result + ((dateEffet == null) ? 0 : dateEffet.hashCode());
		result = prime * result + ((dateFinValidation == null) ? 0 : dateFinValidation.hashCode());
		result = prime * result + ((datePublication == null) ? 0 : datePublication.hashCode());
		result = prime * result + ((dateStatut == null) ? 0 : dateStatut.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((intitule == null) ? 0 : intitule.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((statutParentDto == null) ? 0 : statutParentDto.hashCode());
		result = prime * result + ((typeStatutDto == null) ? 0 : typeStatutDto.hashCode());
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
		StatutDto other = (StatutDto) obj;
		if (annee == null) {
			if (other.annee != null)
				return false;
		} else if (!annee.equals(other.annee))
			return false;
		if (dateEffet == null) {
			if (other.dateEffet != null)
				return false;
		} else if (!dateEffet.equals(other.dateEffet))
			return false;
		if (dateFinValidation == null) {
			if (other.dateFinValidation != null)
				return false;
		} else if (!dateFinValidation.equals(other.dateFinValidation))
			return false;
		if (datePublication == null) {
			if (other.datePublication != null)
				return false;
		} else if (!datePublication.equals(other.datePublication))
			return false;
		if (dateStatut == null) {
			if (other.dateStatut != null)
				return false;
		} else if (!dateStatut.equals(other.dateStatut))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (intitule == null) {
			if (other.intitule != null)
				return false;
		} else if (!intitule.equals(other.intitule))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (statutParentDto == null) {
			if (other.statutParentDto != null)
				return false;
		} else if (!statutParentDto.equals(other.statutParentDto))
			return false;
		if (typeStatutDto == null) {
			if (other.typeStatutDto != null)
				return false;
		} else if (!typeStatutDto.equals(other.typeStatutDto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatutDto [id=" + id + ", dateEffet=" + dateEffet + ", dateStatut=" + dateStatut + ", datePublication="
				+ datePublication + ", dateFinValidation=" + dateFinValidation + ", intitule=" + intitule
				+ ", typeStatutDto=" + typeStatutDto + ", numero=" + numero + ", annee=" + annee + "]";
	}

}
