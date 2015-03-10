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
import dz.gov.mesrs.sii.gfc.business.model.dto.SectionDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.SousSectionDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;

/**
 * @author Salem Chouikh (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "sousSectionBean")
@ViewScoped
public class SousSectionMBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<SousSectionDto> modelSousSection;
	private SousSectionDto sousSectionDto;
	private String searchKeyWord;

	private List<SelectItem> listSections = new ArrayList<SelectItem>();

	// UI
	private boolean isView;
	private boolean isCrud;

	public SousSectionMBean() {
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
		modelSousSection = new LazyDataModel<SousSectionDto>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3174194836578361532L;
			SousSectionDto sousSectionDto = new SousSectionDto();

			@Override
			public List<SousSectionDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				Boolean descending = null;

				// sousSectionDto.setIntituleFr(searchKeyWord);
				// sousSectionDto.setIntituleAr(searchKeyWord);

				SearchMode searchMode = new SearchMode(pageSize, first);
				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}

				}
				modelSousSection.setRowCount(serviceLocator.getSousSectionService().countByKeyWord(searchKeyWord));

				return serviceLocator.getSousSectionService().findAllByKeyWord(searchKeyWord, searchMode);
			}
		};
	}

	public LazyDataModel<SousSectionDto> getModelSousSection() {
		return modelSousSection;
	}

	public void setModelSousSection(LazyDataModel<SousSectionDto> modelSousSection) {
		this.modelSousSection = modelSousSection;
	}

	/**
	 * Evenement de selection d'un dossier etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			sousSectionDto = (SousSectionDto) event.getObject();

			isCrud = true;
			listSections = buildSectionList();
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
		sousSectionDto = new SousSectionDto();
		listSections = buildSectionList();
		isCrud = true;
	}

	/**
	 * Declancher l'action de la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 26 nov. 2014 14:27:38
	 */
	public void searchAction() {
		System.out.println("----searchKeyWord-----------" + searchKeyWord);
		loadSearchResults();
		System.out.println("------modelSousSection-----" + modelSousSection.getRowCount());
	}

	/**
	 * Enregistrer l'affectation
	 * 
	 * @author Mounir.MESSAOUDI on : 26 nov. 2014 14:26:51
	 */
	public void saveAction() {
		try {
			if (sousSectionDto != null) {
				serviceLocator.getSousSectionService().save(sousSectionDto);
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
		System.out.println("------dtos---" + dtos.size());
		for (SectionDto dto : dtos) {
			results.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
		}
		return results;
	}

	/**
	 * delete Section
	 */
	public void deleteAction() {

		try {
			if (sousSectionDto != null) {
				serviceLocator.getSousSectionService().remove(sousSectionDto);
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
	 * @return the sousSectionDto
	 */
	public SousSectionDto getSousSectionDto() {
		return sousSectionDto;
	}

	/**
	 * @param sousSectionDto
	 *            the sousSectionDto to set
	 */
	public void setSousSectionDto(SousSectionDto sousSectionDto) {
		this.sousSectionDto = sousSectionDto;
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

}
