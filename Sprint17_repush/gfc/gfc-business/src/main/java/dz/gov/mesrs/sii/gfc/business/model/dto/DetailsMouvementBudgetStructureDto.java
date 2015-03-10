package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class DetailsMouvementBudgetStructureDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private NomenclatureDto typeMouvement;

	private RepartitionBudgetStructureDto repartitionBudgetStructureSource;
	private RepartitionBudgetStructureDto repartitionBudgetStructureCible;
	private BigDecimal montant;
	private String observation;

	public DetailsMouvementBudgetStructureDto() {
	}

	public DetailsMouvementBudgetStructureDto(Integer id, BigDecimal montant) {
		this.id = id;
		this.montant = montant;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RepartitionBudgetStructureDto getRepartitionBudgetStructureSource() {
		return this.repartitionBudgetStructureSource;
	}

	public void setRepartitionBudgetStructureSource(RepartitionBudgetStructureDto repartitionBudgetStructureSource) {
		this.repartitionBudgetStructureSource = repartitionBudgetStructureSource;
	}

	public RepartitionBudgetStructureDto getRepartitionBudgetStructureCible() {
		return this.repartitionBudgetStructureCible;
	}

	public void setRepartitionBudgetStructureCible(RepartitionBudgetStructureDto repartitionBudgetStructureCible) {
		this.repartitionBudgetStructureCible = repartitionBudgetStructureCible;
	}

	public BigDecimal getMontant() {
		return this.montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public NomenclatureDto getTypeMouvement() {
		return this.typeMouvement;
	}

	public void setTypeMouvement(NomenclatureDto typeMouvement) {
		this.typeMouvement = typeMouvement;
	}

	@Override
	public String toString() {
		return "DetailsMouvementBudgetStructureDto [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DetailsMouvementBudgetStructureDto other = (DetailsMouvementBudgetStructureDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}