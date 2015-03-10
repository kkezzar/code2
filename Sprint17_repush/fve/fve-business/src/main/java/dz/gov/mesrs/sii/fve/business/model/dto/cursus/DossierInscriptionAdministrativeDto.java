package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.InscriptionExamenDto;

public class DossierInscriptionAdministrativeDto implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String numeroInscription;
	private Integer anneeAcademiqueId;
	private String anneeAcademiqueCode;
	private String refCodeTypeInscription;
	private String refCodeNatureInscription;
	private String refCodeStatutEtudiant;
	private String refLibelleTypeInscription;
	private String refLibelleNatureInscription;
	private String refLibelleStatutEtudiant;
	private Integer situationId;
	private String situationLibelleFr;
	private String situationLibelleAr;
	private Integer dossierId;
	private Integer dossierEtudiantId;
	private String numeroMatricule;
	private String refCodeDomaine;
	private String refCodeFiliere;
	private String refCodeSpecialite;
	private String resultRefCodeDomaine;
	private String resultRefCodeFiliere;
	private String resultRefCodeSpecialite;
	private String refLibelleDomaine;
	private String refLibelleFiliere;
	private String refLibelleSpecialite;
	private Integer ouvertureOffreFormationId;
	private Integer offreFormationId;
	private String offreFormationCode;
	private String offreFormationLibelleFr;
	private String offreFormationLibelleAr;
	private String refCodeCycle;
	private String refCodeNiveau;
	private String refLibelleCycle;
	private String refLibelleNiveau;
	private Date dateInscription;
	private String centreScolarite;
	private String typeDossier;
	private String typeDossierLibelleFr;
	private String typeDossierLibelleAr;
	private Date dateCreation;
	private String codeFiliere;
	private String codeDomaine;
	// Etudiant Individu
	private Integer individuId;
	private String nin;
	private String individuNomArabe;
	private String individuNomLatin;
	private String individuPrenomArabe;
	private String individuPrenomLatin;
	private Date individuDateNaissance;
	private String individuLieuNaissance;
	private String individuNationaliteLibelleLongFr;
	private String individuNationaliteLibelleLongAr;
	private String refLibelleNiveauArabe;
	private String refLibelleDomaineArabe;
	private String refLibelleFiliereArabe;
	private String refLibelleSpecialiteArabe;
	private Integer refEtablissementId;
	private String refCodeEtablissement;
	private String llEtablissementArabe;
	private String llEtablissementLatin;
	// wilaya de naissance
	private String individuibelleWilayaNaissance;
	private String refCodeWilayaNaissance;
	private String adresseResidence;
	// bac
	private String matriculeBac;
	private double moyenneBac;
	private double lastMoyenne;
	private String photo;
	// Cycle+niveau
	private Integer cycleId;
	private String cycleCode;
	private String cycleLibelleLongLt;
	private Integer niveauId;
	private String niveauCode;
	private Integer niveauRang;
	private String niveauLibelleLongLt;
	// Informations ONOU (bourse et hebergement
	private Boolean herbergementDemande;
	private Boolean bourseDemandee;
	// type de la demande d'hebergement
	private Integer idTypeDemandeHebergement;
	private String typeDemandeHebergementLibelleFr;
	private String typeDemandeHebergementLibelleAr;
	// private NomenclatureDto typeDemandeHebergement;
	private Integer idTypeDemandeBourse;
	private String typeDemandeBourseLibelleFr;
	private String typeDemandeBourseLibelleAr;
	// private NomenclatureDto typeDemandeBourse;
	private String observationScolariteHebergement;
	private String observationScolaritBourse;
	private Boolean herbergementAccorde;
	private Boolean bourseAccordee;
	// type d'hebergement
	private Integer idTypeHebergement;
	private String typeHebergementLibelleFr;
	private String typeHebergementLibelleAr;
	// private NomenclatureDto typeHebergement;
	private String lieuHebergement;
	private Integer ancienneteHebergement;
	private String observationOnouHebergement;
	private String observationOnouBourse;
	private Float montantBourse;
	private Integer ancienneteBourse;
	private Boolean decisionOnouValide;
	private Date dateDecisionOnou;
	private String etudiantSexe;
	private String etudiantCivilite;
	private List<BilanSessionDto> bilanSessionDtos;
	private List<DossierInscriptionAdministrativeDto> dossierInscriptionAdministrativeDtos;
	private List<InscriptionExamenDto> inscriptionExamenDtos;
	private List<AffectationGroupePedagogiqueDto> affectationGroupePedagogiqueDtos;
	private String groupePedagogiqueIntitule;

	public DossierInscriptionAdministrativeDto() {
	}

	public String getRefLibelleTypeInscription() {
		return refLibelleTypeInscription;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setRefLibelleTypeInscription(String refLibelleTypeInscription) {
		this.refLibelleTypeInscription = refLibelleTypeInscription;
	}

	public String getRefLibelleNatureInscription() {
		return refLibelleNatureInscription;
	}

	public void setRefLibelleNatureInscription(
			String refLibelleNatureInscription) {
		this.refLibelleNatureInscription = refLibelleNatureInscription;
	}

	public String getRefLibelleStatutEtudiant() {
		return refLibelleStatutEtudiant;
	}

	public void setRefLibelleStatutEtudiant(String refLibelleStatutEtudiant) {
		this.refLibelleStatutEtudiant = refLibelleStatutEtudiant;
	}

	public String getRefLibelleDomaine() {
		return refLibelleDomaine;
	}

	public void setRefLibelleDomaine(String refLibelleDomaine) {
		this.refLibelleDomaine = refLibelleDomaine;
	}

	public String getRefLibelleFiliere() {
		return refLibelleFiliere;
	}

	public void setRefLibelleFiliere(String refLibelleFiliere) {
		this.refLibelleFiliere = refLibelleFiliere;
	}

	public String getRefLibelleSpecialite() {
		return refLibelleSpecialite;
	}

	public void setRefLibelleSpecialite(String refLibelleSpecialite) {
		this.refLibelleSpecialite = refLibelleSpecialite;
	}

	public String getRefLibelleCycle() {
		return refLibelleCycle;
	}

	public void setRefLibelleCycle(String refLibelleCycle) {
		this.refLibelleCycle = refLibelleCycle;
	}

	public String getRefLibelleNiveau() {
		return refLibelleNiveau;
	}

	public void setRefLibelleNiveau(String refLibelleNiveau) {
		this.refLibelleNiveau = refLibelleNiveau;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroInscription() {
		return numeroInscription;
	}

	public void setNumeroInscription(String numeroInscription) {
		this.numeroInscription = numeroInscription;
	}

	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}

	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	public String getRefCodeTypeInscription() {
		return refCodeTypeInscription;
	}

	public void setRefCodeTypeInscription(String refCodeTypeInscription) {
		this.refCodeTypeInscription = refCodeTypeInscription;
	}

	public String getRefCodeNatureInscription() {
		return refCodeNatureInscription;
	}

	public void setRefCodeNatureInscription(String refCodeNatureInscription) {
		this.refCodeNatureInscription = refCodeNatureInscription;
	}

	public String getRefCodeStatutEtudiant() {
		return refCodeStatutEtudiant;
	}

	public void setRefCodeStatutEtudiant(String refCodeStatutEtudiant) {
		this.refCodeStatutEtudiant = refCodeStatutEtudiant;
	}

	public Integer getDossierId() {
		return dossierId;
	}

	public void setDossierId(Integer dossierId) {
		this.dossierId = dossierId;
	}

	public String getNumeroMatricule() {
		return numeroMatricule;
	}

	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}

	public String getRefCodeDomaine() {
		return refCodeDomaine;
	}

	public void setRefCodeDomaine(String refCodeDomaine) {
		this.refCodeDomaine = refCodeDomaine;
	}

	public String getRefCodeFiliere() {
		return refCodeFiliere;
	}

	public void setRefCodeFiliere(String refCodeFiliere) {
		this.refCodeFiliere = refCodeFiliere;
	}

	public String getRefCodeSpecialite() {
		return refCodeSpecialite;
	}

	public void setRefCodeSpecialite(String refCodeSpecialite) {
		this.refCodeSpecialite = refCodeSpecialite;
	}

	public Integer getOuvertureOffreFormationId() {
		return ouvertureOffreFormationId;
	}

	public void setOuvertureOffreFormationId(Integer ouvertureOffreFormationId) {
		this.ouvertureOffreFormationId = ouvertureOffreFormationId;
	}

	public Integer getOffreFormationId() {
		return offreFormationId;
	}

	public void setOffreFormationId(Integer offreFormationId) {
		this.offreFormationId = offreFormationId;
	}

	public String getOffreFormationLibelleFr() {
		return offreFormationLibelleFr;
	}

	public void setOffreFormationLibelleFr(String offreFormationLibelleFr) {
		this.offreFormationLibelleFr = offreFormationLibelleFr;
	}

	public String getOffreFormationLibelleAr() {
		return offreFormationLibelleAr;
	}

	public void setOffreFormationLibelleAr(String offreFormationLibelleAr) {
		this.offreFormationLibelleAr = offreFormationLibelleAr;
	}

	public String getRefCodeCycle() {
		return refCodeCycle;
	}

	public void setRefCodeCycle(String refCodeCycle) {
		this.refCodeCycle = refCodeCycle;
	}

	public String getRefCodeNiveau() {
		return refCodeNiveau;
	}

	public void setRefCodeNiveau(String refCodeNiveau) {
		this.refCodeNiveau = refCodeNiveau;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getCentreScolarite() {
		return centreScolarite;
	}

	public void setCentreScolarite(String centreScolarite) {
		this.centreScolarite = centreScolarite;
	}

	public Integer getSituationId() {
		return situationId;
	}

	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}

	public String getSituationLibelleFr() {
		return situationLibelleFr;
	}

	public void setSituationLibelleFr(String situationLibelleFr) {
		this.situationLibelleFr = situationLibelleFr;
	}

	public String getSituationLibelleAr() {
		return situationLibelleAr;
	}

	public void setSituationLibelleAr(String situationLibelleAr) {
		this.situationLibelleAr = situationLibelleAr;
	}

	public Integer getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	public void setDossierEtudiantId(Integer dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	public String getTypeDossier() {
		return typeDossier;
	}

	public void setTypeDossier(String typeDossier) {
		this.typeDossier = typeDossier;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.individuId] Getter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:04:31
	 * @return the individuId
	 */
	public Integer getIndividuId() {
		return individuId;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.individuId] Setter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:04:31
	 * @param individuId
	 *            the individuId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.nin] Getter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:04:31
	 * @return the nin
	 */
	public String getNin() {
		return nin;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.nin] Setter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:04:31
	 * @param nin
	 *            the nin to set
	 */
	public void setNin(String nin) {
		this.nin = nin;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.individuNomArabe] Getter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:04:31
	 * @return the individuNomArabe
	 */
	public String getIndividuNomArabe() {
		return individuNomArabe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.individuNomArabe] Setter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:04:31
	 * @param individuNomArabe
	 *            the individuNomArabe to set
	 */
	public void setIndividuNomArabe(String individuNomArabe) {
		this.individuNomArabe = individuNomArabe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.individuNomLatin] Getter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:04:31
	 * @return the individuNomLatin
	 */
	public String getIndividuNomLatin() {
		return individuNomLatin;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.individuNomLatin] Setter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:04:31
	 * @param individuNomLatin
	 *            the individuNomLatin to set
	 */
	public void setIndividuNomLatin(String individuNomLatin) {
		this.individuNomLatin = individuNomLatin;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.individuPrenomArabe] Getter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:04:31
	 * @return the individuPrenomArabe
	 */
	public String getIndividuPrenomArabe() {
		return individuPrenomArabe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.individuPrenomArabe] Setter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:04:31
	 * @param individuPrenomArabe
	 *            the individuPrenomArabe to set
	 */
	public void setIndividuPrenomArabe(String individuPrenomArabe) {
		this.individuPrenomArabe = individuPrenomArabe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.individuPrenomLatin] Getter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:04:31
	 * @return the individuPrenomLatin
	 */
	public String getIndividuPrenomLatin() {
		return individuPrenomLatin;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.individuPrenomLatin] Setter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:04:31
	 * @param individuPrenomLatin
	 *            the individuPrenomLatin to set
	 */
	public void setIndividuPrenomLatin(String individuPrenomLatin) {
		this.individuPrenomLatin = individuPrenomLatin;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.individuDateNaissance] Getter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:04:31
	 * @return the individuDateNaissance
	 */
	public Date getIndividuDateNaissance() {
		return individuDateNaissance;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.individuDateNaissance] Setter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:04:31
	 * @param individuDateNaissance
	 *            the individuDateNaissance to set
	 */
	public void setIndividuDateNaissance(Date individuDateNaissance) {
		this.individuDateNaissance = individuDateNaissance;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.refLibelleNiveauArabe] Getter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 10:34:43
	 * @return the refLibelleNiveauArabe
	 */
	public String getRefLibelleNiveauArabe() {
		return refLibelleNiveauArabe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.refLibelleNiveauArabe] Setter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 10:34:43
	 * @param refLibelleNiveauArabe
	 *            the refLibelleNiveauArabe to set
	 */
	public void setRefLibelleNiveauArabe(String refLibelleNiveauArabe) {
		this.refLibelleNiveauArabe = refLibelleNiveauArabe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.refLibelleDomaineArabe] Getter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 10:38:47
	 * @return the refLibelleDomaineArabe
	 */
	public String getRefLibelleDomaineArabe() {
		return refLibelleDomaineArabe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.refLibelleDomaineArabe] Setter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 10:38:47
	 * @param refLibelleDomaineArabe
	 *            the refLibelleDomaineArabe to set
	 */
	public void setRefLibelleDomaineArabe(String refLibelleDomaineArabe) {
		this.refLibelleDomaineArabe = refLibelleDomaineArabe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.refLibelleFiliereArabe] Getter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 10:38:47
	 * @return the refLibelleFiliereArabe
	 */
	public String getRefLibelleFiliereArabe() {
		return refLibelleFiliereArabe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.refLibelleFiliereArabe] Setter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 10:38:47
	 * @param refLibelleFiliereArabe
	 *            the refLibelleFiliereArabe to set
	 */
	public void setRefLibelleFiliereArabe(String refLibelleFiliereArabe) {
		this.refLibelleFiliereArabe = refLibelleFiliereArabe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.refLibelleSpecialiteArabe] Getter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 10:38:47
	 * @return the refLibelleSpecialiteArabe
	 */
	public String getRefLibelleSpecialiteArabe() {
		return refLibelleSpecialiteArabe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.refLibelleSpecialiteArabe] Setter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 10:38:47
	 * @param refLibelleSpecialiteArabe
	 *            the refLibelleSpecialiteArabe to set
	 */
	public void setRefLibelleSpecialiteArabe(String refLibelleSpecialiteArabe) {
		this.refLibelleSpecialiteArabe = refLibelleSpecialiteArabe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.individuLieuNaissance] Getter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 16:06:56
	 * @return the individuLieuNaissance
	 */
	public String getIndividuLieuNaissance() {
		return individuLieuNaissance;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.individuLieuNaissance] Setter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 16:06:56
	 * @param individuLieuNaissance
	 *            the individuLieuNaissance to set
	 */
	public void setIndividuLieuNaissance(String individuLieuNaissance) {
		this.individuLieuNaissance = individuLieuNaissance;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.refCodeEtablissement] Getter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 18:42:24
	 * @return the refCodeEtablissement
	 */
	public String getRefCodeEtablissement() {
		return refCodeEtablissement;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.refCodeEtablissement] Setter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 18:42:24
	 * @param refCodeEtablissement
	 *            the refCodeEtablissement to set
	 */
	public void setRefCodeEtablissement(String refCodeEtablissement) {
		this.refCodeEtablissement = refCodeEtablissement;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.llEtablissementArabe] Getter
	 * 
	 * @author BELDI Jamel on : 23 juin 2014 18:57:41
	 * @return the llEtablissementArabe
	 */
	public String getLlEtablissementArabe() {
		return llEtablissementArabe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.llEtablissementArabe] Setter
	 * 
	 * @author BELDI Jamel on : 23 juin 2014 18:57:41
	 * @param llEtablissementArabe
	 *            the llEtablissementArabe to set
	 */
	public void setLlEtablissementArabe(String llEtablissementArabe) {
		this.llEtablissementArabe = llEtablissementArabe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.llEtablissementLatin] Getter
	 * 
	 * @author BELDI Jamel on : 23 juin 2014 18:57:41
	 * @return the llEtablissementLatin
	 */
	public String getLlEtablissementLatin() {
		return llEtablissementLatin;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.llEtablissementLatin] Setter
	 * 
	 * @author BELDI Jamel on : 23 juin 2014 18:57:41
	 * @param llEtablissementLatin
	 *            the llEtablissementLatin to set
	 */
	public void setLlEtablissementLatin(String llEtablissementLatin) {
		this.llEtablissementLatin = llEtablissementLatin;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.codeFiliere] Getter
	 * 
	 * @author rlaib on : 24 juin 2014 16:26:57
	 * @return the codeFiliere
	 */
	public String getCodeFiliere() {
		return codeFiliere;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.codeFiliere] Setter
	 * 
	 * @author rlaib on : 24 juin 2014 16:26:57
	 * @param codeFiliere
	 *            the codeFiliere to set
	 */
	public void setCodeFiliere(String codeFiliere) {
		this.codeFiliere = codeFiliere;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.codeDomaine] Getter
	 * 
	 * @author rlaib on : 24 juin 2014 16:26:57
	 * @return the codeDomaine
	 */
	public String getCodeDomaine() {
		return codeDomaine;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.codeDomaine] Setter
	 * 
	 * @author rlaib on : 24 juin 2014 16:26:57
	 * @param codeDomaine
	 *            the codeDomaine to set
	 */
	public void setCodeDomaine(String codeDomaine) {
		this.codeDomaine = codeDomaine;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.matriculeBac] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 juin 2014 11:38:06
	 * @return the matriculeBac
	 */
	public String getMatriculeBac() {
		return matriculeBac;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.matriculeBac] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 juin 2014 11:38:06
	 * @param matriculeBac
	 *            the matriculeBac to set
	 */
	public void setMatriculeBac(String matriculeBac) {
		this.matriculeBac = matriculeBac;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.offreFormationCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 juil. 2014 14:21:14
	 * @return the offreFormationCode
	 */
	public String getOffreFormationCode() {
		return offreFormationCode;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.offreFormationCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 juil. 2014 14:21:14
	 * @param offreFormationCode
	 *            the offreFormationCode to set
	 */
	public void setOffreFormationCode(String offreFormationCode) {
		this.offreFormationCode = offreFormationCode;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.resultRefCodeDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 juil. 2014 11:44:10
	 * @return the resultRefCodeDomaine
	 */
	public String getResultRefCodeDomaine() {
		return resultRefCodeDomaine;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.resultRefCodeDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 juil. 2014 11:44:10
	 * @param resultRefCodeDomaine
	 *            the resultRefCodeDomaine to set
	 */
	public void setResultRefCodeDomaine(String resultRefCodeDomaine) {
		this.resultRefCodeDomaine = resultRefCodeDomaine;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.resultRefCodeFiliere] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 juil. 2014 11:44:10
	 * @return the resultRefCodeFiliere
	 */
	public String getResultRefCodeFiliere() {
		return resultRefCodeFiliere;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.resultRefCodeFiliere] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 juil. 2014 11:44:10
	 * @param resultRefCodeFiliere
	 *            the resultRefCodeFiliere to set
	 */
	public void setResultRefCodeFiliere(String resultRefCodeFiliere) {
		this.resultRefCodeFiliere = resultRefCodeFiliere;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.resultRefCodeSpecialite] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 juil. 2014 11:44:10
	 * @return the resultRefCodeSpecialite
	 */
	public String getResultRefCodeSpecialite() {
		return resultRefCodeSpecialite;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.resultRefCodeSpecialite] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 juil. 2014 11:44:10
	 * @param resultRefCodeSpecialite
	 *            the resultRefCodeSpecialite to set
	 */
	public void setResultRefCodeSpecialite(String resultRefCodeSpecialite) {
		this.resultRefCodeSpecialite = resultRefCodeSpecialite;
	}

	public String getIndividuibelleWilayaNaissance() {
		return individuibelleWilayaNaissance;
	}

	public void setIndividuibelleWilayaNaissance(
			String individuibelleWilayaNaissance) {
		this.individuibelleWilayaNaissance = individuibelleWilayaNaissance;
	}

	public String getRefCodeWilayaNaissance() {
		return refCodeWilayaNaissance;
	}

	public void setRefCodeWilayaNaissance(String refCodeWilayaNaissance) {
		this.refCodeWilayaNaissance = refCodeWilayaNaissance;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.adresseResidence] Getter
	 * 
	 * @author BELDI Jamel on : 6 aoï¿½t 2014 19:06:46
	 * @return the adresseResidence
	 */
	public String getAdresseResidence() {
		return adresseResidence;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.adresseResidence] Setter
	 * 
	 * @author BELDI Jamel on : 6 aoï¿½t 2014 19:06:46
	 * @param adresseResidence
	 *            the adresseResidence to set
	 */
	public void setAdresseResidence(String adresseResidence) {
		this.adresseResidence = adresseResidence;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.cycleId] Getter
	 * 
	 * @author BELDI Jamel on : 15 sept. 2014 09:56:38
	 * @return the cycleId
	 */
	public Integer getCycleId() {
		return cycleId;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.cycleId] Setter
	 * 
	 * @author BELDI Jamel on : 15 sept. 2014 09:56:38
	 * @param cycleId
	 *            the cycleId to set
	 */
	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.cycleCode] Getter
	 * 
	 * @author BELDI Jamel on : 15 sept. 2014 09:56:38
	 * @return the cycleCode
	 */
	public String getCycleCode() {
		return cycleCode;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.cycleCode] Setter
	 * 
	 * @author BELDI Jamel on : 15 sept. 2014 09:56:38
	 * @param cycleCode
	 *            the cycleCode to set
	 */
	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.cycleLibelleLongLt] Getter
	 * 
	 * @author BELDI Jamel on : 15 sept. 2014 09:56:38
	 * @return the cycleLibelleLongLt
	 */
	public String getCycleLibelleLongLt() {
		return cycleLibelleLongLt;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.cycleLibelleLongLt] Setter
	 * 
	 * @author BELDI Jamel on : 15 sept. 2014 09:56:38
	 * @param cycleLibelleLongLt
	 *            the cycleLibelleLongLt to set
	 */
	public void setCycleLibelleLongLt(String cycleLibelleLongLt) {
		this.cycleLibelleLongLt = cycleLibelleLongLt;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.niveauId] Getter
	 * 
	 * @author BELDI Jamel on : 15 sept. 2014 09:56:38
	 * @return the niveauId
	 */
	public Integer getNiveauId() {
		return niveauId;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.niveauId] Setter
	 * 
	 * @author BELDI Jamel on : 15 sept. 2014 09:56:38
	 * @param niveauId
	 *            the niveauId to set
	 */
	public void setNiveauId(Integer niveauId) {
		this.niveauId = niveauId;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.niveauCode] Getter
	 * 
	 * @author BELDI Jamel on : 15 sept. 2014 09:56:38
	 * @return the niveauCode
	 */
	public String getNiveauCode() {
		return niveauCode;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.niveauCode] Setter
	 * 
	 * @author BELDI Jamel on : 15 sept. 2014 09:56:38
	 * @param niveauCode
	 *            the niveauCode to set
	 */
	public void setNiveauCode(String niveauCode) {
		this.niveauCode = niveauCode;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.niveauLibelleLongLt] Getter
	 * 
	 * @author BELDI Jamel on : 15 sept. 2014 09:56:38
	 * @return the niveauLibelleLongLt
	 */
	public String getNiveauLibelleLongLt() {
		return niveauLibelleLongLt;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.niveauLibelleLongLt] Setter
	 * 
	 * @author BELDI Jamel on : 15 sept. 2014 09:56:38
	 * @param niveauLibelleLongLt
	 *            the niveauLibelleLongLt to set
	 */
	public void setNiveauLibelleLongLt(String niveauLibelleLongLt) {
		this.niveauLibelleLongLt = niveauLibelleLongLt;
	}

	public String getIndividuNationaliteLibelleLongFr() {
		return individuNationaliteLibelleLongFr;
	}

	public void setIndividuNationaliteLibelleLongFr(
			String individuNationaliteLibelleLongFr) {
		this.individuNationaliteLibelleLongFr = individuNationaliteLibelleLongFr;
	}

	public String getIndividuNationaliteLibelleLongAr() {
		return individuNationaliteLibelleLongAr;
	}

	public void setIndividuNationaliteLibelleLongAr(
			String individuNationaliteLibelleLongAr) {
		this.individuNationaliteLibelleLongAr = individuNationaliteLibelleLongAr;
	}

	public Boolean getHerbergementDemande() {
		return herbergementDemande;
	}

	public Integer getIdTypeDemandeHebergement() {
		return idTypeDemandeHebergement;
	}

	public void setIdTypeDemandeHebergement(Integer idTypeDemandeHebergement) {
		this.idTypeDemandeHebergement = idTypeDemandeHebergement;
	}

	public String getTypeDemandeHebergementLibelleFr() {
		return typeDemandeHebergementLibelleFr;
	}

	public void setTypeDemandeHebergementLibelleFr(
			String typeDemandeHebergementLibelleFr) {
		this.typeDemandeHebergementLibelleFr = typeDemandeHebergementLibelleFr;
	}

	public String getTypeDemandeHebergementLibelleAr() {
		return typeDemandeHebergementLibelleAr;
	}

	public void setTypeDemandeHebergementLibelleAr(
			String typeDemandeHebergementLibelleAr) {
		this.typeDemandeHebergementLibelleAr = typeDemandeHebergementLibelleAr;
	}

	public Integer getIdTypeDemandeBourse() {
		return idTypeDemandeBourse;
	}

	public void setIdTypeDemandeBourse(Integer idTypeDemandeBourse) {
		this.idTypeDemandeBourse = idTypeDemandeBourse;
	}

	public String getTypeDemandeBourseLibelleFr() {
		return typeDemandeBourseLibelleFr;
	}

	public void setTypeDemandeBourseLibelleFr(String typeDemandeBourseLibelleFr) {
		this.typeDemandeBourseLibelleFr = typeDemandeBourseLibelleFr;
	}

	public String getTypeDemandeBourseLibelleAr() {
		return typeDemandeBourseLibelleAr;
	}

	public void setTypeDemandeBourseLibelleAr(String typeDemandeBourseLibelleAr) {
		this.typeDemandeBourseLibelleAr = typeDemandeBourseLibelleAr;
	}

	public Integer getIdTypeHebergement() {
		return idTypeHebergement;
	}

	public void setIdTypeHebergement(Integer idTypeHebergement) {
		this.idTypeHebergement = idTypeHebergement;
	}

	public String getTypeHebergementLibelleFr() {
		return typeHebergementLibelleFr;
	}

	public void setTypeHebergementLibelleFr(String typeHebergementLibelleFr) {
		this.typeHebergementLibelleFr = typeHebergementLibelleFr;
	}

	public String getTypeHebergementLibelleAr() {
		return typeHebergementLibelleAr;
	}

	public void setTypeHebergementLibelleAr(String typeHebergementLibelleAr) {
		this.typeHebergementLibelleAr = typeHebergementLibelleAr;
	}

	public void setHerbergementDemande(Boolean herbergementDemande) {
		this.herbergementDemande = herbergementDemande;
	}

	public Boolean getBourseDemandee() {
		return bourseDemandee;
	}

	public void setBourseDemandee(Boolean bourseDemandee) {
		this.bourseDemandee = bourseDemandee;
	}

	public String getObservationScolariteHebergement() {
		return observationScolariteHebergement;
	}

	public void setObservationScolariteHebergement(
			String observationScolariteHebergement) {
		this.observationScolariteHebergement = observationScolariteHebergement;
	}

	public String getObservationScolaritBourse() {
		return observationScolaritBourse;
	}

	public void setObservationScolaritBourse(String observationScolaritBourse) {
		this.observationScolaritBourse = observationScolaritBourse;
	}

	public Boolean getHerbergementAccorde() {
		return herbergementAccorde;
	}

	public void setHerbergementAccorde(Boolean herbergementAccorde) {
		this.herbergementAccorde = herbergementAccorde;
	}

	public Boolean getBourseAccordee() {
		return bourseAccordee;
	}

	public void setBourseAccordee(Boolean bourseAccordee) {
		this.bourseAccordee = bourseAccordee;
	}

	public String getLieuHebergement() {
		return lieuHebergement;
	}

	public void setLieuHebergement(String lieuHebergement) {
		this.lieuHebergement = lieuHebergement;
	}

	public String getObservationOnouHebergement() {
		return observationOnouHebergement;
	}

	public void setObservationOnouHebergement(String observationOnouHebergement) {
		this.observationOnouHebergement = observationOnouHebergement;
	}

	public String getObservationOnouBourse() {
		return observationOnouBourse;
	}

	public void setObservationOnouBourse(String observationOnouBourse) {
		this.observationOnouBourse = observationOnouBourse;
	}

	public Float getMontantBourse() {
		return montantBourse;
	}

	public void setMontantBourse(Float montantBourse) {
		this.montantBourse = montantBourse;
	}

	public Boolean getDecisionOnouValide() {
		return decisionOnouValide;
	}

	public void setDecisionOnouValide(Boolean decisionOnouValide) {
		this.decisionOnouValide = decisionOnouValide;
	}

	public Date getDateDecisionOnou() {
		return dateDecisionOnou;
	}

	public void setDateDecisionOnou(Date dateDecisionOnou) {
		this.dateDecisionOnou = dateDecisionOnou;
	}

	public Integer getAncienneteHebergement() {
		return ancienneteHebergement;
	}

	public void setAncienneteHebergement(Integer ancienneteHebergement) {
		this.ancienneteHebergement = ancienneteHebergement;
	}

	public Integer getAncienneteBourse() {
		return ancienneteBourse;
	}

	public void setAncienneteBourse(Integer ancienneteBourse) {
		this.ancienneteBourse = ancienneteBourse;
	}

	public String getTypeDossierLibelleFr() {
		return typeDossierLibelleFr;
	}

	public void setTypeDossierLibelleFr(String typeDossierLibelleFr) {
		this.typeDossierLibelleFr = typeDossierLibelleFr;
	}

	public String getTypeDossierLibelleAr() {
		return typeDossierLibelleAr;
	}

	public void setTypeDossierLibelleAr(String typeDossierLibelleAr) {
		this.typeDossierLibelleAr = typeDossierLibelleAr;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.bilanSessionDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 dÃ©c. 2014 09:32:47
	 * @return the bilanSessionDtos
	 */
	public List<BilanSessionDto> getBilanSessionDtos() {
		return bilanSessionDtos;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.bilanSessionDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 dÃ©c. 2014 09:32:47
	 * @param bilanSessionDtos
	 *            the bilanSessionDtos to set
	 */
	public void setBilanSessionDtos(List<BilanSessionDto> bilanSessionDtos) {
		this.bilanSessionDtos = bilanSessionDtos;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.dossierInscriptionAdministrativeDtos
	 * ] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 dÃ©c. 2014 11:13:51
	 * @return the dossierInscriptionAdministrativeDtos
	 */
	public List<DossierInscriptionAdministrativeDto> getDossierInscriptionAdministrativeDtos() {
		return dossierInscriptionAdministrativeDtos;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.dossierInscriptionAdministrativeDtos
	 * ] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 dÃ©c. 2014 11:13:51
	 * @param dossierInscriptionAdministrativeDtos
	 *            the dossierInscriptionAdministrativeDtos to set
	 */
	public void setDossierInscriptionAdministrativeDtos(
			List<DossierInscriptionAdministrativeDto> dossierInscriptionAdministrativeDtos) {
		this.dossierInscriptionAdministrativeDtos = dossierInscriptionAdministrativeDtos;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.niveauRang] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 dÃ©c. 2014 09:07:40
	 * @return the niveauRang
	 */
	public Integer getNiveauRang() {
		return niveauRang;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.niveauRang] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 dÃ©c. 2014 09:07:40
	 * @param niveauRang
	 *            the niveauRang to set
	 */
	public void setNiveauRang(Integer niveauRang) {
		this.niveauRang = niveauRang;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.refEtablissementId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 déc. 2014 12:25:18
	 * @return the refEtablissementId
	 */
	public Integer getRefEtablissementId() {
		return refEtablissementId;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.refEtablissementId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 déc. 2014 12:25:18
	 * @param refEtablissementId
	 *            the refEtablissementId to set
	 */
	public void setRefEtablissementId(Integer refEtablissementId) {
		this.refEtablissementId = refEtablissementId;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.inscriptionExamenDtos] Getter 
	 * @author MAKERRI Sofiane on : 4 janv. 2015  17:10:38
	 * @return the inscriptionExamenDtos
	 */
	public List<InscriptionExamenDto> getInscriptionExamenDtos() {
		return inscriptionExamenDtos;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.inscriptionExamenDtos] Setter 
	 * @author MAKERRI Sofiane on : 4 janv. 2015  17:10:38
	 * @param inscriptionExamenDtos the inscriptionExamenDtos to set
	 */
	public void setInscriptionExamenDtos(
			List<InscriptionExamenDto> inscriptionExamenDtos) {
		this.inscriptionExamenDtos = inscriptionExamenDtos;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.affectationGroupePedagogiqueDtos] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  08:05:45
	 * @return the affectationGroupePedagogiqueDtos
	 */
	public List<AffectationGroupePedagogiqueDto> getAffectationGroupePedagogiqueDtos() {
		return affectationGroupePedagogiqueDtos;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.affectationGroupePedagogiqueDtos] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  08:05:45
	 * @param affectationGroupePedagogiqueDtos the affectationGroupePedagogiqueDtos to set
	 */
	public void setAffectationGroupePedagogiqueDtos(
			List<AffectationGroupePedagogiqueDto> affectationGroupePedagogiqueDtos) {
		this.affectationGroupePedagogiqueDtos = affectationGroupePedagogiqueDtos;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.etudiantSexe] Getter 
	 * @author MAKERRI Sofiane on : 25 janv. 2015  16:16:55
	 * @return the etudiantSexe
	 */
	public String getEtudiantSexe() {
		if(etudiantCivilite != null && etudiantCivilite.toLowerCase().equals("monsieur")) {
			etudiantSexe = "Masculin";
		} else {
			etudiantSexe = "Féminin";
		}
		return etudiantSexe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.etudiantSexe] Setter 
	 * @author MAKERRI Sofiane on : 25 janv. 2015  16:16:55
	 * @param etudiantSexe the etudiantSexe to set
	 */
	public void setEtudiantSexe(String etudiantSexe) {
		this.etudiantSexe = etudiantSexe;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.etudiantCivilite] Getter 
	 * @author MAKERRI Sofiane on : 25 janv. 2015  17:18:53
	 * @return the etudiantCivilite
	 */
	public String getEtudiantCivilite() {
		return etudiantCivilite;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.etudiantCivilite] Setter 
	 * @author MAKERRI Sofiane on : 25 janv. 2015  17:18:53
	 * @param etudiantCivilite the etudiantCivilite to set
	 */
	public void setEtudiantCivilite(String etudiantCivilite) {
		this.etudiantCivilite = etudiantCivilite;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.moyenneBac] Getter 
	 * @author MAKERRI Sofiane on : 28 janv. 2015  10:20:59
	 * @return the moyenneBac
	 */
	public double getMoyenneBac() {
		return moyenneBac;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.moyenneBac] Setter 
	 * @author MAKERRI Sofiane on : 28 janv. 2015  10:20:59
	 * @param moyenneBac the moyenneBac to set
	 */
	public void setMoyenneBac(double moyenneBac) {
		this.moyenneBac = moyenneBac;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.lastMoyenne] Getter 
	 * @author MAKERRI Sofiane on : 28 janv. 2015  11:43:39
	 * @return the lastMoyenne
	 */
	public double getLastMoyenne() {
		return lastMoyenne;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.lastMoyenne] Setter 
	 * @author MAKERRI Sofiane on : 28 janv. 2015  11:43:39
	 * @param lastMoyenne the lastMoyenne to set
	 */
	public void setLastMoyenne(double lastMoyenne) {
		this.lastMoyenne = lastMoyenne;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.groupePedagogiqueIntitule] Getter 
	 * @author MAKERRI Sofiane on : 1 févr. 2015  13:04:55
	 * @return the groupePedagogiqueIntitule
	 */
	public String getGroupePedagogiqueIntitule() {
		return groupePedagogiqueIntitule;
	}

	/**
	 * [DossierInscriptionAdministrativeDto.groupePedagogiqueIntitule] Setter 
	 * @author MAKERRI Sofiane on : 1 févr. 2015  13:04:55
	 * @param groupePedagogiqueIntitule the groupePedagogiqueIntitule to set
	 */
	public void setGroupePedagogiqueIntitule(String groupePedagogiqueIntitule) {
		this.groupePedagogiqueIntitule = groupePedagogiqueIntitule;
	}
	
	
	
	

}
