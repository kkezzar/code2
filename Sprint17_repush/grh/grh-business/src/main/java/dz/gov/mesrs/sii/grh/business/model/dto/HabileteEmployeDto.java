package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class HabileteEmployeDto implements Serializable {

	private static final long serialVersionUID = 8314281766636877189L;

	private Long id;

	private NomenclatureDto typeDto;

	private NomenclatureDto niveauDto;

	private DossierEmployeDto employeDto;

	private String description;

	public HabileteEmployeDto() {
		super();
	}

	public HabileteEmployeDto(Long id, NomenclatureDto typeDto, NomenclatureDto niveauDto,
			DossierEmployeDto employeDto, String description) {
		super();
		this.id = id;
		this.typeDto = typeDto;
		this.niveauDto = niveauDto;
		this.employeDto = employeDto;
		this.description = description;
	}
	
	

	public HabileteEmployeDto(NomenclatureDto typeDto, NomenclatureDto niveauDto, DossierEmployeDto employeDto) {
		super();
		this.typeDto = typeDto;
		this.niveauDto = niveauDto;
		this.employeDto = employeDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NomenclatureDto getTypeDto() {
		return typeDto;
	}

	public void setTypeDto(NomenclatureDto typeDto) {
		this.typeDto = typeDto;
	}

	public NomenclatureDto getNiveauDto() {
		return niveauDto;
	}

	public void setNiveauDto(NomenclatureDto niveauDto) {
		this.niveauDto = niveauDto;
	}

	public DossierEmployeDto getEmployeDto() {
		return employeDto;
	}

	public void setEmployeDto(DossierEmployeDto employeDto) {
		this.employeDto = employeDto;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((employeDto == null) ? 0 : employeDto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((niveauDto == null) ? 0 : niveauDto.hashCode());
		result = prime * result + ((typeDto == null) ? 0 : typeDto.hashCode());
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
		HabileteEmployeDto other = (HabileteEmployeDto) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		if (niveauDto == null) {
			if (other.niveauDto != null)
				return false;
		} else if (!niveauDto.equals(other.niveauDto))
			return false;
		if (typeDto == null) {
			if (other.typeDto != null)
				return false;
		} else if (!typeDto.equals(other.typeDto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HabieleteEmployeDto [id=" + id + ", typeDto=" + typeDto + ", niveauDto=" + niveauDto + ", employeDto="
				+ employeDto.getMatricule() + ", description=" + description + "]";
	}

}
