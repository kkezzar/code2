package dz.gov.mesrs.sii.commons.data.model.grh;



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
 * 
 * @author BELDI Jamel
 *
 */
@Entity
@Table(name = "inscription_session_formation", schema = "grh")
public class InscriptionSessionFormation implements java.io.Serializable , Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private SessionFormation sessionFormation;
	private DossierEmploye dossierEmploye;
	private Date dateDemande;
	private Date dateInscription;
	private Boolean resultat;
	private String motif;
	private Boolean present;

	public InscriptionSessionFormation() {
	}

	public InscriptionSessionFormation(Long id) {
		this.id = id;
	}

	

	@Id
	@SequenceGenerator(name="inscription_session_formation_id_seq_gen", sequenceName="grh.inscription_session_formation_id_seq")
	@GeneratedValue(generator="inscription_session_formation_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_formation")
	public SessionFormation getSessionFormation() {
		return this.sessionFormation;
	}

	public void setSessionFormation(SessionFormation sessionFormation) {
		this.sessionFormation = sessionFormation;
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
	@Column(name = "date_demande", length = 13)
	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_inscription", length = 13)
	public Date getDateInscription() {
		return this.dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	@Column(name = "resultat")
	public Boolean getResultat() {
		return this.resultat;
	}

	public void setResultat(Boolean resultat) {
		this.resultat = resultat;
	}

	@Column(name = "motif")
	public String getMotif() {
		return this.motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	@Column(name = "present")
	public Boolean getPresent() {
		return this.present;
	}

	public void setPresent(Boolean present) {
		this.present = present;
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
