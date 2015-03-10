/**
 * [dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto.java] 
 * @author rlaib on : 16 déc. 2014  13:24:10
 */
package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;


/**
 * @author rlaib  on : 16 déc. 2014  13:24:10
 */
public class GroupeDto implements Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:55:09
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	// RefGroupe
	private Integer refGroupeId;
	private String refGroupeIdentifiant;
	private String refGroupeLibelleFr;
	private String refGroupeLibelleAr;
	
	// Structure de recherche
	private Long  structureId;
	private String  	structureLibelleLatin;
	
	public GroupeDto() {
	}
	
	/**
	 * [GroupeDto.GroupeDto()] Constructor
	 * @author rlaib  on : 16 déc. 2014  13:50:29
	 * @param refGroupeId
	 * @param refGroupeIdentifiant
	 * @param regGroupeLibelle
	 * @param structureId
	 * @param structureLibelleLatin	
	 */
	public GroupeDto(Integer refGroupeId, String refGroupeIdentifiant,
			String regGroupeLibelle, Long structureId,
			String structureLibelleLatin) {
		super();
		this.refGroupeId = refGroupeId;
		this.refGroupeIdentifiant = refGroupeIdentifiant;
		this.structureId = structureId;
		this.structureLibelleLatin = structureLibelleLatin;
	}

	/**
	 * [GroupeDto.id] Getter 
	 * @author rlaib on : 16 déc. 2014  13:49:55
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * [GroupeDto.id] Setter 
	 * @author rlaib on : 16 déc. 2014  13:49:55
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * [GroupeDto.refGroupeId] Getter 
	 * @author rlaib on : 16 déc. 2014  13:49:55
	 * @return the refGroupeId
	 */
	public Integer getRefGroupeId() {
		return refGroupeId;
	}
	/**
	 * [GroupeDto.refGroupeId] Setter 
	 * @author rlaib on : 16 déc. 2014  13:49:55
	 * @param refGroupeId the refGroupeId to set
	 */
	public void setRefGroupeId(Integer refGroupeId) {
		this.refGroupeId = refGroupeId;
	}
	/**
	 * [GroupeDto.refGroupeIdentifiant] Getter 
	 * @author rlaib on : 16 déc. 2014  13:49:55
	 * @return the refGroupeIdentifiant
	 */
	public String getRefGroupeIdentifiant() {
		return refGroupeIdentifiant;
	}
	/**
	 * [GroupeDto.refGroupeIdentifiant] Setter 
	 * @author rlaib on : 16 déc. 2014  13:49:55
	 * @param refGroupeIdentifiant the refGroupeIdentifiant to set
	 */
	public void setRefGroupeIdentifiant(String refGroupeIdentifiant) {
		this.refGroupeIdentifiant = refGroupeIdentifiant;
	}
	
	/**
	 * [GroupeDto.refGroupeLibelleFr] Getter 
	 * @author rlaib on : 18 déc. 2014  13:38:27
	 * @return the refGroupeLibelleFr
	 */
	public String getRefGroupeLibelleFr() {
		return refGroupeLibelleFr;
	}

	/**
	 * [GroupeDto.refGroupeLibelleFr] Setter 
	 * @author rlaib on : 18 déc. 2014  13:38:27
	 * @param refGroupeLibelleFr the refGroupeLibelleFr to set
	 */
	public void setRefGroupeLibelleFr(String refGroupeLibelleFr) {
		this.refGroupeLibelleFr = refGroupeLibelleFr;
	}

	/**
	 * [GroupeDto.refGroupeLibelleAr] Getter 
	 * @author rlaib on : 18 déc. 2014  13:38:27
	 * @return the refGroupeLibelleAr
	 */
	public String getRefGroupeLibelleAr() {
		return refGroupeLibelleAr;
	}

	/**
	 * [GroupeDto.refGroupeLibelleAr] Setter 
	 * @author rlaib on : 18 déc. 2014  13:38:27
	 * @param refGroupeLibelleAr the refGroupeLibelleAr to set
	 */
	public void setRefGroupeLibelleAr(String refGroupeLibelleAr) {
		this.refGroupeLibelleAr = refGroupeLibelleAr;
	}

	/**
	 * [GroupeDto.structureId] Getter 
	 * @author rlaib on : 16 déc. 2014  13:49:55
	 * @return the structureId
	 */
	public Long getStructureId() {
		return structureId;
	}
	/**
	 * [GroupeDto.structureId] Setter 
	 * @author rlaib on : 16 déc. 2014  13:49:55
	 * @param structureId the structureId to set
	 */
	public void setStructureId(Long structureId) {
		this.structureId = structureId;
	}
	/**
	 * [GroupeDto.structureLibelleLatin] Getter 
	 * @author rlaib on : 16 déc. 2014  13:49:55
	 * @return the structureLibelleLatin
	 */
	public String getStructureLibelleLatin() {
		return structureLibelleLatin;
	}
	/**
	 * [GroupeDto.structureLibelleLatin] Setter 
	 * @author rlaib on : 16 déc. 2014  13:49:55
	 * @param structureLibelleLatin the structureLibelleLatin to set
	 */
	public void setStructureLibelleLatin(String structureLibelleLatin) {
		this.structureLibelleLatin = structureLibelleLatin;
	}
	
	
}
