package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import dz.gov.mesrs.sii.commons.data.model.grh.Grade;


public class EmployeProposeDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3773988686108599817L;
	private Long id;
	private PropostionAvancementDto propostionAvancementDto;
	private DossierEmployeDto dossierEmployeDto;
	private Date dateProposition;
	private Date dateEffetProposee;
	private Date dateEffetRetenue;
	private CarriereDto carriereDto;
	private GrilleIndiciaireDto grilleIndiciaireDto;
	private Boolean confirm;
	private Integer duree;
	private Boolean selection;
	private GradeDto nouveauGradeDto;
	///////
	

	public EmployeProposeDto() {
		
	}
	public EmployeProposeDto(Long id) {
		this.id = id;
	}
	
	public EmployeProposeDto(Long id,
			PropostionAvancementDto propostionAvancementDto,
			DossierEmployeDto dossierEmployeDto, Date dateProposition,
			Date dateEffetProposee, Date dateEffetRetenue,
			CarriereDto carriereDto, GrilleIndiciaireDto grilleIndiciaireDto,
			Boolean confirm, Integer duree, Boolean selection,GradeDto nouveauGradeDto) {
		super();
		this.id = id;
		this.propostionAvancementDto = propostionAvancementDto;
		this.dossierEmployeDto = dossierEmployeDto;
		this.dateProposition = dateProposition;
		this.dateEffetProposee = dateEffetProposee;
		this.dateEffetRetenue = dateEffetRetenue;
		this.carriereDto = carriereDto;
		this.grilleIndiciaireDto = grilleIndiciaireDto;
		this.confirm = confirm;
		this.duree = duree;
		this.selection = selection;
		this.nouveauGradeDto=nouveauGradeDto;
	}
	/**
	 * [EmployeProposeDto.id] Getter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * [EmployeProposeDto.id] Setter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * [EmployeProposeDto.propostionAvancementDto] Getter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @return the propostionAvancementDto
	 */
	public PropostionAvancementDto getPropostionAvancementDto() {
		return propostionAvancementDto;
	}
	/**
	 * [EmployeProposeDto.propostionAvancementDto] Setter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @param propostionAvancementDto the propostionAvancementDto to set
	 */
	public void setPropostionAvancementDto(
			PropostionAvancementDto propostionAvancementDto) {
		this.propostionAvancementDto = propostionAvancementDto;
	}
	/**
	 * [EmployeProposeDto.dossierEmployeDto] Getter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @return the dossierEmployeDto
	 */
	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}
	/**
	 * [EmployeProposeDto.dossierEmployeDto] Setter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @param dossierEmployeDto the dossierEmployeDto to set
	 */
	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}
	/**
	 * [EmployeProposeDto.dateProposition] Getter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @return the dateProposition
	 */
	public Date getDateProposition() {
		return dateProposition;
	}
	/**
	 * [EmployeProposeDto.dateProposition] Setter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @param dateProposition the dateProposition to set
	 */
	public void setDateProposition(Date dateProposition) {
		this.dateProposition = dateProposition;
	}
	/**
	 * [EmployeProposeDto.dateEffetProposee] Getter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @return the dateEffetProposee
	 */
	public Date getDateEffetProposee() {
		return dateEffetProposee;
	}
	/**
	 * [EmployeProposeDto.dateEffetProposee] Setter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @param dateEffetProposee the dateEffetProposee to set
	 */
	public void setDateEffetProposee(Date dateEffetProposee) {
		this.dateEffetProposee = dateEffetProposee;
	}
	/**
	 * [EmployeProposeDto.dateEffetRetenue] Getter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @return the dateEffetRetenue
	 */
	public Date getDateEffetRetenue() {
		return dateEffetRetenue;
	}
	/**
	 * [EmployeProposeDto.dateEffetRetenue] Setter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @param dateEffetRetenue the dateEffetRetenue to set
	 */
	public void setDateEffetRetenue(Date dateEffetRetenue) {
		this.dateEffetRetenue = dateEffetRetenue;
	}
	/**
	 * [EmployeProposeDto.carriereDto] Getter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @return the carriereDto
	 */
	public CarriereDto getCarriereDto() {
		return carriereDto;
	}
	/**
	 * [EmployeProposeDto.carriereDto] Setter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @param carriereDto the carriereDto to set
	 */
	public void setCarriereDto(CarriereDto carriereDto) {
		this.carriereDto = carriereDto;
	}
	/**
	 * [EmployeProposeDto.grilleIndiciaireDto] Getter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @return the grilleIndiciaireDto
	 */
	public GrilleIndiciaireDto getGrilleIndiciaireDto() {
		return grilleIndiciaireDto;
	}
	/**
	 * [EmployeProposeDto.grilleIndiciaireDto] Setter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @param grilleIndiciaireDto the grilleIndiciaireDto to set
	 */
	public void setGrilleIndiciaireDto(GrilleIndiciaireDto grilleIndiciaireDto) {
		this.grilleIndiciaireDto = grilleIndiciaireDto;
	}
	/**
	 * [EmployeProposeDto.confirm] Getter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @return the confirm
	 */
	public Boolean getConfirm() {
		return confirm;
	}
	/**
	 * [EmployeProposeDto.confirm] Setter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @param confirm the confirm to set
	 */
	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}
	/**
	 * [EmployeProposeDto.duree] Getter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @return the duree
	 */
	public Integer getDuree() {
		return duree;
	}
	/**
	 * [EmployeProposeDto.duree] Setter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @param duree the duree to set
	 */
	public void setDuree(Integer duree) {
		this.duree = duree;
	}
	/**
	 * [EmployeProposeDto.selection] Getter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @return the selection
	 */
	public Boolean getSelection() {
		return selection;
	}
	/**
	 * [EmployeProposeDto.selection] Setter 
	 * @author obelbrik on : 23 déc. 2014  11:19:54
	 * @param selection the selection to set
	 */
	public void setSelection(Boolean selection) {
		this.selection = selection;
	}
	public GradeDto getNouveauGradeDto() {
		return nouveauGradeDto;
	}
	public void setNouveauGradeDto(GradeDto nouveauGradeDto) {
		this.nouveauGradeDto = nouveauGradeDto;
	}

	

	/**
	 * @return the id
	 */
	

}
