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
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.CongeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ReintegrationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.VoeuEtudiantChoixDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.VoeuEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.CongeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.ReintegrationService;
import dz.gov.mesrs.sii.fve.business.service.cursus.VoeuEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanSessionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.CycleService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;

/**
 * ManagedBean pour gerer les fiches de voeux de l'etudiant
 * 
 * @author Mounir.MESSAOUDI on : 28 sept. 2014 13:05:25
 */

@ManagedBean(name = "dossierReinscriptionEtudiantMBean")
@ViewScoped
public class DossierReinscriptionEtudiantMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(DossierReinscriptionEtudiantMBean.class);

	private ResourceBundle reinscriptionBundle;
	private ResourceBundle bundleCommon;

	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;

	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;

	@ManagedProperty(value = "#{voeuEtudiantService}")
	private VoeuEtudiantService voeuEtudiantService;

	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;

	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;

	@ManagedProperty("#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;

	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;

	@ManagedProperty("#{offreFormationService}")
	private OffreFormationService offreFormationService;

	@ManagedProperty(value = "#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;

	// TODO a enveler
	@ManagedProperty(value = "#{refDomaineLMDServiceImpl}")
	private RefDomaineLMDService refDomaineLMDService;

	@ManagedProperty(value = "#{refFiliereLmdServiceImpl}")
	private RefFiliereLmdService refFiliereLmdService;

	@ManagedProperty(value = "#{refSpecialiteLmdServiceImpl}")
	private RefSpecialiteLmdService refSpecialiteLmdService;

	@ManagedProperty(value = "#{refEtablissementServiceImpl}")
	private RefEtablissementService refEtablissementService;

	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;

	@ManagedProperty(value = "#{bilanSessionService}")
	private BilanSessionService bilanSessionService;

	@ManagedProperty(value = "#{reintegrationService}")
	private ReintegrationService reintegrationService;

	@ManagedProperty(value = "#{cycleService}")
	private CycleService cycleService;

	@ManagedProperty(value = "#{niveauService}")
	private NiveauService niveauService;

	@ManagedProperty(value = "#{congeAcademiqueService}")
	private CongeAcademiqueService congeAcademiqueService;

	private Integer idSituationEncoursTraitementEtudiant;
	private Integer idSituationCree;

	// Ui
	private Integer idCurrAnneeAcademique;
	private Integer idAnneeAcademique;
	private AnneeAcademiqueDto anneeAcademique;

	private List<OuvertureOffreFormationDto> ouvertureOffreFormationDtos;
	private List<VoeuEtudiantDto> voeuxEtudiantsDtoSearch;
	private VoeuEtudiantDto voeuEtudiantDto;
	private DossierEtudiantDto dossierEtudiantDto;

	private BilanSessionDto bilanSessionAnnuelDto;

	private DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto;

	private List<SelectItem> listAnneeAcademique;

	private boolean isView;
	private boolean isCrud;

	private boolean isAuthrorizedtoReinscrire = false;
	private String messageNotAuthrorizedtoReinscrire;

	private Integer typeReinscriptionId;

	private Integer idNiveau;
	private Integer idCycle;

	private String viewId; // +setter

	// Historique des situations
	private List<SituationEntiteOccurrenceDto> ficheVoeuxHistory;

	/**
	 * @author Mounir.MESSAOUDI on : 28 sept. 2014 13:06:18
	 */
	public DossierReinscriptionEtudiantMBean() {
		super();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		reinscriptionBundle = facesContext.getApplication().getResourceBundle(facesContext,
				CursusConstants.DOSSIER_REINSCRIPTION_BUNDLE_MSG_NAME);
		bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
				CursusConstants.COMMON_BUNDLE_MSG_NAME);

	}

	/**
	 * Post constructeur
	 * 
	 * @author Mounir.MESSAOUDI on : 28 sept. 2014 13:06:18
	 */
	@PostConstruct
	public void init() {

		try {
			String url = FacesContext.getCurrentInstance().getViewRoot().getViewId();
			this.viewId = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));

			idCurrAnneeAcademique = sessionBean.getIdAnneeAcademique();
			idAnneeAcademique = idCurrAnneeAcademique;

			anneeAcademique = anneeAcademiqueService.findById(idAnneeAcademique);

			Integer individuId = this.sessionBean.getSessionBean().getUser().getId();
			dossierEtudiantDto = dossierEtudiantService.findByIdIndividu(individuId, null).get(0);

			idSituationEncoursTraitementEtudiant = situationService.findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
					UtilConstants.FICHE_VOEUX_REINSCRIPTION_SITUTAION_ENCOURS_TRAITMENT_ETUDIANT_CODE).getId();

			idSituationCree = situationService.findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
					UtilConstants.FICHE_VOEUX_REINSCRIPTION_SITUTAION_CREEE_CODE).getId();

			// edit mode ?
			if (viewId.equals("FicheVoeuxEtudiantEdit")) {
				resetVoeuEtudiantDto();

			} else {
				loadSearchResults();

				// preparer la liste des annees academique
				listAnneeAcademique = new ArrayList<SelectItem>();
				List<AnneeAcademiqueDto> liste = anneeAcademiqueService.findAll();
				if (liste != null && !liste.isEmpty()) {
					for (AnneeAcademiqueDto anneeAcademiqueDto : liste) {
						SelectItem si = new SelectItem(
						/* anneeAcademiqueDto.getPremiereAnnee() */anneeAcademiqueDto.getId(),
								anneeAcademiqueDto.getPremiereAnnee() + "/" + anneeAcademiqueDto.getDeuxiemeAnnee());
						listAnneeAcademique.add(si);
					}
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
	 * @author Mounir.MESSAOUDI on : 16 oct. 2014 10:23:46
	 */
	public void loadSearchResults() {
		try {
			// recuperer tout les fiches de voeux
			VoeuEtudiantDto searchBo = new VoeuEtudiantDto();
			searchBo.setAnneeAcademiqueId(idAnneeAcademique);
			searchBo.setDossierEtudiantId(dossierEtudiantDto.getId());
			voeuxEtudiantsDtoSearch = voeuEtudiantService.findAdvanced(searchBo, null);
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}

	}

	/**
	 * Reinitialiser le dto voeuEtudiantDto
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 09:47:47
	 */
	private void resetVoeuEtudiantDto() {
		try {

			voeuEtudiantDto = voeuEtudiantService.findByIdDosEtudIdAnnAcad(this.dossierEtudiantDto.getId(),
					idAnneeAcademique);

			// si aucun voeux n'a ete cree
			if (voeuEtudiantDto == null) {

				checkIsAuthorizedtoReinscrire();

				if (isAuthrorizedtoReinscrire) {

					voeuEtudiantDto = new VoeuEtudiantDto();
					voeuEtudiantDto.setAnneeAcademiqueId(getIdCurrAnneeAcademique());
					voeuEtudiantDto.setAnneeAcademiqueCode(anneeAcademique.getCode());
					voeuEtudiantDto.setAnneeAcademiquePremiereAnnee(anneeAcademique.getPremiereAnnee());
					voeuEtudiantDto.setAnneeAcademiqueDeuxiemeAnnee(anneeAcademique.getDeuxiemeAnnee());

					voeuEtudiantDto.setDateCreation(new Date());
					voeuEtudiantDto.setDossierEtudiantId(this.dossierEtudiantDto.getId());

					voeuEtudiantDto.setIdAncienDossierInsAdmin(this.dossierInscriptionAdministrativeDto.getId());

					// TODO a envelever et recuperer directement le id de
					// l'etablissement du dossier etudiant
					RefEtablissementDto etablissement = refEtablissementService.findById(this.dossierEtudiantDto
							.getRefEtablissementId());

					voeuEtudiantDto.setEtablissement(etablissement);

					// recuper le cycle et le niveau a partir de la
					// decision
					CycleDto cycle = cycleService.findById(this.idCycle);

					NiveauDto niveau = niveauService.findById(this.idNiveau);

					voeuEtudiantDto.setCycle(cycle);
					voeuEtudiantDto.setNiveau(niveau);
					voeuEtudiantDto.setTypeReinscriptionId(this.typeReinscriptionId);

					// recuperer les offres de formation du cycle/niveau ouvert
					// dans
					// l'etablissement de l'etudiant
					// pendant l'annee academique en cours
					OuvertureOffreFormationDto oof = new OuvertureOffreFormationDto();
					oof.setAnneeAcademiqueId(this.idCurrAnneeAcademique);
					oof.setCycleId(cycle.getId());

					oof.setOfRefCodeDomaine(dossierInscriptionAdministrativeDto.getRefCodeDomaine());

					oof.setOfRefCodeFiliere(dossierInscriptionAdministrativeDto.getRefCodeFiliere());
					oof.setOfRefCodeSpecialite(dossierInscriptionAdministrativeDto.getRefCodeSpecialite());
					oof.setIdEtablissement(etablissement.getId());
					oof.setEstOuverte(true);

					ouvertureOffreFormationDtos = ouvertureOffreFormationService.findAdvanced(oof);

					List<VoeuEtudiantChoixDto> voeuxEtudiantsChoixDto = new ArrayList<>();
					int i = 1;
					if (ouvertureOffreFormationDtos != null && ouvertureOffreFormationDtos.size() > 0)
						for (OuvertureOffreFormationDto ouvertureOffreFormationDto : ouvertureOffreFormationDtos) {

							VoeuEtudiantChoixDto v = new VoeuEtudiantChoixDto();
							v.setOuvertureOffreFormation(ouvertureOffreFormationDto);

							String refCodeDomaine = ouvertureOffreFormationDto.getOfRefCodeDomaine();
							String refCodeFiliere = ouvertureOffreFormationDto.getOfRefCodeFiliere();
							String refCodeSpecialite = ouvertureOffreFormationDto.getOfRefCodeSpecialite();

							if (refCodeDomaine != null) {
								RefDomaineLMDDto d = refDomaineLMDService.findByIdentifiant(refCodeDomaine);
								v.setDomaine(d);
							}

							if (refCodeFiliere != null) {
								RefFiliereLmdDto f = refFiliereLmdService.findByIdentifiant(refCodeFiliere);
								v.setFiliere(f);
							}

							if (refCodeSpecialite != null) {
								RefSpecialiteLmdDto s = refSpecialiteLmdService.findByIdentifiant(refCodeSpecialite);
								v.setSpecialite(s);
							}

							v.setPriorite(i);
							i++;
							voeuxEtudiantsChoixDto.add(v);

						}

					voeuEtudiantDto.setVoeuxEtudiantsChoixDto(voeuxEtudiantsChoixDto);
					isCrud = true;

				} else { // l'etudiant n'est pas autorisee a se reinscrire

					// afficher le message

				}

			} else {

				this.isAuthrorizedtoReinscrire = true;

				Integer situationId = voeuEtudiantDto.getSituationId();
				// cree ou en cours de traitement !
				if (situationId == 49 || situationId == 55) {
					isCrud = true;
				} else {
					isCrud = false;
				}

				// historique des situations
				this.ficheVoeuxHistory = situationService.getEntityOccurrenceHistory(
						UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION, voeuEtudiantDto.getId());
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}

	}

	/**
	 * Verifier si l'etudiant est autorise a se reinscrire
	 * 
	 * @author Mounir.MESSAOUDI on : 26 oct. 2014 14:16:12
	 * @return
	 */
	private boolean checkIsAuthorizedtoReinscrire() {

		dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
				.findPrecdentDossierInscriptionForEtudiant(dossierEtudiantDto.getId());

		if (dossierInscriptionAdministrativeDto == null) {
			messageNotAuthrorizedtoReinscrire = "Bilan annuel null";
		} else {
			// recuperer le bilan annuel de l'etudiant
			bilanSessionAnnuelDto = bilanSessionService
					.findBilanFinalByIdDossierInscrAdmin(dossierInscriptionAdministrativeDto.getId());

			// si aucun bilan n'existe
			if (bilanSessionAnnuelDto == null) {
				messageNotAuthrorizedtoReinscrire = "bilan annuel null";
				isAuthrorizedtoReinscrire = false;

			} else {
				// traiter les reinscription suivant le type de la
				// d�cision

				// chercher le prochain niveau dans le cycle
				NiveauDto n = niveauService.findById(dossierInscriptionAdministrativeDto.getNiveauId());
				NiveauDto prochainNiveau = niveauService.findProchainNiveauOf(n);

				switch (bilanSessionAnnuelDto.getTypeDecisionCode()) {

				// admis avec dette
				case CursusConstants.TYPE_DECISION_ADMISSION_PASSAGE_AVEC_DETTE:

					if (prochainNiveau != null) {
						NomenclatureDto typeInsReins = nomenclatureService.findByCode(
								CursusConstants.NC_TYPE_INSCRIPTION_REINSCRIPTION,
								CursusConstants.TYPE_INSCRIPTION_REINSCRIPTION_REINSCRIPTION_ADMISSION);
						this.typeReinscriptionId = typeInsReins.getId();

						this.idCycle = dossierInscriptionAdministrativeDto.getCycleId();
						this.idNiveau = prochainNiveau.getId();

						isAuthrorizedtoReinscrire = true;
					} else {
						messageNotAuthrorizedtoReinscrire = "ADMISSION_PASSAGE_AVEC_DETTE prochainNiveau null";
						isAuthrorizedtoReinscrire = false;
					}

					break;

				// admis sans dette
				case CursusConstants.TYPE_DECISION_ADMISSION_PASSAGE_SANS_DETTE:

					if (prochainNiveau != null) {
						NomenclatureDto typeInsReins = nomenclatureService.findByCode(
								CursusConstants.NC_TYPE_INSCRIPTION_REINSCRIPTION,
								CursusConstants.TYPE_INSCRIPTION_REINSCRIPTION_REINSCRIPTION_ADMISSION);
						this.typeReinscriptionId = typeInsReins.getId();

						this.idCycle = dossierInscriptionAdministrativeDto.getCycleId();
						this.idNiveau = prochainNiveau.getId();

						isAuthrorizedtoReinscrire = true;
					} else {
						messageNotAuthrorizedtoReinscrire = "ADMISSION_PASSAGE_AVEC_DETTE prochainNiveau null";
						isAuthrorizedtoReinscrire = false;
					}

					break;

				// redoublement
				case CursusConstants.TYPE_DECISION_ADMISSION_REDOUBLEMENT:
					NomenclatureDto typeInsReins = nomenclatureService.findByCode(
							CursusConstants.NC_TYPE_INSCRIPTION_REINSCRIPTION,
							CursusConstants.TYPE_INSCRIPTION_REINSCRIPTION_REINSCRIPTION_REDOUBLEMENT);
					this.typeReinscriptionId = typeInsReins.getId();

					this.idCycle = dossierInscriptionAdministrativeDto.getCycleId();
					this.idNiveau = dossierInscriptionAdministrativeDto.getNiveauId();

					isAuthrorizedtoReinscrire = true;

					break;

				// exclu
				case CursusConstants.TYPE_DECISION_ADMISSION_EXCLUSION:
					// recherche s'il y a une reintegration deja validee
					ReintegrationDto reintegrationDto = new ReintegrationDto();
					reintegrationDto.setDossierInscriptionId(dossierInscriptionAdministrativeDto.getId());
					reintegrationDto.setReintegrationValidee(true);
					reintegrationDto.setRefCodeTypeExclusion(CursusConstants.EXCLUSION_ANNEE_UNIVERSITAIRE);

					List<ReintegrationDto> listeReintegrationsDto = reintegrationService.findAdvanced(reintegrationDto);
					if (listeReintegrationsDto != null && !listeReintegrationsDto.isEmpty()) {

						NomenclatureDto tt = nomenclatureService.findByCode(
								CursusConstants.NC_TYPE_INSCRIPTION_REINSCRIPTION,
								CursusConstants.TYPE_INSCRIPTION_REINSCRIPTION_REINSCRIPTION_EXLUSION);
						this.typeReinscriptionId = tt.getId();

						this.idCycle = dossierInscriptionAdministrativeDto.getCycleId();
						this.idNiveau = dossierInscriptionAdministrativeDto.getNiveauId();

						isAuthrorizedtoReinscrire = true;
					} else {
						// TODO message dans l'ecran il faut aumoin un
						// reintegration validee
						messageNotAuthrorizedtoReinscrire = "Exclu: Votre r�int�gration n'est pas encore valid�e.";

						isAuthrorizedtoReinscrire = true;

					}
					break;

				// conge academique
				case CursusConstants.TYPE_DECISION_ADMISSION_CONGE_ACADEMIQUE:
					// recherche s'il ya le conge academique de l'annee
					// en cours a ete validee
					CongeAcademiqueDto congeAcademiqueDto = congeAcademiqueService
							.findByIdDossierInscriptionAdministrative(dossierInscriptionAdministrativeDto.getId());

					if (congeAcademiqueDto != null && congeAcademiqueDto.isReintegrationValidee()) {

						NomenclatureDto tt = nomenclatureService.findByCode(
								CursusConstants.NC_TYPE_INSCRIPTION_REINSCRIPTION,
								CursusConstants.TYPE_INSCRIPTION_REINSCRIPTION_REINSCRIPTION_CONGE_ACADEMIQUE);
						this.typeReinscriptionId = tt.getId();

						this.idCycle = dossierInscriptionAdministrativeDto.getCycleId();
						this.idNiveau = dossierInscriptionAdministrativeDto.getNiveauId();

						isAuthrorizedtoReinscrire = true;

					} else {
						// TODO message dans l'ecran il faut que une
						// reintegration validee
						messageNotAuthrorizedtoReinscrire = "En cong� acad�mique : votre r�int�gration n'est pas encore valid�e.";
						isAuthrorizedtoReinscrire = false;
					}
					break;

				// reorientation
				case CursusConstants.TYPE_DECISION_ADMISSION_REORIENTATION:
					// TODO message dans l'ecran que se cas est traite
					// dans les transferts
					break;

				// validation de cycle
				case CursusConstants.TYPE_DECISION_ADMISSION_VALIDATION_CYCLE:
					// TODO a traiter
					break;

				// validation de periode
				case CursusConstants.TYPE_DECISION_ADMISSION_VALIDATION_PERIODE:
					// TODO a traiter
					break;

				default:
					isAuthrorizedtoReinscrire = false;
					messageNotAuthrorizedtoReinscrire = "Vous n'�tes pas autoris� a se r�inscrire";
					break;
				}
			}
		}

		return isAuthrorizedtoReinscrire;

	}

	public void setReinscriptionBundle(ResourceBundle reinscriptionBundle) {
		this.reinscriptionBundle = reinscriptionBundle;
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

	public VoeuEtudiantDto getVoeuEtudiantDto() {
		return voeuEtudiantDto;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 1 oct. 2014 10:23:42
	 * @param voeuEtudiantDto
	 */
	public void setVoeuEtudiantDto(VoeuEtudiantDto voeuEtudiantDto) {
		this.voeuEtudiantDto = voeuEtudiantDto;
	}

	public DossierEtudiantDto getDossierEtudiantDto() {
		return dossierEtudiantDto;
	}

	public void setDossierEtudiantDto(DossierEtudiantDto dossierEtudiantDto) {
		this.dossierEtudiantDto = dossierEtudiantDto;
	}

	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	public void setAnneeAcademiqueService(AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	public void setOuvertureOffreFormationService(OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	public void setOffreFormationService(OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	public RefEtablissementService getRefEtablissementService() {
		return refEtablissementService;
	}

	public void setRefEtablissementService(RefEtablissementService refEtablissementService) {
		this.refEtablissementService = refEtablissementService;
	}

	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	public List<OuvertureOffreFormationDto> getOuvertureOffreFormationDtos() {
		return ouvertureOffreFormationDtos;
	}

	public void setOuvertureOffreFormationDtos(List<OuvertureOffreFormationDto> ouvertureOffreFormationDtos) {
		this.ouvertureOffreFormationDtos = ouvertureOffreFormationDtos;
	}

	public void setDossierEtudiantService(DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	public void setRefDomaineLMDService(RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	public ReintegrationService getReintegrationService() {
		return reintegrationService;
	}

	public void setReintegrationService(ReintegrationService reintegrationService) {
		this.reintegrationService = reintegrationService;
	}

	public CycleService getCycleService() {
		return cycleService;
	}

	public void setCycleService(CycleService cycleService) {
		this.cycleService = cycleService;
	}

	public RefSpecialiteLmdService getRefSpecialiteLmdService() {
		return refSpecialiteLmdService;
	}

	public void setRefSpecialiteLmdService(RefSpecialiteLmdService refSpecialiteLmdService) {
		this.refSpecialiteLmdService = refSpecialiteLmdService;
	}

	public boolean isView() {
		return isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public void setCrud(boolean isCrud) {
		this.isCrud = isCrud;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public Integer getIdSituationEncoursTraitementEtudiant() {
		return idSituationEncoursTraitementEtudiant;
	}

	public void setIdSituationEncoursTraitementEtudiant(Integer idSituationEncoursTraitementEtudiant) {
		this.idSituationEncoursTraitementEtudiant = idSituationEncoursTraitementEtudiant;
	}

	public Integer getIdSituationCree() {
		return idSituationCree;
	}

	public void setIdSituationCree(Integer idSituationCree) {
		this.idSituationCree = idSituationCree;
	}

	public DossierInscriptionAdministrativeDto getDossierInscriptionAdministrativeDto() {
		return dossierInscriptionAdministrativeDto;
	}

	public void setDossierInscriptionAdministrativeDto(
			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto) {
		this.dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeDto;
	}

	public List<VoeuEtudiantDto> getVoeuxEtudiantsDtoSearch() {
		return voeuxEtudiantsDtoSearch;
	}

	public void setVoeuxEtudiantsDtoSearch(List<VoeuEtudiantDto> voeuxEtudiantsDtoSearch) {
		this.voeuxEtudiantsDtoSearch = voeuxEtudiantsDtoSearch;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 7 oct. 2014 10:02:59
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			voeuEtudiantDto = (VoeuEtudiantDto) event.getObject();

			// historique des situations
			ficheVoeuxHistory = situationService.getEntityOccurrenceHistory(
					UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION, voeuEtudiantDto.getId());

			isView = true;
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * Evenement changement d'un choix
	 * 
	 * @author Mounir.MESSAOUDI on : 7 oct. 2014 10:02:50
	 * @param event
	 */
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Choix edit� ", ((VoeuEtudiantChoixDto) event.getObject())
				.getOuvertureOffreFormation().getOffreFormationlibelle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Evenement annulation d'un choix
	 * 
	 * @author Mounir.MESSAOUDI on : 7 oct. 2014 10:02:54
	 * @param event
	 */
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Choix annul� ", ((VoeuEtudiantChoixDto) event.getObject())
				.getOuvertureOffreFormation().getOffreFormationlibelle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void listenerListChanged() {
		List<VoeuEtudiantChoixDto> v = voeuEtudiantDto.getVoeuxEtudiantsChoixDto();
		if (v != null) {
			int i = 1;
			for (VoeuEtudiantChoixDto voeuEtudiantChoixDto : v) {
				voeuEtudiantChoixDto.setPriorite(i);
				i++;
			}
		}

	}

	/**
	 * Enregistrer la fiche de voeux
	 * 
	 * @author Mounir.MESSAOUDI on : 1 oct. 2014 10:13:00
	 */
	public void saveFicheVoeuxAction() {
		FacesMessage msg = new FacesMessage();
		try {

			List<VoeuEtudiantChoixDto> v = voeuEtudiantDto.getVoeuxEtudiantsChoixDto();
			if (v != null) {
				int i = 1;
				for (VoeuEtudiantChoixDto voeuEtudiantChoixDto : v) {
					voeuEtudiantChoixDto.setPriorite(i);
					i++;
				}
			}

			if (voeuEtudiantDto.getId() == null)
				voeuEtudiantDto.setSituationId(idSituationCree);
			else
				voeuEtudiantDto.setSituationId(idSituationEncoursTraitementEtudiant);

			voeuEtudiantService.insertOrUpdate(voeuEtudiantDto);

			// historique des situations
			ficheVoeuxHistory = situationService.getEntityOccurrenceHistory(
					UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION, voeuEtudiantDto.getId());

			this.resetVoeuEtudiantDto();

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_enregistrement"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
		// loadSearchResults();
	}

	/**
	 * Valider la fiche de voeux
	 * 
	 * @author Mounir.MESSAOUDI on : 1 oct. 2014 10:12:57
	 */
	public void validateFicheVoeuxAction() {
		FacesMessage msg = new FacesMessage();
		try {
			List<VoeuEtudiantChoixDto> v = voeuEtudiantDto.getVoeuxEtudiantsChoixDto();
			if (v != null) {
				int i = 1;
				for (VoeuEtudiantChoixDto voeuEtudiantChoixDto : v) {
					voeuEtudiantChoixDto.setPriorite(i);
					i++;
				}
			}

			voeuEtudiantDto.setSituationId(situationService.findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION,
					UtilConstants.FICHE_VOEUX_REINSCRIPTION_SITUTAION_VALIDEE_ETUDIANT_CODE).getId());

			voeuEtudiantService.insertOrUpdate(voeuEtudiantDto);

			// historique des situations
			ficheVoeuxHistory = situationService.getEntityOccurrenceHistory(
					UtilConstants.ENTITE_FICHE_VOEUX_REINSCRIPTION, voeuEtudiantDto.getId());

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success")
					+ ": "
					+ reinscriptionBundle
							.getString("dossier_reinscription_msg_success_validation_fiche_voeux_etudiant"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

			isCrud = false;

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
		isCrud = false;
		loadSearchResults();
	}

	/**
	 * Annuler la fiche de voeux
	 * 
	 * @author Mounir.MESSAOUDI on : 1 oct. 2014 10:23:27
	 */
	public void cancelFicheVoeuxAction() {
		isView = false;
		isCrud = false;
		resetVoeuEtudiantDto();
		loadSearchResults();

	}

	public List<SituationEntiteOccurrenceDto> getFicheVoeuxHistory() {
		if (ficheVoeuxHistory == null)
			ficheVoeuxHistory = new ArrayList<SituationEntiteOccurrenceDto>();
		return ficheVoeuxHistory;
	}

	public List<SelectItem> getListAnneeAcademique() {
		return listAnneeAcademique;
	}

	public BilanSessionService getBilanSessionService() {
		return bilanSessionService;
	}

	public void setBilanSessionService(BilanSessionService bilanSessionService) {
		this.bilanSessionService = bilanSessionService;
	}

	public NiveauService getNiveauService() {
		return niveauService;
	}

	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	public CongeAcademiqueService getCongeAcademiqueService() {
		return congeAcademiqueService;
	}

	public void setCongeAcademiqueService(CongeAcademiqueService congeAcademiqueService) {
		this.congeAcademiqueService = congeAcademiqueService;
	}

	public boolean isAuthrorizedtoReinscrire() {
		return isAuthrorizedtoReinscrire;
	}

	public void setAuthrorizedtoReinscrire(boolean isAuthrorizedtoReinscrire) {
		this.isAuthrorizedtoReinscrire = isAuthrorizedtoReinscrire;
	}

	public String getMessageNotAuthrorizedtoReinscrire() {
		return messageNotAuthrorizedtoReinscrire;
	}

	public void setMessageNotAuthrorizedtoReinscrire(String messageNotAuthrorizedtoReinscrire) {
		this.messageNotAuthrorizedtoReinscrire = messageNotAuthrorizedtoReinscrire;
	}

}