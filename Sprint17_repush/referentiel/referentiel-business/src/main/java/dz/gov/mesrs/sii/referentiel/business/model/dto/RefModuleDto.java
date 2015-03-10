/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefModuleDto.java] 
 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:10:11
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;


/**
 * @author MAKERRI Sofiane  on : 23 f�vr. 2014  15:10:11
 */
public class RefModuleDto {

	private Integer id;
	private Integer idDomaine;
	private String identifiantDomaine;
	private String nomDomaine;
	private String nomArabeDomaine;
	private Integer rangDomaine;
	private Integer idDomaineParent;
	private String identifiantDomaineParent;
	private String nomDomaineParent;
	private String nomArabeDomaineParent;
	private Integer rangDomaineParent;
	private String identifiant;
	private String nom;
	private String nomArabe;
	private Integer rang;
	private boolean parent;
	
	private String icon;
	private String tooltip;
	private String description;
	private String helpPage;
	
	/**
	 * [RefModuleDto.RefModuleDto()] Constructor
	 * @author MAKERRI Sofiane  on : 23 f�vr. 2014  15:10:11	
	 */
	public RefModuleDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * [RefModuleDto.id] Getter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:11:26
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefModuleDto.id] Setter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:11:26
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefModuleDto.idDomaine] Getter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:11:26
	 * @return the idDomaine
	 */
	public Integer getIdDomaine() {
		return idDomaine;
	}

	/**
	 * [RefModuleDto.idDomaine] Setter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:11:26
	 * @param idDomaine the idDomaine to set
	 */
	public void setIdDomaine(Integer idDomaine) {
		this.idDomaine = idDomaine;
	}

	/**
	 * [RefModuleDto.nomDomaine] Getter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:11:26
	 * @return the nomDomaine
	 */
	public String getNomDomaine() {
		return nomDomaine;
	}

	/**
	 * [RefModuleDto.nomDomaine] Setter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:11:26
	 * @param nomDomaine the nomDomaine to set
	 */
	public void setNomDomaine(String nomDomaine) {
		this.nomDomaine = nomDomaine;
	}

	/**
	 * [RefModuleDto.identifiant] Getter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:11:26
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * [RefModuleDto.identifiant] Setter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:11:26
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * [RefModuleDto.nom] Getter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:11:26
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * [RefModuleDto.nom] Setter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:11:26
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * [RefModuleDto.rang] Getter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:11:26
	 * @return the rang
	 */
	public Integer getRang() {
		return rang;
	}

	/**
	 * [RefModuleDto.rang] Setter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:11:26
	 * @param rang the rang to set
	 */
	public void setRang(Integer rang) {
		this.rang = rang;
	}

	/**
	 * [RefModuleDto.identifiantDomaine] Getter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:21:14
	 * @return the identifiantDomaine
	 */
	public String getIdentifiantDomaine() {
		return identifiantDomaine;
	}

	/**
	 * [RefModuleDto.identifiantDomaine] Setter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:21:14
	 * @param identifiantDomaine the identifiantDomaine to set
	 */
	public void setIdentifiantDomaine(String identifiantDomaine) {
		this.identifiantDomaine = identifiantDomaine;
	}

	/**
	 * [RefModuleDto.rangDomaine] Getter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:45:27
	 * @return the rangDomaine
	 */
	public Integer getRangDomaine() {
		return rangDomaine;
	}

	/**
	 * [RefModuleDto.rangDomaine] Setter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:45:27
	 * @param rangDomaine the rangDomaine to set
	 */
	public void setRangDomaine(Integer rangDomaine) {
		this.rangDomaine = rangDomaine;
	}

	/**
	 * [RefModuleDto.parent] Getter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  17:09:27
	 * @return the parent
	 */
	public boolean isParent() {
		parent = false;
		return parent;
	}

	/**
	 * [RefModuleDto.parent] Setter 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014  17:09:27
	 * @param parent the parent to set
	 */
	public void setParent(boolean parent) {
		this.parent = parent;
	}

	/**
	 * [RefModuleDto.idDomaineParent] Getter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  08:16:20
	 * @return the idDomaineParent
	 */
	public Integer getIdDomaineParent() {
		return idDomaineParent;
	}

	/**
	 * [RefModuleDto.idDomaineParent] Setter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  08:16:20
	 * @param idDomaineParent the idDomaineParent to set
	 */
	public void setIdDomaineParent(Integer idDomaineParent) {
		this.idDomaineParent = idDomaineParent;
	}

	/**
	 * [RefModuleDto.identifiantDomaineParent] Getter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  08:16:20
	 * @return the identifiantDomaineParent
	 */
	public String getIdentifiantDomaineParent() {
		return identifiantDomaineParent;
	}

	/**
	 * [RefModuleDto.identifiantDomaineParent] Setter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  08:16:20
	 * @param identifiantDomaineParent the identifiantDomaineParent to set
	 */
	public void setIdentifiantDomaineParent(String identifiantDomaineParent) {
		this.identifiantDomaineParent = identifiantDomaineParent;
	}

	/**
	 * [RefModuleDto.nomDomaineParent] Getter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  08:16:20
	 * @return the nomDomaineParent
	 */
	public String getNomDomaineParent() {
		return nomDomaineParent;
	}

	/**
	 * [RefModuleDto.nomDomaineParent] Setter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  08:16:20
	 * @param nomDomaineParent the nomDomaineParent to set
	 */
	public void setNomDomaineParent(String nomDomaineParent) {
		this.nomDomaineParent = nomDomaineParent;
	}

	/**
	 * [RefModuleDto.rangDomaineParent] Getter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  08:16:20
	 * @return the rangDomaineParent
	 */
	public Integer getRangDomaineParent() {
		return rangDomaineParent;
	}

	/**
	 * [RefModuleDto.rangDomaineParent] Setter 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014  08:16:20
	 * @param rangDomaineParent the rangDomaineParent to set
	 */
	public void setRangDomaineParent(Integer rangDomaineParent) {
		this.rangDomaineParent = rangDomaineParent;
	}

	
	

	/**
	 * [RefModuleDto.nomArabeDomaine] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:20:01
	 * @return the nomArabeDomaine
	 */
	public String getNomArabeDomaine() {
		return nomArabeDomaine;
	}

	/**
	 * [RefModuleDto.nomArabeDomaine] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:20:01
	 * @param nomArabeDomaine the nomArabeDomaine to set
	 */
	public void setNomArabeDomaine(String nomArabeDomaine) {
		this.nomArabeDomaine = nomArabeDomaine;
	}

	/**
	 * [RefModuleDto.nomArabeDomaineParent] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:04:23
	 * @return the nomArabeDomaineParent
	 */
	public String getNomArabeDomaineParent() {
		return nomArabeDomaineParent;
	}

	/**
	 * [RefModuleDto.nomArabeDomaineParent] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:04:23
	 * @param nomArabeDomaineParent the nomArabeDomaineParent to set
	 */
	public void setNomArabeDomaineParent(String nomArabeDomaineParent) {
		this.nomArabeDomaineParent = nomArabeDomaineParent;
	}

	/**
	 * [RefModuleDto.nomArabe] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:00:14
	 * @return the nomArabe
	 */
	public String getNomArabe() {
		return nomArabe;
	}

	/**
	 * [RefModuleDto.nomArabe] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:00:14
	 * @param nomArabe the nomArabe to set
	 */
	public void setNomArabe(String nomArabe) {
		this.nomArabe = nomArabe;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String getHelpPage() {
		return helpPage;
	}
	
	public void setHelpPage(String helpPage) {
		this.helpPage = helpPage;
	}

}
