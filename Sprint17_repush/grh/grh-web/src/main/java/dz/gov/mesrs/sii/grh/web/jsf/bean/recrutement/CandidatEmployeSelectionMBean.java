package dz.gov.mesrs.sii.grh.web.jsf.bean.recrutement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
import dz.gov.mesrs.sii.grh.business.model.dto.CandidatEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DetailRecrutementDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

@ManagedBean(name = "candidatEmployeSelectionMBean")
@ViewScoped
/**
 * 
 * @author BELDI Jamel  on : 16 nov. 2014  14:51:27
 */
public class CandidatEmployeSelectionMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private BesoinRecrutementDto besoinRecrutementDto;
	private List<CandidatEmployeDto> listCandidatEmployeDtos;
	private LazyDataModel<RefIndividuDto> individuModel;
	private RefIndividuDto individuSearchDto;
	private int selectedCandidatEmployeIndex;
	CandidatEmployeDto candidatEmployeDto;
	private LazyDataModel<BesoinRecrutementDto> besoinRecrutementModel;
	private List<SituationEntiteOccurrenceDto> besoinRecrutementHistory;
	private Integer nbreDemandeParPoste;
	private Integer nbreSelectionParPoste;

	public CandidatEmployeSelectionMBean() {

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
			nbreDemandeParPoste = selectedCandidat.getDetailRecrutementDto()
					.getNbrePoste();
			candidatEmployeDto = new CandidatEmployeDto();
			this.serviceLocator.getMapper().map(selectedCandidat,
					candidatEmployeDto);
			selectedCandidatEmployeIndex = listCandidatEmployeDtos
					.indexOf(selectedCandidat);
			calculNbreSelectionParPoste();
		} catch (Exception e) {

		}
	}

	private void findListCandidatEmployeDtos() {
		CandidatEmployeDto example = new CandidatEmployeDto();
		example.setDetailRecrutementDto(new DetailRecrutementDto(null, null,
				besoinRecrutementDto, null, null, null, null, null));
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
										UtilConstants.SITUATION_SOUMISE_SELECTION_CANDIDAT_CODE)
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

	public void onSaveSelectionCandidat() {
		if (validateForm()) {
			this.listCandidatEmployeDtos.set(selectedCandidatEmployeIndex,
					candidatEmployeDto);
			RequestContext.getCurrentInstance().execute(
					"PF('selectCandidatDialog').hide()");
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
							UtilConstants.SITUATION_SOUMISE_DECISION_CODE);
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
		if (candidatEmployeDto.getDateDepot() != null
				&& candidatEmployeDto.getDateDepot().after(
						candidatEmployeDto.getDateSelection())) {
			GRHMessagesUtils
					.showErrorMessage("date_selection_candidat_invalide");
			result = false;

		}
		if (candidatEmployeDto.getRetenu() != null
				&& candidatEmployeDto.getRetenu() == true) {
			candidatEmployeDto.setMotifConvocation(null);
			if (candidatEmployeDto.getDateConvocation() != null
					&& candidatEmployeDto.getDateConvocation().before(
							candidatEmployeDto.getDateSelection())) {
				GRHMessagesUtils
						.showErrorMessage("date_convocation_candidat_invalide");
				result = false;
			}
			if (nbreDemandeParPoste == nbreSelectionParPoste) {
				GRHMessagesUtils.showErrorMessage("nbre_poste_depasse");
				result = false;
			}

		} else {
			candidatEmployeDto.setDateConvocation(null);
			candidatEmployeDto.setClassement(null);
		}

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

	private void calculNbreSelectionParPoste() {
		nbreSelectionParPoste = 0;
		for (CandidatEmployeDto candidat : listCandidatEmployeDtos) {
			if (candidat
					.getDetailRecrutementDto()
					.getReference()
					.equals(candidatEmployeDto.getDetailRecrutementDto()
							.getReference())
					&& candidat.getRetenu() != null
					&& candidat.getRetenu().equals(Boolean.TRUE)) {
				nbreSelectionParPoste++;
			}

		}
	}

	public BesoinRecrutementDto getBesoinRecrutementDto() {
		return besoinRecrutementDto;
	}

	public void setBesoinRecrutementDto(
			BesoinRecrutementDto besoinRecrutementDto) {
		this.besoinRecrutementDto = besoinRecrutementDto;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
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

	public CandidatEmployeDto getCandidatEmployeDto() {
		return candidatEmployeDto;
	}

	public void setCandidatEmployeDto(CandidatEmployeDto candidatEmployeDto) {
		this.candidatEmployeDto = candidatEmployeDto;
	}

	public int getSelectedCandidatEmployeIndex() {
		return selectedCandidatEmployeIndex;
	}

	public void setSelectedCandidatEmployeIndex(int selectedCandidatEmployeIndex) {
		this.selectedCandidatEmployeIndex = selectedCandidatEmployeIndex;
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

	public Integer getNbreDemandeParPoste() {
		return nbreDemandeParPoste;
	}

	public void setNbreDemandeParPoste(Integer nbreDemandeParPoste) {
		this.nbreDemandeParPoste = nbreDemandeParPoste;
	}

	public Integer getNbreSelectionParPoste() {
		return nbreSelectionParPoste;
	}

	public void setNbreSelectionParPoste(Integer nbreSelectionParPoste) {
		this.nbreSelectionParPoste = nbreSelectionParPoste;
	}

}
