package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

public class AffectationEtabChapitreArticleDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:31:32
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private ChapitreDto chapitre = new ChapitreDto();
	private RefEtablissementDto refEtablissement = new RefEtablissementDto();
	private ArticleDto article = new ArticleDto();
	private BigDecimal montantPlafond;
	private String observation;
	private List<FondsDto> fondses = new ArrayList<FondsDto>(0);
	private List<RegieDto> regies = new ArrayList<RegieDto>(0);

	private boolean selected;

	public AffectationEtabChapitreArticleDto() {
		chapitre = new ChapitreDto();
		refEtablissement = new RefEtablissementDto();
		article = new ArticleDto();
	}

	public AffectationEtabChapitreArticleDto(Integer id, ChapitreDto chapitre, RefEtablissementDto refEtablissement) {
		this.id = id;
		this.chapitre = chapitre;
		this.refEtablissement = refEtablissement;
	}

	public AffectationEtabChapitreArticleDto(Integer id, ChapitreDto chapitre, RefEtablissementDto refEtablissement,
			ArticleDto article, BigDecimal montantPlafond, String observation, List<FondsDto> fondses,
			List<RegieDto> regies) {
		this.id = id;
		this.chapitre = chapitre;
		this.refEtablissement = refEtablissement;
		this.article = article;
		this.montantPlafond = montantPlafond;
		this.observation = observation;
		this.fondses = fondses;
		this.regies = regies;
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

	public RefEtablissementDto getRefEtablissement() {
		return this.refEtablissement;
	}

	public void setRefEtablissement(RefEtablissementDto refEtablissement) {
		this.refEtablissement = refEtablissement;
	}

	public ArticleDto getArticle() {
		return this.article;
	}

	public void setArticle(ArticleDto article) {
		this.article = article;
	}

	public BigDecimal getMontantPlafond() {
		return this.montantPlafond;
	}

	public void setMontantPlafond(BigDecimal montantPlafond) {
		this.montantPlafond = montantPlafond;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<FondsDto> getFondses() {
		return this.fondses;
	}

	public void setFondses(List<FondsDto> fondses) {
		this.fondses = fondses;
	}

	public List<RegieDto> getRegies() {
		return this.regies;
	}

	public void setRegies(List<RegieDto> regies) {
		this.regies = regies;
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected
	 *            the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + ((chapitre == null) ? 0 : chapitre.hashCode());
		result = prime * result + ((fondses == null) ? 0 : fondses.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((montantPlafond == null) ? 0 : montantPlafond.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((refEtablissement == null) ? 0 : refEtablissement.hashCode());
		result = prime * result + ((regies == null) ? 0 : regies.hashCode());
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
		AffectationEtabChapitreArticleDto other = (AffectationEtabChapitreArticleDto) obj;
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
		if (fondses == null) {
			if (other.fondses != null)
				return false;
		} else if (!fondses.equals(other.fondses))
			return false;
		if (id != other.id)
			return false;
		if (montantPlafond == null) {
			if (other.montantPlafond != null)
				return false;
		} else if (!montantPlafond.equals(other.montantPlafond))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (refEtablissement == null) {
			if (other.refEtablissement != null)
				return false;
		} else if (!refEtablissement.equals(other.refEtablissement))
			return false;
		if (regies == null) {
			if (other.regies != null)
				return false;
		} else if (!regies.equals(other.regies))
			return false;
		return true;
	}

}
