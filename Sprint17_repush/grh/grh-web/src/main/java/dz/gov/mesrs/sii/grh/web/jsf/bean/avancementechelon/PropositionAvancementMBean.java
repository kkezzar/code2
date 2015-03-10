package dz.gov.mesrs.sii.grh.web.jsf.bean.avancementechelon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.EmployeProposeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PropostionAvancementDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

@ManagedBean(name = "propositionAvancementBean")
@ViewScoped
/**
 * 
 * @author SLIMANE Ramdane  on : 04 dec. 2014  14:51:27
 */
public class PropositionAvancementMBean extends BaseBean{
	/**
	 * serialVersionUID 
	 * @author SLIMANE Ramdane   on : 4 déc. 2014  12:31:19
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private LazyDataModel<PropostionAvancementDto> propostionAvancementModel ;
	private PropostionAvancementDto propostionAvancementDto ;
	private PropostionAvancementDto propostionAvancementSearchDto ;
	private List<SelectItem>  moisLIST = null,listeStructure;
	private List<PropostionAvancementDto> listPropostionAvancementDto;
	private List<Integer>listNomenclatureActifDetachementID;
	private String valeurMinEchelom,valeurMoyEchelom,valeurMaxEchelom;
	private List<EmployeProposeDto> listDetailPropostionAvancementDto;
	private RefEtablissementDto refEtablissementDto;
	private SituationEntiteDto situationEntiteDto;
	private NomenclatureDto nomenclatureByTypeAvancementDto;
	private List<NomenclatureDto> listnomenclatureByMoisDto;
	private SelectItem moisSelected;
	private Integer selectedMoisId; 
	public PropositionAvancementMBean() {
		// TODO Auto-generated constructor stub
	}

		
	@PostConstruct
	public void init()  {		
		propostionAvancementDto = null;
		searchKeyWords = "";
		refEtablissementDto=sessionBean.getRefEtablissementDto();
		listemployeActifDetachement() ;
		propostionAvancementSearchDto = new PropostionAvancementDto();
		propostionAvancementSearchDto.setRefEtablissementDto(refEtablissementDto);
		listPropostionAvancementDto = new ArrayList<PropostionAvancementDto>();	
		situationEntiteDto = this.serviceLocator.getSituationService().findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_PROPOSITION_AVANCEMENT_CODE,UtilConstants.SITUATION_CREEE_CODE);
		nomenclatureByTypeAvancementDto = this.serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_TYPE_AVANCEMENT_VALUE,UtilConstant.NC_GRH_AVANCEMENT_ECHELON_CODE);
		listNomenclatureActifDetachementID= listNomenclatureActifDetachementID();
		listnomenclatureByMoisDto= this.serviceLocator.getNomenclatureService().findByDomaine(UtilConstant.NC_GRH_MOIS_VALUE);
		onSearchPropositionAvancement();
		initSelectItemLists();	
	}
	public List<Integer> listNomenclatureActifDetachementID() {
		 List<Integer> listNomenclatureByPositionAgentDto = new ArrayList<Integer>();
		NomenclatureDto  nomenclatureByPositionAgentDto = this.serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,UtilConstant.NC_GRH_POSITION_EMPLOYE_ACTIVE_CODE);
	    if(nomenclatureByPositionAgentDto != null)  listNomenclatureByPositionAgentDto.add(nomenclatureByPositionAgentDto.getId()) ;      
		nomenclatureByPositionAgentDto = this.serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,UtilConstant.NC_GRH_POSITION_EMPLOYE_DETACHEMENT_CODE);
		if(nomenclatureByPositionAgentDto != null)  listNomenclatureByPositionAgentDto.add(nomenclatureByPositionAgentDto.getId()) ;    
		return listNomenclatureByPositionAgentDto;
	}
	
	public void carriereedit() {
		
		
		
	}
	
	public void employeproposeredit() {
		@SuppressWarnings("unused")
		List<EmployeProposeDto> employeProposeDto = this.serviceLocator.getEmployeProposeService().findAll();
				
	}
		

	private void initDetail() {
		propostionAvancementDto = null;
			}

	private void initSelectItemLists() {
	moisLIST = findListeMois();
	valeurMinEchelom=serviceLocator.getRefParametreEtabService().findValueOfKey(propostionAvancementSearchDto.getRefEtablissementDto().getId(), "GRH_ECHELON_DUREE_MINIMALE");
	valeurMoyEchelom=serviceLocator.getRefParametreEtabService().findValueOfKey(propostionAvancementSearchDto.getRefEtablissementDto().getId(), "GRH_ECHELON_DUREE_MOYENNE");
	valeurMaxEchelom=serviceLocator.getRefParametreEtabService().findValueOfKey(propostionAvancementSearchDto.getRefEtablissementDto().getId(), "GRH_ECHELON_DUREE_MAXIMALE");	
	}

	public void onSearchPropositionAvancement() {
		try {initDetail();
			loadPropostionAvancementModel();
			
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	
	@SuppressWarnings("deprecation")
	public void onNew() {
		propostionAvancementDto = new PropostionAvancementDto();
		propostionAvancementDto.setRefEtablissementDto(sessionBean.getRefEtablissementDto());		
		propostionAvancementDto.setAnnee((new Date()).getYear()+1900 ); 		
		propostionAvancementDto.setMoisDto(rechercheValeurMois((new Date()).getMonth())); 
		listDetailPropostionAvancementDto = new ArrayList<EmployeProposeDto>();		

	}
	
	
	public List<SelectItem> findListeMois() {
		List<SelectItem> result = new ArrayList<>();
		if (listnomenclatureByMoisDto != null)if (!listnomenclatureByMoisDto.isEmpty()) {
		for(NomenclatureDto dto:listnomenclatureByMoisDto){
			result.add(new SelectItem(dto.getId(), dto.getLibelleCourtFr()));
			
		}}	
		return result;

	}
	
	
	
	private NomenclatureDto rechercheValeurMois(Integer mois){		
		NomenclatureDto nomenclatureMois = new NomenclatureDto();
		for(NomenclatureDto dto:listnomenclatureByMoisDto){
		if(Integer.valueOf(dto.getCode())==mois){
			nomenclatureMois=dto;			
		}			
		}	
		return nomenclatureMois;
	}

	public void onRowSelect(SelectEvent event) {
		try {
			propostionAvancementDto = (PropostionAvancementDto) event.getObject();
			listDetailPropostionAvancementDto = propostionAvancementDto.getEmployeProposesDto();
			selectedMoisId=propostionAvancementDto.getMoisDto().getId();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public void onMoisSelected() {
		try {			
			if (listnomenclatureByMoisDto != null)if (!listnomenclatureByMoisDto.isEmpty()) {
				for(NomenclatureDto dto:listnomenclatureByMoisDto){
					if(dto.getId()==selectedMoisId)propostionAvancementDto.setMoisDto(dto);				
				}}		
			
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	
	private void loadPropostionAvancementModel() {
		propostionAvancementModel = new LazyDataModel<PropostionAvancementDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(PropostionAvancementDto propostionAvancementDto) {
				if(propostionAvancementDto!= null)
				return propostionAvancementDto.getId();
				else return null;
			}

			@Override
			public List<PropostionAvancementDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {				
				SearchMode searchMode = new SearchMode(pageSize, first);
				if (!StringUtils.isEmpty(sortField)) {
					SearchMode.SortOrder order = null;
					if (sortOrder != null) {
						if (sortOrder.equals(SortOrder.DESCENDING)) {
							order = SearchMode.SortOrder.DESC;
						} else {
							order = SearchMode.SortOrder.ASC;
						}
					}
					SearchMode.SortField sort = new SearchMode.SortField(sortField,order);
					searchMode.addSortField(sort);
				}
				
				
				@SuppressWarnings("unused")
				Collection<Integer> collection = new ArrayList<Integer>();
				searchMode.addFilter(new Filter("nomenclatureByTypeAvancement.id", nomenclatureByTypeAvancementDto.getId(),	FilterMode.EQUALS));
				searchMode.addFilter(new Filter("situation.id", situationEntiteDto.getId(),	FilterMode.EQUALS));
				searchMode.addFilter(new Filter("refEtablissement.id", refEtablissementDto.getId(),FilterMode.EQUALS));
				propostionAvancementModel.setRowCount(serviceLocator.getPropostionAvancementService().countByKeyWord(searchKeyWords));
				List<PropostionAvancementDto> listpropavancement = serviceLocator.getPropostionAvancementService().findAllByKeyWord(searchKeyWords, searchMode);
				if (listpropavancement== null) listpropavancement = new ArrayList<PropostionAvancementDto>();
				return listpropavancement;
			}
		};

	}
@Transient
	public void onSave() {
		if (validateForm()) {
			//beforeSave();
		List<EmployeProposeDto> employeProposeDtos = new ArrayList<EmployeProposeDto>();
		propostionAvancementDto.setSituationDto(situationEntiteDto);
		propostionAvancementDto.setNomenclatureByTypeAvancementDto(nomenclatureByTypeAvancementDto);
		propostionAvancementDto = this.serviceLocator.getPropostionAvancementService().save(propostionAvancementDto);
		SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
		situationEntiteOccurrenceDto.setDateSituation(new Date());
		situationEntiteOccurrenceDto.setIdOccurrence(propostionAvancementDto.getId().intValue());
		situationEntiteOccurrenceDto.setIdSituation(situationEntiteDto.getId());
		this.serviceLocator.getSituationService().saveSituationOccurrence(situationEntiteOccurrenceDto);
		if(listDetailPropostionAvancementDto != null) 
				{for(EmployeProposeDto employeProposeDto:listDetailPropostionAvancementDto) {
					employeProposeDto.setPropostionAvancementDto(propostionAvancementDto);					
					employeProposeDto=this.serviceLocator.getEmployeProposeService().save(employeProposeDto);	
					employeProposeDtos.add(employeProposeDto);
				}
				}
			
			propostionAvancementDto.setEmployeProposesDto(employeProposeDtos);
			propostionAvancementDto = this.serviceLocator.getPropostionAvancementService().save(propostionAvancementDto);			
			loadPropostionAvancementModel();
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}

	public void onRemove() {
		if(this.serviceLocator.getPropostionAvancementService().findById(propostionAvancementDto.getId())  != null){
		this.serviceLocator.getPropostionAvancementService().remove(propostionAvancementDto);
		loadPropostionAvancementModel();
		initDetail();
		CommonMessagesUtils.showSuccessDeleteMessage();
	}
	}
	private boolean validateForm() {
		boolean result = true;
		List<String> maxDatepropositionAvancement=this.serviceLocator.getPropostionAvancementService().maxDatepropositionAvancement( situationEntiteDto.getId(),refEtablissementDto.getId(), nomenclatureByTypeAvancementDto.getId());
		Integer anneeMax = Integer.valueOf(maxDatepropositionAvancement.get(1));
		Integer moisMax = Integer.valueOf(maxDatepropositionAvancement.get(2));
		@SuppressWarnings("unused")
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		
		
		if (propostionAvancementDto.getAnnee() != null){
			
		if(      (anneeMax >propostionAvancementDto.getAnnee())  || ((anneeMax ==propostionAvancementDto.getAnnee() )
				&& (moisMax>Integer.valueOf(propostionAvancementDto.getMoisDto().getCode() )))) 
			{GRHMessagesUtils.showErrorMessage("date_depot_recrutement_invalide");
			result = false;
			}		
		}
		
		return result;

	}

	public void onCreateListEmployeProposable() {		
		if((propostionAvancementDto.getAnnee() != null)&&(selectedMoisId!= null)){		
			
			if (listnomenclatureByMoisDto != null)if (!listnomenclatureByMoisDto.isEmpty()) {
				for(NomenclatureDto dto:listnomenclatureByMoisDto){
					if(dto.getId().intValue()==selectedMoisId.intValue())propostionAvancementDto.setMoisDto(dto);				
				}}	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		
		listDetailPropostionAvancementDto=new ArrayList<EmployeProposeDto>();	
		try {
			if(valeurMinEchelom != null)
			listDetailPropostionAvancementDto=serviceLocator.getCarriereService().agentProposableEchelon(Integer.valueOf(propostionAvancementDto.getMoisDto().getCode()),propostionAvancementDto.getAnnee(),Integer.valueOf(valeurMinEchelom),
					                                                             formatter.parse("2017-01-01"), propostionAvancementSearchDto,refEtablissementDto.getId(),listNomenclatureActifDetachementID);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		}
	}

		
	public void listemployeActifDetachement() {
		List<NomenclatureDto> listNomenclatureByPositionAgentDto = new ArrayList<NomenclatureDto>();
		NomenclatureDto  nomenclatureByPositionAgentDto = this.serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,UtilConstant.NC_GRH_POSITION_EMPLOYE_ACTIVE_CODE);
		if(nomenclatureByPositionAgentDto != null)  listNomenclatureByPositionAgentDto.add(nomenclatureByPositionAgentDto) ;      
		nomenclatureByPositionAgentDto = this.serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,UtilConstant.NC_GRH_POSITION_EMPLOYE_DETACHEMENT_CODE);
		if(nomenclatureByPositionAgentDto != null)  listNomenclatureByPositionAgentDto.add(nomenclatureByPositionAgentDto) ; 
		

	}
	
	
	
	
	public LazyDataModel<PropostionAvancementDto> getPropostionAvancementModel() {
		if(propostionAvancementModel== null) propostionAvancementModel = new LazyDataModel<PropostionAvancementDto>() {
	private static final long serialVersionUID = 1L;
		};
		return propostionAvancementModel;
	}

		
	public void setPropostionAvancementModel(
			LazyDataModel<PropostionAvancementDto> propostionAvancementModel) {
		this.propostionAvancementModel = propostionAvancementModel;
	}

	public PropostionAvancementDto getPropostionAvancementDto() {
		return propostionAvancementDto;
	}

	
	public void setPropostionAvancementDto(
			PropostionAvancementDto propostionAvancementDto) {
		this.propostionAvancementDto = propostionAvancementDto;
	}

	
	public PropostionAvancementDto getPropostionAvancementSearchDto() {
		return propostionAvancementSearchDto;
	}

	
	public void setPropostionAvancementSearchDto(
			PropostionAvancementDto propostionAvancementSearchDto) {
		this.propostionAvancementSearchDto = propostionAvancementSearchDto;
	}

	
	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	
	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	
	public List<SelectItem> getListeStructure() {
		return listeStructure;
	}

	
	public void setListeStructure(List<SelectItem> listeStructure) {
		this.listeStructure = listeStructure;
	}

	
	public List<PropostionAvancementDto> getListPropostionAvancementDto() {
		return listPropostionAvancementDto;
	}

	public void setListPropostionAvancementDto(
			List<PropostionAvancementDto> listPropostionAvancementDto) {
		this.listPropostionAvancementDto = listPropostionAvancementDto;
	}

	
	
	

	public List<SelectItem> getMoisLIST() {
		return moisLIST;
	}


	public void setMoisLIST(List<SelectItem> moisLIST) {
		this.moisLIST = moisLIST;
	}


	public String getValeurMinEchelom() {
		return valeurMinEchelom;
	}

	
	public void setValeurMinEchelom(String valeurMinEchelom) {
		this.valeurMinEchelom = valeurMinEchelom;
	}

	
	public String getValeurMoyEchelom() {
		return valeurMoyEchelom;
	}

	
	public void setValeurMoyEchelom(String valeurMoyEchelom) {
		this.valeurMoyEchelom = valeurMoyEchelom;
	}

	
	public String getValeurMaxEchelom() {
		return valeurMaxEchelom;
	}

	
	public void setValeurMaxEchelom(String valeurMaxEchelom) {
		this.valeurMaxEchelom = valeurMaxEchelom;
	}

	
	public List<EmployeProposeDto> getListDetailPropostionAvancementDto() {
		return listDetailPropostionAvancementDto;
	}

	
	public void setListDetailPropostionAvancementDto(
			List<EmployeProposeDto> listDetailPropostionAvancementDto) {
		this.listDetailPropostionAvancementDto = listDetailPropostionAvancementDto;
	}


	public List<NomenclatureDto> getListnomenclatureByMoisDto() {
		return listnomenclatureByMoisDto;
	}


	public void setListnomenclatureByMoisDto(
			List<NomenclatureDto> listnomenclatureByMoisDto) {
		this.listnomenclatureByMoisDto = listnomenclatureByMoisDto;
	}


	public SelectItem getMoisSelected() {
		return moisSelected;
	}


	public void setMoisSelected(SelectItem moisSelected) {
		this.moisSelected = moisSelected;
	}


	public Integer getSelectedMoisId() {
		return selectedMoisId;
	}


	public void setSelectedMoisId(Integer selectedMoisId) {
		this.selectedMoisId = selectedMoisId;
	}

	
	

}
