package dz.gov.mesrs.sii.grh.web.jsf.bean.absence;

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
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.AbsenceDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

@ManagedBean(name = "absenceMBean")
@ViewScoped
public class AbsenceMBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -3882130291290449361L;
	private int etablissementId;
	private String searchKeyWords;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private LazyDataModel<AbsenceDto> absenceDtos;
	private DossierEmployeDto dossierEmployeDto;
	private DossierEmployeDto employeSearchDto;
	private AbsenceDto absenceDto;
	private List<SelectItem> typeAbsences;

	// init
	@PostConstruct
	public void init() {
		etablissementId = getSessionBean().getCompte().getEtabId();
		typeAbsences = findListeTypeAbsence();
		onSearch();

	}

	// Ajax
	public void onNew() {
		absenceDto = new AbsenceDto(new NomenclatureDto());
		dossierEmployeDto = new DossierEmployeDto();
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean.getRefEtablissementDto());
	}

	public void onRemove() {
		serviceLocator.getAbsenceService().remove(absenceDto);
		initDtos();
		CommonMessagesUtils.showSuccessDeleteMessage();

	}

	public void onSave() {
		if (validForm()) {
			absenceDto = serviceLocator.getAbsenceService().enregisterAbsence(absenceDto);
			CommonMessagesUtils.showSuccessSaveMessage();
			if(absenceDto.getNomenclatureDto()==null){
				absenceDto.setNomenclatureDto(new NomenclatureDto());
			}
		}

	}

	public void onConfirmer() {
		if (validForm()) {
			serviceLocator.getAbsenceService().confirmerAbsence(absenceDto);
			initDtos();
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}

	public void onSearch() {
		initDtos();
		loadAbsences();

	}

	public void onInit() {
		// TODO
	}

	public void onSearchDossierEmploye() {
		dossierEmployeDto = null;
		loadDossierEmployes();
	}

	// Absence
	public void onRowSelected(SelectEvent event) {
		absenceDto = (AbsenceDto) event.getObject();
		dossierEmployeDto = absenceDto.getDossierEmployeDto();
		if(absenceDto.getNomenclatureDto()==null){
			absenceDto.setNomenclatureDto(new NomenclatureDto());
		}
	}

	// Employe
	public void onRowSelect(SelectEvent event) {
		dossierEmployeDto = (DossierEmployeDto) event.getObject();
		absenceDto.setDossierEmployeDto(dossierEmployeDto);
	}
	
	public void calculJourAbsence(SelectEvent event) {
		if (absenceDto.getDateDebut() != null && absenceDto.getDateFin() != null) {
			double nbJours = serviceLocator.getRefJourOuvreService().calculateWorkingDaysBetweenTwoDate(
					absenceDto.getDateDebut(), absenceDto.getDateFin());
			absenceDto.setNbreJours(nbJours);
		}
	}

	// private
	private boolean validForm() {
		boolean valid = true;
		if (!validDateDebutAbsence()) {
			valid = false;
		}
		if (!validDateFinAbsence()) {
			valid = false;
		}
		if (!validHeureDebutAbsence()) {
			valid = false;
		}
		if (!validEmployeAbsence()) {
			valid = false;
		}
		if (!validEmployeAutorisationAbsence()) {
			valid = false;
		}
		if (!validEmployeConge()) {
			valid = false;
		}

		return valid;
	}

	private boolean validEmployeConge() {
		Long employeId = dossierEmployeDto.getId();
		Date dateDebut = absenceDto.getDateDebut();
		Date dateFin = absenceDto.getDateFin();
		boolean isConges = serviceLocator.getCongeEmployeService().isEmployeConges(employeId, dateDebut, dateFin);
		if (isConges) {
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_ABSENCE, "msg_employe_already_conges");
			return false;
		} else {
			return true;
		}
	}

	private boolean validEmployeAutorisationAbsence() {
		Long employeId = dossierEmployeDto.getId();
		Date dateDebut = absenceDto.getDateDebut();
		Date dateFin = absenceDto.getDateFin();
		boolean isAutoriseAbsence = serviceLocator.getAutorisationAbsenceService().isEmployeAutoriseAbsence(employeId,
				dateDebut, dateFin);
		if (isAutoriseAbsence) {
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_ABSENCE, "msg_date_employe_already_autorise_absence");
			return false;
		} else {
			return true;
		}
	}

	private boolean validEmployeAbsence() {
		Long employeId = dossierEmployeDto.getId();
		Date dateDebut = absenceDto.getDateDebut();
		Date dateFin = absenceDto.getDateFin();
		boolean isAbsent = serviceLocator.getAbsenceService().isEmployeAbsent(employeId, dateDebut, dateFin);
		if (isAbsent) {
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_ABSENCE, "msg_employe_already_absent");
			return false;
		} else {
			return true;
		}
	}

	private boolean validHeureDebutAbsence() {
		Date heureDebut = absenceDto.getHeureDebut();
		Date heureFin = absenceDto.getHeureFin();
		if(heureDebut == null && heureFin == null){
			return true;
		}
		if((heureDebut == null && heureFin != null)||(heureFin == null &&  heureDebut != null) || heureFin.before(heureDebut)){
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_ABSENCE, "msg_heure_debut_absence_heure_fin_absence_invalid");
			return false;
		}else{
			return true;
		}
	}

	private void initDtos() {
		absenceDto = null;
		dossierEmployeDto = null;
		// FIXME pourquoi la recherche se lance apres un confirm ??? MEETRE LAID
		// ETABLISEEMNT EN TOUT CAS
		// employeSearchDto = new DossierEmployeDto();
	}

	private boolean validDateFinAbsence() {
		Date dateDebutAbsence = absenceDto.getDateDebut();
		Date dateFinAbsence = absenceDto.getDateFin();
		if (dateFinAbsence.before(dateDebutAbsence)) {
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_ABSENCE, "msg_date_fin_date_debut_invalid");
			return false;
		} else {
			return true;
		}

	}

	private boolean validDateDebutAbsence() {
		Date dateDebutAbsence = absenceDto.getDateDebut();
		Date dateRecrutement = dossierEmployeDto.getDateInstallation();
		if (dateDebutAbsence.before(dateRecrutement)) {
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_ABSENCE, "msg_date_debut_absence_date_recrutement_invalid");
			return false;
		} else {
			return true;
		}

	}

	private void loadDossierEmployes() {
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

	private void loadAbsences() {
		absenceDtos = new LazyDataModel<AbsenceDto>() {
			private static final long serialVersionUID = -8750645717985976378L;

			@Override
			public List<AbsenceDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				absenceDtos.setRowCount(serviceLocator.getAbsenceService().countAllAbsencesEnregistrees(
						etablissementId, searchKeyWords));
				return serviceLocator.getAbsenceService().findAllAbsencesEnregistrees(etablissementId, searchMode,
						searchKeyWords);
			}

		};

	}

	// getter/setter
	public LazyDataModel<DossierEmployeDto> getDossierEmployeModel() {
		return dossierEmployeModel;
	}

	public void setDossierEmployeModel(LazyDataModel<DossierEmployeDto> dossierEmployeModel) {
		this.dossierEmployeModel = dossierEmployeModel;
	}

	public LazyDataModel<AbsenceDto> getAbsenceDtos() {
		return absenceDtos;
	}

	public void setAbsenceDtos(LazyDataModel<AbsenceDto> absenceDtos) {
		this.absenceDtos = absenceDtos;
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

	public AbsenceDto getAbsenceDto() {
		return absenceDto;
	}

	public void setAbsenceDto(AbsenceDto absenceDto) {
		this.absenceDto = absenceDto;
	}

	public List<SelectItem> getTypeAbsences() {
		return typeAbsences;
	}

	public void setTypeAbsences(List<SelectItem> typeAbsences) {
		this.typeAbsences = typeAbsences;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

}
