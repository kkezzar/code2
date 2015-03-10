package dz.gov.mesrs.sii.grh.web.jsf.bean.mutation;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.MutationDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

/**
 * 
 * @author BELDI Jamel on : 15 déc. 2014 16:24:37
 */
@ManagedBean(name = "resultatMutationMBean")
@ViewScoped
public class ResultatMutationMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private MutationDto mutationDto;
	private LazyDataModel<MutationDto> mutationModel;
	private List<SituationEntiteOccurrenceDto> mutationHistory;

	public ResultatMutationMBean() {

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

	// Charger les résultat des demandes (Demande Soumise pour validation)
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
						.countAllDemandesSoumisePourValidation(sessionBean
								.getRefEtablissementDto().getId(), searchMode, searchKeyWords));
				 searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				List<MutationDto> dtos = serviceLocator.getMutationService().findAllDemandesSoumisePourValidation(sessionBean
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
				mutationDto = this.serviceLocator.getMutationService().save(mutationDto);
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

	private boolean validateForm() {
		boolean result = true;
		if (mutationDto.getDateResultat() != null && mutationDto.getDateResultat().before(mutationDto.getDateDemande())) {
			GRHMessagesUtils.showErrorMessage("date_resultat_mutation_invalide");
			result = false;

		}
		if (mutationDto.getDateAccordEtabOrigine() != null
				&& mutationDto.getDateAccordEtabOrigine().before(mutationDto.getDateDemande())) {
			GRHMessagesUtils.showErrorMessage("date_accord_etab_origine_mutation_invalide");
			result = false;

		}
		if (mutationDto.getDateAccordEtabAccueil() != null
				&& mutationDto.getDateAccordEtabAccueil().before(mutationDto.getDateDemande())) {
			GRHMessagesUtils.showErrorMessage("date_accord_etab_accueil_mutation_invalide");
			result = false;

		}

		if (mutationDto.getDateDecision() != null && mutationDto.getDateResultat() != null
				&& mutationDto.getDateDecision().before(mutationDto.getDateResultat())) {
			GRHMessagesUtils.showErrorMessage("date_decision_resultat_mutation_invalide");
			result = false;

		}

		return result;
	}

	private void beforeSave() {
		if (mutationDto.getAccepte() != null) {
			if (Boolean.TRUE.equals(mutationDto.getAccepte())) {
				mutationDto.setMotif(null);

			} else {
				mutationDto.setDateDecision(null);
				mutationDto.setDateAccordEtabOrigine(null);
				mutationDto.setDateAccordEtabAccueil(null);
				mutationDto.setDateEffet(null);
			}
		}
	}

	private void afterSave() {
		// onSearch();
		loadMutationModel();
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
