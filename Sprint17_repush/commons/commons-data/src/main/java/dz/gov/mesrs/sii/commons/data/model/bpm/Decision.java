/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Demande.java] 
 * @author rlaib on : 29 avr. 2014  09:12:22
 */
package dz.gov.mesrs.sii.commons.data.model.bpm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author rlaib  on : 29 avr. 2014  09:12:22
 */
@Entity
@Table(name = "decision", schema = "bpm")
public class Decision implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:17:48
	 */
	private static final long serialVersionUID = 2169260415411667779L;
	private int id;
	private String code;
	private Demande demande;
	private TypeDecision typeDecision;
	private Date dateDecision;
	private Date dateVisa;
	private SituationEntite situationEntite;
	private Map<String,DecisionI18n> i18n = new HashMap<String, DecisionI18n>();
	
	
	public Decision() {
	}

	/**
	 * [Demande.id] Getter 
	 * @author rlaib on : 29 avr. 2014  10:57:20
	 * @return the id
	 */
	@SequenceGenerator(name="decision_id_seq_gen", sequenceName="bpm.decision_id_seq")
	@Id @GeneratedValue(generator="decision_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * [Demande.id] Setter 
	 * @author rlaib on : 29 avr. 2014  10:57:20
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [Decision.code] Getter 
	 * @author rlaib on : 29 avr. 2014  11:25:37
	 * @return the code
	 */
	@Column(name = "code", nullable = false,length=10)
	public String getCode() {
		return code;
	}

	/**
	 * [Decision.code] Setter 
	 * @author rlaib on : 29 avr. 2014  11:25:37
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [Decision.demande] Getter 
	 * @author rlaib on : 29 avr. 2014  11:25:37
	 * @return the demande
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_demande", nullable = false)
	public Demande getDemande() {
		return demande;
	}

	/**
	 * [Decision.demande] Setter 
	 * @author rlaib on : 29 avr. 2014  11:25:37
	 * @param demande the demande to set
	 */
	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	/**
	 * [Decision.typeDecision] Getter 
	 * @author rlaib on : 29 avr. 2014  11:25:37
	 * @return the typeDecision
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_type_decision", nullable = false)
	public TypeDecision getTypeDecision() {
		return typeDecision;
	}

	/**
	 * [Decision.typeDecision] Setter 
	 * @author rlaib on : 29 avr. 2014  11:25:37
	 * @param typeDecision the typeDecision to set
	 */
	public void setTypeDecision(TypeDecision typeDecision) {
		this.typeDecision = typeDecision;
	}

	/**
	 * [Decision.dateDecision] Getter 
	 * @author rlaib on : 29 avr. 2014  11:25:37
	 * @return the dateDecision
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_decision", length = 29)
	public Date getDateDecision() {
		return dateDecision;
	}

	/**
	 * [Decision.dateDecision] Setter 
	 * @author rlaib on : 29 avr. 2014  11:25:37
	 * @param dateDecision the dateDecision to set
	 */
	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}

	/**
	 * [Decision.dateVisa] Getter 
	 * @author rlaib on : 29 avr. 2014  11:25:37
	 * @return the dateVisa
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_visa", length = 29)
	public Date getDateVisa() {
		return dateVisa;
	}

	/**
	 * [Decision.dateVisa] Setter 
	 * @author rlaib on : 29 avr. 2014  11:25:37
	 * @param dateVisa the dateVisa to set
	 */
	public void setDateVisa(Date dateVisa) {
		this.dateVisa = dateVisa;
	}

	/**
	 * [Decision.situationEntite] Getter 
	 * @author rlaib on : 29 avr. 2014  11:25:37
	 * @return the situationEntite
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituationEntite() {
		return situationEntite;
	}

	/**
	 * [Decision.situationEntite] Setter 
	 * @author rlaib on : 29 avr. 2014  11:25:37
	 * @param situationEntite the situationEntite to set
	 */
	public void setSituationEntite(SituationEntite situationEntite) {
		this.situationEntite = situationEntite;
	}

	/**
	 * [Decision.i18n] Getter 
	 * @author rlaib on : 29 avr. 2014  11:36:49
	 * @return the i18n
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "decision", targetEntity= DecisionI18n.class)
	@MapKey(name="langue")
	public Map<String, DecisionI18n> getI18n() {
		return i18n;
	}

	/**
	 * [Decision.i18n] Setter 
	 * @author rlaib on : 29 avr. 2014  11:36:49
	 * @param i18n the i18n to set
	 */
	public void setI18n(Map<String, DecisionI18n> i18n) {
		this.i18n = i18n;
	}

	
	
}
