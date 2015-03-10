/**
 * [dz.gov.mesrs.sii.recherche.web.jsf.bean.structure.RechercheStructureBB.java] 
 * @author rlaib on : 16 dï¿½c. 2014  16:06:14
 * 
 */
package dz.gov.mesrs.sii.recherche.web.jsf.bean.programme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.SerializationUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.data.model.gfc.Article;
import dz.gov.mesrs.sii.commons.data.model.gfc.Chapitre;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.recherche.business.model.dto.ActiviteBudgetDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.DemandeBudgetDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.DemandeCreditDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.DemandeEquipementDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.DemandeOperationDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.GroupeDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ProgrammeDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ResultatAttenduDto;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.RechercheConstantBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

@ManagedBean(name = "demandeBB")
@ViewScoped
public class DemandeBudgetBB extends BaseBean {
	
	private static final long serialVersionUID = 1L;

	// ===================================================================  
	// Constructors
	// ===================================================================

	public DemandeBudgetBB() {
		
	}
	
	@PostConstruct
	public void init() {

	}

	// ===================================================================  
	// Properties Model
	// ===================================================================
	// Deamnde de budget
	private String searchComponentKeyWords;
	private List<DemandeBudgetDto> listDemands; 
	private DemandeBudgetDto selectedDemand;
	private DemandeBudgetDto currentDemand;
	private boolean canShowDemandDetails = false;
	private boolean isNewDemand= false;
	private boolean canDeleteDemand = false;
	private boolean canAddOneDetail = false;
	private boolean canSubmitDemand = false;
	private boolean canValidateDemand = false;
	private boolean canEditDemand = false;
	private String titleDetailsDemand;
	private List<SelectItem> listPrograms;
	private List<SelectItem> listBeginYears;
	private List<SelectItem> listEndYears;
	private Short startBeginYear;
	private List<SituationEntiteOccurrenceDto> demandHistory;
	private String currentRoleCode;
	// Activites demande
	private List<ActiviteBudgetDto> listDemandActivities;
	private ActiviteBudgetDto selectedDemandActivity;
	private boolean toEditDemandActivity= false;
	private boolean canAddOneActivity= false;
	private Long selectedDemandActivityId;
	private String titleDemandActivityDialog;
	private List<SelectItem> listNcActivities;
	private List<SelectItem> listGroups;
	// Equipements demande
	private List<DemandeEquipementDto> listDemandEquipements;
	private DemandeEquipementDto selectedDemandEquipment;
	private boolean toEditDemandEquipment= false;
	private boolean canAddOneEquipment= false;
	private Long selectedDemandEquipmentId;
	private String titleDemandEquipmentDialog;
	private List<SelectItem> listFamilyEquipments;
	private List<SelectItem> listSubFamilyEquipments;
	private Double totalEquipmentsAmounts;
	// Credits demande
	private List<DemandeCreditDto> listDemandCredits;
	private DemandeCreditDto selectedDemandCredit;
	private boolean toEditDemandCredit= false;
	private boolean canAddOneCredit= false;
	private Long selectedDemandCreditId;
	private String titleDemandCreditDialog;
	private List<SelectItem> listChaptersCredit;
	private List<SelectItem> listArticlesCredit;
	private Double totalCreditsAmounts;
	// Operations demande
	private List<DemandeOperationDto> listDemandOperations;
	private DemandeOperationDto selectedDemandOperation;
	private boolean toEditDemandOperation= false;
	private boolean canAddOneOperation= false;
	private Long selectedDemandOperationId;
	private String titleDemandOperationDialog;
	private List<SelectItem> listNcOperations;
	private Double totalOperationsAmounts;
	
	// Resultats attendus demande
	private List<ResultatAttenduDto> listDemandResultats;
	private ResultatAttenduDto selectedDemandResultat;
	private boolean toEditDemandResultat= false;
	private boolean canAddOneResultat= false;
	private Long selectedDemandResultatId;
	private String titleDemandResultatDialog;
	private List<SelectItem> listNcResultats;
	
	private Integer activeTabIndex = 0;
	private HashMap<Short, Double> recapTotalsAmountByYear;
	private List<Entry<Short, Double>> recapEntries;
	
	public String getCurrentRoleCode() {
		return currentRoleCode;
	}

	public void setCurrentRoleCode(String currentRoleCode) {
		this.currentRoleCode = currentRoleCode;
	}

	public boolean isCanValidateDemand() {
		return canValidateDemand;
	}

	public void setCanValidateDemand(boolean canValidateDemand) {
		this.canValidateDemand = canValidateDemand;
	}

	public List<SituationEntiteOccurrenceDto> getDemandHistory() {
		return demandHistory;
	}

	public void setDemandHistory(List<SituationEntiteOccurrenceDto> demandHistory) {
		this.demandHistory = demandHistory;
	}

	public boolean isCanEditDemand() {
		return canEditDemand;
	}

	public void setCanEditDemand(boolean canEditDemand) {
		this.canEditDemand = canEditDemand;
	}

	public boolean isCanSubmitDemand() {
		return canSubmitDemand;
	}

	public void setCanSubmitDemand(boolean canSubmitDemand) {
		this.canSubmitDemand = canSubmitDemand;
	}

	public boolean isNewDemand() {
		return isNewDemand;
	}

	public void setNewDemand(boolean isNewDemand) {
		this.isNewDemand = isNewDemand;
	}

	public List<Entry<Short, Double>> getRecapEntries() {
		
		Map<Short, Double> treeMap = new TreeMap<Short, Double>(recapTotalsAmountByYear);
		return new ArrayList<Entry<Short,Double>>(treeMap.entrySet());
	}

	public HashMap<Short, Double> getRecapTotalsAmountByYear() {
		return recapTotalsAmountByYear;
	}

	public void setRecapTotalsAmountByYear(
			HashMap<Short, Double> recapTotalsAmountByYear) {
		this.recapTotalsAmountByYear = recapTotalsAmountByYear;
	}

	public Integer getActiveTabIndex() {
		return activeTabIndex;
	}

	public void setActiveTabIndex(Integer activeTabIndex) {
		this.activeTabIndex = activeTabIndex;
	}

	public List<ResultatAttenduDto> getListDemandResultats() {
		return listDemandResultats;
	}

	public void setListDemandResultats(List<ResultatAttenduDto> listDemandResultats) {
		this.listDemandResultats = listDemandResultats;
	}

	public ResultatAttenduDto getSelectedDemandResultat() {
		if(selectedDemandResultat ==null)
			selectedDemandResultat = new ResultatAttenduDto();
		return selectedDemandResultat;
	}

	public void setSelectedDemandResultat(ResultatAttenduDto selectedDemandResultat) {
		this.selectedDemandResultat = selectedDemandResultat;
	}

	public boolean isToEditDemandResultat() {
		return toEditDemandResultat;
	}

	public void setToEditDemandResultat(boolean toEditDemandResultat) {
		this.toEditDemandResultat = toEditDemandResultat;
	}

	public boolean isCanAddOneResultat() {
		return canAddOneResultat;
	}

	public void setCanAddOneResultat(boolean canAddOneResultat) {
		this.canAddOneResultat = canAddOneResultat;
	}

	public Long getSelectedDemandResultatId() {
		return selectedDemandResultatId;
	}

	public void setSelectedDemandResultatId(Long selectedDemandResultatId) {
		this.selectedDemandResultatId = selectedDemandResultatId;
	}

	public String getTitleDemandResultatDialog() {
		return titleDemandResultatDialog;
	}

	public void setTitleDemandResultatDialog(String titleDemandResultatDialog) {
		this.titleDemandResultatDialog = titleDemandResultatDialog;
	}

	public List<SelectItem> getListNcResultats() {
		return listNcResultats;
	}

	public void setListNcResultats(List<SelectItem> listNcResultats) {
		this.listNcResultats = listNcResultats;
	}

	/**
	 * [DemandeBudgetBB.listDemandOperations] Getter 
	 * @author rlaib on : 8 févr. 2015  09:42:32
	 * @return the listDemandOperations
	 */
	public List<DemandeOperationDto> getListDemandOperations() {
		return listDemandOperations;
	}

	/**
	 * [DemandeBudgetBB.listDemandOperations] Setter 
	 * @author rlaib on : 8 févr. 2015  09:42:32
	 * @param listDemandOperations the listDemandOperations to set
	 */
	public void setListDemandOperations(
			List<DemandeOperationDto> listDemandOperations) {
		this.listDemandOperations = listDemandOperations;
	}

