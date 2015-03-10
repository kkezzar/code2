/**
 * 
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.evenement;

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
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.RowEditEvent;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEvenementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTacheDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefEvenementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefTacheService;

/**
 * @author jbeldi
 * 
 */
@ManagedBean(name = "evenementSearchBean")
@RequestScoped
public class EvenementSearchBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(EvenementSearchBean.class);
	private String searchInput;
	private RefEvenementDto searchDto;
	private List<RefEvenementDto> listRefEvenementDto;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	@ManagedProperty(value = "#{refEvenementServiceImpl}")
	private RefEvenementService refEvenementServiceImpl;
	@ManagedProperty("#{flash}")
	private Flash flash;

	/**
	 * Constructor of EvenementMgrBean
	 */
	public EvenementSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				EvenementSearchBean.MESSAGES_FILE_NAME);
		searchDto = new RefEvenementDto();

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
		listRefEvenementDto = (List<RefEvenementDto>) flash
				.get("listRefEvenementDto");

		flash.keep("listRefEvenementDto");
	
		log.info("******************************pullValuesFromFlash: "
				+ listRefEvenementDto);

	}
	
	
	
	

	

	/**
	 * [EvenementMgrBean.findGeneric] Method Find the list of evenement by the
	 * the search input
	 * 
	 * @author jbeldi on : 15 janv. 2014 11:18:14
	 */
	public String findGeneric() {
		FacesMessage msg = new FacesMessage();
		setSearchDto(new RefEvenementDto());
		try {
			List<RefEvenementDto> result = refEvenementServiceImpl
					.findGeneric(SessionValues.getIdEtablissement(), this.searchInput);
			if (result == null || result.isEmpty()) {
				flash.put("listRefEvenementDto", null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				flash.setKeepMessages(true);
			} else {
				flash.put("listRefEvenementDto", result);
				flash.keep("listRefEvenementDto");
				pullValuesFromFlash(null);
			}
		} catch (Exception e) {
			flash.put("listRefEvenementDto", null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			flash.setKeepMessages(true);
		}
		return "evenementSearch?faces-redirect=true";
	}

	/**
	 * [EvenementMgrBean.findAdvanced] Method to Find the list of evenement by
	 * the the advanced search
	 * 
	 * @author jbeldi on : 15 janv. 2014 11:17:49
	 */
	public String findAdvanced() {
		FacesMessage msg = new FacesMessage();
		try {
			setSearchInput(null);
			List<RefEvenementDto> result = refEvenementServiceImpl
					.findAdvanced(SessionValues.getIdEtablissement(), searchDto);

			if (result == null || result.isEmpty()) {
				flash.put("listRefEvenementDto", null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				flash.setKeepMessages(true);
			} else {
				flash.put("listRefEvenementDto", result);
				flash.keep("listRefEvenementDto");
				pullValuesFromFlash(null);
			}
		} catch (Exception e) {
			flash.put("listRefEvenementDto", null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			flash.setKeepMessages(true);
		}
		return "evenementSearch?faces-redirect=true";
	}

	

	
	/**
	 * 
	 * @param event
	 * @return
	 */
	public String toDetails() {
		 HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			        String idf = request.getParameter("idf");
	log.info("----------------------------IDF--------------"+idf);
			return "evenementDetails?faces-redirect=true&idf="+idf;

		//return "toDetails";
	}

	/**
	 * navigate to new evenement page
	 * 
	 * @return outcome
	 */
	public String toNew() {

		return "toNew";
	}

	/**
	 * 
	 * navigate to edit evenement page
	 * 
	 * @return
	 */
	public String toEdit() {
		 HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			        String idf = request.getParameter("idf");
	        log.info("----------------------------IDF--------------"+idf);
			return "evenementEdit?faces-redirect=true&idf="+idf;
	//	return  "evenementEdit?faces-redirect=true";
	}
	
	
	

	/**
	 * [EvenementMgrBean.listRefEvenementDto] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:45:37
	 * @return the listRefEvenementDto
	 */
	public List<RefEvenementDto> getListRefEvenementDto() {

		return listRefEvenementDto;
	}

	/**
	 * [EvenementMgrBean.listRefEvenementDto] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:45:37
	 * @param listRefEvenementDto
	 *            the listRefEvenementDto to set
	 */
	public void setListRefEvenementDto(List<RefEvenementDto> listRefEvenementDto) {
		this.listRefEvenementDto = listRefEvenementDto;
	}

	
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	public Flash getFlash() {
		return flash;
	}

	/**
	 * [EvenementMgrBean.refEvenementServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:41:32
	 * @return the refEvenementServiceImpl
	 */
	public RefEvenementService getRefEvenementServiceImpl() {
		return refEvenementServiceImpl;
	}

	/**
	 * [EvenementMgrBean.refEvenementServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:41:32
	 * @param refEvenementServiceImpl
	 *            the refEvenementServiceImpl to set
	 */
	public void setRefEvenementServiceImpl(
			RefEvenementService refEvenementServiceImpl) {
		this.refEvenementServiceImpl = refEvenementServiceImpl;
	}

	/**
	 * @return the searchInput
	 */
	public String getSearchInput() {

		return searchInput;
	}

	/**
	 * @param searchInput
	 *            the searchInput to set
	 */
	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	/**
	 * [EvenementMgrBean.searchDto] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:43:12
	 * @return the searchDto
	 */
	public RefEvenementDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [EvenementMgrBean.searchDto] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:43:12
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(RefEvenementDto searchDto) {
		this.searchDto = searchDto;
	}

	

	

	

	
	
	
}
