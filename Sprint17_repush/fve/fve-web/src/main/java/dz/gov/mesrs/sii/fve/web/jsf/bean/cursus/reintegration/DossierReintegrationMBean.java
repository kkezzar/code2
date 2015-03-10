/**
 *  
 * @author Mounir.MESSAOUDI on : 28 sept. 2014 13:05:25
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.reintegration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Reintegration;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.CongeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ReintegrationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.CongeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.ReintegrationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefDocumentService;

/**
 * Mangedbean pour gerer les demandes de reintegration
 * 
 * @author Mounir.MESSAOUDI on : 1 oct. 2014 10:30:40
 */

@ManagedBean(name = "dossierReintegrationMBean")
@ViewScoped
public class DossierReintegrationMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Log log = LogFactory
			.getLog(DossierReintegrationMBean.class);

	private ResourceBundle reintegrationBundle;
	private ResourceBundle bundleCommon;

	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;

	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;

	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;

	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;

	@ManagedProperty("#{offreFormationService}")
	private OffreFormationService offreFormationService;

	@ManagedProperty("#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;

	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;

	@ManagedProperty(value = "#{reintegrationService}")
	private ReintegrationService reintegrationService;

	@ManagedProperty(value = "#{congeAcademiqueService}")
	private CongeAcademiqueService congeAcademiqueService;

	@ManagedProperty("#{refDocumentServiceImpl}")
	private RefDocumentService documentService;

	private ReintegrationDto reintegrationSearchDto;
	private ReintegrationDto reintegrationDto;

	private List<ReintegrationDto> listeReintegrationsDto;

	private CongeAcademiqueDto congeAcademiqueSearchDto;
	private CongeAcademiqueDto congeAcademiqueDto;

	private List<CongeAcademiqueDto> listeCongesAcademiqueDto;

	// Ui

	private Integer sourceReintegration;
	private Integer idCurrAnneeAcademique;
	private Integer idAnneeAcademique;

	private DossierEtudiantDto dossierEtudiantDto;

	private DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto;

	private List<SelectItem> listAnneeAcademique;

	// Historique des situations
	private List<SituationEntiteOccurrenceDto> dossierInscriptionHistory;

	private boolean isView;
	private boolean isCrud;

	private String viewId; // +setter

	private String refCodeCurrEtablissement;

	/**
	 * @author Mounir.MESSAOUDI on : 28 sept. 2014 13:06:18
	 */
	public DossierReintegrationMBean() {
		super();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		reintegrationBundle = facesContext.getApplication().getResourceBundle(
				facesContext,
				CursusConstants.DOSSIER_REINTEGRATION_BUNDLE_MSG_NAME);
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
	}

	/**
	 * Post constructeur
	 * 
	 * @author Mounir.MESSAOUDI on : 28 sept. 2014 13:06:18
	 */
	@PostConstruct
	public void init() {

		try {
			String url = FacesContext.getCurrentInstance().getViewRoot()
					.getViewId();
			viewId = url.substring(url.lastIndexOf("/") + 1,
					url.lastIndexOf("."));

			// Conges academique
			sourceReintegration = 1;

			idCurrAnneeAcademique = sessionBean.getIdAnneeAcademique();
			idAnneeAcademique = idCurrAnneeAcademique;

			this.refCodeCurrEtablissement = sessionBean.getCodeEtablissement();

			loadSearchResults();

			// preparer la liste des annees academique
			listAnneeAcademique = new ArrayList<SelectItem>();
			List<AnneeAcademiqueDto> liste = anneeAcademiqueService.findAll();
			if (liste != null && !liste.isEmpty()) {
				for (AnneeAcademiqueDto anneeAcademiqueDto : liste) {
					SelectItem si = new SelectItem(
							/* anneeAcademiqueDto.getPremiereAnnee() */anneeAcademiqueDto
									.getId(), anneeAcademiqueDto
									.getPremiereAnnee()
									+ "/"
									+ anneeAcademiqueDto.getDeuxiemeAnnee());
					listAnneeAcademique.add(si);
				}
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.info(e.getMessage());
		}

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 31 ao�t 2014 09:42:05
	 */
	public void handleAnneeAcademiqueChange() {
		this.isView = false;
		loadSearchResults();
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 16 oct. 2014 15:05:10
	 */
	public void loadSearchResults() {
		try {
			reintegrationSearchDto = new ReintegrationDto();
			reintegrationSearchDto.setExclusionId(0);
			reintegrationSearchDto.setAnneeAcademiqueId(idAnneeAcademique);
			reintegrationSearchDto
					.setRefCodeTypeExclusion(CursusConstants.EXCLUSION_ANNEE_UNIVERSITAIRE);

			// edit mode ?
			if (viewId.equals("ReintegrationEdit")) {

				reintegrationSearchDto.setResultat(Boolean.FALSE);

				reintegrationSearchDto.setReintegrationValidee(Boolean.FALSE);

			} else {

			}

			this.listeReintegrationsDto = reintegrationService.findAdvanced(
					reintegrationSearchDto, refCodeCurrEtablissement, null);

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	public ResourceBundle getBundleCommon() {
		return bundleCommon;
	}

	public void setBundleCommon(ResourceBundle bundleCommon) {
		this.bundleCommon = bundleCommon;
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	public SituationService getSituationService() {
		return situationService;
	}

	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	public CongeAcademiqueService getCongeAcademiqueService() {
		return congeAcademiqueService;
	}

	public void setCongeAcademiqueService(
			CongeAcademiqueService congeAcademiqueService) {
		this.congeAcademiqueService = congeAcademiqueService;
	}

	public RefDocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(RefDocumentService documentService) {
		this.documentService = documentService;
	}

	public ReintegrationService getReintegrationService() {
		return reintegrationService;
	}

	public void setReintegrationService(
			ReintegrationService reintegrationService) {
		this.reintegrationService = reintegrationService;
	}

	public Integer getIdCurrAnneeAcademique() {
		return idCurrAnneeAcademique;
	}

	public void setIdCurrAnneeAcademique(Integer idCurrAnneeAcademique) {
		this.idCurrAnneeAcademique = idCurrAnneeAcademique;
	}

	public Integer getIdAnneeAcademique() {
		return idAnneeAcademique;
	}

	public void setIdAnneeAcademique(Integer idAnneeAcademique) {
		this.idAnneeAcademique = idAnneeAcademique;
	}

	public DossierEtudiantDto getDossierEtudiantDto() {
		return dossierEtudiantDto;
	}

	public void setDossierEtudiantDto(DossierEtudiantDto dossierEtudiantDto) {
		this.dossierEtudiantDto = dossierEtudiantDto;
	}

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public List<CongeAcademiqueDto> getListeCongesAcademiqueDto() {
		return listeCongesAcademiqueDto;
	}

	public void setListeCongesAcademiqueDto(
			List<CongeAcademiqueDto> listeCongesAcademiqueDto) {
		this.listeCongesAcademiqueDto = listeCongesAcademiqueDto;
	}

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public void setCrud(boolean isCrud) {
		this.isCrud = isCrud;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 2 oct. 2014 07:55:13
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {

		try {
			reintegrationDto = (ReintegrationDto) event.getObject();
			dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
					.findById(reintegrationDto.getDossierInscriptionId());

			isCrud = true;
			isView = true;

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * Handle changement de la source de reintegration
	 * 
	 * @author Mounir.MESSAOUDI on : 25 oct. 2014 15:47:46
	 * @param event
	 */
	public void handleEtablissementChange(AjaxBehaviorEvent event) {
		try {
			isCrud = false;
			isView = false;
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.info(e.getMessage());
		}

	}

	/**
	 * Enregistrer la validation
	 * 
	 * @author Mounir.MESSAOUDI on : 2 oct. 2014 07:55:01
	 */
	public void sendToValidationDossierInscriptionAction() {
		FacesMessage msg = new FacesMessage();
		try {

			dossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto();
			dossierInscriptionAdministrativeDto
					.setAnneeAcademiqueId(idCurrAnneeAcademique);

			dossierInscriptionAdministrativeDto
					.setDossierEtudiantId(reintegrationDto
							.getDossierEtudiantId());

			dossierInscriptionAdministrativeDto
					.setRefCodeDomaine(dossierInscriptionAdministrativeDto
							.getRefCodeDomaine());
			dossierInscriptionAdministrativeDto
					.setCodeDomaine(dossierInscriptionAdministrativeDto
							.getCodeDomaine());
			dossierInscriptionAdministrativeDto
					.setRefCodeFiliere(dossierInscriptionAdministrativeDto
							.getRefCodeFiliere());
			dossierInscriptionAdministrativeDto
					.setCodeFiliere(dossierInscriptionAdministrativeDto
							.getCodeFiliere());
			dossierInscriptionAdministrativeDto
					.setRefCodeSpecialite(dossierInscriptionAdministrativeDto
							.getRefCodeSpecialite());

			dossierInscriptionAdministrativeDto.setDateInscription(new Date());

			dossierInscriptionAdministrativeDto
					.setNiveauId(dossierInscriptionAdministrativeDto
							.getNiveauId());

			dossierInscriptionAdministrativeDto
					.setRefCodeNiveau(dossierInscriptionAdministrativeDto
							.getNiveauCode());

			dossierInscriptionAdministrativeDto
					.setCycleId(dossierInscriptionAdministrativeDto
							.getCycleId());
			dossierInscriptionAdministrativeDto
					.setRefCodeCycle(dossierInscriptionAdministrativeDto
							.getCycleCode());

			// TODO recuperer les codes
			dossierInscriptionAdministrativeDto.setRefCodeStatutEtudiant("");
			dossierInscriptionAdministrativeDto.setRefCodeNatureInscription("");

			dossierInscriptionAdministrativeDto
					.setNumeroInscription(dossierInscriptionAdministrativeDto
							.getRefCodeEtablissement()
							+ reintegrationDto.getDossierEtudiantMatricule()
							+ sessionBean.getDeuxiemeAnneeAcademique());

			// TODO !recuperer le code type de dossier depuis la
			// nomenclautre
			dossierInscriptionAdministrativeDto.setTypeDossier("D001");

			dossierInscriptionAdministrativeDto
					.setOuvertureOffreFormationId(dossierInscriptionAdministrativeDto
							.getOuvertureOffreFormationId());
			dossierInscriptionAdministrativeDto
					.setRefEtablissementId(dossierInscriptionAdministrativeDto
							.getRefEtablissementId());

			dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
					.insertOrUpdate(dossierInscriptionAdministrativeDto);

			// ajouter tout les documents attache au voeux au dossier
			// d'inscription
			String entityName = Reintegration.class.getSimpleName();
			List<RefDocumentDto> listRefDocumentDto = documentService
					.findDocumentsOfEntity(entityName,
							String.valueOf(reintegrationDto.getId()));

			if (listRefDocumentDto != null && !listRefDocumentDto.isEmpty()) {
				for (RefDocumentDto refDocumentDto : listRefDocumentDto) {
					refDocumentDto.setId(null);
					refDocumentDto
							.setNomEntite(DossierInscriptionAdministrative.class
									.getSimpleName());
					refDocumentDto.setIdentifiantEntite(String
							.valueOf(dossierInscriptionAdministrativeDto
									.getId()));
					// refDocumentDto.setIdentifiant();
					documentService.insertOrUpdate(refDocumentDto);
				}
			}

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_enregistrement"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
		isCrud = false;
		loadSearchResults();
	}

	/**
	 * Valider la reintegration
	 * 
	 * @author Mounir.MESSAOUDI on : 1 oct. 2014 17:36:10
	 */
	public void validateReintegrationAction() {
		FacesMessage msg = new FacesMessage();
		try {
			reintegrationDto.setReintegrationValidee(Boolean.TRUE);
			reintegrationDto.setDateValidation(new Date());
			reintegrationService.insertOrUpdate(reintegrationDto);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_validation"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
		isCrud = false;
		loadSearchResults();
	}

	/**
	 * Annuler l'action en cours sur la demande de reintegration
	 * 
	 * @author Mounir.MESSAOUDI on : 2 oct. 2014 07:59:08
	 */
	public void cancelReintegrationAction() {
		isCrud = false;
		isView = false;
	}

	public List<SelectItem> getListAnneeAcademique() {
		return listAnneeAcademique;
	}

	public void setListAnneeAcademique(List<SelectItem> listAnneeAcademique) {
		this.listAnneeAcademique = listAnneeAcademique;
	}

	public DossierInscriptionAdministrativeDto getDossierInscriptionAdministrativeDto() {
		return dossierInscriptionAdministrativeDto;
	}

	public void setDossierInscriptionAdministrativeDto(
			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto) {
		this.dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeDto;
	}

	public ReintegrationDto getReintegrationDto() {
		return reintegrationDto;
	}

	public Integer getSourceReintegration() {
		return sourceReintegration;
	}

	public void setSourceReintegration(Integer sourceReintegration) {
		this.sourceReintegration = sourceReintegration;
	}

	public void setReintegrationDto(ReintegrationDto reintegrationDto) {
		this.reintegrationDto = reintegrationDto;
	}

	public List<ReintegrationDto> getListeReintegrationsDto() {
		return listeReintegrationsDto;
	}

	public void setListeReintegrationsDto(
			List<ReintegrationDto> listeReintegrationsDto) {
		this.listeReintegrationsDto = listeReintegrationsDto;
	}

	public List<SituationEntiteOccurrenceDto> getDossierInscriptionHistory() {
		return dossierInscriptionHistory;
	}
}
