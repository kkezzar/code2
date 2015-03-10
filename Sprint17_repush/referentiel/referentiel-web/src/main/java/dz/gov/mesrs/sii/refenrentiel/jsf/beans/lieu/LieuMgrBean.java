
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.lieu;

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
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefLieuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefLieuService;

/**
 * @author obelbrik
 * 
 */
@ManagedBean(name = "lieuMgrBean")
@RequestScoped
public class LieuMgrBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String searchInput;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	@ManagedProperty(value = "#{refLieuServiceImpl}")
	private RefLieuService refLieuServiceImpl;
	private static final Log log = LogFactory.getLog(LieuMgrBean.class);
	private RefLieuDto refLieuDto;
	private RefLieuDto searchDto;
	private boolean updateMode;
	private boolean readMode;
	@ManagedProperty(value = "#{lieuBckBean}")
	private LieuBckBean lieuBckBean;
	private RefLieuDto refLieuDtoPere;
    private boolean disableOnglet;
    private boolean typelieuissalle;
    @ManagedProperty("#{flash}")
    private Flash flash;
    @ManagedProperty(value = "#{comboBckBean}")
	private ComboBckBean comboBckBean;
	
   
	
    
    /**
	 * [GroupeMgrBean.GroupeMgrBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:19:25
	 */
    
	public LieuMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				LieuMgrBean.MESSAGES_FILE_NAME);
		searchDto = new RefLieuDto();
		disableOnglet =  true;
		
	}


	public String getSearchInput() {

		return searchInput;
	}


	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	
	public RefLieuDto getRefLieuDto() {
		if (this.refLieuDto == null) {
			if (lieuBckBean.getRefLieuDto() != null) {
				setRefLieuDto(lieuBckBean.getRefLieuDto());
			} else {
				refLieuDto = new RefLieuDto();
				setDefaultValues();
			}
		}
		return refLieuDto;
	}


	public boolean isUpdateMode() {
		if (updateMode == false) {
			if (lieuBckBean.getRefLieuDto() != null) {
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
	 * [LieuMgrBean.disableOnglet] Getter 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014  19:57:19
	 * @return the disableOnglet
	 */
	public boolean isDisableOnglet() {
		return disableOnglet;
	}


	/**
	 * [LieuMgrBean.disableOnglet] Setter 
	 * @author BELBRIK Oualid on : 17 f�vr. 2014  19:57:19
	 * @param disableOnglet the disableOnglet to set
	 */
	public void setDisableOnglet(boolean disableOnglet) {
		this.disableOnglet = disableOnglet;
	}


	public String toDetails() {
		lieuBckBean.setListRefLieuDto(null);
		return "toDetails";
	}


	public String toNew() {
		lieuBckBean.setListRefLieuDto(null);
		lieuBckBean.setRefLieuDto(null);
		lieuBckBean.setTypelieuissalle(false);
		return "toNew";
	}


	public String toEdit() {
		setDisableOnglet(false);
		lieuBckBean.setListRefLieuDto(null);
		return "toEdit";
	}

	public void findGeneric() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefLieuDto> result = refLieuServiceImpl
					.findGeneric(SessionValues.getIdEtablissement(), this.searchInput);
			if (result == null || result.isEmpty()) {
				lieuBckBean.setListRefLieuDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				lieuBckBean.setListRefLieuDto(result);

			}
		} catch (Exception e) {
			lieuBckBean.setListRefLieuDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}


	public void findAdvanced() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefLieuDto> result = refLieuServiceImpl
					.findAdvanced(SessionValues.getIdEtablissement(), searchDto);

			if (result == null || result.isEmpty()) {
				lieuBckBean.setListRefLieuDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				lieuBckBean.setListRefLieuDto(result);

			}
		} catch (Exception e) {
			lieuBckBean.setListRefLieuDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}


	public String toSearch() {
		lieuBckBean = new LieuBckBean();
		return "toSearchLieu";
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

	
	public String saveLieu() {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("Mgr Bean saveLieu");
			if (isUpdateMode()) {
				refLieuDto.setIdEtablissement(SessionValues.getIdEtablissement());
				refLieuServiceImpl.update(refLieuDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setDisableOnglet(false);
				setTypelieuissalle(false);
			} else {
				refLieuDto.setIdEtablissement(SessionValues.getIdEtablissement());
				refLieuServiceImpl.save(refLieuDto);
				setUpdateMode(true);
				lieuBckBean.setRefLieuDto(refLieuDto);
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
		return  "lieuEdit?faces-redirect=true";
	}


	public RefLieuService getRefLieuServiceImpl() {
		return refLieuServiceImpl;
	}


	public void setRefLieuServiceImpl(
			RefLieuService refLieuServiceImpl) {
		this.refLieuServiceImpl = refLieuServiceImpl;
	}


	public RefLieuDto getSearchDto() {
		return searchDto;
	}


	public void setSearchDto(RefLieuDto searchDto) {
		this.searchDto = searchDto;
	}


	public LieuBckBean getlieuBckBean() {
		return lieuBckBean;
	}


	public void setlieuBckBean(LieuBckBean lieuBckBean) {
		this.lieuBckBean = lieuBckBean;
	}


	public void setRefLieuDto(RefLieuDto refLieuDto) {
		this.refLieuDto = refLieuDto;
	}


	public RefLieuDto getRefLieuDtoPere() {
		if(refLieuDto!=null && refLieuDto.getIdPereLieu()!=null ){
			refLieuDtoPere = refLieuServiceImpl.findById(refLieuDto.getIdPereLieu());
		}else{
			refLieuDtoPere = new RefLieuDto();
		}
		return refLieuDtoPere;
	}

	public void setRefLieuDtoPere(RefLieuDto refLieuDtoPere) {
		this.refLieuDtoPere = refLieuDtoPere;
	}
	
	/**
	 * listener on change Type lieu
	 * @return 
	 */
	public void onChangeTypeLieu() {
		
		
		if ( refLieuDto.getTypelieuLibelleLongAr().equals("Salle")) {
		    setTypelieuissalle(true);
			}
			else{
			setTypelieuissalle(false);
			}
		
	}



	public boolean isTypelieuissalle() {
		return typelieuissalle;
	}


	public void setTypelieuissalle(boolean typelieuissalle) {
		this.typelieuissalle = typelieuissalle;
	}


	/**
	 * [LieuMgrBean.flash] Getter 
	 * @author MAKERRI Sofiane on : 9 avr. 2014  10:10:02
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}


	/**
	 * [LieuMgrBean.flash] Setter 
	 * @author MAKERRI Sofiane on : 9 avr. 2014  10:10:02
	 * @param flash the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}
	
	/**
	 * [LieuMgrBean.setDefaultValues] Method 
	 * @author MAKERRI Sofiane  on : 29 avr. 2014  12:21:04
	 */
	public void setDefaultValues() {
		refLieuDto.setTypelieuId(comboBckBean.getDefaultTypeLieu());
		refLieuDto.setTypeImplantId(comboBckBean.getDefaultTypeImplan());
		refLieuDto.setTypeAccesId(comboBckBean.getDefaultTypeAcces());
	}


	/**
	 * [LieuMgrBean.comboBckBean] Getter 
	 * @author MAKERRI Sofiane on : 29 avr. 2014  12:25:03
	 * @return the comboBckBean
	 */
	public ComboBckBean getComboBckBean() {
		return comboBckBean;
	}


	/**
	 * [LieuMgrBean.comboBckBean] Setter 
	 * @author MAKERRI Sofiane on : 29 avr. 2014  12:25:03
	 * @param comboBckBean the comboBckBean to set
	 */
	public void setComboBckBean(ComboBckBean comboBckBean) {
		this.comboBckBean = comboBckBean;
	}


	
	
	

	
}
