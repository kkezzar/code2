package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author BELBRIK Oualid  on : 21 avr. 2014  10:06:12
 */
@XmlRootElement(name = "RefSpecialiteLmdDto")
public class RefSpecialiteLmdDto implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String identifiant;
	private String lcSpecialiteArabe;
	private String lcSpecialite;
	private String llSpecialiteArabe;
	private String llSpecialite;
	private String descriptionSpecialiteArabe;
	private String descriptionSpecialite;
	
	/*** Current Situation **/
	private Date dateSituation;
	private String llSituationAr;
	private String llSituationFr;
	
	/***** Filiere ******/
	private Integer idFiliere;
	private String identifiantFiliere;
	private String lcFiliere;
	private String lcFiliereArabe;
	private String llFiliere;
	private String llFiliereArabe;
	private Integer idDomaine;
	private String identifiantDomaine;
	private String lcDomaine;
	private String lcDomaineArabe;
	
	/**
	 * [RefSpecialiteLmdDto.id] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  11:57:37
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * [RefSpecialiteLmdDto.id] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  11:57:37
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * [RefSpecialiteLmdDto.identifiant] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}
	/**
	 * [RefSpecialiteLmdDto.identifiant] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	/**
	 * [RefSpecialiteLmdDto.lcSpecialiteArabe] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the lcSpecialiteArabe
	 */
	public String getLcSpecialiteArabe() {
		return lcSpecialiteArabe;
	}
	/**
	 * [RefSpecialiteLmdDto.lcSpecialiteArabe] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param lcSpecialiteArabe the lcSpecialiteArabe to set
	 */
	public void setLcSpecialiteArabe(String lcSpecialiteArabe) {
		this.lcSpecialiteArabe = lcSpecialiteArabe;
	}
	/**
	 * [RefSpecialiteLmdDto.lcSpecialite] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the lcSpecialite
	 */
	public String getLcSpecialite() {
		return lcSpecialite;
	}
	/**
	 * [RefSpecialiteLmdDto.lcSpecialite] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param lcSpecialite the lcSpecialite to set
	 */
	public void setLcSpecialite(String lcSpecialite) {
		this.lcSpecialite = lcSpecialite;
	}
	/**
	 * [RefSpecialiteLmdDto.llSpecialiteArabe] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the llSpecialiteArabe
	 */
	public String getLlSpecialiteArabe() {
		return llSpecialiteArabe;
	}
	/**
	 * [RefSpecialiteLmdDto.llSpecialiteArabe] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param llSpecialiteArabe the llSpecialiteArabe to set
	 */
	public void setLlSpecialiteArabe(String llSpecialiteArabe) {
		this.llSpecialiteArabe = llSpecialiteArabe;
	}
	/**
	 * [RefSpecialiteLmdDto.llSpecialite] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the llSpecialite
	 */
	public String getLlSpecialite() {
		return llSpecialite;
	}
	/**
	 * [RefSpecialiteLmdDto.llSpecialite] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param llSpecialite the llSpecialite to set
	 */
	public void setLlSpecialite(String llSpecialite) {
		this.llSpecialite = llSpecialite;
	}
	/**
	 * [RefSpecialiteLmdDto.descriptionSpecialiteArabe] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the descriptionSpecialiteArabe
	 */
	public String getDescriptionSpecialiteArabe() {
		return descriptionSpecialiteArabe;
	}
	/**
	 * [RefSpecialiteLmdDto.descriptionSpecialiteArabe] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param descriptionSpecialiteArabe the descriptionSpecialiteArabe to set
	 */
	public void setDescriptionSpecialiteArabe(String descriptionSpecialiteArabe) {
		this.descriptionSpecialiteArabe = descriptionSpecialiteArabe;
	}
	/**
	 * [RefSpecialiteLmdDto.descriptionSpecialite] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the descriptionSpecialite
	 */
	public String getDescriptionSpecialite() {
		return descriptionSpecialite;
	}
	/**
	 * [RefSpecialiteLmdDto.descriptionSpecialite] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param descriptionSpecialite the descriptionSpecialite to set
	 */
	public void setDescriptionSpecialite(String descriptionSpecialite) {
		this.descriptionSpecialite = descriptionSpecialite;
	}
	/**
	 * [RefSpecialiteLmdDto.dateSituation] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the dateSituation
	 */
	public Date getDateSituation() {
		return dateSituation;
	}
	/**
	 * [RefSpecialiteLmdDto.dateSituation] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param dateSituation the dateSituation to set
	 */
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}
	/**
	 * [RefSpecialiteLmdDto.llSituationAr] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the llSituationAr
	 */
	public String getLlSituationAr() {
		return llSituationAr;
	}
	/**
	 * [RefSpecialiteLmdDto.llSituationAr] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param llSituationAr the llSituationAr to set
	 */
	public void setLlSituationAr(String llSituationAr) {
		this.llSituationAr = llSituationAr;
	}
	/**
	 * [RefSpecialiteLmdDto.llSituationFr] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the llSituationFr
	 */
	public String getLlSituationFr() {
		return llSituationFr;
	}
	/**
	 * [RefSpecialiteLmdDto.llSituationFr] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param llSituationFr the llSituationFr to set
	 */
	public void setLlSituationFr(String llSituationFr) {
		this.llSituationFr = llSituationFr;
	}

	
	/**
	 * [RefSpecialiteLmdDto.lcFiliere] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the lcFiliere
	 */
	public String getLcFiliere() {
		return lcFiliere;
	}
	/**
	 * [RefSpecialiteLmdDto.lcFiliere] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param lcFiliere the lcFiliere to set
	 */
	public void setLcFiliere(String lcFiliere) {
		this.lcFiliere = lcFiliere;
	}
	/**
	 * [RefSpecialiteLmdDto.lcFiliereArabe] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the lcFiliereArabe
	 */
	public String getLcFiliereArabe() {
		return lcFiliereArabe;
	}
	/**
	 * [RefSpecialiteLmdDto.lcFiliereArabe] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param lcFiliereArabe the lcFiliereArabe to set
	 */
	public void setLcFiliereArabe(String lcFiliereArabe) {
		this.lcFiliereArabe = lcFiliereArabe;
	}
	/**
	 * [RefSpecialiteLmdDto.llFiliere] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the llFiliere
	 */
	public String getLlFiliere() {
		return llFiliere;
	}
	/**
	 * [RefSpecialiteLmdDto.llFiliere] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param llFiliere the llFiliere to set
	 */
	public void setLlFiliere(String llFiliere) {
		this.llFiliere = llFiliere;
	}
	/**
	 * [RefSpecialiteLmdDto.llFiliereArabe] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the llFiliereArabe
	 */
	public String getLlFiliereArabe() {
		return llFiliereArabe;
	}
	/**
	 * [RefSpecialiteLmdDto.llFiliereArabe] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param llFiliereArabe the llFiliereArabe to set
	 */
	public void setLlFiliereArabe(String llFiliereArabe) {
		this.llFiliereArabe = llFiliereArabe;
	}
	
	/**
	 * [RefSpecialiteLmdDto.lcDomaine] Getter 
	 * @author BELBRIK Oualid on : 24 avr. 2014  13:36:21
	 * @return the lcDomaine
	 */
	public String getLcDomaine() {
		return lcDomaine;
	}
	/**
	 * [RefSpecialiteLmdDto.lcDomaine] Setter 
	 * @author BELBRIK Oualid on : 24 avr. 2014  13:36:21
	 * @param lcDomaine the lcDomaine to set
	 */
	public void setLcDomaine(String lcDomaine) {
		this.lcDomaine = lcDomaine;
	}
	/**
	 * [RefSpecialiteLmdDto.lcDomaineArabe] Getter 
	 * @author BELBRIK Oualid on : 24 avr. 2014  13:36:21
	 * @return the lcDomaineArabe
	 */
	public String getLcDomaineArabe() {
		return lcDomaineArabe;
	}
	/**
	 * [RefSpecialiteLmdDto.lcDomaineArabe] Setter 
	 * @author BELBRIK Oualid on : 24 avr. 2014  13:36:21
	 * @param lcDomaineArabe the lcDomaineArabe to set
	 */
	public void setLcDomaineArabe(String lcDomaineArabe) {
		this.lcDomaineArabe = lcDomaineArabe;
	}
	/**
	 * [RefSpecialiteLmdDto.idFiliere] Getter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  08:45:23
	 * @return the idFiliere
	 */
	public Integer getIdFiliere() {
		return idFiliere;
	}
	/**
	 * [RefSpecialiteLmdDto.idFiliere] Setter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  08:45:23
	 * @param idFiliere the idFiliere to set
	 */
	public void setIdFiliere(Integer idFiliere) {
		this.idFiliere = idFiliere;
	}
	/**
	 * [RefSpecialiteLmdDto.idDomaine] Getter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  08:47:15
	 * @return the idDomaine
	 */
	public Integer getIdDomaine() {
		return idDomaine;
	}
	/**
	 * [RefSpecialiteLmdDto.idDomaine] Setter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  08:47:15
	 * @param idDomaine the idDomaine to set
	 */
	public void setIdDomaine(Integer idDomaine) {
		this.idDomaine = idDomaine;
	}
	/**
	 * [RefSpecialiteLmdDto.identifiantFiliere] Getter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  09:20:58
	 * @return the identifiantFiliere
	 */
	public String getIdentifiantFiliere() {
		return identifiantFiliere;
	}
	/**
	 * [RefSpecialiteLmdDto.identifiantFiliere] Setter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  09:20:58
	 * @param identifiantFiliere the identifiantFiliere to set
	 */
	public void setIdentifiantFiliere(String identifiantFiliere) {
		this.identifiantFiliere = identifiantFiliere;
	}
	/**
	 * [RefSpecialiteLmdDto.identifiantDomaine] Getter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  09:20:58
	 * @return the identifiantDomaine
	 */
	public String getIdentifiantDomaine() {
		return identifiantDomaine;
	}
	/**
	 * [RefSpecialiteLmdDto.identifiantDomaine] Setter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  09:20:58
	 * @param identifiantDomaine the identifiantDomaine to set
	 */
	public void setIdentifiantDomaine(String identifiantDomaine) {
		this.identifiantDomaine = identifiantDomaine;
	}
	
	

}
