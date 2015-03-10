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
import dz.gov.mesrs.sii.gfc.business.model.dto.ChapitreDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.PartieDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.SectionDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.SousSectionDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.TitreDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;

/**
 * @author Salem Chouikh (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "chapitreBean")
@ViewScoped
public class ChapitreMBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<ChapitreDto> modelChapitres;
	private ChapitreDto chapitreDto;

	private String searchKeyWord;

	private List<SelectItem> listTitres = new ArrayList<SelectItem>();
	private List<SelectItem> listParties = new ArrayList<SelectItem>();
	private List<SelectItem> listSections = new ArrayList<SelectItem>();
	private List<SelectItem> listSousSections = new ArrayList<SelectItem>();

	// UI
	private boolean isView;
	private boolean isCrud;

	public ChapitreMBean() {
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
		modelChapitres = new LazyDataModel<ChapitreDto>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3174194836578361532L;
			ChapitreDto chapitreDto = new ChapitreDto();

			@Override
			public List<ChapitreDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				Boolean descending = null;

				// chapitreDto.setIntituleFr(searchKeyWord);
				// chapitreDto.setIntituleAr(searchKeyWord);

				SearchMode searchMode = new SearchMode(pageSize, first);
				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}

				}
				modelChapitres.setRowCount(serviceLocator.getChapitreService().countByKeyWord(searchKeyWord));

				return serviceLocator.getChapitreService().findAllByKeyWord(searchKeyWord, searchMode);
			}
		};
	}

	/**
	 * Evenement de selection d'un dossier etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			chapitreDto = (ChapitreDto) event.getObject();

			isCrud = true;
			listParties = buildPartieList();
			listSections = buildSectionList();
			if (chapitreDto != null && chapitreDto.getIdSection() != null)
				listSousSections = buildSousSectionList(chapitreDto.getIdSection());
			listTitres = buildTitreList();

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
		chapitreDto = new ChapitreDto();
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
			if (chapitreDto != null) {
				if (chapitreDto.getTitre() != null && chapitreDto.getTitre().getId() == 0)
					chapitreDto.setTitre(null);
				if (chapitreDto.getPartie() != null && chapitreDto.getPartie().getId() == 0)
					chapitreDto.setPartie(null);
				if (chapitreDto.getIdSousSection() != null && chapitreDto.getIdSousSection().getId() == 0)
					chapitreDto.setIdSousSection(null);
				serviceLocator.getChapitreService().save(chapitreDto);
				searchAction();
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
	 * build list soussection
	 */
	public List<SelectItem> buildSousSectionList(SectionDto section) {
		List<SelectItem> results = new ArrayList<>();

		if (section != null) {
			results = findListeSousSection(section);
		}

		return results;
	}

	/**
	 * build list Titre/section
	 */
	public List<SelectItem> buildTitreList(SectionDto section) {
		List<SelectItem> results = new ArrayList<>();

		if (section != null) {
			results = findListeTitres(section);
		}
		return results;
	}

	/**
	 * build list Titre/ sous section
	 */
	public List<SelectItem> buildTitreList(SousSectionDto sousSection) {
		List<SelectItem> results = new ArrayList<>();

		if (sousSection != null) {
			results = findListeTitres(sousSection);
		}
		return results;
	}

	/**
	 * build list Titre
	 */
	public List<SelectItem> buildTitreList() {
		List<TitreDto> dtos = serviceLocator.getTitreService().findAll();
		List<SelectItem> results = new ArrayList<>();

		for (TitreDto dto : dtos) {
			results.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
		}
		return results;
	}

	/**
	 * build list Partie
	 */
	public List<SelectItem> buildPartieList() {
		List<PartieDto> dtos = serviceLocator.getPartieService().findAll();
		List<SelectItem> results = new ArrayList<>();

		for (PartieDto dto : dtos) {
			results.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
		}
		return results;
	}

	/**
	 * build list partie par titre
	 */
	public List<SelectItem> buildPartieTitreList(TitreDto titre) {
		List<SelectItem> results = new ArrayList<>();

		if (titre != null && titre.getId() != null && titre.getId() != 0) {
			results = findListePartieByTitre(titre);
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
		listTitres = new ArrayList<SelectItem>();

		try {
			// chapitreDto.setSection((SectionDto)new
			// SectionDto(event.getSource()));

			if (chapitreDto != null && chapitreDto.getIdSection() != null && chapitreDto.getIdSection().getId() != 0) {
				listSousSections = buildSousSectionList(chapitreDto.getIdSection());
				listTitres = buildTitreList(chapitreDto.getIdSection());

			}

		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * change select list section
	 * 
	 * @param event
	 */
	public void onChangeSelectSousSection(AjaxBehaviorEvent event) {
		listTitres = new ArrayList<SelectItem>();

		try {
			// chapitreDto.setSection((SectionDto)new
			// SectionDto(event.getSource()));

			if (chapitreDto != null && chapitreDto.getIdSousSection() != null
					&& chapitreDto.getIdSousSection().getId() != 0) {
				listTitres = buildTitreList(chapitreDto.getIdSousSection());

			}

		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	public void onChangeSelectTitre(AjaxBehaviorEvent event) {
		listParties = new ArrayList<SelectItem>();

		try {
			// chapitreDto.setSection((SectionDto)new
			// SectionDto(event.getSource()));

			if (chapitreDto != null && chapitreDto.getTitre() != null && chapitreDto.getTitre().getId() != 0) {
				listParties = buildPartieTitreList(chapitreDto.getTitre());

			}

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
			if (chapitreDto != null) {
				serviceLocator.getChapitreService().remove(chapitreDto);
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
	 * @return the chapitreDto
	 */
	public ChapitreDto getChapitreDto() {
		return chapitreDto;
	}

	/**
	 * @param chapitreDto
	 *            the chapitreDto to set
	 */
	public void setChapitreDto(ChapitreDto chapitreDto) {
		this.chapitreDto = chapitreDto;
	}

	/**
	 * @return the modelChapitres
	 */
	public LazyDataModel<ChapitreDto> getModelChapitres() {
		return modelChapitres;
	}

	/**
	 * @param modelChapitres
	 *            the modelChapitres to set
	 */
	public void setModelChapitres(LazyDataModel<ChapitreDto> modelChapitres) {
		this.modelChapitres = modelChapitres;
	}

	/**
	 * @return the listTitres
	 */
	public List<SelectItem> getListTitres() {
		return listTitres;
	}

	/**
	 * @param listTitres
	 *            the listTitres to set
	 */
	public void setListTitres(List<SelectItem> listTitres) {
		this.listTitres = listTitres;
	}

	/**
	 * @return the listParties
	 */
	public List<SelectItem> getListParties() {
		return listParties;
	}

	/**
	 * @param listParties
	 *            the listParties to set
	 */
	public void setListParties(List<SelectItem> listParties) {
		this.listParties = listParties;
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

}
