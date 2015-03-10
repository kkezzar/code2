package dz.gov.mesrs.sii.grh.web.jsf.bean.formation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.grh.business.model.dto.CatalogueFormationDto;
import dz.gov.mesrs.sii.grh.business.model.dto.FormationCatalogueDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@ManagedBean(name = "catalogueFormationMBean")
@ViewScoped
public class CatalogueFormationMBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private CatalogueFormationDto catalogueFormationDto;
	private List<CatalogueFormationDto> listeCatalogueFormation;
	private FormationCatalogueDto formationCatalogueDto;
	private List<FormationCatalogueDto> listFormations;
	private List<SelectItem> listeNcTheme;
	private List<SelectItem> listeNcMethode;
	private Integer selectedFormationIndex;

	public CatalogueFormationMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		initSelectItemLists();
		onSearch();
	}

	private void initSelectItemLists() {
		listeNcTheme = findListeNcThemeFormation();
		listeNcMethode = findListeNcMethodeFormation();
	}

	public void initDetail() {
		catalogueFormationDto = null;
		formationCatalogueDto = null;
		listFormations = null;
		selectedFormationIndex = null;
	}

	public void onNew() {
		catalogueFormationDto = new CatalogueFormationDto();
		this.listFormations = catalogueFormationDto.getFormationCatalogueDtos();
		catalogueFormationDto.setStatut(true);
		formationCatalogueDto = new FormationCatalogueDto();
		formationCatalogueDto.setNomenclatureByMethodeDto(new NomenclatureDto());
		formationCatalogueDto.setNomenclatureByThemeDto(new NomenclatureDto());
	}
	
	public void onSearch() {
		try {
			loadlisteCatalogueFormation();
			initDetail();
		} catch (Exception e) {

		}
	}

	private void loadlisteCatalogueFormation() {
		listeCatalogueFormation = serviceLocator.getCatalogueFormationService()
				.findAllByKeyWord(searchKeyWords, null);
	}

	public void onCatalogueSelect(SelectEvent event) {
		catalogueFormationDto = (CatalogueFormationDto) event.getObject();
		this.listFormations = catalogueFormationDto.getFormationCatalogueDtos();
		formationCatalogueDto = new FormationCatalogueDto();
		formationCatalogueDto.setNomenclatureByMethodeDto(new NomenclatureDto());
		formationCatalogueDto.setNomenclatureByThemeDto(new NomenclatureDto());
	}

	public void onSave() {
		catalogueFormationDto.setFormationCatalogueDtos(this.listFormations);
		serviceLocator.getCatalogueFormationService().save(
				catalogueFormationDto);
		loadlisteCatalogueFormation();
		initDetail();
		CommonMessagesUtils.showSuccessSaveMessage();

	}

	public void onAddFormation() {
		formationCatalogueDto = new FormationCatalogueDto();
		formationCatalogueDto.setNomenclatureByMethodeDto(new NomenclatureDto());
		formationCatalogueDto.setNomenclatureByThemeDto(new NomenclatureDto());
	}

	public void onFormationSelect(SelectEvent event) {
		formationCatalogueDto = (FormationCatalogueDto) event.getObject();

	}

	public void onSaveFormation() {

		if (formationCatalogueDto.getId() == null) {
			if (this.listFormations == null) {
				this.listFormations = new ArrayList<FormationCatalogueDto>();
			}
			formationCatalogueDto
					.setCatalogueFormationDto(catalogueFormationDto);
			this.listFormations.add(formationCatalogueDto);
//			formationCatalogueDto = new FormationCatalogueDto();
//			formationCatalogueDto.setNomenclatureByMethodeDto(new NomenclatureDto());
//			formationCatalogueDto.setNomenclatureByThemeDto(new NomenclatureDto());
		}
	}

	public void removeFormation() {
		this.formationCatalogueDto = this.listFormations.get(selectedFormationIndex);
		this.listFormations.remove(this.formationCatalogueDto);

	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public CatalogueFormationDto getCatalogueFormationDto() {
		return catalogueFormationDto;
	}

	public void setCatalogueFormationDto(
			CatalogueFormationDto catalogueFormationDto) {
		this.catalogueFormationDto = catalogueFormationDto;
	}

	public List<CatalogueFormationDto> getListeCatalogueFormation() {
		return listeCatalogueFormation;
	}

	public void setListeCatalogueFormation(
			List<CatalogueFormationDto> listeCatalogueFormation) {
		this.listeCatalogueFormation = listeCatalogueFormation;
	}

	public List<FormationCatalogueDto> getListFormations() {
		return listFormations;
	}

	public void setListFormations(List<FormationCatalogueDto> listFormations) {
		this.listFormations = listFormations;
	}

	public List<SelectItem> getListeNcTheme() {
		return listeNcTheme;
	}

	public void setListeNcTheme(List<SelectItem> listeNcTheme) {
		this.listeNcTheme = listeNcTheme;
	}

	public List<SelectItem> getListeNcMethode() {
		return listeNcMethode;
	}

	public void setListeNcMethode(List<SelectItem> listeNcMethode) {
		this.listeNcMethode = listeNcMethode;
	}

	public Integer getSelectedFormationIndex() {
		return selectedFormationIndex;
	}

	public void setSelectedFormationIndex(Integer selectedFormationIndex) {
		this.selectedFormationIndex = selectedFormationIndex;
	}

	public FormationCatalogueDto getFormationCatalogueDto() {
		return formationCatalogueDto;
	}

	public void setFormationCatalogueDto(
			FormationCatalogueDto formationCatalogueDto) {
		this.formationCatalogueDto = formationCatalogueDto;
	}

}
