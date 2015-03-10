package dz.gov.mesrs.sii.gfc.web.jsf.bean.preparationBudgetFonctionnement.preparationBudgetCentral;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.dao.DataIntegrityViolationException;

import com.google.common.collect.ListMultimap;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.ChapitreDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ConsolidationBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsFicheBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ExerciceBudgetaireDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.FicheBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ProjetBudgetDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.GFCConstantBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * Gere les projets de budget
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "projetsBudgetsBean")
@ViewScoped
public class ProjetsBudgetsMBean extends BaseBean {

	public final static String PREPARATION_BUDGET_BUNDLE_MSG_NAME = "preparationBudgetFonctionnementMsgs";

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<ProjetBudgetDto> model;
	private ProjetBudgetDto projetBudgetDto;
	private ProjetBudgetDto projetBudgetSearchDto;

	private FicheBesoinsDto ficheBesoinsDto;

	// UI
	private boolean isView;
	private boolean isCrud;

	private boolean isViewDetailFicheBesoins;

	private List<SelectItem> listExercicesBudgetaires;
	private List<SelectItem> listTypesBudget;
	private List<SelectItem> listUnitesMonetaires;

	private List<SelectItem> listProjetsBudgetsParent;

	private LazyDataModel<DetailsFicheBesoinsDto> modelDemRecetteDetailsFicheBesoins;
	private LazyDataModel<DetailsFicheBesoinsDto> modelDemDepenseDetailsFicheBesoins;

	private ListMultimap<String, ConsolidationBesoinsDto> listConsolidationeBesoinDto;

	private List<Object[]> totauxDetailsProjetBudget;
	private List<Object[]> totauxFicheBesoins;
	private List<Object[]> totauxConsolidationBesoins;

	private DetailsFicheBesoinsDto detailsFicheBesoinsSearchDto;
	private DetailsFicheBesoinsDto detailsFichBesSearchRecettesDto;
	private DetailsFicheBesoinsDto detailsFichBesSearchDepensesDto;

	private List<SelectItem> listStructures;
	private RefStructureDto structureSearchDto;

	// Search
	public String keyWords;

	// Historique des situations
	private List<SituationEntiteOccurrenceDto> projetBudgetHistory;

	public ProjetsBudgetsMBean() {
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
		projetBudgetSearchDto = new ProjetBudgetDto();
		ficheBesoinsDto = new FicheBesoinsDto();

		// les exercices budgetaires valides seulement
		listExercicesBudgetaires = new ArrayList<SelectItem>();
		SituationEntiteDto situationValidee = serviceLocator.getSituationService()
				.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_EXERCICE_BUDGETAIRE_CODE,
						UtilConstants.SITUATION_VALIDEE_CODE);
		ExerciceBudgetaireDto exBudgDto = new ExerciceBudgetaireDto();
		exBudgDto.setSituation(situationValidee);
		List<ExerciceBudgetaireDto> eb = serviceLocator.getExerciceBudgetaireService().findAllByExample(exBudgDto);
		if (eb != null && !eb.isEmpty()) {
			for (ExerciceBudgetaireDto exerciceBudgetaireDto : eb) {
				listExercicesBudgetaires.add(new SelectItem(exerciceBudgetaireDto.getId(), exerciceBudgetaireDto
						.getIntituleFr()));
			}
		}

		listTypesBudget = findNomenclatureList(GFCConstantBean.CODE_TYPE_BUDGET);
		listUnitesMonetaires = findNomenclatureList(GFCConstantBean.CODE_TYPE_UNITE_MONETAIRE);

