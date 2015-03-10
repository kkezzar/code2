package dz.gov.mesrs.sii.gfc.web.jsf.bean.parametrageBudget.regies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.AffectationEtabChapitreArticleDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.AffectationRegieChapitreArticleDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.CompteDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.RegieDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.GFCConstantBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * @author salem chouikh
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "regiesBean")
@ViewScoped
public class RegiesBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<RegieDto> modelRegies;
	private RegieDto regieDto;
	private String searchKeyWord;

	private List<SelectItem> listTypeRegies = new ArrayList<SelectItem>();
	private List<SelectItem> listStructures = new ArrayList<SelectItem>();
	private List<SelectItem> listComptesCCP = new ArrayList<SelectItem>();
	private List<SelectItem> listComptes = new ArrayList<SelectItem>();
	private List<SelectItem> listSituation = new ArrayList<SelectItem>();
	private List<SelectItem> listOrdonnateurs = new ArrayList<SelectItem>();
	private List<SelectItem> listAgentComptables = new ArrayList<SelectItem>();

	private AffectationRegieChapitreArticleDto affectationRegieChapitreArticleDto;
	private AffectationEtabChapitreArticleDto affectationEtabChapitreArticleDto;

	private List<AffectationEtabChapitreArticleDto> affectationEtabChapitreArticleList;

	private NomenclatureDto typeCompteTresor;
	private NomenclatureDto typeCompteCCP;

	// Historique des situations
	private List<SituationEntiteOccurrenceDto> regiesHistory;
	// UI
	private boolean isView;
	private boolean isCrud;

	public RegiesBean() {
	}

	@PostConstruct
	public void init() {
		searchKeyWord = "";
		loadSearchResults();
		typeCompteTresor = serviceLocator.getNomenclatureService().findByCode(GFCConstantBean.CODE_TYPE_COMPTE,
				GFCConstantBean.CODE_TYPE_COMPTE_TRESOR);

		typeCompteCCP = serviceLocator.getNomenclatureService().findByCode(GFCConstantBean.CODE_TYPE_COMPTE,
				GFCConstantBean.CODE_TYPE_COMPTE_CCP);
	}

	/**
	 * Charger la rechcerche
	 * 
	 * @author Salem.Chouikh on : 26 nov. 2014 14:28:01
	 */
	public void loadSearchResults() {
		modelRegies = new LazyDataModel<RegieDto>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3174194836578361532L;
			RegieDto regieDto = new RegieDto();

			@Override
			public List<RegieDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				Boolean descending = null;

				// regieDto.setIntituleFr(searchKeyWord);
				// regieDto.setIntituleAr(searchKeyWord);

				SearchMode searchMode = new SearchMode(pageSize, first);
				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}

				}
				Collection<Integer> collection = new ArrayList<Integer>();
				switch (viewId) {
				case "RegiesEdit":
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_REGIE_CODE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situationEntite.id", collection.toArray(), FilterMode.NOT_IN));
					break;
				case "RegiesCloture":
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_REGIE_CODE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situationEntite.id", collection.toArray(), FilterMode.IN));
					break;
				default:
					break;
				}
				modelRegies.setRowCount(serviceLocator.getRegieService().countAllByExample(regieDto, searchMode));

				return serviceLocator.getRegieService().findAllByKeyWord(searchKeyWord, searchMode);
			}
		};
	}

	/**
	 * 
	 * @author Salem.Chouikh on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			regieDto = (RegieDto) event.getObject();

			isCrud = true;
			listTypeRegies = buildTypeRegieList();

			// buildListStructures();
			listOrdonnateurs = findListOrdonnateurs(regieDto.getRefEtablissement());
			listAgentComptables = findListAgents(regieDto.getRefEtablissement(), regieDto.getRefStructure());
			if (regieDto.getAgentComptable() != null) {
				listComptes = findListComptes(regieDto.getAgentComptable(), typeCompteTresor);
				listComptesCCP = findListComptes(regieDto.getAgentComptable(), typeCompteCCP);

			}
			regieDto.getAffectationRegieChapitreArticles();
			if (regieDto.getCompteCcp() == null)
				regieDto.setCompteCcp(new CompteDto());
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	public void onRowSelectConsult(SelectEvent event) {
		try {
			regieDto = (RegieDto) event.getObject();
			// historique des situations
			regiesHistory = serviceLocator.getSituationService().getEntityOccurrenceHistory(
					UtilConstants.ENTITE_GFC_REGIE_CODE, regieDto.getId());
			isCrud = true;

		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Declancher l'action d'ajout
	 * 
	 * @author Salem.Chouikh on : 26 nov. 2014 14:19:20
	 */

	public void addAction() {
		regieDto = new RegieDto();
		RefEtablissementDto etablissement = this.serviceLocator.getEtablissementService().findById(
				sessionBean.getCompte().getEtabId());
		buildListStructures();
		regieDto.setRefEtablissement(etablissement);
		listTypeRegies = buildTypeRegieList();
		listOrdonnateurs = findListOrdonnateurs(etablissement);
		regieDto.setCompteCcp(new CompteDto());
		// listAgentComptables=findListAgents(refEtablissementDto, structure)
		isCrud = true;
	}

	/**
	 * Declancher l'action de la recherche
	 * 
	 * @author Salem.Chouikh on : 26 nov. 2014 14:27:38
	 */
	public void searchAction() {

		loadSearchResults();
	}

	/**
	 * Enregistrer l'affectation
	 * 
	 * @author Salem.Chouikh on : 26 nov. 2014 14:26:51
	 */
	public void saveAction() {
		try {
			if (regieDto != null) {
				if (regieDto.getAgentComptable() != null && regieDto.getAgentComptable().getId() == null)
					regieDto.setAgentComptable(null);
				if (regieDto.getCompteCcp() != null && regieDto.getCompteCcp().getId() == null)
					regieDto.setCompteCcp(null);
				if (regieDto.getTypeRegie() != null && regieDto.getTypeRegie().getId() == null)
					regieDto.setTypeRegie(null);

				if (regieDto.getId() == null) {
					SituationEntiteDto situation = serviceLocator.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_ENTITE_CODE,
									UtilConstants.SITUATION_CREEE_CODE);
					regieDto.setSituationEntite(situation);
					regieDto = serviceLocator.getRegieService().save(regieDto);

					// enregistrer une occurence de situation
					serviceLocator.getSituationService().saveSituationOccurrence(situation, regieDto.getId());

					CommonMessagesUtils.showSuccessSaveMessage();
				} else {
					regieDto = serviceLocator.getRegieService().save(regieDto);
					CommonMessagesUtils.showSuccessUpdateMessage();
				}

				isCrud = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Action du boutton Retour
	 * 
	 * @author Salem.Chouikh on : 26 nov. 2014 14:27:14
	 */
	public void cancelAction() {
		if (regieDto != null) {

			if (regieDto.getCompteCcp() != null)
				regieDto.setCompteCcp(null);
		}
		isCrud = false;
		isView = false;
	}

	/**
	 * build list type
	 */
	public List<SelectItem> buildTypeRegieList() {
		return findNomenclatureList(GFCConstantBean.CODE_TYPE_REGIE);

	}

	/**
	 * add chpitre autorises
	 */
	public void addAffectationAction() {
		affectationEtabChapitreArticleList = new ArrayList<AffectationEtabChapitreArticleDto>();
		affectationRegieChapitreArticleDto = new AffectationRegieChapitreArticleDto();
		RefEtablissementDto etablissement = this.serviceLocator.getEtablissementService().findById(
				sessionBean.getCompte().getEtabId());
		affectationEtabChapitreArticleList = this.findListAffectations(etablissement);
		// System.out.println("--------------" +
		// affectationEtabChapitreArticleList.size());

	}

	/**
	 * build list structure
	 */
	public List<SelectItem> buildListStructures() {

		listStructures = new ArrayList<SelectItem>();
		try {
			List<RefStructureDto> listStructuresDto = serviceLocator.getStructureService().findAll(
					sessionBean.getCompte().getEtabId());
			for (RefStructureDto ls : listStructuresDto) {
				listStructures.add(new SelectItem(ls.getId(), ls.getLlStructureLatin()));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listStructures;
	}

	/**
	 * change select list section
	 * 
	 * @param event
	 */
	public void onChangeSelect(AjaxBehaviorEvent event) {
		System.out.println("-----change type----");

	}

	/**
	 * change select list section
	 * 
	 * @param event
	 */
	public void onChangeSelectStructure(AjaxBehaviorEvent event) {

		listAgentComptables = new ArrayList<SelectItem>();
		try {
			RefEtablissementDto etablissement = this.serviceLocator.getEtablissementService().findById(
					sessionBean.getCompte().getEtabId());
			RefStructureDto structure = this.serviceLocator.getStructureService().findById(
					regieDto.getRefStructure().getId());
			if (structure != null)
				listAgentComptables = findListAgents(etablissement, structure);
			else
				listAgentComptables = new ArrayList<SelectItem>();
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * delete Section
	 */
	public void deleteAction() {
		try {
			if (regieDto != null) {
				serviceLocator.getRegieService().remove(regieDto);
				CommonMessagesUtils.showSuccessDeleteMessage();
				isCrud = false;
			}
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * 
	 * @return
	 */
	public void confirmAffectationAction() {

		if (this.affectationEtabChapitreArticleList != null && this.affectationEtabChapitreArticleList.size() > 0) {
			for (AffectationEtabChapitreArticleDto affChapitreArticle : this.affectationEtabChapitreArticleList) {
				System.out.println("-------affChapitreArticle----" + affChapitreArticle.isSelected());

				affectationRegieChapitreArticleDto = new AffectationRegieChapitreArticleDto();
				affectationRegieChapitreArticleDto.setRegie(regieDto);
				affectationRegieChapitreArticleDto.setAffectationEtabChapitreArticle(affChapitreArticle);

				regieDto.getAffectationRegieChapitreArticles().add(affectationRegieChapitreArticleDto);
			}
		}

	}

	/**
	 * 
	 * @param affectationEtabStrAgentComptableDto
	 */
	public void removeAffectationAction(AffectationRegieChapitreArticleDto affectationRegieChapitreArticleDto) {
		this.affectationRegieChapitreArticleDto = affectationRegieChapitreArticleDto;

		regieDto.getAffectationRegieChapitreArticles().remove(this.affectationRegieChapitreArticleDto);
	}

	public void doAction() {

	}

	/**
	 * 
	 * @param event
	 */
	public void onChangeSelectAgent(AjaxBehaviorEvent event) {

		listComptes = new ArrayList<SelectItem>();
		listComptesCCP = new ArrayList<SelectItem>();
		try {

			if (regieDto.getAgentComptable() != null) {
				listComptes = findListComptes(regieDto.getAgentComptable(), typeCompteTresor);
				listComptesCCP = findListComptes(regieDto.getAgentComptable(), typeCompteCCP);

			}
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Cloturer une regie
	 * 
	 * @author
	 */
	public void closingAction() {
		try {
			if (regieDto != null) {
				if (regieDto.getAgentComptable() != null && regieDto.getAgentComptable().getId() == 0)
					regieDto.setAgentComptable(null);

				SituationEntiteDto situation = serviceLocator.getSituationService()
						.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_REGIE_CODE,
								UtilConstants.SITUATION_CLOTUREE_CODE);
				situation.setIdEntite(regieDto.getId());
				regieDto.setSituationEntite(situation);
				regieDto.setDateCloture(new Date());
				serviceLocator.getRegieService().save(regieDto);
				// enregistrer une occurence de situation
				serviceLocator.getSituationService().saveSituationOccurrence(situation, regieDto.getId());

				CommonMessagesUtils.showSuccessClosingMessage();
				isCrud = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * validateAction()
	 */
	public void validateAction() {
		try {
			if (regieDto != null) {
				if (regieDto.getAgentComptable() != null && regieDto.getAgentComptable().getId() == null)
					regieDto.setAgentComptable(null);
				if (regieDto.getCompteCcp() != null && regieDto.getCompteCcp().getId() == null)
					regieDto.setCompteCcp(null);
				if (regieDto.getTypeRegie() != null && regieDto.getTypeRegie().getId() == null)
					regieDto.setTypeRegie(null);
				SituationEntiteDto situation = serviceLocator.getSituationService()
						.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_REGIE_CODE,
								UtilConstants.SITUATION_VALIDEE_CODE);
				situation.setIdEntite(regieDto.getId());
				regieDto.setSituationEntite(situation);
				serviceLocator.getRegieService().save(regieDto);
				// enregistrer une occurence de situation
				serviceLocator.getSituationService().saveSituationOccurrence(situation, regieDto.getId());

				CommonMessagesUtils.showSuccessValidationMessage();
				isCrud = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	public boolean isView() {
		return isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	/**
	 * @return the searchKeyWord
	 */
	public String getSearchKeyWord() {
		return searchKeyWord;
	}

	/**
	 * @param searchKeyWord
	 *            the searchKeyWord to set
	 */
	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}

	/**
	 * @return the regieDto
	 */
	public RegieDto getRegieDto() {
		return regieDto;
	}

	/**
	 * @param regieDto
	 *            the regieDto to set
	 */
	public void setRegieDto(RegieDto regieDto) {
		this.regieDto = regieDto;
	}

	/**
	 * @return the modelRegies
	 */
	public LazyDataModel<RegieDto> getModelRegies() {
		return modelRegies;
	}

	/**
	 * @param modelRegies
	 *            the modelRegies to set
	 */
	public void setModelRegies(LazyDataModel<RegieDto> modelRegies) {
		this.modelRegies = modelRegies;
	}

	/**
	 * @return the listTypeRegies
	 */
	public List<SelectItem> getListTypeRegies() {
		return listTypeRegies;
	}

	/**
	 * @param listTypeRegies
	 *            the listTypeRegies to set
	 */
	public void setListTypeRegies(List<SelectItem> listTypeRegies) {
		this.listTypeRegies = listTypeRegies;
	}

	/**
	 * @return the listStructures
	 */
	public List<SelectItem> getListStructures() {
		if (listStructures == null || listStructures.size() == 0) {
			buildListStructures();
		}
		return listStructures;
	}

	/**
	 * @param listStructures
	 *            the listStructures to set
	 */
	public void setListStructures(List<SelectItem> listStructures) {
		this.listStructures = listStructures;
	}

	/**
	 * @return the listComptesCCP
	 */
	public List<SelectItem> getListComptesCCP() {
		return listComptesCCP;
	}

	/**
	 * @param listComptesCCP
	 *            the listComptesCCP to set
	 */
	public void setListComptesCCP(List<SelectItem> listComptesCCP) {
		this.listComptesCCP = listComptesCCP;
	}

	/**
	 * @return the listComptes
	 */
	public List<SelectItem> getListComptes() {
		return listComptes;
	}

	/**
	 * @param listComptes
	 *            the listComptes to set
	 */
	public void setListComptes(List<SelectItem> listComptes) {
		this.listComptes = listComptes;
	}

	/**
	 * @return the listSituation
	 */
	public List<SelectItem> getListSituation() {
		return listSituation;
	}

	/**
	 * @param listSituation
	 *            the listSituation to set
	 */
	public void setListSituation(List<SelectItem> listSituation) {
		this.listSituation = listSituation;
	}

	/**
	 * @return the listOrdonnateurs
	 */
	public List<SelectItem> getListOrdonnateurs() {
		return listOrdonnateurs;
	}

	/**
	 * @param listOrdonnateurs
	 *            the listOrdonnateurs to set
	 */
	public void setListOrdonnateurs(List<SelectItem> listOrdonnateurs) {
		this.listOrdonnateurs = listOrdonnateurs;
	}

	/**
	 * @return the listAgentComptables
	 */
	public List<SelectItem> getListAgentComptables() {
		return listAgentComptables;
	}

	/**
	 * @param listAgentComptables
	 *            the listAgentComptables to set
	 */
	public void setListAgentComptables(List<SelectItem> listAgentComptables) {
		this.listAgentComptables = listAgentComptables;
	}

	/**
	 * @return the affectationRegieChapitreArticleDto
	 */
	public AffectationRegieChapitreArticleDto getAffectationRegieChapitreArticleDto() {
		return affectationRegieChapitreArticleDto;
	}

	/**
	 * @param affectationRegieChapitreArticleDto
	 *            the affectationRegieChapitreArticleDto to set
	 */
	public void setAffectationRegieChapitreArticleDto(
			AffectationRegieChapitreArticleDto affectationRegieChapitreArticleDto) {
		this.affectationRegieChapitreArticleDto = affectationRegieChapitreArticleDto;
	}

	/**
	 * @return the affectationEtabChapitreArticleDto
	 */
	public AffectationEtabChapitreArticleDto getAffectationEtabChapitreArticleDto() {
		return affectationEtabChapitreArticleDto;
	}

	/**
	 * @param affectationEtabChapitreArticleDto
	 *            the affectationEtabChapitreArticleDto to set
	 */
	public void setAffectationEtabChapitreArticleDto(AffectationEtabChapitreArticleDto affectationEtabChapitreArticleDto) {
		this.affectationEtabChapitreArticleDto = affectationEtabChapitreArticleDto;
	}

	/**
	 * @return the affectationEtabChapitreArticleList
	 */
	public List<AffectationEtabChapitreArticleDto> getAffectationEtabChapitreArticleList() {
		return affectationEtabChapitreArticleList;
	}

	/**
	 * @param affectationEtabChapitreArticleList
	 *            the affectationEtabChapitreArticleList to set
	 */
	public void setAffectationEtabChapitreArticleList(
			List<AffectationEtabChapitreArticleDto> affectationEtabChapitreArticleList) {
		this.affectationEtabChapitreArticleList = affectationEtabChapitreArticleList;
	}

	/**
	 * @return the typeCompteTresor
	 */
	public NomenclatureDto getTypeCompteTresor() {
		return typeCompteTresor;
	}

	/**
	 * @param typeCompteTresor
	 *            the typeCompteTresor to set
	 */
	public void setTypeCompteTresor(NomenclatureDto typeCompteTresor) {
		this.typeCompteTresor = typeCompteTresor;
	}

	/**
	 * @return the typeCompteCCP
	 */
	public NomenclatureDto getTypeCompteCCP() {
		return typeCompteCCP;
	}

	/**
	 * @param typeCompteCCP
	 *            the typeCompteCCP to set
	 */
	public void setTypeCompteCCP(NomenclatureDto typeCompteCCP) {
		this.typeCompteCCP = typeCompteCCP;
	}

	/**
	 * @return the regiesHistory
	 */
	public List<SituationEntiteOccurrenceDto> getRegiesHistory() {
		return regiesHistory;
	}

	/**
	 * @param regiesHistory
	 *            the regiesHistory to set
	 */
	public void setRegiesHistory(List<SituationEntiteOccurrenceDto> regiesHistory) {
		this.regiesHistory = regiesHistory;
	}

}
