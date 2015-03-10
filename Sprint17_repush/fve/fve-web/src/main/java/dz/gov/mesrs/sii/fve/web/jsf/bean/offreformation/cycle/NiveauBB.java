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

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
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
@ManagedBean(name = "niveauBB")
@ViewScoped
public class NiveauBB {

	public NiveauBB() {
		
	}
	
	@PostConstruct
	public void init() {
		
		 try	{
			 
			 		listTypesFormation = initNomenclatureList(OfConstants.NC_NAME_TYPE_FORMATION);
				 	if(listTypesFormation  != null && listTypesFormation .size()>0) {
			 			if (!(typeFormationLevelCode!= null && !typeFormationLevelCode.trim().isEmpty() && !typeFormationLevelCode.equals("null"))) {
			 				typeFormationLevelCode = listTypesFormation.get(0).getValue().toString();
			 			}
			 			listTypesCycles = dtosToSelectItems(cycleService.findByTypeFormationCode(typeFormationLevelCode));
			 			if (listTypesCycles!= null  && listTypesCycles.size()>0) {
			 				cycleLevelId = listTypesCycles.get(0).getValue().toString();
			 				listLevels = niveauService.findByCycleId(Integer.parseInt(cycleLevelId));
			 			}
			 		}
				 	
				 	if ((cycleLevelId!= null && !cycleLevelId.trim().isEmpty() && !cycleLevelId.equals("null"))) {
						listLevels = niveauService.findByCycleId(Integer.parseInt(cycleLevelId));
				 	}
				 	
					if (niveauId != null && !niveauId.trim().isEmpty() && !niveauId.equals("null") &&(Integer.parseInt(niveauId)>0)) {
			 			loadLevelDetails();
			 		}
			 		else {
			 			selectedLevel = new NiveauDto();
			 			selectedDetailLevelTitle = MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.LEVEL_EDIT_BUNDLE_KEY_EDIT_PAGE_PANEL_NEW_TITLE);
			 		}

		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	
	}

	/**
	 * [NiveauBB.loadLevelDetails] Method 
	 * @author rlaib  on : 11 sept. 2014  09:50:16
	 */
	private void loadLevelDetails() {
		listPeriodsLevel = periodeService.findByLevelId(selectedLevel.getId());
		showLevelDetails = true;
		showBtnRemove = true;
		showBtnSave = true;
		Object[] params = {selectedLevel.getLibelleLongLt()};
		selectedDetailLevelTitle = MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME
				,OfConstants.LEVEL_EDIT_BUNDLE_KEY_EDIT_PAGE_PANEL_TITLE, params,"fr");
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
	 * [NiveauBB.cycleService] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  02:13:54
	 * @return the cycleService
	 */
	public CycleService getCycleService() {
		return cycleService;
	}

	/**
	 * [NiveauBB.cycleService] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  02:13:54
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
	 * [NiveauBB.periodeService] Getter 
	 * @author rlaib on : 22 juil. 2014  13:55:51
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [NiveauBB.periodeService] Setter 
	 * @author rlaib on : 22 juil. 2014  13:55:51
	 * @param periodeService the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [NiveauBB.nomenclatureService] Getter 
	 * @author rlaib on : 12 oct. 2014  10:17:34
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [NiveauBB.nomenclatureService] Setter 
	 * @author rlaib on : 12 oct. 2014  10:17:34
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	// ===================================================================  
	// Parameters
	// ===================================================================
	private String outcomePage;
	private String niveauId;
	private String typeFormationLevelCode;
	private String cycleLevelId;
	
	// ===================================================================  
	// Properties Model
	// ===================================================================
	private List<SelectItem> listTypesFormation;
	private List<SelectItem> listTypesPeriodicites;
	private List<SelectItem> listTypesCycles;
	private String selectedDetailLevelTitle;
	private List<NiveauDto> listLevels;
	private boolean showLevelDetails;
	private boolean showBtnSave;
	private boolean showBtnRemove;
	private NiveauDto selectedLevel;
	private List<PeriodeDto> listPeriodsLevel;
	
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
	 * [CycleBB.outcomePage] Setter 
	 * @author rlaib on : 16 juil. 2014  12:13:25
	 * @param outcomePage the outcomePage to set
	 */
	public void setOutcomePage(String outcomePage) {
		this.outcomePage = outcomePage;
	}
	
	/**
	 * [NiveauBB.typeFormationLevelCode] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  02:05:19
	 * @return the typeFormationLevelCode
	 */
	public String getTypeFormationLevelCode() {
		return typeFormationLevelCode;
	}

