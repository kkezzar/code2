package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.util.Date;

public class AbsenceMatiereDto implements java.io.Serializable {
	

	/**
	 * serialVersionUID 
	 * @author BELBRIK Oualid  on : 1 juin 2014  10:47:28
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String refCodeTypeAbsence;
	private String refCodeModeEnvoi;
	private Boolean justf;
	private Date dateJustif;
	private Date dateAbsence;
	private String dure;	
	private String motif;
	
	//private MatiereConstitutive mc;
	private Integer idMc;
	private String codeMc;
	private String libelleFrMc;
	
	//private AtomePedagogique ap;
	private Integer idAp;
	private String codeAp;
	private Double volumeHoraireAp;
	

	//private DossierInscriptionAdministrative dossierInscriptionAdministrative;
	   private Integer dossierInscriptionId;	
	   private Integer dossierEtudiantId;
	   private String dossierEtudiantMatricule;
	   private String refCodeEtabOrigine;
	 
	private Integer anneeAcademiqueId;
	private String anneeAcademiqueCode;
	private Integer dossierId;
	private String numeroMatricule;

	private String refCodeDomaine;
	private String refCodeFiliere;
	private String refCodeSpecialite;

	private String refLibelleDomaine;
	private String refLibelleFiliere;
	private String refLibelleSpecialite;

	private String refCodeCycle;
	private String refCodeNiveau;
	private String refLibelleCycle;
	private String refLibelleNiveau;
	private Date dateInscription;

	 //Individu
    private Integer individuId;
	private String individuNin;
	private String individuNomArabe;
	private String individuNomLatin;
	private String individuPrenomArabe;
	private String individuPrenomLatin;
	private Date individuDateNaissance;
	private String individuNationaliteLibelleLongFr;
	private String individuNationaliteLibelleLongAr;
	   
		
	public AbsenceMatiereDto() {
	}



	/**
	 * [ParcoursIndividualiseDto.id] Getter 
	 * @author BELBRIK Oualid on : 1 juin 2014  12:09:34
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * [ParcoursIndividualiseDto.id] Setter 
	 * @author BELBRIK Oualid on : 1 juin 2014  12:09:34
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * [AbsenceMatiereDto.refCodeTypeAbsence] Getter 
	 * @author BELBRIK Oualid on : 8 juin 2014  17:13:58
	 * @return the refCodeTypeAbsence
	 */
	public String getRefCodeTypeAbsence() {
		return refCodeTypeAbsence;
	}



	/**
	 * [AbsenceMatiereDto.refCodeTypeAbsence] Setter 
	 * @author BELBRIK Oualid on : 8 juin 2014  17:13:58
	 * @param refCodeTypeAbsence the refCodeTypeAbsence to set
	 */
	public void setRefCodeTypeAbsence(String refCodeTypeAbsence) {
		this.refCodeTypeAbsence = refCodeTypeAbsence;
	}



	/**
	 * [AbsenceMatiereDto.refCodeModeEnvoi] Getter 
	 * @author BELBRIK Oualid on : 8 juin 2014  17:13:58
	 * @return the refCodeModeEnvoi
	 */
	public String getRefCodeModeEnvoi() {
		return refCodeModeEnvoi;
	}



	/**
	 * [AbsenceMatiereDto.refCodeModeEnvoi] Setter 
	 * @author BELBRIK Oualid on : 8 juin 2014  17:13:58
	 * @param refCodeModeEnvoi the refCodeModeEnvoi to set
	 */
	public void setRefCodeModeEnvoi(String refCodeModeEnvoi) {
		this.refCodeModeEnvoi = refCodeModeEnvoi;
	}



	/**
	 * [AbsenceMatiereDto.justf] Getter 
	 * @author BELBRIK Oualid on : 8 juin 2014  17:13:58
	 * @return the justf
	 */
	public Boolean getJustf() {
		return justf;
	}



	/**
	 * [AbsenceMatiereDto.justf] Setter 
	 * @author BELBRIK Oualid on : 8 juin 2014  17:13:58
	 * @param justf the justf to set
	 */
	public void setJustf(Boolean justf) {
		this.justf = justf;
	}



	/**
	 * [AbsenceMatiereDto.dateJustif] Getter 
	 * @author BELBRIK Oualid on : 8 juin 2014  17:13:58
	 * @return the dateJustif
	 */
	public Date getDateJustif() {
		return dateJustif;
	}



