package dz.gov.mesrs.sii.commons.data.model.recherche;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;

@Entity
@Table(name = "demande_budget", schema = "recherche")
public class DemandeBudget implements java.io.Serializable , Identifiable<Long>  {
	

	private static final long serialVersionUID = 1L;
	private Long id;
	private Programme programme;
	private SituationEntite situationEntite;
	private Date dateDemande;
	private short anneeDebut;
	private short anneeFin;
	private String description;
	private String connaissance;
	private String information;
	private String reseauRecherche;
	private String objectif;
	private String observation;
		
	public DemandeBudget() {
		
	}

	/**
	 * [DemandeBudget.id] Getter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="demande_budget_id_seq_gen", sequenceName="recherche.demande_budget_id_seq")
	@GeneratedValue(generator="demande_budget_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * [DemandeBudget.id] Setter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [DemandeBudget.programme] Getter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @return the programme
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_programme", nullable = false)
	public Programme getProgramme() {
		return programme;
	}

	/**
	 * [DemandeBudget.programme] Setter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @param programme the programme to set
	 */
	public void setProgramme(Programme programme) {
		this.programme = programme;
	}

	/**
	 * [DemandeBudget.situationEntite] Getter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @return the situationEntite
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituationEntite() {
		return situationEntite;
	}

	/**
	 * [DemandeBudget.situationEntite] Setter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @param situationEntite the situationEntite to set
	 */
	public void setSituationEntite(SituationEntite situationEntite) {
		this.situationEntite = situationEntite;
	}

	/**
	 * [DemandeBudget.dateDemande] Getter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @return the dateDemande
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_demande", nullable=false)
	public Date getDateDemande() {
		return dateDemande;
	}

	/**
	 * [DemandeBudget.dateDemande] Setter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @param dateDemande the dateDemande to set
	 */
	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	/**
	 * [DemandeBudget.anneeDebut] Getter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @return the anneeDebut
	 */
	@Column(name = "annee_debut",length=4, nullable=false)
	public short getAnneeDebut() {
		return anneeDebut;
	}

	/**
	 * [DemandeBudget.anneeDebut] Setter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @param anneeDebut the anneeDebut to set
	 */
	public void setAnneeDebut(short anneeDebut) {
		this.anneeDebut = anneeDebut;
	}

	/**
	 * [DemandeBudget.anneeFin] Getter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @return the anneeFin
	 */
	@Column(name = "annee_fin",length=4, nullable=false)
	public short getAnneeFin() {
		return anneeFin;
	}

	/**
	 * [DemandeBudget.anneeFin] Setter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @param anneeFin the anneeFin to set
	 */
	public void setAnneeFin(short anneeFin) {
		this.anneeFin = anneeFin;
	}

	/**
	 * [DemandeBudget.description] Getter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @return the description
	 */
	@Column(name = "description",length=1000)
	public String getDescription() {
		return description;
	}

	/**
	 * [DemandeBudget.description] Setter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [DemandeBudget.connaissance] Getter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @return the connaissance
	 */
	@Column(name = "connaissance",length=1000)
	public String getConnaissance() {
		return connaissance;
	}

	/**
	 * [DemandeBudget.connaissance] Setter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @param connaissance the connaissance to set
	 */
	public void setConnaissance(String connaissance) {
		this.connaissance = connaissance;
	}

	/**
	 * [DemandeBudget.information] Getter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @return the information
	 */
	@Column(name = "information",length=1000)
	public String getInformation() {
		return information;
	}

	/**
	 * [DemandeBudget.information] Setter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @param information the information to set
	 */
	public void setInformation(String information) {
		this.information = information;
	}

	/**
	 * [DemandeBudget.reseauRecherche] Getter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @return the reseauRecherche
	 */
	@Column(name = "reseau_recherche",length=1000)
	public String getReseauRecherche() {
		return reseauRecherche;
	}

	/**
	 * [DemandeBudget.reseauRecherche] Setter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @param reseauRecherche the reseauRecherche to set
	 */
	public void setReseauRecherche(String reseauRecherche) {
		this.reseauRecherche = reseauRecherche;
	}

	/**
	 * [DemandeBudget.objectif] Getter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @return the objectif
	 */
	@Column(name = "objectif",length=1000)
	public String getObjectif() {
		return objectif;
	}

	/**
	 * [DemandeBudget.objectif] Setter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @param objectif the objectif to set
	 */
	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	/**
	 * [DemandeBudget.observation] Getter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @return the observation
	 */
	@Column(name = "observation",length=1000)
	public String getObservation() {
		return observation;
	}

	/**
	 * [DemandeBudget.observation] Setter 
	 * @author rlaib on : 29 janv. 2015  14:57:47
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * [Identifiable<Integer>.getIdenfiant] Method 
	 * Overridden By : @author rlaib  on : 29 janv. 2015  14:59:29
	 * @return
	 */
	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	/**
	 * [Identifiable<Integer>.getIdentifiantName] Method 
	 * Overridden By : @author rlaib  on : 29 janv. 2015  14:59:29
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	
}
