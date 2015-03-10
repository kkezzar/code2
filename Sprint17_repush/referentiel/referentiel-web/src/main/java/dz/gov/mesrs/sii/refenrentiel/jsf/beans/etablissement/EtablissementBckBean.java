/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.etablissement.EtablissementBckBean.java] 
 * @author MAKERRI Sofiane on : 10 avr. 2014  14:21:43
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.etablissement;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.ComboBckBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParametreDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParametreEtabDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 10 avr. 2014 14:21:43
 */
@ManagedBean(name = "etablissementBckBean")
@RequestScoped
public class EtablissementBckBean implements Serializable {

	private static final long serialVersionUID = 1L;
	/*
	 * @ManagedProperty("#{param.ide}") private String ide;
	 */
	@ManagedProperty("#{param.id}")
	private String id;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	private RefEtablissementDto refEtablissementDto;
	@ManagedProperty(value = "#{refEtablissementServiceImpl}")
	private RefEtablissementService refEtablissementServiceImpl;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	private static final Log log = LogFactory
			.getLog(EtablissementBckBean.class);
	private ResourceBundle bundle;
	private ResourceBundle etabBundle;
	private boolean entityChange;
	private boolean codeChange;
	@ManagedProperty("#{flash}")
	private Flash flash;
	private boolean idfChange;
	private boolean llLatinChange;
	private boolean llArabeChange;
	private boolean lcLatinChange;
	private boolean lcArabeChange;
	@ManagedProperty(value = "#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabServiceImpl;
	@ManagedProperty(value = "#{refParametreServiceImpl}")
	private RefParametreService refParametreServiceImpl;
	private int identifiantLength;
	@ManagedProperty(value = "#{comboBckBean}")
	private ComboBckBean comboBckBean;

	/**
	 * [EtablissementBckBean.EtablissementBckBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:21:43
	 */
	public EtablissementBckBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		etabBundle = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.ETABLISSEMENT_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		if (id != null) {
			int _id = RefUtilConstant.strToInt(id);
			if (_id != -1) {
				refEtablissementDto = refEtablissementServiceImpl.findById(_id);
			}
		}

		if (refEtablissementDto == null) {
			refEtablissementDto = new RefEtablissementDto();
			setDefaultValues();
		}
	}

	/**
	 * [EtablissementBckBean.ide] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:24:30
	 * @return the ide
	 */
	/*
	 * public String getIde() { return ide; }*-/
	 * 
	 * /** [EtablissementBckBean.ide] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:24:30
	 * 
	 * @param ide the ide to set
	 */
	/*
	 * public void setIde(String ide) { if (ide != null && ide.equals("null")) {
	 * this.ide = null; } else this.ide = ide; }
	 */

	/**
	 * [EtablissementBckBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:24:30
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [EtablissementBckBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:24:30
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
	 * [EtablissementBckBean.refEtablissementDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:24:30
	 * @return the refEtablissementDto
	 */
	public RefEtablissementDto getRefEtablissementDto() {
		return refEtablissementDto;
	}

	/**
	 * [EtablissementBckBean.refEtablissementDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:24:30
	 * @param refEtablissementDto
	 *            the refEtablissementDto to set
	 */
	public void setRefEtablissementDto(RefEtablissementDto refEtablissementDto) {
		this.refEtablissementDto = refEtablissementDto;
	}

	/**
	 * [EtablissementBckBean.refEtablissementServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:24:30
	 * @return the refEtablissementServiceImpl
	 */
	public RefEtablissementService getRefEtablissementServiceImpl() {
		return refEtablissementServiceImpl;
	}

	/**
	 * [EtablissementBckBean.refEtablissementServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:24:30
	 * @param refEtablissementServiceImpl
	 *            the refEtablissementServiceImpl to set
	 */
	public void setRefEtablissementServiceImpl(
			RefEtablissementService refEtablissementServiceImpl) {
		this.refEtablissementServiceImpl = refEtablissementServiceImpl;
	}

	/**
	 * [EtablissementBckBean.entityChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 avr. 2014 16:07:29
	 * @param event
	 */
	public void entityChanged(ValueChangeEvent event) {
		entityChange = true;
	}

	/**
	 * [EtablissementBckBean.codeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 juil. 2014 17:46:11
	 * @param event
	 */
	public void codeChanged(ValueChangeEvent event) {
		entityChange = true;
		codeChange = true;
	}

