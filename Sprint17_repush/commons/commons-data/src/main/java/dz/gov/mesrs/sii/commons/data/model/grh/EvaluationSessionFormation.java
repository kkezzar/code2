package dz.gov.mesrs.sii.commons.data.model.grh;



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
 * 
 * @author BELDI Jamel
 *
 */
@Entity
@Table(name = "evaluation_session_formation", schema = "grh")
public class EvaluationSessionFormation implements java.io.Serializable , Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Nomenclature nomenclatureByChapitre;
	private Nomenclature nomenclatureByCritere;
	private Nomenclature nomenclatureByAppreciation;
	private SessionFormation sessionFormation;
	private String observation;

	public EvaluationSessionFormation() {
	}

	public EvaluationSessionFormation(Long id) {
		this.id = id;
	}

	

	@Id
	@SequenceGenerator(name="evaluation_session_formation_id_seq_gen", sequenceName="grh.evaluation_session_formation_id_seq")
	@GeneratedValue(generator="evaluation_session_formation_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chapitre")
	public Nomenclature getNomenclatureByChapitre() {
		return this.nomenclatureByChapitre;
	}

	public void setNomenclatureByChapitre(Nomenclature nomenclatureByChapitre) {
		this.nomenclatureByChapitre = nomenclatureByChapitre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "critere")
	public Nomenclature getNomenclatureByCritere() {
		return this.nomenclatureByCritere;
	}

	public void setNomenclatureByCritere(Nomenclature nomenclatureByCritere) {
		this.nomenclatureByCritere = nomenclatureByCritere;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appreciation")
	public Nomenclature getNomenclatureByAppreciation() {
		return this.nomenclatureByAppreciation;
	}

	public void setNomenclatureByAppreciation(Nomenclature nomenclatureByAppreciation) {
		this.nomenclatureByAppreciation = nomenclatureByAppreciation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_formation")
	public SessionFormation getSessionFormation() {
		return this.sessionFormation;
	}

	public void setSessionFormation(SessionFormation sessionFormation) {
		this.sessionFormation = sessionFormation;
	}

	@Column(name = "observation")
	public String getObservation() {
		return this.observation;
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
