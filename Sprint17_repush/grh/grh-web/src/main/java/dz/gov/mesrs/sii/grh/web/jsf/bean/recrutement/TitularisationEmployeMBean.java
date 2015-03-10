package dz.gov.mesrs.sii.grh.web.jsf.bean.recrutement;

import java.util.ArrayList;
import java.util.Collection;
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

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.CarriereDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CategorieProfessionnelleDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CorpsDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DetailRecrutementDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GradeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GrilleIndiciaireDto;
import dz.gov.mesrs.sii.grh.business.model.dto.StageDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * 
 * @author BELDI Jamel on : 15 déc. 2014 16:24:37
 */
@ManagedBean(name = "titularisationEmployeMBean")
@ViewScoped
public class TitularisationEmployeMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	private DossierEmployeDto employeSearchDto;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private DossierEmployeDto dossierEmployeDto;
	private CarriereDto carriereDto;
	private List<SelectItem> listeCorps;
	private List<SelectItem> listeGrade;
	private List<SelectItem> listeGrilleIndiciaire;
	private StageDto stageDto;
	private NomenclatureDto decisionConcluant;
	private List<SituationEntiteOccurrenceDto> dossierEmployeHistory;

	public TitularisationEmployeMBean() {

	}

	@PostConstruct
	public void init() {
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean
				.getRefEtablissementDto());
		onSearchDossierEmploye();
		initSelectItemLists();
	}

	private void initDetail() {
		dossierEmployeDto = null;
		carriereDto = null;
		stageDto = null;
	}

	private void initSelectItemLists() {
		listeCorps = findListeCorps();
		decisionConcluant = this.serviceLocator.getNomenclatureService()
				.findByCode(UtilConstant.NC_GRH_DECISION_STAGE,
						UtilConstant.DECISION_STAGE_CONCLUANT_CODE);
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
								UtilConstants.SITUATION_STAGE_CONCLUANT_CODE)
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
			initCarriereDto();
			List<StageDto> stageDtos = dossierEmployeDto.getStageDtos();
			for (StageDto stage : stageDtos) {
				if (stage.getNomenclatureByDecisionStageDto().getId()
						.equals(decisionConcluant.getId())) {
					stageDto = stage;
					break;
				}
			}
			retrieveHistory();
		} catch (Exception e) {

		}
	}

	public void onCorpsSelected(AjaxBehaviorEvent event) {
		carriereDto.setGradeDto(new GradeDto());
		carriereDto.setGrilleIndiciaireDto(new GrilleIndiciaireDto());
		carriereDto
				.setCategorieProfessionnelleDto(new CategorieProfessionnelleDto());
		carriereDto.setIndice(null);
		listeGrade = findListeGrade(carriereDto.getCorpsDto().getId());

	}

	public void onGradeSelected(AjaxBehaviorEvent event) {
		GradeDto gradeDto = this.serviceLocator.getGradeService().findById(
				carriereDto.getGradeDto().getId());
		carriereDto.setGradeDto(gradeDto);
		carriereDto.setCategorieProfessionnelleDto(gradeDto
				.getCategorieProfessionnelleDto());
		carriereDto.setGrilleIndiciaireDto(new GrilleIndiciaireDto());
		carriereDto.setIndice(null);
		listeGrilleIndiciaire = findListeGrilleIndiciaire(gradeDto
				.getCategorieProfessionnelleDto().getId());
	}

	public void onGrilleIndiciareSelected(AjaxBehaviorEvent event) {
		GrilleIndiciaireDto grilleIndiciaireDto = this.serviceLocator
				.getGrilleIndiciaireService().findById(
						carriereDto.getGrilleIndiciaireDto().getId());
		carriereDto.setGrilleIndiciaireDto(grilleIndiciaireDto);
		carriereDto.setIndice(grilleIndiciaireDto.getIndice().intValue());

	}

	public void onSave() {
		try {
			if (validateForm()) {
				beforeSave();
				dossierEmployeDto = this.serviceLocator
						.getDossierEmployeService().saveTitularisation(
								dossierEmployeDto.getDateTitularisation(),
								carriereDto);
				afterSave();
				CommonMessagesUtils.showSuccessSaveMessage();
			}
		} catch (Exception e) {

		}

	}

	private boolean validateForm() {
		boolean result = true;
		if (dossierEmployeDto.getDateTitularisation() != null
				&& dossierEmployeDto.getDateTitularisation().before(
						stageDto.getDateFinReelle())) {
			GRHMessagesUtils
					.showErrorMessage("date_titularisation_candidat_invalide");
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
		List<CarriereDto> carriereDtos = dossierEmployeDto.getCarriereDtos();
		if (carriereDtos == null) {
			carriereDtos = new ArrayList<>();
		}
		dossierEmployeDto.setTitularise(true);
		carriereDto.setDossierEmployeDto(dossierEmployeDto);
		carriereDto.setDateEffet(dossierEmployeDto.getDateTitularisation());
		carriereDto
				.setDateChangement(dossierEmployeDto.getDateTitularisation());
		carriereDto
				.setDateEffetCorps(dossierEmployeDto.getDateTitularisation());
		carriereDto
				.setDateEffetGrade(dossierEmployeDto.getDateTitularisation());
		carriereDto.setDateEffetEchelon(dossierEmployeDto
				.getDateTitularisation());
		carriereDtos.add(carriereDto);

	}

	private void afterSave() {
		onSearchDossierEmploye();

	}

	// Titularisation par default
	private void initCarriereDto() {
		carriereDto = new CarriereDto();
		carriereDto.setGrilleIndiciaireDto(new GrilleIndiciaireDto());
		if (dossierEmployeDto.getCandidatEmployeDto() != null
				&& dossierEmployeDto.getCandidatEmployeDto()
						.getDetailRecrutementDto() != null) {
			DetailRecrutementDto detailRecrutementDto = dossierEmployeDto
					.getCandidatEmployeDto().getDetailRecrutementDto();
			carriereDto.setCorpsDto(detailRecrutementDto.getCorpsDto());
			carriereDto.setGradeDto(detailRecrutementDto.getGradeDto());
			carriereDto.setCategorieProfessionnelleDto(detailRecrutementDto
					.getCategorieProfessionnelleDto());
			listeGrade = findListeGrade(carriereDto.getCorpsDto().getId());
			listeGrilleIndiciaire = findListeGrilleIndiciaire(carriereDto
					.getCategorieProfessionnelleDto().getId());
		} else {
			carriereDto.setCorpsDto(new CorpsDto());
			carriereDto.setGradeDto(new GradeDto());
			carriereDto
					.setCategorieProfessionnelleDto(new CategorieProfessionnelleDto());
		}
	}

	// hitory
	private void retrieveHistory() {
		dossierEmployeHistory = serviceLocator.getSituationService()
				.getEntityOccurrenceHistory(
						UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE,
						dossierEmployeDto.getId().intValue());
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

	public CarriereDto getCarriereDto() {
		return carriereDto;
	}

	public void setCarriereDto(CarriereDto carriereDto) {
		this.carriereDto = carriereDto;
	}

	public List<SelectItem> getListeCorps() {
		return listeCorps;
	}

	public void setListeCorps(List<SelectItem> listeCorps) {
		this.listeCorps = listeCorps;
	}

	public List<SelectItem> getListeGrade() {
		return listeGrade;
	}

	public void setListeGrade(List<SelectItem> listeGrade) {
		this.listeGrade = listeGrade;
	}

	public List<SelectItem> getListeGrilleIndiciaire() {
		return listeGrilleIndiciaire;
	}

	public void setListeGrilleIndiciaire(List<SelectItem> listeGrilleIndiciaire) {
		this.listeGrilleIndiciaire = listeGrilleIndiciaire;
	}

	public StageDto getStageDto() {
		return stageDto;
	}

	public void setStageDto(StageDto stageDto) {
		this.stageDto = stageDto;
	}

	public NomenclatureDto getDecisionConcluant() {
		return decisionConcluant;
	}

	public void setDecisionConcluant(NomenclatureDto decisionConcluant) {
		this.decisionConcluant = decisionConcluant;
	}

	public List<SituationEntiteOccurrenceDto> getDossierEmployeHistory() {
		return dossierEmployeHistory;
	}

	public void setDossierEmployeHistory(
			List<SituationEntiteOccurrenceDto> dossierEmployeHistory) {
		this.dossierEmployeHistory = dossierEmployeHistory;
	}

}
