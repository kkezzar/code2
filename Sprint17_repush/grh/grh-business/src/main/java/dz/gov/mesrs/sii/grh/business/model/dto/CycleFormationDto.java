package dz.gov.mesrs.sii.grh.business.model.dto;


import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;

/**
 * 
 * @author BELDI Jamel
 *
 */

public class CycleFormationDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private SituationEntiteDto situationEntiteDto;
	private ProgrammeFormationGrhDto programmeFormationGrhDto;
	private String intitule;
	private Date dateDebut;
	private Date dateFin;
	private String objectif;
	private String code;
	private List<SessionFormationDto> sessionFormationDtos;
	private List<GradeCycleFormationDto> gradeCycleFormationDtos;

	public CycleFormationDto() {
	}

	public CycleFormationDto(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SituationEntiteDto getSituationEntiteDto() {
		return situationEntiteDto;
	}

	public void setSituationEntiteDto(SituationEntiteDto situationEntiteDto) {
		this.situationEntiteDto = situationEntiteDto;
	}

	public ProgrammeFormationGrhDto getProgrammeFormationGrhDto() {
		return programmeFormationGrhDto;
	}

	public void setProgrammeFormationGrhDto(
			ProgrammeFormationGrhDto programmeFormationGrhDto) {
		this.programmeFormationGrhDto = programmeFormationGrhDto;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<SessionFormationDto> getSessionFormationDtos() {
		return sessionFormationDtos;
	}

	public void setSessionFormationDtos(
			List<SessionFormationDto> sessionFormationDtos) {
		this.sessionFormationDtos = sessionFormationDtos;
	}

	public List<GradeCycleFormationDto> getGradeCycleFormationDtos() {
		return gradeCycleFormationDtos;
	}

	public void setGradeCycleFormationDtos(
			List<GradeCycleFormationDto> gradeCycleFormationDtos) {
		this.gradeCycleFormationDtos = gradeCycleFormationDtos;
	}

	
	

}
