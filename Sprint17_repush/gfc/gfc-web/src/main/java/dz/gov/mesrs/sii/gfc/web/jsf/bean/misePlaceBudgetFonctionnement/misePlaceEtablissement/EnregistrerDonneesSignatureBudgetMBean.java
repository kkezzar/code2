package dz.gov.mesrs.sii.gfc.web.jsf.bean.misePlaceBudgetFonctionnement.misePlaceEtablissement;

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
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsReparatitionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.RepartitionBudgetDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

/**
 * Enregistrer les donnees de signature du budget par article
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 18/11/2015 18:06:31
 */
@ManagedBean(name = "enregistrerDonneesSignatureBudgetBean")
@ViewScoped
public class EnregistrerDonneesSignatureBudgetMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<RepartitionBudgetDto> model;

	private RepartitionBudgetDto repartitionBudgetDto;
	private RepartitionBudgetDto repartitionBudgetSearchDto;

	private ListMultimap<String, DetailsReparatitionBudgetDto> listDetailsReparatitionBudgetDto;

	private List<Object[]> totaux;

	// UI

	private boolean isView;
	private boolean isCrud;

	public EnregistrerDonneesSignatureBudgetMBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		try {
			initUI();
			loadSearchResults();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void initUI() {
		repartitionBudgetSearchDto = new RepartitionBudgetDto();

	}

	private void loadSearchResults() {
		// les fiches des besoins de l'etablissement en cours!
		RefEtablissementDto etablissement = new RefEtablissementDto();
		etablissement.setId(sessionBean.getCompte().getEtabId());
		repartitionBudgetSearchDto.setEtablissement(etablissement);

		model = new LazyDataModel<RepartitionBudgetDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(RepartitionBudgetDto pojetBudgetDto) {
				return pojetBudgetDto.getId();
			}

			@Override
			public List<RepartitionBudgetDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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

				collection.add(serviceLocator
						.getSituationService()
						.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DECISION_REPARTITION,
								UtilConstants.SITUATION_VALIDEE_CODE).getId());

				searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));

				model.setRowCount(serviceLocator.getRepartitionBudgetService().countAllByExample(
						repartitionBudgetSearchDto, searchMode));
				List<RepartitionBudgetDto> results = serviceLocator.getRepartitionBudgetService().findAllByExample(
						repartitionBudgetSearchDto, searchMode);

				if (model.getRowCount() == 1) {
					repartitionBudgetDto = results.get(0);

					listDetailsReparatitionBudgetDto = serviceLocator.getRepartitionBudgetService()
							.splitDetailsRepartitionByTypeOfChapitre(repartitionBudgetDto);
					totaux = serviceLocator.getRepartitionBudgetService().getTotalOfDetailsRepartitionBudget(
							repartitionBudgetDto);

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
	 * Evenement de selection d'une decision de notification
	 * 
	 * @author Mounir.MESSAOUDI on : 12 jan. 2015 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		repartitionBudgetDto = (RepartitionBudgetDto) event.getObject();

		listDetailsReparatitionBudgetDto = serviceLocator.getRepartitionBudgetService()
				.splitDetailsRepartitionByTypeOfChapitre(repartitionBudgetDto);
		totaux = serviceLocator.getRepartitionBudgetService().getTotalOfDetailsRepartitionBudget(repartitionBudgetDto);

		isCrud = true;
		isView = true;
	}

	/**
	 * Enregister les donnees de signature
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:19:55
	 */
	public void saveAction() {
		try {
			repartitionBudgetDto = serviceLocator.getRepartitionBudgetService().saveDonneesSignatureBudgetRepartition(
					repartitionBudgetDto);
			CommonMessagesUtils.showSuccessUpdateMessage();
		} catch (Exception e) {
			logger.error(e.getMessage());
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * Notifier le MESRS
	 */
	public void notifyMESRSAction() {
		repartitionBudgetDto = serviceLocator.getRepartitionBudgetService().notifyMESRS(repartitionBudgetDto);
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

	public LazyDataModel<RepartitionBudgetDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<RepartitionBudgetDto> model) {
		this.model = model;
	}

	public RepartitionBudgetDto getRepartitionBudgetDto() {
		return repartitionBudgetDto;
	}

	public void setRepartitionBudgetDto(RepartitionBudgetDto repartitionBudgetDto) {
		this.repartitionBudgetDto = repartitionBudgetDto;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public RepartitionBudgetDto getRepartitionBudgetSearchDto() {
		return repartitionBudgetSearchDto;
	}

	public ListMultimap<String, DetailsReparatitionBudgetDto> getListDetailsReparatitionBudgetDto() {
		return listDetailsReparatitionBudgetDto;
	}
}