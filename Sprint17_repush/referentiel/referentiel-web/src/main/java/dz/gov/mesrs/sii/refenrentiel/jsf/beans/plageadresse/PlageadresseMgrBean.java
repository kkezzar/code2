/**
 * 
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.plageadresse;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPlageAdresseDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPlageAdresseService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author obelbrik
 * 
 */
@ManagedBean(name = "plageadresseMgrBean")
@RequestScoped
public class PlageadresseMgrBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String searchInput;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	@ManagedProperty(value = "#{refPlageAdresseServiceImpl}")
	private RefPlageAdresseService refPlageAdresseServiceImpl;
	private static final Log log = LogFactory.getLog(PlageadresseMgrBean.class);
	private RefPlageAdresseDto refPlageAdresseDto;
	private RefPlageAdresseDto searchDto;
	private ResourceBundle plageadresseBundle;
	@ManagedProperty("#{param.idadr}")
	private String idadr;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	private boolean adresseChange;
	private boolean idfChange;
	private boolean nameChange;
	private boolean ipChange;
	@ManagedProperty(value = "#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabServiceImpl;
	private int identifiantLength;

	/**
	 * [PlageAdresseMgrBean.PlageAdresseMgrBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:19:25
	 */
	public PlageadresseMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				PlageadresseMgrBean.MESSAGES_FILE_NAME);
		plageadresseBundle = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.PLAGEADRESSE_MESSAGES_FILE_NAME);
		searchDto = new RefPlageAdresseDto();
	}

	@PostConstruct
	public void init() {
		if (idadr != null) {
			int _idadr = RefUtilConstant.strToInt(idadr);
			if (_idadr != -1) {
				refPlageAdresseDto = refPlageAdresseServiceImpl
						.findById(_idadr);
			}
		}

		if (refPlageAdresseDto == null) {
			refPlageAdresseDto = new RefPlageAdresseDto();
		}
		
	}

	/**
	 * 
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:19:43
	 * @return the searchInput
	 */
	public String getSearchInput() {

		return searchInput;
	}

	/**
	 * @return the refPlageAdresseDto
	 */
	public RefPlageAdresseDto getRefPlageAdresseDto() {
		return refPlageAdresseDto;
	}

	
	public void toSave() {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("Mgr Bean toSave");
			if (!adresseChange && idadr != null) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_update_none"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}

			if (refPlageAdresseDto.getAdresseDebut().compareTo(
					(refPlageAdresseDto.getAdresseFin())) > 0) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(plageadresseBundle
						.getString("adresse_debut_superieur_fin"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;

			}

			if (idadr != null) {
				refPlageAdresseDto.setIdEtablissement(SessionValues.getIdEtablissement());
				refPlageAdresseServiceImpl.update(refPlageAdresseDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				refPlageAdresseDto.setIdEtablissement(SessionValues.getIdEtablissement());
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
				refPlageAdresseDto.setIdentifiant(nextIdentifiy);
				refPlageAdresseServiceImpl.save(refPlageAdresseDto);
				if (refPlageAdresseDto.getId() != null) {
					setIdadr(refPlageAdresseDto.getId().toString());
				}
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}
			setAdresseChange(false);
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
	}

	/**
	 * [G
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:42:23
	 * @return the refPlageAdresseServiceImpl
	 */
	public RefPlageAdresseService getRefPlageAdresseServiceImpl() {
		return refPlageAdresseServiceImpl;
	}

	/**
	 * [
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:42:23
	 * @param refPlageAdresseServiceImpl
	 *            the refPlageAdresseServiceImpl to set
	 */
	public void setRefPlageAdresseServiceImpl(
			RefPlageAdresseService refPlageAdresseServiceImpl) {
		this.refPlageAdresseServiceImpl = refPlageAdresseServiceImpl;
	}

	/**
	 * [PlageAdresseMgrBean.searchDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:42:23
	 * @return the searchDto
	 */
	public RefPlageAdresseDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [PlageAdresseMgrBean.searchDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:42:23
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(RefPlageAdresseDto searchDto) {
		this.searchDto = searchDto;
	}

	
	/**
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:42:23
	 * 
	 */
	public void setRefPlageAdresseDto(RefPlageAdresseDto refPlageAdresseDto) {
		this.refPlageAdresseDto = refPlageAdresseDto;
	}

	

	/**
	 * 
	 * 
	 * @author BELBRIK Oualid on : 27 f�vr. 2014 09:20:24
	 * @param searchInput
	 */
	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	/**
	 * 
	 * 
	 * @author BELBRIK Oualid on : 26 f�vr. 2014 18:35:24
	 * @param delete
	 *            horaire d'acces
	 */

	public void toRemove(RefPlageAdresseDto selectedRefPlageAdresseDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedRefPlageAdresseDto != null)
				&& (selectedRefPlageAdresseDto.getId() != 0)) {
			try {
				log.info("deleting Plage adresse id = "
						+ selectedRefPlageAdresseDto.getIdentifiant());
				refPlageAdresseServiceImpl.delete(selectedRefPlageAdresseDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_suppression"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}

	}

	/**
	 * [PlageadresseMgrBean.addAdresse] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 13:45:28
	 */
	public void addAdresse() {
		refPlageAdresseDto = new RefPlageAdresseDto();
	}

	/**
	 * [PlageadresseMgrBean.idadr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 13:52:15
	 * @return the idadr
	 */
	public String getIdadr() {
		return idadr;
	}

	/**
	 * [PlageadresseMgrBean.idadr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 13:52:15
	 * @param idadr
	 *            the idadr to set
	 */
	public void setIdadr(String idadr) {
		if (idadr != null && idadr.equals("null")) {
			this.idadr = null;
		} else
			this.idadr = idadr;
	}

	/**
	 * [PlageadresseMgrBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 13:52:15
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [PlageadresseMgrBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 13:52:15
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
	 * [PlageadresseMgrBean.idfChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 13:58:10
	 * @param event
	 */
	public void idfChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			adresseChange = true;
			idfChange = true;
		}
	}

	/**
	 * [PlageadresseMgrBean.nameChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 14:00:01
	 * @param event
	 */
	public void nameChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			adresseChange = true;
			nameChange = true;
		}
	}

	public void adrChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			adresseChange = true;
			ipChange = true;
		}
	}

	/**
	 * [PlageadresseMgrBean.adresseChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 13:59:05
	 * @return the adresseChange
	 */
	public boolean isAdresseChange() {
		return adresseChange;
	}

	/**
	 * [PlageadresseMgrBean.adresseChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 13:59:05
	 * @param adresseChange
	 *            the adresseChange to set
	 */
	public void setAdresseChange(boolean adresseChange) {
		this.adresseChange = adresseChange;
	}

	/**
	 * [PlageadresseMgrBean.idfChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 13:59:05
	 * @return the idfChange
	 */
	public boolean isIdfChange() {
		return idfChange;
	}

	/**
	 * [PlageadresseMgrBean.idfChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 13:59:05
	 * @param idfChange
	 *            the idfChange to set
	 */
	public void setIdfChange(boolean idfChange) {
		this.idfChange = idfChange;
	}

	/**
	 * [PlageadresseMgrBean.nameChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 13:59:05
	 * @return the nameChange
	 */
	public boolean isNameChange() {
		return nameChange;
	}

	/**
	 * [PlageadresseMgrBean.nameChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 13:59:05
	 * @param nameChange
	 *            the nameChange to set
	 */
	public void setNameChange(boolean nameChange) {
		this.nameChange = nameChange;
	}

	/**
	 * [PlageadresseMgrBean.ipChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 14:00:37
	 * @return the ipChange
	 */
	public boolean isIpChange() {
		return ipChange;
	}

	/**
	 * [PlageadresseMgrBean.ipChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 avr. 2014 14:00:37
	 * @param ipChange
	 *            the ipChange to set
	 */
	public void setIpChange(boolean ipChange) {
		this.ipChange = ipChange;
	}
	
	/**
	 * [PlageadresseMgrBean.back] Method 
	 * @author MAKERRI Sofiane  on : 6 avr. 2014  15:27:04
	 * @return
	 */
	public String back() {
		return "plageAdresseSearch";
	}
	
	/**
	 * [PlageadresseMgrBean.generateIdentify] Method 
	 * @author MAKERRI Sofiane  on : 22 avr. 2014  09:58:35
	 * @return
	 */
	public String generateIdentify() {
		try {
			String prefix = refParametreEtabServiceImpl
					.findValueOfKey(SessionValues.getIdEtablissement(), RefUtilConstant.CODIFICATION_PLAGE_ADRESSE_PREFIX_KEY);
			if (prefix == null) {
				return null;
			}
			int prefixLength =  prefix.length();
			String order = refParametreEtabServiceImpl
					.findValueOfKey(SessionValues.getIdEtablissement(), RefUtilConstant.CODIFICATION_PLAGE_ADRESSE_ORDER_LENGTH_KEY);
			if (order == null) {
				return null;
			}
			int orderLength = Integer.parseInt(order);
			identifiantLength = prefixLength + orderLength;
			return refPlageAdresseServiceImpl.generateIdentify(prefix, orderLength);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}

	
	
	/**
	 * [PlageadresseMgrBean.refParametreEtabServiceImpl] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  17:48:10
	 * @return the refParametreEtabServiceImpl
	 */
	public RefParametreEtabService getRefParametreEtabServiceImpl() {
		return refParametreEtabServiceImpl;
	}

	/**
	 * [PlageadresseMgrBean.refParametreEtabServiceImpl] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  17:48:10
	 * @param refParametreEtabServiceImpl the refParametreEtabServiceImpl to set
	 */
	public void setRefParametreEtabServiceImpl(
			RefParametreEtabService refParametreEtabServiceImpl) {
		this.refParametreEtabServiceImpl = refParametreEtabServiceImpl;
	}

	/**
	 * [PlageadresseMgrBean.identifiantLength] Getter 
	 * @author MAKERRI Sofiane on : 23 avr. 2014  12:27:58
	 * @return the identifiantLength
	 */
	public int getIdentifiantLength() {
		return identifiantLength;
	}

	/**
	 * [PlageadresseMgrBean.identifiantLength] Setter 
	 * @author MAKERRI Sofiane on : 23 avr. 2014  12:27:58
	 * @param identifiantLength the identifiantLength to set
	 */
	public void setIdentifiantLength(int identifiantLength) {
		this.identifiantLength = identifiantLength;
	}
	
	

}
