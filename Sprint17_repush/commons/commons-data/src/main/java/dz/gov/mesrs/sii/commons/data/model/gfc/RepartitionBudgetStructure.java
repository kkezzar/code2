package dz.gov.mesrs.sii.commons.data.model.gfc;

// Generated 25 févr. 2015 10:56:34 by Mounir MESSAOUDI Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;

/**
 * RepartitionBudgetStructure generated by hbm2java
 */
@Entity
@Table(name = "repartition_budget_structure", schema = "gfc")
public class RepartitionBudgetStructure implements java.io.Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Article article;
	private Chapitre chapitre;
	private ExerciceBudgetaire exerciceBudgetaire;
	private RefEtablissement etablissement;
	private RefStructure structure;
	private BigDecimal montantInitial;
	private BigDecimal montantActuel;
	private String observation;

	private List<DetailsMouvementBudgetStructure> detailsMouvementBudgetStructureSource;
	private List<DetailsMouvementBudgetStructure> detailsMouvementBudgetStructureCible;

	public RepartitionBudgetStructure() {
	}

	public RepartitionBudgetStructure(int id, Article article, Chapitre chapitre,
			ExerciceBudgetaire exerciceBudgetaire, RefEtablissement etablissement, RefStructure structure,
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

	@SequenceGenerator(name = "repartition_budget_structure_id_seq", sequenceName = "gfc.repartition_budget_structure_id_seq")
	@Id
	@GeneratedValue(generator = "repartition_budget_structure_id_seq")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_article", nullable = false)
	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_chapitre", nullable = false)
	public Chapitre getChapitre() {
		return this.chapitre;
	}

	public void setChapitre(Chapitre chapitre) {
		this.chapitre = chapitre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_exercice_budgetaire", nullable = false)
	public ExerciceBudgetaire getExerciceBudgetaire() {
		return this.exerciceBudgetaire;
	}

	public void setExerciceBudgetaire(ExerciceBudgetaire exerciceBudgetaire) {
		this.exerciceBudgetaire = exerciceBudgetaire;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_etablissement", nullable = false)
	public RefEtablissement getEtablissement() {
		return this.etablissement;
	}

	public void setEtablissement(RefEtablissement etablissement) {
		this.etablissement = etablissement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_structure", nullable = false)
	public RefStructure getStructure() {
		return this.structure;
	}

	public void setStructure(RefStructure structure) {
		this.structure = structure;
	}

	@Column(name = "montant_initial", nullable = false, precision = 10)
	public BigDecimal getMontantInitial() {
		return this.montantInitial;
	}

	public void setMontantInitial(BigDecimal montantInitial) {
		this.montantInitial = montantInitial;
	}

	@Column(name = "montant_actuel", nullable = false, precision = 10)
	public BigDecimal getMontantActuel() {
		return this.montantActuel;
	}

	public void setMontantActuel(BigDecimal montantActuel) {
		this.montantActuel = montantActuel;
	}

	@Column(name = "observation", length = 200)
	public String getObservation() {
		return this.observation;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "repartitionBudgetStructureSource")
	public List<DetailsMouvementBudgetStructure> getDetailsMouvementBudgetStructureSource() {
		return detailsMouvementBudgetStructureSource;
	}

	public void setDetailsMouvementBudgetStructureSource(
			List<DetailsMouvementBudgetStructure> detailsMouvementBudgetStructureSource) {
		this.detailsMouvementBudgetStructureSource = detailsMouvementBudgetStructureSource;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "repartitionBudgetStructureCible")
	public List<DetailsMouvementBudgetStructure> getDetailsMouvementBudgetStructureCible() {
		return detailsMouvementBudgetStructureCible;
	}

	public void setDetailsMouvementBudgetStructureCible(
			List<DetailsMouvementBudgetStructure> detailsMouvementBudgetStructureCible) {
		this.detailsMouvementBudgetStructureCible = detailsMouvementBudgetStructureCible;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Transient
	@Override
	public Integer getIdenfiant() {
		return id;
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}