package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.util.Date;

/**
 * @author BELBRIK Oualid on : 6 mars. 2014 15:42:34
 */
public class RefEquipementDto implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String identifiant;
	private String code;
	private String designation;
	private String referenceexterne;
	private Date dateMiseDispo;
	private Boolean reservable;

	/***** famille equipement *****/
	private Integer familleequipementId;
	private String familleequipementLibelleLongFr;
	private String familleequipementLibelleLongAr;
	private String familleequipementLibelleCourtFr;
	private String familleequipementLibelleCourtAr;
	private String familleequipementCode;

	/***** sous-famille equipement *****/
	private Integer sousfamilleequipementId;
	private String sousfamilleequipementLibelleLongFr;
	private String sousfamilleequipementLibelleLongAr;
	private String sousfamilleequipementLibelleCourtFr;
	private String sousfamilleequipementLibelleCourtAr;
	private String sousfamilleequipementCode;

	/*** Current Situation **/
	private Date dateSituation;
	private String llSituationAr;
	private String llSituationFr;
	/********** etablissement ****************/
	private Integer idEtablissement;

	/**
	 * [RefEquipementDto.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 mai 2014 11:54:29
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefEquipementDto.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 mai 2014 11:54:29
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefEquipementDto.identifiant] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * [RefEquipementDto.identifiant] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param identifiant
	 *            the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * [RefEquipementDto.code] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [RefEquipementDto.code] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [RefEquipementDto.designation] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * [RefEquipementDto.designation] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * [RefEquipementDto.referenceexterne] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the referenceexterne
	 */
	public String getReferenceexterne() {
		return referenceexterne;
	}

	/**
	 * [RefEquipementDto.referenceexterne] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param referenceexterne
	 *            the referenceexterne to set
	 */
	public void setReferenceexterne(String referenceexterne) {
		this.referenceexterne = referenceexterne;
	}

	/**
	 * [RefEquipementDto.dateMiseDispo] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the dateMiseDispo
	 */
	public Date getDateMiseDispo() {
		return dateMiseDispo;
	}

	/**
	 * [RefEquipementDto.dateMiseDispo] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param dateMiseDispo
	 *            the dateMiseDispo to set
	 */
	public void setDateMiseDispo(Date dateMiseDispo) {
		this.dateMiseDispo = dateMiseDispo;
	}

	/**
	 * [RefEquipementDto.reservable] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the reservable
	 */
	public Boolean getReservable() {
		return reservable;
	}

	/**
	 * [RefEquipementDto.reservable] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param reservable
	 *            the reservable to set
	 */
	public void setReservable(Boolean reservable) {
		this.reservable = reservable;
	}

	/**
	 * [RefEquipementDto.familleequipementId] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the familleequipementId
	 */
	public Integer getFamilleequipementId() {
		return familleequipementId;
	}

	/**
	 * [RefEquipementDto.familleequipementId] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param familleequipementId
	 *            the familleequipementId to set
	 */
	public void setFamilleequipementId(Integer familleequipementId) {
		this.familleequipementId = familleequipementId;
	}

	/**
	 * [RefEquipementDto.familleequipementLibelleLongFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the familleequipementLibelleLongFr
	 */
	public String getFamilleequipementLibelleLongFr() {
		return familleequipementLibelleLongFr;
	}

	/**
	 * [RefEquipementDto.familleequipementLibelleLongFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param familleequipementLibelleLongFr
	 *            the familleequipementLibelleLongFr to set
	 */
	public void setFamilleequipementLibelleLongFr(String familleequipementLibelleLongFr) {
		this.familleequipementLibelleLongFr = familleequipementLibelleLongFr;
	}

	/**
	 * [RefEquipementDto.familleequipementLibelleLongAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the familleequipementLibelleLongAr
	 */
	public String getFamilleequipementLibelleLongAr() {
		return familleequipementLibelleLongAr;
	}

	/**
	 * [RefEquipementDto.familleequipementLibelleLongAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param familleequipementLibelleLongAr
	 *            the familleequipementLibelleLongAr to set
	 */
	public void setFamilleequipementLibelleLongAr(String familleequipementLibelleLongAr) {
		this.familleequipementLibelleLongAr = familleequipementLibelleLongAr;
	}

	/**
	 * [RefEquipementDto.familleequipementLibelleCourtFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the familleequipementLibelleCourtFr
	 */
	public String getFamilleequipementLibelleCourtFr() {
		return familleequipementLibelleCourtFr;
	}

	/**
	 * [RefEquipementDto.familleequipementLibelleCourtFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param familleequipementLibelleCourtFr
	 *            the familleequipementLibelleCourtFr to set
	 */
	public void setFamilleequipementLibelleCourtFr(String familleequipementLibelleCourtFr) {
		this.familleequipementLibelleCourtFr = familleequipementLibelleCourtFr;
	}

	/**
	 * [RefEquipementDto.familleequipementLibelleCourtAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the familleequipementLibelleCourtAr
	 */
	public String getFamilleequipementLibelleCourtAr() {
		return familleequipementLibelleCourtAr;
	}

	/**
	 * [RefEquipementDto.familleequipementLibelleCourtAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param familleequipementLibelleCourtAr
	 *            the familleequipementLibelleCourtAr to set
	 */
	public void setFamilleequipementLibelleCourtAr(String familleequipementLibelleCourtAr) {
		this.familleequipementLibelleCourtAr = familleequipementLibelleCourtAr;
	}

	/**
	 * [RefEquipementDto.familleequipementLibelleCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the familleequipementLibelleCode
	 */
	public String getFamilleequipementLibelleCode() {
		return familleequipementCode;
	}

	/**
	 * [RefEquipementDto.familleequipementLibelleCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param familleequipementLibelleCode
	 *            the familleequipementLibelleCode to set
	 */
	public void setFamilleequipementLibelleCode(String familleequipementLibelleCode) {
		this.familleequipementCode = familleequipementLibelleCode;
	}

	/**
	 * [RefEquipementDto.sousfamilleequipementId] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the sousfamilleequipementId
	 */
	public Integer getSousfamilleequipementId() {
		return sousfamilleequipementId;
	}

	/**
	 * [RefEquipementDto.sousfamilleequipementId] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param sousfamilleequipementId
	 *            the sousfamilleequipementId to set
	 */
	public void setSousfamilleequipementId(Integer sousfamilleequipementId) {
		this.sousfamilleequipementId = sousfamilleequipementId;
	}

	/**
	 * [RefEquipementDto.sousfamilleequipementLibelleLongFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the sousfamilleequipementLibelleLongFr
	 */
	public String getSousfamilleequipementLibelleLongFr() {
		return sousfamilleequipementLibelleLongFr;
	}

	/**
	 * [RefEquipementDto.sousfamilleequipementLibelleLongFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param sousfamilleequipementLibelleLongFr
	 *            the sousfamilleequipementLibelleLongFr to set
	 */
	public void setSousfamilleequipementLibelleLongFr(String sousfamilleequipementLibelleLongFr) {
		this.sousfamilleequipementLibelleLongFr = sousfamilleequipementLibelleLongFr;
	}

	/**
	 * [RefEquipementDto.sousfamilleequipementLibelleLongAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the sousfamilleequipementLibelleLongAr
	 */
	public String getSousfamilleequipementLibelleLongAr() {
		return sousfamilleequipementLibelleLongAr;
	}

	/**
	 * [RefEquipementDto.sousfamilleequipementLibelleLongAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param sousfamilleequipementLibelleLongAr
	 *            the sousfamilleequipementLibelleLongAr to set
	 */
	public void setSousfamilleequipementLibelleLongAr(String sousfamilleequipementLibelleLongAr) {
		this.sousfamilleequipementLibelleLongAr = sousfamilleequipementLibelleLongAr;
	}

	/**
	 * [RefEquipementDto.sousfamilleequipementLibelleCourtFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the sousfamilleequipementLibelleCourtFr
	 */
	public String getSousfamilleequipementLibelleCourtFr() {
		return sousfamilleequipementLibelleCourtFr;
	}

	/**
	 * [RefEquipementDto.sousfamilleequipementLibelleCourtFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param sousfamilleequipementLibelleCourtFr
	 *            the sousfamilleequipementLibelleCourtFr to set
	 */
	public void setSousfamilleequipementLibelleCourtFr(String sousfamilleequipementLibelleCourtFr) {
		this.sousfamilleequipementLibelleCourtFr = sousfamilleequipementLibelleCourtFr;
	}

	/**
	 * [RefEquipementDto.sousfamilleequipementLibelleCourtAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the sousfamilleequipementLibelleCourtAr
	 */
	public String getSousfamilleequipementLibelleCourtAr() {
		return sousfamilleequipementLibelleCourtAr;
	}

	/**
	 * [RefEquipementDto.sousfamilleequipementLibelleCourtAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param sousfamilleequipementLibelleCourtAr
	 *            the sousfamilleequipementLibelleCourtAr to set
	 */
	public void setSousfamilleequipementLibelleCourtAr(String sousfamilleequipementLibelleCourtAr) {
		this.sousfamilleequipementLibelleCourtAr = sousfamilleequipementLibelleCourtAr;
	}

	/**
	 * [RefEquipementDto.sousfamilleequipementLibelleCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @return the sousfamilleequipementLibelleCode
	 */
	public String getSousfamilleequipementLibelleCode() {
		return sousfamilleequipementCode;
	}

	/**
	 * [RefEquipementDto.sousfamilleequipementLibelleCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 17:00:28
	 * @param sousfamilleequipementLibelleCode
	 *            the sousfamilleequipementLibelleCode to set
	 */
	public void setSousfamilleequipementLibelleCode(String sousfamilleequipementLibelleCode) {
		this.sousfamilleequipementCode = sousfamilleequipementLibelleCode;
	}

	/**
	 * [RefEquipementDto.familleequipementCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 mars 2014 16:28:28
	 * @return the familleequipementCode
	 */
	public String getFamilleequipementCode() {
		return familleequipementCode;
	}

	/**
	 * [RefEquipementDto.familleequipementCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 mars 2014 16:28:28
	 * @param familleequipementCode
	 *            the familleequipementCode to set
	 */
	public void setFamilleequipementCode(String familleequipementCode) {
		this.familleequipementCode = familleequipementCode;
	}

	/**
	 * [RefEquipementDto.sousfamilleequipementCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 mars 2014 16:28:28
	 * @return the sousfamilleequipementCode
	 */
	public String getSousfamilleequipementCode() {
		return sousfamilleequipementCode;
	}

	/**
	 * [RefEquipementDto.sousfamilleequipementCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 mars 2014 16:28:28
	 * @param sousfamilleequipementCode
	 *            the sousfamilleequipementCode to set
	 */
	public void setSousfamilleequipementCode(String sousfamilleequipementCode) {
		this.sousfamilleequipementCode = sousfamilleequipementCode;
	}

	/**
	 * [RefEquipementDto.dateSituation] Getter
	 * 
	 * @author BELBRIK Oualid on : 10 mars 2014 15:05:34
	 * @return the dateSituation
	 */
	public Date getDateSituation() {
		return dateSituation;
	}

	/**
	 * [RefEquipementDto.dateSituation] Setter
	 * 
	 * @author BELBRIK Oualid on : 10 mars 2014 15:05:34
	 * @param dateSituation
	 *            the dateSituation to set
	 */
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}

	/**
	 * [RefEquipementDto.llSituationAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 10 mars 2014 15:05:34
	 * @return the llSituationAr
	 */
	public String getLlSituationAr() {
		return llSituationAr;
	}

	/**
	 * [RefEquipementDto.llSituationAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 10 mars 2014 15:05:34
	 * @param llSituationAr
	 *            the llSituationAr to set
	 */
	public void setLlSituationAr(String llSituationAr) {
		this.llSituationAr = llSituationAr;
	}

	/**
	 * [RefEquipementDto.llSituationFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 10 mars 2014 15:05:34
	 * @return the llSituationFr
	 */
	public String getLlSituationFr() {
		return llSituationFr;
	}

	/**
	 * [RefEquipementDto.llSituationFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 10 mars 2014 15:05:34
	 * @param llSituationFr
	 *            the llSituationFr to set
	 */
	public void setLlSituationFr(String llSituationFr) {
		this.llSituationFr = llSituationFr;
	}

	/**
	 * [RefEquipementDto.idfEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 17:03:16
	 * @return the idfEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [RefEquipementDto.idfEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 17:03:16
	 * @param idfEtablissement
	 *            the idfEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

}