package dz.gov.mesrs.sii.grh.web.jsf.bean.evaluation;

import java.io.Serializable;
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

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.EvaluationDetailDto;
import dz.gov.mesrs.sii.grh.business.model.dto.EvaluationEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.EvaluationPeriodeDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

@ManagedBean(name = "evaluationMBean")
@ViewScoped
public class EvaluationMBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -5286407015190866372L;
	private LazyDataModel<EvaluationPeriodeDto> evaluationPeriodeDtos;
	private LazyDataModel<EvaluationEmployeDto> evaluationEmployeDtos;
	private EvaluationEmployeDto evaluationEmployeSearchDto;
	private EvaluationPeriodeDto evaluationPeriodeSearchDto;
	private EvaluationEmployeDto evaluationEmployeDto;
	private EvaluationPeriodeDto evaluationPeriodeDto;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private DossierEmployeDto dossierEmployeDto;
	private int etablissementId;
	private List<SelectItem> niveauAppreciationList;
	private DossierEmployeDto employeSearchDto;

	@PostConstruct
	public void init() {
		etablissementId = getSessionBean().getCompte().getEtabId();
		initEvaluationPeriodeSearchDto();
		loadEvaluationPeriode();
		niveauAppreciationList = findNiveauAppreciationList();
		initEmployeSearchDto();
		loadDossierEmploye();

	}

	// Ajax
	public void onEvaluationPeriodeSelected(SelectEvent event) {
		evaluationPeriodeDto = (EvaluationPeriodeDto) event.getObject();
		evaluationEmployeSearchDto = new EvaluationEmployeDto(evaluationPeriodeDto);
		loadEvaluationEmployes();
		evaluationEmployeDto = null;

	}

	public void onEvaluationEmployeSelected(SelectEvent event) {
		evaluationEmployeDto = (EvaluationEmployeDto) event.getObject();
		for (EvaluationDetailDto detailDto : evaluationEmployeDto.getDetailDtos()) {
			if (detailDto.getNiveauDto() == null) {
				detailDto.setNiveauDto(new NomenclatureDto());
			}
		}
	}

	public void onNewEvaluationPeriode() {
		evaluationPeriodeDto = new EvaluationPeriodeDto(etablissementId);
		evaluationEmployeSearchDto = new EvaluationEmployeDto(evaluationPeriodeDto);
		evaluationEmployeDto = null;
	}

	public void onNewEmployeEvaluation() {
		evaluationEmployeDto = new EvaluationEmployeDto(evaluationPeriodeDto);
		List<NomenclatureDto> critereDtos = serviceLocator.getNomenclatureService().findByName(
				UtilConstant.NC_GRH_CRITERE_EVALUATION);
		for (NomenclatureDto critereDto : critereDtos) {
			evaluationEmployeDto.addEvaluationDetailDto(new EvaluationDetailDto(critereDto, new NomenclatureDto()));

		}

	}

	public void onSaveEmployeEvaluation() {
		evaluationEmployeDto = serviceLocator.getEvaluationEmployeService().save(evaluationEmployeDto);
		for (EvaluationDetailDto dto : evaluationEmployeDto.getDetailDtos()) {
			if (dto.getNiveauDto() == null) {
				dto.setNiveauDto(new NomenclatureDto());
			}
		}
		loadEvaluationEmployes();
		CommonMessagesUtils.showSuccessSaveMessage();

	}

	public void onSavePeriodeEvaluation() {
		if (validEvaluationPeriodeForm()) {
			evaluationPeriodeDto = serviceLocator.getEvaluationPeriodeService().saveEvaluationPeriode(
					evaluationPeriodeDto);
			evaluationEmployeSearchDto = new EvaluationEmployeDto(evaluationPeriodeDto);
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}

	public void onCloturerPeriodeEvaluation() {
		serviceLocator.getEvaluationPeriodeService().cloturerEvaluationPeriode(
				evaluationPeriodeDto);
		CommonMessagesUtils.showSuccessSaveMessage();
		evaluationPeriodeDto = null;
	}

	public void onRowSelect(SelectEvent event) {
		dossierEmployeDto = (DossierEmployeDto) event.getObject();
		evaluationEmployeDto.setEmployeDto(dossierEmployeDto);
		EvaluationEmployeDto dto = serviceLocator.getEvaluationEmployeService().findUniqueOrNoneByExample(
				evaluationEmployeDto);
		if (dto != null) {
			evaluationEmployeDto = dto;
		}
		// TODO attention quand il a deja une evaluation pour cette periode
	}

	public void onSearchEvaluationPeriode() {
		loadEvaluationPeriode();
		// TODO verifier que les dates de recherche sont consistantes
	}

	public void onSearchDossierEmploye() {
		loadDossierEmploye();
	}

	public void onSearchEvaluationEmployes() {

	}

	// private
	private boolean validEvaluationPeriodeForm() {
		boolean valid = true;
		Date dateDebutPeriode = evaluationPeriodeDto.getDateDebutPeriode();
		Date dateFinPeriode = evaluationPeriodeDto.getDateFinPeriode();
		if(dateFinPeriode.before(dateDebutPeriode)){
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_EVALUATION, "msg_date_debut_periode_fin_periode_invalid");
			valid = false;
		}
		
		EvaluationPeriodeDto lastPeriode = serviceLocator.getEvaluationPeriodeService().findLastPeriodeEvaluation(etablissementId);
		if(lastPeriode !=null){
			Date dateLastEvaluationFinPeriode = lastPeriode.getDateFinPeriode();
			if(dateDebutPeriode.before(dateLastEvaluationFinPeriode)){
				GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_EVALUATION, "msg_date_debut_periode_date_fin_periode_derniere_evaluation_invalid");
				valid = false;
			}	
		}
		
		
				
		return valid;
	}

	private void loadEvaluationPeriode() {
		evaluationPeriodeDtos = new LazyDataModel<EvaluationPeriodeDto>() {
			private static final long serialVersionUID = -8713718832105090114L;

			@Override
			public List<EvaluationPeriodeDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				evaluationPeriodeDtos.setRowCount(serviceLocator.getEvaluationPeriodeService()
						.countAllEvalutionPeriodeNonCloture(evaluationPeriodeSearchDto.getDateDebutPeriode(),
								evaluationPeriodeSearchDto.getDateFinPeriode(), etablissementId));
				return serviceLocator.getEvaluationPeriodeService().findAllEvalutionPeriodeNonCloture(
						evaluationPeriodeSearchDto.getDateDebutPeriode(),
						evaluationPeriodeSearchDto.getDateFinPeriode(), etablissementId, searchMode);
			}

		};
	}

	private void loadEvaluationEmployes() {
		evaluationEmployeDtos = new LazyDataModel<EvaluationEmployeDto>() {

			private static final long serialVersionUID = 6215562316369696228L;

			@Override
			public List<EvaluationEmployeDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				evaluationEmployeDtos.setRowCount(serviceLocator.getEvaluationEmployeService().countAllByExample(
						evaluationEmployeSearchDto));
				return serviceLocator.getEvaluationEmployeService().findAllByExample(evaluationEmployeSearchDto,
						searchMode);
			}

		};

	}

	private void loadDossierEmploye() {
		dossierEmployeModel = new LazyDataModel<DossierEmployeDto>() {
			private static final long serialVersionUID = -5215052844410786053L;

			@Override
			public List<DossierEmployeDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				dossierEmployeModel.setRowCount(serviceLocator.getDossierEmployeService().countAllActif(
						employeSearchDto));
				List<DossierEmployeDto> dtos = serviceLocator.getDossierEmployeService().findAllActif(employeSearchDto,
						searchMode);
				return dtos;
			}

		};
	}

	private void initEmployeSearchDto() {
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean.getRefEtablissementDto());
	}

	private void initEvaluationPeriodeSearchDto() {
		evaluationPeriodeSearchDto = new EvaluationPeriodeDto(etablissementId);

	}

	// Getters and Setters

	public LazyDataModel<EvaluationPeriodeDto> getEvaluationPeriodeDtos() {
		return evaluationPeriodeDtos;
	}

	public void setEvaluationPeriodeDtos(LazyDataModel<EvaluationPeriodeDto> evaluationPeriodeDtos) {
		this.evaluationPeriodeDtos = evaluationPeriodeDtos;
	}

	public LazyDataModel<EvaluationEmployeDto> getEvaluationEmployeDtos() {
		return evaluationEmployeDtos;
	}

	public void setEvaluationEmployeDtos(LazyDataModel<EvaluationEmployeDto> evaluationEmployeDtos) {
		this.evaluationEmployeDtos = evaluationEmployeDtos;
	}

	public EvaluationEmployeDto getEvaluationEmployeSearchDto() {
		return evaluationEmployeSearchDto;
	}

	public void setEvaluationEmployeSearchDto(EvaluationEmployeDto evaluationEmployeSearchDto) {
		this.evaluationEmployeSearchDto = evaluationEmployeSearchDto;
	}

	public EvaluationPeriodeDto getEvaluationPeriodeSearchDto() {
		return evaluationPeriodeSearchDto;
	}

	public void setEvaluationPeriodeSearchDto(EvaluationPeriodeDto evaluationPeriodeSearchDto) {
		this.evaluationPeriodeSearchDto = evaluationPeriodeSearchDto;
	}

	public EvaluationEmployeDto getEvaluationEmployeDto() {
		return evaluationEmployeDto;
	}

	public void setEvaluationEmployeDto(EvaluationEmployeDto evaluationEmployeDto) {
		this.evaluationEmployeDto = evaluationEmployeDto;
	}

	public EvaluationPeriodeDto getEvaluationPeriodeDto() {
		return evaluationPeriodeDto;
	}

	public void setEvaluationPeriodeDto(EvaluationPeriodeDto evaluationPeriodeDto) {
		this.evaluationPeriodeDto = evaluationPeriodeDto;
	}

	public LazyDataModel<DossierEmployeDto> getDossierEmployeModel() {
		return dossierEmployeModel;
	}

	public void setDossierEmployeModel(LazyDataModel<DossierEmployeDto> dossierEmployeModel) {
		this.dossierEmployeModel = dossierEmployeModel;
	}

	public DossierEmployeDto getEmployeSearchDto() {
		return employeSearchDto;
	}

	public void setEmployeSearchDto(DossierEmployeDto employeSearchDto) {
		this.employeSearchDto = employeSearchDto;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public List<SelectItem> getNiveauAppreciationList() {
		return this.niveauAppreciationList;
	}

	public void setNiveauAppreciationList(List<SelectItem> niveauAppreciationList) {
		this.niveauAppreciationList = niveauAppreciationList;
	}

}
