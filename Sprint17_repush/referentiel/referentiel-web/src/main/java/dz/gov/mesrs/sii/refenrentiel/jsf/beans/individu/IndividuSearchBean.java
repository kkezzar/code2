/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.individu.IndividuSearchBean.java] 
 * @author MAKERRI Sofiane on : 6 avr. 2014  17:24:58
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.individu;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 6 avr. 2014 17:24:58
 */
@ManagedBean(name = "individuSearchBean")
@ViewScoped
public class IndividuSearchBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 17:25:20
	 */
	private static final long serialVersionUID = 1L;
	private List<RefIndividuDto> listRefIndividuDto;
	private LazyDataModel<RefIndividuDto> individuModel;
	private RefIndividuDto searchDto;
	private ResourceBundle bundle;
	private ResourceBundle bundleIndividu;
//	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuServiceImpl;

	/**
	 * [IndividuSearchBean.IndividuSearchBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 17:24:58
	 */
	public IndividuSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		bundleIndividu = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.INDIVIDU_MESSAGES_FILE_NAME);
		searchDto = new RefIndividuDto();

	}

	@PostConstruct
	public void init() {
		if (searchValue != null) {
			listRefIndividuDto = refIndividuServiceImpl
					.findGeneric(searchValue);
		}
	}

	/**
	 * [IndividuSearchBean.listRefIndividuDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 17:27:49
	 * @return the listRefIndividuDto
	 */
	public List<RefIndividuDto> getListRefIndividuDto() {
		return listRefIndividuDto;
	}

	/**
	 * [IndividuSearchBean.listRefIndividuDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 17:27:49
	 * @param listRefIndividuDto
	 *            the listRefIndividuDto to set
	 */
	public void setListRefIndividuDto(List<RefIndividuDto> listRefIndividuDto) {
		this.listRefIndividuDto = listRefIndividuDto;
	}

	/**
	 * [IndividuSearchBean.searchDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 17:27:49
	 * @return the searchDto
	 */
	public RefIndividuDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [IndividuSearchBean.searchDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 17:27:49
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(RefIndividuDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * [IndividuSearchBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 17:27:49
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [IndividuSearchBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 17:27:49
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
	 * [IndividuSearchBean.refIndividuServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 17:28:46
	 * @return the refIndividuServiceImpl
	 */
	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}

	/**
	 * [IndividuSearchBean.refIndividuServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 17:28:46
	 * @param refIndividuServiceImpl
	 *            the refIndividuServiceImpl to set
	 */
	public void setRefIndividuServiceImpl(
			RefIndividuService refIndividuServiceImpl) {
		this.refIndividuServiceImpl = refIndividuServiceImpl;
	}

	/**
	 * [IndividuSearchBean.findGeneric] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 17:29:35
	 */
	public void findGeneric() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefIndividuDto> result = refIndividuServiceImpl
					.findGeneric(searchValue);
			if (result == null || result.isEmpty()) {
				setListRefIndividuDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefIndividuDto(result);

			}
		} catch (Exception e) {
			setListRefIndividuDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}
	
	public void find(){
	    individuModel  = new LazyDataModel<RefIndividuDto>() {
		private static final long serialVersionUID = -3025574151722364485L;

		@Override
		public List<RefIndividuDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, String> filters) {
		    Boolean descending = null;
		    if(sortOrder != null){
			if(sortOrder.equals(SortOrder.DESCENDING)){
			    descending  = Boolean.TRUE;    
			}else{
			    descending  = Boolean.FALSE;
			}
			
		    }
		    individuModel.setRowCount(refIndividuServiceImpl.countByExample(searchDto));
		    List<RefIndividuDto> dtos = refIndividuServiceImpl.findByExample(searchDto, sortField, pageSize, first, descending);
		    return dtos;
		}
	    };
//	    searchDto = new RefIndividuDto();
	    
	}
	

	/**
	 * [IndividuSearchBean.toDetails] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 17:30:45
	 * @return
	 */
	public String toDetails(RefIndividuDto current) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("refIndividuDto", current);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("readMode", true);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("entity", "individu");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("idEntity", current.getId());
		return "toDetails";
	}

	/**
	 * [IndividuSearchBean.toNew] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 17:30:49
	 * @return
	 */
	public String toNew() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("readMode", false);
		return "toNew";
	}

	/**
	 * [IndividuSearchBean.toEdit] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 17:30:51
	 * @return
	 */
	public String toEdit(RefIndividuDto current) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("refIndividuDto", current);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("entity", "individu");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("idEntity", current.getId());
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("readMode", false);
		return "toEdit";
	}

	/**
	 * [IndividuSearchBean.findAdvanced] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 18:32:42
	 */
	public void findAdvanced() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefIndividuDto> result = refIndividuServiceImpl
					.findAdvanced(searchDto);

			if (result == null || result.isEmpty()) {
				setListRefIndividuDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefIndividuDto(result);// listRefContratDto = result;

			}
		} catch (Exception e) {
			setListRefIndividuDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

        public LazyDataModel<RefIndividuDto> getIndividuModel() {
    	return individuModel;
        }
    
        public void setIndividuModel(LazyDataModel<RefIndividuDto> individuModel) {
    	this.individuModel = individuModel;
        }
}
