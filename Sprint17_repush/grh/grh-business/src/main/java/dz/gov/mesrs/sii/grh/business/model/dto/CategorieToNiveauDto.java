package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class CategorieToNiveauDto implements Serializable {

	private static final long serialVersionUID = 3679015016102552515L;

	private CategorieProfessionnelleDto categorieProfessionnelleDto;

	private NomenclatureDto niveauDto;

	public CategorieToNiveauDto() {
	}

	public CategorieToNiveauDto(CategorieProfessionnelleDto categorieProfessionnelleDto, NomenclatureDto niveauDto) {
		super();
		this.categorieProfessionnelleDto = categorieProfessionnelleDto;
		this.niveauDto = niveauDto;
	}

	public CategorieProfessionnelleDto getCategorieProfessionnelleDto() {
		return categorieProfessionnelleDto;
	}

	public void setCategorieProfessionnelleDto(CategorieProfessionnelleDto categorieProfessionnelleDto) {
		this.categorieProfessionnelleDto = categorieProfessionnelleDto;
	}

	public NomenclatureDto getNiveauDto() {
		return niveauDto;
	}

	public void setNiveauDto(NomenclatureDto niveauDto) {
		this.niveauDto = niveauDto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categorieProfessionnelleDto == null) ? 0 : categorieProfessionnelleDto.hashCode());
		result = prime * result + ((niveauDto == null) ? 0 : niveauDto.hashCode());
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
		CategorieToNiveauDto other = (CategorieToNiveauDto) obj;
		if (categorieProfessionnelleDto == null) {
			if (other.categorieProfessionnelleDto != null)
				return false;
		} else if (!categorieProfessionnelleDto.equals(other.categorieProfessionnelleDto))
			return false;
		if (niveauDto == null) {
			if (other.niveauDto != null)
				return false;
		} else if (!niveauDto.equals(other.niveauDto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategorieToNiveauDto [categorieProfessionnelleDto=" + categorieProfessionnelleDto + ", niveauDto="
				+ niveauDto + "]";
	}

}
