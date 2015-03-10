/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefParametreConfigurationDto.java] 
 * @author MAKERRI Sofiane on : 22 avr. 2014  12:44:31
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;

/**
 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:44:31
 */
public class RefParametreDto  implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:45:00
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String key;
	private String description;
	private String defaultValue;
	private Boolean general;
	

	/**
	 * [RefParametreConfigurationDto.RefParametreConfigurationDto()] Constructor
	 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:44:31	
	 */
	public RefParametreDto() {
		super();
	}

	/**
	 * [RefParametreConfigurationDto.id] Getter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:45:27
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefParametreConfigurationDto.id] Setter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:45:27
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefParametreConfigurationDto.key] Getter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:45:27
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * [RefParametreConfigurationDto.key] Setter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:45:27
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * [RefParametreConfigurationDto.description] Getter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:45:27
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * [RefParametreConfigurationDto.description] Setter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:45:27
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [RefParametreDto.defaultValue] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  09:48:15
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * [RefParametreDto.defaultValue] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  09:48:15
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * [RefParametreDto.general] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  09:48:15
	 * @return the general
	 */
	public Boolean getGeneral() {
		return general;
	}

	/**
	 * [RefParametreDto.general] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  09:48:15
	 * @param general the general to set
	 */
	public void setGeneral(Boolean general) {
		this.general = general;
	}

	
	
	

}
