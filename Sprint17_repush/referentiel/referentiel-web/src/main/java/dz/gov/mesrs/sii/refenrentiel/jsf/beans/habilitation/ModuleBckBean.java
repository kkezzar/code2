/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation.domaine.ModuleBckBean.java] 
 * @author MAKERRI Sofiane on : 23 f�vr. 2014  17:38:48
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefModuleDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService;
import dz.gov.mesrs.sii.referentiel.business.service.RefModuleService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 23 f�vr. 2014 17:38:48
 */
@ManagedBean(name = "moduleBckBean")
@RequestScoped
public class ModuleBckBean implements Serializable, Converter {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 17:40:07
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty("#{param.idfm}")
	private String idfm;
	@ManagedProperty("#{param.idfDomaine}")
	private String idfDomaine;
	private ResourceBundle bundle;
	private ResourceBundle habBundle;
	private static final Logger log = LoggerFactory.getLogger(ModuleBckBean.class.getName());
	@ManagedProperty(value = "#{refModuleServiceImpl}")
	private RefModuleService refModuleServiceImpl;
	@ManagedProperty(value = "#{refDomaineServiceImpl}")
	private RefDomaineService refDomaineServiceImpl;
	@ManagedProperty(value = "#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabServiceImpl;
	@ManagedProperty(value = "#{refFonctionServiceImpl}")
	private RefFonctionService refFonctionServiceImpl;
	private RefModuleDto refModuleDto;
	private boolean idfModuleChange;
	private boolean nameModuleChange;
	private boolean nameDomaineChange;
	private boolean nameArabeModuleChange;
	private boolean rangModuleChange;

	private boolean iconModuleChange;
	private boolean tooltipModuleChange;
	private boolean descriptionModuleChange;

	private boolean moduleChange;
	private int rang = 1;
	private List<RefModuleDto> listModuleDto;
	private List<SelectItem> listDomaine;
	private List<RefFonctionDto> listFonctionDto;
	private int identifiantLength;

	/**
	 * [ModuleBckBean.nameDomaineChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 mars 2014 18:04:43
	 * @return the nameDomaineChange
	 */
	public boolean isNameDomaineChange() {
		return nameDomaineChange;
	}

	/**
	 * [ModuleBckBean.nameDomaineChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 mars 2014 18:04:43
	 * @param nameDomaineChange
	 *            the nameDomaineChange to set
	 */
	public void setNameDomaineChange(boolean nameDomaineChange) {
		this.nameDomaineChange = nameDomaineChange;
	}

	/**
	 * [ModuleBckBean.ModuleBckBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 17:38:48
	 */
	public ModuleBckBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		habBundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.HABILITATION_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		if (idfm != null) {
			refModuleDto = refModuleServiceImpl.findByIdentifiant(idfm);
			listModuleDto = refModuleServiceImpl.findModules(refModuleDto.getIdDomaine());
			loadFonctions();

		}

		if (idfm == null || refModuleDto == null) {
			refModuleDto = new RefModuleDto();
			refModuleDto.setRang(1);
			if (idfDomaine != null) {
				RefDomaineDto refDomaineDto = refDomaineServiceImpl.findByIdentifiant(idfDomaine);
				if (refDomaineDto != null) {
					refModuleDto.setIdDomaine(refDomaineDto.getId());
					refModuleDto.setIdentifiantDomaine(refDomaineDto.getIdentifiant());
					int rang = refModuleServiceImpl.findModuleLastRang(refDomaineDto.getId());
					refModuleDto.setRang(rang);
					listModuleDto = refModuleServiceImpl.findModules(refDomaineDto.getId());
				}

			}
		}
	}

	/**
	 * [ModuleBckBean.loadFonctions] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 mars 2014 12:12:25
	 */
	public void loadFonctions() {
		if (refModuleDto != null && refModuleDto.getId() != null) {
			listFonctionDto = refFonctionServiceImpl.findFonctions(refModuleDto.getId());
		}
	}

	/**
	 * [ModuleBckBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 17:40:38
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [ModuleBckBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 17:40:38
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if ((searchValue != null) && (searchValue.equals("null"))) {
			this.searchValue = "";
		} else {
			this.searchValue = searchValue;
		}
	}

	/**
	 * [ModuleBckBean.idfm] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 17:40:38
	 * @return the idfm
	 */
	public String getIdfm() {
		return idfm;
	}

