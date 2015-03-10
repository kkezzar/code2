/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Demande.java] 
 * @author rlaib on : 29 avr. 2014  09:12:22
 */
package dz.gov.mesrs.sii.commons.data.model.bpm;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author rlaib  on : 29 avr. 2014  09:12:22
 */
@Entity
@Table(name = "tache", schema = "bpm")
public class Tache implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:20:36
	 */
	private static final long serialVersionUID = 5652463153600919290L;
	private int id;
	private EtapeRole etapeRole;
	private SituationEntite situationEntite;
	private Date dateCreation;
	private Date dateCloture;
	private int idOffreFormation;
	private int idDemande;
	private int idDecision;
	
	
	public Tache() {
	}

	/**
	 * [Tache.id] Getter 
	 * @author rlaib on : 29 avr. 2014  10:57:20
	 * @return the id
	 */
	@SequenceGenerator(name="tache_id_seq_gen", sequenceName="bpm.tache_id_seq")
	@Id @GeneratedValue(generator="tache_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * [Tache.id] Setter 
	 * @author rlaib on : 29 avr. 2014  10:57:20
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [Tache.etapeRole] Getter 
	 * @author rlaib on : 29 avr. 2014  12:27:47
	 * @return the etapeRole
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_etape_role", nullable = false)
	public EtapeRole getEtapeRole() {
		return etapeRole;
	}

	/**
	 * [Tache.etapeRole] Setter 
	 * @author rlaib on : 29 avr. 2014  12:27:47
	 * @param etapeRole the etapeRole to set
	 */
	public void setEtapeRole(EtapeRole etapeRole) {
		this.etapeRole = etapeRole;
	}

	/**
	 * [Tache.situationEntite] Getter 
	 * @author rlaib on : 29 avr. 2014  12:27:47
	 * @return the situationEntite
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituationEntite() {
		return situationEntite;
	}

	/**
	 * [Tache.situationEntite] Setter 
	 * @author rlaib on : 29 avr. 2014  12:27:47
	 * @param situationEntite the situationEntite to set
	 */
	public void setSituationEntite(SituationEntite situationEntite) {
		this.situationEntite = situationEntite;
	}
	
	/**
	 * [Tache.dateCreation] Getter 
	 * @author rlaib on : 29 avr. 2014  12:27:47
	 * @return the dateCreation
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_creation", length = 29)
	public Date getDateCreation() {
		return dateCreation;
	}

	
	
	/**
	 * [Tache.dateCreation] Setter 
	 * @author rlaib on : 29 avr. 2014  12:27:47
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * [Tache.dateCloture] Getter 
	 * @author rlaib on : 29 avr. 2014  12:27:47
	 * @return the dateCloture
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_cloture", length = 29)
	public Date getDateCloture() {
		return dateCloture;
	}

	/**
	 * [Tache.dateCloture] Setter 
	 * @author rlaib on : 29 avr. 2014  12:27:47
	 * @param dateCloture the dateCloture to set
	 */
	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	/**
	 * [Tache.idOffreFormation] Getter 
	 * @author rlaib on : 29 avr. 2014  12:27:47
	 * @return the idOffreFormation
	 */
	@Column(name = "id_offre_formation")
	public int getIdOffreFormation() {
		return idOffreFormation;
	}

	/**
	 * [Tache.idOffreFormation] Setter 
	 * @author rlaib on : 29 avr. 2014  12:27:47
	 * @param idOffreFormation the idOffreFormation to set
	 */
	public void setIdOffreFormation(int idOffreFormation) {
		this.idOffreFormation = idOffreFormation;
	}

	/**
	 * [Tache.idDemande] Getter 
	 * @author rlaib on : 29 avr. 2014  12:27:47
	 * @return the idDemande
	 */
	@Column(name = "id_demande")
	public int getIdDemande() {
		return idDemande;
	}

	/**
	 * [Tache.idDemande] Setter 
	 * @author rlaib on : 29 avr. 2014  12:27:47
	 * @param idDemande the idDemande to set
	 */
	public void setIdDemande(int idDemande) {
		this.idDemande = idDemande;
	}

	/**
	 * [Tache.idDecision] Getter 
	 * @author rlaib on : 29 avr. 2014  12:27:47
	 * @return the idDecision
	 */
	@Column(name = "id_decision")
	public int getIdDecision() {
		return idDecision;
	}

	/**
	 * [Tache.idDecision] Setter 
	 * @author rlaib on : 29 avr. 2014  12:27:47
	 * @param idDecision the idDecision to set
	 */
	public void setIdDecision(int idDecision) {
		this.idDecision = idDecision;
	}

	
	
}
