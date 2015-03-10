/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class EtapeI18nDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:47:04
	 */
	private static final long serialVersionUID = 5077206372998840947L;
	private int id;
	private String langue;
	private String libelle;
	// private Etape etape;
	private int etapeId;
	private String etapeCode;

	public EtapeI18nDto() {
	}

	/**
	 * [EtapeI18nDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:46:59
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [EtapeI18nDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:46:59
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EtapeI18nDto.langue] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:46:59
	 * @return the langue
	 */
	public String getLangue() {
		return langue;
	}

	/**
	 * [EtapeI18nDto.langue] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:46:59
	 * @param langue
	 *            the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}

	/**
	 * [EtapeI18nDto.libelle] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:46:59
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * [EtapeI18nDto.libelle] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:46:59
	 * @param libelle
	 *            the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * [EtapeI18nDto.etapeId] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:57:18
	 * @return the etapeId
	 */
	public int getEtapeId() {
		return etapeId;
	}

	/**
	 * [EtapeI18nDto.etapeId] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:57:18
	 * @param etapeId
	 *            the etapeId to set
	 */
	public void setEtapeId(int etapeId) {
		this.etapeId = etapeId;
	}

	/**
	 * [EtapeI18nDto.etapeCode] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:57:18
	 * @return the etapeCode
	 */
	public String getEtapeCode() {
		return etapeCode;
	}

	/**
	 * [EtapeI18nDto.etapeCode] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:57:18
	 * @param etapeCode
	 *            the etapeCode to set
	 */
	public void setEtapeCode(String etapeCode) {
		this.etapeCode = etapeCode;
	}

}
