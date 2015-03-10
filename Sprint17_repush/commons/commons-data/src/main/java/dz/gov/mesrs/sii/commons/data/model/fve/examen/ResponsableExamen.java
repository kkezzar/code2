package dz.gov.mesrs.sii.commons.data.model.fve.examen;

// Generated 1 oct. 2014 16:55:50 by Hibernate Tools 3.6.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

/**
 * 
 * @author BELDI Jamel on : 1 oct. 2014 17:04:34
 */
@Entity
@Table(name = "responsable_examen", schema = "fve_examen")
public class ResponsableExamen implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 1 oct. 2014 17:14:42
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private SalleExamen salleExamen;
	private RefIndividu refIndividu;
	private boolean absent;
	private String motifAbsence;

	public ResponsableExamen() {
	}

	public ResponsableExamen(int id) {
		this.id = id;
	}

	public ResponsableExamen(int id, SalleExamen salleExamen, RefIndividu refIndividu, boolean absent,
	        String motifAbsence) {
		this.id = id;
		this.salleExamen = salleExamen;
		this.refIndividu = refIndividu;
		this.absent = absent;
		this.motifAbsence = motifAbsence;
	}

	@SequenceGenerator(name = "responsable_examen_id_seq_gen", sequenceName = "fve_examen.responsable_examen_id_seq")
	@Id
	@GeneratedValue(generator = "responsable_examen_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_salle_examen")
	public SalleExamen getSalleExamen() {
		return this.salleExamen;
	}

	public void setSalleExamen(SalleExamen salleExamen) {
		this.salleExamen = salleExamen;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_individu")
	public RefIndividu getRefIndividu() {
		return this.refIndividu;
	}

	public void setRefIndividu(RefIndividu refIndividu) {
		this.refIndividu = refIndividu;
	}

	@Column(name = "absent")
	public boolean getAbsent() {
		return this.absent;
	}

	public void setAbsent(boolean absent) {
		this.absent = absent;
	}

	@Column(name = "motif_absence")
	public String getMotifAbsence() {
		return this.motifAbsence;
	}

	public void setMotifAbsence(String motifAbsence) {
		this.motifAbsence = motifAbsence;
	}

}
