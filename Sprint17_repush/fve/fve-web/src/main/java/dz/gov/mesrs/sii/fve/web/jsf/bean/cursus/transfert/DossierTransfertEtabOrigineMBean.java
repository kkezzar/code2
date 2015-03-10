package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.transfert;

import java.util.ArrayList;
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
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.DossierBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.NotesBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierTransfertDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierTransfertService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;

/**
 * Managed bean pour gerer les taches de validation de la demande cote
 * etablissement dorigine
 * 
 * @author Mounir.MESSAOUDI
 */
@ManagedBean(name = "dossierTransfertEtabOrigineMBean")
@ViewScoped
public class DossierTransfertEtabOrigineMBean {

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory
			.getLog(DossierTransfertEtudiantMBean.class);

	private ResourceBundle transfertBundle;
	private ResourceBundle bundleCommon;

	private List<DossierTransfertDto> dossierTransfertResultSearch;

	private DossierTransfertDto dossierTransfertSearchDto;

	@ManagedProperty(value = "#{dossierTransfertService}")
	private DossierTransfertService dossierTransfertService;

	@ManagedProperty(value = "#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;

	@ManagedProperty(value = "#{bacService}")
	private BacService bacService;

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
	@ManagedProperty("#{nomenclatureServiceImpl}")
	NomenclatureService nomenclatureService;

	@ManagedProperty("#{refIndividuServiceImpl}")
	RefIndividuService refIndividuService;
	
	@ManagedProperty("#{refSpecialiteLmdServiceImpl}")
	RefSpecialiteLmdService refSpecialiteLmdService;

	@ManagedProperty("#{refFiliereLmdServiceImpl}")
	RefFiliereLmdService refFiliereLmdService;

	@ManagedProperty("#{refDomaineLMDServiceImpl}")
	RefDomaineLMDService refDomaineLMDService;
	
	@ManagedProperty("#{refEtablissementServiceImpl}")
	private RefEtablissementService refEtablissementService;

	/**
	 * Le dossier du transfert en cours de traitement
	 */
	private DossierTransfertDto dossierTransfertDto;
	private DossierEtudiantDto dossierEtudiantDto;
	private DossierBachelierDto dossierBachelierDto;
	private List<NotesBachelierDto> notesBacheliersDtos;

	private String refCodeEtabOrigine;

	// Ui
	private String refCodeCurrEtablissement;

	private Integer idCurrAnneeAcademique;
	private String refCodeCurrAnneeAcademique;
	private Integer idAnneeAcademique;

	private List<SelectItem> listTypesTransfert;
	private List<SelectItem> listEtablissements;
	private List<SelectItem> listAnneeAcademique;
	private List<SelectItem> listDomaines;
	private List<SelectItem> listFilieres;

	private List<SelectItem> listMotifsRejet;

	private List<SelectItem> listoffresFormation;

	private boolean isCrud;

	private boolean isView;

	private String viewId; // +setter

	// Historique des situations
	private List<SituationEntiteOccurrenceDto> dossierTransfertHistory;

	/**
	 * Creates a new instance of DossierTransfertEtabOrigineMBean
	 */
	public DossierTransfertEtabOrigineMBean() {
		super();
	}

	/**
	 * Post constructeur
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:54:49
	 */
	@PostConstruct
	public void init() {
		try {
			String url = FacesContext.getCurrentInstance().getViewRoot()
					.getViewId();
			this.viewId = url.substring(url.lastIndexOf("/") + 1,
					url.lastIndexOf("."));

			this.refCodeCurrEtablissement = sessionBean.getCodeEtablissement();

			this.idCurrAnneeAcademique = sessionBean.getIdAnneeAcademique();
			this.refCodeCurrAnneeAcademique = sessionBean
					.getCodeAnneeAcademique();
			this.idAnneeAcademique = this.idCurrAnneeAcademique;

			this.initUI();
			this.resetDossiertransfertDto();
			this.loadSearchResults();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Initialiser l'interface
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 10:34:14
	 */
	private void initUI() {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			transfertBundle = facesContext.getApplication().getResourceBundle(
					facesContext,
					CursusConstants.DOSSIER_TRANSFERT_BUNDLE_MSG_NAME);
			bundleCommon = facesContext.getApplication().getResourceBundle(
					facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);

			if (viewId.equals("DemandeTransfertEtabOrigineEdit")) {
				List<NomenclatureDto> listMotifsRejetDtos = nomenclatureService
						.findByName(CursusConstants.NC_MOTIF_REJET_DEMANDE_TRANSFERT);

				this.listMotifsRejet = new ArrayList<SelectItem>();
				for (NomenclatureDto item : listMotifsRejetDtos) {
					this.listMotifsRejet.add(new SelectItem(item.getCode(),
							item.getLibelleLongFr()));
				}
			} else {
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
		}

	}

	/**
	 * Reinitialiser le dossierTransfertDto
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:55:32
	 */
	private void resetDossiertransfertDto() {
		this.dossierTransfertDto = null;
		this.dossierTransfertDto = new DossierTransfertDto();

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
	 * Charger les resultats de la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:55:32
	 */
	public void loadSearchResults() {
		FacesMessage msg = new FacesMessage();
		dossierTransfertResultSearch = new ArrayList<>();
		try {

			dossierTransfertSearchDto = new DossierTransfertDto();

			// la liste des dossiers du transfert de l'etablissement en
			// cours(etab dorigine)
			dossierTransfertSearchDto
					.setRefCodeEtabOrigine(this.refCodeCurrEtablissement);
			// l'annee academique en cours
			dossierTransfertSearchDto
					.setAnneeAcademiqueId(this.idAnneeAcademique);

			List<DossierTransfertDto> dossierTransferts;
			if (viewId.equals("DemandeTransfertEtabOrigineEdit")) {
				List<SituationEntiteDto> situationDtos = new ArrayList<>();
				SituationEntiteDto situationEncTraitEtabOrigine = situationService
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_DOSSIER_TRANSFERT_ETUDIANT,
								UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_ENCOURS_TRAITMENT_GESTIONNAIRE_OR_CODE);

				situationDtos.add(situationEncTraitEtabOrigine);

				dossierTransferts = dossierTransfertService.findAdvanced(
						dossierTransfertSearchDto, situationDtos);

				// afficher seulement les demandes des transferts interne
			} else if (viewId.equals("DemandeTransfertInterne")) {
				dossierTransfertSearchDto
						.setRefCodeTypeTransfert(UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_INTERNE);
				dossierTransferts = dossierTransfertService
						.findAdvanced(dossierTransfertSearchDto);
			} else {
				// dossierTransferts = dossierTransfertService
				// .findAdvanced(dossierTransfertSearchDto);
				List<String> typesDossiersTransfert = new ArrayList<String>();
				typesDossiersTransfert
						.add(UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_INTER_UNIVERSITAIRE);
				typesDossiersTransfert
						.add(UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_INTERNECRU);

				dossierTransferts = dossierTransfertService
						.findAdvanced(dossierTransfertSearchDto,
								typesDossiersTransfert, null);
				dossierTransferts = dossierTransfertService
						.findAdvanced(dossierTransfertSearchDto,
								typesDossiersTransfert, null);
			}

			if (dossierTransferts != null && !dossierTransferts.isEmpty()) {
				for (DossierTransfertDto dossierTransfertDto : dossierTransferts) {
					// individu
					RefIndividuDto refIndividuDto = refIndividuService
							.findByIdentifiant(dossierTransfertDto
									.getIndividuNin());
					if (refIndividuDto != null)
						map(refIndividuDto, dossierTransfertDto);

					// motif de la demande
					NomenclatureDto motifTransfertDto = nomenclatureService
							.findByCode(
									CursusConstants.NC_MOTIF_DEMANDE_TRANSFERT,
									dossierTransfertDto.getRefCodeMotif());
					dossierTransfertDto
							.setRefCodeMotifLibelleFr(motifTransfertDto
									.getLibelleLongFr());
					dossierTransfertDto
							.setRefCodeMotifLibelleAr(motifTransfertDto
									.getLibelleLongAr());

					// type de transfert
					NomenclatureDto typeTransfertDto = nomenclatureService
							.findByCode(
									CursusConstants.NC_TYPE_DEMANDE_TRANSFERT,
									dossierTransfertDto
											.getRefCodeTypeTransfert());
					dossierTransfertDto.setRefLibelleTransfert(typeTransfertDto
							.getLibelleLongFr());

					// etablissement origine
					RefEtablissementDto refEtablissementDto = refEtablissementService
							.findByIdentifiant(dossierTransfertDto
									.getRefCodeEtabOrigine());

					dossierTransfertDto
							.setRefLibelleEtabOrigine(refEtablissementDto
									.getLlEtablissementLatin());

					// etablissement d'accueil
					if (dossierTransfertDto.getRefCodeEtabAccueil() != null) {
						RefEtablissementDto refEtablissementAccueilDto = refEtablissementService
								.findByIdentifiant(dossierTransfertDto
										.getRefCodeEtabAccueil());
						dossierTransfertDto
								.setRefLibelleEtabAccueil(refEtablissementAccueilDto
										.getLlEtablissementLatin());
					}

					// domaine/filiere demande
					if (dossierTransfertDto.getRefCodeDomaineDemande() != null) {
						RefDomaineLMDDto rdd = refDomaineLMDService
								.findByIdentifiant(dossierTransfertDto
										.getRefCodeDomaineDemande());
						dossierTransfertDto.setDomaineDemandeLibelleFr(rdd
								.getLlDomaine());
						dossierTransfertDto.setDomaineDemandeLibelleAr(rdd
								.getLlDomaineArabe());
					}

					if (dossierTransfertDto.getRefCodeFiliereDemandee() != null) {
						RefFiliereLmdDto rfd = refFiliereLmdService
								.findByIdentifiant(dossierTransfertDto
										.getRefCodeFiliereDemandee());
						if (rfd != null) {
							dossierTransfertDto.setFiliereDemandeeLibelleFr(rfd
									.getLlFiliere());
							dossierTransfertDto.setFiliereDemandeeLibelleAr(rfd
									.getLlFiliereArabe());
						}

					}

					// domaine/filiere origine
					if (dossierTransfertDto.getRefCodeDomaineOrigine() != null) {
						RefDomaineLMDDto rdd = refDomaineLMDService
								.findByIdentifiant(dossierTransfertDto
										.getRefCodeDomaineOrigine());
						dossierTransfertDto.setDomaineOrigineLibelleFr(rdd
								.getLlDomaine());
						dossierTransfertDto.setDomaineOrigineLibelleAr(rdd
								.getLlDomaineArabe());
					}

					if (dossierTransfertDto.getRefCodeFiliereOrigine() != null) {
						RefFiliereLmdDto rfd = refFiliereLmdService
								.findByIdentifiant(dossierTransfertDto
										.getRefCodeFiliereOrigine());
						if (rfd != null) {
							dossierTransfertDto.setFiliereOrigineLibelleFr(rfd
									.getLlFiliere());
							dossierTransfertDto.setFiliereOrigineLibelleAr(rfd
									.getLlFiliereArabe());

							// si l'etudiant est inscrit dans une filiere
							// (domain null)
							if (dossierTransfertDto.getRefCodeDomaineOrigine() == null) {
								dossierTransfertDto
										.setDomaineOrigineLibelleFr(rfd
												.getLlDomaine());
								dossierTransfertDto
										.setDomaineOrigineLibelleAr(rfd
												.getLlDomaineArabe());
							}
						}
					}

					// decision etab d'origine
					Boolean etabOrigineAccepte = dossierTransfertDto
							.getIsEtabOrigineAccepte();

					if (etabOrigineAccepte != null) {
						if (etabOrigineAccepte)
							dossierTransfertDto
									.setRefLibelleAvisEtabOrigine(transfertBundle
											.getString("demande_transfert_etudiant_acceptee"));
						else {
							dossierTransfertDto
									.setRefLibelleAvisEtabOrigine(transfertBundle
											.getString("demande_transfert_etudiant_rejetee"));

							NomenclatureDto motifRejet = nomenclatureService
									.findByCode(
											CursusConstants.NC_MOTIF_REJET_DEMANDE_TRANSFERT,
											dossierTransfertDto
													.getRefCodeMotifEtabOrigine());
							if (motifRejet != null)
								dossierTransfertDto
										.setRefLibelleMotifEtabOrigine(motifRejet
												.getLibelleLongFr());

						}

					}

					// decision etab accueil
					Boolean etabAccueilAccepte = dossierTransfertDto
							.getIsEtabAccueilAccepte();

					if (etabAccueilAccepte != null) {
						if (etabAccueilAccepte)
							dossierTransfertDto
									.setRefLibelleAvisEtabAccueil(transfertBundle
											.getString("demande_transfert_etudiant_acceptee"));
						else {
							dossierTransfertDto
									.setRefLibelleAvisEtabAccueil(transfertBundle
											.getString("demande_transfert_etudiant_rejetee"));

							NomenclatureDto motifRejet = nomenclatureService
									.findByCode(
											CursusConstants.NC_MOTIF_REJET_DEMANDE_TRANSFERT,
											dossierTransfertDto
													.getRefCodeMotifEtabAccueil());
							if (motifRejet != null)
								dossierTransfertDto
										.setRefLibelleMotifEtabAccueil(motifRejet
												.getLibelleLongFr());
						}

					}

					// resultat final
					Boolean accordee = dossierTransfertDto.getIsAccordee();
					if (accordee != null) {
						if (accordee)
							dossierTransfertDto
									.setRefLibelleAvisFinal(transfertBundle
											.getString("demande_transfert_etudiant_accordee"));
						else {
							dossierTransfertDto
									.setRefLibelleAvisFinal(transfertBundle
											.getString("demande_transfert_etudiant_non_accordee"));
						}
					}

					dossierTransfertResultSearch.add(dossierTransfertDto);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:23
	 * @param refIndividuDto
	 * @param dossierTransfertDto
	 */
	private void map(RefIndividuDto refIndividuDto,
			DossierTransfertDto dossierTransfertDto) {
		if (dossierTransfertDto == null) {
			dossierTransfertDto = new DossierTransfertDto();
		}
		dossierTransfertDto.setIndividuId(refIndividuDto.getId());
		dossierTransfertDto.setIndividuNin(refIndividuDto.getIdentifiant());
		dossierTransfertDto.setIndividuNomLatin(refIndividuDto.getNomLatin());
		dossierTransfertDto.setIndividuNomArabe(refIndividuDto.getNomArabe());
		dossierTransfertDto.setIndividuPrenomArabe(refIndividuDto
				.getPrenomArabe());
		dossierTransfertDto.setIndividuPrenomLatin(refIndividuDto
				.getPrenomLatin());
		if (refIndividuDto.getDateNaissance() != null) {
			dossierTransfertDto.setIndividuDateNaissance(refIndividuDto
					.getDateNaissance());
		}
	}

	public List<DossierTransfertDto> getDossierTransfertResultSearch() {
		return dossierTransfertResultSearch;
	}

	public void setDossierTransfertResultSearch(
			List<DossierTransfertDto> dossierTransfertResultSearch) {
		this.dossierTransfertResultSearch = dossierTransfertResultSearch;
	}

	public DossierTransfertDto getDossierTransfertSearchDto() {
		return dossierTransfertSearchDto;
	}

	public void setDossierTransfertSearchDto(
			DossierTransfertDto dossierTransfertSearchDto) {
		this.dossierTransfertSearchDto = dossierTransfertSearchDto;
	}
	
	/**
	 * [DossierTransfertEtabOrigineMBean.refEtablissementService] Getter 
	 * @author Rafik on : 27 nov. 2014  13:02:27
	 * @return the refEtablissementService
	 */
	public RefEtablissementService getRefEtablissementService() {
		return refEtablissementService;
	}

	/**
	 * [DossierTransfertEtabOrigineMBean.refEtablissementService] Setter 
	 * @author Rafik on : 27 nov. 2014  13:02:27
	 * @param refEtablissementService the refEtablissementService to set
	 */
	public void setRefEtablissementService(
			RefEtablissementService refEtablissementService) {
		this.refEtablissementService = refEtablissementService;
	}

	public DossierTransfertService getDossierTransfertService() {
		return dossierTransfertService;
	}

	public void setDossierTransfertService(
			DossierTransfertService dossierTransfertService) {
		this.dossierTransfertService = dossierTransfertService;
	}

	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	public BacService getBacService() {
		return bacService;
	}

	public void setBacService(BacService bacService) {
		this.bacService = bacService;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
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

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	public DossierTransfertDto getDossierTransfertDto() {
		return dossierTransfertDto;
	}

	public void setDossierTransfertDto(DossierTransfertDto dossierTransfertDto) {
		this.dossierTransfertDto = dossierTransfertDto;
	}

	public DossierEtudiantDto getDossierEtudiantDto() {
		return dossierEtudiantDto;
	}

	public void setDossierEtudiantDto(DossierEtudiantDto dossierEtudiantDto) {
		this.dossierEtudiantDto = dossierEtudiantDto;
	}

	public DossierBachelierDto getDossierBachelierDto() {
		return dossierBachelierDto;
	}

	public void setDossierBachelierDto(DossierBachelierDto dossierBachelierDto) {
		this.dossierBachelierDto = dossierBachelierDto;
	}

	public List<NotesBachelierDto> getNotesBacheliersDtos() {
		return notesBacheliersDtos;
	}

	public void setNotesBacheliersDtos(
			List<NotesBachelierDto> notesBacheliersDtos) {
		this.notesBacheliersDtos = notesBacheliersDtos;
	}

	public String getRefCodeEtabOrigine() {
		return refCodeEtabOrigine;
	}

	public void setRefCodeEtabOrigine(String refCodeEtabOrigine) {
		this.refCodeEtabOrigine = refCodeEtabOrigine;
	}

	public List<SelectItem> getListTypesTransfert() {
		return listTypesTransfert;
	}

	public void setListTypesTransfert(List<SelectItem> listTypesTransfert) {
		this.listTypesTransfert = listTypesTransfert;
	}

	public List<SelectItem> getListEtablissements() {
		return listEtablissements;
	}

	public List<SelectItem> getListoffresFormation() {
		return listoffresFormation;
	}

	public void setListoffresFormation(List<SelectItem> listoffresFormation) {
		this.listoffresFormation = listoffresFormation;
	}

	public void setListEtablissements(List<SelectItem> listEtablissements) {
		this.listEtablissements = listEtablissements;
	}

	public List<SelectItem> getListAnneeAcademique() {
		return listAnneeAcademique;
	}

	public void setListAnneeAcademique(List<SelectItem> listAnneeAcademique) {
		this.listAnneeAcademique = listAnneeAcademique;
	}

	public List<SelectItem> getListDomaines() {
		return listDomaines;
	}

	public void setListDomaines(List<SelectItem> listDomaines) {
		this.listDomaines = listDomaines;
	}

	public List<SelectItem> getListFilieres() {
		return listFilieres;
	}

	public void setListFilieres(List<SelectItem> listFilieres) {
		this.listFilieres = listFilieres;
	}

	public List<SelectItem> getListMotifsRejet() {
		return listMotifsRejet;
	}

	public void setListMotifsRejet(List<SelectItem> listMotifsRejet) {
		this.listMotifsRejet = listMotifsRejet;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public void setCrud(boolean isCrud) {
		this.isCrud = isCrud;
	}

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public Integer getIdCurrAnneeAcademique() {
		return idCurrAnneeAcademique;
	}

	public void setIdCurrAnneeAcademique(Integer idCurrAnneeAcademique) {
		this.idCurrAnneeAcademique = idCurrAnneeAcademique;
	}

	public String getRefCodeCurrAnneeAcademique() {
		return refCodeCurrAnneeAcademique;
	}

	public void setRefCodeCurrAnneeAcademique(String refCodeCurrAnneeAcademique) {
		this.refCodeCurrAnneeAcademique = refCodeCurrAnneeAcademique;
	}

	public Integer getIdAnneeAcademique() {
		return idAnneeAcademique;
	}

	public void setIdAnneeAcademique(Integer idAnneeAcademique) {
		this.idAnneeAcademique = idAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 12:14:41
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			this.dossierTransfertDto = (DossierTransfertDto) event.getObject();

			this.notesBacheliersDtos = this.bacService
					.findByIdDossierBachelier(dossierTransfertDto
							.getBacMatricule());
			for (NotesBachelierDto noteBachelierDto : notesBacheliersDtos) {
				NomenclatureDto matiere = nomenclatureService.findByCode(
						CursusConstants.NC_MATIERES_BAC,
						noteBachelierDto.getRefCodeMatiere());
				noteBachelierDto.setRefCodeMatiereLibelleFr(matiere
						.getLibelleLongFr());
				noteBachelierDto.setRefCodeMatiereLibelleAr(matiere
						.getLibelleLongAr());
			}

			// historique des situations
			this.dossierTransfertHistory = situationService
					.getEntityOccurrenceHistory(
							UtilConstants.ENTITE_DOSSIER_TRANSFERT_ETUDIANT,
							this.dossierTransfertDto.getId());

			this.editDemandeTransfert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 17:07:59
	 */
	public void editDemandeTransfert() {
		this.isCrud = true;
		this.isView = true;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 17:07:56
	 */
	public void cancelDemandeTransfert() {
		this.resetDossiertransfertDto();
		this.isCrud = false;
		this.isView = false;
	}

	/**
	 * Listener sur le changement de la decision de transfert
	 * 
	 * @author Mounir.MESSAOUDI on : 27 ao�t 2014 15:28:11
	 */
	public void decisionTransfertChanged() {
		if (this.dossierTransfertDto.getIsEtabOrigineAccepte()) {
			this.dossierTransfertDto.setRefCodeMotifEtabOrigine(null);
		}
	}

	/**
	 * Enregister la decision prise sur la demande de transfert
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 10:41:46
	 */
	public void saveDemandeTransfertAction() {
		FacesMessage msg = new FacesMessage();
		try {

			// dossierTransfertDto
			// .setSituationId(situationService
			// .findBySituationEntiteByCodeSituation(
			// UtilConstants.ENTITE_DOSSIER_TRANSFERT_ETUDIANT,
			// UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_ENCOURS_TRAITMENT_GESTIONNAIRE_AC_CODE)
			// .getId());// 30
			this.dossierTransfertDto = dossierTransfertService
					.insertOrUpdate(this.dossierTransfertDto);

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_enregistrement"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		// this.resetDossiertransfertDto();
		this.loadSearchResults();
		// this.isCrud = false;
	}

	/**
	 * Valider la decision prise sur la demande de transfert
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 10:39:44
	 */
	public void validateDemandeTransfertAction() {
		FacesMessage msg = new FacesMessage();
		try {

			// si l'etablissement d'origine accepte
			if (dossierTransfertDto.getIsEtabOrigineAccepte()) {
				dossierTransfertDto
						.setSituationId(situationService
								.findBySituationEntiteByCodeSituation(
										UtilConstants.ENTITE_DOSSIER_TRANSFERT_ETUDIANT,
										UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_ENCOURS_TRAITMENT_COM_CODE)
								.getId());
				// si l'etablissement d'origine refuse
			} else {
				dossierTransfertDto
						.setSituationId(situationService
								.findBySituationEntiteByCodeSituation(
										UtilConstants.ENTITE_DOSSIER_TRANSFERT_ETUDIANT,
										UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_REJETEE_GESTIONNAIRE_OR_CODE)
								.getId());
				// demande non accordee
				dossierTransfertDto.setIsAccordee(Boolean.FALSE);
			}

			dossierTransfertService.insertOrUpdate(this.dossierTransfertDto);

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_validation"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		this.resetDossiertransfertDto();
		this.loadSearchResults();
		this.isCrud = false;
	}

	/**
	 * Envoyer la demande au responsable des transferts
	 * 
	 * @author Mounir.MESSAOUDI on : 25 ao�t 2014 11:09:24
	 */
	public void sendToResponsableAction() {
		FacesMessage msg = new FacesMessage();
		try {
			dossierTransfertDto
					.setSituationId(situationService
							.findBySituationEntiteByCodeSituation(
									UtilConstants.ENTITE_DOSSIER_TRANSFERT_ETUDIANT,
									UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_ENCOURS_TRAITMENT_COM_CODE)
							.getId());// 30
			dossierTransfertService.insertOrUpdate(this.dossierTransfertDto);

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_envoi"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		this.resetDossiertransfertDto();
		this.loadSearchResults();
		this.isCrud = false;
	}

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public List<SituationEntiteOccurrenceDto> getDossierTransfertHistory() {
		if (dossierTransfertHistory == null)
			dossierTransfertHistory = new ArrayList<SituationEntiteOccurrenceDto>();
		return dossierTransfertHistory;
	}

	public void setDossierTransfertHistory(
			List<SituationEntiteOccurrenceDto> dossierTransfertHistory) {
		this.dossierTransfertHistory = dossierTransfertHistory;
	}

	/**
	 * [DossierTransfertEtabOrigineMBean.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:25:58
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [DossierTransfertEtabOrigineMBean.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:25:58
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [DossierTransfertEtabOrigineMBean.refIndividuService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:25:58
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [DossierTransfertEtabOrigineMBean.refIndividuService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:25:58
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [DossierTransfertEtabOrigineMBean.refSpecialiteLmdService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:25:58
	 * @return the refSpecialiteLmdService
	 */
	public RefSpecialiteLmdService getRefSpecialiteLmdService() {
		return refSpecialiteLmdService;
	}

	/**
	 * [DossierTransfertEtabOrigineMBean.refSpecialiteLmdService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:25:58
	 * @param refSpecialiteLmdService the refSpecialiteLmdService to set
	 */
	public void setRefSpecialiteLmdService(
			RefSpecialiteLmdService refSpecialiteLmdService) {
		this.refSpecialiteLmdService = refSpecialiteLmdService;
	}

	/**
	 * [DossierTransfertEtabOrigineMBean.refFiliereLmdService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:25:58
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [DossierTransfertEtabOrigineMBean.refFiliereLmdService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:25:58
	 * @param refFiliereLmdService the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	/**
	 * [DossierTransfertEtabOrigineMBean.refDomaineLMDService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:25:58
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [DossierTransfertEtabOrigineMBean.refDomaineLMDService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:25:58
	 * @param refDomaineLMDService the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

}
