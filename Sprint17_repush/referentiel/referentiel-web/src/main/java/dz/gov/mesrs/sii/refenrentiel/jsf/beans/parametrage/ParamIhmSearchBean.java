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
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEntityDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFileConfigDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefEntityService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;

/**
 * @author jbeldi
 * 
 */
/**
 * @author BELDI Jamel  on : 23 mars 2014  11:14:32
 */
@ManagedBean(name = "paramIhmSearchBean")
@RequestScoped
public class ParamIhmSearchBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ParamIhmSearchBean.class);
	@ManagedProperty("#{param.idDomain}")
	private String idDomain;
	@ManagedProperty("#{param.idEntity}")
	private String idEntity;
	@ManagedProperty("#{param.idLanguage}")
	private String idLanguage;
	
	private List<RefFileConfigDto> listRefFileConfigDto;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	@ManagedProperty(value = "#{refParametrageServiceImpl}")
	private RefParametrageService refParametrageServiceImpl;
	@ManagedProperty(value = "#{refEntityServiceImpl}")
	private RefEntityService refEntityServiceImpl;
	@ManagedProperty("#{flash}")
	private Flash flash;
	private List<SelectItem> listItemEntity;
	
	/**
	 * Constructor of ParamIhmSearchBean
	 */
	public ParamIhmSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				ParamIhmSearchBean.MESSAGES_FILE_NAME);
		

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
	 * [ParamIhmSearchBean.findAdvanced] Method to Find the list of fileConfig by
	 * the the advanced search
	 * 
	 * @author jbeldi on : 15 janv. 2014 11:17:49
	 */
	public String findResources() {
		FacesMessage msg = new FacesMessage();
		try {
			
			List<RefFileConfigDto> result = refParametrageServiceImpl
					.findResourcesBundle(strToInt(idDomain), strToInt(idEntity), strToInt(idLanguage));
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
		return "paramIhmSearch?faces-redirect=true";
	}

	

	
	/**
	 * [ParamIhmSearchBean.idDomain] Getter 
	 * @author BELDI Jamel on : 20 mars 2014  17:36:45
	 * @return the idDomain
	 */
	public String getIdDomain() {
		return idDomain;
	}

	/**
	 * [ParamIhmSearchBean.idDomain] Setter 
	 * @author BELDI Jamel on : 20 mars 2014  17:36:45
	 * @param idDomain the idDomain to set
	 */
	public void setIdDomain(String idDomain) {
		this.idDomain = idDomain;
	}

	/**
	 * [ParamIhmSearchBean.idEntity] Getter 
	 * @author BELDI Jamel on : 20 mars 2014  17:36:45
	 * @return the idEntity
	 */
	public String getIdEntity() {
		return idEntity;
	}

	/**
	 * [ParamIhmSearchBean.idEntity] Setter 
	 * @author BELDI Jamel on : 20 mars 2014  17:36:45
	 * @param idEntity the idEntity to set
	 */
	public void setIdEntity(String idEntity) {
		this.idEntity = idEntity;
	}

	/**
	 * [ParamIhmSearchBean.idLanguage] Getter 
	 * @author BELDI Jamel on : 20 mars 2014  17:36:45
	 * @return the idLanguage
	 */
	public String getIdLanguage() {
		return idLanguage;
	}

	/**
	 * [ParamIhmSearchBean.idLanguage] Setter 
	 * @author BELDI Jamel on : 20 mars 2014  17:36:45
	 * @param idLanguage the idLanguage to set
	 */
	public void setIdLanguage(String idLanguage) {
		this.idLanguage = idLanguage;
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
	 * [ParamIhmSearchBean.listRefFileConfigDto] Getter
	 * 
	 * @author BELDI Jamel on : 20 Mars 2014 12:45:37
	 * @return the listRefFileConfigDto
	 */
	public List<RefFileConfigDto> getListRefFileConfigDto() {

		return listRefFileConfigDto;
	}

	/**
	 * [ParamIhmSearchBean.listRefFileConfigDto] Setter
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
	 * [ParamIhmSearchBean.refParametrageServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 20 Mars 2014 12:41:32
	 * @return the refParametrageServiceImpl
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	/**
	 * [ParamIhmSearchBean.refParametrageServiceImpl] Setter
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
	 * [ParamIhmSearchBean.listItemEntity] Getter 
	 * @author BELDI Jamel on : 20 mars 2014  18:24:37
	 * @return the listItemEntity
	 */
	public List<SelectItem> getListItemEntity() {
		
		return listItemEntity;
	}

	/**
	 * [ParamIhmSearchBean.refEntityServiceImpl] Getter 
	 * @author BELDI Jamel on : 23 mars 2014  09:29:43
	 * @return the refEntityServiceImpl
	 */
	public RefEntityService getRefEntityServiceImpl() {
		return refEntityServiceImpl;
	}

	/**
	 * [ParamIhmSearchBean.refEntityServiceImpl] Setter 
	 * @author BELDI Jamel on : 23 mars 2014  09:29:43
	 * @param refEntityServiceImpl the refEntityServiceImpl to set
	 */
	public void setRefEntityServiceImpl(RefEntityService refEntityServiceImpl) {
		this.refEntityServiceImpl = refEntityServiceImpl;
	}

	/**
	 * [ParamIhmSearchBean.listItemEntity] Setter 
	 * @author BELDI Jamel on : 20 mars 2014  18:24:37
	 * @param listItemEntity the listItemEntity to set
	 */
	public void setListItemEntity(List<SelectItem> listItemEntity) {
		this.listItemEntity = listItemEntity;
	}


	

	
	/**
	 * [ParamIhmSearchBean.domainChanged] Method 
	 * @author BELDI Jamel  on : 23 mars 2014  11:14:25
	 */
	public void domainChanged() {
		listItemEntity = new ArrayList<SelectItem>();
		if (idDomain != null ){
		List<RefEntityDto> listEntity = refEntityServiceImpl.findByDomain(strToInt(idDomain));
		for (RefEntityDto refEntityDto : listEntity) {
			SelectItem selectItem = new SelectItem(refEntityDto.getId(),
					refEntityDto.getName());
			listItemEntity.add(selectItem);
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
					.findResourcesBundle(strToInt(idDomain), strToInt(idEntity), strToInt(idLanguage));
			
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
