/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.etablissement.EtabParameterBean.java] 
 * @author MAKERRI Sofiane on : 28 avr. 2014  09:06:09
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.etablissement;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.RowEditEvent;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParametreEtabDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 28 avr. 2014 09:06:09
 */
@ManagedBean(name = "etabParameterBean")
@RequestScoped
public class EtabParameterBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:06:27
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{param.id}")
	private String id;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty(value = "#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabServiceImpl;
	private List<RefParametreEtabDto> listRefParametreEtabDto;
	private ResourceBundle bundle;
	private ResourceBundle configBundle;
	@ManagedProperty(value = "#{etabParameterCrudBean}")
	private EtabParameterCrudBean etabParameterCrudBean;

	/**
	 * [EtabParameterBean.EtabParameterBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:06:09
	 */
	public EtabParameterBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		configBundle = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.CONFIGURATION_MESSAGES_FILE_NAME);
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
		String id = (String) request.getAttribute("identifiant");
		if (id != null) {
			this.id = id;
		}
	}

	@PostConstruct
	public void init() {
		loadParameters();

	}

	/**
	 * [EtabParameterBean.loadParameters] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:12:13
	 */
	public void loadParameters() {
		FacesMessage msg = new FacesMessage();

		try {
			if (id != null) {
				int _id = RefUtilConstant.strToInt(id);
				if (_id != -1) {
					listRefParametreEtabDto = refParametreEtabServiceImpl
							.findAll(_id);
				}
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec")
					+ ": "
					+ configBundle
							.getString("parametrage_configuration_load_error"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [EtabParameterBean.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:20:25
	 * @return the identifiant
	 */
	public String getId() {
		return id;
	}

	/**
	 * [EtabParameterBean.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:20:25
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		if (this.id == null) {
			if (id != null && id.equals("null")) {
				this.id = null;
			} else
				this.id = id;
		}
	}

	/**
	 * [EtabParameterBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:07:14
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [EtabParameterBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:07:14
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
	 * [EtabParameterBean.refParametreEtabServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:07:14
	 * @return the refParametreEtabServiceImpl
	 */
	public RefParametreEtabService getRefParametreEtabServiceImpl() {
		return refParametreEtabServiceImpl;
	}

	/**
	 * [EtabParameterBean.refParametreEtabServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:07:14
	 * @param refParametreEtabServiceImpl
	 *            the refParametreEtabServiceImpl to set
	 */
	public void setRefParametreEtabServiceImpl(
			RefParametreEtabService refParametreEtabServiceImpl) {
		this.refParametreEtabServiceImpl = refParametreEtabServiceImpl;
	}

	/**
	 * [EtabParameterBean.listRefParametreEtabDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:07:14
	 * @return the listRefParametreEtabDto
	 */
	public List<RefParametreEtabDto> getListRefParametreEtabDto() {
		return listRefParametreEtabDto;
	}

	/**
	 * [EtabParameterBean.listRefParametreEtabDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:07:14
	 * @param listRefParametreEtabDto
	 *            the listRefParametreEtabDto to set
	 */
	public void setListRefParametreEtabDto(
			List<RefParametreEtabDto> listRefParametreEtabDto) {
		this.listRefParametreEtabDto = listRefParametreEtabDto;
	}

	/**
	 * [EtabParameterBean.onEdit] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:11:39
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

	/**
	 * [EtabParameterBean.delete] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:11:35
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
				loadParameters();
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
	 * [EtabParameterBean.etabParameterCrudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:17:59
	 * @return the etabParameterCrudBean
	 */
	public EtabParameterCrudBean getEtabParameterCrudBean() {
		return etabParameterCrudBean;
	}

	/**
	 * [EtabParameterBean.etabParameterCrudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:17:59
	 * @param etabParameterCrudBean
	 *            the etabParameterCrudBean to set
	 */
	public void setEtabParameterCrudBean(
			EtabParameterCrudBean etabParameterCrudBean) {
		this.etabParameterCrudBean = etabParameterCrudBean;
	}

	/**
	 * [EtabParameterBean.crud] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 09:18:15
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void crud(boolean createAllow, boolean editAllow, boolean deleteAllow) {
		etabParameterCrudBean.setEditAllow(editAllow);
		etabParameterCrudBean.setCreateAllow(createAllow);
		etabParameterCrudBean.setDeleteAllow(deleteAllow);
	}

}
