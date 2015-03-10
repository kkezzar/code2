package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.ArrayList;
import java.util.List;

public class ParagrapheDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:33:31
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private ArticleDto article;
	private String code;
	private String intituleFr;
	private String intituleAr;
	private String intituleFrCourt;
	private String intituleArCourt;
	private String description;
	private List<DetailsFicheBesoinsDto> detailsFicheBesoinses = new ArrayList<DetailsFicheBesoinsDto>(0);

	public ParagrapheDto() {
		article = new ArticleDto();
	}

	public ParagrapheDto(Integer id, String code, String intituleFr, String intituleAr) {
		this.id = id;
		this.code = code;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
	}

	public ParagrapheDto(Integer id, ArticleDto article, String code, String intituleFr, String intituleAr,
			String description, List<DetailsFicheBesoinsDto> detailsFicheBesoinses) {
		this.id = id;
		this.article = article;
		this.code = code;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.description = description;
		this.detailsFicheBesoinses = detailsFicheBesoinses;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArticleDto getArticle() {
		return this.article;
	}

	public void setArticle(ArticleDto article) {
		this.article = article;
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
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((detailsFicheBesoinses == null) ? 0 : detailsFicheBesoinses.hashCode());
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
		ParagrapheDto other = (ParagrapheDto) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
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
		return true;
	}

}
