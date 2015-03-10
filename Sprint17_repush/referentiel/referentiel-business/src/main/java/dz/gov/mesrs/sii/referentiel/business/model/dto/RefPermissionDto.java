/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto.java] 
 * @author MAKERRI Sofiane on : 3 mars 2014  10:26:45
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaine;

/**
 * @author MAKERRI Sofiane on : 3 mars 2014 10:26:45
 */
@XmlRootElement(name = "RefPermissionDto")
public class RefPermissionDto implements Serializable {
	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:38
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Boolean creer;
	private Boolean modifier;
	private Boolean supprimer;
	private Boolean consulter;
	private Boolean allAccess;
	private String idfModel;
	private Integer idfAffectationModel;
	/************** refFonction **************/
	private Integer idFonction;
	private String idfFonction;
	private String nomFonction;
	private String nomLatinFonction;
	private String nomArabeFonction;
	private String urlFonction;
	private Integer rangFonction;
	private Boolean actionFonction;
	/*********** periode **************/
	private Integer idPeriode;
	private String idfPeriode;
	private String nomPeriode;
	private Date dateDebutPeriode;
	private Date dateFinPeriode;
	private Boolean periodique;
	
	/************* refModule ****************/
	private Integer idModule;
	private RefDomaine refDomaine;
	private String idfModule;
	private String nomModule;
	private String nomLatinModule;
	private String nomArabeModule;
	private int rangModule;
	
	private String iconModule;
	private String tooltipModule;
	private String descriptionModule;
	
	/************* refDomaine ****************/
	private Integer idDomaine;
	private String idfDomaine;
	private String nomDomaine;
	private String nomLatinDomaine;
	private String nomArabeDomaine;
	private Integer rangDomaine;
	/************* refSubDomaine ****************/
	private Integer idSubDomaine;
	private String idfSubDomaine;
	private String nomSubDomaine;
	private String nomLatinSubDomaine;
	private String nomArabeSubDomaine;
	private Integer rangSubDomaine;
	/************* RefAffectation **************/
	private Integer idfAffectation;
	private Integer idRole;
	private String roleCode;
	private String roleLlFr;
	private String roleLlAr;
	private String roleLcFr;
	private String roleLcAr;
	private Boolean roleStatus;
	/************ RefIndividu ***********/
	private String idfIndividu;

	/************ RefGroupe ***********/
	private String idfGroupe;

	/************ refStructure ***********/
	private String idfStructure;

	/**
	 * [RefPermissionDto.RefPermissionDto()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:26:45
	 */
	public RefPermissionDto() {

	}

	/**
	 * [RefPermissionDto.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefPermissionDto.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefPermissionDto.creer] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the creer
	 */
	public Boolean getCreer() {
		return creer;
	}

	/**
	 * [RefPermissionDto.creer] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param creer
	 *            the creer to set
	 */
	public void setCreer(Boolean creer) {
		if (consulter != null && supprimer != null && creer != null
				&& modifier != null) {
			setAllAccess(consulter && supprimer && creer && modifier);
		}
		this.creer = creer;
	}

	/**
	 * [RefPermissionDto.modifier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the modifier
	 */
	public Boolean getModifier() {
		return modifier;
	}

	/**
	 * [RefPermissionDto.modifier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param modifier
	 *            the modifier to set
	 */
	public void setModifier(Boolean modifier) {
		if (consulter != null && supprimer != null && creer != null
				&& modifier != null) {
			setAllAccess(consulter && supprimer && creer && modifier);
		}
		this.modifier = modifier;
	}

	/**
	 * [RefPermissionDto.supprimer] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the supprimer
	 */
	public Boolean getSupprimer() {
		return supprimer;
	}

	/**
	 * [RefPermissionDto.supprimer] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param supprimer
	 *            the supprimer to set
	 */
	public void setSupprimer(Boolean supprimer) {
		if (consulter != null && supprimer != null && creer != null
				&& modifier != null) {
			setAllAccess(consulter && supprimer && creer && modifier);
		}
		this.supprimer = supprimer;
	}