	/**
	 * [DemandeBudgetBB.selectedDemandOperation] Getter 
	 * @author rlaib on : 8 févr. 2015  09:42:32
	 * @return the selectedDemandOperation
	 */
	public DemandeOperationDto getSelectedDemandOperation() {
		if(selectedDemandOperation == null)
			selectedDemandOperation = new DemandeOperationDto();
		return selectedDemandOperation;
	}

	/**
	 * [DemandeBudgetBB.selectedDemandOperation] Setter 
	 * @author rlaib on : 8 févr. 2015  09:42:32
	 * @param selectedDemandOperation the selectedDemandOperation to set
	 */
	public void setSelectedDemandOperation(
			DemandeOperationDto selectedDemandOperation) {
		this.selectedDemandOperation = selectedDemandOperation;
	}

	/**
	 * [DemandeBudgetBB.toEditDemandOperation] Getter 
	 * @author rlaib on : 8 févr. 2015  09:42:32
	 * @return the toEditDemandOperation
	 */
	public boolean isToEditDemandOperation() {
		return toEditDemandOperation;
	}

	/**
	 * [DemandeBudgetBB.toEditDemandOperation] Setter 
	 * @author rlaib on : 8 févr. 2015  09:42:32
	 * @param toEditDemandOperation the toEditDemandOperation to set
	 */
	public void setToEditDemandOperation(boolean toEditDemandOperation) {
		this.toEditDemandOperation = toEditDemandOperation;
	}

	/**
	 * [DemandeBudgetBB.canAddOneOperation] Getter 
	 * @author rlaib on : 8 févr. 2015  09:42:32
	 * @return the canAddOneOperation
	 */
	public boolean isCanAddOneOperation() {
		return canAddOneOperation;
	}

	/**
	 * [DemandeBudgetBB.canAddOneOperation] Setter 
	 * @author rlaib on : 8 févr. 2015  09:42:32
	 * @param canAddOneOperation the canAddOneOperation to set
	 */
	public void setCanAddOneOperation(boolean canAddOneOperation) {
		this.canAddOneOperation = canAddOneOperation;
	}

	/**
	 * [DemandeBudgetBB.selectedDemandOperationId] Getter 
	 * @author rlaib on : 8 févr. 2015  09:42:33
	 * @return the selectedDemandOperationId
	 */
	public Long getSelectedDemandOperationId() {
		return selectedDemandOperationId;
	}

	/**
	 * [DemandeBudgetBB.selectedDemandOperationId] Setter 
	 * @author rlaib on : 8 févr. 2015  09:42:33
	 * @param selectedDemandOperationId the selectedDemandOperationId to set
	 */
	public void setSelectedDemandOperationId(Long selectedDemandOperationId) {
		this.selectedDemandOperationId = selectedDemandOperationId;
	}

	/**
	 * [DemandeBudgetBB.titleDemandOperationDialog] Getter 
	 * @author rlaib on : 8 févr. 2015  09:42:33
	 * @return the titleDemandOperationDialog
	 */
	public String getTitleDemandOperationDialog() {
		return titleDemandOperationDialog;
	}

	/**
	 * [DemandeBudgetBB.titleDemandOperationDialog] Setter 
	 * @author rlaib on : 8 févr. 2015  09:42:33
	 * @param titleDemandOperationDialog the titleDemandOperationDialog to set
	 */
	public void setTitleDemandOperationDialog(String titleDemandOperationDialog) {
		this.titleDemandOperationDialog = titleDemandOperationDialog;
	}

	/**
	 * [DemandeBudgetBB.listNcOperations] Getter 
	 * @author rlaib on : 8 févr. 2015  09:45:02
	 * @return the listNcOperations
	 */
	public List<SelectItem> getListNcOperations() {
		return listNcOperations;
	}

	/**
	 * [DemandeBudgetBB.listNcOperations] Setter 
	 * @author rlaib on : 8 févr. 2015  09:45:02
	 * @param listNcOperations the listNcOperations to set
	 */
	public void setListNcOperations(List<SelectItem> listNcOperations) {
		this.listNcOperations = listNcOperations;
	}

	/**
	 * [DemandeBudgetBB.totalOperationsAmounts] Getter 
	 * @author rlaib on : 8 févr. 2015  10:09:48
	 * @return the totalOperationsAmounts
	 */
	public Double getTotalOperationsAmounts() {
		return totalOperationsAmounts;
	}

	/**
	 * [DemandeBudgetBB.totalOperationsAmounts] Setter 
	 * @author rlaib on : 8 févr. 2015  10:09:48
	 * @param totalOperationsAmounts the totalOperationsAmounts to set
	 */
	public void setTotalOperationsAmounts(Double totalOperationsAmounts) {
		this.totalOperationsAmounts = totalOperationsAmounts;
	}

	public Double getTotalCreditsAmounts() {
		return totalCreditsAmounts;
	}

	public void setTotalCreditsAmounts(Double totalCreditsAmounts) {
		this.totalCreditsAmounts = totalCreditsAmounts;
	}

	public Double getTotalEquipmentsAmounts() {
		return totalEquipmentsAmounts;
	}

	public void setTotalEquipmentsAmounts(Double totalEquipmentsAmounts) {
		this.totalEquipmentsAmounts = totalEquipmentsAmounts;
	}

	public List<DemandeCreditDto> getListDemandCredits() {
		return listDemandCredits;
	}

	public void setListDemandCredits(List<DemandeCreditDto> listDemandCredits) {
		this.listDemandCredits = listDemandCredits;
	}

	public DemandeCreditDto getSelectedDemandCredit() {
		if(selectedDemandCredit == null)
			selectedDemandCredit = new DemandeCreditDto();
		return selectedDemandCredit;
	}

	public void setSelectedDemandCredit(DemandeCreditDto selectedDemandCredit) {
		this.selectedDemandCredit = selectedDemandCredit;
	}

	public boolean isToEditDemandCredit() {
		return toEditDemandCredit;
	}

	public void setToEditDemandCredit(boolean toEditDemandCredit) {
		this.toEditDemandCredit = toEditDemandCredit;
	}

	public boolean isCanAddOneCredit() {
		return canAddOneCredit;
	}

	public void setCanAddOneCredit(boolean canAddOneCredit) {
		this.canAddOneCredit = canAddOneCredit;
	}

	public Long getSelectedDemandCreditId() {
		return selectedDemandCreditId;
	}

	public void setSelectedDemandCreditId(Long selectedDemandCreditId) {
		this.selectedDemandCreditId = selectedDemandCreditId;
	}

	public String getTitleDemandCreditDialog() {
		return titleDemandCreditDialog;
	}

	public void setTitleDemandCreditDialog(String titleDemandCreditDialog) {
		this.titleDemandCreditDialog = titleDemandCreditDialog;
	}

	public List<SelectItem> getListChaptersCredit() {
		return listChaptersCredit;
	}

	public void setListChaptersCredit(List<SelectItem> listChaptersCredit) {
		this.listChaptersCredit = listChaptersCredit;
	}

	public List<SelectItem> getListArticlesCredit() {
		return listArticlesCredit;
	}

	public void setListArticlesCredit(List<SelectItem> listArticlesCredit) {
		this.listArticlesCredit = listArticlesCredit;
	}

	public List<DemandeEquipementDto> getListDemandEquipements() {
		return listDemandEquipements;
	}

	public void setListDemandEquipements(
			List<DemandeEquipementDto> listDemandEquipements) {
		this.listDemandEquipements = listDemandEquipements;
	}

	public DemandeEquipementDto getSelectedDemandEquipment() {
		if(selectedDemandEquipment == null)
			selectedDemandEquipment = new DemandeEquipementDto();
		return selectedDemandEquipment;
	}

	public void setSelectedDemandEquipment(
			DemandeEquipementDto selectedDemandEquipment) {
		this.selectedDemandEquipment = selectedDemandEquipment;
	}

	public boolean isToEditDemandEquipment() {
		return toEditDemandEquipment;
	}

	public void setToEditDemandEquipment(boolean toEditDemandEquipment) {
		this.toEditDemandEquipment = toEditDemandEquipment;
	}

	public boolean isCanAddOneEquipment() {
		return canAddOneEquipment;
	}

