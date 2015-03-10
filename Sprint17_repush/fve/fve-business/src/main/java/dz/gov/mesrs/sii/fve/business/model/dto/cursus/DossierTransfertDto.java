package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;

/**
 * @author BELDI Jamel on : 8 juin 2014 12:27:09
 */
public class DossierTransfertDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:26:56
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;

	private String objet;
	private Date dateDepot;
	
	private String refCodeMotif;
	
	private String refCodeMotifLibelleFr;
	private String refCodeMotifLibelleAr;

	// private DossierInscriptionAdministrative
	// dossierInscriptionAdministrative;
	private Integer dossierInscriptionId;
	private Integer dossierEtudiantId;
	private String dossierEtudiantMatricule;

	private String refCodeEtabOrigine;
	private String refLibelleEtabOrigine;

	// Etablissement Accueil
	private String refCodeEtabAccueil;
	private String refLibelleEtabAccueil;

	private String refCodeDomaineOrigine;
	private String domaineOrigineLibelleFr;
	private String domaineOrigineLibelleAr;

	private String refCodeFiliereOrigine;
	private String filiereOrigineLibelleFr;
	private String filiereOrigineLibelleAr;

	private String refCodeDomaineDemande;
	private String domaineDemandeLibelleFr;
	private String domaineDemandeLibelleAr;

	private String refCodeFiliereDemandee;
	private String filiereDemandeeLibelleFr;
	private String filiereDemandeeLibelleAr;

	private Integer ouvertureOfOrigineId;

	private String ofOrigineLibelleFr;
	private String ofOrigineLibelleFiliereFr;
	private String ofOrigineLibelleAr;
	private String ofOrigineLibelleFiliereAr;

	// Individu
	private Integer individuId;
	private String individuNin;
	private String individuNomArabe;
	private String individuNomLatin;
	private String individuPrenomArabe;
	private String individuPrenomLatin;
	private Date individuDateNaissance;
	private String individuNationaliteLibelleLongFr;
	private String individuNationaliteLibelleLongAr;
	private String individuEmail;
	private String individuCivility;


	// BAC
	private Integer bacId;
	private String bacMatricule;
	private String bacRefCodeSerie;
	private String bacLibelleSerie;
	private Double moyenneBac;
	
	private String individuRefCodeWilaya;
	private String individuRefCodeWilayaLibelleLongFr;
	private String individuRefCodeWilayaLibelleLongAr;

	// TYPE TRANSFERT
	private String refCodeTypeTransfert;
	private String refLibelleTransfert;

	// private Dossier dossier;
	private String typeDossier;
	private Date dateCreation;

	// private AnneeAcademique anneeAcademique;
	private Integer anneeAcademiqueId;
	private Short anneeAcademiquePremiereAnnee;
	private Short anneeAcademiqueDeuxiemeAnnee;
	private String anneeAcademiqueCode;

	// private OuvertureOffreFormation ouvertureOffreFormationDemandee;
	private Integer ouvertureOfId;
	private String ofLibelleFr;
	private String ofLibelleFiliereFr;
	private String ofLibelleAr;
	private String ofLibelleFiliereAr;

	// AVIS ETABLISSEMENT ORIGINE
	private Boolean isEtabOrigineAccepte;
	private String isEtabOrigineAccepteLibelleFr;
	private String isEtabOrigineAccepteLibelleAr;

	private String refCodeMotifEtabOrigine;
	private Date dateAvisEtabOrigine;
	private String refLibelleAvisEtabOrigine;
	private String refLibelleMotifEtabOrigine;

	// AVIS ETABLISSEMENT ACCUEIL
	private Boolean isEtabAccueilAccepte;
	private String isEtabAccueilAccepteLibelleFr;
	private String isEtabAccueilAccepteLibelleAr;

	private String refCodeMotifEtabAccueil;
	private Date dateAvisEtabAccueil;
	private String refLibelleAvisEtabAccueil;
	private String refLibelleMotifEtabAccueil;

	// Avis final
	private String refLibelleAvisFinal;

	// Situation
	private Integer situationId;
	private String codeSituation;
	private String situationLibelleFr;
	private String situationLibelleAr;

	private Boolean casExceptionnel;
	private String casExceptionnelLieblleFr;
	private String casExceptionnelLieblleAr;

	private String refCodeMotifCasExceptionnel;
	private String refCodeMotifCasExceptionnelLieblleFr;
	private String refCodeMotifCasExceptionnelLieblleAr;

	private String observationEtabAccueil;
	private String observationEtabOrigine;
	private String observationCommission;

	private Boolean isAccordee;
	private String isAccordeeLieblleFr;
	private String isAccordeeLieblleAr;

	private Boolean nouveauBachelier;
	private String nouveauAncienBachelierLiebleFr;
	private String nouveauAncienBachelierLiebleAr;
	
	
	// Historique des situations
	private List<SituationEntiteOccurrenceDto> dossierTransfertHistory;

	/**
     *
     */
	public DossierTransfertDto() {
	}

	/**
	 * [DossierTransfertDto.id] Getter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:19
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [DossierTransfertDto.id] Setter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:19
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [DossierTransfertDto.objet] Getter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:19
	 * @return the objet
	 */
	public String getObjet() {
		return objet;
	}

	/**
	 * [DossierTransfertDto.objet] Setter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:19
	 * @param objet
	 *            the objet to set
	 */
	public void setObjet(String objet) {
		this.objet = objet;
	}

	/**
	 * [DossierTransfertDto.dateDepot] Getter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:19
	 * @return the dateDepot
	 */
	public Date getDateDepot() {
		return dateDepot;
	}

	/**
	 * [DossierTransfertDto.dateDepot] Setter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:19
	 * @param dateDepot
	 *            the dateDepot to set
	 */
	public void setDateDepot(Date dateDepot) {
		this.dateDepot = dateDepot;
	}

	/**
	 * [DossierTransfertDto.refCodeEtabAccueil] Getter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:19
	 * @return the refCodeEtabAccueil
	 */
	public String getRefCodeEtabAccueil() {
		return refCodeEtabAccueil;
	}

	/**
	 * [DossierTransfertDto.refCodeEtabAccueil] Setter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:19
	 * @param refCodeEtabAccueil
	 *            the refCodeEtabAccueil to set
	 */
	public void setRefCodeEtabAccueil(String refCodeEtabAccueil) {
		this.refCodeEtabAccueil = refCodeEtabAccueil;
	}

	/**
	 * [DossierTransfertDto.refCodeMotifEtabOrigine] Getter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:20
	 * @return the refCodeMotifEtabOrigine
	 */
	public String getRefCodeMotifEtabOrigine() {
		return refCodeMotifEtabOrigine;
	}

	/**
	 * [DossierTransfertDto.refCodeMotifEtabOrigine] Setter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:20
	 * @param refCodeMotifEtabOrigine
	 *            the refCodeMotifEtabOrigine to set
	 */
	public void setRefCodeMotifEtabOrigine(String refCodeMotifEtabOrigine) {
		this.refCodeMotifEtabOrigine = refCodeMotifEtabOrigine;
	}

	/**
	 * [DossierTransfertDto.refCodeMotifEtabAccueil] Getter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:20
	 * @return the refCodeMotifEtabAccueil
	 */
	public String getRefCodeMotifEtabAccueil() {
		return refCodeMotifEtabAccueil;
	}

	/**
	 * [DossierTransfertDto.refCodeMotifEtabAccueil] Setter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:20
	 * @param refCodeMotifEtabAccueil
	 *            the refCodeMotifEtabAccueil to set
	 */
	public void setRefCodeMotifEtabAccueil(String refCodeMotifEtabAccueil) {
		this.refCodeMotifEtabAccueil = refCodeMotifEtabAccueil;
	}

	/**
	 * [DossierTransfertDto.dateAvisEtabOrigine] Getter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:20
	 * @return the dateAvisEtabOrigine
	 */
	public Date getDateAvisEtabOrigine() {
		return dateAvisEtabOrigine;
	}

	/**
	 * [DossierTransfertDto.dateAvisEtabOrigine] Setter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:20
	 * @param dateAvisEtabOrigine
	 *            the dateAvisEtabOrigine to set
	 */
	public void setDateAvisEtabOrigine(Date dateAvisEtabOrigine) {
		this.dateAvisEtabOrigine = dateAvisEtabOrigine;
	}

	/**
	 * [DossierTransfertDto.dateAvisEtabAccueil] Getter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:20
	 * @return the dateAvisEtabAccueil
	 */
	public Date getDateAvisEtabAccueil() {
		return dateAvisEtabAccueil;
	}

	/**
	 * [DossierTransfertDto.dateAvisEtabAccueil] Setter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:32:20
	 * @param dateAvisEtabAccueil
	 *            the dateAvisEtabAccueil to set
	 */
	public void setDateAvisEtabAccueil(Date dateAvisEtabAccueil) {
		this.dateAvisEtabAccueil = dateAvisEtabAccueil;
	}

	/**
	 * [DossierTransfertDto.refCodeTypeTransfert] Getter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 16:58:08
	 * @return the refCodeTypeTransfert
	 */
	public String getRefCodeTypeTransfert() {
		return refCodeTypeTransfert;
	}

	/**
	 * [DossierTransfertDto.refCodeTypeTransfert] Setter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 16:58:08
	 * @param refCodeTypeTransfert
	 *            the refCodeTypeTransfert to set
	 */
	public void setRefCodeTypeTransfert(String refCodeTypeTransfert) {
		this.refCodeTypeTransfert = refCodeTypeTransfert;
	}

	/**
	 * [DossierTransfertDto.dossierInscriptionId] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the dossierInscriptionId
	 */
	public Integer getDossierInscriptionId() {
		return dossierInscriptionId;
	}

	/**
	 * [DossierTransfertDto.dossierInscriptionId] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param dossierInscriptionId
	 *            the dossierInscriptionId to set
	 */
	public void setDossierInscriptionId(Integer dossierInscriptionId) {
		this.dossierInscriptionId = dossierInscriptionId;
	}

	/**
	 * [DossierTransfertDto.dossierEtudiantId] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the dossierEtudiantId
	 */
	public Integer getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	public Boolean getIsEtabOrigineAccepte() {
		return isEtabOrigineAccepte;
	}

	public void setIsEtabOrigineAccepte(Boolean isEtabOrigineAccepte) {
		this.isEtabOrigineAccepte = isEtabOrigineAccepte;
	}

	public Boolean getIsEtabAccueilAccepte() {
		return isEtabAccueilAccepte;
	}

	public void setIsEtabAccueilAccepte(Boolean isEtabAccueilAccepte) {
		this.isEtabAccueilAccepte = isEtabAccueilAccepte;
	}

	/**
	 * [DossierTransfertDto.dossierEtudiantId] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param dossierEtudiantId
	 *            the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(Integer dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	/**
	 * [DossierTransfertDto.dossierEtudiantMatricule] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the dossierEtudiantMatricule
	 */
	public String getDossierEtudiantMatricule() {
		return dossierEtudiantMatricule;
	}

	/**
	 * [DossierTransfertDto.dossierEtudiantMatricule] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param dossierEtudiantMatricule
	 *            the dossierEtudiantMatricule to set
	 */
	public void setDossierEtudiantMatricule(String dossierEtudiantMatricule) {
		this.dossierEtudiantMatricule = dossierEtudiantMatricule;
	}

	/**
	 * [DossierTransfertDto.refLibelleEtabOrigine] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the refLibelleEtabOrigine
	 */
	public String getRefLibelleEtabOrigine() {
		return refLibelleEtabOrigine;
	}

	/**
	 * [DossierTransfertDto.refLibelleEtabOrigine] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param refLibelleEtabOrigine
	 *            the refLibelleEtabOrigine to set
	 */
	public void setRefLibelleEtabOrigine(String refLibelleEtabOrigine) {
		this.refLibelleEtabOrigine = refLibelleEtabOrigine;
	}

	/**
	 * [DossierTransfertDto.ouvertureOfOrigineId] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the ouvertureOfOrigineId
	 */
	public Integer getOuvertureOfOrigineId() {
		return ouvertureOfOrigineId;
	}

	/**
	 * [DossierTransfertDto.ouvertureOfOrigineId] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param ouvertureOfOrigineId
	 *            the ouvertureOfOrigineId to set
	 */
	public void setOuvertureOfOrigineId(Integer ouvertureOfOrigineId) {
		this.ouvertureOfOrigineId = ouvertureOfOrigineId;
	}

	/**
	 * [DossierTransfertDto.ofOrigineLibelleFr] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the ofOrigineLibelleFr
	 */
	public String getOfOrigineLibelleFr() {
		return ofOrigineLibelleFr;
	}

	/**
	 * [DossierTransfertDto.ofOrigineLibelleFr] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param ofOrigineLibelleFr
	 *            the ofOrigineLibelleFr to set
	 */
	public void setOfOrigineLibelleFr(String ofOrigineLibelleFr) {
		this.ofOrigineLibelleFr = ofOrigineLibelleFr;
	}

	/**
	 * [DossierTransfertDto.ofOrigineLibelleFiliereFr] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the ofOrigineLibelleFiliereFr
	 */
	public String getOfOrigineLibelleFiliereFr() {
		return ofOrigineLibelleFiliereFr;
	}

	/**
	 * [DossierTransfertDto.ofOrigineLibelleFiliereFr] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param ofOrigineLibelleFiliereFr
	 *            the ofOrigineLibelleFiliereFr to set
	 */
	public void setOfOrigineLibelleFiliereFr(String ofOrigineLibelleFiliereFr) {
		this.ofOrigineLibelleFiliereFr = ofOrigineLibelleFiliereFr;
	}

	/**
	 * [DossierTransfertDto.ofOrigineLibelleAr] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the ofOrigineLibelleAr
	 */
	public String getOfOrigineLibelleAr() {
		return ofOrigineLibelleAr;
	}

	/**
	 * [DossierTransfertDto.ofOrigineLibelleAr] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param ofOrigineLibelleAr
	 *            the ofOrigineLibelleAr to set
	 */
	public void setOfOrigineLibelleAr(String ofOrigineLibelleAr) {
		this.ofOrigineLibelleAr = ofOrigineLibelleAr;
	}

	/**
	 * [DossierTransfertDto.ofOrigineLibelleFiliereAr] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the ofOrigineLibelleFiliereAr
	 */
	public String getOfOrigineLibelleFiliereAr() {
		return ofOrigineLibelleFiliereAr;
	}

	/**
	 * [DossierTransfertDto.ofOrigineLibelleFiliereAr] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param ofOrigineLibelleFiliereAr
	 *            the ofOrigineLibelleFiliereAr to set
	 */
	public void setOfOrigineLibelleFiliereAr(String ofOrigineLibelleFiliereAr) {
		this.ofOrigineLibelleFiliereAr = ofOrigineLibelleFiliereAr;
	}

	/**
	 * [DossierTransfertDto.individuId] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the individuId
	 */
	public Integer getIndividuId() {
		return individuId;
	}

	/**
	 * [DossierTransfertDto.individuId] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param individuId
	 *            the individuId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}

	/**
	 * [DossierTransfertDto.individuNin] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:48:12
	 * @return the individuNin
	 */
	public String getIndividuNin() {
		return individuNin;
	}

	/**
	 * [DossierTransfertDto.individuNin] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:48:12
	 * @param individuNin
	 *            the individuNin to set
	 */
	public void setIndividuNin(String individuNin) {
		this.individuNin = individuNin;
	}

	/**
	 * [DossierTransfertDto.individuNomArabe] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the individuNomArabe
	 */
	public String getIndividuNomArabe() {
		return individuNomArabe;
	}

	/**
	 * [DossierTransfertDto.individuNomArabe] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param individuNomArabe
	 *            the individuNomArabe to set
	 */
	public void setIndividuNomArabe(String individuNomArabe) {
		this.individuNomArabe = individuNomArabe;
	}

	/**
	 * [DossierTransfertDto.individuNomLatin] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the individuNomLatin
	 */
	public String getIndividuNomLatin() {
		return individuNomLatin;
	}

	/**
	 * [DossierTransfertDto.individuNomLatin] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param individuNomLatin
	 *            the individuNomLatin to set
	 */
	public void setIndividuNomLatin(String individuNomLatin) {
		this.individuNomLatin = individuNomLatin;
	}

	/**
	 * [DossierTransfertDto.individuPrenomArabe] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the individuPrenomArabe
	 */
	public String getIndividuPrenomArabe() {
		return individuPrenomArabe;
	}

	/**
	 * [DossierTransfertDto.individuPrenomArabe] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param individuPrenomArabe
	 *            the individuPrenomArabe to set
	 */
	public void setIndividuPrenomArabe(String individuPrenomArabe) {
		this.individuPrenomArabe = individuPrenomArabe;
	}

	/**
	 * [DossierTransfertDto.individuPrenomLatin] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the individuPrenomLatin
	 */
	public String getIndividuPrenomLatin() {
		return individuPrenomLatin;
	}

	/**
	 * [DossierTransfertDto.individuPrenomLatin] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param individuPrenomLatin
	 *            the individuPrenomLatin to set
	 */
	public void setIndividuPrenomLatin(String individuPrenomLatin) {
		this.individuPrenomLatin = individuPrenomLatin;
	}

	/**
	 * [DossierTransfertDto.individuDateNaissance] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the individuDateNaissance
	 */
	public Date getIndividuDateNaissance() {
		return individuDateNaissance;
	}

	/**
	 * [DossierTransfertDto.individuDateNaissance] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param individuDateNaissance
	 *            the individuDateNaissance to set
	 */
	public void setIndividuDateNaissance(Date individuDateNaissance) {
		this.individuDateNaissance = individuDateNaissance;
	}

	/**
	 * [DossierTransfertDto.individuNationaliteLibelleLongFr] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the individuNationaliteLibelleLongFr
	 */
	public String getIndividuNationaliteLibelleLongFr() {
		return individuNationaliteLibelleLongFr;
	}

	/**
	 * [DossierTransfertDto.individuNationaliteLibelleLongFr] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param individuNationaliteLibelleLongFr
	 *            the individuNationaliteLibelleLongFr to set
	 */
	public void setIndividuNationaliteLibelleLongFr(
			String individuNationaliteLibelleLongFr) {
		this.individuNationaliteLibelleLongFr = individuNationaliteLibelleLongFr;
	}

	/**
	 * [DossierTransfertDto.individuNationaliteLibelleLongAr] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the individuNationaliteLibelleLongAr
	 */
	public String getIndividuNationaliteLibelleLongAr() {
		return individuNationaliteLibelleLongAr;
	}

	/**
	 * [DossierTransfertDto.individuNationaliteLibelleLongAr] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param individuNationaliteLibelleLongAr
	 *            the individuNationaliteLibelleLongAr to set
	 */
	public void setIndividuNationaliteLibelleLongAr(
			String individuNationaliteLibelleLongAr) {
		this.individuNationaliteLibelleLongAr = individuNationaliteLibelleLongAr;
	}

	/**
	 * [DossierTransfertDto.bacId] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the bacId
	 */
	public Integer getBacId() {
		return bacId;
	}

	/**
	 * [DossierTransfertDto.bacId] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param bacId
	 *            the bacId to set
	 */
	public void setBacId(Integer bacId) {
		this.bacId = bacId;
	}

	/**
	 * [DossierTransfertDto.bacMatricule] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the bacMatricule
	 */
	public String getBacMatricule() {
		return bacMatricule;
	}

	/**
	 * [DossierTransfertDto.bacMatricule] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param bacMatricule
	 *            the bacMatricule to set
	 */
	public void setBacMatricule(String bacMatricule) {
		this.bacMatricule = bacMatricule;
	}

	/**
	 * [DossierTransfertDto.bacRefCodeSerie] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:52:13
	 * @return the bacRefCodeSerie
	 */
	public String getBacRefCodeSerie() {
		return bacRefCodeSerie;
	}

	/**
	 * [DossierTransfertDto.bacRefCodeSerie] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:52:13
	 * @param bacRefCodeSerie
	 *            the bacRefCodeSerie to set
	 */
	public void setBacRefCodeSerie(String bacRefCodeSerie) {
		this.bacRefCodeSerie = bacRefCodeSerie;
	}

	/**
	 * [DossierTransfertDto.bacLibelleSerie] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:52:13
	 * @return the bacLibelleSerie
	 */
	public String getBacLibelleSerie() {
		return bacLibelleSerie;
	}

	/**
	 * [DossierTransfertDto.moyenneBac] Setter
	 * 
	 * @author Mounir.MESSAOUDI on : 26 augest 2014 09:52:13
	 * @param bacLibelleSerie
	 *            the bacLibelleSerie to set
	 */
	public void setBacLibelleSerie(String bacLibelleSerie) {
		this.bacLibelleSerie = bacLibelleSerie;
	}

	/**
	 * [DossierTransfertDto.bacLibelleSerie] Getter
	 * 
	 * @author Mounir.MESSAOUDI on : 26 augest 2014 09:52:13
	 * @return the moyenneBac
	 */
	public Double getMoyenneBac() {
		return moyenneBac;
	}

	/**
	 * [DossierTransfertDto.moyenneBac] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:52:13
	 * @param moyenneBac
	 *            the moyenneBac to set
	 */
	public void setMoyenneBac(Double moyenneBac) {
		this.moyenneBac = moyenneBac;
	}

	/**
	 * [DossierTransfertDto.refLibelleTransfert] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the refLibelleTransfert
	 */
	public String getRefLibelleTransfert() {
		return refLibelleTransfert;
	}

	/**
	 * [DossierTransfertDto.refLibelleTransfert] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param refLibelleTransfert
	 *            the refLibelleTransfert to set
	 */
	public void setRefLibelleTransfert(String refLibelleTransfert) {
		this.refLibelleTransfert = refLibelleTransfert;
	}

	/**
	 * [DossierTransfertDto.typeDossier] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the typeDossier
	 */
	public String getTypeDossier() {
		return typeDossier;
	}

	/**
	 * [DossierTransfertDto.typeDossier] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param typeDossier
	 *            the typeDossier to set
	 */
	public void setTypeDossier(String typeDossier) {
		this.typeDossier = typeDossier;
	}

	/**
	 * [DossierTransfertDto.dateCreation] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * [DossierTransfertDto.dateCreation] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param dateCreation
	 *            the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * [DossierTransfertDto.anneeAcademiqueId] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [DossierTransfertDto.anneeAcademiqueId] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [DossierTransfertDto.anneeAcademiquePremiereAnnee] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the anneeAcademiquePremiereAnnee
	 */
	public Short getAnneeAcademiquePremiereAnnee() {
		return anneeAcademiquePremiereAnnee;
	}

	/**
	 * [DossierTransfertDto.anneeAcademiquePremiereAnnee] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param anneeAcademiquePremiereAnnee
	 *            the anneeAcademiquePremiereAnnee to set
	 */
	public void setAnneeAcademiquePremiereAnnee(
			Short anneeAcademiquePremiereAnnee) {
		this.anneeAcademiquePremiereAnnee = anneeAcademiquePremiereAnnee;
	}

	/**
	 * [DossierTransfertDto.anneeAcademiqueDeuxiemeAnnee] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the anneeAcademiqueDeuxiemeAnnee
	 */
	public Short getAnneeAcademiqueDeuxiemeAnnee() {
		return anneeAcademiqueDeuxiemeAnnee;
	}

	/**
	 * [DossierTransfertDto.anneeAcademiqueDeuxiemeAnnee] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param anneeAcademiqueDeuxiemeAnnee
	 *            the anneeAcademiqueDeuxiemeAnnee to set
	 */
	public void setAnneeAcademiqueDeuxiemeAnnee(
			Short anneeAcademiqueDeuxiemeAnnee) {
		this.anneeAcademiqueDeuxiemeAnnee = anneeAcademiqueDeuxiemeAnnee;
	}

	/**
	 * [DossierTransfertDto.refLibelleEtabAccueil] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the refLibelleEtabAccueil
	 */
	public String getRefLibelleEtabAccueil() {
		return refLibelleEtabAccueil;
	}

	/**
	 * [DossierTransfertDto.refLibelleEtabAccueil] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param refLibelleEtabAccueil
	 *            the refLibelleEtabAccueil to set
	 */
	public void setRefLibelleEtabAccueil(String refLibelleEtabAccueil) {
		this.refLibelleEtabAccueil = refLibelleEtabAccueil;
	}

	/**
	 * [DossierTransfertDto.ouvertureOfDemandeeId] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the ouvertureOfId
	 */
	public Integer getOuvertureOfId() {
		return ouvertureOfId;
	}

	/**
	 * [DossierTransfertDto.ouvertureOfDemandeeId] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param ouvertureOfId
	 *            the ouvertureOfDemandeeId to set
	 */
	public void setOuvertureOfId(Integer ouvertureOfId) {
		this.ouvertureOfId = ouvertureOfId;
	}

	/**
	 * [DossierTransfertDto.ofDemandeeLibelleFr] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the ofDemandeeLibelleFr
	 */
	public String getOfLibelleFr() {
		return ofLibelleFr;
	}

	/**
	 * [DossierTransfertDto.ofDemandeeLibelleFr] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param ofLibelleFr
	 *            the ofDemandeeLibelleFr to set
	 */
	public void setOfLibelleFr(String ofLibelleFr) {
		this.ofLibelleFr = ofLibelleFr;
	}

	/**
	 * [DossierTransfertDto.ofDemandeeLibelleFiliereFr] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the ofDemandeeLibelleFiliereFr
	 */
	public String getOfLibelleFiliereFr() {
		return ofLibelleFiliereFr;
	}

	/**
	 * [DossierTransfertDto.ofDemandeeLibelleFiliereFr] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param ofLibelleFiliereFr
	 *            the ofLibelleFiliereFr to set
	 */
	public void setOfLibelleFiliereFr(String ofLibelleFiliereFr) {
		this.ofLibelleFiliereFr = ofLibelleFiliereFr;
	}

	/**
	 * [DossierTransfertDto.ofDemandeeLibelleAr] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the ofDemandeeLibelleAr
	 */
	public String getOfLibelleAr() {
		return ofLibelleAr;
	}

	/**
	 * [DossierTransfertDto.ofDemandeeLibelleAr] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param ofLibelleAr
	 *            the ofLibelleAr to set
	 */
	public void setOfLibelleAr(String ofLibelleAr) {
		this.ofLibelleAr = ofLibelleAr;
	}

	/**
	 * [DossierTransfertDto.ofDemandeeLibelleFiliereAr] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @return the ofLibelleFiliereAr
	 */
	public String getOfLibelleFiliereAr() {
		return ofLibelleFiliereAr;
	}

	/**
	 * [DossierTransfertDto.ofDemandeeLibelleFiliereAr] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 09:23:06
	 * @param ofLibelleFiliereAr
	 *            the ofLibelleFiliereAr to set
	 */
	public void setOfLibelleFiliereAr(String ofLibelleFiliereAr) {
		this.ofLibelleFiliereAr = ofLibelleFiliereAr;
	}

	/**
	 * [DossierTransfertDto.anneeAcademiqueCode] Getter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 10:40:29
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}

	/**
	 * [DossierTransfertDto.anneeAcademiqueCode] Setter
	 * 
	 * @author BELDI Jamel on : 9 juin 2014 10:40:29
	 * @param anneeAcademiqueCode
	 *            the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	/**
	 * [DossierTransfertDto.refCodeEtabOrigine] Getter
	 * 
	 * @author BELDI Jamel on : 10 juin 2014 11:36:55
	 * @return the refCodeEtabOrigine
	 */
	public String getRefCodeEtabOrigine() {
		return refCodeEtabOrigine;
	}

	/**
	 * [DossierTransfertDto.refCodeEtabOrigine] Setter
	 * 
	 * @author BELDI Jamel on : 10 juin 2014 11:36:55
	 * @param refCodeEtabOrigine
	 *            the refCodeEtabOrigine to set
	 */
	public void setRefCodeEtabOrigine(String refCodeEtabOrigine) {
		this.refCodeEtabOrigine = refCodeEtabOrigine;
	}

	/**
	 * [DossierTransfertDto.situationId] Getter
	 * 
	 * @author BELDI Jamel on : 10 juil. 2014 11:16:44
	 * @return the situationId
	 */
	public Integer getSituationId() {
		return situationId;
	}

	/**
	 * [DossierTransfertDto.situationId] Setter
	 * 
	 * @author BELDI Jamel on : 10 juil. 2014 11:16:44
	 * @param situationId
	 *            the situationId to set
	 */
	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}

	/**
	 * [DossierTransfertDto.situationLibelleFr] Getter
	 * 
	 * @author BELDI Jamel on : 10 juil. 2014 11:16:44
	 * @return the situationLibelleFr
	 */
	public String getSituationLibelleFr() {
		return situationLibelleFr;
	}

	/**
	 * [DossierTransfertDto.situationLibelleFr] Setter
	 * 
	 * @author BELDI Jamel on : 10 juil. 2014 11:16:44
	 * @param situationLibelleFr
	 *            the situationLibelleFr to set
	 */
	public void setSituationLibelleFr(String situationLibelleFr) {
		this.situationLibelleFr = situationLibelleFr;
	}

	/**
	 * [DossierTransfertDto.situationLibelleAr] Getter
	 * 
	 * @author BELDI Jamel on : 10 juil. 2014 11:16:44
	 * @return the situationLibelleAr
	 */
	public String getSituationLibelleAr() {
		return situationLibelleAr;
	}

	/**
	 * [DossierTransfertDto.situationLibelleAr] Setter
	 * 
	 * @author BELDI Jamel on : 10 juil. 2014 11:16:44
	 * @param situationLibelleAr
	 *            the situationLibelleAr to set
	 */
	public void setSituationLibelleAr(String situationLibelleAr) {
		this.situationLibelleAr = situationLibelleAr;
	}

	/**
	 * [DossierTransfertDto.refLibelleAvisEtabOrigine] Getter
	 * 
	 * @author BELDI Jamel on : 13 juil. 2014 10:37:27
	 * @return the refLibelleAvisEtabOrigine
	 */
	public String getRefLibelleAvisEtabOrigine() {
		return refLibelleAvisEtabOrigine;
	}

	/**
	 * [DossierTransfertDto.refLibelleAvisEtabOrigine] Setter
	 * 
	 * @author BELDI Jamel on : 13 juil. 2014 10:37:27
	 * @param refLibelleAvisEtabOrigine
	 *            the refLibelleAvisEtabOrigine to set
	 */
	public void setRefLibelleAvisEtabOrigine(String refLibelleAvisEtabOrigine) {
		this.refLibelleAvisEtabOrigine = refLibelleAvisEtabOrigine;
	}

	/**
	 * [DossierTransfertDto.refLibelleMotifEtabOrigine] Getter
	 * 
	 * @author BELDI Jamel on : 13 juil. 2014 10:37:27
	 * @return the refLibelleMotifEtabOrigine
	 */
	public String getRefLibelleMotifEtabOrigine() {
		return refLibelleMotifEtabOrigine;
	}

	/**
	 * [DossierTransfertDto.refLibelleMotifEtabOrigine] Setter
	 * 
	 * @author BELDI Jamel on : 13 juil. 2014 10:37:27
	 * @param refLibelleMotifEtabOrigine
	 *            the refLibelleMotifEtabOrigine to set
	 */
	public void setRefLibelleMotifEtabOrigine(String refLibelleMotifEtabOrigine) {
		this.refLibelleMotifEtabOrigine = refLibelleMotifEtabOrigine;
	}

	/**
	 * [DossierTransfertDto.refLibelleAvisEtabAccueil] Getter
	 * 
	 * @author BELDI Jamel on : 13 juil. 2014 10:37:27
	 * @return the refLibelleAvisEtabAccueil
	 */
	public String getRefLibelleAvisEtabAccueil() {
		return refLibelleAvisEtabAccueil;
	}

	/**
	 * [DossierTransfertDto.refLibelleAvisEtabAccueil] Setter
	 * 
	 * @author BELDI Jamel on : 13 juil. 2014 10:37:27
	 * @param refLibelleAvisEtabAccueil
	 *            the refLibelleAvisEtabAccueil to set
	 */
	public void setRefLibelleAvisEtabAccueil(String refLibelleAvisEtabAccueil) {
		this.refLibelleAvisEtabAccueil = refLibelleAvisEtabAccueil;
	}

	/**
	 * [DossierTransfertDto.refLibelleMotifEtabAccueil] Getter
	 * 
	 * @author BELDI Jamel on : 13 juil. 2014 10:37:27
	 * @return the refLibelleMotifEtabAccueil
	 */
	public String getRefLibelleMotifEtabAccueil() {
		return refLibelleMotifEtabAccueil;
	}

	/**
	 * [DossierTransfertDto.refLibelleMotifEtabAccueil] Setter
	 * 
	 * @author BELDI Jamel on : 13 juil. 2014 10:37:27
	 * @param refLibelleMotifEtabAccueil
	 *            the refLibelleMotifEtabAccueil to set
	 */
	public void setRefLibelleMotifEtabAccueil(String refLibelleMotifEtabAccueil) {
		this.refLibelleMotifEtabAccueil = refLibelleMotifEtabAccueil;
	}

	/**
	 * 
	 * @return
	 */
	public String getCodeSituation() {
		return codeSituation;
	}

	/**
	 * 
	 * @param codeSituation
	 */
	public void setCodeSituation(String codeSituation) {
		this.codeSituation = codeSituation;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean getCasExceptionnel() {
		return casExceptionnel;
	}

	/**
	 * 
	 * @param casExceptionnel
	 */
	public void setCasExceptionnel(Boolean casExceptionnel) {
		this.casExceptionnel = casExceptionnel;
	}

	/**
	 * 
	 * @return
	 */
	public String getRefCodeMotifCasExceptionnel() {
		return refCodeMotifCasExceptionnel;
	}

	/**
	 * 
	 * @param refCodeMotifCasExceptionnel
	 */
	public void setRefCodeMotifCasExceptionnel(
			String refCodeMotifCasExceptionnel) {
		this.refCodeMotifCasExceptionnel = refCodeMotifCasExceptionnel;
	}

	/**
	 * 
	 * @return
	 */
	public String getObservationEtabAccueil() {
		return observationEtabAccueil;
	}

	/**
	 * 
	 * @param observationEtabAccueil
	 */
	public void setObservationEtabAccueil(String observationEtabAccueil) {
		this.observationEtabAccueil = observationEtabAccueil;
	}

	/**
	 * 
	 * @return
	 */
	public String getObservationEtabOrigine() {
		return observationEtabOrigine;
	}

	/**
	 * 
	 * @param observationEtabOrigine
	 */
	public void setObservationEtabOrigine(String observationEtabOrigine) {
		this.observationEtabOrigine = observationEtabOrigine;
	}

	/**
	 * 
	 * @return
	 */
	public String getObservationCommission() {
		return observationCommission;
	}

	/**
	 * 
	 * @param observationCommission
	 */
	public void setObservationCommission(String observationCommission) {
		this.observationCommission = observationCommission;
	}

	/**
	 * 
	 * @return
	 */
	public String getRefCodeDomaineOrigine() {
		return refCodeDomaineOrigine;
	}

	/**
	 * 
	 * @param refCodeDomaineOrigine
	 */
	public void setRefCodeDomaineOrigine(String refCodeDomaineOrigine) {
		this.refCodeDomaineOrigine = refCodeDomaineOrigine;
	}

	/**
	 * 
	 * @return
	 */
	public String getDomaineOrigineLibelleFr() {
		return domaineOrigineLibelleFr;
	}

	/**
	 * 
	 * @param domaineOrigineLibelleFr
	 */
	public void setDomaineOrigineLibelleFr(String domaineOrigineLibelleFr) {
		this.domaineOrigineLibelleFr = domaineOrigineLibelleFr;
	}

	/**
	 * 
	 * @return
	 */
	public String getDomaineOrigineLibelleAr() {
		return domaineOrigineLibelleAr;
	}

	/**
	 * 
	 * @param domaineOrigineLibelleAr
	 */
	public void setDomaineOrigineLibelleAr(String domaineOrigineLibelleAr) {
		this.domaineOrigineLibelleAr = domaineOrigineLibelleAr;
	}

	/**
	 * 
	 * @return
	 */
	public String getRefCodeFiliereOrigine() {
		return refCodeFiliereOrigine;
	}

	/**
	 * 
	 * @param refCodeFiliereOrigine
	 */
	public void setRefCodeFiliereOrigine(String refCodeFiliereOrigine) {
		this.refCodeFiliereOrigine = refCodeFiliereOrigine;
	}

	/**
	 * 
	 * @return
	 */
	public String getFiliereOrigineLibelleFr() {
		return filiereOrigineLibelleFr;
	}

	/**
	 * 
	 * @param filiereOrigineLibelleFr
	 */
	public void setFiliereOrigineLibelleFr(String filiereOrigineLibelleFr) {
		this.filiereOrigineLibelleFr = filiereOrigineLibelleFr;
	}

	/**
	 * 
	 * @return
	 */
	public String getIFiereOrigineLibelleAr() {
		return filiereOrigineLibelleAr;
	}

	/**
	 * 
	 * @param filiereOrigineLibelleAr
	 */
	public void setIFiereOrigineLibelleAr(String filiereOrigineLibelleAr) {
		this.filiereOrigineLibelleAr = filiereOrigineLibelleAr;
	}

	/**
	 * 
	 * @return
	 */
	public String getRefCodeDomaineDemande() {
		return refCodeDomaineDemande;
	}

	/**
	 * 
	 * @param refCodeDomaineDemande
	 */
	public void setRefCodeDomaineDemande(String refCodeDomaineDemande) {
		this.refCodeDomaineDemande = refCodeDomaineDemande;
	}

	/**
	 * 
	 * @return
	 */
	public String getDomaineDemandeLibelleFr() {
		return domaineDemandeLibelleFr;
	}

	/**
	 * 
	 * @param domaineDemandeLibelleFr
	 */
	public void setDomaineDemandeLibelleFr(String domaineDemandeLibelleFr) {
		this.domaineDemandeLibelleFr = domaineDemandeLibelleFr;
	}

	/**
	 * 
	 * @return
	 */
	public String getDomaineDemandeLibelleAr() {
		return domaineDemandeLibelleAr;
	}

	/**
	 * 
	 * @param domaineDemandeLibelleAr
	 */
	public void setDomaineDemandeLibelleAr(String domaineDemandeLibelleAr) {
		this.domaineDemandeLibelleAr = domaineDemandeLibelleAr;
	}

	/**
	 * 
	 * @return
	 */
	public String getFiliereOrigineLibelleAr() {
		return filiereOrigineLibelleAr;
	}

	/**
	 * 
	 * @param filiereOrigineLibelleAr
	 */
	public void setFiliereOrigineLibelleAr(String filiereOrigineLibelleAr) {
		this.filiereOrigineLibelleAr = filiereOrigineLibelleAr;
	}

	/**
	 * 
	 * @return
	 */
	public String getRefCodeFiliereDemandee() {
		return refCodeFiliereDemandee;
	}

	/**
	 * 
	 * @param refCodeFiliereDemandee
	 */
	public void setRefCodeFiliereDemandee(String refCodeFiliereDemandee) {
		this.refCodeFiliereDemandee = refCodeFiliereDemandee;
	}

	/**
	 * 
	 * @return
	 */
	public String getFiliereDemandeeLibelleFr() {
		return filiereDemandeeLibelleFr;
	}

	/**
	 * 
	 * @param filiereDemandeeLibelleFr
	 */
	public void setFiliereDemandeeLibelleFr(String filiereDemandeeLibelleFr) {
		this.filiereDemandeeLibelleFr = filiereDemandeeLibelleFr;
	}

	/**
	 * 
	 * @return
	 */
	public String getFiliereDemandeeLibelleAr() {
		return filiereDemandeeLibelleAr;
	}

	/**
	 * 
	 * @param filiereDemandeeLibelleAr
	 */
	public void setFiliereDemandeeLibelleAr(String filiereDemandeeLibelleAr) {
		this.filiereDemandeeLibelleAr = filiereDemandeeLibelleAr;
	}

	public Boolean getIsAccordee() {
		return isAccordee;
	}

	public void setIsAccordee(Boolean isAccordee) {
		this.isAccordee = isAccordee;
	}

	public String getRefLibelleAvisFinal() {
		return refLibelleAvisFinal;
	}

	public void setRefLibelleAvisFinal(String refLibelleAvisFinal) {
		this.refLibelleAvisFinal = refLibelleAvisFinal;
	}

	public String getCasExceptionnelLieblleFr() {
		return casExceptionnelLieblleFr;
	}

	public void setCasExceptionnelLieblleFr(String casExceptionnelLieblleFr) {
		this.casExceptionnelLieblleFr = casExceptionnelLieblleFr;
	}

	public String getCasExceptionnelLieblleAr() {
		return casExceptionnelLieblleAr;
	}

	public void setCasExceptionnelLieblleAr(String casExceptionnelLieblleAr) {
		this.casExceptionnelLieblleAr = casExceptionnelLieblleAr;
	}

	public String getRefCodeMotifCasExceptionnelLieblleFr() {
		return refCodeMotifCasExceptionnelLieblleFr;
	}

	public void setRefCodeMotifCasExceptionnelLieblleFr(
			String refCodeMotifCasExceptionnelLieblleFr) {
		this.refCodeMotifCasExceptionnelLieblleFr = refCodeMotifCasExceptionnelLieblleFr;
	}

	public String getRefCodeMotifCasExceptionnelLieblleAr() {
		return refCodeMotifCasExceptionnelLieblleAr;
	}

	public void setRefCodeMotifCasExceptionnelLieblleAr(
			String refCodeMotifCasExceptionnelLieblleAr) {
		this.refCodeMotifCasExceptionnelLieblleAr = refCodeMotifCasExceptionnelLieblleAr;
	}

	public String getIsAccordeeLieblleFr() {
		return isAccordeeLieblleFr;
	}

	public void setIsAccordeeLieblleFr(String isAccordeeLieblleFr) {
		this.isAccordeeLieblleFr = isAccordeeLieblleFr;
	}

	public String getIsAccordeeLieblleAr() {
		return isAccordeeLieblleAr;
	}

	public void setIsAccordeeLieblleAr(String isAccordeeLieblleAr) {
		this.isAccordeeLieblleAr = isAccordeeLieblleAr;
	}

	public String getIsEtabOrigineAccepteLibelleFr() {
		return isEtabOrigineAccepteLibelleFr;
	}

	public void setIsEtabOrigineAccepteLibelleFr(
			String isEtabOrigineAccepteLibelleFr) {
		this.isEtabOrigineAccepteLibelleFr = isEtabOrigineAccepteLibelleFr;
	}

	public String getIsEtabOrigineAccepteLibelleAr() {
		return isEtabOrigineAccepteLibelleAr;
	}

	public void setIsEtabOrigineAccepteLibelleAr(
			String isEtabOrigineAccepteLibelleAr) {
		this.isEtabOrigineAccepteLibelleAr = isEtabOrigineAccepteLibelleAr;
	}

	public String getIsEtabAccueilAccepteLibelleFr() {
		return isEtabAccueilAccepteLibelleFr;
	}

	public void setIsEtabAccueilAccepteLibelleFr(
			String isEtabAccueilAccepteLibelleFr) {
		this.isEtabAccueilAccepteLibelleFr = isEtabAccueilAccepteLibelleFr;
	}

	public String getIsEtabAccueilAccepteLibelleAr() {
		return isEtabAccueilAccepteLibelleAr;
	}

	public void setIsEtabAccueilAccepteLibelleAr(
			String isEtabAccueilAccepteLibelleAr) {
		this.isEtabAccueilAccepteLibelleAr = isEtabAccueilAccepteLibelleAr;
	}

	public String getIndividuRefCodeWilaya() {
		return individuRefCodeWilaya;
	}

	public void setIndividuRefCodeWilaya(String individuRefCodeWilaya) {
		this.individuRefCodeWilaya = individuRefCodeWilaya;
	}

	public String getIndividuRefCodeWilayaLibelleLongFr() {
		return individuRefCodeWilayaLibelleLongFr;
	}

	public void setIndividuRefCodeWilayaLibelleLongFr(
			String individuRefCodeWilayaLibelleLongFr) {
		this.individuRefCodeWilayaLibelleLongFr = individuRefCodeWilayaLibelleLongFr;
	}

	public String getIndividuRefCodeWilayaLibelleLongAr() {
		return individuRefCodeWilayaLibelleLongAr;
	}

	public void setIndividuRefCodeWilayaLibelleLongAr(
			String individuRefCodeWilayaLibelleLongAr) {
		this.individuRefCodeWilayaLibelleLongAr = individuRefCodeWilayaLibelleLongAr;
	}

	public Boolean getNouveauBachelier() {
		return nouveauBachelier;
	}

	public void setNouveauBachelier(Boolean nouveauBachelier) {
		this.nouveauBachelier = nouveauBachelier;
	}

	public void setNouveauAncienBachelierLiebleAr(
			String nouveauAncienBachelierLiebleAr) {
		this.nouveauAncienBachelierLiebleAr = nouveauAncienBachelierLiebleAr;
	}

	public String getNouveauAncienBachelierLiebleAr() {
		return nouveauAncienBachelierLiebleAr;
	}

	public void setNouveauAncienBachelierLiebleFr(
			String nouveauAncienBachelierLiebleFr) {
		this.nouveauAncienBachelierLiebleFr = nouveauAncienBachelierLiebleFr;
	}

	public String getNouveauAncienBachelierLiebleFr() {
		return nouveauAncienBachelierLiebleFr;
	}

	public String getRefCodeMotif() {
		return refCodeMotif;
	}

	public void setRefCodeMotif(String refCodeMotif) {
		this.refCodeMotif = refCodeMotif;
	}

	public String getRefCodeMotifLibelleFr() {
		return refCodeMotifLibelleFr;
	}

	public void setRefCodeMotifLibelleFr(String refCodeMotifLibelleFr) {
		this.refCodeMotifLibelleFr = refCodeMotifLibelleFr;
	}

	public String getRefCodeMotifLibelleAr() {
		return refCodeMotifLibelleAr;
	}

	public void setRefCodeMotifLibelleAr(String refCodeMotifLibelleAr) {
		this.refCodeMotifLibelleAr = refCodeMotifLibelleAr;
	}

	public List<SituationEntiteOccurrenceDto> getDossierTransfertHistory() {
		return dossierTransfertHistory;
	}

	public void setDossierTransfertHistory(
			List<SituationEntiteOccurrenceDto> dossierTransfertHistory) {
		this.dossierTransfertHistory = dossierTransfertHistory;
	}
	
	public String getIndividuEmail() {
		return individuEmail;
	}
	public void setIndividuEmail(String individuEmail) {
		this.individuEmail = individuEmail;
	}
	
	public String getIndividuCivility() {
		return individuCivility;
	}
	
	public void setIndividuCivility(String individuCivility) {
		this.individuCivility = individuCivility;
	}

}
