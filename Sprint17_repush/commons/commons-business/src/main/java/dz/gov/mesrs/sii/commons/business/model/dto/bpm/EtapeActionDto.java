/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class EtapeActionDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:46:11
	 */
	private static final long serialVersionUID = 152309478824355554L;
	private int id;
	// private Etape etape;
	private int etapeId;
	private String etapeCode;
	// private Action action;
	private int actionId;
	private String actionCode;

	public EtapeActionDto() {
	}

	/**
	 * [EtapeActionDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:46:05
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [EtapeActionDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:46:05
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EtapeActionDto.etapeId] Getter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 12:47:27
	 * @return the etapeId
	 */
	public int getEtapeId() {
		return etapeId;
	}

	/**
	 * [EtapeActionDto.etapeId] Setter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 12:47:27
	 * @param etapeId
	 *            the etapeId to set
	 */
	public void setEtapeId(int etapeId) {
		this.etapeId = etapeId;
	}

	/**
	 * [EtapeActionDto.etapeCode] Getter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 12:47:27
	 * @return the etapeCode
	 */
	public String getEtapeCode() {
		return etapeCode;
	}

	/**
	 * [EtapeActionDto.etapeCode] Setter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 12:47:27
	 * @param etapeCode
	 *            the etapeCode to set
	 */
	public void setEtapeCode(String etapeCode) {
		this.etapeCode = etapeCode;
	}

	/**
	 * [EtapeActionDto.actionId] Getter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 12:47:27
	 * @return the actionId
	 */
	public int getActionId() {
		return actionId;
	}

	/**
	 * [EtapeActionDto.actionId] Setter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 12:47:27
	 * @param actionId
	 *            the actionId to set
	 */
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	/**
	 * [EtapeActionDto.actionCode] Getter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 12:47:27
	 * @return the actionCode
	 */
	public String getActionCode() {
		return actionCode;
	}

	/**
	 * [EtapeActionDto.actionCode] Setter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 12:47:27
	 * @param actionCode
	 *            the actionCode to set
	 */
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

}
