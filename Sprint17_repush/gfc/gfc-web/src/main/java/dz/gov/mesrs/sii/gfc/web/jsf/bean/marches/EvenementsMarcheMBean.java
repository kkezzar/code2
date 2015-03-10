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
import dz.gov.mesrs.sii.gfc.business.model.dto.EvenementMarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.MarcheDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.GFCConstantBean;

/**
 * Gere les evenements sur les marches
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 15/02/2015 10:06:31
 */
@ManagedBean(name = "evenementsMarcheBean")
@ViewScoped
public class EvenementsMarcheMBean extends BaseBean {

	public final static String MARCHES_BUNDLE_MSG_NAME = "marchesMsgs";

	private static final long serialVersionUID = 1L;

	private LazyDataModel<MarcheDto> modelMarches;
	private LazyDataModel<EvenementMarcheDto> model;
	private EvenementMarcheDto evenementMarcheDto;
	private MarcheDto marcheDto;

	private EvenementMarcheDto evenementMarcheSearchDto;
	private MarcheDto marcheSearchDto;

	// UI
	private boolean isView;
	private boolean isCrud;

	public String keyWords;
	private List<SituationEntiteOccurrenceDto> evenementMarcheHistory;

	private List<SelectItem> listTypesEvenements;

	public EvenementsMarcheMBean() {
	}

	@PostConstruct
	public void init() {
		evenementMarcheSearchDto = new EvenementMarcheDto();
		marcheSearchDto = new MarcheDto();
		marcheSearchDto.setEtablissement(sessionBean.getCurrentEtablissement());
		marcheSearchDto.setStructure(sessionBean.getCurrentRefStructureDto());
		evenementMarcheSearchDto.setMarche(marcheSearchDto);

		evenementMarcheDto = new EvenementMarcheDto();

		initUI();
		loadSearchResults();
	}

	private void initUI() {

		listTypesEvenements = findNomenclatureList(GFCConstantBean.CODE_TYPE_EVENEMENT_MARCHE);
	}

	private void loadSearchResults() {
		if (viewId.equals("EvenementMarcheEdit")) {
			modelMarches = new LazyDataModel<MarcheDto>() {
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

					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_MARCHE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));

					modelMarches.setRowCount(serviceLocator.getMarcheService().countAllByExample(marcheSearchDto,
							searchMode));

					return serviceLocator.getMarcheService().findAllByExample(marcheSearchDto, searchMode);
				}
			};
		} else {

			model = new LazyDataModel<EvenementMarcheDto>() {
				private static final long serialVersionUID = -3025574151722364485L;

				@Override
				public Object getRowKey(EvenementMarcheDto evenementMarcheDto) {
					return evenementMarcheDto.getId();
				}

				@Override
				public List<EvenementMarcheDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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

					model.setRowCount(serviceLocator.getEvenementMarcheService().countAllByExample(
							evenementMarcheSearchDto, searchMode));

					return serviceLocator.getEvenementMarcheService().findAllByExample(evenementMarcheSearchDto,
							searchMode);
				}
			};
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

	public LazyDataModel<MarcheDto> getModelMarches() {
		return modelMarches;
	}

	public void setModelMarches(LazyDataModel<MarcheDto> modelMarches) {
		this.modelMarches = modelMarches;
	}

	public LazyDataModel<EvenementMarcheDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<EvenementMarcheDto> model) {
		this.model = model;
	}

	public void onRowSelect(SelectEvent event) {
		marcheDto = (MarcheDto) event.getObject();
		isCrud = true;
	}

	public void onRowSelectDetailEvenementMarche(SelectEvent event) {
		evenementMarcheDto = (EvenementMarcheDto) event.getObject();
		isView = true;
	}

	public void addAction() {
		evenementMarcheDto = new EvenementMarcheDto();
		evenementMarcheDto.setMarche(marcheDto);
		isCrud = true;
	}

	public void onRowSelectEvenementMarche(SelectEvent event) {
		evenementMarcheDto = (EvenementMarcheDto) event.getObject();
	}

	public void saveAction() {
		if (evenementMarcheDto.getId() == null) {
			evenementMarcheDto = serviceLocator.getEvenementMarcheService().enregistrerEvenementMarche(
					evenementMarcheDto);
			marcheDto.getEvenementMarches().add(evenementMarcheDto);
			CommonMessagesUtils.showSuccessSaveMessage();
		} else {
			serviceLocator.getEvenementMarcheService().save(evenementMarcheDto);
			CommonMessagesUtils.showSuccessUpdateMessage();
		}
		RequestContext.getCurrentInstance().execute("PF('gererDemBudgetaireWidget').hide()");
	}

	public void validateAction() {
		serviceLocator.getEvenementMarcheService().validerEnregistrementEvenementMarche(evenementMarcheDto);
	}

	public void deleteAction() {
		serviceLocator.getEvenementMarcheService().remove(evenementMarcheDto);
		marcheDto.getEvenementMarches().remove(evenementMarcheDto);
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

	public List<SituationEntiteOccurrenceDto> getEvenementMarcheHistory() {
		return evenementMarcheHistory;
	}

	public EvenementMarcheDto getEvenementMarcheDto() {
		return evenementMarcheDto;
	}

	public void setEvenementMarcheDto(EvenementMarcheDto evenementMarcheDto) {
		this.evenementMarcheDto = evenementMarcheDto;
	}

	public List<SelectItem> getListTypesEvenements() {
		return listTypesEvenements;
	}

	public MarcheDto getMarcheDto() {
		return marcheDto;
	}

}