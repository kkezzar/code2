package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.inscription;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;

/**
 * @author BELDI Jamel on : 7 juil. 2014 11:11:58
 */
@ManagedBean(name = "dossierInscriptionBean")
@RequestScoped
public class DossierInscriptionBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 14:52:24
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(DossierInscriptionBean.class);
	private int exception;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	private ResourceBundle bundleCommon;
	private ResourceBundle inscriptionBundle;
	@ManagedProperty("#{dossierService}")
	private DossierService dossierService;
	@ManagedProperty("#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;
	@ManagedProperty("#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;
	@ManagedProperty("#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;
	@ManagedProperty("#{bacService}")
	private BacService bacService;
	@ManagedProperty("#{param.dossierInscriptionId}")
	private String dossierInscriptionId;
	@ManagedProperty("#{param.dossierEtudiantId}")
	private String dossierEtudiantId;
	private DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto;

	/**
	 * [DossierInscriptionBean.DossierInscriptionBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:03
	 */
	public DossierInscriptionBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);

	}

	/**
	 * [DossierInscriptionBean.init] Method
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:07
	 */
	@PostConstruct
	public void init() {
		loadDossierInscriptionDto();
	}

	/**
	 * [DossierInscriptionBean.loadDossierInscriptionDto] Method
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:12
	 */
	private void loadDossierInscriptionDto() {
		FacesMessage msg = new FacesMessage();
		dossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto();
		try {

		} catch (Exception e) {
			exception = 1;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ exception + ":"
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [DossierInscriptionBean.exception] Getter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @return the exception
	 */
	public int getException() {
		return exception;
	}

	/**
	 * [DossierInscriptionBean.exception] Setter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @param exception
	 *            the exception to set
	 */
	public void setException(int exception) {
		this.exception = exception;
	}

	/**
	 * [DossierInscriptionBean.flash] Getter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [DossierInscriptionBean.flash] Setter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [DossierInscriptionBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [DossierInscriptionBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [DossierInscriptionBean.dossierService] Getter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @return the dossierService
	 */
	public DossierService getDossierService() {
		return dossierService;
	}

	/**
	 * [DossierInscriptionBean.dossierService] Setter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @param dossierService
	 *            the dossierService to set
	 */
	public void setDossierService(DossierService dossierService) {
		this.dossierService = dossierService;
	}

	/**
	 * [DossierInscriptionBean.dossierEtudiantService] Getter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @return the dossierEtudiantService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [DossierInscriptionBean.dossierEtudiantService] Setter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @param dossierEtudiantService
	 *            the dossierEtudiantService to set
	 */
	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	/**
	 * [DossierInscriptionBean.dossierInscriptionAdministrativeService] Getter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [DossierInscriptionBean.dossierInscriptionAdministrativeService] Setter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @param dossierInscriptionAdministrativeService
	 *            the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [DossierInscriptionBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [DossierInscriptionBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [DossierInscriptionBean.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [DossierInscriptionBean.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [DossierInscriptionBean.bacService] Getter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @return the bacService
	 */
	public BacService getBacService() {
		return bacService;
	}

	/**
	 * [DossierInscriptionBean.bacService] Setter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @param bacService
	 *            the bacService to set
	 */
	public void setBacService(BacService bacService) {
		this.bacService = bacService;
	}

	/**
	 * [DossierInscriptionBean.dossierInscriptionId] Getter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @return the dossierInscriptionId
	 */
	public String getDossierInscriptionId() {
		return dossierInscriptionId;
	}

	/**
	 * [DossierInscriptionBean.dossierInscriptionId] Setter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @param dossierInscriptionId
	 *            the dossierInscriptionId to set
	 */
	public void setDossierInscriptionId(String dossierInscriptionId) {
		this.dossierInscriptionId = dossierInscriptionId;
	}

	/**
	 * [DossierInscriptionBean.dossierEtudiantId] Getter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @return the dossierEtudiantId
	 */
	public String getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [DossierInscriptionBean.dossierEtudiantId] Setter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @param dossierEtudiantId
	 *            the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(String dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	/**
	 * [DossierInscriptionBean.dossierInscriptionAdministrativeDto] Getter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @return the dossierInscriptionAdministrativeDto
	 */
	public DossierInscriptionAdministrativeDto getDossierInscriptionAdministrativeDto() {
		return dossierInscriptionAdministrativeDto;
	}

	/**
	 * [DossierInscriptionBean.dossierInscriptionAdministrativeDto] Setter
	 * 
	 * @author BELDI Jamel on : 7 juil. 2014 11:12:52
	 * @param dossierInscriptionAdministrativeDto
	 *            the dossierInscriptionAdministrativeDto to set
	 */
	public void setDossierInscriptionAdministrativeDto(
			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto) {
		this.dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeDto;
	}

}
