/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

import java.util.HashMap;
import java.util.Map;

import dz.gov.mesrs.sii.commons.data.model.bpm.RoleI18n;

/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class RoleDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:50:50
	 */
	private static final long serialVersionUID = -3936493156149092753L;
	private int id;
	private String code;
	private Map<String, RoleI18n> i18n = new HashMap<String, RoleI18n>();

	public RoleDto(int id) {
		this.id = id;
	}

	public RoleDto() {
	}

	/**
	 * [RoleDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:50:46
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [RoleDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:50:46
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [RoleDto.code] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:50:46
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [RoleDto.code] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:50:46
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [RoleDto.i18n] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:50:46
	 * @return the i18n
	 */
	public Map<String, RoleI18n> getI18n() {
		return i18n;
	}

	/**
	 * [RoleDto.i18n] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:50:46
	 * @param i18n
	 *            the i18n to set
	 */
	public void setI18n(Map<String, RoleI18n> i18n) {
		this.i18n = i18n;
	}

}
