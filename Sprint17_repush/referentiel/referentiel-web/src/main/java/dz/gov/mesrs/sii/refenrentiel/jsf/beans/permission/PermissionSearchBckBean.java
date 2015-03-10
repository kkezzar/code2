/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.permission.PermissionSearchBckBean.java] 
 * @author MAKERRI Sofiane on : 16 mars 2014  17:12:50
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.permission;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane  on : 16 mars 2014  17:12:50
 */
/**
 * @author MAKERRI Sofiane  on : 16 mars 2014  17:16:49
 */
/**
 * @author MAKERRI Sofiane  on : 16 mars 2014  17:16:50
 */
@ManagedBean(name = "permissionSearchBckBean")
@ViewScoped
public class PermissionSearchBckBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 16 mars 2014  17:13:18
	 */
	private static final long serialVersionUID = 1L;
	private String searchValue;
    private List<NomenclatureDto> listRoles;
    @ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
    private static final Log log = LogFactory.getLog(PermissionSearchBckBean.class);
    private ResourceBundle bundle;
	
    /**
	 * [PermissionSearchBckBean.PermissionSearchBckBean()] Constructor
	 * @author MAKERRI Sofiane  on : 16 mars 2014  17:12:50	
	 */
	public PermissionSearchBckBean() {
		super();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
	}
	
	@PostConstruct
	public void init() {
		if(searchValue != null) {
		   listRoles = nomenclatureServiceImpl.findGeneric(UtilConstant.NC_ROLE_NAME, searchValue);
		}
	}
	
	/**
	 * [PermissionSearchBckBean.toNew] Method 
	 * @author MAKERRI Sofiane  on : 16 mars 2014  17:16:54
	 * @return
	 */
	public String toNew() {
		return "";
	}


	/**
	 * [PermissionSearchBckBean.searchValue] Getter 
	 * @author MAKERRI Sofiane on : 16 mars 2014  17:15:27
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}
	
	/**
	 * [PermissionSearchBckBean.toEdit] Method 
	 * @author MAKERRI Sofiane  on : 16 mars 2014  17:17:02
	 * @return
	 */
	public String toEdit(NomenclatureDto role) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("role", role);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("readMode", false);
		return "toEdit";
	}
	

	/**
	 * [PermissionSearchBckBean.searchValue] Setter 
	 * @author MAKERRI Sofiane on : 16 mars 2014  17:15:27
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if ((searchValue != null) && (searchValue.equals("null"))) {
			this.searchValue = "";
		} else	this.searchValue = searchValue;
	}
	
	
	/**
	 * [PermissionSearchBckBean.findGeneric] Method 
	 * @author MAKERRI Sofiane  on : 25 mars 2014  16:50:43
	 * @param value
	 * @return
	 */
	public boolean findGeneric(String value) {
		log.info("findgeneric by value " + value);
		FacesMessage msg = new FacesMessage();
		try {
			List<NomenclatureDto> result = nomenclatureServiceImpl
					.findGeneric(UtilConstant.NC_ROLE_NAME, value);
			if (result == null || result.isEmpty()) {
				setListRoles(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));

				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRoles(result);
				return true;

			}
		} catch (RuntimeException e) {
			setListRoles(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return false;
			// throw e;
		}
		return false;

	}


	/**
	 * [PermissionSearchBckBean.listRoles] Getter 
	 * @author MAKERRI Sofiane on : 16 mars 2014  17:19:39
	 * @return the listRoles
	 */
	public List<NomenclatureDto> getListRoles() {
		return listRoles;
	}


	/**
	 * [PermissionSearchBckBean.listRoles] Setter 
	 * @author MAKERRI Sofiane on : 16 mars 2014  17:19:39
	 * @param listRoles the listRoles to set
	 */
	public void setListRoles(List<NomenclatureDto> listRoles) {
		this.listRoles = listRoles;
	}

	/**
	 * [PermissionSearchBckBean.nomenclatureServiceImpl] Getter 
	 * @author MAKERRI Sofiane on : 16 mars 2014  17:37:01
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	/**
	 * [PermissionSearchBckBean.nomenclatureServiceImpl] Setter 
	 * @author MAKERRI Sofiane on : 16 mars 2014  17:37:01
	 * @param nomenclatureServiceImpl the nomenclatureServiceImpl to set
	 */
	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	/**
	 * [PermissionSearchBckBean.bundle] Getter 
	 * @author MAKERRI Sofiane on : 16 mars 2014  17:53:35
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [PermissionSearchBckBean.bundle] Setter 
	 * @author MAKERRI Sofiane on : 16 mars 2014  17:53:35
	 * @param bundle the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}
	
	
	
	

}
