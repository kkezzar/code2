/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class ActionI18nDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:37:10
	 */
	private static final long serialVersionUID = 3942756997255179425L;
	private int id;
	private String langue;
	private String libelle;
	// private Action action;
	private int actionId;
	private String actionCode;

	public ActionI18nDto() {
	}

	/**
	 * [ActionI18nDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:37:03
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [ActionI18nDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:37:03
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [ActionI18nDto.langue] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:37:03
	 * @return the langue
	 */
	public String getLangue() {
		return langue;
	}

	/**
	 * [ActionI18nDto.langue] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:37:03
	 * @param langue
	 *            the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}

	/**
	 * [ActionI18nDto.libelle] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:37:03
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * [ActionI18nDto.libelle] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:37:03
	 * @param libelle
	 *            the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * [ActionI18nDto.actionId] Getter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 15:30:28
	 * @return the actionId
	 */
	public int getActionId() {
		return actionId;
	}

	/**
	 * [ActionI18nDto.actionId] Setter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 15:30:28
	 * @param actionId
	 *            the actionId to set
	 */
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	/**
	 * [ActionI18nDto.actionCode] Getter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 15:30:28
	 * @return the actionCode
	 */
	public String getActionCode() {
		return actionCode;
	}

	/**
	 * [ActionI18nDto.actionCode] Setter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 15:30:28
	 * @param actionCode
	 *            the actionCode to set
	 */
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

}
