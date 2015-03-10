package dz.gov.mesrs.sii.gfc.web.jsf.bean.misePlaceBudgetFonctionnement.misePlaceEtablissement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.google.common.collect.ListMultimap;

import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.exception.ProgresAppException;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.ChapitreDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DecisionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsReparatitionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.RepartitionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.SectionDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

/**
 * Enregistrer la reparatition de budget par article
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "enregistrerReparationBudgetArticleBean")
@ViewScoped
public class EnregistrerReparationBudgetArticleMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<DecisionBudgetDto> model;

	private LazyDataModel<DetailsReparatitionBudgetDto> modelDetailsReparatitionBudgetDto;
	private DecisionBudgetDto decisionBudgetDto;

	private RepartitionBudgetDto repartitionBudgetDto;
	private DetailsReparatitionBudgetDto detailsReparatitionBudgetDto;

	private DecisionBudgetDto decisionBudgetSearchDto;

	private DetailsReparatitionBudgetDto detailsReparatitionBudgetSearchDto;

	private ListMultimap<String, DetailsReparatitionBudgetDto> listDetailsReparatitionBudgetDto;

	private List<Object[]> totaux;

	// UI

	private boolean isView;
	private boolean isCrud;

	private boolean isViewDetail;
	private boolean isCrudDetail;

	private List<SelectItem> listSections;
	private List<SelectItem> listChapitres;
	private List<SelectItem> listArticles;

	private boolean isBudgetRecette;

	public EnregistrerReparationBudgetArticleMBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		try {
			initUI();
			loadSearchResults();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initUI() {
		decisionBudgetSearchDto = new DecisionBudgetDto();
		repartitionBudgetDto = new RepartitionBudgetDto();

		detailsReparatitionBudgetDto = new DetailsReparatitionBudgetDto();
		detailsReparatitionBudgetSearchDto = new DetailsReparatitionBudgetDto();

		// listChapitres = new ArrayList<SelectItem>();
		listArticles = new ArrayList<SelectItem>();
		listChapitres = new ArrayList<SelectItem>();
		// listChapitres = findListeChapitres();
	}

	private void loadSearchResults() {
		// les fiches des besoins de l'etablissement en cours!
		RefEtablissementDto etablissement = new RefEtablissementDto();
		etablissement.setId(sessionBean.getCompte().getEtabId());
		decisionBudgetSearchDto.setEtablissement(etablissement);

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

				collection.add(serviceLocator
						.getSituationService()
						.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DECISION_NOTIFICATION,
								UtilConstants.SITUATION_SIGNEE).getId());

				searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));

				model.setRowCount(serviceLocator.getDecisionBudgetService().countAllByExample(decisionBudgetSearchDto,
						searchMode));

				List<DecisionBudgetDto> results = serviceLocator.getDecisionBudgetService().findAllByExample(
						decisionBudgetSearchDto, searchMode);
				if (model.getRowCount() == 1) {
					decisionBudgetDto = results.get(0);

					repartitionBudgetDto = new RepartitionBudgetDto();
					repartitionBudgetDto.setDecisionBudget(decisionBudgetDto);

					repartitionBudgetDto = serviceLocator.getRepartitionBudgetService().findUniqueOrNoneByExample(
							repartitionBudgetDto);
					if (repartitionBudgetDto == null) {
						// creer une nouvelle repartition
						repartitionBudgetDto = new RepartitionBudgetDto();
						repartitionBudgetDto.setDecisionBudget(decisionBudgetDto);

						RefEtablissementDto etablissement = new RefEtablissementDto();
						etablissement.setId(sessionBean.getCompte().getEtabId());
						repartitionBudgetDto.setEtablissement(etablissement);
						repartitionBudgetDto.setUniteMonetaire(decisionBudgetDto.getUniteMonetaire());
						repartitionBudgetDto = serviceLocator.getRepartitionBudgetService().addRepartitionBudget(
								repartitionBudgetDto);
						// repartitionBudgetDto =
						// serviceLocator.getRepartitionBudgetService().save(repartitionBudgetDto);
					} else {
						totaux = serviceLocator.getRepartitionBudgetService().getTotalOfDetailsRepartitionBudget(
								repartitionBudgetDto);
					}

					listDetailsReparatitionBudgetDto = serviceLocator.getRepartitionBudgetService()
							.splitDetailsRepartitionByTypeOfChapitre(repartitionBudgetDto);

					isCrud = true;
					isView = true;
				}

				return results;
			}
		};
	}

	private void loadDetailsReparationBudget() {
		detailsReparatitionBudgetSearchDto.setRepartitionBudget(repartitionBudgetDto);
		// charger le detail de la fiche des besoins
		modelDetailsReparatitionBudgetDto = new LazyDataModel<DetailsReparatitionBudgetDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(DetailsReparatitionBudgetDto detailsReparatitionBudgetDto) {
				return detailsReparatitionBudgetDto.getId();
			}

			@Override
			public List<DetailsReparatitionBudgetDto> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {

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
				modelDetailsReparatitionBudgetDto.setRowCount(serviceLocator.getDetailsReparatitionBudgetService()
						.countAllByExample(detailsReparatitionBudgetSearchDto, searchMode));
				return serviceLocator.getDetailsReparatitionBudgetService().findAllByExample(
						detailsReparatitionBudgetSearchDto, searchMode);
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
		decisionBudgetDto = (DecisionBudgetDto) event.getObject();
		repartitionBudgetDto = new RepartitionBudgetDto();
		repartitionBudgetDto.setDecisionBudget(decisionBudgetDto);
		try {
			repartitionBudgetDto = serviceLocator.getRepartitionBudgetService().findUniqueOrNoneByExample(
					repartitionBudgetDto);
			if (repartitionBudgetDto == null) {
				// creer une nouvelle repartition
				repartitionBudgetDto = new RepartitionBudgetDto();
				repartitionBudgetDto.setDecisionBudget(decisionBudgetDto);

				RefEtablissementDto etablissement = new RefEtablissementDto();
				etablissement.setId(sessionBean.getCompte().getEtabId());
				repartitionBudgetDto.setEtablissement(etablissement);
				repartitionBudgetDto.setUniteMonetaire(decisionBudgetDto.getUniteMonetaire());
				repartitionBudgetDto = serviceLocator.getRepartitionBudgetService().addRepartitionBudget(
						repartitionBudgetDto);
				// repartitionBudgetDto =
				// serviceLocator.getRepartitionBudgetService().save(repartitionBudgetDto);
			} else {
				totaux = serviceLocator.getRepartitionBudgetService().getTotalOfDetailsRepartitionBudget(
						repartitionBudgetDto);
			}

		} catch (Exception e) {
		}

		listDetailsReparatitionBudgetDto = serviceLocator.getRepartitionBudgetService()
				.splitDetailsRepartitionByTypeOfChapitre(repartitionBudgetDto);

		isCrud = true;
		isView = true;
	}

	/**
	 * Evenement de selection d'un detail de reparation de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelectDetailsReparationBudget(SelectEvent event) {
		detailsReparatitionBudgetDto = (DetailsReparatitionBudgetDto) event.getObject();

		// charger les selectMenus
		SectionDto section = detailsReparatitionBudgetDto.getChapitre().getIdSection();
		listArticles = new ArrayList<SelectItem>();
		if (section.getId() == null)
			listChapitres = new ArrayList<SelectItem>();
		else {
			RefEtablissementDto etablissement = new RefEtablissementDto();
			etablissement.setId(sessionBean.getCompte().getEtabId());
			listChapitres = findListAffectationsChapitres(etablissement, section, detailsReparatitionBudgetDto
					.getChapitre().getRecetteType());
		}

		if (listChapitres == null)
			listChapitres = new ArrayList<SelectItem>();

		ChapitreDto chapitre = detailsReparatitionBudgetDto.getChapitre();
		listArticles = findListeArticle(chapitre);
		if (listArticles == null)
			listArticles = new ArrayList<SelectItem>();
		isCrudDetail = true;
	}

	/**
	 * Handle changement du section
	 * 
	 * @author Mounir.MESSAOUDI on : 2 dec. 2014 16:38:44
	 * @param event
	 * @throws Exception
	 */
	public void handleSectionsSelectOneMenuChange(AjaxBehaviorEvent event) {
		SectionDto section = detailsReparatitionBudgetDto.getChapitre().getIdSection();
		listArticles = new ArrayList<SelectItem>();
		if (section.getId() == null)
			listChapitres = new ArrayList<SelectItem>();
		else {
			RefEtablissementDto etablissement = new RefEtablissementDto();
			etablissement.setId(sessionBean.getCompte().getEtabId());
			// listChapitres = findListeChapitres(section);
			listChapitres = findListAffectationsChapitres(etablissement, section, isBudgetRecette);
		}

		if (listChapitres == null)
			listChapitres = new ArrayList<SelectItem>();

		listArticles = new ArrayList<SelectItem>();

	}

	/**
	 * Handle changement du chapitre
	 * 
	 * @author Mounir.MESSAOUDI on : 2 dec. 2014 16:38:44
	 * @param event
	 */
	public void handleChapitresSelectOneMenuChange(AjaxBehaviorEvent event) {
		ChapitreDto chapitre = detailsReparatitionBudgetDto.getChapitre();
		listArticles = findListeArticle(chapitre);
		if (listArticles == null)
			listArticles = new ArrayList<SelectItem>();
	}

	public void addBudgetRecetteAction() {
		addBudget();
		isBudgetRecette = true;
		// isCrudDetail = true;
	}

	public void addBudgetDepenseAction() {
		addBudget();
		isBudgetRecette = false;
		// isCrudDetail = true;
	}

	private void addBudget() {
		detailsReparatitionBudgetDto = new DetailsReparatitionBudgetDto();
		detailsReparatitionBudgetDto.setRepartitionBudget(repartitionBudgetDto);

		listChapitres = new ArrayList<SelectItem>();
		listArticles = new ArrayList<SelectItem>();
	}

	/**
	 * Valider la repartition de budget par article
	 * 
	 * @throws ProgresAppException
	 */
	public void validateAction() throws ProgresAppException {
		if (repartitionBudgetDto != null) {
			serviceLocator.getRepartitionBudgetService().validateRepartitionBudgetArticle(repartitionBudgetDto);

			isCrud = false;
			CommonMessagesUtils.showSuccessValidationMessage();
		} else
			throw new ProgresAppException();
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

	public void saveDetailRepBudgetAction() throws ProgresAppException {
		if (detailsReparatitionBudgetDto != null) {
			if (detailsReparatitionBudgetDto.getArticle().getId() == null)
				detailsReparatitionBudgetDto.setArticle(null);

			if (detailsReparatitionBudgetDto.getId() == null) {
				detailsReparatitionBudgetDto = serviceLocator.getDetailsReparatitionBudgetService().save(
						detailsReparatitionBudgetDto);

				if (detailsReparatitionBudgetDto.getChapitre().isRecetteType()) {
					listDetailsReparatitionBudgetDto.put("true", detailsReparatitionBudgetDto);
				} else {
					listDetailsReparatitionBudgetDto.put("false", detailsReparatitionBudgetDto);
				}

				CommonMessagesUtils.showSuccessSaveMessage();
			} else {
				detailsReparatitionBudgetDto = serviceLocator.getDetailsReparatitionBudgetService().save(
						detailsReparatitionBudgetDto);
				CommonMessagesUtils.showSuccessUpdateMessage();
			}

			totaux = serviceLocator.getRepartitionBudgetService().getTotalOfDetailsRepartitionBudget(
					repartitionBudgetDto);

			detailsReparatitionBudgetDto = new DetailsReparatitionBudgetDto();

			RequestContext.getCurrentInstance().execute("PF('gererRepBudgetArticleWidget').hide()");
			// isCrudDetail = false;

		} else
			throw new ProgresAppException();
	}

	public void deleteDetailRepBudgetAction() throws ProgresAppException {
		if (detailsReparatitionBudgetDto != null) {
			serviceLocator.getDetailsReparatitionBudgetService().remove(detailsReparatitionBudgetDto);
			listDetailsReparatitionBudgetDto.remove(detailsReparatitionBudgetDto.getChapitre().getRecetteType()
					.toString(), detailsReparatitionBudgetDto);

			totaux = serviceLocator.getRepartitionBudgetService().getTotalOfDetailsRepartitionBudget(
					repartitionBudgetDto);

			CommonMessagesUtils.showSuccessDeleteMessage();
			isCrudDetail = false;
		} else
			throw new ProgresAppException();
	}

	public void cancelRepBudgetAction() {

	}

	public BigDecimal getTotalMontant(Boolean recette_type) {
		if (totaux != null) {
			for (Object[] x : totaux) {
				if (recette_type == (Boolean) x[0]) {
					return (BigDecimal) x[1];
				}
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

	public boolean isViewDetail() {
		return isViewDetail;
	}

	public boolean isCrudDetail() {
		return isCrudDetail;
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

	public RepartitionBudgetDto getRepartitionBudgetDto() {
		return repartitionBudgetDto;
	}

	public void setRepartitionBudgetDto(RepartitionBudgetDto repartitionBudgetDto) {
		this.repartitionBudgetDto = repartitionBudgetDto;
	}

	public DetailsReparatitionBudgetDto getDetailsReparatitionBudgetDto() {
		return detailsReparatitionBudgetDto;
	}

	public void setDetailsReparatitionBudgetDto(DetailsReparatitionBudgetDto detailsReparatitionBudgetDto) {
		this.detailsReparatitionBudgetDto = detailsReparatitionBudgetDto;
	}

	public List<SelectItem> getListSections() {
		if (listSections == null) {
			listSections = findListeSections();
		}
		return listSections;
	}

	public List<SelectItem> getListChapitres() {
		// if (listChapitres == null) {
		// listChapitres = findListeChapitres();
		// }
		return listChapitres;
	}

	public void setListChapitres(List<SelectItem> listChapitres) {
		this.listChapitres = listChapitres;
	}

	public List<SelectItem> getListArticles() {
		return listArticles;
	}

	public void setListArticles(List<SelectItem> listArticles) {
		this.listArticles = listArticles;
	}

	public DetailsReparatitionBudgetDto getDetailsReparatitionBudgetSearchDto() {
		return detailsReparatitionBudgetSearchDto;
	}

	public void setDetailsReparatitionBudgetSearchDto(DetailsReparatitionBudgetDto detailsReparatitionBudgetSearchDto) {
		this.detailsReparatitionBudgetSearchDto = detailsReparatitionBudgetSearchDto;
	}

	public LazyDataModel<DetailsReparatitionBudgetDto> getModelDetailsReparatitionBudgetDto() {
		return modelDetailsReparatitionBudgetDto;
	}

	public void setModelDetailsReparatitionBudgetDto(
			LazyDataModel<DetailsReparatitionBudgetDto> modelDetailsReparatitionBudgetDto) {
		this.modelDetailsReparatitionBudgetDto = modelDetailsReparatitionBudgetDto;
	}

	public ListMultimap<String, DetailsReparatitionBudgetDto> getListDetailsReparatitionBudgetDto() {
		return listDetailsReparatitionBudgetDto;
	}

}