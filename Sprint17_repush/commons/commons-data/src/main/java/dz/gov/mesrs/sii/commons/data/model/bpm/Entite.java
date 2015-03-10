/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Action.java] 
 * @author rlaib on : 29 avr. 2014  08:54:09
 */
package dz.gov.mesrs.sii.commons.data.model.bpm;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaine;

/**
 * @author rlaib on : 29 avr. 2014 08:54:09
 */
@Entity
@Table(name = "entite", schema = "bpm")
public class Entite implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:18:32
	 */
	private static final long serialVersionUID = 3679193221849085748L;
	private int id;
	private String code;
	private String libelle;
	private RefDomaine refDomaine;
	public Entite() {
	}

	public Entite(String code, String libelle) {

		this.code = code;
		this.libelle = libelle;
	}

	/**
	 * [Action.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name = "entite_id_seq_gen", sequenceName = "bpm.entite_id_seq")
	@Id
	@GeneratedValue(generator = "entite_id_seq_gen")
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
	@Column(name = "code", nullable = false, length = 50)
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
	 * [Entite.libelle] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 09:40:08
	 * @return the libelle
	 */
	@Column(name = "libelle", nullable = false, length = 10)
	public String getLibelle() {
		return libelle;
	}

	/**
	 * [Entite.libelle] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 09:40:08
	 * @param libelle
	 *            the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	/**
	 * [Entite.refDomaine] Getter 
	 * @author rlaib on : 21 janv. 2015  16:15:51
	 * @return the refDomaine
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_domaine", nullable = false)
	public RefDomaine getRefDomaine() {
		return refDomaine;
	}

	/**
	 * [Entite.refDomaine] Setter 
	 * @author rlaib on : 21 janv. 2015  16:15:51
	 * @param refDomaine the refDomaine to set
	 */
	public void setRefDomaine(RefDomaine refDomaine) {
		this.refDomaine = refDomaine;
	}

	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Entite.hashCode]
	 * Method
	 * 
	 * @author rlaib on : 29 avr. 2014 10:19:31
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
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Entite.equals]
	 * Method
	 * 
	 * @author rlaib on : 29 avr. 2014 10:19:31
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
		Entite other = (Entite) obj;
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
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Entite.toString]
	 * Method
	 * 
	 * @author rlaib on : 29 avr. 2014 10:19:41
	 * @return
	 */
	@Override
	public String toString() {
		return "Entite [libelle=" + libelle + "]";
	}

}