	/**
	 * [RefPermissionDto.consulter] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the consulter
	 */
	public Boolean getConsulter() {
		return consulter;
	}

	/**
	 * [RefPermissionDto.consulter] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param consulter
	 *            the consulter to set
	 */
	public void setConsulter(Boolean consulter) {
		if (consulter != null && supprimer != null && creer != null
				&& modifier != null) {
			setAllAccess(consulter && supprimer && creer && modifier);
		}
		this.consulter = consulter;
	}

	/**
	 * [RefPermissionDto.idFonction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the idFonction
	 */
	public Integer getIdFonction() {
		return idFonction;
	}

	/**
	 * [RefPermissionDto.idFonction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param idFonction
	 *            the idFonction to set
	 */
	public void setIdFonction(Integer idFonction) {
		this.idFonction = idFonction;
	}

	/**
	 * [RefPermissionDto.idfFonction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the idfFonction
	 */
	public String getIdfFonction() {
		return idfFonction;
	}

	/**
	 * [RefPermissionDto.idfFonction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param idfFonction
	 *            the idfFonction to set
	 */
	public void setIdfFonction(String idfFonction) {
		this.idfFonction = idfFonction;
	}

	/**
	 * [RefPermissionDto.nomFonction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the nomFonction
	 */
	public String getNomFonction() {
		if(nomFonction == null) {
			nomFonction = nomLatinFonction;
		}
		return nomFonction;
	}

	/**
	 * [RefPermissionDto.nomFonction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param nomFonction
	 *            the nomFonction to set
	 */
	public void setNomFonction(String nomFonction) {
		this.nomFonction = nomFonction;
	}

	/**
	 * [RefPermissionDto.urlFonction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the urlFonction
	 */
	public String getUrlFonction() {
		return urlFonction;
	}

	/**
	 * [RefPermissionDto.urlFonction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param urlFonction
	 *            the urlFonction to set
	 */
	public void setUrlFonction(String urlFonction) {
		this.urlFonction = urlFonction;
	}

	/**
	 * [RefPermissionDto.rangFonction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the rangFonction
	 */
	public Integer getRangFonction() {
		return rangFonction;
	}

	/**
	 * [RefPermissionDto.rangFonction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param rangFonction
	 *            the rangFonction to set
	 */
	public void setRangFonction(Integer rangFonction) {
		this.rangFonction = rangFonction;
	}

	/**
	 * [RefPermissionDto.actionFonction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the actionFonction
	 */
	public Boolean getActionFonction() {
		return actionFonction;
	}

	/**
	 * [RefPermissionDto.actionFonction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param actionFonction
	 *            the actionFonction to set
	 */
	public void setActionFonction(Boolean actionFonction) {
		this.actionFonction = actionFonction;
	}

	/**
	 * [RefPermissionDto.idModule] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the idModule
	 */
	public Integer getIdModule() {
		return idModule;
	}

	/**
	 * [RefPermissionDto.idModule] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param idModule
	 *            the idModule to set
	 */
	public void setIdModule(Integer idModule) {
		this.idModule = idModule;
	}

	/**
	 * [RefPermissionDto.refDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the refDomaine
	 */
	public RefDomaine getRefDomaine() {
		return refDomaine;
	}

	/**
	 * [RefPermissionDto.refDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param refDomaine
	 *            the refDomaine to set
	 */
	public void setRefDomaine(RefDomaine refDomaine) {
		this.refDomaine = refDomaine;
	}

	/**
	 * [RefPermissionDto.idfModule] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the idfModule
	 */
	public String getIdfModule() {
		return idfModule;
	}

	/**
	 * [RefPermissionDto.idfModule] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param idfModule
	 *            the idfModule to set
	 */
	public void setIdfModule(String idfModule) {
		this.idfModule = idfModule;
	}

	/**
	 * [RefPermissionDto.nomModule] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the nomModule
	 */
	public String getNomModule() {
		return nomModule;
	}

	/**
	 * [RefPermissionDto.nomModule] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param nomModule
	 *            the nomModule to set
	 */
	public void setNomModule(String nomModule) {
		this.nomModule = nomModule;
	}

