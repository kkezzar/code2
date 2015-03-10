package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class CategorieNiveauDto implements Serializable {

	private static final long serialVersionUID = -9161536698545573624L;

	private Long id;

	private NomenclatureDto nomenclatureDto;

	private CategorieProfessionnelleDto categorieProfessionnelleDto;

	public CategorieNiveauDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NomenclatureDto getNomenclatureDto() {
		return nomenclatureDto;
	}

	public void setNomenclatureDto(NomenclatureDto nomenclatureDto) {
		this.nomenclatureDto = nomenclatureDto;
	}

	public CategorieProfessionnelleDto getCategorieProfessionnelleDto() {
		return categorieProfessionnelleDto;
	}

	public void setCategorieProfessionnelleDto(CategorieProfessionnelleDto categorieProfessionnelleDto) {
		this.categorieProfessionnelleDto = categorieProfessionnelleDto;
	}

	@Override
	public String toString() {
		return "CategorieNiveauDto [id=" + id + ", nomenclatureDto=" + nomenclatureDto
				+ ", categorieProfessionnelleDto=" + categorieProfessionnelleDto.getId() + "]";
	}

}
