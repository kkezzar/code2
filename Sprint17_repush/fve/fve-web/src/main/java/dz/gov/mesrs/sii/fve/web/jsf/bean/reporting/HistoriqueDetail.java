/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.reporting.HistoriqueDetail.java] 
 * @author MAKERRI Sofiane on : 22 juin 2014  14:17:36
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.reporting;

import java.util.Date;

/**
 * @author MAKERRI Sofiane  on : 22 juin 2014  14:17:36
 */
public class HistoriqueDetail {

	private Integer id;
	private Integer idCompte;
	private Integer idEtablissement;
	private Integer idFonction;
	private Integer idIndividu;
	private Integer idOccurence;
	private String libelleAction;
	private String nomArabeUser;
	private String nomCompte;
	private String nomEntite;
	private String nomFonction;
	private String nomLatinUser;
	private String prenomArabeUser;
	private String prenomLatinUser;
	private Integer typeAction;
	private Integer dossierEtudiantId;
	private String numeroMatricule;
	private String matriculeBac;
	private String individuNomLatin;
	private String individuPrenomLatin;
	private String codeEtablissement;
	private String codeFonction;
	private String codeUser;
	private Date dateAction;
	private Date heureAction;
	/**
	 * [HistoriqueDetail.HistoriqueDetail()] Constructor
	 * @author MAKERRI Sofiane  on : 22 juin 2014  14:17:36	
	 */
	public HistoriqueDetail() {
		super();
	}
	
	/**
	 * [HistoriqueDetail.id] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * [HistoriqueDetail.id] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * [HistoriqueDetail.idCompte] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the idCompte
	 */
	public Integer getIdCompte() {
		return idCompte;
	}
	/**
	 * [HistoriqueDetail.idCompte] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param idCompte the idCompte to set
	 */
	public void setIdCompte(Integer idCompte) {
		this.idCompte = idCompte;
	}
	/**
	 * [HistoriqueDetail.idEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}
	/**
	 * [HistoriqueDetail.idEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param idEtablissement the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}
	/**
	 * [HistoriqueDetail.idFonction] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the idFonction
	 */
	public Integer getIdFonction() {
		return idFonction;
	}
	