	/**
	 * [RefPermissionDto.rangModule] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the rangModule
	 */
	public int getRangModule() {
		return rangModule;
	}

	/**
	 * [RefPermissionDto.rangModule] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param rangModule
	 *            the rangModule to set
	 */
	public void setRangModule(int rangModule) {
		this.rangModule = rangModule;
	}

	/**
	 * [RefPermissionDto.idDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the idDomaine
	 */
	public Integer getIdDomaine() {
		return idDomaine;
	}

	/**
	 * [RefPermissionDto.idDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param idDomaine
	 *            the idDomaine to set
	 */
	public void setIdDomaine(Integer idDomaine) {
		this.idDomaine = idDomaine;
	}

	/**
	 * [RefPermissionDto.idfDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the idfDomaine
	 */
	public String getIdfDomaine() {
		return idfDomaine;
	}

	/**
	 * [RefPermissionDto.idfDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param idfDomaine
	 *            the idfDomaine to set
	 */
	public void setIdfDomaine(String idfDomaine) {
		this.idfDomaine = idfDomaine;
	}

	/**
	 * [RefPermissionDto.nomDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the nomDomaine
	 */
	public String getNomDomaine() {
		return nomDomaine;
	}

	/**
	 * [RefPermissionDto.nomDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param nomDomaine
	 *            the nomDomaine to set
	 */
	public void setNomDomaine(String nomDomaine) {
		this.nomDomaine = nomDomaine;
	}

	/**
	 * [RefPermissionDto.rangDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the rangDomaine
	 */
	public Integer getRangDomaine() {
		return rangDomaine;
	}

	/**
	 * [RefPermissionDto.rangDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param rangDomaine
	 *            the rangDomaine to set
	 */
	public void setRangDomaine(Integer rangDomaine) {
		this.rangDomaine = rangDomaine;
	}

	/**
	 * [RefPermissionDto.idSubDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the idSubDomaine
	 */
	public Integer getIdSubDomaine() {
		return idSubDomaine;
	}

	/**
	 * [RefPermissionDto.idSubDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param idSubDomaine
	 *            the idSubDomaine to set
	 */
	public void setIdSubDomaine(Integer idSubDomaine) {
		this.idSubDomaine = idSubDomaine;
	}

	/**
	 * [RefPermissionDto.idfSubDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the idfSubDomaine
	 */
	public String getIdfSubDomaine() {
		return idfSubDomaine;
	}

	/**
	 * [RefPermissionDto.idfSubDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param idfSubDomaine
	 *            the idfSubDomaine to set
	 */
	public void setIdfSubDomaine(String idfSubDomaine) {
		this.idfSubDomaine = idfSubDomaine;
	}

	/**
	 * [RefPermissionDto.nomSubDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the nomSubDomaine
	 */
	public String getNomSubDomaine() {
		return nomSubDomaine;
	}

	/**
	 * [RefPermissionDto.nomSubDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param nomSubDomaine
	 *            the nomSubDomaine to set
	 */
	public void setNomSubDomaine(String nomSubDomaine) {
		this.nomSubDomaine = nomSubDomaine;
	}

	/**
	 * [RefPermissionDto.rangSubDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the rangSubDomaine
	 */
	public Integer getRangSubDomaine() {
		return rangSubDomaine;
	}

	/**
	 * [RefPermissionDto.rangSubDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param rangSubDomaine
	 *            the rangSubDomaine to set
	 */
	public void setRangSubDomaine(Integer rangSubDomaine) {
		this.rangSubDomaine = rangSubDomaine;
	}

	/**
	 * [RefPermissionDto.idfAffectation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the idfAffectation
	 */
	public Integer getIdfAffectation() {
		return idfAffectation;
	}

	/**
	 * [RefPermissionDto.idfAffectation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param idfAffectation
	 *            the idfAffectation to set
	 */
	public void setIdfAffectation(Integer idfAffectation) {
		this.idfAffectation = idfAffectation;
	}

	/**
	 * [RefPermissionDto.roleLlFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the roleLlFr
	 */
	public String getRoleLlFr() {
		return roleLlFr;
	}

