package dz.gov.mesrs.sii.grh.web.jsf.bean.referentiel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.grh.business.model.dto.StatutDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * 
 * @author salem
 * 
 */

@ManagedBean(name = "statutMBean")
@ViewScoped
public class StatutMBean extends BaseBean {
	private static final long serialVersionUID = 1L;

	public StatutDto editStatut;
//	public LazyDataModel<StatutDto> listStatuts;
	private List<StatutDto> listStatuts;
	private String searchKeyWord;
	private List<SelectItem> statutList;
	private List<SelectItem> typeStatutList;
	private boolean newElement = false;
	private boolean displayDataTable;
	
	@PostConstruct
	public void init(){
		onSearch();
	}

	public void onRowSelect(SelectEvent event) {
		editStatut = (StatutDto) event.getObject();
		if (editStatut.getStatutParentDto() == null) {
			editStatut.setStatutParentDto(new StatutDto());
		}
		buildListItem();
	}

	public void addNewStatut() {
		editStatut = new StatutDto();
		editStatut.setStatutParentDto(new StatutDto());
		editStatut.setTypeStatutDto(new NomenclatureDto());
		buildListItem();

	}

	public void buildListItem() {
		List<StatutDto> listValide = new ArrayList<StatutDto>();
		statutList = new ArrayList<SelectItem>();
		listValide = this.serviceLocator.getStatutService().getListValideStatuts();
		if (listValide != null) {
			for (StatutDto dto : listValide) {
				statutList.add(new SelectItem(dto.getId(), dto.getNumero()));
			}
		}
		typeStatutList = findListTypeStatut();

	}

	public void saveStatut() {
		if (editStatut.getStatutParentDto().getId() == null) {
			editStatut.setStatutParentDto(null);
		}

		if (validateForm()) {
			if (editStatut.getId() == null) {
				editStatut.setDateStatut(new Date());
			}
			editStatut = this.serviceLocator.getStatutService().save(editStatut);
			CommonMessagesUtils.showSuccessSaveMessage();
			onSearch();
		}
		if (editStatut.getStatutParentDto() == null) {
			editStatut.setStatutParentDto(new StatutDto());
		}

	}

	private boolean validateForm() {
		Date datePublication = editStatut.getDatePublication();
		if (editStatut.getStatutParentDto() != null) {
			StatutDto parentDto = this.serviceLocator.getStatutService().findUniqueByExample(
					editStatut.getStatutParentDto());
			if (parentDto.getDatePublication().compareTo(datePublication) > 0) {
				GRHMessagesUtils.showErrorMessage("date_decret_invalide");
				return false;
			}
		}
		Date dateEffet = editStatut.getDateEffet();
		if (dateEffet.compareTo(datePublication) < 0) {
			GRHMessagesUtils.showErrorMessage("date_effet_publication_invalide");
			return false;
		}

		Date dateFinValidation = editStatut.getDateFinValidation();
		if (dateFinValidation != null && dateFinValidation.compareTo(dateEffet) < 0) {
			GRHMessagesUtils.showErrorMessage("date_effet_fin_validation_invalide");
			return false;
		}
		return true;

	}

	public void onSearch() {
		displayDataTable = true;
//		listStatuts = new LazyDataModel<StatutDto>() {
//			private static final long serialVersionUID = 3174194836578361532L;
//
//			@Override
//			public List<StatutDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
//					Map<String, String> filters) {
//				listStatuts.setRowCount(serviceLocator.getStatutService().countByKeyWord(searchKeyWord));
//				SearchMode searchMode = new SearchMode(pageSize, first);
//				if (!StringUtils.isEmpty(sortField)) {
//					SearchMode.SortOrder order = null;
//					if (sortOrder != null) {
//						if (sortOrder.equals(SortOrder.DESCENDING)) {
//							order = SearchMode.SortOrder.DESC;
//						} else {
//							order = SearchMode.SortOrder.ASC;
//						}
//
//					}
//					SearchMode.SortField sort = new SearchMode.SortField(sortField, order);
//					searchMode.addSortField(sort);
//				}
//
//				return serviceLocator.getStatutService().findAllByKeyWord(searchKeyWord, searchMode);
//			}
//		};
		
		listStatuts = serviceLocator.getStatutService().findAll();
	}

	public void deleteStatut() {
		this.serviceLocator.getStatutService().remove(editStatut);
		CommonMessagesUtils.showSuccessDeleteMessage();
		editStatut = null;
	}

	public StatutDto getEditStatut() {
		return editStatut;
	}

	public void setEditStatut(StatutDto editStatut) {
		this.editStatut = editStatut;
	}

//	public LazyDataModel<StatutDto> getListStatuts() {
//		return listStatuts;
//	}
//
//	public void setListStatuts(LazyDataModel<StatutDto> listStatuts) {
//		this.listStatuts = listStatuts;
//	}

	
	public String getSearchKeyWord() {
		return searchKeyWord;
	}

	public List<StatutDto> getListStatuts() {
		return listStatuts;
	}

	public void setListStatuts(List<StatutDto> listStatuts) {
		this.listStatuts = listStatuts;
	}

	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}

	public List<SelectItem> getStatutList() {
		return statutList;
	}

	public List<SelectItem> getTypeStatutList() {
		return typeStatutList;
	}

	public boolean isNewElement() {
		return newElement;
	}

	public void setNewElement(boolean newElement) {
		this.newElement = newElement;
	}

	public boolean isDisplayDataTable() {
		return displayDataTable;
	}

	public void setDisplayDataTable(boolean displayDataTable) {
		this.displayDataTable = displayDataTable;
	}

}
