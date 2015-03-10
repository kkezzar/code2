package dz.gov.mesrs.sii.grh.web.jsf.bean.competence;

import java.io.Serializable;
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
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.CarriereDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CategorieNiveauDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CategorieProfessionnelleDto;
import dz.gov.mesrs.sii.grh.business.model.dto.ConnaissanceEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.HabileteEmployeDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

@ManagedBean(name = "employeCompetenceMBean")
@ViewScoped
public class EmployeCompetenceMBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -1503677456393173173L;
	private DossierEmployeDto dto;
	private DossierEmployeDto searchDto;
	private LazyDataModel<DossierEmployeDto> dtos;
	private String searchKeyWords;
	private HabileteEmployeDto habileteDto;
	private ConnaissanceEmployeDto connaissanceDto;
	private List<SelectItem> niveauQualificationList;
	private List<SelectItem> niveauCompetenceList;
	private List<SelectItem> typeConnaissanceList;
	private List<SelectItem> typeHabileteList;
	private List<SelectItem> niveauHabileteList;
	private static  final String COMPTENCE_BUNDLE_MSG_NAME = "competenceMsgs"; 
	private Integer connaissanceSelectIndex;
	private Integer habileteSelectIndex;

	@PostConstruct
	public void init() {
		initStaticLists();
		searchDto = new DossierEmployeDto();
		searchDto.setRefEtablissementDto(sessionBean.getRefEtablissementDto());
		onSearch();
		habileteDto = new HabileteEmployeDto(new NomenclatureDto(), new NomenclatureDto(), dto);
		connaissanceDto = new ConnaissanceEmployeDto(new NomenclatureDto(), dto);

	}


	// Ajax
	public void onSave() {
		dto = serviceLocator.getDossierEmployeService().save(dto);
		CommonMessagesUtils.showSuccessSaveMessage();

	}

	public void onSearch() {
		dto = null;
		dtos = new LazyDataModel<DossierEmployeDto>() {

			private static final long serialVersionUID = 1098023221644023567L;

			@Override
			public List<DossierEmployeDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				dtos.setRowCount(serviceLocator.getDossierEmployeService().countAllByExample(searchDto));
				return serviceLocator.getDossierEmployeService().findAllByExample(searchDto, searchMode);
			}
		};

	}

	public void onRowSelected(SelectEvent event) {
		dto = (DossierEmployeDto) event.getObject();
		if (dto.getNiveauCompetenceDto() == null) {
			dto.setNiveauCompetenceDto(new NomenclatureDto());
		}
		if (dto.getNiveauQualificationDto() == null) {
			dto.setNiveauQualificationDto(new NomenclatureDto());
		}
		initNiveauQualificationList();
		connaissanceSelectIndex = null;
		habileteSelectIndex = null;
		
	}


	public void onNewHabilete() {
		habileteDto = new HabileteEmployeDto(new NomenclatureDto(), new NomenclatureDto(), dto);

	}

	public void onNewConnaissance() {
		connaissanceDto = new ConnaissanceEmployeDto(new NomenclatureDto(), dto);

	}
	
	public void onRemoveConnaissance(){
		dto.getConnaissanceDtos().remove(connaissanceSelectIndex.intValue());
		serviceLocator.getDossierEmployeService().save(dto);
		CommonMessagesUtils.showSuccessDeleteMessage();
		
	}
	public void onRemoveHabilete(){
		dto.getHabileteDtos().remove(habileteSelectIndex.intValue());
		serviceLocator.getDossierEmployeService().save(dto);
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	public void onSaveConnaissance() {
		dto.addConnaissanceDto(connaissanceDto);
		DossierEmployeDto result = serviceLocator.getDossierEmployeService().save(dto);
		dto.setConnaissanceDtos(result.getConnaissanceDtos());
		CommonMessagesUtils.showSuccessSaveMessage();
		
		
	}

	public void onSaveHabilete() {
		dto.addHabileteDto(habileteDto);
		DossierEmployeDto result = serviceLocator.getDossierEmployeService().save(dto);
		dto.setHabileteDtos(result.getHabileteDtos());
		CommonMessagesUtils.showSuccessSaveMessage();
	}

	// private
	
	private void initStaticLists() {
		niveauCompetenceList = super.getNiveauCompetenceList();
		typeConnaissanceList = super.getTypeConnaissanceList();
		niveauHabileteList  = super.getNiveauHabileteList();
		typeHabileteList = super.getTypeHabileteList();
		
	}
	
	private void initNiveauQualificationList() {
		niveauQualificationList = new ArrayList<>();
		CarriereDto carriereDto = dto.getCarriereCouranteDto();
		if(carriereDto == null){
			GRHMessagesUtils.showWarningMessage(COMPTENCE_BUNDLE_MSG_NAME, "msg_employe_sans_carriere_courante");
			return;
		}
		CategorieProfessionnelleDto categorieProfessionnelleDto = carriereDto.getCategorieProfessionnelleDto();
		if(categorieProfessionnelleDto == null){
			GRHMessagesUtils.showWarningMessage(COMPTENCE_BUNDLE_MSG_NAME, "msg_employe_sans_categorie_professionnelle");
			return;
		}
		List<CategorieNiveauDto> niveauDtos = categorieProfessionnelleDto.getCategorieNiveauDtos();
		if(niveauDtos == null || niveauDtos.isEmpty()){
			GRHMessagesUtils.showWarningMessage(COMPTENCE_BUNDLE_MSG_NAME, "msg_categorie_niveau_qualification");
		}else{
			for(CategorieNiveauDto dto : niveauDtos){
				niveauQualificationList.add(new SelectItem(dto.getNomenclatureDto().getId(),dto.getNomenclatureDto().getLibelleLongFr()));
			}
			
		}

	}


	// getters & setters
	public DossierEmployeDto getDto() {
		return dto;
	}

	public void setDto(DossierEmployeDto dto) {
		this.dto = dto;
	}

	public LazyDataModel<DossierEmployeDto> getDtos() {
		return dtos;
	}

	public void setDtos(LazyDataModel<DossierEmployeDto> dtos) {
		this.dtos = dtos;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public HabileteEmployeDto getHabileteDto() {
		return habileteDto;
	}

	public void setHabileteDto(HabileteEmployeDto habileteDto) {
		this.habileteDto = habileteDto;
	}

	public ConnaissanceEmployeDto getConnaissanceDto() {
		return connaissanceDto;
	}

	public void setConnaissanceDto(ConnaissanceEmployeDto connaissanceDto) {
		this.connaissanceDto = connaissanceDto;
	}

	public List<SelectItem> getNiveauQualificationList() {
		return niveauQualificationList;
	}

	public void setNiveauQualificationList(List<SelectItem> niveauQualificationList) {
		this.niveauQualificationList = niveauQualificationList;
	}

	public List<SelectItem> getNiveauCompetenceList() {
		return niveauCompetenceList;
	}

	public void setNiveauCompetenceList(List<SelectItem> niveauCompetenceList) {
		this.niveauCompetenceList = niveauCompetenceList;
	}

	public List<SelectItem> getTypeConnaissanceList() {
		return typeConnaissanceList;
	}

	public void setTypeConnaissanceList(List<SelectItem> typeConnaissanceList) {
		this.typeConnaissanceList = typeConnaissanceList;
	}

	public List<SelectItem> getTypeHabileteList() {
		return typeHabileteList;
	}

	public void setTypeHabileteList(List<SelectItem> typeHabileteList) {
		this.typeHabileteList = typeHabileteList;
	}

	public List<SelectItem> getNiveauHabileteList() {
		return niveauHabileteList;
	}

	public void setNiveauHabileteList(List<SelectItem> niveauHabileteList) {
		this.niveauHabileteList = niveauHabileteList;
	}

	public DossierEmployeDto getSearchDto() {
		return searchDto;
	}

	public void setSearchDto(DossierEmployeDto searchDto) {
		this.searchDto = searchDto;
	}


	public Integer getConnaissanceSelectIndex() {
		return connaissanceSelectIndex;
	}


	public void setConnaissanceSelectIndex(Integer connaissanceSelectIndex) {
		this.connaissanceSelectIndex = connaissanceSelectIndex;
	}


	public Integer getHabileteSelectIndex() {
		return habileteSelectIndex;
	}


	public void setHabileteSelectIndex(Integer habileteSelectIndex) {
		this.habileteSelectIndex = habileteSelectIndex;
	}
	
	
}