	/**
	 * [RefPermissionDto.roleLlFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param roleLlFr
	 *            the roleLlFr to set
	 */
	public void setRoleLlFr(String roleLlFr) {
		this.roleLlFr = roleLlFr;
	}

	/**
	 * [RefPermissionDto.roleLlAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the roleLlAr
	 */
	public String getRoleLlAr() {
		return roleLlAr;
	}

	/**
	 * [RefPermissionDto.roleLlAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param roleLlAr
	 *            the roleLlAr to set
	 */
	public void setRoleLlAr(String roleLlAr) {
		this.roleLlAr = roleLlAr;
	}

	/**
	 * [RefPermissionDto.roleLcFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the roleLcFr
	 */
	public String getRoleLcFr() {
		return roleLcFr;
	}

	/**
	 * [RefPermissionDto.roleLcFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param roleLcFr
	 *            the roleLcFr to set
	 */
	public void setRoleLcFr(String roleLcFr) {
		this.roleLcFr = roleLcFr;
	}

	/**
	 * [RefPermissionDto.roleLcAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the roleLcAr
	 */
	public String getRoleLcAr() {
		return roleLcAr;
	}

	/**
	 * [RefPermissionDto.roleLcAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param roleLcAr
	 *            the roleLcAr to set
	 */
	public void setRoleLcAr(String roleLcAr) {
		this.roleLcAr = roleLcAr;
	}

	/**
	 * [RefPermissionDto.roleStatus] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the roleStatus
	 */
	public Boolean getRoleStatus() {
		return roleStatus;
	}

	/**
	 * [RefPermissionDto.roleStatus] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param roleStatus
	 *            the roleStatus to set
	 */
	public void setRoleStatus(Boolean roleStatus) {
		this.roleStatus = roleStatus;
	}

	/**
	 * [RefPermissionDto.idfIndividu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the idfIndividu
	 */
	public String getIdfIndividu() {
		return idfIndividu;
	}

	/**
	 * [RefPermissionDto.idfIndividu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param idfIndividu
	 *            the idfIndividu to set
	 */
	public void setIdfIndividu(String idfIndividu) {
		this.idfIndividu = idfIndividu;
	}

	/**
	 * [RefPermissionDto.idfGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the idfGroupe
	 */
	public String getIdfGroupe() {
		return idfGroupe;
	}

	/**
	 * [RefPermissionDto.idfGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param idfGroupe
	 *            the idfGroupe to set
	 */
	public void setIdfGroupe(String idfGroupe) {
		this.idfGroupe = idfGroupe;
	}

	/**
	 * [RefPermissionDto.idfStructure] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @return the idfStructure
	 */
	public String getIdfStructure() {
		return idfStructure;
	}

	/**
	 * [RefPermissionDto.idfStructure] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 10:38:28
	 * @param idfStructure
	 *            the idfStructure to set
	 */
	public void setIdfStructure(String idfStructure) {
		this.idfStructure = idfStructure;
	}

	/**
	 * [RefPermissionDto.allAccess] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 mars 2014 11:32:57
	 * @return the allAccess
	 */
	public Boolean getAllAccess() {
		// allAccess = creer && modifier && supprimer && consulter;
		return allAccess;
	}

	/**
	 * [RefPermissionDto.allAccess] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 mars 2014 11:32:57
	 * @param allAccess
	 *            the allAccess to set
	 */
	public void setAllAccess(Boolean allAccess) {
		this.allAccess = allAccess;
	}

	/**
	 * [RefPermissionDto.idfModel] Getter 
	 * @author MAKERRI Sofiane on : 10 mars 2014  16:42:07
	 * @return the idfModel
	 */
	public String getIdfModel() {
		return idfModel;
	}

	/**
	 * [RefPermissionDto.idfModel] Setter 
	 * @author MAKERRI Sofiane on : 10 mars 2014  16:42:07
	 * @param idfModel the idfModel to set
	 */
	public void setIdfModel(String idfModel) {
		this.idfModel = idfModel;
	}

