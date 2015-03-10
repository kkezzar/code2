package dz.gov.mesrs.sii.gfc.web.jsf.bean.referentiel;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.SectionDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;

/**
 * @author Salem Chouikh (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "sectionBean")
@ViewScoped
public class SectionMBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<SectionDto> model;
	private SectionDto sectionDto;
	private String searchKeyWord;

	// UI
	private boolean isView;
	private boolean isCrud;

	public SectionMBean() {
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
		model = new LazyDataModel<SectionDto>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3174194836578361532L;
			SectionDto sectionDto = new SectionDto();

			@Override
			public List<SectionDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				Boolean descending = null;

				// sectionDto.setIntituleFr(searchKeyWord);
				// sectionDto.setIntituleAr(searchKeyWord);

				SearchMode searchMode = new SearchMode(pageSize, first);
				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}

				}
				model.setRowCount(serviceLocator.getSectionService().countByKeyWord(searchKeyWord));

				return serviceLocator.getSectionService().findAllByKeyWord(searchKeyWord, searchMode);
			}
		};
	}

	public LazyDataModel<SectionDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<SectionDto> model) {
		this.model = model;
	}

	/**
	 * Evenement de selection d'un dossier etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			sectionDto = (SectionDto) event.getObject();
			isCrud = true;
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
		sectionDto = new SectionDto();
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
		System.out.println("------model-----" + model.getRowCount());
	}

	/**
	 * Enregistrer l'affectation
	 * 
	 * @author Mounir.MESSAOUDI on : 26 nov. 2014 14:26:51
	 */
	public void saveAction() {
		try {
			if (sectionDto != null) {
				serviceLocator.getSectionService().save(sectionDto);
				CommonMessagesUtils.showSuccessSaveMessage();
				isCrud = false;
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
			if (sectionDto != null) {
				serviceLocator.getSectionService().remove(sectionDto);
				CommonMessagesUtils.showSuccessDeleteMessage();
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
	 * @return the sectionDto
	 */
	public SectionDto getSectionDto() {
		return sectionDto;
	}

	/**
	 * @param sectionDto
	 *            the sectionDto to set
	 */
	public void setSectionDto(SectionDto sectionDto) {
		this.sectionDto = sectionDto;
	}

}
