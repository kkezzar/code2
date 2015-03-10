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
@Table(name = "etape_i18n", schema = "bpm")
public class EtapeI18n implements java.io.Serializable {
	
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:19:02
	 */
	private static final long serialVersionUID = 2660771439864617535L;
	private int id;
	private String langue;
	private String libelle;
	private Etape etape;

	public EtapeI18n() {
	}
	/**
	 * [EtapeI18n.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="etape_i18n_id_seq_gen", sequenceName="bpm.etape_i18n_id_seq")
	@Id @GeneratedValue(generator="etape_i18n_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}
	/**
	 * [EtapeI18n.id] Setter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * [EtapeI18n.langue] Getter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @return the langue
	 */
	@Column(name = "langue", nullable = false,length=5)
	public String getLangue() {
		return langue;
	}
	/**
	 * [EtapeI18n.langue] Setter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @param langue the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}
	/**
	 * [EtapeI18n.libelle] Getter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @return the libelle
	 */
	@Column(name = "libelle", nullable = false,length=255)
	public String getLibelle() {
		return libelle;
	}
	/**
	 * [EtapeI18n.libelle] Setter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * [EtapeI18n.etape] Getter 
	 * @author rlaib on : 29 avr. 2014  12:09:15
	 * @return the etape
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_etape", nullable = false)
	public Etape getEtape() {
		return etape;
	}
	/**
	 * [EtapeI18n.etape] Setter 
	 * @author rlaib on : 29 avr. 2014  12:09:15
	 * @param etape the etape to set
	 */
	public void setEtape(Etape etape) {
		this.etape = etape;
	}
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.EtapeI18n.hashCode] Method 
	 * @author rlaib  on : 29 avr. 2014  12:11:08
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
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.EtapeI18n.equals] Method 
	 * @author rlaib  on : 29 avr. 2014  12:11:08
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
		EtapeI18n other = (EtapeI18n) obj;
		if (id != other.id)
			return false;
		return true;
	}
	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.EtapeI18n.toString] Method 
	 * @author rlaib  on : 29 avr. 2014  12:11:18
	 * @return
	 */
	@Override
	public String toString() {
		return "EtapeI18n [langue=" + langue + ", libelle=" + libelle + "]";
	}
	
	
}
