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
@Table(name = "autorisation_absence", schema = "grh")
public class AutorisationAbsence implements java.io.Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = 4209138943536105723L;

	private Integer id;
	private Nomenclature nomenclature;
	private DossierEmploye employe;
	private String objet;
	private Date dateDemande;
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
	private SituationEntite situation;

	public AutorisationAbsence() {
		super();
	}

	@Id
	@SequenceGenerator(name = "autorisation_absence_id_autorisation_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autorisation_absence_id_autorisation_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "frequence")
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

	@Column(name = "objet", length = 256)
	public String getObjet() {
		return this.objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
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

	@Column(name = "dimanche")
	public Boolean getDimanche() {
		return this.dimanche;
	}

	public void setDimanche(Boolean dimanche) {
		this.dimanche = dimanche;
	}

	@Column(name = "lundi")
	public Boolean getLundi() {
		return this.lundi;
	}

	public void setLundi(Boolean lundi) {
		this.lundi = lundi;
	}

	@Column(name = "mardi")
	public Boolean getMardi() {
		return this.mardi;
	}

	public void setMardi(Boolean mardi) {
		this.mardi = mardi;
	}

	@Column(name = "mercredi")
	public Boolean getMercredi() {
		return this.mercredi;
	}

	public void setMercredi(Boolean mercredi) {
		this.mercredi = mercredi;
	}

	@Column(name = "jeudi")
	public Boolean getJeudi() {
		return this.jeudi;
	}

	public void setJeudi(Boolean jeudi) {
		this.jeudi = jeudi;
	}

	@Column(name = "vendredi")
	public Boolean getVendredi() {
		return this.vendredi;
	}

	public void setVendredi(Boolean vendredi) {
		this.vendredi = vendredi;
	}

	@Column(name = "samedi")
	public Boolean getSamedi() {
		return this.samedi;
	}

	public void setSamedi(Boolean samedi) {
		this.samedi = samedi;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_resultat", length = 13)
	public Date getDateResultat() {
		return this.dateResultat;
	}

	public void setDateResultat(Date dateResultat) {
		this.dateResultat = dateResultat;
	}

	@Column(name = "acceptee")
	public Boolean getAcceptee() {
		return this.acceptee;
	}

	public void setAcceptee(Boolean acceptee) {
		this.acceptee = acceptee;
	}

	@Column(name = "motif_refus", length = 256)
	public String getMotifRefus() {
		return this.motifRefus;
	}

	public void setMotifRefus(String motifRefus) {
		this.motifRefus = motifRefus;
	}

	@Column(name = "observation")
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "situation", nullable = false)
	public SituationEntite getSituation() {
		return situation;
	}

	public void setSituation(SituationEntite situtation) {
		this.situation = situtation;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_demande", length = 13)
	public Date getDateDemande() {
		return this.dateDemande;
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
	public String toString() {
		return "AutorisationAbsence [id=" + id + ", nomenclature=" + nomenclature + ", dossierEmploye="
				+ employe.getId() + ", objet=" + objet + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", dimanche=" + dimanche + ", lundi="
				+ lundi + ", mardi=" + mardi + ", mercredi=" + mercredi + ", jeudi=" + jeudi + ", vendredi=" + vendredi
				+ ", samedi=" + samedi + ", dateResultat=" + dateResultat + ", acceptee=" + acceptee + ", motifRefus="
				+ motifRefus + ", observation=" + observation + ", situation=" + situation + "]";
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
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
		result = prime * result + ((heureDebut == null) ? 0 : heureDebut.hashCode());
		result = prime * result + ((heureFin == null) ? 0 : heureFin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jeudi == null) ? 0 : jeudi.hashCode());
		result = prime * result + ((lundi == null) ? 0 : lundi.hashCode());
		result = prime * result + ((mardi == null) ? 0 : mardi.hashCode());
		result = prime * result + ((mercredi == null) ? 0 : mercredi.hashCode());
		result = prime * result + ((motifRefus == null) ? 0 : motifRefus.hashCode());
		result = prime * result + ((nomenclature == null) ? 0 : nomenclature.hashCode());
		result = prime * result + ((objet == null) ? 0 : objet.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((samedi == null) ? 0 : samedi.hashCode());
		result = prime * result + ((situation == null) ? 0 : situation.hashCode());
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
		AutorisationAbsence other = (AutorisationAbsence) obj;
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
		if (nomenclature == null) {
			if (other.nomenclature != null)
				return false;
		} else if (!nomenclature.equals(other.nomenclature))
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
		if (situation == null) {
			if (other.situation != null)
				return false;
		} else if (!situation.equals(other.situation))
			return false;
		if (vendredi == null) {
			if (other.vendredi != null)
				return false;
		} else if (!vendredi.equals(other.vendredi))
			return false;
		return true;
	}

}
