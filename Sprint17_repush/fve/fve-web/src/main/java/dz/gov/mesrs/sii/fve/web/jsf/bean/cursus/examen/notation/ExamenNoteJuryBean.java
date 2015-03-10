/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation.ExamenNoteJuryBean.java] 
 * @author MAKERRI Sofiane on : 25 sept. 2014  12:05:38
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanUeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.DeliberationSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.InscriptionExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.PlanningSessionDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.DeliberationSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.InscriptionExamenService;
import dz.gov.mesrs.sii.fve.business.service.examen.PlanningSessionService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;

/**
 * @author MAKERRI Sofiane on : 25 sept. 2014 12:05:38
 */
@ManagedBean(name = "examenNoteJuryBean")
@ViewScoped
public class ExamenNoteJuryBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:05:58
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ExamenNoteJuryBean.class);
	private boolean showDetail;
	private List<SelectItem> planningSessionList;
	private List<SelectItem> planingExamenList;
	private List<SelectItem> sessionExamenList;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> ofList;
	private List<SelectItem> periodeList;
	private List<SelectItem> niveauList;
	private List<SelectItem> etudiantList;
	@ManagedProperty("#{planningSessionService}")
	private PlanningSessionService planningSessionService;
	@ManagedProperty("#{deliberationSessionService}")
	private DeliberationSessionService deliberationSessionService;
	@ManagedProperty("#{inscriptionExamenService}")
	private InscriptionExamenService inscriptionExamenService;
	@ManagedProperty("#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;
	@ManagedProperty(value = "#{bilanSessionService}")
	private BilanSessionService bilanSessionService;
	private ResourceBundle notebundle;
	private Integer examenSessionId;
	private Integer inscriptionExamenId;
	private ExamenSessionDto examenSessionDto;
	private PlanningSessionDto planningSessionDto;
	private List<BilanSessionDto> bilanSessionDtos;
	private List<InscriptionExamenDto> filtredInscription;
	private String exportFileName;
	private Double noteMax;
	private Double noteMin;
	private Double moyenneMc;
	private boolean etudiantRachat;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	private boolean editMode;
	private Integer anneeAcademiqueId;
	private Integer oofId;
	private Integer periodeId;
	private Integer niveauId;
	private Long planningSessionId;
	private Integer diaId;
	private String moyenne;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	private SituationEntiteDto situationPublie;
	private SituationEntiteDto situationClose;
	private double mg;
	private double creditTotal;
	private DossierInscriptionAdministrativeDto diaDto;;

	/**
	 * [ExamenNoteJuryBean.ExamenNoteJuryBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:05:38
	 */
	public ExamenNoteJuryBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		notebundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.NOTE_EXAMEN_BUNDLE_MSG_NAME);

	}

	@PostConstruct
	public void init() {
		examenSessionDto = new ExamenSessionDto();
		planningSessionDto = new PlanningSessionDto();
		// planningSessionDto = new PlanningSessionDto();
		editMode = true;
		if (editMode) {
			anneeAcademiqueId = sessionBean.getIdAnneeAcademique();
			loadOffreFormationOuverte();
		} else {
			loadAnneeAcademique();
		}

		loadSituation();
	}

	/**
	 * [ExamenNoteJuryBean.loadSituation] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 déc. 2014 15:54:00
	 */
	public void loadSituation() {
		situationPublie = situationService
				.findBySituationEntiteByCodeSituation(
						UtilConstants.ENTITE_PLANNING_SESSION_CODE,
						UtilConstants.SITUTAION_PUBLIEE_CODE);

		if (situationClose == null || situationClose.getId() == 0) {
			situationClose = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DELIBERATION_SESSION_CODE,
							UtilConstants.SITUTAION_CLOTUREE_CODE);
		}
	}

	/**
	 * [ExamenNoteJuryBean.loadPlanningSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 16:27:54
	 */
	public void loadPlanningSession() {
		try {
			if (oofId == null || periodeId == null || anneeAcademiqueId == null) {
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
			planingExamenList = utilBean.loadPlanningSession(searchDto);
			if (planingExamenList != null && planingExamenList.size() == 1) {
				planningSessionId = (Long) planingExamenList.get(0)
						.getValue();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [ExamenNoteJuryBean.loadAnneeAcademique] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 10:17:52
	 */
	public void loadAnneeAcademique() {
		try {
			anneeAcademiqueList = utilBean.loadAnneeAcademique();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [ExamenNoteJuryBean.loadPlanningSession] Method Charger la liste des
	 * planning de session d'examen
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:25:14
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
			sessionExamenList = utilBean.loadExamenGroupedPlanning(searchDto);
		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [ExamenNoteJuryBean.loadOffreFormationOuverte] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 10:19:13
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
	 * [ExamenNoteJuryBean.loadPeriodes] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 10:22:10
	 */
	private void loadPeriodes() {
		try {
			periodeList = new ArrayList<SelectItem>();
			if (niveauId == null) {
				return;
			}
			periodeList = utilBean.loadPeriode(niveauId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [ExamenNoteJuryBean.loadNiveaux] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:08:45
	 */
	private void loadNiveaux() {
		try {
			niveauList = new ArrayList<SelectItem>();
			if (oofId == null) {
				return;
			}
			niveauList = utilBean.loadNiveaux(oofId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [ExamenNoteJuryBean.loadEtudiant] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 11:14:32
	 */
	public void loadEtudiant() {
		try {
			if (anneeAcademiqueId != null && oofId != null && niveauId != null) {
				etudiantList = new ArrayList<SelectItem>();
				DossierInscriptionAdministrativeDto serachDto = new DossierInscriptionAdministrativeDto();
				serachDto.setAnneeAcademiqueId(anneeAcademiqueId);
				serachDto.setOuvertureOffreFormationId(oofId);
				serachDto.setRefEtablissementId(sessionBean
						.getIdEtablissement());
				serachDto.setNiveauId(niveauId);
				// PeriodeDto _periodeDto = utilBean.findPeriode(periodeId);
				// if (_periodeDto != null) {
				// serachDto.setNiveauId(_periodeDto.getIdNiveau());
				// }

				List<DossierInscriptionAdministrativeDto> inscritList = dossierInscriptionAdministrativeService
						.findAdvanced(serachDto, false);
				if (inscritList != null) {

					for (DossierInscriptionAdministrativeDto etudiant : inscritList) {
						SelectItem item = new SelectItem(etudiant.getId(),
								etudiant.getIndividuNomLatin() + " "
										+ etudiant.getIndividuPrenomLatin());
						etudiantList.add(item);

					}
				}
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [ExamenNoteJuryBean.showDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:07:39
	 * @return the showDetail
	 */
	public boolean isShowDetail() {
		return showDetail;
	}

	/**
	 * [ExamenNoteJuryBean.showDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:07:39
	 * @param showDetail
	 *            the showDetail to set
	 */
	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}

	/**
	 * [ExamenNoteJuryBean.anneeAcademiqueChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 10:10:48
	 */
	public void anneeChanged() {
		showDetail = false;
		diaId = null;
		loadOffreFormationOuverte();
		ofChanged();
	}

	/**
	 * [ExamenNoteJuryBean.ofChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 10:11:01
	 */
	public void ofChanged() {
		showDetail = false;
		diaId = null;
		loadNiveaux();
		niveauChanged();

	}

	/**
	 * [ExamenNoteJuryBean.niveauChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 10:11:27
	 */
	public void niveauChanged() {
		diaId = null;
		loadPeriodes();
		periodeChanged();
		loadEtudiant();

	}

	/**
	 * [ExamenNoteJuryBean.periodeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 10:11:24
	 */
	public void periodeChanged() {
		showDetail = false;
		diaId = null;
		// loadExamenSession();
		loadPlanningSession();
		planningChanged();
	}

	/**
	 * [ExamenNoteJuryBean.planningChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 16:32:37
	 */
	public void planningChanged() {
		showDetail = false;
		if (planningSessionId != null) {
			planningSessionDto = planningSessionService
					.findById(planningSessionId);
			if (planningSessionDto != null
					&& !planningSessionDto.getNotesValide()) {
				Utility.showWarningMessage(notebundle
						.getString("note_examen_erreur_non_valide"));
				return;
			}
			DeliberationSessionDto deliberationSessionDto = deliberationSessionService
					.findByPlanningId(planningSessionId);
			if (deliberationSessionDto != null
					&& situationClose != null
					&& deliberationSessionDto.getSituationId() != null
					&& deliberationSessionDto.getSituationId().equals(
							situationClose.getId())) {
				Utility.showErrorMessage(notebundle
						.getString("note_examen_erreur_seance_deliberation_cloture"));
				return;
			}
		}
		loadEtudiant();
	}

	/**
	 * [ExamenNoteJuryBean.sessionExamenList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:09:07
	 * @return the sessionExamenList
	 */
	public List<SelectItem> getSessionExamenList() {
		return sessionExamenList;
	}

	/**
	 * [ExamenNoteJuryBean.sessionExamenList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:09:07
	 * @param sessionExamenList
	 *            the sessionExamenList to set
	 */
	public void setSessionExamenList(List<SelectItem> sessionExamenList) {
		this.sessionExamenList = sessionExamenList;
	}

	/**
	 * [ExamenNoteJuryBean.planingExamenList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:09:07
	 * @return the planingExamenList
	 */
	public List<SelectItem> getPlaningExamenList() {
		return planingExamenList;
	}

	/**
	 * [ExamenNoteJuryBean.planingExamenList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:09:07
	 * @param planingExamenList
	 *            the planingExamenList to set
	 */
	public void setPlaningExamenList(List<SelectItem> planingExamenList) {
		this.planingExamenList = planingExamenList;
	}

	/**
	 * [ExamenNoteJuryBean.planningSessionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:09:43
	 * @return the planningSessionService
	 */
	public PlanningSessionService getPlanningSessionService() {
		return planningSessionService;
	}

	/**
	 * [ExamenNoteJuryBean.planningSessionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:09:43
	 * @param planningSessionService
	 *            the planningSessionService to set
	 */
	public void setPlanningSessionService(
			PlanningSessionService planningSessionService) {
		this.planningSessionService = planningSessionService;
	}

	/**
	 * [ExamenNoteJuryBean.inscriptionExamenService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:09:43
	 * @return the inscriptionExamenService
	 */
	public InscriptionExamenService getInscriptionExamenService() {
		return inscriptionExamenService;
	}

	/**
	 * [ExamenNoteJuryBean.inscriptionExamenService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:09:43
	 * @param inscriptionExamenService
	 *            the inscriptionExamenService to set
	 */
	public void setInscriptionExamenService(
			InscriptionExamenService inscriptionExamenService) {
		this.inscriptionExamenService = inscriptionExamenService;
	}

	/**
	 * [ExamenNoteJuryBean.notebundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:09:43
	 * @return the notebundle
	 */
	public ResourceBundle getNotebundle() {
		return notebundle;
	}

	/**
	 * [ExamenNoteJuryBean.notebundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:09:43
	 * @param notebundle
	 *            the notebundle to set
	 */
	public void setNotebundle(ResourceBundle notebundle) {
		this.notebundle = notebundle;
	}

	/**
	 * [ExamenNoteJuryBean.examenSessionId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:10:19
	 * @return the examenSessionId
	 */
	public Integer getExamenSessionId() {
		return examenSessionId;
	}

	/**
	 * [ExamenNoteJuryBean.examenSessionId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:10:19
	 * @param examenSessionId
	 *            the examenSessionId to set
	 */
	public void setExamenSessionId(Integer examenSessionId) {
		this.examenSessionId = examenSessionId;
	}

	/**
	 * [ExamenNoteJuryBean.sessionChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:10:40
	 */
	public void sessionChanged() {
		loadEtudiant();
	}

	/**
	 * [ExamenNoteJuryBean.findBilanSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 déc. 2014 12:21:13
	 */
	public void findBilanSession() {
		try {
			showDetail = false;
			bilanSessionDtos = new ArrayList<BilanSessionDto>();
			if (diaId != null) {
				BilanSessionDto searchDto = new BilanSessionDto();
				searchDto.setAnneeAcademiqueId(anneeAcademiqueId);
				searchDto.setPeriodeId(periodeId);
				searchDto.setOofId(oofId);
				searchDto.setDossierInscriptionAdministrativeId(diaId);
				searchDto.setPlanningSessionId(planningSessionId);
				searchDto.setType(UtilConstants.BILAN_TYPE_SESSION);
				bilanSessionDtos = bilanSessionService.findAdvanced(searchDto);
				if (bilanSessionDtos == null || bilanSessionDtos.isEmpty()) {
					Utility.showWarningMessage(notebundle
							.getString("note_examen_data_table_seacrh_result_no_result"));
					return;
				}
				calculMoyenne();
				showDetail = true;
			}

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [ExamenNoteJuryBean.calculMoyenne] Method
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 11:40:40
	 */
	public void calculMoyenne() {
		/* calcul de la moyenne générale *** */
		mg = 0.0;
		creditTotal = 0.0;
		if (bilanSessionDtos != null && !bilanSessionDtos.isEmpty()) {
			double somMoyenneSession = 0.0;
			for (BilanSessionDto bilan : bilanSessionDtos) {
				double MoyenneSession = 0.0;
				BilanSessionDto _bilan = bilan;
				if(bilan.getBilanSessionDtoMax() != null) {
					_bilan = bilan.getBilanSessionDtoMax();
				}
				List<BilanUeDto> bilanUes = _bilan.getBilanUes();
				if (bilanUes != null) {
					double somNoteUe = 0.0;
					double somCoefficientUe = 0.0;
					for (BilanUeDto ue : bilanUes) {
						double somNoteMc = 0.0;
						double somCoefficientMc = 0.0;
						double moyenneUe = 0.0;
						List<BilanMcDto> bilanMcs = ue.getBilanMcs();
						if (bilanMcs != null) {

							for (BilanMcDto mc : bilanMcs) {
								double mgMc = 0.0;
								if (mc.getNoteJuryToDisplay() != null && mc.getNoteJuryToDisplay() != 0) {
									mc.setNoteJury(mc.getNoteJuryToDisplay());
								} else {
									mc.setNoteJury(mc.getNoteExamen());
								}
								if (mc.getMoyenneControleContinu() != null) {
									mgMc = (mc.getMoyenneControleContinu() * mc
											.getCoefficientControleContinu())
											+ (mc.getNoteJury() * mc
													.getCoefficientExamen());
								} else {
									mgMc = mc.getNoteJury();
								}
								mc.setMoyenneGenerale(mgMc);
								if (mgMc >= mc.getMcNoteObtention()) {
									mc.setCreditObtenu(mc.getCredit());
									mc.setMcAcquis(true);
								} else {
									mc.setDette(true);
								}
								somNoteMc = somNoteMc
										+ (mgMc * mc.getCoefficient());
								somCoefficientMc = somCoefficientMc
										+ mc.getCoefficient();
							}
							if (somCoefficientMc != 0) {
								moyenneUe = somNoteMc / somCoefficientMc;
								ue.setMoyenne(moyenneUe);
							}
							if (moyenneUe >= ue.getUeNoteObtention()) {
								ue.setCreditObtenu(ue.getCredit());
								ue.setUeAcquis(true);
							} else {
								ue.setCreditObtenu(0);
							}
						}
						somNoteUe = somNoteUe
								+ (moyenneUe * ue.getCoefficient());
						somCoefficientUe = somCoefficientUe
								+ ue.getCoefficient();
					}

					if (somCoefficientUe != 0) {
						MoyenneSession = somNoteUe / somCoefficientUe;
					}
					somMoyenneSession = somMoyenneSession + MoyenneSession;
					_bilan.setMoyenne(MoyenneSession);
					if (somMoyenneSession >= 10) {
						_bilan.setCreditObtenu(_bilan.getCredit());
					}
				}
			}
			mg = somMoyenneSession / bilanSessionDtos.size();

		}
		/****** mise à jour de bilan selon la moyenne générale *******/

		if (bilanSessionDtos != null /* && bilanSessionDtos.size() > 1 */) {
			for (BilanSessionDto _bilan : bilanSessionDtos) {
				List<BilanUeDto> bilanUes = _bilan.getBilanUes();
				if (bilanUes != null) {
					for (BilanUeDto ue : bilanUes) {
						List<BilanMcDto> bilanMcs = ue.getBilanMcs();
						if (bilanMcs != null) {

							for (BilanMcDto mc : bilanMcs) {
								if (mg >= mc.getMcNoteObtention()) {
									mc.setCreditAcquis(mc.getCredit());
									mc.setDette(false);
									mc.setMcAcquis(true);
								}

							}
						}
						if (mg >= ue.getUeNoteObtention()) {
							ue.setCreditAcquis(ue.getCredit());
							ue.setUeAcquis(true);
						}

						/***** TO DO : mise à jour de crédit total et les dettes ***/
						// _bilan.setMoyenne(MoyenneSession);

					}
				}
				if (mg >= 10) {
					_bilan.setCreditAcquis(_bilan.getCredit());
					creditTotal = creditTotal + _bilan.getCredit();
				}
			}
		}

	}

	/**
	 * [ExamenNoteJuryBean.etudiantChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 17:04:29
	 */
	public void etudiantChanged() {
		if (diaId != null) {
			diaDto = dossierInscriptionAdministrativeService.findById(diaId);
		}

		if (diaDto == null) {
			diaDto = new DossierInscriptionAdministrativeDto();
		}
		findBilanSession();
	}

	/**
	 * [ExamenNoteJuryBean.examenSessionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:13:40
	 * @return the examenSessionDto
	 */
	public ExamenSessionDto getExamenSessionDto() {
		return examenSessionDto;
	}

	/**
	 * [ExamenNoteJuryBean.examenSessionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:13:40
	 * @param examenSessionDto
	 *            the examenSessionDto to set
	 */
	public void setExamenSessionDto(ExamenSessionDto examenSessionDto) {
		this.examenSessionDto = examenSessionDto;
	}

	/**
	 * [ExamenNoteJuryBean.filtredInscription] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:15:15
	 * @return the filtredInscription
	 */
	public List<InscriptionExamenDto> getFiltredInscription() {
		return filtredInscription;
	}

	/**
	 * [ExamenNoteJuryBean.filtredInscription] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:15:15
	 * @param filtredInscription
	 *            the filtredInscription to set
	 */
	public void setFiltredInscription(
			List<InscriptionExamenDto> filtredInscription) {
		this.filtredInscription = filtredInscription;
	}

	/**
	 * [ExamenNoteJuryBean.exportFileName] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:15:49
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
	 * [ExamenNoteJuryBean.exportFileName] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:15:49
	 * @param exportFileName
	 *            the exportFileName to set
	 */
	public void setExportFileName(String exportFileName) {
		this.exportFileName = exportFileName;
	}

	/**
	 * [ExamenNoteJuryBean.noteMax] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:17:04
	 * @return the noteMax
	 */
	public Double getNoteMax() {
		noteMax = 10.0;
		return noteMax;
	}

	/**
	 * [ExamenNoteJuryBean.noteMax] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:17:04
	 * @param noteMax
	 *            the noteMax to set
	 */
	public void setNoteMax(Double noteMax) {
		this.noteMax = noteMax;
	}

	/**
	 * [ExamenNoteJuryBean.noteMin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:17:20
	 * @return the noteMin
	 */
	public Double getNoteMin() {
		noteMin = 5.0;
		return noteMin;
	}

	/**
	 * [ExamenNoteJuryBean.noteMin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:17:20
	 * @param noteMin
	 *            the noteMin to set
	 */
	public void setNoteMin(Double noteMin) {
		this.noteMin = noteMin;
	}

	/**
	 * [ExamenNoteJuryBean.saveNote] Method enregister les note de jury et
	 * traiter les r�gle de gestion
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:18:01
	 */
	// public void saveNote() {
	// try {
	// if (noteJuryList != null) {
	// for (InscriptionExamenDto inscriptionExamenDto : noteJuryList) {
	// if (inscriptionExamenDto.getNoteJuryToDisplay() != null) {
	// inscriptionExamenDto.setNoteJury(inscriptionExamenDto.getNoteJuryToDisplay());
	// }
	// if (inscriptionExamenDto.getNoteJury() != null &&
	// inscriptionExamenDto.getAbsent()) {
	// Utility.showErrorMessage(notebundle.getString("note_examen_erreur_etudiant_basent"));
	// return;
	//
	// }
	//
	// if (inscriptionExamenDto.getNoteJury() != null &&
	// inscriptionExamenDto.getCopieNonRemise()) {
	// Utility.showErrorMessage(notebundle.getString("note_examen_erreur_copie_non_remis"));
	// return;
	// }
	// if (inscriptionExamenDto.getNoteJury() == null &&
	// !inscriptionExamenDto.getCopieNonRemise()
	// && !inscriptionExamenDto.getAbsent()) {
	// Utility.showErrorMessage(notebundle.getString("note_examen_erreur_note_jury_vide"));
	// return;
	// }
	// /****
	// * la note de jury doit etre inferieur ou egale � la note de
	// * session
	// *******/
	// if (!inscriptionExamenDto.getAbsent() &&
	// !inscriptionExamenDto.getCopieNonRemise()) {
	// if (inscriptionExamenDto.getNoteJury() <
	// inscriptionExamenDto.getNoteSession()) {
	// Utility.showErrorMessage(notebundle
	// .getString("note_examen_erreur_note_jury_inferieur_note_session"));
	// return;
	// }
	//
	// /****
	// * la note de jury ne doit pas d�passer la note
	// * eliminatoire si la note de session est moins de la
	// * note eliminatoire
	// *******/
	//
	// // if (inscriptionExamenDto
	// // .getRattachementMcNoteEliminatoire() != null
	// // && inscriptionExamenDto.getNoteSession() <
	// // inscriptionExamenDto
	// // .getRattachementMcNoteEliminatoire()
	// // && inscriptionExamenDto.getNoteSession() >
	// // inscriptionExamenDto
	// // .getRattachementMcNoteEliminatoire()) {
	// // Utility.showErrorMessage(notebundle
	// //
	// .getString("note_examen_erreur_note_jury_superieur_note_eliminatoire"));
	// // return;
	// // }
	//
	// /****
	// * la note de jury ne doit pas d�passer la note de base
	// * si la note de session est moins de la note de base
	// *******/
	//
	// // if (inscriptionExamenDto.getNoteSession() <
	// // inscriptionExamenDto
	// // .getRattachementMcNoteDeBase()
	// // && inscriptionExamenDto.getNoteSession() >
	// // inscriptionExamenDto
	// // .getRattachementMcNoteDeBase()) {
	// // Utility.showErrorMessage(notebundle
	// // .getString("note_examen_erreur_note_jury_superieur_note_base"));
	// // return;
	// // }
	// }
	//
	// if (inscriptionExamenDto.getAbsent() ||
	// inscriptionExamenDto.getCopieNonRemise()) {
	// inscriptionExamenDto.setNoteSession(null);
	// inscriptionExamenDto.setNoteJury(null);
	// }
	// inscriptionExamenService.insertOrUpdate(inscriptionExamenDto);
	//
	// }
	// Utility.showSuccessUpdateMessage();
	// return;
	// }
	// } catch (Exception e) {
	// log.error(e.getMessage());
	// Utility.showErrorMessage(e.getMessage());
	// }
	// }

	public void saveNote() {
		try {
			if (bilanSessionDtos != null) {
				for (BilanSessionDto bilanSessionDto : bilanSessionDtos) {
					List<BilanUeDto> bilanUes = bilanSessionDto.getBilanUes();
					if (bilanUes != null) {
						for (BilanUeDto ue : bilanUes) {
							List<BilanMcDto> bilanMcs = ue.getBilanMcs();
							if (bilanMcs != null) {
								for (BilanMcDto mc : bilanMcs) {
									/****
									 * la note de jury doit etre inferieur ou
									 * egale � la note de session
									 *******/
									if (mc.getNoteJury() < mc.getNoteExamen()) {
										Utility.showErrorMessage(notebundle
												.getString("note_examen_erreur_note_jury_inferieur_note_session"));
										return;
									}
									if (mc.getNoteJuryToDisplay() != null) {
										InscriptionExamenDto searchDto = new InscriptionExamenDto();
										searchDto
												.setDossierInscriptionAdministrativeId(bilanSessionDto
														.getDossierInscriptionAdministrativeId());
										searchDto
												.setPlanningSessionId(planningSessionId);
										searchDto.setRattachementMcId(mc
												.getRattachementMcId());
										List<InscriptionExamenDto> _list = inscriptionExamenService
												.findAdvanced(searchDto);
										if (_list != null && !_list.isEmpty()) {
											InscriptionExamenDto inscriptionExamenDto = _list
													.get(0);
											inscriptionExamenDto.setNoteJury(mc
													.getNoteJuryToDisplay());
											inscriptionExamenService
													.insertOrUpdate(inscriptionExamenDto);
										}
									}
								}
							}
						}
					}
					bilanSessionService.insertOrUpdate(bilanSessionDto);
				}
				Utility.showSuccessUpdateMessage();
				return;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [ExamenNoteJuryBean.moyenneMc] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:26:26
	 * @return the moyenneMc
	 */
	public Double getMoyenneMc() {
		return moyenneMc;
	}

	/**
	 * [ExamenNoteJuryBean.moyenneMc] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 12:26:26
	 * @param moyenneMc
	 *            the moyenneMc to set
	 */
	public void setMoyenneMc(Double moyenneMc) {
		this.moyenneMc = moyenneMc;
	}

	/**
	 * [ExamenNoteJuryBean.etudiantRachat] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 15:59:21
	 * @return the etudiantRachat
	 */
	public boolean isEtudiantRachat() {
		return etudiantRachat;
	}

	/**
	 * [ExamenNoteJuryBean.etudiantRachat] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 15:59:21
	 * @param etudiantRachat
	 *            the etudiantRachat to set
	 */
	public void setEtudiantRachat(boolean etudiantRachat) {
		this.etudiantRachat = etudiantRachat;
	}

	/**
	 * [ExamenNoteJuryBean.readCheckChange] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 sept. 2014 16:37:23 cette fonction permet
	 *         de donner une note de rachat � un �tudiant : la note de jury
	 *         la note de rachat est l'ajustement soit de la note
	 *         �liminatoire, soit de la note d'obtention
	 * @param event
	 */
	public void rachatChange(AjaxBehaviorEvent event) {
		SelectBooleanCheckbox permit = (SelectBooleanCheckbox) event
				.getComponent();
		boolean checked = (Boolean) permit.getValue();
		InscriptionExamenDto _inscriptionExamenDto = (InscriptionExamenDto) permit
				.getAttributes().get("selectedRecord");

		if (checked) {
			_inscriptionExamenDto.setEtudiantRachete(true);
			if (_inscriptionExamenDto.getNoteExamen() < _inscriptionExamenDto
					.getRattachementMcNoteEliminatoire()) {
				_inscriptionExamenDto.setNoteJury(_inscriptionExamenDto
						.getRattachementMcNoteEliminatoire());
			} else if (_inscriptionExamenDto.getNoteExamen() < _inscriptionExamenDto
					.getRattachementMcNoteObtension()) {
				_inscriptionExamenDto.setNoteJury(_inscriptionExamenDto
						.getRattachementMcNoteObtension());
			}
		} else {
			_inscriptionExamenDto.setEtudiantRachete(false);
			_inscriptionExamenDto.setNoteJury(_inscriptionExamenDto
					.getNoteExamen());
		}
		// for (InscriptionExamenDto ie : noteJuryList) {
		// if (ie.getId() == _inscriptionExamenDto.getId()) {
		// ie.setEtudiantRachete(_inscriptionExamenDto.getEtudiantRachete());
		// ie.setNoteJury(_inscriptionExamenDto.getNoteJury());
		// }
		// }
	}

	/**
	 * [ExamenNoteJuryBean.etudiantList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 11:04:08
	 * @return the etudiantList
	 */
	public List<SelectItem> getEtudiantList() {
		return etudiantList;
	}

	/**
	 * [ExamenNoteJuryBean.etudiantList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 11:04:08
	 * @param etudiantList
	 *            the etudiantList to set
	 */
	public void setEtudiantList(List<SelectItem> etudiantList) {
		this.etudiantList = etudiantList;
	}

	/**
	 * [ExamenNoteJuryBean.anneeAcademiqueList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 10:09:47
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [ExamenNoteJuryBean.anneeAcademiqueList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 10:09:47
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [ExamenNoteJuryBean.ofList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 10:09:47
	 * @return the ofList
	 */
	public List<SelectItem> getOfList() {
		return ofList;
	}

	/**
	 * [ExamenNoteJuryBean.ofList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 10:09:47
	 * @param ofList
	 *            the ofList to set
	 */
	public void setOfList(List<SelectItem> ofList) {
		this.ofList = ofList;
	}

	/**
	 * [ExamenNoteJuryBean.periodeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 10:09:47
	 * @return the periodeList
	 */
	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	/**
	 * [ExamenNoteJuryBean.periodeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 10:09:47
	 * @param periodeList
	 *            the periodeList to set
	 */
	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	/**
	 * [ExamenNoteJuryBean.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 10:20:18
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [ExamenNoteJuryBean.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 10:20:18
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [ExamenNoteJuryBean.inscriptionExamenId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 11:17:28
	 * @return the inscriptionExamenId
	 */
	public Integer getInscriptionExamenId() {
		return inscriptionExamenId;
	}

	/**
	 * [ExamenNoteJuryBean.inscriptionExamenId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 sept. 2014 11:17:28
	 * @param inscriptionExamenId
	 *            the inscriptionExamenId to set
	 */
	public void setInscriptionExamenId(Integer inscriptionExamenId) {
		this.inscriptionExamenId = inscriptionExamenId;
	}

	/**
	 * [ExamenNoteJuryBean.editMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 14:18:54
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [ExamenNoteJuryBean.editMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 14:18:54
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * [ExamenNoteJuryBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 14:20:49
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [ExamenNoteJuryBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 14:20:49
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [ExamenNoteJuryBean.anneeAcademiqueId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 15:42:04
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [ExamenNoteJuryBean.anneeAcademiqueId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 15:42:04
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [ExamenNoteJuryBean.oofId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 15:42:04
	 * @return the oofId
	 */
	public Integer getOofId() {
		return oofId;
	}

	/**
	 * [ExamenNoteJuryBean.oofId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 15:42:04
	 * @param oofId
	 *            the oofId to set
	 */
	public void setOofId(Integer oofId) {
		this.oofId = oofId;
	}

	/**
	 * [ExamenNoteJuryBean.periodeId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 15:42:04
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [ExamenNoteJuryBean.periodeId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 15:42:04
	 * @param periodeId
	 *            the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [ExamenNoteJuryBean.planningSessionId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 16:24:01
	 * @return the planningSessionId
	 */
	public Long getPlanningSessionId() {
		return planningSessionId;
	}

	/**
	 * [ExamenNoteJuryBean.planningSessionId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 16:24:01
	 * @param planningSessionId
	 *            the planningSessionId to set
	 */
	public void setPlanningSessionId(Long planningSessionId) {
		this.planningSessionId = planningSessionId;
	}

	/**
	 * [ExamenNoteJuryBean.planningSessionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 16:31:09
	 * @return the planningSessionList
	 */
	public List<SelectItem> getPlanningSessionList() {
		return planningSessionList;
	}

	/**
	 * [ExamenNoteJuryBean.planningSessionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 16:31:09
	 * @param planningSessionList
	 *            the planningSessionList to set
	 */
	public void setPlanningSessionList(List<SelectItem> planningSessionList) {
		this.planningSessionList = planningSessionList;
	}

	/**
	 * [ExamenNoteJuryBean.dossierInscriptionAdministrativeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 16:41:06
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [ExamenNoteJuryBean.dossierInscriptionAdministrativeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 16:41:06
	 * @param dossierInscriptionAdministrativeService
	 *            the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [ExamenNoteJuryBean.diaId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 16:48:21
	 * @return the diaId
	 */
	public Integer getDiaId() {
		return diaId;
	}

	/**
	 * [ExamenNoteJuryBean.diaId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 16:48:21
	 * @param diaId
	 *            the diaId to set
	 */
	public void setDiaId(Integer diaId) {
		this.diaId = diaId;
	}

	/**
	 * [ExamenNoteJuryBean.planningSessionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 09:48:23
	 * @return the planningSessionDto
	 */
	public PlanningSessionDto getPlanningSessionDto() {
		return planningSessionDto;
	}

	/**
	 * [ExamenNoteJuryBean.planningSessionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 09:48:23
	 * @param planningSessionDto
	 *            the planningSessionDto to set
	 */
	public void setPlanningSessionDto(PlanningSessionDto planningSessionDto) {
		this.planningSessionDto = planningSessionDto;
	}

	/**
	 * [ExamenNoteJuryBean.moyenne] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 10:40:22
	 * @return the moyenne
	 */
	public String getMoyenne() {
		return moyenne;
	}

	/**
	 * [ExamenNoteJuryBean.moyenne] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 10:40:22
	 * @param moyenne
	 *            the moyenne to set
	 */
	public void setMoyenne(String moyenne) {
		this.moyenne = moyenne;
	}

	/**
	 * [ExamenNoteJuryBean.situationPublie] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 nov. 2014 11:45:36
	 * @return the situationPublie
	 */
	public SituationEntiteDto getSituationPublie() {
		return situationPublie;
	}

	/**
	 * [ExamenNoteJuryBean.situationPublie] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 nov. 2014 11:45:36
	 * @param situationPublie
	 *            the situationPublie to set
	 */
	public void setSituationPublie(SituationEntiteDto situationPublie) {
		this.situationPublie = situationPublie;
	}

	/**
	 * [ExamenNoteJuryBean.situationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 nov. 2014 11:46:15
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [ExamenNoteJuryBean.situationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 nov. 2014 11:46:15
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [ExamenNoteJuryBean.bilanSessionDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 déc. 2014 11:55:09
	 * @return the bilanSessionDtos
	 */
	public List<BilanSessionDto> getBilanSessionDtos() {
		return bilanSessionDtos;
	}

	/**
	 * [ExamenNoteJuryBean.bilanSessionDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 déc. 2014 11:55:09
	 * @param bilanSessionDtos
	 *            the bilanSessionDtos to set
	 */
	public void setBilanSessionDtos(List<BilanSessionDto> bilanSessionDtos) {
		this.bilanSessionDtos = bilanSessionDtos;
	}

	/**
	 * [ExamenNoteJuryBean.bilanSessionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 déc. 2014 11:57:30
	 * @return the bilanSessionService
	 */
	public BilanSessionService getBilanSessionService() {
		return bilanSessionService;
	}

	/**
	 * [ExamenNoteJuryBean.bilanSessionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 déc. 2014 11:57:30
	 * @param bilanSessionService
	 *            the bilanSessionService to set
	 */
	public void setBilanSessionService(BilanSessionService bilanSessionService) {
		this.bilanSessionService = bilanSessionService;
	}

	/**
	 * [ExamenNoteJuryBean.mg] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 déc. 2014 13:59:06
	 * @return the mg
	 */
	public double getMg() {
		return mg;
	}

	/**
	 * [ExamenNoteJuryBean.mg] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 déc. 2014 13:59:06
	 * @param mg
	 *            the mg to set
	 */
	public void setMg(double mg) {
		this.mg = mg;
	}

	/**
	 * [ExamenNoteJuryBean.formatObjectNote] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 14:16:40
	 * @param note
	 * @return
	 */
	public String formatObjectNote(Double note, boolean withoutCC) {
		if (withoutCC) {
			return "-";
		}
		if (note == null) {
			return null;
		}
		return Utility.formatNoteWith2Position(note);
	}

	/**
	 * [ExamenNoteJuryBean.deliberationSessionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 déc. 2014 15:26:54
	 * @return the deliberationSessionService
	 */
	public DeliberationSessionService getDeliberationSessionService() {
		return deliberationSessionService;
	}

	/**
	 * [ExamenNoteJuryBean.deliberationSessionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 déc. 2014 15:26:54
	 * @param deliberationSessionService
	 *            the deliberationSessionService to set
	 */
	public void setDeliberationSessionService(
			DeliberationSessionService deliberationSessionService) {
		this.deliberationSessionService = deliberationSessionService;
	}

	/**
	 * [ExamenNoteJuryBean.situationClose] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 déc. 2014 15:34:10
	 * @return the situationClose
	 */
	public SituationEntiteDto getSituationClose() {
		return situationClose;
	}

	/**
	 * [ExamenNoteJuryBean.situationClose] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 déc. 2014 15:34:10
	 * @param situationClose
	 *            the situationClose to set
	 */
	public void setSituationClose(SituationEntiteDto situationClose) {
		this.situationClose = situationClose;
	}

	/**
	 * [ExamenNoteJuryBean.diaDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 déc. 2014 15:56:38
	 * @return the diaDto
	 */
	public DossierInscriptionAdministrativeDto getDiaDto() {
		return diaDto;
	}

	/**
	 * [ExamenNoteJuryBean.diaDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 déc. 2014 15:56:38
	 * @param diaDto
	 *            the diaDto to set
	 */
	public void setDiaDto(DossierInscriptionAdministrativeDto diaDto) {
		this.diaDto = diaDto;
	}

	/**
	 * [ExamenNoteJuryBean.niveauList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:10:17
	 * @return the niveauList
	 */
	public List<SelectItem> getNiveauList() {
		return niveauList;
	}

	/**
	 * [ExamenNoteJuryBean.niveauList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:10:17
	 * @param niveauList
	 *            the niveauList to set
	 */
	public void setNiveauList(List<SelectItem> niveauList) {
		this.niveauList = niveauList;
	}

	/**
	 * [ExamenNoteJuryBean.niveauId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:10:17
	 * @return the niveauId
	 */
	public Integer getNiveauId() {
		return niveauId;
	}

	/**
	 * [ExamenNoteJuryBean.niveauId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:10:17
	 * @param niveauId
	 *            the niveauId to set
	 */
	public void setNiveauId(Integer niveauId) {
		this.niveauId = niveauId;
	}

	/**
	 * [ExamenNoteJuryBean.creditTotal] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 13:31:03
	 * @return the creditTotal
	 */
	public double getCreditTotal() {
		return creditTotal;
	}

	/**
	 * [ExamenNoteJuryBean.creditTotal] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 13:31:03
	 * @param creditTotal
	 *            the creditTotal to set
	 */
	public void setCreditTotal(double creditTotal) {
		this.creditTotal = creditTotal;
	}

}