	/**
	 * [AbsenceMatiereDto.dateJustif] Setter 
	 * @author BELBRIK Oualid on : 8 juin 2014  17:13:58
	 * @param dateJustif the dateJustif to set
	 */
	public void setDateJustif(Date dateJustif) {
		this.dateJustif = dateJustif;
	}



	/**
	 * [AbsenceMatiereDto.dateAbsence] Getter 
	 * @author BELBRIK Oualid on : 8 juin 2014  17:13:58
	 * @return the dateAbsence
	 */
	public Date getDateAbsence() {
		return dateAbsence;
	}



	/**
	 * [AbsenceMatiereDto.dateAbsence] Setter 
	 * @author BELBRIK Oualid on : 8 juin 2014  17:13:58
	 * @param dateAbsence the dateAbsence to set
	 */
	public void setDateAbsence(Date dateAbsence) {
		this.dateAbsence = dateAbsence;
	}



	/**
	 * [AbsenceMatiereDto.dure] Getter 
	 * @author BELBRIK Oualid on : 8 juin 2014  17:13:58
	 * @return the dure
	 */
	public String getDure() {
		return dure;
	}



	/**
	 * [AbsenceMatiereDto.dure] Setter 
	 * @author BELBRIK Oualid on : 8 juin 2014  17:13:58
	 * @param dure the dure to set
	 */
	public void setDure(String dure) {
		this.dure = dure;
	}



	/**
	 * [AbsenceMatiereDto.serialversionuid] Getter 
	 * @author BELBRIK Oualid on : 8 juin 2014  17:13:58
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	/**
	 * [AbsenceMatiereDto.idMc] Getter 
	 * @author BELBRIK Oualid on : 11 juin 2014  11:26:25
	 * @return the idMc
	 */
	public Integer getIdMc() {
		return idMc;
	}



	/**
	 * [AbsenceMatiereDto.idMc] Setter 
	 * @author BELBRIK Oualid on : 11 juin 2014  11:26:25
	 * @param idMc the idMc to set
	 */
	public void setIdMc(Integer idMc) {
		this.idMc = idMc;
	}



	/**
	 * [AbsenceMatiereDto.codeMc] Getter 
	 * @author BELBRIK Oualid on : 11 juin 2014  11:26:25
	 * @return the codeMc
	 */
	public String getCodeMc() {
		return codeMc;
	}



	/**
	 * [AbsenceMatiereDto.codeMc] Setter 
	 * @author BELBRIK Oualid on : 11 juin 2014  11:26:25
	 * @param codeMc the codeMc to set
	 */
	public void setCodeMc(String codeMc) {
		this.codeMc = codeMc;
	}



	/**
	 * [AbsenceMatiereDto.libelleFrMc] Getter 
	 * @author BELBRIK Oualid on : 11 juin 2014  11:26:25
	 * @return the libelleFrMc
	 */
	public String getLibelleFrMc() {
		return libelleFrMc;
	}



	/**
	 * [AbsenceMatiereDto.libelleFrMc] Setter 
	 * @author BELBRIK Oualid on : 11 juin 2014  11:26:25
	 * @param libelleFrMc the libelleFrMc to set
	 */
	public void setLibelleFrMc(String libelleFrMc) {
		this.libelleFrMc = libelleFrMc;
	}



	

	/**
	 * [AbsenceMatiereDto.idAp] Getter 
	 * @author BELBRIK Oualid on : 11 juin 2014  11:26:25
	 * @return the idAp
	 */
	public Integer getIdAp() {
		return idAp;
	}



	/**
	 * [AbsenceMatiereDto.idAp] Setter 
	 * @author BELBRIK Oualid on : 11 juin 2014  11:26:25
	 * @param idAp the idAp to set
	 */
	public void setIdAp(Integer idAp) {
		this.idAp = idAp;
	}



	/**
	 * [AbsenceMatiereDto.codeAp] Getter 
	 * @author BELBRIK Oualid on : 11 juin 2014  11:26:25
	 * @return the codeAp
	 */
	public String getCodeAp() {
		return codeAp;
	}



	/**
	 * [AbsenceMatiereDto.codeAp] Setter 
	 * @author BELBRIK Oualid on : 11 juin 2014  11:26:25
	 * @param codeAp the codeAp to set
	 */
	public void setCodeAp(String codeAp) {
		this.codeAp = codeAp;
	}





	/**
	 * [AbsenceMatiereDto.volumeHoraireAp] Getter 
	 * @author BELBRIK Oualid on : 11 juin 2014  11:26:25
	 * @return the volumeHoraireAp
	 */
	public Double getVolumeHoraireAp() {
		return volumeHoraireAp;
	}



