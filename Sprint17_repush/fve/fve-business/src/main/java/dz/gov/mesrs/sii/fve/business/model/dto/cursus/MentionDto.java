/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.cursus.MentionDto.java] 
 * @author MAKERRI Sofiane on : 22 oct. 2014  15:44:59
 */
package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.io.Serializable;

/**
 * @author MAKERRI Sofiane  on : 22 oct. 2014  15:44:59
 */
public class MentionDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 22 oct. 2014  15:45:08
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer anneeAcademiqueId;
	private double noteMin;
	private double noteMax;
	private Integer ncMentionId;
	private String ncMentionCode;
	private String ncMentionLibelleLongFr;
	private String ncMentionLibelleLongAr;
	private String ncMentionLibelleCourtFr;
	private String ncMentionLibelleCourtAr;

	/**
	 * [MentionDto.MentionDto()] Constructor
	 * @author MAKERRI Sofiane  on : 22 oct. 2014  15:44:59	
	 */
	public MentionDto() {
		super();
	}

	/**
	 * [MentionDto.id] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [MentionDto.id] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [MentionDto.anneeAcademiqueId] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [MentionDto.anneeAcademiqueId] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @param anneeAcademiqueId the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [MentionDto.noteMin] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @return the noteMin
	 */
	public double getNoteMin() {
		return noteMin;
	}

	/**
	 * [MentionDto.noteMin] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @param noteMin the noteMin to set
	 */
	public void setNoteMin(double noteMin) {
		this.noteMin = noteMin;
	}

	/**
	 * [MentionDto.noteMax] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @return the noteMax
	 */
	public double getNoteMax() {
		return noteMax;
	}

	/**
	 * [MentionDto.noteMax] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @param noteMax the noteMax to set
	 */
	public void setNoteMax(double noteMax) {
		this.noteMax = noteMax;
	}

	/**
	 * [MentionDto.ncMentionId] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @return the ncMentionId
	 */
	public Integer getNcMentionId() {
		return ncMentionId;
	}

	/**
	 * [MentionDto.ncMentionId] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @param ncMentionId the ncMentionId to set
	 */
	public void setNcMentionId(Integer ncMentionId) {
		this.ncMentionId = ncMentionId;
	}

	/**
	 * [MentionDto.ncMentionCode] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @return the ncMentionCode
	 */
	public String getNcMentionCode() {
		return ncMentionCode;
	}

	/**
	 * [MentionDto.ncMentionCode] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @param ncMentionCode the ncMentionCode to set
	 */
	public void setNcMentionCode(String ncMentionCode) {
		this.ncMentionCode = ncMentionCode;
	}

	/**
	 * [MentionDto.ncMentionLibelleLongFr] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @return the ncMentionLibelleLongFr
	 */
	public String getNcMentionLibelleLongFr() {
		return ncMentionLibelleLongFr;
	}

	/**
	 * [MentionDto.ncMentionLibelleLongFr] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @param ncMentionLibelleLongFr the ncMentionLibelleLongFr to set
	 */
	public void setNcMentionLibelleLongFr(String ncMentionLibelleLongFr) {
		this.ncMentionLibelleLongFr = ncMentionLibelleLongFr;
	}

	/**
	 * [MentionDto.ncMentionLibelleLongAr] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @return the ncMentionLibelleLongAr
	 */
	public String getNcMentionLibelleLongAr() {
		return ncMentionLibelleLongAr;
	}

	/**
	 * [MentionDto.ncMentionLibelleLongAr] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @param ncMentionLibelleLongAr the ncMentionLibelleLongAr to set
	 */
	public void setNcMentionLibelleLongAr(String ncMentionLibelleLongAr) {
		this.ncMentionLibelleLongAr = ncMentionLibelleLongAr;
	}

	/**
	 * [MentionDto.ncMentionLibelleCourtFr] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @return the ncMentionLibelleCourtFr
	 */
	public String getNcMentionLibelleCourtFr() {
		return ncMentionLibelleCourtFr;
	}

	/**
	 * [MentionDto.ncMentionLibelleCourtFr] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @param ncMentionLibelleCourtFr the ncMentionLibelleCourtFr to set
	 */
	public void setNcMentionLibelleCourtFr(String ncMentionLibelleCourtFr) {
		this.ncMentionLibelleCourtFr = ncMentionLibelleCourtFr;
	}

	/**
	 * [MentionDto.ncMentionLibelleCourtAr] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @return the ncMentionLibelleCourtAr
	 */
	public String getNcMentionLibelleCourtAr() {
		return ncMentionLibelleCourtAr;
	}

	/**
	 * [MentionDto.ncMentionLibelleCourtAr] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:54:54
	 * @param ncMentionLibelleCourtAr the ncMentionLibelleCourtAr to set
	 */
	public void setNcMentionLibelleCourtAr(String ncMentionLibelleCourtAr) {
		this.ncMentionLibelleCourtAr = ncMentionLibelleCourtAr;
	}

	
	

}
