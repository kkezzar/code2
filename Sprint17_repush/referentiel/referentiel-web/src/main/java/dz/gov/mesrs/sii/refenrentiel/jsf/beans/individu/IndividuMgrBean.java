/**
 * 
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.individu;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.affectation.AffectationMgrBean;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.ComboBckBean;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.etatvalidation.SituationMgrBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;
import dz.gov.mesrs.sii.referentiel.jsf.beans.coordonnee.CoordonneeMgrBean;

/**
 * @author jbeldi
 * 
 */
@ManagedBean(name = "individuMgrBean")
@ViewScoped
public class IndividuMgrBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private String searchInput;
	private String searchValue;
	private String name;
	private String surName;

	private ResourceBundle bundle;
	private ResourceBundle bundleIndividu;
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuServiceImpl;
	private static final Log log = LogFactory.getLog(IndividuMgrBean.class);
	private RefIndividuDto refIndividuDto;
	private RefIndividuDto searchDto;
	private boolean updateMode;
	private Boolean readMode;
	@ManagedProperty(value = "#{comboBckBean}")
	private ComboBckBean comboBckBean;
	@ManagedProperty(value = "#{affectationMgrBean}")
	private AffectationMgrBean affectationMgrBean;
	@ManagedProperty(value = "#{coordonneeMgrBean}")
	private CoordonneeMgrBean coordonneeMgrBean;
	@ManagedProperty(value = "#{situationMgrBean}")
	private SituationMgrBean situationMgrBean;
	
	@ManagedProperty(value = "#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabServiceImpl;
	private boolean entityChange;
	@ManagedProperty("#{flash}")
	private Flash flash;

	/**
	 * Constructor of IndividuMgrBean
	 */
	public IndividuMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		bundleIndividu = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.INDIVIDU_MESSAGES_FILE_NAME);
		searchDto = new RefIndividuDto();

	}

	@PostConstruct
	public void init() {
		refIndividuDto = (RefIndividuDto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("refIndividuDto");
		readMode = (Boolean) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("readMode");
		if(readMode == null) {
			readMode = false;
		}
		if(refIndividuDto == null) {
			refIndividuDto = new RefIndividuDto();
			setDefaultValues();
		}
		//refIndividuDto = new RefIndividuDto();
		/*if (this.searchValue != null && !this.searchValue.equals("null")
				&& !this.searchValue.trim().isEmpty()) {
			this.searchValue = this.searchValue.trim();
			this.findGeneric();
		} else {
			// vider la table de recherche
			individuBckBean.setListRefIndividuDto(null);
		}*/

	}

	/**
	 * @return the searchValue
	 */

	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	/**
	 * @return the refIndividuServiceImpl
	 */
	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}

	/**
	 * @param refIndividuServiceImpl
	 *            the refIndividuServiceImpl to set
	 */
	public void setRefIndividuServiceImpl(
			RefIndividuService refIndividuServiceImpl) {
		this.refIndividuServiceImpl = refIndividuServiceImpl;
	}

	/**
	 * @return the refIndividuDto
	 */
	public RefIndividuDto getRefIndividuDto() {
		return refIndividuDto;
	}

	/**
	 * @param refIndividuDto
	 *            the refIndividuDto to set
	 */
	public void setRefIndividuDto(RefIndividuDto refIndividuDto) {
		this.refIndividuDto = refIndividuDto;
	}

	/**
	 * @return the searchDto
	 */
	public RefIndividuDto getSearchDto() {

		return searchDto;
	}

	/**
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(RefIndividuDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * @return the updateMode
	 */
	public boolean isUpdateMode() {
		return updateMode;
	}

	/**
	 * @param updateMode
	 *            the updateMode to set
	 */
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}

	/**
	 * @return the readMode
	 */
	public Boolean getReadMode() {
		return readMode;
	}

	/**
	 * @param readMode
	 *            the readMode to set
	 */
	public void setReadMode(Boolean readMode) {
		this.readMode = readMode;
	}

	
	/**
	 * 
	 * @param event
	 * @return
	 */
	public String toDetails() {
		setReadMode(true);
		setUpdateMode(false);
		//individuBckBean.setListRefIndividuDto(null);
		return "toDetails";
	}

	/**
	 * navigate to new individu page
	 * 
	 * @return outcome
	 */
	public String toNew() {
		setReadMode(true);
		setUpdateMode(false);
		return "toNew";
	}

	/**
	 * 
	 * navigate to edit individu page
	 * 
	 * @return
	 */
	public String toEdit() {
		setReadMode(false);
		setUpdateMode(true);
		//individuBckBean.setListRefIndividuDto(null);
		return "toEdit";
	}

	/**
	 * [IndividuMgrBean.findGeneric] Method Find the list of individu by the the
	 * search input
	 * 
	 * @author jbeldi on : 15 janv. 2014 11:18:14
	 */
	public void findGeneric() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefIndividuDto> result = null;
			if (searchValue != null && searchValue.trim().isEmpty()) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info")
						+ ": "
						+ bundleIndividu
								.getString("individu_warn_search_empty"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			String value = refParametreEtabServiceImpl.findValueOfKey(
					SessionValues.getIdEtablissement(),
					RefUtilConstant.MAX_SEARCH_INDIVIDU_KEY);
			int maxIndividu = RefUtilConstant.strToInt(value);
			if (maxIndividu > 0) {
				result = refIndividuServiceImpl.findGeneric(this.searchValue,
						maxIndividu);
			} else {
				result = refIndividuServiceImpl.findGeneric(this.searchValue);
			}
			if (result == null || result.isEmpty()) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				

			}

		} catch (Exception e) {
			
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * [IndividuMgrBean.findAdvanced] Method to Find the list of individu by the
	 * the advanced search
	 * 
	 * @author jbeldi on : 15 janv. 2014 11:17:49
	 */
	public void findAdvanced() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefIndividuDto> result = null;
			String value = refParametreEtabServiceImpl.findValueOfKey(
					SessionValues.getIdEtablissement(),
					RefUtilConstant.MAX_SEARCH_INDIVIDU_KEY);
			int maxIndividu = RefUtilConstant.strToInt(value);
			if (maxIndividu > 0) {
				 result = refIndividuServiceImpl
						.findAdvanced(searchDto, maxIndividu);
			} else {
				result = refIndividuServiceImpl
						.findAdvanced(searchDto);
			}

			if (result == null || result.isEmpty()) {
				
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {

			}
		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String toSearch() {
		
		return "toSearchIndividu";
	}

	public String saveIndividu() {
		FacesMessage msg = new FacesMessage();
		try {
			
			if (!entityChange) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_update_none"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return null;
			}
			
			if (updateMode) {
				refIndividuServiceImpl.update(refIndividuDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				RefIndividuDto refIndividuDtoFound = refIndividuServiceImpl
						.findByRefIndividuDto(refIndividuDto);
				if (refIndividuDtoFound != null) {
					refIndividuDtoFound.setIdentifiant(null);
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle
							.getString("error_echec")
							+ ": "
							+ bundleIndividu
									.getString("individu_warn_already_exist"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return "";
				} else {
					refIndividuServiceImpl.save(refIndividuDto);
					setUpdateMode(true);

					
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundle.getString("msg_success") + ": "
							+ bundle.getString("msg_success_enregistrement"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					//initBeans();
					FacesContext.getCurrentInstance().getExternalContext().getFlash().put("refIndividuDto", refIndividuDto);
					FacesContext.getCurrentInstance().getExternalContext().getFlash().put("entity", "individu");
					FacesContext.getCurrentInstance().getExternalContext().getFlash().put("idEntity", refIndividuDto.getId());
					FacesContext.getCurrentInstance().getExternalContext().getFlash().put("readMode", true);
					flash.setKeepMessages(true);
					return "individuEdit?faces-redirect=true";
				}

			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
			log.info(e.getMessage());
		}
		return null;
	}

	
	/**
	 * [IndividuMgrBean.initBeans] Method 
	 * @author MAKERRI Sofiane  on : 6 janv. 2015  13:50:35
	 */
	private void initBeans() {
		affectationMgrBean.setEntity("individu");
		affectationMgrBean.setIdEntity(refIndividuDto.getId());
		coordonneeMgrBean.setEntity("individu");
		coordonneeMgrBean.setIdEntity(refIndividuDto.getId());
		situationMgrBean.setEntity("individu");
		situationMgrBean.setIdEntity(refIndividuDto.getId());
	}
	
	/**
	 * [IndividuMgrBean.setDefaultValues] Method
	 * 
	 * @author MAKERRI Sofiane on : 29 avr. 2014 10:38:41
	 */
	public void setDefaultValues() {
		refIndividuDto.setCiviliteId(comboBckBean.getDefaultCivilite());
		refIndividuDto.setGroupeSanguinId(comboBckBean
				.getDefaultGroupeSanguin());
		refIndividuDto.setNationaliteId(comboBckBean.getDefaultNationalite());
		refIndividuDto.setServiceNatId(comboBckBean.getDefaultSitSceNat());
		refIndividuDto.setSituationFamilialeId(comboBckBean
				.getDefaultSituationFamiliale());
	}

	/**
	 * [IndividuMgrBean.comboBckBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 avr. 2014 10:21:02
	 * @return the comboBckBean
	 */
	public ComboBckBean getComboBckBean() {
		return comboBckBean;
	}

	/**
	 * [IndividuMgrBean.comboBckBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 avr. 2014 10:21:02
	 * @param comboBckBean
	 *            the comboBckBean to set
	 */
	public void setComboBckBean(ComboBckBean comboBckBean) {
		this.comboBckBean = comboBckBean;
	}

	/**
	 * [IndividuMgrBean.refParametreEtabServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 avr. 2014 14:00:32
	 * @return the refParametreEtabServiceImpl
	 */
	public RefParametreEtabService getRefParametreEtabServiceImpl() {
		return refParametreEtabServiceImpl;
	}

	/**
	 * [IndividuMgrBean.refParametreEtabServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 avr. 2014 14:00:32
	 * @param refParametreEtabServiceImpl
	 *            the refParametreEtabServiceImpl to set
	 */
	public void setRefParametreEtabServiceImpl(
			RefParametreEtabService refParametreEtabServiceImpl) {
		this.refParametreEtabServiceImpl = refParametreEtabServiceImpl;
	}

	/**
	 * [IndividuMgrBean.name] Getter 
	 * @author MAKERRI Sofiane on : 5 mai 2014  09:15:20
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * [IndividuMgrBean.name] Setter 
	 * @author MAKERRI Sofiane on : 5 mai 2014  09:15:20
	 * @param name the name to set
	 */
	public void setName(String name) {
		if(name != null && name.equals("null")) {
			this.name = null;
		} else 	this.name = name;
	}

	/**
	 * [IndividuMgrBean.surName] Getter 
	 * @author MAKERRI Sofiane on : 5 mai 2014  09:15:20
	 * @return the surName
	 */
	public String getSurName() {
		return surName;
	}

	/**
	 * [IndividuMgrBean.surName] Setter 
	 * @author MAKERRI Sofiane on : 5 mai 2014  09:15:20
	 * @param surName the surName to set
	 */
	public void setSurName(String surName) {
		if(surName != null && surName.equals("null")) {
			this.surName = null;
		} else 	this.surName = surName;
		
	}
	
	
	/**
	 * [IndividuMgrBean.findByNames] Method 
	 * @author MAKERRI Sofiane  on : 5 mai 2014  10:19:41
	 */
	public void findByNames() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefIndividuDto> result = null;
			if ((name != null && name.trim().isEmpty())||(surName != null && surName.trim().isEmpty())) {
				
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info")
						+ ": "
						+ bundleIndividu
								.getString("individu_warn_search_empty"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			String value = refParametreEtabServiceImpl.findValueOfKey(
					SessionValues.getIdEtablissement(),
					RefUtilConstant.MAX_SEARCH_INDIVIDU_KEY);
			int maxIndividu = RefUtilConstant.strToInt(value);
			if (maxIndividu > 0) {
				result = refIndividuServiceImpl.findByNames(this.name, this.surName, 
						maxIndividu);
			} else {
				result = refIndividuServiceImpl.findByNames(this.name, this.surName);
			}
			if (result == null || result.isEmpty()) {
				
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				

			}

		} catch (Exception e) {
			
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}
	
	
	/**
	 * [IndividuMgrBean.entityChanged] Method 
	 * @author MAKERRI Sofiane  on : 6 mai 2014  11:55:01
	 * @param event
	 */
	public void entityChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			entityChange = true;
		}
	}

	/**
	 * [IndividuMgrBean.entityChange] Getter 
	 * @author MAKERRI Sofiane on : 6 mai 2014  11:55:26
	 * @return the entityChange
	 */
	public boolean isEntityChange() {
		return entityChange;
	}

	/**
	 * [IndividuMgrBean.entityChange] Setter 
	 * @author MAKERRI Sofiane on : 6 mai 2014  11:55:26
	 * @param entityChange the entityChange to set
	 */
	public void setEntityChange(boolean entityChange) {
		this.entityChange = entityChange;
	}

	/**
	 * [IndividuMgrBean.flash] Getter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  12:52:07
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [IndividuMgrBean.flash] Setter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  12:52:07
	 * @param flash the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [IndividuMgrBean.affectationMgrBean] Getter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  13:43:43
	 * @return the affectationMgrBean
	 */
	public AffectationMgrBean getAffectationMgrBean() {
		return affectationMgrBean;
	}

	/**
	 * [IndividuMgrBean.affectationMgrBean] Setter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  13:43:43
	 * @param affectationMgrBean the affectationMgrBean to set
	 */
	public void setAffectationMgrBean(AffectationMgrBean affectationMgrBean) {
		this.affectationMgrBean = affectationMgrBean;
	}

	/**
	 * [IndividuMgrBean.coordonneeMgrBean] Getter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  13:43:43
	 * @return the coordonneeMgrBean
	 */
	public CoordonneeMgrBean getCoordonneeMgrBean() {
		return coordonneeMgrBean;
	}

	/**
	 * [IndividuMgrBean.coordonneeMgrBean] Setter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  13:43:43
	 * @param coordonneeMgrBean the coordonneeMgrBean to set
	 */
	public void setCoordonneeMgrBean(CoordonneeMgrBean coordonneeMgrBean) {
		this.coordonneeMgrBean = coordonneeMgrBean;
	}

	/**
	 * [IndividuMgrBean.situationMgrBean] Getter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  13:43:43
	 * @return the situationMgrBean
	 */
	public SituationMgrBean getSituationMgrBean() {
		return situationMgrBean;
	}

	/**
	 * [IndividuMgrBean.situationMgrBean] Setter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  13:43:43
	 * @param situationMgrBean the situationMgrBean to set
	 */
	public void setSituationMgrBean(SituationMgrBean situationMgrBean) {
		this.situationMgrBean = situationMgrBean;
	}

	

}
