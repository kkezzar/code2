package dz.gov.mesrs.sii.grh.web.jsf.bean.formation;

import java.util.ArrayList;
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
import dz.gov.mesrs.sii.grh.business.model.dto.CorpsDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DetailBesoinFormationDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.FormationCatalogueDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GradeDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@ManagedBean(name = "besoinFormationEditMBean")
@ViewScoped
public class BesoinFormationEditMBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private BesoinFormationDto besoinFormationDto;
	private List<BesoinFormationDto> listeBesoinFormation;
	private List<SelectItem> listeNcTypeBesoin;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private DossierEmployeDto employeSearchDto;
	private RefGroupeDto selectedGroupeReferentiel;
	private List<RefGroupeDto> listGroupesReferentiel;
	private String refGroupSearchKeyWord;
	private RefStructureDto selectedStructureReferentiel;
	private List<RefStructureDto> listStructuresReferentiel;
	private String refStructureSearchKeyWord;
	private Integer selectedStructureId;
	private List<SelectItem> listTypesStructuresReferentiel;
	private Integer selectedTypeStructureReferentielId;
	private boolean renderEmploye;
	private boolean renderStrucrure;
	private boolean renderGroupe;
	List<DetailBesoinFormationDto> listFormationsStructure;
	List<DetailBesoinFormationDto> listFormationsGroupe;
	List<DetailBesoinFormationDto> listFormationsEmploye;
	private List<SelectItem> listFormationsCatalogue;
	private List<SelectItem> listGrades;
	private Integer selectedFormationStructureIndex;
	private Integer selectedFormationGroupeIndex;
	private Integer selectedFormationEmployeIndex;

	public BesoinFormationEditMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		refGroupSearchKeyWord ="";
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean
				.getRefEtablissementDto());
		initSelectItemLists();
		onSearchBesoin();
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
		listFormationsCatalogue = findListFormationGroupedByCatalogue();
		listGrades = findListGradeGroupedByCorps();
	}

	public void initDetail() {
		besoinFormationDto = null;

	}

	public void onNew() {
		besoinFormationDto = new BesoinFormationDto();
		besoinFormationDto.setRefEtablissementDto(sessionBean
				.getRefEtablissementDto());
		besoinFormationDto.setNomenclatureDto(new NomenclatureDto());
		renderStrucrure = false;
		renderGroupe = false;
		renderEmploye = false;

	}

	public void onSearchBesoin() {
		try {
			loadlisteBesoinFormation();
			initDetail();
		} catch (Exception e) {

		}
	}

	private void loadlisteBesoinFormation() {
		listeBesoinFormation = serviceLocator.getBesoinFormationService().findAllBesoinFormationCrees(sessionBean
				.getIdEtablissement(), new SearchMode(), searchKeyWords);
			
	}

	public void onBesoinSelect(SelectEvent event) {
		besoinFormationDto = (BesoinFormationDto) event.getObject();
		verifyTypeBesoin();
		if (renderStrucrure) {
			listFormationsStructure = besoinFormationDto
					.getDetailBesoinFormationDtos();
		} else if (renderGroupe) {
			listFormationsGroupe = besoinFormationDto
					.getDetailBesoinFormationDtos();
		} else if (renderEmploye) {
			listFormationsEmploye = besoinFormationDto
					.getDetailBesoinFormationDtos();
		}

	}

	public void onTypeBesoinChange(AjaxBehaviorEvent event) {
		verifyTypeBesoin();
		
	}

	public void onAddDetailBesoinFormation() {

		DetailBesoinFormationDto detailBesoinFormationDto = new DetailBesoinFormationDto();
		detailBesoinFormationDto
				.setFormationCatalogueDto(new FormationCatalogueDto());
		detailBesoinFormationDto.setBesoinFormationDto(besoinFormationDto);
		if (renderStrucrure) {
			detailBesoinFormationDto.setCorpsDto(new CorpsDto());
			detailBesoinFormationDto.setGradeDto(new GradeDto());

			if (listFormationsStructure == null) {
				listFormationsStructure = new ArrayList<DetailBesoinFormationDto>();
			}

			listFormationsStructure.add(detailBesoinFormationDto);
		} else if (renderGroupe) {
			if (listFormationsGroupe == null) {
				listFormationsGroupe = new ArrayList<DetailBesoinFormationDto>();
			}
			listFormationsGroupe.add(detailBesoinFormationDto);
		} else if (renderEmploye) {
			if (listFormationsEmploye == null) {
				listFormationsEmploye = new ArrayList<DetailBesoinFormationDto>();
			}
			listFormationsEmploye.add(detailBesoinFormationDto);
		}

	}
	
	
	public void onRemoveDetailBesoinFormation() {
		if (renderStrucrure) {
			DetailBesoinFormationDto detailBesoinFormationDto =listFormationsStructure.get(selectedFormationStructureIndex);
			listFormationsStructure.remove(detailBesoinFormationDto);
		} else if (renderGroupe) {
			DetailBesoinFormationDto detailBesoinFormationDto = listFormationsGroupe.get(selectedFormationGroupeIndex);
			listFormationsGroupe.remove(detailBesoinFormationDto);
		} else if (renderEmploye) {
			DetailBesoinFormationDto detailBesoinFormationDto = listFormationsEmploye.get(selectedFormationEmployeIndex);
			listFormationsEmploye.remove(detailBesoinFormationDto);
		}

	}

	public void onSave() {
		if(validateForm()){
		if (renderStrucrure) {
			besoinFormationDto.setDetailBesoinFormationDtos(listFormationsStructure);
			addCorps();
			besoinFormationDto.setRefGroupeDto(null);
			besoinFormationDto.setDossierEmployeDto(null);
		} else if (renderGroupe) {
			besoinFormationDto.setDetailBesoinFormationDtos(listFormationsGroupe);
			besoinFormationDto.setRefStructureDto(null);
			besoinFormationDto.setDossierEmployeDto(null);
		} else if (renderEmploye) {
			besoinFormationDto.setDetailBesoinFormationDtos(listFormationsEmploye);
			besoinFormationDto.setRefGroupeDto(null);
			besoinFormationDto.setRefStructureDto(null);
		}
		
		besoinFormationDto = serviceLocator.getBesoinFormationService().saveBesoinFormation(besoinFormationDto);
		loadlisteBesoinFormation();
		//initDetail();
		CommonMessagesUtils.showSuccessSaveMessage();
		}
	}
	
	
	
	
	private boolean validateForm(){
		boolean result = true;
		if ( serviceLocator.getRefJourOuvreService().isWeekendDay(
				besoinFormationDto.getDateDebut())
				|| serviceLocator.getRefJourOuvreService().isJourOuvre(
						besoinFormationDto.getDateDebut())) {
			GRHMessagesUtils
					.showErrorMessage("besoin_formation_date_debut_invalide");
			result = false;
		}
		if ( serviceLocator.getRefJourOuvreService().isWeekendDay(
				besoinFormationDto.getDateFin())
				|| serviceLocator.getRefJourOuvreService().isJourOuvre(
						besoinFormationDto.getDateFin())) {
			GRHMessagesUtils
					.showErrorMessage("besoin_formation_date_fin_invalide");
			result = false;
		}
		if(besoinFormationDto.getDateDebut().after(besoinFormationDto.getDateFin())){
			GRHMessagesUtils
			.showErrorMessage("besoin_formation_date_debut_fin_invalide");
	result = false;
		}
		return result;
	}
	
	private void addCorps(){
		for(DetailBesoinFormationDto detail: listFormationsStructure){
			GradeDto gradeDto =  serviceLocator.getGradeService().findById(detail.getGradeDto().getId());
			detail.setCorpsDto(gradeDto.getCorpsDto());
		}
	}
	
	private void verifyTypeBesoin() {
		NomenclatureDto typeBesoin = serviceLocator.getNomenclatureService()
				.findById(besoinFormationDto.getNomenclatureDto().getId());
		if (typeBesoin == null) {
			renderStrucrure = false;
			renderGroupe = false;
			renderEmploye = false;
		} else if (typeBesoin.getCode().equals(
				UtilConstant.NC_GRH_BESOIN_STRUCTURE_VALUE)) {
			renderStrucrure = true;
			renderGroupe = false;
			renderEmploye = false;
		} else if (typeBesoin.getCode().equals(
				UtilConstant.NC_GRH_BESOIN_GROUPE_VALUE)) {
			renderStrucrure = false;
			renderGroupe = true;
			renderEmploye = false;

		} else if (typeBesoin.getCode().equals(
				UtilConstant.NC_GRH_BESOIN_INDIVIDUEL_VALUE)) {
			renderStrucrure = false;
			renderGroupe = false;
			renderEmploye = true;

		}
	}

	public void onRowSelect(SelectEvent event) {
		DossierEmployeDto dossierEmployeDto = (DossierEmployeDto) event
				.getObject();
		besoinFormationDto.setDossierEmployeDto(dossierEmployeDto);

	}

	public void onRowGroupSelect(SelectEvent event) {
		RefGroupeDto refGroupeDto = (RefGroupeDto) event.getObject();
		besoinFormationDto.setRefGroupeDto(refGroupeDto);
	}

	public void onRowStructureSelect(SelectEvent event) {
		RefStructureDto refStructureDto = (RefStructureDto) event.getObject();
		besoinFormationDto.setRefStructureDto(refStructureDto);
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

	public void searchStructure() {
		NomenclatureDto typeGroupeRecherche = serviceLocator
				.getNomenclatureService().findByCode(
						UtilConstant.NC_TYPE_GROUPE_NAME,
						UtilConstant.NC_TYPE_GROUPE_RECHERCHE_CODE);
		if (typeGroupeRecherche != null) {
			Integer idTypeGroupeRecherche = typeGroupeRecherche.getId();
			if (idTypeGroupeRecherche != null) {
				listGroupesReferentiel = serviceLocator.getRefGroupeService()
						.findGeneric(sessionBean.getIdEtablissement(),
								refGroupSearchKeyWord, idTypeGroupeRecherche);
			}
		}
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

	

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public BesoinFormationDto getBesoinFormationDto() {
		return besoinFormationDto;
	}

	public void setBesoinFormationDto(BesoinFormationDto besoinFormationDto) {
		this.besoinFormationDto = besoinFormationDto;
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

	public void setSelectedGroupeReferentiel(
			RefGroupeDto selectedGroupeReferentiel) {
		this.selectedGroupeReferentiel = selectedGroupeReferentiel;
	}

	public List<RefGroupeDto> getListGroupesReferentiel() {
		return listGroupesReferentiel;
	}

	public void setListGroupesReferentiel(
			List<RefGroupeDto> listGroupesReferentiel) {
		this.listGroupesReferentiel = listGroupesReferentiel;
	}

	public String getRefGroupSearchKeyWord() {
		return refGroupSearchKeyWord;
	}

	public void setRefGroupSearchKeyWord(String refGroupSearchKeyWord) {
		this.refGroupSearchKeyWord = refGroupSearchKeyWord;
	}

	public RefStructureDto getSelectedStructureReferentiel() {
		return selectedStructureReferentiel;
	}

	public void setSelectedStructureReferentiel(
			RefStructureDto selectedStructureReferentiel) {
		this.selectedStructureReferentiel = selectedStructureReferentiel;
	}

	public List<RefStructureDto> getListStructuresReferentiel() {
		return listStructuresReferentiel;
	}

	public void setListStructuresReferentiel(
			List<RefStructureDto> listStructuresReferentiel) {
		this.listStructuresReferentiel = listStructuresReferentiel;
	}

	public String getRefStructureSearchKeyWord() {
		return refStructureSearchKeyWord;
	}

	public void setRefStructureSearchKeyWord(String refStructureSearchKeyWord) {
		this.refStructureSearchKeyWord = refStructureSearchKeyWord;
	}

	public Integer getSelectedStructureId() {
		return selectedStructureId;
	}

	public void setSelectedStructureId(Integer selectedStructureId) {
		this.selectedStructureId = selectedStructureId;
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

	public boolean isRenderEmploye() {
		return renderEmploye;
	}

	public void setRenderEmploye(boolean renderEmploye) {
		this.renderEmploye = renderEmploye;
	}

	public boolean isRenderStrucrure() {
		return renderStrucrure;
	}

	public void setRenderStrucrure(boolean renderStrucrure) {
		this.renderStrucrure = renderStrucrure;
	}

	public boolean isRenderGroupe() {
		return renderGroupe;
	}

	public void setRenderGroupe(boolean renderGroupe) {
		this.renderGroupe = renderGroupe;
	}

	public List<DetailBesoinFormationDto> getListFormationsStructure() {
		return listFormationsStructure;
	}

	public void setListFormationsStructure(
			List<DetailBesoinFormationDto> listFormationsStructure) {
		this.listFormationsStructure = listFormationsStructure;
	}

	public List<DetailBesoinFormationDto> getListFormationsGroupe() {
		return listFormationsGroupe;
	}

	public void setListFormationsGroupe(
			List<DetailBesoinFormationDto> listFormationsGroupe) {
		this.listFormationsGroupe = listFormationsGroupe;
	}

	public List<DetailBesoinFormationDto> getListFormationsEmploye() {
		return listFormationsEmploye;
	}

	public void setListFormationsEmploye(
			List<DetailBesoinFormationDto> listFormationsEmploye) {
		this.listFormationsEmploye = listFormationsEmploye;
	}

	// public DetailBesoinFormationDto getDetailBesoinFormationDto() {
	// return detailBesoinFormationDto;
	// }
	//
	// public void setDetailBesoinFormationDto(
	// DetailBesoinFormationDto detailBesoinFormationDto) {
	// this.detailBesoinFormationDto = detailBesoinFormationDto;
	// }

	public List<SelectItem> getListFormationsCatalogue() {
		return listFormationsCatalogue;
	}

	public void setListFormationsCatalogue(
			List<SelectItem> listFormationsCatalogue) {
		this.listFormationsCatalogue = listFormationsCatalogue;
	}

	public List<SelectItem> getListGrades() {
		return listGrades;
	}

	public void setListGrades(List<SelectItem> listGrades) {
		this.listGrades = listGrades;
	}

	public Integer getSelectedFormationStructureIndex() {
		return selectedFormationStructureIndex;
	}

	public void setSelectedFormationStructureIndex(
			Integer selectedFormationStructureIndex) {
		this.selectedFormationStructureIndex = selectedFormationStructureIndex;
	}

	public Integer getSelectedFormationGroupeIndex() {
		return selectedFormationGroupeIndex;
	}

	public void setSelectedFormationGroupeIndex(
			Integer selectedFormationGroupeIndex) {
		this.selectedFormationGroupeIndex = selectedFormationGroupeIndex;
	}

	public Integer getSelectedFormationEmployeIndex() {
		return selectedFormationEmployeIndex;
	}

	public void setSelectedFormationEmployeIndex(
			Integer selectedFormationEmployeIndex) {
		this.selectedFormationEmployeIndex = selectedFormationEmployeIndex;
	}

}
