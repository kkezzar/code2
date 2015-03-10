/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;


/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class ActionDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 16:02:38
	 */
	private static final long serialVersionUID = -7594826718131528952L;
	private int id;
	private String code;
	private String libelleFr;
	private String libelleAr;
	
	public ActionDto() {
	}

	public ActionDto(int id) {
		this.id = id;
	}

	/**
	 * [ActionDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:33:47
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [ActionDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:33:47
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [ActionDto.code] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:33:47
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [ActionDto.code] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:33:47
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [ActionDto.libelleFr] Getter 
	 * @author rlaib on : 15 janv. 2015  11:35:29
	 * @return the libelleFr
	 */
	public String getLibelleFr() {
		return libelleFr;
	}

	/**
	 * [ActionDto.libelleFr] Setter 
	 * @author rlaib on : 15 janv. 2015  11:35:29
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	/**
	 * [ActionDto.libelleAr] Getter 
	 * @author rlaib on : 15 janv. 2015  11:35:29
	 * @return the libelleAr
	 */
	public String getLibelleAr() {
		return libelleAr;
	}

	/**
	 * [ActionDto.libelleAr] Setter 
	 * @author rlaib on : 15 janv. 2015  11:35:29
	 * @param libelleAr the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	/**
	 * [dz.gov.mesrs.sii.commons.business.model.dto.bpm.ActionDto.toString] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  11:35:59
	 * @return
	 */
	@Override
	public String toString() {
		return "ActionDto [code=" + code + ", libelleFr=" + libelleFr + "]";
	}

	/**
	 * [dz.gov.mesrs.sii.commons.business.model.dto.bpm.ActionDto.hashCode] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  11:36:05
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((libelleAr == null) ? 0 : libelleAr.hashCode());
		result = prime * result
				+ ((libelleFr == null) ? 0 : libelleFr.hashCode());
		return result;
	}

	/**
	 * [dz.gov.mesrs.sii.commons.business.model.dto.bpm.ActionDto.equals] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  11:36:05
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
		ActionDto other = (ActionDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id != other.id)
			return false;
		if (libelleAr == null) {
			if (other.libelleAr != null)
				return false;
		} else if (!libelleAr.equals(other.libelleAr))
			return false;
		if (libelleFr == null) {
			if (other.libelleFr != null)
				return false;
		} else if (!libelleFr.equals(other.libelleFr))
			return false;
		return true;
	}
	
	
}