		listProjetsBudgetsParent = new ArrayList<SelectItem>();
		List<ProjetBudgetDto> pb = serviceLocator.getProjetBudgetService().findAll();
		if (pb != null && !pb.isEmpty()) {
			for (ProjetBudgetDto projetBudgetDto : pb) {
				StringBuffer s = new StringBuffer();
				s.append(projetBudgetDto.getIdentite());
				s.append(" - ");
				s.append(projetBudgetDto.getDescription());
				listProjetsBudgetsParent.add(new SelectItem(projetBudgetDto.getId(), s.toString()));
			}
		}

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
				SearchMode searchMode = new SearchMode(pageSize, first);
				Collection<Integer> collection = new ArrayList<Integer>();
				if (viewId.equals("ProjetsBudgetsEdit")) {
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE,
									UtilConstants.SITUATION_CREEE_CODE).getId());
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE,
									UtilConstants.SITUATION_NOTIFIEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));
				}
				// filtrer par situation
				model.setRowCount(serviceLocator.getProjetBudgetService().countAllByExample(projetBudgetSearchDto,
						searchMode));

				List<ProjetBudgetDto> result = serviceLocator.getProjetBudgetService().findAllByExample(
						projetBudgetSearchDto, searchMode);

				if (model.getRowCount() == 1) {
					isCrud = true;
					isView = true;
					projetBudgetDto = result.get(0);
					if (projetBudgetDto.getProjetBudget() == null)
						projetBudgetDto.setProjetBudget(new ProjetBudgetDto());

					if (viewId.equals("ProjetsBudgets")) {
						totauxDetailsProjetBudget = serviceLocator.getProjetBudgetService()
								.getTotalOfDetailsProjetBudget(projetBudgetDto);
					}
				}

				return result;

			}
		};
	}

	/**
	 * Evenement de selection d'un agent comptable
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		projetBudgetDto = (ProjetBudgetDto) event.getObject();

		if (projetBudgetDto.getProjetBudget() == null)
			projetBudgetDto.setProjetBudget(new ProjetBudgetDto());

		if (viewId.equals("ProjetsBudgets")) {
			totauxDetailsProjetBudget = serviceLocator.getProjetBudgetService().getTotalOfDetailsProjetBudget(
					projetBudgetDto);
		}

		isCrud = true;
		isView = true;
	}

	public void onChangeTypeProgrammeSelect(AjaxBehaviorEvent event) {
		Integer id = projetBudgetDto.getTypeBudget().getId();
		NomenclatureDto n = serviceLocator.getNomenclatureService().findById(id);
		projetBudgetDto.setTypeBudget(n);
	}

	/**
	 * Evenement de selection d'un agent comptable
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelectFicheBesoins(SelectEvent event) {
		ficheBesoinsDto = (FicheBesoinsDto) event.getObject();

		structureSearchDto = new RefStructureDto();
		loadDemRecetteDetailsFicheBesoins();
		loadDemDepenseDetailsFicheBesoins();

		listConsolidationeBesoinDto = serviceLocator.getFicheBesoinsService()
				.splitConsolidationsBesoinsByTypeOfChapitre(ficheBesoinsDto);

		totauxConsolidationBesoins = serviceLocator.getFicheBesoinsService().getTotalOfConsolidationBesoins(
				ficheBesoinsDto);
		totauxFicheBesoins = serviceLocator.getFicheBesoinsService().getTotalOfFicheBesoins(ficheBesoinsDto);

		isView = true;
		isViewDetailFicheBesoins = true;

		listStructures = null;
	}

	private void loadDemRecetteDetailsFicheBesoins() {
		detailsFichBesSearchRecettesDto = new DetailsFicheBesoinsDto();

		detailsFichBesSearchRecettesDto.setStructure(structureSearchDto);
		detailsFichBesSearchRecettesDto.setFicheBesoins(ficheBesoinsDto);
		// demandes budgetaire en recette!
		ChapitreDto chapitre = new ChapitreDto();
		chapitre.setRecetteType(Boolean.TRUE);
		detailsFichBesSearchRecettesDto.setChapitre(chapitre);

		// charger le detail de la fiche des besoins
		modelDemRecetteDetailsFicheBesoins = new LazyDataModel<DetailsFicheBesoinsDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(DetailsFicheBesoinsDto ficheBesoinsDto) {
				return ficheBesoinsDto.getId();
			}

			@Override
			public List<DetailsFicheBesoinsDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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
				modelDemRecetteDetailsFicheBesoins.setRowCount(serviceLocator.getDetailsFicheBesoinsService()
						.countAllByExample(detailsFichBesSearchRecettesDto));
				return serviceLocator.getDetailsFicheBesoinsService().findAllByExample(detailsFichBesSearchRecettesDto,
						searchMode);
			}
		};

	}

	private void loadDemDepenseDetailsFicheBesoins() {
		detailsFichBesSearchDepensesDto = new DetailsFicheBesoinsDto();

		detailsFichBesSearchDepensesDto.setStructure(structureSearchDto);
		detailsFichBesSearchDepensesDto.setFicheBesoins(ficheBesoinsDto);
		// demandes budgetaire en recette!
		ChapitreDto chapitre = new ChapitreDto();
		chapitre.setRecetteType(Boolean.FALSE);
		detailsFichBesSearchDepensesDto.setChapitre(chapitre);

		// charger le detail de la fiche des besoins
		modelDemDepenseDetailsFicheBesoins = new LazyDataModel<DetailsFicheBesoinsDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(DetailsFicheBesoinsDto ficheBesoinsDto) {
				return ficheBesoinsDto.getId();
			}

			@Override
			public List<DetailsFicheBesoinsDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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
				modelDemDepenseDetailsFicheBesoins.setRowCount(serviceLocator.getDetailsFicheBesoinsService()
						.countAllByExample(detailsFichBesSearchDepensesDto));
				return serviceLocator.getDetailsFicheBesoinsService().findAllByExample(detailsFichBesSearchDepensesDto,
						searchMode);
			}
		};
	}

	/**
	 * Ajouter un nouveau projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 18:08:47
	 */
	public void addAction() {
		projetBudgetDto = new ProjetBudgetDto();
		projetBudgetDto.setProjetBudget(new ProjetBudgetDto());
		isCrud = true;

	}

	/**
	 * Enregistrer le projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:19:55
	 */
	public void saveAction() {
		if (projetBudgetDto != null) {

			if (projetBudgetDto.getProjetBudget().getId() == null) {
				projetBudgetDto.setProjetBudget(null);
			}

			if (projetBudgetDto.getSituation().getId() == 0) {
				projetBudgetDto = serviceLocator.getProjetBudgetService().creerNoveauProjetBudget(projetBudgetDto);
				CommonMessagesUtils.showSuccessSaveMessage();
			} else {
				projetBudgetDto = serviceLocator.getProjetBudgetService().save(projetBudgetDto);
				CommonMessagesUtils.showSuccessUpdateMessage();
			}

			if (projetBudgetDto.getProjetBudget() == null) {
				projetBudgetDto.setProjetBudget(new ProjetBudgetDto());
			}
		}

		if (projetBudgetDto.getProjetBudget() == null) {
			projetBudgetDto.setProjetBudget(new ProjetBudgetDto());
		}
	}

	/**
	 * Valider le projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:19:37
	 */
	public void validateAction() {
		projetBudgetDto = serviceLocator.getProjetBudgetService().validerProjetBudget(projetBudgetDto);
		CommonMessagesUtils.showSuccessValidationMessage();
	}

	/**
	 * Notifier les etablissements
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:24:55
	 */
	public void notifyEtablissementsAction() {
		projetBudgetDto = serviceLocator.getProjetBudgetService().notifierEtablissements(projetBudgetDto);
		isCrud = false;

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle preparationBudgetBundle = facesContext.getApplication().getResourceBundle(facesContext,
				PREPARATION_BUDGET_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_INFO);
		face.setSummary(commonResourceBundle.getString("msg_success") + ": "
				+ preparationBudgetBundle.getString("msg_success_demande_prepration_budget"));
		FacesContext.getCurrentInstance().addMessage(null, face);

	}

	/**
	 * Demander la preparation du budget
	 * 
	 * @author Mounir.MESSAOUDI on : 22 déc. 2014 09:52:36 TODO to be removed
	 */
	public void demanderPreparationBudgetAction() {
		projetBudgetDto = serviceLocator.getProjetBudgetService().demanderPreparationBudget(projetBudgetDto);
		isCrud = false;

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle preparationBudgetBundle = facesContext.getApplication().getResourceBundle(facesContext,
				PREPARATION_BUDGET_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_INFO);
		face.setSummary(commonResourceBundle.getString("msg_success") + ": "
				+ preparationBudgetBundle.getString("msg_success_demande_prepration_budget"));
		FacesContext.getCurrentInstance().addMessage(null, face);
	}

	/**
	 * Supprimer le projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:19:30
	 */
	public void deleteAction() {
		try {
			if (projetBudgetDto != null) {
				serviceLocator.getProjetBudgetService().remove(projetBudgetDto);
				CommonMessagesUtils.showSuccessDeleteMessage();
				isCrud = false;
			}
		} catch (DataIntegrityViolationException e) {
			CommonMessagesUtils.showConstraintViolationErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Cloturer le projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:19:24
	 */
	public void closingAction() {

	}

	/**
	 * Annuler l'operation encours sur le projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:20:07
	 */
	public void cancelAction() {
		isCrud = false;
		isView = false;
		isViewDetailFicheBesoins = false;
	}

	public boolean isView() {
		return isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public boolean isViewDetailFicheBesoins() {
		return isViewDetailFicheBesoins;
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

	public List<SelectItem> getListExercicesBudgetaires() {
		return listExercicesBudgetaires;
	}

	public List<SelectItem> getListUnitesMonetaires() {
		return listUnitesMonetaires;
	}

	public void setListUnitesMonetaires(List<SelectItem> listUnitesMonetaires) {
		this.listUnitesMonetaires = listUnitesMonetaires;
	}

	public void setListExercicesBudgetaires(List<SelectItem> listExercicesBudgetaires) {
		this.listExercicesBudgetaires = listExercicesBudgetaires;
	}

	public List<SelectItem> getListTypesBudget() {
		return listTypesBudget;
	}

	public ProjetBudgetDto getProjetBudgetSearchDto() {
		return projetBudgetSearchDto;
	}

	public void setProjetBudgetSearchDto(ProjetBudgetDto projetBudgetSearchDto) {
		this.projetBudgetSearchDto = projetBudgetSearchDto;
	}

	public void setListTypesBudget(List<SelectItem> listTypesBudget) {
		this.listTypesBudget = listTypesBudget;
	}

	public List<SelectItem> getListProjetsBudgetsParent() {
		return listProjetsBudgetsParent;
	}

	public void setListProjetsBudgetsParent(List<SelectItem> listProjetsBudgetsParent) {
		this.listProjetsBudgetsParent = listProjetsBudgetsParent;
	}

	public FicheBesoinsDto getFicheBesoinsDto() {
		return ficheBesoinsDto;
	}

	public void setFicheBesoinsDto(FicheBesoinsDto ficheBesoinsDto) {
		this.ficheBesoinsDto = ficheBesoinsDto;
	}

	public List<SituationEntiteOccurrenceDto> getProjetBudgetHistory() {
		// historique des situations
		projetBudgetHistory = serviceLocator.getSituationService().getEntityOccurrenceHistory(
				UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE, projetBudgetDto.getId());

		return projetBudgetHistory;
	}

	public void setProjetBudgetHistory(List<SituationEntiteOccurrenceDto> projetBudgetHistory) {
		this.projetBudgetHistory = projetBudgetHistory;
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
		Integer s = structureSearchDto.getId();
		for (Object[] x : totauxFicheBesoins) {
			if ((s == x[0] || s != null && s.equals(x[0])) && recette_type == (Boolean) x[1]) {
				return (BigDecimal) x[2];
			}
		}
		return null;
	}

	public BigDecimal getTotalMontantProposeDetailProjetBudget(Boolean recette_type) {
		for (Object[] x : totauxDetailsProjetBudget) {
			if (recette_type == (Boolean) x[0]) {
				return (BigDecimal) x[1];
			}
		}
		return null;
	}

	public BigDecimal getTotalMontantFinalDetailProjetBudget(Boolean recette_type) {
		for (Object[] x : totauxDetailsProjetBudget) {
			if (recette_type == (Boolean) x[0]) {
				return (BigDecimal) x[2];
			}
		}
		return null;
	}

	/**
	 * Renvoi la date du dernier jour de l'annee N-1
	 * 
	 * @return
	 */
	public final Date getDateFirstDayOfCurrYear() {
		Calendar cal = Calendar.getInstance();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		cal.set(Calendar.YEAR, year - 1);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		return cal.getTime();
	}

	// ----- Rercherche
	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	/**
	 * Lancer la recherche
	 */
	public void searchAction() {
		loadSearchResults();
	}

	/**
	 * Lancer la recherche avancee
	 */
	public void advancedSearchAction() {

	}

	public ListMultimap<String, ConsolidationBesoinsDto> getListConsolidationeBesoinDto() {
		return listConsolidationeBesoinDto;
	}

	public LazyDataModel<DetailsFicheBesoinsDto> getModelDemRecetteDetailsFicheBesoins() {
		return modelDemRecetteDetailsFicheBesoins;
	}

	public LazyDataModel<DetailsFicheBesoinsDto> getModelDemDepenseDetailsFicheBesoins() {
		return modelDemDepenseDetailsFicheBesoins;
	}

	public List<SelectItem> getListStructures() {
		if (listStructures == null) {
			// la liste des strcutures de l'etablissement de la fiche des
			// besoins
			RefEtablissementDto refEtablissementDto = ficheBesoinsDto.getEtablissement();
			listStructures = findListeStructure(refEtablissementDto);
		}
		return listStructures;
	}

	public RefStructureDto getStructureSearchDto() {
		return structureSearchDto;
	}
}