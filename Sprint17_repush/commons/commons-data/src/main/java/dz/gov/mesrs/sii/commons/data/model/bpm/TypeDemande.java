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
@Table(name = "type_demande", schema = "bpm")
public class TypeDemande implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:21:28
	 */
	private static final long serialVersionUID = -2998916493410079587L;
	private int id;
	private String code;
	private Map<String,TypeDemandeI18n> i18n = new HashMap<String, TypeDemandeI18n>();

	public TypeDemande() {
	}
	/**
	 * [Action.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="type_demande_id_seq_gen", sequenceName="bpm.type_demande_id_seq")
	@Id @GeneratedValue(generator="type_demande_id_seq_gen")
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
	 * [TypeDemande.i18n] Getter 
	 * @author rlaib on : 29 avr. 2014  09:22:10
	 * @return the i18n
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "typeDemande", targetEntity= TypeDemandeI18n.class)
	@MapKey(name="langue")
	public Map<String, TypeDemandeI18n> getI18n() {
		return i18n;
	}
	
	/**
	 * [TypeDemande.i18n] Setter 
	 * @author rlaib on : 29 avr. 2014  09:22:10
	 * @param i18n the i18n to set
	 */
	public void setI18n(Map<String, TypeDemandeI18n> i18n) {
		this.i18n = i18n;
	}
	
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.TypeDemande.hashCode] Method 
	 * @author rlaib  on : 29 avr. 2014  09:23:24
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.TypeDemande.equals] Method 
	 * @author rlaib  on : 29 avr. 2014  09:23:24
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
		TypeDemande other = (TypeDemande) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.TypeDemande.toString] Method 
	 * @author rlaib  on : 29 avr. 2014  09:23:36
	 * @return
	 */
	@Override
	public String toString() {
		return "TypeDemande [code=" + code + "]";
	}
	
	
	
}
