
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.filierelmd;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FlowEvent;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;

/**
 * @author obelbrik
 * 
 */
@ManagedBean(name = "filierelmdMgrBean")
@RequestScoped
public class FilierelmdMgrBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String searchInput;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	@ManagedProperty(value = "#{refFiliereLmdServiceImpl}")
	private RefFiliereLmdService refFiliereLmdServiceImpl;
	private static final Log log = LogFactory.getLog(FilierelmdMgrBean.class);
	private RefFiliereLmdDto refFiliereLmdDto;
	private RefFiliereLmdDto searchDto;
	private boolean updateMode;
	private boolean readMode;
	@ManagedProperty(value = "#{filierelmdBckBean}")
	private FilierelmdBckBean filierelmdBckBean;
	private boolean disableOnglet;
    @ManagedProperty("#{flash}")
    private Flash flash;

	private List<RefFiliereLmdDto> listRefFiliereLmdDto;
   

    
    /**
	 * 
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:19:25
	 */
    
	public FilierelmdMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				FilierelmdMgrBean.MESSAGES_FILE_NAME);
		searchDto = new RefFiliereLmdDto();
		disableOnglet =  true;
		
	}


	public String getSearchInput() {

		return searchInput;
	}


	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	
	public RefFiliereLmdDto getRefFiliereLmdDto() {
		if (this.refFiliereLmdDto == null) {
			if (filierelmdBckBean.getRefFiliereLmdDto() != null) {
				setRefFiliereLmdDto(filierelmdBckBean.getRefFiliereLmdDto());
			} else {
				refFiliereLmdDto = new RefFiliereLmdDto();
			}
		}
		return refFiliereLmdDto;
	}


	public boolean isUpdateMode() {
		if (updateMode == false) {
			if (filierelmdBckBean.getRefFiliereLmdDto() != null) {
				updateMode = true;
			}
		}
		return updateMode;
	}


	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}

	
	public boolean isReadMode() {
		return readMode;
	}

	
	public void setReadMode(boolean readMode) {
		this.readMode = readMode;
	}

	
	/**
	 * [FiliereLmdMgrBean.disableOnglet] Getter 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014  19:57:19
	 * @return the disableOnglet
	 */
	public boolean isDisableOnglet() {
		return disableOnglet;
	}


	/**
	 * [FiliereLmdMgrBean.disableOnglet] Setter 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014  19:57:19
	 * @param disableOnglet the disableOnglet to set
	 */
	public void setDisableOnglet(boolean disableOnglet) {
		this.disableOnglet = disableOnglet;
	}


	public String toDetails() {
		filierelmdBckBean.setListRefFiliereLmdDto(null);
		return "toDetails";
	}


	public String toNew() {
		filierelmdBckBean.setListRefFiliereLmdDto(null);
		filierelmdBckBean.setRefFiliereLmdDto(null);
		return "toNew";
	}


	public String toEdit() {
		setDisableOnglet(false);
		filierelmdBckBean.setListRefFiliereLmdDto(null);
		return "toEdit";
	}

	public void findGeneric() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefFiliereLmdDto> result = refFiliereLmdServiceImpl
					.findGeneric(this.searchInput);
			if (result == null || result.isEmpty()) {
				filierelmdBckBean.setListRefFiliereLmdDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				filierelmdBckBean.setListRefFiliereLmdDto(result);

			}
		} catch (Exception e) {
			filierelmdBckBean.setListRefFiliereLmdDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}


	public void findAdvanced() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefFiliereLmdDto> result = refFiliereLmdServiceImpl
					.findAdvanced(searchDto);

			if (result == null || result.isEmpty()) {
				filierelmdBckBean.setListRefFiliereLmdDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				filierelmdBckBean.setListRefFiliereLmdDto(result);

			}
		} catch (Exception e) {
			filierelmdBckBean.setListRefFiliereLmdDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}


	public String toSearch() {
		filierelmdBckBean = new FilierelmdBckBean();
		return "toSearchFiliereLmd";
	}


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

	
	public String saveFiliere() {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("Mgr Bean saveFiliere");
			
			if (isUpdateMode()) {
				refFiliereLmdServiceImpl.update(refFiliereLmdDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setDisableOnglet(false);
				return null;
				
			} else {
				refFiliereLmdServiceImpl.save(refFiliereLmdDto);
				setUpdateMode(true);
				filierelmdBckBean.setRefFiliereLmdDto(refFiliereLmdDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setDisableOnglet(false);
				flash.setKeepMessages(true);
				return  "filierelmdEdit?faces-redirect=true";
				
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
		return null;
		
	}


	public RefFiliereLmdService getRefFiliereLmdServiceImpl() {
		return refFiliereLmdServiceImpl;
	}


	public void setRefFiliereLmdServiceImpl(
			RefFiliereLmdService refFiliereLmdServiceImpl) {
		this.refFiliereLmdServiceImpl = refFiliereLmdServiceImpl;
	}


	public RefFiliereLmdDto getSearchDto() {
		return searchDto;
	}


	public void setSearchDto(RefFiliereLmdDto searchDto) {
		this.searchDto = searchDto;
	}


	public FilierelmdBckBean getFilierelmdBckBean() {
		return filierelmdBckBean;
	}


	public void setFilierelmdBckBean(FilierelmdBckBean FilierelmdBckBean) {
		this.filierelmdBckBean = FilierelmdBckBean;
	}


	public void setRefFiliereLmdDto(RefFiliereLmdDto refFiliereLmdDto) {
		this.refFiliereLmdDto = refFiliereLmdDto;
	}


	public Flash getFlash() {
		return flash;
	}


	public void setFlash(Flash flash) {
		this.flash = flash;
	}
	
	public void delete(RefFiliereLmdDto selectedFiliereLmdDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedFiliereLmdDto != null) && (selectedFiliereLmdDto.getIdentifiant()!=null)) {
			try {
				log.info("deleting FiliereLmd partenariat id = "+selectedFiliereLmdDto.getIdentifiant());			
			  refFiliereLmdServiceImpl.delete(selectedFiliereLmdDto);
			
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



	/**
	 * [FilierelmdMgrBean.listRefFiliereLmdDto] Getter 
	 * @author BELBRIK Oualid on : 27 avr. 2014  12:26:18
	 * @return the listRefFiliereLmdDto
	 */
	public List<RefFiliereLmdDto> getListRefFiliereLmdDto() {
		return getListRefFiliereLmdDto();
	}


	/**
	 * [FilierelmdMgrBean.listRefFiliereLmdDto] Setter 
	 * @author BELBRIK Oualid on : 27 avr. 2014  12:26:18
	 * @param listRefFiliereLmdDto the listRefFiliereLmdDto to set
	 */
	public void setListRefFiliereLmdDto(List<RefFiliereLmdDto> listRefFiliereLmdDto) {
		this.listRefFiliereLmdDto = listRefFiliereLmdDto;
	}
	
	public void load() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefFiliereLmdDto> result = refFiliereLmdServiceImpl
					.findAll();
			if (result == null || result.isEmpty()) {
				filierelmdBckBean.setListRefFiliereLmdDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				filierelmdBckBean.setListRefFiliereLmdDto(result);

			}
		} catch (Exception e) {
			filierelmdBckBean.setListRefFiliereLmdDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}


	
	
	public void findByDomainelmd(Integer idDomainelmd) {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefFiliereLmdDto> result = refFiliereLmdServiceImpl
					.findByIdDomainelmd(idDomainelmd);
			if (result == null || result.isEmpty()) {
				filierelmdBckBean.setListRefFiliereLmdDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				filierelmdBckBean.setListRefFiliereLmdDto(result);
			}
		} catch (Exception e) {
			filierelmdBckBean.setListRefFiliereLmdDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}
	
	public String cancel() {
		return "filierelmdSearch";
	}


	
	
}
