package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class AbsenceDto implements Serializable {

	private static final long serialVersionUID = -4782647772870785675L;
	private Integer id;
	private NomenclatureDto nomenclatureDto;
	private DossierEmployeDto dossierEmployeDto;
	private Double nbreJours;
	private Date dateDebut;
	private Date dateFin;
	private Date heureDebut;
	private Date heureFin;
	private Boolean jusitifee;
	private Boolean renumeree;
	private SituationEntiteDto situationDto;

	public AbsenceDto() {
		super();
	}
	
	

	public AbsenceDto(NomenclatureDto nomenclatureDto) {
		super();
		this.nomenclatureDto = nomenclatureDto;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public Double getNbreJours() {
		return nbreJours;
	}

	public void setNbreJours(Double nbreJours) {
		this.nbreJours = nbreJours;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Date getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Date getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

	public Boolean getJusitifee() {
		return jusitifee;
	}

	public void setJusitifee(Boolean jusitifee) {
		this.jusitifee = jusitifee;
	}

	public Boolean getRenumeree() {
		return renumeree;
	}

	public void setRenumeree(Boolean renumeree) {
		this.renumeree = renumeree;
	}

	public SituationEntiteDto getSituationDto() {
		return situationDto;
	}

	public void setSituationDto(SituationEntiteDto situationDto) {
		this.situationDto = situationDto;
	}

	
	public NomenclatureDto getNomenclatureDto() {
		return nomenclatureDto;
	}

	public void setNomenclatureDto(NomenclatureDto nomenclatureDto) {
		this.nomenclatureDto = nomenclatureDto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		result = prime * result + ((dossierEmployeDto == null) ? 0 : dossierEmployeDto.hashCode());
		result = prime * result + ((heureDebut == null) ? 0 : heureDebut.hashCode());
		result = prime * result + ((heureFin == null) ? 0 : heureFin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jusitifee == null) ? 0 : jusitifee.hashCode());
		result = prime * result + ((nbreJours == null) ? 0 : nbreJours.hashCode());
		result = prime * result + ((nomenclatureDto == null) ? 0 : nomenclatureDto.hashCode());
		result = prime * result + ((renumeree == null) ? 0 : renumeree.hashCode());
		result = prime * result + ((situationDto == null) ? 0 : situationDto.hashCode());
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
		AbsenceDto other = (AbsenceDto) obj;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.equals(other.dateDebut))
			return false;
		if (dateFin == null) {
			if (other.dateFin != null)
				return false;
		} else if (!dateFin.equals(other.dateFin))
			return false;
		if (dossierEmployeDto == null) {
			if (other.dossierEmployeDto != null)
				return false;
		} else if (!dossierEmployeDto.equals(other.dossierEmployeDto))
			return false;
		if (heureDebut == null) {
			if (other.heureDebut != null)
				return false;
		} else if (!heureDebut.equals(other.heureDebut))
			return false;
		if (heureFin == null) {
			if (other.heureFin != null)
				return false;
		} else if (!heureFin.equals(other.heureFin))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (jusitifee == null) {
			if (other.jusitifee != null)
				return false;
		} else if (!jusitifee.equals(other.jusitifee))
			return false;
		if (nbreJours == null) {
			if (other.nbreJours != null)
				return false;
		} else if (!nbreJours.equals(other.nbreJours))
			return false;
		if (nomenclatureDto == null) {
			if (other.nomenclatureDto != null)
				return false;
		} else if (!nomenclatureDto.equals(other.nomenclatureDto))
			return false;
		if (renumeree == null) {
			if (other.renumeree != null)
				return false;
		} else if (!renumeree.equals(other.renumeree))
			return false;
		if (situationDto == null) {
			if (other.situationDto != null)
				return false;
		} else if (!situationDto.equals(other.situationDto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbsenceDto [id=" + id + ", nomenclature=" + nomenclatureDto + ", dossierEmploye="
				+ dossierEmployeDto.getMatricule() + ", nbreJours=" + nbreJours + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", jusitifee="
				+ jusitifee + ", renumeree=" + renumeree + ", situationDto=" + situationDto.getCodeSituation() + "]";
	}

}
