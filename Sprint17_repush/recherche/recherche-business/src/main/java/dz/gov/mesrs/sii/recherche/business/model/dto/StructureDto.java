/**
 * [dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto.java] 
 * @author rlaib on : 16 déc. 2014  13:24:10
 */
package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * @author rlaib  on : 16 déc. 2014  13:24:10
 */
public class StructureDto implements Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:54:28
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	// RefStructure
	private Integer  refStructureId;
	private String  	refStructureLibelleLatin;
	private String  	refStructureLibelleArabe;
	private String 	refStructureIdentifiant;
	private Integer refStructureEtablissementId;
	private String 	refStructureEtablissementIdentifiant;
	private String 	refStructureEtablissementLibelleArabe;
	private String 	refStructureEtablissementLibelleLatin;
	private String		refStructureEtablissementAncienCode;
	private String 	refStructureTypeId;
	private String 	refStructureTypeCode;
	private String 	refStructureTypeLibelle;
	private String 	refStructureNif;
	private String 	refStructureNis;
	private String 	refStructureNrc;
	private Date		refStructureDateCreation;
	private Integer	refStructureStatutId;
	private String		refStructureStatutLibelle;
	private Integer	refStructureFormeJuridiqueId;
	private String		refStructureFormeJuridiqueLibelle;
	
	
	/**
	 * [StructureDto.StructureDto()] Constructor
	 * @author rlaib  on : 16 déc. 2014  13:40:48	
	 */
	public StructureDto() {
	}
	
	/**
	 * [StructureDto.StructureDto()] Constructor
	 * @author rlaib  on : 16 déc. 2014  13:33:04
	 * @param refStructureId
	 * @param refStructureLibelleLatin
	 * @param refStructureLibelleArabe
	 * @param refStructureIdentifiant
	 * @param refStructureEtablissementId
	 * @param refStructureEtablissementIdentifiant
	 * @param refStructureEtablissementLibelleArabe
	 * @param refStructureEtablissementLibelleLatin
	 * @param refStructureEtablissementAncienCode	
	 */
	public StructureDto(Integer refStructureId,
			String refStructureLibelleLatin, String refStructureLibelleArabe,
			String refStructureIdentifiant,
			Integer refStructureEtablissementId,
			String refStructureEtablissementIdentifiant,
			String refStructureEtablissementLibelleArabe,
			String refStructureEtablissementLibelleLatin,
			String refStructureEtablissementAncienCode) {
		super();
		this.refStructureId = refStructureId;
		this.refStructureLibelleLatin = refStructureLibelleLatin;
		this.refStructureLibelleArabe = refStructureLibelleArabe;
		this.refStructureIdentifiant = refStructureIdentifiant;
		this.refStructureEtablissementId = refStructureEtablissementId;
		this.refStructureEtablissementIdentifiant = refStructureEtablissementIdentifiant;
		this.refStructureEtablissementLibelleArabe = refStructureEtablissementLibelleArabe;
		this.refStructureEtablissementLibelleLatin = refStructureEtablissementLibelleLatin;
		this.refStructureEtablissementAncienCode = refStructureEtablissementAncienCode;
	}
	/**
	 * [StructureDto.id] Getter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * [StructureDto.id] Setter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * [StructureDto.refStructureId] Getter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @return the refStructureId
	 */
	public Integer getRefStructureId() {
		return refStructureId;
	}
	/**
	 * [StructureDto.refStructureId] Setter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @param refStructureId the refStructureId to set
	 */
	public void setRefStructureId(Integer refStructureId) {
		this.refStructureId = refStructureId;
	}
	/**
	 * [StructureDto.refStructureLibelleLatin] Getter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @return the refStructureLibelleLatin
	 */
	public String getRefStructureLibelleLatin() {
		return refStructureLibelleLatin;
	}
	/**
	 * [StructureDto.refStructureLibelleLatin] Setter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @param refStructureLibelleLatin the refStructureLibelleLatin to set
	 */
	public void setRefStructureLibelleLatin(String refStructureLibelleLatin) {
		this.refStructureLibelleLatin = refStructureLibelleLatin;
	}
	/**
	 * [StructureDto.refStructureLibelleArabe] Getter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @return the refStructureLibelleArabe
	 */
	public String getRefStructureLibelleArabe() {
		return refStructureLibelleArabe;
	}
	/**
	 * [StructureDto.refStructureLibelleArabe] Setter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @param refStructureLibelleArabe the refStructureLibelleArabe to set
	 */
	public void setRefStructureLibelleArabe(String refStructureLibelleArabe) {
		this.refStructureLibelleArabe = refStructureLibelleArabe;
	}
	/**
	 * [StructureDto.refStructureIdentifiant] Getter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @return the refStructureIdentifiant
	 */
	public String getRefStructureIdentifiant() {
		return refStructureIdentifiant;
	}
	/**
	 * [StructureDto.refStructureIdentifiant] Setter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @param refStructureIdentifiant the refStructureIdentifiant to set
	 */
	public void setRefStructureIdentifiant(String refStructureIdentifiant) {
		this.refStructureIdentifiant = refStructureIdentifiant;
	}
	/**
	 * [StructureDto.refStructureEtablissementId] Getter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @return the refStructureEtablissementId
	 */
	public Integer getRefStructureEtablissementId() {
		return refStructureEtablissementId;
	}
	/**
	 * [StructureDto.refStructureEtablissementId] Setter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @param refStructureEtablissementId the refStructureEtablissementId to set
	 */
	public void setRefStructureEtablissementId(Integer refStructureEtablissementId) {
		this.refStructureEtablissementId = refStructureEtablissementId;
	}
	/**
	 * [StructureDto.refStructureEtablissementIdentifiant] Getter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @return the refStructureEtablissementIdentifiant
	 */
	public String getRefStructureEtablissementIdentifiant() {
		return refStructureEtablissementIdentifiant;
	}
	/**
	 * [StructureDto.refStructureEtablissementIdentifiant] Setter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @param refStructureEtablissementIdentifiant the refStructureEtablissementIdentifiant to set
	 */
	public void setRefStructureEtablissementIdentifiant(
			String refStructureEtablissementIdentifiant) {
		this.refStructureEtablissementIdentifiant = refStructureEtablissementIdentifiant;
	}
	/**
	 * [StructureDto.refStructureEtablissementLibelleArabe] Getter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @return the refStructureEtablissementLibelleArabe
	 */
	public String getRefStructureEtablissementLibelleArabe() {
		return refStructureEtablissementLibelleArabe;
	}
	/**
	 * [StructureDto.refStructureEtablissementLibelleArabe] Setter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @param refStructureEtablissementLibelleArabe the refStructureEtablissementLibelleArabe to set
	 */
	public void setRefStructureEtablissementLibelleArabe(
			String refStructureEtablissementLibelleArabe) {
		this.refStructureEtablissementLibelleArabe = refStructureEtablissementLibelleArabe;
	}
	/**
	 * [StructureDto.refStructureEtablissementLibelleLatin] Getter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @return the refStructureEtablissementLibelleLatin
	 */
	public String getRefStructureEtablissementLibelleLatin() {
		return refStructureEtablissementLibelleLatin;
	}
	/**
	 * [StructureDto.refStructureEtablissementLibelleLatin] Setter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @param refStructureEtablissementLibelleLatin the refStructureEtablissementLibelleLatin to set
	 */
	public void setRefStructureEtablissementLibelleLatin(
			String refStructureEtablissementLibelleLatin) {
		this.refStructureEtablissementLibelleLatin = refStructureEtablissementLibelleLatin;
	}
	/**
	 * [StructureDto.refStructureEtablissementAncienCode] Getter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @return the refStructureEtablissementAncienCode
	 */
	public String getRefStructureEtablissementAncienCode() {
		return refStructureEtablissementAncienCode;
	}
	/**
	 * [StructureDto.refStructureEtablissementAncienCode] Setter 
	 * @author rlaib on : 16 déc. 2014  13:32:42
	 * @param refStructureEtablissementAncienCode the refStructureEtablissementAncienCode to set
	 */
	public void setRefStructureEtablissementAncienCode(
			String refStructureEtablissementAncienCode) {
		this.refStructureEtablissementAncienCode = refStructureEtablissementAncienCode;
	}

	/**
	 * [StructureDto.refStructureTypeId] Getter 
	 * @author rlaib on : 17 déc. 2014  12:35:06
	 * @return the refStructureTypeId
	 */
	public String getRefStructureTypeId() {
		return refStructureTypeId;
	}

	/**
	 * [StructureDto.refStructureTypeId] Setter 
	 * @author rlaib on : 17 déc. 2014  12:35:06
	 * @param refStructureTypeId the refStructureTypeId to set
	 */
	public void setRefStructureTypeId(String refStructureTypeId) {
		this.refStructureTypeId = refStructureTypeId;
	}

	/**
	 * [StructureDto.refStructureTypeCode] Getter 
	 * @author rlaib on : 17 déc. 2014  12:35:06
	 * @return the refStructureTypeCode
	 */
	public String getRefStructureTypeCode() {
		return refStructureTypeCode;
	}

	/**
	 * [StructureDto.refStructureTypeCode] Setter 
	 * @author rlaib on : 17 déc. 2014  12:35:06
	 * @param refStructureTypeCode the refStructureTypeCode to set
	 */
	public void setRefStructureTypeCode(String refStructureTypeCode) {
		this.refStructureTypeCode = refStructureTypeCode;
	}

	/**
	 * [StructureDto.refStructureTypeLibelle] Getter 
	 * @author rlaib on : 17 déc. 2014  12:35:06
	 * @return the refStructureTypeLibelle
	 */
	public String getRefStructureTypeLibelle() {
		return refStructureTypeLibelle;
	}

	/**
	 * [StructureDto.refStructureTypeLibelle] Setter 
	 * @author rlaib on : 17 déc. 2014  12:35:06
	 * @param refStructureTypeLibelle the refStructureTypeLibelle to set
	 */
	public void setRefStructureTypeLibelle(String refStructureTypeLibelle) {
		this.refStructureTypeLibelle = refStructureTypeLibelle;
	}

	/**
	 * [StructureDto.refStructureNif] Getter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @return the refStructureNif
	 */
	public String getRefStructureNif() {
		return refStructureNif;
	}

	/**
	 * [StructureDto.refStructureNif] Setter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @param refStructureNif the refStructureNif to set
	 */
	public void setRefStructureNif(String refStructureNif) {
		this.refStructureNif = refStructureNif;
	}

	/**
	 * [StructureDto.refStructureNis] Getter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @return the refStructureNis
	 */
	public String getRefStructureNis() {
		return refStructureNis;
	}

	/**
	 * [StructureDto.refStructureNis] Setter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @param refStructureNis the refStructureNis to set
	 */
	public void setRefStructureNis(String refStructureNis) {
		this.refStructureNis = refStructureNis;
	}

	/**
	 * [StructureDto.refStructureNrc] Getter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @return the refStructureNrc
	 */
	public String getRefStructureNrc() {
		return refStructureNrc;
	}

	/**
	 * [StructureDto.refStructureNrc] Setter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @param refStructureNrc the refStructureNrc to set
	 */
	public void setRefStructureNrc(String refStructureNrc) {
		this.refStructureNrc = refStructureNrc;
	}

	/**
	 * [StructureDto.refStructureDateCreation] Getter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @return the refStructureDateCreation
	 */
	public Date getRefStructureDateCreation() {
		return refStructureDateCreation;
	}

	/**
	 * [StructureDto.refStructureDateCreation] Setter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @param refStructureDateCreation the refStructureDateCreation to set
	 */
	public void setRefStructureDateCreation(Date refStructureDateCreation) {
		this.refStructureDateCreation = refStructureDateCreation;
	}

	/**
	 * [StructureDto.refStructureStatutId] Getter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @return the refStructureStatutId
	 */
	public Integer getRefStructureStatutId() {
		return refStructureStatutId;
	}

	/**
	 * [StructureDto.refStructureStatutId] Setter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @param refStructureStatutId the refStructureStatutId to set
	 */
	public void setRefStructureStatutId(Integer refStructureStatutId) {
		this.refStructureStatutId = refStructureStatutId;
	}

	/**
	 * [StructureDto.refStructureStatutLibelle] Getter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @return the refStructureStatutLibelle
	 */
	public String getRefStructureStatutLibelle() {
		return refStructureStatutLibelle;
	}

	/**
	 * [StructureDto.refStructureStatutLibelle] Setter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @param refStructureStatutLibelle the refStructureStatutLibelle to set
	 */
	public void setRefStructureStatutLibelle(String refStructureStatutLibelle) {
		this.refStructureStatutLibelle = refStructureStatutLibelle;
	}

	/**
	 * [StructureDto.refStructureFormeJuridiqueId] Getter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @return the refStructureFormeJuridiqueId
	 */
	public Integer getRefStructureFormeJuridiqueId() {
		return refStructureFormeJuridiqueId;
	}

	/**
	 * [StructureDto.refStructureFormeJuridiqueId] Setter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @param refStructureFormeJuridiqueId the refStructureFormeJuridiqueId to set
	 */
	public void setRefStructureFormeJuridiqueId(Integer refStructureFormeJuridiqueId) {
		this.refStructureFormeJuridiqueId = refStructureFormeJuridiqueId;
	}

	/**
	 * [StructureDto.refStructureFormeJuridiqueLibelle] Getter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @return the refStructureFormeJuridiqueLibelle
	 */
	public String getRefStructureFormeJuridiqueLibelle() {
		return refStructureFormeJuridiqueLibelle;
	}

	/**
	 * [StructureDto.refStructureFormeJuridiqueLibelle] Setter 
	 * @author rlaib on : 18 déc. 2014  12:57:22
	 * @param refStructureFormeJuridiqueLibelle the refStructureFormeJuridiqueLibelle to set
	 */
	public void setRefStructureFormeJuridiqueLibelle(
			String refStructureFormeJuridiqueLibelle) {
		this.refStructureFormeJuridiqueLibelle = refStructureFormeJuridiqueLibelle;
	}

	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto.toString] Method 
	 * Overridden By : @author rlaib  on : 17 déc. 2014  15:35:24
	 * @return
	 */
	@Override
	public String toString() {
		return "StructureDto [id=" + id + ", refStructureId=" + refStructureId
				+ ", refStructureLibelleLatin=" + refStructureLibelleLatin
				+ ", refStructureTypeLibelle=" + refStructureTypeLibelle + "]";
	}
	
	
	
}
