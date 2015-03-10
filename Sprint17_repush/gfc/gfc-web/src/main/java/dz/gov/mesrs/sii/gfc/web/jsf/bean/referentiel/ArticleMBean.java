package dz.gov.mesrs.sii.gfc.web.jsf.bean.referentiel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.ArticleDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ChapitreDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;

/**
 * @author Salem Chouikh (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "articleBean")
@ViewScoped
public class ArticleMBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<ArticleDto> modelArticle;
	private ArticleDto articleDto;
	private String searchKeyWord;

	private List<SelectItem> listChapitres = new ArrayList<SelectItem>();

	// UI
	private boolean isView;
	private boolean isCrud;

	public ArticleMBean() {
	}

	@PostConstruct
	public void init() {
		searchKeyWord = "";
		loadSearchResults();

	}

	/**
	 * Charger la rechcerche
	 * 
	 * @author Mounir.MESSAOUDI on : 26 nov. 2014 14:28:01
	 */
	public void loadSearchResults() {
		modelArticle = new LazyDataModel<ArticleDto>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3174194836578361532L;
			ArticleDto articleDto = new ArticleDto();

			@Override
			public List<ArticleDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				Boolean descending = null;

				// articleDto.setIntituleFr(searchKeyWord);
				// articleDto.setIntituleAr(searchKeyWord);

				SearchMode searchMode = new SearchMode(pageSize, first);
				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}

				}
				modelArticle.setRowCount(serviceLocator.getArticleService().countByKeyWord(searchKeyWord));

				return serviceLocator.getArticleService().findAllByKeyWord(searchKeyWord, searchMode);
			}
		};
	}

	public LazyDataModel<ArticleDto> getModelArticle() {
		return modelArticle;
	}

	public void setModelArticle(LazyDataModel<ArticleDto> modelArticle) {
		this.modelArticle = modelArticle;
	}

	/**
	 * Evenement de selection d'un dossier etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			articleDto = (ArticleDto) event.getObject();

			isCrud = true;
			listChapitres = buildChapitreList();
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Declancher l'action d'ajout
	 * 
	 * @author Mounir.MESSAOUDI on : 26 nov. 2014 14:19:20
	 */
	public void addAction() {
		articleDto = new ArticleDto();
		listChapitres = buildChapitreList();
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
			if (articleDto != null) {
				serviceLocator.getArticleService().save(articleDto);
				CommonMessagesUtils.showSuccessSaveMessage();
				isCrud = false;
			}
		} catch (Exception e) {
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

	/**
	 * build list section
	 */
	public List<SelectItem> buildChapitreList() {
		List<ChapitreDto> dtos = serviceLocator.getChapitreService().findAll();
		List<SelectItem> results = new ArrayList<>();

		for (ChapitreDto dto : dtos) {
			results.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
		}
		return results;
	}

	/**
	 * delete Chapitre
	 */
	public void deleteAction() {

		try {
			if (articleDto != null) {
				serviceLocator.getArticleService().remove(articleDto);
				CommonMessagesUtils.showSuccessDeleteMessage();
				isCrud = false;
			}
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	public boolean isView() {
		return isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	/**
	 * @return the searchKeyWord
	 */
	public String getSearchKeyWord() {
		return searchKeyWord;
	}

	/**
	 * @param searchKeyWord
	 *            the searchKeyWord to set
	 */
	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}

	/**
	 * @return the articleDto
	 */
	public ArticleDto getArticleDto() {
		return articleDto;
	}

	/**
	 * @param articleDto
	 *            the articleDto to set
	 */
	public void setArticleDto(ArticleDto articleDto) {
		this.articleDto = articleDto;
	}

	/**
	 * @return the listChapitres
	 */
	public List<SelectItem> getListChapitres() {
		return listChapitres;
	}

	/**
	 * @param listChapitres
	 *            the listChapitres to set
	 */
	public void setListChapitres(List<SelectItem> listChapitres) {
		this.listChapitres = listChapitres;
	}

}
