/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.scolarite.EnseignantVoeu.java] 
 * @author rlaib on : 12 oct. 2014  14:03:13
 */
package dz.gov.mesrs.sii.commons.data.model.fve.scolarite;

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

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;

/**
 * @author rlaib  on : 12 oct. 2014  14:03:13
 */
@Entity
@Table(name = "enseignant_service_fait", schema = "fve_scolarite")
public class EnseignantServiceFait implements java.io.Serializable  {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 oct. 2014  16:05:34
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private EnseignantChargePedagogique enseignantChargePedagogique;
	private SituationEntite situation;
	private TypeSeance typeSeance;
	private Date dateSeance;
	private Date heureDebutSeance;
	private Date heureFinSeance;
	private Double dureeSeance;
	private Integer nbEtudiantsPresents;
	private String motif;
	private String observation;
	private String justificatifMotif;
	
	public EnseignantServiceFait() {
		
	}

	/**
	 * [EnseignantServiceFait.id] Getter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="enseignant_service_fait_id_seq_gen", sequenceName="fve_scolarite.enseignant_service_fait_id_seq")
	@GeneratedValue(generator="enseignant_service_fait_id_seq_gen")
	public int getId() {
		return id;
	}

	/**
	 * [EnseignantServiceFait.id] Setter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EnseignantServiceFait.enseignantChargePedagogique] Getter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @return the enseignantChargePedagogique
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_charge_pedagogique", nullable = false)
	public EnseignantChargePedagogique getEnseignantChargePedagogique() {
		return enseignantChargePedagogique;
	}

	/**
	 * [EnseignantServiceFait.enseignantChargePedagogique] Setter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @param enseignantChargePedagogique the enseignantChargePedagogique to set
	 */
	public void setEnseignantChargePedagogique(
			EnseignantChargePedagogique enseignantChargePedagogique) {
		this.enseignantChargePedagogique = enseignantChargePedagogique;
	}

	/**
	 * [EnseignantServiceFait.situation] Getter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @return the situation
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituation() {
		return situation;
	}

	/**
	 * [EnseignantServiceFait.situation] Setter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @param situation the situation to set
	 */
	public void setSituation(SituationEntite situation) {
		this.situation = situation;
	}

	/**
	 * [EnseignantServiceFait.typeSeance] Getter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @return the typeSeance
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_seance", nullable = false)
	public TypeSeance getTypeSeance() {
		return typeSeance;
	}

	/**
	 * [EnseignantServiceFait.typeSeance] Setter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @param typeSeance the typeSeance to set
	 */
	public void setTypeSeance(TypeSeance typeSeance) {
		this.typeSeance = typeSeance;
	}

	/**
	 * [EnseignantServiceFait.dateSeance] Getter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @return the dateSeance
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_seance", nullable=false)
	public Date getDateSeance() {
		return dateSeance;
	}

	/**
	 * [EnseignantServiceFait.dateSeance] Setter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @param dateSeance the dateSeance to set
	 */
	public void setDateSeance(Date dateSeance) {
		this.dateSeance = dateSeance;
	}

	/**
	 * [EnseignantServiceFait.heureDebutSeance] Getter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @return the heureDebutSeance
	 */
	@Temporal(TemporalType.TIME)
	@Column(name = "heure_debut_seance")
	public Date getHeureDebutSeance() {
		return heureDebutSeance;
	}

	/**
	 * [EnseignantServiceFait.heureDebutSeance] Setter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @param heureDebutSeance the heureDebutSeance to set
	 */
	public void setHeureDebutSeance(Date heureDebutSeance) {
		this.heureDebutSeance = heureDebutSeance;
	}

	/**
	 * [EnseignantServiceFait.heureFinSeance] Getter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @return the heureFinSeance
	 */
	@Temporal(TemporalType.TIME)
	@Column(name = "heure_fin_seance")
	public Date getHeureFinSeance() {
		return heureFinSeance;
	}

	/**
	 * [EnseignantServiceFait.heureFinSeance] Setter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @param heureFinSeance the heureFinSeance to set
	 */
	public void setHeureFinSeance(Date heureFinSeance) {
		this.heureFinSeance = heureFinSeance;
	}

	/**
	 * [EnseignantServiceFait.dureeSeance] Getter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @return the dureeSeance
	 */
	@Column(name = "duree_seance")
	public Double getDureeSeance() {
		return dureeSeance;
	}

	/**
	 * [EnseignantServiceFait.dureeSeance] Setter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @param dureeSeance the dureeSeance to set
	 */
	public void setDureeSeance(Double dureeSeance) {
		this.dureeSeance = dureeSeance;
	}

	/**
	 * [EnseignantServiceFait.nbEtudiantsPresents] Getter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @return the nbEtudiantsPresents
	 */
	@Column(name = "nb_etudiants_presents")
	public Integer getNbEtudiantsPresents() {
		return nbEtudiantsPresents;
	}

	/**
	 * [EnseignantServiceFait.nbEtudiantsPresents] Setter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @param nbEtudiantsPresents the nbEtudiantsPresents to set
	 */
	public void setNbEtudiantsPresents(Integer nbEtudiantsPresents) {
		this.nbEtudiantsPresents = nbEtudiantsPresents;
	}

	/**
	 * [EnseignantServiceFait.motif] Getter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @return the motif
	 */
	@Column(name = "motif")
	public String getMotif() {
		return motif;
	}

	/**
	 * [EnseignantServiceFait.motif] Setter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}

	/**
	 * [EnseignantServiceFait.observation] Getter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @return the observation
	 */
	@Column(name = "observation")
	public String getObservation() {
		return observation;
	}

	/**
	 * [EnseignantServiceFait.observation] Setter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * [EnseignantServiceFait.justificatifMotif] Getter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @return the justificatifMotif
	 */
	@Column(name = "justificatif_motif")
	public String getJustificatifMotif() {
		return justificatifMotif;
	}

	/**
	 * [EnseignantServiceFait.justificatifMotif] Setter 
	 * @author rlaib on : 29 oct. 2014  11:54:02
	 * @param justificatifMotif the justificatifMotif to set
	 */
	public void setJustificatifMotif(String justificatifMotif) {
		this.justificatifMotif = justificatifMotif;
	}
}
