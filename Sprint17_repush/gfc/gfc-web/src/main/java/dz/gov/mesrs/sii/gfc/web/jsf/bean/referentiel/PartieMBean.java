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
import dz.gov.mesrs.sii.gfc.business.model.dto.PartieDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.TitreDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;

/**
 * @author Salem Chouikh (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "partieBean")
@ViewScoped
public class PartieMBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<PartieDto> modelParties;
	private PartieDto partieDto;
	private String searchKeyWord;

	// UI
	private boolean isView;
	private boolean isCrud;
	private List<SelectItem> listTitres = new ArrayList<SelectItem>();

	public PartieMBean() {
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
		modelParties = new LazyDataModel<PartieDto>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3174194836578361532L;
			PartieDto partieDto = new PartieDto();

			@Override
			public List<PartieDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				Boolean descending = null;

				// partieDto.setIntituleFr(searchKeyWord);
				// partieDto.setIntituleAr(searchKeyWord);

				SearchMode searchMode = new SearchMode(pageSize, first);
				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}

				}
				modelParties.setRowCount(serviceLocator.getPartieService().countByKeyWord(searchKeyWord));

				return serviceLocator.getPartieService().findAllByKeyWord(searchKeyWord, searchMode);
			}
		};
	}

	public LazyDataModel<PartieDto> getModelParties() {
		return modelParties;
	}

	public void setModelParties(LazyDataModel<PartieDto> modelPartie) {
		this.modelParties = modelPartie;
	}

	/**
	 * Evenement de selection d'un dossier etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			partieDto = (PartieDto) event.getObject();

			isCrud = true;
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
		partieDto = new PartieDto();
		listTitres = buildTitreList();
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
			if (partieDto != null) {
				serviceLocator.getPartieService().save(partieDto);
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
	public List<SelectItem> buildTitreList() {
		List<TitreDto> dtos = serviceLocator.getTitreService().findAll();
		List<SelectItem> results = new ArrayList<>();
		System.out.println("------dtos---" + dtos.size());
		for (TitreDto dto : dtos) {
			results.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
		}
		return results;
	}

	/**
	 * delete Titre
	 */
	public void deleteAction() {

		try {
			if (partieDto != null) {
				serviceLocator.getPartieService().remove(partieDto);
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
	 * @return the partieDto
	 */
	public PartieDto getPartieDto() {
		return partieDto;
	}

	/**
	 * @param partieDto
	 *            the partieDto to set
	 */
	public void setPartieDto(PartieDto partieDto) {
		this.partieDto = partieDto;
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

}
