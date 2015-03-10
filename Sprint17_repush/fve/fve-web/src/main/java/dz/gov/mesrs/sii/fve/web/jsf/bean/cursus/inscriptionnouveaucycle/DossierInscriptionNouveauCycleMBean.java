/**
 *  
 * @author Mounir.MESSAOUDI on : 28 sept. 2014 13:05:25
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.inscriptionnouveaucycle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import dz.gov.mesrs.sii.commons.data.model.fve.concours.DossierCandidat;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.ConcoursDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.DossierCandidatDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.concours.ConcoursService;
import dz.gov.mesrs.sii.fve.business.service.concours.DossierCandidatService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.impression.ImpressionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefDocumentService;

/**
 * Mangedbean pour gerer les demandes d'inscriptions dans un nouveau cycle
 * 
 * @author Mounir.MESSAOUDI on : 1 oct. 2014 10:30:40
 */

@ManagedBean(name = "dossierInscriptionNouveauCycleMBean")
@ViewScoped
public class DossierInscriptionNouveauCycleMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Log log = LogFactory
			.getLog(DossierInscriptionNouveauCycleMBean.class);

	private ResourceBundle inscriptionNouveayCycleBundle;
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

	@ManagedProperty("#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;

	@ManagedProperty("#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;

	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;

	@ManagedProperty("#{impressionService}")
	private ImpressionService impressionService;

	@ManagedProperty("#{refDocumentServiceImpl}")
	private RefDocumentService documentService;

	@ManagedProperty("#{concoursServiceImpl}")
	private ConcoursService concoursService;

	@ManagedProperty("#{dossierCandidatServiceImpl}")
	private DossierCandidatService dossierCandidatService;

	@ManagedProperty("#{niveauService}")
	private NiveauService niveauService;

	// Ui
	private Integer idCurrAnneeAcademique;
	private Integer idAnneeAcademique;

	private DossierEtudiantDto dossierEtudiantDto;

	private DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto;

	private List<SelectItem> listAnneeAcademique;

	// Historique des situations
	private List<SituationEntiteOccurrenceDto> dossierInscriptionHistory;

	private List<ConcoursDto> listeConcoursDto;
	private List<DossierCandidatDto> listeDossierCondidatDto;

	private Long idSelectedConcours;
	private List<SelectItem> listeItemsConcours;

	private ConcoursDto concoursDto;
	private DossierCandidatDto dossierCandidatDto;

	private boolean isView;
	private boolean isCrud;

	private String viewId; // +setter

	// recherche
	private String sNIN;
	private String sNom;
	private String sPrenom;

	/**
	 * @author Mounir.MESSAOUDI on : 28 sept. 2014 13:06:18
	 */
	public DossierInscriptionNouveauCycleMBean() {
		super();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		inscriptionNouveayCycleBundle = facesContext
				.getApplication()
				.getResourceBundle(
						facesContext,
						CursusConstants.DOSSIER_INSCRIPTION_NOUVEAU_CYCLE_BUNDLE_MSG_NAME);
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

			if (viewId.equals("InscriptionsNouveauCycle")) {
				// preparer la liste des annees academique
				listAnneeAcademique = new ArrayList<SelectItem>();
				List<AnneeAcademiqueDto> liste = anneeAcademiqueService
						.findAll();
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
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 31 ao�t 2014 09:42:05
	 */
	public void handleConcoursChange() {
		try {
			// ConcoursDto c = new ConcoursDto();
			// c.setId(this.idSelectedConcours);

			if (idSelectedConcours != null) {
				concoursDto = concoursService.findById(this.idSelectedConcours);

				// concoursDto = listeConcoursDto.get(this.idSelectedConcours
				// .intValue());

				if (concoursDto != null) {

					DossierCandidatDto searchDossiersCondidatsDto = new DossierCandidatDto();
					// // TODO correction dans les DTO !!
					// searchDossiersCondidatsDto.setConcoursId(concoursDto
					// .getId().intValue());
					// searchDossiersCondidatsDto.setAdmis(Boolean.TRUE);
					// d.setDesistement(Boolean.FALSE);
					// etablissement en cours
					// d.setEtablissementAdmissionId(sessionBean.getIdEtablissement());

					listeDossierCondidatDto = dossierCandidatService
							.findAll(searchDossiersCondidatsDto);

					this.isView = true;
					this.isCrud = true;
					return;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		this.isView = false;
		this.isCrud = false;

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 31 ao�t 2014 09:42:05
	 */
	public void handleAnneeAcademiqueChange() {
		this.isView = false;
		this.isCrud = false;
		loadSearchResults();
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 16 oct. 2014 15:05:10
	 */
	public void loadSearchResults() {
		try {

			ConcoursDto searchConcoursDto = new ConcoursDto();
			searchConcoursDto.setAnneeAcademiqueId(idAnneeAcademique);
			// searchConcoursDto.setEtablissementId(sessionBean.getIdEtablissement());
			// TODO un des etablissements d'admission = etablissement en cours ?

			listeConcoursDto = concoursService.findAll(searchConcoursDto);

			// preparer la liste concours
			listeItemsConcours = new ArrayList<SelectItem>();
			if (listeConcoursDto != null && !listeConcoursDto.isEmpty()) {

				int i = 0;
				for (ConcoursDto concoursDto : listeConcoursDto) {
					// SelectItem si = new SelectItem(concoursDto.getId(),
					// concoursDto.getIntituleLatin());

					SelectItem si = new SelectItem(i,
							concoursDto.getIntituleLatin());
					listeItemsConcours.add(si);
					i++;
				}
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	public ResourceBundle getInscriptionNouveayCycleBundle() {
		return inscriptionNouveayCycleBundle;
	}

	public void setInscriptionNouveayCycleBundle(
			ResourceBundle inscriptionNouveayCycleBundle) {
		this.inscriptionNouveayCycleBundle = inscriptionNouveayCycleBundle;
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

	public NiveauService getNiveauService() {
		return niveauService;
	}

	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
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

	public ConcoursService getConcoursService() {
		return concoursService;
	}

	public void setConcoursService(ConcoursService concoursService) {
		this.concoursService = concoursService;
	}

	public DossierCandidatService getDossierCandidatService() {
		return dossierCandidatService;
	}

	public void setDossierCandidatService(
			DossierCandidatService dossierCandidatService) {
		this.dossierCandidatService = dossierCandidatService;
	}

	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	public List<ConcoursDto> getListeConcoursDto() {
		return listeConcoursDto;
	}

	public List<DossierCandidatDto> getListeDossierCondidatDto() {
		return listeDossierCondidatDto;
	}

	public void setListeDossierCondidatDto(
			List<DossierCandidatDto> listeDossierCondidatDto) {
		this.listeDossierCondidatDto = listeDossierCondidatDto;
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

	public String getsNIN() {
		return sNIN;
	}

	public void setsNIN(String sNIN) {
		this.sNIN = sNIN;
	}

	public String getsNom() {
		return sNom;
	}

	public void setsNom(String sNom) {
		this.sNom = sNom;
	}

	public String getsPrenom() {
		return sPrenom;
	}

	public void setsPrenom(String sPrenom) {
		this.sPrenom = sPrenom;
	}

	/**
	 * Evenement de selection d'un concours depuis le tableau
	 * 
	 * @author Mounir.MESSAOUDI on : 2 nov. 2014 08:33:42
	 * @param event
	 */
	public void onRowSelectConcours(SelectEvent event) {
		try {
			concoursDto = (ConcoursDto) event.getObject();
			if (concoursDto != null) {
				DossierCandidatDto searchDossiersCondidatsDto = new DossierCandidatDto();
				// TODO correction dans les DTO !!
				searchDossiersCondidatsDto.setConcoursId(concoursDto.getId());
				searchDossiersCondidatsDto.setAdmis(Boolean.TRUE);
				searchDossiersCondidatsDto.setDesistement(Boolean.FALSE);
				// TODO etablissement en cours
				// d.setEtablissementAdmissionId(sessionBean.getIdEtablissement());

				listeDossierCondidatDto = dossierCandidatService
						.findAllAdmisByIdConcours(concoursDto.getId());

				this.isView = true;
				this.isCrud = true;
				return;
			}
		} catch (Exception e) {

		}
		this.isView = false;
		this.isCrud = false;
	}

	/**
	 * Action boutton retour
	 * 
	 * @author Mounir.MESSAOUDI on : 2 nov. 2014 08:48:44
	 */
	public void cancelConcoursAction() {
		concoursDto = null;
		this.isView = false;
		this.isCrud = false;
	}

	/**
	 * Evenement de selection d'un etudiant parmis la liste des etudiants admis
	 * 
	 * @author Mounir.MESSAOUDI on : 2 oct. 2014 07:55:13
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			dossierCandidatDto = (DossierCandidatDto) event.getObject();
			if (dossierCandidatDto != null) {

				// recuperer le dernier dossier Etudiant enregistre pour
				// l'individu
				dossierEtudiantDto = dossierEtudiantService
						.findLastByIdIndividu(dossierCandidatDto.getIndividu()
								.getId());

				if (dossierEtudiantDto != null) {

					// dossierInscriptionAdministrativeDto =
					// dossierInscriptionAdministrativeService
					// .findPresentDossierInscriptionForEtudiant(dossierCandidatDto.
					// .getId());

					dossierInscriptionAdministrativeDto = dossierCandidatDto
							.getDossierInscriptionAdministrative();

					OuvertureOffreFormationDto oof = concoursDto
							.getOuvertureOffreFormationDto();

					if (dossierInscriptionAdministrativeDto == null) {
						dossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto();

						dossierInscriptionHistory = new ArrayList<>();

						// etablissement
						dossierInscriptionAdministrativeDto
								.setRefEtablissementId(sessionBean
										.getIdEtablissement());

						// l'annee academique
						dossierInscriptionAdministrativeDto
								.setAnneeAcademiqueId(idCurrAnneeAcademique);
						dossierInscriptionAdministrativeDto
								.setAnneeAcademiqueCode(oof
										.getAnneeAcademiquePremiereAnnee()
										+ "/"
										+ oof.getAnneeAcademiqueDeuxiemeAnnee());

						// dossier etudiant
						dossierInscriptionAdministrativeDto
								.setDossierEtudiantId(dossierEtudiantDto
										.getId());
						dossierInscriptionAdministrativeDto
								.setTypeDossier(CursusConstants.TYPE_INSCRIPTION_REINSCRIPTION_INSCRIPTION_NOUVEAU_CYCLE);

						dossierInscriptionAdministrativeDto
								.setTypeDossier(CursusConstants.TYPE_INSCRIPTION_REINSCRIPTION_INSCRIPTION_NOUVEAU_CYCLE);

						// offre de formation
						dossierInscriptionAdministrativeDto
								.setOuvertureOffreFormationId(oof.getId());
						dossierInscriptionAdministrativeDto
								.setOffreFormationLibelleFr(oof
										.getOfLibelleLongFr());

						dossierInscriptionAdministrativeDto.setCodeDomaine(oof
								.getOfRefCodeDomaine());

						dossierInscriptionAdministrativeDto.setCodeFiliere(oof
								.getOfRefCodeFiliere());

						dossierInscriptionAdministrativeDto
								.setRefCodeDomaine(oof.getOfRefCodeDomaine());
						dossierInscriptionAdministrativeDto
								.setRefLibelleDomaine(oof
										.getOfRefLibelleDomaine());

						dossierInscriptionAdministrativeDto
								.setRefCodeFiliere(oof.getOfRefCodeFiliere());
						dossierInscriptionAdministrativeDto
								.setRefLibelleFiliere(oof
										.getOfRefLibelleFiliere());

						dossierInscriptionAdministrativeDto
								.setRefCodeSpecialite(oof
										.getOfRefCodeSpecialite());
						dossierInscriptionAdministrativeDto
								.setRefLibelleSpecialite(oof
										.getOfRefLibelleSpecialite());

						// Cycle et niveau
						Integer cycleId = concoursDto
								.getOuvertureOffreFormationDto().getCycleId();
						dossierInscriptionAdministrativeDto.setCycleId(cycleId);
						dossierInscriptionAdministrativeDto
								.setCycleLibelleLongLt(oof
										.getCycleLibelleLongLt());

						NiveauDto premierNiveau = niveauService
								.findPremierNiveauOf(cycleId);
						if (premierNiveau != null) {
							dossierInscriptionAdministrativeDto
									.setNiveauId(premierNiveau.getId());
							dossierInscriptionAdministrativeDto
									.setNiveauLibelleLongLt(premierNiveau
											.getLibelleLongLt());

						}

					} else {
						// historique des situations
						this.dossierInscriptionHistory = situationService
								.getEntityOccurrenceHistory(
										UtilConstants.ENTITE_DOSSIER_CODE,
										dossierInscriptionAdministrativeDto
												.getId());
					}
				}

				isCrud = true;
				isView = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * Generer le dossier etudiant si il n'existe pas
	 * 
	 * @author Mounir.MESSAOUDI on : 2 nov. 2014 11:01:31
	 */
	public void generateDossierEtudiantAction() {
		FacesMessage msg = new FacesMessage();
		try {
			if (dossierEtudiantDto == null) {
				dossierEtudiantDto = new DossierEtudiantDto();
				dossierEtudiantDto.setIndividuId(dossierCandidatDto
						.getIndividu().getId());
				dossierEtudiantDto.setDateCreation(new Date());
				dossierEtudiantDto
						.setTypeDossier(UtilConstants.TYPE_DOSSIER_ETUDIANT_CODE);

				SituationEntiteDto _se = situationService
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_DOSSIER_CODE,
								UtilConstants.SITUTAION_GENEREE_AUTO_CODE);

				dossierEtudiantDto.setRefEtablissementId(sessionBean
						.getIdEtablissement());

				dossierEtudiantDto.setSituationId(_se.getId());

				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				Integer ai = cal.get(Calendar.YEAR);
				String anneeInscription = ai.toString().substring(2);
				String anneeBac = "XX";

				String matricule = anneeInscription + anneeBac
						+ dossierCandidatDto.getIndividu().getIdentifiant();
				dossierEtudiantDto.setNumeroMatricule(matricule);

				dossierEtudiantDto = dossierEtudiantService
						.insertOrUpdate(dossierEtudiantDto);

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ bundleCommon.getString("msg_success_generation"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}

	}

	/**
	 * Soumettre le dossier d'inscription a la validation
	 * 
	 * @author Mounir.MESSAOUDI on : 15 oct. 2014 11:47:05
	 */
	public void sendToValidationAction() {
		FacesMessage msg = new FacesMessage();
		try {
			// if (dossierInscriptionAdministrativeDto == null) {
			// dossierInscriptionAdministrativeDto = new
			// DossierInscriptionAdministrativeDto();

			dossierInscriptionAdministrativeDto.setDateCreation(new Date());
			dossierInscriptionAdministrativeDto.setDateInscription(new Date());

			dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
					.insertOrUpdate(dossierInscriptionAdministrativeDto);

			// Enregistrer l'historique de changement de situation
			SituationEntiteOccurrenceDto s = new SituationEntiteOccurrenceDto();
			s.setDateSituation(new Date());
			s.setIdOccurrence(dossierInscriptionAdministrativeDto.getId());
			s.setIdSituation(dossierInscriptionAdministrativeDto
					.getSituationId());
			situationService.saveSituationOccurrence(s);

			// ajouter tout les documents attache au dossier condidat
			// d'inscription
			String entityName = DossierCandidat.class.getSimpleName();
			List<RefDocumentDto> listRefDocumentDto = documentService
					.findDocumentsOfEntity(entityName,
							String.valueOf(dossierCandidatDto.getId()));
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

			// mettre a jours le dossier condidat avec l'id de l'inscription
			dossierCandidatDto
					.setDossierInscriptionAdministrative(dossierInscriptionAdministrativeDto);
			dossierCandidatService.save(dossierCandidatDto);

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success")
					+ ": "
					+ inscriptionNouveayCycleBundle
							.getString("msg_success_soumission"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			// }

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}

		this.isCrud = false;
	}

	/**
	 * Annuler l'action en cours sur la fiche de voeux
	 * 
	 * @author Mounir.MESSAOUDI on : 2 oct. 2014 07:59:08
	 */
	public void cancelInscriptionAction() {
		dossierEtudiantDto = null;
		dossierInscriptionAdministrativeDto = null;
		dossierCandidatDto = null;
		isCrud = false;
	}

	/**
	 * Recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 11:10:38
	 */
	public void searchAction() {
		isCrud = false;
		isView = false;
		loadSearchResults();
	}

	/**
	 * Recherche avancee
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 11:10:54
	 */
	public void advancedSearchAction() {

	}

	public List<SituationEntiteOccurrenceDto> getDossierInscriptionHistory() {
		return dossierInscriptionHistory;
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

	public ConcoursDto getConcoursDto() {
		return concoursDto;
	}

	public void setConcoursDto(ConcoursDto concoursDto) {
		this.concoursDto = concoursDto;
	}

	public void setListeConcoursDto(List<ConcoursDto> listeConcoursDto) {
		this.listeConcoursDto = listeConcoursDto;
	}

	public List<SelectItem> getListeItemsConcours() {
		return listeItemsConcours;
	}

	public void setListeItemsConcours(List<SelectItem> listeItemsConcours) {
		this.listeItemsConcours = listeItemsConcours;
	}

	public Long getIdSelectedConcours() {
		return idSelectedConcours;
	}

	public void setIdSelectedConcours(Long idSelectedConcours) {
		this.idSelectedConcours = idSelectedConcours;
	}

	public DossierCandidatDto getDossierCandidatDto() {
		return dossierCandidatDto;
	}

	public void setDossierCandidatDto(DossierCandidatDto dossierCandidatDto) {
		this.dossierCandidatDto = dossierCandidatDto;
	}
}
