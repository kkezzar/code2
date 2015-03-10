
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.specialitelmd;

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
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FlowEvent;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;

/**
 * @author obelbrik
 * 
 */
@ManagedBean(name = "specialitelmdMgrBean")
@RequestScoped
public class SpecialitelmdMgrBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String searchInput;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	@ManagedProperty(value = "#{refSpecialiteLmdServiceImpl}")
	private RefSpecialiteLmdService refSpecialiteLmdServiceImpl;
	private static final Log log = LogFactory.getLog(SpecialitelmdMgrBean.class);
	private RefSpecialiteLmdDto refSpecialiteLmdDto;
	private RefSpecialiteLmdDto searchDto;
	private boolean updateMode;
	private boolean readMode;
	@ManagedProperty(value = "#{specialitelmdBckBean}")
	private SpecialitelmdBckBean specialitelmdBckBean;
	private boolean disableOnglet;
    @ManagedProperty("#{flash}")
    private Flash flash;
    private List<SelectItem> listFilieresdomaine;
    @ManagedProperty(value = "#{refFiliereLmdServiceImpl}")
	private RefFiliereLmdService refFiliereLmdServiceImpl;
	@ManagedProperty("#{param.idDomain}")
	private int idDomain;
	@ManagedProperty("#{param.idFiliere}")
	private String idFiliere;
	private boolean enChange;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
    

    
    /**
	 * 
	 * 
	 * @author BELBRIK Oualid on : 24 avril. 2014 09:19:25
	 */
    
	public SpecialitelmdMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				SpecialitelmdMgrBean.MESSAGES_FILE_NAME);
		searchDto = new RefSpecialiteLmdDto();
		disableOnglet =  true;
		
	}


	@PostConstruct
	public void init() {
		log.info("******************************@PostConstruct: ");

	}
	
	

	
	
	public void domainChanged(AjaxBehaviorEvent event)
			  {
	
		if (this.refSpecialiteLmdDto.getIdDomaine() != null) {
			listFilieresdomaine = new ArrayList<SelectItem>();

			List<RefFiliereLmdDto> listFilieresDto = refFiliereLmdServiceImpl.findByIdDomainelmd(refSpecialiteLmdDto.getIdDomaine());

			for (RefFiliereLmdDto ld : listFilieresDto) {
				SelectItem itemFr = new SelectItem(ld.getId(),
						ld.getLlFiliere()  + " (" + ld.getId() + ")");
				listFilieresdomaine.add(itemFr);
				
				

			}
		}

	}

	
	public String getSearchInput() {

		return searchInput; 
	}


	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	
	public RefSpecialiteLmdDto getRefSpecialiteLmdDto() {
		if (this.refSpecialiteLmdDto == null) {
			if (specialitelmdBckBean.getRefSpecialiteLmdDto() != null) {
				setRefSpecialiteLmdDto(specialitelmdBckBean.getRefSpecialiteLmdDto());
			} else {
				refSpecialiteLmdDto = new RefSpecialiteLmdDto();
			}
		}
		return refSpecialiteLmdDto;
	}


	public boolean isUpdateMode() {
		if (updateMode == false) {
			if (specialitelmdBckBean.getRefSpecialiteLmdDto() != null) {
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
	 * [SpecialiteLmdMgrBean.disableOnglet] Getter 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014  19:57:19
	 * @return the disableOnglet
	 */
	public boolean isDisableOnglet() {
		return disableOnglet;
	}


	/**
	 * [SpecialiteLmdMgrBean.disableOnglet] Setter 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014  19:57:19
	 * @param disableOnglet the disableOnglet to set
	 */
	public void setDisableOnglet(boolean disableOnglet) {
		this.disableOnglet = disableOnglet;
	}


	public String toDetails() {
		specialitelmdBckBean.setListRefSpecialiteLmdDto(null);
		return "toDetails";
	}


	public String toNew() {
		specialitelmdBckBean.setListRefSpecialiteLmdDto(null);
		specialitelmdBckBean.setRefSpecialiteLmdDto(null);
		return "toNew";
	}


	public String toEdit() {
		setDisableOnglet(false);
		specialitelmdBckBean.setListRefSpecialiteLmdDto(null);
		return "toEdit";
	}

	public void findGeneric() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefSpecialiteLmdDto> result = refSpecialiteLmdServiceImpl
					.findGeneric(this.searchInput);
			if (result == null || result.isEmpty()) {
				specialitelmdBckBean.setListRefSpecialiteLmdDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				specialitelmdBckBean.setListRefSpecialiteLmdDto(result);

			}
		} catch (Exception e) {
			specialitelmdBckBean.setListRefSpecialiteLmdDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}


	public void findAdvanced() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefSpecialiteLmdDto> result = refSpecialiteLmdServiceImpl
					.findAdvanced(searchDto);

			if (result == null || result.isEmpty()) {
				specialitelmdBckBean.setListRefSpecialiteLmdDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				specialitelmdBckBean.setListRefSpecialiteLmdDto(result);

			}
		} catch (Exception e) {
			specialitelmdBckBean.setListRefSpecialiteLmdDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}


	public String toSearch() {
		specialitelmdBckBean = new SpecialitelmdBckBean();
		return "toSearchSpecialiteLmd";
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

	
	public String saveSpecialite() {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("Mgr Bean saveSpecialite");
			
			if (isUpdateMode()) {
				refSpecialiteLmdServiceImpl.update(refSpecialiteLmdDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setDisableOnglet(false);
				return null;
				
			} else {
				refSpecialiteLmdServiceImpl.save(refSpecialiteLmdDto);
				setUpdateMode(true);
				specialitelmdBckBean.setRefSpecialiteLmdDto(refSpecialiteLmdDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setDisableOnglet(false);
				flash.setKeepMessages(true);
				return  "specialitelmdEdit?faces-redirect=true";
				
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


	public RefSpecialiteLmdService getRefSpecialiteLmdServiceImpl() {
		return refSpecialiteLmdServiceImpl;
	}


	public void setRefSpecialiteLmdServiceImpl(
			RefSpecialiteLmdService refSpecialiteLmdServiceImpl) {
		this.refSpecialiteLmdServiceImpl = refSpecialiteLmdServiceImpl;
	}


	public RefSpecialiteLmdDto getSearchDto() {
		return searchDto;
	}


	public void setSearchDto(RefSpecialiteLmdDto searchDto) {
		this.searchDto = searchDto;
	}


	public SpecialitelmdBckBean getspecialitelmdBckBean() {
		return specialitelmdBckBean;
	}


	public void setspecialitelmdBckBean(SpecialitelmdBckBean specialitelmdBckBean) {
		this.specialitelmdBckBean = specialitelmdBckBean;
	}


	public void setRefSpecialiteLmdDto(RefSpecialiteLmdDto refSpecialiteLmdDto) {
		this.refSpecialiteLmdDto = refSpecialiteLmdDto;
	}


	public Flash getFlash() {
		return flash;
	}


	public void setFlash(Flash flash) {
		this.flash = flash;
	}
	
	public void delete(RefSpecialiteLmdDto selectedSpecialiteLmdDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedSpecialiteLmdDto != null) && (selectedSpecialiteLmdDto.getIdentifiant()!=null)) {
			try {
				log.info("deleting SpecialiteLmd partenariat id = "+selectedSpecialiteLmdDto.getIdentifiant());			
			  refSpecialiteLmdServiceImpl.delete(selectedSpecialiteLmdDto);
			
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
	 * [SpecialitelmdMgrBean.listFilieresdomaine] Getter 
	 * @author BELBRIK Oualid on : 20 mai 2014  10:42:41
	 * @return the listFilieresdomaine
	 */
	public List<SelectItem> getListFilieresdomaine() {
		return loadFiliere();
	}
	
		
	public void setListFilieresdomaine(List<SelectItem> listFilieresdomaine) {
		this.listFilieresdomaine = listFilieresdomaine;
	}


	public List<SelectItem> loadFiliere() {
		if (listFilieresdomaine == null || listFilieresdomaine.isEmpty()) {
			listFilieresdomaine = new ArrayList<SelectItem>();
				List<RefFiliereLmdDto> list = null;
			
					list = refFiliereLmdServiceImpl
							.findByIdDomainelmd(idDomain);

				
				for (RefFiliereLmdDto refFiliereLmdDto : list) {
					SelectItem selectItem = new SelectItem(refFiliereLmdDto.getId());
					listFilieresdomaine.add(selectItem);
				}
			}
			return listFilieresdomaine;
		}




	/**
	 * [SpecialitelmdMgrBean.refFiliereLmdServiceImpl] Getter 
	 * @author BELBRIK Oualid on : 8 mai 2014  18:52:29
	 * @return the refFiliereLmdServiceImpl
	 */
	public RefFiliereLmdService getRefFiliereLmdServiceImpl() {
		return refFiliereLmdServiceImpl;
	}



	/**
	 * [SpecialitelmdMgrBean.refFiliereLmdServiceImpl] Setter 
	 * @author BELBRIK Oualid on : 8 mai 2014  18:52:29
	 * @param refFiliereLmdServiceImpl the refFiliereLmdServiceImpl to set
	 */
	public void setRefFiliereLmdServiceImpl(
			RefFiliereLmdService refFiliereLmdServiceImpl) {
		this.refFiliereLmdServiceImpl = refFiliereLmdServiceImpl;
	}


	/**
	 * [SpecialitelmdMgrBean.idDomain] Getter 
	 * @author BELBRIK Oualid on : 20 mai 2014  10:42:41
	 * @return the idDomain
	 */
	public int getIdDomain() {
		return idDomain;
	}


	/**
	 * [SpecialitelmdMgrBean.idDomain] Setter 
	 * @author BELBRIK Oualid on : 20 mai 2014  10:42:41
	 * @param idDomain the idDomain to set
	 */
	public void setIdDomain(int idDomain) {
		this.idDomain = idDomain;
	}


	/**
	 * [SpecialitelmdMgrBean.idFiliere] Getter 
	 * @author BELBRIK Oualid on : 20 mai 2014  10:42:41
	 * @return the idFiliere
	 */
	public String getIdFiliere() {
		return idFiliere;
	}


	/**
	 * [SpecialitelmdMgrBean.idFiliere] Setter 
	 * @author BELBRIK Oualid on : 20 mai 2014  10:42:41
	 * @param idFiliere the idFiliere to set
	 */
	public void setIdFiliere(String idFiliere) {
		this.idFiliere = idFiliere;
	}


	public void enChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			enChange = true;
		}
	}


	/**
	 * [SpecialitelmdMgrBean.specialitelmdBckBean] Getter 
	 * @author BELBRIK Oualid on : 31 juil. 2014  14:02:27
	 * @return the specialitelmdBckBean
	 */
	public SpecialitelmdBckBean getSpecialitelmdBckBean() {
		return specialitelmdBckBean;
	}


	/**
	 * [SpecialitelmdMgrBean.specialitelmdBckBean] Setter 
	 * @author BELBRIK Oualid on : 31 juil. 2014  14:02:27
	 * @param specialitelmdBckBean the specialitelmdBckBean to set
	 */
	public void setSpecialitelmdBckBean(SpecialitelmdBckBean specialitelmdBckBean) {
		this.specialitelmdBckBean = specialitelmdBckBean;
	}


	/**
	 * [SpecialitelmdMgrBean.enChange] Getter 
	 * @author BELBRIK Oualid on : 31 juil. 2014  14:02:27
	 * @return the enChange
	 */
	public boolean isEnChange() {
		return enChange;
	}


	/**
	 * [SpecialitelmdMgrBean.enChange] Setter 
	 * @author BELBRIK Oualid on : 31 juil. 2014  14:02:27
	 * @param enChange the enChange to set
	 */
	public void setEnChange(boolean enChange) {
		this.enChange = enChange;
	}


	/**
	 * [SpecialitelmdMgrBean.searchValue] Getter 
	 * @author BELBRIK Oualid on : 31 juil. 2014  14:02:27
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}


	/**
	 * [SpecialitelmdMgrBean.searchValue] Setter 
	 * @author BELBRIK Oualid on : 31 juil. 2014  14:02:27
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	
	public String cancel() {
		return "specialitelmdSearch";
	}


	
}