	/**
	 * [RefPermissionDto.idfAffectationModel] Getter 
	 * @author MAKERRI Sofiane on : 11 mars 2014  08:40:57
	 * @return the idfAffectationModel
	 */
	public Integer getIdfAffectationModel() {
		return idfAffectationModel;
	}

	/**
	 * [RefPermissionDto.idfAffectationModel] Setter 
	 * @author MAKERRI Sofiane on : 11 mars 2014  08:40:57
	 * @param idfAffectationModel the idfAffectationModel to set
	 */
	public void setIdfAffectationModel(Integer idfAffectationModel) {
		this.idfAffectationModel = idfAffectationModel;
	}

	/**
	 * [RefPermissionDto.idRole] Getter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  10:08:08
	 * @return the idRole
	 */
	public Integer getIdRole() {
		return idRole;
	}

	/**
	 * [RefPermissionDto.idRole] Setter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  10:08:08
	 * @param idRole the idRole to set
	 */
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	/**
	 * [RefPermissionDto.roleCode] Getter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  10:08:08
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * [RefPermissionDto.roleCode] Setter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  10:08:08
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * [RefPermissionDto.idPeriode] Getter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  12:15:03
	 * @return the idPeriode
	 */
	public Integer getIdPeriode() {
		return idPeriode;
	}

	/**
	 * [RefPermissionDto.idPeriode] Setter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  12:15:03
	 * @param idPeriode the idPeriode to set
	 */
	public void setIdPeriode(Integer idPeriode) {
		this.idPeriode = idPeriode;
	}

	/**
	 * [RefPermissionDto.idfPeriode] Getter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  12:15:03
	 * @return the idfPeriode
	 */
	public String getIdfPeriode() {
		return idfPeriode;
	}

	/**
	 * [RefPermissionDto.idfPeriode] Setter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  12:15:03
	 * @param idfPeriode the idfPeriode to set
	 */
	public void setIdfPeriode(String idfPeriode) {
		this.idfPeriode = idfPeriode;
	}

	/**
	 * [RefPermissionDto.nomPeriode] Getter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  12:15:03
	 * @return the nomPeriode
	 */
	public String getNomPeriode() {
		return nomPeriode;
	}

	/**
	 * [RefPermissionDto.nomPeriode] Setter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  12:15:03
	 * @param nomPeriode the nomPeriode to set
	 */
	public void setNomPeriode(String nomPeriode) {
		this.nomPeriode = nomPeriode;
	}

	/**
	 * [RefPermissionDto.dateDebutPeriode] Getter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  12:15:03
	 * @return the dateDebutPeriode
	 */
	public Date getDateDebutPeriode() {
		return dateDebutPeriode;
	}

	/**
	 * [RefPermissionDto.dateDebutPeriode] Setter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  12:15:03
	 * @param dateDebutPeriode the dateDebutPeriode to set
	 */
	public void setDateDebutPeriode(Date dateDebutPeriode) {
		this.dateDebutPeriode = dateDebutPeriode;
	}

	/**
	 * [RefPermissionDto.dateFinPeriode] Getter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  12:15:03
	 * @return the dateFinPeriode
	 */
	public Date getDateFinPeriode() {
		return dateFinPeriode;
	}

	/**
	 * [RefPermissionDto.dateFinPeriode] Setter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  12:15:03
	 * @param dateFinPeriode the dateFinPeriode to set
	 */
	public void setDateFinPeriode(Date dateFinPeriode) {
		this.dateFinPeriode = dateFinPeriode;
	}

	/**
	 * [RefPermissionDto.periodique] Getter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  12:15:03
	 * @return the periodique
	 */
	public Boolean getPeriodique() {
		return periodique;
	}

	/**
	 * [RefPermissionDto.periodique] Setter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  12:15:03
	 * @param periodique the periodique to set
	 */
	public void setPeriodique(Boolean periodique) {
		this.periodique = periodique;
	}

	/**
	 * [RefPermissionDto.nomLatinFonction] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @return the nomLatinFonction
	 */
	public String getNomLatinFonction() {
		return nomLatinFonction;
	}

