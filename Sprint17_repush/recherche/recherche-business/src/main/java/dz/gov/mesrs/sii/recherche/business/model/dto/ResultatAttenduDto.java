/**
 * [dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto.java] 
 * @author rlaib on : 16 déc. 2014  13:24:10
 */
package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;


public class ResultatAttenduDto implements Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:55:29
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private short annee;	
	private String description;
	private Integer quantite;
	
	// Resultat
	private Integer ncResultatId;
	private String ncResultatCode;
	private String ncResultatLibelle;
	
	// Demande de budget
	private Long demandeBudgetId;
	private String demandeBudgetLibelle;
	private Long demandeBudgetProgrammeId;
	private String demandeBudgetProgrammeLibelle;
	
	public ResultatAttenduDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public short getAnnee() {
		return annee;
	}

	public void setAnnee(short annee) {
		this.annee = annee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Integer getNcResultatId() {
		return ncResultatId;
	}

	public void setNcResultatId(Integer ncResultatId) {
		this.ncResultatId = ncResultatId;
	}

	public String getNcResultatCode() {
		return ncResultatCode;
	}

	public void setNcResultatCode(String ncResultatCode) {
		this.ncResultatCode = ncResultatCode;
	}

	public String getNcResultatLibelle() {
		return ncResultatLibelle;
	}

	public void setNcResultatLibelle(String ncResultatLibelle) {
		this.ncResultatLibelle = ncResultatLibelle;
	}

	public Long getDemandeBudgetId() {
		return demandeBudgetId;
	}

	public void setDemandeBudgetId(Long demandeBudgetId) {
		this.demandeBudgetId = demandeBudgetId;
	}

	public String getDemandeBudgetLibelle() {
		return demandeBudgetLibelle;
	}

	public void setDemandeBudgetLibelle(String demandeBudgetLibelle) {
		this.demandeBudgetLibelle = demandeBudgetLibelle;
	}

	public Long getDemandeBudgetProgrammeId() {
		return demandeBudgetProgrammeId;
	}

	public void setDemandeBudgetProgrammeId(Long demandeBudgetProgrammeId) {
		this.demandeBudgetProgrammeId = demandeBudgetProgrammeId;
	}

	public String getDemandeBudgetProgrammeLibelle() {
		return demandeBudgetProgrammeLibelle;
	}

	public void setDemandeBudgetProgrammeLibelle(
			String demandeBudgetProgrammeLibelle) {
		this.demandeBudgetProgrammeLibelle = demandeBudgetProgrammeLibelle;
	}

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
		result = prime * result
				+ ((ncResultatId == null) ? 0 : ncResultatId.hashCode());
		result = prime * result
				+ ((quantite == null) ? 0 : quantite.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultatAttenduDto other = (ResultatAttenduDto) obj;
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
		if (ncResultatId == null) {
			if (other.ncResultatId != null)
				return false;
		} else if (!ncResultatId.equals(other.ncResultatId))
			return false;
		if (quantite == null) {
			if (other.quantite != null)
				return false;
		} else if (!quantite.equals(other.quantite))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResultatAttenduDto [annee=" + annee + ", quantite=" + quantite
				+ ", ncResultatLibelle=" + ncResultatLibelle
				+ ", demandeBudgetLibelle=" + demandeBudgetLibelle + "]";
	}

	
		
}
