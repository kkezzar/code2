/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation.domaine.DomaineSearchBckBean.java] 
 * @author MAKERRI Sofiane on : 18 f�vr. 2014  09:56:10
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

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 18 f�vr. 2014 09:56:10
 */
@ManagedBean(name = "domaineBckBean")
@RequestScoped
public class DomaineBckBean implements Serializable, Converter {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 09:56:25
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(DomaineBckBean.class.getName());
	@ManagedProperty(value = "#{refDomaineServiceImpl}")
	private RefDomaineService refDomaineServiceImpl;
	private RefDomaineDto refDomaineDto;
	private RefDomaineDto refSubDomaineDto;
	private RefDomaineDto selectedSubDomaine;
	private ResourceBundle bundle;
	private ResourceBundle habBundle;
	private boolean domaineChange;
	private boolean subDomaineChange;
	private List<RefDomaineDto> listSubDomaineDto;
	private int rang = 1;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty("#{param.idfd}")
	private String idfd;
	@ManagedProperty("#{param.idfDomaine}")
	private String idfDomaine;
	@ManagedProperty("#{param.subDomaine}")
	private String subDomaine;
	private Boolean showSubDomaine;
	@ManagedProperty("#{param.idfSubDomaine}")
	private String idfSubDomaine;
	private boolean idfDomaineChange;
	private boolean nameDomaineChange;
	private boolean nameArabeDomaineChange;
	private boolean idfSubDomaineChange;
	private boolean nameSubDomaineChange;
	private boolean nameArabeSubDomaineChange;
	private boolean orderingChange;
	private boolean rangDomaineChange;
	private List<RefDomaineDto> listDomaineDto;
	@ManagedProperty("#{param.newDomaine}")
	private String newDomaine;
	private Boolean showNewDomaine;
	private int identifiantLength;
	@ManagedProperty(value = "#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabServiceImpl;

	/**
	 * [DomaineSearchBckBean.DomaineSearchBckBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 09:56:10
	 */
	public DomaineBckBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		habBundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.HABILITATION_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		if (idfd != null) {
			refDomaineDto = refDomaineServiceImpl.findByIdentifiant(idfd);
			if (refDomaineDto != null) {
				loadSubDomaines();
			} else {
				refDomaineDto = new RefDomaineDto();
				refDomaineDto.setRang(1);
			}
		} else {
			refDomaineDto = new RefDomaineDto();
			int rang = refDomaineServiceImpl.findDomaineLastRang();
			refDomaineDto.setRang(rang);
		}
		if (idfSubDomaine == null || idfSubDomaine.trim().isEmpty()) {
			refSubDomaineDto = new RefDomaineDto();
			refSubDomaineDto.setRang(1);

		} else {
			refSubDomaineDto = refDomaineServiceImpl.findByIdentifiant(idfSubDomaine);
		}

		loadDomaines();
	}

	/**
	 * [DomaineBckBean.loadSubDomaines] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 17:59:00
	 */
	public void loadSubDomaines() {
		if (refDomaineDto != null && refDomaineDto.getId() != null) {
			listSubDomaineDto = refDomaineServiceImpl.findSubDomaine(refDomaineDto.getId());
		} else {
			listSubDomaineDto = new ArrayList<RefDomaineDto>();
		}
	}

	/**
	 * [DomaineBckBean.loadDomaines] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 f�vr. 2014 10:54:13
	 */
	public void loadDomaines() {
		listDomaineDto = refDomaineServiceImpl.findDomaines();
		System.out.println(listDomaineDto);
	}

	/**
	 * [DomaineSearchBckBean.refDomaineServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 09:59:50
	 * @return the refDomaineServiceImpl
	 */
	public RefDomaineService getRefDomaineServiceImpl() {
		return refDomaineServiceImpl;
	}

	/**
	 * [DomaineSearchBckBean.refDomaineServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 09:59:50
	 * @param refDomaineServiceImpl
	 *            the refDomaineServiceImpl to set
	 */
	public void setRefDomaineServiceImpl(RefDomaineService refDomaineServiceImpl) {
		this.refDomaineServiceImpl = refDomaineServiceImpl;
	}