	/**
	 * [EtablissementBckBean.idfChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 10:01:19
	 * @param event
	 */
	public void idfChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			idfChange = true;
			entityChange = true;
		}
	}

	/**
	 * [EtablissementBckBean.llLatinChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 09:51:32
	 * @param event
	 */
	public void llLatinChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			llLatinChange = true;
			entityChange = true;
		}
	}

	/**
	 * [EtablissementBckBean.llArabeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 09:51:35
	 * @param event
	 */
	public void llArabeChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			llArabeChange = true;
			entityChange = true;
		}
	}

	/**
	 * [EtablissementBckBean.lcLatinChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 09:51:38
	 * @param event
	 */
	public void lcLatinChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			lcLatinChange = true;
			entityChange = true;
		}
	}

	/**
	 * [EtablissementBckBean.lcArabeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 09:51:41
	 * @param event
	 */
	public void lcArabeChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			lcArabeChange = true;
			entityChange = true;
		}
	}

	/**
	 * [EtablissementBckBean.save] Method
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 14:57:55
	 */
	public String save() {
		FacesMessage msg = new FacesMessage();
		try {

			if (!entityChange) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_update_none"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return null;
			}

			if (idfChange && refEtablissementDto.getIdentifiant() != null) {
				RefEtablissementDto refEtablissementDtoFound = refEtablissementServiceImpl
						.findByIdentifiant(refEtablissementDto.getIdentifiant()
								.trim());
				if (refEtablissementDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec")
							+ ": "
							+ etabBundle
									.getString("etablissement_error_identifiant_already_exist")
							+ " ("
							+ refEtablissementDto.getIdentifiant().trim() + ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return null;
				}
			}

			if (llLatinChange
					&& refEtablissementDto.getLlEtablissementLatin() != null) {
				RefEtablissementDto refEtablissementDtoFound = refEtablissementServiceImpl
						.findByLlLatin(refEtablissementDto
								.getLlEtablissementLatin().trim());
				if (refEtablissementDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec")
							+ ": "
							+ etabBundle
									.getString("etablissement_error_llLatin_already_exist")
							+ " ("
							+ refEtablissementDto.getLlEtablissementLatin()
									.trim() + ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return null;
				}
			}

			if (llArabeChange
					&& refEtablissementDto.getLlEtablissementArabe() != null) {
				RefEtablissementDto refEtablissementDtoFound = refEtablissementServiceImpl
						.findByLlArabe(refEtablissementDto
								.getLlEtablissementArabe().trim());
				if (refEtablissementDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec")
							+ ": "
							+ etabBundle
									.getString("etablissement_error_llArabe_already_exist")
							+ " ("
							+ refEtablissementDto.getLlEtablissementArabe()
									.trim() + ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return null;
				}
			}

			if (lcLatinChange
					&& refEtablissementDto.getLcEtablissementLatin() != null) {
				RefEtablissementDto refEtablissementDtoFound = refEtablissementServiceImpl
						.findByLcLatin(refEtablissementDto
								.getLcEtablissementLatin().trim());
				if (refEtablissementDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec")
							+ ": "
							+ etabBundle
									.getString("etablissement_error_lcLatin_already_exist")
							+ " ("
							+ refEtablissementDto.getLcEtablissementLatin()
									.trim() + ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return null;
				}
			}

			if (lcArabeChange
					&& refEtablissementDto.getLcEtablissementArabe() != null) {
				RefEtablissementDto refEtablissementDtoFound = refEtablissementServiceImpl
						.findByLcArabe(refEtablissementDto
								.getLcEtablissementArabe().trim());
				if (refEtablissementDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec")
							+ ": "
							+ etabBundle
									.getString("etablissement_error_lcArabe_already_exist")
							+ " ("
							+ refEtablissementDto.getLcEtablissementArabe()
									.trim() + ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return null;
				}
			}
			if (codeChange) {
				String nextIdentifiy = generateIdentify();
				if (nextIdentifiy == null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ bundle.getString("error_generation_identify"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return null;
				} else if (nextIdentifiy.length() > identifiantLength) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec")
							+ ": "
							+ bundle.getString("error_identify_max_legnt_affected"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return null;
				}
				refEtablissementDto.setIdentifiant(nextIdentifiy);
			}

			if (id != null) {
				refEtablissementServiceImpl.update(refEtablissementDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setEntityChange(false);
				setIdfChange(false);
				setCodeChange(false);
				setLlArabeChange(false);
				setLlLatinChange(false);
				setLcArabeChange(false);
				setLcLatinChange(false);
			} else {

				refEtablissementServiceImpl.save(refEtablissementDto);
				addParameterForEtab(refEtablissementDto.getId());
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setEntityChange(false);
				setIdfChange(false);
				setLlArabeChange(false);
				setLlLatinChange(false);
				setLcArabeChange(false);
				setLcLatinChange(false);
				setId(refEtablissementDto.getId().toString());
				flash.setKeepMessages(true);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpServletRequest request = (HttpServletRequest) facesContext
						.getExternalContext().getRequest();
				request.setAttribute("id", refEtablissementDto.getId());
				return "etabEdit?faces-redirect=true&id=" + id;
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
		return null;
	}

	/**
	 * [EtablissementBckBean.calculateIdentify] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 11:42:11
	 * @return
	 */
	public String generateIdentify() {
		NomenclatureDto ncTypeEtabDto = nomenclatureServiceImpl
				.findById(refEtablissementDto.getTypeId());
		if (ncTypeEtabDto != null && ncTypeEtabDto.getCode() != null) {
			refEtablissementDto.setTypeCode(ncTypeEtabDto.getCode());
		}
		NomenclatureDto ncWilayaDto = nomenclatureServiceImpl
				.findById(refEtablissementDto.getWilayaId());
		if (ncWilayaDto != null && ncWilayaDto.getCode() != null) {
			refEtablissementDto.setWilayaCode(ncWilayaDto.getCode());
		}
		String order = refParametreEtabServiceImpl
				.findValueOfKey(RefUtilConstant.CODIFICATION_ETABLISSEMENT_ORDER_LENGTH_KEY);
		if (order == null) {
			return null;
		}
		String prefix = refEtablissementDto.getTypeCode()
				+ refEtablissementDto.getWilayaCode();
		int prefixLength = prefix.length();
		int orderLength = Integer.parseInt(order);
		identifiantLength = prefixLength + orderLength;
		String nextIdentifiy = refEtablissementServiceImpl.generateIdentify(
				prefix, orderLength);
		return nextIdentifiy;
	}

	/**
	 * [EtablissementBckBean.entityChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 avr. 2014 16:07:58
	 * @return the entityChange
	 */
	public boolean isEntityChange() {
		return entityChange;
	}

	/**
	 * [EtablissementBckBean.entityChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 avr. 2014 16:07:58
	 * @param entityChange
	 *            the entityChange to set
	 */
	public void setEntityChange(boolean entityChange) {
		this.entityChange = entityChange;
	}

	/**
	 * [EtablissementBckBean.back] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 avr. 2014 16:51:15
	 * @return
	 */
	public String back() {
		return "toSearch";
	}

	/**
	 * [EtablissementBckBean.flash] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 avr. 2014 17:29:10
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [EtablissementBckBean.flash] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 avr. 2014 17:29:10
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [EtablissementBckBean.addEtab] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 avr. 2014 17:31:32
	 */
	public String addEtab() {
		refEtablissementDto = new RefEtablissementDto();
		setDefaultValues();
		return "etabEdit?faces-redirect=true";
	}

	/**
	 * [EtablissementBckBean.llLatinChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 09:54:18
	 * @return the llLatinChange
	 */
	public boolean isLlLatinChange() {
		return llLatinChange;
	}

	/**
	 * [EtablissementBckBean.llLatinChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 09:54:18
	 * @param llLatinChange
	 *            the llLatinChange to set
	 */
	public void setLlLatinChange(boolean llLatinChange) {
		this.llLatinChange = llLatinChange;
	}

	/**
	 * [EtablissementBckBean.llArabeChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 09:54:18
	 * @return the llArabeChange
	 */
	public boolean isLlArabeChange() {
		return llArabeChange;
	}

	/**
	 * [EtablissementBckBean.llArabeChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 09:54:18
	 * @param llArabeChange
	 *            the llArabeChange to set
	 */
	public void setLlArabeChange(boolean llArabeChange) {
		this.llArabeChange = llArabeChange;
	}

	/**
	 * [EtablissementBckBean.lcLatinChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 09:54:18
	 * @return the lcLatinChange
	 */
	public boolean isLcLatinChange() {
		return lcLatinChange;
	}

	/**
	 * [EtablissementBckBean.lcLatinChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 09:54:18
	 * @param lcLatinChange
	 *            the lcLatinChange to set
	 */
	public void setLcLatinChange(boolean lcLatinChange) {
		this.lcLatinChange = lcLatinChange;
	}

	/**
	 * [EtablissementBckBean.lcArabeChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 09:54:18
	 * @return the lcArabeChange
	 */
	public boolean isLcArabeChange() {
		return lcArabeChange;
	}

	/**
	 * [EtablissementBckBean.lcArabeChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 09:54:18
	 * @param lcArabeChange
	 *            the lcArabeChange to set
	 */
	public void setLcArabeChange(boolean lcArabeChange) {
		this.lcArabeChange = lcArabeChange;
	}

	/**
	 * [EtablissementBckBean.idfChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 10:01:38
	 * @return the idfChange
	 */
	public boolean isIdfChange() {
		return idfChange;
	}

	/**
	 * [EtablissementBckBean.idfChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 10:01:38
	 * @param idfChange
	 *            the idfChange to set
	 */
	public void setIdfChange(boolean idfChange) {
		this.idfChange = idfChange;
	}

	/**
	 * [EtablissementBckBean.nomenclatureServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 11:31:26
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	/**
	 * [EtablissementBckBean.nomenclatureServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 11:31:26
	 * @param nomenclatureServiceImpl
	 *            the nomenclatureServiceImpl to set
	 */
	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	/**
	 * [EtablissementBckBean.refParametreEtabServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 17:38:00
	 * @return the refParametreEtabServiceImpl
	 */
	public RefParametreEtabService getRefParametreEtabServiceImpl() {
		return refParametreEtabServiceImpl;
	}

	/**
	 * [EtablissementBckBean.refParametreEtabServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 17:38:00
	 * @param refParametreEtabServiceImpl
	 *            the refParametreEtabServiceImpl to set
	 */
	public void setRefParametreEtabServiceImpl(
			RefParametreEtabService refParametreEtabServiceImpl) {
		this.refParametreEtabServiceImpl = refParametreEtabServiceImpl;
	}

	/**
	 * [EtablissementBckBean.identifiantLength] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 avr. 2014 12:13:17
	 * @return the identifiantLength
	 */
	public int getIdentifiantLength() {
		return identifiantLength;
	}

	/**
	 * [EtablissementBckBean.identifiantLength] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 avr. 2014 12:13:17
	 * @param identifiantLength
	 *            the identifiantLength to set
	 */
	public void setIdentifiantLength(int identifiantLength) {
		this.identifiantLength = identifiantLength;
	}

	/**
	 * [EtablissementBckBean.comboBckBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 avr. 2014 12:34:10
	 * @return the comboBckBean
	 */
	public ComboBckBean getComboBckBean() {
		return comboBckBean;
	}

	/**
	 * [EtablissementBckBean.comboBckBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 avr. 2014 12:34:10
	 * @param comboBckBean
	 *            the comboBckBean to set
	 */
	public void setComboBckBean(ComboBckBean comboBckBean) {
		this.comboBckBean = comboBckBean;
	}

	/**
	 * [EtablissementBckBean.setDefaultValues] Method
	 * 
	 * @author MAKERRI Sofiane on : 29 avr. 2014 12:35:49
	 */
	public void setDefaultValues() {
		refEtablissementDto.setTypeId(comboBckBean
				.getDefaultTypeEtablissement());
		refEtablissementDto.setFormeJuridiqueId(comboBckBean
				.getDefaultFormeJuridique());
		refEtablissementDto.setWilayaId(comboBckBean.getDefaultWilaya());
	}

	/**
	 * [EtabParameterBean.addParameterForEtab] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 mai 2014 16:07:47
	 */
	public void addParameterForEtab(Integer idEtab) {

		List<RefParametreDto> list = refParametreServiceImpl
				.findAllNotGeneric();
		if (list == null) {
			return;
		}
		for (RefParametreDto current : list) {
			RefParametreEtabDto refParametreEtabDto = new RefParametreEtabDto();
			refParametreEtabDto.setValue(current.getDefaultValue());
			refParametreEtabDto.setDescription(current.getDescription());
			refParametreEtabDto.setIdParam(current.getId());
			refParametreEtabDto.setKey(current.getKey());
			refParametreEtabDto.setIdEtablissement(idEtab);
			refParametreEtabServiceImpl.save(refParametreEtabDto);
		}
	}

	/**
	 * [EtablissementBckBean.refParametreServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mai 2014 16:13:16
	 * @return the refParametreServiceImpl
	 */
	public RefParametreService getRefParametreServiceImpl() {
		return refParametreServiceImpl;
	}

	/**
	 * [EtablissementBckBean.refParametreServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mai 2014 16:13:16
	 * @param refParametreServiceImpl
	 *            the refParametreServiceImpl to set
	 */
	public void setRefParametreServiceImpl(
			RefParametreService refParametreServiceImpl) {
		this.refParametreServiceImpl = refParametreServiceImpl;
	}

	/**
	 * [EtablissementBckBean.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 mai 2014 13:52:06
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * [EtablissementBckBean.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 mai 2014 13:52:06
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		if (id != null && id.equals("null")) {
			this.id = null;
		} else
			this.id = id;
	}

	/**
	 * [EtablissementBckBean.codeChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 juil. 2014 17:46:28
	 * @return the codeChange
	 */
	public boolean isCodeChange() {
		return codeChange;
	}

	/**
	 * [EtablissementBckBean.codeChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 juil. 2014 17:46:28
	 * @param codeChange
	 *            the codeChange to set
	 */
	public void setCodeChange(boolean codeChange) {
		this.codeChange = codeChange;
	}

}
