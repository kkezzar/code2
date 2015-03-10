/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Action.java] 
 * @author rlaib on : 29 avr. 2014  08:54:09
 */
package dz.gov.mesrs.sii.commons.data.model.bpm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
 * @author rlaib on : 29 avr. 2014 08:54:09
 */
@Entity
@Table(name = "situation", schema = "bpm")
public class Situation implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:19:56
	 */
	private static final long serialVersionUID = 5950279106389740515L;
	private int id;
	private String code;
	private String styleCss;
	private String libelleFr;
	private String libelleAr;

	public Situation() {
	}

	public Situation(String code) {

		this.code = code;
	}

	/**
	 * [Action.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name = "situation_id_seq_gen", sequenceName = "bpm.situation_id_seq")
	@Id
	@GeneratedValue(generator = "situation_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * [Action.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 08:56:30
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [Action.code] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 08:56:30
	 * @return the code
	 */
	@Column(name = "code", nullable = false, length = 10)
	public String getCode() {
		return code;
	}

	/**
	 * [Action.code] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 08:56:30
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [Situation.styleCss] Getter
	 * 
	 * @author rlaib on : 20 oct. 2014 10:25:04
	 * @return the styleCss
	 */
	@Column(name = "style_css", length = 250)
	public String getStyleCss() {
		return styleCss;
	}

	/**
	 * [Situation.styleCss] Setter
	 * 
	 * @author rlaib on : 20 oct. 2014 10:25:04
	 * @param styleCss
	 *            the styleCss to set
	 */
	public void setStyleCss(String styleCss) {
		this.styleCss = styleCss;
	}

	/**
	 * [Situation.libelleFr] Getter 
	 * @author rlaib on : 7 janv. 2015  15:44:00
	 * @return the libelleFr
	 */
	@Column(name = "libelle_fr", length = 100)
	public String getLibelleFr() {
		return libelleFr;
	}

	/**
	 * [Situation.libelleFr] Setter 
	 * @author rlaib on : 7 janv. 2015  15:44:00
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	/**
	 * [Situation.libelleAr] Getter 
	 * @author rlaib on : 7 janv. 2015  15:44:00
	 * @return the libelleAr
	 */
	@Column(name = "libelle_ar", length = 100)
	public String getLibelleAr() {
		return libelleAr;
	}

	/**
	 * [Situation.libelleAr] Setter 
	 * @author rlaib on : 7 janv. 2015  15:44:00
	 * @param libelleAr the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

		/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Situation.hashCode]
	 * Method
	 * 
	 * @author rlaib on : 29 avr. 2014 10:21:07
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + id;
		return result;
	}

	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Situation.equals]
	 * Method
	 * 
	 * @author rlaib on : 29 avr. 2014 10:21:07
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
		Situation other = (Situation) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * [[dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Situation.toString]
	 * Method author rlaib on : 29 avr. 2014 10:21:16
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return "Situation [code=" + code + "]";
	}

}
