/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.bac.BacBB.java] 
 * @author  Rafik LAIB on : 21 mai 2014  11:18:57
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.cycle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.ParamTypeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeParamDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.CycleService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.MessageUtil;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

/**
 * @author rlaib  on : 16 juil. 2014  11:56:31
 */
@ManagedBean(name = "periodeBB")
@ViewScoped
public class PeriodeBB {

	public PeriodeBB() {
		
	}
	
	@PostConstruct
	public void init() {
		
		 try	{
			 	
					initSessionInfos();
					listTypesFormation = initNomenclatureList(OfConstants.NC_NAME_TYPE_FORMATION);
					listTypesPeriodicites = initNomenclatureList(OfConstants.NC_NAME_CYCLE_PERIODE_PERIODICITE);
			 	
					if(listTypesPeriodicites != null  && listTypesPeriodicites.size()>0) {
						selectedPeriodiciteCode = listTypesPeriodicites.get(0).getValue().toString();
						listPeriodesParPeriodicite=  initPeriodesParPeriodicite(selectedPeriodiciteCode);
						if(listPeriodesParPeriodicite != null  && listPeriodesParPeriodicite.size()>0) {
							selectedPeriodeId = Integer.parseInt(listPeriodesParPeriodicite.get(0).getValue().toString());
						}
					}
					
				 	if(listTypesFormation  != null && listTypesFormation .size()>0) {
			 			if (!(typeFormationPeriodCode!= null && !typeFormationPeriodCode.trim().isEmpty() && !typeFormationPeriodCode.equals("null"))) {
			 				typeFormationPeriodCode = listTypesFormation.get(0).getValue().toString();
			 			}
			 			listTypesCycles = cycleDtosToSelectItems(cycleService.findByTypeFormationCode(typeFormationPeriodCode));
			 			if (listTypesCycles!= null  && listTypesCycles.size()>0) {
			 				cyclePeriodId = listTypesCycles.get(0).getValue().toString();
			 				listTypesNiveaux = niveauDtosToSelectItems(niveauService.findByCycleId(Integer.parseInt(cyclePeriodId)));
			 				
			 				if (listTypesNiveaux!= null  && listTypesNiveaux.size()>0) {
				 				levelPeriodId = listTypesNiveaux.get(0).getValue().toString();
				 				listPeriods = periodeService.findByLevelId(Integer.parseInt(levelPeriodId));
				 			}
			 			}
			 		}
				 	if ((levelPeriodId!= null && !levelPeriodId.trim().isEmpty() && !levelPeriodId.equals("null"))) {
						listPeriods = periodeService.findByLevelId(Integer.parseInt(levelPeriodId));
				 	}
				 	
					if (periodId != null && !periodId.trim().isEmpty() && !periodId.equals("null") &&(Integer.parseInt(periodId)>0)) {
			 			loadPeriodDetails();
		
			 		}
			 		else {
			 			selectedPeriod = new PeriodeDto();
			 			selectedDetailPeriodTitle = MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.PERIOD_EDIT_BUNDLE_KEY_EDIT_PAGE_PANEL_NEW_TITLE);
			 		}

		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	
	}
	

	private List<SelectItem> initNomenclatureList(String ncNameTypeFormation) {

		try {
					List<NomenclatureDto> _list = nomenclatureService.findByName(ncNameTypeFormation);
					List<SelectItem> result = new ArrayList<SelectItem>();
					for (NomenclatureDto _nomenclatureDto : _list) {
						result.add(new SelectItem(_nomenclatureDto.getCode(),_nomenclatureDto.getLibelleLongFr()));
					}
					return result;
					
		}
		 catch(Exception e){
			 e.printStackTrace();
		 }
		return null;
	
	}

	// ===================================================================  
	// Beans Services 
	// ===================================================================
	@ManagedProperty(value="#{cycleService}")
	private CycleService cycleService;
	
	@ManagedProperty(value="#{niveauService}")
	private NiveauService niveauService;
	
	@ManagedProperty(value="#{periodeService}")
	private PeriodeService periodeService;

	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;
	
