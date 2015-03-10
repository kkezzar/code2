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
import dz.gov.mesrs.sii.commons.data.model.gfc.Article;
import dz.gov.mesrs.sii.commons.data.model.gfc.Chapitre;

@Entity
@Table(name = "demande_credit", schema = "recherche")
public class DemandeCredit implements java.io.Serializable , Identifiable<Long>  {
	

	private static final long serialVersionUID = 1L;
	private Long id;
	private DemandeBudget demandeBudget;
	private Chapitre ncChapitre;
	private Article ncArticle;
	private short annee;	
	private Double montant;
	private String description;
	
	public DemandeCredit() {
		
	}

	/**
	 * [DemandeCredit.id] Getter 
	 * @author rlaib on : 1 févr. 2015  11:27:17
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="demande_credit_id_seq_gen", sequenceName="recherche.demande_credit_id_seq")
	@GeneratedValue(generator="demande_credit_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * [DemandeCredit.id] Setter 
	 * @author rlaib on : 1 févr. 2015  11:27:17
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [DemandeCredit.demandeBudget] Getter 
	 * @author rlaib on : 1 févr. 2015  11:27:17
	 * @return the demandeBudget
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_demande_budget", nullable = false)
	public DemandeBudget getDemandeBudget() {
		return demandeBudget;
	}

	/**
	 * [DemandeCredit.demandeBudget] Setter 
	 * @author rlaib on : 1 févr. 2015  11:27:17
	 * @param demandeBudget the demandeBudget to set
	 */
	public void setDemandeBudget(DemandeBudget demandeBudget) {
		this.demandeBudget = demandeBudget;
	}

	/**
	 * [DemandeCredit.ncChapitre] Getter 
	 * @author rlaib on : 1 févr. 2015  11:27:17
	 * @return the ncChapitre
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_chapitre", nullable = false)
	public Chapitre getNcChapitre() {
		return ncChapitre;
	}

	/**
	 * [DemandeCredit.ncChapitre] Setter 
	 * @author rlaib on : 1 févr. 2015  11:27:17
	 * @param ncChapitre the ncChapitre to set
	 */
	public void setNcChapitre(Chapitre ncChapitre) {
		this.ncChapitre = ncChapitre;
	}

	/**
	 * [DemandeCredit.ncArticle] Getter 
	 * @author rlaib on : 1 févr. 2015  11:27:17
	 * @return the ncArticle
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_article", nullable = false)
	public Article getNcArticle() {
		return ncArticle;
	}

	/**
	 * [DemandeCredit.ncArticle] Setter 
	 * @author rlaib on : 1 févr. 2015  11:27:17
	 * @param ncArticle the ncArticle to set
	 */
	public void setNcArticle(Article ncArticle) {
		this.ncArticle = ncArticle;
	}

	/**
	 * [DemandeCredit.annee] Getter 
	 * @author rlaib on : 1 févr. 2015  11:27:17
	 * @return the annee
	 */
	@Column(name = "annee",length=4, nullable=false)
	public short getAnnee() {
		return annee;
	}

	/**
	 * [DemandeCredit.annee] Setter 
	 * @author rlaib on : 1 févr. 2015  11:27:17
	 * @param annee the annee to set
	 */
	public void setAnnee(short annee) {
		this.annee = annee;
	}

	/**
	 * [DemandeCredit.montant] Getter 
	 * @author rlaib on : 1 févr. 2015  11:27:17
	 * @return the montant
	 */
	@Column(name = "montant",nullable=false)
	public Double getMontant() {
		return montant;
	}

	/**
	 * [DemandeCredit.montant] Setter 
	 * @author rlaib on : 1 févr. 2015  11:27:17
	 * @param montant the montant to set
	 */
	public void setMontant(Double montant) {
		this.montant = montant;
	}

	/**
	 * [DemandeCredit.description] Getter 
	 * @author rlaib on : 1 févr. 2015  11:27:17
	 * @return the description
	 */
	@Column(name = "description",length=1000)
	public String getDescription() {
		return description;
	}

	/**
	 * [DemandeCredit.description] Setter 
	 * @author rlaib on : 1 févr. 2015  11:27:17
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [Identifiable<Integer>.getIdenfiant] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  11:28:20
	 * @return
	 */
	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	/**
	 * [Identifiable<Integer>.getIdentifiantName] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  11:28:20
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	
}
