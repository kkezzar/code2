package dz.gov.mesrs.sii.gfc.web.jsf.bean.preparationBudgetFonctionnement.preparationBudgetEtablissement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.google.common.collect.ListMultimap;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.ConsolidationBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsFicheBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.FicheBesoinsDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

/**
 * Enregistrer les propositions budgetaires au niveau etablissement
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "propositionsBudgetairesEtabBean")
@ViewScoped
public class PropositionsBudgetairesEtabMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	public final static String PREPARATION_BUDGET_BUNDLE_MSG_NAME = "preparationBudgetFonctionnementMsgs";

	private LazyDataModel<FicheBesoinsDto> model;

	private FicheBesoinsDto ficheBesoinsDto;
	private FicheBesoinsDto ficheBesoinsSearchDto;

	private ConsolidationBesoinsDto consolidationBesoinsDto;
	private ConsolidationBesoinsDto consolidationBesoinsSearchDto;

	private ListMultimap<String, DetailsFicheBesoinsDto> listDetailsFicheBesoinDto;

	private ListMultimap<String, ConsolidationBesoinsDto> listConsolidationeBesoinDto;

	private Set<ConsolidationBesoinsDto> listEditedConsolidationsBesoinsDto;

	// Historique des situations
	private List<SituationEntiteOccurrenceDto> ficheBesoinsHistory;

	private List<Object[]> totauxFicheBesoins;
	private List<Object[]> totauxConsolidationBesoins;

	// UI
	private boolean isView;
	private boolean isCrud;

	public PropositionsBudgetairesEtabMBean() {

	}

	@PostConstruct
	public void init() {
		initUI();
		loadSearchResults();

	}

	private void initUI() {
		ficheBesoinsSearchDto = new FicheBesoinsDto();
		consolidationBesoinsSearchDto = new ConsolidationBesoinsDto();
		listEditedConsolidationsBesoinsDto = new HashSet<ConsolidationBesoinsDto>();
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

				if (viewId.equals("PropositionsBudgetairesEtabEdit")) {
					// seulement les fiches des besoins cree ou en cours de
					// preparation, ou consolidee

					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE,
									UtilConstants.SITUATION_EN_COURS_PREPARATION_ENREG_DEMANDES_BUDG_CODE).getId());

					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE,
									UtilConstants.SITUATION_EN_COURS_PREPARATION_ENREG_PROP_BUDG_CODE).getId());

					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE,
									UtilConstants.SITUATION_CONSOLIDE_CODE).getId());

					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));

				}

				model.setRowCount(serviceLocator.getFicheBesoinsService().countAllByExample(ficheBesoinsSearchDto,
						searchMode));
				// return
				// serviceLocator.getFicheBesoinsService().findAllByExample(ficheBesoinsSearchDto,
				// searchMode);
				List<FicheBesoinsDto> results = serviceLocator.getFicheBesoinsService().findAllByExample(
						ficheBesoinsSearchDto, searchMode);

				if (model.getRowCount() == 1) {
					ficheBesoinsDto = results.get(0);

					listDetailsFicheBesoinDto = serviceLocator.getFicheBesoinsService()
							.splitDetailsFicheBesoinsByTypeOfChapitre(ficheBesoinsDto);
					listConsolidationeBesoinDto = serviceLocator.getFicheBesoinsService()
							.splitConsolidationsBesoinsByTypeOfChapitre(ficheBesoinsDto);
					listEditedConsolidationsBesoinsDto = new HashSet<ConsolidationBesoinsDto>();

					totauxConsolidationBesoins = serviceLocator.getFicheBesoinsService()
							.getTotalOfConsolidationBesoins(ficheBesoinsDto);

					totauxFicheBesoins = serviceLocator.getFicheBesoinsService()
							.getTotalOfFicheBesoins(ficheBesoinsDto);

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
	 * Evenement de selection d'un agent comptable
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		ficheBesoinsDto = (FicheBesoinsDto) event.getObject();

		listDetailsFicheBesoinDto = serviceLocator.getFicheBesoinsService().splitDetailsFicheBesoinsByTypeOfChapitre(
				ficheBesoinsDto);
		listConsolidationeBesoinDto = serviceLocator.getFicheBesoinsService()
				.splitConsolidationsBesoinsByTypeOfChapitre(ficheBesoinsDto);
		listEditedConsolidationsBesoinsDto = new HashSet<ConsolidationBesoinsDto>();

		totauxConsolidationBesoins = serviceLocator.getFicheBesoinsService().getTotalOfConsolidationBesoins(
				ficheBesoinsDto);
		totauxFicheBesoins = serviceLocator.getFicheBesoinsService().getTotalOfFicheBesoins(ficheBesoinsDto);

		isCrud = true;
		isView = true;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Ligne modifiée avec succès, montant proposé: ",
				((ConsolidationBesoinsDto) event.getObject()).getMontantPropose().toString());

		listEditedConsolidationsBesoinsDto.add((ConsolidationBesoinsDto) event.getObject());
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Modification annulée");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Consolider la fiche des besoinb
	 * 
	 * @author Mounir.MESSAOUDI on : 25 déc. 2014 14:24:20
	 */
	public void consolidateAction() {
		ficheBesoinsDto = serviceLocator.getFicheBesoinsService().consoliderFicheBesoins(ficheBesoinsDto);

		listConsolidationeBesoinDto = serviceLocator.getFicheBesoinsService()
				.splitConsolidationsBesoinsByTypeOfChapitre(ficheBesoinsDto);
		totauxConsolidationBesoins = serviceLocator.getFicheBesoinsService().getTotalOfConsolidationBesoins(
				ficheBesoinsDto);

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle preparationBudgetBundle = facesContext.getApplication().getResourceBundle(facesContext,
				PREPARATION_BUDGET_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_INFO);
		face.setSummary(commonResourceBundle.getString("msg_success") + ": "
				+ preparationBudgetBundle.getString("msg_success_consolidation_budget"));
		FacesContext.getCurrentInstance().addMessage(null, face);
	}

	/**
	 * Transferer les montants demandes dans les montants proposes
	 * 
	 * @author Mounir.MESSAOUDI on : 23 déc. 2014 17:16:11
	 */
	public void transferAction() {
		ficheBesoinsDto = serviceLocator.getFicheBesoinsService().transfererMontantsDemProp(ficheBesoinsDto);
		totauxConsolidationBesoins = serviceLocator.getFicheBesoinsService().getTotalOfConsolidationBesoins(
				ficheBesoinsDto);
	}

	/**
	 * Enregistrer les consolidations (montants proposes) de la fiche des
	 * besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 23 déc. 2014 08:51:56
	 */
	public void saveAction() {
		if (listEditedConsolidationsBesoinsDto != null && !listEditedConsolidationsBesoinsDto.isEmpty()) {
			for (ConsolidationBesoinsDto dto : listEditedConsolidationsBesoinsDto) {
				serviceLocator.getConsolidationBesoinsService().save(dto);
			}
		}
		listEditedConsolidationsBesoinsDto = new HashSet<ConsolidationBesoinsDto>();

		totauxConsolidationBesoins = serviceLocator.getFicheBesoinsService().getTotalOfConsolidationBesoins(
				ficheBesoinsDto);
		CommonMessagesUtils.showSuccessUpdateMessage();
	}

	/**
	 * Valider les consolidations (montants proposés) detail de la fiche des
	 * besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 22 déc. 2014 11:03:03
	 */
	public void validateAction() {
		if (listEditedConsolidationsBesoinsDto != null && !listEditedConsolidationsBesoinsDto.isEmpty()) {
			for (ConsolidationBesoinsDto dto : listEditedConsolidationsBesoinsDto) {
				serviceLocator.getConsolidationBesoinsService().save(dto);
			}
		}
		listEditedConsolidationsBesoinsDto = new HashSet<ConsolidationBesoinsDto>();
		ficheBesoinsDto = serviceLocator.getFicheBesoinsService().validerConsolidationFicheBesoins(ficheBesoinsDto);
		isCrud = false;

		CommonMessagesUtils.showSuccessUpdateMessage();
	}

	public BigDecimal getTotalMontantDemande(Boolean recette_type) {
		for (Object[] x : totauxConsolidationBesoins) {
			if (recette_type == (Boolean) x[0]) {
				return (BigDecimal) x[1];
			}
		}
		return null;
	}

	public BigDecimal getTotalMontantPropose(Boolean recette_type) {
		for (Object[] x : totauxConsolidationBesoins) {
			if (recette_type == (Boolean) x[0]) {
				return (BigDecimal) x[2];
			}
		}
		return null;
	}

	public BigDecimal getTotalMontant(Boolean recette_type) {
		Integer s = null;// structureSearchDto.getId();
		for (Object[] x : totauxFicheBesoins) {
			if ((s == x[0] || s != null && s.equals(x[0])) && recette_type == (Boolean) x[1]) {
				return (BigDecimal) x[2];
			}
		}
		return null;
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public BigDecimal getTotalMontant() {
		Double tt = Math.random();
		return new BigDecimal(tt.intValue() + 10000);
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

	public List<SituationEntiteOccurrenceDto> getFicheBesoinsHistory() {
		// historique des situations
		ficheBesoinsHistory = serviceLocator.getSituationService().getEntityOccurrenceHistory(
				UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE, ficheBesoinsDto.getId());

		return ficheBesoinsHistory;
	}

	public void setFicheBesoinsHistory(List<SituationEntiteOccurrenceDto> ficheBesoinsHistory) {
		this.ficheBesoinsHistory = ficheBesoinsHistory;
	}

	public ListMultimap<String, DetailsFicheBesoinsDto> getListDetailsFicheBesoinDto() {
		return listDetailsFicheBesoinDto;
	}

	public ListMultimap<String, ConsolidationBesoinsDto> getListConsolidationeBesoinDto() {
		return listConsolidationeBesoinDto;
	}
}
