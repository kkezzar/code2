package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetailsReparatitionBudgetDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:32:54
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private DetailsNotificationDecisionBudgetDto detailsNotificationDecisionBudget;
	private ChapitreDto chapitre;
	private RepartitionBudgetDto repartitionBudget;
	private ArticleDto article;
	private BigDecimal montant;

	private String observation;

	private List<DetailsMouvementBudgetDto> detailsMouvementBudgetsForIdDetailsRepartitionBudgetCible = new ArrayList<DetailsMouvementBudgetDto>(
			0);
	private List<DetailsMouvementBudgetDto> detailsMouvementBudgetsForIdDetailsRepartitionBudgetSource = new ArrayList<DetailsMouvementBudgetDto>(
			0);

	public DetailsReparatitionBudgetDto() {
		chapitre = new ChapitreDto();
		article = new ArticleDto();
	}

	public DetailsReparatitionBudgetDto(Integer id, ChapitreDto chapitre) {
		this.id = id;
		this.chapitre = chapitre;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DetailsNotificationDecisionBudgetDto getDetailsNotificationDecisionBudget() {
		return this.detailsNotificationDecisionBudget;
	}

	public void setDetailsNotificationDecisionBudget(
			DetailsNotificationDecisionBudgetDto detailsNotificationDecisionBudget) {
		this.detailsNotificationDecisionBudget = detailsNotificationDecisionBudget;
	}

	public ChapitreDto getChapitre() {
		return this.chapitre;
	}

	public void setChapitre(ChapitreDto chapitre) {
		this.chapitre = chapitre;
	}

	public RepartitionBudgetDto getRepartitionBudget() {
		return this.repartitionBudget;
	}

	public void setRepartitionBudget(RepartitionBudgetDto repartitionBudget) {
		this.repartitionBudget = repartitionBudget;
	}

	public ArticleDto getArticle() {
		return this.article;
	}

	public void setArticle(ArticleDto article) {
		this.article = article;
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

	public List<DetailsMouvementBudgetDto> getDetailsMouvementBudgetsForIdDetailsRepartitionBudgetCible() {
		return this.detailsMouvementBudgetsForIdDetailsRepartitionBudgetCible;
	}

	public void setDetailsMouvementBudgetsForIdDetailsRepartitionBudgetCible(
			List<DetailsMouvementBudgetDto> detailsMouvementBudgetsForIdDetailsRepartitionBudgetCible) {
		this.detailsMouvementBudgetsForIdDetailsRepartitionBudgetCible = detailsMouvementBudgetsForIdDetailsRepartitionBudgetCible;
	}

	public List<DetailsMouvementBudgetDto> getDetailsMouvementBudgetsForIdDetailsRepartitionBudgetSource() {
		return this.detailsMouvementBudgetsForIdDetailsRepartitionBudgetSource;
	}

	public void setDetailsMouvementBudgetsForIdDetailsRepartitionBudgetSource(
			List<DetailsMouvementBudgetDto> detailsMouvementBudgetsForIdDetailsRepartitionBudgetSource) {
		this.detailsMouvementBudgetsForIdDetailsRepartitionBudgetSource = detailsMouvementBudgetsForIdDetailsRepartitionBudgetSource;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + ((chapitre == null) ? 0 : chapitre.hashCode());
		result = prime
				* result
				+ ((detailsMouvementBudgetsForIdDetailsRepartitionBudgetCible == null) ? 0
						: detailsMouvementBudgetsForIdDetailsRepartitionBudgetCible.hashCode());
		result = prime
				* result
				+ ((detailsMouvementBudgetsForIdDetailsRepartitionBudgetSource == null) ? 0
						: detailsMouvementBudgetsForIdDetailsRepartitionBudgetSource.hashCode());
		result = prime * result
				+ ((detailsNotificationDecisionBudget == null) ? 0 : detailsNotificationDecisionBudget.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((montant == null) ? 0 : montant.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((repartitionBudget == null) ? 0 : repartitionBudget.hashCode());
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
		DetailsReparatitionBudgetDto other = (DetailsReparatitionBudgetDto) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (chapitre == null) {
			if (other.chapitre != null)
				return false;
		} else if (!chapitre.equals(other.chapitre))
			return false;
		if (detailsMouvementBudgetsForIdDetailsRepartitionBudgetCible == null) {
			if (other.detailsMouvementBudgetsForIdDetailsRepartitionBudgetCible != null)
				return false;
		} else if (!detailsMouvementBudgetsForIdDetailsRepartitionBudgetCible
				.equals(other.detailsMouvementBudgetsForIdDetailsRepartitionBudgetCible))
			return false;
		if (detailsMouvementBudgetsForIdDetailsRepartitionBudgetSource == null) {
			if (other.detailsMouvementBudgetsForIdDetailsRepartitionBudgetSource != null)
				return false;
		} else if (!detailsMouvementBudgetsForIdDetailsRepartitionBudgetSource
				.equals(other.detailsMouvementBudgetsForIdDetailsRepartitionBudgetSource))
			return false;
		if (detailsNotificationDecisionBudget == null) {
			if (other.detailsNotificationDecisionBudget != null)
				return false;
		} else if (!detailsNotificationDecisionBudget.equals(other.detailsNotificationDecisionBudget))
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
		if (repartitionBudget == null) {
			if (other.repartitionBudget != null)
				return false;
		} else if (!repartitionBudget.equals(other.repartitionBudget))
			return false;
		return true;
	}

}
