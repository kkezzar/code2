package dz.gov.mesrs.sii.gfc.web.jsf.bean.marches;

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

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.DocumentRealisationMarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.EngagementMarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.EquipementMarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.MarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.PrestationMarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ProduitMarcheDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.GFCConstantBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEquipementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

/**
 * Gere les marches
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 15/02/2015 10:06:31
 */
@ManagedBean(name = "marchesBean")
@ViewScoped
public class MarchesMBean extends BaseBean {

	public final static String MARCHES_BUNDLE_MSG_NAME = "marchesMsgs";

	private static final long serialVersionUID = 1L;

	private LazyDataModel<MarcheDto> model;
	private MarcheDto marcheDto;
	private PrestationMarcheDto prestationMarcheDto;
	private ProduitMarcheDto produitMarcheDto;
	private EquipementMarcheDto equipementMarcheDto;
	private EngagementMarcheDto engagementMarcheDto;
	private DocumentRealisationMarcheDto documentRealisationMarcheDto;

	private MarcheDto marcheSearchDto;

	// UI
	private boolean isView;
	private boolean isCrud;

	private boolean isContratRequired;

	//
	private boolean isViewDetailEvenement;
	private boolean isViewDetailEngagement;
	private boolean isViewDetailDocumentRealisation;

	public String keyWords;
	private List<SituationEntiteOccurrenceDto> marcheHistory;
	private List<SituationEntiteOccurrenceDto> engagementMarcheHistory;
	private List<SituationEntiteOccurrenceDto> documentRealisationMarcheHistory;

	private List<SelectItem> listModesPassation;
	private List<SelectItem> listTypesMarche;
	private List<SelectItem> listTypesCloture;

	private List<SelectItem> listEquipements;
	private List<SelectItem> listTypesPrestations;

	// private List<RefContratDto> listContrats;

	private List<SelectItem> listContrats;
	private List<SelectItem> listContacts;

	public MarchesMBean() {
	}

	@PostConstruct
	public void init() {
		marcheSearchDto = new MarcheDto();
		prestationMarcheDto = new PrestationMarcheDto();
		equipementMarcheDto = new EquipementMarcheDto();
		produitMarcheDto = new ProduitMarcheDto();

		initUI();
		loadSearchResults();

	}

	private void initUI() {
		// TODO optimisation ?
		try {
			listModesPassation = findNomenclatureList(GFCConstantBean.CODE_MODE_PASSATION);
			listTypesMarche = findNomenclatureList(GFCConstantBean.CODE_TYPE_MARCHE);
			listTypesCloture = findNomenclatureList(GFCConstantBean.CODE_TYPE_CLOTURE_MARCHE);
			listTypesPrestations = findNomenclatureList(GFCConstantBean.CODE_TYPE_PRESTATION);

			listContrats = new ArrayList<SelectItem>();
			List<RefContratDto> resultsContrats = serviceLocator.getContratService().findGeneric("%", false);
			if (resultsContrats != null && !resultsContrats.isEmpty()) {
				for (RefContratDto refContratDto : resultsContrats) {
					listContrats.add(new SelectItem(refContratDto.getId(), refContratDto.getIdentifiant() + " - "
							+ refContratDto.getObjetContratFr()));
				}
			}

			listEquipements = new ArrayList<SelectItem>();
			List<RefEquipementDto> results = serviceLocator.getEquipementService().findAll();
			if (results != null && !results.isEmpty()) {
				String s;
				for (RefEquipementDto refEquipementDto : results) {
					s = new StringBuffer().append(refEquipementDto.getIdentifiant()).append(" - ")
							.append(refEquipementDto.getDesignation()).toString();
					listEquipements.add(new SelectItem(refEquipementDto.getId(), s));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadSearchResults() {

		model = new LazyDataModel<MarcheDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(MarcheDto marcheDto) {
				return marcheDto.getId();
			}

			@Override
			public List<MarcheDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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
				case "MarcheEdit":
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_MARCHE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.NOT_IN));
					break;
				case "MarcheCloture":
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_MARCHE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));
					break;
				case "Marche":
					break;

				default:
					break;
				}

				model.setRowCount(serviceLocator.getMarcheService().countAllByExample(marcheSearchDto, searchMode));
				System.out.println(model.getRowCount());
				return serviceLocator.getMarcheService().findAllByExample(marcheSearchDto, searchMode);
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

