package dz.gov.mesrs.sii.fve.web.jsf.bean.scolarite.chargepedagogique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AtomePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeParamDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.DossierEmployeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantFicheServicesDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantFicheVoeuxDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuLigneDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AtomePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantFicheServicesService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantFicheVoeuxService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.MessageUtil;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;

@ManagedBean(name = "ficheVoeuxBB")
@ViewScoped
public class FicheVoeuxBB {

	// ===================================================================
	// Constructors
	// ===================================================================
	public FicheVoeuxBB() {

	}

	@PostConstruct
	public void init() {

		try {

			// init session infos
			initSessionInfos();
			// check for loading requirements

			if (checkPageRequirements()) {

				// init nomeclatures
				initNomenclatures();

				// init Liste annees
				initYearsList();
				checkExistingFiches();

			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [FicheVoeuxBB.estEnseignant] Method
	 * 
	 * @author rlaib on : 30 oct. 2014 09:37:55
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean estEnseignant() {

		try {
			NomenclatureDto _nomenclatureDto = nomenclatureService.findByCode(OfConstants.NC_NAME_ROLE,
					OfConstants.CHARGE_ENSEIGNANT_ENSEIGNANT_ROLE_CODE);
			if (_nomenclatureDto != null && this.sessionBean.getSessionBean().getCompte() != null) {

				Integer _idRole = this.sessionBean.getSessionBean().getCompte().getIdRole();
				if (_idRole == null) {
					RefCompteDto _compte = refCompteServiceImpl.findById(this.sessionBean.getSessionBean().getCompte()
							.getIdCompte());
					_idRole = _compte.getIdRole();
				}
				if (_idRole == _nomenclatureDto.getId())
					return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	/**
	 * [FicheVoeuxBB.initYearsList] Method
	 * 
	 * @author rlaib on : 18 oct. 2014 12:46:37
	 * @return
	 */
	void initYearsList() {

		try {
			this.listYears = utilBean.loadAnneeAcademique();
			if (this.listYears != null && this.listYears.size() > 0) {

				selectedIdYear = Integer.parseInt(listYears.get(0).getValue().toString());
			} else {
				selectedIdYear = this.idYear;

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// ===================================================================
	// Beans Services
	// ===================================================================
	@ManagedProperty(value = "#{niveauService}")
	private NiveauService niveauService;

	@ManagedProperty(value = "#{periodeService}")
	private PeriodeService periodeService;

	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;

	@ManagedProperty(value = "#{offreFormationService}")
	private OffreFormationService offreFormationService;

	@ManagedProperty(value = "#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;

	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;

	@ManagedProperty(value = "#{enseignantFicheVoeuxService}")
	private EnseignantFicheVoeuxService enseignantFicheVoeuxService;

	@ManagedProperty(value = "#{enseignantFicheServicesService}")
	private EnseignantFicheServicesService enseignantFicheServicesService;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	@ManagedProperty(value = "#{utilBean}")
	private UtilBean utilBean;

	@ManagedProperty(value = "#{atomePedagogiqueService}")
	private AtomePedagogiqueService atomePedagogiqueService;

	@ManagedProperty(value = "#{rattachementMcService}")
	private RattachementMcService rattachementMcService;

	@ManagedProperty(value = "#{refCompteServiceImpl}")
	private RefCompteService refCompteServiceImpl;

	@ManagedProperty(value = "#{refEtablissementServiceImpl}")
	private RefEtablissementService refEtablissementService;

	// ===================================================================
	// Beans Services Getters / Setters
	// ===================================================================

	/**
	 * [FicheVoeuxBB.niveauService] Getter
	 * 
	 * @author rlaib on : 12 oct. 2014 17:25:55
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [FicheVoeuxBB.enseignantFicheServicesService] Getter
	 * 
	 * @author rlaib on : 3 nov. 2014 17:57:46
	 * @return the enseignantFicheServicesService
	 */
	public EnseignantFicheServicesService getEnseignantFicheServicesService() {
		return enseignantFicheServicesService;
	}

	/**
	 * [FicheVoeuxBB.enseignantFicheServicesService] Setter
	 * 
	 * @author rlaib on : 3 nov. 2014 17:57:46
	 * @param enseignantFicheServicesService
	 *            the enseignantFicheServicesService to set
	 */
	public void setEnseignantFicheServicesService(EnseignantFicheServicesService enseignantFicheServicesService) {
		this.enseignantFicheServicesService = enseignantFicheServicesService;
	}

	/**
	 * [FicheVoeuxBB.refCompteServiceImpl] Getter
	 * 
	 * @author rlaib on : 30 oct. 2014 10:00:43
	 * @return the refCompteServiceImpl
	 */
	public RefCompteService getRefCompteServiceImpl() {
		return refCompteServiceImpl;
	}

	/**
	 * [FicheVoeuxBB.refCompteServiceImpl] Setter
	 * 
	 * @author rlaib on : 30 oct. 2014 10:00:43
	 * @param refCompteServiceImpl
	 *            the refCompteServiceImpl to set
	 */
	public void setRefCompteServiceImpl(RefCompteService refCompteServiceImpl) {
		this.refCompteServiceImpl = refCompteServiceImpl;
	}

	/**
	 * [FicheVoeuxBB.refEtablissementService] Getter
	 * 
	 * @author Rafik on : 4 déc. 2014 13:57:35
	 * @return the refEtablissementService
	 */
	public RefEtablissementService getRefEtablissementService() {
		return refEtablissementService;
	}

	/**
	 * [FicheVoeuxBB.refEtablissementService] Setter
	 * 
	 * @author Rafik on : 4 déc. 2014 13:57:35
	 * @param refEtablissementService
	 *            the refEtablissementService to set
	 */
	public void setRefEtablissementService(RefEtablissementService refEtablissementService) {
		this.refEtablissementService = refEtablissementService;
	}

	/**
	 * [FicheVoeuxBB.niveauService] Setter
	 * 
	 * @author rlaib on : 12 oct. 2014 17:25:55
	 * @param niveauService
	 *            the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [FicheVoeuxBB.periodeService] Getter
	 * 
	 * @author rlaib on : 12 oct. 2014 17:25:55
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [FicheVoeuxBB.periodeService] Setter
	 * 
	 * @author rlaib on : 12 oct. 2014 17:25:55
	 * @param periodeService
	 *            the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [FicheVoeuxBB.sessionBean] Getter
	 * 
	 * @author rlaib on : 12 oct. 2014 17:25:55
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [FicheVoeuxBB.sessionBean] Setter
	 * 
	 * @author rlaib on : 12 oct. 2014 17:25:55
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [FicheVoeuxBB.situationService] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 15:42:34
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [FicheVoeuxBB.situationService] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 15:42:34
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [FicheVoeuxBB.offreFormationService] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 15:53:31
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [FicheVoeuxBB.offreFormationService] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 15:53:31
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [FicheVoeuxBB.ouvertureOffreFormationService] Getter
	 * 
	 * @author rlaib on : 21 oct. 2014 16:41:55
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [FicheVoeuxBB.ouvertureOffreFormationService] Setter
	 * 
	 * @author rlaib on : 21 oct. 2014 16:41:55
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [FicheVoeuxBB.nomenclatureService] Getter
	 * 
	 * @author rlaib on : 12 oct. 2014 17:25:55
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [FicheVoeuxBB.nomenclatureService] Setter
	 * 
	 * @author rlaib on : 12 oct. 2014 17:25:55
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [FicheVoeuxBB.enseignantFicheVoeuxService] Getter
	 * 
	 * @author rlaib on : 12 oct. 2014 17:25:55
	 * @return the enseignantFicheVoeuxService
	 */
	public EnseignantFicheVoeuxService getEnseignantFicheVoeuxService() {
		return enseignantFicheVoeuxService;
	}

	/**
	 * [FicheVoeuxBB.enseignantFicheVoeuxService] Setter
	 * 
	 * @author rlaib on : 12 oct. 2014 17:25:55
	 * @param enseignantFicheVoeuxService
	 *            the enseignantFicheVoeuxService to set
	 */
	public void setEnseignantFicheVoeuxService(EnseignantFicheVoeuxService enseignantFicheVoeuxService) {
		this.enseignantFicheVoeuxService = enseignantFicheVoeuxService;
	}

	/**
	 * [FicheVoeuxBB.rattachementMcService] Getter
	 * 
	 * @author rlaib on : 16 oct. 2014 10:58:39
	 * @return the rattachementMcService
	 */
	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	/**
	 * [FicheVoeuxBB.rattachementMcService] Setter
	 * 
	 * @author rlaib on : 16 oct. 2014 10:58:39
	 * @param rattachementMcService
	 *            the rattachementMcService to set
	 */
	public void setRattachementMcService(RattachementMcService rattachementMcService) {
		this.rattachementMcService = rattachementMcService;
	}

	/**
	 * [FicheVoeuxBB.utilBean] Getter
	 * 
	 * @author rlaib on : 16 oct. 2014 12:03:19
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [FicheVoeuxBB.atomePedagogiqueService] Getter
	 * 
	 * @author rlaib on : 16 oct. 2014 12:44:38
	 * @return the atomePedagogiqueService
	 */
	public AtomePedagogiqueService getAtomePedagogiqueService() {
		return atomePedagogiqueService;
	}

	/**
	 * [FicheVoeuxBB.atomePedagogiqueService] Setter
	 * 
	 * @author rlaib on : 16 oct. 2014 12:44:38
	 * @param atomePedagogiqueService
	 *            the atomePedagogiqueService to set
	 */
	public void setAtomePedagogiqueService(AtomePedagogiqueService atomePedagogiqueService) {
		this.atomePedagogiqueService = atomePedagogiqueService;
	}

	/**
	 * [FicheVoeuxBB.utilBean] Setter
	 * 
	 * @author rlaib on : 16 oct. 2014 12:03:19
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [FicheVoeuxBB.loginBean] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 18:03:08
	 * @return the loginBean
	 */
	public LoginBean getLoginBean() {
		return loginBean;
	}

	/**
	 * [FicheVoeuxBB.loginBean] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 18:03:08
	 * @param loginBean
	 *            the loginBean to set
	 */
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	// ===================================================================
	// Properties Model
	// ===================================================================
	private List<SelectItem> listTypeFormation;
	private List<SelectItem> listPeriodicites;
	private String selectedPeriodiciteCode;
	private Integer selectedPeriodeFicheId;
	private Integer selectedPeriodeOfId;
	private List<SelectItem> listPeriodesParPeriodicite;
	private List<SelectItem> listTypeAp;
	private String selectedTypeFormation;
	private Integer selectedNiveauId;
	private List<SelectItem> listOffresFormation;
	private List<SelectItem> listEtablissements;
	private List<SelectItem> listNiveaux;
	private List<SelectItem> listMatieresOf;
	private List<SelectItem> listApsMatiere;
	private List<EnseignantFicheVoeuxDto> fichesVoeux;
	private EnseignantFicheVoeuxDto selectedFicheVoeux;
	private Integer selectedFicheVoeuxId;
	private List<EnseignantVoeuDto> voeux;
	private EnseignantVoeuDto selectedVoeu;
	private Integer selectedVoeuId;
	private Integer selectedVoeuIndex;
	private Integer selectedVoeuLigneIndex;
	private List<EnseignantVoeuLigneDto> lignes;
	private EnseignantVoeuLigneDto currentVoeuLigne;
	private Integer selectedLigneId;
	private boolean toEdit = false;
	private boolean toConsult = false;
	private boolean toSubmit = false;
	private boolean toValidate = false;
	private boolean canAddVoeu = true;
	private boolean canSaveVoeu = false;
	private boolean canSaveFiche = false;
	private boolean canSubmitFiche = false;
	private boolean canAddLigneVoeu = true;
	private boolean canSaveLigneVoeu = false;
	private boolean canCreateFicheVoeux = false;
	private boolean hasValidParam = true;
	private boolean hasFicheVoeux = false;
	private Integer maxVoeux;
	private Integer maxLignes;
	private Integer selectedIdEtablissement;
	private Integer selectedIdOf;
	private Integer selectedIdCycleOf;
	private Integer selectedIdYear;
	private Integer selectedIdRattachementMc;
	private Integer selectedIdAp;
	private List<SelectItem> listYears;
	private List<SituationEntiteOccurrenceDto> ficheHistory;
	private List<PeriodeParamDto> periodeParams;
	private Map<Integer, List<EnseignantVoeuLigneDto>> lignesVoeux;
	private Map<Integer, List<AtomePedagogiqueDto>> apsMatieres;
	private DossierEmployeDto enseignantDto;
	private List<PeriodeDto> currentPeriodes;
	private List<EnseignantVoeuLigneDto> currentLignesVoeux;
	private boolean addOrEditDialog = false;
	private Map<Integer, String> mapEtablissements;

	/**
	 * [FicheVoeuxBB.mapEtablissements] Getter
	 * 
	 * @author Rafik on : 4 déc. 2014 20:38:38
	 * @return the mapEtablissements
	 */
	public Map<Integer, String> getMapEtablissements() {
		return mapEtablissements;
	}

	/**
	 * [FicheVoeuxBB.mapEtablissements] Setter
	 * 
	 * @author Rafik on : 4 déc. 2014 20:38:38
	 * @param mapEtablissements
	 *            the mapEtablissements to set
	 */
	public void setMapEtablissements(Map<Integer, String> mapEtablissements) {
		this.mapEtablissements = mapEtablissements;
	}

	/**
	 * [FicheVoeuxBB.addOrEditDialog] Getter
	 * 
	 * @author rlaib on : 8 nov. 2014 09:26:14
	 * @return the addOrEditDialog
	 */
	public boolean isAddOrEditDialog() {
		return addOrEditDialog;
	}

	/**
	 * [FicheVoeuxBB.addOrEditDialog] Setter
	 * 
	 * @author rlaib on : 8 nov. 2014 09:26:14
	 * @param addOrEditDialog
	 *            the addOrEditDialog to set
	 */
	public void setAddOrEditDialog(boolean addOrEditDialog) {
		this.addOrEditDialog = addOrEditDialog;
	}

	/**
	 * [FicheVoeuxBB.currentLignesVoeux] Getter
	 * 
	 * @author rlaib on : 7 nov. 2014 16:39:41
	 * @return the currentLignesVoeux
	 */
	public List<EnseignantVoeuLigneDto> getCurrentLignesVoeux() {
		return currentLignesVoeux;
	}

	/**
	 * [FicheVoeuxBB.currentLignesVoeux] Setter
	 * 
	 * @author rlaib on : 7 nov. 2014 16:39:41
	 * @param currentLignesVoeux
	 *            the currentLignesVoeux to set
	 */
	public void setCurrentLignesVoeux(List<EnseignantVoeuLigneDto> currentLignesVoeux) {
		this.currentLignesVoeux = currentLignesVoeux;
	}

	/**
	 * [FicheVoeuxBB.currentPeriodes] Getter
	 * 
	 * @author rlaib on : 2 nov. 2014 17:11:39
	 * @return the currentPeriodes
	 */
	public List<PeriodeDto> getCurrentPeriodes() {
		return currentPeriodes;
	}

	/**
	 * [FicheVoeuxBB.currentPeriodes] Setter
	 * 
	 * @author rlaib on : 2 nov. 2014 17:11:39
	 * @param currentPeriodes
	 *            the currentPeriodes to set
	 */
	public void setCurrentPeriodes(List<PeriodeDto> currentPeriodes) {
		this.currentPeriodes = currentPeriodes;
	}

	/**
	 * [FicheVoeuxBB.enseignantDto] Getter
	 * 
	 * @author rlaib on : 29 oct. 2014 16:35:00
	 * @return the enseignantDto
	 */
	public DossierEmployeDto getEnseignantDto() {
		return enseignantDto;
	}

	/**
	 * [FicheVoeuxBB.enseignantDto] Setter
	 * 
	 * @author rlaib on : 29 oct. 2014 16:35:00
	 * @param enseignantDto
	 *            the enseignantDto to set
	 */
	public void setEnseignantDto(DossierEmployeDto enseignantDto) {
		this.enseignantDto = enseignantDto;
	}

	/**
	 * [FicheVoeuxBB.listTypeFormation] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 11:18:48
	 * @return the listTypeFormation
	 */
	public List<SelectItem> getListTypeFormation() {
		return listTypeFormation;
	}

	/**
	 * [FicheVoeuxBB.listTypeFormation] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 11:18:48
	 * @param listTypeFormation
	 *            the listTypeFormation to set
	 */
	public void setListTypeFormation(List<SelectItem> listTypeFormation) {
		this.listTypeFormation = listTypeFormation;
	}

	/**
	 * [FicheVoeuxBB.selectedPeriodiciteCode] Getter
	 * 
	 * @author rlaib on : 1 nov. 2014 15:22:06
	 * @return the selectedPeriodiciteCode
	 */
	public String getSelectedPeriodiciteCode() {
		return selectedPeriodiciteCode;
	}

	/**
	 * [FicheVoeuxBB.selectedPeriodiciteCode] Setter
	 * 
	 * @author rlaib on : 1 nov. 2014 15:22:06
	 * @param selectedPeriodiciteCode
	 *            the selectedPeriodiciteCode to set
	 */
	public void setSelectedPeriodiciteCode(String selectedPeriodiciteCode) {
		this.selectedPeriodiciteCode = selectedPeriodiciteCode;
	}

	/**
	 * [FicheVoeuxBB.selectedPeriode] Getter
	 * 
	 * @author rlaib on : 1 nov. 2014 15:22:06
	 * @return the selectedPeriode
	 */
	public Integer getSelectedPeriodeFicheId() {
		return selectedPeriodeFicheId;
	}

	/**
	 * [FicheVoeuxBB.selectedPeriode] Setter
	 * 
	 * @author rlaib on : 1 nov. 2014 15:22:06
	 * @param selectedPeriode
	 *            the selectedPeriode to set
	 */
	public void setSelectedPeriodeFicheId(Integer selectedPeriodeFicheId) {
		this.selectedPeriodeFicheId = selectedPeriodeFicheId;
	}

	/**
	 * [FicheVoeuxBB.selectedPeriodeOfId] Getter
	 * 
	 * @author rlaib on : 7 d�c. 2014 11:01:43
	 * @return the selectedPeriodeOfId
	 */
	public Integer getSelectedPeriodeOfId() {
		return selectedPeriodeOfId;
	}

	/**
	 * [FicheVoeuxBB.selectedPeriodeOfId] Setter
	 * 
	 * @author rlaib on : 7 d�c. 2014 11:01:43
	 * @param selectedPeriodeOfId
	 *            the selectedPeriodeOfId to set
	 */
	public void setSelectedPeriodeOfId(Integer selectedPeriodeOfId) {
		this.selectedPeriodeOfId = selectedPeriodeOfId;
	}

	/**
	 * [FicheVoeuxBB.listPeriodicites] Getter
	 * 
	 * @author rlaib on : 1 nov. 2014 14:53:32
	 * @return the listPeriodicites
	 */
	public List<SelectItem> getListPeriodicites() {
		return listPeriodicites;
	}

	/**
	 * [FicheVoeuxBB.listPeriodicites] Setter
	 * 
	 * @author rlaib on : 1 nov. 2014 14:53:32
	 * @param listPeriodicites
	 *            the listPeriodicites to set
	 */
	public void setListPeriodicites(List<SelectItem> listPeriodicites) {
		this.listPeriodicites = listPeriodicites;
	}

	/**
	 * [FicheVoeuxBB.listPeriodesParPeriodicite] Getter
	 * 
	 * @author rlaib on : 1 nov. 2014 14:53:32
	 * @return the listPeriodesParPeriodicite
	 */
	public List<SelectItem> getListPeriodesParPeriodicite() {
		return listPeriodesParPeriodicite;
	}

	/**
	 * [FicheVoeuxBB.listPeriodesParPeriodicite] Setter
	 * 
	 * @author rlaib on : 1 nov. 2014 14:53:32
	 * @param listPeriodesParPeriodicite
	 *            the listPeriodesParPeriodicite to set
	 */
	public void setListPeriodesParPeriodicite(List<SelectItem> listPeriodesParPeriodicite) {
		this.listPeriodesParPeriodicite = listPeriodesParPeriodicite;
	}

	/**
	 * [FicheVoeuxBB.listTypeAp] Getter
	 * 
	 * @author rlaib on : 16 oct. 2014 11:02:54
	 * @return the listTypeAp
	 */
	public List<SelectItem> getListTypeAp() {
		return listTypeAp;
	}

	/**
	 * [FicheVoeuxBB.listTypeAp] Setter
	 * 
	 * @author rlaib on : 16 oct. 2014 11:02:54
	 * @param listTypeAp
	 *            the listTypeAp to set
	 */
	public void setListTypeAp(List<SelectItem> listTypeAp) {
		this.listTypeAp = listTypeAp;
	}

	/**
	 * [FicheVoeuxBB.selectedTypeFormation] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 14:19:24
	 * @return the selectedTypeFormation
	 */
	public String getSelectedTypeFormation() {
		return selectedTypeFormation;
	}

	/**
	 * [FicheVoeuxBB.selectedTypeFormation] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 14:19:24
	 * @param selectedTypeFormation
	 *            the selectedTypeFormation to set
	 */
	public void setSelectedTypeFormation(String selectedTypeFormation) {
		this.selectedTypeFormation = selectedTypeFormation;
	}

	/**
	 * [FicheVoeuxBB.listNiveaux] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 16:19:01
	 * @return the listNiveaux
	 */
	public List<SelectItem> getListNiveaux() {
		return listNiveaux;
	}

	/**
	 * [FicheVoeuxBB.listNiveaux] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 16:19:01
	 * @param listNiveaux
	 *            the listNiveaux to set
	 */
	public void setListNiveaux(List<SelectItem> listNiveaux) {
		this.listNiveaux = listNiveaux;
	}

	/**
	 * [FicheVoeuxBB.selectedNiveauId] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 16:19:01
	 * @return the selectedNiveauId
	 */
	public Integer getSelectedNiveauId() {
		return selectedNiveauId;
	}

	/**
	 * [FicheVoeuxBB.selectedNiveauId] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 16:19:01
	 * @param selectedNiveauId
	 *            the selectedNiveauId to set
	 */
	public void setSelectedNiveauId(Integer selectedNiveauId) {
		this.selectedNiveauId = selectedNiveauId;
	}

	/**
	 * [FicheVoeuxBB.listOffresFormation] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 11:18:48
	 * @return the listOffresFormation
	 */
	public List<SelectItem> getListOffresFormation() {
		return listOffresFormation;
	}

	/**
	 * [FicheVoeuxBB.listOffresFormation] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 11:18:48
	 * @param listOffresFormation
	 *            the listOffresFormation to set
	 */
	public void setListOffresFormation(List<SelectItem> listOffresFormation) {
		this.listOffresFormation = listOffresFormation;
	}

	/**
	 * [FicheVoeuxBB.listMatieresOf] Getter
	 * 
	 * @author rlaib on : 16 oct. 2014 11:36:06
	 * @return the listMatieresOf
	 */
	public List<SelectItem> getListMatieresOf() {
		return listMatieresOf;
	}

	/**
	 * [FicheVoeuxBB.listMatieresOf] Setter
	 * 
	 * @author rlaib on : 16 oct. 2014 11:36:06
	 * @param listMatieresOf
	 *            the listMatieresOf to set
	 */
	public void setListMatieresOf(List<SelectItem> listMatieresOf) {
		this.listMatieresOf = listMatieresOf;
	}

	/**
	 * [FicheVoeuxBB.listApsMatiere] Getter
	 * 
	 * @author rlaib on : 16 oct. 2014 12:24:48
	 * @return the listApsMatiere
	 */
	public List<SelectItem> getListApsMatiere() {
		return listApsMatiere;
	}

	/**
	 * [FicheVoeuxBB.listApsMatiere] Setter
	 * 
	 * @author rlaib on : 16 oct. 2014 12:24:48
	 * @param listApsMatiere
	 *            the listApsMatiere to set
	 */
	public void setListApsMatiere(List<SelectItem> listApsMatiere) {
		this.listApsMatiere = listApsMatiere;
	}

	/**
	 * [FicheVoeuxBB.fichesVoeux] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 11:18:48
	 * @return the fichesVoeux
	 */
	public List<EnseignantFicheVoeuxDto> getFichesVoeux() {
		return fichesVoeux;
	}

	/**
	 * [FicheVoeuxBB.fichesVoeux] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 11:18:48
	 * @param fichesVoeux
	 *            the fichesVoeux to set
	 */
	public void setFichesVoeux(List<EnseignantFicheVoeuxDto> fichesVoeux) {
		this.fichesVoeux = fichesVoeux;
	}

	/**
	 * [FicheVoeuxBB.selectedFicheVoeux] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 11:20:26
	 * @return the selectedFicheVoeux
	 */
	public EnseignantFicheVoeuxDto getSelectedFicheVoeux() {
		return selectedFicheVoeux;
	}

	/**
	 * [FicheVoeuxBB.selectedFicheVoeux] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 11:20:26
	 * @param selectedFicheVoeux
	 *            the selectedFicheVoeux to set
	 */
	public void setSelectedFicheVoeux(EnseignantFicheVoeuxDto selectedFicheVoeux) {
		this.selectedFicheVoeux = selectedFicheVoeux;
	}

	/**
	 * [FicheVoeuxBB.selectedFicheVoeuxId] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 11:20:26
	 * @return the selectedFicheVoeuxId
	 */
	public Integer getSelectedFicheVoeuxId() {
		return selectedFicheVoeuxId;
	}

	/**
	 * [FicheVoeuxBB.selectedFicheVoeuxId] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 11:20:26
	 * @param selectedFicheVoeuxId
	 *            the selectedFicheVoeuxId to set
	 */
	public void setSelectedFicheVoeuxId(Integer selectedFicheVoeuxId) {
		this.selectedFicheVoeuxId = selectedFicheVoeuxId;
	}

	/**
	 * [FicheVoeuxBB.voeux] Getter
	 * 
	 * @author rlaib on : 14 oct. 2014 17:06:55
	 * @return the voeux
	 */
	public List<EnseignantVoeuDto> getVoeux() {
		return voeux;
	}

	/**
	 * [FicheVoeuxBB.voeux] Setter
	 * 
	 * @author rlaib on : 14 oct. 2014 17:06:55
	 * @param voeux
	 *            the voeux to set
	 */
	public void setVoeux(List<EnseignantVoeuDto> voeux) {
		this.voeux = voeux;
	}

	/**
	 * [FicheVoeuxBB.selectedVoeu] Getter
	 * 
	 * @author rlaib on : 14 oct. 2014 17:06:55
	 * @return the selectedVoeu
	 */
	public EnseignantVoeuDto getSelectedVoeu() {
		return selectedVoeu;
	}

	/**
	 * [FicheVoeuxBB.selectedVoeu] Setter
	 * 
	 * @author rlaib on : 14 oct. 2014 17:06:55
	 * @param selectedVoeu
	 *            the selectedVoeu to set
	 */
	public void setSelectedVoeu(EnseignantVoeuDto selectedVoeu) {
		this.selectedVoeu = selectedVoeu;
	}

	/**
	 * [FicheVoeuxBB.selectedVoeuId] Getter
	 * 
	 * @author rlaib on : 14 oct. 2014 17:06:55
	 * @return the selectedVoeuId
	 */
	public Integer getSelectedVoeuId() {
		return selectedVoeuId;
	}

	/**
	 * [FicheVoeuxBB.selectedVoeuId] Setter
	 * 
	 * @author rlaib on : 14 oct. 2014 17:06:55
	 * @param selectedVoeuId
	 *            the selectedVoeuId to set
	 */
	public void setSelectedVoeuId(Integer selectedVoeuId) {
		this.selectedVoeuId = selectedVoeuId;
	}

	/**
	 * [FicheVoeuxBB.selectedVoeuIndex] Getter
	 * 
	 * @author rlaib on : 23 oct. 2014 16:28:40
	 * @return the selectedVoeuIndex
	 */
	public Integer getSelectedVoeuIndex() {
		return selectedVoeuIndex;
	}

	/**
	 * [FicheVoeuxBB.selectedVoeuIndex] Setter
	 * 
	 * @author rlaib on : 23 oct. 2014 16:28:40
	 * @param selectedVoeuIndex
	 *            the selectedVoeuIndex to set
	 */
	public void setSelectedVoeuIndex(Integer selectedVoeuIndex) {
		this.selectedVoeuIndex = selectedVoeuIndex;
	}

	/**
	 * [FicheVoeuxBB.selectedVoeuLigneIndex] Getter
	 * 
	 * @author rlaib on : 24 oct. 2014 10:24:55
	 * @return the selectedVoeuLigneIndex
	 */
	public Integer getSelectedVoeuLigneIndex() {
		return selectedVoeuLigneIndex;
	}

	/**
	 * [FicheVoeuxBB.selectedVoeuLigneIndex] Setter
	 * 
	 * @author rlaib on : 24 oct. 2014 10:24:55
	 * @param selectedVoeuLigneIndex
	 *            the selectedVoeuLigneIndex to set
	 */
	public void setSelectedVoeuLigneIndex(Integer selectedVoeuLigneIndex) {
		this.selectedVoeuLigneIndex = selectedVoeuLigneIndex;
	}

	/**
	 * [FicheVoeuxBB.lignes] Getter
	 * 
	 * @author rlaib on : 15 oct. 2014 15:07:50
	 * @return the lignes
	 */
	public List<EnseignantVoeuLigneDto> getLignes() {
		return lignes;
	}

	/**
	 * [FicheVoeuxBB.lignes] Setter
	 * 
	 * @author rlaib on : 15 oct. 2014 15:07:50
	 * @param lignes
	 *            the lignes to set
	 */
	public void setLignes(List<EnseignantVoeuLigneDto> lignes) {
		this.lignes = lignes;
	}

	/**
	 * [FicheVoeuxBB.currentVoeuLigne] Getter
	 * 
	 * @author rlaib on : 6 nov. 2014 16:37:45
	 * @return the currentVoeuLigne
	 */
	public EnseignantVoeuLigneDto getCurrentVoeuLigne() {
		return currentVoeuLigne;
	}

	/**
	 * [FicheVoeuxBB.currentVoeuLigne] Setter
	 * 
	 * @author rlaib on : 6 nov. 2014 16:37:45
	 * @param currentVoeuLigne
	 *            the currentVoeuLigne to set
	 */
	public void setCurrentVoeuLigne(EnseignantVoeuLigneDto currentVoeuLigne) {
		this.currentVoeuLigne = currentVoeuLigne;
	}

	/**
	 * [FicheVoeuxBB.selectedLigneId] Getter
	 * 
	 * @author rlaib on : 15 oct. 2014 15:07:50
	 * @return the selectedLigneId
	 */
	public Integer getSelectedLigneId() {
		return selectedLigneId;
	}

	/**
	 * [FicheVoeuxBB.selectedLigneId] Setter
	 * 
	 * @author rlaib on : 15 oct. 2014 15:07:50
	 * @param selectedLigneId
	 *            the selectedLigneId to set
	 */
	public void setSelectedLigneId(Integer selectedLigneId) {
		this.selectedLigneId = selectedLigneId;
	}

	/**
	 * [FicheVoeuxBB.maxLignes] Getter
	 * 
	 * @author rlaib on : 15 oct. 2014 15:07:50
	 * @return the maxLignes
	 */
	public Integer getMaxLignes() {
		return maxLignes;
	}

	/**
	 * [FicheVoeuxBB.maxLignes] Setter
	 * 
	 * @author rlaib on : 15 oct. 2014 15:07:50
	 * @param maxLignes
	 *            the maxLignes to set
	 */
	public void setMaxLignes(Integer maxLignes) {
		this.maxLignes = maxLignes;
	}

	/**
	 * [FicheVoeuxBB.selectedIdOf] Getter
	 * 
	 * @author rlaib on : 16 oct. 2014 16:37:30
	 * @return the selectedIdOf
	 */
	public Integer getSelectedIdOf() {
		return selectedIdOf;
	}

	/**
	 * [FicheVoeuxBB.selectedIdOf] Setter
	 * 
	 * @author rlaib on : 16 oct. 2014 16:37:30
	 * @param selectedIdOf
	 *            the selectedIdOf to set
	 */
	public void setSelectedIdOf(Integer selectedIdOf) {
		this.selectedIdOf = selectedIdOf;
	}

	/**
	 * [FicheVoeuxBB.listEtablissements] Getter
	 * 
	 * @author Rafik on : 4 déc. 2014 13:44:20
	 * @return the listEtablissements
	 */
	public List<SelectItem> getListEtablissements() {
		return listEtablissements;
	}

	/**
	 * [FicheVoeuxBB.listEtablissements] Setter
	 * 
	 * @author Rafik on : 4 déc. 2014 13:44:20
	 * @param listEtablissements
	 *            the listEtablissements to set
	 */
	public void setListEtablissements(List<SelectItem> listEtablissements) {
		this.listEtablissements = listEtablissements;
	}

	/**
	 * [FicheVoeuxBB.selectedIdEtablissement] Getter
	 * 
	 * @author Rafik on : 4 déc. 2014 13:44:20
	 * @return the selectedIdEtablissement
	 */
	public Integer getSelectedIdEtablissement() {
		return selectedIdEtablissement;
	}

	/**
	 * [FicheVoeuxBB.selectedIdEtablissement] Setter
	 * 
	 * @author Rafik on : 4 déc. 2014 13:44:20
	 * @param selectedIdEtablissement
	 *            the selectedIdEtablissement to set
	 */
	public void setSelectedIdEtablissement(Integer selectedIdEtablissement) {
		this.selectedIdEtablissement = selectedIdEtablissement;
	}

	/**
	 * [FicheVoeuxBB.selectedIdCycleOf] Getter
	 * 
	 * @author rlaib on : 16 oct. 2014 16:37:30
	 * @return the selectedIdCycleOf
	 */
	public Integer getSelectedIdCycleOf() {
		return selectedIdCycleOf;
	}

	/**
	 * [FicheVoeuxBB.selectedIdCycleOf] Setter
	 * 
	 * @author rlaib on : 16 oct. 2014 16:37:30
	 * @param selectedIdCycleOf
	 *            the selectedIdCycleOf to set
	 */
	public void setSelectedIdCycleOf(Integer selectedIdCycleOf) {
		this.selectedIdCycleOf = selectedIdCycleOf;
	}

	/**
	 * [FicheVoeuxBB.selectedIdYear] Getter
	 * 
	 * @author rlaib on : 18 oct. 2014 12:48:15
	 * @return the selectedIdYear
	 */
	public Integer getSelectedIdYear() {
		return selectedIdYear;
	}

	/**
	 * [FicheVoeuxBB.selectedIdYear] Setter
	 * 
	 * @author rlaib on : 18 oct. 2014 12:48:15
	 * @param selectedIdYear
	 *            the selectedIdYear to set
	 */
	public void setSelectedIdYear(Integer selectedIdYear) {
		this.selectedIdYear = selectedIdYear;
	}

	/**
	 * [FicheVoeuxBB.toEdit] Getter
	 * 
	 * @author rlaib on : 14 oct. 2014 17:06:55
	 * @return the toEdit
	 */
	public boolean isToEdit() {
		return toEdit;
	}

	/**
	 * [FicheVoeuxBB.toConsult] Getter
	 * 
	 * @author rlaib on : 9 d�c. 2014 14:39:58
	 * @return the toConsult
	 */
	public boolean isToConsult() {
		return toConsult;
	}

	/**
	 * [FicheVoeuxBB.toConsult] Setter
	 * 
	 * @author rlaib on : 9 d�c. 2014 14:39:58
	 * @param toConsult
	 *            the toConsult to set
	 */
	public void setToConsult(boolean toConsult) {
		this.toConsult = toConsult;
	}

	/**
	 * [FicheVoeuxBB.toSubmit] Getter
	 * 
	 * @author rlaib on : 18 oct. 2014 18:50:52
	 * @return the toSubmit
	 */
	public boolean isToSubmit() {
		return toSubmit;
	}

	/**
	 * [FicheVoeuxBB.toSubmit] Setter
	 * 
	 * @author rlaib on : 18 oct. 2014 18:50:52
	 * @param toSubmit
	 *            the toSubmit to set
	 */
	public void setToSubmit(boolean toSubmit) {
		this.toSubmit = toSubmit;
	}

	/**
	 * [FicheVoeuxBB.toValiate] Getter
	 * 
	 * @author rlaib on : 20 oct. 2014 13:26:15
	 * @return the toValiate
	 */
	public boolean isToValidate() {
		return toValidate;
	}

	/**
	 * [FicheVoeuxBB.toValidate] Setter
	 * 
	 * @author rlaib on : 5 nov. 2014 17:55:01
	 * @param toValidate
	 *            the toValidate to set
	 */
	public void setToValidate(boolean toValidate) {
		this.toValidate = toValidate;
	}

	/**
	 * [FicheVoeuxBB.canAddVoeu] Getter
	 * 
	 * @author rlaib on : 16 oct. 2014 09:40:57
	 * @return the canAddVoeu
	 */
	public boolean isCanAddVoeu() {
		return canAddVoeu;
	}

	/**
	 * [FicheVoeuxBB.canAddVoeu] Setter
	 * 
	 * @author rlaib on : 16 oct. 2014 09:40:57
	 * @param canAddVoeu
	 *            the canAddVoeu to set
	 */
	public void setCanAddVoeu(boolean canAddVoeu) {
		this.canAddVoeu = canAddVoeu;
	}

	/**
	 * [FicheVoeuxBB.canSaveVoeu] Getter
	 * 
	 * @author rlaib on : 18 oct. 2014 19:10:15
	 * @return the canSaveVoeu
	 */
	public boolean isCanSaveVoeu() {
		return canSaveVoeu;
	}

	/**
	 * [FicheVoeuxBB.canSaveVoeu] Setter
	 * 
	 * @author rlaib on : 18 oct. 2014 19:10:15
	 * @param canSaveVoeu
	 *            the canSaveVoeu to set
	 */
	public void setCanSaveVoeu(boolean canSaveVoeu) {
		this.canSaveVoeu = canSaveVoeu;
	}

	/**
	 * [FicheVoeuxBB.canSaveFiche] Getter
	 * 
	 * @author rlaib on : 22 oct. 2014 17:09:27
	 * @return the canSaveFiche
	 */
	public boolean isCanSaveFiche() {
		return canSaveFiche;
	}

	/**
	 * [FicheVoeuxBB.canSaveFiche] Setter
	 * 
	 * @author rlaib on : 22 oct. 2014 17:09:27
	 * @param canSaveFiche
	 *            the canSaveFiche to set
	 */
	public void setCanSaveFiche(boolean canSaveFiche) {
		this.canSaveFiche = canSaveFiche;
	}

	/**
	 * [FicheVoeuxBB.canSubmitFiche] Getter
	 * 
	 * @author rlaib on : 24 oct. 2014 13:19:10
	 * @return the canSubmitFiche
	 */
	public boolean isCanSubmitFiche() {
		return canSubmitFiche;
	}

	/**
	 * [FicheVoeuxBB.canSubmitFiche] Setter
	 * 
	 * @author rlaib on : 24 oct. 2014 13:19:10
	 * @param canSubmitFiche
	 *            the canSubmitFiche to set
	 */
	public void setCanSubmitFiche(boolean canSubmitFiche) {
		this.canSubmitFiche = canSubmitFiche;
	}

	/**
	 * [FicheVoeuxBB.canSaveLigneVoeu] Getter
	 * 
	 * @author rlaib on : 18 oct. 2014 19:10:15
	 * @return the canSaveLigneVoeu
	 */
	public boolean isCanSaveLigneVoeu() {
		return canSaveLigneVoeu;
	}

	/**
	 * [FicheVoeuxBB.canSaveLigneVoeu] Setter
	 * 
	 * @author rlaib on : 18 oct. 2014 19:10:15
	 * @param canSaveLigneVoeu
	 *            the canSaveLigneVoeu to set
	 */
	public void setCanSaveLigneVoeu(boolean canSaveLigneVoeu) {
		this.canSaveLigneVoeu = canSaveLigneVoeu;
	}

	/**
	 * [FicheVoeuxBB.selectedIdRattachementMc] Getter
	 * 
	 * @author rlaib on : 16 oct. 2014 17:38:02
	 * @return the selectedIdRattachementMc
	 */
	public Integer getSelectedIdRattachementMc() {
		return selectedIdRattachementMc;
	}

	/**
	 * [FicheVoeuxBB.selectedIdRattachementMc] Setter
	 * 
	 * @author rlaib on : 16 oct. 2014 17:38:02
	 * @param selectedIdRattachementMc
	 *            the selectedIdRattachementMc to set
	 */
	public void setSelectedIdRattachementMc(Integer selectedIdRattachementMc) {
		this.selectedIdRattachementMc = selectedIdRattachementMc;
	}

	/**
	 * [FicheVoeuxBB.selectedIdAp] Getter
	 * 
	 * @author rlaib on : 6 nov. 2014 17:28:29
	 * @return the selectedIdAp
	 */
	public Integer getSelectedIdAp() {
		return selectedIdAp;
	}

	/**
	 * [FicheVoeuxBB.selectedIdAp] Setter
	 * 
	 * @author rlaib on : 6 nov. 2014 17:28:29
	 * @param selectedIdAp
	 *            the selectedIdAp to set
	 */
	public void setSelectedIdAp(Integer selectedIdAp) {
		this.selectedIdAp = selectedIdAp;
	}

	/**
	 * [FicheVoeuxBB.listYears] Getter
	 * 
	 * @author rlaib on : 18 oct. 2014 12:45:22
	 * @return the listYears
	 */
	public List<SelectItem> getListYears() {
		return listYears;
	}

	/**
	 * [FicheVoeuxBB.listYears] Setter
	 * 
	 * @author rlaib on : 18 oct. 2014 12:45:22
	 * @param listYears
	 *            the listYears to set
	 */
	public void setListYears(List<SelectItem> listYears) {
		this.listYears = listYears;
	}

	/**
	 * [FicheVoeuxBB.ficheHistory] Getter
	 * 
	 * @author rlaib on : 19 oct. 2014 12:37:10
	 * @return the ficheHistory
	 */
	public List<SituationEntiteOccurrenceDto> getFicheHistory() {
		return ficheHistory;
	}

	/**
	 * [FicheVoeuxBB.ficheHistory] Setter
	 * 
	 * @author rlaib on : 19 oct. 2014 12:37:10
	 * @param ficheHistory
	 *            the ficheHistory to set
	 */
	public void setFicheHistory(List<SituationEntiteOccurrenceDto> ficheHistory) {
		this.ficheHistory = ficheHistory;
	}

	/**
	 * [FicheVoeuxBB.periodeParams] Getter
	 * 
	 * @author rlaib on : 27 oct. 2014 16:30:02
	 * @return the periodeParams
	 */
	public List<PeriodeParamDto> getPeriodeParams() {
		return periodeParams;
	}

	/**
	 * [FicheVoeuxBB.periodeParams] Setter
	 * 
	 * @author rlaib on : 27 oct. 2014 16:30:02
	 * @param periodeParams
	 *            the periodeParams to set
	 */
	public void setPeriodeParams(List<PeriodeParamDto> periodeParams) {
		this.periodeParams = periodeParams;
	}

	/**
	 * [FicheVoeuxBB.canAddLigneVoeu] Getter
	 * 
	 * @author rlaib on : 16 oct. 2014 09:40:57
	 * @return the canAddLigneVoeu
	 */
	public boolean isCanAddLigneVoeu() {
		return canAddLigneVoeu;
	}

	/**
	 * [FicheVoeuxBB.canAddLigneVoeu] Setter
	 * 
	 * @author rlaib on : 16 oct. 2014 09:40:57
	 * @param canAddLigneVoeu
	 *            the canAddLigneVoeu to set
	 */
	public void setCanAddLigneVoeu(boolean canAddLigneVoeu) {
		this.canAddLigneVoeu = canAddLigneVoeu;
	}

	/**
	 * [FicheVoeuxBB.canCreateFicheVoeux] Getter
	 * 
	 * @author rlaib on : 18 oct. 2014 11:41:24
	 * @return the canCreateFicheVoeux
	 */
	public boolean isCanCreateFicheVoeux() {
		return canCreateFicheVoeux;
	}

	/**
	 * [FicheVoeuxBB.canCreateFicheVoeux] Setter
	 * 
	 * @author rlaib on : 18 oct. 2014 11:41:24
	 * @param canCreateFicheVoeux
	 *            the canCreateFicheVoeux to set
	 */
	public void setCanCreateFicheVoeux(boolean canCreateFicheVoeux) {
		this.canCreateFicheVoeux = canCreateFicheVoeux;
	}

	/**
	 * [FicheVoeuxBB.hasValidParam] Getter
	 * 
	 * @author rlaib on : 18 oct. 2014 11:34:12
	 * @return the hasValidParam
	 */
	public boolean isHasValidParam() {
		return hasValidParam;
	}

	/**
	 * [FicheVoeuxBB.hasValidParam] Setter
	 * 
	 * @author rlaib on : 18 oct. 2014 11:34:12
	 * @param hasValidParam
	 *            the hasValidParam to set
	 */
	public void setHasValidParam(boolean hasValidParam) {
		this.hasValidParam = hasValidParam;
	}

	/**
	 * [FicheVoeuxBB.hasFicheVoeux] Getter
	 * 
	 * @author rlaib on : 21 oct. 2014 12:40:30
	 * @return the hasFicheVoeux
	 */
	public boolean isHasFicheVoeux() {
		return hasFicheVoeux;
	}

	/**
	 * [FicheVoeuxBB.hasFicheVoeux] Setter
	 * 
	 * @author rlaib on : 21 oct. 2014 12:40:30
	 * @param hasFicheVoeux
	 *            the hasFicheVoeux to set
	 */
	public void setHasFicheVoeux(boolean hasFicheVoeux) {
		this.hasFicheVoeux = hasFicheVoeux;
	}

	/**
	 * [FicheVoeuxBB.toEdit] Setter
	 * 
	 * @author rlaib on : 14 oct. 2014 17:06:55
	 * @param toEdit
	 *            the toEdit to set
	 */
	public void setToEdit(boolean toEdit) {
		this.toEdit = toEdit;
	}

	/**
	 * [FicheVoeuxBB.maxVoeux] Getter
	 * 
	 * @author rlaib on : 14 oct. 2014 17:27:06
	 * @return the maxVoeux
	 */
	public Integer getMaxVoeux() {
		if (maxVoeux == null)
			return 6;
		return maxVoeux;
	}

	/**
	 * [FicheVoeuxBB.maxVoeux] Setter
	 * 
	 * @author rlaib on : 14 oct. 2014 17:27:06
	 * @param maxVoeux
	 *            the maxVoeux to set
	 */
	public void setMaxVoeux(Integer maxVoeux) {
		this.maxVoeux = maxVoeux;
	}

	/**
	 * [FicheVoeuxBB.apsMatieres] Getter
	 * 
	 * @author rlaib on : 25 oct. 2014 13:02:50
	 * @return the apsMatieres
	 */
	public Map<Integer, List<AtomePedagogiqueDto>> getApsMatieres() {
		return apsMatieres;
	}

	/**
	 * [FicheVoeuxBB.apsMatieres] Setter
	 * 
	 * @author rlaib on : 25 oct. 2014 13:02:50
	 * @param apsMatieres
	 *            the apsMatieres to set
	 */
	public void setApsMatieres(Map<Integer, List<AtomePedagogiqueDto>> apsMatieres) {
		this.apsMatieres = apsMatieres;
	}

	/**
	 * [FicheVoeuxBB.lignesVoeux] Getter
	 * 
	 * @author rlaib on : 15 oct. 2014 14:17:44
	 * @return the lignesVoeux
	 */
	public Map<Integer, List<EnseignantVoeuLigneDto>> getLignesVoeux() {
		if (lignesVoeux == null)
			lignesVoeux = new HashMap<Integer, List<EnseignantVoeuLigneDto>>();
		return lignesVoeux;
	}

	/**
	 * [FicheVoeuxBB.lignesVoeux] Setter
	 * 
	 * @author rlaib on : 15 oct. 2014 14:17:44
	 * @param lignesVoeux
	 *            the lignesVoeux to set
	 */
	public void setLignesVoeux(Map<Integer, List<EnseignantVoeuLigneDto>> lignesVoeux) {
		this.lignesVoeux = lignesVoeux;
	}

	// ===================================================================
	// Session Infos
	// ===================================================================
	private Integer idEtablissement;
	private String codeEtablissement;
	private String newEtabCode;
	private Integer newEtabId;
	private String libelleEtab;
	private Integer idYear;
	private Integer userId;
	private String userFisrtName;
	private String userLastName;
	private String libelleYear;

	/**
	 * [FicheVoeuxBB.idEtablissement] Getter
	 * 
	 * @author rlaib on : 18 oct. 2014 10:44:21
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [FicheVoeuxBB.idEtablissement] Setter
	 * 
	 * @author rlaib on : 18 oct. 2014 10:44:21
	 * @param idEtablissement
	 *            the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * [FicheVoeuxBB.codeEtablissement] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 10:54:09
	 * @return the codeEtablissement
	 */
	public String getCodeEtablissement() {
		return codeEtablissement;
	}

	/**
	 * [FicheVoeuxBB.codeEtablissement] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 10:54:09
	 * @param codeEtablissement
	 *            the codeEtablissement to set
	 */
	public void setCodeEtablissement(String codeEtablissement) {
		this.codeEtablissement = codeEtablissement;
	}

	/**
	 * [FicheVoeuxBB.newEtabCode] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 10:54:09
	 * @return the newEtabCode
	 */
	public String getNewEtabCode() {
		return newEtabCode;
	}

	/**
	 * [FicheVoeuxBB.newEtabCode] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 10:54:09
	 * @param newEtabCode
	 *            the newEtabCode to set
	 */
	public void setNewEtabCode(String newEtabCode) {
		this.newEtabCode = newEtabCode;
	}

	/**
	 * [FicheVoeuxBB.libelleEtab] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 10:54:09
	 * @return the libelleEtab
	 */
	public String getLibelleEtab() {
		return libelleEtab;
	}

	/**
	 * [FicheVoeuxBB.libelleEtab] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 10:54:10
	 * @param libelleEtab
	 *            the libelleEtab to set
	 */
	public void setLibelleEtab(String libelleEtab) {
		this.libelleEtab = libelleEtab;
	}

	/**
	 * [FicheVoeuxBB.idYear] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 10:54:10
	 * @return the idYear
	 */
	public Integer getIdYear() {
		return idYear;
	}

	/**
	 * [FicheVoeuxBB.idYear] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 10:54:10
	 * @param idYear
	 *            the idYear to set
	 */
	public void setIdYear(Integer idYear) {
		this.idYear = idYear;
	}

	/**
	 * [FicheVoeuxBB.userId] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 18:13:14
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * [FicheVoeuxBB.userId] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 18:13:14
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * [FicheVoeuxBB.userFisrtName] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 18:13:14
	 * @return the userFisrtName
	 */
	public String getUserFisrtName() {
		return userFisrtName;
	}

	/**
	 * [FicheVoeuxBB.userFisrtName] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 18:13:14
	 * @param userFisrtName
	 *            the userFisrtName to set
	 */
	public void setUserFisrtName(String userFisrtName) {
		this.userFisrtName = userFisrtName;
	}

	/**
	 * [FicheVoeuxBB.userLastName] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 18:13:14
	 * @return the userLastName
	 */
	public String getUserLastName() {
		return userLastName;
	}

	/**
	 * [FicheVoeuxBB.userLastName] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 18:13:14
	 * @param userLastName
	 *            the userLastName to set
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	/**
	 * [FicheVoeuxBB.libelleYear] Getter
	 * 
	 * @author rlaib on : 13 oct. 2014 10:54:10
	 * @return the libelleYear
	 */
	public String getLibelleYear() {
		return libelleYear;
	}

	/**
	 * [FicheVoeuxBB.libelleYear] Setter
	 * 
	 * @author rlaib on : 13 oct. 2014 10:54:10
	 * @param libelleYear
	 *            the libelleYear to set
	 */
	public void setLibelleYear(String libelleYear) {
		this.libelleYear = libelleYear;
	}

	// ===================================================================
	// Listeners
	// ===================================================================
	/**
	 * [FicheVoeuxBB.handleOffreFormationChange] Method
	 * 
	 * @author rlaib on : 24 oct. 2014 14:13:18
	 * @param event
	 */
	public void handleOffreFormationChange(AjaxBehaviorEvent event) {

		initMcsApsByOffreFormation();
	}

	/**
	 * [FicheVoeuxBB.initMcsApsByOffreFormation] Method
	 * 
	 * @author Rafik on : 4 déc. 2014 13:48:39
	 */
	private void initMcsApsByOffreFormation() {
		if (selectedIdOf != null) {

			OuvertureOffreFormationDto ouvertureOffreFormationDto = ouvertureOffreFormationService
					.findById(selectedIdOf);
			if (ouvertureOffreFormationDto != null && ouvertureOffreFormationDto.getCycleId() != null) {
				selectedIdCycleOf = ouvertureOffreFormationDto.getCycleId();
				listNiveaux = initNiveaux(selectedIdCycleOf);
				if (listNiveaux != null && listNiveaux.size() > 0)
					selectedNiveauId = Integer.parseInt(listNiveaux.get(0).getValue().toString());
				else
					return;

				initMatieresApsParOffreFormation(selectedIdOf, selectedPeriodeOfId);
				initListMatieresOf(selectedIdOf, selectedPeriodeOfId);
				if (listMatieresOf != null && listMatieresOf.size() > 0) {
					SelectItemGroup firstGroup = (SelectItemGroup) listMatieresOf.get(0);
					if (firstGroup != null && firstGroup.getSelectItems().length > 0) {
						selectedIdRattachementMc = Integer.parseInt(firstGroup.getSelectItems()[0].getValue()
								.toString());
					}
				} else {
					selectedIdRattachementMc = null;
					listApsMatiere = null;
				}
				initListApsMatiere(selectedIdRattachementMc);
				if (listApsMatiere != null && listApsMatiere.size() > 0) {
					selectedIdAp = Integer.parseInt(listApsMatiere.get(0).getValue().toString());
				} else {
					selectedIdAp = null;
					listApsMatiere = null;
				}
			} else {
				listNiveaux = null;
				listMatieresOf = null;
				listApsMatiere = null;
			}
		} else {
			listNiveaux = null;
			listPeriodesParPeriodicite = null;
			listMatieresOf = null;
			listApsMatiere = null;
		}

	}

	/**
	 * [FicheVoeuxBB.handleEtablissementChange] Method
	 * 
	 * @author Rafik on : 4 déc. 2014 13:45:23
	 * @param event
	 */
	public void handleEtablissementChange(AjaxBehaviorEvent event) {

		if (selectedIdEtablissement != null && !selectedIdEtablissement.toString().equals("")
				&& selectedIdEtablissement > 0) {
			initOffresFormation(false);
		}

	}

	/**
	 * [FicheVoeuxBB.handleNiveauChange] Method
	 * 
	 * @author rlaib on : 24 oct. 2014 14:13:26
	 * @param event
	 */
	public void handleNiveauChange(AjaxBehaviorEvent event) {

		if (selectedNiveauId != null) {

			currentPeriodes = periodeService.findCurrentPeriodsByLevelId(selectedNiveauId, selectedIdYear);
			if (currentPeriodes != null && currentPeriodes.size() > 0) {
				selectedPeriodeOfId = currentPeriodes.get(0).getId();
				initListMatieresOf(selectedIdOf, selectedPeriodeOfId);
				if (listMatieresOf != null && listMatieresOf.size() > 0) {
					SelectItemGroup firstGroup = (SelectItemGroup) listMatieresOf.get(0);
					if (firstGroup != null && firstGroup.getSelectItems().length > 0) {
						selectedIdRattachementMc = Integer.parseInt(firstGroup.getSelectItems()[0].getValue()
								.toString());
					}
				} else {
					selectedIdRattachementMc = null;
					listApsMatiere = null;
				}
				initListApsMatiere(selectedIdRattachementMc);
				if (listApsMatiere != null && listApsMatiere.size() > 0) {
					selectedIdAp = Integer.parseInt(listApsMatiere.get(0).getValue().toString());
				} else {
					selectedIdAp = null;
					listApsMatiere = null;
				}
			} else {
				listMatieresOf = null;
				listApsMatiere = null;
			}
		} else {
			listPeriodesParPeriodicite = null;
			listMatieresOf = null;
			listApsMatiere = null;
		}
	}

	/**
	 * [FicheVoeuxBB.handlePeriodeChange] Method
	 * 
	 * @author rlaib on : 24 oct. 2014 14:13:33
	 * @param event
	 */
	public void handlePeriodiciteChange(AjaxBehaviorEvent event) {

		if (selectedPeriodiciteCode != null) {
			listPeriodesParPeriodicite = initPeriodesParPeriodicite(selectedPeriodiciteCode);
			// if (listPeriodesParPeriodicite != null &&
			// listPeriodesParPeriodicite.size() > 0) {
			// selectedPeriode =
			// Integer.parseInt(listPeriodesParPeriodicite.get(0).getValue().toString());
			// }
			// checkExistingFiches();
		}
	}

	public void handlePeriodeChange(AjaxBehaviorEvent event) {

		if (selectedPeriodeFicheId != null) {
			checkExistingFiches();
		}
	}

	/**
	 * [FicheVoeuxBB.handleYearChange] Method
	 * 
	 * @author rlaib on : 24 oct. 2014 14:13:39
	 * @param event
	 */
	public void handleYearChange(AjaxBehaviorEvent event) {

		checkExistingFiches();
	}

	/**
	 * [FicheVoeuxBB.onVoeuxRowToggle] Method
	 * 
	 * @author rlaib on : 24 oct. 2014 14:13:45
	 * @param event
	 */
	public void onVoeuxRowToggle(ToggleEvent event) {

		selectedVoeu = (EnseignantVoeuDto) event.getData();
		selectedVoeuIndex = selectedVoeu.getOrder();
		// if(selectedVoeu != null) {
		// List<EnseignantVoeuLigneDto> _lignes =
		// enseignantFicheVoeuxService.findLignesByIdVoeu(selectedVoeu.getId());
		// if(_lignes == null && _lignes.size()>0)
		// manageLignesVoeux(selectedVoeu.getId(),_lignes);
		// }
	}

	/**
	 * [FicheVoeuxBB.manageLignesVoeux] Method
	 * 
	 * @author rlaib on : 15 oct. 2014 14:55:05
	 * @param id
	 * @param lignes
	 */
	private void manageLignesVoeux(int ordre, List<EnseignantVoeuLigneDto> _lignes) {
		try {
			if (lignesVoeux != null) {
				if (lignesVoeux.containsKey(ordre)) {
					lignesVoeux.remove(ordre);
				}
				lignesVoeux.put(ordre, _lignes);
				checkMapLignesVoeuxForSubmit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void handleMcsListChange(AjaxBehaviorEvent event) {

		if (selectedIdRattachementMc != null) {
			initListApsMatiere(selectedIdRattachementMc);
			if (listApsMatiere != null && listApsMatiere.size() > 0) {
				selectedIdAp = Integer.parseInt(listApsMatiere.get(0).getValue().toString());
			} else {
				selectedIdAp = null;
				listApsMatiere = null;
			}
		} else {
			listPeriodesParPeriodicite = null;
			listMatieresOf = null;
			listApsMatiere = null;
		}

	}

	// ===================================================================
	// ActionListener
	// ===================================================================
	public void addFicheVoeux() {
		try {
			if (canAddFicheVoeux()) {

				if (selectedIdYear == null || userId == null) {
					return;
				} else {
					if (enseignantDto == null) {
						MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
								OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_DOSSIER_ENSEIGNANT_MANQUANT), MessageUtil
								.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
										OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_DOSSIER_ENSEIGNANT_MANQUANT));
						return;
					} else {
						selectedFicheVoeux = new EnseignantFicheVoeuxDto();
						selectedFicheVoeux.setIdAnneeAcademique(selectedIdYear);
						selectedFicheVoeux.setIdEnseignant(enseignantDto.getId());
						selectedFicheVoeux.setIdPeriode(selectedPeriodeFicheId);
						selectedFicheVoeux.setIdEtablissement(idEtablissement);
						SituationEntiteDto seDto = new SituationEntiteDto();
						seDto = situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, UtilConstants.SITUTAION_CREEE_CODE);
						selectedFicheVoeux.setIdSituation(seDto.getId());
						selectedFicheVoeux = enseignantFicheVoeuxService.insertOrUpdate(selectedFicheVoeux);
						selectedFicheVoeuxId = selectedFicheVoeux.getId();

						// HISTORIQUE DES SITUATIONS
						this.ficheHistory = situationService.getEntityOccurrenceHistory(
								UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, selectedFicheVoeux.getId());
						loadFicheVoeuxDetails();
						canCreateFicheVoeux = false;
						hasFicheVoeux = true;
						toEdit = true;
						MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
								OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_CREATE_SUCCESS), MessageUtil
								.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
										OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_CREATE_SUCCESS));

					}
				}
			} else {
				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
						OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
						OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_CREATE_FAILURE), MessageUtil
						.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_CREATE_FAILURE));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ===================================================================
	// Actions and Methods
	// ===================================================================
	/**
	 * [FicheVoeuxBB.editFicheVoeux] Method
	 * 
	 * @author rlaib on : 15 oct. 2014 09:54:52
	 */
	public void editFicheVoeux() {

		try {
			loadFicheVoeuxDetails();
		} catch (Exception ex) {

		}
	}

	/**
	 * [FicheVoeuxBB.prepareSubmitFicheVoeux] Method
	 * 
	 * @author rlaib on : 18 oct. 2014 18:12:36
	 */
	private boolean checkSubmitFicheVoeux() {

		try {
			if (selectedFicheVoeuxId != null) {

				selectedFicheVoeux = enseignantFicheVoeuxService.findById(selectedFicheVoeuxId);
				// HISTORIQUE DES SITUATIONS
				this.ficheHistory = situationService.getEntityOccurrenceHistory(
						UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, selectedFicheVoeuxId);
				voeux = enseignantFicheVoeuxService.findVoeuxByIdFiche(selectedFicheVoeuxId);
				if (voeux == null || voeux.size() == 0) {

					MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
							OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_SUBMIT_CONTROL), MessageUtil
							.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
									OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_SUBMIT_CONTROL));

					return false;
				} else {
					lignesVoeux = new HashMap<Integer, List<EnseignantVoeuLigneDto>>();
					Integer cpt = 0;
					Integer idx = 0;
					for (EnseignantVoeuDto _item : voeux) {
						idx = idx + 1;
						List<EnseignantVoeuLigneDto> _newList = enseignantFicheVoeuxService.findLignesByIdVoeu(_item
								.getId());
						cpt = cpt + _newList.size();
						lignesVoeux.put(idx, _newList);

					}

					if (cpt == 0) {

						MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
								OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_SUBMIT_CONTROL), MessageUtil
								.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
										OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_SUBMIT_CONTROL));
						return false;
					}

				}
			}
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	/**
	 * [FicheVoeuxBB.editVoeu] Method
	 * 
	 * @author rlaib on : 16 oct. 2014 10:25:24
	 */
	public void editVoeu() {

		try {

			for (EnseignantVoeuDto _voeu : voeux) {
				if (_voeu.getOrder() == selectedVoeuIndex) {
					_voeu.setEditable(true);
				}
				if (_voeu.getId() == 0)
					voeux.remove(_voeu);
			}
			canAddVoeu = false;
			canSaveVoeu = true;
			canSaveFiche = true;
		} catch (Exception ex) {

		}
	}

	/**
	 * [FicheVoeuxBB.editLigneVoeu] Method
	 * 
	 * @author rlaib on : 18 oct. 2014 10:41:40
	 */
	public void editLigneVoeu() {

		try {

			if (selectedVoeuLigneIndex != null && selectedVoeuLigneIndex > 0) {

				addOrEditDialog = true;
				lignes = lignesVoeux.get(selectedVoeuIndex);
				for (EnseignantVoeuLigneDto _ligne : lignes) {

					if (lignes.indexOf(_ligne) == selectedVoeuLigneIndex - 1) {
						_ligne.setEditable(true);
						// preparation detail ligne voeu pour affichage edition
						// dans le dialog
						currentVoeuLigne = _ligne;
						selectedIdOf = _ligne.getIdOffreFormation();
						OffreFormationDto _offreFormationDto = offreFormationService.findById(selectedIdOf);
						selectedIdCycleOf = _offreFormationDto.getIdCycle();
						initNiveaux(selectedIdCycleOf);
						selectedNiveauId = _ligne.getIdNiveau();
						initListMatieresOf(selectedIdOf, selectedPeriodeOfId);
						selectedIdRattachementMc = _ligne.getIdRattachementMc();
						initListApsMatiere(selectedIdRattachementMc);
						selectedIdAp = _ligne.getIdAp();
					}

				}
				canAddLigneVoeu = true;
				canSaveLigneVoeu = true;
				canSaveFiche = true;
			}
		} catch (Exception ex) {

		}
	}

	/**
	 * [FicheVoeuxBB.addVoeu] Method
	 * 
	 * @author rlaib on : 15 oct. 2014 09:54:46
	 */
	public void addVoeu() {

		if (canAddVoeu()) {

			if (selectedFicheVoeuxId != null) {

				if (voeux != null) {
					if (voeux.size() == this.maxVoeux) {

						Object[] params = { maxVoeux };
						MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
								OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM), MessageUtil
								.getStringValueFromBundleResourceWithParams(
										OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
										OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_MAX_VOEUX_ATTEINT, params,
										"fr"));
						return;
					}
				}
				// for (EnseignantVoeuDto _voeu : voeux) {
				// _voeu.setEditable(false);
				// }

				EnseignantVoeuDto enseignantVoeuDto = prepareNextVoeu();
				enseignantVoeuDto.setOrder(voeux.size() + 1);
				List<EnseignantVoeuLigneDto> _emptyList = new ArrayList<EnseignantVoeuLigneDto>();
				lignesVoeux.put(voeux.size() + 1, _emptyList);
				voeux.add(enseignantVoeuDto);
				canAddVoeu = true;
				canAddLigneVoeu = true;
				canSaveVoeu = true;
				canSaveFiche = true;
				initListEtablissements();
				// initOffresFormation(false);
			}
		} else {

			canAddVoeu = true;
		}

	}

