/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineDto.java] 
 * @author MAKERRI Sofiane on : 18 f�vr. 2014  09:36:25
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.util.List;

/**
 * @author MAKERRI Sofiane  on : 18 f�vr. 2014  09:36:25
 */
public class RefDomaineDto {
     
	private Integer id;
	private String identifiant;
	private String nom;
	private String nomArabe;
	private Integer rang;
	private Integer idDomaineParent;
	private String idfDomaineParent;
	private String nomDomaineParent;
	private String nomArabeDomaineParent;
	private List<RefDomaineDto> listSubDomaine;
	private boolean parent;
	private String helpTemplateName;
	/**
	 * [RefDomaineDto.parent] Getter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  17:08:48
	 * @return the parent
	 */
	public boolean isParent() {
		parent = true;
		return parent;
	}

	/**
	 * [RefDomaineDto.parent] Setter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  17:08:48
	 * @param parent the parent to set
	 */
	public void setParent(boolean parent) {
		this.parent = parent;
	}

	/**
	 * [RefDomaineDto.RefDomaineDto()] Constructor
	 * @author MAKERRI Sofiane  on : 18 f�vr. 2014  09:36:25	
	 */
	public RefDomaineDto() {
		// TODO Auto-generated constructor stub
	}
	
	public RefDomaineDto(int id, String identifiant, String nom, int rang) {
		this.id = id;
		this.identifiant = identifiant;
		this.nom = nom;
		this.rang = rang;
	}

	
	/**
	 * [RefDomaineDto.identifiant] Getter 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014  09:37:46
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}
	/**
	 * [RefDomaineDto.identifiant] Setter 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014  09:37:46
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	/**
	 * [RefDomaineDto.nom] Getter 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014  09:37:46
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * [RefDomaineDto.nom] Setter 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014  09:37:46
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * [RefDomaineDto.rang] Getter 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014  09:37:46
	 * @return the rang
	 */
	public Integer getRang() {
		return rang;
	}
	/**
	 * [RefDomaineDto.rang] Setter 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014  09:37:46
	 * @param rang the rang to set
	 */
	public void setRang(Integer rang) {
		this.rang = rang;
	}
	

	/**
	 * [RefDomaineDto.id] Getter 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014  12:44:07
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * [RefDomaineDto.id] Setter 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014  12:44:07
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * [RefDomaineDto.idDomaineParent] Getter 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014  16:59:51
	 * @return the idDomaineParent
	 */
	public Integer getIdDomaineParent() {
		return idDomaineParent;
	}
	/**
	 * [RefDomaineDto.idDomaineParent] Setter 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014  16:59:51
	 * @param idDomaineParent the idDomaineParent to set
	 */
	public void setIdDomaineParent(Integer idDomaineParent) {
		this.idDomaineParent = idDomaineParent;
	}
	/**
	 * [RefDomaineDto.idfDomaineParent] Getter 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014  16:59:51
	 * @return the idfDomaineParent
	 */
	public String getIdfDomaineParent() {
		return idfDomaineParent;
	}
	/**
	 * [RefDomaineDto.idfDomaineParent] Setter 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014  16:59:51
	 * @param idfDomaineParent the idfDomaineParent to set
	 */
	public void setIdfDomaineParent(String idfDomaineParent) {
		this.idfDomaineParent = idfDomaineParent;
	}
	/**
	 * [RefDomaineDto.nomDomaineParent] Getter 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014  16:59:51
	 * @return the nomDomaineParent
	 */
	public String getNomDomaineParent() {
		return nomDomaineParent;
	}
	/**
	 * [RefDomaineDto.nomDomaineParent] Setter 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014  16:59:51
	 * @param nomDomaineParent the nomDomaineParent to set
	 */
	public void setNomDomaineParent(String nomDomaineParent) {
		this.nomDomaineParent = nomDomaineParent;
	}
	/**
	 * [RefDomaineDto.listSubDomaine] Getter 
	 * @author MAKERRI Sofiane on : 19 f�vr. 2014  10:51:36
	 * @return the listSubDomaine
	 */
	public List<RefDomaineDto> getListSubDomaine() {
		return listSubDomaine;
	}
	/**
	 * [RefDomaineDto.listSubDomaine] Setter 
	 * @author MAKERRI Sofiane on : 19 f�vr. 2014  10:51:36
	 * @param listSubDomaine the listSubDomaine to set
	 */
	public void setListSubDomaine(List<RefDomaineDto> listSubDomaine) {
		this.listSubDomaine = listSubDomaine;
	}

	/**
	 * [RefDomaineDto.nomArabe] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  08:59:17
	 * @return the nomArabe
	 */
	public String getNomArabe() {
		return nomArabe;
	}

	/**
	 * [RefDomaineDto.nomArabe] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  08:59:17
	 * @param nomArabe the nomArabe to set
	 */
	public void setNomArabe(String nomArabe) {
		this.nomArabe = nomArabe;
	}

	/**
	 * [RefDomaineDto.nomArabeDomaineParent] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:03:42
	 * @return the nomArabeDomaineParent
	 */
	public String getNomArabeDomaineParent() {
		return nomArabeDomaineParent;
	}

	/**
	 * [RefDomaineDto.nomArabeDomaineParent] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:03:42
	 * @param nomArabeDomaineParent the nomArabeDomaineParent to set
	 */
	public void setNomArabeDomaineParent(String nomArabeDomaineParent) {
		this.nomArabeDomaineParent = nomArabeDomaineParent;
	}
	
	public String getHelpTemplateName() {
		return helpTemplateName;
	}

	public void setHelpTemplateName(String helpTemplateName) {
		this.helpTemplateName = helpTemplateName;
	}

}
