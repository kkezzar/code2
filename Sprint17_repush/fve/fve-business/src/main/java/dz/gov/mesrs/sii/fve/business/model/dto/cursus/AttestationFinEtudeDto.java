package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AttestationFinEtudeEdition;



public class AttestationFinEtudeDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String identifiant;
	private Integer idEtablissement;
	private Date dateObtention;
	private Boolean estValide;
	private Boolean estValideEtab;
	private Boolean estDelivre;
	private Date dateDerniereEdition;
	private Integer idCompteAgent;
	private String refTypeImpression;
	private String observation;
	private String observationEtab;
	private String equivalence;
	private String refMinistere;
	private Date dateSignatureMinistere;
	private Date dateSignatureEtablissement;
	private String autoriteMinistere;
	private String autoriteEtablissement;
	private Set<AttestationFinEtudeEdition> attestationFinEtudeEditions = new HashSet<AttestationFinEtudeEdition>(0);
	List<AttestationFinEtudeEditionDto> attestationFinEtudeEditionList;
	private int idAnneAcademique;
	private Date dateCreation;
	private Boolean estValideFac;
	private Date dateSignatureFaculte;
	private Boolean generationValide;
	private String refEtab;
	private String refFac;
	private String autoriteFac;
	private String observationFac;
	
	//Bilan session
	private Long bilanSessionId;
	private Integer bilanSessionDeliberationSessionId;
	private Integer bilanSessionDossierInscriptionAdministrativeId;
	private Integer bilanSessionTypeDecisionId;
	private Integer bilanSessionAnneeAcademiqueId;
	private String bilanSessionrefCodeTypeSession;
	
	//nomenclature
	private String refCodeAttestation;
	private Integer mentionId;
	private String  mentionCode;
	private String  mentionLibelleFr;
	private String  mentionLibelleAr;
	
    	// private AnneeAcademique anneeAcademique;
		private Integer anneeAcademiqueId;
		private String anneeAcademiqueCode;
		
		//cycle + niveau
		private Integer cycleId;
		private String  cycleCode;
		private String  cycleLibelleLongLt;
		private String  cycleLibelleLongAr;
		private Integer niveauId;
		private String  niveauCode;
		private String  niveauLibelleLongLt;


		//private DossierInscriptionAdministrative dossierInscriptionAdministrative;
		private Integer dossierInscriptionAdministrativeId;
		private String  numeroInscription;
		private Integer dossierEtudiantId;
		private String  numeroMatricule;
		private String  photo;
		private String anneeAcademiqueInscriptionCode;
		private Integer dossierId;
		private String refCodeDomaine;
		private String refCodeFiliere;
		private String refCodeSpecialite;
		private String resultRefCodeDomaine;
		private String resultRefCodeFiliere;
		private String resultRefCodeSpecialite;
		private String refLibelleDomaine;
		private String refLibelleFiliere;
		private String refLibelleSpecialite;
		
		//Offre de formation
		private Integer ouvertureOffreFormationId;
		private Integer offreFormationId;
		private String  offreFormationCode;
		private String  offreFormationLibelleFr;
		private String  offreFormationLibelleAr;
		private String  offreFormationrefCodeTypeFormation;
		private String  refCodeTypeFormation;
		
		// Etudiant Individu
		private Integer individuId;
		private String nin;
		private String individuNomArabe;
		private String individuNomLatin;
		private String individuPrenomArabe;
		private String individuPrenomLatin;
		private Date individuDateNaissance;
		private String individuLieuNaissance;
		private String refLibelleNiveauArabe;
		private String refLibelleDomaineArabe;
		private String refLibelleFiliereArabe;
		private String refLibelleSpecialiteArabe;
		private String llEtablissementArabe;
		private String llEtablissementLatin;
		
		//Etablissement
		
	

	public AttestationFinEtudeDto() {
	}


	/**
	 * [attestationFinEtudeDto.id] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * [attestationFinEtudeDto.id] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * [attestationFinEtudeDto.idEtablissement] Getter 
	 * @author BELBRIK Oualid on : 1 nov. 2014  17:36:33
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}


	/**
	 * [attestationFinEtudeDto.idEtablissement] Setter 
	 * @author BELBRIK Oualid on : 1 nov. 2014  17:36:33
	 * @param idEtablissement the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}


	/**
	 * [attestationFinEtudeDto.refCodeattestation] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @return the refCodeattestation
	 */
	public String getRefCodeAttestation() {
		return refCodeAttestation;
	}


	/**
	 * [attestationFinEtudeDto.refCodeattestation] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @param refCodeattestation the refCodeattestation to set
	 */
	public void setRefCodeAttestation(String refCodeAttestation) {
		this.refCodeAttestation = refCodeAttestation;
	}


	

	/**
	 * [attestationFinEtudeDto.dateObtention] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @return the dateObtention
	 */
	public Date getDateObtention() {
		return dateObtention;
	}


	/**
	 * [attestationFinEtudeDto.dateObtention] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @param dateObtention the dateObtention to set
	 */
	public void setDateObtention(Date dateObtention) {
		this.dateObtention = dateObtention;
	}


	/**
	 * [attestationFinEtudeDto.estValide] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @return the estValide
	 */
	public Boolean getEstValide() {
		return estValide;
	}


	/**
	 * [attestationFinEtudeDto.estValide] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @param estValide the estValide to set
	 */
	public void setEstValide(Boolean estValide) {
		this.estValide = estValide;
	}


	/**
	 * [attestationFinEtudeDto.estDelivre] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @return the estDelivre
	 */
	public Boolean getEstDelivre() {
		return estDelivre;
	}


	/**
	 * [attestationFinEtudeDto.estDelivre] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @param estDelivre the estDelivre to set
	 */
	public void setEstDelivre(Boolean estDelivre) {
		this.estDelivre = estDelivre;
	}


	/**
	 * [attestationFinEtudeDto.dateDerniereEdition] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @return the dateDerniereEdition
	 */
	public Date getDateDerniereEdition() {
		return dateDerniereEdition;
	}


	/**
	 * [attestationFinEtudeDto.dateDerniereEdition] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @param dateDerniereEdition the dateDerniereEdition to set
	 */
	public void setDateDerniereEdition(Date dateDerniereEdition) {
		this.dateDerniereEdition = dateDerniereEdition;
	}


	
	/**
	 * [attestationFinEtudeDto.idCompteAgent] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @return the idCompteAgent
	 */
	public Integer getIdCompteAgent() {
		return idCompteAgent;
	}


	/**
	 * [attestationFinEtudeDto.idCompteAgent] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @param idCompteAgent the idCompteAgent to set
	 */
	public void setIdCompteAgent(Integer idCompteAgent) {
		this.idCompteAgent = idCompteAgent;
	}


	/**
	 * [attestationFinEtudeDto.refTypeImpression] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @return the refTypeImpression
	 */
	public String getRefTypeImpression() {
		return refTypeImpression;
	}


	/**
	 * [attestationFinEtudeDto.refTypeImpression] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @param refTypeImpression the refTypeImpression to set
	 */
	public void setRefTypeImpression(String refTypeImpression) {
		this.refTypeImpression = refTypeImpression;
	}


	/**
	 * [attestationFinEtudeDto.observation] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}


	/**
	 * [attestationFinEtudeDto.observation] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:32:20
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}


	/**
	 * [attestationFinEtudeDto.anneeAcademiqueId] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}


	/**
	 * [attestationFinEtudeDto.anneeAcademiqueId] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param anneeAcademiqueId the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}


	/**
	 * [attestationFinEtudeDto.anneeAcademiqueCode] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}


	/**
	 * [attestationFinEtudeDto.anneeAcademiqueCode] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param anneeAcademiqueCode the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}


	

	/**
	 * [attestationFinEtudeDto.cycleId] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the cycleId
	 */
	public Integer getCycleId() {
		return cycleId;
	}


	/**
	 * [attestationFinEtudeDto.cycleId] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param cycleId the cycleId to set
	 */
	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}


	/**
	 * [attestationFinEtudeDto.cycleCode] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the cycleCode
	 */
	public String getCycleCode() {
		return cycleCode;
	}


	/**
	 * [attestationFinEtudeDto.cycleCode] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param cycleCode the cycleCode to set
	 */
	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}


	/**
	 * [attestationFinEtudeDto.cycleLibelleLongLt] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the cycleLibelleLongLt
	 */
	public String getCycleLibelleLongLt() {
		return cycleLibelleLongLt;
	}


	/**
	 * [attestationFinEtudeDto.cycleLibelleLongLt] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param cycleLibelleLongLt the cycleLibelleLongLt to set
	 */
	public void setCycleLibelleLongLt(String cycleLibelleLongLt) {
		this.cycleLibelleLongLt = cycleLibelleLongLt;
	}


	/**
	 * [attestationFinEtudeDto.niveauId] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the niveauId
	 */
	public Integer getNiveauId() {
		return niveauId;
	}


	/**
	 * [attestationFinEtudeDto.niveauId] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param niveauId the niveauId to set
	 */
	public void setNiveauId(Integer niveauId) {
		this.niveauId = niveauId;
	}


	/**
	 * [attestationFinEtudeDto.niveauCode] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the niveauCode
	 */
	public String getNiveauCode() {
		return niveauCode;
	}


	/**
	 * [attestationFinEtudeDto.niveauCode] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param niveauCode the niveauCode to set
	 */
	public void setNiveauCode(String niveauCode) {
		this.niveauCode = niveauCode;
	}


	/**
	 * [attestationFinEtudeDto.niveauLibelleLongLt] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the niveauLibelleLongLt
	 */
	public String getNiveauLibelleLongLt() {
		return niveauLibelleLongLt;
	}


	/**
	 * [attestationFinEtudeDto.niveauLibelleLongLt] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param niveauLibelleLongLt the niveauLibelleLongLt to set
	 */
	public void setNiveauLibelleLongLt(String niveauLibelleLongLt) {
		this.niveauLibelleLongLt = niveauLibelleLongLt;
	}


	/**
	 * [attestationFinEtudeDto.dossierInscriptionAdministrativeId] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the dossierInscriptionAdministrativeId
	 */
	public Integer getDossierInscriptionAdministrativeId() {
		return dossierInscriptionAdministrativeId;
	}


	/**
	 * [attestationFinEtudeDto.dossierInscriptionAdministrativeId] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param dossierInscriptionAdministrativeId the dossierInscriptionAdministrativeId to set
	 */
	public void setDossierInscriptionAdministrativeId(
			Integer dossierInscriptionAdministrativeId) {
		this.dossierInscriptionAdministrativeId = dossierInscriptionAdministrativeId;
	}


	/**
	 * [attestationFinEtudeDto.numeroInscription] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the numeroInscription
	 */
	public String getNumeroInscription() {
		return numeroInscription;
	}


	/**
	 * [attestationFinEtudeDto.numeroInscription] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param numeroInscription the numeroInscription to set
	 */
	public void setNumeroInscription(String numeroInscription) {
		this.numeroInscription = numeroInscription;
	}


	/**
	 * [attestationFinEtudeDto.dossierEtudiantId] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the dossierEtudiantId
	 */
	public Integer getDossierEtudiantId() {
		return dossierEtudiantId;
	}


	/**
	 * [attestationFinEtudeDto.dossierEtudiantId] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param dossierEtudiantId the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(Integer dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}


	/**
	 * [attestationFinEtudeDto.numeroMatricule] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the numeroMatricule
	 */
	public String getNumeroMatricule() {
		return numeroMatricule;
	}


	/**
	 * [attestationFinEtudeDto.numeroMatricule] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param numeroMatricule the numeroMatricule to set
	 */
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}


	/**
	 * [attestationFinEtudeDto.ouvertureOffreFormationId] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the ouvertureOffreFormationId
	 */
	public Integer getOuvertureOffreFormationId() {
		return ouvertureOffreFormationId;
	}


	/**
	 * [attestationFinEtudeDto.ouvertureOffreFormationId] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param ouvertureOffreFormationId the ouvertureOffreFormationId to set
	 */
	public void setOuvertureOffreFormationId(Integer ouvertureOffreFormationId) {
		this.ouvertureOffreFormationId = ouvertureOffreFormationId;
	}


	/**
	 * [attestationFinEtudeDto.offreFormationId] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the offreFormationId
	 */
	public Integer getOffreFormationId() {
		return offreFormationId;
	}


	/**
	 * [attestationFinEtudeDto.offreFormationId] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param offreFormationId the offreFormationId to set
	 */
	public void setOffreFormationId(Integer offreFormationId) {
		this.offreFormationId = offreFormationId;
	}


	/**
	 * [attestationFinEtudeDto.offreFormationCode] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the offreFormationCode
	 */
	public String getOffreFormationCode() {
		return offreFormationCode;
	}


	/**
	 * [attestationFinEtudeDto.offreFormationCode] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param offreFormationCode the offreFormationCode to set
	 */
	public void setOffreFormationCode(String offreFormationCode) {
		this.offreFormationCode = offreFormationCode;
	}


	/**
	 * [attestationFinEtudeDto.offreFormationLibelleFr] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the offreFormationLibelleFr
	 */
	public String getOffreFormationLibelleFr() {
		return offreFormationLibelleFr;
	}


	/**
	 * [attestationFinEtudeDto.offreFormationLibelleFr] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param offreFormationLibelleFr the offreFormationLibelleFr to set
	 */
	public void setOffreFormationLibelleFr(String offreFormationLibelleFr) {
		this.offreFormationLibelleFr = offreFormationLibelleFr;
	}


	/**
	 * [attestationFinEtudeDto.offreFormationLibelleAr] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the offreFormationLibelleAr
	 */
	public String getOffreFormationLibelleAr() {
		return offreFormationLibelleAr;
	}


	/**
	 * [attestationFinEtudeDto.offreFormationLibelleAr] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param offreFormationLibelleAr the offreFormationLibelleAr to set
	 */
	public void setOffreFormationLibelleAr(String offreFormationLibelleAr) {
		this.offreFormationLibelleAr = offreFormationLibelleAr;
	}


	/**
	 * [attestationFinEtudeDto.offreFormationrefCodeTypeFormation] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the offreFormationrefCodeTypeFormation
	 */
	public String getOffreFormationrefCodeTypeFormation() {
		return offreFormationrefCodeTypeFormation;
	}


	/**
	 * [attestationFinEtudeDto.offreFormationrefCodeTypeFormation] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param offreFormationrefCodeTypeFormation the offreFormationrefCodeTypeFormation to set
	 */
	public void setOffreFormationrefCodeTypeFormation(
			String offreFormationrefCodeTypeFormation) {
		this.offreFormationrefCodeTypeFormation = offreFormationrefCodeTypeFormation;
	}


	/**
	 * [attestationFinEtudeDto.individuId] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the individuId
	 */
	public Integer getIndividuId() {
		return individuId;
	}


	/**
	 * [attestationFinEtudeDto.individuId] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param individuId the individuId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}


	/**
	 * [attestationFinEtudeDto.nin] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the nin
	 */
	public String getNin() {
		return nin;
	}


	/**
	 * [attestationFinEtudeDto.nin] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param nin the nin to set
	 */
	public void setNin(String nin) {
		this.nin = nin;
	}


	/**
	 * [attestationFinEtudeDto.individuNomArabe] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the individuNomArabe
	 */
	public String getIndividuNomArabe() {
		return individuNomArabe;
	}


	/**
	 * [attestationFinEtudeDto.individuNomArabe] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param individuNomArabe the individuNomArabe to set
	 */
	public void setIndividuNomArabe(String individuNomArabe) {
		this.individuNomArabe = individuNomArabe;
	}


	/**
	 * [attestationFinEtudeDto.individuNomLatin] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the individuNomLatin
	 */
	public String getIndividuNomLatin() {
		return individuNomLatin;
	}


	/**
	 * [attestationFinEtudeDto.individuNomLatin] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param individuNomLatin the individuNomLatin to set
	 */
	public void setIndividuNomLatin(String individuNomLatin) {
		this.individuNomLatin = individuNomLatin;
	}


	/**
	 * [attestationFinEtudeDto.individuPrenomArabe] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the individuPrenomArabe
	 */
	public String getIndividuPrenomArabe() {
		return individuPrenomArabe;
	}


	/**
	 * [attestationFinEtudeDto.individuPrenomArabe] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param individuPrenomArabe the individuPrenomArabe to set
	 */
	public void setIndividuPrenomArabe(String individuPrenomArabe) {
		this.individuPrenomArabe = individuPrenomArabe;
	}


	/**
	 * [attestationFinEtudeDto.individuPrenomLatin] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the individuPrenomLatin
	 */
	public String getIndividuPrenomLatin() {
		return individuPrenomLatin;
	}


	/**
	 * [attestationFinEtudeDto.individuPrenomLatin] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param individuPrenomLatin the individuPrenomLatin to set
	 */
	public void setIndividuPrenomLatin(String individuPrenomLatin) {
		this.individuPrenomLatin = individuPrenomLatin;
	}


	/**
	 * [attestationFinEtudeDto.individuDateNaissance] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the individuDateNaissance
	 */
	public Date getIndividuDateNaissance() {
		return individuDateNaissance;
	}


	/**
	 * [attestationFinEtudeDto.individuDateNaissance] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param individuDateNaissance the individuDateNaissance to set
	 */
	public void setIndividuDateNaissance(Date individuDateNaissance) {
		this.individuDateNaissance = individuDateNaissance;
	}


	/**
	 * [attestationFinEtudeDto.individuLieuNaissance] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the individuLieuNaissance
	 */
	public String getIndividuLieuNaissance() {
		return individuLieuNaissance;
	}


	/**
	 * [attestationFinEtudeDto.individuLieuNaissance] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param individuLieuNaissance the individuLieuNaissance to set
	 */
	public void setIndividuLieuNaissance(String individuLieuNaissance) {
		this.individuLieuNaissance = individuLieuNaissance;
	}


	/**
	 * [attestationFinEtudeDto.refLibelleNiveauArabe] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the refLibelleNiveauArabe
	 */
	public String getRefLibelleNiveauArabe() {
		return refLibelleNiveauArabe;
	}


	/**
	 * [attestationFinEtudeDto.refLibelleNiveauArabe] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param refLibelleNiveauArabe the refLibelleNiveauArabe to set
	 */
	public void setRefLibelleNiveauArabe(String refLibelleNiveauArabe) {
		this.refLibelleNiveauArabe = refLibelleNiveauArabe;
	}


	/**
	 * [attestationFinEtudeDto.refLibelleDomaineArabe] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the refLibelleDomaineArabe
	 */
	public String getRefLibelleDomaineArabe() {
		return refLibelleDomaineArabe;
	}


	/**
	 * [attestationFinEtudeDto.refLibelleDomaineArabe] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param refLibelleDomaineArabe the refLibelleDomaineArabe to set
	 */
	public void setRefLibelleDomaineArabe(String refLibelleDomaineArabe) {
		this.refLibelleDomaineArabe = refLibelleDomaineArabe;
	}


	/**
	 * [attestationFinEtudeDto.refLibelleFiliereArabe] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the refLibelleFiliereArabe
	 */
	public String getRefLibelleFiliereArabe() {
		return refLibelleFiliereArabe;
	}


	/**
	 * [attestationFinEtudeDto.refLibelleFiliereArabe] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param refLibelleFiliereArabe the refLibelleFiliereArabe to set
	 */
	public void setRefLibelleFiliereArabe(String refLibelleFiliereArabe) {
		this.refLibelleFiliereArabe = refLibelleFiliereArabe;
	}


	/**
	 * [attestationFinEtudeDto.refLibelleSpecialiteArabe] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the refLibelleSpecialiteArabe
	 */
	public String getRefLibelleSpecialiteArabe() {
		return refLibelleSpecialiteArabe;
	}


	/**
	 * [attestationFinEtudeDto.refLibelleSpecialiteArabe] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param refLibelleSpecialiteArabe the refLibelleSpecialiteArabe to set
	 */
	public void setRefLibelleSpecialiteArabe(String refLibelleSpecialiteArabe) {
		this.refLibelleSpecialiteArabe = refLibelleSpecialiteArabe;
	}


	/**
	 * [attestationFinEtudeDto.llEtablissementArabe] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the llEtablissementArabe
	 */
	public String getLlEtablissementArabe() {
		return llEtablissementArabe;
	}


	/**
	 * [attestationFinEtudeDto.llEtablissementArabe] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param llEtablissementArabe the llEtablissementArabe to set
	 */
	public void setLlEtablissementArabe(String llEtablissementArabe) {
		this.llEtablissementArabe = llEtablissementArabe;
	}


	/**
	 * [attestationFinEtudeDto.llEtablissementLatin] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the llEtablissementLatin
	 */
	public String getLlEtablissementLatin() {
		return llEtablissementLatin;
	}


	/**
	 * [attestationFinEtudeDto.llEtablissementLatin] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @param llEtablissementLatin the llEtablissementLatin to set
	 */
	public void setLlEtablissementLatin(String llEtablissementLatin) {
		this.llEtablissementLatin = llEtablissementLatin;
	}


	/**
	 * [attestationFinEtudeDto.serialversionuid] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  12:32:56
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * [attestationFinEtudeDto.attestationFinEtudeEditions] Getter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  15:29:06
	 * @return the attestationFinEtudeEditions
	 */
	public Set<AttestationFinEtudeEdition> getAttestationFinEtudeEditions() {
		return attestationFinEtudeEditions;
	}


	/**
	 * [attestationFinEtudeDto.attestationFinEtudeEditions] Setter 
	 * @author BELBRIK Oualid on : 1 oct. 2014  15:29:06
	 * @param attestationFinEtudeEditions the attestationFinEtudeEditions to set
	 */
	public void setAttestationFinEtudeEditions(
			Set<AttestationFinEtudeEdition> attestationFinEtudeEditions) {
		this.attestationFinEtudeEditions = attestationFinEtudeEditions;
	}


	/**
	 * [attestationFinEtudeDto.anneeAcademiqueInscriptionCode] Getter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @return the anneeAcademiqueInscriptionCode
	 */
	public String getAnneeAcademiqueInscriptionCode() {
		return anneeAcademiqueInscriptionCode;
	}


	/**
	 * [attestationFinEtudeDto.anneeAcademiqueInscriptionCode] Setter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @param anneeAcademiqueInscriptionCode the anneeAcademiqueInscriptionCode to set
	 */
	public void setAnneeAcademiqueInscriptionCode(
			String anneeAcademiqueInscriptionCode) {
		this.anneeAcademiqueInscriptionCode = anneeAcademiqueInscriptionCode;
	}


	/**
	 * [attestationFinEtudeDto.dossierId] Getter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @return the dossierId
	 */
	public Integer getDossierId() {
		return dossierId;
	}


	/**
	 * [attestationFinEtudeDto.dossierId] Setter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @param dossierId the dossierId to set
	 */
	public void setDossierId(Integer dossierId) {
		this.dossierId = dossierId;
	}


	/**
	 * [attestationFinEtudeDto.refCodeDomaine] Getter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @return the refCodeDomaine
	 */
	public String getRefCodeDomaine() {
		return refCodeDomaine;
	}


	/**
	 * [attestationFinEtudeDto.refCodeDomaine] Setter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @param refCodeDomaine the refCodeDomaine to set
	 */
	public void setRefCodeDomaine(String refCodeDomaine) {
		this.refCodeDomaine = refCodeDomaine;
	}


	/**
	 * [attestationFinEtudeDto.refCodeFiliere] Getter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @return the refCodeFiliere
	 */
	public String getRefCodeFiliere() {
		return refCodeFiliere;
	}


	/**
	 * [attestationFinEtudeDto.refCodeFiliere] Setter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @param refCodeFiliere the refCodeFiliere to set
	 */
	public void setRefCodeFiliere(String refCodeFiliere) {
		this.refCodeFiliere = refCodeFiliere;
	}


	/**
	 * [attestationFinEtudeDto.refCodeSpecialite] Getter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @return the refCodeSpecialite
	 */
	public String getRefCodeSpecialite() {
		return refCodeSpecialite;
	}


	/**
	 * [attestationFinEtudeDto.refCodeSpecialite] Setter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @param refCodeSpecialite the refCodeSpecialite to set
	 */
	public void setRefCodeSpecialite(String refCodeSpecialite) {
		this.refCodeSpecialite = refCodeSpecialite;
	}


	/**
	 * [attestationFinEtudeDto.resultRefCodeDomaine] Getter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @return the resultRefCodeDomaine
	 */
	public String getResultRefCodeDomaine() {
		return resultRefCodeDomaine;
	}


	/**
	 * [attestationFinEtudeDto.resultRefCodeDomaine] Setter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @param resultRefCodeDomaine the resultRefCodeDomaine to set
	 */
	public void setResultRefCodeDomaine(String resultRefCodeDomaine) {
		this.resultRefCodeDomaine = resultRefCodeDomaine;
	}


	/**
	 * [attestationFinEtudeDto.resultRefCodeFiliere] Getter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @return the resultRefCodeFiliere
	 */
	public String getResultRefCodeFiliere() {
		return resultRefCodeFiliere;
	}


	/**
	 * [attestationFinEtudeDto.resultRefCodeFiliere] Setter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @param resultRefCodeFiliere the resultRefCodeFiliere to set
	 */
	public void setResultRefCodeFiliere(String resultRefCodeFiliere) {
		this.resultRefCodeFiliere = resultRefCodeFiliere;
	}


	/**
	 * [attestationFinEtudeDto.resultRefCodeSpecialite] Getter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @return the resultRefCodeSpecialite
	 */
	public String getResultRefCodeSpecialite() {
		return resultRefCodeSpecialite;
	}


	/**
	 * [attestationFinEtudeDto.resultRefCodeSpecialite] Setter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @param resultRefCodeSpecialite the resultRefCodeSpecialite to set
	 */
	public void setResultRefCodeSpecialite(String resultRefCodeSpecialite) {
		this.resultRefCodeSpecialite = resultRefCodeSpecialite;
	}


	/**
	 * [attestationFinEtudeDto.refLibelleDomaine] Getter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @return the refLibelleDomaine
	 */
	public String getRefLibelleDomaine() {
		return refLibelleDomaine;
	}


	/**
	 * [attestationFinEtudeDto.refLibelleDomaine] Setter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @param refLibelleDomaine the refLibelleDomaine to set
	 */
	public void setRefLibelleDomaine(String refLibelleDomaine) {
		this.refLibelleDomaine = refLibelleDomaine;
	}


	/**
	 * [attestationFinEtudeDto.refLibelleFiliere] Getter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @return the refLibelleFiliere
	 */
	public String getRefLibelleFiliere() {
		return refLibelleFiliere;
	}


	/**
	 * [attestationFinEtudeDto.refLibelleFiliere] Setter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @param refLibelleFiliere the refLibelleFiliere to set
	 */
	public void setRefLibelleFiliere(String refLibelleFiliere) {
		this.refLibelleFiliere = refLibelleFiliere;
	}


	/**
	 * [attestationFinEtudeDto.refLibelleSpecialite] Getter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @return the refLibelleSpecialite
	 */
	public String getRefLibelleSpecialite() {
		return refLibelleSpecialite;
	}


	/**
	 * [attestationFinEtudeDto.refLibelleSpecialite] Setter 
	 * @author BELBRIK Oualid on : 8 oct. 2014  11:06:20
	 * @param refLibelleSpecialite the refLibelleSpecialite to set
	 */
	public void setRefLibelleSpecialite(String refLibelleSpecialite) {
		this.refLibelleSpecialite = refLibelleSpecialite;
	}


	/**
	 * [attestationFinEtudeDto.attestationFinEtudeEditionList] Getter 
	 * @author BELBRIK Oualid on : 9 oct. 2014  18:45:39
	 * @return the attestationFinEtudeEditionList
	 */
	public List<AttestationFinEtudeEditionDto> getAttestationFinEtudeEditionList() {
		return attestationFinEtudeEditionList;
	}


	/**
	 * [attestationFinEtudeDto.attestationFinEtudeEditionList] Setter 
	 * @author BELBRIK Oualid on : 9 oct. 2014  18:45:39
	 * @param attestationFinEtudeEditionList the attestationFinEtudeEditionList to set
	 */
	public void setAttestationFinEtudeEditionList(
			List<AttestationFinEtudeEditionDto> attestationFinEtudeEditionList) {
		this.attestationFinEtudeEditionList = attestationFinEtudeEditionList;
	}


	/**
	 * [attestationFinEtudeDto.equivalence] Getter 
	 * @author BELBRIK Oualid on : 11 oct. 2014  11:19:54
	 * @return the equivalence
	 */
	public String getEquivalence() {
		return equivalence;
	}


	/**
	 * [attestationFinEtudeDto.equivalence] Setter 
	 * @author BELBRIK Oualid on : 11 oct. 2014  11:19:54
	 * @param equivalence the equivalence to set
	 */
	public void setEquivalence(String equivalence) {
		this.equivalence = equivalence;
	}


	/**
	 * [attestationFinEtudeDto.refMinistere] Getter 
	 * @author BELBRIK Oualid on : 11 oct. 2014  17:27:21
	 * @return the refMinistere
	 */
	public String getRefMinistere() {
		return refMinistere;
	}


	/**
	 * [attestationFinEtudeDto.refMinistere] Setter 
	 * @author BELBRIK Oualid on : 11 oct. 2014  17:27:21
	 * @param refMinistere the refMinistere to set
	 */
	public void setRefMinistere(String refMinistere) {
		this.refMinistere = refMinistere;
	}



	/**
	 * [attestationFinEtudeDto.dateSignatureMinistere] Getter 
	 * @author BELBRIK Oualid on : 11 oct. 2014  17:27:21
	 * @return the dateSignatureMinistere
	 */
	public Date getDateSignatureMinistere() {
		return dateSignatureMinistere;
	}


	/**
	 * [attestationFinEtudeDto.dateSignatureMinistere] Setter 
	 * @author BELBRIK Oualid on : 11 oct. 2014  17:27:21
	 * @param dateSignatureMinistere the dateSignatureMinistere to set
	 */
	public void setDateSignatureMinistere(Date dateSignatureMinistere) {
		this.dateSignatureMinistere = dateSignatureMinistere;
	}


	/**
	 * [attestationFinEtudeDto.dateSignatureEtablissement] Getter 
	 * @author BELBRIK Oualid on : 11 oct. 2014  17:27:21
	 * @return the dateSignatureEtablissement
	 */
	public Date getDateSignatureEtablissement() {
		return dateSignatureEtablissement;
	}


	/**
	 * [attestationFinEtudeDto.dateSignatureEtablissement] Setter 
	 * @author BELBRIK Oualid on : 11 oct. 2014  17:27:21
	 * @param dateSignatureEtablissement the dateSignatureEtablissement to set
	 */
	public void setDateSignatureEtablissement(Date dateSignatureEtablissement) {
		this.dateSignatureEtablissement = dateSignatureEtablissement;
	}


	/**
	 * [attestationFinEtudeDto.autoriteMinistere] Getter 
	 * @author BELBRIK Oualid on : 11 oct. 2014  17:27:21
	 * @return the autoriteMinistere
	 */
	public String getAutoriteMinistere() {
		return autoriteMinistere;
	}


	/**
	 * [attestationFinEtudeDto.autoriteMinistere] Setter 
	 * @author BELBRIK Oualid on : 11 oct. 2014  17:27:21
	 * @param autoriteMinistere the autoriteMinistere to set
	 */
	public void setAutoriteMinistere(String autoriteMinistere) {
		this.autoriteMinistere = autoriteMinistere;
	}


	/**
	 * [attestationFinEtudeDto.autoriteEtablissement] Getter 
	 * @author BELBRIK Oualid on : 11 oct. 2014  17:27:21
	 * @return the autoriteEtablissement
	 */
	public String getAutoriteEtablissement() {
		return autoriteEtablissement;
	}


	/**
	 * [attestationFinEtudeDto.autoriteEtablissement] Setter 
	 * @author BELBRIK Oualid on : 11 oct. 2014  17:27:21
	 * @param autoriteEtablissement the autoriteEtablissement to set
	 */
	public void setAutoriteEtablissement(String autoriteEtablissement) {
		this.autoriteEtablissement = autoriteEtablissement;
	}


	/**
	 * [attestationFinEtudeDto.estValideEtab] Getter 
	 * @author BELBRIK Oualid on : 12 oct. 2014  12:44:37
	 * @return the estValideEtab
	 */
	public Boolean getEstValideEtab() {
		return estValideEtab;
	}


	/**
	 * [attestationFinEtudeDto.estValideEtab] Setter 
	 * @author BELBRIK Oualid on : 12 oct. 2014  12:44:37
	 * @param estValideEtab the estValideEtab to set
	 */
	public void setEstValideEtab(Boolean estValideEtab) {
		this.estValideEtab = estValideEtab;
	}


	/**
	 * [attestationFinEtudeDto.observationEtab] Getter 
	 * @author BELBRIK Oualid on : 16 oct. 2014  20:04:10
	 * @return the observationEtab
	 */
	public String getObservationEtab() {
		return observationEtab;
	}


	/**
	 * [attestationFinEtudeDto.observationEtab] Setter 
	 * @author BELBRIK Oualid on : 16 oct. 2014  20:04:11
	 * @param observationEtab the observationEtab to set
	 */
	public void setObservationEtab(String observationEtab) {
		this.observationEtab = observationEtab;
	}


	/**
	 * [attestationFinEtudeDto.idAnneAcademique] Getter 
	 * @author BELBRIK Oualid on : 16 oct. 2014  20:56:58
	 * @return the idAnneAcademique
	 */
	public int getIdAnneAcademique() {
		return idAnneAcademique;
	}


	/**
	 * [attestationFinEtudeDto.idAnneAcademique] Setter 
	 * @author BELBRIK Oualid on : 16 oct. 2014  20:56:58
	 * @param idAnneAcademique the idAnneAcademique to set
	 */
	public void setIdAnneAcademique(int idAnneAcademique) {
		this.idAnneAcademique = idAnneAcademique;
	}


	/**
	 * [attestationFinEtudeDto.dateCreation] Getter 
	 * @author BELBRIK Oualid on : 25 oct. 2014  11:24:53
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}


	/**
	 * [attestationFinEtudeDto.dateCreation] Setter 
	 * @author BELBRIK Oualid on : 25 oct. 2014  11:24:53
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	/**
	 * [attestationFinEtudeDto.bilanSessionId] Getter 
	 * @author BELBRIK Oualid on : 25 oct. 2014  12:09:12
	 * @return the bilanSessionId
	 */
	public Long getBilanSessionId() {
		return bilanSessionId;
	}


	/**
	 * [attestationFinEtudeDto.bilanSessionId] Setter 
	 * @author BELBRIK Oualid on : 25 oct. 2014  12:09:12
	 * @param bilanSessionId the bilanSessionId to set
	 */
	public void setBilanSessionId(Long bilanSessionId) {
		this.bilanSessionId = bilanSessionId;
	}


	/**
	 * [attestationFinEtudeDto.bilanSessionDeliberationSessionId] Getter 
	 * @author BELBRIK Oualid on : 25 oct. 2014  12:09:12
	 * @return the bilanSessionDeliberationSessionId
	 */
	public Integer getBilanSessionDeliberationSessionId() {
		return bilanSessionDeliberationSessionId;
	}


	/**
	 * [attestationFinEtudeDto.bilanSessionDeliberationSessionId] Setter 
	 * @author BELBRIK Oualid on : 25 oct. 2014  12:09:12
	 * @param bilanSessionDeliberationSessionId the bilanSessionDeliberationSessionId to set
	 */
	public void setBilanSessionDeliberationSessionId(
			Integer bilanSessionDeliberationSessionId) {
		this.bilanSessionDeliberationSessionId = bilanSessionDeliberationSessionId;
	}


	/**
	 * [attestationFinEtudeDto.bilanSessionDossierInscriptionAdministrativeId] Getter 
	 * @author BELBRIK Oualid on : 25 oct. 2014  12:09:12
	 * @return the bilanSessionDossierInscriptionAdministrativeId
	 */
	public Integer getBilanSessionDossierInscriptionAdministrativeId() {
		return bilanSessionDossierInscriptionAdministrativeId;
	}


	/**
	 * [attestationFinEtudeDto.bilanSessionDossierInscriptionAdministrativeId] Setter 
	 * @author BELBRIK Oualid on : 25 oct. 2014  12:09:12
	 * @param bilanSessionDossierInscriptionAdministrativeId the bilanSessionDossierInscriptionAdministrativeId to set
	 */
	public void setBilanSessionDossierInscriptionAdministrativeId(
			Integer bilanSessionDossierInscriptionAdministrativeId) {
		this.bilanSessionDossierInscriptionAdministrativeId = bilanSessionDossierInscriptionAdministrativeId;
	}


	/**
	 * [attestationFinEtudeDto.bilanSessionTypeDecisionId] Getter 
	 * @author BELBRIK Oualid on : 25 oct. 2014  12:09:12
	 * @return the bilanSessionTypeDecisionId
	 */
	public Integer getBilanSessionTypeDecisionId() {
		return bilanSessionTypeDecisionId;
	}


	/**
	 * [attestationFinEtudeDto.bilanSessionTypeDecisionId] Setter 
	 * @author BELBRIK Oualid on : 25 oct. 2014  12:09:12
	 * @param bilanSessionTypeDecisionId the bilanSessionTypeDecisionId to set
	 */
	public void setBilanSessionTypeDecisionId(Integer bilanSessionTypeDecisionId) {
		this.bilanSessionTypeDecisionId = bilanSessionTypeDecisionId;
	}


	/**
	 * [attestationFinEtudeDto.bilanSessionAnneeAcademiqueId] Getter 
	 * @author BELBRIK Oualid on : 25 oct. 2014  12:09:12
	 * @return the bilanSessionAnneeAcademiqueId
	 */
	public Integer getBilanSessionAnneeAcademiqueId() {
		return bilanSessionAnneeAcademiqueId;
	}


	/**
	 * [attestationFinEtudeDto.bilanSessionAnneeAcademiqueId] Setter 
	 * @author BELBRIK Oualid on : 25 oct. 2014  12:09:12
	 * @param bilanSessionAnneeAcademiqueId the bilanSessionAnneeAcademiqueId to set
	 */
	public void setBilanSessionAnneeAcademiqueId(
			Integer bilanSessionAnneeAcademiqueId) {
		this.bilanSessionAnneeAcademiqueId = bilanSessionAnneeAcademiqueId;
	}


	/**
	 * [attestationFinEtudeDto.cycleLibelleLongAr] Getter 
	 * @author BELBRIK Oualid on : 28 oct. 2014  12:10:24
	 * @return the cycleLibelleLongAr
	 */
	public String getCycleLibelleLongAr() {
		return cycleLibelleLongAr;
	}


	/**
	 * [attestationFinEtudeDto.cycleLibelleLongAr] Setter 
	 * @author BELBRIK Oualid on : 28 oct. 2014  12:10:24
	 * @param cycleLibelleLongAr the cycleLibelleLongAr to set
	 */
	public void setCycleLibelleLongAr(String cycleLibelleLongAr) {
		this.cycleLibelleLongAr = cycleLibelleLongAr;
	}


	/**
	 * [attestationFinEtudeDto.bilanSessionrefCodeTypeSession] Getter 
	 * @author BELBRIK Oualid on : 28 oct. 2014  18:43:14
	 * @return the bilanSessionrefCodeTypeSession
	 */
	public String getBilanSessionrefCodeTypeSession() {
		return bilanSessionrefCodeTypeSession;
	}


	/**
	 * [attestationFinEtudeDto.bilanSessionrefCodeTypeSession] Setter 
	 * @author BELBRIK Oualid on : 28 oct. 2014  18:43:14
	 * @param bilanSessionrefCodeTypeSession the bilanSessionrefCodeTypeSession to set
	 */
	public void setBilanSessionrefCodeTypeSession(
			String bilanSessionrefCodeTypeSession) {
		this.bilanSessionrefCodeTypeSession = bilanSessionrefCodeTypeSession;
	}


	/**
	 * [attestationFinEtudeDto.refCodeTypeFormation] Getter 
	 * @author BELBRIK Oualid on : 29 oct. 2014  12:50:30
	 * @return the refCodeTypeFormation
	 */
	public String getRefCodeTypeFormation() {
		return refCodeTypeFormation;
	}


	/**
	 * [attestationFinEtudeDto.refCodeTypeFormation] Setter 
	 * @author BELBRIK Oualid on : 29 oct. 2014  12:50:30
	 * @param refCodeTypeFormation the refCodeTypeFormation to set
	 */
	public void setRefCodeTypeFormation(String refCodeTypeFormation) {
		this.refCodeTypeFormation = refCodeTypeFormation;
	}


	/**
	 * [attestationFinEtudeDto.photo] Getter 
	 * @author BELBRIK Oualid on : 29 oct. 2014  17:18:12
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}


	/**
	 * [attestationFinEtudeDto.photo] Setter 
	 * @author BELBRIK Oualid on : 29 oct. 2014  17:18:12
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}


	/**
	 * [attestationFinEtudeDto.estValideFac] Getter 
	 * @author BELBRIK Oualid on : 2 nov. 2014  17:43:11
	 * @return the estValideFac
	 */
	public Boolean getEstValideFac() {
		return estValideFac;
	}


	/**
	 * [attestationFinEtudeDto.estValideFac] Setter 
	 * @author BELBRIK Oualid on : 2 nov. 2014  17:43:11
	 * @param estValideFac the estValideFac to set
	 */
	public void setEstValideFac(Boolean estValideFac) {
		this.estValideFac = estValideFac;
	}


	/**
	 * [attestationFinEtudeDto.dateSignatureFaculte] Getter 
	 * @author BELBRIK Oualid on : 2 nov. 2014  17:43:11
	 * @return the dateSignatureFaculte
	 */
	public Date getDateSignatureFaculte() {
		return dateSignatureFaculte;
	}


	/**
	 * [attestationFinEtudeDto.dateSignatureFaculte] Setter 
	 * @author BELBRIK Oualid on : 2 nov. 2014  17:43:11
	 * @param dateSignatureFaculte the dateSignatureFaculte to set
	 */
	public void setDateSignatureFaculte(Date dateSignatureFaculte) {
		this.dateSignatureFaculte = dateSignatureFaculte;
	}


	/**
	 * [attestationFinEtudeDto.generationValide] Getter 
	 * @author BELBRIK Oualid on : 3 nov. 2014  11:54:31
	 * @return the generationValide
	 */
	public Boolean getGenerationValide() {
		return generationValide;
	}


	/**
	 * [attestationFinEtudeDto.generationValide] Setter 
	 * @author BELBRIK Oualid on : 3 nov. 2014  11:54:31
	 * @param generationValide the generationValide to set
	 */
	public void setGenerationValide(Boolean generationValide) {
		this.generationValide = generationValide;
	}


	/**
	 * [attestationFinEtudeDto.refEtab] Getter 
	 * @author BELBRIK Oualid on : 3 nov. 2014  16:37:25
	 * @return the refEtab
	 */
	public String getRefEtab() {
		return refEtab;
	}


	/**
	 * [attestationFinEtudeDto.refEtab] Setter 
	 * @author BELBRIK Oualid on : 3 nov. 2014  16:37:25
	 * @param refEtab the refEtab to set
	 */
	public void setRefEtab(String refEtab) {
		this.refEtab = refEtab;
	}


	/**
	 * [attestationFinEtudeDto.refFac] Getter 
	 * @author BELBRIK Oualid on : 3 nov. 2014  16:37:25
	 * @return the refFac
	 */
	public String getRefFac() {
		return refFac;
	}


	/**
	 * [attestationFinEtudeDto.refFac] Setter 
	 * @author BELBRIK Oualid on : 3 nov. 2014  16:37:25
	 * @param refFac the refFac to set
	 */
	public void setRefFac(String refFac) {
		this.refFac = refFac;
	}


	/**
	 * [attestationFinEtudeDto.autoriteFac] Getter 
	 * @author BELBRIK Oualid on : 3 nov. 2014  16:37:25
	 * @return the autoriteFac
	 */
	public String getAutoriteFac() {
		return autoriteFac;
	}


	/**
	 * [attestationFinEtudeDto.autoriteFac] Setter 
	 * @author BELBRIK Oualid on : 3 nov. 2014  16:37:25
	 * @param autoriteFac the autoriteFac to set
	 */
	public void setAutoriteFac(String autoriteFac) {
		this.autoriteFac = autoriteFac;
	}


	/**
	 * [attestationFinEtudeDto.observationFac] Getter 
	 * @author BELBRIK Oualid on : 3 nov. 2014  16:37:25
	 * @return the observationFac
	 */
	public String getObservationFac() {
		return observationFac;
	}


	/**
	 * [attestationFinEtudeDto.observationFac] Setter 
	 * @author BELBRIK Oualid on : 3 nov. 2014  16:37:25
	 * @param observationFac the observationFac to set
	 */
	public void setObservationFac(String observationFac) {
		this.observationFac = observationFac;
	}


	/**
	 * [AttestationFinEtudeDto.mentionId] Getter 
	 * @author BELBRIK Oualid on : 10 nov. 2014  12:40:32
	 * @return the mentionId
	 */
	public Integer getMentionId() {
		return mentionId;
	}


	/**
	 * [AttestationFinEtudeDto.mentionId] Setter 
	 * @author BELBRIK Oualid on : 10 nov. 2014  12:40:32
	 * @param mentionId the mentionId to set
	 */
	public void setMentionId(Integer mentionId) {
		this.mentionId = mentionId;
	}


	/**
	 * [AttestationFinEtudeDto.mentionCode] Getter 
	 * @author BELBRIK Oualid on : 10 nov. 2014  12:40:32
	 * @return the mentionCode
	 */
	public String getMentionCode() {
		return mentionCode;
	}


	/**
	 * [AttestationFinEtudeDto.mentionCode] Setter 
	 * @author BELBRIK Oualid on : 10 nov. 2014  12:40:32
	 * @param mentionCode the mentionCode to set
	 */
	public void setMentionCode(String mentionCode) {
		this.mentionCode = mentionCode;
	}


	/**
	 * [AttestationFinEtudeDto.mentionLibelleFr] Getter 
	 * @author BELBRIK Oualid on : 10 nov. 2014  12:40:32
	 * @return the mentionLibelleFr
	 */
	public String getMentionLibelleFr() {
		return mentionLibelleFr;
	}


	/**
	 * [AttestationFinEtudeDto.mentionLibelleFr] Setter 
	 * @author BELBRIK Oualid on : 10 nov. 2014  12:40:32
	 * @param mentionLibelleFr the mentionLibelleFr to set
	 */
	public void setMentionLibelleFr(String mentionLibelleFr) {
		this.mentionLibelleFr = mentionLibelleFr;
	}


	/**
	 * [AttestationFinEtudeDto.mentionLibelleAr] Getter 
	 * @author BELBRIK Oualid on : 10 nov. 2014  12:40:32
	 * @return the mentionLibelleAr
	 */
	public String getMentionLibelleAr() {
		return mentionLibelleAr;
	}


	/**
	 * [AttestationFinEtudeDto.mentionLibelleAr] Setter 
	 * @author BELBRIK Oualid on : 10 nov. 2014  12:40:32
	 * @param mentionLibelleAr the mentionLibelleAr to set
	 */
	public void setMentionLibelleAr(String mentionLibelleAr) {
		this.mentionLibelleAr = mentionLibelleAr;
	}


	/**
	 * [AttestationFinEtudeDto.identifiant] Getter 
	 * @author BELBRIK Oualid on : 10 nov. 2014  12:41:57
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}


	/**
	 * [AttestationFinEtudeDto.identifiant] Setter 
	 * @author BELBRIK Oualid on : 10 nov. 2014  12:41:57
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}


	
	

}
