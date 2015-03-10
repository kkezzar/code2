/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.ActionHistBean.java] 
 * @author BELDI Jamel on : 5 mai 2014  16:15:04
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.TacheActionDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.TacheActionService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;

/**
 * @author BELDI Jamel  on : 5 mai 2014  16:15:04
 */
@ManagedBean(name = "actionHistBean")
@RequestScoped
public class ActionHistBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 5 mai 2014  16:16:09
	 */
	private static final long serialVersionUID = 1L;
	private List<TacheActionDto> listTacheActions;
	private ResourceBundle bundleDmde;
	private ResourceBundle bundleCommon;
	@ManagedProperty(value = "#{tacheActionService}")
	private TacheActionService tacheActionService;
	@ManagedProperty("#{param.entityCode}")
	private String entityCode;
	@ManagedProperty("#{param.entityInstanceId}")
	private String entityInstanceId;
	private int exception;
	/**
	 * [ActionHistBean.ActionHistBean()] Constructor
	 * @author BELDI Jamel  on : 5 mai 2014  16:15:04	
	 */
	public ActionHistBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();		
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, OfConstants.COMMON_MESSAGES_FILE_NAME);		
		listTacheActions = new ArrayList<>();
	}
	@PostConstruct
	public void init(){
		if (entityCode != null && entityInstanceId != null && !entityCode.trim().equals("") && !entityInstanceId.trim().equals("")
				&& !entityCode.trim().equals("null") && !entityInstanceId.trim().equals("null") )
		loadHistorique();
	}
	
	/**
	 * [ActionHistBean.loadHistorique] Method load Historique of Action
	 * @author BELDI Jamel  on : 6 mai 2014  12:08:57
	 */
	private void loadHistorique() {
		FacesMessage msg = new FacesMessage();
		try{
			if(listTacheActions==null || listTacheActions.isEmpty()){
				listTacheActions= tacheActionService.findByEntity(entityCode, Integer.valueOf(entityInstanceId));
			}
		} catch (Exception e) {
			exception = 1;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + exception
					+ ": " + bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
		
	}

	/**
	 * [ActionHistBean.listTacheActions] Getter 
	 * @author BELDI Jamel on : 5 mai 2014  17:20:52
	 * @return the listTacheActions
	 */
	public List<TacheActionDto> getListTacheActions() {
		return listTacheActions;
	}
	/**
	 * [ActionHistBean.listTacheActions] Setter 
	 * @author BELDI Jamel on : 5 mai 2014  17:20:52
	 * @param listTacheActions the listTacheActions to set
	 */
	public void setListTacheActions(List<TacheActionDto> listTacheActions) {
		this.listTacheActions = listTacheActions;
	}
	/**
	 * [ActionHistBean.tacheActionService] Getter 
	 * @author BELDI Jamel on : 6 mai 2014  10:36:42
	 * @return the tacheActionService
	 */
	public TacheActionService getTacheActionService() {
		return tacheActionService;
	}
	/**
	 * [ActionHistBean.tacheActionService] Setter 
	 * @author BELDI Jamel on : 6 mai 2014  10:36:42
	 * @param tacheActionService the tacheActionService to set
	 */
	public void setTacheActionService(TacheActionService tacheActionService) {
		this.tacheActionService = tacheActionService;
	}

	/**
	 * [ActionHistBean.entityCode] Getter 
	 * @author BELDI Jamel on : 6 mai 2014  10:40:48
	 * @return the entityCode
	 */
	public String getEntityCode() {
		return entityCode;
	}

	/**
	 * [ActionHistBean.entityCode] Setter 
	 * @author BELDI Jamel on : 6 mai 2014  10:40:48
	 * @param entityCode the entityCode to set
	 */
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	/**
	 * [ActionHistBean.entityInstanceId] Getter 
	 * @author BELDI Jamel on : 6 mai 2014  10:40:48
	 * @return the entityInstanceId
	 */
	public String getEntityInstanceId() {
		return entityInstanceId;
	}

	/**
	 * [ActionHistBean.entityInstanceId] Setter 
	 * @author BELDI Jamel on : 6 mai 2014  10:40:48
	 * @param entityInstanceId the entityInstanceId to set
	 */
	public void setEntityInstanceId(String entityInstanceId) {
		this.entityInstanceId = entityInstanceId;
	}

	/**
	 * [ActionHistBean.exception] Getter 
	 * @author BELDI Jamel on : 6 mai 2014  10:43:01
	 * @return the exception
	 */
	public int getException() {
		return exception;
	}

	/**
	 * [ActionHistBean.exception] Setter 
	 * @author BELDI Jamel on : 6 mai 2014  10:43:01
	 * @param exception the exception to set
	 */
	public void setException(int exception) {
		this.exception = exception;
	}
	
	
	
	

}
