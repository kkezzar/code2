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
import javax.persistence.UniqueConstraint;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

@Entity
@Table(name = "evaluation_valeur", schema = "recherche", 
				uniqueConstraints=@UniqueConstraint(columnNames={"id", "id_evaluation_chercheur", "id_grille_evaluation_detail", "id_nc_appreciation"}))
public class EvaluationValeur implements java.io.Serializable , Identifiable<Long>  {

	private static final long serialVersionUID = 1L;
	private Long id;
	private EvaluationChercheur evaluationChercheur;
	private GrilleEvaluationDetail grilleEvaluationDetail;
	private Nomenclature ncNiveauAppreciation;
	private Double note;
	private String observation;
	
	public EvaluationValeur() {
		
	}

	@Id
	@SequenceGenerator(name="evaluation_valeur_id_seq_gen", sequenceName="recherche.evaluation_valeur_id_seq")
	@GeneratedValue(generator="evaluation_valeur_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_evaluation_chercheur", nullable = false)
	public EvaluationChercheur getEvaluationChercheur() {
		return evaluationChercheur;
	}

	public void setEvaluationChercheur(EvaluationChercheur evaluationChercheur) {
		this.evaluationChercheur = evaluationChercheur;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_grille_evaluation_detail", nullable = false)
	public GrilleEvaluationDetail getGrilleEvaluationDetail() {
		return grilleEvaluationDetail;
	}

	public void setGrilleEvaluationDetail(
			GrilleEvaluationDetail grilleEvaluationDetail) {
		this.grilleEvaluationDetail = grilleEvaluationDetail;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_appreciation", nullable = false)
	public Nomenclature getNcNiveauAppreciation() {
		return ncNiveauAppreciation;
	}

	public void setNcNiveauAppreciation(Nomenclature ncNiveauAppreciation) {
		this.ncNiveauAppreciation = ncNiveauAppreciation;
	}

	@Column(name = "note", nullable = false)
	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
	}

	@Column(name = "observation")
	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
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
