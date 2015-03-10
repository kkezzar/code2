/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDto.java] 
 * @author rlaib on : 16 juil. 2014  09:51:36
 */
package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import java.io.Serializable;

/**
 * @author rlaib  on : 16 juil. 2014  09:51:36
 */
public class PeriodeDto implements Serializable {
	
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
	private int credit;
	private String refCodePeriodicite;
	private int idNiveau;
	private int rangNiveau;
	private String refCodePeriode;
	private String libelleLongFrNiveau;
	private String libelleLongFrCycle;
	
	// Periode de l annee (nomeclature)
	private Integer ncPeriodeId;
	private String ncPeriodeCode;
	private String ncPeriodeLibelle;
	
	
	public PeriodeDto() {
	}

	/**
	 * [PeriodeDto.id] Getter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [PeriodeDto.id] Setter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * [PeriodeDto.code] Getter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [PeriodeDto.code] Setter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [PeriodeDto.libelleLongLt] Getter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @return the libelleLongLt
	 */
	public String getLibelleLongLt() {
		return libelleLongLt;
	}

	/**
	 * [PeriodeDto.libelleLongLt] Setter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @param libelleLongLt the libelleLongLt to set
	 */
	public void setLibelleLongLt(String libelleLongLt) {
		this.libelleLongLt = libelleLongLt;
	}

	/**
	 * [PeriodeDto.libelleLongAr] Getter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @return the libelleLongAr
	 */
	public String getLibelleLongAr() {
		return libelleLongAr;
	}

	/**
	 * [PeriodeDto.libelleLongAr] Setter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @param libelleLongAr the libelleLongAr to set
	 */
	public void setLibelleLongAr(String libelleLongAr) {
		this.libelleLongAr = libelleLongAr;
	}

	/**
	 * [PeriodeDto.libelleCourtLt] Getter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @return the libelleCourtLt
	 */
	public String getLibelleCourtLt() {
		return libelleCourtLt;
	}

	/**
	 * [PeriodeDto.libelleCourtLt] Setter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @param libelleCourtLt the libelleCourtLt to set
	 */
	public void setLibelleCourtLt(String libelleCourtLt) {
		this.libelleCourtLt = libelleCourtLt;
	}

	/**
	 * [PeriodeDto.libelleCourtAr] Getter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @return the libelleCourtAr
	 */
	public String getLibelleCourtAr() {
		return libelleCourtAr;
	}

	/**
	 * [PeriodeDto.libelleCourtAr] Setter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @param libelleCourtAr the libelleCourtAr to set
	 */
	public void setLibelleCourtAr(String libelleCourtAr) {
		this.libelleCourtAr = libelleCourtAr;
	}

	/**
	 * [PeriodeDto.rang] Getter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @return the rang
	 */
	public int getRang() {
		return rang;
	}

	/**
	 * [PeriodeDto.rang] Setter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @param rang the rang to set
	 */
	public void setRang(int rang) {
		this.rang = rang;
	}

	/**
	 * [PeriodeDto.credit] Getter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @return the credit
	 */
	public int getCredit() {
		return credit;
	}

	/**
	 * [PeriodeDto.credit] Setter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @param credit the credit to set
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}

	/**
	 * [PeriodeDto.refCodePeriodicite] Getter 
	 * @author rlaib on : 22 juil. 2014  12:43:24
	 * @return the refCodePeriodicite
	 */
	public String getRefCodePeriodicite() {
		return refCodePeriodicite;
	}

	/**
	 * [PeriodeDto.refCodePeriodicite] Setter 
	 * @author rlaib on : 22 juil. 2014  12:43:24
	 * @param refCodePeriodicite the refCodePeriodicite to set
	 */
	public void setRefCodePeriodicite(String refCodePeriodicite) {
		this.refCodePeriodicite = refCodePeriodicite;
	}

	/**
	 * [PeriodeDto.idNiveau] Getter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @return the idNiveau
	 */
	public int getIdNiveau() {
		return idNiveau;
	}

	/**
	 * [PeriodeDto.idNiveau] Setter 
	 * @author rlaib on : 16 juil. 2014  09:57:27
	 * @param idNiveau the idNiveau to set
	 */
	public void setIdNiveau(int idNiveau) {
		this.idNiveau = idNiveau;
	}

	/**
	 * [PeriodeDto.refCodePeriode] Getter 
	 * @author rlaib on : 2 nov. 2014  09:44:57
	 * @return the refCodePeriode
	 */
	public String getRefCodePeriode() {
		return refCodePeriode;
	}

