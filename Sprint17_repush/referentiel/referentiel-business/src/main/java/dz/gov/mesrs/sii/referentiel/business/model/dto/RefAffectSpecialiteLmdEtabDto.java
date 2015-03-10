package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.util.Date;

public class RefAffectSpecialiteLmdEtabDto {

	private int id;
	private Date date;
	private String observation;

	/****SpecialiteLMD******/
	private String identifiantSpecialiteLMD;
	private Integer idSpecialiteLMD;
	private String lcSpecialite;
	private String lcSpecialiteArabe;
	private String llSpecialite;
	private String llSpecialiteArabe;
	private String descriptionSpecialite;
	private String descriptionArabeSpecialite;

	/*****Etablissement*****/
	private String identifiantEtablissement;
	private Integer idEtablissement;
	private String lcEtablissementArabe;
	private String lcEtablissementLatin;
	private String llEtablissementArabe;
	private String llEtablissementLatin;

	
	/***RefGroupe*/
	private Integer idGroupe;
	private String lcGroupe;
	private String lcGroupeArabe;
	private String llGroupe;
	private String llGroupeArabe;
	
	/**
	 * [RefAffectDomLmdEtabDto.id] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * [RefAffectDomLmdEtabDto.id] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * [RefAffectDomLmdEtabDto.date] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * [RefAffectDomLmdEtabDto.date] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * [RefAffectDomLmdEtabDto.observation] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}
	/**
	 * [RefAffectDomLmdEtabDto.observation] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}
	/**
	 * [RefAffectDomLmdEtabDto.identifiantSpecialiteLMD] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the identifiantSpecialiteLMD
	 */
	public String getIdentifiantSpecialiteLMD() {
		return identifiantSpecialiteLMD;
	}
	/**
	 * [RefAffectDomLmdEtabDto.identifiantSpecialiteLMD] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param identifiantSpecialiteLMD the identifiantSpecialiteLMD to set
	 */
	public void setIdentifiantSpecialiteLMD(String identifiantSpecialiteLMD) {
		this.identifiantSpecialiteLMD = identifiantSpecialiteLMD;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcSpecialite] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the lcSpecialite
	 */
	public String getLcSpecialite() {
		return lcSpecialite;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcSpecialite] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param lcSpecialite the lcSpecialite to set
	 */
	public void setLcSpecialite(String lcSpecialite) {
		this.lcSpecialite = lcSpecialite;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcSpecialiteArabe] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the lcSpecialiteArabe
	 */
	public String getLcSpecialiteArabe() {
		return lcSpecialiteArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcSpecialiteArabe] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param lcSpecialiteArabe the lcSpecialiteArabe to set
	 */
	public void setLcSpecialiteArabe(String lcSpecialiteArabe) {
		this.lcSpecialiteArabe = lcSpecialiteArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llSpecialite] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the llSpecialite
	 */
	public String getLlSpecialite() {
		return llSpecialite;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llSpecialite] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param llSpecialite the llSpecialite to set
	 */
	public void setLlSpecialite(String llSpecialite) {
		this.llSpecialite = llSpecialite;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llSpecialiteArabe] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the llSpecialiteArabe
	 */
	public String getLlSpecialiteArabe() {
		return llSpecialiteArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llSpecialiteArabe] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param llSpecialiteArabe the llSpecialiteArabe to set
	 */
	public void setLlSpecialiteArabe(String llSpecialiteArabe) {
		this.llSpecialiteArabe = llSpecialiteArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.descriptionSpecialite] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the descriptionSpecialite
	 */
	public String getDescriptionSpecialite() {
		return descriptionSpecialite;
	}
	/**
	 * [RefAffectDomLmdEtabDto.descriptionSpecialite] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param descriptionSpecialite the descriptionSpecialite to set
	 */
	public void setDescriptionSpecialite(String descriptionSpecialite) {
		this.descriptionSpecialite = descriptionSpecialite;
	}
	/**
	 * [RefAffectDomLmdEtabDto.descriptionArabeSpecialite] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the descriptionArabeSpecialite
	 */
	public String getDescriptionArabeSpecialite() {
		return descriptionArabeSpecialite;
	}
	/**
	 * [RefAffectDomLmdEtabDto.descriptionArabeSpecialite] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param descriptionArabeSpecialite the descriptionArabeSpecialite to set
	 */
	public void setDescriptionArabeSpecialite(String descriptionArabeSpecialite) {
		this.descriptionArabeSpecialite = descriptionArabeSpecialite;
	}
	/**
	 * [RefAffectDomLmdEtabDto.identifiantEtablissement] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the identifiantEtablissement
	 */
	public String getIdentifiantEtablissement() {
		return identifiantEtablissement;
	}
	/**
	 * [RefAffectDomLmdEtabDto.identifiantEtablissement] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param identifiantEtablissement the identifiantEtablissement to set
	 */
	public void setIdentifiantEtablissement(String identifiantEtablissement) {
		this.identifiantEtablissement = identifiantEtablissement;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcEtablissementArabe] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the lcEtablissementArabe
	 */
	public String getLcEtablissementArabe() {
		return lcEtablissementArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcEtablissementArabe] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param lcEtablissementArabe the lcEtablissementArabe to set
	 */
	public void setLcEtablissementArabe(String lcEtablissementArabe) {
		this.lcEtablissementArabe = lcEtablissementArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcEtablissementLatin] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the lcEtablissementLatin
	 */
	public String getLcEtablissementLatin() {
		return lcEtablissementLatin;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcEtablissementLatin] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param lcEtablissementLatin the lcEtablissementLatin to set
	 */
	public void setLcEtablissementLatin(String lcEtablissementLatin) {
		this.lcEtablissementLatin = lcEtablissementLatin;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llEtablissementArabe] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the llEtablissementArabe
	 */
	public String getLlEtablissementArabe() {
		return llEtablissementArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llEtablissementArabe] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param llEtablissementArabe the llEtablissementArabe to set
	 */
	public void setLlEtablissementArabe(String llEtablissementArabe) {
		this.llEtablissementArabe = llEtablissementArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llEtablissementLatin] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the llEtablissementLatin
	 */
	public String getLlEtablissementLatin() {
		return llEtablissementLatin;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llEtablissementLatin] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param llEtablissementLatin the llEtablissementLatin to set
	 */
	public void setLlEtablissementLatin(String llEtablissementLatin) {
		this.llEtablissementLatin = llEtablissementLatin;
	}
	/**
	 * [RefAffectDomLmdEtabDto.idSpecialiteLMD] Getter 
	 * @author MAKERRI Sofiane on : 15 aout 2014  16:22:48
	 * @return the idSpecialiteLMD
	 */
	public Integer getIdSpecialiteLMD() {
		return idSpecialiteLMD;
	}
	/**
	 * [RefAffectDomLmdEtabDto.idSpecialiteLMD] Setter 
	 * @author MAKERRI Sofiane on : 15 aout 2014  16:22:48
	 * @param idSpecialiteLMD the idSpecialiteLMD to set
	 */
	public void setIdSpecialiteLMD(Integer idSpecialiteLMD) {
		this.idSpecialiteLMD = idSpecialiteLMD;
	}
	/**
	 * [RefAffectDomLmdEtabDto.idEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 15 aout 2014  16:32:45
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}
	/**
	 * [RefAffectDomLmdEtabDto.idEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 15 aout 2014  16:32:45
	 * @param idEtablissement the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}
	/**
	 * [RefAffectDomLmdEtabDto.idGroupe] Getter 
	 * @author BELBRIK Oualid on : 18 aout 2014  16:35:50
	 * @return the idGroupe
	 */
	public Integer getIdGroupe() {
		return idGroupe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.idGroupe] Setter 
	 * @author BELBRIK Oualid on : 18 aout 2014  16:35:50
	 * @param idGroupe the idGroupe to set
	 */
	public void setIdGroupe(Integer idGroupe) {
		this.idGroupe = idGroupe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcGroupe] Getter 
	 * @author BELBRIK Oualid on : 18 aout 2014  16:35:50
	 * @return the lcGroupe
	 */
	public String getLcGroupe() {
		return lcGroupe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcGroupe] Setter 
	 * @author BELBRIK Oualid on : 18 aout 2014  16:35:50
	 * @param lcGroupe the lcGroupe to set
	 */
	public void setLcGroupe(String lcGroupe) {
		this.lcGroupe = lcGroupe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcGroupeArabe] Getter 
	 * @author BELBRIK Oualid on : 18 aout 2014  16:35:50
	 * @return the lcGroupeArabe
	 */
	public String getLcGroupeArabe() {
		return lcGroupeArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcGroupeArabe] Setter 
	 * @author BELBRIK Oualid on : 18 aout 2014  16:35:50
	 * @param lcGroupeArabe the lcGroupeArabe to set
	 */
	public void setLcGroupeArabe(String lcGroupeArabe) {
		this.lcGroupeArabe = lcGroupeArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llGroupe] Getter 
	 * @author BELBRIK Oualid on : 18 aout 2014  16:35:50
	 * @return the llGroupe
	 */
	public String getLlGroupe() {
		return llGroupe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llGroupe] Setter 
	 * @author BELBRIK Oualid on : 18 aout 2014  16:35:50
	 * @param llGroupe the llGroupe to set
	 */
	public void setLlGroupe(String llGroupe) {
		this.llGroupe = llGroupe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llGroupeArabe] Getter 
	 * @author BELBRIK Oualid on : 18 aout 2014  16:35:50
	 * @return the llGroupeArabe
	 */
	public String getLlGroupeArabe() {
		return llGroupeArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llGroupeArabe] Setter 
	 * @author BELBRIK Oualid on : 18 aout 2014  16:35:50
	 * @param llGroupeArabe the llGroupeArabe to set
	 */
	public void setLlGroupeArabe(String llGroupeArabe) {
		this.llGroupeArabe = llGroupeArabe;
	}
	
	
		
	
}
