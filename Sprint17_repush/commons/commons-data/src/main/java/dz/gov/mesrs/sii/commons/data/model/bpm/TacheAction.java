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



/**
 * @author rlaib  on : 29 avr. 2014  08:54:09
 */
@Entity
@Table(name = "tache_action", schema = "bpm")
public class TacheAction implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:18:52
	 */
	private static final long serialVersionUID = 2433201654261576607L;
	private int id;
	private Tache tache;
	private Action action;
	private String utilisateur;
	private String commentaire;
	private Date dateAction;
	
	public TacheAction() {
	}
	
	/**
	 * [TacheAction.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="tache_action_id_seq_gen", sequenceName="bpm.tache_action_id_seq")
	@Id @GeneratedValue(generator="tache_action_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	/**
	 * [TacheAction.id] Setter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [TacheAction.tache] Getter 
	 * @author rlaib on : 29 avr. 2014  12:16:55
	 * @return the etape
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tache", nullable = true)
	public Tache getTache() {
		return tache;
	}

	/**
	 * [TacheAction.tache] Setter 
	 * @author rlaib on : 29 avr. 2014  12:16:55
	 * @param etape the etape to set
	 */
	public void setTache(Tache tache) {
		this.tache = tache;
	}

	/**
	 * [TacheAction.action] Getter 
	 * @author rlaib on : 29 avr. 2014  12:16:55
	 * @return the action
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_action", nullable = false)
	public Action getAction() {
		return action;
	}

	/**
	 * [TacheAction.action] Setter 
	 * @author rlaib on : 29 avr. 2014  12:16:55
	 * @param action the action to set
	 */
	public void setAction(Action action) {
		this.action = action;
	}

	/**
	 * [TacheAction.utilisateur] Getter 
	 * @author rlaib on : 29 avr. 2014  15:28:18
	 * @return the utilisateur
	 */
	@Column(name = "utilisateur")
	public String getUtilisateur() {
		return utilisateur;
	}

	/**
	 * [TacheAction.utilisateur] Setter 
	 * @author rlaib on : 29 avr. 2014  15:28:18
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * [TacheAction.commentaire] Getter 
	 * @author rlaib on : 29 avr. 2014  15:28:18
	 * @return the commentaire
	 */
	@Column(name = "commentaire")
	public String getCommentaire() {
		return commentaire;
	}

	/**
	 * [TacheAction.commentaire] Setter 
	 * @author rlaib on : 29 avr. 2014  15:28:18
	 * @param commentaire the commentaire to set
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	/**
	 * [TacheAction.dateAction] Getter 
	 * @author BELDI Jamel on : 30 avr. 2014  18:08:37
	 * @return the dateAction
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_action", length = 29)
	public Date getDateAction() {
		return dateAction;
	}

	/**
	 * [TacheAction.dateAction] Setter 
	 * @author BELDI Jamel on : 30 avr. 2014  18:08:37
	 * @param dateAction the dateAction to set
	 */
	public void setDateAction(Date dateAction) {
		this.dateAction = dateAction;
	}

	
	
	
}
