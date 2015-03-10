package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class PosteSuperieureDto implements Serializable {

	private static final long serialVersionUID = 4127826926865781712L;
	private Integer id;
	private CategorieProfessionnelleDto categorieProfessionnelleDto;
	private NomenclatureDto typePosteSuperieurDto;
	private CorpsDto corpsDto;
	private Long bonificationIndiciaire;
	private Set<DetailRecrutementDto> detailRecrutements = new HashSet<DetailRecrutementDto>(0);

	public PosteSuperieureDto() {
	}

	public PosteSuperieureDto(Integer id) {
		this.id = id;
	}

	public PosteSuperieureDto(Integer id, CategorieProfessionnelleDto categorieProfessionnelleDto,
			NomenclatureDto typePosteSuperieurDto, CorpsDto corpsDto, Long bonificationIndiciaire,
			Set<DetailRecrutementDto> detailRecrutements) {
		super();
		this.id = id;
		this.categorieProfessionnelleDto = categorieProfessionnelleDto;
		this.typePosteSuperieurDto = typePosteSuperieurDto;
		this.corpsDto = corpsDto;
		this.bonificationIndiciaire = bonificationIndiciaire;
		this.detailRecrutements = detailRecrutements;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CategorieProfessionnelleDto getCategorieProfessionnelleDto() {
		return categorieProfessionnelleDto;
	}

	public void setCategorieProfessionnelleDto(CategorieProfessionnelleDto categorieProfessionnelleDto) {
		this.categorieProfessionnelleDto = categorieProfessionnelleDto;
	}

	public NomenclatureDto getTypePosteSuperieurDto() {
		return typePosteSuperieurDto;
	}

	public void setTypePosteSuperieurDto(NomenclatureDto typePosteSuperieurDto) {
		this.typePosteSuperieurDto = typePosteSuperieurDto;
	}

	public CorpsDto getCorpsDto() {
		return corpsDto;
	}

	public void setCorpsDto(CorpsDto corpsDto) {
		this.corpsDto = corpsDto;
	}

	public Long getBonificationIndiciaire() {
		return bonificationIndiciaire;
	}

	public void setBonificationIndiciaire(Long bonificationIndiciaire) {
		this.bonificationIndiciaire = bonificationIndiciaire;
	}

	public Set<DetailRecrutementDto> getDetailRecrutements() {
		return detailRecrutements;
	}

	public void setDetailRecrutements(Set<DetailRecrutementDto> detailRecrutements) {
		this.detailRecrutements = detailRecrutements;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonificationIndiciaire == null) ? 0 : bonificationIndiciaire.hashCode());
		result = prime * result + ((categorieProfessionnelleDto == null) ? 0 : categorieProfessionnelleDto.hashCode());
		result = prime * result + ((corpsDto == null) ? 0 : corpsDto.hashCode());
		result = prime * result + ((detailRecrutements == null) ? 0 : detailRecrutements.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((typePosteSuperieurDto == null) ? 0 : typePosteSuperieurDto.hashCode());
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
		PosteSuperieureDto other = (PosteSuperieureDto) obj;
		if (bonificationIndiciaire == null) {
			if (other.bonificationIndiciaire != null)
				return false;
		} else if (!bonificationIndiciaire.equals(other.bonificationIndiciaire))
			return false;
		if (categorieProfessionnelleDto == null) {
			if (other.categorieProfessionnelleDto != null)
				return false;
		} else if (!categorieProfessionnelleDto.equals(other.categorieProfessionnelleDto))
			return false;
		if (corpsDto == null) {
			if (other.corpsDto != null)
				return false;
		} else if (!corpsDto.equals(other.corpsDto))
			return false;
		if (detailRecrutements == null) {
			if (other.detailRecrutements != null)
				return false;
		} else if (!detailRecrutements.equals(other.detailRecrutements))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (typePosteSuperieurDto == null) {
			if (other.typePosteSuperieurDto != null)
				return false;
		} else if (!typePosteSuperieurDto.equals(other.typePosteSuperieurDto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PosteSuperieureDto [id=" + id + ", categorieProfessionnelleDto=" + categorieProfessionnelleDto
				+ ", typePosteSuperieurDto=" + typePosteSuperieurDto + ", corpsDto=" + corpsDto
				+ ", bonificationIndiciaire=" + bonificationIndiciaire + ", detailRecrutements=" + detailRecrutements
				+ "]";
	}

}
