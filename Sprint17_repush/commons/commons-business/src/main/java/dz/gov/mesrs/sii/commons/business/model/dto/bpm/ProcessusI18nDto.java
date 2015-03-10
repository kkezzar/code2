/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

import dz.gov.mesrs.sii.commons.data.model.bpm.Processus;

/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class ProcessusI18nDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:49:56
	 */
	private static final long serialVersionUID = 3905891095882592550L;
	private int id;
	private String langue;
	private String libelle;
	private Processus processus;

	public ProcessusI18nDto() {
	}

	/**
	 * [ProcessusI18nDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:49:51
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [ProcessusI18nDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:49:51
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [ProcessusI18nDto.langue] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:49:51
	 * @return the langue
	 */
	public String getLangue() {
		return langue;
	}

	/**
	 * [ProcessusI18nDto.langue] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:49:51
	 * @param langue
	 *            the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}

	/**
	 * [ProcessusI18nDto.libelle] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:49:51
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * [ProcessusI18nDto.libelle] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:49:51
	 * @param libelle
	 *            the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * [ProcessusI18nDto.processus] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:49:51
	 * @return the processus
	 */
	public Processus getProcessus() {
		return processus;
	}

	/**
	 * [ProcessusI18nDto.processus] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:49:51
	 * @param processus
	 *            the processus to set
	 */
	public void setProcessus(Processus processus) {
		this.processus = processus;
	}

}
