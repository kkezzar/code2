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

import org.dozer.DozerBeanMapper;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionByDateDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.PlanningSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.SalleExamenDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.ExamenSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.PlanningSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.SalleExamenService;
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
@ManagedBean(name = "examenSessionConsultBean")
@ViewScoped
public class ExamenSessionConsultBean implements Serializable {
	private static final long serialVersionUID = 1L;	
	private ResourceBundle bundleCommon;
	private ResourceBundle examenBundle;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{examenSessionService}")
	private ExamenSessionService examenSessionService;
    private PlanningSessionDto planningSessionSearchDto;
	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;

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
	@ManagedProperty("#{salleExamenService}")
	private SalleExamenService salleExamenService;
	private List<SalleExamenDto> salleExamenList;
	@ManagedProperty("#{planningSessionService}")
	private PlanningSessionService planningSessionService;
	/**
	 * 
	 * [ExamenSessionConsultBean.ExamenSessionConsultBean()] Constructor
	 * @author BELDI Jamel  on : 19 oct. 2014  10:04:36
	 */
	public ExamenSessionConsultBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		examenBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.EXAMEN_BUNDLE_MSG_NAME);
        
	}

	/**
	 * 
	 * [ExamenSessionConsultBean.init] Method 
	 * @author BELDI Jamel  on : 19 oct. 2014  10:04:32
	 */
	@PostConstruct
	public void init() {
		planningSessionSearchDto = new PlanningSessionDto();
	}

	
	

	

	

	public void onPlanningSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage();
		try{
		planningSessionSearchDto = ((PlanningSessionDto) event
				.getObject());	
		searchExamens();
		} catch (Exception e) {
			
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec"));
			msg.setDetail(bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}
	
public	void searchExamens(){
	try {

        salleExamenList = new ArrayList<SalleExamenDto>();
        if(planningSessionSearchDto==null || planningSessionSearchDto.getId()==0 ){
        	return;
        }       
        //PlanningSessionDto _planningSessionDto = planningSessionService.findById(planningSessionSearchDto.getId());//.findByYearAndOfAndLevelAndPeriodAndTypeSession(planningSessionSearchDto);
       
        salleExamenList = salleExamenService.findBySession(planningSessionSearchDto.getId(), true, true);            
        
		} catch (Exception e) {
			 salleExamenList = new ArrayList<SalleExamenDto>();
			e.printStackTrace();
		}
}
	
	
//getter and Setter
	
	/**
	 * [ExamenSessionConsultBean.sessionBean] Getter 
	 * @author BELDI Jamel on : 19 oct. 2014  10:05:32
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	
	/**
	 * [ExamenSessionConsultBean.sessionBean] Setter 
	 * @author BELDI Jamel on : 19 oct. 2014  10:05:32
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	

	/**
	 * [ExamenSessionConsultBean.planningSessionSearchDto] Getter 
	 * @author BELDI Jamel on : 19 oct. 2014  11:17:59
	 * @return the planningSessionSearchDto
	 */
	public PlanningSessionDto getPlanningSessionSearchDto() {
		return planningSessionSearchDto;
	}

	/**
	 * [ExamenSessionConsultBean.planningSessionSearchDto] Setter 
	 * @author BELDI Jamel on : 19 oct. 2014  11:17:59
	 * @param planningSessionSearchDto the planningSessionSearchDto to set
	 */
	public void setPlanningSessionSearchDto(
			PlanningSessionDto planningSessionSearchDto) {
		this.planningSessionSearchDto = planningSessionSearchDto;
	}

	/**
	 * [ExamenSessionConsultBean.utilBean] Getter 
	 * @author BELDI Jamel on : 19 oct. 2014  10:15:31
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [ExamenSessionConsultBean.utilBean] Setter 
	 * @author BELDI Jamel on : 19 oct. 2014  10:15:31
	 * @param utilBean the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [ExamenSessionConsultBean.examenSessionService] Getter 
	 * @author BELDI Jamel on : 19 oct. 2014  10:55:07
	 * @return the examenSessionService
	 */
	public ExamenSessionService getExamenSessionService() {
		return examenSessionService;
	}

	/**
	 * [ExamenSessionConsultBean.examenSessionService] Setter 
	 * @author BELDI Jamel on : 19 oct. 2014  10:55:07
	 * @param examenSessionService the examenSessionService to set
	 */
	public void setExamenSessionService(ExamenSessionService examenSessionService) {
		this.examenSessionService = examenSessionService;
	}

	

	/**
	 * [ExamenSessionConsultBean.mapper] Getter 
	 * @author BELDI Jamel on : 19 oct. 2014  11:19:59
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [ExamenSessionConsultBean.mapper] Setter 
	 * @author BELDI Jamel on : 19 oct. 2014  11:19:59
	 * @param mapper the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	

	/**
	 * [ExamenSessionConsultBean.anneeAcademiqueService] Getter 
	 * @author BELDI Jamel on : 19 oct. 2014  19:12:52
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [ExamenSessionConsultBean.anneeAcademiqueService] Setter 
	 * @author BELDI Jamel on : 19 oct. 2014  19:12:52
	 * @param anneeAcademiqueService the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [ExamenSessionConsultBean.ouvertureOffreFormationService] Getter 
	 * @author BELDI Jamel on : 19 oct. 2014  19:12:52
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [ExamenSessionConsultBean.ouvertureOffreFormationService] Setter 
	 * @author BELDI Jamel on : 19 oct. 2014  19:12:52
	 * @param ouvertureOffreFormationService the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [ExamenSessionConsultBean.periodeService] Getter 
	 * @author BELDI Jamel on : 19 oct. 2014  19:12:52
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [ExamenSessionConsultBean.periodeService] Setter 
	 * @author BELDI Jamel on : 19 oct. 2014  19:12:52
	 * @param periodeService the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [ExamenSessionConsultBean.niveauService] Getter 
	 * @author BELDI Jamel on : 19 oct. 2014  19:12:52
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [ExamenSessionConsultBean.niveauService] Setter 
	 * @author BELDI Jamel on : 19 oct. 2014  19:12:52
	 * @param niveauService the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [ExamenSessionConsultBean.nomenclatureService] Getter 
	 * @author BELDI Jamel on : 19 oct. 2014  19:12:52
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [ExamenSessionConsultBean.nomenclatureService] Setter 
	 * @author BELDI Jamel on : 19 oct. 2014  19:12:52
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [ExamenSessionConsultBean.salleExamenService] Getter 
	 * @author BELDI Jamel on : 20 oct. 2014  10:37:39
	 * @return the salleExamenService
	 */
	public SalleExamenService getSalleExamenService() {
		return salleExamenService;
	}

	/**
	 * [ExamenSessionConsultBean.salleExamenService] Setter 
	 * @author BELDI Jamel on : 20 oct. 2014  10:37:39
	 * @param salleExamenService the salleExamenService to set
	 */
	public void setSalleExamenService(SalleExamenService salleExamenService) {
		this.salleExamenService = salleExamenService;
	}

	/**
	 * [ExamenSessionConsultBean.salleExamenList] Getter 
	 * @author BELDI Jamel on : 20 oct. 2014  10:37:39
	 * @return the salleExamenList
	 */
	public List<SalleExamenDto> getSalleExamenList() {
		return salleExamenList;
	}

	/**
	 * [ExamenSessionConsultBean.salleExamenList] Setter 
	 * @author BELDI Jamel on : 20 oct. 2014  10:37:39
	 * @param salleExamenList the salleExamenList to set
	 */
	public void setSalleExamenList(List<SalleExamenDto> salleExamenList) {
		this.salleExamenList = salleExamenList;
	}

	/**
	 * [ExamenSessionConsultBean.planningSessionService] Getter 
	 * @author BELDI Jamel on : 20 oct. 2014  10:46:27
	 * @return the planningSessionService
	 */
	public PlanningSessionService getPlanningSessionService() {
		return planningSessionService;
	}

	/**
	 * [ExamenSessionConsultBean.planningSessionService] Setter 
	 * @author BELDI Jamel on : 20 oct. 2014  10:46:27
	 * @param planningSessionService the planningSessionService to set
	 */
	public void setPlanningSessionService(
			PlanningSessionService planningSessionService) {
		this.planningSessionService = planningSessionService;
	}
	
	
      
    
    
}