	/**
	 * [DomaineSearchBckBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 10:19:31
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [DomaineSearchBckBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 10:19:31
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
	 * [DomaineBckBean.refDomaineDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 11:33:54
	 * @return the refDomaineDto
	 */
	public RefDomaineDto getRefDomaineDto() {
		return refDomaineDto;
	}

	/**
	 * [DomaineBckBean.refDomaineDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 11:33:54
	 * @param refDomaineDto
	 *            the refDomaineDto to set
	 */
	public void setRefDomaineDto(RefDomaineDto refDomaineDto) {
		this.refDomaineDto = refDomaineDto;
	}

	/**
	 * [DomaineBckBean.idfd] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 11:35:16
	 * @return the idfd
	 */
	public String getIdfd() {
		return idfd;
	}

	/**
	 * [DomaineBckBean.idfd] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 11:35:16
	 * @param idfd
	 *            the idfd to set
	 */
	public void setIdfd(String idfd) {
		if ((idfd != null) && (idfd.equals("null"))) {
			this.idfd = null;
		} else
			this.idfd = idfd;
	}

	/**
	 * [DomaineBckBean.saveDomaine] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 11:56:03
	 */
	public void saveDomaine() {

		FacesMessage msg = new FacesMessage();

		try {

			if (!domaineChange && idfd != null) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": " + bundle.getString("warn_info_update_none"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				loadDomaines();
				loadSubDomaines();
				return;
			}

			if (idfDomaineChange) {
				RefDomaineDto refDomaineDtoFound = refDomaineServiceImpl.findByIdentifiant(refDomaineDto
						.getIdentifiant());
				if (refDomaineDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ habBundle.getString("domaine_error_identifiant_already_exist") + " ("
							+ refDomaineDto.getIdentifiant() + ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					loadDomaines();
					loadSubDomaines();
					return;
				}
			}

			if (nameDomaineChange) {
				RefDomaineDto refDomaineDtoFound = refDomaineServiceImpl.findByName(refDomaineDto.getNom());
				if (refDomaineDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ habBundle.getString("domaine_error_name_already_exist") + " (" + refDomaineDto.getNom()
							+ ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					loadDomaines();
					loadSubDomaines();
					return;
				}
			}

			if (nameArabeDomaineChange) {
				RefDomaineDto refDomaineDtoFound = refDomaineServiceImpl.findByNameArabe(refDomaineDto.getNomArabe());
				if (refDomaineDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ habBundle.getString("domaine_error_name_already_exist") + " (" + refDomaineDto.getNom()
							+ ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					loadDomaines();
					loadSubDomaines();
					return;
				}
			}

			if (domaineChange) {
				if (idfd != null) {
					refDomaineServiceImpl.update(refDomaineDto);
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundle.getString("msg_success") + ": "
							+ bundle.getString("msg_success_modification"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					setDomaineChange(false);
				} else {
					// refDomaineDto.setRang(1);
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
					refDomaineDto.setIdentifiant(nextIdentifiy);
					refDomaineServiceImpl.save(refDomaineDto);

					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundle.getString("msg_success") + ": "
							+ bundle.getString("msg_success_enregistrement"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					setDomaineChange(false);
					setIdfd(refDomaineDto.getIdentifiant());
					loadDomaines();
					loadSubDomaines();
				}
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": " + bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.error("Une erreur est survenue lors de la sauveguarde du domaine", e);
		}

	}

	public void onAnotherValueChange(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			domaineChange = true;
		}
	}

	/**
	 * [DomaineBckBean.toNew] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 11:56:16
	 * @return
	 */
	public String toNew() {
		return "";
	}

	public String back() {
		return "toSearchDomaine";
	}

	/**
	 * [DomaineBckBean.listSubDomaineDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 15:24:47
	 * @return the listSubDomaineDto
	 */
	public List<RefDomaineDto> getListSubDomaineDto() {
		return listSubDomaineDto;
	}

	/**
	 * [DomaineBckBean.listSubDomaineDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 15:24:47
	 * @param listSubDomaineDto
	 *            the listSubDomaineDto to set
	 */
	public void setListSubDomaineDto(List<RefDomaineDto> listSubDomaineDto) {
		this.listSubDomaineDto = listSubDomaineDto;
	}

	/**
	 * [DomaineBckBean.addSubDomaine] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 16:13:01
	 */
	public void addSubDomaine() {
		// setShowSousDomaine(true);
		refSubDomaineDto = new RefDomaineDto();
		setIdfSubDomaine(null);
		/*
		 * if (refDomaineDto != null && refDomaineDto.getId() != null) { int
		 * rang = refDomaineServiceImpl
		 * .findSubDomaineLastRang(refDomaineDto.getId());
		 * refSubDomaineDto.setRang(rang); }
		 */

	}

	/**
	 * [DomaineBckBean.refSubDomaineDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 16:35:26
	 * @return the refSubDomaineDto
	 */
	public RefDomaineDto getRefSubDomaineDto() {
		return refSubDomaineDto;
	}

	/**
	 * [DomaineBckBean.refSubDomaineDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 16:35:26
	 * @param refSubDomaineDto
	 *            the refSubDomaineDto to set
	 */
	public void setRefSubDomaineDto(RefDomaineDto refSubDomaineDto) {
		this.refSubDomaineDto = refSubDomaineDto;
	}

	/**
	 * [DomaineBckBean.saveSubDomaine] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 14:05:29
	 */
	public void saveSubDomaine() {

		FacesMessage msg = new FacesMessage();

		try {

			if (idfSubDomaineChange) {
				RefDomaineDto refDomaineDtoFound = refDomaineServiceImpl.findByIdentifiant(refSubDomaineDto
						.getIdentifiant());
				if (refDomaineDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ habBundle.getString("domaine_error_identifiant_already_exist") + " ("
							+ refSubDomaineDto.getIdentifiant() + ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
			}

			if (nameSubDomaineChange) {
				RefDomaineDto refDomaineDtoFound = refDomaineServiceImpl.findByName(refSubDomaineDto.getNom());
				if (refDomaineDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ habBundle.getString("domaine_error_name_already_exist") + " ("
							+ refSubDomaineDto.getNom() + ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
			}

			if (nameArabeSubDomaineChange) {
				RefDomaineDto refDomaineDtoFound = refDomaineServiceImpl
						.findByNameArabe(refSubDomaineDto.getNomArabe());
				if (refDomaineDtoFound != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ habBundle.getString("domaine_error_name_already_exist") + " ("
							+ refSubDomaineDto.getNom() + ")");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
			}

			if (idfd != null && subDomaineChange) {
				if (refSubDomaineDto.getId() == null) {
					refSubDomaineDto.setIdfDomaineParent(idfd);
					refSubDomaineDto.setIdDomaineParent(refDomaineDto.getId());
					int rang = refDomaineServiceImpl.findSubDomaineLastRang(refDomaineDto.getId());
					refSubDomaineDto.setRang(rang);
					String nextIdentifiy = generateSubIdentify();
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
					refSubDomaineDto.setIdentifiant(nextIdentifiy);
					refDomaineServiceImpl.save(refSubDomaineDto);

					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundle.getString("msg_success") + ": "
							+ bundle.getString("msg_success_enregistrement"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					refDomaineServiceImpl.update(refSubDomaineDto);
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundle.getString("msg_success") + ": "
							+ bundle.getString("msg_success_modification"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
				loadSubDomaines();
				refSubDomaineDto = new RefDomaineDto();
				// setShowSubDomaine(false);
				setSubDomaineChange(false);
				setOrderingChange(false);
			} else if (orderingChange) {
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": " + bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": " + bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}

	}

	/**
	 * [DomaineBckBean.rang] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 f�vr. 2014 10:15:19
	 * @return the rang
	 */
	public int getRang() {
		return rang;
	}

	/**
	 * [DomaineBckBean.rang] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 f�vr. 2014 10:15:19
	 * @param rang
	 *            the rang to set
	 */
	public void setRang(int rang) {
		this.rang = rang;
	}

	/**
	 * [DomaineBckBean.subDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 09:10:57
	 * @return the subDomaine
	 */
	public String getSubDomaine() {
		return subDomaine;
	}

	/**
	 * [DomaineBckBean.subDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 09:10:57
	 * @param subDomaine
	 *            the subDomaine to set
	 */
	public void setSubDomaine(String subDomaine) {
		setShowSubDomaine(Boolean.parseBoolean(subDomaine));
		this.subDomaine = subDomaine;
	}

	/**
	 * [DomaineBckBean.showSubDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 09:10:57
	 * @return the showSubDomaine
	 */
	public Boolean getShowSubDomaine() {
		return showSubDomaine;
	}

	/**
	 * [DomaineBckBean.showSubDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 09:10:57
	 * @param showSubDomaine
	 *            the showSubDomaine to set
	 */
	public void setShowSubDomaine(Boolean showSubDomaine) {
		this.showSubDomaine = showSubDomaine;
	}

	/**
	 * [DomaineBckBean.deleteSubDomaine] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 11:35:35
	 */
	public void deleteSubDomaine() {
		if (refSubDomaineDto != null && refSubDomaineDto.getId() != null) {
			FacesMessage msg = new FacesMessage();
			try {
				refDomaineServiceImpl.remove(refSubDomaineDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": " + bundle.getString("msg_success_suppression"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setSubDomaine("false");
				reorderSubDomaine();
				loadDomaines();
				loadSubDomaines();
			} catch (Exception e) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": " + bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				log.info(e.getMessage());
			}
			refSubDomaineDto = new RefDomaineDto();
		}
	}

	/**
	 * [DomaineBckBean.bundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 10:45:43
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [DomaineBckBean.bundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 10:45:43
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [DomaineBckBean.habBundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 10:45:43
	 * @return the habBundle
	 */
	public ResourceBundle getHabBundle() {
		return habBundle;
	}

	/**
	 * [DomaineBckBean.habBundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 10:45:43
	 * @param habBundle
	 *            the habBundle to set
	 */
	public void setHabBundle(ResourceBundle habBundle) {
		this.habBundle = habBundle;
	}

	/**
	 * [DomaineBckBean.idfDomaineChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 10:46:57
	 * @return the idfDomaineChange
	 */
	public boolean isIdfDomaineChange() {
		return idfDomaineChange;
	}

	/**
	 * [DomaineBckBean.idfDomaineChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 10:46:57
	 * @param idfDomaineChange
	 *            the idfDomaineChange to set
	 */
	public void setIdfDomaineChange(boolean idfDomaineChange) {
		this.idfDomaineChange = idfDomaineChange;
	}

	/**
	 * [DomaineBckBean.nameDomaineChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 10:46:57
	 * @return the nomDomaineChange
	 */
	public boolean isNameDomaineChange() {
		return nameDomaineChange;
	}

	/**
	 * [DomaineBckBean.nameDomaineChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 10:46:57
	 * @param nomDomaineChange
	 *            the nomDomaineChange to set
	 */
	public void setNameDomaineChange(boolean nameDomaineChange) {
		this.nameDomaineChange = nameDomaineChange;
	}

	/**
	 * [DomaineBckBean.idfSubDomaineChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 10:46:57
	 * @return the idfSubDomaineChange
	 */
	public boolean isIdfSubDomaineChange() {
		return idfSubDomaineChange;
	}

	/**
	 * [DomaineBckBean.idfSubDomaineChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 10:46:57
	 * @param idfSubDomaineChange
	 *            the idfSubDomaineChange to set
	 */
	public void setIdfSubDomaineChange(boolean idfSubDomaineChange) {
		this.idfSubDomaineChange = idfSubDomaineChange;
	}

	/**
	 * [DomaineBckBean.nameSubDomaineChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 10:46:57
	 * @return the nomSubDomaineChange
	 */
	public boolean isNameSubDomaineChange() {
		return nameSubDomaineChange;
	}

	/**
	 * [DomaineBckBean.nameSubDomaineChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 10:46:57
	 * @param nomSubDomaineChange
	 *            the nomSubDomaineChange to set
	 */
	public void setNameSubDomaineChange(boolean nameSubDomaineChange) {
		this.nameSubDomaineChange = nameSubDomaineChange;
	}

	/**
	 * [DomaineBckBean.idfDomaineChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 10:48:29
	 * @param event
	 */
	public void idfDomaineChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			idfDomaineChange = true;
			domaineChange = true;
		}
	}

	/**
	 * [DomaineBckBean._nameDomaineChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014 12:10:07
	 * @param event
	 */
	public void _nameDomaineChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			nameDomaineChange = true;
			domaineChange = true;
		}
	}

	/**
	 * [DomaineBckBean._nameArabeDomaineChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 mai 2014 09:11:04
	 * @param event
	 */
	public void _nameArabeDomaineChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			nameArabeDomaineChange = true;
			domaineChange = true;
		}
	}

	/**
	 * [DomaineBckBean.idfSubDomaineChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 10:48:36
	 * @param event
	 */
	public void idfSubDomaineChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			idfSubDomaineChange = true;
			subDomaineChange = true;
		}
	}

	/**
	 * [DomaineBckBean.nameSubDomaineChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 10:48:39
	 * @param event
	 */
	public void nameSubDomaineChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			nameSubDomaineChange = true;
			subDomaineChange = true;
		}
	}

	/**
	 * [DomaineBckBean.nameArabeSubDomaineChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 mai 2014 09:17:35
	 * @param event
	 */
	public void nameArabeSubDomaineChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			nameArabeSubDomaineChange = true;
			subDomaineChange = true;
		}
	}

	/**
	 * [DomaineBckBean.domaineChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 12:16:46
	 * @return the domaineChange
	 */
	public boolean isDomaineChange() {
		return domaineChange;
	}

	/**
	 * [DomaineBckBean.domaineChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 12:16:46
	 * @param domaineChange
	 *            the domaineChange to set
	 */
	public void setDomaineChange(boolean domaineChange) {
		this.domaineChange = domaineChange;
	}

	/**
	 * [DomaineBckBean.subDomaineChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 12:16:46
	 * @return the subDomaineChange
	 */
	public boolean isSubDomaineChange() {
		return subDomaineChange;
	}

	/**
	 * [DomaineBckBean.subDomaineChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 12:16:46
	 * @param subDomaineChange
	 *            the subDomaineChange to set
	 */
	public void setSubDomaineChange(boolean subDomaineChange) {
		this.subDomaineChange = subDomaineChange;
	}

	public void orderListChange(ValueChangeEvent event) {

	}

	/**
	 * [DomaineBckBean.orderingChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 12:52:31
	 * @return the orderingChange
	 */
	public boolean isOrderingChange() {
		return orderingChange;
	}

	/**
	 * [DomaineBckBean.orderingChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 f�vr. 2014 12:52:31
	 * @param orderingChange
	 *            the orderingChange to set
	 */
	public void setOrderingChange(boolean orderingChange) {
		this.orderingChange = orderingChange;
	}

	/**
	 * [DomaineBckBean.toRangEdit] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 f�vr. 2014 15:07:31
	 * @return
	 */
	public String toRangEdit() {
		return "toRangEdit";
	}

	/**
	 * [DomaineBckBean.selectedSubDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 f�vr. 2014 15:43:12
	 * @return the selectedSubDomaine
	 */
	public RefDomaineDto getSelectedSubDomaine() {
		return selectedSubDomaine;
	}

	/**
	 * [DomaineBckBean.selectedSubDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 f�vr. 2014 15:43:12
	 * @param selectedSubDomaine
	 *            the selectedSubDomaine to set
	 */
	public void setSelectedSubDomaine(RefDomaineDto selectedSubDomaine) {
		this.selectedSubDomaine = selectedSubDomaine;
	}

	public void onSubDomaineSelect(SelectEvent event) {
		refSubDomaineDto = (RefDomaineDto) (event.getObject());
		if (refSubDomaineDto != null) {
			setIdfSubDomaine(refSubDomaineDto.getIdentifiant());
		}
		// setShowSubDomaine(true);
		// setSubDomaineChange(true);
	}

	/**
	 * [DomaineBckBean.idfSubDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 10:18:57
	 * @return the idfSubDomaine
	 */
	public String getIdfSubDomaine() {
		return idfSubDomaine;
	}

	/**
	 * [DomaineBckBean.idfSubDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 10:18:57
	 * @param idfSubDomaine
	 *            the idfSubDomaine to set
	 */
	public void setIdfSubDomaine(String idfSubDomaine) {
		if ((idfSubDomaine != null) && (idfSubDomaine.equals("null"))) {
			this.idfSubDomaine = "";
		} else {
			this.idfSubDomaine = idfSubDomaine;
		}
	}

	public void reorderSubDomaine() {
		if (idfd != null) {
			refDomaineServiceImpl.updateRangSubDomaine(idfd);
		}
	}

	/**
	 * [DomaineBckBean.idfDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 11:54:03
	 * @return the idfDomaine
	 */
	public String getIdfDomaine() {
		return idfDomaine;
	}

	/**
	 * [DomaineBckBean.idfDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 f�vr. 2014 11:54:03
	 * @param idfDomaine
	 *            the idfDomaine to set
	 */
	public void setIdfDomaine(String idfDomaine) {
		if (idfDomaine != null) {
			setIdfd(idfDomaine);
		}
		this.idfDomaine = idfDomaine;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.trim().equals("")) {
			return null;
		} else {
			try {

				RefDomaineDto _refDomaineDto = refDomaineServiceImpl.findByIdentifiant(value);
				if (_refDomaineDto == null) {
					return null;
				}
				if (showSubDomaine && _refDomaineDto.getIdDomaineParent() == null) {
					return null;
				}
				if (!showSubDomaine && _refDomaineDto.getIdDomaineParent() != null) {
					return null;
				}
				if (_refDomaineDto.getRang() != rang) {
					_refDomaineDto.setRang(rang);
					refDomaineServiceImpl.update(_refDomaineDto);
					if (idfd != null && _refDomaineDto.getIdentifiant().equals(idfd)) {
						_refDomaineDto.setRang(rang);
						setRangDomaineChange(true);
						setRefDomaineDto(_refDomaineDto);
					}

				}
				rang++;
				return _refDomaineDto;
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
			return ((RefDomaineDto) value).getIdentifiant();
		}
	}

	/**
	 * [DomaineBckBean.rangDomaineChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 f�vr. 2014 10:50:22
	 * @return the rangDomaineChange
	 */
	public boolean isRangDomaineChange() {
		return rangDomaineChange;
	}

	/**
	 * [DomaineBckBean.rangDomaineChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 f�vr. 2014 10:50:22
	 * @param rangDomaineChange
	 *            the rangDomaineChange to set
	 */
	public void setRangDomaineChange(boolean rangDomaineChange) {
		this.rangDomaineChange = rangDomaineChange;
	}

	/**
	 * [DomaineBckBean.listDomaineDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 f�vr. 2014 10:52:40
	 * @return the listDomaineDto
	 */
	public List<RefDomaineDto> getListDomaineDto() {
		return listDomaineDto;
	}

	/**
	 * [DomaineBckBean.listDomaineDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 f�vr. 2014 10:52:40
	 * @param listDomaineDto
	 *            the listDomaineDto to set
	 */
	public void setListDomaineDto(List<RefDomaineDto> listDomaineDto) {
		this.listDomaineDto = listDomaineDto;
	}

	public void saveDomaineRang() {
		log.info("save rang");
		if (rangDomaineChange) {
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundle.getString("msg_success") + ": " + bundle.getString("msg_success_modification"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [DomaineBckBean.newDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 f�vr. 2014 13:44:13
	 * @return the newDomaine
	 */
	public String getNewDomaine() {
		return newDomaine;
	}

	/**
	 * [DomaineBckBean.newDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 f�vr. 2014 13:44:13
	 * @param newDomaine
	 *            the newDomaine to set
	 */
	public void setNewDomaine(String newDomaine) {
		setShowNewDomaine(Boolean.parseBoolean(newDomaine));
		this.newDomaine = newDomaine;
	}

	/**
	 * [DomaineBckBean.showNewDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 f�vr. 2014 13:44:13
	 * @return the showNewDomaine
	 */
	public Boolean getShowNewDomaine() {
		return showNewDomaine;
	}

	/**
	 * [DomaineBckBean.showNewDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 f�vr. 2014 13:44:13
	 * @param showNewDomaine
	 *            the showNewDomaine to set
	 */
	public void setShowNewDomaine(Boolean showNewDomaine) {
		this.showNewDomaine = showNewDomaine;
	}

	/**
	 * [DomaineBckBean.generateIdentify] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 avr. 2014 09:57:54
	 * @return
	 */
	public String generateIdentify() {
		try {
			String prefix = refParametreEtabServiceImpl.findValueOfKey(SessionValues.getIdEtablissement(),
					RefUtilConstant.CODIFICATION_DOMAINE_PREFIX_KEY);
			if (prefix == null) {
				return null;
			}
			int prefixLength = prefix.length();
			String order = refParametreEtabServiceImpl.findValueOfKey(SessionValues.getIdEtablissement(),
					RefUtilConstant.CODIFICATION_DOMAINE_ORDER_LENGTH_KEY);
			if (order == null) {
				return null;
			}
			int orderLength = Integer.parseInt(order);
			identifiantLength = prefixLength + orderLength;
			return refDomaineServiceImpl.generateIdentify(prefix, orderLength);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}

	/**
	 * [DomaineBckBean.generateSubIdentify] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 avr. 2014 10:43:01
	 * @return
	 */
	public String generateSubIdentify() {
		try {
			String prefix = refParametreEtabServiceImpl.findValueOfKey(SessionValues.getIdEtablissement(),
					RefUtilConstant.CODIFICATION_SUB_DOMAINE_PREFIX_KEY);
			if (prefix == null) {
				return null;
			}
			int prefixLength = prefix.length();
			String order = refParametreEtabServiceImpl.findValueOfKey(SessionValues.getIdEtablissement(),
					RefUtilConstant.CODIFICATION_SUB_DOMAINE_ORDER_LENGTH_KEY);
			if (order == null) {
				return null;
			}
			int orderLength = Integer.parseInt(order);
			identifiantLength = prefixLength + orderLength;
			return refDomaineServiceImpl.generateIdentify(prefix, orderLength);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}

	/**
	 * [DomaineBckBean.identifiantLength] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 avr. 2014 10:36:58
	 * @return the identifiantLength
	 */
	public int getIdentifiantLength() {
		return identifiantLength;
	}

	/**
	 * [DomaineBckBean.identifiantLength] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 avr. 2014 10:36:58
	 * @param identifiantLength
	 *            the identifiantLength to set
	 */
	public void setIdentifiantLength(int identifiantLength) {
		this.identifiantLength = identifiantLength;
	}

	/**
	 * [DomaineBckBean.refParametreEtabServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 17:42:16
	 * @return the refParametreEtabServiceImpl
	 */
	public RefParametreEtabService getRefParametreEtabServiceImpl() {
		return refParametreEtabServiceImpl;
	}

	/**
	 * [DomaineBckBean.refParametreEtabServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 17:42:16
	 * @param refParametreEtabServiceImpl
	 *            the refParametreEtabServiceImpl to set
	 */
	public void setRefParametreEtabServiceImpl(RefParametreEtabService refParametreEtabServiceImpl) {
		this.refParametreEtabServiceImpl = refParametreEtabServiceImpl;
	}

	/**
	 * [DomaineBckBean.nameArabeDomaineChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 mai 2014 09:10:48
	 * @return the nameArabeDomaineChange
	 */
	public boolean isNameArabeDomaineChange() {
		return nameArabeDomaineChange;
	}

	/**
	 * [DomaineBckBean.nameArabeDomaineChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 mai 2014 09:10:48
	 * @param nameArabeDomaineChange
	 *            the nameArabeDomaineChange to set
	 */
	public void setNameArabeDomaineChange(boolean nameArabeDomaineChange) {
		this.nameArabeDomaineChange = nameArabeDomaineChange;
	}

	/**
	 * [DomaineBckBean.nameArabeSubDomaineChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 mai 2014 09:17:53
	 * @return the nameArabeSubDomaineChange
	 */
	public boolean isNameArabeSubDomaineChange() {
		return nameArabeSubDomaineChange;
	}

	/**
	 * [DomaineBckBean.nameArabeSubDomaineChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 mai 2014 09:17:53
	 * @param nameArabeSubDomaineChange
	 *            the nameArabeSubDomaineChange to set
	 */
	public void setNameArabeSubDomaineChange(boolean nameArabeSubDomaineChange) {
		this.nameArabeSubDomaineChange = nameArabeSubDomaineChange;
	}

}
