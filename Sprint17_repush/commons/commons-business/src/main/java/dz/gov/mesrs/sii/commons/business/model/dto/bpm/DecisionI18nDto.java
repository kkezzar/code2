/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;


import dz.gov.mesrs.sii.commons.data.model.bpm.Decision;

/**
 * @author rlaib  on : 29 avr. 2014  15:30:20
 */
public class DecisionI18nDto implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:40:48
	 */
	private static final long serialVersionUID = -3591608889239128423L;
	private int id;
	private String langue;
	private String titre;
	private String message;
	private String motif;
	private Decision decision;
	public DecisionI18nDto() {
	}
	/**
	 * [DecisionI18nDto.id] Getter 
	 * @author rlaib on : 29 avr. 2014  15:40:42
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * [DecisionI18nDto.id] Setter 
	 * @author rlaib on : 29 avr. 2014  15:40:42
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * [DecisionI18nDto.langue] Getter 
	 * @author rlaib on : 29 avr. 2014  15:40:42
	 * @return the langue
	 */
	public String getLangue() {
		return langue;
	}
	/**
	 * [DecisionI18nDto.langue] Setter 
	 * @author rlaib on : 29 avr. 2014  15:40:42
	 * @param langue the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}
	/**
	 * [DecisionI18nDto.titre] Getter 
	 * @author rlaib on : 29 avr. 2014  15:40:42
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}
	/**
	 * [DecisionI18nDto.titre] Setter 
	 * @author rlaib on : 29 avr. 2014  15:40:42
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}
	/**
	 * [DecisionI18nDto.message] Getter 
	 * @author rlaib on : 29 avr. 2014  15:40:42
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * [DecisionI18nDto.message] Setter 
	 * @author rlaib on : 29 avr. 2014  15:40:42
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * [DecisionI18nDto.motif] Getter 
	 * @author rlaib on : 29 avr. 2014  15:40:42
	 * @return the motif
	 */
	public String getMotif() {
		return motif;
	}
	/**
	 * [DecisionI18nDto.motif] Setter 
	 * @author rlaib on : 29 avr. 2014  15:40:42
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}
	/**
	 * [DecisionI18nDto.decision] Getter 
	 * @author rlaib on : 29 avr. 2014  15:40:42
	 * @return the decision
	 */
	public Decision getDecision() {
		return decision;
	}
	/**
	 * [DecisionI18nDto.decision] Setter 
	 * @author rlaib on : 29 avr. 2014  15:40:42
	 * @param decision the decision to set
	 */
	public void setDecision(Decision decision) {
		this.decision = decision;
	}

		
}
