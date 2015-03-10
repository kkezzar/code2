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
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

/**
 * 
 * @author BELDI Jamel
 *
 */
@Entity
@Table(name = "formateur_session_formation", schema = "grh")
public class FormateurSessionFormation implements java.io.Serializable , Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private RefIndividu refIndividu;
	private SessionFormation sessionFormation;

	public FormateurSessionFormation() {
	}

	public FormateurSessionFormation(Long id) {
		this.id = id;
	}

	public FormateurSessionFormation(Long id, RefIndividu refIndividu, SessionFormation sessionFormation) {
		this.id = id;
		this.refIndividu = refIndividu;
		this.sessionFormation = sessionFormation;
	}

	@Id
	@SequenceGenerator(name="formateur_session_formation_id_seq_gen", sequenceName="grh.formateur_session_formation_id_seq")
	@GeneratedValue(generator="formateur_session_formation_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "individu")
	public RefIndividu getRefIndividu() {
		return this.refIndividu;
	}

	public void setRefIndividu(RefIndividu refIndividu) {
		this.refIndividu = refIndividu;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_formation")
	public SessionFormation getSessionFormation() {
		return this.sessionFormation;
	}

	public void setSessionFormation(SessionFormation sessionFormation) {
		this.sessionFormation = sessionFormation;
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
