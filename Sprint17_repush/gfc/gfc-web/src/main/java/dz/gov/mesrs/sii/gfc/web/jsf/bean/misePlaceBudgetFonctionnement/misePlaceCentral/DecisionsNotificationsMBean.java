package dz.gov.mesrs.sii.gfc.web.jsf.bean.misePlaceBudgetFonctionnement.misePlaceCentral;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.google.common.collect.ListMultimap;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.DecisionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsNotificationDecisionBudgetDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;

/**
 * Enregistrer les decisions de notifications
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "decisionsNotificationsBean")
@ViewScoped
public class DecisionsNotificationsMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<DecisionBudgetDto> model;
	private DecisionBudgetDto decisionBudgetDto;
	private DecisionBudgetDto decisionBudgetSearchDto;

	private ListMultimap<String, DetailsNotificationDecisionBudgetDto> listDetailsNotifications;

	private List<Object[]> totaux;

	// UI

	private List<SelectItem> listProjetsBudget;
	private List<SelectItem> listExercicesBudgetaires;

	private String searchKeywords;

	private boolean isView;
	private boolean isCrud;

	public DecisionsNotificationsMBean() {
	}

	@PostConstruct
	public void init() {
		initUI();
		loadSearchResults();
	}

	private void initUI() {
		decisionBudgetSearchDto = new DecisionBudgetDto();

		listProjetsBudget = new ArrayList<SelectItem>();
		listExercicesBudgetaires = new ArrayList<SelectItem>();
	}

	private void loadSearchResults() {
		model = new LazyDataModel<DecisionBudgetDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(DecisionBudgetDto pojetBudgetDto) {
				return pojetBudgetDto.getId();
			}

			@Override
			public List<DecisionBudgetDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Boolean descending = null;

				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}
				}

				SearchMode searchMode = new SearchMode(pageSize, first);
				Collection<Integer> collection = new ArrayList<Integer>();

				switch (viewId) {
				case "DecisionsNotificationBudgetEdit":
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DECISION_NOTIFICATION,
									UtilConstants.SITUATION_CREEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));
					break;

				case "EnregistrerDateSignatureDecisons":
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DECISION_NOTIFICATION,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));
					break;

				default:
					break;
				}
				if (viewId.equals("ProjetsBudgetsEdit")) {

				}

				model.setRowCount(serviceLocator.getDecisionBudgetService().countAllByExample(decisionBudgetSearchDto,
						searchMode));
				List<DecisionBudgetDto> results = serviceLocator.getDecisionBudgetService().findAllByExample(
						decisionBudgetSearchDto, searchMode);
				if (model.getRowCount() == 1) {
					decisionBudgetDto = results.get(0);
					listDetailsNotifications = serviceLocator.getDecisionBudgetService()
							.splitDetailsNotificationsByTypeOfChapitre(decisionBudgetDto);
					totaux = serviceLocator.getDecisionBudgetService().getTotalOfNotificationsBudget(decisionBudgetDto);

					isCrud = true;
					isView = true;
				}

				return results;
			}
		};
	}

	public void searchAction() {
		loadSearchResults();
	}

	public void advancedSearchAction() {

	}

	/**
	 * Evenement de selection d'une decision de notification
	 * 
	 * @author Mounir.MESSAOUDI on : 12 jan. 2015 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		decisionBudgetDto = (DecisionBudgetDto) event.getObject();
		listDetailsNotifications = serviceLocator.getDecisionBudgetService().splitDetailsNotificationsByTypeOfChapitre(
				decisionBudgetDto);
		totaux = serviceLocator.getDecisionBudgetService().getTotalOfNotificationsBudget(decisionBudgetDto);

		isCrud = true;
		isView = true;
	}

	/**
	 * Enregister la decision de notification
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:19:55
	 */
	public void saveAction() {
		decisionBudgetDto = serviceLocator.getDecisionBudgetService().save(decisionBudgetDto);
		CommonMessagesUtils.showSuccessUpdateMessage();
	}

	/**
	 * Valider le projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:19:37
	 */
	public void validateAction() {
		SituationEntiteDto situation = serviceLocator.getSituationService().findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_DECISION_NOTIFICATION, UtilConstants.SITUATION_VALIDEE_CODE);
		decisionBudgetDto.setSituation(situation);
		serviceLocator.getDecisionBudgetService().save(decisionBudgetDto);

		CommonMessagesUtils.showSuccessValidationMessage();
		isCrud = false;

	}

	public void saveDateValidationAction() {
		decisionBudgetDto = serviceLocator.getDecisionBudgetService().save(decisionBudgetDto);
		CommonMessagesUtils.showSuccessUpdateMessage();

	}

	public void validateDateValidationAction() {
		SituationEntiteDto situation = serviceLocator.getSituationService().findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_GFC_DECISION_NOTIFICATION, UtilConstants.SITUATION_SIGNEE);
		decisionBudgetDto.setSituation(situation);

		serviceLocator.getDecisionBudgetService().save(decisionBudgetDto);

		CommonMessagesUtils.showSuccessValidationMessage();
		isCrud = false;
	}

	/**
	 * Annuler l'operation encours sur le projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:20:07
	 */
	public void cancelAction() {
		isCrud = false;
		isView = false;
	}

	public BigDecimal getTotalMontant(Boolean recette_type) {
		for (Object[] x : totaux) {
			if (recette_type == (Boolean) x[0]) {
				return (BigDecimal) x[1];
			}
		}
		return null;
	}

	public boolean isView() {
		return isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public LazyDataModel<DecisionBudgetDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<DecisionBudgetDto> model) {
		this.model = model;
	}

	public DecisionBudgetDto getDecisionBudgetDto() {
		return decisionBudgetDto;
	}

	public void setDecisionBudgetDto(DecisionBudgetDto decisionBudgetDto) {
		this.decisionBudgetDto = decisionBudgetDto;
	}

	public DecisionBudgetDto getDecisionBudgetSearchDto() {
		return decisionBudgetSearchDto;
	}

	public void setDecisionBudgetSearchDto(DecisionBudgetDto decisionBudgetSearchDto) {
		this.decisionBudgetSearchDto = decisionBudgetSearchDto;
	}

	public ListMultimap<String, DetailsNotificationDecisionBudgetDto> getListDetailsNotifications() {
		return listDetailsNotifications;
	}

	public List<SelectItem> getListProjetsBudget() {
		return listProjetsBudget;
	}

	public List<SelectItem> getListExercicesBudgetaires() {
		return listExercicesBudgetaires;
	}

	public String getSearchKeywords() {
		return searchKeywords;
	}
}
