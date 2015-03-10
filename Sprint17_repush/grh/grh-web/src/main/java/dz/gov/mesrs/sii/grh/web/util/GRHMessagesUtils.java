package dz.gov.mesrs.sii.grh.web.util;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class GRHMessagesUtils {
	public final static String GRH_BUNDLE_MSG_NAME = "grhmsgs";

	public static void showErrorMessage(String msgKey) {
		showErrorMessage(GRH_BUNDLE_MSG_NAME, msgKey);
	}

	public static void showErrorMessage(String messageBundle, String msgKey) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleGRH = facesContext.getApplication().getResourceBundle(facesContext, messageBundle);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_ERROR);
		face.setSummary("Erreur");
		face.setDetail(bundleGRH.getString(msgKey));
		FacesContext.getCurrentInstance().addMessage(null, face);
	}

	public static void showWarningMessage(String messageBundle, String msgKey) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleGRH = facesContext.getApplication().getResourceBundle(facesContext, messageBundle);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_WARN);
		face.setSummary("Attention");
		face.setDetail(bundleGRH.getString(msgKey));
		FacesContext.getCurrentInstance().addMessage(null, face);
		
	}
	

}
