/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.plageadresse.PlageSearchBean.java] 
 * @author MAKERRI Sofiane on : 6 avr. 2014  15:04:37
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.plageadresse;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPlageAdresseDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefPlageAdresseService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane  on : 6 avr. 2014  15:04:37
 */
@ManagedBean(name = "plageSearchBean")
@RequestScoped
public class PlageSearchBean implements Serializable{

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 6 avr. 2014  15:04:56
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	private List<RefPlageAdresseDto > listRefPlageAdresseDto;
	@ManagedProperty(value = "#{refPlageAdresseServiceImpl}")
	private RefPlageAdresseService refPlageAdresseServiceImpl;
	private ResourceBundle bundle;
	private static final Log log = LogFactory.getLog(PlageSearchBean.class);

	/**
	 * [PlageSearchBean.PlageSearchBean()] Constructor
	 * @author MAKERRI Sofiane  on : 6 avr. 2014  15:04:37	
	 */
	public PlageSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		
		
	}
	
	@PostConstruct
	public void init() {
		if (searchValue != null) {
			listRefPlageAdresseDto = refPlageAdresseServiceImpl.findGeneric(searchValue);
		}
	}

	/**
	 * [PlageSearchBean.searchValue] Getter 
	 * @author MAKERRI Sofiane on : 6 avr. 2014  15:06:16
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [PlageSearchBean.searchValue] Setter 
	 * @author MAKERRI Sofiane on : 6 avr. 2014  15:06:16
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if (searchValue != null && searchValue.equals("null")) {
			this.searchValue = null;
		} else
			this.searchValue = searchValue;
	}

	/**
	 * [PlageSearchBean.listRefPlageAdresseDto] Getter 
	 * @author MAKERRI Sofiane on : 6 avr. 2014  15:06:16
	 * @return the listRefPlageAdresseDto
	 */
	public List<RefPlageAdresseDto> getListRefPlageAdresseDto() {
		return listRefPlageAdresseDto;
	}

	/**
	 * [PlageSearchBean.listRefPlageAdresseDto] Setter 
	 * @author MAKERRI Sofiane on : 6 avr. 2014  15:06:16
	 * @param listRefPlageAdresseDto the listRefPlageAdresseDto to set
	 */
	public void setListRefPlageAdresseDto(
			List<RefPlageAdresseDto> listRefPlageAdresseDto) {
		this.listRefPlageAdresseDto = listRefPlageAdresseDto;
	}

	/**
	 * [PlageSearchBean.refPlageAdresseServiceImpl] Getter 
	 * @author MAKERRI Sofiane on : 6 avr. 2014  15:06:16
	 * @return the refPlageAdresseServiceImpl
	 */
	public RefPlageAdresseService getRefPlageAdresseServiceImpl() {
		return refPlageAdresseServiceImpl;
	}

	/**
	 * [PlageSearchBean.refPlageAdresseServiceImpl] Setter 
	 * @author MAKERRI Sofiane on : 6 avr. 2014  15:06:16
	 * @param refPlageAdresseServiceImpl the refPlageAdresseServiceImpl to set
	 */
	public void setRefPlageAdresseServiceImpl(
			RefPlageAdresseService refPlageAdresseServiceImpl) {
		this.refPlageAdresseServiceImpl = refPlageAdresseServiceImpl;
	}
	
	
	
	/**
	 * [PlageSearchBean.findGeneric] Method 
	 * @author MAKERRI Sofiane  on : 6 avr. 2014  15:07:23
	 */
	public void findGeneric() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefPlageAdresseDto> result = refPlageAdresseServiceImpl
					.findGeneric(SessionValues.getIdEtablissement(), searchValue);
			if (result == null || result.isEmpty()) {
				setListRefPlageAdresseDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefPlageAdresseDto(result);

			}
		} catch (Exception e) {
			setListRefPlageAdresseDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}
	
	
	/**
	 * [PlageSearchBean.toNew] Method 
	 * @author MAKERRI Sofiane  on : 6 avr. 2014  15:11:35
	 * @return
	 */
	public String toNew() {
		return "toNew";
	}
	
	
	/**
	 * [PlageSearchBean.toEdit] Method 
	 * @author MAKERRI Sofiane  on : 6 avr. 2014  15:11:49
	 * @return
	 */
	public String toEdit() {
		return "toEdit";
	}
	
	/**
	 * [PlageSearchBean.toDetails] Method 
	 * @author MAKERRI Sofiane  on : 6 avr. 2014  15:13:19
	 * @return
	 */
	public String toDetails() {
		return "toDetails";
	}
	
	
	/**
	 * [PlageSearchBean.toRemove] Method 
	 * @author MAKERRI Sofiane  on : 6 avr. 2014  15:15:23
	 * @param selectedRefPlageAdresseDto
	 */
	public void toRemove(RefPlageAdresseDto selectedRefPlageAdresseDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedRefPlageAdresseDto != null)
				&& (selectedRefPlageAdresseDto.getId() != 0)) {
			try {
				log.info("deleting Plage adresse id = "
						+ selectedRefPlageAdresseDto.getIdentifiant());
				refPlageAdresseServiceImpl.delete(selectedRefPlageAdresseDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_suppression"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				if (searchValue != null) {
					listRefPlageAdresseDto = refPlageAdresseServiceImpl.findGeneric(searchValue);
				}
			} catch (Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}

	}
	
	


}
