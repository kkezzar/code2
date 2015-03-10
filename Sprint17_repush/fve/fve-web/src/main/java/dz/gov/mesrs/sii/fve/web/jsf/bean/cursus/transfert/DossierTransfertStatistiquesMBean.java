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
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.DossierBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.NotesBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierTransfertDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.TransfertDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.TransfertStatistiquesTypeTransfertDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierTransfertService;
import dz.gov.mesrs.sii.fve.business.service.cursus.TransfertService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
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
 * Managed bean pour l'affichage des statistiques sur les transferts.
 * responsable de transfert
 * 
 * @author Mounir.MESSAOUDI
 */
@ManagedBean(name = "dossierTransfertStatistiquesMBean")
@ViewScoped
public class DossierTransfertStatistiquesMBean {

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory
			.getLog(DossierTransfertEtudiantMBean.class);

	private ResourceBundle transfertBundle;
	private ResourceBundle bundleCommon;

	private List<TransfertDto> transfertResultSearch;

	private List<TransfertDto> transfertsInternesDtos;
	private List<TransfertDto> transfertsExternesDtos;

	private List<TransfertDto> transfertsInternesBachelierDtos;
	private List<TransfertDto> transfertsExternesBachelierDtos;
	private List<TransfertDto> transfertsTypesTransfertsDtos;
	private List<TransfertDto> transfertsExternesSortantesBachelierDtos;

	private List<TransfertStatistiquesTypeTransfertDto> transfertStatistiquesTypeTransfertResultSearch;

	private List<DossierTransfertDto> dossiersTransfertDtos;

	private DossierTransfertDto dossierTransfertSearchDto;

	@ManagedProperty(value = "#{dossierTransfertService}")
	private DossierTransfertService dossierTransfertService;

	@ManagedProperty(value = "#{transfertService}")
	private TransfertService transfertService;

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
	private NomenclatureService nomenclatureService;
	
	
	@ManagedProperty("#{refEtablissementServiceImpl}")
	private RefEtablissementService refEtablissementService;