	/**
	 * [NiveauBB.typeFormationLevelCode] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  02:05:19
	 * @param typeFormationLevelCode the typeFormationLevelCode to set
	 */
	public void setTypeFormationLevelCode(String typeFormationLevelCode) {
		this.typeFormationLevelCode = typeFormationLevelCode;
	}

	/**
	 * [NiveauBB.cycleLevelId] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  03:27:07
	 * @return the cycleLevelId
	 */
	public String getCycleLevelId() {
		return cycleLevelId;
	}

	/**
	 * [NiveauBB.cycleLevelId] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  03:27:07
	 * @param cycleLevelId the cycleLevelId to set
	 */
	public void setCycleLevelId(String cycleLevelId) {
		this.cycleLevelId = cycleLevelId;
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
	 * [CycleBB.listTypesPeriodicites] Getter 
	 * @author rlaib on : 16 juil. 2014  13:06:46
	 * @return the listTypesPeriodicites
	 */
	public List<SelectItem> getListTypesPeriodicites() {
		return listTypesPeriodicites;
	}

	/**
	 * [CycleBB.listTypesPeriodicites] Setter 
	 * @author rlaib on : 16 juil. 2014  13:06:46
	 * @param listTypesPeriodicites the listTypesPeriodicites to set
	 */
	public void setListTypesPeriodicites(List<SelectItem> listTypesPeriodicites) {
		this.listTypesPeriodicites = listTypesPeriodicites;
	}

	/**
	 * [NiveauBB.niveauId] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  01:57:30
	 * @return the niveauId
	 */
	public String getNiveauId() {
		return niveauId;
	}

	/**
	 * [NiveauBB.niveauId] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  01:57:30
	 * @param niveauId the niveauId to set
	 */
	public void setNiveauId(String niveauId) {
		this.niveauId = niveauId;
	}

	/**
	 * [NiveauBB.listTypesCycles] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  01:57:30
	 * @return the listTypesCycles
	 */
	public List<SelectItem> getListTypesCycles() {
		return listTypesCycles;
	}

	/**
	 * [NiveauBB.listTypesCycles] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  01:57:30
	 * @param listTypesCycles the listTypesCycles to set
	 */
	public void setListTypesCycles(List<SelectItem> listTypesCycles) {
		this.listTypesCycles = listTypesCycles;
	}

	/**
	 * [NiveauBB.selectedDetailLevelTitle] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  01:57:30
	 * @return the selectedDetailLevelTitle
	 */
	public String getSelectedDetailLevelTitle() {
		return selectedDetailLevelTitle;
	}

	/**
	 * [NiveauBB.selectedDetailLevelTitle] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  01:57:30
	 * @param selectedDetailLevelTitle the selectedDetailLevelTitle to set
	 */
	public void setSelectedDetailLevelTitle(String selectedDetailLevelTitle) {
		this.selectedDetailLevelTitle = selectedDetailLevelTitle;
	}

	/**
	 * [NiveauBB.listLevels] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  01:57:30
	 * @return the listLevels
	 */
	public List<NiveauDto> getListLevels() {
		return listLevels;
	}

	/**
	 * [NiveauBB.listLevels] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  01:57:30
	 * @param listLevels the listLevels to set
	 */
	public void setListLevels(List<NiveauDto> listLevels) {
		this.listLevels = listLevels;
	}

	/**
	 * [NiveauBB.showLevelDetails] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  01:57:30
	 * @return the showLevelDetails
	 */
	public boolean isShowLevelDetails() {
		
		return showLevelDetails;
	}

	/**
	 * [NiveauBB.showLevelDetails] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  01:57:30
	 * @param showLevelDetails the showLevelDetails to set
	 */
	public void setShowLevelDetails(boolean showLevelDetails) {
		this.showLevelDetails = showLevelDetails;
	}

	/**
	 * [NiveauBB.showBtnSave] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  01:57:30
	 * @return the showBtnSave
	 */
	public boolean isShowBtnSave() {
		return showBtnSave;
	}

	/**
	 * [NiveauBB.showBtnSave] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  01:57:30
	 * @param showBtnSave the showBtnSave to set
	 */
	public void setShowBtnSave(boolean showBtnSave) {
		this.showBtnSave = showBtnSave;
	}

	/**
	 * [NiveauBB.showBtnRemove] Getter 
	 * @author rlaib on : 22 juil. 2014  16:28:27
	 * @return the showBtnRemove
	 */
	public boolean isShowBtnRemove() {
	
		return showBtnRemove;
	}

	/**
	 * [NiveauBB.showBtnRemove] Setter 
	 * @author rlaib on : 22 juil. 2014  16:28:27
	 * @param showBtnRemove the showBtnRemove to set
	 */
	public void setShowBtnRemove(boolean showBtnRemove) {
		this.showBtnRemove = showBtnRemove;
	}

	/**
	 * [NiveauBB.selectedLevel] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  01:57:30
	 * @return the selectedLevel
	 */
	public NiveauDto getSelectedLevel() {
		return selectedLevel;
	}

	/**
	 * [NiveauBB.selectedLevel] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  01:57:30
	 * @param selectedLevel the selectedLevel to set
	 */
	public void setSelectedLevel(NiveauDto selectedLevel) {
		this.selectedLevel = selectedLevel;
	}
	
	/**
	 * [NiveauBB.listPeriodsLevel] Getter 
	 * @author rlaib on : 22 juil. 2014  13:40:43
	 * @return the listPeriodsLevel
	 */
	public List<PeriodeDto> getListPeriodsLevel() {
		return listPeriodsLevel;
	}

	/**
	 * [NiveauBB.listPeriodsLevel] Setter 
	 * @author rlaib on : 22 juil. 2014  13:40:43
	 * @param listPeriodsLevel the listPeriodsLevel to set
	 */
	public void setListPeriodsLevel(List<PeriodeDto> listPeriodsLevel) {
		this.listPeriodsLevel = listPeriodsLevel;
	}

	// ===================================================================  
	// ActionListener
	// ===================================================================
	public void addLevel() {
		try {
			selectedLevel = new NiveauDto();
			listPeriodsLevel = null;
			selectedLevel.setRang(getMaxCycleRangs(listLevels) + 1);
			niveauId = "0";
			showLevelDetails = true;
			showBtnSave = true;
			selectedDetailLevelTitle = MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.LEVEL_EDIT_BUNDLE_KEY_EDIT_PAGE_PANEL_NEW_TITLE);

		}
		 catch(Exception e){
			 e.printStackTrace();
		 }
	}
	
