package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DiplomeFinEtudeEdition;

public class DiplomeFinEtudeDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String identifiant;
	private Integer idEtablissement;
	private Date dateObtention;
	private boolean estValide;
	private boolean estValideEtab;
	private boolean estDelivre;
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
	private Set<DiplomeFinEtudeEdition> diplomeFinEtudeEditions = new HashSet<DiplomeFinEtudeEdition>(
			0);
	List<DiplomeFinEtudeEditionDto> diplomeFinEtudeEditionList;
	private int idAnneAcademique;
	private Date dateCreation;
	private boolean estValideFac;
	private Date dateSignatureFaculte;
	private boolean generationValide;
	private String refEtab;
	private String refFac;
	private String autoriteFac;
	private String observationFac;

	// Bilan session
	private Long bilanSessionId;
	private Integer deliberationSessionId;
	private Integer dossierInscriptionAdministrativeId;
	private Integer typeDecisionId;
	private Integer anneeAcademiqueId;
	private String anneeAcademiqueCode;
	private String refCodeTypeSession;
	private double moyenne;

	// nomenclature
	private Integer mentionId;
	private String mentionCode;
	private String mentionLibelleFr;
	private String mentionLibelleAr;
	// cycle + niveau
	private Integer cycleId;
	private String cycleCode;
	private String cycleLibelleLongLt;
	private String cycleLibelleLongAr;
	private Integer niveauId;
	private String niveauCode;
	private String niveauLibelleLongLt;

	// private DossierInscriptionAdministrative
	// dossierInscriptionAdministrative;
	private String numeroInscription;
	private Integer dossierEtudiantId;
	private String numeroMatricule;
	private String photo;
	private Integer dossierId;

	// Offre de formation
	private Integer ouvertureOffreFormationId;
	private Integer offreFormationId;
	private String offreFormationCode;
	private String offreFormationLibelleFr;
	private String offreFormationLibelleAr;
	private String offreFormationrefCodeTypeFormation;
	private String refCodeTypeFormation;
	private String refCodeDomaine;
	private String refCodeFiliere;
	private String refCodeSpecialite;
	private String refLibelleDomaine;
	private String refLibelleFiliere;
	private String refLibelleSpecialite;
	private String refLibelleDomaineAr;
	private String refLibelleFiliereAr;
	private String refLibelleSpecialiteAr;

	// Etudiant Individu
	private Integer individuId;
	private String nin;
	private String etudiantNomArabe;
	private String etudiantNomLatin;
	private String etudiantPrenomArabe;
	private String etudiantPrenomLatin;
	private Date etudiantDateNaissance;
	private String etudiantLieuNaissance;
	private String refLibelleNiveauArabe;
	private String refLibelleDomaineArabe;
	private String refLibelleFiliereArabe;
	private String refLibelleSpecialiteArabe;
	private String llEtablissementArabe;
	private String llEtablissementLatin;
	private String libelleDiplomePrincipalArabe;
	private String libelleDiplomeSecondaireArabe;
	private String libelleDiplomePrincipalLatin;
	private String libelleDiplomeSecondaireLatin;

	public DiplomeFinEtudeDto() {
	}

	/**
	 * [DiplomeFinEtudeDto.DiplomeFinEtudeDto()] Constructor
	 * @author MAKERRI Sofiane  on : 23 févr. 2015  08:42:48
	 * @param diplomeFinEtudeDto	
	 */
	public DiplomeFinEtudeDto(DiplomeFinEtudeDto diplomeFinEtudeDto) {
		if (diplomeFinEtudeDto != null) {
			this.id = diplomeFinEtudeDto.getId();
			this.etudiantNomArabe = diplomeFinEtudeDto.getEtudiantNomArabe();
			this.etudiantPrenomArabe = diplomeFinEtudeDto
					.getEtudiantPrenomArabe();
			this.etudiantDateNaissance = diplomeFinEtudeDto
					.getEtudiantDateNaissance();
			this.etudiantLieuNaissance = diplomeFinEtudeDto
					.getEtudiantLieuNaissance();
			this.anneeAcademiqueCode = diplomeFinEtudeDto
					.getAnneeAcademiqueCode();
			this.cycleLibelleLongLt = diplomeFinEtudeDto
					.getCycleLibelleLongLt();
			this.offreFormationLibelleFr = diplomeFinEtudeDto
					.getOffreFormationLibelleFr();
			this.offreFormationLibelleAr = diplomeFinEtudeDto
					.getOffreFormationLibelleAr();
			this.etudiantNomLatin = diplomeFinEtudeDto.getEtudiantNomLatin();
			this.etudiantPrenomLatin = diplomeFinEtudeDto
					.getEtudiantPrenomLatin();
			this.dateObtention = diplomeFinEtudeDto.getDateObtention();

		}
	}

	/**
	 * [DiplomeFinEtudeDto.id] Getter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [DiplomeFinEtudeDto.id] Setter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [DiplomeFinEtudeDto.idEtablissement] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 nov. 2014 17:36:33
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [DiplomeFinEtudeDto.idEtablissement] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 nov. 2014 17:36:33
	 * @param idEtablissement
	 *            the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * [DiplomeFinEtudeDto.dateObtention] Getter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @return the dateObtention
	 */
	public Date getDateObtention() {
		return dateObtention;
	}

	/**
	 * [DiplomeFinEtudeDto.dateObtention] Setter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @param dateObtention
	 *            the dateObtention to set
	 */
	public void setDateObtention(Date dateObtention) {
		this.dateObtention = dateObtention;
	}

	/**
	 * [DiplomeFinEtudeDto.estValide] Getter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @return the estValide
	 */
	public boolean getEstValide() {
		return estValide;
	}

	/**
	 * [DiplomeFinEtudeDto.estValide] Setter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @param estValide
	 *            the estValide to set
	 */
	public void setEstValide(boolean estValide) {
		this.estValide = estValide;
	}

	/**
	 * [DiplomeFinEtudeDto.estDelivre] Getter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @return the estDelivre
	 */
	public boolean getEstDelivre() {
		return estDelivre;
	}

	/**
	 * [DiplomeFinEtudeDto.estDelivre] Setter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @param estDelivre
	 *            the estDelivre to set
	 */
	public void setEstDelivre(boolean estDelivre) {
		this.estDelivre = estDelivre;
	}

	/**
	 * [DiplomeFinEtudeDto.dateDerniereEdition] Getter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @return the dateDerniereEdition
	 */
	public Date getDateDerniereEdition() {
		return dateDerniereEdition;
	}

	/**
	 * [DiplomeFinEtudeDto.dateDerniereEdition] Setter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @param dateDerniereEdition
	 *            the dateDerniereEdition to set
	 */
	public void setDateDerniereEdition(Date dateDerniereEdition) {
		this.dateDerniereEdition = dateDerniereEdition;
	}

	/**
	 * [DiplomeFinEtudeDto.idCompteAgent] Getter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @return the idCompteAgent
	 */
	public Integer getIdCompteAgent() {
		return idCompteAgent;
	}

	/**
	 * [DiplomeFinEtudeDto.idCompteAgent] Setter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @param idCompteAgent
	 *            the idCompteAgent to set
	 */
	public void setIdCompteAgent(Integer idCompteAgent) {
		this.idCompteAgent = idCompteAgent;
	}

	/**
	 * [DiplomeFinEtudeDto.refTypeImpression] Getter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @return the refTypeImpression
	 */
	public String getRefTypeImpression() {
		return refTypeImpression;
	}

	/**
	 * [DiplomeFinEtudeDto.refTypeImpression] Setter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @param refTypeImpression
	 *            the refTypeImpression to set
	 */
	public void setRefTypeImpression(String refTypeImpression) {
		this.refTypeImpression = refTypeImpression;
	}

	/**
	 * [DiplomeFinEtudeDto.observation] Getter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * [DiplomeFinEtudeDto.observation] Setter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 19:32:20
	 * @param observation
	 *            the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * [DiplomeFinEtudeDto.anneeAcademiqueId] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [DiplomeFinEtudeDto.anneeAcademiqueId] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [DiplomeFinEtudeDto.cycleId] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the cycleId
	 */
	public Integer getCycleId() {
		return cycleId;
	}

	/**
	 * [DiplomeFinEtudeDto.cycleId] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param cycleId
	 *            the cycleId to set
	 */
	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	/**
	 * [DiplomeFinEtudeDto.cycleCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the cycleCode
	 */
	public String getCycleCode() {
		return cycleCode;
	}

	/**
	 * [DiplomeFinEtudeDto.cycleCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param cycleCode
	 *            the cycleCode to set
	 */
	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}

	/**
	 * [DiplomeFinEtudeDto.cycleLibelleLongLt] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the cycleLibelleLongLt
	 */
	public String getCycleLibelleLongLt() {
		return cycleLibelleLongLt;
	}

	/**
	 * [DiplomeFinEtudeDto.cycleLibelleLongLt] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param cycleLibelleLongLt
	 *            the cycleLibelleLongLt to set
	 */
	public void setCycleLibelleLongLt(String cycleLibelleLongLt) {
		this.cycleLibelleLongLt = cycleLibelleLongLt;
	}

	/**
	 * [DiplomeFinEtudeDto.niveauId] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the niveauId
	 */
	public Integer getNiveauId() {
		return niveauId;
	}

	/**
	 * [DiplomeFinEtudeDto.niveauId] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param niveauId
	 *            the niveauId to set
	 */
	public void setNiveauId(Integer niveauId) {
		this.niveauId = niveauId;
	}

	/**
	 * [DiplomeFinEtudeDto.niveauCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the niveauCode
	 */
	public String getNiveauCode() {
		return niveauCode;
	}

	/**
	 * [DiplomeFinEtudeDto.niveauCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param niveauCode
	 *            the niveauCode to set
	 */
	public void setNiveauCode(String niveauCode) {
		this.niveauCode = niveauCode;
	}

	/**
	 * [DiplomeFinEtudeDto.niveauLibelleLongLt] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the niveauLibelleLongLt
	 */
	public String getNiveauLibelleLongLt() {
		return niveauLibelleLongLt;
	}

	/**
	 * [DiplomeFinEtudeDto.niveauLibelleLongLt] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param niveauLibelleLongLt
	 *            the niveauLibelleLongLt to set
	 */
	public void setNiveauLibelleLongLt(String niveauLibelleLongLt) {
		this.niveauLibelleLongLt = niveauLibelleLongLt;
	}

	/**
	 * [DiplomeFinEtudeDto.dossierInscriptionAdministrativeId] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the dossierInscriptionAdministrativeId
	 */
	public Integer getDossierInscriptionAdministrativeId() {
		return dossierInscriptionAdministrativeId;
	}

	/**
	 * [DiplomeFinEtudeDto.dossierInscriptionAdministrativeId] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param dossierInscriptionAdministrativeId
	 *            the dossierInscriptionAdministrativeId to set
	 */
	public void setDossierInscriptionAdministrativeId(
			Integer dossierInscriptionAdministrativeId) {
		this.dossierInscriptionAdministrativeId = dossierInscriptionAdministrativeId;
	}

	/**
	 * [DiplomeFinEtudeDto.numeroInscription] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the numeroInscription
	 */
	public String getNumeroInscription() {
		return numeroInscription;
	}

	/**
	 * [DiplomeFinEtudeDto.numeroInscription] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param numeroInscription
	 *            the numeroInscription to set
	 */
	public void setNumeroInscription(String numeroInscription) {
		this.numeroInscription = numeroInscription;
	}

	/**
	 * [DiplomeFinEtudeDto.dossierEtudiantId] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the dossierEtudiantId
	 */
	public Integer getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [DiplomeFinEtudeDto.dossierEtudiantId] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param dossierEtudiantId
	 *            the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(Integer dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	/**
	 * [DiplomeFinEtudeDto.numeroMatricule] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the numeroMatricule
	 */
	public String getNumeroMatricule() {
		return numeroMatricule;
	}

	/**
	 * [DiplomeFinEtudeDto.numeroMatricule] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param numeroMatricule
	 *            the numeroMatricule to set
	 */
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}

	/**
	 * [DiplomeFinEtudeDto.ouvertureOffreFormationId] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the ouvertureOffreFormationId
	 */
	public Integer getOuvertureOffreFormationId() {
		return ouvertureOffreFormationId;
	}

	/**
	 * [DiplomeFinEtudeDto.ouvertureOffreFormationId] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param ouvertureOffreFormationId
	 *            the ouvertureOffreFormationId to set
	 */
	public void setOuvertureOffreFormationId(Integer ouvertureOffreFormationId) {
		this.ouvertureOffreFormationId = ouvertureOffreFormationId;
	}

	/**
	 * [DiplomeFinEtudeDto.offreFormationId] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the offreFormationId
	 */
	public Integer getOffreFormationId() {
		return offreFormationId;
	}

	/**
	 * [DiplomeFinEtudeDto.offreFormationId] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param offreFormationId
	 *            the offreFormationId to set
	 */
	public void setOffreFormationId(Integer offreFormationId) {
		this.offreFormationId = offreFormationId;
	}

	/**
	 * [DiplomeFinEtudeDto.offreFormationCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the offreFormationCode
	 */
	public String getOffreFormationCode() {
		return offreFormationCode;
	}

	/**
	 * [DiplomeFinEtudeDto.offreFormationCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param offreFormationCode
	 *            the offreFormationCode to set
	 */
	public void setOffreFormationCode(String offreFormationCode) {
		this.offreFormationCode = offreFormationCode;
	}

	/**
	 * [DiplomeFinEtudeDto.offreFormationLibelleFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the offreFormationLibelleFr
	 */
	public String getOffreFormationLibelleFr() {
		return offreFormationLibelleFr;
	}

	/**
	 * [DiplomeFinEtudeDto.offreFormationLibelleFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param offreFormationLibelleFr
	 *            the offreFormationLibelleFr to set
	 */
	public void setOffreFormationLibelleFr(String offreFormationLibelleFr) {
		this.offreFormationLibelleFr = offreFormationLibelleFr;
	}

	/**
	 * [DiplomeFinEtudeDto.offreFormationLibelleAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the offreFormationLibelleAr
	 */
	public String getOffreFormationLibelleAr() {
		return offreFormationLibelleAr;
	}

	/**
	 * [DiplomeFinEtudeDto.offreFormationLibelleAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param offreFormationLibelleAr
	 *            the offreFormationLibelleAr to set
	 */
	public void setOffreFormationLibelleAr(String offreFormationLibelleAr) {
		this.offreFormationLibelleAr = offreFormationLibelleAr;
	}

	/**
	 * [DiplomeFinEtudeDto.offreFormationrefCodeTypeFormation] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the offreFormationrefCodeTypeFormation
	 */
	public String getOffreFormationrefCodeTypeFormation() {
		return offreFormationrefCodeTypeFormation;
	}

	/**
	 * [DiplomeFinEtudeDto.offreFormationrefCodeTypeFormation] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param offreFormationrefCodeTypeFormation
	 *            the offreFormationrefCodeTypeFormation to set
	 */
	public void setOffreFormationrefCodeTypeFormation(
			String offreFormationrefCodeTypeFormation) {
		this.offreFormationrefCodeTypeFormation = offreFormationrefCodeTypeFormation;
	}

	/**
	 * [DiplomeFinEtudeDto.individuId] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the individuId
	 */
	public Integer getIndividuId() {
		return individuId;
	}

	/**
	 * [DiplomeFinEtudeDto.individuId] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param individuId
	 *            the individuId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}

	/**
	 * [DiplomeFinEtudeDto.nin] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the nin
	 */
	public String getNin() {
		return nin;
	}

	/**
	 * [DiplomeFinEtudeDto.nin] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param nin
	 *            the nin to set
	 */
	public void setNin(String nin) {
		this.nin = nin;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleNiveauArabe] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the refLibelleNiveauArabe
	 */
	public String getRefLibelleNiveauArabe() {
		return refLibelleNiveauArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleNiveauArabe] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param refLibelleNiveauArabe
	 *            the refLibelleNiveauArabe to set
	 */
	public void setRefLibelleNiveauArabe(String refLibelleNiveauArabe) {
		this.refLibelleNiveauArabe = refLibelleNiveauArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleDomaineArabe] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the refLibelleDomaineArabe
	 */
	public String getRefLibelleDomaineArabe() {
		return refLibelleDomaineArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleDomaineArabe] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param refLibelleDomaineArabe
	 *            the refLibelleDomaineArabe to set
	 */
	public void setRefLibelleDomaineArabe(String refLibelleDomaineArabe) {
		this.refLibelleDomaineArabe = refLibelleDomaineArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleFiliereArabe] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the refLibelleFiliereArabe
	 */
	public String getRefLibelleFiliereArabe() {
		return refLibelleFiliereArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleFiliereArabe] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param refLibelleFiliereArabe
	 *            the refLibelleFiliereArabe to set
	 */
	public void setRefLibelleFiliereArabe(String refLibelleFiliereArabe) {
		this.refLibelleFiliereArabe = refLibelleFiliereArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleSpecialiteArabe] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the refLibelleSpecialiteArabe
	 */
	public String getRefLibelleSpecialiteArabe() {
		return refLibelleSpecialiteArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleSpecialiteArabe] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param refLibelleSpecialiteArabe
	 *            the refLibelleSpecialiteArabe to set
	 */
	public void setRefLibelleSpecialiteArabe(String refLibelleSpecialiteArabe) {
		this.refLibelleSpecialiteArabe = refLibelleSpecialiteArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.llEtablissementArabe] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the llEtablissementArabe
	 */
	public String getLlEtablissementArabe() {
		return llEtablissementArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.llEtablissementArabe] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param llEtablissementArabe
	 *            the llEtablissementArabe to set
	 */
	public void setLlEtablissementArabe(String llEtablissementArabe) {
		this.llEtablissementArabe = llEtablissementArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.llEtablissementLatin] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the llEtablissementLatin
	 */
	public String getLlEtablissementLatin() {
		return llEtablissementLatin;
	}

	/**
	 * [DiplomeFinEtudeDto.llEtablissementLatin] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @param llEtablissementLatin
	 *            the llEtablissementLatin to set
	 */
	public void setLlEtablissementLatin(String llEtablissementLatin) {
		this.llEtablissementLatin = llEtablissementLatin;
	}

	/**
	 * [DiplomeFinEtudeDto.serialversionuid] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 12:32:56
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * [DiplomeFinEtudeDto.diplomeFinEtudeEditions] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 15:29:06
	 * @return the diplomeFinEtudeEditions
	 */
	public Set<DiplomeFinEtudeEdition> getDiplomeFinEtudeEditions() {
		return diplomeFinEtudeEditions;
	}

	/**
	 * [DiplomeFinEtudeDto.diplomeFinEtudeEditions] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 oct. 2014 15:29:06
	 * @param diplomeFinEtudeEditions
	 *            the diplomeFinEtudeEditions to set
	 */
	public void setDiplomeFinEtudeEditions(
			Set<DiplomeFinEtudeEdition> diplomeFinEtudeEditions) {
		this.diplomeFinEtudeEditions = diplomeFinEtudeEditions;
	}

	/**
	 * [DiplomeFinEtudeDto.dossierId] Getter
	 * 
	 * @author BELBRIK Oualid on : 8 oct. 2014 11:06:20
	 * @return the dossierId
	 */
	public Integer getDossierId() {
		return dossierId;
	}

	/**
	 * [DiplomeFinEtudeDto.dossierId] Setter
	 * 
	 * @author BELBRIK Oualid on : 8 oct. 2014 11:06:20
	 * @param dossierId
	 *            the dossierId to set
	 */
	public void setDossierId(Integer dossierId) {
		this.dossierId = dossierId;
	}

	/**
	 * [DiplomeFinEtudeDto.refCodeDomaine] Getter
	 * 
	 * @author BELBRIK Oualid on : 8 oct. 2014 11:06:20
	 * @return the refCodeDomaine
	 */
	public String getRefCodeDomaine() {
		return refCodeDomaine;
	}

	/**
	 * [DiplomeFinEtudeDto.refCodeDomaine] Setter
	 * 
	 * @author BELBRIK Oualid on : 8 oct. 2014 11:06:20
	 * @param refCodeDomaine
	 *            the refCodeDomaine to set
	 */
	public void setRefCodeDomaine(String refCodeDomaine) {
		this.refCodeDomaine = refCodeDomaine;
	}

	/**
	 * [DiplomeFinEtudeDto.refCodeFiliere] Getter
	 * 
	 * @author BELBRIK Oualid on : 8 oct. 2014 11:06:20
	 * @return the refCodeFiliere
	 */
	public String getRefCodeFiliere() {
		return refCodeFiliere;
	}

	/**
	 * [DiplomeFinEtudeDto.refCodeFiliere] Setter
	 * 
	 * @author BELBRIK Oualid on : 8 oct. 2014 11:06:20
	 * @param refCodeFiliere
	 *            the refCodeFiliere to set
	 */
	public void setRefCodeFiliere(String refCodeFiliere) {
		this.refCodeFiliere = refCodeFiliere;
	}

	/**
	 * [DiplomeFinEtudeDto.refCodeSpecialite] Getter
	 * 
	 * @author BELBRIK Oualid on : 8 oct. 2014 11:06:20
	 * @return the refCodeSpecialite
	 */
	public String getRefCodeSpecialite() {
		return refCodeSpecialite;
	}

	/**
	 * [DiplomeFinEtudeDto.refCodeSpecialite] Setter
	 * 
	 * @author BELBRIK Oualid on : 8 oct. 2014 11:06:20
	 * @param refCodeSpecialite
	 *            the refCodeSpecialite to set
	 */
	public void setRefCodeSpecialite(String refCodeSpecialite) {
		this.refCodeSpecialite = refCodeSpecialite;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleDomaine] Getter
	 * 
	 * @author BELBRIK Oualid on : 8 oct. 2014 11:06:20
	 * @return the refLibelleDomaine
	 */
	public String getRefLibelleDomaine() {
		return refLibelleDomaine;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleDomaine] Setter
	 * 
	 * @author BELBRIK Oualid on : 8 oct. 2014 11:06:20
	 * @param refLibelleDomaine
	 *            the refLibelleDomaine to set
	 */
	public void setRefLibelleDomaine(String refLibelleDomaine) {
		this.refLibelleDomaine = refLibelleDomaine;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleFiliere] Getter
	 * 
	 * @author BELBRIK Oualid on : 8 oct. 2014 11:06:20
	 * @return the refLibelleFiliere
	 */
	public String getRefLibelleFiliere() {
		return refLibelleFiliere;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleFiliere] Setter
	 * 
	 * @author BELBRIK Oualid on : 8 oct. 2014 11:06:20
	 * @param refLibelleFiliere
	 *            the refLibelleFiliere to set
	 */
	public void setRefLibelleFiliere(String refLibelleFiliere) {
		this.refLibelleFiliere = refLibelleFiliere;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleSpecialite] Getter
	 * 
	 * @author BELBRIK Oualid on : 8 oct. 2014 11:06:20
	 * @return the refLibelleSpecialite
	 */
	public String getRefLibelleSpecialite() {
		return refLibelleSpecialite;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleSpecialite] Setter
	 * 
	 * @author BELBRIK Oualid on : 8 oct. 2014 11:06:20
	 * @param refLibelleSpecialite
	 *            the refLibelleSpecialite to set
	 */
	public void setRefLibelleSpecialite(String refLibelleSpecialite) {
		this.refLibelleSpecialite = refLibelleSpecialite;
	}

	/**
	 * [DiplomeFinEtudeDto.diplomeFinEtudeEditionList] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 oct. 2014 18:45:39
	 * @return the diplomeFinEtudeEditionList
	 */
	public List<DiplomeFinEtudeEditionDto> getDiplomeFinEtudeEditionList() {
		return diplomeFinEtudeEditionList;
	}

	/**
	 * [DiplomeFinEtudeDto.diplomeFinEtudeEditionList] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 oct. 2014 18:45:39
	 * @param diplomeFinEtudeEditionList
	 *            the diplomeFinEtudeEditionList to set
	 */
	public void setDiplomeFinEtudeEditionList(
			List<DiplomeFinEtudeEditionDto> diplomeFinEtudeEditionList) {
		this.diplomeFinEtudeEditionList = diplomeFinEtudeEditionList;
	}

	/**
	 * [DiplomeFinEtudeDto.equivalence] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 oct. 2014 11:19:54
	 * @return the equivalence
	 */
	public String getEquivalence() {
		return equivalence;
	}

	/**
	 * [DiplomeFinEtudeDto.equivalence] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 oct. 2014 11:19:54
	 * @param equivalence
	 *            the equivalence to set
	 */
	public void setEquivalence(String equivalence) {
		this.equivalence = equivalence;
	}

	/**
	 * [DiplomeFinEtudeDto.refMinistere] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 oct. 2014 17:27:21
	 * @return the refMinistere
	 */
	public String getRefMinistere() {
		return refMinistere;
	}

	/**
	 * [DiplomeFinEtudeDto.refMinistere] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 oct. 2014 17:27:21
	 * @param refMinistere
	 *            the refMinistere to set
	 */
	public void setRefMinistere(String refMinistere) {
		this.refMinistere = refMinistere;
	}

	/**
	 * [DiplomeFinEtudeDto.dateSignatureMinistere] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 oct. 2014 17:27:21
	 * @return the dateSignatureMinistere
	 */
	public Date getDateSignatureMinistere() {
		return dateSignatureMinistere;
	}

	/**
	 * [DiplomeFinEtudeDto.dateSignatureMinistere] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 oct. 2014 17:27:21
	 * @param dateSignatureMinistere
	 *            the dateSignatureMinistere to set
	 */
	public void setDateSignatureMinistere(Date dateSignatureMinistere) {
		this.dateSignatureMinistere = dateSignatureMinistere;
	}

	/**
	 * [DiplomeFinEtudeDto.dateSignatureEtablissement] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 oct. 2014 17:27:21
	 * @return the dateSignatureEtablissement
	 */
	public Date getDateSignatureEtablissement() {
		return dateSignatureEtablissement;
	}

	/**
	 * [DiplomeFinEtudeDto.dateSignatureEtablissement] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 oct. 2014 17:27:21
	 * @param dateSignatureEtablissement
	 *            the dateSignatureEtablissement to set
	 */
	public void setDateSignatureEtablissement(Date dateSignatureEtablissement) {
		this.dateSignatureEtablissement = dateSignatureEtablissement;
	}

	/**
	 * [DiplomeFinEtudeDto.autoriteMinistere] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 oct. 2014 17:27:21
	 * @return the autoriteMinistere
	 */
	public String getAutoriteMinistere() {
		return autoriteMinistere;
	}

	/**
	 * [DiplomeFinEtudeDto.autoriteMinistere] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 oct. 2014 17:27:21
	 * @param autoriteMinistere
	 *            the autoriteMinistere to set
	 */
	public void setAutoriteMinistere(String autoriteMinistere) {
		this.autoriteMinistere = autoriteMinistere;
	}

	/**
	 * [DiplomeFinEtudeDto.autoriteEtablissement] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 oct. 2014 17:27:21
	 * @return the autoriteEtablissement
	 */
	public String getAutoriteEtablissement() {
		return autoriteEtablissement;
	}

	/**
	 * [DiplomeFinEtudeDto.autoriteEtablissement] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 oct. 2014 17:27:21
	 * @param autoriteEtablissement
	 *            the autoriteEtablissement to set
	 */
	public void setAutoriteEtablissement(String autoriteEtablissement) {
		this.autoriteEtablissement = autoriteEtablissement;
	}

	/**
	 * [DiplomeFinEtudeDto.estValideEtab] Getter
	 * 
	 * @author BELBRIK Oualid on : 12 oct. 2014 12:44:37
	 * @return the estValideEtab
	 */
	public boolean getEstValideEtab() {
		return estValideEtab;
	}

	/**
	 * [DiplomeFinEtudeDto.estValideEtab] Setter
	 * 
	 * @author BELBRIK Oualid on : 12 oct. 2014 12:44:37
	 * @param estValideEtab
	 *            the estValideEtab to set
	 */
	public void setEstValideEtab(boolean estValideEtab) {
		this.estValideEtab = estValideEtab;
	}

	/**
	 * [DiplomeFinEtudeDto.observationEtab] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 oct. 2014 20:04:10
	 * @return the observationEtab
	 */
	public String getObservationEtab() {
		return observationEtab;
	}

	/**
	 * [DiplomeFinEtudeDto.observationEtab] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 oct. 2014 20:04:11
	 * @param observationEtab
	 *            the observationEtab to set
	 */
	public void setObservationEtab(String observationEtab) {
		this.observationEtab = observationEtab;
	}

	/**
	 * [DiplomeFinEtudeDto.idAnneAcademique] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 oct. 2014 20:56:58
	 * @return the idAnneAcademique
	 */
	public int getIdAnneAcademique() {
		return idAnneAcademique;
	}

	/**
	 * [DiplomeFinEtudeDto.idAnneAcademique] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 oct. 2014 20:56:58
	 * @param idAnneAcademique
	 *            the idAnneAcademique to set
	 */
	public void setIdAnneAcademique(int idAnneAcademique) {
		this.idAnneAcademique = idAnneAcademique;
	}

	/**
	 * [DiplomeFinEtudeDto.dateCreation] Getter
	 * 
	 * @author BELBRIK Oualid on : 25 oct. 2014 11:24:53
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * [DiplomeFinEtudeDto.dateCreation] Setter
	 * 
	 * @author BELBRIK Oualid on : 25 oct. 2014 11:24:53
	 * @param dateCreation
	 *            the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * [DiplomeFinEtudeDto.bilanSessionId] Getter
	 * 
	 * @author BELBRIK Oualid on : 25 oct. 2014 12:09:12
	 * @return the bilanSessionId
	 */
	public Long getBilanSessionId() {
		return bilanSessionId;
	}

	/**
	 * [DiplomeFinEtudeDto.bilanSessionId] Setter
	 * 
	 * @author BELBRIK Oualid on : 25 oct. 2014 12:09:12
	 * @param bilanSessionId
	 *            the bilanSessionId to set
	 */
	public void setBilanSessionId(Long bilanSessionId) {
		this.bilanSessionId = bilanSessionId;
	}

	/**
	 * [DiplomeFinEtudeDto.cycleLibelleLongAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 28 oct. 2014 12:10:24
	 * @return the cycleLibelleLongAr
	 */
	public String getCycleLibelleLongAr() {
		return cycleLibelleLongAr;
	}

	/**
	 * [DiplomeFinEtudeDto.cycleLibelleLongAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 28 oct. 2014 12:10:24
	 * @param cycleLibelleLongAr
	 *            the cycleLibelleLongAr to set
	 */
	public void setCycleLibelleLongAr(String cycleLibelleLongAr) {
		this.cycleLibelleLongAr = cycleLibelleLongAr;
	}

	/**
	 * [DiplomeFinEtudeDto.refCodeTypeFormation] Getter
	 * 
	 * @author BELBRIK Oualid on : 29 oct. 2014 12:50:30
	 * @return the refCodeTypeFormation
	 */
	public String getRefCodeTypeFormation() {
		return refCodeTypeFormation;
	}

	/**
	 * [DiplomeFinEtudeDto.refCodeTypeFormation] Setter
	 * 
	 * @author BELBRIK Oualid on : 29 oct. 2014 12:50:30
	 * @param refCodeTypeFormation
	 *            the refCodeTypeFormation to set
	 */
	public void setRefCodeTypeFormation(String refCodeTypeFormation) {
		this.refCodeTypeFormation = refCodeTypeFormation;
	}

	/**
	 * [DiplomeFinEtudeDto.photo] Getter
	 * 
	 * @author BELBRIK Oualid on : 29 oct. 2014 17:18:12
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * [DiplomeFinEtudeDto.photo] Setter
	 * 
	 * @author BELBRIK Oualid on : 29 oct. 2014 17:18:12
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * [DiplomeFinEtudeDto.estValideFac] Getter
	 * 
	 * @author BELBRIK Oualid on : 2 nov. 2014 17:43:11
	 * @return the estValideFac
	 */
	public boolean getEstValideFac() {
		return estValideFac;
	}

	/**
	 * [DiplomeFinEtudeDto.estValideFac] Setter
	 * 
	 * @author BELBRIK Oualid on : 2 nov. 2014 17:43:11
	 * @param estValideFac
	 *            the estValideFac to set
	 */
	public void setEstValideFac(boolean estValideFac) {
		this.estValideFac = estValideFac;
	}

	/**
	 * [DiplomeFinEtudeDto.dateSignatureFaculte] Getter
	 * 
	 * @author BELBRIK Oualid on : 2 nov. 2014 17:43:11
	 * @return the dateSignatureFaculte
	 */
	public Date getDateSignatureFaculte() {
		return dateSignatureFaculte;
	}

	/**
	 * [DiplomeFinEtudeDto.dateSignatureFaculte] Setter
	 * 
	 * @author BELBRIK Oualid on : 2 nov. 2014 17:43:11
	 * @param dateSignatureFaculte
	 *            the dateSignatureFaculte to set
	 */
	public void setDateSignatureFaculte(Date dateSignatureFaculte) {
		this.dateSignatureFaculte = dateSignatureFaculte;
	}

	/**
	 * [DiplomeFinEtudeDto.generationValide] Getter
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 11:54:31
	 * @return the generationValide
	 */
	public boolean getGenerationValide() {
		return generationValide;
	}

	/**
	 * [DiplomeFinEtudeDto.generationValide] Setter
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 11:54:31
	 * @param generationValide
	 *            the generationValide to set
	 */
	public void setGenerationValide(boolean generationValide) {
		this.generationValide = generationValide;
	}

	/**
	 * [DiplomeFinEtudeDto.refEtab] Getter
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 16:37:25
	 * @return the refEtab
	 */
	public String getRefEtab() {
		return refEtab;
	}

	/**
	 * [DiplomeFinEtudeDto.refEtab] Setter
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 16:37:25
	 * @param refEtab
	 *            the refEtab to set
	 */
	public void setRefEtab(String refEtab) {
		this.refEtab = refEtab;
	}

	/**
	 * [DiplomeFinEtudeDto.refFac] Getter
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 16:37:25
	 * @return the refFac
	 */
	public String getRefFac() {
		return refFac;
	}

	/**
	 * [DiplomeFinEtudeDto.refFac] Setter
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 16:37:25
	 * @param refFac
	 *            the refFac to set
	 */
	public void setRefFac(String refFac) {
		this.refFac = refFac;
	}

	/**
	 * [DiplomeFinEtudeDto.autoriteFac] Getter
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 16:37:25
	 * @return the autoriteFac
	 */
	public String getAutoriteFac() {
		return autoriteFac;
	}

	/**
	 * [DiplomeFinEtudeDto.autoriteFac] Setter
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 16:37:25
	 * @param autoriteFac
	 *            the autoriteFac to set
	 */
	public void setAutoriteFac(String autoriteFac) {
		this.autoriteFac = autoriteFac;
	}

	/**
	 * [DiplomeFinEtudeDto.observationFac] Getter
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 16:37:25
	 * @return the observationFac
	 */
	public String getObservationFac() {
		return observationFac;
	}

	/**
	 * [DiplomeFinEtudeDto.observationFac] Setter
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 16:37:25
	 * @param observationFac
	 *            the observationFac to set
	 */
	public void setObservationFac(String observationFac) {
		this.observationFac = observationFac;
	}

	/**
	 * [DiplomeFinEtudeDto.mentionId] Getter
	 * 
	 * @author BELBRIK Oualid on : 10 nov. 2014 11:36:38
	 * @return the mentionId
	 */
	public Integer getMentionId() {
		return mentionId;
	}

	/**
	 * [DiplomeFinEtudeDto.mentionId] Setter
	 * 
	 * @author BELBRIK Oualid on : 10 nov. 2014 11:36:38
	 * @param mentionId
	 *            the mentionId to set
	 */
	public void setMentionId(Integer mentionId) {
		this.mentionId = mentionId;
	}

	/**
	 * [DiplomeFinEtudeDto.mentionCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 10 nov. 2014 11:36:38
	 * @return the mentionCode
	 */
	public String getMentionCode() {
		return mentionCode;
	}

	/**
	 * [DiplomeFinEtudeDto.mentionCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 10 nov. 2014 11:36:38
	 * @param mentionCode
	 *            the mentionCode to set
	 */
	public void setMentionCode(String mentionCode) {
		this.mentionCode = mentionCode;
	}

	/**
	 * [DiplomeFinEtudeDto.mentionLibelleFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 10 nov. 2014 11:36:38
	 * @return the mentionLibelleFr
	 */
	public String getMentionLibelleFr() {
		return mentionLibelleFr;
	}

	/**
	 * [DiplomeFinEtudeDto.mentionLibelleFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 10 nov. 2014 11:36:38
	 * @param mentionLibelleFr
	 *            the mentionLibelleFr to set
	 */
	public void setMentionLibelleFr(String mentionLibelleFr) {
		this.mentionLibelleFr = mentionLibelleFr;
	}

	/**
	 * [DiplomeFinEtudeDto.mentionLibelleAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 10 nov. 2014 11:36:38
	 * @return the mentionLibelleAr
	 */
	public String getMentionLibelleAr() {
		return mentionLibelleAr;
	}

	/**
	 * [DiplomeFinEtudeDto.mentionLibelleAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 10 nov. 2014 11:36:38
	 * @param mentionLibelleAr
	 *            the mentionLibelleAr to set
	 */
	public void setMentionLibelleAr(String mentionLibelleAr) {
		this.mentionLibelleAr = mentionLibelleAr;
	}

	/**
	 * [DiplomeFinEtudeDto.identifiant] Getter
	 * 
	 * @author BELBRIK Oualid on : 10 nov. 2014 12:41:34
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * [DiplomeFinEtudeDto.identifiant] Setter
	 * 
	 * @author BELBRIK Oualid on : 10 nov. 2014 12:41:34
	 * @param identifiant
	 *            the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleDomaineAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 12 nov. 2014 09:56:53
	 * @return the refLibelleDomaineAr
	 */
	public String getRefLibelleDomaineAr() {
		return refLibelleDomaineAr;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleDomaineAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 12 nov. 2014 09:56:53
	 * @param refLibelleDomaineAr
	 *            the refLibelleDomaineAr to set
	 */
	public void setRefLibelleDomaineAr(String refLibelleDomaineAr) {
		this.refLibelleDomaineAr = refLibelleDomaineAr;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleFiliereAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 12 nov. 2014 09:56:53
	 * @return the refLibelleFiliereAr
	 */
	public String getRefLibelleFiliereAr() {
		return refLibelleFiliereAr;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleFiliereAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 12 nov. 2014 09:56:53
	 * @param refLibelleFiliereAr
	 *            the refLibelleFiliereAr to set
	 */
	public void setRefLibelleFiliereAr(String refLibelleFiliereAr) {
		this.refLibelleFiliereAr = refLibelleFiliereAr;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleSpecialiteAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 12 nov. 2014 09:56:53
	 * @return the refLibelleSpecialiteAr
	 */
	public String getRefLibelleSpecialiteAr() {
		return refLibelleSpecialiteAr;
	}

	/**
	 * [DiplomeFinEtudeDto.refLibelleSpecialiteAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 12 nov. 2014 09:56:53
	 * @param refLibelleSpecialiteAr
	 *            the refLibelleSpecialiteAr to set
	 */
	public void setRefLibelleSpecialiteAr(String refLibelleSpecialiteAr) {
		this.refLibelleSpecialiteAr = refLibelleSpecialiteAr;
	}

	/**
	 * [DiplomeFinEtudeDto.deliberationSessionId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @return the deliberationSessionId
	 */
	public Integer getDeliberationSessionId() {
		return deliberationSessionId;
	}

	/**
	 * [DiplomeFinEtudeDto.deliberationSessionId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @param deliberationSessionId
	 *            the deliberationSessionId to set
	 */
	public void setDeliberationSessionId(Integer deliberationSessionId) {
		this.deliberationSessionId = deliberationSessionId;
	}

	/**
	 * [DiplomeFinEtudeDto.typeDecisionId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @return the typeDecisionId
	 */
	public Integer getTypeDecisionId() {
		return typeDecisionId;
	}

	/**
	 * [DiplomeFinEtudeDto.typeDecisionId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @param typeDecisionId
	 *            the typeDecisionId to set
	 */
	public void setTypeDecisionId(Integer typeDecisionId) {
		this.typeDecisionId = typeDecisionId;
	}

	/**
	 * [DiplomeFinEtudeDto.refCodeTypeSession] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @return the refCodeTypeSession
	 */
	public String getRefCodeTypeSession() {
		return refCodeTypeSession;
	}

	/**
	 * [DiplomeFinEtudeDto.refCodeTypeSession] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @param refCodeTypeSession
	 *            the refCodeTypeSession to set
	 */
	public void setRefCodeTypeSession(String refCodeTypeSession) {
		this.refCodeTypeSession = refCodeTypeSession;
	}

	/**
	 * [DiplomeFinEtudeDto.etudiantNomArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @return the etudiantNomArabe
	 */
	public String getEtudiantNomArabe() {
		return etudiantNomArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.etudiantNomArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @param etudiantNomArabe
	 *            the etudiantNomArabe to set
	 */
	public void setEtudiantNomArabe(String etudiantNomArabe) {
		this.etudiantNomArabe = etudiantNomArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.etudiantNomLatin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @return the etudiantNomLatin
	 */
	public String getEtudiantNomLatin() {
		return etudiantNomLatin;
	}

	/**
	 * [DiplomeFinEtudeDto.etudiantNomLatin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @param etudiantNomLatin
	 *            the etudiantNomLatin to set
	 */
	public void setEtudiantNomLatin(String etudiantNomLatin) {
		this.etudiantNomLatin = etudiantNomLatin;
	}

	/**
	 * [DiplomeFinEtudeDto.etudiantPrenomArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @return the etudiantPrenomArabe
	 */
	public String getEtudiantPrenomArabe() {
		return etudiantPrenomArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.etudiantPrenomArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @param etudiantPrenomArabe
	 *            the etudiantPrenomArabe to set
	 */
	public void setEtudiantPrenomArabe(String etudiantPrenomArabe) {
		this.etudiantPrenomArabe = etudiantPrenomArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.etudiantPrenomLatin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @return the etudiantPrenomLatin
	 */
	public String getEtudiantPrenomLatin() {
		return etudiantPrenomLatin;
	}

	/**
	 * [DiplomeFinEtudeDto.etudiantPrenomLatin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @param etudiantPrenomLatin
	 *            the etudiantPrenomLatin to set
	 */
	public void setEtudiantPrenomLatin(String etudiantPrenomLatin) {
		this.etudiantPrenomLatin = etudiantPrenomLatin;
	}

	/**
	 * [DiplomeFinEtudeDto.etudiantDateNaissance] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @return the etudiantDateNaissance
	 */
	public Date getEtudiantDateNaissance() {
		return etudiantDateNaissance;
	}

	/**
	 * [DiplomeFinEtudeDto.etudiantDateNaissance] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @param etudiantDateNaissance
	 *            the etudiantDateNaissance to set
	 */
	public void setEtudiantDateNaissance(Date etudiantDateNaissance) {
		this.etudiantDateNaissance = etudiantDateNaissance;
	}

	/**
	 * [DiplomeFinEtudeDto.etudiantLieuNaissance] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @return the etudiantLieuNaissance
	 */
	public String getEtudiantLieuNaissance() {
		return etudiantLieuNaissance;
	}

	/**
	 * [DiplomeFinEtudeDto.etudiantLieuNaissance] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 12:26:46
	 * @param etudiantLieuNaissance
	 *            the etudiantLieuNaissance to set
	 */
	public void setEtudiantLieuNaissance(String etudiantLieuNaissance) {
		this.etudiantLieuNaissance = etudiantLieuNaissance;
	}

	/**
	 * [DiplomeFinEtudeDto.anneeAcademiqueCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 13:22:19
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}

	/**
	 * [DiplomeFinEtudeDto.anneeAcademiqueCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 13:22:19
	 * @param anneeAcademiqueCode
	 *            the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	/**
	 * [DiplomeFinEtudeDto.moyenne] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2015 10:53:48
	 * @return the moyenne
	 */
	public double getMoyenne() {
		return moyenne;
	}

	/**
	 * [DiplomeFinEtudeDto.moyenne] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2015 10:53:48
	 * @param moyenne
	 *            the moyenne to set
	 */
	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}

	/**
	 * [DiplomeFinEtudeDto.libelleDiplomePrincipalArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 08:37:03
	 * @return the libelleDiplomePrincipalArabe
	 */
	public String getLibelleDiplomePrincipalArabe() {
		return libelleDiplomePrincipalArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.libelleDiplomePrincipalArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 08:37:04
	 * @param libelleDiplomePrincipalArabe
	 *            the libelleDiplomePrincipalArabe to set
	 */
	public void setLibelleDiplomePrincipalArabe(
			String libelleDiplomePrincipalArabe) {
		this.libelleDiplomePrincipalArabe = libelleDiplomePrincipalArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.libelleDiplomeSecondaireArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 08:37:04
	 * @return the libelleDiplomeSecondaireArabe
	 */
	public String getLibelleDiplomeSecondaireArabe() {
		return libelleDiplomeSecondaireArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.libelleDiplomeSecondaireArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 08:37:04
	 * @param libelleDiplomeSecondaireArabe
	 *            the libelleDiplomeSecondaireArabe to set
	 */
	public void setLibelleDiplomeSecondaireArabe(
			String libelleDiplomeSecondaireArabe) {
		this.libelleDiplomeSecondaireArabe = libelleDiplomeSecondaireArabe;
	}

	/**
	 * [DiplomeFinEtudeDto.libelleDiplomePrincipalLatin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 08:37:04
	 * @return the libelleDiplomePrincipalLatin
	 */
	public String getLibelleDiplomePrincipalLatin() {
		return libelleDiplomePrincipalLatin;
	}

	/**
	 * [DiplomeFinEtudeDto.libelleDiplomePrincipalLatin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 08:37:04
	 * @param libelleDiplomePrincipalLatin
	 *            the libelleDiplomePrincipalLatin to set
	 */
	public void setLibelleDiplomePrincipalLatin(
			String libelleDiplomePrincipalLatin) {
		this.libelleDiplomePrincipalLatin = libelleDiplomePrincipalLatin;
	}

	/**
	 * [DiplomeFinEtudeDto.libelleDiplomeSecondaireLatin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 08:37:04
	 * @return the libelleDiplomeSecondaireLatin
	 */
	public String getLibelleDiplomeSecondaireLatin() {
		return libelleDiplomeSecondaireLatin;
	}

	/**
	 * [DiplomeFinEtudeDto.libelleDiplomeSecondaireLatin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 08:37:04
	 * @param libelleDiplomeSecondaireLatin
	 *            the libelleDiplomeSecondaireLatin to set
	 */
	public void setLibelleDiplomeSecondaireLatin(
			String libelleDiplomeSecondaireLatin) {
		this.libelleDiplomeSecondaireLatin = libelleDiplomeSecondaireLatin;
	}

}
