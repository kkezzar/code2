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
@Table(name = "action_entite", schema = "bpm", uniqueConstraints=@UniqueConstraint(columnNames={"id_action", "id_entite"}))
public class ActionEntite implements java.io.Serializable {
	

	private static final long serialVersionUID = 4873908492408865047L;
	private int id;
	private Entite entite;
	private Action action;

	public ActionEntite() {
	}

	@SequenceGenerator(name="action_entite_id_seq_gen", sequenceName="bpm.action_entite_id_seq")
	@Id @GeneratedValue(generator="action_entite_id_seq_gen")
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_entite", nullable = false)
	public Entite getEntite() {
		return entite;
	}

	public void setEntite(Entite entite) {
		this.entite = entite;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_action", nullable = false)
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	
	
}