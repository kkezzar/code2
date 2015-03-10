package dz.gov.mesrs.sii.fve.web.jsf.bean.scolarite.chargepedagogique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantServiceFait;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeParamDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.DossierEmployeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantChargePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantFicheServicesDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantServiceFaitDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.TypeSeanceDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantFicheServicesService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantFicheVoeuxService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantServiceFaitService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.MessageUtil;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

@ManagedBean(name = "servicesFaitsBB")
@ViewScoped
public class ServicesFaitsBB {

	// ===================================================================
	// Constructors
	// ===================================================================
	public ServicesFaitsBB() {

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
	private List<EnseignantChargePedagogiqueDto> selectedFicheServicesChargesPedagogiques;
	private EnseignantChargePedagogiqueDto selectedChargePedagogique;
	private Integer selectedChargePedagogiqueId;
	private List<SituationEntiteOccurrenceDto> ficheHistory;
	private List<EnseignantServiceFaitDto> selectedServicesFaits;
	private EnseignantServiceFaitDto selectedServiceFait;
	private Integer selectedServiceFaitId;
	private List<DossierEmployeDto> dossierEmployeDtos;
	private DossierEmployeDto selectedDossierEmploye;
	private Long selectedDossierEmployeId;
	private List<SelectItem> listYears;
	private Integer selectedIdYear;
	private List<SelectItem> listPeriodes;
	private List<SelectItem> listOffresFormation;
	private Integer selectedIdOf;
	private Integer selectedIdCycleOf;
	private EnseignantFicheServicesDto selectedFicheServices;
	private DossierEmployeDto enseignantDto;
	private boolean canShowDetails = false;
	private List<SelectItem> listPeriodicites;
	private String selectedPeriodiciteCode;
	private Integer selectedPeriode;
	private List<SelectItem> listPeriodesParPeriodicite;
	private boolean addOrEditDialog;
	private boolean canAddServicefait;
	private List<SelectItem> listSituationsServiceFait;
	private List<SelectItem> listTypeSeanceServiceFait;
	private Integer selectedTypeSeanceId;
	private Integer selectedStatutSeanceId;
	private String txtSearchEnseignant;
	private Double totalVHServicesFaitsCharge;
	private boolean canShowServicesFaitsCharge = false;
	private Integer paramValNBS;
	private Map<String, String> paramsPeriode;

	/**
	 * [ServicesFaitsBB.paramsPeriode] Getter
	 * 
	 * @author rlaib on : 12 nov. 2014 16:14:05
	 * @return the paramsPeriode
	 */
	public Map<String, String> getParamsPeriode() {
		return paramsPeriode;
	}

	/**
	 * [ServicesFaitsBB.paramsPeriode] Setter
	 * 
	 * @author rlaib on : 12 nov. 2014 16:14:05
	 * @param paramsPeriode
	 *            the paramsPeriode to set
	 */
	public void setParamsPeriode(Map<String, String> paramsPeriode) {
		this.paramsPeriode = paramsPeriode;
	}

	/**
	 * [ServicesFaitsBB.paramValNBS] Getter
	 * 
	 * @author rlaib on : 12 nov. 2014 16:08:17
	 * @return the paramValNBS
	 */
	public Integer getParamValNBS() {
		return paramValNBS;
	}

	/**
	 * [ServicesFaitsBB.paramValNBS] Setter
	 * 
	 * @author rlaib on : 12 nov. 2014 16:08:17
	 * @param paramValNBS
	 *            the paramValNBS to set
	 */
	public void setParamValNBS(Integer paramValNBS) {
		this.paramValNBS = paramValNBS;
	}

	/**
	 * [ServicesFaitsBB.canShowServicesFaitsCharge] Getter
	 * 
	 * @author rlaib on : 12 nov. 2014 09:50:04
	 * @return the canShowServicesFaitsCharge
	 */
	public boolean isCanShowServicesFaitsCharge() {
		return canShowServicesFaitsCharge;
	}

	/**
	 * [ServicesFaitsBB.canShowServicesFaitsCharge] Setter
	 * 
	 * @author rlaib on : 12 nov. 2014 09:50:04
	 * @param canShowServicesFaitsCharge
	 *            the canShowServicesFaitsCharge to set
	 */
	public void setCanShowServicesFaitsCharge(boolean canShowServicesFaitsCharge) {
		this.canShowServicesFaitsCharge = canShowServicesFaitsCharge;
	}

	/**
	 * [ServicesFaitsBB.totalVHServicesFaitsCharge] Getter
	 * 
	 * @author rlaib on : 12 nov. 2014 09:08:08
	 * @return the totalVHServicesFaitsCharge
	 */
	public Double getTotalVHServicesFaitsCharge() {
		return totalVHServicesFaitsCharge;
	}

	/**
	 * [ServicesFaitsBB.totalVHServicesFaitsCharge] Setter
	 * 
	 * @author rlaib on : 12 nov. 2014 09:08:08
	 * @param totalVHServicesFaitsCharge
	 *            the totalVHServicesFaitsCharge to set
	 */
	public void setTotalVHServicesFaitsCharge(Double totalVHServicesFaitsCharge) {
		this.totalVHServicesFaitsCharge = totalVHServicesFaitsCharge;
	}

	/**
	 * [ServicesFaitsBB.selectedDossierEmploye] Getter
	 * 
	 * @author rlaib on : 11 nov. 2014 12:02:36
	 * @return the selectedDossierEmploye
	 */
	public DossierEmployeDto getSelectedDossierEmploye() {
		return selectedDossierEmploye;
	}

	/**
	 * [ServicesFaitsBB.selectedDossierEmploye] Setter
	 * 
	 * @author rlaib on : 11 nov. 2014 12:02:36
	 * @param selectedDossierEmploye
	 *            the selectedDossierEmploye to set
	 */
	public void setSelectedDossierEmploye(DossierEmployeDto selectedDossierEmploye) {
		this.selectedDossierEmploye = selectedDossierEmploye;
	}

	/**
	 * [ServicesFaitsBB.txtSearchEnseignant] Getter
	 * 
	 * @author rlaib on : 11 nov. 2014 11:23:13
	 * @return the txtSearchEnseignant
	 */
	public String getTxtSearchEnseignant() {
		return txtSearchEnseignant;
	}

	/**
	 * [ServicesFaitsBB.txtSearchEnseignant] Setter
	 * 
	 * @author rlaib on : 11 nov. 2014 11:23:13
	 * @param txtSearchEnseignant
	 *            the txtSearchEnseignant to set
	 */
	public void setTxtSearchEnseignant(String txtSearchEnseignant) {
		this.txtSearchEnseignant = txtSearchEnseignant;
	}

	/**
	 * [ServicesFaitsBB.selectedServiceFaitId] Getter
	 * 
	 * @author rlaib on : 11 nov. 2014 10:46:15
	 * @return the selectedServiceFaitId
	 */
	public Integer getSelectedServiceFaitId() {
		return selectedServiceFaitId;
	}

	/**
	 * [ServicesFaitsBB.selectedServiceFaitId] Setter
	 * 
	 * @author rlaib on : 11 nov. 2014 10:46:15
	 * @param selectedServiceFaitId
	 *            the selectedServiceFaitId to set
	 */
	public void setSelectedServiceFaitId(Integer selectedServiceFaitId) {
		this.selectedServiceFaitId = selectedServiceFaitId;
	}

	/**
	 * [ServicesFaitsBB.selectedTypeSeanceId] Getter
	 * 
	 * @author rlaib on : 10 nov. 2014 15:57:38
	 * @return the selectedTypeSeanceId
	 */
	public Integer getSelectedTypeSeanceId() {
		return selectedTypeSeanceId;
	}

	/**
	 * [ServicesFaitsBB.selectedTypeSeanceId] Setter
	 * 
	 * @author rlaib on : 10 nov. 2014 15:57:38
	 * @param selectedTypeSeanceId
	 *            the selectedTypeSeanceId to set
	 */
	public void setSelectedTypeSeanceId(Integer selectedTypeSeanceId) {
		this.selectedTypeSeanceId = selectedTypeSeanceId;
	}

	/**
	 * [ServicesFaitsBB.selectedStatutSeanceId] Getter
	 * 
	 * @author rlaib on : 10 nov. 2014 15:57:38
	 * @return the selectedStatutSeanceId
	 */
	public Integer getSelectedStatutSeanceId() {
		return selectedStatutSeanceId;
	}

	/**
	 * [ServicesFaitsBB.selectedStatutSeanceId] Setter
	 * 
	 * @author rlaib on : 10 nov. 2014 15:57:38
	 * @param selectedStatutSeanceId
	 *            the selectedStatutSeanceId to set
	 */
	public void setSelectedStatutSeanceId(Integer selectedStatutSeanceId) {
		this.selectedStatutSeanceId = selectedStatutSeanceId;
	}

	/**
	 * [ServicesFaitsBB.listSituationsServiceFait] Getter
	 * 
	 * @author rlaib on : 10 nov. 2014 14:46:24
	 * @return the listSituationsServiceFait
	 */
	public List<SelectItem> getListSituationsServiceFait() {
		return listSituationsServiceFait;
	}

	/**
	 * [ServicesFaitsBB.listSituationsServiceFait] Setter
	 * 
	 * @author rlaib on : 10 nov. 2014 14:46:24
	 * @param listSituationsServiceFait
	 *            the listSituationsServiceFait to set
	 */
	public void setListSituationsServiceFait(List<SelectItem> listSituationsServiceFait) {
		this.listSituationsServiceFait = listSituationsServiceFait;
	}

	/**
	 * [ServicesFaitsBB.listTypeSeanceServiceFait] Getter
	 * 
	 * @author rlaib on : 10 nov. 2014 14:46:24
	 * @return the listTypeSeanceServiceFait
	 */
	public List<SelectItem> getListTypeSeanceServiceFait() {
		return listTypeSeanceServiceFait;
	}

	/**
	 * [ServicesFaitsBB.listTypeSeanceServiceFait] Setter
	 * 
	 * @author rlaib on : 10 nov. 2014 14:46:24
	 * @param listTypeSeanceServiceFait
	 *            the listTypeSeanceServiceFait to set
	 */
	public void setListTypeSeanceServiceFait(List<SelectItem> listTypeSeanceServiceFait) {
		this.listTypeSeanceServiceFait = listTypeSeanceServiceFait;
	}

	/**
	 * [ServicesFaitsBB.canAddServicefait] Getter
	 * 
	 * @author rlaib on : 10 nov. 2014 14:29:10
	 * @return the canAddServicefait
	 */
	public boolean isCanAddServicefait() {

		canAddServicefait = (selectedChargePedagogique != null);
		return canAddServicefait;
	}

	/**
	 * [ServicesFaitsBB.canAddServicefait] Setter
	 * 
	 * @author rlaib on : 10 nov. 2014 14:29:10
	 * @param canAddServicefait
	 *            the canAddServicefait to set
	 */
	public void setCanAddServicefait(boolean canAddServicefait) {
		this.canAddServicefait = canAddServicefait;
	}

	/**
	 * [ServicesFaitsBB.addOrEditDialog] Getter
	 * 
	 * @author rlaib on : 10 nov. 2014 13:40:38
	 * @return the addOrEditDialog
	 */
	public boolean isAddOrEditDialog() {
		return addOrEditDialog;
	}

	/**
	 * [ServicesFaitsBB.addOrEditDialog] Setter
	 * 
	 * @author rlaib on : 10 nov. 2014 13:40:38
	 * @param addOrEditDialog
	 *            the addOrEditDialog to set
	 */
	public void setAddOrEditDialog(boolean addOrEditDialog) {
		this.addOrEditDialog = addOrEditDialog;
	}

	/**
	 * [ServicesFaitsBB.listPeriodicites] Getter
	 * 
	 * @author rlaib on : 4 nov. 2014 08:50:12
	 * @return the listPeriodicites
	 */
	public List<SelectItem> getListPeriodicites() {
		return listPeriodicites;
	}

	/**
	 * [ServicesFaitsBB.listPeriodicites] Setter
	 * 
	 * @author rlaib on : 4 nov. 2014 08:50:12
	 * @param listPeriodicites
	 *            the listPeriodicites to set
	 */
	public void setListPeriodicites(List<SelectItem> listPeriodicites) {
		this.listPeriodicites = listPeriodicites;
	}

	/**
	 * [ServicesFaitsBB.selectedPeriodiciteCode] Getter
	 * 
	 * @author rlaib on : 4 nov. 2014 08:50:12
	 * @return the selectedPeriodiciteCode
	 */
	public String getSelectedPeriodiciteCode() {
		return selectedPeriodiciteCode;
	}

	/**
	 * [ServicesFaitsBB.selectedPeriodiciteCode] Setter
	 * 
	 * @author rlaib on : 4 nov. 2014 08:50:12
	 * @param selectedPeriodiciteCode
	 *            the selectedPeriodiciteCode to set
	 */
	public void setSelectedPeriodiciteCode(String selectedPeriodiciteCode) {
		this.selectedPeriodiciteCode = selectedPeriodiciteCode;
	}

	/**
	 * [ServicesFaitsBB.selectedPeriode] Getter
	 * 
	 * @author rlaib on : 4 nov. 2014 08:50:12
	 * @return the selectedPeriode
	 */
	public Integer getSelectedPeriode() {
		return selectedPeriode;
	}

	/**
	 * [ServicesFaitsBB.selectedPeriode] Setter
	 * 
	 * @author rlaib on : 4 nov. 2014 08:50:12
	 * @param selectedPeriode
	 *            the selectedPeriode to set
	 */
	public void setSelectedPeriode(Integer selectedPeriode) {
		this.selectedPeriode = selectedPeriode;
	}

	/**
	 * [ServicesFaitsBB.listPeriodesParPeriodicite] Getter
	 * 
	 * @author rlaib on : 4 nov. 2014 08:50:12
	 * @return the listPeriodesParPeriodicite
	 */
	public List<SelectItem> getListPeriodesParPeriodicite() {
		return listPeriodesParPeriodicite;
	}

	/**
	 * [ServicesFaitsBB.listPeriodesParPeriodicite] Setter
	 * 
	 * @author rlaib on : 4 nov. 2014 08:50:12
	 * @param listPeriodesParPeriodicite
	 *            the listPeriodesParPeriodicite to set
	 */
	public void setListPeriodesParPeriodicite(List<SelectItem> listPeriodesParPeriodicite) {
		this.listPeriodesParPeriodicite = listPeriodesParPeriodicite;
	}

	/**
	 * [ServicesFaitsBB.selectedChargePedagogiqueId] Getter
	 * 
	 * @author rlaib on : 1 nov. 2014 09:49:43
	 * @return the selectedChargePedagogiqueId
	 */
	public Integer getSelectedChargePedagogiqueId() {
		return selectedChargePedagogiqueId;
	}

	/**
	 * [ServicesFaitsBB.selectedChargePedagogiqueId] Setter
	 * 
	 * @author rlaib on : 1 nov. 2014 09:49:43
	 * @param selectedChargePedagogiqueId
	 *            the selectedChargePedagogiqueId to set
	 */
	public void setSelectedChargePedagogiqueId(Integer selectedChargePedagogiqueId) {
		this.selectedChargePedagogiqueId = selectedChargePedagogiqueId;
	}

	/**
	 * [ServicesFaitsBB.canShowDetails] Getter
	 * 
	 * @author rlaib on : 1 nov. 2014 09:22:32
	 * @return the canShowDetails
	 */
	public boolean isCanShowDetails() {
		return canShowDetails;
	}

	/**
	 * [ServicesFaitsBB.canShowDetails] Setter
	 * 
	 * @author rlaib on : 1 nov. 2014 09:22:32
	 * @param canShowDetails
	 *            the canShowDetails to set
	 */
	public void setCanShowDetails(boolean canShowDetails) {
		this.canShowDetails = canShowDetails;
	}

	/**
	 * [ServicesFaitsBB.enseignantDto] Getter
	 * 
	 * @author rlaib on : 1 nov. 2014 09:03:38
	 * @return the enseignantDto
	 */
	public DossierEmployeDto getEnseignantDto() {
		return enseignantDto;
	}

	/**
	 * [ServicesFaitsBB.enseignantDto] Setter
	 * 
	 * @author rlaib on : 1 nov. 2014 09:03:38
	 * @param enseignantDto
	 *            the enseignantDto to set
	 */
	public void setEnseignantDto(DossierEmployeDto enseignantDto) {
		this.enseignantDto = enseignantDto;
	}

	/**
	 * [ServicesFaitsBB.selectedFicheServices] Getter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:56:06
	 * @return the selectedFicheServices
	 */
	public EnseignantFicheServicesDto getSelectedFicheServices() {
		return selectedFicheServices;
	}

	/**
	 * [ServicesFaitsBB.selectedFicheServices] Setter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:56:06
	 * @param selectedFicheServices
	 *            the selectedFicheServices to set
	 */
	public void setSelectedFicheServices(EnseignantFicheServicesDto selectedFicheServices) {
		this.selectedFicheServices = selectedFicheServices;
	}

	/**
	 * [ServicesFaitsBB.selectedIdCycleOf] Getter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:32:46
	 * @return the selectedIdCycleOf
	 */
	public Integer getSelectedIdCycleOf() {
		return selectedIdCycleOf;
	}

	/**
	 * [ServicesFaitsBB.selectedIdCycleOf] Setter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:32:46
	 * @param selectedIdCycleOf
	 *            the selectedIdCycleOf to set
	 */
	public void setSelectedIdCycleOf(Integer selectedIdCycleOf) {
		this.selectedIdCycleOf = selectedIdCycleOf;
	}

	/**
	 * [ServicesFaitsBB.listOffresFormation] Getter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:31:11
	 * @return the listOffresFormation
	 */
	public List<SelectItem> getListOffresFormation() {
		return listOffresFormation;
	}

	/**
	 * [ServicesFaitsBB.listOffresFormation] Setter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:31:11
	 * @param listOffresFormation
	 *            the listOffresFormation to set
	 */
	public void setListOffresFormation(List<SelectItem> listOffresFormation) {
		this.listOffresFormation = listOffresFormation;
	}

	/**
	 * [ServicesFaitsBB.selectedIdOf] Getter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:31:11
	 * @return the selectedIdOf
	 */
	public Integer getSelectedIdOf() {
		return selectedIdOf;
	}

	/**
	 * [ServicesFaitsBB.selectedIdOf] Setter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:31:11
	 * @param selectedIdOf
	 *            the selectedIdOf to set
	 */
	public void setSelectedIdOf(Integer selectedIdOf) {
		this.selectedIdOf = selectedIdOf;
	}

	/**
	 * [ServicesFaitsBB.listPeriodes] Getter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:24:43
	 * @return the listPeriodes
	 */
	public List<SelectItem> getListPeriodes() {
		return listPeriodes;
	}

	/**
	 * [ServicesFaitsBB.listPeriodes] Setter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:24:43
	 * @param listPeriodes
	 *            the listPeriodes to set
	 */
	public void setListPeriodes(List<SelectItem> listPeriodes) {
		this.listPeriodes = listPeriodes;
	}

	/**
	 * [ServicesFaitsBB.selectedChargePedagogique] Getter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:20:50
	 * @return the selectedChargePedagogique
	 */
	public EnseignantChargePedagogiqueDto getSelectedChargePedagogique() {
		return selectedChargePedagogique;
	}

	/**
	 * [ServicesFaitsBB.selectedChargePedagogique] Setter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:20:50
	 * @param selectedChargePedagogique
	 *            the selectedChargePedagogique to set
	 */
	public void setSelectedChargePedagogique(EnseignantChargePedagogiqueDto selectedChargePedagogique) {
		this.selectedChargePedagogique = selectedChargePedagogique;
	}

	/**
	 * [ServicesFaitsBB.selectedIdYear] Getter
	 * 
	 * @author rlaib on : 30 oct. 2014 16:41:55
	 * @return the selectedIdYear
	 */
	public Integer getSelectedIdYear() {
		return selectedIdYear;
	}

	/**
	 * [ServicesFaitsBB.selectedIdYear] Setter
	 * 
	 * @author rlaib on : 30 oct. 2014 16:41:55
	 * @param selectedIdYear
	 *            the selectedIdYear to set
	 */
	public void setSelectedIdYear(Integer selectedIdYear) {
		this.selectedIdYear = selectedIdYear;
	}

	/**
	 * [ServicesFaitsBB.listYears] Getter
	 * 
	 * @author rlaib on : 30 oct. 2014 16:40:59
	 * @return the listYears
	 */
	public List<SelectItem> getListYears() {
		return listYears;
	}

	/**
	 * [ServicesFaitsBB.listYears] Setter
	 * 
	 * @author rlaib on : 30 oct. 2014 16:40:59
	 * @param listYears
	 *            the listYears to set
	 */
	public void setListYears(List<SelectItem> listYears) {
		this.listYears = listYears;
	}

	/**
	 * [ServicesFaitsBB.selectedDossierEmployeId] Getter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:02:03
	 * @return the selectedDossierEmployeId
	 */
	public Long getSelectedDossierEmployeId() {
		return selectedDossierEmployeId;
	}

	/**
	 * [ServicesFaitsBB.selectedDossierEmployeId] Setter
	 * 
	 * @author rlaib on : 30 oct. 2014 17:02:03
	 * @param selectedDossierEmployeId
	 *            the selectedDossierEmployeId to set
	 */
	public void setSelectedDossierEmployeId(Long selectedDossierEmployeId) {
		this.selectedDossierEmployeId = selectedDossierEmployeId;
	}

	/**
	 * [ServicesFaitsBB.dossierEmployeDtos] Getter
	 * 
	 * @author rlaib on : 30 oct. 2014 16:37:59
	 * @return the dossierEmployeDtos
	 */
	public List<DossierEmployeDto> getDossierEmployeDtos() {
		return dossierEmployeDtos;
	}

	/**
	 * [ServicesFaitsBB.dossierEmployeDtos] Setter
	 * 
	 * @author rlaib on : 30 oct. 2014 16:37:59
	 * @param dossierEmployeDtos
	 *            the dossierEmployeDtos to set
	 */
	public void setDossierEmployeDtos(List<DossierEmployeDto> dossierEmployeDtos) {
		this.dossierEmployeDtos = dossierEmployeDtos;
	}

	/**
	 * [ServicesFaitsBB.selectedFicheServicesChargesPedagogiques] Getter
	 * 
	 * @author rlaib on : 29 oct. 2014 16:06:10
	 * @return the selectedFicheServicesChargesPedagogiques
	 */
	public List<EnseignantChargePedagogiqueDto> getSelectedFicheServicesChargesPedagogiques() {
		return selectedFicheServicesChargesPedagogiques;
	}

	/**
	 * [ServicesFaitsBB.selectedFicheServicesChargesPedagogiques] Setter
	 * 
	 * @author rlaib on : 29 oct. 2014 16:06:10
	 * @param selectedFicheServicesChargesPedagogiques
	 *            the selectedFicheServicesChargesPedagogiques to set
	 */
	public void setSelectedFicheServicesChargesPedagogiques(
			List<EnseignantChargePedagogiqueDto> selectedFicheServicesChargesPedagogiques) {
		this.selectedFicheServicesChargesPedagogiques = selectedFicheServicesChargesPedagogiques;
	}

	/**
	 * [ServicesFaitsBB.ficheHistory] Getter
	 * 
	 * @author rlaib on : 29 oct. 2014 16:06:10
	 * @return the ficheHistory
	 */
	public List<SituationEntiteOccurrenceDto> getFicheHistory() {
		return ficheHistory;
	}

	/**
	 * [ServicesFaitsBB.ficheHistory] Setter
	 * 
	 * @author rlaib on : 29 oct. 2014 16:06:10
	 * @param ficheHistory
	 *            the ficheHistory to set
	 */
	public void setFicheHistory(List<SituationEntiteOccurrenceDto> ficheHistory) {
		this.ficheHistory = ficheHistory;
	}

	/**
	 * [ServicesFaitsBB.selectedServicesFaits] Getter
	 * 
	 * @author rlaib on : 29 oct. 2014 16:06:10
	 * @return the selectedServicesFaits
	 */
	public List<EnseignantServiceFaitDto> getSelectedServicesFaits() {
		return selectedServicesFaits;
	}

	/**
	 * [ServicesFaitsBB.selectedServicesFaits] Setter
	 * 
	 * @author rlaib on : 29 oct. 2014 16:06:10
	 * @param selectedServicesFaits
	 *            the selectedServicesFaits to set
	 */
	public void setSelectedServicesFaits(List<EnseignantServiceFaitDto> selectedServicesFaits) {
		this.selectedServicesFaits = selectedServicesFaits;
	}

	/**
	 * [ServicesFaitsBB.selectedServiceFait] Getter
	 * 
	 * @author rlaib on : 29 oct. 2014 16:06:10
	 * @return the selectedServiceFait
	 */
	public EnseignantServiceFaitDto getSelectedServiceFait() {
		return selectedServiceFait;
	}

	/**
	 * [ServicesFaitsBB.selectedServiceFait] Setter
	 * 
	 * @author rlaib on : 29 oct. 2014 16:06:10
	 * @param selectedServiceFait
	 *            the selectedServiceFait to set
	 */
	public void setSelectedServiceFait(EnseignantServiceFaitDto selectedServiceFait) {
		this.selectedServiceFait = selectedServiceFait;
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
	 * [FicheServicesBB.idEtablissement] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [FicheServicesBB.idEtablissement] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @param idEtablissement
	 *            the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * [FicheServicesBB.codeEtablissement] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @return the codeEtablissement
	 */
	public String getCodeEtablissement() {
		return codeEtablissement;
	}

	/**
	 * [FicheServicesBB.codeEtablissement] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @param codeEtablissement
	 *            the codeEtablissement to set
	 */
	public void setCodeEtablissement(String codeEtablissement) {
		this.codeEtablissement = codeEtablissement;
	}

	/**
	 * [FicheServicesBB.newEtabCode] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @return the newEtabCode
	 */
	public String getNewEtabCode() {
		return newEtabCode;
	}

	/**
	 * [FicheServicesBB.newEtabCode] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @param newEtabCode
	 *            the newEtabCode to set
	 */
	public void setNewEtabCode(String newEtabCode) {
		this.newEtabCode = newEtabCode;
	}

	/**
	 * [FicheServicesBB.libelleEtab] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @return the libelleEtab
	 */
	public String getLibelleEtab() {
		return libelleEtab;
	}

	/**
	 * [FicheServicesBB.libelleEtab] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @param libelleEtab
	 *            the libelleEtab to set
	 */
	public void setLibelleEtab(String libelleEtab) {
		this.libelleEtab = libelleEtab;
	}

	/**
	 * [FicheServicesBB.idYear] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @return the idYear
	 */
	public Integer getIdYear() {
		return idYear;
	}

	/**
	 * [FicheServicesBB.idYear] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @param idYear
	 *            the idYear to set
	 */
	public void setIdYear(Integer idYear) {
		this.idYear = idYear;
	}

	/**
	 * [FicheServicesBB.userId] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * [FicheServicesBB.userId] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * [FicheServicesBB.userFisrtName] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @return the userFisrtName
	 */
	public String getUserFisrtName() {
		return userFisrtName;
	}

	/**
	 * [FicheServicesBB.userFisrtName] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @param userFisrtName
	 *            the userFisrtName to set
	 */
	public void setUserFisrtName(String userFisrtName) {
		this.userFisrtName = userFisrtName;
	}

	/**
	 * [FicheServicesBB.userLastName] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @return the userLastName
	 */
	public String getUserLastName() {
		return userLastName;
	}

	/**
	 * [FicheServicesBB.userLastName] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @param userLastName
	 *            the userLastName to set
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	/**
	 * [FicheServicesBB.libelleYear] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @return the libelleYear
	 */
	public String getLibelleYear() {
		return libelleYear;
	}

	/**
	 * [FicheServicesBB.libelleYear] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:37:37
	 * @param libelleYear
	 *            the libelleYear to set
	 */
	public void setLibelleYear(String libelleYear) {
		this.libelleYear = libelleYear;
	}

	// ===================================================================
	// Listeners
	// ===================================================================
	public void onChargesEnseignementRowSelect(SelectEvent event) {

		canShowServicesFaitsCharge = true;
		initServicesFaitsEnseignant();
	}

	public void handleOffreFormationChange(AjaxBehaviorEvent event) {

		if (selectedIdOf != null) {

			OffreFormationDto oneOf = offreFormationService.findById(selectedIdOf);
			OuvertureOffreFormationDto ouvertureOffreFormationDto = ouvertureOffreFormationService
					.findById(selectedIdOf);
			if (oneOf != null && ouvertureOffreFormationDto.getCycleId() != null) {
				selectedIdCycleOf = ouvertureOffreFormationDto.getCycleId();

			}
		} else {
			listPeriodes = null;
		}
		managePeridoesByCycle(selectedIdCycleOf);
		initChargesPedagogiquesEnseignant();
	}

	public void handlePeriodeChange(AjaxBehaviorEvent event) {

		initChargesPedagogiquesEnseignant();
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

	// ===================================================================
	// ActionListener
	// ===================================================================

	// ===================================================================
	// Listeners
	// ===================================================================
	/**
	 * [ServicesFaitsBB.handleYearChange] Method
	 * 
	 * @author rlaib on : 12 nov. 2014 09:10:30
	 * @param event
	 */
	public void handleYearChange(AjaxBehaviorEvent event) {

		initChargesPedagogiquesEnseignant();
	}

	/**
	 * [ServicesFaitsBB.handleChargesChange] Method
	 * 
	 * @author rlaib on : 12 nov. 2014 09:10:19
	 * @param event
	 */
	public void handleChargesChange(AjaxBehaviorEvent event) {

		initServicesFaitsEnseignant();
	}

	/**
	 * [ServicesFaitsBB.initServicesFaitsEnseignant] Method
	 * 
	 * @author rlaib on : 4 nov. 2014 09:23:40
	 */
	private void initServicesFaitsEnseignant() {
		try {

			if (selectedDossierEmployeId == null || selectedIdYear == null || idEtablissement == null
					|| selectedPeriode == null || selectedChargePedagogique == null) {
				return;
			}
			selectedChargePedagogiqueId = selectedChargePedagogique.getId();
			listTypeSeanceServiceFait = initListTypesSeance();
			listSituationsServiceFait = initListStatutsSeance();

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
			selectedServicesFaits = enseignantServiceFaitService
					.findServicesFaitsByEtabByUserByOfByPeriodeByYearByCharge(this.idEtablissement,
							this.selectedDossierEmployeId, this.selectedIdYear, null, this.selectedPeriode,
							this.selectedChargePedagogiqueId);
			canShowServicesFaitsCharge = true;
			canShowDetails = true;
			// calcul total du volume horaire (VH) des services faits pour a
			// charge selectionn�e
			if (selectedServicesFaits != null && selectedServicesFaits.size() > 0) {
				totalVHServicesFaitsCharge = calculTotalVH(selectedServicesFaits);
			} else {
				totalVHServicesFaitsCharge = (double) 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [ServicesFaitsBB.calculTotalVH] Method
	 * 
	 * @author rlaib on : 12 nov. 2014 09:21:39
	 * @param _list
	 * @return
	 */
	private double calculTotalVH(List<EnseignantServiceFaitDto> _list) {

		double _total = 0;
		for (EnseignantServiceFaitDto _item : _list) {
			_total = _total + _item.getDureeSeance();
		}
		return _total;
	}

	/**
	 * [ServicesFaitsBB.handleEnseignantChange] Method
	 * 
	 * @author rlaib on : 12 nov. 2014 09:10:54
	 * @param event
	 */
	public void handleEnseignantChange(AjaxBehaviorEvent event) {

		initChargesPedagogiquesEnseignant();
	}

	/**
	 * [ServicesFaitsBB.handlePeriodiciteChange] Method
	 * 
	 * @author rlaib on : 12 nov. 2014 09:10:58
	 * @param event
	 */
	public void handlePeriodiciteChange(AjaxBehaviorEvent event) {

		if (selectedPeriodiciteCode != null) {
			listPeriodesParPeriodicite = initPeriodesParPeriodicite(selectedPeriodiciteCode);
			if (listPeriodesParPeriodicite != null && listPeriodesParPeriodicite.size() > 0) {
				selectedPeriode = Integer.parseInt(listPeriodesParPeriodicite.get(0).getValue().toString());
			}
			initChargesPedagogiquesEnseignant();
		}
	}

	/**
	 * [ServicesFaitsBB.onItemSelect] Method
	 * 
	 * @author rlaib on : 12 nov. 2014 09:11:05
	 * @param event
	 */
	public void onItemSelect(SelectEvent event) {

		// Chargement des charges pedagogiques de l enseignant selectionne dans
		// la recherche auto-complete.
		selectedDossierEmployeId = selectedDossierEmploye.getId();
		initChargesPedagogiquesEnseignant();
	}

	// ===================================================================
	// Actions and Methods
	// ===================================================================
	/**
	 * [ServicesFaitsBB.saveServiceFait] Method
	 * 
	 * @author rlaib on : 12 nov. 2014 09:12:44
	 */
	public void saveServiceFait() {

		RequestContext context = RequestContext.getCurrentInstance();
		try {

			if (selectedServiceFait == null)
				return;
			else {
				if (selectedServiceFait.getNbEtudiantsPresents() == null) {
					// Le NB presences est requis pour TD ou TP
					if (selectedChargePedagogique.getApCodeType()
							.equals(OfConstants.CHARGE_PEDAGOGIQUE_AP_TYPE_TD_CODE)
							|| selectedChargePedagogique.getApCodeType().equals(
									OfConstants.CHARGE_PEDAGOGIQUE_AP_TYPE_TP_CODE)) {
						MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
								OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.SERVICE_FAIT_ENSEIGNANT_SAVE_CONTROLE_NB_PRESENCES), MessageUtil
								.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
										OfConstants.SERVICE_FAIT_ENSEIGNANT_SAVE_CONTROLE_NB_PRESENCES));
						context.addCallbackParam("isValid", false);
						return;
					}
				}
			}
			selectedServiceFait.setChargeId(selectedChargePedagogique.getId());
			long diff = selectedServiceFait.getHeureFinSeance().getTime()
					- selectedServiceFait.getHeureDebutSeance().getTime();
			Double minutes = (double) TimeUnit.MILLISECONDS.toMinutes(diff);
			selectedServiceFait.setDureeSeance(minutes);
			enseignantServiceFaitService.insertOrUpdate(selectedServiceFait);

			initServicesFaitsEnseignant();
			MessageUtil.addInfoMessageWithoutKey(
					MessageUtil.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.SERVICE_FAIT_ENSEIGNANT_SAVE_SUCCESS), MessageUtil
							.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
									OfConstants.SERVICE_FAIT_ENSEIGNANT_SAVE_SUCCESS));
			context.addCallbackParam("isValid", true);
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addErrorMessageWithoutKey(
					MessageUtil.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.SERVICE_FAIT_ENSEIGNANT_SAVE_FAILURE), MessageUtil
							.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
									OfConstants.SERVICE_FAIT_ENSEIGNANT_SAVE_FAILURE));
			context.addCallbackParam("isValid", false);
		}

	}

	/**
	 * [ServicesFaitsBB.updateServiceFait] Method
	 * 
	 * @author rlaib on : 12 nov. 2014 09:12:56
	 */
	public void updateServiceFait() {

	}

	/**
	 * [ServicesFaitsBB.prepareCurrentServiceFait] Method
	 * 
	 * @author rlaib on : 12 nov. 2014 09:13:16
	 */
	public void prepareCurrentServiceFait() {

		selectedServiceFait = new EnseignantServiceFaitDto();
		selectedServiceFait.setChargeId(selectedChargePedagogique.getId());
		addOrEditDialog = false;

	}

	/**
	 * [ServicesFaitsBB.editServiceFait] Method
	 * 
	 * @author rlaib on : 12 nov. 2014 09:13:27
	 */
	public void editServiceFait() {

		try {

			if (selectedChargePedagogiqueId != null && selectedServiceFaitId != null) {
				addOrEditDialog = true;
				selectedServiceFait = enseignantServiceFaitService.findById(selectedServiceFaitId);
			}

		} catch (Exception ex) {

		}
	}

	// ===================================================================
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

	/**
	 * [ServicesFaitsBB.initChargesPedagogiquesEnseignant] Method
	 * 
	 * @author rlaib on : 30 oct. 2014 17:48:40
	 */
	private void initChargesPedagogiquesEnseignant() {

		try {

			if (selectedDossierEmploye == null || selectedIdYear == null || idEtablissement == null
					|| selectedPeriode == null) {
				canShowDetails = false;
				return;
			}
			selectedDossierEmployeId = selectedDossierEmploye.getId();
			enseignantDto = enseignantFicheVoeuxService.getEnseignantById(selectedDossierEmployeId);
			List<EnseignantFicheServicesDto> enseignantFicheServicesDtos = enseignantFicheServicesService
					.findFichesServicesByEtabByUserByPeriodeByYear(idEtablissement, selectedDossierEmployeId,
							selectedIdYear, selectedPeriode);
			if (enseignantFicheServicesDtos == null || enseignantFicheServicesDtos.size() == 0) {
				selectedFicheServicesChargesPedagogiques = null;
				canShowDetails = true;
				return;
			} else
				selectedFicheServices = enseignantFicheServicesDtos.get(0);
			selectedFicheServicesChargesPedagogiques = enseignantFicheServicesService
					.findChargesPedagogiquesByFicheServicesId(selectedFicheServices.getId());
			if (selectedFicheServicesChargesPedagogiques != null && selectedFicheServicesChargesPedagogiques.size() > 0) {
				selectedChargePedagogique = selectedFicheServicesChargesPedagogiques.get(0);
				initServicesFaitsEnseignant();
			}
			canShowDetails = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [ServicesFaitsBB.initYearsList] Method
	 * 
	 * @author rlaib on : 4 nov. 2014 08:59:19
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

	/**
	 * [ServicesFaitsBB.initOffresFormation] Method
	 * 
	 * @author rlaib on : 4 nov. 2014 08:59:28
	 * @param withDefaultValue
	 */
	void initOffresFormation(boolean withDefaultValue) {
		try {

			List<SelectItem> result = new ArrayList<SelectItem>();
			OuvertureOffreFormationDto searchDto = new OuvertureOffreFormationDto();
			searchDto.setIdEtablissement(this.newEtabId);
			searchDto.setAnneeAcademiqueId(this.idYear);
			searchDto.setEstOuverte(true);
			List<OuvertureOffreFormationDto> _list = ouvertureOffreFormationService.findAdvanced(searchDto);

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
				managePeridoesByCycle(selectedIdCycleOf);
			}

			listOffresFormation = result;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [ServicesFaitsBB.managePeridoesByCycle] Method
	 * 
	 * @author rlaib on : 4 nov. 2014 08:59:43
	 * @param idCycle
	 */
	private void managePeridoesByCycle(Integer idCycle) {

		if (idCycle == null) {
			return;
		} else {
			List<NiveauDto> niveauList = niveauService.findByCycleId(idCycle);
			if (niveauList != null) {
				listPeriodes = new ArrayList<SelectItem>();
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
					listPeriodes.add(niveau);
				}
			}
			SelectItemGroup firstGroup = (SelectItemGroup) listPeriodes.get(0);
			if (firstGroup != null && firstGroup.getSelectItems().length > 0) {
				selectedPeriode = Integer.parseInt(firstGroup.getSelectItems()[0].getValue().toString());
			}
		}
	}

	/**
	 * [ServicesFaitsBB.initNomenclatures] Method
	 * 
	 * @author rlaib on : 4 nov. 2014 08:59:52
	 */
	void initNomenclatures() {

		try {
			listPeriodicites = initNomenclatureList(OfConstants.NC_NAME_CYCLE_PERIODE_PERIODICITE);
			if (listPeriodicites != null && listPeriodicites.size() > 0) {
				String codePeriodicite = listPeriodicites.get(0).getValue().toString();
				listPeriodesParPeriodicite = initPeriodesParPeriodicite(codePeriodicite);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [ServicesFaitsBB.initNomenclatureList] Method
	 * 
	 * @author rlaib on : 4 nov. 2014 08:59:59
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
	 * [ServicesFaitsBB.initPeriodesParPeriodicite] Method
	 * 
	 * @author rlaib on : 4 nov. 2014 09:00:08
	 * @param codePeriodicite
	 * @return
	 */
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

	/**
	 * [ServicesFaitsBB.initListTypesSeance] Method
	 * 
	 * @author rlaib on : 12 nov. 2014 09:08:56
	 * @return
	 */
	public List<SelectItem> initListTypesSeance() {

		try {
			List<TypeSeanceDto> _list = enseignantServiceFaitService.getAllTypesSeances();
			List<SelectItem> result = new ArrayList<SelectItem>();
			for (TypeSeanceDto _item : _list) {
				result.add(new SelectItem(_item.getId(), _item.getLibelleFr()));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [ServicesFaitsBB.initListStatutsSeance] Method
	 * 
	 * @author rlaib on : 12 nov. 2014 09:09:06
	 * @return
	 */
	public List<SelectItem> initListStatutsSeance() {

		try {
			List<SituationEntiteDto> _list = situationService
					.findSituationsByEntiteCode(UtilConstants.ENTITE_CHARGE_SERVICE_FAIT);
			List<SelectItem> result = new ArrayList<SelectItem>();
			for (SituationEntiteDto _item : _list) {
				result.add(new SelectItem(_item.getId(), _item.getLibelleSituation()));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [ServicesFaitsBB.getConvertVH] Method
	 * 
	 * @author rlaib on : 12 nov. 2014 09:09:18
	 * @param vh
	 * @return
	 */
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

	/**
	 * [ServicesFaitsBB.initParamsPeriode] Method
	 * 
	 * @author rlaib on : 25 nov. 2014 10:38:35
	 */
	private void initParamsPeriode() {

		try {
			NomenclatureDto _nomenclaturePeriode = nomenclatureService.findById(this.selectedFicheServices
					.getFicheVoeuxPeriodeId());
			NomenclatureDto _nomenclaturePeriodicite = nomenclatureService.findById(_nomenclaturePeriode
					.getIdReference());
			List<PeriodeParamDto> _params = periodeService.findPeriodParamByPeriodiciteByYear(this.selectedIdYear,
					_nomenclaturePeriodicite.getCode());
			paramsPeriode = new HashMap<String, String>();
			paramsPeriode.clear();
			for (PeriodeParamDto periodeParamDto : _params) {
				String val = periodeParamDto.getValeur().toString();
				paramsPeriode.put(periodeParamDto.getCode(), val);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [ServicesFaitsBB.newEtabId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 nov. 2014 14:48:14
	 * @return the newEtabId
	 */
	public Integer getNewEtabId() {
		return newEtabId;
	}

	/**
	 * [ServicesFaitsBB.newEtabId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 nov. 2014 14:48:14
	 * @param newEtabId
	 *            the newEtabId to set
	 */
	public void setNewEtabId(Integer newEtabId) {
		this.newEtabId = newEtabId;
	}

}
