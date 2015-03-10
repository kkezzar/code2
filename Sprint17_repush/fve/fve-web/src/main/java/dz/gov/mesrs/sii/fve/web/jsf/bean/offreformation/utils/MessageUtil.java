package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;


/**
 * @author rlaib  on : 13 fev. 2014  16:47:18
 */
public class MessageUtil {

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
