package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class HabiletePosteDto implements Serializable {

	private static final long serialVersionUID = 5673500204154697783L;

	private Long id;
	private String motsCles;
	private String description;
	private NomenclatureDto typeHabileteDto;
	private NomenclatureDto niveauHabileteDto;
	private PosteEmploiDto posteDto;

	public HabiletePosteDto() {
		super();
	}

	public HabiletePosteDto(NomenclatureDto typeHabileteDto, NomenclatureDto niveauHabileteDto,
			PosteEmploiDto posteEmploiDto) {
		super();
		this.typeHabileteDto = typeHabileteDto;
		this.niveauHabileteDto = niveauHabileteDto;
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

	public NomenclatureDto getTypeHabileteDto() {
		return typeHabileteDto;
	}

	public void setTypeHabileteDto(NomenclatureDto typeHabileteDto) {
		this.typeHabileteDto = typeHabileteDto;
	}

	public NomenclatureDto getNiveauHabileteDto() {
		return niveauHabileteDto;
	}

	public void setNiveauHabileteDto(NomenclatureDto niveauHabileteDto) {
		this.niveauHabileteDto = niveauHabileteDto;
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
		result = prime * result + ((niveauHabileteDto == null) ? 0 : niveauHabileteDto.hashCode());
		result = prime * result + ((typeHabileteDto == null) ? 0 : typeHabileteDto.hashCode());
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
		HabiletePosteDto other = (HabiletePosteDto) obj;
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
		if (niveauHabileteDto == null) {
			if (other.niveauHabileteDto != null)
				return false;
		} else if (!niveauHabileteDto.equals(other.niveauHabileteDto))
			return false;
		if (typeHabileteDto == null) {
			if (other.typeHabileteDto != null)
				return false;
		} else if (!typeHabileteDto.equals(other.typeHabileteDto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HabiletePosteDto [id=" + id + ", motsCles=" + motsCles + ", description=" + description
				+ ", typeHabileteDto=" + typeHabileteDto + ", niveauHabileteDto=" + niveauHabileteDto + ", posteDto="
				+ posteDto.getId() + "]";
	}

}
