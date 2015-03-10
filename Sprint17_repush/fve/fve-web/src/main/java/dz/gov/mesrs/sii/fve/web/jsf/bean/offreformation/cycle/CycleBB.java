/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.bac.BacBB.java] 
 * @author  Rafik LAIB on : 21 mai 2014  11:18:57
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.cycle;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDiplomeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.CycleService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.MessageUtil;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;


/**
 * @author rlaib  on : 16 juil. 2014  11:56:31
 */
@ManagedBean(name = "cycleBB")
@ViewScoped
public class CycleBB {

	public CycleBB() {
		
	}
	
	@PostConstruct
	public void init() {
		
		 try	{
			 		initNomenclatures();
			 		if(listTypesFormation  != null && listTypesFormation .size()>0) {
			 			if (!(typeFormationCycleCode!= null && !typeFormationCycleCode.trim().isEmpty() && !typeFormationCycleCode.equals("null"))) {
			 				typeFormationCycleCode = listTypesFormation.get(0).getValue().toString();
			 			}
			 			listCycles = cycleService.findByTypeFormationCode(typeFormationCycleCode);
			 		}
			 		if (cycleId != null && !cycleId.trim().isEmpty() && !cycleId.equals("null") &&(Integer.parseInt(cycleId)>0)) {
			 			//loadCycleDetails();
		
			 		}
			 		else {
			 			selectedCycle = new CycleDto();
						selectedDetailCycleTitle = MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
								OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_PANEL_NEW_TITLE);
			 		}
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	
	}

