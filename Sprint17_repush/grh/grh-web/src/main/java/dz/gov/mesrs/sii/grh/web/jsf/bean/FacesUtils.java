package dz.gov.mesrs.sii.grh.web.jsf.bean;

import java.util.ResourceBundle;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import dz.gov.mesrs.sii.commons.web.jsf.bean.SessionBean;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 27-10-2014
 * @description Classe utilitaire de JSF
 */
public class FacesUtils {

	/**
	 * Get servlet context.
	 * 
	 * @return the servlet context
	 */
	public static ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}

	public static ExternalContext getExternalContext() {
		FacesContext fc = FacesContext.getCurrentInstance();
		return fc.getExternalContext();
	}

	public static HttpSession getHttpSession(boolean create) {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(create);
	}

	/**
	 * Get managed bean based on the bean name.
	 * 
	 * @param beanName
	 *            the bean name
	 * @return the managed bean associated with the bean name
	 */
	public static Object getManagedBean(String beanName) {

		return getValueBinding(getJsfEl(beanName)).getValue(FacesContext.getCurrentInstance());
	}

	/**
	 * Remove the managed bean based on the bean name.
	 * 
	 * @param beanName
	 *            the bean name of the managed bean to be removed
	 */
	public static void resetManagedBean(String beanName) {
		getValueBinding(getJsfEl(beanName)).setValue(FacesContext.getCurrentInstance(), null);
	}

	/**
	 * Store the managed bean inside the session scope.
	 * 
	 * @param beanName
	 *            the name of the managed bean to be stored
	 * @param managedBean
	 *            the managed bean to be stored
	 */
	public static void setManagedBeanInSession(String beanName, Object managedBean) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(beanName, managedBean);
	}

	/**
	 * Get parameter value from request scope.
	 * 
	 * @param name
	 *            the name of the parameter
	 * @return the parameter value
	 */
	public static String getRequestParameter(String name) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
	}

	private static Application getApplication() {
		ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder
				.getFactory(FactoryFinder.APPLICATION_FACTORY);
		return appFactory.getApplication();
	}

	private static ValueBinding getValueBinding(String el) {
		return getApplication().createValueBinding(el);
	}

	/**
	 * Add information message.
	 * 
	 * @param msg
	 *            the information message
	 */
	public static void addInfoMessage(String msg) {
		addInfoMessage(null, msg);
	}

	/**
	 * Add information message to a specific client.
	 * 
	 * @param clientId
	 *            the client id
	 * @param msg
	 *            the information message
	 */
	public static void addInfoMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	private static String getJsfEl(String value) {
		return "#{" + value + "}";
	}

	public static String getBundleValue(String resource, String prop) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = ResourceBundle.getBundle(resource, context.getViewRoot().getLocale());
		return bundle.getString(prop);
	}

	/**
	 * 
	 * @return sessionBean
	 */
	public static SessionBean getSessionBean() {
		return (SessionBean) getManagedBean("sessionBean");
	}

	/**
	 * return ConstantBean
	 */
	public static ConstantBean getConstantBean() {
		return (ConstantBean) getManagedBean("constantBean");
	}

	/**
	 * // /** // // * @description init input value after validation with error
	 * message // * @param mapIdInputValue //
	 */
	// public static void initJsfInput(Map<String, Object> mapIdInputValue){
	// if(mapIdInputValue!=null && !mapIdInputValue.isEmpty()){
	// Iterator<String> it = mapIdInputValue.keySet().iterator();
	// while (it.hasNext()) {
	// String idInput = it.next();
	//
	// if(FacesContext.getCurrentInstance()!=null
	// && FacesContext.getCurrentInstance().getViewRoot()!=null
	// &&
	// FacesContext.getCurrentInstance().getViewRoot().findComponent(idInput)!=null
	// && FacesContext.getCurrentInstance().getViewRoot().findComponent(idInput)
	// instanceof UIInput){
	// UIInput input = (UIInput)
	// FacesContext.getCurrentInstance().getViewRoot().findComponent(idInput);
	// if(input!=null){
	// input.setValue(mapIdInputValue.get(idInput));
	// input.setSubmittedValue(mapIdInputValue.get(idInput));
	// input.setLocalValueSet(false);
	// input.setValid(true);
	// }
	// }
	// }
	// }
	// }
}