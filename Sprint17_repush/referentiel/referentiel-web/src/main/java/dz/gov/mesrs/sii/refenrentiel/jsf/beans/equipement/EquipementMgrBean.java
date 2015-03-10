
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.equipement;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FlowEvent;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.ComboBckBean;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEquipementDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefEquipementService;

/**
 * @author obelbrik
 * 
 */
@ManagedBean(name = "equipementMgrBean")
@RequestScoped
public class EquipementMgrBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String searchInput;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	@ManagedProperty(value = "#{refEquipementServiceImpl}")
	private RefEquipementService refEquipementServiceImpl;
	private static final Log log = LogFactory.getLog(EquipementMgrBean.class);
	private RefEquipementDto refEquipementDto;
	private RefEquipementDto searchDto;
	private boolean updateMode;
	private boolean readMode;
	@ManagedProperty(value = "#{equipementBckBean}")
	private EquipementBckBean equipementBckBean;
    private boolean disableOnglet;
    @ManagedProperty("#{flash}")
    private Flash flash;
    @ManagedProperty(value = "#{comboBckBean}")
   	private ComboBckBean comboBckBean;
	
    
    /**
	 * [GroupeMgrBean.GroupeMgrBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on :3 mars. 2014 09:19:25
	 */
    
	public EquipementMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				EquipementMgrBean.MESSAGES_FILE_NAME);
		searchDto = new RefEquipementDto();		
		disableOnglet =  true;
	}


	public String getSearchInput() {
		return searchInput;
	}


	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	
	public RefEquipementDto getRefEquipementDto() {
		if (this.refEquipementDto == null) {
			if (equipementBckBean.getRefEquipementDto() != null) {
				setRefEquipementDto(equipementBckBean.getRefEquipementDto());
			} else {
				refEquipementDto = new RefEquipementDto();
				setDefaultValues();
			}
		}
		return refEquipementDto;
	}


	public boolean isUpdateMode() {
		if (updateMode == false) {
			if (equipementBckBean.getRefEquipementDto() != null) {
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
	 * [EquipementMgrBean.disableOnglet] Getter 
	 * @author BELBRIK Oualid on : 3 mars. 2014  19:57:19
	 * @return the disableOnglet
	 */
	public boolean isDisableOnglet() {
		return disableOnglet;
	}


	/**
	 * [EquipementMgrBean.disableOnglet] Setter 
	 * @author BELBRIK Oualid on : 3 mars. 2014  19:57:19
	 * @param disableOnglet the disableOnglet to set
	 */
	public void setDisableOnglet(boolean disableOnglet) {
		this.disableOnglet = disableOnglet;
	}


	public String toDetails() {
		equipementBckBean.setListRefEquipementDto(null);
		return "toDetails";
	}


	public String toNew() {
		equipementBckBean.setListRefEquipementDto(null);
		equipementBckBean.setRefEquipementDto(null);
		return "toNew";
	}


	public String toEdit() {
		setDisableOnglet(false);
		equipementBckBean.setListRefEquipementDto(null);
		return "toEdit";
	}

	public void findGeneric() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefEquipementDto> result = refEquipementServiceImpl
					.findGeneric(SessionValues.getIdEtablissement(), this.searchInput);
			if (result == null || result.isEmpty()) {
				equipementBckBean.setListRefEquipementDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				equipementBckBean.setListRefEquipementDto(result);

			}
		} catch (Exception e) {
			equipementBckBean.setListRefEquipementDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}


	public void findAdvanced() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefEquipementDto> result = refEquipementServiceImpl
					.findAdvanced(SessionValues.getIdEtablissement(), searchDto);

			if (result == null || result.isEmpty()) {
				equipementBckBean.setListRefEquipementDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				equipementBckBean.setListRefEquipementDto(result);

			}
		} catch (Exception e) {
			equipementBckBean.setListRefEquipementDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}


	public String toSearch() {
		equipementBckBean = new EquipementBckBean();
		return "toSearchEquipement";
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

	public String saveEquipement() {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("Mgr Bean saveEquipement");
			if (isUpdateMode()) {
				refEquipementDto.setIdEtablissement(SessionValues.getIdEtablissement());
				refEquipementServiceImpl.update(refEquipementDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setDisableOnglet(false);
			} else {
				refEquipementDto.setIdEtablissement(SessionValues.getIdEtablissement());
				refEquipementServiceImpl.save(refEquipementDto);
				setUpdateMode(true);
				equipementBckBean.setRefEquipementDto(refEquipementDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setDisableOnglet(false);
				
				
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
		flash.setKeepMessages(true);
		return  "equipementEdit?faces-redirect=true";
		
	}


	public RefEquipementService getRefEquipementServiceImpl() {
		return refEquipementServiceImpl;
	}


	public void setRefEquipementServiceImpl(
			RefEquipementService refEquipementServiceImpl) {
		this.refEquipementServiceImpl = refEquipementServiceImpl;
	}


	public RefEquipementDto getSearchDto() {
		return searchDto;
	}


	public void setSearchDto(RefEquipementDto searchDto) {
		this.searchDto = searchDto;
	}


	public EquipementBckBean getequipementBckBean() {
		return equipementBckBean;
	}


	public void setequipementBckBean(EquipementBckBean equipementBckBean) {
		this.equipementBckBean = equipementBckBean;
	}


	public void setRefEquipementDto(RefEquipementDto refEquipementDto) {
		this.refEquipementDto = refEquipementDto;
	}


	/**
	 * [EquipementMgrBean.flash] Getter 
	 * @author MAKERRI Sofiane on : 8 avr. 2014  18:02:03
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}


	/**
	 * [EquipementMgrBean.flash] Setter 
	 * @author MAKERRI Sofiane on : 8 avr. 2014  18:02:03
	 * @param flash the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}
	
	/**
	 * [EquipementMgrBean.setDefaultValues] Method 
	 * @author MAKERRI Sofiane  on : 29 avr. 2014  12:29:18
	 */
	public void setDefaultValues() {
		refEquipementDto.setFamilleequipementId(comboBckBean.getDefaultFamillequipement());
		refEquipementDto.setSousfamilleequipementId(comboBckBean.getDefaultSousFamillequipement());
	}


	/**
	 * [EquipementMgrBean.comboBckBean] Getter 
	 * @author MAKERRI Sofiane on : 29 avr. 2014  12:29:04
	 * @return the comboBckBean
	 */
	public ComboBckBean getComboBckBean() {
		return comboBckBean;
	}


	/**
	 * [EquipementMgrBean.comboBckBean] Setter 
	 * @author MAKERRI Sofiane on : 29 avr. 2014  12:29:04
	 * @param comboBckBean the comboBckBean to set
	 */
	public void setComboBckBean(ComboBckBean comboBckBean) {
		this.comboBckBean = comboBckBean;
	}
	
	
	
	
	
}
