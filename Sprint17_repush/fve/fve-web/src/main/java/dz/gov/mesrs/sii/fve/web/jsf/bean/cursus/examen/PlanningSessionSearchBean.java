package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.dozer.DozerBeanMapper;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.PlanningSessionDto;
import dz.gov.mesrs.sii.fve.business.service.examen.PlanningSessionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

/**
 * 
 * @author BELDI Jamel  on : 19 oct. 2014  10:04:54
 */
@ManagedBean(name = "planningSessionSearchBean")
@ViewScoped
public class PlanningSessionSearchBean implements Serializable {
	private static final long serialVersionUID = 1L;	
	private ResourceBundle bundleCommon;
	private ResourceBundle examenBundle;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;	
    private PlanningSessionDto planningSessionSearchDto;	
	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> offreFormationList;
	private List<SelectItem> niveauList;
	private List<SelectItem> periodeList;
	private List<SelectItem> typeSessionList;
	@ManagedProperty("#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{periodeService}")
	private PeriodeService periodeService;
	@ManagedProperty("#{niveauService}")
	private NiveauService niveauService;
	@ManagedProperty("#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	@ManagedProperty("#{planningSessionService}")
	private PlanningSessionService planningSessionService;
	private List<PlanningSessionDto> planningSessionList;
	@ManagedProperty("#{examenSessionConsultBean}")
	private ExamenSessionConsultBean examenSessionConsultBean;
	@ManagedProperty("#{planningSessionSaveBean}")
	private PlanningSessionSaveBean planningSessionSaveBean;
	/**
	 * 
	 * [PlanningSessionSearchBean.PlanningSessionSearchBean()] Constructor
	 * @author BELDI Jamel  on : 20 oct. 2014  15:11:39
	 */
	public PlanningSessionSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		examenBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.EXAMEN_BUNDLE_MSG_NAME);
        
	}

	/**
	 * 
	 * [PlanningSessionSearchBean.init] Method 
	 * @author BELDI Jamel  on : 20 oct. 2014  15:11:44
	 */
	@PostConstruct
	public void init() {
		planningSessionSearchDto = new PlanningSessionDto();
		planningSessionSearchDto.setRefCodeEtablissement(sessionBean.getCodeEtablissement());
		planningSessionSearchDto.setAnneeAcademiqueId(sessionBean.getIdAnneeAcademique());
		anneeChanged();
		loadAnneeAcademique();
		loadTypeSessions();
	}

	
	
/**
 * 
 * [PlanningSessionSearchBean.loadAnneeAcademique] Method 
 * @author BELDI Jamel  on : 20 oct. 2014  15:11:49
 */
	private void loadAnneeAcademique() {
		anneeAcademiqueList = utilBean.loadAnneeAcademique();
		
	}
	/**
	 * 
	 * [PlanningSessionSearchBean.loadTypeSessions] Method 
	 * @author BELDI Jamel  on : 20 oct. 2014  15:11:53
	 */
	private void loadTypeSessions() {
		
		typeSessionList = utilBean.loadTypeSessions();
	}

	/**
	 * 
	 * [PlanningSessionSearchBean.anneeChanged] Method 
	 * @author BELDI Jamel  on : 20 oct. 2014  15:11:59
	 */
	public void anneeChanged() {
		planningSessionSearchDto.setOuvertureOffreFormationId(null);
		offreFormationList = utilBean.loadOffreFormationOuverte(planningSessionSearchDto.getAnneeAcademiqueId());
		ofChanged();

	}

	/**
	 * 
	 * [PlanningSessionSearchBean.ofChanged] Method 
	 * @author BELDI Jamel  on : 20 oct. 2014  15:12:03
	 */
	public void ofChanged() {
		planningSessionSearchDto.setNiveauId(null);
		niveauList = utilBean.loadNiveaux(planningSessionSearchDto.getOuvertureOffreFormationId());
		niveauChanged();

	}


	/**
	 * 
	 * [PlanningSessionSearchBean.niveauChanged] Method 
	 * @author BELDI Jamel  on : 20 oct. 2014  15:12:07
	 */
	public void niveauChanged() {
		planningSessionSearchDto.setIdPeriode(null);
		periodeList = utilBean.loadPeriodes(planningSessionSearchDto.getNiveauId());
		periodeChanged();

	}

	

	/**
	 * 
	 * [PlanningSessionSearchBean.periodeChanged] Method 
	 * @author BELDI Jamel  on : 20 oct. 2014  15:12:11
	 */
	public void periodeChanged() {
		searchPlanningSessions();

	}

	/**
	 * 
	 * [PlanningSessionSearchBean.typeSessionChanged] Method 
	 * @author BELDI Jamel  on : 20 oct. 2014  15:12:14
	 */
	public void typeSessionChanged() {
		searchPlanningSessions();

	}

	
