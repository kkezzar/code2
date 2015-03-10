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
@Table(name = "role_i18n", schema = "bpm")
public class RoleI18n implements java.io.Serializable {
	
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:19:48
	 */
	private static final long serialVersionUID = 8755715462462747173L;
	private int id;
	private String langue;
	private String libelle;
	private Role role;

	public RoleI18n() {
	}
	/**
	 * [RoleI18n.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="role_i18n_id_seq_gen", sequenceName="bpm.role_i18n_id_seq")
	@Id @GeneratedValue(generator="role_i18n_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}
	/**
	 * [RoleI18n.id] Setter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * [RoleI18n.langue] Getter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @return the langue
	 */
	@Column(name = "langue", nullable = false,length=5)
	public String getLangue() {
		return langue;
	}
	/**
	 * [RoleI18n.langue] Setter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @param langue the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}
	/**
	 * [RoleI18n.libelle] Getter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @return the libelle
	 */
	@Column(name = "libelle", nullable = false,length=255)
	public String getLibelle() {
		return libelle;
	}
	/**
	 * [RoleI18n.libelle] Setter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * [RoleI18n.role] Getter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @return the role
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_role", nullable = false)
	public Role getRole() {
		return this.role;
	}
	/**
	 * [RoleI18n.role] Setter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.RoleI18n.hashCode] Method 
	 * @author rlaib  on : 29 avr. 2014  11:54:18
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.RoleI18n.equals] Method 
	 * @author rlaib  on : 29 avr. 2014  11:54:18
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
		RoleI18n other = (RoleI18n) obj;
		if (id != other.id)
			return false;
		return true;
	}
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.RoleI18n.toString] Method 
	 * @author rlaib  on : 29 avr. 2014  11:54:27
	 * @return
	 */
	@Override
	public String toString() {
		return "RoleI18n [langue=" + langue + ", libelle=" + libelle + "]";
	}
	
	
	
}
