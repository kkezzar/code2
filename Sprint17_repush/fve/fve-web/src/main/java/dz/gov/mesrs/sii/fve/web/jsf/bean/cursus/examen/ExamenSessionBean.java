package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.dozer.DozerBeanMapper;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.InscriptionExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.PlanningSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ResponsableExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.SalleExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.UniteEnseignementDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.ExamenSessionService;
import dz.gov.mesrs.sii.fve.business.service.cursus.ParcoursIndividualiseService;
import dz.gov.mesrs.sii.fve.business.service.examen.PlanningSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.SalleExamenService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RepartitionUeService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SearchBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

/**
 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
 */
@ManagedBean(name = "examenSessionBean")
@ViewScoped
public class ExamenSessionBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:10
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	private final ResourceBundle examenBundle;
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
	@ManagedProperty("#{planningSessionSaveBean}")
	private PlanningSessionSaveBean planningSessionSaveBean;
	@ManagedProperty("#{examenSessionService}")
	private ExamenSessionService examenSessionService;
	@ManagedProperty("#{salleExamenService}")
	private SalleExamenService salleExamenService;
	@ManagedProperty("#{repartitionUeService}")
	private RepartitionUeService repartitionUeService;
	@ManagedProperty("#{rattachementMcService}")
	private RattachementMcService rattachementMcService;

	@ManagedProperty("#{parcoursIndividualiseService}")
	private ParcoursIndividualiseService parcoursIndividualiseService;
	private ExamenSessionDto examenSessionDto;
	private List<SelectItem> ueList;
	private List<SelectItem> rattachementMcList;
	private List<SelectItem> modeleExamenList;
	private List<SelectItem> typeExamenList;
	@ManagedProperty("#{inscriptionExamenBean}")
	private InscriptionExamenBean inscriptionExamenBean;
	@ManagedProperty("#{deroulementInscriptionExamenBean}")
	private DeroulementInscriptionExamenBean deroulementInscriptionExamenBean;
	@ManagedProperty("#{deroulementAbsenceResponsableBean}")
	private DeroulementAbsenceResponsableBean deroulementAbsenceResponsableBean;
	@ManagedProperty("#{salleExamenBean}")
	private SalleExamenBean salleExamenBean;
	@ManagedProperty("#{responsableExamenBean}")
	private ResponsableExamenBean responsableExamenBean;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{searchBean}")
	private SearchBean searchBean;
	// private boolean showSessionDetail;
	private PlanningSessionDto planningSessionDto;
	private boolean deroulementMode;
	private boolean modelExist;
	private Long modeleExamenId;
	private List<ExamenSessionDto> examensSessionList;

	/**
	 * [ParcoursIndividualiseBean.ParcoursIndividualiseBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
	 */
	public ExamenSessionBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		examenBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.EXAMEN_BUNDLE_MSG_NAME);
		planningSessionDto = new PlanningSessionDto();
		deroulementMode = false;
		String viewId = facesContext.getViewRoot().getViewId();
		if (viewId != null && viewId.toLowerCase().contains("deroul")) {
			deroulementMode = true;
		}
	}

	/**
	 * 
	 * [ExamenSessionBean.init] Method
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:10:41
	 */
	@PostConstruct
	public void init() {
		examenSessionDto = new ExamenSessionDto();
		examenSessionDto.setDateExamen(new Date());
		examenSessionDto.setDuree(60);
		if (planningSessionSaveBean != null) {
			if (examenSessionDto.getOuvertureOffreFormationId() == null) {
				examenSessionDto.setOuvertureOffreFormationId(searchBean
						.getOofId());
			}
			if (examenSessionDto.getIdPeriode() == null) {
				examenSessionDto.setIdPeriode(searchBean.getPeriodeId());
			}
			if (planningSessionSaveBean.getPlanningSessionDto() != null) {
				examenSessionDto.setPlanningSessionId(planningSessionSaveBean
						.getPlanningSessionDto().getId());
			}
			if (planningSessionSaveBean.getExamenSessionDto() != null) {
				examenSessionDto = planningSessionSaveBean
						.getExamenSessionDto();
				examenSelected();
			}
			examensSessionList = planningSessionSaveBean
					.getExamensSessionList();

		}
		modeleExamenId = null;
		loadRattachementMcList();
		loadModeleMcList();
		loadTypeExamenList();

	}

	/**
	 * 
	 * [ExamenSessionBean.loadTypeExamenList] Method
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 09:54:51
	 */
	private void loadTypeExamenList() {
		try {
			typeExamenList = new ArrayList<SelectItem>();
			List<NomenclatureDto> list = nomenclatureService
					.findByName(CursusConstants.NC_TYPE_EXAMEN);

			for (NomenclatureDto type : list) {
				SelectItem item = new SelectItem(type.getCode(),
						type.getLibelleLongFr());
				typeExamenList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * [ExamenSessionBean.addExamen] Method
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 10:35:00
	 */
	public void addExamen() {

		examenSessionDto = new ExamenSessionDto();
		examenSessionDto.setPlanningSessionId(planningSessionSaveBean
				.getPlanningSessionDto().getId());
		examenSessionDto
				.setAnneeAcademiqueId(searchBean.getAnneeAcademiqueId());
		examenSessionDto.setOuvertureOffreFormationId(searchBean.getOofId());
		examenSessionDto.setIdPeriode(searchBean.getPeriodeId());
		examenSessionDto.setDateExamen(new Date());
		examenSessionDto.setDuree(60);
		modeleExamenId = null;
		loadRattachementMcList();
		loadModeleMcList();
		planningSessionSaveBean.setHideExamen(false);
		planningSessionSaveBean.setShowSessionDetail(true);
		// planningSessionSaveBean.setShowDetail(true);
		salleExamenBean.init();
		salleExamenBean.examenChanged();
		// salleExamenBean.loadExamenList(planningSessionSaveBean
		// .getPlanningSessionDto().getId());
		inscriptionExamenBean.init();
		inscriptionExamenBean.getSalleExamenDto().setExamenSessionId(null);
		inscriptionExamenBean.examenChanged();
		// inscriptionExamenBean.loadExamenList(planningSessionSaveBean
		// .getPlanningSessionDto().getId());
		responsableExamenBean.init();
		responsableExamenBean.getSalleExamenDto().setExamenSessionId(null);
		responsableExamenBean.examenChanged();
		// loadUebyOfAndPeriode();
	}

	/**
	 * [ExamenSessionBean.applyModel] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 08:25:38
	 */
	public boolean applyModel() {
		try {
			List<SalleExamenDto> salleModeleList = salleExamenService
					.findByExamen(examenSessionDto.getModeleExamenId());
			/****
			 * supprimer les salle de l'examen en cours : suppression en cascade
			 *****/
			List<SalleExamenDto> currentSalleList = salleExamenService
					.findByExamen(examenSessionDto.getId());
			// List<SalleExamenDto> currentSalleList = examenSessionDto
			// .getSalleExamenDtos();
			if (currentSalleList != null) {
				for (SalleExamenDto currentSalle : currentSalleList) {
					salleExamenService.remove(currentSalle);
				}
			}

			if (salleModeleList != null) {
				for (SalleExamenDto salle : salleModeleList) {
					salle = salleExamenService.findById(salle.getId());// refresh
					salle.setExamenSessionId(examenSessionDto.getId());
					salle.setId(null);
					List<InscriptionExamenDto> modelEtudiantList = salle
							.getEtudiants();
					List<ResponsableExamenDto> modeleResponsableList = salle
							.getResponsables();
					List<InscriptionExamenDto> newEtudiantList = null;
					List<ResponsableExamenDto> newResponsableList = null;
					if (modelEtudiantList != null) {
						newEtudiantList = new ArrayList<InscriptionExamenDto>();
						for (InscriptionExamenDto _etudiant : modelEtudiantList) {
							InscriptionExamenDto newEtudiant = new InscriptionExamenDto();
							newEtudiant
									.setDossierInscriptionAdministrativeId(_etudiant
											.getDossierInscriptionAdministrativeId());
							newEtudiantList.add(newEtudiant);
						}
					}
					if (modeleResponsableList != null) {
						newResponsableList = new ArrayList<ResponsableExamenDto>();
						for (ResponsableExamenDto _responsable : modeleResponsableList) {
							ResponsableExamenDto newResponsable = new ResponsableExamenDto();
							newResponsable.setIndividuId(_responsable
									.getIndividuId());
							newResponsableList.add(newResponsable);
						}
					}
					salle.setEtudiants(newEtudiantList);
					salle.setResponsables(newResponsableList);
					salleExamenService.insertOrUpdate(salle);

				}
			}

			examenSelected();
			return true;
		} catch (Exception e) {
			e.getStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
		return false;
	}

	/**
	 * 
	 * [ExamenSessionBean.saveExamen] Method
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 10:47:39
	 */
	public void saveExamen() {

		boolean nouveau = false;
		try {
			if (examenSessionDto == null) {
				return;
			}
			if (planningSessionSaveBean.getPlanningSessionDto() != null) {
				examenSessionDto.setPlanningSessionId(planningSessionSaveBean
						.getPlanningSessionDto().getId());
			}
			if (examenSessionDto.getDateExamen() == null
					|| examenSessionDto.getDateExamen().before(
							planningSessionSaveBean.getPlanningSessionDto()
									.getDateDebut())
					|| examenSessionDto.getDateExamen().after(
							planningSessionSaveBean.getPlanningSessionDto()
									.getDateFin())) {
				Utility.showErrorMessage(examenBundle
						.getString("examen_error_date_examen"));
				return;
			}
			if (examenSessionDto.getPlanningSessionId() != null) {
				ExamenSessionDto _examenSessionDto = examenSessionService
						.verifyExistingExamen(
								examenSessionDto.getPlanningSessionId(),
								examenSessionDto.getRattachementMcId(),
								examenSessionDto.getDateExamen(),
								examenSessionDto.getHeureDebut(),
								examenSessionDto.getRefTypeExamen());
				if (_examenSessionDto != null
						&& _examenSessionDto.getId() != examenSessionDto
								.getId()) {
					Utility.showErrorMessage(examenBundle
							.getString("examen_error_exist"));
					return;
				}
			}
			if (examenSessionDto.getId() == 0) {
				examenSessionDto.setDateCreation(new Date());
				nouveau = true;

			}
			Date _heureFin = addMinutesToDate(examenSessionDto.getDuree(),
					examenSessionDto.getHeureDebut());
			examenSessionDto.setHeureFin(_heureFin);
			// Default deroulement
			examenSessionDto.setDateExamenReelle(examenSessionDto
					.getDateExamen());
			examenSessionDto.setHeureDebutReelle(examenSessionDto
					.getHeureDebut());
			examenSessionDto.setHeureFinReelle(examenSessionDto.getHeureFin());

			examenSessionDto = examenSessionService
					.insertOrUpdate(examenSessionDto);
			if (nouveau) {

			}
			if (examenSessionDto.getModeleExamenId() != null
					&& !examenSessionDto.getModeleExamenId().equals(
							modeleExamenId)) {

				boolean success = applyModel();
				if (success) {
					modeleExamenId = examenSessionDto.getModeleExamenId();
					if (nouveau) {
						Utility.showSuccessSaveMessage();
					} else {
						Utility.showSuccessUpdateMessage();
					}
				}
				return;
			}
			loadModeleMcList();
			// examenSessionDto = null;

			// Refresh DATA
			planningSessionSaveBean.loadExamenSessionList();
			examensSessionList = planningSessionSaveBean
					.getExamensSessionList();
			salleExamenBean.init();
			salleExamenBean.setExamenSessionDto(examenSessionDto);
			salleExamenBean.examenChanged();
			salleExamenBean.loadExamenList(planningSessionSaveBean
					.getPlanningSessionDto().getId());
			inscriptionExamenBean.init();
			inscriptionExamenBean.getSalleExamenDto().setExamenSessionId(
					examenSessionDto.getId());
			inscriptionExamenBean.examenChanged();
			inscriptionExamenBean.loadExamenList(planningSessionSaveBean
					.getPlanningSessionDto().getId());

			responsableExamenBean.init();
			inscriptionExamenBean.getSalleExamenDto().setExamenSessionId(
					examenSessionDto.getId());
			// responsableExamenBean.loadExamenList(planningSessionSaveBean
			// .getPlanningSessionDto().getId());
			responsableExamenBean.examenChanged();
			if (nouveau) {
				Utility.showSuccessSaveMessage();
			} else {
				Utility.showSuccessUpdateMessage();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());

		}
	}

	/**
	 * 
	 * [ExamenSessionBean.deleteExamen] Method
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 10:47:39
	 */
	public void deleteExamen() {
		try {
			if (examenSessionDto == null) {
				return;
			}

			if (examenSessionDto.getId() != 0) {

				// supprimer les responsable si le groupe est changer
				// absenceResponsableService.removeByExamen(examenSessionDto
				// .getId());
				List<SalleExamenDto> _listSalle = salleExamenService
						.findByExamen(examenSessionDto.getId());
				if (_listSalle != null && !_listSalle.isEmpty()) {
					Utility.showErrorMessage(examenBundle
							.getString("examen_error_suppression_salle_affecte"));
					return;
				}

				examenSessionService.remove(examenSessionDto);
				planningSessionSaveBean.loadExamenSessionList();
				examensSessionList = planningSessionSaveBean
						.getExamensSessionList();
				inscriptionExamenBean.init();
				salleExamenBean.init();
				responsableExamenBean.init();
				examenSessionDto = new ExamenSessionDto();
				Utility.showSuccessDeleteMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());

		}
	}

	/**
	 * 
	 * [ExamenSessionBean.onExamenSelect] Method
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:10:46
	 * @param event
	 */
	public void onExamenSelect(SelectEvent event) {
		try {
			ExamenSessionDto _examenSessionDto = ((ExamenSessionDto) event
					.getObject());
			if (_examenSessionDto.getId() == examenSessionDto.getId()) {
				return;
			}
			examenSessionDto = _examenSessionDto;
			modeleExamenId = examenSessionDto.getModeleExamenId();
			examenSelected();

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [ExamenSessionBean.examenSelected] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 15:18:19
	 */
	private void examenSelected() {
		if (deroulementMode) {
			deroulementInscriptionExamenBean.getSalleExamenDto()
					.setExamenSessionId(examenSessionDto.getId());
			deroulementInscriptionExamenBean.examenChanged();
			deroulementAbsenceResponsableBean.getSalleExamenDto()
					.setExamenSessionId(examenSessionDto.getId());
			deroulementAbsenceResponsableBean.examenChanged();

		} else {
			// loadUebyOfAndPeriode();
			loadRattachementMcList();
			loadModeleMcList();
			planningSessionSaveBean.setHideExamen(false);
			salleExamenBean.init();
			salleExamenBean.setExamenSessionDto(examenSessionDto);
			salleExamenBean.examenChanged();
			inscriptionExamenBean.init();
			inscriptionExamenBean.getSalleExamenDto().setExamenSessionId(
					examenSessionDto.getId());
			inscriptionExamenBean.examenChanged();
			inscriptionExamenBean.setRattachementMcId(examenSessionDto
					.getRattachementMcId());
			responsableExamenBean.init();
			responsableExamenBean.getSalleExamenDto().setExamenSessionId(
					examenSessionDto.getId());
			responsableExamenBean.examenChanged();
		}
		planningSessionSaveBean.setShowSessionDetail(true);
		planningSessionSaveBean.setShowDeroulementDetail(true);
	}

	/**
	 * 
	 * [ExamenSessionBean.loadUebyOfAndPeriode] Method
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 11:53:12
	 */
	public void loadUebyOfAndPeriode() {
		ueList = new ArrayList<SelectItem>();

		try {
			if (planningSessionSaveBean.getPlanningSessionDto()
					.getOuvertureOffreFormationId() == null
					|| planningSessionSaveBean.getPlanningSessionDto()
							.getIdPeriode() == null) {
				return;
			}

			List<UniteEnseignementDto> _listUes = repartitionUeService
					.findUeOofAndPeriode(planningSessionSaveBean
							.getPlanningSessionDto()
							.getOuvertureOffreFormationId(),
							planningSessionSaveBean.getPlanningSessionDto()
									.getIdPeriode().intValue());
			for (UniteEnseignementDto ue : _listUes) {

				SelectItem selectItem = new SelectItem(ue.getId(),
						ue.getLibelleFr());
				ueList.add(selectItem);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [ExamenSessionBean.loadRattachementMcList] Method
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 11:55:49
	 */
	private void loadRattachementMcList() {
		examenSessionDto.setOuvertureOffreFormationId(searchBean.getOofId());
		examenSessionDto.setIdPeriode(searchBean.getPeriodeId());

		rattachementMcList = new ArrayList<SelectItem>();
		try {

			if (examenSessionDto != null
					&& examenSessionDto.getOuvertureOffreFormationId() != null
					&& examenSessionDto.getIdPeriode() != null) {
				rattachementMcList = utilBean.loadMcGroupedByUe(
						examenSessionDto.getOuvertureOffreFormationId(),
						examenSessionDto.getIdPeriode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [ExamenSessionBean.loadModeleMcList] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 16:49:58
	 */
	private void loadModeleMcList() {

		modeleExamenList = new ArrayList<SelectItem>();
		modelExist = false;
		try {
			// NomenclatureDto _ncNormale = utilBean.loadNcTypeSessionNormale();
			if (planningSessionDto != null
					&& planningSessionDto.getNcTypeSessionId() != null /*
																		 * &&
																		 * _ncNormale
																		 * !=
																		 * null
																		 * &&
																		 * planningSessionDto
																		 * .
																		 * getNcTypeSessionId
																		 * (
																		 * ).equals
																		 * (
																		 * _ncNormale
																		 * .
																		 * getId
																		 * ())
																		 */) {
				if (examenSessionDto != null && planningSessionDto.getId() != 0) {
					List<ExamenSessionDto> _list = examenSessionService
							.findExamenByPlanning(planningSessionDto.getId());
					if (_list != null) {
						for (ExamenSessionDto _examen : _list) {
							if (examenSessionDto.getRattachementMcId() != null
									&& !_examen.getRattachementMcId().equals(
											examenSessionDto
													.getRattachementMcId())) {
								modelExist = true;
								modeleExamenList.add(new SelectItem(_examen
										.getId(), _examen.getMcLibelleFr()));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * [ExamenSessionBean.onRowEdit] Method
	 * 
	 * @author BELDI Jamel on : 30 sept. 2014 17:10:22
	 * @param event
	 */
	public void onRowEdit(RowEditEvent event) {

		try {
			ExamenSessionDto element = ((ExamenSessionDto) event.getObject());
			if (element != null
					&& element.getNbInscrits() != null
					&& element.getNbCopieRemises() != null
					&& element.getNbCopieRemises().compareTo(
							element.getNbInscrits()) > 0) {
				Utility.showErrorMessage(examenBundle
						.getString("examen_error_deroulement_copie_remise"));
				return;
			}
			if (element.getDateExamenReelle() == null
					|| element.getDateExamenReelle().before(
							planningSessionSaveBean.getPlanningSessionDto()
									.getDateDebut())
					|| element.getDateExamenReelle().after(
							planningSessionSaveBean.getPlanningSessionDto()
									.getDateFin())) {
				Utility.showErrorMessage(examenBundle
						.getString("examen_error_date_examen_reelle"));
				return;
			}
			if (element != null
					&& element.getHeureFinReelle() != null
					&& element.getHeureDebutReelle() != null
					&& element.getHeureFinReelle().before(
							element.getHeureDebutReelle())) {
				Utility.showErrorMessage(examenBundle
						.getString("examen_error_deroulement_heure_reelle"));

				return;
			}
			examenSessionService.insertOrUpdate(element);
			Utility.showSuccessSaveMessage();

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());

		}
	}

	/**
	 * 
	 * [ExamenSessionBean.onRowCancel] Method
	 * 
	 * @author BELDI Jamel on : 30 sept. 2014 17:10:26
	 * @param event
	 */
	public void onRowCancel(RowEditEvent event) {

	}

	/**
	 * 
	 * [ExamenSessionBean.addMinutesToDate] Method
	 * 
	 * @author BELDI Jamel on : 30 sept. 2014 18:19:52
	 * @param minutes
	 * @param beforeTime
	 * @return
	 */
	private Date addMinutesToDate(int minutes, Date beforeTime) {
		final long ONE_MINUTE_IN_MILLIS = 60000;// millisecs

		long curTimeInMs = beforeTime.getTime();
		Date afterAddingMins = new Date(curTimeInMs
				+ (minutes * ONE_MINUTE_IN_MILLIS));
		return afterAddingMins;
	}

	/**
	 * [ExamenSessionBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [ExamenSessionBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [ExamenSessionBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [ExamenSessionBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [ExamenSessionBean.offreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [ExamenSessionBean.offreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [ExamenSessionBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [ExamenSessionBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [ExamenSessionBean.niveauService] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [ExamenSessionBean.niveauService] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @param niveauService
	 *            the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [ExamenSessionBean.periodeService] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [ExamenSessionBean.periodeService] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @param periodeService
	 *            the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [ExamenSessionBean.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [ExamenSessionBean.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [ExamenSessionBean.situationService] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [ExamenSessionBean.situationService] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [ExamenSessionBean.planningSessionService] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @return the planningSessionService
	 */
	public PlanningSessionService getPlanningSessionService() {
		return planningSessionService;
	}

	/**
	 * [ExamenSessionBean.planningSessionService] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:08:22
	 * @param planningSessionService
	 *            the planningSessionService to set
	 */
	public void setPlanningSessionService(
			PlanningSessionService planningSessionService) {
		this.planningSessionService = planningSessionService;
	}

	/**
	 * [ExamenSessionBean.planningSessionSaveBean] Getter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:53:33
	 * @return the planningSessionSaveBean
	 */
	public PlanningSessionSaveBean getPlanningSessionSaveBean() {
		return planningSessionSaveBean;
	}

	/**
	 * [ExamenSessionBean.planningSessionSaveBean] Setter
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 12:53:33
	 * @param planningSessionSaveBean
	 *            the planningSessionSaveBean to set
	 */
	public void setPlanningSessionSaveBean(
			PlanningSessionSaveBean planningSessionSaveBean) {
		if (planningSessionSaveBean != null) {
			planningSessionDto = planningSessionSaveBean
					.getPlanningSessionDto();
		}
		this.planningSessionSaveBean = planningSessionSaveBean;
	}

	/**
	 * [ExamenSessionBean.examenSessionService] Getter
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 10:33:06
	 * @return the examenSessionService
	 */
	public ExamenSessionService getExamenSessionService() {
		return examenSessionService;
	}

	/**
	 * [ExamenSessionBean.examenSessionService] Setter
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 10:33:06
	 * @param examenSessionService
	 *            the examenSessionService to set
	 */
	public void setExamenSessionService(
			ExamenSessionService examenSessionService) {
		this.examenSessionService = examenSessionService;
	}

	/**
	 * [ExamenSessionBean.examenSessionDto] Getter
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 10:34:21
	 * @return the examenSessionDto
	 */
	public ExamenSessionDto getExamenSessionDto() {
		return examenSessionDto;
	}

	/**
	 * [ExamenSessionBean.examenSessionDto] Setter
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 10:34:21
	 * @param examenSessionDto
	 *            the examenSessionDto to set
	 */
	public void setExamenSessionDto(ExamenSessionDto examenSessionDto) {
		this.examenSessionDto = examenSessionDto;
	}

	/**
	 * [ExamenSessionBean.ueList] Getter
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 11:43:22
	 * @return the ueList
	 */
	public List<SelectItem> getUeList() {
		return ueList;
	}

	/**
	 * [ExamenSessionBean.ueList] Setter
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 11:43:22
	 * @param ueList
	 *            the ueList to set
	 */
	public void setUeList(List<SelectItem> ueList) {
		this.ueList = ueList;
	}

	/**
	 * [ExamenSessionBean.rattachementMcList] Getter
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 11:43:22
	 * @return the rattachementMcList
	 */
	public List<SelectItem> getRattachementMcList() {
		return rattachementMcList;
	}

	/**
	 * [ExamenSessionBean.rattachementMcList] Setter
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 11:43:22
	 * @param rattachementMcList
	 *            the rattachementMcList to set
	 */
	public void setRattachementMcList(List<SelectItem> rattachementMcList) {
		this.rattachementMcList = rattachementMcList;
	}

	/**
	 * [ExamenSessionBean.repartitionUeService] Getter
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 11:50:37
	 * @return the repartitionUeService
	 */
	public RepartitionUeService getRepartitionUeService() {
		return repartitionUeService;
	}

	/**
	 * [ExamenSessionBean.repartitionUeService] Setter
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 11:50:37
	 * @param repartitionUeService
	 *            the repartitionUeService to set
	 */
	public void setRepartitionUeService(
			RepartitionUeService repartitionUeService) {
		this.repartitionUeService = repartitionUeService;
	}

	/**
	 * [ExamenSessionBean.rattachementMcService] Getter
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 11:57:13
	 * @return the rattachementMcService
	 */
	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	/**
	 * [ExamenSessionBean.rattachementMcService] Setter
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 11:57:13
	 * @param rattachementMcService
	 *            the rattachementMcService to set
	 */
	public void setRattachementMcService(
			RattachementMcService rattachementMcService) {
		this.rattachementMcService = rattachementMcService;
	}

	/**
	 * [ExamenSessionBean.typeExamenList] Getter
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 15:15:03
	 * @return the typeExamenList
	 */
	public List<SelectItem> getTypeExamenList() {
		return typeExamenList;
	}

	/**
	 * [ExamenSessionBean.typeExamenList] Setter
	 * 
	 * @author BELDI Jamel on : 23 sept. 2014 15:15:03
	 * @param typeExamenList
	 *            the typeExamenList to set
	 */
	public void setTypeExamenList(List<SelectItem> typeExamenList) {
		this.typeExamenList = typeExamenList;
	}

	/**
	 * [ExamenSessionBean.parcoursIndividualiseService] Getter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 12:03:31
	 * @return the parcoursIndividualiseService
	 */
	public ParcoursIndividualiseService getParcoursIndividualiseService() {
		return parcoursIndividualiseService;
	}

	/**
	 * [ExamenSessionBean.parcoursIndividualiseService] Setter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 12:03:31
	 * @param parcoursIndividualiseService
	 *            the parcoursIndividualiseService to set
	 */
	public void setParcoursIndividualiseService(
			ParcoursIndividualiseService parcoursIndividualiseService) {
		this.parcoursIndividualiseService = parcoursIndividualiseService;
	}

	/**
	 * [ExamenSessionBean.inscriptionExamenBean] Getter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 15:07:32
	 * @return the inscriptionExamenBean
	 */
	public InscriptionExamenBean getInscriptionExamenBean() {
		return inscriptionExamenBean;
	}

	/**
	 * [ExamenSessionBean.inscriptionExamenBean] Setter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 15:07:32
	 * @param inscriptionExamenBean
	 *            the inscriptionExamenBean to set
	 */
	public void setInscriptionExamenBean(
			InscriptionExamenBean inscriptionExamenBean) {
		this.inscriptionExamenBean = inscriptionExamenBean;
	}

	/**
	 * [ExamenSessionBean.salleExamenBean] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 11:05:12
	 * @return the salleExamenBean
	 */
	public SalleExamenBean getSalleExamenBean() {
		return salleExamenBean;
	}

	/**
	 * [ExamenSessionBean.salleExamenBean] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 11:05:12
	 * @param salleExamenBean
	 *            the salleExamenBean to set
	 */
	public void setSalleExamenBean(SalleExamenBean salleExamenBean) {
		this.salleExamenBean = salleExamenBean;
	}

	/**
	 * [ExamenSessionBean.responsableExamenBean] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 17:56:47
	 * @return the responsableExamenBean
	 */
	public ResponsableExamenBean getResponsableExamenBean() {
		return responsableExamenBean;
	}

	/**
	 * [ExamenSessionBean.responsableExamenBean] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 17:56:47
	 * @param responsableExamenBean
	 *            the responsableExamenBean to set
	 */
	public void setResponsableExamenBean(
			ResponsableExamenBean responsableExamenBean) {
		this.responsableExamenBean = responsableExamenBean;
	}

	/**
	 * [ExamenSessionBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 16:58:11
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [ExamenSessionBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 16:58:11
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [ExamenSessionBean.planningSessionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 17:11:11
	 * @return the planningSessionDto
	 */
	public PlanningSessionDto getPlanningSessionDto() {
		return planningSessionDto;
	}

	/**
	 * [ExamenSessionBean.planningSessionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 17:11:11
	 * @param planningSessionDto
	 *            the planningSessionDto to set
	 */
	public void setPlanningSessionDto(PlanningSessionDto planningSessionDto) {
		this.planningSessionDto = planningSessionDto;
	}

	/**
	 * [ExamenSessionBean.nomenclatureService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 08:24:30
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [ExamenSessionBean.nomenclatureService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 08:24:30
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [ExamenSessionBean.deroulementMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 15:18:51
	 * @return the deroulementMode
	 */
	public boolean isDeroulementMode() {
		return deroulementMode;
	}

	/**
	 * [ExamenSessionBean.deroulementMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 15:18:51
	 * @param deroulementMode
	 *            the deroulementMode to set
	 */
	public void setDeroulementMode(boolean deroulementMode) {
		this.deroulementMode = deroulementMode;
	}

	/**
	 * [ExamenSessionBean.deroulementInscriptionExamenBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 15:21:03
	 * @return the deroulementInscriptionExamenBean
	 */
	public DeroulementInscriptionExamenBean getDeroulementInscriptionExamenBean() {
		return deroulementInscriptionExamenBean;
	}

	/**
	 * [ExamenSessionBean.deroulementInscriptionExamenBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 15:21:03
	 * @param deroulementInscriptionExamenBean
	 *            the deroulementInscriptionExamenBean to set
	 */
	public void setDeroulementInscriptionExamenBean(
			DeroulementInscriptionExamenBean deroulementInscriptionExamenBean) {
		this.deroulementInscriptionExamenBean = deroulementInscriptionExamenBean;
	}

	/**
	 * [ExamenSessionBean.deroulementAbsenceResponsableBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 15:25:32
	 * @return the deroulementAbsenceResponsableBean
	 */
	public DeroulementAbsenceResponsableBean getDeroulementAbsenceResponsableBean() {
		return deroulementAbsenceResponsableBean;
	}

	/**
	 * [ExamenSessionBean.deroulementAbsenceResponsableBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 15:25:32
	 * @param deroulementAbsenceResponsableBean
	 *            the deroulementAbsenceResponsableBean to set
	 */
	public void setDeroulementAbsenceResponsableBean(
			DeroulementAbsenceResponsableBean deroulementAbsenceResponsableBean) {
		this.deroulementAbsenceResponsableBean = deroulementAbsenceResponsableBean;
	}

	/**
	 * [ExamenSessionBean.salleExamenService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 11:22:00
	 * @return the salleExamenService
	 */
	public SalleExamenService getSalleExamenService() {
		return salleExamenService;
	}

	/**
	 * [ExamenSessionBean.salleExamenService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 11:22:00
	 * @param salleExamenService
	 *            the salleExamenService to set
	 */
	public void setSalleExamenService(SalleExamenService salleExamenService) {
		this.salleExamenService = salleExamenService;
	}

	/**
	 * [ExamenSessionBean.modeleExamenList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 08:45:08
	 * @return the modeleExamenList
	 */
	public List<SelectItem> getModeleExamenList() {
		return modeleExamenList;
	}

	/**
	 * [ExamenSessionBean.modeleExamenList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 08:45:08
	 * @param modeleExamenList
	 *            the modeleExamenList to set
	 */
	public void setModeleExamenList(List<SelectItem> modeleExamenList) {
		this.modeleExamenList = modeleExamenList;
	}

	/**
	 * [ExamenSessionBean.modelExist] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 17:02:53
	 * @return the modelExist
	 */
	public boolean isModelExist() {
		return modelExist;
	}

	/**
	 * [ExamenSessionBean.modelExist] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 17:02:53
	 * @param modelExist
	 *            the modelExist to set
	 */
	public void setModelExist(boolean modelExist) {
		this.modelExist = modelExist;
	}

	/**
	 * [ExamenSessionBean.modeleExamenId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 08:43:54
	 * @return the modeleExamenId
	 */
	public Long getModeleExamenId() {
		return modeleExamenId;
	}

	/**
	 * [ExamenSessionBean.modeleExamenId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 08:43:54
	 * @param modeleExamenId
	 *            the modeleExamenId to set
	 */
	public void setModeleExamenId(Long modeleExamenId) {
		this.modeleExamenId = modeleExamenId;
	}

	/**
	 * [ExamenSessionBean.examensSessionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 10:52:24
	 * @return the examensSessionList
	 */
	public List<ExamenSessionDto> getExamensSessionList() {
		examensSessionList = planningSessionSaveBean.getExamensSessionList();
		return examensSessionList;
	}

	/**
	 * [ExamenSessionBean.examensSessionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 10:52:24
	 * @param examensSessionList
	 *            the examensSessionList to set
	 */
	public void setExamensSessionList(List<ExamenSessionDto> examensSessionList) {
		this.examensSessionList = examensSessionList;
	}

	/**
	 * [ExamenSessionBean.searchBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 janv. 2015 15:09:02
	 * @return the searchBean
	 */
	public SearchBean getSearchBean() {
		return searchBean;
	}

	/**
	 * [ExamenSessionBean.searchBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 janv. 2015 15:09:02
	 * @param searchBean
	 *            the searchBean to set
	 */
	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}

}