	public void saveLevel() {
		
		try {
			
				if(checkRangeAndUnicityLevel(listLevels)) {
						selectedLevel.setIdCycle(Integer.parseInt(cycleLevelId));
						selectedLevel = niveauService.insertOrUpdate(selectedLevel) ;
						niveauId = String.valueOf(selectedLevel.getId());
						listLevels = niveauService.findByCycleId(Integer.parseInt(cycleLevelId));
						MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
																				,MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_SAVE_SUCCESS));
						
						showBtnRemove = true;
				}
		}
		 catch(Exception e){
				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
						,MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_SAVE_FAILURE)); 
			 
		 }
	}
	
	public void removeLevel() {
		
		try {
					niveauService.remove(selectedLevel.getId()) ;
					listLevels = niveauService.findByCycleId(selectedLevel.getIdCycle());
					niveauId = null;
					showLevelDetails = false;
					Object[] params = {selectedLevel.getLibelleLongLt()};
					MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
							,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.LEVEL_EDIT_BUNDLE_KEY_EDIT_PAGE_REMOVE_SUCCESS, params,"fr")); 
 	
		}
		catch(Exception e){
		 	Object[] params = {selectedLevel.getLibelleLongLt()};
			MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
					,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.LEVEL_EDIT_BUNDLE_KEY_EDIT_PAGE_REMOVE_FAILURE, params,"fr")); 
		 
	 	}
	}
	
	// ===================================================================  
	// Listeners
	// ===================================================================
	public void  handleTypesFormationFilterChange(AjaxBehaviorEvent event) {
		
		if ((typeFormationLevelCode!= null && !typeFormationLevelCode.trim().isEmpty() && !typeFormationLevelCode.equals("null"))) {
	 			listTypesCycles = dtosToSelectItems(cycleService.findByTypeFormationCode(typeFormationLevelCode));
	 			if (listTypesCycles!= null  && listTypesCycles.size()>0) {
	 				cycleLevelId = listTypesCycles.get(0).getValue().toString();
	 				listLevels = niveauService.findByCycleId(Integer.parseInt(cycleLevelId));
	 			}
	 			niveauId = null;
	 			showLevelDetails = false;
	 			showBtnRemove = false;
	 			showBtnSave = false;
		}
	
	}
	public void  handleCyclesFilterChange(AjaxBehaviorEvent event) {
		
		if ((cycleLevelId!= null && !cycleLevelId.trim().isEmpty() && !cycleLevelId.equals("null"))) {
			
			listLevels = niveauService.findByCycleId(Integer.parseInt(cycleLevelId));
			niveauId = null;
			CycleDto oneCycle = cycleService.findById(Integer.parseInt(cycleLevelId));
			if(oneCycle != null)
				typeFormationLevelCode = oneCycle.getRefCodeTypeFormation();
			
			niveauId = null;
			showLevelDetails = false;
			showBtnRemove = false;
 			showBtnSave = false;
		}
		
	}

	public void onLevelRowSelect(SelectEvent event) {

		try {
			loadLevelDetails();
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
	
	public void editLevel() {
		
		try {
			loadLevelDetails();
		}
		catch (Exception ex)  {
			
		}
	}
	
	// ===================================================================  
	// Functions Helper
	// ===================================================================
	/**
	 * [NiveauBB.getMaxCycleRangs] Method 
	 * @author rlaib  on : 13 oct. 2014  09:53:47
	 * @param list
	 * @return
	 */
	private int getMaxCycleRangs(List<NiveauDto> list) {
		
		int maxRang = 0;
		if(list != null && list.size() > 0) {
			maxRang = list.get(0).getRang();
			
			for (NiveauDto _niveauDto : list) {
				if(_niveauDto.getRang() > maxRang)
					maxRang = _niveauDto.getRang();
			} 		
		}
		return maxRang;
	}

	/**
	 * [NiveauBB.checkRangeAndUnicityLevel] Method 
	 * @author rlaib  on : 13 oct. 2014  09:53:39
	 * @param levels
	 * @return
	 */
	private boolean checkRangeAndUnicityLevel(List<NiveauDto> levels) {
		
		boolean existingRange = false;
		boolean existingCode = false;
		boolean existingLabel = false;
		boolean existingNewRange = false;
		boolean existingNewCode = false;
		boolean existingNewLabel = false;
		int count = 0;
		int maxRang = 0;
		if(levels != null) {
		for (NiveauDto _niveauDto : levels) {
			if((selectedLevel.getId() != _niveauDto.getId())) {
					if(_niveauDto.getRang() == selectedLevel.getRang()) {
						if((selectedLevel.getId()>0))
							existingRange = true;
						else
							existingNewRange = true;
							
					}
					if(_niveauDto.getCode().equals(selectedLevel.getCode())) {
						if((selectedLevel.getId()>0))
							existingCode = true;
						else
							existingNewCode = true;
					}
					if(_niveauDto.getLibelleLongLt().equals(selectedLevel.getLibelleLongLt())) {
						if((selectedLevel.getId()>0))
							existingLabel = true;
						else
							existingNewLabel = true;
					}
			}
		} 	
		count = levels.size();
		maxRang = getMaxCycleRangs(levels);
		}
	
		// Enregistrement d un niveau existant
 		if (niveauId != null && !niveauId.trim().isEmpty() && !niveauId.equals("null") &&(Integer.parseInt(niveauId)>0)) {
 			if((existingCode)) {
 					Object[] params = {selectedLevel.getCode()};
					MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
							,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_CODE, params,"fr")); 
 				return false;
 			}
 			if((existingLabel)) {
 					Object[] params = {selectedLevel.getLibelleLongLt()};
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
 	// Enregistrement d un nouveau niveau
 		else {
 	
 			if(existingNewRange) {
 				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
 						,MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_RANGE)); 
 				return false;
 			}
 			// Test si le code est unique
 			if(existingNewCode) {
 					Object[] params = {selectedLevel.getCode()};
 					MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
 					,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_CODE, params,"fr")); 
 					return false;
 			}
 			// Test si le libelle long Fr est unique
 					if(existingNewLabel) {
 							Object[] params = {selectedLevel.getLibelleLongLt()};
 							MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
 							,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_LABEL, params,"fr")); 
 							return false;
 			}
 			// Test sur le rang suivant
 					if((selectedLevel.getRang() - maxRang > 1)) {
 		 				Object[] params = {count + 1};
 		 				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
 		 				,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_RANGE_FISRT, params,"fr")); 
 		 				return false;
 		 			}
 		}
		
		return true;
			
	}

	/**
	 * [NiveauBB.dtosToSelectItems] Method 
	 * @author  Rafik LAIB  on : 22 juil. 2014  02:14:47
	 * @param findByTypeFormationCode
	 * @return
	 */
	private List<SelectItem> dtosToSelectItems(
			List<CycleDto> dtos) {
			List<SelectItem> result = new ArrayList<SelectItem>();
		try {
	 		for (CycleDto item : dtos) {
	 			result.add(new SelectItem(item.getId(), String.valueOf(item.getRang()) +" - "+ item.getLibelleLongLt()+ " - " +getTypeFormationLibelle(item.getRefCodeTypeFormation(),listTypesFormation)));
			}
	 }
		catch (Exception e) {
			 e.printStackTrace();
		}
		return result;
	}
	
	private String getTypeFormationLibelle(String codeType, List<SelectItem> listTypes) {
		
		if (listTypes == null)
			return null;
		else {
			for (SelectItem selectItem : listTypes) {
				
				if(selectItem.getValue().toString().equals(codeType))
					return selectItem.getLabel().toString();
			}
		}
		return null;
		
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
}
