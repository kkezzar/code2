package dz.gov.mesrs.sii.grh.web.jsf.bean.suivimedical;

import java.util.ArrayList;
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
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierMedicalDto;
import dz.gov.mesrs.sii.grh.business.model.dto.VaccinDto;
import dz.gov.mesrs.sii.grh.business.model.dto.VisiteMedicaleDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@ManagedBean(name = "dossierMedicalMBean")
@ViewScoped
public class DossierMedicalMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	private DossierEmployeDto employeSearchDto;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private DossierEmployeDto dossierEmployeDto;
	private List<DossierMedicalDto> listAntecedents;
	private List<DossierMedicalDto> listAllergies;
	private List<DossierMedicalDto> listPathologies;
	private List<VaccinDto> listVaccins;
	private List<VisiteMedicaleDto> listVisiteMedicales;
	private List<SelectItem> listeNcAntecedent;
	private List<SelectItem> listeNcAllergie;
	private List<SelectItem> listeNcPathologie;
	private List<SelectItem> listeNcVaccin;
	private Integer selectedAntecedentIndex;
	private Integer selectedPathologieIndex;
	private Integer selectedVaccinIndex;
	private Integer selectedAllergieIndex;
	private List<DossierMedicalDto> listAntecedentstoDelete;
	private List<DossierMedicalDto> listAllergiestoDelete;
	private List<DossierMedicalDto> listPathologiestoDelete;
	private List<VaccinDto> listVaccinstoDelete;
	private List<VisiteMedicaleDto> listVisites;
	public DossierMedicalMBean() {

	}

	@PostConstruct
	public void init() {
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean
				.getRefEtablissementDto());
		onSearchDossierEmploye();
		initSelectItemLists();
	}

	private void initSelectItemLists() {
		listeNcAntecedent = findListeNcAntecedents();
		listeNcAllergie = findListeNcAllergies();
		listeNcPathologie = findListeNcPathologies();
		listeNcVaccin = findListeNcVaccins();
	}

	private void initDetail() {
		dossierEmployeDto = null;
		listAntecedents = null;
		listAllergies = null;
		listPathologies = null;
		listVaccins = null;
		listAntecedentstoDelete = null;
		listAllergiestoDelete = null;
		listPathologiestoDelete = null;
		listVaccinstoDelete = null;
	}

	/**
	 * recherche des employés en activité
	 */
	public void onSearchDossierEmploye() {
		loaddossierEmployeModel();
		initDetail();

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
				SearchMode searchMode = new SearchMode();
				dossierEmployeModel.setRowCount(serviceLocator
						.getDossierEmployeService().countAllActif(
								employeSearchDto));
				searchMode = SearchModeMapper.map(first, pageSize, sortField,
						sortOrder);
				List<DossierEmployeDto> dtos = serviceLocator
						.getDossierEmployeService().findAllActif(
								employeSearchDto, searchMode);
				return dtos;
			}
		};

	}

	public void onRowSelect(SelectEvent event) {
		dossierEmployeDto = (DossierEmployeDto) event.getObject();
		loadDossierMedical();
	}
	
	private void loadDossierMedical(){
		loadAntecedents();
		loadAllergies();
		loadPathologies();
		loadVaccins();
		loadVisites();
	}
	
	public void onConsultDossierMedical(){
		loadDossierMedical();
	}

	public void onSaveAntecedents() {
		serviceLocator.getDossierMedicalService().saveAll(listAntecedents,
				listAntecedentstoDelete);
		loadAntecedents();
		CommonMessagesUtils.showSuccessSaveMessage();
	}

	public void onSaveAllergies() {
		serviceLocator.getDossierMedicalService().saveAll(listAllergies,
				listAllergiestoDelete);
	loadAllergies();
		CommonMessagesUtils.showSuccessSaveMessage();
	}

	public void onSavePathologies() {
		serviceLocator.getDossierMedicalService().saveAll(listPathologies,
				listPathologiestoDelete);
		loadPathologies();
		CommonMessagesUtils.showSuccessSaveMessage();
	}

	public void onSaveVaccins() {
		serviceLocator.getVaccinService().saveAll(listVaccins,
				listVaccinstoDelete);
		loadVaccins();
		CommonMessagesUtils.showSuccessSaveMessage();
	}

	public void addAntecedent() {
		DossierMedicalDto antecedent = new DossierMedicalDto();
		if (this.listAntecedents == null) {
			this.listAntecedents = new ArrayList<DossierMedicalDto>();
		}
		antecedent.setNomenclatureDto(new NomenclatureDto());
		antecedent.setDossierEmployeDto(this.dossierEmployeDto);
		antecedent.setAntecedent(true);
		this.listAntecedents.add(antecedent);
	}

	public void removeAntecedent() {
		DossierMedicalDto antecedent = this.listAntecedents
				.get(selectedAntecedentIndex);
		if (antecedent.getId() != 0) {
			listAntecedentstoDelete.add(antecedent);
		}
		this.listAntecedents.remove(antecedent);

	}

	public void addAllergie() {
		DossierMedicalDto allergie = new DossierMedicalDto();
		if (this.listAllergies == null) {
			this.listAllergies = new ArrayList<DossierMedicalDto>();
		}
		allergie.setNomenclatureDto(new NomenclatureDto());
		allergie.setDossierEmployeDto(this.dossierEmployeDto);
		allergie.setAllergie(true);
		this.listAllergies.add(allergie);
	}

	public void removeAllergie() {
		DossierMedicalDto allergie = this.listAllergies
				.get(selectedAllergieIndex);
		if (allergie.getId() != 0) {
			listAllergiestoDelete.add(allergie);
		}
		this.listAllergies.remove(allergie);

	}

	public void addPathologie() {
		DossierMedicalDto pathologie = new DossierMedicalDto();
		if (this.listPathologies == null) {
			this.listPathologies = new ArrayList<DossierMedicalDto>();
		}
		pathologie.setNomenclatureDto(new NomenclatureDto());
		pathologie.setDossierEmployeDto(this.dossierEmployeDto);
		pathologie.setPathologie(true);
		this.listPathologies.add(pathologie);
	}

	public void removePathologie() {
		DossierMedicalDto pathologie = this.listPathologies
				.get(selectedPathologieIndex);
		if (pathologie.getId() != 0) {
			listPathologiestoDelete.add(pathologie);
		}
		this.listPathologies.remove(pathologie);

	}

	public void addVaccin() {
		VaccinDto vaccin = new VaccinDto();
		if (this.listVaccins == null) {
			this.listVaccins = new ArrayList<VaccinDto>();
		}
		vaccin.setNomenclatureDto(new NomenclatureDto());
		vaccin.setDossierEmployeDto(this.dossierEmployeDto);

		this.listVaccins.add(vaccin);
	}

	public void removeVaccin() {
		VaccinDto vaccin = this.listVaccins.get(selectedVaccinIndex);
		if (vaccin.getId() != 0) {
			listVaccinstoDelete.add(vaccin);
		}
		this.listVaccins.remove(vaccin);

	}

	private void loadAntecedents() {
		listAntecedents = serviceLocator.getDossierMedicalService()
				.findAllAntcedentsEmploye(dossierEmployeDto.getId());
		listAntecedentstoDelete = new ArrayList<DossierMedicalDto>();
	}

	private void loadAllergies() {
		listAllergies = serviceLocator.getDossierMedicalService()
				.findAllAllergiesEmploye(dossierEmployeDto.getId());
		listAllergiestoDelete = new ArrayList<DossierMedicalDto>();
	}

	private void loadPathologies() {
		listPathologies = serviceLocator.getDossierMedicalService()
				.findAllPathologiesEmploye(dossierEmployeDto.getId());
		listPathologiestoDelete = new ArrayList<DossierMedicalDto>();
	}

	private void loadVaccins() {
		listVaccins = serviceLocator.getVaccinService().findAllVaccinsEmploye(
				dossierEmployeDto.getId());
		listVaccinstoDelete = new ArrayList<VaccinDto>();
	}
	
	private void loadVisites() {
		listVisites = serviceLocator.getVisiteMedicaleService().findAllVisitesEmploye(
				dossierEmployeDto.getId());
		
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

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public List<DossierMedicalDto> getListAntecedents() {
		return listAntecedents;
	}

	public void setListAntecedents(List<DossierMedicalDto> listAntecedents) {
		this.listAntecedents = listAntecedents;
	}

	public List<DossierMedicalDto> getListAllergies() {
		return listAllergies;
	}

	public void setListAllergies(List<DossierMedicalDto> listAllergies) {
		this.listAllergies = listAllergies;
	}

	public List<DossierMedicalDto> getListPathologies() {
		return listPathologies;
	}

	public void setListPathologies(List<DossierMedicalDto> listPathologies) {
		this.listPathologies = listPathologies;
	}

	public List<SelectItem> getListeNcAntecedent() {
		return listeNcAntecedent;
	}

	public void setListeNcAntecedent(List<SelectItem> listeNcAntecedent) {
		this.listeNcAntecedent = listeNcAntecedent;
	}

	public List<SelectItem> getListeNcAllergie() {
		return listeNcAllergie;
	}

	public void setListeNcAllergie(List<SelectItem> listeNcAllergie) {
		this.listeNcAllergie = listeNcAllergie;
	}

	public List<SelectItem> getListeNcPathologie() {
		return listeNcPathologie;
	}

	public void setListeNcPathologie(List<SelectItem> listeNcPathologie) {
		this.listeNcPathologie = listeNcPathologie;
	}

	public List<SelectItem> getListeNcVaccin() {
		return listeNcVaccin;
	}

	public void setListeNcVaccin(List<SelectItem> listeNcVaccin) {
		this.listeNcVaccin = listeNcVaccin;
	}

	public Integer getSelectedAntecedentIndex() {
		return selectedAntecedentIndex;
	}

	public void setSelectedAntecedentIndex(Integer selectedAntecedentIndex) {
		this.selectedAntecedentIndex = selectedAntecedentIndex;
	}

	public Integer getSelectedPathologieIndex() {
		return selectedPathologieIndex;
	}

	public void setSelectedPathologieIndex(Integer selectedPathologieIndex) {
		this.selectedPathologieIndex = selectedPathologieIndex;
	}

	public Integer getSelectedVaccinIndex() {
		return selectedVaccinIndex;
	}

	public void setSelectedVaccinIndex(Integer selectedVaccinIndex) {
		this.selectedVaccinIndex = selectedVaccinIndex;
	}

	public Integer getSelectedAllergieIndex() {
		return selectedAllergieIndex;
	}

	public void setSelectedAllergieIndex(Integer selectedAllergieIndex) {
		this.selectedAllergieIndex = selectedAllergieIndex;
	}

	public List<VaccinDto> getListVaccins() {
		return listVaccins;
	}

	public void setListVaccins(List<VaccinDto> listVaccins) {
		this.listVaccins = listVaccins;
	}

	public List<VisiteMedicaleDto> getListVisiteMedicales() {
		return listVisiteMedicales;
	}

	public void setListVisiteMedicales(
			List<VisiteMedicaleDto> listVisiteMedicales) {
		this.listVisiteMedicales = listVisiteMedicales;
	}

	public List<DossierMedicalDto> getListAntecedentstoDelete() {
		return listAntecedentstoDelete;
	}

	public void setListAntecedentstoDelete(
			List<DossierMedicalDto> listAntecedentstoDelete) {
		this.listAntecedentstoDelete = listAntecedentstoDelete;
	}

	public List<DossierMedicalDto> getListAllergiestoDelete() {
		return listAllergiestoDelete;
	}

	public void setListAllergiestoDelete(
			List<DossierMedicalDto> listAllergiestoDelete) {
		this.listAllergiestoDelete = listAllergiestoDelete;
	}

	public List<DossierMedicalDto> getListPathologiestoDelete() {
		return listPathologiestoDelete;
	}

	public void setListPathologiestoDelete(
			List<DossierMedicalDto> listPathologiestoDelete) {
		this.listPathologiestoDelete = listPathologiestoDelete;
	}

	public List<VaccinDto> getListVaccinstoDelete() {
		return listVaccinstoDelete;
	}

	public void setListVaccinstoDelete(List<VaccinDto> listVaccinstoDelete) {
		this.listVaccinstoDelete = listVaccinstoDelete;
	}

	public List<VisiteMedicaleDto> getListVisites() {
		return listVisites;
	}

	public void setListVisites(List<VisiteMedicaleDto> listVisites) {
		this.listVisites = listVisites;
	}
	
	

}
