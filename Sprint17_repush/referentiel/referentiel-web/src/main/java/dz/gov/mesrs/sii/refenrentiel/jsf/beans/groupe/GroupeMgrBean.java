/**
 * 
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.groupe;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FlowEvent;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author obelbrik
 * 
 */
@ManagedBean(name = "groupeMgrBean")
@ViewScoped
public class GroupeMgrBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String searchInput;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	@ManagedProperty(value = "#{refGroupeServiceImpl}")
	private RefGroupeService refGroupeServiceImpl;
	private static final Log log = LogFactory.getLog(GroupeMgrBean.class);
	private RefGroupeDto refGroupeDto;
	private RefGroupeDto searchDto;
	private boolean updateMode;
	private boolean readMode;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty(value = "#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabServiceImpl;
	private int identifiantLength;
	@ManagedProperty(value = "#{groupeDroitBean}")
	private GroupeDroitBean groupeDroitBean;
	private boolean codeChange;
	private List<RefGroupeDto > listRefGroupeDto;

	/**
	 * [GroupeMgrBean.GroupeMgrBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:19:25
	 */
	public GroupeMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				GroupeMgrBean.MESSAGES_FILE_NAME);
		searchDto = new RefGroupeDto();

	}
	
	@PostConstruct
	public void init() {
		refGroupeDto = (RefGroupeDto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("refGroupeDto");

		if(refGroupeDto == null) {
			refGroupeDto = new RefGroupeDto();
		}
	}

	/**
	 * [GroupeMgrBean.getSearchInput] Method Getter
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:19:43
	 * @return the searchInput
	 */
	public String getSearchInput() {

		return searchInput;
	}

	/**
	 * [GroupeMgrBean.setSearchInput] Method Setter
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:20:24
	 * @param searchInput
	 */
	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	/**
	 * @return the refGroupeDto
	 */
	public RefGroupeDto getRefGroupeDto() {
		return refGroupeDto;
	}

	/**
	 * @return the updateMode
	 */
	public boolean isUpdateMode() {
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
	public String toDetails(RefGroupeDto current) {
		setReadMode(true);
		setUpdateMode(false);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("refGroupeDto", current);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("readMode", true);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("entity", "groupe");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("idEntity", current.getId());
		return "toDetails";
	}

	/**
	 * navigate to new Groupe page
	 * 
	 * @return outcome
	 */
	public String toNew() {
		setReadMode(true);
		setUpdateMode(false);
		return "toNew";
	}

	/**
	 * 
	 * navigate to edit Groupe page
	 * 
	 * @return
	 */
	public String toEdit(RefGroupeDto current) {
		setReadMode(false);
		setUpdateMode(true);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("refGroupeDto", current);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("entity", "groupe");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("idEntity", current.getId());
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("readMode", false);
		return "toEdit";
	}

	/**
	 * [GroupeMgrBean.findGeneric] Method Find the list of Groupe by the the
	 * search input
	 * 
	 * @author obelbrik on : 15 janv. 2014 11:18:14
	 */
	public void findGeneric() {
		FacesMessage msg = new FacesMessage();
		try {
			listRefGroupeDto = refGroupeServiceImpl.findGeneric(
					SessionValues.getIdEtablissement(), this.searchInput);
			if (listRefGroupeDto == null || listRefGroupeDto.isEmpty()) {
				
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				

			}
		} catch (Exception e) {
			
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * @author obelbrik on : 15 janv. 2014 11:17:49
	 */
	public void findAdvanced() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefGroupeDto> result = refGroupeServiceImpl.findAdvanced(
					SessionValues.getIdEtablissement(), searchDto);

			if (result == null || result.isEmpty()) {
				
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				

			}
		} catch (Exception e) {
			
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
		return "toSearchGroupe";
	}

	/**
	 * 
	 * @author obelbrik on : 15 janv. 2014 10:38:21
	 * @param event
	 * @return the current step
	 */
	public String onFlowProcess(FlowEvent event) {
		log.info("Current wizard step:" + event.getOldStep());
		log.info("Next step:" + event.getNewStep());
		if (event.getOldStep() != null
				&& event.getOldStep().equals(bundle.getString("common_infos"))) {
			if (isUpdateMode()) {
				return event.getNewStep();
			} else {
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_sauvegarde_data"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return event.getOldStep();
			}
		} else {
			return event.getNewStep();
		}

	}

	public String saveGroupe() {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("Mgr Bean saveGroupe");
			refGroupeDto.setIdEtablissement(SessionValues.getIdEtablissement());
			refGroupeDto.setIdFonction(groupeDroitBean.getIdFonction());
			refGroupeDto.setIdCompte(SessionValues.getIdCompte());
			if (codeChange) {
				String nextIdentifiy = generateIdentify();
				if (nextIdentifiy == null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ bundle.getString("error_generation_identify"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return null;
				} else if (nextIdentifiy.length() > identifiantLength) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec")
							+ ": "
							+ bundle.getString("error_identify_max_legnt_affected"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return null;
				}
				refGroupeDto.setIdentifiant(nextIdentifiy);
			}
			setCodeChange(false);
			if (refGroupeDto.getId() != null) {
				refGroupeServiceImpl.update(refGroupeDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {

				refGroupeServiceImpl.save(refGroupeDto);
				setUpdateMode(true);
				
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				flash.setKeepMessages(true);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().put("refGroupeDto", refGroupeDto);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().put("entity", "groupe");
				FacesContext.getCurrentInstance().getExternalContext().getFlash().put("idEntity", refGroupeDto.getId());
				FacesContext.getCurrentInstance().getExternalContext().getFlash().put("readMode", true);
				return "groupeEdit?faces-redirect=true";
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

	/**
	 * [GroupeMgrBean.refStructureServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:42:23
	 * @return the refGroupeServiceImpl
	 */
	public RefGroupeService getRefGroupeServiceImpl() {
		return refGroupeServiceImpl;
	}

	/**
	 * [GroupeMgrBean.refGroupeServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:42:23
	 * @param refGroupeServiceImpl
	 *            the refGroupeServiceImpl to set
	 */
	public void setRefGroupeServiceImpl(RefGroupeService refGroupeServiceImpl) {
		this.refGroupeServiceImpl = refGroupeServiceImpl;
	}

	/**
	 * [GroupeMgrBean.searchDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:42:23
	 * @return the searchDto
	 */
	public RefGroupeDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [GroupeMgrBean.searchDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:42:23
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(RefGroupeDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * [GroupeMgrBean.refGroupeDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:42:23
	 * @param refGroupeDto
	 *            the refGroupeDto to set
	 */
	public void setRefGroupeDto(RefGroupeDto refGroupeDto) {
		this.refGroupeDto = refGroupeDto;
	}

	/**
	 * [GroupeMgrBean.flash] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 avr. 2014 14:39:32
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [GroupeMgrBean.flash] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 avr. 2014 14:39:32
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [GroupeMgrBean.generateIdentify] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 avr. 2014 08:50:31
	 * @return
	 */
	public String generateIdentify() {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(refGroupeDto.getDateCreation());
			Integer year = cal.get(Calendar.YEAR);

			String order = refParametreEtabServiceImpl.findValueOfKey(
					SessionValues.getIdEtablissement(),
					RefUtilConstant.CODIFICATION_GROUPE_ORDER_LENGTH_KEY);
			if (order == null) {
				return null;
			}
			int orderLength = Integer.parseInt(order);
			String _year = refParametreEtabServiceImpl.findValueOfKey(
					SessionValues.getIdEtablissement(),
					RefUtilConstant.CODIFICATION_GROUPE_YEAR_LENGTH_KEY);
			int yearLength;
			if (_year == null) {
				yearLength = 2;
			} else {
				yearLength = Integer.parseInt(_year);
			}

			String year2 = year.toString().substring(4 - yearLength);
			String prefix = SessionValues.getIdfEtablissement() + year2;
			int prefixLength = prefix.length();
			identifiantLength = prefixLength + yearLength + orderLength;
			return refGroupeServiceImpl.generateIdentify(prefix, orderLength);
		} catch (Exception e) {

		}
		return null;

	}

	/**
	 * [GroupeMgrBean.refParametreEtabServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 17:45:49
	 * @return the refParametreEtabServiceImpl
	 */
	public RefParametreEtabService getRefParametreEtabServiceImpl() {
		return refParametreEtabServiceImpl;
	}

	/**
	 * [GroupeMgrBean.refParametreEtabServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 17:45:49
	 * @param refParametreEtabServiceImpl
	 *            the refParametreEtabServiceImpl to set
	 */
	public void setRefParametreEtabServiceImpl(
			RefParametreEtabService refParametreEtabServiceImpl) {
		this.refParametreEtabServiceImpl = refParametreEtabServiceImpl;
	}

	/**
	 * [GroupeMgrBean.identifiantLength] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 avr. 2014 11:50:17
	 * @return the identifiantLength
	 */
	public int getIdentifiantLength() {
		return identifiantLength;
	}

	/**
	 * [GroupeMgrBean.identifiantLength] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 avr. 2014 11:50:17
	 * @param identifiantLength
	 *            the identifiantLength to set
	 */
	public void setIdentifiantLength(int identifiantLength) {
		this.identifiantLength = identifiantLength;
	}

	/**
	 * [GroupeMgrBean.groupeDroitBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 11:30:32
	 * @return the groupeDroitBean
	 */
	public GroupeDroitBean getGroupeDroitBean() {
		return groupeDroitBean;
	}

	/**
	 * [GroupeMgrBean.groupeDroitBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 11:30:32
	 * @param groupeDroitBean
	 *            the groupeDroitBean to set
	 */
	public void setGroupeDroitBean(GroupeDroitBean groupeDroitBean) {
		this.groupeDroitBean = groupeDroitBean;
	}
	
	/**
	 * [GroupeMgrBean.codeChanged] Method 
	 * @author MAKERRI Sofiane  on : 27 juil. 2014  18:13:06
	 * @param event
	 */
	public void codeChanged(ValueChangeEvent event) {
		codeChange = true;
	}

	/**
	 * [GroupeMgrBean.codeChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 juil. 2014 18:12:20
	 * @return the codeChange
	 */
	public boolean isCodeChange() {
		return codeChange;
	}

	/**
	 * [GroupeMgrBean.codeChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 juil. 2014 18:12:20
	 * @param codeChange
	 *            the codeChange to set
	 */
	public void setCodeChange(boolean codeChange) {
		this.codeChange = codeChange;
	}

	/**
	 * [GroupeMgrBean.listRefGroupeDto] Getter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  12:16:23
	 * @return the listRefGroupeDto
	 */
	public List<RefGroupeDto> getListRefGroupeDto() {
		return listRefGroupeDto;
	}

	/**
	 * [GroupeMgrBean.listRefGroupeDto] Setter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  12:16:23
	 * @param listRefGroupeDto the listRefGroupeDto to set
	 */
	public void setListRefGroupeDto(List<RefGroupeDto> listRefGroupeDto) {
		this.listRefGroupeDto = listRefGroupeDto;
	}
	
	

}