	/**
	 * [AbsenceMatiereDto.volumeHoraireAp] Setter 
	 * @author BELBRIK Oualid on : 11 juin 2014  11:26:25
	 * @param volumeHoraireAp the volumeHoraireAp to set
	 */
	public void setVolumeHoraireAp(Double volumeHoraireAp) {
		this.volumeHoraireAp = volumeHoraireAp;
	}



	/**
	 * [AbsenceMatiereDto.numeroMatricule] Getter 
	 * @author BELBRIK Oualid on : 11 juin 2014  11:26:25
	 * @return the numeroMatricule
	 */
	public String getNumeroMatricule() {
		return numeroMatricule;
	}



	/**
	 * [AbsenceMatiereDto.numeroMatricule] Setter 
	 * @author BELBRIK Oualid on : 11 juin 2014  11:26:25
	 * @param numeroMatricule the numeroMatricule to set
	 */
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}



	/**
	 * [AbsenceMatiereDto.anneeAcademiqueId] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}



	/**
	 * [AbsenceMatiereDto.anneeAcademiqueId] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @param anneeAcademiqueId the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}



	/**
	 * [AbsenceMatiereDto.anneeAcademiqueCode] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}



	/**
	 * [AbsenceMatiereDto.anneeAcademiqueCode] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @param anneeAcademiqueCode the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}



	/**
	 * [AbsenceMatiereDto.dossierId] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @return the dossierId
	 */
	public Integer getDossierId() {
		return dossierId;
	}



	/**
	 * [AbsenceMatiereDto.dossierId] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @param dossierId the dossierId to set
	 */
	public void setDossierId(Integer dossierId) {
		this.dossierId = dossierId;
	}



	/**
	 * [AbsenceMatiereDto.dossierEtudiantId] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @return the dossierEtudiantId
	 */
	public Integer getDossierEtudiantId() {
		return dossierEtudiantId;
	}



	/**
	 * [AbsenceMatiereDto.dossierEtudiantId] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @param dossierEtudiantId the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(Integer dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}



	/**
	 * [AbsenceMatiereDto.refCodeDomaine] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @return the refCodeDomaine
	 */
	public String getRefCodeDomaine() {
		return refCodeDomaine;
	}



	/**
	 * [AbsenceMatiereDto.refCodeDomaine] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @param refCodeDomaine the refCodeDomaine to set
	 */
	public void setRefCodeDomaine(String refCodeDomaine) {
		this.refCodeDomaine = refCodeDomaine;
	}



	/**
	 * [AbsenceMatiereDto.refCodeFiliere] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @return the refCodeFiliere
	 */
	public String getRefCodeFiliere() {
		return refCodeFiliere;
	}



	/**
	 * [AbsenceMatiereDto.refCodeFiliere] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @param refCodeFiliere the refCodeFiliere to set
	 */
	public void setRefCodeFiliere(String refCodeFiliere) {
		this.refCodeFiliere = refCodeFiliere;
	}



	/**
	 * [AbsenceMatiereDto.refCodeSpecialite] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @return the refCodeSpecialite
	 */
	public String getRefCodeSpecialite() {
		return refCodeSpecialite;
	}



	/**
	 * [AbsenceMatiereDto.refCodeSpecialite] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @param refCodeSpecialite the refCodeSpecialite to set
	 */
	public void setRefCodeSpecialite(String refCodeSpecialite) {
		this.refCodeSpecialite = refCodeSpecialite;
	}



	/**
	 * [AbsenceMatiereDto.refLibelleDomaine] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @return the refLibelleDomaine
	 */
	public String getRefLibelleDomaine() {
		return refLibelleDomaine;
	}



	/**
	 * [AbsenceMatiereDto.refLibelleDomaine] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @param refLibelleDomaine the refLibelleDomaine to set
	 */
	public void setRefLibelleDomaine(String refLibelleDomaine) {
		this.refLibelleDomaine = refLibelleDomaine;
	}



	/**
	 * [AbsenceMatiereDto.refLibelleFiliere] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @return the refLibelleFiliere
	 */
	public String getRefLibelleFiliere() {
		return refLibelleFiliere;
	}



	/**
	 * [AbsenceMatiereDto.refLibelleFiliere] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @param refLibelleFiliere the refLibelleFiliere to set
	 */
	public void setRefLibelleFiliere(String refLibelleFiliere) {
		this.refLibelleFiliere = refLibelleFiliere;
	}



	/**
	 * [AbsenceMatiereDto.refLibelleSpecialite] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @return the refLibelleSpecialite
	 */
	public String getRefLibelleSpecialite() {
		return refLibelleSpecialite;
	}



	/**
	 * [AbsenceMatiereDto.refLibelleSpecialite] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @param refLibelleSpecialite the refLibelleSpecialite to set
	 */
	public void setRefLibelleSpecialite(String refLibelleSpecialite) {
		this.refLibelleSpecialite = refLibelleSpecialite;
	}


	/**
	 * [AbsenceMatiereDto.refCodeCycle] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @return the refCodeCycle
	 */
	public String getRefCodeCycle() {
		return refCodeCycle;
	}



	/**
	 * [AbsenceMatiereDto.refCodeCycle] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @param refCodeCycle the refCodeCycle to set
	 */
	public void setRefCodeCycle(String refCodeCycle) {
		this.refCodeCycle = refCodeCycle;
	}



	/**
	 * [AbsenceMatiereDto.refCodeNiveau] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @return the refCodeNiveau
	 */
	public String getRefCodeNiveau() {
		return refCodeNiveau;
	}



	/**
	 * [AbsenceMatiereDto.refCodeNiveau] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @param refCodeNiveau the refCodeNiveau to set
	 */
	public void setRefCodeNiveau(String refCodeNiveau) {
		this.refCodeNiveau = refCodeNiveau;
	}



	/**
	 * [AbsenceMatiereDto.refLibelleCycle] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @return the refLibelleCycle
	 */
	public String getRefLibelleCycle() {
		return refLibelleCycle;
	}



	/**
	 * [AbsenceMatiereDto.refLibelleCycle] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @param refLibelleCycle the refLibelleCycle to set
	 */
	public void setRefLibelleCycle(String refLibelleCycle) {
		this.refLibelleCycle = refLibelleCycle;
	}



	/**
	 * [AbsenceMatiereDto.refLibelleNiveau] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @return the refLibelleNiveau
	 */
	public String getRefLibelleNiveau() {
		return refLibelleNiveau;
	}



	/**
	 * [AbsenceMatiereDto.refLibelleNiveau] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @param refLibelleNiveau the refLibelleNiveau to set
	 */
	public void setRefLibelleNiveau(String refLibelleNiveau) {
		this.refLibelleNiveau = refLibelleNiveau;
	}



	/**
	 * [AbsenceMatiereDto.dateInscription] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @return the dateInscription
	 */
	public Date getDateInscription() {
		return dateInscription;
	}



	/**
	 * [AbsenceMatiereDto.dateInscription] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  13:49:27
	 * @param dateInscription the dateInscription to set
	 */
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}



	/**
	 * [AbsenceMatiereDto.dossierInscriptionId] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @return the dossierInscriptionId
	 */
	public Integer getDossierInscriptionId() {
		return dossierInscriptionId;
	}



	/**
	 * [AbsenceMatiereDto.dossierInscriptionId] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @param dossierInscriptionId the dossierInscriptionId to set
	 */
	public void setDossierInscriptionId(Integer dossierInscriptionId) {
		this.dossierInscriptionId = dossierInscriptionId;
	}



	/**
	 * [AbsenceMatiereDto.dossierEtudiantMatricule] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @return the dossierEtudiantMatricule
	 */
	public String getDossierEtudiantMatricule() {
		return dossierEtudiantMatricule;
	}



	/**
	 * [AbsenceMatiereDto.dossierEtudiantMatricule] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @param dossierEtudiantMatricule the dossierEtudiantMatricule to set
	 */
	public void setDossierEtudiantMatricule(String dossierEtudiantMatricule) {
		this.dossierEtudiantMatricule = dossierEtudiantMatricule;
	}



	/**
	 * [AbsenceMatiereDto.refCodeEtabOrigine] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @return the refCodeEtabOrigine
	 */
	public String getRefCodeEtabOrigine() {
		return refCodeEtabOrigine;
	}



	/**
	 * [AbsenceMatiereDto.refCodeEtabOrigine] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @param refCodeEtabOrigine the refCodeEtabOrigine to set
	 */
	public void setRefCodeEtabOrigine(String refCodeEtabOrigine) {
		this.refCodeEtabOrigine = refCodeEtabOrigine;
	}



	/**
	 * [AbsenceMatiereDto.individuId] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @return the individuId
	 */
	public Integer getIndividuId() {
		return individuId;
	}



	/**
	 * [AbsenceMatiereDto.individuId] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @param individuId the individuId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}



	/**
	 * [AbsenceMatiereDto.individuNin] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @return the individuNin
	 */
	public String getIndividuNin() {
		return individuNin;
	}



	/**
	 * [AbsenceMatiereDto.individuNin] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @param individuNin the individuNin to set
	 */
	public void setIndividuNin(String individuNin) {
		this.individuNin = individuNin;
	}



	/**
	 * [AbsenceMatiereDto.individuNomArabe] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @return the individuNomArabe
	 */
	public String getIndividuNomArabe() {
		return individuNomArabe;
	}



	/**
	 * [AbsenceMatiereDto.individuNomArabe] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @param individuNomArabe the individuNomArabe to set
	 */
	public void setIndividuNomArabe(String individuNomArabe) {
		this.individuNomArabe = individuNomArabe;
	}



	/**
	 * [AbsenceMatiereDto.individuNomLatin] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @return the individuNomLatin
	 */
	public String getIndividuNomLatin() {
		return individuNomLatin;
	}



	/**
	 * [AbsenceMatiereDto.individuNomLatin] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @param individuNomLatin the individuNomLatin to set
	 */
	public void setIndividuNomLatin(String individuNomLatin) {
		this.individuNomLatin = individuNomLatin;
	}



	/**
	 * [AbsenceMatiereDto.individuPrenomArabe] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @return the individuPrenomArabe
	 */
	public String getIndividuPrenomArabe() {
		return individuPrenomArabe;
	}



	/**
	 * [AbsenceMatiereDto.individuPrenomArabe] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @param individuPrenomArabe the individuPrenomArabe to set
	 */
	public void setIndividuPrenomArabe(String individuPrenomArabe) {
		this.individuPrenomArabe = individuPrenomArabe;
	}



	/**
	 * [AbsenceMatiereDto.individuPrenomLatin] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @return the individuPrenomLatin
	 */
	public String getIndividuPrenomLatin() {
		return individuPrenomLatin;
	}



	/**
	 * [AbsenceMatiereDto.individuPrenomLatin] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @param individuPrenomLatin the individuPrenomLatin to set
	 */
	public void setIndividuPrenomLatin(String individuPrenomLatin) {
		this.individuPrenomLatin = individuPrenomLatin;
	}



	/**
	 * [AbsenceMatiereDto.individuDateNaissance] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @return the individuDateNaissance
	 */
	public Date getIndividuDateNaissance() {
		return individuDateNaissance;
	}



	/**
	 * [AbsenceMatiereDto.individuDateNaissance] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @param individuDateNaissance the individuDateNaissance to set
	 */
	public void setIndividuDateNaissance(Date individuDateNaissance) {
		this.individuDateNaissance = individuDateNaissance;
	}



	/**
	 * [AbsenceMatiereDto.individuNationaliteLibelleLongFr] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @return the individuNationaliteLibelleLongFr
	 */
	public String getIndividuNationaliteLibelleLongFr() {
		return individuNationaliteLibelleLongFr;
	}



	/**
	 * [AbsenceMatiereDto.individuNationaliteLibelleLongFr] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @param individuNationaliteLibelleLongFr the individuNationaliteLibelleLongFr to set
	 */
	public void setIndividuNationaliteLibelleLongFr(
			String individuNationaliteLibelleLongFr) {
		this.individuNationaliteLibelleLongFr = individuNationaliteLibelleLongFr;
	}



	/**
	 * [AbsenceMatiereDto.individuNationaliteLibelleLongAr] Getter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @return the individuNationaliteLibelleLongAr
	 */
	public String getIndividuNationaliteLibelleLongAr() {
		return individuNationaliteLibelleLongAr;
	}



	/**
	 * [AbsenceMatiereDto.individuNationaliteLibelleLongAr] Setter 
	 * @author BELBRIK Oualid on : 12 juin 2014  19:20:43
	 * @param individuNationaliteLibelleLongAr the individuNationaliteLibelleLongAr to set
	 */
	public void setIndividuNationaliteLibelleLongAr(
			String individuNationaliteLibelleLongAr) {
		this.individuNationaliteLibelleLongAr = individuNationaliteLibelleLongAr;
	}



	/**
	 * [AbsenceMatiereDto.motif] Getter 
	 * @author BELBRIK Oualid on : 15 juin 2014  17:15:52
	 * @return the motif
	 */
	public String getMotif() {
		return motif;
	}



	/**
	 * [AbsenceMatiereDto.motif] Setter 
	 * @author BELBRIK Oualid on : 15 juin 2014  17:15:52
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}



}
