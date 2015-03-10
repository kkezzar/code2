/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation.ActionBckBean.java] 
 * @author MAKERRI Sofiane on : 19 mars 2014  09:42:58
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefModuleDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService;
import dz.gov.mesrs.sii.referentiel.business.service.RefModuleService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 19 mars 2014 09:42:58
 */
@ManagedBean(name = "actionBckBean")
@RequestScoped
public class ActionBckBean implements Serializable, Converter {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:44:37
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty("#{param.idfa}")
	private String idfa;
	@ManagedProperty("#{param.idfct}")
	private String idfct;
	@ManagedProperty("#{param.idfm}")
	private String idfm;
	private ResourceBundle bundle;
	private ResourceBundle habBundle;
	private static final Log log = LogFactory.getLog(FonctionBckBean.class);
	@ManagedProperty(value = "#{refFonctionServiceImpl}")
	private RefFonctionService refFonctionServiceImpl;
	@ManagedProperty(value = "#{refModuleServiceImpl}")
	private RefModuleService refModuleServiceImpl;
	private int rang = 1;
	private List<RefFonctionDto> listFonctionDto;
	private List<RefFonctionDto> listActionDto;
	private List<String> listFilesUrl;
	private List<SelectItem> urlList;
	private RefFonctionDto refActionDto;
	private RefFonctionDto refFonctionDto;
	private RefModuleDto refModuleDto;
	private boolean idfActionChange;
	private boolean nameActionChange;
	private boolean nameArabeActionChange;
	private boolean actionChange;
	private boolean nameModuleChange;
	private boolean rangFonctionChange;
	private String path = "";
	private String racine;
	private List<SelectItem> listFonction;
	private boolean urlActionChange;
	private int identifiantLength;
	@ManagedProperty(value = "#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabServiceImpl;
	

	/**
	 * [ActionBckBean.ActionBckBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:42:58
	 */
	public ActionBckBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		habBundle = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.HABILITATION_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		if (idfa != null) {
			refActionDto = refFonctionServiceImpl.findByIdentifiant(idfa);
			if (refActionDto != null) {
				listFonctionDto = refFonctionServiceImpl
						.findFonctions(refActionDto.getIdModule());
				setIdfm(refActionDto.getIdfModule());
			}

		} else if (idfa == null || refActionDto == null) {
			refActionDto = new RefFonctionDto();
			refActionDto.setRang(1);
		}
		if (idfct != null) {
			refFonctionDto = refFonctionServiceImpl.findByIdentifiant(idfct);
			if (refFonctionDto != null) {
				refActionDto.setIdFonctionMere(refFonctionDto.getId());
				refActionDto.setIdModule(refFonctionDto.getIdModule());
				if (idfa == null) {
					int rang = refFonctionServiceImpl
							.findActionLastRang(refFonctionDto.getId());
					refActionDto.setRang(rang);
				}
				listActionDto = refFonctionServiceImpl
						.findActions(refFonctionDto.getId());
			}
		}

		if (idfm != null) {
			RefModuleDto refModuleDto = refModuleServiceImpl
					.findByIdentifiant(idfm);
			if (refModuleDto != null) {
				refActionDto.setIdModule(refModuleDto.getId());

				listFonctionDto = refFonctionServiceImpl
						.findFonctions(refModuleDto.getId());
				loadListFonction();
			}

		} else {
			listFonctionDto = refFonctionServiceImpl.findAll();
			loadListFonction();
		}

	}

