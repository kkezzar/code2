package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.util.Date;

public class RefAffectDomLmdEtabDto {

	private int id;
	private Date date;
	private String observation;
	/****domaineLMD******/
	private String identifiantDomaineLMD;
	private Integer idDomaineLMD;
	private String lcDomaine;
	private String lcDomaineArabe;
	private String llDomaine;
	private String llDomaineArabe;
	private String descriptionDomaine;
	private String descriptionArabeDomaine;

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
	
	/***RefStructure*/
	private Integer idStructure;
	private String identifiantStructure;
	private String lcStructure;
	private String lcStructureArabe;
	private String llStructure;
	private String llStructureArabe;
	
	/**
	 * [RefAffectDomLmdEtabDto.id] Getter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * [RefAffectDomLmdEtabDto.id] Setter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * [RefAffectDomLmdEtabDto.date] Getter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * [RefAffectDomLmdEtabDto.date] Setter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * [RefAffectDomLmdEtabDto.observation] Getter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}
	/**
	 * [RefAffectDomLmdEtabDto.observation] Setter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}
	/**
	 * [RefAffectDomLmdEtabDto.identifiantDomaineLMD] Getter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @return the identifiantDomaineLMD
	 */
	public String getIdentifiantDomaineLMD() {
		return identifiantDomaineLMD;
	}
	/**
	 * [RefAffectDomLmdEtabDto.identifiantDomaineLMD] Setter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @param identifiantDomaineLMD the identifiantDomaineLMD to set
	 */
	public void setIdentifiantDomaineLMD(String identifiantDomaineLMD) {
		this.identifiantDomaineLMD = identifiantDomaineLMD;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcDomaine] Getter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @return the lcDomaine
	 */
	public String getLcDomaine() {
		return lcDomaine;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcDomaine] Setter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @param lcDomaine the lcDomaine to set
	 */
	public void setLcDomaine(String lcDomaine) {
		this.lcDomaine = lcDomaine;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcDomaineArabe] Getter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @return the lcDomaineArabe
	 */
	public String getLcDomaineArabe() {
		return lcDomaineArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcDomaineArabe] Setter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @param lcDomaineArabe the lcDomaineArabe to set
	 */
	public void setLcDomaineArabe(String lcDomaineArabe) {
		this.lcDomaineArabe = lcDomaineArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llDomaine] Getter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @return the llDomaine
	 */
	public String getLlDomaine() {
		return llDomaine;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llDomaine] Setter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @param llDomaine the llDomaine to set
	 */
	public void setLlDomaine(String llDomaine) {
		this.llDomaine = llDomaine;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llDomaineArabe] Getter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @return the llDomaineArabe
	 */
	public String getLlDomaineArabe() {
		return llDomaineArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llDomaineArabe] Setter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @param llDomaineArabe the llDomaineArabe to set
	 */
	public void setLlDomaineArabe(String llDomaineArabe) {
		this.llDomaineArabe = llDomaineArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.descriptionDomaine] Getter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @return the descriptionDomaine
	 */
	public String getDescriptionDomaine() {
		return descriptionDomaine;
	}
	/**
	 * [RefAffectDomLmdEtabDto.descriptionDomaine] Setter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @param descriptionDomaine the descriptionDomaine to set
	 */
	public void setDescriptionDomaine(String descriptionDomaine) {
		this.descriptionDomaine = descriptionDomaine;
	}
	/**
	 * [RefAffectDomLmdEtabDto.descriptionArabeDomaine] Getter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @return the descriptionArabeDomaine
	 */
	public String getDescriptionArabeDomaine() {
		return descriptionArabeDomaine;
	}
	/**
	 * [RefAffectDomLmdEtabDto.descriptionArabeDomaine] Setter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @param descriptionArabeDomaine the descriptionArabeDomaine to set
	 */
	public void setDescriptionArabeDomaine(String descriptionArabeDomaine) {
		this.descriptionArabeDomaine = descriptionArabeDomaine;
	}
	/**
	 * [RefAffectDomLmdEtabDto.identifiantEtablissement] Getter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @return the identifiantEtablissement
	 */
	public String getIdentifiantEtablissement() {
		return identifiantEtablissement;
	}
	/**
	 * [RefAffectDomLmdEtabDto.identifiantEtablissement] Setter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @param identifiantEtablissement the identifiantEtablissement to set
	 */
	public void setIdentifiantEtablissement(String identifiantEtablissement) {
		this.identifiantEtablissement = identifiantEtablissement;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcEtablissementArabe] Getter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @return the lcEtablissementArabe
	 */
	public String getLcEtablissementArabe() {
		return lcEtablissementArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcEtablissementArabe] Setter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @param lcEtablissementArabe the lcEtablissementArabe to set
	 */
	public void setLcEtablissementArabe(String lcEtablissementArabe) {
		this.lcEtablissementArabe = lcEtablissementArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcEtablissementLatin] Getter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @return the lcEtablissementLatin
	 */
	public String getLcEtablissementLatin() {
		return lcEtablissementLatin;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcEtablissementLatin] Setter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @param lcEtablissementLatin the lcEtablissementLatin to set
	 */
	public void setLcEtablissementLatin(String lcEtablissementLatin) {
		this.lcEtablissementLatin = lcEtablissementLatin;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llEtablissementArabe] Getter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @return the llEtablissementArabe
	 */
	public String getLlEtablissementArabe() {
		return llEtablissementArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llEtablissementArabe] Setter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @param llEtablissementArabe the llEtablissementArabe to set
	 */
	public void setLlEtablissementArabe(String llEtablissementArabe) {
		this.llEtablissementArabe = llEtablissementArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llEtablissementLatin] Getter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @return the llEtablissementLatin
	 */
	public String getLlEtablissementLatin() {
		return llEtablissementLatin;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llEtablissementLatin] Setter 
	 * @author BELBRIK Oualid on : 20 avr. 2014  11:33:45
	 * @param llEtablissementLatin the llEtablissementLatin to set
	 */
	public void setLlEtablissementLatin(String llEtablissementLatin) {
		this.llEtablissementLatin = llEtablissementLatin;
	}
	/**
	 * [RefAffectDomLmdEtabDto.idDomaineLMD] Getter 
	 * @author MAKERRI Sofiane on : 15 mai 2014  16:22:48
	 * @return the idDomaineLMD
	 */
	public Integer getIdDomaineLMD() {
		return idDomaineLMD;
	}
	/**
	 * [RefAffectDomLmdEtabDto.idDomaineLMD] Setter 
	 * @author MAKERRI Sofiane on : 15 mai 2014  16:22:48
	 * @param idDomaineLMD the idDomaineLMD to set
	 */
	public void setIdDomaineLMD(Integer idDomaineLMD) {
		this.idDomaineLMD = idDomaineLMD;
	}
	/**
	 * [RefAffectDomLmdEtabDto.idEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 15 mai 2014  16:32:45
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}
	/**
	 * [RefAffectDomLmdEtabDto.idEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 15 mai 2014  16:32:45
	 * @param idEtablissement the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}
	/**
	 * [RefAffectDomLmdEtabDto.idGroupe] Getter 
	 * @author BELBRIK Oualid on : 18 mai 2014  16:35:50
	 * @return the idGroupe
	 */
	public Integer getIdGroupe() {
		return idGroupe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.idGroupe] Setter 
	 * @author BELBRIK Oualid on : 18 mai 2014  16:35:50
	 * @param idGroupe the idGroupe to set
	 */
	public void setIdGroupe(Integer idGroupe) {
		this.idGroupe = idGroupe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcGroupe] Getter 
	 * @author BELBRIK Oualid on : 18 mai 2014  16:35:50
	 * @return the lcGroupe
	 */
	public String getLcGroupe() {
		return lcGroupe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcGroupe] Setter 
	 * @author BELBRIK Oualid on : 18 mai 2014  16:35:50
	 * @param lcGroupe the lcGroupe to set
	 */
	public void setLcGroupe(String lcGroupe) {
		this.lcGroupe = lcGroupe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcGroupeArabe] Getter 
	 * @author BELBRIK Oualid on : 18 mai 2014  16:35:50
	 * @return the lcGroupeArabe
	 */
	public String getLcGroupeArabe() {
		return lcGroupeArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcGroupeArabe] Setter 
	 * @author BELBRIK Oualid on : 18 mai 2014  16:35:50
	 * @param lcGroupeArabe the lcGroupeArabe to set
	 */
	public void setLcGroupeArabe(String lcGroupeArabe) {
		this.lcGroupeArabe = lcGroupeArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llGroupe] Getter 
	 * @author BELBRIK Oualid on : 18 mai 2014  16:35:50
	 * @return the llGroupe
	 */
	public String getLlGroupe() {
		return llGroupe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llGroupe] Setter 
	 * @author BELBRIK Oualid on : 18 mai 2014  16:35:50
	 * @param llGroupe the llGroupe to set
	 */
	public void setLlGroupe(String llGroupe) {
		this.llGroupe = llGroupe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llGroupeArabe] Getter 
	 * @author BELBRIK Oualid on : 18 mai 2014  16:35:50
	 * @return the llGroupeArabe
	 */
	public String getLlGroupeArabe() {
		return llGroupeArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llGroupeArabe] Setter 
	 * @author BELBRIK Oualid on : 18 mai 2014  16:35:50
	 * @param llGroupeArabe the llGroupeArabe to set
	 */
	public void setLlGroupeArabe(String llGroupeArabe) {
		this.llGroupeArabe = llGroupeArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.idStructure] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:05
	 * @return the idStructure
	 */
	public Integer getIdStructure() {
		return idStructure;
	}
	/**
	 * [RefAffectDomLmdEtabDto.idStructure] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:05
	 * @param idStructure the idStructure to set
	 */
	public void setIdStructure(Integer idStructure) {
		this.idStructure = idStructure;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcStructure] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:05
	 * @return the lcStructure
	 */
	public String getLcStructure() {
		return lcStructure;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcStructure] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:05
	 * @param lcStructure the lcStructure to set
	 */
	public void setLcStructure(String lcStructure) {
		this.lcStructure = lcStructure;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcStructureArabe] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:05
	 * @return the lcStructureArabe
	 */
	public String getLcStructureArabe() {
		return lcStructureArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcStructureArabe] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:05
	 * @param lcStructureArabe the lcStructureArabe to set
	 */
	public void setLcStructureArabe(String lcStructureArabe) {
		this.lcStructureArabe = lcStructureArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llStructure] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:05
	 * @return the llStructure
	 */
	public String getLlStructure() {
		return llStructure;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llStructure] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:05
	 * @param llStructure the llStructure to set
	 */
	public void setLlStructure(String llStructure) {
		this.llStructure = llStructure;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llStructureArabe] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:05
	 * @return the llStructureArabe
	 */
	public String getLlStructureArabe() {
		return llStructureArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llStructureArabe] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:05
	 * @param llStructureArabe the llStructureArabe to set
	 */
	public void setLlStructureArabe(String llStructureArabe) {
		this.llStructureArabe = llStructureArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.identifiantStructure] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:50:26
	 * @return the identifiantStructure
	 */
	public String getIdentifiantStructure() {
		return identifiantStructure;
	}
	/**
	 * [RefAffectDomLmdEtabDto.identifiantStructure] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:50:26
	 * @param identifiantStructure the identifiantStructure to set
	 */
	public void setIdentifiantStructure(String identifiantStructure) {
		this.identifiantStructure = identifiantStructure;
	}
	
	
		
	
}
