package dz.gov.mesrs.sii.gfc.business.model.dto;

public class AffectationRegieChapitreArticleDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:31:32
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private AffectationEtabChapitreArticleDto affectationEtabChapitreArticle;
	private RegieDto regie;
	private double plafond;

	public AffectationRegieChapitreArticleDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AffectationEtabChapitreArticleDto getAffectationEtabChapitreArticle() {
		return affectationEtabChapitreArticle;
	}

	public void setAffectationEtabChapitreArticle(AffectationEtabChapitreArticleDto affectationEtabChapitreArticle) {
		this.affectationEtabChapitreArticle = affectationEtabChapitreArticle;
	}

	public RegieDto getRegie() {
		return regie;
	}

	public void setRegie(RegieDto regie) {
		this.regie = regie;
	}

	/**
	 * @return the plafond
	 */
	public double getPlafond() {
		return plafond;
	}

	/**
	 * @param plafond
	 *            the plafond to set
	 */
	public void setPlafond(double plafond) {
		this.plafond = plafond;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((affectationEtabChapitreArticle == null) ? 0 : affectationEtabChapitreArticle.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((regie == null) ? 0 : regie.hashCode());
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
		AffectationRegieChapitreArticleDto other = (AffectationRegieChapitreArticleDto) obj;
		if (affectationEtabChapitreArticle == null) {
			if (other.affectationEtabChapitreArticle != null)
				return false;
		} else if (!affectationEtabChapitreArticle.equals(other.affectationEtabChapitreArticle))
			return false;
		if (id != other.id)
			return false;
		if (regie == null) {
			if (other.regie != null)
				return false;
		} else if (!regie.equals(other.regie))
			return false;
		return true;
	}

}
