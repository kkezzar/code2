/**
 * 
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.parametrage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefConfigurationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefConfigurationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFileConfigDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefConfigurationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefConfigurationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;

/**
 * @author jbeldi
 * 
 */
/**
 * @author BELDI Jamel  on : 23 mars 2014  11:14:32
 */
@ManagedBean(name = "paramConfigSearchBean")
@RequestScoped
public class ParamConfigSearchBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ParamConfigSearchBean.class);
	@ManagedProperty("#{param.idDomain}")
	private String idDomain;
	@ManagedProperty("#{param.idConfig}")
	private String idConfig;
	
	
	private List<RefFileConfigDto> listRefFileConfigDto;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	@ManagedProperty(value = "#{refParametrageServiceImpl}")
	private RefParametrageService refParametrageServiceImpl;
	@ManagedProperty(value = "#{refConfigurationServiceImpl}")
	private RefConfigurationService refConfigurationServiceImpl;
	@ManagedProperty("#{flash}")
	private Flash flash;
	private List<SelectItem> listItemConfig;
	
	/**
	 * Constructor of ParamConfigSearchBean
	 */
	public ParamConfigSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				ParamConfigSearchBean.MESSAGES_FILE_NAME);
		

	}

	@PostConstruct
	public void init() {
		log.info("******************************@PostConstruct: ");
		domainChanged();
		pullValuesFromFlash(null);
	}

	/**
	 * System event called before view rendering used to pull values from flash
	 * and set to bean properties
	 * 
	 * @param e
	 */
	public void pullValuesFromFlash(ComponentSystemEvent e) {

		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		listRefFileConfigDto = (List<RefFileConfigDto>) flash
				.get("listRefFileConfigDto");

		flash.keep("listRefFileConfigDto");
		if(listRefFileConfigDto== null){
			listRefFileConfigDto=loadFiles();
		}
	
		log.info("******************************pullValuesFromFlash: "
				+ listRefFileConfigDto);

	}
	
	
	
	

	


	/**
	 * [ParamConfigSearchBean.findAdvanced] Method to Find the list of fileConfig by
	 * the the advanced search
	 * 
	 * @author jbeldi on : 15 janv. 2014 11:17:49
	 */
	public String findResources() {
		FacesMessage msg = new FacesMessage();
		try {
			
			List<RefFileConfigDto> result = refParametrageServiceImpl.findResourcesConfig(strToInt(idDomain), strToInt(idConfig));
					
			if (result == null || result.isEmpty()) {
				flash.put("listRefFileConfigDto", null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				flash.setKeepMessages(true);
			} else {
				flash.put("listRefFileConfigDto", result);
				flash.keep("listRefFileConfigDto");
				pullValuesFromFlash(null);
			}
		} catch (Exception e) {
			flash.put("listRefFileConfigDto", null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			flash.setKeepMessages(true);
		}
		return "paramConfigSearch?faces-redirect=true";
	}

	

	
	/**
	 * [ParamConfigSearchBean.idDomain] Getter 
	 * @author BELDI Jamel on : 20 mars 2014  17:36:45
	 * @return the idDomain
	 */
	public String getIdDomain() {
		return idDomain;
	}

	/**
	 * [ParamConfigSearchBean.idDomain] Setter 
	 * @author BELDI Jamel on : 20 mars 2014  17:36:45
	 * @param idDomain the idDomain to set
	 */
	public void setIdDomain(String idDomain) {
		this.idDomain = idDomain;
	}

	

	

	
	/**
	 * [ParamConfigSearchBean.idConfig] Getter 
	 * @author BELDI Jamel on : 25 mars 2014  11:06:24
	 * @return the idConfig
	 */
	public String getIdConfig() {
		return idConfig;
	}

	/**
	 * [ParamConfigSearchBean.idConfig] Setter 
	 * @author BELDI Jamel on : 25 mars 2014  11:06:24
	 * @param idConfig the idConfig to set
	 */
	public void setIdConfig(String idConfig) {
		this.idConfig = idConfig;
	}

	/**
	 * 
	 * navigate to edit fileConfig page
	 * 
	 * @return
	 */
	public String toEdit() {
		 HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			        String idf = request.getParameter("idf");
	        log.info("----------------------------IDF--------------"+idf);
			return "paramEdit?faces-redirect=true&idf="+idf;
	
	}
	
	
	

	/**
	 * [ParamConfigSearchBean.listRefFileConfigDto] Getter
	 * 
	 * @author BELDI Jamel on : 20 Mars 2014 12:45:37
	 * @return the listRefFileConfigDto
	 */
	public List<RefFileConfigDto> getListRefFileConfigDto() {

		return listRefFileConfigDto;
	}

	/**
	 * [ParamConfigSearchBean.listRefFileConfigDto] Setter
	 * 
	 * @author BELDI Jamel on : 20 Mars 2014 12:45:37
	 * @param listRefFileConfigDto
	 *            the listRefFileConfigDto to set
	 */
	public void setListRefFileConfigDto(List<RefFileConfigDto> listRefFileConfigDto) {
		this.listRefFileConfigDto = listRefFileConfigDto;
	}

	
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	public Flash getFlash() {
		return flash;
	}

	/**
	 * [ParamConfigSearchBean.refParametrageServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 20 Mars 2014 12:41:32
	 * @return the refParametrageServiceImpl
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	/**
	 * [ParamConfigSearchBean.refParametrageServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 20 Mars 2014 12:41:32
	 * @param refParametrageServiceImpl
	 *            the refParametrageServiceImpl to set
	 */
	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}

	

	/**
	 * [ParamConfigSearchBean.listItemConfiguration] Getter 
	 * @author BELDI Jamel on : 20 mars 2014  18:24:37
	 * @return the listItemConfiguration
	 */
	public List<SelectItem> getListItemConfig() {
		
		return listItemConfig;
	}

	/**
	 * [ParamConfigSearchBean.refConfigurationServiceImpl] Getter 
	 * @author BELDI Jamel on : 23 mars 2014  09:29:43
	 * @return the refConfigurationServiceImpl
	 */
	public RefConfigurationService getRefConfigurationServiceImpl() {
		return refConfigurationServiceImpl;
	}

	/**
	 * [ParamConfigSearchBean.refConfigurationServiceImpl] Setter 
	 * @author BELDI Jamel on : 23 mars 2014  09:29:43
	 * @param refConfigurationServiceImpl the refConfigurationServiceImpl to set
	 */
	public void setRefConfigurationServiceImpl(RefConfigurationService refConfigurationServiceImpl) {
		this.refConfigurationServiceImpl = refConfigurationServiceImpl;
	}

	/**
	 * [ParamConfigSearchBean.listItemConfiguration] Setter 
	 * @author BELDI Jamel on : 20 mars 2014  18:24:37
	 * @param listItemConfiguration the listItemConfiguration to set
	 */
	public void setListItemConfig(List<SelectItem> listItemConfig) {
		this.listItemConfig = listItemConfig;
	}


	

	
	/**
	 * [ParamConfigSearchBean.domainChanged] Method 
	 * @author BELDI Jamel  on : 23 mars 2014  11:14:25
	 */
	public void domainChanged() {
		listItemConfig = new ArrayList<SelectItem>();
		if (idDomain != null ){
		List<RefConfigurationDto> listConfiguration = refConfigurationServiceImpl.findByDomain(strToInt(idDomain));
		for (RefConfigurationDto refConfigurationDto : listConfiguration) {
			SelectItem selectItem = new SelectItem(refConfigurationDto.getId(),
					refConfigurationDto.getName());
			listItemConfig.add(selectItem);
		}
		}
	}


	
	
	public static Integer strToInt(String str) {
		try {
			if (str!=null){
			return Integer.parseInt(str);
			}
			
			
		} catch (NumberFormatException e) {
			
		}
		return null;
	}
	
	private  List<RefFileConfigDto> loadFiles(){
		FacesMessage msg = new FacesMessage();
		List<RefFileConfigDto> result = null;
		try {
			
			 result = refParametrageServiceImpl
					.findResourcesConfig(strToInt(idDomain), strToInt(idConfig));
			
		} catch (Exception e) {
			flash.put("listRefFileConfigDto", null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			flash.setKeepMessages(true);
		}
		return result;
	}
	
}
