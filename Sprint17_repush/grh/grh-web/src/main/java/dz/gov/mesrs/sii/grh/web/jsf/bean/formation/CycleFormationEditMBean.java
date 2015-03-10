package dz.gov.mesrs.sii.grh.web.jsf.bean.formation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.grh.business.model.dto.CycleFormationDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GradeCycleFormationDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GradeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.ProgrammeFormationGrhDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@ManagedBean(name = "cycleFormationEditMBean")
@ViewScoped
public class CycleFormationEditMBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private CycleFormationDto cycleFormationDto;
	private List<CycleFormationDto> listeCycleFormation;
	private List<SelectItem> listeProgrammes;
	private ProgrammeFormationGrhDto programmeFormationGrhDto;
	private List<GradeCycleFormationDto> listGradeCycleFormations;
	private Integer selectedGradeCycleFormationIndex;
	private List<SelectItem> listGrades;

	public CycleFormationEditMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		initSelectItemLists();
		onSearchCycle();
	}

	private void initSelectItemLists() {
		listeProgrammes = findListeProgrammeFormationGrh();
		listGrades = findListGradeGroupedByCorps();
	}

	public void initDetail() {
		cycleFormationDto = null;

	}

	public void onNew() {
		cycleFormationDto = new CycleFormationDto();
		cycleFormationDto
				.setProgrammeFormationGrhDto(new ProgrammeFormationGrhDto());
		listGradeCycleFormations = new ArrayList<GradeCycleFormationDto>();

	}

	public void onSearchCycle() {
		try {
			loadlisteCycleFormation();
			initDetail();
		} catch (Exception e) {

		}
	}

	private void loadlisteCycleFormation() {
		listeCycleFormation = serviceLocator.getCycleFormationService()
				.findAllCycleFormationCrees( new SearchMode(), searchKeyWords);

	}

	public void onCycleSelect(SelectEvent event) {
		cycleFormationDto = (CycleFormationDto) event.getObject();
		listGradeCycleFormations = cycleFormationDto
				.getGradeCycleFormationDtos();
		findProgrammeFormationGrhDto();

	}

	public void onProgrammeChange(AjaxBehaviorEvent event) {
		findProgrammeFormationGrhDto();

	}
	

	public void onSave() {
		if (validateForm()) {
			cycleFormationDto.setGradeCycleFormationDtos(listGradeCycleFormations);
			cycleFormationDto = serviceLocator.getCycleFormationService().saveCycleFormation(
					cycleFormationDto);
			listGradeCycleFormations = cycleFormationDto.getGradeCycleFormationDtos();
			loadlisteCycleFormation();
			// initDetail();
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}

	private boolean validateForm() {
		boolean result = true;
		if (serviceLocator.getRefJourOuvreService().isWeekendDay(
				cycleFormationDto.getDateDebut())
				|| serviceLocator.getRefJourOuvreService().isJourOuvre(
						cycleFormationDto.getDateDebut())) {
			GRHMessagesUtils
					.showErrorMessage("cycle_formation_date_debut_invalide");
			result = false;
		}
		if (serviceLocator.getRefJourOuvreService().isWeekendDay(
				cycleFormationDto.getDateFin())
				|| serviceLocator.getRefJourOuvreService().isJourOuvre(
						cycleFormationDto.getDateFin())) {
			GRHMessagesUtils
					.showErrorMessage("cycle_formation_date_fin_invalide");
			result = false;
		}
		if (cycleFormationDto.getDateDebut().after(
				cycleFormationDto.getDateFin())) {
			GRHMessagesUtils
					.showErrorMessage("cycle_formation_date_debut_fin_invalide");
			result = false;
		}
		
		if (programmeFormationGrhDto.getDateDebut().after(cycleFormationDto.getDateDebut()
				)) {
			GRHMessagesUtils
					.showErrorMessage("cycle_formation_date_debut_programme_date_debut_invalide");
			result = false;
		}
		
		if (cycleFormationDto.getDateFin().after(programmeFormationGrhDto.getDateFin()
				)) {
			GRHMessagesUtils
					.showErrorMessage("cycle_formation_date_fin_programme_date_fin_invalide");
			result = false;
		}
		return result;
	}

	public void onAddGradeCycleFormation() {
		GradeCycleFormationDto gradeCycleFormationDto = new GradeCycleFormationDto();
		gradeCycleFormationDto.setGradeDto(new GradeDto());
		if (listGradeCycleFormations == null) {
			listGradeCycleFormations = new ArrayList<GradeCycleFormationDto>();
		}
		gradeCycleFormationDto.setCycleFormationDto(cycleFormationDto);
		listGradeCycleFormations.add(gradeCycleFormationDto);
	}

	public void onRemoveGradCycleFormation() {
		GradeCycleFormationDto gradeCycleFormationDto = listGradeCycleFormations
				.get(selectedGradeCycleFormationIndex);
		listGradeCycleFormations.remove(gradeCycleFormationDto);
	}
	
	private void findProgrammeFormationGrhDto(){
		if(cycleFormationDto.getProgrammeFormationGrhDto().getId()!=null){
			programmeFormationGrhDto = serviceLocator.getProgrammeFormationGrhService().findById(cycleFormationDto.getProgrammeFormationGrhDto().getId());
			}else {
				programmeFormationGrhDto = null;	
			}

	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public CycleFormationDto getCycleFormationDto() {
		return cycleFormationDto;
	}

	public void setCycleFormationDto(CycleFormationDto cycleFormationDto) {
		this.cycleFormationDto = cycleFormationDto;
	}

	public List<CycleFormationDto> getListeCycleFormation() {
		return listeCycleFormation;
	}

	public void setListeCycleFormation(
			List<CycleFormationDto> listeCycleFormation) {
		this.listeCycleFormation = listeCycleFormation;
	}

	public List<SelectItem> getListeProgrammes() {
		return listeProgrammes;
	}

	public void setListeProgrammes(List<SelectItem> listeProgrammes) {
		this.listeProgrammes = listeProgrammes;
	}

	public ProgrammeFormationGrhDto getProgrammeFormationGrhDto() {
		return programmeFormationGrhDto;
	}

	public void setProgrammeFormationGrhDto(
			ProgrammeFormationGrhDto programmeFormationGrhDto) {
		this.programmeFormationGrhDto = programmeFormationGrhDto;
	}

	public List<GradeCycleFormationDto> getListGradeCycleFormations() {
		return listGradeCycleFormations;
	}

	public void setListGradeCycleFormations(
			List<GradeCycleFormationDto> listGradeCycleFormations) {
		this.listGradeCycleFormations = listGradeCycleFormations;
	}

	public Integer getSelectedGradeCycleFormationIndex() {
		return selectedGradeCycleFormationIndex;
	}

	public void setSelectedGradeCycleFormationIndex(
			Integer selectedGradeCycleFormationIndex) {
		this.selectedGradeCycleFormationIndex = selectedGradeCycleFormationIndex;
	}

	public List<SelectItem> getListGrades() {
		return listGrades;
	}

	public void setListGrades(List<SelectItem> listGrades) {
		this.listGrades = listGrades;
	}

}
