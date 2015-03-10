/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.authentification.LoginBean.java] 
 * @author BELDI Jamel on : 1 mars 2014  10:57:20
 */
package dz.gov.mesrs.sii.commons.web.jsf.bean;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import dz.gov.mesrs.sii.commons.web.util.UtilConstant;

/**
 * Cette class assure la connexion a l'application
 * 
 * @author Mounir.MESSAOUDI on : 7 déc. 2014 15:53:04
 */
@ManagedBean(name = "loginBean", eager = true)
@SessionScoped
public class LoginBean implements Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 8 déc. 2014 15:35:29
	 */
	private static final long serialVersionUID = 1L;
	// the logger for this class
	protected final Log logger = LogFactory.getLog(this.getClass());

	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager = null;

	private final ResourceBundle bundle;
	private final ResourceBundle authentificationBundle;

	@NotEmpty
	@Size(min = 1, max = 25)
	private String userName = null;

	@NotEmpty
	@Size(min = 1, max = 25)
	private String password = null;

	private String rememberMe = null;

	/**
	 * [LoginBean.LoginBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 16 juin 2014 17:45:13
	 */
	public LoginBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext, UtilConstant.COMMON_BUNDLE_MSG_NAME);
		authentificationBundle = facesContext.getApplication().getResourceBundle(facesContext,
				UtilConstant.AUTHENTIFICATION_BUNDLE_MSG_NAME);
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 7 déc. 2014 15:53:04
	 */
	public String login() {
		try {
			Authentication request = new UsernamePasswordAuthenticationToken(this.getUserName(), getPassword());
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			logger.info("Login Success! ..");
			return "success";
		} catch (BadCredentialsException ex) {
			// Failed compte not found on BD
			FacesMessage message = new FacesMessage(authentificationBundle.getString("authentification_loginFailed")
					+ ": "
					+ authentificationBundle.getString("authentification_loginFailed_user_or_password_incorrect"));
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (LockedException ex) {
			// Failed compte not found on BD
			FacesMessage message = new FacesMessage(authentificationBundle.getString("authentification_loginFailed")
					+ ": "
					+ authentificationBundle.getString("authentification_loginFailed_user_or_password_incorrect"));
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary(bundle.getString("error_echec") + ": " + bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return "error";
	}

	public String logout() {
		// System.out.println("LoginBean.logout()....");
		SecurityContextHolder.getContext().setAuthentication(null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		return "login";
	}

	public String doLogout() {
		SecurityContextHolder.clearContext();
		/**
		 * Delete Cookies
		 */
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
		Cookie cookie = new Cookie("SPRING_SECURITY_REMEMBER_ME_COOKIE", null);
		cookie.setMaxAge(0);
		cookie.setPath(httpServletRequest.getContextPath().length() > 0 ? httpServletRequest.getContextPath() : "/");
		httpServletResponse.addCookie(cookie);
		return "loggout";

	}

	/**
	 * [LoginBean.userName] Getter
	 * 
	 * @author BELDI Jamel on : 16 juin 2014 17:47:29
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * [LoginBean.userName] Setter
	 * 
	 * @author BELDI Jamel on : 16 juin 2014 17:47:29
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * [LoginBean.password] Getter
	 * 
	 * @author BELDI Jamel on : 16 juin 2014 17:47:29
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * [LoginBean.password] Setter
	 * 
	 * @author BELDI Jamel on : 16 juin 2014 17:47:29
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}
}