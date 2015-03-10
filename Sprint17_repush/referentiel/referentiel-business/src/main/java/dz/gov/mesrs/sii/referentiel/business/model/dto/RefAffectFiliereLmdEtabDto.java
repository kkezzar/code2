package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.util.Date;

public class RefAffectFiliereLmdEtabDto {

	private int id;
	private Date date;
	private String observation;

	/****FiliereLMD******/
	private String identifiantFiliereLMD;
	private Integer idFiliereLMD;
	private String lcFiliere;
	private String lcFiliereArabe;
	private String llFiliere;
	private String llFiliereArabe;
	private String descriptionFiliere;
	private String descriptionArabeFiliere;
	private String numArrete;

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
	
	/**RefDomaineLmd*/
	private Integer domaineId;
	
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
	 * [RefAffectDomLmdEtabDto.identifiantFiliereLMD] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the identifiantFiliereLMD
	 */
	public String getIdentifiantFiliereLMD() {
		return identifiantFiliereLMD;
	}
	/**
	 * [RefAffectDomLmdEtabDto.identifiantFiliereLMD] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param identifiantFiliereLMD the identifiantFiliereLMD to set
	 */
	public void setIdentifiantFiliereLMD(String identifiantFiliereLMD) {
		this.identifiantFiliereLMD = identifiantFiliereLMD;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcFiliere] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the lcFiliere
	 */
	public String getLcFiliere() {
		return lcFiliere;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcFiliere] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param lcFiliere the lcFiliere to set
	 */
	public void setLcFiliere(String lcFiliere) {
		this.lcFiliere = lcFiliere;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcFiliereArabe] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the lcFiliereArabe
	 */
	public String getLcFiliereArabe() {
		return lcFiliereArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.lcFiliereArabe] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param lcFiliereArabe the lcFiliereArabe to set
	 */
	public void setLcFiliereArabe(String lcFiliereArabe) {
		this.lcFiliereArabe = lcFiliereArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llFiliere] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the llFiliere
	 */
	public String getLlFiliere() {
		return llFiliere;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llFiliere] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param llFiliere the llFiliere to set
	 */
	public void setLlFiliere(String llFiliere) {
		this.llFiliere = llFiliere;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llFiliereArabe] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the llFiliereArabe
	 */
	public String getLlFiliereArabe() {
		return llFiliereArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.llFiliereArabe] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param llFiliereArabe the llFiliereArabe to set
	 */
	public void setLlFiliereArabe(String llFiliereArabe) {
		this.llFiliereArabe = llFiliereArabe;
	}
	/**
	 * [RefAffectDomLmdEtabDto.descriptionFiliere] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the descriptionFiliere
	 */
	public String getDescriptionFiliere() {
		return descriptionFiliere;
	}
	/**
	 * [RefAffectDomLmdEtabDto.descriptionFiliere] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param descriptionFiliere the descriptionFiliere to set
	 */
	public void setDescriptionFiliere(String descriptionFiliere) {
		this.descriptionFiliere = descriptionFiliere;
	}
	/**
	 * [RefAffectDomLmdEtabDto.descriptionArabeFiliere] Getter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @return the descriptionArabeFiliere
	 */
	public String getDescriptionArabeFiliere() {
		return descriptionArabeFiliere;
	}
	/**
	 * [RefAffectDomLmdEtabDto.descriptionArabeFiliere] Setter 
	 * @author BELBRIK Oualid on : 18 08. 2014  11:33:45
	 * @param descriptionArabeFiliere the descriptionArabeFiliere to set
	 */
	public void setDescriptionArabeFiliere(String descriptionArabeFiliere) {
		this.descriptionArabeFiliere = descriptionArabeFiliere;
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
	 * [RefAffectDomLmdEtabDto.idFiliereLMD] Getter 
	 * @author MAKERRI Sofiane on : 15 aout 2014  16:22:48
	 * @return the idFiliereLMD
	 */
	public Integer getIdFiliereLMD() {
		return idFiliereLMD;
	}
	/**
	 * [RefAffectDomLmdEtabDto.idFiliereLMD] Setter 
	 * @author MAKERRI Sofiane on : 15 aout 2014  16:22:48
	 * @param idFiliereLMD the idFiliereLMD to set
	 */
	public void setIdFiliereLMD(Integer idFiliereLMD) {
		this.idFiliereLMD = idFiliereLMD;
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
	/**
	 * [RefAffectFiliereLmdEtabDto.numArrete] Getter 
	 * @author BELBRIK Oualid on : 4 sept. 2014  15:41:27
	 * @return the numArrete
	 */
	public String getNumArrete() {
		return numArrete;
	}
	/**
	 * [RefAffectFiliereLmdEtabDto.numArrete] Setter 
	 * @author BELBRIK Oualid on : 4 sept. 2014  15:41:27
	 * @param numArrete the numArrete to set
	 */
	public void setNumArrete(String numArrete) {
		this.numArrete = numArrete;
	}
	/**
	 * [RefAffectFiliereLmdEtabDto.idStructure] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:41
	 * @return the idStructure
	 */
	public Integer getIdStructure() {
		return idStructure;
	}
	/**
	 * [RefAffectFiliereLmdEtabDto.idStructure] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:41
	 * @param idStructure the idStructure to set
	 */
	public void setIdStructure(Integer idStructure) {
		this.idStructure = idStructure;
	}
	/**
	 * [RefAffectFiliereLmdEtabDto.lcStructure] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:41
	 * @return the lcStructure
	 */
	public String getLcStructure() {
		return lcStructure;
	}
	/**
	 * [RefAffectFiliereLmdEtabDto.lcStructure] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:41
	 * @param lcStructure the lcStructure to set
	 */
	public void setLcStructure(String lcStructure) {
		this.lcStructure = lcStructure;
	}
	/**
	 * [RefAffectFiliereLmdEtabDto.lcStructureArabe] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:41
	 * @return the lcStructureArabe
	 */
	public String getLcStructureArabe() {
		return lcStructureArabe;
	}
	/**
	 * [RefAffectFiliereLmdEtabDto.lcStructureArabe] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:41
	 * @param lcStructureArabe the lcStructureArabe to set
	 */
	public void setLcStructureArabe(String lcStructureArabe) {
		this.lcStructureArabe = lcStructureArabe;
	}
	/**
	 * [RefAffectFiliereLmdEtabDto.llStructure] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:41
	 * @return the llStructure
	 */
	public String getLlStructure() {
		return llStructure;
	}
	/**
	 * [RefAffectFiliereLmdEtabDto.llStructure] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:41
	 * @param llStructure the llStructure to set
	 */
	public void setLlStructure(String llStructure) {
		this.llStructure = llStructure;
	}
	/**
	 * [RefAffectFiliereLmdEtabDto.llStructureArabe] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:41
	 * @return the llStructureArabe
	 */
	public String getLlStructureArabe() {
		return llStructureArabe;
	}
	/**
	 * [RefAffectFiliereLmdEtabDto.llStructureArabe] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:21:41
	 * @param llStructureArabe the llStructureArabe to set
	 */
	public void setLlStructureArabe(String llStructureArabe) {
		this.llStructureArabe = llStructureArabe;
	}
	/**
	 * [RefAffectFiliereLmdEtabDto.identifiantStructure] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:50:54
	 * @return the identifiantStructure
	 */
	public String getIdentifiantStructure() {
		return identifiantStructure;
	}
	/**
	 * [RefAffectFiliereLmdEtabDto.identifiantStructure] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  18:50:54
	 * @param identifiantStructure the identifiantStructure to set
	 */
	public void setIdentifiantStructure(String identifiantStructure) {
		this.identifiantStructure = identifiantStructure;
	}
	
	
	public Integer getDomaineId() {
	    return domaineId;
	}
	
	public void setDomaineId(Integer domaineId) {
	    this.domaineId = domaineId;
	}
	
		
	
}
