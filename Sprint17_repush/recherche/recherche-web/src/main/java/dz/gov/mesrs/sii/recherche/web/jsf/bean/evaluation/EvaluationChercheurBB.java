/**
 * [dz.gov.mesrs.sii.recherche.web.jsf.bean.structure.RechercheStructureBB.java] 
 * @author rlaib on : 16 d�c. 2014  16:06:14
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
import javax.faces.model.SelectItemGroup;

import org.apache.commons.lang3.SerializationUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.recherche.business.model.dto.EvaluationChercheurDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.EvaluationValeurDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.GrilleEvaluationDetailDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.GrilleEvaluationDto;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.RechercheConstantBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

@ManagedBean(name = "evlChercheurBB")
@ViewScoped
public class EvaluationChercheurBB extends BaseBean {
	
	private static final long serialVersionUID = 1L;
	
	// ===================================================================  
	// Constructors
	// ===================================================================

	public EvaluationChercheurBB() {
		
	}
	
	@PostConstruct
	public void init() {

	}

	// ===================================================================  
	// Properties Model
	// ===================================================================
	private String searchComponentKeyWords;
	private List<EvaluationChercheurDto> listEvaluations; 
	private EvaluationChercheurDto selectedEvaluation;
	private EvaluationChercheurDto currentEvaluation;
	private boolean canShowEvaluationDetails = false;
	private boolean canEditEvaluation = false;
	private boolean canEditEvaluationValues = false;
	private boolean toEditEvaluationValueDialog = false;
	private String titleEvaluationDetails;
	private String titleDialogEvaluationValue;
	private List<SelectItem> listGridsEvaluation;
	private List<EvaluationValeurDto> currentEvaluationValues;
	private EvaluationValeurDto selectedEvaluationValue;
	private Long selectedEvaluationValueId;
	private List<SelectItem> listGridCriterias;
	private List<SelectItem> listAppreciations;
	private RefIndividuDto currentEvaluationIndividu;
	
	public RefIndividuDto getCurrentEvaluationIndividu() {
		return currentEvaluationIndividu;
	}

	public void setCurrentEvaluationIndividu(
			RefIndividuDto currentEvaluationIndividu) {
		this.currentEvaluationIndividu = currentEvaluationIndividu;
	}

	public List<SelectItem> getListGridCriterias() {
		return listGridCriterias;
	}

	public void setListGridCriterias(List<SelectItem> listGridCriterias) {
		this.listGridCriterias = listGridCriterias;
	}

	public List<SelectItem> getListAppreciations() {
		return listAppreciations;
	}

	public void setListAppreciations(List<SelectItem> listAppreciations) {
		this.listAppreciations = listAppreciations;
	}

	public String getTitleDialogEvaluationValue() {
		return titleDialogEvaluationValue;
	}

	public void setTitleDialogEvaluationValue(String titleDialogEvaluationValue) {
		this.titleDialogEvaluationValue = titleDialogEvaluationValue;
	}

	public boolean isToEditEvaluationValueDialog() {
		return toEditEvaluationValueDialog;
	}

	public void setToEditEvaluationValueDialog(boolean toEditEvaluationValueDialog) {
		this.toEditEvaluationValueDialog = toEditEvaluationValueDialog;
	}

	public boolean isCanEditEvaluationValues() {
		return canEditEvaluationValues;
	}

	public void setCanEditEvaluationValues(boolean canEditEvaluationValues) {
		this.canEditEvaluationValues = canEditEvaluationValues;
	}

	public List<EvaluationValeurDto> getCurrentEvaluationValues() {
		return currentEvaluationValues;
	}

	public void setCurrentEvaluationValues(
			List<EvaluationValeurDto> currentEvaluationValues) {
		this.currentEvaluationValues = currentEvaluationValues;
	}

	public EvaluationValeurDto getSelectedEvaluationValue() {
		return selectedEvaluationValue;
	}

	public void setSelectedEvaluationValue(
			EvaluationValeurDto selectedEvaluationValue) {
		this.selectedEvaluationValue = selectedEvaluationValue;
	}

	public Long getSelectedEvaluationValueId() {
		return selectedEvaluationValueId;
	}

	public void setSelectedEvaluationValueId(Long selectedEvaluationValueId) {
		this.selectedEvaluationValueId = selectedEvaluationValueId;
	}

	public List<SelectItem> getListGridsEvaluation() {
		return listGridsEvaluation;
	}

	public void setListGridsEvaluation(List<SelectItem> listGridsEvaluation) {
		this.listGridsEvaluation = listGridsEvaluation;
	}

	public String getTitleEvaluationDetails() {
		return titleEvaluationDetails;
	}

	public void setTitleEvaluationDetails(String titleEvaluationDetails) {
		this.titleEvaluationDetails = titleEvaluationDetails;
	}

	public boolean isCanEditEvaluation() {
		return canEditEvaluation;
	}

	public void setCanEditEvaluation(boolean canEditEvaluation) {
		this.canEditEvaluation = canEditEvaluation;
	}

	public String getSearchComponentKeyWords() {
		return searchComponentKeyWords;
	}

	public void setSearchComponentKeyWords(String searchComponentKeyWords) {
		this.searchComponentKeyWords = searchComponentKeyWords;
	}

	public List<EvaluationChercheurDto> getListEvaluations() {
		return listEvaluations;
	}

	public void setListEvaluations(List<EvaluationChercheurDto> listEvaluations) {
		this.listEvaluations = listEvaluations;
	}

	public EvaluationChercheurDto getSelectedEvaluation() {
		return selectedEvaluation;
	}

	public void setSelectedEvaluation(EvaluationChercheurDto selectedEvaluation) {
		this.selectedEvaluation = selectedEvaluation;
	}

	public EvaluationChercheurDto getCurrentEvaluation() {
		return currentEvaluation;
	}

	public void setCurrentEvaluation(EvaluationChercheurDto currentEvaluation) {
		this.currentEvaluation = currentEvaluation;
	}

	public boolean isCanShowEvaluationDetails() {
		return canShowEvaluationDetails;
	}

	public void setCanShowEvaluationDetails(boolean canShowEvaluationDetails) {
		this.canShowEvaluationDetails = canShowEvaluationDetails;
	}
	
	
	// ===================================================================  
	// Listeners
	// ===================================================================

	public void onEvaluationRowSelect(SelectEvent event) {  
		
		if(selectedEvaluation != null) {
			canEditEvaluationValues = true;
			// Ceci est pour ne pas faire un raffraichissement depuis la BDD quand clique sur la ligne deja selectionnee
			if(currentEvaluation == null || selectedEvaluation.getId()!=currentEvaluation.getId()) {
						canShowEvaluationDetails= true;
						canEditEvaluation = true;
						currentEvaluation= SerializationUtils.clone(selectedEvaluation);
						currentEvaluation.setRefIndividuNomLatin(currentEvaluation.getRefIndividuPrenomLatin()+ " " + currentEvaluation.getRefIndividuNomLatin());
						loadEvaluationDetail();
						titleEvaluationDetails= CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME, 
								RechercheConstantBean.RECHERCHE_GRILLE_EVALUATION_RESSOURCE_MESSAGE_DETAILS_EDIT);
						if(listGridsEvaluation == null) {
							initListGrid();
						}
			}
		}
	}

	public void  handleEvaluationGridChange(AjaxBehaviorEvent event) {
		
	}

	public void onIndividuSelect(SelectEvent event) {
		currentEvaluationIndividu = (RefIndividuDto) event.getObject();
		currentEvaluation.setRefIndividuId(currentEvaluationIndividu.getId());
		currentEvaluation.setRefIndividuNomLatin(currentEvaluationIndividu.getPrenomLatin() + " " + currentEvaluationIndividu.getNomLatin());
//		currentEvaluation.setRefIndividuPrenomLatin(currentEvaluationIndividu.getPrenomLatin());
		
	}
	// ===================================================================  
	// Actions and Methods
	// ===================================================================
	public void searchEvaluations() {
		listEvaluations = serviceLocator.getRechercheEvaluationChercheurService().findEvaluationsByKeyWords(searchComponentKeyWords);
		if(listEvaluations  != null && !listEvaluations.isEmpty()) {
			if (listEvaluations.size() == 1) {
				selectedEvaluation= listEvaluations.get(0);
				currentEvaluation= SerializationUtils.clone(selectedEvaluation);
				canShowEvaluationDetails= true;
				loadEvaluationDetail();
			}
			else {
				canShowEvaluationDetails = false;
			}
		}
		else {
			selectedEvaluation= currentEvaluation= null;
			canShowEvaluationDetails = false;
		}
	}
	
	private void loadEvaluationDetail() {
		
		currentEvaluationValues = serviceLocator.getRechercheEvaluationChercheurService().findEvaluationValuesByEvaluationId(currentEvaluation.getId());
		
	}

	public void saveEvaluation() {
		
		if(currentEvaluation== null)
			return;
		else {
			if(!currentEvaluation.equals(selectedEvaluation)) {
				currentEvaluation = serviceLocator.getRechercheEvaluationChercheurService().saveOneEvaluation(currentEvaluation);
				selectedEvaluation = SerializationUtils.clone(currentEvaluation);

					CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
							.getStringValueFromBundleResource(
										RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
										RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_SAVE_EVALUATION_SUCCESS),
					CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_SAVE_EVALUATION_SUCCESS));
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
			toEditEvaluationValueDialog= true;
			canEditEvaluationValues = true;
			
		}
	}

	public void prepareAddNewEvaluation() {
		currentEvaluation = new EvaluationChercheurDto();
		currentEvaluationValues = new ArrayList<EvaluationValeurDto>();
		canShowEvaluationDetails= true;
		canEditEvaluation = true;
		canEditEvaluationValues = false;
		titleEvaluationDetails= CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME, 
				RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_DETAILS_ADD);
		if(listGridsEvaluation == null) {
			initListGrid();
		}
	}
	
	private void initListGrid() {
		
		List<GrilleEvaluationDto> _list = serviceLocator.getRechercheEvaluationChercheurService().findAllGrids();
		if(_list != null && !_list.isEmpty()) {
			listGridsEvaluation = new ArrayList<SelectItem>();
			for (GrilleEvaluationDto _item : _list) {
				listGridsEvaluation.add(new SelectItem(_item.getId(), _item.getIntituleFr()));
			}
		}
	}
	
	public void prepareAddOneEvaluationValue() {
		if(selectedEvaluationValue== null) {
			selectedEvaluationValue = new EvaluationValeurDto();
		}
		else {
			selectedEvaluationValue.setId(null);
		}
		toEditEvaluationValueDialog= false;
		titleDialogEvaluationValue= CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME, 
				RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_VALUE_RESSOURCE_MESSAGE_DETAILS_ADD);
		if(listGridCriterias== null) {
			initGridCriterias();
		}
		if(listAppreciations== null) {
			initListAppreciations();
		}
	}

	private void initListAppreciations() {

		listAppreciations = this.findNomenclatureByName(dz.gov.mesrs.sii.commons.data.util.UtilConstant.NC_RCH_EVALUATION_VALUE_APPRECIATION_NAME);
	}

	private void initGridCriterias() {

		List<GrilleEvaluationDetailDto> _list = serviceLocator.getRechercheEvaluationChercheurService().findGridDetails(currentEvaluation.getGrilleEvaluationId());
		if(_list != null && !_list.isEmpty()) {
			listGridCriterias = new ArrayList<SelectItem>();
			List<GrilleEvaluationDetailDto> _listGroupDtos = new ArrayList<GrilleEvaluationDetailDto>();
			for (GrilleEvaluationDetailDto _item : _list) {
				if( _item.getNcCritereId() == 0) {
					_listGroupDtos.add(_item);
				}
			}
			
			for (GrilleEvaluationDetailDto _groupDto : _listGroupDtos) {
				List<GrilleEvaluationDetailDto> _listItems = new ArrayList<GrilleEvaluationDetailDto>();
				for (GrilleEvaluationDetailDto _item : _list) {
					if( _item.getNcCritereId() != 0) {
						if(_groupDto.getNcChapitreId().equals(_item.getNcChapitreId())) {
							_listItems.add(_item);
						}
					}
				}
				
				SelectItemGroup chapitre = new SelectItemGroup(_groupDto.getNcChapitreIntituleFr());
				SelectItem[] _groupItems = new SelectItem[_listItems.size()];
				int i = 0;
				for (GrilleEvaluationDetailDto _crt : _listItems) {
					SelectItem _it = new SelectItem(_crt.getId(),
							_crt.getNcCritereIntituleFr());
					_groupItems[i] = _it;
					i++;
				}
				chapitre.setSelectItems(_groupItems);
				listGridCriterias.add(chapitre);
			}
		
		}
		
	}
	
	public void saveOneEvaluationValue() {	
		
		RequestContext context = RequestContext.getCurrentInstance();
		if(currentEvaluationValues== null) {
			currentEvaluationValues = new ArrayList<EvaluationValeurDto>();
		}
		if(selectedEvaluationValue== null)
			return;
		else {
			
			selectedEvaluationValue.setEvaluationChercheurId(currentEvaluation.getId());
			if(!toEditEvaluationValueDialog)  {
					// Test for existing Evaluation / mode ajout
					for (EvaluationValeurDto _item : currentEvaluationValues) {
						if(selectedEvaluationValue.getGrilleEvaluationDetailId().equals(_item.getGrilleEvaluationDetailId())
							) {
							CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
									.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
													RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_VALUE_RESSOURCE_MESSAGE_SAVE_EXISTING_CONTROL),
							CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
								RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_VALUE_RESSOURCE_MESSAGE_SAVE_EXISTING_CONTROL));
							context.addCallbackParam("isValid", false);
							return;
						}
					}
			}
//			else {
//				// Test for existing Evaluation / mode edition
//				for (EvaluationValeurDto _item : currentEvaluationValues) {
//					if(selectedEvaluationValue.getGrilleEvaluationDetailId().equals(_item.getGrilleEvaluationDetailId())
//							&& selectedEvaluationValue.getId().equals(_item.getId())
//							&& selectedEvaluationValue.getId().equals(_item.getId())) {
//						CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
//								.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
//												RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_VALUE_RESSOURCE_MESSAGE_SAVE_EXISTING_CONTROL),
//						CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
//							RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_VALUE_RESSOURCE_MESSAGE_SAVE_EXISTING_CONTROL));
//						context.addCallbackParam("isValid", false);
//						return;
//					}
//				}
//			}
			selectedEvaluationValue= serviceLocator.getRechercheEvaluationChercheurService().saveOneEvaluationValue(selectedEvaluationValue);
			loadEvaluationDetail();
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
					.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_VALUE_RESSOURCE_MESSAGE_SAVE_SUCCESS),
					CommonMessagesUtils.getStringValueFromBundleResource(
									RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
									RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_VALUE_RESSOURCE_MESSAGE_SAVE_SUCCESS));
			context.addCallbackParam("isValid", true);
		}
	}
	
	public void removeOneEvaluationValue() {

		if(selectedEvaluationValueId== null)
				return;
			serviceLocator.getRechercheEvaluationChercheurService().removeOneEvaluationValue(selectedEvaluationValueId);
			loadEvaluationDetail();
			
			// Suppression ligne evaluation reussie
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_VALUE_RESSOURCE_MESSAGE_REMOVE_VALUE_SUCCESS),
					CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_VALUE_RESSOURCE_MESSAGE_REMOVE_VALUE_SUCCESS));
	}
	
	public void prepareEditEvaluationValue() {
		
		if (selectedEvaluationValueId != null) {
			
			toEditEvaluationValueDialog= true;
			titleDialogEvaluationValue= CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_RESSOURCE_MESSAGE_FILE_NAME, 
					RechercheConstantBean.RECHERCHE_EVALUATION_CHERCHEUR_VALUE_RESSOURCE_MESSAGE_DETAILS_EDIT);
			selectedEvaluationValue= serviceLocator.getRechercheEvaluationChercheurService().findOneEvaluationValue(selectedEvaluationValueId);
			if(listGridCriterias== null) {
				initGridCriterias();
			}
			if(listAppreciations== null) {
				initListAppreciations();
			}
		}
	}
}
