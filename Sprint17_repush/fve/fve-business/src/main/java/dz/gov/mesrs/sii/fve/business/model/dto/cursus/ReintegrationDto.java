package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.util.Date;

public class ReintegrationDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 9 Juillet 2014 10:47:28
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private Date dateDemande;
	private Boolean resultat;
	private String objet;
	private Date dateResultat;
	private Date dateAutorisation;
	private Date dateReintegration;
	private String motif;

	private Boolean reintegrationValidee;
	private Date dateValidation;

	// private Exclusion exclusion;
	private Integer exclusionId;
	private Integer dossierId;
	private String refCodeTypeExclusion;
	private String refCodeTypeCauseExclusion;
	private String causeExclusion;
	private Date dateDecisionExclusion;
	private Date dateDebutExclusion;
	private Date dateFinExclusion;
	private Short nombreAbsence;
	private Integer idMc;
	private String codeMc;
	private String libelleFrMc;

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

	// private AnneeAcademique anneeAcademique;
	private Integer anneeAcademiqueId;
	private String anneeAcademiqueCode;
	private Short anneeAcademiquePremiereAnnee;
	private Short anneeAcademiqueDeuxiemeAnnee;

	// private DossierInscriptionAdministrative
	// dossierInscriptionAdministrative;
	private Integer dossierInscriptionId;
	private Integer dossierEtudiantId;
	private String dossierEtudiantMatricule;

	public ReintegrationDto() {
	}

	/**
	 * [ParcoursIndividualiseDto.id] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 Juillet 2014 12:09:34
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [ParcoursIndividualiseDto.id] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 Juillet 2014 12:09:34
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [ReintegrationDto.dateDemande] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juil. 2014 11:56:42
	 * @return the dateDemande
	 */
	public Date getDateDemande() {
		return dateDemande;
	}

	/**
	 * [ReintegrationDto.dateDemande] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juil. 2014 11:56:42
	 * @param dateDemande
	 *            the dateDemande to set
	 */
	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	/**
	 * [ReintegrationDto.resultat] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juil. 2014 11:56:42
	 * @return the resultat
	 */
	public Boolean getResultat() {
		return resultat;
	}

	/**
	 * [ReintegrationDto.resultat] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juil. 2014 11:56:42
	 * @param resultat
	 *            the resultat to set
	 */
	public void setResultat(Boolean resultat) {
		this.resultat = resultat;
	}

	/**
	 * [ReintegrationDto.objet] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juil. 2014 11:56:42
	 * @return the objet
	 */
	public String getObjet() {
		return objet;
	}

	/**
	 * [ReintegrationDto.objet] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juil. 2014 11:56:42
	 * @param objet
	 *            the objet to set
	 */
	public void setObjet(String objet) {
		this.objet = objet;
	}

	/**
	 * [ReintegrationDto.dateResultat] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juil. 2014 11:56:42
	 * @return the dateResultat
	 */
	public Date getDateResultat() {
		return dateResultat;
	}

	/**
	 * [ReintegrationDto.dateResultat] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juil. 2014 11:56:42
	 * @param dateResultat
	 *            the dateResultat to set
	 */
	public void setDateResultat(Date dateResultat) {
		this.dateResultat = dateResultat;
	}

	/**
	 * [ReintegrationDto.dateAutorisation] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juil. 2014 11:56:42
	 * @return the dateAutorisation
	 */
	public Date getDateAutorisation() {
		return dateAutorisation;
	}

	/**
	 * [ReintegrationDto.dateAutorisation] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juil. 2014 11:56:42
	 * @param dateAutorisation
	 *            the dateAutorisation to set
	 */
	public void setDateAutorisation(Date dateAutorisation) {
		this.dateAutorisation = dateAutorisation;
	}

	/**
	 * [ReintegrationDto.dateReintegration] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juil. 2014 11:56:42
	 * @return the dateReintegration
	 */
	public Date getDateReintegration() {
		return dateReintegration;
	}

	/**
	 * [ReintegrationDto.dateReintegration] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juil. 2014 11:56:42
	 * @param dateReintegration
	 *            the dateReintegration to set
	 */
	public void setDateReintegration(Date dateReintegration) {
		this.dateReintegration = dateReintegration;
	}

	/**
	 * [ReintegrationDto.exclusionId] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juil. 2014 11:56:42
	 * @return the exclusionId
	 */
	public Integer getExclusionId() {
		return exclusionId;
	}

	/**
	 * [ReintegrationDto.exclusionId] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juil. 2014 11:56:42
	 * @param exclusionId
	 *            the exclusionId to set
	 */
	public void setExclusionId(Integer exclusionId) {
		this.exclusionId = exclusionId;
	}

	/**
	 * [ReintegrationDto.serialversionuid] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juil. 2014 11:56:42
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * [ReintegrationDto.refCodeTypeExclusion] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @return the refCodeTypeExclusion
	 */
	public String getRefCodeTypeExclusion() {
		return refCodeTypeExclusion;
	}

	/**
	 * [ReintegrationDto.refCodeTypeExclusion] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @param refCodeTypeExclusion
	 *            the refCodeTypeExclusion to set
	 */
	public void setRefCodeTypeExclusion(String refCodeTypeExclusion) {
		this.refCodeTypeExclusion = refCodeTypeExclusion;
	}

	/**
	 * [ReintegrationDto.refCodeTypeCauseExclusion] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @return the refCodeTypeCauseExclusion
	 */
	public String getRefCodeTypeCauseExclusion() {
		return refCodeTypeCauseExclusion;
	}

	/**
	 * [ReintegrationDto.refCodeTypeCauseExclusion] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @param refCodeTypeCauseExclusion
	 *            the refCodeTypeCauseExclusion to set
	 */
	public void setRefCodeTypeCauseExclusion(String refCodeTypeCauseExclusion) {
		this.refCodeTypeCauseExclusion = refCodeTypeCauseExclusion;
	}

	/**
	 * [ReintegrationDto.causeExclusion] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @return the causeExclusion
	 */
	public String getCauseExclusion() {
		return causeExclusion;
	}

	/**
	 * [ReintegrationDto.causeExclusion] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @param causeExclusion
	 *            the causeExclusion to set
	 */
	public void setCauseExclusion(String causeExclusion) {
		this.causeExclusion = causeExclusion;
	}

	/**
	 * [ReintegrationDto.dateDecisionExclusion] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @return the dateDecisionExclusion
	 */
	public Date getDateDecisionExclusion() {
		return dateDecisionExclusion;
	}

	/**
	 * [ReintegrationDto.dateDecisionExclusion] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @param dateDecisionExclusion
	 *            the dateDecisionExclusion to set
	 */
	public void setDateDecisionExclusion(Date dateDecisionExclusion) {
		this.dateDecisionExclusion = dateDecisionExclusion;
	}

	/**
	 * [ReintegrationDto.dateDebutExclusion] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @return the dateDebutExclusion
	 */
	public Date getDateDebutExclusion() {
		return dateDebutExclusion;
	}

	/**
	 * [ReintegrationDto.dateDebutExclusion] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @param dateDebutExclusion
	 *            the dateDebutExclusion to set
	 */
	public void setDateDebutExclusion(Date dateDebutExclusion) {
		this.dateDebutExclusion = dateDebutExclusion;
	}

	/**
	 * [ReintegrationDto.dateFinExclusion] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @return the dateFinExclusion
	 */
	public Date getDateFinExclusion() {
		return dateFinExclusion;
	}

	/**
	 * [ReintegrationDto.dateFinExclusion] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @param dateFinExclusion
	 *            the dateFinExclusion to set
	 */
	public void setDateFinExclusion(Date dateFinExclusion) {
		this.dateFinExclusion = dateFinExclusion;
	}

	/**
	 * [ReintegrationDto.nombreAbsence] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @return the nombreAbsence
	 */
	public Short getNombreAbsence() {
		return nombreAbsence;
	}

	/**
	 * [ReintegrationDto.nombreAbsence] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @param nombreAbsence
	 *            the nombreAbsence to set
	 */
	public void setNombreAbsence(Short nombreAbsence) {
		this.nombreAbsence = nombreAbsence;
	}

	/**
	 * [ReintegrationDto.idMc] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @return the idMc
	 */
	public Integer getIdMc() {
		return idMc;
	}

	/**
	 * [ReintegrationDto.idMc] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @param idMc
	 *            the idMc to set
	 */
	public void setIdMc(Integer idMc) {
		this.idMc = idMc;
	}

	/**
	 * [ReintegrationDto.codeMc] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @return the codeMc
	 */
	public String getCodeMc() {
		return codeMc;
	}

	/**
	 * [ReintegrationDto.codeMc] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @param codeMc
	 *            the codeMc to set
	 */
	public void setCodeMc(String codeMc) {
		this.codeMc = codeMc;
	}

	/**
	 * [ReintegrationDto.libelleFrMc] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @return the libelleFrMc
	 */
	public String getLibelleFrMc() {
		return libelleFrMc;
	}

	/**
	 * [ReintegrationDto.libelleFrMc] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @param libelleFrMc
	 *            the libelleFrMc to set
	 */
	public void setLibelleFrMc(String libelleFrMc) {
		this.libelleFrMc = libelleFrMc;
	}

	/**
	 * [ReintegrationDto.anneeAcademiqueId] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [ReintegrationDto.anneeAcademiqueId] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [ReintegrationDto.anneeAcademiqueCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}

	/**
	 * [ReintegrationDto.anneeAcademiqueCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @param anneeAcademiqueCode
	 *            the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	/**
	 * [ReintegrationDto.individuId] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @return the individuId
	 */
	public Integer getIndividuId() {
		return individuId;
	}

	/**
	 * [ReintegrationDto.individuId] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @param individuId
	 *            the individuId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}

	/**
	 * [ReintegrationDto.individuNin] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @return the individuNin
	 */
	public String getIndividuNin() {
		return individuNin;
	}

	/**
	 * [ReintegrationDto.individuNin] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @param individuNin
	 *            the individuNin to set
	 */
	public void setIndividuNin(String individuNin) {
		this.individuNin = individuNin;
	}

	/**
	 * [ReintegrationDto.individuNomArabe] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @return the individuNomArabe
	 */
	public String getIndividuNomArabe() {
		return individuNomArabe;
	}

	/**
	 * [ReintegrationDto.individuNomArabe] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 12:11:32
	 * @param individuNomArabe
	 *            the individuNomArabe to set
	 */
	public void setIndividuNomArabe(String individuNomArabe) {
		this.individuNomArabe = individuNomArabe;
	}

	/**
	 * [ReintegrationDto.individuNomLatin] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 13:33:52
	 * @return the individuNomLatin
	 */
	public String getIndividuNomLatin() {
		return individuNomLatin;
	}

	/**
	 * [ReintegrationDto.individuNomLatin] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 13:33:52
	 * @param individuNomLatin
	 *            the individuNomLatin to set
	 */
	public void setIndividuNomLatin(String individuNomLatin) {
		this.individuNomLatin = individuNomLatin;
	}

	/**
	 * [ReintegrationDto.individuPrenomArabe] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 13:33:52
	 * @return the individuPrenomArabe
	 */
	public String getIndividuPrenomArabe() {
		return individuPrenomArabe;
	}

	/**
	 * [ReintegrationDto.individuPrenomArabe] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 13:33:52
	 * @param individuPrenomArabe
	 *            the individuPrenomArabe to set
	 */
	public void setIndividuPrenomArabe(String individuPrenomArabe) {
		this.individuPrenomArabe = individuPrenomArabe;
	}

	/**
	 * [ReintegrationDto.individuPrenomLatin] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 13:33:52
	 * @return the individuPrenomLatin
	 */
	public String getIndividuPrenomLatin() {
		return individuPrenomLatin;
	}

	/**
	 * [ReintegrationDto.individuPrenomLatin] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 13:33:52
	 * @param individuPrenomLatin
	 *            the individuPrenomLatin to set
	 */
	public void setIndividuPrenomLatin(String individuPrenomLatin) {
		this.individuPrenomLatin = individuPrenomLatin;
	}

	/**
	 * [ReintegrationDto.individuDateNaissance] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 13:33:52
	 * @return the individuDateNaissance
	 */
	public Date getIndividuDateNaissance() {
		return individuDateNaissance;
	}

	/**
	 * [ReintegrationDto.individuDateNaissance] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 13:33:52
	 * @param individuDateNaissance
	 *            the individuDateNaissance to set
	 */
	public void setIndividuDateNaissance(Date individuDateNaissance) {
		this.individuDateNaissance = individuDateNaissance;
	}

	/**
	 * [ReintegrationDto.individuNationaliteLibelleLongFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 13:33:52
	 * @return the individuNationaliteLibelleLongFr
	 */
	public String getIndividuNationaliteLibelleLongFr() {
		return individuNationaliteLibelleLongFr;
	}

	/**
	 * [ReintegrationDto.individuNationaliteLibelleLongFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 13:33:52
	 * @param individuNationaliteLibelleLongFr
	 *            the individuNationaliteLibelleLongFr to set
	 */
	public void setIndividuNationaliteLibelleLongFr(
			String individuNationaliteLibelleLongFr) {
		this.individuNationaliteLibelleLongFr = individuNationaliteLibelleLongFr;
	}

	/**
	 * [ReintegrationDto.individuNationaliteLibelleLongAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 13:33:52
	 * @return the individuNationaliteLibelleLongAr
	 */
	public String getIndividuNationaliteLibelleLongAr() {
		return individuNationaliteLibelleLongAr;
	}

	/**
	 * [ReintegrationDto.individuNationaliteLibelleLongAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 13:33:52
	 * @param individuNationaliteLibelleLongAr
	 *            the individuNationaliteLibelleLongAr to set
	 */
	public void setIndividuNationaliteLibelleLongAr(
			String individuNationaliteLibelleLongAr) {
		this.individuNationaliteLibelleLongAr = individuNationaliteLibelleLongAr;
	}

	/**
	 * [ReintegrationDto.dossierId] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 15:49:57
	 * @return the dossierId
	 */
	public Integer getDossierId() {
		return dossierId;
	}

	/**
	 * [ReintegrationDto.dossierId] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 juil. 2014 15:49:57
	 * @param dossierId
	 *            the dossierId to set
	 */
	public void setDossierId(Integer dossierId) {
		this.dossierId = dossierId;
	}

	/**
	 * [ReintegrationDto.dossierInscriptionId] Getter
	 * 
	 * @author BELBRIK Oualid on : 14 juil. 2014 11:26:54
	 * @return the dossierInscriptionId
	 */
	public Integer getDossierInscriptionId() {
		return dossierInscriptionId;
	}

	/**
	 * [ReintegrationDto.dossierInscriptionId] Setter
	 * 
	 * @author BELBRIK Oualid on : 14 juil. 2014 11:26:54
	 * @param dossierInscriptionId
	 *            the dossierInscriptionId to set
	 */
	public void setDossierInscriptionId(Integer dossierInscriptionId) {
		this.dossierInscriptionId = dossierInscriptionId;
	}

	/**
	 * [ReintegrationDto.dossierEtudiantId] Getter
	 * 
	 * @author BELBRIK Oualid on : 14 juil. 2014 11:26:54
	 * @return the dossierEtudiantId
	 */
	public Integer getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [ReintegrationDto.dossierEtudiantId] Setter
	 * 
	 * @author BELBRIK Oualid on : 14 juil. 2014 11:26:54
	 * @param dossierEtudiantId
	 *            the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(Integer dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	/**
	 * [ReintegrationDto.dossierEtudiantMatricule] Getter
	 * 
	 * @author BELBRIK Oualid on : 14 juil. 2014 11:26:54
	 * @return the dossierEtudiantMatricule
	 */
	public String getDossierEtudiantMatricule() {
		return dossierEtudiantMatricule;
	}

	/**
	 * [ReintegrationDto.dossierEtudiantMatricule] Setter
	 * 
	 * @author BELBRIK Oualid on : 14 juil. 2014 11:26:54
	 * @param dossierEtudiantMatricule
	 *            the dossierEtudiantMatricule to set
	 */
	public void setDossierEtudiantMatricule(String dossierEtudiantMatricule) {
		this.dossierEtudiantMatricule = dossierEtudiantMatricule;
	}

	/**
	 * [ReintegrationDto.motif] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 juil. 2014 11:53:21
	 * @return the motif
	 */
	public String getMotif() {
		return motif;
	}

	/**
	 * [ReintegrationDto.motif] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 juil. 2014 11:53:21
	 * @param motif
	 *            the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getIndividuLieuNaissance() {
		return individuLieuNaissance;
	}

	public void setIndividuLieuNaissance(String individuLieuNaissance) {
		this.individuLieuNaissance = individuLieuNaissance;
	}

	public Boolean getReintegrationValidee() {
		return reintegrationValidee;
	}

	public void setReintegrationValidee(Boolean reintegrationValidee) {
		this.reintegrationValidee = reintegrationValidee;
	}

	public Date getDateValidation() {
		return dateValidation;
	}

	public void setDateValidation(Date dateValidation) {
		this.dateValidation = dateValidation;
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
}
