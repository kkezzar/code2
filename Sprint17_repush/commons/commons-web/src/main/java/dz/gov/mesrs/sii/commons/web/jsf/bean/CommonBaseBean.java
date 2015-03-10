package dz.gov.mesrs.sii.commons.web.jsf.bean;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.commons.web.jsf.bean.locator.CommonServiceLocatorBean;
import dz.gov.mesrs.sii.referentiel.business.util.ConfigHolder;

/**
 * Common base bean qui regroupe les informations/services en commun entre les
 * managedbeans.
 * 
 * @author Mounir.MESSAOUDI on : 4 déc. 2014 15:36:10
 */
public abstract class CommonBaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// the logger for this class
	protected final Log logger = LogFactory.getLog(this.getClass());

	protected final FacesContext context = FacesContext.getCurrentInstance();
	@SuppressWarnings("unused")
	protected final HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

	@ManagedProperty(value = "#{configHolder}")
	protected ConfigHolder configHolder;

	@ManagedProperty(value = "#{sessionBean}")
	protected SessionBean sessionBean;

	// the service locator of the business services
	@ManagedProperty(value = "#{commonServiceLocatorBean}")
	protected CommonServiceLocatorBean commonServiceLocator;

	/**
	 * l'id de la page en cours
	 */
	public final String viewId;

	public static ResourceBundle commonResourceBundle;

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 4 déc. 2014 15:36:18
	 */
	public CommonBaseBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		commonResourceBundle = facesContext.getApplication().getResourceBundle(facesContext,
				dz.gov.mesrs.sii.commons.web.util.UtilConstant.COMMON_MESSAGES_FILE_NAME);

		String url = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		viewId = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public CommonServiceLocatorBean getCommonServiceLocator() {
		return commonServiceLocator;
	}

	public void setCommonServiceLocator(CommonServiceLocatorBean commonServiceLocator) {
		this.commonServiceLocator = commonServiceLocator;
	}

	public String getViewId() {
		return viewId;
	}

	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	@SuppressWarnings("rawtypes")
	protected Map getSessionMap() {
		return getExternalContext().getSessionMap();
	}

	@SuppressWarnings("rawtypes")
	protected Map getRequestParameterMap() {
		return getExternalContext().getRequestParameterMap();
	}

	protected Locale getCurrentLocale() {
		return getFacesContext().getViewRoot().getLocale();
	}

	public ConfigHolder getConfigHolder() {
		return configHolder;
	}

	public void setConfigHolder(ConfigHolder configHolder) {
		this.configHolder = configHolder;
	}

}