	@ManagedProperty(value="#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	// ===================================================================  
	// Beans Services  Getters / Setters
	// ===================================================================
	/**
	 * [NiveauBB.cycleService] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  02:13:54
	 * @return the cycleService
	 */
	public CycleService getCycleService() {
		return cycleService;
	}

	/**
	 * [NiveauBB.cycleService] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  02:13:54
	 * @param cycleService the cycleService to set
	 */
	public void setCycleService(CycleService cycleService) {
		this.cycleService = cycleService;
	}
	
	/**
	 * [CycleBB.niveauService] Getter 
	 * @author rlaib on : 16 juil. 2014  11:55:49
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [CycleBB.niveauService] Setter 
	 * @author rlaib on : 16 juil. 2014  11:55:49
	 * @param niveauService the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [PeriodeBB.periodeService] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:21:08
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [PeriodeBB.periodeService] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:21:08
	 * @param periodeService the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}
	
	/**
	 * [PeriodeBB.sessionBean] Getter 
	 * @author rlaib on : 6 oct. 2014  11:42:02
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [PeriodeBB.sessionBean] Setter 
	 * @author rlaib on : 6 oct. 2014  11:42:02
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [PeriodeBB.nomenclatureService] Getter 
	 * @author rlaib on : 12 oct. 2014  10:09:04
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [PeriodeBB.nomenclatureService] Setter 
	 * @author rlaib on : 12 oct. 2014  10:09:04
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	// ===================================================================  
	// Parameters
	// ===================================================================
	private String outcomePage;
	private String periodId;
	private String levelPeriodId;
	private String typeFormationPeriodCode;
	private String cyclePeriodId;
	private String periodParamId;
	private boolean applyAllToPeriodsLevel;
	private boolean canAddParam;
	
	// ===================================================================  
	// Session Infos
	// ===================================================================
	private String codeEtablissement;
	private String newEtabCode;
	private String libelleEtab;
	private Integer idYear;
	private String libelleYear;
	
	// ===================================================================  
	// Properties Model
	// ===================================================================
	private List<SelectItem> listTypesFormation;
	private List<SelectItem> listTypesPeriodicites;
	private List<SelectItem> listTypesCycles;
	private List<SelectItem> listTypesNiveaux;
	private String selectedDetailPeriodTitle;
	private List<PeriodeDto> listPeriods;
	private boolean showPeriodDetails;
	private boolean showParamPeriodDetails;
	private boolean showBtnSave;
	private boolean showBtnRemove;
	private PeriodeDto selectedPeriod;
	private List<PeriodeParamDto> periodeParams;
	private PeriodeParamDto selectedPeriodeParam;
	private List<SelectItem> listTypesParams;
	private String selectedPeriodiciteCode;
	private Integer selectedPeriodeId;
	private List<SelectItem> listPeriodesParPeriodicite;
	
	// ===================================================================  
	// Parameters Getters / Setters
	// ===================================================================
	
	/**
	 * [PeriodeBB.outcomePage] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:39:11
	 * @return the outcomePage
	 */
	public String getOutcomePage() {
		return outcomePage;
	}

	/**
	 * [PeriodeBB.outcomePage] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:39:11
	 * @param outcomePage the outcomePage to set
	 */
	public void setOutcomePage(String outcomePage) {
		this.outcomePage = outcomePage;
	}
	
	/**
	 * [PeriodeBB.periodId] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:44:05
	 * @return the periodId
	 */
	public String getPeriodId() {
		return periodId;
	}

	/**
	 * [PeriodeBB.periodId] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:44:05
	 * @param periodId the periodId to set
	 */
	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}

	/**
	 * [PeriodeBB.levelPeriodId] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:39:11
	 * @return the levelPeriodId
	 */
	public String getLevelPeriodId() {
		return levelPeriodId;
	}

	/**
	 * [PeriodeBB.levelPeriodId] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:39:11
	 * @param levelPeriodId the levelPeriodId to set
	 */
	public void setLevelPeriodId(String levelPeriodId) {
		this.levelPeriodId = levelPeriodId;
	}

	/**
	 * [PeriodeBB.typeFormationPeriodCode] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:39:11
	 * @return the typeFormationPeriodCode
	 */
	public String getTypeFormationPeriodCode() {
		return typeFormationPeriodCode;
	}

	/**
	 * [PeriodeBB.typeFormationPeriodCode] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:39:11
	 * @param typeFormationPeriodCode the typeFormationPeriodCode to set
	 */
	public void setTypeFormationPeriodCode(String typeFormationPeriodCode) {
		this.typeFormationPeriodCode = typeFormationPeriodCode;
	}

	/**
	 * [PeriodeBB.cyclePeriodId] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:39:11
	 * @return the cyclePeriodId
	 */
	public String getCyclePeriodId() {
		return cyclePeriodId;
	}

	/**
	 * [PeriodeBB.cyclePeriodId] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:39:11
	 * @param cyclePeriodId the cyclePeriodId to set
	 */
	public void setCyclePeriodId(String cyclePeriodId) {
		this.cyclePeriodId = cyclePeriodId;
	}

	/**
	 * [PeriodeBB.periodParamId] Getter 
	 * @author rlaib on : 6 oct. 2014  13:59:02
	 * @return the periodParamId
	 */
	public String getPeriodParamId() {
		return periodParamId;
	}

	/**
	 * [PeriodeBB.periodParamId] Setter 
	 * @author rlaib on : 6 oct. 2014  13:59:02
	 * @param periodParamId the periodParamId to set
	 */
	public void setPeriodParamId(String periodParamId) {
		this.periodParamId = periodParamId;
	}

	/**
	 * [PeriodeBB.applyAllToPeriodsLevel] Getter 
	 * @author rlaib on : 12 oct. 2014  10:22:48
	 * @return the applyAllToPeriodsLevel
	 */
	public boolean isApplyAllToPeriodsLevel() {
		return applyAllToPeriodsLevel;
	}

	/**
	 * [PeriodeBB.canAddParam] Getter 
	 * @author rlaib on : 8 déc. 2014  16:41:29
	 * @return the canAddParam
	 */
	public boolean isCanAddParam() {
		return canAddParam;
	}

	/**
	 * [PeriodeBB.canAddParam] Setter 
	 * @author rlaib on : 8 déc. 2014  16:41:29
	 * @param canAddParam the canAddParam to set
	 */
	public void setCanAddParam(boolean canAddParam) {
		this.canAddParam = canAddParam;
	}

	/**
	 * [PeriodeBB.applyAllToPeriodsLevel] Setter 
	 * @author rlaib on : 12 oct. 2014  10:22:48
	 * @param applyAllToPeriodsLevel the applyAllToPeriodsLevel to set
	 */
	public void setApplyAllToPeriodsLevel(boolean applyAllToPeriodsLevel) {
		this.applyAllToPeriodsLevel = applyAllToPeriodsLevel;
	}

	// ===================================================================  
	// Infos Session Getters / Setters
	// ===================================================================
	/**
	 * [PeriodeBB.codeEtablissement] Getter 
	 * @author rlaib on : 6 oct. 2014  11:45:41
	 * @return the codeEtablissement
	 */
	public String getCodeEtablissement() {
		return codeEtablissement;
	}

