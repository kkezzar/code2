/**
 * 
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.parametrage;

import java.io.FileNotFoundException;
import java.io.IOException;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.RowEditEvent;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFileConfigDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParamDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;


/**
 * @author jbeldi
 * 
 */
/**
 * @author BELDI Jamel  on : 23 mars 2014  11:14:32
 */
@ManagedBean(name = "paramMgrBean")
@RequestScoped
public class ParamMgrBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ParamMgrBean.class);


	
	private List<RefFileConfigDto> listRefFileConfigDto;
	private List<RefParamDto> listParamOfFile;
	private RefFileConfigDto refFileConfigDto;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";

	@ManagedProperty(value = "#{refParametrageServiceImpl}")
	private RefParametrageService refParametrageServiceImpl;

	@ManagedProperty("#{param.idf}")
	private String idf;
	@ManagedProperty("#{flash}")
	private Flash flash;
	private List<RefParamDto> filteredParams;
	/**
	 * Constructor of EvenementMgrBean
	 */
	public ParamMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				ParamMgrBean.MESSAGES_FILE_NAME);
		

	}

	@PostConstruct
	public void init() {
		log.info("******************************@PostConstruct: ");
		 if(idf!=null && !idf.equals("null") ){
	        	refFileConfigDto = refParametrageServiceImpl.findById(strToInt(idf));
	        	pullValuesFromFlash(null);
	        	if(listParamOfFile==null|| listParamOfFile.isEmpty()){
	        		loadParams();
	        	}
	        	
	        }else{
	        	pullValuesFromFlash(null);
	        	if(listParamOfFile==null){
	        		loadParams();
	        	}
	        	
	        
	        }
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
		if(refFileConfigDto==null){
			refFileConfigDto = new RefFileConfigDto();
		}
		listParamOfFile = (List<RefParamDto>) flash
				.get("listParamOfFile");	
		flash.keep("listParamOfFile");
		log.info("******************************pullValuesFromFlash refFileConfigDto: "
				+ refFileConfigDto);
		log.info("******************************pullValuesFromFlash listParamOfFile: "
				+ listParamOfFile);

	}

	

	private void loadParams() {
		if (refFileConfigDto != null) {
			try {
				listParamOfFile = refParametrageServiceImpl.viewParams(refFileConfigDto);
				log.info(listParamOfFile.size());
				
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				log.info(e.getMessage());
			}
		} else {
			listParamOfFile = new ArrayList<RefParamDto>();
			log.info(listParamOfFile.size());
		}
	}
	
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	public Flash getFlash() {
		return flash;
	}

	/**
	 * [EvenementMgrBean.refParametrageServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:41:32
	 * @return the refParametrageServiceImpl
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	/**
	 * [EvenementMgrBean.refParametrageServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:41:32
	 * @param refParametrageServiceImpl
	 *            the refParametrageServiceImpl to set
	 */
	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}

	

	

	

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String toSearch() {
		if(refFileConfigDto!=null && refFileConfigDto.getType()!=null && refFileConfigDto.getType().equals("IHM")){
			return "toSearchParamIhm";
		}else if(refFileConfigDto!=null && refFileConfigDto.getType()!=null && refFileConfigDto.getType().equals("Configuration")){
			return "toSearchParamConfig";
		}
		else {
			return null;
		}
		
	}

	
	
	
	
	
	
	/**
	 * [EvenementMgrBean.listRefFileConfigDto] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:45:37
	 * @return the listRefFileConfigDto
	 */
	public List<RefFileConfigDto> getListRefFileConfigDto() {

		return listRefFileConfigDto;
	}

	/**
	 * [EvenementMgrBean.listRefFileConfigDto] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:45:37
	 * @param listRefFileConfigDto
	 *            the listRefFileConfigDto to set
	 */
	public void setListRefFileConfigDto(List<RefFileConfigDto> listRefFileConfigDto) {
		this.listRefFileConfigDto = listRefFileConfigDto;
	}

	/**
	 * [EvenementMgrBean.refFileConfigDto] Getter
	 * 
	 * @author BELDI Jamel on : 16 f�vr. 2014 18:06:47
	 * @return the refFileConfigDto
	 */
	public RefFileConfigDto getRefFileConfigDto() {
		return refFileConfigDto;
	}

	/**
	 * [EvenementMgrBean.refFileConfigDto] Setter
	 * 
	 * @author BELDI Jamel on : 16 f�vr. 2014 18:06:47
	 * @param refFileConfigDto
	 *            the refFileConfigDto to set
	 */
	public void setRefFileConfigDto(RefFileConfigDto refFileConfigDto) {
		this.refFileConfigDto = refFileConfigDto;
	}


	/**
	 * [EvenementMgrBean.listParamOfFile] Getter
	 * 
	 * @author BELDI Jamel on : 19 f�vr. 2014 16:04:57
	 * @return the listParamOfFile
	 */
	public List<RefParamDto> getListParamOfFile() {
		//loadParams();
		return listParamOfFile;
	}

	/**
	 * [EvenementMgrBean.listParamOfFile] Setter
	 * 
	 * @author BELDI Jamel on : 19 f�vr. 2014 16:04:57
	 * @param listParamOfFile
	 *            the listParamOfFile to set
	 */
	public void setListParamOfFile(List<RefParamDto> listParamOfFile) {
		this.listParamOfFile = listParamOfFile;
	}

	public void onEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage();
		log.info("onEdit:");
		try {
		RefParamDto element = ((RefParamDto) event.getObject());
			refParametrageServiceImpl.updateParamValue(element);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundle.getString("msg_success") + ": "
					+ bundle.getString("msg_success_modification"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		 } catch (FileNotFoundException e) {
			 log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		catch (IOException e) {
			
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	 finally{
		loadParams();
	 }
		
	}

	public void onCancel(RowEditEvent event) {
		
		log.info("onCancel:");
		
	}

	
	public void initDefaultConfig() {
		FacesMessage msg = new FacesMessage();
		log.info("initDefaultConfig:");
		try {
			refParametrageServiceImpl.initDefaultConfig(refFileConfigDto);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundle.getString("msg_success") + ": "
					+ bundle.getString("msg_success_modification"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		 } catch (FileNotFoundException e) {
			 log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		catch (IOException e) {
			
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	 finally{
		loadParams();
	 }
		
	}
	
	

	

	/**
	 * [EvenementMgrBean.idf] Getter 
	 * @author BELDI Jamel on : 5 mars 2014  11:16:18
	 * @return the idf
	 */
	public String getIdf() {
		return idf;
	}

	/**
	 * [EvenementMgrBean.idf] Setter 
	 * @author BELDI Jamel on : 5 mars 2014  11:16:18
	 * @param idf the idf to set
	 */
	public void setIdf(String idf) {
		this.idf = idf;
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

	/**
	 * [ParamMgrBean.filteredParams] Getter 
	 * @author BELDI Jamel on : 6 avr. 2014  17:31:58
	 * @return the filteredParams
	 */
	public List<RefParamDto> getFilteredParams() {
		return filteredParams;
	}

	/**
	 * [ParamMgrBean.filteredParams] Setter 
	 * @author BELDI Jamel on : 6 avr. 2014  17:31:58
	 * @param filteredParams the filteredParams to set
	 */
	public void setFilteredParams(List<RefParamDto> filteredParams) {
		this.filteredParams = filteredParams;
	}
	
	
}