	/**
	 * [CycleBB.loadCycleDtails] Method 
	 * @author rlaib  on : 11 sept. 2014  09:25:36
	 */
	private void loadCycleDetails() {

		listLevelsCycle = niveauService.findByCycleId(selectedCycle.getId());
		initCycleDiplomes();
		showCycleDetails = true;
		Object[] params = {selectedCycle.getLibelleLongLt()};
		selectedDetailCycleTitle = MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
				OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_PANEL_TITLE, params,"fr");
		toEditCycle = true;
	}
	
	/**
	 * [CycleBB.initCycleDiplomes] Method 
	 * @author rlaib  on : 25 nov. 2014  11:22:20
	 */
	private void initCycleDiplomes() {
	
		selectedCycleDiplomes = cycleService.findDiplomesByCycle(selectedCycle.getId());
		selectedCycleDiplomeOut = new ArrayList<CycleDiplomeDto>();
		selectedCycleDiplomesIn = new ArrayList<CycleDiplomeDto>();
		for (CycleDiplomeDto cycleDiplomeDto : selectedCycleDiplomes) {
			
			switch (cycleDiplomeDto.getSens()) {
			case OfConstants.CYCLE_DIPLOME_SENS_IN:
				selectedCycleDiplomesIn.add(cycleDiplomeDto);
				break;
			case OfConstants.CYCLE_DIPLOME_SENS_OUT:
				selectedCycleDiplomeOut.add(cycleDiplomeDto);
				break;

			default:
				break;
			}
		}
		
	}

	// ===================================================================  
	// Beans Services 
	// ===================================================================
	@ManagedProperty(value="#{cycleService}")
	private CycleService cycleService;
	
	@ManagedProperty(value="#{niveauService}")
	private NiveauService niveauService;
	
	@ManagedProperty(value="#{periodeService}")
	private PeriodeService periodeService;

	@ManagedProperty(value="#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;

	// ===================================================================  
	// Beans Services  Getters / Setters
	// ===================================================================
	/**
	 * [CycleBB.cycleService] Getter 
	 * @author rlaib on : 16 juil. 2014  11:55:49
	 * @return the cycleService
	 */
	public CycleService getCycleService() {
		return cycleService;
	}

	/**
	 * [CycleBB.cycleService] Setter 
	 * @author rlaib on : 16 juil. 2014  11:55:49
	 * @param cycleService the cycleService to set
	 */
	public void setCycleService(CycleService cycleService) {
		this.cycleService = cycleService;
	}

	/**
	 * [CycleBB.niveauService] Getter 
	 * @author rlaib on : 16 juil. 2014  11:55:49
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [CycleBB.niveauService] Setter 
	 * @author rlaib on : 16 juil. 2014  11:55:49
	 * @param niveauService the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [CycleBB.periodeService] Getter 
	 * @author rlaib on : 16 juil. 2014  11:55:49
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [CycleBB.periodeService] Setter 
	 * @author rlaib on : 16 juil. 2014  11:55:49
	 * @param periodeService the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [CycleBB.nomenclatureService] Getter 
	 * @author rlaib on : 13 oct. 2014  09:50:01
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [CycleBB.nomenclatureService] Setter 
	 * @author rlaib on : 13 oct. 2014  09:50:01
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}
	
	// ===================================================================  
	// Parameters
	// ===================================================================
	private String outcomePage;
	private String cycleId;
	private String typeFormationCycleCode;
	private String dialogDiplomeTitle;
	// ===================================================================  
	// Properties Model
	// ===================================================================
	private List<SelectItem> listTypesFormation;
	private List<SelectItem> listTypesPassage;
	private List<SelectItem> listNiveauxDiplomes;
	private List<SelectItem> listTypesDiplomes;
	private String selectedTypeFormationFilter;
	private String selectedDetailCycleTitle;
	private List<CycleDto> listCycles;
	private List<NiveauDto> listLevelsCycle;
	private boolean showCycleDetails;
	private boolean showBtnSave;
	private CycleDto selectedCycle;
	private List<CycleDiplomeDto> selectedCycleDiplomesIn;
	private Integer selectedCycleDiplomesInId;
	private Integer selectedCycleDiplomesOutId;
	private List<CycleDiplomeDto> selectedCycleDiplomeOut;
	private List<CycleDiplomeDto> selectedCycleDiplomes;
	private CycleDiplomeDto selectedCycleDiplome;
	private Integer selectedNiveauDiplomeId;
	private Integer selectedTypeDiplomeId;
	private Integer selectedRang;
	private String selectedSens;
	private boolean addOrEditDialog;
	private boolean sensIsIn;
	private boolean toEditCycle = false;
	
	// ===================================================================  
	// Parameters Getters / Setters
	// ===================================================================
	
	/**
	 * [CycleBB.outcomePage] Getter 
	 * @author rlaib on : 16 juil. 2014  12:13:25
	 * @return the outcomePage
	 */
	public String getOutcomePage() {
		return outcomePage;
	}

	/**
	 * [CycleBB.toEditCycle] Getter 
	 * @author rlaib on : 4 févr. 2015  16:20:46
	 * @return the toEditCycle
	 */
	public boolean isToEditCycle() {
		return toEditCycle;
	}

	/**
	 * [CycleBB.toEditCycle] Setter 
	 * @author rlaib on : 4 févr. 2015  16:20:46
	 * @param toEditCycle the toEditCycle to set
	 */
	public void setToEditCycle(boolean toEditCycle) {
		this.toEditCycle = toEditCycle;
	}

	/**
	 * [CycleBB.outcomePage] Setter 
	 * @author rlaib on : 16 juil. 2014  12:13:25
	 * @param outcomePage the outcomePage to set
	 */
	public void setOutcomePage(String outcomePage) {
		this.outcomePage = outcomePage;
	}
	
	/**
	 * [CycleBB.selectedNiveauDiplomeId] Getter 
	 * @author rlaib on : 24 nov. 2014  17:19:12
	 * @return the selectedNiveauDiplomeId
	 */
	public Integer getSelectedNiveauDiplomeId() {
		return selectedNiveauDiplomeId;
	}

	/**
	 * [CycleBB.selectedNiveauDiplomeId] Setter 
	 * @author rlaib on : 24 nov. 2014  17:19:12
	 * @param selectedNiveauDiplomeId the selectedNiveauDiplomeId to set
	 */
	public void setSelectedNiveauDiplomeId(Integer selectedNiveauDiplomeId) {
		this.selectedNiveauDiplomeId = selectedNiveauDiplomeId;
	}

	/**
	 * [CycleBB.selectedTypeDiplomeId] Getter 
	 * @author rlaib on : 24 nov. 2014  17:19:12
	 * @return the selectedTypeDiplomeId
	 */
	public Integer getSelectedTypeDiplomeId() {
		return selectedTypeDiplomeId;
	}

	/**
	 * [CycleBB.selectedTypeDiplomeId] Setter 
	 * @author rlaib on : 24 nov. 2014  17:19:12
	 * @param selectedTypeDiplomeId the selectedTypeDiplomeId to set
	 */
	public void setSelectedTypeDiplomeId(Integer selectedTypeDiplomeId) {
		this.selectedTypeDiplomeId = selectedTypeDiplomeId;
	}

	/**
	 * [CycleBB.dialogDiplomeTitle] Getter 
	 * @author rlaib on : 24 nov. 2014  17:03:55
	 * @return the dialogDiplomeTitle
	 */
	public String getDialogDiplomeTitle() {
		return dialogDiplomeTitle;
	}

	/**
	 * [CycleBB.dialogDiplomeTitle] Setter 
	 * @author rlaib on : 24 nov. 2014  17:03:55
	 * @param dialogDiplomeTitle the dialogDiplomeTitle to set
	 */
	public void setDialogDiplomeTitle(String dialogDiplomeTitle) {
		this.dialogDiplomeTitle = dialogDiplomeTitle;
	}

	/**
	 * [CycleBB.cycleId] Getter 
	 * @author rlaib on : 16 juil. 2014  15:55:01
	 * @return the cycleId
	 */
	public String getCycleId() {
		return cycleId;
	}

	/**
	 * [CycleBB.cycleId] Setter 
	 * @author rlaib on : 16 juil. 2014  15:55:01
	 * @param cycleId the cycleId to set
	 */
	public void setCycleId(String cycleId) {
		this.cycleId = cycleId;
	}
	
	/**
	 * [CycleBB.typeFormationCycleCode] Getter 
	 * @author rlaib on : 20 juil. 2014  14:20:46
	 * @return the typeFormationCycleCode
	 */
	public String getTypeFormationCycleCode() {
		return typeFormationCycleCode;
	}

	/**
	 * [CycleBB.typeFormationCycleCode] Setter 
	 * @author rlaib on : 20 juil. 2014  14:20:46
	 * @param typeFormationCycleCode the typeFormationCycleCode to set
	 */
	public void setTypeFormationCycleCode(String typeFormationCycleCode) {
		this.typeFormationCycleCode = typeFormationCycleCode;
	}

	// ===================================================================  
	// Properties Model Getters / Setters
	// ===================================================================
	/**
	 * [CycleBB.listTypesFormation] Getter 
	 * @author rlaib on : 16 juil. 2014  13:06:46
	 * @return the listTypesFormation
	 */
	public List<SelectItem> getListTypesFormation() {
		return listTypesFormation;
	}

	/**
	 * [CycleBB.listTypesFormation] Setter 
	 * @author rlaib on : 16 juil. 2014  13:06:46
	 * @param listTypesFormation the listTypesFormation to set
	 */
	public void setListTypesFormation(List<SelectItem> listTypesFormation) {
		this.listTypesFormation = listTypesFormation;
	}

	/**
	 * [CycleBB.listNiveauxDiplomes] Getter 
	 * @author rlaib on : 24 nov. 2014  16:12:38
	 * @return the listNiveauxDiplomes
	 */
	public List<SelectItem> getListNiveauxDiplomes() {
		return listNiveauxDiplomes;
	}

	/**
	 * [CycleBB.listNiveauxDiplomes] Setter 
	 * @author rlaib on : 24 nov. 2014  16:12:39
	 * @param listNiveauxDiplomes the listNiveauxDiplomes to set
	 */
	public void setListNiveauxDiplomes(List<SelectItem> listNiveauxDiplomes) {
		this.listNiveauxDiplomes = listNiveauxDiplomes;
	}

	/**
	 * [CycleBB.listTypesDiplomes] Getter 
	 * @author rlaib on : 24 nov. 2014  16:12:39
	 * @return the listTypesDiplomes
	 */
	public List<SelectItem> getListTypesDiplomes() {
		return listTypesDiplomes;
	}

	/**
	 * [CycleBB.listTypesDiplomes] Setter 
	 * @author rlaib on : 24 nov. 2014  16:12:39
	 * @param listTypesDiplomes the listTypesDiplomes to set
	 */
	public void setListTypesDiplomes(List<SelectItem> listTypesDiplomes) {
		this.listTypesDiplomes = listTypesDiplomes;
	}

	/**
	 * [CycleBB.selectedCycleDiplomesIn] Getter 
	 * @author rlaib on : 24 nov. 2014  16:12:39
	 * @return the selectedCycleDiplomesIn
	 */
	public List<CycleDiplomeDto> getSelectedCycleDiplomesIn() {
		return selectedCycleDiplomesIn;
	}

	/**
	 * [CycleBB.selectedCycleDiplomesIn] Setter 
	 * @author rlaib on : 24 nov. 2014  16:12:39
	 * @param selectedCycleDiplomesIn the selectedCycleDiplomesIn to set
	 */
	public void setSelectedCycleDiplomesIn(
			List<CycleDiplomeDto> selectedCycleDiplomesIn) {
		this.selectedCycleDiplomesIn = selectedCycleDiplomesIn;
	}
	
	/**
	 * [CycleBB.selectedCycleDiplomesInId] Getter 
	 * @author rlaib on : 25 nov. 2014  14:08:11
	 * @return the selectedCycleDiplomesInId
	 */
	public Integer getSelectedCycleDiplomesInId() {
		return selectedCycleDiplomesInId;
	}

	/**
	 * [CycleBB.selectedCycleDiplomesInId] Setter 
	 * @author rlaib on : 25 nov. 2014  14:08:11
	 * @param selectedCycleDiplomesInId the selectedCycleDiplomesInId to set
	 */
	public void setSelectedCycleDiplomesInId(Integer selectedCycleDiplomesInId) {
		this.selectedCycleDiplomesInId = selectedCycleDiplomesInId;
	}

	/**
	 * [CycleBB.selectedCycleDiplomesOutId] Getter 
	 * @author rlaib on : 25 nov. 2014  14:08:11
	 * @return the selectedCycleDiplomesOutId
	 */
	public Integer getSelectedCycleDiplomesOutId() {
		return selectedCycleDiplomesOutId;
	}

	/**
	 * [CycleBB.selectedCycleDiplomesOutId] Setter 
	 * @author rlaib on : 25 nov. 2014  14:08:11
	 * @param selectedCycleDiplomesOutId the selectedCycleDiplomesOutId to set
	 */
	public void setSelectedCycleDiplomesOutId(Integer selectedCycleDiplomesOutId) {
		this.selectedCycleDiplomesOutId = selectedCycleDiplomesOutId;
	}

	/**
	 * [CycleBB.selectedCycleDiplomeOut] Getter 
	 * @author rlaib on : 24 nov. 2014  16:12:39
	 * @return the selectedCycleDiplomeOut
	 */
	public List<CycleDiplomeDto> getSelectedCycleDiplomeOut() {
		return selectedCycleDiplomeOut;
	}

	/**
	 * [CycleBB.selectedCycleDiplomeOut] Setter 
	 * @author rlaib on : 24 nov. 2014  16:12:39
	 * @param selectedCycleDiplomeOut the selectedCycleDiplomeOut to set
	 */
	public void setSelectedCycleDiplomeOut(
			List<CycleDiplomeDto> selectedCycleDiplomeOut) {
		this.selectedCycleDiplomeOut = selectedCycleDiplomeOut;
	}

	/**
	 * [CycleBB.selectedCycleDiplomes] Getter 
	 * @author rlaib on : 25 nov. 2014  11:45:34
	 * @return the selectedCycleDiplomes
	 */
	public List<CycleDiplomeDto> getSelectedCycleDiplomes() {
		return selectedCycleDiplomes;
	}

	/**
	 * [CycleBB.selectedCycleDiplomes] Setter 
	 * @author rlaib on : 25 nov. 2014  11:45:34
	 * @param selectedCycleDiplomes the selectedCycleDiplomes to set
	 */
	public void setSelectedCycleDiplomes(List<CycleDiplomeDto> selectedCycleDiplomes) {
		this.selectedCycleDiplomes = selectedCycleDiplomes;
	}

	/**
	 * [CycleBB.listTypesPassage] Getter 
	 * @author rlaib on : 16 juil. 2014  13:06:46
	 * @return the listTypesPassage
	 */
	public List<SelectItem> getListTypesPassage() {
		return listTypesPassage;
	}

	/**
	 * [CycleBB.listTypesPassage] Setter 
	 * @author rlaib on : 16 juil. 2014  13:06:46
	 * @param listTypesPassage the listTypesPassage to set
	 */
	public void setListTypesPassage(List<SelectItem> listTypesPassage) {
		this.listTypesPassage = listTypesPassage;
	}
	
	/**
	 * [CycleBB.selectedTypeFormationFilter] Getter 
	 * @author rlaib on : 16 juil. 2014  13:14:40
	 * @return the selectedTypeFormationFilter
	 */
	public String getSelectedTypeFormationFilter() {
		return selectedTypeFormationFilter;
	}

	/**
	 * [CycleBB.selectedTypeFormationFilter] Setter 
	 * @author rlaib on : 16 juil. 2014  13:14:40
	 * @param selectedTypeFormationFilter the selectedTypeFormationFilter to set
	 */
	public void setSelectedTypeFormationFilter(String selectedTypeFormationFilter) {
		this.selectedTypeFormationFilter = selectedTypeFormationFilter;
	}
		
	/**
	 * [CycleBB.selectedDetailCycleTitle] Getter 
	 * @author  Rafik LAIB on : 21 juil. 2014  22:54:32
	 * @return the selectedDetailCycleTitle
	 */
	public String getSelectedDetailCycleTitle() {
		return selectedDetailCycleTitle;
	}

	/**
	 * [CycleBB.selectedDetailCycleTitle] Setter 
	 * @author  Rafik LAIB on : 21 juil. 2014  22:54:32
	 * @param selectedDetailCycleTitle the selectedDetailCycleTitle to set
	 */
	public void setSelectedDetailCycleTitle(String selectedDetailCycleTitle) {
		this.selectedDetailCycleTitle = selectedDetailCycleTitle;
	}

	/**
	 * [CycleBB.listCycles] Getter 
	 * @author rlaib on : 16 juil. 2014  14:01:04
	 * @return the listCycles
	 */
	public List<CycleDto> getListCycles() {
		return listCycles;
	}

	/**
	 * [CycleBB.listCycles] Setter 
	 * @author rlaib on : 16 juil. 2014  14:01:04
	 * @param listCycles the listCycles to set
	 */
	public void setListCycles(List<CycleDto> listCycles) {
		this.listCycles = listCycles;
	}

	/**
	 * [CycleBB.listLevelsCycle] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  05:53:28
	 * @return the listLevelsCycle
	 */
	public List<NiveauDto> getListLevelsCycle() {
		return listLevelsCycle;
	}

	/**
	 * [CycleBB.listLevelsCycle] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  05:53:28
	 * @param listLevelsCycle the listLevelsCycle to set
	 */
	public void setListLevelsCycle(List<NiveauDto> listLevelsCycle) {
		this.listLevelsCycle = listLevelsCycle;
	}

	/**
	 * [CycleBB.showCycleDetails] Getter 
	 * @author rlaib on : 17 juil. 2014  13:22:03
	 * @return the showCycleDetails
	 */
	public boolean isShowCycleDetails() {
//		try {
//			showCycleDetails = (cycleId != null && !cycleId.trim().isEmpty() && !cycleId.equals("null") && (Integer.parseInt(cycleId)>=0));
//		}
//		catch (Exception e) {
//	
//			showCycleDetails = false;
//		}
		return showCycleDetails;
	}

	/**
	 * [CycleBB.showCycleDetails] Setter 
	 * @author rlaib on : 17 juil. 2014  13:22:03
	 * @param showCycleDetails the showCycleDetails to set
	 */
	public void setShowCycleDetails(boolean showCycleDetails) {
		this.showCycleDetails = showCycleDetails;
	}

	/**
	 * [CycleBB.showBtnSave] Getter 
	 * @author rlaib on : 20 juil. 2014  13:34:13
	 * @return the showBtnSave
	 */
	public boolean isShowBtnSave() {
		try {
			showBtnSave = (cycleId != null && !cycleId.trim().isEmpty() && !cycleId.equals("null") && (Integer.parseInt(cycleId)>0));
		}
		catch (Exception e) {
	
			showBtnSave = false;
		}
		return showBtnSave;
	}

	/**
	 * [CycleBB.showBtnSave] Setter 
	 * @author rlaib on : 20 juil. 2014  13:34:13
	 * @param showBtnSave the showBtnSave to set
	 */
	public void setShowBtnSave(boolean showBtnSave) {
		this.showBtnSave = showBtnSave;
	}

	/**
	 * [CycleBB.addOrEditDialog] Getter 
	 * @author rlaib on : 25 nov. 2014  10:03:05
	 * @return the addOrEditDialog
	 */
	public boolean isAddOrEditDialog() {
		return addOrEditDialog;
	}

	/**
	 * [CycleBB.addOrEditDialog] Setter 
	 * @author rlaib on : 25 nov. 2014  10:03:05
	 * @param addOrEditDialog the addOrEditDialog to set
	 */
	public void setAddOrEditDialog(boolean addOrEditDialog) {
		this.addOrEditDialog = addOrEditDialog;
	}

	/**
	 * [CycleBB.sensIsIn] Getter 
	 * @author rlaib on : 25 nov. 2014  10:10:39
	 * @return the sensIsIn
	 */
	public boolean isSensIsIn() {
		return sensIsIn;
	}

	/**
	 * [CycleBB.sensIsIn] Setter 
	 * @author rlaib on : 25 nov. 2014  10:10:39
	 * @param sensIsIn the sensIsIn to set
	 */
	public void setSensIsIn(boolean sensIsIn) {
		this.sensIsIn = sensIsIn;
	}

	/**
	 * [CycleBB.selectedCycleDiplome] Getter 
	 * @author rlaib on : 25 nov. 2014  10:07:33
	 * @return the selectedCycleDiplome
	 */
	public CycleDiplomeDto getSelectedCycleDiplome() {
		return selectedCycleDiplome;
	}

	/**
	 * [CycleBB.selectedCycleDiplome] Setter 
	 * @author rlaib on : 25 nov. 2014  10:07:33
	 * @param selectedCycleDiplome the selectedCycleDiplome to set
	 */
	public void setSelectedCycleDiplome(CycleDiplomeDto selectedCycleDiplome) {
		this.selectedCycleDiplome = selectedCycleDiplome;
	}

	/**
	 * [CycleBB.selectedCycle] Getter 
	 * @author rlaib on : 17 juil. 2014  13:57:50
	 * @return the selectedCycle
	 */
	public CycleDto getSelectedCycle() {
		return selectedCycle;
	}

	/**
	 * [CycleBB.selectedCycle] Setter 
	 * @author rlaib on : 17 juil. 2014  13:57:50
	 * @param selectedCycle the selectedCycle to set
	 */
	public void setSelectedCycle(CycleDto selectedCycle) {
		this.selectedCycle = selectedCycle;
	}

	// ===================================================================  
	// ActionListener
	// ===================================================================
	/**
	 * [CycleBB.addCycle] Method 
	 * @author rlaib  on : 25 nov. 2014  10:13:34
	 */
	public void addCycle() {
			try {
				selectedCycle = new CycleDto();
				toEditCycle = false;
				listLevelsCycle = null;
				selectedCycleDiplomeOut = null;
				selectedCycleDiplomesIn = null;
				selectedCycle.setStatut(true);
				selectedCycle.setRang(getMaxCycleRangs(listCycles) + 1);
				cycleId = "0";
				showCycleDetails = true;
				showBtnSave = true;
				selectedDetailCycleTitle = MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_PANEL_NEW_TITLE);

			}
			 catch(Exception e){
				 e.printStackTrace();
			 }
	}
	
	/**
	 * [CycleBB.saveCycle] Method 
	 * @author rlaib  on : 25 nov. 2014  10:13:27
	 */
	public void saveCycle() {
		
		try {
			
				if(checkRangeAndUnicityCycle(listCycles)) {
						selectedCycle.setRefCodeTypeFormation(typeFormationCycleCode);
						selectedCycle = cycleService.insertOrUpdate(selectedCycle) ;
						cycleId = String.valueOf(selectedCycle.getId());
			 			listCycles = cycleService.findByTypeFormationCode(typeFormationCycleCode);
						MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
																				,MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_SAVE_SUCCESS));
						toEditCycle = true;
				}
		}
		 catch(Exception e){
				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
						,MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_SAVE_FAILURE)); 
			 
		 }
	}
	
	/**
	 * [CycleBB.checkRangeCycle] Method 
	 * @author rlaib  on : 20 juil. 2014  13:58:41
	 */
	private boolean checkRangeAndUnicityCycle(List<CycleDto> cycles) {
		
		boolean existingRange = false;
		boolean existingCode = false;
		boolean existingLabel = false;
		boolean existingNewRange = false;
		boolean existingNewCode = false;
		boolean existingNewLabel = false;
		int count = 0;
		int maxRang = 0;
		
		if(cycles != null) {
		for (CycleDto _cycleDto : cycles) {
			if((selectedCycle.getId() != _cycleDto.getId())) {
					if(_cycleDto.getRang() == selectedCycle.getRang()) {
						if((selectedCycle.getId()>0))
							existingRange = true;
						else
							existingNewRange = true;
							
					}
					if(_cycleDto.getCode().equals(selectedCycle.getCode())) {
						if((selectedCycle.getId()>0))
							existingCode = true;
						else
							existingNewCode = true;
					}
					if(_cycleDto.getLibelleLongLt().equals(selectedCycle.getLibelleLongLt())) {
						if((selectedCycle.getId()>0))
							existingLabel = true;
						else
							existingNewLabel = true;
					}
			}
		} 		
		 count = cycles.size();
		 maxRang = getMaxCycleRangs(cycles);
		}
		
		// Enregistrement d un cycle existant
 		if (cycleId != null && !cycleId.trim().isEmpty() && !cycleId.equals("null") &&(Integer.parseInt(cycleId)>0)) {
 			if((existingCode)) {
 					Object[] params = {selectedCycle.getCode()};
					MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
							,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_CODE, params,"fr")); 
 				return false;
 			}
 			if((existingLabel)) {
 					Object[] params = {selectedCycle.getLibelleLongLt()};
					MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
								,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_LABEL, params,"fr"));
				return false;
			}
 			if((existingRange)) {
 				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
 						,MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_RANGE)); 
 				return false;
 			}
 		}
 	// Enregistrement d un nouveau cycle
 		else {
 	
 			if(existingNewRange) {
 				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
 						,MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_RANGE)); 
 				return false;
 			}
 			// Test si le code est unique
 			if(existingNewCode) {
 					Object[] params = {selectedCycle.getCode()};
 					MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
 					,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_CODE, params,"fr")); 
 					return false;
 			}
 			// Test si le libelle long Fr est unique
 					if(existingNewLabel) {
 							Object[] params = {selectedCycle.getLibelleLongLt()};
 							MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
 							,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_LABEL, params,"fr")); 
 							return false;
 			}
 			// Test sur le rang suivant
 					if((selectedCycle.getRang() - maxRang > 1)) {
 		 				Object[] params = {count + 1};
 		 				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
 		 				,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_RANGE_FISRT, params,"fr")); 
 		 				return false;
 		 			}
 		}
		
		return true;
			
	}

	/**
	 * [CycleBB.removeCycle] Method 
	 * @author rlaib  on : 25 nov. 2014  10:13:43
	 */
	public void removeCycle() {
		
		try {
					cycleService.remove(selectedCycle.getId()) ;
					listCycles = cycleService.findByTypeFormationCode(typeFormationCycleCode);
					cycleId = null;
					showCycleDetails = false;
					Object[] params = {selectedCycle.getLibelleLongLt()};
					MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
							,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_REMOVE_SUCCESS, params,"fr")); 
 	
		}
		catch(Exception e){
		 	Object[] params = {selectedCycle.getLibelleLongLt()};
			MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
					,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_REMOVE_FAILURE, params,"fr")); 
		 
	 	}
	}
	
	// ===================================================================  
	// Listeners
	// ===================================================================
	public void  handleTypesFormationFilterChange(AjaxBehaviorEvent event) {
		
		if(typeFormationCycleCode != null) {
			listCycles = cycleService.findByTypeFormationCode(typeFormationCycleCode);
			cycleId = null;
			showCycleDetails = false;
		}
	}
	
	public void  handleNiveauxDiplomesChange(AjaxBehaviorEvent event) {
		
		if(selectedNiveauDiplomeId != null) {
			listTypesDiplomes= initTypesDiplomes(selectedNiveauDiplomeId);
		}
	}

	public void onCycleRowSelect(SelectEvent event) {

		try {
			loadCycleDetails();
		}
		catch (Exception e)  {
			e.printStackTrace();
		}

	}
	
	// ===================================================================  
	// Actions and Methods
	// ===================================================================
	public String goBack() {
		
		return this.outcomePage;
	}
	
	public void editCycle() {
		
		try {
			loadCycleDetails();
		}
		catch (Exception e)  {
			e.printStackTrace();
		}
	}
	
	public void prepareAddCycleDiplome() {
		
		addOrEditDialog = false;
		selectedCycleDiplome = new CycleDiplomeDto();
		 
		//Initialisation des niveaux et type diplomes
		if (selectedNiveauDiplomeId == null) {
			listNiveauxDiplomes = initNomenclatureListByIdValue(OfConstants.NC_FVE_NIVEAUX_DIPLOMES);
			if(listNiveauxDiplomes!= null && listNiveauxDiplomes.size()>0) {
				selectedNiveauDiplomeId = Integer.parseInt(listNiveauxDiplomes.get(0).getValue().toString());
			}
			else {
				listTypesDiplomes = null;
				selectedNiveauDiplomeId = null;
			}
		}
		listTypesDiplomes = initTypesDiplomes(selectedNiveauDiplomeId);
			
		if(sensIsIn) {
				dialogDiplomeTitle= MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
					OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_ADD_DIPLOME_IN) ;
		}
		else {
			
			dialogDiplomeTitle= MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
					OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_ADD_DIPLOME_OUT) ;

		}
		
	}
	
	public void prepareEditCycleDiplome(CycleDiplomeDto selectedCycleDiplomesInId) {
		
		addOrEditDialog = true;
		//selectedCycleDiplome = cycleService.findCycleDiplomeById(selectedCycleDiplomesInId);
		selectedCycleDiplome = selectedCycleDiplomesInId;
		selectedTypeDiplomeId = selectedCycleDiplome.getDiplomeId();
		selectedNiveauDiplomeId = selectedCycleDiplome.getNiveauDiplomeId();
		selectedRang = selectedCycleDiplome.getRang();
		selectedSens = selectedCycleDiplome.getSens();
		//Initialisation des niveaux et type diplomes
		listNiveauxDiplomes = initNomenclatureListByIdValue(OfConstants.NC_FVE_NIVEAUX_DIPLOMES);
		if(listNiveauxDiplomes!= null && listNiveauxDiplomes.size()>0) {
			listTypesDiplomes = initTypesDiplomes(selectedNiveauDiplomeId);
		}
		else {
			listTypesDiplomes = null;
			selectedNiveauDiplomeId = null;
		}
		if(sensIsIn) {
			dialogDiplomeTitle= MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
					OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_UPDATE_DIPLOME_IN) ;
		}
		else {
			
			dialogDiplomeTitle= MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
					OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_UPDATE_DIPLOME_OUT) ;
			
		}
		
	}

	public void saveCycleDiplome() {
	
		RequestContext context = RequestContext.getCurrentInstance();
		try {
				selectedCycleDiplome.setCycleId(selectedCycle.getId());
				selectedCycleDiplome.setDiplomeId(selectedTypeDiplomeId);
				
				if(sensIsIn) {
					selectedCycleDiplome.setSens(OfConstants.CYCLE_DIPLOME_SENS_IN);
				}
				else {
					if(selectedRang == null) {
						selectedRang = 1;
					}
					selectedCycleDiplome.setRang(selectedRang);
					selectedCycleDiplome.setSens(OfConstants.CYCLE_DIPLOME_SENS_OUT);
					if(selectedCycleDiplomeOut != null) {
						for(CycleDiplomeDto cycleDiplomeDto : selectedCycleDiplomeOut) {
							if(cycleDiplomeDto.getDiplomeId() != null && !cycleDiplomeDto.getDiplomeId().equals(selectedTypeDiplomeId)) {
								cycleDiplomeDto.setRang(3 - selectedRang);
								cycleService.insertOrUpdate(cycleDiplomeDto);
							}
						}
					}
					
				}
				 
				//Checking for duplicated CycleDomaineDto
				if(addOrEditDialog) {
					for (CycleDiplomeDto _cycleDiplomeDto : selectedCycleDiplomes) {
							if(selectedCycleDiplome.getDiplomeId().equals(_cycleDiplomeDto.getDiplomeId()) 
									&& !_cycleDiplomeDto.getId().equals(selectedCycleDiplome.getId())
									&& selectedCycleDiplome.getCycleId().equals(_cycleDiplomeDto.getCycleId())
									&& selectedCycleDiplome.getSens().equals(_cycleDiplomeDto.getSens())) {
								
										MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
															OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_SAVE_DIPLOME_ALREADY_EXISTS),
													MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
															OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_SAVE_DIPLOME_ALREADY_EXISTS));
										context.addCallbackParam("isValid", false);
										return;
							}
					}
				}
				else {
					
					List<CycleDiplomeDto> _list = selectedCycleDiplomes;
					for (CycleDiplomeDto cycleDiplomeDto : _list) {
							if(cycleDiplomeDto.getId().equals(selectedTypeDiplomeId)) {
								_list.remove(cycleDiplomeDto);
							}
					}
					for (CycleDiplomeDto cycleDiplomeDto : _list) {
						if(selectedCycleDiplome.getDiplomeId().equals(cycleDiplomeDto.getDiplomeId()) 
								&& selectedCycleDiplome.getCycleId().equals(cycleDiplomeDto.getCycleId())
								&& selectedCycleDiplome.getSens().equals(cycleDiplomeDto.getSens())) {
							
							MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
									OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_SAVE_DIPLOME_ALREADY_EXISTS),
							MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
									OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_SAVE_DIPLOME_ALREADY_EXISTS));
							context.addCallbackParam("isValid", false);
							return;
							
						}
					}
				}
				cycleService.insertOrUpdate(selectedCycleDiplome);
				
				if(sensIsIn) {
							MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
														OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_ADD_DIPLOME_IN_SUCCESS),
											MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
														OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_ADD_DIPLOME_IN_SUCCESS));
				}
				else {
							MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
														OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_ADD_DIPLOME_OUT_SUCCESS),
											MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
														OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_ADD_DIPLOME_OUT_SUCCESS));
				}
				initCycleDiplomes();
				context.addCallbackParam("isValid", true);
		}
		catch (Exception e) {
			e.printStackTrace();
			if(sensIsIn) {
							MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
													OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_SAVE_DIPLOME_IN_FAILURE),
											MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
													OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_SAVE_DIPLOME_IN_FAILURE));
			}
			else {
							MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
													OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_SAVE_DIPLOME_OUT_FAILURE),
											MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
													OfConstants.CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_SAVE_DIPLOME_OUT_FAILURE));
			}
			context.addCallbackParam("isValid", false);
		}
	}
	
	public void removeCycleDiplome() {
		try {
					if(sensIsIn) {
						cycleService.removeCycleDiplome(selectedCycleDiplomesInId);
						initCycleDiplomes();
						selectedCycleDiplomesInId = null;
						MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
													OfConstants.CYCLE_BUNDLE_KEY_DIPLOME_MSG_DELETE_DIPLOME_IN_SUCCESS),
										MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
													OfConstants.CYCLE_BUNDLE_KEY_DIPLOME_MSG_DELETE_DIPLOME_IN_SUCCESS));
					}
					else {
						cycleService.removeCycleDiplome(selectedCycleDiplomesOutId);
						initCycleDiplomes();
						selectedCycleDiplomesOutId = null;
						MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
													OfConstants.CYCLE_BUNDLE_KEY_DIPLOME_MSG_DELETE_DIPLOME_OUT_SUCCESS),
										MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
													OfConstants.CYCLE_BUNDLE_KEY_DIPLOME_MSG_DELETE_DIPLOME_OUT_SUCCESS));
					}
					
			}
		catch(Exception e){
			e.printStackTrace();
			if(sensIsIn) {
							MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
													OfConstants.CYCLE_BUNDLE_KEY_DIPLOME_MSG_DELETE_DIPLOME_IN_FAILURE),
											MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
													OfConstants.CYCLE_BUNDLE_KEY_DIPLOME_MSG_DELETE_DIPLOME_IN_FAILURE));
			}
			else {
							MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
													OfConstants.CYCLE_BUNDLE_KEY_DIPLOME_MSG_DELETE_DIPLOME_OUT_FAILURE),
											MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,
													OfConstants.CYCLE_BUNDLE_KEY_DIPLOME_MSG_DELETE_DIPLOME_OUT_FAILURE));
			}
	 	}
	}
	// ===================================================================  
	// Functions Helper
	// ===================================================================

	private int getMaxCycleRangs(List<CycleDto> list) {
		
		int maxRang = 0;
		if(list != null && list.size() > 0) {
			maxRang = list.get(0).getRang();
			
			for (CycleDto _cycleDto : list) {
				if(_cycleDto.getRang() > maxRang)
					maxRang = _cycleDto.getRang();
			} 		
		}
		return maxRang;
	}
	
	private List<SelectItem> initNomenclatureList(String ncNameTypeFormation) {

		try {
					List<NomenclatureDto> _list = nomenclatureService.findByName(ncNameTypeFormation);
					List<SelectItem> result = new ArrayList<SelectItem>();
					for (NomenclatureDto _nomenclatureDto : _list) {
						result.add(new SelectItem(_nomenclatureDto.getCode(),_nomenclatureDto.getLibelleLongFr()));
					}
					return result;
		}
		 catch(Exception e){
			 e.printStackTrace();
		 }
		return null;
	
	}
	
	private List<SelectItem> initNomenclatureListByIdValue(String ncNameTypeFormation) {
		
		try {
			List<NomenclatureDto> _list = nomenclatureService.findByName(ncNameTypeFormation);
			List<SelectItem> result = new ArrayList<SelectItem>();
			for (NomenclatureDto _nomenclatureDto : _list) {
				result.add(new SelectItem(_nomenclatureDto.getId(),_nomenclatureDto.getLibelleLongFr()));
			}
			return result;
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	void initNomenclatures() {

		try {
			listTypesFormation = initNomenclatureList(OfConstants.NC_NAME_TYPE_FORMATION);
	 		listTypesPassage = initNomenclatureList(OfConstants.NC_NAME_TYPE_CYCLE_PASSAGE);
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * [CycleBB.initTypesDiplomes] Method 
	 * @author rlaib  on : 24 nov. 2014  16:21:10
	 * @param selectedNiveauDiplomeId2
	 */
	private List<SelectItem> initTypesDiplomes(Integer niveauDiplomeId) {
		
		try {
					List<NomenclatureDto> _list = nomenclatureService.findByReference(OfConstants.NC_FVE_TYPES_DIPLOMES, niveauDiplomeId);
					List<SelectItem> result = new ArrayList<SelectItem>();
					for (NomenclatureDto _nomenclatureDto : _list) {
						result.add(new SelectItem(_nomenclatureDto.getId(),_nomenclatureDto.getLibelleLongFr()));
					}
					return result;
				}
				catch(Exception e){
						e.printStackTrace();
				}
				return null;
		}

	/**
	 * [CycleBB.selectedRang] Getter 
	 * @author MAKERRI Sofiane on : 23 févr. 2015  15:18:34
	 * @return the selectedRang
	 */
	public Integer getSelectedRang() {
		return selectedRang;
	}

	/**
	 * [CycleBB.selectedRang] Setter 
	 * @author MAKERRI Sofiane on : 23 févr. 2015  15:18:34
	 * @param selectedRang the selectedRang to set
	 */
	public void setSelectedRang(Integer selectedRang) {
		this.selectedRang = selectedRang;
	}

	/**
	 * [CycleBB.selectedSens] Getter 
	 * @author MAKERRI Sofiane on : 23 févr. 2015  15:38:14
	 * @return the selectedSens
	 */
	public String getSelectedSens() {
		return selectedSens;
	}

	/**
	 * [CycleBB.selectedSens] Setter 
	 * @author MAKERRI Sofiane on : 23 févr. 2015  15:38:14
	 * @param selectedSens the selectedSens to set
	 */
	public void setSelectedSens(String selectedSens) {
		this.selectedSens = selectedSens;
	}
	
	
	
	
}
