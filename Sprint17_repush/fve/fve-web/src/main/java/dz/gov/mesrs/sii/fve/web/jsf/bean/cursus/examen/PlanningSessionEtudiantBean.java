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

import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.InscriptionExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.PlanningSessionDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.examen.InscriptionExamenService;
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
 * @author BELDI Jamel on : 19 oct. 2014 10:04:54
 */
@ManagedBean(name = "planningSessionEtudiantBean")
@ViewScoped
public class PlanningSessionEtudiantBean implements Serializable {
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
	@ManagedProperty("#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;
	@ManagedProperty("#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	@ManagedProperty("#{inscriptionExamenService}")
	private InscriptionExamenService inscriptionExamenService;
	private DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto;
	private List<PlanningSessionDto> planningSessionList;
	private List<InscriptionExamenDto> inscriptionExamenList;

	/**
	 * 
	 * [PlanningSessionSearchBean.PlanningSessionSearchBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:11:39
	 */
	public PlanningSessionEtudiantBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
				CursusConstants.COMMON_BUNDLE_MSG_NAME);
		examenBundle = facesContext.getApplication().getResourceBundle(facesContext,
				CursusConstants.EXAMEN_BUNDLE_MSG_NAME);

	}

	/**
	 * 
	 * [PlanningSessionSearchBean.init] Method
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:11:44
	 */
	@PostConstruct
	public void init() {
		planningSessionSearchDto = new PlanningSessionDto();
		planningSessionList = new ArrayList<PlanningSessionDto>();
		inscriptionExamenList = new ArrayList<InscriptionExamenDto>();
		loadPlanningSessionList();
	}

