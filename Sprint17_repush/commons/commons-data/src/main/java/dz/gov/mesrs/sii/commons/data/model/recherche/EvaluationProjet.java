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


/**
 * @author slimane ramdane   on : 02 fev. 2015 15:33:48
 */
@Entity
@Table(name = "evaluation_projet", schema = "recherche")
public class EvaluationProjet implements java.io.Serializable , Identifiable<Long>  {
	

	/**
	 * serialVersionUID 
	 * @author slimane ramdane  on : 01 fev. 2015 15:30:03
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date dateEvaluation;
	private Date dateFinPeriode;
	private String description;
	private String appreciationGlobale;
	private Integer nbBrevet;
	private Integer nbPublication;
	private String resultatAtteint;
	private Double tauxRealisation;
	private Projet projet;
	
	public EvaluationProjet() {
		
	}

	
	@Id
	@SequenceGenerator(name="evaluation_projet_id_seq_gen", sequenceName="recherche.evaluation_projet_id_seq")
	@GeneratedValue(generator="evaluation_projet_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_evaluation")
	public Date getDateEvaluation() {
		return dateEvaluation;
	}


	public void setDateEvaluation(Date dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin_periode")
	public Date getDateFinPeriode() {
		return dateFinPeriode;
	}


	public void setDateFinPeriode(Date dateFinPeriode) {
		this.dateFinPeriode = dateFinPeriode;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "appreciation_globale")
	public String getAppreciationGlobale() {
		return appreciationGlobale;
	}


	public void setAppreciationGlobale(String appreciationGlobale) {
		this.appreciationGlobale = appreciationGlobale;
	}

	@Column(name = "nb_brevet")
	public Integer getNbBrevet() {
		return nbBrevet;
	}


	public void setNbBrevet(Integer nbBrevet) {
		this.nbBrevet = nbBrevet;
	}

	@Column(name = "nb_publication")
	public Integer getNbPublication() {
		return nbPublication;
	}


	public void setNbPublication(Integer nbPublication) {
		this.nbPublication = nbPublication;
	}

	@Column(name = "resultat_atteint")
	public String getResultatAtteint() {
		return resultatAtteint;
	}


	public void setResultatAtteint(String resultatAtteint) {
		this.resultatAtteint = resultatAtteint;
	}

	@Column(name = "taux_realisation")
	public Double getTauxRealisation() {
		return tauxRealisation;
	}


	public void setTauxRealisation(Double tauxRealisation) {
		this.tauxRealisation = tauxRealisation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_projet")	
	public Projet getProjet() {
		return projet;
	}


	public void setProjet(Projet projet) {
		this.projet = projet;
	}


	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	
	/**
	 * [Identifiable<Integer>.getIdentifiantName] Method 
	 * Overridden By : @author sram  on : 02 fev. 2015  11:02:27
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	
	
}
