package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.util.Date;

public class ExclusionDto implements java.io.Serializable {
	

	/**
	 * serialVersionUID 
	 * @author BELBRIK Oualid  on : 03 07 2014  10:47:28
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String refCodeTypeExclusion;
	private String refCodeTypeCause;
	private String cause;
	private Date dateDecision;
	private Date dateDebut;
	private Date dateFin;
	private Short nombreAbsence;
	
	//private MatiereConstitutive mc;
	private Integer idMc;
	private String codeMc;
	private String libelleFrMc;

	//private DossierInscriptionAdministrative dossierInscriptionAdministrative;
	private Integer dossierInscriptionId;	
	private Integer dossierEtudiantId;
	private String dossierEtudiantMatricule;
	private String refCodeEtabOrigine;
	 
	private Integer anneeAcademiqueId;
	private String anneeAcademiqueCode;
	private Integer dossierId;
	private String numeroMatricule;

	 //Individu
    private Integer individuId;
	private String individuNin;
	private String individuNomArabe;
	/**
	 * [ExclusionDto.refCodeTypeExclusion] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the refCodeTypeExclusion
	 */
	public String getRefCodeTypeExclusion() {
		return refCodeTypeExclusion;
	}



	/**
	 * [ExclusionDto.refCodeTypeExclusion] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param refCodeTypeExclusion the refCodeTypeExclusion to set
	 */
	public void setRefCodeTypeExclusion(String refCodeTypeExclusion) {
		this.refCodeTypeExclusion = refCodeTypeExclusion;
	}



	/**
	 * [ExclusionDto.refCodeTypeCause] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the refCodeTypeCause
	 */
	public String getRefCodeTypeCause() {
		return refCodeTypeCause;
	}



	/**
	 * [ExclusionDto.refCodeTypeCause] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param refCodeTypeCause the refCodeTypeCause to set
	 */
	public void setRefCodeTypeCause(String refCodeTypeCause) {
		this.refCodeTypeCause = refCodeTypeCause;
	}



	/**
	 * [ExclusionDto.cause] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}



	/**
	 * [ExclusionDto.cause] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param cause the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}



	/**
	 * [ExclusionDto.dateDecision] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the dateDecision
	 */
	public Date getDateDecision() {
		return dateDecision;
	}



