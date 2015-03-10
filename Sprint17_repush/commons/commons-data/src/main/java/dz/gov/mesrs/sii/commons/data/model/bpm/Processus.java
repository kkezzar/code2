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
@Table(name = "processus", schema = "bpm")
public class Processus implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:19:19
	 */
	private static final long serialVersionUID = -980067712791244523L;
	private int id;
	private String code;
	private String libelleFr;
	private String libelleAr;
	private Entite entite;

	public Processus() {
	}
	/**
	 * [Action.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="processus_id_seq_gen", sequenceName="bpm.processus_id_seq")
	@Id @GeneratedValue(generator="processus_id_seq_gen")
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
	 * [Processus.libelleFr] Getter 
	 * @author rlaib on : 15 janv. 2015  10:22:11
	 * @return the libelleFr
	 */
	@Column(name = "libelle_fr", nullable = false,length=255)
	public String getLibelleFr() {
		return libelleFr;
	}
	/**
	 * [Processus.libelleFr] Setter 
	 * @author rlaib on : 15 janv. 2015  10:22:11
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}
	/**
	 * [Processus.libelleAr] Getter 
	 * @author rlaib on : 15 janv. 2015  10:22:11
	 * @return the libelleAr
	 */
	@Column(name = "libelle_ar", nullable = false,length=255)
	public String getLibelleAr() {
		return libelleAr;
	}
	/**
	 * [Processus.libelleAr] Setter 
	 * @author rlaib on : 15 janv. 2015  10:22:11
	 * @param libelleAr the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}
	/**
	 * [Processus.entite] Getter 
	 * @author rlaib on : 15 janv. 2015  10:22:11
	 * @return the entite
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_entite", nullable = false)
	public Entite getEntite() {
		return entite;
	}
	/**
	 * [Processus.entite] Setter 
	 * @author rlaib on : 15 janv. 2015  10:22:11
	 * @param entite the entite to set
	 */
	public void setEntite(Entite entite) {
		this.entite = entite;
	}
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Processus.hashCode] Method 
	 * @author rlaib  on : 29 avr. 2014  11:47:45
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
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Processus.equals] Method 
	 * @author rlaib  on : 29 avr. 2014  11:47:45
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
		Processus other = (Processus) obj;
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
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Processus.toString] Method 
	 * @author rlaib  on : 29 avr. 2014  11:47:53
	 * @return
	 */
	@Override
	public String toString() {
		return "Processus [code=" + code + "]";
	}
	
	
}
