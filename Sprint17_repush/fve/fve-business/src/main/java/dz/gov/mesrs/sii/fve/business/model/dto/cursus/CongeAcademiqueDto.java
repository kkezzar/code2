package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.util.Date;

/**
 * 
 * @author yselmane on : 8 juin 2014 14:23:00
 */
public class CongeAcademiqueDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;

	private int idDossier;
	private Date dateCreation;
	private String typeDossier;

	private int idDossierEtudiant;
	private Integer dossierInscriptionId;
	private String dossierEtudiantMatricule;

	private Date dateDemande;
	private String refCodeTypeConge;
	private String typeCongeLibelleLongFr;
	private String typeCongeLibelleLongAr;

	private Date dateDebutDemande;
	private Date dateFinDemande;
	private boolean resultat;
	private Date dateResultat;
	private Date dateDebutAccordee;
	private Date dateFinAccordee;
	private String motifRefus;
	private Date dateReintegration;
	private String raison;

	private boolean demandeValidee;
	private boolean resultatValide;
	private boolean reintegrationValidee;

	private Date dateValidationDemande;
	private Date dateValidationResultat;
	private Date dateValidationReintegration;

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

	private Integer anneeAcademiqueId;
	private String anneeAcademiqueCode;
	private Short anneeAcademiquePremiereAnnee;
	private Short anneeAcademiqueDeuxiemeAnnee;

	private Integer anneeAcademiqueReintegrationId;
	private String anneeAcademiqueReintegrationCode;
	private Short anneeAcademiqueReintegrationPremiereAnnee;
	private Short anneeAcademiqueReintegrationDeuxiemeAnnee;

	// BAC
	private Integer bacId;
	private String bacMatricule;
	private String bacRefCodeSerie;
	private String bacLibelleSerie;

	public CongeAcademiqueDto() {
	}

	/**
	 * [CongeAcademiqueDto.id] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [CongeAcademiqueDto.id] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [CongeAcademiqueDto.idDossier] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @return the idDossier
	 */
	public int getIdDossier() {
		return idDossier;
	}

	/**
	 * [CongeAcademiqueDto.idDossier] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @param idDossier
	 *            the idDossier to set
	 */
	public void setIdDossier(int idDossier) {
		this.idDossier = idDossier;
	}

	/**
	 * [CongeAcademiqueDto.idDossierEtudiant] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @return the idDossierEtudiant
	 */
	public int getIdDossierEtudiant() {
		return idDossierEtudiant;
	}

	/**
	 * [CongeAcademiqueDto.idDossierEtudiant] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @param idDossierEtudiant
	 *            the idDossierEtudiant to set
	 */
	public void setIdDossierEtudiant(int idDossierEtudiant) {
		this.idDossierEtudiant = idDossierEtudiant;
	}

	/**
	 * [CongeAcademiqueDto.dateDemande] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @return the dateDemande
	 */
	public Date getDateDemande() {
		return dateDemande;
	}

	/**
	 * [CongeAcademiqueDto.dateDemande] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @param dateDemande
	 *            the dateDemande to set
	 */
	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	/**
	 * [CongeAcademiqueDto.refCodeTypeConge] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @return the refCodeTypeConge
	 */
	public String getRefCodeTypeConge() {
		return refCodeTypeConge;
	}

	/**
	 * [CongeAcademiqueDto.refCodeTypeConge] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @param refCodeTypeConge
	 *            the refCodeTypeConge to set
	 */
	public void setRefCodeTypeConge(String refCodeTypeConge) {
		this.refCodeTypeConge = refCodeTypeConge;
	}

	/**
	 * [CongeAcademiqueDto.typeCongeLibelleLongFr] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 15:41:08
	 * @return the typeCongeLibelleLongFr
	 */
	public String getTypeCongeLibelleLongFr() {
		return typeCongeLibelleLongFr;
	}

	/**
	 * [CongeAcademiqueDto.typeCongeLibelleLongFr] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 15:41:08
	 * @param typeCongeLibelleLongFr
	 *            the typeCongeLibelleLongFr to set
	 */
	public void setTypeCongeLibelleLongFr(String typeCongeLibelleLongFr) {
		this.typeCongeLibelleLongFr = typeCongeLibelleLongFr;
	}

	/**
	 * [CongeAcademiqueDto.typeCongeLibelleLongAr] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 15:41:08
	 * @return the typeCongeLibelleLongAr
	 */
	public String getTypeCongeLibelleLongAr() {
		return typeCongeLibelleLongAr;
	}

	/**
	 * [CongeAcademiqueDto.typeCongeLibelleLongAr] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 15:41:08
	 * @param typeCongeLibelleLongAr
	 *            the typeCongeLibelleLongAr to set
	 */
	public void setTypeCongeLibelleLongAr(String typeCongeLibelleLongAr) {
		this.typeCongeLibelleLongAr = typeCongeLibelleLongAr;
	}

	/**
	 * [CongeAcademiqueDto.dateDebutDemande] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @return the dateDebutDemande
	 */
	public Date getDateDebutDemande() {
		return dateDebutDemande;
	}

	/**
	 * [CongeAcademiqueDto.dateDebutDemande] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @param dateDebutDemande
	 *            the dateDebutDemande to set
	 */
	public void setDateDebutDemande(Date dateDebutDemande) {
		this.dateDebutDemande = dateDebutDemande;
	}

	/**
	 * [CongeAcademiqueDto.dateFinDemande] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @return the dateFinDemande
	 */
	public Date getDateFinDemande() {
		return dateFinDemande;
	}

	/**
	 * [CongeAcademiqueDto.dateFinDemande] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @param dateFinDemande
	 *            the dateFinDemande to set
	 */
	public void setDateFinDemande(Date dateFinDemande) {
		this.dateFinDemande = dateFinDemande;
	}

	/**
	 * [CongeAcademiqueDto.resultat] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @return the resultat
	 */
	public boolean getResultat() {
		return resultat;
	}

	/**
	 * [CongeAcademiqueDto.resultat] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @param resultat
	 *            the resultat to set
	 */
	public void setResultat(boolean resultat) {
		this.resultat = resultat;
	}

	/**
	 * [CongeAcademiqueDto.dateResultat] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @return the dateResultat
	 */
	public Date getDateResultat() {
		return dateResultat;
	}

	/**
	 * [CongeAcademiqueDto.dateResultat] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @param dateResultat
	 *            the dateResultat to set
	 */
	public void setDateResultat(Date dateResultat) {
		this.dateResultat = dateResultat;
	}

	/**
	 * [CongeAcademiqueDto.dateDebutAccordee] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @return the dateDebutAccordee
	 */
	public Date getDateDebutAccordee() {
		return dateDebutAccordee;
	}

	/**
	 * [CongeAcademiqueDto.dateDebutAccordee] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @param dateDebutAccordee
	 *            the dateDebutAccordee to set
	 */
	public void setDateDebutAccordee(Date dateDebutAccordee) {
		this.dateDebutAccordee = dateDebutAccordee;
	}

	/**
	 * [CongeAcademiqueDto.dateFinAccordee] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @return the dateFinAccordee
	 */
	public Date getDateFinAccordee() {
		return dateFinAccordee;
	}

	/**
	 * [CongeAcademiqueDto.dateFinAccordee] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @param dateFinAccordee
	 *            the dateFinAccordee to set
	 */
	public void setDateFinAccordee(Date dateFinAccordee) {
		this.dateFinAccordee = dateFinAccordee;
	}

	/**
	 * [CongeAcademiqueDto.motifRefus] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @return the motifRefus
	 */
	public String getMotifRefus() {
		return motifRefus;
	}

	/**
	 * [CongeAcademiqueDto.motifRefus] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @param motifRefus
	 *            the motifRefus to set
	 */
	public void setMotifRefus(String motifRefus) {
		this.motifRefus = motifRefus;
	}

	/**
	 * [CongeAcademiqueDto.dateReintegration] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @return the dateReintegration
	 */
	public Date getDateReintegration() {
		return dateReintegration;
	}

	/**
	 * [CongeAcademiqueDto.dateReintegration] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @param dateReintegration
	 *            the dateReintegration to set
	 */
	public void setDateReintegration(Date dateReintegration) {
		this.dateReintegration = dateReintegration;
	}

	/**
	 * [CongeAcademiqueDto.raison] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @return the raison
	 */
	public String getRaison() {
		return raison;
	}

	/**
	 * [CongeAcademiqueDto.raison] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 14:22:41
	 * @param raison
	 *            the raison to set
	 */
	public void setRaison(String raison) {
		this.raison = raison;
	}

	/**
	 * [CongeAcademiqueDto.dateCreation] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 16:37:29
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * [CongeAcademiqueDto.dateCreation] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 16:37:29
	 * @param dateCreation
	 *            the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * [CongeAcademiqueDto.typeDossier] Getter
	 * 
	 * @author yselmane on : 8 juin 2014 16:37:29
	 * @return the typeDossier
	 */
	public String getTypeDossier() {
		return typeDossier;
	}

	/**
	 * [CongeAcademiqueDto.typeDossier] Setter
	 * 
	 * @author yselmane on : 8 juin 2014 16:37:29
	 * @param typeDossier
	 *            the typeDossier to set
	 */
	public void setTypeDossier(String typeDossier) {
		this.typeDossier = typeDossier;
	}

	/**
	 * [CongeAcademiqueDto.individuId] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @return the individuId
	 */
	public Integer getIndividuId() {
		return individuId;
	}

	/**
	 * [CongeAcademiqueDto.individuId] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @param individuId
	 *            the individuId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}

	/**
	 * [CongeAcademiqueDto.individuNin] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @return the individuNin
	 */
	public String getIndividuNin() {
		return individuNin;
	}

	/**
	 * [CongeAcademiqueDto.individuNin] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @param individuNin
	 *            the individuNin to set
	 */
	public void setIndividuNin(String individuNin) {
		this.individuNin = individuNin;
	}

	/**
	 * [CongeAcademiqueDto.individuNomArabe] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @return the individuNomArabe
	 */
	public String getIndividuNomArabe() {
		return individuNomArabe;
	}

	/**
	 * [CongeAcademiqueDto.individuNomArabe] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @param individuNomArabe
	 *            the individuNomArabe to set
	 */
	public void setIndividuNomArabe(String individuNomArabe) {
		this.individuNomArabe = individuNomArabe;
	}

	/**
	 * [CongeAcademiqueDto.individuNomLatin] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @return the individuNomLatin
	 */
	public String getIndividuNomLatin() {
		return individuNomLatin;
	}

	/**
	 * [CongeAcademiqueDto.individuNomLatin] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @param individuNomLatin
	 *            the individuNomLatin to set
	 */
	public void setIndividuNomLatin(String individuNomLatin) {
		this.individuNomLatin = individuNomLatin;
	}

	/**
	 * [CongeAcademiqueDto.individuPrenomArabe] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @return the individuPrenomArabe
	 */
	public String getIndividuPrenomArabe() {
		return individuPrenomArabe;
	}

	/**
	 * [CongeAcademiqueDto.individuPrenomArabe] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @param individuPrenomArabe
	 *            the individuPrenomArabe to set
	 */
	public void setIndividuPrenomArabe(String individuPrenomArabe) {
		this.individuPrenomArabe = individuPrenomArabe;
	}

	/**
	 * [CongeAcademiqueDto.individuPrenomLatin] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @return the individuPrenomLatin
	 */
	public String getIndividuPrenomLatin() {
		return individuPrenomLatin;
	}

	/**
	 * [CongeAcademiqueDto.individuPrenomLatin] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @param individuPrenomLatin
	 *            the individuPrenomLatin to set
	 */
	public void setIndividuPrenomLatin(String individuPrenomLatin) {
		this.individuPrenomLatin = individuPrenomLatin;
	}

	/**
	 * [CongeAcademiqueDto.individuDateNaissance] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @return the individuDateNaissance
	 */
	public Date getIndividuDateNaissance() {
		return individuDateNaissance;
	}

	/**
	 * [CongeAcademiqueDto.individuDateNaissance] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @param individuDateNaissance
	 *            the individuDateNaissance to set
	 */
	public void setIndividuDateNaissance(Date individuDateNaissance) {
		this.individuDateNaissance = individuDateNaissance;
	}

	/**
	 * [CongeAcademiqueDto.individuNationaliteLibelleLongFr] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @return the individuNationaliteLibelleLongFr
	 */
	public String getIndividuNationaliteLibelleLongFr() {
		return individuNationaliteLibelleLongFr;
	}

	/**
	 * [CongeAcademiqueDto.individuNationaliteLibelleLongFr] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @param individuNationaliteLibelleLongFr
	 *            the individuNationaliteLibelleLongFr to set
	 */
	public void setIndividuNationaliteLibelleLongFr(
			String individuNationaliteLibelleLongFr) {
		this.individuNationaliteLibelleLongFr = individuNationaliteLibelleLongFr;
	}

	/**
	 * [CongeAcademiqueDto.individuNationaliteLibelleLongAr] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @return the individuNationaliteLibelleLongAr
	 */
	public String getIndividuNationaliteLibelleLongAr() {
		return individuNationaliteLibelleLongAr;
	}

	/**
	 * [CongeAcademiqueDto.individuNationaliteLibelleLongAr] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @param individuNationaliteLibelleLongAr
	 *            the individuNationaliteLibelleLongAr to set
	 */
	public void setIndividuNationaliteLibelleLongAr(
			String individuNationaliteLibelleLongAr) {
		this.individuNationaliteLibelleLongAr = individuNationaliteLibelleLongAr;
	}

	/**
	 * [CongeAcademiqueDto.bacId] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @return the bacId
	 */
	public Integer getBacId() {
		return bacId;
	}

	/**
	 * [CongeAcademiqueDto.bacId] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @param bacId
	 *            the bacId to set
	 */
	public void setBacId(Integer bacId) {
		this.bacId = bacId;
	}

	/**
	 * [CongeAcademiqueDto.bacMatricule] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @return the bacMatricule
	 */
	public String getBacMatricule() {
		return bacMatricule;
	}

	/**
	 * [CongeAcademiqueDto.bacMatricule] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @param bacMatricule
	 *            the bacMatricule to set
	 */
	public void setBacMatricule(String bacMatricule) {
		this.bacMatricule = bacMatricule;
	}

	/**
	 * [CongeAcademiqueDto.bacRefCodeSerie] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @return the bacRefCodeSerie
	 */
	public String getBacRefCodeSerie() {
		return bacRefCodeSerie;
	}

	/**
	 * [CongeAcademiqueDto.bacRefCodeSerie] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @param bacRefCodeSerie
	 *            the bacRefCodeSerie to set
	 */
	public void setBacRefCodeSerie(String bacRefCodeSerie) {
		this.bacRefCodeSerie = bacRefCodeSerie;
	}

	/**
	 * [CongeAcademiqueDto.bacLibelleSerie] Getter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @return the bacLibelleSerie
	 */
	public String getBacLibelleSerie() {
		return bacLibelleSerie;
	}

	/**
	 * [CongeAcademiqueDto.bacLibelleSerie] Setter
	 * 
	 * @author yselmane on : 16 juin 2014 18:03:18
	 * @param bacLibelleSerie
	 *            the bacLibelleSerie to set
	 */
	public void setBacLibelleSerie(String bacLibelleSerie) {
		this.bacLibelleSerie = bacLibelleSerie;
	}

	/**
	 * [CongeAcademiqueDto.demandeValidee] Getter
	 * 
	 * @author yselmane on : 18 juin 2014 12:57:43
	 * @return the demandeValidee
	 */
	public boolean isDemandeValidee() {
		return demandeValidee;
	}

	/**
	 * [CongeAcademiqueDto.demandeValidee] Setter
	 * 
	 * @author yselmane on : 18 juin 2014 12:57:43
	 * @param demandeValidee
	 *            the demandeValidee to set
	 */
	public void setDemandeValidee(boolean demandeValidee) {
		this.demandeValidee = demandeValidee;
	}

	/**
	 * [CongeAcademiqueDto.resultatValide] Getter
	 * 
	 * @author yselmane on : 18 juin 2014 12:57:43
	 * @return the resultatValide
	 */
	public boolean isResultatValide() {
		return resultatValide;
	}

	/**
	 * [CongeAcademiqueDto.resultatValide] Setter
	 * 
	 * @author yselmane on : 18 juin 2014 12:57:43
	 * @param resultatValide
	 *            the resultatValide to set
	 */
	public void setResultatValide(boolean resultatValide) {
		this.resultatValide = resultatValide;
	}

	/**
	 * [CongeAcademiqueDto.reintegrationValidee] Getter
	 * 
	 * @author yselmane on : 18 juin 2014 12:57:43
	 * @return the reintegrationValidee
	 */
	public boolean isReintegrationValidee() {
		return reintegrationValidee;
	}

	/**
	 * [CongeAcademiqueDto.reintegrationValidee] Setter
	 * 
	 * @author yselmane on : 18 juin 2014 12:57:43
	 * @param reintegrationValidee
	 *            the reintegrationValidee to set
	 */
	public void setReintegrationValidee(boolean reintegrationValidee) {
		this.reintegrationValidee = reintegrationValidee;
	}

	/**
	 * [CongeAcademiqueDto.dateValidationDemande] Getter
	 * 
	 * @author yselmane on : 19 juin 2014 09:54:43
	 * @return the dateValidationDemande
	 */
	public Date getDateValidationDemande() {
		return dateValidationDemande;
	}

	/**
	 * [CongeAcademiqueDto.dateValidationDemande] Setter
	 * 
	 * @author yselmane on : 19 juin 2014 09:54:43
	 * @param dateValidationDemande
	 *            the dateValidationDemande to set
	 */
	public void setDateValidationDemande(Date dateValidationDemande) {
		this.dateValidationDemande = dateValidationDemande;
	}

	/**
	 * [CongeAcademiqueDto.dateValidationResultat] Getter
	 * 
	 * @author yselmane on : 19 juin 2014 09:54:43
	 * @return the dateValidationResultat
	 */
	public Date getDateValidationResultat() {
		return dateValidationResultat;
	}

	/**
	 * [CongeAcademiqueDto.dateValidationResultat] Setter
	 * 
	 * @author yselmane on : 19 juin 2014 09:54:43
	 * @param dateValidationResultat
	 *            the dateValidationResultat to set
	 */
	public void setDateValidationResultat(Date dateValidationResultat) {
		this.dateValidationResultat = dateValidationResultat;
	}

	/**
	 * [CongeAcademiqueDto.dateValidationReintegration] Getter
	 * 
	 * @author yselmane on : 19 juin 2014 09:54:43
	 * @return the dateValidationReintegration
	 */
	public Date getDateValidationReintegration() {
		return dateValidationReintegration;
	}

	/**
	 * [CongeAcademiqueDto.dateValidationReintegration] Setter
	 * 
	 * @author yselmane on : 19 juin 2014 09:54:43
	 * @param dateValidationReintegration
	 *            the dateValidationReintegration to set
	 */
	public void setDateValidationReintegration(Date dateValidationReintegration) {
		this.dateValidationReintegration = dateValidationReintegration;
	}

	public Integer getDossierInscriptionId() {
		return dossierInscriptionId;
	}

	public void setDossierInscriptionId(Integer dossierInscriptionId) {
		this.dossierInscriptionId = dossierInscriptionId;
	}

	public String getDossierEtudiantMatricule() {
		return dossierEtudiantMatricule;
	}

	public void setDossierEtudiantMatricule(String dossierEtudiantMatricule) {
		this.dossierEtudiantMatricule = dossierEtudiantMatricule;
	}

	public String getIndividuLieuNaissance() {
		return individuLieuNaissance;
	}

	public void setIndividuLieuNaissance(String individuLieuNaissance) {
		this.individuLieuNaissance = individuLieuNaissance;
	}

	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}

	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
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

	public Integer getAnneeAcademiqueReintegrationId() {
		return anneeAcademiqueReintegrationId;
	}

	public void setAnneeAcademiqueReintegrationId(
			Integer anneeAcademiqueReintegrationId) {
		this.anneeAcademiqueReintegrationId = anneeAcademiqueReintegrationId;
	}

	public String getAnneeAcademiqueReintegrationCode() {
		return anneeAcademiqueReintegrationCode;
	}

	public void setAnneeAcademiqueReintegrationCode(
			String anneeAcademiqueReintegrationCode) {
		this.anneeAcademiqueReintegrationCode = anneeAcademiqueReintegrationCode;
	}

	public Short getAnneeAcademiqueReintegrationPremiereAnnee() {
		return anneeAcademiqueReintegrationPremiereAnnee;
	}

	public void setAnneeAcademiqueReintegrationPremiereAnnee(
			Short anneeAcademiqueReintegrationPremiereAnnee) {
		this.anneeAcademiqueReintegrationPremiereAnnee = anneeAcademiqueReintegrationPremiereAnnee;
	}

	public Short getAnneeAcademiqueReintegrationDeuxiemeAnnee() {
		return anneeAcademiqueReintegrationDeuxiemeAnnee;
	}

	public void setAnneeAcademiqueReintegrationDeuxiemeAnnee(
			Short anneeAcademiqueReintegrationDeuxiemeAnnee) {
		this.anneeAcademiqueReintegrationDeuxiemeAnnee = anneeAcademiqueReintegrationDeuxiemeAnnee;
	}

}
