package dz.gov.mesrs.sii.grh.web.jsf.bean.position;

import java.io.Serializable;
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
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.ChangementPositionDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;

@ManagedBean(name = "validerChangementPositionMBean")
@ViewScoped
public class ValiderChangementPositionMBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -1588246816874292550L;

	private LazyDataModel<ChangementPositionDto> changementPositionDtos;
	private String searchKeyWords;
	private ChangementPositionDto changementPositionDto;
	private int etablissementId;

	@PostConstruct
	public void init() {
		etablissementId = getSessionBean().getCompte().getEtabId();
		loadChangementDtos();
	}

	// Ajax

	public void onSearch() {
		changementPositionDto=null;
		loadChangementDtos();
	}

	public void onValidate() {
		
		serviceLocator.getChangementPositionService().validate(changementPositionDto);
		CommonMessagesUtils.showSuccessSaveMessage();
		loadChangementDtos();
		changementPositionDto=null;

	}
	
	public void onRowSelected(SelectEvent event){
		changementPositionDto = (ChangementPositionDto) event.getObject();
	}

	// private
	public void loadChangementDtos() {
		changementPositionDtos = new LazyDataModel<ChangementPositionDto>() {

			private static final long serialVersionUID = 312770449708652963L;

			@Override
			public List<ChangementPositionDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				changementPositionDtos.setRowCount(serviceLocator.getChangementPositionService()
						.countAllAcceptedRequest(etablissementId, searchKeyWords));
				return serviceLocator.getChangementPositionService().findAllAcceptedRequest(etablissementId,
						searchKeyWords, searchMode);

			}

		};
	}
	// Getters & Setters

	public LazyDataModel<ChangementPositionDto> getChangementPositionDtos() {
		return changementPositionDtos;
	}

	public void setChangementPositionDtos(LazyDataModel<ChangementPositionDto> changementPositionDtos) {
		this.changementPositionDtos = changementPositionDtos;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public ChangementPositionDto getChangementPositionDto() {
		return changementPositionDto;
	}

	public void setChangementPositionDto(ChangementPositionDto changementPositionDto) {
		this.changementPositionDto = changementPositionDto;
	}
	
	
	

}
