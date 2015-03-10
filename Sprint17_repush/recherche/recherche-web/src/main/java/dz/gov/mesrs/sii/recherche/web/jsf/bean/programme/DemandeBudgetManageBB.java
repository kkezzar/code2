/**
 * [dz.gov.mesrs.sii.recherche.web.jsf.bean.structure.RechercheStructureBB.java] 
 * @author rlaib on : 16 d�c. 2014  16:06:14
 * 
 */
package dz.gov.mesrs.sii.recherche.web.jsf.bean.programme;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.SerializationUtils;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.recherche.business.model.dto.DemandeBudgetDto;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.BaseBean;

@ManagedBean(name = "demandeBudgetManageBB")
@ViewScoped
public class DemandeBudgetManageBB extends BaseBean {
	
	private static final long serialVersionUID = 1L;
	// ===================================================================  
	// Constructors
	// ===================================================================

	public DemandeBudgetManageBB() {
		
	}
	
	@PostConstruct
	public void init() {

	}

	// ===================================================================  
	// Properties Model
	// ===================================================================
	// Demande de budget
	private String searchComponentKeyWords;
	private List<DemandeBudgetDto> listDemands; 
	private DemandeBudgetDto selectedDemand;
	private DemandeBudgetDto currentDemand;
	private String titleDetailsDemand;

	@ManagedProperty(value = "#{demandeBB}")
	private DemandeBudgetBB demandeBudgetBB;
	
	public String getSearchComponentKeyWords() {
		return searchComponentKeyWords;
	}

	public void setSearchComponentKeyWords(String searchComponentKeyWords) {
		this.searchComponentKeyWords = searchComponentKeyWords;
	}

	public List<DemandeBudgetDto> getListDemands() {
		return listDemands;
	}

	public void setListDemands(List<DemandeBudgetDto> listDemands) {
		this.listDemands = listDemands;
	}

	public DemandeBudgetDto getSelectedDemand() {
		return selectedDemand;
	}

	public void setSelectedDemand(DemandeBudgetDto selectedDemand) {
		this.selectedDemand = selectedDemand;
	}

	public DemandeBudgetDto getCurrentDemand() {
		return currentDemand;
	}

	public void setCurrentDemand(DemandeBudgetDto currentDemand) {
		this.currentDemand = currentDemand;
	}

	public String getTitleDetailsDemand() {
		return titleDetailsDemand;
	}

	public void setTitleDetailsDemand(String titleDetailsDemand) {
		this.titleDetailsDemand = titleDetailsDemand;
	}

	public DemandeBudgetBB getDemandeBudgetBB() {
		return demandeBudgetBB;
	}

	public void setDemandeBudgetBB(DemandeBudgetBB demandeBudgetBB) {
		this.demandeBudgetBB = demandeBudgetBB;
	}

	// ===================================================================  
	// Actions and Methods
	// ===================================================================
		
	public void searchDemands() {
		listDemands = serviceLocator.getRechercheDemandeBudgetService().findByKeyWords(searchComponentKeyWords);
		demandeBudgetBB.setListDemands(listDemands);
		if(listDemands  != null && !listDemands.isEmpty()) {
			
			if (listDemands.size() == 1) {
				selectedDemand= listDemands.get(0);
				currentDemand = SerializationUtils.clone(selectedDemand);
				demandeBudgetBB.setCanShowDemandDetails(true);
			}
			else {
				demandeBudgetBB.setCanShowDemandDetails(false);
			}
		}
		else {
			selectedDemand = currentDemand = null;
			demandeBudgetBB.setCanShowDemandDetails(false);
		}
		
	}
	
	public void addNewDemand() {
		demandeBudgetBB.setCanEditDemand(true);
		demandeBudgetBB.setActiveTabIndex(0);
		demandeBudgetBB.prepareAddDemand();
	}
	
	public void onDemandRowSelect(SelectEvent event) {  
		
		demandeBudgetBB.setCanEditDemand(true);
		demandeBudgetBB.setSearchComponentKeyWords(searchComponentKeyWords);
		if(selectedDemand != null) {
			if(currentDemand == null || selectedDemand.getId()!=currentDemand.getId()) {
						demandeBudgetBB.setCanShowDemandDetails(true);
						currentDemand = SerializationUtils.clone(selectedDemand);
						demandeBudgetBB.setSelectedDemand(selectedDemand);
						demandeBudgetBB.setCurrentDemand(currentDemand);
						demandeBudgetBB.loadDemandDetail();
			}
		}
		
	}
}
