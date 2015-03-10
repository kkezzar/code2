package dz.gov.mesrs.sii.grh.web.jsf.bean.referentiel;

import java.util.ArrayList;
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
import dz.gov.mesrs.sii.grh.business.model.dto.CorpsDto;
import dz.gov.mesrs.sii.grh.business.model.dto.StatutDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

@ManagedBean(name = "corpsMBean")
@ViewScoped
public class CorpsMBean extends BaseBean {

	private static final long serialVersionUID = 2869826388759142153L;

	private String searchKeyWords;
	// private LazyDataModel<CorpsDto> corpsDtos;
	private List<CorpsDto> corpsDtos;
	private CorpsDto corpsDto;
	private List<SelectItem> status;
	private List<SelectItem> filieres;
	private List<SelectItem> corps;
	private boolean displayDataTable;

	@PostConstruct
	public void init() {
		status = new ArrayList<>();
		List<StatutDto> dtos = this.serviceLocator.getStatutService().getListValideStatuts();
		for (StatutDto dto : dtos) {
			status.add(new SelectItem(dto.getId(), dto.getNumero() + " " + dto.getAnnee()));
		}
		filieres = findFiliereProferssionnelleList();
		corps = findCorpsProfessionnelsList();
		onSearch();
	}

	public void onCorpsDtoSelected(SelectEvent event) {
		corpsDto = (CorpsDto) event.getObject();
	}

	public void onSearch() {
		displayDataTable = true;
		// corpsDtos = new LazyDataModel<CorpsDto>() {
		// private static final long serialVersionUID = 5502914952517272680L;
		//
		// @Override
		// public List<CorpsDto> load(int first, int pageSize, String sortField,
		// SortOrder sortOrder,
		// Map<String, String> filters) {
		// corpsDtos.setRowCount(serviceLocator.getCorpsService().countByKeyWord(searchKeyWords));
		// SearchMode searchMode = new SearchMode(pageSize, first);
		// if (!StringUtils.isEmpty(sortField)) {
		// SearchMode.SortOrder order = null;
		// if (sortOrder != null) {
		// if (sortOrder.equals(SortOrder.DESCENDING)) {
		// order = SearchMode.SortOrder.DESC;
		// } else {
		// order = SearchMode.SortOrder.ASC;
		// }
		//
		// }
		// SearchMode.SortField sort = new SearchMode.SortField(sortField,
		// order);
		// searchMode.addSortField(sort);
		// }
		//
		// return
		// serviceLocator.getCorpsService().findAllByKeyWord(searchKeyWords,
		// searchMode);
		// }
		// };
		corpsDtos = serviceLocator.getCorpsService().findAll();
	}

	public void onDelete() {
		this.serviceLocator.getCorpsService().remove(corpsDto);
		corpsDto = null;
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	public void onSave() {
		corpsDto = this.serviceLocator.getCorpsService().save(corpsDto);
		CommonMessagesUtils.showSuccessSaveMessage();
		onSearch();
	}

	public void onNew() {
		corpsDto = new CorpsDto();
		NomenclatureDto filiereDto = new NomenclatureDto();
		NomenclatureDto corpsDto = new NomenclatureDto();
		StatutDto statutDto = new StatutDto();
		this.corpsDto.setStatut(statutDto);
		this.corpsDto.setNomenclatureByIdNomenclatureCorps(corpsDto);
		this.corpsDto.setNomenclatureByIdNomenclatureFiliere(filiereDto);

	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

//	public LazyDataModel<CorpsDto> getCorpsDtos() {
//		return corpsDtos;
//	}
//
//	public void setCorpsDtos(LazyDataModel<CorpsDto> corpsDtos) {
//		this.corpsDtos = corpsDtos;
//	}

	
	public List<CorpsDto> getCorpsDtos() {
		return corpsDtos;
	}
	
	public void setCorpsDtos(List<CorpsDto> corpsDtos) {
		this.corpsDtos = corpsDtos;
	}
	
	public CorpsDto getCorpsDto() {
		return corpsDto;
	}

	public void setCorpsDto(CorpsDto corpsDto) {
		this.corpsDto = corpsDto;
	}

	public List<SelectItem> getStatus() {
		return status;
	}

	public void setStatus(List<SelectItem> status) {
		this.status = status;
	}

	public List<SelectItem> getFilieres() {
		return filieres;
	}

	public void setFilieres(List<SelectItem> filieres) {
		this.filieres = filieres;
	}

	public List<SelectItem> getCorps() {
		return corps;
	}

	public void setCorps(List<SelectItem> corps) {
		this.corps = corps;
	}

	public boolean isDisplayDataTable() {
		return displayDataTable;
	}

	public void setDisplayDataTable(boolean displayDataTable) {
		this.displayDataTable = displayDataTable;
	}

}
