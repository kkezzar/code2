package dz.gov.mesrs.sii.gfc.web.jsf.bean.parametrageBudget.programmesNationaux;

import java.util.ArrayList;
import java.util.Collection;
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
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.ProgrammeNationalDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.GFCConstantBean;

/**
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "programmesNationauxBean")
@ViewScoped
public class ProgrammesNationauxBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<ProgrammeNationalDto> model;
	private ProgrammeNationalDto programmeNationalDto;
	private ProgrammeNationalDto programmeNationalSearchDto;

	// UI
	private String searchByKeywords;

	private List<SelectItem> listTypesProgrammesNationaux;

	private boolean isView;
	private boolean isCrud;

	// Historique des situations
	private List<SituationEntiteOccurrenceDto> programmesNationauxHistory;

	public ProgrammesNationauxBean() {
	}

	@PostConstruct
	public void init() {
		try {
			initUI();
			loadSearchResults();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Initialiser l'UI
	 * 
	 * @author Mounir.MESSAOUDI on : 26 nov. 2014 18:06:39
	 */
	private void initUI() {
		if (viewId.equals("ProgrammesNationauxEdit")) {
			listTypesProgrammesNationaux = findNomenclatureList(GFCConstantBean.CODE_TYPE_PROGRAMME_NATIONAL);
		}

	}

	/**
	 * Charger les resultats de la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 26 nov. 2014 15:06:28
	 */
	private void loadSearchResults() {

		programmeNationalSearchDto = new ProgrammeNationalDto();
		programmeNationalSearchDto.setIntituleFr(searchByKeywords);
		// programmeNationalDto.setIntituleAr(searchByKeywords);

		model = new LazyDataModel<ProgrammeNationalDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public List<ProgrammeNationalDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				Boolean descending = null;

				// searchMode.addSortField(sortField);

				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}

				}

				SearchMode searchMode = new SearchMode(pageSize, first);
				Collection<Integer> collection = new ArrayList<Integer>();
				switch (viewId) {
				case "ProgrammesNationauxEdit":
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_PROGRAMME_NATIONNAUX_CODE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.NOT_IN));
					break;
				case "ProgrammesNationauxCloture":
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_PROGRAMME_NATIONNAUX_CODE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));
					break;
				default:
					break;
				}

				model.setRowCount(serviceLocator.getProgrammeNationalService().countAllByExample(
						programmeNationalSearchDto, searchMode));
				return serviceLocator.getProgrammeNationalService().findAllByExample(programmeNationalSearchDto,
						searchMode);
			}
		};
	}

	public LazyDataModel<ProgrammeNationalDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<ProgrammeNationalDto> model) {
		this.model = model;
	}

	public ProgrammeNationalDto getProgrammeNationalDto() {
		return programmeNationalDto;
	}

	public void setProgrammeNationalDto(ProgrammeNationalDto programmeNationalDto) {
		this.programmeNationalDto = programmeNationalDto;
	}

	/**
	 * Evenement de selection d'un dossier etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			programmeNationalDto = (ProgrammeNationalDto) event.getObject();
			isCrud = true;
			// historique des situations
			programmesNationauxHistory = serviceLocator.getSituationService().getEntityOccurrenceHistory(
					UtilConstants.ENTITE_GFC_PROGRAMME_NATIONNAUX_CODE, programmeNationalDto.getId());
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Declancher l'action d'ajout
	 * 
	 * @author Mounir.MESSAOUDI on : 26 nov. 2014 14:19:20
	 */
	public void addAction() {
		programmeNationalDto = new ProgrammeNationalDto();
		isCrud = true;
	}

	/**
	 * Lancer la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 30 nov. 2014 11:52:42
	 */
	public void searchAction() {
		loadSearchResults();
	}

	/**
	 * Enregistrer le programme national
	 * 
	 * @author Mounir.MESSAOUDI on : 30 nov. 2014 11:52:28
	 */
	public void saveAction() {
		try {
			if (programmeNationalDto != null) {
				if (programmeNationalDto.getSituation().getId() == 0) {
					SituationEntiteDto situation = serviceLocator.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_PROGRAMME_NATIONNAUX_CODE,
									UtilConstants.SITUATION_CREEE_CODE);
					programmeNationalDto.setSituation(situation);
					programmeNationalDto = serviceLocator.getProgrammeNationalService().save(programmeNationalDto);
					// enregistrer situation
					serviceLocator.getSituationService().saveSituationOccurrence(situation,
							programmeNationalDto.getId());
					CommonMessagesUtils.showSuccessSaveMessage();
				} else {
					serviceLocator.getProgrammeNationalService().save(programmeNationalDto);
					CommonMessagesUtils.showSuccessUpdateMessage();
				}
			}
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}

	}

	/**
	 * Envoyer le programme pour validation
	 * 
	 * @author Mounir.MESSAOUDI on : 30 nov. 2014 11:51:53
	 */
	public void sendToValidationAction() {
		try {
			SituationEntiteDto situation = serviceLocator.getSituationService()
					.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_PROGRAMME_NATIONNAUX_CODE,
							UtilConstants.SITUATION_SOUMISE_VALIDATION_CODE);
			situation.setIdEntite(programmeNationalDto.getId());
			programmeNationalDto.setSituation(situation);
			serviceLocator.getProgrammeNationalService().save(programmeNationalDto);
			// enregistrer situation
			serviceLocator.getSituationService().saveSituationOccurrence(situation, programmeNationalDto.getId());

			CommonMessagesUtils.showSuccessSendToValidationMessage();
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Valider le programme national
	 * 
	 * @author Mounir.MESSAOUDI on : 30 nov. 2014 11:52:05
	 */
	public void validateAction() {
		try {
			SituationEntiteDto situation = serviceLocator.getSituationService().findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_GFC_PROGRAMME_NATIONNAUX_CODE, UtilConstants.SITUATION_VALIDEE_CODE);
			situation.setIdEntite(programmeNationalDto.getId());
			programmeNationalDto.setSituation(situation);
			serviceLocator.getProgrammeNationalService().save(programmeNationalDto);
			// enregistrer situation
			serviceLocator.getSituationService().saveSituationOccurrence(situation, programmeNationalDto.getId());

			CommonMessagesUtils.showSuccessValidationMessage();
			isCrud = false;
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Cloturer le programme national
	 * 
	 * @author Mounir.MESSAOUDI on : 30 nov. 2014 11:38:35
	 */
	public void closingAction() {
		try {
			if (programmeNationalDto != null) {
				SituationEntiteDto situation = serviceLocator.getSituationService()
						.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_PROGRAMME_NATIONNAUX_CODE,
								UtilConstants.SITUATION_CLOTUREE_CODE);
				situation.setIdEntite(programmeNationalDto.getId());
				programmeNationalDto.setSituation(situation);
				serviceLocator.getProgrammeNationalService().save(programmeNationalDto);
				// enregistrer situation
				serviceLocator.getSituationService().saveSituationOccurrence(situation, programmeNationalDto.getId());

				CommonMessagesUtils.showSuccessClosingMessage();
			}
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Supprimer le programme nationnal
	 * 
	 * @author Mounir.MESSAOUDI on : 24 déc. 2014 16:30:31
	 */
	public void deleteAction() {
		serviceLocator.getProgrammeNationalService().remove(programmeNationalDto);
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	/**
	 * Action de retour ou annulation
	 * 
	 * @author Mounir.MESSAOUDI on : 30 nov. 2014 11:52:51
	 */
	public void cancelAction() {
		isCrud = false;
		isView = false;
	}

	public boolean isView() {
		return isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public List<SelectItem> getListTypesProgrammesNationaux() {
		return listTypesProgrammesNationaux;
	}

	public String getSearchByKeywords() {
		return searchByKeywords;
	}

	public void setSearchByKeywords(String searchByKeywords) {
		this.searchByKeywords = searchByKeywords;
	}

	/**
	 * @return the programmesNationauxHistory
	 */
	public List<SituationEntiteOccurrenceDto> getProgrammesNationauxHistory() {
		return programmesNationauxHistory;
	}

	/**
	 * @param programmesNationauxHistory
	 *            the programmesNationauxHistory to set
	 */
	public void setProgrammesNationauxHistory(List<SituationEntiteOccurrenceDto> programmesNationauxHistory) {
		this.programmesNationauxHistory = programmesNationauxHistory;
	}

}
