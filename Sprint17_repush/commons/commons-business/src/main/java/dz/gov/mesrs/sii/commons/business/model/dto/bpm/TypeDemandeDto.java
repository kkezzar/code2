/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

import java.util.HashMap;
import java.util.Map;

import dz.gov.mesrs.sii.commons.data.model.bpm.TypeDemandeI18n;

/**
 * @author rlaib  on : 29 avr. 2014  15:30:20
 */
public class TypeDemandeDto implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  16:01:04
	 */
	private static final long serialVersionUID = -4360779147941850455L;
	private int id;
	private String code;
	private Map<String,TypeDemandeI18n> i18n = new HashMap<String, TypeDemandeI18n>();
	
	public TypeDemandeDto() {
	}

	/**
	 * [TypeDemandeDto.id] Getter 
	 * @author rlaib on : 29 avr. 2014  16:01:00
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [TypeDemandeDto.id] Setter 
	 * @author rlaib on : 29 avr. 2014  16:01:00
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [TypeDemandeDto.code] Getter 
	 * @author rlaib on : 29 avr. 2014  16:01:00
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [TypeDemandeDto.code] Setter 
	 * @author rlaib on : 29 avr. 2014  16:01:00
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [TypeDemandeDto.i18n] Getter 
	 * @author rlaib on : 29 avr. 2014  16:01:00
	 * @return the i18n
	 */
	public Map<String, TypeDemandeI18n> getI18n() {
		return i18n;
	}

	/**
	 * [TypeDemandeDto.i18n] Setter 
	 * @author rlaib on : 29 avr. 2014  16:01:00
	 * @param i18n the i18n to set
	 */
	public void setI18n(Map<String, TypeDemandeI18n> i18n) {
		this.i18n = i18n;
	}
	
	
}
