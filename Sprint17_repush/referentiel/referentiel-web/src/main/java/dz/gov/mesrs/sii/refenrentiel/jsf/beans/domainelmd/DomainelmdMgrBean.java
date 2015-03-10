
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.domainelmd;

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
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FlowEvent;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;

/**
 * @author obelbrik
 * 
 */
@ManagedBean(name = "domainelmdMgrBean")
@RequestScoped
public class DomainelmdMgrBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String searchInput;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	@ManagedProperty(value = "#{refDomaineLMDServiceImpl}")
	private RefDomaineLMDService refDomaineLMDServiceImpl;
	private static final Log log = LogFactory.getLog(DomainelmdMgrBean.class);
	private RefDomaineLMDDto refDomaineLMDDto;
	private RefDomaineLMDDto searchDto;
	private boolean updateMode;
	private boolean readMode;
	@ManagedProperty(value = "#{domainelmdBckBean}")
	private DomainelmdBckBean domainelmdBckBean;
	private boolean disableOnglet;
    @ManagedProperty("#{flash}")
    private Flash flash;
	@ManagedProperty(value = "#{domaineaffectationCrudBean}")
	private DomaineAffectationCrudBean domaineaffectationCrudBean;

   

    
    /**
	 * 
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:19:25
	 */
    
	public DomainelmdMgrBean() {
		super();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				DomainelmdMgrBean.MESSAGES_FILE_NAME);
		searchDto = new RefDomaineLMDDto();
		disableOnglet =  true;
		
	}



	public String getSearchInput() {

		return searchInput;
	}


	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	
	public RefDomaineLMDDto getRefDomaineLMDDto() {
		if (this.refDomaineLMDDto == null) {
			if (domainelmdBckBean.getRefDomaineLMDDto() != null) {
				setRefDomaineLMDDto(domainelmdBckBean.getRefDomaineLMDDto());
			} else {
				refDomaineLMDDto = new RefDomaineLMDDto();
			}
		}
		return refDomaineLMDDto;
	}


	public boolean isUpdateMode() {
		if (updateMode == false) {
			if (domainelmdBckBean.getRefDomaineLMDDto() != null) {
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
	 * [DomainelmdMgrBean.disableOnglet] Getter 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014  19:57:19
	 * @return the disableOnglet
	 */
	public boolean isDisableOnglet() {
		return disableOnglet;
	}


	/**
	 * [DomainelmdMgrBean.disableOnglet] Setter 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014  19:57:19
	 * @param disableOnglet the disableOnglet to set
	 */
	public void setDisableOnglet(boolean disableOnglet) {
		this.disableOnglet = disableOnglet;
	}


	public String toDetails() {
		domainelmdBckBean.setListRefDomaineLMDDto(null);
		return "toDetails";
	}


	public String toNew() {
		domainelmdBckBean.setListRefDomaineLMDDto(null);
		domainelmdBckBean.setRefDomaineLMDDto(null);
		return "toNew";
	}


	public String toEdit() {
		setDisableOnglet(false);
		domainelmdBckBean.setListRefDomaineLMDDto(null);
		return "toEdit";
	}

	public void findGeneric() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefDomaineLMDDto> result = refDomaineLMDServiceImpl
					.findGeneric(this.searchInput);
			if (result == null || result.isEmpty()) {
				domainelmdBckBean.setListRefDomaineLMDDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				domainelmdBckBean.setListRefDomaineLMDDto(result);

			}
		} catch (Exception e) {
			domainelmdBckBean.setListRefDomaineLMDDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}


	public void findAdvanced() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefDomaineLMDDto> result = refDomaineLMDServiceImpl
					.findAdvanced(searchDto);

			if (result == null || result.isEmpty()) {
				domainelmdBckBean.setListRefDomaineLMDDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				domainelmdBckBean.setListRefDomaineLMDDto(result);

			}
		} catch (Exception e) {
			domainelmdBckBean.setListRefDomaineLMDDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}


	public String toSearch() {
		domainelmdBckBean = new DomainelmdBckBean();
		return "toSearchDomainelmd";
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

	
	public String saveDomaine() {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("Mgr Bean saveDomaine");
			
			if (isUpdateMode()) {
				refDomaineLMDServiceImpl.update(refDomaineLMDDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setDisableOnglet(false);
				return null;
				
			} else {
				refDomaineLMDServiceImpl.save(refDomaineLMDDto);
				setUpdateMode(true);
				domainelmdBckBean.setRefDomaineLMDDto(refDomaineLMDDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setDisableOnglet(false);
				flash.setKeepMessages(true);
				return  "domainelmdEdit?faces-redirect=true&id="+refDomaineLMDDto.getId();
				
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


	public RefDomaineLMDService getRefDomaineLMDServiceImpl() {
		return refDomaineLMDServiceImpl;
	}


	public void setRefDomaineLMDServiceImpl(
			RefDomaineLMDService refDomaineLMDServiceImpl) {
		this.refDomaineLMDServiceImpl = refDomaineLMDServiceImpl;
	}


	public RefDomaineLMDDto getSearchDto() {
		return searchDto;
	}


	public void setSearchDto(RefDomaineLMDDto searchDto) {
		this.searchDto = searchDto;
	}


	public DomainelmdBckBean getdomainelmdBckBean() {
		return domainelmdBckBean;
	}


	public void setdomainelmdBckBean(DomainelmdBckBean domainelmdBckBean) {
		this.domainelmdBckBean = domainelmdBckBean;
	}


	public void setRefDomaineLMDDto(RefDomaineLMDDto refDomaineLMDDto) {
		this.refDomaineLMDDto = refDomaineLMDDto;
	}


	public Flash getFlash() {
		return flash;
	}


	public void setFlash(Flash flash) {
		this.flash = flash;
	}
	
	public void delete(RefDomaineLMDDto selectedDomaineLMDDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedDomaineLMDDto != null) && (selectedDomaineLMDDto.getIdentifiant()!=null)) {
			try {
				log.info("deleting DomaineLMD partenariat id = "+selectedDomaineLMDDto.getIdentifiant());			
			  refDomaineLMDServiceImpl.delete(selectedDomaineLMDDto);
			
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
	
	public void crud(boolean createAllow, boolean editAllow, boolean deleteAllow) {
		domaineaffectationCrudBean.setEditAllow(editAllow);
		domaineaffectationCrudBean.setCreateAllow(createAllow);
		domaineaffectationCrudBean.setDeleteAllow(deleteAllow);
	}


	/**
	 * [DomainelmdMgrBean.domainelmdBckBean] Getter 
	 * @author BELBRIK Oualid on : 27 avr. 2014  12:02:10
	 * @return the domainelmdBckBean
	 */
	public DomainelmdBckBean getDomainelmdBckBean() {
		return domainelmdBckBean;
	}


	/**
	 * [DomainelmdMgrBean.domainelmdBckBean] Setter 
	 * @author BELBRIK Oualid on : 27 avr. 2014  12:02:10
	 * @param domainelmdBckBean the domainelmdBckBean to set
	 */
	public void setDomainelmdBckBean(DomainelmdBckBean domainelmdBckBean) {
		this.domainelmdBckBean = domainelmdBckBean;
	}


	/**
	 * [DomainelmdMgrBean.domaineaffectationCrudBean] Getter 
	 * @author BELBRIK Oualid on : 27 avr. 2014  12:02:10
	 * @return the domaineaffectationCrudBean
	 */
	public DomaineAffectationCrudBean getDomaineaffectationCrudBean() {
		return domaineaffectationCrudBean;
	}


	/**
	 * [DomainelmdMgrBean.domaineaffectationCrudBean] Setter 
	 * @author BELBRIK Oualid on : 27 avr. 2014  12:02:10
	 * @param domaineaffectationCrudBean the domaineaffectationCrudBean to set
	 */
	public void setDomaineaffectationCrudBean(
			DomaineAffectationCrudBean domaineaffectationCrudBean) {
		this.domaineaffectationCrudBean = domaineaffectationCrudBean;
	}
	
	public String cancel() {
		return "domainelmdSearch";
	}
	

}
