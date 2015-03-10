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
@Table(name = "etape_role", schema = "bpm")
public class EtapeRole implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:19:11
	 */
	private static final long serialVersionUID = 7700363984303334936L;
	private int id;
	private Etape etape;
	private Role role;

	public EtapeRole() {
	}
	
	/**
	 * [EtapeRole.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="etape_role_id_seq_gen", sequenceName="bpm.etape_role_id_seq")
	@Id @GeneratedValue(generator="etape_role_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	/**
	 * [EtapeRole.id] Setter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EtapeRole.etape] Getter 
	 * @author rlaib on : 29 avr. 2014  12:20:10
	 * @return the etape
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_etape", nullable = false)
	public Etape getEtape() {
		return etape;
	}

	/**
	 * [EtapeRole.etape] Setter 
	 * @author rlaib on : 29 avr. 2014  12:20:10
	 * @param etape the etape to set
	 */
	public void setEtape(Etape etape) {
		this.etape = etape;
	}

	/**
	 * [EtapeRole.role] Getter 
	 * @author rlaib on : 29 avr. 2014  12:20:10
	 * @return the role
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_role", nullable = false)
	public Role getRole() {
		return role;
	}

	/**
	 * [EtapeRole.role] Setter 
	 * @author rlaib on : 29 avr. 2014  12:20:10
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	
}
