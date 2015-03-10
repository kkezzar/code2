/**
 * [dz.gov.mesrs.sii.recherche.web.jsf.bean.structure.RechercheStructureBB.java] 
 * @author rlaib on : 16 dï¿½c. 2014  16:06:14
 * 
 */
package dz.gov.mesrs.sii.recherche.web.jsf.bean.evaluation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.SerializationUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.recherche.business.model.dto.GrilleEvaluationDetailDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.GrilleEvaluationDto;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.RechercheConstantBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

@ManagedBean(name = "evlGrilleBB")
@ViewScoped
public class EvaluationGrilleBB extends BaseBean {
	
	private static final long serialVersionUID = 1L;
	
	// ===================================================================  
	// Constructors
	// ===================================================================

	public EvaluationGrilleBB() {
		
	}
	
	@PostConstruct
	public void init() {

	}

	// ===================================================================  
	// Properties Model
	// ===================================================================
	private String searchComponentKeyWords;
	private List<GrilleEvaluationDto> listGrids; 
	private GrilleEvaluationDto selectedGrid;
	private GrilleEvaluationDto currentGrid;
	private boolean canShowGridDetails = false;
	private boolean canEditGrid= false;
	private boolean canEditDetailGrid = false;
	private boolean toEditGridDetailDialog= false;
	private List<GrilleEvaluationDetailDto> selectedGridListDetails;
	private GrilleEvaluationDetailDto selectedGridDetail;
	private Long selectedGridDetailId;
	private String titleGridDetails;
	private String titleDialogGridDetail;
	private List<SelectItem> listChapters;
	private List<SelectItem> listCriterias;
	private List<SelectItem> listTypeAppreciations;



	public boolean isCanEditDetailGrid() {
		return canEditDetailGrid;
	}

	public void setCanEditDetailGrid(boolean canEditDetailGrid) {
		this.canEditDetailGrid = canEditDetailGrid;
	}

	public List<SelectItem> getListTypeAppreciations() {
		return listTypeAppreciations;
	}

	public void setListTypeAppreciations(List<SelectItem> listTypeAppreciations) {
		this.listTypeAppreciations = listTypeAppreciations;
	}

	public List<SelectItem> getListChapters() {
		return listChapters;
	}

	public void setListChapters(List<SelectItem> listChapters) {
		this.listChapters = listChapters;
	}

	public List<SelectItem> getListCriterias() {
		return listCriterias;
	}

	public void setListCriterias(List<SelectItem> listCriterias) {
		this.listCriterias = listCriterias;
	}

	public String getTitleDialogGridDetail() {
		return titleDialogGridDetail;
	}

	public void setTitleDialogGridDetail(String titleDialogGridDetail) {
		this.titleDialogGridDetail = titleDialogGridDetail;
	}

	public boolean isCanEditGrid() {
		return canEditGrid;
	}

	public void setCanEditGrid(boolean canEditGrid) {
		this.canEditGrid = canEditGrid;
	}

	public boolean isToEditGridDetailDialog() {
		return toEditGridDetailDialog;
	}

	public void setToEditGridDetailDialog(boolean toEditGridDetailDialog) {
		this.toEditGridDetailDialog = toEditGridDetailDialog;
	}

	public String getTitleGridDetails() {
		return titleGridDetails;
	}

	public void setTitleGridDetails(String titleGridDetails) {
		this.titleGridDetails = titleGridDetails;
	}

	public String getSearchComponentKeyWords() {
		return searchComponentKeyWords;
	}

	public void setSearchComponentKeyWords(String searchComponentKeyWords) {
		this.searchComponentKeyWords = searchComponentKeyWords;
	}

	public List<GrilleEvaluationDto> getListGrids() {
		return listGrids;
	}

	public void setListGrids(List<GrilleEvaluationDto> listGrids) {
		this.listGrids = listGrids;
	}

	public GrilleEvaluationDto getSelectedGrid() {
		return selectedGrid;
	}

	public void setSelectedGrid(GrilleEvaluationDto selectedGrid) {
		this.selectedGrid = selectedGrid;
	}

	public GrilleEvaluationDto getCurrentGrid() {
		return currentGrid;
	}

	public void setCurrentGrid(GrilleEvaluationDto currentGrid) {
		this.currentGrid = currentGrid;
	}

	public boolean isCanShowGridDetails() {
		return canShowGridDetails;
	}

	public void setCanShowGridDetails(boolean canShowGridDetails) {
		this.canShowGridDetails = canShowGridDetails;
	}

	public List<GrilleEvaluationDetailDto> getSelectedGridListDetails() {
		return selectedGridListDetails;
	}

	public void setSelectedGridListDetails(
			List<GrilleEvaluationDetailDto> selectedGridListDetails) {
		this.selectedGridListDetails = selectedGridListDetails;
	}

	public GrilleEvaluationDetailDto getSelectedGridDetail() {
		return selectedGridDetail;
	}

	public void setSelectedGridDetail(GrilleEvaluationDetailDto selectedGridDetail) {
		this.selectedGridDetail = selectedGridDetail;
	}

	public Long getSelectedGridDetailId() {
		return selectedGridDetailId;
	}

