package dz.gov.mesrs.sii.gfc.web.jsf.bean.marches;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

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
import dz.gov.mesrs.sii.gfc.business.model.dto.DossierPaiementDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.GFCConstantBean;

/**
 * Gere les dossiers de paiement des marches
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 15/02/2015 10:06:31
 */
@ManagedBean(name = "dossiersPaiementMarcheBean")
@ViewScoped
public class DossiersPaiementMarcheMBean extends BaseBean {

	public final static String MARCHES_BUNDLE_MSG_NAME = "marchesMsgs";

	private static final long serialVersionUID = 1L;

	private LazyDataModel<DossierPaiementDto> model;

	private DossierPaiementDto dossierPaiementDto;
	private DossierPaiementDto dossierPaiementSearchDto;

	// UI
	private List<SelectItem> listModesPaiement;

	private List<SelectItem> listDocumentsRealisation;

	private boolean isView;
	private boolean isCrud;

	public String keyWords;
	private List<SituationEntiteOccurrenceDto> dossierPaiementHistory;

	public DossiersPaiementMarcheMBean() {
	}

	@PostConstruct
	public void init() {
		initUI();
		loadSearchResults();
	}

	private void initUI() {
		dossierPaiementSearchDto = new DossierPaiementDto();

		if (viewId.equals("DossiersPaiementEdit")) {
			listModesPaiement = findNomenclatureList(GFCConstantBean.CODE_MODES_PAIEMENT);

			listDocumentsRealisation = new ArrayList<SelectItem>();
			List<DocumentRealisationMarcheDto> l = serviceLocator.getDocumentRealisationMarcheService()
					.findDocumentsRealisationForPaiement(sessionBean.getCurrentEtablissement(),
							sessionBean.getCurrentRefStructureDto());
			if (l != null && !l.isEmpty()) {
				StringBuffer label = new StringBuffer();
				for (DocumentRealisationMarcheDto documentRealisationMarcheDto : l) {
					label.append(documentRealisationMarcheDto.getMarche().getIntituleFr()).append(" / Le ")
							.append(documentRealisationMarcheDto.getDateDocument()).append(" : ")
							.append(documentRealisationMarcheDto.getTypeDocument().getLibelleLongFr()).append(" - ");
					listDocumentsRealisation
							.add(new SelectItem(documentRealisationMarcheDto.getId(), label.toString()));
					label.setLength(0);
				}
			}

		}

	}

	private void loadSearchResults() {
		model = new LazyDataModel<DossierPaiementDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(DossierPaiementDto dossierPaiementDto) {
				return dossierPaiementDto.getId();
			}

			@Override
			public List<DossierPaiementDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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
				if (viewId.equals("DossiersPaiementEdit")) {
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DOSSIER_PAIEMENT,
									UtilConstants.SITUATION_CREEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));

					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DOSSIER_PAIEMENT,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));
				}

				model.setRowCount(serviceLocator.getDossierPaiementService().countAllByExample(
						dossierPaiementSearchDto, searchMode));

				return serviceLocator.getDossierPaiementService()
						.findAllByExample(dossierPaiementSearchDto, searchMode);
			}
		};
	}

	public void valueChangeDocumentRealisationOneMenuChange(ValueChangeEvent e) throws Exception {
		Integer id = dossierPaiementDto.getDocumentRealisationMarche().getId();
		DocumentRealisationMarcheDto s = serviceLocator.getDocumentRealisationMarcheService().findById(id);
		dossierPaiementDto.setDocumentRealisationMarche(s);
	}

	public void handleDocumentRealisationOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		if (this.dossierPaiementDto != null) {
			Integer id = dossierPaiementDto.getDocumentRealisationMarche().getId();
			DocumentRealisationMarcheDto s = serviceLocator.getDocumentRealisationMarcheService().findById(id);
			dossierPaiementDto.setDocumentRealisationMarche(s);
		}
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

	public LazyDataModel<DossierPaiementDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<DossierPaiementDto> model) {
		this.model = model;
	}

	public void addAction() {
		dossierPaiementDto = new DossierPaiementDto();

		isCrud = true;
	}

	public void onRowSelect(SelectEvent event) {
		dossierPaiementDto = (DossierPaiementDto) event.getObject();

		// historique des situations
		dossierPaiementHistory = serviceLocator.getSituationService().getEntityOccurrenceHistory(
				UtilConstants.ENTITE_GFC_DOSSIER_PAIEMENT, dossierPaiementDto.getId());

		isCrud = true;
		isView = true;
	}

	public void saveAction() {
		if (dossierPaiementDto != null) {
			if (dossierPaiementDto.getId() == null) {
				dossierPaiementDto = serviceLocator.getDossierPaiementService().enregistrerDossierPaiement(
						dossierPaiementDto);

				CommonMessagesUtils.showSuccessSaveMessage();
			} else {
				dossierPaiementDto = serviceLocator.getDossierPaiementService().save(dossierPaiementDto);
				CommonMessagesUtils.showSuccessUpdateMessage();
			}
		}
	}

	public void sendAction() {
		serviceLocator.getDossierPaiementService().envoyerDossierPaiement(dossierPaiementDto);
		CommonMessagesUtils.showSuccessValidationMessage();
		isCrud = false;
	}

	public void deleteAction() {
		if (dossierPaiementDto != null) {
			serviceLocator.getDossierPaiementService().supprimerDossierPaiement(dossierPaiementDto);
			CommonMessagesUtils.showSuccessDeleteMessage();
			isCrud = false;
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

	public List<SituationEntiteOccurrenceDto> getDossierPaiementHistory() {
		return dossierPaiementHistory;
	}

	public DossierPaiementDto getDossierPaiementDto() {
		return dossierPaiementDto;
	}

	public List<SelectItem> getListModesPaiement() {
		return listModesPaiement;
	}

	public List<SelectItem> getListDocumentsRealisation() {
		return listDocumentsRealisation;
	}
}