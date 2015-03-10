package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.primefaces.event.SelectEvent;
import org.springframework.util.StringUtils;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.InscriptionExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.PlanningSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ResponsableExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.SalleExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.ExamenSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.PlanningSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.SalleExamenService;
import dz.gov.mesrs.sii.fve.business.service.impression.ImpressionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SearchBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.impression.PrintMgrBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

/**
 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
 */
@ManagedBean(name = "planningSessionSaveBean")
@ViewScoped
public class PlanningSessionSaveBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:10
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(PlanningSessionSaveBean.class);
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{printMgrBean}")
	private PrintMgrBean printMgrBean;
	@ManagedProperty("#{impressionService}")
	private ImpressionService impressionService;
	private ResourceBundle bundleCommon;
	private ResourceBundle examenBundle;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty("#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{niveauService}")
	private NiveauService niveauService;
	@ManagedProperty("#{periodeService}")
	private PeriodeService periodeService;
	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	@ManagedProperty("#{planningSessionService}")
	private PlanningSessionService planningSessionService;
	@ManagedProperty("#{examenSessionService}")
	private ExamenSessionService examenSessionService;
	@ManagedProperty("#{salleExamenBean}")
	private SalleExamenBean salleExamenBean;
	@ManagedProperty("#{inscriptionExamenBean}")
	private InscriptionExamenBean inscriptionExamenBean;
	@ManagedProperty("#{responsableExamenBean}")
	private ResponsableExamenBean responsableExamenBean;
	@ManagedProperty("#{deroulementAbsenceResponsableBean}")
	private DeroulementAbsenceResponsableBean deroulementAbsenceResponsableBean;
	@ManagedProperty("#{deroulementInscriptionExamenBean}")
	private DeroulementInscriptionExamenBean deroulementInscriptionExamenBean;
	@ManagedProperty("#{searchBean}")
	private SearchBean searchBean;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> offreFormationList;
	private List<SelectItem> niveauList;
	private List<SelectItem> periodeList;
	private List<SelectItem> sessionNormaleList;
	private PlanningSessionDto planningSessionDto;
	private List<SelectItem> typeSessionList;
	private List<ExamenSessionDto> examensSessionList;
	private boolean hideExamen;
	private NomenclatureDto sessionRemplacement;
	private NomenclatureDto sessionNormale;
	private boolean sessionListDisable;
	private boolean inclureControle;
	private List<PlanningSessionDto> planningSessionList;
	private boolean showInfoDetail;
	private boolean showExamenDetail;
	private boolean showTableSearch;
	private boolean showDeroulementDetail;
	private boolean editMode;
	private boolean deroulementMode;
	private boolean showSessionDetail;
	private List<SalleExamenDto> salleExamenList;
	@ManagedProperty("#{salleExamenService}")
	private SalleExamenService salleExamenService;
	private ExamenSessionDto examenSessionDto;

	/**
	 * [ParcoursIndividualiseBean.ParcoursIndividualiseBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
	 */
	public PlanningSessionSaveBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		examenBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.EXAMEN_BUNDLE_MSG_NAME);
		String viewId = facesContext.getViewRoot().getViewId();
		editMode = true;
		if (viewId != null && viewId.toLowerCase().contains("consul")) {
			editMode = false;
		}
		deroulementMode = false;
		if (viewId != null && viewId.toLowerCase().contains("deroul")) {
			deroulementMode = true;
		}

		planningSessionDto = new PlanningSessionDto();

	}

	/**
	 * 
	 * [PlanningSessionSaveBean.init] Method
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:56
	 */
	@PostConstruct
	public void init() {

		loadTypeSessions();
		loadOffreFormationOuverte();
		loadNiveaux();
		loadPeriodes();
		loadSessionNormale();
		loadSessionRemplacement();
		sessionListDisable = true;
		inclureControle = false;
	}

	/**
	 * [PlanningSessionSaveBean.searchPlanningSessions] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 16:09:36
	 */
	public void searchPlanningSessions() {
		showTableSearch = false;
		showInfoDetail = false;
		showExamenDetail = false;
		showSessionDetail = false;
		try {
			planningSessionList = new ArrayList<PlanningSessionDto>();
			planningSessionDto = new PlanningSessionDto();
			if (searchBean.getAnneeAcademiqueId() != null
					&& searchBean.getOofId() != null
					&& searchBean.getPeriodeId() != null) {
				planningSessionDto.setAnneeAcademiqueId(searchBean
						.getAnneeAcademiqueId());
				planningSessionDto.setOuvertureOffreFormationId(searchBean
						.getOofId());
				planningSessionDto.setIdPeriode(searchBean.getPeriodeId());
				planningSessionList = planningSessionService
						.findAdvanced(planningSessionDto);
				if (planningSessionList == null
						|| planningSessionList.isEmpty()) {
					Utility.showWarningMessage(examenBundle
							.getString("examen_datable_list_planning_no_result"));
					return;
				}
				if (planningSessionList.size() == 1) {
					planningSessionDto = planningSessionList.get(0);
					planningSelected();
				}
				showTableSearch = true;

			}
			// planningSessionSaveBean.setPlanningSessionDto(null);
		} catch (Exception e) {
			// planningSessionList = new ArrayList<PlanningSessionDto>();
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [PlanningSessionSaveBean.onPlanningSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 16:09:56
	 * @param event
	 */
	public void onPlanningSelect(SelectEvent event) {
		try {
			planningSessionDto = ((PlanningSessionDto) event.getObject());
			planningSelected();
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [PlanningSessionSaveBean.planningSelected] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2015 15:38:08
	 */
	private void planningSelected() {
		try {
			if (!editMode) {
				searchExamens();
			} else {
				loadPlanningSession();
			}
			showInfoDetail = true;
			showExamenDetail = true;
			if (deroulementMode) {
				showSessionDetail = false;
			} else {
				showSessionDetail = true;
			}

		} catch (Exception e) {

		}
	}

	/**
	 * 
	 * [PlanningSessionSaveBean.loadTypeSessions] Method
	 * 
	 * @author BELDI Jamel on : 21 sept. 2014 09:54:51
	 */
	private void loadTypeSessions() {
		try {
			List<NomenclatureDto> list = nomenclatureService
					.findByName(CursusConstants.NC_TYPE_SESSION);
			typeSessionList = new ArrayList<SelectItem>();
			for (NomenclatureDto type : list) {
				SelectItem item = new SelectItem(type.getId(),
						type.getLibelleLongFr());
				typeSessionList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * [PlanningSessionSaveBean.loadAnneeAcademique] Method
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:56:49
	 */
	public void loadAnneeAcademique() {
		try {
			anneeAcademiqueList = utilBean.loadAnneeAcademique();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [ParcoursIndividualiseConsultBean.anneeChanged] Method
	 * 
	 * @author BELDI Jamel on : 13 aoï¿½t 2014 16:35:53
	 */
	public void anneeChanged() {
		planningSessionDto.setOuvertureOffreFormationId(null);
		if (searchBean.getAnneeAcademiqueId() != null) {
			loadOffreFormationOuverte();
			ofChanged();
		}

	}

	/**
	 * [PlanningSessionSaveBean.loadSessionNormale] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 10:21:41
	 */
	public void loadSessionNormale() {
		try {
			sessionNormale = utilBean.loadNcTypeSessionNormale();
			if (sessionNormale == null) {
				sessionNormale = new NomenclatureDto();
			}
		} catch (Exception e) {

		}
	}

	/**
	 * [PlanningSessionSaveBean.loadSessionRemplacement] Method
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 08:41:57
	 */
	public void loadSessionRemplacement() {
		try {
			sessionRemplacement = utilBean.loadNcTypeSessionRemplacement();
			if (sessionRemplacement == null) {
				sessionRemplacement = new NomenclatureDto();
			}
		} catch (Exception e) {

		}
	}

	/**
	 * 
	 * [ParcoursIndividualiseConsultBean.loadOffreFormationOuverte] Method
	 * 
	 * @author BELDI Jamel on : 13 aoï¿½t 2014 16:37:29
	 */
	public void loadOffreFormationOuverte() {
		offreFormationList = new ArrayList<SelectItem>();

		try {
			offreFormationList = utilBean.loadOffreFormationOuverte(searchBean
					.getAnneeAcademiqueId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * [ParcoursIndividualiseStatisticBean.ofChanged] Method
	 * 
	 * @author BELDI Jamel on : 14 aoï¿½t 2014 12:34:36
	 */
	public void ofChanged() {
		planningSessionDto.setNiveauId(null);
		// loadNiveaux();
		loadPeriodes();
		periodeChanged();

	}

	/**
	 * [PlanningSessionSaveBean.headerExamen] Method
	 * 
	 * @author MAKERRI Sofiane on : 5 févr. 2015 09:09:20
	 * @param examenSessionDto
	 * @return
	 */
	public String headerExamen(ExamenSessionDto examenSessionDto) {
		if (examenSessionDto != null) {
			return examenSessionDto.getMcLibelleFr()
					+ " - "
					+ Utility.formatDate(examenSessionDto.getDateExamen(),
							"dd/MM/yyyy")
					+ "("
					+ Utility.formatDate(examenSessionDto.getHeureDebut(),
							"MM:ss")
					+ "-"
					+ Utility.formatDate(examenSessionDto.getHeureFin(),
							"MM:ss") + ")";
		}
		return null;

	}

	/**
	 * 
	 * [PlanningSessionSaveBean.loadNiveaux] Method
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 11:05:22
	 */
	private void loadNiveaux() {
		try {
			niveauList = new ArrayList<SelectItem>();
			if (planningSessionDto == null || searchBean.getOofId() == null) {
				return;
			}
			OuvertureOffreFormationDto _oof = ouvertureOffreFormationService
					.findById(searchBean.getOofId());
			List<NiveauDto> list = niveauService.findByCycleId(_oof
					.getCycleId());

			for (NiveauDto niveauDto : list) {
				SelectItem item = new SelectItem(niveauDto.getId(),
						niveauDto.getLibelleLongLt());
				niveauList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [PlanningSessionSaveBean.niveauChanged] Method
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 11:05:32
	 */
	public void niveauChanged() {
		// planningSessionDto.setIdPeriode(null);
		loadPeriodes();
		periodeChanged();

	}

	/**
	 * 
	 * [PlanningSessionSaveBean.loadPeriodes] Method
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 11:05:39
	 */
	private void loadPeriodes() {
		try {
			periodeList = new ArrayList<SelectItem>();
			if (searchBean.getOofId() != null) {
				periodeList = utilBean.loadPeriodeByNiveau(searchBean
						.getOofId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [ParcoursIndividualiseStatisticBean.ofChanged] Method
	 * 
	 * @author BELDI Jamel on : 14 aoï¿½t 2014 12:34:36
	 */
	public void periodeChanged() {
		searchPlanningSessions();

	}

	/**
	 * [PlanningSessionSaveBean.typeSessionChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 11:03:21
	 */
	public void typeSessionChanged() {
		sessionListDisable = true;
		inclureControle = false;
		sessionNormaleList = new ArrayList<SelectItem>();
		if (planningSessionDto.getNcTypeSessionId() != null
				&& sessionRemplacement != null
				&& sessionRemplacement.getId() != null
				&& planningSessionDto.getNcTypeSessionId().equals(
						sessionRemplacement.getId())) {
			loadSessionNormaleList();
			sessionListDisable = false;
		} else if (planningSessionDto.getNcTypeSessionId() != null
				&& sessionNormale != null
				&& sessionNormale.getId() != null
				&& planningSessionDto.getNcTypeSessionId().equals(
						sessionNormale.getId())) {
			inclureControle = true;
		}

	}

	/**
	 * [PlanningSessionSaveBean.loadSessionNormaleList] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 09:41:05
	 */
	public void loadSessionNormaleList() {
		try {
			sessionNormaleList = new ArrayList<SelectItem>();
			PlanningSessionDto searchDto = new PlanningSessionDto();
			searchDto.setOuvertureOffreFormationId(searchBean.getOofId());
			searchDto.setIdPeriode(searchBean.getPeriodeId());
			searchDto.setAnneeAcademiqueId(searchBean.getAnneeAcademiqueId());
			if (sessionNormale != null && sessionNormale.getId() != null) {
				searchDto.setNcTypeSessionId(sessionNormale.getId());
			}
			sessionNormaleList = utilBean.loadPlanningSession(searchDto);

		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
		}

	}

	/**
	 * 
	 * [PlanningSessionSaveBean.loadPlanningSession] Method
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 11:01:37
	 */
	public void loadPlanningSession() {
		try {

			hideExamen = true;
			loadExamenSessionList();
			// Refresh Data
			salleExamenBean.init();
			salleExamenBean.loadExamenList(planningSessionDto.getId());
			inscriptionExamenBean.init();
			inscriptionExamenBean.loadExamenList(planningSessionDto.getId());
			inscriptionExamenBean.setPlanningSessionDto(planningSessionDto);
			responsableExamenBean.init();
			responsableExamenBean.loadExamenList(planningSessionDto.getId());
			deroulementAbsenceResponsableBean.init();
			deroulementAbsenceResponsableBean.loadExamenList(planningSessionDto
					.getId());
			deroulementInscriptionExamenBean.init();
			deroulementInscriptionExamenBean.loadExamenList(planningSessionDto
					.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [PlanningSessionSaveBean.loadExamenSessionList] Method
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 17:23:51
	 */
	public void loadExamenSessionList() {
		try {

			examensSessionList = new ArrayList<ExamenSessionDto>();
			if (planningSessionDto == null || planningSessionDto.getId() == 0) {
				return;
			}
			examensSessionList = examenSessionService
					.findBySession(planningSessionDto.getId());
			if (examensSessionList != null && examensSessionList.size() == 1) {
				examenSessionDto = examensSessionList.get(0);
			}

		} catch (Exception e) {
			examensSessionList = new ArrayList<ExamenSessionDto>();
			e.printStackTrace();
		}
	}

	/**
	 * [PlanningSessionSaveBean.addPlanning] Method
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 09:42:49
	 */
	public void addPlanning() {
		planningSessionDto = new PlanningSessionDto();
		planningSessionDto.setAnneeAcademiqueId(searchBean
				.getAnneeAcademiqueId());
		planningSessionDto.setOuvertureOffreFormationId(searchBean.getOofId());
		planningSessionDto.setIdPeriode(searchBean.getPeriodeId());
		planningSessionDto.setCoefficient(1.0);
		planningSessionDto.setDateDebut(new Date());
		init();
		loadPlanningSession();
		showInfoDetail = true;
		showSessionDetail = false;

	}

	/**
	 * 
	 * [PlanningSessionSaveBean.savePlanningSession] Method
	 * 
	 * @author BELDI Jamel on : 21 sept. 2014 10:12:56
	 */
	public void savePlanningSession() {
		try {
			if (planningSessionDto == null) {
				return;
			}
			if (searchBean.getAnneeAcademiqueId() == null
					|| searchBean.getOofId() == null
					|| searchBean.getPeriodeId() == null) {
				Utility.showErrorMessage("Impossible de sauvegarder le planning");
				return;
			}
			planningSessionDto.setAnneeAcademiqueId(searchBean
					.getAnneeAcademiqueId());
			planningSessionDto.setOuvertureOffreFormationId(searchBean
					.getOofId());

			PeriodeDto _periodeDto = periodeService.findById(searchBean
					.getPeriodeId());
			if (_periodeDto == null || _periodeDto.getIdNiveau() == 0) {
				Utility.showErrorMessage("Impossible de sauvegarder le planning");
				return;
			}
			planningSessionDto.setNiveauId(_periodeDto.getIdNiveau());
			planningSessionDto.setIdPeriode(searchBean.getPeriodeId());
			if (planningSessionDto.getDateDebut() == null
					|| planningSessionDto.getDateFin() == null
					|| planningSessionDto.getDateDebut().after(
							planningSessionDto.getDateFin())) {
				Utility.showErrorMessage(examenBundle
						.getString("examen_error_date_debut_fin"));
				return;
			}
			boolean update = true;
			if (planningSessionDto.getId() == 0) {
				planningSessionDto.setSituationId(situationService
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_PLANNING_SESSION_CODE,
								UtilConstants.SITUTAION_CREEE_CODE).getId());
				planningSessionDto
						.setSituationCode(UtilConstants.SITUTAION_CREEE_CODE);
				planningSessionDto.setDateCreation(new Date());
				update = false;

			}

			planningSessionDto = planningSessionService
					.insertOrUpdate(planningSessionDto);
			showInfoDetail = true;
			showExamenDetail = true;
			showSessionDetail = true;
			loadExamenSessionList();
			if (update) {
				Utility.showSuccessUpdateMessage();
			} else {
				Utility.showSuccessSaveMessage();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * 
	 * [PlanningSessionSaveBean.publishPlanningSession] Method
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 15:30:56
	 */
	public void publishPlanningSession() {
		try {
			if (planningSessionDto == null) {
				return;
			}

			if (planningSessionService.publish(planningSessionDto.getId())) {
				loadPlanningSession();
				Utility.showSuccessUpdateMessage();
			} else {
				Utility.showErrorMessage(bundleCommon
						.getString("error_echec_inconnue"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * 
	 * [PlanningSessionSaveBean.fencePlanningSession] Method
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 15:40:56
	 */
	public void fencePlanningSession() {
		try {
			if (planningSessionDto == null) {
				return;
			}

			if (planningSessionService.fence(planningSessionDto.getId())) {
				loadPlanningSession();
				Utility.showSuccessUpdateMessage();
			} else {
				bundleCommon.getString("error_echec_inconnue");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());

		}
	}

	/**
	 * [PlanningSessionSaveBean.imprimerExamens] Method
	 * 
	 * @author MAKERRI Sofiane on : 5 févr. 2015 12:09:11
	 */
	public void imprimerExamens() {
		try {

			String name = "planning_examens";
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String SUBREPORT_DIR = facesContext.getExternalContext()
					.getRealPath("/WEB-INF/classes")
					+ "/jasper/examen/planning/";
			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = SUBREPORT_DIR
					+ "examens.jrxml";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.JASPER_PARAM_IMG_PAPS_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logopaps.png");
			params.put(Const.JASPER_PARAM_IMG_LOGO_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logo"
					+ sessionBean.getCodeEtablissement() + ".png");

			// prepareExamenSession();
			List<PlanningSessionDto> planningSessionDtos = new ArrayList<PlanningSessionDto>();
			planningSessionDto
					.setExamenSessionDtos(new HashSet<ExamenSessionDto>(
							examensSessionList));
			planningSessionDtos.add(planningSessionDto);
			params.put(Const.JASPER_PARAM_SUBREPORT_DIR_KEY, SUBREPORT_DIR);
			byte[] bytes = impressionService.viewPDFWithDataSource(
					RESOURCE_PATH_TO_INPUT_FILE_JASPER, params,
					planningSessionDtos);
			printMgrBean.imprimer(bytes, name, "pdf");

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [PlanningSessionSaveBean.prepareExamenSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 févr. 2015 10:10:26
	 * @return
	 */
	private void prepareExamenSession() {
		if (examensSessionList != null) {
			for (ExamenSessionDto _examenSessionDto : examensSessionList) {
				List<SalleExamenDto> _salleExamenDtos = _examenSessionDto
						.getSalleExamenDtos();
				if (_salleExamenDtos != null && !_salleExamenDtos.isEmpty()) {
					List<ResponsableExamenDto> responsableExamenDtos = new ArrayList<ResponsableExamenDto>();
					for (SalleExamenDto _salleExamenDto : _salleExamenDtos) {
						List<ResponsableExamenDto> _responsableExamenDtos = _salleExamenDto
								.getResponsables();
						String responsableString = "-";
						if (_responsableExamenDtos != null
								&& !_responsableExamenDtos.isEmpty()) {
							if (_responsableExamenDtos != null) {
								responsableString = StringUtils
										.collectionToDelimitedString(
												_responsableExamenDtos, "-");

							}
						}
						ResponsableExamenDto responsableExamenDto = new ResponsableExamenDto();
						responsableExamenDto.setNomPrenom(responsableString);
						responsableExamenDtos.add(responsableExamenDto);
					}
					_examenSessionDto
							.setResponsableExamenDtosGrouped(responsableExamenDtos);
				}
			}
		}
	}

	/**
	 * [PlanningSessionSaveBean.imprimerExamenInscriptions] Method
	 * 
	 * @author MAKERRI Sofiane on : 5 févr. 2015 16:00:29
	 */
	public void imprimerExamenInscriptions() {
		try {
			String name = "Répartition_etudiants_"
					+ planningSessionDto.getOffreFormationLibelleFr() + "_"
					+ planningSessionDto.getLibellePeriode() + "_"
					+ planningSessionDto.getNcTypeSessionLibelleFr() + "_"
					+ planningSessionDto.getAnneeAcademiqueCode();
			imprimerDetail(name, "examens_inscriptions.jrxml");

		} catch (Exception e) {

		}
	}

	/**
	 * [PlanningSessionSaveBean.imprimerExamenResponsables] Method
	 * 
	 * @author MAKERRI Sofiane on : 5 févr. 2015 16:38:27
	 */
	public void imprimerExamenResponsables() {
		try {
			String name = "Répartition_responsables_"
					+ planningSessionDto.getOffreFormationLibelleFr() + "_"
					+ planningSessionDto.getLibellePeriode() + "_"
					+ planningSessionDto.getNcTypeSessionLibelleFr() + "_"
					+ planningSessionDto.getAnneeAcademiqueCode();
			imprimerDetail(name, "examens_responsables.jrxml");

		} catch (Exception e) {

		}
	}

	/**
	 * [PlanningSessionSaveBean.imprimerDetail] Method
	 * 
	 * @author MAKERRI Sofiane on : 5 févr. 2015 16:37:22
	 * @param name
	 */
	private void imprimerDetail(String fileName, String japserName) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String SUBREPORT_DIR = facesContext.getExternalContext()
					.getRealPath("/WEB-INF/classes")
					+ "/jasper/examen/planning/";
			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = SUBREPORT_DIR
					+ japserName;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.JASPER_PARAM_IMG_PAPS_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logopaps.png");
			params.put(Const.JASPER_PARAM_IMG_LOGO_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logo"
					+ sessionBean.getCodeEtablissement() + ".png");
			params.put(Const.JASPER_PARAM_SUBREPORT_DIR_KEY, SUBREPORT_DIR);
			params.put(Const.JASPER_PARAM_OFFRE_FORMATION_KEY,
					planningSessionDto.getOffreFormationLibelleFr());
			params.put(Const.JASPER_PARAM_ANNEE_ACADEMIQUE_KEY,
					planningSessionDto.getAnneeAcademiqueCode());
			params.put(Const.JASPER_PARAM_PERIODE_KEY,
					planningSessionDto.getLibellePeriode());
			params.put(Const.JASPER_PARAM_NIVEAU_KEY,
					planningSessionDto.getNiveauLibelleLongLt());
			params.put(Const.JASPER_PARAM_INTITULE_KEY,
					planningSessionDto.getIntitule());
			params.put(Const.JASPER_PARAM_TYPE_SESSION_KEY,
					planningSessionDto.getNcTypeSessionLibelleFr());
			params.put(Const.JASPER_PARAM_DATE_DEBUT_KEY,
					planningSessionDto.getDateDebut());
			params.put(Const.JASPER_PARAM_DATE_FIN_KEY,
					planningSessionDto.getDateFin());
			byte[] bytes = impressionService.viewPDFWithDataSource(
					RESOURCE_PATH_TO_INPUT_FILE_JASPER, params,
					examensSessionList);
			printMgrBean.imprimer(bytes, fileName, "pdf");
		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [PlanningSessionSaveBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [PlanningSessionSaveBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [PlanningSessionSaveBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [PlanningSessionSaveBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [PlanningSessionSaveBean.offreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [PlanningSessionSaveBean.offreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [PlanningSessionSaveBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [PlanningSessionSaveBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [PlanningSessionSaveBean.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [PlanningSessionSaveBean.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [PlanningSessionSaveBean.situationService] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [PlanningSessionSaveBean.situationService] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [PlanningSessionSaveBean.planningSessionService] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @return the planningSessionService
	 */
	public PlanningSessionService getPlanningSessionService() {
		return planningSessionService;
	}

	/**
	 * [PlanningSessionSaveBean.planningSessionService] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @param planningSessionService
	 *            the planningSessionService to set
	 */
	public void setPlanningSessionService(
			PlanningSessionService planningSessionService) {
		this.planningSessionService = planningSessionService;
	}

	/**
	 * [PlanningSessionSaveBean.anneeAcademiqueList] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [PlanningSessionSaveBean.anneeAcademiqueList] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [PlanningSessionSaveBean.offreFormationList] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @return the offreFormationList
	 */
	public List<SelectItem> getOffreFormationList() {
		return offreFormationList;
	}

	/**
	 * [PlanningSessionSaveBean.offreFormationList] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @param offreFormationList
	 *            the offreFormationList to set
	 */
	public void setOffreFormationList(List<SelectItem> offreFormationList) {
		this.offreFormationList = offreFormationList;
	}

	/**
	 * [PlanningSessionSaveBean.niveauList] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @return the niveauList
	 */
	public List<SelectItem> getNiveauList() {
		return niveauList;
	}

	/**
	 * [PlanningSessionSaveBean.niveauList] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @param niveauList
	 *            the niveauList to set
	 */
	public void setNiveauList(List<SelectItem> niveauList) {
		this.niveauList = niveauList;
	}

	/**
	 * [PlanningSessionSaveBean.periodeList] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @return the periodeList
	 */
	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	/**
	 * [PlanningSessionSaveBean.periodeList] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:00
	 * @param periodeList
	 *            the periodeList to set
	 */
	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	/**
	 * [PlanningSessionSaveBean.niveauService] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 11:22:34
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [PlanningSessionSaveBean.niveauService] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 11:22:34
	 * @param niveauService
	 *            the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [PlanningSessionSaveBean.periodeService] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 11:22:34
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [PlanningSessionSaveBean.periodeService] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 11:22:34
	 * @param periodeService
	 *            the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [PlanningSessionSaveBean.planningSessionDto] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 11:53:40
	 * @return the planningSessionDto
	 */
	public PlanningSessionDto getPlanningSessionDto() {
		return planningSessionDto;
	}

	/**
	 * [PlanningSessionSaveBean.planningSessionDto] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 11:53:40
	 * @param planningSessionDto
	 *            the planningSessionDto to set
	 */
	public void setPlanningSessionDto(PlanningSessionDto planningSessionDto) {
		this.planningSessionDto = planningSessionDto;
	}

	/**
	 * [PlanningSessionSaveBean.typeSessionList] Getter
	 * 
	 * @author BELDI Jamel on : 21 sept. 2014 09:32:38
	 * @return the typeSessionList
	 */
	public List<SelectItem> getTypeSessionList() {
		return typeSessionList;
	}

	/**
	 * [PlanningSessionSaveBean.typeSessionList] Setter
	 * 
	 * @author BELDI Jamel on : 21 sept. 2014 09:32:38
	 * @param typeSessionList
	 *            the typeSessionList to set
	 */
	public void setTypeSessionList(List<SelectItem> typeSessionList) {
		this.typeSessionList = typeSessionList;
	}

	/**
	 * [PlanningSessionSaveBean.examensSessionList] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @return the examensSessionList
	 */
	public List<ExamenSessionDto> getExamensSessionList() {
		return examensSessionList;
	}

	/**
	 * [PlanningSessionSaveBean.examensSessionList] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @param examensSessionList
	 *            the examensSessionList to set
	 */
	public void setExamensSessionList(List<ExamenSessionDto> examensSessionList) {
		this.examensSessionList = examensSessionList;
	}

	/**
	 * [PlanningSessionSaveBean.examenSessionService] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 16:50:09
	 * @return the examenSessionService
	 */
	public ExamenSessionService getExamenSessionService() {
		return examenSessionService;
	}

	/**
	 * [PlanningSessionSaveBean.examenSessionService] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 16:50:09
	 * @param examenSessionService
	 *            the examenSessionService to set
	 */
	public void setExamenSessionService(
			ExamenSessionService examenSessionService) {
		this.examenSessionService = examenSessionService;
	}

	/**
	 * [PlanningSessionSaveBean.hideExamen] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 09:35:41
	 * @return the hideExamen
	 */
	public boolean isHideExamen() {
		return hideExamen;
	}

	/**
	 * [PlanningSessionSaveBean.hideExamen] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 09:35:41
	 * @param hideExamen
	 *            the hideExamen to set
	 */
	public void setHideExamen(boolean hideExamen) {
		this.hideExamen = hideExamen;
	}

	/**
	 * [PlanningSessionSaveBean.salleExamenBean] Getter
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:06:59
	 * @return the salleExamenBean
	 */
	public SalleExamenBean getSalleExamenBean() {
		return salleExamenBean;
	}

	/**
	 * [PlanningSessionSaveBean.salleExamenBean] Setter
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:06:59
	 * @param salleExamenBean
	 *            the salleExamenBean to set
	 */
	public void setSalleExamenBean(SalleExamenBean salleExamenBean) {
		this.salleExamenBean = salleExamenBean;
	}

	/**
	 * [PlanningSessionSaveBean.inscriptionExamenBean] Getter
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:23:02
	 * @return the inscriptionExamenBean
	 */
	public InscriptionExamenBean getInscriptionExamenBean() {
		return inscriptionExamenBean;
	}

	/**
	 * [PlanningSessionSaveBean.inscriptionExamenBean] Setter
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:23:02
	 * @param inscriptionExamenBean
	 *            the inscriptionExamenBean to set
	 */
	public void setInscriptionExamenBean(
			InscriptionExamenBean inscriptionExamenBean) {
		this.inscriptionExamenBean = inscriptionExamenBean;
	}

	/**
	 * [PlanningSessionSaveBean.responsableExamenBean] Getter
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:30:37
	 * @return the responsableExamenBean
	 */
	public ResponsableExamenBean getResponsableExamenBean() {
		return responsableExamenBean;
	}

	/**
	 * [PlanningSessionSaveBean.responsableExamenBean] Setter
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:30:37
	 * @param responsableExamenBean
	 *            the responsableExamenBean to set
	 */
	public void setResponsableExamenBean(
			ResponsableExamenBean responsableExamenBean) {
		this.responsableExamenBean = responsableExamenBean;
	}

	/**
	 * [PlanningSessionSaveBean.deroulementAbsenceResponsableBean] Getter
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:48:25
	 * @return the deroulementAbsenceResponsableBean
	 */
	public DeroulementAbsenceResponsableBean getDeroulementAbsenceResponsableBean() {
		return deroulementAbsenceResponsableBean;
	}

	/**
	 * [PlanningSessionSaveBean.deroulementAbsenceResponsableBean] Setter
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:48:25
	 * @param deroulementAbsenceResponsableBean
	 *            the deroulementAbsenceResponsableBean to set
	 */
	public void setDeroulementAbsenceResponsableBean(
			DeroulementAbsenceResponsableBean deroulementAbsenceResponsableBean) {
		this.deroulementAbsenceResponsableBean = deroulementAbsenceResponsableBean;
	}

	/**
	 * [PlanningSessionSaveBean.deroulementInscriptionExamenBean] Getter
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:48:25
	 * @return the deroulementInscriptionExamenBean
	 */
	public DeroulementInscriptionExamenBean getDeroulementInscriptionExamenBean() {
		return deroulementInscriptionExamenBean;
	}

	/**
	 * [PlanningSessionSaveBean.deroulementInscriptionExamenBean] Setter
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:48:25
	 * @param deroulementInscriptionExamenBean
	 *            the deroulementInscriptionExamenBean to set
	 */
	public void setDeroulementInscriptionExamenBean(
			DeroulementInscriptionExamenBean deroulementInscriptionExamenBean) {
		this.deroulementInscriptionExamenBean = deroulementInscriptionExamenBean;
	}

	/**
	 * [PlanningSessionSaveBean.sessionNormaleList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 09:39:30
	 * @return the sessionNormaleList
	 */
	public List<SelectItem> getSessionNormaleList() {
		return sessionNormaleList;
	}

	/**
	 * [PlanningSessionSaveBean.sessionNormaleList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 09:39:30
	 * @param sessionNormaleList
	 *            the sessionNormaleList to set
	 */
	public void setSessionNormaleList(List<SelectItem> sessionNormaleList) {
		this.sessionNormaleList = sessionNormaleList;
	}

	/**
	 * [PlanningSessionSaveBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 09:44:45
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [PlanningSessionSaveBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 09:44:45
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [PlanningSessionSaveBean.nomenclatureService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 09:49:31
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [PlanningSessionSaveBean.nomenclatureService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 09:49:31
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [PlanningSessionSaveBean.sessionNormale] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 10:04:58
	 * @return the sessionNormale
	 */
	public NomenclatureDto getSessionNormale() {
		return sessionNormale;
	}

	/**
	 * [PlanningSessionSaveBean.sessionNormale] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 10:04:58
	 * @param sessionNormale
	 *            the sessionNormale to set
	 */
	public void setSessionNormale(NomenclatureDto sessionNormale) {
		this.sessionNormale = sessionNormale;
	}

	/**
	 * [PlanningSessionSaveBean.sessionListDisable] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 11:14:35
	 * @return the sessionListDisable
	 */
	public boolean isSessionListDisable() {
		return sessionListDisable;
	}

	/**
	 * [PlanningSessionSaveBean.sessionListDisable] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 11:14:35
	 * @param sessionListDisable
	 *            the sessionListDisable to set
	 */
	public void setSessionListDisable(boolean sessionListDisable) {
		this.sessionListDisable = sessionListDisable;
	}

	/**
	 * [PlanningSessionSaveBean.planningSessionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 12:01:07
	 * @return the planningSessionList
	 */
	public List<PlanningSessionDto> getPlanningSessionList() {
		return planningSessionList;
	}

	/**
	 * [PlanningSessionSaveBean.planningSessionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 12:01:07
	 * @param planningSessionList
	 *            the planningSessionList to set
	 */
	public void setPlanningSessionList(
			List<PlanningSessionDto> planningSessionList) {
		this.planningSessionList = planningSessionList;
	}

	/**
	 * [PlanningSessionSaveBean.showInfoDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 14:35:21
	 * @return the showDetail
	 */
	public boolean isShowInfoDetail() {
		return showInfoDetail;
	}

	/**
	 * [PlanningSessionSaveBean.showInfoDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 14:35:21
	 * @param showDetail
	 *            the showDetail to set
	 */
	public void setShowInfoDetail(boolean showInfoDetail) {
		this.showInfoDetail = showInfoDetail;
	}

	/**
	 * [PlanningSessionSaveBean.showTableSearch] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 14:42:16
	 * @return the showTableSearch
	 */
	public boolean getShowTableSearch() {
		return showTableSearch;
	}

	/**
	 * [PlanningSessionSaveBean.showTableSearch] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 14:42:16
	 * @param showTableSearch
	 *            the showTableSearch to set
	 */
	public void setShowTableSearch(boolean showTableSearch) {
		this.showTableSearch = showTableSearch;
	}

	/**
	 * [PlanningSessionSaveBean.editMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 14:57:54
	 * @return the editMode
	 */
	public boolean getEditMode() {
		return editMode;
	}

	/**
	 * [PlanningSessionSaveBean.editMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 14:57:54
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * [PlanningSessionSaveBean.sessionRemplacement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 08:42:29
	 * @return the sessionRemplacement
	 */
	public NomenclatureDto getSessionRemplacement() {
		return sessionRemplacement;
	}

	/**
	 * [PlanningSessionSaveBean.sessionRemplacement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 08:42:29
	 * @param sessionRemplacement
	 *            the sessionRemplacement to set
	 */
	public void setSessionRemplacement(NomenclatureDto sessionRemplacement) {
		this.sessionRemplacement = sessionRemplacement;
	}

	/**
	 * [PlanningSessionSaveBean.showSessionDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 09:42:16
	 * @return the showSessionDetail
	 */
	public boolean isShowSessionDetail() {
		return showSessionDetail;
	}

	/**
	 * [PlanningSessionSaveBean.showSessionDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 09:42:16
	 * @param showSessionDetail
	 *            the showSessionDetail to set
	 */
	public void setShowSessionDetail(boolean showSessionDetail) {
		this.showSessionDetail = showSessionDetail;
	}

	/**
	 * [PlanningSessionSaveBean.salleExamenList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 14:24:15
	 * @return the salleExamenList
	 */
	public List<SalleExamenDto> getSalleExamenList() {
		return salleExamenList;
	}

	/**
	 * [PlanningSessionSaveBean.salleExamenList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 14:24:15
	 * @param salleExamenList
	 *            the salleExamenList to set
	 */
	public void setSalleExamenList(List<SalleExamenDto> salleExamenList) {
		this.salleExamenList = salleExamenList;
	}

	/**
	 * [PlanningSessionSaveBean.salleExamenService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 14:27:04
	 * @return the salleExamenService
	 */
	public SalleExamenService getSalleExamenService() {
		return salleExamenService;
	}

	/**
	 * [PlanningSessionSaveBean.salleExamenService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 14:27:04
	 * @param salleExamenService
	 *            the salleExamenService to set
	 */
	public void setSalleExamenService(SalleExamenService salleExamenService) {
		this.salleExamenService = salleExamenService;
	}

	/**
	 * [PlanningSessionSaveBean.searchExamens] Method
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 14:27:12
	 */
	public void searchExamens() {
		try {

			salleExamenList = new ArrayList<SalleExamenDto>();
			if (planningSessionDto == null || planningSessionDto.getId() == 0) {
				return;
			}

			examensSessionList = examenSessionService
					.findBySession(planningSessionDto.getId());
			fillInscriptionResponsableExamen();
			prepareExamenSession();

		} catch (Exception e) {
			salleExamenList = new ArrayList<SalleExamenDto>();
			e.printStackTrace();
		}
	}

	/**
	 * [PlanningSessionSaveBean.fillInscriptionExamen] Method
	 * 
	 * @author MAKERRI Sofiane on : 5 févr. 2015 08:14:33
	 */
	private void fillInscriptionResponsableExamen() {
		if (examensSessionList != null) {
			for (ExamenSessionDto _examenSessionDto : examensSessionList) {
				List<InscriptionExamenDto> inscriptionExamenDtos = new ArrayList<InscriptionExamenDto>();
				List<ResponsableExamenDto> responsableExamenDtos = new ArrayList<ResponsableExamenDto>();
				List<SalleExamenDto> salleExamenDtos = _examenSessionDto
						.getSalleExamenDtos();
				if (salleExamenDtos != null) {
					for (SalleExamenDto salleExamenDto : salleExamenDtos) {
						inscriptionExamenDtos.addAll(salleExamenDto
								.getEtudiants());
						responsableExamenDtos.addAll(salleExamenDto
								.getResponsables());
					}
				}
				_examenSessionDto
						.setInscriptionExamenDtos(inscriptionExamenDtos);
				_examenSessionDto
						.setResponsableExamenDtos(responsableExamenDtos);
			}
		}
	}

	/**
	 * [PlanningSessionSaveBean.examenBundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 15:54:31
	 * @return the examenBundle
	 */
	public ResourceBundle getExamenBundle() {
		return examenBundle;
	}

	/**
	 * [PlanningSessionSaveBean.examenBundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 15:54:31
	 * @param examenBundle
	 *            the examenBundle to set
	 */
	public void setExamenBundle(ResourceBundle examenBundle) {
		this.examenBundle = examenBundle;
	}

	/**
	 * [PlanningSessionSaveBean.deroulementMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 15:54:45
	 * @return the deroulementMode
	 */
	public boolean isDeroulementMode() {
		return deroulementMode;
	}

	/**
	 * [PlanningSessionSaveBean.deroulementMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 15:54:45
	 * @param deroulementMode
	 *            the deroulementMode to set
	 */
	public void setDeroulementMode(boolean deroulementMode) {
		this.deroulementMode = deroulementMode;
	}

	/**
	 * [PlanningSessionSaveBean.showExamenDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 08:20:52
	 * @return the showExamenDetail
	 */
	public boolean isShowExamenDetail() {
		return showExamenDetail;
	}

	/**
	 * [PlanningSessionSaveBean.showExamenDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 08:20:52
	 * @param showExamenDetail
	 *            the showExamenDetail to set
	 */
	public void setShowExamenDetail(boolean showExamenDetail) {
		this.showExamenDetail = showExamenDetail;
	}

	/**
	 * [PlanningSessionSaveBean.showDeroulementDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 12:02:31
	 * @return the showDeroulementDetail
	 */
	public boolean isShowDeroulementDetail() {
		return showDeroulementDetail;
	}

	/**
	 * [PlanningSessionSaveBean.showDeroulementDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 12:02:31
	 * @param showDeroulementDetail
	 *            the showDeroulementDetail to set
	 */
	public void setShowDeroulementDetail(boolean showDeroulementDetail) {
		this.showDeroulementDetail = showDeroulementDetail;
	}

	/**
	 * [PlanningSessionSaveBean.examenSessionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 15:16:29
	 * @return the examenSessionDto
	 */
	public ExamenSessionDto getExamenSessionDto() {
		return examenSessionDto;
	}

	/**
	 * [PlanningSessionSaveBean.examenSessionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 15:16:29
	 * @param examenSessionDto
	 *            the examenSessionDto to set
	 */
	public void setExamenSessionDto(ExamenSessionDto examenSessionDto) {
		this.examenSessionDto = examenSessionDto;
	}

	/**
	 * [PlanningSessionSaveBean.searchBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 janv. 2015 15:06:28
	 * @return the searchBean
	 */
	public SearchBean getSearchBean() {
		return searchBean;
	}

	/**
	 * [PlanningSessionSaveBean.searchBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 janv. 2015 15:06:28
	 * @param searchBean
	 *            the searchBean to set
	 */
	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}

	/**
	 * [PlanningSessionSaveBean.inclureControle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 16:03:35
	 * @return the inclureControle
	 */
	public boolean isInclureControle() {
		return inclureControle;
	}

	/**
	 * [PlanningSessionSaveBean.inclureControle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 16:03:35
	 * @param inclureControle
	 *            the inclureControle to set
	 */
	public void setInclureControle(boolean inclureControle) {
		this.inclureControle = inclureControle;
	}

	/**
	 * [PlanningSessionSaveBean.printMgrBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 févr. 2015 10:36:58
	 * @return the printMgrBean
	 */
	public PrintMgrBean getPrintMgrBean() {
		return printMgrBean;
	}

	/**
	 * [PlanningSessionSaveBean.printMgrBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 févr. 2015 10:36:58
	 * @param printMgrBean
	 *            the printMgrBean to set
	 */
	public void setPrintMgrBean(PrintMgrBean printMgrBean) {
		this.printMgrBean = printMgrBean;
	}

	/**
	 * [PlanningSessionSaveBean.impressionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 févr. 2015 10:36:58
	 * @return the impressionService
	 */
	public ImpressionService getImpressionService() {
		return impressionService;
	}

	/**
	 * [PlanningSessionSaveBean.impressionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 févr. 2015 10:36:58
	 * @param impressionService
	 *            the impressionService to set
	 */
	public void setImpressionService(ImpressionService impressionService) {
		this.impressionService = impressionService;
	}

}
