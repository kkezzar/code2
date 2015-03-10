/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.periode.PeriodeBckBean.java] 
 * @author MAKERRI Sofiane on : 25 mars 2014  16:32:34
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.periode;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPeriodeDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPeriodeService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane  on : 2 avr. 2014  10:01:14
 */
/**
 * @author MAKERRI Sofiane on : 2 avr. 2014 10:02:44
 */
@ManagedBean(name = "periodeBckBean")
@RequestScoped
public class PeriodeBckBean implements Serializable{

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 16:35:28
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{param.idp}")
	private String idp;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	private RefPeriodeDto refPeriodeDto;
	@ManagedProperty(value = "#{refPeriodeServiceImpl}")
	private RefPeriodeService refPeriodeServiceImpl;
	private static final Log log = LogFactory.getLog(PeriodeBckBean.class);
	private ResourceBundle bundle;
	private ResourceBundle pBundle;
	private boolean idfPeriodeChange;
	private boolean namePeriodeChange;
	private boolean periodeChange;
	private String pattern;
	@ManagedProperty(value = "#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabServiceImpl;
	private int identifiantLength;


	/**
	 * [PeriodeBckBean.PeriodeBckBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 16:32:34
	 */
	public PeriodeBckBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		pBundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.PERIODE_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		if (idp != null) {
			int _idp = RefUtilConstant.strToInt(idp);
			if (_idp != -1) {
				refPeriodeDto = refPeriodeServiceImpl.findById(_idp);
			}
		}

		if (refPeriodeDto == null) {
			refPeriodeDto = new RefPeriodeDto();
		}
	}

	/**
	 * [PeriodeBckBean.idp] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 17:57:30
	 * @return the idp
	 */
	public String getIdp() {
		return idp;
	}

	/**
	 * [PeriodeBckBean.idp] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 17:57:30
	 * @param idp
	 *            the idp to set
	 */
	public void setIdp(String idp) {
		if (idp != null && idp.equals("null")) {
			this.idp = null;
		} else
			this.idp = idp;
	}

	/**
	 * [PeriodeBckBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 17:54:01
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [PeriodeBckBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 17:54:01
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if (searchValue != null && searchValue.equals("null")) {
			this.searchValue = null;
		} else
			this.searchValue = searchValue;
	}

	/**
	 * [PeriodeBckBean.refPeriodeDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 17:54:01
	 * @return the refPeriodeDto
	 */
	public RefPeriodeDto getRefPeriodeDto() {
		return refPeriodeDto;
	}

	/**
	 * [PeriodeBckBean.refPeriodeDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 17:54:01
	 * @param refPeriodeDto
	 *            the refPeriodeDto to set
	 */
	public void setRefPeriodeDto(RefPeriodeDto refPeriodeDto) {
		this.refPeriodeDto = refPeriodeDto;
	}

	/**
	 * [PeriodeBckBean.refPeriodeServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 17:54:01
	 * @return the refPeriodeServiceImpl
	 */
	public RefPeriodeService getRefPeriodeServiceImpl() {
		return refPeriodeServiceImpl;
	}

	/**
	 * [PeriodeBckBean.refPeriodeServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 17:54:01
	 * @param refPeriodeServiceImpl
	 *            the refPeriodeServiceImpl to set
	 */
	public void setRefPeriodeServiceImpl(RefPeriodeService refPeriodeServiceImpl) {
		this.refPeriodeServiceImpl = refPeriodeServiceImpl;
	}

