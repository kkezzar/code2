/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

import java.util.HashMap;
import java.util.Map;

import dz.gov.mesrs.sii.commons.data.model.bpm.TypeDecisionI18n;

/**
 * @author rlaib  on : 29 avr. 2014  15:30:20
 */
public class TypeDecisionDto implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:58:49
	 */
	private static final long serialVersionUID = 1537627954309621858L;
	private int id;
	private String code;
	private Map<String,TypeDecisionI18n> i18n = new HashMap<String, TypeDecisionI18n>();
	
	public TypeDecisionDto() {
	}

	/**
	 * [TypeDecisionDto.id] Getter 
	 * @author rlaib on : 29 avr. 2014  15:58:39
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [TypeDecisionDto.id] Setter 
	 * @author rlaib on : 29 avr. 2014  15:58:39
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [TypeDecisionDto.code] Getter 
	 * @author rlaib on : 29 avr. 2014  15:58:39
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [TypeDecisionDto.code] Setter 
	 * @author rlaib on : 29 avr. 2014  15:58:39
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [TypeDecisionDto.i18n] Getter 
	 * @author rlaib on : 29 avr. 2014  15:58:39
	 * @return the i18n
	 */
	public Map<String, TypeDecisionI18n> getI18n() {
		return i18n;
	}

	/**
	 * [TypeDecisionDto.i18n] Setter 
	 * @author rlaib on : 29 avr. 2014  15:58:39
	 * @param i18n the i18n to set
	 */
	public void setI18n(Map<String, TypeDecisionI18n> i18n) {
		this.i18n = i18n;
	}

	
}
