/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto.java] 
 * @author MAKERRI Sofiane on : 27 f�vr. 2014  13:57:26
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author MAKERRI Sofiane  on : 27 f�vr. 2014  13:57:26
 */
@XmlRootElement(name = "RefFonctionDto")
public class RefFonctionDto implements Serializable{

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 18 mars 2014  18:11:43
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String identifiant;
	private String nom;
	private String nomArabe;
	private Integer rang;
	private String url;
	private Boolean action;
	
	/*********fonction mère*******/
	private Integer idFonctionMere;
	private String idfFonctionMere;
	private String nomFonctionMere;
	private String nomArabeFonctionMere;
	private String urlFonctionMere;
	private Integer rangFonctionMere;
	
	/******* module **************/
	private Integer idModule;
	private String idfModule;
	private String nomModule;
	private String nomArabeModule;
	private Integer rangModule;
	private String iconModule;
	private String tooltipModule;
	private String descriptionModule;
	/******** Domaine-Parent **************/
	private Integer idParentDomaine;
	private String idfParentDomaine;
	private String nomParentDomaine;
	private String nomArabeParentDomaine;
	private Integer rangParentDomaine;
	/******** domaine ***********/
	private Integer idDomaine;
	private String idfDomaine;
	private String nomDomaine;
	private String nomArabeDomaine;
	private Integer rangDomaine;
	/********** periode *****************/
	private Integer idPeriode;
	private String idfPeriode;
	private String nomPeriode;
	private Date dateDebutPeriode;
	private Date dateFinPeriode;
	private Boolean periodique;
	//Aide en ligne
	private String helpAnchor;
	
