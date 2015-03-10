package dz.gov.mesrs.sii.grh.web.jsf.bean.recrutement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
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
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.StageDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * 
 * @author BELDI Jamel on : 15 déc. 2014 16:24:37
 */
@ManagedBean(name = "resultatStageMBean")
@ViewScoped
public class ResultatStageMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private StageDto stageDto;
	private DossierEmployeDto dossierEmployeDto;
	private List<SelectItem> listeDecisionStage;
	private boolean renderedProlongation;
	private SituationEntiteDto situationConcluant;
	private SituationEntiteDto situationAProlanger;
	private SituationEntiteDto situationLicenciement;

	private NomenclatureDto decisionConcluant;
	private NomenclatureDto decisionAProlonger;
	private NomenclatureDto decisionLicenciement;
	private DossierEmployeDto employeSearchDto;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private List<SituationEntiteOccurrenceDto> dossierEmployeHistory;

	public ResultatStageMBean() {

	}

	@PostConstruct
	public void init() {
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean
				.getRefEtablissementDto());
		onSearchDossierEmploye();
		initSelectItemLists();
		// initDetail();
		loadSituationDtos();
		loadDecisionDtos();
	}

	private void loadDecisionDtos() {
		decisionConcluant = this.serviceLocator.getNomenclatureService()
				.findByCode(UtilConstant.NC_GRH_DECISION_STAGE,
						UtilConstant.DECISION_STAGE_CONCLUANT_CODE);
		decisionAProlonger = this.serviceLocator.getNomenclatureService()
				.findByCode(UtilConstant.NC_GRH_DECISION_STAGE,
						UtilConstant.DECISION_STAGE_A_PROLONGER_CODE);
		decisionLicenciement = this.serviceLocator.getNomenclatureService()
				.findByCode(UtilConstant.NC_GRH_DECISION_STAGE,
						UtilConstant.DECISION_STAGE_LICENCIEMENT_CODE);

	}

	private void initDetail() {
		stageDto = null;
		dossierEmployeDto = null;
	}

	private void initSelectItemLists() {
		listeDecisionStage = findListeNcDecisionStage();
	}

	private void loadSituationDtos() {
		situationConcluant = this.serviceLocator.getSituationService()
				.findBySituationEntiteByCodeSituation(
						UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE,
						UtilConstants.SITUATION_STAGE_CONCLUANT_CODE);
		situationAProlanger = this.serviceLocator.getSituationService()
				.findBySituationEntiteByCodeSituation(
						UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE,
						UtilConstants.SITUATION_STAGE_PROLONGE_CODE);
		situationLicenciement = this.serviceLocator.getSituationService()
				.findBySituationEntiteByCodeSituation(
						UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE,
						UtilConstants.SITUATION_LICENCIE_CODE);
	}

	public void onSearchDossierEmploye() {
		try {
			loaddossierEmployeModel();
			initDetail();
		} catch (Exception e) {

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
			public List<DossierEmployeDto> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize,
						sortField, sortOrder);
				Collection<Integer> collection = new ArrayList<Integer>();

				collection.add(serviceLocator
						.getSituationService()
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE,
								UtilConstants.SITUATION_EN_STAGE_CODE).getId());
				searchMode.addFilter(new Filter("situationEntite.id",
						collection.toArray(), FilterMode.IN));
				dossierEmployeModel.setRowCount(serviceLocator
						.getDossierEmployeService().countAllByExample(
								employeSearchDto, searchMode));

				List<DossierEmployeDto> dtos = serviceLocator
						.getDossierEmployeService().findAllByExample(
								employeSearchDto, searchMode);
				return dtos;
			}
		};

	}

	public void onRowSelect(SelectEvent event) {
		try {
			dossierEmployeDto = (DossierEmployeDto) event.getObject();
			List<StageDto> stageDtos = dossierEmployeDto.getStageDtos();
			for (StageDto stage : stageDtos) {
				if (stage.getNomenclatureByDecisionStageDto().getId() == null) {
					stageDto = stage; // dossierEmployeDto.getStageDtos().get(0);
					break;
				}
			}
			retrieveHistory();
		} catch (Exception e) {

		}
	}

	public void onSave() {
		try {
			if (validateForm()) {
				beforeSave();
				// switch decision
				SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
				situationEntiteOccurrenceDto.setDateSituation(new Date());
				NomenclatureDto decision = stageDto
						.getNomenclatureByDecisionStageDto();
				if (decision.getId().equals(decisionConcluant.getId())) {
					dossierEmployeDto.setSituationEntiteDto(situationConcluant);
					situationEntiteOccurrenceDto
							.setIdSituation(situationConcluant.getId());
				} else if (decision.getId().equals(decisionAProlonger.getId())) {
					dossierEmployeDto
							.setSituationEntiteDto(situationAProlanger);
					situationEntiteOccurrenceDto
							.setIdSituation(situationAProlanger.getId());
				} else if (decision.getId()
						.equals(decisionLicenciement.getId())) {
					dossierEmployeDto
							.setSituationEntiteDto(situationLicenciement);
					situationEntiteOccurrenceDto
							.setIdSituation(situationLicenciement.getId());
				}

				dossierEmployeDto = this.serviceLocator
						.getDossierEmployeService().save(dossierEmployeDto);
				// FIXME
				situationEntiteOccurrenceDto.setIdOccurrence(dossierEmployeDto
						.getId().intValue());
				this.serviceLocator.getSituationService()
						.saveSituationOccurrence(situationEntiteOccurrenceDto);
				afterSave();
				CommonMessagesUtils.showSuccessSaveMessage();
			}
		} catch (Exception e) {

		}

	}

	private boolean validateForm() {
		boolean result = true;
		if (stageDto.getDateDebutReelle() != null
				&& stageDto.getDateDebutReelle().before(
						dossierEmployeDto.getDateCreation())) {
			GRHMessagesUtils
					.showErrorMessage("date_debut_reelle_stage_candidat_invalide");
			result = false;

		}
		if (stageDto.getDateDebutReelle() != null
				&& stageDto.getDateFinReelle() != null
				&& stageDto.getDateFinReelle().before(
						stageDto.getDateDebutReelle())) {
			GRHMessagesUtils
					.showErrorMessage("date_fin_reelle_stage_candidat_invalide");
			result = false;

		}
		if (stageDto.getNomenclatureByDecisionStageDto().getId()
				.equals(decisionAProlonger.getId())
				&& dossierEmployeDto.getStageDtos().size() > 2) {
			GRHMessagesUtils
					.showErrorMessage("prolongation_stage_une_seule_fois");
			result = false;

		}

		return result;
	}

	private void beforeSave() {
		if (dossierEmployeDto.getNomenclatureByTypeCompteDto().getId() == null
				|| dossierEmployeDto.getNomenclatureByTypeCompteDto().getId() == 0) {
			dossierEmployeDto.setNomenclatureByTypeCompteDto(null);
		}
		if (dossierEmployeDto.getNomenclatureByTypePermisDto().getId() == null
				|| dossierEmployeDto.getNomenclatureByTypePermisDto().getId() == 0) {
			dossierEmployeDto.setNomenclatureByTypePermisDto(null);
		}
		if (dossierEmployeDto.getRefStructureDto().getId() == null
				|| dossierEmployeDto.getRefStructureDto().getId() == 0) {
			dossierEmployeDto.setRefStructureDto(null);
		}

	}

	private void afterSave() {
		onSearchDossierEmploye();

	}

	public void onDecisionSelected(AjaxBehaviorEvent event) {

		if (stageDto.getNomenclatureByDecisionStageDto() != null
				&& stageDto.getNomenclatureByDecisionStageDto().getId() != null
				&& stageDto.getNomenclatureByDecisionStageDto().getId()
						.equals(decisionAProlonger.getId())) {
			setRenderedProlongation(true);
		} else {
			setRenderedProlongation(false);
		}

	}

	// hitory
	private void retrieveHistory() {
		dossierEmployeHistory = serviceLocator.getSituationService()
				.getEntityOccurrenceHistory(
						UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE,
						dossierEmployeDto.getId().intValue());
	}

	public StageDto getStageDto() {
		return stageDto;
	}

	public void setStageDto(StageDto stageDto) {
		this.stageDto = stageDto;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public List<SelectItem> getListeDecisionStage() {
		return listeDecisionStage;
	}

	public void setListeDecisionStage(List<SelectItem> listeDecisionStage) {
		this.listeDecisionStage = listeDecisionStage;
	}

	public boolean isRenderedProlongation() {
		return renderedProlongation;
	}

	public void setRenderedProlongation(boolean renderedProlongation) {
		this.renderedProlongation = renderedProlongation;
	}

	public SituationEntiteDto getSituationConcluant() {
		return situationConcluant;
	}

	public void setSituationConcluant(SituationEntiteDto situationConcluant) {
		this.situationConcluant = situationConcluant;
	}

	public SituationEntiteDto getSituationAProlanger() {
		return situationAProlanger;
	}

	public void setSituationAProlanger(SituationEntiteDto situationAProlanger) {
		this.situationAProlanger = situationAProlanger;
	}

	public SituationEntiteDto getSituationLicenciement() {
		return situationLicenciement;
	}

	public void setSituationLicenciement(
			SituationEntiteDto situationLicenciement) {
		this.situationLicenciement = situationLicenciement;
	}

	public NomenclatureDto getDecisionConcluant() {
		return decisionConcluant;
	}

	public void setDecisionConcluant(NomenclatureDto decisionConcluant) {
		this.decisionConcluant = decisionConcluant;
	}

	public NomenclatureDto getDecisionAProlonger() {
		return decisionAProlonger;
	}

	public void setDecisionAProlonger(NomenclatureDto decisionAProlonger) {
		this.decisionAProlonger = decisionAProlonger;
	}

	public NomenclatureDto getDecisionLicenciement() {
		return decisionLicenciement;
	}

	public void setDecisionLicenciement(NomenclatureDto decisionLicenciement) {
		this.decisionLicenciement = decisionLicenciement;
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

	public void setDossierEmployeModel(
			LazyDataModel<DossierEmployeDto> dossierEmployeModel) {
		this.dossierEmployeModel = dossierEmployeModel;
	}

	public List<SituationEntiteOccurrenceDto> getDossierEmployeHistory() {
		return dossierEmployeHistory;
	}

	public void setDossierEmployeHistory(
			List<SituationEntiteOccurrenceDto> dossierEmployeHistory) {
		this.dossierEmployeHistory = dossierEmployeHistory;
	}

}
