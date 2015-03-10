/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.configuration.ParametreEtabSearchBean.java] 
 * @author MAKERRI Sofiane on : 27 avr. 2014  14:25:02
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.configuration;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParametreEtabDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 27 avr. 2014 14:25:02
 */
@ManagedBean(name = "parametreEtabSearchBean")
@RequestScoped
public class ParametreEtabSearchBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:25:23
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty("#{param.idfEtab}")
	private String idfEtab;
	private ResourceBundle bundle;
	private ResourceBundle configBundle;
	private List<RefParametreEtabDto> listRefParametreEtabDto;
	@ManagedProperty(value = "#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabServiceImpl;

	/**
	 * [ParametreEtabSearchBean.ParametreEtabSearchBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:25:02
	 */
	public ParametreEtabSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		configBundle = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.CONFIGURATION_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		if (searchValue != null && idfEtab != null) {
			listRefParametreEtabDto = refParametreEtabServiceImpl.findGeneric(
					Integer.parseInt(idfEtab), searchValue);
		}
	}

	/**
	 * [ParametreEtabSearchBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:27:59
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [ParametreEtabSearchBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:27:59
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if (searchValue != null && searchValue.equals("null")) {
			this.searchValue = null;
		} else
			this.searchValue = searchValue;
	}

	/**
	 * [ParametreEtabSearchBean.listRefParametreEtabDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:27:59
	 * @return the listRefParametreEtabDto
	 */
	public List<RefParametreEtabDto> getListRefParametreEtabDto() {
		return listRefParametreEtabDto;
	}

	/**
	 * [ParametreEtabSearchBean.listRefParametreEtabDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:27:59
	 * @param listRefParametreEtabDto
	 *            the listRefParametreEtabDto to set
	 */
	public void setListRefParametreEtabDto(
			List<RefParametreEtabDto> listRefParametreEtabDto) {
		this.listRefParametreEtabDto = listRefParametreEtabDto;
	}

	/**
	 * [ParametreEtabSearchBean.refParametreEtabServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:27:59
	 * @return the refParametreEtabServiceImpl
	 */
	public RefParametreEtabService getRefParametreEtabServiceImpl() {
		return refParametreEtabServiceImpl;
	}

	/**
	 * [ParametreEtabSearchBean.refParametreEtabServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:27:59
	 * @param refParametreEtabServiceImpl
	 *            the refParametreEtabServiceImpl to set
	 */
	public void setRefParametreEtabServiceImpl(
			RefParametreEtabService refParametreEtabServiceImpl) {
		this.refParametreEtabServiceImpl = refParametreEtabServiceImpl;
	}

	/**
	 * [ParametreEtabSearchBean.findGeneric] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:28:29
	 * @param searchValue
	 */
	public void findGeneric(String searchValue) {
		FacesMessage msg = new FacesMessage();
		try {
			setSearchValue(searchValue);
			List<RefParametreEtabDto> result = null;
			if (idfEtab == null) {
				result = refParametreEtabServiceImpl.findGeneric(null,
						searchValue);
			} else {
				result = refParametreEtabServiceImpl.findGeneric(
						Integer.parseInt(idfEtab), searchValue);
			}

			if (result == null || result.isEmpty()) {
				setListRefParametreEtabDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefParametreEtabDto(result);

			}
		} catch (Exception e) {
			setListRefParametreEtabDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * [ParametreEtabSearchBean.toNew] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:29:07
	 * @return
	 */
	public String toNew() {
		return "toNew";
	}

	/**
	 * [ParametreEtabSearchBean.toEdit] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:29:04
	 * @return
	 */
	public String toEdit() {
		return "toEdit";
	}

	/**
	 * [ParametreEtabSearchBean.toDetails] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:29:02
	 * @return
	 */
	public String toDetails() {
		return "toDetails";
	}

	/**
	 * [ParametreEtabSearchBean.delete] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:38:02
	 * @param _refParametreEtabDto
	 */
	public void delete(RefParametreEtabDto _refParametreEtabDto) {
		FacesMessage msg = new FacesMessage();

		try {

			if (_refParametreEtabDto != null
					&& _refParametreEtabDto.getId() != null) {
				refParametreEtabServiceImpl.delete(_refParametreEtabDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_suppression"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				findGeneric(this.searchValue);
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec")
					+ ": "
					+ configBundle
							.getString("parametrage_configuration_remove_error"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [ParametreEtabSearchBean.idfEtab] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:46:08
	 * @return the idfEtab
	 */
	public String getIdfEtab() {
		return idfEtab;
	}

	/**
	 * [ParametreEtabSearchBean.idfEtab] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:46:08
	 * @param idfEtab
	 *            the idfEtab to set
	 */
	public void setIdfEtab(String idfEtab) {
		if (idfEtab != null && idfEtab.equals("null")) {
			this.idfEtab = null;
		} else
			this.idfEtab = idfEtab;
	}

	/**
	 * [ParametreEtabSearchBean.onEdit] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 16:31:21
	 * @param event
	 */
	public void onEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage();
		// log.info("onEdit:");
		try {
			RefParametreEtabDto element = ((RefParametreEtabDto) event
					.getObject());
			refParametreEtabServiceImpl.update(element);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundle.getString("msg_success") + ": "
					+ bundle.getString("msg_success_modification"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			// log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} finally {
			// loadParams();
		}

	}

}
