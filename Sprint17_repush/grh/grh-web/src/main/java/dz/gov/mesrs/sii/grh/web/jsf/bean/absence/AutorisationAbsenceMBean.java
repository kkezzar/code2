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
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.AutorisationAbsenceDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

@ManagedBean(name = "autorisationAbsenceMBean")
@ViewScoped
public class AutorisationAbsenceMBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -3882130291290449361L;
	private String searchKeyWords;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private LazyDataModel<AutorisationAbsenceDto> autorisationAbsenceDtos;
	private DossierEmployeDto dossierEmployeDto;
	private DossierEmployeDto employeSearchDto;
	private AutorisationAbsenceDto autorisationAbsenceDto;
	private List<SelectItem> frequences;
	private int etablissementId;
	private Integer frequenceHebdomadaireNcId;
	private boolean displayJourSemaine;

	// init
	@PostConstruct
	public void init() {
		etablissementId = getSessionBean().getCompte().getEtabId();
		frequences = findListeFrequence();
		onSearch();
		NomenclatureDto ncDto = serviceLocator.getNomenclatureService().findByCode(
				UtilConstant.NC_GRH_FREQUENCE_ABSENCE, UtilConstant.NC_GRH_FREQUENCE_ABSENCE_HEBDOMADAIRE_VALUE);
		frequenceHebdomadaireNcId = ncDto.getId();
	}

	// Ajax
	public void onNew() {
		autorisationAbsenceDto = new AutorisationAbsenceDto(new NomenclatureDto());
		dossierEmployeDto = new DossierEmployeDto();
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean.getRefEtablissementDto());
		displayJourSemaine = false;
	}

	public void onRemove() {
		serviceLocator.getAutorisationAbsenceService().remove(autorisationAbsenceDto);
		initDtos();
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	public void onSave() {
		if (validEnregistrementForm()) {
			autorisationAbsenceDto = serviceLocator.getAutorisationAbsenceService().enregisterDemande(
					autorisationAbsenceDto);
			if (autorisationAbsenceDto.getNomenclatureDto() == null) {
				autorisationAbsenceDto.setNomenclatureDto(new NomenclatureDto());
			}
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}

	public void onSaveResultat() {
		if (validResultatForm()) {
			serviceLocator.getAutorisationAbsenceService().enregisterResultat(autorisationAbsenceDto);
			initDtos();
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}

	public void onSearch() {
		initDtos();
		loadDemandes();
	}

	private void initDtos() {
		autorisationAbsenceDto = null;
		dossierEmployeDto = null;
		employeSearchDto = null;
	}

	public void onSearchDossierEmploye() {
		dossierEmployeDto = null;
		loadDossierEmploye();
	}

	public void onInit() {
		// TODO
	}

	// Demande autorisation absence
	public void onRowSelected(SelectEvent event) {
		autorisationAbsenceDto = (AutorisationAbsenceDto) event.getObject();
		dossierEmployeDto = autorisationAbsenceDto.getDossierEmployeDto();
		if(autorisationAbsenceDto.getNomenclatureDto()!=null){
			displayJourSemaine(autorisationAbsenceDto.getNomenclatureDto().getId());	
		}else{
			autorisationAbsenceDto.setNomenclatureDto(new NomenclatureDto());
		}
		displayJourSemaine(autorisationAbsenceDto.getNomenclatureDto().getId());

	}

	// Employe
	public void onRowSelect(SelectEvent event) {
		dossierEmployeDto = (DossierEmployeDto) event.getObject();
		autorisationAbsenceDto.setDossierEmployeDto(dossierEmployeDto);
	}

	public void onFrequenceSelected() {
		Integer selectedFrequenceId = autorisationAbsenceDto.getNomenclatureDto().getId();
		displayJourSemaine(selectedFrequenceId);
	}

	// private

	private void displayJourSemaine(Integer selectedFrequenceId) {
		if (selectedFrequenceId != null && selectedFrequenceId.equals(frequenceHebdomadaireNcId)) {
			displayJourSemaine = true;
		} else {
			displayJourSemaine = false;
		}
	}

	private boolean validResultatForm() {
		return validDateResultat();
	}

	private boolean validEnregistrementForm() {
		boolean valid = true;
		if (!validDateDemandeDateRecrutement()) {
			valid = false;
		}

		if (!validDateDemandeDateDebut()) {
			valid = false;
		}

		if (!validDateDebutDateFin()) {
			valid = false;
		}

		if (!validFrequence()) {
			valid = false;
		}
		
		if (!validHeureDebutFin()) {
			valid = false;
		}

		return valid;
	}

	private boolean validHeureDebutFin() {
		Date heureDebut = autorisationAbsenceDto.getHeureDebut();
		Date heureFin = autorisationAbsenceDto.getHeureFin();
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

	private boolean validDateDebutDateFin() {
		boolean valid = true;
		Date dateFin = autorisationAbsenceDto.getDateFin();
		Date dateDebut = autorisationAbsenceDto.getDateDebut();
		if (dateFin.before(dateDebut)) {
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_ABSENCE, "msg_date_debut_date_demande_invalid");
			valid = false;
		}
		return valid;
	}

	private boolean validDateDemandeDateDebut() {
		boolean valid = true;
		Date dateDemande = autorisationAbsenceDto.getDateDemande();
		Date dateDebut = autorisationAbsenceDto.getDateDebut();
		if (dateDemande.after(dateDebut)) {
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_ABSENCE, "msg_date_debut_date_demande_invalid");
			valid = false;
		}
		return valid;
	}

	private boolean validDateDemandeDateRecrutement() {
		boolean valid = true;
		Date dateInstallation = autorisationAbsenceDto.getDossierEmployeDto().getDateInstallation();
		Date dateDemande = autorisationAbsenceDto.getDateDemande();
		if (dateDemande.before(dateInstallation)) {
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_ABSENCE, "msg_date_demande_date_recrutement_invalid");
			valid = false;
		}
		return valid;
	}

	private boolean validFrequence() {
		Integer selectedFrequence = autorisationAbsenceDto.getNomenclatureDto().getId();
		if (frequenceHebdomadaireNcId.equals(selectedFrequence)) {
			if (!isSelectedAtLeastOneDay()) {
				GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_ABSENCE, "msg_frequence_jours_invalid");
				return false;
			}
		}
		return true;
	}

	private boolean isSelectedAtLeastOneDay() {
		return autorisationAbsenceDto.getDimanche() || autorisationAbsenceDto.getLundi()
				|| autorisationAbsenceDto.getMardi() || autorisationAbsenceDto.getMercredi()
				|| autorisationAbsenceDto.getJeudi();
	}

	private boolean validDateResultat() {
		Date dateResultat = autorisationAbsenceDto.getDateResultat();
		Date dateDemande = autorisationAbsenceDto.getDateDemande();
		if (dateResultat.before(dateDemande)) {
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_ABSENCE, "msg_date_resultat_autorisation_invalid");
			return false;
		} else {
			return true;
		}

	}

	private void loadDossierEmploye() {
		dossierEmployeModel = new LazyDataModel<DossierEmployeDto>() {

			private static final long serialVersionUID = 2324791002192828521L;

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

	private void loadDemandes() {
		autorisationAbsenceDtos = new LazyDataModel<AutorisationAbsenceDto>() {

			private static final long serialVersionUID = 7524191666876260189L;

			@Override
			public List<AutorisationAbsenceDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				autorisationAbsenceDtos.setRowCount(serviceLocator.getAutorisationAbsenceService()
						.countAllPendingRequest(etablissementId, searchKeyWords));
				return serviceLocator.getAutorisationAbsenceService().findAllPendingRequest(etablissementId,
						searchMode, searchKeyWords);
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

	public LazyDataModel<AutorisationAbsenceDto> getAutorisationAbsenceDtos() {
		return autorisationAbsenceDtos;
	}

	public void setAutorisationAbsenceDtos(LazyDataModel<AutorisationAbsenceDto> autorisationAbsenceDtos) {
		this.autorisationAbsenceDtos = autorisationAbsenceDtos;
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

	public AutorisationAbsenceDto getAutorisationAbsenceDto() {
		return autorisationAbsenceDto;
	}

	public void setAutorisationAbsenceDto(AutorisationAbsenceDto autorisationAbsenceDto) {
		this.autorisationAbsenceDto = autorisationAbsenceDto;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public List<SelectItem> getFrequences() {
		return frequences;
	}

	public void setFrequences(List<SelectItem> frequences) {
		this.frequences = frequences;
	}

	public boolean isDisplayJourSemaine() {
		return displayJourSemaine;
	}

	public void setDisplayJourSemaine(boolean displayJourSemaine) {
		this.displayJourSemaine = displayJourSemaine;
	}

}
