/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

import java.util.Date;

/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class TacheDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:56:44
	 */
	private static final long serialVersionUID = -1103135024187703191L;
	private int id;
	// private EtapeRole etapeRole;
	private int etapeRoleId;
	private int etapeId;
	private String etapeCode;
	private int roleId;
	private String roleCode;

	// private SituationEntite situationEntite;
	private Integer idSituation;
	private Date dateCreation;
	private Date dateCloture;
	private int idOffreFormation;
	private int idDemande;
	private int idDecision;

	private EtapeRoleDto etapeRoleDto = new EtapeRoleDto();

	public TacheDto() {
	}

	/**
	 * [TacheDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:56:37
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [TacheDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:56:37
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [TacheDto.idSituation] Getter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 18:48:13
	 * @return the idSituation
	 */
	public Integer getIdSituation() {
		return idSituation;
	}

	/**
	 * [TacheDto.idSituation] Setter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 18:48:13
	 * @param idSituation
	 *            the idSituation to set
	 */
	public void setIdSituation(Integer idSituation) {
		this.idSituation = idSituation;
	}

	/**
	 * [TacheDto.dateCreation] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:56:37
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * [TacheDto.dateCreation] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:56:37
	 * @param dateCreation
	 *            the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * [TacheDto.dateCloture] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:56:37
	 * @return the dateCloture
	 */
	public Date getDateCloture() {
		return dateCloture;
	}

	/**
	 * [TacheDto.dateCloture] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:56:37
	 * @param dateCloture
	 *            the dateCloture to set
	 */
	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	/**
	 * [TacheDto.idOffreFormation] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:56:37
	 * @return the idOffreFormation
	 */
	public int getIdOffreFormation() {
		return idOffreFormation;
	}

	/**
	 * [TacheDto.idOffreFormation] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:56:37
	 * @param idOffreFormation
	 *            the idOffreFormation to set
	 */
	public void setIdOffreFormation(int idOffreFormation) {
		this.idOffreFormation = idOffreFormation;
	}

	/**
	 * [TacheDto.idDemande] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:56:37
	 * @return the idDemande
	 */
	public int getIdDemande() {
		return idDemande;
	}

	/**
	 * [TacheDto.idDemande] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:56:37
	 * @param idDemande
	 *            the idDemande to set
	 */
	public void setIdDemande(int idDemande) {
		this.idDemande = idDemande;
	}

	/**
	 * [TacheDto.idDecision] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:56:37
	 * @return the idDecision
	 */
	public int getIdDecision() {
		return idDecision;
	}

	/**
	 * [TacheDto.idDecision] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:56:37
	 * @param idDecision
	 *            the idDecision to set
	 */
	public void setIdDecision(int idDecision) {
		this.idDecision = idDecision;
	}

	/**
	 * [TacheDto.etapeRoleId] Getter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 18:46:20
	 * @return the etapeRoleId
	 */
	public int getEtapeRoleId() {
		return etapeRoleId;
	}

	/**
	 * [TacheDto.etapeRoleId] Setter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 18:46:20
	 * @param etapeRoleId
	 *            the etapeRoleId to set
	 */
	public void setEtapeRoleId(int etapeRoleId) {
		this.etapeRoleId = etapeRoleId;
	}

	/**
	 * [TacheDto.etapeId] Getter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 18:46:20
	 * @return the etapeId
	 */
	public int getEtapeId() {
		return etapeId;
	}

	/**
	 * [TacheDto.etapeId] Setter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 18:46:20
	 * @param etapeId
	 *            the etapeId to set
	 */
	public void setEtapeId(int etapeId) {
		this.etapeId = etapeId;
	}

	/**
	 * [TacheDto.etapeCode] Getter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 18:46:20
	 * @return the etapeCode
	 */
	public String getEtapeCode() {
		return etapeCode;
	}

	/**
	 * [TacheDto.etapeCode] Setter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 18:46:20
	 * @param etapeCode
	 *            the etapeCode to set
	 */
	public void setEtapeCode(String etapeCode) {
		this.etapeCode = etapeCode;
	}

	/**
	 * [TacheDto.roleId] Getter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 18:46:20
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * [TacheDto.roleId] Setter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 18:46:20
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * [TacheDto.roleCode] Getter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 18:46:20
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * [TacheDto.roleCode] Setter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 18:46:20
	 * @param roleCode
	 *            the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * [TacheDto.etapeRoleDto] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 18:21:04
	 * @return the etapeRoleDto
	 */
	public EtapeRoleDto getEtapeRoleDto() {
		return etapeRoleDto;
	}

	/**
	 * [TacheDto.etapeRoleDto] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 18:21:04
	 * @param etapeRoleDto
	 *            the etapeRoleDto to set
	 */
	public void setEtapeRoleDto(EtapeRoleDto etapeRoleDto) {
		this.etapeRoleDto = etapeRoleDto;
	}

}
