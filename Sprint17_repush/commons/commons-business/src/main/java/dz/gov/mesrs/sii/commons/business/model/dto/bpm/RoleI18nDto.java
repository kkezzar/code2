/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class RoleI18nDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:51:46
	 */
	private static final long serialVersionUID = -1113845031234747944L;
	private int id;
	private String langue;
	private String libelle;
	// private Role role;
	private int roleId;
	private String roleCode;

	public RoleI18nDto() {
	}

	/**
	 * [RoleI18nDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:51:41
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [RoleI18nDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:51:41
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [RoleI18nDto.langue] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:51:41
	 * @return the langue
	 */
	public String getLangue() {
		return langue;
	}

	/**
	 * [RoleI18nDto.langue] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:51:41
	 * @param langue
	 *            the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}

	/**
	 * [RoleI18nDto.libelle] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:51:41
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * [RoleI18nDto.libelle] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:51:41
	 * @param libelle
	 *            the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * [RoleI18nDto.roleId] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:58:13
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * [RoleI18nDto.roleId] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:58:13
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * [RoleI18nDto.roleCode] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:58:13
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * [RoleI18nDto.roleCode] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:58:13
	 * @param roleCode
	 *            the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}
