/**
 *  
 * @author Mounir.MESSAOUDI on : 18 oct. 2014 16:44:27
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.inscription;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanSessionService;
import dz.gov.mesrs.sii.fve.business.service.impression.ImpressionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.impression.PrintMgrBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;
import dz.gov.mesrs.sii.referentiel.business.util.ConfigHolder;

/**
 * Managed Bean pour gerer les dossiers d'inscription administrative des
 * etudiants suite a une - Inscription dans un nouveau cycle - Reinscription -
 * Reintegration
 * 
 * - Pre-inscription (etudiant etrange) - Transfert
 * 
 * @author Mounir.MESSAOUDI on : 18 oct. 2014 16:44:27
 */
@ManagedBean(name = "inscriptionAdministrativeMBean")
@ViewScoped
public class InscriptionAdministrativeMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Log log = LogFactory
			.getLog(InscriptionAdministrativeMBean.class);

	private ResourceBundle inscriptionAdministrativeBundle;
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

	@ManagedProperty("#{printMgrBean}")
	private PrintMgrBean printMgrBean;

	@ManagedProperty("#{impressionService}")
	private ImpressionService impressionService;

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

	@ManagedProperty("#{refGroupeServiceImpl}")
	RefGroupeService refGroupeService;
	
	@ManagedProperty("#{refCompteServiceImpl}")
	private RefCompteService refCompteService;

	@ManagedProperty("#{refAffectationServiceImpl}")
	RefAffectationService refAffectationService;
	
	@ManagedProperty("#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabService;

	@ManagedProperty("#{configHolder}")
	ConfigHolder configHolder;
	
	private dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto roleEtudiant;
	private DossierInscriptionAdministrativeDto dossierInscription;
	private List<DossierInscriptionAdministrativeDto> dossiersInscriptionList;
	private List<DossierEtudiantDto> dossiersEtudiantList;

	DossierInscriptionAdministrativeDto searchDossierInscriptionDto;

	private SituationEntiteDto situationValidee;
	private SituationEntiteDto situationEnregistrer;

	private List<SituationEntiteDto> situationsDto;

	@ManagedProperty(value = "#{bilanSessionService}")
	private BilanSessionService bilanSessionService;

	private DossierInscriptionAdministrativeDto ancienDossierInscriptionAdministrativeDto;
	private BilanSessionDto bilanSessionAnnuelDto;

	// Ui
	private Integer idCurrAnneeAcademique;
	private Integer idAnneeAcademique;

	private String refCodeCurrEtablissement;
	private Integer idCurrEtablissement;

	private List<SelectItem> listAnneeAcademique;

	private List<SelectItem> listeItemsTypesBourses;
	private List<SelectItem> listeItemsTypesHebergement;

	// Historique des situations
	private List<SituationEntiteOccurrenceDto> dossierInscriptionHistory;

	private boolean isView;
	private boolean isCrud;

	private String viewId; // +setter

	// UI Recherche
	private String searchByMatricule;
	private String searchByNom;
	private String searchByPrenom;
	private String refGroupeCode;

	private LazyDataModel<DossierInscriptionAdministrativeDto> dossiersInscAdminModel;

	public InscriptionAdministrativeMBean() {
		super();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		inscriptionAdministrativeBundle = facesContext.getApplication()
				.getResourceBundle(facesContext,
						CursusConstants.DOSSIER_INSCRIPTION_MSG_NAME);
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);

	}

	@PostConstruct
	public void init() {
		try {
			String url = FacesContext.getCurrentInstance().getViewRoot()
					.getViewId();
			viewId = url.substring(url.lastIndexOf("/") + 1,
					url.lastIndexOf("."));

			idCurrAnneeAcademique = sessionBean.getIdAnneeAcademique();
			idAnneeAcademique = idCurrAnneeAcademique;

			this.refCodeCurrEtablissement = sessionBean.getCodeEtablissement();

			situationValidee = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DOSSIER_CODE,
							UtilConstants.SITUTAION_VALIDEE_CODE);

			situationEnregistrer = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DOSSIER_CODE,
							UtilConstants.SITUTAION_ENREGISTREE_CODE);

			// edit mode?
			if (viewId.equals("InscriptionEdit")
					|| viewId.equals("DecisionOnouEdit")
					|| viewId.equals("DemandeOnouEdit")) {
				// listes
				listeItemsTypesBourses = new ArrayList<>();
				List<NomenclatureDto> typesBoursesDto = nomenclatureService
						.findByName(CursusConstants.NC_ONOU_TYPE_DEMANDE_BOURSE);
				if (typesBoursesDto != null) {
					for (NomenclatureDto nomenclatureDto : typesBoursesDto) {
						listeItemsTypesBourses.add(new SelectItem(
								nomenclatureDto.getId(), nomenclatureDto
										.getLibelleLongFr()));
					}
				}

				listeItemsTypesHebergement = new ArrayList<>();
				List<NomenclatureDto> typesHebergementDto = nomenclatureService
						.findByName(CursusConstants.NC_ONOU_TYPE_DEMANDE_HEBERGEMENT);
				if (typesHebergementDto != null) {
					for (NomenclatureDto nomenclatureDto : typesHebergementDto) {
						listeItemsTypesHebergement.add(new SelectItem(
								nomenclatureDto.getId(), nomenclatureDto
										.getLibelleLongFr()));
					}
				}

			} else {

			}

			loadSearchResults();

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 oct. 2014 18:06:37
	 */
	public void loadSearchResults() {
		try {
			searchDossierInscriptionDto = new DossierInscriptionAdministrativeDto();
			searchDossierInscriptionDto.setAnneeAcademiqueId(idAnneeAcademique);
			searchDossierInscriptionDto
					.setRefEtablissementId(idCurrEtablissement);

			situationsDto = new ArrayList<>();

			// edit mode ?
			if (viewId.equals("InscriptionEdit")) {
				SituationEntiteDto situationCree = situationService
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_DOSSIER_CODE,
								UtilConstants.SITUTAION_CREEE_CODE);

				SituationEntiteDto situationGen = situationService
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_DOSSIER_CODE,
								UtilConstants.SITUTAION_GENEREE_AUTO_CODE);

				situationsDto.add(situationCree);
				situationsDto.add(situationGen);

			} else if (viewId.equals("DecisionOnouEdit")) {
				// les dossiers d'inscriptions validee seulements
				SituationEntiteDto situationValidee = situationService
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_DOSSIER_CODE,
								UtilConstants.SITUTAION_VALIDEE_CODE);

				situationsDto.add(situationValidee);

				searchDossierInscriptionDto
						.setDecisionOnouValide(Boolean.FALSE);
			}

			// searchDossierInscriptionDto.setDossierEtudiantId(0);
			searchDossierInscriptionDto.setDossierEtudiantId(0);
			searchDossierInscriptionDto.setIndividuId(0);
			searchDossierInscriptionDto.setNumeroMatricule(searchByMatricule);
			searchDossierInscriptionDto.setIndividuNomLatin(searchByNom);
			searchDossierInscriptionDto.setIndividuPrenomLatin(searchByPrenom);
			searchDossierInscriptionDto.setIndividuNomArabe(searchByNom);
			searchDossierInscriptionDto.setIndividuPrenomArabe(searchByPrenom);

			// RefIndividuDto r = new RefIndividuDto();
			// r.setId(0);
			// r.setIdentifiant(searchByNIN);
			// r.setNomLatin(searchByNom);
			// r.setNomArabe(searchByNom);
			// r.setPrenomLatin(searchByPrenom);
			// r.setPrenomArabe(searchByPrenom);

			// DossierEtudiantDto d = new DossierEtudiantDto();
			// d.setId(0);
			// d.setIndividu(r);
			// searchDossierInscriptionDto.setdos

			// TODO recuperer tout les offres ouverte dans la structure de
			// l'utilisateur connecte
			// dossiersInscriptionList = dossierInscriptionAdministrativeService
			// .findAdvancedByOuvertureOffres(searchDossierInscriptionDto,
			// null, situationDtos);

			dossiersInscAdminModel = new LazyDataModel<DossierInscriptionAdministrativeDto>() {
				private static final long serialVersionUID = -3025574151722364485L;

				@Override
				public List<DossierInscriptionAdministrativeDto> load(
						int first, int pageSize, String sortField,
						SortOrder sortOrder, Map<String, String> filters) {
					Boolean descending = null;

					if (sortOrder != null) {
						if (sortOrder.equals(SortOrder.DESCENDING)) {
							descending = Boolean.TRUE;
						} else {
							descending = Boolean.FALSE;
						}

					}
					dossiersInscAdminModel
							.setRowCount(dossierInscriptionAdministrativeService
									.countByDia(searchDossierInscriptionDto,
											null, situationsDto).intValue());

					dossiersInscriptionList = dossierInscriptionAdministrativeService
							.findByDia(searchDossierInscriptionDto, null,
									situationsDto, sortField, pageSize, first,
									descending);
					try {
						for (DossierInscriptionAdministrativeDto d : dossiersInscriptionList) {
							if (d.getRefLibelleSpecialite() == null) {
								try {
									RefSpecialiteLmdDto _specialite = refSpecialiteLmdService
											.findByIdentifiant(d
													.getRefCodeSpecialite());

									if (_specialite != null) {
										d.setRefLibelleSpecialite(_specialite
												.getLlSpecialite());
										d.setRefLibelleSpecialiteArabe(_specialite
												.getLlSpecialiteArabe());
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

							if (d.getRefLibelleFiliere() == null) {
								try {
									RefFiliereLmdDto _filiere = refFiliereLmdService
											.findByIdentifiant(d
													.getRefCodeFiliere());
									if (_filiere != null) {
										d.setRefLibelleFiliere(_filiere
												.getLlFiliere());
										d.setRefLibelleFiliereArabe(_filiere
												.getLlFiliereArabe());
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

							if (d.getRefLibelleDomaine() == null) {
								try {
									RefDomaineLMDDto _domaine = refDomaineLMDService
											.findByIdentifiant(d
													.getRefCodeDomaine());
									if (_domaine != null) {
										d.setRefLibelleDomaine(_domaine
												.getLlDomaine());
										d.setRefLibelleDomaineArabe(_domaine
												.getLlDomaineArabe());
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

							}
						}

						if (dossiersInscriptionList != null
								&& dossiersInscriptionList.size() == 1) {
							// simuler un row select event
							SelectEvent event = new SelectEvent(null, null,
									dossiersInscriptionList.get(0));
							onRowSelect(event);
						}

					} catch (Exception e) {
						e.printStackTrace();
						Utility.showErrorMessage(e.getMessage());
						log.error(e.getMessage());
					}

					return dossiersInscriptionList;
				}
			};

			// for (DossierInscriptionAdministrativeDto d :
			// dossiersInscriptionList) {
			// if (d.getRefLibelleSpecialite() == null) {
			// RefSpecialiteLmdDto _specialite = ppmWSProxy
			// .findSpecialiteByIdentifiant(d
			// .getRefCodeSpecialite());
			//
			// if (_specialite != null) {
			// d.setRefLibelleSpecialite(_specialite.getLlSpecialite());
			// d.setRefLibelleSpecialiteArabe(_specialite
			// .getLlSpecialiteArabe());
			// }
			// }
			//
			// if (d.getRefLibelleFiliere() == null) {
			// RefFiliereLmdDto _filiere = ppmWSProxy
			// .findFiliereByIdentifiant(d.getRefCodeFiliere());
			// if (_filiere != null) {
			// d.setRefLibelleFiliere(_filiere.getLlFiliere());
			// d.setRefLibelleFiliereArabe(_filiere
			// .getLlFiliereArabe());
			// }
			// }
			//
			// if (d.getRefLibelleDomaine() == null) {
			// RefDomaineLMDDto _domaine = ppmWSProxy
			// .findDomaineLMDByIdentifiant(d.getRefCodeDomaine());
			// if (_domaine != null) {
			// d.setRefLibelleDomaine(_domaine.getLlDomaine());
			// d.setRefLibelleDomaineArabe(_domaine
			// .getLlDomaineArabe());
			// }
			// }
			// }
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * Action de recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 28 oct. 2014 11:06:28
	 */
	public void searchAction() {
		// TODO Important: Fix le probleme de de recherche apres avoir faire la
		// pagination (first = toujours la page en cours)
		// http://stackoverflow.com/questions/5616943/reset-primefaces-pagination-selected-page-on-ajax-request
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot()
				.findComponent("form_search:ResultSearchDataTable");
		dataTable.setFirst(0);
		// dataTable.reset();

		loadSearchResults();
		isCrud = false;
		isView = false;
	}

	/**
	 * Action de recherche avancee
	 * 
	 * @author Mounir.MESSAOUDI on : 28 oct. 2014 11:06:43
	 */
	public void advancedSearchAction() {

	}

	public void onRowSelect(SelectEvent event) {
		try {
			dossierInscription = (DossierInscriptionAdministrativeDto) event
					.getObject();

			// libelle type de dossier
			NomenclatureDto n = nomenclatureService.findByCode(
					CursusConstants.NC_TYPE_INSCRIPTION_REINSCRIPTION,
					dossierInscription.getTypeDossier());
			if (n != null) {
				dossierInscription
						.setTypeDossierLibelleFr(n.getLibelleLongFr());
				dossierInscription
						.setTypeDossierLibelleAr(n.getLibelleLongAr());
			}

			ancienDossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
					.findPrecdentDossierInscriptionForEtudiant(dossierInscription
							.getDossierEtudiantId());

			if (ancienDossierInscriptionAdministrativeDto != null) {
				// situation de la derniere inscription ?
				bilanSessionAnnuelDto = bilanSessionService
						.findBilanFinalByIdDossierInscrAdmin(ancienDossierInscriptionAdministrativeDto
								.getId());
			} else {
				ancienDossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto();
				bilanSessionAnnuelDto = new BilanSessionDto();
			}

			// historique des situations
			dossierInscriptionHistory = situationService
					.getEntityOccurrenceHistory(
							UtilConstants.ENTITE_DOSSIER_CODE,
							dossierInscription.getDossierId());

			this.isCrud = true;
			this.isView = true;

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * Enregistrer les modifications effectue sur le dossier d'inscription
	 * 
	 * @author Mounir.MESSAOUDI on : 2 nov. 2014 08:07:49
	 */
	public void saveDossierInscriptionAction() {
		FacesMessage msg = new FacesMessage();
		try {

			dossierInscription = dossierInscriptionAdministrativeService
					.insertOrUpdate(dossierInscription);

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_enregistrement"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * Valider le dossier d'inscription
	 * 
	 * @author Mounir.MESSAOUDI on : 20 oct. 2014 11:27:10
	 */
	public void validateDossierInscriptionAction() {
		FacesMessage msg = new FacesMessage();
		try {
			SituationEntiteDto situationValidee = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DOSSIER_CODE,
							UtilConstants.SITUTAION_VALIDEE_CODE);

			dossierInscription.setSituationId(situationValidee.getId());
			dossierInscription = dossierInscriptionAdministrativeService
					.insertOrUpdate(dossierInscription);

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success")
					+ ": "
					+ inscriptionAdministrativeBundle
							.getString("dossier_inscription_msg_success_validation"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}

		isCrud = false;
		loadSearchResults();
	}

	/**
	 * Verifier si l'etudiant est affecte a un groupe
	 * 
	 * @author Mounir.MESSAOUDI on : 23 oct. 2014 14:14:59
	 * @return
	 */
	private boolean affecterEtudiantAuGroupe() {
		try {
			if (dossierInscription.getIndividuId() == null) {
				Utility.showErrorMessage(inscriptionAdministrativeBundle
						.getString("dossier_inscription_individu_required"));
				return false;
			}

			roleEtudiant = nomenclatureService.findByCode(Const.NC_ROLE,
					Const.REF_CODE_ROLE_ETUDIANT);
			if (roleEtudiant == null) {
				Utility.showErrorMessage(inscriptionAdministrativeBundle
						.getString("dossier_inscription_role_etudiant_required"));
				return false;
			}
			dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto searchDto = new dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto();
			searchDto.setIdEtablissement(sessionBean.getIdEtablissement());
			
			if (refGroupeCode == null || refGroupeCode.isEmpty()) {
				refGroupeCode = refParametreEtabService.findValueOfKey(
						sessionBean.getIdEtablissement(),
						Const.PARAM_FVE_CODE_GROUPE_BACHELIERS_KEY);
				if (refGroupeCode == null || refGroupeCode.isEmpty()) {
					Utility.showErrorMessage(inscriptionAdministrativeBundle
							.getString("dossier_inscription_groupe_etudiant_required"));
					return false;
				}
			}
			searchDto.setIdentifiant(refGroupeCode);
			List<dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto> groupeList = refGroupeService
					.findAdvanced(searchDto);

			if (groupeList == null || groupeList.isEmpty()) {
				Utility.showErrorMessage(inscriptionAdministrativeBundle
						.getString("dossier_inscription_groupe_etudiant_required"));
				return false;
			}
			dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto groupeEtudiant = groupeList
					.get(0);

			dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto refAffectationDto = new dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto();
			refAffectationDto.setDateDebut(new Date());
			refAffectationDto.setIdGroupe(groupeEtudiant.getId());
			refAffectationDto.setIdIndividu(dossierInscription.getIndividuId());
			refAffectationDto.setRoleId(roleEtudiant.getId());
			refAffectationDto = refAffectationService
					.saveOrUpdate(refAffectationDto);
			return true;

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
			return false;
		}

	}

	/**
	 * Generer le compte pour l'etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 23 oct. 2014 08:10:30
	 */
	public void generateCompteAction() {
		try {
			if (situationValidee.getId() != dossierInscription.getSituationId()) {
				Utility.showErrorMessage(inscriptionAdministrativeBundle
						.getString("dossier_inscription_erreur_inscription_non_valide"));
				return;
			}
			RefCompteDto refCompteDto = refCompteService
					.findByUser(dossierInscription.getIndividuId());
			if (refCompteDto == null) {
				/* l'etudiant n'a pas encore un compte */
				if (affecterEtudiantAuGroupe()) {
					if (dossierInscription.getNin() == null) {
						dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto refIndividuDto = refIndividuService
								.findByIdentifiant(dossierInscription.getNin());

						dossierInscription.setNin(refIndividuDto
								.getIdentifiant());
					}
					if (dossierInscription.getIndividuDateNaissance() == null) {
						Utility.showErrorMessage(inscriptionAdministrativeBundle
								.getString("dossier_inscription_date_naissance_required"));
						return;
					}

					refCompteDto = new RefCompteDto();
					refCompteDto.setIndividuId(dossierInscription
							.getIndividuId());
					refCompteDto.setIndividuNomLatin(dossierInscription
							.getIndividuNomLatin());
					refCompteDto.setIndividuPrenomLatin(dossierInscription
							.getIndividuPrenomLatin());
					refCompteDto.setAccessJourFerie(false);
					refCompteDto.setPremiereSession(false);
					refCompteDto.setAdmin(false);
					refCompteDto.setActivate(true);
					refCompteDto.setChangementMotPasse(true);
					refCompteDto.setEtabId(sessionBean.getIdEtablissement());

					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					String dateNaissance = df.format(dossierInscription
							.getIndividuDateNaissance());
					refCompteDto.setMotPasse(dateNaissance.replaceAll("/", ""));
					refCompteDto.setConfirmationMotPasse(refCompteDto
							.getMotPasse());
					refCompteDto.setDateDebut(new Date());
					refCompteService.insertOrUpdate(refCompteDto);
					refCompteDto = refCompteService
							.findByUser(dossierInscription
									.getIndividuId());
					refCompteDto.setMotPasse(dateNaissance.replaceAll("/", ""));

				}
			}

			// imprimer le compte
			Collection<RefCompteDto> comptes = new ArrayList<>();
			comptes.add(refCompteDto);
			String name = "compte_" + refCompteDto.getIndividuNomLatin() + "_"
					+ refCompteDto.getIndividuPrenomLatin();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext
					.getExternalContext().getRealPath("WEB-INF")
					+ "/classes/jasper/lettre_information_compte.jrxml";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("IMG_PAPS", facesContext.getExternalContext()
					.getRealPath("images") + "/logopaps.png");
			params.put("IMG_LOGO",
					facesContext.getExternalContext().getRealPath("images")
							+ "/logo" + sessionBean.getCodeEtablissement()
							+ ".png");
			byte[] bytes = impressionService.viewPDFWithDataSource(
					RESOURCE_PATH_TO_INPUT_FILE_JASPER, params, comptes);
			printMgrBean.imprimer(bytes, name, "pdf");

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * Imprimer l'attestation d'inscription
	 * 
	 * @author Mounir.MESSAOUDI on : 23 oct. 2014 08:11:52
	 */
	public void printAttestationAction() {
		try {
			Collection<DossierInscriptionAdministrativeDto> etudiants = new ArrayList<>();

			etudiants.add(dossierInscription);
			printMgrBean.viewAttestationPDF(
					etudiants,
					"attestationinscription_"
							+ dossierInscription.getNumeroInscription());
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * Imprimer la lettre d'engagement
	 * 
	 * @author Mounir.MESSAOUDI on : 23 oct. 2014 08:12:45
	 */
	public void printLettreEngagementAction() {
		try {
			Collection<DossierInscriptionAdministrativeDto> etudiants = new ArrayList<>();

			etudiants.add(dossierInscription);
			printMgrBean.viewLettreEngagementPDF(
					etudiants,
					"lettre_engagement_"
							+ dossierInscription.getNumeroInscription());
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * Imprimer le badge
	 * 
	 * @author Mounir.MESSAOUDI on : 23 oct. 2014 08:13:41
	 */
	public void printBadgeAction() {
		try {
			Collection<DossierInscriptionAdministrativeDto> etudiants = new ArrayList<>();

			etudiants.add(dossierInscription);

			String name = "badge_" + dossierInscription.getNumeroInscription();

			FacesContext facesContext = FacesContext.getCurrentInstance();

			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext
					.getExternalContext().getRealPath(
							"/WEB-INF/classes/jasper/badge.jrxml");
			Map<String, Object> params = new HashMap<String, Object>();

			String url = dossierInscription.getPhoto();
			String photo = null;

			if (url != null) {
				String folder_photo = configHolder.getPhotoUploadFolder() + "/";
				photo = folder_photo + url;
			} else {
				ServletContext scontext = (ServletContext) facesContext
						.getExternalContext().getContext();
				photo = scontext.getRealPath("/resources/images/boy.png");
			}

			// ServletContext scontext = (ServletContext) facesContext
			// .getExternalContext().getContext();
			// photo = scontext.getRealPath("/resources/images/boy.png");

			params.put("PHOTO_ETUDIANT", photo);

			params.put("IMG_PAPS", facesContext.getExternalContext()
					.getRealPath("images") + "/logopaps.png");
			params.put("BADGE_BACKGROUND", facesContext.getExternalContext()
					.getRealPath("images") + "/badge_bg.png");

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

	/**
	 * Annuler l'operation en cours sur le dossier d'inscription
	 * 
	 * @author Mounir.MESSAOUDI on : 20 oct. 2014 11:27:39
	 */
	public void cancelDossierInscriptionAction() {
		dossierInscription = null;
		dossierInscriptionHistory = null;
		bilanSessionAnnuelDto = null;

		isCrud = false;
		isView = false;

	}

	// --------------------------------------
	// service ONOU

	/**
	 * Enregistrer la decision du service ONOU
	 * 
	 * @author Mounir.MESSAOUDI on : 1 nov. 2014 10:48:01
	 */
	public void saveDecisionONOUAction() {
		FacesMessage msg = new FacesMessage();
		try {
			dossierInscriptionAdministrativeService
					.insertOrUpdate(dossierInscription);

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_enregistrement"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
		loadSearchResults();
		// isCrud = false;
	}

	/**
	 * Valider la decision du service ONOU
	 * 
	 * @author Mounir.MESSAOUDI on : 1 nov. 2014 10:48:29
	 */
	public void validateDecisionONOUAction() {
		FacesMessage msg = new FacesMessage();
		try {
			dossierInscription.setDecisionOnouValide(Boolean.TRUE);
			dossierInscription.setDateDecisionOnou(new Date());
			dossierInscriptionAdministrativeService
					.insertOrUpdate(dossierInscription);

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_validation"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
		loadSearchResults();
		isCrud = false;
	}

	public List<DossierInscriptionAdministrativeDto> getDossiersInscriptionList() {
		return dossiersInscriptionList;
	}

	public void setDossiersInscriptionList(
			List<DossierInscriptionAdministrativeDto> dossiersInscriptionList) {
		this.dossiersInscriptionList = dossiersInscriptionList;
	}

	public List<DossierEtudiantDto> getDossiersEtudiantList() {
		return dossiersEtudiantList;
	}

	public void setDossiersEtudiantList(
			List<DossierEtudiantDto> dossiersEtudiantList) {
		this.dossiersEtudiantList = dossiersEtudiantList;
	}

	public Integer getIdCurrEtablissement() {
		return idCurrEtablissement;
	}

	public void setIdCurrEtablissement(Integer idCurrEtablissement) {
		this.idCurrEtablissement = idCurrEtablissement;
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

	public DossierInscriptionAdministrativeDto getDossierInscription() {
		return dossierInscription;
	}

	public DossierInscriptionAdministrativeDto getAncienDossierInscriptionAdministrativeDto() {
		return ancienDossierInscriptionAdministrativeDto;
	}

	public void setAncienDossierInscriptionAdministrativeDto(
			DossierInscriptionAdministrativeDto ancienDossierInscriptionAdministrativeDto) {
		this.ancienDossierInscriptionAdministrativeDto = ancienDossierInscriptionAdministrativeDto;
	}

	public void setDossierInscription(
			DossierInscriptionAdministrativeDto dossierInscription) {
		this.dossierInscription = dossierInscription;
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

	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	public SituationService getSituationService() {
		return situationService;
	}

	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	public RefGroupeService getRefGroupeService() {
		return refGroupeService;
	}

	public void setRefGroupeService(RefGroupeService refGroupeService) {
		this.refGroupeService = refGroupeService;
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

	public RefAffectationService getRefAffectationService() {
		return refAffectationService;
	}

	public void setRefAffectationService(
			RefAffectationService refAffectationService) {
		this.refAffectationService = refAffectationService;
	}

	public void setConfigHolder(ConfigHolder configHolder) {
		this.configHolder = configHolder;
	}

	public ConfigHolder getConfigHolder() {
		return configHolder;
	}

	public ImpressionService getImpressionService() {
		return impressionService;
	}

	public void setImpressionService(ImpressionService impressionService) {
		this.impressionService = impressionService;
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

	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	public PrintMgrBean getPrintMgrBean() {
		return printMgrBean;
	}

	public void setPrintMgrBean(PrintMgrBean printMgrBean) {
		this.printMgrBean = printMgrBean;
	}

	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	public List<SituationEntiteOccurrenceDto> getDossierInscriptionHistory() {
		return dossierInscriptionHistory;
	}

	public void setDossierInscriptionHistory(
			List<SituationEntiteOccurrenceDto> dossierInscriptionHistory) {
		this.dossierInscriptionHistory = dossierInscriptionHistory;
	}
	public String getSearchByMatricule() {
		return searchByMatricule;
	}

	public void setSearchByMatricule(String searchByMatricule) {
		this.searchByMatricule = searchByMatricule;
	}

	public String getSearchByNom() {
		return searchByNom;
	}

	public void setSearchByNom(String searchByNom) {
		this.searchByNom = searchByNom;
	}

	public String getSearchByPrenom() {
		return searchByPrenom;
	}

	public void setSearchByPrenom(String searchByPrenom) {
		this.searchByPrenom = searchByPrenom;
	}

	public LazyDataModel<DossierInscriptionAdministrativeDto> getDossiersInscAdminModel() {
		return dossiersInscAdminModel;
	}

	public void setDossiersInscAdminModel(
			LazyDataModel<DossierInscriptionAdministrativeDto> dossiersInscAdminModel) {
		this.dossiersInscAdminModel = dossiersInscAdminModel;
	}

	public List<SelectItem> getListeItemsTypesBourses() {
		return listeItemsTypesBourses;
	}

	public List<SelectItem> getListAnneeAcademique() {
		return listAnneeAcademique;
	}

	public void setListAnneeAcademique(List<SelectItem> listAnneeAcademique) {
		this.listAnneeAcademique = listAnneeAcademique;
	}

	public void setListeItemsTypesBourses(
			List<SelectItem> listeItemsTypesBourses) {
		this.listeItemsTypesBourses = listeItemsTypesBourses;
	}

	public List<SelectItem> getListeItemsTypesHebergement() {
		return listeItemsTypesHebergement;
	}

	public void setListeItemsTypesHebergement(
			List<SelectItem> listeItemsTypesHebergement) {
		this.listeItemsTypesHebergement = listeItemsTypesHebergement;
	}

	/**
	 * [InscriptionAdministrativeMBean.refSpecialiteLmdService] Getter 
	 * @author rlaib on : 20 nov. 2014  15:57:05
	 * @return the refSpecialiteLmdService
	 */
	public RefSpecialiteLmdService getRefSpecialiteLmdService() {
		return refSpecialiteLmdService;
	}

	/**
	 * [InscriptionAdministrativeMBean.refSpecialiteLmdService] Setter 
	 * @author rlaib on : 20 nov. 2014  15:57:05
	 * @param refSpecialiteLmdService the refSpecialiteLmdService to set
	 */
	public void setRefSpecialiteLmdService(
			RefSpecialiteLmdService refSpecialiteLmdService) {
		this.refSpecialiteLmdService = refSpecialiteLmdService;
	}

	/**
	 * [InscriptionAdministrativeMBean.refFiliereLmdService] Getter 
	 * @author rlaib on : 20 nov. 2014  15:58:01
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [InscriptionAdministrativeMBean.refFiliereLmdService] Setter 
	 * @author rlaib on : 20 nov. 2014  15:58:01
	 * @param refFiliereLmdService the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	/**
	 * [InscriptionAdministrativeMBean.refDomaineLMDService] Getter 
	 * @author rlaib on : 20 nov. 2014  15:58:57
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [InscriptionAdministrativeMBean.refDomaineLMDService] Setter 
	 * @author rlaib on : 20 nov. 2014  15:58:57
	 * @param refDomaineLMDService the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	/**
	 * [InscriptionAdministrativeMBean.refCompteService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:00:25
	 * @return the refCompteService
	 */
	public RefCompteService getRefCompteService() {
		return refCompteService;
	}

	/**
	 * [InscriptionAdministrativeMBean.refCompteService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:00:25
	 * @param refCompteService the refCompteService to set
	 */
	public void setRefCompteService(RefCompteService refCompteService) {
		this.refCompteService = refCompteService;
	}

	/**
	 * [InscriptionAdministrativeMBean.refParametreEtabService] Getter 
	 * @author MAKERRI Sofiane on : 7 déc. 2014  15:03:20
	 * @return the refParametreEtabService
	 */
	public RefParametreEtabService getRefParametreEtabService() {
		return refParametreEtabService;
	}

	/**
	 * [InscriptionAdministrativeMBean.refParametreEtabService] Setter 
	 * @author MAKERRI Sofiane on : 7 déc. 2014  15:03:20
	 * @param refParametreEtabService the refParametreEtabService to set
	 */
	public void setRefParametreEtabService(
			RefParametreEtabService refParametreEtabService) {
		this.refParametreEtabService = refParametreEtabService;
	}

	/**
	 * [InscriptionAdministrativeMBean.refGroupeCode] Getter 
	 * @author MAKERRI Sofiane on : 7 déc. 2014  15:04:10
	 * @return the refGroupeCode
	 */
	public String getRefGroupeCode() {
		return refGroupeCode;
	}

	/**
	 * [InscriptionAdministrativeMBean.refGroupeCode] Setter 
	 * @author MAKERRI Sofiane on : 7 déc. 2014  15:04:10
	 * @param refGroupeCode the refGroupeCode to set
	 */
	public void setRefGroupeCode(String refGroupeCode) {
		this.refGroupeCode = refGroupeCode;
	}
	
	
	

}
