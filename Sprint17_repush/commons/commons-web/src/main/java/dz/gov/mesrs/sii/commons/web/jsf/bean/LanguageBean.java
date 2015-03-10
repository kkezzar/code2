package dz.gov.mesrs.sii.commons.web.jsf.bean;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.context.FacesContext;

/**
 * Cette class permet de gerer l'Internationnalisation de l'application et le
 * changement de la langue
 * 
 * @author Mounir.MESSAOUDI on : 7 déc. 2014 15:53:52
 */
public class LanguageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * FRENCH language
	 * 
	 * @return String
	 */
	public String activateFR() {
		System.out.println("--------------activate fr");
		// FacesUtils.getSessionBean().setLocale(Locale.FRENCH);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(Locale.FRENCH);

		return null;
	}

	/**
	 * ENGLISH language
	 * 
	 * @return String
	 */
	public String activateEN() {
		System.out.println("--------------activate en");
		// FacesUtils.getSessionBean().setLocale(Locale.ENGLISH);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(Locale.ENGLISH);

		return null;
	}

	/**
	 * language arabe
	 * 
	 * @return String
	 */
	// public String activateAR() {
	// System.out.println("--------------activate Ar");
	// // FacesUtils.getSessionBean().setLocale(Locale.ENGLISH);
	// FacesContext context = FacesContext.getCurrentInstance();
	// context.getViewRoot().setLocale(Locale.);
	//
	//
	// return null;
	// }

	/**
	 * GERMAN language
	 * 
	 * @return String
	 */
	public String activateDE() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(Locale.GERMAN);

		return null;
	}

	/**
	 * ITALIAN language
	 * 
	 * @return String
	 */
	public String activateIT() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(Locale.ITALIAN);

		return null;
	}

	/**
	 * SPANISH language
	 * 
	 * @return String
	 */
	public String activateES() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale("es", "ES"));

		return null;
	}

	/**
	 * TURKISH language
	 * 
	 * @return String
	 */
	public String activateTR() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale("tr", "TR"));

		return null;
	}

	/**
	 * RUSSIAN language
	 * 
	 * @return String
	 */
	public String activateRU() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale("ru", "RU"));

		return null;
	}

	/**
	 * PORTUGUESE language
	 * 
	 * @return String
	 */
	public String activatePT() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale("pt", "PT"));

		return null;
	}

	/**
	 * SWEDISH language
	 * 
	 * @return String
	 */
	public String activateSE() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale("se", "SE"));

		return null;
	}

	/**
	 * NORWEGIAN language
	 * 
	 * @return String
	 */
	public String activateNO() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale("no", "NO"));

		return null;
	}

	/**
	 * DANISH language
	 * 
	 * @return String
	 */
	public String activateDK() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale("dk", "DK"));

		return null;
	}

	/**
	 * FLEMISH language
	 * 
	 * @return String
	 */
	public String activateBE() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale("be", "BE"));

		return null;
	}

	/**
	 * DUTCH language
	 * 
	 * @return String
	 */
	public String activateNL() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale("nl", "NL"));

		return null;
	}

	/**
	 * POLISH language
	 * 
	 * @return String
	 */
	public String activatePL() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale("pl", "PL"));

		return null;
	}

	/**
	 * FINNISH language
	 * 
	 * @return String
	 */
	public String activateFI() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale("fi", "FI"));

		return null;
	}

	/**
	 * GREEK language
	 * 
	 * @return String
	 */
	public String activateGR() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale("gr", "GR"));

		return null;
	}

}