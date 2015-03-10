/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation.FonctionBckBean.java] 
 * @author MAKERRI Sofiane on : 27 f�vr. 2014  16:42:05
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation;

import java.io.File;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefModuleDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService;
import dz.gov.mesrs.sii.referentiel.business.service.RefModuleService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:42:05
 */
@ManagedBean(name = "fonctionBckBean")
@RequestScoped
public class FonctionBckBean implements Serializable, Converter {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:44:08
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty("#{param.idfct}")
	private String idfct;
	@ManagedProperty("#{param.idfm}")
	private String idfm;
	private ResourceBundle bundle;
	private ResourceBundle habBundle;
	private static final Logger log = LoggerFactory.getLogger(FonctionBckBean.class.getName());
	@ManagedProperty(value = "#{refFonctionServiceImpl}")
	private RefFonctionService refFonctionServiceImpl;
	@ManagedProperty(value = "#{refModuleServiceImpl}")
	private RefModuleService refModuleServiceImpl;
	private int rang = 1;
	private List<RefFonctionDto> listFonctionDto;
	private List<String> listFilesUrl;
	private List<SelectItem> urlList;
	private RefFonctionDto refFonctionDto;
	private RefModuleDto refModuleDto;
	private boolean idfFonctionChange;
	private boolean nameFonctionChange;
	private boolean nameArabeFonctionChange;
	private boolean urlFonctionChange;
	private boolean fonctionChange;
	private boolean nameModuleChange;
	private boolean rangFonctionChange;
	private String path = "";
	private String racine;
	private List<SelectItem> listModule;
	private List<RefFonctionDto> listActionsDto;
	private int identifiantLength;
	@ManagedProperty(value = "#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabServiceImpl;

	/**
	 * [FonctionBckBean.FonctionBckBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:42:05
	 */
	public FonctionBckBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		habBundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.HABILITATION_MESSAGES_FILE_NAME);

	}

	@PostConstruct
	public void init() {
		if (idfct != null) {
			refFonctionDto = refFonctionServiceImpl.findByIdentifiant(idfct);
			listFonctionDto = refFonctionServiceImpl.findFonctions(refFonctionDto.getIdModule());
			listActionsDto = refFonctionServiceImpl.findActions(refFonctionDto.getId());

		}

		if (idfct == null || refFonctionDto == null) {
			refFonctionDto = new RefFonctionDto();
			refFonctionDto.setRang(1);
			if (idfm != null) {
				RefModuleDto refModuleDto = refModuleServiceImpl.findByIdentifiant(idfm);
				if (refModuleDto != null) {
					refFonctionDto.setIdModule(refModuleDto.getId());
					int rang = refFonctionServiceImpl.findFonctionLastRang(refModuleDto.getId());
					refFonctionDto.setRang(rang);
					listFonctionDto = refFonctionServiceImpl.findFonctions(refModuleDto.getId());
				}

			}

		}
	}

	/**
	 * [FonctionBckBean.loadUrls] Method
	 * 
	 * @author MAKERRI Sofiane on : 1 mars 2014 13:56:01
	 */
	public List<String> loadUrls() {
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		racine = extContext.getRealPath("pages");
		File dir = new File(racine);
		racine = dir.getName();
		listFilesUrl = new ArrayList<String>();
		loadUrl(dir, listFilesUrl);
		return listFilesUrl;
	}

	/**
	 * [FonctionBckBean.loadUrl] Method
	 * 
	 * @author MAKERRI Sofiane on : 1 mars 2014 13:56:06
	 * @param file
	 * @param urlList
	 */
	public void loadUrl(File file, List<String> urlList) {
		if (file != null) {

			if (file.isDirectory()) {
				path = path + "/" + file.getName();
				File[] list = file.listFiles();
				if (list != null) {
					for (int i = 0; i < list.length; i++) {

						loadUrl(list[i], urlList);
					}
					path = "/" + racine;
				}
			} else {
				String pathname = path + "/" + file.getName();
				urlList.add(pathname);

			}
		}
	}

	/**
	 * [FonctionBckBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:44:54
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [FonctionBckBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:44:54
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	/**
	 * [FonctionBckBean.idf] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:44:54
	 * @return the idf
	 */
	public String getIdfct() {
		return idfct;
	}

	/**
	 * [FonctionBckBean.idf] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:44:54
	 * @param idf
	 *            the idf to set
	 */
	public void setIdfct(String idfct) {
		if ((idfct != null) && (idfct.equals("null") || idfct.trim().isEmpty())) {
			this.idfct = null;
		} else {
			this.idfct = idfct;
		}
	}

	/**
	 * [FonctionBckBean.bundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:44:54
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [FonctionBckBean.bundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:44:54
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [FonctionBckBean.habBundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:44:54
	 * @return the habBundle
	 */
	public ResourceBundle getHabBundle() {
		return habBundle;
	}

	/**
	 * [FonctionBckBean.habBundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:44:54
	 * @param habBundle
	 *            the habBundle to set
	 */
	public void setHabBundle(ResourceBundle habBundle) {
		this.habBundle = habBundle;
	}

	/**
	 * [FonctionBckBean.refFonctionServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:44:54
	 * @return the refFonctionServiceImpl
	 */
	public RefFonctionService getRefFonctionServiceImpl() {
		return refFonctionServiceImpl;
	}

	/**
	 * [FonctionBckBean.refFonctionServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:44:54
	 * @param refFonctionServiceImpl
	 *            the refFonctionServiceImpl to set
	 */
	public void setRefFonctionServiceImpl(RefFonctionService refFonctionServiceImpl) {
		this.refFonctionServiceImpl = refFonctionServiceImpl;
	}

	/**
	 * [FonctionBckBean.rang] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:44:54
	 * @return the rang
	 */
	public int getRang() {
		return rang;
	}

	/**
	 * [FonctionBckBean.rang] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:44:54
	 * @param rang
	 *            the rang to set
	 */
	public void setRang(int rang) {
		this.rang = rang;
	}

	/**
	 * [FonctionBckBean.listFonctionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:44:54
	 * @return the listFonctionDto
	 */
	public List<RefFonctionDto> getListFonctionDto() {
		return listFonctionDto;
	}

	/**
	 * [FonctionBckBean.listFonctionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:44:54
	 * @param listFonctionDto
	 *            the listFonctionDto to set
	 */
	public void setListFonctionDto(List<RefFonctionDto> listFonctionDto) {
		this.listFonctionDto = listFonctionDto;
	}

	/**
	 * [FonctionBckBean.refFonctionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:45:27
	 * @return the refFonctionDto
	 */
	public RefFonctionDto getRefFonctionDto() {
		return refFonctionDto;
	}

	/**
	 * [FonctionBckBean.refFonctionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:45:27
	 * @param refFonctionDto
	 *            the refFonctionDto to set
	 */
	public void setRefFonctionDto(RefFonctionDto refFonctionDto) {
		this.refFonctionDto = refFonctionDto;
	}

	/**
	 * [FonctionBckBean.refModuleDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:47:32
	 * @return the refModuleDto
	 */
	public RefModuleDto getRefModuleDto() {
		return refModuleDto;
	}

	/**
	 * [FonctionBckBean.refModuleDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 16:47:32
	 * @param refModuleDto
	 *            the refModuleDto to set
	 */
	public void setRefModuleDto(RefModuleDto refModuleDto) {
		this.refModuleDto = refModuleDto;
	}

	/**
	 * [FonctionBckBean.back] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 18:04:47
	 * @return
	 */
	public String back() {
		return "toSearchFonction";
	}

	/**
	 * [FonctionBckBean.idfFonctionChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 18:48:25
	 * @param event
	 */
	public void idfFonctionChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			idfFonctionChange = true;
			fonctionChange = true;
		}
	}

	/**
	 * [FonctionBckBean.nameModuleChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 mars 2014 11:04:12
	 * @param event
	 */
	public void nameModuleChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			nameModuleChange = true;
			fonctionChange = true;
			listFonctionDto = refFonctionServiceImpl.findFonctions((Integer) event.getNewValue());
		}
	}

	/**
	 * [FonctionBckBean.nameFonctionChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 18:48:53
	 * @param event
	 */
	public void nameFonctionChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			nameFonctionChange = true;
			fonctionChange = true;
		}
	}

	public void nameArabeFonctionChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			nameArabeFonctionChange = true;
			fonctionChange = true;
		}
	}

	/**
	 * [FonctionBckBean.urlFonctionChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 3 avr. 2014 12:01:58
	 * @param event
	 */
	public void urlFonctionChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			urlFonctionChange = true;
			fonctionChange = true;
		}
	}

	/**
	 * [FonctionBckBean.periodeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 3 avr. 2014 12:02:45
	 * @param event
	 */
	public void periodeChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			if (event.getNewValue() == null) {
				refFonctionDto.setIdfPeriode(null);
				refFonctionDto.setNomPeriode(null);
				refFonctionDto.setDateDebutPeriode(null);
				refFonctionDto.setDateFinPeriode(null);
				refFonctionDto.setPeriodique(null);
			}
			fonctionChange = true;
		}
	}

	public void onAnotherValueChange(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			fonctionChange = true;
		}
	}

	/**
	 * [FonctionBckBean.idfFonctionChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 18:48:19
	 * @return the idfFonctionChange
	 */
	public boolean isIdfFonctionChange() {
		return idfFonctionChange;
	}

	/**
	 * [FonctionBckBean.idfFonctionChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 18:48:19
	 * @param idfFonctionChange
	 *            the idfFonctionChange to set
	 */
	public void setIdfFonctionChange(boolean idfFonctionChange) {
		this.idfFonctionChange = idfFonctionChange;
	}

	/**
	 * [FonctionBckBean.fonctionChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 18:48:19
	 * @return the fonctionChange
	 */
	public boolean isFonctionChange() {
		return fonctionChange;
	}

	/**
	 * [FonctionBckBean.fonctionChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 18:48:19
	 * @param fonctionChange
	 *            the fonctionChange to set
	 */
	public void setFonctionChange(boolean fonctionChange) {
		this.fonctionChange = fonctionChange;
	}

	/**
	 * [FonctionBckBean.nameFonctionChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 18:49:35
	 * @return the nameFonctionChange
	 */
	public boolean isNameFonctionChange() {
		return nameFonctionChange;
	}

	/**
	 * [FonctionBckBean.nameFonctionChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 18:49:35
	 * @param nameFonctionChange
	 *            the nameFonctionChange to set
	 */
	public void setNameFonctionChange(boolean nameFonctionChange) {
		this.nameFonctionChange = nameFonctionChange;
	}

	/**
	 * [FonctionBckBean.urlFonctionChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 18:49:35
	 * @return the urlFonctionChange
	 */
	public boolean isUrlFonctionChange() {
		return urlFonctionChange;
	}

	/**
	 * [FonctionBckBean.urlFonctionChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 18:49:35
	 * @param urlFonctionChange
	 *            the urlFonctionChange to set
	 */
	public void setUrlFonctionChange(boolean urlFonctionChange) {
		this.urlFonctionChange = urlFonctionChange;
	}

	/**
	 * [FonctionBckBean.saveFonction] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 18:50:17
	 */
	public void saveFonction() {

		FacesMessage msg = new FacesMessage();

		try {

			if (!fonctionChange && idfct != null) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": " + bundle.getString("warn_info_update_none"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}

			if (idfFonctionChange) {
				RefFonctionDto refFonctionDtoFound = refFonctionServiceImpl.findByIdentifiant(refFonctionDto
						.getIdentifiant());
				if (refFonctionDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ habBundle.getString("fonction_error_identifiant_already_exist") + " ("
							+ refFonctionDto.getIdentifiant() + ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
			}

			if (nameFonctionChange) {
				RefFonctionDto refFonctionDtoFound = refFonctionServiceImpl.findByName(refFonctionDto.getId(),
						refFonctionDto.getNom());
				if (refFonctionDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ habBundle.getString("fonction_error_name_already_exist") + " (" + refFonctionDto.getNom()
							+ ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
			}

			if (nameArabeFonctionChange) {
				RefFonctionDto refFonctionDtoFound = refFonctionServiceImpl.findByNameArabe(refFonctionDto.getId(),
						refFonctionDto.getNomArabe());
				if (refFonctionDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ habBundle.getString("fonction_error_name_already_exist") + " (" + refFonctionDto.getNom()
							+ ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
			}

			if (fonctionChange) {
				if (idfct != null) {

					refFonctionServiceImpl.update(refFonctionDto);
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
						msg.setSummary(bundle.getString("error_echec") + ": "
								+ bundle.getString("error_identify_max_legnt_affected"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return;
					}
					refFonctionDto.setIdentifiant(nextIdentifiy);
					refFonctionServiceImpl.save(refFonctionDto);
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundle.getString("msg_success") + ": "
							+ bundle.getString("msg_success_enregistrement"));
					FacesContext.getCurrentInstance().addMessage(null, msg);

					RefModuleDto refModuleDto = refModuleServiceImpl.findById(refFonctionDto.getIdModule());
					if (refModuleDto != null) {
						refFonctionDto.setIdfModule(refModuleDto.getIdentifiant());
						listFonctionDto = refFonctionServiceImpl.findFonctions(refModuleDto.getId());
					}

					setIdfm(refFonctionDto.getIdfModule());
					setIdfct(refFonctionDto.getIdentifiant());
				}
				setFonctionChange(false);
				setRangFonctionChange(false);

			}

		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": " + bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.error("Une erreur est survenue lors de la sauveguarde de la fonction", e);
		}

	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.trim().equals("")) {
			return null;
		} else {
			try {

				RefFonctionDto _refFonctionDto = refFonctionServiceImpl.findByIdentifiant(value);
				if (_refFonctionDto.getRang() != rang) {
					_refFonctionDto.setRang(rang);
					refFonctionServiceImpl.update(_refFonctionDto);
					if (idfct != null && _refFonctionDto.getIdentifiant().equals(idfct)) {
						_refFonctionDto.setRang(rang);
						setRangFonctionChange(true);
						setRefFonctionDto(_refFonctionDto);
					}
				}
				rang++;
				return _refFonctionDto;
			} catch (ConverterException exception) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"invalid domaine"));
			} catch (RuntimeException exception) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update Error",
						"erreur de mise � jour"));
			}

		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		log.info("getAsString Object:" + value);
		if (value == null || value.equals("")) {
			return "";
		} else {
			return ((RefFonctionDto) value).getIdentifiant();
		}
	}

	/**
	 * [FonctionBckBean.idfm] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 mars 2014 12:28:04
	 * @return the idfm
	 */
	public String getIdfm() {
		return idfm;
	}

	/**
	 * [FonctionBckBean.idfm] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 mars 2014 12:28:05
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
	 * [FonctionBckBean.refModuleServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 mars 2014 12:32:19
	 * @return the refModuleServiceImpl
	 */
	public RefModuleService getRefModuleServiceImpl() {
		return refModuleServiceImpl;
	}

	/**
	 * [FonctionBckBean.refModuleServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 mars 2014 12:32:19
	 * @param refModuleServiceImpl
	 *            the refModuleServiceImpl to set
	 */
	public void setRefModuleServiceImpl(RefModuleService refModuleServiceImpl) {
		this.refModuleServiceImpl = refModuleServiceImpl;
	}

	/**
	 * [FonctionBckBean.listFilesUrl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 mars 2014 15:23:13
	 * @return the listFilesUrl
	 */
	public List<String> getListFilesUrl() {
		return listFilesUrl;
	}

	/**
	 * [FonctionBckBean.listFilesUrl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 mars 2014 15:23:13
	 * @param listFilesUrl
	 *            the listFilesUrl to set
	 */
	public void setListFilesUrl(List<String> listFilesUrl) {
		this.listFilesUrl = listFilesUrl;
	}

	/**
	 * [FonctionBckBean.urlList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 mars 2014 14:03:16
	 * @return the urlList
	 */
	public List<SelectItem> getUrlList() {
		urlList = new ArrayList<SelectItem>();
		List<String> list = loadUrls();
		for (String file : list) {
			SelectItem selectItem = new SelectItem(file, file);
			urlList.add(selectItem);
		}
		return urlList;
	}

	/**
	 * [FonctionBckBean.urlList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 mars 2014 14:03:16
	 * @param urlList
	 *            the urlList to set
	 */
	public void setUrlList(List<SelectItem> urlList) {
		this.urlList = urlList;
	}

	/**
	 * [FonctionBckBean.nameModuleChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 mars 2014 11:04:39
	 * @return the nameModuleChange
	 */
	public boolean isNameModuleChange() {
		return nameModuleChange;
	}

	/**
	 * [FonctionBckBean.nameModuleChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 mars 2014 11:04:39
	 * @param nameModuleChange
	 *            the nameModuleChange to set
	 */
	public void setNameModuleChange(boolean nameModuleChange) {
		this.nameModuleChange = nameModuleChange;
	}

	/**
	 * [FonctionBckBean.listModule] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 mars 2014 11:20:25
	 * @return the listModule
	 */
	public List<SelectItem> getListModule() {
		listModule = new ArrayList<SelectItem>();
		List<RefModuleDto> list = refModuleServiceImpl.findModules();
		for (RefModuleDto refModuleDto : list) {
			SelectItem selectItem = new SelectItem(refModuleDto.getId(), refModuleDto.getNom());
			listModule.add(selectItem);
		}

		return listModule;
	}

	/**
	 * [FonctionBckBean.listModule] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 mars 2014 11:20:25
	 * @param listModule
	 *            the listModule to set
	 */
	public void setListModule(List<SelectItem> listModule) {
		this.listModule = listModule;
	}

	/**
	 * [FonctionBckBean.saveRang] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 mars 2014 12:02:14
	 */
	public void saveRang() {
		log.info("save rang");
		if (rangFonctionChange) {
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundle.getString("msg_success") + ": " + bundle.getString("msg_success_modification"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [FonctionBckBean.rangFonctionChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 mars 2014 12:04:10
	 * @return the rangFonctionChange
	 */
	public boolean isRangFonctionChange() {
		return rangFonctionChange;
	}

	/**
	 * [FonctionBckBean.rangFonctionChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 mars 2014 12:04:10
	 * @param rangFonctionChange
	 *            the rangFonctionChange to set
	 */
	public void setRangFonctionChange(boolean rangFonctionChange) {
		this.rangFonctionChange = rangFonctionChange;
	}

	/**
	 * [FonctionBckBean.listActionsDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 17:47:54
	 * @return the listActionsDto
	 */
	public List<RefFonctionDto> getListActionsDto() {
		return listActionsDto;
	}

	/**
	 * [FonctionBckBean.listActionsDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 17:47:54
	 * @param listActionsDto
	 *            the listActionsDto to set
	 */
	public void setListActionsDto(List<RefFonctionDto> listActionsDto) {
		this.listActionsDto = listActionsDto;
	}

	/**
	 * [FonctionBckBean.generateIdentify] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 avr. 2014 16:51:18
	 * @return
	 */
	public String generateIdentify() {
		try {
			String prefix = refParametreEtabServiceImpl.findValueOfKey(SessionValues.getIdEtablissement(),
					RefUtilConstant.CODIFICATION_FUNCTION_PREFIX_KEY);
			if (prefix == null) {
				return null;
			}
			int prefixLength = prefix.length();
			String order = refParametreEtabServiceImpl.findValueOfKey(SessionValues.getIdEtablissement(),
					RefUtilConstant.CODIFICATION_FUNCTION_ORDER_LENGTH_KEY);
			if (order == null) {
				return null;
			}
			int orderLength = Integer.parseInt(order);
			identifiantLength = prefixLength + orderLength;
			return refFonctionServiceImpl.generateIdentify(prefix, orderLength);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;

	}

	/**
	 * [FonctionBckBean.identifiantLength] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 avr. 2014 10:06:30
	 * @return the identifiantLength
	 */
	public int getIdentifiantLength() {
		return identifiantLength;
	}

	/**
	 * [FonctionBckBean.identifiantLength] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 avr. 2014 10:06:30
	 * @param identifiantLength
	 *            the identifiantLength to set
	 */
	public void setIdentifiantLength(int identifiantLength) {
		this.identifiantLength = identifiantLength;
	}

	/**
	 * [FonctionBckBean.refParametreEtabServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 17:23:04
	 * @return the refParametreEtabServiceImpl
	 */
	public RefParametreEtabService getRefParametreEtabServiceImpl() {
		return refParametreEtabServiceImpl;
	}

	/**
	 * [FonctionBckBean.refParametreEtabServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 17:23:04
	 * @param refParametreEtabServiceImpl
	 *            the refParametreEtabServiceImpl to set
	 */
	public void setRefParametreEtabServiceImpl(RefParametreEtabService refParametreEtabServiceImpl) {
		this.refParametreEtabServiceImpl = refParametreEtabServiceImpl;
	}

	/**
	 * [FonctionBckBean.nameArabeFonctionChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 mai 2014 09:46:50
	 * @return the nameArabeFonctionChange
	 */
	public boolean isNameArabeFonctionChange() {
		return nameArabeFonctionChange;
	}

	/**
	 * [FonctionBckBean.nameArabeFonctionChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 mai 2014 09:46:50
	 * @param nameArabeFonctionChange
	 *            the nameArabeFonctionChange to set
	 */
	public void setNameArabeFonctionChange(boolean nameArabeFonctionChange) {
		this.nameArabeFonctionChange = nameArabeFonctionChange;
	}

}