	/**
	 * [FicheVoeuxBB.initListEtablissements] Method
	 * 
	 * @author Rafik on : 4 déc. 2014 13:51:22
	 */
	private void initListEtablissements() {

		try {
			if (listEtablissements == null) {
				List<RefEtablissementDto> _list = refEtablissementService.findAll();
				listEtablissements = new ArrayList<SelectItem>();
				mapEtablissements = new HashMap<Integer, String>();
				SelectItem defaultSelection = null;
				if (_list != null) {
					defaultSelection = new SelectItem(0, MessageUtil.getStringValueFromBundleResource(
							OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_LIST_ETABLISSEMENTS_DEFAULT), null, false,
							false, true);
					for (RefEtablissementDto refEtablissementDto : _list) {
						listEtablissements.add(new SelectItem(refEtablissementDto.getId(), refEtablissementDto
								.getLlEtablissementLatin()));
						mapEtablissements.put(refEtablissementDto.getId(),
								refEtablissementDto.getLlEtablissementLatin());
					}
					// selectedIdEtablissement = idEtablissement;
				} else {
					defaultSelection = new SelectItem("", MessageUtil.getStringValueFromBundleResource(
							OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_LIST_ETABLISSEMENTS_EMPTY), null, false, false,
							true);
				}
				listEtablissements.add(0, defaultSelection);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [FicheVoeuxBB.saveVoeux] Method
	 * 
	 * @author rlaib on : 16 oct. 2014 10:23:44
	 */
	public void saveVoeux() {
		try {
			voeux = enseignantFicheVoeuxService.saveVoeux(voeux);
			SituationEntiteDto seDto = new SituationEntiteDto();

			if (!toValidate) {
				toSubmit = true;
				seDto = situationService.findBySituationEntiteByCodeSituation(
						UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, UtilConstants.SITUTAION_ENREGISTREE_CODE);
				if (seDto != null && seDto.getId() > 0) {
					selectedFicheVoeux.setIdSituation(seDto.getId());
				}

			} else {
				seDto = situationService.findBySituationEntiteByCodeSituation(
						UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT,
						UtilConstants.SITUTAION_ENREGISTREE_AVANT_VALIDATION_CODE);
				toSubmit = false;
			}

			selectedFicheVoeux = enseignantFicheVoeuxService.insertOrUpdate(selectedFicheVoeux);
			SituationEntiteOccurrenceDto situation = new SituationEntiteOccurrenceDto();
			situation.setIdOccurrence(selectedFicheVoeuxId);
			situation.setIdSituation(seDto.getId());
			situation.setDateSituation(new java.util.Date());
			situationService.saveSituationOccurrence(situation);
			selectedFicheVoeux = enseignantFicheVoeuxService.findById(selectedFicheVoeuxId);
			// HISTORIQUE DES SITUATIONS
			this.ficheHistory = situationService.getEntityOccurrenceHistory(
					UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, selectedFicheVoeuxId);
			canAddVoeu = true;
			canSaveVoeu = false;
			MessageUtil.addInfoMessageWithoutKey(MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_SAVE_SUCCESS), MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_SAVE_SUCCESS));

		} catch (Exception ex) {
			MessageUtil.addErrorMessageWithoutKey(MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_SAVE_FAILURE), MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_SAVE_FAILURE));

		}
	}

	public void submitFicheVoeux() {
		try {

			if (selectedFicheVoeux == null) {

			} else {
				if (checkSubmitFicheVoeux()) {
					SituationEntiteDto seDto = new SituationEntiteDto();
					seDto = situationService.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT,
							UtilConstants.SITUTAION_SOUMISE_VALIDATION_CODE);

					if (seDto != null && seDto.getId() > 0) {
						selectedFicheVoeux.setIdSituation(seDto.getId());
						selectedFicheVoeux = enseignantFicheVoeuxService.insertOrUpdate(selectedFicheVoeux);
						loadFicheVoeuxDetails();
						canCreateFicheVoeux = false;
						toEdit = false;
						toSubmit = false;
						MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
								OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_SUBMIT_SUCCESS), MessageUtil
								.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
										OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_SUBMIT_SUCCESS));
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
					OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
					OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_SUBMIT_FAILURE), MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_SUBMIT_FAILURE));

		}
	}

	/**
	 * [FicheVoeuxBB.validateFicheVoeux] Method
	 * 
	 * @author rlaib on : 23 oct. 2014 09:16:10
	 */
	public void validateFicheVoeux() {
		try {

			if (canValidateFicheServices()) {
				if (selectedFicheVoeux == null) {

				} else {
					SituationEntiteDto seDto = new SituationEntiteDto();
					seDto = situationService.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, UtilConstants.SITUTAION_VALIDEE_CODE);

					if (seDto != null && seDto.getId() > 0) {
						selectedFicheVoeux.setIdSituation(seDto.getId());

						List<EnseignantVoeuLigneDto> enseignantVoeuLigneDtos = getLignesVoeuxFromMapLignesVoeux();

						@SuppressWarnings("unused")
						Map<Integer, Object> result = enseignantFicheVoeuxService.saveFicheVoeuxDetails(
								selectedFicheVoeux, voeux, enseignantVoeuLigneDtos);

						// Sauvegarde historique de la situation de la fiche de
						// voeux
						SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
						situationEntiteOccurrenceDto.setIdOccurrence(selectedFicheVoeux.getId());
						situationEntiteOccurrenceDto.setIdSituation(selectedFicheVoeux.getIdSituation());
						situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
						situationService.saveSituationOccurrence(situationEntiteOccurrenceDto);

						// Sauvegarde historique de la situation de la fiche de
						// services
						EnseignantFicheServicesDto enseignantFicheServicesDto = enseignantFicheServicesService
								.findById(selectedFicheVoeux.getId());
						situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
						situationEntiteOccurrenceDto.setIdOccurrence(enseignantFicheServicesDto.getId());
						situationEntiteOccurrenceDto.setIdSituation(enseignantFicheServicesDto.getIdSituation());
						situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
						situationService.saveSituationOccurrence(situationEntiteOccurrenceDto);

						// Rechargement de la liste des fiches de voeux a
						// valider
						loadFichesVoeuxToValidate();
						canCreateFicheVoeux = false;
						hasFicheVoeux = false;
						toEdit = false;
						MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
								OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_VALIDATE_SUCCESS), MessageUtil
								.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
										OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_VALIDATE_SUCCESS));

						// generation de la fiche de services correspondante
						// try {
						// SituationEntiteDto situtationEntiteGeneree =
						// situationService
						// .findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_FICHE_SERVICES_ENSEIGNANT,
						// UtilConstants.SITUTAION_GENEREE_AUTO_CODE);
						// if (situtationEntiteGeneree != null &&
						// situtationEntiteGeneree.getId() > 0) {
						//
						// enseignantFicheServicesDto.setIdSituation(situtationEntiteGeneree.getId());
						// enseignantFicheServicesDto =
						// enseignantFicheVoeuxService.generate(enseignantFicheServicesDto);
						//
						// MessageUtil
						// .addInfoMessageWithoutKey(
						// MessageUtil
						// .getStringValueFromBundleResource(
						// OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
						// OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_GENERATE_SUCCESS),
						// MessageUtil
						// .getStringValueFromBundleResource(
						// OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
						// OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_GENERATE_SUCCESS));
						// }
						//
						// } catch (Exception e) {
						// e.printStackTrace();
						// MessageUtil
						// .addErrorMessageWithoutKey(
						// MessageUtil
						// .getStringValueFromBundleResource(
						// OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
						// OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_GENERATE_FAILURE),
						// MessageUtil
						// .getStringValueFromBundleResource(
						// OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
						// OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_GENERATE_FAILURE));
						//
						// }
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
					OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
					OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_VALIDATE_FAILURE), MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_VALIDATE_FAILURE));

		}
	}

	/**
	 * [FicheVoeuxBB.canValidateFicheServices] Method
	 * 
	 * @author rlaib on : 27 oct. 2014 08:14:53
	 * @return
	 */
	private boolean canValidateFicheServices() {

		try {

			if (lignesVoeux != null && lignesVoeux.keySet().size() > 0) {
				Integer validatedLines = 0;
				for (Integer indexLigne : lignesVoeux.keySet()) {

					List<EnseignantVoeuLigneDto> _lignes = lignesVoeux.get(indexLigne);
					if (_lignes != null) {
						for (EnseignantVoeuLigneDto _ligne : _lignes) {
							if (_ligne.getEstValidee())
								validatedLines = validatedLines + 1;
						}
					}
				}
				if (validatedLines > 0)
					return true;
				else {
					MessageUtil
							.addErrorMessageWithoutKey(
									MessageUtil
											.getStringValueFromBundleResource(
													OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
													OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_VALIDATE_MSG_NO_LINE_SELECTED_TO_VALIDATE),
									MessageUtil
											.getStringValueFromBundleResource(
													OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
													OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_VALIDATE_MSG_NO_LINE_SELECTED_TO_VALIDATE));
					return false;
				}

			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return false;
	}

	public void saveFicheVoeux() {
		try {

			currentLignesVoeux = getLignesVoeuxFromMapLignesVoeux();
			SituationEntiteDto seDto = new SituationEntiteDto();
			if (!toValidate) {
				toSubmit = true;
				seDto = situationService.findBySituationEntiteByCodeSituation(
						UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, UtilConstants.SITUTAION_ENREGISTREE_CODE);

			} else {
				seDto = situationService.findBySituationEntiteByCodeSituation(
						UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT,
						UtilConstants.SITUTAION_ENREGISTREE_AVANT_VALIDATION_CODE);
				toSubmit = false;
			}
			if (seDto != null && seDto.getId() > 0) {
				selectedFicheVoeux.setIdSituation(seDto.getId());
			}

			@SuppressWarnings("unused")
			List<EnseignantVoeuDto> enseignantVoeuDtos = new ArrayList<EnseignantVoeuDto>();
			enseignantVoeuDtos = voeux;

			try {
				Map<Integer, Object> result = enseignantFicheVoeuxService.saveFicheVoeuxDetails(selectedFicheVoeux,
						enseignantVoeuDtos, currentLignesVoeux);

				// Sauvegarde historique de la situation de la fiche de voeux
				SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
				situationEntiteOccurrenceDto.setIdOccurrence(selectedFicheVoeux.getId());
				situationEntiteOccurrenceDto.setIdSituation(selectedFicheVoeux.getIdSituation());
				situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
				situationService.saveSituationOccurrence(situationEntiteOccurrenceDto);

				initMapVoeuxLignes(selectedFicheVoeuxId);
				voeux = (List<EnseignantVoeuDto>) result.get(1);
			} catch (Exception e) {
				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
						OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
						OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_SAVE_FAILURE), MessageUtil
						.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_SAVE_FAILURE));
				return;
			}

			// selectedFicheVoeux =
			// enseignantFicheVoeuxService.insertOrUpdate(selectedFicheVoeux);
			selectedFicheVoeux = enseignantFicheVoeuxService.findById(selectedFicheVoeuxId);
			// HISTORIQUE DES SITUATIONS
			this.ficheHistory = situationService.getEntityOccurrenceHistory(
					UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, selectedFicheVoeuxId);
			canAddVoeu = true;
			canAddLigneVoeu = true;
			MessageUtil.addInfoMessageWithoutKey(MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_SAVE_SUCCESS), MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_SAVE_SUCCESS));

		} catch (Exception ex) {
			MessageUtil.addErrorMessageWithoutKey(MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_SAVE_FAILURE), MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_SAVE_FAILURE));

		}
	}

	/**
	 * [FicheVoeuxBB.getLignesVoeuxFromMapLignesVoeux] Method
	 * 
	 * @author rlaib on : 7 nov. 2014 16:36:18
	 * @return
	 */
	private List<EnseignantVoeuLigneDto> getLignesVoeuxFromMapLignesVoeux() {
		List<EnseignantVoeuLigneDto> enseignantVoeuLigneDtos = new ArrayList<EnseignantVoeuLigneDto>();
		for (Integer key : lignesVoeux.keySet()) {
			for (EnseignantVoeuLigneDto _enseignantVoeuLigneDto : lignesVoeux.get(key)) {
				_enseignantVoeuLigneDto.setIndexVoeu(key);
				enseignantVoeuLigneDtos.add(_enseignantVoeuLigneDto);
			}
		}
		return enseignantVoeuLigneDtos;
	}

	public void saveLignesVoeu() {
		try {

			if (selectedVoeuId != null) {
				lignes = lignesVoeux.get(selectedVoeuId);
				for (EnseignantVoeuLigneDto _item : lignes) {
					_item.setIdEnseignantVoeu(selectedVoeuId);
				}
				lignes = enseignantFicheVoeuxService.saveLignesVoeu(lignes);
				SituationEntiteDto seDto = new SituationEntiteDto();
				if (!toValidate) {
					toSubmit = true;
					seDto = situationService.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, UtilConstants.SITUTAION_ENREGISTREE_CODE);

				} else {

					seDto = situationService.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT,
							UtilConstants.SITUTAION_ENREGISTREE_AVANT_VALIDATION_CODE);
					toSubmit = false;
				}
				selectedFicheVoeux = enseignantFicheVoeuxService.insertOrUpdate(selectedFicheVoeux);
				SituationEntiteOccurrenceDto situation = new SituationEntiteOccurrenceDto();
				situation.setIdOccurrence(selectedFicheVoeuxId);
				situation.setIdSituation(seDto.getId());
				situation.setDateSituation(new java.util.Date());
				situationService.saveSituationOccurrence(situation);

				lignes = enseignantFicheVoeuxService.findLignesByIdVoeu(selectedVoeuId);
				canAddLigneVoeu = true;
				canSaveLigneVoeu = false;
				hasValidParam = true;

				manageLignesVoeux(selectedVoeuId, lignes);
				MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
						OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
						OfConstants.CHARGE_PEDAGOGIQUE_LIGNE_VOEU_SAVE_SUCCESS), MessageUtil
						.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_LIGNE_VOEU_SAVE_SUCCESS));
			}
		} catch (Exception ex) {
			hasValidParam = false;
			MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
					OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
					OfConstants.CHARGE_PEDAGOGIQUE_LIGNE_VOEU_SAVE_FAILURE), MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_LIGNE_VOEU_SAVE_FAILURE));

		}
	}

	/**
	 * [FicheVoeuxBB.addLigneVoeu] Method
	 * 
	 * @author rlaib on : 16 oct. 2014 10:23:55
	 */
	public void addLigneVoeu() {

		if (selectedVoeuIndex != null) {

			selectedVoeu = getVoeuByOrder(selectedVoeuIndex, voeux);
			lignes = lignesVoeux.get(selectedVoeuIndex);

			if (lignes != null) {
				// if(lignes.size() == this.maxLignes) {

				// Object[] params = {maxLignes};
				// MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM)
				// ,
				// MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_MAX_LIGNES_VOEU_ATTEINT,
				// params,"fr"));
				// return;
				// }
			} else
				lignes = new ArrayList<EnseignantVoeuLigneDto>();

			EnseignantVoeuLigneDto oneDto = currentVoeuLigne;
			if (oneDto == null) {
				oneDto = new EnseignantVoeuLigneDto();
				currentVoeuLigne = new EnseignantVoeuLigneDto();
			}
			PeriodeDto _periode = periodeService.findByLevelAndYearPeriod(selectedNiveauId, selectedPeriodeFicheId);
			if (_periode == null)
				return;
			selectedPeriodeOfId = _periode.getId();

			oneDto = prepareNextLigneVoeu();
			oneDto.setLibelleEtablissement(mapEtablissements.get(selectedIdEtablissement));
			oneDto.setIdOffreFormation(selectedIdOf);
			oneDto.setIdNiveau(selectedNiveauId);
			oneDto.setIdPeriode(selectedPeriodeOfId);
			oneDto.setIdRattachementMc(selectedIdRattachementMc);
			oneDto.setIdAp(selectedIdAp);
			oneDto.setLibelleOffreFormation(getLabelItemFromListById(listOffresFormation, selectedIdOf));
			oneDto.setLibelleNiveau(getLabelItemFromListById(listNiveaux, selectedNiveauId));
			oneDto.setLibelleMc(getLabelItemFromListById(listMatieresOf, selectedIdRattachementMc));
			oneDto.setCodeTypeAp(getLabelItemFromListById(listApsMatiere, selectedIdAp));
			oneDto.setOrder(lignes.size() + 1);
			oneDto.setIndexVoeu(selectedVoeuIndex);

			// // Test si edition ligne de voeu
			// if(currentVoeuLigne != null) {
			// Integer cpt = 0;
			// boolean found = false;
			// while ((cpt < lignes.size()) && !found) {
			//
			// EnseignantVoeuLigneDto _item =lignes.get(cpt);
			// if (_item.getOrder() == selectedVoeuLigneIndex) {
			// lignes.remove(_item);
			// found = true;
			// manageLignesVoeux(selectedVoeuIndex, lignes);
			// }
			// else
			// cpt = cpt +1;
			// }
			// }
			// else
			// currentVoeuLigne = new EnseignantVoeuLigneDto();

			List<EnseignantVoeuLigneDto> _list = getLignesVoeuxFromMapLignesVoeux();
			currentVoeuLigne = oneDto;
			_list.add(currentVoeuLigne);
			if (canAddLigneVoeu(_list)) {
				lignes.add(currentVoeuLigne);
				manageLignesVoeux(selectedVoeuIndex, lignes);
				selectedLigneId = 0;
				selectedVoeuLigneIndex = currentVoeuLigne.getOrder();
				canSaveLigneVoeu = true;
				canSaveVoeu = true;
				canSaveFiche = true;
				currentVoeuLigne = null;
			} else
				return;

		} else {

			Object[] params = { "[" + OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_MAX_LIGNES_VOEU + "]",
					"[" + this.selectedFicheVoeux.getLibellePeriode() + "]" };
			MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
					OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
					OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM), MessageUtil
					.getStringValueFromBundleResourceWithParams(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM, params, "fr"));

		}

	}

	public void updateLigneVoeu() {

		if (selectedVoeuIndex != null) {

			selectedVoeu = getVoeuByOrder(selectedVoeuIndex, voeux);
			lignes = lignesVoeux.get(selectedVoeuIndex);
			EnseignantVoeuLigneDto oneDto = currentVoeuLigne;
			oneDto.setIdOffreFormation(selectedIdOf);
			oneDto.setIdNiveau(selectedNiveauId);
			oneDto.setIdRattachementMc(selectedIdRattachementMc);
			oneDto.setIdAp(selectedIdAp);
			oneDto.setLibelleOffreFormation(getLabelItemFromListById(listOffresFormation, selectedIdOf));
			oneDto.setLibelleNiveau(getLabelItemFromListById(listNiveaux, selectedNiveauId));
			oneDto.setLibelleMc(getLabelItemFromListById(listMatieresOf, selectedIdRattachementMc));
			oneDto.setCodeTypeAp(getLabelItemFromListById(listApsMatiere, selectedIdAp));
			oneDto.setOrder(selectedVoeuLigneIndex);
			// Test si edition ligne de voeu
			boolean found = false;
			if (currentVoeuLigne != null) {
				Integer cpt = 0;
				while ((cpt < lignes.size()) && !found) {

					EnseignantVoeuLigneDto _item = lignes.get(cpt);
					if (_item.getOrder() == selectedVoeuLigneIndex) {
						// lignes.remove(_item);
						found = true;
						manageLignesVoeux(selectedVoeuIndex, lignes);
					} else
						cpt = cpt + 1;
				}
			}

			List<EnseignantVoeuLigneDto> _list = getLignesVoeuxFromMapLignesVoeux();
			_list.remove(currentVoeuLigne);
			_list.add(oneDto);
			if (canAddLigneVoeu(_list)) {
				if (found)
					lignes.remove(currentVoeuLigne);
				lignes.add(oneDto.getOrder() - 1, oneDto);
				currentVoeuLigne = oneDto;
				manageLignesVoeux(selectedVoeuIndex, lignes);
				selectedLigneId = 0;
				selectedVoeuLigneIndex = currentVoeuLigne.getOrder();
				canSaveLigneVoeu = true;
				canSaveVoeu = true;
				canSaveFiche = true;
				currentVoeuLigne = null;
			}

		}
	}

	public void prepareCurrentLigneVoeu() {

		if (selectedVoeuIndex != null) {
			initListEtablissements();
			currentVoeuLigne = null;
			addOrEditDialog = false;
		} else {

			Object[] params = { "[" + OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_MAX_LIGNES_VOEU + "]",
					"[" + this.selectedFicheVoeux.getLibellePeriode() + "]" };
			MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
					OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
					OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM), MessageUtil
					.getStringValueFromBundleResourceWithParams(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM, params, "fr"));

		}

	}

	/**
	 * [FicheVoeuxBB.getVoeuByOrder] Method
	 * 
	 * @author rlaib on : 25 oct. 2014 12:13:58
	 * @param voeux2
	 * @return
	 */
	private EnseignantVoeuDto getVoeuByOrder(Integer order, List<EnseignantVoeuDto> listVoeux) {

		for (EnseignantVoeuDto _enseignantVoeuDto : listVoeux) {

			if (_enseignantVoeuDto.getOrder() == order)
				return _enseignantVoeuDto;

		}
		return null;
	}

	/**
	 * [FicheVoeuxBB.removeVoeu] Method
	 * 
	 * @author rlaib on : 16 oct. 2014 10:24:02
	 */
	public void removeVoeu() {
		try {
			if (this.selectedVoeuIndex != null) {

				// recuperer le voeu selectionn� depuis le MODELE DE LA VUE
				EnseignantVoeuDto _currentVoeu = new EnseignantVoeuDto();
				for (EnseignantVoeuDto _enseignantVoeuDto : voeux) {
					if (_enseignantVoeuDto.getOrder() == selectedVoeuIndex)
						_currentVoeu = _enseignantVoeuDto;
				}

				if (_currentVoeu.getId() == 0) {

					// recuperer la ligne depuis le Map Modele de la vue
					voeux.remove(_currentVoeu);
					lignesVoeux.remove(selectedVoeuIndex);
				} else {

					// Supprimer le Voeu
					String entityName = EnseignantVoeuDto.class.getName();
					if (selectedVoeuId > 0)
						enseignantFicheVoeuxService.remove(selectedVoeuId, entityName);

					SituationEntiteDto seDto = new SituationEntiteDto();
					if (!toValidate) {
						toSubmit = true;
						seDto = situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, UtilConstants.SITUTAION_ENREGISTREE_CODE);
						if (seDto != null && seDto.getId() > 0) {
							selectedFicheVoeux.setIdSituation(seDto.getId());
						}

					} else {
						seDto = situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT,
								UtilConstants.SITUTAION_ENREGISTREE_AVANT_VALIDATION_CODE);
						toSubmit = false;
					}
					selectedFicheVoeux.setIdSituation(seDto.getId());
					selectedFicheVoeux = enseignantFicheVoeuxService.insertOrUpdate(selectedFicheVoeux);
					SituationEntiteOccurrenceDto situation = new SituationEntiteOccurrenceDto();
					situation.setIdOccurrence(selectedVoeuId);
					situation.setIdSituation(seDto.getId());
					situation.setDateSituation(new java.util.Date());
					situationService.saveSituationOccurrence(situation);
					initMapVoeuxLignes(selectedFicheVoeuxId);
				}
				checkMapLignesVoeuxForSubmit();
				canAddVoeu = true;
				MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
						OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
						OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_DELETE_SUCCESS), MessageUtil
						.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_DELETE_SUCCESS));

			}
		} catch (Exception e) {

			e.printStackTrace();
			MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
					OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
					OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_DELETE_FAILURE), MessageUtil.getStringValueFromBundleResource(
					OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
					OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_DELETE_FAILURE));

		}
	}

	/**
	 * [FicheVoeuxBB.removeLigneVoeu] Method
	 * 
	 * @author rlaib on : 18 oct. 2014 10:41:53
	 */
	public void removeLigneVoeu() {
		try {
			if (this.selectedVoeuIndex != null && selectedVoeuLigneIndex != null) {

				// recuperer la ligne voeu selectionn�e depuis le MODELE DE LA
				// VUE
				EnseignantVoeuLigneDto _currentVoeu = new EnseignantVoeuLigneDto();
				lignes = lignesVoeux.get(selectedVoeuIndex);

				for (EnseignantVoeuLigneDto _item : lignes) {
					if (lignes.indexOf(_item) == selectedVoeuLigneIndex - 1)
						_currentVoeu = _item;
				}
				if (_currentVoeu.getId() == 0) {

					// recuperer la ligne depuis le Map Modele de la vue
					lignes.remove(_currentVoeu);
				} else {

					// Supprimer la ligne Voeu depuis la BDD
					String entityName = EnseignantVoeuLigneDto.class.getName();
					enseignantFicheVoeuxService.remove(selectedLigneId, entityName);
					SituationEntiteDto seDto = new SituationEntiteDto();
					if (!toValidate) {
						toSubmit = true;
						seDto = situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, UtilConstants.SITUTAION_ENREGISTREE_CODE);
						if (seDto != null && seDto.getId() > 0) {
							selectedFicheVoeux.setIdSituation(seDto.getId());
						}

					} else {
						seDto = situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT,
								UtilConstants.SITUTAION_ENREGISTREE_AVANT_VALIDATION_CODE);
						toSubmit = false;
					}

					selectedFicheVoeux.setIdSituation(seDto.getId());
					selectedFicheVoeux = enseignantFicheVoeuxService.insertOrUpdate(selectedFicheVoeux);
					SituationEntiteOccurrenceDto situation = new SituationEntiteOccurrenceDto();
					situation.setIdOccurrence(selectedFicheVoeuxId);
					situation.setIdSituation(seDto.getId());
					situation.setDateSituation(new java.util.Date());
					situationService.saveSituationOccurrence(situation);
					lignes = enseignantFicheVoeuxService.findLignesByIdVoeu(selectedVoeuId);
				}
				manageLignesVoeux(selectedVoeuIndex, lignes);
				canAddLigneVoeu = true;
				MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
						OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
						OfConstants.CHARGE_PEDAGOGIQUE_LIGNE_VOEU_DELETE_SUCCESS), MessageUtil
						.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_LIGNE_VOEU_DELETE_SUCCESS));

			}
		} catch (Exception e) {

			e.printStackTrace();
			MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
					OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
					OfConstants.CHARGE_PEDAGOGIQUE_LIGNE_VOEU_DELETE_FAILURE), MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_LIGNE_VOEU_DELETE_FAILURE));

		}
	}

	// ===================================================================
	// Functions Helper
	// ===================================================================
	/**
	 * [FicheVoeuxBB.loadFicheVoeuxDtails] Method
	 * 
	 * @author rlaib on : 13 oct. 2014 11:40:01
	 */
	public void loadFicheVoeuxDetails() {

		if (selectedFicheVoeux != null && selectedIdYear != null) {

			enseignantDto = enseignantFicheVoeuxService.getEnseignantById(selectedFicheVoeux.getIdEnseignant());
			selectedFicheVoeuxId = selectedFicheVoeux.getId();
			selectedIdYear = selectedFicheVoeux.getIdAnneeAcademique();
			selectedPeriodiciteCode = selectedFicheVoeux.getCodePeriodicite();
			selectedPeriodeFicheId = selectedFicheVoeux.getIdPeriode();
			hasFicheVoeux = true;

			// HISTORIQUE DES SITUATIONS
			this.ficheHistory = situationService.getEntityOccurrenceHistory(
					UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, selectedFicheVoeuxId);
			initMapVoeuxLignes(selectedFicheVoeuxId);

			// OffreFormationDto oneOf =
			// offreFormationService.findById(selectedFicheVoeux.getIdOffreFormation());
			canAddVoeu = true;
			// toEdit = (!selectedFicheVoeux.isEstSoumise() && toSubmit) ||
			// (selectedFicheVoeux.isEstSoumise() && toValiate);
			SituationEntiteDto created = situationService

			.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT,
					UtilConstants.SITUTAION_CREEE_CODE);

			SituationEntiteDto saved = situationService.findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, UtilConstants.SITUTAION_ENREGISTREE_CODE);

			SituationEntiteDto savedBeforeValidation = situationService.findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT,
					UtilConstants.SITUTAION_ENREGISTREE_AVANT_VALIDATION_CODE);

			SituationEntiteDto submitted = situationService.findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, UtilConstants.SITUTAION_SOUMISE_VALIDATION_CODE);

			SituationEntiteDto validated = situationService.findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, UtilConstants.SITUTAION_VALIDEE_CODE);

			if (!toEdit)
				toEdit = selectedFicheVoeux.getIdSituation() == created.getId()
						|| selectedFicheVoeux.getIdSituation() == saved.getId();

			if (!toValidate)
				toValidate = selectedFicheVoeux.getIdSituation() == savedBeforeValidation.getId()
						|| selectedFicheVoeux.getIdSituation() == submitted.getId();

			if (!toSubmit)
				toSubmit = (!toValidate)
						&& (selectedFicheVoeux.getIdSituation() == created.getId() || selectedFicheVoeux
								.getIdSituation() == saved.getId());

			canSaveFiche = toValidate || (toEdit && !toValidate)
					&& (!(validated.getId() == selectedFicheVoeux.getIdSituation()));

			// initOffresFormation(false);
			initParametresPeriode();
		} else {
			canAddVoeu = false;
			canAddLigneVoeu = false;
			Object[] params = { "[" + OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_MAX_VOEUX + "]",
					"[" + this.selectedFicheVoeux.getLibellePeriode() + "]" };
			MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
					OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
					OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM), MessageUtil
					.getStringValueFromBundleResourceWithParams(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM, params, "fr"));

		}
	}

	/**
	 * [FicheVoeuxBB.initMatieresApsParOffreFormation] Method
	 * 
	 * @author rlaib on : 4 nov. 2014 14:48:06
	 * @param selectedIdOf2
	 * @param _firstPeriodId
	 */
	private void initMatieresApsParOffreFormation(Integer idOffre, Integer idPeriode) {

		listMatieresOf = initMatieresOffreFormation(idOffre, idPeriode);
		apsMatieres = new HashMap<Integer, List<AtomePedagogiqueDto>>();
		apsMatieres.clear();
		if (listMatieresOf != null && listMatieresOf.size() > 0) {

			for (Object _itemGroup : listMatieresOf) {
				if (_itemGroup instanceof SelectItemGroup) {

					for (SelectItem _item : ((SelectItemGroup) _itemGroup).getSelectItems()) {
						Integer _idMc = Integer.parseInt(_item.getValue().toString());
						List<AtomePedagogiqueDto> _listAps = atomePedagogiqueService.findByRattachementMC(_idMc);
						apsMatieres.put(_idMc, _listAps);
					}
				}
			}

			SelectItemGroup firstGroup = (SelectItemGroup) listMatieresOf.get(0);
			if (firstGroup != null && firstGroup.getSelectItems().length > 0) {
				Integer idRattachementMc = Integer.parseInt(firstGroup.getSelectItems()[0].getValue().toString());
				listApsMatiere = initApList(idRattachementMc);
			}
		}

	}

	/**
	 * [FicheVoeuxBB.initMapVoeuxLignes] Method
	 * 
	 * @author rlaib on : 16 oct. 2014 15:31:29
	 * @param idFiche
	 */
	private void initMapVoeuxLignes(Integer idFiche) {
		try {
			voeux = enseignantFicheVoeuxService.findVoeuxByIdFiche(idFiche);
			lignesVoeux = new HashMap<Integer, List<EnseignantVoeuLigneDto>>();
			if (voeux != null && voeux.size() > 0) {
				Integer idx = 0;
				if (voeux != null && voeux.size() > 0) {
					lignesVoeux.clear();
					for (EnseignantVoeuDto _item : voeux) {
						idx = idx + 1;
						List<EnseignantVoeuLigneDto> _newList = enseignantFicheVoeuxService.findLignesByIdVoeu(_item
								.getId());
						if (_newList != null && _newList.size() > 0)
							canSubmitFiche = true;
						lignesVoeux.put(idx, _newList);
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	/**
	 * [FicheVoeuxBB.initSessionInfos] Method
	 * 
	 * @author rlaib on : 13 oct. 2014 10:58:45
	 */
	void initSessionInfos() {
		try {
			this.idEtablissement = this.sessionBean.getIdEtablissement();
			this.codeEtablissement = this.sessionBean.getAncienCodeEtablissement();
			this.libelleEtab = this.sessionBean.getLlLatinEtablissement();
			this.newEtabCode = this.sessionBean.getCodeEtablissement();
			this.newEtabId = this.sessionBean.getIdEtablissement();
			this.idYear = this.sessionBean.getIdAnneeAcademique();
			this.libelleYear = this.sessionBean.getCodeAnneeAcademique();
			this.userId = this.sessionBean.getSessionBean().getUser().getId();
			this.userFisrtName = this.sessionBean.getSessionBean().getUser().getPrenomLatin();
			this.userLastName = this.sessionBean.getSessionBean().getUser().getNomLatin();
			enseignantDto = enseignantFicheVoeuxService.getEnseignantByIdUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [FicheVoeuxBB.checkPageRequirements] Method
	 * 
	 * @author rlaib on : 13 oct. 2014 10:58:23
	 * @return
	 */
	private boolean checkPageRequirements() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * [FicheVoeuxBB.initNomenclatures] Method
	 * 
	 * @author rlaib on : 13 oct. 2014 11:13:10
	 */
	void initNomenclatures() {

		try {
			listTypeFormation = initNomenclatureList(OfConstants.NC_NAME_TYPE_FORMATION);
			listPeriodicites = initNomenclatureList(OfConstants.NC_NAME_CYCLE_PERIODE_PERIODICITE);
			if (listPeriodicites != null && listPeriodicites.size() > 0) {
				String codePeriodicite = listPeriodicites.get(0).getValue().toString();
				listPeriodesParPeriodicite = initPeriodesParPeriodicite(codePeriodicite);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<SelectItem> initPeriodesParPeriodicite(String codePeriodicite) {

		List<SelectItem> result = new ArrayList<SelectItem>();
		try {
			List<NomenclatureDto> _list = nomenclatureService.findNcCompositeList(
					OfConstants.NC_NAME_CYCLE_PERIODE_PERIODICITE, codePeriodicite,
					OfConstants.NC_NAME_PERIODE_PAR_PERIODICITE);
			SelectItem defaultSelection = null;

			if (_list != null && _list.size() > 0) {
				defaultSelection = new SelectItem(0, MessageUtil.getStringValueFromBundleResource(
						OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
						OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_LIST_PERIDOES_DEFAULT), null, false, false, true);

				for (NomenclatureDto _nomenclatureDto : _list) {
					result.add(new SelectItem(_nomenclatureDto.getId(), _nomenclatureDto.getLibelleLongFr()));
				}
			} else {
				defaultSelection = new SelectItem("", MessageUtil.getStringValueFromBundleResource(
						OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
						OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_LIST_OFFRE_FORMATION_EMPTY), null, false, false,
						true);
			}
			result.add(0, defaultSelection);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * [FicheVoeuxBB.initNomenclatureList] Method
	 * 
	 * @author rlaib on : 13 oct. 2014 11:21:12
	 * @param ncNameTypeFormation
	 * @return
	 */
	public List<SelectItem> initNomenclatureList(String ncNameTypeFormation) {

		try {
			List<NomenclatureDto> _list = nomenclatureService.findByName(ncNameTypeFormation);
			List<SelectItem> result = new ArrayList<SelectItem>();
			for (NomenclatureDto _nomenclatureDto : _list) {
				result.add(new SelectItem(_nomenclatureDto.getCode(), _nomenclatureDto.getLibelleLongFr()));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * [FicheVoeuxBB.initListeFichesVoeux] Method
	 * 
	 * @author rlaib on : 13 oct. 2014 11:58:46
	 */
	void initListeFichesVoeux(Integer idEtab, Long idEnseignant, Integer idAnnee, Integer idNcPeriode, boolean toSearch) {

		try {
			fichesVoeux = enseignantFicheVoeuxService.findFichesByEtabByUserByOfByPeriodeByYear(idEtab, idEnseignant,
					idAnnee, selectedPeriodeFicheId, toSearch);
			if (fichesVoeux != null && fichesVoeux.size() > 0) {
				canCreateFicheVoeux = false;
				hasFicheVoeux = true;
				selectedFicheVoeux = fichesVoeux.get(0);
				loadFicheVoeuxDetails();
				canCreateFicheVoeux = false;
			} else {
				canCreateFicheVoeux = true;
				hasFicheVoeux = false;
				selectedFicheVoeux = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [FicheVoeuxBB.initPeriodes] Method
	 * 
	 * @author rlaib on : 13 oct. 2014 14:20:57
	 */
	void initOffresFormation(boolean withDefaultValue) {
		try {

			if (selectedIdYear != null && selectedFicheVoeux != null) {
				List<SelectItem> result = new ArrayList<SelectItem>();
				OuvertureOffreFormationDto searchDto = new OuvertureOffreFormationDto();
				searchDto.setIdEtablissement(this.selectedIdEtablissement);
				searchDto.setAnneeAcademiqueId(this.selectedIdYear);
				searchDto.setEstOuverte(true);
				List<OuvertureOffreFormationDto> _list = ouvertureOffreFormationService.findAdvanced(searchDto);
				selectedPeriodiciteCode = selectedFicheVoeux.getCodePeriodicite();
				selectedPeriodeFicheId = selectedFicheVoeux.getIdPeriode();
				SelectItem defaultSelection = null;
				if (_list != null && _list.size() > 0) {
					defaultSelection = new SelectItem("", MessageUtil.getStringValueFromBundleResource(
							OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_LIST_OFFRE_FORMATION_DEFAULT)
							+ _list.size(), null, false, false, true);
					for (OuvertureOffreFormationDto _ouvertureOffreFormationDto : _list) {

						String libelleFr = _ouvertureOffreFormationDto.getOfLibelleLongFr();
						if (libelleFr != null && !libelleFr.trim().isEmpty() && !libelleFr.equals("null"))
							result.add(new SelectItem(_ouvertureOffreFormationDto.getId(), libelleFr));
						else
							result.add(new SelectItem(_ouvertureOffreFormationDto.getId(), _ouvertureOffreFormationDto
									.getOffreFormationCode()));

					}

					if (withDefaultValue) {
						result.add(0, defaultSelection);
						selectedIdOf = null;
						selectedIdCycleOf = null;
					} else {
						OuvertureOffreFormationDto oneOf = _list.get(0);
						if (oneOf != null && oneOf.getCycleId() != null) {
							selectedIdOf = oneOf.getId();
							selectedIdCycleOf = oneOf.getCycleId();
						}
					}
				}
				listOffresFormation = result;
				OuvertureOffreFormationDto oneOf = null;
				if (listOffresFormation != null && listOffresFormation.size() > 0)
					oneOf = _list.get(0);
				if (oneOf != null && oneOf.getCycleId() != null) {
					selectedIdOf = oneOf.getId();
					selectedIdCycleOf = oneOf.getCycleId();
					listNiveaux = initNiveaux(selectedIdCycleOf);
					if (listNiveaux != null && listNiveaux.size() > 0) {
						selectedNiveauId = Integer.parseInt(listNiveaux.get(0).getValue().toString());
						PeriodeDto _periodeDto = periodeService.findByLevelAndYearPeriod(selectedNiveauId,
								selectedPeriodeFicheId);

						if (_periodeDto != null) {
							selectedPeriodeOfId = _periodeDto.getId();
							initListMatieresOf(selectedIdOf, selectedPeriodeOfId);
							if (listMatieresOf != null && listMatieresOf.size() > 0) {
								SelectItemGroup firstGroup = (SelectItemGroup) listMatieresOf.get(0);
								if (firstGroup != null && firstGroup.getSelectItems().length > 0) {
									selectedIdRattachementMc = Integer.parseInt(firstGroup.getSelectItems()[0]
											.getValue().toString());
								}
							} else {
								selectedIdRattachementMc = null;
								listApsMatiere = null;
							}
							initListApsMatiere(selectedIdRattachementMc);
							if (listApsMatiere != null && listApsMatiere.size() > 0) {
								selectedIdAp = Integer.parseInt(listApsMatiere.get(0).getValue().toString());
							} else {
								selectedIdAp = null;
								listApsMatiere = null;
							}
						}

					} else
						return;

					initParametresPeriode();
				} else {

					listNiveaux = null;
					listMatieresOf = null;
					listApsMatiere = null;
					listOffresFormation = null;
					canAddVoeu = false;
					canSaveFiche = false;
					canCreateFicheVoeux = false;
					hasFicheVoeux = false;
					Object[] params = { this.libelleYear };
					MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
							OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_LISTE_OFFRE_FORMATION_VIDE),
							MessageUtil
									.getStringValueFromBundleResourceWithParams(
											OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
											OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_LISTE_OFFRE_FORMATION_VIDE,
											params, "fr"));

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [FicheVoeuxBB.initListMatieresOf] Method
	 * 
	 * @author rlaib on : 6 nov. 2014 17:22:54
	 */
	private void initListMatieresOf(Integer _idOf, Integer _idPeriode) {

		listMatieresOf = initMatieresOffreFormation(_idOf, _idPeriode);

	}

	/**
	 * [FicheVoeuxBB.initListApsMatiere] Method
	 * 
	 * @author rlaib on : 6 nov. 2014 17:27:04
	 */
	private void initListApsMatiere(Integer _idRattachementMc) {

		listApsMatiere = initApList(_idRattachementMc);

	}

	/**
	 * [FicheVoeuxBB.initNiveaux] Method
	 * 
	 * @author rlaib on : 24 oct. 2014 14:12:53
	 * @param idCycle
	 * @return
	 */
	private List<SelectItem> initNiveaux(Integer idCycle) {
		try {
			if (idCycle == null || idCycle == 0)
				return null;

			List<SelectItem> result = new ArrayList<SelectItem>();
			List<NiveauDto> _list = niveauService.findByCycleId(idCycle);

			if (_list != null && _list.size() > 0) {

				for (NiveauDto _niveauDto : _list) {
					result.add(new SelectItem(_niveauDto.getId(), _niveauDto.getLibelleLongLt() + " - "
							+ _niveauDto.getCode()));
				}
			}
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * [FicheVoeuxBB.initApList] Method
	 * 
	 * @author rlaib on : 24 oct. 2014 14:12:41
	 * @param idRattachementMc
	 * @return
	 */
	private List<SelectItem> initApList(Integer idRattachementMc) {
		try {
			if (idRattachementMc == null || idRattachementMc == 0)
				return null;

			List<SelectItem> result = new ArrayList<SelectItem>();
			List<AtomePedagogiqueDto> _list = atomePedagogiqueService.findByRattachementMC(idRattachementMc);

			if (_list != null && _list.size() > 0) {

				for (AtomePedagogiqueDto _atomePedagogiqueDto : _list) {
					result.add(new SelectItem(_atomePedagogiqueDto.getId(), _atomePedagogiqueDto.getNcTypeApCode()));
				}
			}
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * [FicheVoeuxBB.initMatieresOffreFormation] Method
	 * 
	 * @author rlaib on : 24 oct. 2014 14:12:35
	 * @param idOf
	 * @param idPeriode
	 * @return
	 */
	private List<SelectItem> initMatieresOffreFormation(Integer idOf, Integer idPeriode) {
		try {

			if (idOf == null || idOf == 0 || idPeriode == null || idPeriode == 0)
				return null;

			return utilBean.loadMcGroupedByUe(idOf, idPeriode);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * [FicheVoeuxBB.initPeriodes] Method
	 * 
	 * @author rlaib on : 24 oct. 2014 14:12:29
	 * @param idNiveau
	 * @return
	 */
	private List<SelectItem> initPeriodes(Integer idNiveau) {
		try {

			List<SelectItem> result = new ArrayList<SelectItem>();
			List<PeriodeDto> _list = periodeService.findByLevelId(idNiveau);

			if (_list != null && _list.size() > 0) {
				for (PeriodeDto _periodeDto : _list) {
					result.add(new SelectItem(_periodeDto.getId(), _periodeDto.getLibelleLongLt()));
				}
			}
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * [FicheVoeuxBB.canAddFicheVoeux] Method
	 * 
	 * @author rlaib on : 13 oct. 2014 17:51:00
	 * @return
	 */
	private boolean canAddFicheVoeux() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * [FicheVoeuxBB.prepareNextVoeu] Method
	 * 
	 * @author rlaib on : 14 oct. 2014 17:22:29
	 * @return
	 */
	private EnseignantVoeuDto prepareNextVoeu() {

		EnseignantVoeuDto _dto = null;
		try {
			Integer maxPriority = 0;
			for (EnseignantVoeuDto _item : voeux) {

				if (maxPriority < _item.getPriorite())
					maxPriority = _item.getPriorite();

			}
			_dto = new EnseignantVoeuDto();
			_dto.setPriorite(maxPriority + 1);
			_dto.setIdFicheVoeux(selectedFicheVoeuxId);
			_dto.setEditable(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return _dto;
	}

	private EnseignantVoeuLigneDto prepareNextLigneVoeu() {

		EnseignantVoeuLigneDto _dto = null;
		try {
			_dto = new EnseignantVoeuLigneDto();
			_dto.setIdPriorite(selectedVoeu.getPriorite());
			_dto.setIdEnseignantVoeu(selectedVoeu.getId());
			_dto.setEditable(true);
			if (listNiveaux != null) {
				try {
					_dto.setIdNiveau(Integer.parseInt(listNiveaux.get(0).getValue().toString()));
					_dto.setLibelleNiveau(listNiveaux.get(0).getLabel().toString());
				} catch (Exception e) {

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return _dto;
	}

	/**
	 * [FicheVoeuxBB.canAddVoeu] Method
	 * 
	 * @author rlaib on : 14 oct. 2014 17:23:50
	 * @return
	 */
	private boolean canAddVoeu() {

		try {
			// if(voeux != null) {
			//
			// for (EnseignantVoeuDto _voeu : voeux) {
			// // Il y a deja une ligne en instance
			// if(_voeu.getId() == 0)
			// return false;
			// }
			// }
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * [FicheVoeuxBB.canEditFicheVoeu] Method
	 * 
	 * @author rlaib on : 24 oct. 2014 14:11:49
	 * @return
	 */
	private boolean canEditFicheVoeu() {

		try {
			PeriodeParamDto param = periodeService.findParamByCodeByPeriodiciteByYear(
					OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_MAX_VOEUX, this.selectedIdYear,
					selectedPeriodiciteCode);
			try {
				maxVoeux = Integer.parseInt(param.getValeur().toString());
			} catch (Exception e) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * [FicheVoeuxBB.canAddLigneVoeu] Method
	 * 
	 * @author rlaib on : 24 oct. 2014 14:11:55
	 * @return
	 */
	private boolean canAddLigneVoeu(List<EnseignantVoeuLigneDto> _listLignesVoeux) {

		try {

			// Verification des doublons EnseignantVoeuLigneDto
			RequestContext context = RequestContext.getCurrentInstance();
			Set<EnseignantVoeuLigneDto> setEnseignantVoeuLigneDtos = new HashSet<>(_listLignesVoeux);
			if (setEnseignantVoeuLigneDtos.size() < _listLignesVoeux.size()) {
				/* There are duplicates */
				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
						OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
						OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_FORMULATE_FAILURE), MessageUtil
						.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_VOEUX_FORMULATE_FAILURE));

				context.addCallbackParam("isValid", false);
				return false;
			} else {
				context.addCallbackParam("isValid", true);
				return true;
			}
			// PeriodeParamDto param =
			// periodeService.findParamByCodeByPeriodByYear(OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_MAX_LIGNES_VOEU,
			// this.idYear, selectedIdPeriode);
			// maxLignes = Integer.parseInt(param.getValeur().toString());

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * [FicheVoeuxBB.initParametres] Method
	 * 
	 * @author rlaib on : 14 oct. 2014 17:27:53
	 */
	private void initParametresPeriode() {

		try {
			this.periodeParams = periodeService.findPeriodParamByPeriodiciteByYear(this.selectedIdYear,
					selectedPeriodiciteCode);
			PeriodeParamDto param1 = periodeService.findParamByCodeByPeriodiciteByYear(
					OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_MAX_VOEUX, this.selectedIdYear,
					selectedPeriodiciteCode);
			try {
				if (param1 != null) {
					maxVoeux = Integer.parseInt(param1.getValeur().toString());
				} else {
					maxVoeux = 5;
				}
			} catch (NumberFormatException nfe) {
				Object[] params = { "[" + OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_MAX_VOEUX + "]",
						"[" + this.selectedFicheVoeux.getLibellePeriode() + "]" };
				MessageUtil
						.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
								OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM), MessageUtil
								.getStringValueFromBundleResourceWithParams(
										OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
										OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM,
										params, "fr"));

				return;

			}
			PeriodeParamDto param2 = periodeService.findParamByCodeByPeriodiciteByYear(
					OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_NOMBRE_SEMAINES, this.selectedIdYear,
					selectedPeriodiciteCode);
			if (param2 == null) {
				Object[] params = { "[" + OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_NOMBRE_SEMAINES + "]",
						"[" + this.selectedFicheVoeux.getLibellePeriode() + "]" };
				MessageUtil
						.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
								OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM), MessageUtil
								.getStringValueFromBundleResourceWithParams(
										OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
										OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM,
										params, "fr"));

				return;

			}
			PeriodeParamDto param3 = periodeService.findParamByCodeByPeriodiciteByYear(
					OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_VOLUME_HORAIRE_HEBDO_CM, this.selectedIdYear,
					selectedPeriodiciteCode);
			if (param3 == null) {
				Object[] params = { "[" + OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_VOLUME_HORAIRE_HEBDO_CM + "]",
						"[" + this.selectedFicheVoeux.getLibellePeriode() + "]" };
				MessageUtil
						.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
								OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM), MessageUtil
								.getStringValueFromBundleResourceWithParams(
										OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
										OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM,
										params, "fr"));

				return;
			}
			PeriodeParamDto param4 = periodeService.findParamByCodeByPeriodiciteByYear(
					OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_VOLUME_HORAIRE_HEBDO_TD, this.selectedIdYear,
					selectedPeriodiciteCode);
			if (param4 == null) {
				Object[] params = { "[" + OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_VOLUME_HORAIRE_HEBDO_TD + "]",
						"[" + this.selectedFicheVoeux.getLibellePeriode() + "]" };
				MessageUtil
						.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
								OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM), MessageUtil
								.getStringValueFromBundleResourceWithParams(
										OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
										OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM,
										params, "fr"));

				return;
			}
			PeriodeParamDto param5 = periodeService.findParamByCodeByPeriodiciteByYear(
					OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_VOLUME_HORAIRE_HEBDO_TP, this.selectedIdYear,
					selectedPeriodiciteCode);
			if (param5 == null) {
				Object[] params = { "[" + OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_VOLUME_HORAIRE_HEBDO_TP + "]",
						"[" + this.selectedFicheVoeux.getLibellePeriode() + "]" };
				MessageUtil
						.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
								OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM), MessageUtil
								.getStringValueFromBundleResourceWithParams(
										OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
										OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM,
										params, "fr"));

				return;
			}
			PeriodeParamDto param6 = periodeService.findParamByCodeByPeriodiciteByYear(
					OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_TAUX_VOLUME_HORAIRE_COMP, this.selectedIdYear,
					selectedPeriodiciteCode);
			if (param6 == null) {
				Object[] params = { "[" + OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_TAUX_VOLUME_HORAIRE_COMP + "]",
						"[" + this.selectedFicheVoeux.getLibellePeriode() + "]" };
				MessageUtil
						.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
								OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM), MessageUtil
								.getStringValueFromBundleResourceWithParams(
										OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
										OfConstants.CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM,
										params, "fr"));

				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [FicheVoeuxBB.managePeridoesByCycle] Method
	 * 
	 * @author rlaib on : 24 oct. 2014 14:12:09
	 * @param idCycle
	 */
	@SuppressWarnings("unused")
	private void managePeridoesByCycle(Integer idCycle) {

		if (idCycle == null) {
			return;
		} else {
			List<NiveauDto> niveauList = niveauService.findByCycleId(idCycle);
			if (niveauList != null) {
				listPeriodesParPeriodicite = new ArrayList<SelectItem>();
				for (NiveauDto niveauDto : niveauList) {

					SelectItemGroup niveau = new SelectItemGroup(niveauDto.getLibelleLongLt());
					List<PeriodeDto> periodeList = periodeService.findByLevelId(niveauDto.getId());

					if (periodeList != null) {
						SelectItem[] periodeItems = new SelectItem[periodeList.size()];
						int i = 0;
						for (PeriodeDto periodeDto : periodeList) {
							SelectItem periode = new SelectItem(periodeDto.getId(), periodeDto.getLibelleLongLt());
							periodeItems[i] = periode;
							i++;
						}
						niveau.setSelectItems(periodeItems);
					}
					listPeriodesParPeriodicite.add(niveau);
				}
			}
			SelectItemGroup firstGroup = (SelectItemGroup) listPeriodesParPeriodicite.get(0);
			if (firstGroup != null && firstGroup.getSelectItems().length > 0) {
				selectedPeriodeOfId = Integer.parseInt(firstGroup.getSelectItems()[0].getValue().toString());
			}
			checkExistingFiches();
		}
	}

	/**
	 * [FicheVoeuxBB.checkExistingFiches] Method
	 * 
	 * @author rlaib on : 18 oct. 2014 11:47:56
	 */
	public void checkExistingFiches() {

		try {

			if (selectedIdYear != null && selectedPeriodeFicheId != null
					&& !selectedPeriodeFicheId.toString().equals("") && selectedPeriodeFicheId > 0) {

				if (toValidate)
					loadFichesVoeuxToValidate();
				else {
					Long _userId = (long) 0;
					if (enseignantDto != null && enseignantDto.getId() != null)
						_userId = enseignantDto.getId();

					if (toEdit || enseignantDto != null) {
						initListeFichesVoeux(idEtablissement, _userId, selectedIdYear, selectedPeriodeFicheId,
								toConsult);
					} else {
						initListeFichesVoeux(idEtablissement, _userId, selectedIdYear, selectedPeriodeFicheId,
								toConsult);
					}
				}
			} else {
				// toEdit = false;
				toSubmit = false;
				// toValidate = false;
				hasFicheVoeux = false;
				canCreateFicheVoeux = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [FicheVoeuxValidate.loadFichesVoeuxToValidate] Method
	 * 
	 * @author rlaib on : 20 oct. 2014 12:33:58
	 */
	public void loadFichesVoeuxToValidate() {

		try {

			if (idEtablissement != null && selectedIdYear != null) {
				SituationEntiteDto seDto = new SituationEntiteDto();
				seDto = situationService.findBySituationEntiteByCodeSituation(
						UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT, UtilConstants.SITUTAION_SOUMISE_VALIDATION_CODE);
				Integer idSituation = seDto.getId();
				if (seDto != null && idSituation != null) {

					List<EnseignantFicheVoeuxDto> _listSubmitted = enseignantFicheVoeuxService
							.findFichesVoeuxByEtabBySituation(idEtablissement, selectedIdYear, idSituation);
					fichesVoeux = _listSubmitted;
					seDto = situationService.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT,
							UtilConstants.SITUTAION_ENREGISTREE_AVANT_VALIDATION_CODE);
					idSituation = seDto.getId();
					List<EnseignantFicheVoeuxDto> _listSavedBeforeValidation = enseignantFicheVoeuxService
							.findFichesVoeuxByEtabBySituation(idEtablissement, selectedIdYear, idSituation);
					for (EnseignantFicheVoeuxDto enseignantFicheVoeuxDto : _listSavedBeforeValidation) {
						fichesVoeux.add(enseignantFicheVoeuxDto);
					}
					if (fichesVoeux != null && fichesVoeux.size() > 0) {
						selectedFicheVoeux = fichesVoeux.get(0);
						loadFicheVoeuxDetails();
					} else {

					}
				}
			}

			hasFicheVoeux = false;
			selectedFicheVoeux = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [FicheVoeuxBB.checkMapLignesVoeuxForSubmit] Method
	 * 
	 * @author rlaib on : 24 oct. 2014 14:12:15
	 */
	private void checkMapLignesVoeuxForSubmit() {

		try {

			if (lignesVoeux != null) {
				for (Integer key : lignesVoeux.keySet()) {

					List<EnseignantVoeuLigneDto> _list = lignesVoeux.get(key);
					if (_list != null && _list.size() > 0) {
						canSubmitFiche = true;
						return;
					}
				}
				canSubmitFiche = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getLabelItemFromListById(List<SelectItem> listItems, Integer idItem) {
		String result = "";
		for (Object _object : listItems) {

			if (_object instanceof SelectItemGroup) {

				SelectItemGroup _itemGroup = (SelectItemGroup) _object;
				SelectItem[] _listItems = _itemGroup.getSelectItems();
				for (int i = 0; i < _listItems.length; i++) {
					if (Integer.parseInt(_listItems[i].getValue().toString()) == idItem)
						return _listItems[i].getLabel();
				}

			} else {
				SelectItem _selectItem = (SelectItem) _object;
				if (Integer.parseInt(_selectItem.getValue().toString()) == idItem)
					return _selectItem.getLabel();
			}
		}
		return result;
	}

	/**
	 * [FicheVoeuxBB.newEtabId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 nov. 2014 14:30:07
	 * @return the newEtabId
	 */
	public Integer getNewEtabId() {
		return newEtabId;
	}

	/**
	 * [FicheVoeuxBB.newEtabId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 nov. 2014 14:30:07
	 * @param newEtabId
	 *            the newEtabId to set
	 */
	public void setNewEtabId(Integer newEtabId) {
		this.newEtabId = newEtabId;
	}

}
