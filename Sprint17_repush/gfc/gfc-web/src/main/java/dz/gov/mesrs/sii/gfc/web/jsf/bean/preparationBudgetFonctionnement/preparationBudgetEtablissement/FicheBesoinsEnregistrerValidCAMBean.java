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
 * Enregistre la date de validation du budget consolide de l'etablissement par
 * le conseil d'administration.
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 05/01/2015 18:06:31
 */
@ManagedBean(name = "ficheBesoinsEnregistrerValidCABean")
@ViewScoped
public class FicheBesoinsEnregistrerValidCAMBean extends BaseBean {

	public final static String PREPARATION_BUDGET_BUNDLE_MSG_NAME = "preparationBudgetFonctionnementMsgs";

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

	public FicheBesoinsEnregistrerValidCAMBean() {
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
				collection.add(serviceLocator
						.getSituationService()
						.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE,
								UtilConstants.SITUATION_A_VALIDER_CODE).getId());

				searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));

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

	public void searchAction() {
		loadSearchResults();
	}

	/**
	 * Evenement de selection d'un agent comptable
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		ficheBesoinsDto = (FicheBesoinsDto) event.getObject();

		isCrud = true;
		isView = true;
	}

	/**
	 * Enregister la date de validation du CA
	 * 
	 * @author Mounir.MESSAOUDI on : 16 Jan. 2015 17:19:37
	 */
	public void saveAction() {
		ficheBesoinsDto = serviceLocator.getFicheBesoinsService().save(ficheBesoinsDto);
		CommonMessagesUtils.showSuccessUpdateMessage();
	}

	/**
	 * Valider l'enregistrement de la validation du CA
	 * 
	 * @author Mounir.MESSAOUDI on : 16 Jan. 2015 17:19:37
	 */
	public void validateAction() {
		ficheBesoinsDto = serviceLocator.getFicheBesoinsService().validerEnregistrementValidCA(ficheBesoinsDto);
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

	public boolean isView() {
		return isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public LazyDataModel<FicheBesoinsDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<FicheBesoinsDto> model) {
		this.model = model;
	}

	public FicheBesoinsDto getFicheBesoinsDto() {
		return ficheBesoinsDto;
	}

	public void setFicheBesoinsDto(FicheBesoinsDto ficheBesoinsDto) {
		this.ficheBesoinsDto = ficheBesoinsDto;
	}

}
