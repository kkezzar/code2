/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.compte.CompteMgrBean.java] 
 * @author BELDI Jamel on : 24 f�vr. 2014  09:15:14
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.compte;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELDI Jamel  on : 24 f�vr. 2014  09:15:14
 */
@ManagedBean(name = "compteSearchBean")
@ViewScoped
public class CompteSearchBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(CompteSearchBean.class);
	private String searchInput;
	private RefCompteDto searchDto;
	private List<RefCompteDto> listRefCompteDto;
	
	private ResourceBundle bundle;
	

	@ManagedProperty(value = "#{refCompteServiceImpl}")
	private RefCompteService refCompteServiceImpl;
	@ManagedProperty("#{flash}")
	private Flash flash;

	/**
	 * [CompteSearchBean.flash] Getter 
	 * @author BELDI Jamel on : 25 f�vr. 2014  16:32:31
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [CompteSearchBean.flash] Setter 
	 * @author BELDI Jamel on : 25 f�vr. 2014  16:32:31
	 * @param flash the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [CompteMgrBean.CompteMgrBean()] Constructor
	 * @author BELDI Jamel  on : 24 f�vr. 2014  09:15:15	
	 */
	public CompteSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		searchDto = new RefCompteDto();
	}

	@PostConstruct
	public void init() {
		log.info("******************************@PostConstruct: ");
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
		listRefCompteDto = (List<RefCompteDto>) flash
				.get("listRefCompteDto");		
		flash.keep("listRefCompteDto");

		log.info("******************************pullValuesFromFlash: "
				+ listRefCompteDto);

	}
	
	/**
	 * [CompteMgrBean.searchInput] Getter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  10:31:06
	 * @return the searchInput
	 */
	public String getSearchInput() {
		return searchInput;
	}

	/**
	 * [CompteMgrBean.searchInput] Setter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  10:31:06
	 * @param searchInput the searchInput to set
	 */
	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	/**
	 * [CompteMgrBean.searchDto] Getter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  10:31:06
	 * @return the searchDto
	 */
	public RefCompteDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [CompteMgrBean.searchDto] Setter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  10:31:06
	 * @param searchDto the searchDto to set
	 */
	public void setSearchDto(RefCompteDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * [CompteMgrBean.listRefCompteDto] Getter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  10:31:06
	 * @return the listRefCompteDto
	 */
	public List<RefCompteDto> getListRefCompteDto() {
		return listRefCompteDto;
	}

	/**
	 * [CompteMgrBean.listRefCompteDto] Setter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  10:31:06
	 * @param listRefCompteDto the listRefCompteDto to set
	 */
	public void setListRefCompteDto(List<RefCompteDto> listRefCompteDto) {
		this.listRefCompteDto = listRefCompteDto;
	}

	


	/**
	 * [CompteMgrBean.refCompteServiceImpl] Getter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  10:31:06
	 * @return the refCompteServiceImpl
	 */
	public RefCompteService getRefCompteServiceImpl() {
		return refCompteServiceImpl;
	}

	/**
	 * [CompteMgrBean.refCompteServiceImpl] Setter 
	 * @author BELDI Jamel on : 24 f�vr. 2014  10:31:06
	 * @param refCompteServiceImpl the refCompteServiceImpl to set
	 */
	public void setRefCompteServiceImpl(RefCompteService refCompteServiceImpl) {
		this.refCompteServiceImpl = refCompteServiceImpl;
	}
	
	
	

	/**
	 * navigate to new Compte page
	 * 
	 * @return outcome
	 */
	public String toNew() {

		return "toNew";
	}

	/**
	 * 
	 * navigate to edit Compte page
	 * 
	 * @return
	 */
	public String toEdit() {
		     /*HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		        String idf = request.getParameter(""
		        		+ "");
log.info("----------------------------IDF--------------"+idf);
		return "compteEdit?faces-redirect=true&idf="+idf;*/
		return "toEdit";
	}

	/**
	 * [CompteMgrBean.findGeneric] Method Find the list of Compte by the
	 * the search input
	 * 
	 * @author jbeldi on : 15 janv. 2014 11:18:14
	 */
	public String findGeneric() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefCompteDto> result = refCompteServiceImpl
					.findGeneric(SessionValues.getIdEtablissement(), this.searchInput);
			if (result == null || result.isEmpty()) {
				flash.put("listRefCompteDto", null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				flash.setKeepMessages(true);
			} else {
				flash.put("listRefCompteDto", result);
				flash.keep("listRefCompteDto");
				pullValuesFromFlash(null);

			}
		} catch (Exception e) {
			flash.put("listRefCompteDto", null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			flash.setKeepMessages(true);
		}
		return "compteSearch?faces-redirect=true";
	}

	/**
	 * [CompteMgrBean.findAdvanced] Method to Find the list of individu by the
	 * the advanced search
	 * 
	 * @author jbeldi on : 15 janv. 2014 11:17:49
	 */
	public String findAdvanced() {
		FacesMessage msg = new FacesMessage();
		try {
			flash.put("listRefCompteDto", null);
			List<RefCompteDto> result = refCompteServiceImpl
					.findAdvanced(searchDto);

			if (result == null || result.isEmpty()) {
				setListRefCompteDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				flash.setKeepMessages(true);
			} else {
				flash.put("listRefCompteDto", result);
				flash.keep("listRefCompteDto");
				pullValuesFromFlash(null);
			}
		} catch (Exception e) {
			flash.put("listRefCompteDto", null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			flash.setKeepMessages(true);
		}
		return "compteSearch?faces-redirect=true";
	}

	

	
	
	

	
	
	
	
}