	/**
	 * [ModuleBckBean.idfm] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 17:40:38
	 * @param idfm
	 *            the idfm to set
	 */
	public void setIdfm(String idfm) {
		if ((idfm != null) && (idfm.equals("null") || idfm.trim().isEmpty())) {
			this.idfm = null;
		} else {
			this.idfm = idfm;
		}

	}

	/**
	 * [ModuleBckBean.bundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 17:40:38
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [ModuleBckBean.bundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 17:40:38
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [ModuleBckBean.habBundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 17:40:38
	 * @return the habBundle
	 */
	public ResourceBundle getHabBundle() {
		return habBundle;
	}

	/**
	 * [ModuleBckBean.habBundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 17:40:38
	 * @param habBundle
	 *            the habBundle to set
	 */
	public void setHabBundle(ResourceBundle habBundle) {
		this.habBundle = habBundle;
	}

	/**
	 * [ModuleBckBean.refModuleServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 17:40:38
	 * @return the refModuleServiceImpl
	 */
	public RefModuleService getRefModuleServiceImpl() {
		return refModuleServiceImpl;
	}

	/**
	 * [ModuleBckBean.refModuleServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 17:40:38
	 * @param refModuleServiceImpl
	 *            the refModuleServiceImpl to set
	 */
	public void setRefModuleServiceImpl(RefModuleService refModuleServiceImpl) {
		this.refModuleServiceImpl = refModuleServiceImpl;
	}

	/**
	 * [ModuleBckBean.refModuleDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 17:42:01
	 * @return the refModuleDto
	 */
	public RefModuleDto getRefModuleDto() {
		return refModuleDto;
	}

	/**
	 * [ModuleBckBean.refModuleDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 17:42:01
	 * @param refModuleDto
	 *            the refModuleDto to set
	 */
	public void setRefModuleDto(RefModuleDto refModuleDto) {
		this.refModuleDto = refModuleDto;
	}

	/**
	 * [ModuleBckBean.back] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 12:22:37
	 * @return
	 */
	public String back() {
		return "toSearchModule";
	}

	/**
	 * [ModuleBckBean.toRangEdit] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 12:26:09
	 * @return
	 */
	public String toRangEdit() {
		return "toRangEdit";
	}

	/**
	 * [ModuleBckBean.idfModuleChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 12:22:49
	 * @return the idfModuleChange
	 */
	public boolean isIdfModuleChange() {
		return idfModuleChange;
	}

	/**
	 * [ModuleBckBean.idfModuleChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 12:22:49
	 * @param idfModuleChange
	 *            the idfModuleChange to set
	 */
	public void setIdfModuleChange(boolean idfModuleChange) {
		this.idfModuleChange = idfModuleChange;
	}

	/**
	 * [ModuleBckBean.nameModuleChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 12:22:49
	 * @return the nameModuleChange
	 */
	public boolean isNameModuleChange() {
		return nameModuleChange;
	}

	/**
	 * [ModuleBckBean.nameModuleChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 12:22:49
	 * @param nameModuleChange
	 *            the nameModuleChange to set
	 */
	public void setNameModuleChange(boolean nameModuleChange) {
		this.nameModuleChange = nameModuleChange;
	}

