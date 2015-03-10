package dz.gov.mesrs.sii.grh.web.jsf.bean.referentiel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.CategorieNiveauDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CategorieProfessionnelleDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GrilleIndiciaireDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

@ManagedBean(name = "categorieMBean")
@ViewScoped
public class CategorieMBean extends BaseBean {

	private static final long serialVersionUID = 7498735332112916007L;

	private List<SelectItem> groupes;
	private List<SelectItem> typeCategories;
	private List<SelectItem> categories;
	private List<NomenclatureDto> niveaux;
	private NomenclatureDto[] selectedNiveaux;
	private List<NomenclatureDto> echelons;

	// private LazyDataModel<CategorieProfessionnelleDto> categorieDtos;
	private List<CategorieProfessionnelleDto> categorieDtos;
	private CategorieProfessionnelleDto categorieDto;

	private boolean displayDataTable;
	private String searchKeyWords;

	@PostConstruct
	public void init() {
		groupes = findGroupeList();
		typeCategories = findTypeCategorieList();
		categories = findCategorieProCode();
		onSearch();
	}

	public void onNew() {
		categorieDto = new CategorieProfessionnelleDto();
		categorieDto.setNomenclatureByCategorieDto(new NomenclatureDto());
		categorieDto.setNomenclatureByGroupeDto(new NomenclatureDto());
		categorieDto.setNomenclatureByTypeCategorieDto(new NomenclatureDto());
		echelons = this.serviceLocator.getNomenclatureService().findByName(
				UtilConstant.NC_GRH_CATEGORIE_PRO_ECHELON_NAME, "id");
		for (NomenclatureDto echelonDto : echelons) {
			GrilleIndiciaireDto grilleIndiciaireDto = new GrilleIndiciaireDto();
			grilleIndiciaireDto.setEchlon(echelonDto.getLibelleLongFr());
			categorieDto.addGrilleIndiciaireDto(grilleIndiciaireDto);
		}

		niveaux = this.serviceLocator.getNomenclatureService().findByName(
				UtilConstant.NC_GRH_CATEGORIE_NIVEAU_QUALIFICATION, "id");
		selectedNiveaux = null;
	}

	public void onSave() {
		if (categorieDto.getNomenclatureByTypeCategorieDto().getId() == null) {
			categorieDto.setNomenclatureByTypeCategorieDto(null);
		}
		categorieDto.setCategorieNiveauDtos(null);
		for (NomenclatureDto dto : selectedNiveaux) {
			CategorieNiveauDto categorieNiveauDto = new CategorieNiveauDto();
			categorieNiveauDto.setNomenclatureDto(dto);
			categorieDto.addCategorieNiveauDto(categorieNiveauDto);
		}
		categorieDto = this.serviceLocator.getCategorieProfessionnelleService().save(categorieDto);
		if (categorieDto.getNomenclatureByTypeCategorieDto() == null) {
			categorieDto.setNomenclatureByTypeCategorieDto(new NomenclatureDto());
		}
		onSearch();
		CommonMessagesUtils.showSuccessSaveMessage();
	}

	public void onDelete() {
		this.serviceLocator.getCategorieProfessionnelleService().remove(categorieDto);
		categorieDto = null;
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	public void onSearch() {
		displayDataTable = true;
		categorieDto = null;
		selectedNiveaux = null;
		// categorieDtos = new LazyDataModel<CategorieProfessionnelleDto>() {
		//
		// private static final long serialVersionUID = 2324926971755676528L;
		//
		// @Override
		// public List<CategorieProfessionnelleDto> load(int first, int
		// pageSize, String sortField,
		// SortOrder sortOrder, Map<String, String> filters) {
		// // TODO faire le search
		// categorieDtos.setRowCount(serviceLocator.getCategorieProfessionnelleService().countAllByExample(
		// new CategorieProfessionnelleDto()));
		// SearchMode searchMode = new SearchMode(pageSize, first);
		// if (!StringUtils.isEmpty(sortField)) {
		// SearchMode.SortOrder order = null;
		// if (sortOrder != null) {
		// if (sortOrder.equals(SortOrder.DESCENDING)) {
		// order = SearchMode.SortOrder.DESC;
		// } else {
		// order = SearchMode.SortOrder.ASC;
		// }
		//
		// }
		// SearchMode.SortField sort = new SearchMode.SortField(sortField,
		// order);
		// searchMode.addSortField(sort);
		// }
		// return
		// serviceLocator.getCategorieProfessionnelleService().findAllByExample(
		// new CategorieProfessionnelleDto());
		// }
		//
		// };
		categorieDtos = serviceLocator.getCategorieProfessionnelleService().findAll();
	}

	public void onCategorieSelected(SelectEvent event) {
		categorieDto = (CategorieProfessionnelleDto) event.getObject();
		niveaux = this.serviceLocator.getNomenclatureService().findByName(
				UtilConstant.NC_GRH_CATEGORIE_NIVEAU_QUALIFICATION, "id");
		List<NomenclatureDto> dtos = new ArrayList<>();
		for (CategorieNiveauDto niveauDto : categorieDto.getCategorieNiveauDtos()) {
			dtos.add(niveauDto.getNomenclatureDto());
		}
		if (categorieDto.getNomenclatureByTypeCategorieDto() == null) {
			categorieDto.setNomenclatureByTypeCategorieDto(new NomenclatureDto());
		}
		selectedNiveaux = new NomenclatureDto[dtos.size()];
		selectedNiveaux = dtos.toArray(selectedNiveaux);
		 categorieDto.getGrilleIndiciaireDtos();

	}

	public List<SelectItem> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<SelectItem> groupes) {
		this.groupes = groupes;
	}

	public List<SelectItem> getTypeCategories() {
		return typeCategories;
	}

	public void setTypeCategories(List<SelectItem> typeCategories) {
		this.typeCategories = typeCategories;
	}

	public List<SelectItem> getCategories() {
		return categories;
	}

	public void setCategories(List<SelectItem> categories) {
		this.categories = categories;
	}

	// public LazyDataModel<CategorieProfessionnelleDto> getCategorieDtos() {
	// return categorieDtos;
	// }
	//
	// public void setCategorieDtos(LazyDataModel<CategorieProfessionnelleDto>
	// categorieDtos) {
	// this.categorieDtos = categorieDtos;
	// }

	public List<CategorieProfessionnelleDto> getCategorieDtos() {
		return categorieDtos;
	}

	public void setCategorieDtos(List<CategorieProfessionnelleDto> categorieDtos) {
		this.categorieDtos = categorieDtos;
	}

	public CategorieProfessionnelleDto getCategorieDto() {
		return categorieDto;
	}

	public void setCategorieDto(CategorieProfessionnelleDto categorieDto) {
		this.categorieDto = categorieDto;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public List<NomenclatureDto> getNiveaux() {
		return niveaux;
	}

	public void setNiveaux(List<NomenclatureDto> niveaux) {
		this.niveaux = niveaux;
	}

	public List<NomenclatureDto> getEchelons() {
		return echelons;
	}

	public void setEchelons(List<NomenclatureDto> echelons) {
		this.echelons = echelons;
	}

	public NomenclatureDto[] getSelectedNiveaux() {
		return selectedNiveaux;
	}

	public void setSelectedNiveaux(NomenclatureDto[] selectedNiveaux) {
		this.selectedNiveaux = selectedNiveaux;
	}

	public boolean isDisplayDataTable() {
		return displayDataTable;
	}

	public void setDisplayDataTable(boolean displayDataTable) {
		this.displayDataTable = displayDataTable;
	}

}