	/**
	 * [PeriodeBB.codeEtablissement] Setter 
	 * @author rlaib on : 6 oct. 2014  11:45:41
	 * @param codeEtablissement the codeEtablissement to set
	 */
	public void setCodeEtablissement(String codeEtablissement) {
		this.codeEtablissement = codeEtablissement;
	}

	/**
	 * [PeriodeBB.newEtabCode] Getter 
	 * @author rlaib on : 6 oct. 2014  11:45:41
	 * @return the newEtabCode
	 */
	public String getNewEtabCode() {
		return newEtabCode;
	}

	/**
	 * [PeriodeBB.newEtabCode] Setter 
	 * @author rlaib on : 6 oct. 2014  11:45:41
	 * @param newEtabCode the newEtabCode to set
	 */
	public void setNewEtabCode(String newEtabCode) {
		this.newEtabCode = newEtabCode;
	}

	/**
	 * [PeriodeBB.libelleEtab] Getter 
	 * @author rlaib on : 6 oct. 2014  11:45:41
	 * @return the libelleEtab
	 */
	public String getLibelleEtab() {
		return libelleEtab;
	}

	/**
	 * [PeriodeBB.libelleEtab] Setter 
	 * @author rlaib on : 6 oct. 2014  11:45:41
	 * @param libelleEtab the libelleEtab to set
	 */
	public void setLibelleEtab(String libelleEtab) {
		this.libelleEtab = libelleEtab;
	}

	/**
	 * [PeriodeBB.idYear] Getter 
	 * @author rlaib on : 6 oct. 2014  11:45:41
	 * @return the idYear
	 */
	public Integer getIdYear() {
		return idYear;
	}

	/**
	 * [PeriodeBB.idYear] Setter 
	 * @author rlaib on : 6 oct. 2014  11:45:41
	 * @param idYear the idYear to set
	 */
	public void setIdYear(Integer idYear) {
		this.idYear = idYear;
	}

	/**
	 * [PeriodeBB.libelleYear] Getter 
	 * @author rlaib on : 6 oct. 2014  11:45:41
	 * @return the libelleYear
	 */
	public String getLibelleYear() {
		return libelleYear;
	}

	/**
	 * [PeriodeBB.libelleYear] Setter 
	 * @author rlaib on : 6 oct. 2014  11:45:41
	 * @param libelleYear the libelleYear to set
	 */
	public void setLibelleYear(String libelleYear) {
		this.libelleYear = libelleYear;
	}

	// ===================================================================  
	// Properties Model Getters / Setters
	// ===================================================================
	/**
	 * [PeriodeBB.listTypesFormation] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @return the listTypesFormation
	 */
	public List<SelectItem> getListTypesFormation() {
		return listTypesFormation;
	}

	/**
	 * [PeriodeBB.listTypesFormation] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @param listTypesFormation the listTypesFormation to set
	 */
	public void setListTypesFormation(List<SelectItem> listTypesFormation) {
		this.listTypesFormation = listTypesFormation;
	}

	/**
	 * [PeriodeBB.listTypesPeriodicites] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @return the listTypesPeriodicites
	 */
	public List<SelectItem> getListTypesPeriodicites() {
		return listTypesPeriodicites;
	}

	/**
	 * [PeriodeBB.listTypesPeriodicites] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @param listTypesPeriodicites the listTypesPeriodicites to set
	 */
	public void setListTypesPeriodicites(List<SelectItem> listTypesPeriodicites) {
		this.listTypesPeriodicites = listTypesPeriodicites;
	}

	/**
	 * [PeriodeBB.listTypesCycles] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @return the listTypesCycles
	 */
	public List<SelectItem> getListTypesCycles() {
		return listTypesCycles;
	}

	/**
	 * [PeriodeBB.listTypesCycles] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @param listTypesCycles the listTypesCycles to set
	 */
	public void setListTypesCycles(List<SelectItem> listTypesCycles) {
		this.listTypesCycles = listTypesCycles;
	}

	/**
	 * [PeriodeBB.listTypesNiveaux] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @return the listTypesNiveaux
	 */
	public List<SelectItem> getListTypesNiveaux() {
		return listTypesNiveaux;
	}

	/**
	 * [PeriodeBB.listTypesNiveaux] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @param listTypesNiveaux the listTypesNiveaux to set
	 */
	public void setListTypesNiveaux(List<SelectItem> listTypesNiveaux) {
		this.listTypesNiveaux = listTypesNiveaux;
	}

	/**
	 * [PeriodeBB.selectedDetailPeriodTitle] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @return the selectedDetailPeriodTitle
	 */
	public String getSelectedDetailPeriodTitle() {
		return selectedDetailPeriodTitle;
	}

	/**
	 * [PeriodeBB.selectedDetailPeriodTitle] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @param selectedDetailPeriodTitle the selectedDetailPeriodTitle to set
	 */
	public void setSelectedDetailPeriodTitle(String selectedDetailPeriodTitle) {
		this.selectedDetailPeriodTitle = selectedDetailPeriodTitle;
	}

	/**
	 * [PeriodeBB.listPeriods] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @return the listPeriods
	 */
	public List<PeriodeDto> getListPeriods() {
		return listPeriods;
	}

	/**
	 * [PeriodeBB.listPeriods] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @param listPeriods the listPeriods to set
	 */
	public void setListPeriods(List<PeriodeDto> listPeriods) {
		this.listPeriods = listPeriods;
	}

	/**
	 * [PeriodeBB.showPeriodDetails] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @return the showPeriodDetails
	 */
	public boolean isShowPeriodDetails() {
	
		return showPeriodDetails;
	}

