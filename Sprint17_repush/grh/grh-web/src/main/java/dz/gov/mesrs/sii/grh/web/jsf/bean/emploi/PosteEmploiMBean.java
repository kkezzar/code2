package dz.gov.mesrs.sii.grh.web.jsf.bean.emploi;

import java.io.Serializable;
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
import dz.gov.mesrs.sii.grh.business.model.dto.ConnaissancePosteDto;
import dz.gov.mesrs.sii.grh.business.model.dto.HabiletePosteDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PosteEmploiDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

@ManagedBean(name = "posteEmploiMBean")
@ViewScoped
public class PosteEmploiMBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 5703351630036216896L;
	private String searchKeyWords;
	private List<SelectItem> niveauQualifications;
	private List<SelectItem> niveauCompetences;
	private List<SelectItem> typeConnaissances;
	private List<SelectItem> typeHabiletes;
	private List<SelectItem> niveauHabiletes;
	private LazyDataModel<PosteEmploiDto> posteEmploiDtos;
	private PosteEmploiDto posteEmploiDto;
	private Integer connaissanceSelectedIndex;
	private Integer habileteSelectedIndex;
	private Integer etablissementId;
	private HabiletePosteDto habileteDto;
	private ConnaissancePosteDto connaissanceDto;

	@PostConstruct
	public void init() {
		niveauQualifications = super.getNiveauQualificationList();
		niveauCompetences = super.getNiveauCompetenceList();
		typeConnaissances = super.getTypeConnaissanceList();
		typeHabiletes = super.getTypeHabileteList();
		niveauHabiletes = super.getNiveauHabileteList();
		etablissementId = getSessionBean().getCompte().getEtabId();
		onSearch();
		habileteDto = new HabiletePosteDto(new NomenclatureDto(), new NomenclatureDto(), posteEmploiDto);
		connaissanceDto = new ConnaissancePosteDto(new NomenclatureDto(), posteEmploiDto);

	}

	// Ajax
	public void onSearch() {
		posteEmploiDto = null;
		posteEmploiDtos = new LazyDataModel<PosteEmploiDto>() {

			private static final long serialVersionUID = -4057486218735311521L;

			@Override
			public List<PosteEmploiDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				posteEmploiDtos.setRowCount(serviceLocator.getPosteEmploiService().countByKeyWord(etablissementId,
						searchKeyWords));
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				return serviceLocator.getPosteEmploiService().findAllByKeyWord(etablissementId, searchKeyWords,
						searchMode);
			}

		};
	}

	public void onNewConnaissance() {
		connaissanceDto = new ConnaissancePosteDto(new NomenclatureDto(), posteEmploiDto);
	}

	public void onSaveConnaissance() {
		posteEmploiDto.addConnaissanceDto(connaissanceDto);
		PosteEmploiDto result = serviceLocator.getPosteEmploiService().save(posteEmploiDto);
		posteEmploiDto.setConnaissanceDtos(result.getConnaissanceDtos());
		CommonMessagesUtils.showSuccessSaveMessage();
	}

	public void onNewHabilete() {
		habileteDto = new HabiletePosteDto(new NomenclatureDto(), new NomenclatureDto(), posteEmploiDto);
	}

	public void onSaveHabilete() {
		posteEmploiDto.addHabileteDto(habileteDto);
		PosteEmploiDto result = serviceLocator.getPosteEmploiService().save(posteEmploiDto);
		posteEmploiDto.setHabileteDtos(result.getHabileteDtos());
		CommonMessagesUtils.showSuccessSaveMessage();
	}

	public void onRemoveConnaissance() {
		posteEmploiDto.getConnaissanceDtos().remove(connaissanceSelectedIndex.intValue());
		serviceLocator.getPosteEmploiService().save(posteEmploiDto);
		CommonMessagesUtils.showSuccessDeleteMessage();

	}

	public void onRemoveHabilete() {
		posteEmploiDto.getHabileteDtos().remove(habileteSelectedIndex.intValue());
		serviceLocator.getPosteEmploiService().save(posteEmploiDto);
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	public void onSave() {
		posteEmploiDto = serviceLocator.getPosteEmploiService().save(posteEmploiDto);
		CommonMessagesUtils.showSuccessSaveMessage();
	}

	public void onRowSelected(SelectEvent event) {
		posteEmploiDto = (PosteEmploiDto) event.getObject();
		if (posteEmploiDto.getNiveauCompetenceDto() == null) {
			posteEmploiDto.setNiveauCompetenceDto(new NomenclatureDto());
		}
		if (posteEmploiDto.getNiveauQualificationDto() == null) {
			posteEmploiDto.setNiveauQualificationDto(new NomenclatureDto());
		}
	}

	public void onNew() {
		RefEtablissementDto etablissementDto = new RefEtablissementDto();
		etablissementDto.setId(etablissementId);

		posteEmploiDto = new PosteEmploiDto(new NomenclatureDto(), new NomenclatureDto(), etablissementDto);
	}

	// private

	// Getters & setters
	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public List<SelectItem> getNiveauQualifications() {
		return niveauQualifications;
	}

	public void setNiveauQualifications(List<SelectItem> niveauQualifications) {
		this.niveauQualifications = niveauQualifications;
	}

	public List<SelectItem> getNiveauCompetences() {
		return niveauCompetences;
	}

	public void setNiveauCompetences(List<SelectItem> niveauCompetences) {
		this.niveauCompetences = niveauCompetences;
	}

	public List<SelectItem> getTypeConnaissances() {
		return typeConnaissances;
	}

	public void setTypeConnaissances(List<SelectItem> typeConnaissances) {
		this.typeConnaissances = typeConnaissances;
	}

	public List<SelectItem> getTypeHabiletes() {
		return typeHabiletes;
	}

	public void setTypeHabiletes(List<SelectItem> typeHabiletes) {
		this.typeHabiletes = typeHabiletes;
	}

	public List<SelectItem> getNiveauHabiletes() {
		return niveauHabiletes;
	}

	public void setNiveauHabiletes(List<SelectItem> niveauHabiletes) {
		this.niveauHabiletes = niveauHabiletes;
	}

	public LazyDataModel<PosteEmploiDto> getPosteEmploiDtos() {
		return posteEmploiDtos;
	}

	public void setPosteEmploiDtos(LazyDataModel<PosteEmploiDto> posteEmploiDtos) {
		this.posteEmploiDtos = posteEmploiDtos;
	}

	public PosteEmploiDto getPosteEmploiDto() {
		return posteEmploiDto;
	}

	public void setPosteEmploiDto(PosteEmploiDto posteEmploiDto) {
		this.posteEmploiDto = posteEmploiDto;
	}

	public Integer getConnaissanceSelectedIndex() {
		return connaissanceSelectedIndex;
	}

	public void setConnaissanceSelectedIndex(Integer connaissanceSelectedIndex) {
		this.connaissanceSelectedIndex = connaissanceSelectedIndex;
	}

	public Integer getHabileteSelectedIndex() {
		return habileteSelectedIndex;
	}

	public void setHabileteSelectedIndex(Integer habileteSelectedIndex) {
		this.habileteSelectedIndex = habileteSelectedIndex;
	}

	public ConnaissancePosteDto getConnaissanceDto() {
		return connaissanceDto;
	}

	public void setConnaissanceDto(ConnaissancePosteDto connaissanceDto) {
		this.connaissanceDto = connaissanceDto;
	}

	public HabiletePosteDto getHabileteDto() {
		return habileteDto;
	}

	public void setHabileteDto(HabiletePosteDto habileteDto) {
		this.habileteDto = habileteDto;
	}

}
