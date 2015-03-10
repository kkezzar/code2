/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Action.java] 
 * @author rlaib on : 29 avr. 2014  08:54:09
 */
package dz.gov.mesrs.sii.commons.data.model.bpm;

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

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefCompte;



/**
 * @author rlaib  on : 29 avr. 2014  08:54:09
 */
@Entity
@Table(name = "situation_entite_occurrence", schema = "bpm")
public class SituationEntiteOccurrence implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:20:18
	 */
	private static final long serialVersionUID = -6848660247673722040L;
	private int id;
	private SituationEntite situationEntite;
	private int idOccurrence;
	private Date dateSituation; 
	private RefCompte refCompte;
	
	public SituationEntiteOccurrence() {
	}
	
	/**
	 * [Action.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="situation_entite_occurrence_id_seq_gen", sequenceName="bpm.situation_entite_occurrence_id_seq")
	@Id @GeneratedValue(generator="situation_entite_occurrence_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	/**
	 * [Action.id] Setter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	

	/**
	 * [SituationEntiteOccurrence.situationEntite] Getter 
	 * @author rlaib on : 5 mai 2014  17:43:18
	 * @return the situationEntite
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situation_entite", nullable = false)
	public SituationEntite getSituationEntite() {
		return situationEntite;
	}

	/**
	 * [SituationEntiteOccurrence.situationEntite] Setter 
	 * @author rlaib on : 5 mai 2014  17:43:18
	 * @param situationEntite the situationEntite to set
	 */
	public void setSituationEntite(SituationEntite situationEntite) {
		this.situationEntite = situationEntite;
	}

	/**
	 * [SituationEntiteOccurrence.idOccurrence] Getter 
	 * @author rlaib on : 29 avr. 2014  10:31:04
	 * @return the idOccurrence
	 */
	@Column(name = "id_occurrence")
	public int getIdOccurrence() {
		return idOccurrence;
	}

	/**
	 * [SituationEntiteOccurrence.idOccurrence] Setter 
	 * @author rlaib on : 29 avr. 2014  10:31:04
	 * @param idOccurrence the idOccurrence to set
	 */
	public void setIdOccurrence(int idOccurrence) {
		this.idOccurrence = idOccurrence;
	}

	/**
	 * [SituationEntiteOccurrence.date_situation] Getter 
	 * @author rlaib on : 29 avr. 2014  10:31:04
	 * @return the date_situation
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_situation", length = 29)
	public Date getDateSituation() {
		return dateSituation;
	}

	/**
	 * [SituationEntiteOccurrence.date_situation] Setter 
	 * @author rlaib on : 29 avr. 2014  10:31:04
	 * @param date_situation the date_situation to set
	 */
	public void setDateSituation(Date date_situation) {
		this.dateSituation = date_situation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creee_par")
	public RefCompte getRefCompte() {
		return refCompte;
	}

	public void setRefCompte(RefCompte refCompte) {
		this.refCompte = refCompte;
	}

	
	
}
