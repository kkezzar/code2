/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Action.java] 
 * @author rlaib on : 29 avr. 2014  08:54:09
 */
package dz.gov.mesrs.sii.commons.data.model.bpm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author rlaib  on : 29 avr. 2014  08:54:09
 */
@Entity
@Table(name = "type_decision_i18n", schema = "bpm")
public class TypeDecisionI18n  implements java.io.Serializable {
	
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:21:16
	 */
	private static final long serialVersionUID = -7659329698729923284L;
	private int id;
	private String langue;
	private String libelle;
	private TypeDecision typeDecision;

	public TypeDecisionI18n() {
	}
	
	/**
	 * [Action.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="type_decision_i18n_id_seq_gen", sequenceName="bpm.type_decision_i18n_id_seq")
	@Id @GeneratedValue(generator="type_decision_i18n_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	/**
	 * [Action.id] Setter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * [ActionI18n.langue] Getter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @return the langue
	 */
	@Column(name = "langue", nullable = false,length=5)
	public String getLangue() {
		return langue;
	}
	
	/**
	 * [ActionI18n.langue] Setter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @param langue the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}
	
	/**
	 * [ActionI18n.libelle] Getter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @return the libelle
	 */
	@Column(name = "libelle", nullable = false,length=255)
	public String getLibelle() {
		return libelle;
	}
	
	/**
	 * [ActionI18n.libelle] Setter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	/**
	 * [TypeDecisionI18n.typeDecision] Getter 
	 * @author rlaib on : 29 avr. 2014  09:29:39
	 * @return the typeDecision
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_type_decision", nullable = false)
	public TypeDecision getTypeDecision() {
		return typeDecision;
	}
	
	/**
	 * [TypeDecisionI18n.typeDecision] Setter 
	 * @author rlaib on : 29 avr. 2014  09:29:39
	 * @param typeDecision the typeDecision to set
	 */
	public void setTypeDecision(TypeDecision typeDecision) {
		this.typeDecision = typeDecision;
	}

	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.TypeDecisionI18n.hashCode] Method 
	 * @author rlaib  on : 29 avr. 2014  09:30:40
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.TypeDecisionI18n.equals] Method 
	 * @author rlaib  on : 29 avr. 2014  09:30:40
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TypeDecisionI18n other = (TypeDecisionI18n) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.TypeDecisionI18n.toString] Method 
	 * @author rlaib  on : 29 avr. 2014  09:30:51
	 * @return
	 */
	@Override
	public String toString() {
		return "TypeDecisionI18n [langue=" + langue + ", libelle=" + libelle
				+ "]";
	}
	
	
	
}
