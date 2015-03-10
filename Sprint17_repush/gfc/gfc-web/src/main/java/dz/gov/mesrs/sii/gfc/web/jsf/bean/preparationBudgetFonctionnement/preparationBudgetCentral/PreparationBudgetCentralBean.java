package dz.gov.mesrs.sii.gfc.web.jsf.bean.preparationBudgetFonctionnement.preparationBudgetCentral;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.google.common.collect.ListMultimap;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.ArticleDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ChapitreDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsFicheBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.FicheBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.SectionDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * Prepare le budget au niveau central
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "preparationBudgetCentralBean")
@ViewScoped
public class PreparationBudgetCentralBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<FicheBesoinsDto> model;

	private FicheBesoinsDto ficheBesoinsDto;
	private FicheBesoinsDto ficheBesoinsSearchDto;

	private DetailsFicheBesoinsDto detailsFicheBesoinsDto;
	private DetailsFicheBesoinsDto detailsFicheBesoinsSearchDto;

	private LazyDataModel<DetailsFicheBesoinsDto> modelDetailsFicheBesoins;

	private ListMultimap<String, DetailsFicheBesoinsDto> listDetailsFicheBesoinDto;

	private List<Object[]> totaux;

	// UI
	private boolean isView;
	private boolean isCrud;

	private boolean isViewDetail;
	private boolean isCrudDetail;

	private List<SelectItem> listStructures;
	private RefStructureDto structureSearchDto;

	private List<SelectItem> listSections;
	private List<SelectItem> listChapitres;
	private List<SelectItem> listArticles;

	private List<SelectItem> listFichesBesoins;

	private boolean isBudgetRecette;

	public PreparationBudgetCentralBean() {

	}

	@PostConstruct
	public void init() {
		initUI();
		loadSearchResults();

	}

	private void initUI() {
		ficheBesoinsSearchDto = new FicheBesoinsDto();
		detailsFicheBesoinsDto = new DetailsFicheBesoinsDto();
		detailsFicheBesoinsSearchDto = new DetailsFicheBesoinsDto();
		structureSearchDto = new RefStructureDto();

		listChapitres = new ArrayList<SelectItem>();
		listArticles = new ArrayList<SelectItem>();

	}

	private void loadSearchResults() {
		// les fiches des besoins de l'etablissement en cours!
		RefEtablissementDto etablissement = new RefEtablissementDto();
		etablissement.setId(sessionBean.getCompte().getEtabId());
		ficheBesoinsSearchDto.setEtablissement(etablissement);

		model = new LazyDataModel<FicheBesoinsDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(FicheBesoinsDto ficheBesoinsDto) {
				return ficheBesoinsDto.getId();
			}

			@Override
			public List<FicheBesoinsDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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

				// en mode edit ?
				if (viewId.equals("DemandesBudgetairesEdit")) {
					// seulement les fiches des besoins qui sont en cours de
					// preparation d'enreg des demandes budgetaires!
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_FICHE_BESOINS_CODE,
									UtilConstants.SITUATION_EN_COURS_PREPARATION_ENREG_DEMANDES_BUDG_CODE).getId());

					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));
				}

				// searchMode.addSortField(sortField);
				model.setRowCount(serviceLocator.getFicheBesoinsService().countAllByExample(ficheBesoinsSearchDto,
						searchMode));

				List<FicheBesoinsDto> result = serviceLocator.getFicheBesoinsService().findAllByExample(
						ficheBesoinsSearchDto, searchMode);

				if (model.getRowCount() == 1) {
					ficheBesoinsDto = result.get(0);
					structureSearchDto = new RefStructureDto();
					loadDetailsFicheBesoins();

					totaux = serviceLocator.getFicheBesoinsService().getTotalOfFicheBesoins(ficheBesoinsDto);

					isCrud = true;
					isView = true;
				}

				return result;
			}
		};
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 22 déc. 2014 11:02:51
	 */
	public void searchAction() {
		loadSearchResults();
	}

	/**
	 * Evenement de selection d'une fiche des besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 29 déc. 2014 15:53:54
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		ficheBesoinsDto = (FicheBesoinsDto) event.getObject();

		structureSearchDto = new RefStructureDto();
		loadDetailsFicheBesoins();
		totaux = serviceLocator.getFicheBesoinsService().getTotalOfFicheBesoins(ficheBesoinsDto);

		isCrud = true;
		isView = true;
	}

	/**
	 * Evenement de selection d'un detail de fiche des besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelectDetailsFicheBesoins(SelectEvent event) {
		detailsFicheBesoinsDto = (DetailsFicheBesoinsDto) event.getObject();

		// charger les selectMenus
		SectionDto section = detailsFicheBesoinsDto.getChapitre().getIdSection();
		listArticles = new ArrayList<SelectItem>();
		if (section.getId() == null)
			listChapitres = new ArrayList<SelectItem>();
		else {
			RefEtablissementDto etablissement = new RefEtablissementDto();
			etablissement.setId(sessionBean.getCompte().getEtabId());
			// listChapitres = findListeChapitres(section);
			// seulement les chapitres de depenses affectes a l'etablissement
			listChapitres = findListAffectationsChapitres(etablissement, section, false);
		}

		if (listChapitres == null)
			listChapitres = new ArrayList<SelectItem>();

		ChapitreDto chapitre = detailsFicheBesoinsDto.getChapitre();
		listArticles = findListeArticle(chapitre);
		if (listArticles == null)
			listArticles = new ArrayList<SelectItem>();

		isCrudDetail = true;

	}

	private void loadDetailsFicheBesoins() {
		detailsFicheBesoinsSearchDto = new DetailsFicheBesoinsDto();

		detailsFicheBesoinsSearchDto.setStructure(structureSearchDto);
		detailsFicheBesoinsSearchDto.setFicheBesoins(ficheBesoinsDto);

		// charger le detail de la fiche des besoins
		modelDetailsFicheBesoins = new LazyDataModel<DetailsFicheBesoinsDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(DetailsFicheBesoinsDto detailsFicheBesoinsDto) {
				return detailsFicheBesoinsDto.getId();
			}

			@Override
			public List<DetailsFicheBesoinsDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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

				// searchMode.addSortField(sortField);
				modelDetailsFicheBesoins.setRowCount(serviceLocator.getDetailsFicheBesoinsService().countAllByExample(
						detailsFicheBesoinsSearchDto));
				return serviceLocator.getDetailsFicheBesoinsService().findAllByExample(detailsFicheBesoinsSearchDto,
						searchMode);
			}
		};

	}

	/**
	 * Handle changement de la structure
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 09:49:50
	 * @param event
	 * @throws Exception
	 */
	public void handleStrSelectOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		if (this.detailsFicheBesoinsDto != null) {
			Integer idStrcuture = detailsFicheBesoinsDto.getStructure().getId();
			RefStructureDto s = serviceLocator.getStructureService().findById(idStrcuture);
			detailsFicheBesoinsDto.setStructure(s);
		}
	}

	/**
	 * Handle changement du section
	 * 
	 * @author Mounir.MESSAOUDI on : 2 dÃ©c. 2014 16:38:44
	 * @param event
	 * @throws Exception
	 */
	public void handleSectionsSelectOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		SectionDto section = detailsFicheBesoinsDto.getChapitre().getIdSection();
		listArticles = new ArrayList<SelectItem>();
		if (section.getId() == null)
			listChapitres = new ArrayList<SelectItem>();
		else {
			RefEtablissementDto etablissement = new RefEtablissementDto();
			etablissement.setId(sessionBean.getCompte().getEtabId());
			// listChapitres = findListeChapitres(section);
			// seulement les chapitres de depenses affectes a l'etablissement
			listChapitres = findListAffectationsChapitres(etablissement, section, false);
		}

		if (listChapitres == null)
			listChapitres = new ArrayList<SelectItem>();

	}

	/**
	 * Handle changement du chapitre
	 * 
	 * @author Mounir.MESSAOUDI on : 2 dÃ©c. 2014 16:38:44
	 * @param event
	 * @throws Exception
	 */
	public void handleChapitresSelectOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		ChapitreDto chapitre = detailsFicheBesoinsDto.getChapitre();
		listArticles = findListeArticle(chapitre);
		if (listArticles == null)
			listArticles = new ArrayList<SelectItem>();
	}

	/**
	 * Ajouter un nouveau projet de budget
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 18:08:47
	 */
	public void addAction() {
		detailsFicheBesoinsDto = new DetailsFicheBesoinsDto();
		Integer idEtablissement = getSessionBean().getCompte().getEtabId();
		RefEtablissementDto refEtablissementDto = new RefEtablissementDto();
		refEtablissementDto.setId(idEtablissement);

		detailsFicheBesoinsDto.setEtablissement(refEtablissementDto);
		detailsFicheBesoinsDto.setFicheBesoins(ficheBesoinsDto);
		detailsFicheBesoinsDto.setStructure(structureSearchDto);

		listChapitres = new ArrayList<SelectItem>();
		listArticles = new ArrayList<SelectItem>();

		isCrudDetail = true;
	}

	/**
	 * Ajouter ou modifier un detail
	 * 
	 * @author Mounir.MESSAOUDI on : 22 déc. 2014 11:02:18
	 */
	public void saveAction() {
		// pre-save
		if (detailsFicheBesoinsDto.getArticle() != null && detailsFicheBesoinsDto.getArticle().getId() == null) {
			detailsFicheBesoinsDto.setArticle(null);
		}

		detailsFicheBesoinsDto.setParagraphe(null);

		if (detailsFicheBesoinsDto.getId() == null) {
			detailsFicheBesoinsDto = serviceLocator.getDetailsFicheBesoinsService().save(detailsFicheBesoinsDto);
			CommonMessagesUtils.showSuccessSaveMessage();
		} else {
			serviceLocator.getDetailsFicheBesoinsService().save(detailsFicheBesoinsDto);
			CommonMessagesUtils.showSuccessUpdateMessage();
		}

		// post-save
		if (detailsFicheBesoinsDto.getArticle() == null) {
			detailsFicheBesoinsDto.setArticle(new ArticleDto());
		}

		totaux = serviceLocator.getFicheBesoinsService().getTotalOfFicheBesoins(ficheBesoinsDto);

		RequestContext.getCurrentInstance().execute("PF('gererDemBudgetaireWidget').hide()");
	}

	/**
	 * Supprimer le detail de la fiche des besoins
	 */
	public void deleteAction() {
		if (detailsFicheBesoinsDto != null) {
			serviceLocator.getDetailsFicheBesoinsService().remove(detailsFicheBesoinsDto);
			isCrudDetail = false;

			totaux = serviceLocator.getFicheBesoinsService().getTotalOfFicheBesoins(ficheBesoinsDto);

			CommonMessagesUtils.showSuccessDeleteMessage();
		}
	}

	/**
	 * Annuler l'action en cours sur la fiche des besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 22 déc. 2014 11:03:07
	 */
	public void cancelAction() {
		isCrud = false;
		isView = false;
	}

	/**
	 * Annuler l'action en cours sur le detail de la fiche des besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 22 déc. 2014 11:03:07
	 */
	public void cancelDetailAction() {
		isCrudDetail = false;
		isViewDetail = false;
	}

	public boolean isView() {
		return isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public boolean isViewDetail() {
		return isViewDetail;
	}

	public boolean isCrudDetail() {
		return isCrudDetail;
	}

	public LazyDataModel<FicheBesoinsDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<FicheBesoinsDto> model) {
		this.model = model;
	}

	public FicheBesoinsDto getFicheBesoinsSearchDto() {
		return ficheBesoinsSearchDto;
	}

	public void setFicheBesoinsSearchDto(FicheBesoinsDto ficheBesoinsSearchDto) {
		this.ficheBesoinsSearchDto = ficheBesoinsSearchDto;
	}

	public DetailsFicheBesoinsDto getDetailsFicheBesoinsDto() {
		return detailsFicheBesoinsDto;
	}

	public void setDetailsFicheBesoinsDto(DetailsFicheBesoinsDto detailsFicheBesoinsDto) {
		this.detailsFicheBesoinsDto = detailsFicheBesoinsDto;
	}

	public DetailsFicheBesoinsDto getDetailsFicheBesoinsSearchDto() {
		return detailsFicheBesoinsSearchDto;
	}

	public void setDetailsFicheBesoinsSearchDto(DetailsFicheBesoinsDto detailsFicheBesoinsSearchDto) {
		this.detailsFicheBesoinsSearchDto = detailsFicheBesoinsSearchDto;
	}

	public FicheBesoinsDto getFicheBesoinsDto() {
		return ficheBesoinsDto;
	}

	public void setFicheBesoinsDto(FicheBesoinsDto ficheBesoinsDto) {
		this.ficheBesoinsDto = ficheBesoinsDto;
	}

	public LazyDataModel<DetailsFicheBesoinsDto> getModelDetailsFicheBesoins() {
		return modelDetailsFicheBesoins;
	}

	public void setModelDetailsFicheBesoins(LazyDataModel<DetailsFicheBesoinsDto> modelDetailsFicheBesoins) {
		this.modelDetailsFicheBesoins = modelDetailsFicheBesoins;
	}

	public List<SelectItem> getListStructures() {
		if (listStructures == null) {
			Integer idEtablissement = getSessionBean().getCompte().getEtabId();
			RefEtablissementDto refEtablissementDto = new RefEtablissementDto();
			refEtablissementDto.setId(idEtablissement);
			listStructures = findListeStructure(refEtablissementDto);
		}
		return listStructures;
	}

	public void setListStructures(List<SelectItem> listStructures) {
		this.listStructures = listStructures;
	}

	public List<SelectItem> getListSections() {
		if (listSections == null) {
			listSections = findListeSections();
		}
		return listSections;
	}

	public List<SelectItem> getListChapitres() {
		return listChapitres;
	}

	public void setListChapitres(List<SelectItem> listChapitres) {
		this.listChapitres = listChapitres;
	}

	public List<SelectItem> getListArticles() {
		return listArticles;
	}

	public void setListArticles(List<SelectItem> listArticles) {
		this.listArticles = listArticles;
	}

	public RefStructureDto getStructureSearchDto() {
		return structureSearchDto;
	}

	public void setStructureSearchDto(RefStructureDto structureSearchDto) {
		this.structureSearchDto = structureSearchDto;
	}

	public List<SelectItem> getListFichesBesoins() {
		if (listFichesBesoins == null) {
			listFichesBesoins = new ArrayList<SelectItem>();

			// les fiches des besoins de l'etablissement en cours!
			RefEtablissementDto etablissement = new RefEtablissementDto();
			etablissement.setId(sessionBean.getCompte().getEtabId());
			ficheBesoinsSearchDto.setEtablissement(etablissement);

			SituationEntiteDto situationValidee = serviceLocator.getSituationService()
					.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_ENTITE_CODE,
							UtilConstants.SITUATION_EN_COURS_PREPARATION_ENREG_DEMANDES_BUDG_CODE);

			ficheBesoinsSearchDto.setSituation(situationValidee);
			List<FicheBesoinsDto> listFichesBesoinsDto = serviceLocator.getFicheBesoinsService().findAllByExample(
					ficheBesoinsSearchDto);
			if (listFichesBesoinsDto != null && !listFichesBesoinsDto.isEmpty()) {
				for (FicheBesoinsDto ficheBesoinsDto : listFichesBesoinsDto) {
					listFichesBesoins.add(new SelectItem(ficheBesoinsDto.getId(), ficheBesoinsDto.getIdentite() + " - "
							+ ficheBesoinsDto.getIntituleFr()));
				}
			}
		}
		return listFichesBesoins;
	}

	public void setListFichesBesoins(List<SelectItem> listFichesBesoins) {
		this.listFichesBesoins = listFichesBesoins;
	}

	public BigDecimal getTotalMontant(Boolean recette_type) {
		Integer s = structureSearchDto.getId();
		for (Object[] x : totaux) {
			if ((s == x[0] || s != null && s.equals(x[0])) && recette_type == (Boolean) x[1]) {
				return (BigDecimal) x[2];
			}
		}
		return null;
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public BigDecimal getTotalMontant() {
		Double tt = Math.random();
		return new BigDecimal(tt.intValue() + 10000);
	}
}
