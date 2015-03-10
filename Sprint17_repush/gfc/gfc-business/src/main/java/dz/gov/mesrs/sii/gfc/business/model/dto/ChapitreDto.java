package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.ArrayList;
import java.util.List;

public class ChapitreDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:32:03
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private PartieDto partie;
	private TitreDto titre;
	private SectionDto idSection;
	private SousSectionDto idSousSection;
	private String code;
	private String intituleFr;
	private String intituleAr;
	private String intituleFrCourt;
	private String intituleArCourt;
	private String description;

	private List<DetailsNotificationDecisionBudgetDto> detailsNotificationDecisionBudgets = new ArrayList<DetailsNotificationDecisionBudgetDto>(
			0);
	private List<DetailsReparatitionBudgetDto> detailsReparatitionBudgets = new ArrayList<DetailsReparatitionBudgetDto>(
			0);
	private List<ArticleDto> articles = new ArrayList<ArticleDto>(0);
	private List<DetailsFicheBesoinsDto> detailsFicheBesoinses = new ArrayList<DetailsFicheBesoinsDto>(0);
	private List<AffectationEtabChapitreArticleDto> affectationEtabChapitreArticles = new ArrayList<AffectationEtabChapitreArticleDto>(
			0);
	private DetailsProjetBudgetDto detailsProjetBudget;

	private Boolean recetteType;

	public ChapitreDto() {
		partie = new PartieDto();
		titre = new TitreDto();
		idSection = new SectionDto();
		idSousSection = new SousSectionDto();
	}

	public ChapitreDto(Integer id, SectionDto idSection, String code, String intituleFr, String intituleAr) {
		this.id = id;
		this.idSection = idSection;
		this.code = code;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
	}

	public ChapitreDto(Integer id, PartieDto partie, TitreDto titre, SectionDto idSection,
			SousSectionDto idSousSection, String code, String intituleFr, String intituleAr, String description,
			List<DetailsNotificationDecisionBudgetDto> detailsNotificationDecisionBudgets,
			List<DetailsReparatitionBudgetDto> detailsReparatitionBudgets, List<ArticleDto> articles,
			List<DetailsFicheBesoinsDto> detailsFicheBesoinses,
			List<AffectationEtabChapitreArticleDto> affectationEtabChapitreArticles,
			DetailsProjetBudgetDto detailsProjetBudget) {
		this.id = id;
		this.partie = partie;
		this.titre = titre;
		this.idSection = idSection;
		this.idSousSection = idSousSection;
		this.code = code;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.description = description;
		this.detailsNotificationDecisionBudgets = detailsNotificationDecisionBudgets;
		this.detailsReparatitionBudgets = detailsReparatitionBudgets;
		this.articles = articles;
		this.detailsFicheBesoinses = detailsFicheBesoinses;
		this.affectationEtabChapitreArticles = affectationEtabChapitreArticles;
		this.detailsProjetBudget = detailsProjetBudget;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PartieDto getPartie() {
		return this.partie;
	}

	public void setPartie(PartieDto partie) {
		this.partie = partie;
	}

	public TitreDto getTitre() {
		return this.titre;
	}

	public void setTitre(TitreDto titre) {
		this.titre = titre;
	}

	/**
	 * @return the idSection
	 */
	public SectionDto getIdSection() {
		return idSection;
	}

	/**
	 * @param idSection
	 *            the idSection to set
	 */
	public void setIdSection(SectionDto idSection) {
		this.idSection = idSection;
	}

	/**
	 * @return the idSousSection
	 */
	public SousSectionDto getIdSousSection() {
		return idSousSection;
	}

	/**
	 * @param idSousSection
	 *            the idSousSection to set
	 */
	public void setIdSousSection(SousSectionDto idSousSection) {
		this.idSousSection = idSousSection;
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

	public List<DetailsNotificationDecisionBudgetDto> getDetailsNotificationDecisionBudgets() {
		return this.detailsNotificationDecisionBudgets;
	}

	public void setDetailsNotificationDecisionBudgets(
			List<DetailsNotificationDecisionBudgetDto> detailsNotificationDecisionBudgets) {
		this.detailsNotificationDecisionBudgets = detailsNotificationDecisionBudgets;
	}

	public List<DetailsReparatitionBudgetDto> getDetailsReparatitionBudgets() {
		return this.detailsReparatitionBudgets;
	}

	public void setDetailsReparatitionBudgets(List<DetailsReparatitionBudgetDto> detailsReparatitionBudgets) {
		this.detailsReparatitionBudgets = detailsReparatitionBudgets;
	}

	public List<ArticleDto> getArticles() {
		return this.articles;
	}

	public void setArticles(List<ArticleDto> articles) {
		this.articles = articles;
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

	public DetailsProjetBudgetDto getDetailsProjetBudget() {
		return this.detailsProjetBudget;
	}

	public void setDetailsProjetBudget(DetailsProjetBudgetDto detailsProjetBudget) {
		this.detailsProjetBudget = detailsProjetBudget;
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

	/**
	 * @return the recetteType
	 */
	public Boolean isRecetteType() {
		return recetteType;
	}

	public Boolean getRecetteType() {
		return recetteType;
	}

	/**
	 * @param recetteType
	 *            the recetteType to set
	 */
	public void setRecetteType(Boolean recetteType) {
		this.recetteType = recetteType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((detailsProjetBudget == null) ? 0 : detailsProjetBudget.hashCode());

		result = prime * result + +((id == null) ? 0 : id.hashCode());
		;
		result = prime * result + +((idSection == null) ? 0 : idSection.hashCode());
		;
		result = prime * result + ((idSousSection == null) ? 0 : idSousSection.hashCode());
		result = prime * result + ((intituleFr == null) ? 0 : intituleFr.hashCode());
		result = prime * result + ((intituleAr == null) ? 0 : intituleAr.hashCode());
		result = prime * result + ((partie == null) ? 0 : partie.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
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
		ChapitreDto other = (ChapitreDto) obj;
		if (affectationEtabChapitreArticles == null) {
			if (other.affectationEtabChapitreArticles != null)
				return false;
		} else if (!affectationEtabChapitreArticles.equals(other.affectationEtabChapitreArticles))
			return false;
		if (articles == null) {
			if (other.articles != null)
				return false;
		} else if (!articles.equals(other.articles))
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
		if (detailsNotificationDecisionBudgets == null) {
			if (other.detailsNotificationDecisionBudgets != null)
				return false;
		} else if (!detailsNotificationDecisionBudgets.equals(other.detailsNotificationDecisionBudgets))
			return false;
		if (detailsProjetBudget == null) {
			if (other.detailsProjetBudget != null)
				return false;
		} else if (!detailsProjetBudget.equals(other.detailsProjetBudget))
			return false;
		if (detailsReparatitionBudgets == null) {
			if (other.detailsReparatitionBudgets != null)
				return false;
		} else if (!detailsReparatitionBudgets.equals(other.detailsReparatitionBudgets))
			return false;
		if (id != other.id)
			return false;
		if (idSection != other.idSection)
			return false;
		if (idSousSection == null) {
			if (other.idSousSection != null)
				return false;
		} else if (!idSousSection.equals(other.idSousSection))
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
		if (partie == null) {
			if (other.partie != null)
				return false;
		} else if (!partie.equals(other.partie))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}

}
