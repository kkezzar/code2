package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

// Generated 20 mai 2014 17:49:05 by Hibernate Tools 3.6.0

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;

/**
 * 
 */

public class VoeuEtudiantDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MESSAOUDI Mounir on : 28 septembre 2014 09:00:31
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;

	private Integer dossierInscriptionId;
	private Integer dossierEtudiantId;
	private String dossierEtudiantMatricule;

	// Individu
	private Integer individuId;
	private String individuNin;
	private String individuNomArabe;
	private String individuNomLatin;
	private String individuPrenomArabe;
	private String individuPrenomLatin;
	private Date individuDateNaissance;
	private String individuLieuNaissance;
	private String individuNationaliteLibelleLongFr;
	private String individuNationaliteLibelleLongAr;

	// private Dossier dossier;
	private String typeDossier;
	private Date dateCreation;

	// private AnneeAcademique anneeAcademique;
	private Integer anneeAcademiqueId;
	private Short anneeAcademiquePremiereAnnee;
	private Short anneeAcademiqueDeuxiemeAnnee;
	private String anneeAcademiqueCode;

	// type de reinscription(nomencalture)
	private Integer typeReinscriptionId;
	private String typeReinscriptionCode;
	private String typeReinscriptionLibelleFr;
	private String typeReinscriptionLibelleAr;

	private List<VoeuEtudiantChoixDto> voeuxEtudiantsChoixDto;

	private OuvertureOffreFormationDto ouvertureOffreFormation;
	private RefDomaineLMDDto domaine;
	private RefFiliereLmdDto filiere;
	private RefSpecialiteLmdDto specialite;

	// private DossierInscriptionAdministrative ancienDossierInsAdmin;
	private Integer idAncienDossierInsAdmin;

	// private DossierInscriptionAdministrative nouveauDossierInsAdmin;
	private Integer idNouveauDossierInsAdmin;

	private NiveauDto niveau;
	private CycleDto cycle;

	private String observationEtudiant;
	private String observationEquipePedagogique;
	private String observationReinscription;

	// Situation
	private Integer situationId;
	private String codeSituation;
	private String situationLibelleFr;
	private String situationLibelleAr;

	private RefEtablissementDto etablissement;

	/**
	 * @author Mounir.MESSAOUDI on : 28 sept. 2014 16:53:49
	 */
	public VoeuEtudiantDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDossierInscriptionId() {
		return dossierInscriptionId;
	}

	public void setDossierInscriptionId(Integer dossierInscriptionId) {
		this.dossierInscriptionId = dossierInscriptionId;
	}

	public Integer getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	public void setDossierEtudiantId(Integer dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	public String getDossierEtudiantMatricule() {
		return dossierEtudiantMatricule;
	}

	public void setDossierEtudiantMatricule(String dossierEtudiantMatricule) {
		this.dossierEtudiantMatricule = dossierEtudiantMatricule;
	}

	public Integer getIndividuId() {
		return individuId;
	}

	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}

	public String getIndividuNin() {
		return individuNin;
	}

	public void setIndividuNin(String individuNin) {
		this.individuNin = individuNin;
	}

	public String getIndividuNomArabe() {
		return individuNomArabe;
	}

	public void setIndividuNomArabe(String individuNomArabe) {
		this.individuNomArabe = individuNomArabe;
	}

	public String getIndividuNomLatin() {
		return individuNomLatin;
	}

	public void setIndividuNomLatin(String individuNomLatin) {
		this.individuNomLatin = individuNomLatin;
	}

	public String getIndividuPrenomArabe() {
		return individuPrenomArabe;
	}

	public void setIndividuPrenomArabe(String individuPrenomArabe) {
		this.individuPrenomArabe = individuPrenomArabe;
	}

	public String getIndividuPrenomLatin() {
		return individuPrenomLatin;
	}

	public void setIndividuPrenomLatin(String individuPrenomLatin) {
		this.individuPrenomLatin = individuPrenomLatin;
	}

	public Date getIndividuDateNaissance() {
		return individuDateNaissance;
	}

	public void setIndividuDateNaissance(Date individuDateNaissance) {
		this.individuDateNaissance = individuDateNaissance;
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

	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	public Short getAnneeAcademiquePremiereAnnee() {
		return anneeAcademiquePremiereAnnee;
	}

	public void setAnneeAcademiquePremiereAnnee(
			Short anneeAcademiquePremiereAnnee) {
		this.anneeAcademiquePremiereAnnee = anneeAcademiquePremiereAnnee;
	}

	public Short getAnneeAcademiqueDeuxiemeAnnee() {
		return anneeAcademiqueDeuxiemeAnnee;
	}

	public void setAnneeAcademiqueDeuxiemeAnnee(
			Short anneeAcademiqueDeuxiemeAnnee) {
		this.anneeAcademiqueDeuxiemeAnnee = anneeAcademiqueDeuxiemeAnnee;
	}

	public String getAnneeAcademiqueCode() {
		return anneeAcademiquePremiereAnnee.toString() + "/"
				+ anneeAcademiqueDeuxiemeAnnee.toString();
	}

	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	public String getTypeReinscriptionLibelleFr() {
		return typeReinscriptionLibelleFr;
	}

	public void setTypeReinscriptionLibelleFr(String typeReinscriptionLibelleFr) {
		this.typeReinscriptionLibelleFr = typeReinscriptionLibelleFr;
	}

	public String getTypeReinscriptionLibelleAr() {
		return typeReinscriptionLibelleAr;
	}

	public void setTypeReinscriptionLibelleAr(String typeReinscriptionLibelleAr) {
		this.typeReinscriptionLibelleAr = typeReinscriptionLibelleAr;
	}

	public Integer getIdAncienDossierInsAdmin() {
		return idAncienDossierInsAdmin;
	}

	public void setIdAncienDossierInsAdmin(Integer idAncienDossierInsAdmin) {
		this.idAncienDossierInsAdmin = idAncienDossierInsAdmin;
	}

	public Integer getIdNouveauDossierInsAdmin() {
		return idNouveauDossierInsAdmin;
	}

	public void setIdNouveauDossierInsAdmin(Integer idNouveauDossierInsAdmin) {
		this.idNouveauDossierInsAdmin = idNouveauDossierInsAdmin;
	}

	public String getObservationEtudiant() {
		return observationEtudiant;
	}

	public void setObservationEtudiant(String observationEtudiant) {
		this.observationEtudiant = observationEtudiant;
	}

	public String getObservationEquipePedagogique() {
		return observationEquipePedagogique;
	}

	public void setObservationEquipePedagogique(
			String observationEquipePedagogique) {
		this.observationEquipePedagogique = observationEquipePedagogique;
	}

	public String getObservationReinscription() {
		return observationReinscription;
	}

	public void setObservationReinscription(String observationReinscription) {
		this.observationReinscription = observationReinscription;
	}

	public Integer getSituationId() {
		return situationId;
	}

	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}

	public String getCodeSituation() {
		return codeSituation;
	}

	public void setCodeSituation(String codeSituation) {
		this.codeSituation = codeSituation;
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

	public OuvertureOffreFormationDto getOuvertureOffreFormation() {
		return ouvertureOffreFormation;
	}

	public void setOuvertureOffreFormation(
			OuvertureOffreFormationDto ouvertureOffreFormation) {
		this.ouvertureOffreFormation = ouvertureOffreFormation;
	}

	public RefDomaineLMDDto getDomaine() {
		return domaine;
	}

	public void setDomaine(RefDomaineLMDDto domaine) {
		this.domaine = domaine;
	}

	public RefFiliereLmdDto getFiliere() {
		return filiere;
	}

	public void setFiliere(RefFiliereLmdDto filiere) {
		this.filiere = filiere;
	}

	public RefSpecialiteLmdDto getSpecialite() {
		return specialite;
	}

	public void setSpecialite(RefSpecialiteLmdDto specialite) {
		this.specialite = specialite;
	}

	public NiveauDto getNiveau() {
		return niveau;
	}

	public void setNiveau(NiveauDto niveau) {
		this.niveau = niveau;
	}

	public CycleDto getCycle() {
		return cycle;
	}

	public void setCycle(CycleDto cycle) {
		this.cycle = cycle;
	}

	public List<VoeuEtudiantChoixDto> getVoeuxEtudiantsChoixDto() {
		return voeuxEtudiantsChoixDto;
	}

	public void setVoeuxEtudiantsChoixDto(
			List<VoeuEtudiantChoixDto> voeuxEtudiantsChoixDto) {
		this.voeuxEtudiantsChoixDto = voeuxEtudiantsChoixDto;
	}

	public RefEtablissementDto getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(RefEtablissementDto etablissement) {
		this.etablissement = etablissement;
	}

	public String getIndividuLieuNaissance() {
		return individuLieuNaissance;
	}

	public void setIndividuLieuNaissance(String individuLieuNaissance) {
		this.individuLieuNaissance = individuLieuNaissance;
	}

	public Integer getTypeReinscriptionId() {
		return typeReinscriptionId;
	}

	public void setTypeReinscriptionId(Integer typeReinscriptionId) {
		this.typeReinscriptionId = typeReinscriptionId;
	}

	public String getTypeReinscriptionCode() {
		return typeReinscriptionCode;
	}

	public void setTypeReinscriptionCode(String typeReinscriptionCode) {
		this.typeReinscriptionCode = typeReinscriptionCode;
	}
}