	/**
	 * [ExclusionDto.dateDecision] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param dateDecision the dateDecision to set
	 */
	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}



	/**
	 * [ExclusionDto.dateDebut] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}



	/**
	 * [ExclusionDto.dateDebut] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}



	/**
	 * [ExclusionDto.dateFin] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}



	/**
	 * [ExclusionDto.dateFin] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}



	/**
	 * [ExclusionDto.idMc] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the idMc
	 */
	public Integer getIdMc() {
		return idMc;
	}



	/**
	 * [ExclusionDto.idMc] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param idMc the idMc to set
	 */
	public void setIdMc(Integer idMc) {
		this.idMc = idMc;
	}



	/**
	 * [ExclusionDto.codeMc] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the codeMc
	 */
	public String getCodeMc() {
		return codeMc;
	}



	/**
	 * [ExclusionDto.codeMc] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param codeMc the codeMc to set
	 */
	public void setCodeMc(String codeMc) {
		this.codeMc = codeMc;
	}



	/**
	 * [ExclusionDto.libelleFrMc] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the libelleFrMc
	 */
	public String getLibelleFrMc() {
		return libelleFrMc;
	}



	/**
	 * [ExclusionDto.libelleFrMc] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param libelleFrMc the libelleFrMc to set
	 */
	public void setLibelleFrMc(String libelleFrMc) {
		this.libelleFrMc = libelleFrMc;
	}



	/**
	 * [ExclusionDto.dossierInscriptionId] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the dossierInscriptionId
	 */
	public Integer getDossierInscriptionId() {
		return dossierInscriptionId;
	}



	/**
	 * [ExclusionDto.dossierInscriptionId] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param dossierInscriptionId the dossierInscriptionId to set
	 */
	public void setDossierInscriptionId(Integer dossierInscriptionId) {
		this.dossierInscriptionId = dossierInscriptionId;
	}



	/**
	 * [ExclusionDto.dossierEtudiantId] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the dossierEtudiantId
	 */
	public Integer getDossierEtudiantId() {
		return dossierEtudiantId;
	}



	/**
	 * [ExclusionDto.dossierEtudiantId] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param dossierEtudiantId the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(Integer dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}



	/**
	 * [ExclusionDto.dossierEtudiantMatricule] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the dossierEtudiantMatricule
	 */
	public String getDossierEtudiantMatricule() {
		return dossierEtudiantMatricule;
	}



	/**
	 * [ExclusionDto.dossierEtudiantMatricule] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param dossierEtudiantMatricule the dossierEtudiantMatricule to set
	 */
	public void setDossierEtudiantMatricule(String dossierEtudiantMatricule) {
		this.dossierEtudiantMatricule = dossierEtudiantMatricule;
	}



	/**
	 * [ExclusionDto.refCodeEtabOrigine] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the refCodeEtabOrigine
	 */
	public String getRefCodeEtabOrigine() {
		return refCodeEtabOrigine;
	}



	/**
	 * [ExclusionDto.refCodeEtabOrigine] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param refCodeEtabOrigine the refCodeEtabOrigine to set
	 */
	public void setRefCodeEtabOrigine(String refCodeEtabOrigine) {
		this.refCodeEtabOrigine = refCodeEtabOrigine;
	}



	/**
	 * [ExclusionDto.anneeAcademiqueId] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}



	/**
	 * [ExclusionDto.anneeAcademiqueId] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param anneeAcademiqueId the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}



	/**
	 * [ExclusionDto.anneeAcademiqueCode] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}



	/**
	 * [ExclusionDto.anneeAcademiqueCode] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param anneeAcademiqueCode the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}



	/**
	 * [ExclusionDto.dossierId] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the dossierId
	 */
	public Integer getDossierId() {
		return dossierId;
	}



	/**
	 * [ExclusionDto.dossierId] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param dossierId the dossierId to set
	 */
	public void setDossierId(Integer dossierId) {
		this.dossierId = dossierId;
	}



	/**
	 * [ExclusionDto.numeroMatricule] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the numeroMatricule
	 */
	public String getNumeroMatricule() {
		return numeroMatricule;
	}



	/**
	 * [ExclusionDto.numeroMatricule] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param numeroMatricule the numeroMatricule to set
	 */
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}



	/**
	 * [ExclusionDto.individuId] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the individuId
	 */
	public Integer getIndividuId() {
		return individuId;
	}



	/**
	 * [ExclusionDto.individuId] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param individuId the individuId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}



	/**
	 * [ExclusionDto.individuNin] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the individuNin
	 */
	public String getIndividuNin() {
		return individuNin;
	}



	/**
	 * [ExclusionDto.individuNin] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param individuNin the individuNin to set
	 */
	public void setIndividuNin(String individuNin) {
		this.individuNin = individuNin;
	}



	/**
	 * [ExclusionDto.individuNomArabe] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the individuNomArabe
	 */
	public String getIndividuNomArabe() {
		return individuNomArabe;
	}



	/**
	 * [ExclusionDto.individuNomArabe] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param individuNomArabe the individuNomArabe to set
	 */
	public void setIndividuNomArabe(String individuNomArabe) {
		this.individuNomArabe = individuNomArabe;
	}



	/**
	 * [ExclusionDto.individuNomLatin] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the individuNomLatin
	 */
	public String getIndividuNomLatin() {
		return individuNomLatin;
	}



	/**
	 * [ExclusionDto.individuNomLatin] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param individuNomLatin the individuNomLatin to set
	 */
	public void setIndividuNomLatin(String individuNomLatin) {
		this.individuNomLatin = individuNomLatin;
	}



	/**
	 * [ExclusionDto.individuPrenomArabe] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the individuPrenomArabe
	 */
	public String getIndividuPrenomArabe() {
		return individuPrenomArabe;
	}



	/**
	 * [ExclusionDto.individuPrenomArabe] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param individuPrenomArabe the individuPrenomArabe to set
	 */
	public void setIndividuPrenomArabe(String individuPrenomArabe) {
		this.individuPrenomArabe = individuPrenomArabe;
	}



	/**
	 * [ExclusionDto.individuPrenomLatin] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the individuPrenomLatin
	 */
	public String getIndividuPrenomLatin() {
		return individuPrenomLatin;
	}



	/**
	 * [ExclusionDto.individuPrenomLatin] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param individuPrenomLatin the individuPrenomLatin to set
	 */
	public void setIndividuPrenomLatin(String individuPrenomLatin) {
		this.individuPrenomLatin = individuPrenomLatin;
	}



	/**
	 * [ExclusionDto.individuDateNaissance] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the individuDateNaissance
	 */
	public Date getIndividuDateNaissance() {
		return individuDateNaissance;
	}



	/**
	 * [ExclusionDto.individuDateNaissance] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param individuDateNaissance the individuDateNaissance to set
	 */
	public void setIndividuDateNaissance(Date individuDateNaissance) {
		this.individuDateNaissance = individuDateNaissance;
	}



	/**
	 * [ExclusionDto.individuNationaliteLibelleLongFr] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the individuNationaliteLibelleLongFr
	 */
	public String getIndividuNationaliteLibelleLongFr() {
		return individuNationaliteLibelleLongFr;
	}



	/**
	 * [ExclusionDto.individuNationaliteLibelleLongFr] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param individuNationaliteLibelleLongFr the individuNationaliteLibelleLongFr to set
	 */
	public void setIndividuNationaliteLibelleLongFr(
			String individuNationaliteLibelleLongFr) {
		this.individuNationaliteLibelleLongFr = individuNationaliteLibelleLongFr;
	}



	/**
	 * [ExclusionDto.individuNationaliteLibelleLongAr] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the individuNationaliteLibelleLongAr
	 */
	public String getIndividuNationaliteLibelleLongAr() {
		return individuNationaliteLibelleLongAr;
	}



	/**
	 * [ExclusionDto.individuNationaliteLibelleLongAr] Setter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @param individuNationaliteLibelleLongAr the individuNationaliteLibelleLongAr to set
	 */
	public void setIndividuNationaliteLibelleLongAr(
			String individuNationaliteLibelleLongAr) {
		this.individuNationaliteLibelleLongAr = individuNationaliteLibelleLongAr;
	}



	/**
	 * [ExclusionDto.serialversionuid] Getter 
	 * @author BELBRIK Oualid on : 3 juil. 2014  16:16:06
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	private String individuNomLatin;
	private String individuPrenomArabe;
	private String individuPrenomLatin;
	private Date individuDateNaissance;
	private String individuNationaliteLibelleLongFr;
	private String individuNationaliteLibelleLongAr;
	   
		
	public ExclusionDto() {
	}



	/**
	 * [ParcoursIndividualiseDto.id] Getter 
	 * @author BELBRIK Oualid on : 1 07 2014  12:09:34
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * [ParcoursIndividualiseDto.id] Setter 
	 * @author BELBRIK Oualid on : 1 07 2014  12:09:34
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}



	/**
	 * [ExclusionDto.nombreAbsence] Getter 
	 * @author BELBRIK Oualid on : 7 juil. 2014  15:47:37
	 * @return the nombreAbsence
	 */
	public Short getNombreAbsence() {
		return nombreAbsence;
	}



	/**
	 * [ExclusionDto.nombreAbsence] Setter 
	 * @author BELBRIK Oualid on : 7 juil. 2014  15:47:37
	 * @param nombreAbsence the nombreAbsence to set
	 */
	public void setNombreAbsence(Short nombreAbsence) {
		this.nombreAbsence = nombreAbsence;
	}


	
}
