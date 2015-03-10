package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author BELBRIK Oualid  on : 21 avr. 2014  10:06:12
 */
@XmlRootElement(name = "RefFiliereLmdDto")
public class RefFiliereLmdDto implements Serializable{
		/**
		 * serialVersionUID 
		 * @author BELBRIK Oualid  on : 16 sept. 2014  17:16:44
		 */
		private static final long serialVersionUID = 1L;
		private Integer id;
		private String identifiant;
		private String lcFiliereArabe;
		private String lcFiliere;
		private String llFiliereArabe;
		private String llFiliere;
		private String descriptionFiliereArabe;
		private String descriptionFiliere;
		private Boolean isPremiereInscription;
	
	//private Nomenclature niveauRecrutement;
		private Integer niveauRecrutementId;
		private String niveauRecrutementLibelleLongFr;
		private String niveauRecrutementLibelleLongAr;
		private String niveauRecrutementLibelleCourtFr;
		private String niveauRecrutementLibelleCourtAr;
		private String niveauRecrutementCode;
	
	/*** Current Situation **/
	private Date dateSituation;
	private String llSituationAr;
	private String llSituationFr;
	/***** Domaine ******/
	private String identifiantDomaine;
	private Integer idDomaine;
	private String lcDomaine;
	private String lcDomaineArabe;
	private String llDomaine;
	private String llDomaineArabe;
	
	
	/**
	 * [RefFiliereLmdDto.id] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  11:56:09
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * [RefFiliereLmdDto.id] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  11:56:09
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * [RefFiliereLmdDto.identifiant] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}
	/**
	 * [RefFiliereLmdDto.identifiant] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	/**
	 * [RefFiliereLmdDto.lcFiliereArabe] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the lcFiliereArabe
	 */
	public String getLcFiliereArabe() {
		return lcFiliereArabe;
	}
	/**
	 * [RefFiliereLmdDto.lcFiliereArabe] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param lcFiliereArabe the lcFiliereArabe to set
	 */
	public void setLcFiliereArabe(String lcFiliereArabe) {
		this.lcFiliereArabe = lcFiliereArabe;
	}
	/**
	 * [RefFiliereLmdDto.lcFiliere] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the lcFiliere
	 */
	public String getLcFiliere() {
		return lcFiliere;
	}
	/**
	 * [RefFiliereLmdDto.lcFiliere] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param lcFiliere the lcFiliere to set
	 */
	public void setLcFiliere(String lcFiliere) {
		this.lcFiliere = lcFiliere;
	}
	/**
	 * [RefFiliereLmdDto.llFiliereArabe] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the llFiliereArabe
	 */
	public String getLlFiliereArabe() {
		return llFiliereArabe;
	}
	/**
	 * [RefFiliereLmdDto.llFiliereArabe] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param llFiliereArabe the llFiliereArabe to set
	 */
	public void setLlFiliereArabe(String llFiliereArabe) {
		this.llFiliereArabe = llFiliereArabe;
	}
	/**
	 * [RefFiliereLmdDto.llFiliere] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the llFiliere
	 */
	public String getLlFiliere() {
		return llFiliere;
	}
	/**
	 * [RefFiliereLmdDto.llFiliere] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param llFiliere the llFiliere to set
	 */
	public void setLlFiliere(String llFiliere) {
		this.llFiliere = llFiliere;
	}
	/**
	 * [RefFiliereLmdDto.descriptionFiliereArabe] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the descriptionFiliereArabe
	 */
	public String getDescriptionFiliereArabe() {
		return descriptionFiliereArabe;
	}
	/**
	 * [RefFiliereLmdDto.descriptionFiliereArabe] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param descriptionFiliereArabe the descriptionFiliereArabe to set
	 */
	public void setDescriptionFiliereArabe(String descriptionFiliereArabe) {
		this.descriptionFiliereArabe = descriptionFiliereArabe;
	}
	/**
	 * [RefFiliereLmdDto.descriptionFiliere] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the descriptionFiliere
	 */
	public String getDescriptionFiliere() {
		return descriptionFiliere;
	}
	/**
	 * [RefFiliereLmdDto.descriptionFiliere] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param descriptionFiliere the descriptionFiliere to set
	 */
	public void setDescriptionFiliere(String descriptionFiliere) {
		this.descriptionFiliere = descriptionFiliere;
	}
	/**
	 * [RefFiliereLmdDto.dateSituation] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the dateSituation
	 */
	public Date getDateSituation() {
		return dateSituation;
	}
	/**
	 * [RefFiliereLmdDto.dateSituation] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param dateSituation the dateSituation to set
	 */
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}
	/**
	 * [RefFiliereLmdDto.llSituationAr] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the llSituationAr
	 */
	public String getLlSituationAr() {
		return llSituationAr;
	}
	/**
	 * [RefFiliereLmdDto.llSituationAr] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param llSituationAr the llSituationAr to set
	 */
	public void setLlSituationAr(String llSituationAr) {
		this.llSituationAr = llSituationAr;
	}
	/**
	 * [RefFiliereLmdDto.llSituationFr] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the llSituationFr
	 */
	public String getLlSituationFr() {
		return llSituationFr;
	}
	/**
	 * [RefFiliereLmdDto.llSituationFr] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param llSituationFr the llSituationFr to set
	 */
	public void setLlSituationFr(String llSituationFr) {
		this.llSituationFr = llSituationFr;
	}
	/**
	/**
	 * [RefFiliereLmdDto.identifiantDomaine] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the identifiantDomaine
	 */
	public String getIdentifiantDomaine() {
		return identifiantDomaine;
	}
	/**
	 * [RefFiliereLmdDto.identifiantDomaine] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param identifiantDomaine the identifiantDomaine to set
	 */
	public void setIdentifiantDomaine(String identifiantDomaine) {
		this.identifiantDomaine = identifiantDomaine;
	}
	/**
	 * [RefFiliereLmdDto.lcDomaine] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the lcDomaine
	 */
	public String getLcDomaine() {
		return lcDomaine;
	}
	/**
	 * [RefFiliereLmdDto.lcDomaine] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param lcDomaine the lcDomaine to set
	 */
	public void setLcDomaine(String lcDomaine) {
		this.lcDomaine = lcDomaine;
	}
	/**
	 * [RefFiliereLmdDto.lcDomaineArabe] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the lcDomaineArabe
	 */
	public String getLcDomaineArabe() {
		return lcDomaineArabe;
	}
	/**
	 * [RefFiliereLmdDto.lcDomaineArabe] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param lcDomaineArabe the lcDomaineArabe to set
	 */
	public void setLcDomaineArabe(String lcDomaineArabe) {
		this.lcDomaineArabe = lcDomaineArabe;
	}
	/**
	 * [RefFiliereLmdDto.llDomaine] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the llDomaine
	 */
	public String getLlDomaine() {
		return llDomaine;
	}
	/**
	 * [RefFiliereLmdDto.llDomaine] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param llDomaine the llDomaine to set
	 */
	public void setLlDomaine(String llDomaine) {
		this.llDomaine = llDomaine;
	}
	/**
	 * [RefFiliereLmdDto.llDomaineArabe] Getter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @return the llDomaineArabe
	 */
	public String getLlDomaineArabe() {
		return llDomaineArabe;
	}
	/**
	 * [RefFiliereLmdDto.llDomaineArabe] Setter 
	 * @author BELBRIK Oualid on : 21 avr. 2014  11:56:38
	 * @param llDomaineArabe the llDomaineArabe to set
	 */
	public void setLlDomaineArabe(String llDomaineArabe) {
		this.llDomaineArabe = llDomaineArabe;
	}
	/**
	 * [RefFiliereLmdDto.idDomaine] Getter 
	 * @author MAKERRI Sofiane on : 15 mai 2014  17:19:28
	 * @return the idDomaine
	 */
	public Integer getIdDomaine() {
		return idDomaine;
	}
	/**
	 * [RefFiliereLmdDto.idDomaine] Setter 
	 * @author MAKERRI Sofiane on : 15 mai 2014  17:19:28
	 * @param idDomaine the idDomaine to set
	 */
	public void setIdDomaine(Integer idDomaine) {
		this.idDomaine = idDomaine;
	}
	/**
	 * [RefFiliereLmdDto.isPremiereInscription] Getter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:16:33
	 * @return the isPremiereInscription
	 */
	public Boolean getIsPremiereInscription() {
		return isPremiereInscription;
	}
	/**
	 * [RefFiliereLmdDto.isPremiereInscription] Setter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:16:33
	 * @param isPremiereInscription the isPremiereInscription to set
	 */
	public void setIsPremiereInscription(Boolean isPremiereInscription) {
		this.isPremiereInscription = isPremiereInscription;
	}
	/**
	 * [RefFiliereLmdDto.niveauRecrutementId] Getter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:16:33
	 * @return the niveauRecrutementId
	 */
	public Integer getNiveauRecrutementId() {
		return niveauRecrutementId;
	}
	/**
	 * [RefFiliereLmdDto.niveauRecrutementId] Setter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:16:33
	 * @param niveauRecrutementId the niveauRecrutementId to set
	 */
	public void setNiveauRecrutementId(Integer niveauRecrutementId) {
		this.niveauRecrutementId = niveauRecrutementId;
	}
	/**
	 * [RefFiliereLmdDto.niveauRecrutementLibelleLongFr] Getter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:16:33
	 * @return the niveauRecrutementLibelleLongFr
	 */
	public String getNiveauRecrutementLibelleLongFr() {
		return niveauRecrutementLibelleLongFr;
	}
	/**
	 * [RefFiliereLmdDto.niveauRecrutementLibelleLongFr] Setter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:16:33
	 * @param niveauRecrutementLibelleLongFr the niveauRecrutementLibelleLongFr to set
	 */
	public void setNiveauRecrutementLibelleLongFr(
			String niveauRecrutementLibelleLongFr) {
		this.niveauRecrutementLibelleLongFr = niveauRecrutementLibelleLongFr;
	}
	/**
	 * [RefFiliereLmdDto.niveauRecrutementLibelleLongAr] Getter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:16:33
	 * @return the niveauRecrutementLibelleLongAr
	 */
	public String getNiveauRecrutementLibelleLongAr() {
		return niveauRecrutementLibelleLongAr;
	}
	/**
	 * [RefFiliereLmdDto.niveauRecrutementLibelleLongAr] Setter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:16:33
	 * @param niveauRecrutementLibelleLongAr the niveauRecrutementLibelleLongAr to set
	 */
	public void setNiveauRecrutementLibelleLongAr(
			String niveauRecrutementLibelleLongAr) {
		this.niveauRecrutementLibelleLongAr = niveauRecrutementLibelleLongAr;
	}
	/**
	 * [RefFiliereLmdDto.niveauRecrutementLibelleCourtFr] Getter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:16:33
	 * @return the niveauRecrutementLibelleCourtFr
	 */
	public String getNiveauRecrutementLibelleCourtFr() {
		return niveauRecrutementLibelleCourtFr;
	}
	/**
	 * [RefFiliereLmdDto.niveauRecrutementLibelleCourtFr] Setter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:16:33
	 * @param niveauRecrutementLibelleCourtFr the niveauRecrutementLibelleCourtFr to set
	 */
	public void setNiveauRecrutementLibelleCourtFr(
			String niveauRecrutementLibelleCourtFr) {
		this.niveauRecrutementLibelleCourtFr = niveauRecrutementLibelleCourtFr;
	}
	/**
	 * [RefFiliereLmdDto.niveauRecrutementLibelleCourtAr] Getter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:16:33
	 * @return the niveauRecrutementLibelleCourtAr
	 */
	public String getNiveauRecrutementLibelleCourtAr() {
		return niveauRecrutementLibelleCourtAr;
	}
	/**
	 * [RefFiliereLmdDto.niveauRecrutementLibelleCourtAr] Setter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:16:33
	 * @param niveauRecrutementLibelleCourtAr the niveauRecrutementLibelleCourtAr to set
	 */
	public void setNiveauRecrutementLibelleCourtAr(
			String niveauRecrutementLibelleCourtAr) {
		this.niveauRecrutementLibelleCourtAr = niveauRecrutementLibelleCourtAr;
	}
	/**
	 * [RefFiliereLmdDto.niveauRecrutementCode] Getter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:16:33
	 * @return the niveauRecrutementCode
	 */
	public String getNiveauRecrutementCode() {
		return niveauRecrutementCode;
	}
	/**
	 * [RefFiliereLmdDto.niveauRecrutementCode] Setter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:16:33
	 * @param niveauRecrutementCode the niveauRecrutementCode to set
	 */
	public void setNiveauRecrutementCode(String niveauRecrutementCode) {
		this.niveauRecrutementCode = niveauRecrutementCode;
	}
	
	
	
	



}
