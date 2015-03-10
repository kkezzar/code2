package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import java.io.Serializable;


/**
 * @author rlaib  on : 2 oct. 2014  13:58:08
 */
public class ParamTypeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String code;
	private String libelle;
	private String masque;
	
	public ParamTypeDto() {
	}

	/**
	 * [ParamTypeDto.id] Getter 
	 * @author rlaib on : 6 oct. 2014  17:19:44
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * [ParamTypeDto.id] Setter 
	 * @author rlaib on : 6 oct. 2014  17:19:44
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * [ParamTypeDto.code] Getter 
	 * @author rlaib on : 6 oct. 2014  17:19:44
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	/**
	 * [ParamTypeDto.code] Setter 
	 * @author rlaib on : 6 oct. 2014  17:19:44
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * [ParamTypeDto.libelle] Getter 
	 * @author rlaib on : 6 oct. 2014  17:19:44
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}


	/**
	 * [ParamTypeDto.libelle] Setter 
	 * @author rlaib on : 6 oct. 2014  17:19:44
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	/**
	 * [ParamTypeDto.masque] Getter 
	 * @author rlaib on : 6 oct. 2014  17:19:44
	 * @return the masque
	 */
	public String getMasque() {
		return masque;
	}


	/**
	 * [ParamTypeDto.masque] Setter 
	 * @author rlaib on : 6 oct. 2014  17:19:44
	 * @param masque the masque to set
	 */
	public void setMasque(String masque) {
		this.masque = masque;
	}
	
	
}
