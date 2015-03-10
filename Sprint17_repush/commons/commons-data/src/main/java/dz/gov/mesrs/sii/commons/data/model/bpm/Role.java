/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Action.java] 
 * @author rlaib on : 29 avr. 2014  08:54:09
 */
package dz.gov.mesrs.sii.commons.data.model.bpm;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author rlaib  on : 29 avr. 2014  08:54:09
 */
@Entity
@Table(name = "role", schema = "bpm")
public class Role implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:19:37
	 */
	private static final long serialVersionUID = 8507059365020780945L;
	private int id;
	private String code;
	private Map<String,RoleI18n> i18n = new HashMap<String, RoleI18n>();

	public Role() {
	}
	/**
	 * [Role.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="role_id_seq_gen", sequenceName="bpm.role_id_seq")
	@Id @GeneratedValue(generator="role_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}
	/**
	 * [Role.id] Setter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * [Role.code] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the code
	 */
	@Column(name = "code", nullable = false,length=10)
	public String getCode() {
		return code;
	}
	/**
	 * [Role.code] Setter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * [Role.i18n] Getter 
	 * @author rlaib on : 29 avr. 2014  11:53:36
	 * @return the i18n
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "role", targetEntity= RoleI18n.class)
	@MapKey(name="langue")
	public Map<String, RoleI18n> getI18n() {
		return i18n;
	}
	/**
	 * [Role.i18n] Setter 
	 * @author rlaib on : 29 avr. 2014  11:53:36
	 * @param i18n the i18n to set
	 */
	public void setI18n(Map<String, RoleI18n> i18n) {
		this.i18n = i18n;
	}
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Role.hashCode] Method 
	 * @author rlaib  on : 29 avr. 2014  11:54:39
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + id;
		return result;
	}
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Role.equals] Method 
	 * @author rlaib  on : 29 avr. 2014  11:54:39
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
		Role other = (Role) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Role.toString] Method 
	 * @author rlaib  on : 29 avr. 2014  11:54:47
	 * @return
	 */
	@Override
	public String toString() {
		return "Role [code=" + code + "]";
	}
	
	
}