	/**
	 * [PeriodeBckBean.save] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 18:05:55
	 */
	public void save() {
		FacesMessage msg = new FacesMessage();
		try {

			if (!periodeChange && idp != null) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_update_none"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}

			if (idfPeriodeChange) {
				RefPeriodeDto refPeriodeDtoFound = refPeriodeServiceImpl
						.findByIdentifiant(refPeriodeDto.getIdentifiant());
				if (refPeriodeDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec")
							+ ": "
							+ pBundle
									.getString("periode_error_identifiant_already_exist")
							+ " (" + refPeriodeDto.getIdentifiant().trim()
							+ ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
			}

			if (namePeriodeChange) {
				RefPeriodeDto refPeriodeDtoFound = refPeriodeServiceImpl
						.findByName(refPeriodeDto.getNom());
				if (refPeriodeDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec")
							+ ": "
							+ pBundle
									.getString("periode_error_name_already_exist")
							+ " (" + refPeriodeDto.getNom().trim() + ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
			}
			if (periodeChange) {
				if (idp != null) {

					refPeriodeServiceImpl.update(refPeriodeDto);
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundle.getString("msg_success") + ": "
							+ bundle.getString("msg_success_modification"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					String nextIdentifiy = generateIdentify();
					if (nextIdentifiy == null) {
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						msg.setSummary(bundle.getString("error_echec") + ": "
								+ bundle.getString("error_generation_identify"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return;
					} else if (nextIdentifiy.length() > identifiantLength) {
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						msg.setSummary(bundle.getString("error_echec")
								+ ": "
								+ bundle.getString("error_identify_max_legnt_affected"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return;
					}
					refPeriodeDto.setIdentifiant(nextIdentifiy);
					refPeriodeServiceImpl.save(refPeriodeDto);
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundle.getString("msg_success") + ": "
							+ bundle.getString("msg_success_enregistrement"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					if (refPeriodeDto.getId() != null) {
						setIdp(refPeriodeDto.getId().toString());
					}

				}
				setPeriodeChange(false);
			}

		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}

	}

	/**
	 * [PeriodeBckBean.back] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 avr. 2014 10:16:15
	 * @return
	 */
	public String back() {
		return "toSearch";
	}

	/**
	 * [PeriodeBckBean.idfPeriodeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 avr. 2014 10:16:18
	 * @param event
	 */
	public void idfPeriodeChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			idfPeriodeChange = true;
			periodeChange = true;
		}
	}

	/**
	 * [PeriodeBckBean.namePeriodeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 avr. 2014 10:16:21
	 * @param event
	 */
	public void namePeriodeChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			namePeriodeChange = true;
			periodeChange = true;
		}
	}

	/**
	 * [PeriodeBckBean.infoChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 avr. 2014 10:55:41
	 * @param event
	 */
	public void infoChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			periodeChange = true;
		}
	}

	/**
	 * [PeriodeBckBean.periodiqueChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 3 avr. 2014 08:59:11
	 * @param event
	 */
	public void periodiqueChanged(AjaxBehaviorEvent event) {
		/*SelectBooleanCheckbox _periodiqueUi = (SelectBooleanCheckbox) event
				.getComponent();
		boolean _periodique = (Boolean) _periodiqueUi.getValue();
		if (_periodique) {
			setPattern("dd/MM");
		} else {
			setPattern("dd/MM/yyyy");
		}*/
	}

	/**
	 * [PeriodeBckBean.idfPeriodeChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 avr. 2014 10:02:20
	 * @return the idfPeriodeChange
	 */
	public boolean isIdfPeriodeChange() {
		return idfPeriodeChange;
	}

	/**
	 * [PeriodeBckBean.idfPeriodeChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 avr. 2014 10:02:20
	 * @param idfPeriodeChange
	 *            the idfPeriodeChange to set
	 */
	public void setIdfPeriodeChange(boolean idfPeriodeChange) {
		this.idfPeriodeChange = idfPeriodeChange;
	}

	/**
	 * [PeriodeBckBean.namePeriodeChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 avr. 2014 10:02:20
	 * @return the namePeriodeChange
	 */
	public boolean isNamePeriodeChange() {
		return namePeriodeChange;
	}

	/**
	 * [PeriodeBckBean.namePeriodeChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 avr. 2014 10:02:20
	 * @param namePeriodeChange
	 *            the namePeriodeChange to set
	 */
	public void setNamePeriodeChange(boolean namePeriodeChange) {
		this.namePeriodeChange = namePeriodeChange;
	}

	/**
	 * [PeriodeBckBean.periodeChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 avr. 2014 10:02:33
	 * @return the periodeChange
	 */
	public boolean isPeriodeChange() {
		return periodeChange;
	}

	/**
	 * [PeriodeBckBean.periodeChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 avr. 2014 10:02:33
	 * @param periodeChange
	 *            the periodeChange to set
	 */
	public void setPeriodeChange(boolean periodeChange) {
		this.periodeChange = periodeChange;
	}

	/**
	 * [PeriodeBckBean.addPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 3 avr. 2014 08:50:08
	 */
	public void addPeriode() {
		refPeriodeDto = new RefPeriodeDto();
	}

	/**
	 * [PeriodeBckBean.pattern] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 avr. 2014 08:50:18
	 * @return the pattern
	 */
	public String getPattern() {
		if (pattern == null) {
			this.pattern = "dd/MM/yyyy";
		}
		return pattern;
	}

	/**
	 * [PeriodeBckBean.pattern] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 avr. 2014 08:50:18
	 * @param pattern
	 *            the pattern to set
	 */
	public void setPattern(String pattern) {

		this.pattern = pattern;
	}
	
	/**
	 * [PeriodeBckBean.generateIdentify] Method 
	 * @author MAKERRI Sofiane  on : 22 avr. 2014  10:37:21
	 * @return
	 */
	public String generateIdentify() {
		try {
			String prefix = refParametreEtabServiceImpl
					.findValueOfKey(SessionValues.getIdEtablissement(), RefUtilConstant.CODIFICATION_PERIODE_PREFIX_KEY);
			if (prefix == null) {
				return null;
			}
			int prefixLength =  prefix.length();
			String order = refParametreEtabServiceImpl
					.findValueOfKey(SessionValues.getIdEtablissement(), RefUtilConstant.CODIFICATION_PERIODE_ORDER_LENGTH_KEY);
			if (order == null) {
				return null;
			}
			int orderLength = Integer.parseInt(order);
			identifiantLength = prefixLength + orderLength;
			return refPeriodeServiceImpl.generateIdentify(prefix, orderLength);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}

	
	

	/**
	 * [PeriodeBckBean.refParametreEtabServiceImpl] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  17:47:28
	 * @return the refParametreEtabServiceImpl
	 */
	public RefParametreEtabService getRefParametreEtabServiceImpl() {
		return refParametreEtabServiceImpl;
	}

	/**
	 * [PeriodeBckBean.refParametreEtabServiceImpl] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  17:47:28
	 * @param refParametreEtabServiceImpl the refParametreEtabServiceImpl to set
	 */
	public void setRefParametreEtabServiceImpl(
			RefParametreEtabService refParametreEtabServiceImpl) {
		this.refParametreEtabServiceImpl = refParametreEtabServiceImpl;
	}

	/**
	 * [PeriodeBckBean.identifiantLength] Getter 
	 * @author MAKERRI Sofiane on : 23 avr. 2014  12:56:53
	 * @return the identifiantLength
	 */
	public int getIdentifiantLength() {
		return identifiantLength;
	}

	/**
	 * [PeriodeBckBean.identifiantLength] Setter 
	 * @author MAKERRI Sofiane on : 23 avr. 2014  12:56:53
	 * @param identifiantLength the identifiantLength to set
	 */
	public void setIdentifiantLength(int identifiantLength) {
		this.identifiantLength = identifiantLength;
	}
	
	

	
}