	/**
	 * [PeriodeBB.showPeriodDetails] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @param showPeriodDetails the showPeriodDetails to set
	 */
	public void setShowPeriodDetails(boolean showPeriodDetails) {
		this.showPeriodDetails = showPeriodDetails;
	}

	
	/**
	 * [PeriodeBB.showParamPeriodDetails] Getter 
	 * @author rlaib on : 6 oct. 2014  18:43:03
	 * @return the showParamPeriodDetails
	 */
	public boolean isShowParamPeriodDetails() {
		
		try {
			showParamPeriodDetails = (periodParamId != null && !periodParamId.trim().isEmpty() && !periodParamId.equals("null") && (Integer.parseInt(periodParamId)>=0));
		}
		catch (Exception e) {
	
			showParamPeriodDetails = false;
		}
		return showParamPeriodDetails;
	}

	/**
	 * [PeriodeBB.showParamPeriodDetails] Setter 
	 * @author rlaib on : 6 oct. 2014  18:43:03
	 * @param showParamPeriodDetails the showParamPeriodDetails to set
	 */
	public void setShowParamPeriodDetails(boolean showParamPeriodDetails) {
		this.showParamPeriodDetails = showParamPeriodDetails;
	}

	/**
	 * [PeriodeBB.showBtnSave] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @return the showBtnSave
	 */
	public boolean isShowBtnSave() {
		
		return showBtnSave;
	}

	/**
	 * [PeriodeBB.showBtnSave] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @param showBtnSave the showBtnSave to set
	 */
	public void setShowBtnSave(boolean showBtnSave) {
		this.showBtnSave = showBtnSave;
	}

	
	/**
	 * [PeriodeBB.showBtnRemove] Getter 
	 * @author rlaib on : 22 juil. 2014  15:41:15
	 * @return the showBtnRemove
	 */
	public boolean isShowBtnRemove() {
		return showBtnRemove;
	}

	/**
	 * [PeriodeBB.showBtnRemove] Setter 
	 * @author rlaib on : 22 juil. 2014  15:41:15
	 * @param showBtnRemove the showBtnRemove to set
	 */
	public void setShowBtnRemove(boolean showBtnRemove) {
		this.showBtnRemove = showBtnRemove;
	}

	/**
	 * [PeriodeBB.selectedPeriod] Getter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @return the selectedPeriod
	 */
	public PeriodeDto getSelectedPeriod() {
		return selectedPeriod;
	}

	/**
	 * [PeriodeBB.selectedPeriod] Setter 
	 * @author  Rafik LAIB on : 22 juil. 2014  06:40:18
	 * @param selectedPeriod the selectedPeriod to set
	 */
	public void setSelectedPeriod(PeriodeDto selectedPeriod) {
		this.selectedPeriod = selectedPeriod;
	}
	
	/**
	 * [PeriodeBB.periodeParams] Getter 
	 * @author rlaib on : 6 oct. 2014  11:02:44
	 * @return the periodeParams
	 */
	public List<PeriodeParamDto> getPeriodeParams() {
		
		if(periodeParams ==  null)
			periodeParams = new ArrayList<PeriodeParamDto>();
		return periodeParams;
	}

	/**
	 * [PeriodeBB.periodeParams] Setter 
	 * @author rlaib on : 6 oct. 2014  11:02:44
	 * @param periodeParams the periodeParams to set
	 */
	public void setPeriodeParams(List<PeriodeParamDto> periodeParams) {
		this.periodeParams = periodeParams;
	}

	/**
	 * [PeriodeBB.selectedPeriodeParam] Getter 
	 * @author rlaib on : 6 oct. 2014  11:02:44
	 * @return the selectedPeriodeParam
	 */
	public PeriodeParamDto getSelectedPeriodeParam() {
		return selectedPeriodeParam;
	}

	/**
	 * [PeriodeBB.selectedPeriodeParam] Setter 
	 * @author rlaib on : 6 oct. 2014  11:02:44
	 * @param selectedPeriodeParam the selectedPeriodeParam to set
	 */
	public void setSelectedPeriodeParam(PeriodeParamDto selectedPeriodeParam) {
		this.selectedPeriodeParam = selectedPeriodeParam;
	}
	
	/**
	 * [PeriodeBB.selectedPeriodiciteCode] Getter 
	 * @author rlaib on : 7 déc. 2014  17:03:37
	 * @return the selectedPeriodiciteCode
	 */
	public String getSelectedPeriodiciteCode() {
		return selectedPeriodiciteCode;
	}

	/**
	 * [PeriodeBB.selectedPeriodiciteCode] Setter 
	 * @author rlaib on : 7 déc. 2014  17:03:37
	 * @param selectedPeriodiciteCode the selectedPeriodiciteCode to set
	 */
	public void setSelectedPeriodiciteCode(String selectedPeriodiciteCode) {
		this.selectedPeriodiciteCode = selectedPeriodiciteCode;
	}

	/**
	 * [PeriodeBB.selectedPeriodeId] Getter 
	 * @author rlaib on : 7 déc. 2014  17:25:49
	 * @return the selectedPeriodeId
	 */
	public Integer getSelectedPeriodeId() {
		return selectedPeriodeId;
	}

	/**
	 * [PeriodeBB.selectedPeriodeId] Setter 
	 * @author rlaib on : 7 déc. 2014  17:25:49
	 * @param selectedPeriodeId the selectedPeriodeId to set
	 */
	public void setSelectedPeriodeId(Integer selectedPeriodeId) {
		this.selectedPeriodeId = selectedPeriodeId;
	}

	/**
	 * [PeriodeBB.listPeriodesParPeriodicite] Getter 
	 * @author rlaib on : 7 déc. 2014  17:03:37
	 * @return the listPeriodesParPeriodicite
	 */
	public List<SelectItem> getListPeriodesParPeriodicite() {
		return listPeriodesParPeriodicite;
	}

