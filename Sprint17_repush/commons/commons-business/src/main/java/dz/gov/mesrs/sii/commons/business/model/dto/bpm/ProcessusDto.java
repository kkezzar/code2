/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;
/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class ProcessusDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:49:06
	 */
	private static final long serialVersionUID = -7836409474903910023L;
	private int id;
	private String code;
	private String libelleFr;
	private String libelleAr;
	// Entite
	private Integer entiteId;
	private String entiteCode;
	private String entiteLibelleFr;
	

	public ProcessusDto() {
	}

	/**
	 * [ProcessusDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:49:01
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [ProcessusDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:49:01
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [ProcessusDto.code] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:49:01
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [ProcessusDto.code] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:49:01
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [ProcessusDto.libelleFr] Getter 
	 * @author rlaib on : 15 janv. 2015  11:38:54
	 * @return the libelleFr
	 */
	public String getLibelleFr() {
		return libelleFr;
	}

	/**
	 * [ProcessusDto.libelleFr] Setter 
	 * @author rlaib on : 15 janv. 2015  11:38:54
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	/**
	 * [ProcessusDto.libelleAr] Getter 
	 * @author rlaib on : 15 janv. 2015  11:38:54
	 * @return the libelleAr
	 */
	public String getLibelleAr() {
		return libelleAr;
	}

	/**
	 * [ProcessusDto.libelleAr] Setter 
	 * @author rlaib on : 15 janv. 2015  11:38:54
	 * @param libelleAr the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	/**
	 * [ProcessusDto.entiteId] Getter 
	 * @author rlaib on : 15 janv. 2015  11:38:54
	 * @return the entiteId
	 */
	public Integer getEntiteId() {
		return entiteId;
	}

	/**
	 * [ProcessusDto.entiteId] Setter 
	 * @author rlaib on : 15 janv. 2015  11:38:54
	 * @param entiteId the entiteId to set
	 */
	public void setEntiteId(Integer entiteId) {
		this.entiteId = entiteId;
	}

	/**
	 * [ProcessusDto.entiteCode] Getter 
	 * @author rlaib on : 15 janv. 2015  11:38:54
	 * @return the entiteCode
	 */
	public String getEntiteCode() {
		return entiteCode;
	}

	/**
	 * [ProcessusDto.entiteCode] Setter 
	 * @author rlaib on : 15 janv. 2015  11:38:54
	 * @param entiteCode the entiteCode to set
	 */
	public void setEntiteCode(String entiteCode) {
		this.entiteCode = entiteCode;
	}

	/**
	 * [ProcessusDto.entiteLibelleFr] Getter 
	 * @author rlaib on : 15 janv. 2015  11:38:54
	 * @return the entiteLibelleFr
	 */
	public String getEntiteLibelleFr() {
		return entiteLibelleFr;
	}

	/**
	 * [ProcessusDto.entiteLibelleFr] Setter 
	 * @author rlaib on : 15 janv. 2015  11:38:54
	 * @param entiteLibelleFr the entiteLibelleFr to set
	 */
	public void setEntiteLibelleFr(String entiteLibelleFr) {
		this.entiteLibelleFr = entiteLibelleFr;
	}

	/**
	 * [dz.gov.mesrs.sii.commons.business.model.dto.bpm.ProcessusDto.hashCode] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  11:39:03
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((entiteCode == null) ? 0 : entiteCode.hashCode());
		result = prime * result
				+ ((entiteId == null) ? 0 : entiteId.hashCode());
		result = prime * result
				+ ((entiteLibelleFr == null) ? 0 : entiteLibelleFr.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((libelleAr == null) ? 0 : libelleAr.hashCode());
		result = prime * result
				+ ((libelleFr == null) ? 0 : libelleFr.hashCode());
		return result;
	}

	/**
	 * [dz.gov.mesrs.sii.commons.business.model.dto.bpm.ProcessusDto.equals] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  11:39:03
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
		ProcessusDto other = (ProcessusDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (entiteCode == null) {
			if (other.entiteCode != null)
				return false;
		} else if (!entiteCode.equals(other.entiteCode))
			return false;
		if (entiteId == null) {
			if (other.entiteId != null)
				return false;
		} else if (!entiteId.equals(other.entiteId))
			return false;
		if (entiteLibelleFr == null) {
			if (other.entiteLibelleFr != null)
				return false;
		} else if (!entiteLibelleFr.equals(other.entiteLibelleFr))
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

	/**
	 * [dz.gov.mesrs.sii.commons.business.model.dto.bpm.ProcessusDto.toString] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  11:39:10
	 * @return
	 */
	@Override
	public String toString() {
		return "ProcessusDto [code=" + code + ", libelleFr=" + libelleFr + "]";
	}

	

}
