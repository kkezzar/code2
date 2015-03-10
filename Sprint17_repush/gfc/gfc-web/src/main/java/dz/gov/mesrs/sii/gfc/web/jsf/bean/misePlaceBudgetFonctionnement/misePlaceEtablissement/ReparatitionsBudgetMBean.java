package dz.gov.mesrs.sii.gfc.web.jsf.bean.misePlaceBudgetFonctionnement.misePlaceEtablissement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.google.common.collect.ListMultimap;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsReparatitionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.RepartitionBudgetDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

/**
 * Consulter les repartitions de budget
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 19/01/2015 10:06:31
 */
@ManagedBean(name = "reparatitionsBudgetBean")
@ViewScoped
public class ReparatitionsBudgetMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<RepartitionBudgetDto> model;

	private RepartitionBudgetDto repartitionBudgetDto;
	private ListMultimap<String, DetailsReparatitionBudgetDto> listDetailsReparatitionBudgetDto;

	private RepartitionBudgetDto repartitionBudgetSearchDto;

	private List<Object[]> totaux;

	// UI

	private boolean isView;

	public ReparatitionsBudgetMBean() {
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

		// TODO d'autres criteres de recherche

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

				model.setRowCount(serviceLocator.getRepartitionBudgetService().countAllByExample(
						repartitionBudgetSearchDto, searchMode));
				List<RepartitionBudgetDto> results = serviceLocator.getRepartitionBudgetService().findAllByExample(
						repartitionBudgetSearchDto, searchMode);
				if (model.getRowCount() == 1) {
					repartitionBudgetDto = results.get(0);
					totaux = serviceLocator.getRepartitionBudgetService().getTotalOfDetailsRepartitionBudget(
							repartitionBudgetDto);

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

		isView = true;
	}

	/**
	 * Annuler l'operation en cours sur le projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:20:07
	 */
	public void cancelAction() {
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

	public boolean isView() {
		return this.isView;
	}

	public ListMultimap<String, DetailsReparatitionBudgetDto> getListDetailsReparatitionBudgetDto() {
		return listDetailsReparatitionBudgetDto;
	}

	public void setListDetailsReparatitionBudgetDto(
			ListMultimap<String, DetailsReparatitionBudgetDto> listDetailsReparatitionBudgetDto) {
		this.listDetailsReparatitionBudgetDto = listDetailsReparatitionBudgetDto;
	}

	public RepartitionBudgetDto getRepartitionBudgetSearchDto() {
		return repartitionBudgetSearchDto;
	}

	public void setRepartitionBudgetSearchDto(RepartitionBudgetDto repartitionBudgetSearchDto) {
		this.repartitionBudgetSearchDto = repartitionBudgetSearchDto;
	}

}