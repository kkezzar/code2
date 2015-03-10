/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation.DeliberationBean.java] 
 * @author MAKERRI Sofiane on : 19 oct. 2014  17:23:38
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.business.util.CrossTab;
import dz.gov.mesrs.sii.commons.data.util.UtilDate;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DiplomeFinEtudeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.MentionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SignatureDiplomeFinEtudeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanControleContinuDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanUeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.CoefficientExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.DeliberationSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.InscriptionExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.NoteEliminatoireDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.PlanningSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RepartitionUeDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AttestationFinEtudeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DiplomeFinEtudeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.SignatureDiplomeFinEtudeService;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanControleContinuService;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanMcService;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanUeService;
import dz.gov.mesrs.sii.fve.business.service.examen.CoefficientExamenService;
import dz.gov.mesrs.sii.fve.business.service.examen.DeliberationSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.InscriptionExamenService;
import dz.gov.mesrs.sii.fve.business.service.examen.NoteEliminatoireService;
import dz.gov.mesrs.sii.fve.business.service.examen.PlanningSessionService;
import dz.gov.mesrs.sii.fve.business.service.impression.ImpressionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RepartitionUeService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SearchPlanningBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.impression.PrintMgrBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;

/**
 * @author MAKERRI Sofiane on : 19 oct. 2014 17:23:38
 */
/**
 * @author MAKERRI Sofiane  on : 20 oct. 2014  17:01:37
 */
/**
 * @author MAKERRI Sofiane on : 20 oct. 2014 17:01:38
 */
