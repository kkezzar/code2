package dz.gov.mesrs.sii.gfc.web.jsf.bean.marches;

import java.text.SimpleDateFormat;
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

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.ArticleDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ChapitreDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailEngagementMarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsReparatitionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.EngagementMarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.MarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.SectionDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.GFCConstantBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * Gere les demandes d'engagement
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 18/02/2015 09:40:31
 */
@ManagedBean(name = "demandesEngagementMarcheMBean")
@ViewScoped
public class DemandesEngagementMarcheMBean extends BaseBean {

	public final static String MARCHES_BUNDLE_MSG_NAME = "marchesMsgs";

	private static final long serialVersionUID = 1L;

	private LazyDataModel<EngagementMarcheDto> model;

	private EngagementMarcheDto engagementMarcheDto;
	private DetailEngagementMarcheDto detaiLEngagementMarcheDto;

	private EngagementMarcheDto engagementMarcheSearchDto;
	private MarcheDto marcheSearchDto;

	private List<SelectItem> listNaturesEngagement;

	private List<SelectItem> listMarches;

	private List<SelectItem> listSections;
	private List<SelectItem> listChapitres;
	private List<SelectItem> listArticles;
	private List<SelectItem> listStructures;

	private DetailsReparatitionBudgetDto detailsReparatitionBudgetDto;

	// UI
	private boolean isView;
	private boolean isCrud;

	public String keyWords;
	private List<SituationEntiteOccurrenceDto> engagementMarcheHistory;

	public DemandesEngagementMarcheMBean() {
	}

	@PostConstruct
	public void init() {
		engagementMarcheSearchDto = new EngagementMarcheDto();
		detaiLEngagementMarcheDto = new DetailEngagementMarcheDto();

		detailsReparatitionBudgetDto = new DetailsReparatitionBudgetDto();

		initUI();
		loadSearchResults();
	}

	private void initUI() {
		// TODO Optimisation
		if (viewId.equals("DemanderEngagementMarche")) {
			listNaturesEngagement = findNomenclatureList(GFCConstantBean.CODE_NATURE_ENGAGEMENT_MARCHE);

			// TODO marche de l'etablissement ???
			marcheSearchDto = new MarcheDto();
			marcheSearchDto.setEtablissement(sessionBean.getCurrentEtablissement());
			listMarches = new ArrayList<SelectItem>();
			List<MarcheDto> listMarchesEncours = serviceLocator.getMarcheService().findAllByExample(marcheSearchDto);

			if (listMarchesEncours != null && !listMarchesEncours.isEmpty()) {
				for (MarcheDto marcheDto : listMarchesEncours) {
					SelectItem item = new SelectItem(marcheDto.getId(), marcheDto.getIntituleFr());
					listMarches.add(item);
				}
			}

			listSections = findListeSections();
			listChapitres = new ArrayList<SelectItem>();
			listArticles = new ArrayList<SelectItem>();
			listStructures = findListeStructure(sessionBean.getCurrentEtablissement());
		}

	}

