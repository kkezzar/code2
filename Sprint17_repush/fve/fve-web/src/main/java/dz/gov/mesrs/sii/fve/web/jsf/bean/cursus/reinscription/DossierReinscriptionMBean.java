/**
 *  
 * @author Mounir.MESSAOUDI on : 28 sept. 2014 13:05:25
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.reinscription;

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
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.VoeuEtudiant;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
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
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefDocumentService;

/**
 * Mangedbean pour gerer les validation des fiches de voeux
 * 
 * @author Mounir.MESSAOUDI on : 1 oct. 2014 10:30:40
 */

@ManagedBean(name = "dossierReinscriptionMBean")
@ViewScoped
public class DossierReinscriptionMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Log log = LogFactory
			.getLog(DossierReinscriptionMBean.class);

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

	@ManagedProperty("#{impressionService}")
	private ImpressionService impressionService;

	@ManagedProperty("#{refDocumentServiceImpl}")
	private RefDocumentService documentService;

	@ManagedProperty(value = "#{bilanSessionService}")
	private BilanSessionService bilanSessionService;

	private BilanSessionDto bilanSessionAnnuelDto;

	// Ui
	private Integer idCurrAnneeAcademique;
	private Integer idAnneeAcademique;

	private List<VoeuEtudiantDto> voeuxEtudiantsDtoSearch;
	private VoeuEtudiantDto voeuEtudiantDto;
	private DossierEtudiantDto dossierEtudiantDto;

	private DossierInscriptionAdministrativeDto ancienDossierInscriptionAdministrativeDto;
	private DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto;

	private List<SelectItem> listAnneeAcademique;

	// Historique des situations
	private List<SituationEntiteOccurrenceDto> ficheVoeuxHistory;

	private boolean isView;
	private boolean isCrud;

	private String viewId; // +setter

	/**
	 * @author Mounir.MESSAOUDI on : 28 sept. 2014 13:06:18
	 */
	public DossierReinscriptionMBean() {
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
			// recuperer tout les fiches de voeux du domaine de l'equipe
			// pedagogique
			VoeuEtudiantDto searchBo = new VoeuEtudiantDto();
			searchBo.setAnneeAcademiqueId(idAnneeAcademique);

			List<SituationEntiteDto> situationDtos = new ArrayList<>();

			SituationEntiteDto situationValideeEquipePedagogique = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
							UtilConstants.FICHE_VOEUX_REINSCRIPTION_SITUTAION_VALIDEE_EQUIPE_PEDAGOGIQUE_CODE);

			SituationEntiteDto situationEncourTraitR = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
							UtilConstants.FICHE_VOEUX_REINSCRIPTION_SITUTAION_ENCOURS_TRAITMENT_REINSCRIPTION_CODE);

			situationDtos.add(situationValideeEquipePedagogique);
			situationDtos.add(situationEncourTraitR);

			// edit mode ?
			if (viewId.equals("ReinscriptionEdit")) {

			} else {
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

	public RefDocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(RefDocumentService documentService) {
		this.documentService = documentService;
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

			// ancien dossier d'inscription administrative
			if (voeuEtudiantDto.getIdAncienDossierInsAdmin() != null) {

				// ancien dossier d'inscription administrative
				ancienDossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
						.findById(voeuEtudiantDto.getIdAncienDossierInsAdmin());

				// bilan annuel final
				bilanSessionAnnuelDto = bilanSessionService
						.findBilanFinalByIdDossierInscrAdmin(voeuEtudiantDto
								.getIdAncienDossierInsAdmin());
			}

			// Nouveau dossier d'inscription administrative
			if (voeuEtudiantDto.getIdNouveauDossierInsAdmin() != null) {
				dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
						.findById(voeuEtudiantDto.getIdNouveauDossierInsAdmin());
			}

			// historique des situations
			ficheVoeuxHistory = situationService.getEntityOccurrenceHistory(
					UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
					voeuEtudiantDto.getId());

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
									UtilConstants.FICHE_VOEUX_REINSCRIPTION_SITUTAION_ENCOURS_TRAITMENT_REINSCRIPTION_CODE)
							.getId());

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
	 * Generer le dossier d'inscription administrarive pour l'etudiant en cours
	 * 
	 * @author Mounir.MESSAOUDI on : 15 oct. 2014 11:47:05
	 */
	public void generateDossierInscriptionAction() {
		FacesMessage msg = new FacesMessage();
		try {

			if (voeuEtudiantDto.getIdNouveauDossierInsAdmin() == null) {
				dossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto();
				dossierInscriptionAdministrativeDto
						.setAnneeAcademiqueId(idCurrAnneeAcademique);

				dossierInscriptionAdministrativeDto
						.setDossierEtudiantId(voeuEtudiantDto
								.getDossierEtudiantId());

				dossierInscriptionAdministrativeDto
						.setRefCodeDomaine(voeuEtudiantDto.getDomaine()
								.getIdentifiant());
				dossierInscriptionAdministrativeDto
						.setCodeDomaine(voeuEtudiantDto.getDomaine()
								.getIdentifiant());
				dossierInscriptionAdministrativeDto
						.setRefCodeFiliere(voeuEtudiantDto.getFiliere()
								.getIdentifiant());
				dossierInscriptionAdministrativeDto
						.setCodeFiliere(voeuEtudiantDto.getFiliere()
								.getIdentifiant());
				dossierInscriptionAdministrativeDto
						.setRefCodeSpecialite(voeuEtudiantDto.getSpecialite()
								.getIdentifiant());

				dossierInscriptionAdministrativeDto
						.setDateInscription(new Date());

				dossierInscriptionAdministrativeDto.setNiveauId(voeuEtudiantDto
						.getNiveau().getId());

				dossierInscriptionAdministrativeDto
						.setRefCodeNiveau(voeuEtudiantDto.getNiveau().getCode());
				dossierInscriptionAdministrativeDto.setCycleId(voeuEtudiantDto
						.getCycle().getId());

				dossierInscriptionAdministrativeDto
						.setRefCodeCycle(voeuEtudiantDto.getCycle().getCode());

				// TODO recuperer les codes
				dossierInscriptionAdministrativeDto
						.setRefCodeStatutEtudiant("");
				dossierInscriptionAdministrativeDto
						.setRefCodeNatureInscription("");
				dossierInscriptionAdministrativeDto
						.setNumeroInscription(voeuEtudiantDto
								.getEtablissement().getIdentifiant()
								+ voeuEtudiantDto.getDossierEtudiantMatricule()
								+ voeuEtudiantDto
										.getAnneeAcademiqueDeuxiemeAnnee());

				dossierInscriptionAdministrativeDto
						.setTypeDossier(voeuEtudiantDto
								.getTypeReinscriptionCode());

				dossierInscriptionAdministrativeDto
						.setOuvertureOffreFormationId(voeuEtudiantDto
								.getOuvertureOffreFormation().getId());
				dossierInscriptionAdministrativeDto
						.setRefEtablissementId(voeuEtudiantDto
								.getEtablissement().getId());

				dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
						.insertOrUpdate(dossierInscriptionAdministrativeDto);
				// Enregistrer l'historique de changement de situation
				SituationEntiteOccurrenceDto s = new SituationEntiteOccurrenceDto();
				s.setDateSituation(new Date());
				s.setIdOccurrence(dossierInscriptionAdministrativeDto.getId());
				s.setIdSituation(dossierInscriptionAdministrativeDto
						.getSituationId());
				situationService.saveSituationOccurrence(s);

				// ajouter tout les documents attache au voeux au dossier
				// d'inscription
				String entityName = VoeuEtudiant.class.getSimpleName();
				List<RefDocumentDto> listRefDocumentDto = documentService
						.findDocumentsOfEntity(entityName,
								String.valueOf(voeuEtudiantDto.getId()));

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

				// mettre a jour la fiche de voeux
				voeuEtudiantDto
						.setIdNouveauDossierInsAdmin(dossierInscriptionAdministrativeDto
								.getId());
				voeuEtudiantService.insertOrUpdate(voeuEtudiantDto);

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ bundleCommon.getString("msg_success_generation"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				// dossier d'inscription deja cree
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generer un nouveau dossier d'inscription et soumettre a la validation
	 * 
	 * @author Mounir.MESSAOUDI on : 1 oct. 2014 17:36:10
	 */
	public void sendToValidationAction() {
		FacesMessage msg = new FacesMessage();
		try {

			if (voeuEtudiantDto.getIdNouveauDossierInsAdmin() == null) {
				dossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto();
				dossierInscriptionAdministrativeDto
						.setAnneeAcademiqueId(idCurrAnneeAcademique);

				dossierInscriptionAdministrativeDto
						.setDossierEtudiantId(voeuEtudiantDto
								.getDossierEtudiantId());

				dossierInscriptionAdministrativeDto
						.setRefCodeDomaine(voeuEtudiantDto.getDomaine()
								.getIdentifiant());
				if (voeuEtudiantDto.getDomaine() != null)
					dossierInscriptionAdministrativeDto
							.setCodeDomaine(voeuEtudiantDto.getDomaine()
									.getIdentifiant());

				if (voeuEtudiantDto.getFiliere() != null)
					dossierInscriptionAdministrativeDto
							.setRefCodeFiliere(voeuEtudiantDto.getFiliere()
									.getIdentifiant());

				if (voeuEtudiantDto.getFiliere() != null)
					dossierInscriptionAdministrativeDto
							.setCodeFiliere(voeuEtudiantDto.getFiliere()
									.getIdentifiant());

				if (voeuEtudiantDto.getSpecialite() != null)
					dossierInscriptionAdministrativeDto
							.setRefCodeSpecialite(voeuEtudiantDto
									.getSpecialite().getIdentifiant());

				dossierInscriptionAdministrativeDto
						.setDateInscription(new Date());

				dossierInscriptionAdministrativeDto.setNiveauId(voeuEtudiantDto
						.getNiveau().getId());

				dossierInscriptionAdministrativeDto
						.setRefCodeNiveau(voeuEtudiantDto.getNiveau().getCode());
				dossierInscriptionAdministrativeDto.setCycleId(voeuEtudiantDto
						.getCycle().getId());

				dossierInscriptionAdministrativeDto
						.setRefCodeCycle(voeuEtudiantDto.getCycle().getCode());

				// TODO recuperer les codes
				dossierInscriptionAdministrativeDto
						.setRefCodeStatutEtudiant("");
				dossierInscriptionAdministrativeDto
						.setRefCodeNatureInscription("");
				dossierInscriptionAdministrativeDto
						.setNumeroInscription(voeuEtudiantDto
								.getEtablissement().getIdentifiant()
								+ voeuEtudiantDto.getDossierEtudiantMatricule()
								+ voeuEtudiantDto
										.getAnneeAcademiqueDeuxiemeAnnee());

				dossierInscriptionAdministrativeDto
						.setTypeDossier(voeuEtudiantDto
								.getTypeReinscriptionCode());

				dossierInscriptionAdministrativeDto
						.setOuvertureOffreFormationId(voeuEtudiantDto
								.getOuvertureOffreFormation().getId());
				dossierInscriptionAdministrativeDto
						.setRefEtablissementId(voeuEtudiantDto
								.getEtablissement().getId());
				dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
						.insertOrUpdate(dossierInscriptionAdministrativeDto);
				// Enregistrer l'historique de changement de situation
				SituationEntiteOccurrenceDto s = new SituationEntiteOccurrenceDto();
				s.setDateSituation(new Date());
				s.setIdOccurrence(dossierInscriptionAdministrativeDto.getId());
				s.setIdSituation(dossierInscriptionAdministrativeDto
						.getSituationId());
				situationService.saveSituationOccurrence(s);

				// ajouter tout les documents attache au voeux au dossier
				// d'inscription
				String entityName = VoeuEtudiant.class.getSimpleName();
				List<RefDocumentDto> listRefDocumentDto = documentService
						.findDocumentsOfEntity(entityName,
								String.valueOf(voeuEtudiantDto.getId()));

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

				// mettre a jour la fiche de voeux
				voeuEtudiantDto
						.setIdNouveauDossierInsAdmin(dossierInscriptionAdministrativeDto
								.getId());
				// voeuEtudiantService.insertOrUpdate(voeuEtudiantDto);

				voeuEtudiantDto
						.setSituationId(situationService
								.findBySituationEntiteByCodeSituation(
										UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
										UtilConstants.FICHE_VOEUX_REINSCRIPTION_SITUTAION_TRAITEE_CODE)
								.getId());

				voeuEtudiantService.insertOrUpdate(voeuEtudiantDto);

				// historique des situations
				// ficheVoeuxHistory = situationService
				// .getEntityOccurrenceHistory(
				// UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
				// voeuEtudiantDto.getId());

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ bundleCommon.getString("msg_success_validation"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
		isCrud = false;
		loadSearchResults();
	}

	/**
	 * Annuler l'action en cours sur la fiche de voeux
	 * 
	 * @author Mounir.MESSAOUDI on : 2 oct. 2014 07:59:08
	 */
	public void cancelFicheVoeuxAction() {
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

	public DossierInscriptionAdministrativeDto getDossierInscriptionAdministrativeDto() {
		return dossierInscriptionAdministrativeDto;
	}

	public void setDossierInscriptionAdministrativeDto(
			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto) {
		this.dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeDto;
	}

	public DossierInscriptionAdministrativeDto getAncienDossierInscriptionAdministrativeDto() {
		return ancienDossierInscriptionAdministrativeDto;
	}

	public void setAncienDossierInscriptionAdministrativeDto(
			DossierInscriptionAdministrativeDto ancienDossierInscriptionAdministrativeDto) {
		this.ancienDossierInscriptionAdministrativeDto = ancienDossierInscriptionAdministrativeDto;
	}

	public BilanSessionService getBilanSessionService() {
		return bilanSessionService;
	}

	public void setBilanSessionService(BilanSessionService bilanSessionService) {
		this.bilanSessionService = bilanSessionService;
	}

	public BilanSessionDto getBilanSessionAnnuelDto() {
		return bilanSessionAnnuelDto;
	}

	public void setBilanSessionAnnuelDto(BilanSessionDto bilanSessionAnnuelDto) {
		this.bilanSessionAnnuelDto = bilanSessionAnnuelDto;
	}
}
