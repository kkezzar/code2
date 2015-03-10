package dz.gov.mesrs.sii.gfc.web.jsf.bean.preparationBudgetFonctionnement.preparationBudgetCentral;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.dao.DataIntegrityViolationException;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.ExerciceBudgetaireDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;

/**
 * Gerer les exercices budgetaires (ouverture, consultation, cloture )
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "excerciceBudgetaireBean")
@ViewScoped
public class ExcerciceBudgetaireMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<ExerciceBudgetaireDto> model;
	private ExerciceBudgetaireDto exerciceBudgetaireDto;
	private ExerciceBudgetaireDto exerciceBudgetaireSearchDto;
	// Historique des situations
	private List<SituationEntiteOccurrenceDto> exerciceBudgetaireHistory;
	// UI
	private boolean isView;
	private boolean isCrud;

	public ExcerciceBudgetaireMBean() {
	}

	@PostConstruct
	public void init() {
		initUI();
		loadSearchResults();
	}

	private void initUI() {
		exerciceBudgetaireDto = new ExerciceBudgetaireDto();
		exerciceBudgetaireSearchDto = new ExerciceBudgetaireDto();
	}

	private void loadSearchResults() {
		model = new LazyDataModel<ExerciceBudgetaireDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(ExerciceBudgetaireDto exerciceBudgetaireDto) {
				return exerciceBudgetaireDto.getId();
			}

			@Override
			public List<ExerciceBudgetaireDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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
				case "ExercicesBudgetaireEdit":
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_EXERCICE_BUDGETAIRE_CODE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.NOT_IN));
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_EXERCICE_BUDGETAIRE_CODE,
									UtilConstants.SITUATION_CLOTUREE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.NOT_IN));
					break;
				case "ExercicesBudgetaireCloture":
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_EXERCICE_BUDGETAIRE_CODE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));
					break;
				default:
					break;
				}

				model.setRowCount(serviceLocator.getExerciceBudgetaireService().countAllByExample(
						exerciceBudgetaireSearchDto, searchMode));

				List<ExerciceBudgetaireDto> result = serviceLocator.getExerciceBudgetaireService().findAllByExample(
						exerciceBudgetaireSearchDto, searchMode);

				if (model.getRowCount() == 1) {
					isCrud = true;
					isView = true;
					exerciceBudgetaireDto = result.get(0);
				}

				return result;
			}
		};

	}

	public void searchAction() {
		loadSearchResults();
	}

	/**
	 * Ouvrir un nouveau exercice budgetaire
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 14:53:39
	 */
	public void addAction() {
		exerciceBudgetaireDto = new ExerciceBudgetaireDto();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, +1);
		Integer annee = calendar.get(Calendar.YEAR);
		exerciceBudgetaireDto.setAnnee(annee);
		isCrud = true;
	}

	/**
	 * Evenement de selection d'un exercice budgetaire
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		exerciceBudgetaireDto = (ExerciceBudgetaireDto) event.getObject();
		// historique des situations
		exerciceBudgetaireHistory = serviceLocator.getSituationService().getEntityOccurrenceHistory(
				UtilConstants.ENTITE_GFC_EXERCICE_BUDGETAIRE_CODE, exerciceBudgetaireDto.getId());
		isCrud = true;
		isView = true;
	}

	/**
	 * Ouvrir l'exercice budgetaire
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 14:21:34
	 */
	public void saveOpenAction() {
		if (exerciceBudgetaireDto != null) {
			if (exerciceBudgetaireDto.getId() == null) {
				exerciceBudgetaireDto = serviceLocator.getExerciceBudgetaireService().ouvrirExerciceBudgetaire(
						exerciceBudgetaireDto);
				CommonMessagesUtils.showSuccessSaveMessage();
			} else {
				serviceLocator.getExerciceBudgetaireService().save(exerciceBudgetaireDto);
				CommonMessagesUtils.showSuccessUpdateMessage();
			}
		}
	}

	/**
	 * Valider l'ouverture d'un exercice budgetaire
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 16:27:29
	 */
	public void validateAction() {
		exerciceBudgetaireDto = serviceLocator.getExerciceBudgetaireService().validerOuvertureExerciceBudgetaire(
				exerciceBudgetaireDto);
		isCrud = false;

		CommonMessagesUtils.showSuccessValidationMessage();
	}

	/**
	 * Cloturer l'exercice budgetaire
	 * 
	 * @author
	 */
	public void closingAction() {
		if (exerciceBudgetaireDto != null) {
			serviceLocator.getExerciceBudgetaireService().cloturerExerciceBudgetaire(exerciceBudgetaireDto);
			isCrud = false;

			CommonMessagesUtils.showSuccessClosingMessage();

		}
	}

	/**
	 * Supprimer l'exercice budgetaire
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 16:27:56
	 */
	public void deleteAction() {
		try {
			if (exerciceBudgetaireDto != null) {
				serviceLocator.getExerciceBudgetaireService().remove(exerciceBudgetaireDto);
				CommonMessagesUtils.showSuccessDeleteMessage();
				isCrud = false;
			}
		} catch (DataIntegrityViolationException e) {
			CommonMessagesUtils.showConstraintViolationErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			CommonMessagesUtils.showErrorMessage(e.getMessage());

		}
	}

	/**
	 * Annuler l'operation en cours sur l'exercice budgetaire
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 14:22:39
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

	public LazyDataModel<ExerciceBudgetaireDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<ExerciceBudgetaireDto> model) {
		this.model = model;
	}

	public ExerciceBudgetaireDto getExerciceBudgetaireDto() {
		return exerciceBudgetaireDto;
	}

	public void setExerciceBudgetaireDto(ExerciceBudgetaireDto exerciceBudgetaireDto) {
		this.exerciceBudgetaireDto = exerciceBudgetaireDto;
	}

	public ExerciceBudgetaireDto getExerciceBudgetaireSearchDto() {
		return exerciceBudgetaireSearchDto;
	}

	public void setExerciceBudgetaireSearchDto(ExerciceBudgetaireDto exerciceBudgetaireSearchDto) {
		this.exerciceBudgetaireSearchDto = exerciceBudgetaireSearchDto;
	}

	/**
	 * @return the exerciceBudgetaireHistory
	 */
	public List<SituationEntiteOccurrenceDto> getExerciceBudgetaireHistory() {
		return exerciceBudgetaireHistory;
	}

	/**
	 * @param exerciceBudgetaireHistory
	 *            the exerciceBudgetaireHistory to set
	 */
	public void setExerciceBudgetaireHistory(List<SituationEntiteOccurrenceDto> exerciceBudgetaireHistory) {
		this.exerciceBudgetaireHistory = exerciceBudgetaireHistory;
	}

}
