package dz.gov.mesrs.sii.grh.web.jsf.bean.recrutement;

import java.util.ArrayList;
import java.util.Collection;
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
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.StageDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

/**
 * 
 * @author BELDI Jamel on : 15 déc. 2014 16:24:37
 */
@ManagedBean(name = "periodeStageMBean")
@ViewScoped
public class PeriodeStageMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	private StageDto stageDto;
	private DossierEmployeDto dossierEmployeDto;
	private DossierEmployeDto employeSearchDto;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private List<SituationEntiteOccurrenceDto> dossierEmployeHistory;

	public PeriodeStageMBean() {

	}

	@PostConstruct
	public void init() {
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean
				.getRefEtablissementDto());
		onSearchDossierEmploye();
	}

	private void initDetail() {
		stageDto = null;
		dossierEmployeDto = null;
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
								UtilConstants.SITUATION_CREEE_CODE).getId());
				collection.add(serviceLocator
						.getSituationService()
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE,
								UtilConstants.SITUATION_STAGE_PROLONGE_CODE)
						.getId());
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
			stageDto = new StageDto();
			retrieveHistory();
		} catch (Exception e) {

		}
	}

	public void onSave() {
		try {
			if (validateForm()) {
				beforeSave();
				SituationEntiteDto situationEntiteDto = this.serviceLocator
						.getSituationService()
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE,
								UtilConstants.SITUATION_EN_STAGE_CODE);
				dossierEmployeDto.setSituationEntiteDto(situationEntiteDto);
				dossierEmployeDto = this.serviceLocator
						.getDossierEmployeService().save(dossierEmployeDto);
				SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
				situationEntiteOccurrenceDto.setDateSituation(new Date());
				// FIXME l'id occurence doit etre un Long
				situationEntiteOccurrenceDto.setIdOccurrence(dossierEmployeDto
						.getId().intValue());
				situationEntiteOccurrenceDto.setIdSituation(situationEntiteDto
						.getId());
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
		if (stageDto.getDateDebut() != null
				&& stageDto.getDateDebut().before(
						dossierEmployeDto.getDateCreation())) {
			GRHMessagesUtils
					.showErrorMessage("date_debut_stage_candidat_invalide");
			result = false;

		}
		if (stageDto.getDateDebut() != null && stageDto.getDateFin() != null
				&& stageDto.getDateFin().before(stageDto.getDateDebut())) {
			GRHMessagesUtils
					.showErrorMessage("date_fin_stage_candidat_invalide");
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
		stageDto.setDossierEmployeDto(dossierEmployeDto);
		stageDto.setNomenclatureByDecisionStageDto(null);
		stageDto.setDateDebutReelle(stageDto.getDateDebut());
		stageDto.setDateFinReelle(stageDto.getDateFin());
		List<StageDto> stages = dossierEmployeDto.getStageDtos();

		stages.add(stageDto);
	}

	private void afterSave() {
		onSearchDossierEmploye();
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
