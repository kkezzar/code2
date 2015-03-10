package dz.gov.mesrs.sii.commons.data.model.recherche;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;


/**
 * @author slimane ramdane  on : 04. fev 2015  15:33:48
 */
@Entity
@Table(name = "evaluation_indicateur", schema = "recherche")
public class EvaluationIndicateur implements java.io.Serializable , Identifiable<Long>  {
	

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 15 d�c. 2014  15:30:03
	 */
	private static final long serialVersionUID = 1L;
	private Long id;	
	private Nomenclature typeAppreciation;
	private Double valeur;
	private String observation;
	private EvaluationProjet evaluationProjet;
	private IndEvaluation indEvaluation;
	
		
	public EvaluationIndicateur() {
		
	}
	
	
	@Id
	@SequenceGenerator(name="evaluation_indicateur_id_seq_gen", sequenceName="recherche.evaluation_indicateur_id_seq")
	@GeneratedValue(generator="evaluation_indicateur_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_appreciation")
	public Nomenclature getTypeAppreciation() {
		return typeAppreciation;
	}


	public void setTypeAppreciation(Nomenclature typeAppreciation) {
		this.typeAppreciation = typeAppreciation;
	}

	@Column(name = "valeur")
	public Double getValeur() {
		return valeur;
	}


	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}

	@Column(name = "observation")
	public String getObservation() {
		return observation;
	}


	public void setObservation(String observation) {
		this.observation = observation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_evaluation_projet")	
	public EvaluationProjet getEvaluationProjet() {
		return evaluationProjet;
	}


	public void setEvaluationProjet(EvaluationProjet evaluationProjet) {
		this.evaluationProjet = evaluationProjet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ind_evaluation")	
	public IndEvaluation getIndEvaluation() {
		return indEvaluation;
	}


	public void setIndEvaluation(IndEvaluation indEvaluation) {
		this.indEvaluation = indEvaluation;
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
