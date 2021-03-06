package dz.gov.mesrs.sii.commons.data.model.gfc;

// Generated 25 févr. 2015 10:56:34 by Mounir MESSAOUDI Hibernate Tools 4.0.0

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * DetailsMouvementBudgetStructure generated by hbm2java
 */
@Entity
@Table(name = "details_mouvement_budget_structure", schema = "gfc")
public class DetailsMouvementBudgetStructure implements java.io.Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Nomenclature typeMouvement;

	private RepartitionBudgetStructure repartitionBudgetStructureSource;
	private RepartitionBudgetStructure repartitionBudgetStructureCible;
	private BigDecimal montant;
	private String observation;

	public DetailsMouvementBudgetStructure() {
	}

	public DetailsMouvementBudgetStructure(Integer id, BigDecimal montant) {
		this.id = id;
		this.montant = montant;
	}

	public DetailsMouvementBudgetStructure(Integer id, RepartitionBudgetStructure repartitionBudgetStructureSource,
			RepartitionBudgetStructure repartitionBudgetStructureCible, BigDecimal montant, String observation,
			Nomenclature typeMouvement) {
		this.id = id;
		this.repartitionBudgetStructureSource = repartitionBudgetStructureSource;
		this.repartitionBudgetStructureCible = repartitionBudgetStructureCible;
		this.montant = montant;
		this.observation = observation;
		this.typeMouvement = typeMouvement;
	}

	@SequenceGenerator(name = "details_mouvement_budget_structure_id_seq", sequenceName = "gfc.details_mouvement_budget_structure_id_seq")
	@Id
	@GeneratedValue(generator = "details_mouvement_budget_structure_id_seq")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_details_repartition_budget_structure_source")
	public RepartitionBudgetStructure getRepartitionBudgetStructureSource() {
		return this.repartitionBudgetStructureSource;
	}

	public void setRepartitionBudgetStructureSource(RepartitionBudgetStructure repartitionBudgetStructureSource) {
		this.repartitionBudgetStructureSource = repartitionBudgetStructureSource;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_details_repartition_budget_structure_cible")
	public RepartitionBudgetStructure getRepartitionBudgetStructureCible() {
		return this.repartitionBudgetStructureCible;
	}

	public void setRepartitionBudgetStructureCible(RepartitionBudgetStructure repartitionBudgetStructureCible) {
		this.repartitionBudgetStructureCible = repartitionBudgetStructureCible;
	}

	@Column(name = "montant", nullable = false, precision = 10)
	public BigDecimal getMontant() {
		return this.montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	@Column(name = "observation", length = 100)
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_mouvement", nullable = false)
	public Nomenclature getTypeMouvement() {
		return this.typeMouvement;
	}

	public void setTypeMouvement(Nomenclature typeMouvement) {
		this.typeMouvement = typeMouvement;
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