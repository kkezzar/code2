package dz.gov.mesrs.sii.gfc.web.jsf.bean.misePlaceBudgetFonctionnement.misePlaceEtablissement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.hibernate.engine.RowSelection;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.exception.ProgresAppException;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.ArticleDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ChapitreDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsFicheBesoinsDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsMouvementBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsReparatitionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.MouvementBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.RepartitionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.SectionDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * Enregistrer les delegations CP
 * 
 * @author Chouikh salem (MESRS CCM)
 * @version 1.0
 * @created 19/01/2015 
 */
@ManagedBean(name = "delegationCPBudgetMBean")
@ViewScoped
public class DelegationCPBudgetMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<RepartitionBudgetDto> listRepartitionNotofie;

	private RepartitionBudgetDto repartitionBudgetDto;
	private RepartitionBudgetDto repartitionBudgetSearchDto;
	private MouvementBudgetDto mouvementBudgetDto;
	private DetailsMouvementBudgetDto  detailsMouvementBudgetDto;
	private SectionDto sectionDto;
	private ChapitreDto chapitre;
	private ArticleDto article;

	private List<SelectItem> listStructures;
	private RefStructureDto structureSearchDto;
	private List<SelectItem> listSections;
	private List<SelectItem> listChapitres;
	private List<SelectItem> listArticles;

	private List<MouvementBudgetDto> listMouvementStructures;
	// Historique des situations
	private List<SituationEntiteOccurrenceDto> mvtsHistory;
	private List<SituationEntiteOccurrenceDto> repartitionsHistory;	
	

	private boolean isView;
	private boolean isCrud;
	private boolean isCrudDetail;
	
	public DelegationCPBudgetMBean() {
		// TODO Auto-generated constructor stub
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

		repartitionBudgetDto = new RepartitionBudgetDto();
		repartitionBudgetSearchDto=new RepartitionBudgetDto();
		structureSearchDto = new RefStructureDto();
		mouvementBudgetDto=new MouvementBudgetDto();
		detailsMouvementBudgetDto=new DetailsMouvementBudgetDto();
		listMouvementStructures=new ArrayList<MouvementBudgetDto>();
		sectionDto =new SectionDto();
		chapitre =new ChapitreDto();
		article = new ArticleDto();
	}

	private void loadSearchResults() {
	
		RefEtablissementDto etablissement = new RefEtablissementDto();
		etablissement.setId(sessionBean.getCompte().getEtabId());
		System.out.println("--id etab--"+sessionBean.getCompte().getEtabId());
	
		repartitionBudgetSearchDto.setEtablissement(etablissement);
	
		listRepartitionNotofie = new LazyDataModel<RepartitionBudgetDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(RepartitionBudgetDto pojetBudgetDto) {
				return pojetBudgetDto.getId();
			}

			@Override
			public List<RepartitionBudgetDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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
						.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DECISION_REPARTITION,
								UtilConstants.SITUATION_SIGNEE).getId());
				//to do situation_signee par situation repartie
				listRepartitionNotofie.setRowCount(serviceLocator.getRepartitionBudgetService().countAllByExample(
						repartitionBudgetSearchDto, searchMode));


				System.out.println("-listRepartitionNotofie-"+listRepartitionNotofie.getRowCount());
				
				return	serviceLocator.getRepartitionBudgetService().findAllByExample(repartitionBudgetSearchDto,
						searchMode);
			}
		};
	}

	public void searchAction() {
		loadSearchResults();
	}

	/**
	 * Evenement de selection d'une decision de notification
	 * 
	 * @author Mounir.MESSAOUDI on : 12 jan. 2015 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		repartitionBudgetDto = (RepartitionBudgetDto) event.getObject();
		repartitionsHistory=serviceLocator.getSituationService().getEntityOccurrenceHistory(
				UtilConstants.ENTITE_GFC_DECISION_REPARTITION, repartitionBudgetDto.getId());
		isCrud = true;
		isView = true;
	}

	
	/**
	 * Enregister les donnees de mouvement
	 * 
	 */
	public void saveAction() {
		try {
			
				
			if(repartitionBudgetDto!=null)
				mouvementBudgetDto.setRepartitionBudget(repartitionBudgetDto);
			mouvementBudgetDto.setRefEtablissement(repartitionBudgetDto.getEtablissement());
			mouvementBudgetDto.setUniteMonitaire(repartitionBudgetDto.getUniteMonetaire());
			
			
			mouvementBudgetDto.setIdSituation(serviceLocator.getSituationService().findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_MOUVEMENT,
					UtilConstants.SITUATION_CREEE_CODE).getId());
			mouvementBudgetDto = serviceLocator.getMouvementBudgetService().save(mouvementBudgetDto);
			isCrudDetail = false;
			CommonMessagesUtils.showSuccessUpdateMessage();
		} catch (Exception e) {
			
			logger.error(e.getMessage());
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
	}
	
	
	/**
	 * signe delegation CP
	 */

	public void saveActionSignature() {
		try {
						
			
			
			mouvementBudgetDto.setIdSituation(serviceLocator.getSituationService().findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_MOUVEMENT,
					UtilConstants.SITUATION_SIGNEE).getId());
			mouvementBudgetDto = serviceLocator.getMouvementBudgetService().save(mouvementBudgetDto);
			isCrudDetail = false;
			CommonMessagesUtils.showSuccessUpdateMessage();
		} catch (Exception e) {
			
			logger.error(e.getMessage());
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * add mouvement
	 */
	public void addAction() {
		
		mouvementBudgetDto = new MouvementBudgetDto();
		mouvementBudgetDto.setRepartitionBudget(repartitionBudgetDto);

		isCrudDetail = true;
	}

	/**
	 * 
	 * @param event
	 */
	
	public void onRowEdit(SelectEvent event) {
		mouvementBudgetDto =(MouvementBudgetDto) event.getObject();
		mvtsHistory = serviceLocator.getSituationService().getEntityOccurrenceHistory(
				UtilConstants.ENTITE_GFC_MOUVEMENT, mouvementBudgetDto.getId());
		isCrudDetail = true;
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Modification annul�e");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRowEditDeleg(SelectEvent event) {
		System.out.println("-row edit---");
		RefEtablissementDto etablissement = new RefEtablissementDto();
		etablissement.setId(sessionBean.getCompte().getEtabId());
		detailsMouvementBudgetDto =(DetailsMouvementBudgetDto) event.getObject();
		chapitre=detailsMouvementBudgetDto.getDetailsRepartitionBudgetSource().getChapitre();
		sectionDto= detailsMouvementBudgetDto.getDetailsRepartitionBudgetSource().getChapitre().getIdSection();
		article=detailsMouvementBudgetDto.getDetailsRepartitionBudgetSource().getArticle();
		listChapitres = findListAffectationsChapitres(etablissement,sectionDto, false);
		listArticles = findListeArticle(chapitre);
		
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

	
	public void handleSectionsSelectOneMenuChange(AjaxBehaviorEvent event) {
		SectionDto section =this.getSectionDto();
		listArticles = new ArrayList<SelectItem>();
		if (section.getId() == null)
			listChapitres = new ArrayList<SelectItem>();
		else {
			RefEtablissementDto etablissement = new RefEtablissementDto();
			etablissement.setId(sessionBean.getCompte().getEtabId());
			// listChapitres = findListeChapitres(section);
			listChapitres = findListAffectationsChapitres(etablissement, section, false);
		}

		if (listChapitres == null)
			listChapitres = new ArrayList<SelectItem>();

		listArticles = new ArrayList<SelectItem>();

	}
	
	public void handleChapitresSelectOneMenuChange(AjaxBehaviorEvent event) {
		ChapitreDto chapitre =this.getChapitre();
		listArticles = findListeArticle(chapitre);
		if (listArticles == null)
			listArticles = new ArrayList<SelectItem>();
	}
	
	

	public void handleSectionsSelectStructure(AjaxBehaviorEvent event) {
		
		
		listMouvementStructures =new ArrayList<MouvementBudgetDto>();
		if(structureSearchDto!=null && structureSearchDto.getId()!=null)
			listMouvementStructures = findListeMouvemennt(structureSearchDto,repartitionBudgetDto);
		
	}
	
	/**
	 * save details mvt
	 * @throws ProgresAppException
	 */
	public void saveDetailRepBudgetAction() throws ProgresAppException {
		if (detailsMouvementBudgetDto != null) {
			if (detailsMouvementBudgetDto.getDetailsRepartitionBudgetSource().getArticle().getId() == null)
				detailsMouvementBudgetDto.getDetailsRepartitionBudgetSource().setArticle(null);

					
				if(detailsMouvementBudgetDto.getDetailsRepartitionBudgetSource()!=null && detailsMouvementBudgetDto.getDetailsRepartitionBudgetSource().getId()==null ){
				
					System.out.println("-a---"+detailsMouvementBudgetDto.getDetailsRepartitionBudgetSource().getChapitre().getIntituleFr());
					DetailsReparatitionBudgetDto example= new DetailsReparatitionBudgetDto();
					example.setChapitre(chapitre);
					example.setArticle(article);
					example.setRepartitionBudget(repartitionBudgetDto);
					
					List<DetailsReparatitionBudgetDto> detailSource= this.serviceLocator.getDetailsReparatitionBudgetService().findAllByExample(example);
					if(detailSource!=null && detailSource.size()>0)
						detailsMouvementBudgetDto.setDetailsRepartitionBudgetSource(detailSource.get(0));
					else{
						example.setMontant(detailsMouvementBudgetDto.getMontant());
						this.serviceLocator.getDetailsReparatitionBudgetService().save(example);
						detailsMouvementBudgetDto.setDetailsRepartitionBudgetSource(example);
					}
				}
				if (detailsMouvementBudgetDto.getId() == null) {
					detailsMouvementBudgetDto = serviceLocator.getDetailsMouvementBudgetService().save(
						detailsMouvementBudgetDto);

			

				CommonMessagesUtils.showSuccessSaveMessage();
			} else {
				
				
				detailsMouvementBudgetDto = serviceLocator.getDetailsMouvementBudgetService().save(
						detailsMouvementBudgetDto);
				CommonMessagesUtils.showSuccessUpdateMessage();
			}

			//detailsMouvementBudgetDto = new DetailsMouvementBudgetDto();

			RequestContext.getCurrentInstance().execute("PF('gererRepCPStructWidget').hide()");
			// isCrudDetail = false;

		} else
			throw new ProgresAppException();
	}

	public void deleteDetailRepBudgetAction() throws ProgresAppException {
		if (detailsMouvementBudgetDto != null) {
			serviceLocator.getDetailsMouvementBudgetService().remove(detailsMouvementBudgetDto);
		
			CommonMessagesUtils.showSuccessDeleteMessage();
			isCrudDetail = false;
		} else
			throw new ProgresAppException();
	}
	
	/**
	 * 
	 */
	public void addBudgetDepenseAction() {
		
		detailsMouvementBudgetDto = new DetailsMouvementBudgetDto();
		detailsMouvementBudgetDto.setMouvementBudget(mouvementBudgetDto);
		listSections= new ArrayList<SelectItem>();
		listSections= findListeSections();
		listChapitres = new ArrayList<SelectItem>();
		listArticles = new ArrayList<SelectItem>();
	}
	
	/**
	 * 
	 */
	public void cancelRepBudgetAction() {

	}
	public LazyDataModel<RepartitionBudgetDto> getListRepartitionNotofie() {
		return listRepartitionNotofie;
	}

	public void setListRepartitionNotofie(LazyDataModel<RepartitionBudgetDto> listRepartitionNotofie) {
		this.listRepartitionNotofie = listRepartitionNotofie;
	}

	public RepartitionBudgetDto getRepartitionBudgetDto() {
		return repartitionBudgetDto;
	}

	public void setRepartitionBudgetDto(RepartitionBudgetDto repartitionBudgetDto) {
		this.repartitionBudgetDto = repartitionBudgetDto;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	/**
	 * @return the listStructures
	 */
	public List<SelectItem> getListStructures() {
		if (listStructures == null) {
			Integer idEtablissement = getSessionBean().getCompte().getEtabId();
			RefEtablissementDto refEtablissementDto = new RefEtablissementDto();
			refEtablissementDto.setId(idEtablissement);
			listStructures = findListeStructure(refEtablissementDto);
		}
		return listStructures;
	}
	
	

	/**
	 * @param listStructures the listStructures to set
	 */
	public void setListStructures(List<SelectItem> listStructures) {
		this.listStructures = listStructures;
	}

	/**
	 * @return the structureSearchDto
	 */
	public RefStructureDto getStructureSearchDto() {
		return structureSearchDto;
	}

	/**
	 * @param structureSearchDto the structureSearchDto to set
	 */
	public void setStructureSearchDto(RefStructureDto structureSearchDto) {
		this.structureSearchDto = structureSearchDto;
	}

	/**
	 * @return the mouvementBudgetDto
	 */
	public MouvementBudgetDto getMouvementBudgetDto() {
		return mouvementBudgetDto;
	}

	/**
	 * @param mouvementBudgetDto the mouvementBudgetDto to set
	 */
	public void setMouvementBudgetDto(MouvementBudgetDto mouvementBudgetDto) {
		this.mouvementBudgetDto = mouvementBudgetDto;
	}

	/**
	 * @return the isCrudDetail
	 */
	public boolean isCrudDetail() {
		return isCrudDetail;
	}

	/**
	 * @param isCrudDetail the isCrudDetail to set
	 */
	public void setCrudDetail(boolean isCrudDetail) {
		this.isCrudDetail = isCrudDetail;
	}

	/**
	 * @return the detailsMouvementBudgetDto
	 */
	public DetailsMouvementBudgetDto getDetailsMouvementBudgetDto() {
		return detailsMouvementBudgetDto;
	}

	/**
	 * @param detailsMouvementBudgetDto the detailsMouvementBudgetDto to set
	 */
	public void setDetailsMouvementBudgetDto(
			DetailsMouvementBudgetDto detailsMouvementBudgetDto) {
		this.detailsMouvementBudgetDto = detailsMouvementBudgetDto;
	}

	/**
	 * @return the listSections
	 */
	public List<SelectItem> getListSections() {
		if (listSections == null) {
			listSections = findListeSections();
		}
		return listSections;
	}

	/**
	 * @param listSections the listSections to set
	 */
	public void setListSections(List<SelectItem> listSections) {
		
		this.listSections = listSections;
	}

	/**
	 * @return the listChapitres
	 */
	public List<SelectItem> getListChapitres() {
		return listChapitres;
	}

	/**
	 * @param listChapitres the listChapitres to set
	 */
	public void setListChapitres(List<SelectItem> listChapitres) {
		this.listChapitres = listChapitres;
	}

	/**
	 * @return the listArticles
	 */
	public List<SelectItem> getListArticles() {
		return listArticles;
	}

	/**
	 * @param listArticles the listArticles to set
	 */
	public void setListArticles(List<SelectItem> listArticles) {
		this.listArticles = listArticles;
	}

	/**
	 * @return the listMouvementStructures
	 */
	public List<MouvementBudgetDto> getListMouvementStructures() {
		return listMouvementStructures;
	}

	/**
	 * @param listMouvementStructures the listMouvementStructures to set
	 */
	public void setListMouvementStructures(
			List<MouvementBudgetDto> listMouvementStructures) {
		this.listMouvementStructures = listMouvementStructures;
	}

	/**
	 * @return the mvtsHistory
	 */
	public List<SituationEntiteOccurrenceDto> getMvtsHistory() {
		return mvtsHistory;
	}

	/**
	 * @param mvtsHistory the mvtsHistory to set
	 */
	public void setMvtsHistory(List<SituationEntiteOccurrenceDto> mvtsHistory) {
		this.mvtsHistory = mvtsHistory;
	}

	/**
	 * @return the repartitionsHistory
	 */
	public List<SituationEntiteOccurrenceDto> getRepartitionsHistory() {
		return repartitionsHistory;
	}

	/**
	 * @param repartitionsHistory the repartitionsHistory to set
	 */
	public void setRepartitionsHistory(
			List<SituationEntiteOccurrenceDto> repartitionsHistory) {
		this.repartitionsHistory = repartitionsHistory;
	}

	/**
	 * @return the sectionDto
	 */
	public SectionDto getSectionDto() {
		return sectionDto;
	}

	/**
	 * @param sectionDto the sectionDto to set
	 */
	public void setSectionDto(SectionDto sectionDto) {
		this.sectionDto = sectionDto;
	}

	/**
	 * @return the chapitre
	 */
	public ChapitreDto getChapitre() {
		return chapitre;
	}

	/**
	 * @param chapitre the chapitre to set
	 */
	public void setChapitre(ChapitreDto chapitre) {
		this.chapitre = chapitre;
	}

	/**
	 * @return the article
	 */
	public ArticleDto getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(ArticleDto article) {
		this.article = article;
	}

	
}