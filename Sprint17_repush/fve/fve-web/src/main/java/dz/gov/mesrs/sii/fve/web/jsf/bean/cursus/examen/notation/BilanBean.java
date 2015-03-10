/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation.BilanBean.java] 
 * @author MAKERRI Sofiane on : 25 oct. 2014  14:47:03
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.business.util.CrossTab;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.MentionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanUeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.DeliberationSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.InscriptionExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RepartitionUeDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanMcService;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanUeService;
import dz.gov.mesrs.sii.fve.business.service.examen.DeliberationSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.InscriptionExamenService;
import dz.gov.mesrs.sii.fve.business.service.impression.ImpressionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RepartitionUeService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.impression.PrintMgrBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * @author MAKERRI Sofiane on : 28 déc. 2014 08:19:29
 */
@ManagedBean(name = "bilanBean")
@ViewScoped
public class BilanBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 14:47:21
	 */
	private static final long serialVersionUID = 1L;
	private Integer anneeAcademiqueId;
	private Integer oofId;
	private Integer niveauId;
	private Integer periodeId;
	private boolean editMode;
	private boolean showDetail;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> ofList;
	private List<SelectItem> niveauList;
	private List<SelectItem> periodeList;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{deliberationBean}")
	private DeliberationBean deliberationBean;
	@ManagedProperty("#{bilanSessionService}")
	private BilanSessionService bilanSessionService;
	@ManagedProperty("#{deliberationSessionService}")
	private DeliberationSessionService deliberationSessionService;
	@ManagedProperty("#{situationService}")
	private SituationService situationService;
	@ManagedProperty("#{printMgrBean}")
	private PrintMgrBean printMgrBean;
	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{periodeService}")
	private PeriodeService periodeService;
	@ManagedProperty("#{niveauService}")
	private NiveauService niveauService;
	@ManagedProperty("#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{impressionService}")
	private ImpressionService impressionService;
	@ManagedProperty("#{bilanUeService}")
	private BilanUeService bilanUeService;
	@ManagedProperty("#{bilanMcService}")
	private BilanMcService bilanMcService;
	@ManagedProperty("#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;
	@ManagedProperty("#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;
	@ManagedProperty(value = "#{repartitionUeService}")
	private RepartitionUeService repartitionUeService;
	@ManagedProperty(value = "#{rattachementMcService}")
	private RattachementMcService rattachementMcService;
	@ManagedProperty("#{inscriptionExamenService}")
	private InscriptionExamenService inscriptionExamenService;
	private List<BilanSessionDto> bilanAnnuelList;
	private List<BilanSessionDto> filtredBilanAnnuelList;
	private List<BilanSessionDto> bilanPeriodeList;
	private List<BilanSessionDto> filtredBilanPeriodeList;
	// private List<BilanSessionDto> bilanFinalList;
	private ResourceBundle bilanBundle;
	private boolean annuel;
	private SituationEntiteDto situationClose;
	private List<DeliberationSessionDto> deliberationList;
	private List<BilanModel> bilanModelList;
	private List<BilanSessionDto> releveNoteList;
	// private List<ReleveNoteModel> releveNoteCollection;
	private List<BilanUeDto> bilanUeList;
	private List<BilanMcDto> bilanMcList;
	private List<MentionDto> mentionList;
	private boolean bilanPeriodeClotured;
	private boolean bilanAnnuelClotured;
	private boolean deliberationClotured;
	private List<SelectItem> ncTypeDecisonList;
	private List<NomenclatureDto> ncTypeDecisonAnnuelList;
	private List<NomenclatureDto> ncTypeDecisonPeriodeList;
	private NomenclatureDto decisionAdmisAnnuel;
	private Long deliberationId;
	private boolean deliberationClosed;
	private List<CrossTab> crossCollection;
	private SituationEntiteDto situationCree;
	private BilanSessionDto selectedBilan;
	private List<BilanSessionDto> selectedBilanList;
	private boolean releve;
	private String fileNamePeriode;
	private String fileNameAnnuel;
	private String fileNameReleve;
	private String ofLibelleLongFr;
	private String periodeLibelleLongFr;
	private String niveauLibelleLongFr;
	private String cycleLibelleLongFr;
	private String niveauCode;
	private String anneeCode;
	private List<RepartitionUeDto> ueList;
	private List<RattachementMcDto> mcList;
	private List<NoteEtudiantModel> noteEtudiantModel;
	private List<InscriptionExamenDto> etudiants;
	private List<InscriptionExamenDto> notes;
	private List<BilanSessionDto> bilanSessionList;
	private List<LinkedHashMap<String, String>> deliberation;
	private LinkedHashMap<String, String> line;
	private NiveauDto niveauDto;
	private NomenclatureDto ncDecisionPassageAvecDette;
	private List<DossierInscriptionAdministrativeDto> inscriptionAdministrativeList;

	/**
	 * [BilanBean.BilanBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 14:47:03
	 */
	public BilanBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bilanBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.BILAN_BUNDLE_MSG_NAME);
		editMode = true;
		String viewId = facesContext.getViewRoot().getViewId();
		annuel = false;
		releve = false;
		if (viewId != null && viewId.toLowerCase().contains("annuel")) {
			annuel = true;
		}
		if (viewId != null && viewId.toLowerCase().contains("releve")) {
			releve = true;
		}
		if (viewId != null && viewId.toLowerCase().contains("note")) {
			editMode = false;
		}
		if (viewId != null && viewId.toLowerCase().contains("consul")) {
			editMode = false;
		}

	}

	@PostConstruct
	public void init() {
		loadParameters();
		if (!editMode) {
			loadAnneeAcademique();
		}
		anneeAcademiqueId = sessionBean.getIdAnneeAcademique();
		loadOffreFormationOuverte();
		loadSituation();
		decisionAdmisAnnuel = new NomenclatureDto();
		ncDecisionPassageAvecDette = new NomenclatureDto();

	}

	/**
	 * [BilanBean.loadParameters] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 09:48:04
	 */
	public void loadParameters() {
		String _oofId = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("oofId");
		if (_oofId != null) {
			try {
				oofId = Integer.parseInt(_oofId);
			} catch (NumberFormatException e) {
				oofId = null;
			}

		}
		String _anneeAcademiqueId = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get("anneeAcademiqueId");
		if (_anneeAcademiqueId != null) {
			try {
				anneeAcademiqueId = Integer.parseInt(_anneeAcademiqueId);
			} catch (NumberFormatException e) {
				anneeAcademiqueId = null;
			}
		}
		String _periodeId = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("periodeId");
		if (_periodeId != null) {
			try {
				periodeId = Integer.parseInt(_periodeId);
			} catch (NumberFormatException e) {
				periodeId = null;
			}
		}
		String _niveauId = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("niveauId");
		if (_niveauId != null) {
			try {
				niveauId = Integer.parseInt(_niveauId);
			} catch (NumberFormatException e) {
				niveauId = null;
			}
		}
		if (periodeId != null) {
			loadPeriode();
		}
		if (niveauId != null) {
			loadNiveau();
		}
	}

	/**
	 * [BilanBean.loadSituation] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 17:26:04
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

	}

	/**
	 * [BilanBean.loadAnneeAcademique] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:02:55
	 */
	public void loadAnneeAcademique() {
		try {
			anneeAcademiqueList = utilBean.loadAnneeAcademique();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [BilanBean.anneeAcademiqueId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 14:59:52
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [BilanBean.loadMention] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 13:16:28
	 */
	private void loadMention() {
		if (mentionList == null || mentionList.isEmpty()) {
			mentionList = utilBean.loadAllMention(anneeAcademiqueId);
		}
	}

	/**
	 * [BilanBean.anneeAcademiqueId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 14:59:52
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [BilanBean.editMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 14:59:52
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [BilanBean.editMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 14:59:52
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * [BilanBean.anneeAcademiqueList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 14:59:52
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [BilanBean.anneeAcademiqueList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 14:59:52
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [BilanBean.anneeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:00:25
	 */
	public void anneeChanged() {
		loadOffreFormationOuverte();
		ofChanged();
	}

	/**
	 * [BilanBean.ofChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:01:45
	 */
	public void ofChanged() {
		if (annuel || releve) {
			loadNiveau();
			niveauChanged();
		} else {
			loadPeriode();
		}
	}

	/**
	 * [BilanBean.loadNiveau] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:09:19
	 */
	public void loadNiveau() {
		if (oofId != null) {
			niveauList = utilBean.loadNiveaux(oofId);
		}
	}

	/**
	 * [BilanBean.loadPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 09:37:32
	 */
	public void loadPeriode() {
		if (oofId != null) {
			periodeList = utilBean.loadPeriodeByNiveau(oofId);
		}
	}

	/**
	 * [BilanBean.loadPeriodeForReleve] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 11:16:34
	 */
	public void loadPeriodeForReleve() {
		if (niveauId != null) {
			periodeList = utilBean.loadPeriode(niveauId);
		}
	}

	/**
	 * [BilanBean.niveauChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:07:27
	 */
	public void niveauChanged() {
		showDetail = false;
		if (niveauId != null) {
			niveauDto = niveauService.findById(niveauId);
			findEtudiants();
			findBilanAnnuel();
		}
	}

	/**
	 * [BilanBean.niveauReleveChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 29 oct. 2014 10:09:32
	 */
	public void niveauReleveChanged() {
		if (niveauId != null) {
			// findBilanFinal();
			loadPeriodeForReleve();
		}

	}

	/**
	 * [BilanBean.periodeReleveChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 11:17:29
	 */
	public void periodeReleveChanged() {
		findRelevePeriode();
	}

	/**
	 * [BilanBean.periodeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 16:41:57
	 */
	public void periodeChanged() {
		if (periodeId != null) {
			findBilanPeriode();
		}
	}

	/**
	 * [BilanBean.loadOffreFormationOuverte] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:00:28
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
	 * [BilanBean.ofList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:00:53
	 * @return the ofList
	 */
	public List<SelectItem> getOfList() {
		return ofList;
	}

	/**
	 * [BilanBean.ofList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:00:53
	 * @param ofList
	 *            the ofList to set
	 */
	public void setOfList(List<SelectItem> ofList) {
		this.ofList = ofList;
	}

	/**
	 * [BilanBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:01:27
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [BilanBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:01:27
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [BilanBean.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:03:21
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [BilanBean.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:03:21
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [BilanBean.niveauList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:08:32
	 * @return the niveauList
	 */
	public List<SelectItem> getNiveauList() {
		return niveauList;
	}

	/**
	 * [BilanBean.niveauList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:08:32
	 * @param niveauList
	 *            the niveauList to set
	 */
	public void setNiveauList(List<SelectItem> niveauList) {
		this.niveauList = niveauList;
	}

	/**
	 * [BilanBean.oofId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:09:38
	 * @return the oofId
	 */
	public Integer getOofId() {
		return oofId;
	}

	/**
	 * [BilanBean.oofId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:09:38
	 * @param oofId
	 *            the oofId to set
	 */
	public void setOofId(Integer oofId) {
		this.oofId = oofId;
	}

	/**
	 * [BilanBean.niveauId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:10:15
	 * @return the niveauId
	 */
	public Integer getNiveauId() {
		return niveauId;
	}

	/**
	 * [BilanBean.niveauId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:10:15
	 * @param niveauId
	 *            the niveauId to set
	 */
	public void setNiveauId(Integer niveauId) {
		this.niveauId = niveauId;
	}

	/**
	 * [BilanBean.findBilan] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:11:41
	 */
	public void findBilanAnnuel() {
		try {
			showDetail = false;
			loadBilanPeriode();
			deliberationId = null;
			deliberationClotured = false;

			DeliberationSessionDto _deliberation = getDeliberationWithBilanAnnuel();
			if (_deliberation != null) {
				deliberationId = _deliberation.getId();
				periodeId = _deliberation.getPeriodeId();
				if (_deliberation.getSituationId() != null
						&& _deliberation.getSituationId().equals(
								situationClose.getId())) {
					deliberationClotured = true;
				}
			}
			if (deliberationId == null) {
				Utility.showErrorMessage(bilanBundle
						.getString("bilan_erreur_bilan_annuel_inexistant"));
				return;
			}
			if (_deliberation.getSituationId() != null
					&& situationClose.getId() != 0
					&& _deliberation.getSituationId().equals(
							situationClose.getId())) {
				/*****
				 * Bilan annuel clotur� : chargement directement � partir de la
				 * table
				 *****/
				deliberationClosed = true;
			}
			if (oofId != null && niveauId != null) {
				loadMention();
				loadDecisonJuryItem();
				loadDecisonJuryAnnuel();
				loadNcDecisionPassageDette();
				loadAdmisDecisonAnnuel();
				loadDecisionBilanAnnuel();
				loadBilanByPeriode();
				passage();
				showDetail = true;
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanBean.findEtudiants] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 déc. 2014 09:48:51
	 */
	public void findEtudiants() {
		if (oofId != null && anneeAcademiqueId != null) {
			DossierInscriptionAdministrativeDto dto = new DossierInscriptionAdministrativeDto();
			dto.setOuvertureOffreFormationId(oofId);
			dto.setAnneeAcademiqueId(anneeAcademiqueId);
			inscriptionAdministrativeList = dossierInscriptionAdministrativeService
					.findAdvanced(dto, false);
		}

	}

	/**
	 * [BilanBean.findBilanFinal] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 09:56:42
	 */
	public void findBilanFinal() {
		try {
			showDetail = false;
			if (oofId != null && niveauId != null) {
				// bilanFinalList = bilanSessionService.findBilanFinal(oofId,
				// niveauId);
				// if (bilanFinalList == null || bilanFinalList.isEmpty()) {
				// Utility.showWarningMessage(bilanBundle.getString("bilan_data_table_seacrh_result_no_result"));
				// return;
				// }
				releveNoteList = bilanSessionService.findBilanAnnuel(oofId,
						niveauId);
				if (releveNoteList != null) {
					for (BilanSessionDto _bilaAnnuel : releveNoteList) {
						List<BilanSessionDto> _bilanPeriodeList = _bilaAnnuel
								.getBilanSessionDtos();
						if (_bilanPeriodeList != null) {
							for (BilanSessionDto _bilaPeriode : _bilanPeriodeList) {
								if (_bilaPeriode.getBilanSessionDtoMax() != null) {
									_bilaPeriode.setBilanUes(_bilaPeriode
											.getBilanSessionDtoMax()
											.getBilanUes());
								}
							}
						}
					}
				}
				loadMention();
				loadDecisonJuryItem();
				loadAdmisDecisonAnnuel();
				loadDeliberationList();
				// loadBilanUe();
				// loadBilanMc();
				// loadReleveByPeriode();
				showDetail = true;
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanBean.loadDeliberationList] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 nov. 2014 09:44:38
	 */
	public void loadDeliberationList() {
		DeliberationSessionDto searchDto = new DeliberationSessionDto();
		searchDto.setOofId(oofId);
		searchDto.setPeriodeId(periodeId);
		searchDto.setAnneeAcademiqueId(anneeAcademiqueId);
		deliberationList = deliberationSessionService.findAdvanced(searchDto);

	}

	/**
	 * [BilanBean.findRelevePeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 12:00:38
	 */
	public void findRelevePeriode() {
		try {
			showDetail = false;
			bilanPeriodeList = null;
			releveNoteList = null;
			if (oofId != null && periodeId != null) {

				releveNoteList = bilanSessionService.findBilanPeriode(oofId,
						periodeId);
				for (BilanSessionDto _bilaPeriode : releveNoteList) {

					if (_bilaPeriode.getBilanSessionDtoMax() != null) {

						_bilaPeriode.setBilanUes(_bilaPeriode
								.getBilanSessionDtoMax().getBilanUes());
						List<BilanSessionDto> list = new ArrayList<BilanSessionDto>();
						list.add(_bilaPeriode);
						_bilaPeriode.setBilanSessionDtos(list);

					}
				}
				// bilanFinalList = bilanPeriodeList;
				loadMention();
				loadDecisonJuryItem();
				loadDeliberationList();
				// loadBilanUe();
				// loadBilanMc();
				// loadReleveByPeriode();
				showDetail = true;
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanBean.findReleveNote] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 12:01:31
	 */
	public void findReleveNote() {
		if (periodeId != null) {
			findRelevePeriode();
		} else if (niveauId != null) {
			findBilanFinal();
		}
	}

	/**
	 * [BilanBean.loadBilanPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 15:26:08
	 */
	public void loadBilanPeriode() {

		if (anneeAcademiqueId != null && oofId != null && niveauId != null) {
			deliberationList = deliberationSessionService.findBilanPeriode(
					anneeAcademiqueId, oofId, niveauId);
		}

	}

	/**
	 * [BilanBean.findBilanPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 08:48:39
	 */
	public void findBilanPeriode() {
		try {
			showDetail = false;
			if (!allSessionIsClosed()) {
				// Utility.showErrorMessage(bilanBundle
				// .getString("bilan_erreur_seance_deliberation_non_ferme"));
				// return;
			}
			deliberationId = null;
			deliberationClotured = false;
			DeliberationSessionDto _deliberation = getDeliberationWithBilanPeriode();
			if (_deliberation != null) {
				deliberationId = _deliberation.getId();
				if (_deliberation.getSituationId() != null
						&& _deliberation.getSituationId().equals(
								situationClose.getId())) {
					deliberationClotured = true;
				}
			}

			if (deliberationId == null) {
				Utility.showErrorMessage(bilanBundle
						.getString("bilan_erreur_bilan_periode_inexistant"));
				return;
			}
			if (_deliberation.getSituationId() != null
					&& situationClose.getId() != 0
					&& _deliberation.getSituationId().equals(
							situationClose.getId())) {
				/*****
				 * Bilan annuel clotur� : chargement directement � partir de
				 * la table
				 *****/
				deliberationClosed = true;
			}
			// updateBilanSession();

			if (oofId != null && periodeId != null) {
				bilanSessionList = bilanSessionService.findBilanSession(oofId,
						periodeId);
				if (bilanSessionList == null || bilanSessionList.isEmpty()) {
					Utility.showWarningMessage(bilanBundle
							.getString("bilan_data_table_seacrh_result_no_result"));
					return;
				}
				loadMention();
				// loadDecisonJuryPeriodeItem();
				loadBilanBySession();
				showDetail = true;
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanBean.loadBilanBySession] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 11:14:17
	 */
	public void loadBilanBySession() {
		if (bilanSessionList != null && deliberationList != null) {
			bilanPeriodeList = new ArrayList<BilanSessionDto>();

			// for (DeliberationSessionDto _deliberation : deliberationList)
			{
				for (BilanSessionDto _bilan : bilanSessionList) {
					loadEtudiantInfo(_bilan);
					if (Utility.isBilanPeriode(_bilan.getType())) {
						BilanSessionDto _bilanSessionDto = findBilanSession(_bilan
								.getDossierInscriptionAdministrativeId());
						if (_bilanSessionDto == null) {
							_bilan.setBilanSessionDtos(new ArrayList<BilanSessionDto>());
							bilanPeriodeList.add(_bilan);

						}

					} else if (Utility.isBilanSession(_bilan.getType()))
					// if (_bilan.getDeliberationSessionId() != null
					// &&
					// _bilan.getDeliberationSessionId().equals(_deliberation.getId()))
					{
						BilanSessionDto _bilanSessionDto = findBilanSession(_bilan
								.getDossierInscriptionAdministrativeId());
						if (_bilanSessionDto == null) {
							BilanSessionDto bilanSessionDto = new BilanSessionDto();
							List<BilanSessionDto> sessions = new ArrayList<BilanSessionDto>();

							BilanSessionDto _bs = bilanSessionService
									.findUniquePeriode(
											deliberationId,
											_bilan.getDossierInscriptionAdministrativeId());
							if (_bs != null) {
								bilanSessionDto.setId(_bs.getId());
							}
							bilanSessionDto.setDateNaissanceEtudiant(_bilan
									.getDateNaissanceEtudiant());
							bilanSessionDto.setLieuNaissanceEtudiant(_bilan
									.getLieuNaissanceEtudiant());
							bilanSessionDto.setNomLatinEtudiant(_bilan
									.getNomLatinEtudiant());
							bilanSessionDto.setPrenomLatinEtudiant(_bilan
									.getPrenomLatinEtudiant());
							bilanSessionDto.setMatriculeEtudiant(_bilan
									.getMatriculeEtudiant());
							bilanSessionDto.setNumeroInscriptionEtudiant(_bilan
									.getNumeroInscriptionEtudiant());
							bilanSessionDto.setCreditObtenu(_bilan
									.getCreditObtenu());
							bilanSessionDto.setCreditAcquis(_bilan
									.getCreditAcquis());
							bilanSessionDto
									.setDossierInscriptionAdministrativeId(_bilan
											.getDossierInscriptionAdministrativeId());
							bilanSessionDto.setDossierEtudiantId(_bilan
									.getDossierEtudiantId());
							bilanSessionDto
									.setDeliberationSessionId(deliberationId);
							bilanSessionDto
									.setBilanSessionDtoMax(new BilanSessionDto(
											_bilan.getId()));
							// bilanSessionDto.setBilanSessionMaxId(_bilan.getId());
							// if (_deliberation.getBilanPeriode()) {
							//
							// }
							// _bilan.setSessionIntitule(_deliberation.getPsIntitule());
							// bilanSessionDto.setBilanSessionId(_bilan.getId());
							_bilan.setColumnIntitule(_bilan.getPsIntitule());
							sessions.add(_bilan);
							bilanSessionDto.setCreditObtenu(_bilan
									.getCreditObtenu());
							bilanSessionDto.setCreditAcquis(_bilan
									.getCreditAcquis());
							bilanSessionDto.setCredit(_bilan.getCredit());
							bilanSessionDto.setBilanSessionDtos(sessions);
							bilanSessionDto.setMoyenneGenerale(Utility
									.double2position(_bilan.getMoyenne()));
							bilanSessionDto.setFormattedMG(Utility
									.formatNote(bilanSessionDto
											.getMoyenneGenerale()));
							setMention(_bilan.getMoyenne(), bilanSessionDto);
							bilanPeriodeList.add(bilanSessionDto);

						} else {
							List<BilanSessionDto> _session = _bilanSessionDto
									.getBilanSessionDtos();
							if (_session == null) {
								_session = new ArrayList<BilanSessionDto>();
							}
							_bilan.setColumnIntitule(_bilan.getPsIntitule());
							_session.add(_bilan);
							// boolean exist =
							// findSession(_bilanSessionDto.getBilanSessionDtos(),
							// _bilan);
							// if (!exist)
							{
								if (_bilanSessionDto.getMoyenneGenerale() < _bilan
										.getMoyenne()) {
									_bilanSessionDto.setMoyenneGenerale(Utility
											.double2position(_bilan
													.getMoyenne()));
									_bilanSessionDto.setFormattedMG(Utility
											.formatNote(_bilanSessionDto
													.getMoyenneGenerale()));
									// _bilanSessionDto.setBilanSessionMaxId(_bilan.getId());
									_bilanSessionDto
											.setBilanSessionDtoMax(new BilanSessionDto(
													_bilan.getId()));
									_bilanSessionDto.setCreditObtenu(_bilan
											.getCreditObtenu());
									_bilanSessionDto.setCreditAcquis(_bilan
											.getCreditAcquis());
									_bilanSessionDto.setCredit(_bilan
											.getCredit());
									setMention(_bilan.getMoyenne(),
											_bilanSessionDto);
								}
								_bilanSessionDto
										.setDeliberationSessionId(deliberationId);
								// if (_deliberation.getBilanPeriode()) {
								// //_bilanSessionDto.setPeriode(true);
								//
								// }
								// _bilan.setSessionIntitule(_deliberation.getPsIntitule());
								// _bilanSessionDto.getBilanSessionDtos().add(_bilan);
							}

						}

					}
				}

			}
			// System.out.println(bilanPeriodeList);
		}
	}

	/**
	 * [BilanBean.loadEtudiantInfo] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 nov. 2014 12:47:26
	 * @param _bilan
	 */
	public void loadEtudiantInfo(BilanSessionDto _bilan) {
		if (_bilan.getNomLatinEtudiant() == null
				&& _bilan.getDossierInscriptionAdministrativeId() != null) {
			DossierInscriptionAdministrativeDto _dia = dossierInscriptionAdministrativeService
					.findById(_bilan.getDossierInscriptionAdministrativeId());
			if (_dia != null) {
				_bilan.setMatriculeEtudiant(_dia.getNumeroMatricule());
				_bilan.setNumeroInscriptionEtudiant(_dia.getNumeroInscription());
				_bilan.setNomLatinEtudiant(_dia.getIndividuNomLatin());
				_bilan.setPrenomLatinEtudiant(_dia.getIndividuPrenomLatin());
				_bilan.setNomArabeEtudiant(_dia.getIndividuNomArabe());
				_bilan.setPrenomArabeEtudiant(_dia.getIndividuPrenomArabe());
				_bilan.setDateNaissanceEtudiant(_dia.getIndividuDateNaissance());
				_bilan.setLieuNaissanceEtudiant(_dia.getIndividuLieuNaissance());
				_bilan.setDossierEtudiantId(_dia.getDossierEtudiantId());
			}
		}

	}

	/**
	 * [BilanBean.loadBilanByPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 15:12:17
	 */
	public void loadBilanByPeriode() {

		if (inscriptionAdministrativeList != null) {

			bilanAnnuelList = new ArrayList<BilanSessionDto>();
			for (DossierInscriptionAdministrativeDto _etudiant : inscriptionAdministrativeList) {
				List<DossierInscriptionAdministrativeDto> allInscription = _etudiant
						.getDossierInscriptionAdministrativeDtos();
				if (allInscription != null) {
					for (DossierInscriptionAdministrativeDto _inscription : allInscription) {
						if (_inscription.getNiveauRang() != null
								&& niveauDto != null
								&& _inscription.getNiveauRang() <= niveauDto
										.getRang()) {

							List<BilanSessionDto> bilanSessionList = _inscription
									.getBilanSessionDtos();

							BilanSessionDto bilanAnnuel = null;
							for (BilanSessionDto _bilan : bilanSessionList) {
								boolean bilanAdded = false;
								if (Utility.isBilanPeriode(_bilan.getType())) {

									for (DeliberationSessionDto _deliberation : deliberationList) {
										if (_bilan.getDeliberationSessionId() != null
												&& _bilan
														.getDeliberationSessionId()
														.equals(_deliberation
																.getId())) {
											bilanAdded = true;
											if (_bilan.getBilanSessionDtoMax() != null) {
												_bilan.setBilanUes(_bilan
														.getBilanSessionDtoMax()
														.getBilanUes());
											}
											BilanSessionDto _bilanAnnuel = findBilanAnnuel(_etudiant
													.getDossierEtudiantId());
											if (_bilanAnnuel == null) {
												bilanAnnuel = new BilanSessionDto();
												mapBilanAnnuelDia(bilanAnnuel,
														_inscription);
												List<BilanSessionDto> sessions = new ArrayList<BilanSessionDto>();
												_bilan.setSessionIntitule(_deliberation
														.getPeriodeLibelleFr());
												_bilan.setColumnIntitule(_deliberation
														.getPeriodeLibelleFr());
												sessions.add(_bilan);

												bilanAnnuel
														.setBilanSessionDtos(sessions);
												bilanAnnuel
														.setMoyenneGenerale(Utility
																.double2position(_bilan
																		.getMoyenne()));
												bilanAnnuel
														.setFormattedMG(Utility
																.formatNote(bilanAnnuel
																		.getMoyenneGenerale()));
												bilanAnnuel
														.setCreditObtenu(_bilan
																.getCreditObtenu());
												bilanAnnuel
														.setCreditAcquis(_bilan
																.getCreditAcquis());
												bilanAnnuel.setCredit(_bilan
														.getCredit());
												setDecisionAnnuel(bilanAnnuel);
												setMention(_bilan.getMoyenne(),
														bilanAnnuel);
												bilanAnnuelList
														.add(bilanAnnuel);

											} else {
												if (_bilanAnnuel
														.getBilanSessionDtos() != null) {

													_bilanAnnuel
															.setMoyenneGenerale(Utility
																	.double2position(_bilanAnnuel
																			.getMoyenneGenerale()
																			+ _bilan.getMoyenne()));
													_bilanAnnuel
															.setFormattedMG(Utility
																	.formatNote(_bilanAnnuel
																			.getMoyenneGenerale()));
													_bilanAnnuel
															.setCreditObtenu(_bilanAnnuel
																	.getCreditObtenu()
																	+ _bilan.getCreditObtenu());
													_bilanAnnuel
															.setCreditAcquis(_bilanAnnuel
																	.getCreditAcquis()
																	+ _bilan.getCreditAcquis());
													_bilanAnnuel
															.setCredit(_bilanAnnuel
																	.getCredit()
																	+ _bilan.getCredit());
													// _bilanModel.setBilanSessionId(_bilan.getId());

													if (_deliberation
															.getBilanAnnuel()) {
														_bilanAnnuel
																.setAnnuel(true);
														_bilanAnnuel
																.setDeliberationSessionId(_deliberation
																		.getId());
													}
													_bilan.setSessionIntitule(_deliberation
															.getPeriodeLibelleFr());
													_bilan.setColumnIntitule(_deliberation
															.getPeriodeLibelleFr());
													_bilanAnnuel
															.getBilanSessionDtos()
															.add(_bilan);

													_bilanAnnuel
															.setMoyenneGenerale(Utility
																	.double2position(_bilanAnnuel
																			.getMoyenneGenerale() / 2));
													_bilanAnnuel
															.setFormattedMG(Utility
																	.formatNote(_bilanAnnuel
																			.getMoyenneGenerale()));
													setMention(
															_bilanAnnuel
																	.getMoyenneGenerale(),
															_bilanAnnuel);
													setDecisionAnnuel(_bilanAnnuel);
													if (_bilanAnnuel
															.getMoyenneGenerale() >= 10) {
														_bilanAnnuel
																.setCreditObtenu(_bilanAnnuel
																		.getCredit());

													} else {
														// _bilanAnnuel.setTypeDecisionId(null);
														// _bilanAnnuel.setTypeDecisionLibelleFr(null);
													}

												}
											}

										}
									}
								} else if (!bilanAdded
										&& _bilan.getDeliberationSessionId() != null) {
									/***** bilan d'une année précedente *******/
									if (Utility.isBilanAnnuel(_bilan.getType())
											&& _bilan.getNiveauRang() != niveauDto
													.getRang()) {
										BilanSessionDto _bilanAnnuel = findBilanAnnuel(_etudiant
												.getDossierEtudiantId());
										if (_bilanAnnuel != null) {
											List<BilanSessionDto> _oldBilanAnnuel = _bilanAnnuel
													.getBilanAnnuels();
											if (_oldBilanAnnuel == null) {
												_oldBilanAnnuel = new ArrayList<BilanSessionDto>();
											}
											/**** prendre le max de la session ******/
											List<BilanSessionDto> _bilanPeriodeList = _bilan
													.getBilanSessionDtos();
											if (_bilanPeriodeList != null) {
												for (BilanSessionDto _bilanPeriode : _bilanPeriodeList) {
													if (Utility
															.isBilanPeriode(_bilanPeriode
																	.getType())
															&& _bilanPeriode
																	.getBilanSessionDtoMax() != null) {
														_bilanPeriode
																.setBilanUes(_bilanPeriode
																		.getBilanSessionDtoMax()
																		.getBilanUes());
													}
												}
											}
											_oldBilanAnnuel.add(_bilan);
											_bilanAnnuel
													.setBilanAnnuels(_oldBilanAnnuel);
										}
									}
								}
							}
						}
					}
				}
			}
			// System.out.println(bilanAnnuelList);
		}
	}

	/**
	 * [BilanBean.mapBilanAnnuelDia] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 15:44:55
	 * @param bilanAnnuel
	 * @param diaDto
	 */
	private void mapBilanAnnuelDia(BilanSessionDto bilanAnnuel,
			DossierInscriptionAdministrativeDto diaDto) {
		bilanAnnuel.setDateNaissanceEtudiant(diaDto.getIndividuDateNaissance());
		bilanAnnuel.setLieuNaissanceEtudiant(diaDto.getIndividuLieuNaissance());
		bilanAnnuel.setNomLatinEtudiant(diaDto.getIndividuNomLatin());
		bilanAnnuel.setPrenomLatinEtudiant(diaDto.getIndividuPrenomLatin());
		bilanAnnuel.setMatriculeEtudiant(diaDto.getNumeroMatricule());
		bilanAnnuel.setNumeroInscriptionEtudiant(diaDto.getNumeroInscription());
		bilanAnnuel.setDossierInscriptionAdministrativeId(diaDto.getId());
		bilanAnnuel.setDossierEtudiantId(diaDto.getDossierEtudiantId());
	}

	/**
	 * [BilanBean.passage] Method mise à jour de bilan selon la moyenne
	 * générale
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 16:11:24
	 */
	public void passage() {

		/*************
		 * Règles de passage ****************** Passage du L1 au L2 :
		 * ---------------------- Cas 1 (dans l’un ou l’autre cas,
		 * l’étudiant a capitalisé 60 crédits) : - L’étudiant est admis
		 * en deuxième année s’il a validé les deux semestres de la 1ère
		 * année du cycle de formation. - L’étudiant est admis en deuxième
		 * année s’il a validé l’année par compensation entre les UE de
		 * la première année. Cas 2 (l’étudiant a capitalisé au moins 30
		 * crédits) : - L’étudiant peut être autorisé à poursuivre les
		 * enseignements de la 2ème Année du cycle de formation s’il valide
		 * au moins 50% des crédits de la 1ère Année, dont au moins 1/3 dans
		 * un semestre, il est alors tenu de se réinscrire aux matières non
		 * acquises des UE non acquises de la 1ère Année .
		 * 
		 * Passage du L2 au L3: --------------------- Cas 1 (Capitalisation) : -
		 * L’étudiant est admis en 3ème Année s’il a validé les quatre
		 * premiers semestres du cycle de formation. - L’étudiant est admis
		 * en troisième année s’il a validé l’année par compensation
		 * entre les UE de la deuxième année, et qu’il n’a pas de dettes
		 * de la 1ère année Cas 2 (Compensation) : - La progression à la
		 * 3ème Année peut être autorisé pour tout étudiant justifiant au
		 * moins 92 crédit des 2 premiers années, et ayant validé toutes le
		 * UE fondamentales indispensables à la poursuite des études en
		 * spécialité. Dans ce cas, l’étudiant est tenu de se réinscrire
		 * aux matières non acquises des UE non acquises des quartes premiers
		 * semestres.
		 * 
		 * Passage du M1 au M2: ----------------------- Cas 1 : - L’étudiant
		 * est admis en deuxième année s’il a validé les deux semestres de
		 * la 1ère année du cycle de formation. Cas 2 : - L’étudiant peut
		 * être autorisé à poursuivre les enseignements de la 2ème Année
		 * Master s’il a validé au moins 45 crédits et acquis les UE
		 * fondamental requis pour la poursuite des études en spécialité.
		 */

		if (bilanAnnuelList != null) {
			for (BilanSessionDto _bilanAnnuel : bilanAnnuelList) {
				List<BilanSessionDto> sessions = _bilanAnnuel
						.getBilanSessionDtos();
				double creditTotal = 0.0;
				if (sessions != null) {
					for (BilanSessionDto _bilan : sessions) {
						double creditSession = 0.0;
						if (!_bilan.isOldSession()) {
							List<BilanUeDto> bilanUes = _bilan.getBilanUes();
							if (bilanUes != null) {
								for (BilanUeDto ue : bilanUes) {
									double creditUe = 0.0;
									List<BilanMcDto> bilanMcs = ue
											.getBilanMcs();
									if (bilanMcs != null) {
										for (BilanMcDto mc : bilanMcs) {
											if (_bilanAnnuel
													.getMoyenneGenerale() >= mc
													.getMcNoteObtention()) {
												mc.setCreditObtenu(mc
														.getCredit());
												mc.setDette(false);
												mc.setMcAcquis(true);
											} else if (mc.getMoyenneGenerale() < mc
													.getMcNoteObtention()) {
												mc.setDette(true);
											} else {
												mc.setCreditObtenu(mc
														.getCredit());
											}
											creditUe = creditUe
													+ mc.getCreditObtenu();
										}
									}
									if (_bilanAnnuel.getMoyenneGenerale() >= ue
											.getUeNoteObtention()) {
										ue.setCreditObtenu(ue.getCredit());
										ue.setUeAcquis(true);
									} else {
										ue.setCreditObtenu(creditUe);
									}
									creditSession = creditSession
											+ ue.getCreditObtenu();
								}
							}
							if (_bilanAnnuel.getMoyenneGenerale() >= 10) {
								_bilan.setCreditObtenu(_bilan.getCredit());

							} else {
								_bilan.setCreditObtenu(creditSession);
								/******** verification de la compensation *******/
								if (_bilan.getCreditObtenu() >= Const.NOTATION_PASSAGE_L1_L2_CREDIT_MIN_PERIODE) {
									if (niveauDto != null
											&& niveauDto
													.getCode()
													.equals(UtilConstants.NIVEAU1_INSCRIPTION_LMD_CODE)) {
										/****** L1----> L2 ********/
										_bilan.setCreditMinObtenu(true);
										_bilanAnnuel.setCreditMinObtenu(true);
									}

								}
							}

						} else if (_bilan.isAnnuel()
								&& _bilan
										.getNiveauCode()
										.equals(UtilConstants.NIVEAU1_INSCRIPTION_LMD_CODE)) {
							if (_bilan.getTypeDecisionId() != null
									&& _bilan.getTypeDecisionId().equals(
											ncDecisionPassageAvecDette.getId())) {
								_bilanAnnuel.setPassageL1AvecDette(true);
							}

						}
						creditTotal = creditTotal + _bilan.getCreditObtenu();
					}
				}
				_bilanAnnuel.setCreditObtenu(creditTotal);
				setMention(_bilanAnnuel.getMoyenneGenerale(), _bilanAnnuel);
				if (_bilanAnnuel.getMoyenneGenerale() < 10) {
					/****** L1----> L2 ********/
					if (niveauDto != null
							&& niveauDto.getCode().equals(
									UtilConstants.NIVEAU1_INSCRIPTION_LMD_CODE)) {
						if (_bilanAnnuel.getCreditObtenu() >= Const.NOTATION_PASSAGE_L1_L2_CREDIT_MIN_ANNEE
								&& _bilanAnnuel.isCreditMinObtenu()) {

							if (_bilanAnnuel.getTypeDecisionId() == null
									&& ncDecisionPassageAvecDette != null) {
								_bilanAnnuel
										.setTypeDecisionId(ncDecisionPassageAvecDette
												.getId());
								_bilanAnnuel
										.setTypeDecisionLibelleFr(ncDecisionPassageAvecDette
												.getLibelleLongFr());
								_bilanAnnuel
										.setStyleClass(Const.NOTATION_DECISION_JURY_PASSAGE_AVEC_DETTE_STYLE_CLASS);
							}
						}
					}
				} else {
					/****** L2----> L3 ********/
					if (niveauDto != null
							&& niveauDto.getCode().equals(
									UtilConstants.NIVEAU2_INSCRIPTION_LMD_CODE)) {
						{
							/***
							 * CAS1.1***** pas de dette dans la première année
							 */
							if (_bilanAnnuel.isPassageL1AvecDette()) {

							}

						}
					}
				}
			}
			// System.out.println(bilanAnnuelList);
		}
	}

	/**
	 * [BilanBean.loadReleveByPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 11:09:35
	 */
	public void loadReleveByPeriode() {
		releveNoteList = bilanPeriodeList;
	}

	/**
	 * [BilanBean.loadBilanUe] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 11:17:32
	 */
	public void loadBilanUe() {
		if (oofId != null && niveauId != null) {
			bilanUeList = bilanUeService.findByOofAndNiveau(oofId, niveauId);
		}
	}

	/**
	 * [BilanBean.loadBilanMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 11:17:53
	 */
	public void loadBilanMc() {
		if (oofId != null && niveauId != null) {
			bilanMcList = bilanMcService.findByOofAndNiveau(oofId, niveauId);
		}
	}

	/**
	 * [BilanBean.loadDecisionPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 16:12:58
	 */
	public void loadDecisionBilanAnnuel() {
		if (bilanModelList != null) {
			for (BilanModel bm : bilanModelList) {
				BilanSessionDto _bs = bilanSessionService.findUniqueAnnuel(
						bm.getDeliberationId(), bm.getDiaId());
				if (_bs != null) {
					bm.setDecisionId(_bs.getTypeDecisionId());
				}
			}
		}
	}

	/**
	 * [BilanBean.findBilan] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 11:54:26
	 * @param diaId
	 * @return
	 */
	private BilanSessionDto findBilanSession(Integer diaId) {
		if (diaId == null) {
			return null;
		}
		for (BilanSessionDto _bilanSessionDto : bilanPeriodeList) {
			if (_bilanSessionDto.getDossierInscriptionAdministrativeId() != null
					&& _bilanSessionDto.getDossierInscriptionAdministrativeId()
							.equals(diaId)) {
				return _bilanSessionDto;
			}
		}
		return null;
	}

	/**
	 * [BilanBean.findBilanAnnuel] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 08:03:33
	 * @param dossierEtudiantId
	 * @return
	 */
	private BilanSessionDto findBilanAnnuel(int dossierEtudiantId) {

		for (BilanSessionDto _bilan : bilanAnnuelList) {
			if (_bilan.getDossierEtudiantId() == dossierEtudiantId) {
				return _bilan;
			}
		}
		return null;
	}

	/**
	 * [BilanBean.getMention] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 13:15:35
	 * @param note
	 * @return
	 */
	public MentionDto getMention(Double note) {
		if (mentionList != null) {
			for (MentionDto mention : mentionList) {
				if (note != null && mention.getNoteMin() <= note
						&& mention.getNoteMax() > note) {
					return mention;
				}
			}
		}
		return null;
	}

	/**
	 * [BilanBean.setMention] Method
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 12:26:16
	 * @param note
	 * @param bilanModel
	 */
	public void setMention(double note, BilanModel bilanModel) {
		if (deliberationClosed && deliberationId != null
				&& bilanModel.getBilanSessionId() != null) {
			BilanSessionDto _bilanSessionDto = bilanSessionService
					.findById(bilanModel.getBilanSessionId());
			if (_bilanSessionDto != null
					&& _bilanSessionDto.getMentionId() != null) {
				if (_bilanSessionDto.getMentionCode() != null) {
					bilanModel.setMentionLibelle(_bilanSessionDto
							.getMentionCode());
				} else {
					bilanModel.setMentionLibelle("-");
				}
				bilanModel.setMentionId(_bilanSessionDto.getMentionId());
				return;
			}
		}

		MentionDto _mentionDto = getMention(note);
		if (_mentionDto != null) {
			bilanModel.setMentionLibelle(_mentionDto.getNcMentionCode());
			bilanModel.setMentionId(_mentionDto.getNcMentionId());
		} else {
			bilanModel.setMentionLibelle("-");
			bilanModel.setMentionId(null);
		}
	}

	/**
	 * [BilanBean.setMention] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 08:20:43
	 * @param note
	 * @param bilanAnnuel
	 */
	public void setMention(double note, BilanSessionDto bilanAnnuel) {
		BilanSessionDto bilanDtoMax = bilanAnnuel.getBilanSessionDtoMax();
		if (deliberationClosed && deliberationId != null && bilanDtoMax != null
				&& bilanDtoMax.getId() != 0) {
			// BilanSessionDto _bilanSessionDto = bilanSessionService
			// .findUniqueAnnuel(deliberationId, bilanModel.getDiaId());
			BilanSessionDto _bilanSessionDto = bilanSessionService
					.findById(bilanDtoMax.getId());
			if (_bilanSessionDto != null
					&& _bilanSessionDto.getMentionId() != null) {
				if (_bilanSessionDto.getMentionCode() != null) {
					bilanAnnuel.setMentionLibelleFr(_bilanSessionDto
							.getMentionCode());
					bilanAnnuel.setMentionId(_bilanSessionDto.getMentionId());
				}
				bilanAnnuel.setMentionId(_bilanSessionDto.getMentionId());
				return;
			}
		}

		MentionDto _mentionDto = getMention(note);
		if (_mentionDto != null) {
			bilanAnnuel.setMentionLibelleFr(_mentionDto.getNcMentionCode());
			bilanAnnuel.setMentionId(_mentionDto.getNcMentionId());
		} else {
			bilanAnnuel.setMentionId(null);
			bilanAnnuel.setMentionLibelleFr(null);
		}
	}

	/**
	 * [BilanBean.getMentionId] Method
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 12:18:43
	 * @param note
	 * @return
	 */
	public Integer getMentionId(Double note) {
		if (mentionList != null) {
			for (MentionDto mention : mentionList) {
				if (note != null && mention.getNoteMin() <= note
						&& mention.getNoteMax() > note) {
					return mention.getId();
				}
			}
		}
		return null;
	}

	/**
	 * [BilanBean.bilanSessionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:27:21
	 * @return the bilanSessionService
	 */
	public BilanSessionService getBilanSessionService() {
		return bilanSessionService;
	}

	/**
	 * [BilanBean.bilanSessionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:27:21
	 * @param bilanSessionService
	 *            the bilanSessionService to set
	 */
	public void setBilanSessionService(BilanSessionService bilanSessionService) {
		this.bilanSessionService = bilanSessionService;
	}

	/**
	 * [BilanBean.bilanAnnuelList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 08:45:29
	 * @return the bilanAnnuelList
	 */
	public List<BilanSessionDto> getBilanAnnuelList() {
		return bilanAnnuelList;
	}

	/**
	 * [BilanBean.bilanAnnuelList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 08:45:29
	 * @param bilanAnnuelList
	 *            the bilanAnnuelList to set
	 */
	public void setBilanAnnuelList(List<BilanSessionDto> bilanAnnuelList) {
		this.bilanAnnuelList = bilanAnnuelList;
	}

	/**
	 * [BilanBean.showDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:46:20
	 * @return the showDetail
	 */
	public boolean isShowDetail() {
		return showDetail;
	}

	/**
	 * [BilanBean.showDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:46:20
	 * @param showDetail
	 *            the showDetail to set
	 */
	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}

	/**
	 * [BilanBean.bilanPeriodeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 08:48:21
	 * @return the bilanPeriodeList
	 */
	public List<BilanSessionDto> getBilanPeriodeList() {
		return bilanPeriodeList;
	}

	/**
	 * [BilanBean.bilanPeriodeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 08:48:21
	 * @param bilanPeriodeList
	 *            the bilanPeriodeList to set
	 */
	public void setBilanPeriodeList(List<BilanSessionDto> bilanPeriodeList) {
		this.bilanPeriodeList = bilanPeriodeList;
	}

	/**
	 * [BilanBean.periodeId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 09:36:23
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [BilanBean.periodeId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 09:36:23
	 * @param periodeId
	 *            the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [BilanBean.periodeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 09:36:48
	 * @return the periodeList
	 */
	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	/**
	 * [BilanBean.periodeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 09:36:48
	 * @param periodeList
	 *            the periodeList to set
	 */
	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	/**
	 * [BilanBean.annuel] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 09:38:36
	 * @return the annuel
	 */
	public boolean isAnnuel() {
		return annuel;
	}

	/**
	 * [BilanBean.annuel] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 09:38:36
	 * @param annuel
	 *            the annuel to set
	 */
	public void setAnnuel(boolean annuel) {
		this.annuel = annuel;
	}

	/**
	 * [BilanBean.deliberationSessionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 10:50:30
	 * @return the deliberationSessionService
	 */
	public DeliberationSessionService getDeliberationSessionService() {
		return deliberationSessionService;
	}

	/**
	 * [BilanBean.deliberationSessionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 10:50:30
	 * @param deliberationSessionService
	 *            the deliberationSessionService to set
	 */
	public void setDeliberationSessionService(
			DeliberationSessionService deliberationSessionService) {
		this.deliberationSessionService = deliberationSessionService;
	}

	/**
	 * [BilanBean.allSessionIsClosed] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 10:54:17
	 * @return
	 */
	public boolean allSessionIsClosed() {
		if (situationClose == null || situationClose.getId() == 0) {
			situationClose = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DELIBERATION_SESSION_CODE,
							UtilConstants.SITUTAION_CLOTUREE_CODE);
		}
		if (situationClose != null || situationClose.getId() != 0) {
			DeliberationSessionDto searchDto = new DeliberationSessionDto();
			searchDto.setOofId(oofId);
			searchDto.setPeriodeId(periodeId);
			searchDto.setAnneeAcademiqueId(anneeAcademiqueId);
			deliberationList = deliberationSessionService
					.findAdvanced(searchDto);
			if (deliberationList != null) {
				for (DeliberationSessionDto del : deliberationList) {
					if (del.getSituationId() != null
							&& !del.getSituationId().equals(
									situationClose.getId())) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * [BilanBean.getDeliberationWithBilanPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 15:40:34
	 * @return
	 */
	public DeliberationSessionDto getDeliberationWithBilanPeriode() {
		if (deliberationList != null) {
			for (DeliberationSessionDto del : deliberationList) {
				if (del.getBilanPeriode()) {
					return del;
				}
			}
		}
		return null;
	}

	/**
	 * [BilanBean.getDeliberationWithBilanPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 17:07:24
	 * @return
	 */
	public DeliberationSessionDto getDeliberationWithBilanAnnuel() {
		if (deliberationList != null) {
			for (DeliberationSessionDto del : deliberationList) {
				if (del.getBilanAnnuel()) {
					return del;
				}
			}
		}
		return null;
	}

	/**
	 * [BilanBean.situationClose] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 10:53:24
	 * @return the situationClose
	 */
	public SituationEntiteDto getSituationClose() {
		return situationClose;
	}

	/**
	 * [BilanBean.situationClose] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 10:53:24
	 * @param situationClose
	 *            the situationClose to set
	 */
	public void setSituationClose(SituationEntiteDto situationClose) {
		this.situationClose = situationClose;
	}

	/**
	 * [BilanBean.situationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 10:54:12
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [BilanBean.situationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 10:54:12
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [BilanBean.deliberationList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 11:09:02
	 * @return the deliberationList
	 */
	public List<DeliberationSessionDto> getDeliberationList() {
		return deliberationList;
	}

	/**
	 * [BilanBean.deliberationList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 11:09:02
	 * @param deliberationList
	 *            the deliberationList to set
	 */
	public void setDeliberationList(
			List<DeliberationSessionDto> deliberationList) {
		this.deliberationList = deliberationList;
	}

	/**
	 * [BilanBean.bilanModelList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 11:13:39
	 * @return the bilanModelList
	 */
	public List<BilanModel> getBilanModelList() {
		return bilanModelList;
	}

	/**
	 * [BilanBean.bilanModelList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 11:13:39
	 * @param bilanModelList
	 *            the bilanModelList to set
	 */
	public void setBilanModelList(List<BilanModel> bilanModelList) {
		this.bilanModelList = bilanModelList;
	}

	/**
	 * [BilanBean.mentionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 13:16:14
	 * @return the mentionList
	 */
	public List<MentionDto> getMentionList() {
		return mentionList;
	}

	/**
	 * [BilanBean.mentionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 13:16:14
	 * @param mentionList
	 *            the mentionList to set
	 */
	public void setMentionList(List<MentionDto> mentionList) {
		this.mentionList = mentionList;
	}

	/**
	 * [BilanBean.colturerBilanPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 13:20:52
	 */
	public void colturerBilanPeriode() {
		try {
			saveBilanPeriode();
			bilanPeriodeClotured = true;
			Utility.showSuccessSaveMessage();
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanBean.saveBilanPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 13:24:26
	 */
	public void saveBilanPeriode() {
		if (bilanPeriodeList != null) {
			if (situationClose == null || situationClose.getId() == 0) {
				Utility.showErrorMessage(bilanBundle
						.getString("deliberation_erreur_situation_cree_inexistante"));
				return;
			}
			if (deliberationId == null) {
				Utility.showErrorMessage(bilanBundle
						.getString("deliberation_erreur_deliberation_vide"));
				return;
			}

			for (BilanSessionDto _bilan : bilanPeriodeList) {
				if (_bilan.getId() == 0) {
					BilanSessionDto _bs = bilanSessionService
							.findUniquePeriode(deliberationId, _bilan
									.getDossierInscriptionAdministrativeId());
					if (_bs != null) {
						_bilan.setId(_bs.getId());
					}
				}
				_bilan.setMoyenne(_bilan.getMoyenneGenerale());
				_bilan.setPeriodeId(periodeId);
				_bilan.setOofId(oofId);
				_bilan.setType(UtilConstants.BILAN_TYPE_PERIODE);
				_bilan.setDeliberationSessionId(deliberationId);
				bilanSessionService.insertOrUpdate(_bilan);

				// _bs.setMoyenne(bm.getMoyenneGenerale());
				// _bs.setPeriodeId(periodeId);
				// _bs.setOofId(oofId);
				// _bs.setType(UtilConstants.BILAN_TYPE_PERIODE);
				// _bs.setDeliberationSessionId(deliberationId);
				// _bs.setTypeDecisionId(bm.getDecisionId());
				// _bs.setMentionId(bm.getMentionId());
				// _bs.setDossierInscriptionAdministrativeId(bm.getDiaId());
				// _bs.setBilanSessionMaxId(bm.getBilanSessionId());
				// _bs.setCreditObtenu(bm.getCreditObtenu());
				// _bs.setSituationId(situationClose.getId());
				// bilanSessionService.insertOrUpdate(_bilan);

			}

		}
	}

	/**
	 * [BilanBean.saveBilanAnnuel] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 16:21:01
	 */
	public void saveBilanAnnuel() {
		try {
			bilanAnnuelClotured = true;

			if (bilanAnnuelList != null) {
				if (situationClose == null || situationClose.getId() == 0) {
					Utility.showErrorMessage(bilanBundle
							.getString("deliberation_erreur_situation_cree_inexistante"));
					return;
				}
				if (deliberationId == null) {
					Utility.showErrorMessage(bilanBundle
							.getString("deliberation_erreur_deliberation_vide"));
					return;
				}
				for (BilanSessionDto _bilanAnnuel : bilanAnnuelList) {
					if (_bilanAnnuel.getId() == 0) {
						BilanSessionDto _bs = bilanSessionService
								.findUniqueAnnuel(
										deliberationId,
										_bilanAnnuel
												.getDossierInscriptionAdministrativeId());
						if (_bs != null) {
							_bilanAnnuel.setId(_bs.getId());
						}
					}
					_bilanAnnuel.setMoyenne(_bilanAnnuel.getMoyenneGenerale());
					_bilanAnnuel.setPeriodeId(periodeId);
					_bilanAnnuel.setOofId(oofId);
					_bilanAnnuel.setType(UtilConstants.BILAN_TYPE_ANNUEL);
					_bilanAnnuel.setDeliberationSessionId(deliberationId);
					bilanSessionService.insertOrUpdate(_bilanAnnuel);

				}

			}
			Utility.showSuccessSaveMessage();
		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanBean.annulerColtureBilanPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 13:22:07
	 */
	public void annulerColtureBilanPeriode() {
		try {
			if (bilanModelList != null) {
				if (situationCree == null || situationCree.getId() == 0) {
					Utility.showErrorMessage(bilanBundle
							.getString("deliberation_erreur_situation_cree_inexistante"));
					return;
				}
				for (BilanModel bm : bilanModelList) {

					BilanSessionDto _bs = bilanSessionService
							.findUniquePeriode(bm.getDeliberationId(),
									bm.getDiaId());
					if (_bs != null) {
						_bs.setSituationId(situationCree.getId());
						bilanSessionService.insertOrUpdate(_bs);
					}
				}
				Utility.showSuccessSaveMessage();
				bilanPeriodeClotured = false;
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanBean.annulerColtureBilanAnnuel] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 17:41:01
	 */
	public void annulerColtureBilanAnnuel() {
		try {
			if (bilanModelList != null) {
				if (situationCree == null || situationCree.getId() == 0) {
					Utility.showErrorMessage(bilanBundle
							.getString("deliberation_erreur_situation_cree_inexistante"));
					return;
				}
				for (BilanModel bm : bilanModelList) {
					BilanSessionDto _bs = bilanSessionService.findUniqueAnnuel(
							bm.getDeliberationId(), bm.getDiaId());
					if (_bs != null) {
						_bs.setSituationId(situationCree.getId());
						bilanSessionService.insertOrUpdate(_bs);
					}
				}
				Utility.showSuccessSaveMessage();
				bilanAnnuelClotured = false;
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanBean.loadFileName] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 16:25:41
	 */
	public void loadFileNamePeriode() {
		if (oofId != null) {
			OuvertureOffreFormationDto _oof = ouvertureOffreFormationService
					.findById(oofId);
			if (_oof != null) {
				ofLibelleLongFr = _oof.getOfLibelleLongFr();
			}
		}
		if (periodeId != null) {
			PeriodeDto _periode = periodeService.findById(periodeId);
			if (_periode != null) {
				periodeLibelleLongFr = _periode.getLibelleLongLt();
				niveauLibelleLongFr = _periode.getLibelleLongFrNiveau();
				cycleLibelleLongFr = _periode.getLibelleLongFrCycle();
			}
		}
		if (anneeAcademiqueId != null) {

			AnneeAcademiqueDto _annee = anneeAcademiqueService
					.findById(anneeAcademiqueId);
			if (_annee != null) {
				anneeCode = _annee.getCode();
			}
		}
		fileNamePeriode = "Bilan_P�riode_" + ofLibelleLongFr + "_"
				+ periodeLibelleLongFr + "_" + anneeCode;
	}

	/**
	 * [BilanBean.loadFileNameReleve] Method
	 * 
	 * @author MAKERRI Sofiane on : 29 oct. 2014 13:59:54
	 */
	public void loadFileNameReleve() {
		if (oofId != null) {
			OuvertureOffreFormationDto _oof = ouvertureOffreFormationService
					.findById(oofId);
			if (_oof != null) {
				ofLibelleLongFr = _oof.getOfLibelleLongFr();
			}
		}
		if (niveauId != null && niveauDto == null) {
			NiveauDto _niveau = niveauService.findById(niveauId);
			if (_niveau != null) {
				periodeLibelleLongFr = _niveau.getLibelleLongLt();
				niveauCode = _niveau.getCode();
				cycleLibelleLongFr = _niveau.getLibelleLongLtCycle();

			}
		}
		if (anneeAcademiqueId != null) {

			AnneeAcademiqueDto _annee = anneeAcademiqueService
					.findById(anneeAcademiqueId);
			if (_annee != null) {
				anneeCode = _annee.getCode();
			}
		}
		fileNameReleve = "Releve_Note_" + ofLibelleLongFr + "_"
				+ periodeLibelleLongFr + "_" + anneeCode;
	}

	/**
	 * [BilanBean.loadFileNameAnnuel] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 16:38:05
	 */
	public void loadFileNameAnnuel() {
		if (oofId != null) {
			OuvertureOffreFormationDto _oof = ouvertureOffreFormationService
					.findById(oofId);
			if (_oof != null) {
				ofLibelleLongFr = _oof.getOfLibelleLongFr();
			}
		}
		if (niveauId != null) {
			NiveauDto _niveau = niveauService.findById(niveauId);
			if (_niveau != null) {
				niveauLibelleLongFr = _niveau.getLibelleLongLt();
			}
		}
		if (anneeAcademiqueId != null) {

			AnneeAcademiqueDto _annee = anneeAcademiqueService
					.findById(anneeAcademiqueId);
			if (_annee != null) {
				anneeCode = _annee.getCode();
			}
		}
		fileNameAnnuel = "Bilan_Annuel_" + ofLibelleLongFr + "_"
				+ niveauLibelleLongFr + "_" + anneeCode;
	}

	/**
	 * [BilanBean.imprimerBilanPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 13:21:09
	 */
	public void imprimerBilanPeriode() {
		fillCrossTable(bilanPeriodeList);
		try {
			if (fileNamePeriode == null) {
				loadFileNamePeriode();
			}

			FacesContext facesContext = FacesContext.getCurrentInstance();
			String SUBREPORT_DIR = facesContext.getExternalContext()
					.getRealPath("/WEB-INF/classes")
					+ "/jasper/examen/deliberation/";
			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = SUBREPORT_DIR
					+ "bilan.jrxml";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.JASPER_PARAM_IMG_PAPS_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logopaps.png");
			params.put(Const.JASPER_PARAM_IMG_LOGO_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logo"
					+ sessionBean.getCodeEtablissement() + ".png");
			if (!deliberationClotured) {
				params.put(Const.JASPER_PARAM_INTITULE_KEY, bilanBundle
						.getString("bilan_reporting_bien_provisoire_text"));
			}

			params.put(Const.JASPER_PARAM_OFFRE_FORMATION_KEY, ofLibelleLongFr);

			params.put(Const.JASPER_PARAM_ANNEE_ACADEMIQUE_KEY, anneeCode);

			params.put(Const.JASPER_PARAM_PERIODE_KEY, periodeLibelleLongFr);

			params.put(Const.JASPER_PARAM_NIVEAU_KEY, niveauLibelleLongFr);

			params.put(Const.JASPER_PARAM_TITLE_KEY, "BILAN PERIODE");
			params.put(Const.JASPER_PARAM_SUBREPORT_DIR_KEY, SUBREPORT_DIR);
			params.put(Const.JASPER_PARAM_ETABLISSEMENT_KEY,
					sessionBean.getLlLatinEtablissement());
			byte[] bytes = impressionService
					.viewPDFWithDataSource(RESOURCE_PATH_TO_INPUT_FILE_JASPER,
							params, crossCollection);
			printMgrBean.imprimer(bytes, fileNamePeriode, "pdf");

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanBean.imprimerBilanAnnuel] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 16:37:06
	 */
	public void imprimerBilanAnnuel() {
		fillCrossTable(bilanAnnuelList);
		try {
			if (fileNameAnnuel == null) {
				loadFileNameAnnuel();
			}

			// NiveauDto _periode = niveauService.findById(niveauId);
			// AnneeAcademiqueDto _annee = anneeAcademiqueService
			// .findById(anneeAcademiqueId);
			// String name = "Bilan_Annuel_" + _oof.getOfLibelleLongFr() + "_"
			// + _periode.getLibelleLongAr() + "_" + _annee.getCode();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String SUBREPORT_DIR = facesContext.getExternalContext()
					.getRealPath("/WEB-INF/classes")
					+ "/jasper/examen/deliberation/";
			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = SUBREPORT_DIR
					+ "bilan.jrxml";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.JASPER_PARAM_IMG_PAPS_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logopaps.png");
			params.put(Const.JASPER_PARAM_IMG_LOGO_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logo"
					+ sessionBean.getCodeEtablissement() + ".png");
			if (ofLibelleLongFr != null) {
				params.put(Const.JASPER_PARAM_OFFRE_FORMATION_KEY,
						ofLibelleLongFr);
			}
			if (anneeCode != null) {
				params.put(Const.JASPER_PARAM_ANNEE_ACADEMIQUE_KEY, anneeCode);
			}
			if (niveauLibelleLongFr != null) {
				params.put(Const.JASPER_PARAM_PERIODE_KEY, niveauLibelleLongFr);
			}
			if (!deliberationClotured) {
				params.put(Const.JASPER_PARAM_INTITULE_KEY, bilanBundle
						.getString("bilan_reporting_bien_provisoire_text"));
			}
			params.put(Const.JASPER_PARAM_TITLE_KEY, "BILAN ANNUEL");
			params.put(Const.JASPER_PARAM_ETABLISSEMENT_KEY,
					sessionBean.getLlLatinEtablissement());
			params.put(Const.JASPER_PARAM_SUBREPORT_DIR_KEY, SUBREPORT_DIR);
			byte[] bytes = impressionService
					.viewPDFWithDataSource(RESOURCE_PATH_TO_INPUT_FILE_JASPER,
							params, crossCollection);
			printMgrBean.imprimer(bytes, fileNameAnnuel, "pdf");

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanBean.fillCrossTable] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 15:53:55
	 */
	public void fillCrossTable(List<BilanSessionDto> _bilanSessionList) {
		if (_bilanSessionList == null) {
			return;
		}
		crossCollection = new ArrayList<CrossTab>();
		Integer i = 1;
		for (BilanSessionDto _bilan : _bilanSessionList) {
			CrossTab _cross = new CrossTab();
			_cross.setColumn(bilanBundle
					.getString("bilan_reporting_column_numero_inscription"));
			_cross.setRow(i.toString());
			_cross.setValue(_bilan.getNumeroInscriptionEtudiant());
			crossCollection.add(_cross);
			_cross = new CrossTab();
			_cross.setColumn(bilanBundle
					.getString("bilan_reporting_column_nom"));
			_cross.setRow(i.toString());
			_cross.setValue(_bilan.getNomLatinEtudiant());
			crossCollection.add(_cross);

			_cross = new CrossTab();
			_cross.setColumn(bilanBundle
					.getString("bilan_reporting_column_prenom"));
			_cross.setRow(i.toString());
			_cross.setValue(_bilan.getPrenomLatinEtudiant());
			crossCollection.add(_cross);

			_cross = new CrossTab();
			_cross.setColumn(bilanBundle
					.getString("bilan_reporting_column_date_naissance"));
			_cross.setRow(i.toString());
			if (_bilan.getDateNaissanceEtudiant() == null) {
				_cross.setValue("-");
			} else {
				_cross.setValue(Utility.formatDate(
						_bilan.getDateNaissanceEtudiant(), "dd/MM/yyyy"));
			}
			crossCollection.add(_cross);

			List<BilanSessionDto> sessions = _bilan.getBilanSessionDtos();

			if (sessions != null && sessions.size() > 1) {
				for (BilanSessionDto session : sessions) {
					if (session.getColumnIntitule() != null) {
						_cross = new CrossTab();
						_cross.setColumn(session.getColumnIntitule());
						_cross.setRow(i.toString());
						_cross.setValue(Utility.formatNote(session.getMoyenne()));
						crossCollection.add(_cross);
					}
				}
			}
			_cross = new CrossTab();
			_cross.setColumn(bilanBundle
					.getString("bilan_reporting_column_moyenne_generale"));
			_cross.setRow(i.toString());
			_cross.setValue(Utility.formatNote(_bilan.getMoyenneGenerale()));
			crossCollection.add(_cross);
			_cross = new CrossTab();
			_cross.setColumn(bilanBundle
					.getString("bilan_reporting_column_mention"));
			_cross.setRow(i.toString());
			_cross.setValue(_bilan.getMentionLibelle());
			crossCollection.add(_cross);

			String libelle = _bilan.getTypeDecisionLibelleFr();
			if (libelle == null) {

				libelle = getDecisonLibelle(_bilan.getTypeDecisionId());
			}
			if (libelle != null) {
				_cross = new CrossTab();
				_cross.setColumn(bilanBundle
						.getString("bilan_reporting_column_decision"));
				_cross.setRow(i.toString());
				_cross.setValue(libelle);
				crossCollection.add(_cross);
			}

			i++;
		}
		// System.out.println(crossCollection);

	}

	/**
	 * [BilanBean.getDecisonPeriodeLibelle] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 nov. 2014 13:02:03
	 * @param id
	 * @return
	 */
	public String getDecisonLibelle(Integer id) {
		if (id != null && ncTypeDecisonAnnuelList != null) {
			for (NomenclatureDto nc : ncTypeDecisonAnnuelList) {
				if (nc.getId().equals(id)) {
					return nc.getLibelleLongFr();
				}
			}
		}
		return null;
	}

	/**
	 * [BilanBean.setDecisonLibelle] Method
	 * 
	 * @author MAKERRI Sofiane on : 10 nov. 2014 11:32:20
	 * @param _bilan
	 */
	public void setDecisonLibelle(BilanSessionDto _bilan) {
		if (_bilan.getTypeDecisionLibelleFr() == null) {
			// if (_bilan != null && _bilan.getTypeDecisionId() != null &&
			// ncTypeDecisonPeriodeList != null) {
			// for (NomenclatureDto nc : ncTypeDecisonPeriodeList) {
			// if (nc.getId().equals(_bilan.getTypeDecisionId())) {
			// _bilan.setTypeDecisionLibelleFr(nc.getLibelleLongFr());
			// return;
			// }
			// }
			// }
			if (_bilan != null && _bilan.getTypeDecisionId() != null
					&& ncTypeDecisonAnnuelList != null) {
				for (NomenclatureDto nc : ncTypeDecisonAnnuelList) {
					if (nc.getId().equals(_bilan.getTypeDecisionId())) {
						_bilan.setTypeDecisionLibelleFr(nc.getLibelleLongFr());
						return;
					}
				}
			}
		}

	}

	/**
	 * [BilanBean.loadNcDecisionPassageDette] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 déc. 2014 15:16:22
	 */
	private void loadNcDecisionPassageDette() {
		if (ncTypeDecisonAnnuelList != null
				&& ncDecisionPassageAvecDette.getId() == null) {
			for (NomenclatureDto nc : ncTypeDecisonAnnuelList) {
				if (nc.getCode().equals(
						Const.NOTATION_DECISION_JURY_PASSAGE_AVEC_DETTE_CODE)) {
					ncDecisionPassageAvecDette = nc;
					return;
				}
			}
		}
	}

	/**
	 * [BilanBean.getDecisonAnnuelLibelle] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 nov. 2014 13:02:25
	 * @param id
	 * @return
	 */
	public String getDecisonAnnuelLibelle(Integer id) {
		if (id != null && ncTypeDecisonAnnuelList != null) {
			for (NomenclatureDto nc : ncTypeDecisonAnnuelList) {
				if (nc.getId().equals(id)) {
					return nc.getLibelleLongFr();
				}
			}
		}
		return null;
	}

	/**
	 * [BilanBean.fillCollection] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 oct. 2014 12:15:44
	 */
	public void fillCollection(List<BilanSessionDto> rnList) {
		for (BilanSessionDto releve : rnList) {
			List<BilanSessionDto> periodeList = releve.getBilanSessionDtos();
			if (periodeList != null) {
				for (BilanSessionDto periode : periodeList) {
					List<CrossTab> collection = new ArrayList<CrossTab>();
					List<BilanUeDto> ues = periode.getBilanUes();
					List<BilanSessionDto> sessions = periode.getSessions();

					if (sessions == null) {
						/***** une seule session *****/
						fillWithOneSession(collection, ues);
					} else {
						fillWithAllSession(collection, periode);
					}
					periode.setCollection(collection);
				}
			}
		}

	}

	/**
	 * [BilanBean.fillWithOneSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 nov. 2014 11:00:31
	 * @param ues
	 */
	private void fillWithOneSession(List<CrossTab> collection,
			List<BilanUeDto> ues) {
		if (ues != null) {
			for (BilanUeDto ue : ues) {
				CrossTab _cross = new CrossTab();
				String row = ue.getUeCode() + ":" + ue.getUeLibelleFr();
				_cross = new CrossTab();
				_cross.setColumn(bilanBundle
						.getString("releve_note_reporting_column_nature_ue"));
				_cross.setRow(row);
				_cross.setValue(ue.getUeNatureLlFr());
				collection.add(_cross);
				_cross = new CrossTab();
				_cross.setColumn(bilanBundle
						.getString("releve_note_reporting_column_coefficient"));
				_cross.setRow(row);
				_cross.setValue(Utility.formatNoteWith2Position(ue
						.getCoefficient()));
				collection.add(_cross);
				_cross = new CrossTab();
				_cross.setColumn(bilanBundle
						.getString("releve_note_reporting_column_credit"));
				_cross.setRow(row);
				_cross.setValue(Utility.formatNoteWith2Position(ue.getCredit()));
				collection.add(_cross);

				_cross = new CrossTab();
				_cross.setColumn(bilanBundle
						.getString("releve_note_reporting_column_moyenne"));
				_cross.setRow(row);
				_cross.setValue(Utility.formatNoteWith2Position(ue.getMoyenne()));
				collection.add(_cross);

				_cross = new CrossTab();
				_cross.setColumn(bilanBundle
						.getString("releve_note_reporting_column_credit_obtenu"));
				_cross.setRow(row);
				_cross.setValue(Utility.formatNoteWith2Position(ue
						.getCreditObtenu()));
				collection.add(_cross);
				List<BilanMcDto> mcs = ue.getBilanMcs();
				if (mcs != null) {
					for (BilanMcDto mc : mcs) {
						row = "      " + mc.getMcLibelleFr();
						_cross = new CrossTab();
						_cross.setColumn(bilanBundle
								.getString("releve_note_reporting_column_nature_ue"));
						_cross.setRow(row);
						_cross.setValue("");
						collection.add(_cross);
						_cross = new CrossTab();
						_cross.setColumn(bilanBundle
								.getString("releve_note_reporting_column_coefficient"));
						_cross.setRow(row);
						_cross.setValue(Utility.formatNoteWith2Position(mc
								.getCoefficient()));
						collection.add(_cross);
						_cross = new CrossTab();
						_cross.setColumn(bilanBundle
								.getString("releve_note_reporting_column_credit"));
						_cross.setRow(row);
						_cross.setValue(Utility.formatNoteWith2Position(mc
								.getCredit()));
						collection.add(_cross);

						_cross = new CrossTab();
						_cross.setColumn(bilanBundle
								.getString("releve_note_reporting_column_moyenne"));
						_cross.setRow(row);
						_cross.setValue(Utility.formatNoteWith2Position(mc
								.getMoyenneGenerale()));
						collection.add(_cross);

						collection.add(_cross);
						_cross = new CrossTab();
						_cross.setColumn(bilanBundle
								.getString("releve_note_reporting_column_credit_obtenu"));
						_cross.setRow(row);
						_cross.setValue(Utility.formatNoteWith2Position(mc
								.getCreditObtenu()));
						collection.add(_cross);

					}
				}

			}
		}
	}

	/**
	 * [BilanBean.fillWithAllSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 nov. 2014 11:13:11
	 * @param collection
	 * @param sessions
	 */
	private void fillWithAllSession(List<CrossTab> collection,
			BilanSessionDto periode) {
		List<BilanSessionDto> sessions = periode.getSessions();
		List<BilanUeDto> ues = periode.getBilanUes();
		if (ues != null) {
			for (BilanUeDto ue : ues) {
				CrossTab _cross = new CrossTab();
				String row = ue.getUeCode() + ":" + ue.getUeLibelleFr();
				_cross = new CrossTab();
				_cross.setColumn(bilanBundle
						.getString("releve_note_reporting_column_nature_ue"));
				_cross.setRow(row);
				_cross.setValue(ue.getUeNatureLlFr());
				collection.add(_cross);
				_cross = new CrossTab();
				_cross.setColumn(bilanBundle
						.getString("releve_note_reporting_column_coefficient"));
				_cross.setRow(row);
				_cross.setValue(Utility.formatNoteWith2Position(ue
						.getCoefficient()));
				collection.add(_cross);
				_cross = new CrossTab();
				_cross.setColumn(bilanBundle
						.getString("releve_note_reporting_column_credit"));
				_cross.setRow(row);
				_cross.setValue(Utility.formatNoteWith2Position(ue.getCredit()));
				collection.add(_cross);
				if (sessions != null) {
					for (BilanSessionDto session : sessions) {
						List<BilanUeDto> uesSession = session.getBilanUes();
						if (uesSession != null) {
							for (BilanUeDto ueSession : uesSession) {
								if (ueSession.getUeCode()
										.equals(ue.getUeCode())) {
									_cross = new CrossTab();
									_cross.setColumn(session.getPsIntitule());
									_cross.setRow(row);
									_cross.setValue(Utility
											.formatNoteWith2Position(ueSession
													.getMoyenne()));
									collection.add(_cross);
									break;
								}
							}
						}
					}
				}
				_cross = new CrossTab();
				_cross.setColumn(bilanBundle
						.getString("releve_note_reporting_column_credit_obtenu"));
				_cross.setRow(row);
				_cross.setValue(Utility.formatNoteWith2Position(ue
						.getCreditObtenu()));
				collection.add(_cross);
				List<BilanMcDto> mcs = ue.getBilanMcs();
				if (mcs != null) {
					for (BilanMcDto mc : mcs) {
						row = "      " + mc.getMcLibelleFr();
						_cross = new CrossTab();
						_cross.setColumn(bilanBundle
								.getString("releve_note_reporting_column_nature_ue"));
						_cross.setRow(row);
						_cross.setValue("");
						collection.add(_cross);
						_cross = new CrossTab();
						_cross.setColumn(bilanBundle
								.getString("releve_note_reporting_column_coefficient"));
						_cross.setRow(row);
						_cross.setValue(Utility.formatNoteWith2Position(mc
								.getCoefficient()));
						collection.add(_cross);
						_cross = new CrossTab();
						_cross.setColumn(bilanBundle
								.getString("releve_note_reporting_column_credit"));
						_cross.setRow(row);
						_cross.setValue(Utility.formatNoteWith2Position(mc
								.getCredit()));
						collection.add(_cross);

						if (sessions != null) {
							for (BilanSessionDto session : sessions) {
								List<BilanUeDto> uesSession = session
										.getBilanUes();
								if (uesSession != null) {
									for (BilanUeDto ueSession : uesSession) {
										if (ueSession.getUeCode().equals(
												ue.getUeCode())) {
											List<BilanMcDto> mcsSession = ueSession
													.getBilanMcs();
											if (mcsSession != null) {
												for (BilanMcDto mcSession : mcsSession) {
													if (mcSession
															.getRattachementMcId()
															.equals(mc
																	.getRattachementMcId())) {
														_cross = new CrossTab();
														_cross.setColumn(session
																.getPsIntitule());
														_cross.setRow(row);
														_cross.setValue(Utility
																.formatNoteWith2Position(mcSession
																		.getMoyenneGenerale()));
														collection.add(_cross);
														break;
													}
												}
											}

										}
									}
								}
							}
						}
						collection.add(_cross);
						_cross = new CrossTab();
						_cross.setColumn(bilanBundle
								.getString("releve_note_reporting_column_credit_obtenu"));
						_cross.setRow(row);
						_cross.setValue(Utility.formatNoteWith2Position(mc
								.getCreditObtenu()));
						collection.add(_cross);

					}
				}

			}
		}

	}

	/**
	 * [BilanBean.bilanPeriodeClotured] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 13:22:43
	 * @return the bilanPeriodeClotured
	 */
	public boolean isBilanPeriodeClotured() {
		return bilanPeriodeClotured;
	}

	/**
	 * [BilanBean.bilanPeriodeClotured] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 13:22:43
	 * @param bilanPeriodeClotured
	 *            the bilanPeriodeClotured to set
	 */
	public void setBilanPeriodeClotured(boolean bilanPeriodeClotured) {
		this.bilanPeriodeClotured = bilanPeriodeClotured;
	}

	/**
	 * [BilanBean.loadDecisonJury] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 15:01:36
	 */
	public void loadDecisonJuryItem() {
		if (ncTypeDecisonList == null || ncTypeDecisonList.isEmpty()) {
			ncTypeDecisonList = utilBean.loadTypeDecisionAdmission();
		}
	}

	/**
	 * [BilanBean.loadNcDecisonAnnuel] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 nov. 2014 13:04:21
	 */
	public void loadDecisonJuryAnnuel() {
		if (ncTypeDecisonAnnuelList == null
				|| ncTypeDecisonAnnuelList.isEmpty()) {
			ncTypeDecisonAnnuelList = utilBean.loadTypeDecisionListAnnuel();
		}
	}

	/**
	 * [BilanBean.loadDecisonJuryPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 17:13:48
	 */
	// public void loadDecisonJuryPeriodeItem() {
	// if (ncTypeDecisonList == null || ncTypeDecisonList.isEmpty()) {
	// ncTypeDecisonList = utilBean.loadTypeDecisionAdmissionPeriode();
	// }
	// }

	/**
	 * [BilanBean.loadAdmisDecison] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 15:10:43
	 */
	public void loadAdmisDecisonAnnuel() {
		if (decisionAdmisAnnuel == null || decisionAdmisAnnuel.getId() == null) {
			decisionAdmisAnnuel = utilBean.loadAdmisDecisonAnnuel();
		}

	}

	/**
	 * [BilanBean.ncTypeDecisonList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 15:01:33
	 * @return the ncTypeDecisonList
	 */
	public List<SelectItem> getNcTypeDecisonList() {
		return ncTypeDecisonList;
	}

	/**
	 * [BilanBean.ncTypeDecisonList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 15:01:33
	 * @param ncTypeDecisonList
	 *            the ncTypeDecisonList to set
	 */
	public void setNcTypeDecisonList(List<SelectItem> ncTypeDecisonList) {
		this.ncTypeDecisonList = ncTypeDecisonList;
	}

	/**
	 * [BilanBean.decisionAdmisAnnuel] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 14:16:54
	 * @return the decisionAdmisAnnuel
	 */
	public NomenclatureDto getDecisionAdmisAnnuel() {
		return decisionAdmisAnnuel;
	}

	/**
	 * [BilanBean.decisionAdmisAnnuel] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 14:16:54
	 * @param decisionAdmisAnnuel
	 *            the decisionAdmisAnnuel to set
	 */
	public void setDecisionAdmisAnnuel(NomenclatureDto decisionAdmisAnnuel) {
		this.decisionAdmisAnnuel = decisionAdmisAnnuel;
	}

	/**
	 * [BilanBean.deliberationId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 15:42:08
	 * @return the deliberationId
	 */
	public Long getDeliberationId() {
		return deliberationId;
	}

	/**
	 * [BilanBean.deliberationId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 15:42:08
	 * @param deliberationId
	 *            the deliberationId to set
	 */
	public void setDeliberationId(Long deliberationId) {
		this.deliberationId = deliberationId;
	}

	/**
	 * [BilanBean.printMgrBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 15:51:12
	 * @return the printMgrBean
	 */
	public PrintMgrBean getPrintMgrBean() {
		return printMgrBean;
	}

	/**
	 * [BilanBean.printMgrBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 15:51:12
	 * @param printMgrBean
	 *            the printMgrBean to set
	 */
	public void setPrintMgrBean(PrintMgrBean printMgrBean) {
		this.printMgrBean = printMgrBean;
	}

	/**
	 * [BilanBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 15:56:06
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [BilanBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 15:56:06
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [BilanBean.periodeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 16:01:27
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [BilanBean.periodeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 16:01:27
	 * @param periodeService
	 *            the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [BilanBean.anneeAcademiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 16:01:27
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [BilanBean.anneeAcademiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 16:01:27
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [BilanBean.impressionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 16:01:27
	 * @return the impressionService
	 */
	public ImpressionService getImpressionService() {
		return impressionService;
	}

	/**
	 * [BilanBean.impressionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 16:01:27
	 * @param impressionService
	 *            the impressionService to set
	 */
	public void setImpressionService(ImpressionService impressionService) {
		this.impressionService = impressionService;
	}

	/**
	 * [BilanBean.crossCollection] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 16:17:43
	 * @return the crossCollection
	 */
	public List<CrossTab> getCrossCollection() {
		return crossCollection;
	}

	/**
	 * [BilanBean.crossCollection] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 16:17:43
	 * @param crossCollection
	 *            the crossCollection to set
	 */
	public void setCrossCollection(List<CrossTab> crossCollection) {
		this.crossCollection = crossCollection;
	}

	/**
	 * [BilanBean.bilanAnnuelClotured] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 16:17:37
	 * @return the bilanAnnuelClotured
	 */
	public boolean isBilanAnnuelClotured() {
		return bilanAnnuelClotured;
	}

	/**
	 * [BilanBean.bilanAnnuelClotured] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 16:17:37
	 * @param bilanAnnuelClotured
	 *            the bilanAnnuelClotured to set
	 */
	public void setBilanAnnuelClotured(boolean bilanAnnuelClotured) {
		this.bilanAnnuelClotured = bilanAnnuelClotured;
	}

	/**
	 * [BilanBean.niveauService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 16:39:25
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [BilanBean.niveauService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 16:39:25
	 * @param niveauService
	 *            the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [BilanBean.situationCree] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 17:25:06
	 * @return the situationCree
	 */
	public SituationEntiteDto getSituationCree() {
		return situationCree;
	}

	/**
	 * [BilanBean.situationCree] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 17:25:06
	 * @param situationCree
	 *            the situationCree to set
	 */
	public void setSituationCree(SituationEntiteDto situationCree) {
		this.situationCree = situationCree;
	}

	/**
	 * [BilanBean.selectedBilan] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 09:48:26
	 * @return the selectedBilan
	 */
	public BilanSessionDto getSelectedBilan() {
		return selectedBilan;
	}

	/**
	 * [BilanBean.selectedBilan] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 09:48:26
	 * @param selectedBilan
	 *            the selectedBilan to set
	 */
	public void setSelectedBilan(BilanSessionDto selectedBilan) {
		this.selectedBilan = selectedBilan;
	}

	/**
	 * [BilanBean.onRowSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 09:53:53
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		this.selectedBilan = ((BilanSessionDto) event.getObject());
		selectedBilanList = new ArrayList<BilanSessionDto>();
		selectedBilanList.add(selectedBilan);
		// RequestContext.getCurrentInstance().execute("PF('releveNote').show()");
	}

	/**
	 * [BilanBean.bilanFinalList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 09:56:30
	 * @return the bilanFinalList
	 */
	// public List<BilanSessionDto> getBilanFinalList() {
	// return bilanFinalList;
	// }

	/**
	 * [BilanBean.bilanFinalList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 09:56:30
	 * @param bilanFinalList
	 *            the bilanFinalList to set
	 */
	// public void setBilanFinalList(List<BilanSessionDto> bilanFinalList) {
	// this.bilanFinalList = bilanFinalList;
	// }

	/**
	 * [BilanBean.releveNoteList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 11:05:06
	 * @return the releveNoteList
	 */
	public List<BilanSessionDto> getReleveNoteList() {
		return releveNoteList;
	}

	/**
	 * [BilanBean.releveNoteList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 11:05:06
	 * @param releveNoteList
	 *            the releveNoteList to set
	 */
	public void setReleveNoteList(List<BilanSessionDto> releveNoteList) {
		this.releveNoteList = releveNoteList;
	}

	/**
	 * [BilanBean.bilanUeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 11:11:15
	 * @return the bilanUeList
	 */
	public List<BilanUeDto> getBilanUeList() {
		return bilanUeList;
	}

	/**
	 * [BilanBean.bilanUeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 11:11:15
	 * @param bilanUeList
	 *            the bilanUeList to set
	 */
	public void setBilanUeList(List<BilanUeDto> bilanUeList) {
		this.bilanUeList = bilanUeList;
	}

	/**
	 * [BilanBean.bilanMcList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 11:11:15
	 * @return the bilanMcList
	 */
	public List<BilanMcDto> getBilanMcList() {
		return bilanMcList;
	}

	/**
	 * [BilanBean.bilanMcList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 11:11:15
	 * @param bilanMcList
	 *            the bilanMcList to set
	 */
	public void setBilanMcList(List<BilanMcDto> bilanMcList) {
		this.bilanMcList = bilanMcList;
	}

	/**
	 * [BilanBean.bilanUeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 11:12:14
	 * @return the bilanUeService
	 */
	public BilanUeService getBilanUeService() {
		return bilanUeService;
	}

	/**
	 * [BilanBean.bilanUeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 11:12:14
	 * @param bilanUeService
	 *            the bilanUeService to set
	 */
	public void setBilanUeService(BilanUeService bilanUeService) {
		this.bilanUeService = bilanUeService;
	}

	/**
	 * [BilanBean.bilanMcService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 11:12:14
	 * @return the bilanMcService
	 */
	public BilanMcService getBilanMcService() {
		return bilanMcService;
	}

	/**
	 * [BilanBean.bilanMcService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 11:12:14
	 * @param bilanMcService
	 *            the bilanMcService to set
	 */
	public void setBilanMcService(BilanMcService bilanMcService) {
		this.bilanMcService = bilanMcService;
	}

	/**
	 * [BilanBean.releve] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 11:39:22
	 * @return the releve
	 */
	public boolean isReleve() {
		return releve;
	}

	/**
	 * [BilanBean.releve] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 11:39:22
	 * @param releve
	 *            the releve to set
	 */
	public void setReleve(boolean releve) {
		this.releve = releve;
	}

	/**
	 * [BilanBean.selectedBilanList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 12:21:07
	 * @return the selectedBilanList
	 */
	public List<BilanSessionDto> getSelectedBilanList() {
		return selectedBilanList;
	}

	/**
	 * [BilanBean.selectedBilanList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 12:21:07
	 * @param selectedBilanList
	 *            the selectedBilanList to set
	 */
	public void setSelectedBilanList(List<BilanSessionDto> selectedBilanList) {
		this.selectedBilanList = selectedBilanList;
	}

	/**
	 * [BilanBean.fileNamePeriode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 16:34:34
	 * @return the fileNamePeriode
	 */
	public String getFileNamePeriode() {
		if (fileNamePeriode == null) {
			loadFileNamePeriode();
		}
		return fileNamePeriode;
	}

	/**
	 * [BilanBean.fileNamePeriode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 16:34:34
	 * @param fileNamePeriode
	 *            the fileNamePeriode to set
	 */
	public void setFileNamePeriode(String fileNamePeriode) {
		this.fileNamePeriode = fileNamePeriode;
	}

	/**
	 * [BilanBean.ofLibelleLongFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 16:29:46
	 * @return the ofLibelleLongFr
	 */
	public String getOfLibelleLongFr() {
		return ofLibelleLongFr;
	}

	/**
	 * [BilanBean.ofLibelleLongFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 16:29:46
	 * @param ofLibelleLongFr
	 *            the ofLibelleLongFr to set
	 */
	public void setOfLibelleLongFr(String ofLibelleLongFr) {
		this.ofLibelleLongFr = ofLibelleLongFr;
	}

	/**
	 * [BilanBean.periodeLibelleLongFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 16:29:46
	 * @return the periodeLibelleLongFr
	 */
	public String getPeriodeLibelleLongFr() {
		return periodeLibelleLongFr;
	}

	/**
	 * [BilanBean.periodeLibelleLongFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 16:29:46
	 * @param periodeLibelleLongFr
	 *            the periodeLibelleLongFr to set
	 */
	public void setPeriodeLibelleLongFr(String periodeLibelleLongFr) {
		this.periodeLibelleLongFr = periodeLibelleLongFr;
	}

	/**
	 * [BilanBean.anneeCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 16:29:46
	 * @return the anneeCode
	 */
	public String getAnneeCode() {
		return anneeCode;
	}

	/**
	 * [BilanBean.anneeCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 16:29:46
	 * @param anneeCode
	 *            the anneeCode to set
	 */
	public void setAnneeCode(String anneeCode) {
		this.anneeCode = anneeCode;
	}

	/**
	 * [BilanBean.fileNameAnnuel] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 16:36:49
	 * @return the fileNameAnnuel
	 */
	public String getFileNameAnnuel() {
		if (fileNameAnnuel == null) {
			loadFileNameAnnuel();
		}
		return fileNameAnnuel;
	}

	/**
	 * [BilanBean.fileNameAnnuel] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 16:36:49
	 * @param fileNameAnnuel
	 *            the fileNameAnnuel to set
	 */
	public void setFileNameAnnuel(String fileNameAnnuel) {
		this.fileNameAnnuel = fileNameAnnuel;
	}

	/**
	 * [BilanBean.niveauLibelleLongFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 16:36:49
	 * @return the niveauLibelleLongFr
	 */
	public String getNiveauLibelleLongFr() {
		return niveauLibelleLongFr;
	}

	/**
	 * [BilanBean.niveauLibelleLongFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 16:36:49
	 * @param niveauLibelleLongFr
	 *            the niveauLibelleLongFr to set
	 */
	public void setNiveauLibelleLongFr(String niveauLibelleLongFr) {
		this.niveauLibelleLongFr = niveauLibelleLongFr;
	}

	/**
	 * [BilanBean.imprimerReleveNote] Method
	 * 
	 * @author MAKERRI Sofiane on : 29 oct. 2014 12:32:35
	 */
	public void imprimerReleveNote() {
		try {
			if (fileNameReleve == null) {
				loadFileNameReleve();
			}
			printReleveNote(releveNoteList, fileNameReleve);

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanBean.printSelectedBilan] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 oct. 2014 15:30:45
	 */
	public void printSelectedBilan() {
		try {
			if (selectedBilan != null) {
				if (fileNameReleve == null) {
					loadFileNameReleve();
				}
				List<BilanSessionDto> selected = new ArrayList<BilanSessionDto>();
				selected.add(selectedBilan);
				if (selectedBilan != null) {
					String filaName = "Releve_note_"
							+ selectedBilan.getNomLatinEtudiant() + "_"
							+ selectedBilan.getPrenomLatinEtudiant();
					printReleveNote(selected, filaName);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanBean.printReleveNote] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 oct. 2014 15:29:16
	 * @param collection
	 */
	public void printReleveNote(List<BilanSessionDto> collection,
			String filaneName) {
		try {

			fillCollection(collection);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String SUBREPORT_DIR = facesContext.getExternalContext()
					.getRealPath("/WEB-INF/classes")
					+ "/jasper/examen/deliberation/";
			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = SUBREPORT_DIR
					+ "releve_note.jrxml";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.JASPER_PARAM_IMG_PAPS_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logopaps.png");
			params.put(Const.JASPER_PARAM_IMG_LOGO_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logo"
					+ sessionBean.getCodeEtablissement() + ".png");
			params.put(Const.JASPER_PARAM_OFFRE_FORMATION_KEY, ofLibelleLongFr);
			params.put(Const.JASPER_PARAM_ANNEE_ACADEMIQUE_KEY, anneeCode);
			params.put(Const.JASPER_PARAM_PERIODE_KEY, periodeLibelleLongFr);
			params.put(Const.JASPER_PARAM_NIVEAU_KEY, niveauCode);
			params.put(Const.JASPER_PARAM_CYCLE_KEY, cycleLibelleLongFr);
			params.put(Const.JASPER_PARAM_ETABLISSEMENT_KEY,
					sessionBean.getLlLatinEtablissement());
			params.put(Const.JASPER_PARAM_SUBREPORT_DIR_KEY, SUBREPORT_DIR);
			params.put(Const.JASPER_PARAM_IS_BILAN_ANNUEL_KEY, (periodeId == null ? true : false));

			byte[] bytes = impressionService.viewPDFWithDataSource(
					RESOURCE_PATH_TO_INPUT_FILE_JASPER, params, collection);
			printMgrBean.imprimer(bytes, filaneName, "pdf");

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanBean.fileNameReleve] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 oct. 2014 13:59:06
	 * @return the fileNameReleve
	 */
	public String getFileNameReleve() {
		return fileNameReleve;
	}

	/**
	 * [BilanBean.fileNameReleve] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 oct. 2014 13:59:06
	 * @param fileNameReleve
	 *            the fileNameReleve to set
	 */
	public void setFileNameReleve(String fileNameReleve) {
		this.fileNameReleve = fileNameReleve;
	}

	/**
	 * [BilanBean.setDecisionAnnuel] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 08:19:34
	 * @param bilan
	 */
	private void setDecisionAnnuel(BilanSessionDto bilan) {
		BilanSessionDto _bilanSessionDto = bilanSessionService
				.findUniqueAnnuel(deliberationId,
						bilan.getDossierInscriptionAdministrativeId());
		if (_bilanSessionDto != null) {
			bilan.setId(_bilanSessionDto.getId());
		}

		if (bilan.getMoyenneGenerale() >= 10) {
			if (decisionAdmisAnnuel != null) {
				bilan.setTypeDecisionLibelleFr(decisionAdmisAnnuel
						.getLibelleLongFr());
				bilan.setTypeDecisionId(decisionAdmisAnnuel.getId());
				bilan.setStyleClass(Const.NOTATION_DECISION_JURY_PASSAGE_SANS_DETTE_STYLE_CLASS);
				return;
			}

		} else {
			if (deliberationId != null
					&& bilan.getDossierInscriptionAdministrativeId() != null) {

				if (_bilanSessionDto != null) {
					bilan.setTypeDecisionId(_bilanSessionDto
							.getTypeDecisionId());
					bilan.setTypeDecisionLibelleFr(_bilanSessionDto
							.getTypeDecisionLibelleFr());

				}
			}
		}
	}

	/**
	 * [BilanBean.dossierInscriptionAdministrativeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 nov. 2014 12:45:23
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [BilanBean.dossierInscriptionAdministrativeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 nov. 2014 12:45:23
	 * @param dossierInscriptionAdministrativeService
	 *            the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [BilanBean.ncTypeDecisonAnnuelList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 nov. 2014 13:00:45
	 * @return the ncTypeDecisonAnnuelList
	 */
	public List<NomenclatureDto> getNcTypeDecisonAnnuelList() {
		return ncTypeDecisonAnnuelList;
	}

	/**
	 * [BilanBean.ncTypeDecisonAnnuelList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 nov. 2014 13:00:45
	 * @param ncTypeDecisonAnnuelList
	 *            the ncTypeDecisonAnnuelList to set
	 */
	public void setNcTypeDecisonAnnuelList(
			List<NomenclatureDto> ncTypeDecisonAnnuelList) {
		this.ncTypeDecisonAnnuelList = ncTypeDecisonAnnuelList;
	}

	/**
	 * [BilanBean.ncTypeDecisonPeriodeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 nov. 2014 13:00:45
	 * @return the ncTypeDecisonPeriodeList
	 */
	public List<NomenclatureDto> getNcTypeDecisonPeriodeList() {
		return ncTypeDecisonPeriodeList;
	}

	/**
	 * [BilanBean.ncTypeDecisonPeriodeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 nov. 2014 13:00:45
	 * @param ncTypeDecisonPeriodeList
	 *            the ncTypeDecisonPeriodeList to set
	 */
	public void setNcTypeDecisonPeriodeList(
			List<NomenclatureDto> ncTypeDecisonPeriodeList) {
		this.ncTypeDecisonPeriodeList = ncTypeDecisonPeriodeList;
	}

	/**
	 * [BilanBean.deliberationClosed] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 nov. 2014 08:29:02
	 * @return the deliberationClosed
	 */
	public boolean isDeliberationClosed() {
		return deliberationClosed;
	}

	/**
	 * [BilanBean.deliberationClosed] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 nov. 2014 08:29:02
	 * @param deliberationClosed
	 *            the deliberationClosed to set
	 */
	public void setDeliberationClosed(boolean deliberationClosed) {
		this.deliberationClosed = deliberationClosed;
	}

	/**
	 * [BilanBean.niveauCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 nov. 2014 15:22:44
	 * @return the niveauCode
	 */
	public String getNiveauCode() {
		return niveauCode;
	}

	/**
	 * [BilanBean.niveauCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 nov. 2014 15:22:44
	 * @param niveauCode
	 *            the niveauCode to set
	 */
	public void setNiveauCode(String niveauCode) {
		this.niveauCode = niveauCode;
	}

	/**
	 * [BilanBean.cycleLibelleLongFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 nov. 2014 15:29:07
	 * @return the cycleLibelleLongFr
	 */
	public String getCycleLibelleLongFr() {
		return cycleLibelleLongFr;
	}

	/**
	 * [BilanBean.cycleLibelleLongFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 nov. 2014 15:29:07
	 * @param cycleLibelleLongFr
	 *            the cycleLibelleLongFr to set
	 */
	public void setCycleLibelleLongFr(String cycleLibelleLongFr) {
		this.cycleLibelleLongFr = cycleLibelleLongFr;
	}

	/**
	 * [BilanBean.loadUeMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 déc. 2014 16:39:19
	 */
	public void loadUeMc() {
		if (ueList == null || ueList.isEmpty()) {
			ueList = new ArrayList<RepartitionUeDto>();
			mcList = new ArrayList<RattachementMcDto>();
			ueList = repartitionUeService.findReparationUe(oofId, periodeId);
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

	public List<RattachementMcDto> findMc(int ueId) {
		List<RattachementMcDto> result = new ArrayList<RattachementMcDto>();
		for (RattachementMcDto rmc : mcList) {
			if (rmc.getUeId() != null && rmc.getUeId().equals(ueId)) {
				result.add(rmc);
			}
		}
		return result;
	}

	public List<RattachementMcDto> findUe(int ueId) {
		List<RattachementMcDto> result = new ArrayList<RattachementMcDto>();
		for (RattachementMcDto rmc : mcList) {
			if (rmc.getUeId() != null && rmc.getUeId().equals(ueId)) {
				result.add(rmc);
			}
		}
		return result;
	}

	/**
	 * [BilanBean.ueList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 déc. 2014 16:39:48
	 * @return the ueList
	 */
	public List<RepartitionUeDto> getUeList() {
		return ueList;
	}

	/**
	 * [BilanBean.ueList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 déc. 2014 16:39:48
	 * @param ueList
	 *            the ueList to set
	 */
	public void setUeList(List<RepartitionUeDto> ueList) {
		this.ueList = ueList;
	}

	/**
	 * [BilanBean.mcList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 déc. 2014 16:39:48
	 * @return the mcList
	 */
	public List<RattachementMcDto> getMcList() {
		return mcList;
	}

	/**
	 * [BilanBean.mcList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 déc. 2014 16:39:48
	 * @param mcList
	 *            the mcList to set
	 */
	public void setMcList(List<RattachementMcDto> mcList) {
		this.mcList = mcList;
	}

	/**
	 * [BilanBean.repartitionUeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 déc. 2014 16:40:18
	 * @return the repartitionUeService
	 */
	public RepartitionUeService getRepartitionUeService() {
		return repartitionUeService;
	}

	/**
	 * [BilanBean.repartitionUeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 déc. 2014 16:40:18
	 * @param repartitionUeService
	 *            the repartitionUeService to set
	 */
	public void setRepartitionUeService(
			RepartitionUeService repartitionUeService) {
		this.repartitionUeService = repartitionUeService;
	}

	/**
	 * [BilanBean.rattachementMcService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 déc. 2014 16:40:43
	 * @return the rattachementMcService
	 */
	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	/**
	 * [BilanBean.rattachementMcService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 déc. 2014 16:40:43
	 * @param rattachementMcService
	 *            the rattachementMcService to set
	 */
	public void setRattachementMcService(
			RattachementMcService rattachementMcService) {
		this.rattachementMcService = rattachementMcService;
	}

	/**
	 * [BilanBean.deliberationClotured] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 15:47:17
	 * @return the deliberationClotured
	 */
	public boolean isDeliberationClotured() {
		return deliberationClotured;
	}

	/**
	 * [BilanBean.deliberationClotured] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 15:47:17
	 * @param deliberationClotured
	 *            the deliberationClotured to set
	 */
	public void setDeliberationClotured(boolean deliberationClotured) {
		this.deliberationClotured = deliberationClotured;
	}

	/**
	 * [BilanBean.noteEtudiantModel] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 16:30:50
	 * @return the noteEtudiantModel
	 */
	public List<NoteEtudiantModel> getNoteEtudiantModel() {
		return noteEtudiantModel;
	}

	/**
	 * [BilanBean.noteEtudiantModel] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 16:30:50
	 * @param noteEtudiantModel
	 *            the noteEtudiantModel to set
	 */
	public void setNoteEtudiantModel(List<NoteEtudiantModel> noteEtudiantModel) {
		this.noteEtudiantModel = noteEtudiantModel;
	}

	/**
	 * [BilanBean.etudiants] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 16:30:50
	 * @return the etudiants
	 */
	public List<InscriptionExamenDto> getEtudiants() {
		return etudiants;
	}

	/**
	 * [BilanBean.etudiants] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 16:30:50
	 * @param etudiants
	 *            the etudiants to set
	 */
	public void setEtudiants(List<InscriptionExamenDto> etudiants) {
		this.etudiants = etudiants;
	}

	/**
	 * [BilanBean.notes] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 16:30:50
	 * @return the notes
	 */
	public List<InscriptionExamenDto> getNotes() {
		return notes;
	}

	/**
	 * [BilanBean.notes] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 16:30:50
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(List<InscriptionExamenDto> notes) {
		this.notes = notes;
	}

	/**
	 * [BilanBean.bilanSessionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 16:31:18
	 * @return the bilanSessionList
	 */
	public List<BilanSessionDto> getBilanSessionList() {
		return bilanSessionList;
	}

	/**
	 * [BilanBean.bilanSessionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 16:31:18
	 * @param bilanSessionList
	 *            the bilanSessionList to set
	 */
	public void setBilanSessionList(List<BilanSessionDto> bilanSessionList) {
		this.bilanSessionList = bilanSessionList;
	}

	/**
	 * [BilanBean.deliberation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 16:31:49
	 * @return the deliberation
	 */
	public List<LinkedHashMap<String, String>> getDeliberation() {
		return deliberation;
	}

	/**
	 * [BilanBean.deliberation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 16:31:49
	 * @param deliberation
	 *            the deliberation to set
	 */
	public void setDeliberation(List<LinkedHashMap<String, String>> deliberation) {
		this.deliberation = deliberation;
	}

	/**
	 * [BilanBean.line] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 16:31:49
	 * @return the line
	 */
	public LinkedHashMap<String, String> getLine() {
		return line;
	}

	/**
	 * [BilanBean.line] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 16:31:49
	 * @param line
	 *            the line to set
	 */
	public void setLine(LinkedHashMap<String, String> line) {
		this.line = line;
	}

	/**
	 * [BilanBean.inscriptionExamenService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 16:33:02
	 * @return the inscriptionExamenService
	 */
	public InscriptionExamenService getInscriptionExamenService() {
		return inscriptionExamenService;
	}

	/**
	 * [BilanBean.inscriptionExamenService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 16:33:02
	 * @param inscriptionExamenService
	 *            the inscriptionExamenService to set
	 */
	public void setInscriptionExamenService(
			InscriptionExamenService inscriptionExamenService) {
		this.inscriptionExamenService = inscriptionExamenService;
	}

	/**
	 * [BilanBean.deliberationBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 17:11:45
	 * @return the deliberationBean
	 */
	public DeliberationBean getDeliberationBean() {
		return deliberationBean;
	}

	/**
	 * [BilanBean.deliberationBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 déc. 2014 17:11:45
	 * @param deliberationBean
	 *            the deliberationBean to set
	 */
	public void setDeliberationBean(DeliberationBean deliberationBean) {
		this.deliberationBean = deliberationBean;
	}

	/**
	 * [BilanBean.niveauDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 déc. 2014 08:17:36
	 * @return the niveauDto
	 */
	public NiveauDto getNiveauDto() {
		return niveauDto;
	}

	/**
	 * [BilanBean.niveauDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 déc. 2014 08:17:36
	 * @param niveauDto
	 *            the niveauDto to set
	 */
	public void setNiveauDto(NiveauDto niveauDto) {
		this.niveauDto = niveauDto;
	}

	/**
	 * [BilanBean.ncDecisionPassageAvecDette] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 déc. 2014 15:21:17
	 * @return the ncDecisionPassageAvecDette
	 */
	public NomenclatureDto getNcDecisionPassageAvecDette() {
		return ncDecisionPassageAvecDette;
	}

	/**
	 * [BilanBean.ncDecisionPassageAvecDette] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 déc. 2014 15:21:17
	 * @param ncDecisionPassageAvecDette
	 *            the ncDecisionPassageAvecDette to set
	 */
	public void setNcDecisionPassageAvecDette(
			NomenclatureDto ncDecisionPassageAvecDette) {
		this.ncDecisionPassageAvecDette = ncDecisionPassageAvecDette;
	}

	/**
	 * [BilanBean.inscriptionAdministrativeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 déc. 2014 11:08:12
	 * @return the inscriptionAdministrativeList
	 */
	public List<DossierInscriptionAdministrativeDto> getInscriptionAdministrativeList() {
		return inscriptionAdministrativeList;
	}

	/**
	 * [BilanBean.inscriptionAdministrativeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 déc. 2014 11:08:12
	 * @param inscriptionAdministrativeList
	 *            the inscriptionAdministrativeList to set
	 */
	public void setInscriptionAdministrativeList(
			List<DossierInscriptionAdministrativeDto> inscriptionAdministrativeList) {
		this.inscriptionAdministrativeList = inscriptionAdministrativeList;
	}

	/**
	 * [BilanBean.dossierEtudiantService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 déc. 2014 11:11:27
	 * @return the dossierEtudiantService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [BilanBean.dossierEtudiantService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 déc. 2014 11:11:27
	 * @param dossierEtudiantService
	 *            the dossierEtudiantService to set
	 */
	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	/**
	 * [BilanBean.filtredBilanPeriodeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 f�vr. 2015 10:38:06
	 * @return the filtredBilanPeriodeList
	 */
	public List<BilanSessionDto> getFiltredBilanPeriodeList() {
		return filtredBilanPeriodeList;
	}

	/**
	 * [BilanBean.filtredBilanPeriodeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 f�vr. 2015 10:38:06
	 * @param filtredBilanPeriodeList
	 *            the filtredBilanPeriodeList to set
	 */
	public void setFiltredBilanPeriodeList(
			List<BilanSessionDto> filtredBilanPeriodeList) {
		this.filtredBilanPeriodeList = filtredBilanPeriodeList;
	}

	/**
	 * [BilanBean.filtredBilanAnnuelList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 f�vr. 2015 10:51:43
	 * @return the filtredBilanAnnuelList
	 */
	public List<BilanSessionDto> getFiltredBilanAnnuelList() {
		return filtredBilanAnnuelList;
	}

	/**
	 * [BilanBean.filtredBilanAnnuelList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 f�vr. 2015 10:51:43
	 * @param filtredBilanAnnuelList
	 *            the filtredBilanAnnuelList to set
	 */
	public void setFiltredBilanAnnuelList(
			List<BilanSessionDto> filtredBilanAnnuelList) {
		this.filtredBilanAnnuelList = filtredBilanAnnuelList;
	}

}
