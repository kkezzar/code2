package dz.gov.mesrs.sii.gfc.web.jsf.bean.referentiel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.SectionDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.TitreDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;

/**
 * @author Salem Chouikh (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "titreBean")
@ViewScoped
public class TitreMBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<TitreDto> modelTitre;
	private TitreDto titreDto;
	private String searchKeyWord;

	private List<SelectItem> listSections = new ArrayList<SelectItem>();
	private List<SelectItem> listSousSections = new ArrayList<SelectItem>();

	// UI
	private boolean isView;
	private boolean isCrud;
	private boolean disabledListSousSection;

	public TitreMBean() {
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
		modelTitre = new LazyDataModel<TitreDto>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3174194836578361532L;
			TitreDto titreDto = new TitreDto();

			@Override
			public List<TitreDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				Boolean descending = null;

				titreDto.setIntituleFr(searchKeyWord);
				titreDto.setIntituleAr(searchKeyWord);

				SearchMode searchMode = new SearchMode(pageSize, first);
				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}

				}
				modelTitre.setRowCount(serviceLocator.getTitreService().countByKeyWord(searchKeyWord));

				return serviceLocator.getTitreService().findAllByKeyWord(searchKeyWord, searchMode);
			}
		};
	}

	public LazyDataModel<TitreDto> getModel() {
		return modelTitre;
	}

	public void setModel(LazyDataModel<TitreDto> modelTitre) {
		this.modelTitre = modelTitre;
	}

	/**
	 * Evenement de selection d'un dossier etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			titreDto = (TitreDto) event.getObject();

			isCrud = true;
			listSections = buildSectionList();
			if (titreDto != null && titreDto.getSection() != null) {
				listSousSections = buildSousSectionList(titreDto.getSection());
				disabledListSousSection = false;
			}
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
		titreDto = new TitreDto();
		listSections = buildSectionList();
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
			if (titreDto != null) {
				if (titreDto.getSection() != null && titreDto.getSection().getId() == 0)
					titreDto.setSection(null);
				if (titreDto.getSousSection() != null && titreDto.getSousSection().getId() == 0)
					titreDto.setSousSection(null);
				serviceLocator.getTitreService().save(titreDto);
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
	public List<SelectItem> buildSectionList() {
		List<SectionDto> dtos = serviceLocator.getSectionService().findAll();
		List<SelectItem> results = new ArrayList<>();

		for (SectionDto dto : dtos) {
			results.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
		}
		return results;
	}

	/**
	 * build list section
	 */
	public List<SelectItem> buildSousSectionList(SectionDto section) {
		List<SelectItem> results = new ArrayList<>();

		if (section != null) {
			results = findListeSousSection(section);
		}

		return results;
	}

	/**
	 * change select list section
	 * 
	 * @param event
	 */
	public void onChangeSelect(AjaxBehaviorEvent event) {
		listSousSections = new ArrayList<SelectItem>();

		try {
			// titreDto.setSection((SectionDto)new
			// SectionDto(event.getSource()));

			if (titreDto != null && titreDto.getSection() != null && titreDto.getSection().getId() != 0) {
				listSousSections = buildSousSectionList(titreDto.getSection());
				disabledListSousSection = false;
			} else
				disabledListSousSection = true;
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * delete Section
	 */
	public void deleteAction() {

		try {
			if (titreDto != null) {
				serviceLocator.getTitreService().remove(titreDto);
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
	 * @return the titreDto
	 */
	public TitreDto getTitreDto() {
		return titreDto;
	}

	/**
	 * @param titreDto
	 *            the titreDto to set
	 */
	public void setTitreDto(TitreDto titreDto) {
		this.titreDto = titreDto;
	}

	/**
	 * @return the listSections
	 */
	public List<SelectItem> getListSections() {
		return listSections;
	}

	/**
	 * @param listSections
	 *            the listSections to set
	 */
	public void setListSections(List<SelectItem> listSections) {
		this.listSections = listSections;
	}

	/**
	 * @return the listSousSections
	 */
	public List<SelectItem> getListSousSections() {
		return listSousSections;
	}

	/**
	 * @param listSousSections
	 *            the listSousSections to set
	 */
	public void setListSousSections(List<SelectItem> listSousSections) {
		this.listSousSections = listSousSections;
	}

	/**
	 * @return the desabledListSousSection
	 */
	public boolean isDisabledListSousSection() {
		return disabledListSousSection;
	}

}
