/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefHistoriqueDto.java] 
 * @author MAKERRI Sofiane on : 18 juin 2014  10:26:33
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * @author MAKERRI Sofiane on : 18 juin 2014 10:26:33
 */
@XmlRootElement(name = "RefHistoriqueDto")
public class RefHistoriqueDto implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:26:38
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer idEtablissement;
	private String codeEtablissement;
	private Integer idFonction;
	private String codeFonction;
	private String nomFonction;
	private Integer idCompte;
	private Integer idIndividu;
	private String nomLatinUser;
	private String prenomLatinUser;
	private String nomArabeUser;
	private String prenomArabeUser;
	private String codeUser;
	private String nomCompte;
	private Date dateAction;
	private Date heureAction;
	private Integer idOccurence;
	private Integer typeAction;
	private String libelleAction;
	private String nomEntite;

	/**
	 * [RefHistoriqueDto.RefHistoriqueDto()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:26:33
	 */
	public RefHistoriqueDto() {
		super();
	}

	/**
	 * [RefHistoriqueDto.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:28:34
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefHistoriqueDto.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:28:34
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefHistoriqueDto.idEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [RefHistoriqueDto.idEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @param idEtablissement
	 *            the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * [RefHistoriqueDto.codeEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @return the codeEtablissement
	 */
	public String getCodeEtablissement() {
		return codeEtablissement;
	}

	/**
	 * [RefHistoriqueDto.codeEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @param codeEtablissement
	 *            the codeEtablissement to set
	 */
	public void setCodeEtablissement(String codeEtablissement) {
		this.codeEtablissement = codeEtablissement;
	}

	/**
	 * [RefHistoriqueDto.idFonction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 11:57:56
	 * @return the idFonction
	 */
	public Integer getIdFonction() {
		return idFonction;
	}

	/**
	 * [RefHistoriqueDto.idFonction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 11:57:56
	 * @param idFonction
	 *            the idFonction to set
	 */
	public void setIdFonction(Integer idFonction) {
		this.idFonction = idFonction;
	}

	/**
	 * [RefHistoriqueDto.nomFonction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @return the nomFonction
	 */
	public String getNomFonction() {
		return nomFonction;
	}

	/**
	 * [RefHistoriqueDto.nomFonction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @param nomFonction
	 *            the nomFonction to set
	 */
	public void setNomFonction(String nomFonction) {
		this.nomFonction = nomFonction;
	}

	/**
	 * [RefHistoriqueDto.idCompte] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @return the idCompte
	 */
	public Integer getIdCompte() {
		return idCompte;
	}

	/**
	 * [RefHistoriqueDto.idCompte] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @param idCompte
	 *            the idCompte to set
	 */
	public void setIdCompte(Integer idCompte) {
		this.idCompte = idCompte;
	}

	

	/**
	 * [RefHistoriqueDto.dateAction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @return the dateAction
	 */
	public Date getDateAction() {
		return dateAction;
	}

	/**
	 * [RefHistoriqueDto.dateAction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @param dateAction
	 *            the dateAction to set
	 */
	public void setDateAction(Date dateAction) {
		this.dateAction = dateAction;
	}

	/**
	 * [RefHistoriqueDto.heureAction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @return the heureAction
	 */
	public Date getHeureAction() {
		return heureAction;
	}

	/**
	 * [RefHistoriqueDto.heureAction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @param heureAction
	 *            the heureAction to set
	 */
	public void setHeureAction(Date heureAction) {
		this.heureAction = heureAction;
	}

	/**
	 * [RefHistoriqueDto.idOccurence] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @return the idOccurence
	 */
	public Integer getIdOccurence() {
		return idOccurence;
	}

	/**
	 * [RefHistoriqueDto.idOccurence] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @param idOccurence
	 *            the idOccurence to set
	 */
	public void setIdOccurence(Integer idOccurence) {
		this.idOccurence = idOccurence;
	}

	/**
	 * [RefHistoriqueDto.typeAction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @return the typeAction
	 */
	public Integer getTypeAction() {
		return typeAction;
	}

	/**
	 * [RefHistoriqueDto.typeAction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 10:40:41
	 * @param typeAction
	 *            the typeAction to set
	 */
	public void setTypeAction(Integer typeAction) {
		this.typeAction = typeAction;
	}

	/**
	 * [RefHistoriqueDto.codeFonction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 15:35:01
	 * @return the codeFonction
	 */
	public String getCodeFonction() {
		return codeFonction;
	}

	/**
	 * [RefHistoriqueDto.codeFonction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 15:35:01
	 * @param codeFonction
	 *            the codeFonction to set
	 */
	public void setCodeFonction(String codeFonction) {
		this.codeFonction = codeFonction;
	}

	/**
	 * [RefHistoriqueDto.nomEntite] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 15:58:55
	 * @return the nomEntite
	 */
	public String getNomEntite() {
		return nomEntite;
	}

	/**
	 * [RefHistoriqueDto.nomEntite] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 15:58:55
	 * @param nomEntite
	 *            the nomEntite to set
	 */
	public void setNomEntite(String nomEntite) {
		this.nomEntite = nomEntite;
	}
	
	

	/**
	 * [RefHistoriqueDto.nomLatinUser] Getter 
	 * @author MAKERRI Sofiane on : 19 juin 2014  11:20:24
	 * @return the nomLatinUser
	 */
	public String getNomLatinUser() {
		return nomLatinUser;
	}

	/**
	 * [RefHistoriqueDto.nomLatinUser] Setter 
	 * @author MAKERRI Sofiane on : 19 juin 2014  11:20:24
	 * @param nomLatinUser the nomLatinUser to set
	 */
	public void setNomLatinUser(String nomLatinUser) {
		this.nomLatinUser = nomLatinUser;
	}

	/**
	 * [RefHistoriqueDto.prenomLatinUser] Getter 
	 * @author MAKERRI Sofiane on : 19 juin 2014  11:20:24
	 * @return the prenomLatinUser
	 */
	public String getPrenomLatinUser() {
		return prenomLatinUser;
	}

	/**
	 * [RefHistoriqueDto.prenomLatinUser] Setter 
	 * @author MAKERRI Sofiane on : 19 juin 2014  11:20:24
	 * @param prenomLatinUser the prenomLatinUser to set
	 */
	public void setPrenomLatinUser(String prenomLatinUser) {
		this.prenomLatinUser = prenomLatinUser;
	}

	/**
	 * [RefHistoriqueDto.nomArabeUser] Getter 
	 * @author MAKERRI Sofiane on : 19 juin 2014  11:20:24
	 * @return the nomArabeUser
	 */
	public String getNomArabeUser() {
		return nomArabeUser;
	}

	/**
	 * [RefHistoriqueDto.nomArabeUser] Setter 
	 * @author MAKERRI Sofiane on : 19 juin 2014  11:20:24
	 * @param nomArabeUser the nomArabeUser to set
	 */
	public void setNomArabeUser(String nomArabeUser) {
		this.nomArabeUser = nomArabeUser;
	}

	/**
	 * [RefHistoriqueDto.prenomArabeUser] Getter 
	 * @author MAKERRI Sofiane on : 19 juin 2014  11:20:24
	 * @return the prenomArabeUser
	 */
	public String getPrenomArabeUser() {
		return prenomArabeUser;
	}

	/**
	 * [RefHistoriqueDto.prenomArabeUser] Setter 
	 * @author MAKERRI Sofiane on : 19 juin 2014  11:20:24
	 * @param prenomArabeUser the prenomArabeUser to set
	 */
	public void setPrenomArabeUser(String prenomArabeUser) {
		this.prenomArabeUser = prenomArabeUser;
	}

	/**
	 * [RefHistoriqueDto.codeUser] Getter 
	 * @author MAKERRI Sofiane on : 19 juin 2014  11:27:34
	 * @return the codeUser
	 */
	public String getCodeUser() {
		return codeUser;
	}

	/**
	 * [RefHistoriqueDto.codeUser] Setter 
	 * @author MAKERRI Sofiane on : 19 juin 2014  11:27:34
	 * @param codeUser the codeUser to set
	 */
	public void setCodeUser(String codeUser) {
		this.codeUser = codeUser;
	}

	/**
	 * [RefHistoriqueDto.nomCompte] Getter 
	 * @author MAKERRI Sofiane on : 19 juin 2014  11:27:34
	 * @return the nomCompte
	 */
	public String getNomCompte() {
		return nomCompte;
	}

	/**
	 * [RefHistoriqueDto.nomCompte] Setter 
	 * @author MAKERRI Sofiane on : 19 juin 2014  11:27:34
	 * @param nomCompte the nomCompte to set
	 */
	public void setNomCompte(String nomCompte) {
		this.nomCompte = nomCompte;
	}

	/**
	 * [RefHistoriqueDto.libelleAction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 juin 2014 11:06:54
	 * @return the libelleAction
	 */
	public String getLibelleAction() {
		if (typeAction != null) {
			switch (typeAction) {
			case 0:
				libelleAction = UtilConstant.INSERT_ACTION_LIBELLE;
				break;
			case 1:
				libelleAction = UtilConstant.UPDATE_ACTION_LIBELLE;
				break;
			case 2:
				libelleAction = UtilConstant.DELETE_ACTION_LIBELLE;
				break;
			case 3:
				libelleAction = UtilConstant.SAVE_ACTION_LIBELLE;
				break;
			case 4:
				libelleAction = UtilConstant.VALIDATE_ACTION_LIBELLE;
				break;		
			default:
				break;
			}
		}
		return libelleAction;
	}

	/**
	 * [RefHistoriqueDto.libelleAction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 juin 2014 11:06:54
	 * @param libelleAction
	 *            the libelleAction to set
	 */
	public void setLibelleAction(String libelleAction) {
		this.libelleAction = libelleAction;
	}

	/**
	 * [RefHistoriqueDto.idIndividu] Getter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  11:20:13
	 * @return the idIndividu
	 */
	public Integer getIdIndividu() {
		return idIndividu;
	}

	/**
	 * [RefHistoriqueDto.idIndividu] Setter 
	 * @author MAKERRI Sofiane on : 22 juin 2014  11:20:13
	 * @param idIndividu the idIndividu to set
	 */
	public void setIdIndividu(Integer idIndividu) {
		this.idIndividu = idIndividu;
	}
	

}
