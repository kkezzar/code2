package dz.gov.mesrs.sii.grh.web.jsf.bean.conges;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.exception.CongeDateDebutException;
import dz.gov.mesrs.sii.grh.business.exception.CongeNbJourException;
import dz.gov.mesrs.sii.grh.business.model.dto.CongeEmployeDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

/**
 * 
 * @author BELDI Jamel
 *
 */
@ManagedBean(name = "resultatCongeEmployeMBean")
@ViewScoped
public class ResultatCongeEmployeMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private CongeEmployeDto congeEmployeDto;
	private LazyDataModel<CongeEmployeDto> congeEmployeModel;
	private List<SituationEntiteOccurrenceDto> congeEmployeHistory;	
	

	public ResultatCongeEmployeMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		onSearch();
		initSelectItemLists();
	}
	
   private void initSelectItemLists() {
		
	}
	private void initDetail() {
		congeEmployeDto = null;
	}

	public void onSearch() {
		try {
			loadCongeEmployeModel();
			initDetail();
		} catch (Exception e) {
			

		}
	}
	
	private void loadCongeEmployeModel() {
		congeEmployeModel = new LazyDataModel<CongeEmployeDto>() {
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
				congeEmployeModel.setRowCount(serviceLocator
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

	
	public void onCongeEmployeSelect(SelectEvent event) {
		congeEmployeDto = (CongeEmployeDto) event.getObject();
		congeEmployeDto.setDateDebutAccepte(congeEmployeDto.getDateDebut());
		congeEmployeDto.setNbreJourAccepte(congeEmployeDto.getNbreJourDemande());
		congeEmployeDto.setDateRepriseAccepte(congeEmployeDto.getDateReprise());
		retrieveHistory();
	}

	public void onDateDebutChange(SelectEvent event) {
		this.congeEmployeDto.setDateDebutAccepte((Date) event.getObject());
		deduceDateReprise();
	}

	public void onNbJourChange() {
		deduceDateReprise();
	}

	private void deduceDateReprise() {
		if (this.congeEmployeDto.getDateDebutAccepte() != null
				&& this.congeEmployeDto.getNbreJourAccepte() != null) {
			Date dateReprise = serviceLocator.getRefJourOuvreService()
					.nextWorkingDay(this.congeEmployeDto.getDateDebutAccepte(),
							this.congeEmployeDto.getNbreJourAccepte());
			this.congeEmployeDto.setDateRepriseAccepte(dateReprise);
		} else {
			this.congeEmployeDto.setDateRepriseAccepte(null);
		}
	}

	public void onSave() {

		if (validateForm()) {
			try {
				congeEmployeDto = serviceLocator.getCongeEmployeService().saveResultatConge(congeEmployeDto);
				afterSave();
				CommonMessagesUtils.showSuccessSaveMessage();
			}  catch (CongeDateDebutException e) {
				//Vérifier la date acceptée de début de congé avec la date de la demande!
				GRHMessagesUtils
						.showErrorMessage("resultat_conge_date_debut_acceptee_invalide");
			} catch (CongeNbJourException e) {
				// Vérifier le nombre accepte de jours de congé avec le solde de congé!
				GRHMessagesUtils
						.showErrorMessage("resultat_conge_nbre_jour_accepte_invalide");
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

	
	
	
	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public CongeEmployeDto getCongeEmployeDto() {
		return congeEmployeDto;
	}

	public void setCongeEmployeDto(CongeEmployeDto congeEmployeDto) {
		this.congeEmployeDto = congeEmployeDto;
	}

	public LazyDataModel<CongeEmployeDto> getCongeEmployeModel() {
		return congeEmployeModel;
	}

	public void setCongeEmployeModel(
			LazyDataModel<CongeEmployeDto> congeEmployeModel) {
		this.congeEmployeModel = congeEmployeModel;
	}

	public List<SituationEntiteOccurrenceDto> getCongeEmployeHistory() {
		return congeEmployeHistory;
	}

	public void setCongeEmployeHistory(
			List<SituationEntiteOccurrenceDto> congeEmployeHistory) {
		this.congeEmployeHistory = congeEmployeHistory;
	}

	

}