	/**
	 * [RefPermissionDto.nomLatinFonction] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @param nomLatinFonction the nomLatinFonction to set
	 */
	public void setNomLatinFonction(String nomLatinFonction) {
		this.nomLatinFonction = nomLatinFonction;
	}

	/**
	 * [RefPermissionDto.nomArabeFonction] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @return the nomArabeFonction
	 */
	public String getNomArabeFonction() {
		return nomArabeFonction;
	}

	/**
	 * [RefPermissionDto.nomArabeFonction] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @param nomArabeFonction the nomArabeFonction to set
	 */
	public void setNomArabeFonction(String nomArabeFonction) {
		this.nomArabeFonction = nomArabeFonction;
	}

	/**
	 * [RefPermissionDto.nomLatinModule] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @return the nomLatinModule
	 */
	public String getNomLatinModule() {
		return nomLatinModule;
	}

	/**
	 * [RefPermissionDto.nomLatinModule] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @param nomLatinModule the nomLatinModule to set
	 */
	public void setNomLatinModule(String nomLatinModule) {
		this.nomLatinModule = nomLatinModule;
	}

	/**
	 * [RefPermissionDto.nomArabeModule] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @return the nomArabeModule
	 */
	public String getNomArabeModule() {
		return nomArabeModule;
	}

	/**
	 * [RefPermissionDto.nomArabeModule] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @param nomArabeModule the nomArabeModule to set
	 */
	public void setNomArabeModule(String nomArabeModule) {
		this.nomArabeModule = nomArabeModule;
	}

	/**
	 * [RefPermissionDto.nomLatinDomaine] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @return the nomLatinDomaine
	 */
	public String getNomLatinDomaine() {
		return nomLatinDomaine;
	}

	/**
	 * [RefPermissionDto.nomLatinDomaine] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @param nomLatinDomaine the nomLatinDomaine to set
	 */
	public void setNomLatinDomaine(String nomLatinDomaine) {
		this.nomLatinDomaine = nomLatinDomaine;
	}

	/**
	 * [RefPermissionDto.nomArabeDomaine] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @return the nomArabeDomaine
	 */
	public String getNomArabeDomaine() {
		return nomArabeDomaine;
	}

	/**
	 * [RefPermissionDto.nomArabeDomaine] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @param nomArabeDomaine the nomArabeDomaine to set
	 */
	public void setNomArabeDomaine(String nomArabeDomaine) {
		this.nomArabeDomaine = nomArabeDomaine;
	}

	/**
	 * [RefPermissionDto.nomLatinSubDomaine] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @return the nomLatinSubDomaine
	 */
	public String getNomLatinSubDomaine() {
		return nomLatinSubDomaine;
	}

	/**
	 * [RefPermissionDto.nomLatinSubDomaine] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @param nomLatinSubDomaine the nomLatinSubDomaine to set
	 */
	public void setNomLatinSubDomaine(String nomLatinSubDomaine) {
		this.nomLatinSubDomaine = nomLatinSubDomaine;
	}

	/**
	 * [RefPermissionDto.nomArabeSubDomaine] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @return the nomArabeSubDomaine
	 */
	public String getNomArabeSubDomaine() {
		return nomArabeSubDomaine;
	}

	/**
	 * [RefPermissionDto.nomArabeSubDomaine] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:19:07
	 * @param nomArabeSubDomaine the nomArabeSubDomaine to set
	 */
	public void setNomArabeSubDomaine(String nomArabeSubDomaine) {
		this.nomArabeSubDomaine = nomArabeSubDomaine;
	}

	public String getIconModule() {
		return iconModule;
	}

	public void setIconModule(String iconModule) {
		this.iconModule = iconModule;
	}

	public String getTooltipModule() {
		return tooltipModule;
	}

	public void setTooltipModule(String tooltipModule) {
		this.tooltipModule = tooltipModule;
	}

	public String getDescriptionModule() {
		return descriptionModule;
	}

	public void setDescriptionModule(String descriptionModule) {
		this.descriptionModule = descriptionModule;
	}
	
	
	

}
