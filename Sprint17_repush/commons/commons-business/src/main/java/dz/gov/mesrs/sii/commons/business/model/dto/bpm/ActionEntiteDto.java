/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;


/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class ActionEntiteDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 16:02:38
	 */
	private static final long serialVersionUID = -7594826718131528952L;
	private int id;
	
	// Action 
	private Integer actionId;
	private String actionCode;
	private String actionLibelleFr;
	// Entite
	private Integer entiteId;
	private String entiteCode;
	private String entiteLibelleFr;
	
	public ActionEntiteDto() {
	}

	public ActionEntiteDto(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionLibelleFr() {
		return actionLibelleFr;
	}

	public void setActionLibelleFr(String actionLibelleFr) {
		this.actionLibelleFr = actionLibelleFr;
	}

	public Integer getEntiteId() {
		return entiteId;
	}

	public void setEntiteId(Integer entiteId) {
		this.entiteId = entiteId;
	}

	public String getEntiteCode() {
		return entiteCode;
	}

	public void setEntiteCode(String entiteCode) {
		this.entiteCode = entiteCode;
	}

	public String getEntiteLibelleFr() {
		return entiteLibelleFr;
	}

	public void setEntiteLibelleFr(String entiteLibelleFr) {
		this.entiteLibelleFr = entiteLibelleFr;
	}
	
}