	public void setCanAddOneEquipment(boolean canAddOneEquipment) {
		this.canAddOneEquipment = canAddOneEquipment;
	}

	public Long getSelectedDemandEquipmentId() {
		return selectedDemandEquipmentId;
	}

	public void setSelectedDemandEquipmentId(Long selectedDemandEquipmentId) {
		this.selectedDemandEquipmentId = selectedDemandEquipmentId;
	}

	public String getTitleDemandEquipmentDialog() {
		return titleDemandEquipmentDialog;
	}

	public void setTitleDemandEquipmentDialog(String titleDemandEquipmentDialog) {
		this.titleDemandEquipmentDialog = titleDemandEquipmentDialog;
	}

	public List<SelectItem> getListFamilyEquipments() {
		return listFamilyEquipments;
	}

	public void setListFamilyEquipments(List<SelectItem> listFamilyEquipments) {
		this.listFamilyEquipments = listFamilyEquipments;
	}

	public List<SelectItem> getListSubFamilyEquipments() {
		return listSubFamilyEquipments;
	}

	public void setListSubFamilyEquipments(List<SelectItem> listSubFamilyEquipments) {
		this.listSubFamilyEquipments = listSubFamilyEquipments;
	}

	/**
	 * [DemandeBudgetBB.listGroups] Getter 
	 * @author rlaib on : 4 févr. 2015  11:25:19
	 * @return the listGroups
	 */
	public List<SelectItem> getListGroups() {
		return listGroups;
	}

	/**
	 * [DemandeBudgetBB.listGroups] Setter 
	 * @author rlaib on : 4 févr. 2015  11:25:19
	 * @param listGroups the listGroups to set
	 */
	public void setListGroups(List<SelectItem> listGroups) {
		this.listGroups = listGroups;
	}

	/**
	 * [DemandeBudgetBB.listNcActivities] Getter 
	 * @author rlaib on : 4 févr. 2015  11:24:35
	 * @return the listNcActivities
	 */
	public List<SelectItem> getListNcActivities() {
		return listNcActivities;
	}

	/**
	 * [DemandeBudgetBB.listNcActivities] Setter 
	 * @author rlaib on : 4 févr. 2015  11:24:35
	 * @param listNcActivities the listNcActivities to set
	 */
	public void setListNcActivities(List<SelectItem> listNcActivities) {
		this.listNcActivities = listNcActivities;
	}

	/**
	 * [DemandeBudgetBB.canAddOneActivity] Getter 
	 * @author rlaib on : 4 févr. 2015  11:21:37
	 * @return the canAddOneActivity
	 */
	public boolean isCanAddOneActivity() {
		return canAddOneActivity;
	}

	/**
	 * [DemandeBudgetBB.canAddOneActivity] Setter 
	 * @author rlaib on : 4 févr. 2015  11:21:37
	 * @param canAddOneActivity the canAddOneActivity to set
	 */
	public void setCanAddOneActivity(boolean canAddOneActivity) {
		this.canAddOneActivity = canAddOneActivity;
	}

	/**
	 * [DemandeBudgetBB.selectedDemandActivity] Getter 
	 * @author rlaib on : 4 févr. 2015  11:21:16
	 * @return the selectedDemandActivity
	 */
	public ActiviteBudgetDto getSelectedDemandActivity() {
		if(selectedDemandActivity == null) 
			selectedDemandActivity = new ActiviteBudgetDto();
		return selectedDemandActivity;
	}

	/**
	 * [DemandeBudgetBB.selectedDemandActivity] Setter 
	 * @author rlaib on : 4 févr. 2015  11:21:16
	 * @param selectedDemandActivity the selectedDemandActivity to set
	 */
	public void setSelectedDemandActivity(ActiviteBudgetDto selectedDemandActivity) {
		this.selectedDemandActivity = selectedDemandActivity;
	}

	/**
	 * [DemandeBudgetBB.titleDemandActivityDialog] Getter 
	 * @author rlaib on : 4 févr. 2015  11:16:14
	 * @return the titleDemandActivityDialog
	 */
	public String getTitleDemandActivityDialog() {
		return titleDemandActivityDialog;
	}

	/**
	 * [DemandeBudgetBB.titleDemandActivityDialog] Setter 
	 * @author rlaib on : 4 févr. 2015  11:16:14
	 * @param titleDemandActivityDialog the titleDemandActivityDialog to set
	 */
	public void setTitleDemandActivityDialog(String titleDemandActivityDialog) {
		this.titleDemandActivityDialog = titleDemandActivityDialog;
	}

	/**
	 * [DemandeBudgetBB.listDemandActivities] Getter 
	 * @author rlaib on : 4 févr. 2015  11:14:59
	 * @return the listDemandActivities
	 */
	public List<ActiviteBudgetDto> getListDemandActivities() {
		return listDemandActivities;
	}

	/**
	 * [DemandeBudgetBB.listDemandActivities] Setter 
	 * @author rlaib on : 4 févr. 2015  11:14:59
	 * @param listDemandActivities the listDemandActivities to set
	 */
	public void setListDemandActivities(List<ActiviteBudgetDto> listDemandActivities) {
		this.listDemandActivities = listDemandActivities;
	}

	/**
	 * [DemandeBudgetBB.toEditDemandActivity] Getter 
	 * @author rlaib on : 4 févr. 2015  11:14:59
	 * @return the toEditDemandActivity
	 */
	public boolean isToEditDemandActivity() {
		return toEditDemandActivity;
	}

	/**
	 * [DemandeBudgetBB.toEditDemandActivity] Setter 
	 * @author rlaib on : 4 févr. 2015  11:14:59
	 * @param toEditDemandActivity the toEditDemandActivity to set
	 */
	public void setToEditDemandActivity(boolean toEditDemandActivity) {
		this.toEditDemandActivity = toEditDemandActivity;
	}

	/**
	 * [DemandeBudgetBB.selectedDemandActivityId] Getter 
	 * @author rlaib on : 4 févr. 2015  11:14:59
	 * @return the selectedDemandActivityId
	 */
	public Long getSelectedDemandActivityId() {
		return selectedDemandActivityId;
	}

	/**
	 * [DemandeBudgetBB.selectedDemandActivityId] Setter 
	 * @author rlaib on : 4 févr. 2015  11:14:59
	 * @param selectedDemandActivityId the selectedDemandActivityId to set
	 */
	public void setSelectedDemandActivityId(Long selectedDemandActivityId) {
		this.selectedDemandActivityId = selectedDemandActivityId;
	}

	/**
	 * [DemandeBudgetBB.startBeginYear] Getter 
	 * @author rlaib on : 3 févr. 2015  11:04:11
	 * @return the startBeginYear
	 */
	public Short getStartBeginYear() {
		return startBeginYear;
	}

	/**
	 * [DemandeBudgetBB.startBeginYear] Setter 
	 * @author rlaib on : 3 févr. 2015  11:04:11
	 * @param startBeginYear the startBeginYear to set
	 */
	public void setStartBeginYear(Short startBeginYear) {
		this.startBeginYear = startBeginYear;
	}

	/**
	 * [DemandeBudgetBB.listBeginYears] Getter 
	 * @author rlaib on : 3 févr. 2015  08:53:09
	 * @return the listBeginYears
	 */
	public List<SelectItem> getListBeginYears() {
		return listBeginYears;
	}

	/**
	 * [DemandeBudgetBB.listBeginYears] Setter 
	 * @author rlaib on : 3 févr. 2015  08:53:09
	 * @param listBeginYears the listBeginYears to set
	 */
	public void setListBeginYears(List<SelectItem> listBeginYears) {
		this.listBeginYears = listBeginYears;
	}

	/**
	 * [DemandeBudgetBB.listEndYears] Getter 
	 * @author rlaib on : 3 févr. 2015  08:53:09
	 * @return the listEndYears
	 */
	public List<SelectItem> getListEndYears() {
		return listEndYears;
	}

	/**
	 * [DemandeBudgetBB.listEndYears] Setter 
	 * @author rlaib on : 3 févr. 2015  08:53:09
	 * @param listEndYears the listEndYears to set
	 */
	public void setListEndYears(List<SelectItem> listEndYears) {
		this.listEndYears = listEndYears;
	}

