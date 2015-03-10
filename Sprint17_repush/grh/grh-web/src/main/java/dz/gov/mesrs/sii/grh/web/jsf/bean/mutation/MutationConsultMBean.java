package dz.gov.mesrs.sii.grh.web.jsf.bean.mutation;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.MutationDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;

/**
 * 
 * @author BELDI Jamel on : 29 déc. 2014 10:40:51
 */
@ManagedBean(name = "mutationConsultMBean")
@ViewScoped
public class MutationConsultMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private MutationDto mutationDto;
	private LazyDataModel<MutationDto> mutationModel;
	private List<SituationEntiteOccurrenceDto> mutationHistory;

	public MutationConsultMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		onSearch();
		initSelectItemLists();
	}

	private void initDetail() {
		mutationDto = null;
	}

	private void initSelectItemLists() {

	}

	public void onSearch() {
		try {
			loadMutationModel();
			initDetail();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// Charger les résultat des demandes et les décisions de mutations
	// (traitées)
	private void loadMutationModel() {
		mutationModel = new LazyDataModel<MutationDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(MutationDto mutationDto) {
				return mutationDto.getId();
			}

			@Override
			public List<MutationDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = new SearchMode();
				mutationModel.setRowCount(serviceLocator.getMutationService()
						.countAllMutations(sessionBean
								.getRefEtablissementDto().getId(), searchMode, searchKeyWords));
				searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				List<MutationDto> dtos = serviceLocator.getMutationService().findAllMutations(sessionBean
								.getRefEtablissementDto().getId(), searchMode, searchKeyWords);
				return dtos;
			}
		};

	}

	public void onMutationSelect(SelectEvent event) {
		try {
			mutationDto = (MutationDto) event.getObject();
			retrieveHistory();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// hitory
	private void retrieveHistory() {
		mutationHistory = serviceLocator.getSituationService().getEntityOccurrenceHistory(
				UtilConstants.ENTITE_MUTATION_EMPLOYE_CODE, mutationDto.getId().intValue());
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public MutationDto getMutationDto() {
		return mutationDto;
	}

	public void setMutationDto(MutationDto mutationDto) {
		this.mutationDto = mutationDto;
	}

	public LazyDataModel<MutationDto> getMutationModel() {
		return mutationModel;
	}

	public void setMutationModel(LazyDataModel<MutationDto> mutationModel) {
		this.mutationModel = mutationModel;
	}

	public List<SituationEntiteOccurrenceDto> getMutationHistory() {
		return mutationHistory;
	}

	public void setMutationHistory(List<SituationEntiteOccurrenceDto> mutationHistory) {
		this.mutationHistory = mutationHistory;
	}

}