	@ManagedProperty("#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;

	@ManagedProperty("#{refSpecialiteLmdServiceImpl}")
	private RefSpecialiteLmdService refSpecialiteLmdService;

	@ManagedProperty("#{refFiliereLmdServiceImpl}")
	private RefFiliereLmdService refFiliereLmdService;

	@ManagedProperty("#{refDomaineLMDServiceImpl}")
	private RefDomaineLMDService refDomaineLMDService;
	
	/**
	 * Le dossier du transfert en cours de traitement
	 */
	private DossierTransfertDto dossierTransfertDto;
	private DossierEtudiantDto dossierEtudiantDto;
	private DossierBachelierDto dossierBachelierDto;
	private List<NotesBachelierDto> notesBacheliersDtos;

	private String refCodeEtabOrigine;

	private TransfertDto transfertDto;

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

	private boolean isCrud;

	private boolean isView;

	/**
	 * Creates a new instance of DossierTransfertResponsableMBean
	 */
	public DossierTransfertStatistiquesMBean() {
	}

	/**
	 * Post constructeur
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:54:49
	 */
	@PostConstruct
	public void init() {
		try {

			this.refCodeCurrEtablissement = sessionBean.getCodeEtablissement();

			this.idCurrAnneeAcademique = sessionBean.getIdAnneeAcademique();
			this.refCodeCurrAnneeAcademique = sessionBean
					.getCodeAnneeAcademique();
			this.idAnneeAcademique = this.idCurrAnneeAcademique;

			this.initUI();

			this.resetTransfertDto();
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
			// TODO: handle exception
		}
	}

	/**
	 * Reinitialiser le dossierTransfertDto
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 15:55:32
	 */
	private void resetTransfertDto() {
		this.transfertDto = null;
		this.transfertDto = new TransfertDto();
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
	 * Reinitialiser la liste des dossiers de transfert en cours de traitement
	 * 
	 * @author Mounir.MESSAOUDI on : 26 ao�t 2014 10:57:03
	 */
	private void resetDossiersTransfertDtos() {
		this.dossiersTransfertDtos = null;
		this.dossiersTransfertDtos = new ArrayList<DossierTransfertDto>();
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
		try {

			// transferts internes domaine/filiere
			List<TransfertDto> listdossiersTransfertInternesDtos = transfertService
					.findTransfertInterneByIdAnnAcadRefCodeEtab(
							this.idAnneeAcademique,
							this.refCodeCurrEtablissement);
			transfertsInternesDtos = new ArrayList<TransfertDto>();
			for (TransfertDto transfertDto : listdossiersTransfertInternesDtos) {
				// domaine/filiere
				if (transfertDto.getRefCodeDomaineDemande() != null) {
					RefDomaineLMDDto rdd = refDomaineLMDService
							.findByIdentifiant(transfertDto
									.getRefCodeDomaineDemande());
					transfertDto.setLibelleDomaineDemandeFr(rdd.getLlDomaine());
					transfertDto.setLibelleDomaineDemandeAr(rdd
							.getLlDomaineArabe());
				}

				if (transfertDto.getRefCodeFiliereDemandee() != null) {
					RefFiliereLmdDto rfd = refFiliereLmdService
							.findByIdentifiant(transfertDto
									.getRefCodeFiliereDemandee());

					if (rfd != null) {
						transfertDto.setLibelleFiliereDemandeeFr(rfd
								.getLlFiliere());
						transfertDto.setLibelleFiliereDemandeeAr(rfd
								.getLlFiliereArabe());
					}
				}

				transfertsInternesDtos.add(transfertDto);

			}

			// transferts externes domaine/filiere
			List<TransfertDto> listdossiersTransfertExternesDtos = transfertService
					.findTransfertExterneByIdAnnAcadRefCodeEtab(
							this.idAnneeAcademique,
							this.refCodeCurrEtablissement);
			transfertsExternesDtos = new ArrayList<TransfertDto>();
			for (TransfertDto transfertDto : listdossiersTransfertExternesDtos) {
				// domaine/filiere
				if (transfertDto.getRefCodeDomaineDemande() != null) {
					RefDomaineLMDDto rdd = refDomaineLMDService
							.findByIdentifiant(transfertDto
									.getRefCodeDomaineDemande());
					transfertDto.setLibelleDomaineDemandeFr(rdd.getLlDomaine());
					transfertDto.setLibelleDomaineDemandeAr(rdd
							.getLlDomaineArabe());
				}

				if (transfertDto.getRefCodeFiliereDemandee() != null) {
					RefFiliereLmdDto rfd = refFiliereLmdService
							.findByIdentifiant(transfertDto
									.getRefCodeFiliereDemandee());

					if (rfd != null) {
						transfertDto.setLibelleFiliereDemandeeFr(rfd
								.getLlFiliere());
						transfertDto.setLibelleFiliereDemandeeAr(rfd
								.getLlFiliereArabe());
					}
				}

				transfertsExternesDtos.add(transfertDto);
			}

			// transferts interne par type bachelier
			List<TransfertDto> listTransfertsInternesBachelierDtos = transfertService
					.findTransfertInterneBachelierByIdAnnAcadRefCodeEtab(
							this.idAnneeAcademique,
							this.refCodeCurrEtablissement);
			transfertsInternesBachelierDtos = new ArrayList<TransfertDto>();
			int nouv = 0;
			for (TransfertDto transfertDto : listTransfertsInternesBachelierDtos) {
				if (transfertDto.getNouveauBachelier()) {
					nouv = 1;
					transfertDto
							.setNouveauBachelierLibelleFr(transfertBundle
									.getString("dossier_transfert_bacheliers_nouveaux"));
				} else {
					nouv = 2;
					transfertDto.setNouveauBachelierLibelleFr(transfertBundle
							.getString("dossier_transfert_bacheliers_anciens"));
				}
				transfertsInternesBachelierDtos.add(transfertDto);
			}
			if (transfertsInternesBachelierDtos.size() == 1) {
				TransfertDto t = new TransfertDto();
				long l = 0;
				t.setNbrTotalDemandes(l);
				t.setNbrDemandesTraitees(l);
				t.setNbrDemandesAcceptees(l);
				t.setNbrDemandesRejetees(l);
				t.setNbrDemandesAccordees(l);
				t.setNbrCasExceptionnels(l);
				switch (nouv) {
				case 1:
					// ajouter ligne anciens bachelier
					t.setNouveauBachelierLibelleFr(transfertBundle
							.getString("dossier_transfert_bacheliers_anciens"));
					transfertsInternesBachelierDtos.add(t);
					break;

				case 2:
					// ajouter ligne nouveaux bachelier
					t.setNouveauBachelierLibelleFr(transfertBundle
							.getString("dossier_transfert_bacheliers_nouveaux"));
					transfertsInternesBachelierDtos.add(0, t);
					break;
				}

			}

			// transferts externe par type de bachelier
			List<TransfertDto> listTransfertsExternessBachelierDtos = transfertService
					.findTransfertExterneBachelierByIdAnnAcadRefCodeEtab(
							this.idAnneeAcademique,
							this.refCodeCurrEtablissement);
			transfertsExternesBachelierDtos = new ArrayList<TransfertDto>();
			nouv = 0;
			for (TransfertDto transfertDto : listTransfertsExternessBachelierDtos) {
				if (transfertDto.getNouveauBachelier()) {
					nouv = 1;
					transfertDto
							.setNouveauBachelierLibelleFr(transfertBundle
									.getString("dossier_transfert_bacheliers_nouveaux"));
					transfertsExternesBachelierDtos.add(transfertDto);
				} else {
					nouv = 2;
					transfertDto.setNouveauBachelierLibelleFr(transfertBundle
							.getString("dossier_transfert_bacheliers_anciens"));
					transfertsExternesBachelierDtos.add(0, transfertDto);
				}

			}
			if (transfertsExternesBachelierDtos.size() == 1) {
				TransfertDto t = new TransfertDto();
				long l = 0;
				t.setNbrTotalDemandes(l);
				t.setNbrDemandesTraitees(l);
				t.setNbrDemandesAcceptees(l);
				t.setNbrDemandesRejetees(l);
				t.setNbrDemandesAccordees(l);
				t.setNbrCasExceptionnels(l);
				switch (nouv) {
				case 1:
					// ajouter ligne anciens bachelier
					t.setNouveauBachelierLibelleFr(transfertBundle
							.getString("dossier_transfert_bacheliers_anciens"));
					transfertsExternesBachelierDtos.add(t);
					break;

				case 2:
					// ajouter ligne nouveaux bachelier
					t.setNouveauBachelierLibelleFr(transfertBundle
							.getString("dossier_transfert_bacheliers_nouveaux"));
					transfertsExternesBachelierDtos.add(0, t);
					break;
				}

			}

			// transferts par types de transfert
			List<TransfertDto> listTTransfertsTypesTransfertsDtos = transfertService
					.findTransfertTypeTransfertByIdAnnAcadRefCodeEtab(
							this.idAnneeAcademique,
							this.refCodeCurrEtablissement);
			transfertsTypesTransfertsDtos = new ArrayList<TransfertDto>();
			for (TransfertDto transfertDto : listTTransfertsTypesTransfertsDtos) {
				// Type de la demande
				NomenclatureDto typeDemandeTransfertDto = nomenclatureService
						.findByCode(CursusConstants.NC_TYPE_DEMANDE_TRANSFERT,
								transfertDto.getRefCodeTypeTransfert());
				transfertDto
						.setRefCodeTypeTransfertLibelleFr(typeDemandeTransfertDto
								.getLibelleLongFr());

				transfertsTypesTransfertsDtos.add(transfertDto);
			}

			// transferts sortantes par type bachelier
			List<TransfertDto> listTransfertsSortantesBachelierDtos = transfertService
					.findTransfertExterneSortanteByIdAnnAcadRefCodeEtab(
							this.idAnneeAcademique,
							this.refCodeCurrEtablissement);
			transfertsExternesSortantesBachelierDtos = new ArrayList<TransfertDto>();
			for (TransfertDto transfertDto : listTransfertsSortantesBachelierDtos) {
				if (transfertDto.getNouveauBachelier()) {
					transfertDto
							.setNouveauBachelierLibelleFr(transfertBundle
									.getString("dossier_transfert_bacheliers_nouveaux"));
				} else {
					transfertDto.setNouveauBachelierLibelleFr(transfertBundle
							.getString("dossier_transfert_bacheliers_anciens"));
				}

				transfertsExternesSortantesBachelierDtos.add(transfertDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public List<DossierTransfertDto> getDossiersTransfertDtos() {
		return dossiersTransfertDtos;
	}

	public void setDossiersTransfertDtos(
			List<DossierTransfertDto> dossiersTransfertDtos) {
		this.dossiersTransfertDtos = dossiersTransfertDtos;
	}

	public List<TransfertDto> getTransfertResultSearch() {
		return transfertResultSearch;
	}

	public List<TransfertDto> getTransfertsInternesDtos() {
		return transfertsInternesDtos;
	}

	public void setTransfertsInternesDtos(
			List<TransfertDto> transfertsInternesDtos) {
		this.transfertsInternesDtos = transfertsInternesDtos;
	}

	public List<TransfertDto> getTransfertsExternesDtos() {
		return transfertsExternesDtos;
	}

	public void setTransfertsExternesDtos(
			List<TransfertDto> transfertsExternesDtos) {
		this.transfertsExternesDtos = transfertsExternesDtos;
	}

	public List<TransfertDto> getTransfertsInternesBachelierDtos() {
		return transfertsInternesBachelierDtos;
	}

	public void setTransfertsInternesBachelierDtos(
			List<TransfertDto> transfertsInternesBachelierDtos) {
		this.transfertsInternesBachelierDtos = transfertsInternesBachelierDtos;
	}

	public List<TransfertDto> getTransfertsExternesBachelierDtos() {
		return transfertsExternesBachelierDtos;
	}

	public void setTransfertsExternesBachelierDtos(
			List<TransfertDto> transfertsExternesBachelierDtos) {
		this.transfertsExternesBachelierDtos = transfertsExternesBachelierDtos;
	}

	public void setTransfertResultSearch(
			List<TransfertDto> transfertResultSearch) {
		this.transfertResultSearch = transfertResultSearch;
	}

	public DossierTransfertDto getDossierTransfertSearchDto() {
		return dossierTransfertSearchDto;
	}

	public void setDossierTransfertSearchDto(
			DossierTransfertDto dossierTransfertSearchDto) {
		this.dossierTransfertSearchDto = dossierTransfertSearchDto;
	}

	public DossierTransfertService getDossierTransfertService() {
		return dossierTransfertService;
	}

	public void setDossierTransfertService(
			DossierTransfertService dossierTransfertService) {
		this.dossierTransfertService = dossierTransfertService;
	}

	public TransfertService getTransfertService() {
		return transfertService;
	}

	public void setTransfertService(TransfertService transfertService) {
		this.transfertService = transfertService;
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

	public TransfertDto getTransfertDto() {
		return transfertDto;
	}

	public void setTransfertDto(TransfertDto transfertDto) {
		this.transfertDto = transfertDto;
	}

	public List<TransfertStatistiquesTypeTransfertDto> getTransfertStatistiquesTypeTransfertResultSearch() {
		return transfertStatistiquesTypeTransfertResultSearch;
	}

	public void setTransfertStatistiquesTypeTransfertResultSearch(
			List<TransfertStatistiquesTypeTransfertDto> transfertStatistiquesTypeTransfertResultSearch) {
		this.transfertStatistiquesTypeTransfertResultSearch = transfertStatistiquesTypeTransfertResultSearch;
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

	public void setListEtablissements(List<SelectItem> listEtablissements) {
		this.listEtablissements = listEtablissements;
	}

	public List<SelectItem> getListAnneeAcademique() {
		return listAnneeAcademique;
	}

	public void setListAnneeAcademique(List<SelectItem> listAnneeAcademique) {
		this.listAnneeAcademique = listAnneeAcademique;
	}

	public List<TransfertDto> getTransfertsTypesTransfertsDtos() {
		return transfertsTypesTransfertsDtos;
	}

	public void setTransfertsTypesTransfertsDtos(
			List<TransfertDto> transfertsTypesTransfertsDtos) {
		this.transfertsTypesTransfertsDtos = transfertsTypesTransfertsDtos;
	}

	public List<TransfertDto> getTransfertsExternesSortantesBachelierDtos() {
		return transfertsExternesSortantesBachelierDtos;
	}

	public void setTransfertsExternesSortantesDtos(
			List<TransfertDto> transfertsExternesSortantesBachelierDtos) {
		this.transfertsExternesSortantesBachelierDtos = transfertsExternesSortantesBachelierDtos;
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

	public boolean isCrud() {
		return isCrud;
	}

	public void setCrud(boolean isCrud) {
		this.isCrud = isCrud;
	}

	public boolean isView() {
		return isView;
	}

	public String getRefCodeCurrEtablissement() {
		return refCodeCurrEtablissement;
	}

	public void setRefCodeCurrEtablissement(String refCodeCurrEtablissement) {
		this.refCodeCurrEtablissement = refCodeCurrEtablissement;
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
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 17:07:59
	 */
	public void editTransfert() {
		this.isCrud = true;
		this.isView = true;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 17:07:56
	 */
	public void cancelTransfert() {
		this.resetDossiertransfertDto();
		this.isCrud = false;
		this.isView = false;
	}

	/**
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 12:14:41
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			this.transfertDto = (TransfertDto) event.getObject();
			dossierTransfertSearchDto = new DossierTransfertDto();
			dossierTransfertSearchDto
					.setRefCodeDomaineDemande(this.transfertDto
							.getRefCodeDomaineDemande());
			dossierTransfertSearchDto
					.setRefCodeFiliereDemandee(this.transfertDto
							.getRefCodeFiliereDemandee());
			dossierTransfertSearchDto.setRefCodeEtabAccueil(this.transfertDto
					.getRefCodeEtab());

			List<DossierTransfertDto> listdossiersTransfertDtos = dossierTransfertService
					.findAdvanced(dossierTransfertSearchDto);

			this.dossiersTransfertDtos = new ArrayList<DossierTransfertDto>();

			for (DossierTransfertDto dossierTransfertDto : listdossiersTransfertDtos) {
				// individu
				RefIndividuDto refIndividuDto = refIndividuService
						.findByIdentifiant(dossierTransfertDto
								.getIndividuNin());
				map(refIndividuDto, dossierTransfertDto);

				// motif de la demande
				NomenclatureDto motifTransfertDto = nomenclatureService
						.findByCode(CursusConstants.NC_MOTIF_DEMANDE_TRANSFERT,
								dossierTransfertDto.getRefCodeMotif());
				dossierTransfertDto.setRefCodeMotifLibelleFr(motifTransfertDto
						.getLibelleLongFr());
				dossierTransfertDto.setRefCodeMotifLibelleAr(motifTransfertDto
						.getLibelleLongAr());

				// type de transfert
				NomenclatureDto typeTransfertDto = nomenclatureService
						.findByCode(CursusConstants.NC_TYPE_DEMANDE_TRANSFERT,
								dossierTransfertDto.getRefCodeTypeTransfert());
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
							dossierTransfertDto.setDomaineOrigineLibelleFr(rfd
									.getLlDomaine());
							dossierTransfertDto.setDomaineOrigineLibelleAr(rfd
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
					else
						dossierTransfertDto
								.setRefLibelleAvisEtabOrigine(transfertBundle
										.getString("demande_transfert_etudiant_rejetee"));

				}

				// decision etab accueil
				Boolean etabAccueilAccepte = dossierTransfertDto
						.getIsEtabAccueilAccepte();

				if (etabAccueilAccepte != null) {
					if (etabAccueilAccepte)
						dossierTransfertDto
								.setRefLibelleAvisEtabAccueil(transfertBundle
										.getString("demande_transfert_etudiant_acceptee"));
					else
						dossierTransfertDto
								.setRefLibelleAvisEtabAccueil(transfertBundle
										.getString("demande_transfert_etudiant_rejetee"));
				}

				// classement
				Boolean cas_exceptionnel = dossierTransfertDto
						.getCasExceptionnel();
				if (cas_exceptionnel != null) {
					if (cas_exceptionnel)
						dossierTransfertDto
								.setCasExceptionnelLieblleFr(transfertBundle
										.getString("demande_transfert_etudiant_cas_exceptionnel"));
					else
						dossierTransfertDto
								.setCasExceptionnelLieblleFr(transfertBundle
										.getString("demande_transfert_etudiant_non_cas_exceptionnel"));
				}

				Boolean isAccordee = dossierTransfertDto.getIsAccordee();
				if (isAccordee != null) {
					if (isAccordee)
						dossierTransfertDto
								.setIsAccordeeLieblleFr(transfertBundle
										.getString("demande_transfert_etudiant_accorde"));
					else
						dossierTransfertDto
								.setIsAccordeeLieblleFr(transfertBundle
										.getString("demande_transfert_etudiant_non_accorde"));
				}

				this.dossiersTransfertDtos.add(dossierTransfertDto);

			}

			this.editTransfert();

		} catch (Exception e) {
			// TODO: handle exception
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

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 26 ao�t 2014 11:15:36
	 */
	public void onRowDossierTransfertDtoEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("D�cision modif�e",
				((DossierTransfertDto) event.getObject()).getId().toString());

		DossierTransfertDto d = (DossierTransfertDto) event.getObject();

		// classement
		Boolean cas_exceptionnel = d.getCasExceptionnel();
		if (cas_exceptionnel != null) {
			if (cas_exceptionnel) {
				d.setCasExceptionnelLieblleFr(transfertBundle
						.getString("demande_transfert_etudiant_cas_exceptionnel"));
				d.setIsAccordee(true);
			}

			else
				d.setCasExceptionnelLieblleFr(transfertBundle
						.getString("demande_transfert_etudiant_non_cas_exceptionnel"));
		}

		Boolean isAccordee = d.getIsAccordee();
		if (isAccordee != null) {
			if (isAccordee)
				d.setIsAccordeeLieblleFr(transfertBundle
						.getString("demande_transfert_etudiant_accorde"));
			else
				d.setIsAccordeeLieblleFr(transfertBundle
						.getString("demande_transfert_etudiant_non_accorde"));
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 26 ao�t 2014 11:15:39
	 */
	public void onRowDossierTransfertDtoCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Modification annul�e",
				((DossierTransfertDto) event.getObject()).getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Enregistrer le resultat finale des transferts pour le domain/Filiere
	 * 
	 * @author Mounir.MESSAOUDI on : 26 ao�t 2014 10:53:41
	 */
	public void saveResultatsTransfertAction() {
		FacesMessage msg = new FacesMessage();

		try {
			for (DossierTransfertDto dossiersTransfertDto : dossiersTransfertDtos) {
				dossierTransfertService.insertOrUpdate(dossiersTransfertDto);
			}
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
	 * Valider le resultat finale des transferts pour le domain/Filiere
	 * 
	 * @author Mounir.MESSAOUDI on : 26 ao�t 2014 10:53:41
	 */
	public void validateResultatsTransfertAction() {
		FacesMessage msg = new FacesMessage();

		try {
			for (DossierTransfertDto dossiersTransfertDto : dossiersTransfertDtos) {
				dossierTransfertService.insertOrUpdate(dossiersTransfertDto);
			}
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_validation"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * Annuler les modification sur le resultat final des transferts
	 * 
	 * @author Mounir.MESSAOUDI on : 26 ao�t 2014 10:55:00
	 */
	public void cancelResultatsTransfertAction() {
		this.resetTransfertDto();
		this.resetDossiersTransfertDtos();

		this.isCrud = false;
		this.isView = false;

	}

	/**
	 * [DossierTransfertStatistiquesMBean.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:50:08
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [DossierTransfertStatistiquesMBean.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:50:08
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [DossierTransfertStatistiquesMBean.refIndividuService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:50:08
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [DossierTransfertStatistiquesMBean.refIndividuService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:50:08
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [DossierTransfertStatistiquesMBean.refSpecialiteLmdService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:50:39
	 * @return the refSpecialiteLmdService
	 */
	public RefSpecialiteLmdService getRefSpecialiteLmdService() {
		return refSpecialiteLmdService;
	}

	/**
	 * [DossierTransfertStatistiquesMBean.refSpecialiteLmdService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:50:39
	 * @param refSpecialiteLmdService the refSpecialiteLmdService to set
	 */
	public void setRefSpecialiteLmdService(
			RefSpecialiteLmdService refSpecialiteLmdService) {
		this.refSpecialiteLmdService = refSpecialiteLmdService;
	}

	/**
	 * [DossierTransfertStatistiquesMBean.refFiliereLmdService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:50:39
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [DossierTransfertStatistiquesMBean.refFiliereLmdService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:50:39
	 * @param refFiliereLmdService the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	/**
	 * [DossierTransfertStatistiquesMBean.refDomaineLMDService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:50:39
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [DossierTransfertStatistiquesMBean.refDomaineLMDService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:50:39
	 * @param refDomaineLMDService the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	/**
	 * [DossierTransfertStatistiquesMBean.refEtablissementService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:52:13
	 * @return the refEtablissementService
	 */
	public RefEtablissementService getRefEtablissementService() {
		return refEtablissementService;
	}

	/**
	 * [DossierTransfertStatistiquesMBean.refEtablissementService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:52:13
	 * @param refEtablissementService the refEtablissementService to set
	 */
	public void setRefEtablissementService(
			RefEtablissementService refEtablissementService) {
		this.refEtablissementService = refEtablissementService;
	}

}