	/**
	 * [PeriodeBB.listPeriodesParPeriodicite] Setter 
	 * @author rlaib on : 7 déc. 2014  17:03:37
	 * @param listPeriodesParPeriodicite the listPeriodesParPeriodicite to set
	 */
	public void setListPeriodesParPeriodicite(
			List<SelectItem> listPeriodesParPeriodicite) {
		this.listPeriodesParPeriodicite = listPeriodesParPeriodicite;
	}

	/**
	 * [PeriodeBB.listTypesParams] Getter 
	 * @author rlaib on : 6 oct. 2014  16:54:50
	 * @return the listTypesParams
	 */
	public List<SelectItem> getListTypesParams() {
		return listTypesParams;
	}

	/**
	 * [PeriodeBB.listTypesParams] Setter 
	 * @author rlaib on : 6 oct. 2014  16:54:50
	 * @param listTypesParams the listTypesParams to set
	 */
	public void setListTypesParams(List<SelectItem> listTypesParams) {
		this.listTypesParams = listTypesParams;
	}

	// ===================================================================  
	// ActionListener
	// ===================================================================
	public void addPeriod() {
		try {
			selectedPeriod = new PeriodeDto();
			selectedPeriod.setRang(getMaxCycleRangs(listPeriods) + 1);
			periodId = "0";
			canAddParam = false;
			periodeParams = null;
			showPeriodDetails = true;
			showBtnSave = true;
			showBtnRemove = false;
			selectedDetailPeriodTitle= MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.PERIOD_EDIT_BUNDLE_KEY_EDIT_PAGE_PANEL_NEW_TITLE);

		}
		 catch(Exception e){
			 e.printStackTrace();
		 }
	}
	public void removePeriod() {
			try {
				
						periodeService.remove(Integer.parseInt(periodId)) ;
						listPeriods = periodeService.findByLevelId(Integer.parseInt(levelPeriodId));
						periodId = null;
						showPeriodDetails = false;
						Object[] params = {selectedPeriod.getLibelleLongLt()};
						MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
								,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.PERIOD_EDIT_BUNDLE_KEY_EDIT_PAGE_REMOVE_SUCCESS, params,"fr")); 
	 	
		}
		 catch(Exception e){
			 	Object[] params = {selectedPeriod.getLibelleLongLt()};
				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
						,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.PERIOD_EDIT_BUNDLE_KEY_EDIT_PAGE_REMOVE_FAILURE, params,"fr")); 
			 
		 }
	}
	public void savePeriod() {
		
		try {
			
				if(checkRangeAndUnicityLevel(listPeriods)) {
						selectedPeriod.setRefCodePeriodicite(selectedPeriodiciteCode);
						selectedPeriod.setNcPeriodeId(selectedPeriodeId);
						selectedPeriod.setIdNiveau(Integer.parseInt(levelPeriodId));
						selectedPeriod = periodeService.insertOrUpdate(selectedPeriod) ;
						periodId = String.valueOf(selectedPeriod.getId());
						listPeriods = periodeService.findByLevelId(Integer.parseInt(levelPeriodId));
						periodeParams = periodeService.findPeriodParamByPeriodByYear(this.idYear, Integer.parseInt(periodId));
						periodParamId ="0";
						canAddParam = true;
						showBtnSave = true;
						showBtnRemove = true;
						MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
																				,MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_SAVE_SUCCESS));
				}
		}
		 catch(Exception e){
				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
						,MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_SAVE_FAILURE)); 
			 
		 }
	}
	public void removePeriodParam() {
		try {
					if(periodParamId == null)
						return;
					periodeService.removePeriodeParam(Integer.parseInt(periodParamId));
		 			periodeParams = periodeService.findPeriodParamByPeriodByYear(this.idYear, Integer.parseInt(periodId));
					selectedPeriodeParam = new PeriodeParamDto();
					
					MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
							,MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.PERIOD_PARAM_EDIT_BUNDLE_KEY_EDIT_REMOVE_SUCCESS));
		}
	 	catch(Exception e){
	 		
			MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
					,MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.PERIOD_PARAM_EDIT_BUNDLE_KEY_EDIT_REMOVE_FAILURE)); 

	 	}
		
	}
	public void savePeriodParam() {
		try {
			
				if(selectedPeriodeParam == null 
						|| selectedPeriodeParam.getCode() == null
						|| selectedPeriodeParam.getLibelle() == null
						)
					return;
		
				RequestContext context = RequestContext.getCurrentInstance();
				PeriodeParamDto checkExisitingPeriodeParamDto = periodeService.findParamByCodeByPeriodByYear(selectedPeriodeParam.getCode(), this.idYear, Integer.parseInt(this.periodId)) ;
				if(checkExisitingPeriodeParamDto != null && selectedPeriodeParam.getId() <= 0) {
					
					Object[] params = {this.selectedPeriodeParam.getCode()};
					MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.PERIOD_PARAM_EDIT_BUNDLE_KEY_PARAM_TAB_TITLE)
							, MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.PERIOD_PARAM_EDIT_BUNDLE_KEY_ADD_EXISTING_CODE, params,"fr"));
					context.addCallbackParam("isValid", false);
					return;
				}
				selectedPeriodeParam.setIdAnneeAcademique(this.idYear);
				selectedPeriodeParam.setIdPeriode(selectedPeriod.getId());
				selectedPeriodeParam.setValeurParDefaut(selectedPeriodeParam.getValeur());
				if(this.applyAllToPeriodsLevel) {
					
					for (SelectItem _itemNiveau : listTypesNiveaux) {
						
						Integer _idNiveau = Integer.parseInt(_itemNiveau.getValue().toString());
						List<PeriodeDto> periodsLevel = periodeService.findByLevelId(_idNiveau);
						for (PeriodeDto periodeDto : periodsLevel) {
							
							PeriodeParamDto periodeParamDto = null;
							if(selectedPeriodeParam != null){
								 periodeParamDto = periodeService.findParamByCodeByPeriodByYear(selectedPeriodeParam.getCode(), this.idYear, periodeDto.getId());
							}
							if(periodeParamDto == null) {
									periodeParamDto = new PeriodeParamDto();
									periodeParamDto.setCode(selectedPeriodeParam.getCode());
									periodeParamDto.setLibelle(selectedPeriodeParam.getLibelle());
									periodeParamDto.setValeur(selectedPeriodeParam.getValeur());
									periodeParamDto.setValeurParDefaut(selectedPeriodeParam.getValeurParDefaut());
									periodeParamDto.setIdAnneeAcademique(selectedPeriodeParam.getIdAnneeAcademique());
									periodeParamDto.setIdTypeParam(selectedPeriodeParam.getIdTypeParam());
									periodeParamDto.setIdPeriode(periodeDto.getId());
							}
							periodeService.insertOrUpdate(periodeParamDto);
							
						}
					}
							
				}
				else
					periodeService.insertOrUpdate(selectedPeriodeParam);
				
				selectedPeriodeParam = new PeriodeParamDto();
				periodeParams = periodeService.findPeriodParamByPeriodByYear(this.idYear, Integer.parseInt(periodId));
				context.addCallbackParam("isValid", true);
			MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
					,MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.PERIOD_PARAM_EDIT_BUNDLE_KEY_EDIT_SAVE_SUCCESS));
			
		}
		catch(Exception e){
			
			MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
					,MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.PERIOD_PARAM_EDIT_BUNDLE_KEY_EDIT_SAVE_FAILURE)); 
			
		}
		
	}
	public void removeParamPeriode(){
		
		try {
			if(this.periodParamId != null) {
					// Supprimer le Parametre Periode
						periodeService.removePeriodeParam(Integer.parseInt(periodParamId));
						periodeParams = periodeService.findPeriodParamByPeriodByYear(this.idYear, Integer.parseInt(periodId));
			}
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	// ===================================================================  
	// Listeners
	// ===================================================================
	/**
	 * [PeriodeBB.handleTypesFormationFilterChange] Method 
	 * @author rlaib  on : 11 oct. 2014  11:48:14
	 * @param event
	 */
	public void  handleTypesFormationFilterChange(AjaxBehaviorEvent event) {
		
		if ((typeFormationPeriodCode!= null && !typeFormationPeriodCode.trim().isEmpty() && !typeFormationPeriodCode.equals("null"))) {
				initCycleByTypeFormation(typeFormationPeriodCode);
				showPeriodDetails = false;
				showBtnRemove = false;
				showBtnSave = false;
		}
	
	}
	
	/**
	 * [PeriodeBB.initCycleByTypeFormation] Method 
	 * @author rlaib  on : 23 nov. 2014  17:12:40
	 * @param typeFormationPeriodCode2
	 */
	private void initCycleByTypeFormation(String typeFormationCode) {
		
		listTypesCycles = cycleDtosToSelectItems(cycleService.findByTypeFormationCode(typeFormationCode));
			if(listTypesCycles != null && listTypesCycles.size()>0) {
				
				cyclePeriodId = listTypesCycles.get(0).getValue().toString();
				listTypesNiveaux = niveauDtosToSelectItems(niveauService.findByCycleId(Integer.parseInt(cyclePeriodId)));
						if(listTypesNiveaux != null && listTypesNiveaux.size()>0) {
						levelPeriodId =  listTypesNiveaux.get(0).getValue().toString();
						listPeriods = periodeService.findByLevelId(Integer.parseInt(levelPeriodId));
					}
					else
						listPeriods = null;
			}
			
			periodId = null;
		
	}

	/**
	 * [PeriodeBB.handleCyclesFilterChange] Method 
	 * @author rlaib  on : 11 oct. 2014  11:48:24
	 * @param event
	 */
	public void  handleCyclesFilterChange(AjaxBehaviorEvent event) {
		
		if ((cyclePeriodId!= null && !cyclePeriodId.trim().isEmpty() && !cyclePeriodId.equals("null"))) {
				
				listTypesNiveaux = niveauDtosToSelectItems(niveauService.findByCycleId(Integer.parseInt(cyclePeriodId)));
				if(listTypesNiveaux != null && listTypesNiveaux.size()>0) {
				levelPeriodId =  listTypesNiveaux.get(0).getValue().toString();
				listPeriods = periodeService.findByLevelId(Integer.parseInt(levelPeriodId));
			}
			else
				listPeriods = new ArrayList<PeriodeDto>();
			periodId = null;
			showPeriodDetails = false;
			showBtnRemove = false;
			showBtnSave = false;
		}
	}
	/**
	 * [PeriodeBB.handleLevelsFilterChange] Method 
	 * @author rlaib  on : 11 oct. 2014  11:48:09
	 * @param event
	 */
	public void  handleLevelsFilterChange(AjaxBehaviorEvent event) {
		
		if ((levelPeriodId!= null && !levelPeriodId.trim().isEmpty() && !levelPeriodId.equals("null"))) {
			
			listPeriods = periodeService.findByLevelId(Integer.parseInt(levelPeriodId));
			periodId = null;
			NiveauDto oneLevel = niveauService.findById(Integer.parseInt(levelPeriodId));
			if(oneLevel != null) {
				cyclePeriodId = String.valueOf(oneLevel.getIdCycle());
				CycleDto oneCycle = cycleService.findById(Integer.parseInt(cyclePeriodId));
				if(oneCycle != null)
					typeFormationPeriodCode = oneCycle.getRefCodeTypeFormation();
			}
			periodId = null;
			showPeriodDetails = false;
			showBtnRemove = false;
			showBtnSave = false;
			
		}
		
	}
	/**
	 * [PeriodeBB.handleParamsTypesChange] Method 
	 * @author rlaib  on : 11 oct. 2014  11:48:04
	 * @param event
	 */
	public void  handleParamsTypesChange(AjaxBehaviorEvent event) {
		if (selectedPeriodeParam != null) {
				
			ParamTypeDto  paramType = periodeService.findParamTypeById(selectedPeriodeParam.getIdTypeParam());
			selectedPeriodeParam.setMasqueTypeParam(paramType.getMasque());
		}
	}

	public void onPeriodRowSelect(SelectEvent event) {

		try {
			listPeriodesParPeriodicite=  initPeriodesParPeriodicite(selectedPeriod.getRefCodePeriodicite());
			loadPeriodDetails();
		}
		catch (Exception e)  {
			e.printStackTrace();
		}

	}
	
	public void handlePeriodiciteChange(AjaxBehaviorEvent event) {
		
		if (selectedPeriodiciteCode!= null) {
			listPeriodesParPeriodicite= initPeriodesParPeriodicite(selectedPeriodiciteCode);
		}
	}
	
	// ===================================================================  
	// Actions and Methods
	// ===================================================================
	/**
	 * [PeriodeBB.goBack] Method 
	 * @author rlaib  on : 11 oct. 2014  11:47:28
	 * @return
	 */
	public String goBack() {
		
		return this.outcomePage;
	}
	/**
	 * [PeriodeBB.editPeriode] Method 
	 * @author rlaib  on : 11 oct. 2014  11:47:24
	 */
	public void editPeriode() {
		
		try {
	 			loadPeriodDetails();
		}
		catch (Exception ex)  {
			
		}
	}
	/**
	 * [PeriodeBB.prepareParamDetails] Method 
	 * @author rlaib  on : 11 oct. 2014  11:47:17
	 */
	public void prepareParamDetails(){
		
		try {
					
					initListTypesParams();
					if(this.periodParamId != null && !this.periodParamId.equals("null")) {
						this.selectedPeriodeParam = periodeService.findPeriodParamById(Integer.parseInt(periodParamId));
					}
					else {
									this.selectedPeriodeParam = new PeriodeParamDto();
									if(listTypesParams != null)  {
										Integer defaultParamTypeId = Integer.parseInt(String.valueOf(listTypesParams.get(0).getValue()));
										ParamTypeDto  defaultParamType = periodeService.findParamTypeById(defaultParamTypeId);
										selectedPeriodeParam.setIdTypeParam(defaultParamType.getId());
										selectedPeriodeParam.setMasqueTypeParam(defaultParamType.getMasque());
									}
					}
				}
				catch (Exception e) {
					
					e.printStackTrace();
				}
	}
	
	// ===================================================================  
	// Functions Helper
	// ===================================================================
	/**
	 * [PeriodeBB.getMaxCycleRangs] Method 
	 * @author rlaib  on : 11 oct. 2014  11:44:03
	 * @param list
	 * @return
	 */
	private int getMaxCycleRangs(List<PeriodeDto> list) {
		int maxRang = 0;
		if(list != null && list.size() > 0) {
			maxRang = list.get(0).getRang();
			
			for (PeriodeDto _priodeDto : list) {
				if(_priodeDto.getRang() > maxRang)
					maxRang = _priodeDto.getRang();
			} 		
		}
		return maxRang;
	}
	/**
	 * [PeriodeBB.checkRangeAndUnicityLevel] Method 
	 * @author rlaib  on : 11 oct. 2014  11:46:42
	 * @param periods
	 * @return
	 */
	private boolean checkRangeAndUnicityLevel(List<PeriodeDto> periods) {
		
		boolean existingRange = false;
		boolean existingLabel = false;
		boolean existingNewRange = false;
		boolean existingNewCode = false;
		boolean existingNewLabel = false;
		int count = 0;
		int maxRang = 0;
		if(periods != null) {
		for (PeriodeDto _periodeDto : periods) {
			if((selectedPeriod.getId() != _periodeDto.getId())) {
					if(_periodeDto.getRang() == selectedPeriod.getRang()) {
						if((selectedPeriod.getId()>0))
							existingRange = true;
						else
							existingNewRange = true;
							
					}
					if(_periodeDto.getCode().equals(selectedPeriod.getCode())) {
						if((selectedPeriod.getId()>0)) {
						} else
							existingNewCode = true;
					}
					if(_periodeDto.getLibelleLongLt().equals(selectedPeriod.getLibelleLongLt())) {
						if((selectedPeriod.getId()>0))
							existingLabel = true;
						else
							existingNewLabel = true;
					}
			}
		} 	
		count = periods.size();
		maxRang = getMaxCycleRangs(periods);
		}
	
		// Enregistrement d une periode existante
 		if (periodId != null && !periodId.trim().isEmpty() && !periodId.equals("null") &&(Integer.parseInt(periodId)>0)) {
 			if((existingLabel)) {
 					Object[] params = {selectedPeriod.getLibelleLongLt()};
					MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
								,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_LABEL, params,"fr"));
				return false;
			}
 			if((existingRange)) {
 				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
 						,MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_RANGE)); 
 				return false;
 			}
 		}
 	// Enregistrement d une nouvelle periode
 		else {
 	
 			if(existingNewRange) {
 				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
 						,MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_RANGE)); 
 				return false;
 			}
 			// Test si le code est unique
 			if(existingNewCode) {
 					Object[] params = {selectedPeriod.getCode()};
 					MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
 					,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_CODE, params,"fr")); 
 					return false;
 			}
 			// Test si le libelle long Fr est unique
 					if(existingNewLabel) {
 							Object[] params = {selectedPeriod.getLibelleLongLt()};
 							MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
 							,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_LABEL, params,"fr")); 
 							return false;
 			}
 			// Test sur le rang suivant
 					if((selectedPeriod.getRang() - maxRang > 1)) {
 		 				Object[] params = {count + 1};
 		 				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE)
 		 				,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_RANGE_FISRT, params,"fr")); 
 		 				return false;
 		 			}
 		}
		return true;
	}
	/**
	 * [NiveauBB.dtosToSelectItems] Method 
	 * @author  Rafik LAIB  on : 22 juil. 2014  02:14:47
	 * @param findByTypeFormationCode
	 * @return
	 */
	/**
	 * [PeriodeBB.cycleDtosToSelectItems] Method 
	 * @author rlaib  on : 11 oct. 2014  11:46:53
	 * @param dtos
	 * @return
	 */
	private List<SelectItem> cycleDtosToSelectItems(List<CycleDto> dtos) {
			List<SelectItem> result = new ArrayList<SelectItem>();
		try {
	 		for (CycleDto item : dtos) {
	 			result.add(new SelectItem(item.getId(), String.valueOf(item.getRang()) +" - "+ item.getLibelleLongLt()+ " - " +getTypeFormationLibelle(item.getRefCodeTypeFormation(),listTypesFormation)));
			}
	 }
		catch (Exception e) {
			 e.printStackTrace();
		}
		return result;
	}
	/**
	 * [PeriodeBB.niveauDtosToSelectItems] Method 
	 * @author rlaib  on : 11 oct. 2014  11:47:00
	 * @param dtos
	 * @return
	 */
	private List<SelectItem> niveauDtosToSelectItems(List<NiveauDto> dtos) {
			List<SelectItem> result = new ArrayList<SelectItem>();
		try {
	 		for (NiveauDto item : dtos) {
	 			result.add(new SelectItem(item.getId(), String.valueOf(item.getRang()) +" - "+ item.getLibelleLongLt()));
			}
	 }
		catch (Exception e) {
			 e.printStackTrace();
		}
		return result;
	}
	/**
	 * [PeriodeBB.getTypeFormationLibelle] Method 
	 * @author rlaib  on : 11 oct. 2014  11:47:06
	 * @param codeType
	 * @param listTypes
	 * @return
	 */
	private String getTypeFormationLibelle(String codeType, List<SelectItem> listTypes) {
		
		if (listTypes == null)
			return null;
		else {
			for (SelectItem selectItem : listTypes) {
				
				if(selectItem.getValue().toString().equals(codeType))
					return selectItem.getLabel().toString();
			}
		}
		return null;
		
	}
	/**
	 * [PeriodeBB.initSessionInfos] Method 
	 * @author rlaib  on : 11 oct. 2014  11:47:39
	 */
	private void initSessionInfos() {
		try {
			
			 	this.codeEtablissement= this.sessionBean.getAncienCodeEtablissement();
	 			this.libelleEtab=this.sessionBean.getLlLatinEtablissement();
	 			this.newEtabCode = this.sessionBean.getCodeEtablissement();
	 			this.idYear = this.sessionBean.getIdAnneeAcademique();
	 			this.libelleYear = this.sessionBean.getCodeAnneeAcademique();
		 }
		 catch(Exception e){
			 
			 e.printStackTrace();
		 
		 }
	}
	/**
	 * [PeriodeBB.initListTypesParams] Method 
	 * @author rlaib  on : 6 oct. 2014  16:55:38
	 */
	private void initListTypesParams() {

		try {
					List<ParamTypeDto> _list = periodeService.getAllParamsTypes();
					listTypesParams = new ArrayList<SelectItem>();
					for (ParamTypeDto paramTypeDto : _list) {
						listTypesParams.add(new SelectItem(paramTypeDto.getId(), paramTypeDto.getLibelle()));
					}
	 }
	 catch(Exception e){
		 e.printStackTrace();
	 
	 }
		
	}
	/**
	 * [PeriodeBB.loadPeriodDetails] Method 
	 * @author rlaib  on : 11 sept. 2014  11:25:45
	 */
	private void loadPeriodDetails() {
		periodeParams = periodeService.findPeriodParamByPeriodByYear(this.idYear, selectedPeriod.getId());
		selectedPeriodiciteCode = selectedPeriod.getRefCodePeriodicite();
		selectedPeriodeId = selectedPeriod.getNcPeriodeId();
		showPeriodDetails = true;
		showBtnRemove = true;
		showBtnSave = true;
		canAddParam = true;
		Object[] params = {selectedPeriod.getLibelleLongLt()};
		selectedDetailPeriodTitle = MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CYCLE_FORMATION_MESSAGES_FILE_NAME,OfConstants.PERIOD_EDIT_BUNDLE_KEY_EDIT_PAGE_PANEL_TITLE, params,"fr");
	}
	
	private List<SelectItem> initPeriodesParPeriodicite(String codePeriodicite) {
		
		List<SelectItem> result = new ArrayList<SelectItem>();							
		try {
							List<NomenclatureDto> _list = nomenclatureService.findNcCompositeList(OfConstants.NC_NAME_CYCLE_PERIODE_PERIODICITE
									, codePeriodicite, OfConstants.NC_NAME_PERIODE_PAR_PERIODICITE);
							for (NomenclatureDto _nomenclatureDto : _list) {
								result.add(new SelectItem(_nomenclatureDto.getId(),
										_nomenclatureDto.getLibelleLongFr()));
							}
							return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
}
