package dz.gov.mesrs.sii.commons.web.jsf.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import javax.faces.application.ProjectStage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Cette class permet d'extraire les information general de l'application en
 * cours. Utilisable depuis LoginBean.java, MenuBean.java ...etc
 * 
 * @author Mounir.MESSAOUDI on : 26 nov. 2014 17:57:23
 */

@Configuration
@ManagedBean(name = "applicationBean")
@ApplicationScoped
public class ApplicationBean implements Serializable {

	// the logger for this class
	private static final Log logger = LogFactory.getLog(ApplicationBean.class);

	/**
	 * @author Mounir.MESSAOUDI on : 7 d�c. 2014 10:37:06
	 */
	private static final long serialVersionUID = 1L;

	@Value("${APPLICATION.ID}")
	private Integer appId;

	@Value("${APPLICATION.NAME}")
	private String appName;

	@Value("${APPLICATION.CODE}")
	private String appCode;

	@Value("${APPLICATION.DESCRIPTION}")
	private String appDescription;

	public Integer getAppId() {
		return appId;
	}

	public String getAppName() {
		return appName;
	}

	public String getAppCode() {
		return appCode;
	}

	public String getAppDescription() {
		return appDescription;
	}

	/**
	 * Renvoi le timeZone par defaut
	 * 
	 * @author Mounir.MESSAOUDI on : 14 d�c. 2014 10:47:51
	 * @return le timezone par defaut
	 */
	public final static TimeZone getTimeZone() {
		return TimeZone.getDefault();
	}

	/**
	 * Renvoi le pattern date du locale en cours eg: 11/12/2014
	 * 
	 * @author Mounir.MESSAOUDI on : 14 d�c. 2014 10:47:49
	 * @return le pattern
	 */
	public final static String datePattern() {
		SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat
				.getDateInstance(DateFormat.SHORT, getLocale());
		return dateFormat.toPattern().replace("yy", "yyyy");
	}

	/**
	 * Renvoi le pattern date du locale en cours au format medium (eg. 11 dec
	 * 2014)
	 * 
	 * @author Mounir.MESSAOUDI on : 14 d�c. 2014 11:34:47
	 * @return le pattern
	 */
	public final static String datePatternMedium() {
		SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance(DateFormat.MEDIUM,
				getLocale());
		return dateFormat.toPattern();
	}

	/**
	 * Renvoi le pattern date du locale en cours au format medium (eg. 11
	 * decembre 2014)
	 * 
	 * @author Mounir.MESSAOUDI on : 14 d�c. 2014 11:34:47
	 * @return le pattern
	 */
	public final static String datePatternLong() {
		SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance(DateFormat.LONG, getLocale());
		return dateFormat.toPattern();
	}

	/**
	 * Renvoi le pattern date et time du locale en cours (eg. 11/12/2014 10:00)
	 * 
	 * @author Mounir.MESSAOUDI on : 14 d�c. 2014 11:28:57
	 * @return le pattern
	 */
	public final static String dateTimePattern() {
		SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT,
				SimpleDateFormat.SHORT, getLocale());
		return dateFormat.toPattern().replace("yy", "yyyy");
	}

	/**
	 * Renvoi le pattern date du locale en cours au format medium (eg. 11 dec
	 * 2014 10:00)
	 * 
	 * @author Mounir.MESSAOUDI on : 14 d�c. 2014 11:34:47
	 * @return le pattern
	 */
	public final static String dateTimePatternMedium() {
		SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.SHORT, getLocale());
		return dateFormat.toPattern();
	}

	/**
	 * Renvoi le pattern date et time du locale en cours au format long ( eg. 11
	 * decembre 2014 10:00)
	 * 
	 * @author Mounir.MESSAOUDI on : 14 d�c. 2014 11:34:47
	 * @return le pattern
	 */
	public final static String dateTimePatternLong() {
		SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.SHORT, getLocale());
		return dateFormat.toPattern();
	}

	/**
	 * Renvoi le locale en cours
	 * 
	 * @author Mounir.MESSAOUDI on : 14 d�c. 2014 14:17:57
	 * @return le locale
	 */
	private final static Locale getLocale() {
		// Locale locale =
		// FacesContext.getCurrentInstance().getViewRoot().getLocale();

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		return request.getLocale();
	}

	/**
	 * Verifie si l'app est en mode production
	 * 
	 * @author Mounir.MESSAOUDI on : 14 d�c. 2014 14:17:57
	 * @return true si l'app est en mode production, false sinon
	 */
	public static boolean isProduction() {
		ProjectStage stage = ProjectStage.Development;
		String stageValue = null;
		try {
			InitialContext ctx = new InitialContext();
			// stageValue = (String)
			// ctx.lookup(ProjectStage.PROJECT_STAGE_JNDI_NAME);
			stageValue = FacesContext.getCurrentInstance().getApplication().getProjectStage().toString();
			stage = ProjectStage.valueOf(stageValue);
			// FacesContext facesContext = FacesContext.getCurrentInstance();
			// return facesContext.isProjectStage(ProjectStage.Production);
		} catch (Exception e) {
			logger.error("Could not lookup JNDI object with name 'javax.faces.PROJECT_STAGE'. Using default 'production'");
		}
		return ProjectStage.Production == stage;
	}

	public boolean getProduction() {
		return isProduction();
	}
}
