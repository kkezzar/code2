package dz.gov.mesrs.sii.grh.business.model.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

public class PropostionAvancementDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private RefEtablissementDto refEtablissementDto;
	private NomenclatureDto moisDto;
	private Integer annee;
	private String description;
    private String intitule;	
	private Date dateProposition;
	private List<EmployeProposeDto> employeProposesDto = new ArrayList<EmployeProposeDto>();
	private Date dateCommission;
	private Date dateDecision;
	private SituationEntiteDto situationDto;
	private NomenclatureDto nomenclatureByTypeAvancementDto;
	private Integer selectedMoisId;
	
	public PropostionAvancementDto() {
	}

	public PropostionAvancementDto(Long id) {
		this.id = id;
	}

	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	/**
	 * [PropostionAvancementDto.refEtablissementDto] Getter 
	 * @author obelbrik on : 4 déc. 2014  12:23:25
	 * @return the refEtablissementDto
	 */
	public RefEtablissementDto getRefEtablissementDto() {
		return refEtablissementDto;
	}

	/**
	 * [PropostionAvancementDto.refEtablissementDto] Setter 
	 * @author obelbrik on : 4 déc. 2014  12:23:25
	 * @param refEtablissementDto the refEtablissementDto to set
	 */
	public void setRefEtablissementDto(RefEtablissementDto refEtablissementDto) {
		this.refEtablissementDto = refEtablissementDto;
	}

	
	public NomenclatureDto getMoisDto() {
		return moisDto;
	}

	public void setMoisDto(NomenclatureDto moisDto) {
		this.moisDto = moisDto;
	}

	/**
	 * @return the annee
	 */
	public Integer getAnnee() {
		return annee;
	}

	/**
	 * @param annee the annee to set
	 */
	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	/**
	 * [PropostionAvancementDto.intitule] Getter 
	 * @author obelbrik on : 4 déc. 2014  09:21:57
	 * @return the intitule
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * [PropostionAvancementDto.intitule] Setter 
	 * @author obelbrik on : 4 déc. 2014  09:21:57
	 * @param intitule the intitule to set
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	/**
	 * [PropostionAvancementDto.dateProposition] Getter 
	 * @author obelbrik on : 4 déc. 2014  09:21:57
	 * @return the dateProposition
	 */
	public Date getDateProposition() {
		return dateProposition;
	}

	/**
	 * [PropostionAvancementDto.dateProposition] Setter 
	 * @author obelbrik on : 4 déc. 2014  09:21:57
	 * @param dateProposition the dateProposition to set
	 */
	public void setDateProposition(Date dateProposition) {
		this.dateProposition = dateProposition;
	}

	/**
	 * [PropostionAvancementDto.employeProposes] Getter 
	 * @author obelbrik on : 14 déc. 2014  13:27:36
	 * @return the employeProposes
	 */
	public List<EmployeProposeDto> getEmployeProposesDto() {
		return employeProposesDto;
	}

	/**
	 * [PropostionAvancementDto.employeProposes] Setter 
	 * @author obelbrik on : 14 déc. 2014  13:27:36
	 * @param employeProposes the employeProposes to set
	 */
	public void setEmployeProposesDto(List<EmployeProposeDto> employeProposesDto) {
		this.employeProposesDto = employeProposesDto;
	}

	/**
	 * [PropostionAvancementDto.dateCommission] Getter 
	 * @author obelbrik on : 17 déc. 2014  11:23:23
	 * @return the dateCommission
	 */
	public Date getDateCommission() {
		return dateCommission;
	}

	/**
	 * [PropostionAvancementDto.dateCommission] Setter 
	 * @author obelbrik on : 17 déc. 2014  11:23:23
	 * @param dateCommission the dateCommission to set
	 */
	public void setDateCommission(Date dateCommission) {
		this.dateCommission = dateCommission;
	}

	/**
	 * [PropostionAvancementDto.dateDecision] Getter 
	 * @author obelbrik on : 17 déc. 2014  11:23:23
	 * @return the dateDecision
	 */
	public Date getDateDecision() {
		return dateDecision;
	}

	/**
	 * [PropostionAvancementDto.dateDecision] Setter 
	 * @author obelbrik on : 17 déc. 2014  11:23:23
	 * @param dateDecision the dateDecision to set
	 */
	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}

	/**
	 * [PropostionAvancementDto.situation] Getter 
	 * @author obelbrik on : 17 déc. 2014  11:23:23
	 * @return the situation
	 */
	public SituationEntiteDto getSituationDto() {
		return situationDto;
	}

	/**
	 * [PropostionAvancementDto.situation] Setter 
	 * @author obelbrik on : 17 déc. 2014  11:23:23
	 * @param situation the situation to set
	 */
	public void setSituationDto(SituationEntiteDto situationDto) {
		this.situationDto = situationDto;
	}

	/**
	 * [PropostionAvancementDto.nomenclatureByTypeAvancementDto] Getter 
	 * @author obelbrik on : 23 déc. 2014  12:25:44
	 * @return the nomenclatureByTypeAvancementDto
	 */
	public NomenclatureDto getNomenclatureByTypeAvancementDto() {
		return nomenclatureByTypeAvancementDto;
	}

	/**
	 * [PropostionAvancementDto.nomenclatureByTypeAvancementDto] Setter 
	 * @author obelbrik on : 23 déc. 2014  12:25:44
	 * @param nomenclatureByTypeAvancementDto the nomenclatureByTypeAvancementDto to set
	 */
	public void setNomenclatureByTypeAvancementDto(
			NomenclatureDto nomenclatureByTypeAvancementDto) {
		this.nomenclatureByTypeAvancementDto = nomenclatureByTypeAvancementDto;
	}

	public Integer getSelectedMoisId() {
		return selectedMoisId;
	}

	public void setSelectedMoisId(Integer selectedMoisId) {
		this.selectedMoisId = selectedMoisId;
	}

	
	
}
