package dz.gov.mesrs.sii.fve.business.model.dto.cursus;



import java.util.Date;
import java.util.List;

/**
 * @author BELDI Jamel  on : 14 juil. 2014  15:21:39
 */

public class ParcoursIndividualiseDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 14 juil. 2014  15:20:45
	 */
	private static final long serialVersionUID = 1L;
	private Integer id; 
    private Integer dossierInscriptionAdministrativeId;
    private String numeroInscription;
	private Integer anneeAcademiqueId;
	private String anneeAcademiqueCode;
	private Integer dossierId;
	private Integer dossierEtudiantId;
	private String numeroMatricule;
	private String refCodeDomaine;
	private String refCodeFiliere;
	private String refCodeSpecialite;
	private String resultRefCodeDomaine;
	private String resultRefCodeFiliere;
	private String resultRefCodeSpecialite;
	private String refLibelleDomaine;
	private String refLibelleFiliere;
	private String refLibelleSpecialite;
	private Integer ouvertureOffreFormationId;
	private Integer offreFormationId;
	private String offreFormationCode;
	private String offreFormationLibelleFr;
	private String offreFormationLibelleAr;	
	// Etudiant Individu
	private String nin;
	private String individuNomArabe;
	private String individuNomLatin;
	private String individuPrenomArabe;
	private String individuPrenomLatin;
	//Situation
	private Integer situationId;
	private String situationLibelleFr;
	private String situationLibelleAr;
//	private SituationEntite situationEntite;
	private Date dateCreation;
	private String refCodeEtablissement;
	//Periode
	private Integer idPeriode;
	private String codePeriode;
	private String libellePeriode;
	
	
