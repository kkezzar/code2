package dz.gov.mesrs.sii.commons.data.model.gfc;

// Generated 24 nov. 2014 16:52:54 by Hibernate Tools 3.6.0

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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

/**
 * ExerciceBudgetaire generated by hbm2java
 */
@Entity
@Table(name = "exercice_budgetaire", schema = "gfc")
public class ExerciceBudgetaire implements java.io.Serializable, Identifiable<Integer> {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:33:00
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private SituationEntite situation;
	private Integer annee;
	private String intituleFr;
	private String intituleAr;
	private Date dateOuverture;
	private Date dateCloture;
	private String observation;

	private List<ProjetBudget> projetBudgets = new ArrayList<ProjetBudget>(0);

	public ExerciceBudgetaire() {
	}

	public ExerciceBudgetaire(Integer id, SituationEntite situation, Integer annee, String intituleFr,
			String intituleAr, Date dateOuverture) {
		this.id = id;
		this.situation = situation;
		this.annee = annee;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.dateOuverture = dateOuverture;
	}

	public ExerciceBudgetaire(Integer id, SituationEntite situation, Integer annee, String intituleFr,
			String intituleAr, Date dateOuverture, Date dateCloture, String observation,
			List<ProjetBudget> projetBudgets) {
		this.id = id;
		this.situation = situation;
		this.annee = annee;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.dateOuverture = dateOuverture;
		this.dateCloture = dateCloture;
		this.observation = observation;
		// this.projetBudgets = projetBudgets;
	}

	@SequenceGenerator(name = "exercice_budgetaire_id_seq", sequenceName = "gfc.exercice_budgetaire_id_seq")
	@Id
	@GeneratedValue(generator = "exercice_budgetaire_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntite situation) {
		this.situation = situation;
	}

	@Column(name = "annee", nullable = false)
	public Integer getAnnee() {
		return this.annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	@Column(name = "intitule_fr", nullable = false, length = 200)
	public String getIntituleFr() {
		return this.intituleFr;
	}

	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	@Column(name = "intitule_ar", nullable = false, length = 200)
	public String getIntituleAr() {
		return this.intituleAr;
	}

	public void setIntituleAr(String intituleAr) {
		this.intituleAr = intituleAr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_ouverture", nullable = false, length = 29)
	public Date getDateOuverture() {
		return this.dateOuverture;
	}

	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_cloture", length = 29)
	public Date getDateCloture() {
		return this.dateCloture;
	}

	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	@Column(name = "observation", length = 200)
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "exerciceBudgetaire")
	public List<ProjetBudget> getProjetBudgets() {
		return this.projetBudgets;
	}

	public void setProjetBudgets(List<ProjetBudget> projetBudgets) {
		this.projetBudgets = projetBudgets;
	}

	@Transient
	@Override
	public Integer getIdenfiant() {
		return id;
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}
