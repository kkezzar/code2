/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Action.java] 
 * @author rlaib on : 29 avr. 2014  08:54:09
 */
package dz.gov.mesrs.sii.commons.data.model.bpm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author rlaib  on : 29 avr. 2014  08:54:09
 */
@Entity
@Table(name = "action", schema = "bpm")
public class Action implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:17:29
	 */
	private static final long serialVersionUID = -3816668385264156758L;
	private int id;
	private String code;
	private String libelleFr;
	private String libelleAr;

	public Action() {
	}
	/**
	 * [Action.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="action_id_seq_gen", sequenceName="bpm.action_id_seq")
	@Id @GeneratedValue(generator="action_id_seq_gen")
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
	 * [Action.code] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the code
	 */
	@Column(name = "code", nullable = false,length=10)
	public String getCode() {
		return code;
	}
	/**
	 * [Action.code] Setter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * [Action.libelleFr] Getter 
	 * @author rlaib on : 15 janv. 2015  10:17:21
	 * @return the libelleFr
	 */
	@Column(name = "libelle_fr", nullable = false,length=100)
	public String getLibelleFr() {
		return libelleFr;
	}
	/**
	 * [Action.libelleFr] Setter 
	 * @author rlaib on : 15 janv. 2015  10:17:21
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}
	/**
	 * [Action.libelleAr] Getter 
	 * @author rlaib on : 15 janv. 2015  10:17:21
	 * @return the libelleAr
	 */
	@Column(name = "libelle_ar", nullable = false,length=100)
	public String getLibelleAr() {
		return libelleAr;
	}
	/**
	 * [Action.libelleAr] Setter 
	 * @author rlaib on : 15 janv. 2015  10:17:21
	 * @param libelleAr the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}
	/**
	 * [dz.gov.mesrs.sii.commons.data.model.bpm.Action.hashCode] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  10:18:24
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((libelleAr == null) ? 0 : libelleAr.hashCode());
		result = prime * result
				+ ((libelleFr == null) ? 0 : libelleFr.hashCode());
		return result;
	}
	/**
	 * [dz.gov.mesrs.sii.commons.data.model.bpm.Action.equals] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  10:18:24
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
		Action other = (Action) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id != other.id)
			return false;
		if (libelleAr == null) {
			if (other.libelleAr != null)
				return false;
		} else if (!libelleAr.equals(other.libelleAr))
			return false;
		if (libelleFr == null) {
			if (other.libelleFr != null)
				return false;
		} else if (!libelleFr.equals(other.libelleFr))
			return false;
		return true;
	}
	/**
	 * [dz.gov.mesrs.sii.commons.data.model.bpm.Action.toString] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  10:18:50
	 * @return
	 */
	@Override
	public String toString() {
		return "Action [code=" + code + ", libelleFr=" + libelleFr + "]";
	}
	
	
	
}
