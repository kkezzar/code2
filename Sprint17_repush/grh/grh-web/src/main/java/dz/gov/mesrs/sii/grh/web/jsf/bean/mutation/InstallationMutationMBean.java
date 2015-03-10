package dz.gov.mesrs.sii.grh.web.jsf.bean.mutation;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.MutationDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

/**
 * 
 * @author BELDI Jamel on : 29 déc. 2014 10:40:51
 */
@ManagedBean(name = "installationMutationMBean")
@ViewScoped
public class InstallationMutationMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private MutationDto mutationDto;
	private LazyDataModel<MutationDto> mutationModel;
	private List<SituationEntiteOccurrenceDto> mutationHistory;
	private List<SelectItem> listeStructure;
	private Date dateInstallation;
	private Integer affectation;

	public InstallationMutationMBean() {

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
		listeStructure = findListeStructure(sessionBean.getRefEtablissementDto().getId());
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
						.countAllMutationsTraites(sessionBean
								.getRefEtablissementDto().getId(), searchMode, searchKeyWords));
				searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				List<MutationDto> dtos = serviceLocator.getMutationService().findAllMutationsTraites(sessionBean
								.getRefEtablissementDto().getId(), searchMode, searchKeyWords);
				return dtos;
			}
		};

	}

	public void onMutationSelect(SelectEvent event) {
		try {
			mutationDto = (MutationDto) event.getObject();
			dateInstallation = mutationDto.getDateEffet();
			listeStructure = findListeStructure(mutationDto.getEtabDemandeDto().getId());
			retrieveHistory();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void onSave() {
		try {
			if (validateForm()) {
				beforeSave();
				serviceLocator.getMutationService().saveInstallationEmployeMute(mutationDto, dateInstallation,
						affectation);
				afterSave();
				CommonMessagesUtils.showSuccessSaveMessage();
			}
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage("Une erreur inconnue s'est déroulée!");
			e.printStackTrace();

		}
	}

	private boolean validateForm() {
		boolean result = true;
		if (dateInstallation != null && dateInstallation.before(mutationDto.getDateEffet())) {
			GRHMessagesUtils.showErrorMessage("date_installation_mutation_invalide");
			result = false;

		}

		return result;
	}

	private void beforeSave() {

	}

	private void afterSave() {
		onSearch();

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

	public List<SelectItem> getListeStructure() {
		return listeStructure;
	}

	public void setListeStructure(List<SelectItem> listeStructure) {
		this.listeStructure = listeStructure;
	}

	public Date getDateInstallation() {
		return dateInstallation;
	}

	public void setDateInstallation(Date dateInstallation) {
		this.dateInstallation = dateInstallation;
	}

	public Integer getAffectation() {
		return affectation;
	}

	public void setAffectation(Integer affectation) {
		this.affectation = affectation;
	}

}
