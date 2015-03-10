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

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeParamDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.DossierEmployeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantChargePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantFicheServicesDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantFicheVoeuxDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuLigneDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantFicheServicesService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantFicheVoeuxService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.MessageUtil;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

@ManagedBean(name = "ficheServicesBB")
@ViewScoped
public class FicheServicesBB {

	// ===================================================================
	// Constructors
	// ===================================================================
	public FicheServicesBB() {

	}

	@PostConstruct
	public void init() {

		try {
			// init session infos
			initSessionInfos();
			// init nomeclatures
			initNomenclatures();

			// init Liste annees
			initYearsList();
			ficheVoeuxBB.setSelectedIdYear(selectedIdYearFicheServices);
			ficheVoeuxBB.initOffresFormation(false);
			ficheVoeuxBB.setToSubmit(false);
			ficheVoeuxBB.setToEdit(false);
			initListFichesServices();
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
	private List<EnseignantFicheServicesDto> fichesServices;
	private EnseignantFicheServicesDto selectedFicheServices;
	private List<EnseignantChargePedagogiqueDto> selectedFicheServicesChargesPedagogiques;
	private EnseignantChargePedagogiqueDto selectedChargePedagogique;
	private List<SituationEntiteOccurrenceDto> ficheHistory;
	private Integer selectedChargePedagogiqueId;
	private boolean canShowFicheDetails = false;
	private boolean canValidate = false;
	private List<ChargePedagogiqueRecap> chargePedagogiqueRecaps;
	private List<ChargePedagogiqueRecap> chargePedagogiqueRecapsEq;
	private Map<String, String> paramsPeriode;
	private Double totalVHReference;
	private Double totalVHNormalAutorise;
	private Double totalVHNormalCM;
	private Double totalVHNormalTD;
	private Double totalVHNormalTP;
	private Double totalVHNormalPeriodeCM;
	private Double totalVHNormalPeriodeTD;
	private Double totalVHNormalPeriodeTP;
	private Double totalVHCompl;
	private Double totalVHComplAutorise;
	private Double totalVHEquivalent;
	private Double heureEquiReferenceCM;
	private Double heureEquiReferenceTP;
	private String cssColorDiff = "color:#336699;";
	private String cssColorDiff1 = "color:#EB7221;";
	private DossierEmployeDto enseignantDto;
	private List<SelectItem> listPeriodicitesFicheServices;
	private String selectedPeriodiciteFicheServices;
	private Integer selectedPeriodeFicheServices;
	private List<SelectItem> listPeriodesParPeriodiciteFicheServices;
	private Integer selectedIdYearFicheServices;
	private List<SelectItem> listYearsFicheServices;
	private boolean checkAll = false;
	private boolean toCheckAll = false;

	/**
	 * [FicheServicesBB.listPeriodicitesFicheServices] Getter
	 * 
	 * @author rlaib on : 5 nov. 2014 14:13:53
	 * @return the listPeriodicitesFicheServices
	 */
	public List<SelectItem> getListPeriodicitesFicheServices() {
		return listPeriodicitesFicheServices;
	}

	/**
	 * [FicheServicesBB.listPeriodicitesFicheServices] Setter
	 * 
	 * @author rlaib on : 5 nov. 2014 14:13:53
	 * @param listPeriodicitesFicheServices
	 *            the listPeriodicitesFicheServices to set
	 */
	public void setListPeriodicitesFicheServices(List<SelectItem> listPeriodicitesFicheServices) {
		this.listPeriodicitesFicheServices = listPeriodicitesFicheServices;
	}

	/**
	 * [FicheServicesBB.selectedPeriodiciteFicheServices] Getter
	 * 
	 * @author rlaib on : 5 nov. 2014 14:13:53
	 * @return the selectedPeriodiciteFicheServices
	 */
	public String getSelectedPeriodiciteFicheServices() {
		return selectedPeriodiciteFicheServices;
	}

	/**
	 * [FicheServicesBB.selectedPeriodiciteFicheServices] Setter
	 * 
	 * @author rlaib on : 5 nov. 2014 14:13:53
	 * @param selectedPeriodiciteFicheServices
	 *            the selectedPeriodiciteFicheServices to set
	 */
	public void setSelectedPeriodiciteFicheServices(String selectedPeriodiciteFicheServices) {
		this.selectedPeriodiciteFicheServices = selectedPeriodiciteFicheServices;
	}

	/**
	 * [FicheServicesBB.selectedPeriodeFicheServices] Getter
	 * 
	 * @author rlaib on : 5 nov. 2014 14:13:53
	 * @return the selectedPeriodeFicheServices
	 */
	public Integer getSelectedPeriodeFicheServices() {
		return selectedPeriodeFicheServices;
	}

	/**
	 * [FicheServicesBB.selectedPeriodeFicheServices] Setter
	 * 
	 * @author rlaib on : 5 nov. 2014 14:13:53
	 * @param selectedPeriodeFicheServices
	 *            the selectedPeriodeFicheServices to set
	 */
	public void setSelectedPeriodeFicheServices(Integer selectedPeriodeFicheServices) {
		this.selectedPeriodeFicheServices = selectedPeriodeFicheServices;
	}

	/**
	 * [FicheServicesBB.listPeriodesParPeriodiciteFicheServices] Getter
	 * 
	 * @author rlaib on : 5 nov. 2014 14:13:53
	 * @return the listPeriodesParPeriodiciteFicheServices
	 */
	public List<SelectItem> getListPeriodesParPeriodiciteFicheServices() {
		return listPeriodesParPeriodiciteFicheServices;
	}

	/**
	 * [FicheServicesBB.listPeriodesParPeriodiciteFicheServices] Setter
	 * 
	 * @author rlaib on : 5 nov. 2014 14:13:53
	 * @param listPeriodesParPeriodiciteFicheServices
	 *            the listPeriodesParPeriodiciteFicheServices to set
	 */
	public void setListPeriodesParPeriodiciteFicheServices(List<SelectItem> listPeriodesParPeriodiciteFicheServices) {
		this.listPeriodesParPeriodiciteFicheServices = listPeriodesParPeriodiciteFicheServices;
	}

	/**
	 * [FicheServicesBB.selectedIdYearFicheServices] Getter
	 * 
	 * @author rlaib on : 5 nov. 2014 14:13:53
	 * @return the selectedIdYearFicheServices
	 */
	public Integer getSelectedIdYearFicheServices() {
		return selectedIdYearFicheServices;
	}

	/**
	 * [FicheServicesBB.selectedIdYearFicheServices] Setter
	 * 
	 * @author rlaib on : 5 nov. 2014 14:13:53
	 * @param selectedIdYearFicheServices
	 *            the selectedIdYearFicheServices to set
	 */
	public void setSelectedIdYearFicheServices(Integer selectedIdYearFicheServices) {
		this.selectedIdYearFicheServices = selectedIdYearFicheServices;
	}

	/**
	 * [FicheServicesBB.listYearsFicheServices] Getter
	 * 
	 * @author rlaib on : 5 nov. 2014 14:13:53
	 * @return the listYearsFicheServices
	 */
	public List<SelectItem> getListYearsFicheServices() {
		return listYearsFicheServices;
	}

	/**
	 * [FicheServicesBB.listYearsFicheServices] Setter
	 * 
	 * @author rlaib on : 5 nov. 2014 14:13:53
	 * @param listYearsFicheServices
	 *            the listYearsFicheServices to set
	 */
	public void setListYearsFicheServices(List<SelectItem> listYearsFicheServices) {
		this.listYearsFicheServices = listYearsFicheServices;
	}

	/**
	 * [FicheServicesBB.checkAll] Getter
	 * 
	 * @author rlaib on : 10 déc. 2014 11:53:05
	 * @return the checkAll
	 */
	public boolean isCheckAll() {
		return checkAll;
	}

	/**
	 * [FicheServicesBB.checkAll] Setter
	 * 
	 * @author rlaib on : 10 déc. 2014 11:53:05
	 * @param checkAll
	 *            the checkAll to set
	 */
	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	/**
	 * [FicheServicesBB.toCheckAll] Getter
	 * 
	 * @author rlaib on : 10 déc. 2014 13:24:17
	 * @return the toCheckAll
	 */
	public boolean isToCheckAll() {
		return toCheckAll;
	}

	/**
	 * [FicheServicesBB.toCheckAll] Setter
	 * 
	 * @author rlaib on : 10 déc. 2014 13:24:17
	 * @param toCheckAll
	 *            the toCheckAll to set
	 */
	public void setToCheckAll(boolean toCheckAll) {
		this.toCheckAll = toCheckAll;
	}

	/**
	 * [FicheServicesBB.enseignantDto] Getter
	 * 
	 * @author rlaib on : 29 oct. 2014 18:41:32
	 * @return the enseignantDto
	 */
	public DossierEmployeDto getEnseignantDto() {
		return enseignantDto;
	}

	/**
	 * [FicheServicesBB.enseignantDto] Setter
	 * 
	 * @author rlaib on : 29 oct. 2014 18:41:32
	 * @param enseignantDto
	 *            the enseignantDto to set
	 */
	public void setEnseignantDto(DossierEmployeDto enseignantDto) {
		this.enseignantDto = enseignantDto;
	}

	/**
	 * [FicheServicesBB.cssColorDiff1] Getter
	 * 
	 * @author rlaib on : 4 nov. 2014 18:21:37
	 * @return the cssColorDiff1
	 */
	public String getCssColorDiff1() {
		return cssColorDiff1;
	}

	/**
	 * [FicheServicesBB.cssColorDiff1] Setter
	 * 
	 * @author rlaib on : 4 nov. 2014 18:21:37
	 * @param cssColorDiff1
	 *            the cssColorDiff1 to set
	 */
	public void setCssColorDiff1(String cssColorDiff1) {
		this.cssColorDiff1 = cssColorDiff1;
	}

	/**
	 * [FicheServicesBB.cssColorDiff] Getter
	 * 
	 * @author rlaib on : 29 oct. 2014 09:53:41
	 * @return the cssColorDiff
	 */
	public String getCssColorDiff() {
		return cssColorDiff;
	}

	/**
	 * [FicheServicesBB.cssColorDiff] Setter
	 * 
	 * @author rlaib on : 29 oct. 2014 09:53:41
	 * @param cssColorDiff
	 *            the cssColorDiff to set
	 */
	public void setCssColorDiff(String cssColorDiff) {
		this.cssColorDiff = cssColorDiff;
	}

	/**
	 * [FicheServicesBB.heureEquiReferenceCM] Getter
	 * 
	 * @author rlaib on : 29 oct. 2014 09:38:39
	 * @return the heureEquiReferenceCM
	 */
	public Double getHeureEquiReferenceCM() {
		return heureEquiReferenceCM;
	}

	/**
	 * [FicheServicesBB.heureEquiReferenceCM] Setter
	 * 
	 * @author rlaib on : 29 oct. 2014 09:38:39
	 * @param heureEquiReferenceCM
	 *            the heureEquiReferenceCM to set
	 */
	public void setHeureEquiReferenceCM(Double heureEquiReferenceCM) {
		this.heureEquiReferenceCM = heureEquiReferenceCM;
	}

	/**
	 * [FicheServicesBB.heureEquiReferenceTP] Getter
	 * 
	 * @author rlaib on : 29 oct. 2014 09:38:39
	 * @return the heureEquiReferenceTP
	 */
	public Double getHeureEquiReferenceTP() {
		return heureEquiReferenceTP;
	}

	/**
	 * [FicheServicesBB.heureEquiReferenceTP] Setter
	 * 
	 * @author rlaib on : 29 oct. 2014 09:38:39
	 * @param heureEquiReferenceTP
	 *            the heureEquiReferenceTP to set
	 */
	public void setHeureEquiReferenceTP(Double heureEquiReferenceTP) {
		this.heureEquiReferenceTP = heureEquiReferenceTP;
	}

	/**
	 * [FicheServicesBB.totalVHNormalPeriodeCM] Getter
	 * 
	 * @author rlaib on : 28 oct. 2014 15:46:20
	 * @return the totalVHNormalPeriodeCM
	 */
	public Double getTotalVHNormalPeriodeCM() {
		return totalVHNormalPeriodeCM;
	}

	/**
	 * [FicheServicesBB.totalVHNormalPeriodeCM] Setter
	 * 
	 * @author rlaib on : 28 oct. 2014 15:46:20
	 * @param totalVHNormalPeriodeCM
	 *            the totalVHNormalPeriodeCM to set
	 */
	public void setTotalVHNormalPeriodeCM(Double totalVHNormalPeriodeCM) {
		this.totalVHNormalPeriodeCM = totalVHNormalPeriodeCM;
	}

	/**
	 * [FicheServicesBB.totalVHNormalPeriodeTD] Getter
	 * 
	 * @author rlaib on : 28 oct. 2014 15:46:20
	 * @return the totalVHNormalPeriodeTD
	 */
	public Double getTotalVHNormalPeriodeTD() {
		return totalVHNormalPeriodeTD;
	}

	/**
	 * [FicheServicesBB.totalVHNormalPeriodeTD] Setter
	 * 
	 * @author rlaib on : 28 oct. 2014 15:46:20
	 * @param totalVHNormalPeriodeTD
	 *            the totalVHNormalPeriodeTD to set
	 */
	public void setTotalVHNormalPeriodeTD(Double totalVHNormalPeriodeTD) {
		this.totalVHNormalPeriodeTD = totalVHNormalPeriodeTD;
	}

	/**
	 * [FicheServicesBB.totalVHNormalPeriodeTP] Getter
	 * 
	 * @author rlaib on : 28 oct. 2014 15:46:20
	 * @return the totalVHNormalPeriodeTP
	 */
	public Double getTotalVHNormalPeriodeTP() {
		return totalVHNormalPeriodeTP;
	}

	/**
	 * [FicheServicesBB.totalVHNormalPeriodeTP] Setter
	 * 
	 * @author rlaib on : 28 oct. 2014 15:46:20
	 * @param totalVHNormalPeriodeTP
	 *            the totalVHNormalPeriodeTP to set
	 */
	public void setTotalVHNormalPeriodeTP(Double totalVHNormalPeriodeTP) {
		this.totalVHNormalPeriodeTP = totalVHNormalPeriodeTP;
	}

	/**
	 * [FicheServicesBB.totalVHNormalCM] Getter
	 * 
	 * @author rlaib on : 28 oct. 2014 15:37:07
	 * @return the totalVHNormalCM
	 */
	public Double getTotalVHNormalCM() {
		return totalVHNormalCM;
	}

	/**
	 * [FicheServicesBB.totalVHNormalCM] Setter
	 * 
	 * @author rlaib on : 28 oct. 2014 15:37:07
	 * @param totalVHNormalCM
	 *            the totalVHNormalCM to set
	 */
	public void setTotalVHNormalCM(Double totalVHNormalCM) {
		this.totalVHNormalCM = totalVHNormalCM;
	}

	/**
	 * [FicheServicesBB.totalVHNormalTD] Getter
	 * 
	 * @author rlaib on : 28 oct. 2014 15:37:07
	 * @return the totalVHNormalTD
	 */
	public Double getTotalVHNormalTD() {
		return totalVHNormalTD;
	}

	/**
	 * [FicheServicesBB.totalVHNormalTD] Setter
	 * 
	 * @author rlaib on : 28 oct. 2014 15:37:07
	 * @param totalVHNormalTD
	 *            the totalVHNormalTD to set
	 */
	public void setTotalVHNormalTD(Double totalVHNormalTD) {
		this.totalVHNormalTD = totalVHNormalTD;
	}

	/**
	 * [FicheServicesBB.totalVHNormalTP] Getter
	 * 
	 * @author rlaib on : 28 oct. 2014 15:37:07
	 * @return the totalVHNormalTP
	 */
	public Double getTotalVHNormalTP() {
		return totalVHNormalTP;
	}

	/**
	 * [FicheServicesBB.totalVHNormalTP] Setter
	 * 
	 * @author rlaib on : 28 oct. 2014 15:37:07
	 * @param totalVHNormalTP
	 *            the totalVHNormalTP to set
	 */
	public void setTotalVHNormalTP(Double totalVHNormalTP) {
		this.totalVHNormalTP = totalVHNormalTP;
	}

	/**
	 * [FicheServicesBB.totalVHNormalAutorise] Getter
	 * 
	 * @author rlaib on : 28 oct. 2014 10:56:27
	 * @return the totalVHNormalAutorise
	 */
	public Double getTotalVHNormalAutorise() {
		return totalVHNormalAutorise;
	}

	/**
	 * [FicheServicesBB.totalVHNormalAutorise] Setter
	 * 
	 * @author rlaib on : 28 oct. 2014 10:56:27
	 * @param totalVHNormalAutorise
	 *            the totalVHNormalAutorise to set
	 */
	public void setTotalVHNormalAutorise(Double totalVHNormalAutorise) {
		this.totalVHNormalAutorise = totalVHNormalAutorise;
	}

	/**
	 * [FicheServicesBB.totalVHCompl] Getter
	 * 
	 * @author rlaib on : 28 oct. 2014 10:56:27
	 * @return the totalVHCompl
	 */
	public Double getTotalVHCompl() {
		return totalVHCompl;
	}

	/**
	 * [FicheServicesBB.totalVHCompl] Setter
	 * 
	 * @author rlaib on : 28 oct. 2014 10:56:27
	 * @param totalVHCompl
	 *            the totalVHCompl to set
	 */
	public void setTotalVHCompl(Double totalVHCompl) {
		this.totalVHCompl = totalVHCompl;
	}

	/**
	 * [FicheServicesBB.totalVHComplAutorise] Getter
	 * 
	 * @author rlaib on : 28 oct. 2014 10:56:27
	 * @return the totalVHComplAutorise
	 */
	public Double getTotalVHComplAutorise() {
		return totalVHComplAutorise;
	}

	/**
	 * [FicheServicesBB.totalVHComplAutorise] Setter
	 * 
	 * @author rlaib on : 28 oct. 2014 10:56:27
	 * @param totalVHComplAutorise
	 *            the totalVHComplAutorise to set
	 */
	public void setTotalVHComplAutorise(Double totalVHComplAutorise) {
		this.totalVHComplAutorise = totalVHComplAutorise;
	}

	/**
	 * [FicheServicesBB.totalVHReference] Getter
	 * 
	 * @author rlaib on : 28 oct. 2014 10:42:40
	 * @return the totalVHReference
	 */
	public Double getTotalVHReference() {
		return totalVHReference;
	}

	/**
	 * [FicheServicesBB.totalVHReference] Setter
	 * 
	 * @author rlaib on : 28 oct. 2014 10:42:40
	 * @param totalVHReference
	 *            the totalVHReference to set
	 */
	public void setTotalVHReference(Double totalVHReference) {
		this.totalVHReference = totalVHReference;
	}

	/**
	 * [FicheServicesBB.totalVHEquivalent] Getter
	 * 
	 * @author rlaib on : 28 oct. 2014 10:42:40
	 * @return the totalVHEquivalent
	 */
	public Double getTotalVHEquivalent() {
		return totalVHEquivalent;
	}

	/**
	 * [FicheServicesBB.totalVHEquivalent] Setter
	 * 
	 * @author rlaib on : 28 oct. 2014 10:42:40
	 * @param totalVHEquivalent
	 *            the totalVHEquivalent to set
	 */
	public void setTotalVHEquivalent(Double totalVHEquivalent) {
		this.totalVHEquivalent = totalVHEquivalent;
	}

	/**
	 * [FicheServicesBB.paramsPeriode] Getter
	 * 
	 * @author rlaib on : 28 oct. 2014 09:23:13
	 * @return the paramsPeriode
	 */
	public Map<String, String> getParamsPeriode() {
		return paramsPeriode;
	}

	/**
	 * [FicheServicesBB.paramsPeriode] Setter
	 * 
	 * @author rlaib on : 28 oct. 2014 09:23:13
	 * @param paramsPeriode
	 *            the paramsPeriode to set
	 */
	public void setParamsPeriode(Map<String, String> paramsPeriode) {
		this.paramsPeriode = paramsPeriode;
	}

	/**
	 * [FicheServicesBB.chargePedagogiqueRecaps] Getter
	 * 
	 * @author rlaib on : 28 oct. 2014 09:18:19
	 * @return the chargePedagogiqueRecaps
	 */
	public List<ChargePedagogiqueRecap> getChargePedagogiqueRecaps() {
		return chargePedagogiqueRecaps;
	}

	/**
	 * [FicheServicesBB.chargePedagogiqueRecaps] Setter
	 * 
	 * @author rlaib on : 28 oct. 2014 09:18:19
	 * @param chargePedagogiqueRecaps
	 *            the chargePedagogiqueRecaps to set
	 */
	public void setChargePedagogiqueRecaps(List<ChargePedagogiqueRecap> chargePedagogiqueRecaps) {
		this.chargePedagogiqueRecaps = chargePedagogiqueRecaps;
	}

	/**
	 * [FicheServicesBB.chargePedagogiqueRecapsEq] Getter
	 * 
	 * @author rlaib on : 29 oct. 2014 09:18:01
	 * @return the chargePedagogiqueRecapsEq
	 */
	public List<ChargePedagogiqueRecap> getChargePedagogiqueRecapsEq() {
		return chargePedagogiqueRecapsEq;
	}

	/**
	 * [FicheServicesBB.chargePedagogiqueRecapsEq] Setter
	 * 
	 * @author rlaib on : 29 oct. 2014 09:18:01
	 * @param chargePedagogiqueRecapsEq
	 *            the chargePedagogiqueRecapsEq to set
	 */
	public void setChargePedagogiqueRecapsEq(List<ChargePedagogiqueRecap> chargePedagogiqueRecapsEq) {
		this.chargePedagogiqueRecapsEq = chargePedagogiqueRecapsEq;
	}

	/**
	 * [FicheServicesBB.canValidate] Getter
	 * 
	 * @author rlaib on : 27 oct. 2014 10:17:58
	 * @return the canValidate
	 */
	public boolean isCanValidate() {
		return canValidate;
	}

	/**
	 * [FicheServicesBB.canValidate] Setter
	 * 
	 * @author rlaib on : 27 oct. 2014 10:17:58
	 * @param canValidate
	 *            the canValidate to set
	 */
	public void setCanValidate(boolean canValidate) {
		this.canValidate = canValidate;
	}

	/**
	 * [FicheServicesBB.canShowFicheDetails] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 15:43:50
	 * @return the canShowFicheDetails
	 */
	public boolean isCanShowFicheDetails() {
		return canShowFicheDetails;
	}

	/**
	 * [FicheServicesBB.canShowFicheDetails] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 15:43:50
	 * @param canShowFicheDetails
	 *            the canShowFicheDetails to set
	 */
	public void setCanShowFicheDetails(boolean canShowFicheDetails) {
		this.canShowFicheDetails = canShowFicheDetails;
	}

	/**
	 * [FicheServicesBB.selectedChargePedagogiqueId] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 15:33:02
	 * @return the selectedChargePedagogiqueId
	 */
	public Integer getSelectedChargePedagogiqueId() {
		return selectedChargePedagogiqueId;
	}

	/**
	 * [FicheServicesBB.selectedChargePedagogiqueId] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 15:33:02
	 * @param selectedChargePedagogiqueId
	 *            the selectedChargePedagogiqueId to set
	 */
	public void setSelectedChargePedagogiqueId(Integer selectedChargePedagogiqueId) {
		this.selectedChargePedagogiqueId = selectedChargePedagogiqueId;
	}

	/**
	 * [FicheServicesBB.ficheHistory] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 14:25:12
	 * @return the ficheHistory
	 */
	public List<SituationEntiteOccurrenceDto> getFicheHistory() {
		return ficheHistory;
	}

	/**
	 * [FicheServicesBB.ficheHistory] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 14:25:12
	 * @param ficheHistory
	 *            the ficheHistory to set
	 */
	public void setFicheHistory(List<SituationEntiteOccurrenceDto> ficheHistory) {
		this.ficheHistory = ficheHistory;
	}

	/**
	 * [FicheServicesBB.selectedChargePedagogique] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 11:50:13
	 * @return the selectedChargePedagogique
	 */
	public EnseignantChargePedagogiqueDto getSelectedChargePedagogique() {
		return selectedChargePedagogique;
	}

	/**
	 * [FicheServicesBB.selectedChargePedagogique] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 11:50:13
	 * @param selectedChargePedagogique
	 *            the selectedChargePedagogique to set
	 */
	public void setSelectedChargePedagogique(EnseignantChargePedagogiqueDto selectedChargePedagogique) {
		this.selectedChargePedagogique = selectedChargePedagogique;
	}

	/**
	 * [FicheServicesBB.selectedFicheServicesChargesPedagogiques] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 10:35:34
	 * @return the selectedFicheServicesChargesPedagogiques
	 */
	public List<EnseignantChargePedagogiqueDto> getSelectedFicheServicesChargesPedagogiques() {
		return selectedFicheServicesChargesPedagogiques;
	}

	/**
	 * [FicheServicesBB.selectedFicheServicesChargesPedagogiques] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 10:35:34
	 * @param selectedFicheServicesChargesPedagogiques
	 *            the selectedFicheServicesChargesPedagogiques to set
	 */
	public void setSelectedFicheServicesChargesPedagogiques(
			List<EnseignantChargePedagogiqueDto> selectedFicheServicesChargesPedagogiques) {
		this.selectedFicheServicesChargesPedagogiques = selectedFicheServicesChargesPedagogiques;
	}

	/**
	 * [FicheServicesBB.selectedFicheServices] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 09:18:14
	 * @return the selectedFicheServices
	 */
	public EnseignantFicheServicesDto getSelectedFicheServices() {
		return selectedFicheServices;
	}

	/**
	 * [FicheServicesBB.selectedFicheServices] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 09:18:14
	 * @param selectedFicheServices
	 *            the selectedFicheServices to set
	 */
	public void setSelectedFicheServices(EnseignantFicheServicesDto selectedFicheServices) {
		this.selectedFicheServices = selectedFicheServices;
	}

	/**
	 * [FicheServicesBB.fichesServices] Getter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:39:15
	 * @return the fichesServices
	 */
	public List<EnseignantFicheServicesDto> getFichesServices() {
		return fichesServices;
	}

	/**
	 * [FicheServicesBB.fichesServices] Setter
	 * 
	 * @author rlaib on : 26 oct. 2014 08:39:15
	 * @param fichesServices
	 *            the fichesServices to set
	 */
	public void setFichesServices(List<EnseignantFicheServicesDto> fichesServices) {
		this.fichesServices = fichesServices;
	}

	// ===================================================================
	// Session Infos
	// ===================================================================
	private Integer idEtablissement;
	private String codeEtablissement;
	private String newEtabCode;
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
	public void onFichesServicesRowSelect(SelectEvent event) {

		try {
			loadAndProcessFicheService();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * [FicheServicesBB.loadAndProcessFicheService] Method
	 * 
	 * @author rlaib on : 5 nov. 2014 14:27:56
	 */
	private void loadAndProcessFicheService() {

		selectedFicheServicesChargesPedagogiques = enseignantFicheServicesService
				.findChargesPedagogiquesByFicheServicesId(selectedFicheServices.getId());
		// HISTORIQUE DES SITUATIONS
		this.ficheHistory = situationService.getEntityOccurrenceHistory(UtilConstants.ENTITE_FICHE_SERVICES_ENSEIGNANT,
				selectedFicheServices.getId());
		enseignantDto = enseignantFicheVoeuxService
				.getEnseignantById(selectedFicheServices.getFicheVoeuxEnseignantId());
		if (selectedFicheServices != null) {
			canShowFicheDetails = true;
			processRecap();
		}

	}

	/**
	 * [FicheServicesBB.processRecap] Method
	 * 
	 * @author rlaib on : 28 oct. 2014 09:19:03
	 */
	private void processRecap() {

		try {

			if (selectedFicheServicesChargesPedagogiques != null) {

				initParamsPeriode();
				Integer _paramNBS = Integer.parseInt(paramsPeriode
						.get(OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_NOMBRE_SEMAINES));
				Double _paramVHHCM = Double.parseDouble(paramsPeriode
						.get(OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_VOLUME_HORAIRE_HEBDO_CM));
				Double _paramVHHTD = Double.parseDouble(paramsPeriode
						.get(OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_VOLUME_HORAIRE_HEBDO_TD));
				Double _paramVHHTP = Double.parseDouble(paramsPeriode
						.get(OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_VOLUME_HORAIRE_HEBDO_TP));
				Double _paramTauxVHComp = Double.parseDouble(paramsPeriode
						.get(OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_TAUX_VOLUME_HORAIRE_COMP));
				heureEquiReferenceCM = Double.parseDouble(paramsPeriode
						.get(OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_COEFF_CM_EQUI_CM));
				heureEquiReferenceTP = Double.parseDouble(paramsPeriode
						.get(OfConstants.CHARGE_PEDAGOGIQUE_PERIODE_PARAM_COEFF_CM_EQUI_TP));
				chargePedagogiqueRecaps = new ArrayList<ChargePedagogiqueRecap>();
				chargePedagogiqueRecapsEq = new ArrayList<ChargePedagogiqueRecap>();

				totalVHNormalAutorise = (_paramVHHTD * 60) * _paramNBS;
				totalVHComplAutorise = (totalVHNormalAutorise * _paramTauxVHComp) / 100;
				List<EnseignantVoeuLigneDto> _listAps = enseignantFicheVoeuxService
						.findLignesByIdFiche(selectedFicheServices.getId());
				totalVHEquivalent = (double) 0;
				totalVHReference = (double) 0;
				totalVHNormalCM = (double) 0;
				totalVHNormalTD = (double) 0;
				totalVHNormalTP = (double) 0;
				totalVHNormalPeriodeCM = (double) 0;
				totalVHNormalPeriodeTD = (double) 0;
				totalVHNormalPeriodeTP = (double) 0;
				for (EnseignantVoeuLigneDto _enseignantVoeuLigneDto : _listAps) {

					ChargePedagogiqueRecap _chargePedagogiqueRecap = new ChargePedagogiqueRecap();
					Double _vhhcm = (double) 0;
					Double _vhhtd = (double) 0;
					Double _vhhtp = (double) 0;

					for (EnseignantChargePedagogiqueDto _enseignantChargePedagogiqueDto : selectedFicheServicesChargesPedagogiques) {

						if ((_enseignantChargePedagogiqueDto.getApId() == _enseignantVoeuLigneDto.getIdAp())
								&& (_enseignantChargePedagogiqueDto.isIncluse() || toCheckAll)) {

							if (toCheckAll)
								selectedFicheServicesChargesPedagogiques.get(
										selectedFicheServicesChargesPedagogiques
												.indexOf(_enseignantChargePedagogiqueDto)).setIncluse(checkAll);
							switch (_enseignantChargePedagogiqueDto.getApCodeType()) {
							case OfConstants.CHARGE_PEDAGOGIQUE_AP_TYPE_CM_CODE:
								if ((toCheckAll && checkAll)
										|| (!toCheckAll && _enseignantChargePedagogiqueDto.isIncluse()))
									_vhhcm = _vhhcm + _enseignantChargePedagogiqueDto.getVolumeHoraireAp();
								break;
							case OfConstants.CHARGE_PEDAGOGIQUE_AP_TYPE_TD_CODE:
								if ((toCheckAll && checkAll)
										|| (!toCheckAll && _enseignantChargePedagogiqueDto.isIncluse()))
									_vhhtd = _vhhtd + _enseignantChargePedagogiqueDto.getVolumeHoraireAp();
								break;
							case OfConstants.CHARGE_PEDAGOGIQUE_AP_TYPE_TP_CODE:
								if ((toCheckAll && checkAll)
										|| (!toCheckAll && _enseignantChargePedagogiqueDto.isIncluse()))
									_vhhtp = _vhhtp + _enseignantChargePedagogiqueDto.getVolumeHoraireAp();
								break;

							default:
								break;
							}
						}
					}
					_chargePedagogiqueRecap.setAp(_enseignantVoeuLigneDto.getCodeAp());
					_chargePedagogiqueRecap.setTotalRefVHHMC(_vhhcm);
					_chargePedagogiqueRecap.setTotalRefVHHTD(_vhhtd);
					_chargePedagogiqueRecap.setTotalRefVHHTP(_vhhtp);
					_chargePedagogiqueRecap.setNbSemainesPeriode(_paramNBS);
					chargePedagogiqueRecaps.add(_chargePedagogiqueRecap);

					totalVHNormalCM = totalVHNormalCM + _vhhcm;
					totalVHNormalTD = totalVHNormalTD + _vhhtd;
					totalVHNormalTP = totalVHNormalTP + _vhhtp;
					totalVHNormalPeriodeCM = totalVHNormalPeriodeCM + _vhhcm * _paramNBS;
					totalVHNormalPeriodeTD = totalVHNormalPeriodeTD + _vhhtd * _paramNBS;
					totalVHNormalPeriodeTP = totalVHNormalPeriodeTP + _vhhtp * _paramNBS;
					totalVHReference = totalVHReference + (_vhhcm + _vhhtd + _vhhtp) * _paramNBS;
					totalVHEquivalent = totalVHEquivalent + (_vhhtd) + (_vhhcm * heureEquiReferenceCM)
							+ (_vhhtp * heureEquiReferenceTP);
				}
				totalVHEquivalent = totalVHEquivalent * _paramNBS;
				totalVHCompl = totalVHEquivalent - totalVHNormalAutorise;
				if (totalVHCompl < 0) {
					totalVHCompl = (double) 0;
					cssColorDiff = "color:#336699;";
				} else {
					if (totalVHCompl < totalVHComplAutorise)
						cssColorDiff = "color:#EB7221;";
					else
						cssColorDiff = "color:red;";
				}

				// canValidate = canValidateFicheServices();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void onChargesEnseignementRowSelect(SelectEvent event) {

		try {

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// ===================================================================
	// ActionListener
	// ===================================================================

	// ===================================================================
	// Listeners
	// ===================================================================
	public void handlePeriodiciteChange(AjaxBehaviorEvent event) {

		if (selectedPeriodiciteFicheServices != null) {
			listPeriodesParPeriodiciteFicheServices = initPeriodesParPeriodicite(selectedPeriodiciteFicheServices);
			if (listPeriodesParPeriodiciteFicheServices != null && listPeriodesParPeriodiciteFicheServices.size() > 0) {
				selectedPeriodeFicheServices = Integer.parseInt(listPeriodesParPeriodiciteFicheServices.get(0)
						.getValue().toString());
			}
			initListFichesServices();
		}
	}

	public void handlePeriodeChange(AjaxBehaviorEvent event) {

		initListFichesServices();
	}

	public void handleYearChange(AjaxBehaviorEvent event) {

		ficheVoeuxBB.setSelectedIdYear(selectedIdYearFicheServices);
		ficheVoeuxBB.initOffresFormation(false);

	}

	public void checkAffected() {
		try {
			toCheckAll = false;
			checkAll = false;
			processRecap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkAffectedAll() {
		try {
			toCheckAll = true;
			processRecap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ===================================================================
	// Actions and Methods
	// ===================================================================

	public void validateFicheServices() {
		try {

			if (canValidateFicheServices()) {

				selectedFicheServicesChargesPedagogiques = enseignantFicheServicesService
						.saveChargesPedagogiques(selectedFicheServicesChargesPedagogiques);
				SituationEntiteDto seDto = new SituationEntiteDto();
				seDto = situationService.findBySituationEntiteByCodeSituation(
						UtilConstants.ENTITE_FICHE_SERVICES_ENSEIGNANT, UtilConstants.SITUTAION_VALIDEE_CODE);

				if (seDto != null && seDto.getId() > 0) {
					if (selectedFicheServices != null) {
						selectedFicheServices.setIdSituation(seDto.getId());
						selectedFicheServices = enseignantFicheServicesService.insertOrUpdate(selectedFicheServices);
						// selectedFicheServices =
						// enseignantFicheServicesService.validate(selectedFicheServices.getFicheVoeuxId());
					} else {
						MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
								OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_FAILURE), MessageUtil
								.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
										OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_FAILURE));
						return;
					}
				}
				initListFichesServices();
				MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
						OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
						OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_SUCCESS), MessageUtil
						.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_SUCCESS));
				canShowFicheDetails = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
					OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
					OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_FAILURE), MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_FAILURE));

		}
	}

	public void saveFicheServices() {
		try {

			selectedFicheServicesChargesPedagogiques = enseignantFicheServicesService
					.saveChargesPedagogiques(selectedFicheServicesChargesPedagogiques);
			SituationEntiteDto seDto = new SituationEntiteDto();
			seDto = situationService.findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_FICHE_SERVICES_ENSEIGNANT,
					UtilConstants.SITUTAION_ENREGISTREE_AVANT_VALIDATION_CODE);

			if (seDto != null && seDto.getId() > 0) {
				if (selectedFicheServices != null) {
					selectedFicheServices.setIdSituation(seDto.getId());
					selectedFicheServices = enseignantFicheServicesService.insertOrUpdate(selectedFicheServices);
				} else {
					MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
							OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_SAVE_FAILURE), MessageUtil
							.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
									OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_SAVE_FAILURE));
					return;
				}
			}
			processRecap();
			MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
					OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
					OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_SAVE_SUCCESS), MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_SAVE_SUCCESS));
			canShowFicheDetails = true;

		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
					OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
					OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_SAVE_FAILURE), MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_SAVE_FAILURE));

		}
	}

	/**
	 * [FicheServicesBB.canValidateFicheServices] Method
	 * 
	 * @author rlaib on : 28 oct. 2014 11:16:18
	 * @return
	 */
	private boolean canValidateFicheServices() {

		try {

			if (totalVHReference == 0) {
				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
						OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
						OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_CONTROL_TOTAL_VH_EMPTY), MessageUtil
						.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_CONTROL_TOTAL_VH_EMPTY));
				canValidate = false;
				return false;
			}
			if (totalVHCompl <= totalVHComplAutorise) {
				canValidate = true;
				return true;

			} else {
				MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
						OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
						OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_CONTROL_TOTAL_COMPL), MessageUtil
						.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
								OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_CONTROL_TOTAL_COMPL));
			}

		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(
					OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
					OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_FAILURE), MessageUtil
					.getStringValueFromBundleResource(OfConstants.CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME,
							OfConstants.CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_FAILURE));

		}
		canValidate = false;
		return false;
	}

	public void loadFicheVoeuxDetails() {
		try {
			if (selectedFicheServices != null) {
				EnseignantFicheVoeuxDto enseignantFicheVoeuxDto = enseignantFicheVoeuxService
						.findById(selectedFicheServices.getId());
				ficheVoeuxBB.setHasFicheVoeux(true);
				ficheVoeuxBB.setSelectedFicheVoeux(enseignantFicheVoeuxDto);
				ficheVoeuxBB.loadFicheVoeuxDetails();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// ===================================================================
	// Functions Helper
	// ===================================================================
	void initSessionInfos() {
		try {
			this.idEtablissement = this.sessionBean.getIdEtablissement();
			this.codeEtablissement = this.sessionBean.getAncienCodeEtablissement();
			this.libelleEtab = this.sessionBean.getLlLatinEtablissement();
			this.newEtabCode = this.sessionBean.getCodeEtablissement();
			this.idYear = this.sessionBean.getIdAnneeAcademique();
			this.libelleYear = this.sessionBean.getCodeAnneeAcademique();
			this.userId = this.sessionBean.getSessionBean().getUser().getId();
			// TODO utilisation sessionBean.getSessionBean() ?
			this.userFisrtName = this.sessionBean.getSessionBean().getUser().getPrenomLatin();
			this.userLastName = this.sessionBean.getSessionBean().getUser().getNomLatin();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	/**
	 * [FicheServicesBB.initListFichesServices] Method
	 * 
	 * @author rlaib on : 26 oct. 2014 09:11:36
	 */
	private void initListFichesServices() {

		fichesServices = enseignantFicheServicesService.findFichesServicesByEtabByUserByPeriodeByYear(idEtablissement,
				null, selectedIdYearFicheServices, selectedPeriodeFicheServices);
		if (fichesServices != null && fichesServices.size() > 0) {
			selectedFicheServices = fichesServices.get(0);
			if (selectedFicheServices != null) {
				loadAndProcessFicheService();
			}

		} else
			canShowFicheDetails = false;

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

	void initNomenclatures() {

		try {
			listPeriodicitesFicheServices = initNomenclatureList(OfConstants.NC_NAME_CYCLE_PERIODE_PERIODICITE);
			if (listPeriodicitesFicheServices != null && listPeriodicitesFicheServices.size() > 0) {
				String codePeriodicite = listPeriodicitesFicheServices.get(0).getValue().toString();
				listPeriodesParPeriodiciteFicheServices = initPeriodesParPeriodicite(codePeriodicite);
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

	void initYearsList() {

		try {
			this.listYearsFicheServices = utilBean.loadAnneeAcademique();
			if (this.listYearsFicheServices != null && this.listYearsFicheServices.size() > 0) {

				selectedIdYearFicheServices = Integer.parseInt(listYearsFicheServices.get(0).getValue().toString());
			} else {
				selectedIdYearFicheServices = this.idYear;

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * [FicheServicesBB.initParamsPeriode] Method
	 * 
	 * @author rlaib on : 28 oct. 2014 09:22:07
	 */
	private void initParamsPeriode() {

		try {
			NomenclatureDto _nomenclaturePeriode = nomenclatureService.findById(this.selectedFicheServices
					.getFicheVoeuxPeriodeId());
			NomenclatureDto _nomenclaturePeriodicite = nomenclatureService.findById(_nomenclaturePeriode
					.getIdReference());
			List<PeriodeParamDto> _params = periodeService.findPeriodParamByPeriodiciteByYear(
					this.selectedIdYearFicheServices, _nomenclaturePeriodicite.getCode());
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

}
