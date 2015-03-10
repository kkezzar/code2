package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class RepartitionBudgetStructureDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private ArticleDto article;
	private ChapitreDto chapitre;
	private ExerciceBudgetaireDto exerciceBudgetaire;
	private RefEtablissementDto etablissement;
	private RefStructureDto structure;
	private BigDecimal montantInitial;
	private BigDecimal montantActuel;
	private String observation;

	private List<DetailsMouvementBudgetStructureDto> detailsMouvementBudgetStructureSource;
	private List<DetailsMouvementBudgetStructureDto> detailsMouvementBudgetStructureCible;

	public RepartitionBudgetStructureDto() {
	}

	public RepartitionBudgetStructureDto(int id, ArticleDto article, ChapitreDto chapitre,
			ExerciceBudgetaireDto exerciceBudgetaire, RefEtablissementDto etablissement, RefStructureDto structure,
			BigDecimal montantInitial, BigDecimal montantActuel) {
		this.id = id;
		this.article = article;
		this.chapitre = chapitre;
		this.exerciceBudgetaire = exerciceBudgetaire;
		this.etablissement = etablissement;
		this.structure = structure;
		this.montantInitial = montantInitial;
		this.montantActuel = montantActuel;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArticleDto getArticle() {
		return this.article;
	}

	public void setArticle(ArticleDto article) {
		this.article = article;
	}

	public ChapitreDto getChapitre() {
		return this.chapitre;
	}

	public void setChapitre(ChapitreDto chapitre) {
		this.chapitre = chapitre;
	}

	public ExerciceBudgetaireDto getExerciceBudgetaire() {
		return this.exerciceBudgetaire;
	}

	public void setExerciceBudgetaire(ExerciceBudgetaireDto exerciceBudgetaire) {
		this.exerciceBudgetaire = exerciceBudgetaire;
	}

	public RefEtablissementDto getEtablissement() {
		return this.etablissement;
	}

	public void setEtablissement(RefEtablissementDto etablissement) {
		this.etablissement = etablissement;
	}

	public RefStructureDto getStructure() {
		return this.structure;
	}

	public void setStructure(RefStructureDto structure) {
		this.structure = structure;
	}

	public BigDecimal getMontantInitial() {
		return this.montantInitial;
	}

	public void setMontantInitial(BigDecimal montantInitial) {
		this.montantInitial = montantInitial;
	}

	public BigDecimal getMontantActuel() {
		return this.montantActuel;
	}

	public void setMontantActuel(BigDecimal montantActuel) {
		this.montantActuel = montantActuel;
	}

	public String getObservation() {
		return this.observation;
	}

	public List<DetailsMouvementBudgetStructureDto> getDetailsMouvementBudgetStructureSource() {
		return detailsMouvementBudgetStructureSource;
	}

	public void setDetailsMouvementBudgetStructureSource(
			List<DetailsMouvementBudgetStructureDto> detailsMouvementBudgetStructureSource) {
		this.detailsMouvementBudgetStructureSource = detailsMouvementBudgetStructureSource;
	}

	public List<DetailsMouvementBudgetStructureDto> getDetailsMouvementBudgetStructureCible() {
		return detailsMouvementBudgetStructureCible;
	}

	public void setDetailsMouvementBudgetStructureCible(
			List<DetailsMouvementBudgetStructureDto> detailsMouvementBudgetStructureCible) {
		this.detailsMouvementBudgetStructureCible = detailsMouvementBudgetStructureCible;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Override
	public String toString() {
		return "RepartitionBudgetStructureDto [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + ((chapitre == null) ? 0 : chapitre.hashCode());
		result = prime
				* result
				+ ((detailsMouvementBudgetStructureCible == null) ? 0 : detailsMouvementBudgetStructureCible.hashCode());
		result = prime
				* result
				+ ((detailsMouvementBudgetStructureSource == null) ? 0 : detailsMouvementBudgetStructureSource
						.hashCode());
		result = prime * result + ((etablissement == null) ? 0 : etablissement.hashCode());
		result = prime * result + ((exerciceBudgetaire == null) ? 0 : exerciceBudgetaire.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((montantActuel == null) ? 0 : montantActuel.hashCode());
		result = prime * result + ((montantInitial == null) ? 0 : montantInitial.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((structure == null) ? 0 : structure.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		RepartitionBudgetDto other = (RepartitionBudgetDto) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		}
		if (!id.equals(other.getId()))
			return false;

		return true;
	}
}
