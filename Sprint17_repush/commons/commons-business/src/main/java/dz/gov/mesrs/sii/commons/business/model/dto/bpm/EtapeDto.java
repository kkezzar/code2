/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;


/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class EtapeDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:45:18
	 */
	private static final long serialVersionUID = -5191044812081819172L;
	private int id;
	private String code;
	private String libelleFr;
	private String libelleAr;
	private Boolean estMultiple;
	private Integer multiplicite;
	
	// Processus
	private Integer processusId;
	private String  processusCode;
	private String  processusLibelleFr;
	private Integer processusEntiteId;
	private String processusEntiteCode;
	private String processusEntiteLibelle;

	// Groupe
	private Integer groupeId;
	private String  groupeCode;
	private String  groupeLibelleFr;

	// Role
	private Integer roleId;
	private String  roleCode;
	private String  roleLibelleFr;

	// Etape Precedente
	private Integer etapePrecedenteId;
	private String  etapePrecedenteCode;
	private String  etapePrecedenteLibelleFr;

	// Etape Suivante
	private Integer etapeSuivanteId;
	private String  etapeSuivanteCode;
	private String  etapeSuivanteLibelleFr;

	// Etape Suivante
	private Integer actionId;
	private String  actionCode;
	private String  actionLibelleFr;
	
	// Etape Suivante
	private Integer situationEntiteId;
	private Integer situationId;
	private String  situationCode;
	private String  situationLibelleFr;
	
	public EtapeDto() {
	}

	public EtapeDto(int id) {
		this.id = id;
	}

	/**
	 * [EtapeDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:45:10
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [EtapeDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:45:10
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EtapeDto.code] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:45:10
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [EtapeDto.code] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:45:10
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [EtapeDto.libelleFr] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the libelleFr
	 */
	public String getLibelleFr() {
		return libelleFr;
	}

	/**
	 * [EtapeDto.libelleFr] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	/**
	 * [EtapeDto.libelleAr] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the libelleAr
	 */
	public String getLibelleAr() {
		return libelleAr;
	}

	/**
	 * [EtapeDto.libelleAr] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param libelleAr the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	/**
	 * [EtapeDto.estMultiple] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the estMultiple
	 */
	public Boolean getEstMultiple() {
		return estMultiple;
	}

	/**
	 * [EtapeDto.estMultiple] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param estMultiple the estMultiple to set
	 */
	public void setEstMultiple(Boolean estMultiple) {
		this.estMultiple = estMultiple;
	}

	/**
	 * [EtapeDto.multiplicite] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the multiplicite
	 */
	public Integer getMultiplicite() {
		return multiplicite;
	}

	/**
	 * [EtapeDto.multiplicite] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param multiplicite the multiplicite to set
	 */
	public void setMultiplicite(Integer multiplicite) {
		this.multiplicite = multiplicite;
	}

	/**
	 * [EtapeDto.processusId] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the processusId
	 */
	public Integer getProcessusId() {
		return processusId;
	}

	/**
	 * [EtapeDto.processusId] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param processusId the processusId to set
	 */
	public void setProcessusId(Integer processusId) {
		this.processusId = processusId;
	}

	/**
	 * [EtapeDto.processusCode] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the processusCode
	 */
	public String getProcessusCode() {
		return processusCode;
	}

	/**
	 * [EtapeDto.processusCode] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param processusCode the processusCode to set
	 */
	public void setProcessusCode(String processusCode) {
		this.processusCode = processusCode;
	}

	/**
	 * [EtapeDto.processusLibelleFr] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the processusLibelleFr
	 */
	public String getProcessusLibelleFr() {
		return processusLibelleFr;
	}

	/**
	 * [EtapeDto.processusLibelleFr] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param processusLibelleFr the processusLibelleFr to set
	 */
	public void setProcessusLibelleFr(String processusLibelleFr) {
		this.processusLibelleFr = processusLibelleFr;
	}

	/**
	 * [EtapeDto.processusEntiteId] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the processusEntiteId
	 */
	public Integer getProcessusEntiteId() {
		return processusEntiteId;
	}

	/**
	 * [EtapeDto.processusEntiteId] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param processusEntiteId the processusEntiteId to set
	 */
	public void setProcessusEntiteId(Integer processusEntiteId) {
		this.processusEntiteId = processusEntiteId;
	}

	/**
	 * [EtapeDto.processusEntiteCode] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the processusEntiteCode
	 */
	public String getProcessusEntiteCode() {
		return processusEntiteCode;
	}

	/**
	 * [EtapeDto.processusEntiteCode] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param processusEntiteCode the processusEntiteCode to set
	 */
	public void setProcessusEntiteCode(String processusEntiteCode) {
		this.processusEntiteCode = processusEntiteCode;
	}

	/**
	 * [EtapeDto.processusEntiteLibelle] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the processusEntiteLibelle
	 */
	public String getProcessusEntiteLibelle() {
		return processusEntiteLibelle;
	}

	/**
	 * [EtapeDto.processusEntiteLibelle] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param processusEntiteLibelle the processusEntiteLibelle to set
	 */
	public void setProcessusEntiteLibelle(String processusEntiteLibelle) {
		this.processusEntiteLibelle = processusEntiteLibelle;
	}

	/**
	 * [EtapeDto.groupeId] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the groupeId
	 */
	public Integer getGroupeId() {
		return groupeId;
	}

	/**
	 * [EtapeDto.groupeId] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param groupeId the groupeId to set
	 */
	public void setGroupeId(Integer groupeId) {
		this.groupeId = groupeId;
	}

	/**
	 * [EtapeDto.groupeCode] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the groupeCode
	 */
	public String getGroupeCode() {
		return groupeCode;
	}

	/**
	 * [EtapeDto.groupeCode] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param groupeCode the groupeCode to set
	 */
	public void setGroupeCode(String groupeCode) {
		this.groupeCode = groupeCode;
	}

	/**
	 * [EtapeDto.groupeLibelleFr] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the groupeLibelleFr
	 */
	public String getGroupeLibelleFr() {
		return groupeLibelleFr;
	}

	/**
	 * [EtapeDto.groupeLibelleFr] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param groupeLibelleFr the groupeLibelleFr to set
	 */
	public void setGroupeLibelleFr(String groupeLibelleFr) {
		this.groupeLibelleFr = groupeLibelleFr;
	}

	/**
	 * [EtapeDto.roleId] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * [EtapeDto.roleId] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * [EtapeDto.roleCode] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * [EtapeDto.roleCode] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * [EtapeDto.roleLibelleFr] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the roleLibelleFr
	 */
	public String getRoleLibelleFr() {
		return roleLibelleFr;
	}

	/**
	 * [EtapeDto.roleLibelleFr] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param roleLibelleFr the roleLibelleFr to set
	 */
	public void setRoleLibelleFr(String roleLibelleFr) {
		this.roleLibelleFr = roleLibelleFr;
	}

	/**
	 * [EtapeDto.etapePrecedenteId] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the etapePrecedenteId
	 */
	public Integer getEtapePrecedenteId() {
		return etapePrecedenteId;
	}

	/**
	 * [EtapeDto.etapePrecedenteId] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param etapePrecedenteId the etapePrecedenteId to set
	 */
	public void setEtapePrecedenteId(Integer etapePrecedenteId) {
		this.etapePrecedenteId = etapePrecedenteId;
	}

	/**
	 * [EtapeDto.etapePrecedenteCode] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the etapePrecedenteCode
	 */
	public String getEtapePrecedenteCode() {
		return etapePrecedenteCode;
	}

	/**
	 * [EtapeDto.etapePrecedenteCode] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param etapePrecedenteCode the etapePrecedenteCode to set
	 */
	public void setEtapePrecedenteCode(String etapePrecedenteCode) {
		this.etapePrecedenteCode = etapePrecedenteCode;
	}

	/**
	 * [EtapeDto.etapePrecedenteLibelleFr] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the etapePrecedenteLibelleFr
	 */
	public String getEtapePrecedenteLibelleFr() {
		return etapePrecedenteLibelleFr;
	}

	/**
	 * [EtapeDto.etapePrecedenteLibelleFr] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param etapePrecedenteLibelleFr the etapePrecedenteLibelleFr to set
	 */
	public void setEtapePrecedenteLibelleFr(String etapePrecedenteLibelleFr) {
		this.etapePrecedenteLibelleFr = etapePrecedenteLibelleFr;
	}

	/**
	 * [EtapeDto.etapeSuivanteId] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the etapeSuivanteId
	 */
	public Integer getEtapeSuivanteId() {
		return etapeSuivanteId;
	}

	/**
	 * [EtapeDto.etapeSuivanteId] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param etapeSuivanteId the etapeSuivanteId to set
	 */
	public void setEtapeSuivanteId(Integer etapeSuivanteId) {
		this.etapeSuivanteId = etapeSuivanteId;
	}

	/**
	 * [EtapeDto.etapeSuivanteCode] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the etapeSuivanteCode
	 */
	public String getEtapeSuivanteCode() {
		return etapeSuivanteCode;
	}

	/**
	 * [EtapeDto.etapeSuivanteCode] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param etapeSuivanteCode the etapeSuivanteCode to set
	 */
	public void setEtapeSuivanteCode(String etapeSuivanteCode) {
		this.etapeSuivanteCode = etapeSuivanteCode;
	}

	/**
	 * [EtapeDto.etapeSuivanteLibelleFr] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the etapeSuivanteLibelleFr
	 */
	public String getEtapeSuivanteLibelleFr() {
		return etapeSuivanteLibelleFr;
	}

	/**
	 * [EtapeDto.etapeSuivanteLibelleFr] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param etapeSuivanteLibelleFr the etapeSuivanteLibelleFr to set
	 */
	public void setEtapeSuivanteLibelleFr(String etapeSuivanteLibelleFr) {
		this.etapeSuivanteLibelleFr = etapeSuivanteLibelleFr;
	}

	/**
	 * [EtapeDto.actionId] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the actionId
	 */
	public Integer getActionId() {
		return actionId;
	}

	/**
	 * [EtapeDto.actionId] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param actionId the actionId to set
	 */
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	/**
	 * [EtapeDto.actionCode] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the actionCode
	 */
	public String getActionCode() {
		return actionCode;
	}

	/**
	 * [EtapeDto.actionCode] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param actionCode the actionCode to set
	 */
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	/**
	 * [EtapeDto.actionLibelleFr] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the actionLibelleFr
	 */
	public String getActionLibelleFr() {
		return actionLibelleFr;
	}

	/**
	 * [EtapeDto.actionLibelleFr] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param actionLibelleFr the actionLibelleFr to set
	 */
	public void setActionLibelleFr(String actionLibelleFr) {
		this.actionLibelleFr = actionLibelleFr;
	}

	/**
	 * [EtapeDto.situationEntiteId] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the situationEntiteId
	 */
	public Integer getSituationEntiteId() {
		return situationEntiteId;
	}

	/**
	 * [EtapeDto.situationEntiteId] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param situationEntiteId the situationEntiteId to set
	 */
	public void setSituationEntiteId(Integer situationEntiteId) {
		this.situationEntiteId = situationEntiteId;
	}

	/**
	 * [EtapeDto.situationId] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the situationId
	 */
	public Integer getSituationId() {
		return situationId;
	}

	/**
	 * [EtapeDto.situationId] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param situationId the situationId to set
	 */
	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}

	/**
	 * [EtapeDto.situationCode] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the situationCode
	 */
	public String getSituationCode() {
		return situationCode;
	}

	/**
	 * [EtapeDto.situationCode] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param situationCode the situationCode to set
	 */
	public void setSituationCode(String situationCode) {
		this.situationCode = situationCode;
	}

	/**
	 * [EtapeDto.situationLibelleFr] Getter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @return the situationLibelleFr
	 */
	public String getSituationLibelleFr() {
		return situationLibelleFr;
	}

	/**
	 * [EtapeDto.situationLibelleFr] Setter 
	 * @author rlaib on : 15 janv. 2015  11:49:00
	 * @param situationLibelleFr the situationLibelleFr to set
	 */
	public void setSituationLibelleFr(String situationLibelleFr) {
		this.situationLibelleFr = situationLibelleFr;
	}
	
	
}
