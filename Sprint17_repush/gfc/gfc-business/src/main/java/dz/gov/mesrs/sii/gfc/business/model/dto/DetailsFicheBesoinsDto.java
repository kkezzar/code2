package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class DetailsFicheBesoinsDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:32:27
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private RefEtablissementDto etablissement;
	private RefStructureDto structure;

	private FicheBesoinsDto ficheBesoins;

	private ChapitreDto chapitre;
	private ArticleDto article;
	private ParagrapheDto paragraphe;

	private BigDecimal montant;
	private String description;

	private boolean budgetRecette;

	public DetailsFicheBesoinsDto() {
		ficheBesoins = new FicheBesoinsDto();
		structure = new RefStructureDto();
		chapitre = new ChapitreDto();
		article = new ArticleDto();
		paragraphe = new ParagrapheDto();
	}

	public DetailsFicheBesoinsDto(Integer id, ChapitreDto chapitre, RefEtablissementDto etablissement,
			FicheBesoinsDto ficheBesoins, boolean budgetRecette) {
		this.id = id;
		this.chapitre = chapitre;
		this.etablissement = etablissement;
		this.ficheBesoins = ficheBesoins;
		this.budgetRecette = budgetRecette;
	}

	public DetailsFicheBesoinsDto(Integer id, ChapitreDto chapitre, RefStructureDto structure,
			ParagrapheDto paragraphe, RefEtablissementDto etablissement, FicheBesoinsDto ficheBesoins,
			ArticleDto article, BigDecimal montant, String description, boolean budgetRecette) {
		this.id = id;
		this.chapitre = chapitre;
		this.structure = structure;
		this.paragraphe = paragraphe;
		this.etablissement = etablissement;
		this.ficheBesoins = ficheBesoins;
		this.article = article;
		this.montant = montant;
		this.description = description;
		this.budgetRecette = budgetRecette;
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

	public ParagrapheDto getParagraphe() {
		return this.paragraphe;
	}

	public void setParagraphe(ParagrapheDto paragraphe) {
		this.paragraphe = paragraphe;
	}

	public RefEtablissementDto getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(RefEtablissementDto etablissement) {
		this.etablissement = etablissement;
	}

	public RefStructureDto getStructure() {
		return structure;
	}

	public void setStructure(RefStructureDto structure) {
		this.structure = structure;
	}

	public FicheBesoinsDto getFicheBesoins() {
		return this.ficheBesoins;
	}

	public void setFicheBesoins(FicheBesoinsDto ficheBesoins) {
		this.ficheBesoins = ficheBesoins;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isBudgetRecette() {
		return this.budgetRecette;
	}

	public void setBudgetRecette(boolean budgetRecette) {
		this.budgetRecette = budgetRecette;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (budgetRecette ? 1231 : 1237);
		result = prime * result + ((chapitre == null) ? 0 : chapitre.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((etablissement == null) ? 0 : etablissement.hashCode());
		result = prime * result + ((ficheBesoins == null) ? 0 : ficheBesoins.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((montant == null) ? 0 : montant.hashCode());
		result = prime * result + ((paragraphe == null) ? 0 : paragraphe.hashCode());
		result = prime * result + ((structure == null) ? 0 : structure.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailsFicheBesoinsDto other = (DetailsFicheBesoinsDto) obj;
		if (budgetRecette != other.budgetRecette)
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
		if (etablissement == null) {
			if (other.etablissement != null)
				return false;
		} else if (!etablissement.equals(other.etablissement))
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
		if (montant == null) {
			if (other.montant != null)
				return false;
		} else if (!montant.equals(other.montant))
			return false;
		if (paragraphe == null) {
			if (other.paragraphe != null)
				return false;
		} else if (!paragraphe.equals(other.paragraphe))
			return false;
		if (structure == null) {
			if (other.structure != null)
				return false;
		} else if (!structure.equals(other.structure))
			return false;
		return true;
	}
}
