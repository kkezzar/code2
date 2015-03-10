package dz.gov.mesrs.sii.fve.web.jsf.bean.scolarite.chargepedagogique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantServiceFait;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeParamDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.DossierEmployeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantChargePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantServiceFaitDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantFicheServicesService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantFicheVoeuxService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantServiceFaitService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

@ManagedBean(name = "syntheseCharge")
@ViewScoped
public class SyntheseChargeEnseignantBB {

	// ===================================================================
	// Constructors
	// ===================================================================
	public SyntheseChargeEnseignantBB() {

	}

	@PostConstruct
	public void init() {

		try {
			// init session infos
			initSessionInfos();
			// Init liste annees
			initYearsList();
			// init nomeclatures
			initNomenclatures();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ===================================================================
	// Beans Services
	// ===================================================================
	@ManagedProperty(value = "#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;

	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;

	@ManagedProperty(value = "#{enseignantFicheVoeuxService}")
	private EnseignantFicheVoeuxService enseignantFicheVoeuxService;

	@ManagedProperty(value = "#{enseignantFicheServicesService}")
	private EnseignantFicheServicesService enseignantFicheServicesService;

	@ManagedProperty(value = "#{enseignantServiceFaitService}")
	private EnseignantServiceFaitService enseignantServiceFaitService;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	@ManagedProperty(value = "#{utilBean}")
	private UtilBean utilBean;

	@ManagedProperty(value = "#{niveauService}")
	private NiveauService niveauService;

	@ManagedProperty(value = "#{periodeService}")
	private PeriodeService periodeService;

	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;

	@ManagedProperty(value = "#{ficheVoeuxBB}")
	private FicheVoeuxBB ficheVoeuxBB;

	@ManagedProperty(value = "#{enseignantServiceFait}")
	private EnseignantServiceFait enseignantServiceFait;

	@ManagedProperty(value = "#{offreFormationService}")
	private OffreFormationService offreFormationService;

	@ManagedProperty(value = "#{employeeConverter}")
	private EmployeeConverter employeeConverter;

	/**
	 * [ServicesFaitsBB.employeeConverter] Getter
	 * 
	 * @author rlaib on : 11 nov. 2014 13:44:06
	 * @return the employeeConverter
	 */
	public EmployeeConverter getEmployeeConverter() {

		employeeConverter = new EmployeeConverter(enseignantFicheVoeuxService);
		return employeeConverter;
	}

	/**
	 * [ServicesFaitsBB.employeeConverter] Setter
	 * 
	 * @author rlaib on : 11 nov. 2014 13:44:06
	 * @param employeeConverter
	 *            the employeeConverter to set
	 */
	public void setEmployeeConverter(EmployeeConverter employeeConverter) {
		this.employeeConverter = employeeConverter;
	}

	/**
	 * [ServicesFaitsBB.enseignantServiceFaitService] Getter
	 * 
	 * @author rlaib on : 10 nov. 2014 15:05:49
	 * @return the enseignantServiceFaitService
	 */
	public EnseignantServiceFaitService getEnseignantServiceFaitService() {
		return enseignantServiceFaitService;
	}

	/**
	 * [ServicesFaitsBB.enseignantServiceFaitService] Setter
	 * 
	 * @author rlaib on : 10 nov. 2014 15:05:49
	 * @param enseignantServiceFaitService
	 *            the enseignantServiceFaitService to set
	 */
	public void setEnseignantServiceFaitService(EnseignantServiceFaitService enseignantServiceFaitService) {
		this.enseignantServiceFaitService = enseignantServiceFaitService;
	}

	/**
	 * [ServicesFaitsBB.offreFormationService] Getter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:36:12
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [ServicesFaitsBB.offreFormationService] Setter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:36:12
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [ServicesFaitsBB.enseignantServiceFait] Getter
	 * 
	 * @author rlaib on : 29 oct. 2014 15:42:21
	 * @return the enseignantServiceFait
	 */
	public EnseignantServiceFait getEnseignantServiceFait() {
		return enseignantServiceFait;
	}

	/**
	 * [ServicesFaitsBB.enseignantServiceFait] Setter
	 * 
	 * @author rlaib on : 29 oct. 2014 15:42:22
	 * @param enseignantServiceFait
	 *            the enseignantServiceFait to set
	 */
	public void setEnseignantServiceFait(EnseignantServiceFait enseignantServiceFait) {
		this.enseignantServiceFait = enseignantServiceFait;
	}

	/**
	 * [FicheServicesBB.ficheVoeuxBB] Getter
	 * 
	 * @author rlaib on : 27 oct. 2014 09:49:08
	 * @return the ficheVoeuxBB
	 */
	public FicheVoeuxBB getFicheVoeuxBB() {
		return ficheVoeuxBB;
	}

	/**
	 * [FicheServicesBB.ficheVoeuxBB] Setter
	 * 
	 * @author rlaib on : 27 oct. 2014 09:49:08
	 * @param ficheVoeuxBB
	 *            the ficheVoeuxBB to set
	 */
	public void setFicheVoeuxBB(FicheVoeuxBB ficheVoeuxBB) {
		this.ficheVoeuxBB = ficheVoeuxBB;
	}

	/**
	 * [FicheServicesBB.ouvertureOffreFormationService] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [FicheServicesBB.ouvertureOffreFormationService] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [FicheServicesBB.nomenclatureService] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [FicheServicesBB.nomenclatureService] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [FicheServicesBB.enseignantFicheVoeuxService] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @return the enseignantFicheVoeuxService
	 */
	public EnseignantFicheVoeuxService getEnseignantFicheVoeuxService() {
		return enseignantFicheVoeuxService;
	}

	/**
	 * [FicheServicesBB.enseignantFicheVoeuxService] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @param enseignantFicheVoeuxService
	 *            the enseignantFicheVoeuxService to set
	 */
	public void setEnseignantFicheVoeuxService(EnseignantFicheVoeuxService enseignantFicheVoeuxService) {
		this.enseignantFicheVoeuxService = enseignantFicheVoeuxService;
	}

	/**
	 * [FicheServicesBB.enseignantFicheServicesService] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @return the enseignantFicheServicesService
	 */
	public EnseignantFicheServicesService getEnseignantFicheServicesService() {
		return enseignantFicheServicesService;
	}

	/**
	 * [FicheServicesBB.enseignantFicheServicesService] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @param enseignantFicheServicesService
	 *            the enseignantFicheServicesService to set
	 */
	public void setEnseignantFicheServicesService(EnseignantFicheServicesService enseignantFicheServicesService) {
		this.enseignantFicheServicesService = enseignantFicheServicesService;
	}

	/**
	 * [FicheServicesBB.loginBean] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @return the loginBean
	 */
	public LoginBean getLoginBean() {
		return loginBean;
	}

	/**
	 * [FicheServicesBB.loginBean] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @param loginBean
	 *            the loginBean to set
	 */
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	/**
	 * [FicheServicesBB.utilBean] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [FicheServicesBB.utilBean] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [FicheServicesBB.niveauService] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [FicheServicesBB.niveauService] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @param niveauService
	 *            the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [FicheServicesBB.periodeService] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [FicheServicesBB.periodeService] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @param periodeService
	 *            the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [FicheServicesBB.sessionBean] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [FicheServicesBB.sessionBean] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [FicheServicesBB.situationService] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [FicheServicesBB.situationService] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:36:28
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	// ===================================================================
	// Properties Model
	// ===================================================================

	private List<SelectItem> listYears;
	private Integer selectedIdYear;
	private List<SelectItem> listPeriodicites;
	private String selectedPeriodiciteCode;
	private List<SelectItem> listPeriodes;
	private Integer selectedPeriodeId;
	private DossierEmployeDto selectedDossierEmploye;
	private Long selectedDossierEmployeId;
	private Map<String, String> paramsPeriode;
	private Integer paramValNBS;
	private List<EnseignantServiceFaitDto> selectedServicesFaits;
	private List<Object[]> enseignantChargesSynthese;
	private List<Object[]> enseignantChargesSyntheseNormale;
	private List<Object[]> enseignantChargesSyntheseComplementaire;
	private EnseignantServiceFaitDto selectedServiceFait;
	private Integer selectedServiceFaitId;
	private Double heureEquiReferenceCM;
	private Double heureEquiReferenceTP;
	private Double totalVHNormalPrevu;
	private Double totalVHNormalRealise;
	private Double totalVHComplementPrevu;
	private Double totalVHComplementRealise;

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
	 * [SyntheseChargeEnseignantBB.selectedServicesFaits] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:59:51
	 * @return the selectedServicesFaits
	 */
	public List<EnseignantServiceFaitDto> getSelectedServicesFaits() {
		return selectedServicesFaits;
	}

	/**
	 * [SyntheseChargeEnseignantBB.selectedServicesFaits] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:59:51
	 * @param selectedServicesFaits
	 *            the selectedServicesFaits to set
	 */
	public void setSelectedServicesFaits(List<EnseignantServiceFaitDto> selectedServicesFaits) {
		this.selectedServicesFaits = selectedServicesFaits;
	}

	/**
	 * [SyntheseChargeEnseignantBB.enseignantChargesSynthese] Getter
	 * 
	 * @author Rafik on : 8 déc. 2014 20:24:30
	 * @return the enseignantChargesSynthese
	 */
	public List<Object[]> getEnseignantChargesSynthese() {
		return enseignantChargesSynthese;
	}

	/**
	 * [SyntheseChargeEnseignantBB.enseignantChargesSynthese] Setter
	 * 
	 * @author Rafik on : 8 déc. 2014 20:24:30
	 * @param enseignantChargesSynthese
	 *            the enseignantChargesSynthese to set
	 */
	public void setEnseignantChargesSynthese(List<Object[]> enseignantChargesSynthese) {
		this.enseignantChargesSynthese = enseignantChargesSynthese;
	}

	/**
	 * [SyntheseChargeEnseignantBB.enseignantChargesSyntheseNormale] Getter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:11:57
	 * @return the enseignantChargesSyntheseNormale
	 */
	public List<Object[]> getEnseignantChargesSyntheseNormale() {
		return enseignantChargesSyntheseNormale;
	}

	/**
	 * [SyntheseChargeEnseignantBB.enseignantChargesSyntheseNormale] Setter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:11:57
	 * @param enseignantChargesSyntheseNormale
	 *            the enseignantChargesSyntheseNormale to set
	 */
	public void setEnseignantChargesSyntheseNormale(List<Object[]> enseignantChargesSyntheseNormale) {
		this.enseignantChargesSyntheseNormale = enseignantChargesSyntheseNormale;
	}

	/**
	 * [SyntheseChargeEnseignantBB.enseignantChargesSyntheseComplementaire]
	 * Getter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:11:57
	 * @return the enseignantChargesSyntheseComplementaire
	 */
	public List<Object[]> getEnseignantChargesSyntheseComplementaire() {
		return enseignantChargesSyntheseComplementaire;
	}

	/**
	 * [SyntheseChargeEnseignantBB.enseignantChargesSyntheseComplementaire]
	 * Setter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:11:57
	 * @param enseignantChargesSyntheseComplementaire
	 *            the enseignantChargesSyntheseComplementaire to set
	 */
	public void setEnseignantChargesSyntheseComplementaire(List<Object[]> enseignantChargesSyntheseComplementaire) {
		this.enseignantChargesSyntheseComplementaire = enseignantChargesSyntheseComplementaire;
	}

	/**
	 * [SyntheseChargeEnseignantBB.selectedServiceFait] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:59:51
	 * @return the selectedServiceFait
	 */
	public EnseignantServiceFaitDto getSelectedServiceFait() {
		return selectedServiceFait;
	}

	/**
	 * [SyntheseChargeEnseignantBB.selectedServiceFait] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:59:51
	 * @param selectedServiceFait
	 *            the selectedServiceFait to set
	 */
	public void setSelectedServiceFait(EnseignantServiceFaitDto selectedServiceFait) {
		this.selectedServiceFait = selectedServiceFait;
	}

	/**
	 * [SyntheseChargeEnseignantBB.selectedServiceFaitId] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:59:51
	 * @return the selectedServiceFaitId
	 */
	public Integer getSelectedServiceFaitId() {
		return selectedServiceFaitId;
	}

	/**
	 * [SyntheseChargeEnseignantBB.selectedServiceFaitId] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:59:51
	 * @param selectedServiceFaitId
	 *            the selectedServiceFaitId to set
	 */
	public void setSelectedServiceFaitId(Integer selectedServiceFaitId) {
		this.selectedServiceFaitId = selectedServiceFaitId;
	}

	/**
	 * [SyntheseChargeEnseignantBB.paramValNBS] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:58:19
	 * @return the paramValNBS
	 */
	public Integer getParamValNBS() {
		return paramValNBS;
	}

	/**
	 * [SyntheseChargeEnseignantBB.paramValNBS] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:58:19
	 * @param paramValNBS
	 *            the paramValNBS to set
	 */
	public void setParamValNBS(Integer paramValNBS) {
		this.paramValNBS = paramValNBS;
	}

	/**
	 * [SyntheseChargeEnseignantBB.paramsPeriode] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:57:40
	 * @return the paramsPeriode
	 */
	public Map<String, String> getParamsPeriode() {
		return paramsPeriode;
	}

	/**
	 * [SyntheseChargeEnseignantBB.paramsPeriode] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:57:40
	 * @param paramsPeriode
	 *            the paramsPeriode to set
	 */
	public void setParamsPeriode(Map<String, String> paramsPeriode) {
		this.paramsPeriode = paramsPeriode;
	}

	/**
	 * [SyntheseChargeEnseignantBB.selectedDossierEmployeId] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:37:56
	 * @return the selectedDossierEmployeId
	 */
	public Long getSelectedDossierEmployeId() {
		return selectedDossierEmployeId;
	}

	/**
	 * [SyntheseChargeEnseignantBB.selectedDossierEmployeId] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:37:56
	 * @param selectedDossierEmployeId
	 *            the selectedDossierEmployeId to set
	 */
	public void setSelectedDossierEmployeId(Long selectedDossierEmployeId) {
		this.selectedDossierEmployeId = selectedDossierEmployeId;
	}

	/**
	 * [SyntheseChargeEnseignantBB.selectedDossierEmploye] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:36:06
	 * @return the selectedDossierEmploye
	 */
	public DossierEmployeDto getSelectedDossierEmploye() {
		return selectedDossierEmploye;
	}

	/**
	 * [SyntheseChargeEnseignantBB.selectedDossierEmploye] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:36:06
	 * @param selectedDossierEmploye
	 *            the selectedDossierEmploye to set
	 */
	public void setSelectedDossierEmploye(DossierEmployeDto selectedDossierEmploye) {
		this.selectedDossierEmploye = selectedDossierEmploye;
	}

	/**
	 * [SyntheseChargeEnseignantBB.listYears] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the listYears
	 */
	public List<SelectItem> getListYears() {
		return listYears;
	}

	/**
	 * [SyntheseChargeEnseignantBB.listYears] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param listYears
	 *            the listYears to set
	 */
	public void setListYears(List<SelectItem> listYears) {
		this.listYears = listYears;
	}

	/**
	 * [SyntheseChargeEnseignantBB.selectedIdYear] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the selectedIdYear
	 */
	public Integer getSelectedIdYear() {
		return selectedIdYear;
	}

	/**
	 * [SyntheseChargeEnseignantBB.selectedIdYear] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param selectedIdYear
	 *            the selectedIdYear to set
	 */
	public void setSelectedIdYear(Integer selectedIdYear) {
		this.selectedIdYear = selectedIdYear;
	}

	/**
	 * [SyntheseChargeEnseignantBB.listPeriodicites] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the listPeriodicites
	 */
	public List<SelectItem> getListPeriodicites() {
		return listPeriodicites;
	}

	/**
	 * [SyntheseChargeEnseignantBB.listPeriodicites] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param listPeriodicites
	 *            the listPeriodicites to set
	 */
	public void setListPeriodicites(List<SelectItem> listPeriodicites) {
		this.listPeriodicites = listPeriodicites;
	}

	/**
	 * [SyntheseChargeEnseignantBB.selectedPeriodiciteCode] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the selectedPeriodiciteCode
	 */
	public String getSelectedPeriodiciteCode() {
		return selectedPeriodiciteCode;
	}

	/**
	 * [SyntheseChargeEnseignantBB.selectedPeriodiciteCode] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param selectedPeriodiciteCode
	 *            the selectedPeriodiciteCode to set
	 */
	public void setSelectedPeriodiciteCode(String selectedPeriodiciteCode) {
		this.selectedPeriodiciteCode = selectedPeriodiciteCode;
	}

	/**
	 * [SyntheseChargeEnseignantBB.listPeriodes] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the listPeriodes
	 */
	public List<SelectItem> getListPeriodes() {
		return listPeriodes;
	}

	/**
	 * [SyntheseChargeEnseignantBB.listPeriodes] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param listPeriodes
	 *            the listPeriodes to set
	 */
	public void setListPeriodes(List<SelectItem> listPeriodes) {
		this.listPeriodes = listPeriodes;
	}

	/**
	 * [SyntheseChargeEnseignantBB.selectedPeriodeId] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the selectedPeriodeId
	 */
	public Integer getSelectedPeriodeId() {
		return selectedPeriodeId;
	}

	/**
	 * [SyntheseChargeEnseignantBB.selectedPeriodeId] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param selectedPeriodeId
	 *            the selectedPeriodeId to set
	 */
	public void setSelectedPeriodeId(Integer selectedPeriodeId) {
		this.selectedPeriodeId = selectedPeriodeId;
	}

	/**
	 * [SyntheseChargeEnseignantBB.idEtablissement] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [SyntheseChargeEnseignantBB.idEtablissement] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param idEtablissement
	 *            the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * [SyntheseChargeEnseignantBB.codeEtablissement] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the codeEtablissement
	 */
	public String getCodeEtablissement() {
		return codeEtablissement;
	}

	/**
	 * [SyntheseChargeEnseignantBB.codeEtablissement] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param codeEtablissement
	 *            the codeEtablissement to set
	 */
	public void setCodeEtablissement(String codeEtablissement) {
		this.codeEtablissement = codeEtablissement;
	}

	/**
	 * [SyntheseChargeEnseignantBB.newEtabCode] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the newEtabCode
	 */
	public String getNewEtabCode() {
		return newEtabCode;
	}

	/**
	 * [SyntheseChargeEnseignantBB.newEtabCode] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param newEtabCode
	 *            the newEtabCode to set
	 */
	public void setNewEtabCode(String newEtabCode) {
		this.newEtabCode = newEtabCode;
	}

	/**
	 * [SyntheseChargeEnseignantBB.newEtabId] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the newEtabId
	 */
	public Integer getNewEtabId() {
		return newEtabId;
	}

	/**
	 * [SyntheseChargeEnseignantBB.newEtabId] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param newEtabId
	 *            the newEtabId to set
	 */
	public void setNewEtabId(Integer newEtabId) {
		this.newEtabId = newEtabId;
	}

	/**
	 * [SyntheseChargeEnseignantBB.libelleEtab] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the libelleEtab
	 */
	public String getLibelleEtab() {
		return libelleEtab;
	}

	/**
	 * [SyntheseChargeEnseignantBB.libelleEtab] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param libelleEtab
	 *            the libelleEtab to set
	 */
	public void setLibelleEtab(String libelleEtab) {
		this.libelleEtab = libelleEtab;
	}

	/**
	 * [SyntheseChargeEnseignantBB.idYear] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the idYear
	 */
	public Integer getIdYear() {
		return idYear;
	}

	/**
	 * [SyntheseChargeEnseignantBB.idYear] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param idYear
	 *            the idYear to set
	 */
	public void setIdYear(Integer idYear) {
		this.idYear = idYear;
	}

	/**
	 * [SyntheseChargeEnseignantBB.userId] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * [SyntheseChargeEnseignantBB.userId] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * [SyntheseChargeEnseignantBB.userFisrtName] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the userFisrtName
	 */
	public String getUserFisrtName() {
		return userFisrtName;
	}

	/**
	 * [SyntheseChargeEnseignantBB.userFisrtName] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param userFisrtName
	 *            the userFisrtName to set
	 */
	public void setUserFisrtName(String userFisrtName) {
		this.userFisrtName = userFisrtName;
	}

	/**
	 * [SyntheseChargeEnseignantBB.userLastName] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the userLastName
	 */
	public String getUserLastName() {
		return userLastName;
	}

	/**
	 * [SyntheseChargeEnseignantBB.userLastName] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param userLastName
	 *            the userLastName to set
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	/**
	 * [SyntheseChargeEnseignantBB.heureEquiReferenceCM] Getter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:21:54
	 * @return the heureEquiReferenceCM
	 */
	public Double getHeureEquiReferenceCM() {
		return heureEquiReferenceCM;
	}

	/**
	 * [SyntheseChargeEnseignantBB.heureEquiReferenceCM] Setter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:21:54
	 * @param heureEquiReferenceCM
	 *            the heureEquiReferenceCM to set
	 */
	public void setHeureEquiReferenceCM(Double heureEquiReferenceCM) {
		this.heureEquiReferenceCM = heureEquiReferenceCM;
	}

	/**
	 * [SyntheseChargeEnseignantBB.totalVHNormalPrevu] Getter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:46:20
	 * @return the totalVHNormalPrevu
	 */
	public Double getTotalVHNormalPrevu() {
		return totalVHNormalPrevu;
	}

	/**
	 * [SyntheseChargeEnseignantBB.totalVHNormalPrevu] Setter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:46:20
	 * @param totalVHNormalPrevu
	 *            the totalVHNormalPrevu to set
	 */
	public void setTotalVHNormalPrevu(Double totalVHNormalPrevu) {
		this.totalVHNormalPrevu = totalVHNormalPrevu;
	}

	/**
	 * [SyntheseChargeEnseignantBB.totalVHNormalRealise] Getter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:46:20
	 * @return the totalVHNormalRealise
	 */
	public Double getTotalVHNormalRealise() {
		return totalVHNormalRealise;
	}

	/**
	 * [SyntheseChargeEnseignantBB.totalVHNormalRealise] Setter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:46:20
	 * @param totalVHNormalRealise
	 *            the totalVHNormalRealise to set
	 */
	public void setTotalVHNormalRealise(Double totalVHNormalRealise) {
		this.totalVHNormalRealise = totalVHNormalRealise;
	}

	/**
	 * [SyntheseChargeEnseignantBB.totalVHComplementPrevu] Getter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:46:20
	 * @return the totalVHComplementPrevu
	 */
	public Double getTotalVHComplementPrevu() {
		return totalVHComplementPrevu;
	}

	/**
	 * [SyntheseChargeEnseignantBB.totalVHComplementPrevu] Setter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:46:20
	 * @param totalVHComplementPrevu
	 *            the totalVHComplementPrevu to set
	 */
	public void setTotalVHComplementPrevu(Double totalVHComplementPrevu) {
		this.totalVHComplementPrevu = totalVHComplementPrevu;
	}

	/**
	 * [SyntheseChargeEnseignantBB.totalVHComplementRealise] Getter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:46:20
	 * @return the totalVHComplementRealise
	 */
	public Double getTotalVHComplementRealise() {
		return totalVHComplementRealise;
	}

	/**
	 * [SyntheseChargeEnseignantBB.totalVHComplementRealise] Setter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:46:20
	 * @param totalVHComplementRealise
	 *            the totalVHComplementRealise to set
	 */
	public void setTotalVHComplementRealise(Double totalVHComplementRealise) {
		this.totalVHComplementRealise = totalVHComplementRealise;
	}

	/**
	 * [SyntheseChargeEnseignantBB.heureEquiReferenceTP] Getter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:21:54
	 * @return the heureEquiReferenceTP
	 */
	public Double getHeureEquiReferenceTP() {
		return heureEquiReferenceTP;
	}

	/**
	 * [SyntheseChargeEnseignantBB.heureEquiReferenceTP] Setter
	 * 
	 * @author Rafik on : 8 déc. 2014 22:21:54
	 * @param heureEquiReferenceTP
	 *            the heureEquiReferenceTP to set
	 */
	public void setHeureEquiReferenceTP(Double heureEquiReferenceTP) {
		this.heureEquiReferenceTP = heureEquiReferenceTP;
	}

	/**
	 * [SyntheseChargeEnseignantBB.libelleYear] Getter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @return the libelleYear
	 */
	public String getLibelleYear() {
		return libelleYear;
	}

	/**
	 * [SyntheseChargeEnseignantBB.libelleYear] Setter
	 * 
	 * @author Rafik on : 6 déc. 2014 20:22:14
	 * @param libelleYear
	 *            the libelleYear to set
	 */
	public void setLibelleYear(String libelleYear) {
		this.libelleYear = libelleYear;
	}

	// ===================================================================
	// Listeners
	// ===================================================================
	public void handleYearChange(AjaxBehaviorEvent event) {

		initSyntheseChargesEnseignant();
	}

	public void handlePeriodiciteChange(AjaxBehaviorEvent event) {

		if (selectedPeriodiciteCode != null) {
			listPeriodes = initPeriodesParPeriodicite(selectedPeriodiciteCode);
			if (listPeriodes != null && listPeriodes.size() > 0) {
				selectedPeriodeId = Integer.parseInt(listPeriodes.get(0).getValue().toString());
			}
			initSyntheseChargesEnseignant();
		}
	}

	public void handlePeriodeChange(AjaxBehaviorEvent event) {

		initSyntheseChargesEnseignant();
	}

	public List<DossierEmployeDto> completeSearchEnseignant(String query) {
		List<DossierEmployeDto> filtredItems = enseignantFicheVoeuxService.searchEnseignants(this.idEtablissement,
				query);

		if (filtredItems != null && filtredItems.size() > 0) {
			selectedDossierEmploye = filtredItems.get(0);
			selectedDossierEmployeId = selectedDossierEmploye.getId();
		}
		return filtredItems;
	}

	public void onItemSelect(SelectEvent event) {

		// Chargement de la synthese charges pedagogiques de l enseignant
		// selectionne dans la recherche auto-complete.
		selectedDossierEmployeId = selectedDossierEmploye.getId();
		initSyntheseChargesEnseignant();
	}

	// ===================================================================
	// ActionListener
	// ===================================================================

	// ===================================================================
	// Listeners
	// ===================================================================

	// ===================================================================
	// Actions and Methods
	// ===================================================================

	/**
	 * [SyntheseChargeEnseignantBB.initSyntheseChargesEnseignant] Method
	 * 
	 * @author Rafik on : 6 déc. 2014 20:40:08
	 */
	private void initSyntheseChargesEnseignant() {

		try {
			enseignantChargesSyntheseNormale = new ArrayList<Object[]>();
			enseignantChargesSyntheseComplementaire = new ArrayList<Object[]>();
			totalVHComplementPrevu = (double) 0;
			totalVHComplementRealise = (double) 0;
			totalVHNormalPrevu = (double) 0;
			totalVHNormalRealise = (double) 0;
			if (selectedDossierEmployeId == null || selectedIdYear == null || idEtablissement == null
					|| selectedPeriodeId == null) {
				return;
			}

			// recupere le parametre NOMBRE DE SEMAINES de la periode
			if (paramsPeriode == null)
				initParamsPeriode();
			try {
				paramValNBS = Integer.parseInt(paramsPeriode
						.get(OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_NOMBRE_SEMAINES));
			} catch (Exception e) {
				paramValNBS = 0;
			}

			// Chargement de la liste de services faits enseignant pour la
			// periode, annee, offre de formation en question
			enseignantChargesSynthese = enseignantServiceFaitService.findSyntheseChargesEnseignant(idEtablissement,
					selectedDossierEmployeId, selectedIdYear, selectedPeriodeId);
			for (Object[] _item : enseignantChargesSynthese) {

				EnseignantChargePedagogiqueDto enseignantChargePedagogiqueDto = (EnseignantChargePedagogiqueDto) _item[0];
				if (enseignantChargePedagogiqueDto != null
						&& enseignantChargePedagogiqueDto.getIdEtablissement() != null) {
					if (enseignantChargePedagogiqueDto.getIdEtablissement().equals(idEtablissement)) {
						enseignantChargesSyntheseNormale.add(_item);
						if (enseignantChargePedagogiqueDto.getVolumeHoraireAp() != null
								&& enseignantChargePedagogiqueDto.getApCodeType() != null) {
							totalVHNormalPrevu = totalVHNormalPrevu
									+ enseignantChargePedagogiqueDto.getVolumeHoraireAp() * paramValNBS
									* getHeureEquivalentTdByTypeAp(enseignantChargePedagogiqueDto.getApCodeType());
							if (_item[1] != null) {
								Long _nbSeance = (Long) _item[1];
								totalVHNormalRealise = totalVHNormalRealise
										+ (enseignantChargePedagogiqueDto.getVolumeHoraireAp() * _nbSeance * getHeureEquivalentTdByTypeAp(enseignantChargePedagogiqueDto
												.getApCodeType()));
							}
						}
					} else {
						enseignantChargesSyntheseComplementaire.add(_item);
						if (enseignantChargePedagogiqueDto.getVolumeHoraireAp() != null
								&& enseignantChargePedagogiqueDto.getApCodeType() != null) {
							totalVHComplementPrevu = totalVHComplementPrevu
									+ enseignantChargePedagogiqueDto.getVolumeHoraireAp() * paramValNBS
									* getHeureEquivalentTdByTypeAp(enseignantChargePedagogiqueDto.getApCodeType());
							if (_item[1] != null) {
								Long _nbSeance = (Long) _item[1];
								totalVHComplementRealise = totalVHComplementRealise
										+ (enseignantChargePedagogiqueDto.getVolumeHoraireAp() * _nbSeance * getHeureEquivalentTdByTypeAp(enseignantChargePedagogiqueDto
												.getApCodeType()));
							}
						}
					}
				}
			}
			// calcul total du volume horaire (VH) des services faits pour a
			// charge selectionn�e

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Functions Helper
	// ===================================================================
	/**
	 * [ServicesFaitsBB.initSessionInfos] Method
	 * 
	 * @author rlaib on : 4 nov. 2014 08:58:59
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
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

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

	void initNomenclatures() {

		try {
			listPeriodicites = initNomenclatureList(OfConstants.NC_NAME_CYCLE_PERIODE_PERIODICITE);
			if (listPeriodicites != null && listPeriodicites.size() > 0) {
				String codePeriodicite = listPeriodicites.get(0).getValue().toString();
				listPeriodes = initPeriodesParPeriodicite(codePeriodicite);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	private List<SelectItem> initPeriodesParPeriodicite(String codePeriodicite) {

		List<SelectItem> result = new ArrayList<SelectItem>();
		try {
			List<NomenclatureDto> _list = nomenclatureService.findNcCompositeList(
					OfConstants.NC_NAME_CYCLE_PERIODE_PERIODICITE, codePeriodicite,
					OfConstants.NC_NAME_PERIODE_PAR_PERIODICITE);
			for (NomenclatureDto _nomenclatureDto : _list) {
				result.add(new SelectItem(_nomenclatureDto.getId(), _nomenclatureDto.getLibelleLongFr()));
			}
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	private void initParamsPeriode() {

		try {
			List<PeriodeParamDto> _params = periodeService.findPeriodParamByPeriodiciteByYear(this.selectedIdYear,
					selectedPeriodiciteCode);
			paramsPeriode = new HashMap<String, String>();
			paramsPeriode.clear();
			for (PeriodeParamDto periodeParamDto : _params) {
				String val = periodeParamDto.getValeur().toString();
				paramsPeriode.put(periodeParamDto.getCode(), val);
			}
			heureEquiReferenceCM = Double.parseDouble(paramsPeriode
					.get(OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_COEFF_CM_EQUI_CM));
			heureEquiReferenceTP = Double.parseDouble(paramsPeriode
					.get(OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_COEFF_CM_EQUI_TP));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getConvertVH(Double vh) {

		try {
			// Conversion volumeHoraireMinutes -> VolumeHoraireAffichage
			if (vh == null || vh == 0)
				return "-";
			int _heure = (int) (vh / 60);
			int _minutes = (int) (vh % 60);
			if (_minutes == 0)
				return String.valueOf(_heure) + "H:00";
			else
				return String.valueOf(_heure) + "H:" + String.valueOf(_minutes);
		} catch (Exception e) {

			return "-";
		}
	}

	public Double getHeureEquivalentTdByTypeAp(String typeAp) {

		if (typeAp == null)
			return (double) 0;
		Double result = (double) 1;
		switch (typeAp) {
		case OfConstants.CHARGE_PEDAGOGIQUE_AP_TYPE_CM_CODE:
			result = heureEquiReferenceCM;

			break;

		case OfConstants.CHARGE_PEDAGOGIQUE_AP_TYPE_TP_CODE:
			result = heureEquiReferenceTP;

			break;
		}
		return result;
	}

}
