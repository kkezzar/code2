/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.impression.AttestationPrintBean.java] 
 * @author BELDI Jamel on : 18 juin 2014  15:46:36
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.impression;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.commons.business.util.FileUtility;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AttestationFinEtudeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DiplomeFinEtudeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.service.impression.ImpressionService;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;

/**
 * @author BELDI Jamel  on : 18 juin 2014  15:46:36
 */
/**
 * @author BELDI Jamel on : 8 juin 2014 14:52:30
 */
@ManagedBean(name = "printMgrBean")
@ViewScoped
public class PrintMgrBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 18 juin 2014 16:00:48
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(PrintMgrBean.class);
	@ManagedProperty("#{impressionService}")
	private ImpressionService impressionService;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	// @ManagedProperty("#{applicationBean}")
	// private ApplicationBean applicationBean;
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;

	public PrintMgrBean() {
		super();

	}

	/**
	 * [PrintBean.viewLogs] Method
	 * 
	 * @author BELDI Jamel on : 23 juin 2014 15:38:15
	 * @param bytes
	 * @throws Exception
	 */
	public void imprimeLogs(byte[] bytes, String name) throws Exception {
		log.info("viewLogs....");

		try {
			imprimer(bytes, "attestationinscription_" + name, "log");
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * [PrintBean.viewAttestationPDF] Method
	 * 
	 * @author BELDI Jamel on : 23 juin 2014 15:38:21
	 * @param etudiants
	 * @throws Exception
	 */
	public void viewAttestationPDF(

	Collection<DossierInscriptionAdministrativeDto> etudiants, String name) throws Exception {
		log.info("viewAttestationPDF....");
		Calendar d = Calendar.getInstance();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext.getExternalContext().getRealPath(
					"/WEB-INF/classes/jasper/attestation_model.jrxml");
			Map<String, Object> params = new HashMap<String, Object>();
			Locale locale = new Locale("ar");

			facesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			ResourceBundle bundlePrint = facesContext.getApplication().getResourceBundle(facesContext,
					CursusConstants.PRINT_BUNDLE_MSG_NAME);
			params.put(
					"IMG_LOGO",
					facesContext.getExternalContext().getRealPath("images") + "/logo"
							+ sessionBean.getCodeEtablissement() + ".png");
			params.put("IMG_PAPS", facesContext.getExternalContext().getRealPath("images") + "/logopaps.png");
			// applicationBean.getRefEtablissementDto().getLlEtablissementLatin()+"\n"+applicationBean.getRefEtablissementDto().getLlEtablissementArabe()
			params.put("attestationPhrase",
					bundlePrint.getString("print_attestation_phrase_" + sessionBean.getCodeEtablissement()));
			locale = new Locale("fr");
			facesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			bundlePrint = facesContext.getApplication().getResourceBundle(facesContext,
					CursusConstants.PRINT_BUNDLE_MSG_NAME);
			params.put("adresse",
					bundlePrint.getString("print_attestation_adresse_" + sessionBean.getCodeEtablissement()));
			params.put("contact",
					bundlePrint.getString("print_attestation_contact_" + sessionBean.getCodeEtablissement()));
			byte[] bytes = impressionService.viewPDFWithDataSource(RESOURCE_PATH_TO_INPUT_FILE_JASPER, params,
					etudiants);

			imprimer(bytes, name, "pdf");
		} catch (Exception e) {
			throw e;

		}
	}

	/**
	 * [PrintBean.viewDiplomePDF] Method
	 * 
	 * @author BELBRIK Oualidon : 27 octobre 2014 20:38:21
	 * @param diplomes
	 * @throws Exception
	 */
	public void viewDiplomePDF(

	Collection<DiplomeFinEtudeDto> diplomes, String name) throws Exception {
		log.info("viewDiplomePDF....");
		Calendar d = Calendar.getInstance();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext.getExternalContext().getRealPath(
					"/WEB-INF/classes/jasper/diplome.jrxml");
			Map<String, Object> params = new HashMap<String, Object>();
			Locale locale = new Locale("ar");

			facesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			ResourceBundle bundlePrint = facesContext.getApplication().getResourceBundle(facesContext,
					CursusConstants.PRINT_BUNDLE_MSG_NAME);
			params.put(
					"IMG_LOGO",
					facesContext.getExternalContext().getRealPath("images") + "/logo"
							+ sessionBean.getCodeEtablissement() + ".png");
			params.put("IMG_PAPS", facesContext.getExternalContext().getRealPath("images") + "/logopaps.png");
			params.put("attestationPhrase",
					bundlePrint.getString("print_attestation_phrase_" + sessionBean.getCodeEtablissement()));
			locale = new Locale("fr");
			facesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			bundlePrint = facesContext.getApplication().getResourceBundle(facesContext,
					CursusConstants.PRINT_BUNDLE_MSG_NAME);
			params.put("adresse",
					bundlePrint.getString("print_attestation_adresse_" + sessionBean.getCodeEtablissement()));
			params.put("contact",
					bundlePrint.getString("print_attestation_contact_" + sessionBean.getCodeEtablissement()));
			byte[] bytes = impressionService
					.viewPDFWithDataSource(RESOURCE_PATH_TO_INPUT_FILE_JASPER, params, diplomes);

			imprimer(bytes, name, "pdf");
		} catch (Exception e) {
			throw e;

		}
	}

	/**
	 * [PrintBean.viewAttesSucPDF] Method
	 * 
	 * @author BELBRIK Oualidon : 28 octobre 2014 07:04:21
	 * @param diplomes
	 * @throws Exception
	 */
	public void viewAttesSucPDF(

	Collection<AttestationFinEtudeDto> diplomes, String name) throws Exception {
		log.info("viewAttesSucPDF....");
		Calendar d = Calendar.getInstance();

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext.getExternalContext().getRealPath(
					"/WEB-INF/classes/jasper/attestation_fin_etude.jrxml");
			Map<String, Object> params = new HashMap<String, Object>();
			Locale locale = new Locale("ar");

			facesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			ResourceBundle bundlePrint = facesContext.getApplication().getResourceBundle(facesContext,
					CursusConstants.PRINT_BUNDLE_MSG_NAME);
			params.put(
					"IMG_LOGO",
					facesContext.getExternalContext().getRealPath("images") + "/logo"
							+ sessionBean.getCodeEtablissement() + ".png");
			params.put("IMG_PAPS", facesContext.getExternalContext().getRealPath("images") + "/logopaps.png");
			params.put("attestationPhrase",
					bundlePrint.getString("print_attestation_phrase_" + sessionBean.getCodeEtablissement()));
			locale = new Locale("fr");
			facesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			bundlePrint = facesContext.getApplication().getResourceBundle(facesContext,
					CursusConstants.PRINT_BUNDLE_MSG_NAME);
			params.put("adresse",
					bundlePrint.getString("print_attestation_adresse_" + sessionBean.getCodeEtablissement()));
			params.put("contact",
					bundlePrint.getString("print_attestation_contact_" + sessionBean.getCodeEtablissement()));
			byte[] bytes = impressionService
					.viewPDFWithDataSource(RESOURCE_PATH_TO_INPUT_FILE_JASPER, params, diplomes);

			imprimer(bytes, name, "pdf");
		} catch (Exception e) {
			throw e;

		}
	}

	/**
	 * [PrintBean.viewLettreEngagementPDF] Method
	 * 
	 * @author BELDI Jamel on :06 ao�t 2014 15:38:21
	 * @param etudiants
	 * @throws Exception
	 */
	public void viewLettreEngagementPDF(

	Collection<DossierInscriptionAdministrativeDto> etudiants, String name) throws Exception {
		log.info("viewLettreEngagementPDF....");

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext.getExternalContext().getRealPath(
					"/WEB-INF/classes/jasper/engagement_model.jrxml");
			Map<String, Object> params = new HashMap<String, Object>();
			ResourceBundle bundlePrint = facesContext.getApplication().getResourceBundle(facesContext,
					CursusConstants.PRINT_BUNDLE_MSG_NAME);
			params.put(
					"IMG_LOGO",
					facesContext.getExternalContext().getRealPath("images") + "/logo"
							+ sessionBean.getCodeEtablissement() + ".png");
			params.put("IMG_PAPS", facesContext.getExternalContext().getRealPath("images") + "/logopaps.png");

			Locale locale = new Locale("fr");
			facesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			bundlePrint = facesContext.getApplication().getResourceBundle(facesContext,
					CursusConstants.PRINT_BUNDLE_MSG_NAME);
			params.put("adresse",
					bundlePrint.getString("print_attestation_adresse_" + sessionBean.getCodeEtablissement()));
			params.put("contact",
					bundlePrint.getString("print_attestation_contact_" + sessionBean.getCodeEtablissement()));
			byte[] bytes = impressionService.viewPDFWithDataSource(RESOURCE_PATH_TO_INPUT_FILE_JASPER, params,
					etudiants);
			imprimer(bytes, name, "pdf");
		} catch (Exception e) {
			throw e;

		}
	}

	// public void viewBadgePDF(
	//
	// Collection<DossierInscriptionAdministrativeDto> etudiants, String name)
	// throws Exception {
	//
	//
	//
	//
	//
	//
	// Calendar d = Calendar.getInstance();
	//
	// FacesContext facesContext = FacesContext.getCurrentInstance();
	//
	// String RESOURCE_PATH_TO_INPUT_FILE_JASPER =
	// facesContext.getExternalContext().getRealPath("WEB-INF")
	// + "/classes/jasper/badge.jrxml";
	// Map<String, Object> params = new HashMap<String, Object>();
	//
	//
	//
	// String url = dossierInscription.getPhoto();
	// String photo = null;
	//
	// if ( url != null ){
	// String folder_photo = ppmWSClient.getParamConfiguration(
	// DocUtilConstants.PHOTO_URL, DocUtilConstants.CONFIGURATION_DOC)
	// .getValue()
	// + "/";
	// photo = folder_photo + url;
	// } else {
	// ServletContext scontext = (ServletContext)
	// facesContext.getExternalContext().getContext();
	// photo = scontext.getRealPath("/resources/images/boy.png");
	// }
	//
	// params.put("PHOTO_ETUDIANT", photo);
	//
	// params.put("IMG_PAPS",
	// facesContext.getExternalContext().getRealPath("images")+
	// "/logopaps.png");
	// params.put("BADGE_BACKGROUND",
	// facesContext.getExternalContext().getRealPath("images")+
	// "/badge_bg.png");
	//
	// params.put("IMG_LOGO",
	// facesContext.getExternalContext().getRealPath("images")+
	// "/logo"+sessionBean
	// .getCodeEtablissement()+".png");
	//
	// locale = new Locale("fr");
	//
	// bundlePrint = facesContext.getApplication().getResourceBundle(
	// facesContext, CursusConstants.PRINT_BUNDLE_MSG_NAME);
	// // params.put(
	// // "adresse",
	// // bundlePrint.getString("print_attestation_adresse_"
	// // + sessionBean.getCodeEtablissement()));
	// // params.put(
	// // "contact",
	// // bundlePrint.getString("print_attestation_contact_"
	// // + sessionBean.getCodeEtablissement()));
	//
	// byte[] bytes = impressionService.viewPDFWithDataSource(
	// RESOURCE_PATH_TO_INPUT_FILE_JASPER, params, etudiants);
	//
	// printMgrBean.imprimer(bytes, name, "pdf");
	//
	// // printMgrBean.viewAttestationPDF(
	// // etudiants,
	// // "badge_"
	// // + dossierInscription.getNumeroInscription());
	// //}
	// } catch (Exception e) {
	// throw e;
	//
	// }
	// }
	//
	/**
	 * [AttestationPrintBean.impressionService] Getter
	 * 
	 * @author BELDI Jamel on : 18 juin 2014 16:06:45
	 * @return the impressionService
	 */
	public ImpressionService getImpressionService() {
		return impressionService;
	}

	/**
	 * [AttestationPrintBean.impressionService] Setter
	 * 
	 * @author BELDI Jamel on : 18 juin 2014 16:06:45
	 * @param impressionService
	 *            the impressionService to set
	 */
	public void setImpressionService(ImpressionService impressionService) {
		this.impressionService = impressionService;
	}

	/**
	 * [AttestationPrintBean.applicationBean] Getter
	 * 
	 * @author BELDI Jamel on : 18 juin 2014 17:52:13
	 * @return the applicationBean
	 */
	// public ApplicationBean getApplicationBean() {
	// return applicationBean;
	// }

	/**
	 * [AttestationPrintBean.applicationBean] Setter
	 * 
	 * @author BELDI Jamel on : 18 juin 2014 17:52:13
	 * @param applicationBean
	 *            the applicationBean to set
	 */
	// public void setApplicationBean(ApplicationBean applicationBean) {
	// this.applicationBean = applicationBean;
	// }

	/**
	 * [PrintBean.loginBean] Getter
	 * 
	 * @author BELDI Jamel on : 25 juin 2014 11:09:29
	 * @return the loginBean
	 */
	public LoginBean getLoginBean() {
		return loginBean;
	}

	/**
	 * [PrintBean.loginBean] Setter
	 * 
	 * @author BELDI Jamel on : 25 juin 2014 11:09:29
	 * @param loginBean
	 *            the loginBean to set
	 */
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public void imprimer(byte[] bytes, String name, String extension) throws Exception {
		try {
			if (bytes != null) {
				FacesContext context = FacesContext.getCurrentInstance();
				HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
				/***********************************************************************
				 * Pour afficher une bo�te de dialogue pour enregistrer le
				 * fichier sous le nom rapport.pdf
				 **********************************************************************/
				response.setContentType(FileUtility.getContentType(extension));
				response.addHeader("Content-disposition", "attachment;filename=" + name + "." + extension);
				response.setContentLength(bytes.length);
				ServletOutputStream out;
				try {
					out = response.getOutputStream();
					if (out != null) {
						out.write(bytes);
						out.flush();
						out.close();
					}

				} catch (IOException e) {
					throw e;
				}
				context.responseComplete();
			}
		} catch (Exception e) {
			throw e;

		}

	}

	/**
	 * [PrintMgrBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 25 juin 2014 15:06:40
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [PrintMgrBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 25 juin 2014 15:06:40
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

}
