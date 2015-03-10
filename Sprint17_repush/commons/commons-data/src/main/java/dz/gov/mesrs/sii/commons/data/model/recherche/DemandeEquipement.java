package dz.gov.mesrs.sii.commons.data.model.recherche;
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

@Entity
@Table(name = "demande_equipement", schema = "recherche")
public class DemandeEquipement implements java.io.Serializable , Identifiable<Long>  {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private DemandeBudget demandeBudget;
	private Nomenclature ncFamille;
	private Nomenclature ncSousFamille;
	private short annee;	
	private Integer quantite;	
	private Double montantEstime;
	
	public DemandeEquipement() {
		
	}

	/**
	 * [DemandeEquipement.id] Getter 
	 * @author rlaib on : 1 févr. 2015  11:35:48
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="demande_equipement_id_seq_gen", sequenceName="recherche.demande_equipement_id_seq")
	@GeneratedValue(generator="demande_equipement_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * [DemandeEquipement.id] Setter 
	 * @author rlaib on : 1 févr. 2015  11:35:48
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [DemandeEquipement.demandeBudget] Getter 
	 * @author rlaib on : 1 févr. 2015  11:35:48
	 * @return the demandeBudget
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_demande_budget", nullable = false)
	public DemandeBudget getDemandeBudget() {
		return demandeBudget;
	}

	/**
	 * [DemandeEquipement.demandeBudget] Setter 
	 * @author rlaib on : 1 févr. 2015  11:35:48
	 * @param demandeBudget the demandeBudget to set
	 */
	public void setDemandeBudget(DemandeBudget demandeBudget) {
		this.demandeBudget = demandeBudget;
	}

	/**
	 * [DemandeEquipement.ncFamille] Getter 
	 * @author rlaib on : 1 févr. 2015  11:35:48
	 * @return the ncFamille
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_famille_equipement", nullable = false)
	public Nomenclature getNcFamille() {
		return ncFamille;
	}

	/**
	 * [DemandeEquipement.ncFamille] Setter 
	 * @author rlaib on : 1 févr. 2015  11:35:48
	 * @param ncFamille the ncFamille to set
	 */
	public void setNcFamille(Nomenclature ncFamille) {
		this.ncFamille = ncFamille;
	}

	/**
	 * [DemandeEquipement.ncSousFamille] Getter 
	 * @author rlaib on : 1 févr. 2015  11:35:48
	 * @return the ncSousFamille
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sous_famille_equipement", nullable = false)
	public Nomenclature getNcSousFamille() {
		return ncSousFamille;
	}

	/**
	 * [DemandeEquipement.ncSousFamille] Setter 
	 * @author rlaib on : 1 févr. 2015  11:35:48
	 * @param ncSousFamille the ncSousFamille to set
	 */
	public void setNcSousFamille(Nomenclature ncSousFamille) {
		this.ncSousFamille = ncSousFamille;
	}

	/**
	 * [DemandeEquipement.annee] Getter 
	 * @author rlaib on : 1 févr. 2015  11:35:48
	 * @return the annee
	 */
	@Column(name = "annee",length=4, nullable=false)
	public short getAnnee() {
		return annee;
	}

	/**
	 * [DemandeEquipement.annee] Setter 
	 * @author rlaib on : 1 févr. 2015  11:35:48
	 * @param annee the annee to set
	 */
	public void setAnnee(short annee) {
		this.annee = annee;
	}

	/**
	 * [DemandeEquipement.quantite] Getter 
	 * @author rlaib on : 1 févr. 2015  11:35:48
	 * @return the quantite
	 */
	@Column(name = "quantite", nullable=false)
	public Integer getQuantite() {
		return quantite;
	}

	/**
	 * [DemandeEquipement.quantite] Setter 
	 * @author rlaib on : 1 févr. 2015  11:35:48
	 * @param quantite the quantite to set
	 */
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	/**
	 * [DemandeEquipement.montantEstime] Getter 
	 * @author rlaib on : 1 févr. 2015  11:35:48
	 * @return the montantEstime
	 */
	@Column(name = "montant_estime",nullable=false)
	public Double getMontantEstime() {
		return montantEstime;
	}

	/**
	 * [DemandeEquipement.montantEstime] Setter 
	 * @author rlaib on : 1 févr. 2015  11:35:48
	 * @param montantEstime the montantEstime to set
	 */
	public void setMontantEstime(Double montantEstime) {
		this.montantEstime = montantEstime;
	}

	/**
	 * [Identifiable<Integer>.getIdenfiant] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  11:36:02
	 * @return
	 */
	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	/**
	 * [Identifiable<Integer>.getIdentifiantName] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  11:36:02
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	
}
