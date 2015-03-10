package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class DetailEngagementMarcheDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private ArticleDto article;
	private ChapitreDto chapitre;
	private RefStructureDto structure;
	private EngagementMarcheDto engagementMarche;
	private BigDecimal montant;
	private String observation;
	private SituationEntiteDto situation;

	public DetailEngagementMarcheDto() {
		structure = new RefStructureDto();
		chapitre = new ChapitreDto();
		article = new ArticleDto();
	}

	public DetailEngagementMarcheDto(Integer id, ArticleDto article, ChapitreDto chapitre,
			EngagementMarcheDto engagementMarche, BigDecimal montant) {
		this.id = id;
		this.article = article;
		this.chapitre = chapitre;
		this.engagementMarche = engagementMarche;
		this.montant = montant;
	}

	public DetailEngagementMarcheDto(Integer id, ArticleDto article, ChapitreDto chapitre,
			EngagementMarcheDto engagementMarche, BigDecimal montant, String observation) {
		this.id = id;
		this.article = article;
		this.chapitre = chapitre;
		this.engagementMarche = engagementMarche;
		this.montant = montant;
		this.observation = observation;
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

	public ChapitreDto getChapitre() {
		return this.chapitre;
	}

	public void setChapitre(ChapitreDto chapitre) {
		this.chapitre = chapitre;
	}

	public EngagementMarcheDto getEngagementMarche() {
		return this.engagementMarche;
	}

	public RefStructureDto getStructure() {
		return structure;
	}

	public void setStructure(RefStructureDto structure) {
		this.structure = structure;
	}

	public void setEngagementMarche(EngagementMarcheDto engagementMarche) {
		this.engagementMarche = engagementMarche;
	}

	public BigDecimal getMontant() {
		return this.montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public SituationEntiteDto getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntiteDto situation) {
		this.situation = situation;
	}
}