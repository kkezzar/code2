package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class ConnaissancePosteDto implements Serializable {

	private static final long serialVersionUID = -4748579541089644983L;

	private Long id;
	private String motsCles;
	private String description;
	private NomenclatureDto typeConnaissanceDto;
	private PosteEmploiDto posteDto;

	public ConnaissancePosteDto() {
		super();
	}

	public ConnaissancePosteDto(NomenclatureDto typeConnaissanceDto, PosteEmploiDto posteEmploiDto) {
		super();
		this.typeConnaissanceDto = typeConnaissanceDto;
		this.posteDto = posteEmploiDto;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMotsCles() {
		return motsCles;
	}

	public void setMotsCles(String motsCles) {
		this.motsCles = motsCles;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public NomenclatureDto getTypeConnaissanceDto() {
		return typeConnaissanceDto;
	}

	public void setTypeConnaissanceDto(NomenclatureDto typeConnaissanceDto) {
		this.typeConnaissanceDto = typeConnaissanceDto;
	}

	public PosteEmploiDto getPosteDto() {
		return posteDto;
	}

	public void setPosteDto(PosteEmploiDto posteDto) {
		this.posteDto = posteDto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motsCles == null) ? 0 : motsCles.hashCode());
		result = prime * result + ((typeConnaissanceDto == null) ? 0 : typeConnaissanceDto.hashCode());
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
		ConnaissancePosteDto other = (ConnaissancePosteDto) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motsCles == null) {
			if (other.motsCles != null)
				return false;
		} else if (!motsCles.equals(other.motsCles))
			return false;
		if (typeConnaissanceDto == null) {
			if (other.typeConnaissanceDto != null)
				return false;
		} else if (!typeConnaissanceDto.equals(other.typeConnaissanceDto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConnaissancePosteDto [id=" + id + ", motsCles=" + motsCles + ", description=" + description
				+ ", typeConnaissanceDto=" + typeConnaissanceDto + ", posteDto=" + posteDto.getId() + "]";
	}

}
