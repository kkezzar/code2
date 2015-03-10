package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class ConnaissanceEmployeDto implements Serializable {
	private static final long serialVersionUID = 1003724685366599198L;

	private Long id;

	private NomenclatureDto typeDto;

	private DossierEmployeDto employeDto;

	private String description;

	public ConnaissanceEmployeDto() {
		super();
	}

	public ConnaissanceEmployeDto(Long id, NomenclatureDto typeDto, DossierEmployeDto employeDto, String description) {
		super();
		this.id = id;
		this.typeDto = typeDto;
		this.employeDto = employeDto;
		this.description = description;
	}

	public ConnaissanceEmployeDto(NomenclatureDto typeDto, DossierEmployeDto employeDto) {
		super();
		this.typeDto = typeDto;
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
	public String toString() {
		return "ConnaissanceEmployeDto [id=" + id + ", typeDto=" + typeDto + ", employeDto="
				+ employeDto.getMatricule() + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((employeDto == null) ? 0 : employeDto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ConnaissanceEmployeDto other = (ConnaissanceEmployeDto) obj;
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
		if (typeDto == null) {
			if (other.typeDto != null)
				return false;
		} else if (!typeDto.equals(other.typeDto))
			return false;
		return true;
	}

}
