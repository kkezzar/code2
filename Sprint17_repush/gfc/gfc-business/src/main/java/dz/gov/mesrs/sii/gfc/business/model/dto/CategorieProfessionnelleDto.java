package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class CategorieProfessionnelleDto implements Serializable {

	private static final long serialVersionUID = 508577750758257188L;

	private Integer id;
	private NomenclatureDto nomenclatureByGroupeDto;
	private NomenclatureDto nomenclatureByCategorieDto;
	private NomenclatureDto nomenclatureByTypeCategorieDto;
	private Long indiceMin;
	private Boolean horsCategorie;
	
	public CategorieProfessionnelleDto() {
		super();
	}

	public CategorieProfessionnelleDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NomenclatureDto getNomenclatureByGroupeDto() {
		return nomenclatureByGroupeDto;
	}

	public void setNomenclatureByGroupeDto(NomenclatureDto nomenclatureByGroupeDto) {
		this.nomenclatureByGroupeDto = nomenclatureByGroupeDto;
	}

	public NomenclatureDto getNomenclatureByCategorieDto() {
		return nomenclatureByCategorieDto;
	}

	public void setNomenclatureByCategorieDto(NomenclatureDto nomenclatureByCategorieDto) {
		this.nomenclatureByCategorieDto = nomenclatureByCategorieDto;
	}

	public NomenclatureDto getNomenclatureByTypeCategorieDto() {
		return nomenclatureByTypeCategorieDto;
	}

	public void setNomenclatureByTypeCategorieDto(NomenclatureDto nomenclatureByTypeCategorieDto) {
		this.nomenclatureByTypeCategorieDto = nomenclatureByTypeCategorieDto;
	}

	public Long getIndiceMin() {
		return indiceMin;
	}

	public void setIndiceMin(Long indiceMin) {
		this.indiceMin = indiceMin;
	}

	public Boolean getHorsCategorie() {
		return horsCategorie;
	}

	public void setHorsCategorie(Boolean horsCategorie) {
		this.horsCategorie = horsCategorie;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((horsCategorie == null) ? 0 : horsCategorie.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((indiceMin == null) ? 0 : indiceMin.hashCode());
		result = prime * result + ((nomenclatureByCategorieDto == null) ? 0 : nomenclatureByCategorieDto.hashCode());
		result = prime * result + ((nomenclatureByGroupeDto == null) ? 0 : nomenclatureByGroupeDto.hashCode());
		result = prime * result
				+ ((nomenclatureByTypeCategorieDto == null) ? 0 : nomenclatureByTypeCategorieDto.hashCode());
		
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
		CategorieProfessionnelleDto other = (CategorieProfessionnelleDto) obj;
		if (horsCategorie == null) {
			if (other.horsCategorie != null)
				return false;
		} else if (!horsCategorie.equals(other.horsCategorie))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (indiceMin == null) {
			if (other.indiceMin != null)
				return false;
		} else if (!indiceMin.equals(other.indiceMin))
			return false;
		if (nomenclatureByCategorieDto == null) {
			if (other.nomenclatureByCategorieDto != null)
				return false;
		} else if (!nomenclatureByCategorieDto.equals(other.nomenclatureByCategorieDto))
			return false;
		if (nomenclatureByGroupeDto == null) {
			if (other.nomenclatureByGroupeDto != null)
				return false;
		} else if (!nomenclatureByGroupeDto.equals(other.nomenclatureByGroupeDto))
			return false;
		if (nomenclatureByTypeCategorieDto == null) {
			if (other.nomenclatureByTypeCategorieDto != null)
				return false;
		} else if (!nomenclatureByTypeCategorieDto.equals(other.nomenclatureByTypeCategorieDto))
			return false;
		
		return true;
	}

}
