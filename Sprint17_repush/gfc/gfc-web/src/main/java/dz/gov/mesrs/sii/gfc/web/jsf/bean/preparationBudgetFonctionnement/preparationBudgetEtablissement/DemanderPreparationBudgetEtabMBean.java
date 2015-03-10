package dz.gov.mesrs.sii.gfc.web.jsf.bean.preparationBudgetFonctionnement.preparationBudgetEtablissement;

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

import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.FicheBesoinsDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

/**
 * Demander la prepration du budget au niveau de l'etablissement
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 04/01/2014 18:06:31
 */
@ManagedBean(name = "demanderPreparationBudgetEtabBean")
@ViewScoped
public class DemanderPreparationBudgetEtabMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<FicheBesoinsDto> model;

	private FicheBesoinsDto ficheBesoinsDto;
	private FicheBesoinsDto ficheBesoinsSearchDto;

	// UI
	private boolean isView;
	private boolean isCrud;

	public DemanderPreparationBudgetEtabMBean() {

	}

	@PostConstruct
	public void init() {
		initUI();
		loadSearchResults();

	}

	private void initUI() {
		ficheBesoinsSearchDto = new FicheBesoinsDto();
	}

	private void loadSearchResults() {
		// les fiches des besoins de l'etablissement en cours!
		RefEtablissementDto etablissement = new RefEtablissementDto();
		etablissement.setId(sessionBean.getCompte().getEtabId());
		ficheBesoinsSearchDto.setEtablissement(etablissement);

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
				Collection<Integer> collection = new ArrayList<Integer>();

				// "seulement" les fiches des besoins qui sont cree ou en
				// cours de preparation
				collection.add(serviceLocator
						.getSituationService()
						.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE,
								UtilConstants.SITUATION_CREEE_CODE).getId());

				collection.add(serviceLocator
						.getSituationService()
						.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE,
								UtilConstants.SITUATION_EN_COURS_PREPARATION_CODE).getId());

				searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));

				// searchMode.addSortField(sortField);
				model.setRowCount(serviceLocator.getFicheBesoinsService().countAllByExample(ficheBesoinsSearchDto,
						searchMode));

				List<FicheBesoinsDto> results = serviceLocator.getFicheBesoinsService().findAllByExample(
						ficheBesoinsSearchDto, searchMode);

				if (model.getRowCount() == 1) {
					ficheBesoinsDto = results.get(0);

					isCrud = true;
					isView = true;
				}

				return results;
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
	 * Evenement de selection d'une fiche des besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 29 déc. 2014 15:53:54
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		ficheBesoinsDto = (FicheBesoinsDto) event.getObject();
		isCrud = true;
		isView = true;
	}

	/**
	 * Enregistrer la fiche des besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 23 déc. 2014 08:51:56
	 */
	public void saveAction() {
		serviceLocator.getFicheBesoinsService().save(ficheBesoinsDto);
		CommonMessagesUtils.showSuccessUpdateMessage();
	}

	/**
	 * Notifier les structures du demmarage de la preparation de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 29 déc. 2014 16:40:28
	 */
	public void notifyAction() {
		ficheBesoinsDto = serviceLocator.getFicheBesoinsService().notifierStructuresDemPrepBudget(ficheBesoinsDto);
		CommonMessagesUtils.showSuccessUpdateMessage();
	}

	/**
	 * Valider la fiche des besoins -> activation du processus d'enregistrement
	 * les demandes budgetaires
	 * 
	 * @author Mounir.MESSAOUDI on : 22 déc. 2014 11:03:03
	 */
	public void validateAction() {
		ficheBesoinsDto = serviceLocator.getFicheBesoinsService().validerFicheBesoins(ficheBesoinsDto);
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

	public boolean isView() {
		return isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public LazyDataModel<FicheBesoinsDto> getModel() {
		return model;
	}

	public FicheBesoinsDto getFicheBesoinsSearchDto() {
		return ficheBesoinsSearchDto;
	}

	public FicheBesoinsDto getFicheBesoinsDto() {
		return ficheBesoinsDto;
	}
}