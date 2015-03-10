package dz.gov.mesrs.sii.grh.web.jsf.bean.recrutement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

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
import dz.gov.mesrs.sii.grh.business.model.dto.CandidatEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DetailRecrutementDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

@ManagedBean(name = "candidatEmployeMBean")
@ViewScoped
/**
 * 
 * @author BELDI Jamel  on : 16 nov. 2014  14:51:27
 */
public class CandidatEmployeMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	private BesoinRecrutementDto besoinRecrutementDto;
	private List<CandidatEmployeDto> listCandidatEmployeDtos;
	private List<CandidatEmployeDto> listCandidatEmployeDtostoDelete;
	private LazyDataModel<RefIndividuDto> individuModel;
	private RefIndividuDto individuSearchDto;
	private int selectedCandidatEmployeIndex;
	private List<SelectItem> listePostes;
	private String searchKeyWords;
	private LazyDataModel<BesoinRecrutementDto> besoinRecrutementModel;
	private List<SituationEntiteOccurrenceDto> besoinRecrutementHistory;

	public CandidatEmployeMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		initSelectItemLists();
		initDetail();
		onSearchBesoinRecrutement();
	}

	private void initDetail() {
		besoinRecrutementDto = null;
		listCandidatEmployeDtos = null;
		listCandidatEmployeDtostoDelete = null;
	}

	private void initSelectItemLists() {
		listePostes = null;

	}

	public void onSearchBesoinRecrutement() {
		try {
			loadBesoinRecrutementModel();
			initDetail();
		} catch (Exception e) {

		}
	}

	public void onRowSelect(SelectEvent event) {
		try {
			besoinRecrutementDto = (BesoinRecrutementDto) event.getObject();
			findListCandidatEmployeDtos();
			// Items poste recrutement
			listePostes = findListePosteRecrutement(besoinRecrutementDto);
			retrieveHistory();
		} catch (Exception e) {

		}
	}

	private void findListCandidatEmployeDtos() {
		CandidatEmployeDto example = new CandidatEmployeDto();
		example.setDetailRecrutementDto(new DetailRecrutementDto(null, null,
				besoinRecrutementDto, null, null, null, null, null));
		listCandidatEmployeDtos = this.serviceLocator
				.getCandidatEmployeService().findAllByExample(example);
		listCandidatEmployeDtostoDelete = new ArrayList<CandidatEmployeDto>();
	}

	public void onIndividuSelect(SelectEvent event) {
		try {
			RefIndividuDto refIndividuDto = (RefIndividuDto) event.getObject();
			// Traitement Specifique
			CandidatEmployeDto candidatEmployeDto = new CandidatEmployeDto();
			candidatEmployeDto.setRefIndividuDto(refIndividuDto);
			addCandidat(candidatEmployeDto);
		} catch (Exception e) {

		}
	}

	private void addCandidat(CandidatEmployeDto candidatEmployeDto) {
		if (this.listCandidatEmployeDtos == null) {
			this.listCandidatEmployeDtos = new ArrayList<CandidatEmployeDto>();
		}

		this.listCandidatEmployeDtos.add(candidatEmployeDto);

	}

	public void removeCandidat() {
		CandidatEmployeDto candidatEmployeDto = this.listCandidatEmployeDtos
				.get(selectedCandidatEmployeIndex);
		if (candidatEmployeDto.getId() != null
				&& candidatEmployeDto.getId() != 0) {
			listCandidatEmployeDtostoDelete.add(candidatEmployeDto);
		}
		this.listCandidatEmployeDtos.remove(candidatEmployeDto);

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
				collection
						.add(serviceLocator
								.getSituationService()
								.findBySituationEntiteByCodeSituation(
										UtilConstants.ENTITE_BESOIN_RECRUTEMENT_CODE,
										UtilConstants.SITUATION_SOUMISE_ENREGISTREMENT_DOSSIER_CANDIDAT_CODE)
								.getId());

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
		try {
			if (validateForm()) {
				beforeSave();
				this.serviceLocator.getCandidatEmployeService().saveAll(
						listCandidatEmployeDtos,
						listCandidatEmployeDtostoDelete);
				afterSave();
				CommonMessagesUtils.showSuccessSaveMessage();
			}
		} catch (Exception e) {

		}

	}

	public void onSend() {
		try {
			if (validateForm()) {
				beforeSave();
				this.serviceLocator.getCandidatEmployeService().saveAll(
						listCandidatEmployeDtos,
						listCandidatEmployeDtostoDelete);
				beforeSend();
				SituationEntiteDto situationEntiteDto = this.serviceLocator
						.getSituationService()
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_BESOIN_RECRUTEMENT_CODE,
								UtilConstants.SITUATION_SOUMISE_SELECTION_CANDIDAT_CODE);
				besoinRecrutementDto.setSituationEntiteDto(situationEntiteDto);
				besoinRecrutementDto = this.serviceLocator
						.getBesoinRecrutementService().save(
								besoinRecrutementDto);
				SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
				situationEntiteOccurrenceDto.setDateSituation(new Date());
				situationEntiteOccurrenceDto
						.setIdOccurrence(besoinRecrutementDto.getId());
				situationEntiteOccurrenceDto.setIdSituation(situationEntiteDto
						.getId());
				this.serviceLocator.getSituationService()
						.saveSituationOccurrence(situationEntiteOccurrenceDto);
				loadBesoinRecrutementModel();
				initDetail();
				CommonMessagesUtils.showSuccessSaveMessage();
			}
		} catch (Exception e) {

		}

	}

	private boolean validateForm() {
		boolean result = true;

		return result;

	}

	private void beforeSave() {
		if (listCandidatEmployeDtos != null) {
			for (CandidatEmployeDto candidatEmployeDto : listCandidatEmployeDtos) {
				if (candidatEmployeDto.getRefStructureDto().getId() == null
						|| candidatEmployeDto.getRefStructureDto().getId() == 0) {
					candidatEmployeDto.setRefStructureDto(null);
				}

			}
		}

	}

	private void afterSave() {
		besoinRecrutementDto = this.serviceLocator
				.getBesoinRecrutementService().findById(
						besoinRecrutementDto.getId());
		findListCandidatEmployeDtos();

	}

	private void beforeSend() {
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
		List<DetailRecrutementDto> listDetailRecrutementDtos = besoinRecrutementDto
				.getDetailRecrutementDtos();
		if (listDetailRecrutementDtos != null) {
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
					} // detailRecrutementDtos.add(_detailRecrutementDto);
				}

			}
		}

	}

	// hitory
	private void retrieveHistory() {
		besoinRecrutementHistory = serviceLocator.getSituationService()
				.getEntityOccurrenceHistory(
						UtilConstants.ENTITE_BESOIN_RECRUTEMENT_CODE,
						besoinRecrutementDto.getId().intValue());
	}

	public BesoinRecrutementDto getBesoinRecrutementDto() {
		return besoinRecrutementDto;
	}

	public void setBesoinRecrutementDto(
			BesoinRecrutementDto besoinRecrutementDto) {
		this.besoinRecrutementDto = besoinRecrutementDto;
	}

	public LazyDataModel<RefIndividuDto> getIndividuModel() {
		return individuModel;
	}

	public void setIndividuModel(LazyDataModel<RefIndividuDto> individuModel) {
		this.individuModel = individuModel;
	}

	public RefIndividuDto getIndividuSearchDto() {
		return individuSearchDto;
	}

	public void setIndividuSearchDto(RefIndividuDto individuSearchDto) {
		this.individuSearchDto = individuSearchDto;
	}

	public List<CandidatEmployeDto> getListCandidatEmployeDtos() {
		return listCandidatEmployeDtos;
	}

	public void setListCandidatEmployeDtos(
			List<CandidatEmployeDto> listCandidatEmployeDtos) {
		this.listCandidatEmployeDtos = listCandidatEmployeDtos;
	}

	public int getSelectedCandidatEmployeIndex() {
		return selectedCandidatEmployeIndex;
	}

	public void setSelectedCandidatEmployeIndex(int selectedCandidatEmployeIndex) {
		this.selectedCandidatEmployeIndex = selectedCandidatEmployeIndex;
	}

	public List<SelectItem> getListePostes() {
		return listePostes;
	}

	public void setListePostes(List<SelectItem> listePostes) {
		this.listePostes = listePostes;
	}

	public List<CandidatEmployeDto> getListCandidatEmployeDtostoDelete() {
		return listCandidatEmployeDtostoDelete;
	}

	public void setListCandidatEmployeDtostoDelete(
			List<CandidatEmployeDto> listCandidatEmployeDtostoDelete) {
		this.listCandidatEmployeDtostoDelete = listCandidatEmployeDtostoDelete;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public LazyDataModel<BesoinRecrutementDto> getBesoinRecrutementModel() {
		return besoinRecrutementModel;
	}

	public void setBesoinRecrutementModel(
			LazyDataModel<BesoinRecrutementDto> besoinRecrutementModel) {
		this.besoinRecrutementModel = besoinRecrutementModel;
	}

	public List<SituationEntiteOccurrenceDto> getBesoinRecrutementHistory() {
		return besoinRecrutementHistory;
	}

	public void setBesoinRecrutementHistory(
			List<SituationEntiteOccurrenceDto> besoinRecrutementHistory) {
		this.besoinRecrutementHistory = besoinRecrutementHistory;
	}

}
