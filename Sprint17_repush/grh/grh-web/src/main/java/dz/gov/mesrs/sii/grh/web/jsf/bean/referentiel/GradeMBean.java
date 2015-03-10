package dz.gov.mesrs.sii.grh.web.jsf.bean.referentiel;

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

import dz.gov.mesrs.sii.grh.business.model.dto.CategorieProfessionnelleDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CorpsDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GradeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.StatutDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

@ManagedBean(name = "gradeMBean")
@ViewScoped
public class GradeMBean extends BaseBean {

	private static final long serialVersionUID = -6332738976987206114L;

	private List<SelectItem> statuts;
	private List<SelectItem> grades;
	private List<SelectItem> corps;
	private List<SelectItem> categories;

//	private LazyDataModel<GradeDto> gradeDtos;
	private List<GradeDto> gradeDtos;
	private GradeDto gradeDto;

	private Integer selectedStatutId;

	private String searchKeyWords;
	private boolean displayDataTable;

	@PostConstruct
	public void init() {
		initStatutList();
		initCategorieList();
		initGradeList();
		onSearch();
	}

	private void initGradeList() {
		grades = this.findGradeList();

	}

	private void initCategorieList() {
		List<CategorieProfessionnelleDto> dtos = this.serviceLocator.getCategorieProfessionnelleService().findAll();
		categories = new ArrayList<>();
		for (CategorieProfessionnelleDto dto : dtos) {
			categories.add(new SelectItem(dto.getId(), dto.getNomenclatureByCategorieDto().getLibelleLongFr()));
		}
	}

	private void initStatutList() {
		List<StatutDto> dtos = this.serviceLocator.getStatutService().getListValideStatuts();
		statuts = new ArrayList<>();
		for (StatutDto dto : dtos) {
			statuts.add(new SelectItem(dto.getId(), dto.getNumero() + " " + dto.getAnnee()));
		}
	}

	public void onStatutSelected() {
		corps = null;
		CorpsDto exampleDto = new CorpsDto();
		StatutDto statutDto = new StatutDto(selectedStatutId);
		exampleDto.setStatut(statutDto);

		List<CorpsDto> corpsDtos = this.serviceLocator.getCorpsService().findAllByExample(exampleDto);
		if (corpsDtos == null || corpsDtos.isEmpty()) {
			// TODO afficher un message
		} else {
			corps = new ArrayList<>();
			for (CorpsDto corpsDto : corpsDtos) {
				corps.add(new SelectItem(corpsDto.getId(), corpsDto.getNomenclatureByIdNomenclatureCorps()
						.getLibelleLongFr()));
			}
		}
	}

	public void onGradeSelected(SelectEvent event) {
		gradeDto = (GradeDto) event.getObject();
		selectedStatutId = gradeDto.getCorpsDto().getStatut().getId();
		if (gradeDto.getCategorieProfessionnelleDto() == null) {
			gradeDto.setCategorieProfessionnelleDto(new CategorieProfessionnelleDto());
		}
		onStatutSelected();
	}

	public void onSearch() {
		displayDataTable = true;
		gradeDto = null;
//		gradeDtos = new LazyDataModel<GradeDto>() {
//
//			private static final long serialVersionUID = -1747349489034839629L;
//
//			@Override
//			public List<GradeDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
//					Map<String, String> filters) {
//				// TODO faire le seach by key words
//				gradeDtos.setRowCount(serviceLocator.getGradeService().countAllByExample(new GradeDto()));
//				return serviceLocator.getGradeService().findAllByExample(new GradeDto());
//			}
//
//		};
		gradeDtos = serviceLocator.getGradeService().findAll();
	}

	public void onNew() {
		selectedStatutId = null;
		gradeDto = new GradeDto();
		gradeDto.setCategorieProfessionnelleDto(new CategorieProfessionnelleDto());
		gradeDto.setCorpsDto(new CorpsDto());
		gradeDto.setNomenclatureDto(new NomenclatureDto());

	}

	public void onSave() {
		if (gradeDto.getCategorieProfessionnelleDto().getId() == null) {
			gradeDto.setCategorieProfessionnelleDto(null);
		}
		gradeDto = this.serviceLocator.getGradeService().save(gradeDto);
		if (gradeDto.getCategorieProfessionnelleDto() == null) {
			gradeDto.setCategorieProfessionnelleDto(new CategorieProfessionnelleDto());
		}
		onSearch();
		CommonMessagesUtils.showSuccessSaveMessage();

	}

	public void onDelete() {
		this.serviceLocator.getGradeService().remove(gradeDto);
		gradeDto = null;
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	public List<SelectItem> getStatuts() {
		return statuts;
	}

	public void setStatuts(List<SelectItem> statuts) {
		this.statuts = statuts;
	}

	public List<SelectItem> getGrades() {
		return grades;
	}

	public void setGrades(List<SelectItem> grades) {
		this.grades = grades;
	}

	public List<SelectItem> getCorps() {
		return corps;
	}

	public void setCorps(List<SelectItem> corps) {
		this.corps = corps;
	}

	public List<SelectItem> getCategories() {
		return categories;
	}

	public void setCategories(List<SelectItem> categories) {
		this.categories = categories;
	}

//	public LazyDataModel<GradeDto> getGradeDtos() {
//		return gradeDtos;
//	}
//
//	public void setGradeDtos(LazyDataModel<GradeDto> gradeDtos) {
//		this.gradeDtos = gradeDtos;
//	}

	public List<GradeDto> getGradeDtos() {
		return gradeDtos;
	}
	
	public void setGradeDtos(List<GradeDto> gradeDtos) {
		this.gradeDtos = gradeDtos;
	}
	
	public GradeDto getGradeDto() {
		return gradeDto;
	}

	public void setGradeDto(GradeDto gradeDto) {
		this.gradeDto = gradeDto;
	}

	public Integer getSelectedStatutId() {
		return selectedStatutId;
	}

	public void setSelectedStatutId(Integer selectedStatutId) {
		this.selectedStatutId = selectedStatutId;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public boolean isDisplayDataTable() {
		return displayDataTable;
	}

	public void setDisplayDataTable(boolean displayDataTable) {
		this.displayDataTable = displayDataTable;
	}

}
