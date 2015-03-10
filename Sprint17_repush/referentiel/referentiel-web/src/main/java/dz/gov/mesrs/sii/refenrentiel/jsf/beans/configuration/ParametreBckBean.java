/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.configuration.ConfigurationBckBean.java] 
 * @author MAKERRI Sofiane on : 22 avr. 2014  15:53:18
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.configuration;

import java.io.Serializable;
import java.util.List;
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

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.ComboBckBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParametreDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParametreEtabDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 22 avr. 2014 15:53:18
 */
@ManagedBean(name = "parametreBckBean")
@RequestScoped
public class ParametreBckBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:53:29
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{param.idpc}")
	private String idpc;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty("#{param.key}")
	private String key;
	private RefParametreDto refParametreDto;
	@ManagedProperty(value = "#{refParametreServiceImpl}")
	private RefParametreService refParametreServiceImpl;
	@ManagedProperty(value = "#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabServiceImpl;
	@ManagedProperty(value = "#{comboBckBean}")
	private ComboBckBean comboBckBean;
	private static final Log log = LogFactory.getLog(ParametreBckBean.class);
	private ResourceBundle bundle;
	private ResourceBundle configBundle;
	private boolean entityChange;
	private boolean keyChange;
	private boolean descChange;
	private boolean valueChange;

	/**
	 * [ConfigurationBckBean.ConfigurationBckBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:53:18
	 */
	public ParametreBckBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		configBundle = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.CONFIGURATION_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		if (idpc != null) {
			Integer _idpc = RefUtilConstant.strToInt(idpc);
			if (_idpc != -1) {
				refParametreDto = refParametreServiceImpl.findById(_idpc);
			}
		}

		if (refParametreDto == null) {
			refParametreDto = new RefParametreDto();
		}
	}

	/**
	 * [ConfigurationBckBean.idpc] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:55:44
	 * @return the idpc
	 */
	public String getIdpc() {
		return idpc;
	}

	/**
	 * [ConfigurationBckBean.idpc] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:55:44
	 * @param idpc
	 *            the idpc to set
	 */
	public void setIdpc(String idpc) {
		if (idpc != null && idpc.equals("null")) {
			this.idpc = null;
		} else
			this.idpc = idpc;
	}

	/**
	 * [ConfigurationBckBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:55:44
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [ConfigurationBckBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:55:44
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
	 * [ParametreBckBean.refParametreDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 10:01:54
	 * @return the refParametreDto
	 */
	public RefParametreDto getRefParametreDto() {
		return refParametreDto;
	}

	/**
	 * [ParametreBckBean.refParametreDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 10:01:54
	 * @param refParametreDto
	 *            the refParametreDto to set
	 */
	public void setRefParametreDto(RefParametreDto refParametreDto) {
		this.refParametreDto = refParametreDto;
	}

	/**
	 * [ConfigurationBckBean.refParametreServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 09:14:11
	 * @return the refParametreServiceImpl
	 */
	public RefParametreService getRefParametreServiceImpl() {
		return refParametreServiceImpl;
	}

	/**
	 * [ConfigurationBckBean.refParametreServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 09:14:11
	 * @param refParametreServiceImpl
	 *            the refParametreServiceImpl to set
	 */
	public void setRefParametreServiceImpl(
			RefParametreService refParametreServiceImpl) {
		this.refParametreServiceImpl = refParametreServiceImpl;
	}

	/**
	 * [ConfigurationBckBean.entityChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:55:44
	 * @return the entityChange
	 */
	public boolean isEntityChange() {
		return entityChange;
	}

	/**
	 * [ConfigurationBckBean.entityChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:55:44
	 * @param entityChange
	 *            the entityChange to set
	 */
	public void setEntityChange(boolean entityChange) {
		this.entityChange = entityChange;
	}

	/**
	 * [ConfigurationBckBean.keyChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:55:44
	 * @return the keyChange
	 */
	public boolean isKeyChange() {
		return keyChange;
	}

	/**
	 * [ConfigurationBckBean.keyChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:55:44
	 * @param keyChange
	 *            the keyChange to set
	 */
	public void setKeyChange(boolean keyChange) {
		this.keyChange = keyChange;
	}

	/**
	 * [ConfigurationBckBean.descChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:55:44
	 * @return the descChange
	 */
	public boolean isDescChange() {
		return descChange;
	}

	/**
	 * [ConfigurationBckBean.descChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:55:44
	 * @param descChange
	 *            the descChange to set
	 */
	public void setDescChange(boolean descChange) {
		this.descChange = descChange;
	}

	/**
	 * [ConfigurationBckBean.valueChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:55:44
	 * @return the valueChange
	 */
	public boolean isValueChange() {
		return valueChange;
	}

	/**
	 * [ConfigurationBckBean.valueChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:55:44
	 * @param valueChange
	 *            the valueChange to set
	 */
	public void setValueChange(boolean valueChange) {
		this.valueChange = valueChange;
	}

	/**
	 * [ConfigurationBckBean.keyChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:57:32
	 * @param event
	 */
	public void keyChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			keyChange = true;
			setKey("true");
			entityChange = true;
		}
	}

	/**
	 * [ConfigurationBckBean.valueChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:57:49
	 * @param event
	 */
	public void defaultValueChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			valueChange = true;
			entityChange = true;
		}
	}

	/**
	 * [ConfigurationBckBean.descChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 15:58:05
	 * @param event
	 */
	public void descChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			descChange = true;
			entityChange = true;
		}
	}

	/**
	 * [ParametreBckBean.entityChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 11:58:59
	 * @param event
	 */
	public void entityChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			entityChange = true;
		}
	}

	/**
	 * [ConfigurationBckBean.save] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 16:02:55
	 */
	public void save() {
		FacesMessage msg = new FacesMessage();
		try {

			if (!entityChange) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_update_none"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}

			if (refParametreDto.getKey() != null) {
				RefParametreDto refParametreConfigurationDtoFound = null;
				if (keyChange) {

					refParametreConfigurationDtoFound = refParametreServiceImpl
							.findByKey(refParametreDto.getId(), refParametreDto
									.getKey().trim());

					if (refParametreConfigurationDtoFound != null) {
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						msg.setSummary(bundle.getString("error_echec")
								+ ": "
								+ configBundle
										.getString("parametrage_configuration_error_key_already_exist")
								+ " (" + refParametreDto.getKey().trim() + ")");
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return;
					}
				}
			}
			if (refParametreDto.getGeneral() == null) {
				refParametreDto.setGeneral(false);
			}
			if (idpc != null) {
				refParametreServiceImpl.update(refParametreDto);
				addParameterForAllEtab();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setEntityChange(false);
				setKeyChange(false);
				setDescChange(false);
				setValueChange(false);
				setKey("false");

			} else {
				refParametreServiceImpl.save(refParametreDto);
				addParameterForAllEtab();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setEntityChange(false);
				setKeyChange(false);
				setDescChange(false);
				setValueChange(false);
				setKey("false");
				setIdpc(refParametreDto.getId().toString());

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
	 * [ParametreBckBean.addParameterForAllEtab] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 15:40:12
	 */
	public void addParameterForAllEtab() {
		RefParametreEtabDto refParametreEtabDto = new RefParametreEtabDto();
		refParametreEtabDto.setValue(refParametreDto.getDefaultValue());
		refParametreEtabDto.setDescription(refParametreDto.getDescription());
		refParametreEtabDto.setIdParam(refParametreDto.getId());
		refParametreEtabDto.setKey(refParametreDto.getKey());

		if (refParametreDto.getGeneral()) {
			//refParametreEtabDto.setIdfEtablissement(null);
			refParametreEtabDto.setIdEtablissement(null);
			refParametreEtabServiceImpl.save(refParametreEtabDto);
		} else {
			List<RefEtablissementDto> etabsList = comboBckBean
					.getListEtablissements();
			for (RefEtablissementDto current : etabsList) {
				refParametreEtabDto = refParametreEtabServiceImpl
						.findByKeyEtab(current.getIdentifiant(),
								refParametreDto.getKey());
				if (refParametreEtabDto == null) {
					refParametreEtabDto = new RefParametreEtabDto();
					refParametreEtabDto.setValue(refParametreDto
							.getDefaultValue());
					refParametreEtabDto.setDescription(refParametreDto
							.getDescription());
					refParametreEtabDto.setIdParam(refParametreDto.getId());
					refParametreEtabDto.setKey(refParametreDto.getKey());

					refParametreEtabDto.setIdEtablissement(current
							.getId());
					refParametreEtabServiceImpl.save(refParametreEtabDto);
				}
			}
		}

	}

	/**
	 * [ConfigurationBckBean.addParameter] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 16:34:55
	 */
	public void addParameter() {
		refParametreDto = new RefParametreDto();
	}

	/**
	 * [ConfigurationBckBean.back] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 16:35:58
	 * @return
	 */
	public String back() {
		return "toSearch";
	}

	/**
	 * [ConfigurationBckBean.key] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 avr. 2014 15:35:45
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * [ConfigurationBckBean.key] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 avr. 2014 15:35:45
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
		setKeyChange(Boolean.parseBoolean(key));
	}

	/**
	 * [ParametreBckBean.refParametreEtabServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 15:25:03
	 * @return the refParametreEtabServiceImpl
	 */
	public RefParametreEtabService getRefParametreEtabServiceImpl() {
		return refParametreEtabServiceImpl;
	}

	/**
	 * [ParametreBckBean.refParametreEtabServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 15:25:03
	 * @param refParametreEtabServiceImpl
	 *            the refParametreEtabServiceImpl to set
	 */
	public void setRefParametreEtabServiceImpl(
			RefParametreEtabService refParametreEtabServiceImpl) {
		this.refParametreEtabServiceImpl = refParametreEtabServiceImpl;
	}

	/**
	 * [ParametreBckBean.comboBckBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 15:26:50
	 * @return the comboBckBean
	 */
	public ComboBckBean getComboBckBean() {
		return comboBckBean;
	}

	/**
	 * [ParametreBckBean.comboBckBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 15:26:50
	 * @param comboBckBean
	 *            the comboBckBean to set
	 */
	public void setComboBckBean(ComboBckBean comboBckBean) {
		this.comboBckBean = comboBckBean;
	}

}
