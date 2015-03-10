/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDto.java] 
 * @author rlaib on : 16 juil. 2014  09:51:36
 */
package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import java.io.Serializable;

/**
 * @author rlaib  on : 16 juil. 2014  09:51:36
 */
public class NiveauDto implements Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 16 juil. 2014  09:53:51
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String libelleLongLt;
	private String libelleLongAr;
	private String libelleCourtLt;
	private String libelleCourtAr;
	private int rang;
	private int idCycle;
	private String libelleCourtLtCycle;
	private String libelleLongLtCycle;
	private String codeCycle;
	
	public NiveauDto() {
	}

	/**
	 * [NiveauDto.id] Getter 
	 * @author rlaib on : 16 juil. 2014  09:55:05
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [NiveauDto.id] Setter 
	 * @author rlaib on : 16 juil. 2014  09:55:05
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * [NiveauDto.code] Getter 
	 * @author rlaib on : 16 juil. 2014  09:55:05
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [NiveauDto.code] Setter 
	 * @author rlaib on : 16 juil. 2014  09:55:05
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [NiveauDto.libelleLongLt] Getter 
	 * @author rlaib on : 16 juil. 2014  09:55:05
	 * @return the libelleLongLt
	 */
	public String getLibelleLongLt() {
		return libelleLongLt;
	}

	/**
	 * [NiveauDto.libelleLongLt] Setter 
	 * @author rlaib on : 16 juil. 2014  09:55:05
	 * @param libelleLongLt the libelleLongLt to set
	 */
	public void setLibelleLongLt(String libelleLongLt) {
		this.libelleLongLt = libelleLongLt;
	}

	/**
	 * [NiveauDto.libelleLongAr] Getter 
	 * @author rlaib on : 16 juil. 2014  09:55:05
	 * @return the libelleLongAr
	 */
	public String getLibelleLongAr() {
		return libelleLongAr;
	}

	/**
	 * [NiveauDto.libelleLongAr] Setter 
	 * @author rlaib on : 16 juil. 2014  09:55:05
	 * @param libelleLongAr the libelleLongAr to set
	 */
	public void setLibelleLongAr(String libelleLongAr) {
		this.libelleLongAr = libelleLongAr;
	}

	/**
	 * [NiveauDto.libelleCourtLt] Getter 
	 * @author rlaib on : 16 juil. 2014  09:55:05
	 * @return the libelleCourtLt
	 */
	public String getLibelleCourtLt() {
		return libelleCourtLt;
	}

	/**
	 * [NiveauDto.libelleCourtLt] Setter 
	 * @author rlaib on : 16 juil. 2014  09:55:05
	 * @param libelleCourtLt the libelleCourtLt to set
	 */
	public void setLibelleCourtLt(String libelleCourtLt) {
		this.libelleCourtLt = libelleCourtLt;
	}

	/**
	 * [NiveauDto.libelleCourtAr] Getter 
	 * @author rlaib on : 16 juil. 2014  09:55:05
	 * @return the libelleCourtAr
	 */
	public String getLibelleCourtAr() {
		return libelleCourtAr;
	}

	/**
	 * [NiveauDto.libelleCourtAr] Setter 
	 * @author rlaib on : 16 juil. 2014  09:55:05
	 * @param libelleCourtAr the libelleCourtAr to set
	 */
	public void setLibelleCourtAr(String libelleCourtAr) {
		this.libelleCourtAr = libelleCourtAr;
	}

	/**
	 * [NiveauDto.rang] Getter 
	 * @author rlaib on : 16 juil. 2014  09:55:05
	 * @return the rang
	 */
	public int getRang() {
		return rang;
	}

	/**
	 * [NiveauDto.rang] Setter 
	 * @author rlaib on : 16 juil. 2014  09:55:05
	 * @param rang the rang to set
	 */
	public void setRang(int rang) {
		this.rang = rang;
	}

	/**
	 * [NiveauDto.idCycle] Getter 
	 * @author rlaib on : 16 juil. 2014  09:56:13
	 * @return the idCycle
	 */
	public int getIdCycle() {
		return idCycle;
	}

	/**
	 * [NiveauDto.idCycle] Setter 
	 * @author rlaib on : 16 juil. 2014  09:56:13
	 * @param idCycle the idCycle to set
	 */
	public void setIdCycle(int idCycle) {
		this.idCycle = idCycle;
	}

	/**
	 * [NiveauDto.libelleCourtLtCycle] Getter 
	 * @author MAKERRI Sofiane on : 12 nov. 2014  15:17:09
	 * @return the libelleCourtLtCycle
	 */
	public String getLibelleCourtLtCycle() {
		return libelleCourtLtCycle;
	}

	/**
	 * [NiveauDto.libelleCourtLtCycle] Setter 
	 * @author MAKERRI Sofiane on : 12 nov. 2014  15:17:09
	 * @param libelleCourtLtCycle the libelleCourtLtCycle to set
	 */
	public void setLibelleCourtLtCycle(String libelleCourtLtCycle) {
		this.libelleCourtLtCycle = libelleCourtLtCycle;
	}

	/**
	 * [NiveauDto.codeCycle] Getter 
	 * @author MAKERRI Sofiane on : 12 nov. 2014  15:17:09
	 * @return the codeCycle
	 */
	public String getCodeCycle() {
		return codeCycle;
	}

	/**
	 * [NiveauDto.codeCycle] Setter 
	 * @author MAKERRI Sofiane on : 12 nov. 2014  15:17:09
	 * @param codeCycle the codeCycle to set
	 */
	public void setCodeCycle(String codeCycle) {
		this.codeCycle = codeCycle;
	}

	/**
	 * [NiveauDto.libelleLongLtCycle] Getter 
	 * @author MAKERRI Sofiane on : 14 janv. 2015  14:07:15
	 * @return the libelleLongLtCycle
	 */
	public String getLibelleLongLtCycle() {
		return libelleLongLtCycle;
	}

	/**
	 * [NiveauDto.libelleLongLtCycle] Setter 
	 * @author MAKERRI Sofiane on : 14 janv. 2015  14:07:15
	 * @param libelleLongLtCycle the libelleLongLtCycle to set
	 */
	public void setLibelleLongLtCycle(String libelleLongLtCycle) {
		this.libelleLongLtCycle = libelleLongLtCycle;
	}
	
	
	
	
}