	private void loadSearchResults() {
		model = new LazyDataModel<EngagementMarcheDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(EngagementMarcheDto engagementMarcheDto) {
				return engagementMarcheDto.getId();
			}

			@Override
			public List<EngagementMarcheDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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
				if (viewId.equals("DemanderEngagementMarche")) {
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_ENGAGEMENT_MARCHE,
									UtilConstants.SITUATION_CREEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));

					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_ENGAGEMENT_MARCHE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));
				}

				model.setRowCount(serviceLocator.getEngagementMarcheService().countAllByExample(
						engagementMarcheSearchDto, searchMode));

				return serviceLocator.getEngagementMarcheService().findAllByExample(engagementMarcheSearchDto,
						searchMode);
			}
		};
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public void searchAction() {
		loadSearchResults();
	}

	public LazyDataModel<EngagementMarcheDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<EngagementMarcheDto> model) {
		this.model = model;
	}

	public void addAction() {
		engagementMarcheDto = new EngagementMarcheDto();

		isCrud = true;
	}

	public void addDetailEngagementAction() {
		detaiLEngagementMarcheDto = new DetailEngagementMarcheDto();
	}

	public void onRowSelect(SelectEvent event) {
		engagementMarcheDto = (EngagementMarcheDto) event.getObject();

		isCrud = true;
		isView = true;
	}

	public void onRowSelectDetailEngagement(SelectEvent event) {
		detaiLEngagementMarcheDto = (DetailEngagementMarcheDto) event.getObject();
	}

	public void handleStrSelectOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		if (this.detaiLEngagementMarcheDto != null) {
			Integer idStrcuture = detaiLEngagementMarcheDto.getStructure().getId();
			RefStructureDto s = serviceLocator.getStructureService().findById(idStrcuture);
			detaiLEngagementMarcheDto.setStructure(s);
		}
	}

	public void handleSectionsSelectOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		SectionDto section = detaiLEngagementMarcheDto.getChapitre().getIdSection();
		listArticles = new ArrayList<SelectItem>();
		if (section.getId() == null)
			listChapitres = new ArrayList<SelectItem>();
		else {
			listChapitres = findListAffectationsChapitres(sessionBean.getCurrentEtablissement(), section, false);
		}

		if (listChapitres == null)
			listChapitres = new ArrayList<SelectItem>();

		listArticles = new ArrayList<SelectItem>();
	}

	public void handleChapitresSelectOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		ChapitreDto chapitre = detaiLEngagementMarcheDto.getChapitre();
		listArticles = findListeArticle(chapitre);
		if (listArticles == null)
			listArticles = new ArrayList<SelectItem>();

		detailsReparatitionBudgetDto = serviceLocator.getDetailsReparatitionBudgetService().findUniqueOrNoneByExample(
				detailsReparatitionBudgetDto);

	}

	public void saveAction() {
		if (engagementMarcheDto.getId() == null) {
			NomenclatureDto engagementDepense = serviceLocator.getNomenclatureService().findByCode(
					GFCConstantBean.CODE_TYPE_ENGAGEMENT_MARCHE, GFCConstantBean.CODE_TYPE_ENGAGEMENT_MARCHE_DEPENSE);

			engagementMarcheDto.setTypeEngagement(engagementDepense);

			String thisYear = new SimpleDateFormat("yyyy").format(new Date());
			System.out.println(thisYear);

			EngagementMarcheDto se = new EngagementMarcheDto();
			Integer c = serviceLocator.getEngagementMarcheService().countAllByExample(se);
			String formattedNumber = String.format("%04d", c);
			engagementMarcheDto.setNumero(thisYear + sessionBean.getCurrentEtablissement().getIdentifiant()
					+ formattedNumber);

			engagementMarcheDto = serviceLocator.getEngagementMarcheService().enregistrerDemandeEngagement(
					engagementMarcheDto);
			CommonMessagesUtils.showSuccessSaveMessage();
		} else {
			serviceLocator.getEngagementMarcheService().save(engagementMarcheDto);
			CommonMessagesUtils.showSuccessUpdateMessage();
		}
	}

	public void validateAction() {
		serviceLocator.getEngagementMarcheService().validerDemandeEngagement(engagementMarcheDto);
		CommonMessagesUtils.showSuccessValidationMessage();
	}

	public void deleteAction() {
		serviceLocator.getEngagementMarcheService().remove(engagementMarcheDto);
		isCrud = false;

		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	public void sendAction() {
		engagementMarcheDto = serviceLocator.getEngagementMarcheService().validerDemandeEngagement(engagementMarcheDto);
		serviceLocator.getEngagementMarcheService().envoyerDemandeEngamgent(engagementMarcheDto);

		isCrud = false;

		CommonMessagesUtils.showSuccessSendToValidationMessage();

	}

	public void saveDetailEngagementAction() {
		if (detaiLEngagementMarcheDto.getId() == null) {
			if (detaiLEngagementMarcheDto.getArticle() != null
					&& detaiLEngagementMarcheDto.getArticle().getId() == null)
				detaiLEngagementMarcheDto.setArticle(null);

			detaiLEngagementMarcheDto.setEngagementMarche(engagementMarcheDto);
			detaiLEngagementMarcheDto = serviceLocator.getDetailEngagementMarcheService().save(
					detaiLEngagementMarcheDto);
			if (detaiLEngagementMarcheDto.getArticle() == null)
				detaiLEngagementMarcheDto.setArticle(new ArticleDto());
			engagementMarcheDto.getDetailEngagementMarches().add(detaiLEngagementMarcheDto);
			CommonMessagesUtils.showSuccessSaveMessage();
		} else {
			if (detaiLEngagementMarcheDto.getArticle() != null)
				detaiLEngagementMarcheDto.setArticle(new ArticleDto());
			serviceLocator.getDetailEngagementMarcheService().save(detaiLEngagementMarcheDto);
			CommonMessagesUtils.showSuccessUpdateMessage();
		}
		RequestContext.getCurrentInstance().execute("PF('gererDemBudgetaireWidget').hide()");
	}

	public void deleteDetailEngagementAction() {
		serviceLocator.getDetailEngagementMarcheService().remove(detaiLEngagementMarcheDto);
		engagementMarcheDto.getDetailEngagementMarches().remove(detaiLEngagementMarcheDto);
		CommonMessagesUtils.showSuccessDeleteMessage();
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

	public EngagementMarcheDto getEngagementMarcheDto() {
		return engagementMarcheDto;
	}

	public EngagementMarcheDto getEngagementMarcheSearchDto() {
		return engagementMarcheSearchDto;
	}

	public List<SituationEntiteOccurrenceDto> getEngagementMarcheHistory() {
		return engagementMarcheHistory;
	}

	public List<SelectItem> getListNaturesEngagement() {
		return listNaturesEngagement;
	}

	public List<SelectItem> getListMarches() {
		return listMarches;
	}

	public DetailEngagementMarcheDto getDetaiLEngagementMarcheDto() {
		return detaiLEngagementMarcheDto;
	}

	public List<SelectItem> getListSections() {
		return listSections;
	}

	public List<SelectItem> getListChapitres() {
		return listChapitres;
	}

	public List<SelectItem> getListArticles() {
		return listArticles;
	}

	public List<SelectItem> getListStructures() {
		return listStructures;
	}

	public DetailsReparatitionBudgetDto getDetailsReparatitionBudgetDto() {
		return detailsReparatitionBudgetDto;
	}
}
