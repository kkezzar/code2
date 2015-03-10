/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import dz.gov.mesrs.sii.commons.data.model.bpm.DecisionI18n;
import dz.gov.mesrs.sii.commons.data.model.bpm.Demande;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.TypeDecision;

/**
 * @author rlaib  on : 29 avr. 2014  15:30:20
 */
public class DecisionDto implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  16:02:57
	 */
	private static final long serialVersionUID = 5843442199450663223L;
	private int id;
	private String code;
	private Demande demande;
	private TypeDecision typeDecision;
	private Date dateDecision;
	private Date dateVisa;
	private SituationEntite situationEntite;
	private Map<String,DecisionI18n> i18n = new HashMap<String, DecisionI18n>();
	
	public DecisionDto() {
	}

	/**
	 * [DecisionDto.id] Getter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [DecisionDto.id] Setter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [DecisionDto.code] Getter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [DecisionDto.code] Setter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [DecisionDto.demande] Getter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @return the demande
	 */
	public Demande getDemande() {
		return demande;
	}

	/**
	 * [DecisionDto.demande] Setter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @param demande the demande to set
	 */
	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	/**
	 * [DecisionDto.typeDecision] Getter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @return the typeDecision
	 */
	public TypeDecision getTypeDecision() {
		return typeDecision;
	}

	/**
	 * [DecisionDto.typeDecision] Setter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @param typeDecision the typeDecision to set
	 */
	public void setTypeDecision(TypeDecision typeDecision) {
		this.typeDecision = typeDecision;
	}

	/**
	 * [DecisionDto.dateDecision] Getter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @return the dateDecision
	 */
	public Date getDateDecision() {
		return dateDecision;
	}

	/**
	 * [DecisionDto.dateDecision] Setter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @param dateDecision the dateDecision to set
	 */
	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}

	/**
	 * [DecisionDto.dateVisa] Getter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @return the dateVisa
	 */
	public Date getDateVisa() {
		return dateVisa;
	}

	/**
	 * [DecisionDto.dateVisa] Setter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @param dateVisa the dateVisa to set
	 */
	public void setDateVisa(Date dateVisa) {
		this.dateVisa = dateVisa;
	}

	/**
	 * [DecisionDto.situationEntite] Getter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @return the situationEntite
	 */
	public SituationEntite getSituationEntite() {
		return situationEntite;
	}

	/**
	 * [DecisionDto.situationEntite] Setter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @param situationEntite the situationEntite to set
	 */
	public void setSituationEntite(SituationEntite situationEntite) {
		this.situationEntite = situationEntite;
	}

	/**
	 * [DecisionDto.i18n] Getter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @return the i18n
	 */
	public Map<String, DecisionI18n> getI18n() {
		return i18n;
	}

	/**
	 * [DecisionDto.i18n] Setter 
	 * @author rlaib on : 29 avr. 2014  15:38:57
	 * @param i18n the i18n to set
	 */
	public void setI18n(Map<String, DecisionI18n> i18n) {
		this.i18n = i18n;
	}

	
	
}
