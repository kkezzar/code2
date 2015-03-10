package dz.gov.mesrs.sii.grh.web.jsf.bean.evaluation;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.EvaluationEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.EvaluationPeriodeDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

@ManagedBean(name = "contestationEvaluationMBean")
@ViewScoped
public class ContestationEvaluationMBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -1676254072820925664L;
	private EvaluationEmployeDto evaluationDto;
	private DossierEmployeDto employeDto;
	private DossierEmployeDto employeSearchDto;
	private LazyDataModel<DossierEmployeDto> employeDtos;
	private List<SelectItem> dateEvaluations;

	// Ajax
	@PostConstruct
	public void init() {
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean.getRefEtablissementDto());
		loadDossierEmploye();
		initDateEvaluations();
	}

	public void onSearchEmploye() {
		employeDto = null;
		evaluationDto = null;
		loadDossierEmploye();

	}

	public void onEmployeSelected(SelectEvent event) {
		employeDto = (DossierEmployeDto) event.getObject();
		evaluationDto = new EvaluationEmployeDto(new EvaluationPeriodeDto());
		evaluationDto.setEmployeDto(employeDto);
	}

	public void onEvaluationSelected() {
		EvaluationEmployeDto dto = serviceLocator.getEvaluationEmployeService()
				.findUniqueOrNoneByExample(evaluationDto);
		if (dto != null) {
			evaluationDto = dto;
		} else {
			GRHMessagesUtils.showWarningMessage(MSG_BUNDLE_EVALUATION, "employe_evaluation_date_no_result");
		}

	}

	public void onSave() {
		if (validForm()) {
			evaluationDto = serviceLocator.getEvaluationEmployeService().save(evaluationDto);
			CommonMessagesUtils.showSuccessSaveMessage();
		}

	}

	// private
	private boolean validForm() {
		boolean valid = true;
		Date dateContestation = evaluationDto.getDateContestation();
		Date dateEvaluation = evaluationDto.getPeriodeDto().getDateEvaluation();
		Date dateCommission = evaluationDto.getDateCommission();
		if(dateContestation.before(dateEvaluation)){
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_EVALUATION, "msg_date_contestation_date_evaluation_invalid");
			valid = false;
		}
		if(dateCommission.before(dateContestation)){
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_EVALUATION, "msg_date_commission_date_commission_invalid");
			valid = false;
		}
		return valid;
	}

	private void loadDossierEmploye() {
		employeDtos = new LazyDataModel<DossierEmployeDto>() {

			private static final long serialVersionUID = -2375650273365579017L;

			@Override
			public List<DossierEmployeDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				employeDtos.setRowCount(serviceLocator.getDossierEmployeService().countAllByExample(employeSearchDto));
				return serviceLocator.getDossierEmployeService().findAllByExample(employeSearchDto, searchMode);
			}

		};
	}

	private void initDateEvaluations() {
		dateEvaluations = new ArrayList<>();
		List<EvaluationPeriodeDto> dtos = serviceLocator.getEvaluationPeriodeService()
				.findAllEvalutionPeriodeNonCloture(null, null, sessionBean.getRefEtablissementDto().getId(),
						new SearchMode());
		if (dtos == null || dtos.size() == 0) {
			GRHMessagesUtils.showWarningMessage(MSG_BUNDLE_EVALUATION, "msg_all_evaluation_closed");
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			for (EvaluationPeriodeDto dto : dtos) {
				dateEvaluations.add(new SelectItem(dto.getId(), sdf.format(dto.getDateEvaluation())));
			}
		}

	}

	// Getters & setters
	public EvaluationEmployeDto getEvaluationDto() {
		return evaluationDto;
	}

	public void setEvaluationDto(EvaluationEmployeDto evaluationDto) {
		this.evaluationDto = evaluationDto;
	}

	public DossierEmployeDto getEmployeDto() {
		return employeDto;
	}

	public void setEmployeDto(DossierEmployeDto employeDto) {
		this.employeDto = employeDto;
	}

	public DossierEmployeDto getEmployeSearchDto() {
		return employeSearchDto;
	}

	public void setEmployeSearchDto(DossierEmployeDto employeSearchDto) {
		this.employeSearchDto = employeSearchDto;
	}

	public LazyDataModel<DossierEmployeDto> getEmployeDtos() {
		return employeDtos;
	}

	public void setEmployeDtos(LazyDataModel<DossierEmployeDto> employeDtos) {
		this.employeDtos = employeDtos;
	}

	public List<SelectItem> getDateEvaluations() {
		return dateEvaluations;
	}

	public void setDateEvaluations(List<SelectItem> dateEvaluations) {
		this.dateEvaluations = dateEvaluations;
	}
}
