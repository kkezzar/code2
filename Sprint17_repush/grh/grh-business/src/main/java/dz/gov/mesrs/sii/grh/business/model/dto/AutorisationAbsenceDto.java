package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class AutorisationAbsenceDto implements Serializable {

	private static final long serialVersionUID = -955087356484595565L;
	private Integer id;
	private NomenclatureDto nomenclatureDto;
	private DossierEmployeDto dossierEmployeDto;
	private String objet;
	private Date dateDebut;
	private Date dateFin;
	private Date heureDebut;
	private Date heureFin;
	private Boolean dimanche;
	private Boolean lundi;
	private Boolean mardi;
	private Boolean mercredi;
	private Boolean jeudi;
	private Boolean vendredi;
	private Boolean samedi;
	private Date dateResultat;
	private Boolean acceptee;
	private String motifRefus;
	private String observation;
	private SituationEntiteDto situationDto;
	private Date dateDemande;

	public AutorisationAbsenceDto() {
		super();
	}
	
	

	public AutorisationAbsenceDto(NomenclatureDto frequence) {
		super();
		this.nomenclatureDto = frequence;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NomenclatureDto getNomenclatureDto() {
		return nomenclatureDto;
	}

	public void setNomenclatureDto(NomenclatureDto nomenclatureDto) {
		this.nomenclatureDto = nomenclatureDto;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
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

	public Boolean getDimanche() {
		return dimanche;
	}

	public void setDimanche(Boolean dimanche) {
		this.dimanche = dimanche;
	}

	public Boolean getLundi() {
		return lundi;
	}

	public void setLundi(Boolean lundi) {
		this.lundi = lundi;
	}

	public Boolean getMardi() {
		return mardi;
	}

	public void setMardi(Boolean mardi) {
		this.mardi = mardi;
	}

	public Boolean getMercredi() {
		return mercredi;
	}

	public void setMercredi(Boolean mercredi) {
		this.mercredi = mercredi;
	}

	public Boolean getJeudi() {
		return jeudi;
	}

	public void setJeudi(Boolean jeudi) {
		this.jeudi = jeudi;
	}

	public Boolean getVendredi() {
		return vendredi;
	}

	public void setVendredi(Boolean vendredi) {
		this.vendredi = vendredi;
	}

	public Boolean getSamedi() {
		return samedi;
	}

	public void setSamedi(Boolean samedi) {
		this.samedi = samedi;
	}

	public Date getDateResultat() {
		return dateResultat;
	}

	public void setDateResultat(Date dateResultat) {
		this.dateResultat = dateResultat;
	}

	public Boolean getAcceptee() {
		return acceptee;
	}

	public void setAcceptee(Boolean acceptee) {
		this.acceptee = acceptee;
	}

	public String getMotifRefus() {
		return motifRefus;
	}

	public void setMotifRefus(String motifRefus) {
		this.motifRefus = motifRefus;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public void setSituationDto(SituationEntiteDto situationDto) {
		this.situationDto = situationDto;
	}

	public SituationEntiteDto getSituationDto() {
		return situationDto;
	}

	public Date getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	@Override
	public String toString() {
		return "AutorisationAbsenceDto [id=" + id + ", nomenclature=" + nomenclatureDto + ", dossierEmploye="
				+ dossierEmployeDto.getMatricule() + ", objet=" + objet + ", dateDebut=" + dateDebut + ", dateFin="
				+ dateFin + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", dimanche=" + dimanche
				+ ", lundi=" + lundi + ", mardi=" + mardi + ", mercredi=" + mercredi + ", jeudi=" + jeudi
				+ ", vendredi=" + vendredi + ", samedi=" + samedi + ", dateResultat=" + dateResultat + ", acceptee="
				+ acceptee + ", motifRefus=" + motifRefus + ", observation=" + observation + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acceptee == null) ? 0 : acceptee.hashCode());
		result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		result = prime * result + ((dateResultat == null) ? 0 : dateResultat.hashCode());
		result = prime * result + ((dimanche == null) ? 0 : dimanche.hashCode());
		result = prime * result + ((dossierEmployeDto == null) ? 0 : dossierEmployeDto.hashCode());
		result = prime * result + ((heureDebut == null) ? 0 : heureDebut.hashCode());
		result = prime * result + ((heureFin == null) ? 0 : heureFin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jeudi == null) ? 0 : jeudi.hashCode());
		result = prime * result + ((lundi == null) ? 0 : lundi.hashCode());
		result = prime * result + ((mardi == null) ? 0 : mardi.hashCode());
		result = prime * result + ((mercredi == null) ? 0 : mercredi.hashCode());
		result = prime * result + ((motifRefus == null) ? 0 : motifRefus.hashCode());
		result = prime * result + ((nomenclatureDto == null) ? 0 : nomenclatureDto.hashCode());
		result = prime * result + ((objet == null) ? 0 : objet.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((samedi == null) ? 0 : samedi.hashCode());
		result = prime * result + ((vendredi == null) ? 0 : vendredi.hashCode());
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
		AutorisationAbsenceDto other = (AutorisationAbsenceDto) obj;
		if (acceptee == null) {
			if (other.acceptee != null)
				return false;
		} else if (!acceptee.equals(other.acceptee))
			return false;
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
		if (dateResultat == null) {
			if (other.dateResultat != null)
				return false;
		} else if (!dateResultat.equals(other.dateResultat))
			return false;
		if (dimanche == null) {
			if (other.dimanche != null)
				return false;
		} else if (!dimanche.equals(other.dimanche))
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
		if (jeudi == null) {
			if (other.jeudi != null)
				return false;
		} else if (!jeudi.equals(other.jeudi))
			return false;
		if (lundi == null) {
			if (other.lundi != null)
				return false;
		} else if (!lundi.equals(other.lundi))
			return false;
		if (mardi == null) {
			if (other.mardi != null)
				return false;
		} else if (!mardi.equals(other.mardi))
			return false;
		if (mercredi == null) {
			if (other.mercredi != null)
				return false;
		} else if (!mercredi.equals(other.mercredi))
			return false;
		if (motifRefus == null) {
			if (other.motifRefus != null)
				return false;
		} else if (!motifRefus.equals(other.motifRefus))
			return false;
		if (nomenclatureDto == null) {
			if (other.nomenclatureDto != null)
				return false;
		} else if (!nomenclatureDto.equals(other.nomenclatureDto))
			return false;
		if (objet == null) {
			if (other.objet != null)
				return false;
		} else if (!objet.equals(other.objet))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (samedi == null) {
			if (other.samedi != null)
				return false;
		} else if (!samedi.equals(other.samedi))
			return false;
		if (vendredi == null) {
			if (other.vendredi != null)
				return false;
		} else if (!vendredi.equals(other.vendredi))
			return false;
		return true;
	}

}