	/**
	 * [HistoriqueDetail.idFonction] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param idFonction the idFonction to set
	 */
	public void setIdFonction(Integer idFonction) {
		this.idFonction = idFonction;
	}
	/**
	 * [HistoriqueDetail.idIndividu] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the idIndividu
	 */
	public Integer getIdIndividu() {
		return idIndividu;
	}
	/**
	 * [HistoriqueDetail.idIndividu] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param idIndividu the idIndividu to set
	 */
	public void setIdIndividu(Integer idIndividu) {
		this.idIndividu = idIndividu;
	}
	/**
	 * [HistoriqueDetail.idOccurence] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the idOccurence
	 */
	public Integer getIdOccurence() {
		return idOccurence;
	}
	/**
	 * [HistoriqueDetail.idOccurence] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param idOccurence the idOccurence to set
	 */
	public void setIdOccurence(Integer idOccurence) {
		this.idOccurence = idOccurence;
	}
	/**
	 * [HistoriqueDetail.libelleAction] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the libelleAction
	 */
	public String getLibelleAction() {
		return libelleAction;
	}
	/**
	 * [HistoriqueDetail.libelleAction] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param libelleAction the libelleAction to set
	 */
	public void setLibelleAction(String libelleAction) {
		this.libelleAction = libelleAction;
	}
	/**
	 * [HistoriqueDetail.nomArabeUser] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the nomArabeUser
	 */
	public String getNomArabeUser() {
		return nomArabeUser;
	}
	/**
	 * [HistoriqueDetail.nomArabeUser] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param nomArabeUser the nomArabeUser to set
	 */
	public void setNomArabeUser(String nomArabeUser) {
		this.nomArabeUser = nomArabeUser;
	}
	/**
	 * [HistoriqueDetail.nomCompte] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the nomCompte
	 */
	public String getNomCompte() {
		return nomCompte;
	}
	/**
	 * [HistoriqueDetail.nomCompte] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param nomCompte the nomCompte to set
	 */
	public void setNomCompte(String nomCompte) {
		this.nomCompte = nomCompte;
	}
	/**
	 * [HistoriqueDetail.nomEntite] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the nomEntite
	 */
	public String getNomEntite() {
		return nomEntite;
	}
	/**
	 * [HistoriqueDetail.nomEntite] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param nomEntite the nomEntite to set
	 */
	public void setNomEntite(String nomEntite) {
		this.nomEntite = nomEntite;
	}
	/**
	 * [HistoriqueDetail.nomFonction] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the nomFonction
	 */
	public String getNomFonction() {
		return nomFonction;
	}
	/**
	 * [HistoriqueDetail.nomFonction] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param nomFonction the nomFonction to set
	 */
	public void setNomFonction(String nomFonction) {
		this.nomFonction = nomFonction;
	}
	/**
	 * [HistoriqueDetail.nomLatinUser] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the nomLatinUser
	 */
	public String getNomLatinUser() {
		return nomLatinUser;
	}
	/**
	 * [HistoriqueDetail.nomLatinUser] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param nomLatinUser the nomLatinUser to set
	 */
	public void setNomLatinUser(String nomLatinUser) {
		this.nomLatinUser = nomLatinUser;
	}
	/**
	 * [HistoriqueDetail.prenomArabeUser] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the prenomArabeUser
	 */
	public String getPrenomArabeUser() {
		return prenomArabeUser;
	}
	/**
	 * [HistoriqueDetail.prenomArabeUser] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param prenomArabeUser the prenomArabeUser to set
	 */
	public void setPrenomArabeUser(String prenomArabeUser) {
		this.prenomArabeUser = prenomArabeUser;
	}
	/**
	 * [HistoriqueDetail.prenomLatinUser] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the prenomLatinUser
	 */
	public String getPrenomLatinUser() {
		return prenomLatinUser;
	}
	/**
	 * [HistoriqueDetail.prenomLatinUser] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param prenomLatinUser the prenomLatinUser to set
	 */
	public void setPrenomLatinUser(String prenomLatinUser) {
		this.prenomLatinUser = prenomLatinUser;
	}
	/**
	 * [HistoriqueDetail.typeAction] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the typeAction
	 */
	public Integer getTypeAction() {
		return typeAction;
	}
	/**
	 * [HistoriqueDetail.typeAction] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param typeAction the typeAction to set
	 */
	public void setTypeAction(Integer typeAction) {
		this.typeAction = typeAction;
	}
	/**
	 * [HistoriqueDetail.dossierEtudiantId] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the dossierEtudiantId
	 */
	public Integer getDossierEtudiantId() {
		return dossierEtudiantId;
	}
	/**
	 * [HistoriqueDetail.dossierEtudiantId] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param dossierEtudiantId the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(Integer dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}
	/**
	 * [HistoriqueDetail.numeroMatricule] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the numeroMatricule
	 */
	public String getNumeroMatricule() {
		return numeroMatricule;
	}
	/**
	 * [HistoriqueDetail.numeroMatricule] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param numeroMatricule the numeroMatricule to set
	 */
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}
	/**
	 * [HistoriqueDetail.individuNomLatin] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the individuNomLatin
	 */
	public String getIndividuNomLatin() {
		return individuNomLatin;
	}
	/**
	 * [HistoriqueDetail.individuNomLatin] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param individuNomLatin the individuNomLatin to set
	 */
	public void setIndividuNomLatin(String individuNomLatin) {
		this.individuNomLatin = individuNomLatin;
	}
	/**
	 * [HistoriqueDetail.individuPrenomLatin] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @return the individuPrenomLatin
	 */
	public String getIndividuPrenomLatin() {
		return individuPrenomLatin;
	}
	/**
	 * [HistoriqueDetail.individuPrenomLatin] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:18:02
	 * @param individuPrenomLatin the individuPrenomLatin to set
	 */
	public void setIndividuPrenomLatin(String individuPrenomLatin) {
		this.individuPrenomLatin = individuPrenomLatin;
	}
	/**
	 * [HistoriqueDetail.codeEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:20:21
	 * @return the codeEtablissement
	 */
	public String getCodeEtablissement() {
		return codeEtablissement;
	}
	/**
	 * [HistoriqueDetail.codeEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:20:21
	 * @param codeEtablissement the codeEtablissement to set
	 */
	public void setCodeEtablissement(String codeEtablissement) {
		this.codeEtablissement = codeEtablissement;
	}
	/**
	 * [HistoriqueDetail.codeFonction] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:20:21
	 * @return the codeFonction
	 */
	public String getCodeFonction() {
		return codeFonction;
	}
	/**
	 * [HistoriqueDetail.codeFonction] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:20:21
	 * @param codeFonction the codeFonction to set
	 */
	public void setCodeFonction(String codeFonction) {
		this.codeFonction = codeFonction;
	}
	/**
	 * [HistoriqueDetail.codeUser] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:20:21
	 * @return the codeUser
	 */
	public String getCodeUser() {
		return codeUser;
	}
	/**
	 * [HistoriqueDetail.codeUser] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:20:21
	 * @param codeUser the codeUser to set
	 */
	public void setCodeUser(String codeUser) {
		this.codeUser = codeUser;
	}
	/**
	 * [HistoriqueDetail.dateAction] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:20:21
	 * @return the dateAction
	 */
	public Date getDateAction() {
		return dateAction;
	}
	/**
	 * [HistoriqueDetail.dateAction] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:20:21
	 * @param dateAction the dateAction to set
	 */
	public void setDateAction(Date dateAction) {
		this.dateAction = dateAction;
	}
	/**
	 * [HistoriqueDetail.heureAction] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:20:21
	 * @return the heureAction
	 */
	public Date getHeureAction() {
		return heureAction;
	}
	/**
	 * [HistoriqueDetail.heureAction] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  14:20:21
	 * @param heureAction the heureAction to set
	 */
	public void setHeureAction(Date heureAction) {
		this.heureAction = heureAction;
	}
	/**
	 * [HistoriqueDetail.matriculeBac] Getter 
	 * @author MAKERRI Sofiane on : 24 juil. 2014  17:02:48
	 * @return the matriculeBac
	 */
	public String getMatriculeBac() {
		return matriculeBac;
	}
	/**
	 * [HistoriqueDetail.matriculeBac] Setter 
	 * @author MAKERRI Sofiane on : 24 juil. 2014  17:02:48
	 * @param matriculeBac the matriculeBac to set
	 */
	public void setMatriculeBac(String matriculeBac) {
		this.matriculeBac = matriculeBac;
	}
	
	

}
