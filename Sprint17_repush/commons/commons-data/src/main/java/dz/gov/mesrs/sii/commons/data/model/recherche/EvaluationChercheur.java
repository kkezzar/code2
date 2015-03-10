package dz.gov.mesrs.sii.commons.data.model.recherche;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

@Entity
@Table(name = "evaluation_chercheur", schema = "recherche")
public class EvaluationChercheur implements java.io.Serializable , Identifiable<Long>  {

	private static final long serialVersionUID = 1L;
	private Long id;
	private GrilleEvaluation grilleEvaluation;
	private RefIndividu refIndividu;
	private Date dateEvaluation;
	private Date dateDebutPeriode;
	private Date dateFinPeriode;
	private String appreciation;
	
	public EvaluationChercheur() {
		
	}

	@Id
	@SequenceGenerator(name="evaluation_chercheur_id_seq_gen", sequenceName="recherche.evaluation_chercheur_id_seq")
	@GeneratedValue(generator="evaluation_chercheur_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_grille_evaluation", nullable = false)
	public GrilleEvaluation getGrilleEvaluation() {
		return grilleEvaluation;
	}

	public void setGrilleEvaluation(GrilleEvaluation grilleEvaluation) {
		this.grilleEvaluation = grilleEvaluation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_individu", nullable = false)
	public RefIndividu getRefIndividu() {
		return refIndividu;
	}

	public void setRefIndividu(RefIndividu refIndividu) {
		this.refIndividu = refIndividu;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_evaluation", nullable=false)
	public Date getDateEvaluation() {
		return dateEvaluation;
	}

	public void setDateEvaluation(Date dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut_periode", nullable=false)
	public Date getDateDebutPeriode() {
		return dateDebutPeriode;
	}

	public void setDateDebutPeriode(Date dateDebutPeriode) {
		this.dateDebutPeriode = dateDebutPeriode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin_periode", nullable=false)
	public Date getDateFinPeriode() {
		return dateFinPeriode;
	}

	public void setDateFinPeriode(Date dateFinPeriode) {
		this.dateFinPeriode = dateFinPeriode;
	}

	@Column(name = "appreciation", nullable=false, length=1000)
	public String getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
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
