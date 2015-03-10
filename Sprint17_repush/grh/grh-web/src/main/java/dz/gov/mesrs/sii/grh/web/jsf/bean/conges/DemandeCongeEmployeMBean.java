package dz.gov.mesrs.sii.grh.web.jsf.bean.conges;

import java.text.ParseException;
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

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.exception.CongeDateDebutException;
import dz.gov.mesrs.sii.grh.business.exception.CongeDateDemandeException;
import dz.gov.mesrs.sii.grh.business.exception.CongeNbJourException;
import dz.gov.mesrs.sii.grh.business.model.dto.AnneeGrhDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CongeEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@ManagedBean(name = "demandeCongeEmployeMBean")
@ViewScoped
public class DemandeCongeEmployeMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private CongeEmployeDto congeEmployeDto;
	private LazyDataModel<CongeEmployeDto> demandeCongeModel;
	private List<SituationEntiteOccurrenceDto> congeEmployeHistory;
	private List<SelectItem> listeTypeConge;
	private DossierEmployeDto employeSearchDto;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private AnneeGrhDto anneeGrh;

	public DemandeCongeEmployeMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean
				.getRefEtablissementDto());
		onSearch();
		initSelectItemLists();
		initCurrentYear();
	}

	private void initDetail() {
		congeEmployeDto = null;
	}

	private void initSelectItemLists() {
		loadListeTypeConge();
	}

	private void initCurrentYear() {
		try {
			anneeGrh = deduceCurrentYear();
		} catch (ParseException e) {

		}
	}

	private void loadListeTypeConge() {
		listeTypeConge = findListeTypeConge();

	}

	public void onSearch() {
		try {
			loadDemandeCongeModel();
			initDetail();
		} catch (Exception e) {
			

		}
	}

	private void loadDemandeCongeModel() {
		demandeCongeModel = new LazyDataModel<CongeEmployeDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(CongeEmployeDto congeEmployeDto) {
				return congeEmployeDto.getId();
			}

			@Override
			public List<CongeEmployeDto> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = new SearchMode();
			
				demandeCongeModel.setRowCount(serviceLocator
						.getCongeEmployeService().countAllDemandesConges(sessionBean
				.getRefEtablissementDto().getId(), searchMode, searchKeyWords));
				searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				List<CongeEmployeDto> dtos = serviceLocator
						.getCongeEmployeService().findAllDemandesConges(sessionBean
				.getRefEtablissementDto().getId(), searchMode, searchKeyWords);
				return dtos;
			}
		};

	}

	public void onNew() {
		congeEmployeDto = new CongeEmployeDto();
		congeEmployeDto.setNomenclatureByTypeCongeDto(new NomenclatureDto());
		congeEmployeDto.setDateDebut(new Date());

	}

	public void onDemandeCongeSelect(SelectEvent event) {
		congeEmployeDto = (CongeEmployeDto) event.getObject();
		retrieveHistory();
	}

	public void onRowSelect(SelectEvent event) {
		DossierEmployeDto dossierEmployeDto = (DossierEmployeDto) event
				.getObject();
		congeEmployeDto.setDossierEmployeDto(dossierEmployeDto);
		congeEmployeDto.setSoldeConge(serviceLocator.getCongeEmployeService()
				.calculateDroitConge(anneeGrh, dossierEmployeDto.getId(),
						dossierEmployeDto.getDateInstallation()));
	}

	public void onDateDebutChange(SelectEvent event) {
		this.congeEmployeDto.setDateDebut((Date) event.getObject());
		deduceDateReprise();
	}

	public void onNbJourChange() {
		deduceDateReprise();
	}

	private void deduceDateReprise() {
		if (this.congeEmployeDto.getDateDebut() != null
				&& this.congeEmployeDto.getNbreJourDemande() != null) {
			Date dateReprise = serviceLocator.getRefJourOuvreService()
					.nextWorkingDay(this.congeEmployeDto.getDateDebut(),
							this.congeEmployeDto.getNbreJourDemande());
			this.congeEmployeDto.setDateReprise(dateReprise);
		} else {
			this.congeEmployeDto.setDateReprise(null);
		}
	}

	public void onSave() {

		if (validateForm()) {

			try {
				congeEmployeDto = this.serviceLocator.getCongeEmployeService()
						.saveDemande(congeEmployeDto, anneeGrh);
				afterSave();
				CommonMessagesUtils.showSuccessSaveMessage();
			} catch (CongeDateDemandeException e) {
				// Vérifier la date de la demande avec la période!
				GRHMessagesUtils
						.showErrorMessage("demande_conge_date_demande_invalide");
			} catch (CongeDateDebutException e) {
				// Vérifier la date de début de congé avec la date de la
				// demande!
				GRHMessagesUtils
						.showErrorMessage("demande_conge_date_debut_invalide");
			} catch (CongeNbJourException e) {
				// Vérifier le nombre de jours de congé avec le solde de congé!
				GRHMessagesUtils
						.showErrorMessage("demande_conge_nbre_jour_invalide");
			}
			
		}

	}

	private boolean validateForm() {
		boolean result = true;

		return result;
	}

	private void afterSave() {
		onSearch();
	}

	// hitory
	private void retrieveHistory() {
		congeEmployeHistory = serviceLocator.getSituationService()
				.getEntityOccurrenceHistory(
						UtilConstants.ENTITE_CONGE_EMPLOYE_CODE,
						congeEmployeDto.getId().intValue());
	}

	public void onSearchDossierEmploye() {
		loaddossierEmployeModel();
	}

	private void loaddossierEmployeModel() {
		dossierEmployeModel = new LazyDataModel<DossierEmployeDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(DossierEmployeDto dossierEmployeDto) {
				return dossierEmployeDto.getId();
			}

			@Override
			public List<DossierEmployeDto> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = new SearchMode(pageSize, first);
				Collection<Integer> collection = new ArrayList<Integer>();
				collection.add(serviceLocator
						.getSituationService()
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE,
								UtilConstants.SITUATION_TITULARISE_CODE)
						.getId());
				searchMode.addFilter(new Filter("situationEntite.id",
						collection.toArray(), FilterMode.IN));
				dossierEmployeModel.setRowCount(serviceLocator
						.getDossierEmployeService().countAllByExample(
								employeSearchDto, searchMode));

				List<DossierEmployeDto> dtos = serviceLocator
						.getDossierEmployeService().findAllByExample(
								employeSearchDto, searchMode);
				return dtos;
			}
		};

	}

	public CongeEmployeDto getCongeEmployeDto() {
		return congeEmployeDto;
	}

	public void setCongeEmployeDto(CongeEmployeDto congeEmployeDto) {
		this.congeEmployeDto = congeEmployeDto;
	}

	public LazyDataModel<CongeEmployeDto> getDemandeCongeModel() {
		return demandeCongeModel;
	}

	public void setDemandeCongeModel(
			LazyDataModel<CongeEmployeDto> demandeCongeModel) {
		this.demandeCongeModel = demandeCongeModel;
	}

	public List<SituationEntiteOccurrenceDto> getCongeEmployeHistory() {
		return congeEmployeHistory;
	}

	public void setCongeEmployeHistory(
			List<SituationEntiteOccurrenceDto> congeEmployeHistory) {
		this.congeEmployeHistory = congeEmployeHistory;
	}

	public List<SelectItem> getListeTypeConge() {
		return listeTypeConge;
	}

	public void setListeTypeConge(List<SelectItem> listeTypeConge) {
		this.listeTypeConge = listeTypeConge;
	}

	public DossierEmployeDto getEmployeSearchDto() {
		return employeSearchDto;
	}

	public void setEmployeSearchDto(DossierEmployeDto employeSearchDto) {
		this.employeSearchDto = employeSearchDto;
	}

	public LazyDataModel<DossierEmployeDto> getDossierEmployeModel() {
		return dossierEmployeModel;
	}

	public void setDossierEmployeModel(
			LazyDataModel<DossierEmployeDto> dossierEmployeModel) {
		this.dossierEmployeModel = dossierEmployeModel;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public AnneeGrhDto getAnneeGrh() {
		return anneeGrh;
	}

	public void setAnneeGrh(AnneeGrhDto anneeGrh) {
		this.anneeGrh = anneeGrh;
	}

}