	/**
	 * [RefFonctionDto.RefFonctionDto()] Constructor
	 * @author MAKERRI Sofiane  on : 27 f�vr. 2014  13:57:26	
	 */
	public RefFonctionDto() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * [RefFonctionDto.id] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * [RefFonctionDto.id] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * [RefFonctionDto.identifiant] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}


	/**
	 * [RefFonctionDto.identifiant] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}


	/**
	 * [RefFonctionDto.nom] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * [RefFonctionDto.nom] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * [RefFonctionDto.rang] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the rang
	 */
	public Integer getRang() {
		return rang;
	}


	/**
	 * [RefFonctionDto.rang] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param rang the rang to set
	 */
	public void setRang(Integer rang) {
		this.rang = rang;
	}
	
	


	/**
	 * [RefFonctionDto.url] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  16:31:22
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * [RefFonctionDto.url] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  16:31:22
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * [RefFonctionDto.action] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the action
	 */
	public Boolean getAction() {
		return action;
	}


	/**
	 * [RefFonctionDto.action] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param action the action to set
	 */
	public void setAction(Boolean action) {
		this.action = action;
	}


	/**
	 * [RefFonctionDto.idModule] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the idModule
	 */
	public Integer getIdModule() {
		return idModule;
	}


	/**
	 * [RefFonctionDto.idModule] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param idModule the idModule to set
	 */
	public void setIdModule(Integer idModule) {
		this.idModule = idModule;
	}


	/**
	 * [RefFonctionDto.idfModule] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the idfModule
	 */
	public String getIdfModule() {
		return idfModule;
	}


	/**
	 * [RefFonctionDto.idfModule] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param idfModule the idfModule to set
	 */
	public void setIdfModule(String idfModule) {
		this.idfModule = idfModule;
	}


	/**
	 * [RefFonctionDto.nomModule] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the nomModule
	 */
	public String getNomModule() {
		return nomModule;
	}


	/**
	 * [RefFonctionDto.nomModule] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param nomModule the nomModule to set
	 */
	public void setNomModule(String nomModule) {
		this.nomModule = nomModule;
	}


	/**
	 * [RefFonctionDto.rangModule] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the rangModule
	 */
	public Integer getRangModule() {
		return rangModule;
	}


	/**
	 * [RefFonctionDto.rangModule] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param rangModule the rangModule to set
	 */
	public void setRangModule(Integer rangModule) {
		this.rangModule = rangModule;
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


	/**
	 * [RefFonctionDto.idParentDomaine] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the idParentDomaine
	 */
	public Integer getIdParentDomaine() {
		return idParentDomaine;
	}


	/**
	 * [RefFonctionDto.idParentDomaine] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param idParentDomaine the idSubDomaine to set
	 */
	public void setIdParentDomaine(Integer idParentDomaine) {
		this.idParentDomaine = idParentDomaine;
	}


	/**
	 * [RefFonctionDto.idfParentDomaine] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the idfParentDomaine
	 */
	public String getIdfParentDomaine() {
		return idfParentDomaine;
	}


	/**
	 * [RefFonctionDto.idfParentDomaine] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param idfParentDomaine the idfParentDomaine to set
	 */
	public void setIdfParentDomaine(String idfParentDomaine) {
		this.idfParentDomaine = idfParentDomaine;
	}


	/**
	 * [RefFonctionDto.nomParentDomaine] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the nomParentDomaine
	 */
	public String getNomParentDomaine() {
		return nomParentDomaine;
	}


	/**
	 * [RefFonctionDto.nomParentDomaine] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param nomParentDomaine the nomParentDomaine to set
	 */
	public void setNomParentDomaine(String nomParentDomaine) {
		this.nomParentDomaine = nomParentDomaine;
	}

	/**
	 * [RefFonctionDto.rangParentDomaine] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:14:12
	 * @return the rangParentDomaine
	 */
	public Integer getRangParentDomaine() {
		return rangParentDomaine;
	}


	/**
	 * [RefFonctionDto.rangParentDomaine] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:14:12
	 * @param rangParentDomaine the rangParentDomaine to set
	 */
	public void setRangParentDomaine(Integer rangParentDomaine) {
		this.rangParentDomaine = rangParentDomaine;
	}


	/**
	 * [RefFonctionDto.idDomaine] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the idDomaine
	 */
	public Integer getIdDomaine() {
		return idDomaine;
	}


	/**
	 * [RefFonctionDto.idDomaine] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param idDomaine the idDomaine to set
	 */
	public void setIdDomaine(Integer idDomaine) {
		this.idDomaine = idDomaine;
	}


	/**
	 * [RefFonctionDto.idfDomaine] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the idfDomaine
	 */
	public String getIdfDomaine() {
		return idfDomaine;
	}


	/**
	 * [RefFonctionDto.idfDomaine] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param idfDomaine the idfDomaine to set
	 */
	public void setIdfDomaine(String idfDomaine) {
		this.idfDomaine = idfDomaine;
	}


	/**
	 * [RefFonctionDto.nomDomaine] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the nomDomaine
	 */
	public String getNomDomaine() {
		return nomDomaine;
	}


	/**
	 * [RefFonctionDto.nomDomaine] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param nomDomaine the nomDomaine to set
	 */
	public void setNomDomaine(String nomDomaine) {
		this.nomDomaine = nomDomaine;
	}


	/**
	 * [RefFonctionDto.rangDomaine] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @return the rangDomaine
	 */
	public Integer getRangDomaine() {
		return rangDomaine;
	}


	/**
	 * [RefFonctionDto.rangDomaine] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:01:28
	 * @param rangDomaine the rangDomaine to set
	 */
	public void setRangDomaine(Integer rangDomaine) {
		this.rangDomaine = rangDomaine;
	}


	/**
	 * [RefFonctionDto.idFonctionMere] Getter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:11:34
	 * @return the idFonctionMere
	 */
	public Integer getIdFonctionMere() {
		return idFonctionMere;
	}


	/**
	 * [RefFonctionDto.idFonctionMere] Setter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:11:34
	 * @param idFonctionMere the idFonctionMere to set
	 */
	public void setIdFonctionMere(Integer idFonctionMere) {
		this.idFonctionMere = idFonctionMere;
	}


	/**
	 * [RefFonctionDto.idfFonctionMere] Getter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:11:34
	 * @return the idfFonctionMere
	 */
	public String getIdfFonctionMere() {
		return idfFonctionMere;
	}


	/**
	 * [RefFonctionDto.idfFonctionMere] Setter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:11:34
	 * @param idfFonctionMere the idfFonctionMere to set
	 */
	public void setIdfFonctionMere(String idfFonctionMere) {
		this.idfFonctionMere = idfFonctionMere;
	}


	/**
	 * [RefFonctionDto.nomFonctionMere] Getter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:11:34
	 * @return the nomFonctionMere
	 */
	public String getNomFonctionMere() {
		return nomFonctionMere;
	}


	/**
	 * [RefFonctionDto.nomFonctionMere] Setter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:11:34
	 * @param nomFonctionMere the nomFonctionMere to set
	 */
	public void setNomFonctionMere(String nomFonctionMere) {
		this.nomFonctionMere = nomFonctionMere;
	}


	/**
	 * [RefFonctionDto.urlFonctionMere] Getter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  10:37:20
	 * @return the urlFonctionMere
	 */
	public String getUrlFonctionMere() {
		return urlFonctionMere;
	}


	/**
	 * [RefFonctionDto.urlFonctionMere] Setter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  10:37:20
	 * @param urlFonctionMere the urlFonctionMere to set
	 */
	public void setUrlFonctionMere(String urlFonctionMere) {
		this.urlFonctionMere = urlFonctionMere;
	}


	/**
	 * [RefFonctionDto.rangFonctionMere] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  13:47:42
	 * @return the rangFonctionMere
	 */
	public Integer getRangFonctionMere() {
		return rangFonctionMere;
	}


	/**
	 * [RefFonctionDto.rangFonctionMere] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  13:47:42
	 * @param rangFonctionMere the rangFonctionMere to set
	 */
	public void setRangFonctionMere(Integer rangFonctionMere) {
		this.rangFonctionMere = rangFonctionMere;
	}


	/**
	 * [RefFonctionDto.idPeriode] Getter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  11:40:37
	 * @return the idPeriode
	 */
	public Integer getIdPeriode() {
		return idPeriode;
	}


	/**
	 * [RefFonctionDto.idPeriode] Setter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  11:40:37
	 * @param idPeriode the idPeriode to set
	 */
	public void setIdPeriode(Integer idPeriode) {
		this.idPeriode = idPeriode;
	}


	/**
	 * [RefFonctionDto.idfPeriode] Getter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  11:40:37
	 * @return the idfPeriode
	 */
	public String getIdfPeriode() {
		return idfPeriode;
	}


	/**
	 * [RefFonctionDto.idfPeriode] Setter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  11:40:37
	 * @param idfPeriode the idfPeriode to set
	 */
	public void setIdfPeriode(String idfPeriode) {
		this.idfPeriode = idfPeriode;
	}


	/**
	 * [RefFonctionDto.nomPeriode] Getter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  11:40:37
	 * @return the nomPeriode
	 */
	public String getNomPeriode() {
		return nomPeriode;
	}


	/**
	 * [RefFonctionDto.nomPeriode] Setter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  11:40:37
	 * @param nomPeriode the nomPeriode to set
	 */
	public void setNomPeriode(String nomPeriode) {
		this.nomPeriode = nomPeriode;
	}


	/**
	 * [RefFonctionDto.dateDebutPeriode] Getter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  11:40:37
	 * @return the dateDebutPeriode
	 */
	public Date getDateDebutPeriode() {
		return dateDebutPeriode;
	}


	/**
	 * [RefFonctionDto.dateDebutPeriode] Setter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  11:40:37
	 * @param dateDebutPeriode the dateDebutPeriode to set
	 */
	public void setDateDebutPeriode(Date dateDebutPeriode) {
		this.dateDebutPeriode = dateDebutPeriode;
	}


	/**
	 * [RefFonctionDto.dateFinPeriode] Getter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  11:40:37
	 * @return the dateFinPeriode
	 */
	public Date getDateFinPeriode() {
		return dateFinPeriode;
	}


	/**
	 * [RefFonctionDto.dateFinPeriode] Setter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  11:40:37
	 * @param dateFinPeriode the dateFinPeriode to set
	 */
	public void setDateFinPeriode(Date dateFinPeriode) {
		this.dateFinPeriode = dateFinPeriode;
	}


	/**
	 * [RefFonctionDto.periodique] Getter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  11:40:37
	 * @return the periodique
	 */
	public Boolean getPeriodique() {
		return periodique;
	}


	/**
	 * [RefFonctionDto.periodique] Setter 
	 * @author MAKERRI Sofiane on : 3 avr. 2014  11:40:37
	 * @param periodique the periodique to set
	 */
	public void setPeriodique(Boolean periodique) {
		this.periodique = periodique;
	}


	/**
	 * [RefFonctionDto.nomArabe] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:02:18
	 * @return the nomArabe
	 */
	public String getNomArabe() {
		return nomArabe;
	}


	/**
	 * [RefFonctionDto.nomArabe] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:02:18
	 * @param nomArabe the nomArabe to set
	 */
	public void setNomArabe(String nomArabe) {
		this.nomArabe = nomArabe;
	}


	/**
	 * [RefFonctionDto.nomArabeFonctionMere] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:02:18
	 * @return the nomArabeFonctionMere
	 */
	public String getNomArabeFonctionMere() {
		return nomArabeFonctionMere;
	}


	/**
	 * [RefFonctionDto.nomArabeFonctionMere] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:02:18
	 * @param nomArabeFonctionMere the nomArabeFonctionMere to set
	 */
	public void setNomArabeFonctionMere(String nomArabeFonctionMere) {
		this.nomArabeFonctionMere = nomArabeFonctionMere;
	}


	/**
	 * [RefFonctionDto.nomArabeModule] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:02:18
	 * @return the nomArabeModule
	 */
	public String getNomArabeModule() {
		return nomArabeModule;
	}


	/**
	 * [RefFonctionDto.nomArabeModule] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:02:18
	 * @param nomArabeModule the nomArabeModule to set
	 */
	public void setNomArabeModule(String nomArabeModule) {
		this.nomArabeModule = nomArabeModule;
	}


	/**
	 * [RefFonctionDto.nomArabeParentDomaine] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:02:18
	 * @return the nomArabeParentDomaine
	 */
	public String getNomArabeParentDomaine() {
		return nomArabeParentDomaine;
	}


	/**
	 * [RefFonctionDto.nomArabeParentDomaine] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:02:18
	 * @param nomArabeParentDomaine the nomArabeParentDomaine to set
	 */
	public void setNomArabeParentDomaine(String nomArabeParentDomaine) {
		this.nomArabeParentDomaine = nomArabeParentDomaine;
	}


	/**
	 * [RefFonctionDto.nomArabeDomaine] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:02:18
	 * @return the nomArabeDomaine
	 */
	public String getNomArabeDomaine() {
		return nomArabeDomaine;
	}


	/**
	 * [RefFonctionDto.nomArabeDomaine] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  09:02:18
	 * @param nomArabeDomaine the nomArabeDomaine to set
	 */
	public void setNomArabeDomaine(String nomArabeDomaine) {
		this.nomArabeDomaine = nomArabeDomaine;
	}


	public String getHelpAnchor() {
		return helpAnchor;
	}


	public void setHelpAnchor(String helpAnchor) {
		this.helpAnchor = helpAnchor;
	}
	
	
	
	

}
