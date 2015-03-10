/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuDto.java] 
 * @author rlaib on : 12 oct. 2014  15:06:05
 */
package dz.gov.mesrs.sii.fve.business.model.dto.scolarite;

import java.util.Date;

/**
 * @author rlaib  on : 12 oct. 2014  15:06:05
 */
public class EnseignantServiceFaitDto implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 oct. 2014  16:09:45
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date dateSeance;
	private Date heureDebutSeance;
	private Date heureFinSeance;
	private Double dureeSeance;
	private Integer nbEtudiantsPresents;
	private String motif;
	private String observation;
	private String justificatifMotif;
	
	// Type seance
	private Integer seanceTypeId;
	private String  seanceTypeCode;
	private String  seanceTypeLibelleFr;

	// Charge Pedagogique
	// Charge Pedagogique -Groupe Pedagogique
	private Integer chargeId;
	private Integer chargeGroupePedagogiqueId;
	private String chargeGroupePedagogiqueLibelle;
	
	// Charge Pedagogique -Matiere
	private Integer chargeMatiereId;
	private String chargeMatiereLibelle;
	
	// Charge Pedagogique -Niveau
	private Integer chargeNiveauId;
	private String chargeNiveauCode;
	private String chargeNiveauLibelle;
	
	// Charge Pedagogique- Peridoe
	private Integer chargePeriodeId;
	private String chargePeriodeCode;
	private String chargePeriodeLibelle;
	
	// Charge Pedagogique - AP
	private Integer chargeApId;
	private String chargeApCodeType;
	private String chargeApLibelle;
	private String chargeTypeEnseignement;
	
	// Situation
	private Integer idSituation;
	private String styleCssSituation;
	private String libelleSituation;
	
	// Offre de formation
	private Integer offreFormationId;
	private String offreFormationCode;
	private String offreFormationLibelle;
	
	// Etablissement
	private Integer idEtablissement;
	private String codeEtablissement;
	private String libelleEtablissement;
	
	public EnseignantServiceFaitDto() {
		
	}

	/**
	 * [EnseignantServiceFaitDto.id] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [EnseignantServiceFaitDto.id] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EnseignantServiceFaitDto.dateSeance] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the dateSeance
	 */
	public Date getDateSeance() {
		return dateSeance;
	}

	/**
	 * [EnseignantServiceFaitDto.dateSeance] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param dateSeance the dateSeance to set
	 */
	public void setDateSeance(Date dateSeance) {
		this.dateSeance = dateSeance;
	}

	/**
	 * [EnseignantServiceFaitDto.heureDebutSeance] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the heureDebutSeance
	 */
	public Date getHeureDebutSeance() {
		return heureDebutSeance;
	}

	/**
	 * [EnseignantServiceFaitDto.heureDebutSeance] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param heureDebutSeance the heureDebutSeance to set
	 */
	public void setHeureDebutSeance(Date heureDebutSeance) {
		this.heureDebutSeance = heureDebutSeance;
	}

	/**
	 * [EnseignantServiceFaitDto.heureFinSeance] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the heureFinSeance
	 */
	public Date getHeureFinSeance() {
		return heureFinSeance;
	}

	/**
	 * [EnseignantServiceFaitDto.heureFinSeance] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param heureFinSeance the heureFinSeance to set
	 */
	public void setHeureFinSeance(Date heureFinSeance) {
		this.heureFinSeance = heureFinSeance;
	}

	/**
	 * [EnseignantServiceFaitDto.dureeSeance] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the dureeSeance
	 */
	public Double getDureeSeance() {
		return dureeSeance;
	}

	/**
	 * [EnseignantServiceFaitDto.dureeSeance] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param dureeSeance the dureeSeance to set
	 */
	public void setDureeSeance(Double dureeSeance) {
		this.dureeSeance = dureeSeance;
	}

	/**
	 * [EnseignantServiceFaitDto.nbEtudiantsPresents] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the nbEtudiantsPresents
	 */
	public Integer getNbEtudiantsPresents() {
		return nbEtudiantsPresents;
	}

	/**
	 * [EnseignantServiceFaitDto.nbEtudiantsPresents] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param nbEtudiantsPresents the nbEtudiantsPresents to set
	 */
	public void setNbEtudiantsPresents(Integer nbEtudiantsPresents) {
		this.nbEtudiantsPresents = nbEtudiantsPresents;
	}

	/**
	 * [EnseignantServiceFaitDto.motif] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the motif
	 */
	public String getMotif() {
		return motif;
	}

	/**
	 * [EnseignantServiceFaitDto.motif] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}

	/**
	 * [EnseignantServiceFaitDto.observation] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * [EnseignantServiceFaitDto.observation] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * [EnseignantServiceFaitDto.justificatifMotif] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the justificatifMotif
	 */
	public String getJustificatifMotif() {
		return justificatifMotif;
	}

	/**
	 * [EnseignantServiceFaitDto.justificatifMotif] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param justificatifMotif the justificatifMotif to set
	 */
	public void setJustificatifMotif(String justificatifMotif) {
		this.justificatifMotif = justificatifMotif;
	}

	/**
	 * [EnseignantServiceFaitDto.seanceTypeId] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the seanceTypeId
	 */
	public Integer getSeanceTypeId() {
		return seanceTypeId;
	}

	/**
	 * [EnseignantServiceFaitDto.seanceTypeId] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param seanceTypeId the seanceTypeId to set
	 */
	public void setSeanceTypeId(Integer seanceTypeId) {
		this.seanceTypeId = seanceTypeId;
	}

	/**
	 * [EnseignantServiceFaitDto.seanceTypeCode] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the seanceTypeCode
	 */
	public String getSeanceTypeCode() {
		return seanceTypeCode;
	}

	/**
	 * [EnseignantServiceFaitDto.seanceTypeCode] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param seanceTypeCode the seanceTypeCode to set
	 */
	public void setSeanceTypeCode(String seanceTypeCode) {
		this.seanceTypeCode = seanceTypeCode;
	}

	/**
	 * [EnseignantServiceFaitDto.seanceTypeLibelleFr] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the seanceTypeLibelleFr
	 */
	public String getSeanceTypeLibelleFr() {
		return seanceTypeLibelleFr;
	}

	/**
	 * [EnseignantServiceFaitDto.seanceTypeLibelleFr] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param seanceTypeLibelleFr the seanceTypeLibelleFr to set
	 */
	public void setSeanceTypeLibelleFr(String seanceTypeLibelleFr) {
		this.seanceTypeLibelleFr = seanceTypeLibelleFr;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeId] Getter 
	 * @author rlaib on : 29 oct. 2014  12:15:42
	 * @return the chargeId
	 */
	public Integer getChargeId() {
		return chargeId;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeId] Setter 
	 * @author rlaib on : 29 oct. 2014  12:15:42
	 * @param chargeId the chargeId to set
	 */
	public void setChargeId(Integer chargeId) {
		this.chargeId = chargeId;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeGroupePedagogiqueId] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the chargeGroupePedagogiqueId
	 */
	public Integer getChargeGroupePedagogiqueId() {
		return chargeGroupePedagogiqueId;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeGroupePedagogiqueId] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param chargeGroupePedagogiqueId the chargeGroupePedagogiqueId to set
	 */
	public void setChargeGroupePedagogiqueId(Integer chargeGroupePedagogiqueId) {
		this.chargeGroupePedagogiqueId = chargeGroupePedagogiqueId;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeGroupePedagogiqueLibelle] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the chargeGroupePedagogiqueLibelle
	 */
	public String getChargeGroupePedagogiqueLibelle() {
		return chargeGroupePedagogiqueLibelle;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeGroupePedagogiqueLibelle] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param chargeGroupePedagogiqueLibelle the chargeGroupePedagogiqueLibelle to set
	 */
	public void setChargeGroupePedagogiqueLibelle(
			String chargeGroupePedagogiqueLibelle) {
		this.chargeGroupePedagogiqueLibelle = chargeGroupePedagogiqueLibelle;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeMatiereId] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the chargeMatiereId
	 */
	public Integer getChargeMatiereId() {
		return chargeMatiereId;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeMatiereId] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param chargeMatiereId the chargeMatiereId to set
	 */
	public void setChargeMatiereId(Integer chargeMatiereId) {
		this.chargeMatiereId = chargeMatiereId;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeMatiereLibelle] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the chargeMatiereLibelle
	 */
	public String getChargeMatiereLibelle() {
		return chargeMatiereLibelle;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeMatiereLibelle] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param chargeMatiereLibelle the chargeMatiereLibelle to set
	 */
	public void setChargeMatiereLibelle(String chargeMatiereLibelle) {
		this.chargeMatiereLibelle = chargeMatiereLibelle;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeNiveauId] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the chargeNiveauId
	 */
	public Integer getChargeNiveauId() {
		return chargeNiveauId;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeNiveauId] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param chargeNiveauId the chargeNiveauId to set
	 */
	public void setChargeNiveauId(Integer chargeNiveauId) {
		this.chargeNiveauId = chargeNiveauId;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeNiveauCode] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the chargeNiveauCode
	 */
	public String getChargeNiveauCode() {
		return chargeNiveauCode;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeNiveauCode] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param chargeNiveauCode the chargeNiveauCode to set
	 */
	public void setChargeNiveauCode(String chargeNiveauCode) {
		this.chargeNiveauCode = chargeNiveauCode;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeNiveauLibelle] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the chargeNiveauLibelle
	 */
	public String getChargeNiveauLibelle() {
		return chargeNiveauLibelle;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeNiveauLibelle] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param chargeNiveauLibelle the chargeNiveauLibelle to set
	 */
	public void setChargeNiveauLibelle(String chargeNiveauLibelle) {
		this.chargeNiveauLibelle = chargeNiveauLibelle;
	}

	/**
	 * [EnseignantServiceFaitDto.chargePeriodeId] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the chargePeriodeId
	 */
	public Integer getChargePeriodeId() {
		return chargePeriodeId;
	}

	/**
	 * [EnseignantServiceFaitDto.chargePeriodeId] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param chargePeriodeId the chargePeriodeId to set
	 */
	public void setChargePeriodeId(Integer chargePeriodeId) {
		this.chargePeriodeId = chargePeriodeId;
	}

	/**
	 * [EnseignantServiceFaitDto.chargePeriodeCode] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the chargePeriodeCode
	 */
	public String getChargePeriodeCode() {
		return chargePeriodeCode;
	}

	/**
	 * [EnseignantServiceFaitDto.chargePeriodeCode] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param chargePeriodeCode the chargePeriodeCode to set
	 */
	public void setChargePeriodeCode(String chargePeriodeCode) {
		this.chargePeriodeCode = chargePeriodeCode;
	}

	/**
	 * [EnseignantServiceFaitDto.chargePeriodeLibelle] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the chargePeriodeLibelle
	 */
	public String getChargePeriodeLibelle() {
		return chargePeriodeLibelle;
	}

	/**
	 * [EnseignantServiceFaitDto.chargePeriodeLibelle] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param chargePeriodeLibelle the chargePeriodeLibelle to set
	 */
	public void setChargePeriodeLibelle(String chargePeriodeLibelle) {
		this.chargePeriodeLibelle = chargePeriodeLibelle;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeApId] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the chargeApId
	 */
	public Integer getChargeApId() {
		return chargeApId;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeApId] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param chargeApId the chargeApId to set
	 */
	public void setChargeApId(Integer chargeApId) {
		this.chargeApId = chargeApId;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeApCodeType] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the chargeApCodeType
	 */
	public String getChargeApCodeType() {
		return chargeApCodeType;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeApCodeType] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param chargeApCodeType the chargeApCodeType to set
	 */
	public void setChargeApCodeType(String chargeApCodeType) {
		this.chargeApCodeType = chargeApCodeType;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeApLibelle] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the chargeApLibelle
	 */
	public String getChargeApLibelle() {
		return chargeApLibelle;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeApLibelle] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param chargeApLibelle the chargeApLibelle to set
	 */
	public void setChargeApLibelle(String chargeApLibelle) {
		this.chargeApLibelle = chargeApLibelle;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeTypeEnseignement] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the chargeTypeEnseignement
	 */
	public String getChargeTypeEnseignement() {
		return chargeTypeEnseignement;
	}

	/**
	 * [EnseignantServiceFaitDto.chargeTypeEnseignement] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param chargeTypeEnseignement the chargeTypeEnseignement to set
	 */
	public void setChargeTypeEnseignement(String chargeTypeEnseignement) {
		this.chargeTypeEnseignement = chargeTypeEnseignement;
	}

	/**
	 * [EnseignantServiceFaitDto.idSituation] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the idSituation
	 */
	public Integer getIdSituation() {
		return idSituation;
	}

	/**
	 * [EnseignantServiceFaitDto.idSituation] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param idSituation the idSituation to set
	 */
	public void setIdSituation(Integer idSituation) {
		this.idSituation = idSituation;
	}

	/**
	 * [EnseignantServiceFaitDto.styleCssSituation] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the styleCssSituation
	 */
	public String getStyleCssSituation() {
		return styleCssSituation;
	}

	/**
	 * [EnseignantServiceFaitDto.styleCssSituation] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param styleCssSituation the styleCssSituation to set
	 */
	public void setStyleCssSituation(String styleCssSituation) {
		this.styleCssSituation = styleCssSituation;
	}

	/**
	 * [EnseignantServiceFaitDto.libelleSituation] Getter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @return the libelleSituation
	 */
	public String getLibelleSituation() {
		return libelleSituation;
	}

	/**
	 * [EnseignantServiceFaitDto.libelleSituation] Setter 
	 * @author rlaib on : 29 oct. 2014  12:09:30
	 * @param libelleSituation the libelleSituation to set
	 */
	public void setLibelleSituation(String libelleSituation) {
		this.libelleSituation = libelleSituation;
	}

	
	/**
	 * [EnseignantServiceFaitDto.offreFormationId] Getter 
	 * @author Rafik on : 7 déc. 2014  19:04:10
	 * @return the offreFormationId
	 */
	public Integer getOffreFormationId() {
		return offreFormationId;
	}

	/**
	 * [EnseignantServiceFaitDto.offreFormationId] Setter 
	 * @author Rafik on : 7 déc. 2014  19:04:10
	 * @param offreFormationId the offreFormationId to set
	 */
	public void setOffreFormationId(Integer offreFormationId) {
		this.offreFormationId = offreFormationId;
	}

	/**
	 * [EnseignantServiceFaitDto.offreFormationCode] Getter 
	 * @author Rafik on : 7 déc. 2014  19:04:10
	 * @return the offreFormationCode
	 */
	public String getOffreFormationCode() {
		return offreFormationCode;
	}

	/**
	 * [EnseignantServiceFaitDto.offreFormationCode] Setter 
	 * @author Rafik on : 7 déc. 2014  19:04:10
	 * @param offreFormationCode the offreFormationCode to set
	 */
	public void setOffreFormationCode(String offreFormationCode) {
		this.offreFormationCode = offreFormationCode;
	}

	/**
	 * [EnseignantServiceFaitDto.offreFormationLibelle] Getter 
	 * @author Rafik on : 7 déc. 2014  19:04:10
	 * @return the offreFormationLibelle
	 */
	public String getOffreFormationLibelle() {
		return offreFormationLibelle;
	}

	/**
	 * [EnseignantServiceFaitDto.offreFormationLibelle] Setter 
	 * @author Rafik on : 7 déc. 2014  19:04:10
	 * @param offreFormationLibelle the offreFormationLibelle to set
	 */
	public void setOffreFormationLibelle(String offreFormationLibelle) {
		this.offreFormationLibelle = offreFormationLibelle;
	}

	/**
	 * [EnseignantServiceFaitDto.idEtablissement] Getter 
	 * @author Rafik on : 7 déc. 2014  19:04:10
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [EnseignantServiceFaitDto.idEtablissement] Setter 
	 * @author Rafik on : 7 déc. 2014  19:04:10
	 * @param idEtablissement the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * [EnseignantServiceFaitDto.codeEtablissement] Getter 
	 * @author Rafik on : 7 déc. 2014  19:04:10
	 * @return the codeEtablissement
	 */
	public String getCodeEtablissement() {
		return codeEtablissement;
	}

	/**
	 * [EnseignantServiceFaitDto.codeEtablissement] Setter 
	 * @author Rafik on : 7 déc. 2014  19:04:10
	 * @param codeEtablissement the codeEtablissement to set
	 */
	public void setCodeEtablissement(String codeEtablissement) {
		this.codeEtablissement = codeEtablissement;
	}

	/**
	 * [EnseignantServiceFaitDto.libelleEtablissement] Getter 
	 * @author Rafik on : 7 déc. 2014  19:04:10
	 * @return the libelleEtablissement
	 */
	public String getLibelleEtablissement() {
		return libelleEtablissement;
	}

	/**
	 * [EnseignantServiceFaitDto.libelleEtablissement] Setter 
	 * @author Rafik on : 7 déc. 2014  19:04:10
	 * @param libelleEtablissement the libelleEtablissement to set
	 */
	public void setLibelleEtablissement(String libelleEtablissement) {
		this.libelleEtablissement = libelleEtablissement;
	}

	
}
