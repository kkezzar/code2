package dz.gov.mesrs.sii.gfc.web.jsf.bean.preparationBudgetFonctionnement.preparationBudgetCentral;

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
import org.springframework.dao.DataIntegrityViolationException;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.ChapitreDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsProjetBudgetEtablissementDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ProjetBudgetDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.gfc.web.util.DetailBudgetPojo;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

/**
 * Cette class permet de gerer les projets de budgets
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "budgetFinalEtabBean")
@ViewScoped
public class BudgetFinalEtabMBean extends BaseBean {

	public final static String PREPARATION_BUDGET_BUNDLE_MSG_NAME = "preparationBudgetFonctionnementMsgs";

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<ProjetBudgetDto> model;
	private ProjetBudgetDto projetBudgetDto;
	private ProjetBudgetDto projetBudgetSearchDto;

	private RefEtablissementDto etablissementSearchDto;

	// UI
	private boolean isView;
	private boolean isCrud;

	// private List<SelectItem> listExercicesBudgetaires;
	private List<SelectItem> listTypesBudget;
	private List<SelectItem> listUnitesMonetaires;

	private List<SelectItem> listProjetsBudgetsParent;

	private List<DetailsProjetBudgetEtablissementDto> listDetailsBudgetDepenses;
	private List<DetailsProjetBudgetEtablissementDto> listDetailsBudgetRecettes;

	private List<DetailBudgetPojo> listDetailsBudgetDepensesPojo;
	private List<DetailBudgetPojo> listDetailsBudgetRecettPojo;

	private List<SelectItem> listEtablissements;

	// Historique des situations
	private List<SituationEntiteOccurrenceDto> projetBudgetHistory;

	public BudgetFinalEtabMBean() {
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

	private void initUI() {
		projetBudgetSearchDto = new ProjetBudgetDto();
		etablissementSearchDto = new RefEtablissementDto();

	}

	private void loadSearchResults() {
		model = new LazyDataModel<ProjetBudgetDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(ProjetBudgetDto pojetBudgetDto) {
				return pojetBudgetDto.getId();
			}

			@Override
			public List<ProjetBudgetDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Boolean descending = null;

				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}
				}

				SearchMode searchMode = new SearchMode(pageSize, first);
				Collection<Integer> collection = new ArrayList<Integer>();
				collection.add(serviceLocator
						.getSituationService()
						.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE,
								UtilConstants.SITUATION_ENVOYE_MF_CODE).getId());
				searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));
				// searchMode.addSortField(sortField);
				model.setRowCount(serviceLocator.getProjetBudgetService().countAllByExample(projetBudgetSearchDto,
						searchMode));

				List<ProjetBudgetDto> result = serviceLocator.getProjetBudgetService().findAllByExample(
						projetBudgetSearchDto, searchMode);
				if (model.getRowCount() == 1) {
					projetBudgetDto = result.get(0);
					if (projetBudgetDto.getProjetBudget() == null)
						projetBudgetDto.setProjetBudget(new ProjetBudgetDto());

					buildListDetailsProjet(projetBudgetDto);
					isCrud = true;
					isView = true;
				}

				return result;

				// return
				// serviceLocator.getProjetBudgetService().findAllByExample(projetBudgetSearchDto,
				// searchMode);
			}
		};
	}

	public void searchAction() {
		loadSearchResults();
	}

	/**
	 * Evenement de selection d'un agent comptable
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		projetBudgetDto = (ProjetBudgetDto) event.getObject();
		if (projetBudgetDto.getProjetBudget() == null)
			projetBudgetDto.setProjetBudget(new ProjetBudgetDto());

		buildListDetailsProjet(projetBudgetDto);
		isCrud = true;
		isView = true;
	}

	/**
	 * Ajouter un nouveau projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 18:08:47
	 */
	public void addAction() {
		projetBudgetDto = new ProjetBudgetDto();
		projetBudgetDto.setProjetBudget(new ProjetBudgetDto());
		isCrud = true;

	}

	/**
	 * Valider le projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:19:37
	 */
	public void validateAction() {
		try {
			SituationEntiteDto situation = serviceLocator.getSituationService().findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE, UtilConstants.SITUATION_FINALE);

			if (projetBudgetDto.getProjetBudget().getId() == null) {
				projetBudgetDto.setProjetBudget(null);
			}

			situation.setIdEntite(projetBudgetDto.getId());
			projetBudgetDto.setSituation(situation);

			serviceLocator.getProjetBudgetService().save(projetBudgetDto);
			// enregistrer la situation occurence
			serviceLocator.getSituationService().saveSituationOccurrence(situation, projetBudgetDto.getId());
			CommonMessagesUtils.showSuccessValidationMessage();

			isCrud = false;
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	public void saveAction() {
		try {

			if (projetBudgetDto.getProjetBudget().getId() == null) {
				projetBudgetDto.setProjetBudget(null);
			}

			for (DetailsProjetBudgetEtablissementDto details : listDetailsBudgetRecettes)
				serviceLocator.getDetailsProjetBudgetEtablissementService().save(details);
			for (DetailsProjetBudgetEtablissementDto details : listDetailsBudgetDepenses)
				serviceLocator.getDetailsProjetBudgetEtablissementService().save(details);

			serviceLocator.getProjetBudgetService().save(projetBudgetDto);

			CommonMessagesUtils.showSuccessUpdateMessage();
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Supprimer le projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:19:30
	 */
	public void deleteAction() {
		try {
			if (projetBudgetDto != null) {
				serviceLocator.getProjetBudgetService().remove(projetBudgetDto);
				CommonMessagesUtils.showSuccessDeleteMessage();
				isCrud = false;
			}
		} catch (DataIntegrityViolationException e) {
			CommonMessagesUtils.showConstraintViolationErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * build list detail
	 */
	public void buildListDetailsProjet(ProjetBudgetDto projetBudget) {
		listDetailsBudgetDepenses = new ArrayList<DetailsProjetBudgetEtablissementDto>();
		listDetailsBudgetRecettes = new ArrayList<DetailsProjetBudgetEtablissementDto>();

	}

	public void refreshDetailAction() {
		listDetailsBudgetDepenses = new ArrayList<DetailsProjetBudgetEtablissementDto>();
		listDetailsBudgetRecettes = new ArrayList<DetailsProjetBudgetEtablissementDto>();

		if (etablissementSearchDto != null) {
			DetailsProjetBudgetEtablissementDto exemple = new DetailsProjetBudgetEtablissementDto();
			DetailsProjetBudgetEtablissementDto exempleDep = new DetailsProjetBudgetEtablissementDto();
			ChapitreDto chapitre = new ChapitreDto();
			exemple.setEtablissement(etablissementSearchDto);
			exemple.setProjetBudget(projetBudgetDto);
			chapitre.setRecetteType(false);
			exemple.setChapitre(chapitre);
			listDetailsBudgetDepenses = this.serviceLocator.getDetailsProjetBudgetEtablissementService()
					.findAllByExample(exemple);
			ChapitreDto chapitreDep = new ChapitreDto();
			chapitreDep.setRecetteType(true);
			exempleDep.setChapitre(chapitreDep);
			exempleDep.setProjetBudget(projetBudgetDto);
			exempleDep.setEtablissement(etablissementSearchDto);
			listDetailsBudgetRecettes = this.serviceLocator.getDetailsProjetBudgetEtablissementService()
					.findAllByExample(exempleDep);

		}
	}

	/**
	 * Cloturer le projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:19:24
	 */
	public void closingAction() {

	}

	/**
	 * transferer les valeurs de montant
	 */
	public void transferAction() {
		// TODO
		if (listDetailsBudgetDepenses != null) {
			for (DetailsProjetBudgetEtablissementDto detail : listDetailsBudgetDepenses)
				detail.setMontantFinal(detail.getMontantBesoins());
		}
		if (listDetailsBudgetRecettes != null) {
			for (DetailsProjetBudgetEtablissementDto detail : listDetailsBudgetRecettes)
				detail.setMontantFinal(detail.getMontantBesoins());
		}
	}

	/**
	 * Annuler l'operation encours sur le projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 17:20:07
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

	public LazyDataModel<ProjetBudgetDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<ProjetBudgetDto> model) {
		this.model = model;
	}

	public ProjetBudgetDto getProjetBudgetDto() {
		return projetBudgetDto;
	}

	public void setProjetBudgetDto(ProjetBudgetDto projetBudgetDto) {
		this.projetBudgetDto = projetBudgetDto;
	}

	public List<SelectItem> getListUnitesMonetaires() {
		return listUnitesMonetaires;
	}

	public void setListUnitesMonetaires(List<SelectItem> listUnitesMonetaires) {
		this.listUnitesMonetaires = listUnitesMonetaires;
	}

	public List<SelectItem> getListTypesBudget() {
		return listTypesBudget;
	}

	public ProjetBudgetDto getProjetBudgetSearchDto() {
		return projetBudgetSearchDto;
	}

	public void setProjetBudgetSearchDto(ProjetBudgetDto projetBudgetSearchDto) {
		this.projetBudgetSearchDto = projetBudgetSearchDto;
	}

	public void setListTypesBudget(List<SelectItem> listTypesBudget) {
		this.listTypesBudget = listTypesBudget;
	}

	public List<SelectItem> getListProjetsBudgetsParent() {
		return listProjetsBudgetsParent;
	}

	public void setListProjetsBudgetsParent(List<SelectItem> listProjetsBudgetsParent) {
		this.listProjetsBudgetsParent = listProjetsBudgetsParent;
	}

	/**
	 * @return the listDetailsBudgetDepenses
	 */
	public List<DetailsProjetBudgetEtablissementDto> getListDetailsBudgetDepenses() {
		return listDetailsBudgetDepenses;
	}

	/**
	 * @param listDetailsBudgetDepenses
	 *            the listDetailsBudgetDepenses to set
	 */
	public void setListDetailsBudgetDepenses(List<DetailsProjetBudgetEtablissementDto> listDetailsBudgetDepenses) {
		this.listDetailsBudgetDepenses = listDetailsBudgetDepenses;
	}

	/**
	 * @return the listDetailsBudgetRecettes
	 */
	public List<DetailsProjetBudgetEtablissementDto> getListDetailsBudgetRecettes() {
		return listDetailsBudgetRecettes;
	}

	/**
	 * @param listDetailsBudgetRecettes
	 *            the listDetailsBudgetRecettes to set
	 */
	public void setListDetailsBudgetRecettes(List<DetailsProjetBudgetEtablissementDto> listDetailsBudgetRecettes) {
		this.listDetailsBudgetRecettes = listDetailsBudgetRecettes;
	}

	/**
	 * @return the listDetailsBudgetDepensesPojo
	 */
	public List<DetailBudgetPojo> getListDetailsBudgetDepensesPojo() {
		return listDetailsBudgetDepensesPojo;
	}

	/**
	 * @param listDetailsBudgetDepensesPojo
	 *            the listDetailsBudgetDepensesPojo to set
	 */
	public void setListDetailsBudgetDepensesPojo(List<DetailBudgetPojo> listDetailsBudgetDepensesPojo) {
		this.listDetailsBudgetDepensesPojo = listDetailsBudgetDepensesPojo;
	}

	/**
	 * @return the listDetailsBudgetRecettPojo
	 */
	public List<DetailBudgetPojo> getListDetailsBudgetRecettPojo() {
		return listDetailsBudgetRecettPojo;
	}

	/**
	 * @param listDetailsBudgetRecettPojo
	 *            the listDetailsBudgetRecettPojo to set
	 */
	public void setListDetailsBudgetRecettPojo(List<DetailBudgetPojo> listDetailsBudgetRecettPojo) {
		this.listDetailsBudgetRecettPojo = listDetailsBudgetRecettPojo;
	}

	public List<SelectItem> getListEtablissements() {
		if (listEtablissements == null)
			listEtablissements = findListeEtablissements();

		return listEtablissements;
	}

	public RefEtablissementDto getEtablissementSearchDto() {
		return etablissementSearchDto;
	}

	public void setEtablissementSearchDto(RefEtablissementDto etablissementSearchDto) {
		this.etablissementSearchDto = etablissementSearchDto;
	}

	/**
	 * @return the projetBudgetHistory
	 */
	public List<SituationEntiteOccurrenceDto> getProjetBudgetHistory() {
		// historique des situations
		projetBudgetHistory = serviceLocator.getSituationService().getEntityOccurrenceHistory(
				UtilConstants.ENTITE_GFC_PROJET_BUDGET_CODE, projetBudgetDto.getId());
		return projetBudgetHistory;
	}

	/**
	 * @param projetBudgetHistory
	 *            the projetBudgetHistory to set
	 */
	public void setProjetBudgetHistory(List<SituationEntiteOccurrenceDto> projetBudgetHistory) {
		this.projetBudgetHistory = projetBudgetHistory;
	}

}