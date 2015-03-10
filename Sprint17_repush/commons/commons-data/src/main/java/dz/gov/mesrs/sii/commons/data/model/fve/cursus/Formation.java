package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

// Generated 21 mai 2014 09:17:10 by Hibernate Tools 3.6.0

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

/**
 * Formation generated by hbm2java
 */
@Entity
@Table(name = "formation", schema = "cursus")
public class Formation implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 09:19:18
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private ReleveDeNotes releveDeNotes;
	private DossierEtudiant dossierEtudiant;
	private String code;
	private String refCodeTypeFormation;
	private String refCodeSpecialite;
	private String libelleFr;
	private String libelleAr;
	private String oraganismeFr;
	private String oraganismeAr;
	private Date dateDebut;
	private Date dateFin;

	public Formation() {
	}

	public Formation(int id, String code) {
		this.id = id;
		this.code = code;
	}

	public Formation(int id, ReleveDeNotes releveDeNotes,
			DossierEtudiant dossierEtudiant, String code,
			String refCodeTypeFormation,String refCodeSpecialite, String libelleFr, String libelleAr,
			String oraganismeFr, String oraganismeAr, Date dateDebut,
			Date dateFin) {
		this.id = id;
		this.releveDeNotes = releveDeNotes;
		this.dossierEtudiant = dossierEtudiant;
		this.code = code;
		this.refCodeTypeFormation = refCodeTypeFormation;
		this.refCodeSpecialite = refCodeSpecialite;
		this.libelleFr = libelleFr;
		this.libelleAr = libelleAr;
		this.oraganismeFr = oraganismeFr;
		this.oraganismeAr = oraganismeAr;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	@SequenceGenerator(name = "formation_id_seq_gen", sequenceName = "cursus.formation_id_seq")
	@Id
	@GeneratedValue(generator = "formation_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_releve_de_notes")
	public ReleveDeNotes getReleveDeNotes() {
		return this.releveDeNotes;
	}

	public void setReleveDeNotes(ReleveDeNotes releveDeNotes) {
		this.releveDeNotes = releveDeNotes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dossier_etudiant")
	public DossierEtudiant getDossierEtudiant() {
		return this.dossierEtudiant;
	}

	public void setDossierEtudiant(DossierEtudiant dossierEtudiant) {
		this.dossierEtudiant = dossierEtudiant;
	}

	@Column(name = "code", length = 30)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "ref_code_type_formation", length = 30)
	public String getRefCodeTypeFormation() {
		return this.refCodeTypeFormation;
	}

	public void setRefCodeTypeFormation(String refCodeTypeFormation) {
		this.refCodeTypeFormation = refCodeTypeFormation;
	}

	@Column(name = "ref_code_specialite")
	public String getRefCodeSpecialite() {
		return this.refCodeSpecialite;
	}

	public void setRefCodeSpecialite(String refCodeSpecialite) {
		this.refCodeSpecialite = refCodeSpecialite;
	}
	@Column(name = "libelle_fr")
	public String getLibelleFr() {
		return this.libelleFr;
	}

	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	@Column(name = "libelle_ar")
	public String getLibelleAr() {
		return this.libelleAr;
	}

	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	@Column(name = "oraganisme_fr")
	public String getOraganismeFr() {
		return this.oraganismeFr;
	}

	public void setOraganismeFr(String oraganismeFr) {
		this.oraganismeFr = oraganismeFr;
	}

	@Column(name = "oraganisme_ar")
	public String getOraganismeAr() {
		return this.oraganismeAr;
	}

	public void setOraganismeAr(String oraganismeAr) {
		this.oraganismeAr = oraganismeAr;
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

}
