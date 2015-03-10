package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetailsNotificationDecisionBudgetDto implements
		java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:32:38
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ChapitreDto chapitre;
	private DecisionBudgetDto decisionBudget;
	private BigDecimal montant;
	private Boolean budgetRecette;
	private String observation;
	private List<DetailsReparatitionBudgetDto> detailsReparatitionBudgets = new ArrayList<DetailsReparatitionBudgetDto>(
			0);

	public DetailsNotificationDecisionBudgetDto() {
	}

	public DetailsNotificationDecisionBudgetDto(Integer id, ChapitreDto chapitre,
			DecisionBudgetDto decisionBudget, BigDecimal montant) {
		this.id = id;
		this.chapitre = chapitre;
		this.decisionBudget = decisionBudget;
		this.montant = montant;
	}

	public DetailsNotificationDecisionBudgetDto(Integer id, ChapitreDto chapitre,
			DecisionBudgetDto decisionBudget, BigDecimal montant,
			Boolean budgetRecette, String observation,
			List<DetailsReparatitionBudgetDto> detailsReparatitionBudgets) {
		this.id = id;
		this.chapitre = chapitre;
		this.decisionBudget = decisionBudget;
		this.montant = montant;
		this.budgetRecette = budgetRecette;
		this.observation = observation;
		this.detailsReparatitionBudgets = detailsReparatitionBudgets;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ChapitreDto getChapitre() {
		return this.chapitre;
	}

	public void setChapitre(ChapitreDto chapitre) {
		this.chapitre = chapitre;
	}

	public DecisionBudgetDto getDecisionBudget() {
		return this.decisionBudget;
	}

	public void setDecisionBudget(DecisionBudgetDto decisionBudget) {
		this.decisionBudget = decisionBudget;
	}

	public BigDecimal getMontant() {
		return this.montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public Boolean getBudgetRecette() {
		return this.budgetRecette;
	}

	public void setBudgetRecette(Boolean budgetRecette) {
		this.budgetRecette = budgetRecette;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<DetailsReparatitionBudgetDto> getDetailsReparatitionBudgets() {
		return this.detailsReparatitionBudgets;
	}

	public void setDetailsReparatitionBudgets(
			List<DetailsReparatitionBudgetDto> detailsReparatitionBudgets) {
		this.detailsReparatitionBudgets = detailsReparatitionBudgets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((budgetRecette == null) ? 0 : budgetRecette.hashCode());
		result = prime * result
				+ ((chapitre == null) ? 0 : chapitre.hashCode());
		result = prime * result
				+ ((decisionBudget == null) ? 0 : decisionBudget.hashCode());
		result = prime
				* result
				+ ((detailsReparatitionBudgets == null) ? 0
						: detailsReparatitionBudgets.hashCode());
		result = prime * result + id;
		result = prime * result + ((montant == null) ? 0 : montant.hashCode());
		result = prime * result
				+ ((observation == null) ? 0 : observation.hashCode());
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
		DetailsNotificationDecisionBudgetDto other = (DetailsNotificationDecisionBudgetDto) obj;
		if (budgetRecette == null) {
			if (other.budgetRecette != null)
				return false;
		} else if (!budgetRecette.equals(other.budgetRecette))
			return false;
		if (chapitre == null) {
			if (other.chapitre != null)
				return false;
		} else if (!chapitre.equals(other.chapitre))
			return false;
		if (decisionBudget == null) {
			if (other.decisionBudget != null)
				return false;
		} else if (!decisionBudget.equals(other.decisionBudget))
			return false;
		if (detailsReparatitionBudgets == null) {
			if (other.detailsReparatitionBudgets != null)
				return false;
		} else if (!detailsReparatitionBudgets
				.equals(other.detailsReparatitionBudgets))
			return false;
		if (id != other.id)
			return false;
		if (montant == null) {
			if (other.montant != null)
				return false;
		} else if (!montant.equals(other.montant))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		return true;
	}

}
