package dz.gov.mesrs.sii.commons.web.jsf.bean;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Handler pour les erreurs et les exceptions
 * 
 * @author Mounir.MESSAOUDI
 * 
 */
@ManagedBean(name = "errorHandlerBean")
@RequestScoped
public class ErrorHandlerBean {

	@ManagedProperty(value = "#{applicationBean}")
	protected ApplicationBean applicationBean;

	public ErrorHandlerBean() {
		// TODO Auto-generated constructor stub
	}

	public String getStatusCode() {
		String val = String.valueOf((Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.status_code"));
		return val;
	}

	public String getMessage() {
		String val = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.message");
		return val;
	}

	public String getExceptionType() {
		String val = FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.exception_type").toString();
		return val;
	}

	public String getException() {
		String val = (String) ((Exception) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.exception")).toString();
		return val;
	}

	public static String printStackTrace(Throwable exception) {
		if (exception == null) {
			return null;
		}

		StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter, true));
		return stringWriter.toString();
	}

	public String getRequestURI() {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.request_uri");
	}

	public String getServletName() {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.servlet_name");
	}

	public void throwNullPointerException() throws Exception {
		throw new Exception("A NullPointerException!");
	}

	public void throwWrappedIllegalStateException() {
		Throwable t = new IllegalStateException("A wrapped IllegalStateException!");
		throw new FacesException(t);
	}

	public void throwViewExpiredException() {
		throw new ViewExpiredException("A ViewExpiredException!", FacesContext.getCurrentInstance().getViewRoot()
				.getViewId());
	}

	public void setApplicationBean(ApplicationBean applicationBean) {
		this.applicationBean = applicationBean;
	}

	public String gotoHomepageAction() {
		return "pages/index.xhtml?facesRedirect=true";
	}
}