	/**
	 * [DemandeBudgetBB.searchComponentKeyWords] Getter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @return the searchComponentKeyWords
	 */
	public String getSearchComponentKeyWords() {
		return searchComponentKeyWords;
	}

	/**
	 * [DemandeBudgetBB.searchComponentKeyWords] Setter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @param searchComponentKeyWords the searchComponentKeyWords to set
	 */
	public void setSearchComponentKeyWords(String searchComponentKeyWords) {
		this.searchComponentKeyWords = searchComponentKeyWords;
	}

	/**
	 * [DemandeBudgetBB.listDemands] Getter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @return the listDemands
	 */
	public List<DemandeBudgetDto> getListDemands() {
		return listDemands;
	}

	/**
	 * [DemandeBudgetBB.listDemands] Setter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @param listDemands the listDemands to set
	 */
	public void setListDemands(List<DemandeBudgetDto> listDemands) {
		this.listDemands = listDemands;
	}

	/**
	 * [DemandeBudgetBB.selectedDemand] Getter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @return the selectedDemand
	 */
	public DemandeBudgetDto getSelectedDemand() {
		return selectedDemand;
	}

	/**
	 * [DemandeBudgetBB.selectedDemand] Setter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @param selectedDemand the selectedDemand to set
	 */
	public void setSelectedDemand(DemandeBudgetDto selectedDemand) {
		this.selectedDemand = selectedDemand;
	}

	/**
	 * [DemandeBudgetBB.currentDemand] Getter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @return the currentDemand
	 */
	public DemandeBudgetDto getCurrentDemand() {
		return currentDemand;
	}

	/**
	 * [DemandeBudgetBB.currentDemand] Setter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @param currentDemand the currentDemand to set
	 */
	public void setCurrentDemand(DemandeBudgetDto currentDemand) {
		this.currentDemand = currentDemand;
	}

	/**
	 * [DemandeBudgetBB.canShowDemandDetails] Getter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @return the canShowDemandDetails
	 */
	public boolean isCanShowDemandDetails() {
		return canShowDemandDetails;
	}

	/**
	 * [DemandeBudgetBB.canShowDemandDetails] Setter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @param canShowDemandDetails the canShowDemandDetails to set
	 */
	public void setCanShowDemandDetails(boolean canShowDemandDetails) {
		this.canShowDemandDetails = canShowDemandDetails;
	}


	/**
	 * [DemandeBudgetBB.canDeleteDemand] Getter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @return the canDeleteDemand
	 */
	public boolean isCanDeleteDemand() {
		return canDeleteDemand;
	}

	/**
	 * [DemandeBudgetBB.canDeleteDemand] Setter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @param canDeleteDemand the canDeleteDemand to set
	 */
	public void setCanDeleteDemand(boolean canDeleteDemand) {
		this.canDeleteDemand = canDeleteDemand;
	}

	/**
	 * [DemandeBudgetBB.canAddOneDetail] Getter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @return the canAddOneDetail
	 */
	public boolean isCanAddOneDetail() {
		return canAddOneDetail;
	}

	/**
	 * [DemandeBudgetBB.canAddOneDetail] Setter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @param canAddOneDetail the canAddOneDetail to set
	 */
	public void setCanAddOneDetail(boolean canAddOneDetail) {
		this.canAddOneDetail = canAddOneDetail;
	}

	/**
	 * [DemandeBudgetBB.titleDetailsDemand] Getter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @return the titleDetailsDemand
	 */
	public String getTitleDetailsDemand() {
		return titleDetailsDemand;
	}

	/**
	 * [DemandeBudgetBB.titleDetailsDemand] Setter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @param titleDetailsDemand the titleDetailsDemand to set
	 */
	public void setTitleDetailsDemand(String titleDetailsDemand) {
		this.titleDetailsDemand = titleDetailsDemand;
	}

	/**
	 * [DemandeBudgetBB.listPrograms] Getter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @return the listPrograms
	 */
	public List<SelectItem> getListPrograms() {
		return listPrograms;
	}

	/**
	 * [DemandeBudgetBB.listPrograms] Setter 
	 * @author rlaib on : 1 févr. 2015  16:07:05
	 * @param listPrograms the listPrograms to set
	 */
	public void setListPrograms(List<SelectItem> listPrograms) {
		this.listPrograms = listPrograms;
	}

