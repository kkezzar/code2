/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.nomenclature.NcListMgrBean.java] 
 * @author MAKERRI Sofiane on : 21 janv. 2014  12:07:28
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.nomenclature;

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

import dz.gov.mesrs.sii.referentiel.business.model.dto.NcNamesDto;
import dz.gov.mesrs.sii.referentiel.business.service.NcNamesService;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 21 janv. 2014 12:07:28
 */
@ManagedBean(name = "ncNamesSearchBckBean")
@RequestScoped
public class NcNamesSearchBckBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 12:07:54
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(NcNamesSearchBckBean.class);
	@ManagedProperty(value = "#{ncNamesServiceImpl}")
	private NcNamesService ncNamesServiceImpl;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	private List<NcNamesDto> listNcNamesDto;
	private ResourceBundle bundle;
	private ResourceBundle ncBundle;
	private NcNamesDto ncNamesDto;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	

	/**
	 * [NcListMgrBean.NcListMgrBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 12:07:28
	 */
	public NcNamesSearchBckBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		ncBundle = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.NC_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		if (ncNamesServiceImpl != null) {
			listNcNamesDto = ncNamesServiceImpl.findGeneric(this.searchValue);
			//listNcNamesDto = ncNamesServiceImpl.findAll();
		}

	}

	/**
	 * [NcNamesSearchBckBean.ncNamesServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:37:52
	 * @return the ncNamesService
	 */
	public NcNamesService getNcNamesServiceImpl() {
		return ncNamesServiceImpl;
	}

	/**
	 * [NcNamesSearchBckBean.ncNamesServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:37:52
	 * @param ncNamesService
	 *            the ncNamesService to set
	 */
	public void setNcNamesServiceImpl(NcNamesService ncNamesServiceImpl) {
		this.ncNamesServiceImpl = ncNamesServiceImpl;
	}

	/**
	 * [NcNamesSearchBckBean.listNcNamesDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:38:50
	 * @return the listNcNamesDto
	 */
	public List<NcNamesDto> getListNcNamesDto() {
		return listNcNamesDto;
	}

	/**
	 * [NcNamesSearchBckBean.listNcNamesDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:38:50
	 * @param listNcNamesDto
	 *            the listNcNamesDto to set
	 */
	public void setListNcNamesDto(List<NcNamesDto> listNcNamesDto) {
		this.listNcNamesDto = listNcNamesDto;
	}

	/**
	 * [NcListMgrBean.bundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 12:12:48
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [NcListMgrBean.bundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 12:12:48
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [NcListMgrBean.toNew] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 14:54:45
	 * @return string url to navigate to new page
	 */
	public String toNew() {
		return "toNew";
	}

	/**
	 * [NcListMgrBean.toEdit] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 14:54:51
	 * @return string url to navigate to edit page
	 */
	public String toEdit() {
		return "toEdit";
	}

	public String toDetails() {
		return "toDetails";
	}

	/**
	 * [NcListMgrBean.findGeneric] Method find all entry with
	 * full-text(searching on all columns)
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 14:54:57
	 * 
	 */
	public boolean findGeneric(String value) {
		log.info("findgeneric by value " + value);
		FacesMessage msg = new FacesMessage();
		try {
			List<NcNamesDto> result = ncNamesServiceImpl
					.findGeneric(value);
			if (result == null || result.isEmpty()) {
				setListNcNamesDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));

				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListNcNamesDto(result);
				return true;

			}
		} catch (RuntimeException e) {
			setListNcNamesDto(null);
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
	 * [NcNamesSearchBckBean.ncNamesDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:40:13
	 * @return the ncNamesDto
	 */
	public NcNamesDto getNcNamesDto() {
		return ncNamesDto;
	}

	/**
	 * [NcNamesSearchBckBean.ncNamesDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:40:13
	 * @param ncNamesDto
	 *            the ncNamesDto to set
	 */
	public void setNcNamesDto(NcNamesDto ncNamesDto) {
		this.ncNamesDto = ncNamesDto;
	}

	
	/**
	 * [NcNamesSearchBckBean.ncBundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 15:45:51
	 * @return the ncBundle
	 */
	public ResourceBundle getNcBundle() {
		return ncBundle;
	}

	/**
	 * [NcNamesSearchBckBean.ncBundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 15:45:51
	 * @param ncBundle
	 *            the ncBundle to set
	 */
	public void setNcBundle(ResourceBundle ncBundle) {
		this.ncBundle = ncBundle;
	}

	/**
	 * [NcNamesSearchBckBean.nomenclatureServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 16:55:23
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	/**
	 * [NcNamesSearchBckBean.nomenclatureServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 16:55:23
	 * @param nomenclatureServiceImpl
	 *            the nomenclatureServiceImpl to set
	 */
	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	/**
	 * [NcNamesSearchBckBean.delete] Method delete NcNamesDto entity if this
	 * entity does not used
	 * 
	 * @author MAKERRI Sofiane on : 23 janv. 2014 10:06:09
	 * @param ncNamesDto
	 */
	public void delete(NcNamesDto ncNamesDto) {
		FacesMessage msg = new FacesMessage();
		try {
			
			boolean existInNc = nomenclatureServiceImpl.existInNc(ncNamesDto
					.getNomNomenclature());
			if (existInNc) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ ncBundle.getString("nc_error_is_already_used"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}

			ncNamesServiceImpl.remove(ncNamesDto);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundle.getString("msg_success") + ": "
					+ bundle.getString("msg_success_suppression"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			findGeneric(this.searchValue);
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
	}

	/**
	 * [NcNamesSearchBckBean.searchValue] Getter 
	 * @author MAKERRI Sofiane on : 2 févr. 2014  15:37:16
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [NcNamesSearchBckBean.searchValue] Setter 
	 * @author MAKERRI Sofiane on : 2 févr. 2014  15:37:16
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if ((searchValue != null) && (searchValue.equals("null"))) {
			this.searchValue = "";
		} else {
				this.searchValue = searchValue;	
		}
	}

	
}
