package dz.gov.mesrs.sii.gfc.business.model.dto;

public class AffectationFondsChapitreArticleDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:31:32
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private AffectationEtabChapitreArticleDto affectationEtabChapitreArticle;
	private FondsDto fonds;

	public AffectationFondsChapitreArticleDto() {
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

	public FondsDto getFonds() {
		return fonds;
	}

	public void setFonds(FondsDto fonds) {
		this.fonds = fonds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((affectationEtabChapitreArticle == null) ? 0 : affectationEtabChapitreArticle.hashCode());
		result = prime * result + ((fonds == null) ? 0 : fonds.hashCode());
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
		AffectationFondsChapitreArticleDto other = (AffectationFondsChapitreArticleDto) obj;
		if (affectationEtabChapitreArticle == null) {
			if (other.affectationEtabChapitreArticle != null)
				return false;
		} else if (!affectationEtabChapitreArticle.equals(other.affectationEtabChapitreArticle))
			return false;
		if (fonds == null) {
			if (other.fonds != null)
				return false;
		} else if (!fonds.equals(other.fonds))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
