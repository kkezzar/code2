package dz.gov.mesrs.sii.gfc.web.jsf.bean.missions;

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

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.MarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.TarifMissionDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.TarifMissionDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.GFCConstantBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * Gere les tarifs des missions
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 15/02/2015 10:06:31
 */
@ManagedBean(name = "tarifsMissionsBean")
@ViewScoped
public class TarifsMissionsMBean extends BaseBean {

	public final static String MARCHES_BUNDLE_MSG_NAME = "missionsMsgs";

	private static final long serialVersionUID = 1L;

	private LazyDataModel<TarifMissionDto> model;
	
	private TarifMissionDto tarifMissions;
	private List<SelectItem> listTypesTarifs;
	private List<SelectItem> listCatgeories;

	// UI
	private boolean isView;
	private boolean isCrud;
	private TarifMissionDto tarifMissionSearch;
	public String keyWords;
	private List<SituationEntiteOccurrenceDto> tarifsMissionHistory;
	private boolean desabledCategorie=false;

	public TarifsMissionsMBean() {
		
	}

	@PostConstruct
	public void init() {
		//tarifMissions=new TarifMissionDto();
		tarifMissionSearch= new TarifMissionDto();
		initUI();
		loadSearchResults();
	}

	private void initUI() {
		try{
			listTypesTarifs = findNomenclatureList(GFCConstantBean.CODE_TYPE_TARIF);
			listCatgeories =  findListeCategorie();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadSearchResults() {

		model = new LazyDataModel<TarifMissionDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(TarifMissionDto tarifMissionDto) {
				return tarifMissionDto.getId();
			}

			@Override
			public List<TarifMissionDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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
				
//					collection.add(serviceLocator
//							.getSituationService()
//							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_TARIF_MISSION,
//									UtilConstants.SITUATION_VALIDEE_CODE).getId());
//					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.NOT_IN));
					

				model.setRowCount(serviceLocator.getTarifMissionService().countAllByExample(tarifMissionSearch,searchMode));
				System.out.println(model.getRowCount());
				// serviceLocator.getTarifMissionService().findAllByExample(tarifMissionSearch,searchMode);
				
				return serviceLocator.getTarifMissionService().findAllByExample(tarifMissionSearch,searchMode);
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

	public LazyDataModel<TarifMissionDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<TarifMissionDto> model) {
		this.model = model;
	}

	public void onRowSelect(SelectEvent event) {
		tarifMissions = (TarifMissionDto) event.getObject();
		
		if(tarifMissions !=null ){
			if(tarifMissions.getFonctionSuperieure()!=null && tarifMissions.getFonctionSuperieure().booleanValue()==true)
				desabledCategorie=true;
			else
				desabledCategorie=false;
		}
		// historique des situations
		tarifsMissionHistory = serviceLocator.getSituationService().getEntityOccurrenceHistory(
				UtilConstants.ENTITE_GFC_TARIF_MISSION, tarifMissions.getId());
		isCrud = true;
		isView = true;
	}
	
	public void doAction() {
		if(tarifMissions!=null){
			if(tarifMissions.getFonctionSuperieure()!=null && tarifMissions.getFonctionSuperieure().booleanValue()==true)
				desabledCategorie=true;
			else
				desabledCategorie=false;
		}
	}
	
	public void addAction() {
		tarifMissions=new TarifMissionDto();
		tarifMissions.setActif(true);
		isCrud=true;
	}

	public void saveAction() {
		if (tarifMissions != null) {
			if(tarifMissions.getCategorieMax()!=null && tarifMissions.getCategorieMax().getId()==null )
				tarifMissions.setCategorieMax(null);
			if(tarifMissions.getCategorieMin()!=null && tarifMissions.getCategorieMin().getId()==null )
				tarifMissions.setCategorieMin(null);
			
			if (tarifMissions.getSituation().getId() == 0) {
				tarifMissions = serviceLocator.getTarifMissionService().enregistrerTarif(tarifMissions);
				CommonMessagesUtils.showSuccessSaveMessage();
			} else {
				tarifMissions = serviceLocator.getTarifMissionService().save(tarifMissions);
				CommonMessagesUtils.showSuccessUpdateMessage();
			}
	}
		isCrud=false;
	}
	
	public void validateAction() {
		if (tarifMissions != null) {
	
		if(tarifMissions.getCategorieMax()!=null && tarifMissions.getCategorieMax().getId()==null )
			tarifMissions.setCategorieMax(null);
		if(tarifMissions.getCategorieMin()!=null && tarifMissions.getCategorieMin().getId()==null )
			tarifMissions.setCategorieMin(null);
		serviceLocator.getTarifMissionService().validerEnregistrementTarif(tarifMissions);
		CommonMessagesUtils.showSuccessValidationMessage();
		isCrud = false;
	}
	}
	
	public void deleteAction() {
		if (tarifMissions != null) {
			serviceLocator.getTarifMissionService().remove(tarifMissions);
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

	public List<SituationEntiteOccurrenceDto> getTarifsMissionHistory() {
		return tarifsMissionHistory;
	}

	/**
	 * @return the tarifMissions
	 */
	public TarifMissionDto getTarifMissions() {
		return tarifMissions;
	}

	/**
	 * @param tarifMissions the tarifMissions to set
	 */
	public void setTarifMissions(TarifMissionDto tarifMissions) {
		this.tarifMissions = tarifMissions;
	}

	/**
	 * @return the listTypesTarifs
	 */
	public List<SelectItem> getListTypesTarifs() {
		return listTypesTarifs;
	}

	/**
	 * @param listTypesTarifs the listTypesTarifs to set
	 */
	public void setListTypesTarifs(List<SelectItem> listTypesTarifs) {
		this.listTypesTarifs = listTypesTarifs;
	}

	/**
	 * @return the listCatgeories
	 */
	public List<SelectItem> getListCatgeories() {
		return listCatgeories;
	}

	/**
	 * @param listCatgeories the listCatgeories to set
	 */
	public void setListCatgeories(List<SelectItem> listCatgeories) {
		this.listCatgeories = listCatgeories;
	}

	/**
	 * @return the tarifMissionSearch
	 */
	public TarifMissionDto getTarifMissionSearch() {
		return tarifMissionSearch;
	}

	/**
	 * @param tarifMissionSearch the tarifMissionSearch to set
	 */
	public void setTarifMissionSearch(TarifMissionDto tarifMissionSearch) {
		this.tarifMissionSearch = tarifMissionSearch;
	}

	/**
	 * @return the desabledCategorie
	 */
	public boolean isDesabledCategorie() {
		return desabledCategorie;
	}

	/**
	 * @param desabledCategorie the desabledCategorie to set
	 */
	public void setDesabledCategorie(boolean desabledCategorie) {
		this.desabledCategorie = desabledCategorie;
	}

	/**
	 * @param tarifsMissionHistory the tarifsMissionHistory to set
	 */
	public void setTarifsMissionHistory(
			List<SituationEntiteOccurrenceDto> tarifsMissionHistory) {
		this.tarifsMissionHistory = tarifsMissionHistory;
	}
	
	
}