/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDto.java] 
 * @author rlaib on : 16 juil. 2014  09:51:36
 */
package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import java.io.Serializable;
import java.util.List;

/**
 * @author rlaib on : 16 juil. 2014 09:51:36
 */
public class CycleDto implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:51
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String libelleLongLt;
	private String libelleLongAr;
	private String libelleCourtLt;
	private String libelleCourtAr;
	private int rang;
	private boolean statut;
	private String refCodeTypeFormation;
	private String refCodeTypePassage;

	private List<NiveauDto> niveauxDto;

	private int nombreCycles;

	public CycleDto() {
	}

	/**
	 * [CycleDto.id] Getter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [CycleDto.id] Setter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [CycleDto.code] Getter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [CycleDto.code] Setter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [CycleDto.libelleLongLt] Getter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @return the libelleLongLt
	 */
	public String getLibelleLongLt() {
		return libelleLongLt;
	}

	/**
	 * [CycleDto.libelleLongLt] Setter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @param libelleLongLt
	 *            the libelleLongLt to set
	 */
	public void setLibelleLongLt(String libelleLongLt) {
		this.libelleLongLt = libelleLongLt;
	}

	/**
	 * [CycleDto.libelleLongAr] Getter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @return the libelleLongAr
	 */
	public String getLibelleLongAr() {
		return libelleLongAr;
	}

	/**
	 * [CycleDto.libelleLongAr] Setter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @param libelleLongAr
	 *            the libelleLongAr to set
	 */
	public void setLibelleLongAr(String libelleLongAr) {
		this.libelleLongAr = libelleLongAr;
	}

	/**
	 * [CycleDto.libelleCourtLt] Getter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @return the libelleCourtLt
	 */
	public String getLibelleCourtLt() {
		return libelleCourtLt;
	}

	/**
	 * [CycleDto.libelleCourtLt] Setter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @param libelleCourtLt
	 *            the libelleCourtLt to set
	 */
	public void setLibelleCourtLt(String libelleCourtLt) {
		this.libelleCourtLt = libelleCourtLt;
	}

	/**
	 * [CycleDto.libelleCourtAr] Getter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @return the libelleCourtAr
	 */
	public String getLibelleCourtAr() {
		return libelleCourtAr;
	}

	/**
	 * [CycleDto.libelleCourtAr] Setter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @param libelleCourtAr
	 *            the libelleCourtAr to set
	 */
	public void setLibelleCourtAr(String libelleCourtAr) {
		this.libelleCourtAr = libelleCourtAr;
	}

	/**
	 * [CycleDto.rang] Getter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @return the rang
	 */
	public int getRang() {
		return rang;
	}

	/**
	 * [CycleDto.rang] Setter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @param rang
	 *            the rang to set
	 */
	public void setRang(int rang) {
		this.rang = rang;
	}

	/**
	 * [CycleDto.statut] Getter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @return the statut
	 */
	public boolean isStatut() {
		return statut;
	}

	/**
	 * [CycleDto.statut] Setter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @param statut
	 *            the statut to set
	 */
	public void setStatut(boolean statut) {
		this.statut = statut;
	}

	/**
	 * [CycleDto.refCodeTypeFormation] Getter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @return the refCodeTypeFormation
	 */
	public String getRefCodeTypeFormation() {
		return refCodeTypeFormation;
	}

	/**
	 * [CycleDto.refCodeTypeFormation] Setter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @param refCodeTypeFormation
	 *            the refCodeTypeFormation to set
	 */
	public void setRefCodeTypeFormation(String refCodeTypeFormation) {
		this.refCodeTypeFormation = refCodeTypeFormation;
	}

	/**
	 * [CycleDto.refCodeTypePassage] Getter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @return the refCodeTypePassage
	 */
	public String getRefCodeTypePassage() {
		return refCodeTypePassage;
	}

	/**
	 * [CycleDto.refCodeTypePassage] Setter
	 * 
	 * @author rlaib on : 16 juil. 2014 09:53:40
	 * @param refCodeTypePassage
	 *            the refCodeTypePassage to set
	 */
	public void setRefCodeTypePassage(String refCodeTypePassage) {
		this.refCodeTypePassage = refCodeTypePassage;
	}

	public int getNombreCycles() {
		return nombreCycles;
	}

	public void setNombreCycles(int nombreCycles) {
		this.nombreCycles = nombreCycles;
	}

	public List<NiveauDto> getNiveauxDto() {
		return niveauxDto;
	}

	public void setNiveauxDto(List<NiveauDto> niveauxDto) {
		this.niveauxDto = niveauxDto;
	}

}
