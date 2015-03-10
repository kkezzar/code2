/**
 * 
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.horaireaccess;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FlowEvent;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefHoraireAccessDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefHoraireAccessService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author obelbrik
 * 
 */
@ManagedBean(name = "horaireMgrBean")
@RequestScoped
public class HoraireMgrBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String searchInput;
	private ResourceBundle bundle;
	@ManagedProperty(value = "#{refHoraireAccessServiceImpl}")
	private RefHoraireAccessService refHoraireAccessServiceImpl;
	private static final Log log = LogFactory.getLog(HoraireMgrBean.class);
	private RefHoraireAccessDto refHoraireAccessDto;
	private RefHoraireAccessDto searchDto;
	private boolean updateMode;
	private boolean readMode;
	@ManagedProperty(value = "#{horaireBckBean}")
	private HoraireBckBean horaireBckBean;
	private ResourceBundle horaireBundle;
	private int identifiantLength;
	@ManagedProperty(value = "#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabServiceImpl;

	/**
	 * [PlageAdresseMgrBean.PlageAdresseMgrBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:19:25
	 */
	public HoraireMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		horaireBundle = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.HORAIRE_MESSAGES_FILE_NAME);
		searchDto = new RefHoraireAccessDto();
	}

	/**
	 * 
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:19:43
	 * @return the searchInput
	 */
	public String getSearchInput() {

		return searchInput;
	}

	/**
	 * @return the refHoraireAccessDto
	 */
	public RefHoraireAccessDto getRefHoraireAccessDto() {
		if (refHoraireAccessDto == null) {
			if (horaireBckBean.getRefHoraireAccessDto() != null) {
				refHoraireAccessDto = horaireBckBean.getRefHoraireAccessDto();
			} else {
				refHoraireAccessDto = new RefHoraireAccessDto();
			}
		}
		return refHoraireAccessDto;
	}

	/**
	 * @return the updateMode
	 */
	public boolean isUpdateMode() {
		if (updateMode == false) {
			if (horaireBckBean.getRefHoraireAccessDto() != null) {
				updateMode = true;
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
		horaireBckBean.setListRefHoraireAccessDto(null);
		return "toDetails";
	}

	/**
	 * navigate to new PlageAdresse page
	 * 
	 * @return outcome
	 */
	public String toNew() {
		setReadMode(true);
		setUpdateMode(false);
		horaireBckBean.setListRefHoraireAccessDto(null);
		horaireBckBean.setRefHoraireAccessDto(null);
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
		horaireBckBean.setListRefHoraireAccessDto(null);
		return "toEdit";
	}

	/**
	 * 
	 * the search input
	 * 
	 * @author obelbrik on : 15 janv. 2014 11:18:14
	 */
	public void findGeneric() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefHoraireAccessDto> result = refHoraireAccessServiceImpl
					.findGeneric(this.searchInput);
			if (result == null || result.isEmpty()) {
				horaireBckBean.setListRefHoraireAccessDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				horaireBckBean.setListRefHoraireAccessDto(result);

			}
		} catch (Exception e) {
			horaireBckBean.setListRefHoraireAccessDto(null);
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
		horaireBckBean = new HoraireBckBean();
		return "toSearchPlageAdresse";
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

	public void toSave() {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("Mgr Bean toSave");
			if (updateMode) {
				refHoraireAccessServiceImpl.update(refHoraireAccessDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				// verify time begin and time end
				if (refHoraireAccessDto.getHeureDebut().after(
						refHoraireAccessDto.getHeureFin())
						|| refHoraireAccessDto.getHeureDebut().equals(
								refHoraireAccessDto.getHeureFin())) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(horaireBundle
							.getString("horaire_debut_superieur_fin"));
					FacesContext.getCurrentInstance().addMessage(null, msg);

				} else {
					String nextIdentifiy = generateIdentify();
					if (nextIdentifiy == null) {
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						msg.setSummary(bundle.getString("error_echec") + ": "
								+ bundle.getString("error_generation_identify"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return;
					} else if (nextIdentifiy.length() > identifiantLength) {
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						msg.setSummary(bundle.getString("error_echec")
								+ ": "
								+ bundle.getString("error_identify_max_legnt_affected"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return;
					}
					refHoraireAccessDto.setIdentifiant(nextIdentifiy);
					refHoraireAccessServiceImpl.save(refHoraireAccessDto);
					setUpdateMode(true);
					horaireBckBean.setRefHoraireAccessDto(refHoraireAccessDto);
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundle.getString("msg_success") + ": "
							+ bundle.getString("msg_success_enregistrement"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
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
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:42:23
	 * @return the refHoraireAccessServiceImpl
	 */
	public RefHoraireAccessService getRefHoraireAccessServiceImpl() {
		return refHoraireAccessServiceImpl;
	}

	/**
	 * [
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:42:23
	 * @param refHoraireAccessServiceImpl
	 *            the refHoraireAccessServiceImpl to set
	 */
	public void setRefHoraireAccessServiceImpl(
			RefHoraireAccessService refHoraireAccessServiceImpl) {
		this.refHoraireAccessServiceImpl = refHoraireAccessServiceImpl;
	}

	/**
	 * [PlageAdresseMgrBean.searchDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:42:23
	 * @return the searchDto
	 */
	public RefHoraireAccessDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [PlageAdresseMgrBean.searchDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:42:23
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(RefHoraireAccessDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * [PlageadresseMgrBean.horaireBckBean] Getter
	 * 
	 * @author BELBRIK Oualid on : 2 mars 2014 09:40:48
	 * @return the horaireBckBean
	 */
	public HoraireBckBean getHoraireBckBean() {
		return horaireBckBean;
	}

	/**
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:42:23
	 * 
	 */
	public void setRefHoraireAccessDto(RefHoraireAccessDto refHoraireAccessDto) {
		this.refHoraireAccessDto = refHoraireAccessDto;
	}

	/**
	 * [HoraireMgrBean.horaireBckBean] Setter
	 * 
	 * @author BELBRIK Oualid on : 25 f�vr. 2014 16:42:34
	 * @param horaireBckBean
	 *            the horaireBckBean to set
	 */
	public void setHoraireBckBean(HoraireBckBean horaireBckBean) {
		this.horaireBckBean = horaireBckBean;
	}

	/**
	 * 
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:20:24
	 * @param searchInput
	 */
	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	/**
	 * 
	 * 
	 * @author BELBRIK Oualid on : 26 f�vr. 2014 18:35:24
	 * @param delete
	 *            horaire d'acces
	 */

	public void toRemove(RefHoraireAccessDto selectedRefHoraireAccessDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedRefHoraireAccessDto != null)
				&& (selectedRefHoraireAccessDto.getId() != 0)) {
			try {
				log.info("deleting Plage adresse id = "
						+ selectedRefHoraireAccessDto.getIdentifiant());
				refHoraireAccessServiceImpl.delete(selectedRefHoraireAccessDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_suppression"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				horaireBckBean.setListRefHoraireAccessDto(null);
			} catch (Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}

	}

	/**
	 * [HoraireMgrBean.generateIdentify] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 10:16:58
	 * @return
	 */
	public String generateIdentify() {
		try {
			String prefix = refParametreEtabServiceImpl
					.findValueOfKey(SessionValues.getIdEtablissement(),  RefUtilConstant.CODIFICATION_HORAIRE_ACCESS_PREFIX_KEY);
			if (prefix == null) {
				return null;
			}
			int prefixLength =  prefix.length();
			String order = refParametreEtabServiceImpl
					.findValueOfKey(SessionValues.getIdEtablissement(), RefUtilConstant.CODIFICATION_HORAIRE_ACCESS_ORDER_LENGTH_KEY);
			if (order == null) {
				return null;
			}
			int orderLength = Integer.parseInt(order);
			identifiantLength = prefixLength + orderLength;
			return refHoraireAccessServiceImpl.generateIdentify(prefix, orderLength);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
		
	}

	/**
	 * [HoraireMgrBean.identifiantLength] Getter 
	 * @author MAKERRI Sofiane on : 23 avr. 2014  12:41:39
	 * @return the identifiantLength
	 */
	public int getIdentifiantLength() {
		return identifiantLength;
	}

	/**
	 * [HoraireMgrBean.identifiantLength] Setter 
	 * @author MAKERRI Sofiane on : 23 avr. 2014  12:41:39
	 * @param identifiantLength the identifiantLength to set
	 */
	public void setIdentifiantLength(int identifiantLength) {
		this.identifiantLength = identifiantLength;
	}

	/**
	 * [HoraireMgrBean.refParametreEtabServiceImpl] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  17:46:46
	 * @return the refParametreEtabServiceImpl
	 */
	public RefParametreEtabService getRefParametreEtabServiceImpl() {
		return refParametreEtabServiceImpl;
	}

	/**
	 * [HoraireMgrBean.refParametreEtabServiceImpl] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  17:46:46
	 * @param refParametreEtabServiceImpl the refParametreEtabServiceImpl to set
	 */
	public void setRefParametreEtabServiceImpl(
			RefParametreEtabService refParametreEtabServiceImpl) {
		this.refParametreEtabServiceImpl = refParametreEtabServiceImpl;
	}

	
	
	
	

}
