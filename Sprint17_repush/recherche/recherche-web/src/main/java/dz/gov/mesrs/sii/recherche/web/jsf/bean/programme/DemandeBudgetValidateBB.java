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
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;

@ManagedBean(name = "demandeValidateBB")
@ViewScoped
public class DemandeBudgetValidateBB extends BaseBean {
	
	private static final long serialVersionUID = 1L;

	// ===================================================================  
	// Constructors
	// ===================================================================

	public DemandeBudgetValidateBB() {
		
	}
	
	@PostConstruct
	public void init() {
				currentAffectation = sessionBean.getCurrentUser().getCurrentAffectation();
				if(currentAffectation != null) {
						currentRoleCode = currentAffectation.getRoleCode();
						initListDemandsToValidate();	
				}
				
	}

	// ===================================================================  
	// Properties Model
	// ===================================================================
	private RefAffectationDto currentAffectation;
	private String currentRoleCode;
	
	// Demande de budget
	private List<DemandeBudgetDto> listDemands; 
	private DemandeBudgetDto selectedDemand;
	private DemandeBudgetDto currentDemand;
	private String titleDetailsDemand;

	@ManagedProperty(value = "#{demandeBB}")
	private DemandeBudgetBB demandeBudgetBB;

	public String getCurrentRoleCode() {
		return currentRoleCode;
	}

	public void setCurrentRoleCode(String currentRoleCode) {
		this.currentRoleCode = currentRoleCode;
	}

	public RefAffectationDto getCurrentAffectation() {
		return currentAffectation;
	}

	public void setCurrentAffectation(RefAffectationDto currentAffectation) {
		this.currentAffectation = currentAffectation;
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
	private void initListDemandsToValidate() {

		listDemands = serviceLocator.getRechercheDemandeBudgetService().findDemandsToValidate(null);
		demandeBudgetBB.setCurrentRoleCode(null);
		demandeBudgetBB.setListDemands(listDemands);
	}
	
	public void onDemandRowSelect(SelectEvent event) {  
		demandeBudgetBB.setCanEditDemand(false);
		demandeBudgetBB.setCanSubmitDemand(false);
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