public	void searchPlanningSessions(){
	FacesMessage msg = new FacesMessage();
	try {
		planningSessionList = new ArrayList<PlanningSessionDto>();
		planningSessionList = planningSessionService.findAdvanced(planningSessionSearchDto);	
		planningSessionSaveBean.setPlanningSessionDto(null);
		} catch (Exception e) {
			planningSessionList = new ArrayList<PlanningSessionDto>();
			e.printStackTrace();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec"));
			msg.setDetail(bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
}
/**
 * 
 * [PlanningSessionSearchBean.onPlanningSelect] Method 
 * @author BELDI Jamel  on : 29 oct. 2014  14:10:05
 * @param event
 */
public void onPlanningSelect(SelectEvent event) {
	FacesMessage msg = new FacesMessage();
	try{
		PlanningSessionDto _planningSessionDto = ((PlanningSessionDto) event
			.getObject());	
		//consulter
		examenSessionConsultBean.setPlanningSessionSearchDto(_planningSessionDto);
		examenSessionConsultBean.searchExamens();
		//Edition
		planningSessionSaveBean.setPlanningSessionDto(_planningSessionDto);
		planningSessionSaveBean.init();
		planningSessionSaveBean.loadPlanningSession();
		
		
	} catch (Exception e) {
		
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary(bundleCommon.getString("error_echec"));
		msg.setDetail(bundleCommon.getString("error_echec_inconnue"));
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
}

//getter and Setter
/**
 * [PlanningSessionSearchBean.sessionBean] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the sessionBean
 */
public SessionBean getSessionBean() {
	return sessionBean;
}

/**
 * [PlanningSessionSearchBean.sessionBean] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param sessionBean the sessionBean to set
 */
public void setSessionBean(SessionBean sessionBean) {
	this.sessionBean = sessionBean;
}

/**
 * [PlanningSessionSearchBean.utilBean] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the utilBean
 */
public UtilBean getUtilBean() {
	return utilBean;
}

/**
 * [PlanningSessionSearchBean.utilBean] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param utilBean the utilBean to set
 */
public void setUtilBean(UtilBean utilBean) {
	this.utilBean = utilBean;
}

/**
 * [PlanningSessionSearchBean.planningSessionSearchDto] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the planningSessionSearchDto
 */
public PlanningSessionDto getPlanningSessionSearchDto() {
	return planningSessionSearchDto;
}

/**
 * [PlanningSessionSearchBean.planningSessionSearchDto] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param planningSessionSearchDto the planningSessionSearchDto to set
 */
public void setPlanningSessionSearchDto(
		PlanningSessionDto planningSessionSearchDto) {
	this.planningSessionSearchDto = planningSessionSearchDto;
}

/**
 * [PlanningSessionSearchBean.mapper] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the mapper
 */
public DozerBeanMapper getMapper() {
	return mapper;
}

/**
 * [PlanningSessionSearchBean.mapper] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param mapper the mapper to set
 */
public void setMapper(DozerBeanMapper mapper) {
	this.mapper = mapper;
}

/**
 * [PlanningSessionSearchBean.anneeAcademiqueList] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the anneeAcademiqueList
 */
public List<SelectItem> getAnneeAcademiqueList() {
	return anneeAcademiqueList;
}

/**
 * [PlanningSessionSearchBean.anneeAcademiqueList] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param anneeAcademiqueList the anneeAcademiqueList to set
 */
public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
	this.anneeAcademiqueList = anneeAcademiqueList;
}

/**
 * [PlanningSessionSearchBean.offreFormationList] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the offreFormationList
 */
public List<SelectItem> getOffreFormationList() {
	return offreFormationList;
}

/**
 * [PlanningSessionSearchBean.offreFormationList] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param offreFormationList the offreFormationList to set
 */
public void setOffreFormationList(List<SelectItem> offreFormationList) {
	this.offreFormationList = offreFormationList;
}

/**
 * [PlanningSessionSearchBean.niveauList] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the niveauList
 */
public List<SelectItem> getNiveauList() {
	return niveauList;
}

/**
 * [PlanningSessionSearchBean.niveauList] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param niveauList the niveauList to set
 */
public void setNiveauList(List<SelectItem> niveauList) {
	this.niveauList = niveauList;
}

/**
 * [PlanningSessionSearchBean.periodeList] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the periodeList
 */
public List<SelectItem> getPeriodeList() {
	return periodeList;
}

/**
 * [PlanningSessionSearchBean.periodeList] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param periodeList the periodeList to set
 */
public void setPeriodeList(List<SelectItem> periodeList) {
	this.periodeList = periodeList;
}

/**
 * [PlanningSessionSearchBean.typeSessionList] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the typeSessionList
 */
public List<SelectItem> getTypeSessionList() {
	return typeSessionList;
}

/**
 * [PlanningSessionSearchBean.typeSessionList] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param typeSessionList the typeSessionList to set
 */
public void setTypeSessionList(List<SelectItem> typeSessionList) {
	this.typeSessionList = typeSessionList;
}

/**
 * [PlanningSessionSearchBean.anneeAcademiqueService] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the anneeAcademiqueService
 */
public AnneeAcademiqueService getAnneeAcademiqueService() {
	return anneeAcademiqueService;
}

/**
 * [PlanningSessionSearchBean.anneeAcademiqueService] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param anneeAcademiqueService the anneeAcademiqueService to set
 */
public void setAnneeAcademiqueService(
		AnneeAcademiqueService anneeAcademiqueService) {
	this.anneeAcademiqueService = anneeAcademiqueService;
}

/**
 * [PlanningSessionSearchBean.ouvertureOffreFormationService] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the ouvertureOffreFormationService
 */
public OuvertureOffreFormationService getOuvertureOffreFormationService() {
	return ouvertureOffreFormationService;
}

/**
 * [PlanningSessionSearchBean.ouvertureOffreFormationService] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param ouvertureOffreFormationService the ouvertureOffreFormationService to set
 */
public void setOuvertureOffreFormationService(
		OuvertureOffreFormationService ouvertureOffreFormationService) {
	this.ouvertureOffreFormationService = ouvertureOffreFormationService;
}

/**
 * [PlanningSessionSearchBean.periodeService] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the periodeService
 */
public PeriodeService getPeriodeService() {
	return periodeService;
}

/**
 * [PlanningSessionSearchBean.periodeService] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param periodeService the periodeService to set
 */
public void setPeriodeService(PeriodeService periodeService) {
	this.periodeService = periodeService;
}

/**
 * [PlanningSessionSearchBean.niveauService] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the niveauService
 */
public NiveauService getNiveauService() {
	return niveauService;
}

/**
 * [PlanningSessionSearchBean.niveauService] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param niveauService the niveauService to set
 */
public void setNiveauService(NiveauService niveauService) {
	this.niveauService = niveauService;
}

/**
 * [PlanningSessionSearchBean.nomenclatureService] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the nomenclatureService
 */
public NomenclatureService getNomenclatureService() {
	return nomenclatureService;
}

/**
 * [PlanningSessionSearchBean.nomenclatureService] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param nomenclatureService the nomenclatureService to set
 */
public void setNomenclatureService(NomenclatureService nomenclatureService) {
	this.nomenclatureService = nomenclatureService;
}

/**
 * [PlanningSessionSearchBean.planningSessionService] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the planningSessionService
 */
public PlanningSessionService getPlanningSessionService() {
	return planningSessionService;
}

/**
 * [PlanningSessionSearchBean.planningSessionService] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param planningSessionService the planningSessionService to set
 */
public void setPlanningSessionService(
		PlanningSessionService planningSessionService) {
	this.planningSessionService = planningSessionService;
}

/**
 * [PlanningSessionSearchBean.planningSessionList] Getter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @return the planningSessionList
 */
public List<PlanningSessionDto> getPlanningSessionList() {
	return planningSessionList;
}

/**
 * [PlanningSessionSearchBean.planningSessionList] Setter 
 * @author BELDI Jamel on : 20 oct. 2014  15:13:53
 * @param planningSessionList the planningSessionList to set
 */
public void setPlanningSessionList(List<PlanningSessionDto> planningSessionList) {
	this.planningSessionList = planningSessionList;
}

/**
 * [PlanningSessionSearchBean.examenSessionConsultBean] Getter 
 * @author BELDI Jamel on : 29 oct. 2014  14:24:31
 * @return the examenSessionConsultBean
 */
public ExamenSessionConsultBean getExamenSessionConsultBean() {
	return examenSessionConsultBean;
}

/**
 * [PlanningSessionSearchBean.examenSessionConsultBean] Setter 
 * @author BELDI Jamel on : 29 oct. 2014  14:24:31
 * @param examenSessionConsultBean the examenSessionConsultBean to set
 */
public void setExamenSessionConsultBean(
		ExamenSessionConsultBean examenSessionConsultBean) {
	this.examenSessionConsultBean = examenSessionConsultBean;
}

/**
 * [PlanningSessionSearchBean.planningSessionSaveBean] Getter 
 * @author BELDI Jamel on : 29 oct. 2014  15:10:50
 * @return the planningSessionSaveBean
 */
public PlanningSessionSaveBean getPlanningSessionSaveBean() {
	return planningSessionSaveBean;
}

/**
 * [PlanningSessionSearchBean.planningSessionSaveBean] Setter 
 * @author BELDI Jamel on : 29 oct. 2014  15:10:50
 * @param planningSessionSaveBean the planningSessionSaveBean to set
 */
public void setPlanningSessionSaveBean(
		PlanningSessionSaveBean planningSessionSaveBean) {
	this.planningSessionSaveBean = planningSessionSaveBean;
}
	
	

	
      
    
    
}
