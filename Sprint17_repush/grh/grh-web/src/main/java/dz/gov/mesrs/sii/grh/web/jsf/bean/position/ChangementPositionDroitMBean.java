package dz.gov.mesrs.sii.grh.web.jsf.bean.position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import dz.gov.mesrs.sii.grh.business.model.dto.ChangementPositionDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

@ManagedBean(name = "changementPositionDroitMBean")
@ViewScoped
public class ChangementPositionDroitMBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 3670549509675396683L;

	private ChangementPositionDto changementPositionDto;
	private Integer etablissementId;
	private LazyDataModel<ChangementPositionDto> changementPositionDtos;
	private String searchKeyWords;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private DossierEmployeDto dossierEmployeDto;
	private DossierEmployeDto employeSearchDto;
	private List<SelectItem> miseEnDispoMotifList;
	private List<SelectItem> typeDemande;
	private List<SelectItem> structureDetachementList;
	private NomenclatureDto typeMiseEnDisponibilite;
	private NomenclatureDto typeDetachement;
	private NomenclatureDto typeServiceNational;
	private boolean displayOrdreDappel;
	private final static int MIN_MOIS_DETACHEMENT = 6;
	private final static int MAX_MOIS_DETACHEMENT = 12 * 5;
	private final static int PERIODE_MOIS_SERVICE_NATIONAL = 18;

	@PostConstruct
	public void init() {
		etablissementId = getSessionBean().getCompte().getEtabId();
		loadChangementPosition();
		typeDemande = findTypeChgtPositionDroitList();
		typeMiseEnDisponibilite = serviceLocator.getNomenclatureService().findByCode(
				UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_MISE_EN_DISPONIBILITE_CODE);
		typeDetachement = serviceLocator.getNomenclatureService().findByCode(
				UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE, UtilConstant.NC_GRH_POSITION_EMPLOYE_DETACHEMENT_CODE);
		typeServiceNational = serviceLocator.getNomenclatureService().findByCode(
				UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE, UtilConstant.NC_GRH_POSITION_EMPLOYE_SERVICE_NATIONAL_CODE);
		miseEnDispoMotifList = findMotifMiseEnDispoList();
		initStructureDetachementList();

	}

	// ajax
	public void onNew() {
		changementPositionDto = new ChangementPositionDto();
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean.getRefEtablissementDto());
		dossierEmployeDto = new DossierEmployeDto();
		displayOrdreDappel = false;
	}

	public void onSave() {
		if (!validForm()) {
			return;
		}
		changementPositionDto = serviceLocator.getChangementPositionService()
				.saveChangementDroit(changementPositionDto);
	}

	public void onValidate() {
		if (!validForm()) {
			return;
		}
		serviceLocator.getChangementPositionService().validateChangementDroit(changementPositionDto);
		changementPositionDto = null;
		dossierEmployeDto = null;
		employeSearchDto = null;
		CommonMessagesUtils.showSuccessSaveMessage();
		loadChangementPosition();
	}

	public void onRemove() {
		serviceLocator.getChangementPositionService().remove(changementPositionDto);
		changementPositionDto = null;
		dossierEmployeDto = null;
		employeSearchDto = null;
		CommonMessagesUtils.showSuccessDeleteMessage();
		loadChangementPosition();
	}

	public void onSearch() {
		dossierEmployeDto = null;
		changementPositionDto = null;
		employeSearchDto = null;
		loadChangementPosition();

	}

	public void onSearchDossierEmploye() {
		dossierEmployeDto = null;
		loadDossierEmployes();
	}

	public void onTypeSelect() {
		Integer typeId = changementPositionDto.getNomenclatureByPositionDto().getId();
		if (typeMiseEnDisponibilite.getId().equals(typeId)) {
			changementPositionDto.setNomenclatureByMotifDto(new NomenclatureDto());
			changementPositionDto.setStructureDto(null);
			displayOrdreDappel = false;
		} else {
			changementPositionDto.setNomenclatureByMotifDto(null);
			if (typeDetachement.getId().equals(typeId)) {
				changementPositionDto.setStructureDto(new RefStructureDto());
				displayOrdreDappel = false;
			} else {
				changementPositionDto.setStructureDto(null);
				// cas du service national
				displayOrdreDappel = true;

			}
		}
	}

	// employe
	public void onRowSelect(SelectEvent event) {
		dossierEmployeDto = (DossierEmployeDto) event.getObject();
		// FIXME recupere la derniere demande enregistree ou en cours
		changementPositionDto = new ChangementPositionDto();
		changementPositionDto.setDossierEmployeDto(dossierEmployeDto);
//		ChangementPositionDto changementPositionDto = dossierEmployeDto.getPositionCouranteDto();
//		if (changementPositionDto != null) {
//			this.changementPositionDto = changementPositionDto;
//		} else {
//			this.changementPositionDto = new ChangementPositionDto(dossierEmployeDto);
//		}

	}

	// changement position
	public void onRowSelected(SelectEvent event) {
		changementPositionDto = (ChangementPositionDto) event.getObject();
		dossierEmployeDto = changementPositionDto.getDossierEmployeDto();
		if (changementPositionDto.getNomenclatureByPositionDto().getId().equals(typeServiceNational.getId())) {
			displayOrdreDappel = true;
		} else {
			displayOrdreDappel = false;
		}
	}

	public void onInit() {

	}

	// private
	private void loadChangementPosition() {
		changementPositionDtos = new LazyDataModel<ChangementPositionDto>() {

			private static final long serialVersionUID = 797024283780909518L;

			@Override
			public List<ChangementPositionDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				changementPositionDtos.setRowCount(serviceLocator.getChangementPositionService()
						.countAllPendingChangementDroit(etablissementId, searchKeyWords));
				return serviceLocator.getChangementPositionService().findAllPendingChangementDroit(etablissementId,
						searchKeyWords, searchMode);

			}
		};
	}

	private void initStructureDetachementList() {
		List<RefStructureDto> dtos = serviceLocator.getRefStructureService().findAll();
		structureDetachementList = new ArrayList<>();
		for (RefStructureDto dto : dtos) {
			structureDetachementList.add(new SelectItem(dto.getId(), dto.getLlStructureLatin()));
		}

	}

	private void loadDossierEmployes() {
		dossierEmployeDto = null;
		dossierEmployeModel = new LazyDataModel<DossierEmployeDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(DossierEmployeDto dossierEmployeDto) {
				return dossierEmployeDto.getId();
			}

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

	private boolean validForm() {
		boolean valid = true;
		Date dateDebutChangement = changementPositionDto.getDateDebut();
		Date dateFinChangement = changementPositionDto.getDateFin();
		if (dateDebutChangement.after(dateDebutChangement)) {
			valid = false;
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_POSITION, "msg_date_debut_changement_fin_changement_invalid");
		}
		
		long tempsChangement = dateFinChangement.getTime() - dateDebutChangement.getTime();
		long nbMois = ((((tempsChangement / 1000) / 60)/60) / 24) / 30;
		int nbMoisInt = (int) nbMois;
		
		if (changementPositionDto.getNomenclatureByPositionDto().getId().equals(typeDetachement.getId())) {
			
			if (nbMoisInt > MAX_MOIS_DETACHEMENT || nbMoisInt < MIN_MOIS_DETACHEMENT) {
				GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_POSITION, "msg_periode_detachement_invalid");
				valid = false;
			}
			
			if(changementPositionDto.getProlongation() && !dossierEmployeDto.getPositionCouranteDto().getNomenclatureByPositionDto().getId().equals(typeDetachement.getId())){
				GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_POSITION, "msg_prolongation_detachement_invalid");
				valid = false;
			}
		}
		
		
		if(changementPositionDto.getNomenclatureByPositionDto().getId().equals(typeServiceNational.getId())){
			if(nbMoisInt != PERIODE_MOIS_SERVICE_NATIONAL){
				GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_POSITION, "msg_periode_service_national_invalid");
				valid = false;
			}
		}

		return valid;
	}

	// getters & setters
	public ChangementPositionDto getChangementPositionDto() {
		return changementPositionDto;
	}

	public void setChangementPositionDto(ChangementPositionDto changementPositionDto) {
		this.changementPositionDto = changementPositionDto;
	}

	public LazyDataModel<ChangementPositionDto> getChangementPositionDtos() {
		return changementPositionDtos;
	}

	public void setChangementPositionDtos(LazyDataModel<ChangementPositionDto> changementPositionDtos) {
		this.changementPositionDtos = changementPositionDtos;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public LazyDataModel<DossierEmployeDto> getDossierEmployeModel() {
		return dossierEmployeModel;
	}

	public void setDossierEmployeModel(LazyDataModel<DossierEmployeDto> dossierEmployeModel) {
		this.dossierEmployeModel = dossierEmployeModel;
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

	public List<SelectItem> getMiseEnDispoMotifList() {
		return miseEnDispoMotifList;
	}

	public void setMiseEnDispoMotifList(List<SelectItem> miseEnDispoMotifList) {
		this.miseEnDispoMotifList = miseEnDispoMotifList;
	}

	public List<SelectItem> getStructureDetachementList() {
		return structureDetachementList;
	}

	public void setStructureDetachementList(List<SelectItem> structureDetachementList) {
		this.structureDetachementList = structureDetachementList;
	}

	public List<SelectItem> getTypeDemande() {
		return typeDemande;
	}

	public void setTypeDemande(List<SelectItem> typeDemande) {
		this.typeDemande = typeDemande;
	}

	public boolean isDisplayOrdreDappel() {
		return displayOrdreDappel;
	}

	public void setDisplayOrdreDappel(boolean displayOrdreDappel) {
		this.displayOrdreDappel = displayOrdreDappel;
	}

}
