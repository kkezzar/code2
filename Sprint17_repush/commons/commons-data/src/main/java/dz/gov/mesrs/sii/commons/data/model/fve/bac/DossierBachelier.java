package dz.gov.mesrs.sii.commons.data.model.fve.bac;

// Generated 22 mai 2014 12:17:42 by Hibernate Tools 3.6.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierEtudiant;

/**
 * DossierBachelier generated by hbm2java
 */
@Entity
@Table(name = "dossier_bachelier", schema = "bac", uniqueConstraints = @UniqueConstraint(columnNames = {"matricule" ,"id_importation"}))
public class DossierBachelier implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 12:19:28
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Importation importation;
	private Importation importationAffectation;
	private String matricule;
	private String nomAr;
	private String prenomAr;
	private String nomFr;
	private String prenomFr;
	private Date dateNaissance;
	private String moyenneBac;
	private String prenomPere;
	private String nomPrenomMere;
	private String telephone;
	private String adresseResidence;
	private String refCodeSexe;
	private String refCodeWilayaNaissance;
	private String refCodeWilayaBac;
	private String refCodeWilayaResidence;
	private String refCodeSerieBac;
	private String libelleVilleNaissance;
	private String libelleSerieBac;
	private Set<NotesBachelier> notesBacheliers = new HashSet<NotesBachelier>(0);
	private Set<FicheVoeuxBachelier> ficheVoeuxBacheliers = new HashSet<FicheVoeuxBachelier>(
			0);
	private DossierEtudiant dossierEtudiant;
	private String refCodeEtablissement;
	private String refCodeFiliere;
	private String numeroChoix;
	private String noteAffectation;
	private String refCodeEtablissementRecours;
	private String refCodeFiliereRecours;
	private String refCodeEtablissementOrientation;
	private String refCodeFiliereOrientation;
	private String refCodeTypeAffectation;
	private String refCodeEtablissementAffectation;
	private String refCodeFiliereAffectation;
	
	public DossierBachelier() {
	}

	public DossierBachelier(int id, Importation importation, String matricule,
			String nomAr, String prenomAr, String nomFr, String prenomFr,
			Date dateNaissance, String moyenneBac, String prenomPere,
			String nomPrenomMere, String refCodeWilayaNaissance,
			String refCodeWilayaBac, String refCodeWilayaResidence,
			String refCodeSerieBac) {
		this.id = id;
		this.importation = importation;
		this.matricule = matricule;
		this.nomAr = nomAr;
		this.prenomAr = prenomAr;
		this.nomFr = nomFr;
		this.prenomFr = prenomFr;
		this.dateNaissance = dateNaissance;
		this.moyenneBac = moyenneBac;
		this.prenomPere = prenomPere;
		this.nomPrenomMere = nomPrenomMere;
		this.refCodeWilayaNaissance = refCodeWilayaNaissance;
		this.refCodeWilayaBac = refCodeWilayaBac;
		this.refCodeWilayaResidence = refCodeWilayaResidence;
		this.refCodeSerieBac = refCodeSerieBac;
	}

	public DossierBachelier(int id, Importation importation, String matricule,
			String nomAr, String prenomAr, String nomFr, String prenomFr,
			Date dateNaissance, String moyenneBac, String prenomPere,
			String nomPrenomMere, String telephone, String adresseResidence,
			String refCodeSexe, String refCodeWilayaNaissance,
			String refCodeWilayaBac, String refCodeWilayaResidence,
			String refCodeSerieBac, String libelleVilleNaissance,
			Set<DossierEtudiant> dossierEtudiants,
			Set<NotesBachelier> notesBacheliers,
			Set<FicheVoeuxBachelier> ficheVoeuxBacheliers) {
		this.id = id;
		this.importation = importation;
		this.matricule = matricule;
		this.nomAr = nomAr;
		this.prenomAr = prenomAr;
		this.nomFr = nomFr;
		this.prenomFr = prenomFr;
		this.dateNaissance = dateNaissance;
		this.moyenneBac = moyenneBac;
		this.prenomPere = prenomPere;
		this.nomPrenomMere = nomPrenomMere;
		this.telephone = telephone;
		this.adresseResidence = adresseResidence;
		this.refCodeSexe = refCodeSexe;
		this.refCodeWilayaNaissance = refCodeWilayaNaissance;
		this.refCodeWilayaBac = refCodeWilayaBac;
		this.refCodeWilayaResidence = refCodeWilayaResidence;
		this.refCodeSerieBac = refCodeSerieBac;
		this.libelleVilleNaissance = libelleVilleNaissance;
		this.notesBacheliers = notesBacheliers;
		this.ficheVoeuxBacheliers = ficheVoeuxBacheliers;

	}

//	@SequenceGenerator(name = "dossier_bachelier_id_seq_gen", sequenceName = "bac.dossier_bachelier_id_seq")
	@Id
