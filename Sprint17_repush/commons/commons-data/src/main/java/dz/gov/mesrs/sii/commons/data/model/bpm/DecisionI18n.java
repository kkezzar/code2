/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Action.java] 
 * @author rlaib on : 29 avr. 2014  08:54:09
 */
package dz.gov.mesrs.sii.commons.data.model.bpm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author rlaib  on : 29 avr. 2014  08:54:09
 */
@Entity
@Table(name = "decision_i18n", schema = "bpm")
public class DecisionI18n implements java.io.Serializable {
	
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:17:58
	 */
	private static final long serialVersionUID = 1410863380617767704L;
	private int id;
	private String langue;
	private String titre;
	private String message;
	private String motif;
	private Decision decision;

	public DecisionI18n() {
	}
	/**
	 * [Action.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="decision_i18n_id_seq_gen", sequenceName="bpm.decision_i18n_id_seq")
	@Id @GeneratedValue(generator="decision_i18n_id_seq_gen")
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
	 * [DecisionI18n.langue] Getter 
	 * @author rlaib on : 29 avr. 2014  11:32:35
	 * @return the langue
	 */
	@Column(name = "langue", nullable = false,length=5)
	public String getLangue() {
		return langue;
	}
	/**
	 * [DecisionI18n.langue] Setter 
	 * @author rlaib on : 29 avr. 2014  11:32:35
	 * @param langue the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}
	/**
	 * [DecisionI18n.titre] Getter 
	 * @author rlaib on : 29 avr. 2014  11:32:35
	 * @return the titre
	 */
	@Column(name = "titre", length=250)
	public String getTitre() {
		return titre;
	}
	/**
	 * [DecisionI18n.titre] Setter 
	 * @author rlaib on : 29 avr. 2014  11:32:35
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}
	/**
	 * [DecisionI18n.message] Getter 
	 * @author rlaib on : 29 avr. 2014  11:32:35
	 * @return the message
	 */
	@Column(name = "message",length=550)
	public String getMessage() {
		return message;
	}
	/**
	 * [DecisionI18n.message] Setter 
	 * @author rlaib on : 29 avr. 2014  11:32:35
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * [DecisionI18n.motif] Getter 
	 * @author rlaib on : 29 avr. 2014  11:32:35
	 * @return the motif
	 */
	@Column(name = "motif",length=550)
	public String getMotif() {
		return motif;
	}
	
	/**
	 * [DecisionI18n.motif] Setter 
	 * @author rlaib on : 29 avr. 2014  11:32:35
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}
	
	/**
	 * [DecisionI18n.decision] Getter 
	 * @author rlaib on : 29 avr. 2014  11:32:35
	 * @return the decision
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_decision", nullable = false)
	public Decision getDecision() {
		return decision;
	}
	
	/**
	 * [DecisionI18n.decision] Setter 
	 * @author rlaib on : 29 avr. 2014  11:32:35
	 * @param decision the decision to set
	 */
	public void setDecision(Decision decision) {
		this.decision = decision;
	}
	
	
}
