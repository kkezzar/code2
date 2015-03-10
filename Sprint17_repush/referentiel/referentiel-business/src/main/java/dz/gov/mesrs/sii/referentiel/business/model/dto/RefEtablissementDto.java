/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto.java] 
 * @author MAKERRI Sofiane on : 10 avr. 2014  12:10:00
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author MAKERRI Sofiane on : 10 avr. 2014 12:10:00
 */
@XmlRootElement(name = "RefEtablissementDto")
public class RefEtablissementDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String identifiant;
	private Date dateCreation;
	private String lcEtablissementArabe;
	private String lcEtablissementLatin;
	private String llEtablissementArabe;
	private String llEtablissementLatin;
	private String nif;
	private String nis;
	private String nrc;
	private String nss;
	private String decret;
	private String ancienCode;

	/***** forme juridique *****/
	private Integer formeJuridiqueId;
	private String formeJuridiqueLibelleLongFr;
	private String formeJuridiqueLibelleLongAr;
	private String formeJuridiqueLibelleCourtFr;
	private String formeJuridiqueLibelleCourtAr;
	private String formeJuridiqueCode;
	/***** etablissement type *****/
	private Integer typeId;
	private String typeLibelleLongFr;
	private String typeLibelleLongAr;
	private String typeLibelleCourtFr;
	private String typeLibelleCourtAr;
	private String typeCode;
	/***** wilaya *****/
	private Integer wilayaId;
	private String wilayaLlFr;
	private String wilayaLlAr;
	private String wilayaLcFr;
	private String wilayaLcAr;
	private String wilayaCode;

	/**
	 * Current Situation
	 * 
	 */
	private Date dateSituation;
	private String llSituationAr;
	private String llSituationFr;

	/**
	 * [RefEtablissementDto.RefEtablissementDto()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:10:00
	 */
	public RefEtablissementDto() {
		super();
	}

	/**
	 * [RefEtablissementDto.RefEtablissementDto()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 3 févr. 2015 09:12:53
	 * @param id
	 * @param identifiant
	 * @param llEtablissementLatin
	 */
	public RefEtablissementDto(Integer id, String identifiant,
			String llEtablissementLatin) {
		this.id = id;
		this.identifiant = identifiant;
		this.llEtablissementLatin = llEtablissementLatin;
	}

	/**
	 * [RefEtablissementDto.RefEtablissementDto()] Constructor
	 * @author MAKERRI Sofiane  on : 3 févr. 2015  09:14:35
	 * @param refAffectationDto	
	 */
	public RefEtablissementDto(RefAffectationDto refAffectationDto) {
		if (refAffectationDto != null) {
			this.id = refAffectationDto.getIdRefEtablissement();
			this.identifiant = refAffectationDto.getCodeRefEtablissement();
			this.llEtablissementLatin = refAffectationDto.getLlFrRefEtablissement();
			this.llEtablissementArabe = refAffectationDto.getLlArRefEtablissement();
			this.ancienCode = refAffectationDto.getAncienCodeRefEtablissement();
		}
	}

	public RefEtablissementDto(Integer id, String identifiant,
			String lcEtablissementArabe, String lcEtablissementLatin,
			String llEtablissementArabe, String llEtablissementLatin,
			String ancienCode) {
		super();
		this.id = id;
		this.identifiant = identifiant;
		this.lcEtablissementArabe = lcEtablissementArabe;
		this.lcEtablissementLatin = lcEtablissementLatin;
		this.llEtablissementArabe = llEtablissementArabe;
		this.llEtablissementLatin = llEtablissementLatin;
		this.ancienCode = ancienCode;
	}

	/**
	 * [RefEtablissementDto.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 mai 2014 11:54:49
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefEtablissementDto.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 mai 2014 11:54:49
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefEtablissementDto.identifiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * [RefEtablissementDto.identifiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @param identifiant
	 *            the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * [RefEtablissementDto.dateCreation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * [RefEtablissementDto.dateCreation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @param dateCreation
	 *            the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * [RefEtablissementDto.lcEtablissementArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @return the lcEtablissementArabe
	 */
	public String getLcEtablissementArabe() {
		return lcEtablissementArabe;
	}

	/**
	 * [RefEtablissementDto.lcEtablissementArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @param lcEtablissementArabe
	 *            the lcEtablissementArabe to set
	 */
	public void setLcEtablissementArabe(String lcEtablissementArabe) {
		this.lcEtablissementArabe = lcEtablissementArabe;
	}

	/**
	 * [RefEtablissementDto.lcEtablissementLatin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @return the lcEtablissementLatin
	 */
	public String getLcEtablissementLatin() {
		return lcEtablissementLatin;
	}

	/**
	 * [RefEtablissementDto.lcEtablissementLatin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @param lcEtablissementLatin
	 *            the lcEtablissementLatin to set
	 */
	public void setLcEtablissementLatin(String lcEtablissementLatin) {
		this.lcEtablissementLatin = lcEtablissementLatin;
	}

	/**
	 * [RefEtablissementDto.llEtablissementArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @return the llEtablissementArabe
	 */
	public String getLlEtablissementArabe() {
		return llEtablissementArabe;
	}

	/**
	 * [RefEtablissementDto.llEtablissementArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @param llEtablissementArabe
	 *            the llEtablissementArabe to set
	 */
	public void setLlEtablissementArabe(String llEtablissementArabe) {
		this.llEtablissementArabe = llEtablissementArabe;
	}

	/**
	 * [RefEtablissementDto.llEtablissementLatin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @return the llEtablissementLatin
	 */
	public String getLlEtablissementLatin() {
		return llEtablissementLatin;
	}

	/**
	 * [RefEtablissementDto.llEtablissementLatin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @param llEtablissementLatin
	 *            the llEtablissementLatin to set
	 */
	public void setLlEtablissementLatin(String llEtablissementLatin) {
		this.llEtablissementLatin = llEtablissementLatin;
	}

	/**
	 * [RefEtablissementDto.nif] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @return the nif
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * [RefEtablissementDto.nif] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @param nif
	 *            the nif to set
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}

	/**
	 * [RefEtablissementDto.nis] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @return the nis
	 */
	public String getNis() {
		return nis;
	}

	/**
	 * [RefEtablissementDto.nis] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @param nis
	 *            the nis to set
	 */
	public void setNis(String nis) {
		this.nis = nis;
	}

	/**
	 * [RefEtablissementDto.nrc] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @return the nrc
	 */
	public String getNrc() {
		return nrc;
	}

	/**
	 * [RefEtablissementDto.nrc] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @param nrc
	 *            the nrc to set
	 */
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	/**
	 * [RefEtablissementDto.nss] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @return the nss
	 */
	public String getNss() {
		return nss;
	}

	/**
	 * [RefEtablissementDto.nss] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:11:42
	 * @param nss
	 *            the nss to set
	 */
	public void setNss(String nss) {
		this.nss = nss;
	}

	/**
	 * [RefEtablissementDto.formeJuridiqueId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @return the formeJuridiqueId
	 */
	public Integer getFormeJuridiqueId() {
		return formeJuridiqueId;
	}

	/**
	 * [RefEtablissementDto.formeJuridiqueId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @param formeJuridiqueId
	 *            the formeJuridiqueId to set
	 */
	public void setFormeJuridiqueId(Integer formeJuridiqueId) {
		this.formeJuridiqueId = formeJuridiqueId;
	}

	/**
	 * [RefEtablissementDto.formeJuridiqueLibelleLongFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @return the formeJuridiqueLibelleLongFr
	 */
	public String getFormeJuridiqueLibelleLongFr() {
		return formeJuridiqueLibelleLongFr;
	}

	/**
	 * [RefEtablissementDto.formeJuridiqueLibelleLongFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @param formeJuridiqueLibelleLongFr
	 *            the formeJuridiqueLibelleLongFr to set
	 */
	public void setFormeJuridiqueLibelleLongFr(
			String formeJuridiqueLibelleLongFr) {
		this.formeJuridiqueLibelleLongFr = formeJuridiqueLibelleLongFr;
	}

	/**
	 * [RefEtablissementDto.formeJuridiqueLibelleLongAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @return the formeJuridiqueLibelleLongAr
	 */
	public String getFormeJuridiqueLibelleLongAr() {
		return formeJuridiqueLibelleLongAr;
	}

	/**
	 * [RefEtablissementDto.formeJuridiqueLibelleLongAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @param formeJuridiqueLibelleLongAr
	 *            the formeJuridiqueLibelleLongAr to set
	 */
	public void setFormeJuridiqueLibelleLongAr(
			String formeJuridiqueLibelleLongAr) {
		this.formeJuridiqueLibelleLongAr = formeJuridiqueLibelleLongAr;
	}

	/**
	 * [RefEtablissementDto.formeJuridiqueLibelleCourtFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @return the formeJuridiqueLibelleCourtFr
	 */
	public String getFormeJuridiqueLibelleCourtFr() {
		return formeJuridiqueLibelleCourtFr;
	}

	/**
	 * [RefEtablissementDto.formeJuridiqueLibelleCourtFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @param formeJuridiqueLibelleCourtFr
	 *            the formeJuridiqueLibelleCourtFr to set
	 */
	public void setFormeJuridiqueLibelleCourtFr(
			String formeJuridiqueLibelleCourtFr) {
		this.formeJuridiqueLibelleCourtFr = formeJuridiqueLibelleCourtFr;
	}

	/**
	 * [RefEtablissementDto.formeJuridiqueLibelleCourtAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @return the formeJuridiqueLibelleCourtAr
	 */
	public String getFormeJuridiqueLibelleCourtAr() {
		return formeJuridiqueLibelleCourtAr;
	}

	/**
	 * [RefEtablissementDto.formeJuridiqueLibelleCourtAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @param formeJuridiqueLibelleCourtAr
	 *            the formeJuridiqueLibelleCourtAr to set
	 */
	public void setFormeJuridiqueLibelleCourtAr(
			String formeJuridiqueLibelleCourtAr) {
		this.formeJuridiqueLibelleCourtAr = formeJuridiqueLibelleCourtAr;
	}

	/**
	 * [RefEtablissementDto.formeJuridiqueCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @return the formeJuridiqueCode
	 */
	public String getFormeJuridiqueCode() {
		return formeJuridiqueCode;
	}

	/**
	 * [RefEtablissementDto.formeJuridiqueCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @param formeJuridiqueCode
	 *            the formeJuridiqueCode to set
	 */
	public void setFormeJuridiqueCode(String formeJuridiqueCode) {
		this.formeJuridiqueCode = formeJuridiqueCode;
	}

	/**
	 * [RefEtablissementDto.dateSituation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @return the dateSituation
	 */
	public Date getDateSituation() {
		return dateSituation;
	}

	/**
	 * [RefEtablissementDto.dateSituation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @param dateSituation
	 *            the dateSituation to set
	 */
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}

	/**
	 * [RefEtablissementDto.llSituationAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @return the llSituationAr
	 */
	public String getLlSituationAr() {
		return llSituationAr;
	}

	/**
	 * [RefEtablissementDto.llSituationAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @param llSituationAr
	 *            the llSituationAr to set
	 */
	public void setLlSituationAr(String llSituationAr) {
		this.llSituationAr = llSituationAr;
	}

	/**
	 * [RefEtablissementDto.llSituationFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @return the llSituationFr
	 */
	public String getLlSituationFr() {
		return llSituationFr;
	}

	/**
	 * [RefEtablissementDto.llSituationFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:42:42
	 * @param llSituationFr
	 *            the llSituationFr to set
	 */
	public void setLlSituationFr(String llSituationFr) {
		this.llSituationFr = llSituationFr;
	}

	/**
	 * [RefEtablissementDto.decret] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:39:53
	 * @return the decret
	 */
	public String getDecret() {
		return decret;
	}

	/**
	 * [RefEtablissementDto.decret] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:39:53
	 * @param decret
	 *            the decret to set
	 */
	public void setDecret(String decret) {
		this.decret = decret;
	}

	/**
	 * [RefEtablissementDto.typeId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:48:18
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * [RefEtablissementDto.typeId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:48:18
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * [RefEtablissementDto.typeLibelleLongFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:48:18
	 * @return the typeLibelleLongFr
	 */
	public String getTypeLibelleLongFr() {
		return typeLibelleLongFr;
	}

	/**
	 * [RefEtablissementDto.typeLibelleLongFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:48:18
	 * @param typeLibelleLongFr
	 *            the typeLibelleLongFr to set
	 */
	public void setTypeLibelleLongFr(String typeLibelleLongFr) {
		this.typeLibelleLongFr = typeLibelleLongFr;
	}

	/**
	 * [RefEtablissementDto.typeLibelleLongAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:48:18
	 * @return the typeLibelleLongAr
	 */
	public String getTypeLibelleLongAr() {
		return typeLibelleLongAr;
	}

	/**
	 * [RefEtablissementDto.typeLibelleLongAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:48:18
	 * @param typeLibelleLongAr
	 *            the typeLibelleLongAr to set
	 */
	public void setTypeLibelleLongAr(String typeLibelleLongAr) {
		this.typeLibelleLongAr = typeLibelleLongAr;
	}

	/**
	 * [RefEtablissementDto.typeLibelleCourtFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:48:18
	 * @return the typeLibelleCourtFr
	 */
	public String getTypeLibelleCourtFr() {
		return typeLibelleCourtFr;
	}

	/**
	 * [RefEtablissementDto.typeLibelleCourtFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:48:18
	 * @param typeLibelleCourtFr
	 *            the typeLibelleCourtFr to set
	 */
	public void setTypeLibelleCourtFr(String typeLibelleCourtFr) {
		this.typeLibelleCourtFr = typeLibelleCourtFr;
	}

	/**
	 * [RefEtablissementDto.typeLibelleCourtAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:48:18
	 * @return the typeLibelleCourtAr
	 */
	public String getTypeLibelleCourtAr() {
		return typeLibelleCourtAr;
	}

	/**
	 * [RefEtablissementDto.typeLibelleCourtAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:48:18
	 * @param typeLibelleCourtAr
	 *            the typeLibelleCourtAr to set
	 */
	public void setTypeLibelleCourtAr(String typeLibelleCourtAr) {
		this.typeLibelleCourtAr = typeLibelleCourtAr;
	}

	/**
	 * [RefEtablissementDto.typeCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:48:18
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * [RefEtablissementDto.typeCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:48:18
	 * @param typeCode
	 *            the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * [RefEtablissementDto.wilayaId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 10:20:13
	 * @return the wilayaId
	 */
	public Integer getWilayaId() {
		return wilayaId;
	}

	/**
	 * [RefEtablissementDto.wilayaId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 10:20:13
	 * @param wilayaId
	 *            the wilayaId to set
	 */
	public void setWilayaId(Integer wilayaId) {
		this.wilayaId = wilayaId;
	}

	/**
	 * [RefEtablissementDto.wilayaLlFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 10:20:13
	 * @return the wilayaLlFr
	 */
	public String getWilayaLlFr() {
		return wilayaLlFr;
	}

	/**
	 * [RefEtablissementDto.wilayaLlFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 10:20:13
	 * @param wilayaLlFr
	 *            the wilayaLlFr to set
	 */
	public void setWilayaLlFr(String wilayaLlFr) {
		this.wilayaLlFr = wilayaLlFr;
	}

	/**
	 * [RefEtablissementDto.wilayaLlAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 10:20:13
	 * @return the wilayaLlAr
	 */
	public String getWilayaLlAr() {
		return wilayaLlAr;
	}

	/**
	 * [RefEtablissementDto.wilayaLlAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 10:20:13
	 * @param wilayaLlAr
	 *            the wilayaLlAr to set
	 */
	public void setWilayaLlAr(String wilayaLlAr) {
		this.wilayaLlAr = wilayaLlAr;
	}

	/**
	 * [RefEtablissementDto.wilayaLcFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 10:20:13
	 * @return the wilayaLcFr
	 */
	public String getWilayaLcFr() {
		return wilayaLcFr;
	}

	/**
	 * [RefEtablissementDto.wilayaLcFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 10:20:13
	 * @param wilayaLcFr
	 *            the wilayaLcFr to set
	 */
	public void setWilayaLcFr(String wilayaLcFr) {
		this.wilayaLcFr = wilayaLcFr;
	}

	/**
	 * [RefEtablissementDto.wilayaLcAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 10:20:13
	 * @return the wilayaLcAr
	 */
	public String getWilayaLcAr() {
		return wilayaLcAr;
	}

	/**
	 * [RefEtablissementDto.wilayaLcAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 10:20:13
	 * @param wilayaLcAr
	 *            the wilayaLcAr to set
	 */
	public void setWilayaLcAr(String wilayaLcAr) {
		this.wilayaLcAr = wilayaLcAr;
	}

	/**
	 * [RefEtablissementDto.wilayaCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 10:20:13
	 * @return the wilayaCode
	 */
	public String getWilayaCode() {
		return wilayaCode;
	}

	/**
	 * [RefEtablissementDto.wilayaCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 10:20:13
	 * @param wilayaCode
	 *            the wilayaCode to set
	 */
	public void setWilayaCode(String wilayaCode) {
		this.wilayaCode = wilayaCode;
	}

	/**
	 * [RefEtablissementDto.ancienCode] Getter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 09:36:50
	 * @return the ancienCode
	 */
	public String getAncienCode() {
		return ancienCode;
	}

	/**
	 * [RefEtablissementDto.ancienCode] Setter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 09:36:50
	 * @param ancienCode
	 *            the ancienCode to set
	 */
	public void setAncienCode(String ancienCode) {
		this.ancienCode = ancienCode;
	}

}
