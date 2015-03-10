package dz.gov.mesrs.sii.commons.data.model.grh;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;

@Entity
@Table(name = "evaluation_employe", schema = "grh")
public class EvaluationEmploye implements Serializable, Identifiable<Long> {

	private static final long serialVersionUID = -7218545712214974503L;
	private Long id;
	private String appreciation;
	private Double note;
	private Date dateContestation;
	private Date dateCommission;
	private Double noteRevise;
	private String objetContestation;
	private Boolean resultat;
	private String motif;
	private EvaluationPeriode periode;
	private DossierEmploye employe;
	private List<EvaluationDetail> details;

	public EvaluationEmploye() {
		super();
	}

	@Id
	@SequenceGenerator(name = "evaluation_employe_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evaluation_employe_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	@Column(name = "appreciation")
	public String getAppreciation() {
		return appreciation;
	}

	@Column(name = "note")
	public Double getNote() {
		return note;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_contestation", length = 13)
	public Date getDateContestation() {
		return dateContestation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_commission", length = 13)
	public Date getDateCommission() {
		return dateCommission;
	}

	@Column(name = "note_revise")
	public Double getNoteRevise() {
		return noteRevise;
	}

	@Column(name = "objet_contestation")
	public String getObjetContestation() {
		return objetContestation;
	}

	@Column(name = "resultat")
	public Boolean getResultat() {
		return resultat;
	}

	@Column(name = "motif")
	public String getMotif() {
		return motif;
	}
	@ManyToOne
	@JoinColumn(name = "evaluation_periode")
	public EvaluationPeriode getPeriode() {
		return periode;
	}

	@ManyToOne
	@JoinColumn(name = "dossier_employe")
	public DossierEmploye getEmploye() {
		return employe;
	}

	@OneToMany(mappedBy = "evaluationEmploye" ,cascade=CascadeType.ALL ,orphanRemoval=true)
	public List<EvaluationDetail> getDetails() {
		return details;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}

	public void setNote(Double note) {
		this.note = note;
	}

	public void setDateContestation(Date dateContestation) {
		this.dateContestation = dateContestation;
	}

	public void setDateCommission(Date dateCommission) {
		this.dateCommission = dateCommission;
	}

	public void setNoteRevise(Double noteRevise) {
		this.noteRevise = noteRevise;
	}

	public void setObjetContestation(String objetContestation) {
		this.objetContestation = objetContestation;
	}

	public void setResultat(Boolean resultat) {
		this.resultat = resultat;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public void setPeriode(EvaluationPeriode periode) {
		this.periode = periode;
	}

	public void setEmploye(DossierEmploye employe) {
		this.employe = employe;
	}

	public void setDetails(List<EvaluationDetail> details) {
		this.details = details;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appreciation == null) ? 0 : appreciation.hashCode());
		result = prime * result + ((dateCommission == null) ? 0 : dateCommission.hashCode());
		result = prime * result + ((dateContestation == null) ? 0 : dateContestation.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motif == null) ? 0 : motif.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((noteRevise == null) ? 0 : noteRevise.hashCode());
		result = prime * result + ((objetContestation == null) ? 0 : objetContestation.hashCode());
		result = prime * result + ((periode == null) ? 0 : periode.hashCode());
		result = prime * result + ((resultat == null) ? 0 : resultat.hashCode());
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
		EvaluationEmploye other = (EvaluationEmploye) obj;
		if (appreciation == null) {
			if (other.appreciation != null)
				return false;
		} else if (!appreciation.equals(other.appreciation))
			return false;
		if (dateCommission == null) {
			if (other.dateCommission != null)
				return false;
		} else if (!dateCommission.equals(other.dateCommission))
			return false;
		if (dateContestation == null) {
			if (other.dateContestation != null)
				return false;
		} else if (!dateContestation.equals(other.dateContestation))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (employe == null) {
			if (other.employe != null)
				return false;
		} else if (!employe.equals(other.employe))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motif == null) {
			if (other.motif != null)
				return false;
		} else if (!motif.equals(other.motif))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (noteRevise == null) {
			if (other.noteRevise != null)
				return false;
		} else if (!noteRevise.equals(other.noteRevise))
			return false;
		if (objetContestation == null) {
			if (other.objetContestation != null)
				return false;
		} else if (!objetContestation.equals(other.objetContestation))
			return false;
		if (periode == null) {
			if (other.periode != null)
				return false;
		} else if (!periode.equals(other.periode))
			return false;
		if (resultat == null) {
			if (other.resultat != null)
				return false;
		} else if (!resultat.equals(other.resultat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EvaluationEmploye [id=" + id + ", appreciation=" + appreciation + ", note=" + note
				+ ", dateContestation=" + dateContestation + ", dateCommission=" + dateCommission + ", noteRevise="
				+ noteRevise + ", objetContestation=" + objetContestation + ", resultat=" + resultat + ", motif="
				+ motif + ", periode=" + periode + ", employe=" + employe.getMatricule() + ", details=" + details + "]";
	}

	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}