	// ===================================================================  
	// Actions and Methods
	// ===================================================================
	
	
	public void prepareAddDemand() {
		
		isNewDemand = true;
		canSubmitDemand = false;
		resetAll();
		titleDetailsDemand = CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME, 
				RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_PANEL_DETAILS_TITLE_ADD);
		if(listBeginYears== null) {
			initBeginListYears();
			initEndListYears();
		}
		if (listBeginYears != null  &&  !listBeginYears.isEmpty()
				&&	listEndYears != null  &&  !listEndYears.isEmpty()) {
				currentDemand.setAnneeDebut(Short.parseShort(listBeginYears.get(0).getValue().toString()));
				currentDemand.setAnneeFin(Short.parseShort(listEndYears.get(0).getValue().toString()));
				initListPrograms();
		}
	
	}
	private void resetAll() {
		currentDemand = new DemandeBudgetDto();
		canShowDemandDetails = true;
		listDemandActivities = null;
		listDemandEquipements = null;
		listDemandOperations = null;
		listDemandCredits = null;
		listDemandResultats = null;
		recapTotalsAmountByYear = new HashMap<>();
		activeTabIndex = 0;
		totalCreditsAmounts = totalEquipmentsAmounts = totalOperationsAmounts = (double) 0;
	}

	public void prepareAddOneActivity() {
		
		if(selectedDemandActivity == null) {
			selectedDemandActivity = new ActiviteBudgetDto();
		}
		else  {
			selectedDemandActivity.setId(null);
			selectedDemandActivity.setDescription(null);
		}
		toEditDemandActivity= false;
		if(listDemandActivities== null)
			listDemandActivities  = new ArrayList<ActiviteBudgetDto>();
		titleDemandActivityDialog= CommonMessagesUtils.getStringValueFromBundleResource(
				RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME, 
				RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_ACTIVITY_DIALOG_ADD);
		if(listNcActivities== null) {
			initListNcActivities();
		}
		if(listGroups== null) {
			initListGroups();
		}
	
	}
	public void prepareAddOneOperation() {
		
		if(selectedDemandOperation == null) {
			selectedDemandOperation = new DemandeOperationDto();
		}
		else  {
			selectedDemandOperation.setId(null);
			selectedDemandOperation.setMontantEstime(null);
			
		}
		toEditDemandOperation = false;
		if(listDemandOperations == null)
			listDemandOperations  = new ArrayList<DemandeOperationDto>();
		titleDemandOperationDialog = CommonMessagesUtils.getStringValueFromBundleResource(
				RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME, 
				RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_OPERATION_DIALOG_ADD);
		if(listNcOperations== null) {
			initListNcOperations();
		}
		
	}
	public void prepareAddOneResult() {
		
		if(selectedDemandResultat  == null) {
			selectedDemandResultat = new ResultatAttenduDto();
		}
		else {
			selectedDemandResultat.setId(null);
			selectedDemandResultat.setDescription(null);
		}
		selectedDemandResultat.setQuantite(1);
		toEditDemandResultat = false;
		if(listDemandResultats == null)
			listDemandResultats = new ArrayList<ResultatAttenduDto>();
		titleDemandOperationDialog = CommonMessagesUtils.getStringValueFromBundleResource(
				RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME, 
				RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_OPERATION_DIALOG_ADD);
		if(listNcResultats== null) {
			initListNcResultats();
		}
		
	}
	public void prepareAddOneEquipment() {
		
		if(selectedDemandEquipment == null) {
			selectedDemandEquipment= new DemandeEquipementDto();
		}
		else {
			selectedDemandEquipment.setId(null);
			selectedDemandEquipment.setMontantEstime(null);
		}
		selectedDemandEquipment.setQuantite(1);
		toEditDemandEquipment= false;
		if(listDemandEquipements == null)
			listDemandEquipements  = new ArrayList<DemandeEquipementDto>();
		titleDemandEquipmentDialog= CommonMessagesUtils.getStringValueFromBundleResource(
				RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME, 
				RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_EQUIPMENT_DIALOG_ADD);
		if(listFamilyEquipments== null) {
			initListFamilyEquipments();
			if (listFamilyEquipments!= null) {
				initListSubFamilyEquipments(Integer.parseInt(listFamilyEquipments.get(0).getValue().toString()));
			}
			else {
				listSubFamilyEquipments = null;
			}
		}
	}
	public void prepareAddOneCredit() {
		
		if(selectedDemandCredit == null) {
			selectedDemandCredit= new DemandeCreditDto();
		}
		else {
			selectedDemandCredit.setId(null);
			selectedDemandCredit.setMontant(null);
			selectedDemandCredit.setDescription(null);
		}
		
		toEditDemandCredit= false;
		if(listDemandCredits== null)
			listDemandCredits  = new ArrayList<DemandeCreditDto>();
		titleDemandCreditDialog= CommonMessagesUtils.getStringValueFromBundleResource(
				RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME, 
				RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_CREDIT_DIALOG_ADD);
		if(listChaptersCredit== null) {
			initListChapters();
			if (listChaptersCredit!= null) {
				initListArticlesByChapter(Integer.parseInt(listChaptersCredit.get(0).getValue().toString()));
			}
			else {
				listArticlesCredit= null;
			}
		}
	}
	private void initListNcResultats() {

		listNcResultats= this.findNomenclatureByName(UtilConstant.NC_RCH_RESULTATS_NAME);
	}
	private void initListNcOperations() {

		listNcOperations= this.findNomenclatureByName(UtilConstant.NC_RCH_OPERATION_NAME);
	}
	private void initListFamilyEquipments() {
		listFamilyEquipments= this.findNomenclatureByName(UtilConstant.NC_FAMILLE_EQUIPEMENT_NAME);
	}
	private void initListChapters() {
		List<Chapitre> _list = serviceLocator.getRechercheDemandeBudgetService().findChapters();
		if(_list != null) {
			listChaptersCredit = new ArrayList<SelectItem>();
			for (Chapitre _chapitre : _list) {
				listChaptersCredit.add(new SelectItem(_chapitre.getId(), _chapitre.getIntituleFr()));
			}
		}
	}
	private void initListSubFamilyEquipments(Integer idFamily) {
		List<NomenclatureDto> _list = serviceLocator.getNomenclatureService().findNcCompositeList(idFamily, UtilConstant.NC_SOUS_FAMILLE_EQUIPEMENT_NAME);
		if(_list != null) {
			listSubFamilyEquipments = new ArrayList<SelectItem>();
			for (NomenclatureDto _nomenclatureDto : _list) {
				listSubFamilyEquipments.add(new SelectItem(_nomenclatureDto.getId(), _nomenclatureDto.getLibelleLongFr()));
			}
		}
 		
	}
	private void initListGroups() {

		List<GroupeDto> _list = serviceLocator.getRechercheGroupeService().findAll();
		if(_list!= null && !_list.isEmpty()) {
			listGroups = new ArrayList<SelectItem>();
			for (GroupeDto _groupeDto : _list) {
				listGroups.add(new SelectItem(_groupeDto.getId(), _groupeDto.getRefGroupeLibelleFr()));
				
			}
		}
	}
	private void initListNcActivities() {
		
		listNcActivities= this.findNomenclatureByName(UtilConstant.NC_RCH_ACTIVITES_NAME);
	
	}
	private void initListArticlesByChapter(Integer ncChapitreId) {
		
		List<Article> _list = serviceLocator.getRechercheDemandeBudgetService().findArticlesByChapterId(ncChapitreId);
		if(_list != null) {
			listArticlesCredit= new ArrayList<SelectItem>();
			for (Article _article : _list) {
				listArticlesCredit.add(new SelectItem(_article.getId(), _article.getIntituleFr()));
			}
	}
	
}  
	private void initListPrograms() {

		List<ProgrammeDto> _programs = serviceLocator.getRechercheProgrammeService().findByPeriod(currentDemand.getAnneeDebut(), currentDemand.getAnneeFin());
		if(_programs  != null && !_programs.isEmpty()) {
			listPrograms = new ArrayList<SelectItem>();
			for (ProgrammeDto _programmeDto : _programs) {
				listPrograms.add(new SelectItem(_programmeDto.getId(), _programmeDto.getIntituleFr()));
				
			}
		}
		else {
			listPrograms = null;
		}
	}
	private void initEndListYears() {
		
		listEndYears = new ArrayList<SelectItem>();
		for (int i = 0; i <= 35; i++) {
			listEndYears.add(new SelectItem(startBeginYear + i, String.valueOf(startBeginYear + i)));
		}
		
	}
	private void initBeginListYears() {

//		startBeginYear = Calendar.getInstance().get(Calendar.YEAR);
		startBeginYear = Short.parseShort(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_START_YEAR);
		listBeginYears = new ArrayList<SelectItem>();
		for (int i = 0; i <= 35; i++) {
			listBeginYears.add(new SelectItem(startBeginYear + i, String.valueOf(startBeginYear + i)));
		}
	}

	public void prepareEditOneActivity() {
		if (selectedDemandActivityId != null) {
			if(listNcActivities == null) {
				initListNcActivities();
			}
			toEditDemandActivity= true;
			titleDemandActivityDialog= CommonMessagesUtils.getStringValueFromBundleResource(
					RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME, 
					RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_ACTIVITY_DIALOG_EDIT);
			selectedDemandActivity = serviceLocator.getRechercheDemandeBudgetService().findDemandActivityById(selectedDemandActivityId);
		}
	}
	public void prepareEditOneOperation() {
		if (selectedDemandOperationId != null) {
			if(listNcOperations== null) {
				initListNcOperations();
			}
			toEditDemandOperation= true;
			titleDemandOperationDialog= CommonMessagesUtils.getStringValueFromBundleResource(
					RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME, 
					RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_OPERATION_DIALOG_EDIT);
			selectedDemandOperation= serviceLocator.getRechercheDemandeBudgetService().findDemandOperationById(selectedDemandOperationId);
		}
	}
	public void prepareEditOneResult() {
		if (selectedDemandResultatId != null) {
			if(listNcResultats== null) {
				initListNcResultats();
			}
			toEditDemandResultat= true;
			titleDemandResultatDialog= CommonMessagesUtils.getStringValueFromBundleResource(
					RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME, 
					RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_RESULT_DIALOG_EDIT);
			selectedDemandResultat= serviceLocator.getRechercheDemandeBudgetService().findDemandExpectedResultById(selectedDemandResultatId);
		}
	}
	public void prepareEditOneEquipment() {
		
		if (selectedDemandEquipmentId != null) {
			
			toEditDemandEquipment= true;
			titleDemandEquipmentDialog= CommonMessagesUtils.getStringValueFromBundleResource(
					RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME, 
					RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_EQUIPMENT_DIALOG_EDIT);
			selectedDemandEquipment = serviceLocator.getRechercheDemandeBudgetService().findDemandEquipmentById(selectedDemandEquipmentId);
			if(listFamilyEquipments== null) {
				initListFamilyEquipments();
				if (listFamilyEquipments!= null) {
					initListSubFamilyEquipments(selectedDemandEquipment.getNcFamilleId());
				}
				else {
					listSubFamilyEquipments = null;
				}
			}
		}
	}
	public void prepareEditOneCredit() {
		
		if (selectedDemandCreditId!= null) {
			toEditDemandCredit= true;
			titleDemandCreditDialog= CommonMessagesUtils.getStringValueFromBundleResource(
					RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME, 
					RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_CREDIT_DIALOG_EDIT);
			selectedDemandCredit= serviceLocator.getRechercheDemandeBudgetService().findDemandCreditById(selectedDemandCreditId);
			if(listChaptersCredit== null) {
				initListChapters();
				if (listChaptersCredit!= null) {
					initListArticlesByChapter(selectedDemandCredit.getNcChapitreId());
				}
				else {
					listArticlesCredit= null;
				}
			}
		}
	}
	
	public void removeOneActivity() {

		if(selectedDemandActivityId== null)
				return;
			serviceLocator.getRechercheDemandeBudgetService().removeDemandActivityById(selectedDemandActivityId, selectedDemand.getId()
					,sessionBean.getCurrentUser().getCompte().getIdCompte());
			listDemandActivities= serviceLocator.getRechercheDemandeBudgetService().findDemandActivities(selectedDemand.getId());
			
			// Suppression Activite  reussie
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_REMOVE_ACTIVITY_SUCCESS),
			CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_REMOVE_ACTIVITY_SUCCESS));
	}
	public void removeOneOperation() {
		
		if(selectedDemandOperationId== null)
			return;
		serviceLocator.getRechercheDemandeBudgetService().removeDemandOperationById(selectedDemandOperationId,selectedDemand.getId()
				,sessionBean.getCurrentUser().getCompte().getIdCompte());
		listDemandOperations= serviceLocator.getRechercheDemandeBudgetService().findDemandOperations(selectedDemand.getId());
		processTotalOperations();
		processRecap();
		// Suppression Operation reussie
		CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
				RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
				RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_REMOVE_OPERATION_SUCCESS),
				CommonMessagesUtils.getStringValueFromBundleResource(
						RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
						RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_REMOVE_OPERATION_SUCCESS));
	}
	public void removeOneResult() {
		
		if(selectedDemandResultatId== null)
			return;
		serviceLocator.getRechercheDemandeBudgetService().removeDemandExpectedResultById(selectedDemandResultatId,selectedDemand.getId()
				,sessionBean.getCurrentUser().getCompte().getIdCompte());
		listDemandResultats= serviceLocator.getRechercheDemandeBudgetService().findDemandExpectedResults(selectedDemand.getId());
		
		// Suppression Resultat attendu reussie
		CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
				RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
				RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_REMOVE_RESULT_SUCCESS),
				CommonMessagesUtils.getStringValueFromBundleResource(
						RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
						RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_REMOVE_RESULT_SUCCESS));
	}
	public void removeOneEquipment() {

		if(selectedDemandEquipmentId== null)
				return;
			serviceLocator.getRechercheDemandeBudgetService().removeDemandEquipmentById(selectedDemandEquipmentId, selectedDemand.getId()
					,sessionBean.getCurrentUser().getCompte().getIdCompte());
			listDemandEquipements= serviceLocator.getRechercheDemandeBudgetService().findDemandEquipments(selectedDemand.getId());
			processTotalEquipments();
			processRecap();
			// Suppression Equipement  reussie
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_REMOVE_EQUIPMENT_SUCCESS),
			CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_REMOVE_EQUIPMENT_SUCCESS));
	}
	public void removeOneCredit() {

		if(selectedDemandCreditId== null)
				return;
			serviceLocator.getRechercheDemandeBudgetService().removeDemandCreditById(selectedDemandCreditId, selectedDemand.getId()
					,sessionBean.getCurrentUser().getCompte().getIdCompte());
			listDemandCredits = serviceLocator.getRechercheDemandeBudgetService().findDemandCredits(selectedDemand.getId());
			processTotalCredits();
			processRecap();
			// Suppression Credit  reussie
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_REMOVE_CREDIT_SUCCESS),
			CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_REMOVE_CREDIT_SUCCESS));
	}
	
	public void saveDemand() {
		
		if(currentDemand== null)
			return;
		else {
			if(!currentDemand.equals(selectedDemand)) {
					currentDemand = serviceLocator.getRechercheDemandeBudgetService().insertOrUpdate(currentDemand, sessionBean.getCurrentUser().getCompte().getIdCompte());
					selectedDemand  = SerializationUtils.clone(currentDemand);
					demandHistory =serviceLocator.getSituationService().getEntityOccurrenceHistory(
							UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, selectedDemand.getId().intValue()); 
					canSubmitDemand = true;
					isNewDemand = false;
					listDemands = serviceLocator.getRechercheDemandeBudgetService().findByKeyWords(searchComponentKeyWords);
					CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
										RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
										RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_DEMAND_SUCCESS),
					CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_DEMAND_SUCCESS));
			}
			else {
					CommonMessagesUtils.addWarnMessageWithoutKey(CommonMessagesUtils
						.getStringValueFromBundleResource(
									RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
									RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_DEMAND_NO_CHANGES),
					CommonMessagesUtils.getStringValueFromBundleResource(
						RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
						RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_DEMAND_NO_CHANGES));
			}
			
		}
	}
	
	public void submitDemand() {
		
		if(currentDemand== null)
			return;
		else {
					currentDemand = serviceLocator.getRechercheDemandeBudgetService().submitDemand(currentDemand,sessionBean.getCurrentUser().getCompte().getIdCompte());
					demandHistory =serviceLocator.getSituationService().getEntityOccurrenceHistory(
							UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, selectedDemand.getId().intValue()); 
					selectedDemand  = SerializationUtils.clone(currentDemand);
					canSubmitDemand = false;
					canValidateDemand = false;
					isNewDemand = false;
					canEditDemand = false;
					listDemands = serviceLocator.getRechercheDemandeBudgetService().findByKeyWords(searchComponentKeyWords);
					CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
										RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
										RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SUBMIT_DEMAND_SUCCESS),
					CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SUBMIT_DEMAND_SUCCESS));
					
		}
	}
	
	public void validateDemand() {
		
		if(currentDemand== null)
			return;
		else {
					currentDemand = serviceLocator.getRechercheDemandeBudgetService().validateDemand(currentDemand,sessionBean.getCurrentUser().getCompte().getIdCompte());
					demandHistory =serviceLocator.getSituationService().getEntityOccurrenceHistory(
							UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, selectedDemand.getId().intValue()); 
					selectedDemand  = SerializationUtils.clone(currentDemand);
					canSubmitDemand = false;
					canValidateDemand = false;
					isNewDemand = false;
					canEditDemand = false;
					listDemands = serviceLocator.getRechercheDemandeBudgetService().findDemandsToValidate(currentRoleCode);
					CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
										RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
										RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_VALIDATE_DEMAND_SUCCESS),
					CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_VALIDATE_DEMAND_SUCCESS));
					
		}
	}
	
	public void saveOneEquipment() {
		
		RequestContext context = RequestContext.getCurrentInstance();
		if(selectedDemandEquipment== null)
			return;
		else {
			
			selectedDemandEquipment.setDemandeBudgetId(currentDemand.getId());
			if(!toEditDemandEquipment)  {
					// Test for existing Equipment
					for (DemandeEquipementDto _item : listDemandEquipements) {
						if(selectedDemandEquipment.getNcFamilleId().equals(_item.getNcFamilleId())
								&& selectedDemandEquipment.getNcSousFamilleId().equals(_item.getNcSousFamilleId())
								&& (selectedDemandEquipment.getAnnee() == _item.getAnnee())) {
							CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
									.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
													RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_EQUIPMENT_EXISTING_CONTROL),
							CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
								RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_EQUIPMENT_EXISTING_CONTROL));
							context.addCallbackParam("isValid", false);
							return;
						}
					}
			}
			else {
				// Test for existing Equipment
				for (DemandeEquipementDto _item : listDemandEquipements) {
					if(selectedDemandEquipment.getNcFamilleId().equals(_item.getNcFamilleId())
							&& selectedDemandEquipment.getNcSousFamilleId().equals(_item.getNcSousFamilleId())
							&& (selectedDemandEquipment.getAnnee() == _item.getAnnee())
							&& (selectedDemandEquipment.getId()!=_item.getId())) {
						CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
								.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_EQUIPMENT_EXISTING_CONTROL),
						CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_EQUIPMENT_EXISTING_CONTROL));
						context.addCallbackParam("isValid", false);
						return;
					}
				}
			}
			selectedDemandEquipment = (DemandeEquipementDto) serviceLocator.getRechercheDemandeBudgetService().saveItem(selectedDemandEquipment
					,sessionBean.getCurrentUser().getCompte().getIdCompte());
			listDemandEquipements= serviceLocator.getRechercheDemandeBudgetService().findDemandEquipments(selectedDemand.getId());
			listDemandResultats= serviceLocator.getRechercheDemandeBudgetService().findDemandExpectedResults(selectedDemand.getId());
			currentDemand = serviceLocator.getRechercheDemandeBudgetService().findById(selectedDemandEquipment.getDemandeBudgetId());
			demandHistory =serviceLocator.getSituationService().getEntityOccurrenceHistory(
					UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, selectedDemand.getId().intValue()); 
			selectedDemand  = SerializationUtils.clone(currentDemand);
			processTotalEquipments();
			processRecap();
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
					.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_EQUIPMENT_SUCCESS),
			CommonMessagesUtils.getStringValueFromBundleResource(
									RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
									RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_EQUIPMENT_SUCCESS));
			context.addCallbackParam("isValid", true);
		}
	}
	public void saveOneCredit() {
		
		RequestContext context = RequestContext.getCurrentInstance();
		if(selectedDemandCredit== null)
			return;
		else {
			
			selectedDemandCredit.setDemandeBudgetId(currentDemand.getId());
			if(!toEditDemandCredit)  {
					// Test for existing Credit
					for (DemandeCreditDto _item : listDemandCredits) {
						if(selectedDemandCredit.getNcChapitreId().equals(_item.getNcChapitreId())
								&& selectedDemandCredit.getNcArticleId().equals(_item.getNcArticleId())
								&& (selectedDemandCredit.getAnnee() == _item.getAnnee())) {
							CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
									.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
													RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_CREDIT_EXISTING_CONTROL),
							CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
								RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_CREDIT_EXISTING_CONTROL));
							context.addCallbackParam("isValid", false);
							return;
						}
					}
			}
			else {
				// Test for existing Credit
				for (DemandeCreditDto _item : listDemandCredits) {
					if(selectedDemandCredit.getNcChapitreId().equals(_item.getNcChapitreId())
							&& selectedDemandCredit.getNcArticleId().equals(_item.getNcArticleId())
							&& (selectedDemandCredit.getAnnee() == _item.getAnnee())
							&& (selectedDemandCredit.getId()!=_item.getId())) {
						CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
								.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_CREDIT_EXISTING_CONTROL),
						CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_CREDIT_EXISTING_CONTROL));
						context.addCallbackParam("isValid", false);
						return;
					}
				}
			}
			selectedDemandCredit = (DemandeCreditDto) serviceLocator.getRechercheDemandeBudgetService().saveItem(selectedDemandCredit
					,sessionBean.getCurrentUser().getCompte().getIdCompte());
			listDemandCredits = serviceLocator.getRechercheDemandeBudgetService().findDemandCredits(selectedDemand.getId());
			demandHistory =serviceLocator.getSituationService().getEntityOccurrenceHistory(
					UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, selectedDemand.getId().intValue()); 
			currentDemand = serviceLocator.getRechercheDemandeBudgetService().findById(selectedDemandCredit.getDemandeBudgetId());
			selectedDemand  = SerializationUtils.clone(currentDemand);
			updateModelDemands();
			processTotalCredits();
			processRecap();
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
					.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_CREDIT_SUCCESS),
			CommonMessagesUtils.getStringValueFromBundleResource(
									RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
									RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_CREDIT_SUCCESS));
			context.addCallbackParam("isValid", true);
		}
	}
	public void saveOneActivity() {
		
		RequestContext context = RequestContext.getCurrentInstance();
		if(selectedDemandActivity== null)
			return;
		else {
			
			selectedDemandActivity.setDemandeBudgetId(currentDemand.getId());
			if(!toEditDemandActivity)  {
					// Test for existing Activity
					for (ActiviteBudgetDto _item : listDemandActivities) {
						if(selectedDemandActivity.getNcActiviteId().equals(_item.getNcActiviteId())
								&& (selectedDemandActivity.getGroupeId().equals(_item.getGroupeId()))
								&& (selectedDemandActivity.getAnnee() == _item.getAnnee())) {
							CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
									.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
													RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_ACTIVITY_EXISTING_CONTROL),
							CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
								RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_ACTIVITY_EXISTING_CONTROL));
							context.addCallbackParam("isValid", false);
							return;
						}
					}
			}
			else {
				// Test for existing Activity
				for (ActiviteBudgetDto _item : listDemandActivities) {
					if(selectedDemandActivity.getNcActiviteId().equals(_item.getNcActiviteId()) 
							&& (selectedDemandActivity.getGroupeId().equals(_item.getGroupeId()))
							&& (selectedDemandActivity.getAnnee() == _item.getAnnee())
							&& selectedDemandActivity.getId()!=_item.getId()) {
						CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
								.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_ACTIVITY_EXISTING_CONTROL),
						CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_ACTIVITY_EXISTING_CONTROL));
						context.addCallbackParam("isValid", false);
						return;
					}
				}
			}
			selectedDemandActivity = (ActiviteBudgetDto) serviceLocator.getRechercheDemandeBudgetService().saveItem(selectedDemandActivity
					,sessionBean.getCurrentUser().getCompte().getIdCompte());
			listDemandActivities = serviceLocator.getRechercheDemandeBudgetService().findDemandActivities(selectedDemandActivity.getDemandeBudgetId());
			demandHistory =serviceLocator.getSituationService().getEntityOccurrenceHistory(
					UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, selectedDemand.getId().intValue()); 
			currentDemand = serviceLocator.getRechercheDemandeBudgetService().findById(selectedDemandActivity.getDemandeBudgetId());
			selectedDemand  = SerializationUtils.clone(currentDemand);
			updateModelDemands();
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
					.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_ACTIVITY_SUCCESS),
			CommonMessagesUtils.getStringValueFromBundleResource(
									RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
									RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_ACTIVITY_SUCCESS));
			context.addCallbackParam("isValid", true);
		}
	}
	public void saveOneOperation() {
		
		RequestContext context = RequestContext.getCurrentInstance();
		if(selectedDemandOperation == null)
			return;
		else {
			
			selectedDemandOperation.setDemandeBudgetId(currentDemand.getId());
			if(!toEditDemandOperation)  {
					// Test for existing Operation
					for (DemandeOperationDto _item : listDemandOperations) {
						if(selectedDemandOperation.getNcOperationId().equals(_item.getNcOperationId())
								&& (selectedDemandOperation.getAnnee() == _item.getAnnee())) {
							CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
									.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
													RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_OPERATION_EXISTING_CONTROL),
							CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
								RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_OPERATION_EXISTING_CONTROL));
							context.addCallbackParam("isValid", false);
							return;
						}
					}
			}
			else {
				// Test for existing Activity
				for (DemandeOperationDto _item : listDemandOperations) {
					if(selectedDemandOperation.getNcOperationId().equals(_item.getNcOperationId())
							&& (selectedDemandOperation.getAnnee() == _item.getAnnee())
							&& selectedDemandOperation.getId()!=_item.getId()) {
						CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
								.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_OPERATION_EXISTING_CONTROL),
						CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_OPERATION_EXISTING_CONTROL));
						context.addCallbackParam("isValid", false);
						return;
					}
				}
			}
			selectedDemandOperation= (DemandeOperationDto) serviceLocator.getRechercheDemandeBudgetService().saveItem(selectedDemandOperation
					,sessionBean.getCurrentUser().getCompte().getIdCompte());
			listDemandOperations= serviceLocator.getRechercheDemandeBudgetService().findDemandOperations(selectedDemand.getId());
			demandHistory =serviceLocator.getSituationService().getEntityOccurrenceHistory(
					UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, selectedDemand.getId().intValue()); 
			currentDemand = serviceLocator.getRechercheDemandeBudgetService().findById(selectedDemandOperation.getDemandeBudgetId());
			selectedDemand  = SerializationUtils.clone(currentDemand);
			updateModelDemands();
			processTotalOperations();
			processRecap();
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
					.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_OPERATION_SUCCESS),
			CommonMessagesUtils.getStringValueFromBundleResource(
									RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
									RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_OPERATION_SUCCESS));
			context.addCallbackParam("isValid", true);
		}
	}
	public void saveOneResult() {
		
		RequestContext context = RequestContext.getCurrentInstance();
		if(selectedDemandResultat == null)
			return;
		else {
			
			selectedDemandResultat.setDemandeBudgetId(currentDemand.getId());
			if(!toEditDemandResultat)  {
					// Test for existing Result
					for (ResultatAttenduDto _item : listDemandResultats) {
						if(selectedDemandResultat.getNcResultatId().equals(_item.getNcResultatId())
								&& (selectedDemandResultat.getAnnee() == _item.getAnnee())) {
							CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
									.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
													RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_RESULT_EXISTING_CONTROL),
							CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
								RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_RESULT_EXISTING_CONTROL));
							context.addCallbackParam("isValid", false);
							return;
						}
					}
			}
			else {
				// Test for existing Result
				for (ResultatAttenduDto _item : listDemandResultats) {
					if(selectedDemandResultat.getNcResultatId().equals(_item.getNcResultatId())
							&& (selectedDemandResultat.getAnnee() == _item.getAnnee())
							&& selectedDemandResultat.getId()!=_item.getId()) {
						CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
								.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_RESULT_EXISTING_CONTROL),
						CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_DETAILS_RESULT_EXISTING_CONTROL));
						context.addCallbackParam("isValid", false);
						return;
					}
				}
			}
			selectedDemandResultat=  (ResultatAttenduDto) serviceLocator.getRechercheDemandeBudgetService().saveItem(selectedDemandResultat
					,sessionBean.getCurrentUser().getCompte().getIdCompte());
			listDemandResultats= serviceLocator.getRechercheDemandeBudgetService().findDemandExpectedResults(selectedDemand.getId());
			demandHistory =serviceLocator.getSituationService().getEntityOccurrenceHistory(
					UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, selectedDemand.getId().intValue()); 
			currentDemand = serviceLocator.getRechercheDemandeBudgetService().findById(selectedDemandResultat.getDemandeBudgetId());
			selectedDemand  = SerializationUtils.clone(currentDemand);
			updateModelDemands();
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
					.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_RESULT_SUCCESS),
			CommonMessagesUtils.getStringValueFromBundleResource(
									RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME,
									RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_SAVE_RESULT_SUCCESS));
			context.addCallbackParam("isValid", true);
		}
	}
	public void loadDemandDetail() {
		
		isNewDemand = false;
		canSubmitDemand = true;
		titleDetailsDemand= CommonMessagesUtils.getStringValueFromBundleResource(
				RechercheConstantBean.RECHERCHE_DEMANDE_BUDGET_RESSOURCE_MESSAGE_FILE_NAME, 
				RechercheConstantBean.RECHERCHE_DEAMANDE_BUDGET_RESSOURCE_MESSAGE_PANEL_DETAILS_TITLE_EDIT);
		if(listBeginYears== null) {
			initBeginListYears();
			initEndListYears();
		}
		if (listBeginYears != null  &&  !listBeginYears.isEmpty()
				&&	listEndYears != null  &&  !listEndYears.isEmpty()) {
				initListPrograms();
		}
		listDemandActivities = serviceLocator.getRechercheDemandeBudgetService().findDemandActivities(selectedDemand.getId());
		listDemandEquipements= serviceLocator.getRechercheDemandeBudgetService().findDemandEquipments(selectedDemand.getId());
		listDemandCredits= serviceLocator.getRechercheDemandeBudgetService().findDemandCredits(selectedDemand.getId());
		listDemandOperations= serviceLocator.getRechercheDemandeBudgetService().findDemandOperations(selectedDemand.getId());
		listDemandResultats= serviceLocator.getRechercheDemandeBudgetService().findDemandExpectedResults(selectedDemand.getId());
		demandHistory =serviceLocator.getSituationService().getEntityOccurrenceHistory(
				UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, selectedDemand.getId().intValue()); 
		recapTotalsAmountByYear = new HashMap<Short, Double>();
		processTotals();
		processRecap();
		if(selectedDemand.getSituationCode().equals(UtilConstant.SITUTAION_CREEE_CODE) ||
				selectedDemand.getSituationCode().equals(UtilConstant.SITUTAION_ENREGISTREE_CODE) ) {
			canEditDemand = true;
			canSubmitDemand = true;
			canValidateDemand = false;
		}
		else {
					if(selectedDemand.getSituationCode().equals(UtilConstant.SITUTAION_SOUMISE_VALIDATION_CODE)) {
						canValidateDemand = true;
					}
					else {
						canValidateDemand = false;
					}
					canEditDemand = false;
					canSubmitDemand = false;
		}
	}
	private void processTotals() {
		processTotalEquipments();
		processTotalCredits();
		processTotalOperations();
	}
	private void processTotalEquipments() {
		totalEquipmentsAmounts = (double) 0;
		if(listDemandEquipements != null) {
			for (DemandeEquipementDto _item : listDemandEquipements) {
				totalEquipmentsAmounts = totalEquipmentsAmounts + _item.getMontantEstime();
			}
		}
	}
	private void processTotalCredits() {
		totalCreditsAmounts = (double) 0;
		if(listDemandCredits != null) {
			for (DemandeCreditDto _item : listDemandCredits) {
				totalCreditsAmounts = totalCreditsAmounts + _item.getMontant();
			}
		}
	}
	private void processTotalOperations() {
		totalOperationsAmounts = (double) 0;
		if(listDemandOperations != null) {
			for (DemandeOperationDto _item : listDemandOperations) {
				totalOperationsAmounts = totalOperationsAmounts + _item.getMontantEstime();
			}
		}
	}
	
	private void processRecap() {
		
		recapTotalsAmountByYear = new  HashMap<Short, Double>();
		if(listDemandOperations != null) {
			for (DemandeOperationDto _item : listDemandOperations) {
				if(recapTotalsAmountByYear.containsKey(_item.getAnnee())) {
					Double _sum = recapTotalsAmountByYear.get(_item.getAnnee());
					_sum = _sum + _item.getMontantEstime();
					recapTotalsAmountByYear.remove(_item.getAnnee());
					recapTotalsAmountByYear.put(_item.getAnnee(), _sum);
				}
				else {
					recapTotalsAmountByYear.put(_item.getAnnee(), _item.getMontantEstime());
				}
			}
		}
		
		if(listDemandCredits != null) {
			for (DemandeCreditDto _item : listDemandCredits) {
				if(recapTotalsAmountByYear.containsKey(_item.getAnnee())) {
					Double _sum = recapTotalsAmountByYear.get(_item.getAnnee());
					_sum = _sum + _item.getMontant();
					recapTotalsAmountByYear.remove(_item.getAnnee());
					recapTotalsAmountByYear.put(_item.getAnnee(), _sum);
				}
				else {
					recapTotalsAmountByYear.put(_item.getAnnee(), _item.getMontant());
				}
			}
		}
		
		if(listDemandEquipements != null) {
			for (DemandeEquipementDto _item : listDemandEquipements) {
				if(recapTotalsAmountByYear.containsKey(_item.getAnnee())) {
					Double _sum = recapTotalsAmountByYear.get(_item.getAnnee());
					_sum = _sum + _item.getMontantEstime();
					recapTotalsAmountByYear.remove(_item.getAnnee());
					recapTotalsAmountByYear.put(_item.getAnnee(), _sum);
				}
				else {
					recapTotalsAmountByYear.put(_item.getAnnee(), _item.getMontantEstime());
				}
			}
		}
	}
	private void updateModelDemands() {

		boolean _exist = false;
		if(listDemands == null) {
			listDemands = new ArrayList<DemandeBudgetDto>();
		}
		for (DemandeBudgetDto _item : listDemands) {
			if(_item.getId().equals(currentDemand.getId())) {
				listDemands.set(listDemands.indexOf(_item), currentDemand);
				_exist = true;
			}
		}
		if(!_exist) {
			listDemands.add(currentDemand);
		}
	}
	private void updateDemandActivitiesModel() {

		boolean _exist = false;
		for (ActiviteBudgetDto _item : listDemandActivities) {
			if(_item.getId().equals(selectedDemandActivity.getId())) {
				listDemandActivities.set(listDemandActivities.indexOf(_item), selectedDemandActivity);
				_exist = true;
			}
		}
		if(!_exist) {
			listDemandActivities.add(selectedDemandActivity);
		}
	}
	
	// ===================================================================  
	// Listeners
	// ===================================================================
	public void onDemandRowSelect(SelectEvent event) {  
		
		
		if(selectedDemand != null) {
			if(currentDemand == null || selectedDemand.getId()!=currentDemand.getId()) {
						canShowDemandDetails= true;
						currentDemand = SerializationUtils.clone(selectedDemand);
						loadDemandDetail();
			}
		}
		
	}
	public void handleBeginYearChange(AjaxBehaviorEvent event) {
		startBeginYear = currentDemand.getAnneeDebut();
		initEndListYears();
		initListPrograms();
	 }  
	public void handleEndYearChange(AjaxBehaviorEvent event) {
		startBeginYear = currentDemand.getAnneeFin();
		initListPrograms();
	}  
	public void handleEquipmentFamilyChange(AjaxBehaviorEvent event) {
		initListSubFamilyEquipments(selectedDemandEquipment.getNcFamilleId());
	}  
	public void handleChapterChange(AjaxBehaviorEvent event) {
		initListArticlesByChapter(selectedDemandCredit.getNcChapitreId());
	}
	
}
