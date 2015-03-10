package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;



/**
 * @author  Rafik LAIB  on : 18 avr. 2014  19:42:45
 */
public class ParcoursTypeI18nDto implements java.io.Serializable {

	
	/**
	 * serialVersionUID 
	 * @author  Rafik LAIB  on : 18 avr. 2014  19:42:06
	 */
	private static final long serialVersionUID = 20523087697462387L;
	private int id;
	private ParcoursTypeDto parcoursTypeDto;
	private String langue;
	private String libelle;
	private String libelle_court;
	
	
	public ParcoursTypeI18nDto() {
	}

	/**
	 * [ParcoursTypeDto.getId] Method 
	 * @author  Rafik LAIB  on : 18 avr. 2014  19:42:19
	 * @return
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * [ParcoursTypeDto.setId] Method 
	 * @author  Rafik LAIB  on : 18 avr. 2014  19:42:23
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [ParcoursTypeI18nDto.parcoursTypeDto] Getter 
	 * @author  Rafik LAIB on : 19 avr. 2014  16:14:06
	 * @return the parcoursTypeDto
	 */
	public ParcoursTypeDto getParcoursTypeDto() {
		return parcoursTypeDto;
	}

	/**
	 * [ParcoursTypeI18nDto.parcoursTypeDto] Setter 
	 * @author  Rafik LAIB on : 19 avr. 2014  16:14:06
	 * @param parcoursTypeDto the parcoursTypeDto to set
	 */
	public void setParcoursTypeDto(ParcoursTypeDto parcoursTypeDto) {
		this.parcoursTypeDto = parcoursTypeDto;
	}

	/**
	 * [ParcoursTypeI18nDto.langue] Getter 
	 * @author  Rafik LAIB on : 19 avr. 2014  16:14:06
	 * @return the langue
	 */
	public String getLangue() {
		return langue;
	}

	/**
	 * [ParcoursTypeI18nDto.langue] Setter 
	 * @author  Rafik LAIB on : 19 avr. 2014  16:14:06
	 * @param langue the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}

	/**
	 * [ParcoursTypeI18nDto.libelle] Getter 
	 * @author  Rafik LAIB on : 19 avr. 2014  16:14:06
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * [ParcoursTypeI18nDto.libelle] Setter 
	 * @author  Rafik LAIB on : 19 avr. 2014  16:14:06
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * [ParcoursTypeI18nDto.libelle_court] Getter 
	 * @author  Rafik LAIB on : 19 avr. 2014  16:14:06
	 * @return the libelle_court
	 */
	public String getLibelle_court() {
		return libelle_court;
	}

	/**
	 * [ParcoursTypeI18nDto.libelle_court] Setter 
	 * @author  Rafik LAIB on : 19 avr. 2014  16:14:06
	 * @param libelle_court the libelle_court to set
	 */
	public void setLibelle_court(String libelle_court) {
		this.libelle_court = libelle_court;
	}

	

}