	/**
	 * [PeriodeDto.refCodePeriode] Setter 
	 * @author rlaib on : 2 nov. 2014  09:44:57
	 * @param refCodePeriode the refCodePeriode to set
	 */
	public void setRefCodePeriode(String refCodePeriode) {
		this.refCodePeriode = refCodePeriode;
	}

	
	/**
	 * [PeriodeDto.ncPeriodeId] Getter 
	 * @author rlaib on : 7 déc. 2014  13:25:51
	 * @return the ncPeriodeId
	 */
	public Integer getNcPeriodeId() {
		return ncPeriodeId;
	}

	/**
	 * [PeriodeDto.ncPeriodeId] Setter 
	 * @author rlaib on : 7 déc. 2014  13:25:51
	 * @param ncPeriodeId the ncPeriodeId to set
	 */
	public void setNcPeriodeId(Integer ncPeriodeId) {
		this.ncPeriodeId = ncPeriodeId;
	}

	/**
	 * [PeriodeDto.ncPeriodeCode] Getter 
	 * @author rlaib on : 7 déc. 2014  13:25:51
	 * @return the ncPeriodeCode
	 */
	public String getNcPeriodeCode() {
		return ncPeriodeCode;
	}

	/**
	 * [PeriodeDto.ncPeriodeCode] Setter 
	 * @author rlaib on : 7 déc. 2014  13:25:51
	 * @param ncPeriodeCode the ncPeriodeCode to set
	 */
	public void setNcPeriodeCode(String ncPeriodeCode) {
		this.ncPeriodeCode = ncPeriodeCode;
	}

	/**
	 * [PeriodeDto.ncPeriodeLibelle] Getter 
	 * @author rlaib on : 7 déc. 2014  13:25:51
	 * @return the ncPeriodeLibelle
	 */
	public String getNcPeriodeLibelle() {
		return ncPeriodeLibelle;
	}

	/**
	 * [PeriodeDto.ncPeriodeLibelle] Setter 
	 * @author rlaib on : 7 déc. 2014  13:25:51
	 * @param ncPeriodeLibelle the ncPeriodeLibelle to set
	 */
	public void setNcPeriodeLibelle(String ncPeriodeLibelle) {
		this.ncPeriodeLibelle = ncPeriodeLibelle;
	}

	/**
	 * [PeriodeDto.rangNiveau] Getter 
	 * @author MAKERRI Sofiane on : 28 janv. 2015  11:37:45
	 * @return the rangNiveau
	 */
	public int getRangNiveau() {
		return rangNiveau;
	}

	/**
	 * [PeriodeDto.rangNiveau] Setter 
	 * @author MAKERRI Sofiane on : 28 janv. 2015  11:37:45
	 * @param rangNiveau the rangNiveau to set
	 */
	public void setRangNiveau(int rangNiveau) {
		this.rangNiveau = rangNiveau;
	}

	/**
	 * [PeriodeDto.libelleLongFrNiveau] Getter 
	 * @author MAKERRI Sofiane on : 11 févr. 2015  14:26:45
	 * @return the libelleLongFrNiveau
	 */
	public String getLibelleLongFrNiveau() {
		return libelleLongFrNiveau;
	}

	/**
	 * [PeriodeDto.libelleLongFrNiveau] Setter 
	 * @author MAKERRI Sofiane on : 11 févr. 2015  14:26:45
	 * @param libelleLongFrNiveau the libelleLongFrNiveau to set
	 */
	public void setLibelleLongFrNiveau(String libelleLongFrNiveau) {
		this.libelleLongFrNiveau = libelleLongFrNiveau;
	}

	/**
	 * [PeriodeDto.libelleLongFrCycle] Getter 
	 * @author MAKERRI Sofiane on : 11 févr. 2015  16:01:47
	 * @return the libelleLongFrCycle
	 */
	public String getLibelleLongFrCycle() {
		return libelleLongFrCycle;
	}

	/**
	 * [PeriodeDto.libelleLongFrCycle] Setter 
	 * @author MAKERRI Sofiane on : 11 févr. 2015  16:01:47
	 * @param libelleLongFrCycle the libelleLongFrCycle to set
	 */
	public void setLibelleLongFrCycle(String libelleLongFrCycle) {
		this.libelleLongFrCycle = libelleLongFrCycle;
	}
	
	
	
}