	/**
	 * [ActionBckBean.loadListFonction] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 10:59:34
	 */
	public void loadListFonction() {
		listFonction = new ArrayList<SelectItem>();
		for (RefFonctionDto refFonctionDto : listFonctionDto) {
			SelectItem selectItem = new SelectItem(refFonctionDto.getId(),
					refFonctionDto.getNom());
			listFonction.add(selectItem);
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value.trim().equals("")) {
			return null;
		} else {
			try {

				RefFonctionDto _refFonctionDto = refFonctionServiceImpl
						.findByIdentifiant(value);
				if (_refFonctionDto.getRang() != rang) {
					_refFonctionDto.setRang(rang);
					refFonctionServiceImpl.update(_refFonctionDto);
					if (idfa != null
							&& _refFonctionDto.getIdentifiant().equals(idfa)) {
						_refFonctionDto.setRang(rang);
						setRangFonctionChange(true);
						setRefActionDto(_refFonctionDto);
					}
				}
				rang++;
				return _refFonctionDto;
			} catch (ConverterException exception) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"invalid domaine"));
			} catch (RuntimeException exception) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Update Error",
						"erreur de mise � jour"));
			}

		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		log.info("getAsString Object:" + value);
		if (value == null || value.equals("")) {
			return "";
		} else {
			return ((RefFonctionDto) value).getIdentifiant();
		}
	}

	/**
	 * [ActionBckBean.loadUrls] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 10:43:45
	 * @return
	 */
	public List<String> loadUrls() {
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		racine = extContext.getRealPath("pages");
		File dir = new File(racine);
		racine = dir.getName();
		listFilesUrl = new ArrayList<String>();
		loadUrl(dir, listFilesUrl);
		return listFilesUrl;
	}

	/**
	 * [ActionBckBean.loadUrl] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 10:43:49
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
	 * [ActionBckBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [ActionBckBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	/**
	 * [ActionBckBean.idfct] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the idfct
	 */
	public String getIdfct() {
		return idfct;
	}

	/**
	 * [ActionBckBean.idfct] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param idfct
	 *            the idfct to set
	 */
	public void setIdfct(String idfct) {
		if ((idfct != null) && (idfct.equals("null") || idfct.trim().isEmpty())) {
			this.idfct = null;
		} else {
			this.idfct = idfct;
		}
	}

	/**
	 * [ActionBckBean.idfm] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the idfm
	 */
	public String getIdfm() {
		return idfm;
	}

	/**
	 * [ActionBckBean.idfm] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
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
	 * [ActionBckBean.habBundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the habBundle
	 */
	public ResourceBundle getHabBundle() {
		return habBundle;
	}

	/**
	 * [ActionBckBean.habBundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param habBundle
	 *            the habBundle to set
	 */
	public void setHabBundle(ResourceBundle habBundle) {
		this.habBundle = habBundle;
	}

	/**
	 * [ActionBckBean.refFonctionServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the refFonctionServiceImpl
	 */
	public RefFonctionService getRefFonctionServiceImpl() {
		return refFonctionServiceImpl;
	}

	/**
	 * [ActionBckBean.refFonctionServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param refFonctionServiceImpl
	 *            the refFonctionServiceImpl to set
	 */
	public void setRefFonctionServiceImpl(
			RefFonctionService refFonctionServiceImpl) {
		this.refFonctionServiceImpl = refFonctionServiceImpl;
	}

	/**
	 * [ActionBckBean.refModuleServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the refModuleServiceImpl
	 */
	public RefModuleService getRefModuleServiceImpl() {
		return refModuleServiceImpl;
	}

	/**
	 * [ActionBckBean.refModuleServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param refModuleServiceImpl
	 *            the refModuleServiceImpl to set
	 */
	public void setRefModuleServiceImpl(RefModuleService refModuleServiceImpl) {
		this.refModuleServiceImpl = refModuleServiceImpl;
	}

	/**
	 * [ActionBckBean.rang] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the rang
	 */
	public int getRang() {
		return rang;
	}

	/**
	 * [ActionBckBean.rang] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param rang
	 *            the rang to set
	 */
	public void setRang(int rang) {
		this.rang = rang;
	}

	/**
	 * [ActionBckBean.listFonctionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the listFonctionDto
	 */
	public List<RefFonctionDto> getListFonctionDto() {
		return listFonctionDto;
	}

	/**
	 * [ActionBckBean.listFonctionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param listFonctionDto
	 *            the listFonctionDto to set
	 */
	public void setListFonctionDto(List<RefFonctionDto> listFonctionDto) {
		this.listFonctionDto = listFonctionDto;
	}

	/**
	 * [ActionBckBean.listFilesUrl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the listFilesUrl
	 */
	public List<String> getListFilesUrl() {
		return listFilesUrl;
	}

	/**
	 * [ActionBckBean.listFilesUrl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param listFilesUrl
	 *            the listFilesUrl to set
	 */
	public void setListFilesUrl(List<String> listFilesUrl) {
		this.listFilesUrl = listFilesUrl;
	}

	/**
	 * [ActionBckBean.urlList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
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
	 * [ActionBckBean.urlList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param urlList
	 *            the urlList to set
	 */
	public void setUrlList(List<SelectItem> urlList) {
		this.urlList = urlList;
	}

	/**
	 * [ActionBckBean.refActionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 15:59:40
	 * @return the refActionDto
	 */
	public RefFonctionDto getRefActionDto() {
		return refActionDto;
	}

	/**
	 * [ActionBckBean.refActionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 15:59:40
	 * @param refActionDto
	 *            the refActionDto to set
	 */
	public void setRefActionDto(RefFonctionDto refActionDto) {
		this.refActionDto = refActionDto;
	}

	/**
	 * [ActionBckBean.refModuleDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the refModuleDto
	 */
	public RefModuleDto getRefModuleDto() {
		return refModuleDto;
	}

	/**
	 * [ActionBckBean.refModuleDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param refModuleDto
	 *            the refModuleDto to set
	 */
	public void setRefModuleDto(RefModuleDto refModuleDto) {
		this.refModuleDto = refModuleDto;
	}

	/**
	 * [ActionBckBean.idfActionChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the idfActionChange
	 */
	public boolean isIdfActionChange() {
		return idfActionChange;
	}

	/**
	 * [ActionBckBean.idfActionChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param idfActionChange
	 *            the idfActionChange to set
	 */
	public void setIdfActionChange(boolean idfActionChange) {
		this.idfActionChange = idfActionChange;
	}

	/**
	 * [ActionBckBean.nameActionChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the nameActionChange
	 */
	public boolean isNameActionChange() {
		return nameActionChange;
	}

	/**
	 * [ActionBckBean.nameActionChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param nameActionChange
	 *            the nameActionChange to set
	 */
	public void setNameActionChange(boolean nameActionChange) {
		this.nameActionChange = nameActionChange;
	}

	/**
	 * [ActionBckBean.actionChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the actionChange
	 */
	public boolean isActionChange() {
		return actionChange;
	}

	/**
	 * [ActionBckBean.actionChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param actionChange
	 *            the actionChange to set
	 */
	public void setActionChange(boolean actionChange) {
		this.actionChange = actionChange;
	}

	/**
	 * [ActionBckBean.nameModuleChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the nameModuleChange
	 */
	public boolean isNameModuleChange() {
		return nameModuleChange;
	}

	/**
	 * [ActionBckBean.nameModuleChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param nameModuleChange
	 *            the nameModuleChange to set
	 */
	public void setNameModuleChange(boolean nameModuleChange) {
		this.nameModuleChange = nameModuleChange;
	}

	/**
	 * [ActionBckBean.rangFonctionChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the rangFonctionChange
	 */
	public boolean isRangFonctionChange() {
		return rangFonctionChange;
	}

	/**
	 * [ActionBckBean.rangFonctionChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param rangFonctionChange
	 *            the rangFonctionChange to set
	 */
	public void setRangFonctionChange(boolean rangFonctionChange) {
		this.rangFonctionChange = rangFonctionChange;
	}

	/**
	 * [ActionBckBean.path] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * [ActionBckBean.path] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * [ActionBckBean.racine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @return the racine
	 */
	public String getRacine() {
		return racine;
	}

	/**
	 * [ActionBckBean.racine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:47:14
	 * @param racine
	 *            the racine to set
	 */
	public void setRacine(String racine) {
		this.racine = racine;
	}

	/**
	 * [ActionBckBean.listFonction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 10:57:48
	 * @return the listFonction
	 */
	public List<SelectItem> getListFonction() {
		return listFonction;
	}

	/**
	 * [ActionBckBean.listFonction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 10:57:48
	 * @param listFonction
	 *            the listFonction to set
	 */
	public void setListFonction(List<SelectItem> listFonction) {
		this.listFonction = listFonction;
	}

	/**
	 * [ActionBckBean.saveAction] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 10:49:48
	 */
	public void saveAction() {

		FacesMessage msg = new FacesMessage();

		try {

			if (!actionChange && idfa != null) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_update_none"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}

			if (idfActionChange) {
				RefFonctionDto refFonctionDtoFound = refFonctionServiceImpl
						.findByIdentifiant(refActionDto.getIdentifiant());
				if (refFonctionDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec")
							+ ": "
							+ habBundle
									.getString("action_error_identifiant_already_exist")
							+ " (" + refActionDto.getIdentifiant() + ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
			}

			/*
			 * if (nameActionChange) { RefFonctionDto refFonctionDtoFound =
			 * refFonctionServiceImpl .findByName(refActionDto.getNom()); if
			 * (refFonctionDtoFound != null) {
			 * msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			 * msg.setSummary(bundle.getString("error_echec") + ": " + habBundle
			 * .getString("fonction_error_name_already_exist") + " (" +
			 * refActionDto.getNom() + ")");
			 * FacesContext.getCurrentInstance().addMessage(null, msg); return;
			 * } }
			 */
			if (actionChange) {
				if (idfa != null) {

					refFonctionServiceImpl.update(refActionDto);
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundle.getString("msg_success") + ": "
							+ bundle.getString("msg_success_modification"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					if (refActionDto.getIdModule() == null) {
						RefFonctionDto _refFonctionDto = refFonctionServiceImpl
								.findById(refActionDto.getIdFonctionMere());
						if (_refFonctionDto != null) {
							refActionDto.setIdModule(_refFonctionDto
									.getIdModule());
						}
					}
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
					refActionDto.setIdentifiant(nextIdentifiy);
					refFonctionServiceImpl.save(refActionDto);
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundle.getString("msg_success") + ": "
							+ bundle.getString("msg_success_enregistrement"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					listActionDto = refFonctionServiceImpl
							.findActions(refActionDto.getIdFonctionMere());
					setIdfm(refActionDto.getIdfModule());
					setIdfa(refActionDto.getIdentifiant());
				}
				setActionChange(false);
				setRangFonctionChange(false);

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
	 * [ActionBckBean.nameFonctionChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:54:30
	 * @param event
	 */
	/*
	 * public void nameFonctionChanged(ValueChangeEvent event) { if
	 * (event.getOldValue() == null ||
	 * !event.getOldValue().equals(event.getNewValue())) { nameFonctionChange =
	 * true; fonctionChange = true; } }
	 */

	/**
	 * [ActionBckBean.idfActionChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 09:55:21
	 * @param event
	 */
	public void idfActionChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			idfActionChange = true;
			actionChange = true;
		}
	}

	/**
	 * [ActionBckBean.nameActionChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 10:10:06
	 * @param event
	 */
	public void nameActionChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			nameActionChange = true;
			actionChange = true;
		}
	}
	
	/**
	 * [ActionBckBean.nameArabeActionChanged] Method 
	 * @author MAKERRI Sofiane  on : 4 mai 2014  10:03:54
	 * @param event
	 */
	public void nameArabeActionChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			nameArabeActionChange = true;
			actionChange = true;
		}
	}

	/**
	 * [ActionBckBean.saveRang] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 10:10:12
	 */
	public void saveRang() {
		log.info("save rang");
		if (rangFonctionChange) {
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundle.getString("msg_success") + ": "
					+ bundle.getString("msg_success_modification"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [ActionBckBean.back] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 10:10:39
	 * @return
	 */
	public String back() {
		return "toSearch";
	}

	/**
	 * [ActionBckBean.idfa] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 15:06:52
	 * @return the idfa
	 */
	public String getIdfa() {
		return idfa;
	}

	/**
	 * [ActionBckBean.idfa] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 15:06:52
	 * @param idfa
	 *            the idfa to set
	 */
	public void setIdfa(String idfa) {
		if ((idfa != null) && (idfa.equals("null") || idfa.trim().isEmpty())) {
			this.idfa = null;
		} else {
			this.idfa = idfa;
		}
	}

	/**
	 * [ActionBckBean.refFonctionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 16:00:42
	 * @return the refFonctionDto
	 */
	public RefFonctionDto getRefFonctionDto() {
		return refFonctionDto;
	}

	/**
	 * [ActionBckBean.refFonctionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 16:00:42
	 * @param refFonctionDto
	 *            the refFonctionDto to set
	 */
	public void setRefFonctionDto(RefFonctionDto refFonctionDto) {
		this.refFonctionDto = refFonctionDto;
	}

	/**
	 * [ActionBckBean.listActionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 16:20:50
	 * @return the listActionDto
	 */
	public List<RefFonctionDto> getListActionDto() {
		return listActionDto;
	}

	/**
	 * [ActionBckBean.listActionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 mars 2014 16:20:50
	 * @param listActionDto
	 *            the listActionDto to set
	 */
	public void setListActionDto(List<RefFonctionDto> listActionDto) {
		this.listActionDto = listActionDto;
	}

	/**
	 * [ActionBckBean.urlFonctionChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 10:33:57
	 * @param event
	 */
	public void urlActionChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			urlActionChange = true;
			actionChange = true;
		}
	}
	
	
	/**
	 * [ActionBckBean.periodeChanged] Method 
	 * @author MAKERRI Sofiane  on : 3 avr. 2014  14:46:09
	 * @param event
	 */
	public void periodeChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			actionChange = true;
		}
	}

	/**
	 * [ActionBckBean.urlActionChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 10:34:48
	 * @return the urlActionChange
	 */
	public boolean isUrlActionChange() {
		return urlActionChange;
	}

	/**
	 * [ActionBckBean.urlActionChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 10:34:48
	 * @param urlActionChange
	 *            the urlActionChange to set
	 */
	public void setUrlActionChange(boolean urlActionChange) {
		this.urlActionChange = urlActionChange;
	}
	
	
	/**
	 * [ActionBckBean.generateIdentify] Method 
	 * @author MAKERRI Sofiane  on : 21 avr. 2014  17:47:41
	 * @return
	 */
	public String generateIdentify() {
		try {
			String prefix = refParametreEtabServiceImpl
					.findValueOfKey(SessionValues.getIdEtablissement(), RefUtilConstant.CODIFICATION_ACTION_PREFIX_KEY);
			if (prefix == null) {
				return null;
			}
			int prefixLength =  prefix.length();
			String order = refParametreEtabServiceImpl
					.findValueOfKey(SessionValues.getIdEtablissement(), RefUtilConstant.CODIFICATION_ACTION_ORDER_LENGTH_KEY);
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
	 * [ActionBckBean.identifiantLength] Getter 
	 * @author MAKERRI Sofiane on : 23 avr. 2014  10:10:04
	 * @return the identifiantLength
	 */
	public int getIdentifiantLength() {
		return identifiantLength;
	}

	/**
	 * [ActionBckBean.identifiantLength] Setter 
	 * @author MAKERRI Sofiane on : 23 avr. 2014  10:10:04
	 * @param identifiantLength the identifiantLength to set
	 */
	public void setIdentifiantLength(int identifiantLength) {
		this.identifiantLength = identifiantLength;
	}

	/**
	 * [ActionBckBean.refParametreEtabServiceImpl] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  17:41:22
	 * @return the refParametreEtabServiceImpl
	 */
	public RefParametreEtabService getRefParametreEtabServiceImpl() {
		return refParametreEtabServiceImpl;
	}

	/**
	 * [ActionBckBean.refParametreEtabServiceImpl] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  17:41:22
	 * @param refParametreEtabServiceImpl the refParametreEtabServiceImpl to set
	 */
	public void setRefParametreEtabServiceImpl(
			RefParametreEtabService refParametreEtabServiceImpl) {
		this.refParametreEtabServiceImpl = refParametreEtabServiceImpl;
	}

	/**
	 * [ActionBckBean.nameArabeActionChange] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  10:04:18
	 * @return the nameArabeActionChange
	 */
	public boolean isNameArabeActionChange() {
		return nameArabeActionChange;
	}

	/**
	 * [ActionBckBean.nameArabeActionChange] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  10:04:18
	 * @param nameArabeActionChange the nameArabeActionChange to set
	 */
	public void setNameArabeActionChange(boolean nameArabeActionChange) {
		this.nameArabeActionChange = nameArabeActionChange;
	}

	

	
	

}
