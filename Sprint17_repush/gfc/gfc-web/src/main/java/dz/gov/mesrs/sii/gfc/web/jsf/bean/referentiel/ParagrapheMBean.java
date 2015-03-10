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
import dz.gov.mesrs.sii.gfc.business.model.dto.ParagrapheDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;

/**
 * @author Salem Chouikh (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "paragrapheBean")
@ViewScoped
public class ParagrapheMBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<ParagrapheDto> modelParagraphe;
	private ParagrapheDto paragrapheDto;
	private String searchKeyWord;
	private List<SelectItem> listArticles = new ArrayList<SelectItem>();
	// UI
	private boolean isView;
	private boolean isCrud;

	public ParagrapheMBean() {
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
		modelParagraphe = new LazyDataModel<ParagrapheDto>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3174194836578361532L;
			ParagrapheDto paragrapheDto = new ParagrapheDto();

			@Override
			public List<ParagrapheDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				Boolean descending = null;

				// paragrapheDto.setIntituleFr(searchKeyWord);
				// paragrapheDto.setIntituleAr(searchKeyWord);

				SearchMode searchMode = new SearchMode(pageSize, first);
				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}

				}
				modelParagraphe.setRowCount(serviceLocator.getParagrapheService().countByKeyWord(searchKeyWord));

				return serviceLocator.getParagrapheService().findAllByKeyWord(searchKeyWord, searchMode);
			}
		};
	}

	public LazyDataModel<ParagrapheDto> getModelParagraphe() {
		return modelParagraphe;
	}

	public void setModelParagraphe(LazyDataModel<ParagrapheDto> modelParagraphe) {
		this.modelParagraphe = modelParagraphe;
	}

	/**
	 * Evenement de selection d'un dossier etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			paragrapheDto = (ParagrapheDto) event.getObject();

			isCrud = true;
			listArticles = buildArticleList();
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
		paragrapheDto = new ParagrapheDto();
		listArticles = buildArticleList();
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
			if (paragrapheDto != null) {
				serviceLocator.getParagrapheService().save(paragrapheDto);
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
	public List<SelectItem> buildArticleList() {
		List<ArticleDto> dtos = serviceLocator.getArticleService().findAll();
		List<SelectItem> results = new ArrayList<>();

		for (ArticleDto dto : dtos) {
			results.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
		}
		return results;
	}

	/**
	 * delete Article
	 */
	public void deleteAction() {

		try {
			if (paragrapheDto != null) {
				serviceLocator.getParagrapheService().remove(paragrapheDto);
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
	 * @return the paragrapheDto
	 */
	public ParagrapheDto getParagrapheDto() {
		return paragrapheDto;
	}

	/**
	 * @param paragrapheDto
	 *            the paragrapheDto to set
	 */
	public void setParagrapheDto(ParagrapheDto paragrapheDto) {
		this.paragrapheDto = paragrapheDto;
	}

	/**
	 * @return the listArticles
	 */
	public List<SelectItem> getListArticles() {
		return listArticles;
	}

	/**
	 * @param listArticles
	 *            the listArticles to set
	 */
	public void setListArticles(List<SelectItem> listArticles) {
		this.listArticles = listArticles;
	}

}
