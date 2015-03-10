package dz.gov.mesrs.sii.grh.web.jsf.bean.recrutement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

@ManagedBean(name = "candidatEmployeDesistementMBean")
@ViewScoped
/**
 * 
 * @author BELDI Jamel  on : 16 nov. 2014  14:51:27
 */
public class CandidatEmployeDesistementMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private BesoinRecrutementDto besoinRecrutementDto;
	private List<CandidatEmployeDto> listCandidatEmployeDtos;
	private int selectedCandidatEmployeIndex;
	private CandidatEmployeDto candidatEmployeDto;
	private String searchKeyWords;
	private LazyDataModel<BesoinRecrutementDto> besoinRecrutementModel;
	private List<SituationEntiteOccurrenceDto> besoinRecrutementHistory;

	public CandidatEmployeDesistementMBean() {

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
	}

	private void initSelectItemLists() {

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
			retrieveHistory();
		} catch (Exception e) {

		}
	}

	public void onCandidatSelect(SelectEvent event) {
		try {
			CandidatEmployeDto selectedCandidat = (CandidatEmployeDto) event
					.getObject();
			candidatEmployeDto = new CandidatEmployeDto();
			this.serviceLocator.getMapper().map(selectedCandidat,
					candidatEmployeDto);
			selectedCandidatEmployeIndex = listCandidatEmployeDtos
					.indexOf(selectedCandidat);
		} catch (Exception e) {

		}
	}

	private void findListCandidatEmployeDtos() {
		CandidatEmployeDto example = new CandidatEmployeDto();
		example.setDetailRecrutementDto(new DetailRecrutementDto(null, null,
				besoinRecrutementDto, null, null, null, null, null));
		example.setRetenu(true);
		example.setAdmis(true);
		listCandidatEmployeDtos = this.serviceLocator
				.getCandidatEmployeService().findAllByExample(example);
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
										UtilConstants.SITUATION_SOUMISE_DESISTEMENT_CANDIDAT_CODE)
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

	public void onSaveDesistement() {
		if (validateForm()) {
			this.listCandidatEmployeDtos.set(selectedCandidatEmployeIndex,
					candidatEmployeDto);
		}
	}

	public void onSave() {
		try {
			beforeSave();
			this.serviceLocator.getCandidatEmployeService().saveAll(
					this.listCandidatEmployeDtos, null);
			afterSave();
			CommonMessagesUtils.showSuccessSaveMessage();
		} catch (Exception e) {

		}

	}

	public void onSend() {
		try {
			beforeSave();
			this.serviceLocator.getCandidatEmployeService().saveAll(
					listCandidatEmployeDtos, null);
			SituationEntiteDto situationEntiteDto = this.serviceLocator
					.getSituationService()
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_BESOIN_RECRUTEMENT_CODE,
							UtilConstants.SITUATION_SOUMISE_INSTALLATION_CANDIDAT_CODE);
			besoinRecrutementDto.setSituationEntiteDto(situationEntiteDto);
			beforeSend();
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
		} catch (Exception e) {

		}
	}

	private boolean validateForm() {

		boolean result = true;

		if (candidatEmployeDto.getDesistement() != null
				&& candidatEmployeDto.getDesistement() == true) {
			if (candidatEmployeDto.getDateDesistement() != null
					&& candidatEmployeDto.getDateDesistement().before(
							candidatEmployeDto.getDateConvocationRec())) {
				GRHMessagesUtils
						.showErrorMessage("date_desistement_candidat_invalide");
				result = false;

			}
		} else {
			candidatEmployeDto.setDateDesistement(null);
			candidatEmployeDto.setMotifDesistement(null);
		}

		return result;

	}

	private void beforeSave() {
		if (listCandidatEmployeDtos != null) {
			for (CandidatEmployeDto candidatEmployeDto : listCandidatEmployeDtos) {
				if (candidatEmployeDto.getDesistement() == null) {
					candidatEmployeDto.setDesistement(Boolean.FALSE);
				}
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
					}
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

	public CandidatEmployeDto getCandidatEmployeDto() {
		return candidatEmployeDto;
	}

	public void setCandidatEmployeDto(CandidatEmployeDto candidatEmployeDto) {
		this.candidatEmployeDto = candidatEmployeDto;
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
