package dz.gov.mesrs.sii.commons.data.model.gfc;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEquipement;

/**
 * EquipementMarche generated by hbm2java
 */
@Entity
@Table(name = "equipement_marche", schema = "gfc")
public class EquipementMarche implements java.io.Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Marche marche;
	private RefEquipement equipement;
	private Integer quantite;
	private BigDecimal prixUnitaire;
	private Float tauxTva;
	private BigDecimal montantTtc;
	private List<DetailRealisationEquipement> detailRealisationEquipements = new ArrayList<DetailRealisationEquipement>();

	public EquipementMarche() {
	}

	public EquipementMarche(Integer id, RefEquipement equipement, Integer quantite, BigDecimal prixUnitaire,
			BigDecimal montantTtc) {
		this.id = id;
		this.equipement = equipement;
		this.quantite = quantite;
		this.prixUnitaire = prixUnitaire;
		this.montantTtc = montantTtc;
	}

	public EquipementMarche(Integer id, Marche marche, RefEquipement equipement, Integer quantite,
			BigDecimal prixUnitaire, Float tauxTva, BigDecimal montantTtc,
			List<DetailRealisationEquipement> detailRealisationEquipements) {
		this.id = id;
		this.marche = marche;
		this.equipement = equipement;
		this.quantite = quantite;
		this.prixUnitaire = prixUnitaire;
		this.tauxTva = tauxTva;
		this.montantTtc = montantTtc;
		this.detailRealisationEquipements = detailRealisationEquipements;
	}

	@SequenceGenerator(name = "equipement_marche_id_seq", sequenceName = "gfc.equipement_marche_id_seq")
	@Id
	@GeneratedValue(generator = "equipement_marche_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_marche", nullable = false)
	public Marche getMarche() {
		return this.marche;
	}

	public void setMarche(Marche marche) {
		this.marche = marche;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_equipement", nullable = false)
	public RefEquipement getEquipement() {
		return this.equipement;
	}

	public void setEquipement(RefEquipement equipement) {
		this.equipement = equipement;
	}

	@Column(name = "quantite", nullable = false)
	public Integer getQuantite() {
		return this.quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	@Column(name = "prix_unitaire", nullable = false, precision = 10)
	public BigDecimal getPrixUnitaire() {
		return this.prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	@Column(name = "taux_tva", precision = 10)
	public Float getTauxTva() {
		return this.tauxTva;
	}

	public void setTauxTva(Float tauxTva) {
		this.tauxTva = tauxTva;
	}

	@Column(name = "montant_ttc", nullable = false, precision = 10)
	public BigDecimal getMontantTtc() {
		return this.montantTtc;
	}

	public void setMontantTtc(BigDecimal montantTtc) {
		this.montantTtc = montantTtc;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipementMarche")
	public List<DetailRealisationEquipement> getDetailRealisationEquipements() {
		return this.detailRealisationEquipements;
	}

	public void setDetailRealisationEquipements(List<DetailRealisationEquipement> detailRealisationEquipements) {
		this.detailRealisationEquipements = detailRealisationEquipements;
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