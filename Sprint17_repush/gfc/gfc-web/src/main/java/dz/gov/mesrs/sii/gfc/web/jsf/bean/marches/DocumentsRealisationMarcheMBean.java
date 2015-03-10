package dz.gov.mesrs.sii.gfc.web.jsf.bean.marches;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailRealisationEquipementDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailRealisationPrestationDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DocumentRealisationMarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.EquipementMarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.MarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.PrestationMarcheDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.GFCConstantBean;

/**
 * Gere les documents de realisation
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 15/02/2015 10:06:31
 */
@ManagedBean(name = "documentsRealisationMarcheBean")
@ViewScoped
public class DocumentsRealisationMarcheMBean extends BaseBean {

	public final static String MARCHES_BUNDLE_MSG_NAME = "marchesMsgs";

	private static final long serialVersionUID = 1L;

	private LazyDataModel<DocumentRealisationMarcheDto> model;

	private DocumentRealisationMarcheDto documentRealisationMarcheDto;
	private DocumentRealisationMarcheDto documentRealisationMarcheSearchDto;

	private MarcheDto marcheSearchDto;

	private DetailRealisationPrestationDto detailRealisationPrestationDto;
	private DetailRealisationEquipementDto detailRealisationEquipementDto;

	// UI
	private List<SelectItem> listPrestations;
	private List<SelectItem> listEquipements;
	private List<SelectItem> listProduits;

	private List<SelectItem> listTypesDocumentsRealisation;
	private List<SelectItem> listMarches;

	private boolean isView;
	private boolean isCrud;

	public String keyWords;
	private List<SituationEntiteOccurrenceDto> documentRealisationHistory;

	public DocumentsRealisationMarcheMBean() {
	}

	@PostConstruct
	public void init() {
		initUI();
		loadSearchResults();
	}

	private void initUI() {
		documentRealisationMarcheSearchDto = new DocumentRealisationMarcheDto();

		if (viewId.equals("DocumentsRealisationEdit")) {
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

			listTypesDocumentsRealisation = findNomenclatureList(GFCConstantBean.CODE_TYPE_DOCUMENT_REALISATION_MARCHE);
		}
	}

