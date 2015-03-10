package dz.gov.mesrs.sii.grh.web.jsf.bean.administrative;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.FinActiviteDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

@ManagedBean(name = "cessationActiviteMBean")
@ViewScoped
public class CessationActiviteMBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -4436242074243315492L;
	private int etablissementId;
	private String searchKeyWords;
	private FinActiviteDto cessationActiviteDto;
	private DossierEmployeDto dossierEmployeDto;
	private DossierEmployeDto employeSearchDto;
	private LazyDataModel<FinActiviteDto> cessationModel;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private List<SelectItem> listeTypeCessation;
	private NomenclatureDto typeCessationDeces;
	private NomenclatureDto typeCessationRetraite;
	private NomenclatureDto typeCessationDemission;
	private boolean departRetraite;
	private boolean deces;
	private boolean cessationAutre;
	private boolean displayMotif;
	private boolean motifEditable;
	private boolean typeSelecionEditable;

	@PostConstruct
	public void init() {
		listeTypeCessation = findTypeCessationList();
		etablissementId = getSessionBean().getCompte().getEtabId();
		initEmployeSearchDto();
		onSearch();
		typeCessationDeces = serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_TYPE_CESSATION,
				UtilConstant.NC_GRH_TYPE_CESSATION_DECES_VALUE);
		typeCessationRetraite = serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_TYPE_CESSATION,
				UtilConstant.NC_GRH_TYPE_CESSATION_RETRAITE_VALUE);
		typeCessationDemission = serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_TYPE_CESSATION,
				UtilConstant.NC_GRH_TYPE_CESSATION_DEMISSION_VALUE);
	}

	private void initEmployeSearchDto() {
		RefEtablissementDto etablissementDto = new RefEtablissementDto();
		etablissementDto.setId(etablissementId);
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(etablissementDto);
	}

	// Ajax
	public void onNew() {
		cessationActiviteDto = new FinActiviteDto(new NomenclatureDto());
		dossierEmployeDto = new DossierEmployeDto();
		// dossierEmployeModel = null;
		cessationActiviteDto.setEmploye(dossierEmployeDto);
		cessationActiviteDto.setNomenclature(new NomenclatureDto());
		initEmployeSearchDto();
		typeSelecionEditable = true;
		if(dossierEmployeModel != null){
		dossierEmployeModel.setRowCount(0);
		dossierEmployeModel.setWrappedData(null);}
//		onSearchDossierEmploye();

	}

	public void onSave() {
		cessationActiviteDto.setEmploye(dossierEmployeDto);
		serviceLocator.getFinActiviteService().saveCessationFinal(cessationActiviteDto);
		CommonMessagesUtils.showSuccessSaveMessage();
		onSearch();
	}

	public void onValidate() {
		cessationActiviteDto.setEmploye(dossierEmployeDto);
		serviceLocator.getFinActiviteService().validerCessationFinal(cessationActiviteDto);
		CommonMessagesUtils.showSuccessSaveMessage();
		onSearch();
	}

	public void onSearch() {
		cessationActiviteDto = null;
//		dossierEmployeDto = null;
		cessationModel = new LazyDataModel<FinActiviteDto>() {
			private static final long serialVersionUID = 4453742764020984653L;

			@Override
			public Object getRowKey(FinActiviteDto object) {
				return object.getId();
			}

			@Override
			public List<FinActiviteDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = new SearchMode(pageSize, first);
				cessationModel.setRowCount(serviceLocator.getFinActiviteService().countAllCessationFinalCree(
						etablissementId, searchKeyWords));
				if (!StringUtils.isEmpty(sortField)) {
					SearchMode.SortOrder order = null;
					if (sortOrder != null) {
						if (sortOrder.equals(SortOrder.DESCENDING)) {
							order = SearchMode.SortOrder.DESC;
						} else {
							order = SearchMode.SortOrder.ASC;
						}

					}
					SearchMode.SortField sort = new SearchMode.SortField(sortField, order);
					searchMode.addSortField(sort);
				}

				return serviceLocator.getFinActiviteService().findAllCessationFinalCree(etablissementId,
						searchKeyWords, searchMode);
			}
		};
	}

	public void onSearchDossierEmploye() {
		// dossierEmployeDto = null;
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

	public void onTypeCessationSelected() {
		setFormAction();
	}

	private void setFormAction() {
		Integer typeId = cessationActiviteDto.getNomenclature().getId();
		if (typeId == null) {
			deces = false;
			departRetraite = false;
			cessationAutre = false;
			displayMotif = false;
			typeSelecionEditable = true;
			motifEditable = false;
		} else if (typeId.equals(typeCessationDeces.getId())) {
			deces = true;
			departRetraite = false;
			cessationAutre = false;
			displayMotif = false;
			motifEditable = false;
		} else if (typeId.equals(typeCessationRetraite.getId())) {
			deces = false;
			departRetraite = true;
			cessationAutre = false;
			displayMotif = true;
			typeSelecionEditable = false;
			motifEditable = false;
		} else if (typeId.equals(typeCessationDemission.getId())) {
			deces = false;
			departRetraite = false;
			cessationAutre = true;
			displayMotif = true;
			typeSelecionEditable = false;
			motifEditable = false;
		} else {
			deces = false;
			departRetraite = false;
			cessationAutre = true;
			displayMotif = true;
			typeSelecionEditable = true;
			motifEditable = true;
		}

	}

	// Data Cessation Activite
	public void onRowSelected(SelectEvent event) {
		cessationActiviteDto = (FinActiviteDto) event.getObject();
		dossierEmployeDto = cessationActiviteDto.getEmploye();
		setFormAction();

	}

	// DataTabe Employe
	public void onRowSelect(SelectEvent event) {
		dossierEmployeDto = (DossierEmployeDto) event.getObject();
		if (!dossierEmployeDto.getFinActiviteDtos().isEmpty()) {
			FinActiviteDto dto = dossierEmployeDto.getFinActiviteDtos().get(0);
			cessationActiviteDto = Boolean.FALSE.equals(dto.getAccepte()) || dto.getAccepte() == null ? new FinActiviteDto(
					new NomenclatureDto()) : dto;
		} else {
			cessationActiviteDto = new FinActiviteDto(new NomenclatureDto());
			dossierEmployeDto.addFinActiviteDto(cessationActiviteDto);
		}
		setFormAction();
	}

	// Getters Setters
	public FinActiviteDto getCessationActiviteDto() {
		return cessationActiviteDto;
	}

	public void setCessationActiviteDto(FinActiviteDto cessationActiviteDto) {
		this.cessationActiviteDto = cessationActiviteDto;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public LazyDataModel<FinActiviteDto> getCessationModel() {
		return cessationModel;
	}

	public void setCessationModel(LazyDataModel<FinActiviteDto> cessationModel) {
		this.cessationModel = cessationModel;
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

	public void setDossierEmployeModel(LazyDataModel<DossierEmployeDto> dossierEmployeModel) {
		this.dossierEmployeModel = dossierEmployeModel;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public List<SelectItem> getListeTypeCessation() {
		return listeTypeCessation;
	}

	public void setListeTypeCessation(List<SelectItem> listeTypeCessation) {
		this.listeTypeCessation = listeTypeCessation;
	}

	public boolean isDepartRetraite() {
		return departRetraite;
	}

	public void setDepartRetraite(boolean departRetraite) {
		this.departRetraite = departRetraite;
	}

	public boolean isDeces() {
		return deces;
	}

	public void setDeces(boolean deces) {
		this.deces = deces;
	}

	public boolean isCessationAutre() {
		return cessationAutre;
	}

	public void setCessationAutre(boolean cessationAutre) {
		this.cessationAutre = cessationAutre;
	}

	public int getEtablissementId() {
		return etablissementId;
	}

	public void setEtablissementId(int etablissementId) {
		this.etablissementId = etablissementId;
	}

	public NomenclatureDto getTypeCessationDeces() {
		return typeCessationDeces;
	}

	public void setTypeCessationDeces(NomenclatureDto typeCessationDeces) {
		this.typeCessationDeces = typeCessationDeces;
	}

	public NomenclatureDto getTypeCessationRetraite() {
		return typeCessationRetraite;
	}

	public void setTypeCessationRetraite(NomenclatureDto typeCessationRetraite) {
		this.typeCessationRetraite = typeCessationRetraite;
	}

	public NomenclatureDto getTypeCessationDemission() {
		return typeCessationDemission;
	}

	public void setTypeCessationDemission(NomenclatureDto typeCessationDemission) {
		this.typeCessationDemission = typeCessationDemission;
	}

	public boolean isDisplayMotif() {
		return displayMotif;
	}

	public void setDisplayMotif(boolean displayMotif) {
		this.displayMotif = displayMotif;
	}

	public boolean isMotifEditable() {
		return motifEditable;
	}

	public void setMotifEditable(boolean motifEditable) {
		this.motifEditable = motifEditable;
	}

	public boolean isTypeSelecionEditable() {
		return typeSelecionEditable;
	}

	public void setTypeSelecionEditable(boolean typeSelecionEditable) {
		this.typeSelecionEditable = typeSelecionEditable;
	}

}
