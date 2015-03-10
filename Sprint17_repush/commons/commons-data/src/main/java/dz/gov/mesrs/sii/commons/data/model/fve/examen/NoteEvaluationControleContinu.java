package dz.gov.mesrs.sii.commons.data.model.fve.examen;

// Generated 29 sept. 2014 16:32:23 by Hibernate Tools 3.6.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AffectationGroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * NoteEvaluationControleContinu generated by hbm2java
 */
@Entity
@Table(name = "note_evaluation_controle_continu", schema = "fve_examen")
public class NoteEvaluationControleContinu implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 19 nov. 2014  17:26:38
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private EvaluationControleContinu evaluationControleContinu;
	private Nomenclature nomenclature;
	private AffectationGroupePedagogique affectationGroupePedagogique;
	private Double note;
	private Boolean absent;
	private String observation;

	public NoteEvaluationControleContinu() {
	}

	public NoteEvaluationControleContinu(
			EvaluationControleContinu evaluationControleContinu, int id) {
		this.evaluationControleContinu = evaluationControleContinu;
		this.id = id;
	}

	public NoteEvaluationControleContinu(
			EvaluationControleContinu evaluationControleContinu,
			Nomenclature nomenclature,
			AffectationGroupePedagogique affectationGroupePedagogique,
			int id, Double note, Boolean absent, String observation) {
		this.evaluationControleContinu = evaluationControleContinu;
		this.nomenclature = nomenclature;
		this.affectationGroupePedagogique = affectationGroupePedagogique;
		this.id = id;
		this.note = note;
		this.absent = absent;
		this.observation = observation;
	}

	@SequenceGenerator(name = "note_evaluation_controle_continu_id_seq", sequenceName = "fve_examen.note_evaluation_controle_continu_id_seq")
	@Id
	@GeneratedValue(generator = "note_evaluation_controle_continu_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_evaluation_controle_continu")
	public EvaluationControleContinu getEvaluationControleContinu() {
		return this.evaluationControleContinu;
	}

	public void setEvaluationControleContinu(
			EvaluationControleContinu evaluationControleContinu) {
		this.evaluationControleContinu = evaluationControleContinu;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nc_type_appreciation")
	public Nomenclature getNomenclature() {
		return this.nomenclature;
	}

	public void setNomenclature(Nomenclature nomenclature) {
		this.nomenclature = nomenclature;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_affectation_gp")
	public AffectationGroupePedagogique getAffectationGroupePedagogique() {
		return affectationGroupePedagogique;
	}

	public void setAffectationGroupePedagogique(
			AffectationGroupePedagogique affectationGroupePedagogique) {
		this.affectationGroupePedagogique = affectationGroupePedagogique;
	}

	@Column(name = "note", precision = 17, scale = 17)
	public Double getNote() {
		return this.note;
	}

	public void setNote(Double note) {
		this.note = note;
	}

	@Column(name = "absent")
	public Boolean getAbsent() {
		return this.absent;
	}

	public void setAbsent(Boolean absent) {
		this.absent = absent;
	}

	@Column(name = "observation", length = 500)
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

}