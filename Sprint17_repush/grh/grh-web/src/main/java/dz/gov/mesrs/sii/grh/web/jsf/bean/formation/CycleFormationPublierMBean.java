package dz.gov.mesrs.sii.grh.web.jsf.bean.formation;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.grh.business.model.dto.CycleFormationDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@ManagedBean(name = "cycleFormationPublierMBean")
@ViewScoped
public class CycleFormationPublierMBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private CycleFormationDto cycleFormationDto;
	private List<CycleFormationDto> listeCycleFormation;
	
	
	

	public CycleFormationPublierMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		
		onSearchCycle();
	}

	

	public void initDetail() {
		cycleFormationDto = null;

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
		

	}


	

	public void onSave() {
			cycleFormationDto = serviceLocator.getCycleFormationService().publierCycleFormation(cycleFormationDto);
			loadlisteCycleFormation();
			 initDetail();
			CommonMessagesUtils.showSuccessSaveMessage();
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

	

}
