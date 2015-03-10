/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class TacheActionDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:57:50
	 */
	private static final long serialVersionUID = 243094334066168457L;
	private int id;
	// private Tache tache;
	private int tacheId;
	private int actionId;
	// private Action action;
	private String utilisateur;
	private String commentaire;
	private Date dateAction;
	private Map<String, ActionI18nDto> actionI18nDtos = new HashMap<String, ActionI18nDto>();
	private TacheDto tacheDto = new TacheDto();

	public TacheActionDto() {
	}

	/**
	 * [TacheActionDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:57:44
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [TacheActionDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:57:44
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [TacheActionDto.utilisateur] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:57:44
	 * @return the utilisateur
	 */
	public String getUtilisateur() {
		return utilisateur;
	}

	/**
	 * [TacheActionDto.utilisateur] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:57:44
	 * @param utilisateur
	 *            the utilisateur to set
	 */
	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * [TacheActionDto.commentaire] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:57:44
	 * @return the commentaire
	 */
	public String getCommentaire() {
		return commentaire;
	}

	/**
	 * [TacheActionDto.commentaire] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:57:44
	 * @param commentaire
	 *            the commentaire to set
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	/**
	 * [TacheActionDto.tacheId] Getter
	 * 
	 * @author BELDI Jamel on : 30 avr. 2014 15:53:14
	 * @return the tacheId
	 */
	public int getTacheId() {
		return tacheId;
	}

	/**
	 * [TacheActionDto.tacheId] Setter
	 * 
	 * @author BELDI Jamel on : 30 avr. 2014 15:53:14
	 * @param tacheId
	 *            the tacheId to set
	 */
	public void setTacheId(int tacheId) {
		this.tacheId = tacheId;
	}

	/**
	 * [TacheActionDto.actionId] Getter
	 * 
	 * @author BELDI Jamel on : 30 avr. 2014 15:53:14
	 * @return the actionId
	 */
	public int getActionId() {
		return actionId;
	}

	/**
	 * [TacheActionDto.actionId] Setter
	 * 
	 * @author BELDI Jamel on : 30 avr. 2014 15:53:14
	 * @param actionId
	 *            the actionId to set
	 */
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	/**
	 * [TacheActionDto.dateAction] Getter
	 * 
	 * @author BELDI Jamel on : 30 avr. 2014 18:09:52
	 * @return the dateAction
	 */
	public Date getDateAction() {
		return dateAction;
	}

	/**
	 * [TacheActionDto.dateAction] Setter
	 * 
	 * @author BELDI Jamel on : 30 avr. 2014 18:09:52
	 * @param dateAction
	 *            the dateAction to set
	 */
	public void setDateAction(Date dateAction) {
		this.dateAction = dateAction;
	}

	/**
	 * [TacheActionDto.actionI18nDtos] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 18:18:51
	 * @return the actionI18nDtos
	 */
	public Map<String, ActionI18nDto> getActionI18nDtos() {
		return actionI18nDtos;
	}

	/**
	 * [TacheActionDto.actionI18nDtos] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 18:18:51
	 * @param actionI18nDtos
	 *            the actionI18nDtos to set
	 */
	public void setActionI18nDtos(Map<String, ActionI18nDto> actionI18nDtos) {
		this.actionI18nDtos = actionI18nDtos;
	}

	/**
	 * [TacheActionDto.tacheDto] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 18:18:51
	 * @return the tacheDto
	 */
	public TacheDto getTacheDto() {
		return tacheDto;
	}

	/**
	 * [TacheActionDto.tacheDto] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 18:18:51
	 * @param tacheDto
	 *            the tacheDto to set
	 */
	public void setTacheDto(TacheDto tacheDto) {
		this.tacheDto = tacheDto;
	}

}