	/**
	 * 
	 * [PlanningSessionEtudiantBean.loadPlanningSessionList] Method
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 09:36:02
	 */
	private void loadPlanningSessionList() {
		try {
			planningSessionList = new ArrayList<PlanningSessionDto>();
			List<DossierEtudiantDto> listDossierEtudiants = dossierEtudiantService.findByIdIndividu(this.sessionBean
					.getSessionBean().getUser().getId(), sessionBean.getIdEtablissement());
			if (listDossierEtudiants != null && !listDossierEtudiants.isEmpty()) {
				DossierEtudiantDto _dossierEtudiantDto = listDossierEtudiants.get(0);
				dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
						.findPresentDossierInscriptionForEtudiant(_dossierEtudiantDto.getId());
				if (dossierInscriptionAdministrativeDto != null && dossierInscriptionAdministrativeDto.getId() != 0) {
					planningSessionList = planningSessionService
							.findSessionforStudent(dossierInscriptionAdministrativeDto.getId());
				}
			}

		} catch (Exception e) {
			planningSessionList = new ArrayList<PlanningSessionDto>();
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * [PlanningSessionEtudiantBean.onPlanningSelect] Method
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:11:10
	 * @param event
	 */
	public void onPlanningSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage();
		try {
			planningSessionSearchDto = ((PlanningSessionDto) event.getObject());
			searchInscriptionExamens();
		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec"));
			msg.setDetail(bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	/**
	 * 
	 * [PlanningSessionEtudiantBean.searchInscriptionExamens] Method
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:13:08
	 */
	public void searchInscriptionExamens() {
		try {

			if (planningSessionSearchDto == null || planningSessionSearchDto.getId() == 0
					|| dossierInscriptionAdministrativeDto == null || dossierInscriptionAdministrativeDto.getId() == 0) {
				return;
			}

			inscriptionExamenList = inscriptionExamenService.findByPlanningAndStudent(planningSessionSearchDto.getId(),
					dossierInscriptionAdministrativeDto.getId());

		} catch (Exception e) {
			inscriptionExamenList = new ArrayList<InscriptionExamenDto>();

			e.printStackTrace();
		}
	}

	// getter and Setter
	/**
	 * [PlanningSessionSearchBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [PlanningSessionSearchBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [PlanningSessionSearchBean.utilBean] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [PlanningSessionSearchBean.utilBean] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [PlanningSessionSearchBean.planningSessionSearchDto] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the planningSessionSearchDto
	 */
	public PlanningSessionDto getPlanningSessionSearchDto() {
		return planningSessionSearchDto;
	}

	/**
	 * [PlanningSessionSearchBean.planningSessionSearchDto] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param planningSessionSearchDto
	 *            the planningSessionSearchDto to set
	 */
	public void setPlanningSessionSearchDto(PlanningSessionDto planningSessionSearchDto) {
		this.planningSessionSearchDto = planningSessionSearchDto;
	}

	/**
	 * [PlanningSessionSearchBean.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [PlanningSessionSearchBean.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [PlanningSessionSearchBean.anneeAcademiqueList] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [PlanningSessionSearchBean.anneeAcademiqueList] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [PlanningSessionSearchBean.offreFormationList] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the offreFormationList
	 */
	public List<SelectItem> getOffreFormationList() {
		return offreFormationList;
	}

	/**
	 * [PlanningSessionSearchBean.offreFormationList] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param offreFormationList
	 *            the offreFormationList to set
	 */
	public void setOffreFormationList(List<SelectItem> offreFormationList) {
		this.offreFormationList = offreFormationList;
	}

	/**
	 * [PlanningSessionSearchBean.niveauList] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the niveauList
	 */
	public List<SelectItem> getNiveauList() {
		return niveauList;
	}

	/**
	 * [PlanningSessionSearchBean.niveauList] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param niveauList
	 *            the niveauList to set
	 */
	public void setNiveauList(List<SelectItem> niveauList) {
		this.niveauList = niveauList;
	}

	/**
	 * [PlanningSessionSearchBean.periodeList] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the periodeList
	 */
	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	/**
	 * [PlanningSessionSearchBean.periodeList] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param periodeList
	 *            the periodeList to set
	 */
	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	/**
	 * [PlanningSessionSearchBean.typeSessionList] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the typeSessionList
	 */
	public List<SelectItem> getTypeSessionList() {
		return typeSessionList;
	}

	/**
	 * [PlanningSessionSearchBean.typeSessionList] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param typeSessionList
	 *            the typeSessionList to set
	 */
	public void setTypeSessionList(List<SelectItem> typeSessionList) {
		this.typeSessionList = typeSessionList;
	}

	/**
	 * [PlanningSessionSearchBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [PlanningSessionSearchBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [PlanningSessionSearchBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [PlanningSessionSearchBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [PlanningSessionSearchBean.periodeService] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [PlanningSessionSearchBean.periodeService] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param periodeService
	 *            the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [PlanningSessionSearchBean.niveauService] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [PlanningSessionSearchBean.niveauService] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param niveauService
	 *            the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [PlanningSessionSearchBean.nomenclatureService] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [PlanningSessionSearchBean.nomenclatureService] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [PlanningSessionSearchBean.planningSessionService] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the planningSessionService
	 */
	public PlanningSessionService getPlanningSessionService() {
		return planningSessionService;
	}

	/**
	 * [PlanningSessionSearchBean.planningSessionService] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param planningSessionService
	 *            the planningSessionService to set
	 */
	public void setPlanningSessionService(PlanningSessionService planningSessionService) {
		this.planningSessionService = planningSessionService;
	}

	/**
	 * [PlanningSessionSearchBean.planningSessionList] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @return the planningSessionList
	 */
	public List<PlanningSessionDto> getPlanningSessionList() {
		return planningSessionList;
	}

	/**
	 * [PlanningSessionSearchBean.planningSessionList] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 15:13:53
	 * @param planningSessionList
	 *            the planningSessionList to set
	 */
	public void setPlanningSessionList(List<PlanningSessionDto> planningSessionList) {
		this.planningSessionList = planningSessionList;
	}

	/**
	 * [PlanningSessionEtudiantBean.dossierEtudiantService] Getter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 09:41:12
	 * @return the dossierEtudiantService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [PlanningSessionEtudiantBean.dossierEtudiantService] Setter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 09:41:12
	 * @param dossierEtudiantService
	 *            the dossierEtudiantService to set
	 */
	public void setDossierEtudiantService(DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	/**
	 * [PlanningSessionEtudiantBean.dossierInscriptionAdministrativeService]
	 * Getter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 09:41:12
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [PlanningSessionEtudiantBean.dossierInscriptionAdministrativeService]
	 * Setter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 09:41:12
	 * @param dossierInscriptionAdministrativeService
	 *            the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [PlanningSessionEtudiantBean.loginBean] Getter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:04:31
	 * @return the loginBean
	 */
	public LoginBean getLoginBean() {
		return loginBean;
	}

	/**
	 * [PlanningSessionEtudiantBean.loginBean] Setter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:04:31
	 * @param loginBean
	 *            the loginBean to set
	 */
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	/**
	 * [PlanningSessionEtudiantBean.dossierInscriptionAdministrativeDto] Getter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:09:26
	 * @return the dossierInscriptionAdministrativeDto
	 */
	public DossierInscriptionAdministrativeDto getDossierInscriptionAdministrativeDto() {
		return dossierInscriptionAdministrativeDto;
	}

	/**
	 * [PlanningSessionEtudiantBean.dossierInscriptionAdministrativeDto] Setter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:09:26
	 * @param dossierInscriptionAdministrativeDto
	 *            the dossierInscriptionAdministrativeDto to set
	 */
	public void setDossierInscriptionAdministrativeDto(
			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto) {
		this.dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeDto;
	}

	/**
	 * [PlanningSessionEtudiantBean.inscriptionExamenList] Getter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:12:21
	 * @return the inscriptionExamenList
	 */
	public List<InscriptionExamenDto> getInscriptionExamenList() {
		return inscriptionExamenList;
	}

	/**
	 * [PlanningSessionEtudiantBean.inscriptionExamenList] Setter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:12:21
	 * @param inscriptionExamenList
	 *            the inscriptionExamenList to set
	 */
	public void setInscriptionExamenList(List<InscriptionExamenDto> inscriptionExamenList) {
		this.inscriptionExamenList = inscriptionExamenList;
	}

	/**
	 * [PlanningSessionEtudiantBean.inscriptionExamenService] Getter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:15:16
	 * @return the inscriptionExamenService
	 */
	public InscriptionExamenService getInscriptionExamenService() {
		return inscriptionExamenService;
	}

	/**
	 * [PlanningSessionEtudiantBean.inscriptionExamenService] Setter
	 * 
	 * @author BELDI Jamel on : 21 oct. 2014 11:15:16
	 * @param inscriptionExamenService
	 *            the inscriptionExamenService to set
	 */
	public void setInscriptionExamenService(InscriptionExamenService inscriptionExamenService) {
		this.inscriptionExamenService = inscriptionExamenService;
	}

}
