/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Action.java] 
 * @author rlaib on : 29 avr. 2014  08:54:09
 */
package dz.gov.mesrs.sii.commons.data.model.bpm;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author rlaib  on : 29 avr. 2014  08:54:09
 */
@Entity
@Table(name = "situation_entite", schema = "bpm")
public class SituationEntite implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:20:06
	 */
	private static final long serialVersionUID = 4873908492408865047L;
	private int id;
	private Entite entite;
	private Situation situation;

	public SituationEntite() {
	}
	
	public SituationEntite(Situation s, Entite e) {
		
		this.situation = s;
		this.entite =e;
	}
	
	/**
	 * [Action.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="situation_entite_id_seq_gen", sequenceName="bpm.situation_entite_id_seq")
	@Id @GeneratedValue(generator="situation_entite_id_seq_gen")
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
	 * [SituationEntite.entite] Getter 
	 * @author rlaib on : 29 avr. 2014  10:05:24
	 * @return the entite
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_entite", nullable = false)
	public Entite getEntite() {
		return entite;
	}

	/**
	 * [SituationEntite.entite] Setter 
	 * @author rlaib on : 29 avr. 2014  10:05:24
	 * @param entite the entite to set
	 */
	public void setEntite(Entite entite) {
		this.entite = entite;
	}

	/**
	 * [SituationEntite.situation] Getter 
	 * @author rlaib on : 29 avr. 2014  10:05:24
	 * @return the situation
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situation", nullable = false)
	public Situation getSituation() {
		return situation;
	}

	/**
	 * [SituationEntite.situation] Setter 
	 * @author rlaib on : 29 avr. 2014  10:05:24
	 * @param situation the situation to set
	 */
	public void setSituation(Situation situation) {
		this.situation = situation;
	}

}