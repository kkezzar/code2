package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

// Generated 8 juin 2014 12:21:31 by Hibernate Tools 3.6.0

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

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.MatiereConstitutive;
/**
 * AbsenceMatiere generated by hbm2java
 */
@Entity
@Table(name = "exclusion", schema = "cursus")
public class Exclusion implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELBRIK Oualid  on : 8 juin 2014  13:32:41
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private DossierInscriptionAdministrative dossierInscriptionAdministrative;
	private AnneeAcademique anneeAcademique;
	private MatiereConstitutive mc;
	private String refCodeTypeExclusion;
	private String refCodeTypeCause;
	private String cause;
	private Date dateDecision;
	private Date dateDebut;
	private Date dateFin;
	private Short nombreAbsence;
	
	

	public Exclusion() {
	}

	public Exclusion(int id) {
		this.id = id;
	}

	public Exclusion(int id,DossierInscriptionAdministrative dossierInscriptionAdministrative,AnneeAcademique anneeAcademique,
			MatiereConstitutive mc, String refCodeTypeExclusion,String refCodeTypeCause, String cause, Date dateDecision, Date dateDebut, Date dateFin, Short nombreAbsence) {
		this.id = id;
		this.mc = mc;
		this.dossierInscriptionAdministrative = dossierInscriptionAdministrative;
		this.anneeAcademique = anneeAcademique;
		this.refCodeTypeExclusion = refCodeTypeExclusion;
		this.refCodeTypeCause = refCodeTypeCause;
		this.cause = cause;
		this.dateDecision = dateDecision;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nombreAbsence = nombreAbsence;
		
		
	}

	
	
	@SequenceGenerator(name = "exclusion_id_seq_gen", sequenceName = "cursus.exclusion_id_seq")
	@Id
	@GeneratedValue(generator = "exclusion_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mc")
	public MatiereConstitutive getMc() {
		return this.mc;
	}

	public void setMc(MatiereConstitutive mc) {
		this.mc = mc;
	}

				
	@Column(name = "ref_code_type_exclusion", length = 30)
	public String getRefCodeTypeExclusion() {
		return this.refCodeTypeExclusion;
	}

	public void setRefCodeTypeExclusion(String refCodeTypeExclusion) {
		this.refCodeTypeExclusion = refCodeTypeExclusion;
	}
	
	@Column(name = "ref_code_type_cause", length = 30)
	public String getRefCodeTypeCause() {
		return this.refCodeTypeCause;
	}

	public void setRefCodeTypeCause(String refCodeTypeCause) {
		this.refCodeTypeCause = refCodeTypeCause;
	}
	
	@Column(name = "cause")
	public String getCause() {
		return this.cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}


	

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut", length = 13)
	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin", length = 13)
	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_decision", length = 13)
	public Date getDateDecision() {
		return this.dateDecision;
	}

	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_inscription_administrative", nullable = false)
	public DossierInscriptionAdministrative getDossierInscriptionAdministrative() {
		return this.dossierInscriptionAdministrative;
	}

	public void setDossierInscriptionAdministrative(
			DossierInscriptionAdministrative dossierInscriptionAdministrative) {
		this.dossierInscriptionAdministrative = dossierInscriptionAdministrative;
	}
	

		@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_annee_academique", nullable = false)
	public AnneeAcademique getAnneeAcademique() {
		return this.anneeAcademique;
	}

	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	/**
	 * [Exclusion.nombreAbsence] Getter 
	 * @author BELBRIK Oualid on : 7 juil. 2014  15:45:34
	 * @return the nombreAbsence
	 */
	@Column(name = "nombre_absence")
	public Short getNombreAbsence() {
		return nombreAbsence;
	}

	/**
	 * [Exclusion.nombreAbsence] Setter 
	 * @author BELBRIK Oualid on : 7 juil. 2014  15:45:34
	 * @param nombreAbsence the nombreAbsence to set
	 */
	public void setNombreAbsence(Short nombreAbsence) {
		this.nombreAbsence = nombreAbsence;
	}
	
}
