package dz.gov.mesrs.sii.grh.business.model.dto;

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
	private List<PosteSuperieureDto> posteSuperieureDtos;
	private List<GradeDto> gradeDtos;
	private List<CarriereDto> carriereDtos;
	private List<NomenclatureDto> nomenclatureDtos;

	private List<DetailRecrutementDto> detailRecrutementDtos;
	private List<CategorieNiveauDto> categorieToNiveauDtos;
	private List<GrilleIndiciaireDto> grilleIndiciaireDtos;

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

	public List<PosteSuperieureDto> getPosteSuperieureDtos() {
		return posteSuperieureDtos;
	}

	public void setPosteSuperieureDtos(List<PosteSuperieureDto> posteSuperieureDtos) {
		this.posteSuperieureDtos = posteSuperieureDtos;
	}

	public List<GradeDto> getGradeDtos() {
		return gradeDtos;
	}

	public void setGradeDtos(List<GradeDto> gradeDtos) {
		this.gradeDtos = gradeDtos;
	}

	public List<CarriereDto> getCarriereDtos() {
		return carriereDtos;
	}

	public void setCarriereDtos(List<CarriereDto> carriereDtos) {
		this.carriereDtos = carriereDtos;
	}

	public List<NomenclatureDto> getNomenclatureDtos() {
		return nomenclatureDtos;
	}

	public void setNomenclatureDtos(List<NomenclatureDto> nomenclatureDtos) {
		this.nomenclatureDtos = nomenclatureDtos;
	}

	public List<GrilleIndiciaireDto> getGrilleIndiciaireDtos() {
		if (grilleIndiciaireDtos == null) {
			grilleIndiciaireDtos = new ArrayList<>();
		}
		return grilleIndiciaireDtos;
	}

	public void setGrilleIndiciaireDtos(List<GrilleIndiciaireDto> grilleIndiciaireDtos) {
		this.grilleIndiciaireDtos = grilleIndiciaireDtos;
	}

	public void addGrilleIndiciaireDto(GrilleIndiciaireDto grilleIndiciaireDto) {
		this.getGrilleIndiciaireDtos().add(grilleIndiciaireDto);
		grilleIndiciaireDto.setCategorieProfessionnelleDto(this);
	}

	public List<DetailRecrutementDto> getDetailRecrutementDtos() {
		return detailRecrutementDtos;
	}

	public void setDetailRecrutementDtos(List<DetailRecrutementDto> detailRecrutementDtos) {
		this.detailRecrutementDtos = detailRecrutementDtos;
	}

	public List<CategorieNiveauDto> getCategorieNiveauDtos() {
		if (categorieToNiveauDtos == null) {
			categorieToNiveauDtos = new ArrayList<>();
		}
		return categorieToNiveauDtos;
	}

	public void setCategorieNiveauDtos(List<CategorieNiveauDto> categorieToNiveauDtos) {
		this.categorieToNiveauDtos = categorieToNiveauDtos;
	}

	public void addCategorieNiveauDto(CategorieNiveauDto categorieToNiveauDto) {
		this.getCategorieNiveauDtos().add(categorieToNiveauDto);
		categorieToNiveauDto.setCategorieProfessionnelleDto(this);

	}

	public void removeCategorieNiveauDto(CategorieNiveauDto categorieToNiveauDto) {
		this.getCategorieNiveauDtos().remove(categorieToNiveauDto);
	}

	@Override
	public String toString() {
		return "CategorieProfessionnelleDto [id=" + id + ", nomenclatureByGroupeDto=" + nomenclatureByGroupeDto
				+ ", nomenclatureByCategorieDto=" + nomenclatureByCategorieDto + ", nomenclatureByTypeCategorieDto="
				+ nomenclatureByTypeCategorieDto + ", indiceMin=" + indiceMin + ", horsCategorie=" + horsCategorie
				+ ", posteSuperieureDtos=" + posteSuperieureDtos + ", gradeDtos=" + gradeDtos + ", carriereDtos="
				+ carriereDtos + ", nomenclatureDtos=" + nomenclatureDtos + ", detailRecrutementDtos="
				+ detailRecrutementDtos + ", categorieToNiveauDtos=" + categorieToNiveauDtos
				+ ", grilleIndiciaireDtos=" + grilleIndiciaireDtos + "]";
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
		result = prime * result + ((nomenclatureDtos == null) ? 0 : nomenclatureDtos.hashCode());
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
		if (nomenclatureDtos == null) {
			if (other.nomenclatureDtos != null)
				return false;
		} else if (!nomenclatureDtos.equals(other.nomenclatureDtos))
			return false;
		return true;
	}

}
