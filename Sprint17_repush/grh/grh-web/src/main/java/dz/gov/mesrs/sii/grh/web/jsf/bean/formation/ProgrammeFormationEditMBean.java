package dz.gov.mesrs.sii.grh.web.jsf.bean.formation;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.grh.business.model.dto.ProgrammeFormationGrhDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@ManagedBean(name = "programmeFormationEditMBean")
@ViewScoped
public class ProgrammeFormationEditMBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private ProgrammeFormationGrhDto programmeFormationGrhDto;
	private List<ProgrammeFormationGrhDto> listeProgrammeFormationGrh;
	

	public ProgrammeFormationEditMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		onSearchProgramme();
	}

	

	public void initDetail() {
		programmeFormationGrhDto = null;

	}

	public void onNew() {
		programmeFormationGrhDto = new ProgrammeFormationGrhDto();
//		programmeFormationGrhDto.setRefEtablissementDto(sessionBean
//				.getRefEtablissementDto());
		

	}

	public void onSearchProgramme() {
		try {
			loadlisteProgrammeFormationGrh();
			initDetail();
		} catch (Exception e) {

		}
	}

	private void loadlisteProgrammeFormationGrh() {
		listeProgrammeFormationGrh = serviceLocator.getProgrammeFormationGrhService().findAllByKeyWord(searchKeyWords, null);
				
			
	}

	public void onProgrammeSelect(SelectEvent event) {
		programmeFormationGrhDto = (ProgrammeFormationGrhDto) event.getObject();
		

	}

	
	public void onSave() {
		if(validateForm()){
			programmeFormationGrhDto = serviceLocator.getProgrammeFormationGrhService().save(programmeFormationGrhDto);
			loadlisteProgrammeFormationGrh();
		//initDetail();
		CommonMessagesUtils.showSuccessSaveMessage();
		}
	}
	
	
	
	
	private boolean validateForm(){
		boolean result = true;
		if ( serviceLocator.getRefJourOuvreService().isWeekendDay(
				programmeFormationGrhDto.getDateDebut())
				|| serviceLocator.getRefJourOuvreService().isJourOuvre(
						programmeFormationGrhDto.getDateDebut())) {
			GRHMessagesUtils
					.showErrorMessage("programme__formation_date_debut_invalide");
			result = false;
		}
		if ( serviceLocator.getRefJourOuvreService().isWeekendDay(
				programmeFormationGrhDto.getDateFin())
				|| serviceLocator.getRefJourOuvreService().isJourOuvre(
						programmeFormationGrhDto.getDateFin())) {
			GRHMessagesUtils
					.showErrorMessage("programme_formation_date_fin_invalide");
			result = false;
		}
		if(programmeFormationGrhDto.getDateDebut().after(programmeFormationGrhDto.getDateFin())){
			GRHMessagesUtils
			.showErrorMessage("programme__formation_date_debut_fin_invalide");
	result = false;
		}
		return result;
	}
	
	

			

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public ProgrammeFormationGrhDto getProgrammeFormationGrhDto() {
		return programmeFormationGrhDto;
	}

	public void setProgrammeFormationGrhDto(
			ProgrammeFormationGrhDto programmeFormationGrhDto) {
		this.programmeFormationGrhDto = programmeFormationGrhDto;
	}

	public List<ProgrammeFormationGrhDto> getListeProgrammeFormationGrh() {
		return listeProgrammeFormationGrh;
	}

	public void setListeProgrammeFormationGrh(
			List<ProgrammeFormationGrhDto> listeProgrammeFormationGrh) {
		this.listeProgrammeFormationGrh = listeProgrammeFormationGrh;
	}

	

	

}
