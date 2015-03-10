package dz.gov.mesrs.sii.grh.web.jsf.bean.mutation;

import java.util.ArrayList;
import java.util.Collection;
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

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.MutationDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

/**
 * 
 * @author BELDI Jamel on : 15 déc. 2014 16:24:37
 */
@ManagedBean(name = "decisionMutationMBean")
@ViewScoped
public class DecisionMutationMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private MutationDto mutationDto;
	private LazyDataModel<MutationDto> mutationModel;
	private List<SituationEntiteOccurrenceDto> mutationHistory;
	private List<SelectItem> listeEtabDemandes;
	private DossierEmployeDto employeSearchDto;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;

	public DecisionMutationMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean.getRefEtablissementDto());
		onSearch();
		initSelectItemLists();
	}

	private void initDetail() {
		mutationDto = null;
	}

	private void initSelectItemLists() {
		loadEtablissementsDemandes();
	}

	public void onSearch() {
		try {
			loadMutationModel();
			initDetail();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Charger les décisions de mutation créées
	 * [DecisionMutationMBean.loadMutationModel] Method
	 * 
	 * @author BELDI Jamel on : 28 déc. 2014 17:27:31
	 */
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
						.countAllDecisionsMutation(sessionBean
								.getRefEtablissementDto().getId(), searchMode, searchKeyWords));
				 searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				List<MutationDto> dtos = serviceLocator.getMutationService().findAllDecisionsMutation(sessionBean
								.getRefEtablissementDto().getId(), searchMode, searchKeyWords);
				return dtos;
			}
		};

	}

	public void onNew() {
		mutationDto = new MutationDto();
		mutationDto.setEtabOrigineDto(sessionBean.getRefEtablissementDto());
		mutationDto.setEtabDemandeDto(new RefEtablissementDto());

	}

	public void onMutationSelect(SelectEvent event) {
		try {
			mutationDto = (MutationDto) event.getObject();

			retrieveHistory();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void onRowSelect(SelectEvent event) {
		try {
			DossierEmployeDto dossierEmployeDto = (DossierEmployeDto) event.getObject();
			mutationDto.setDossierEmployeDto(dossierEmployeDto);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void onSave() {
		try {
			if (validateForm()) {
				beforeSave();
				boolean updateSituation = false;
				if (mutationDto.getId() == null || mutationDto.getId() == 0) {
					updateSituation = true;
				}
				SituationEntiteDto situationEntiteDto = this.serviceLocator.getSituationService()
						.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_MUTATION_EMPLOYE_CODE,
								UtilConstants.SITUATION_CREEE_CODE);
				mutationDto.setSituationEntiteDto(situationEntiteDto);
				mutationDto = this.serviceLocator.getMutationService().save(mutationDto);
				if (updateSituation) {
					SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
					situationEntiteOccurrenceDto.setDateSituation(new Date());
					situationEntiteOccurrenceDto.setIdOccurrence(mutationDto.getId());
					situationEntiteOccurrenceDto.setIdSituation(situationEntiteDto.getId());
					this.serviceLocator.getSituationService().saveSituationOccurrence(situationEntiteOccurrenceDto);
				}
				afterSave();
				CommonMessagesUtils.showSuccessSaveMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onSend() {
		if (validateForm()) {
			beforeSave();
			SituationEntiteDto situationEntiteDto = this.serviceLocator.getSituationService()
					.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_MUTATION_EMPLOYE_CODE,
							UtilConstants.SITUATION_TRAITEE_CODE);
			mutationDto.setSituationEntiteDto(situationEntiteDto);
			mutationDto = this.serviceLocator.getMutationService().save(mutationDto);
			SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
			situationEntiteOccurrenceDto.setDateSituation(new Date());
			situationEntiteOccurrenceDto.setIdOccurrence(mutationDto.getId());
			situationEntiteOccurrenceDto.setIdSituation(situationEntiteDto.getId());
			this.serviceLocator.getSituationService().saveSituationOccurrence(situationEntiteOccurrenceDto);
			onSearch();
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}

	public void onRemove() {
		this.serviceLocator.getMutationService().remove(mutationDto);
		onSearch();
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	private boolean validateForm() {
		boolean result = true;
		if (mutationDto.getDateDecision() != null && mutationDto.getDossierEmployeDto().getDateTitularisation() != null
				&& mutationDto.getDateDecision().before(mutationDto.getDossierEmployeDto().getDateTitularisation())) {
			GRHMessagesUtils.showErrorMessage("date_decision_mutation_invalide");
			result = false;

		}

		if (mutationDto.getDateAccordEtabOrigine() != null
				&& mutationDto.getDossierEmployeDto().getDateTitularisation() != null
				&& mutationDto.getDateAccordEtabOrigine().before(
						mutationDto.getDossierEmployeDto().getDateTitularisation())) {
			GRHMessagesUtils.showErrorMessage("date_decision_accord_etab_origine_mutation_invalide");
			result = false;

		}
		if (mutationDto.getDateAccordEtabAccueil() != null
				&& mutationDto.getDossierEmployeDto().getDateTitularisation() != null
				&& mutationDto.getDateAccordEtabAccueil().before(
						mutationDto.getDossierEmployeDto().getDateTitularisation())) {
			GRHMessagesUtils.showErrorMessage("date_decision_accord_etab_accueil_mutation_invalide");
			result = false;

		}

		return result;
	}

	private void beforeSave() {

	}

	private void afterSave() {
		//onSearch();
		loadMutationModel();
	}

	private void loadEtablissementsDemandes() {
		listeEtabDemandes = new ArrayList<SelectItem>();
		List<RefEtablissementDto> list = serviceLocator.getRefEtablissementService().findAll();
		for (RefEtablissementDto refEtablissementDto : list) {
			if (!refEtablissementDto.getId().equals(sessionBean.getRefEtablissementDto().getId())) {
				SelectItem selectItem = new SelectItem(refEtablissementDto.getId(),
						refEtablissementDto.getLlEtablissementLatin());
				listeEtabDemandes.add(selectItem);
			}
		}
	}

	// hitory
	private void retrieveHistory() {
		mutationHistory = serviceLocator.getSituationService().getEntityOccurrenceHistory(
				UtilConstants.ENTITE_MUTATION_EMPLOYE_CODE, mutationDto.getId().intValue());
	}

	public void onSearchDossierEmploye() {
		try {
			loaddossierEmployeModel();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private void loaddossierEmployeModel() {
		dossierEmployeModel = new LazyDataModel<DossierEmployeDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(DossierEmployeDto dossierEmployeDto) {
				return dossierEmployeDto.getId();
			}

			@Override
			public List<DossierEmployeDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = new SearchMode(pageSize, first);
				Collection<Integer> collection = new ArrayList<Integer>();
				collection.add(serviceLocator
						.getSituationService()
						.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE,
								UtilConstants.SITUATION_TITULARISE_CODE).getId());
				searchMode.addFilter(new Filter("situationEntite.id", collection.toArray(), FilterMode.IN));
				dossierEmployeModel.setRowCount(serviceLocator.getDossierEmployeService().countAllByExample(
						employeSearchDto, searchMode));

				List<DossierEmployeDto> dtos = serviceLocator.getDossierEmployeService().findAllByExample(
						employeSearchDto, searchMode);
				return dtos;
			}
		};

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

	public List<SelectItem> getListeEtabDemandes() {
		return listeEtabDemandes;
	}

	public void setListeEtabDemandes(List<SelectItem> listeEtabDemandes) {
		this.listeEtabDemandes = listeEtabDemandes;
	}

	public DossierEmployeDto getEmployeSearchDto() {
		return employeSearchDto;
	}

	public void setEmployeSearchDto(DossierEmployeDto employeSearchDto) {
		this.employeSearchDto = employeSearchDto;
	}

	public LazyDataModel<DossierEmployeDto> getDossierEmployeModel() {
		return dossierEmployeModel;
	}

	public void setDossierEmployeModel(LazyDataModel<DossierEmployeDto> dossierEmployeModel) {
		this.dossierEmployeModel = dossierEmployeModel;
	}

}
