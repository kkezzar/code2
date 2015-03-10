package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanSession;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.InscriptionExamen;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Niveau;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

@Entity
@Table(name = "dossier_inscription_administrative", schema = "cursus")
public class DossierInscriptionAdministrative implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Dossier dossier;
	private DossierEtudiant dossierEtudiant;
	private String numeroInscription;
	private AnneeAcademique anneeAcademique;
	private OuvertureOffreFormation ouvertureOffreFormation;
	private String refCodeTypeInscription;
	private String refCodeNatureInscription;
	private String refCodeStatutEtudiant;
	private Date dateInscription;
	private String centreScolarite;
	private String refCodeFiliere;
	private String refCodeDomaine;

	private Niveau niveau;

	// Informations ONOU (bourse et hebergement
	private Boolean herbergementDemande;
	private Boolean bourseDemandee;

	private Nomenclature typeDemandeHebergement;
	private Nomenclature typeDemandeBourse;

	private String observationScolariteHebergement;
	private String observationScolaritBourse;

	private Boolean herbergementAccorde;
	private Boolean bourseAccordee;

	private Nomenclature typeHebergement;
	private String lieuHebergement;

	private Integer ancienneteHebergement;

	private String observationOnouHebergement;
	private String observationOnouBourse;

	private Float montantBourse;
	private Integer ancienneteBourse;

	private Boolean decisionOnouValide;
	private Date dateDecisionOnou;
	private List<BilanSession> bilanSessions;
	//private List<InscriptionExamen> inscriptionExamens;
	//private List<AffectationGroupePedagogique> affectationGroupePedagogiques;
	private RefEtablissement refEtablissement;

	public DossierInscriptionAdministrative() {
	}

	public DossierInscriptionAdministrative(Dossier dossier) {
		this.dossier = dossier;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ouverture_of")
	public OuvertureOffreFormation getOuvertureOffreFormation() {
		return this.ouvertureOffreFormation;
	}

	public void setOuvertureOffreFormation(
			OuvertureOffreFormation ouvertureOffreFormation) {
		this.ouvertureOffreFormation = ouvertureOffreFormation;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Dossier getDossier() {
		return this.dossier;
	}

	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}

	// TODO le cascade =CascadeType.PERSIST pose un probleme l'ors de
	// l'enregistrement d'un dossier d'insc pour dossier d'etudiant qui existe
	// deja
	// a revoir la partie generation des dossiers etudiants
	// @ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.PERSIST)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dossier_etudiant")
	public DossierEtudiant getDossierEtudiant() {
		return this.dossierEtudiant;
	}

	public void setDossierEtudiant(DossierEtudiant dossierEtudiant) {
		this.dossierEtudiant = dossierEtudiant;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_annee_academique")
	public AnneeAcademique getAnneeAcademique() {
		return anneeAcademique;
	}

	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	@Column(name = "numero_inscription")
	public String getNumeroInscription() {
		return this.numeroInscription;
	}

	public void setNumeroInscription(String numeroInscription) {
		this.numeroInscription = numeroInscription;
	}

	@Column(name = "ref_code_type_inscription")
	public String getRefCodeTypeInscription() {
		return this.refCodeTypeInscription;
	}

	public void setRefCodeTypeInscription(String refCodeTypeInscription) {
		this.refCodeTypeInscription = refCodeTypeInscription;
	}

	@Column(name = "ref_code_nature_inscription")
	public String getRefCodeNatureInscription() {
		return this.refCodeNatureInscription;
	}

	public void setRefCodeNatureInscription(String refCodeNatureInscription) {
		this.refCodeNatureInscription = refCodeNatureInscription;
	}

	@Column(name = "ref_code_statut_etudiant")
	public String getRefCodeStatutEtudiant() {
		return this.refCodeStatutEtudiant;
	}

	public void setRefCodeStatutEtudiant(String refCodeStatutEtudiant) {
		this.refCodeStatutEtudiant = refCodeStatutEtudiant;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_inscription", length = 35)
	public Date getDateInscription() {
		return this.dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	@Column(name = "centre_scolarite")
	public String getCentreScolarite() {
		return this.centreScolarite;
	}

	public void setCentreScolarite(String centreScolarite) {
		this.centreScolarite = centreScolarite;
	}

	/**
	 * [DossierInscriptionAdministrative.refCodeFiliere] Getter
	 * 
	 * @author rlaib on : 25 juin 2014 10:18:15
	 * @return the refCodeFiliere
	 */
	@Column(name = "ref_code_filiere")
	public String getRefCodeFiliere() {
		return refCodeFiliere;
	}

	/**
	 * [DossierInscriptionAdministrative.refCodeFiliere] Setter
	 * 
	 * @author rlaib on : 25 juin 2014 10:18:15
	 * @param refCodeFiliere
	 *            the refCodeFiliere to set
	 */
	public void setRefCodeFiliere(String refCodeFiliere) {
		this.refCodeFiliere = refCodeFiliere;
	}

	/**
	 * [DossierInscriptionAdministrative.refCodeDomaine] Getter
	 * 
	 * @author rlaib on : 25 juin 2014 10:18:15
	 * @return the refCodeDomaine
	 */
	@Column(name = "ref_code_domaine")
	public String getRefCodeDomaine() {
		return refCodeDomaine;
	}

	/**
	 * [DossierInscriptionAdministrative.refCodeDomaine] Setter
	 * 
	 * @author rlaib on : 25 juin 2014 10:18:15
	 * @param refCodeDomaine
	 *            the refCodeDomaine to set
	 */
	public void setRefCodeDomaine(String refCodeDomaine) {
		this.refCodeDomaine = refCodeDomaine;
	}

	

	/**
	 * 
	 * [DossierInscriptionAdministrative.getNiveau] Getter
	 * 
	 * @author BELDI Jamel on : 15 sept. 2014 09:46:09
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_niveau")
	public Niveau getNiveau() {
		return this.niveau;
	}

	/**
	 * 
	 * [DossierInscriptionAdministrative.setNiveau] Getter
	 * 
	 * @author BELDI Jamel on : 15 sept. 2014 09:46:21
	 * @param niveau
	 */
	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	@Column(name = "herbergement_demande")
	public Boolean getHerbergementDemande() {
		return herbergementDemande;
	}

	public void setHerbergementDemande(Boolean herbergementDemande) {
		this.herbergementDemande = herbergementDemande;
	}

	@Column(name = "bourse_demandee")
	public Boolean getBourseDemandee() {
		return bourseDemandee;
	}

	public void setBourseDemandee(Boolean bourseDemandee) {
		this.bourseDemandee = bourseDemandee;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_demande_hebergment", nullable = true, insertable = false, updatable = false)
	public Nomenclature getTypeDemandeHebergement() {
		return typeDemandeHebergement;
	}

	public void setTypeDemandeHebergement(Nomenclature typeDemandeHebergement) {
		this.typeDemandeHebergement = typeDemandeHebergement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_demande_bourse", nullable = true, insertable = false, updatable = false)
	public Nomenclature getTypeDemandeBourse() {
		return typeDemandeBourse;
	}

	public void setTypeDemandeBourse(Nomenclature typeDemandeBourse) {
		this.typeDemandeBourse = typeDemandeBourse;
	}

	@Column(name = "observation_scolarite_hebergement")
	public String getObservationScolariteHebergement() {
		return observationScolariteHebergement;
	}

	public void setObservationScolariteHebergement(
			String observationScolariteHebergement) {
		this.observationScolariteHebergement = observationScolariteHebergement;
	}

	@Column(name = "observation_scolarite_bourse")
	public String getObservationScolaritBourse() {
		return observationScolaritBourse;
	}

	public void setObservationScolaritBourse(String observationScolaritBourse) {
		this.observationScolaritBourse = observationScolaritBourse;
	}

	@Column(name = "herbergement_accorde")
	public Boolean getHerbergementAccorde() {
		return herbergementAccorde;
	}

	public void setHerbergementAccorde(Boolean herbergementAccorde) {
		this.herbergementAccorde = herbergementAccorde;
	}

	@Column(name = "bourse_accordee")
	public Boolean getBourseAccordee() {
		return bourseAccordee;
	}

	public void setBourseAccordee(Boolean bourseAccordee) {
		this.bourseAccordee = bourseAccordee;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_hebergement", nullable = true, insertable = false, updatable = false)
	public Nomenclature getTypeHebergement() {
		return typeHebergement;
	}

	public void setTypeHebergement(Nomenclature typeHebergement) {
		this.typeHebergement = typeHebergement;
	}

	@Column(name = "lieu_hebergement")
	public String getLieuHebergement() {
		return lieuHebergement;
	}

	public void setLieuHebergement(String lieuHebergement) {
		this.lieuHebergement = lieuHebergement;
	}

	@Column(name = "anciennete_hebergement")
	public Integer getAncienneteHebergement() {
		return ancienneteHebergement;
	}

	public void setAncienneteHebergement(Integer ancienneteHebergement) {
		this.ancienneteHebergement = ancienneteHebergement;
	}

	@Column(name = "observation_onou_hebergement")
	public String getObservationOnouHebergement() {
		return observationOnouHebergement;
	}

	public void setObservationOnouHebergement(String observationOnouHebergement) {
		this.observationOnouHebergement = observationOnouHebergement;
	}

	@Column(name = "observation_onou_bourse")
	public String getObservationOnouBourse() {
		return observationOnouBourse;
	}

	public void setObservationOnouBourse(String observationOnouBourse) {
		this.observationOnouBourse = observationOnouBourse;
	}

	@Column(name = "montant_bourse")
	public Float getMontantBourse() {
		return montantBourse;
	}

	public void setMontantBourse(Float montantBourse) {
		this.montantBourse = montantBourse;
	}

	@Column(name = "anciennete_bourse")
	public Integer getAncienneteBourse() {
		return ancienneteBourse;
	}

	public void setAncienneteBourse(Integer ancienneteBourse) {
		this.ancienneteBourse = ancienneteBourse;
	}

	@Column(name = "decision_onou_valide")
	public Boolean getDecisionOnouValide() {
		return decisionOnouValide;
	}

	public void setDecisionOnouValide(Boolean decisionOnouValide) {
		this.decisionOnouValide = decisionOnouValide;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_decision_onou", length = 35)
	public Date getDateDecisionOnou() {
		return dateDecisionOnou;
	}

	public void setDateDecisionOnou(Date dateDecisionOnou) {
		this.dateDecisionOnou = dateDecisionOnou;
	}

	/**
	 * [DossierInscriptionAdministrative.bilanSessions] Getter 
	 * @author MAKERRI Sofiane on : 25 dÃ©c. 2014  09:29:16
	 * @return the bilanSessions
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dossierInscriptionAdministrative", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<BilanSession> getBilanSessions() {
		return bilanSessions;
	}

	/**
	 * [DossierInscriptionAdministrative.bilanSessions] Setter 
	 * @author MAKERRI Sofiane on : 25 dÃ©c. 2014  09:29:16
	 * @param bilanSessions the bilanSessions to set
	 */
	public void setBilanSessions(List<BilanSession> bilanSessions) {
		this.bilanSessions = bilanSessions;
	}

	/**
	 * [DossierInscriptionAdministrative.refEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 30 déc. 2014  12:23:37
	 * @return the refEtablissement
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_etablissement")
	public RefEtablissement getRefEtablissement() {
		return refEtablissement;
	}

	/**
	 * [DossierInscriptionAdministrative.refEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 30 déc. 2014  12:23:37
	 * @param refEtablissement the refEtablissement to set
	 */
	public void setRefEtablissement(RefEtablissement refEtablissement) {
		this.refEtablissement = refEtablissement;
	}

	/**
	 * [DossierInscriptionAdministrative.inscriptionExamens] Getter 
	 * @author MAKERRI Sofiane on : 4 janv. 2015  17:12:00
	 * @return the inscriptionExamens
	 */
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dossierInscriptionAdministrative", cascade = CascadeType.ALL, orphanRemoval = true)
//	public List<InscriptionExamen> getInscriptionExamens() {
//		return inscriptionExamens;
//	}

	/**
	 * [DossierInscriptionAdministrative.inscriptionExamens] Setter 
	 * @author MAKERRI Sofiane on : 4 janv. 2015  17:12:00
	 * @param inscriptionExamens the inscriptionExamens to set
	 */
//	public void setInscriptionExamens(List<InscriptionExamen> inscriptionExamens) {
//		this.inscriptionExamens = inscriptionExamens;
//	}

	/**
	 * [DossierInscriptionAdministrative.affectationGroupePedagogiques] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  08:04:34
	 * @return the affectationGroupePedagogiques
	 */
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dossierInscriptionAdministrative", cascade = CascadeType.ALL, orphanRemoval = true)
//	public List<AffectationGroupePedagogique> getAffectationGroupePedagogiques() {
//		return affectationGroupePedagogiques;
//	}

	/**
	 * [DossierInscriptionAdministrative.affectationGroupePedagogiques] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  08:04:34
	 * @param affectationGroupePedagogiques the affectationGroupePedagogiques to set
	 */
//	public void setAffectationGroupePedagogiques(
//			List<AffectationGroupePedagogique> affectationGroupePedagogiques) {
//		this.affectationGroupePedagogiques = affectationGroupePedagogiques;
//	}
	
	
	
	

}
