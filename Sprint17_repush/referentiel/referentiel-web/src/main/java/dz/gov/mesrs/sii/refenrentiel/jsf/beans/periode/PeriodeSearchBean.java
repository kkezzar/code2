/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.periode.PeriodeSearchBean.java] 
 * @author MAKERRI Sofiane on : 25 mars 2014  16:32:18
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.periode;

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

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPeriodeDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPeriodeService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 25 mars 2014 16:32:18
 */
@ManagedBean(name = "periodeSearchBean")
@RequestScoped
public class PeriodeSearchBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 16:34:36
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	private List<RefPeriodeDto> listRefPeriodeDto;
	@ManagedProperty(value = "#{refPeriodeServiceImpl}")
	private RefPeriodeService refPeriodeServiceImpl;
	@ManagedProperty(value = "#{refFonctionServiceImpl}")
	private RefFonctionService refFonctionServiceImpl;
	private static final Log log = LogFactory.getLog(PeriodeSearchBean.class);
	private ResourceBundle bundle;
	private ResourceBundle pBundle;

	/**
	 * [PeriodeSearchBean.PeriodeSearchBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 16:32:18
	 */
	public PeriodeSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		pBundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.PERIODE_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		if (searchValue != null) {
			listRefPeriodeDto = refPeriodeServiceImpl.findGeneric(searchValue);
		}
	}

	/**
	 * [PeriodeSearchBean.listRefPeriodeDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 16:49:54
	 * @return the listRefPeriodeDto
	 */
	public List<RefPeriodeDto> getListRefPeriodeDto() {
		return listRefPeriodeDto;
	}

	/**
	 * [PeriodeSearchBean.listRefPeriodeDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 16:49:54
	 * @param listRefPeriodeDto
	 *            the listRefPeriodeDto to set
	 */
	public void setListRefPeriodeDto(List<RefPeriodeDto> listRefPeriodeDto) {
		this.listRefPeriodeDto = listRefPeriodeDto;
	}

	/**
	 * [PeriodeSearchBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 16:48:31
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [PeriodeSearchBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 16:48:31
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if ((searchValue != null) && (searchValue.equals("null"))) {
			this.searchValue = "";
		} else
			this.searchValue = searchValue;
	}

	/**
	 * [PeriodeSearchBean.refPeriodeServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 16:48:31
	 * @return the refPeriodeServiceImpl
	 */
	public RefPeriodeService getRefPeriodeServiceImpl() {
		return refPeriodeServiceImpl;
	}

	/**
	 * [PeriodeSearchBean.refPeriodeServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 16:48:31
	 * @param refPeriodeServiceImpl
	 *            the refPeriodeServiceImpl to set
	 */
	public void setRefPeriodeServiceImpl(RefPeriodeService refPeriodeServiceImpl) {
		this.refPeriodeServiceImpl = refPeriodeServiceImpl;
	}

	/**
	 * [PeriodeSearchBean.findGeneric] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 16:50:56
	 * @param value
	 * @return
	 */
	public boolean findGeneric(String value) {
		log.info("findgeneric by value " + value);
		setSearchValue(value);
		FacesMessage msg = new FacesMessage();
		try {
			List<RefPeriodeDto> result = refPeriodeServiceImpl
					.findGeneric(value);
			if (result == null || result.isEmpty()) {
				setListRefPeriodeDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));

				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefPeriodeDto(result);
				return true;

			}
		} catch (RuntimeException e) {
			setListRefPeriodeDto(null);
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
	 * [PeriodeSearchBean.toDetails] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 17:14:32
	 * @return
	 */
	public String toDetails() {
		return "toDetails";
	}

	/**
	 * [PeriodeSearchBean.toNew] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 17:14:48
	 * @return
	 */
	public String toNew() {
		return "toNew";
	}

	/**
	 * [PeriodeSearchBean.toEdit] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 17:15:10
	 * @return
	 */
	public String toEdit() {
		return "toEdit";
	}

	/**
	 * [PeriodeSearchBean.toRemove] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 17:16:01
	 * @param refPeriodeDto
	 */
	public void toRemove(RefPeriodeDto refPeriodeDto) {
		if (refPeriodeDto != null) {
			FacesMessage msg = new FacesMessage();
			try {
				RefFonctionDto refFonctionDto = refFonctionServiceImpl
						.findPeriode(refPeriodeDto.getId());
				if (refFonctionDto != null) {
					//setListRefPeriodeDto(null);
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec")
							+ ": "
							+ pBundle
									.getString("periode_error_delete_periode_is_used_in_function")
							+ " (" + refFonctionDto.getNom().trim()
							+ ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
				refPeriodeServiceImpl.delete(refPeriodeDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_suppression"));

				FacesContext.getCurrentInstance().addMessage(null, msg);
				listRefPeriodeDto = refPeriodeServiceImpl
						.findGeneric(searchValue);
			} catch (RuntimeException e) {
				setListRefPeriodeDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}
		}
	}

	/**
	 * [PeriodeSearchBean.refFonctionServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 avr. 2014 15:05:22
	 * @return the refFonctionServiceImpl
	 */
	public RefFonctionService getRefFonctionServiceImpl() {
		return refFonctionServiceImpl;
	}

	/**
	 * [PeriodeSearchBean.refFonctionServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 avr. 2014 15:05:22
	 * @param refFonctionServiceImpl
	 *            the refFonctionServiceImpl to set
	 */
	public void setRefFonctionServiceImpl(
			RefFonctionService refFonctionServiceImpl) {
		this.refFonctionServiceImpl = refFonctionServiceImpl;
	}

}
