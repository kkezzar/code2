package dz.gov.mesrs.sii.gfc.web.jsf.bean.parametrageBudget.intervenantsBudgets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.dao.DataIntegrityViolationException;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.AffectationEtabStrAgentComptableDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.AgentComptableDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * Managedbean qui gere les ecrans de gestion des agents comptables
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "agentsComptablesBean")
@ViewScoped
public class AgentsComptablesBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<AgentComptableDto> model;
	private AgentComptableDto agentComptableDto;
	private AgentComptableDto agentComptableSearchDto;
	private AffectationEtabStrAgentComptableDto affectationEtabStrAgentComptableDto;

	// UI
	private List<SelectItem> listEtablissements;
	private List<SelectItem> listStructures;

	private boolean isView;
	private boolean isCrud;

	private Integer selectedAffectationIndex;

	public AgentsComptablesBean() {
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
		listEtablissements = new ArrayList<SelectItem>();
		listStructures = new ArrayList<SelectItem>();

		agentComptableSearchDto = new AgentComptableDto();
		affectationEtabStrAgentComptableDto = new AffectationEtabStrAgentComptableDto();
	}

	/**
	 * Charger les resultats de la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 1 dÃ©c. 2014 11:11:46
	 */
	private void loadSearchResults() {

		model = new LazyDataModel<AgentComptableDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(AgentComptableDto agentComptableDto) {
				return agentComptableDto.getId();
			}

			@Override
			public List<AgentComptableDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				Boolean descending = null;
				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}

				}
				model.setRowCount(serviceLocator.getAgentComptableService().countAllByExample(agentComptableSearchDto));
				SearchMode searchMode = new SearchMode(pageSize, first);
				return serviceLocator.getAgentComptableService().findAllByExample(agentComptableSearchDto, searchMode);
			}
		};
	}

	/**
	 * Lancer la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 1 dÃ©c. 2014 11:12:13
	 */
	public void searchAction() {
	}

	/**
	 * Evenement de selection d'un agent comptable
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		agentComptableDto = (AgentComptableDto) event.getObject();
		isCrud = true;
		isView = true;
	}

	/**
	 * @author Mounir.MESSAOUDI on : 1 dÃ©c. 2014 11:54:25
	 */
	public void addAction() {
		agentComptableDto = new AgentComptableDto();
		isCrud = true;
	}

	/**
	 * Evenement de selection d'un individu depuis la boite de dialgue
	 * 
	 * @author Mounir.MESSAOUDI on : 11 déc. 2014 12:16:58
	 * @param event
	 */
	public void onIndividuSelect(SelectEvent event) {
		RefIndividuDto refIndividuDto = (RefIndividuDto) event.getObject();
		agentComptableDto.setRefIndividu(refIndividuDto);
	}

	/**
	 * Ouvrir la boite de dialogue pour ajouter une nouvelle affectation
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 10:31:40
	 */
	public void addAffectationAction() {
		affectationEtabStrAgentComptableDto = null;
		affectationEtabStrAgentComptableDto = new AffectationEtabStrAgentComptableDto();
		listStructures = new ArrayList<>();
	}

	/**
	 * Modifier l'affectation
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 08:32:37
	 */
	public void editAffectationAction(AffectationEtabStrAgentComptableDto affectationEtabStrAgentComptableDto) {
		this.affectationEtabStrAgentComptableDto = affectationEtabStrAgentComptableDto;
		if (affectationEtabStrAgentComptableDto.getRefStructure() == null) {
			affectationEtabStrAgentComptableDto.setRefStructure(new RefStructureDto());
		}
	}

	/**
	 * Supprimer l'affectation
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 08:32:37
	 */
	public void removeAffectationAction(AffectationEtabStrAgentComptableDto affectationEtabStrAgentComptableDto) {
		this.affectationEtabStrAgentComptableDto = affectationEtabStrAgentComptableDto;
		if (this.affectationEtabStrAgentComptableDto.getRefStructure() != null
				&& this.affectationEtabStrAgentComptableDto.getRefStructure().getId() == null) {
			this.affectationEtabStrAgentComptableDto.setRefStructure(null);
		}
		agentComptableDto.getAffectationEtabStrAgentComptables().remove(this.affectationEtabStrAgentComptableDto);
	}

	/**
	 * Enregistrer l'affectation
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 08:56:57
	 */
	public void saveAffectationAction() {
		if (affectationEtabStrAgentComptableDto != null) {
			// if (affectationEtabStrAgentComptableDto.getRefStructure().getId()
			// == null) {
			// affectationEtabStrAgentComptableDto.setRefStructure(null);
			// }
			if (affectationEtabStrAgentComptableDto.getId() == null
					&& affectationEtabStrAgentComptableDto.getAgentComptable() == null) {
				affectationEtabStrAgentComptableDto.setAgentComptable(agentComptableDto);
				agentComptableDto.getAffectationEtabStrAgentComptables().add(affectationEtabStrAgentComptableDto);
			}
		}

	}

	/**
	 * Enregistrer l'agent comptable
	 * 
	 * @author Mounir.MESSAOUDI on : 1 dÃ©c. 2014 11:12:55
	 */
	public void saveAction() {
		try {
			if (agentComptableDto != null) {
				List<AffectationEtabStrAgentComptableDto> l = agentComptableDto.getAffectationEtabStrAgentComptables();
				if (!l.isEmpty()) {
					for (AffectationEtabStrAgentComptableDto a : l) {
						if (a.getRefStructure() != null && a.getRefStructure().getId() == null) {
							a.setRefStructure(null);
						}
					}
				}

				if (agentComptableDto.getId() == null) {

					agentComptableDto = serviceLocator.getAgentComptableService().save(agentComptableDto);
					CommonMessagesUtils.showSuccessSaveMessage();
				} else {
					serviceLocator.getAgentComptableService().save(agentComptableDto);
					CommonMessagesUtils.showSuccessUpdateMessage();
				}
			}
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Supprimer le regisseur
	 * 
	 * @author Mounir.MESSAOUDI on : 1 dÃ©c. 2014 15:32:17
	 */
	public void deleteAction() {
		try {
			if (agentComptableDto != null) {
				serviceLocator.getAgentComptableService().remove(agentComptableDto);
				CommonMessagesUtils.showSuccessDeleteMessage();
				isCrud = false;
			}
		} catch (DataIntegrityViolationException e) {
			CommonMessagesUtils.showConstraintViolationErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

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

	public LazyDataModel<AgentComptableDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<AgentComptableDto> model) {
		this.model = model;
	}

	public AgentComptableDto getAgentComptableDto() {
		return agentComptableDto;
	}

	public void setAgentComptableDto(AgentComptableDto agentComptableDto) {
		this.agentComptableDto = agentComptableDto;
	}

	public AgentComptableDto getAgentComptableSearchDto() {
		return agentComptableSearchDto;
	}

	public void setAgentComptableSearchDto(AgentComptableDto agentComptableSearchDto) {
		this.agentComptableSearchDto = agentComptableSearchDto;
	}

	/**
	 * Handle changement de l'etalissement
	 * 
	 * @author Mounir.MESSAOUDI on : 2 dÃ©c. 2014 16:38:44
	 * @param event
	 * @throws Exception
	 */
	public void handleEtabSelectOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		if (this.affectationEtabStrAgentComptableDto != null) {
			listStructures = new ArrayList<SelectItem>();

			Integer idEtablissement = affectationEtabStrAgentComptableDto.getRefEtablissement().getId();
			RefEtablissementDto e = serviceLocator.getEtablissementService().findById(idEtablissement);
			affectationEtabStrAgentComptableDto.setRefEtablissement(e);

			List<RefStructureDto> listStructuresDto = serviceLocator.getStructureService().findAll(idEtablissement);
			for (RefStructureDto ls : listStructuresDto) {
				listStructures.add(new SelectItem(ls.getId(), ls.getLlStructureLatin()));
			}
		}
	}

	/**
	 * Handle changement de la structure
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 09:49:50
	 * @param event
	 * @throws Exception
	 */
	public void handleStrSelectOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		if (this.affectationEtabStrAgentComptableDto != null) {
			Integer idStrcuture = affectationEtabStrAgentComptableDto.getRefStructure().getId();
			RefStructureDto s = serviceLocator.getStructureService().findById(idStrcuture);
			affectationEtabStrAgentComptableDto.setRefStructure(s);
		}
	}

	public List<SelectItem> getListEtablissements() {
		if (listEtablissements == null || listEtablissements.isEmpty())
			listEtablissements = findListeEtablissements();

		return listEtablissements;
	}

	public void setListEtablissements(List<SelectItem> listEtablissements) {
		this.listEtablissements = listEtablissements;
	}

	public List<SelectItem> getListStructures() {
		return listStructures;
	}

	public void setListStructures(List<SelectItem> listStructures) {
		this.listStructures = listStructures;
	}

	public AffectationEtabStrAgentComptableDto getAffectationEtabStrAgentComptableDto() {
		return affectationEtabStrAgentComptableDto;
	}

	public void setAffectationEtabStrAgentComptableDto(
			AffectationEtabStrAgentComptableDto affectationEtabStrAgentComptableDto) {
		this.affectationEtabStrAgentComptableDto = affectationEtabStrAgentComptableDto;
	}

	public Integer getSelectedAffectationIndex() {
		return selectedAffectationIndex;
	}

	public void setSelectedAffectationIndex(Integer selectedAffectationIndex) {
		this.selectedAffectationIndex = selectedAffectationIndex;
	}

}
