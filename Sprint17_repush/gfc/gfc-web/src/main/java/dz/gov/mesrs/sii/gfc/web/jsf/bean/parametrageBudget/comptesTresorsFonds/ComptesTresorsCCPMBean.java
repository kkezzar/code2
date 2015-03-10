package dz.gov.mesrs.sii.gfc.web.jsf.bean.parametrageBudget.comptesTresorsFonds;

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
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.AgentComptableDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.CompteDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.GFCConstantBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "comptesTresorsCCPBean")
@ViewScoped
public class ComptesTresorsCCPMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<CompteDto> model;
	private CompteDto compteDto;

	private CompteDto compteSearchDto;

	private NomenclatureDto typeCompteTresor;
	private NomenclatureDto typeCompteCCP;

	// UI

	private boolean isView;
	private boolean isCrud;

	private List<SelectItem> listAgentsComptable;

	private List<CompteDto> revisions;

	public ComptesTresorsCCPMBean() {
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
		typeCompteTresor = serviceLocator.getNomenclatureService().findByCode(GFCConstantBean.CODE_TYPE_COMPTE,
				GFCConstantBean.CODE_TYPE_COMPTE_TRESOR);

		typeCompteCCP = serviceLocator.getNomenclatureService().findByCode(GFCConstantBean.CODE_TYPE_COMPTE,
				GFCConstantBean.CODE_TYPE_COMPTE_CCP);

		compteSearchDto = new CompteDto();
	}

	private void loadSearchResults() {

		if (viewId.equals("ComptesTresorEdit") || viewId.equals("ComptesTresor")
				|| viewId.equals("ComptesTresorCloture")) {
			compteSearchDto.setType(typeCompteTresor);
		} else {
			compteSearchDto.setType(typeCompteCCP);
		}

		model = new LazyDataModel<CompteDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(CompteDto compteDto) {
				return compteDto.getId();
			}

			@Override
			public List<CompteDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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
				switch (viewId) {
				case "ComptesCCPEdit":
				case "ComptesTresorEdit":
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_ENTITE_CODE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.NOT_IN));
					break;
				case "ComptesCCPCloture":
				case "ComptesTresorCloture":
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_ENTITE_CODE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));
					break;
				default:
					break;
				}

				model.setRowCount(serviceLocator.getCompteService().countAllByExample(compteSearchDto, searchMode));
				return serviceLocator.getCompteService().findAllByExample(compteSearchDto, searchMode);
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
		compteDto = (CompteDto) event.getObject();
		isCrud = true;
		isView = true;
	}

	/**
	 * Ajouter un compte tresor
	 * 
	 * @author Mounir.MESSAOUDI on : 10 déc. 2014 09:42:35
	 */
	public void addCompteTresorAction() {
		compteDto = new CompteDto();
		compteDto.setType(typeCompteTresor);
		isCrud = true;
	}

	/**
	 * Ajouter un compte CCP
	 * 
	 * @author Mounir.MESSAOUDI on : 10 déc. 2014 09:42:10
	 */
	public void addCompteCCPAction() {
		compteDto = new CompteDto();
		compteDto.setType(typeCompteCCP);
		isCrud = true;
	}

	/**
	 * Enregistrer le compte
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 15:28:41
	 */
	public void saveAction() {
		try {
			if (compteDto != null) {
				if (compteDto.getSituation().getId() == 0) {
					SituationEntiteDto situation = serviceLocator.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_ENTITE_CODE,
									UtilConstants.SITUATION_CREEE_CODE);
					compteDto.setSituation(situation);
					compteDto = serviceLocator.getCompteService().save(compteDto);
					CommonMessagesUtils.showSuccessSaveMessage();
				} else {
					serviceLocator.getCompteService().save(compteDto);
					CommonMessagesUtils.showSuccessUpdateMessage();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Valider le compte
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 15:29:15
	 */
	public void validateAction() {
		try {
			SituationEntiteDto situation = serviceLocator.getSituationService().findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_ENTITE_CODE, UtilConstants.SITUATION_VALIDEE_CODE);
			situation.setIdEntite(compteDto.getId());
			compteDto.setSituation(situation);
			serviceLocator.getCompteService().save(compteDto);
			CommonMessagesUtils.showSuccessValidationMessage();
			isCrud = false;
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Cloturer un compte
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 17:24:58
	 */
	public void closingAction() {
		try {
			if (compteDto != null) {
				SituationEntiteDto situation = serviceLocator.getSituationService()
						.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_ENTITE_CODE,
								UtilConstants.SITUATION_CLOTUREE_CODE);
				situation.setIdEntite(compteDto.getId());
				compteDto.setSituation(situation);
				serviceLocator.getCompteService().save(compteDto);

				CommonMessagesUtils.showSuccessClosingMessage();
				isCrud = false;
			}
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Supprimer le compte
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 15:29:22
	 */
	public void deleteAction() {
		try {
			if (compteDto != null) {
				serviceLocator.getCompteService().remove(compteDto);
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

	/**
	 * Annuler l'operation sur le compte
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 15:29:29
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

	public LazyDataModel<CompteDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<CompteDto> model) {
		this.model = model;
	}

	public CompteDto getCompteDto() {
		return compteDto;
	}

	public void setCompteDto(CompteDto compteDto) {
		this.compteDto = compteDto;
	}

	public CompteDto getCompteSearchDto() {
		return compteSearchDto;
	}

	public void setCompteSearchDto(CompteDto compteSearchDto) {
		this.compteSearchDto = compteSearchDto;
	}

	/**
	 * Renvoi la liste des agents comptable
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 13:56:40
	 * @return liste des agents comptable
	 */
	public List<SelectItem> getListAgentsComptable() {
		if (listAgentsComptable == null || listAgentsComptable.isEmpty()) {

			listAgentsComptable = new ArrayList<>();
			List<AgentComptableDto> l = serviceLocator.getAgentComptableService().findAll();
			for (AgentComptableDto agentComptableDto : l) {
				SelectItem s = new SelectItem(agentComptableDto.getId(), agentComptableDto.getCode() + " - "
						+ agentComptableDto.getRefIndividu().getNomLatin() + " "
						+ agentComptableDto.getRefIndividu().getPrenomLatin());
				listAgentsComptable.add(s);
			}
		}
		return listAgentsComptable;
	}

	public void setListAgentsComptable(List<SelectItem> listAgentsComptable) {
		this.listAgentsComptable = listAgentsComptable;
	}

	public List<CompteDto> getRevisions() {
		return revisions;
	}

	public void setRevisions(List<CompteDto> revisions) {
		this.revisions = revisions;
	}

}
