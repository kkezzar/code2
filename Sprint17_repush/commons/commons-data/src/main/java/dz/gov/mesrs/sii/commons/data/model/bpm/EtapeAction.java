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
import javax.persistence.UniqueConstraint;



/**
 * @author rlaib  on : 29 avr. 2014  08:54:09
 */
@Entity
@Table(name = "etape_action", schema = "bpm")
public class EtapeAction implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:18:52
	 */
	private static final long serialVersionUID = 2433201654261576607L;
	private int id;
	private Etape etape;
	private Action action;
	
	public EtapeAction() {
	}
	
	/**
	 * [EtapeAction.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="etape_action_id_seq_gen", sequenceName="bpm.etape_action_id_seq")
	@Id @GeneratedValue(generator="etape_action_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	/**
	 * [EtapeAction.id] Setter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EtapeAction.etape] Getter 
	 * @author rlaib on : 29 avr. 2014  12:16:55
	 * @return the etape
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_etape", nullable = false)
	public Etape getEtape() {
		return etape;
	}

	/**
	 * [EtapeAction.etape] Setter 
	 * @author rlaib on : 29 avr. 2014  12:16:55
	 * @param etape the etape to set
	 */
	public void setEtape(Etape etape) {
		this.etape = etape;
	}

	/**
	 * [EtapeAction.action] Getter 
	 * @author rlaib on : 29 avr. 2014  12:16:55
	 * @return the action
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_action", nullable = false)
	public Action getAction() {
		return action;
	}

	/**
	 * [EtapeAction.action] Setter 
	 * @author rlaib on : 29 avr. 2014  12:16:55
	 * @param action the action to set
	 */
	public void setAction(Action action) {
		this.action = action;
	}

	
}
