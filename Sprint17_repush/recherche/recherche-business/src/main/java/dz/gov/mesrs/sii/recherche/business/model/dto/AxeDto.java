/**
 * [dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto.java] 
 * @author rlaib on : 16 déc. 2014  13:24:10
 */
package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;


/**
 * @author rlaib  on : 16 déc. 2014  13:24:10
 */
public class AxeDto implements Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:55:29
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String description;
	
	// Axe recherche Nomenclature
	private Integer axeNomenclatureId;
	private String axeNomenclatureCode;
	private String axeNomenclatureLibelleFr;
	
	// Axe recherche Nomenclature
	private Long programmeId;
	private String programmeCode;
	private String programmeLibelleFr;
	
	public AxeDto() {
	}

	/**
	 * [AxeDto.id] Getter 
	 * @author rlaib on : 27 janv. 2015  12:28:52
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [AxeDto.id] Setter 
	 * @author rlaib on : 27 janv. 2015  12:28:52
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [AxeDto.description] Getter 
	 * @author rlaib on : 27 janv. 2015  12:28:52
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * [AxeDto.description] Setter 
	 * @author rlaib on : 27 janv. 2015  12:28:52
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [AxeDto.axeNomenclatureId] Getter 
	 * @author rlaib on : 27 janv. 2015  12:28:52
	 * @return the axeNomenclatureId
	 */
	public Integer getAxeNomenclatureId() {
		return axeNomenclatureId;
	}

	/**
	 * [AxeDto.axeNomenclatureId] Setter 
	 * @author rlaib on : 27 janv. 2015  12:28:52
	 * @param axeNomenclatureId the axeNomenclatureId to set
	 */
	public void setAxeNomenclatureId(Integer axeNomenclatureId) {
		this.axeNomenclatureId = axeNomenclatureId;
	}

	/**
	 * [AxeDto.axeNomenclatureCode] Getter 
	 * @author rlaib on : 27 janv. 2015  12:28:52
	 * @return the axeNomenclatureCode
	 */
	public String getAxeNomenclatureCode() {
		return axeNomenclatureCode;
	}

	/**
	 * [AxeDto.axeNomenclatureCode] Setter 
	 * @author rlaib on : 27 janv. 2015  12:28:52
	 * @param axeNomenclatureCode the axeNomenclatureCode to set
	 */
	public void setAxeNomenclatureCode(String axeNomenclatureCode) {
		this.axeNomenclatureCode = axeNomenclatureCode;
	}

	/**
	 * [AxeDto.axeNomenclatureLibelleFr] Getter 
	 * @author rlaib on : 27 janv. 2015  12:28:52
	 * @return the axeNomenclatureLibelleFr
	 */
	public String getAxeNomenclatureLibelleFr() {
		return axeNomenclatureLibelleFr;
	}

	/**
	 * [AxeDto.axeNomenclatureLibelleFr] Setter 
	 * @author rlaib on : 27 janv. 2015  12:28:52
	 * @param axeNomenclatureLibelleFr the axeNomenclatureLibelleFr to set
	 */
	public void setAxeNomenclatureLibelleFr(String axeNomenclatureLibelleFr) {
		this.axeNomenclatureLibelleFr = axeNomenclatureLibelleFr;
	}

	/**
	 * [AxeDto.programmeId] Getter 
	 * @author rlaib on : 27 janv. 2015  12:39:56
	 * @return the programmeId
	 */
	public Long getProgrammeId() {
		return programmeId;
	}
	

	/**
	 * [AxeDto.programmeId] Setter 
	 * @author rlaib on : 27 janv. 2015  12:39:56
	 * @param programmeId the programmeId to set
	 */
	public void setProgrammeId(Long programmeId) {
		this.programmeId = programmeId;
	}
	

	/**
	 * [AxeDto.programmeCode] Getter 
	 * @author rlaib on : 27 janv. 2015  12:39:56
	 * @return the programmeCode
	 */
	public String getProgrammeCode() {
		return programmeCode;
	}
	

	/**
	 * [AxeDto.programmeCode] Setter 
	 * @author rlaib on : 27 janv. 2015  12:39:56
	 * @param programmeCode the programmeCode to set
	 */
	public void setProgrammeCode(String programmeCode) {
		this.programmeCode = programmeCode;
	}
	

	/**
	 * [AxeDto.programmeLibelleFr] Getter 
	 * @author rlaib on : 27 janv. 2015  12:39:56
	 * @return the programmeLibelleFr
	 */
	public String getProgrammeLibelleFr() {
		return programmeLibelleFr;
	}
	

	/**
	 * [AxeDto.programmeLibelleFr] Setter 
	 * @author rlaib on : 27 janv. 2015  12:39:56
	 * @param programmeLibelleFr the programmeLibelleFr to set
	 */
	public void setProgrammeLibelleFr(String programmeLibelleFr) {
		this.programmeLibelleFr = programmeLibelleFr;
	}
	

	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.AxeDto.hashCode] Method 
	 * Overridden By : @author rlaib  on : 28 janv. 2015  11:35:01
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((axeNomenclatureCode == null) ? 0 : axeNomenclatureCode
						.hashCode());
		result = prime
				* result
				+ ((axeNomenclatureId == null) ? 0 : axeNomenclatureId
						.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((programmeId == null) ? 0 : programmeId.hashCode());
		return result;
	}

	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.AxeDto.equals] Method 
	 * Overridden By : @author rlaib  on : 28 janv. 2015  11:35:01
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
		AxeDto other = (AxeDto) obj;
		if (axeNomenclatureCode == null) {
			if (other.axeNomenclatureCode != null)
				return false;
		} else if (!axeNomenclatureCode.equals(other.axeNomenclatureCode))
			return false;
		if (axeNomenclatureId == null) {
			if (other.axeNomenclatureId != null)
				return false;
		} else if (!axeNomenclatureId.equals(other.axeNomenclatureId))
			return false;
		if (id != other.id)
			return false;
		if (programmeId == null) {
			if (other.programmeId != null)
				return false;
		} else if (!programmeId.equals(other.programmeId))
			return false;
		return true;
	}

	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.AxeDto.toString] Method 
	 * Overridden By : @author rlaib  on : 27 janv. 2015  12:29:52
	 * @return
	 */
	@Override
	public String toString() {
		return "AxeDto [id=" + id + ", description=" + description
				+ ", axeNomenclatureLibelleFr=" + axeNomenclatureLibelleFr
				+ "]";
	}
	
}
