package dz.gov.mesrs.sii.gfc.web.jsf.bean.preparationBudgetFonctionnement.preparationBudgetEtablissement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.ConsolidationBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsFicheBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsProjetBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.FicheBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ProjetBudgetDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;

/**
 * Gere le budget propose de l'etablissement
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "budgetProposeEtabBean")
@ViewScoped
public class BudgetProposeEtabMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<FicheBesoinsDto> model;

	private LazyDataModel<ProjetBudgetDto> modelProjetsBudgets;

	private ProjetBudgetDto projetBudgetDto;
	private ProjetBudgetDto projetBudgetSearchDto;

	private FicheBesoinsDto ficheBesoinsDto;
	private FicheBesoinsDto ficheBesoinsSearchDto;

	private ConsolidationBesoinsDto consolidationBesoinsDto;
	private ConsolidationBesoinsDto consolidationBesoinsSearchDto;

	private DetailsFicheBesoinsDto detailsFicheBesoinsDto;
	private DetailsFicheBesoinsDto detailsFicheBesoinsSearchDto;

	private DetailsProjetBudgetDto detailsProjetBudgetDto;
	private DetailsProjetBudgetDto DetailsProjetBudgetSearchDto;

	// UI
	private boolean isView;
	private boolean isCrud;

	private boolean isViewDetailBudget;

	private List<SelectItem> listProjetsBudgets;

	public BudgetProposeEtabMBean() {

	}

	@PostConstruct
	public void init() {
		initUI();
		loadSearchResults();
	}

	private void initUI() {
		projetBudgetDto = new ProjetBudgetDto();
		ficheBesoinsSearchDto = new FicheBesoinsDto();
		consolidationBesoinsSearchDto = new ConsolidationBesoinsDto();

		projetBudgetSearchDto = new ProjetBudgetDto();
	}

	private void loadSearchResults() {
		modelProjetsBudgets = new LazyDataModel<ProjetBudgetDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(ProjetBudgetDto ficheBesoinsDto) {
				return ficheBesoinsDto.getId();
			}

			@Override
			public List<ProjetBudgetDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Boolean descending = null;

				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}
				}

				// seulement les projets de budget avec situation consolide
				SearchMode searchMode = new SearchMode(pageSize, first);
				Collection<Integer> collection = new ArrayList<Integer>();
				if (viewId.equals("BudgetProposeEdit")) {
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE,
									UtilConstants.SITUATION_EN_COURS_PREPARATION_CODE).getId());

					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE,
									UtilConstants.SITUATION_CONSOLIDE_CODE).getId());

					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));
				}

				modelProjetsBudgets.setRowCount(serviceLocator.getProjetBudgetService().countAllByExample(
						projetBudgetSearchDto, searchMode));

				List<ProjetBudgetDto> result = serviceLocator.getProjetBudgetService().findAllByExample(
						projetBudgetSearchDto, searchMode);

				if (modelProjetsBudgets.getRowCount() == 1) {
					projetBudgetDto = result.get(0);

					isCrud = true;
					isView = true;
				}

				return result;

				// return
				// serviceLocator.getProjetBudgetService().findAllByExample(projetBudgetSearchDto,
				// searchMode);
			}
		};
	}

	private void loadSearchResults0() {
		// TODO + situatioon de la fiche des besoins ?

		model = new LazyDataModel<FicheBesoinsDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(FicheBesoinsDto ficheBesoinsDto) {
				return ficheBesoinsDto.getId();
			}

			@Override
			public List<FicheBesoinsDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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
				// searchMode.addSortField(sortField);
				model.setRowCount(serviceLocator.getFicheBesoinsService().countAllByExample(ficheBesoinsSearchDto));
				return serviceLocator.getFicheBesoinsService().findAllByExample(ficheBesoinsSearchDto, searchMode);
			}
		};
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 22 déc. 2014 11:02:51
	 */
	public void searchAction() {
		loadSearchResults();
	}

	/**
	 * Handle changement du projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 2 dÃ©c. 2014 16:38:44
	 * @param event
	 * @throws Exception
	 */
	public void handleProjetBudgetSelectOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		projetBudgetDto = serviceLocator.getProjetBudgetService().findById(projetBudgetDto.getId());
	}

	/**
	 * Evenement de selection d'un agent comptable
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		ficheBesoinsDto = (FicheBesoinsDto) event.getObject();
		isViewDetailBudget = true;
	}

	/**
	 * Evenement de selection d'un agent comptable
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelectProjetBudget(SelectEvent event) {
		projetBudgetDto = (ProjetBudgetDto) event.getObject();

		isCrud = true;
		isView = true;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Edited", ((ConsolidationBesoinsDto) event.getObject()).getId()
				.toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((ConsolidationBesoinsDto) event.getObject()).getId()
				.toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Consolider les budget proposes des etablissements + central
	 * 
	 * @author Mounir.MESSAOUDI on : 23 déc. 2014 17:16:11
	 */
	public void consolidAction() {
		serviceLocator.getProjetBudgetService().consoliderProjetBudget(projetBudgetDto);
		CommonMessagesUtils.showSuccessSaveMessage();

	}

	/**
	 * Valider le projet de budget, l'utilisateur peux l'envoyer au MF
	 * 
	 * @author Mounir.MESSAOUDI on : 22 déc. 2014 11:03:03
	 */
	public void validateAction() {
		serviceLocator.getProjetBudgetService().aEnvoyerBudgetMF(projetBudgetDto);
		isCrud = false;

		CommonMessagesUtils.showSuccessValidationMessage();
	}

	/**
	 * Annuler l'action en cours
	 * 
	 * @author Mounir.MESSAOUDI on : 22 déc. 2014 11:03:07
	 */
	public void cancelAction() {
		isCrud = false;
		isView = false;
	}

	public void cancelDetailBudgetAction() {
		isViewDetailBudget = false;
	}

	public boolean isView() {
		return isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public boolean isViewDetailBudget() {
		return isViewDetailBudget;
	}

	public LazyDataModel<FicheBesoinsDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<FicheBesoinsDto> model) {
		this.model = model;
	}

	public FicheBesoinsDto getFicheBesoinsSearchDto() {
		return ficheBesoinsSearchDto;
	}

	public void setFicheBesoinsSearchDto(FicheBesoinsDto ficheBesoinsSearchDto) {
		this.ficheBesoinsSearchDto = ficheBesoinsSearchDto;
	}

	public FicheBesoinsDto getFicheBesoinsDto() {
		return ficheBesoinsDto;
	}

	public void setFicheBesoinsDto(FicheBesoinsDto ficheBesoinsDto) {
		this.ficheBesoinsDto = ficheBesoinsDto;
	}

	public ConsolidationBesoinsDto getConsolidationBesoinsDto() {
		return consolidationBesoinsDto;
	}

	public void setConsolidationBesoinsDto(ConsolidationBesoinsDto consolidationBesoinsDto) {
		this.consolidationBesoinsDto = consolidationBesoinsDto;
	}

	public ConsolidationBesoinsDto getConsolidationBesoinsSearchDto() {
		return consolidationBesoinsSearchDto;
	}

	public void setConsolidationBesoinsSearchDto(ConsolidationBesoinsDto consolidationBesoinsSearchDto) {
		this.consolidationBesoinsSearchDto = consolidationBesoinsSearchDto;
	}

	public ProjetBudgetDto getProjetBudgetDto() {
		return projetBudgetDto;
	}

	public void setProjetBudgetDto(ProjetBudgetDto projetBudgetDto) {
		this.projetBudgetDto = projetBudgetDto;
	}

	public DetailsFicheBesoinsDto getDetailsFicheBesoinsDto() {
		return detailsFicheBesoinsDto;
	}

	public void setDetailsFicheBesoinsDto(DetailsFicheBesoinsDto detailsFicheBesoinsDto) {
		this.detailsFicheBesoinsDto = detailsFicheBesoinsDto;
	}

	public DetailsFicheBesoinsDto getDetailsFicheBesoinsSearchDto() {
		return detailsFicheBesoinsSearchDto;
	}

	public void setDetailsFicheBesoinsSearchDto(DetailsFicheBesoinsDto detailsFicheBesoinsSearchDto) {
		this.detailsFicheBesoinsSearchDto = detailsFicheBesoinsSearchDto;
	}

	public DetailsProjetBudgetDto getDetailsProjetBudgetDto() {
		return detailsProjetBudgetDto;
	}

	public void setDetailsProjetBudgetDto(DetailsProjetBudgetDto detailsProjetBudgetDto) {
		this.detailsProjetBudgetDto = detailsProjetBudgetDto;
	}

	public DetailsProjetBudgetDto getDetailsProjetBudgetSearchDto() {
		return DetailsProjetBudgetSearchDto;
	}

	public void setDetailsProjetBudgetSearchDto(DetailsProjetBudgetDto detailsProjetBudgetSearchDto) {
		DetailsProjetBudgetSearchDto = detailsProjetBudgetSearchDto;
	}

	public List<SelectItem> getListProjetsBudgets() {
		if (listProjetsBudgets == null) {
			listProjetsBudgets = new ArrayList<>();
			// TODO seulement les projets en cours
			List<ProjetBudgetDto> l = serviceLocator.getProjetBudgetService().findAll();
			if (l != null && !l.isEmpty()) {
				for (ProjetBudgetDto projetBudgetDto : l) {
					listProjetsBudgets.add(new SelectItem(projetBudgetDto.getId(), projetBudgetDto.getIdentite() + " "
							+ projetBudgetDto.getDescription()));
				}
			}
		}
		return listProjetsBudgets;
	}

	public void setListProjetsBudgets(List<SelectItem> listProjetsBudgets) {
		this.listProjetsBudgets = listProjetsBudgets;
	}

	public LazyDataModel<ProjetBudgetDto> getModelProjetsBudgets() {
		return modelProjetsBudgets;
	}

	public void setModelProjetsBudgets(LazyDataModel<ProjetBudgetDto> modelProjetsBudgets) {
		this.modelProjetsBudgets = modelProjetsBudgets;
	}

}
