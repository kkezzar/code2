package dz.gov.mesrs.sii.fve.business.model.dto.examen;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 
 * @author BELDI Jamel on : 17 sept. 2014 16:17:13
 */

public class ExamenSessionDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 17 sept. 2014 16:17:58
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	// private RattachementMc rattachementMc;
	private Integer rattachementMcId;
	private Double rattachementMcCoefficient;
	private Double rattachementMcNoteObtension;
	private Double rattachementMcNoteDeBase;
	private Double rattachementMcNoteEliminatoire;
	private Integer ueId;
	private String ueCode;
	private String ueLibelleFr;

	private Integer mcId;
	private String mcCode;
	private String mcLibelleFr;
	private Integer mcCoefficient;
	private Integer mcCredit;

	private Long planningSessionId;
	private String planningSessionIntitule;
	private Integer ouvertureOffreFormationId;
	private double planningCoefficientNoteEliminatoire;

	private String numeroSession;
	private String typeSession;
	private Integer cycleId;
	private String cycleCode;
	private String cycleLibelleLongLt;
	private Integer niveauId;
	private String niveauCode;
	private String niveauLibelleLongLt;

	private Integer idPeriode;
	private String codePeriode;
	private String libellePeriode;

	private Integer anneeAcademiqueId;
	private String anneeAcademiqueCode;
	private Integer situationId;

	private Date dateExamen;
	private Date heureDebut;
	private Date heureFin;
	private Integer duree;
	private Boolean anonymat;
	private Date heureDebutReelle;
	private Date heureFinReelle;
	private String incident;
	private Integer nbCopieRemises;
	private String refTypeExamen;
	private String refTypeExamenLibelle;
	private Date dateCreation;
	private String refModeInscription;
	private Date dateExamenReelle;
	private Integer nbInscrits;
	private List<SalleExamenDto> salleExamenDtos;
	private List<InscriptionExamenDto> inscriptionExamenDtos;
	private List<ResponsableExamenDto> responsableExamenDtos;
	private List<ResponsableExamenDto> responsableExamenDtosGrouped;
	private Long modeleExamenId;
	private double moyenneSession;
	private String salleResponsableExamen;
	private SalleExamenDto salleExamenDto;
	private ResponsableExamenDto responsableExamenDto;

	public ExamenSessionDto() {

	}

	/**
	 * [ExamenSessionDto.ExamenSessionDto()] Constructor
	 * @author MAKERRI Sofiane  on : 8 févr. 2015  10:43:56
	 * @param examenSessionDto	
	 */
	public ExamenSessionDto(ExamenSessionDto examenSessionDto) {
		if (examenSessionDto != null) {
			this.mcLibelleFr = examenSessionDto.getMcLibelleFr();
			this.id = examenSessionDto.getId();
			this.salleExamenDtos = examenSessionDto.getSalleExamenDtos();
			this.responsableExamenDtos = examenSessionDto
					.getResponsableExamenDtos();
			this.dateExamen = examenSessionDto.getDateExamen();
			this.heureDebut = examenSessionDto.getHeureDebut();
			this.heureFin = examenSessionDto.getHeureFin();
		}

	}

	/**
	 * 
	 * [ExamenSessionDto.getId] Method
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:32:14
	 * @return
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * 
	 * [ExamenSessionDto.setId] Method
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:32:17
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [ExamenSessionDto.rattachementMcId] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:31:44
	 * @return the rattachementMcId
	 */
	public Integer getRattachementMcId() {
		return rattachementMcId;
	}

	/**
	 * [ExamenSessionDto.rattachementMcId] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:31:44
	 * @param rattachementMcId
	 *            the rattachementMcId to set
	 */
	public void setRattachementMcId(Integer rattachementMcId) {
		this.rattachementMcId = rattachementMcId;
	}

	/**
	 * [ExamenSessionDto.ueId] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:31:44
	 * @return the ueId
	 */
	public Integer getUeId() {
		return ueId;
	}

	/**
	 * [ExamenSessionDto.ueId] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:31:44
	 * @param ueId
	 *            the ueId to set
	 */
	public void setUeId(Integer ueId) {
		this.ueId = ueId;
	}

	/**
	 * [ExamenSessionDto.ueCode] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:31:44
	 * @return the ueCode
	 */
	public String getUeCode() {
		return ueCode;
	}

	/**
	 * [ExamenSessionDto.ueCode] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:31:44
	 * @param ueCode
	 *            the ueCode to set
	 */
	public void setUeCode(String ueCode) {
		this.ueCode = ueCode;
	}

	/**
	 * [ExamenSessionDto.ueLibelleFr] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:31:44
	 * @return the ueLibelleFr
	 */
	public String getUeLibelleFr() {
		return ueLibelleFr;
	}

	/**
	 * [ExamenSessionDto.ueLibelleFr] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:31:44
	 * @param ueLibelleFr
	 *            the ueLibelleFr to set
	 */
	public void setUeLibelleFr(String ueLibelleFr) {
		this.ueLibelleFr = ueLibelleFr;
	}

	/**
	 * [ExamenSessionDto.mcId] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:31:44
	 * @return the mcId
	 */
	public Integer getMcId() {
		return mcId;
	}

	/**
	 * [ExamenSessionDto.mcId] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:31:44
	 * @param mcId
	 *            the mcId to set
	 */
	public void setMcId(Integer mcId) {
		this.mcId = mcId;
	}

	/**
	 * [ExamenSessionDto.mcCode] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:31:44
	 * @return the mcCode
	 */
	public String getMcCode() {
		return mcCode;
	}

	/**
	 * [ExamenSessionDto.mcCode] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:31:44
	 * @param mcCode
	 *            the mcCode to set
	 */
	public void setMcCode(String mcCode) {
		this.mcCode = mcCode;
	}

	/**
	 * [ExamenSessionDto.mcLibelleFr] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:31:44
	 * @return the mcLibelleFr
	 */
	public String getMcLibelleFr() {
		return mcLibelleFr;
	}

	/**
	 * [ExamenSessionDto.mcLibelleFr] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:31:44
	 * @param mcLibelleFr
	 *            the mcLibelleFr to set
	 */
	public void setMcLibelleFr(String mcLibelleFr) {
		this.mcLibelleFr = mcLibelleFr;
	}

	/**
	 * [ExamenSessionDto.planningSessionId] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:35:54
	 * @return the planningSessionId
	 */
	public Long getPlanningSessionId() {
		return planningSessionId;
	}

	/**
	 * [ExamenSessionDto.planningSessionId] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:35:54
	 * @param planningSessionId
	 *            the planningSessionId to set
	 */
	public void setPlanningSessionId(Long planningSessionId) {
		this.planningSessionId = planningSessionId;
	}

	/**
	 * [ExamenSessionDto.planningSessionIntitule] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:35:54
	 * @return the planningSessionIntitule
	 */
	public String getPlanningSessionIntitule() {
		return planningSessionIntitule;
	}

	/**
	 * [ExamenSessionDto.planningSessionIntitule] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:35:54
	 * @param planningSessionIntitule
	 *            the planningSessionIntitule to set
	 */
	public void setPlanningSessionIntitule(String planningSessionIntitule) {
		this.planningSessionIntitule = planningSessionIntitule;
	}

	/**
	 * [ExamenSessionDto.idPeriode] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:35:54
	 * @return the idPeriode
	 */
	public Integer getIdPeriode() {
		return idPeriode;
	}

	/**
	 * [ExamenSessionDto.idPeriode] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:35:54
	 * @param idPeriode
	 *            the idPeriode to set
	 */
	public void setIdPeriode(Integer idPeriode) {
		this.idPeriode = idPeriode;
	}

	/**
	 * [ExamenSessionDto.codePeriode] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:35:54
	 * @return the codePeriode
	 */
	public String getCodePeriode() {
		return codePeriode;
	}

	/**
	 * [ExamenSessionDto.codePeriode] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:35:54
	 * @param codePeriode
	 *            the codePeriode to set
	 */
	public void setCodePeriode(String codePeriode) {
		this.codePeriode = codePeriode;
	}

	/**
	 * [ExamenSessionDto.libellePeriode] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:35:54
	 * @return the libellePeriode
	 */
	public String getLibellePeriode() {
		return libellePeriode;
	}

	/**
	 * [ExamenSessionDto.libellePeriode] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:35:54
	 * @param libellePeriode
	 *            the libellePeriode to set
	 */
	public void setLibellePeriode(String libellePeriode) {
		this.libellePeriode = libellePeriode;
	}

	/**
	 * [ExamenSessionDto.anneeAcademiqueId] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:35:54
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [ExamenSessionDto.anneeAcademiqueId] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:35:54
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [ExamenSessionDto.anneeAcademiqueCode] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:35:54
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}

	/**
	 * [ExamenSessionDto.anneeAcademiqueCode] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 10:35:54
	 * @param anneeAcademiqueCode
	 *            the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	public Date getDateExamen() {
		return this.dateExamen;
	}

	public void setDateExamen(Date dateExamen) {
		this.dateExamen = dateExamen;
	}

	public Date getHeureDebut() {
		return this.heureDebut;
	}

	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Date getHeureFin() {
		return this.heureFin;
	}

	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

	public Integer getDuree() {
		return this.duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public Boolean getAnonymat() {
		return this.anonymat;
	}

	public void setAnonymat(Boolean anonymat) {
		this.anonymat = anonymat;
	}

	public Date getHeureDebutReelle() {
		return this.heureDebutReelle;
	}

	public void setHeureDebutReelle(Date heureDebutReelle) {
		this.heureDebutReelle = heureDebutReelle;
	}

	public Date getHeureFinReelle() {
		return this.heureFinReelle;
	}

	public void setHeureFinReelle(Date heureFinReelle) {
		this.heureFinReelle = heureFinReelle;
	}

	public String getIncident() {
		return this.incident;
	}

	public void setIncident(String incident) {
		this.incident = incident;
	}

	public Integer getNbCopieRemises() {
		return this.nbCopieRemises;
	}

	public void setNbCopieRemises(Integer nbCopieRemises) {
		this.nbCopieRemises = nbCopieRemises;
	}

	public String getRefTypeExamen() {
		return this.refTypeExamen;
	}

	public void setRefTypeExamen(String refTypeExamen) {
		this.refTypeExamen = refTypeExamen;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getRefModeInscription() {
		return this.refModeInscription;
	}

	public void setRefModeInscription(String refModeInscription) {
		this.refModeInscription = refModeInscription;
	}

	/**
	 * [ExamenSessionDto.inscriptionExamenDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 févr. 2015 08:14:16
	 * @return the inscriptionExamenDtos
	 */
	public List<InscriptionExamenDto> getInscriptionExamenDtos() {
		return inscriptionExamenDtos;
	}

	/**
	 * [ExamenSessionDto.inscriptionExamenDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 févr. 2015 08:14:16
	 * @param inscriptionExamenDtos
	 *            the inscriptionExamenDtos to set
	 */
	public void setInscriptionExamenDtos(
			List<InscriptionExamenDto> inscriptionExamenDtos) {
		this.inscriptionExamenDtos = inscriptionExamenDtos;
	}

	/**
	 * [ExamenSessionDto.refTypeExamenLibelle] Setter
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 18:16:04
	 * @param refTypeExamenLibelle
	 *            the refTypeExamenLibelle to set
	 */
	public void setRefTypeExamenLibelle(String refTypeExamenLibelle) {
		this.refTypeExamenLibelle = refTypeExamenLibelle;
	}

	/**
	 * [ExamenSessionDto.numeroSession] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 17:00:01
	 * @return the numeroSession
	 */
	public String getNumeroSession() {
		return numeroSession;
	}

	/**
	 * [ExamenSessionDto.numeroSession] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 17:00:01
	 * @param numeroSession
	 *            the numeroSession to set
	 */
	public void setNumeroSession(String numeroSession) {
		this.numeroSession = numeroSession;
	}

	/**
	 * [ExamenSessionDto.typeSession] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 17:00:01
	 * @return the typeSession
	 */
	public String getTypeSession() {
		return typeSession;
	}

	/**
	 * [ExamenSessionDto.typeSession] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 17:00:01
	 * @param typeSession
	 *            the typeSession to set
	 */
	public void setTypeSession(String typeSession) {
		this.typeSession = typeSession;
	}

	/**
	 * [ExamenSessionDto.mcCoefficient] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 17:26:22
	 * @return the mcCoefficient
	 */
	public Integer getMcCoefficient() {
		return mcCoefficient;
	}

	/**
	 * [ExamenSessionDto.mcCoefficient] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 17:26:22
	 * @param mcCoefficient
	 *            the mcCoefficient to set
	 */
	public void setMcCoefficient(Integer mcCoefficient) {
		this.mcCoefficient = mcCoefficient;
	}

	/**
	 * [ExamenSessionDto.mcCredit] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 17:26:22
	 * @return the mcCredit
	 */
	public Integer getMcCredit() {
		return mcCredit;
	}

	/**
	 * [ExamenSessionDto.mcCredit] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 17:26:22
	 * @param mcCredit
	 *            the mcCredit to set
	 */
	public void setMcCredit(Integer mcCredit) {
		this.mcCredit = mcCredit;
	}

	/**
	 * [ExamenSessionDto.rattachementMcCoefficient] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:53:03
	 * @return the rattachementMcCoefficient
	 */
	public Double getRattachementMcCoefficient() {
		return rattachementMcCoefficient;
	}

	/**
	 * [ExamenSessionDto.rattachementMcCoefficient] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:53:03
	 * @param rattachementMcCoefficient
	 *            the rattachementMcCoefficient to set
	 */
	public void setRattachementMcCoefficient(Double rattachementMcCoefficient) {
		this.rattachementMcCoefficient = rattachementMcCoefficient;
	}

	/**
	 * [ExamenSessionDto.rattachementMcNoteObtension] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:53:03
	 * @return the rattachementMcNoteObtension
	 */
	public Double getRattachementMcNoteObtension() {
		return rattachementMcNoteObtension;
	}

	/**
	 * [ExamenSessionDto.rattachementMcNoteObtension] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:53:03
	 * @param rattachementMcNoteObtension
	 *            the rattachementMcNoteObtension to set
	 */
	public void setRattachementMcNoteObtension(
			Double rattachementMcNoteObtension) {
		this.rattachementMcNoteObtension = rattachementMcNoteObtension;
	}

	/**
	 * [ExamenSessionDto.rattachementMcNoteDeBase] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:53:03
	 * @return the rattachementMcNoteDeBase
	 */
	public Double getRattachementMcNoteDeBase() {
		return rattachementMcNoteDeBase;
	}

	/**
	 * [ExamenSessionDto.rattachementMcNoteDeBase] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:53:03
	 * @param rattachementMcNoteDeBase
	 *            the rattachementMcNoteDeBase to set
	 */
	public void setRattachementMcNoteDeBase(Double rattachementMcNoteDeBase) {
		this.rattachementMcNoteDeBase = rattachementMcNoteDeBase;
	}

	/**
	 * [ExamenSessionDto.rattachementMcNoteEliminatoire] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:53:03
	 * @return the rattachementMcNoteEliminatoire
	 */
	public Double getRattachementMcNoteEliminatoire() {
		return rattachementMcNoteEliminatoire;
	}

	/**
	 * [ExamenSessionDto.rattachementMcNoteEliminatoire] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:53:03
	 * @param rattachementMcNoteEliminatoire
	 *            the rattachementMcNoteEliminatoire to set
	 */
	public void setRattachementMcNoteEliminatoire(
			Double rattachementMcNoteEliminatoire) {
		this.rattachementMcNoteEliminatoire = rattachementMcNoteEliminatoire;
	}

	/**
	 * [ExamenSessionDto.cycleId] Getter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 11:10:38
	 * @return the cycleId
	 */
	public Integer getCycleId() {
		return cycleId;
	}

	/**
	 * [ExamenSessionDto.cycleId] Setter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 11:10:38
	 * @param cycleId
	 *            the cycleId to set
	 */
	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	/**
	 * [ExamenSessionDto.cycleCode] Getter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 11:10:38
	 * @return the cycleCode
	 */
	public String getCycleCode() {
		return cycleCode;
	}

	/**
	 * [ExamenSessionDto.cycleCode] Setter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 11:10:38
	 * @param cycleCode
	 *            the cycleCode to set
	 */
	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}

	/**
	 * [ExamenSessionDto.cycleLibelleLongLt] Getter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 11:10:38
	 * @return the cycleLibelleLongLt
	 */
	public String getCycleLibelleLongLt() {
		return cycleLibelleLongLt;
	}

	/**
	 * [ExamenSessionDto.cycleLibelleLongLt] Setter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 11:10:38
	 * @param cycleLibelleLongLt
	 *            the cycleLibelleLongLt to set
	 */
	public void setCycleLibelleLongLt(String cycleLibelleLongLt) {
		this.cycleLibelleLongLt = cycleLibelleLongLt;
	}

	/**
	 * [ExamenSessionDto.niveauId] Getter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 11:10:38
	 * @return the niveauId
	 */
	public Integer getNiveauId() {
		return niveauId;
	}

	/**
	 * [ExamenSessionDto.niveauId] Setter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 11:10:38
	 * @param niveauId
	 *            the niveauId to set
	 */
	public void setNiveauId(Integer niveauId) {
		this.niveauId = niveauId;
	}

	/**
	 * [ExamenSessionDto.niveauCode] Getter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 11:10:38
	 * @return the niveauCode
	 */
	public String getNiveauCode() {
		return niveauCode;
	}

	/**
	 * [ExamenSessionDto.niveauCode] Setter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 11:10:38
	 * @param niveauCode
	 *            the niveauCode to set
	 */
	public void setNiveauCode(String niveauCode) {
		this.niveauCode = niveauCode;
	}

	/**
	 * [ExamenSessionDto.niveauLibelleLongLt] Getter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 11:10:38
	 * @return the niveauLibelleLongLt
	 */
	public String getNiveauLibelleLongLt() {
		return niveauLibelleLongLt;
	}

	/**
	 * [ExamenSessionDto.niveauLibelleLongLt] Setter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 11:10:38
	 * @param niveauLibelleLongLt
	 *            the niveauLibelleLongLt to set
	 */
	public void setNiveauLibelleLongLt(String niveauLibelleLongLt) {
		this.niveauLibelleLongLt = niveauLibelleLongLt;
	}

	/**
	 * [ExamenSessionDto.dateExamenReelle] Getter
	 * 
	 * @author BELDI Jamel on : 30 sept. 2014 16:51:34
	 * @return the dateExamenReelle
	 */
	public Date getDateExamenReelle() {
		return dateExamenReelle;
	}

	/**
	 * [ExamenSessionDto.dateExamenReelle] Setter
	 * 
	 * @author BELDI Jamel on : 30 sept. 2014 16:51:34
	 * @param dateExamenReelle
	 *            the dateExamenReelle to set
	 */
	public void setDateExamenReelle(Date dateExamenReelle) {
		this.dateExamenReelle = dateExamenReelle;
	}

	/**
	 * [ExamenSessionDto.refTypeExamenLibelle] Getter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 16:40:46
	 * @return the refTypeExamenLibelle
	 */
	public String getRefTypeExamenLibelle() {
		return refTypeExamenLibelle;
	}

	/**
	 * [ExamenSessionDto.ouvertureOffreFormationId] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 12:18:17
	 * @return the ouvertureOffreFormationId
	 */
	public Integer getOuvertureOffreFormationId() {
		return ouvertureOffreFormationId;
	}

	/**
	 * [ExamenSessionDto.ouvertureOffreFormationId] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 12:18:17
	 * @param ouvertureOffreFormationId
	 *            the ouvertureOffreFormationId to set
	 */
	public void setOuvertureOffreFormationId(Integer ouvertureOffreFormationId) {
		this.ouvertureOffreFormationId = ouvertureOffreFormationId;
	}

	/**
	 * [ExamenSessionDto.situationId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 09:53:31
	 * @return the situationId
	 */
	public Integer getSituationId() {
		return situationId;
	}

	/**
	 * [ExamenSessionDto.situationId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 09:53:31
	 * @param situationId
	 *            the situationId to set
	 */
	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}

	/**
	 * [ExamenSessionDto.nbInscrits] Getter
	 * 
	 * @author BELDI Jamel on : 29 oct. 2014 18:56:56
	 * @return the nbInscrits
	 */
	public Integer getNbInscrits() {
		return nbInscrits;
	}

	/**
	 * [ExamenSessionDto.nbInscrits] Setter
	 * 
	 * @author BELDI Jamel on : 29 oct. 2014 18:56:56
	 * @param nbInscrits
	 *            the nbInscrits to set
	 */
	public void setNbInscrits(Integer nbInscrits) {
		this.nbInscrits = nbInscrits;
	}

	/**
	 * [ExamenSessionDto.salleExamenDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 09:39:30
	 * @return the salleExamenDtos
	 */
	public List<SalleExamenDto> getSalleExamenDtos() {
		return salleExamenDtos;
	}

	/**
	 * [ExamenSessionDto.salleExamenDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 09:39:30
	 * @param salleExamenDtos
	 *            the salleExamenDtos to set
	 */
	public void setSalleExamenDtos(List<SalleExamenDto> salleExamenDtos) {
		this.salleExamenDtos = salleExamenDtos;
	}

	/**
	 * [ExamenSessionDto.modeleExamenId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 11:54:33
	 * @return the modeleExamenId
	 */
	public Long getModeleExamenId() {
		return modeleExamenId;
	}

	/**
	 * [ExamenSessionDto.modeleExamenId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 11:54:33
	 * @param modeleExamenId
	 *            the modeleExamenId to set
	 */
	public void setModeleExamenId(Long modeleExamenId) {
		this.modeleExamenId = modeleExamenId;
	}

	/**
	 * [ExamenSessionDto.moyenneSession] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 08:26:58
	 * @return the moyenneSession
	 */
	public double getMoyenneSession() {
		return moyenneSession;
	}

	/**
	 * [ExamenSessionDto.moyenneSession] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 08:26:58
	 * @param moyenneSession
	 *            the moyenneSession to set
	 */
	public void setMoyenneSession(double moyenneSession) {
		this.moyenneSession = moyenneSession;
	}

	/**
	 * [ExamenSessionDto.planningCoefficientNoteEliminatoire] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 10:47:15
	 * @return the planningCoefficientNoteEliminatoire
	 */
	public double getPlanningCoefficientNoteEliminatoire() {
		return planningCoefficientNoteEliminatoire;
	}

	/**
	 * [ExamenSessionDto.planningCoefficientNoteEliminatoire] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 10:47:15
	 * @param planningCoefficientNoteEliminatoire
	 *            the planningCoefficientNoteEliminatoire to set
	 */
	public void setPlanningCoefficientNoteEliminatoire(
			double planningCoefficientNoteEliminatoire) {
		this.planningCoefficientNoteEliminatoire = planningCoefficientNoteEliminatoire;
	}

	/**
	 * [ExamenSessionDto.salleResponsableExamen] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 févr. 2015 08:18:17
	 * @return the salleResponsableExamen
	 */
	public String getSalleResponsableExamen() {
		if (salleExamenDtos != null) {
			salleResponsableExamen = "";
			// salleResponsableExamen += salleExamenDtos.toString();
			for (SalleExamenDto _salle : salleExamenDtos) {

				salleResponsableExamen = StringUtils
						.collectionToDelimitedString(salleExamenDtos, ",");
				List<ResponsableExamenDto> _responsables = _salle
						.getResponsables();
				if (_responsables != null) {
					String responsableString = StringUtils
							.collectionToDelimitedString(_responsables, "-");
					salleResponsableExamen += "(" + responsableString + ")";
				}
			}
		}
		return salleResponsableExamen;
	}

	/**
	 * [ExamenSessionDto.salleResponsableExamen] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 févr. 2015 08:18:17
	 * @param salleResponsableExamen
	 *            the salleResponsableExamen to set
	 */
	public void setSalleResponsableExamen(String salleResponsableExamen) {
		this.salleResponsableExamen = salleResponsableExamen;
	}

	/**
	 * [ExamenSessionDto.responsableExamenDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 févr. 2015 10:45:11
	 * @return the responsableExamenDtos
	 */
	public List<ResponsableExamenDto> getResponsableExamenDtos() {
		return responsableExamenDtos;
	}

	/**
	 * [ExamenSessionDto.responsableExamenDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 févr. 2015 10:45:11
	 * @param responsableExamenDtos
	 *            the responsableExamenDtos to set
	 */
	public void setResponsableExamenDtos(
			List<ResponsableExamenDto> responsableExamenDtos) {
		this.responsableExamenDtos = responsableExamenDtos;
	}

	/**
	 * [ExamenSessionDto.salleExamenDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 févr. 2015 10:08:15
	 * @return the salleExamenDto
	 */
	public SalleExamenDto getSalleExamenDto() {
		return salleExamenDto;
	}

	/**
	 * [ExamenSessionDto.salleExamenDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 févr. 2015 10:08:15
	 * @param salleExamenDto
	 *            the salleExamenDto to set
	 */
	public void setSalleExamenDto(SalleExamenDto salleExamenDto) {
		this.salleExamenDto = salleExamenDto;
	}

	/**
	 * [ExamenSessionDto.responsableExamenDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 févr. 2015 10:08:15
	 * @return the responsableExamenDto
	 */
	public ResponsableExamenDto getResponsableExamenDto() {
		return responsableExamenDto;
	}

	/**
	 * [ExamenSessionDto.responsableExamenDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 févr. 2015 10:08:15
	 * @param responsableExamenDto
	 *            the responsableExamenDto to set
	 */
	public void setResponsableExamenDto(
			ResponsableExamenDto responsableExamenDto) {
		this.responsableExamenDto = responsableExamenDto;
	}

	/**
	 * [ExamenSessionDto.responsableExamenDtosGrouped] Getter 
	 * @author MAKERRI Sofiane on : 8 févr. 2015  15:02:36
	 * @return the responsableExamenDtosGouped
	 */
	public List<ResponsableExamenDto> getResponsableExamenDtosGrouped() {
		return responsableExamenDtosGrouped;
	}

	/**
	 * [ExamenSessionDto.responsableExamenDtosGrouped] Setter 
	 * @author MAKERRI Sofiane on : 8 févr. 2015  15:02:36
	 * @param responsableExamenDtosGouped the responsableExamenDtosGouped to set
	 */
	public void setResponsableExamenDtosGrouped(
			List<ResponsableExamenDto> responsableExamenDtosGrouped) {
		this.responsableExamenDtosGrouped = responsableExamenDtosGrouped;
	}
	
	

}
