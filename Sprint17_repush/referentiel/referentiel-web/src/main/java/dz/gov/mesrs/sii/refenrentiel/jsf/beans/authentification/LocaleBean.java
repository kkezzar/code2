package dz.gov.mesrs.sii.refenrentiel.jsf.beans.authentification;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.permission.MenuBean;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

/**
 * <b>File :</b> LoacleBean.java<br>
 * <b>Creation date :</b> 18 April 2014<br>
 * <b>Description :</b><br>
 * Bean JSF to i18n
 * 
 * @author j.beldi
 * 
 */
@ManagedBean(name = "localeBean")
@SessionScoped
public class LocaleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	//private static final Log log = LogFactory.getLog(LocaleBean.class);
	private boolean languageChanged;
	/**
	 * NC Services
	 */
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;

	@ManagedProperty(value = "#{menuBean}")
	private MenuBean menuBean;

	/**
	 * [LocaleBean.nomenclatureServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 8 avr. 2014 15:34:31
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	/**
	 * [LocaleBean.nomenclatureServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 8 avr. 2014 15:34:31
	 * @param nomenclatureServiceImpl
	 *            the nomenclatureServiceImpl to set
	 */
	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	public LocaleBean() {
		super();
	}

	/**
	 * the current Local language
	 */
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot()
			.getLocale();
	/**
	 * the language
	 */
	private String language;

	private Integer idLanguage;

	private String dir = "ltr";

	private String positionMenu = "west";

	/**
	 * [LocaleBean.positionMenu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 17:11:50
	 * @return the positionMenu
	 */
	public String getPositionMenu() {
		return positionMenu;
	}

	/**
	 * [LocaleBean.positionMenu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 17:11:50
	 * @param positionMenu
	 *            the positionMenu to set
	 */
	public void setPositionMenu(String positionMenu) {
		this.positionMenu = positionMenu;
	}

	private Date currentDate = new Date();

	/**
	 * @return the language
	 */
	public String getLanguage() {

		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * [LocaleBean.idLanguage] Getter
	 * 
	 * @author BELDI Jamel on : 8 avr. 2014 15:32:27
	 * @return the idLanguage
	 */
	public Integer getIdLanguage() {
		return idLanguage;
	}

	/**
	 * [LocaleBean.idLanguage] Setter
	 * 
	 * @author BELDI Jamel on : 8 avr. 2014 15:32:27
	 * @param idLanguage
	 *            the idLanguage to set
	 */
	public void setIdLanguage(Integer idLanguage) {
		this.idLanguage = idLanguage;
	}

	/**
	 * [LocaleBean.changeLangue] Method
	 * 
	 * @author BELDI Jamel on : 8 avr. 2014 15:15:01
	 */
	public void changeLangue() {
		String oldLanguage = null;
		if (idLanguage != null) {
			oldLanguage = nomenclatureServiceImpl
					.findById(idLanguage.intValue()).getCode().toLowerCase();
		}
		if (oldLanguage != null && !oldLanguage.equals(language)) {
			setLanguageChanged(true);
			detroyBeanSession();
		}
		language = oldLanguage;
		locale = new Locale(language);
		if (language != null && language.equals("ar")) {
			dir = "rtl";
			positionMenu = "east";
		} else {
			dir = "ltr";
			positionMenu = "west";
		}
		menuBean.setLanguage(language);
		menuBean.setLanguageChanged(languageChanged);
		if (menuBean.getCompte() != null && menuBean.getCompte().getAdmin()) {
			menuBean.loadAdminMenu();
		} else {
			menuBean.loadMenu();
		}

	}

	/**
	 * [LocaleBean.dir] Getter
	 * 
	 * @author BELDI Jamel on : 8 avr. 2014 16:38:05
	 * @return the dir
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * [LocaleBean.dir] Setter
	 * 
	 * @author BELDI Jamel on : 8 avr. 2014 16:38:05
	 * @param dir
	 *            the dir to set
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * [LocaleBean.menuBean] Getter
	 * 
	 * @author BELDI Jamel on : 14 avr. 2014 09:14:26
	 * @return the menuBean
	 */
	public MenuBean getMenuBean() {
		return menuBean;
	}

	/**
	 * [LocaleBean.menuBean] Setter
	 * 
	 * @author BELDI Jamel on : 14 avr. 2014 09:14:26
	 * @param menuBean
	 *            the menuBean to set
	 */
	public void setMenuBean(MenuBean menuBean) {
		this.menuBean = menuBean;
	}

	/**
	 * [LocaleBean.currentDate] Getter
	 * 
	 * @author BELDI Jamel on : 16 avr. 2014 16:46:14
	 * @return the currentDate
	 */
	public Date getCurrentDate() {
		return new Date();
	}

	/**
	 * [LocaleBean.currentDate] Setter
	 * 
	 * @author BELDI Jamel on : 16 avr. 2014 16:46:14
	 * @param currentDate
	 *            the currentDate to set
	 */
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	
	
	public String getCurrentYear() {	
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		return currentYear+"";
	}

	/**
	 * [LocaleBean.languageChanged] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 mai 2014 14:30:29
	 * @return the languageChanged
	 */
	public boolean isLanguageChanged() {
		return languageChanged;
	}

	/**
	 * [LocaleBean.languageChanged] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 mai 2014 14:30:29
	 * @param languageChanged
	 *            the languageChanged to set
	 */
	public void setLanguageChanged(boolean languageChanged) {
		this.languageChanged = languageChanged;
	}

	/**
	 * [LocaleBean.detroyBeanSession] Method 
	 * @author MAKERRI Sofiane  on : 5 mai 2014  12:31:11
	 */
	public void detroyBeanSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Object request = facesContext.getExternalContext().getRequest();
		if (request instanceof HttpServletRequest) {
			facesContext.getExternalContext().getSessionMap()
					.remove("crudBean");
			facesContext.getExternalContext().getSessionMap()
					.remove("individuDroitBean");
			facesContext.getExternalContext().getSessionMap()
					.remove("groupeDroitBean");
			facesContext.getExternalContext().getSessionMap()
					.remove("structureDroitBean");
			facesContext.getExternalContext().getSessionMap()
					.remove("avenantDroitBean");
			facesContext.getExternalContext().getSessionMap()
					.remove("contratDroitBean");
			facesContext.getExternalContext().getSessionMap()
					.remove("DomainelmdDroitBean");
			facesContext.getExternalContext().getSessionMap()
					.remove("equipementDroitBean");
			facesContext.getExternalContext().getSessionMap()
					.remove("etablissementDroitBean");
			facesContext.getExternalContext().getSessionMap()
					.remove("evenementDroitBean");
			facesContext.getExternalContext().getSessionMap()
					.remove("lieuDroitBean");
			facesContext.getExternalContext().getSessionMap()
					.remove("SpecialitelmdDroitBean");
			facesContext.getExternalContext().getSessionMap()
					.remove("documentDroitBean");
		}
	}

}
