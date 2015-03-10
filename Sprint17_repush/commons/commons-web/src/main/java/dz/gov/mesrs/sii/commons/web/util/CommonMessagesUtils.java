package dz.gov.mesrs.sii.commons.web.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
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
		face.setSummary(bundleCommon.getString("msg_success") + ": "
				+ bundleCommon.getString("msg_success_enregistrement"));
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
		face.setSummary(bundleCommon.getString("msg_success") + ": "
				+ bundleCommon.getString("msg_success_modification"));
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
		face.setSummary(bundleCommon.getString("msg_success") + ": "
				+ bundleCommon.getString("msg_success_suppression"));
		FacesContext.getCurrentInstance().addMessage(null, face);
	}

	/**
	 * [Utility.showSuccessMessage] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 09:26:03
	 * @param msg
	 */
	public static void showErrorMessage(String msg) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
				COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_ERROR);
		face.setSummary(bundleCommon.getString("error_echec") + ": " + msg);
		FacesContext.getCurrentInstance().addMessage(null, face);
	}
	
	
	/**
	 * [CommonMessagesUtils.showSuccessMessage] Method 
	 * @author MAKERRI Sofiane  on : 16 f�vr. 2015  15:07:09
	 * @param msg
	 */
	public static void showSuccessMessage(String msg) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
				COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_INFO);
		face.setSummary(bundleCommon.getString("msg_success") + ": " + msg);
		FacesContext.getCurrentInstance().addMessage(null, face);
	}
	
	
	/**
	 * [CommonMessagesUtils.showSuccessSaveOrUpdateMessage] Method 
	 * @author MAKERRI Sofiane  on : 5 janv. 2015  11:50:49
	 * @param save
	 */
	public static void showSuccessSaveOrUpdateMessage(boolean save) {
		if(save) {
			showSuccessSaveMessage();
		} else {
			showSuccessUpdateMessage();
		}
	}
	
	
	/**
	 * [CommonMessagesUtils.showWarningMessage] Method 
	 * @author MAKERRI Sofiane  on : 5 janv. 2015  15:16:24
	 * @param msg
	 */
	public static void showWarningMessage(String msg) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication()
				.getResourceBundle(facesContext,
						COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_WARN);
		face.setSummary(bundleCommon.getString("warn_info") + ": " + msg);
		FacesContext.getCurrentInstance().addMessage(null, face);
	}
	

	public static void showConstraintViolationErrorMessage(String msg) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
				COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_WARN);
		face.setSummary(bundleCommon.getString("error_constraint_violation") + ": " + msg);
		FacesContext.getCurrentInstance().addMessage(null, face);
	}

	/**
	 * Message de success de soumission a la validation
	 * 
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 09:51:33
	 */
	public static void showSuccessSendToValidationMessage() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
				COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_INFO);
		face.setSummary(bundleCommon.getString("msg_success") + ": "
				+ bundleCommon.getString("msg_success_soumission_validation"));
		FacesContext.getCurrentInstance().addMessage(null, face);
	}

	/**
	 * Message de success de validation
	 * 
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 09:51:33
	 */
	public static void showSuccessValidationMessage() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
				COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_INFO);
		face.setSummary(bundleCommon.getString("msg_success") + ": " + bundleCommon.getString("msg_success_validation"));
		FacesContext.getCurrentInstance().addMessage(null, face);
	}

	/**
	 * Message de success de cloture
	 * 
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 09:51:21
	 */
	public static void showSuccessClosingMessage() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
				COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_INFO);
		face.setSummary(bundleCommon.getString("msg_success") + ": " + bundleCommon.getString("msg_success_cloture"));
		FacesContext.getCurrentInstance().addMessage(null, face);
	}
	
	/**
	 * [MessageUtil.addInfoMessage] Method 
	 * @author rlaib  on : 13 fev. 2014  16:47:32
	 * @param str
	 */
	public static void addInfoMessage(String str) {
		
				FacesContext context = FacesContext.getCurrentInstance();
				ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
				String message = bundle.getString(str);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
	}

	/**
	 * [MessageUtil.addInfoMessage] Method 
	 * @author rlaib  on : 13 fev. 2014  16:47:44
	 * @param summary
	 * @param detail
	 */
	public static void addInfoMessage(String summary, String detail) {
				
				FacesContext context = FacesContext.getCurrentInstance();
				ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
				String message = bundle.getString(summary);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, detail));
	}

    /**
     * [MessageUtil.addInfoMessage] Method 
     * @author rlaib  on : 13 fev. 2014  16:47:50
     * @param str
     * @param args
     */
    public static void addInfoMessage(String str, Object... args) {
        		
    			FacesContext context = FacesContext.getCurrentInstance();
    			ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
    			String message = bundle.getString(str);
    			if (args != null) {
    					
    					message = MessageFormat.format(message, args);
    			}
    			
    			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
    }

    /**
     * [MessageUtil.addInfoMessageWithoutKey] Method 
     * @author rlaib  on : 13 fev. 2014  16:48:00
     * @param summary
     * @param detail
     */
    public static void addInfoMessageWithoutKey(String summary, String detail) {
    			FacesContext context = FacesContext.getCurrentInstance();
    			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    			
    }
    
    /**
     * [MessageUtil.addWarnMessageWithoutKey] Method 
     * @author rlaib  on : 13 avr. 2014  16:48:29
     * @param summary
     * @param detail
     */
    public static void addWarnMessageWithoutKey(String summary, String detail) {
    		
    			FacesContext context = FacesContext.getCurrentInstance();
    			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
    }
    
    /**
     * [MessageUtil.addErrorMessageWithoutKey] Method 
     * @author rlaib  on : 13 avr. 2014  16:48:35
     * @param summary
     * @param detail
     */
    public static void addErrorMessageWithoutKey(String summary, String detail) {
    			
    			FacesContext context = FacesContext.getCurrentInstance();
    			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
    }
    
    /**
     * [MessageUtil.getStringValueFromBundleResource] Method 
     * @author rlaib  on : 13 avr. 2014  16:48:42
     * @param bundleName
     * @param keyValue
     * @return
     */
    public static String getStringValueFromBundleResource(String bundleName, String keyValue)  {
    	
    			FacesContext context = FacesContext.getCurrentInstance();
    			ResourceBundle bundle = context.getApplication().getResourceBundle(context, bundleName);
    			String message = bundle.getString(keyValue);
    	
    			return message;
    }
    
    /**
     * [MessageUtil.getStringValueFromBundleResourceWithParams] Method 
     * @author rlaib  on : 04 avr. 2014  10:07:41
     * @param bundleName
     * @param key
     * @param params
     * @param locale
     * @return
     */
    public static String getStringValueFromBundleResourceWithParams(String bundleName, String key, Object[] params, String language) {

        		String result;
        		
        		// Getting the current Locale for I18N
                FacesContext context = FacesContext.getCurrentInstance();

                Locale locale = new Locale(language);
                context.getViewRoot().setLocale(locale);
                
                // Loading .properties file Bundle
//            	ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
            	
            	ResourceBundle bundle = context.getApplication().getResourceBundle(context, bundleName);
        		try {
        					result = bundle.getString(key);
        						
        		} catch (MissingResourceException e) {
        				
        					result = " La clé [" + key + "] introuvable dans le fichier ressources : " + bundleName;
        		}

        		if (params != null) {
        				
        					// Formatting value with params if there are !
        					MessageFormat mf = new MessageFormat(result, locale);
        					result = mf.format(params, new StringBuffer(), null).toString();
        		}
        		return result;
    }
    
    public static Locale getLocale(FacesContext context) {

        Locale locale = null;
        UIViewRoot viewRoot = context.getViewRoot();

        if (viewRoot != null) 
            		locale = viewRoot.getLocale();

        if (locale == null) 
            		locale = Locale.getDefault();

        return locale;

    } 

}
