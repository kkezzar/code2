package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class GroupeDto implements Serializable {

	private static final long serialVersionUID = 1542683560956019847L;

	private NomenclatureDto nomenclatureGoupe;
	// Les catégories professionnelles qui sont liées au groupe
	private List<CategorieProfessionnelleDto> categorieProfessionnelleDtos;
	// Les catégories professionnelles qui sont liées au groupe, et qui sont
	// classées hors catégorie
	private List<CategorieProfessionnelleDto> horsCategorieProfessionnelleDtos;

	public GroupeDto() {
		super();
	}

	public GroupeDto(NomenclatureDto nomenclatureGoupe, List<CategorieProfessionnelleDto> categorieProfessionnelleDtos) {
		super();
		this.nomenclatureGoupe = nomenclatureGoupe;
		this.categorieProfessionnelleDtos = categorieProfessionnelleDtos;
	}

	public GroupeDto(NomenclatureDto nomenclatureGroupDto) {
		super();
		this.nomenclatureGoupe = nomenclatureGroupDto;
	}

	public NomenclatureDto getNomenclatureGoupe() {
		return nomenclatureGoupe;
	}

	public void setNomenclatureGoupe(NomenclatureDto nomenclatureGoupe) {
		this.nomenclatureGoupe = nomenclatureGoupe;
	}

	public List<CategorieProfessionnelleDto> getCategorieProfessionnelleDtos() {
		if (categorieProfessionnelleDtos == null) {
			categorieProfessionnelleDtos = new ArrayList<>();
		}
		return categorieProfessionnelleDtos;
	}

	public void setCategorieProfessionnelleDtos(List<CategorieProfessionnelleDto> categorieProfessionnelleDtos) {
		this.categorieProfessionnelleDtos = categorieProfessionnelleDtos;
	}

	public List<CategorieProfessionnelleDto> getHorsCategorieProfessionnelleDtos() {
		if(horsCategorieProfessionnelleDtos == null){
			horsCategorieProfessionnelleDtos = new ArrayList<>();
		}
		return horsCategorieProfessionnelleDtos;
	}

	public void setHorsCategorieProfessionnelleDtos(List<CategorieProfessionnelleDto> horsCategorieProfessionnelleDtos) {
		this.horsCategorieProfessionnelleDtos = horsCategorieProfessionnelleDtos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomenclatureGoupe == null) ? 0 : nomenclatureGoupe.hashCode());
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
		GroupeDto other = (GroupeDto) obj;
		if (nomenclatureGoupe == null) {
			if (other.nomenclatureGoupe != null)
				return false;
		} else if (!nomenclatureGoupe.equals(other.nomenclatureGoupe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GroupeDto [nomenclatureGoupe=" + nomenclatureGoupe + ", categorieProfessionnelleDtos="
				+ categorieProfessionnelleDtos + ", horsCategorieProfessionnelleDtos="
				+ horsCategorieProfessionnelleDtos + "]";
	}

}
