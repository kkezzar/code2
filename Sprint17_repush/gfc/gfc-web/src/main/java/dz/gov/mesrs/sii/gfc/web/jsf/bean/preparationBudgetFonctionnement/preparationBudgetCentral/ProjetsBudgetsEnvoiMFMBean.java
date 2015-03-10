package dz.gov.mesrs.sii.gfc.web.jsf.bean.preparationBudgetFonctionnement.preparationBudgetCentral;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.google.common.collect.ListMultimap;

import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsProjetBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ProjetBudgetDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;

/**
 * Enregistre la date d'envoi de budget du MERS au MF
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "projetsBudgetsEnvoiMFBean")
@ViewScoped
public class ProjetsBudgetsEnvoiMFMBean extends BaseBean {

	public final static String PREPARATION_BUDGET_BUNDLE_MSG_NAME = "preparationBudgetFonctionnementMsgs";

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<ProjetBudgetDto> model;
	private ProjetBudgetDto projetBudgetDto;
	private ProjetBudgetDto projetBudgetSearchDto;

	private ListMultimap<String, DetailsProjetBudgetDto> listDetailsProjetBudgetDtoDto;

	private List<Object[]> totauxDetailsProjetBudget;

	// UI
	private boolean isView;
	private boolean isCrud;

	public ProjetsBudgetsEnvoiMFMBean() {
	}

	@PostConstruct
	public void init() {
		initUI();
		loadSearchResults();
	}

	private void initUI() {
		projetBudgetSearchDto = new ProjetBudgetDto();
	}

	private void loadSearchResults() {
		model = new LazyDataModel<ProjetBudgetDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(ProjetBudgetDto pojetBudgetDto) {
				return pojetBudgetDto.getId();
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

				// seulement les projets de budget avec situation A envoyer au
				// MF !
				SearchMode searchMode = new SearchMode(pageSize, first);
				Collection<Integer> collection = new ArrayList<Integer>();

				collection.add(serviceLocator
						.getSituationService()
						.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE,
								UtilConstants.SITUATION_A_ENVOYER_MF_CODE).getId());
				searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));

				model.setRowCount(serviceLocator.getProjetBudgetService().countAllByExample(projetBudgetSearchDto,
						searchMode));

				List<ProjetBudgetDto> results = serviceLocator.getProjetBudgetService().findAllByExample(
						projetBudgetSearchDto, searchMode);

				if (model.getRowCount() == 1) {
					projetBudgetDto = results.get(0);

					listDetailsProjetBudgetDtoDto = serviceLocator.getProjetBudgetService()
							.splitDetailsBudgetByTypeOfChapitre(projetBudgetDto);
					totauxDetailsProjetBudget = serviceLocator.getProjetBudgetService().getTotalOfDetailsProjetBudget(
							projetBudgetDto);

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

	/**
	 * Evenement de selection d'un agent comptable
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		projetBudgetDto = (ProjetBudgetDto) event.getObject();

		listDetailsProjetBudgetDtoDto = serviceLocator.getProjetBudgetService().splitDetailsBudgetByTypeOfChapitre(
				projetBudgetDto);
		totauxDetailsProjetBudget = serviceLocator.getProjetBudgetService().getTotalOfDetailsProjetBudget(
				projetBudgetDto);

		isCrud = true;
		isView = true;
	}

	/**
	 * Enregister la date d'envoi du projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:19:55
	 */
	public void saveAction() {
		projetBudgetDto = serviceLocator.getProjetBudgetService().save(projetBudgetDto);
		CommonMessagesUtils.showSuccessUpdateMessage();
	}

	/**
	 * Valider le projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:19:37
	 */
	public void validateAction() {
		projetBudgetDto = serviceLocator.getProjetBudgetService().envoyerBudgetMF(projetBudgetDto);
		isCrud = false;

		CommonMessagesUtils.showSuccessValidationMessage();

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

	public BigDecimal getTotalMontantProposeDetailProjetBudget(Boolean recette_type) {
		for (Object[] x : totauxDetailsProjetBudget) {
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

	public LazyDataModel<ProjetBudgetDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<ProjetBudgetDto> model) {
		this.model = model;
	}

	public ProjetBudgetDto getProjetBudgetDto() {
		return projetBudgetDto;
	}

	public void setProjetBudgetDto(ProjetBudgetDto projetBudgetDto) {
		this.projetBudgetDto = projetBudgetDto;
	}

	public ListMultimap<String, DetailsProjetBudgetDto> getListDetailsProjetBudgetDtoDto() {
		return listDetailsProjetBudgetDtoDto;
	}
}