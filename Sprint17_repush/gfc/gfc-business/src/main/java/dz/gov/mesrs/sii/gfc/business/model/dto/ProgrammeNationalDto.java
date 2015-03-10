package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.Date;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class ProgrammeNationalDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:33:48
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private NomenclatureDto type = new NomenclatureDto();
	private SituationEntiteDto situation = new SituationEntiteDto();
	private String code;
	private String intituleFr;
	private String intituleAr;
	private Short anneeDebut;
	private Short anneeFin;
	private Date dateCreation;
	private Date dateCloture;
	private String observation;

	public ProgrammeNationalDto() {
	}

	public ProgrammeNationalDto(Integer id, NomenclatureDto nomenclature, SituationEntiteDto situationEntite,
			String intituleFr, String intituleAr, Short anneeDebut, Short anneeFin) {
		this.id = id;
		this.type = nomenclature;
		this.situation = situationEntite;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.anneeDebut = anneeDebut;
		this.anneeFin = anneeFin;
	}

	public ProgrammeNationalDto(Integer id, NomenclatureDto nomenclature, SituationEntiteDto situationEntite,
			String code, String intituleFr, String intituleAr, Short anneeDebut, Short anneeFin, Date dateCreation,
			Date dateCloture, String observation) {
		this.id = id;
		this.type = nomenclature;
		this.situation = situationEntite;
		this.code = code;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.anneeDebut = anneeDebut;
		this.anneeFin = anneeFin;
		this.dateCreation = dateCreation;
		this.dateCloture = dateCloture;
		this.observation = observation;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NomenclatureDto getType() {
		return this.type;
	}

	public void setType(NomenclatureDto nomenclature) {
		this.type = nomenclature;
	}

	public SituationEntiteDto getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntiteDto situation) {
		this.situation = situation;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIntituleFr() {
		return this.intituleFr;
	}

	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	public String getIntituleAr() {
		return this.intituleAr;
	}

	public void setIntituleAr(String intituleAr) {
		this.intituleAr = intituleAr;
	}

	public Short getAnneeDebut() {
		return this.anneeDebut;
	}

	public void setAnneeDebut(Short anneeDebut) {
		this.anneeDebut = anneeDebut;
	}

	public Short getAnneeFin() {
		return this.anneeFin;
	}

	public void setAnneeFin(Short anneeFin) {
		this.anneeFin = anneeFin;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateCloture() {
		return this.dateCloture;
	}

	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((dateCloture == null) ? 0 : dateCloture.hashCode());
		result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result + ((anneeDebut == null) ? 0 : anneeDebut.hashCode());
		result = prime * result + ((anneeFin == null) ? 0 : anneeFin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((intituleAr == null) ? 0 : intituleAr.hashCode());
		result = prime * result + ((intituleFr == null) ? 0 : intituleFr.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((situation == null) ? 0 : situation.hashCode());
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
		ProgrammeNationalDto other = (ProgrammeNationalDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (dateCloture == null) {
			if (other.dateCloture != null)
				return false;
		} else if (!dateCloture.equals(other.dateCloture))
			return false;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (anneeDebut == null) {
			if (other.anneeDebut != null)
				return false;
		} else if (!anneeDebut.equals(other.anneeDebut))
			return false;
		if (anneeFin == null) {
			if (other.anneeFin != null)
				return false;
		} else if (!anneeFin.equals(other.anneeFin))
			return false;
		if (id != other.id)
			return false;
		if (intituleAr == null) {
			if (other.intituleAr != null)
				return false;
		} else if (!intituleAr.equals(other.intituleAr))
			return false;
		if (intituleFr == null) {
			if (other.intituleFr != null)
				return false;
		} else if (!intituleFr.equals(other.intituleFr))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (situation == null) {
			if (other.situation != null)
				return false;
		} else if (!situation.equals(other.situation))
			return false;
		return true;
	}

}
