/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefParametreEtabDto.java] 
 * @author MAKERRI Sofiane on : 27 avr. 2014  14:07:43
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;

/**
 * @author MAKERRI Sofiane  on : 27 avr. 2014  14:07:43
 */
public class RefParametreEtabDto implements Serializable{

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 27 avr. 2014  14:08:13
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer idParam;
	private String key;
	private String description;
	private String defaultValue;
	private Boolean general;
	private String value;
	private Integer idEtablissement;

	/**
	 * [RefParametreEtabDto.RefParametreEtabDto()] Constructor
	 * @author MAKERRI Sofiane  on : 27 avr. 2014  14:07:43	
	 */
	public RefParametreEtabDto() {
		super();
	}


	/**
	 * [RefParametreEtabDto.id] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:09:57
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * [RefParametreEtabDto.id] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:09:57
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}



	/**
	 * [RefParametreEtabDto.idParam] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:09:57
	 * @return the idParam
	 */
	public Integer getIdParam() {
		return idParam;
	}


	/**
	 * [RefParametreEtabDto.idParam] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:09:57
	 * @param idParam the idParam to set
	 */
	public void setIdParam(Integer idParam) {
		this.idParam = idParam;
	}


	/**
	 * [RefParametreEtabDto.key] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:09:57
	 * @return the key
	 */
	public String getKey() {
		return key;
	}


	/**
	 * [RefParametreEtabDto.key] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:09:57
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}


	/**
	 * [RefParametreEtabDto.description] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:09:57
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * [RefParametreEtabDto.description] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:09:57
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * [RefParametreEtabDto.defaultValue] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:09:57
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}


	/**
	 * [RefParametreEtabDto.defaultValue] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:09:57
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}


	/**
	 * [RefParametreEtabDto.general] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:09:57
	 * @return the general
	 */
	public Boolean getGeneral() {
		return general;
	}


	/**
	 * [RefParametreEtabDto.general] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:09:57
	 * @param general the general to set
	 */
	public void setGeneral(Boolean general) {
		this.general = general;
	}


	/**
	 * [RefParametreEtabDto.value] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:09:57
	 * @return the value
	 */
	public String getValue() {
		return value;
	}


	/**
	 * [RefParametreEtabDto.value] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:09:57
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * [RefParametreEtabDto.idEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 13 mai 2014  14:09:12
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}


	/**
	 * [RefParametreEtabDto.idEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 13 mai 2014  14:09:12
	 * @param idEtablissement the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}
	
	

}
