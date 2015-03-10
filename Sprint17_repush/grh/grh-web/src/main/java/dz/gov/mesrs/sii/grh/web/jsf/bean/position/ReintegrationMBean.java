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
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

@ManagedBean(name = "reintegrationMBean")
@ViewScoped
public class ReintegrationMBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 4609490856120093782L;

	private int etablissementId;
	private LazyDataModel<ChangementPositionDto> changementPositionDtos;
	private ChangementPositionDto changementPositionDto;
	private String searchKeyWords;

	//

	@PostConstruct
	public void init() {
		etablissementId = getSessionBean().getCompte().getEtabId();
		loadDossierEmployes();
	}

	// Ajax
	public void onSearch() {
		loadDossierEmployes();
	}

	public void onSave() {
		serviceLocator.getChangementPositionService().reintegrer(changementPositionDto);
		changementPositionDto = null;
		CommonMessagesUtils.showSuccessSaveMessage();
	}

	public void onRowSelected(SelectEvent event) {
		changementPositionDto = (ChangementPositionDto) event.getObject();
	}

	// private
	private void loadDossierEmployes() {
		changementPositionDto = null;
		changementPositionDtos = new LazyDataModel<ChangementPositionDto>() {

			private static final long serialVersionUID = 6588606585772563357L;

			@Override
			public List<ChangementPositionDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				changementPositionDtos.setRowCount(serviceLocator.getChangementPositionService()
						.countAllValidatedRequest(etablissementId, searchKeyWords));
				return serviceLocator.getChangementPositionService().findAllValidatedRequest(etablissementId,
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

	public ChangementPositionDto getChangementPositionDto() {
		return changementPositionDto;
	}

	public void setChangementPositionDto(ChangementPositionDto changementPositionDto) {
		this.changementPositionDto = changementPositionDto;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

}
