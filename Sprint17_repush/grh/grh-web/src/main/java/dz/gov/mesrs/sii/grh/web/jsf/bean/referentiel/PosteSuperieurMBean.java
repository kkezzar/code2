package dz.gov.mesrs.sii.grh.web.jsf.bean.referentiel;

import java.util.ArrayList;
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
import dz.gov.mesrs.sii.grh.business.model.dto.CategorieProfessionnelleDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CorpsDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PosteSuperieureDto;
import dz.gov.mesrs.sii.grh.business.model.dto.StatutDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

@ManagedBean(name = "posteSuperieurMBean")
@ViewScoped
public class PosteSuperieurMBean extends BaseBean {

	private static final long serialVersionUID = -531388248240833840L;

	private LazyDataModel<PosteSuperieureDto> posteSuperieureDtos;
	private PosteSuperieureDto posteSuperieureDto;

	private List<SelectItem> statuts;
	private List<SelectItem> posteSuperieurTypes;
	private List<SelectItem> corps;
	private List<SelectItem> categorieSuperieures;

	private Integer selectedStatutId;

	private String searchKeyWords;
	private boolean displayDataTable;

	@PostConstruct
	public void init() {
		initStatutList();
		posteSuperieurTypes = findPosteSuperieurTypeList();
		initCategorieSuperieures();
		onSearch();

	}

	private void initCategorieSuperieures() {
		categorieSuperieures = null;
		CategorieProfessionnelleDto exampleDto = new CategorieProfessionnelleDto();
		NomenclatureDto typeCategorieDto = new NomenclatureDto();
		typeCategorieDto.setCode(UtilConstant.NC_GRH_TYPE_CATEGORIE_PRO_SUP_VALUE);
		exampleDto.setNomenclatureByTypeCategorieDto(typeCategorieDto);
		List<CategorieProfessionnelleDto> dtos = serviceLocator.getCategorieProfessionnelleService().findAllByExample(
				exampleDto);
		if (dtos != null && dtos.size() > 0) {
			categorieSuperieures = new ArrayList<>();
			for (CategorieProfessionnelleDto dto : dtos) {
				categorieSuperieures.add(new SelectItem(dto.getId(), dto.getNomenclatureByCategorieDto()
						.getLibelleLongFr()));
			}
		}

	}

	private void initStatutList() {
		statuts = new ArrayList<>();
		List<StatutDto> statutDtos = serviceLocator.getStatutService().getListValideStatuts();
		for (StatutDto dto : statutDtos) {
			statuts.add(new SelectItem(dto.getId(), dto.getNumero() + " " + dto.getAnnee()));

		}

	}

	public void onStatutSelected() {
		if (selectedStatutId != null) {
			corps = initCorpsList();
		}

	}

	private List<SelectItem> initCorpsList() {
		corps = null;
		CorpsDto corpsDto = new CorpsDto();
		StatutDto statutDto = new StatutDto(selectedStatutId);
		corpsDto.setStatut(statutDto);
		List<CorpsDto> corpsDtos = serviceLocator.getCorpsService().findAllByExample(corpsDto);
		if (corpsDtos != null && corpsDtos.size() > 0) {
			corps = new ArrayList<>();
			for (CorpsDto dto : corpsDtos) {
				corps.add(new SelectItem(dto.getId(), dto.getNomenclatureByIdNomenclatureCorps().getLibelleLongFr()));
			}
		}
		return corps;
	}

	public void onSearch() {
		displayDataTable = true;
		// TODO faire le search by key
		posteSuperieureDtos = new LazyDataModel<PosteSuperieureDto>() {

			private static final long serialVersionUID = 7110204523164476970L;

			@Override
			public List<PosteSuperieureDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				posteSuperieureDtos.setRowCount(serviceLocator.getPosteSuperieureService().countAllByExample(
						new PosteSuperieureDto()));
				SearchMode searchMode = new SearchMode(pageSize, first);
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

				return serviceLocator.getPosteSuperieureService()
						.findAllByExample(new PosteSuperieureDto(), searchMode);
			}

		};

	}

	public void onPosteSuperieurSelected(SelectEvent event) {
		posteSuperieureDto = (PosteSuperieureDto) event.getObject();
		selectedStatutId = posteSuperieureDto.getCorpsDto().getStatut().getId();
		onStatutSelected();
	}

	public void onNew() {
		selectedStatutId = null;
		corps = null;
		posteSuperieureDto = new PosteSuperieureDto();
		posteSuperieureDto.setCategorieProfessionnelleDto(new CategorieProfessionnelleDto());
		posteSuperieureDto.setCorpsDto(new CorpsDto());
		posteSuperieureDto.setTypePosteSuperieurDto(new NomenclatureDto());
	}

	public void onDelete() {
		serviceLocator.getPosteSuperieureService().remove(posteSuperieureDto);
		posteSuperieureDto = null;
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	public void onSave() {

		posteSuperieureDto = serviceLocator.getPosteSuperieureService().save(posteSuperieureDto);
		CommonMessagesUtils.showSuccessSaveMessage();
	}

	public LazyDataModel<PosteSuperieureDto> getPosteSuperieureDtos() {
		return posteSuperieureDtos;
	}

	public void setPosteSuperieureDtos(LazyDataModel<PosteSuperieureDto> posteSuperieureDtos) {
		this.posteSuperieureDtos = posteSuperieureDtos;
	}

	public PosteSuperieureDto getPosteSuperieureDto() {
		return posteSuperieureDto;
	}

	public void setPosteSuperieureDto(PosteSuperieureDto posteSuperieureDto) {
		this.posteSuperieureDto = posteSuperieureDto;
	}

	public List<SelectItem> getStatuts() {
		return statuts;
	}

	public void setStatuts(List<SelectItem> statuts) {
		this.statuts = statuts;
	}

	public List<SelectItem> getPosteSuperieurTypes() {
		return posteSuperieurTypes;
	}

	public void setPosteSuperieurTypes(List<SelectItem> posteSuperieurTypes) {
		this.posteSuperieurTypes = posteSuperieurTypes;
	}

	public List<SelectItem> getCorps() {
		return corps;
	}

	public void setCorps(List<SelectItem> corps) {
		this.corps = corps;
	}

	public List<SelectItem> getCategorieSuperieures() {
		return categorieSuperieures;
	}

	public void setCategorieSuperieures(List<SelectItem> categorieSuperieures) {
		this.categorieSuperieures = categorieSuperieures;
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
