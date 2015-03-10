package dz.gov.mesrs.sii.commons.data.model.grh;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * 
 * @author BELDI Jamel
 *
 */

@Entity
@Table(name = "prevision_conge", schema = "grh")
public class PrevisionConge implements java.io.Serializable, Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private DossierEmploye dossierEmploye;
	private Date dateDebut;
	private String observation;
	private Double nbreJours;

	public PrevisionConge() {
	}

	public PrevisionConge(Long id) {
		this.id = id;
	}

	
	@Id
	@SequenceGenerator(name = "grh_prevision_conge_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh_prevision_conge_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employe")
	public DossierEmploye getDossierEmploye() {
		return this.dossierEmploye;
	}

	public void setDossierEmploye(DossierEmploye dossierEmploye) {
		this.dossierEmploye = dossierEmploye;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut", length = 13)
	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	@Column(name = "observation")
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Column(name = "nbre_jours", precision = 17, scale = 17)
	public Double getNbreJours() {
		return this.nbreJours;
	}

	public void setNbreJours(Double nbreJours) {
		this.nbreJours = nbreJours;
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
