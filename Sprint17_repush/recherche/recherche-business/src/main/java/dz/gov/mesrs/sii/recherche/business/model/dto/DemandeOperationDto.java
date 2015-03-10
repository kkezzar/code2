/**
 * [dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto.java] 
 * @author rlaib on : 16 déc. 2014  13:24:10
 */
package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;


public class DemandeOperationDto implements Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:55:29
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private short annee;	
	private Double montantEstime;
	
	// Operation
	private Integer ncOperationId;
	private String ncOperationCode;
	private String ncOperationLibelle;
	
	// Demande de budget
	private Long demandeBudgetId;
	private String demandeBudgetLibelle;
	private Long demandeBudgetProgrammeId;
	private String demandeBudgetProgrammeLibelle;
	
	public DemandeOperationDto() {
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

	public Double getMontantEstime() {
		return montantEstime;
	}

	public void setMontantEstime(Double montantEstime) {
		this.montantEstime = montantEstime;
	}

	public Integer getNcOperationId() {
		return ncOperationId;
	}

	public void setNcOperationId(Integer ncOperationId) {
		this.ncOperationId = ncOperationId;
	}

	public String getNcOperationCode() {
		return ncOperationCode;
	}

	public void setNcOperationCode(String ncOperationCode) {
		this.ncOperationCode = ncOperationCode;
	}

	public String getNcOperationLibelle() {
		return ncOperationLibelle;
	}

	public void setNcOperationLibelle(String ncOperationLibelle) {
		this.ncOperationLibelle = ncOperationLibelle;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((montantEstime == null) ? 0 : montantEstime.hashCode());
		result = prime * result
				+ ((ncOperationId == null) ? 0 : ncOperationId.hashCode());
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
		DemandeOperationDto other = (DemandeOperationDto) obj;
		if (annee != other.annee)
			return false;
		if (demandeBudgetId == null) {
			if (other.demandeBudgetId != null)
				return false;
		} else if (!demandeBudgetId.equals(other.demandeBudgetId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (montantEstime == null) {
			if (other.montantEstime != null)
				return false;
		} else if (!montantEstime.equals(other.montantEstime))
			return false;
		if (ncOperationId == null) {
			if (other.ncOperationId != null)
				return false;
		} else if (!ncOperationId.equals(other.ncOperationId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DemandeOperationDto [annee=" + annee + ", montantEstime="
				+ montantEstime + ", ncOperationLibelle=" + ncOperationLibelle
				+ "]";
	}

	
		
}
