/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

import java.util.HashSet;
import java.util.Set;

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;

/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class SituationDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:52:39
	 */
	private static final long serialVersionUID = 7727332663824484032L;
	private int id;
	private String code;
	private String styleCss;
	private String libelleFr;
	private String libelleAr;
	private Set<SituationEntite> situationEntites = new HashSet<SituationEntite>(0);

	public SituationDto() {
	}

	/**
	 * [SituationDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:52:35
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [SituationDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:52:35
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [SituationDto.code] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:52:35
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [SituationDto.code] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:52:35
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [SituationDto.styleCss] Getter
	 * 
	 * @author rlaib on : 20 oct. 2014 10:26:09
	 * @return the styleCss
	 */
	public String getStyleCss() {
		return styleCss;
	}

	/**
	 * [SituationDto.styleCss] Setter
	 * 
	 * @author rlaib on : 20 oct. 2014 10:26:09
	 * @param styleCss
	 *            the styleCss to set
	 */
	public void setStyleCss(String styleCss) {
		this.styleCss = styleCss;
	}

	/**
	 * [SituationDto.libelleFr] Getter 
	 * @author rlaib on : 7 janv. 2015  15:47:54
	 * @return the libelleFr
	 */
	public String getLibelleFr() {
		return libelleFr;
	}

	/**
	 * [SituationDto.libelleFr] Setter 
	 * @author rlaib on : 7 janv. 2015  15:47:54
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	/**
	 * [SituationDto.libelleAr] Getter 
	 * @author rlaib on : 7 janv. 2015  15:47:54
	 * @return the libelleAr
	 */
	public String getLibelleAr() {
		return libelleAr;
	}

	/**
	 * [SituationDto.libelleAr] Setter 
	 * @author rlaib on : 7 janv. 2015  15:47:54
	 * @param libelleAr the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}


	/**
	 * [SituationDto.situationEntites] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:52:35
	 * @return the situationEntites
	 */
	public Set<SituationEntite> getSituationEntites() {
		return situationEntites;
	}

	/**
	 * [SituationDto.situationEntites] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:52:35
	 * @param situationEntites
	 *            the situationEntites to set
	 */
	public void setSituationEntites(Set<SituationEntite> situationEntites) {
		this.situationEntites = situationEntites;
	}

}
