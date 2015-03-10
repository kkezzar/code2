package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.transfert;

import java.io.Serializable;
import java.text.MessageFormat;
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
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.SelectEvent;
import org.springframework.orm.jpa.JpaSystemException;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.AccessWilayaDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.CapaciteEtabDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.DossierBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.DroitAccessWilayaDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.NoteAccessFiliereDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.NotesBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.PrioriteSerieBacDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierTransfertDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.bac.AccessWilayaService;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.service.bac.CapaciteEtabService;
import dz.gov.mesrs.sii.fve.business.service.bac.NoteAccessFiliereService;
import dz.gov.mesrs.sii.fve.business.service.bac.PrioriteSerieBacService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierTransfertService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
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
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;

@ManagedBean(name = "dossierTransfertEtudiantMBean")
@ViewScoped
public class DossierTransfertEtudiantMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DossierTransfertEtudiantMBean.class);

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

	@ManagedProperty("#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabService;

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

	@ManagedProperty(value = "#{accessWilayaService}")
	private AccessWilayaService accessWilayaService;

	@ManagedProperty(value = "#{capaciteEtabService}")
	private CapaciteEtabService capaciteEtabService;

	@ManagedProperty(value = "#{prioriteSerieBacService}")
	private PrioriteSerieBacService prioriteSerieBacService;

	@ManagedProperty(value = "#{noteAccessFiliereService}")
	private NoteAccessFiliereService noteAccessFiliereService;

	private Boolean conditionSerieBac = null;
	private Boolean conditionMoyenneBac = null;
	private Boolean conditionNotesMatieresBac = null;
	private Boolean conditionMoyenneCalculeeMatieresBac = null;
	private Boolean conditionDroitAccessWilaya = null;
	private Boolean conditionsAccess = false;

	/**
	 * Le dossier du transfert en cours de traitement
	 */
	private DossierTransfertDto dossierTransfertDto;
	private DossierEtudiantDto dossierEtudiantDto;
	private DossierBachelierDto dossierBachelierDto;
	private DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto;
	private List<NotesBachelierDto> notesBacheliersDtos;

	private String refCodeEtabOrigine;

	// Ui
	private Integer idCurrAnneeAcademique;
	private String refCodeCurrAnneeAcademique;
	private Integer idAnneeAcademique;

	private List<SelectItem> listTypesTransfert;
	private List<SelectItem> listEtablissements;
	private List<SelectItem> listAnneeAcademique;
	private List<SelectItem> listDomaines;
	private List<SelectItem> listFilieres;
	private List<SelectItem> listMotifsTransfert;

	private boolean isCrud;
	private boolean isView;

	private String viewId; // +setter

	private Long nbrMaxDemandesTransferts;
	private Long nbrDemandesCrees;
	private boolean autoriseACree;

	private String dossTrsftMsgCondDroitAccessWilaya;
	private String dossTrsftMsgCondSerieBac;
	private String dossTrsftMsgCondMoyenneBac;
	private String dossTrsftMsgCondNotesMatieresBac;
	private String dossTrsftMsgCondMoyenneCalculeeMatieresBac;

	public DossierTransfertEtudiantMBean() {
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
			String url = FacesContext.getCurrentInstance().getViewRoot().getViewId();
			this.viewId = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));

			this.idCurrAnneeAcademique = sessionBean.getIdAnneeAcademique();
			this.refCodeCurrAnneeAcademique = sessionBean.getCodeAnneeAcademique();
			this.idAnneeAcademique = this.idCurrAnneeAcademique;

			Integer individuId = this.sessionBean.getSessionBean().getUser().getId();
			List<DossierEtudiantDto> list = dossierEtudiantService.findByIdIndividu(individuId, null);
			if (list != null && !list.isEmpty()) {
				this.dossierEtudiantDto = list.get(0);
				this.dossierBachelierDto = bacService.findBachelorFileById(dossierEtudiantDto.getDossierBachelierId());

				this.dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
						.findPresentDossierInscriptionForEtudiant(dossierEtudiantDto.getId());
			}

			// List<DossierInscriptionAdministrativeDto>
			// listDossierInscriptionAdministrativeDtos =
			// dossierInscriptionAdministrativeService
			// .findDossierInscriptionsBy(dossierEtudiantDto.getId());
			// if (listDossierInscriptionAdministrativeDtos != null
			// && !listDossierInscriptionAdministrativeDtos.isEmpty()) {
			// this.dossierInscriptionAdministrativeDto =
			// listDossierInscriptionAdministrativeDtos
			// .get(0);
			// }

			initUI();
			resetDossiertransfertDto();
			loadSearchResults();

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
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
			transfertBundle = facesContext.getApplication().getResourceBundle(facesContext,
					CursusConstants.DOSSIER_TRANSFERT_BUNDLE_MSG_NAME);
			bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
					CursusConstants.COMMON_BUNDLE_MSG_NAME);

			if (viewId.equals("DemandeTransfertEtudiantEdit")) {

				// motifs de transfert
				listMotifsTransfert = new ArrayList<>();
				List<NomenclatureDto> _motifTrsf = nomenclatureService
						.findByName(CursusConstants.NC_MOTIF_DEMANDE_TRANSFERT);
				for (NomenclatureDto item : _motifTrsf) {
					listMotifsTransfert.add(new SelectItem(item.getCode(), item.getLibelleLongFr()));
				}

				// types de transfert
				listTypesTransfert = new ArrayList<>();
				List<NomenclatureDto> _typeAps = nomenclatureService
						.findByName(CursusConstants.NC_TYPE_DEMANDE_TRANSFERT);
				for (NomenclatureDto item : _typeAps) {
					listTypesTransfert.add(new SelectItem(item.getCode(), item.getLibelleLongFr()));
				}

				// preparer la liste des etablissement
				listEtablissements = new ArrayList<>();

				List<RefEtablissementDto> list = refEtablissementService.findGeneric("%");
				for (RefEtablissementDto refEtablissementDto : list) {
					// if (!refEtablissementDto.getIdentifiant().equals(
					// dossierTransfertDto.getRefCodeEtabOrigine())) {
					SelectItem selectItem = new SelectItem(refEtablissementDto.getIdentifiant(),
							refEtablissementDto.getLlEtablissementLatin());
					listEtablissements.add(selectItem);
					// }
				}

				// preparer la liste des domaines
				listDomaines = new ArrayList<>();

				List<RefDomaineLMDDto> listDomainesDto = refDomaineLMDService.findAdvanced(new RefDomaineLMDDto());
				for (RefDomaineLMDDto ld : listDomainesDto) {
					SelectItem itemFr = new SelectItem(ld.getIdentifiant(), ld.getLlDomaine() + " ("
							+ ld.getIdentifiant() + ")");
					listDomaines.add(itemFr);
				}

			} else {
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
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:55:32
	 */
	private void resetDossiertransfertDto() {

		try {

			this.conditionSerieBac = null;
			this.conditionMoyenneBac = null;
			this.conditionNotesMatieresBac = null;
			this.conditionMoyenneCalculeeMatieresBac = null;
			this.conditionDroitAccessWilaya = null;
			this.conditionsAccess = false;

			this.dossierTransfertDto = null;
			this.dossierTransfertDto = new DossierTransfertDto();
			this.dossierTransfertDto.setDateDepot(new Date());
			this.dossierTransfertDto.setAnneeAcademiqueId(this.idCurrAnneeAcademique);

			// TODO
			// this.dossierTransfertDto.setRefCodeEtabOrigine(dossierBachelierDto.getRefCodeEtablissement());

			if (this.dossierInscriptionAdministrativeDto.getAnneeAcademiqueId().equals(this.idCurrAnneeAcademique))
				// Etablissement d'origine
				this.dossierTransfertDto.setNouveauBachelier(true);
			else
				this.dossierTransfertDto.setNouveauBachelier(false);

			// Etablissement d'origine
			this.dossierTransfertDto.setRefCodeEtabOrigine(this.dossierEtudiantDto.getRefCodeEtablissement());

			// dossierTransfertDto
			// .setRefCodeEtabOrigine(this.dossierInscriptionAdministrativeDto
			// .getRefCodeEtablissement());
			RefEtablissementDto refEtablissementDto = refEtablissementService
					.findByIdentifiant(this.dossierTransfertDto.getRefCodeEtabOrigine());
			dossierTransfertDto.setRefLibelleEtabOrigine(refEtablissementDto.getLlEtablissementLatin());

			// this.dossierTransfertDto
			// .setRefCodeDomaineOrigine(this.dossierBachelierDto
			// .getCodeFiliereInscription());
			//
			// domaine/Filiere origine
			this.dossierTransfertDto.setRefCodeDomaineOrigine(this.dossierInscriptionAdministrativeDto
					.getResultRefCodeDomaine());

			// TODO ref_code_filiere ref_code_specialite doivent etre null dans
			// la table lmd.offre_formation
			// quand l'offre conserne un domain
			// this.dossierTransfertDto
			// .setRefCodeFiliereOrigine(this.dossierInscriptionAdministrativeDto
			// .getResultRefCodeFiliere());

			this.dossierTransfertDto.setRefCodeFiliereOrigine(this.dossierInscriptionAdministrativeDto
					.getRefCodeFiliere());

			// recuper les libelles du domaine/Filiere origine
			if (this.dossierTransfertDto.getRefCodeDomaineOrigine() != null) {
				RefDomaineLMDDto rdd = refDomaineLMDService.findByIdentifiant(dossierTransfertDto
						.getRefCodeDomaineOrigine());
				this.dossierTransfertDto.setDomaineOrigineLibelleFr(rdd.getLlDomaine());
				this.dossierTransfertDto.setDomaineOrigineLibelleAr(rdd.getLlDomaineArabe());
			}

			if (this.dossierTransfertDto.getRefCodeFiliereOrigine() != null) {
				RefFiliereLmdDto rfd = refFiliereLmdService.findByIdentifiant(dossierTransfertDto
						.getRefCodeFiliereOrigine());
				if (rfd != null) {
					this.dossierTransfertDto.setFiliereOrigineLibelleFr(rfd.getLlFiliere());
					this.dossierTransfertDto.setFiliereOrigineLibelleAr(rfd.getLlFiliereArabe());

					// si l'etudiant a ete affecte a une filiere , recuperer le
					// code
					// domaine de la filiere
					if (this.dossierTransfertDto.getRefCodeDomaineOrigine() == null) {
						this.dossierTransfertDto.setDomaineOrigineLibelleFr(rfd.getLlDomaine());
						this.dossierTransfertDto.setDomaineOrigineLibelleAr(rfd.getLlDomaineArabe());
					}
				}

			}

			this.dossierTransfertDto.setDossierInscriptionId(this.dossierInscriptionAdministrativeDto.getId());
			// this.dossierTransfertDto.setDossierEtudiantId(this.dossierEtudiantDto.getId());
			// this.dossierTransfertDto.setBacId(this.dossierBachelierDto.getId());

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
	 * * Charger les demandes de transfert de l'etudiant en cours
	 * 
	 * @author Mounir.MESSAOUDI on : 12 ao�t 2014 18:02:42
	 */
	private void loadSearchResults() {

		RefIndividuDto refIndividuDto = this.sessionBean.getSessionBean().getUser();
		dossierTransfertResultSearch = new ArrayList<DossierTransfertDto>();

		try {
			if (refIndividuDto != null) {
				if (dossierTransfertSearchDto == null) {
					dossierTransfertSearchDto = new DossierTransfertDto();
				}

				// les demandes de transfert de l'etudiant en cours et l'annee
				// accamdemique idAnneeAcademique
				dossierTransfertSearchDto.setIndividuNin(refIndividuDto.getIdentifiant());
				dossierTransfertSearchDto.setAnneeAcademiqueId(this.idAnneeAcademique);

				// TODO situation
				// List<SituationDto> situationDtos = new ArrayList<>();
				// SituationDto s = new SituationDto();
				// s.setId(44);
				List<DossierTransfertDto> dossierTransferts;
				if (viewId.equals("DemandeTransfertEtudiantEdit")) {

					DossierTransfertDto dtDto = new DossierTransfertDto();
					dtDto.setAnneeAcademiqueId(this.idAnneeAcademique);
					dtDto.setIndividuNin(refIndividuDto.getIdentifiant());
					dtDto.setDossierInscriptionId(this.dossierInscriptionAdministrativeDto.getId());
					// recuperer le nombre de demandes crees par l'etudiant
					this.nbrDemandesCrees = dossierTransfertService.findCountDossiersTransfert(dtDto);

					// recuperer le nombre de demandes max a cree par l'etudiant
					// dans son etablissement
					this.nbrMaxDemandesTransferts = Long.valueOf(refParametreEtabService.findByKeyEtab(
							this.dossierTransfertDto.getRefCodeEtabOrigine(),
							UtilConstants.ETABLISSEMENT_MAX_DEMANDES_TRANSFERT).getValue());
					if (this.nbrDemandesCrees < this.nbrMaxDemandesTransferts)
						this.autoriseACree = true;
					else
						this.autoriseACree = false;

					List<SituationEntiteDto> situationDtos = new ArrayList<>();
					SituationEntiteDto situationEnregistreEtudiant = situationService
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_DOSSIER_TRANSFERT_ETUDIANT,
									UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_ENREGISTREE_CODE);
					situationDtos.add(situationEnregistreEtudiant);

					dossierTransferts = dossierTransfertService.findAdvanced(dossierTransfertSearchDto, situationDtos);

				} else {
					dossierTransferts = dossierTransfertService.findAdvanced(dossierTransfertSearchDto);
				}

				// etablissmenet origine
				RefEtablissementDto refEtablissementDto = refEtablissementService
						.findById(dossierInscriptionAdministrativeDto.getRefEtablissementId());

				if (dossierTransferts != null && !dossierTransferts.isEmpty()) {

					//

					for (DossierTransfertDto dossierTransfertDto : dossierTransferts) {
						// loadInfosFilieres(dossierTransfertDto);
						// map(refIndividuDto, dossierTransfertDto);
						map(refIndividuDto, dossierTransfertDto);

						// motif de la demande
						NomenclatureDto motifTransfertDto = nomenclatureService.findByCode(
								CursusConstants.NC_MOTIF_DEMANDE_TRANSFERT, dossierTransfertDto.getRefCodeMotif());
						dossierTransfertDto.setRefCodeMotifLibelleFr(motifTransfertDto.getLibelleLongFr());
						dossierTransfertDto.setRefCodeMotifLibelleAr(motifTransfertDto.getLibelleLongAr());

						// Type de la demande
						NomenclatureDto typeDemandeTransfertDto = nomenclatureService.findByCode(
								CursusConstants.NC_TYPE_DEMANDE_TRANSFERT,
								dossierTransfertDto.getRefCodeTypeTransfert());
						dossierTransfertDto.setRefLibelleTransfert(typeDemandeTransfertDto.getLibelleLongFr());

						// Etablissement d'origine
						dossierTransfertDto.setRefCodeEtabOrigine(this.dossierInscriptionAdministrativeDto
								.getRefCodeEtablissement());
						dossierTransfertDto.setRefLibelleEtabOrigine(refEtablissementDto.getLlEtablissementLatin());

						// Etablissement d'accueil
						if (dossierTransfertDto.getRefCodeEtabAccueil() != null) {
							RefEtablissementDto refEtablissementAccueilDto = refEtablissementService
									.findByIdentifiant(dossierTransfertDto.getRefCodeEtabAccueil());
							dossierTransfertDto.setRefLibelleEtabAccueil(refEtablissementAccueilDto
									.getLlEtablissementLatin());
						}

						// domaine/filiere demande
						if (dossierTransfertDto.getRefCodeDomaineDemande() != null) {
							RefDomaineLMDDto rdd = refDomaineLMDService.findByIdentifiant(dossierTransfertDto
									.getRefCodeDomaineDemande());
							dossierTransfertDto.setDomaineDemandeLibelleFr(rdd.getLlDomaine());
							dossierTransfertDto.setDomaineDemandeLibelleAr(rdd.getLlDomaineArabe());
						}

						if (dossierTransfertDto.getRefCodeFiliereDemandee() != null) {
							RefFiliereLmdDto rfd = refFiliereLmdService.findByIdentifiant(dossierTransfertDto
									.getRefCodeFiliereDemandee());
							if (rfd != null) {
								dossierTransfertDto.setFiliereDemandeeLibelleFr(rfd.getLlFiliere());
								dossierTransfertDto.setFiliereDemandeeLibelleAr(rfd.getLlFiliereArabe());
							}
						}

						// domaine/filiere origine
						if (dossierTransfertDto.getRefCodeDomaineOrigine() != null) {
							RefDomaineLMDDto rdd = refDomaineLMDService.findByIdentifiant(dossierTransfertDto
									.getRefCodeDomaineOrigine());
							dossierTransfertDto.setDomaineOrigineLibelleFr(rdd.getLlDomaine());
							dossierTransfertDto.setDomaineOrigineLibelleAr(rdd.getLlDomaineArabe());
						}

						if (dossierTransfertDto.getRefCodeFiliereOrigine() != null) {
							RefFiliereLmdDto rfd = refFiliereLmdService.findByIdentifiant(dossierTransfertDto
									.getRefCodeFiliereOrigine());
							if (rfd != null) {
								dossierTransfertDto.setFiliereOrigineLibelleFr(rfd.getLlFiliere());
								dossierTransfertDto.setFiliereOrigineLibelleAr(rfd.getLlFiliereArabe());

								// si l'etudiant est inscrit dans une filiere
								// (domain null)
								if (dossierTransfertDto.getRefCodeDomaineOrigine() == null) {
									dossierTransfertDto.setDomaineOrigineLibelleFr(rfd.getLlDomaine());
									dossierTransfertDto.setDomaineOrigineLibelleAr(rfd.getLlDomaineArabe());
								}
							}
						}

						// decision etab d'origine
						Boolean etabOrigineAccepte = dossierTransfertDto.getIsEtabOrigineAccepte();

						if (etabOrigineAccepte != null) {
							if (etabOrigineAccepte)
								dossierTransfertDto.setRefLibelleAvisEtabOrigine(transfertBundle
										.getString("demande_transfert_etudiant_acceptee"));
							else {
								dossierTransfertDto.setRefLibelleAvisEtabOrigine(transfertBundle
										.getString("demande_transfert_etudiant_rejetee"));

								NomenclatureDto motifRejet = nomenclatureService.findByCode(
										CursusConstants.NC_MOTIF_REJET_DEMANDE_TRANSFERT,
										dossierTransfertDto.getRefCodeMotifEtabOrigine());
								dossierTransfertDto.setRefLibelleMotifEtabOrigine(motifRejet.getLibelleLongFr());

							}
						}

						// decision etab accueil
						Boolean etabAccueilAccepte = dossierTransfertDto.getIsEtabAccueilAccepte();

						if (etabAccueilAccepte != null) {
							if (etabAccueilAccepte)
								dossierTransfertDto.setRefLibelleAvisEtabAccueil(transfertBundle
										.getString("demande_transfert_etudiant_acceptee"));
							else {
								dossierTransfertDto.setRefLibelleAvisEtabAccueil(transfertBundle
										.getString("demande_transfert_etudiant_rejetee"));

								NomenclatureDto motifRejet = nomenclatureService.findByCode(
										CursusConstants.NC_MOTIF_REJET_DEMANDE_TRANSFERT,
										dossierTransfertDto.getRefCodeMotifEtabAccueil());
								dossierTransfertDto.setRefLibelleMotifEtabAccueil(motifRejet.getLibelleLongFr());
							}
						}

						// resultat final
						Boolean accordee = dossierTransfertDto.getIsAccordee();
						if (accordee != null) {
							if (accordee)
								dossierTransfertDto.setRefLibelleAvisFinal(transfertBundle
										.getString("demande_transfert_etudiant_accordee"));
							else {
								dossierTransfertDto.setRefLibelleAvisFinal(transfertBundle
										.getString("demande_transfert_etudiant_non_accordee"));
							}
						}

						dossierTransfertResultSearch.add(dossierTransfertDto);
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
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:57:35
	 * @param listFilieres
	 */
	public void typeTransfertChanged() {
		// Si c'est un transfert interne, donc id_etab_accueil = id_etab_origine
		if (UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_INTERNE.equals(dossierTransfertDto.getRefCodeTypeTransfert())) {
			dossierTransfertDto.setRefCodeEtabAccueil(dossierTransfertDto.getRefCodeEtabOrigine());

			RefEtablissementDto refEtablissementDto;
			try {
				refEtablissementDto = refEtablissementService.findByIdentifiant(this.dossierTransfertDto
						.getRefCodeEtabAccueil());
				this.dossierTransfertDto.setRefLibelleEtabAccueil(refEtablissementDto.getLlEtablissementLatin());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			dossierTransfertDto.setRefCodeEtabAccueil(null);
			dossierTransfertDto.setRefLibelleEtabAccueil(null);
		}
		checkConditionsAccess();
	}

	/**
	 * [DossierTransfertSearchBean.dossierTransfertResultSearch] Getter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 18:43:38
	 * @return the dossierTransfertResultSearch
	 */
	public List<DossierTransfertDto> getDossierTransfertResultSearch() {
		return dossierTransfertResultSearch;
	}

	/**
	 * [DossierTransfertSearchBean.dossierTransfertResultSearch] Setter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 18:43:38
	 * @param dossierTransfertResultSearch
	 *            the dossierTransfertResultSearch to set
	 */
	public void setDossierTransfertResultSearch(List<DossierTransfertDto> dossierTransfertResultSearch) {
		this.dossierTransfertResultSearch = dossierTransfertResultSearch;
	}

	/**
	 * [DossierTransfertSearchBean.dossierTransfertSearchDto] Getter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 18:43:38
	 * @return the dossierTransfertSearchDto
	 */
	public DossierTransfertDto getDossierTransfertSearchDto() {
		return dossierTransfertSearchDto;
	}

	/**
	 * [DossierTransfertSearchBean.dossierTransfertSearchDto] Setter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 18:43:38
	 * @param dossierTransfertSearchDto
	 *            the dossierTransfertSearchDto to set
	 */
	public void setDossierTransfertSearchDto(DossierTransfertDto dossierTransfertSearchDto) {
		this.dossierTransfertSearchDto = dossierTransfertSearchDto;
	}

	/**
	 * [DossierTransfertSearchBean.dossierTransfertService] Getter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 18:43:38
	 * @return the dossierTransfertService
	 */
	public DossierTransfertService getDossierTransfertService() {
		return dossierTransfertService;
	}

	public AccessWilayaService getAccessWilayaService() {
		return accessWilayaService;
	}

	public void setAccessWilayaService(AccessWilayaService accessWilayaService) {
		this.accessWilayaService = accessWilayaService;
	}

	/**
	 * [DossierTransfertSearchBean.dossierTransfertService] Setter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 18:43:38
	 * @param dossierTransfertService
	 *            the dossierTransfertService to set
	 */
	public void setDossierTransfertService(DossierTransfertService dossierTransfertService) {
		this.dossierTransfertService = dossierTransfertService;
	}

	public CapaciteEtabService getCapaciteEtabService() {
		return capaciteEtabService;
	}

	public void setCapaciteEtabService(CapaciteEtabService capaciteEtabService) {
		this.capaciteEtabService = capaciteEtabService;
	}

	public PrioriteSerieBacService getPrioriteSerieBacService() {
		return prioriteSerieBacService;
	}

	public void setPrioriteSerieBacService(PrioriteSerieBacService prioriteSerieBacService) {
		this.prioriteSerieBacService = prioriteSerieBacService;
	}

	public NoteAccessFiliereService getNoteAccessFiliereService() {
		return noteAccessFiliereService;
	}

	public void setNoteAccessFiliereService(NoteAccessFiliereService noteAccessFiliereService) {
		this.noteAccessFiliereService = noteAccessFiliereService;
	}

	/**
	 * [DossierTransfertSearchBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 25 mai 2014 12:29:37
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [DossierTransfertSearchBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 25 mai 2014 12:29:37
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:57:57
	 * @return
	 */
	public LoginBean getLoginBean() {
		return loginBean;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:57:54
	 * @param loginBean
	 */
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:02
	 * @return
	 */
	public DossierTransfertDto getDossierTransfertDto() {
		return dossierTransfertDto;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:06
	 * @param dossierTransfertDto
	 */
	public void setDossierTransfertDto(DossierTransfertDto dossierTransfertDto) {
		this.dossierTransfertDto = dossierTransfertDto;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:09
	 * @return
	 */
	public boolean isCrud() {
		return isCrud;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:12
	 * @param isCrud
	 */
	public void setCrud(boolean isCrud) {
		this.isCrud = isCrud;
	}

	public boolean isView() {
		return isView;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:16
	 * @param isView
	 */
	public void setView(boolean isView) {
		this.isView = isView;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:23
	 * @param refIndividuDto
	 * @param dossierTransfertDto
	 */
	private void map(RefIndividuDto refIndividuDto, DossierTransfertDto dossierTransfertDto) {
		if (dossierTransfertDto == null) {
			dossierTransfertDto = new DossierTransfertDto();
		}
		dossierTransfertDto.setIndividuId(refIndividuDto.getId());
		dossierTransfertDto.setIndividuNin(refIndividuDto.getIdentifiant());
		dossierTransfertDto.setIndividuNomLatin(refIndividuDto.getNomLatin());
		dossierTransfertDto.setIndividuNomArabe(refIndividuDto.getNomArabe());
		dossierTransfertDto.setIndividuPrenomArabe(refIndividuDto.getPrenomArabe());
		dossierTransfertDto.setIndividuPrenomLatin(refIndividuDto.getPrenomLatin());
		if (refIndividuDto.getDateNaissance() != null) {
			dossierTransfertDto.setIndividuDateNaissance(refIndividuDto.getDateNaissance());
		}

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:27
	 * @return
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:31
	 * @param dossierEtudiantService
	 */
	public void setDossierEtudiantService(DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:35
	 * @return
	 */
	public BacService getBacService() {
		return bacService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:39
	 * @param bacService
	 */
	public void setBacService(BacService bacService) {
		this.bacService = bacService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:42
	 * @return
	 */
	public DossierEtudiantDto getDossierEtudiantDto() {
		return dossierEtudiantDto;
	}

	public void setDossierEtudiantDto(DossierEtudiantDto dossierEtudiantDto) {
		this.dossierEtudiantDto = dossierEtudiantDto;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:45
	 * @return
	 */
	public DossierBachelierDto getDossierBachelierDto() {
		return dossierBachelierDto;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:49
	 * @param dossierBachelierDto
	 */
	public void setDossierBachelierDto(DossierBachelierDto dossierBachelierDto) {
		this.dossierBachelierDto = dossierBachelierDto;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:53
	 * @return
	 */
	public List<NotesBachelierDto> getNotesBacheliersDtos() {
		return notesBacheliersDtos;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:59:01
	 * @param notesBacheliersDtos
	 */
	public void setNotesBacheliersDtos(List<NotesBachelierDto> notesBacheliersDtos) {
		this.notesBacheliersDtos = notesBacheliersDtos;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:58:58
	 * @return
	 */
	public String getRefCodeEtabOrigine() {
		return refCodeEtabOrigine;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:59:06
	 * @param refCodeEtabOrigine
	 */
	public void setRefCodeEtabOrigine(String refCodeEtabOrigine) {
		this.refCodeEtabOrigine = refCodeEtabOrigine;
	}

	/**
	 * [DossierInscriptionSearchCmpBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:40:10
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [DossierInscriptionSearchCmpBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:40:10
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [DossierTransfertSearchBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juin 2014 15:14:06
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [DossierTransfertSearchBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juin 2014 15:14:06
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [DossierTransfertSearchBean.offreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juin 2014 15:14:06
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [DossierTransfertSearchBean.offreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juin 2014 15:14:06
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [DossierTransfertSearchBean.dossierInscriptionAdministrativeService]
	 * Getter
	 * 
	 * @author BELDI Jamel on : 13 juil. 2014 11:17:43
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [DossierTransfertSearchBean.dossierInscriptionAdministrativeService]
	 * Setter
	 * 
	 * @author BELDI Jamel on : 13 juil. 2014 11:17:43
	 * @param dossierInscriptionAdministrativeService
	 *            the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:59:23
	 * @return
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:59:18
	 * @param situationService
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 12:14:41
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			this.dossierTransfertDto = (DossierTransfertDto) event.getObject();
			this.editDemandeTransfert();
			this.handleDomainesListChange(null);
			// if
			// (UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_VALIDEE_ETUDIANT_CODE
			// .equals(this.dossierTransfertDto.getCodeSituation())) {
			// this.viewDemandeTransfert();
			// } else {
			// this.editDemandeTransfert();
			// this.handleDomainesListChange(null);
			// }
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 17:08:02
	 */
	public void addDemandeTransfertAction() {
		this.resetDossiertransfertDto();
		this.isCrud = true;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 17:07:59
	 */
	public void editDemandeTransfert() {
		this.isCrud = true;
		this.isView = true;
	}

	public void viewDemandeTransfert() {
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
	 * Handle changement de l'etablissement
	 * 
	 * @author Mounir.MESSAOUDI on : 16 sept. 2014 15:33:54
	 * @param event
	 */
	public void handleEtablissementChange(AjaxBehaviorEvent event) {
		RefEtablissementDto refEtablissementDto;
		try {
			refEtablissementDto = refEtablissementService.findByIdentifiant(this.dossierTransfertDto
					.getRefCodeEtabAccueil());
			this.dossierTransfertDto.setRefLibelleEtabAccueil(refEtablissementDto.getLlEtablissementLatin());
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
		checkConditionsAccess();
	}

	/**
	 * Handle changement du domaine
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 15:14:20
	 * @param event
	 * @throws Exception_Exception
	 */
	public void handleDomainesListChange(AjaxBehaviorEvent event) throws Exception {
		// TODO liste des filieres du domaine accessWilayaDto
		// .getRefCodeDomaine() et l'etaablissement en cour
		if (this.listDomaines != null) {
			listFilieres = new ArrayList<SelectItem>();

			List<RefFiliereLmdDto> listFilieresDto = refFiliereLmdService.findByCodeDomainelmd(dossierTransfertDto
					.getRefCodeDomaineDemande());

			for (RefFiliereLmdDto ld : listFilieresDto) {
				SelectItem itemFr = new SelectItem(ld.getIdentifiant(), ld.getLlFiliere() + " (" + ld.getIdentifiant()
						+ ")");
				listFilieres.add(itemFr);

			}
		}
		checkConditionsAccess();

	}

	/**
	 * Handle changement du filiere
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 15:14:20
	 * @param event
	 * @throws Exception_Exception
	 */
	public void handleFiliersListChange(AjaxBehaviorEvent event) throws Exception {
		checkConditionsAccess();

	}

	/**
	 * Enregister la demande de transfert
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 10:41:46
	 */
	public void saveDemandeTransfertAction() {
		FacesMessage msg = new FacesMessage();
		try {
			checkConditionsAccess();
			// conditionsAccess = true;

			if (conditionsAccess) {
				// nouvelle demande
				if (this.dossierTransfertDto.getSituationId() == null) {

					dossierTransfertDto.setSituationId(situationService.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DOSSIER_TRANSFERT_ETUDIANT,
							UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_ENREGISTREE_CODE).getId());
				}
				this.dossierTransfertDto = dossierTransfertService.insertOrUpdate(this.dossierTransfertDto);

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ bundleCommon.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				// this.resetDossiertransfertDto();
				this.loadSearchResults();

				// this.isCrud = false;
			} else {
				// afficher le message d'erreur sur les conditions d'access
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundleCommon.getString("error_echec") + ": "
						+ transfertBundle.getString("error_demande_transfert_conditions_acces"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (JpaSystemException e) {
			// Utility.showErrorMessage(e.getMessage());

			if (e.getCause().getCause().getCause() instanceof ConstraintViolationException) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleCommon.getString("error_echec") + ": "
						+ transfertBundle.getString("error_demande_transfert_deja_enoye_etablissement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}

	}

	/**
	 * Verifier les conditions d'access a l'etablissement / domaine / filiere
	 * 
	 * @author Mounir.MESSAOUDI on : 2 sept. 2014 13:11:51
	 */
	private void checkConditionsAccess() {

		try {
			conditionDroitAccessWilaya = false;
			conditionSerieBac = false;
			conditionMoyenneBac = false;
			conditionNotesMatieresBac = null;
			conditionMoyenneCalculeeMatieresBac = null;

			List<DroitAccessWilayaDto> droitAccessWilayasDtos = new ArrayList<>();

			// 1/ conditions d'accès wilaya
			// si c'est un transfert interne, condition doit acess wilaya = ok
			if (UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_INTERNE.equals(this.dossierTransfertDto
					.getRefCodeTypeTransfert())) {
				conditionDroitAccessWilaya = true;
			} else {
				AccessWilayaDto searchAccessWilayaDto = new AccessWilayaDto();
				searchAccessWilayaDto.setIdAnneeAcademique(this.dossierTransfertDto.getAnneeAcademiqueId());

				searchAccessWilayaDto.setRefCodeEtablissement(this.dossierTransfertDto.getRefCodeEtabAccueil());
				searchAccessWilayaDto.setRefCodeDomaine(this.dossierTransfertDto.getRefCodeDomaineDemande());
				searchAccessWilayaDto.setRefCodeFiliere(this.dossierTransfertDto.getRefCodeFiliereDemandee());
				List<AccessWilayaDto> accessWilayaDtos = accessWilayaService.findAdvanced(searchAccessWilayaDto);

				// droit d'access wilaya
				if (!accessWilayaDtos.isEmpty()) {
					AccessWilayaDto accessWilayaDto = accessWilayaDtos.get(0);
					droitAccessWilayasDtos = accessWilayaDto.getDroitAccessWilayaDto();

					String codeWilaya = this.dossierBachelierDto.getRefCodeWilayaBac(); // refAdresseDto.getWilayaCode();
					DroitAccessWilayaDto d = new DroitAccessWilayaDto();
					d.setRefCodeWilaya(codeWilaya);
					if (droitAccessWilayasDtos.contains(d)) {
						conditionDroitAccessWilaya = true;
					}

					// liste des acces wilayas vide
				} else {
					conditionDroitAccessWilaya = false;
				}

				if (!conditionDroitAccessWilaya && this.dossierTransfertDto.getRefCodeEtabAccueil() != null) {
					this.dossTrsftMsgCondDroitAccessWilaya = MessageFormat.format(
							transfertBundle.getString("dossier_transfert_conditions_acces_acces_wilaya_message"),
							this.dossierTransfertDto.getRefLibelleEtabAccueil(), droitAccessWilayasDtos.toString());
				}

			}

			// 2/ conditions d'acces capacite etablissement
			conditionSerieBac = false;
			conditionMoyenneBac = false;
			conditionNotesMatieresBac = null;
			conditionMoyenneCalculeeMatieresBac = null;

			CapaciteEtabDto searchCapaciteEtabDto = new CapaciteEtabDto();
			searchCapaciteEtabDto.setIdAnneeAcademique(this.dossierTransfertDto.getAnneeAcademiqueId());
			searchCapaciteEtabDto.setRefCodeEtablissement(this.dossierTransfertDto.getRefCodeEtabAccueil());
			searchCapaciteEtabDto.setRefCodeDomaine(this.dossierTransfertDto.getRefCodeDomaineDemande());
			searchCapaciteEtabDto.setRefCodeFiliere(this.dossierTransfertDto.getRefCodeFiliereDemandee());
			List<CapaciteEtabDto> capaciteEtabDtos = capaciteEtabService.findAdvanced(searchCapaciteEtabDto);

			if (capaciteEtabDtos != null && !capaciteEtabDtos.isEmpty()) {
				CapaciteEtabDto capaciteEtabDto = capaciteEtabDtos.get(0);
				List<PrioriteSerieBacDto> prioriteSerieBacDtos = capaciteEtabDto.getPrioriteSerieBacDto();
				String refCodeSerieBacEtudiant = dossierBachelierDto.getRefCodeSerieBac();
				String _moyenne = dossierBachelierDto.getMoyenneBac();
				Double moyenneBacEtudiant = null;
				if (_moyenne != null) {
					moyenneBacEtudiant = new Double(_moyenne);
				}
				if (prioriteSerieBacDtos != null) {
					for (PrioriteSerieBacDto prioriteSerieBacDto : prioriteSerieBacDtos) {
						// si la serie de bac de l'etudiant existe dans la liste
						// des series de bac et priorite

						// 3/ conditions d'acces serie de bac
						if (refCodeSerieBacEtudiant.equals(prioriteSerieBacDto.getRefCodeSerieBac())) {
							conditionSerieBac = true;

							// 4/ conditions d'acces moyenne de bac
							Double moyenneAccess = prioriteSerieBacDto.getMoyenne();
							if (moyenneAccess != null && moyenneBacEtudiant != null) {
								if (moyenneBacEtudiant > moyenneAccess) {
									conditionMoyenneBac = true;
								} else {
									this.dossTrsftMsgCondMoyenneBac = MessageFormat.format(transfertBundle
											.getString("dossier_transfert_conditions_acces_moyenne_bac_message"),
											moyenneAccess);

								}
							}

							// 5/ conditions d'acces note des matieres
							List<NoteAccessFiliereDto> listNotesAccesMatieresBac = prioriteSerieBacDto
									.getNoteAccessFilieresDto();
							if (listNotesAccesMatieresBac != null && !listNotesAccesMatieresBac.isEmpty()) {
								for (NoteAccessFiliereDto noteAccessFiliereDto : listNotesAccesMatieresBac) {
									Double noteAccess = noteAccessFiliereDto.getNoteAccess();
									conditionNotesMatieresBac = false;
									if (noteAccess != null) {
										if (notesBacheliersDtos != null) {
											for (NotesBachelierDto notesBachelier : notesBacheliersDtos) {
												if (notesBachelier.getRefCodeMatiere().equals(
														noteAccessFiliereDto.getRefCodeMatiere())) {
													Double noteMatiere = notesBachelier.getNote();
													if (noteMatiere < noteAccess) {
														conditionNotesMatieresBac = false;
														this.dossTrsftMsgCondDroitAccessWilaya = MessageFormat
																.format(transfertBundle
																		.getString("dossier_transfert_conditions_acces_acces_wilaya_message"),
																		noteMatiere, notesBachelier
																				.getRefCodeMatiereLibelleFr());
														break;
													} else {
														conditionNotesMatieresBac = true;
													}
												}
											}
											// donnnes de notes de bac manquants
										} else {

										}

										// si aumoin une note d'acces n'est pas
										// valide sortir de la boucle.
										if (conditionNotesMatieresBac == false)
											break;
										// si il n'ya pas de note d'acces
									} else {
										conditionNotesMatieresBac = true;
									}

								}
								// s'il n y pas de conditions d'access par note
								// de matieres
							} else {
								conditionNotesMatieresBac = true;
							}

						}
					}
					// aucune priorite n'a ete definit
				} else {
					conditionMoyenneBac = true;
					conditionSerieBac = true;
					conditionNotesMatieresBac = true;
					conditionMoyenneCalculeeMatieresBac = true;
				}

			} else {
				conditionMoyenneBac = true;
				conditionSerieBac = true;
				conditionNotesMatieresBac = true;
				conditionMoyenneCalculeeMatieresBac = true;
			}

			if (!conditionDroitAccessWilaya || !conditionMoyenneBac || !conditionSerieBac || !conditionSerieBac
					|| !conditionNotesMatieresBac) {
				conditionsAccess = false;
			} else {
				conditionsAccess = true;
			}

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * Valider la demande de transfert
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 10:39:44
	 */
	public void validateDemandeTransfert() {
		FacesMessage msg = new FacesMessage();
		try {
			checkConditionsAccess();
			// conditionsAccess = true;

			if (conditionsAccess) {

				dossierTransfertDto.setSituationId(situationService.findBySituationEntiteByCodeSituation(
						UtilConstants.ENTITE_DOSSIER_TRANSFERT_ETUDIANT,
						UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_VALIDEE_ETUDIANT_CODE).getId());// 30

				dossierTransfertService.insertOrUpdate(this.dossierTransfertDto);

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ transfertBundle.getString("dossier_transfertmsg_success_validation_etudiant"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				// afficher le message d'erreur sur les conditions d'access
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundleCommon.getString("error_echec") + ": "
						+ transfertBundle.getString("error_demande_transfert_conditions_acces"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
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
	 * Desister la demande de transfert
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 10:40:53
	 */
	public void withdrawDemandeTransfert() {
		FacesMessage msg = new FacesMessage();
		try {
			dossierTransfertDto.setSituationId(situationService.findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_DOSSIER_TRANSFERT_ETUDIANT,
					UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_ANNULER_ETUDIANT_CODE).getId());// 30

			dossierTransfertService.insertOrUpdate(this.dossierTransfertDto);

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_annulation"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}

		this.resetDossiertransfertDto();
		this.loadSearchResults();
		this.isCrud = false;
	}

	/**
	 * Supprimer la demande de transfert
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 10:40:50
	 */
	public void deleteDemandeTransfertAction() {
		FacesMessage msg = new FacesMessage();
		try {
			dossierTransfertDto.setSituationId(situationService.findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_DOSSIER_TRANSFERT_ETUDIANT,
					UtilConstants.DEMANDE_TRANSFERT_ETUDIANT_ANNULER_ETUDIANT_CODE).getId());// 30

			dossierTransfertService.remove(this.dossierTransfertDto);

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_suppression"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}

		this.resetDossiertransfertDto();
		this.loadSearchResults();
		this.isCrud = false;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:56:26
	 * @return
	 */
	public List<SelectItem> getListTypesTransfert() {
		return listTypesTransfert;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 31 ao�t 2014 11:26:40
	 * @return
	 */
	public Integer getIdCurrAnneeAcademique() {
		return idCurrAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 31 ao�t 2014 11:26:43
	 * @param idCurrAnneeAcademique
	 */
	public void setIdCurrAnneeAcademique(Integer idCurrAnneeAcademique) {
		this.idCurrAnneeAcademique = idCurrAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 31 ao�t 2014 11:26:46
	 * @return
	 */
	public String getRefCodeCurrAnneeAcademique() {
		return refCodeCurrAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 31 ao�t 2014 11:26:48
	 * @param refCodeCurrAnneeAcademique
	 */
	public void setRefCodeCurrAnneeAcademique(String refCodeCurrAnneeAcademique) {
		this.refCodeCurrAnneeAcademique = refCodeCurrAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 31 ao�t 2014 11:26:51
	 * @return
	 */
	public Integer getIdAnneeAcademique() {
		return idAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 31 ao�t 2014 11:26:55
	 * @param idAnneeAcademique
	 */
	public void setIdAnneeAcademique(Integer idAnneeAcademique) {
		this.idAnneeAcademique = idAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:57:11
	 * @param listTypesTransfert
	 */
	public void setListTypesTransfert(List<SelectItem> listTypesTransfert) {
		this.listTypesTransfert = listTypesTransfert;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:57:14
	 * @return
	 */
	public List<SelectItem> getListEtablissements() {
		return listEtablissements;
	}

	public void setListEtablissements(List<SelectItem> listEtablissements) {
		this.listEtablissements = listEtablissements;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:57:20
	 * @return
	 */
	public List<SelectItem> getListAnneeAcademique() {
		return listAnneeAcademique;
	}

	public void setListAnneeAcademique(List<SelectItem> listAnneeAcademique) {
		this.listAnneeAcademique = listAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:57:23
	 * @return
	 */
	public List<SelectItem> getListDomaines() {
		return listDomaines;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:57:30
	 * @param listDomaines
	 */
	public void setListDomaines(List<SelectItem> listDomaines) {
		this.listDomaines = listDomaines;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:57:27
	 * @return
	 */
	public List<SelectItem> getListFilieres() {
		return listFilieres;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:57:35
	 * @param listFilieres
	 */
	public void setListFilieres(List<SelectItem> listFilieres) {
		this.listFilieres = listFilieres;
	}

	public List<SelectItem> getListMotifsTransfert() {
		return listMotifsTransfert;
	}

	public void setListMotifsTransfert(List<SelectItem> listMotifsTransfert) {
		this.listMotifsTransfert = listMotifsTransfert;
	}

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public Boolean getConditionNotesMatieresBac() {
		return conditionNotesMatieresBac;
	}

	public void setConditionNotesMatieresBac(Boolean conditionNotesMatieresBac) {
		this.conditionNotesMatieresBac = conditionNotesMatieresBac;
	}

	public Boolean getConditionMoyenneCalculeeMatieresBac() {
		return conditionMoyenneCalculeeMatieresBac;
	}

	public void setConditionMoyenneCalculeeMatieresBac(Boolean conditionMoyenneCalculeeMatieresBac) {
		this.conditionMoyenneCalculeeMatieresBac = conditionMoyenneCalculeeMatieresBac;
	}

	public Boolean getConditionDroitAccessWilaya() {
		return conditionDroitAccessWilaya;
	}

	public void setConditionDroitAccessWilaya(Boolean conditionDroitAccessWilaya) {
		this.conditionDroitAccessWilaya = conditionDroitAccessWilaya;
	}

	public DossierInscriptionAdministrativeDto getDossierInscriptionAdministrativeDto() {
		return dossierInscriptionAdministrativeDto;
	}

	public void setDossierInscriptionAdministrativeDto(
			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto) {
		this.dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeDto;
	}

	public Boolean getConditionMoyenneBac() {
		return conditionMoyenneBac;
	}

	public void setConditionMoyenneBac(Boolean conditionMoyenneBac) {
		this.conditionMoyenneBac = conditionMoyenneBac;
	}

	public Boolean getConditionsAccess() {
		return conditionsAccess;
	}

	public void setConditionsAccess(Boolean conditionsAccess) {
		this.conditionsAccess = conditionsAccess;
	}

	public Boolean getConditionSerieBac() {
		return conditionSerieBac;
	}

	public void setConditionSerieBac(Boolean conditionSerieBac) {
		this.conditionSerieBac = conditionSerieBac;
	}

	public Long getNbrMaxDemandesTransferts() {
		return nbrMaxDemandesTransferts;
	}

	public void setNbrMaxDemandesTransferts(Long nbrMaxDemandesTransferts) {
		this.nbrMaxDemandesTransferts = nbrMaxDemandesTransferts;
	}

	public Long getNbrDemandesCrees() {
		return nbrDemandesCrees;
	}

	public void setNbrDemandesCrees(Long nbrDemandesCrees) {
		this.nbrDemandesCrees = nbrDemandesCrees;
	}

	public boolean isAutoriseACree() {
		return autoriseACree;
	}

	public void setAutoriseACree(boolean autoriseACree) {
		this.autoriseACree = autoriseACree;
	}

	public String getDossTrsftMsgCondDroitAccessWilaya() {
		return dossTrsftMsgCondDroitAccessWilaya;
	}

	public void setDossTrsftMsgCondDroitAccessWilaya(String dossTrsftMsgCondDroitAccessWilaya) {
		this.dossTrsftMsgCondDroitAccessWilaya = dossTrsftMsgCondDroitAccessWilaya;
	}

	public String getDossTrsftMsgCondSerieBac() {
		return dossTrsftMsgCondSerieBac;
	}

	public void setDossTrsftMsgCondSerieBac(String dossTrsftMsgCondSerieBac) {
		this.dossTrsftMsgCondSerieBac = dossTrsftMsgCondSerieBac;
	}

	public String getDossTrsftMsgCondMoyenneBac() {
		return dossTrsftMsgCondMoyenneBac;
	}

	public void setDossTrsftMsgCondMoyenneBac(String dossTrsftMsgCondMoyenneBac) {
		this.dossTrsftMsgCondMoyenneBac = dossTrsftMsgCondMoyenneBac;
	}

	public String getDossTrsftMsgCondNotesMatieresBac() {
		return dossTrsftMsgCondNotesMatieresBac;
	}

	public void setDossTrsftMsgCondNotesMatieresBac(String dossTrsftMsgCondNotesMatieresBac) {
		this.dossTrsftMsgCondNotesMatieresBac = dossTrsftMsgCondNotesMatieresBac;
	}

	public String getDossTrsftMsgCondMoyenneCalculeeMatieresBac() {
		return dossTrsftMsgCondMoyenneCalculeeMatieresBac;
	}

	public void setDossTrsftMsgCondMoyenneCalculeeMatieresBac(String dossTrsftMsgCondMoyenneCalculeeMatieresBac) {
		this.dossTrsftMsgCondMoyenneCalculeeMatieresBac = dossTrsftMsgCondMoyenneCalculeeMatieresBac;
	}

	/**
	 * [DossierTransfertEtudiantMBean.nomenclatureService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:29:02
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [DossierTransfertEtudiantMBean.nomenclatureService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:29:02
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [DossierTransfertEtudiantMBean.refIndividuService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:29:02
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [DossierTransfertEtudiantMBean.refIndividuService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:29:02
	 * @param refIndividuService
	 *            the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [DossierTransfertEtudiantMBean.refSpecialiteLmdService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:29:02
	 * @return the refSpecialiteLmdService
	 */
	public RefSpecialiteLmdService getRefSpecialiteLmdService() {
		return refSpecialiteLmdService;
	}

	/**
	 * [DossierTransfertEtudiantMBean.refSpecialiteLmdService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:29:02
	 * @param refSpecialiteLmdService
	 *            the refSpecialiteLmdService to set
	 */
	public void setRefSpecialiteLmdService(RefSpecialiteLmdService refSpecialiteLmdService) {
		this.refSpecialiteLmdService = refSpecialiteLmdService;
	}

	/**
	 * [DossierTransfertEtudiantMBean.refFiliereLmdService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:29:02
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [DossierTransfertEtudiantMBean.refFiliereLmdService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:29:02
	 * @param refFiliereLmdService
	 *            the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	/**
	 * [DossierTransfertEtudiantMBean.refDomaineLMDService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:29:02
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [DossierTransfertEtudiantMBean.refDomaineLMDService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:29:02
	 * @param refDomaineLMDService
	 *            the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	/**
	 * [DossierTransfertEtudiantMBean.refEtablissementService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:34:04
	 * @return the refEtablissementService
	 */
	public RefEtablissementService getRefEtablissementService() {
		return refEtablissementService;
	}

	/**
	 * [DossierTransfertEtudiantMBean.refEtablissementService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:34:04
	 * @param refEtablissementService
	 *            the refEtablissementService to set
	 */
	public void setRefEtablissementService(RefEtablissementService refEtablissementService) {
		this.refEtablissementService = refEtablissementService;
	}

	/**
	 * [DossierTransfertEtudiantMBean.refParametreEtabService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:34:04
	 * @return the refParametreEtabService
	 */
	public RefParametreEtabService getRefParametreEtabService() {
		return refParametreEtabService;
	}

	/**
	 * [DossierTransfertEtudiantMBean.refParametreEtabService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:34:04
	 * @param refParametreEtabService
	 *            the refParametreEtabService to set
	 */
	public void setRefParametreEtabService(RefParametreEtabService refParametreEtabService) {
		this.refParametreEtabService = refParametreEtabService;
	}

}
