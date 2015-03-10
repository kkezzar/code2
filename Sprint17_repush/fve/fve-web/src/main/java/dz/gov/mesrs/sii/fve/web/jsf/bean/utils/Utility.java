package dz.gov.mesrs.sii.fve.web.jsf.bean.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefHistoriqueDto;

public final class Utility {

	public static ResourceBundle getOffreFormationRessourcesBundle(
			FacesContext facesContext) {
		return facesContext.getApplication().getResourceBundle(facesContext,
				Const.OFFRE_FORMATION_RESSOURCES_BUNDLE_FILE_NAME);
	}

	public static ResourceBundle getCommonRessourcesBundle(
			FacesContext facesContext) {
		return facesContext.getApplication().getResourceBundle(facesContext,
				Const.COMMON_RESSOURCES_BUNDLE_FILE_NAME);
	}
	
	
	/**
	 * [Utility.getMcRessourcesBundle] Method 
	 * @author MAKERRI Sofiane  on : 24 nov. 2014  16:24:23
	 * @param facesContext
	 * @return
	 */
	public static ResourceBundle getMcRessourcesBundle(
			FacesContext facesContext) {
		return facesContext.getApplication().getResourceBundle(facesContext,
				Const.MATIERE_CONSTITUTIVE_RESSOURCES_BUNDLE_FILE_NAME);
	}
	
	/**
	 * [Utility.geUeRessourcesBundle] Method 
	 * @author MAKERRI Sofiane  on : 25 nov. 2014  08:44:37
	 * @param facesContext
	 * @return
	 */
	public static ResourceBundle geUeRessourcesBundle(
			FacesContext facesContext) {
		return facesContext.getApplication().getResourceBundle(facesContext,
				Const.UNITE_ENSEIGNEMENT_RESSOURCES_BUNDLE_FILE_NAME);
	}

	/**
	 * [Utility.getRefHistoriqueDto] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 16:08:37
	 * @param idCompte
	 * @param idEtablissement
	 * @param codeFonction
	 * @param idOccurence
	 * @param nomEntite
	 * @param typeAction
	 * @return
	 */
	public static RefHistoriqueDto getRefHistoriqueDto(Integer idCompte,
			Integer idEtablissement, String codeFonction, Integer idOccurence,
			String nomEntite, Integer typeAction) {
		RefHistoriqueDto refHistoriqueDto = new RefHistoriqueDto();
		refHistoriqueDto.setTypeAction(typeAction);
		refHistoriqueDto.setIdOccurence(idOccurence);
		refHistoriqueDto.setIdCompte(idCompte);
		refHistoriqueDto.setCodeFonction(codeFonction);
		refHistoriqueDto.setIdEtablissement(idEtablissement);
		refHistoriqueDto.setNomEntite(nomEntite);
		return refHistoriqueDto;
	}

