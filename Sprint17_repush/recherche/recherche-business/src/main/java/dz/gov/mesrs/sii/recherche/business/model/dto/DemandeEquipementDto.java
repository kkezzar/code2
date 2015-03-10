/**
 * [dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto.java] 
 * @author rlaib on : 16 déc. 2014  13:24:10
 */
package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;


public class DemandeEquipementDto implements Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:55:29
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private short annee;	
	private Integer quantite;	
	private Double montantEstime;

	// Famille equipement
	private Integer ncFamilleId;
	private String ncFamilleCode;
	private String ncFamilleLibelle;
	
	// Famille equipement
	private Integer ncSousFamilleId;
	private String ncSousFamilleCode;
	private String ncSousFamilleLibelle;
	
	// Demande de budget
	private Long demandeBudgetId;
	private String demandeBudgetLibelle;
	private Long demandeBudgetProgrammeId;
	private String demandeBudgetProgrammeLibelle;
	
	public DemandeEquipementDto() {
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

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Double getMontantEstime() {
		return montantEstime;
	}

	public void setMontantEstime(Double montantEstime) {
		this.montantEstime = montantEstime;
	}

	public Integer getNcFamilleId() {
		return ncFamilleId;
	}

	public void setNcFamilleId(Integer ncFamilleId) {
		this.ncFamilleId = ncFamilleId;
	}

	public String getNcFamilleCode() {
		return ncFamilleCode;
	}

	public void setNcFamilleCode(String ncFamilleCode) {
		this.ncFamilleCode = ncFamilleCode;
	}

	public String getNcFamilleLibelle() {
		return ncFamilleLibelle;
	}

	public void setNcFamilleLibelle(String ncFamilleLibelle) {
		this.ncFamilleLibelle = ncFamilleLibelle;
	}

	public Integer getNcSousFamilleId() {
		return ncSousFamilleId;
	}

	public void setNcSousFamilleId(Integer ncSousFamilleId) {
		this.ncSousFamilleId = ncSousFamilleId;
	}

	public String getNcSousFamilleCode() {
		return ncSousFamilleCode;
	}

	public void setNcSousFamilleCode(String ncSousFamilleCode) {
		this.ncSousFamilleCode = ncSousFamilleCode;
	}

	public String getNcSousFamilleLibelle() {
		return ncSousFamilleLibelle;
	}

	public void setNcSousFamilleLibelle(String ncSousFamilleLibelle) {
		this.ncSousFamilleLibelle = ncSousFamilleLibelle;
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
				+ ((ncFamilleId == null) ? 0 : ncFamilleId.hashCode());
		result = prime * result
				+ ((ncSousFamilleId == null) ? 0 : ncSousFamilleId.hashCode());
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
		DemandeEquipementDto other = (DemandeEquipementDto) obj;
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
		if (ncFamilleId == null) {
			if (other.ncFamilleId != null)
				return false;
		} else if (!ncFamilleId.equals(other.ncFamilleId))
			return false;
		if (ncSousFamilleId == null) {
			if (other.ncSousFamilleId != null)
				return false;
		} else if (!ncSousFamilleId.equals(other.ncSousFamilleId))
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
		return "DemandeEquipementDto [annee=" + annee + ", ncFamilleLibelle="
				+ ncFamilleLibelle + ", ncSousFamilleLibelle="
				+ ncSousFamilleLibelle + ", demandeBudgetLibelle="
				+ demandeBudgetLibelle + "]";
	}

	
	
}