List<ParcoursIndividualiseMatiereDto> parcoursIndividualiseMatiereList;
	public ParcoursIndividualiseDto() {
	}

	
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	
	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}



	/**
	 * [ParcoursIndividualiseDto.dossierInscriptionAdministrativeId] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  12:17:06
	 * @return the dossierInscriptionAdministrativeId
	 */
	public Integer getDossierInscriptionAdministrativeId() {
		return dossierInscriptionAdministrativeId;
	}



	/**
	 * [ParcoursIndividualiseDto.dossierInscriptionAdministrativeId] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  12:17:06
	 * @param dossierInscriptionAdministrativeId the dossierInscriptionAdministrativeId to set
	 */
	public void setDossierInscriptionAdministrativeId(
			Integer dossierInscriptionAdministrativeId) {
		this.dossierInscriptionAdministrativeId = dossierInscriptionAdministrativeId;
	}



	/**
	 * [ParcoursIndividualiseDto.situationId] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  12:17:06
	 * @return the situationId
	 */
	public Integer getSituationId() {
		return situationId;
	}



	/**
	 * [ParcoursIndividualiseDto.situationId] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  12:17:06
	 * @param situationId the situationId to set
	 */
	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}



	/**
	 * [ParcoursIndividualiseDto.situationLibelleFr] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  12:17:06
	 * @return the situationLibelleFr
	 */
	public String getSituationLibelleFr() {
		return situationLibelleFr;
	}



	/**
	 * [ParcoursIndividualiseDto.situationLibelleFr] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  12:17:06
	 * @param situationLibelleFr the situationLibelleFr to set
	 */
	public void setSituationLibelleFr(String situationLibelleFr) {
		this.situationLibelleFr = situationLibelleFr;
	}



	/**
	 * [ParcoursIndividualiseDto.situationLibelleAr] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  12:17:06
	 * @return the situationLibelleAr
	 */
	public String getSituationLibelleAr() {
		return situationLibelleAr;
	}



	/**
	 * [ParcoursIndividualiseDto.situationLibelleAr] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  12:17:06
	 * @param situationLibelleAr the situationLibelleAr to set
	 */
	public void setSituationLibelleAr(String situationLibelleAr) {
		this.situationLibelleAr = situationLibelleAr;
	}



	/**
	 * [ParcoursIndividualiseDto.numeroInscription] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the numeroInscription
	 */
	public String getNumeroInscription() {
		return numeroInscription;
	}



	/**
	 * [ParcoursIndividualiseDto.numeroInscription] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param numeroInscription the numeroInscription to set
	 */
	public void setNumeroInscription(String numeroInscription) {
		this.numeroInscription = numeroInscription;
	}



	/**
	 * [ParcoursIndividualiseDto.anneeAcademiqueId] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}



	/**
	 * [ParcoursIndividualiseDto.anneeAcademiqueId] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param anneeAcademiqueId the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}



	/**
	 * [ParcoursIndividualiseDto.anneeAcademiqueCode] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}



	/**
	 * [ParcoursIndividualiseDto.anneeAcademiqueCode] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param anneeAcademiqueCode the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}



	/**
	 * [ParcoursIndividualiseDto.dossierId] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the dossierId
	 */
	public Integer getDossierId() {
		return dossierId;
	}



	/**
	 * [ParcoursIndividualiseDto.dossierId] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param dossierId the dossierId to set
	 */
	public void setDossierId(Integer dossierId) {
		this.dossierId = dossierId;
	}



	/**
	 * [ParcoursIndividualiseDto.dossierEtudiantId] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the dossierEtudiantId
	 */
	public Integer getDossierEtudiantId() {
		return dossierEtudiantId;
	}



	/**
	 * [ParcoursIndividualiseDto.dossierEtudiantId] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param dossierEtudiantId the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(Integer dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}



	/**
	 * [ParcoursIndividualiseDto.numeroMatricule] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the numeroMatricule
	 */
	public String getNumeroMatricule() {
		return numeroMatricule;
	}



	/**
	 * [ParcoursIndividualiseDto.numeroMatricule] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param numeroMatricule the numeroMatricule to set
	 */
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}



	/**
	 * [ParcoursIndividualiseDto.refCodeDomaine] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the refCodeDomaine
	 */
	public String getRefCodeDomaine() {
		return refCodeDomaine;
	}



	/**
	 * [ParcoursIndividualiseDto.refCodeDomaine] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param refCodeDomaine the refCodeDomaine to set
	 */
	public void setRefCodeDomaine(String refCodeDomaine) {
		this.refCodeDomaine = refCodeDomaine;
	}



	/**
	 * [ParcoursIndividualiseDto.refCodeFiliere] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the refCodeFiliere
	 */
	public String getRefCodeFiliere() {
		return refCodeFiliere;
	}



	/**
	 * [ParcoursIndividualiseDto.refCodeFiliere] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param refCodeFiliere the refCodeFiliere to set
	 */
	public void setRefCodeFiliere(String refCodeFiliere) {
		this.refCodeFiliere = refCodeFiliere;
	}



	/**
	 * [ParcoursIndividualiseDto.refCodeSpecialite] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the refCodeSpecialite
	 */
	public String getRefCodeSpecialite() {
		return refCodeSpecialite;
	}



	/**
	 * [ParcoursIndividualiseDto.refCodeSpecialite] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param refCodeSpecialite the refCodeSpecialite to set
	 */
	public void setRefCodeSpecialite(String refCodeSpecialite) {
		this.refCodeSpecialite = refCodeSpecialite;
	}



	/**
	 * [ParcoursIndividualiseDto.resultRefCodeDomaine] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the resultRefCodeDomaine
	 */
	public String getResultRefCodeDomaine() {
		return resultRefCodeDomaine;
	}



	/**
	 * [ParcoursIndividualiseDto.resultRefCodeDomaine] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param resultRefCodeDomaine the resultRefCodeDomaine to set
	 */
	public void setResultRefCodeDomaine(String resultRefCodeDomaine) {
		this.resultRefCodeDomaine = resultRefCodeDomaine;
	}



	/**
	 * [ParcoursIndividualiseDto.resultRefCodeFiliere] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the resultRefCodeFiliere
	 */
	public String getResultRefCodeFiliere() {
		return resultRefCodeFiliere;
	}



	/**
	 * [ParcoursIndividualiseDto.resultRefCodeFiliere] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param resultRefCodeFiliere the resultRefCodeFiliere to set
	 */
	public void setResultRefCodeFiliere(String resultRefCodeFiliere) {
		this.resultRefCodeFiliere = resultRefCodeFiliere;
	}



	/**
	 * [ParcoursIndividualiseDto.resultRefCodeSpecialite] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the resultRefCodeSpecialite
	 */
	public String getResultRefCodeSpecialite() {
		return resultRefCodeSpecialite;
	}



	/**
	 * [ParcoursIndividualiseDto.resultRefCodeSpecialite] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param resultRefCodeSpecialite the resultRefCodeSpecialite to set
	 */
	public void setResultRefCodeSpecialite(String resultRefCodeSpecialite) {
		this.resultRefCodeSpecialite = resultRefCodeSpecialite;
	}



	/**
	 * [ParcoursIndividualiseDto.refLibelleDomaine] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the refLibelleDomaine
	 */
	public String getRefLibelleDomaine() {
		return refLibelleDomaine;
	}



	/**
	 * [ParcoursIndividualiseDto.refLibelleDomaine] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param refLibelleDomaine the refLibelleDomaine to set
	 */
	public void setRefLibelleDomaine(String refLibelleDomaine) {
		this.refLibelleDomaine = refLibelleDomaine;
	}



	/**
	 * [ParcoursIndividualiseDto.refLibelleFiliere] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the refLibelleFiliere
	 */
	public String getRefLibelleFiliere() {
		return refLibelleFiliere;
	}



	/**
	 * [ParcoursIndividualiseDto.refLibelleFiliere] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param refLibelleFiliere the refLibelleFiliere to set
	 */
	public void setRefLibelleFiliere(String refLibelleFiliere) {
		this.refLibelleFiliere = refLibelleFiliere;
	}



	/**
	 * [ParcoursIndividualiseDto.refLibelleSpecialite] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the refLibelleSpecialite
	 */
	public String getRefLibelleSpecialite() {
		return refLibelleSpecialite;
	}



	/**
	 * [ParcoursIndividualiseDto.refLibelleSpecialite] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param refLibelleSpecialite the refLibelleSpecialite to set
	 */
	public void setRefLibelleSpecialite(String refLibelleSpecialite) {
		this.refLibelleSpecialite = refLibelleSpecialite;
	}



	/**
	 * [ParcoursIndividualiseDto.ouvertureOffreFormationId] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the ouvertureOffreFormationId
	 */
	public Integer getOuvertureOffreFormationId() {
		return ouvertureOffreFormationId;
	}



	/**
	 * [ParcoursIndividualiseDto.ouvertureOffreFormationId] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param ouvertureOffreFormationId the ouvertureOffreFormationId to set
	 */
	public void setOuvertureOffreFormationId(Integer ouvertureOffreFormationId) {
		this.ouvertureOffreFormationId = ouvertureOffreFormationId;
	}



	/**
	 * [ParcoursIndividualiseDto.offreFormationId] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the offreFormationId
	 */
	public Integer getOffreFormationId() {
		return offreFormationId;
	}



	/**
	 * [ParcoursIndividualiseDto.offreFormationId] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param offreFormationId the offreFormationId to set
	 */
	public void setOffreFormationId(Integer offreFormationId) {
		this.offreFormationId = offreFormationId;
	}



	/**
	 * [ParcoursIndividualiseDto.offreFormationCode] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the offreFormationCode
	 */
	public String getOffreFormationCode() {
		return offreFormationCode;
	}



	/**
	 * [ParcoursIndividualiseDto.offreFormationCode] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param offreFormationCode the offreFormationCode to set
	 */
	public void setOffreFormationCode(String offreFormationCode) {
		this.offreFormationCode = offreFormationCode;
	}



	/**
	 * [ParcoursIndividualiseDto.offreFormationLibelleFr] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the offreFormationLibelleFr
	 */
	public String getOffreFormationLibelleFr() {
		return offreFormationLibelleFr;
	}



	/**
	 * [ParcoursIndividualiseDto.offreFormationLibelleFr] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param offreFormationLibelleFr the offreFormationLibelleFr to set
	 */
	public void setOffreFormationLibelleFr(String offreFormationLibelleFr) {
		this.offreFormationLibelleFr = offreFormationLibelleFr;
	}



	/**
	 * [ParcoursIndividualiseDto.offreFormationLibelleAr] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the offreFormationLibelleAr
	 */
	public String getOffreFormationLibelleAr() {
		return offreFormationLibelleAr;
	}



	/**
	 * [ParcoursIndividualiseDto.offreFormationLibelleAr] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param offreFormationLibelleAr the offreFormationLibelleAr to set
	 */
	public void setOffreFormationLibelleAr(String offreFormationLibelleAr) {
		this.offreFormationLibelleAr = offreFormationLibelleAr;
	}



	/**
	 * [ParcoursIndividualiseDto.nin] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the nin
	 */
	public String getNin() {
		return nin;
	}



	/**
	 * [ParcoursIndividualiseDto.nin] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param nin the nin to set
	 */
	public void setNin(String nin) {
		this.nin = nin;
	}



	/**
	 * [ParcoursIndividualiseDto.individuNomArabe] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the individuNomArabe
	 */
	public String getIndividuNomArabe() {
		return individuNomArabe;
	}



	/**
	 * [ParcoursIndividualiseDto.individuNomArabe] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param individuNomArabe the individuNomArabe to set
	 */
	public void setIndividuNomArabe(String individuNomArabe) {
		this.individuNomArabe = individuNomArabe;
	}



	/**
	 * [ParcoursIndividualiseDto.individuNomLatin] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the individuNomLatin
	 */
	public String getIndividuNomLatin() {
		return individuNomLatin;
	}



	/**
	 * [ParcoursIndividualiseDto.individuNomLatin] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param individuNomLatin the individuNomLatin to set
	 */
	public void setIndividuNomLatin(String individuNomLatin) {
		this.individuNomLatin = individuNomLatin;
	}



	/**
	 * [ParcoursIndividualiseDto.individuPrenomArabe] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the individuPrenomArabe
	 */
	public String getIndividuPrenomArabe() {
		return individuPrenomArabe;
	}



	/**
	 * [ParcoursIndividualiseDto.individuPrenomArabe] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param individuPrenomArabe the individuPrenomArabe to set
	 */
	public void setIndividuPrenomArabe(String individuPrenomArabe) {
		this.individuPrenomArabe = individuPrenomArabe;
	}



	/**
	 * [ParcoursIndividualiseDto.individuPrenomLatin] Getter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @return the individuPrenomLatin
	 */
	public String getIndividuPrenomLatin() {
		return individuPrenomLatin;
	}



	/**
	 * [ParcoursIndividualiseDto.individuPrenomLatin] Setter 
	 * @author BELDI Jamel on : 10 aout 2014  17:55:54
	 * @param individuPrenomLatin the individuPrenomLatin to set
	 */
	public void setIndividuPrenomLatin(String individuPrenomLatin) {
		this.individuPrenomLatin = individuPrenomLatin;
	}



	/**
	 * [ParcoursIndividualiseDto.refCodeEtablissement] Getter 
	 * @author BELDI Jamel on : 13 aout 2014  10:27:12
	 * @return the refCodeEtablissement
	 */
	public String getRefCodeEtablissement() {
		return refCodeEtablissement;
	}



	/**
	 * [ParcoursIndividualiseDto.refCodeEtablissement] Setter 
	 * @author BELDI Jamel on : 13 aout 2014  10:27:13
	 * @param refCodeEtablissement the refCodeEtablissement to set
	 */
	public void setRefCodeEtablissement(String refCodeEtablissement) {
		this.refCodeEtablissement = refCodeEtablissement;
	}



	/**
	 * [ParcoursIndividualiseDto.parcoursIndividualiseMatiereList] Getter 
	 * @author BELDI Jamel on : 14 aout 2014  14:14:03
	 * @return the parcoursIndividualiseMatiereList
	 */
	public List<ParcoursIndividualiseMatiereDto> getParcoursIndividualiseMatiereList() {
		return parcoursIndividualiseMatiereList;
	}



	/**
	 * [ParcoursIndividualiseDto.parcoursIndividualiseMatiereList] Setter 
	 * @author BELDI Jamel on : 14 aout 2014  14:14:03
	 * @param parcoursIndividualiseMatiereList the parcoursIndividualiseMatiereList to set
	 */
	public void setParcoursIndividualiseMatiereList(
			List<ParcoursIndividualiseMatiereDto> parcoursIndividualiseMatiereList) {
		this.parcoursIndividualiseMatiereList = parcoursIndividualiseMatiereList;
	}



	/**
	 * [ParcoursIndividualiseDto.idPeriode] Getter 
	 * @author BELDI Jamel on : 7 sept. 2014  17:28:27
	 * @return the idPeriode
	 */
	public Integer getIdPeriode() {
		return idPeriode;
	}



	/**
	 * [ParcoursIndividualiseDto.idPeriode] Setter 
	 * @author BELDI Jamel on : 7 sept. 2014  17:28:27
	 * @param idPeriode the idPeriode to set
	 */
	public void setIdPeriode(Integer idPeriode) {
		this.idPeriode = idPeriode;
	}



	/**
	 * [ParcoursIndividualiseDto.codePeriode] Getter 
	 * @author BELDI Jamel on : 7 sept. 2014  17:28:27
	 * @return the codePeriode
	 */
	public String getCodePeriode() {
		return codePeriode;
	}



	/**
	 * [ParcoursIndividualiseDto.codePeriode] Setter 
	 * @author BELDI Jamel on : 7 sept. 2014  17:28:27
	 * @param codePeriode the codePeriode to set
	 */
	public void setCodePeriode(String codePeriode) {
		this.codePeriode = codePeriode;
	}



	/**
	 * [ParcoursIndividualiseDto.libellePeriode] Getter 
	 * @author BELDI Jamel on : 7 sept. 2014  17:28:27
	 * @return the libellePeriode
	 */
	public String getLibellePeriode() {
		return libellePeriode;
	}



	/**
	 * [ParcoursIndividualiseDto.libellePeriode] Setter 
	 * @author BELDI Jamel on : 7 sept. 2014  17:28:27
	 * @param libellePeriode the libellePeriode to set
	 */
	public void setLibellePeriode(String libellePeriode) {
		this.libellePeriode = libellePeriode;
	}

	

}
