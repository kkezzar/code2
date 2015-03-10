package dz.gov.mesrs.sii.grh.web.jsf.bean.avancementpromotion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
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
import dz.gov.mesrs.sii.grh.business.model.dto.CarriereDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CorpsDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.EmployeProposeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GradeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GrilleIndiciaireDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PropostionAvancementDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

@ManagedBean(name = "avancementPromotionListAptitude")
@ViewScoped
/**
 * 
 * @author SLIMANE Ramdane  on : 04 dec. 2014  14:51:27
 */
public class AvancementPromotionListAptitude extends BaseBean{
	/**
	 * serialVersionUID 
	 * @author SLIMANE Ramdane   on : 4 déc. 2014  12:31:19
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private LazyDataModel<PropostionAvancementDto> propostionAvancementModel ;
	private PropostionAvancementDto propostionAvancementDto ;
	private PropostionAvancementDto propostionAvancementSearchDto ;
	private List<SelectItem> listeStructure;
	private List<PropostionAvancementDto> listPropostionAvancementDto;
	private List<SelectItem> moisLIST = null;	
	private List<EmployeProposeDto> listDetailPropostionAvancementDto;
	private RefEtablissementDto refEtablissementDto;
	private SituationEntiteDto situationEntiteDto;
	private NomenclatureDto nomenclatureByTypeAvancementDto,nomenclatureByTypePromotion;
	private EmployeProposeDto employeProposeDto;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private DossierEmployeDto employeSearchDto;
	private DossierEmployeDto dossierEmployeDto;
	private Integer selectedCorpsId,selectedGradeId,employerProposerDtoSelectedIndex;	
	private List<SelectItem> listeCorps;
	private List<SelectItem> listeGrade;
	private Long indiceNouveauGrade;
	private Date datepropositionPromotion;
	private String dureeProposablePromotion;
	private Date periodeAutorise;
	private List<Integer> listNomenclatureActifDetachementID;
	private Integer selectedMoisId;
	private List<NomenclatureDto> listnomenclatureByMoisDto;
	public AvancementPromotionListAptitude() {
		// TODO Auto-generated constructor stub
	}

	///********************************maj
	
	@PostConstruct
	public void init()  {		
		employeSearchDto = new DossierEmployeDto();
		propostionAvancementDto = null;
		searchKeyWords = "";
		refEtablissementDto=sessionBean.getRefEtablissementDto();
		if(refEtablissementDto != null){
		dureeProposablePromotion=serviceLocator.getRefParametreEtabService().findValueOfKey(refEtablissementDto.getId(), "GRH_DUREE_PROMOTION_LIST_APTITUDE");		
		propostionAvancementSearchDto = new PropostionAvancementDto();
		propostionAvancementSearchDto.setRefEtablissementDto(refEtablissementDto);
		listPropostionAvancementDto = new ArrayList<PropostionAvancementDto>();	
		situationEntiteDto = this.serviceLocator.getSituationService().findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_PROPOSITION_AVANCEMENT_CODE,UtilConstants.SITUATION_CREEE_CODE);
		nomenclatureByTypeAvancementDto = this.serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_TYPE_AVANCEMENT_VALUE,UtilConstant.NC_GRH_AVANCEMENT_PROMOTION_CODE);
		nomenclatureByTypePromotion= this.serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_TYPE_PROMOTION_VALUE,UtilConstant.NC_GRH_TYPE_PROMOTION_CODE_LIST_APTITUDE);
		listnomenclatureByMoisDto= this.serviceLocator.getNomenclatureService().findByDomaine(UtilConstant.NC_GRH_MOIS_VALUE);
			
		initSelectItemLists();	
		onSearchPropositionAvancement();
		moisLIST = findListeMois();
		}
	}
	
	public void carriereedit() {
		List<CarriereDto> carrieresDto = this.serviceLocator.getCarriereService().findAll();
	}
	
	public void employeproposeredit() {
		List<EmployeProposeDto> employeProposeDto = this.serviceLocator.getEmployeProposeService().findAll();
	}

	private void initDetail() {
		propostionAvancementDto = null;		
			}

	private void initSelectItemLists() {
		moisLIST = findListeMois();
	listNomenclatureActifDetachementID= listNomenclatureActifDetachementID();
	}
	

	public void onSearchPropositionAvancement() {
		try {initDetail();
			loadPropostionAvancementModel();			
		} catch (Exception e) {
			e.printStackTrace();
			}
		}
	public List<Integer> listNomenclatureActifDetachementID() {
		 List<Integer> listNomenclatureByPositionAgentDto = new ArrayList<Integer>();
		NomenclatureDto  nomenclatureByPositionAgentDto = this.serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,UtilConstant.NC_GRH_POSITION_EMPLOYE_ACTIVE_CODE);
	    if(nomenclatureByPositionAgentDto != null)  listNomenclatureByPositionAgentDto.add(nomenclatureByPositionAgentDto.getId()) ;      
		nomenclatureByPositionAgentDto = this.serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,UtilConstant.NC_GRH_POSITION_EMPLOYE_DETACHEMENT_CODE);
		if(nomenclatureByPositionAgentDto != null)  listNomenclatureByPositionAgentDto.add(nomenclatureByPositionAgentDto.getId()) ;    
		return listNomenclatureByPositionAgentDto;
	}
	
	
	@SuppressWarnings("deprecation")
	public void onNew() {
		propostionAvancementDto = new PropostionAvancementDto();
		propostionAvancementDto.setRefEtablissementDto(sessionBean.getRefEtablissementDto());		
		propostionAvancementDto.setAnnee((new Date()).getYear()+1900 ); 		
		//propostionAvancementDto.setMoisDto(moisDto);((new Date()).getMonth());
		propostionAvancementDto.setMoisDto(rechercheValeurMois((new Date()).getMonth())); 
		listDetailPropostionAvancementDto = new ArrayList<EmployeProposeDto>();	
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
	
public List<SelectItem> findListeMois() {
		List<SelectItem> result = new ArrayList<>();
		if (listnomenclatureByMoisDto != null)if (!listnomenclatureByMoisDto.isEmpty()) {
		for(NomenclatureDto dto:listnomenclatureByMoisDto){
			result.add(new SelectItem(dto.getId(), dto.getLibelleCourtFr()));
			
		}}	
		return result;

	}

	public void onRowSelectlistproposition(SelectEvent event) {
		try {
			propostionAvancementDto = (PropostionAvancementDto) event.getObject();
			listDetailPropostionAvancementDto = propostionAvancementDto.getEmployeProposesDto();
			selectedMoisId=propostionAvancementDto.getMoisDto().getId();
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
				searchMode.addFilter(new Filter("nomenclatureByTypeAvancement.id", nomenclatureByTypeAvancementDto.getId(),
						FilterMode.EQUALS));
				searchMode.addFilter(new Filter("situation.id", situationEntiteDto.getId(),
						FilterMode.EQUALS));
				searchMode.addFilter(new Filter("refEtablissement.id", refEtablissementDto.getId(),
						FilterMode.EQUALS));				
				propostionAvancementModel.setRowCount(serviceLocator.getPropostionAvancementService().countByKeyWord(searchKeyWords));
				List<PropostionAvancementDto> listpropavancement = serviceLocator.getPropostionAvancementService().findAllByKeyWord(searchKeyWords, searchMode);
				if (listpropavancement== null) listpropavancement = new ArrayList<PropostionAvancementDto>();
				return listpropavancement;
			}
		};

	}

	public void onSave() {
		if (validateForm()) {
			//beforeSave();
		List<EmployeProposeDto> employeProposeDtos = new ArrayList<EmployeProposeDto>();
		propostionAvancementDto.setSituationDto(situationEntiteDto);
		propostionAvancementDto.setNomenclatureByTypeAvancementDto(nomenclatureByTypeAvancementDto);
       if(propostionAvancementDto.getMoisDto().getCode()== null){			
			if (listnomenclatureByMoisDto != null)if (!listnomenclatureByMoisDto.isEmpty()) {
				for(NomenclatureDto dto:listnomenclatureByMoisDto){
					if(dto.getId().intValue()==selectedMoisId.intValue())propostionAvancementDto.setMoisDto(dto);				
				}}	
		}
		
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
		this.serviceLocator.getPropostionAvancementService().remove(propostionAvancementDto);
		loadPropostionAvancementModel();
		initDetail();
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	private boolean validateForm() {
		boolean result = true;
		List<String> maxDatepropositionAvancement=this.serviceLocator.getPropostionAvancementService().maxDatepropositionAvancement( situationEntiteDto.getId(),refEtablissementDto.getId(), nomenclatureByTypeAvancementDto.getId());
		Integer anneeMax = Integer.valueOf(maxDatepropositionAvancement.get(1));
		Integer moisMax = Integer.valueOf(maxDatepropositionAvancement.get(2));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		
		if (propostionAvancementDto.getAnnee() != null){			
		if((anneeMax >propostionAvancementDto.getAnnee())  || ((anneeMax ==propostionAvancementDto.getAnnee() )
				&& (moisMax>Integer.valueOf(propostionAvancementDto.getMoisDto().getCode() ) ))) 
			{GRHMessagesUtils.showErrorMessage("date_depot_recrutement_invalide");
			result = false;
			}		
		}		
		return result;
	}

		
	public void onCreateListEmployeProposable() {		
		periodeAutorise=creeDatePeriode();		
		List<Object> paramettres = new ArrayList<Object>();
		paramettres.add(0,periodeAutorise);
		paramettres.add(1,nomenclatureByTypePromotion.getId());
		paramettres.add(2,refEtablissementDto.getId());					
		List<Long> listDossierEmployeID=serviceLocator.getDossierEmployeService().findListeAptitudePromotionID(paramettres, listNomenclatureActifDetachementID);
		if(!listDossierEmployeID.isEmpty());
		listDetailPropostionAvancementDto=propostionAvancementDto.getEmployeProposesDto();
		this.serviceLocator.getPropostionAvancementService().deleteAllEmployProposition(propostionAvancementDto);
		//for(EmployeProposeDto proposeDto : listDetailPropostionAvancementDto){
		//	if(proposeDto!= null){				 
		///	EmployeProposeDto ep =proposeDto;
		//	ep =this.serviceLocator.getEmployeProposeService().findById(ep.getId());					
		//	if(ep !=null){
		//	ep =this.serviceLocator.getEmployeProposeService().merge(ep);
		//	this.serviceLocator.getEmployeProposeService().remove(ep);
			
		//	}}			
		//}
		listDetailPropostionAvancementDto=new ArrayList<EmployeProposeDto>();
		try {			
			List<EmployeProposeDto> ls=serviceLocator.getCarriereService().agentProposableListAptitude(listDossierEmployeID, propostionAvancementDto);		
			if(ls != null)if(!ls.isEmpty())
				for(EmployeProposeDto proposeDto : ls){						
			if(proposeDto!=null){
				employeProposeDto=this.serviceLocator.getEmployeProposeService().save(proposeDto);
				listDetailPropostionAvancementDto.add(employeProposeDto);
			}
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	public void onSearchDossierEmploye() {
		try {
			dossierEmployeModel =null;
			loaddossierEmployeModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loaddossierEmployeModel() {
		dossierEmployeModel = new LazyDataModel<DossierEmployeDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(DossierEmployeDto dossierEmployeDto) {
				return dossierEmployeDto.getId();
			}

			@Override
			public List<DossierEmployeDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,Map<String, String> filters) {			    
					periodeAutorise=creeDatePeriode();
					SearchMode searchMode = new SearchMode(pageSize, first);
					List<Object> paramettres = new ArrayList<Object>();
					paramettres.add(0, periodeAutorise);
					paramettres.add(1, nomenclatureByTypePromotion.getId());
					paramettres.add(2, refEtablissementDto.getId());					
					List<Long> listDossierEmployeID=serviceLocator.getDossierEmployeService().findListeAptitudePromotionID(paramettres, listNomenclatureActifDetachementID);
					List<DossierEmployeDto> dtos=null;
					if(!listDossierEmployeID.isEmpty()){
					searchMode.addFilter(new Filter("id",listDossierEmployeID.toArray(),FilterMode.IN));
					dossierEmployeModel.setRowCount(serviceLocator.getDossierEmployeService().countAllByExample(employeSearchDto, searchMode));
					dtos = serviceLocator.getDossierEmployeService().findAllByExample(employeSearchDto,searchMode);
					}
					if (dtos == null) dtos = new ArrayList<DossierEmployeDto>();
					return dtos;
			}
		};
	}	
	
	public List<SelectItem> findListeCorps() {
		List<CorpsDto> dtos = this.getServiceLocator().getCorpsService().findAll();
		List<SelectItem> result = new ArrayList<>();
		
		if (dtos != null) {
			
			for (CorpsDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getNomenclatureByIdNomenclatureCorps().getLibelleLongFr()));
			}
		}
		return result;
	}
	
	
	public void onCorpsSelected(AjaxBehaviorEvent event) {
		selectedGradeId = null;
		listeGrade = findListeGradeSuperieur(selectedCorpsId);
		
	}

	public void onGradeSelected(AjaxBehaviorEvent event) {
		GradeDto gradeDto = this.serviceLocator.getGradeService().findById(selectedGradeId);
		employeProposeDto.setNouveauGradeDto(gradeDto);
		List<GrilleIndiciaireDto> listGrilleIndicaireDto = gradeDto.getCategorieProfessionnelleDto().getGrilleIndiciaireDtos();
		for(GrilleIndiciaireDto grilleIndiciaireDto:listGrilleIndicaireDto){		
			String echelonAct = employeProposeDto.getDossierEmployeDto().getCarriereCouranteDto().getGrilleIndiciaireDto().getEchlon().toString();
			String echelonGrille = grilleIndiciaireDto.getEchlon().toString();
		if(echelonAct.equals(echelonGrille))	
			//indiceNouveauGrade = grilleIndiciaireDto.getIndice();
		employeProposeDto.setGrilleIndiciaireDto(grilleIndiciaireDto);
		}
		}
	
	public List<SelectItem> findListeGradeSuperieur(Integer selectedCorpIdAct){		
		List<SelectItem> listeGradeSup=new ArrayList<SelectItem>();
		if(employeProposeDto!= null)if(employeProposeDto.getDossierEmployeDto().getCarriereCouranteDto()!= null){
		 listeGradeSup= findListeGradeSuperieurByencientGrade(employeProposeDto.getDossierEmployeDto().getCarriereCouranteDto().getGradeDto(),selectedCorpsId);}	
		return listeGradeSup;
		}
	
	
	public void onRowSelect(SelectEvent event) {
		dossierEmployeDto = (DossierEmployeDto) event.getObject();
		if (dossierEmployeDto == null) {employeProposeDto = new EmployeProposeDto();}else{
		listeCorps = findListeCorps();
		employeProposeDto.setDossierEmployeDto(dossierEmployeDto);
		employeProposeDto.setPropostionAvancementDto(propostionAvancementDto);	
		employeProposeDto.setCarriereDto(dossierEmployeDto.getCarriereCouranteDto());
				
		}	
			
		
	}
	public void listemployeActifDetachement() {
		 List<NomenclatureDto> listNomenclatureByPositionAgentDto = new ArrayList<NomenclatureDto>();
		NomenclatureDto  nomenclatureByPositionAgentDto = this.serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,UtilConstant.NC_GRH_POSITION_EMPLOYE_ACTIVE_CODE);
	    if(nomenclatureByPositionAgentDto != null)  listNomenclatureByPositionAgentDto.add(nomenclatureByPositionAgentDto) ;      
		nomenclatureByPositionAgentDto = this.serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,UtilConstant.NC_GRH_POSITION_EMPLOYE_DETACHEMENT_CODE);
		 if(nomenclatureByPositionAgentDto != null)  listNomenclatureByPositionAgentDto.add(nomenclatureByPositionAgentDto) ;     
		
	}
	
	public void onEmployeSelect(SelectEvent event) {
		////////////////////////////////
		employeProposeDto = (EmployeProposeDto) event.getObject();
		
		
		if (employeProposeDto == null) {employeProposeDto = new EmployeProposeDto();}else{
		listeCorps = findListeCorps();
		}
			}
	
	
	private Date creeDatePeriode(){
		Date datePeriode = null;
		
		if(propostionAvancementDto.getMoisDto().getCode()== null){
			
			if (listnomenclatureByMoisDto != null)if (!listnomenclatureByMoisDto.isEmpty()) {
				for(NomenclatureDto dto:listnomenclatureByMoisDto){
					if(dto.getId().intValue()==selectedMoisId.intValue())propostionAvancementDto.setMoisDto(dto);				
				}}	
		}
	if(	propostionAvancementDto != null)if(propostionAvancementDto.getAnnee()!= null)
		if(propostionAvancementDto.getMoisDto().getCode()!= null)if(dureeProposablePromotion!=null){
			Integer moisPeriode = Integer.valueOf(propostionAvancementDto.getMoisDto().getCode());
			Integer anneePeriode = propostionAvancementDto.getAnnee();
			if(moisPeriode<12)moisPeriode++;
			else{
				moisPeriode = 1;
				anneePeriode++;
			}
			anneePeriode=anneePeriode-Integer.valueOf(dureeProposablePromotion);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateInString = anneePeriode.toString()+"-"+moisPeriode.toString()+"-01";
			try {				 
				datePeriode= formatter.parse(dateInString);		
					 
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
	return datePeriode;
		
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
	
	
	public void ajouterEmploye() {
		employeProposeDto=this.serviceLocator.getEmployeProposeService().save(employeProposeDto);
		listDetailPropostionAvancementDto= employeProposeDto.getPropostionAvancementDto().getEmployeProposesDto();		
		RequestContext.getCurrentInstance().execute("PF('employerproposeDialog').hide()");
	
	}
	
	public void removeEmploye() {
		EmployeProposeDto employerproposerDtoSelected = this.listDetailPropostionAvancementDto.get(employerProposerDtoSelectedIndex);
		this.listDetailPropostionAvancementDto.remove(employerproposerDtoSelected);
		propostionAvancementDto.setEmployeProposesDto(this.listDetailPropostionAvancementDto);
		propostionAvancementDto = this.serviceLocator.getPropostionAvancementService().save(propostionAvancementDto);
	}

	
	public void onAddEmployeProposeDto() {
		employeProposeDto = new EmployeProposeDto();
		dossierEmployeModel = null;
		listeGrade = new ArrayList<SelectItem>();
		listeCorps = new ArrayList<SelectItem>();
		indiceNouveauGrade = null;		
	}
	
	public void onSaveAvancementEmploye(){
		if(indiceNouveauGrade != null){
		//	employeProposeDto.set
		}
	}
	
	public void initialiserEmployes(){
		dossierEmployeModel = null;
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

	public List<EmployeProposeDto> getListDetailPropostionAvancementDto() {
		return listDetailPropostionAvancementDto;
	}

	
	public void setListDetailPropostionAvancementDto(
			List<EmployeProposeDto> listDetailPropostionAvancementDto) {
		this.listDetailPropostionAvancementDto = listDetailPropostionAvancementDto;
	}

	public EmployeProposeDto getEmployeProposeDto() {
		return employeProposeDto;
	}

	public void setEmployeProposeDto(EmployeProposeDto employeProposeDto) {
		this.employeProposeDto = employeProposeDto;
	}

	public LazyDataModel<DossierEmployeDto> getDossierEmployeModel() {
		return dossierEmployeModel;
	}

	public void setDossierEmployeModel(
			LazyDataModel<DossierEmployeDto> dossierEmployeModel) {
		this.dossierEmployeModel = dossierEmployeModel;
	}

	public RefEtablissementDto getRefEtablissementDto() {
		return refEtablissementDto;
	}

	public void setRefEtablissementDto(RefEtablissementDto refEtablissementDto) {
		this.refEtablissementDto = refEtablissementDto;
	}

	public DossierEmployeDto getEmployeSearchDto() {
		return employeSearchDto;
	}

	public void setEmployeSearchDto(DossierEmployeDto employeSearchDto) {
		this.employeSearchDto = employeSearchDto;
	}

	public Integer getSelectedCorpsId() {
		return selectedCorpsId;
	}

	public void setSelectedCorpsId(Integer selectedCorpsId) {
		this.selectedCorpsId = selectedCorpsId;
	}

	public Integer getSelectedGradeId() {
		return selectedGradeId;
	}

	public void setSelectedGradeId(Integer selectedGradeId) {
		this.selectedGradeId = selectedGradeId;
	}

	public List<SelectItem> getListeCorps() {
		return listeCorps;
	}

	public void setListeCorps(List<SelectItem> listeCorps) {
		this.listeCorps = listeCorps;
	}

	public List<SelectItem> getListeGrade() {
		return listeGrade;
	}

	public void setListeGrade(List<SelectItem> listeGrade) {
		this.listeGrade = listeGrade;
	}

	public Long getIndiceNouveauGrade() {
		return indiceNouveauGrade;
	}

	public void setIndiceNouveauGrade(Long indiceNouveauGrade) {
		this.indiceNouveauGrade = indiceNouveauGrade;
	}

	public Date getDatepropositionPromotion() {
		return datepropositionPromotion;
	}

	public void setDatepropositionPromotion(Date datepropositionPromotion) {
		this.datepropositionPromotion = datepropositionPromotion;
	}

	public Integer getEmployerProposerDtoSelectedIndex() {
		return employerProposerDtoSelectedIndex;
	}

	public void setEmployerProposerDtoSelectedIndex(
			Integer employerProposerDtoSelectedIndex) {
		this.employerProposerDtoSelectedIndex = employerProposerDtoSelectedIndex;
	}

	public Integer getSelectedMoisId() {
		return selectedMoisId;
	}

	public void setSelectedMoisId(Integer selectedMoisId) {
		this.selectedMoisId = selectedMoisId;
	}

	
	
	
	
	
	

}
