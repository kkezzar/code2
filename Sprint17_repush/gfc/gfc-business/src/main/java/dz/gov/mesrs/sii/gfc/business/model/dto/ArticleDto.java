package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.ArrayList;
import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsProjetBudget;

public class ArticleDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:31:55
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private ChapitreDto chapitre;
	private String code;
	private String intituleFr;
	private String intituleAr;
	private String intituleFrCourt;
	private String intituleArCourt;
	private String description;
	private List<DetailsFicheBesoinsDto> detailsFicheBesoinses = new ArrayList<DetailsFicheBesoinsDto>(0);
	private List<AffectationEtabChapitreArticleDto> affectationEtabChapitreArticles = new ArrayList<AffectationEtabChapitreArticleDto>(
			0);
	private List<DetailsProjetBudget> detailsProjetBudgets = new ArrayList<DetailsProjetBudget>(0);
	private List<ParagrapheDto> paragraphes = new ArrayList<ParagrapheDto>(0);
	private List<DetailsReparatitionBudgetDto> detailsReparatitionBudgets = new ArrayList<DetailsReparatitionBudgetDto>(
			0);

	public ArticleDto() {
		chapitre = new ChapitreDto();
	}

	public ArticleDto(Integer id, String code, String intituleFr, String intituleAr) {
		this.id = id;
		this.code = code;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
	}

	public ArticleDto(Integer id, ChapitreDto chapitre, String code, String intituleFr, String intituleAr,
			String description, List<DetailsFicheBesoinsDto> detailsFicheBesoinses,
			List<AffectationEtabChapitreArticleDto> affectationEtabChapitreArticles,
			List<DetailsProjetBudget> detailsProjetBudgets, List<ParagrapheDto> paragraphes,
			List<DetailsReparatitionBudgetDto> detailsReparatitionBudgets) {
		this.id = id;
		this.chapitre = chapitre;
		this.code = code;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.description = description;
		this.detailsFicheBesoinses = detailsFicheBesoinses;
		this.affectationEtabChapitreArticles = affectationEtabChapitreArticles;
		this.detailsProjetBudgets = detailsProjetBudgets;
		this.paragraphes = paragraphes;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIntituleFr() {
		return this.intituleFr;
	}

	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	public String getIntituleAr() {
		return this.intituleAr;
	}

	public void setIntituleAr(String intituleAr) {
		this.intituleAr = intituleAr;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<DetailsFicheBesoinsDto> getDetailsFicheBesoinses() {
		return this.detailsFicheBesoinses;
	}

	public void setDetailsFicheBesoinses(List<DetailsFicheBesoinsDto> detailsFicheBesoinses) {
		this.detailsFicheBesoinses = detailsFicheBesoinses;
	}

	public List<AffectationEtabChapitreArticleDto> getAffectationEtabChapitreArticles() {
		return this.affectationEtabChapitreArticles;
	}

	public void setAffectationEtabChapitreArticles(
			List<AffectationEtabChapitreArticleDto> affectationEtabChapitreArticles) {
		this.affectationEtabChapitreArticles = affectationEtabChapitreArticles;
	}

	public List<DetailsProjetBudget> getDetailsProjetBudgets() {
		return this.detailsProjetBudgets;
	}

	public void setDetailsProjetBudgets(List<DetailsProjetBudget> detailsProjetBudgets) {
		this.detailsProjetBudgets = detailsProjetBudgets;
	}

	public List<ParagrapheDto> getParagraphes() {
		return this.paragraphes;
	}

	public void setParagraphes(List<ParagrapheDto> paragraphes) {
		this.paragraphes = paragraphes;
	}

	public List<DetailsReparatitionBudgetDto> getDetailsReparatitionBudgets() {
		return this.detailsReparatitionBudgets;
	}

	public void setDetailsReparatitionBudgets(List<DetailsReparatitionBudgetDto> detailsReparatitionBudgets) {
		this.detailsReparatitionBudgets = detailsReparatitionBudgets;
	}

	/**
	 * @return the intituleFrCourt
	 */
	public String getIntituleFrCourt() {
		return intituleFrCourt;
	}

	/**
	 * @param intituleFrCourt
	 *            the intituleFrCourt to set
	 */
	public void setIntituleFrCourt(String intituleFrCourt) {
		this.intituleFrCourt = intituleFrCourt;
	}

	/**
	 * @return the intituleArCourt
	 */
	public String getIntituleArCourt() {
		return intituleArCourt;
	}

	/**
	 * @param intituleArCourt
	 *            the intituleArCourt to set
	 */
	public void setIntituleArCourt(String intituleArCourt) {
		this.intituleArCourt = intituleArCourt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((chapitre == null) ? 0 : chapitre.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((intituleFr == null) ? 0 : intituleFr.hashCode());
		result = prime * result + ((intituleAr == null) ? 0 : intituleAr.hashCode());
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
		ArticleDto other = (ArticleDto) obj;
		if (affectationEtabChapitreArticles == null) {
			if (other.affectationEtabChapitreArticles != null)
				return false;
		} else if (!affectationEtabChapitreArticles.equals(other.affectationEtabChapitreArticles))
			return false;
		if (chapitre == null) {
			if (other.chapitre != null)
				return false;
		} else if (!chapitre.equals(other.chapitre))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (detailsFicheBesoinses == null) {
			if (other.detailsFicheBesoinses != null)
				return false;
		} else if (!detailsFicheBesoinses.equals(other.detailsFicheBesoinses))
			return false;
		if (detailsProjetBudgets == null) {
			if (other.detailsProjetBudgets != null)
				return false;
		} else if (!detailsProjetBudgets.equals(other.detailsProjetBudgets))
			return false;
		if (detailsReparatitionBudgets == null) {
			if (other.detailsReparatitionBudgets != null)
				return false;
		} else if (!detailsReparatitionBudgets.equals(other.detailsReparatitionBudgets))
			return false;
		if (id != other.id)
			return false;
		if (intituleFr == null) {
			if (other.intituleFr != null)
				return false;
		} else if (!intituleFr.equals(other.intituleFr))
			return false;
		if (intituleAr == null) {
			if (other.intituleAr != null)
				return false;
		} else if (!intituleAr.equals(other.intituleAr))
			return false;
		if (paragraphes == null) {
			if (other.paragraphes != null)
				return false;
		} else if (!paragraphes.equals(other.paragraphes))
			return false;
		return true;
	}

}
