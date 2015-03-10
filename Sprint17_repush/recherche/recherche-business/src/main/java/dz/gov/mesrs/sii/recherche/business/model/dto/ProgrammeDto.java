package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;
import java.util.Date;

public class ProgrammeDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:54:51
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String intituleFr;
	private String intituleAr;
	private Date dateDebut;
	private Date dateFin;
	
	// Type programme
	private int typeProgrammeId;
	private String typeProgrammeCode;
	private String typeProgrammeLibelle;

	// Circuit de validation
	private int circuitValidationId;
	private String circuitValidationCode;
	private String circuitValidationLibelle;
	
	public ProgrammeDto() {
	}

	/**
	 * [ProgrammeDto.id] Getter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * [ProgrammeDto.id] Setter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [ProgrammeDto.code] Getter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [ProgrammeDto.code] Setter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [ProgrammeDto.intituleFr] Getter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @return the intituleFr
	 */
	public String getIntituleFr() {
		return intituleFr;
	}

	/**
	 * [ProgrammeDto.intituleFr] Setter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @param intituleFr the intituleFr to set
	 */
	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	/**
	 * [ProgrammeDto.intituleAr] Getter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @return the intituleAr
	 */
	public String getIntituleAr() {
		return intituleAr;
	}

	/**
	 * [ProgrammeDto.intituleAr] Setter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @param intituleAr the intituleAr to set
	 */
	public void setIntituleAr(String intituleAr) {
		this.intituleAr = intituleAr;
	}

	/**
	 * [ProgrammeDto.dateDebut] Getter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * [ProgrammeDto.dateDebut] Setter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * [ProgrammeDto.dateFin] Getter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * [ProgrammeDto.dateFin] Setter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * [ProgrammeDto.typeProgrammeId] Getter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @return the typeProgrammeId
	 */
	public int getTypeProgrammeId() {
		return typeProgrammeId;
	}

	/**
	 * [ProgrammeDto.typeProgrammeId] Setter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @param typeProgrammeId the typeProgrammeId to set
	 */
	public void setTypeProgrammeId(int typeProgrammeId) {
		this.typeProgrammeId = typeProgrammeId;
	}

	/**
	 * [ProgrammeDto.typeProgrammeCode] Getter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @return the typeProgrammeCode
	 */
	public String getTypeProgrammeCode() {
		return typeProgrammeCode;
	}

	/**
	 * [ProgrammeDto.typeProgrammeCode] Setter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @param typeProgrammeCode the typeProgrammeCode to set
	 */
	public void setTypeProgrammeCode(String typeProgrammeCode) {
		this.typeProgrammeCode = typeProgrammeCode;
	}

	/**
	 * [ProgrammeDto.typeProgrammeLibelle] Getter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @return the typeProgrammeLibelle
	 */
	public String getTypeProgrammeLibelle() {
		return typeProgrammeLibelle;
	}

	/**
	 * [ProgrammeDto.typeProgrammeLibelle] Setter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @param typeProgrammeLibelle the typeProgrammeLibelle to set
	 */
	public void setTypeProgrammeLibelle(String typeProgrammeLibelle) {
		this.typeProgrammeLibelle = typeProgrammeLibelle;
	}

	/**
	 * [ProgrammeDto.circuitValidationId] Getter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @return the circuitValidationId
	 */
	public int getCircuitValidationId() {
		return circuitValidationId;
	}

	/**
	 * [ProgrammeDto.circuitValidationId] Setter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @param circuitValidationId the circuitValidationId to set
	 */
	public void setCircuitValidationId(int circuitValidationId) {
		this.circuitValidationId = circuitValidationId;
	}

	/**
	 * [ProgrammeDto.circuitValidationCode] Getter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @return the circuitValidationCode
	 */
	public String getCircuitValidationCode() {
		return circuitValidationCode;
	}

	/**
	 * [ProgrammeDto.circuitValidationCode] Setter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @param circuitValidationCode the circuitValidationCode to set
	 */
	public void setCircuitValidationCode(String circuitValidationCode) {
		this.circuitValidationCode = circuitValidationCode;
	}

	/**
	 * [ProgrammeDto.circuitValidationLibelle] Getter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @return the circuitValidationLibelle
	 */
	public String getCircuitValidationLibelle() {
		return circuitValidationLibelle;
	}

	/**
	 * [ProgrammeDto.circuitValidationLibelle] Setter 
	 * @author rlaib on : 25 janv. 2015  11:45:00
	 * @param circuitValidationLibelle the circuitValidationLibelle to set
	 */
	public void setCircuitValidationLibelle(String circuitValidationLibelle) {
		this.circuitValidationLibelle = circuitValidationLibelle;
	}


	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.ProgrammeDto.hashCode] Method 
	 * Overridden By : @author rlaib  on : 3 févr. 2015  12:28:14
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + circuitValidationId;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((intituleAr == null) ? 0 : intituleAr.hashCode());
		result = prime * result
				+ ((intituleFr == null) ? 0 : intituleFr.hashCode());
		result = prime * result + typeProgrammeId;
		return result;
	}

	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.ProgrammeDto.equals] Method 
	 * Overridden By : @author rlaib  on : 3 févr. 2015  12:28:14
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
		ProgrammeDto other = (ProgrammeDto) obj;
		if (circuitValidationId != other.circuitValidationId)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.equals(other.dateDebut))
			return false;
		if (dateFin == null) {
			if (other.dateFin != null)
				return false;
		} else if (!dateFin.equals(other.dateFin))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (intituleAr == null) {
			if (other.intituleAr != null)
				return false;
		} else if (!intituleAr.equals(other.intituleAr))
			return false;
		if (intituleFr == null) {
			if (other.intituleFr != null)
				return false;
		} else if (!intituleFr.equals(other.intituleFr))
			return false;
		if (typeProgrammeId != other.typeProgrammeId)
			return false;
		return true;
	}

	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.ProgrammeDto.toString] Method 
	 * Overridden By : @author rlaib  on : 25 janv. 2015  11:45:44
	 * @return
	 */
	@Override
	public String toString() {
		return "ProgrammeDto [code=" + code + ", intituleFr=" + intituleFr
				+ ", typeProgrammeCode=" + typeProgrammeCode + "]";
	}
	
	
}
