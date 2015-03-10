/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Action.java] 
 * @author rlaib on : 29 avr. 2014  08:54:09
 */
package dz.gov.mesrs.sii.commons.data.model.bpm;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author rlaib  on : 29 avr. 2014  08:54:09
 */
@Entity
@Table(name = "type_decision", schema = "bpm")
public class TypeDecision implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:21:06
	 */
	private static final long serialVersionUID = -8182003402714899457L;
	private int id;
	private String code;
	private Map<String,TypeDecisionI18n> i18n = new HashMap<String, TypeDecisionI18n>();

	public TypeDecision() {
	}
	/**
	 * [Action.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="type_decision_id_seq_gen", sequenceName="bpm.type_decision_id_seq")
	@Id @GeneratedValue(generator="type_decision_id_seq_gen")
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
	 * [TypeDemande.code] Getter 
	 * @author rlaib on : 29 avr. 2014  09:16:30
	 * @return the code
	 */
	@Column(name = "code", nullable = false,length=10)
	public String getCode() {
		return code;
	}
	
	/**
	 * [TypeDemande.code] Setter 
	 * @author rlaib on : 29 avr. 2014  09:16:30
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * [TypeDecision.i18n] Getter 
	 * @author rlaib on : 29 avr. 2014  09:31:42
	 * @return the i18n
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "typeDecision", targetEntity= TypeDecisionI18n.class)
	@MapKey(name="langue")
	public Map<String, TypeDecisionI18n> getI18n() {
		return i18n;
	}
	
	/**
	 * [TypeDecision.i18n] Setter 
	 * @author rlaib on : 29 avr. 2014  09:31:42
	 * @param i18n the i18n to set
	 */
	public void setI18n(Map<String, TypeDecisionI18n> i18n) {
		this.i18n = i18n;
	}
	
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.TypeDecision.hashCode] Method 
	 * @author rlaib  on : 29 avr. 2014  09:32:42
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
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.TypeDecision.equals] Method 
	 * @author rlaib  on : 29 avr. 2014  09:32:42
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
		TypeDecision other = (TypeDecision) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.TypeDecision.toString] Method 
	 * @author rlaib  on : 29 avr. 2014  09:32:52
	 * @return
	 */
	@Override
	public String toString() {
		return "TypeDecision [code=" + code + "]";
	}
	
	
	
}