	/**
	 * [Utility.formatDate] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 09:50:13
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return null;
		}

		if (pattern == null) {
			throw new NullPointerException("pattern");
		}

		Locale locale = FacesContext.getCurrentInstance().getViewRoot()
				.getLocale();
		return new SimpleDateFormat(pattern, locale).format(date);
	}

	/**
	 * [GroupeAffectationBean.showErrorMessage] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 09:26:03
	 * @param msg
	 */
	public static void showErrorMessage(String msg) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication()
				.getResourceBundle(facesContext,
						CursusConstants.COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_ERROR);
		face.setSummary(bundleCommon.getString("error_echec") + ": " + msg);
		FacesContext.getCurrentInstance().addMessage(null, face);
	}

	/**
	 * [Utility.showWarningMessage] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 12:04:39
	 * @param msg
	 */
	public static void showWarningMessage(String msg) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication()
				.getResourceBundle(facesContext,
						CursusConstants.COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_WARN);
		face.setSummary(bundleCommon.getString("warn_info") + ": " + msg);
		FacesContext.getCurrentInstance().addMessage(null, face);
	}

	/**
	 * [GroupeAffectationBean.showSuccessMessage] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 09:26:16
	 * @param msg
	 */
	public static void showSuccessSaveMessage() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication()
				.getResourceBundle(facesContext,
						CursusConstants.COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_INFO);
		face.setSummary(bundleCommon.getString("msg_success") + ": "
				+ bundleCommon.getString("msg_success_enregistrement"));
		FacesContext.getCurrentInstance().addMessage(null, face);
	}

	/**
	 * [Utility.showSuccessMessage] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 09:07:37
	 */
	public static void showSuccessUpdateMessage() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication()
				.getResourceBundle(facesContext,
						CursusConstants.COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_INFO);
		face.setSummary(bundleCommon.getString("msg_success") + ": "
				+ bundleCommon.getString("msg_success_modification"));
		FacesContext.getCurrentInstance().addMessage(null, face);
	}

	/**
	 * [Utility.showSuccessMessage] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 10:19:05
	 */
	public static void showSuccessDeleteMessage() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication()
				.getResourceBundle(facesContext,
						CursusConstants.COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_INFO);
		face.setSummary(bundleCommon.getString("msg_success") + ": "
				+ bundleCommon.getString("msg_success_suppression"));
		FacesContext.getCurrentInstance().addMessage(null, face);
	}

	/**
	 * [Utility.showSuccessMessage] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 sept. 2014 10:51:55
	 * @param msg
	 */
	public static void showSuccessMessage(String msg) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundleCommon = facesContext.getApplication()
				.getResourceBundle(facesContext,
						CursusConstants.COMMON_BUNDLE_MSG_NAME);
		FacesMessage face = new FacesMessage();
		face.setSeverity(FacesMessage.SEVERITY_INFO);
		face.setSummary(bundleCommon.getString("msg_success") + ": " + msg);
		FacesContext.getCurrentInstance().addMessage(null, face);
	}
	
	/**
	 * [Utility.showSuccessSaveOrUpdateMessage] Method 
	 * @author MAKERRI Sofiane  on : 25 nov. 2014  12:27:29
	 * @param save
	 */
	public static void showSuccessSaveOrUpdateMessage(boolean save) {
		if(save) {
			showSuccessSaveMessage();
		} else {
			showSuccessUpdateMessage();
		}
	}

	/**
	 * [Utility.Double2position] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 08:12:25
	 * @param value
	 * @return
	 */
	public static Double double2position(Double value) {
		if (value != null) {
			value = Math.round(value * Math.pow(10, 2)) / Math.pow(10, 2);
		}
		return value;
	}

	/**
	 * [Utility.formatNote] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 oct. 2014 15:33:39
	 * @param note
	 * @return
	 */
	public static String formatNote(Double note) {
		
		if (note != null) {
			String ret = note.toString();
			StringTokenizer st = new StringTokenizer(ret, ".");
			
			//String[] token = ret.split(".");
			if (st.countTokens() > 2) {
				return "00.00";
			}
			if (st.countTokens() == 2) {
				String s1 = st.nextToken();
				String s2 = st.nextToken();
				if (s1.length() == 1) {
					s1 = "0" + s1;
				}
				if (s2.length() == 1) {
					s2 = s2 + "0";
				}
				ret = s1 + "." + s2;
				return ret;
			}
			if (st.countTokens() == 1) {
				String s1 = st.nextToken();
				if (s1.length() == 1) {
					s1 = "0" + s1;
				}
				return s1;
			}

		}
		return "00.00";
	}
	
	/**
	 * [Utility.formatNoteWith2Position] Method 
	 * @author MAKERRI Sofiane  on : 11 nov. 2014  11:47:38
	 * @param note
	 * @return
	 */
	public static String formatNoteWith2Position(Double note) {
		if (note != null) {
			note = double2position(note);
		}
		return formatNote(note);
	}
	
	/**
	 * [Utility.isBilanSession] Method 
	 * @author MAKERRI Sofiane  on : 29 déc. 2014  12:04:01
	 * @param type
	 * @return
	 */
	public static boolean isBilanSession(int type) {
		if(type == UtilConstants.BILAN_TYPE_SESSION) {
			return true;
		}
		return false;
	}
	
	/**
	 * [Utility.isBilanPeriode] Method 
	 * @author MAKERRI Sofiane  on : 29 déc. 2014  12:03:22
	 * @param type
	 * @return
	 */
	public static boolean isBilanPeriode(int type) {
		if(type == UtilConstants.BILAN_TYPE_PERIODE) {
			return true;
		}
		return false;
	}
	
	/**
	 * [Utility.isBilanAnnuel] Method 
	 * @author MAKERRI Sofiane  on : 29 déc. 2014  12:03:25
	 * @param type
	 * @return
	 */
	public static  boolean isBilanAnnuel(int type) {
		if(type == UtilConstants.BILAN_TYPE_ANNUEL) {
			return true;
		}
		return false;
	}
}
