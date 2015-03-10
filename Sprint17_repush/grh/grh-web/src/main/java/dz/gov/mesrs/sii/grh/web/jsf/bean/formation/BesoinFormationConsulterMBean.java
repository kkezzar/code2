package dz.gov.mesrs.sii.grh.web.jsf.bean.formation;

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

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.BesoinFormationDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@ManagedBean(name = "besoinFormationConsulterMBean")
@ViewScoped
public class BesoinFormationConsulterMBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BesoinFormationDto besoinFormationSearchDto;
	private List<BesoinFormationDto> listeBesoinFormation;
	private List<SelectItem> listTypesStructuresReferentiel;
	private Integer selectedTypeStructureReferentielId;
	private List<RefStructureDto> listStructuresReferentiel;
	private RefStructureDto selectedStructureReferentiel;
	private List<SelectItem> listeNcTypeBesoin;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private DossierEmployeDto employeSearchDto;
	private RefGroupeDto selectedGroupeReferentiel;
	private List<RefGroupeDto> listGroupesReferentiel;
	private String refGroupSearchKeyWord;

	public BesoinFormationConsulterMBean() {

	}

	@PostConstruct
	public void init() {
		besoinFormationSearchDto = new BesoinFormationDto();
		besoinFormationSearchDto.setNomenclatureDto(new NomenclatureDto());
		besoinFormationSearchDto.setRefEtablissementDto(sessionBean
				.getRefEtablissementDto());
		refGroupSearchKeyWord ="";
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean
				.getRefEtablissementDto());
		initSelectItemLists();
		//loadlisteBesoinFormation();
	}

	private void initSelectItemLists() {
		listeNcTypeBesoin = findListeNcTypeBesoinFormation();
		listTypesStructuresReferentiel = findStructureTypes(sessionBean
				.getIdEtablissement());
		if (listTypesStructuresReferentiel != null
				&& !listTypesStructuresReferentiel.isEmpty()) {
			selectedTypeStructureReferentielId = (Integer) listTypesStructuresReferentiel
					.get(0).getValue();
		}
	}

	public void onTypeBesoinChange(AjaxBehaviorEvent event) {
		NomenclatureDto typeBesoin = serviceLocator
				.getNomenclatureService()
				.findById(besoinFormationSearchDto.getNomenclatureDto().getId());
		if (typeBesoin == null) {
			besoinFormationSearchDto.setDossierEmployeDto(null);
			besoinFormationSearchDto.setRefStructureDto(null);
			besoinFormationSearchDto.setRefGroupeDto(null);
		} else if (typeBesoin.getCode().equals(
				UtilConstant.NC_GRH_BESOIN_STRUCTURE_VALUE)) {
			besoinFormationSearchDto.setDossierEmployeDto(null);
			besoinFormationSearchDto.setRefStructureDto(new RefStructureDto());
			besoinFormationSearchDto.setRefGroupeDto(null);
		} else if (typeBesoin.getCode().equals(
				UtilConstant.NC_GRH_BESOIN_GROUPE_VALUE)) {
			besoinFormationSearchDto.setDossierEmployeDto(null);
			besoinFormationSearchDto.setRefStructureDto(null);
			besoinFormationSearchDto.setRefGroupeDto(new RefGroupeDto());

		} else if (typeBesoin.getCode().equals(
				UtilConstant.NC_GRH_BESOIN_INDIVIDUEL_VALUE)) {
			besoinFormationSearchDto
					.setDossierEmployeDto(new DossierEmployeDto());
			besoinFormationSearchDto.setRefStructureDto(null);
			besoinFormationSearchDto.setRefGroupeDto(null);

		}

	}

	public void onSearchBesoin() {
		try {
			loadlisteBesoinFormation();

		} catch (Exception e) {

		}
	}

	private void loadlisteBesoinFormation() {
		listeBesoinFormation = serviceLocator.getBesoinFormationService()
				.findAllByExample(besoinFormationSearchDto);

	}

	public void onRowSelect(SelectEvent event) {
		DossierEmployeDto dossierEmployeDto = (DossierEmployeDto) event
				.getObject();
		besoinFormationSearchDto.setDossierEmployeDto(dossierEmployeDto);

	}

	public void onRowGroupSelect(SelectEvent event) {
		RefGroupeDto refGroupeDto = (RefGroupeDto) event.getObject();
		besoinFormationSearchDto.setRefGroupeDto(refGroupeDto);
	}

	
	
	public void handleTypeStructureChange(AjaxBehaviorEvent event) {
		initStrcuturesReferentielParType();
	}

	
	
	private void initStrcuturesReferentielParType() {
		if (sessionBean.getIdEtablissement() != null
				&& selectedTypeStructureReferentielId != null) {
			listStructuresReferentiel = serviceLocator.getRefStructureService()
					.findStructuresByTypeByEtab(
							sessionBean.getIdEtablissement(),
							selectedTypeStructureReferentielId);
		}
	}

	
	public void onRowStructureSelect(SelectEvent event) {
		RefStructureDto refStructureDto = (RefStructureDto) event.getObject();
		besoinFormationSearchDto.setRefStructureDto(refStructureDto);
	}

	public void onSearchDossierEmploye() {
		loaddossierEmployeModel();
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
						.getDossierEmployeService().countAllByExample(
								employeSearchDto, searchMode));
				searchMode = SearchModeMapper.map(first, pageSize, sortField,
						sortOrder);
				List<DossierEmployeDto> dtos = serviceLocator
						.getDossierEmployeService().findAllByExample(
								employeSearchDto, searchMode);
				return dtos;
			}
		};

	}

	public void searchGroup() {

		NomenclatureDto typeGroupeFormation = serviceLocator
				.getNomenclatureService().findByCode(
						UtilConstant.NC_TYPE_GROUPE_NAME,
						UtilConstant.NC_TYPE_GROUPE_FORMATION_CODE);
		if (typeGroupeFormation != null) {
			Integer idTypeGroupeRecherche = typeGroupeFormation.getId();
			if (idTypeGroupeRecherche != null) {
				listGroupesReferentiel = serviceLocator.getRefGroupeService()
						.findGeneric(sessionBean.getIdEtablissement(),
								refGroupSearchKeyWord, idTypeGroupeRecherche);
			}
		}
	}
	public BesoinFormationDto getBesoinFormationSearchDto() {
		return besoinFormationSearchDto;
	}

	public void setBesoinFormationSearchDto(
			BesoinFormationDto besoinFormationSearchDto) {
		this.besoinFormationSearchDto = besoinFormationSearchDto;
	}

	public List<BesoinFormationDto> getListeBesoinFormation() {
		return listeBesoinFormation;
	}

	public void setListeBesoinFormation(
			List<BesoinFormationDto> listeBesoinFormation) {
		this.listeBesoinFormation = listeBesoinFormation;
	}

	

	public List<SelectItem> getListeNcTypeBesoin() {
		return listeNcTypeBesoin;
	}

	public void setListeNcTypeBesoin(List<SelectItem> listeNcTypeBesoin) {
		this.listeNcTypeBesoin = listeNcTypeBesoin;
	}

	public List<SelectItem> getListTypesStructuresReferentiel() {
		return listTypesStructuresReferentiel;
	}

	public void setListTypesStructuresReferentiel(
			List<SelectItem> listTypesStructuresReferentiel) {
		this.listTypesStructuresReferentiel = listTypesStructuresReferentiel;
	}

	public Integer getSelectedTypeStructureReferentielId() {
		return selectedTypeStructureReferentielId;
	}

	public void setSelectedTypeStructureReferentielId(
			Integer selectedTypeStructureReferentielId) {
		this.selectedTypeStructureReferentielId = selectedTypeStructureReferentielId;
	}

	public List<RefStructureDto> getListStructuresReferentiel() {
		return listStructuresReferentiel;
	}

	public void setListStructuresReferentiel(
			List<RefStructureDto> listStructuresReferentiel) {
		this.listStructuresReferentiel = listStructuresReferentiel;
	}

	public RefStructureDto getSelectedStructureReferentiel() {
		return selectedStructureReferentiel;
	}

	public void setSelectedStructureReferentiel(
			RefStructureDto selectedStructureReferentiel) {
		this.selectedStructureReferentiel = selectedStructureReferentiel;
	}

	public LazyDataModel<DossierEmployeDto> getDossierEmployeModel() {
		return dossierEmployeModel;
	}

	public void setDossierEmployeModel(
			LazyDataModel<DossierEmployeDto> dossierEmployeModel) {
		this.dossierEmployeModel = dossierEmployeModel;
	}

	public DossierEmployeDto getEmployeSearchDto() {
		return employeSearchDto;
	}

	public void setEmployeSearchDto(DossierEmployeDto employeSearchDto) {
		this.employeSearchDto = employeSearchDto;
	}

	public RefGroupeDto getSelectedGroupeReferentiel() {
		return selectedGroupeReferentiel;
	}

	public void setSelectedGroupeReferentiel(RefGroupeDto selectedGroupeReferentiel) {
		this.selectedGroupeReferentiel = selectedGroupeReferentiel;
	}

	public List<RefGroupeDto> getListGroupesReferentiel() {
		return listGroupesReferentiel;
	}

	public void setListGroupesReferentiel(List<RefGroupeDto> listGroupesReferentiel) {
		this.listGroupesReferentiel = listGroupesReferentiel;
	}

	public String getRefGroupSearchKeyWord() {
		return refGroupSearchKeyWord;
	}

	public void setRefGroupSearchKeyWord(String refGroupSearchKeyWord) {
		this.refGroupSearchKeyWord = refGroupSearchKeyWord;
	}

	
	
}
