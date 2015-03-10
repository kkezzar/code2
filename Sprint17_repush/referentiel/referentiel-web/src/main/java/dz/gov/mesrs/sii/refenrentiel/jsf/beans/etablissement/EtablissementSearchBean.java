/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.etablissement.EtablissementSearchBean.java] 
 * @author MAKERRI Sofiane on : 10 avr. 2014  12:08:10
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
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 10 avr. 2014 12:08:10
 */
@ManagedBean(name = "etablissementSearchBean")
@RequestScoped
public class EtablissementSearchBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ResourceBundle bundle;
	private ResourceBundle etabBundle;
	private RefEtablissementDto refEtablissementDto;
	private List<RefEtablissementDto> listRefEtablissementDto;
	private RefEtablissementDto searchDto;
	@ManagedProperty(value = "#{refEtablissementServiceImpl}")
	private RefEtablissementService refEtablissementServiceImpl;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty("#{flash}")
	private Flash flash;

	/**
	 * [EtablissementSearchBean.EtablissementSearchBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:08:10
	 */
	public EtablissementSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		etabBundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.ETABLISSEMENT_MESSAGES_FILE_NAME);
		searchDto = new RefEtablissementDto();
	}

	@PostConstruct
	public void init() {
		if (searchValue != null) {
			listRefEtablissementDto = refEtablissementServiceImpl
					.findGeneric(searchValue);
		}
	}

	/**
	 * [EtablissementSearchBean.refEtablissementDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:18:20
	 * @return the refEtablissementDto
	 */
	public RefEtablissementDto getRefEtablissementDto() {
		return refEtablissementDto;
	}

	/**
	 * [EtablissementSearchBean.refEtablissementDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:18:20
	 * @param refEtablissementDto
	 *            the refEtablissementDto to set
	 */
	public void setRefEtablissementDto(RefEtablissementDto refEtablissementDto) {
		this.refEtablissementDto = refEtablissementDto;
	}

	/**
	 * [EtablissementSearchBean.listRefEtablissementDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:18:20
	 * @return the listRefEtablissementDto
	 */
	public List<RefEtablissementDto> getListRefEtablissementDto() {
		return listRefEtablissementDto;
	}

	/**
	 * [EtablissementSearchBean.listRefEtablissementDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:18:20
	 * @param listRefEtablissementDto
	 *            the listRefEtablissementDto to set
	 */
	public void setListRefEtablissementDto(
			List<RefEtablissementDto> listRefEtablissementDto) {
		this.listRefEtablissementDto = listRefEtablissementDto;
	}

	/**
	 * [EtablissementSearchBean.searchDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:18:20
	 * @return the searchDto
	 */
	public RefEtablissementDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [EtablissementSearchBean.searchDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:18:20
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(RefEtablissementDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * [EtablissementSearchBean.refEtablissementServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:18:20
	 * @return the refEtablissementServiceImpl
	 */
	public RefEtablissementService getRefEtablissementServiceImpl() {
		return refEtablissementServiceImpl;
	}

	/**
	 * [EtablissementSearchBean.refEtablissementServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:18:20
	 * @param refEtablissementServiceImpl
	 *            the refEtablissementServiceImpl to set
	 */
	public void setRefEtablissementServiceImpl(
			RefEtablissementService refEtablissementServiceImpl) {
		this.refEtablissementServiceImpl = refEtablissementServiceImpl;
	}

	/**
	 * [EtablissementSearchBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:18:20
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [EtablissementSearchBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:18:20
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
	 * [EtablissementSearchBean.flash] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:18:20
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [EtablissementSearchBean.flash] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:18:20
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [EtablissementSearchBean.findGeneric] Method
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:20:37
	 */
	public void findGeneric(String value) {
		FacesMessage msg = new FacesMessage();
		setSearchValue(value);
		try {
			List<RefEtablissementDto> result = refEtablissementServiceImpl
					.findGeneric(this.searchValue);
			if (result == null || result.isEmpty()) {
				setListRefEtablissementDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefEtablissementDto(result);

			}
		} catch (Exception e) {
			setListRefEtablissementDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * [EtablissementSearchBean.findAdvanced] Method
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:32:57
	 */
	public void findAdvanced() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefEtablissementDto> result = refEtablissementServiceImpl
					.findAdvanced(searchDto);

			if (result == null || result.isEmpty()) {
				setListRefEtablissementDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefEtablissementDto(result);

			}
		} catch (Exception e) {
			setListRefEtablissementDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [EtablissementSearchBean.toNew] Method
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:22:36
	 * @return
	 */
	public String toNew() {
		return "toNew";
	}

	/**
	 * [EtablissementSearchBean.toEdit] Method
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:22:49
	 * @return
	 */
	public String toEdit(Integer id) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
    	request.setAttribute("id", id);
		return "toEdit";
	}

	/**
	 * [EtablissementSearchBean.toDetails] Method
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:23:02
	 * @return
	 */
	public String toDetails(Integer id) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
    	request.setAttribute("id", id);
		return "toDetails";
	}
	
	
	
	/**
	 * [EtablissementSearchBean.delete] Method 
	 * @author MAKERRI Sofiane  on : 22 avr. 2014  16:25:07
	 * @param _refEtablissementDto
	 */
	public void delete(RefEtablissementDto _refEtablissementDto) {
		FacesMessage msg = new FacesMessage();
		try {
			
			if(_refEtablissementDto != null && _refEtablissementDto.getIdentifiant() != null) {
			refEtablissementServiceImpl.delete(_refEtablissementDto);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundle.getString("msg_success") + ": "
					+ bundle.getString("msg_success_suppression"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			findGeneric(this.searchValue);
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ etabBundle.getString("etablissement_remove_error"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

}
