package dz.gov.mesrs.sii.fve.business.model.dto.cursus;



import java.util.Date;


/**
 * @author BELDI Jamel  on : 14 juil. 2014  15:21:15
 */

public class ParcoursIndividualiseMatiereDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 14 juil. 2014  15:20:53
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private boolean choix;
	private String remarque;
	private Date dateRemarque;
	
//	private ParcoursIndividualise parcoursIndividualise;
	private Integer parcoursIndividualiseId;
	private Integer dossierInscriptionAdministrativeId;
	
//	private SituationEntite situationEntiteId;
	private Date dateCreation;
	//Situation
	private Integer situationId;
	private String situationLibelleFr;
	private String situationLibelleAr;
//	private RattachementMc rattachementMc;
	private Integer rattachementMcId;
	private Integer ueId;
	private String ueCode;
	private String ueLibelleFr;
	private Integer ueCredits;
	//private Mc mc;
	private Integer mcId;
	private String mcCode;
	private String mcLibelleFr;
	private Integer mcCredits;
	private Double coefficient;
	private Double noteObtension;
	private Double noteDeBase;
	private Double noteEliminatoire;

	private Boolean optionnelle;
	private Boolean aChoix;
	//
	private Integer anneeAcademiqueId;
	/**
	 * [ParcoursIndividualiseMatiereDto.anneeAcademiqueId] Getter 
	 * @author BELDI Jamel on : 8 sept. 2014  09:31:58
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.anneeAcademiqueId] Setter 
	 * @author BELDI Jamel on : 8 sept. 2014  09:31:58
	 * @param anneeAcademiqueId the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.anneeAcademiqueCode] Getter 
	 * @author BELDI Jamel on : 8 sept. 2014  09:31:58
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.anneeAcademiqueCode] Setter 
	 * @author BELDI Jamel on : 8 sept. 2014  09:31:58
	 * @param anneeAcademiqueCode the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}




	private String anneeAcademiqueCode;
	
	//Periode
		private Integer idPeriode;
		private String codePeriode;
		private String libellePeriode;

	public ParcoursIndividualiseMatiereDto() {
	}

	

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	
	public boolean isChoix() {
		return this.choix;
	}

	public void setChoix(boolean choix) {
		this.choix = choix;
	}

	
	public String getRemarque() {
		return this.remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	
	public Date getDateRemarque() {
		return this.dateRemarque;
	}

	public void setDateRemarque(Date dateRemarque) {
		this.dateRemarque = dateRemarque;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.parcoursIndividualiseId] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the parcoursIndividualiseId
	 */
	public Integer getParcoursIndividualiseId() {
		return parcoursIndividualiseId;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.parcoursIndividualiseId] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param parcoursIndividualiseId the parcoursIndividualiseId to set
	 */
	public void setParcoursIndividualiseId(Integer parcoursIndividualiseId) {
		this.parcoursIndividualiseId = parcoursIndividualiseId;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.dossierInscriptionAdministrativeId] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the dossierInscriptionAdministrativeId
	 */
	public Integer getDossierInscriptionAdministrativeId() {
		return dossierInscriptionAdministrativeId;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.dossierInscriptionAdministrativeId] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param dossierInscriptionAdministrativeId the dossierInscriptionAdministrativeId to set
	 */
	public void setDossierInscriptionAdministrativeId(
			Integer dossierInscriptionAdministrativeId) {
		this.dossierInscriptionAdministrativeId = dossierInscriptionAdministrativeId;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.dateCreation] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.dateCreation] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.situationId] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the situationId
	 */
	public Integer getSituationId() {
		return situationId;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.situationId] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param situationId the situationId to set
	 */
	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.situationLibelleFr] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the situationLibelleFr
	 */
	public String getSituationLibelleFr() {
		return situationLibelleFr;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.situationLibelleFr] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param situationLibelleFr the situationLibelleFr to set
	 */
	public void setSituationLibelleFr(String situationLibelleFr) {
		this.situationLibelleFr = situationLibelleFr;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.situationLibelleAr] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the situationLibelleAr
	 */
	public String getSituationLibelleAr() {
		return situationLibelleAr;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.situationLibelleAr] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param situationLibelleAr the situationLibelleAr to set
	 */
	public void setSituationLibelleAr(String situationLibelleAr) {
		this.situationLibelleAr = situationLibelleAr;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.rattachementMcId] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the rattachementMcId
	 */
	public Integer getRattachementMcId() {
		return rattachementMcId;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.rattachementMcId] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param rattachementMcId the rattachementMcId to set
	 */
	public void setRattachementMcId(Integer rattachementMcId) {
		this.rattachementMcId = rattachementMcId;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.ueId] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the ueId
	 */
	public Integer getUeId() {
		return ueId;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.ueId] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param ueId the ueId to set
	 */
	public void setUeId(Integer ueId) {
		this.ueId = ueId;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.ueCode] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the ueCode
	 */
	public String getUeCode() {
		return ueCode;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.ueCode] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param ueCode the ueCode to set
	 */
	public void setUeCode(String ueCode) {
		this.ueCode = ueCode;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.ueLibelleFr] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the ueLibelleFr
	 */
	public String getUeLibelleFr() {
		return ueLibelleFr;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.ueLibelleFr] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param ueLibelleFr the ueLibelleFr to set
	 */
	public void setUeLibelleFr(String ueLibelleFr) {
		this.ueLibelleFr = ueLibelleFr;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.ueCredits] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the ueCredits
	 */
	public Integer getUeCredits() {
		return ueCredits;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.ueCredits] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param ueCredits the ueCredits to set
	 */
	public void setUeCredits(Integer ueCredits) {
		this.ueCredits = ueCredits;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.mcId] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the mcId
	 */
	public Integer getMcId() {
		return mcId;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.mcId] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param mcId the mcId to set
	 */
	public void setMcId(Integer mcId) {
		this.mcId = mcId;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.mcCode] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the mcCode
	 */
	public String getMcCode() {
		return mcCode;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.mcCode] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param mcCode the mcCode to set
	 */
	public void setMcCode(String mcCode) {
		this.mcCode = mcCode;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.mcLibelleFr] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the mcLibelleFr
	 */
	public String getMcLibelleFr() {
		return mcLibelleFr;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.mcLibelleFr] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param mcLibelleFr the mcLibelleFr to set
	 */
	public void setMcLibelleFr(String mcLibelleFr) {
		this.mcLibelleFr = mcLibelleFr;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.mcCredits] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the mcCredits
	 */
	public Integer getMcCredits() {
		return mcCredits;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.mcCredits] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param mcCredits the mcCredits to set
	 */
	public void setMcCredits(Integer mcCredits) {
		this.mcCredits = mcCredits;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.coefficient] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the coefficient
	 */
	public Double getCoefficient() {
		return coefficient;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.coefficient] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param coefficient the coefficient to set
	 */
	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.noteObtension] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the noteObtension
	 */
	public Double getNoteObtension() {
		return noteObtension;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.noteObtension] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param noteObtension the noteObtension to set
	 */
	public void setNoteObtension(Double noteObtension) {
		this.noteObtension = noteObtension;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.noteDeBase] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the noteDeBase
	 */
	public Double getNoteDeBase() {
		return noteDeBase;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.noteDeBase] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param noteDeBase the noteDeBase to set
	 */
	public void setNoteDeBase(Double noteDeBase) {
		this.noteDeBase = noteDeBase;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.noteEliminatoire] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the noteEliminatoire
	 */
	public Double getNoteEliminatoire() {
		return noteEliminatoire;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.noteEliminatoire] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param noteEliminatoire the noteEliminatoire to set
	 */
	public void setNoteEliminatoire(Double noteEliminatoire) {
		this.noteEliminatoire = noteEliminatoire;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.optionnelle] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the optionnelle
	 */
	public Boolean getOptionnelle() {
		return optionnelle;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.optionnelle] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param optionnelle the optionnelle to set
	 */
	public void setOptionnelle(Boolean optionnelle) {
		this.optionnelle = optionnelle;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.aChoix] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @return the aChoix
	 */
	public Boolean getaChoix() {
		return aChoix;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.aChoix] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  16:02:55
	 * @param aChoix the aChoix to set
	 */
	public void setaChoix(Boolean aChoix) {
		this.aChoix = aChoix;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.idPeriode] Getter 
	 * @author BELDI Jamel on : 7 sept. 2014  17:29:49
	 * @return the idPeriode
	 */
	public Integer getIdPeriode() {
		return idPeriode;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.idPeriode] Setter 
	 * @author BELDI Jamel on : 7 sept. 2014  17:29:49
	 * @param idPeriode the idPeriode to set
	 */
	public void setIdPeriode(Integer idPeriode) {
		this.idPeriode = idPeriode;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.codePeriode] Getter 
	 * @author BELDI Jamel on : 7 sept. 2014  17:29:49
	 * @return the codePeriode
	 */
	public String getCodePeriode() {
		return codePeriode;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.codePeriode] Setter 
	 * @author BELDI Jamel on : 7 sept. 2014  17:29:49
	 * @param codePeriode the codePeriode to set
	 */
	public void setCodePeriode(String codePeriode) {
		this.codePeriode = codePeriode;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.libellePeriode] Getter 
	 * @author BELDI Jamel on : 7 sept. 2014  17:29:49
	 * @return the libellePeriode
	 */
	public String getLibellePeriode() {
		return libellePeriode;
	}




	/**
	 * [ParcoursIndividualiseMatiereDto.libellePeriode] Setter 
	 * @author BELDI Jamel on : 7 sept. 2014  17:29:49
	 * @param libellePeriode the libellePeriode to set
	 */
	public void setLibellePeriode(String libellePeriode) {
		this.libellePeriode = libellePeriode;
	}

	
	
	
}
