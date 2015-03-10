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
@Table(name = "activite_budget", schema = "recherche")
public class ActiviteBudget implements java.io.Serializable , Identifiable<Long>  {
	

	private static final long serialVersionUID = 1L;
	private Long id;
	private DemandeBudget demandeBudget;
	private Groupe groupe;
	private short annee;	
	private String description;
	private Nomenclature ncActivite;

	
	public ActiviteBudget() {
		
	}

	/**
	 * [ActiviteBudget.id] Getter 
	 * @author rlaib on : 1 févr. 2015  11:19:40
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="demande_activite_id_seq_gen", sequenceName="recherche.demande_activite_id_seq")
	@GeneratedValue(generator="demande_activite_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * [ActiviteBudget.id] Setter 
	 * @author rlaib on : 1 févr. 2015  11:19:40
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [ActiviteBudget.demandeBudget] Getter 
	 * @author rlaib on : 1 févr. 2015  11:19:40
	 * @return the demandeBudget
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_demande_budget", nullable = false)
	public DemandeBudget getDemandeBudget() {
		return demandeBudget;
	}

	/**
	 * [ActiviteBudget.demandeBudget] Setter 
	 * @author rlaib on : 1 févr. 2015  11:19:40
	 * @param demandeBudget the demandeBudget to set
	 */
	public void setDemandeBudget(DemandeBudget demandeBudget) {
		this.demandeBudget = demandeBudget;
	}

	/**
	 * [ActiviteBudget.groupe] Getter 
	 * @author rlaib on : 1 févr. 2015  11:19:40
	 * @return the groupe
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_groupe", nullable = false)
	public Groupe getGroupe() {
		return groupe;
	}

	/**
	 * [ActiviteBudget.groupe] Setter 
	 * @author rlaib on : 1 févr. 2015  11:19:40
	 * @param groupe the groupe to set
	 */
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	/**
	 * [ActiviteBudget.annee] Getter 
	 * @author rlaib on : 1 févr. 2015  11:19:40
	 * @return the annee
	 */
	@Column(name = "annee",length=4, nullable=false)
	public short getAnnee() {
		return annee;
	}

	/**
	 * [ActiviteBudget.annee] Setter 
	 * @author rlaib on : 1 févr. 2015  11:19:40
	 * @param annee the annee to set
	 */
	public void setAnnee(short annee) {
		this.annee = annee;
	}

	/**
	 * [ActiviteBudget.description] Getter 
	 * @author rlaib on : 1 févr. 2015  11:19:40
	 * @return the description
	 */
	@Column(name = "description",length=1000)
	public String getDescription() {
		return description;
	}

	/**
	 * [ActiviteBudget.description] Setter 
	 * @author rlaib on : 1 févr. 2015  11:19:40
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [ActiviteBudget.ncActivite] Getter 
	 * @author rlaib on : 1 févr. 2015  15:31:05
	 * @return the ncActivite
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_activite", nullable = false)
	public Nomenclature getNcActivite() {
		return ncActivite;
	}

	/**
	 * [ActiviteBudget.ncActivite] Setter 
	 * @author rlaib on : 1 févr. 2015  15:31:05
	 * @param ncActivite the ncActivite to set
	 */
	public void setNcActivite(Nomenclature ncActivite) {
		this.ncActivite = ncActivite;
	}

	/**
	 * [Identifiable<Integer>.getIdenfiant] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  11:19:44
	 * @return
	 */
	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	/**
	 * [Identifiable<Integer>.getIdentifiantName] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  11:19:44
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	
	
	
}
