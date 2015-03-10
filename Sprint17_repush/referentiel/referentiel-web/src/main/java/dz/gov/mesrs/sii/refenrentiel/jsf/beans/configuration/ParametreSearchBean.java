/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.configuration.ConfigurationSearchBean.java] 
 * @author MAKERRI Sofiane on : 22 avr. 2014  12:55:39
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

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParametreDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:55:39
 */
/**
 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:57:52
 */
/**
 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:57:54
 */
@ManagedBean(name = "parametreSearchBean")
@RequestScoped
public class ParametreSearchBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:55:51
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	private ResourceBundle bundle;
	private ResourceBundle configBundle;
	private List<RefParametreDto > listRefParametreDto;
	@ManagedProperty(value = "#{refParametreServiceImpl}")
	private RefParametreService refParametreServiceImpl;
	
	//private static final Log log = LogFactory.getLog(ConfigurationSearchBean.class);

	/**
	 * [ConfigurationSearchBean.ConfigurationSearchBean()] Constructor
	 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:55:39	
	 */
	public ParametreSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		configBundle = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.CONFIGURATION_MESSAGES_FILE_NAME);
	}
	
	@PostConstruct
	public void init() {
		if (searchValue != null) {
			listRefParametreDto = refParametreServiceImpl.findGeneric(searchValue);
		}
	}

	/**
	 * [ConfigurationSearchBean.searchValue] Getter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:57:10
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [ConfigurationSearchBean.searchValue] Setter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:57:10
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if (searchValue != null && searchValue.equals("null")) {
			this.searchValue = null;
		} else
			this.searchValue = searchValue;
	}
	
	
	/**
	 * [ConfigurationSearchBean.findGeneric] Method 
	 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:57:27
	 */
	public void findGeneric(String searchValue) {
		FacesMessage msg = new FacesMessage();
		try {
			setSearchValue(searchValue);
			List<RefParametreDto> result = refParametreServiceImpl
					.findGeneric(searchValue);
			if (result == null || result.isEmpty()) {
				setListRefParametreDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefParametreDto(result);

			}
		} catch (Exception e) {
			setListRefParametreDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}
	
	
	/**
	 * [ConfigurationSearchBean.toNew] Method 
	 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:57:46
	 * @return
	 */
	public String toNew() {
		return "toNew";
	}
	
	
	/**
	 * [ConfigurationSearchBean.toEdit] Method 
	 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:57:49
	 * @return
	 */
	public String toEdit() {
		return "toEdit";
	}
	
	/**
	 * [ConfigurationSearchBean.toDetails] Method 
	 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:58:05
	 * @return
	 */
	public String toDetails() {
		return "toDetails";
	}

	
	
	/**
	 * [ParametreSearchBean.listRefParametreDto] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  09:59:13
	 * @return the listRefParametreDto
	 */
	public List<RefParametreDto> getListRefParametreDto() {
		return listRefParametreDto;
	}

	/**
	 * [ParametreSearchBean.listRefParametreDto] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  09:59:13
	 * @param listRefParametreDto the listRefParametreDto to set
	 */
	public void setListRefParametreDto(List<RefParametreDto> listRefParametreDto) {
		this.listRefParametreDto = listRefParametreDto;
	}

	/**
	 * [ConfigurationSearchBean.refParametreServiceImpl] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  09:13:21
	 * @return the refParametreServiceImpl
	 */
	public RefParametreService getRefParametreServiceImpl() {
		return refParametreServiceImpl;
	}

	/**
	 * [ConfigurationSearchBean.refParametreServiceImpl] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  09:13:21
	 * @param refParametreServiceImpl the refParametreServiceImpl to set
	 */
	public void setRefParametreServiceImpl(
			RefParametreService refParametreServiceImpl) {
		this.refParametreServiceImpl = refParametreServiceImpl;
	}

	/**
	 * [ConfigurationSearchBean.delete] Method 
	 * @author MAKERRI Sofiane  on : 22 avr. 2014  16:25:24
	 * @param _refEtablissementDto
	 */
	public void delete(RefParametreDto _refParametreConfigurationDto) {
		FacesMessage msg = new FacesMessage();
		try {
			
			if(_refParametreConfigurationDto != null && _refParametreConfigurationDto.getId() != null) {
				refParametreServiceImpl.delete(_refParametreConfigurationDto);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundle.getString("msg_success") + ": "
					+ bundle.getString("msg_success_suppression"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			findGeneric(this.searchValue);
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ configBundle.getString("parametrage_configuration_remove_error"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	

}
