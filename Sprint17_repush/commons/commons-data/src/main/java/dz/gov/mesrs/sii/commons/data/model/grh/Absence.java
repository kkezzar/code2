package dz.gov.mesrs.sii.commons.data.model.grh;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

@Entity
@Table(name = "absence", schema = "grh")
public class Absence implements java.io.Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = 130588948905449839L;

	private Integer id;
	private Nomenclature nomenclature;
	private DossierEmploye employe;
	private Double nbreJours;
	private Date dateDebut;
	private Date dateFin;
	private Date heureDebut;
	private Date heureFin;
	private Boolean jusitifee;
	private Boolean renumeree;
	private SituationEntite situation;

	public Absence() {
		super();
	}

	public Absence(Integer id) {
		this.id = id;
	}

	@Id
	@SequenceGenerator(name = "absence_id_absence_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "absence_id_absence_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type")
	public Nomenclature getNomenclature() {
		return this.nomenclature;
	}

	public void setNomenclature(Nomenclature nomenclature) {
		this.nomenclature = nomenclature;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employe")
	public DossierEmploye getEmploye() {
		return this.employe;
	}

	public void setEmploye(DossierEmploye dossierEmploye) {
		this.employe = dossierEmploye;
	}

	@Column(name = "nbre_jours")
	public Double getNbreJours() {
		return this.nbreJours;
	}

	public void setNbreJours(Double nbreJours) {
		this.nbreJours = nbreJours;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut", length = 13)
	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin", length = 13)
	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "heure_debut", length = 15)
	public Date getHeureDebut() {
		return this.heureDebut;
	}

	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "heure_fin", length = 15)
	public Date getHeureFin() {
		return this.heureFin;
	}

	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

	@Column(name = "jusitifee")
	public Boolean getJusitifee() {
		return this.jusitifee;
	}

	public void setJusitifee(Boolean jusitifee) {
		this.jusitifee = jusitifee;
	}

	@Column(name = "renumeree")
	public Boolean getRenumeree() {
		return this.renumeree;
	}

	public void setRenumeree(Boolean renumeree) {
		this.renumeree = renumeree;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "situation", nullable = false)
	public SituationEntite getSituation() {
		return situation;
	}

	public void setSituation(SituationEntite situation) {
		this.situation = situation;
	}

	@Transient
	@Override
	public Integer getIdenfiant() {
		return this.getId();
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
		result = prime * result + ((heureDebut == null) ? 0 : heureDebut.hashCode());
		result = prime * result + ((heureFin == null) ? 0 : heureFin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jusitifee == null) ? 0 : jusitifee.hashCode());
		result = prime * result + ((nbreJours == null) ? 0 : nbreJours.hashCode());
		result = prime * result + ((nomenclature == null) ? 0 : nomenclature.hashCode());
		result = prime * result + ((renumeree == null) ? 0 : renumeree.hashCode());
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
		Absence other = (Absence) obj;
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
		if (employe == null) {
			if (other.employe != null)
				return false;
		} else if (!employe.equals(other.employe))
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
		if (nomenclature == null) {
			if (other.nomenclature != null)
				return false;
		} else if (!nomenclature.equals(other.nomenclature))
			return false;
		if (renumeree == null) {
			if (other.renumeree != null)
				return false;
		} else if (!renumeree.equals(other.renumeree))
			return false;
		if (situation == null) {
			if (other.situation != null)
				return false;
		} else if (!situation.equals(other.situation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Absence [id=" + id + ", nomenclature=" + nomenclature + ", dossierEmploye="
				+ employe.getMatricule() + ", nbreJours=" + nbreJours + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", jusitifee="
				+ jusitifee + ", renumeree=" + renumeree + ", situation=" + situation + "]";
	}

}
