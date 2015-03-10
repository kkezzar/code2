package dz.gov.mesrs.sii.fve.business.model.dto.examen;

import java.util.Date;

/**
 * 
 * @author BELDI Jamel on : 17 sept. 2014 16:17:29
 */

public class InscriptionExamenDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 17 sept. 2014 16:18:05
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	// private SalleExamen salleExamen;

	private Integer salleExamenId;
	private Integer refLieuId;
	private String refLieuLibelle;
	private Integer refLieuTypeId;
	private String refLieuTypeLibelle;
	private Short refLieuCapacite;
	private Integer examenSessionId;
	private Integer rattachementMcId;
	private Integer ueId;
	private String ueCode;
	private String ueLibelleFr;
	private String ueNatureLlFr;

	private Integer mcId;
	private String mcCode;
	private String mcLibelleFr;
	private Double rattachementMcCoefficient;
	private Double rattachementMcCredit;
	private Double mcCreditObtenu;
	private Double rattachementMcNoteObtension;
	private Double rattachementMcNoteDeBase;
	private Double rattachementMcNoteEliminatoire;
	private double noteEliminatoire;
	
	private Double coefficientExamen;
	private Double coefficientControleContinu;
	private Double coefficientControleIntermediaire;
	private double totalCoefficient;
	// private PlanningSessionDto planningSession;
	private Long planningSessionId;
	private String planningSessionIntitule;
	private boolean avecControleContinu;
	// private Periode periode;
	private Integer idPeriode;
	private String codePeriode;
	private String libellePeriode;
	// private AnneeAcademique anneeAcademique;
	private Integer anneeAcademiqueId;
	private String anneeAcademiqueCode;

	// private DossierInscriptionAdministrative

	private Integer dossierInscriptionAdministrativeId;
	private String numeroInscription;
	private Integer dossierEtudiantId;
	private String numeroMatricule;
	// Etudiant Individu
	private String nin;
	private Integer individuId;
	private String individuNomArabe;
	private String individuNomLatin;
	private String individuPrenomArabe;
	private String individuPrenomLatin;
	private Date individuDateNaissance;

	private boolean copieNonRemise;
	private boolean absent;
	private String motifAbsence;
	private boolean absenceJustifie;
	private Double noteExamen;
	private Double noteJury;
	private Double noteJuryToDisplay;
	private boolean modifiableByJury;
	private boolean noteObtenu;
	private boolean readerByJury;
	private boolean etudiantRachete;
	private String urlPhoto;
	private Double noteMax;
	private Double noteControleContinu;
	private Double noteControleIntermediaire;
	//private double noteExamen;
	private Date dateExamen;
	private Date heureDebut;
	private Date heureFin;
	private String refTypeExamen;
	private String refTypeExamenLibelle;
	private boolean subscribed;
	private Double moyenneControleContinu;
	private double moyenneGenerale;

	public InscriptionExamenDto(InscriptionExamenDto inscriptionExamenDto) {
		if (inscriptionExamenDto != null) {
			this.numeroMatricule = inscriptionExamenDto.getNumeroMatricule();
			this.individuNomLatin = inscriptionExamenDto.getIndividuNomLatin();
			this.individuPrenomLatin = inscriptionExamenDto
					.getIndividuPrenomLatin();
			this.individuDateNaissance = inscriptionExamenDto
					.getIndividuDateNaissance();
			this.noteExamen = inscriptionExamenDto.getNoteExamen();
			this.absent = inscriptionExamenDto.getAbsent();
			this.copieNonRemise = inscriptionExamenDto.getCopieNonRemise();
		}
	}

	public InscriptionExamenDto() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [InscriptionExamenDto.salleExamenId] Getter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:05:51
	 * @return the salleExamenId
	 */
	public Integer getSalleExamenId() {
		return salleExamenId;
	}

	/**
	 * [InscriptionExamenDto.salleExamenId] Setter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:05:51
	 * @param salleExamenId
	 *            the salleExamenId to set
	 */
	public void setSalleExamenId(Integer salleExamenId) {
		this.salleExamenId = salleExamenId;
	}

	/**
	 * [InscriptionExamenDto.refLieuId] Getter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:05:51
	 * @return the refLieuId
	 */
	public Integer getRefLieuId() {
		return refLieuId;
	}

	/**
	 * [InscriptionExamenDto.refLieuId] Setter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:05:51
	 * @param refLieuId
	 *            the refLieuId to set
	 */
	public void setRefLieuId(Integer refLieuId) {
		this.refLieuId = refLieuId;
	}

	/**
	 * [InscriptionExamenDto.refLieuLibelle] Getter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:05:51
	 * @return the refLieuLibelle
	 */
	public String getRefLieuLibelle() {
		return refLieuLibelle;
	}

	/**
	 * [InscriptionExamenDto.refLieuLibelle] Setter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:05:51
	 * @param refLieuLibelle
	 *            the refLieuLibelle to set
	 */
	public void setRefLieuLibelle(String refLieuLibelle) {
		this.refLieuLibelle = refLieuLibelle;
	}

	/**
	 * [InscriptionExamenDto.refLieuTypeId] Getter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:05:51
	 * @return the refLieuTypeId
	 */
	public Integer getRefLieuTypeId() {
		return refLieuTypeId;
	}

	/**
	 * [InscriptionExamenDto.refLieuTypeId] Setter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:05:51
	 * @param refLieuTypeId
	 *            the refLieuTypeId to set
	 */
	public void setRefLieuTypeId(Integer refLieuTypeId) {
		this.refLieuTypeId = refLieuTypeId;
	}

	/**
	 * [InscriptionExamenDto.refLieuTypeLibelle] Getter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:05:51
	 * @return the refLieuTypeLibelle
	 */
	public String getRefLieuTypeLibelle() {
		return refLieuTypeLibelle;
	}

	/**
	 * [InscriptionExamenDto.refLieuTypeLibelle] Setter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:05:51
	 * @param refLieuTypeLibelle
	 *            the refLieuTypeLibelle to set
	 */
	public void setRefLieuTypeLibelle(String refLieuTypeLibelle) {
		this.refLieuTypeLibelle = refLieuTypeLibelle;
	}

	/**
	 * [InscriptionExamenDto.examenSessionId] Getter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:05:51
	 * @return the examenSessionId
	 */
	public Integer getExamenSessionId() {
		return examenSessionId;
	}

	/**
	 * [InscriptionExamenDto.examenSessionId] Setter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:05:51
	 * @param examenSessionId
	 *            the examenSessionId to set
	 */
	public void setExamenSessionId(Integer examenSessionId) {
		this.examenSessionId = examenSessionId;
	}

	/**
	 * [InscriptionExamenDto.rattachementMcId] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the rattachementMcId
	 */
	public Integer getRattachementMcId() {
		return rattachementMcId;
	}

	/**
	 * [InscriptionExamenDto.rattachementMcId] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param rattachementMcId
	 *            the rattachementMcId to set
	 */
	public void setRattachementMcId(Integer rattachementMcId) {
		this.rattachementMcId = rattachementMcId;
	}

	/**
	 * [InscriptionExamenDto.ueId] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the ueId
	 */
	public Integer getUeId() {
		return ueId;
	}

	/**
	 * [InscriptionExamenDto.ueId] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param ueId
	 *            the ueId to set
	 */
	public void setUeId(Integer ueId) {
		this.ueId = ueId;
	}

	/**
	 * [InscriptionExamenDto.ueCode] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the ueCode
	 */
	public String getUeCode() {
		return ueCode;
	}

	/**
	 * [InscriptionExamenDto.ueCode] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param ueCode
	 *            the ueCode to set
	 */
	public void setUeCode(String ueCode) {
		this.ueCode = ueCode;
	}

	/**
	 * [InscriptionExamenDto.ueLibelleFr] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the ueLibelleFr
	 */
	public String getUeLibelleFr() {
		return ueLibelleFr;
	}

	/**
	 * [InscriptionExamenDto.ueLibelleFr] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param ueLibelleFr
	 *            the ueLibelleFr to set
	 */
	public void setUeLibelleFr(String ueLibelleFr) {
		this.ueLibelleFr = ueLibelleFr;
	}

	/**
	 * [InscriptionExamenDto.mcId] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the mcId
	 */
	public Integer getMcId() {
		return mcId;
	}

	/**
	 * [InscriptionExamenDto.mcId] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param mcId
	 *            the mcId to set
	 */
	public void setMcId(Integer mcId) {
		this.mcId = mcId;
	}

	/**
	 * [InscriptionExamenDto.mcCode] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the mcCode
	 */
	public String getMcCode() {
		return mcCode;
	}

	/**
	 * [InscriptionExamenDto.mcCode] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param mcCode
	 *            the mcCode to set
	 */
	public void setMcCode(String mcCode) {
		this.mcCode = mcCode;
	}

	/**
	 * [InscriptionExamenDto.mcLibelleFr] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the mcLibelleFr
	 */
	public String getMcLibelleFr() {
		return mcLibelleFr;
	}

	/**
	 * [InscriptionExamenDto.mcLibelleFr] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param mcLibelleFr
	 *            the mcLibelleFr to set
	 */
	public void setMcLibelleFr(String mcLibelleFr) {
		this.mcLibelleFr = mcLibelleFr;
	}

	/**
	 * [InscriptionExamenDto.planningSessionId] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the planningSessionId
	 */
	public Long getPlanningSessionId() {
		return planningSessionId;
	}

	/**
	 * [InscriptionExamenDto.planningSessionId] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param planningSessionId
	 *            the planningSessionId to set
	 */
	public void setPlanningSessionId(Long planningSessionId) {
		this.planningSessionId = planningSessionId;
	}

	/**
	 * [InscriptionExamenDto.planningSessionIntitule] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the planningSessionIntitule
	 */
	public String getPlanningSessionIntitule() {
		return planningSessionIntitule;
	}

	/**
	 * [InscriptionExamenDto.planningSessionIntitule] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param planningSessionIntitule
	 *            the planningSessionIntitule to set
	 */
	public void setPlanningSessionIntitule(String planningSessionIntitule) {
		this.planningSessionIntitule = planningSessionIntitule;
	}

	/**
	 * [InscriptionExamenDto.idPeriode] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the idPeriode
	 */
	public Integer getIdPeriode() {
		return idPeriode;
	}

	/**
	 * [InscriptionExamenDto.idPeriode] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param idPeriode
	 *            the idPeriode to set
	 */
	public void setIdPeriode(Integer idPeriode) {
		this.idPeriode = idPeriode;
	}

	/**
	 * [InscriptionExamenDto.codePeriode] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the codePeriode
	 */
	public String getCodePeriode() {
		return codePeriode;
	}

	/**
	 * [InscriptionExamenDto.codePeriode] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param codePeriode
	 *            the codePeriode to set
	 */
	public void setCodePeriode(String codePeriode) {
		this.codePeriode = codePeriode;
	}

	/**
	 * [InscriptionExamenDto.libellePeriode] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the libellePeriode
	 */
	public String getLibellePeriode() {
		return libellePeriode;
	}

	/**
	 * [InscriptionExamenDto.libellePeriode] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param libellePeriode
	 *            the libellePeriode to set
	 */
	public void setLibellePeriode(String libellePeriode) {
		this.libellePeriode = libellePeriode;
	}

	/**
	 * [InscriptionExamenDto.anneeAcademiqueId] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [InscriptionExamenDto.anneeAcademiqueId] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [InscriptionExamenDto.anneeAcademiqueCode] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}

	/**
	 * [InscriptionExamenDto.anneeAcademiqueCode] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param anneeAcademiqueCode
	 *            the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	/**
	 * [InscriptionExamenDto.dossierInscriptionAdministrativeId] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the dossierInscriptionAdministrativeId
	 */
	public Integer getDossierInscriptionAdministrativeId() {
		return dossierInscriptionAdministrativeId;
	}

	/**
	 * [InscriptionExamenDto.dossierInscriptionAdministrativeId] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param dossierInscriptionAdministrativeId
	 *            the dossierInscriptionAdministrativeId to set
	 */
	public void setDossierInscriptionAdministrativeId(
			Integer dossierInscriptionAdministrativeId) {
		this.dossierInscriptionAdministrativeId = dossierInscriptionAdministrativeId;
	}

	/**
	 * [InscriptionExamenDto.numeroInscription] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the numeroInscription
	 */
	public String getNumeroInscription() {
		return numeroInscription;
	}

	/**
	 * [InscriptionExamenDto.numeroInscription] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param numeroInscription
	 *            the numeroInscription to set
	 */
	public void setNumeroInscription(String numeroInscription) {
		this.numeroInscription = numeroInscription;
	}

	/**
	 * [InscriptionExamenDto.dossierEtudiantId] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the dossierEtudiantId
	 */
	public Integer getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [InscriptionExamenDto.dossierEtudiantId] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param dossierEtudiantId
	 *            the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(Integer dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	/**
	 * [InscriptionExamenDto.numeroMatricule] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the numeroMatricule
	 */
	public String getNumeroMatricule() {
		return numeroMatricule;
	}

	/**
	 * [InscriptionExamenDto.numeroMatricule] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param numeroMatricule
	 *            the numeroMatricule to set
	 */
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}

	/**
	 * [InscriptionExamenDto.nin] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the nin
	 */
	public String getNin() {
		return nin;
	}

	/**
	 * [InscriptionExamenDto.nin] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param nin
	 *            the nin to set
	 */
	public void setNin(String nin) {
		this.nin = nin;
	}

	/**
	 * [InscriptionExamenDto.individuNomArabe] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the individuNomArabe
	 */
	public String getIndividuNomArabe() {
		return individuNomArabe;
	}

	/**
	 * [InscriptionExamenDto.individuNomArabe] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param individuNomArabe
	 *            the individuNomArabe to set
	 */
	public void setIndividuNomArabe(String individuNomArabe) {
		this.individuNomArabe = individuNomArabe;
	}

	/**
	 * [InscriptionExamenDto.individuNomLatin] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the individuNomLatin
	 */
	public String getIndividuNomLatin() {
		return individuNomLatin;
	}

	/**
	 * [InscriptionExamenDto.individuNomLatin] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param individuNomLatin
	 *            the individuNomLatin to set
	 */
	public void setIndividuNomLatin(String individuNomLatin) {
		this.individuNomLatin = individuNomLatin;
	}

	/**
	 * [InscriptionExamenDto.individuPrenomArabe] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the individuPrenomArabe
	 */
	public String getIndividuPrenomArabe() {
		return individuPrenomArabe;
	}

	/**
	 * [InscriptionExamenDto.individuPrenomArabe] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param individuPrenomArabe
	 *            the individuPrenomArabe to set
	 */
	public void setIndividuPrenomArabe(String individuPrenomArabe) {
		this.individuPrenomArabe = individuPrenomArabe;
	}

	/**
	 * [InscriptionExamenDto.individuPrenomLatin] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @return the individuPrenomLatin
	 */
	public String getIndividuPrenomLatin() {
		return individuPrenomLatin;
	}

	/**
	 * [InscriptionExamenDto.individuPrenomLatin] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:21:04
	 * @param individuPrenomLatin
	 *            the individuPrenomLatin to set
	 */
	public void setIndividuPrenomLatin(String individuPrenomLatin) {
		this.individuPrenomLatin = individuPrenomLatin;
	}

	/**
	 * [InscriptionExamenDto.individuDateNaissance] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 15:45:12
	 * @return the individuDateNaissance
	 */
	public Date getIndividuDateNaissance() {
		return individuDateNaissance;
	}

	/**
	 * [InscriptionExamenDto.individuDateNaissance] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 15:45:12
	 * @param individuDateNaissance
	 *            the individuDateNaissance to set
	 */
	public void setIndividuDateNaissance(Date individuDateNaissance) {
		this.individuDateNaissance = individuDateNaissance;
	}

	public boolean getCopieNonRemise() {
		return this.copieNonRemise;
	}

	public void setCopieNonRemise(boolean copieNonRemise) {
		this.copieNonRemise = copieNonRemise;
	}

	public boolean getAbsent() {
		return this.absent;
	}

	public void setAbsent(boolean absent) {
		this.absent = absent;
	}

	public String getMotifAbsence() {
		return this.motifAbsence;
	}

	public void setMotifAbsence(String motifAbsence) {
		this.motifAbsence = motifAbsence;
	}

	public boolean getAbsenceJustifie() {
		return this.absenceJustifie;
	}

	public void setAbsenceJustifie(boolean absenceJustifie) {
		this.absenceJustifie = absenceJustifie;
	}

	/**
	 * [InscriptionExamenDto.noteExamen] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 15:39:18
	 * @return the noteExamen
	 */
	public Double getNoteExamen() {
		return noteExamen;
	}

	/**
	 * [InscriptionExamenDto.noteSession] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 15:39:18
	 * @param noteExamen
	 *            the noteExamen to set
	 */
	public void setNoteExamen(Double noteExamen) {
		this.noteExamen = noteExamen;
	}

	/**
	 * [InscriptionExamenDto.noteJury] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 15:39:18
	 * @return the noteJury
	 */
	public Double getNoteJury() {
		return noteJury;
	}

	/**
	 * [InscriptionExamenDto.noteJury] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 15:39:18
	 * @param noteJury
	 *            the noteJury to set
	 */
	public void setNoteJury(Double noteJury) {
		this.noteJury = noteJury;
	}

	/**
	 * [InscriptionExamenDto.rattachementMcCoefficient] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:46:51
	 * @return the rattachementMcCoefficient
	 */
	public Double getRattachementMcCoefficient() {
		return rattachementMcCoefficient;
	}

	/**
	 * [InscriptionExamenDto.rattachementMcCoefficient] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:46:51
	 * @param rattachementMcCoefficient
	 *            the rattachementMcCoefficient to set
	 */
	public void setRattachementMcCoefficient(Double rattachementMcCoefficient) {
		this.rattachementMcCoefficient = rattachementMcCoefficient;
	}

	/**
	 * [InscriptionExamenDto.rattachementMcNoteObtension] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:46:51
	 * @return the rattachementMcNoteObtension
	 */
	public Double getRattachementMcNoteObtension() {
		return rattachementMcNoteObtension;
	}

	/**
	 * [InscriptionExamenDto.rattachementMcNoteObtension] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:46:51
	 * @param rattachementMcNoteObtension
	 *            the rattachementMcNoteObtension to set
	 */
	public void setRattachementMcNoteObtension(
			Double rattachementMcNoteObtension) {
		this.rattachementMcNoteObtension = rattachementMcNoteObtension;
	}

	/**
	 * [InscriptionExamenDto.rattachementMcNoteDeBase] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:46:51
	 * @return the rattachementMcNoteDeBase
	 */
	public Double getRattachementMcNoteDeBase() {
		return rattachementMcNoteDeBase;
	}

	/**
	 * [InscriptionExamenDto.rattachementMcNoteDeBase] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:46:51
	 * @param rattachementMcNoteDeBase
	 *            the rattachementMcNoteDeBase to set
	 */
	public void setRattachementMcNoteDeBase(Double rattachementMcNoteDeBase) {
		this.rattachementMcNoteDeBase = rattachementMcNoteDeBase;
	}

	/**
	 * [InscriptionExamenDto.rattachementMcNoteEliminatoire] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:46:51
	 * @return the rattachementMcNoteEliminatoire
	 */
	public Double getRattachementMcNoteEliminatoire() {
		return rattachementMcNoteEliminatoire;
	}

	/**
	 * [InscriptionExamenDto.rattachementMcNoteEliminatoire] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 09:46:51
	 * @param rattachementMcNoteEliminatoire
	 *            the rattachementMcNoteEliminatoire to set
	 */
	public void setRattachementMcNoteEliminatoire(
			Double rattachementMcNoteEliminatoire) {
		this.rattachementMcNoteEliminatoire = rattachementMcNoteEliminatoire;
	}

	/**
	 * [InscriptionExamenDto.modifiableByJury] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 14:27:49
	 * @return the modifiableByJury
	 */
	public boolean getModifiableByJury() {
		modifiableByJury = false;
		if (noteExamen != null && rattachementMcNoteObtension != null
				&& noteExamen < rattachementMcNoteObtension && !absent
				&& !copieNonRemise) {
			modifiableByJury = true;
		}
		return modifiableByJury;
		// modifiableByJury = false;
		// if (!absent
		// && !copieNonRemise) {
		// modifiableByJury = true;
		// }
		// return modifiableByJury;
	}

	/**
	 * [InscriptionExamenDto.modifiableByJury] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 14:27:49
	 * @param modifiableByJury
	 *            the modifiableByJury to set
	 */
	public void setModifiableByJury(boolean modifiableByJury) {
		this.modifiableByJury = modifiableByJury;
	}

	/**
	 * [InscriptionExamenDto.readerByJury] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 15:03:06
	 * @return the readerByJury
	 */
	public boolean getReaderByJury() {
		readerByJury = true;
		if (noteExamen != null && rattachementMcNoteObtension != null
				&& noteExamen < rattachementMcNoteObtension && !absent
				&& !copieNonRemise) {
			readerByJury = false;
		}
		return readerByJury;
	}

	/**
	 * [InscriptionExamenDto.readerByJury] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 15:03:06
	 * @param readerByJury
	 *            the readerByJury to set
	 */
	public void setReaderByJury(boolean readerByJury) {
		this.readerByJury = readerByJury;
	}

	/**
	 * [InscriptionExamenDto.etudiantRachete] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 16:32:49
	 * @return the etudiantRachete
	 */
	public boolean getEtudiantRachete() {
		return etudiantRachete;
	}

	/**
	 * [InscriptionExamenDto.etudiantRachete] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 16:32:49
	 * @param etudiantRachete
	 *            the etudiantRachete to set
	 */
	public void setEtudiantRachete(boolean etudiantRachete) {
		this.etudiantRachete = etudiantRachete;
	}

	/**
	 * [InscriptionExamenDto.noteMax] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 12:06:40
	 * @return the noteMax
	 */
	public Double getNoteMax() {
		return noteMax;
	}

	/**
	 * [InscriptionExamenDto.noteMax] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 12:06:40
	 * @param noteMax
	 *            the noteMax to set
	 */
	public void setNoteMax(Double noteMax) {
		this.noteMax = noteMax;
	}

	/**
	 * [InscriptionExamenDto.individuId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 14:31:11
	 * @return the individuId
	 */
	public Integer getIndividuId() {
		return individuId;
	}

	/**
	 * [InscriptionExamenDto.individuId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 14:31:11
	 * @param individuId
	 *            the individuId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}

	/**
	 * [InscriptionExamenDto.noteControleContinu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 15:39:15
	 * @return the noteControleContinu
	 */
	public Double getNoteControleContinu() {
		return noteControleContinu;
	}

	/**
	 * [InscriptionExamenDto.noteControleContinu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 15:39:15
	 * @param noteControleContinu
	 *            the noteControleContinu to set
	 */
	public void setNoteControleContinu(Double noteControleContinu) {
		this.noteControleContinu = noteControleContinu;
	}

	/**
	 * [InscriptionExamenDto.noteExamen] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 13:03:55
	 * @return the noteExamen
	 */
