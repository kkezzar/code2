package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;

public class DetailsMouvementBudgetDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:32:33
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private DetailsReparatitionBudgetDto detailsRepartitionBudgetCible;
	private DetailsReparatitionBudgetDto detailsRepartitionBudgetSource;
	private MouvementBudgetDto mouvementBudget;
	private BigDecimal montant;
	private String observation;

	public DetailsMouvementBudgetDto() {
		detailsRepartitionBudgetSource = new DetailsReparatitionBudgetDto();
	}

	public DetailsMouvementBudgetDto(Integer id, DetailsReparatitionBudgetDto detailsRepartitionBudgetSource,
			MouvementBudgetDto mouvementBudget, BigDecimal montant) {
		this.id = id;
		this.detailsRepartitionBudgetSource = detailsRepartitionBudgetSource;
		this.mouvementBudget = mouvementBudget;
		this.montant = montant;
	}

	public DetailsMouvementBudgetDto(Integer id, DetailsReparatitionBudgetDto detailsRepartitionBudgetCible,
			DetailsReparatitionBudgetDto detailsRepartitionBudgetSource, MouvementBudgetDto mouvementBudget,
			BigDecimal montant, String observation) {
		this.id = id;
		this.detailsRepartitionBudgetCible = detailsRepartitionBudgetCible;
		this.detailsRepartitionBudgetSource = detailsRepartitionBudgetSource;
		this.mouvementBudget = mouvementBudget;
		this.montant = montant;
		this.observation = observation;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DetailsReparatitionBudgetDto getDetailsRepartitionBudgetCible() {
		return this.detailsRepartitionBudgetCible;
	}

	public void setDetailsRepartitionBudgetCible(DetailsReparatitionBudgetDto detailsRepartitionBudgetCible) {
		this.detailsRepartitionBudgetCible = detailsRepartitionBudgetCible;
	}

	public DetailsReparatitionBudgetDto getDetailsRepartitionBudgetSource() {
		return this.detailsRepartitionBudgetSource;
	}

	public void setDetailsRepartitionBudgetSource(DetailsReparatitionBudgetDto detailsRepartitionBudgetSource) {
		this.detailsRepartitionBudgetSource = detailsRepartitionBudgetSource;
	}

	public MouvementBudgetDto getMouvementBudget() {
		return this.mouvementBudget;
	}

	public void setMouvementBudget(MouvementBudgetDto mouvementBudget) {
		this.mouvementBudget = mouvementBudget;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((detailsRepartitionBudgetCible == null) ? 0 : detailsRepartitionBudgetCible.hashCode());
		result = prime * result
				+ ((detailsRepartitionBudgetSource == null) ? 0 : detailsRepartitionBudgetSource.hashCode());
		result = prime * result + id;
		result = prime * result + ((montant == null) ? 0 : montant.hashCode());
		result = prime * result + ((mouvementBudget == null) ? 0 : mouvementBudget.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
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
		DetailsMouvementBudgetDto other = (DetailsMouvementBudgetDto) obj;
		if (detailsRepartitionBudgetCible == null) {
			if (other.detailsRepartitionBudgetCible != null)
				return false;
		} else if (!detailsRepartitionBudgetCible.equals(other.detailsRepartitionBudgetCible))
			return false;
		if (detailsRepartitionBudgetSource == null) {
			if (other.detailsRepartitionBudgetSource != null)
				return false;
		} else if (!detailsRepartitionBudgetSource.equals(other.detailsRepartitionBudgetSource))
			return false;
		if (id != other.id)
			return false;
		if (montant == null) {
			if (other.montant != null)
				return false;
		} else if (!montant.equals(other.montant))
			return false;
		if (mouvementBudget == null) {
			if (other.mouvementBudget != null)
				return false;
		} else if (!mouvementBudget.equals(other.mouvementBudget))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		return true;
	}

}
