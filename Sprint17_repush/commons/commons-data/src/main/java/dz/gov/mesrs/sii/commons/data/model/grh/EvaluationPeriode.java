package dz.gov.mesrs.sii.commons.data.model.grh;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

@Entity
@Table(name = "evaluation_periode", schema = "grh")
public class EvaluationPeriode implements Serializable, Identifiable<Long> {

	private static final long serialVersionUID = 11611011988516585L;
	private Long id;
	private Date dateEvaluation;
	private Date dateDebutPeriode;
	private Date dateFinPeriode;
	private SituationEntite situation;
	private List<EvaluationEmploye> evaluationEmployes;
	private RefEtablissement etablissement;

	public EvaluationPeriode() {
		super();
	}

	@Id
	@SequenceGenerator(name = "evaluation_periode_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evaluation_periode_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_evaluation", length = 13)
	public Date getDateEvaluation() {
		return dateEvaluation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut_periode", length = 13)
	public Date getDateDebutPeriode() {
		return dateDebutPeriode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin_periode", length = 13)
	public Date getDateFinPeriode() {
		return dateFinPeriode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "situation", nullable = false)
	public SituationEntite getSituation() {
		return situation;
	}

	@OneToMany(mappedBy = "periode")
	public List<EvaluationEmploye> getEvaluationEmployes() {
		return evaluationEmployes;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ref_etablissement", nullable = false)
	public RefEtablissement getEtablissement() {
		return etablissement;
	}

	public void setEvaluationEmployes(List<EvaluationEmploye> evaluationEmployes) {
		this.evaluationEmployes = evaluationEmployes;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDateEvaluation(Date dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	public void setDateDebutPeriode(Date dateDebutPeriode) {
		this.dateDebutPeriode = dateDebutPeriode;
	}

	public void setDateFinPeriode(Date dateFinPeriode) {
		this.dateFinPeriode = dateFinPeriode;
	}

	public void setSituation(SituationEntite situation) {
		this.situation = situation;
	}

	public void setEtablissement(RefEtablissement etablissement) {
		this.etablissement = etablissement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebutPeriode == null) ? 0 : dateDebutPeriode.hashCode());
		result = prime * result + ((dateEvaluation == null) ? 0 : dateEvaluation.hashCode());
		result = prime * result + ((dateFinPeriode == null) ? 0 : dateFinPeriode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		EvaluationPeriode other = (EvaluationPeriode) obj;
		if (dateDebutPeriode == null) {
			if (other.dateDebutPeriode != null)
				return false;
		} else if (!dateDebutPeriode.equals(other.dateDebutPeriode))
			return false;
		if (dateEvaluation == null) {
			if (other.dateEvaluation != null)
				return false;
		} else if (!dateEvaluation.equals(other.dateEvaluation))
			return false;
		if (dateFinPeriode == null) {
			if (other.dateFinPeriode != null)
				return false;
		} else if (!dateFinPeriode.equals(other.dateFinPeriode))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return "EvaluationPeriode [id=" + id + ", dateEvaluation=" + dateEvaluation + ", dateDebutPeriode="
				+ dateDebutPeriode + ", dateFinPeriode=" + dateFinPeriode + ", situation=" + situation + "]";
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