	/**
	 * [ModuleBckBean.idfModuleChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 12:23:28
	 * @param event
	 */
	public void idfModuleChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			idfModuleChange = true;
			moduleChange = true;
		}
	}

	/**
	 * [ModuleBckBean.nameModuleChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 12:23:44
	 * @param event
	 */
	public void nameModuleChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			nameModuleChange = true;
			moduleChange = true;
		}
	}

	/**
	 * [ModuleBckBean.nameArabeModuleChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 mai 2014 09:34:05
	 * @param event
	 */
	public void nameArabeModuleChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			nameArabeModuleChange = true;
			moduleChange = true;
		}
	}

	/**
	 * [ModuleBckBean.nameDomaineChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 mai 2014 09:33:52
	 * @param event
	 */
	public void nameDomaineChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			nameDomaineChange = true;
			moduleChange = true;
			listModuleDto = refModuleServiceImpl.findModules((Integer) event.getNewValue());
		}
	}

	public void onOtherChange(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			moduleChange = true;

		}
	}

	/**
	 * [ModuleBckBean.saveModule] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 12:31:33
	 */
	public void saveModule() {

		FacesMessage msg = new FacesMessage();

		try {

			if (!idfModuleChange && idfm != null && !nameModuleChange && !nameArabeModuleChange && !moduleChange) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": " + bundle.getString("warn_info_update_none"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}

			if (idfModuleChange) {
				RefModuleDto refModuleDtoFound = refModuleServiceImpl.findByIdentifiant(refModuleDto.getIdentifiant());
				if (refModuleDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ habBundle.getString("module_error_identifiant_already_exist") + " ("
							+ refModuleDto.getIdentifiant() + ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
			}

			if (nameModuleChange) {
				RefModuleDto refModuleDtoFound = refModuleServiceImpl.findByName(refModuleDto.getId(),
						refModuleDto.getNom());
				if (refModuleDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ habBundle.getString("module_error_name_already_exist") + " (" + refModuleDto.getNom()
							+ ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
			}

			if (nameArabeModuleChange) {
				RefModuleDto refModuleDtoFound = refModuleServiceImpl.findByNameArabe(refModuleDto.getId(),
						refModuleDto.getNom());
				if (refModuleDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ habBundle.getString("module_error_name_already_exist") + " (" + refModuleDto.getNom()
							+ ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
			}

			if (moduleChange) {
				if (idfm != null) {

					refModuleServiceImpl.update(refModuleDto);
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundle.getString("msg_success") + ": "
							+ bundle.getString("msg_success_modification"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					int rang = refModuleServiceImpl.findModuleLastRang(refModuleDto.getIdDomaine());

					refModuleDto.setRang(rang);
					String nextIdentifiy = generateIdentify();
					if (nextIdentifiy == null) {
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						msg.setSummary(bundle.getString("error_echec") + ": "
								+ bundle.getString("error_generation_identify"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return;
					} else if (nextIdentifiy.length() > identifiantLength) {
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						msg.setSummary(bundle.getString("error_echec") + ": "
								+ bundle.getString("error_identify_max_legnt_affected"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return;
					}
					refModuleDto.setIdentifiant(nextIdentifiy);
					refModuleServiceImpl.save(refModuleDto);
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundle.getString("msg_success") + ": "
							+ bundle.getString("msg_success_enregistrement"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					RefDomaineDto refDomaineDto = refDomaineServiceImpl.findById(refModuleDto.getIdDomaine());
					if (refDomaineDto != null) {
						refModuleDto.setIdentifiantDomaine(refDomaineDto.getIdentifiant());
						listModuleDto = refModuleServiceImpl.findModules(refModuleDto.getIdDomaine());
					}
					setIdfm(refModuleDto.getIdentifiant());
					setIdfDomaine(refModuleDto.getIdentifiantDomaine());

				}
				setModuleChange(false);
				setRangModuleChange(false);
			}

		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": " + bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.error("Une erreur est survenue lors de la sauveguarde du module", e);
		}

	}

	/**
	 * [ModuleBckBean.moduleChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 12:30:03
	 * @return the moduleChange
	 */
	public boolean isModuleChange() {
		return moduleChange;
	}

	/**
	 * [ModuleBckBean.moduleChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 12:30:03
	 * @param moduleChange
	 *            the moduleChange to set
	 */
	public void setModuleChange(boolean moduleChange) {
		this.moduleChange = moduleChange;
	}

	/**
	 * [ModuleBckBean.idDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 13:57:42
	 * @return the idfDomaine
	 */
	public String getIdfDomaine() {
		return idfDomaine;
	}

	/**
	 * [ModuleBckBean.idfDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 13:57:42
	 * @param idfDomaine
	 *            the idfDomaine to set
	 */
	public void setIdfDomaine(String idfDomaine) {
		if ((idfDomaine != null) && (idfDomaine.equals("null"))) {
			this.idfDomaine = null;
		} else {
			this.idfDomaine = idfDomaine;
		}
	}

	/**
	 * [ModuleBckBean.refDomaineServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 14:06:16
	 * @return the refDomaineServiceImpl
	 */
	public RefDomaineService getRefDomaineServiceImpl() {
		return refDomaineServiceImpl;
	}

	/**
	 * [ModuleBckBean.refDomaineServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 14:06:16
	 * @param refDomaineServiceImpl
	 *            the refDomaineServiceImpl to set
	 */
	public void setRefDomaineServiceImpl(RefDomaineService refDomaineServiceImpl) {
		this.refDomaineServiceImpl = refDomaineServiceImpl;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		log.info("getAsString Object:" + value);
		if (value == null || value.equals("")) {
			return "";
		} else {
			return ((RefModuleDto) value).getIdentifiant();
		}
	}

	/**
	 * [ModuleBckBean.rang] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 17:32:02
	 * @return the rang
	 */
	public int getRang() {
		return rang;
	}

	/**
	 * [ModuleBckBean.rang] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 17:32:02
	 * @param rang
	 *            the rang to set
	 */
	public void setRang(int rang) {
		this.rang = rang;
	}

	/**
	 * [ModuleBckBean.listModuleDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 17:33:21
	 * @return the listModuleDto
	 */
	public List<RefModuleDto> getListModuleDto() {
		return listModuleDto;
	}

	/**
	 * [ModuleBckBean.listModuleDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 17:33:21
	 * @param listModuleDto
	 *            the listModuleDto to set
	 */
	public void setListModuleDto(List<RefModuleDto> listModuleDto) {
		this.listModuleDto = listModuleDto;
	}

	/**
	 * [ModuleBckBean.saveRang] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 f�vr. 2014 09:07:38
	 */
	public void saveRang() {
		log.info("save rang");
		if (rangModuleChange) {
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundle.getString("msg_success") + ": " + bundle.getString("msg_success_modification"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.trim().equals("")) {
			return null;
		} else {
			try {

				RefModuleDto _refModuleDto = refModuleServiceImpl.findByIdentifiant(value);
				if (_refModuleDto.getRang() != rang) {
					_refModuleDto.setRang(rang);
					refModuleServiceImpl.update(_refModuleDto);
					if (idfm != null && _refModuleDto.getIdentifiant().equals(idfm)) {
						_refModuleDto.setRang(rang);
						setRangModuleChange(true);
						setRefModuleDto(_refModuleDto);
					}
				}
				rang++;
				return _refModuleDto;
			} catch (ConverterException exception) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"invalid domaine"));
			} catch (RuntimeException exception) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update Error",
						"erreur de mise � jour"));
			}

		}

	}

	/**
	 * [ModuleBckBean.toRang] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 f�vr. 2014 11:43:02
	 * @return
	 */
	public String toRang() {
		return "toRangModule";
	}

	/**
	 * [ModuleBckBean.rangModuleChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 f�vr. 2014 08:54:20
	 * @return the rangModuleChange
	 */
	public boolean isRangModuleChange() {
		return rangModuleChange;
	}

	/**
	 * [ModuleBckBean.rangModuleChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 f�vr. 2014 08:54:20
	 * @param rangModuleChange
	 *            the rangModuleChange to set
	 */
	public void setRangModuleChange(boolean rangModuleChange) {
		this.rangModuleChange = rangModuleChange;
	}

	/**
	 * [ModuleBckBean.listDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 mars 2014 11:33:38
	 * @return the listDomaine
	 */
	public List<SelectItem> getListDomaine() {
		listDomaine = new ArrayList<SelectItem>();
		List<RefDomaineDto> list = refDomaineServiceImpl.findDomainesAndSubDomaines();
		for (RefDomaineDto refDomaineDto : list) {
			SelectItem selectItem = new SelectItem(refDomaineDto.getId(), refDomaineDto.getNom());
			listDomaine.add(selectItem);
		}
		return listDomaine;
	}

	/**
	 * [ModuleBckBean.listDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 mars 2014 11:33:38
	 * @param listDomaine
	 *            the listDomaine to set
	 */
	public void setListDomaine(List<SelectItem> listDomaine) {
		this.listDomaine = listDomaine;
	}

	/**
	 * [ModuleBckBean.listFonctionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 mars 2014 12:11:14
	 * @return the listFonctionDto
	 */
	public List<RefFonctionDto> getListFonctionDto() {
		return listFonctionDto;
	}

	/**
	 * [ModuleBckBean.listFonctionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 mars 2014 12:11:14
	 * @param listFonctionDto
	 *            the listFonctionDto to set
	 */
	public void setListFonctionDto(List<RefFonctionDto> listFonctionDto) {
		this.listFonctionDto = listFonctionDto;
	}

	/**
	 * [ModuleBckBean.refFonctionServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 mars 2014 12:14:22
	 * @return the refFonctionServiceImpl
	 */
	public RefFonctionService getRefFonctionServiceImpl() {
		return refFonctionServiceImpl;
	}

	/**
	 * [ModuleBckBean.refFonctionServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 mars 2014 12:14:22
	 * @param refFonctionServiceImpl
	 *            the refFonctionServiceImpl to set
	 */
	public void setRefFonctionServiceImpl(RefFonctionService refFonctionServiceImpl) {
		this.refFonctionServiceImpl = refFonctionServiceImpl;
	}

	/**
	 * [ModuleBckBean.generateIdentify] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 avr. 2014 15:52:04
	 * @return
	 */
	public String generateIdentify() {
		try {

			String prefix = refParametreEtabServiceImpl.findValueOfKey(SessionValues.getIdEtablissement(),
					RefUtilConstant.CODIFICATION_MODULE_PREFIX_KEY);
			if (prefix == null) {
				return null;
			}
			int prefixLength = prefix.length();
			String order = refParametreEtabServiceImpl.findValueOfKey(SessionValues.getIdEtablissement(),
					RefUtilConstant.CODIFICATION_MODULE_ORDER_LENGTH_KEY);
			if (order == null) {
				return null;
			}
			int orderLength = Integer.parseInt(order);
			identifiantLength = prefixLength + orderLength;
			return refModuleServiceImpl.generateIdentify(prefix, orderLength);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;

	}

	/**
	 * [ModuleBckBean.refParametreEtabServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 17:44:24
	 * @return the refParametreEtabServiceImpl
	 */
	public RefParametreEtabService getRefParametreEtabServiceImpl() {
		return refParametreEtabServiceImpl;
	}

	/**
	 * [ModuleBckBean.refParametreEtabServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 17:44:24
	 * @param refParametreEtabServiceImpl
	 *            the refParametreEtabServiceImpl to set
	 */
	public void setRefParametreEtabServiceImpl(RefParametreEtabService refParametreEtabServiceImpl) {
		this.refParametreEtabServiceImpl = refParametreEtabServiceImpl;
	}

	/**
	 * [ModuleBckBean.identifiantLength] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 17:42:49
	 * @return the identifiantLength
	 */
	public int getIdentifiantLength() {
		return identifiantLength;
	}

	/**
	 * [ModuleBckBean.identifiantLength] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 17:42:49
	 * @param identifiantLength
	 *            the identifiantLength to set
	 */
	public void setIdentifiantLength(int identifiantLength) {
		this.identifiantLength = identifiantLength;
	}

	/**
	 * [ModuleBckBean.nameArabeModuleChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 mai 2014 09:35:33
	 * @return the nameArabeModuleChange
	 */
	public boolean isNameArabeModuleChange() {
		return nameArabeModuleChange;
	}

	/**
	 * [ModuleBckBean.nameArabeModuleChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 mai 2014 09:35:33
	 * @param nameArabeModuleChange
	 *            the nameArabeModuleChange to set
	 */
	public void setNameArabeModuleChange(boolean nameArabeModuleChange) {
		this.nameArabeModuleChange = nameArabeModuleChange;
	}

	public boolean isIconModuleChange() {
		return iconModuleChange;
	}

	public void setIconModuleChange(boolean iconModuleChange) {
		this.iconModuleChange = iconModuleChange;
	}

	public boolean isDescriptionModuleChange() {
		return descriptionModuleChange;
	}

	public void setDescriptionModuleChange(boolean descriptionModuleChange) {
		this.descriptionModuleChange = descriptionModuleChange;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 13 oct. 2014 17:48:08
	 * @param event
	 */
	public void iconModuleChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			iconModuleChange = true;
			moduleChange = true;
		}
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 13 oct. 2014 17:48:08
	 * @param event
	 */
	public void tooltipModuleChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			tooltipModuleChange = true;
			moduleChange = true;
		}
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 13 oct. 2014 17:48:08
	 * @param event
	 */
	public void descriptionModuleChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			descriptionModuleChange = true;
			moduleChange = true;
		}
	}

}
