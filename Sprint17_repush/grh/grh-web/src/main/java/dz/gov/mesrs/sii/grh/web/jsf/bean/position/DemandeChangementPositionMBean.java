package dz.gov.mesrs.sii.grh.web.jsf.bean.position;

import java.io.Serializable;
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
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.ChangementPositionDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

@ManagedBean(name = "demandeChangementPositionMBean")
@ViewScoped
public class DemandeChangementPositionMBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 2411378917745029830L;

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
	private NomenclatureDto typeDemandeMiseEnDisponibilite;
	private NomenclatureDto typeDemandeDetachement;
	private NomenclatureDto typeHorsCadre;
	private final static int MAX_MOIS_DETACHEMENT = 5 * 12;
	private final static int MIN_MOIS_DETACHEMENT = 6;
	private final static int MAX_MOIS_HORS_CADRE = 5 * 12;

	@PostConstruct
	public void init() {
		miseEnDispoMotifList = findMotifMiseEnDispoList();
		typeDemande = findTypeDemandeChgtPositionList();
		etablissementId = getSessionBean().getCompte().getEtabId();
		loadDemandeChangement();
		typeDemandeMiseEnDisponibilite = serviceLocator.getNomenclatureService().findByCode(
				UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_MISE_EN_DISPONIBILITE_CODE);
		typeDemandeDetachement = serviceLocator.getNomenclatureService().findByCode(
				UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE, UtilConstant.NC_GRH_POSITION_EMPLOYE_DETACHEMENT_CODE);
		typeHorsCadre = serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_HORS_CADRE_CODE);
		initStructureDetachementList();
	}

	private void initStructureDetachementList() {
		List<RefStructureDto> dtos = serviceLocator.getRefStructureService().findAll();
		structureDetachementList = new ArrayList<>();
		for (RefStructureDto dto : dtos) {
			structureDetachementList.add(new SelectItem(dto.getId(), dto.getLlStructureLatin()));
		}

	}

	// Ajax
	public void onNew() {
		changementPositionDto = new ChangementPositionDto();
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean.getRefEtablissementDto());
		dossierEmployeDto = new DossierEmployeDto();
	}

	public void onRemove() {
		serviceLocator.getChangementPositionService().remove(changementPositionDto);
		changementPositionDto = null;
		dossierEmployeDto = null;
		CommonMessagesUtils.showSuccessDeleteMessage();

	}

	public void onInit() {

	}

	public void onSearch() {
		loadDemandeChangement();
		changementPositionDto = null;
		dossierEmployeDto = null;
	}

	public void onSave() {
		if (!validForm()) {
			return;
		}
		// if (changementPositionDto.getNomenclatureByMotif().getId() == null) {
		// changementPositionDto.setNomenclatureByMotif(null);
		// }
		changementPositionDto = serviceLocator.getChangementPositionService().saveDemande(changementPositionDto);
		CommonMessagesUtils.showSuccessSaveMessage();
		loadDemandeChangement();

	}

	public void onTraiter() {
		serviceLocator.getChangementPositionService().traiterDemande(changementPositionDto);
		CommonMessagesUtils.showSuccessSaveMessage();
		loadDemandeChangement();
		changementPositionDto = null;
		dossierEmployeDto = null;
	}

	public void onSearchDossierEmploye() {
		dossierEmployeDto = null;
		loadDossierEmployes();
	}

	public void onRowSelect(SelectEvent event) {
		dossierEmployeDto = (DossierEmployeDto) event.getObject();
		// FIXME recupere la derniere demande enregistree ou en cours
		changementPositionDto = new ChangementPositionDto(dossierEmployeDto);
//		ChangementPositionDto changementPositionDto = dossierEmployeDto.getPositionCouranteDto();
//		if (changementPositionDto != null) {
//			this.changementPositionDto = changementPositionDto;
//		} else {
//			this.changementPositionDto = new ChangementPositionDto(dossierEmployeDto);
//		}

	}

	public void onRowSelected(SelectEvent event) {
		changementPositionDto = (ChangementPositionDto) event.getObject();
		dossierEmployeDto = changementPositionDto.getDossierEmployeDto();
	}

	public void onTypeSelect() {
		Integer typeId = changementPositionDto.getNomenclatureByPositionDto().getId();
		if (typeDemandeMiseEnDisponibilite.getId().equals(typeId)) {
			changementPositionDto.setNomenclatureByMotifDto(new NomenclatureDto());
			changementPositionDto.setStructureDto(null);
		} else {
			changementPositionDto.setNomenclatureByMotifDto(null);
			if (typeDemandeDetachement.getId().equals(typeId)) {
				changementPositionDto.setStructureDto(new RefStructureDto());
			} else {
				changementPositionDto.setStructureDto(null);
			}
		}
	}

	// private

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

	private void loadDemandeChangement() {
		changementPositionDtos = new LazyDataModel<ChangementPositionDto>() {

			private static final long serialVersionUID = 797024283780909518L;

			@Override
			public List<ChangementPositionDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				changementPositionDtos.setRowCount(serviceLocator.getChangementPositionService()
						.countAllPendingRequest(etablissementId, searchKeyWords));
				return serviceLocator.getChangementPositionService().findAllPendingRequest(etablissementId,
						searchKeyWords, searchMode);

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
		
		if (changementPositionDto.getNomenclatureByPositionDto().getId().equals(typeDemandeDetachement.getId())) {
			
			if (nbMoisInt > MAX_MOIS_DETACHEMENT || nbMoisInt < MIN_MOIS_DETACHEMENT) {
				GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_POSITION, "msg_periode_detachement_invalid");
				valid = false;
			}
			
			if(changementPositionDto.getProlongation() && !dossierEmployeDto.getPositionCouranteDto().getNomenclatureByPositionDto().getId().equals(typeDemandeDetachement.getId())){
				GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_POSITION, "msg_prolongation_detachement_invalid");
				valid = false;
			}
		}
		
		
		if(changementPositionDto.getNomenclatureByPositionDto().getId().equals(typeHorsCadre.getId())){
			if(nbMoisInt > MAX_MOIS_HORS_CADRE){
				GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_POSITION, "msg_periode_hors_cadre_invalid");
				valid = false;
			}
		}

		return valid;
	}

	// Getters/Setters
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

	public List<SelectItem> getTypeDemande() {
		return typeDemande;
	}

	public void setTypeDemande(List<SelectItem> typeDemande) {
		this.typeDemande = typeDemande;
	}

	public List<SelectItem> getStructureDetachementList() {
		return structureDetachementList;
	}

	public void setStructureDetachementList(List<SelectItem> structureDetachementList) {
		this.structureDetachementList = structureDetachementList;
	}

}