//	public double getNoteExamen() {
//		return noteExamen;
//	}

	/**
	 * [InscriptionExamenDto.noteExamen] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 13:03:55
	 * @param noteExamen
	 *            the noteExamen to set
	 */
//	public void setNoteExamen(double noteExamen) {
//		this.noteExamen = noteExamen;
//	}

	/**
	 * [InscriptionExamenDto.urlPhoto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 18:08:04
	 * @return the urlPhoto
	 */
	public String getUrlPhoto() {
		return urlPhoto;
	}

	/**
	 * [InscriptionExamenDto.urlPhoto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 18:08:04
	 * @param urlPhoto
	 *            the urlPhoto to set
	 */
	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	/**
	 * [InscriptionExamenDto.dateExamen] Getter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:44:57
	 * @return the dateExamen
	 */
	public Date getDateExamen() {
		return dateExamen;
	}

	/**
	 * [InscriptionExamenDto.dateExamen] Setter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:44:57
	 * @param dateExamen
	 *            the dateExamen to set
	 */
	public void setDateExamen(Date dateExamen) {
		this.dateExamen = dateExamen;
	}

	/**
	 * [InscriptionExamenDto.heureDebut] Getter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:44:57
	 * @return the heureDebut
	 */
	public Date getHeureDebut() {
		return heureDebut;
	}

	/**
	 * [InscriptionExamenDto.heureDebut] Setter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:44:58
	 * @param heureDebut
	 *            the heureDebut to set
	 */
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	/**
	 * [InscriptionExamenDto.heureFin] Getter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:44:58
	 * @return the heureFin
	 */
	public Date getHeureFin() {
		return heureFin;
	}

	/**
	 * [InscriptionExamenDto.heureFin] Setter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:44:58
	 * @param heureFin
	 *            the heureFin to set
	 */
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

	/**
	 * [InscriptionExamenDto.refTypeExamen] Getter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:44:58
	 * @return the refTypeExamen
	 */
	public String getRefTypeExamen() {
		return refTypeExamen;
	}

	/**
	 * [InscriptionExamenDto.refTypeExamen] Setter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:44:58
	 * @param refTypeExamen
	 *            the refTypeExamen to set
	 */
	public void setRefTypeExamen(String refTypeExamen) {
		this.refTypeExamen = refTypeExamen;
	}

	/**
	 * [InscriptionExamenDto.refTypeExamenLibelle] Getter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:44:58
	 * @return the refTypeExamenLibelle
	 */
	public String getRefTypeExamenLibelle() {
		return refTypeExamenLibelle;
	}

	/**
	 * [InscriptionExamenDto.refTypeExamenLibelle] Setter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:44:58
	 * @param refTypeExamenLibelle
	 *            the refTypeExamenLibelle to set
	 */
	public void setRefTypeExamenLibelle(String refTypeExamenLibelle) {
		this.refTypeExamenLibelle = refTypeExamenLibelle;
	}

	/**
	 * [InscriptionExamenDto.avecControleContinu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 12:14:54
	 * @return the avecControleContinu
	 */
	public boolean getAvecControleContinu() {
		return avecControleContinu;
	}

	/**
	 * [InscriptionExamenDto.avecControleContinu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 12:14:54
	 * @param avecControleContinu
	 *            the avecControleContinu to set
	 */
	public void setAvecControleContinu(boolean avecControleContinu) {
		this.avecControleContinu = avecControleContinu;
	}

	/**
	 * [InscriptionExamenDto.rattachementMcTauxExamen] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 12:37:08
	 * @return the rattachementMcTauxExamen
	 */
