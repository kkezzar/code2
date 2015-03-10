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
@Table(name = "demande_operation", schema = "recherche")
public class DemandeOperation implements java.io.Serializable , Identifiable<Long>  {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private DemandeBudget demandeBudget;
	private Nomenclature ncOperation;
	private short annee;	
	private Double montantEstime;
	
	public DemandeOperation() {
		
	}

	/**
	 * [DemandeOperation.id] Getter 
	 * @author rlaib on : 1 févr. 2015  11:40:24
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="demande_operation_id_seq_gen", sequenceName="recherche.demande_operation_id_seq")
	@GeneratedValue(generator="demande_operation_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * [DemandeOperation.id] Setter 
	 * @author rlaib on : 1 févr. 2015  11:40:24
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [DemandeOperation.demandeBudget] Getter 
	 * @author rlaib on : 1 févr. 2015  11:40:24
	 * @return the demandeBudget
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_demande_budget", nullable = false)
	public DemandeBudget getDemandeBudget() {
		return demandeBudget;
	}

	/**
	 * [DemandeOperation.demandeBudget] Setter 
	 * @author rlaib on : 1 févr. 2015  11:40:24
	 * @param demandeBudget the demandeBudget to set
	 */
	public void setDemandeBudget(DemandeBudget demandeBudget) {
		this.demandeBudget = demandeBudget;
	}

	/**
	 * [DemandeOperation.ncOperation] Getter 
	 * @author rlaib on : 1 févr. 2015  11:40:24
	 * @return the ncOperation
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_operation", nullable = false)
	public Nomenclature getNcOperation() {
		return ncOperation;
	}

	/**
	 * [DemandeOperation.ncOperation] Setter 
	 * @author rlaib on : 1 févr. 2015  11:40:24
	 * @param ncOperation the ncOperation to set
	 */
	public void setNcOperation(Nomenclature ncOperation) {
		this.ncOperation = ncOperation;
	}

	/**
	 * [DemandeOperation.annee] Getter 
	 * @author rlaib on : 1 févr. 2015  11:40:24
	 * @return the annee
	 */
	@Column(name = "annee",length=4, nullable=false)
	public short getAnnee() {
		return annee;
	}

	/**
	 * [DemandeOperation.annee] Setter 
	 * @author rlaib on : 1 févr. 2015  11:40:24
	 * @param annee the annee to set
	 */
	public void setAnnee(short annee) {
		this.annee = annee;
	}

	/**
	 * [DemandeOperation.montantEstime] Getter 
	 * @author rlaib on : 1 févr. 2015  11:40:24
	 * @return the montantEstime
	 */
	@Column(name = "montant_estime",nullable=false)
	public Double getMontantEstime() {
		return montantEstime;
	}

	/**
	 * [DemandeOperation.montantEstime] Setter 
	 * @author rlaib on : 1 févr. 2015  11:40:24
	 * @param montantEstime the montantEstime to set
	 */
	public void setMontantEstime(Double montantEstime) {
		this.montantEstime = montantEstime;
	}

	/**
	 * [Identifiable<Integer>.getIdenfiant] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  11:40:29
	 * @return
	 */
	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	/**
	 * [Identifiable<Integer>.getIdentifiantName] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  11:40:29
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	
	
}
