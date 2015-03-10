/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructure.java] 
 * @author MAKERRI Sofiane on : 5 janv. 2014  10:51:38
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author MAKERRI Sofiane on : 5 janv. 2014 10:51:38
 */
@XmlRootElement(name = "RefStructureDto")
public class RefStructureDto implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:18:01
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * [RefStructure.RefStructure()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 10:51:38
	 */
	private Integer id;
	private String identifiant;
	private Date dateCreation;
	private String decret;
	private String lcStructureArabe;
	private String lcStructureLatin;
	private String llStructureArabe;
	private String llStructureLatin;
	private String nif;
	private String nis;
	private String nrc;
	private String nss;

	/****** structure m�re *****/
	private Integer idMotherStructure;
	private String lcArMotherStructure;
	private String lcLtMotherStructure;
	private String llArMotherStructure;
	private String llLtMotherStructure;
	/***** status *****/
	private Integer statusId;
	private String statusLibelleLongFr;
	private String statusLibelleLongAr;
	private String statusLibelleCourtFr;
	private String statusLibelleCourtAr;
	private String statusCode;
	/***** structure type *****/
	private Integer typeId;
	private String typeLibelleLongFr;
	private String typeLibelleLongAr;
	private String typeLibelleCourtFr;
	private String typeLibelleCourtAr;
	private String typeCode;
	/***** forme juridique *****/
	private Integer formeJuridiqueId;
	private String formeJuridiqueLibelleLongFr;
	private String formeJuridiqueLibelleLongAr;
	private String formeJuridiqueLibelleCourtFr;
	private String formeJuridiqueLibelleCourtAr;
	private String formeJuridiqueCode;

	/**
	 * Current Situation
	 * 
	 */
	private Date dateSituation;
	private String llSituationAr;
	private String llSituationFr;
	/***** etablissement *********/
	private Integer idEtablissement;

	/***** to complete coordonnes ******/
	/*
	 * private Set<RefCoordonnee> refCoordonnees = new
	 * HashSet<RefCoordonnee>(0);
	 */

	public RefStructureDto() {

	}

	/**
	 * [RefStructureDto.RefStructureDto()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 29 janv. 2015 13:22:12
	 * @param id
	 * @param identifiant
	 * @param lcStructureArabe
	 * @param llStructureArabe
	 * @param lcStructureLatin
	 * @param llStructureLatin
	 */
	public RefStructureDto(Integer id, String identifiant,
			String lcStructureArabe, String llStructureArabe,
			String lcStructureLatin, String llStructureLatin) {
		this.id = id;
		this.identifiant = identifiant;
		this.lcStructureArabe = lcStructureArabe;
		this.llStructureArabe = llStructureArabe;
		this.lcStructureLatin = lcStructureLatin;
		this.llStructureLatin = llStructureLatin;

	}

	/**
	 * [RefStructureDto.RefStructureDto()] Constructor
	 * @author MAKERRI Sofiane  on : 29 janv. 2015  13:28:32
	 * @param refAffectationDto	
	 */
	public RefStructureDto(RefAffectationDto refAffectationDto) {
		if (refAffectationDto != null) {
			this.id = refAffectationDto.getIdStructure();
			this.identifiant = refAffectationDto.getIdentifiantStructure();
			this.lcStructureArabe = refAffectationDto.getLcStructureArabe();
			this.llStructureArabe = refAffectationDto.getLlStructureArabe();
			this.lcStructureLatin = refAffectationDto.getLcStructureLatin();
			this.llStructureLatin = refAffectationDto.getLlStructureLatin();
		}
	}

	/**
	 * [RefStructureDto.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 mai 2014 11:58:12
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefStructureDto.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 mai 2014 11:58:12
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefStructureDto.identifiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * [RefStructureDto.identifiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @param identifiant
	 *            the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * [RefStructureDto.dateCreation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * [RefStructureDto.dateCreation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @param dateCreation
	 *            the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * [RefStructureDto.decret] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @return the decret
	 */
	public String getDecret() {
		return decret;
	}

	/**
	 * [RefStructureDto.decret] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @param decret
	 *            the decret to set
	 */
	public void setDecret(String decret) {
		this.decret = decret;
	}

	/**
	 * [RefStructureDto.lcStructureArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @return the lcStructureArabe
	 */
	public String getLcStructureArabe() {
		return lcStructureArabe;
	}

	/**
	 * [RefStructureDto.lcStructureArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @param lcStructureArabe
	 *            the lcStructureArabe to set
	 */
	public void setLcStructureArabe(String lcStructureArabe) {
		this.lcStructureArabe = lcStructureArabe;
	}

	/**
	 * [RefStructureDto.lcStructureLatin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @return the lcStructureLatin
	 */
	public String getLcStructureLatin() {
		return lcStructureLatin;
	}

	/**
	 * [RefStructureDto.lcStructureLatin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @param lcStructureLatin
	 *            the lcStructureLatin to set
	 */
	public void setLcStructureLatin(String lcStructureLatin) {
		this.lcStructureLatin = lcStructureLatin;
	}

	/**
	 * [RefStructureDto.llStructureArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @return the llStructureArabe
	 */
	public String getLlStructureArabe() {
		return llStructureArabe;
	}

	/**
	 * [RefStructureDto.llStructureArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @param llStructureArabe
	 *            the llStructureArabe to set
	 */
	public void setLlStructureArabe(String llStructureArabe) {
		this.llStructureArabe = llStructureArabe;
	}

	/**
	 * [RefStructureDto.llStructureLatin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @return the llStructureLatin
	 */
	public String getLlStructureLatin() {
		return llStructureLatin;
	}

	/**
	 * [RefStructureDto.llStructureLatin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @param llStructureLatin
	 *            the llStructureLatin to set
	 */
	public void setLlStructureLatin(String llStructureLatin) {
		this.llStructureLatin = llStructureLatin;
	}

	/**
	 * [RefStructureDto.nif] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @return the nif
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * [RefStructureDto.nif] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @param nif
	 *            the nif to set
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}

	/**
	 * [RefStructureDto.nis] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @return the nis
	 */
	public String getNis() {
		return nis;
	}

	/**
	 * [RefStructureDto.nis] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @param nis
	 *            the nis to set
	 */
	public void setNis(String nis) {
		this.nis = nis;
	}

	/**
	 * [RefStructureDto.nrc] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @return the nrc
	 */
	public String getNrc() {
		return nrc;
	}

	/**
	 * [RefStructureDto.nrc] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @param nrc
	 *            the nrc to set
	 */
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	/**
	 * [RefStructureDto.nss] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @return the nss
	 */
	public String getNss() {
		return nss;
	}

	/**
	 * [RefStructureDto.nss] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @param nss
	 *            the nss to set
	 */
	public void setNss(String nss) {
		this.nss = nss;
	}

	/**
	 * [RefStructureDto.idMotherStructure] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @return the idMotherStructure
	 */
	public Integer getIdMotherStructure() {
		return idMotherStructure;
	}

	/**
	 * [RefStructureDto.idMotherStructure] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:53:06
	 * @param idMotherStructure
	 *            the idMotherStructure to set
	 */
	public void setIdMotherStructure(Integer idMotherStructure) {
		this.idMotherStructure = idMotherStructure;
	}

	/**
	 * @return the lcArMotherStructure
	 */
	public String getLcArMotherStructure() {
		return lcArMotherStructure;
	}

	/**
	 * @param lcArMotherStructure
	 *            the lcArMotherStructure to set
	 */
	public void setLcArMotherStructure(String lcArMotherStructure) {
		this.lcArMotherStructure = lcArMotherStructure;
	}

	/**
	 * @return the lcLtMotherStructure
	 */
	public String getLcLtMotherStructure() {
		return lcLtMotherStructure;
	}

	/**
	 * @param lcLtMotherStructure
	 *            the lcLtMotherStructure to set
	 */
	public void setLcLtMotherStructure(String lcLtMotherStructure) {
		this.lcLtMotherStructure = lcLtMotherStructure;
	}

	/**
	 * @return the llArMotherStructure
	 */
	public String getLlArMotherStructure() {
		return llArMotherStructure;
	}

	/**
	 * @param llArMotherStructure
	 *            the llArMotherStructure to set
	 */
	public void setLlArMotherStructure(String llArMotherStructure) {
		this.llArMotherStructure = llArMotherStructure;
	}

	/**
	 * @return the llLtMotherStructure
	 */
	public String getLlLtMotherStructure() {
		return llLtMotherStructure;
	}

	/**
	 * @param llLtMotherStructure
	 *            the llLtMotherStructure to set
	 */
	public void setLlLtMotherStructure(String llLtMotherStructure) {
		this.llLtMotherStructure = llLtMotherStructure;
	}

	/**
	 * [RefStructureDto.dateSituation] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 10:38:48
	 * @return the dateSituation
	 */
	public Date getDateSituation() {
		return dateSituation;
	}

	/**
	 * [RefStructureDto.dateSituation] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 10:38:48
	 * @param dateSituation
	 *            the dateSituation to set
	 */
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}

	/**
	 * [RefStructureDto.llSituationAr] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 10:38:48
	 * @return the llSituationAr
	 */
	public String getLlSituationAr() {
		return llSituationAr;
	}

	/**
	 * [RefStructureDto.llSituationAr] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 10:38:48
	 * @param llSituationAr
	 *            the llSituationAr to set
	 */
	public void setLlSituationAr(String llSituationAr) {
		this.llSituationAr = llSituationAr;
	}

	/**
	 * [RefStructureDto.llSituationFr] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 10:38:48
	 * @return the llSituationFr
	 */
	public String getLlSituationFr() {
		return llSituationFr;
	}

	/**
	 * [RefStructureDto.llSituationFr] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 10:38:48
	 * @param llSituationFr
	 *            the llSituationFr to set
	 */
	public void setLlSituationFr(String llSituationFr) {
		this.llSituationFr = llSituationFr;
	}

	/**
	 * [RefStructureDto.statusId] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the statusId
	 */
	public Integer getStatusId() {
		return statusId;
	}

	/**
	 * [RefStructureDto.statusId] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param statusId
	 *            the statusId to set
	 */
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	/**
	 * [RefStructureDto.statusLibelleLongFr] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the statusLibelleLongFr
	 */
	public String getStatusLibelleLongFr() {
		return statusLibelleLongFr;
	}

	/**
	 * [RefStructureDto.statusLibelleLongFr] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param statusLibelleLongFr
	 *            the statusLibelleLongFr to set
	 */
	public void setStatusLibelleLongFr(String statusLibelleLongFr) {
		this.statusLibelleLongFr = statusLibelleLongFr;
	}

	/**
	 * [RefStructureDto.statusLibelleLongAr] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the statusLibelleLongAr
	 */
	public String getStatusLibelleLongAr() {
		return statusLibelleLongAr;
	}

	/**
	 * [RefStructureDto.statusLibelleLongAr] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param statusLibelleLongAr
	 *            the statusLibelleLongAr to set
	 */
	public void setStatusLibelleLongAr(String statusLibelleLongAr) {
		this.statusLibelleLongAr = statusLibelleLongAr;
	}

	/**
	 * [RefStructureDto.statusLibelleCourtFr] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the statusLibelleCourtFr
	 */
	public String getStatusLibelleCourtFr() {
		return statusLibelleCourtFr;
	}

	/**
	 * [RefStructureDto.statusLibelleCourtFr] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param statusLibelleCourtFr
	 *            the statusLibelleCourtFr to set
	 */
	public void setStatusLibelleCourtFr(String statusLibelleCourtFr) {
		this.statusLibelleCourtFr = statusLibelleCourtFr;
	}

	/**
	 * [RefStructureDto.statusLibelleCourtAr] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the statusLibelleCourtAr
	 */
	public String getStatusLibelleCourtAr() {
		return statusLibelleCourtAr;
	}

	/**
	 * [RefStructureDto.statusLibelleCourtAr] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param statusLibelleCourtAr
	 *            the statusLibelleCourtAr to set
	 */
	public void setStatusLibelleCourtAr(String statusLibelleCourtAr) {
		this.statusLibelleCourtAr = statusLibelleCourtAr;
	}

	/**
	 * [RefStructureDto.statusCode] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * [RefStructureDto.statusCode] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * [RefStructureDto.typeId] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * [RefStructureDto.typeId] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * [RefStructureDto.typeLibelleLongFr] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the typeLibelleLongFr
	 */
	public String getTypeLibelleLongFr() {
		return typeLibelleLongFr;
	}

	/**
	 * [RefStructureDto.typeLibelleLongFr] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param typeLibelleLongFr
	 *            the typeLibelleLongFr to set
	 */
	public void setTypeLibelleLongFr(String typeLibelleLongFr) {
		this.typeLibelleLongFr = typeLibelleLongFr;
	}

	/**
	 * [RefStructureDto.typeLibelleLongAr] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the typeLibelleLongAr
	 */
	public String getTypeLibelleLongAr() {
		return typeLibelleLongAr;
	}

	/**
	 * [RefStructureDto.typeLibelleLongAr] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param typeLibelleLongAr
	 *            the typeLibelleLongAr to set
	 */
	public void setTypeLibelleLongAr(String typeLibelleLongAr) {
		this.typeLibelleLongAr = typeLibelleLongAr;
	}

	/**
	 * [RefStructureDto.typeLibelleCourtFr] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the typeLibelleCourtFr
	 */
	public String getTypeLibelleCourtFr() {
		return typeLibelleCourtFr;
	}

	/**
	 * [RefStructureDto.typeLibelleCourtFr] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param typeLibelleCourtFr
	 *            the typeLibelleCourtFr to set
	 */
	public void setTypeLibelleCourtFr(String typeLibelleCourtFr) {
		this.typeLibelleCourtFr = typeLibelleCourtFr;
	}

	/**
	 * [RefStructureDto.typeLibelleCourtAr] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the typeLibelleCourtAr
	 */
	public String getTypeLibelleCourtAr() {
		return typeLibelleCourtAr;
	}

	/**
	 * [RefStructureDto.typeLibelleCourtAr] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param typeLibelleCourtAr
	 *            the typeLibelleCourtAr to set
	 */
	public void setTypeLibelleCourtAr(String typeLibelleCourtAr) {
		this.typeLibelleCourtAr = typeLibelleCourtAr;
	}

	/**
	 * [RefStructureDto.typeCode] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * [RefStructureDto.typeCode] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param typeCode
	 *            the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * [RefStructureDto.formeJuridiqueId] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the formeJuridiqueId
	 */
	public Integer getFormeJuridiqueId() {
		return formeJuridiqueId;
	}

	/**
	 * [RefStructureDto.formeJuridiqueId] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param formeJuridiqueId
	 *            the formeJuridiqueId to set
	 */
	public void setFormeJuridiqueId(Integer formeJuridiqueId) {
		this.formeJuridiqueId = formeJuridiqueId;
	}

	/**
	 * [RefStructureDto.formeJuridiqueLibelleLongFr] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the formeJuridiqueLibelleLongFr
	 */
	public String getFormeJuridiqueLibelleLongFr() {
		return formeJuridiqueLibelleLongFr;
	}

	/**
	 * [RefStructureDto.formeJuridiqueLibelleLongFr] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param formeJuridiqueLibelleLongFr
	 *            the formeJuridiqueLibelleLongFr to set
	 */
	public void setFormeJuridiqueLibelleLongFr(
			String formeJuridiqueLibelleLongFr) {
		this.formeJuridiqueLibelleLongFr = formeJuridiqueLibelleLongFr;
	}

	/**
	 * [RefStructureDto.formeJuridiqueLibelleLongAr] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the formeJuridiqueLibelleLongAr
	 */
	public String getFormeJuridiqueLibelleLongAr() {
		return formeJuridiqueLibelleLongAr;
	}

	/**
	 * [RefStructureDto.formeJuridiqueLibelleLongAr] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param formeJuridiqueLibelleLongAr
	 *            the formeJuridiqueLibelleLongAr to set
	 */
	public void setFormeJuridiqueLibelleLongAr(
			String formeJuridiqueLibelleLongAr) {
		this.formeJuridiqueLibelleLongAr = formeJuridiqueLibelleLongAr;
	}

	/**
	 * [RefStructureDto.formeJuridiqueLibelleCourtFr] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the formeJuridiqueLibelleCourtFr
	 */
	public String getFormeJuridiqueLibelleCourtFr() {
		return formeJuridiqueLibelleCourtFr;
	}

	/**
	 * [RefStructureDto.formeJuridiqueLibelleCourtFr] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param formeJuridiqueLibelleCourtFr
	 *            the formeJuridiqueLibelleCourtFr to set
	 */
	public void setFormeJuridiqueLibelleCourtFr(
			String formeJuridiqueLibelleCourtFr) {
		this.formeJuridiqueLibelleCourtFr = formeJuridiqueLibelleCourtFr;
	}

	/**
	 * [RefStructureDto.formeJuridiqueLibelleCourtAr] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the formeJuridiqueLibelleCourtAr
	 */
	public String getFormeJuridiqueLibelleCourtAr() {
		return formeJuridiqueLibelleCourtAr;
	}

	/**
	 * [RefStructureDto.formeJuridiqueLibelleCourtAr] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param formeJuridiqueLibelleCourtAr
	 *            the formeJuridiqueLibelleCourtAr to set
	 */
	public void setFormeJuridiqueLibelleCourtAr(
			String formeJuridiqueLibelleCourtAr) {
		this.formeJuridiqueLibelleCourtAr = formeJuridiqueLibelleCourtAr;
	}

	/**
	 * [RefStructureDto.formeJuridiqueCode] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @return the formeJuridiqueCode
	 */
	public String getFormeJuridiqueCode() {
		return formeJuridiqueCode;
	}

	/**
	 * [RefStructureDto.formeJuridiqueCode] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 11:17:43
	 * @param formeJuridiqueCode
	 *            the formeJuridiqueCode to set
	 */
	public void setFormeJuridiqueCode(String formeJuridiqueCode) {
		this.formeJuridiqueCode = formeJuridiqueCode;
	}

	/**
	 * [RefStructureDto.idEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 mai 2014 15:35:11
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [RefStructureDto.idEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 mai 2014 15:35:11
	 * @param idEtablissement
	 *            the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

}