	public LazyDataModel<MarcheDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<MarcheDto> model) {
		this.model = model;
	}

	public void onRowSelect(SelectEvent event) {
		marcheDto = (MarcheDto) event.getObject();
		if (viewId.equals("MarcheCloture"))
			marcheDto.setTypeCloture(new NomenclatureDto());

		// historique des situations
		marcheHistory = serviceLocator.getSituationService().getEntityOccurrenceHistory(
				UtilConstants.ENTITE_GFC_MARCHE, marcheDto.getId());

		isCrud = true;
		isView = true;
	}

	public void onRowSelectDemandeEngamgent(SelectEvent event) {
		engagementMarcheDto = (EngagementMarcheDto) event.getObject();
		// historique des situations
		engagementMarcheHistory = serviceLocator.getSituationService().getEntityOccurrenceHistory(
				UtilConstants.ENTITE_GFC_ENGAGEMENT_MARCHE, engagementMarcheDto.getId());
		isViewDetailEngagement = true;
	}

	public void onRowSelectDocumentRealisation(SelectEvent event) {
		documentRealisationMarcheDto = (DocumentRealisationMarcheDto) event.getObject();
		// historique des situations
		documentRealisationMarcheHistory = serviceLocator.getSituationService().getEntityOccurrenceHistory(
				UtilConstants.ENTITE_GFC_DOC_REALISATION, documentRealisationMarcheDto.getId());
		isViewDetailDocumentRealisation = true;
	}

	public void onIndividuSelect(SelectEvent event) {
		RefIndividuDto refIndividuDto = (RefIndividuDto) event.getObject();
		marcheDto.setContact(refIndividuDto);
	}

	public void onRowSelectPrestation(SelectEvent event) {
		prestationMarcheDto = (PrestationMarcheDto) event.getObject();
	}

	public void onRowSelectEquipement(SelectEvent event) {
		equipementMarcheDto = (EquipementMarcheDto) event.getObject();
	}

	public void onRowSelectProduit(SelectEvent event) {
		produitMarcheDto = (ProduitMarcheDto) event.getObject();
	}

	public void handleTypeMarcheOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		if (this.marcheDto != null) {
			Integer id = marcheDto.getType().getId();
			NomenclatureDto s = serviceLocator.getNomenclatureService().findById(id);
			marcheDto.setType(s);
			if (s.getCode().equals(GFCConstantBean.CODE_TYPE_MARCHE_SOUS_CONTRAT))
				isContratRequired = true;
			else
				isContratRequired = false;
		}
	}

	public void handleTypePrestationOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		if (this.prestationMarcheDto != null) {
			Integer id = prestationMarcheDto.getType().getId();
			NomenclatureDto s = serviceLocator.getNomenclatureService().findById(id);
			prestationMarcheDto.setType(s);
		}
	}

	public void handleEquipementOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		if (this.equipementMarcheDto != null) {
			Integer id = equipementMarcheDto.getEquipement().getId();
			RefEquipementDto s = serviceLocator.getEquipementService().findById(id.toString());
			// equipementMarcheDto.setEquipement(s);
		}
	}

	public void addAction() {
		marcheDto = new MarcheDto();
		marcheDto.setEtablissement(sessionBean.getCurrentEtablissement());
		marcheDto.setStructure(sessionBean.getCurrentRefStructureDto());
		isCrud = true;
	}

	public void saveAction() {
		if (marcheDto != null) {
			if (marcheDto.getSituation().getId() == 0) {
				marcheDto = serviceLocator.getMarcheService().enregistrerMarche(marcheDto);
				CommonMessagesUtils.showSuccessSaveMessage();
			} else {
				marcheDto = serviceLocator.getMarcheService().save(marcheDto);
				CommonMessagesUtils.showSuccessUpdateMessage();
			}
		}
	}

	public void validateAction() {
		serviceLocator.getMarcheService().validerEnregistrementMarche(marcheDto);
		CommonMessagesUtils.showSuccessValidationMessage();
		isCrud = false;
	}

	public void deleteAction() {
		if (marcheDto != null) {
			serviceLocator.getMarcheService().remove(marcheDto);
			CommonMessagesUtils.showSuccessDeleteMessage();
			isCrud = false;
		}
	}

	public void closingAction() {
		serviceLocator.getMarcheService().cloturerMarche(marcheDto);
		CommonMessagesUtils.showSuccessClosingMessage();
		isCrud = false;
	}

	public void cancelAction() {
		isCrud = false;
		isView = false;
	}

	public boolean isView() {
		return isView;
	}

	public void addPrestationAction() {
		prestationMarcheDto = new PrestationMarcheDto();
	}

	public void savePrestationAction() {
		try {
			if (prestationMarcheDto.getId() == null) {
				prestationMarcheDto.setMarche(marcheDto);
				prestationMarcheDto.setMontantTtc(serviceLocator.getPrestationMarcheService().calculerMontantTTC(
						prestationMarcheDto));
				prestationMarcheDto = serviceLocator.getPrestationMarcheService().save(prestationMarcheDto);
				marcheDto.getPrestationMarches().add(prestationMarcheDto);

				CommonMessagesUtils.showSuccessSaveMessage();
			} else {
				prestationMarcheDto.setMontantTtc(serviceLocator.getPrestationMarcheService().calculerMontantTTC(
						prestationMarcheDto));
				serviceLocator.getPrestationMarcheService().save(prestationMarcheDto);
				CommonMessagesUtils.showSuccessUpdateMessage();
			}

			RequestContext.getCurrentInstance().execute("PF('gererPrestationWidget').hide()");

		} catch (Exception e) {
			logger.error(e.getMessage());
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
	}

	public void deletePrestationAction() {
		serviceLocator.getPrestationMarcheService().remove(prestationMarcheDto);
		marcheDto.getPrestationMarches().remove(prestationMarcheDto);
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	public void addEquipementAction() {
		equipementMarcheDto = new EquipementMarcheDto();

	}

	public void saveEquipementAction() {
		try {
			if (equipementMarcheDto.getId() == null) {
				equipementMarcheDto.setMarche(marcheDto);
				equipementMarcheDto.setMontantTtc(serviceLocator.getEquipementMarcheService().calculerMontantTTC(
						equipementMarcheDto));
				equipementMarcheDto = serviceLocator.getEquipementMarcheService().save(equipementMarcheDto);

				marcheDto.getEquipementMarches().add(equipementMarcheDto);

				CommonMessagesUtils.showSuccessSaveMessage();
			} else {

				equipementMarcheDto.setMontantTtc(serviceLocator.getEquipementMarcheService().calculerMontantTTC(
						equipementMarcheDto));
				serviceLocator.getEquipementMarcheService().save(equipementMarcheDto);
				CommonMessagesUtils.showSuccessUpdateMessage();
			}

			RequestContext.getCurrentInstance().execute("PF('gererEquipementWidget').hide()");
		} catch (Exception e) {
			logger.error(e.getMessage());
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
	}

	public void deleteEquipementAction() {
		serviceLocator.getEquipementMarcheService().remove(equipementMarcheDto);
		marcheDto.getEquipementMarches().remove(equipementMarcheDto);
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	public void addProduitAction() {
		produitMarcheDto = new ProduitMarcheDto();
	}

	public void saveProduitAction() {

	}

	public void deleteProduitAction() {

	}

	public boolean isViewDetailEvenement() {
		return isViewDetailEvenement;
	}

	public boolean isViewDetailEngagement() {
		return isViewDetailEngagement;
	}

	public boolean isViewDetailDocumentRealisation() {
		return isViewDetailDocumentRealisation;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public List<SituationEntiteOccurrenceDto> getMarcheHistory() {
		return marcheHistory;
	}

	public MarcheDto getMarcheDto() {
		return marcheDto;
	}

	public List<SelectItem> getListModesPassation() {
		return listModesPassation;
	}

	public List<SelectItem> getListTypesMarche() {
		return listTypesMarche;
	}

	public List<SelectItem> getListTypesCloture() {
		return listTypesCloture;
	}

	public List<SelectItem> getListEquipements() {
		return listEquipements;
	}

	public List<SelectItem> getListTypesPrestations() {
		return listTypesPrestations;
	}

	public PrestationMarcheDto getPrestationMarcheDto() {
		return prestationMarcheDto;
	}

	public ProduitMarcheDto getProduitMarcheDto() {
		return produitMarcheDto;
	}

	public MarcheDto getMarcheSearchDto() {
		return marcheSearchDto;
	}

	public EquipementMarcheDto getEquipementMarcheDto() {
		return equipementMarcheDto;
	}

	public List<SelectItem> getListContacts() {
		return listContacts;
	}

	public List<SelectItem> getListContrats() {
		return listContrats;
	}

	public EngagementMarcheDto getEngagementMarcheDto() {
		return engagementMarcheDto;
	}

	public DocumentRealisationMarcheDto getDocumentRealisationMarcheDto() {
		return documentRealisationMarcheDto;
	}

	public List<SituationEntiteOccurrenceDto> getEngagementMarcheHistory() {
		return engagementMarcheHistory;
	}

	public List<SituationEntiteOccurrenceDto> getDocumentRealisationMarcheHistory() {
		return documentRealisationMarcheHistory;
	}

	public boolean isContratRequired() {
		return isContratRequired;
	}
}