	public void setSelectedGridDetailId(Long selectedGridDetailId) {
		this.selectedGridDetailId = selectedGridDetailId;
	}
	
	// ===================================================================  
	// Listeners
	// ===================================================================
	public void  handleChaptersChange(AjaxBehaviorEvent event) {
		
		if(selectedGridDetail != null && selectedGridDetail.getNcChapitreId()!= null) {
			listCriterias = initCriteriasByChapter(selectedGridDetail.getNcChapitreId());
		}
	}
	private List<SelectItem> initCriteriasByChapter(Integer ncChapitreId) {

		List<NomenclatureDto> _list = serviceLocator.getNomenclatureService().findNcCompositeList(ncChapitreId, 
				dz.gov.mesrs.sii.commons.data.util.UtilConstant.NC_RCH_EVALUATION_CRITERE_NAME);
		List<SelectItem> _result = new ArrayList<SelectItem>();
		SelectItem defaultSelection = null;
		String _msg = "";
		if(_list != null && !_list.isEmpty()) {
			
			_msg = CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME, 
					RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_SAVE_GRID_SELECT_CRITERIA_DEFAULT_SELECTION);
			for (NomenclatureDto _item : _list) {
			_result.add(new SelectItem(_item.getId(), _item.getLibelleLongFr()));
			}
		}
		else {
			_msg = CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME, 
					RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_SAVE_GRID_SELECT_CRITERIA_NO_SELECTION);
		}
		defaultSelection =new SelectItem(0, _msg , null, false, false, true);
		_result.add(0, defaultSelection);
		return _result;
	}

	// ===================================================================  
	// Actions and Methods
	// ===================================================================
		
	public void searchGrids() {
		listGrids = serviceLocator.getRechercheEvaluationChercheurService().findByKeyWords(searchComponentKeyWords);
		if(listGrids  != null && !listGrids.isEmpty()) {
			if (listGrids.size() == 1) {
				selectedGrid= listGrids.get(0);
				currentGrid = SerializationUtils.clone(selectedGrid);
				canShowGridDetails = true;
				loadGridDetail();
			}
			else {
				canShowGridDetails = false;
			}
		}
		else {
			selectedGrid= currentGrid= null;
			canShowGridDetails = false;
		}
		
	}
	
	public void prepareAddNewGrid() {
				currentGrid = new GrilleEvaluationDto();
				selectedGridListDetails = new ArrayList<GrilleEvaluationDetailDto>();
				canShowGridDetails = true;
				canEditGrid = true;
				canEditDetailGrid = false;
				titleGridDetails= CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME, 
						RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_DETAILS_ADD);
				
	}
	
	public void onGridRowSelect(SelectEvent event) {  
		
		if(selectedGrid != null) {
			canEditDetailGrid = true;
			// Ceci est pour ne pas faire un raffraichissement depuis la BDD quand clique sur la ligne deja selectionnee
			if(currentGrid == null || selectedGrid.getId()!=currentGrid.getId()) {
						canShowGridDetails= true;
						canEditGrid = true;
						currentGrid= SerializationUtils.clone(selectedGrid);
						loadGridDetail();
						titleGridDetails= CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME, 
								RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_DETAILS_EDIT);
			}
		}
	}
	
	private void initListTypeAppreciations() {
		listTypeAppreciations = this.findNomenclatureByName(dz.gov.mesrs.sii.commons.data.util.UtilConstant.NC_RCH_EVALUATION_APPRECIATION_NAME);
	}

	private void initListChapters() {
		listChapters = this.findNomenclatureByName(dz.gov.mesrs.sii.commons.data.util.UtilConstant.NC_RCH_EVALUATION_CHAPITRE_NAME);
	}

	public void removeOneGridDetail() {

		if(selectedGridDetailId== null)
				return;
			serviceLocator.getRechercheEvaluationChercheurService().removeDetail(selectedGridDetailId);
			loadGridDetail();
			
			// Suppression Chapitre / critère reussie
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_REMOVE_GRID_DETAIL_SUCCESS),
					CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_REMOVE_GRID_DETAIL_SUCCESS));
	}
	
	private void loadGridDetail() {
		selectedGridListDetails= serviceLocator.getRechercheEvaluationChercheurService().findGridDetails(currentGrid.getId());
		if(selectedGridListDetails == null) {
			selectedGridListDetails = new ArrayList<GrilleEvaluationDetailDto>();
		}
	}

	public void prepareEditOneGridDetail() {
		
		if (selectedGridDetailId != null) {
			
			toEditGridDetailDialog= true;
			titleDialogGridDetail= CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME, 
					RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_DIALOG_DETAILS_EDIT);
			selectedGridDetail= serviceLocator.getRechercheEvaluationChercheurService().findOneDetailById(selectedGridDetailId);
			if(listChapters== null) {
				initListChapters();
				if(listChapters != null && !listChapters.isEmpty()) {
					Integer _idChapter = Integer.parseInt(listChapters.get(0).getValue().toString());
					if(_idChapter != null) {
						listCriterias = initCriteriasByChapter(_idChapter);
					}
				}
			}
			if(listTypeAppreciations== null) {
				initListTypeAppreciations();
			}
		}
	}

	public void prepareAddOneDetail() {
		if(selectedGridDetail  == null) {
				selectedGridDetail = new GrilleEvaluationDetailDto();
		}
		else {
			selectedGridDetail.setId(null);
		}
		toEditGridDetailDialog = false;
		titleDialogGridDetail= CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME, 
				RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_DIALOG_DETAILS_ADD);
		if(listChapters== null) {
			initListChapters();
			if(listChapters != null && !listChapters.isEmpty()) {
				Integer _idChapter = Integer.parseInt(listChapters.get(0).getValue().toString());
				if(_idChapter != null) {
					listCriterias = initCriteriasByChapter(_idChapter);
				}
			}
		}
		if(listTypeAppreciations== null) {
			initListTypeAppreciations();
		}
	}
	
	private Integer getNextChapterNumber() {

			Integer _result =null;
			if(selectedGridListDetails == null || selectedGridListDetails.isEmpty()) {
				_result = 0;
			}
			else {
				for (GrilleEvaluationDetailDto _item : selectedGridListDetails) {
					if(_item.getNcCritereId() == null) {
						_result = _result +1;
					}
				}
			}
			return _result +1;
	}
	
	private Integer getNextCriteriaNumberWithinChapter() {

		Integer _result =null;
		if(selectedGridListDetails == null || selectedGridListDetails.isEmpty()) {
			_result = 0;
		}
		else {
			for (GrilleEvaluationDetailDto _item : selectedGridListDetails) {
				if(_item.getNcChapitreId().equals(selectedGridDetail.getNcChapitreId())
					&&	_item.getNcCritereId()!=null) {
					_result = _result +1;
				}
			}
		}
		return _result +1;
	}

	public void saveGrid() {
		
		if(currentGrid== null)
			return;
		else {
			if(!currentGrid.equals(selectedGrid)) {
					currentGrid = serviceLocator.getRechercheEvaluationChercheurService().insertOrUpdate(currentGrid);
					selectedGrid = SerializationUtils.clone(currentGrid);

					CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
							.getStringValueFromBundleResource(
										RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
										RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_SAVE_GRID_SUCCESS),
					CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_SAVE_GRID_SUCCESS));
			}
			else {
					CommonMessagesUtils.addWarnMessageWithoutKey(CommonMessagesUtils
						.getStringValueFromBundleResource(
									RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
									RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_SAVE_GRID_NO_CHANGES),
					CommonMessagesUtils.getStringValueFromBundleResource(
						RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
						RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_SAVE_GRID_NO_CHANGES));
			}
			toEditGridDetailDialog= true;
			canEditDetailGrid = true;
			
		}
	}

	public void saveOneGridDetail() {	
		
		RequestContext context = RequestContext.getCurrentInstance();
		if(selectedGridListDetails == null) {
			selectedGridListDetails = new ArrayList<GrilleEvaluationDetailDto>();
		}
		if(selectedGridDetail== null)
			return;
		else {
			
			selectedGridDetail.setGrilleEvaluationId(currentGrid.getId());
			if(!toEditGridDetailDialog)  {
					// Test for existing Chapter/Criteria / mode ajout
					for (GrilleEvaluationDetailDto _item : selectedGridListDetails) {
						if(selectedGridDetail.getNcChapitreId().equals(_item.getNcChapitreId())
								&& selectedGridDetail.getNcCritereId().equals(_item.getNcCritereId())) {
							CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
									.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
													RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_SAVE_GRID_DETAIL_EXISTING_CONTROL),
							CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
								RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_SAVE_GRID_DETAIL_EXISTING_CONTROL));
							context.addCallbackParam("isValid", false);
							return;
						}
					}
			}
			else {
				// Test for existing Chapter/Criteria / mode edition
				for (GrilleEvaluationDetailDto _item : selectedGridListDetails) {
					if(selectedGridDetail.getNcChapitreId().equals(_item.getNcChapitreId())
							&& selectedGridDetail.getNcCritereId().equals(_item.getNcCritereId())
							&& selectedGridDetail.getNcAppreciationId().equals(_item.getNcAppreciationId())
							&& selectedGridDetail.getNumero().equals(_item.getNumero())
							&& selectedGridDetail.getId().equals(_item.getId())) {
						CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
								.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_SAVE_GRID_DETAIL_EXISTING_CONTROL),
						CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_SAVE_GRID_DETAIL_EXISTING_CONTROL));
						context.addCallbackParam("isValid", false);
						return;
					}
				}
			}
			if(selectedGridDetail.getNcCritereId()==0) {
				selectedGridDetail.setNcCritereId(null);
			}
			selectedGridDetail= serviceLocator.getRechercheEvaluationChercheurService().saveDetail(selectedGridDetail);
			loadGridDetail();
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
					.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_SAVE_GRID_DETAIL_SUCCESS),
					CommonMessagesUtils.getStringValueFromBundleResource(
									RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
									RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_SAVE_GRID_DETAIL_SUCCESS));
			context.addCallbackParam("isValid", true);
		}
	}
}
