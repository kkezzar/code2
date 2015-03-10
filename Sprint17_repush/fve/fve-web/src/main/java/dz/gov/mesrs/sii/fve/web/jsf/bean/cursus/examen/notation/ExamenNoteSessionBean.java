/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation.NoteExamenBean.java] 
 * @author MAKERRI Sofiane on : 22 sept. 2014  10:16:33
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanUeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.InscriptionExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.NoteEliminatoireDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.PlanningSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RepartitionUeDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.ExamenSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanControleContinuService;
import dz.gov.mesrs.sii.fve.business.service.examen.InscriptionExamenService;
import dz.gov.mesrs.sii.fve.business.service.examen.NoteEliminatoireService;
import dz.gov.mesrs.sii.fve.business.service.examen.PlanningSessionService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.FileReaderExamenNotes;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.util.ConfigHolder;

/**
 * @author MAKERRI Sofiane on : 28 déc. 2014 17:06:51
 */
@ManagedBean(name = "examenNoteSessionBean")
@ViewScoped
public class ExamenNoteSessionBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 10:16:53
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(ExamenNoteSessionBean.class);
	private PlanningSessionDto planningSessionDto;
	private InscriptionExamenDto inscriptionExamenDto;
	private List<SelectItem> sessionExamenList;
	private List<SelectItem> planingExamenList;
	private List<InscriptionExamenDto> noteSessionList;
	private List<InscriptionExamenDto> oldNoteSessionList;
	private List<InscriptionExamenDto> filtredNoteSession;
	@ManagedProperty("#{examenSessionService}")
	private ExamenSessionService examenSessionService;
	@ManagedProperty("#{planningSessionService}")
	private PlanningSessionService planningSessionService;
	@ManagedProperty("#{inscriptionExamenService}")
	private InscriptionExamenService inscriptionExamenService;
	@ManagedProperty("#{bilanControleContinuService}")
	private BilanControleContinuService bilanControleContinuService;
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;
	@ManagedProperty(value = "#{noteEliminatoireService}")
	private NoteEliminatoireService noteEliminatoireService;
	@ManagedProperty(value = "#{configHolder}")
	private ConfigHolder configHolder;
	private ResourceBundle notebundle;
	private Long examenSessionId;
	private ExamenSessionDto examenSessionDto;
	private UploadedFile fileToImport;
	private String fileName;
	private String filePath;
	private List<ImportNoteEtudiantModel> importNoteEtudiantModelList;
	private String exportFileName;
	private Double noteMax;
	private boolean showDetail;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> ofList;
	private List<SelectItem> periodeList;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	private SituationEntiteDto situationPublie;
	private boolean editMode;
	private boolean noteImported;
	private List<InscriptionExamenDto> etudiants;
	private List<InscriptionExamenDto> notes;
	private List<RattachementMcDto> rattachementMcDtos;
	private boolean validate;
	private List<BilanSessionDto> bilanSessionDtos;
	private Integer anneeAcademiqueId;
	private Integer oofId;
	private Integer periodeId;
	private Long planningSessionId;
	private List<RepartitionUeDto> repartitionUeDtos;
	private NoteEliminatoireDto noteEliminatoireDto;

	/**
	 * [NoteExamenBean.NoteExamenBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 10:16:33
	 */
	public ExamenNoteSessionBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		notebundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.NOTE_EXAMEN_BUNDLE_MSG_NAME);
		editMode = true;
		validate = false;
		String viewId = facesContext.getViewRoot().getViewId();
		if (viewId != null && (viewId.toLowerCase().contains("valid"))) {
			validate = true;
		}
	}

	@PostConstruct
	public void init() {
		try {
			inscriptionExamenDto = new InscriptionExamenDto();
			planningSessionDto = new PlanningSessionDto();
			examenSessionDto = new ExamenSessionDto();
			// loadAnneeAcademique();
		} catch (Exception e) {

		}
		situationPublie = situationService
				.findBySituationEntiteByCodeSituation(
						UtilConstants.ENTITE_PLANNING_SESSION_CODE,
						UtilConstants.SITUTAION_PUBLIEE_CODE);
		noteImported = false;
		if (editMode) {
			anneeAcademiqueId = sessionBean.getIdAnneeAcademique();
			loadOffreFormationOuverte();
		} else {
			loadAnneeAcademique();
		}
	}

	/**
	 * [ExamenNoteSessionBean.loadStudentOfSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 16:55:48
	 */
	public void loadStudentOfSession() {
		try {
			if (planningSessionId != null) {
				etudiants = inscriptionExamenService
						.findByPlanning(planningSessionId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [ExamenNoteSessionBean.loadNotes] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 17:41:27
	 */
	public void loadNotes() {
		try {
			if (planningSessionId != 0) {
				notes = inscriptionExamenService
						.findNoteByPlanning(planningSessionId);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * [ExamenNoteSessionBean.loadPlanningSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 16:55:54
	 */
	public void loadPlanningSession() {
		try {
			if (oofId == null || periodeId == null) {
				return;
			}
			planingExamenList = new ArrayList<SelectItem>();
			PlanningSessionDto searchDto = new PlanningSessionDto();
			searchDto.setOuvertureOffreFormationId(oofId);
			searchDto.setIdPeriode(periodeId);
			searchDto.setAnneeAcademiqueId(anneeAcademiqueId);
			if (situationPublie != null) {
				searchDto.setSituationId(situationPublie.getId());
			}
			List<PlanningSessionDto> list = planningSessionService
					.findAdvanced(searchDto);
			if (list != null) {
				for (PlanningSessionDto item : list) {
					planingExamenList.add(new SelectItem(item.getId(), item
							.getIntitule()));
				}
				if (planingExamenList != null && planingExamenList.size() == 1) {
					planningSessionId = (Long) planingExamenList.get(0)
							.getValue();
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [ExamenNoteSessionBean.loadRepartitionUe] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 janv. 2015 10:36:21
	 */
	public void loadRepartitionUe(Integer oofId, Integer periodeId) {
		try {
			repartitionUeDtos = utilBean.loadRepartitionUe(oofId, periodeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [ExamenNoteSessionBean.loadAnneeAcademique] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:50:21
	 */
	public void loadAnneeAcademique() {
		try {
			anneeAcademiqueList = utilBean.loadAnneeAcademique();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [ExamenNoteSessionBean.loadOffreFormationOuverte] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:51:30
	 */
	public void loadOffreFormationOuverte() {
		ofList = new ArrayList<SelectItem>();
		try {
			if (anneeAcademiqueId == null) {
				return;
			}
			ofList = utilBean.loadOffreFormationOuverte(anneeAcademiqueId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [ExamenNoteSessionBean.loadPeriodes] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:51:55
	 */
	private void loadPeriodes() {
		try {
			periodeList = new ArrayList<SelectItem>();
			if (oofId == null) {
				return;
			}
			periodeList = utilBean.loadPeriodeByNiveau(oofId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [NoteExamenBean.loadExamenSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 11:01:24
	 */
	public void loadExamenSession() {
		try {
			if (oofId == null || periodeId == null || anneeAcademiqueId == null) {
				return;
			}
			PlanningSessionDto searchDto = new PlanningSessionDto();
			searchDto.setOuvertureOffreFormationId(oofId);
			searchDto.setIdPeriode(periodeId);
			searchDto.setAnneeAcademiqueId(anneeAcademiqueId);
			if (situationPublie != null) {
				searchDto.setSituationId(situationPublie.getId());
			}
			sessionExamenList = utilBean.loadExamenGroupedPlanning(searchDto);
		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [ExamenNoteSessionBean.anneeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:52:19
	 */
	public void anneeChanged() {
		showDetail = false;
		loadOffreFormationOuverte();
		ofChanged();

	}

	/**
	 * [ExamenNoteSessionBean.ofChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:52:30
	 */
	public void ofChanged() {
		showDetail = false;
		loadPeriodes();
		periodeChanged();

	}

	/**
	 * [ExamenNoteSessionBean.periodeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:52:54
	 */
	public void periodeChanged() {
		showDetail = false;
		if (validate) {
			loadPlanningSession();
		} else {
			loadExamenSession();
		}
		sessionChanged();
	}

	/**
	 * [NoteExamenBean.sessionExamenList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 10:39:53
	 * @return the sessionExamenList
	 */
	public List<SelectItem> getSessionExamenList() {
		return sessionExamenList;
	}

	/**
	 * [NoteExamenBean.sessionExamenList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 10:39:53
	 * @param sessionExamenList
	 *            the sessionExamenList to set
	 */
	public void setSessionExamenList(List<SelectItem> sessionExamenList) {
		this.sessionExamenList = sessionExamenList;
	}

	/**
	 * [NoteExamenBean.sessionChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 10:41:13
	 */
	public void sessionChanged() {
		showDetail = false;
		if (planningSessionId != null) {
			planningSessionDto = planningSessionService
					.findById(planningSessionId);
		}
		if (validate) {
			if (planningSessionId != null) {
				loadRattachementMc();
				loadRepartitionUe(oofId, periodeId);
				findNoteToValidate();
			}
		} else {
			findInscriptionSession();
		}

	}

	/**
	 * [NoteExamenBean.findInscriptionSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 10:41:53
	 */
	public void findInscriptionSession() {
		try {
			showDetail = false;
			if (examenSessionId != null) {
				examenSessionDto = examenSessionService
						.findById(examenSessionId);

			}

			if (examenSessionId != null) {
				noteSessionList = inscriptionExamenService
						.findByExamen(examenSessionId);
				if (noteSessionList != null && !noteSessionList.isEmpty()) {
					showDetail = true;
				} else {
					Utility.showWarningMessage(notebundle
							.getString("note_examen_data_table_seacrh_result_no_result"));
					showDetail = false;
					return;
				}

				for (InscriptionExamenDto _ie : noteSessionList) {
					if (_ie.getNin() != null) {
						RefIndividuDto refIndividu = refIndividuService
								.findByIdentifiant(_ie.getNin());
						if (refIndividu != null) {
							_ie.setIndividuNomArabe(refIndividu.getNomArabe());
							_ie.setIndividuNomLatin(refIndividu.getNomLatin()
									.toUpperCase());
							_ie.setIndividuPrenomLatin(refIndividu
									.getPrenomLatin().substring(0, 1)
									.toUpperCase()
									+ refIndividu.getPrenomLatin().substring(1)
											.toLowerCase());
							_ie.setIndividuPrenomArabe(refIndividu
									.getPrenomArabe());
							_ie.setIndividuDateNaissance(refIndividu
									.getDateNaissance());
						}
					}

					// RequestContext.getCurrentInstance().execute("saveWidget.focus();");
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [ExamenNoteSessionBean.findAllSessionInscription] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 14:33:32
	 */
	public void findAllSessionInscription() {
		try {
			if (planningSessionId != 0) {

			}

		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [NoteExamenBean.noteSessionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:03:05
	 * @return the noteSessionList
	 */
	public List<InscriptionExamenDto> getNoteSessionList() {
		return noteSessionList;
	}

	/**
	 * [NoteExamenBean.noteSessionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:03:05
	 * @param noteSessionList
	 *            the noteSessionList to set
	 */
	public void setNoteSessionList(List<InscriptionExamenDto> noteSessionList) {
		this.noteSessionList = noteSessionList;
	}

	/**
	 * [NoteExamenBean.examenSessionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 10:47:12
	 * @return the examenSessionService
	 */
	public ExamenSessionService getExamenSessionService() {
		return examenSessionService;
	}

	/**
	 * [NoteExamenBean.examenSessionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 10:47:12
	 * @param examenSessionService
	 *            the examenSessionService to set
	 */
	public void setExamenSessionService(
			ExamenSessionService examenSessionService) {
		this.examenSessionService = examenSessionService;
	}

	/**
	 * [NoteExamenBean.planingExamenList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 11:00:03
	 * @return the planingExamenList
	 */
	public List<SelectItem> getPlaningExamenList() {
		return planingExamenList;
	}

	/**
	 * [NoteExamenBean.planingExamenList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 11:00:03
	 * @param planingExamenList
	 *            the planingExamenList to set
	 */
	public void setPlaningExamenList(List<SelectItem> planingExamenList) {
		this.planingExamenList = planingExamenList;
	}

	/**
	 * [NoteExamenBean.planningSessionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 11:00:40
	 * @return the planningSessionService
	 */
	public PlanningSessionService getPlanningSessionService() {
		return planningSessionService;
	}

	/**
	 * [NoteExamenBean.planningSessionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 11:00:40
	 * @param planningSessionService
	 *            the planningSessionService to set
	 */
	public void setPlanningSessionService(
			PlanningSessionService planningSessionService) {
		this.planningSessionService = planningSessionService;
	}

	/**
	 * [NoteExamenBean.inscriptionExamenService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 11:49:07
	 * @return the inscriptionExamenService
	 */
	public InscriptionExamenService getInscriptionExamenService() {
		return inscriptionExamenService;
	}

	/**
	 * [NoteExamenBean.inscriptionExamenService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 11:49:07
	 * @param inscriptionExamenService
	 *            the inscriptionExamenService to set
	 */
	public void setInscriptionExamenService(
			InscriptionExamenService inscriptionExamenService) {
		this.inscriptionExamenService = inscriptionExamenService;
	}

	/**
	 * [NoteExamenBean.saveNote] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 12:55:29
	 */
	public void saveNote() {
		try {
			if (noteSessionList != null && !noteSessionList.isEmpty()) {
				double moyenneSession = 0.0;

				for (InscriptionExamenDto inscriptionExamenDto : noteSessionList) {
					if (inscriptionExamenDto.getNoteExamen() != null
							&& inscriptionExamenDto.getAbsent()) {
						Utility.showErrorMessage(notebundle
								.getString("note_examen_erreur_etudiant_basent")
								+ " ["
								+ inscriptionExamenDto.getIndividuNomLatin()
								+ " "
								+ inscriptionExamenDto.getIndividuPrenomLatin()
								+ "]");
						return;

					}

					if (inscriptionExamenDto.getNoteExamen() != null
							&& inscriptionExamenDto.getCopieNonRemise()) {
						Utility.showErrorMessage(notebundle
								.getString("note_examen_erreur_copie_non_remis")
								+ " ["
								+ inscriptionExamenDto.getIndividuNomLatin()
								+ " "
								+ inscriptionExamenDto.getIndividuPrenomLatin()
								+ "]");
						return;
					}
					if (inscriptionExamenDto.getNoteExamen() == null
							&& !inscriptionExamenDto.getCopieNonRemise()
							&& !inscriptionExamenDto.getAbsent()) {
						Utility.showErrorMessage(notebundle
								.getString("note_examen_erreur_note_session_vide")
								+ " ["
								+ inscriptionExamenDto.getIndividuNomLatin()
								+ " "
								+ inscriptionExamenDto.getIndividuPrenomLatin()
								+ "]");
						return;
					}
					if (inscriptionExamenDto.getAbsent()
							|| inscriptionExamenDto.getCopieNonRemise()) {
						inscriptionExamenDto.setNoteExamen(0.0);
					}
					inscriptionExamenDto.setNoteJury(inscriptionExamenDto
							.getNoteExamen());
					inscriptionExamenService
							.insertOrUpdate(inscriptionExamenDto);
					moyenneSession = moyenneSession
							+ inscriptionExamenDto.getNoteJury();
				}
				moyenneSession = moyenneSession / noteSessionList.size();
				moyenneSession = Utility.double2position(moyenneSession);
				examenSessionDto.setMoyenneSession(moyenneSession);
				examenSessionService.insertOrUpdate(examenSessionDto);
				saveNoteEliminatoire();
				Utility.showSuccessUpdateMessage();
				return;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [ExamenNoteSessionBean.saveNoteEliminatoire] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 11:02:05
	 */
	private void saveNoteEliminatoire() {
		if (examenSessionDto.getPlanningCoefficientNoteEliminatoire() != 0) {
			if (oofId != null && periodeId != null
					&& examenSessionDto.getId() != 0) {
				noteEliminatoireDto = new NoteEliminatoireDto(oofId, periodeId,
						examenSessionDto.getId(), null);
				List<NoteEliminatoireDto> noteEliminatoireDtos = noteEliminatoireService
						.findAdvanced(noteEliminatoireDto);
				if (noteEliminatoireDtos != null
						&& !noteEliminatoireDtos.isEmpty()) {
					noteEliminatoireDto.setId(noteEliminatoireDtos.get(0)
							.getId());
				}
				noteEliminatoireDto
						.setNoteEliminatoire(Utility.double2position(examenSessionDto
								.getMoyenneSession()
								* examenSessionDto
										.getPlanningCoefficientNoteEliminatoire()));
				noteEliminatoireService.insertOrUpdate(noteEliminatoireDto);
			}
		}
	}

	/**
	 * [ExamenNoteSessionBean.validateNote] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 14:45:20
	 */
	public void validateNote() {
		try {
			if (planningSessionDto != null) {
				if (notes != null) {
					for (InscriptionExamenDto _note : notes) {
						inscriptionExamenService.insertOrUpdate(_note);
					}
				}
				planningSessionDto.setNotesValide(true);
				planningSessionService.insertOrUpdate(planningSessionDto);
				//examenSessionService.insertOrUpdate(examenSessionDto);
				Utility.showSuccessMessage(notebundle
						.getString("note_examen_note_session_valide_success"));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * [ExamenNoteSessionBean.invalidateNote] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 sept. 2014 12:22:37
	 */
	public void invalidateNote() {
		try {
			if (planningSessionDto != null) {
				planningSessionDto.setNotesValide(false);
				planningSessionService.insertOrUpdate(planningSessionDto);
				Utility.showSuccessMessage(notebundle
						.getString("note_examen_note_session_invalide_success"));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * [NoteExamenBean.inscriptionExamenDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 15:46:57
	 * @return the inscriptionExamenDto
	 */
	public InscriptionExamenDto getInscriptionExamenDto() {
		return inscriptionExamenDto;
	}

	/**
	 * [NoteExamenBean.inscriptionExamenDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 15:46:57
	 * @param inscriptionExamenDto
	 *            the inscriptionExamenDto to set
	 */
	public void setInscriptionExamenDto(
			InscriptionExamenDto inscriptionExamenDto) {
		this.inscriptionExamenDto = inscriptionExamenDto;
	}

	
	/**
	 * [ExamenNoteSessionBean.filtredNoteSession] Getter 
	 * @author MAKERRI Sofiane on : 29 janv. 2015  10:53:36
	 * @return the filtredNoteSession
	 */
	public List<InscriptionExamenDto> getFiltredNoteSession() {
		return filtredNoteSession;
	}

	/**
	 * [ExamenNoteSessionBean.filtredNoteSession] Setter 
	 * @author MAKERRI Sofiane on : 29 janv. 2015  10:53:36
	 * @param filtredNoteSession the filtredNoteSession to set
	 */
	public void setFiltredNoteSession(List<InscriptionExamenDto> filtredNoteSession) {
		this.filtredNoteSession = filtredNoteSession;
	}

	/**
	 * [NoteExamenBean.notebundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 16:05:02
	 * @return the notebundle
	 */
	public ResourceBundle getNotebundle() {
		return notebundle;
	}

	/**
	 * [NoteExamenBean.notebundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 16:05:02
	 * @param notebundle
	 *            the notebundle to set
	 */
	public void setNotebundle(ResourceBundle notebundle) {
		this.notebundle = notebundle;
	}

	/**
	 * [NoteExamenBean.examenSessionId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 16:53:57
	 * @return the examenSessionId
	 */
	public Long getExamenSessionId() {
		return examenSessionId;
	}

	/**
	 * [NoteExamenBean.examenSessionId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 16:53:57
	 * @param examenSessionId
	 *            the examenSessionId to set
	 */
	public void setExamenSessionId(Long examenSessionId) {
		this.examenSessionId = examenSessionId;
	}

	/**
	 * [NoteExamenBean.examenSessionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 16:56:10
	 * @return the examenSessionDto
	 */
	public ExamenSessionDto getExamenSessionDto() {
		return examenSessionDto;
	}

	/**
	 * [NoteExamenBean.examenSessionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 sept. 2014 16:56:10
	 * @param examenSessionDto
	 *            the examenSessionDto to set
	 */
	public void setExamenSessionDto(ExamenSessionDto examenSessionDto) {
		this.examenSessionDto = examenSessionDto;
	}

	/**
	 * [NoteExamenBean.fileToImport] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 sept. 2014 08:46:07
	 * @return the fileToImport
	 */
	public UploadedFile getFileToImport() {
		return fileToImport;
	}

	/**
	 * [NoteExamenBean.fileToImport] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 sept. 2014 08:46:07
	 * @param fileToImport
	 *            the fileToImport to set
	 */
	public void setFileToImport(UploadedFile fileToImport) {
		this.fileToImport = fileToImport;
	}

	/**
	 * [NoteExamenBean.handleFileUpload] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 sept. 2014 08:47:50
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {

		if (event.getFile().equals(null)) {
			Utility.showErrorMessage("Aucun fichier selectionne !");
		}
		try {
			fileToImport = event.getFile();
			fileName = event.getFile().getFileName();
			uploadFile();
		} catch (Exception e) {
			Utility.showErrorMessage("Erreur lors du chargement du fichier.");
		}

	}

	/**
	 * [NoteExamenBean.uploadFile] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 sept. 2014 08:50:18
	 */
	private void uploadFile() {

		InputStream inputStream = null;
		try {
			this.filePath = getUploadFolderPath() + fileName;
			inputStream = fileToImport.getInputstream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("url----------" + filePath);

		OutputStream out = null;
		try {
			out = new FileOutputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int read = 0;
		byte[] bytes = new byte[4096];

		try {
			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			inputStream.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * [NoteExamenBean.fileName] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 sept. 2014 08:48:32
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * [NoteExamenBean.fileName] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 sept. 2014 08:48:32
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * [NoteExamenBean.filePath] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 sept. 2014 08:51:01
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * [NoteExamenBean.filePath] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 sept. 2014 08:51:01
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * [NoteExamenBean.getUploadFolderPath] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 sept. 2014 08:52:03
	 * @return
	 */
	private String getUploadFolderPath() {
		return configHolder.getDocumentTempFolder() + "/";
	}

	/**
	 * [NoteExamenBean.importFile] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 sept. 2014 16:22:26
	 */
	public void importFile() {
		try {
			if (this.fileName != null && (!this.fileName.trim().equals(""))) {
				List<InscriptionExamenDto> _tmp = noteSessionList;
				this.filePath = getUploadFolderPath() + fileName;
				FileReaderExamenNotes fr = new FileReaderExamenNotes();
				fr.processOneSheet(filePath);
				importNoteEtudiantModelList = FileReaderExamenNotes.importNoteEtudiantModelList;
				if (importNoteEtudiantModelList != null && _tmp != null) {
					if (importNoteEtudiantModelList.size() != noteSessionList
							.size()) {
						Utility.showErrorMessage(notebundle
								.getString("note_examen_erreur_taille_non_identique"));
						return;
					}
					oldNoteSessionList = new ArrayList<InscriptionExamenDto>();
					for (ImportNoteEtudiantModel _import : importNoteEtudiantModelList) {
						noteImported = true;
						for (InscriptionExamenDto _actuel : _tmp) {
							if (_import.getNumeroMatricule() != null
									&& _import.getNumeroMatricule().equals(
											_actuel.getNumeroMatricule())) {
								oldNoteSessionList
										.add(new InscriptionExamenDto(_actuel));
								/****
								 * verifier si la note est superieur a la note
								 * de base
								 ****/
								if (_import.getNote() != null) {
									if (_import.getNote() != 0
											&& _actuel.getAbsent()) {
										/***
										 * on a donner une note a un etudiant
										 * absent
										 ****/
										Utility.showErrorMessage(notebundle
												.getString("note_examen_erreur_etudiant_basent")
												+ " ["
												+ _actuel.getIndividuNomLatin()
												+ " "
												+ _actuel
														.getIndividuPrenomLatin()
												+ "]");
										return;
									}
									if (_import.getNote() != 0
											&& _actuel.getCopieNonRemise()) {
										/***
										 * on a donner une note a un etudiant
										 * absent
										 ****/
										Utility.showErrorMessage(notebundle
												.getString("note_examen_erreur_copie_non_remis")
												+ " ["
												+ _actuel.getIndividuNomLatin()
												+ " "
												+ _actuel
														.getIndividuPrenomLatin()
												+ "]");
										return;
									}
									if (_import.getNote() > examenSessionDto
											.getRattachementMcNoteDeBase()) {
										Utility.showErrorMessage(notebundle
												.getString("note_examen_erreur_note_superieur_note_base"));
										return;
									}
									_actuel.setNoteExamen(_import.getNote());
								}

							}
						}
					}
					noteSessionList = _tmp;
					Utility.showSuccessMessage(notebundle
							.getString("note_examen_import_success"));
				}

			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * [ExamenNoteSessionBean.importNoteEtudiantModelList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:45:27
	 * @return the importNoteEtudiantModelList
	 */
	public List<ImportNoteEtudiantModel> getImportNoteEtudiantModelList() {
		return importNoteEtudiantModelList;
	}

	/**
	 * [ExamenNoteSessionBean.importNoteEtudiantModelList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:45:27
	 * @param importNoteEtudiantModelList
	 *            the importNoteEtudiantModelList to set
	 */
	public void setImportNoteEtudiantModelList(
			List<ImportNoteEtudiantModel> importNoteEtudiantModelList) {
		this.importNoteEtudiantModelList = importNoteEtudiantModelList;
	}

	/**
	 * [NoteExamenBean.exportFileName] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 sept. 2014 16:47:26
	 * @return the exportFileName
	 */
	public String getExportFileName() {
		exportFileName = " examen_note_session_"
				+ examenSessionDto.getPlanningSessionIntitule() + "_"
				+ examenSessionDto.getNumeroSession() + "_"
				+ examenSessionDto.getMcLibelleFr();
		return exportFileName;
	}

	/**
	 * [NoteExamenBean.exportFileName] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 sept. 2014 16:47:26
	 * @param exportFileName
	 *            the exportFileName to set
	 */
	public void setExportFileName(String exportFileName) {
		this.exportFileName = exportFileName;
	}

	/**
	 * [NoteExamenBean.noteMax] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 10:22:44
	 * @return the noteMax
	 */
	public Double getNoteMax() {
		noteMax = 20.0;
		if (examenSessionDto != null
				&& examenSessionDto.getRattachementMcNoteDeBase() != null) {
			noteMax = examenSessionDto.getRattachementMcNoteDeBase();
		}
		return noteMax;
	}

	/**
	 * [NoteExamenBean.noteMax] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 10:22:44
	 * @param noteMax
	 *            the noteMax to set
	 */
	public void setNoteMax(Double noteMax) {
		this.noteMax = noteMax;
	}

	/**
	 * [NoteExamenBean.showDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 18:00:31
	 * @return the showDetail
	 */
	public boolean isShowDetail() {
		return showDetail;
	}

	/**
	 * [NoteExamenBean.showDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 18:00:31
	 * @param showDetail
	 *            the showDetail to set
	 */
	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}

	/**
	 * [ExamenNoteSessionBean.planningSessionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:49:18
	 * @return the planningSessionDto
	 */
	public PlanningSessionDto getPlanningSessionDto() {
		return planningSessionDto;
	}

	/**
	 * [ExamenNoteSessionBean.planningSessionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:49:18
	 * @param planningSessionDto
	 *            the planningSessionDto to set
	 */
	public void setPlanningSessionDto(PlanningSessionDto planningSessionDto) {
		this.planningSessionDto = planningSessionDto;
	}

	/**
	 * [ExamenNoteSessionBean.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:49:59
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [ExamenNoteSessionBean.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:49:59
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [ExamenNoteSessionBean.anneeAcademiqueList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:50:53
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [ExamenNoteSessionBean.anneeAcademiqueList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:50:53
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [ExamenNoteSessionBean.ofList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:50:53
	 * @return the ofList
	 */
	public List<SelectItem> getOfList() {
		return ofList;
	}

	/**
	 * [ExamenNoteSessionBean.ofList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:50:53
	 * @param ofList
	 *            the ofList to set
	 */
	public void setOfList(List<SelectItem> ofList) {
		this.ofList = ofList;
	}

	/**
	 * [ExamenNoteSessionBean.periodeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:50:53
	 * @return the periodeList
	 */
	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	/**
	 * [ExamenNoteSessionBean.periodeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 13:50:53
	 * @param periodeList
	 *            the periodeList to set
	 */
	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	/**
	 * [ExamenNoteSessionBean.situationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 14:39:43
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [ExamenNoteSessionBean.situationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 14:39:43
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [ExamenNoteSessionBean.editMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 11:40:17
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [ExamenNoteSessionBean.editMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 11:40:17
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * [ExamenNoteSessionBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 11:41:15
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [ExamenNoteSessionBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 11:41:15
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [ExamenNoteSessionBean.noteImported] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 12:56:09
	 * @return the noteImported
	 */
	public boolean isNoteImported() {
		return noteImported;
	}

	/**
	 * [ExamenNoteSessionBean.noteImported] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 12:56:09
	 * @param noteImported
	 *            the noteImported to set
	 */
	public void setNoteImported(boolean noteImported) {
		this.noteImported = noteImported;
	}

	/**
	 * [ExamenNoteSessionBean.cancelImportation] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 12:57:33
	 */
	public void cancelImportation() {
		noteSessionList = oldNoteSessionList;
		noteImported = false;
	}

	/**
	 * [ExamenNoteSessionBean.oldNoteSessionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 12:59:28
	 * @return the oldNoteSessionList
	 */
	public List<InscriptionExamenDto> getOldNoteSessionList() {
		return oldNoteSessionList;
	}

	/**
	 * [ExamenNoteSessionBean.oldNoteSessionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 12:59:28
	 * @param oldNoteSessionList
	 *            the oldNoteSessionList to set
	 */
	public void setOldNoteSessionList(
			List<InscriptionExamenDto> oldNoteSessionList) {
		this.oldNoteSessionList = oldNoteSessionList;
	}

	/**
	 * [ExamenNoteSessionBean.etudiants] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 16:25:07
	 * @return the etudiants
	 */
	public List<InscriptionExamenDto> getEtudiants() {
		return etudiants;
	}

	/**
	 * [ExamenNoteSessionBean.etudiants] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 16:25:07
	 * @param etudiants
	 *            the etudiants to set
	 */
	public void setEtudiants(List<InscriptionExamenDto> etudiants) {
		this.etudiants = etudiants;
	}

	/**
	 * [ExamenNoteSessionBean.findNoteToValidate] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 17:34:31
	 */
	public void findNoteToValidate() {
		showDetail = false;
		loadStudentOfSession();
		loadNotes();
		if (etudiants != null && !etudiants.isEmpty()) {
			showDetail = true;
			bilanSessionDtos = new ArrayList<BilanSessionDto>();
			for (InscriptionExamenDto _etudiant : etudiants) {
				if (notes != null) {
					// NoteEtudiantModel noteEtudiant = new NoteEtudiantModel();
					BilanSessionDto noteEtudiant = new BilanSessionDto();
					List<BilanUeDto> noteList = new ArrayList<BilanUeDto>();
					noteEtudiant.setBilanUes(noteList);
					DossierInscriptionAdministrativeDto dia = new DossierInscriptionAdministrativeDto();
					dia.setId(_etudiant.getDossierInscriptionAdministrativeId());

					for (InscriptionExamenDto _note : notes) {
						if (_etudiant.getDossierInscriptionAdministrativeId() != null
								&& _note.getDossierInscriptionAdministrativeId() != null
								&& _etudiant
										.getDossierInscriptionAdministrativeId()
										.equals(_note
												.getDossierInscriptionAdministrativeId())) {

							// dia.setIndividuDateNaissance(_note
							// .getIndividuDateNaissance());
							// dia.setIndividuNomLatin(_note.getIndividuNomLatin());
							// dia.setIndividuPrenomLatin(_note
							// .getIndividuPrenomLatin());
							// dia.setNumeroMatricule(_note.getNumeroMatricule());
							// dia.setPhoto(_note.getUrlPhoto());
							noteEtudiant.setUrlPhoto(_note.getUrlPhoto());
							noteEtudiant.setNomLatinEtudiant(_note
									.getIndividuNomLatin());
							noteEtudiant.setPrenomLatinEtudiant(_note
									.getIndividuPrenomLatin());
							if (_note.getNoteExamen() == null) {
								_note.setNoteExamen(0.0);
							}
							// double noteCC = 0.0;
							// fillCoefficientExamen(_note);
							// if (planningSessionDto.getAvecControleContinu())
							// {
							// BilanControleContinuDto bilanControleContinuDto =
							// bilanControleContinuService
							// .findUniqueByDiaId(
							// anneeAcademiqueId,
							// oofId,
							// periodeId,
							// _note.getDossierInscriptionAdministrativeId(),
							// _note.getRattachementMcId());
							// if (bilanControleContinuDto != null
							// && bilanControleContinuDto.getNote() != null) {
							// noteCC = bilanControleContinuDto.getNote();
							// _note.setMoyenneControleContinu(noteCC);
							// _note.setNoteControleContinu(noteCC);
							// // fillCoefficientExamen(_note);
							// _note.setNoteExamen(((noteCC * _note
							// .getCoefficientControleContinu()) + (_note
							// .getNoteSession() * _note
							// .getCoefficientExamen())));
							// _note.setNoteExamen(Utility
							// .double2position(_note
							// .getNoteExamen()));
							// _note.setMoyenneGenerale(Utility
							// .double2position(_note
							// .getNoteExamen()));
							// } else {
							// _note.setNoteControleContinu(null);
							// _note.setNoteExamen(Utility
							// .double2position(_note
							// .getNoteSession()));
							// _note.setMoyenneGenerale(Utility
							// .double2position(_note
							// .getNoteSession()));
							// }
							//
							// } else {
							// _note.setNoteExamen(_note.getNoteSession());
							// _note.setMoyenneGenerale(_note.getNoteSession());
							//
							// }
							// if (_note.getRattachementMcCoefficient() != null)
							// {
							// somNote = somNote
							// + (_note.getNoteExamen() * _note
							// .getRattachementMcCoefficient());
							// somCoefficient = somCoefficient
							// + _note.getRattachementMcCoefficient();
							// }
							addBilanUe(noteEtudiant, _note);
						}

					}

					// noteEtudiant.setEtudiant(dia);
					// noteEtudiant.setNotes(noteList);

					// if (somCoefficient != 0) {
					// moyenne = somNote / somCoefficient;
					// }

					// noteEtudiant.setMoyenne(Utility.double2position(moyenne));
					calculMoyenneUeEtudiant(noteEtudiant);
					calculCreditEtudiant(noteEtudiant);
					bilanSessionDtos.add(noteEtudiant);
				}
			}
		}
		System.out.println(bilanSessionDtos);
	}

	/**
	 * [ExamenNoteSessionBean.addBilanUe] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 janv. 2015 08:36:34
	 * @param _noteEtudiant
	 * @param _note
	 */
	public void addBilanUe(BilanSessionDto _noteEtudiant,
			InscriptionExamenDto _note) {
		if (_noteEtudiant != null) {
			List<BilanUeDto> _bilaUes = _noteEtudiant.getBilanUes();
			if (_bilaUes != null) {
				BilanUeDto _bilanUeDto = null;
				for (BilanUeDto ue : _bilaUes) {
					if (ue.getUeCode() != null
							&& ue.getUeCode().equals(_note.getUeCode())) {
						_bilanUeDto = ue;
						break;
					}
				}
				if (_bilanUeDto == null) {
					_bilanUeDto = new BilanUeDto();
					_bilaUes.add(_bilanUeDto);
					_bilanUeDto.setUeCode(_note.getUeCode());

					fillReparationUe(_bilanUeDto, _note);
					_bilanUeDto.setUeLibelleFr(_note.getUeLibelleFr());
					_bilanUeDto.setUeNatureLlFr(_note.getUeNatureLlFr());
				}
				List<BilanMcDto> _bilanMcs = _bilanUeDto.getBilanMcs();
				if (_bilanMcs == null) {
					_bilanMcs = new ArrayList<BilanMcDto>();
					_bilanUeDto.setBilanMcs(_bilanMcs);
				}
				BilanMcDto _bilanMc = new BilanMcDto();
				_bilanMc.setRattachementMcId(_note.getRattachementMcId());
				_bilanMc.setCredit(_note.getRattachementMcCredit());
				_bilanMc.setCoefficient(_note.getRattachementMcCoefficient());
				_bilanMc.setMoyenneGenerale(_note.getMoyenneGenerale());
				_bilanMc.setNoteExamen(_note.getNoteExamen());
				_bilanMc.setMcNoteObtention(_note
						.getRattachementMcNoteObtension());
				if (_note.getMoyenneGenerale() == 0) {
					_bilanMc.setMoyenneGenerale(_note.getNoteExamen());
				}
				// _bilanMc.setMoyenneGenerale(_note.getMoyenneGenerale());
				_bilanMc.setMcLibelleFr(_note.getMcLibelleFr());
				_bilanMc.setMoyenneControleContinu(_note
						.getNoteControleContinu());
				_bilanMc.setNoteControleIntermediaire(_note
						.getNoteControleIntermediaire());
				_bilanMc.setCoefficientControleContinu(_note
						.getCoefficientControleContinu());
				_bilanMc.setCoefficientControleIntermediaire(_note
						.getCoefficientControleIntermediaire());
				_bilanMc.setCoefficientExamen(_note.getCoefficientExamen());
				//_bilanMc.setNoteEliminatoire(_note.getNoteEliminatoire());
				_bilanMc.setNoteEliminatoire(_note.getRattachementMcNoteEliminatoire());
				_bilanMcs.add(_bilanMc);
			}
		}
	}

	/**
	 * [ExamenNoteSessionBean.fillReparationUe] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 janv. 2015 10:50:23
	 * @param _bilanUeDto
	 * @param _note
	 */
	private void fillReparationUe(BilanUeDto _bilanUeDto,
			InscriptionExamenDto _note) {
		if (_note != null && _bilanUeDto != null && repartitionUeDtos != null) {
			for (RepartitionUeDto _rue : repartitionUeDtos) {
				if (_note.getUeId() != null
						&& _note.getUeId()
								.equals(_rue.getUniteEnseignementId())) {
					_bilanUeDto.setCoefficient(_rue.getCoefficient());
					_bilanUeDto.setCredit(_rue.getUniteEnseignementCredits());
					_bilanUeDto.setUeNoteObtention(_rue.getNoteObtension());
					_bilanUeDto.setRepartitionUeId(_rue.getId());
				}
			}
		}
	}

	/**
	 * [ExamenNoteSessionBean.calculMoyenneUeEtudiant] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 janv. 2015 10:20:05
	 * @param _noteEtudiant
	 */
	public void calculMoyenneUeEtudiant(BilanSessionDto _noteEtudiant) {

		if (_noteEtudiant == null) {
			return;
		}
		List<BilanUeDto> bilanUeDtos = _noteEtudiant.getBilanUes();
		if (bilanUeDtos != null) {
			Double somMoyenneUe = 0.0;
			Double somCoefficientUe = 0.0;
			for (BilanUeDto _ue : bilanUeDtos) {
				List<BilanMcDto> bilanMcDtos = _ue.getBilanMcs();
				Double somMcNote = 0.0;
				Double somCoefficientMc = 0.0;

				if (bilanMcDtos != null) {
					for (BilanMcDto _mc : bilanMcDtos) {
						somMcNote = somMcNote
								+ (_mc.getMoyenneGenerale() * _mc
										.getCoefficient());
						somCoefficientMc = somCoefficientMc
								+ _mc.getCoefficient();
						// if (_noteEtudiant.getMoyenne() >= 10
						// || _mc.getMoyenneGenerale() >= _mc
						// .getMcNoteObtention()) {
						// _mc.setCreditObtenu(_mc.getCredit());
						// _mc.setDette(false);
						// } else {
						// _mc.setDette(true);
						// _mc.setCreditObtenu(0.0);
						// }
					}
					if (somCoefficientMc != 0) {
						Double _moyenneUe = Utility
								.double2position((somMcNote / somCoefficientMc));
						_ue.setMoyenne(_moyenneUe);

						somMoyenneUe = somMoyenneUe
								+ (_moyenneUe * _ue.getCoefficient());
						somCoefficientUe = somCoefficientUe
								+ _ue.getCoefficient();
					}
					if (_ue.getMoyenne() >= _ue.getUeNoteObtention()) {
						_ue.setUeAcquis(true);
					}
				}
			}
			if (somCoefficientUe != 0) {
				Double mg = Utility.double2position(somMoyenneUe
						/ somCoefficientUe);
				_noteEtudiant.setMoyenne(mg);
			}
		}

	}

	/**
	 * [ExamenNoteSessionBean.calculCreditEtudiant] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2015 12:56:47
	 * @param _noteEtudiant
	 */
	public void calculCreditEtudiant(BilanSessionDto _noteEtudiant) {

		if (_noteEtudiant == null) {
			return;
		}
		double totalCredit = 0.0;
		double totalCreditAquis = 0.0;
		double totalCreditObtenu = 0.0;

		List<BilanUeDto> bilanUeDtos = _noteEtudiant.getBilanUes();
		if (bilanUeDtos != null) {
			for (BilanUeDto _ue : bilanUeDtos) {
				List<BilanMcDto> bilanMcDtos = _ue.getBilanMcs();
				totalCredit = totalCredit + _ue.getCredit();
				if (bilanMcDtos != null) {
					for (BilanMcDto _mc : bilanMcDtos) {
						if (_mc.getMoyenneGenerale() >= _mc
								.getMcNoteObtention()) {
							_mc.setCreditObtenu(_mc.getCredit());
							_mc.setDette(false);
						} else if (_ue.isUeAcquis()
								|| _noteEtudiant.getMoyenne() >= 10) {
							_mc.setCreditAcquis(_mc.getCredit());
							_mc.setDette(false);

						} else {
							_mc.setDette(true);
							_mc.setCreditObtenu(0.0);
						}

					}
					if (_noteEtudiant.getMoyenne() >= 10 || _ue.isUeAcquis()) {
						_ue.setCreditAcquis(_ue.getCredit());
						_ue.setUeAcquis(true);
					}
					if (_ue.getMoyenne() >= _ue.getUeNoteObtention()) {
						_ue.setCreditObtenu(_ue.getCredit());
						_ue.setUeAcquis(true);

					} else {
						_ue.setUeAcquis(false);
					}

				}
				totalCreditAquis = totalCreditAquis + _ue.getCreditObtenu();
				totalCreditObtenu = totalCreditObtenu + _ue.getCreditAcquis();
			}
		}
		_noteEtudiant.setCreditObtenu(totalCreditObtenu);
		_noteEtudiant.setCredit(totalCredit);
		if (_noteEtudiant.getMoyenne() >= 10) {
			_noteEtudiant.setCreditAcquis(totalCreditAquis);
		}
	}

	/**
	 * [ExamenNoteSessionBean.loadRattachementMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2015 10:06:51
	 */
	private void loadRattachementMc() {
		rattachementMcDtos = utilBean.loadRattachementMc(oofId, periodeId);
	}

	/**
	 * [ExamenNoteSessionBean.notes] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 17:40:13
	 * @return the notes
	 */
	public List<InscriptionExamenDto> getNotes() {
		return notes;
	}

	/**
	 * [ExamenNoteSessionBean.notes] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 17:40:13
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(List<InscriptionExamenDto> notes) {
		this.notes = notes;
	}

	/**
	 * [ExamenNoteSessionBean.validate] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 17:45:51
	 * @return the validate
	 */
	public boolean isValidate() {
		return validate;
	}

	/**
	 * [ExamenNoteSessionBean.validate] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 17:45:51
	 * @param validate
	 *            the validate to set
	 */
	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	/**
	 * [ExamenNoteSessionBean.noteEtudiantModel] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 18:05:12
	 * @return the noteEtudiantModel
	 */
	// public List<NoteEtudiantModel> getNoteEtudiantModel() {
	// return noteEtudiantModel;
	// }

	/**
	 * [ExamenNoteSessionBean.noteEtudiantModel] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 18:05:12
	 * @param noteEtudiantModel
	 *            the noteEtudiantModel to set
	 */
	// public void setNoteEtudiantModel(List<NoteEtudiantModel>
	// noteEtudiantModel) {
	// this.noteEtudiantModel = noteEtudiantModel;
	// }

	/**
	 * [ExamenNoteSessionBean.oofId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:01:54
	 * @return the oofId
	 */
	public Integer getOofId() {
		return oofId;
	}

	/**
	 * [ExamenNoteSessionBean.oofId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:01:54
	 * @param oofId
	 *            the oofId to set
	 */
	public void setOofId(Integer oofId) {
		this.oofId = oofId;
	}

	/**
	 * [ExamenNoteSessionBean.periodeId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:01:54
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [ExamenNoteSessionBean.periodeId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:01:54
	 * @param periodeId
	 *            the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [ExamenNoteSessionBean.planningSessionId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:01:54
	 * @return the planningSessionId
	 */
	public Long getPlanningSessionId() {
		return planningSessionId;
	}

	/**
	 * [ExamenNoteSessionBean.planningSessionId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:01:54
	 * @param planningSessionId
	 *            the planningSessionId to set
	 */
	public void setPlanningSessionId(Long planningSessionId) {
		this.planningSessionId = planningSessionId;
	}

	/**
	 * [ExamenNoteSessionBean.anneeAcademiqueId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:07:49
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [ExamenNoteSessionBean.anneeAcademiqueId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:07:49
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [ExamenNoteSessionBean.bilanControleContinuService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 12:26:14
	 * @return the bilanControleContinuService
	 */
	public BilanControleContinuService getBilanControleContinuService() {
		return bilanControleContinuService;
	}

	/**
	 * [ExamenNoteSessionBean.bilanControleContinuService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 12:26:14
	 * @param bilanControleContinuService
	 *            the bilanControleContinuService to set
	 */
	public void setBilanControleContinuService(
			BilanControleContinuService bilanControleContinuService) {
		this.bilanControleContinuService = bilanControleContinuService;
	}

	public void setConfigHolder(ConfigHolder configHolder) {
		this.configHolder = configHolder;
	}

	public ConfigHolder getConfigHolder() {
		return configHolder;
	}

	/**
	 * [ExamenNoteSessionBean.refIndividuService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:31:17
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [ExamenNoteSessionBean.refIndividuService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:31:17
	 * @param refIndividuService
	 *            the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [ExamenNoteSessionBean.rattachementMcDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2015 09:54:11
	 * @return the rattachementMcDtos
	 */
	public List<RattachementMcDto> getRattachementMcDtos() {
		return rattachementMcDtos;
	}

	/**
	 * [ExamenNoteSessionBean.rattachementMcDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2015 09:54:11
	 * @param rattachementMcDtos
	 *            the rattachementMcDtos to set
	 */
	public void setRattachementMcDtos(List<RattachementMcDto> rattachementMcDtos) {
		this.rattachementMcDtos = rattachementMcDtos;
	}

	/**
	 * [ExamenNoteSessionBean.bilanSessionDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 janv. 2015 11:16:49
	 * @return the bilanSessionDtos
	 */
	public List<BilanSessionDto> getBilanSessionDtos() {
		return bilanSessionDtos;
	}

	/**
	 * [ExamenNoteSessionBean.bilanSessionDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 janv. 2015 11:16:49
	 * @param bilanSessionDtos
	 *            the bilanSessionDtos to set
	 */
	public void setBilanSessionDtos(List<BilanSessionDto> bilanSessionDtos) {
		this.bilanSessionDtos = bilanSessionDtos;
	}

	/**
	 * [ExamenNoteSessionBean.repartitionUeDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 janv. 2015 10:30:30
	 * @return the repartitionUeDtos
	 */
	public List<RepartitionUeDto> getRepartitionUeDtos() {
		return repartitionUeDtos;
	}

	/**
	 * [ExamenNoteSessionBean.repartitionUeDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 janv. 2015 10:30:30
	 * @param repartitionUeDtos
	 *            the repartitionUeDtos to set
	 */
	public void setRepartitionUeDtos(List<RepartitionUeDto> repartitionUeDtos) {
		this.repartitionUeDtos = repartitionUeDtos;
	}

	/**
	 * [ExamenNoteSessionBean.noteEliminatoireService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 10:58:43
	 * @return the noteEliminatoireService
	 */
	public NoteEliminatoireService getNoteEliminatoireService() {
		return noteEliminatoireService;
	}

	/**
	 * [ExamenNoteSessionBean.noteEliminatoireService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 10:58:43
	 * @param noteEliminatoireService
	 *            the noteEliminatoireService to set
	 */
	public void setNoteEliminatoireService(
			NoteEliminatoireService noteEliminatoireService) {
		this.noteEliminatoireService = noteEliminatoireService;
	}

	/**
	 * [ExamenNoteSessionBean.noteEliminatoireDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 11:00:17
	 * @return the noteEliminatoireDto
	 */
	public NoteEliminatoireDto getNoteEliminatoireDto() {
		return noteEliminatoireDto;
	}

	/**
	 * [ExamenNoteSessionBean.noteEliminatoireDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 11:00:17
	 * @param noteEliminatoireDto
	 *            the noteEliminatoireDto to set
	 */
	public void setNoteEliminatoireDto(NoteEliminatoireDto noteEliminatoireDto) {
		this.noteEliminatoireDto = noteEliminatoireDto;
	}

}
