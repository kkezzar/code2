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
@Table(name = "action_i18n", schema = "bpm")
public class ActionI18n implements java.io.Serializable {
	
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:17:37
	 */
	private static final long serialVersionUID = -1269798931365401322L;
	private int id;
	private String langue;
	private String libelle;
	private Action action;

	public ActionI18n() {
	}
	/**
	 * [Action.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="action_i18n_id_seq_gen", sequenceName="bpm.action_i18n_id_seq")
	@Id @GeneratedValue(generator="action_i18n_id_seq_gen")
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
	 * [ActionI18n.langue] Getter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @return the langue
	 */
	@Column(name = "langue", nullable = false,length=5)
	public String getLangue() {
		return langue;
	}
	/**
	 * [ActionI18n.langue] Setter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @param langue the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}
	/**
	 * [ActionI18n.libelle] Getter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @return the libelle
	 */
	@Column(name = "libelle", nullable = false,length=255)
	public String getLibelle() {
		return libelle;
	}
	/**
	 * [ActionI18n.libelle] Setter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * [ActionI18n.action] Getter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @return the action
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_action", nullable = false)
	public Action getAction() {
		return action;
	}
	/**
	 * [ActionI18n.action] Setter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @param action the action to set
	 */
	public void setAction(Action action) {
		this.action = action;
	}
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.ActionI18n.hashCode] Method 
	 * @author rlaib  on : 29 avr. 2014  09:05:44
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + id;
		result = prime * result + ((langue == null) ? 0 : langue.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		return result;
	}
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.ActionI18n.equals] Method 
	 * @author rlaib  on : 29 avr. 2014  09:05:44
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActionI18n other = (ActionI18n) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (id != other.id)
			return false;
		if (langue == null) {
			if (other.langue != null)
				return false;
		} else if (!langue.equals(other.langue))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.ActionI18n.toString] Method 
	 * @author rlaib  on : 29 avr. 2014  09:06:05
	 * @return
	 */
	@Override
	public String toString() {
		return "ActionI18n [langue=" + langue + ", libelle=" + libelle + "]";
	}
	
	
	
}
