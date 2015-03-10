package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

public class DetailsProjetBudgetEtablissementDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:32:50
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private ProjetBudgetDto projetBudget;
	private ChapitreDto chapitre;
	private ArticleDto article;

	private RefEtablissementDto etablissement;

	private BigDecimal montantBesoins;
	private BigDecimal montantFinal;
	private String description;

	public DetailsProjetBudgetEtablissementDto() {
	}

	public DetailsProjetBudgetEtablissementDto(ProjetBudgetDto projetBudget, ChapitreDto chapitre, Integer idChapitre,
			BigDecimal montantBesoins) {
		this.projetBudget = projetBudget;
		this.chapitre = chapitre;
		this.montantBesoins = montantBesoins;
	}

	public DetailsProjetBudgetEtablissementDto(ProjetBudgetDto projetBudget, ChapitreDto chapitre, ArticleDto article,
			Integer idChapitre, BigDecimal montantBesoins, BigDecimal montantFinal, String description) {
		this.projetBudget = projetBudget;
		this.chapitre = chapitre;
		this.article = article;
		this.montantBesoins = montantBesoins;
		this.montantFinal = montantFinal;
		this.description = description;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProjetBudgetDto getProjetBudget() {
		return this.projetBudget;
	}

	public void setProjetBudget(ProjetBudgetDto projetBudget) {
		this.projetBudget = projetBudget;
	}

	public ChapitreDto getChapitre() {
		return this.chapitre;
	}

	public void setChapitre(ChapitreDto chapitre) {
		this.chapitre = chapitre;
	}

	public ArticleDto getArticle() {
		return this.article;
	}

	public void setArticle(ArticleDto article) {
		this.article = article;
	}

	public RefEtablissementDto getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(RefEtablissementDto etablissement) {
		this.etablissement = etablissement;
	}

	public BigDecimal getMontantBesoins() {
		return this.montantBesoins;
	}

	public void setMontantBesoins(BigDecimal montantBesoins) {
		this.montantBesoins = montantBesoins;
	}

	public BigDecimal getMontantFinal() {
		return this.montantFinal;
	}

	public void setMontantFinal(BigDecimal montantFinal) {
		this.montantFinal = montantFinal;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + ((chapitre == null) ? 0 : chapitre.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((etablissement == null) ? 0 : etablissement.hashCode());
		result = prime * result + ((montantBesoins == null) ? 0 : montantBesoins.hashCode());
		result = prime * result + ((montantFinal == null) ? 0 : montantFinal.hashCode());
		result = prime * result + ((projetBudget == null) ? 0 : projetBudget.hashCode());
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
		DetailsProjetBudgetEtablissementDto other = (DetailsProjetBudgetEtablissementDto) obj;
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (montantBesoins == null) {
			if (other.montantBesoins != null)
				return false;
		} else if (!montantBesoins.equals(other.montantBesoins))
			return false;
		if (montantFinal == null) {
			if (other.montantFinal != null)
				return false;
		} else if (!montantFinal.equals(other.montantFinal))
			return false;
		if (projetBudget == null) {
			if (other.projetBudget != null)
				return false;
		} else if (!projetBudget.equals(other.projetBudget))
			return false;
		return true;
	}

}