	private void loadSearchResults() {
		model = new LazyDataModel<DocumentRealisationMarcheDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(DocumentRealisationMarcheDto documentRealisationMarcheDto) {
				return documentRealisationMarcheDto.getId();
			}

			@Override
			public List<DocumentRealisationMarcheDto> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {
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
				if (viewId.equals("DocumentsRealisationEdit")) {
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DOC_REALISATION,
									UtilConstants.SITUATION_CREEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));

					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DOC_REALISATION,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));
				}

				model.setRowCount(serviceLocator.getDocumentRealisationMarcheService().countAllByExample(
						documentRealisationMarcheSearchDto, searchMode));

				return serviceLocator.getDocumentRealisationMarcheService().findAllByExample(
						documentRealisationMarcheSearchDto, searchMode);
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

	public LazyDataModel<DocumentRealisationMarcheDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<DocumentRealisationMarcheDto> model) {
		this.model = model;
	}

	public void onRowSelect(SelectEvent event) {
		documentRealisationMarcheDto = (DocumentRealisationMarcheDto) event.getObject();
		loadInfosMarche();

		// historique des situations
		documentRealisationHistory = serviceLocator.getSituationService().getEntityOccurrenceHistory(
				UtilConstants.ENTITE_GFC_DOC_REALISATION, documentRealisationMarcheDto.getId());

		isCrud = true;
		isView = true;
	}

	public void onRowSelectPrestation(SelectEvent event) {
		detailRealisationPrestationDto = (DetailRealisationPrestationDto) event.getObject();
	}

	public void onRowSelectEquipement(SelectEvent event) {
		detailRealisationEquipementDto = (DetailRealisationEquipementDto) event.getObject();
	}

	public void addAction() {
		documentRealisationMarcheDto = new DocumentRealisationMarcheDto();
		isCrud = true;
	}

	private void loadInfosMarche() {
		StringBuffer label = new StringBuffer();

		MarcheDto marche = documentRealisationMarcheDto.getMarche();
		listPrestations = new ArrayList<SelectItem>();

		List<PrestationMarcheDto> l = marche.getPrestationMarches();
		if (l != null && !l.isEmpty()) {
			for (PrestationMarcheDto prestationMarcheDto : l) {
				label.append(prestationMarcheDto.getDescription()).append(" - ")
						.append(prestationMarcheDto.getType().getLibelleLongFr());
				listPrestations.add(new SelectItem(prestationMarcheDto.getId(), label.toString()));

				label.setLength(0);
			}
		}

		listEquipements = new ArrayList<SelectItem>();
		List<EquipementMarcheDto> e = marche.getEquipementMarches();
		if (e != null && !e.isEmpty()) {
			for (EquipementMarcheDto equipementMarcheDto : e) {
				label.append(equipementMarcheDto.getEquipement().getDesignation()).append(" - ")
						.append(equipementMarcheDto.getEquipement().getCode());
				listEquipements.add(new SelectItem(equipementMarcheDto.getId(), label.toString()));

				label.setLength(0);
			}
		}

		detailRealisationPrestationDto = new DetailRealisationPrestationDto();
		detailRealisationEquipementDto = new DetailRealisationEquipementDto();
	}

	public void saveAction() {
		if (documentRealisationMarcheDto != null) {
			if (documentRealisationMarcheDto.getId() == null) {
				documentRealisationMarcheDto = serviceLocator.getDocumentRealisationMarcheService()
						.enregistrerDocuementRealisationMarche(documentRealisationMarcheDto);

				loadInfosMarche();
				CommonMessagesUtils.showSuccessSaveMessage();
			} else {
				documentRealisationMarcheDto = serviceLocator.getDocumentRealisationMarcheService().save(
						documentRealisationMarcheDto);
				CommonMessagesUtils.showSuccessUpdateMessage();
			}
		}
	}

	public void validateAction() {
		serviceLocator.getDocumentRealisationMarcheService().validerEnregistrementDocuementRealisationMarche(
				documentRealisationMarcheDto);
		CommonMessagesUtils.showSuccessValidationMessage();
		isCrud = false;
	}

	public void deleteAction() {
		if (documentRealisationMarcheDto != null) {
			serviceLocator.getDocumentRealisationMarcheService().supprimerDocuementRealisationMarche(
					documentRealisationMarcheDto);
			CommonMessagesUtils.showSuccessDeleteMessage();
			isCrud = false;
		}
	}

	public void cancelAction() {
		isCrud = false;
		isView = false;
	}

	public void addDetailRealisationPrestationAction() {
		detailRealisationPrestationDto = new DetailRealisationPrestationDto();
	}

	public void addDetailRealisationEquipementAction() {
		detailRealisationEquipementDto = new DetailRealisationEquipementDto();
	}

	// TODO
	public void addDetailRealisationProduitAction() {

	}

	public void savePrestationAction() {
		try {
			if (detailRealisationPrestationDto.getId() == null) {
				detailRealisationPrestationDto.setDocumentRealisationMarche(documentRealisationMarcheDto);
				detailRealisationPrestationDto = serviceLocator.getDetailRealisationPrestationService().save(
						detailRealisationPrestationDto);
				documentRealisationMarcheDto.getDetailRealisationPrestations().add(detailRealisationPrestationDto);
				CommonMessagesUtils.showSuccessSaveMessage();
			} else {
				detailRealisationPrestationDto = serviceLocator.getDetailRealisationPrestationService().save(
						detailRealisationPrestationDto);
				CommonMessagesUtils.showSuccessUpdateMessage();
			}

			RequestContext.getCurrentInstance().execute("PF('gererPrestationWidget').hide()");
		} catch (Exception e) {
			logger.error(e.getMessage());
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
	}

	public void deletePrestationAction() {
		serviceLocator.getDetailRealisationPrestationService().remove(detailRealisationPrestationDto);
		documentRealisationMarcheDto.getDetailRealisationPrestations().remove(detailRealisationPrestationDto);
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	public void saveEquipementAction() {
		try {
			if (detailRealisationEquipementDto.getId() == null) {
				detailRealisationEquipementDto.setDocumentRealisationMarche(documentRealisationMarcheDto);
				detailRealisationEquipementDto = serviceLocator.getDetailRealisationEquipementService().save(
						detailRealisationEquipementDto);
				documentRealisationMarcheDto.getDetailRealisationEquipements().add(detailRealisationEquipementDto);
			} else {
				detailRealisationEquipementDto = serviceLocator.getDetailRealisationEquipementService().save(
						detailRealisationEquipementDto);
				CommonMessagesUtils.showSuccessUpdateMessage();
			}

			RequestContext.getCurrentInstance().execute("PF('gererEquipementWidget').hide()");
		} catch (Exception e) {
			logger.error(e.getMessage());
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
	}

	public void deleteEquipementAction() {
		serviceLocator.getDetailRealisationEquipementService().remove(detailRealisationEquipementDto);
		documentRealisationMarcheDto.getDetailRealisationEquipements().remove(detailRealisationEquipementDto);
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	public boolean isView() {
		return isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public List<SituationEntiteOccurrenceDto> getDocumentRealisationHistory() {
		return documentRealisationHistory;
	}

	public DocumentRealisationMarcheDto getDocumentRealisationMarcheDto() {
		return documentRealisationMarcheDto;
	}

	public List<SelectItem> getListTypesDocumentsRealisation() {
		return listTypesDocumentsRealisation;
	}

	public List<SelectItem> getListMarches() {
		return listMarches;
	}

	public DetailRealisationPrestationDto getDetailRealisationPrestationDto() {
		return detailRealisationPrestationDto;
	}

	public DetailRealisationEquipementDto getDetailRealisationEquipementDto() {
		return detailRealisationEquipementDto;
	}

	public List<SelectItem> getListPrestations() {
		return listPrestations;
	}

	public List<SelectItem> getListEquipements() {
		return listEquipements;
	}

	public List<SelectItem> getListProduits() {
		return listProduits;
	}
}