//	public double getRattachementMcTauxExamen() {
//		return rattachementMcTauxExamen;
//	}

	/**
	 * [InscriptionExamenDto.rattachementMcTauxExamen] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 12:37:08
	 * @param rattachementMcTauxExamen
	 *            the rattachementMcTauxExamen to set
	 */
//	public void setRattachementMcTauxExamen(double rattachementMcTauxExamen) {
//		this.rattachementMcTauxExamen = rattachementMcTauxExamen;
//	}

	/**
	 * [InscriptionExamenDto.rattachementMcTauxControleContinu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 12:37:08
	 * @return the rattachementMcTauxControleContinu
	 */
//	public double getRattachementMcTauxControleContinu() {
//		return rattachementMcTauxControleContinu;
//	}

	/**
	 * [InscriptionExamenDto.rattachementMcTauxControleContinu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 12:37:08
	 * @param rattachementMcTauxControleContinu
	 *            the rattachementMcTauxControleContinu to set
	 */
//	public void setRattachementMcTauxControleContinu(
//			double rattachementMcTauxControleContinu) {
//		this.rattachementMcTauxControleContinu = rattachementMcTauxControleContinu;
//	}

	/**
	 * [InscriptionExamenDto.rattachementMcCredit] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 17:01:36
	 * @return the rattachementMcCredit
	 */
	public Double getRattachementMcCredit() {
		return rattachementMcCredit;
	}

	/**
	 * [InscriptionExamenDto.rattachementMcCredit] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 17:01:36
	 * @param rattachementMcCredit
	 *            the rattachementMcCredit to set
	 */
	public void setRattachementMcCredit(Double rattachementMcCredit) {
		this.rattachementMcCredit = rattachementMcCredit;
	}

	/**
	 * [InscriptionExamenDto.mcCreditObtenu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 nov. 2014 12:23:18
	 * @return the mcCreditObtenu
	 */
	public Double getMcCreditObtenu() {
		if (rattachementMcNoteObtension != null
				&& noteJury >= rattachementMcNoteObtension) {
			mcCreditObtenu = rattachementMcCredit;
		} else {
			mcCreditObtenu = 0.0;
		}
		return mcCreditObtenu;
	}

	/**
	 * [InscriptionExamenDto.mcCreditObtenu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 nov. 2014 12:23:18
	 * @param mcCreditObtenu
	 *            the mcCreditObtenu to set
	 */
	public void setMcCreditObtenu(Double mcCreditObtenu) {
		this.mcCreditObtenu = mcCreditObtenu;
	}

	/**
	 * [InscriptionExamenDto.noteObtenu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 nov. 2014 12:34:24
	 * @return the noteNonObtenu
	 */
	public boolean getNoteObtenu() {
		noteObtenu = false;
		if (noteExamen != null && rattachementMcNoteObtension != null
				&& noteExamen >= rattachementMcNoteObtension && !absent
				&& !copieNonRemise) {
			noteObtenu = true;
		}

		return noteObtenu;
	}

	/**
	 * [InscriptionExamenDto.noteObtenu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 nov. 2014 12:34:24
	 * @param noteNonObtenu
	 *            the noteNonObtenu to set
	 */
	public void setNoteObtenu(boolean noteObtenu) {
		this.noteObtenu = noteObtenu;
	}

	/**
	 * [InscriptionExamenDto.noteJuryToDisplay] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 nov. 2014 12:52:02
	 * @return the noteJuryToDisplay
	 */
	public Double getNoteJuryToDisplay() {
//		if (noteJuryToDisplay != null) {
//			if (noteJury != null && noteSession != null
//					&& noteJury.equals(noteSession)) {
//				noteJuryToDisplay = null;
//			} else {
//				noteJuryToDisplay = noteJury;
//			}
//		} 
		return noteJuryToDisplay;
	}

	/**
	 * [InscriptionExamenDto.noteJuryToDisplay] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 nov. 2014 12:52:02
	 * @param noteJuryToDisplay
	 *            the noteJuryToDisplay to set
	 */
	public void setNoteJuryToDisplay(Double noteJuryToDisplay) {
		this.noteJuryToDisplay = noteJuryToDisplay;
	}

	/**
	 * [InscriptionExamenDto.refLieuCapacite] Getter 
	 * @author BELDI Jamel on : 10 nov. 2014  10:15:19
	 * @return the refLieuCapacite
	 */
	public Short getRefLieuCapacite() {
		return refLieuCapacite;
	}

	/**
	 * [InscriptionExamenDto.refLieuCapacite] Setter 
	 * @author BELDI Jamel on : 10 nov. 2014  10:15:19
	 * @param refLieuCapacite the refLieuCapacite to set
	 */
	public void setRefLieuCapacite(Short refLieuCapacite) {
		this.refLieuCapacite = refLieuCapacite;
	}

	/**
	 * [InscriptionExamenDto.subscribed] Getter 
	 * @author BELDI Jamel on : 12 nov. 2014  11:54:12
	 * @return the subscribed
	 */
	public boolean isSubscribed() {
		return subscribed;
	}

	/**
	 * [InscriptionExamenDto.subscribed] Setter 
	 * @author BELDI Jamel on : 12 nov. 2014  11:54:12
	 * @param subscribed the subscribed to set
	 */
	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}

	/**
	 * [InscriptionExamenDto.moyenneControleContinu] Getter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  13:46:29
	 * @return the moyenneControleContinu
	 */
	public Double getMoyenneControleContinu() {
		return moyenneControleContinu;
	}

	/**
	 * [InscriptionExamenDto.moyenneControleContinu] Setter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  13:46:29
	 * @param moyenneControleContinu the moyenneControleContinu to set
	 */
	public void setMoyenneControleContinu(Double moyenneControleContinu) {
		this.moyenneControleContinu = moyenneControleContinu;
	}

	/**
	 * [InscriptionExamenDto.moyenneGenerale] Getter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  13:46:29
	 * @return the moyenneGenerale
	 */
	public double getMoyenneGenerale() {
		return moyenneGenerale;
	}

	/**
	 * [InscriptionExamenDto.moyenneGenerale] Setter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  13:46:29
	 * @param moyenneGenerale the moyenneGenerale to set
	 */
	public void setMoyenneGenerale(double moyenneGenerale) {
		this.moyenneGenerale = moyenneGenerale;
	}

	/**
	 * [InscriptionExamenDto.coefficientExamen] Getter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  10:12:46
	 * @return the coefficientExamen
	 */
	public Double getCoefficientExamen() {
		return coefficientExamen;
	}

	/**
	 * [InscriptionExamenDto.coefficientExamen] Setter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  10:12:46
	 * @param coefficientExamen the coefficientExamen to set
	 */
	public void setCoefficientExamen(Double coefficientExamen) {
		this.coefficientExamen = coefficientExamen;
	}

	/**
	 * [InscriptionExamenDto.coefficientControleContinu] Getter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  10:13:49
	 * @return the coefficientControleContinu
	 */
	public Double getCoefficientControleContinu() {
		return coefficientControleContinu;
	}

	/**
	 * [InscriptionExamenDto.coefficientControleContinu] Setter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  10:13:49
	 * @param coefficientControleContinu the coefficientControleContinu to set
	 */
	public void setCoefficientControleContinu(Double coefficientControleContinu) {
		this.coefficientControleContinu = coefficientControleContinu;
	}

	/**
	 * [InscriptionExamenDto.coefficientControleIntermediaire] Getter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  10:13:49
	 * @return the coefficientControleIntermediaire
	 */
	public Double getCoefficientControleIntermediaire() {
		return coefficientControleIntermediaire;
	}

	/**
	 * [InscriptionExamenDto.coefficientControleIntermediaire] Setter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  10:13:49
	 * @param coefficientControleIntermediaire the coefficientControleIntermediaire to set
	 */
	public void setCoefficientControleIntermediaire(
			Double coefficientControleIntermediaire) {
		this.coefficientControleIntermediaire = coefficientControleIntermediaire;
	}

	/**
	 * [InscriptionExamenDto.ueNatureLlFr] Getter 
	 * @author MAKERRI Sofiane on : 8 janv. 2015  09:56:09
	 * @return the ueNatureLlFr
	 */
	public String getUeNatureLlFr() {
		return ueNatureLlFr;
	}

	/**
	 * [InscriptionExamenDto.ueNatureLlFr] Setter 
	 * @author MAKERRI Sofiane on : 8 janv. 2015  09:56:09
	 * @param ueNatureLlFr the ueNatureLlFr to set
	 */
	public void setUeNatureLlFr(String ueNatureLlFr) {
		this.ueNatureLlFr = ueNatureLlFr;
	}

	/**
	 * [InscriptionExamenDto.noteControleIntermediaire] Getter 
	 * @author MAKERRI Sofiane on : 11 janv. 2015  13:36:43
	 * @return the noteControleIntermediaire
	 */
	public Double getNoteControleIntermediaire() {
		return noteControleIntermediaire;
	}

	/**
	 * [InscriptionExamenDto.noteControleIntermediaire] Setter 
	 * @author MAKERRI Sofiane on : 11 janv. 2015  13:36:43
	 * @param noteControleIntermediaire the noteControleIntermediaire to set
	 */
	public void setNoteControleIntermediaire(Double noteControleIntermediaire) {
		this.noteControleIntermediaire = noteControleIntermediaire;
	}

	/**
	 * [InscriptionExamenDto.totalCoefficient] Getter 
	 * @author MAKERRI Sofiane on : 12 janv. 2015  10:30:32
	 * @return the totalCoefficient
	 */
	public double getTotalCoefficient() {
		return totalCoefficient;
	}

	/**
	 * [InscriptionExamenDto.totalCoefficient] Setter 
	 * @author MAKERRI Sofiane on : 12 janv. 2015  10:30:32
	 * @param totalCoefficient the totalCoefficient to set
	 */
	public void setTotalCoefficient(double totalCoefficient) {
		this.totalCoefficient = totalCoefficient;
	}

	/**
	 * [InscriptionExamenDto.noteEliminatoire] Getter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  15:29:36
	 * @return the noteEliminatoire
	 */
	public double getNoteEliminatoire() {
		return noteEliminatoire;
	}

	/**
	 * [InscriptionExamenDto.noteEliminatoire] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  15:29:36
	 * @param noteEliminatoire the noteEliminatoire to set
	 */
	public void setNoteEliminatoire(double noteEliminatoire) {
		this.noteEliminatoire = noteEliminatoire;
	}

	

	
	
}
