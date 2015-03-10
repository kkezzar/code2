/**
 * 
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.contrat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.commandbutton.CommandButton;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefContratService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author jbeldi
 * 
 */
@ManagedBean(name = "contratSearchBean")
@ViewScoped
public class ContratSearchBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResourceBundle bundle;
	private RefContratDto refContratDto;
	private List<RefContratDto> listRefContratDto;
	private RefContratDto searchDto;
	private String searchInput;
	@ManagedProperty(value = "#{refContratServiceImpl}")
	private RefContratService refContratServiceImpl;
	private String searchValue;
	private String advSearchValue;
	@ManagedProperty("#{flash}")
	private Flash flash;

	/**
	 * [ContratBean.ContratBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2013 16:28:06
	 */
	public ContratSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);

	}

	@PostConstruct
	public void init() {
		if (searchValue != null) {
			listRefContratDto = refContratServiceImpl.findGeneric(
					SessionValues.getIdEtablissement(), searchValue, false);
		} else if (advSearchValue != null) {
			searchDto = constructContratDto(advSearchValue);
			listRefContratDto = refContratServiceImpl.findAdvanced(
					SessionValues.getIdEtablissement(), searchDto, false);
		}
		if (searchDto == null) {
			searchDto = new RefContratDto();
		}

	}

	/**
	 * [ContratSearchBean.findGeneric] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 10:38:28
	 */
	public void findGeneric(String value) {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefContratDto> result = null;
			if (value != null) {
				result = refContratServiceImpl.findGeneric(
						SessionValues.getIdEtablissement(), value, false);
			}
			if (result == null || result.isEmpty()) {
				setListRefContratDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefContratDto(result);// listRefContratDto = result;

			}
		} catch (Exception e) {
			setListRefContratDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * [ContratSearchBean.findAdvanced] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 10:38:23
	 */
	public void findAdvanced() {
		FacesMessage msg = new FacesMessage();
		try {
			setAdvSearchValue(searchDto.toString());
			List<RefContratDto> result = refContratServiceImpl.findAdvanced(
					SessionValues.getIdEtablissement(), searchDto, false);
			if (result == null || result.isEmpty()) {
				setListRefContratDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefContratDto(result);// listRefContratDto = result;

			}
		} catch (Exception e) {
			setListRefContratDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [ContratBckBean.listRefContratDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 janv. 2014 09:32:37
	 * @return the listRefContratDto
	 */
	public List<RefContratDto> getListRefContratDto() {
		return listRefContratDto;
	}

	/**
	 * [ContratBckBean.listRefContratDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 janv. 2014 09:32:37
	 * @param listRefContratDto
	 *            the listRefContratDto to set
	 */
	public void setListRefContratDto(List<RefContratDto> listRefContratDto) {
		this.listRefContratDto = listRefContratDto;
	}

	/**
	 * [ContratBckBean.refContratDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 janv. 2014 10:59:30
	 * @return the refContratDto
	 */
	public RefContratDto getRefContratDto() {
		return refContratDto;
	}

	/**
	 * [ContratBckBean.refContratDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 janv. 2014 10:59:30
	 * @param refContratDto
	 *            the refContratDto to set
	 */
	public void setRefContratDto(RefContratDto refContratDto) {
		this.refContratDto = refContratDto;
	}

	/**
	 * [ContratBckBean.searchDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 janv. 2014 09:43:05
	 * @return the searchDto
	 */
	public RefContratDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [ContratBckBean.searchDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 janv. 2014 09:43:05
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(RefContratDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * [ContratBckBean.searchInput] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 janv. 2014 09:43:05
	 * @return the searchInput
	 */
	public String getSearchInput() {
		return searchInput;
	}

	/**
	 * [ContratBckBean.searchInput] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 janv. 2014 09:43:05
	 * @param searchInput
	 *            the searchInput to set
	 */
	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	/**
	 * [ContratSearchBean.toNewContrat] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 avr. 2014 11:39:38
	 * @return
	 */
	public String toNewContrat() {
		refContratDto = new RefContratDto();
		return "toNewContrat";
	}

	/**
	 * [ContratSearchBean.toDetails] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 avr. 2014 11:39:42
	 * @return
	 */
	public String toDetails(Integer id) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
    	request.setAttribute("id", id);
		return "toDetails";
	}

	/**
	 * [ContratSearchBean.toEditContrat] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 avr. 2014 11:39:45
	 * @return
	 */
	public String toEditContrat(Integer id) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
    	request.setAttribute("id", id);
		return "toEditContrat";
	}

	/**
	 * [ContratSearchBean.bundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 10:39:12
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [ContratSearchBean.bundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 10:39:12
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [ContratSearchBean.refContratServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 10:39:40
	 * @return the refContratServiceImpl
	 */
	public RefContratService getRefContratServiceImpl() {
		return refContratServiceImpl;
	}

	/**
	 * [ContratSearchBean.refContratServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 10:39:40
	 * @param refContratServiceImpl
	 *            the refContratServiceImpl to set
	 */
	public void setRefContratServiceImpl(RefContratService refContratServiceImpl) {
		this.refContratServiceImpl = refContratServiceImpl;
	}

	/**
	 * [ContratSearchBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 10:49:22
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [ContratSearchBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 10:49:22
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if ((searchValue != null) && (searchValue.equals("null"))) {
			this.searchValue = null;
		} else
			this.searchValue = searchValue;
	}

	/**
	 * [ContratSearchBean.flash] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 mars 2014 13:55:16
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [ContratSearchBean.flash] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 mars 2014 13:55:16
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	public void allCheckChange(AjaxBehaviorEvent event) {
		CommandButton edit = (CommandButton) event.getComponent();

		RefContratDto _refContratDto = (RefContratDto) edit.getAttributes()
				.get("selectedRecord");
		System.out.println(_refContratDto);

	}

	/**
	 * [ContratSearchBean.advSearchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 avr. 2014 09:48:20
	 * @return the advSearchValue
	 */
	public String getAdvSearchValue() {
		return advSearchValue;
	}

	/**
	 * [ContratSearchBean.advSearchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 avr. 2014 09:48:20
	 * @param advSearchValue
	 *            the advSearchValue to set
	 */
	public void setAdvSearchValue(String advSearchValue) {
		if ((advSearchValue != null) && (advSearchValue.equals("null"))) {
			this.advSearchValue = null;
		} else
			this.advSearchValue = advSearchValue;
	}

	/**
	 * [ContratSearchBean.constructContratDto] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 avr. 2014 10:32:09
	 * @param token
	 * @return
	 */
	public RefContratDto constructContratDto(String token) {

		StringTokenizer tokenizer = new StringTokenizer(token,
				RefUtilConstant.CHAR_SEPARATOR, true);
		RefContratDto result = new RefContratDto();
		Object nextToken = tokenizer.nextElement();
		if (nextToken != null
				&& !nextToken.toString().trim()
						.equals(RefUtilConstant.CHAR_SEPARATOR)) {
			result.setIdentifiant((String) nextToken);
		}
		nextToken = tokenizer.nextElement();
		if (nextToken != null
				&& !nextToken.toString().trim()
						.equals(RefUtilConstant.CHAR_SEPARATOR)) {
			result.setObjetContratFr((String) nextToken);
		}
		nextToken = tokenizer.nextElement();
		if (nextToken != null
				&& !nextToken.toString().trim()
						.equals(RefUtilConstant.CHAR_SEPARATOR)) {
			result.setDateCreation((Date) nextToken);
		}
		nextToken = tokenizer.nextElement();
		if (nextToken != null
				&& !nextToken.toString().trim()
						.equals(RefUtilConstant.CHAR_SEPARATOR)) {
			result.setDateDebutValidite((Date) nextToken);
		}
		nextToken = tokenizer.nextElement();
		if (nextToken != null
				&& !nextToken.toString().trim()
						.equals(RefUtilConstant.CHAR_SEPARATOR)) {
			result.setDateFinValidite((Date) nextToken);
		}
		nextToken = tokenizer.nextElement();
		if (nextToken != null
				&& !nextToken.toString().trim()
						.equals(RefUtilConstant.CHAR_SEPARATOR)) {
			result.setDuree((Short) nextToken);
		}
		nextToken = tokenizer.nextElement();
		if (nextToken != null
				&& !nextToken.toString().trim()
						.equals(RefUtilConstant.CHAR_SEPARATOR)) {
			result.setDateFinValidite((Date) nextToken);
		}
		nextToken = tokenizer.nextElement();
		if (nextToken != null
				&& !nextToken.toString().trim()
						.equals(RefUtilConstant.CHAR_SEPARATOR)) {
			result.setMontantTva((BigDecimal) nextToken);
		}
		nextToken = tokenizer.nextElement();
		if (nextToken != null
				&& !nextToken.toString().trim()
						.equals(RefUtilConstant.CHAR_SEPARATOR)) {
			result.setMontantTtc((BigDecimal) nextToken);
		}
		nextToken = tokenizer.nextElement();
		if (nextToken != null
				&& !nextToken.toString().trim()
						.equals(RefUtilConstant.CHAR_SEPARATOR)) {
			result.setObjetContratAr((String) nextToken);
		}
		nextToken = tokenizer.nextElement();
		if (nextToken != null
				&& !nextToken.toString().trim()
						.equals(RefUtilConstant.CHAR_SEPARATOR)) {
			result.setObservation((String) nextToken);
		}
		nextToken = tokenizer.nextElement();
		if (nextToken != null
				&& !nextToken.toString().trim()
						.equals(RefUtilConstant.CHAR_SEPARATOR)) {
			result.setAvenant((Boolean) nextToken);
		}
		nextToken = tokenizer.nextElement();
		if (nextToken != null
				&& !nextToken.toString().trim()
						.equals(RefUtilConstant.CHAR_SEPARATOR)) {
			result.setReconductible((Boolean) nextToken);
		}
		nextToken = tokenizer.nextElement();
		if (nextToken != null
				&& !nextToken.toString().trim()
						.equals(RefUtilConstant.CHAR_SEPARATOR)) {
			result.setReferenceDocumentaire((String) nextToken);
		}
		return result;
	}

}
