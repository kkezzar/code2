/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefLieuDto.java] 
 * @author BELBRIK Oualid on : 11 f�vr. 2014 11:42:34
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author BELBRIK Oualid on : 11 f�vr. 2014 11:42:34
 */
@XmlRootElement(name = "RefLieuDto")
public class RefLieuDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String identifiant;
	private String designation;
	private String designationArabe;
	private String description;
	private String descriptionArabe;
	private Boolean acceshandicape;
	private Boolean reservable;
	private String codePorte;
	private Short superficie;
	private Short capacite;
	private Short etage;
	private String adresse;
	private String adresseAr;
	private Integer telephone;

	/****** lieu d'appartenance *****/
	private Integer idPereLieu;
	private String desigPereLieu;
	private String desigArPereLieu;

	/***** lieu type *****/
	private Integer typelieuId;
	private String typelieuLibelleLongFr;
	private String typelieuLibelleLongAr;
	private String typelieuLibelleCourtFr;
	private String typelieuLibelleCourtAr;
	private String typelieuCode;

	/***** Implantation type *****/
	private Integer typeImplantId;
	private String typeImplantLibelleLongFr;
	private String typeImplantLibelleLongAr;
	private String typeImplantLibelleCourtFr;
	private String typeImplantLibelleCourtAr;
	private String typeImplantCode;

	/***** Acces type *****/
	private Integer typeAccesId;
	private String typeAccesLibelleLongFr;
	private String typeAccesLibelleLongAr;
	private String typeAccesLibelleCourtFr;
	private String typeAccesLibelleCourtAr;
	private String typeAccesCode;

	/***** Salle type *****/
	private Integer typeSalleId;
	private String typeSalleLibelleLongFr;
	private String typeSalleLibelleLongAr;
	private String typeSalleLibelleCourtFr;
	private String typeSalleLibelleCourtAr;
	private String typeSalleCode;

	/*** Current Situation **/
	private Date dateSituation;
	private String llSituationAr;
	private String llSituationFr;

	/** * Structure */
	private Integer idStructure;
	private String llStructureArabe;
	private String llStructureLatin;

	// private Nomenclature nomenclatureByDaira;
	private Integer dairaId;
	private String dairaLibelleLongFr;
	private String dairaLibelleLongAr;
	private String dairaLibelleCourtFr;
	private String dairaLibelleCourtAr;
	private String dairaCode;
	// private Nomenclature nomenclatureByCommune;
	private Integer communeId;
	private String communeLibelleLongFr;
	private String communeLibelleLongAr;
	private String communeLibelleCourtFr;
	private String communeLibelleCourtAr;
	private String communeCode;
	// private Nomenclature nomenclatureByWilaya;
	private Integer wilayaId;
	private String wilayaLibelleLongFr;
	private String wilayaLibelleLongAr;
	private String wilayaLibelleCourtFr;
	private String wilayaLibelleCourtAr;
	private String wilayaCode;

	/********** etablissement ****************/
	private Integer idEtablissement;
	
	

	/**
	 * [RefLieuDto.id] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  11:56:57
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefLieuDto.id] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  11:56:57
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefLieuDto.designation] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * [RefLieuDto.idStructure] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 f�vr. 2014 18:41:46
	 * @return the idStructure
	 */
	public Integer getIdStructure() {
		return idStructure;
	}

	/**
	 * [RefLieuDto.idStructure] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 f�vr. 2014 18:41:46
	 * @param idStructure
	 *            the idStructure to set
	 */
	public void setIdStructure(Integer idStructure) {
		this.idStructure = idStructure;
	}

	/**
	 * [RefLieuDto.llStructureArabe] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 f�vr. 2014 18:41:46
	 * @return the llStructureArabe
	 */
	public String getLlStructureArabe() {
		return llStructureArabe;
	}

	/**
	 * [RefLieuDto.llStructureArabe] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 f�vr. 2014 18:41:46
	 * @param llStructureArabe
	 *            the llStructureArabe to set
	 */
	public void setLlStructureArabe(String llStructureArabe) {
		this.llStructureArabe = llStructureArabe;
	}

	/**
	 * [RefLieuDto.llStructureLatin] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 f�vr. 2014 18:41:46
	 * @return the llStructureLatin
	 */
	public String getLlStructureLatin() {
		return llStructureLatin;
	}

	/**
	 * [RefLieuDto.llStructureLatin] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 f�vr. 2014 18:41:46
	 * @param llStructureLatin
	 *            the llStructureLatin to set
	 */
	public void setLlStructureLatin(String llStructureLatin) {
		this.llStructureLatin = llStructureLatin;
	}

	/**
	 * [RefLieuDto.designation] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * [RefLieuDto.designationArabe] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the designationArabe
	 */
	public String getDesignationArabe() {
		return designationArabe;
	}

	/**
	 * [RefLieuDto.designationArabe] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param designationArabe
	 *            the designationArabe to set
	 */
	public void setDesignationArabe(String designationArabe) {
		this.designationArabe = designationArabe;
	}

	/**
	 * [RefLieuDto.description] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * [RefLieuDto.description] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [RefLieuDto.descriptionArabe] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the descriptionArabe
	 */
	public String getDescriptionArabe() {
		return descriptionArabe;
	}

	/**
	 * [RefLieuDto.descriptionArabe] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param descriptionArabe
	 *            the descriptionArabe to set
	 */
	public void setDescriptionArabe(String descriptionArabe) {
		this.descriptionArabe = descriptionArabe;
	}

	/**
	 * [RefLieuDto.acceshandicape] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the acceshandicape
	 */
	public Boolean getAcceshandicape() {
		return acceshandicape;
	}

	/**
	 * [RefLieuDto.acceshandicape] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param acceshandicape
	 *            the acceshandicape to set
	 */
	public void setAcceshandicape(Boolean acceshandicape) {
		this.acceshandicape = acceshandicape;
	}

	/**
	 * [RefLieuDto.reservable] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the reservable
	 */
	public Boolean getReservable() {
		return reservable;
	}

	/**
	 * [RefLieuDto.reservable] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param reservable
	 *            the reservable to set
	 */
	public void setReservable(Boolean reservable) {
		this.reservable = reservable;
	}

	/**
	 * [RefLieuDto.codePorte] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the codePorte
	 */
	public String getCodePorte() {
		return codePorte;
	}

	/**
	 * [RefLieuDto.codePorte] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param codePorte
	 *            the codePorte to set
	 */
	public void setCodePorte(String codePorte) {
		this.codePorte = codePorte;
	}

	/**
	 * [RefLieuDto.superficie] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the superficie
	 */
	public Short getSuperficie() {
		return superficie;
	}

	/**
	 * [RefLieuDto.superficie] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param superficie
	 *            the superficie to set
	 */
	public void setSuperficie(Short superficie) {
		this.superficie = superficie;
	}

	/**
	 * [RefLieuDto.capacite] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the capacite
	 */
	public Short getCapacite() {
		return capacite;
	}

	/**
	 * [RefLieuDto.capacite] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param capacite
	 *            the capacite to set
	 */
	public void setCapacite(Short capacite) {
		this.capacite = capacite;
	}

	/**
	 * [RefLieuDto.etage] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the etage
	 */
	public Short getEtage() {
		return etage;
	}

	/**
	 * [RefLieuDto.etage] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param etage
	 *            the etage to set
	 */
	public void setEtage(Short etage) {
		this.etage = etage;
	}

	/**
	 * [RefLieuDto.idPerelieu] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the idPerelieu
	 */
	public Integer getIdPereLieu() {
		return idPereLieu;
	}

	/**
	 * [RefLieuDto.idPerelieu] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param idPerelieu
	 *            the idPerelieu to set
	 */
	public void setIdPereLieu(Integer idPereLieu) {
		this.idPereLieu = idPereLieu;
	}

	/**
	 * [RefLieuDto.desigPereLieu] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the desigPereLieu
	 */
	public String getDesigPereLieu() {
		return desigPereLieu;
	}

	/**
	 * [RefLieuDto.desigPereLieu] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param desigPereLieu
	 *            the desigPereLieu to set
	 */
	public void setDesigPereLieu(String desigPereLieu) {
		this.desigPereLieu = desigPereLieu;
	}

	/**
	 * [RefLieuDto.desigArPereLieu] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the desigArPereLieu
	 */
	public String getDesigArPereLieu() {
		return desigArPereLieu;
	}

	/**
	 * [RefLieuDto.desigArPereLieu] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param desigArPereLieu
	 *            the desigArPereLieu to set
	 */
	public void setDesigArPereLieu(String desigArPereLieu) {
		this.desigArPereLieu = desigArPereLieu;
	}

	/**
	 * [RefLieuDto.typelieuId] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typelieuId
	 */
	public Integer getTypelieuId() {
		return typelieuId;
	}

	/**
	 * [RefLieuDto.typelieuId] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typelieuId
	 *            the typelieuId to set
	 */
	public void setTypelieuId(Integer typelieuId) {
		this.typelieuId = typelieuId;
	}

	/**
	 * [RefLieuDto.typelieuLibelleLongFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typelieuLibelleLongFr
	 */
	public String getTypelieuLibelleLongFr() {
		return typelieuLibelleLongFr;
	}

	/**
	 * [RefLieuDto.typelieuLibelleLongFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typelieuLibelleLongFr
	 *            the typelieuLibelleLongFr to set
	 */
	public void setTypelieuLibelleLongFr(String typelieuLibelleLongFr) {
		this.typelieuLibelleLongFr = typelieuLibelleLongFr;
	}

	/**
	 * [RefLieuDto.typelieuLibelleLongAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typelieuLibelleLongAr
	 */
	public String getTypelieuLibelleLongAr() {
		return typelieuLibelleLongAr;
	}

	/**
	 * [RefLieuDto.typelieuLibelleLongAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typelieuLibelleLongAr
	 *            the typelieuLibelleLongAr to set
	 */
	public void setTypelieuLibelleLongAr(String typelieuLibelleLongAr) {
		this.typelieuLibelleLongAr = typelieuLibelleLongAr;
	}

	/**
	 * [RefLieuDto.typelieuLibelleCourtFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typelieuLibelleCourtFr
	 */
	public String getTypelieuLibelleCourtFr() {
		return typelieuLibelleCourtFr;
	}

	/**
	 * [RefLieuDto.typelieuLibelleCourtFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typelieuLibelleCourtFr
	 *            the typelieuLibelleCourtFr to set
	 */
	public void setTypelieuLibelleCourtFr(String typelieuLibelleCourtFr) {
		this.typelieuLibelleCourtFr = typelieuLibelleCourtFr;
	}

	/**
	 * [RefLieuDto.typelieuLibelleCourtAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typelieuLibelleCourtAr
	 */
	public String getTypelieuLibelleCourtAr() {
		return typelieuLibelleCourtAr;
	}

	/**
	 * [RefLieuDto.typelieuLibelleCourtAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typelieuLibelleCourtAr
	 *            the typelieuLibelleCourtAr to set
	 */
	public void setTypelieuLibelleCourtAr(String typelieuLibelleCourtAr) {
		this.typelieuLibelleCourtAr = typelieuLibelleCourtAr;
	}

	/**
	 * [RefLieuDto.typelieuCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typelieuCode
	 */
	public String getTypelieuCode() {
		return typelieuCode;
	}

	/**
	 * [RefLieuDto.typelieuCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typelieuCode
	 *            the typelieuCode to set
	 */
	public void setTypelieuCode(String typelieuCode) {
		this.typelieuCode = typelieuCode;
	}

	/**
	 * [RefLieuDto.typeImplantId] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeImplantId
	 */
	public Integer getTypeImplantId() {
		return typeImplantId;
	}

	/**
	 * [RefLieuDto.typeImplantId] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeImplantId
	 *            the typeImplantId to set
	 */
	public void setTypeImplantId(Integer typeImplantId) {
		this.typeImplantId = typeImplantId;
	}

	/**
	 * [RefLieuDto.typeImplantLibelleLongFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeImplantLibelleLongFr
	 */
	public String getTypeImplantLibelleLongFr() {
		return typeImplantLibelleLongFr;
	}

	/**
	 * [RefLieuDto.typeImplantLibelleLongFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeImplantLibelleLongFr
	 *            the typeImplantLibelleLongFr to set
	 */
	public void setTypeImplantLibelleLongFr(String typeImplantLibelleLongFr) {
		this.typeImplantLibelleLongFr = typeImplantLibelleLongFr;
	}

	/**
	 * [RefLieuDto.typeImplantLibelleLongAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeImplantLibelleLongAr
	 */
	public String getTypeImplantLibelleLongAr() {
		return typeImplantLibelleLongAr;
	}

	/**
	 * [RefLieuDto.typeImplantLibelleLongAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeImplantLibelleLongAr
	 *            the typeImplantLibelleLongAr to set
	 */
	public void setTypeImplantLibelleLongAr(String typeImplantLibelleLongAr) {
		this.typeImplantLibelleLongAr = typeImplantLibelleLongAr;
	}

	/**
	 * [RefLieuDto.typeImplantLibelleCourtFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeImplantLibelleCourtFr
	 */
	public String getTypeImplantLibelleCourtFr() {
		return typeImplantLibelleCourtFr;
	}

	/**
	 * [RefLieuDto.typeImplantLibelleCourtFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeImplantLibelleCourtFr
	 *            the typeImplantLibelleCourtFr to set
	 */
	public void setTypeImplantLibelleCourtFr(String typeImplantLibelleCourtFr) {
		this.typeImplantLibelleCourtFr = typeImplantLibelleCourtFr;
	}

	/**
	 * [RefLieuDto.typeImplantLibelleCourtAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeImplantLibelleCourtAr
	 */
	public String getTypeImplantLibelleCourtAr() {
		return typeImplantLibelleCourtAr;
	}

	/**
	 * [RefLieuDto.typeImplantLibelleCourtAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeImplantLibelleCourtAr
	 *            the typeImplantLibelleCourtAr to set
	 */
	public void setTypeImplantLibelleCourtAr(String typeImplantLibelleCourtAr) {
		this.typeImplantLibelleCourtAr = typeImplantLibelleCourtAr;
	}

	/**
	 * [RefLieuDto.typeImplantCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeImplantCode
	 */
	public String getTypeImplantCode() {
		return typeImplantCode;
	}

	/**
	 * [RefLieuDto.typeImplantCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeImplantCode
	 *            the typeImplantCode to set
	 */
	public void setTypeImplantCode(String typeImplantCode) {
		this.typeImplantCode = typeImplantCode;
	}

	/**
	 * [RefLieuDto.typeAccesId] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeAccesId
	 */
	public Integer getTypeAccesId() {
		return typeAccesId;
	}

	/**
	 * [RefLieuDto.typeAccesId] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeAccesId
	 *            the typeAccesId to set
	 */
	public void setTypeAccesId(Integer typeAccesId) {
		this.typeAccesId = typeAccesId;
	}

	/**
	 * [RefLieuDto.typeAccesLibelleLongFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeAccesLibelleLongFr
	 */
	public String getTypeAccesLibelleLongFr() {
		return typeAccesLibelleLongFr;
	}

	/**
	 * [RefLieuDto.typeAccesLibelleLongFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeAccesLibelleLongFr
	 *            the typeAccesLibelleLongFr to set
	 */
	public void setTypeAccesLibelleLongFr(String typeAccesLibelleLongFr) {
		this.typeAccesLibelleLongFr = typeAccesLibelleLongFr;
	}

	/**
	 * [RefLieuDto.typeAccesLibelleLongAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeAccesLibelleLongAr
	 */
	public String getTypeAccesLibelleLongAr() {
		return typeAccesLibelleLongAr;
	}

	/**
	 * [RefLieuDto.typeAccesLibelleLongAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeAccesLibelleLongAr
	 *            the typeAccesLibelleLongAr to set
	 */
	public void setTypeAccesLibelleLongAr(String typeAccesLibelleLongAr) {
		this.typeAccesLibelleLongAr = typeAccesLibelleLongAr;
	}

	/**
	 * [RefLieuDto.typeAccesLibelleCourtFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeAccesLibelleCourtFr
	 */
	public String getTypeAccesLibelleCourtFr() {
		return typeAccesLibelleCourtFr;
	}

	/**
	 * [RefLieuDto.typeAccesLibelleCourtFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeAccesLibelleCourtFr
	 *            the typeAccesLibelleCourtFr to set
	 */
	public void setTypeAccesLibelleCourtFr(String typeAccesLibelleCourtFr) {
		this.typeAccesLibelleCourtFr = typeAccesLibelleCourtFr;
	}

	/**
	 * [RefLieuDto.typeAccesLibelleCourtAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeAccesLibelleCourtAr
	 */
	public String getTypeAccesLibelleCourtAr() {
		return typeAccesLibelleCourtAr;
	}

	/**
	 * [RefLieuDto.typeAccesLibelleCourtAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeAccesLibelleCourtAr
	 *            the typeAccesLibelleCourtAr to set
	 */
	public void setTypeAccesLibelleCourtAr(String typeAccesLibelleCourtAr) {
		this.typeAccesLibelleCourtAr = typeAccesLibelleCourtAr;
	}

	/**
	 * [RefLieuDto.typeAccesCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeAccesCode
	 */
	public String getTypeAccesCode() {
		return typeAccesCode;
	}

	/**
	 * [RefLieuDto.typeAccesCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeAccesCode
	 *            the typeAccesCode to set
	 */
	public void setTypeAccesCode(String typeAccesCode) {
		this.typeAccesCode = typeAccesCode;
	}

	/**
	 * [RefLieuDto.typeSalleId] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeSalleId
	 */
	public Integer getTypeSalleId() {
		return typeSalleId;
	}

	/**
	 * [RefLieuDto.typeSalleId] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeSalleId
	 *            the typeSalleId to set
	 */
	public void setTypeSalleId(Integer typeSalleId) {
		this.typeSalleId = typeSalleId;
	}

	/**
	 * [RefLieuDto.typeSalleLibelleLongFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeSalleLibelleLongFr
	 */
	public String getTypeSalleLibelleLongFr() {
		return typeSalleLibelleLongFr;
	}

	/**
	 * [RefLieuDto.typeSalleLibelleLongFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeSalleLibelleLongFr
	 *            the typeSalleLibelleLongFr to set
	 */
	public void setTypeSalleLibelleLongFr(String typeSalleLibelleLongFr) {
		this.typeSalleLibelleLongFr = typeSalleLibelleLongFr;
	}

	/**
	 * [RefLieuDto.typeSalleLibelleLongAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeSalleLibelleLongAr
	 */
	public String getTypeSalleLibelleLongAr() {
		return typeSalleLibelleLongAr;
	}

	/**
	 * [RefLieuDto.typeSalleLibelleLongAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeSalleLibelleLongAr
	 *            the typeSalleLibelleLongAr to set
	 */
	public void setTypeSalleLibelleLongAr(String typeSalleLibelleLongAr) {
		this.typeSalleLibelleLongAr = typeSalleLibelleLongAr;
	}

	/**
	 * [RefLieuDto.typeSalleLibelleCourtFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeSalleLibelleCourtFr
	 */
	public String getTypeSalleLibelleCourtFr() {
		return typeSalleLibelleCourtFr;
	}

	/**
	 * [RefLieuDto.typeSalleLibelleCourtFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeSalleLibelleCourtFr
	 *            the typeSalleLibelleCourtFr to set
	 */
	public void setTypeSalleLibelleCourtFr(String typeSalleLibelleCourtFr) {
		this.typeSalleLibelleCourtFr = typeSalleLibelleCourtFr;
	}

	/**
	 * [RefLieuDto.typeSalleLibelleCourtAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeSalleLibelleCourtAr
	 */
	public String getTypeSalleLibelleCourtAr() {
		return typeSalleLibelleCourtAr;
	}

	/**
	 * [RefLieuDto.typeSalleLibelleCourtAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeSalleLibelleCourtAr
	 *            the typeSalleLibelleCourtAr to set
	 */
	public void setTypeSalleLibelleCourtAr(String typeSalleLibelleCourtAr) {
		this.typeSalleLibelleCourtAr = typeSalleLibelleCourtAr;
	}

	/**
	 * [RefLieuDto.typeSalleCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the typeSalleCode
	 */
	public String getTypeSalleCode() {
		return typeSalleCode;
	}

	/**
	 * [RefLieuDto.typeSalleCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param typeSalleCode
	 *            the typeSalleCode to set
	 */
	public void setTypeSalleCode(String typeSalleCode) {
		this.typeSalleCode = typeSalleCode;
	}

	/**
	 * [RefLieuDto.dateSituation] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the dateSituation
	 */
	public Date getDateSituation() {
		return dateSituation;
	}

	/**
	 * [RefLieuDto.dateSituation] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param dateSituation
	 *            the dateSituation to set
	 */
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}

	/**
	 * [RefLieuDto.llSituationAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the llSituationAr
	 */
	public String getLlSituationAr() {
		return llSituationAr;
	}

	/**
	 * [RefLieuDto.llSituationAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param llSituationAr
	 *            the llSituationAr to set
	 */
	public void setLlSituationAr(String llSituationAr) {
		this.llSituationAr = llSituationAr;
	}

	/**
	 * [RefLieuDto.llSituationFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @return the llSituationFr
	 */
	public String getLlSituationFr() {
		return llSituationFr;
	}

	/**
	 * [RefLieuDto.llSituationFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:39:10
	 * @param llSituationFr
	 *            the llSituationFr to set
	 */
	public void setLlSituationFr(String llSituationFr) {
		this.llSituationFr = llSituationFr;
	}

	/**
	 * [RefLieuDto.RefLieuDto()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 11:42:34
	 */
	public RefLieuDto() {

	}

	/**
	 * [RefLieuDto.identifiant] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:02:49
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * [RefLieuDto.identifiant] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 f�vr. 2014 12:02:49
	 * @param identifiant
	 *            the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * [RefLieuDto.dairaId] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @return the dairaId
	 */
	public Integer getDairaId() {
		return dairaId;
	}

	/**
	 * [RefLieuDto.dairaId] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @param dairaId
	 *            the dairaId to set
	 */
	public void setDairaId(Integer dairaId) {
		this.dairaId = dairaId;
	}

	/**
	 * [RefLieuDto.dairaLibelleLongFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @return the dairaLibelleLongFr
	 */
	public String getDairaLibelleLongFr() {
		return dairaLibelleLongFr;
	}

	/**
	 * [RefLieuDto.dairaLibelleLongFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @param dairaLibelleLongFr
	 *            the dairaLibelleLongFr to set
	 */
	public void setDairaLibelleLongFr(String dairaLibelleLongFr) {
		this.dairaLibelleLongFr = dairaLibelleLongFr;
	}

	/**
	 * [RefLieuDto.dairaLibelleLongAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @return the dairaLibelleLongAr
	 */
	public String getDairaLibelleLongAr() {
		return dairaLibelleLongAr;
	}

	/**
	 * [RefLieuDto.dairaLibelleLongAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @param dairaLibelleLongAr
	 *            the dairaLibelleLongAr to set
	 */
	public void setDairaLibelleLongAr(String dairaLibelleLongAr) {
		this.dairaLibelleLongAr = dairaLibelleLongAr;
	}

	/**
	 * [RefLieuDto.dairaLibelleCourtFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @return the dairaLibelleCourtFr
	 */
	public String getDairaLibelleCourtFr() {
		return dairaLibelleCourtFr;
	}

	/**
	 * [RefLieuDto.dairaLibelleCourtFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @param dairaLibelleCourtFr
	 *            the dairaLibelleCourtFr to set
	 */
	public void setDairaLibelleCourtFr(String dairaLibelleCourtFr) {
		this.dairaLibelleCourtFr = dairaLibelleCourtFr;
	}

	/**
	 * [RefLieuDto.dairaLibelleCourtAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @return the dairaLibelleCourtAr
	 */
	public String getDairaLibelleCourtAr() {
		return dairaLibelleCourtAr;
	}

	/**
	 * [RefLieuDto.dairaLibelleCourtAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @param dairaLibelleCourtAr
	 *            the dairaLibelleCourtAr to set
	 */
	public void setDairaLibelleCourtAr(String dairaLibelleCourtAr) {
		this.dairaLibelleCourtAr = dairaLibelleCourtAr;
	}

	/**
	 * [RefLieuDto.dairaCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @return the dairaCode
	 */
	public String getDairaCode() {
		return dairaCode;
	}

	/**
	 * [RefLieuDto.dairaCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @param dairaCode
	 *            the dairaCode to set
	 */
	public void setDairaCode(String dairaCode) {
		this.dairaCode = dairaCode;
	}

	/**
	 * [RefLieuDto.communeId] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @return the communeId
	 */
	public Integer getCommuneId() {
		return communeId;
	}

	/**
	 * [RefLieuDto.communeId] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @param communeId
	 *            the communeId to set
	 */
	public void setCommuneId(Integer communeId) {
		this.communeId = communeId;
	}

	/**
	 * [RefLieuDto.communeLibelleLongFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @return the communeLibelleLongFr
	 */
	public String getCommuneLibelleLongFr() {
		return communeLibelleLongFr;
	}

	/**
	 * [RefLieuDto.communeLibelleLongFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @param communeLibelleLongFr
	 *            the communeLibelleLongFr to set
	 */
	public void setCommuneLibelleLongFr(String communeLibelleLongFr) {
		this.communeLibelleLongFr = communeLibelleLongFr;
	}

	/**
	 * [RefLieuDto.communeLibelleLongAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @return the communeLibelleLongAr
	 */
	public String getCommuneLibelleLongAr() {
		return communeLibelleLongAr;
	}

	/**
	 * [RefLieuDto.communeLibelleLongAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @param communeLibelleLongAr
	 *            the communeLibelleLongAr to set
	 */
	public void setCommuneLibelleLongAr(String communeLibelleLongAr) {
		this.communeLibelleLongAr = communeLibelleLongAr;
	}

	/**
	 * [RefLieuDto.communeLibelleCourtFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @return the communeLibelleCourtFr
	 */
	public String getCommuneLibelleCourtFr() {
		return communeLibelleCourtFr;
	}

	/**
	 * [RefLieuDto.communeLibelleCourtFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @param communeLibelleCourtFr
	 *            the communeLibelleCourtFr to set
	 */
	public void setCommuneLibelleCourtFr(String communeLibelleCourtFr) {
		this.communeLibelleCourtFr = communeLibelleCourtFr;
	}

	/**
	 * [RefLieuDto.communeLibelleCourtAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:11
	 * @return the communeLibelleCourtAr
	 */
	public String getCommuneLibelleCourtAr() {
		return communeLibelleCourtAr;
	}

	/**
	 * [RefLieuDto.communeLibelleCourtAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:12
	 * @param communeLibelleCourtAr
	 *            the communeLibelleCourtAr to set
	 */
	public void setCommuneLibelleCourtAr(String communeLibelleCourtAr) {
		this.communeLibelleCourtAr = communeLibelleCourtAr;
	}

	/**
	 * [RefLieuDto.communeCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:12
	 * @return the communeCode
	 */
	public String getCommuneCode() {
		return communeCode;
	}

	/**
	 * [RefLieuDto.communeCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:12
	 * @param communeCode
	 *            the communeCode to set
	 */
	public void setCommuneCode(String communeCode) {
		this.communeCode = communeCode;
	}

	/**
	 * [RefLieuDto.wilayaId] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:12
	 * @return the wilayaId
	 */
	public Integer getWilayaId() {
		return wilayaId;
	}

	/**
	 * [RefLieuDto.wilayaId] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:12
	 * @param wilayaId
	 *            the wilayaId to set
	 */
	public void setWilayaId(Integer wilayaId) {
		this.wilayaId = wilayaId;
	}

	/**
	 * [RefLieuDto.wilayaLibelleLongFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:12
	 * @return the wilayaLibelleLongFr
	 */
	public String getWilayaLibelleLongFr() {
		return wilayaLibelleLongFr;
	}

	/**
	 * [RefLieuDto.wilayaLibelleLongFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:12
	 * @param wilayaLibelleLongFr
	 *            the wilayaLibelleLongFr to set
	 */
	public void setWilayaLibelleLongFr(String wilayaLibelleLongFr) {
		this.wilayaLibelleLongFr = wilayaLibelleLongFr;
	}

	/**
	 * [RefLieuDto.wilayaLibelleLongAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:12
	 * @return the wilayaLibelleLongAr
	 */
	public String getWilayaLibelleLongAr() {
		return wilayaLibelleLongAr;
	}

	/**
	 * [RefLieuDto.wilayaLibelleLongAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:12
	 * @param wilayaLibelleLongAr
	 *            the wilayaLibelleLongAr to set
	 */
	public void setWilayaLibelleLongAr(String wilayaLibelleLongAr) {
		this.wilayaLibelleLongAr = wilayaLibelleLongAr;
	}

	/**
	 * [RefLieuDto.wilayaLibelleCourtFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:12
	 * @return the wilayaLibelleCourtFr
	 */
	public String getWilayaLibelleCourtFr() {
		return wilayaLibelleCourtFr;
	}

	/**
	 * [RefLieuDto.wilayaLibelleCourtFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:12
	 * @param wilayaLibelleCourtFr
	 *            the wilayaLibelleCourtFr to set
	 */
	public void setWilayaLibelleCourtFr(String wilayaLibelleCourtFr) {
		this.wilayaLibelleCourtFr = wilayaLibelleCourtFr;
	}

	/**
	 * [RefLieuDto.wilayaLibelleCourtAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:12
	 * @return the wilayaLibelleCourtAr
	 */
	public String getWilayaLibelleCourtAr() {
		return wilayaLibelleCourtAr;
	}

	/**
	 * [RefLieuDto.wilayaLibelleCourtAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:12
	 * @param wilayaLibelleCourtAr
	 *            the wilayaLibelleCourtAr to set
	 */
	public void setWilayaLibelleCourtAr(String wilayaLibelleCourtAr) {
		this.wilayaLibelleCourtAr = wilayaLibelleCourtAr;
	}

	/**
	 * [RefLieuDto.wilayaCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:12
	 * @return the wilayaCode
	 */
	public String getWilayaCode() {
		return wilayaCode;
	}

	/**
	 * [RefLieuDto.wilayaCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 12:45:12
	 * @param wilayaCode
	 *            the wilayaCode to set
	 */
	public void setWilayaCode(String wilayaCode) {
		this.wilayaCode = wilayaCode;
	}

	/**
	 * [RefLieuDto.adresse] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 15:26:38
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * [RefLieuDto.adresse] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 15:26:38
	 * @param adresse
	 *            the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * [RefLieuDto.adresseAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 15:26:38
	 * @return the adresseAr
	 */
	public String getAdresseAr() {
		return adresseAr;
	}

	/**
	 * [RefLieuDto.adresseAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 15:26:38
	 * @param adresseAr
	 *            the adresseAr to set
	 */
	public void setAdresseAr(String adresseAr) {
		this.adresseAr = adresseAr;
	}

	/**
	 * [RefLieuDto.telephone] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 15:26:38
	 * @return the telephone
	 */
	public Integer getTelephone() {
		return telephone;
	}

	/**
	 * [RefLieuDto.telephone] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014 15:26:38
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}

	/**
	 * [RefLieuDto.idEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:32:44
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [RefLieuDto.idEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:32:44
	 * @param idEtablissement the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	

}
