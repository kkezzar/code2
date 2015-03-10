package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

// Generated 21 mai 2014 09:17:10 by Hibernate Tools 3.6.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * TitreAcces generated by hbm2java
 */
@Entity
@Table(name = "titre_acces", schema = "cursus")
public class TitreAcces implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 21 mai 2014  09:20:58
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private ReleveDeNotes releveDeNotes;
	private DossierEtudiant dossierEtudiant;
	private String numeroTitreAcces;
	private Double moyenne;
	private String refCodeLangueEtrangere1;
	private String refCodeLangueEtrangere2;
	private String refCodeTypeTitre;
	private String refCodeMention;
	private String refCodeSpecialite;
	private String libelleFr;
	private String libelleAr;
	private Short anneeObtention;
	private String etablissementObtentionFr;
	private String etablissementObtentionAr;
	private String equivalence;
	private Nomenclature pays;

	public TitreAcces() {
	}

	public TitreAcces(int id) {
		this.id = id;
	}

	public TitreAcces(int id, ReleveDeNotes releveDeNotes,
			DossierEtudiant dossierEtudiant, String numeroTitreAcces,
			Double moyenne, String refCodeLangueEtrangere1,
			String refCodeLangueEtrangere2, String refCodeTypeTitre,
			String refCodeMention, String refCodeSpecialite, String libelleFr,
			String libelleAr, Short anneeObtention,  String equivalence,
			String etablissementObtentionFr, String etablissementObtentionAr) {
		this.id = id;
		this.releveDeNotes = releveDeNotes;
		this.dossierEtudiant = dossierEtudiant;
		this.numeroTitreAcces = numeroTitreAcces;
		this.moyenne = moyenne;
		this.refCodeLangueEtrangere1 = refCodeLangueEtrangere1;
		this.refCodeLangueEtrangere2 = refCodeLangueEtrangere2;
		this.refCodeTypeTitre = refCodeTypeTitre;
		this.refCodeMention = refCodeMention;
		this.refCodeSpecialite = refCodeSpecialite;
		this.libelleFr = libelleFr;
		this.libelleAr = libelleAr;
		this.anneeObtention = anneeObtention;
		this.etablissementObtentionFr = etablissementObtentionFr;
		this.etablissementObtentionAr = etablissementObtentionAr;
		this.equivalence = equivalence;
	}

	@SequenceGenerator(name="titreAcces_id_seq_gen", sequenceName="cursus.titre_acces_id_seq")
	@Id @GeneratedValue(generator="titreAcces_id_seq_gen")
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dossier_etudiant")
	public DossierEtudiant getDossierEtudiant() {
		return this.dossierEtudiant;
	}

	public void setDossierEtudiant(DossierEtudiant dossierEtudiant) {
		this.dossierEtudiant = dossierEtudiant;
	}

	@Column(name = "numero_titre_acces")
	public String getNumeroTitreAcces() {
		return this.numeroTitreAcces;
	}

	public void setNumeroTitreAcces(String numeroTitreAcces) {
		this.numeroTitreAcces = numeroTitreAcces;
	}

	@Column(name = "moyenne", precision = 17, scale = 17)
	public Double getMoyenne() {
		return this.moyenne;
	}

	public void setMoyenne(Double moyenne) {
		this.moyenne = moyenne;
	}

	@Column(name = "ref_code_langue_etrangere1")
	public String getRefCodeLangueEtrangere1() {
		return this.refCodeLangueEtrangere1;
	}

	public void setRefCodeLangueEtrangere1(String refCodeLangueEtrangere1) {
		this.refCodeLangueEtrangere1 = refCodeLangueEtrangere1;
	}

	@Column(name = "ref_code_langue_etrangere2")
	public String getRefCodeLangueEtrangere2() {
		return this.refCodeLangueEtrangere2;
	}

	public void setRefCodeLangueEtrangere2(String refCodeLangueEtrangere2) {
		this.refCodeLangueEtrangere2 = refCodeLangueEtrangere2;
	}

	@Column(name = "ref_code_type_titre", length = 30)
	public String getRefCodeTypeTitre() {
		return this.refCodeTypeTitre;
	}

	public void setRefCodeTypeTitre(String refCodeTypeTitre) {
		this.refCodeTypeTitre = refCodeTypeTitre;
	}

	@Column(name = "ref_code_mention", length = 30)
	public String getRefCodeMention() {
		return this.refCodeMention;
	}

	public void setRefCodeMention(String refCodeMention) {
		this.refCodeMention = refCodeMention;
	}

	@Column(name = "ref_code_specialite", length = 30)
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

	@Column(name = "annee_obtention")
	public Short getAnneeObtention() {
		return this.anneeObtention;
	}

	public void setAnneeObtention(Short anneeObtention) {
		this.anneeObtention = anneeObtention;
	}

	@Column(name = "etablissement_obtention_fr")
	public String getEtablissementObtentionFr() {
		return this.etablissementObtentionFr;
	}

	public void setEtablissementObtentionFr(String etablissementObtentionFr) {
		this.etablissementObtentionFr = etablissementObtentionFr;
	}

	@Column(name = "etablissement_obtention_ar")
	public String getEtablissementObtentionAr() {
		return this.etablissementObtentionAr;
	}

	public void setEtablissementObtentionAr(String etablissementObtentionAr) {
		this.etablissementObtentionAr = etablissementObtentionAr;
	}
	
	@Column(name = "equivalence")
	public String getEquivalence() {
		return this.equivalence;
	}

	public void setEquivalence(String equivalence) {
		this.equivalence = equivalence;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nc_pays", nullable = true)
	public Nomenclature getPays() {
	    return pays;
	}

	public void setPays(Nomenclature pays) {
	    this.pays = pays;
	}
	
	

}
