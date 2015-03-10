package dz.gov.mesrs.sii.grh.web.jsf.bean.recrutement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.BesoinRecrutementDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DetailRecrutementDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GradeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PosteSuperieureDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

@ManagedBean(name = "besoinRecrutementMBean")
@ViewScoped
/**
 * 
 * @author BELDI Jamel  on : 16 nov. 2014  14:51:27
 */
public class BesoinRecrutementMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private LazyDataModel<BesoinRecrutementDto> besoinRecrutementModel;
	private BesoinRecrutementDto besoinRecrutementDto;
	private BesoinRecrutementDto besoinRecrutementSearchDto;
	private List<SelectItem> listeModeRecrutement;
	private List<SelectItem> listeTypeRecrutement;
	private List<SelectItem> listeCorps;
	private List<SelectItem> listeGrade;
	private List<SelectItem> listePosteSuperieur;
	private List<DetailRecrutementDto> listDetailRecrutementDtos;
	private Integer selectedDetailRecrutementIndex;
	private DetailRecrutementDto detailRecrutementDto;
	private List<SelectItem> listeStructure;
	private Integer selectedCorpsId;
	private Integer selectedGradeId;
	private Integer selectedStructureId;
	private Integer selectedPosteSuperieurId;
	private List<SituationEntiteOccurrenceDto> besoinRecrutementHistory;

	public BesoinRecrutementMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		besoinRecrutementSearchDto = new BesoinRecrutementDto();
		besoinRecrutementSearchDto.setRefEtablissementDto(sessionBean
				.getRefEtablissementDto());
		onSearchBesoinRecrutement();
		initSelectItemLists();
	}

	private void initDetail() {
		besoinRecrutementDto = null;
		detailRecrutementDto = new DetailRecrutementDto();

	}

	private void initSelectItemLists() {
		listeModeRecrutement = findListeNcModeRecrutement();
		listeTypeRecrutement = findListeNcTypeRecrutement();
		listeCorps = findListeCorps();
		listeStructure = findListeStructure(sessionBean
				.getRefEtablissementDto().getId());
	}

	public void onSearchBesoinRecrutement() {
		try {
			loadBesoinRecrutementModel();
			initDetail();
		} catch (Exception e) {

		}
	}

	public void onNew() {
		besoinRecrutementDto = new BesoinRecrutementDto();
		besoinRecrutementDto.setRefEtablissementDto(sessionBean
				.getRefEtablissementDto());
		listDetailRecrutementDtos = besoinRecrutementDto
				.getDetailRecrutementDtos();

	}

	public void onRowSelect(SelectEvent event) {
		try {
			besoinRecrutementDto = (BesoinRecrutementDto) event.getObject();
			listDetailRecrutementDtos = besoinRecrutementDto
					.getDetailRecrutementDtos();
			retrieveHistory();
		} catch (Exception e) {

		}
	}

	private void loadBesoinRecrutementModel() {
		besoinRecrutementModel = new LazyDataModel<BesoinRecrutementDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(BesoinRecrutementDto besoinRecrutementDto) {
				return besoinRecrutementDto.getId();
			}

			@Override
			public List<BesoinRecrutementDto> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				SearchMode searchMode = SearchModeMapper.map(first, pageSize,
						sortField, sortOrder);
				Collection<Integer> collection = new ArrayList<Integer>();
				collection.add(serviceLocator
						.getSituationService()
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_BESOIN_RECRUTEMENT_CODE,
								UtilConstants.SITUATION_CREEE_CODE).getId());

				searchMode.addFilter(new Filter("situationEntite.id",
						collection.toArray(), FilterMode.IN));
				searchMode.addFilter(new Filter("refEtablissement.id",
						sessionBean.getRefEtablissementDto().getId(),
						FilterMode.EQUALS));
				besoinRecrutementModel.setRowCount(serviceLocator
						.getBesoinRecrutementService().countByKeyWord(
								searchKeyWords, searchMode));
				return serviceLocator.getBesoinRecrutementService()
						.findAllByKeyWord(searchKeyWords, searchMode);
			}
		};

	}

	public void onSave() {
		if (validateForm()) {
			beforeSave();
			boolean updateSituation = false;
			if (besoinRecrutementDto.getId() == null
					|| besoinRecrutementDto.getId() == 0) {
				updateSituation = true;
			}

			SituationEntiteDto situationEntiteDto = this.serviceLocator
					.getSituationService()
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_BESOIN_RECRUTEMENT_CODE,
							UtilConstants.SITUATION_CREEE_CODE);
			besoinRecrutementDto.setSituationEntiteDto(situationEntiteDto);
			besoinRecrutementDto = this.serviceLocator
					.getBesoinRecrutementService().save(besoinRecrutementDto);
			if (updateSituation) {
				SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
				situationEntiteOccurrenceDto.setDateSituation(new Date());
				situationEntiteOccurrenceDto
						.setIdOccurrence(besoinRecrutementDto.getId());
				situationEntiteOccurrenceDto.setIdSituation(situationEntiteDto
						.getId());
				this.serviceLocator.getSituationService()
						.saveSituationOccurrence(situationEntiteOccurrenceDto);
			}
			afterSave();
			loadBesoinRecrutementModel();
			retrieveHistory();
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}

	public void onSend() {
		if (validateForm()) {
			beforeSave();
			SituationEntiteDto situationEntiteDto = this.serviceLocator
					.getSituationService()
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_BESOIN_RECRUTEMENT_CODE,
							UtilConstants.SITUATION_SOUMISE_ENREGISTREMENT_DOSSIER_CANDIDAT_CODE);
			besoinRecrutementDto.setSituationEntiteDto(situationEntiteDto);
			besoinRecrutementDto = this.serviceLocator
					.getBesoinRecrutementService().save(besoinRecrutementDto);
			SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
			situationEntiteOccurrenceDto.setDateSituation(new Date());
			situationEntiteOccurrenceDto.setIdOccurrence(besoinRecrutementDto
					.getId());
			situationEntiteOccurrenceDto.setIdSituation(situationEntiteDto
					.getId());
			this.serviceLocator.getSituationService().saveSituationOccurrence(
					situationEntiteOccurrenceDto);
			loadBesoinRecrutementModel();
			initDetail();
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}

	public void onRemove() {
		this.serviceLocator.getBesoinRecrutementService().remove(
				besoinRecrutementDto);
		loadBesoinRecrutementModel();
		initDetail();
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	private boolean validateForm() {
		boolean result = true;
		if (besoinRecrutementDto.getDateDepot() != null
				&& besoinRecrutementDto.getDateDepot().before(
						besoinRecrutementDto.getDateOuverture())) {
			GRHMessagesUtils
					.showErrorMessage("date_depot_recrutement_invalide");
			result = false;

		}
		if (besoinRecrutementDto.getDateDepot() != null
				&& besoinRecrutementDto.getDateRecrutement() != null
				&& besoinRecrutementDto.getDateRecrutement().before(
						besoinRecrutementDto.getDateDepot())) {
			GRHMessagesUtils
					.showErrorMessage("date_prevue_recrutement_invalide");
			result = false;

		}
		if (besoinRecrutementDto.getDatePublication() != null
				&& besoinRecrutementDto.getDatePublication().before(
						besoinRecrutementDto.getDateOuverture())) {
			GRHMessagesUtils
					.showErrorMessage("date_publication_recrutement_invalide");
			result = false;

		}
		return result;

	}

	private void beforeSave() {
		if (besoinRecrutementDto.getNomenclatureByModeRecrutementDto().getId() == null
				|| besoinRecrutementDto.getNomenclatureByModeRecrutementDto()
						.getId() == 0) {
			besoinRecrutementDto.setNomenclatureByModeRecrutementDto(null);
		}
		if (besoinRecrutementDto.getNomenclatureByTypeRecrutementDto().getId() == null
				|| besoinRecrutementDto.getNomenclatureByTypeRecrutementDto()
						.getId() == 0) {
			besoinRecrutementDto.setNomenclatureByTypeRecrutementDto(null);
		}

		if (listDetailRecrutementDtos != null) {
			List<DetailRecrutementDto> detailRecrutementDtos = new ArrayList<DetailRecrutementDto>();
			for (DetailRecrutementDto _detailRecrutementDto : listDetailRecrutementDtos) {
				if (_detailRecrutementDto.getReference() != null) {
					if (_detailRecrutementDto.getPosteSuperieureDto().getId() == null
							|| _detailRecrutementDto.getPosteSuperieureDto()
									.getId() == 0) {
						_detailRecrutementDto.setPosteSuperieureDto(null);
					}
					if (_detailRecrutementDto.getRefStructureDto().getId() == null
							|| _detailRecrutementDto.getRefStructureDto()
									.getId() == 0) {
						_detailRecrutementDto.setRefStructureDto(null);
					}
					detailRecrutementDtos.add(_detailRecrutementDto);
				}

			}
			besoinRecrutementDto
					.setDetailRecrutementDtos(detailRecrutementDtos);
		}

	}

	private void afterSave() {
		listDetailRecrutementDtos = besoinRecrutementDto
				.getDetailRecrutementDtos();
	}

	public void onAddPoste() {
		detailRecrutementDto = new DetailRecrutementDto();
		selectedCorpsId = null;
		selectedGradeId = null;
		selectedPosteSuperieurId = null;
		selectedStructureId = null;
		listeGrade = null;
		listePosteSuperieur = null;
	}

	public void onCorpsSelected(AjaxBehaviorEvent event) {
		selectedGradeId = null;
		selectedPosteSuperieurId = null;
		detailRecrutementDto.setCategorieProfessionnelleDto(null);
		listeGrade = findListeGrade(selectedCorpsId);
		detailRecrutementDto.setCorpsDto(this.serviceLocator.getCorpsService()
				.findById(selectedCorpsId));
	}

	public void onGradeSelected(AjaxBehaviorEvent event) {
		GradeDto gradeDto = this.serviceLocator.getGradeService().findById(
				selectedGradeId);
		detailRecrutementDto.setGradeDto(gradeDto);
		detailRecrutementDto.setCategorieProfessionnelleDto(gradeDto
				.getCategorieProfessionnelleDto());
		PosteSuperieureDto example = new PosteSuperieureDto();
		example.setCategorieProfessionnelleDto(gradeDto
				.getCategorieProfessionnelleDto());
		listePosteSuperieur = findListePosteSuperieur(example);
	}

	public void onPosteSuperieurSelected(AjaxBehaviorEvent event) {
		detailRecrutementDto
				.setPosteSuperieureDto(this.serviceLocator
						.getPosteSuperieureService().findById(
								selectedPosteSuperieurId));
	}

	public void onStructureSelected(AjaxBehaviorEvent event) {
		detailRecrutementDto.setRefStructureDto(this.serviceLocator
				.getRefStructureService().findById(selectedStructureId));
	}

	public void addPoste() {
		if (listDetailRecrutementDtos != null) {
			for (DetailRecrutementDto _detailRecrutementDto : listDetailRecrutementDtos) {
				if (_detailRecrutementDto.getReference().equals(
						detailRecrutementDto.getReference())) {
					GRHMessagesUtils
							.showErrorMessage("reference_poste_invalide");
					return;
				}

			}
		} else {
			this.listDetailRecrutementDtos = new ArrayList<DetailRecrutementDto>();
		}
		detailRecrutementDto.setBesoinRecrutementDto(besoinRecrutementDto);
		this.listDetailRecrutementDtos.add(detailRecrutementDto);
		RequestContext.getCurrentInstance().execute("PF('posteDialog').hide()");
		// RequestContext.getCurrentInstance().closeDialog(null);
	}

	public void removePoste() {
		DetailRecrutementDto posteDto = this.listDetailRecrutementDtos
				.get(selectedDetailRecrutementIndex);
		this.listDetailRecrutementDtos.remove(posteDto);

	}

	// hitory
	private void retrieveHistory() {
		besoinRecrutementHistory = serviceLocator.getSituationService()
				.getEntityOccurrenceHistory(
						UtilConstants.ENTITE_BESOIN_RECRUTEMENT_CODE,
						besoinRecrutementDto.getId().intValue());
	}

	public LazyDataModel<BesoinRecrutementDto> getBesoinRecrutementModel() {
		return besoinRecrutementModel;
	}

	public void setBesoinRecrutementModel(
			LazyDataModel<BesoinRecrutementDto> besoinRecrutementModel) {
		this.besoinRecrutementModel = besoinRecrutementModel;
	}

	public BesoinRecrutementDto getBesoinRecrutementDto() {
		return besoinRecrutementDto;
	}

	public void setBesoinRecrutementDto(
			BesoinRecrutementDto besoinRecrutementDto) {
		this.besoinRecrutementDto = besoinRecrutementDto;
	}

	public BesoinRecrutementDto getBesoinRecrutementSearchDto() {
		return besoinRecrutementSearchDto;
	}

	public void setBesoinRecrutementSearchDto(
			BesoinRecrutementDto besoinRecrutementSearchDto) {
		this.besoinRecrutementSearchDto = besoinRecrutementSearchDto;
	}

	public List<SelectItem> getListeModeRecrutement() {
		return listeModeRecrutement;
	}

	public void setListeModeRecrutement(List<SelectItem> listeModeRecrutement) {
		this.listeModeRecrutement = listeModeRecrutement;
	}

	public List<SelectItem> getListeTypeRecrutement() {
		return listeTypeRecrutement;
	}

	public void setListeTypeRecrutement(List<SelectItem> listeTypeRecrutement) {
		this.listeTypeRecrutement = listeTypeRecrutement;
	}

	public List<SelectItem> getListeCorps() {
		return listeCorps;
	}

	public void setListeCorps(List<SelectItem> listeCorps) {
		this.listeCorps = listeCorps;
	}

	public List<SelectItem> getListeGrade() {
		return listeGrade;
	}

	public void setListeGrade(List<SelectItem> listeGrade) {
		this.listeGrade = listeGrade;
	}

	public List<SelectItem> getListePosteSuperieur() {
		return listePosteSuperieur;
	}

	public void setListePosteSuperieur(List<SelectItem> listePosteSuperieur) {
		this.listePosteSuperieur = listePosteSuperieur;
	}

	public List<DetailRecrutementDto> getListDetailRecrutementDtos() {
		return listDetailRecrutementDtos;
	}

	public void setListDetailRecrutementDtos(
			List<DetailRecrutementDto> listDetailRecrutementDtos) {
		this.listDetailRecrutementDtos = listDetailRecrutementDtos;
	}

	public Integer getSelectedDetailRecrutementIndex() {
		return selectedDetailRecrutementIndex;
	}

	public void setSelectedDetailRecrutementIndex(
			Integer selectedDetailRecrutementIndex) {
		this.selectedDetailRecrutementIndex = selectedDetailRecrutementIndex;
	}

	public DetailRecrutementDto getDetailRecrutementDto() {
		return detailRecrutementDto;
	}

	public void setDetailRecrutementDto(
			DetailRecrutementDto detailRecrutementDto) {
		this.detailRecrutementDto = detailRecrutementDto;
	}

	public List<SelectItem> getListeStructure() {
		return listeStructure;
	}

	public void setListeStructure(List<SelectItem> listeStructure) {
		this.listeStructure = listeStructure;
	}

	public Integer getSelectedCorpsId() {
		return selectedCorpsId;
	}

	public void setSelectedCorpsId(Integer selectedCorpsId) {
		this.selectedCorpsId = selectedCorpsId;
	}

	public Integer getSelectedGradeId() {
		return selectedGradeId;
	}

	public void setSelectedGradeId(Integer selectedGradeId) {
		this.selectedGradeId = selectedGradeId;
	}

	public Integer getSelectedStructureId() {
		return selectedStructureId;
	}

	public void setSelectedStructureId(Integer selectedStructureId) {
		this.selectedStructureId = selectedStructureId;
	}

	public Integer getSelectedPosteSuperieurId() {
		return selectedPosteSuperieurId;
	}

	public void setSelectedPosteSuperieurId(Integer selectedPosteSuperieurId) {
		this.selectedPosteSuperieurId = selectedPosteSuperieurId;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public List<SituationEntiteOccurrenceDto> getBesoinRecrutementHistory() {
		return besoinRecrutementHistory;
	}

	public void setBesoinRecrutementHistory(
			List<SituationEntiteOccurrenceDto> besoinRecrutementHistory) {
		this.besoinRecrutementHistory = besoinRecrutementHistory;
	}

}
