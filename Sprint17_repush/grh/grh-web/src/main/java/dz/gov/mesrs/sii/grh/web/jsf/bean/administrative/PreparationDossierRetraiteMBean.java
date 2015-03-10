package dz.gov.mesrs.sii.grh.web.jsf.bean.administrative;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.business.model.dto.FinActiviteDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PieceConstitutiveDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

@ManagedBean(name = "preparationDossierRetraiteMBean")
@ViewScoped
public class PreparationDossierRetraiteMBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -7012458144424823279L;

	private int etablissementId;

	private String searchKeyWords;
	private LazyDataModel<FinActiviteDto> finActviteModel;
	private FinActiviteDto finActiviteDto;

	@PostConstruct
	public void init() {
		etablissementId = getSessionBean().getCompte().getEtabId();
		onSearch();
	}

	// Ajax
	public void onSearch() {
		finActiviteDto = null;
		finActviteModel = new LazyDataModel<FinActiviteDto>() {

			private static final long serialVersionUID = -7149871064545314907L;

			@Override
			public List<FinActiviteDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = new SearchMode(pageSize, first);
				finActviteModel.setRowCount(serviceLocator.getFinActiviteService().countAllAdmisRetraite(
						etablissementId, searchKeyWords));

				if (!StringUtils.isEmpty(sortField)) {
					SearchMode.SortOrder order = null;
					if (sortOrder != null) {
						if (sortOrder.equals(SortOrder.DESCENDING)) {
							order = SearchMode.SortOrder.DESC;
						} else {
							order = SearchMode.SortOrder.ASC;
						}

					}
					SearchMode.SortField sort = new SearchMode.SortField(sortField, order);
					searchMode.addSortField(sort);
				}

				return serviceLocator.getFinActiviteService().findAllAdmisRetraite(etablissementId, searchKeyWords,
						searchMode);
			}

		};

	}

	public void onRowSelected(SelectEvent event) {
		finActiviteDto = (FinActiviteDto) event.getObject();
		Collections.sort(finActiviteDto.getDossierDto().getPieceConstitutiveDtos(),
				new Comparator<PieceConstitutiveDto>() {

					@Override
					public int compare(PieceConstitutiveDto o1, PieceConstitutiveDto o2) {
						return o1.getTypePiece().getRang().compareTo(o2.getTypePiece().getRang());
					}
				});

	}

	public void onSave() {
		if(validForm()){
			serviceLocator.getFinActiviteService().save(finActiviteDto);
			CommonMessagesUtils.showSuccessSaveMessage();	
		}
		
	}
//private
	private boolean validForm(){
		boolean valid = true;
		List<PieceConstitutiveDto> pieceConstitutiveDtos = finActiviteDto.getDossierDto().getPieceConstitutiveDtos();
		for(PieceConstitutiveDto dto : pieceConstitutiveDtos){
			if(dto.isFournie() && dto.getDateFourniture()==null){
				valid = false;
				GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_FIN_ACTIVITE, "piece_data_table_required_date_fourniture");
			}
		}
		return valid;
	}
	// Getters/Setters
	public LazyDataModel<FinActiviteDto> getFinActviteModel() {
		return finActviteModel;
	}

	public void setFinActviteModel(LazyDataModel<FinActiviteDto> finActviteModel) {
		this.finActviteModel = finActviteModel;
	}

	public FinActiviteDto getFinActiviteDto() {
		return finActiviteDto;
	}

	public void setFinActiviteDto(FinActiviteDto finActiviteDto) {
		this.finActiviteDto = finActiviteDto;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

}
