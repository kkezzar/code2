/**
 * [dz.gov.mesrs.sii.recherche.web.jsf.bean.structure.RechercheStructureBB.java] 
 * @author rlaib on : 16 dï¿½c. 2014  16:06:14
 */
package dz.gov.mesrs.sii.recherche.web.jsf.bean.programme;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.SerializationUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.ProcessusDto;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.RechercheConstantBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto;

@ManagedBean(name = "circuitBB")
@ViewScoped
public class CircuitValidationBB extends BaseBean {
	
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 16 dec. 2014  16:08:20
	 */
	private static final long serialVersionUID = 1L;

	// ===================================================================  
	// Constructors
	// ===================================================================

	public CircuitValidationBB() {
		
	}
	
	@PostConstruct
	public void init() {
		
		List<EntiteDto >_entities = serviceLocator.getProcessusService().findEntityByCodeDomaine(RechercheConstantBean.RCH_RECHERCHE_DOMAINE_CODE);
		if(_entities != null && !_entities.isEmpty()) {
			currentEntities =  new ArrayList<SelectItem>();
			for (EntiteDto _entite : _entities) {
				currentEntities.add(new SelectItem(_entite.getId(), _entite.getLibelle()));
			}
		}
		currentTypeGroupe = serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_TYPE_GROUPE_NAME, 
				UtilConstant.NC_TYPE_GROUPE_RECHERCHE_CODE); 
		initProcess();
	}

	// ===================================================================  
	// Properties Model
	// ===================================================================
	private List<SelectItem> currentEntities;
	private NomenclatureDto currentTypeGroupe;
	private List<ProcessusDto> listProcessus;
	private List<ProcessusDto> listProcessusClone;
	private List<EtapeDto> listSteps;
	private Integer selectedProcessusId;
	private Integer selectedStepId;
	private Integer selectedEntiteId;
	private EtapeDto selectedStep;
	private ProcessusDto selectedProcessus;
	private String searchComponentKeyWords;
	private boolean canShowProcessusDetails = false;
	private boolean toEditProcessDialog= false;
	private boolean toEditStepDialog= false;
	private boolean canDeleteProcess = false;
	private boolean canShowStepsDetails= false;
	private List<SelectItem>  listGroupes;
	private List<SelectItem>  listRoles;
	private List<SelectItem>  listNextSteps;
	private List<SelectItem>  listPreviousSteps;
	private String titlePanelDetails;
	
	
	/**
	 * [CircuitValidationBB.titlePanelDetails] Getter 
	 * @author rlaib on : 26 janv. 2015  12:25:58
	 * @return the titlePanelDetails
	 */
	public String getTitlePanelDetails() {
		return titlePanelDetails;
	}

	/**
	 * [CircuitValidationBB.titlePanelDetails] Setter 
	 * @author rlaib on : 26 janv. 2015  12:25:58
	 * @param titlePanelDetails the titlePanelDetails to set
	 */
	public void setTitlePanelDetails(String titlePanelDetails) {
		this.titlePanelDetails = titlePanelDetails;
	}

	/**
	 * [CircuitValidationBB.canShowStepsDetails] Getter 
	 * @author rlaib on : 25 janv. 2015  15:03:13
	 * @return the canShowStepsDetails
	 */
	public boolean isCanShowStepsDetails() {
		return canShowStepsDetails;
	}

	/**
	 * [CircuitValidationBB.canShowStepsDetails] Setter 
	 * @author rlaib on : 25 janv. 2015  15:03:13
	 * @param canShowStepsDetails the canShowStepsDetails to set
	 */
	public void setCanShowStepsDetails(boolean canShowStepsDetails) {
		this.canShowStepsDetails = canShowStepsDetails;
	}

	/**
	 * [CircuitValidationBB.selectedEntiteId] Getter 
	 * @author rlaib on : 21 janv. 2015  16:58:30
	 * @return the selectedEntiteId
	 */
	public Integer getSelectedEntiteId() {
		return selectedEntiteId;
	}

	/**
	 * [CircuitValidationBB.selectedEntiteId] Setter 
	 * @author rlaib on : 21 janv. 2015  16:58:30
	 * @param selectedEntiteId the selectedEntiteId to set
	 */
	public void setSelectedEntiteId(Integer selectedEntiteId) {
		this.selectedEntiteId = selectedEntiteId;
	}

	/**
	 * [CircuitValidationBB.canDeleteProcess] Getter 
	 * @author rlaib on : 21 janv. 2015  15:05:56
	 * @return the canDeleteProcess
	 */
	public boolean isCanDeleteProcess() {
		return canDeleteProcess;
	}

	/**
	 * [CircuitValidationBB.canDeleteProcess] Setter 
	 * @author rlaib on : 21 janv. 2015  15:05:56
	 * @param canDeleteProcess the canDeleteProcess to set
	 */
	public void setCanDeleteProcess(boolean canDeleteProcess) {
		this.canDeleteProcess = canDeleteProcess;
	}

	/**
	 * [CircuitValidationBB.listNextSteps] Getter 
	 * @author rlaib on : 20 janv. 2015  15:59:45
	 * @return the listNextSteps
	 */
	public List<SelectItem> getListNextSteps() {
		return listNextSteps;
	}

	/**
	 * [CircuitValidationBB.listNextSteps] Setter 
	 * @author rlaib on : 20 janv. 2015  15:59:45
	 * @param listNextSteps the listNextSteps to set
	 */
	public void setListNextSteps(List<SelectItem> listNextSteps) {
		this.listNextSteps = listNextSteps;
	}

	/**
	 * [CircuitValidationBB.listPreviousSteps] Getter 
	 * @author rlaib on : 20 janv. 2015  15:59:45
	 * @return the listPreviousSteps
	 */
	public List<SelectItem> getListPreviousSteps() {
		return listPreviousSteps;
	}

	/**
	 * [CircuitValidationBB.listPreviousSteps] Setter 
	 * @author rlaib on : 20 janv. 2015  15:59:45
	 * @param listPreviousSteps the listPreviousSteps to set
	 */
	public void setListPreviousSteps(List<SelectItem> listPreviousSteps) {
		this.listPreviousSteps = listPreviousSteps;
	}

	/**
	 * [CircuitValidationBB.currentTypeGroupe] Getter 
	 * @author rlaib on : 20 janv. 2015  15:31:17
	 * @return the currentTypeGroupe
	 */
	public NomenclatureDto getCurrentTypeGroupe() {
		return currentTypeGroupe;
	}

	/**
	 * [CircuitValidationBB.currentTypeGroupe] Setter 
	 * @author rlaib on : 20 janv. 2015  15:31:17
	 * @param currentTypeGroupe the currentTypeGroupe to set
	 */
	public void setCurrentTypeGroupe(NomenclatureDto currentTypeGroupe) {
		this.currentTypeGroupe = currentTypeGroupe;
	}

	/**
	 * [CircuitValidationBB.listGroupes] Getter 
	 * @author rlaib on : 20 janv. 2015  15:15:39
	 * @return the listGroupes
	 */
	public List<SelectItem> getListGroupes() {
		return listGroupes;
	}

	/**
	 * [CircuitValidationBB.listGroupes] Setter 
	 * @author rlaib on : 20 janv. 2015  15:15:39
	 * @param listGroupes the listGroupes to set
	 */
	public void setListGroupes(List<SelectItem> listGroupes) {
		this.listGroupes = listGroupes;
	}

	/**
	 * [CircuitValidationBB.listRoles] Getter 
	 * @author rlaib on : 20 janv. 2015  15:15:39
	 * @return the listRoles
	 */
	public List<SelectItem> getListRoles() {
		return listRoles;
	}

	/**
	 * [CircuitValidationBB.listRoles] Setter 
	 * @author rlaib on : 20 janv. 2015  15:15:39
	 * @param listRoles the listRoles to set
	 */
	public void setListRoles(List<SelectItem> listRoles) {
		this.listRoles = listRoles;
	}

	/**
	 * [CircuitValidationBB.toEditStepDialog] Getter 
	 * @author rlaib on : 20 janv. 2015  12:45:28
	 * @return the toEditStepDialog
	 */
	public boolean isToEditStepDialog() {
		return toEditStepDialog;
	}

	/**
	 * [CircuitValidationBB.toEditStepDialog] Setter 
	 * @author rlaib on : 20 janv. 2015  12:45:28
	 * @param toEditStepDialog the toEditStepDialog to set
	 */
	public void setToEditStepDialog(boolean toEditStepDialog) {
		this.toEditStepDialog = toEditStepDialog;
	}

	/**
	 * [CircuitValidationBB.listProcessusClone] Getter 
	 * @author rlaib on : 20 janv. 2015  11:59:58
	 * @return the listProcessusClone
	 */
	public List<ProcessusDto> getListProcessusClone() {
		return listProcessusClone;
	}

	/**
	 * [CircuitValidationBB.listProcessusClone] Setter 
	 * @author rlaib on : 20 janv. 2015  11:59:58
	 * @param listProcessusClone the listProcessusClone to set
	 */
	public void setListProcessusClone(List<ProcessusDto> listProcessusClone) {
		this.listProcessusClone = listProcessusClone;
	}

	/**
	 * [CircuitValidationBB.listSteps] Getter 
	 * @author rlaib on : 19 janv. 2015  16:53:26
	 * @return the listSteps
	 */
	public List<EtapeDto> getListSteps() {
		return listSteps;
	}

	/**
	 * [CircuitValidationBB.listSteps] Setter 
	 * @author rlaib on : 19 janv. 2015  16:53:26
	 * @param listSteps the listSteps to set
	 */
	public void setListSteps(List<EtapeDto> listSteps) {
		this.listSteps = listSteps;
	}

	/**
	 * [CircuitValidationBB.selectedStepId] Getter 
	 * @author rlaib on : 19 janv. 2015  16:53:26
	 * @return the selectedStepId
	 */
	public Integer getSelectedStepId() {
		return selectedStepId;
	}

	/**
	 * [CircuitValidationBB.selectedStepId] Setter 
	 * @author rlaib on : 19 janv. 2015  16:53:26
	 * @param selectedStepId the selectedStepId to set
	 */
	public void setSelectedStepId(Integer selectedStepId) {
		this.selectedStepId = selectedStepId;
	}

	/**
	 * [CircuitValidationBB.selectedStep] Getter 
	 * @author rlaib on : 19 janv. 2015  16:53:26
	 * @return the selectedStep
	 */
	public EtapeDto getSelectedStep() {
		return selectedStep;
	}

	/**
	 * [CircuitValidationBB.selectedStep] Setter 
	 * @author rlaib on : 19 janv. 2015  16:53:26
	 * @param selectedStep the selectedStep to set
	 */
	public void setSelectedStep(EtapeDto selectedStep) {
		this.selectedStep = selectedStep;
	}


	/**
	 * [CircuitValidationBB.currentEntities] Getter 
	 * @author rlaib on : 21 janv. 2015  16:27:04
	 * @return the currentEntities
	 */
	public List<SelectItem> getCurrentEntities() {
		return currentEntities;
	}

	/**
	 * [CircuitValidationBB.currentEntities] Setter 
	 * @author rlaib on : 21 janv. 2015  16:27:04
	 * @param currentEntities the currentEntities to set
	 */
	public void setCurrentEntities(List<SelectItem> currentEntities) {
		this.currentEntities = currentEntities;
	}

	/**
	 * [CircuitValidationBB.toEditProcessDialog] Getter 
	 * @author rlaib on : 19 janv. 2015  09:56:29
	 * @return the toEditProcessDialog
	 */
	public boolean isToEditProcessDialog() {
		return toEditProcessDialog;
	}

	/**
	 * [CircuitValidationBB.toEditProcessDialog] Setter 
	 * @author rlaib on : 19 janv. 2015  09:56:29
	 * @param toEditProcessDialog the toEditProcessDialog to set
	 */
	public void setToEditProcessDialog(boolean toEditProcessDialog) {
		this.toEditProcessDialog = toEditProcessDialog;
	}

	/**
	 * [CircuitValidationBB.canShowProcessusDetails] Getter 
	 * @author rlaib on : 19 janv. 2015  09:26:04
	 * @return the canShowProcessusDetails
	 */
	public boolean isCanShowProcessusDetails() {
		return canShowProcessusDetails;
	}

	/**
	 * [CircuitValidationBB.canShowProcessusDetails] Setter 
	 * @author rlaib on : 19 janv. 2015  09:26:04
	 * @param canShowProcessusDetails the canShowProcessusDetails to set
	 */
	public void setCanShowProcessusDetails(boolean canShowProcessusDetails) {
		this.canShowProcessusDetails = canShowProcessusDetails;
	}

	/**
	 * [CircuitValidationBB.listProcessus] Getter 
	 * @author rlaib on : 19 janv. 2015  09:17:23
	 * @return the listProcessus
	 */
	public List<ProcessusDto> getListProcessus() {
		return listProcessus;
	}

	/**
	 * [CircuitValidationBB.listProcessus] Setter 
	 * @author rlaib on : 19 janv. 2015  09:17:23
	 * @param listProcessus the listProcessus to set
	 */
	public void setListProcessus(List<ProcessusDto> listProcessus) {
		this.listProcessus = listProcessus;
	}

	/**
	 * [CircuitValidationBB.searchComponentKeyWords] Getter 
	 * @author rlaib on : 19 janv. 2015  09:16:06
	 * @return the searchComponentKeyWords
	 */
	public String getSearchComponentKeyWords() {
		return searchComponentKeyWords;
	}

	/**
	 * [CircuitValidationBB.searchComponentKeyWords] Setter 
	 * @author rlaib on : 19 janv. 2015  09:16:06
	 * @param searchComponentKeyWords the searchComponentKeyWords to set
	 */
	public void setSearchComponentKeyWords(String searchComponentKeyWords) {
		this.searchComponentKeyWords = searchComponentKeyWords;
	}

	/**
	 * [CircuitValidationBB.selectedProcessusId] Getter 
	 * @author rlaib on : 19 janv. 2015  08:58:51
	 * @return the selectedProcessusId
	 */
	public Integer getSelectedProcessusId() {
		return selectedProcessusId;
	}

	/**
	 * [CircuitValidationBB.selectedProcessusId] Setter 
	 * @author rlaib on : 19 janv. 2015  08:58:51
	 * @param selectedProcessusId the selectedProcessusId to set
	 */
	public void setSelectedProcessusId(Integer selectedProcessusId) {
		this.selectedProcessusId = selectedProcessusId;
	}

	/**
	 * [CircuitValidationBB.selectedProcessus] Getter 
	 * @author rlaib on : 19 janv. 2015  08:58:51
	 * @return the selectedProcessus
	 */
	public ProcessusDto getSelectedProcessus() {
		return selectedProcessus;
	}

	/**
	 * [CircuitValidationBB.selectedProcessus] Setter 
	 * @author rlaib on : 19 janv. 2015  08:58:51
	 * @param selectedProcessus the selectedProcessus to set
	 */
	public void setSelectedProcessus(ProcessusDto selectedProcessus) {
		this.selectedProcessus = selectedProcessus;
	}
	
	// ===================================================================  
	// Actions and Methods
	// ===================================================================

	/**
	 * [CircuitValidationBB.searchProcess] Method 
	 * @author rlaib  on : 20 janv. 2015  12:48:15
	 */
	public void searchProcess(){
		
		listProcessus = serviceLocator.getProcessusService().findByKeyWords(searchComponentKeyWords);
		canShowProcessusDetails = false;
		selectedProcessus = null;
	}
	
	/**
	 * [CircuitValidationBB.loadProcessusDetail] Method 
	 * @author rlaib  on : 19 janv. 2015  09:26:21
	 */
	private void loadProcessusDetail() {
	
		selectedEntiteId = selectedProcessus.getEntiteId();
		listSteps = serviceLocator.getProcessusService().findStepsOfProcess(selectedProcessus.getId());
		if(listSteps != null) {
			
			List<EtapeDto> _list = new ArrayList<EtapeDto>();
			EtapeDto start= null;
			Integer idNextStep = null;
			while (_list.size() != listSteps.size()) {
				for (EtapeDto _step : listSteps) {
					if(_step.getEtapePrecedenteId() == null &&  _list.isEmpty()){
						_list.add(_step);
						start = _step;
						idNextStep = start.getEtapeSuivanteId();
					}
					else {
						if(idNextStep != null) {
								if(_step.getId() == idNextStep) {
									_list.add(_step);
										idNextStep = _step.getEtapeSuivanteId();
								}
						}
					}
				}
				
			}
			listSteps = _list;
			canDeleteProcess = false;
		}
		else
			canDeleteProcess = true;
		
	}
	
	/**
	 * [CircuitValidationBB.prepareAddProcess] Method 
	 * @author rlaib  on : 20 janv. 2015  12:48:10
	 */
	public void prepareAddProcess() {
		selectedProcessus = new ProcessusDto();
		toEditProcessDialog= false;
		canShowStepsDetails = false;
		canShowProcessusDetails = true;
		canDeleteProcess = false;
		listSteps = null;
		titlePanelDetails = CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME, 
				RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_PANEL_DETAILS_TITLE_ADD);
	}
	
	/**
	 * [CircuitValidationBB.prepareAddOneStep] Method 
	 * @author rlaib  on : 20 janv. 2015  12:48:05
	 */
	public void prepareAddOneStep() {
		selectedStep = new EtapeDto();
		toEditStepDialog= false;
		if(listGroupes == null) {
			initListGroupes();
			initListRoles();
		}
		initListPreviousSteps();
	}
	
	/**
	 * [CircuitValidationBB.initListPreviousSteps] Method 
	 * @author rlaib  on : 20 janv. 2015  17:55:20
	 */
	private void initListPreviousSteps() {
		
		if(listSteps != null && !listSteps.isEmpty()) {
			
			listPreviousSteps = new ArrayList<SelectItem>();
			if (!toEditStepDialog) {
					// Mode ajout --> la liste des etape precedentes est simplement la dernire etape ajoutee.
					listPreviousSteps.add(new SelectItem(listSteps.get(listSteps.size()-1).getId(),listSteps.get(listSteps.size()-1).getLibelleFr()));
			}
			else {
				// Mode edition --> On recupere simplement l etape precedente de l etape a editer.
				for (EtapeDto _step : listSteps) {
					if(selectedStep.getEtapePrecedenteId() != null) {
						if(_step.getId() == selectedStep.getEtapePrecedenteId()) {
							listPreviousSteps.add(new SelectItem(_step.getId(),_step.getLibelleFr()));
						}
					}
				}
				
			}
		}
		else
			listPreviousSteps = null;
			
		
	}

	/**
	 * [CircuitValidationBB.initListRoles] Method 
	 * @author rlaib  on : 20 janv. 2015  15:32:52
	 */
	private void initListRoles() {
		
		if(currentTypeGroupe !=null) {
			Integer idTypeGroupeRecherche = currentTypeGroupe.getId();
			if(idTypeGroupeRecherche != null) {
					List<NomenclatureDto> listRolesReferentiel = serviceLocator.getNomenclatureService().findNcCompositeList(idTypeGroupeRecherche, UtilConstant.NC_ROLE_NAME);
					listRoles =  new ArrayList<SelectItem>();
					for (NomenclatureDto _nomenclatureDto : listRolesReferentiel) {
						listRoles.add(new SelectItem(_nomenclatureDto.getId(),_nomenclatureDto.getLibelleLongFr()));
					}
					
			}
		}
		
	}

	/**
	 * [CircuitValidationBB.initListGroupes] Method 
	 * @author rlaib  on : 20 janv. 2015  15:16:10
	 */
	private void initListGroupes() {
	
		if(currentTypeGroupe !=null) {
			Integer idTypeGroupeRecherche = currentTypeGroupe.getId();
			if(idTypeGroupeRecherche != null) {
					List<RefGroupeDto> listGroupesReferentiel = serviceLocator.getRefGroupeService().findGeneric(sessionBean.getIdEtablissement(),"%", idTypeGroupeRecherche);
					listGroupes = new ArrayList<SelectItem>();
					for (RefGroupeDto _refGroupeDto : listGroupesReferentiel) {
						listGroupes.add(new SelectItem(_refGroupeDto.getId(),_refGroupeDto.getLlGroupe()));
					}
			}
		}
		
	}

	/**
	 * [CircuitValidationBB.prepareEditProcess] Method 
	 * @author rlaib  on : 20 janv. 2015  12:47:58
	 */
	public void prepareEditProcess() {
		if (selectedProcessusId != null) {
			toEditProcessDialog = true;
			selectedProcessus= serviceLocator.getProcessusService().findById(selectedProcessusId);
		}
	}
	
	/**
	 * [CircuitValidationBB.prepareEditOneStep] Method ²
	/**
	 * [CircuitValidationBB.saveProcess] Method 
	 * @author rlaib  on : 20 janv. 2015  12:48:24
	 */
	public void saveProcess() {
		
		RequestContext context = RequestContext.getCurrentInstance();
		if(selectedProcessus == null)
			return;
		else {
				// Test for already existing Process
				if(listProcessus != null) {
						for (ProcessusDto _processusDto : listProcessus) {
							if(selectedProcessus.getId() != _processusDto.getId()) {
							if(_processusDto.getCode().trim().equalsIgnoreCase(selectedProcessus.getCode().trim())) {
								CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
										.getStringValueFromBundleResource(
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_PROCESS_EXISTING_CODE),
								CommonMessagesUtils.getStringValueFromBundleResource(
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_PROCESS_EXISTING_CODE));
								listProcessus = listProcessusClone;
								return;
							}
							else {
								
								if(_processusDto.getLibelleFr().trim().equalsIgnoreCase(selectedProcessus.getLibelleFr().trim())) {
									CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
											.getStringValueFromBundleResource(
													RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
													RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_PROCESS_EXISTING_LABEL_FR),
									CommonMessagesUtils.getStringValueFromBundleResource(
													RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
													RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_PROCESS_EXISTING_LABEL_FR));
									listProcessus = listProcessusClone;
									return;
								}
							}
						}
					}
			}
			
		}
		selectedProcessus.setEntiteId(selectedEntiteId);
		serviceLocator.getProcessusService().insertOrUpdate(selectedProcessus);
		initProcess();
		canShowProcessusDetails = true;
		canShowStepsDetails = true;
		// Ajout groupe reussi
		if(!toEditProcessDialog) {
				CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
							.getStringValueFromBundleResource(
										RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
										RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_PROCESS_SUCCESS),
				CommonMessagesUtils.getStringValueFromBundleResource(
						RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
						RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_PROCESS_SUCCESS));
				listSteps = null;
		}
		else {
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
					.getStringValueFromBundleResource(
								RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
								RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_UPDATE_PROCESS_SUCCESS),
			CommonMessagesUtils.getStringValueFromBundleResource(
								RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
								RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_UPDATE_PROCESS_SUCCESS));
		}
	
		context.addCallbackParam("isValid", true);
	}
	
	/**
	 * [CircuitValidationBB.saveOneStep] Method 
	 * @author rlaib  on : 20 janv. 2015  12:47:50
	 */
	public void saveOneStep() {
		
		RequestContext context = RequestContext.getCurrentInstance();
		if(selectedStep== null)
			return;
		else {
			if(!toEditStepDialog && listSteps != null) {
				// Test for already existing Step
						for (EtapeDto _etapeDto : listSteps) {
							if(_etapeDto.getGroupeId().equals(selectedStep.getGroupeId()) && _etapeDto.getRoleId().equals(selectedStep.getRoleId())) {
								CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
										.getStringValueFromBundleResource(
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_STEP_EXISTING_GROUPE_ROLE),
												CommonMessagesUtils.getStringValueFromBundleResource(
														RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
														RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_STEP_EXISTING_GROUPE_ROLE));
								return;
							}
							if(_etapeDto.getLibelleFr().trim().equalsIgnoreCase(selectedStep.getLibelleFr().trim())) {
								CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
										.getStringValueFromBundleResource(
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_STEP_EXISTING_LABEL_FR),
								CommonMessagesUtils.getStringValueFromBundleResource(
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_STEP_EXISTING_LABEL_FR));
								return;
							}
						}
			}
		
		}
	
		selectedStep.setProcessusId(selectedProcessus.getId());
		selectedStep.setProcessusEntiteCode(selectedProcessus.getEntiteCode());
		selectedStep.setEstMultiple(false);
		selectedStep.setMultiplicite(1);
		selectedStep = serviceLocator.getProcessusService().saveOneStepValidation(selectedStep);
		loadProcessusDetail();
		
		// Ajout groupe reussi
		if(!toEditStepDialog) {
				CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
							.getStringValueFromBundleResource(
										RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
										RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_STEP_SUCCESS),
				CommonMessagesUtils.getStringValueFromBundleResource(
						RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
						RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_STEP_SUCCESS));
		}
		else {
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
					.getStringValueFromBundleResource(
								RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
								RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_UPDATE_STEP_SUCCESS),
		CommonMessagesUtils.getStringValueFromBundleResource(
				RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
				RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_UPDATE_STEP_SUCCESS));
		}
	
		context.addCallbackParam("isValid", true);
			
		
	}

	/**
	 * [CircuitValidationBB.initProcess] Method 
	 * @author rlaib  on : 19 janv. 2015  13:25:14
	 */
	private void initProcess() {

		listProcessus = serviceLocator.getProcessusService().findByCodeDomaine(RechercheConstantBean.RCH_RECHERCHE_DOMAINE_CODE);
		listProcessusClone = new ArrayList<ProcessusDto>(listProcessus.size());
		for (ProcessusDto _item : listProcessus) {
			listProcessusClone.add(SerializationUtils.clone(_item));
		}
	}

	/**
	 * [CircuitValidationBB.removeProcess] Method 
	 * @author rlaib  on : 20 janv. 2015  12:48:45
	 */
	public void removeProcess() {

		if(selectedProcessus== null)
				return;
		selectedProcessusId = selectedProcessus.getId();
		serviceLocator.getProcessusService().remove(selectedProcessusId);
		initProcess();
		selectedProcessus = null;
		canShowProcessusDetails = false;
		// Suppression Process reussie
		CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
					RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
					RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_REMOVE_PROCESS_SUCCESS),
		CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
					RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_REMOVE_PROCESS_SUCCESS));
	}
	public void removeOneStep() {
		
		if(selectedStepId== null)
			return;
		serviceLocator.getProcessusService().removeOneStep(selectedStepId);
		loadProcessusDetail();
		// Suppression Process reussie
		CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
				RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
				RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_REMOVE_STEP_SUCCESS),
		CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
						RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_REMOVE_STEP_SUCCESS));
	}
	
	// ===================================================================  
	// Listeners
	// ===================================================================

	/**
	 * [CircuitValidationBB.onProcessusRowSelect] Method 
	 * @author rlaib  on : 20 janv. 2015  12:48:49
	 * @param event
	 */
	public void onProcessusRowSelect(SelectEvent event) {  
		
		canShowProcessusDetails= true;
		canShowStepsDetails = true;
		loadProcessusDetail();
		titlePanelDetails = CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME, 
				RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_PANEL_DETAILS_TITLE_EDIT);
	}
	
}
