package dz.gov.mesrs.sii.gfc.web.jsf.bean.parametrageBudget.affectationNomenclaturesBudget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.validation.ConstraintViolationException;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.gfc.business.model.dto.AffectationEtabChapitreArticleDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ChapitreDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;

/**
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "affectationNomenclaturesBudgetBean")
@ViewScoped
public class AffectationNomenclaturesBudgetBean extends BaseBean {

	/**
	 * @author Mounir.MESSAOUDI on : 26 nov. 2014 15:04:24
	 */
	private static final long serialVersionUID = 1L;

	private ResourceBundle paramettrageBudgetBundle;

	private LazyDataModel<AffectationEtabChapitreArticleDto> model;

	private AffectationEtabChapitreArticleDto affectationEtabChapitreArticleSearchDto;
	private AffectationEtabChapitreArticleDto affectationEtabChapitreArticleDto;

	// UI
	private Integer searchByIdEtablissement;
	private Integer searchByIdChapitre;
	private Integer searchByIdArticle;

	private boolean isView;
	private boolean isCrud;

	private List<SelectItem> listChapitres;
	private List<SelectItem> listArticles;
	private List<SelectItem> listEtablissements;

	public AffectationNomenclaturesBudgetBean() {
	}

	@PostConstruct
	public void init() {
		try {
			initUI();

			listChapitres = findListeChapitres();
			listArticles = new ArrayList<SelectItem>();
			listEtablissements = findListeEtablissements();

			loadSearchResults();
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	private void initUI() {
		affectationEtabChapitreArticleSearchDto = new AffectationEtabChapitreArticleDto();
	}

	/**
	 * Charger les resultats de la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 26 nov. 2014 14:28:01
	 */
	private void loadSearchResults() {
		model = new LazyDataModel<AffectationEtabChapitreArticleDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public List<AffectationEtabChapitreArticleDto> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {
				Boolean descending = null;

				// programmeNationalDto.setIntituleFr(searchByKeywords);
				// programmeNationalDto.setIntituleAr(searchByKeywords);
				// affectationEtabChapitreArticleDto.setRefEtablissement(refEtablissement);

				// SearchMode searchMode = new SearchMode(pageSize, first);
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				// searchMode.addSortField(sortField);
				// if (sortOrder != null) {
				// if (sortOrder.equals(SortOrder.DESCENDING)) {
				// descending = Boolean.TRUE;
				// } else {
				// descending = Boolean.FALSE;
				// }
				//
				// }
				model.setRowCount(serviceLocator.getAffectationEtabChapitreArticleService().countAllByExample(
						affectationEtabChapitreArticleSearchDto, searchMode));
				return serviceLocator.getAffectationEtabChapitreArticleService().findAllByExample(
						affectationEtabChapitreArticleSearchDto, searchMode);
			}
		};
	}

	public LazyDataModel<AffectationEtabChapitreArticleDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<AffectationEtabChapitreArticleDto> model) {
		this.model = model;
	}

	/**
	 * Evenement de selection d'un dossier etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			affectationEtabChapitreArticleDto = (AffectationEtabChapitreArticleDto) event.getObject();
			isCrud = true;
			isView = true;
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Handle changement du chapitre
	 * 
	 * @author Mounir.MESSAOUDI on : 2 déc. 2014 16:38:44
	 * @param event
	 * @throws Exception
	 */
	public void handleChapitresSelectOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		ChapitreDto chapitre = affectationEtabChapitreArticleDto.getChapitre();
		listArticles = findListeArticle(chapitre);
	}

	/**
	 * Handle changement du chapitre dans la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 2 déc. 2014 16:38:44
	 * @param event
	 * @throws Exception
	 */
	public void handleSearchChapitresSelectOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		listArticles = findListeArticle(affectationEtabChapitreArticleSearchDto.getChapitre());
		loadSearchResults();
	}

	/**
	 * Handle changement de l'article dans la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 2 déc. 2014 16:38:44
	 * @param event
	 * @throws Exception
	 */
	public void handleSearchArticlesSelectOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		loadSearchResults();
	}

	/**
	 * Handle changement de l'etablissement dans la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 2 déc. 2014 16:38:44
	 * @param event
	 * @throws Exception
	 */
	public void handleSearchEtablissementsSelectOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		loadSearchResults();
	}

	/**
	 * Declancher l'action d'ajout
	 * 
	 * @author Mounir.MESSAOUDI on : 26 nov. 2014 14:19:20
	 */
	public void addAction() {
		affectationEtabChapitreArticleDto = new AffectationEtabChapitreArticleDto();
		isCrud = true;
	}

	/**
	 * Declancher l'action de la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 26 nov. 2014 14:27:38
	 */
	public void searchAction() {
		loadSearchResults();
	}

	/**
	 * Enregistrer l'affectation
	 * 
	 * @author Mounir.MESSAOUDI on : 26 nov. 2014 14:26:51
	 */
	public void saveAction() {
		try {
			if (affectationEtabChapitreArticleDto != null) {
				// fix transient instance with non-mandatory @ManyToOne
				if (affectationEtabChapitreArticleDto.getArticle().getId() == null
						|| affectationEtabChapitreArticleDto.getArticle().getId() == 0) {
					affectationEtabChapitreArticleDto.setArticle(null);
				}

				affectationEtabChapitreArticleDto = serviceLocator.getAffectationEtabChapitreArticleService().save(
						affectationEtabChapitreArticleDto);
				if (affectationEtabChapitreArticleDto.getId() == null) {
					CommonMessagesUtils.showSuccessSaveMessage();
				} else {
					CommonMessagesUtils.showSuccessUpdateMessage();
				}
			}
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
		loadSearchResults();
	}

	/**
	 * Supprimer l'affectation
	 * 
	 * @author Mounir.MESSAOUDI on : 8 d�c. 2014 14:49:58
	 */
	public void deleteAction() {
		try {
			serviceLocator.getAffectationEtabChapitreArticleService().remove(affectationEtabChapitreArticleDto);
		} catch (ConstraintViolationException e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Action du boutton Retour
	 * 
	 * @author Mounir.MESSAOUDI on : 26 nov. 2014 14:27:14
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

	public Integer getSearchByIdEtablissement() {
		return searchByIdEtablissement;
	}

	public void setSearchByIdEtablissement(Integer searchByIdEtablissement) {
		this.searchByIdEtablissement = searchByIdEtablissement;
	}

	public Integer getSearchByIdChapitre() {
		return searchByIdChapitre;
	}

	public void setSearchByIdChapitre(Integer searchByIdChapitre) {
		this.searchByIdChapitre = searchByIdChapitre;
	}

	public Integer getSearchByIdArticle() {
		return searchByIdArticle;
	}

	public void setSearchByIdArticle(Integer searchByIdArticle) {
		this.searchByIdArticle = searchByIdArticle;
	}

	public AffectationEtabChapitreArticleDto getAffectationEtabChapitreArticleDto() {
		return affectationEtabChapitreArticleDto;
	}

	public void setAffectationEtabChapitreArticleDto(AffectationEtabChapitreArticleDto affectationEtabChapitreArticleDto) {
		this.affectationEtabChapitreArticleDto = affectationEtabChapitreArticleDto;
	}

	public List<SelectItem> getListChapitres() {
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

	public List<SelectItem> getListEtablissements() {
		return listEtablissements;
	}

	public void setListEtablissements(List<SelectItem> listEtablissements) {
		this.listEtablissements = listEtablissements;
	}

	public AffectationEtabChapitreArticleDto getAffectationEtabChapitreArticleSearchDto() {
		return affectationEtabChapitreArticleSearchDto;
	}

	public void setAffectationEtabChapitreArticleSearchDto(
			AffectationEtabChapitreArticleDto affectationEtabChapitreArticleSearchDto) {
		this.affectationEtabChapitreArticleSearchDto = affectationEtabChapitreArticleSearchDto;
	}

}
