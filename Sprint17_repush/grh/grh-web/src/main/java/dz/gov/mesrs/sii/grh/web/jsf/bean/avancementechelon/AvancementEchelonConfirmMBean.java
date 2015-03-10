package dz.gov.mesrs.sii.grh.web.jsf.bean.avancementechelon;

import java.beans.Transient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.EmployeProposeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GrilleIndiciaireDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PropostionAvancementDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

@ManagedBean(name = "avancementEchelonConfirmMBean")
@ViewScoped
/**
 * 
 * @author SLIMANE Ramdane  on : 04 dec. 2014  14:51:27
 */
public class AvancementEchelonConfirmMBean extends BaseBean{
	/**
	 * serialVersionUID 
	 * @author SLIMANE Ramdane   on : 4 déc. 2014  12:31:19
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private LazyDataModel<PropostionAvancementDto> propostionAvancementModel;
	private PropostionAvancementDto propostionAvancementDto ;
	private PropostionAvancementDto propostionAvancementSearchDto ;
	private List<SelectItem> listeStructure;
	private List<PropostionAvancementDto> listPropostionAvancementDto;
	private List<Integer> moisLIST = null,listNomenclatureActifDetachementID;
	private String valeurMinEchelom,valeurMoyEchelom,valeurMaxEchelom;
	private List<EmployeProposeDto> listDetailPropostionAvancementDto;
	private EmployeProposeDto employeProposeDto;
	private List<SelectItem> echelons;
	private SelectItem echelon;
	private GrilleIndiciaireDto grilleIndiciaireDto;
	private List<GrilleIndiciaireDto> listgrilleIndiciaireDto;
	private RefEtablissementDto refEtablissementDto;
	private SituationEntiteDto situationEntiteDto;
	private NomenclatureDto nomenclatureByTypeAvancementDto;
	public AvancementEchelonConfirmMBean() {
		// TODO Auto-generated constructor stub
	}

	///********************************maj
	
	@PostConstruct
	public void init()  {	
		searchKeyWords = "";
		refEtablissementDto=sessionBean.getRefEtablissementDto();
		propostionAvancementSearchDto = new PropostionAvancementDto();
		propostionAvancementSearchDto.setRefEtablissementDto(refEtablissementDto);
		listPropostionAvancementDto = new ArrayList<PropostionAvancementDto>();	
		situationEntiteDto = this.serviceLocator.getSituationService().findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_PROPOSITION_AVANCEMENT_CODE,UtilConstants.SITUATION_CREEE_CODE);
		nomenclatureByTypeAvancementDto = this.serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_TYPE_AVANCEMENT_VALUE,UtilConstant.NC_GRH_AVANCEMENT_ECHELON_CODE);
		listNomenclatureActifDetachementID= listNomenclatureActifDetachementID();	
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
		List<CarriereDto> carrieresDto = this.serviceLocator.getCarriereService().findAll();
		for(int i = 0;i< carrieresDto.size();i++){
			System.out.println(carrieresDto.get(i).getMotif());
		}
		
		
	}
	
	
	
	

	private void initDetail() {
		propostionAvancementDto = null;
	//detailRecrutementDto = new DetailRecrutementDto();

	}

	private void initSelectItemLists() {		
	//	NomenclatureDto mm = this.serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_TYPE_AVANCEMENT_VALUE,UtilConstant.NC_GRH_AVANCEMENT_ECHELON_CODE);
	//	System.out.println("----------------------"+mm.getCode()+mm.getId().toString());
	//	mm = this.serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_TYPE_AVANCEMENT_VALUE,UtilConstant.NC_GRH_AVANCEMENT_PROMOTION_CODE);
	//	System.out.println("----------------------"+mm.getCode()+mm.getId().toString());
		
		//listeModeRecrutement = findListeNcModeRecrutement();
		//listeTypeRecrutement = findListeNcTypeRecrutement();
		//listeCorps = findListeCorps();
		//listeStructure =  findListeStructure(sessionBean.getRefEtablissementDto().getId());
	moisLIST = Collections.unmodifiableList(Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11, 12}));
	valeurMinEchelom=serviceLocator.getRefParametreEtabService().findValueOfKey(propostionAvancementSearchDto.getRefEtablissementDto().getId(), "GRH_ECHELON_DUREE_MINIMALE");
	valeurMoyEchelom=serviceLocator.getRefParametreEtabService().findValueOfKey(propostionAvancementSearchDto.getRefEtablissementDto().getId(), "GRH_ECHELON_DUREE_MOYENNE");
	valeurMaxEchelom=serviceLocator.getRefParametreEtabService().findValueOfKey(propostionAvancementSearchDto.getRefEtablissementDto().getId(), "GRH_ECHELON_DUREE_MAXIMALE");
	
	}

	public void onSearchPropositionAvancement() {
		try {initDetail();
			loadPropostionAvancementModel();
			initDetail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings("deprecation")
	public void onNew() {
		propostionAvancementDto = new PropostionAvancementDto();
		propostionAvancementDto.setRefEtablissementDto(sessionBean.getRefEtablissementDto());		
		propostionAvancementDto.setAnnee((new Date()).getYear()+1900 ); 		
		//propostionAvancementDto.setMois((new Date()).getMonth());				
		listDetailPropostionAvancementDto = new ArrayList<EmployeProposeDto>();
				

	}

	public void onRowSelect(SelectEvent event) {
		try {
			propostionAvancementDto = (PropostionAvancementDto) event.getObject();
			listDetailPropostionAvancementDto = propostionAvancementDto.getEmployeProposesDto();
			List<EmployeProposeDto> lsemployeProposedto = new ArrayList<EmployeProposeDto>();
			if(listDetailPropostionAvancementDto!= null)if(!listDetailPropostionAvancementDto.isEmpty()){
				for(EmployeProposeDto employeProposedto :listDetailPropostionAvancementDto){
					if(employeProposedto != null)if(employeProposedto.getSelection()){
						lsemployeProposedto.add(employeProposedto);
					}
				}
				listDetailPropostionAvancementDto=	lsemployeProposedto;	
			}
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
				
				////////
				///////
				@SuppressWarnings("unused")
				Collection<Integer> collection = new ArrayList<Integer>();	
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


@javax.persistence.Transient
	public void onSave() {	try{	
		List<EmployeProposeDto> employeProposeDtos = new ArrayList<EmployeProposeDto>();
		SituationEntiteDto situationEntiteDto = this.serviceLocator.getSituationService()
				.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_PROPOSITION_AVANCEMENT_CODE,
						UtilConstants.SITUATION_CREEE_CODE);
		propostionAvancementDto.setSituationDto(situationEntiteDto);
		if(listDetailPropostionAvancementDto != null) 
		{for(EmployeProposeDto employeProposeDto:listDetailPropostionAvancementDto) {
		employeProposeDto.setPropostionAvancementDto(propostionAvancementDto);
		employeProposeDto=this.serviceLocator.getEmployeProposeService().save(employeProposeDto);	
		employeProposeDtos.add(employeProposeDto);		
		}
		}	
	propostionAvancementDto.setEmployeProposesDto(employeProposeDtos);
	propostionAvancementDto = this.serviceLocator.getPropostionAvancementService().save(propostionAvancementDto);		
		SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
		situationEntiteOccurrenceDto.setDateSituation(new Date());
		situationEntiteOccurrenceDto.setIdOccurrence(propostionAvancementDto.getId().intValue());
		situationEntiteOccurrenceDto.setIdSituation(situationEntiteDto.getId());
		this.serviceLocator.getSituationService().saveSituationOccurrence(situationEntiteOccurrenceDto);
		loadPropostionAvancementModel();
			CommonMessagesUtils.showSuccessSaveMessage();
		}catch(Exception e){
	    System.out.println("probleme de sauvegarde");
	}
	
	
	}

	public void onValide() {
		List<EmployeProposeDto> employeProposeDtos = new ArrayList<EmployeProposeDto>();
		SituationEntiteDto situationEntiteDto = this.serviceLocator.getSituationService()
				.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_PROPOSITION_AVANCEMENT_CODE,
						UtilConstants.SITUATION_VALIDEE_CODE);
		propostionAvancementDto.setSituationDto(situationEntiteDto);
		propostionAvancementDto = this.serviceLocator.getPropostionAvancementService().save(propostionAvancementDto);
		SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
		situationEntiteOccurrenceDto.setDateSituation(new Date());
		situationEntiteOccurrenceDto.setIdOccurrence(propostionAvancementDto.getId().intValue());
		situationEntiteOccurrenceDto.setIdSituation(situationEntiteDto.getId());
		this.serviceLocator.getSituationService().saveSituationOccurrence(situationEntiteOccurrenceDto);
		////////						
			if(listDetailPropostionAvancementDto != null) 
				{for(EmployeProposeDto employeProposeDto:listDetailPropostionAvancementDto) {
					if(employeProposeDto.getConfirm()!= null)if(employeProposeDto.getConfirm()){
					employeProposeDto.setPropostionAvancementDto(propostionAvancementDto);									
					employeProposeDto=this.serviceLocator.getEmployeProposeService().save(employeProposeDto);	
					employeProposeDtos.add(employeProposeDto);
					modifierCarriereEmployer(employeProposeDto);}
				}
				}
			
			propostionAvancementDto.setEmployeProposesDto(employeProposeDtos);
			propostionAvancementDto = this.serviceLocator.getPropostionAvancementService().save(propostionAvancementDto);			
			loadPropostionAvancementModel();
			CommonMessagesUtils.showSuccessSaveMessage();
			propostionAvancementDto = null;		
	}

	
    private void modifierCarriereEmployer(EmployeProposeDto employeProposeDto){
		DossierEmployeDto dosemployeDto =employeProposeDto.getDossierEmployeDto();
		CarriereDto carriereDto = new CarriereDto();		
		if(employeProposeDto.getCarriereDto() != null){
			carriereDto.setCorpsDto(dosemployeDto.getCarriereCouranteDto().getCorpsDto());
			carriereDto.setDateChangement(dosemployeDto.getCarriereCouranteDto().getDateChangement());
			carriereDto.setGradeDto(dosemployeDto.getCarriereCouranteDto().getGradeDto());
			carriereDto.setDateEffetGrade(dosemployeDto.getCarriereCouranteDto().getDateEffetGrade());
			carriereDto.setDateEffet(employeProposeDto.getDateEffetRetenue());
			carriereDto.setDateEffetEchelon(employeProposeDto.getDateEffetRetenue());
			carriereDto.setDossierEmployeDto(employeProposeDto.getDossierEmployeDto());
			carriereDto.setGrilleIndiciaireDto(employeProposeDto.getGrilleIndiciaireDto());
			carriereDto.setDateEffetCorps(dosemployeDto.getCarriereCouranteDto().getDateEffetCorps());
			carriereDto.setConfirm(dosemployeDto.getCarriereCouranteDto().getConfirm());
			carriereDto.setDateChangement(dosemployeDto.getCarriereCouranteDto().getDateChangement());
			carriereDto.setDateDebutFormation(dosemployeDto.getCarriereCouranteDto().getDateDebutFormation());
			carriereDto.setDateExament(dosemployeDto.getCarriereCouranteDto().getDateExament());
			carriereDto.setDateFinFormation(dosemployeDto.getCarriereCouranteDto().getDateFinFormation());
			carriereDto.setDateObtentionDiplome(dosemployeDto.getCarriereCouranteDto().getDateObtentionDiplome());
			carriereDto.setMotif(dosemployeDto.getCarriereCouranteDto().getMotif());
			carriereDto.setNomenclatureBydiplomeDto(dosemployeDto.getCarriereCouranteDto().getNomenclatureBydiplomeDto());
			carriereDto.setNomenclatureBytitreDto(dosemployeDto.getCarriereCouranteDto().getNomenclatureBytitreDto());
			carriereDto.setNomenclatureBytypePromotionDto(dosemployeDto.getCarriereCouranteDto().getNomenclatureBytypePromotionDto());
			carriereDto.setObjetFormation(dosemployeDto.getCarriereCouranteDto().getObjetFormation());			
			carriereDto =this.serviceLocator.getCarriereService().save(carriereDto);
			if(carriereDto != null)	dosemployeDto=this.serviceLocator.getDossierEmployeService().saveDossierEmployeByCarriere(carriereDto);
			
			
    }}
	
	public void onCreateListEmployeProposable() {
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		listDetailPropostionAvancementDto=new ArrayList<EmployeProposeDto>();	
		try {
			if(valeurMinEchelom != null)
				listDetailPropostionAvancementDto=serviceLocator.getCarriereService().agentProposableEchelon(Integer.valueOf(propostionAvancementDto.getMoisDto().getCode()), propostionAvancementDto.getAnnee(),Integer.valueOf(valeurMinEchelom),
						formatter.parse("2017-01-01"), propostionAvancementSearchDto,refEtablissementDto.getId(),listNomenclatureActifDetachementID);
				
				List<EmployeProposeDto> detailPropostionAvancementDto = new ArrayList<EmployeProposeDto>();
				if(listDetailPropostionAvancementDto != null)if(!listDetailPropostionAvancementDto.isEmpty())
				for(EmployeProposeDto employePropose :listDetailPropostionAvancementDto){			
					if(employePropose.getSelection()) detailPropostionAvancementDto.add(employePropose);			
									}
				
				listDetailPropostionAvancementDto = detailPropostionAvancementDto;
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
@Transient
	public void confirmEmploye() {
		List<EmployeProposeDto> lsemployeProposedto = new ArrayList<EmployeProposeDto>();		
		employeProposeDto=this.serviceLocator.getEmployeProposeService().save(employeProposeDto);
		listDetailPropostionAvancementDto= employeProposeDto.getPropostionAvancementDto().getEmployeProposesDto();	
		if(listDetailPropostionAvancementDto!= null)if(!listDetailPropostionAvancementDto.isEmpty()){
			for(EmployeProposeDto employeProposedto :listDetailPropostionAvancementDto){
				if(employeProposedto != null)if(employeProposedto.getSelection()){
					lsemployeProposedto.add(employeProposedto);
				}
			}
			listDetailPropostionAvancementDto=	lsemployeProposedto;	
			List<EmployeProposeDto> detailPropostionAvancementDto = new ArrayList<EmployeProposeDto>();
			if(listDetailPropostionAvancementDto != null)if(!listDetailPropostionAvancementDto.isEmpty())
			for(EmployeProposeDto employePropose :listDetailPropostionAvancementDto){			
				if(employePropose.getSelection()) detailPropostionAvancementDto.add(employePropose);	
								}
			listDetailPropostionAvancementDto = detailPropostionAvancementDto;
	}
		RequestContext.getCurrentInstance().execute("PF('employerproposeDialog').hide()");	
		
	}

	
	
	public LazyDataModel<PropostionAvancementDto> getPropostionAvancementModel() {
		if(propostionAvancementModel== null) propostionAvancementModel = new LazyDataModel<PropostionAvancementDto>() {

			/**
			 * serialVersionUID 
			 * @author obelbrik  on : 4 déc. 2014  15:25:21
			 */
			private static final long serialVersionUID = 1L;
		};
		return propostionAvancementModel;
	}

	
	/**
	 * [PropositionAvancementBean.propostionAvancementModel] Setter 
	 * @author SLIMANE Ramdane on : 4 déc. 2014  11:22:52
	 * @param propostionAvancementModel the propostionAvancementModel to set
	 */
	public void setPropostionAvancementModel(
			LazyDataModel<PropostionAvancementDto> propostionAvancementModel) {
		this.propostionAvancementModel = propostionAvancementModel;
	}

	/**
	 * [PropositionAvancementBean.propostionAvancementDto] Getter 
	 * @author SLIMANE Ramdane on : 4 déc. 2014  11:09:43
	 * @return the propostionAvancementDto
	 */
	public PropostionAvancementDto getPropostionAvancementDto() {
		return propostionAvancementDto;
	}

	/**
	 * [PropositionAvancementBean.propostionAvancementDto] Setter 
	 * @author SLIMANE Ramdane on : 4 déc. 2014  11:09:43
	 * @param propostionAvancementDto the propostionAvancementDto to set
	 */
	public void setPropostionAvancementDto(
			PropostionAvancementDto propostionAvancementDto) {
		this.propostionAvancementDto = propostionAvancementDto;
	}

	/**
	 * [PropositionAvancementBean.propostionAvancementSearchDto] Getter 
	 * @author SLIMANE Ramdane on : 4 déc. 2014  11:21:04
	 * @return the propostionAvancementSearchDto
	 */
	public PropostionAvancementDto getPropostionAvancementSearchDto() {
		return propostionAvancementSearchDto;
	}

	/**
	 * [PropositionAvancementBean.propostionAvancementSearchDto] Setter 
	 * @author SLIMANE Ramdane on : 4 déc. 2014  11:21:04
	 * @param propostionAvancementSearchDto the propostionAvancementSearchDto to set
	 */
	public void setPropostionAvancementSearchDto(
			PropostionAvancementDto propostionAvancementSearchDto) {
		this.propostionAvancementSearchDto = propostionAvancementSearchDto;
	}

	/**
	 * [PropositionAvancementBean.searchKeyWords] Getter 
	 * @author SLIMANE Ramdane on : 4 déc. 2014  11:24:26
	 * @return the searchKeyWords
	 */
	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	/**
	 * [PropositionAvancementBean.searchKeyWords] Setter 
	 * @author SLIMANE Ramdane on : 4 déc. 2014  11:24:26
	 * @param searchKeyWords the searchKeyWords to set
	 */
	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	/**
	 * [PropositionAvancementBean.listeStructure] Getter 
	 * @author obelbrik on : 7 déc. 2014  10:14:24
	 * @return the listeStructure
	 */
	public List<SelectItem> getListeStructure() {
		return listeStructure;
	}

	/**
	 * [PropositionAvancementBean.listeStructure] Setter 
	 * @author obelbrik on : 7 déc. 2014  10:14:24
	 * @param listeStructure the listeStructure to set
	 */
	public void setListeStructure(List<SelectItem> listeStructure) {
		this.listeStructure = listeStructure;
	}

	/**
	 * [PropositionAvancementMBean.listPropostionAvancementDto] Getter 
	 * @author obelbrik on : 7 déc. 2014  12:21:46
	 * @return the listPropostionAvancementDto
	 */
	public List<PropostionAvancementDto> getListPropostionAvancementDto() {
		return listPropostionAvancementDto;
	}

	/**
	 * [PropositionAvancementMBean.listPropostionAvancementDto] Setter 
	 * @author obelbrik on : 7 déc. 2014  12:21:46
	 * @param listPropostionAvancementDto the listPropostionAvancementDto to set
	 */
	public void setListPropostionAvancementDto(
			List<PropostionAvancementDto> listPropostionAvancementDto) {
		this.listPropostionAvancementDto = listPropostionAvancementDto;
	}

	
	/**
	 * [PropositionAvancementMBean.moisLIST] Getter 
	 * @author obelbrik on : 9 déc. 2014  12:12:56
	 * @return the moisLIST
	 */
	public List<Integer> getMoisLIST() {
		return moisLIST;
	}

	/**
	 * [PropositionAvancementMBean.moisLIST] Setter 
	 * @author obelbrik on : 9 déc. 2014  12:12:56
	 * @param moisLIST the moisLIST to set
	 */
	public void setMoisLIST(List<Integer> moisLIST) {
		this.moisLIST = moisLIST;
	}

	public String getValeurMinEchelom() {
		return valeurMinEchelom;
	}

	/**
	 * [PropositionAvancementMBean.valeurMinEchelom] Setter 
	 * @author obelbrik on : 9 déc. 2014  10:00:24
	 * @param valeurMinEchelom the valeurMinEchelom to set
	 */
	public void setValeurMinEchelom(String valeurMinEchelom) {
		this.valeurMinEchelom = valeurMinEchelom;
	}

	/**
	 * [PropositionAvancementMBean.valeurMoyEchelom] Getter 
	 * @author obelbrik on : 9 déc. 2014  12:41:44
	 * @return the valeurMoyEchelom
	 */
	public String getValeurMoyEchelom() {
		return valeurMoyEchelom;
	}

	/**
	 * [PropositionAvancementMBean.valeurMoyEchelom] Setter 
	 * @author obelbrik on : 9 déc. 2014  12:41:44
	 * @param valeurMoyEchelom the valeurMoyEchelom to set
	 */
	public void setValeurMoyEchelom(String valeurMoyEchelom) {
		this.valeurMoyEchelom = valeurMoyEchelom;
	}

	/**
	 * [PropositionAvancementMBean.valeurMaxEchelom] Getter 
	 * @author obelbrik on : 9 déc. 2014  12:41:44
	 * @return the valeurMaxEchelom
	 */
	public String getValeurMaxEchelom() {
		return valeurMaxEchelom;
	}

	/**
	 * [PropositionAvancementMBean.valeurMaxEchelom] Setter 
	 * @author obelbrik on : 9 déc. 2014  12:41:44
	 * @param valeurMaxEchelom the valeurMaxEchelom to set
	 */
	public void setValeurMaxEchelom(String valeurMaxEchelom) {
		this.valeurMaxEchelom = valeurMaxEchelom;
	}

	/**
	 * [PropositionAvancementMBean.listDetailPropostionAvancementDto] Getter 
	 * @author obelbrik on : 16 déc. 2014  10:00:04
	 * @return the listDetailPropostionAvancementDto
	 */
	public List<EmployeProposeDto> getListDetailPropostionAvancementDto() {
		return listDetailPropostionAvancementDto;
	}

	/**
	 * [PropositionAvancementMBean.listDetailPropostionAvancementDto] Setter 
	 * @author obelbrik on : 16 déc. 2014  10:00:04
	 * @param listDetailPropostionAvancementDto the listDetailPropostionAvancementDto to set
	 */
	public void setListDetailPropostionAvancementDto(
			List<EmployeProposeDto> listDetailPropostionAvancementDto) {
		this.listDetailPropostionAvancementDto = listDetailPropostionAvancementDto;
	}

	/**
	 * [PropositionAvancementMBean.mois_LIST] Setter 
	 * @author obelbrik on : 8 déc. 2014  15:08:27
	 * @param mois_LIST the mois_LIST to set
	 */
	
	public void onEmployeSelect(SelectEvent event) {
		try {
			
			echelons = new ArrayList<>();
			EmployeProposeDto selectedemploye = (EmployeProposeDto) event.getObject();
			employeProposeDto = new EmployeProposeDto();
			if(selectedemploye.getGrilleIndiciaireDto()== null) selectedemploye.setGrilleIndiciaireDto(new GrilleIndiciaireDto());
			this.serviceLocator.getMapper().map(selectedemploye, employeProposeDto);
			Integer categorieEmployeId =employeProposeDto.getCarriereDto().getGrilleIndiciaireDto().getCategorieProfessionnelleDto().getId();
			if(categorieEmployeId != null){
				echelons=findListeGrilleIndiciaireByencientEchelon(categorieEmployeId,employeProposeDto.getCarriereDto().getGrilleIndiciaireDto().getIndice());
				
			}
			
			
			
			
			
			//listgrilleIndiciaireDto = employeProposeDto.getCarriere().getGrilleIndiciaireDto().getCategorieProfessionnelleDto().getGrilleIndiciaireDtos();
			
			//for(GrilleIndiciaireDto grilleIndiciaireDto : listgrilleIndiciaireDto){
			//	echelons.add(grilleIndiciaireDto.getEchlon());
			//}
		
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public EmployeProposeDto getEmployeProposeDto() {
		return employeProposeDto;
	}

	public void setEmployeProposeDto(EmployeProposeDto employeProposeDto) {
		this.employeProposeDto = employeProposeDto;
	}

	/**
	 * [AvancementEchelonConfirmMBean.grilleIndiciaireDto] Getter 
	 * @author obelbrik on : 18 déc. 2014  12:19:27
	 * @return the grilleIndiciaireDto
	 */
	public GrilleIndiciaireDto getGrilleIndiciaireDto() {
		return grilleIndiciaireDto;
	}

	/**
	 * [AvancementEchelonConfirmMBean.grilleIndiciaireDto] Setter 
	 * @author obelbrik on : 18 déc. 2014  12:19:27
	 * @param grilleIndiciaireDto the grilleIndiciaireDto to set
	 */
	public void setGrilleIndiciaireDto(GrilleIndiciaireDto grilleIndiciaireDto) {
		this.grilleIndiciaireDto = grilleIndiciaireDto;
	}

	/**
	 * [AvancementEchelonConfirmMBean.listgrilleIndiciaireDto] Getter 
	 * @author obelbrik on : 18 déc. 2014  12:19:27
	 * @return the listgrilleIndiciaireDto
	 */
	public List<GrilleIndiciaireDto> getListgrilleIndiciaireDto() {
		return listgrilleIndiciaireDto;
	}

	/**
	 * [AvancementEchelonConfirmMBean.listgrilleIndiciaireDto] Setter 
	 * @author obelbrik on : 18 déc. 2014  12:19:27
	 * @param listgrilleIndiciaireDto the listgrilleIndiciaireDto to set
	 */
	public void setListgrilleIndiciaireDto(
			List<GrilleIndiciaireDto> listgrilleIndiciaireDto) {
		this.listgrilleIndiciaireDto = listgrilleIndiciaireDto;
	}

	/**
	 * [AvancementEchelonConfirmMBean.echelons] Getter 
	 * @author obelbrik on : 18 déc. 2014  12:54:24
	 * @return the echelons
	 */
	

	/**
	 * [AvancementEchelonConfirmMBean.echelon] Getter 
	 * @author obelbrik on : 18 déc. 2014  12:54:24
	 * @return the echelon
	 */
	

	/**
	 * [AvancementEchelonConfirmMBean.echelons] Setter 
	 * @author obelbrik on : 22 déc. 2014  12:17:27
	 * @param echelons the echelons to set
	 */
	

	
	/**
	 * [AvancementEchelonConfirmMBean.echelons] Getter 
	 * @author obelbrik on : 22 déc. 2014  12:34:52
	 * @return the echelons
	 */
	public List<SelectItem> getEchelons() {
		return echelons;
	}

	/**
	 * [AvancementEchelonConfirmMBean.echelons] Setter 
	 * @author obelbrik on : 22 déc. 2014  12:34:52
	 * @param echelons the echelons to set
	 */
	public void setEchelons(List<SelectItem> echelons) {
		this.echelons = echelons;
	}

	/**
	 * [AvancementEchelonConfirmMBean.echelon] Getter 
	 * @author obelbrik on : 22 déc. 2014  12:31:58
	 * @return the echelon
	 */
	public SelectItem getEchelon() {
		return echelon;
	}

	
	
	/**
	 * [AvancementEchelonConfirmMBean.echelon] Setter 
	 * @author obelbrik on : 22 déc. 2014  12:31:58
	 * @param echelon the echelon to set
	 */
	public void setEchelon(SelectItem echelon) {
		this.echelon = echelon;
	}

	/**
	 * [AvancementEchelonConfirmMBean.echelons] Getter 
	 * @author obelbrik on : 22 déc. 2014  12:31:58
	 * @return the echelons
	 */
	

	



























}