@ManagedBean(name = "deliberationBean")
@ViewScoped
public class DeliberationBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:23:55
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DeliberationBean.class);
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{planningSessionService}")
	private PlanningSessionService planningSessionService;
	@ManagedProperty("#{deliberationSessionService}")
	private DeliberationSessionService deliberationSessionService;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	@ManagedProperty(value = "#{repartitionUeService}")
	private RepartitionUeService repartitionUeService;
	@ManagedProperty(value = "#{rattachementMcService}")
	private RattachementMcService rattachementMcService;
	@ManagedProperty("#{printMgrBean}")
	private PrintMgrBean printMgrBean;
	@ManagedProperty("#{impressionService}")
	private ImpressionService impressionService;
	@ManagedProperty("#{inscriptionExamenService}")
	private InscriptionExamenService inscriptionExamenService;
	@ManagedProperty("#{bilanSessionService}")
	private BilanSessionService bilanSessionService;
	@ManagedProperty("#{bilanUeService}")
	private BilanUeService bilanUeService;
	@ManagedProperty("#{bilanMcService}")
	private BilanMcService bilanMcService;
	@ManagedProperty(value = "#{periodeService}")
	private PeriodeService periodeService;
	@ManagedProperty("#{coefficientExamenService}")
	private CoefficientExamenService coefficientExamenService;
	@ManagedProperty("#{bilanControleContinuService}")
	private BilanControleContinuService bilanControleContinuService;
	@ManagedProperty("#{examenNoteSessionBean}")
	private ExamenNoteSessionBean examenNoteSessionBean;
	@ManagedProperty("#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabService;
	@ManagedProperty("#{searchPlanningBean}")
	private SearchPlanningBean searchPlanningBean;
	@ManagedProperty("#{noteEliminatoireService}")
	private NoteEliminatoireService noteEliminatoireService;
	@ManagedProperty("#{diplomeFinEtudeService}")
	private DiplomeFinEtudeService diplomeFinEtudeService;
	@ManagedProperty("#{attestationFinEtudeService}")
	private AttestationFinEtudeService attestationFinEtudeService;
	@ManagedProperty("#{signatureDiplomeFinEtudeService}")
	private SignatureDiplomeFinEtudeService signatureDiplomeFinEtudeService;
	private SituationEntiteDto situationCree;
	private SituationEntiteDto situationClose;
	private SituationEntiteDto situationEnregistre;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> ofList;
	private List<SelectItem> periodeList;
	private List<SelectItem> planingExamenList;
	private List<SelectItem> sessionExamenList;
	private DeliberationSessionDto deliberationSessionDto;
	private ResourceBundle bilanBundle;
	private boolean editMode;
	private boolean showDetail;
	private boolean showTable;
	private List<DeliberationSessionDto> deliberationList;
	private List<NoteEtudiantModel> noteEtudiantModel;
	private List<InscriptionExamenDto> etudiants;
	private List<InscriptionExamenDto> notes;
	private List<BilanControleContinuDto> notesControleContinu;
	private List<InscriptionExamenDto> notesControleIntermeidiare;

	private List<String> columns;
	// private List<LinkedHashMap<String, String>> deliberation;
	private List<DeliberationModel> deliberation;
	private List<LinkedHashMap<String, String>> filtredDeliberation;
	private LinkedHashMap<String, String> line;
	private List<CrossTab> moyenneCollection;

	private List<RepartitionUeDto> ueList;
	private List<RattachementMcDto> mcList;
	private List<BilanSessionDto> bilanSessionList;
	private String exportFileName;
	private List<MentionDto> mentionList;
	private boolean cloture;
	private boolean clotured;
	// private Integer searchPlanningBean.getAnneeAcademiqueId();
	// private Integer searchPlanningBean.getPeriodeId();
	// private Integer searchPlanningBean.getOofId();
	// private Long searchPlanningBean.getPlanningSessionId();
	private Integer niveauId;
	private boolean bilanAnnuelAllowed;
	private boolean listChanged;
	private PlanningSessionDto planningSessionDto;
	private List<RattachementMcDto> rattachementMcDtos;
	private BilanSessionDto bilanSessionDto;
	private List<NoteEliminatoireDto> noteEliminatoireDtos;

	/**
	 * [DeliberationBean.DeliberationBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:23:38
	 */
	public DeliberationBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bilanBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.BILAN_BUNDLE_MSG_NAME);
		editMode = true;
		String viewId = facesContext.getViewRoot().getViewId();
		cloture = false;
		if (viewId != null && viewId.toLowerCase().contains("cloture")) {
			cloture = true;
		}
	}

	@PostConstruct
	public void init() {
		deliberationSessionDto = new DeliberationSessionDto();
		planningSessionDto = new PlanningSessionDto();
		loadSituation();

	}

	/**
	 * [DeliberationBean.loadSituationCree] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 09:02:51
	 */
	public void loadSituation() {
		if (situationCree == null || situationCree.getId() == 0) {
			situationCree = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DELIBERATION_SESSION_CODE,
							UtilConstants.SITUTAION_CREEE_CODE);
		}

		if (situationClose == null || situationClose.getId() == 0) {
			situationClose = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DELIBERATION_SESSION_CODE,
							UtilConstants.SITUTAION_CLOTUREE_CODE);
		}

		if (situationEnregistre == null || situationEnregistre.getId() == 0) {
			situationEnregistre = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DELIBERATION_SESSION_CODE,
							UtilConstants.SITUTAION_ENREGISTREE_CODE);
		}

	}

	/**
	 * [DeliberationBean.loadMention] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 oct. 2014 13:50:40
	 */
	// private void loadMention() {
	// mentionList =
	// utilBean.loadAllMention(searchPlanningBean.getAnneeAcademiqueId());
	// }

	/**
	 * [DeliberationBean.loadExamenSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:29:45
	 */
	public void loadExamenSession() {
		try {
			if (searchPlanningBean.getOofId() == null
					|| searchPlanningBean.getPeriodeId() == null
					|| searchPlanningBean.getAnneeAcademiqueId() == null) {
				return;
			}
			PlanningSessionDto searchDto = new PlanningSessionDto();
			searchDto.setOuvertureOffreFormationId(searchPlanningBean
					.getOofId());
			searchDto.setIdPeriode(searchPlanningBean.getPeriodeId());
			searchDto.setAnneeAcademiqueId(searchPlanningBean
					.getAnneeAcademiqueId());
			sessionExamenList = utilBean.loadExamenGroupedPlanning(searchDto);
		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [DeliberationBean.planningChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 5 nov. 2014 15:13:58
	 */
	public void planningChanged() {
		showTable = false;
		showDetail = false;
		if (searchPlanningBean.getPlanningSessionId() != null) {
			loadPanningSession();
			loadNoteEliminatoire();
			findDeliberation();
		}
		if (searchPlanningBean.getPeriodeId() != null) {
			PeriodeDto _periodeDto = periodeService.findById(searchPlanningBean
					.getPeriodeId());
			if (_periodeDto != null) {
				niveauId = _periodeDto.getIdNiveau();
			}
		}
		findBilanPeriode();
	}

	/**
	 * [DeliberationBean.loadRattachementMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 11:13:10
	 */
	private void loadRattachementMc() {
		rattachementMcDtos = utilBean.loadRattachementMc(
				searchPlanningBean.getOofId(),
				searchPlanningBean.getPeriodeId());
	}

	/**
	 * [DeliberationBean.loadPalnningSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 dÃ©c. 2014 09:49:56
	 */
	public void loadPanningSession() {
		if (searchPlanningBean.getPlanningSessionId() != null) {
			planningSessionDto = planningSessionService
					.findById(searchPlanningBean.getPlanningSessionId());
		}
	}

	/**
	 * [DeliberationBean.loadNoteEliminatoire] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 15:22:40
	 */
	public void loadNoteEliminatoire() {
		if (searchPlanningBean.getPlanningSessionId() != null
				&& planningSessionDto.getCoefficientNoteEliminatoire() != 0) {
			NoteEliminatoireDto searchDto = new NoteEliminatoireDto(
					searchPlanningBean.getOofId(),
					searchPlanningBean.getPeriodeId(), null,
					searchPlanningBean.getPlanningSessionId());
			noteEliminatoireDtos = noteEliminatoireService
					.findAdvanced(searchDto);
		}
	}

	/**
	 * [DeliberationBean.loadPlanningSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:33:03
	 */
	// public void loadPlanningSession() {
	// try {
	// if (searchPlanningBean.getOofId() == null ||
	// searchPlanningBean.getPeriodeId() == null ||
	// searchPlanningBean.getAnneeAcademiqueId() == null) {
	// return;
	// }
	// planingExamenList = new ArrayList<SelectItem>();
	// PlanningSessionDto searchDto = new PlanningSessionDto();
	// searchDto.setOuvertureOffreFormationId(searchPlanningBean.getOofId());
	// searchDto.setIdPeriode(searchPlanningBean.getPeriodeId());
	// searchDto.setAnneeAcademiqueId(searchPlanningBean.getAnneeAcademiqueId());
	// List<PlanningSessionDto> list = planningSessionService
	// .findAdvanced(searchDto);
	// if (list != null) {
	// for (PlanningSessionDto item : list) {
	// planingExamenList.add(new SelectItem(item.getId(), item
	// .getIntitule()));
	// }
	// if (planingExamenList.size() == 1) {
	// searchPlanningBean.setPlanningSessionId((Long) planingExamenList.get(0)
	// .getValue());
	// // planningChanged();
	// }
	// }
	//
	// } catch (Exception e) {
	// log.error(e.getMessage());
	// Utility.showErrorMessage(e.getMessage());
	// }
	// }

	/**
	 * [DeliberationBean.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:26:12
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [DeliberationBean.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:26:12
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [DeliberationBean.anneeAcademiqueList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:26:12
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [DeliberationBean.anneeAcademiqueList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:26:12
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [DeliberationBean.ofList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:26:12
	 * @return the ofList
	 */
	public List<SelectItem> getOfList() {
		return ofList;
	}

	/**
	 * [DeliberationBean.ofList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:26:12
	 * @param ofList
	 *            the ofList to set
	 */
	public void setOfList(List<SelectItem> ofList) {
		this.ofList = ofList;
	}

	/**
	 * [DeliberationBean.periodeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:26:12
	 * @return the periodeList
	 */
	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	/**
	 * [DeliberationBean.periodeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:26:12
	 * @param periodeList
	 *            the periodeList to set
	 */
	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	/**
	 * [DeliberationBean.deliberationSessionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:26:12
	 * @return the deliberationSessionDto
	 */
	public DeliberationSessionDto getDeliberationSessionDto() {
		return deliberationSessionDto;
	}

	/**
	 * [DeliberationBean.deliberationSessionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:26:12
	 * @param deliberationSessionDto
	 *            the deliberationSessionDto to set
	 */
	public void setDeliberationSessionDto(
			DeliberationSessionDto deliberationSessionDto) {
		this.deliberationSessionDto = deliberationSessionDto;
	}

	/**
	 * [DeliberationBean.editMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:27:28
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [DeliberationBean.editMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:27:28
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * [DeliberationBean.planingExamenList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:30:43
	 * @return the planingExamenList
	 */
	public List<SelectItem> getPlaningExamenList() {
		return planingExamenList;
	}

	/**
	 * [DeliberationBean.planingExamenList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:30:43
	 * @param planingExamenList
	 *            the planingExamenList to set
	 */
	public void setPlaningExamenList(List<SelectItem> planingExamenList) {
		this.planingExamenList = planingExamenList;
	}

	/**
	 * [DeliberationBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:31:11
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [DeliberationBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:31:11
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [DeliberationBean.sessionExamenList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:32:51
	 * @return the sessionExamenList
	 */
	public List<SelectItem> getSessionExamenList() {
		return sessionExamenList;
	}

	/**
	 * [DeliberationBean.sessionExamenList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:32:51
	 * @param sessionExamenList
	 *            the sessionExamenList to set
	 */
	public void setSessionExamenList(List<SelectItem> sessionExamenList) {
		this.sessionExamenList = sessionExamenList;
	}

	/**
	 * [DeliberationBean.planningSessionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:33:27
	 * @return the planningSessionService
	 */
	public PlanningSessionService getPlanningSessionService() {
		return planningSessionService;
	}

	/**
	 * [DeliberationBean.planningSessionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:33:27
	 * @param planningSessionService
	 *            the planningSessionService to set
	 */
	public void setPlanningSessionService(
			PlanningSessionService planningSessionService) {
		this.planningSessionService = planningSessionService;
	}

	/**
	 * [DeliberationBean.showDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:34:16
	 * @return the showDetail
	 */
	public boolean isShowDetail() {
		return showDetail;
	}

	/**
	 * [DeliberationBean.showDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:34:16
	 * @param showDetail
	 *            the showDetail to set
	 */
	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}

	/**
	 * [DeliberationBean.showTable] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:34:16
	 * @return the showTable
	 */
	public boolean isShowTable() {
		return showTable;
	}

	/**
	 * [DeliberationBean.showTable] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:34:16
	 * @param showTable
	 *            the showTable to set
	 */
	public void setShowTable(boolean showTable) {
		this.showTable = showTable;
	}

	/**
	 * [DeliberationBean.deliberationSessionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:45:50
	 * @return the deliberationSessionService
	 */
	public DeliberationSessionService getDeliberationSessionService() {
		return deliberationSessionService;
	}

	/**
	 * [DeliberationBean.deliberationSessionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:45:50
	 * @param deliberationSessionService
	 *            the deliberationSessionService to set
	 */
	public void setDeliberationSessionService(
			DeliberationSessionService deliberationSessionService) {
		this.deliberationSessionService = deliberationSessionService;
	}

	/**
	 * [DeliberationBean.findDeliberation] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:34:28
	 */
	public void findDeliberation() {
		showTable = false;
		showDetail = false;
		if (listChanged) {
			// searchPlanningBean.getPlanningSessionId() = null;
		}
		loadDeliberation();
		if (deliberationList != null && !deliberationList.isEmpty()) {
			showTable = true;
			if (deliberationList.size() == 1) {
				deliberationSessionDto = deliberationList.get(0);
				selectRow();
			}
		} else {
			Utility.showWarningMessage(bilanBundle
					.getString("deliberation_data_table_seacrh_result_no_result"));
			return;
		}
		listChanged = false;

	}

	/**
	 * [DeliberationBean.loadDeliberation] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 nov. 2014 11:36:58
	 */
	private void loadDeliberation() {
		DeliberationSessionDto searchDto = new DeliberationSessionDto();
		searchDto.setOofId(searchPlanningBean.getOofId());
		searchDto.setPeriodeId(searchPlanningBean.getPeriodeId());
		searchDto.setAnneeAcademiqueId(searchPlanningBean
				.getAnneeAcademiqueId());
		searchDto.setPlanningSessionId(searchPlanningBean
				.getPlanningSessionId());
		if (!cloture && situationClose.getId() != 0) {
			searchDto.setSituationId(situationClose.getId());
		}
		deliberationList = deliberationSessionService.findAdvanced(searchDto);

	}

	/**
	 * [DeliberationBean.onRowSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 10:31:45
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		setShowDetail(false);
		this.deliberationSessionDto = ((DeliberationSessionDto) event
				.getObject());
		// searchPlanningBean.getPlanningSessionId() =
		// deliberationSessionDto.getPlanningSessionId();
		selectRow();
	}

	/**
	 * [DeliberationBean.onNoteRowSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 14:22:30
	 * @param event
	 */
	public void onNoteRowSelect(SelectEvent event) {
		DeliberationModel line = ((DeliberationModel) event.getObject());
		bilanSessionDto = line.getBilanSessionDto();
		if (bilanSessionDto == null) {
			bilanSessionDto = new BilanSessionDto();
		}
	}

	/**
	 * [DeliberationBean.selectRow] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 09:30:16
	 */
	public void selectRow() {
		loadRattachementMc();
		findNoteToValidate(true);

		setShowDetail(true);
		clotured = false;
		deliberationSessionDto.setSituationLibelleFr("Créée");
		if (deliberationSessionDto.getSituationId() != null
				&& situationClose != null
				&& deliberationSessionDto.getSituationId().equals(
						situationClose.getId())) {
			clotured = true;
			deliberationSessionDto.setSituationLibelleFr("Clôturée");
		} else if (deliberationSessionDto.getSituationId() != null
				&& situationEnregistre != null
				&& deliberationSessionDto.getSituationId().equals(
						situationEnregistre.getId())) {

			deliberationSessionDto.setSituationLibelleFr("Enregistrée");
			{
			}
		}
	}

	/**
	 * [DeliberationBean.saveDeliberation] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 17:44:55
	 */
	public void saveDeliberation() {
		try {
			if (situationCree == null || situationCree.getId() == 0) {
				Utility.showErrorMessage(bilanBundle
						.getString("deliberation_erreur_situation_cree_inexistante"));
				return;
			}
			if (deliberationSessionDto.getBilanPeriode()) {
				if (searchPlanningBean.getAnneeAcademiqueId() != null
						&& searchPlanningBean.getOofId() != null
						&& searchPlanningBean.getPeriodeId() != null) {
					DeliberationSessionDto bilanPeriodeAlreadyExist = deliberationSessionService
							.bilanPeriodeExist(
									searchPlanningBean.getAnneeAcademiqueId(),
									searchPlanningBean.getOofId(),
									searchPlanningBean.getPeriodeId());
					if (bilanPeriodeAlreadyExist != null
							&& deliberationSessionDto.getId() != null
							&& !bilanPeriodeAlreadyExist.getId().equals(
									deliberationSessionDto.getId())) {
						Utility.showErrorMessage(bilanBundle
								.getString("deliberation_erreur_bilan_periode_existe_deja"));
						return;
					}
				}
			}
			deliberationSessionDto.setAnneeAcademiqueId(searchPlanningBean
					.getAnneeAcademiqueId());
			deliberationSessionDto.setPlanningSessionId(searchPlanningBean
					.getPlanningSessionId());
			deliberationSessionDto.setPeriodeId(searchPlanningBean
					.getPeriodeId());
			deliberationSessionDto.setOofId(searchPlanningBean.getOofId());

			// deliberationSessionDto.setSituationId(situationCree.getId());
			// deliberationSessionService.insertOrUpdate(deliberationSessionDto);
			loadDeliberation();
			findBilanPeriode();
			deliberationSessionDto.setSituationId(situationEnregistre.getId());
			deliberationSessionService.insertOrUpdate(deliberationSessionDto);
			if (!saveBilanSession()) {
				deliberationSessionDto.setSituationId(situationCree.getId());
				deliberationSessionService
						.insertOrUpdate(deliberationSessionDto);
				showTable = true;
				showDetail = true;
				return;
			}

			// deliberationSessionDto.setPlanningSessionId(searchPlanningBean.getPlanningSessionId());

			// selectRow();
			// saveBilanSession();
			showTable = true;
			showDetail = true;
			Utility.showSuccessSaveMessage();
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * [DeliberationBean.bilanAnnuelAllowed] Method
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 15:07:43
	 * @return
	 */
	public void findBilanPeriode() {
		if (searchPlanningBean.getPeriodeId() != null
				&& searchPlanningBean.getAnneeAcademiqueId() != null
				&& searchPlanningBean.getOofId() != null) {
			PeriodeDto _periodeDto = utilBean.findPeriode(searchPlanningBean
					.getPeriodeId());
			if (_periodeDto != null && _periodeDto.getIdNiveau() != 0) {
				List<PeriodeDto> periodeList = periodeService
						.findByLevelId(_periodeDto.getIdNiveau());
				if (periodeList != null) {
					for (PeriodeDto _periode : periodeList) {
						DeliberationSessionDto bialnPeriodeExist = deliberationSessionService
								.bilanPeriodeExist(searchPlanningBean
										.getAnneeAcademiqueId(),
										searchPlanningBean.getOofId(), _periode
												.getId());
						if (bialnPeriodeExist != null) {
							bilanAnnuelAllowed = false;
							return;
						}
					}
					bilanAnnuelAllowed = true;
					return;
				}
			}
		}
		bilanAnnuelAllowed = false;
	}

	/**
	 * [DeliberationBean.situationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 10:10:39
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [DeliberationBean.situationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 10:10:39
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [DeliberationBean.situationCree] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 10:10:39
	 * @return the situationCree
	 */
	public SituationEntiteDto getSituationCree() {
		return situationCree;
	}

	/**
	 * [DeliberationBean.situationCree] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 10:10:39
	 * @param situationCree
	 *            the situationCree to set
	 */
	public void setSituationCree(SituationEntiteDto situationCree) {
		this.situationCree = situationCree;
	}

	/**
	 * [DeliberationBean.deliberationList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 10:24:20
	 * @return the deliberationList
	 */
	public List<DeliberationSessionDto> getDeliberationList() {
		return deliberationList;
	}

	/**
	 * [DeliberationBean.deliberationList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 10:24:20
	 * @param deliberationList
	 *            the deliberationList to set
	 */
	public void setDeliberationList(
			List<DeliberationSessionDto> deliberationList) {
		this.deliberationList = deliberationList;
	}

	/**
	 * [DeliberationBean.newDeliberation] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 10:35:54
	 */
	public void newDeliberation() {
		deliberationSessionDto = new DeliberationSessionDto();
		deliberationSessionDto.setDateDeliberation(new Date());
		deliberationSessionDto.setHeureDeliberation(new Date());
		showDetail = true;
		loadRattachementMc();
		findNoteToValidate(true);
		// saveDeliberation();
	}

	/**
	 * [DeliberationBean.noteEtudiantModel] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 14:49:51
	 * @return the noteEtudiantModel
	 */
	public List<NoteEtudiantModel> getNoteEtudiantModel() {
		return noteEtudiantModel;
	}

	/**
	 * [DeliberationBean.noteEtudiantModel] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 14:49:51
	 * @param noteEtudiantModel
	 *            the noteEtudiantModel to set
	 */
	public void setNoteEtudiantModel(List<NoteEtudiantModel> noteEtudiantModel) {
		this.noteEtudiantModel = noteEtudiantModel;
	}

	/**
	 * [DeliberationBean.findNoteToValidate] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 14:52:07
	 */
	public void findNoteToValidate(boolean loadMcUe) {

		showDetail = true;
		if (planningSessionDto != null && !planningSessionDto.getNotesValide()) {
			Utility.showWarningMessage(bilanBundle
					.getString("bilan_examen_erreur_note_non_valide"));
			return;
		}
		// loadMention();
		loadStudentOfSession();
		loadNotes();
		if (planningSessionDto.getAvecControleContinu()) {
			loadNoteControleContinu();
		}
		if (planningSessionDto.getAvecControleIntermediaire()) {
			loadNoteControleIntermediare();
		}
		if (etudiants != null) {
			examenNoteSessionBean.loadRepartitionUe(
					searchPlanningBean.getOofId(),
					searchPlanningBean.getPeriodeId());
			// noteEtudiantModel = new ArrayList<NoteEtudiantModel>();
			bilanSessionList = new ArrayList<BilanSessionDto>();
			deliberation = new ArrayList<DeliberationModel>();
			for (InscriptionExamenDto _etudiant : etudiants) {
				DeliberationModel _deliberationModel = new DeliberationModel();
				line = new LinkedHashMap<String, String>();
				if (notes != null) {
					BilanSessionDto bilanSession = new BilanSessionDto();
					List<BilanUeDto> noteList = new ArrayList<BilanUeDto>();
					bilanSession.setBilanUes(noteList);
					DossierInscriptionAdministrativeDto dia = new DossierInscriptionAdministrativeDto();
					dia.setId(_etudiant.getDossierInscriptionAdministrativeId());
					bilanSession.setDossierInscriptionAdministrativeId(dia
							.getId());
					_deliberationModel.setDiaId(dia.getId());
					Double moyenne = 0.0;
					Double somNote = 0.0;
					Double somCoefficient = 0.0;

					for (InscriptionExamenDto _note : notes) {
						if (_etudiant.getDossierInscriptionAdministrativeId() != null
								&& _note.getDossierInscriptionAdministrativeId() != null
								&& _etudiant
										.getDossierInscriptionAdministrativeId()
										.equals(_note
												.getDossierInscriptionAdministrativeId())) {

							// if (_note.getMoyenneGenerale() == null) {
							// _note.setMoyenneGenerale(0.0);
							// }

							fillCoefficientExamen(_note);
							double noteCC = 0.0;
							double noteCI = 0.0;
							if (planningSessionDto.getAvecControleContinu()) {
								BilanControleContinuDto bilanCC = findNoteControleContinu(
										_note.getDossierInscriptionAdministrativeId(),
										_note.getRattachementMcId());
								if (bilanCC != null
										&& bilanCC.getNote() != null) {
									noteCC = bilanCC.getNote();
									_note.setNoteControleContinu(bilanCC
											.getNote());
								} else {
									_note.setNoteControleContinu(null);
								}

							}
							if (planningSessionDto
									.getAvecControleIntermediaire()) {
								InscriptionExamenDto _noteCIDto = findNoteControleIntermeidiare(
										_note.getDossierInscriptionAdministrativeId(),
										_note.getRattachementMcId());
								if (_noteCIDto != null
										&& _noteCIDto.getNoteJury() != null) {
									noteCI = _noteCIDto.getNoteJury();
									_note.setNoteControleIntermediaire(_noteCIDto
											.getNoteJury());
								} else {
									_note.setNoteControleIntermediaire(null);
								}

							}

							// fillCoefficientExamen(_note);
							if (_note.getTotalCoefficient() == 0) {
								_note.setTotalCoefficient(1.0);
							}
							_note.setMoyenneGenerale(((noteCC * _note
									.getCoefficientControleContinu())
									+ (noteCI * _note
											.getCoefficientControleIntermediaire()) + (_note
									.getNoteExamen() * _note
									.getCoefficientExamen()))
									/ _note.getTotalCoefficient());
							_note.setMoyenneGenerale(Utility
									.double2position(_note.getMoyenneGenerale()));

							if (_note.getRattachementMcCoefficient() != null) {
								somNote = somNote
										+ (_note.getMoyenneGenerale() * _note
												.getRattachementMcCoefficient());
								somCoefficient = somCoefficient
										+ _note.getRattachementMcCoefficient();
							}

							bilanSession.setMatriculeEtudiant(_note
									.getNumeroMatricule());
							bilanSession.setNumeroInscriptionEtudiant(_note
									.getNumeroInscription());
							bilanSession.setUrlPhoto(_note.getUrlPhoto());
							bilanSession.setNomLatinEtudiant(_note
									.getIndividuNomLatin());
							bilanSession.setPrenomLatinEtudiant(_note
									.getIndividuPrenomLatin());
							fillNoteEliminatoire(_note);
							examenNoteSessionBean.addBilanUe(bilanSession,
									_note);

							if (line.get("N° inscription") == null) {
								line.put("N° inscription",
										_note.getNumeroInscription());
							}
							if (line.get("Etudiant") == null) {
								line.put(
										"Etudiant",
										_note.getIndividuNomLatin()
												+ " "
												+ _note.getIndividuPrenomLatin());
							}
							String oldMoyenne = line.get(_note.getUeCode());
							if (oldMoyenne == null) {
								moyenne = 0.0;

								line.put(_note.getUeCode(),
										Utility.formatNote(moyenne));
							}
							// else
							{
								somNote = somNote
										+ (_note.getMoyenneGenerale() * _note
												.getRattachementMcCoefficient());
								somCoefficient = somCoefficient
										+ _note.getRattachementMcCoefficient();

							}
							if (line.get(_note.getMcCode()) == null) {
								line.put(_note.getMcCode(), Utility
										.formatNote(_note.getMoyenneGenerale()));
								line.put(_note.getMcCode() + "_NoteExamen",
										Utility.formatNote(_note
												.getNoteExamen()));
								line.put(_note.getMcCode() + "_NoteJury",
										Utility.formatNote(_note.getNoteJury()));
								if (_note.getMoyenneControleContinu() == null) {
									line.put(_note.getMcCode() + "_NoteCC",
											null);
								} else {
									line.put(
											_note.getMcCode() + "_NoteCC",
											Utility.formatNote(_note
													.getMoyenneControleContinu()));
								}

							}
						}
					}

					if (somCoefficient != 0) {
						moyenne = somNote / somCoefficient;
					}
					bilanSession.setMoyenne(moyenne);
					examenNoteSessionBean.calculMoyenneUeEtudiant(bilanSession);
					examenNoteSessionBean.calculCreditEtudiant(bilanSession);
					bilanSessionList.add(bilanSession);
					_deliberationModel.setBilanSessionDto(bilanSession);
				}

				line.put("MG", "0.0");
				_deliberationModel.setLine(line);
				deliberation.add(_deliberationModel);
			}
			calculMoyenneUeEtudiant();
			if (loadMcUe) {
				calculMoyenneUeMc();
			}
		}
		System.out.println(deliberation);
	}

	/**
	 * [DeliberationBean.fillNoteEliminatoire] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 15:31:09
	 * @param _note
	 */
	public void fillNoteEliminatoire(InscriptionExamenDto _inscriptionExamenDto) {
		_inscriptionExamenDto.setNoteEliminatoire(_inscriptionExamenDto
				.getRattachementMcNoteEliminatoire());
		if (noteEliminatoireDtos != null && !noteEliminatoireDtos.isEmpty()) {
			for (NoteEliminatoireDto noteEliminatoireDto : noteEliminatoireDtos) {
				if (noteEliminatoireDto.getRattachementMcId().equals(
						_inscriptionExamenDto.getRattachementMcId())) {
					_inscriptionExamenDto
							.setNoteEliminatoire(noteEliminatoireDto
									.getNoteEliminatoireAjuste());
				}
			}
		}
	}

	/**
	 * [DeliberationBean.fillCoefficientExamen] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 11:12:27
	 * @param _note
	 */
	private void fillCoefficientExamen(InscriptionExamenDto _note) {
		_note.setCoefficientExamen(_note.getRattachementMcCoefficient());
		_note.setCoefficientControleContinu(0.0);
		_note.setCoefficientControleIntermediaire(0.0);
		_note.setTotalCoefficient(_note.getCoefficientExamen());
		if (_note != null && rattachementMcDtos != null) {
			for (RattachementMcDto rmc : rattachementMcDtos) {
				if (_note.getRattachementMcId() != null
						&& _note.getRattachementMcId().equals(rmc.getId())) {
					List<CoefficientExamenDto> coefficientExamenDtos = rmc
							.getCoefficientExamenDtos();
					if (coefficientExamenDtos != null) {
						for (CoefficientExamenDto _ce : coefficientExamenDtos) {
							if (_ce.getPeriodeId() != null
									&& _ce.getPeriodeId().equals(
											searchPlanningBean.getPeriodeId())
									&& _ce.getOofId() != null
									&& _ce.getOofId().equals(
											searchPlanningBean.getOofId())) {
								_note.setCoefficientExamen(_ce
										.getCoefficientExamen());
								_note.setCoefficientControleContinu(_ce
										.getCoefficientControleContinu());
								_note.setCoefficientControleIntermediaire(_ce
										.getCoefficientControleIntermediaire());
								_note.setTotalCoefficient(_note
										.getCoefficientExamen()
										+ _note.getCoefficientControleContinu()
										+ _note.getCoefficientControleIntermediaire());
								if (_note.getTotalCoefficient() == 0) {
									_note.setTotalCoefficient(1.0);
								}
								return;
							}
						}
					}
				}
			}
		}

	}

	/**
	 * [DeliberationBean.loadStudentOfSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 14:52:19
	 */
	public void loadStudentOfSession() {
		try {
			if (searchPlanningBean.getPlanningSessionId() != 0) {
				etudiants = inscriptionExamenService
						.findByPlanning(searchPlanningBean
								.getPlanningSessionId());
			}
		} catch (Exception e) {

		}
	}

	/**
	 * [DeliberationBean.loadNotes] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 14:53:10
	 */
	public void loadNotes() {
		try {
			if (searchPlanningBean.getPlanningSessionId() != 0) {
				notes = inscriptionExamenService
						.findNoteByPlanning(searchPlanningBean
								.getPlanningSessionId());
			}
		} catch (Exception e) {

		}
	}

	/**
	 * [DeliberationBean.loadNoteControleContinu] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 12:07:52
	 */
	public void loadNoteControleContinu() {
		BilanControleContinuDto bilanCCSearch = new BilanControleContinuDto();
		bilanCCSearch.setAnneeAcademiqueId(searchPlanningBean
				.getAnneeAcademiqueId());
		bilanCCSearch.setOofId(searchPlanningBean.getOofId());
		bilanCCSearch.setPeriodeId(searchPlanningBean.getPeriodeId());
		notesControleContinu = bilanControleContinuService
				.findAdvanced(bilanCCSearch);
	}

	/**
	 * [DeliberationBean.loadNoteControleIntermediare] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 12:26:45
	 */
	public void loadNoteControleIntermediare() {
		/***** recuperer la session avec type 'Controle intermeidiare' *******/
		NomenclatureDto _ncControleIntermediare = utilBean
				.loadNcTypeSessionControleIntermeidiare();
		if (_ncControleIntermediare != null) {
			PlanningSessionDto searchDto = new PlanningSessionDto();
			searchDto.setAnneeAcademiqueId(searchPlanningBean
					.getAnneeAcademiqueId());
			searchDto.setOuvertureOffreFormationId(searchPlanningBean
					.getOofId());
			searchDto.setIdPeriode(searchPlanningBean.getPeriodeId());
			searchDto.setNcTypeSessionId(_ncControleIntermediare.getId());
			List<PlanningSessionDto> planningList = planningSessionService
					.findAdvanced(searchDto);
			if (planningList == null || planningList.isEmpty()) {
				return;
			}
			if (planningList.size() > 1) {
				Utility.showErrorMessage(bilanBundle
						.getString("deliberation_erreur_plisieur_session_controle_intermeidiare"));
				return;
			}
			notesControleIntermeidiare = inscriptionExamenService
					.findByPlanningId(planningList.get(0).getId());
		}
	}

	/**
	 * [DeliberationBean.findNoteControleContinu] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 12:34:42
	 * @param diaId
	 * @param rattachementMcId
	 * @return
	 */
	public BilanControleContinuDto findNoteControleContinu(int diaId,
			int rattachementMcId) {
		if (notesControleContinu == null) {
			return null;
		}
		for (BilanControleContinuDto _noteCC : notesControleContinu) {
			if (_noteCC.getRattachementMcId() != null
					&& _noteCC.getRattachementMcId() == rattachementMcId
					&& _noteCC.getDossierInscriptionAdministrativeId() != null
					&& _noteCC.getDossierInscriptionAdministrativeId() == diaId) {
				return _noteCC;
			}
		}
		return null;
	}

	/**
	 * [DeliberationBean.findNoteControleIntermeidiare] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 12:37:33
	 * @param diaId
	 * @param rattachementMcId
	 * @return
	 */
	public InscriptionExamenDto findNoteControleIntermeidiare(int diaId,
			int rattachementMcId) {
		if (notesControleIntermeidiare == null) {
			return null;
		}
		for (InscriptionExamenDto _noteCI : notesControleIntermeidiare) {
			if (_noteCI.getRattachementMcId() != null
					&& _noteCI.getRattachementMcId() == rattachementMcId
					&& _noteCI.getDossierInscriptionAdministrativeId() != null
					&& _noteCI.getDossierInscriptionAdministrativeId() == diaId) {
				return _noteCI;
			}
		}
		return null;
	}

	/**
	 * [DeliberationBean.etudiants] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 14:52:47
	 * @return the etudiants
	 */
	public List<InscriptionExamenDto> getEtudiants() {
		return etudiants;
	}

	/**
	 * [DeliberationBean.etudiants] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 14:52:47
	 * @param etudiants
	 *            the etudiants to set
	 */
	public void setEtudiants(List<InscriptionExamenDto> etudiants) {
		this.etudiants = etudiants;
	}

	/**
	 * [DeliberationBean.notes] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 14:52:47
	 * @return the notes
	 */
	public List<InscriptionExamenDto> getNotes() {
		return notes;
	}

	/**
	 * [DeliberationBean.notes] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 14:52:47
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(List<InscriptionExamenDto> notes) {
		this.notes = notes;
	}

	/**
	 * [DeliberationBean.inscriptionExamenService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 14:53:30
	 * @return the inscriptionExamenService
	 */
	public InscriptionExamenService getInscriptionExamenService() {
		return inscriptionExamenService;
	}

	/**
	 * [DeliberationBean.inscriptionExamenService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 14:53:30
	 * @param inscriptionExamenService
	 *            the inscriptionExamenService to set
	 */
	public void setInscriptionExamenService(
			InscriptionExamenService inscriptionExamenService) {
		this.inscriptionExamenService = inscriptionExamenService;
	}

	/**
	 * [DeliberationBean.columns] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 15:50:35
	 * @return the columns
	 */
	public List<String> getColumns() {
		return columns;
	}

	/**
	 * [DeliberationBean.columns] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 15:50:35
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	/**
	 * [DeliberationBean.loadUeMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 07:55:56
	 */
	public void loadUeMc() {
		ueList = new ArrayList<RepartitionUeDto>();
		mcList = new ArrayList<RattachementMcDto>();
		if (searchPlanningBean.getOofId() != null
				&& searchPlanningBean.getPeriodeId() != null) {
			ueList = repartitionUeService.findReparationUe(
					searchPlanningBean.getOofId(),
					searchPlanningBean.getPeriodeId());
			if (ueList != null) {
				for (RepartitionUeDto _ue : ueList) {
					List<RattachementMcDto> rmcList = rattachementMcService
							.find(_ue.getUniteEnseignementId(), null);
					if (rmcList != null) {
						for (RattachementMcDto rmc : rmcList) {
							mcList.add(rmc);
						}
					}
				}
			}
		}
	}

	/**
	 * List
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 07:58:12
	 */
	public List<RattachementMcDto> findByUe(int ueId) {
		List<RattachementMcDto> result = new ArrayList<RattachementMcDto>();
		for (RattachementMcDto rmc : mcList) {
			if (rmc.getUeId() != null && rmc.getUeId().equals(ueId)) {
				result.add(rmc);
			}
		}
		return result;
	}

	/**
	 * [DeliberationBean.calculMoyenneUeEtudiant] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 15:52:05
	 */
	public void calculMoyenneUeEtudiant() {

		if (deliberation == null || deliberation.isEmpty()) {
			return;
		}
		loadUeMc();
		if (ueList != null) {
			for (DeliberationModel model : deliberation) {
				LinkedHashMap<String, String> map = model.getLine();
				if (map != null) {
					Double somMoyenneUe = 0.0;
					Double somCoefficientUe = 0.0;
					for (RepartitionUeDto _ue : ueList) {
						String moyenneUe = map.get(_ue
								.getUniteEnseignementCode());

						if (moyenneUe != null) {
							List<RattachementMcDto> rmcList = findByUe(_ue
									.getUniteEnseignementId());
							Double somMcNoteJury = 0.0;
							Double somCoefficientMc = 0.0;
							if (rmcList != null) {
								for (RattachementMcDto rmc : rmcList) {
									String noteMc = map.get(rmc.getMcCode());

									if (noteMc != null) {
										somMcNoteJury = somMcNoteJury
												+ (new Double(noteMc) * rmc
														.getCoefficient());
										somCoefficientMc = somCoefficientMc
												+ rmc.getCoefficient();
										rmc.setMcMoyenneGenerale(Utility
												.double2position(rmc
														.getMcMoyenneGenerale()
														+ new Double(noteMc)));
									}
								}
								if (somCoefficientMc != 0) {
									Double _moyenneUe = Utility
											.double2position((somMcNoteJury / somCoefficientMc));
									moyenneUe = Utility.formatNote(_moyenneUe);
									map.put(_ue.getUniteEnseignementCode(),
											moyenneUe);
									_ue.setUeMoyenneGenerale(Utility
											.double2position(_ue
													.getUeMoyenneGenerale()
													+ _moyenneUe));
									somMoyenneUe = somMoyenneUe
											+ (_moyenneUe * _ue
													.getCoefficient());
									somCoefficientUe = somCoefficientUe
											+ _ue.getCoefficient();

								}
							}
						}
					}
					if (somCoefficientUe != 0) {
						Double mg = Utility.double2position(somMoyenneUe
								/ somCoefficientUe);
						map.put("MG", Utility.formatNote(mg));
						// map.put("Mention", getMention(mg));
					}

				}
			}
		}
	}

	/**
	 * [DeliberationBean.calculMoyenneUeMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 oct. 2014 17:35:30
	 */
	public void calculMoyenneUeMc() {
		if (ueList != null) {
			for (RepartitionUeDto _ue : ueList) {
				if (etudiants.size() != 0) {
					_ue.setUeMoyenneGenerale(Utility.double2position(_ue
							.getUeMoyenneGenerale() / etudiants.size()));
				} else {
					_ue.setUeMoyenneGenerale(0.0);
				}
			}
		}

		if (mcList != null) {
			for (RattachementMcDto _mc : mcList) {
				if (etudiants.size() != 0) {
					_mc.setMcMoyenneGenerale(Utility.double2position(_mc
							.getMcMoyenneGenerale() / etudiants.size()));
				} else {
					_mc.setMcMoyenneGenerale(0.0);
				}
			}
		}
	}

	/**
	 * [DeliberationBean.repartitionUeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 15:55:45
	 * @return the repartitionUeService
	 */
	public RepartitionUeService getRepartitionUeService() {
		return repartitionUeService;
	}

	/**
	 * [DeliberationBean.repartitionUeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 15:55:45
	 * @param repartitionUeService
	 *            the repartitionUeService to set
	 */
	public void setRepartitionUeService(
			RepartitionUeService repartitionUeService) {
		this.repartitionUeService = repartitionUeService;
	}

	/**
	 * [DeliberationBean.rattachementMcService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 15:55:45
	 * @return the rattachementMcService
	 */
	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	/**
	 * [DeliberationBean.rattachementMcService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 15:55:45
	 * @param rattachementMcService
	 *            the rattachementMcService to set
	 */
	public void setRattachementMcService(
			RattachementMcService rattachementMcService) {
		this.rattachementMcService = rattachementMcService;
	}

	/**
	 * [DeliberationBean.deliberation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 16:21:38
	 * @return the deliberation
	 */
	// public List<LinkedHashMap<String, String>> getDeliberation() {
	// return deliberation;
	// }

	/**
	 * [DeliberationBean.deliberation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 16:21:38
	 * @param deliberation
	 *            the deliberation to set
	 */
	// public void setDeliberation(List<LinkedHashMap<String, String>>
	// deliberation) {
	// this.deliberation = deliberation;
	// }

	/**
	 * [DeliberationBean.getColumnNames] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 09:43:05
	 * @return
	 */
	public List<Map.Entry<String, String>> getColumnNames() {
		if (deliberation != null && !deliberation.isEmpty()
				&& deliberation.get(0) != null && deliberation.get(0) != null
				&& deliberation.get(0).getLine() != null) {
			Set<Map.Entry<String, String>> moyenneSet = deliberation.get(0)
					.getLine().entrySet();
			return new ArrayList<Map.Entry<String, String>>(moyenneSet);
		}
		return null;

	}

	/**
	 * [DeliberationBean.renderColumn] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 dÃ©c. 2014 11:37:40
	 * @param key
	 * @return
	 */
	public boolean renderColumn(String key) {
		if (key != null && !key.contains("NoteExamen")
				&& !key.contains("NoteJury") && !key.contains("NoteCC")) {
			return true;
		}
		return false;
	}

	/**
	 * [DeliberationBean.isUe] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 14:30:10
	 * @param key
	 * @return
	 */
	public boolean isUe(String key) {
		if (ueList != null && key != null) {
			for (RepartitionUeDto ue : ueList) {
				if (ue.getUniteEnseignementLibelleFr().equals(key)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * [DeliberationBean.line] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 17:17:41
	 * @return the line
	 */
	public LinkedHashMap<String, String> getLine() {
		return line;
	}

	/**
	 * [DeliberationBean.line] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 oct. 2014 17:17:41
	 * @param line
	 *            the line to set
	 */
	public void setLine(LinkedHashMap<String, String> line) {
		this.line = line;
	}

	/**
	 * [DeliberationBean.imprimer] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 oct. 2014 12:37:18
	 */
	public void imprimer() {
		fillCrossTable();
		try {

			String name = "Déliberation_"
					+ planningSessionDto.getAnneeAcademiqueCode() + "_"
					+ planningSessionDto.getLibellePeriode();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String SUBREPORT_DIR = facesContext.getExternalContext()
					.getRealPath("/WEB-INF/classes")
					+ "/jasper/examen/deliberation/";
			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = SUBREPORT_DIR
					+ "examen_deliberation.jrxml";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.JASPER_PARAM_IMG_PAPS_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logopaps.png");
			params.put(Const.JASPER_PARAM_IMG_LOGO_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logo"
					+ sessionBean.getCodeEtablissement() + ".png");
			params.put(Const.JASPER_PARAM_OFFRE_FORMATION_KEY,
					planningSessionDto.getOffreFormationLibelleFr());
			params.put(Const.JASPER_PARAM_ANNEE_ACADEMIQUE_KEY,
					planningSessionDto.getAnneeAcademiqueCode());
			params.put(Const.JASPER_PARAM_PERIODE_KEY,
					planningSessionDto.getLibellePeriode());
			params.put(Const.JASPER_PARAM_NIVEAU_KEY,
					planningSessionDto.getNiveauLibelleLongLt());
			params.put(Const.JASPER_PARAM_SUBREPORT_DIR_KEY, SUBREPORT_DIR);
			byte[] bytes = impressionService.viewPDFWithDataSource(
					RESOURCE_PATH_TO_INPUT_FILE_JASPER, params,
					moyenneCollection);
			printMgrBean.imprimer(bytes, name, "pdf");

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [DeliberationBean.fillCrossTable] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 10:20:26
	 */
	public void fillCrossTable() {
		moyenneCollection = new ArrayList<CrossTab>();
		// FacesContext facesContext = FacesContext.getCurrentInstance();
		// DataTable table = (DataTable)
		// facesContext.getViewRoot().findComponent(
		// ":deliberationForm:tab:deliberationTable");
		//
		// deliberation = (List<LinkedHashMap<String, String>>)
		// table.getValue();
		// BeanComparator fieldComparator = new BeanComparator("MG");
		// Collections.sort(deliberation, fieldComparator);
		Integer i = 1;
		for (DeliberationModel model : deliberation) {
			LinkedHashMap<String, String> map = model.getLine();
			if (map != null) {
				// LinkedHashMap<String, String> map = dm.getMoyenne();
				String mat = map.get("N° inscription");
				// String mg = map.get("MG");
				String nomPrenom = map.get("Etudiant");
				// mat = mat + " " + nomPrenom;

				CrossTab _cross = new CrossTab();
				_cross.setColumn(bilanBundle
						.getString("bilan_reporting_column_numero_inscription"));
				_cross.setValue(mat);
				_cross.setRow(i.toString());
				moyenneCollection.add(_cross);

				_cross = new CrossTab();
				_cross.setColumn(bilanBundle
						.getString("bilan_reporting_column_etudiant"));
				_cross.setValue(nomPrenom);
				_cross.setRow(i.toString());
				moyenneCollection.add(_cross);

				for (Entry<String, String> entry : map.entrySet()) {
					String key = entry.getKey();
					if (key != null && !key.equals("N° inscription")
							&& !key.equals("Etudiant")
							&& !key.contains("NoteExamen")
							&& !key.contains("NoteJury")
							&& !key.contains("NoteCC")) {
						_cross = new CrossTab();
						_cross.setColumn(key);
						_cross.setValue(entry.getValue());
						_cross.setRow(i.toString());
						moyenneCollection.add(_cross);
					}
				}
				i++;

			}
		}
	}

	/**
	 * [DeliberationBean.printMgrBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 oct. 2014 12:39:03
	 * @return the printMgrBean
	 */
	public PrintMgrBean getPrintMgrBean() {
		return printMgrBean;
	}

	/**
	 * [DeliberationBean.printMgrBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 oct. 2014 12:39:03
	 * @param printMgrBean
	 *            the printMgrBean to set
	 */
	public void setPrintMgrBean(PrintMgrBean printMgrBean) {
		this.printMgrBean = printMgrBean;
	}

	/**
	 * [DeliberationBean.impressionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 oct. 2014 12:45:53
	 * @return the impressionService
	 */
	public ImpressionService getImpressionService() {
		return impressionService;
	}

	/**
	 * [DeliberationBean.impressionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 oct. 2014 12:45:53
	 * @param impressionService
	 *            the impressionService to set
	 */
	public void setImpressionService(ImpressionService impressionService) {
		this.impressionService = impressionService;
	}

	/**
	 * [DeliberationBean.moyenneCollection] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 oct. 2014 15:35:04
	 * @return the moyenneCollection
	 */
	public List<CrossTab> getMoyenneCollection() {
		return moyenneCollection;
	}

	/**
	 * [DeliberationBean.moyenneCollection] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 oct. 2014 15:35:04
	 * @param moyenneCollection
	 *            the moyenneCollection to set
	 */
	public void setMoyenneCollection(List<CrossTab> moyenneCollection) {
		this.moyenneCollection = moyenneCollection;
	}

	/**
	 * [DeliberationBean.ueList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 oct. 2014 17:23:43
	 * @return the ueList
	 */
	public List<RepartitionUeDto> getUeList() {
		return ueList;
	}

	/**
	 * [DeliberationBean.ueList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 oct. 2014 17:23:43
	 * @param ueList
	 *            the ueList to set
	 */
	public void setUeList(List<RepartitionUeDto> ueList) {
		this.ueList = ueList;
	}

	/**
	 * [DeliberationBean.mcList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 oct. 2014 17:23:43
	 * @return the mcList
	 */
	public List<RattachementMcDto> getMcList() {
		return mcList;
	}

	/**
	 * [DeliberationBean.mcList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 oct. 2014 17:23:43
	 * @param mcList
	 *            the mcList to set
	 */
	public void setMcList(List<RattachementMcDto> mcList) {
		this.mcList = mcList;
	}

	/**
	 * [DeliberationBean.exportFileName] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 13:50:30
	 * @return the exportFileName
	 */
	public String getExportFileName() {
		exportFileName = "DÃ©liberation_"
				+ deliberationSessionDto.getPsCodeAnneeAcademique() + "_"
				+ deliberationSessionDto.getPsPeriodeLibelle();
		return exportFileName;
	}

	/**
	 * [DeliberationBean.exportFileName] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 13:50:30
	 * @param exportFileName
	 *            the exportFileName to set
	 */
	public void setExportFileName(String exportFileName) {
		this.exportFileName = exportFileName;
	}

	/**
	 * [DeliberationBean.filtredDeliberation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 14:03:08
	 * @return the filtredDeliberation
	 */
	public List<LinkedHashMap<String, String>> getFiltredDeliberation() {
		return filtredDeliberation;
	}

	/**
	 * [DeliberationBean.filtredDeliberation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 14:03:08
	 * @param filtredDeliberation
	 *            the filtredDeliberation to set
	 */
	public void setFiltredDeliberation(
			List<LinkedHashMap<String, String>> filtredDeliberation) {
		this.filtredDeliberation = filtredDeliberation;
	}

	/**
	 * [DeliberationBean.colturerDeliberation] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 17:21:47
	 */
	public void colturerDeliberation() {
		try {
			// saveBilanSession();
			if (situationClose == null || situationClose.getId() == 0) {
				Utility.showErrorMessage(bilanBundle
						.getString("deliberation_erreur_situation_cloture_inexistante"));
				return;
			}

			if (generateDiplome()) {
				deliberationSessionDto.setDateCloture(new Date());
				deliberationSessionDto.setSituationId(situationClose.getId());
				deliberationSessionService
						.insertOrUpdate(deliberationSessionDto);
				selectRow();
				Utility.showSuccessSaveMessage();
				clotured = true;
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * [DeliberationBean.generateDiplome] Method
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 13:53:19
	 */
	public boolean generateDiplome() {
		try {

			if (!deliberationSessionDto.getBilanAnnuel()) {
				return true;
			}
			List<BilanSessionDto> _bilanSessionList = null;
			OuvertureOffreFormationDto ouvertureOffreFormationDto = utilBean
					.findOofById(searchPlanningBean.getOofId());
			if (searchPlanningBean.getOofId() != null) {
				OuvertureOffreFormationDto oofDto = utilBean
						.findOof(searchPlanningBean.getOofId());
				if (oofDto != null && oofDto.getCycleId() != null) {
					NiveauDto niveauDto = utilBean.loadLastNiveauOfCycle(oofDto
							.getCycleId());
					if (niveauDto != null && niveauDto.getId() != 0)
						_bilanSessionList = bilanSessionService
								.findBilanFinCycle(
										searchPlanningBean.getOofId(), 9/*
																		 * niveauDto
																		 * .
																		 * getId
																		 * ()
																		 */);
				}

				if (_bilanSessionList != null && !_bilanSessionList.isEmpty()) {

					for (BilanSessionDto bilanSessionDto : _bilanSessionList) {

						DiplomeFinEtudeDto diplomeFinEtudeDto = new DiplomeFinEtudeDto();
						diplomeFinEtudeDto.setDateCreation(new Date());

						diplomeFinEtudeDto.setDateObtention(bilanSessionDto
								.getDateDeliberation());
						diplomeFinEtudeDto
								.setAnneeAcademiqueId(searchPlanningBean
										.getAnneeAcademiqueId());
						diplomeFinEtudeDto.setDateObtention(bilanSessionDto
								.getDateDeliberation());
						diplomeFinEtudeDto.setIdEtablissement(sessionBean
								.getIdEtablissement());
						diplomeFinEtudeDto.setCycleId(oofDto.getCycleId());
						diplomeFinEtudeDto.setBilanSessionId(bilanSessionDto
								.getId());
						diplomeFinEtudeDto.setMentionId(bilanSessionDto
								.getMentionId());
						diplomeFinEtudeDto.setEstValide(false);
						diplomeFinEtudeDto.setEstValideEtab(false);
						diplomeFinEtudeDto.setEstValideFac(false);
						diplomeFinEtudeDto.setGenerationValide(false);
						String identifiant = generateIdentify(UtilDate
								.getYeafOfDate(bilanSessionDto
										.getDateDeliberation())
								+ ouvertureOffreFormationDto
										.getOffreFormationCode());
						diplomeFinEtudeDto.setIdentifiant(identifiant);
						DiplomeFinEtudeDto _diplomeFinEtudeDto = diplomeFinEtudeService
								.findUniqueByBilanSession(bilanSessionDto
										.getId());
						if (_diplomeFinEtudeDto != null) {
							diplomeFinEtudeDto.setId(_diplomeFinEtudeDto
									.getId());
						}
						diplomeFinEtudeDto = diplomeFinEtudeService
								.insertOrUpdate(diplomeFinEtudeDto);
						//generateSignature(diplomeFinEtudeDto);
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			return false;
		}
	}

	private void generateSignature(DiplomeFinEtudeDto diplomeFinEtudeDto) {
		List<NomenclatureDto> ncSignatureDiplomes = utilBean
				.loadNcSignatureDiplome();
		if (ncSignatureDiplomes != null) {

			for (NomenclatureDto nc : ncSignatureDiplomes) {
				SignatureDiplomeFinEtudeDto signatureDiplomeFinEtudeDto = new SignatureDiplomeFinEtudeDto();
				signatureDiplomeFinEtudeDto.setNcSignatureDiplomeId(nc.getId());
				signatureDiplomeFinEtudeDto.setNcSignatureDiplomeCode(nc
						.getCode());
				signatureDiplomeFinEtudeDto
						.setNcSignatureDiplomeLibelleLongFr(nc
								.getLibelleLongFr());

				signatureDiplomeFinEtudeDto.setAttestation(false);
				signatureDiplomeFinEtudeDto
						.setDiplomeFinEtudeId(diplomeFinEtudeDto.getId());
				SignatureDiplomeFinEtudeDto signExist = signatureDiplomeFinEtudeService
						.findUnique(signatureDiplomeFinEtudeDto
								.getNcSignatureDiplomeId(), diplomeFinEtudeDto
								.getId(), false);
				if (signExist != null) {
					signatureDiplomeFinEtudeDto.setId(signExist.getId());
				}
				signatureDiplomeFinEtudeService
						.insertOrUpdate(signatureDiplomeFinEtudeDto);
				signatureDiplomeFinEtudeDto.setAttestation(true);
				signatureDiplomeFinEtudeDto.setId(null);
				signExist = signatureDiplomeFinEtudeService
						.findUnique(signatureDiplomeFinEtudeDto
								.getNcSignatureDiplomeId(), diplomeFinEtudeDto
								.getId(), true);
				if (signExist != null) {
					signatureDiplomeFinEtudeDto.setId(signExist.getId());
				}
				signatureDiplomeFinEtudeService
						.insertOrUpdate(signatureDiplomeFinEtudeDto);

			}
		}
	}

	/**
	 * [DeliberationBean.generateIdentify] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2015 08:45:11
	 * @param prefix
	 * @return
	 */
	public String generateIdentify(String prefix) {
		try {
			if (prefix == null || prefix.isEmpty()) {
				return null;
			}
			String order = refParametreEtabService
					.findValueOfKey(UtilConstants.CODIFICATION_FVE_DIPLOME_ORDER_LENGTH_KEY);
			if (order == null) {
				order = "6"; // default value
			}
			int orderLength = Integer.parseInt(order);
			return diplomeFinEtudeService.generateIdentify(prefix, orderLength);
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * [DeliberationBean.annulerColture] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 11:18:09
	 */
	public void annulerColture() {
		try {
			if (situationEnregistre == null || situationEnregistre.getId() == 0) {
				Utility.showErrorMessage(bilanBundle
						.getString("deliberation_erreur_situation_enregistre_inexistante"));
				return;
			}
			deliberationSessionDto.setDateCloture(null);
			deliberationSessionDto.setSituationId(situationEnregistre.getId());
			deliberationSessionService.insertOrUpdate(deliberationSessionDto);
			// removeBilanSession();
			selectRow();
			Utility.showSuccessSaveMessage();
			clotured = false;
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * [DeliberationBean.saveBilanPeriodique] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 17:26:34
	 */
	public boolean saveBilanSession() {
		try {
			// if (deliberationSessionDto.getSituationId() != null
			// &&
			// deliberationSessionDto.getSituationId().equals(situationEnregistre.getId()))
			// {
			// return true;
			// }
			if (deliberationSessionDto.getId() == null
					|| deliberationSessionDto.getId() == 0) {
				Utility.showErrorMessage(bilanBundle
						.getString("deliberation_erreur_deliberation_vide"));
				return false;
			}

			if (deliberation != null && bilanSessionList != null) {
				for (BilanSessionDto bs : bilanSessionList) {
					// String mg = null;
					// LinkedHashMap<String, String> notes = null;
					// for (DeliberationModel model : deliberation) {
					// LinkedHashMap<String, String> map = model.getLine();
					// if (map != null) {
					// String mat = map.get("Matricule");
					//
					// if (mat != null
					// && bs.getMatriculeEtudiant() != null
					// && bs.getMatriculeEtudiant().equals(mat)) {
					// mg = map.get("MG");
					// notes = map;
					// break;
					// }
					// }
					// if (notes == null) {
					// break;
					// }
					BilanSessionDto _bs = bilanSessionService
							.findUniqueSession(deliberationSessionDto.getId(),
									bs.getDossierInscriptionAdministrativeId());
					if (_bs != null) {
						bs.setId(_bs.getId());
						updateBilanUe(_bs, bs);
					}
					// _bs.setMoyenne(new Double(mg));
					bs.setPeriodeId(searchPlanningBean.getPeriodeId());
					bs.setOofId(searchPlanningBean.getOofId());
					bs.setType(UtilConstants.BILAN_TYPE_SESSION);
					bs.setDeliberationSessionId(deliberationSessionDto.getId());
					// if (deliberationSessionDto.getBilanPeriode()) {
					// _bs.setType(UtilConstants.BILAN_TYPE_PERIODE);
					// } else {
					// _bs.setType(UtilConstants.BILAN_TYPE_SESSION);
					// }

					// updateBilanUe(_bs, notes, _bs.getMoyenne());
					bilanSessionService.insertOrUpdate(bs);
					System.out.println(bs);
					// bs = bilanSessionService.findById(bs.getId());

					// if (deliberationSessionDto.getBilanPeriode()) {
					// bs.setType(UtilConstants.BILAN_TYPE_PERIODE);
					// } else {
					// bs.setType(UtilConstants.BILAN_TYPE_SESSION);
					// }
					// bs.setType(UtilConstants.BILAN_TYPE_SESSION);
					// bs.setDeliberationSessionId(deliberationSessionDto
					// .getId());
					// //saveBilanUe(bs, notes, bs.getMoyenne());
					// bs.setId(0);
					// bs.setDeliberationSessionId(deliberationSessionDto
					// .getId());
					// bilanSessionService.insertOrUpdate(bs);
					// bs = bilanSessionService.findById(bs.getId());
				}

				// }
				// }
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * [DeliberationBean.saveBilanUe] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 10:17:54
	 * @param bilanSessionDto
	 */
	private void saveBilanUe(BilanSessionDto bilanSessionDto,
			LinkedHashMap<String, String> map, double moyenne) {
		double totalCredit = 0.0;
		double totalCreditAquis = 0.0;
		if (ueList != null) {
			List<BilanUeDto> bilanUeListDto = new ArrayList<BilanUeDto>();
			for (RepartitionUeDto _ue : ueList) {
				BilanUeDto bilanUeDto = new BilanUeDto();
				// bilanUeDto.setBilanSessionId(bilanSessionDto.getId());
				bilanUeDto.setRepartitionUeId(_ue.getId());
				bilanUeDto.setCoefficient(_ue.getCoefficient());
				bilanUeDto.setCredit(_ue.getUniteEnseignementCredits());
				if (map.get(_ue.getUniteEnseignementCode()) == null) {
					// bilanUeDto.setMoyenne(0.0);
					continue;
				}
				bilanUeDto.setMoyenne(new Double(map.get(_ue
						.getUniteEnseignementCode())));
				bilanUeDto.setCreditObtenu(0.0);
				bilanUeDto.setCreditAcquis(0.0);
				totalCredit = totalCredit + _ue.getUniteEnseignementCredits();
				if (bilanUeDto.getMoyenne() >= _ue.getNoteObtension()) {
					bilanUeDto.setCreditObtenu(_ue
							.getUniteEnseignementCredits());
					totalCreditAquis = totalCreditAquis
							+ _ue.getUniteEnseignementCredits();
				} else if (moyenne >= 10) {
					bilanUeDto.setCreditAcquis(_ue
							.getUniteEnseignementCredits());
					totalCreditAquis = totalCreditAquis
							+ _ue.getUniteEnseignementCredits();
				}
				if (bilanUeDto.getBilanSessionId() != null) {
					BilanUeDto _bilanUeDto = bilanUeService.findUniqueSession(
							bilanUeDto.getBilanSessionId(),
							bilanUeDto.getRepartitionUeId());
					if (_bilanUeDto != null) {
						bilanUeDto.setId(_bilanUeDto.getId());
					}
				}
				bilanUeListDto.add(bilanUeDto);
				// bilanUeService.insertOrUpdate(bilanUeDto);
				if (mcList != null) {
					List<BilanMcDto> bilanMcListDto = new ArrayList<BilanMcDto>();
					for (RattachementMcDto rmc : mcList) {
						if (map.get(rmc.getMcCode()) == null) {
							continue;
						}
						if (rmc.getUeId().equals(_ue.getUniteEnseignementId())) {
							BilanMcDto __bilanMcDto = new BilanMcDto();
							saveBilanMc(bilanUeDto, __bilanMcDto, rmc, map,
									moyenne);
							if (__bilanMcDto != null) {
								bilanMcListDto.add(__bilanMcDto);
							}
						}

					}
					bilanUeDto.setBilanMcs(bilanMcListDto);
				}
			}
			bilanSessionDto.setBilanUes(bilanUeListDto);
		}
		bilanSessionDto.setCreditObtenu(totalCreditAquis);
		bilanSessionDto.setCredit(totalCredit);
		if (moyenne >= 10) {
			bilanSessionDto.setCreditAcquis(totalCredit);
		}
	}

	/**
	 * [DeliberationBean.updateBilanUe] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 nov. 2014 14:36:37
	 * @param bilanSessionDto
	 * @param map
	 */
	private void updateBilanUe(BilanSessionDto bilanSessionDto,
			LinkedHashMap<String, String> map, double moyenne) {
		List<BilanUeDto> bilanUeListDto = bilanSessionDto.getBilanUes();
		if (bilanUeListDto == null) {
			bilanUeListDto = new ArrayList<BilanUeDto>();
		}
		double totalCredit = 0.0;
		double totalCreditAquis = 0.0;
		if (ueList != null) {

			for (RepartitionUeDto _ue : ueList) {

				if (map.get(_ue.getUniteEnseignementCode()) == null) {
					continue;
				}
				BilanUeDto bilanUeDto = new BilanUeDto();
				boolean bilanUeExist = false;
				for (BilanUeDto item : bilanUeListDto) {
					if (item.getRepartitionUeId().equals(_ue.getId())) {
						bilanUeDto = item;
						bilanUeExist = true;
						break;
					}
				}
				bilanUeDto.setBilanSessionId(bilanSessionDto.getId());
				bilanUeDto.setRepartitionUeId(_ue.getId());
				bilanUeDto.setCoefficient(_ue.getCoefficient());
				bilanUeDto.setCredit(_ue.getUniteEnseignementCredits());
				bilanUeDto.setMoyenne(new Double(map.get(_ue
						.getUniteEnseignementCode())));
				bilanUeDto.setCreditObtenu(0.0);
				bilanUeDto.setCreditAcquis(0.0);
				totalCredit = totalCredit + _ue.getUniteEnseignementCredits();
				if (bilanUeDto.getMoyenne() >= _ue.getNoteObtension()) {
					bilanUeDto.setCreditObtenu(_ue
							.getUniteEnseignementCredits());
					totalCreditAquis = totalCreditAquis
							+ _ue.getUniteEnseignementCredits();
				}
				if (bilanUeDto.getMoyenne() >= _ue.getNoteObtension()) {
					bilanUeDto.setCreditObtenu(_ue
							.getUniteEnseignementCredits());
				} else if (moyenne >= 10) {
					bilanUeDto.setCreditAcquis(_ue
							.getUniteEnseignementCredits());
					totalCreditAquis = totalCreditAquis
							+ _ue.getUniteEnseignementCredits();
				} else {
					bilanUeDto.setCreditObtenu(0.0);
				}
				if (bilanUeDto.getBilanSessionId() != null) {
					BilanUeDto _bilanUeDto = bilanUeService.findUniqueSession(
							bilanUeDto.getBilanSessionId(),
							bilanUeDto.getRepartitionUeId());
					if (_bilanUeDto != null) {
						bilanUeDto.setId(_bilanUeDto.getId());
					}
				}
				if (!bilanUeExist) {
					bilanUeListDto.add(bilanUeDto);
				}
				// bilanUeService.insertOrUpdate(bilanUeDto);
				if (mcList != null) {
					List<BilanMcDto> bilanMcListDto = bilanUeDto.getBilanMcs();
					if (bilanMcListDto == null) {
						bilanMcListDto = new ArrayList<BilanMcDto>();
					}
					for (RattachementMcDto rmc : mcList) {
						if (map.get(rmc.getMcCode()) == null) {
							continue;
						}
						if (rmc.getUeId().equals(_ue.getUniteEnseignementId())) {
							boolean bilanMcExist = false;
							for (BilanMcDto _item : bilanMcListDto) {
								if (_item.getRattachementMcId().equals(
										rmc.getId())) {
									bilanMcExist = true;
									saveBilanMc(bilanUeDto, _item, rmc, map,
											moyenne);
								}
							}
							if (!bilanMcExist) {
								BilanMcDto __bilanMcDto = new BilanMcDto();
								saveBilanMc(bilanUeDto, __bilanMcDto, rmc, map,
										moyenne);
								if (__bilanMcDto != null) {
									bilanMcListDto.add(__bilanMcDto);
								}
							}
						}

					}

				}
			}
			bilanSessionDto.setCreditObtenu(totalCreditAquis);
			bilanSessionDto.setCredit(totalCredit);
			if (moyenne >= 10) {
				bilanSessionDto.setCreditAcquis(totalCredit);
			}
		}
	}

	/**
	 * [DeliberationBean.updateBilanUe] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 janv. 2015 15:06:19
	 * @param oldBilanSessionDto
	 * @param newBilanSessionDto
	 */
	private void updateBilanUe(BilanSessionDto oldBilanSessionDto,
			BilanSessionDto newBilanSessionDto) {
		List<BilanUeDto> oldBilanUeListDto = oldBilanSessionDto.getBilanUes();
		List<BilanUeDto> newBilanUeListDto = newBilanSessionDto.getBilanUes();
		if (oldBilanUeListDto == null || newBilanUeListDto == null) {
			return;
		}
		for (BilanUeDto _newUe : newBilanUeListDto) {
			for (BilanUeDto _oldUe : oldBilanUeListDto) {
				if (_oldUe.getRepartitionUeId().equals(
						_newUe.getRepartitionUeId())) {
					_newUe.setId(_oldUe.getId());
					_newUe.setBilanSessionId(newBilanSessionDto.getId());
					List<BilanMcDto> oldBilanMcListDto = _oldUe.getBilanMcs();
					List<BilanMcDto> newBilanMcListDto = _newUe.getBilanMcs();
					if (_oldUe != null && newBilanMcListDto != null) {
						for (BilanMcDto _newMc : newBilanMcListDto) {
							for (BilanMcDto _oldMc : oldBilanMcListDto) {
								if (_newMc.getRattachementMcId().equals(
										_oldMc.getRattachementMcId())) {
									_newMc.setId(_oldMc.getId());
									_newMc.setBilanUeId(_oldUe.getId());
								}
							}
						}
					}
				}

			}
		}

	}

	/**
	 * [DeliberationBean.saveBilanMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 10:42:40
	 * @param bilanUeDto
	 * @param rmc
	 * @param map
	 */
	private void saveBilanMc(BilanUeDto bilanUeDto, BilanMcDto bilanMcDto,
			RattachementMcDto rmc, LinkedHashMap<String, String> map,
			double moyenne) {
		bilanMcDto.setBilanUeId(bilanUeDto.getId());
		bilanMcDto.setRattachementMcId(rmc.getId());
		bilanMcDto.setCoefficient(rmc.getCoefficient());
		bilanMcDto.setCredit(rmc.getCredit());
		fillCoefficientExamen(bilanMcDto, rmc);
		bilanMcDto.setCoefficientControleContinu(rmc
				.getCoefficientControleContinu());
		bilanMcDto.setCoefficientExamen(rmc.getCoefficientExamen());
		String moyenneGenerale = map.get(rmc.getMcCode());
		String noteJury = map.get(rmc.getMcCode() + "_NoteJury");
		String noteExamen = map.get(rmc.getMcCode() + "_NoteExamen");
		String moyenneCC = map.get(rmc.getMcCode() + "_NoteCC");

		if (moyenneGenerale != null) {
			bilanMcDto.setMoyenneGenerale(new Double(moyenneGenerale));

		} else {
			bilanMcDto.setMoyenneGenerale(0.0);
		}
		if (noteJury != null) {
			bilanMcDto.setNoteJury(new Double(noteJury));

		} else {
			bilanMcDto.setNoteJury(0.0);
		}
		if (noteExamen != null) {
			bilanMcDto.setNoteExamen(new Double(noteExamen));
		} else {
			bilanMcDto.setNoteExamen(0.0);
		}
		if (moyenneCC != null) {
			bilanMcDto.setMoyenneControleContinu(new Double(moyenneCC));
		} else {
			bilanMcDto.setMoyenneControleContinu(null);
		}
		if (bilanMcDto.getMoyenneGenerale() >= rmc.getNoteObtension()) {
			bilanMcDto.setCreditObtenu(rmc.getCredit());
		} else if (moyenne >= 10) {
			bilanMcDto.setCreditAcquis(bilanMcDto.getCredit());
		} else {
			bilanMcDto.setCreditObtenu(0.0);
			bilanMcDto.setDette(true);
		}

	}

	/**
	 * [DeliberationBean.fillCoefficientExamen] Method
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 17:04:06
	 * @param bilanMcDto
	 * @param rmc
	 */
	private void fillCoefficientExamen(BilanMcDto bilanMcDto,
			RattachementMcDto rmc) {
		if (rmc != null && rmc.getId() != 0
				&& searchPlanningBean.getOofId() != null
				&& searchPlanningBean.getPeriodeId() != null
				&& bilanMcDto.getCoefficientExamen() == null) {
			CoefficientExamenDto _coefficientExamenDto = utilBean
					.findCoefficientExamen(searchPlanningBean.getOofId(),
							searchPlanningBean.getPeriodeId(), rmc.getId());
			if (_coefficientExamenDto != null) {
				bilanMcDto.setCoefficientControleContinu(_coefficientExamenDto
						.getCoefficientControleContinu());
				bilanMcDto.setCoefficientExamen(_coefficientExamenDto
						.getCoefficientExamen());
				bilanMcDto
						.setCoefficientControleIntermediaire(_coefficientExamenDto
								.getCoefficientControleIntermediaire());
			}
		}
	}

	/**
	 * [DeliberationBean.removeBilanSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 11:26:09
	 */
	public void removeBilanSession() {
		List<BilanSessionDto> list = bilanSessionService
				.findByDeliberation(deliberationSessionDto.getId());
		if (list != null) {
			for (BilanSessionDto bs : list) {
				bilanSessionService.remove(bs);
			}

		}
		deliberationSessionDto.setSituationId(situationCree.getId());
		deliberationSessionService.insertOrUpdate(deliberationSessionDto);
	}

	/**
	 * [DeliberationBean.mentionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 oct. 2014 13:43:59
	 * @return the mentionList
	 */
	public List<MentionDto> getMentionList() {
		return mentionList;
	}

	/**
	 * [DeliberationBean.mentionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 oct. 2014 13:43:59
	 * @param mentionList
	 *            the mentionList to set
	 */
	public void setMentionList(List<MentionDto> mentionList) {
		this.mentionList = mentionList;
	}

	/**
	 * [DeliberationBean.getMention] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 oct. 2014 13:57:17
	 * @param note
	 * @return
	 */
	public String getMention(Double note) {
		if (mentionList != null) {
			for (MentionDto mention : mentionList) {
				if (note != null && mention.getNoteMin() <= note
						&& mention.getNoteMax() > note) {
					return mention.getNcMentionCode();
				}
			}
		}
		return "-";
	}

	/**
	 * [DeliberationBean.cloture] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 09:01:38
	 * @return the cloture
	 */
	public boolean isCloture() {
		return cloture;
	}

	/**
	 * [DeliberationBean.cloture] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 09:01:38
	 * @param cloture
	 *            the cloture to set
	 */
	public void setCloture(boolean cloture) {
		this.cloture = cloture;
	}

	/**
	 * [DeliberationBean.situationClose] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 09:25:29
	 * @return the situationClose
	 */
	public SituationEntiteDto getSituationClose() {
		return situationClose;
	}

	/**
	 * [DeliberationBean.situationClose] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 09:25:29
	 * @param situationClose
	 *            the situationClose to set
	 */
	public void setSituationClose(SituationEntiteDto situationClose) {
		this.situationClose = situationClose;
	}

	/**
	 * [DeliberationBean.clotured] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 09:26:23
	 * @return the clotured
	 */
	public boolean isClotured() {
		return clotured;
	}

	/**
	 * [DeliberationBean.clotured] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 09:26:23
	 * @param clotured
	 *            the clotured to set
	 */
	public void setClotured(boolean clotured) {
		this.clotured = clotured;
	}

	/**
	 * [DeliberationBean.bilanSessionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 09:41:07
	 * @return the bilanSessionList
	 */
	public List<BilanSessionDto> getBilanSessionList() {
		return bilanSessionList;
	}

	/**
	 * [DeliberationBean.bilanSessionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 09:41:07
	 * @param bilanSessionList
	 *            the bilanSessionList to set
	 */
	public void setBilanSessionList(List<BilanSessionDto> bilanSessionList) {
		this.bilanSessionList = bilanSessionList;
	}

	/**
	 * [DeliberationBean.bilanSessionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 09:52:34
	 * @return the bilanSessionService
	 */
	public BilanSessionService getBilanSessionService() {
		return bilanSessionService;
	}

	/**
	 * [DeliberationBean.bilanSessionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 09:52:34
	 * @param bilanSessionService
	 *            the bilanSessionService to set
	 */
	public void setBilanSessionService(BilanSessionService bilanSessionService) {
		this.bilanSessionService = bilanSessionService;
	}

	/**
	 * [DeliberationBean.bilanUeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 10:29:46
	 * @return the bilanUeService
	 */
	public BilanUeService getBilanUeService() {
		return bilanUeService;
	}

	/**
	 * [DeliberationBean.bilanUeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 10:29:46
	 * @param bilanUeService
	 *            the bilanUeService to set
	 */
	public void setBilanUeService(BilanUeService bilanUeService) {
		this.bilanUeService = bilanUeService;
	}

	/**
	 * [DeliberationBean.bilanMcService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 10:29:46
	 * @return the bilanMcService
	 */
	public BilanMcService getBilanMcService() {
		return bilanMcService;
	}

	/**
	 * [DeliberationBean.bilanMcService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 10:29:46
	 * @param bilanMcService
	 *            the bilanMcService to set
	 */
	public void setBilanMcService(BilanMcService bilanMcService) {
		this.bilanMcService = bilanMcService;
	}

	/**
	 * [DeliberationBean.loadInforBulle] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 08:36:42
	 * @param code
	 * @return
	 */
	public String loadInforBulle(String code) {
		if (notes != null) {
			for (InscriptionExamenDto _note : notes) {
				if (_note.getUeCode() != null && _note.getUeCode().equals(code)) {
					return _note.getUeLibelleFr();
				}
				if (_note.getMcCode() != null && _note.getMcCode().equals(code)) {
					return _note.getMcLibelleFr();
				}

			}
		}
		return "";
	}

	/**
	 * [DeliberationBean.goToGenerateBilanPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 09:11:55
	 * @return
	 */
	public String goToGenerateBilanPeriode() {
		return "/pages/cursus/notation/BilanPeriode.xhtml";
	}

	/**
	 * [DeliberationBean.goToGenerateBilanAnnuel] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 09:11:57
	 * @return
	 */
	public String goToGenerateBilanAnnuel() {
		return "/pages/cursus/notation/BilanAnnuel.xhtml";
	}

	/**
	 * [DeliberationBean.niveauId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 09:56:25
	 * @return the niveauId
	 */
	public Integer getNiveauId() {
		return niveauId;
	}

	/**
	 * [DeliberationBean.niveauId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 09:56:25
	 * @param niveauId
	 *            the niveauId to set
	 */
	public void setNiveauId(Integer niveauId) {
		this.niveauId = niveauId;
	}

	/**
	 * [DeliberationBean.periodeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 09:58:11
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [DeliberationBean.periodeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 09:58:11
	 * @param periodeService
	 *            the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [DeliberationBean.bilanAnnuelAllowed] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 15:36:36
	 * @return the bilanAnnuelAllowed
	 */
	public boolean getBilanAnnuelAllowed() {
		return bilanAnnuelAllowed;
	}

	/**
	 * [DeliberationBean.bilanAnnuelAllowed] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 15:36:36
	 * @param bilanAnnuelAllowed
	 *            the bilanAnnuelAllowed to set
	 */
	public void setBilanAnnuelAllowed(boolean bilanAnnuelAllowed) {
		this.bilanAnnuelAllowed = bilanAnnuelAllowed;
	}

	/**
	 * [DeliberationBean.situationEnregistre] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 dÃ©c. 2014 11:46:05
	 * @return the situationEnregistre
	 */
	public SituationEntiteDto getSituationEnregistre() {
		return situationEnregistre;
	}

	/**
	 * [DeliberationBean.situationEnregistre] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 dÃ©c. 2014 11:46:06
	 * @param situationEnregistre
	 *            the situationEnregistre to set
	 */
	public void setSituationEnregistre(SituationEntiteDto situationEnregistre) {
		this.situationEnregistre = situationEnregistre;
	}

	/**
	 * [DeliberationBean.planningSessionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 dÃ©c. 2014 09:01:19
	 * @return the planningSessionDto
	 */
	public PlanningSessionDto getPlanningSessionDto() {
		return planningSessionDto;
	}

	/**
	 * [DeliberationBean.planningSessionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 dÃ©c. 2014 09:01:19
	 * @param planningSessionDto
	 *            the planningSessionDto to set
	 */
	public void setPlanningSessionDto(PlanningSessionDto planningSessionDto) {
		this.planningSessionDto = planningSessionDto;
	}

	/**
	 * [DeliberationBean.coefficientExamenService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 17:05:00
	 * @return the coefficientExamenService
	 */
	public CoefficientExamenService getCoefficientExamenService() {
		return coefficientExamenService;
	}

	/**
	 * [DeliberationBean.coefficientExamenService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 17:05:00
	 * @param coefficientExamenService
	 *            the coefficientExamenService to set
	 */
	public void setCoefficientExamenService(
			CoefficientExamenService coefficientExamenService) {
		this.coefficientExamenService = coefficientExamenService;
	}

	/**
	 * [DeliberationBean.rattachementMcDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 11:12:53
	 * @return the rattachementMcDtos
	 */
	public List<RattachementMcDto> getRattachementMcDtos() {
		return rattachementMcDtos;
	}

	/**
	 * [DeliberationBean.rattachementMcDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 11:12:53
	 * @param rattachementMcDtos
	 *            the rattachementMcDtos to set
	 */
	public void setRattachementMcDtos(List<RattachementMcDto> rattachementMcDtos) {
		this.rattachementMcDtos = rattachementMcDtos;
	}

	/**
	 * [DeliberationBean.bilanControleContinuService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 11:14:29
	 * @return the bilanControleContinuService
	 */
	public BilanControleContinuService getBilanControleContinuService() {
		return bilanControleContinuService;
	}

	/**
	 * [DeliberationBean.bilanControleContinuService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 11:14:29
	 * @param bilanControleContinuService
	 *            the bilanControleContinuService to set
	 */
	public void setBilanControleContinuService(
			BilanControleContinuService bilanControleContinuService) {
		this.bilanControleContinuService = bilanControleContinuService;
	}

	/**
	 * [DeliberationBean.notesControleContinu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 11:56:08
	 * @return the notesControleContinu
	 */
	public List<BilanControleContinuDto> getNotesControleContinu() {
		return notesControleContinu;
	}

	/**
	 * [DeliberationBean.notesControleContinu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 11:56:08
	 * @param notesControleContinu
	 *            the notesControleContinu to set
	 */
	public void setNotesControleContinu(
			List<BilanControleContinuDto> notesControleContinu) {
		this.notesControleContinu = notesControleContinu;
	}

	/**
	 * [DeliberationBean.notesControleIntermeidiare] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 11:56:08
	 * @return the notesControleIntermeidiare
	 */
	public List<InscriptionExamenDto> getNotesControleIntermeidiare() {
		return notesControleIntermeidiare;
	}

	/**
	 * [DeliberationBean.notesControleIntermeidiare] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 11:56:08
	 * @param notesControleIntermeidiare
	 *            the notesControleIntermeidiare to set
	 */
	public void setNotesControleIntermeidiare(
			List<InscriptionExamenDto> notesControleIntermeidiare) {
		this.notesControleIntermeidiare = notesControleIntermeidiare;
	}

	/**
	 * [DeliberationBean.examenNoteSessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 14:03:17
	 * @return the examenNoteSessionBean
	 */
	public ExamenNoteSessionBean getExamenNoteSessionBean() {
		return examenNoteSessionBean;
	}

	/**
	 * [DeliberationBean.examenNoteSessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 14:03:17
	 * @param examenNoteSessionBean
	 *            the examenNoteSessionBean to set
	 */
	public void setExamenNoteSessionBean(
			ExamenNoteSessionBean examenNoteSessionBean) {
		this.examenNoteSessionBean = examenNoteSessionBean;
	}

	/**
	 * [DeliberationBean.bilanSessionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 14:27:22
	 * @return the bilanSessionDto
	 */
	public BilanSessionDto getBilanSessionDto() {
		return bilanSessionDto;
	}

	/**
	 * [DeliberationBean.bilanSessionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 14:27:22
	 * @param bilanSessionDto
	 *            the bilanSessionDto to set
	 */
	public void setBilanSessionDto(BilanSessionDto bilanSessionDto) {
		this.bilanSessionDto = bilanSessionDto;
	}

	/**
	 * [DeliberationBean.deliberation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 janv. 2015 10:13:52
	 * @return the deliberation
	 */
	public List<DeliberationModel> getDeliberation() {
		return deliberation;
	}

	/**
	 * [DeliberationBean.deliberation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 janv. 2015 10:13:52
	 * @param deliberation
	 *            the deliberation to set
	 */
	public void setDeliberation(List<DeliberationModel> deliberation) {
		this.deliberation = deliberation;
	}

	/**
	 * [DeliberationBean.searchPlanningBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 14:58:13
	 * @return the searchPlanningBean
	 */
	public SearchPlanningBean getSearchPlanningBean() {
		return searchPlanningBean;
	}

	/**
	 * [DeliberationBean.searchPlanningBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 14:58:13
	 * @param searchPlanningBean
	 *            the searchPlanningBean to set
	 */
	public void setSearchPlanningBean(SearchPlanningBean searchPlanningBean) {
		this.searchPlanningBean = searchPlanningBean;
	}

	/**
	 * [DeliberationBean.noteEliminatoireService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 15:21:48
	 * @return the noteEliminatoireService
	 */
	public NoteEliminatoireService getNoteEliminatoireService() {
		return noteEliminatoireService;
	}

	/**
	 * [DeliberationBean.noteEliminatoireService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 15:21:48
	 * @param noteEliminatoireService
	 *            the noteEliminatoireService to set
	 */
	public void setNoteEliminatoireService(
			NoteEliminatoireService noteEliminatoireService) {
		this.noteEliminatoireService = noteEliminatoireService;
	}

	/**
	 * [DeliberationBean.noteEliminatoireDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 15:21:48
	 * @return the noteEliminatoireDtos
	 */
	public List<NoteEliminatoireDto> getNoteEliminatoireDtos() {
		return noteEliminatoireDtos;
	}

	/**
	 * [DeliberationBean.noteEliminatoireDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 15:21:48
	 * @param noteEliminatoireDtos
	 *            the noteEliminatoireDtos to set
	 */
	public void setNoteEliminatoireDtos(
			List<NoteEliminatoireDto> noteEliminatoireDtos) {
		this.noteEliminatoireDtos = noteEliminatoireDtos;
	}

	/**
	 * [DeliberationBean.diplomeFinEtudeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 13:52:08
	 * @return the diplomeFinEtudeService
	 */
	public DiplomeFinEtudeService getDiplomeFinEtudeService() {
		return diplomeFinEtudeService;
	}

	/**
	 * [DeliberationBean.diplomeFinEtudeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 13:52:08
	 * @param diplomeFinEtudeService
	 *            the diplomeFinEtudeService to set
	 */
	public void setDiplomeFinEtudeService(
			DiplomeFinEtudeService diplomeFinEtudeService) {
		this.diplomeFinEtudeService = diplomeFinEtudeService;
	}

	/**
	 * [DeliberationBean.attestationFinEtudeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 13:52:08
	 * @return the attestationFinEtudeService
	 */
	public AttestationFinEtudeService getAttestationFinEtudeService() {
		return attestationFinEtudeService;
	}

	/**
	 * [DeliberationBean.attestationFinEtudeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 févr. 2015 13:52:08
	 * @param attestationFinEtudeService
	 *            the attestationFinEtudeService to set
	 */
	public void setAttestationFinEtudeService(
			AttestationFinEtudeService attestationFinEtudeService) {
		this.attestationFinEtudeService = attestationFinEtudeService;
	}

	/**
	 * [DeliberationBean.refParametreEtabService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2015 08:34:31
	 * @return the refParametreEtabService
	 */
	public RefParametreEtabService getRefParametreEtabService() {
		return refParametreEtabService;
	}

	/**
	 * [DeliberationBean.refParametreEtabService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2015 08:34:31
	 * @param refParametreEtabService
	 *            the refParametreEtabService to set
	 */
	public void setRefParametreEtabService(
			RefParametreEtabService refParametreEtabService) {
		this.refParametreEtabService = refParametreEtabService;
	}

	/**
	 * [DeliberationBean.signatureDiplomeFinEtudeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 15:45:12
	 * @return the signatureDiplomeFinEtudeService
	 */
	public SignatureDiplomeFinEtudeService getSignatureDiplomeFinEtudeService() {
		return signatureDiplomeFinEtudeService;
	}

	/**
	 * [DeliberationBean.signatureDiplomeFinEtudeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 15:45:12
	 * @param signatureDiplomeFinEtudeService
	 *            the signatureDiplomeFinEtudeService to set
	 */
	public void setSignatureDiplomeFinEtudeService(
			SignatureDiplomeFinEtudeService signatureDiplomeFinEtudeService) {
		this.signatureDiplomeFinEtudeService = signatureDiplomeFinEtudeService;
	}

}
