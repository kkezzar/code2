/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class EtapeRoleDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:48:00
	 */
	private static final long serialVersionUID = 2681020602429727241L;
	private int id;
	private int etapeId;
	private String etapeCode;
	private int roleId;
	private String roleCode;
	private Map<String, EtapeI18nDto> etapeI18nDtos = new HashMap<String, EtapeI18nDto>();
	private Map<String, RoleI18nDto> roleI18nDtos = new HashMap<String, RoleI18nDto>();

	public EtapeRoleDto() {
	}

	/**
	 * [EtapeRoleDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:47:53
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [EtapeRoleDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:47:53
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EtapeRoleDto.etapeId] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 14:04:24
	 * @return the etapeId
	 */
	public int getEtapeId() {
		return etapeId;
	}

	/**
	 * [EtapeRoleDto.etapeId] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 14:04:24
	 * @param etapeId
	 *            the etapeId to set
	 */
	public void setEtapeId(int etapeId) {
		this.etapeId = etapeId;
	}

	/**
	 * [EtapeRoleDto.roleId] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 14:04:24
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * [EtapeRoleDto.roleId] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 14:04:24
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * [EtapeRoleDto.roleCode] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 14:04:24
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * [EtapeRoleDto.roleCode] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 14:04:24
	 * @param roleCode
	 *            the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * [EtapeRoleDto.etapeCode] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 14:22:04
	 * @return the etapeCode
	 */
	public String getEtapeCode() {
		return etapeCode;
	}

	/**
	 * [EtapeRoleDto.etapeCode] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 14:22:04
	 * @param etapeCode
	 *            the etapeCode to set
	 */
	public void setEtapeCode(String etapeCode) {
		this.etapeCode = etapeCode;
	}

	/**
	 * [EtapeRoleDto.etapeI18nDtos] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 18:23:04
	 * @return the etapeI18nDtos
	 */
	public Map<String, EtapeI18nDto> getEtapeI18nDtos() {
		return etapeI18nDtos;
	}

	/**
	 * [EtapeRoleDto.etapeI18nDtos] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 18:23:04
	 * @param etapeI18nDtos
	 *            the etapeI18nDtos to set
	 */
	public void setEtapeI18nDtos(Map<String, EtapeI18nDto> etapeI18nDtos) {
		this.etapeI18nDtos = etapeI18nDtos;
	}

	/**
	 * [EtapeRoleDto.roleI18nDtos] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 18:23:04
	 * @return the roleI18nDtos
	 */
	public Map<String, RoleI18nDto> getRoleI18nDtos() {
		return roleI18nDtos;
	}

	/**
	 * [EtapeRoleDto.roleI18nDtos] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 18:23:04
	 * @param roleI18nDtos
	 *            the roleI18nDtos to set
	 */
	public void setRoleI18nDtos(Map<String, RoleI18nDto> roleI18nDtos) {
		this.roleI18nDtos = roleI18nDtos;
	}

}
