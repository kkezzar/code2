/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.impression.AttestationPrintBean.java] 
 * @author BELDI Jamel on : 18 juin 2014  15:46:36
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.impression;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.impression.ImpressionService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;

/**
 * @author BELDI Jamel  on : 18 juin 2014  15:46:36
 */
/**
 * @author BELDI Jamel on : 8 juin 2014 14:52:30
 */
/**
 * @author MAKERRI Sofiane  on : 7 déc. 2014  10:13:39
 */
/**
 * @author MAKERRI Sofiane  on : 7 déc. 2014  10:25:38
 */
/**
 * @author MAKERRI Sofiane on : 7 déc. 2014 10:25:39
 */
@ManagedBean(name = "printBean")
@ViewScoped
public class PrintBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 18 juin 2014 16:00:48
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(PrintBean.class);
	@ManagedProperty("#{impressionService}")
	private ImpressionService impressionService;

	private List<DossierInscriptionAdministrativeDto> dossierInscriptionResultSearch;
	private List<SelectItem> listeAnneeAcademiques;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;

	private DossierInscriptionAdministrativeDto dossierInscriptionSearchDto;
	@ManagedProperty(value = "#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;

	@ManagedProperty("#{printMgrBean}")
	private PrintMgrBean printMgrBean;

	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;

	private ResourceBundle diaBundle;

	/**
	 * [AttestationPrintBean.AttestationPrintBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 18 juin 2014 15:46:36
	 */
	public PrintBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		diaBundle = facesContext
				.getApplication()
				.getResourceBundle(
						facesContext,
						Const.DOSSIER_INSCRIPTION_ADMINISTRATIF_RESSOURCES_BUNDLE_FILE_NAME);

	}

	@PostConstruct
	public void init() {
		dossierInscriptionSearchDto = new DossierInscriptionAdministrativeDto();
		dossierInscriptionSearchDto.setRefEtablissementId(sessionBean
				.getIdEtablissement());
		loadAnneeAcademique();
	}

	/**
	 * [PrintBean.loadAnneeAcademique] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 déc. 2014 10:25:42
	 */
	private void loadAnneeAcademique() {
		listeAnneeAcademiques = utilBean.loadAnneeAcademique();
	}

	/**
	 * [PrintBean.searchDossierInscription] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 déc. 2014 10:13:42
	 */
	public void searchDossierInscription() {
		loadDossierInscriptionResultSearch();
	}

	/**
	 * [PrintBean.loadDossierInscriptionResultSearch] Method
	 * 
	 * @author BELDI Jamel on : 23 juin 2014 15:38:34
	 */
	public void loadDossierInscriptionResultSearch() {
		try {
			dossierInscriptionResultSearch = null;

			if (dossierInscriptionSearchDto != null
					&& ((dossierInscriptionSearchDto.getAnneeAcademiqueId() != null && dossierInscriptionSearchDto
							.getAnneeAcademiqueId().intValue() != 0))) {

				dossierInscriptionResultSearch = dossierInscriptionAdministrativeService
						.findAdvanced(dossierInscriptionSearchDto, false);
				if (dossierInscriptionResultSearch == null
						|| dossierInscriptionResultSearch.isEmpty()) {
					dossierInscriptionResultSearch = null;
					Utility.showWarningMessage(diaBundle
							.getString("dossier_inscription_datable_list_dossier_etudiant_no_result"));
					return;
				}

			}
		} catch (Exception e) {
			dossierInscriptionResultSearch = new ArrayList<DossierInscriptionAdministrativeDto>();
			Utility.showErrorMessage(e.getMessage());
		}

	}

	/**
	 * [PrintBean.printAttestationPDF] Method
	 * 
	 * @author BELDI Jamel on : 23 juin 2014 15:38:25
	 */
	public void printAttestationPDF() {
		log.info("printAttestationPDF....");

		try {
			Collection<DossierInscriptionAdministrativeDto> etudiants = new ArrayList<>();

			if (dossierInscriptionResultSearch != null
					&& !dossierInscriptionResultSearch.isEmpty()) {
				for (DossierInscriptionAdministrativeDto inscription : dossierInscriptionResultSearch) {
					if (inscription.getAnneeAcademiqueCode() == null
							// || inscription.getIndividuDateNaissance() == null
							|| inscription.getIndividuLieuNaissance() == null
							|| inscription.getIndividuNomArabe() == null
							|| inscription.getIndividuPrenomArabe() == null
							|| inscription.getNumeroInscription() == null
							|| (inscription.getRefLibelleFiliereArabe() == null && inscription
									.getRefLibelleDomaineArabe() == null)
							|| inscription.getRefLibelleNiveauArabe() == null) {
						etudiants.add(inscription);
					}

				}
			}
			etudiants = dossierInscriptionResultSearch;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext
					.getExternalContext()
					.getRealPath(
							"WEB-INF/classes/jasper/inscription/attestation_model.jrxml");

			byte[] bytes = impressionService.viewPDFWithDataSource(
					RESOURCE_PATH_TO_INPUT_FILE_JASPER, null, etudiants);

			printMgrBean.imprimer(
					bytes,
					"attestationinscription_"
							+ sessionBean.getCodeEtablissement()
							+ generateDateString(), "pdf");
		} catch (Exception e) {
			dossierInscriptionResultSearch = new ArrayList<DossierInscriptionAdministrativeDto>();
			Utility.showErrorMessage(e.getMessage());

		}
	}

	/**
	 * [AttestationPrintBean.impressionService] Getter
	 * 
	 * @author BELDI Jamel on : 18 juin 2014 16:06:45
	 * @return the impressionService
	 */
	public ImpressionService getImpressionService() {
		return impressionService;
	}

	/**
	 * [AttestationPrintBean.impressionService] Setter
	 * 
	 * @author BELDI Jamel on : 18 juin 2014 16:06:45
	 * @param impressionService
	 *            the impressionService to set
	 */
	public void setImpressionService(ImpressionService impressionService) {
		this.impressionService = impressionService;
	}

	/**
	 * [AttestationPrintBean.getListeAnneeAcademiques] Method
	 * 
	 * @author BELDI Jamel on : 19 juin 2014 14:51:14
	 * @return
	 */
	public List<SelectItem> getListeAnneeAcademiques() {
		return listeAnneeAcademiques;
	}

	/**
	 * [AttestationPrintBean.setListeAnneeAcademiques] Method
	 * 
	 * @author BELDI Jamel on : 19 juin 2014 14:51:05
	 * @param listeAnneeAcademiques
	 */
	public void setListeAnneeAcademiques(List<SelectItem> listeAnneeAcademiques) {
		this.listeAnneeAcademiques = listeAnneeAcademiques;
	}

	/**
	 * [PrintBean.dossierInscriptionResultSearch] Getter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 15:57:09
	 * @return the dossierInscriptionResultSearch
	 */
	public List<DossierInscriptionAdministrativeDto> getDossierInscriptionResultSearch() {
		return dossierInscriptionResultSearch;
	}

	/**
	 * [PrintBean.dossierInscriptionResultSearch] Setter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 15:57:09
	 * @param dossierInscriptionResultSearch
	 *            the dossierInscriptionResultSearch to set
	 */
	public void setDossierInscriptionResultSearch(
			List<DossierInscriptionAdministrativeDto> dossierInscriptionResultSearch) {
		this.dossierInscriptionResultSearch = dossierInscriptionResultSearch;
	}

	/**
	 * [PrintBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 16:01:25
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [PrintBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 16:01:25
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [PrintBean.dossierInscriptionSearchDto] Getter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 16:02:01
	 * @return the dossierInscriptionSearchDto
	 */
	public DossierInscriptionAdministrativeDto getDossierInscriptionSearchDto() {
		return dossierInscriptionSearchDto;
	}

	/**
	 * [PrintBean.dossierInscriptionSearchDto] Setter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 16:02:01
	 * @param dossierInscriptionSearchDto
	 *            the dossierInscriptionSearchDto to set
	 */
	public void setDossierInscriptionSearchDto(
			DossierInscriptionAdministrativeDto dossierInscriptionSearchDto) {
		this.dossierInscriptionSearchDto = dossierInscriptionSearchDto;
	}

	/**
	 * [PrintBean.dossierInscriptionAdministrativeService] Getter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 16:07:53
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [PrintBean.dossierInscriptionAdministrativeService] Setter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 16:07:53
	 * @param dossierInscriptionAdministrativeService
	 *            the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	private String generateDateString() {
		Calendar c = Calendar.getInstance();
		return String.valueOf(c.get(Calendar.YEAR))
				+ String.valueOf(c.get(Calendar.MONTH) + 1)
				+ String.valueOf(c.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * [PrintBean.printMgrBean] Getter
	 * 
	 * @author BELDI Jamel on : 25 juin 2014 12:47:24
	 * @return the printMgrBean
	 */
	public PrintMgrBean getPrintMgrBean() {
		return printMgrBean;
	}

	/**
	 * [PrintBean.printMgrBean] Setter
	 * 
	 * @author BELDI Jamel on : 25 juin 2014 12:47:24
	 * @param printMgrBean
	 *            the printMgrBean to set
	 */
	public void setPrintMgrBean(PrintMgrBean printMgrBean) {
		this.printMgrBean = printMgrBean;
	}

	/**
	 * [PrintBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 déc. 2014 10:24:47
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [PrintBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 déc. 2014 10:24:47
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [PrintBean.anneeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 déc. 2014 11:35:26
	 */
	public void anneeChanged() {
		searchDossierInscription();
	}

}
