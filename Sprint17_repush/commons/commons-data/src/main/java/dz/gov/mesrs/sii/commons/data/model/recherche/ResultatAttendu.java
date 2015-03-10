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
@Table(name = "resultat_attendu", schema = "recherche")
public class ResultatAttendu implements java.io.Serializable , Identifiable<Long>  {
	

	private static final long serialVersionUID = 1L;
	private Long id;
	private DemandeBudget demandeBudget;
	private Nomenclature ncResultat;
	private short annee;	
	private String description;
	private Integer quantite;	
	
	public ResultatAttendu() {
		
	}

	/**
	 * [ResultatAttendu.id] Getter 
	 * @author rlaib on : 4 févr. 2015  10:12:18
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="demande_resultat_id_seq_gen", sequenceName="recherche.demande_resultat_id_seq")
	@GeneratedValue(generator="demande_resultat_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * [ResultatAttendu.id] Setter 
	 * @author rlaib on : 4 févr. 2015  10:12:18
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [ResultatAttendu.demandeBudget] Getter 
	 * @author rlaib on : 4 févr. 2015  10:12:18
	 * @return the demandeBudget
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_demande_budget", nullable = false)
	public DemandeBudget getDemandeBudget() {
		return demandeBudget;
	}

	/**
	 * [ResultatAttendu.demandeBudget] Setter 
	 * @author rlaib on : 4 févr. 2015  10:12:18
	 * @param demandeBudget the demandeBudget to set
	 */
	public void setDemandeBudget(DemandeBudget demandeBudget) {
		this.demandeBudget = demandeBudget;
	}

	/**
	 * [ResultatAttendu.ncResultat] Getter 
	 * @author rlaib on : 4 févr. 2015  10:12:18
	 * @return the ncResultat
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_resultat", nullable = false)
	public Nomenclature getNcResultat() {
		return ncResultat;
	}

	/**
	 * [ResultatAttendu.ncResultat] Setter 
	 * @author rlaib on : 4 févr. 2015  10:12:18
	 * @param ncResultat the ncResultat to set
	 */
	public void setNcResultat(Nomenclature ncResultat) {
		this.ncResultat = ncResultat;
	}

	/**
	 * [ResultatAttendu.annee] Getter 
	 * @author rlaib on : 4 févr. 2015  10:12:18
	 * @return the annee
	 */
	@Column(name = "annee",length=4, nullable=false)
	public short getAnnee() {
		return annee;
	}

	/**
	 * [ResultatAttendu.annee] Setter 
	 * @author rlaib on : 4 févr. 2015  10:12:18
	 * @param annee the annee to set
	 */
	public void setAnnee(short annee) {
		this.annee = annee;
	}

	/**
	 * [ResultatAttendu.description] Getter 
	 * @author rlaib on : 4 févr. 2015  10:12:18
	 * @return the description
	 */
	@Column(name = "observation",length=1000)
	public String getDescription() {
		return description;
	}

	/**
	 * [ResultatAttendu.description] Setter 
	 * @author rlaib on : 4 févr. 2015  10:12:18
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	/**
	 * [ResultatAttendu.quantite] Getter 
	 * @author rlaib on : 4 févr. 2015  10:16:01
	 * @return the quantite
	 */
	@Column(name = "quantite", nullable=false)
	public Integer getQuantite() {
		return quantite;
	}

	/**
	 * [ResultatAttendu.quantite] Setter 
	 * @author rlaib on : 4 févr. 2015  10:16:01
	 * @param quantite the quantite to set
	 */
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	/**
	 * [Identifiable<Long>.getIdenfiant] Method 
	 * Overridden By : @author rlaib  on : 4 févr. 2015  10:13:03
	 * @return
	 */
	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	/**
	 * [Identifiable<Long>.getIdentifiantName] Method 
	 * Overridden By : @author rlaib  on : 4 févr. 2015  10:13:03
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	
	
}
