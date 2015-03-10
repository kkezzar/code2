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

/**
 * 
 * @author BELDI Jamel
 *
 */
@Entity
@Table(name = "grade_cycle_formation", schema = "grh")
public class GradeCycleFormation implements java.io.Serializable , Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Grade grade;
	private CycleFormation cycleFormation;
	private Integer nbCandidat;
	private String observation;

	public GradeCycleFormation() {
	}

	public GradeCycleFormation(Long id) {
		this.id = id;
	}

	

	@Id
	@SequenceGenerator(name="grade_cycle_formation_id_seq_gen", sequenceName="grh.grade_cycle_formation_id_seq")
	@GeneratedValue(generator="grade_cycle_formation_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grade")
	public Grade getGrade() {
		return this.grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cycle_formation")
	public CycleFormation getCycleFormation() {
		return this.cycleFormation;
	}

	public void setCycleFormation(CycleFormation cycleFormation) {
		this.cycleFormation = cycleFormation;
	}

	@Column(name = "nb_candidat")
	public Integer getNbCandidat() {
		return this.nbCandidat;
	}

	public void setNbCandidat(Integer nbCandidat) {
		this.nbCandidat = nbCandidat;
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
