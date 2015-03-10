package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;

public class ConsolidationBesoinsDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:32:17
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private FicheBesoinsDto ficheBesoins;
	private ChapitreDto chapitre;
	private ArticleDto article;
	private BigDecimal montantDemande;
	private BigDecimal montantPropose;

	public ConsolidationBesoinsDto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FicheBesoinsDto getFicheBesoins() {
		return ficheBesoins;
	}

	public void setFicheBesoins(FicheBesoinsDto ficheBesoins) {
		this.ficheBesoins = ficheBesoins;
	}

	public ChapitreDto getChapitre() {
		return chapitre;
	}

	public void setChapitre(ChapitreDto chapitre) {
		this.chapitre = chapitre;
	}

	public ArticleDto getArticle() {
		return article;
	}

	public void setArticle(ArticleDto article) {
		this.article = article;
	}

	public BigDecimal getMontantDemande() {
		return montantDemande;
	}

	public void setMontantDemande(BigDecimal montantDemande) {
		this.montantDemande = montantDemande;
	}

	public BigDecimal getMontantPropose() {
		return montantPropose;
	}

	public void setMontantPropose(BigDecimal montantPropose) {
		this.montantPropose = montantPropose;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + ((chapitre == null) ? 0 : chapitre.hashCode());
		result = prime * result + ((ficheBesoins == null) ? 0 : ficheBesoins.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((montantDemande == null) ? 0 : montantDemande.hashCode());
		result = prime * result + ((montantPropose == null) ? 0 : montantPropose.hashCode());
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
		ConsolidationBesoinsDto other = (ConsolidationBesoinsDto) obj;
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
		if (ficheBesoins == null) {
			if (other.ficheBesoins != null)
				return false;
		} else if (!ficheBesoins.equals(other.ficheBesoins))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (montantDemande == null) {
			if (other.montantDemande != null)
				return false;
		} else if (!montantDemande.equals(other.montantDemande))
			return false;
		if (montantPropose == null) {
			if (other.montantPropose != null)
				return false;
		} else if (!montantPropose.equals(other.montantPropose))
			return false;
		return true;
	}

}
