/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineDto.java] 
 * @author BELBRIK Oualid on : 09 f�vr. 2014 11:42:34
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author BELBRIK Oualid  on : 14 avr. 2014  10:06:12
 */
@XmlRootElement(name = "RefDomaineLMDDto")
public class RefDomaineLMDDto implements Serializable {
	private Integer id;
	private String identifiant;
	private String lcDomaine;
	private String lcDomaineArabe;
	private String llDomaine;
	private String llDomaineArabe;
	private String description;
	private String descriptionArabe;
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
	
	/**
	 * [RefDomaineLMDDto.id] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  11:54:04
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * [RefDomaineLMDDto.id] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  11:54:04
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * [RefDomaineLMDDto.identifiant] Getter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  10:06:12
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}
	/**
	 * [RefDomaineLMDDto.identifiant] Setter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  10:06:12
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	/**
	 * [RefDomaineLMDDto.lcDomaine] Getter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  10:06:12
	 * @return the lcDomaine
	 */
	public String getLcDomaine() {
		return lcDomaine;
	}
	/**
	 * [RefDomaineLMDDto.lcDomaine] Setter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  10:06:12
	 * @param lcDomaine the lcDomaine to set
	 */
	public void setLcDomaine(String lcDomaine) {
		this.lcDomaine = lcDomaine;
	}
	/**
	 * [RefDomaineLMDDto.lcDomaineArabe] Getter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  10:06:12
	 * @return the lcDomaineArabe
	 */
	public String getLcDomaineArabe() {
		return lcDomaineArabe;
	}
	/**
	 * [RefDomaineLMDDto.lcDomaineArabe] Setter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  10:06:12
	 * @param lcDomaineArabe the lcDomaineArabe to set
	 */
	public void setLcDomaineArabe(String lcDomaineArabe) {
		this.lcDomaineArabe = lcDomaineArabe;
	}
	/**
	 * [RefDomaineLMDDto.llDomaine] Getter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  10:06:12
	 * @return the llDomaine
	 */
	public String getLlDomaine() {
		return llDomaine;
	}
	/**
	 * [RefDomaineLMDDto.llDomaine] Setter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  10:06:12
	 * @param llDomaine the llDomaine to set
	 */
	public void setLlDomaine(String llDomaine) {
		this.llDomaine = llDomaine;
	}
	/**
	 * [RefDomaineLMDDto.llDomaineArabe] Getter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  10:06:12
	 * @return the llDomaineArabe
	 */
	public String getLlDomaineArabe() {
		return llDomaineArabe;
	}
	/**
	 * [RefDomaineLMDDto.llDomaineArabe] Setter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  10:06:12
	 * @param llDomaineArabe the llDomaineArabe to set
	 */
	public void setLlDomaineArabe(String llDomaineArabe) {
		this.llDomaineArabe = llDomaineArabe;
	}
	/**
	 * [RefDomaineLMDDto.description] Getter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  10:06:12
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * [RefDomaineLMDDto.description] Setter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  10:06:13
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * [RefDomaineLMDDto.descriptionArabe] Getter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  10:06:13
	 * @return the descriptionArabe
	 */
	public String getDescriptionArabe() {
		return descriptionArabe;
	}
	/**
	 * [RefDomaineLMDDto.descriptionArabe] Setter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  10:06:13
	 * @param descriptionArabe the descriptionArabe to set
	 */
	public void setDescriptionArabe(String descriptionArabe) {
		this.descriptionArabe = descriptionArabe;
	}
	
	
	
	
	/**
	 * [RefDomaineLMDDto.dateSituation] Getter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  16:58:13
	 * @return the dateSituation
	 */
	public Date getDateSituation() {
		return dateSituation;
	}
	/**
	 * [RefDomaineLMDDto.dateSituation] Setter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  16:58:13
	 * @param dateSituation the dateSituation to set
	 */
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}
	/**
	 * [RefDomaineLMDDto.llSituationAr] Getter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  16:58:13
	 * @return the llSituationAr
	 */
	public String getLlSituationAr() {
		return llSituationAr;
	}
	/**
	 * [RefDomaineLMDDto.llSituationAr] Setter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  16:58:13
	 * @param llSituationAr the llSituationAr to set
	 */
	public void setLlSituationAr(String llSituationAr) {
		this.llSituationAr = llSituationAr;
	}
	/**
	 * [RefDomaineLMDDto.llSituationFr] Getter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  16:58:13
	 * @return the llSituationFr
	 */
	public String getLlSituationFr() {
		return llSituationFr;
	}
	/**
	 * [RefDomaineLMDDto.llSituationFr] Setter 
	 * @author BELBRIK Oualid on : 14 avr. 2014  16:58:13
	 * @param llSituationFr the llSituationFr to set
	 */
	public void setLlSituationFr(String llSituationFr) {
		this.llSituationFr = llSituationFr;
	}
	/**
	 * [RefDomaineLMDDto.isPremiereInscription] Getter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:15:31
	 * @return the isPremiereInscription
	 */
	public Boolean getIsPremiereInscription() {
		return isPremiereInscription;
	}
	/**
	 * [RefDomaineLMDDto.isPremiereInscription] Setter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:15:31
	 * @param isPremiereInscription the isPremiereInscription to set
	 */
	public void setIsPremiereInscription(Boolean isPremiereInscription) {
		this.isPremiereInscription = isPremiereInscription;
	}
	/**
	 * [RefDomaineLMDDto.niveauRecrutementId] Getter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:15:31
	 * @return the niveauRecrutementId
	 */
	public Integer getNiveauRecrutementId() {
		return niveauRecrutementId;
	}
	/**
	 * [RefDomaineLMDDto.niveauRecrutementId] Setter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:15:31
	 * @param niveauRecrutementId the niveauRecrutementId to set
	 */
	public void setNiveauRecrutementId(Integer niveauRecrutementId) {
		this.niveauRecrutementId = niveauRecrutementId;
	}
	/**
	 * [RefDomaineLMDDto.niveauRecrutementLibelleLongFr] Getter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:15:31
	 * @return the niveauRecrutementLibelleLongFr
	 */
	public String getNiveauRecrutementLibelleLongFr() {
		return niveauRecrutementLibelleLongFr;
	}
	/**
	 * [RefDomaineLMDDto.niveauRecrutementLibelleLongFr] Setter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:15:31
	 * @param niveauRecrutementLibelleLongFr the niveauRecrutementLibelleLongFr to set
	 */
	public void setNiveauRecrutementLibelleLongFr(
			String niveauRecrutementLibelleLongFr) {
		this.niveauRecrutementLibelleLongFr = niveauRecrutementLibelleLongFr;
	}
	/**
	 * [RefDomaineLMDDto.niveauRecrutementLibelleLongAr] Getter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:15:31
	 * @return the niveauRecrutementLibelleLongAr
	 */
	public String getNiveauRecrutementLibelleLongAr() {
		return niveauRecrutementLibelleLongAr;
	}
	/**
	 * [RefDomaineLMDDto.niveauRecrutementLibelleLongAr] Setter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:15:31
	 * @param niveauRecrutementLibelleLongAr the niveauRecrutementLibelleLongAr to set
	 */
	public void setNiveauRecrutementLibelleLongAr(
			String niveauRecrutementLibelleLongAr) {
		this.niveauRecrutementLibelleLongAr = niveauRecrutementLibelleLongAr;
	}
	/**
	 * [RefDomaineLMDDto.niveauRecrutementLibelleCourtFr] Getter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:15:31
	 * @return the niveauRecrutementLibelleCourtFr
	 */
	public String getNiveauRecrutementLibelleCourtFr() {
		return niveauRecrutementLibelleCourtFr;
	}
	/**
	 * [RefDomaineLMDDto.niveauRecrutementLibelleCourtFr] Setter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:15:31
	 * @param niveauRecrutementLibelleCourtFr the niveauRecrutementLibelleCourtFr to set
	 */
	public void setNiveauRecrutementLibelleCourtFr(
			String niveauRecrutementLibelleCourtFr) {
		this.niveauRecrutementLibelleCourtFr = niveauRecrutementLibelleCourtFr;
	}
	/**
	 * [RefDomaineLMDDto.niveauRecrutementLibelleCourtAr] Getter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:15:31
	 * @return the niveauRecrutementLibelleCourtAr
	 */
	public String getNiveauRecrutementLibelleCourtAr() {
		return niveauRecrutementLibelleCourtAr;
	}
	/**
	 * [RefDomaineLMDDto.niveauRecrutementLibelleCourtAr] Setter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:15:31
	 * @param niveauRecrutementLibelleCourtAr the niveauRecrutementLibelleCourtAr to set
	 */
	public void setNiveauRecrutementLibelleCourtAr(
			String niveauRecrutementLibelleCourtAr) {
		this.niveauRecrutementLibelleCourtAr = niveauRecrutementLibelleCourtAr;
	}
	/**
	 * [RefDomaineLMDDto.niveauRecrutementCode] Getter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:15:31
	 * @return the niveauRecrutementCode
	 */
	public String getNiveauRecrutementCode() {
		return niveauRecrutementCode;
	}
	/**
	 * [RefDomaineLMDDto.niveauRecrutementCode] Setter 
	 * @author BELBRIK Oualid on : 16 sept. 2014  17:15:31
	 * @param niveauRecrutementCode the niveauRecrutementCode to set
	 */
	public void setNiveauRecrutementCode(String niveauRecrutementCode) {
		this.niveauRecrutementCode = niveauRecrutementCode;
	}
	
}