//	@GeneratedValue(generator = "dossier_bachelier_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_importation", nullable = false)
	public Importation getImportation() {
		return this.importation;
	}

	public void setImportation(Importation importation) {
		this.importation = importation;
	}

	/**
	 * [DossierBachelier.importationAffectation] Getter 
	 * @author  Rafik LAIB on : 1 ao�t 2014  14:50:13
	 * @return the importationAffectation
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_importation_affectation", nullable = false)
	public Importation getImportationAffectation() {
		return importationAffectation;
	}

	/**
	 * [DossierBachelier.importationAffectation] Setter 
	 * @author  Rafik LAIB on : 1 ao�t 2014  14:50:13
	 * @param importationAffectation the importationAffectation to set
	 */
	public void setImportationAffectation(Importation importationAffectation) {
		this.importationAffectation = importationAffectation;
	}

	@Column(name = "matricule", unique = true, nullable = false, length = 10)
	public String getMatricule() {
		return this.matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	@Column(name = "nom_ar", nullable = false, length = 50)
	public String getNomAr() {
		return this.nomAr;
	}

	public void setNomAr(String nomAr) {
		this.nomAr = nomAr;
	}

	@Column(name = "prenom_ar", nullable = false, length = 50)
	public String getPrenomAr() {
		return this.prenomAr;
	}

	public void setPrenomAr(String prenomAr) {
		this.prenomAr = prenomAr;
	}

	@Column(name = "nom_fr", nullable = false, length = 50)
	public String getNomFr() {
		return this.nomFr;
	}

	public void setNomFr(String nomFr) {
		this.nomFr = nomFr;
	}

	@Column(name = "prenom_fr", nullable = false, length = 50)
	public String getPrenomFr() {
		return this.prenomFr;
	}

	public void setPrenomFr(String prenomFr) {
		this.prenomFr = prenomFr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_naissance", nullable = false, length = 29)
	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	@Column(name = "moyenne_bac", nullable = false, length = 5)
	public String getMoyenneBac() {
		return this.moyenneBac;
	}

	public void setMoyenneBac(String moyenneBac) {
		this.moyenneBac = moyenneBac;
	}

	@Column(name = "prenom_pere", nullable = false, length = 50)
	public String getPrenomPere() {
		return this.prenomPere;
	}

	public void setPrenomPere(String prenomPere) {
		this.prenomPere = prenomPere;
	}

	@Column(name = "nom_prenom_mere", nullable = false, length = 50)
	public String getNomPrenomMere() {
		return this.nomPrenomMere;
	}

	public void setNomPrenomMere(String nomPrenomMere) {
		this.nomPrenomMere = nomPrenomMere;
	}

	@Column(name = "telephone", length = 20)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "adresse_residence", length = 150)
	public String getAdresseResidence() {
		return this.adresseResidence;
	}

	public void setAdresseResidence(String adresseResidence) {
		this.adresseResidence = adresseResidence;
	}

	@Column(name = "ref_code_sexe", length = 20)
	public String getRefCodeSexe() {
		return this.refCodeSexe;
	}

	public void setRefCodeSexe(String refCodeSexe) {
		this.refCodeSexe = refCodeSexe;
	}

	@Column(name = "ref_code_wilaya_naissance", nullable = false, length = 2)
	public String getRefCodeWilayaNaissance() {
		return this.refCodeWilayaNaissance;
	}

	public void setRefCodeWilayaNaissance(String refCodeWilayaNaissance) {
		this.refCodeWilayaNaissance = refCodeWilayaNaissance;
	}

	@Column(name = "ref_code_wilaya_bac", nullable = false, length = 2)
	public String getRefCodeWilayaBac() {
		return this.refCodeWilayaBac;
	}

	public void setRefCodeWilayaBac(String refCodeWilayaBac) {
		this.refCodeWilayaBac = refCodeWilayaBac;
	}

	@Column(name = "ref_code_wilaya_residence", nullable = false, length = 2)
	public String getRefCodeWilayaResidence() {
		return this.refCodeWilayaResidence;
	}

	public void setRefCodeWilayaResidence(String refCodeWilayaResidence) {
		this.refCodeWilayaResidence = refCodeWilayaResidence;
	}

	@Column(name = "ref_code_serie_bac", nullable = false, length = 5)
	public String getRefCodeSerieBac() {
		return this.refCodeSerieBac;
	}

	public void setRefCodeSerieBac(String refCodeSerieBac) {
		this.refCodeSerieBac = refCodeSerieBac;
	}

	@Column(name = "libelle_ville_naissance", length = 50)
	public String getLibelleVilleNaissance() {
		return this.libelleVilleNaissance;
	}

	public void setLibelleVilleNaissance(String libelleVilleNaissance) {
		this.libelleVilleNaissance = libelleVilleNaissance;
	}

	/**
	 * [DossierBachelier.libelleSerieBac] Getter
	 * 
	 * @author Rafik LAIB on : 25 mai 2014 16:27:00
	 * @return the libelleSerieBac
	 */
	@Column(name = "libelle_serie_bac", length = 150)
	public String getLibelleSerieBac() {
		return libelleSerieBac;
	}

	/**
	 * [DossierBachelier.libelleSerieBac] Setter
	 * 
	 * @author Rafik LAIB on : 25 mai 2014 16:27:00
	 * @param libelleSerieBac
	 *            the libelleSerieBac to set
	 */
	public void setLibelleSerieBac(String libelleSerieBac) {
		this.libelleSerieBac = libelleSerieBac;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dossierBachelier")
	public Set<NotesBachelier> getNotesBacheliers() {
		return this.notesBacheliers;
	}

	public void setNotesBacheliers(Set<NotesBachelier> notesBacheliers) {
		this.notesBacheliers = notesBacheliers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dossierBachelier")
	public Set<FicheVoeuxBachelier> getFicheVoeuxBacheliers() {
		return this.ficheVoeuxBacheliers;
	}

	public void setFicheVoeuxBacheliers(
			Set<FicheVoeuxBachelier> ficheVoeuxBacheliers) {
		this.ficheVoeuxBacheliers = ficheVoeuxBacheliers;
	}
	
	@Column(name = "ref_code_etablissement", length = 10)
	public String getRefCodeEtablissement() {
		return this.refCodeEtablissement;
	}

	public void setRefCodeEtablissement(String refCodeEtablissement) {
		this.refCodeEtablissement = refCodeEtablissement;
	}

	@Column(name = "ref_code_filiere", length = 10)
	public String getRefCodeFiliere() {
		return this.refCodeFiliere;
	}

	public void setRefCodeFiliere(String refCodeFiliere) {
		this.refCodeFiliere = refCodeFiliere;
	}

	@Column(name = "numero_choix", nullable = false)
	public String getNumeroChoix() {
		return this.numeroChoix;
	}

	public void setNumeroChoix(String numeroChoix) {
		this.numeroChoix = numeroChoix;
	}

	@Column(name = "note_affectation", precision = 17, scale = 17)
	public String getNoteAffectation() {
		return this.noteAffectation;
	}

	public void setNoteAffectation(String noteAffectation) {
		this.noteAffectation = noteAffectation;
	}

	@Column(name = "ref_code_etablissement_recours", length = 10)
	public String getRefCodeEtablissementRecours() {
		return this.refCodeEtablissementRecours;
	}

	public void setRefCodeEtablissementRecours(
			String refCodeEtablissementRecours) {
		this.refCodeEtablissementRecours = refCodeEtablissementRecours;
	}

	@Column(name = "ref_code_filiere_recours", length = 10)
	public String getRefCodeFiliereRecours() {
		return this.refCodeFiliereRecours;
	}

	public void setRefCodeFiliereRecours(String refCodeFiliereRecours) {
		this.refCodeFiliereRecours = refCodeFiliereRecours;
	}

	@Column(name = "ref_code_etablissement_orientation", length = 10)
	public String getRefCodeEtablissementOrientation() {
		return this.refCodeEtablissementOrientation;
	}

	public void setRefCodeEtablissementOrientation(
			String refCodeEtablissementOrientation) {
		this.refCodeEtablissementOrientation = refCodeEtablissementOrientation;
	}

	@Column(name = "ref_code_filiere_orientation", length = 10)
	public String getRefCodeFiliereOrientation() {
		return this.refCodeFiliereOrientation;
	}

	public void setRefCodeFiliereOrientation(String refCodeFiliereOrientation) {
		this.refCodeFiliereOrientation = refCodeFiliereOrientation;
	}

	@Column(name = "ref_code_type_affectation", length = 10)
	public String getRefCodeTypeAffectation() {
		return this.refCodeTypeAffectation;
	}

	public void setRefCodeTypeAffectation(String refCodeTypeAffectation) {
		this.refCodeTypeAffectation = refCodeTypeAffectation;
	}

	@Column(name = "ref_code_etablissement_affectation", length = 10)
	public String getRefCodeEtablissementAffectation() {
		return this.refCodeEtablissementAffectation;
	}

	public void setRefCodeEtablissementAffectation(
			String refCodeEtablissementAffectation) {
		this.refCodeEtablissementAffectation = refCodeEtablissementAffectation;
	}

	@Column(name = "ref_code_filiere_affectation", length = 10)
	public String getRefCodeFiliereAffectation() {
		return this.refCodeFiliereAffectation;
	}

	public void setRefCodeFiliereAffectation(String refCodeFiliereAffectation) {
		this.refCodeFiliereAffectation = refCodeFiliereAffectation;
	}

	/**
	 * [DossierBachelier.dossierEtudiant] Getter 
	 * @author rlaib on : 16 nov. 2014  12:06:53
	 * @return the dossierEtudiant
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy ="dossierBachelier")
	public DossierEtudiant getDossierEtudiant() {
		return dossierEtudiant;
	}

	/**
	 * [DossierBachelier.dossierEtudiant] Setter 
	 * @author rlaib on : 16 nov. 2014  12:06:53
	 * @param dossierEtudiant the dossierEtudiant to set
	 */
	public void setDossierEtudiant(DossierEtudiant dossierEtudiant) {
		this.dossierEtudiant = dossierEtudiant;
	}


	
}
