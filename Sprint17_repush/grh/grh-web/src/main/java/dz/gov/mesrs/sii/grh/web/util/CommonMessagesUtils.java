package dz.gov.mesrs.sii.grh.web.util;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class CommonMessagesUtils {
	public final static String COMMON_BUNDLE_MSG_NAME = "commonmsgs";

	/**
	 * [GroupeAffectationBean.showSuccessMessage] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 août 2014 09:26:16
	 * @param msg
	 */
	public static void showSuccessSaveMessage() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
				COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_INFO);
		face.setSummary(bundleCommon.getString("msg_success"));
		face.setDetail(bundleCommon.getString("msg_success_enregistrement"));
		FacesContext.getCurrentInstance().addMessage(null, face);
	}

	/**
	 * [Utility.showSuccessMessage] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 août 2014 09:07:37
	 */
	public static void showSuccessUpdateMessage() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
				COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_INFO);
		face.setSummary(bundleCommon.getString("msg_success"));
		face.setDetail(bundleCommon.getString("msg_success_modification"));
		FacesContext.getCurrentInstance().addMessage(null, face);
	}

	/**
	 * [Utility.showSuccessMessage] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 août 2014 10:19:05
	 */
	public static void showSuccessDeleteMessage() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
				COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_INFO);
		face.setSummary(bundleCommon.getString("msg_success"));
		face.setDetail(bundleCommon.getString("msg_success_suppression"));
		FacesContext.getCurrentInstance().addMessage(null, face);
	}
}
