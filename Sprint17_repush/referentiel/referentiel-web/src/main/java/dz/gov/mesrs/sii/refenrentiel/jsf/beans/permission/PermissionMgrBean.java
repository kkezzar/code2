/**
 * 
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.permission;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FlowEvent;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefPermissionService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author obelbrik
 * 
 */
@ManagedBean(name = "permissionMgrBean")
@RequestScoped
public class PermissionMgrBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String searchInput;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	@ManagedProperty(value = "#{refPermissionServiceImpl}")
	private RefPermissionService refPermissionServiceImpl;
	private static final Log log = LogFactory.getLog(PermissionMgrBean.class);
	private RefPermissionDto refPermissionDto;
	private RefPermissionDto searchDto;
	private boolean updateMode;
	private boolean readMode;
	@ManagedProperty(value = "#{permissionBckBean}")
	private PermissionBckBean permissionBckBean;

	/**
	 * [PlageAdresseMgrBean.PlageAdresseMgrBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 04 mars. 2014 09:19:25
	 */
	public PermissionMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				PermissionMgrBean.MESSAGES_FILE_NAME);
		
		searchDto = new RefPermissionDto();
	}

	/**
	 * 
	 * 
	 * @author BELBRIK Oualid on : 04 mars. 2014 09:19:43
	 * @return the searchInput
	 */
	public String getSearchInput() {

		return searchInput;
	}

	
	/**
	 * @return the refPermissionDto
	 */
	public RefPermissionDto getRefPermissionDto() {
		if (refPermissionDto == null) {
			if (permissionBckBean.getRefPermissionDto() != null) {
				refPermissionDto = permissionBckBean.getRefPermissionDto();
			} else {
				refPermissionDto = new RefPermissionDto();
			}
		}
		return refPermissionDto;
	}

	/**
	 * @return the updateMode
	 */
	public boolean isUpdateMode() {
		if (updateMode == false) {
			if (permissionBckBean.getRefPermissionDto() != null) {
				updateMode =true;
			}
		}
		return updateMode;
	}

	/**
	 * @param updateMode
	 *            the updateMode to set
	 */
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}

	/**
	 * @return the readMode
	 */
	public boolean isReadMode() {
		return readMode;
	}

	/**
	 * @param readMode
	 *            the readMode to set
	 */
	public void setReadMode(boolean readMode) {
		this.readMode = readMode;
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String toDetails() {
		setReadMode(true);
		setUpdateMode(false);
		permissionBckBean.setListRefPermissionDto(null);
		return "toDetails";
	}

	/**
	 * navigate to new permission page
	 * 
	 * @return outcome
	 */
	public String toNew() {
		setReadMode(true);
		setUpdateMode(false);
		permissionBckBean.setListRefPermissionDto(null);
		permissionBckBean.setRefPermissionDto(null);
		return "toNew";
	}

	/**
	 * 
	 * navigate to edit PlageAdresse page
	 * 
	 * @return
	 */
	public String toEdit() {
		setReadMode(false);
		setUpdateMode(true);
		permissionBckBean.setListRefPermissionDto(null);
		return "toEdit";
	}

	/**
	 * 
	 * the search input
	 * 
	 * @author obelbrik on : 04 mars. 2014 11:18:14
	 */
	public void findGeneric() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefPermissionDto> result = refPermissionServiceImpl
					.findGeneric(this.searchInput);
			if (result == null || result.isEmpty()) {
				permissionBckBean.setListRefPermissionDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				permissionBckBean.setListRefPermissionDto(result);

			}
		} catch (Exception e) {
			permissionBckBean.setListRefPermissionDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	

	/**
	 * 
	 * @param event 
	 * @return
	 */
	public String toSearch() {
		permissionBckBean = new PermissionBckBean();
		return "toSearchPermission";
	}

	/**
     * 
	 * @author obelbrik on : 04 mars. 2014 10:38:21
	 * @param event
	 * @return the current step
	 */
	public String onFlowProcess(FlowEvent event) {
		log.info("Current wizard step:" + event.getOldStep());
		log.info("Next step:" + event.getNewStep());
		if(event.getOldStep()!=null && event.getOldStep().equals(bundle.getString("common_infos"))){
			if(isUpdateMode()){
				return event.getNewStep();
			}
			else{				
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+bundle.getString("warn_info_sauvegarde_data"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return event.getOldStep();
			}
		}else{
			return event.getNewStep();
		}

	}

	public void toSave() {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("Mgr Bean toSave");
			if (updateMode) {
				refPermissionServiceImpl.update(refPermissionDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				refPermissionServiceImpl.save(refPermissionDto);
				setUpdateMode(true);
				permissionBckBean.setRefPermissionDto(refPermissionDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
	}

	/**
	 * [G
	 * @author BELBRIK Oualid on : 04 mars. 2014  09:42:23
	 * @return the refPermissionServiceImpl
	 */
	public RefPermissionService getRefPermissionServiceImpl() {
		return refPermissionServiceImpl;
	}

	/**
	 * [
	 * @author BELBRIK Oualid on : 04 mars. 2014  09:42:23
	 * @param refPermissionServiceImpl the refPermissionServiceImpl to set
	 */
	public void setRefPermissionServiceImpl(
			RefPermissionService refPermissionServiceImpl) {
		this.refPermissionServiceImpl = refPermissionServiceImpl;
	}

	/**
	 * [permissionMgrBean.searchDto] Getter 
	 * @author BELBRIK Oualid on : 04 mars. 2014  09:42:23
	 * @return the searchDto
	 */
	public RefPermissionDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [permissionMgrBean.searchDto] Setter 
	 * @author BELBRIK Oualid on : 04 mars. 2014  09:42:23
	 * @param searchDto the searchDto to set
	 */
	public void setSearchDto(RefPermissionDto searchDto) {
		this.searchDto = searchDto;
	}



	/**
	 * [permissionMgrBean.permissionBckBean] Getter 
	 * @author BELBRIK Oualid on : 2 mars 2014  09:40:48
	 * @return the PermissionBckBean
	 */
	public PermissionBckBean getPermissionBckBean() {
		return permissionBckBean;
	}

	/**
	 * 
	 * @author BELBRIK Oualid on : 04 mars. 2014  09:42:23
	 * 
	 */
	public void setRefPermissionDto(RefPermissionDto refPermissionDto) {
		this.refPermissionDto = refPermissionDto;
	}

	/**
	 * [permissionMgrBean.permissionBckBean] Setter 
	 * @author BELBRIK Oualid on : 25 févr. 2014  16:42:34
	 * @param permissionBckBean the permissionBckBean to set
	 */
	public void setPermissionBckBean(PermissionBckBean permissionBckBean) {
		this.permissionBckBean = permissionBckBean;
	}

	/**
	 * 
	 * 
	 * @author BELBRIK Oualid on : 04 mars. 2014 09:20:24
	 * @param searchInput
	 */
	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}	
	
	/**
	 * 
	 * 
	 * @author BELBRIK Oualid on : 26 févr. 2014 18:35:24
	 * @param delete horaire d'acces
	 */
	
	public void toRemove(RefPermissionDto selectedRefPermissionDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedRefPermissionDto != null) && (selectedRefPermissionDto.getId()!=0)) {
			try {
				log.info("deleting permission id = "+selectedRefPermissionDto.getId());			
			    refPermissionServiceImpl.delete(selectedRefPermissionDto);
			  msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_suppression"));
				FacesContext.getCurrentInstance().addMessage(null, msg);	
				}
			catch(Exception e) {
				log.info(e.getMessage());	
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
							}
		}
		
	}	
	
	
}
