/**
 *  
 * @author Mounir.MESSAOUDI on : 28 sept. 2014 13:05:25
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.reinscription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.VoeuEtudiantChoixDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.VoeuEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.VoeuEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanSessionService;
import dz.gov.mesrs.sii.fve.business.service.impression.ImpressionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.impression.PrintMgrBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;

/**
 * Mangedbean pour gerer les validation des fiches de voeux
 * 
 * @author Mounir.MESSAOUDI on : 1 oct. 2014 10:30:40
 */

@ManagedBean(name = "dossierReinscriptionValidationMBean")
@ViewScoped
public class DossierReinscriptionValidationMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Log log = LogFactory
			.getLog(DossierReinscriptionValidationMBean.class);

	private ResourceBundle reinscriptionBundle;
	private ResourceBundle bundleCommon;

	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;

	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;

	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;

	@ManagedProperty(value = "#{voeuEtudiantService}")
	private VoeuEtudiantService voeuEtudiantService;

	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;

	@ManagedProperty("#{offreFormationService}")
	private OffreFormationService offreFormationService;

	@ManagedProperty("#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;

	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;

	@ManagedProperty("#{printMgrBean}")
	private PrintMgrBean printMgrBean;

	@ManagedProperty("#{impressionService}")
	private ImpressionService impressionService;

	@ManagedProperty(value = "#{bilanSessionService}")
	private BilanSessionService bilanSessionService;

	private BilanSessionDto bilanSessionAnnuelDto;

	private DossierInscriptionAdministrativeDto ancienDossierInscriptionAdministrativeDto;

	// Ui
	private Integer idCurrAnneeAcademique;
	private Integer idAnneeAcademique;

	private List<VoeuEtudiantDto> voeuxEtudiantsDtoSearch;
	private VoeuEtudiantDto voeuEtudiantDto;
	private DossierEtudiantDto dossierEtudiantDto;

	private VoeuEtudiantChoixDto selectedVoeuxEtudiantChoix;

	private List<SelectItem> listAnneeAcademique;

	// Historique des situations
	private List<SituationEntiteOccurrenceDto> ficheVoeuxHistory;

	private boolean isView;
	private boolean isCrud;

	private String viewId; // +setter

	/**
	 * @author Mounir.MESSAOUDI on : 28 sept. 2014 13:06:18
	 */
	public DossierReinscriptionValidationMBean() {
		super();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		reinscriptionBundle = facesContext.getApplication().getResourceBundle(
				facesContext,
				CursusConstants.DOSSIER_REINSCRIPTION_BUNDLE_MSG_NAME);
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		// TODO Auto-generated constructor stub
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

			idCurrAnneeAcademique = sessionBean.getIdAnneeAcademique();
			idAnneeAcademique = idCurrAnneeAcademique;

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
			log.error(e.getMessage());
		}

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 31 août 2014 09:42:05
	 */
	public void handleAnneeAcademiqueChange() {
		this.isView = false;
		loadSearchResults();
	}

	public void loadSearchResults() {
		try {

			// recuperer tout les fiches de voeux du domaine de l'equipe
			// pedagogique
			VoeuEtudiantDto searchBo = new VoeuEtudiantDto();
			searchBo.setAnneeAcademiqueId(idAnneeAcademique);

			List<SituationEntiteDto> situationDtos = new ArrayList<>();

			SituationEntiteDto situationValideeEtudiant = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
							UtilConstants.FICHE_VOEUX_REINSCRIPTION_SITUTAION_VALIDEE_ETUDIANT_CODE);

			SituationEntiteDto situationEncourTraitEP = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
							UtilConstants.FICHE_VOEUX_REINSCRIPTION_SITUTAION_ENCOURS_TRAITMENT_EQUIPE_PEDAGOGIQUE_CODE);

			situationDtos.add(situationValideeEtudiant);
			situationDtos.add(situationEncourTraitEP);

			// edit mode ?
			if (viewId.equals("ValidationFichesVoeuxEdit")) {

			} else {

				SituationEntiteDto situationValEP = situationService
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
								UtilConstants.FICHE_VOEUX_REINSCRIPTION_SITUTAION_VALIDEE_EQUIPE_PEDAGOGIQUE_CODE);
				situationDtos.add(situationValEP);

				SituationEntiteDto situationEncourTraitR = situationService
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
								UtilConstants.FICHE_VOEUX_REINSCRIPTION_SITUTAION_ENCOURS_TRAITMENT_REINSCRIPTION_CODE);
				situationDtos.add(situationEncourTraitR);

				SituationEntiteDto situationTrait = situationService
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
								UtilConstants.FICHE_VOEUX_REINSCRIPTION_SITUTAION_TRAITEE_CODE);
				situationDtos.add(situationTrait);
			}
			voeuxEtudiantsDtoSearch = voeuEtudiantService.findAdvanced(
					searchBo, situationDtos);

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
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

	public VoeuEtudiantService getVoeuEtudiantService() {
		return voeuEtudiantService;
	}

	public BilanSessionService getBilanSessionService() {
		return bilanSessionService;
	}

	public void setBilanSessionService(BilanSessionService bilanSessionService) {
		this.bilanSessionService = bilanSessionService;
	}

	public void setVoeuEtudiantService(VoeuEtudiantService voeuEtudiantService) {
		this.voeuEtudiantService = voeuEtudiantService;
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

	public PrintMgrBean getPrintMgrBean() {
		return printMgrBean;
	}

	public void setPrintMgrBean(PrintMgrBean printMgrBean) {
		this.printMgrBean = printMgrBean;
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

	public ImpressionService getImpressionService() {
		return impressionService;
	}

	public void setImpressionService(ImpressionService impressionService) {
		this.impressionService = impressionService;
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

	public List<VoeuEtudiantDto> getVoeuxEtudiantsDtoSearch() {
		return voeuxEtudiantsDtoSearch;
	}

	public void setVoeuxEtudiantsDtoSearch(
			List<VoeuEtudiantDto> voeuxEtudiantsDtoSearch) {
		this.voeuxEtudiantsDtoSearch = voeuxEtudiantsDtoSearch;
	}

	public VoeuEtudiantDto getVoeuEtudiantDto() {
		return voeuEtudiantDto;
	}

	public void setVoeuEtudiantDto(VoeuEtudiantDto voeuEtudiantDto) {
		this.voeuEtudiantDto = voeuEtudiantDto;
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
			voeuEtudiantDto = (VoeuEtudiantDto) event.getObject();

			isCrud = true;
			isView = true;
			if (voeuEtudiantDto.getIdAncienDossierInsAdmin() != null) {
				// ancien dossier d'inscription administrative
				ancienDossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
						.findById(voeuEtudiantDto.getIdAncienDossierInsAdmin());

				// bilan annuel final
				bilanSessionAnnuelDto = bilanSessionService
						.findBilanFinalByIdDossierInscrAdmin(voeuEtudiantDto
								.getIdAncienDossierInsAdmin());
			}

			List<VoeuEtudiantChoixDto> o = voeuEtudiantDto
					.getVoeuxEtudiantsChoixDto();

			if (o != null && !o.isEmpty())
				for (VoeuEtudiantChoixDto voeuEtudiantChoixDto : o) {
					if (voeuEtudiantChoixDto.getEstRetenu() != null
							&& voeuEtudiantChoixDto.getEstRetenu()) {
						selectedVoeuxEtudiantChoix = voeuEtudiantChoixDto;
						break;
					}
				}

			// historique des situations
			ficheVoeuxHistory = situationService.getEntityOccurrenceHistory(
					UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
					voeuEtudiantDto.getId());

			isCrud = true;
			isView = true;

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	public VoeuEtudiantChoixDto getSelectedVoeuxEtudiantChoix() {
		return selectedVoeuxEtudiantChoix;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 9 oct. 2014 15:23:26
	 * @param selectedVoeuxEtudiantChoix
	 */
	public void setSelectedVoeuxEtudiantChoix(
			VoeuEtudiantChoixDto selectedVoeuxEtudiantChoix) {
		if (this.selectedVoeuxEtudiantChoix != null) {
			this.selectedVoeuxEtudiantChoix.setEstRetenu(false);
		}

		this.selectedVoeuxEtudiantChoix = selectedVoeuxEtudiantChoix;
		if (this.selectedVoeuxEtudiantChoix != null)
			this.selectedVoeuxEtudiantChoix.setEstRetenu(true);

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 7 oct. 2014 10:02:59
	 * @param event
	 */
	public void onRowSelectChoix(SelectEvent event) {
		try {
			if (this.selectedVoeuxEtudiantChoix != null)
				this.selectedVoeuxEtudiantChoix.setEstRetenu(false);
			VoeuEtudiantChoixDto v = (VoeuEtudiantChoixDto) event.getObject();
			v.setEstRetenu(true);
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * Enregistrer la validation
	 * 
	 * @author Mounir.MESSAOUDI on : 2 oct. 2014 07:55:01
	 */
	public void saveFicheVoeuxAction() {
		FacesMessage msg = new FacesMessage();
		try {
			voeuEtudiantDto
					.setSituationId(situationService
							.findBySituationEntiteByCodeSituation(
									UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
									UtilConstants.FICHE_VOEUX_REINSCRIPTION_SITUTAION_ENCOURS_TRAITMENT_EQUIPE_PEDAGOGIQUE_CODE)
							.getId());

			selectedVoeuxEtudiantChoix.setEstRetenu(true);
			voeuEtudiantDto
					.setOuvertureOffreFormation(selectedVoeuxEtudiantChoix
							.getOuvertureOffreFormation());
			voeuEtudiantDto.setDomaine(selectedVoeuxEtudiantChoix.getDomaine());
			voeuEtudiantDto.setFiliere(selectedVoeuxEtudiantChoix.getFiliere());
			voeuEtudiantDto.setSpecialite(selectedVoeuxEtudiantChoix
					.getSpecialite());

			voeuEtudiantDto = voeuEtudiantService
					.insertOrUpdate(voeuEtudiantDto);

			// historique des situations
			ficheVoeuxHistory = situationService.getEntityOccurrenceHistory(
					UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
					voeuEtudiantDto.getId());

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_enregistrement"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
		// isCrud = false;
		loadSearchResults();
	}

	/**
	 * Valider la fiche de voeux
	 * 
	 * @author Mounir.MESSAOUDI on : 1 oct. 2014 17:36:10
	 */
	public void validateFicheVoeuxAction() {
		FacesMessage msg = new FacesMessage();
		try {
			voeuEtudiantDto
					.setSituationId(situationService
							.findBySituationEntiteByCodeSituation(
									UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
									UtilConstants.FICHE_VOEUX_REINSCRIPTION_SITUTAION_VALIDEE_EQUIPE_PEDAGOGIQUE_CODE)
							.getId());

			selectedVoeuxEtudiantChoix.setEstRetenu(true);
			voeuEtudiantDto
					.setOuvertureOffreFormation(selectedVoeuxEtudiantChoix
							.getOuvertureOffreFormation());
			voeuEtudiantDto.setDomaine(selectedVoeuxEtudiantChoix.getDomaine());
			voeuEtudiantDto.setFiliere(selectedVoeuxEtudiantChoix.getFiliere());
			voeuEtudiantDto.setSpecialite(selectedVoeuxEtudiantChoix
					.getSpecialite());

			voeuEtudiantService.insertOrUpdate(voeuEtudiantDto);

			// historique des situations
			ficheVoeuxHistory = situationService.getEntityOccurrenceHistory(
					UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
					voeuEtudiantDto.getId());

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
	 * Generer l'autorisation d'inscription/reinscription pour l'etudiant
	 * selectionnee
	 * 
	 * @author Mounir.MESSAOUDI on : 12 oct. 2014 12:24:09
	 */
	public void generateAutorisationInscriptionAction() {
		try {

			Collection<VoeuEtudiantDto> etudiants = new ArrayList<>();

			etudiants.add(voeuEtudiantDto);

			String name = "attestation_reinscription_"
					+ voeuEtudiantDto.getDossierEtudiantMatricule();

			FacesContext facesContext = FacesContext.getCurrentInstance();

			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext
					.getExternalContext().getRealPath("/WEB-INF/classes")
					+ "/jasper/autorisation_inscription.jrxml";

			// String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext
			// .getExternalContext().getRealPath("WEB-INF/classes")
			// + "/classes/jasper/attestation_reinscription.jrxml";
			Map<String, Object> params = new HashMap<String, Object>();

			params.put("IMG_PAPS", facesContext.getExternalContext()
					.getRealPath("images") + "/logopaps.png");

			params.put("IMG_LOGO",
					facesContext.getExternalContext().getRealPath("images")
							+ "/logo" + sessionBean.getCodeEtablissement()
							+ ".png");

			byte[] bytes = impressionService.viewPDFWithDataSource(
					RESOURCE_PATH_TO_INPUT_FILE_JASPER, params, etudiants);

			printMgrBean.imprimer(bytes, name, "pdf");
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/*
	 * public void generateaAutorisationInscriptionAction() { try {
	 * 
	 * Collection<VoeuEtudiantDto> etudiants = new ArrayList<>();
	 * 
	 * etudiants.add(voeuEtudiantDto);
	 * 
	 * String name = "attestation_reinscription_" +
	 * voeuEtudiantDto.getDossierEtudiantMatricule();
	 * 
	 * FacesContext facesContext = FacesContext.getCurrentInstance();
	 * 
	 * String RESOURCE_PATH_TO_INPUT_FILE_JASPER =
	 * facesContext.getExternalContext() .getRealPath("/WEB-INF/classes") +
	 * "/jasper/attestation_reinscription.jrxml";
	 * 
	 * // String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext //
	 * .getExternalContext().getRealPath("WEB-INF/classes") // +
	 * "/classes/jasper/attestation_reinscription.jrxml"; Map<String, Object>
	 * params = new HashMap<String, Object>();
	 * 
	 * params.put("IMG_PAPS", facesContext.getExternalContext()
	 * .getRealPath("images") + "/logopaps.png");
	 * 
	 * params.put("IMG_LOGO",
	 * facesContext.getExternalContext().getRealPath("images") + "/logo" +
	 * sessionBean.getCodeEtablissement() + ".png");
	 * 
	 * byte[] bytes = impressionService.viewPDFWithDataSource(
	 * RESOURCE_PATH_TO_INPUT_FILE_JASPER, params, etudiants);
	 * 
	 * printMgrBean.imprimer(bytes, name, "pdf"); } catch (Exception e) { //
	 * TODO: handle exception } }
	 */

	/**
	 * Annuler l'action en cours sur la fiche de voeux
	 * 
	 * @author Mounir.MESSAOUDI on : 2 oct. 2014 07:59:08
	 */
	public void cancelFicheVoeuxAction() {
		selectedVoeuxEtudiantChoix = null;
		voeuEtudiantDto = null;
		isCrud = false;
		isView = false;

	}

	public List<SituationEntiteOccurrenceDto> getFicheVoeuxHistory() {
		return ficheVoeuxHistory;
	}

	public List<SelectItem> getListAnneeAcademique() {
		return listAnneeAcademique;
	}

	public void setListAnneeAcademique(List<SelectItem> listAnneeAcademique) {
		this.listAnneeAcademique = listAnneeAcademique;
	}

	public BilanSessionDto getBilanSessionAnnuelDto() {
		return bilanSessionAnnuelDto;
	}

	public void setBilanSessionAnnuelDto(BilanSessionDto bilanSessionAnnuelDto) {
		this.bilanSessionAnnuelDto = bilanSessionAnnuelDto;
	}

	public DossierInscriptionAdministrativeDto getAncienDossierInscriptionAdministrativeDto() {
		return ancienDossierInscriptionAdministrativeDto;
	}

	public void setAncienDossierInscriptionAdministrativeDto(
			DossierInscriptionAdministrativeDto ancienDossierInscriptionAdministrativeDto) {
		this.ancienDossierInscriptionAdministrativeDto = ancienDossierInscriptionAdministrativeDto;
	}

}
