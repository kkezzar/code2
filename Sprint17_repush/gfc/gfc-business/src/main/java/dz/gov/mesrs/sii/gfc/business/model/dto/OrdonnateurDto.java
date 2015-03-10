package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class OrdonnateurDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:33:26
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private RefIndividuDto refIndividu = new RefIndividuDto();
	private NomenclatureDto type = new NomenclatureDto();
	private String code;
	private String decisionNomination;
	private Date dateNomination;
	private Date dateFinNomination;

	private RefEtablissementDto etablissement = new RefEtablissementDto();
	private RefStructureDto structure = new RefStructureDto();

	// private List<RegieDto> regies = new ArrayList<RegieDto>(0);

	public OrdonnateurDto() {
	}

	public OrdonnateurDto(Integer id, RefIndividuDto refIndividu, NomenclatureDto type) {
		this.id = id;
		this.refIndividu = refIndividu;
		this.type = type;
	}

	public OrdonnateurDto(Integer id, RefIndividuDto refIndividu, NomenclatureDto type, String code,
			String decisionNomination, Date dateNomination, Date dateFinNomination, List<RegieDto> regies) {
		this.id = id;
		this.refIndividu = refIndividu;
		this.type = type;
		this.code = code;
		this.decisionNomination = decisionNomination;
		this.dateNomination = dateNomination;
		this.dateFinNomination = dateFinNomination;
		// this.regies = regies;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RefIndividuDto getRefIndividu() {
		return this.refIndividu;
	}

	public void setRefIndividu(RefIndividuDto refIndividu) {
		this.refIndividu = refIndividu;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	// public List<RegieDto> getRegies() {
	// return this.regies;
	// }
	//
	// public void setRegies(List<RegieDto> regies) {
	// this.regies = regies;
	// }

	public String getDecisionNomination() {
		return decisionNomination;
	}

	public void setDecisionNomination(String decisionNomination) {
		this.decisionNomination = decisionNomination;
	}

	public Date getDateNomination() {
		return dateNomination;
	}

	public void setDateNomination(Date dateNomination) {
		this.dateNomination = dateNomination;
	}

	public Date getDateFinNomination() {
		return dateFinNomination;
	}

	public void setDateFinNomination(Date dateFinNomination) {
		this.dateFinNomination = dateFinNomination;
	}

	public RefEtablissementDto getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(RefEtablissementDto etablissement) {
		this.etablissement = etablissement;
	}

	public RefStructureDto getStructure() {
		return structure;
	}

	public void setStructure(RefStructureDto structure) {
		this.structure = structure;
	}

	public NomenclatureDto getType() {
		return type;
	}

	public void setType(NomenclatureDto type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((dateFinNomination == null) ? 0 : dateFinNomination.hashCode());
		result = prime * result + ((dateNomination == null) ? 0 : dateNomination.hashCode());
		result = prime * result + ((decisionNomination == null) ? 0 : decisionNomination.hashCode());
		result = prime * result + ((etablissement == null) ? 0 : etablissement.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((refIndividu == null) ? 0 : refIndividu.hashCode());
		result = prime * result + ((structure == null) ? 0 : structure.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		OrdonnateurDto other = (OrdonnateurDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (dateFinNomination == null) {
			if (other.dateFinNomination != null)
				return false;
		} else if (!dateFinNomination.equals(other.dateFinNomination))
			return false;
		if (dateNomination == null) {
			if (other.dateNomination != null)
				return false;
		} else if (!dateNomination.equals(other.dateNomination))
			return false;
		if (decisionNomination == null) {
			if (other.decisionNomination != null)
				return false;
		} else if (!decisionNomination.equals(other.decisionNomination))
			return false;
		if (etablissement == null) {
			if (other.etablissement != null)
				return false;
		} else if (!etablissement.equals(other.etablissement))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (refIndividu == null) {
			if (other.refIndividu != null)
				return false;
		} else if (!refIndividu.equals(other.refIndividu))
			return false;
		if (structure == null) {
			if (other.structure != null)
				return false;
		} else if (!structure.equals(other.structure))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
