/**
 * [dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto.java] 
 * @author rlaib on : 16 déc. 2014  13:24:10
 */
package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;


public class DemandeCreditDto implements Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:55:29
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private short annee;	
	private Double montant;
	private String description;

	// Chapitre
	private Integer ncChapitreId;
	private String ncChapitreCode;
	private String ncChapitreLibelle;
	
	// Article
	private Integer ncArticleId;
	private String ncArticleCode;
	private String ncArticleLibelle;
		
	// Demande Budget
	private Long demandeBudgetId;
	private String demandeBudgetLibelle;
	private Long demandeBudgetProgrammeId;
	private String demandeBudgetProgrammeLibelle;
	
	public DemandeCreditDto() {
	}

	/**
	 * [DemandeCreditDto.id] Getter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * [DemandeCreditDto.id] Setter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [DemandeCreditDto.annee] Getter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @return the annee
	 */
	public short getAnnee() {
		return annee;
	}

	/**
	 * [DemandeCreditDto.annee] Setter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @param annee the annee to set
	 */
	public void setAnnee(short annee) {
		this.annee = annee;
	}

	/**
	 * [DemandeCreditDto.montant] Getter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @return the montant
	 */
	public Double getMontant() {
		return montant;
	}

	/**
	 * [DemandeCreditDto.montant] Setter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @param montant the montant to set
	 */
	public void setMontant(Double montant) {
		this.montant = montant;
	}

	/**
	 * [DemandeCreditDto.description] Getter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * [DemandeCreditDto.description] Setter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [DemandeCreditDto.ncChapitreId] Getter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @return the ncChapitreId
	 */
	public Integer getNcChapitreId() {
		return ncChapitreId;
	}

	/**
	 * [DemandeCreditDto.ncChapitreId] Setter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @param ncChapitreId the ncChapitreId to set
	 */
	public void setNcChapitreId(Integer ncChapitreId) {
		this.ncChapitreId = ncChapitreId;
	}

	/**
	 * [DemandeCreditDto.ncChapitreCode] Getter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @return the ncChapitreCode
	 */
	public String getNcChapitreCode() {
		return ncChapitreCode;
	}

	/**
	 * [DemandeCreditDto.ncChapitreCode] Setter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @param ncChapitreCode the ncChapitreCode to set
	 */
	public void setNcChapitreCode(String ncChapitreCode) {
		this.ncChapitreCode = ncChapitreCode;
	}

	/**
	 * [DemandeCreditDto.ncChapitreLibelle] Getter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @return the ncChapitreLibelle
	 */
	public String getNcChapitreLibelle() {
		return ncChapitreLibelle;
	}

	/**
	 * [DemandeCreditDto.ncChapitreLibelle] Setter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @param ncChapitreLibelle the ncChapitreLibelle to set
	 */
	public void setNcChapitreLibelle(String ncChapitreLibelle) {
		this.ncChapitreLibelle = ncChapitreLibelle;
	}

	/**
	 * [DemandeCreditDto.ncArticleId] Getter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @return the ncArticleId
	 */
	public Integer getNcArticleId() {
		return ncArticleId;
	}

	/**
	 * [DemandeCreditDto.ncArticleId] Setter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @param ncArticleId the ncArticleId to set
	 */
	public void setNcArticleId(Integer ncArticleId) {
		this.ncArticleId = ncArticleId;
	}

	/**
	 * [DemandeCreditDto.ncArticleCode] Getter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @return the ncArticleCode
	 */
	public String getNcArticleCode() {
		return ncArticleCode;
	}

	/**
	 * [DemandeCreditDto.ncArticleCode] Setter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @param ncArticleCode the ncArticleCode to set
	 */
	public void setNcArticleCode(String ncArticleCode) {
		this.ncArticleCode = ncArticleCode;
	}

	/**
	 * [DemandeCreditDto.ncArticleLibelle] Getter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @return the ncArticleLibelle
	 */
	public String getNcArticleLibelle() {
		return ncArticleLibelle;
	}

	/**
	 * [DemandeCreditDto.ncArticleLibelle] Setter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @param ncArticleLibelle the ncArticleLibelle to set
	 */
	public void setNcArticleLibelle(String ncArticleLibelle) {
		this.ncArticleLibelle = ncArticleLibelle;
	}

	/**
	 * [DemandeCreditDto.demandeBudgetId] Getter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @return the demandeBudgetId
	 */
	public Long getDemandeBudgetId() {
		return demandeBudgetId;
	}

	/**
	 * [DemandeCreditDto.demandeBudgetId] Setter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @param demandeBudgetId the demandeBudgetId to set
	 */
	public void setDemandeBudgetId(Long demandeBudgetId) {
		this.demandeBudgetId = demandeBudgetId;
	}

	/**
	 * [DemandeCreditDto.demandeBudgetLibelle] Getter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @return the demandeBudgetLibelle
	 */
	public String getDemandeBudgetLibelle() {
		return demandeBudgetLibelle;
	}

	/**
	 * [DemandeCreditDto.demandeBudgetLibelle] Setter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @param demandeBudgetLibelle the demandeBudgetLibelle to set
	 */
	public void setDemandeBudgetLibelle(String demandeBudgetLibelle) {
		this.demandeBudgetLibelle = demandeBudgetLibelle;
	}

	/**
	 * [DemandeCreditDto.demandeBudgetProgrammeId] Getter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @return the demandeBudgetProgrammeId
	 */
	public Long getDemandeBudgetProgrammeId() {
		return demandeBudgetProgrammeId;
	}

	/**
	 * [DemandeCreditDto.demandeBudgetProgrammeId] Setter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @param demandeBudgetProgrammeId the demandeBudgetProgrammeId to set
	 */
	public void setDemandeBudgetProgrammeId(Long demandeBudgetProgrammeId) {
		this.demandeBudgetProgrammeId = demandeBudgetProgrammeId;
	}

	/**
	 * [DemandeCreditDto.demandeBudgetProgrammeLibelle] Getter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @return the demandeBudgetProgrammeLibelle
	 */
	public String getDemandeBudgetProgrammeLibelle() {
		return demandeBudgetProgrammeLibelle;
	}

	/**
	 * [DemandeCreditDto.demandeBudgetProgrammeLibelle] Setter 
	 * @author rlaib on : 4 févr. 2015  10:55:53
	 * @param demandeBudgetProgrammeLibelle the demandeBudgetProgrammeLibelle to set
	 */
	public void setDemandeBudgetProgrammeLibelle(
			String demandeBudgetProgrammeLibelle) {
		this.demandeBudgetProgrammeLibelle = demandeBudgetProgrammeLibelle;
	}

	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.DemandeCreditDto.hashCode] Method 
	 * Overridden By : @author rlaib  on : 4 févr. 2015  10:56:18
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((montant == null) ? 0 : montant.hashCode());
		result = prime * result
				+ ((ncArticleId == null) ? 0 : ncArticleId.hashCode());
		result = prime * result
				+ ((ncChapitreId == null) ? 0 : ncChapitreId.hashCode());
		return result;
	}

	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.DemandeCreditDto.equals] Method 
	 * Overridden By : @author rlaib  on : 4 févr. 2015  10:56:18
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
		DemandeCreditDto other = (DemandeCreditDto) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (montant == null) {
			if (other.montant != null)
				return false;
		} else if (!montant.equals(other.montant))
			return false;
		if (ncArticleId == null) {
			if (other.ncArticleId != null)
				return false;
		} else if (!ncArticleId.equals(other.ncArticleId))
			return false;
		if (ncChapitreId == null) {
			if (other.ncChapitreId != null)
				return false;
		} else if (!ncChapitreId.equals(other.ncChapitreId))
			return false;
		return true;
	}

	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.DemandeCreditDto.toString] Method 
	 * Overridden By : @author rlaib  on : 4 févr. 2015  10:57:02
	 * @return
	 */
	@Override
	public String toString() {
		return "DemandeCreditDto [annee=" + annee + ", montant=" + montant
				+ ", description=" + description + ", demandeBudgetLibelle="
				+ demandeBudgetLibelle + ", demandeBudgetProgrammeLibelle="
				+ demandeBudgetProgrammeLibelle + "]";
	}

	
}
