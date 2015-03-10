/**
 * [dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto.java] 
 * @author rlaib on : 16 déc. 2014  13:24:10
 */
package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;


public class ActiviteBudgetDto implements Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:55:29
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private short annee;	
	private String description;
	
	// Ativite
	private Long ncActiviteId;
	private String ncActiviteCode;
	private String ncActiviteLibelle;
		
	// Demande Budget
	private Long demandeBudgetId;
	private String demandeBudgetLibelle;
	private Long demandeBudgetProgrammeId;
	private String demandeBudgetProgrammeLibelle;
	
	// Groupe
	private Long groupeId;
	private String groupeLibelle;
	
	public ActiviteBudgetDto() {
	}

	/**
	 * [ActiviteBudgetDto.id] Getter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * [ActiviteBudgetDto.id] Setter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [ActiviteBudgetDto.annee] Getter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @return the annee
	 */
	public short getAnnee() {
		return annee;
	}

	/**
	 * [ActiviteBudgetDto.annee] Setter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @param annee the annee to set
	 */
	public void setAnnee(short annee) {
		this.annee = annee;
	}

	/**
	 * [ActiviteBudgetDto.description] Getter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * [ActiviteBudgetDto.description] Setter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [ActiviteBudgetDto.ncActiviteId] Getter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @return the ncActiviteId
	 */
	public Long getNcActiviteId() {
		return ncActiviteId;
	}

	/**
	 * [ActiviteBudgetDto.ncActiviteId] Setter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @param ncActiviteId the ncActiviteId to set
	 */
	public void setNcActiviteId(Long ncActiviteId) {
		this.ncActiviteId = ncActiviteId;
	}

	/**
	 * [ActiviteBudgetDto.ncActiviteCode] Getter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @return the ncActiviteCode
	 */
	public String getNcActiviteCode() {
		return ncActiviteCode;
	}

	/**
	 * [ActiviteBudgetDto.ncActiviteCode] Setter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @param ncActiviteCode the ncActiviteCode to set
	 */
	public void setNcActiviteCode(String ncActiviteCode) {
		this.ncActiviteCode = ncActiviteCode;
	}

	/**
	 * [ActiviteBudgetDto.ncActiviteLibelle] Getter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @return the ncActiviteLibelle
	 */
	public String getNcActiviteLibelle() {
		return ncActiviteLibelle;
	}

	/**
	 * [ActiviteBudgetDto.ncActiviteLibelle] Setter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @param ncActiviteLibelle the ncActiviteLibelle to set
	 */
	public void setNcActiviteLibelle(String ncActiviteLibelle) {
		this.ncActiviteLibelle = ncActiviteLibelle;
	}

	/**
	 * [ActiviteBudgetDto.demandeBudgetId] Getter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @return the demandeBudgetId
	 */
	public Long getDemandeBudgetId() {
		return demandeBudgetId;
	}

	/**
	 * [ActiviteBudgetDto.demandeBudgetId] Setter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @param demandeBudgetId the demandeBudgetId to set
	 */
	public void setDemandeBudgetId(Long demandeBudgetId) {
		this.demandeBudgetId = demandeBudgetId;
	}

	/**
	 * [ActiviteBudgetDto.demandeBudgetLibelle] Getter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @return the demandeBudgetLibelle
	 */
	public String getDemandeBudgetLibelle() {
		return demandeBudgetLibelle;
	}

	/**
	 * [ActiviteBudgetDto.demandeBudgetLibelle] Setter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @param demandeBudgetLibelle the demandeBudgetLibelle to set
	 */
	public void setDemandeBudgetLibelle(String demandeBudgetLibelle) {
		this.demandeBudgetLibelle = demandeBudgetLibelle;
	}

	/**
	 * [ActiviteBudgetDto.demandeBudgetProgrammeId] Getter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @return the demandeBudgetProgrammeId
	 */
	public Long getDemandeBudgetProgrammeId() {
		return demandeBudgetProgrammeId;
	}

	/**
	 * [ActiviteBudgetDto.demandeBudgetProgrammeId] Setter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @param demandeBudgetProgrammeId the demandeBudgetProgrammeId to set
	 */
	public void setDemandeBudgetProgrammeId(Long demandeBudgetProgrammeId) {
		this.demandeBudgetProgrammeId = demandeBudgetProgrammeId;
	}

	/**
	 * [ActiviteBudgetDto.demandeBudgetProgrammeLibelle] Getter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @return the demandeBudgetProgrammeLibelle
	 */
	public String getDemandeBudgetProgrammeLibelle() {
		return demandeBudgetProgrammeLibelle;
	}

	/**
	 * [ActiviteBudgetDto.demandeBudgetProgrammeLibelle] Setter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @param demandeBudgetProgrammeLibelle the demandeBudgetProgrammeLibelle to set
	 */
	public void setDemandeBudgetProgrammeLibelle(
			String demandeBudgetProgrammeLibelle) {
		this.demandeBudgetProgrammeLibelle = demandeBudgetProgrammeLibelle;
	}

	/**
	 * [ActiviteBudgetDto.groupeId] Getter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @return the groupeId
	 */
	public Long getGroupeId() {
		return groupeId;
	}

	/**
	 * [ActiviteBudgetDto.groupeId] Setter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @param groupeId the groupeId to set
	 */
	public void setGroupeId(Long groupeId) {
		this.groupeId = groupeId;
	}

	/**
	 * [ActiviteBudgetDto.groupeLibelle] Getter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @return the groupeLibelle
	 */
	public String getGroupeLibelle() {
		return groupeLibelle;
	}

	/**
	 * [ActiviteBudgetDto.groupeLibelle] Setter 
	 * @author rlaib on : 1 févr. 2015  15:32:25
	 * @param groupeLibelle the groupeLibelle to set
	 */
	public void setGroupeLibelle(String groupeLibelle) {
		this.groupeLibelle = groupeLibelle;
	}

	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.ActiviteBudgetDto.hashCode] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  15:32:50
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + annee;
		result = prime * result
				+ ((demandeBudgetId == null) ? 0 : demandeBudgetId.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((groupeId == null) ? 0 : groupeId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((ncActiviteId == null) ? 0 : ncActiviteId.hashCode());
		return result;
	}

	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.ActiviteBudgetDto.equals] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  15:32:50
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActiviteBudgetDto other = (ActiviteBudgetDto) obj;
		if (annee != other.annee)
			return false;
		if (demandeBudgetId == null) {
			if (other.demandeBudgetId != null)
				return false;
		} else if (!demandeBudgetId.equals(other.demandeBudgetId))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (groupeId == null) {
			if (other.groupeId != null)
				return false;
		} else if (!groupeId.equals(other.groupeId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ncActiviteId == null) {
			if (other.ncActiviteId != null)
				return false;
		} else if (!ncActiviteId.equals(other.ncActiviteId))
			return false;
		return true;
	}

	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.ActiviteBudgetDto.toString] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  15:33:19
	 * @return
	 */
	@Override
	public String toString() {
		return "ActiviteBudgetDto [id=" + id + ", annee=" + annee
				+ ", ncActiviteLibelle=" + ncActiviteLibelle
				+ ", demandeBudgetProgrammeLibelle="
				+ demandeBudgetProgrammeLibelle + ", groupeLibelle="
				+ groupeLibelle + "]";
	}
	
	
}
