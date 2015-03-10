package dz.gov.mesrs.sii.grh.web.jsf.bean.emploi;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.AffectationPosteDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PosteEmploiDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

@ManagedBean(name = "affectationPosteMBean")
@ViewScoped
public class AffectationPosteMBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -1466061822496958360L;

	private Integer etablissementId;

	private PosteEmploiDto selectedPosteDto;
	private DossierEmployeDto selectedEmployeDto;
	private AffectationPosteDto affectationDto;

	private LazyDataModel<DossierEmployeDto> employeProposesDtos;
	private DossierEmployeDto searchEmployeProposeDto;

	private DossierEmployeDto searchEmployeDto;
	private LazyDataModel<DossierEmployeDto> employeDtos;

	private String searchPosteKeyWords;
	private LazyDataModel<PosteEmploiDto> posteDtos;

	@PostConstruct
	public void init() {
		etablissementId = getSessionBean().getCompte().getEtabId();
		initSearchDtos();
		loadPostes();
		loadEmployes();

	}

	// Ajax
	public void onSearchPoste() {
		searchEmployeDto = null;
		searchEmployeProposeDto = null;
		affectationDto = null;
		loadPostes();
	}

	public void onSearchEmploye() {
		loadEmployes();
	}

	public void onSearchEmployePropose() {
		loadEmployeProposes();

	}

	public void onPosteSelected(SelectEvent event) {
		selectedPosteDto = (PosteEmploiDto) event.getObject();

		if (selectedPosteDto.getAffectationDto() == null) {
			affectationDto = new AffectationPosteDto();
			selectedEmployeDto = new DossierEmployeDto();
		} else {
			affectationDto = selectedPosteDto.getAffectationDto();
			selectedEmployeDto = affectationDto.getDossierEmployeDto();
		}
		affectationDto.setPosteDto(selectedPosteDto);
		// loadEmployes();
		loadEmployeProposes();

	}

	public void onEmployeSelected(SelectEvent event) {
		selectedEmployeDto = (DossierEmployeDto) event.getObject();
		affectationDto.setDossierEmployeDto(selectedEmployeDto);

	}

	public void onEmployeProposedSelected(SelectEvent event) {
		selectedEmployeDto = (DossierEmployeDto) event.getObject();
		affectationDto.setDossierEmployeDto(selectedEmployeDto);
	}

	public void onAffectationPoste() {
		if (validAffectation()) {
			affectationDto = serviceLocator.getAffectationPosteService().save(affectationDto);
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}

	// private
	private boolean validAffectation() {
		boolean valid = true;
		Date dateDebutAffectation = affectationDto.getDateDebutAffectation();
		Date dateFinAffectation = affectationDto.getDateFinAffectation();
		Date dateRecrutementEmploye = affectationDto.getDossierEmployeDto().getDateInstallation();
		if (dateFinAffectation != null && dateFinAffectation.before(dateDebutAffectation)) {
			valid = false;
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_POSTES, "msg_date_debut_affectation_fin_affectation_invalid");
		}
		if (dateRecrutementEmploye.after(dateDebutAffectation)) {
			valid = false;
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_POSTES, "msg_date_debut_affectation_recrutement_invalid");
		}
		return valid;
	}

	private void loadEmployeProposes() {
		employeProposesDtos = new LazyDataModel<DossierEmployeDto>() {

			private static final long serialVersionUID = 6265837484982151852L;

			@Override
			public List<DossierEmployeDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				employeProposesDtos.setRowCount(serviceLocator.getAffectationPosteService().countEmployeProposes(
						selectedPosteDto.getId()));
				return serviceLocator.getAffectationPosteService().findEmployeProposes(searchEmployeProposeDto,
						selectedPosteDto.getId(), searchMode);
			}

		};

	}

	private void loadEmployes() {
		employeDtos = new LazyDataModel<DossierEmployeDto>() {
			private static final long serialVersionUID = 7052785226013231211L;

			@Override
			public List<DossierEmployeDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				employeDtos.setRowCount(serviceLocator.getDossierEmployeService().countAllByExample(searchEmployeDto));
				return serviceLocator.getDossierEmployeService().findAllByExample(searchEmployeDto, searchMode);
			}

		};
	}

	private void loadPostes() {
		posteDtos = new LazyDataModel<PosteEmploiDto>() {

			private static final long serialVersionUID = -6103085827521059304L;

			@Override
			public List<PosteEmploiDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				posteDtos.setRowCount(serviceLocator.getPosteEmploiService().countByKeyWord(etablissementId,
						searchPosteKeyWords));
				return serviceLocator.getPosteEmploiService().findAllByKeyWord(etablissementId, searchPosteKeyWords,
						searchMode);
			}

		};

	}

	private void initSearchDtos() {
		searchEmployeDto = new DossierEmployeDto();
		searchEmployeProposeDto = new DossierEmployeDto();
		RefEtablissementDto etablissementDto = new RefEtablissementDto();
		etablissementDto.setId(etablissementId);
		searchEmployeDto.setRefEtablissementDto(etablissementDto);
		searchEmployeProposeDto.setRefEtablissementDto(etablissementDto);

	}

	// getters & setters
	public PosteEmploiDto getSelectedPosteDto() {
		return selectedPosteDto;
	}

	public DossierEmployeDto getSelectedEmployeDto() {
		return selectedEmployeDto;
	}

	public AffectationPosteDto getAffectationDto() {
		return affectationDto;
	}

	public LazyDataModel<DossierEmployeDto> getEmployeProposesDtos() {
		return employeProposesDtos;
	}

	public DossierEmployeDto getSearchEmployeProposeDto() {
		return searchEmployeProposeDto;
	}

	public DossierEmployeDto getSearchEmployeDto() {
		return searchEmployeDto;
	}

	public LazyDataModel<DossierEmployeDto> getEmployeDtos() {
		return employeDtos;
	}

	public String getSearchPosteKeyWords() {
		return searchPosteKeyWords;
	}

	public LazyDataModel<PosteEmploiDto> getPosteDtos() {
		return posteDtos;
	}

	public void setSelectedPosteDto(PosteEmploiDto selectedPosteDto) {
		this.selectedPosteDto = selectedPosteDto;
	}

	public void setSelectedEmployeDto(DossierEmployeDto selectedEmployeDto) {
		this.selectedEmployeDto = selectedEmployeDto;
	}

	public void setAffectationDto(AffectationPosteDto affectationDto) {
		this.affectationDto = affectationDto;
	}

	public void setSearchEmployeProposeDto(DossierEmployeDto searchEmployeProposeDto) {
		this.searchEmployeProposeDto = searchEmployeProposeDto;
	}

	public void setSearchEmployeDto(DossierEmployeDto searchEmployeDto) {
		this.searchEmployeDto = searchEmployeDto;
	}

	public void setEmployeDtos(LazyDataModel<DossierEmployeDto> employeDtos) {
		this.employeDtos = employeDtos;
	}

	public void setSearchPosteKeyWords(String searchPosteKeyWords) {
		this.searchPosteKeyWords = searchPosteKeyWords;
	}

	public void setPosteDtos(LazyDataModel<PosteEmploiDto> posteDtos) {
		this.posteDtos = posteDtos;
	}

}
