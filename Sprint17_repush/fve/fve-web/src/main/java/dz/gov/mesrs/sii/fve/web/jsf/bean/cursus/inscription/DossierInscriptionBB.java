package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.inscription;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.jfree.util.Log;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.PieceConstitutiveDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.PieceConstitutiveService;
import dz.gov.mesrs.sii.fve.business.service.impression.ImpressionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.PieceManagerBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.impression.PrintMgrBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefHistoriqueDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefHistoriqueService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefTypePieceConstitutiveService;
import dz.gov.mesrs.sii.referentiel.business.util.ConfigHolder;

/**
 * Classe de type Backingbean pour la gestion en m�me temps de la vue Edit et
 * Detail d'un dossier d'nscription
 * 
 * @author kheireddine omrani
 * 
 */
/**
 * @author MAKERRI Sofiane  on : 4 déc. 2014  15:53:19
 */
/**
 * @author MAKERRI Sofiane  on : 4 déc. 2014  15:53:23
 */
/**
 * @author MAKERRI Sofiane  on : 4 déc. 2014  15:53:26
 */
/**
 * @author MAKERRI Sofiane  on : 4 déc. 2014  15:53:32
 */
/**
 * @author MAKERRI Sofiane on : 4 déc. 2014 15:53:37
 */
@ManagedBean(name = "dossierInscriptionBB")
@ViewScoped
public class DossierInscriptionBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;
	@ManagedProperty(value = "#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty(value = "#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;
	@ManagedProperty(value = "#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;
	@ManagedProperty(value = "#{pieceManagerBean}")
	private PieceManagerBean pieceManagerBean;
	private List<DossierInscriptionAdministrativeDto> dossierInscriptionList;
	private List<DossierEtudiantDto> dossierEtudiantList;
	private DossierInscriptionAdministrativeDto dossierInscription;
	private RefIndividuDto individuDto;
	private RefIndividuDto searchedIndividus;
	private String searchMatricule;
	private String dossierId;
	private String niSearchKeyword;
	private String matricule;
	private String showNew;
	private boolean newInscription;

	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;
	private List<SelectItem> anneeAcademiqueList;
	List<OuvertureOffreFormationDto> ouvertureOffreFormationList;
	private List<SelectItem> offreFormationItemList;
	private ResourceBundle pbundle;
	private ResourceBundle diabundle;
	private List<SelectItem> niveauList;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	@ManagedProperty(value = "#{pieceConstitutiveService}")
	private PieceConstitutiveService pieceConstitutiveService;
	@ManagedProperty("#{refFiliereLmdServiceImpl}")
	private RefFiliereLmdService refFiliereLmdService;
	@ManagedProperty("#{refSpecialiteLmdServiceImpl}")
	private RefSpecialiteLmdService refSpecialiteLmdService;
	@ManagedProperty("#{refDomaineLMDServiceImpl}")
	private RefDomaineLMDService refDomaineLMDService;
	@ManagedProperty(value = "#{configHolder}")
	private ConfigHolder configHolder;
	private String codeTypeDossier;
	private boolean offreChange;
	private Boolean showOofList;
	private boolean disableValidation;
	private SituationEntiteDto situationValidee;
	private SituationEntiteDto situationEnregistrer;
	private NomenclatureDto roleEtudiant;

	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;

	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;

	@ManagedProperty("#{refHistoriqueServiceImpl}")
	private RefHistoriqueService refHistoriqueService;

	@ManagedProperty("#{refTypePieceConstitutiveServiceImpl}")
	private RefTypePieceConstitutiveService refTypePieceConstitutiveService;

	@ManagedProperty("#{refCompteServiceImpl}")
	private RefCompteService refCompteService;

	@ManagedProperty("#{refGroupeServiceImpl}")
	private RefGroupeService refGroupeService;

	@ManagedProperty("#{refAffectationServiceImpl}")
	private RefAffectationService refAffectationService;

	@ManagedProperty("#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabService;

	private static final org.apache.commons.logging.Log log = LogFactory.getLog(DossierInscriptionBB.class);
	private DossierInscriptionAdministrativeDto diaSearchDto;
	private String matriculeBac;
	private boolean showDetail;
	private String refGroupeCode;
	private boolean advancedSearch;

	/**
	 * 
	 */
	public DossierInscriptionBB() {
		super();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		pbundle = facesContext.getApplication().getResourceBundle(facesContext,
				Const.PIECE_CONSTITUTIVE_RESSOURCES_BUNDLE_FILE_NAME);
		diabundle = facesContext.getApplication().getResourceBundle(facesContext,
				Const.DOSSIER_INSCRIPTION_ADMINISTRATIF_RESSOURCES_BUNDLE_FILE_NAME);
		diaSearchDto = new DossierInscriptionAdministrativeDto();
	}

	@PostConstruct
	public void init() {
		showOofList = true;
		showDetail = false;
		disableValidation = false;
		advancedSearch = false;
		if (matricule != null) {
			// findossierInscriptionList();

		} else {
			// searchedIndividus = sessionBean.getIndividuSearchDto();
			// advancedSearch();
		}
		situationValidee = situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_DOSSIER_CODE,
				UtilConstants.SITUTAION_VALIDEE_CODE);

		situationEnregistrer = situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_DOSSIER_CODE,
				UtilConstants.SITUTAION_ENREGISTREE_CODE);

	}

	/**
	 * [DossierInscriptionBB.loadIndividuDto] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 juil. 2014 16:10:15
	 */
	public void loadIndividuDto() {
		try {
			individuDto = refIndividuService.findByIdentifiant(dossierInscription.getNin());
		} catch (Exception e) {

		}
	}

	/**
	 * [DossierInscriptionBB.findossierInscriptionList] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 juil. 2014 11:44:46
	 */
	public void findossierInscriptionList() {
		if (matricule != null) {
			DossierInscriptionAdministrativeDto _dto = new DossierInscriptionAdministrativeDto();
			_dto.setMatriculeBac(matricule);
			_dto.setRefEtablissementId(sessionBean.getIdEtablissement());

			dossierInscriptionList = dossierInscriptionAdministrativeService.findByMatriculeBac(_dto);
			if (dossierInscriptionList == null || dossierInscriptionList.isEmpty()) {
				dossierInscriptionList = null;
				Utility.showWarningMessage(diabundle
						.getString("dossier_inscription_datable_list_dossier_etudiant_no_result"));
				return;
			}
			if (dossierInscriptionList != null && dossierInscriptionList.size() == 1) {
				dossierInscription = dossierInscriptionList.get(0);

				selectDossier();
			}
		}
	}

	/**
	 * [DossierInscriptionBB2.loadDossierInscriptionInfo] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 juil. 2014 18:18:26
	 */
	public void loadDossierInscriptionInfo() {
		try {
			if (dossierInscription == null)
				return;
			individuDto = refIndividuService.findByIdentifiant(dossierInscription.getNin());
			if (individuDto != null) {
				dossierInscription.setIndividuId(individuDto.getId());
				dossierInscription.setIndividuDateNaissance(individuDto.getDateNaissance());
				dossierInscription.setIndividuLieuNaissance(individuDto.getLieuNaissance());
				dossierInscription.setIndividuNomArabe(individuDto.getNomArabe());
				dossierInscription.setIndividuPrenomArabe(individuDto.getPrenomArabe());

				dossierInscription.setIndividuNomLatin(individuDto.getNomLatin());
				dossierInscription.setIndividuPrenomLatin(individuDto.getPrenomLatin());

			}

			// chargement libelle de la wilaya

			if (dossierInscription.getRefCodeWilayaNaissance() != null) {
				NomenclatureDto wilayaDto = utilBean.getNomenclatureItemValue(OfConstants.NC_NAME_WILAYA,
						dossierInscription.getRefCodeWilayaNaissance());
				if (wilayaDto != null) {
					dossierInscription.setIndividuibelleWilayaNaissance(wilayaDto.getLibelleLongFr());
				}

			}

			dossierInscription.setLlEtablissementLatin(sessionBean.getLlLatinEtablissement());
			dossierInscription.setLlEtablissementArabe(sessionBean.getLlArabeEtablissement());
			if (dossierInscription.getRefLibelleSpecialite() == null
					&& dossierInscription.getRefCodeSpecialite() != null) {
				RefSpecialiteLmdDto _specialite = refSpecialiteLmdService.findByIdentifiant(dossierInscription
						.getRefCodeSpecialite());

				if (_specialite != null) {
					dossierInscription.setRefLibelleSpecialite(_specialite.getLlSpecialite());
					dossierInscription.setRefLibelleSpecialiteArabe(_specialite.getLlSpecialiteArabe());
				}
			}

			if (dossierInscription.getRefLibelleFiliere() == null && dossierInscription.getRefCodeFiliere() != null) {
				RefFiliereLmdDto _filiere = refFiliereLmdService.findByIdentifiant(dossierInscription
						.getRefCodeFiliere());
				if (_filiere != null) {
					dossierInscription.setRefLibelleFiliere(_filiere.getLlFiliere());
					dossierInscription.setRefLibelleFiliereArabe(_filiere.getLlFiliereArabe());
				}
			}

			if (dossierInscription.getRefLibelleDomaine() == null && dossierInscription.getRefCodeDomaine() != null) {
				RefDomaineLMDDto _domaine = refDomaineLMDService.findByIdentifiant(dossierInscription
						.getRefCodeDomaine());
				if (_domaine != null) {
					dossierInscription.setRefLibelleDomaine(_domaine.getLlDomaine());
					dossierInscription.setRefLibelleDomaineArabe(_domaine.getLlDomaineArabe());
				}
			}

			// initialisation du type d'inscription
			if (dossierInscription.getRefLibelleTypeInscription() == null) {
				NomenclatureDto _ncDto = utilBean.getNomenclatureItemValue(Const.LMD_TYPE_DOSSIER_INSCRIPTION,
						dossierInscription.getRefCodeTypeInscription());
				if (_ncDto != null)
					dossierInscription.setRefLibelleTypeInscription(_ncDto.getLibelleLongFr());
			}

			// intialisation de l'ann�e acad�mique
			if (dossierInscription.getAnneeAcademiqueId() == null) {
				// TODO : � r�cup�rer de l'objet SessionBean.
				int _currentYear = Calendar.getInstance().get(Calendar.YEAR);
				AnneeAcademiqueDto _aa = anneeAcademiqueService
						.findByFirstAndSecondYear(_currentYear, _currentYear + 1);
				if (_aa != null) {
					dossierInscription.setAnneeAcademiqueId(_aa.getId());
					dossierInscription.setAnneeAcademiqueCode(_aa.getCode());

				}
			}
//			if (dossierInscription.getSituationId() != null) {
//				List<SituationI18nDto> situation18n = situationService.findByIdSituationEntite(dossierInscription
//						.getSituationId());
//
//				for (SituationI18nDto s18n : situation18n) {
//					if (s18n.getLangue().equals("fr")) {
//						dossierInscription.setSituationLibelleFr(s18n.getLibelle());
//					} else if (s18n.getLangue().equals("ar")) {
//						dossierInscription.setSituationLibelleAr(s18n.getLibelle());
//					}
//				}
//			}

		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}

	/**
	 * [DossierInscriptionBB.loadOffreFormationOuverte] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 juil. 2014 12:57:24
	 */
	public void loadOffreFormationOuverte() {
		offreFormationItemList = new ArrayList<SelectItem>();

		try {
			Integer etabId = sessionBean.getIdEtablissement();
			String etabCode = sessionBean.getCodeEtablissement();
			Integer idAnnee = dossierInscription.getAnneeAcademiqueId();
			if (etabId == null || etabCode == null) {
				return;
			}
			if (idAnnee == null) {
				return;
			}
			showOofList = true;

			// Added by Rafik
			if (dossierInscription.getRefCodeDomaine() == null && dossierInscription.getRefCodeFiliere() == null)
				return;

			OuvertureOffreFormationDto searchDto = new OuvertureOffreFormationDto();
			searchDto.setEstOuverte(true);
			searchDto.setAnneeAcademiqueId(idAnnee);
			searchDto.setIdEtablissement(etabId);
			// searchDto.setOfRefCodeCycle(dossierInscription.getRefCodeCycle());
			// searchDto.setOfRefCodeCycle(dossierInscription.getCycleCode());
			searchDto.setCycleId(dossierInscription.getCycleId());
			searchDto.setOfRefCodeDomaine(dossierInscription.getRefCodeDomaine());
			// searchDto.setOfRefCodeFiliere(dossierInscription
			// .getRefCodeFiliere());

			if (ouvertureOffreFormationList == null || ouvertureOffreFormationList.isEmpty()) {
				ouvertureOffreFormationList = ouvertureOffreFormationService.findAdvanced(searchDto);
			}
			if (ouvertureOffreFormationList != null)
				for (OuvertureOffreFormationDto item : ouvertureOffreFormationList) {
					/****
					 * test de code de l'offre de formation : a revoir si on le
					 * laisse ou pas : MAKERRI Sofiane
					 ****/
					if (item.getOffreFormationCode() != null && item.getOffreFormationCode().startsWith(etabCode)) {
						SelectItem _of = new SelectItem(item.getId(),

						dossierInscription.getNiveauLibelleLongLt() + " - " + item.getOfLibelleLongFr());
						offreFormationItemList.add(_of);
					}
				}
			if (offreFormationItemList.size() == 1) {
				SelectItem _of = offreFormationItemList.get(0);
				dossierInscription.setOffreFormationLibelleFr(_of.getLabel());
				dossierInscription.setOuvertureOffreFormationId((Integer) _of.getValue());
				dossierInscription.setOffreFormationId(findOffFormationId(dossierInscription
						.getOuvertureOffreFormationId()));
				showOofList = false;
			}
			if (situationValidee != null && dossierInscription.getSituationId() == situationValidee.getId()) {
				showOofList = false;
			}

			if (situationEnregistrer != null && dossierInscription.getSituationId() == situationEnregistrer.getId()) {
				showOofList = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [DossierInscriptionBB.findOffFormationId] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 juil. 2014 13:50:13
	 * @param oofId
	 * @return
	 */
	public Integer findOffFormationId(Integer oofId) {
		if (oofId != null && ouvertureOffreFormationList != null) {
			for (OuvertureOffreFormationDto oofDto : ouvertureOffreFormationList) {
				if (oofDto.getId().equals(oofId)) {
					return oofDto.getOffreFormationId();
				}
			}
		}
		return null;
	}

	/****************************************************************************/
	/********************************* getter/setter ***************************/
	/****************************************************************************/

	// ****************** getter/setter des services **********************/
	/**
	 * Obtient l'objet service permettant la gestion des dossiers d'inscription
	 * 
	 * @return le service de gestion des dossiers d'inscription.
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * Met 0� jour l'objet service permettant la gestion des dossiers
	 * d'inscription
	 * 
	 * @param dossierInscriptionService
	 *            : Le nouveau service de gestion des dossiers d'inscription.
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [DossierInscriptionBB.getOffreFormationService] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 15:53:16
	 * @return
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	public void setOffreFormationService(OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [DossierInscriptionBB.getOuvertureOffreFormationService] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 15:53:29
	 * @return
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	public void setOuvertureOffreFormationService(OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	public void setAnneeAcademiqueService(AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	public void setDossierEtudiantService(DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	public DozerBeanMapper getMapper() {
		return mapper;
	}

	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	// ************ getter/setter des propri�t�s & composants
	// **************/

	public List<DossierInscriptionAdministrativeDto> getDossierInscriptionList() {
		return dossierInscriptionList;
	}

	public void setDossierInscriptionList(List<DossierInscriptionAdministrativeDto> dossierInscriptionList) {
		this.dossierInscriptionList = dossierInscriptionList;
	}

	/**
	 * Obtient l'instance dossier d'nscription en cours de cr�ation,
	 * consultation ou modification
	 * 
	 * @return l'DossierInscription en cours de gestion
	 */
	public DossierInscriptionAdministrativeDto getDossierInscription() {
		return dossierInscription;
	}

	public void setDossierInscription(DossierInscriptionAdministrativeDto dossierInscription) {
		this.dossierInscription = dossierInscription;
	}

	public String getDossierId() {
		return dossierId;
	}

	public void setDossierId(String dossierId) {
		this.dossierId = dossierId;
	}

	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	public String getNiSearchKeyword() {
		return niSearchKeyword;
	}

	public void setNiSearchKeyword(String niSearchKeyword) {
		this.niSearchKeyword = niSearchKeyword;
	}

	public RefIndividuDto getSearchedIndividus() {
		return searchedIndividus;
	}

	public void setSearchedIndividus(RefIndividuDto searchedIndividus) {
		this.searchedIndividus = searchedIndividus;
	}

	public RefIndividuDto getIndividuDto() {
		return individuDto;
	}

	public void setIndividuDto(RefIndividuDto individuDto) {
		this.individuDto = individuDto;
	}

	/**
	 * [DossierInscriptionBB.offreFormationItemList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 juil. 2014 12:57:11
	 * @return the offreFormationItemList
	 */
	public List<SelectItem> getOffreFormationItemList() {
		return offreFormationItemList;
	}

	/**
	 * [DossierInscriptionBB.offreFormationItemList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 juil. 2014 12:57:11
	 * @param offreFormationItemList
	 *            the offreFormationItemList to set
	 */
	public void setOffreFormationItemList(List<SelectItem> offreFormationItemList) {
		this.offreFormationItemList = offreFormationItemList;
	}

	public List<SelectItem> getNiveauList() {
		return niveauList;
	}

	/****************************************************************************/
	/************************** Persistance & navigation ************************/
	/****************************************************************************/

	public void searchWithNumeroInscription(String numeroInscription) {

		DossierInscriptionAdministrativeDto _dto = new DossierInscriptionAdministrativeDto();
		_dto.setNumeroInscription(numeroInscription);
		advancedSearch = false;
		dossierInscriptionList = dossierInscriptionAdministrativeService.findAdvanced(_dto, true);

		// this.inConsultOrUpdateMode = false;
	}

	/**
	 * [DossierInscriptionBB2.searchWithMatriculeBac] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 juil. 2014 15:00:49
	 * @param matriculeBac
	 */
	public void searchWithMatriculeBac() {
		setMatricule(matriculeBac);
		showDetail = false;
		DossierInscriptionAdministrativeDto _dto = new DossierInscriptionAdministrativeDto();
		_dto.setMatriculeBac(matriculeBac);
		_dto.setRefEtablissementId(sessionBean.getIdEtablissement());
		_dto.setAnneeAcademiqueId(sessionBean.getIdAnneeAcademique());
		dossierInscriptionList = dossierInscriptionAdministrativeService.findByMatriculeBac(_dto);

		if (dossierInscriptionList == null || dossierInscriptionList.isEmpty()) {
			dossierInscriptionList = null;
			dossierInscription = null;
			Utility.showWarningMessage(diabundle
					.getString("dossier_inscription_datable_list_dossier_etudiant_no_result"));
			return;
		}
		dossierInscription = new DossierInscriptionAdministrativeDto();
		if (dossierInscriptionList != null && dossierInscriptionList.size() == 1) {
			dossierInscription = dossierInscriptionList.get(0);
			selectDossier();

		}

		// this.inConsultOrUpdateMode = false;
	}

	public void advancedSearch() {

		dossierInscriptionList = new ArrayList<DossierInscriptionAdministrativeDto>();
		showDetail = false;
		advancedSearch = true;
		try {
			RefIndividuDto searchDto = sessionBean.getIndividuSearchDto();
			if ((searchDto.getIdentifiant() == null || searchDto.getIdentifiant().isEmpty())
					&& (searchDto.getNomLatin() == null || searchDto.getNomLatin().isEmpty())
					&& (searchDto.getPrenomLatin() == null || searchDto.getPrenomLatin().isEmpty())
					&& (searchDto.getNomArabe() == null || searchDto.getNomArabe().isEmpty())
					&& (searchDto.getPrenomArabe() == null || searchDto.getPrenomArabe().isEmpty())) {
				return;
			}
			List<RefIndividuDto> _individuList = refIndividuService.findAdvanced(searchDto);
			// sessionBean.setIndividuSearchDto(searchedIndividus);
			DossierEtudiantDto _deSearch = null;
			List<DossierEtudiantDto> _deList = null;
			for (RefIndividuDto _idvditem : _individuList) {

				// monter l'etudiant de recherche.
				_deSearch = new DossierEtudiantDto();
				_deSearch.setNin(_idvditem.getIdentifiant());
				if (searchMatricule != null) {
					_deSearch.setNumeroMatricule(searchMatricule);
				}
				// r�cup�rer la liste des �tudiants v�rifiant les
				// crit�res
				// de
				// recherche.
				_deList = dossierEtudiantService.findAdvanced(_deSearch);
				if (!newInscription && _deList != null) {
					for (DossierEtudiantDto _deItem : _deList) {
						dossierInscriptionList.addAll(dossierInscriptionAdministrativeService
								.findDossierInscriptionsBy(_deItem.getId()));
					}
				}
			}
			setDossierId(null);
			if (dossierInscriptionList == null || dossierInscriptionList.isEmpty()) {
				dossierInscriptionList = null;
				Utility.showWarningMessage(diabundle
						.getString("dossier_inscription_datable_list_dossier_etudiant_no_result"));
				return;
			}
			if (dossierInscriptionList != null && dossierInscriptionList.size() == 1) {
				dossierInscription = dossierInscriptionList.get(0);
				selectDossier();
			}

			// dossierInscriptionList =
			// dossierInscriptionAdministrativeService.findStudentAdvanced(diaSearchDto);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void newDossierInscription() {
		// Cr�er un nouveau dossier d'inscription
		dossierInscription = new DossierInscriptionAdministrativeDto();

		// this.inConsultOrUpdateMode = true;
	}

	public void inscrireEtudiant(DossierInscriptionAdministrativeDto selectedDia) {
		// mettre � jour la r�f�rence du dossierInscription � la
		// s�lection du
		// datatable en cours
		this.dossierInscription = selectedDia;
		loadOffreFormationOuverte();
		// loadDossierInscriptionInfo();

		// initDossierInscriptionInfos();

		// this.inConsultOrUpdateMode = true;
	}

	public void detailDossierInscription(DossierInscriptionAdministrativeDto selectedDia) {
		// mettre � jour la r�f�rence du dossierInscription � la
		// s�lection du
		// datatable en cours
		this.dossierInscription = selectedDia;

		// initDossierInscriptionInfos();

		// this.inConsultOrUpdateMode = true;
	}

	public void saveDossierInscription() {

		if (dossierInscription != null) {
			try {
				// if (showOofList)
				{
					SituationEntiteDto situationEnregistre = situationService.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DOSSIER_CODE, UtilConstants.SITUTAION_ENREGISTREE_CODE);
					if (situationEnregistre != null) {

						dossierInscription.setSituationId(situationEnregistre.getId());
					}
					dossierInscription = dossierInscriptionAdministrativeService.insertOrUpdate(dossierInscription);
					saveHistorique(Const.SAVE_ACTION_CODE);
					setOffreChange(false);

				}
				if (pieceManagerBean != null) {
					pieceManagerBean.setDisableSave(true);
					String result = pieceManagerBean.save();
					if (result != null && !result.equals("OK")) {
						// refreshDossierInscription();
						return;
					}
				}
				// List<RefTypePieceConstitutiveDto> list =
				// refTypePieceConstitutiveService
				// .findByCodeTypeDossierDate(
				// CursusConstants.CODE_TYPE_DOSSIER_INSCRIPTION_PREMIERE_ANNEE,
				// new Date());
				List<PieceConstitutiveDto> list = pieceManagerBean.getListPcProvided();
				for (PieceConstitutiveDto current : list) {
					if (current.getRefTypePieceAFournir() && current.getRefTypePieceOgligatoire()) {
						// boolean valid = pieceConstitutiveService
						// .isInRequiredValid(current.getIdTypePiece(),
						// dossierInscription.getId());
						if (!current.getFournie()) {
							Utility.showWarningMessage(pbundle.getString("piece_obligatoire_non_valide"));
							// refreshDossierInscription();
							return;
						}
					}
				}

				try {

					if (situationValidee != null && situationValidee.getId() != dossierInscription.getId()) {

						dossierInscription.setSituationId(situationValidee.getId());
						dossierInscription = dossierInscriptionAdministrativeService.insertOrUpdate(dossierInscription);
						disableValidation = true;
						// generateCompte();
						// refreshDossierInscription();
						pieceManagerBean.loadTypePieceConstitutive();
						// RequestContext.getCurrentInstance().update(":form_dossierInscriptionDetail");
						try {
							saveHistorique(Const.VALIDATE_ACTION_CODE);

						} catch (Exception e) {

						}
						showOofList = false;
						Utility.showSuccessMessage(diabundle.getString("dossier_inscription_msg_success_validation"));
						if (advancedSearch) {
							advancedSearch();
						} else {
							searchWithMatriculeBac();
						}
					}

				} catch (Exception e) {
					Utility.showErrorMessage(e.getMessage());
					return;

				}

			} catch (Exception e) {
				Utility.showErrorMessage(e.getMessage());
				return;

			}

		}

	}

	/**
	 * [DossierInscriptionBB.generateCompte] Method
	 * 
	 * @author MAKERRI Sofiane on : 3 sept. 2014 17:26:14
	 */
	public void generateCompte() {

		try {
			if (situationValidee.getId() != dossierInscription.getSituationId()) {
				Utility.showErrorMessage(diabundle.getString("dossier_inscription_erreur_inscription_non_valide"));
				return;
			}
			RefCompteDto refCompteDto = refCompteService.findByUser(dossierInscription.getIndividuId());
			if (refCompteDto == null) {
				/* l'etudiant n'a pas encore un compte */
				if (affecterEtudiantAuGroupe()) {
					if (dossierInscription.getNin() != null) {
						RefIndividuDto refIndividuDto = refIndividuService.findByIdentifiant(dossierInscription
								.getNin());
						if (refIndividuDto != null) {
							dossierInscription.setNin(refIndividuDto.getIdentifiant());
						}
					}
					if (dossierInscription.getIndividuDateNaissance() == null) {
						Utility.showErrorMessage(diabundle.getString("dossier_inscription_date_naissance_required"));
						return;
					}

					refCompteDto = new RefCompteDto();
					refCompteDto.setIndividuId(dossierInscription.getIndividuId());
					refCompteDto.setIndividuNomLatin(dossierInscription.getIndividuNomLatin());
					refCompteDto.setIndividuPrenomLatin(dossierInscription.getIndividuPrenomLatin());
					refCompteDto.setAccessJourFerie(false);
					refCompteDto.setPremiereSession(false);
					refCompteDto.setAdmin(false);
					refCompteDto.setActivate(true);
					refCompteDto.setChangementMotPasse(true);
					refCompteDto.setEtabId(sessionBean.getIdEtablissement());

					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					String dateNaissance = df.format(dossierInscription.getIndividuDateNaissance());
					refCompteDto.setMotPasse(dateNaissance.replaceAll("/", ""));
					refCompteDto.setConfirmationMotPasse(refCompteDto.getMotPasse());
					refCompteDto.setDateDebut(new Date());
					refCompteService.insertOrUpdate(refCompteDto);
					refCompteDto = refCompteService.findByUser(dossierInscription.getIndividuId());
					refCompteDto.setMotPasse(dateNaissance.replaceAll("/", ""));

				} else {
					return;
				}

			}

			printInfoCompte(refCompteDto);

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.info(e.getMessage());
		}

	}

	/**
	 * [DossierInscriptionBB.affecterEtudiantAuGroupe] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 sept. 2014 13:44:32
	 */
	public boolean affecterEtudiantAuGroupe() {
		try {
			if (dossierInscription.getIndividuId() == null) {
				Utility.showErrorMessage(diabundle.getString("dossier_inscription_individu_required"));
				return false;
			}
			roleEtudiant = nomenclatureService.findByCode(Const.NC_ROLE, Const.REF_CODE_ROLE_ETUDIANT);
			if (roleEtudiant == null) {
				Utility.showErrorMessage(diabundle.getString("dossier_inscription_role_etudiant_required"));
				return false;
			}
			RefGroupeDto searchDto = new RefGroupeDto();
			searchDto.setIdEtablissement(sessionBean.getIdEtablissement());
			if (refGroupeCode == null || refGroupeCode.isEmpty()) {
				refGroupeCode = refParametreEtabService.findValueOfKey(sessionBean.getIdEtablissement(),
						Const.PARAM_FVE_CODE_GROUPE_BACHELIERS_KEY);
				if (refGroupeCode == null || refGroupeCode.isEmpty()) {
					Utility.showErrorMessage(diabundle.getString("dossier_inscription_groupe_etudiant_required"));
					return false;
				}
			}
			searchDto.setIdentifiant(refGroupeCode);
			List<RefGroupeDto> groupeList = refGroupeService.findAdvanced(searchDto);
			if (groupeList == null || groupeList.isEmpty()) {
				Utility.showErrorMessage(diabundle.getString("dossier_inscription_groupe_etudiant_required"));
				return false;
			}
			RefGroupeDto groupeEtudiant = groupeList.get(0);
			RefAffectationDto refAffectationDto = new RefAffectationDto();
			refAffectationDto.setDateDebut(new Date());
			refAffectationDto.setIdGroupe(groupeEtudiant.getId());
			refAffectationDto.setIdIndividu(dossierInscription.getIndividuId());
			refAffectationDto.setRoleId(roleEtudiant.getId());
			refAffectationDto = refAffectationService.saveOrUpdate(refAffectationDto);
			return true;

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.info(e.getMessage());
			return false;
		}

	}

	/**
	 * [DossierInscriptionBB.saveAnnulationValidation] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 sept. 2014 14:18:38
	 */
	public void saveAnnulationValidation() {

		if (dossierInscription != null) {

			try {
				if (situationEnregistrer != null && situationEnregistrer.getId() != dossierInscription.getId()) {
					dossierInscription.setSituationId(situationEnregistrer.getId());
					dossierInscription = dossierInscriptionAdministrativeService.insertOrUpdate(dossierInscription);
					disableValidation = false;
					// refreshDossierInscription();
					// pieceManagerBean.setForceShowFourni(true);
					pieceManagerBean.setDisableSave(true);
					pieceManagerBean.loadTypePieceConstitutive();

					try {
						saveHistorique(Const.ANNULATION_VALIDATE_ACTION_CODE);

					} catch (Exception e) {

					}
					showOofList = false;
					Utility.showSuccessMessage(diabundle
							.getString("dossier_inscription_msg_success_annuler_validation"));
				}

			} catch (Exception e) {
				Utility.showErrorMessage(e.getMessage());
				return;

			}

		}

	}

	/**
	 * [DossierInscriptionBB.refreshDossierInscription] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 juil. 2014 12:36:11
	 */
	public void refreshDossierInscription() {
		if (dossierInscription != null) {
			// dossierInscription
			// .setOffreFormationLibelleFr(loadOffreFormationInfo(dossierInscription
			// .getOffreFormationId()));
		}
		if (matricule != null) {
			findossierInscriptionList();
		} else {
			// searchedIndividus = sessionBean.getIndividuSearchDto();
			advancedSearch();
		}
		findossierInscriptionList();

	}

	/**
	 * [DossierInscriptionBB.saveHistorique] Method
	 * 
	 * @author MAKERRI Sofiane on : 14 juil. 2014 17:13:53
	 * @param action
	 */
	private void saveHistorique(int action) {
		try {
			RefHistoriqueDto refHistoriqueDto = Utility.getRefHistoriqueDto(this.sessionBean.getSessionBean()
					.getCompte().getIdCompte(), sessionBean.getIdEtablissement(),
					Const.FONCTION_GERER_DOSSIER_ETUDIANT_CODE, dossierInscription.getId(),
					Const.DOSSIER_INSCRIPTION_ADMINISTRATIF_ENTITY_NAME, action);
			refHistoriqueService.save(refHistoriqueDto);
		} catch (Exception e) {

		}
	}

	/**
	 * [DossierInscriptionBB.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 14:13:33
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [DossierInscriptionBB.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 14:13:33
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [DossierInscriptionBB.loginBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 14:15:28
	 * @return the loginBean
	 */
	public LoginBean getLoginBean() {
		return loginBean;
	}

	/**
	 * [DossierInscriptionBB.loginBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 14:15:28
	 * @param loginBean
	 *            the loginBean to set
	 */
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	@ManagedProperty("#{printMgrBean}")
	private PrintMgrBean printMgrBean;

	public PrintMgrBean getPrintMgrBean() {
		return printMgrBean;
	}

	public void setPrintMgrBean(PrintMgrBean printMgrBean) {
		this.printMgrBean = printMgrBean;
	}

	@ManagedProperty("#{impressionService}")
	private ImpressionService impressionService;

	public ImpressionService getImpressionService() {
		return impressionService;
	}

	public void setImpressionService(ImpressionService impressionService) {
		this.impressionService = impressionService;
	}

	public void printAttestation() {
		// log.info("printAttestationPDF....");

		try {
			Collection<DossierInscriptionAdministrativeDto> etudiants = new ArrayList<>();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext.getExternalContext().getRealPath(
					"WEB-INF/classes/jasper/inscription/attestation_model.jrxml");
			etudiants.add(dossierInscription);
			byte[] bytes = impressionService.viewPDFWithDataSource(RESOURCE_PATH_TO_INPUT_FILE_JASPER, null, etudiants);

			printMgrBean.imprimer(bytes, "attestationinscription_" + dossierInscription.getNumeroInscription(), "pdf");

			// printMgrBean.viewAttestationPDF(
			// etudiants,
			// "attestationinscription_"
			// + dossierInscription.getNumeroInscription());

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * 
	 * [DossierInscriptionBB.printLettreEngagement]Print Lettre Engagement
	 * Method
	 * 
	 * @author BELDI Jamel on : 6 ao�t 2014 17:40:28
	 */
	public void printLettreEngagement() {

		try {
			Collection<DossierInscriptionAdministrativeDto> etudiants = new ArrayList<>();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext.getExternalContext().getRealPath(
					"WEB-INF/classes/jasper/inscription/engagement_model.jrxml");
			etudiants.add(dossierInscription);
			byte[] bytes = impressionService.viewPDFWithDataSource(RESOURCE_PATH_TO_INPUT_FILE_JASPER, null, etudiants);

			printMgrBean.imprimer(bytes, "lettre_engagement_", "pdf");
			//
			// printMgrBean.viewLettreEngagementPDF(
			// etudiants,
			// "lettre_engagement_"
			// + dossierInscription.getNumeroInscription());
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * Generation du badge pour l'etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 3 ao�t 2014 14:55:33
	 */
	public void printBadge() {

		try {
			Collection<DossierInscriptionAdministrativeDto> etudiants = new ArrayList<>();

			etudiants.add(dossierInscription);

			String name = "badge_" + dossierInscription.getNumeroInscription();

			// Calendar d = Calendar.getInstance();

			FacesContext facesContext = FacesContext.getCurrentInstance();

			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext.getExternalContext().getRealPath(
					"WEB-INF/classes/jasper/inscription/badge.jrxml");
			Map<String, Object> params = new HashMap<String, Object>();

			String url = dossierInscription.getPhoto();
			String photo = null;

			// TODO remettre la photo de l'etudiant / bug
			if (url != null) {
				String folder_photo = configHolder.getPhotoUploadFolder() + "/";
				photo = folder_photo + url;
			} else {
				ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
				photo = scontext.getRealPath("/resources/images/boy.png");
			}

			// ServletContext scontext = (ServletContext) facesContext
			// .getExternalContext().getContext();
			// photo = scontext.getRealPath("/resources/images/boy.png");

			params.put("PHOTO_ETUDIANT", photo);

			params.put("IMG_PAPS", facesContext.getExternalContext().getRealPath("images") + "/logopaps.png");
			params.put("BADGE_BACKGROUND", facesContext.getExternalContext().getRealPath("images") + "/badge_bg.png");

			params.put(
					"IMG_LOGO",
					facesContext.getExternalContext().getRealPath("images") + "/logo"
							+ sessionBean.getCodeEtablissement() + ".png");

			byte[] bytes = impressionService.viewPDFWithDataSource(RESOURCE_PATH_TO_INPUT_FILE_JASPER, params,
					etudiants);

			printMgrBean.imprimer(bytes, name, "pdf");

			// printMgrBean.viewAttestationPDF(
			// etudiants,
			// "badge_"
			// + dossierInscription.getNumeroInscription());
			// }

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [DossierInscriptionBB.printInfoCompte] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 sept. 2014 14:37:54
	 * @param refCompteDto
	 */
	public void printInfoCompte(RefCompteDto refCompteDto) {
		try {
			Collection<RefCompteDto> comptes = new ArrayList<>();
			comptes.add(refCompteDto);
			String name = "compte_" + refCompteDto.getIndividuNomLatin() + "_" + refCompteDto.getIndividuPrenomLatin();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext.getExternalContext().getRealPath(
					"WEB-INF/classes/jasper/inscription/lettre_information_compte.jrxml");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("IMG_PAPS", facesContext.getExternalContext().getRealPath("images") + "/logopaps.png");
			params.put(
					"IMG_LOGO",
					facesContext.getExternalContext().getRealPath("images") + "/logo"
							+ sessionBean.getCodeEtablissement() + ".png");
			byte[] bytes = impressionService.viewPDFWithDataSource(RESOURCE_PATH_TO_INPUT_FILE_JASPER, params, comptes);
			printMgrBean.imprimer(bytes, name, "pdf");

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [DossierInscriptionBB2.matricule] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 juin 2014 13:55:46
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * [DossierInscriptionBB2.matricule] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 juin 2014 13:55:46
	 * @param matricule
	 *            the matricule to set
	 */
	public void setMatricule(String matricule) {
		if (matricule != null && matricule.equals("null")) {
			this.matricule = null;
		} else {
			this.matricule = matricule;
		}
	}

	/**
	 * [DossierInscriptionBB2.situationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 juin 2014 16:55:36
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [DossierInscriptionBB2.situationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 juin 2014 16:55:36
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [DossierInscriptionBB2.pieceConstitutiveService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 juin 2014 17:02:11
	 * @return the pieceConstitutiveService
	 */
	public PieceConstitutiveService getPieceConstitutiveService() {
		return pieceConstitutiveService;
	}

	/**
	 * [DossierInscriptionBB2.pieceConstitutiveService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 juin 2014 17:02:11
	 * @param pieceConstitutiveService
	 *            the pieceConstitutiveService to set
	 */
	public void setPieceConstitutiveService(PieceConstitutiveService pieceConstitutiveService) {
		this.pieceConstitutiveService = pieceConstitutiveService;
	}

	/**
	 * [DossierInscriptionBB2.showNew] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 juil. 2014 16:24:59
	 * @return the showNew
	 */
	public String getShowNew() {
		return showNew;
	}

	public void setConfigHolder(ConfigHolder configHolder) {
		this.configHolder = configHolder;
	}

	public ConfigHolder getConfigHolder() {
		return configHolder;
	}

	/**
	 * [DossierInscriptionBB2.showNew] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 juil. 2014 16:24:59
	 * @param showNew
	 *            the showNew to set
	 */
	public void setShowNew(String showNew) {
		if (showNew != null && showNew.equals("null")) {
			this.showNew = null;
		} else {
			this.showNew = showNew;
			setNewInscription(Boolean.parseBoolean(showNew));
		}

	}

	/**
	 * [DossierInscriptionBB2.newInscription] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 juil. 2014 16:26:11
	 * @return the newInscription
	 */
	public boolean isNewInscription() {
		return newInscription;
	}

	/**
	 * [DossierInscriptionBB2.newInscription] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 juil. 2014 16:26:11
	 * @param newInscription
	 *            the newInscription to set
	 */
	public void setNewInscription(boolean newInscription) {
		this.newInscription = newInscription;
	}

	/**
	 * [DossierInscriptionBB2.searchMatricule] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 juil. 2014 13:21:36
	 * @return the searchMatricule
	 */
	public String getSearchMatricule() {
		return searchMatricule;
	}

	/**
	 * [DossierInscriptionBB2.searchMatricule] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 juil. 2014 13:21:36
	 * @param searchMatricule
	 *            the searchMatricule to set
	 */
	public void setSearchMatricule(String searchMatricule) {
		this.searchMatricule = searchMatricule;
	}

	/**
	 * [DossierInscriptionBB2.dossierEtudiantList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 juil. 2014 13:22:51
	 * @return the dossierEtudiantList
	 */
	public List<DossierEtudiantDto> getDossierEtudiantList() {
		return dossierEtudiantList;
	}

	/**
	 * [DossierInscriptionBB2.dossierEtudiantList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 juil. 2014 13:22:51
	 * @param dossierEtudiantList
	 *            the dossierEtudiantList to set
	 */
	public void setDossierEtudiantList(List<DossierEtudiantDto> dossierEtudiantList) {
		this.dossierEtudiantList = dossierEtudiantList;
	}

	/**
	 * [DossierInscriptionBB.onRowSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 14 juil. 2014 13:04:27
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		this.dossierInscription = ((DossierInscriptionAdministrativeDto) event.getObject());
		selectDossier();
		// loadDossierInscriptionInfo();
	}

	/**
	 * [DossierInscriptionBB.selectDossier] Method
	 * 
	 * @author MAKERRI Sofiane on : 10 ao�t 2014 10:40:07
	 */
	public void selectDossier() {
		disableValidation = false;
		showDetail = true;

		if (situationValidee != null && dossierInscription.getSituationId() != null
				&& dossierInscription.getSituationId() == situationValidee.getId()) {
			showOofList = false;
			disableValidation = true;
		}

		if (situationEnregistrer != null && dossierInscription.getSituationId() != null
				&& dossierInscription.getSituationId() == situationEnregistrer.getId()) {
			showOofList = false;
			disableValidation = false;
		}
		if (pieceManagerBean != null) {
			pieceManagerBean.setDossierId(String.valueOf(dossierInscription.getId()));
			NomenclatureDto _ncTypeDossier = utilBean.getNomenclatureItemValue(CursusConstants.NC_TYPE_TYPE_DOSSIER,
					CursusConstants.CODE_TYPE_DOSSIER_INSCRIPTION_PREMIERE_ANNEE);
			if (_ncTypeDossier == null) {
				Utility.showErrorMessage(pbundle.getString("piece_type_dossier_inscription_premiere_annee_inexistant"));
				return;
			}

			pieceManagerBean.setCodeTypeDossier(CursusConstants.CODE_TYPE_DOSSIER_INSCRIPTION_PREMIERE_ANNEE);
			pieceManagerBean.setIdTypeDossier(_ncTypeDossier.getId());
			pieceManagerBean.setDisableSave(true);
			pieceManagerBean.setReadOnly(disableValidation);
			pieceManagerBean.init();
			pieceManagerBean.loadTypePieceConstitutive();
		}
		showOofList = true;

		if (showOofList || dossierInscription.getOuvertureOffreFormationId() == null) {
			loadOffreFormationOuverte();
		} else {
			// dossierInscription
			// .setOffreFormationLibelleFr(loadOffreFormationInfo(dossierInscription
			// .getOffreFormationId()));
		}
	}

	/**
	 * [DossierInscriptionBB.codeTypeDossier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 juil. 2014 14:06:19
	 * @return the codeTypeDossier
	 */
	public String getCodeTypeDossier() {
		codeTypeDossier = CursusConstants.CODE_TYPE_DOSSIER_INSCRIPTION_PREMIERE_ANNEE;
		return codeTypeDossier;
	}

	/**
	 * [DossierInscriptionBB.codeTypeDossier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 juil. 2014 14:06:19
	 * @param codeTypeDossier
	 *            the codeTypeDossier to set
	 */
	public void setCodeTypeDossier(String codeTypeDossier) {
		this.codeTypeDossier = codeTypeDossier;
	}

	/**
	 * [DossierInscriptionBB.pieceManagerBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 juil. 2014 14:21:54
	 * @return the pieceManagerBean
	 */
	public PieceManagerBean getPieceManagerBean() {
		return pieceManagerBean;
	}

	/**
	 * [DossierInscriptionBB.pieceManagerBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 juil. 2014 14:21:54
	 * @param pieceManagerBean
	 *            the pieceManagerBean to set
	 */
	public void setPieceManagerBean(PieceManagerBean pieceManagerBean) {
		this.pieceManagerBean = pieceManagerBean;
	}

	/**
	 * [DossierInscriptionBB.offreChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 juil. 2014 17:17:52
	 * @return the offreChange
	 */
	public boolean isOffreChange() {
		return offreChange;
	}

	/**
	 * [DossierInscriptionBB.offreChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 juil. 2014 17:17:52
	 * @param offreChange
	 *            the offreChange to set
	 */
	public void setOffreChange(boolean offreChange) {
		this.offreChange = offreChange;
	}

	/**
	 * [DossierInscriptionBB.offreChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 14 juil. 2014 17:17:55
	 * @param event
	 */
	public void offreChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
			offreChange = true;
		}
	}

	/**
	 * [DossierInscriptionBB.showOofList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 juil. 2014 17:53:21
	 * @return the showOofList
	 */
	public Boolean getShowOofList() {
		return showOofList;
	}

	/**
	 * [DossierInscriptionBB.showOofList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 juil. 2014 17:53:21
	 * @param showOofList
	 *            the showOofList to set
	 */
	public void setShowOofList(Boolean showOofList) {
		this.showOofList = showOofList;
	}

	/**
	 * [DossierInscriptionBB.situationValidee] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 09:18:47
	 * @return the situationValidee
	 */
	public SituationEntiteDto getSituationValidee() {
		return situationValidee;
	}

	/**
	 * [DossierInscriptionBB.situationValidee] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 09:18:47
	 * @param situationValidee
	 *            the situationValidee to set
	 */
	public void setSituationValidee(SituationEntiteDto situationValidee) {
		this.situationValidee = situationValidee;
	}

	/**
	 * [DossierInscriptionBB.ouvertureOffreFormationList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 juil. 2014 13:02:38
	 * @return the ouvertureOffreFormationList
	 */
	public List<OuvertureOffreFormationDto> getOuvertureOffreFormationList() {
		return ouvertureOffreFormationList;
	}

	/**
	 * [DossierInscriptionBB.ouvertureOffreFormationList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 juil. 2014 13:02:38
	 * @param ouvertureOffreFormationList
	 *            the ouvertureOffreFormationList to set
	 */
	public void setOuvertureOffreFormationList(List<OuvertureOffreFormationDto> ouvertureOffreFormationList) {
		this.ouvertureOffreFormationList = ouvertureOffreFormationList;
	}

	/**
	 * [DossierInscriptionBB.disableValidation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 juil. 2014 13:07:19
	 * @return the disableValidation
	 */
	public boolean getDisableValidation() {
		return disableValidation;
	}

	/**
	 * [DossierInscriptionBB.disableValidation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 juil. 2014 13:07:19
	 * @param disableValidation
	 *            the disableValidation to set
	 */
	public void setDisableValidation(boolean disableValidation) {
		this.disableValidation = disableValidation;
	}

	public String cancel() {
		return "dossierInscriptionEdit";
	}

	/**
	 * [DossierInscriptionBB.situationEnregistrer] Getter
	 * 
	 * @author BELBRIK Oualid on : 30 juil. 2014 19:24:48
	 * @return the situationEnregistrer
	 */
	public SituationEntiteDto getSituationEnregistrer() {
		return situationEnregistrer;
	}

	/**
	 * [DossierInscriptionBB.situationEnregistrer] Setter
	 * 
	 * @author BELBRIK Oualid on : 30 juil. 2014 19:24:48
	 * @param situationEnregistrer
	 *            the situationEnregistrer to set
	 */
	public void setSituationEnregistrer(SituationEntiteDto situationEnregistrer) {
		this.situationEnregistrer = situationEnregistrer;
	}

	/**
	 * [DossierInscriptionBB.roleEtudiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 sept. 2014 13:49:00
	 * @return the roleEtudiant
	 */
	public NomenclatureDto getRoleEtudiant() {
		return roleEtudiant;
	}

	/**
	 * [DossierInscriptionBB.roleEtudiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 sept. 2014 13:49:00
	 * @param roleEtudiant
	 *            the roleEtudiant to set
	 */
	public void setRoleEtudiant(NomenclatureDto roleEtudiant) {
		this.roleEtudiant = roleEtudiant;
	}

	/**
	 * [DossierInscriptionBB.nomenclatureService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:43:30
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [DossierInscriptionBB.nomenclatureService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:43:30
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [DossierInscriptionBB.refIndividuService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:43:30
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [DossierInscriptionBB.refIndividuService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:43:30
	 * @param refIndividuService
	 *            the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [DossierInscriptionBB.refHistoriqueService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:46:05
	 * @return the refHistoriqueService
	 */
	public RefHistoriqueService getRefHistoriqueService() {
		return refHistoriqueService;
	}

	/**
	 * [DossierInscriptionBB.refHistoriqueService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:46:05
	 * @param refHistoriqueService
	 *            the refHistoriqueService to set
	 */
	public void setRefHistoriqueService(RefHistoriqueService refHistoriqueService) {
		this.refHistoriqueService = refHistoriqueService;
	}

	/**
	 * [DossierInscriptionBB.refTypePieceConstitutiveService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:47:33
	 * @return the refTypePieceConstitutiveService
	 */
	public RefTypePieceConstitutiveService getRefTypePieceConstitutiveService() {
		return refTypePieceConstitutiveService;
	}

	/**
	 * [DossierInscriptionBB.refTypePieceConstitutiveService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:47:33
	 * @param refTypePieceConstitutiveService
	 *            the refTypePieceConstitutiveService to set
	 */
	public void setRefTypePieceConstitutiveService(RefTypePieceConstitutiveService refTypePieceConstitutiveService) {
		this.refTypePieceConstitutiveService = refTypePieceConstitutiveService;
	}

	/**
	 * [DossierInscriptionBB.refCompteService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:48:29
	 * @return the refCompteService
	 */
	public RefCompteService getRefCompteService() {
		return refCompteService;
	}

	/**
	 * [DossierInscriptionBB.refCompteService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:48:29
	 * @param refCompteService
	 *            the refCompteService to set
	 */
	public void setRefCompteService(RefCompteService refCompteService) {
		this.refCompteService = refCompteService;
	}

	/**
	 * [DossierInscriptionBB.refGroupeService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:51:20
	 * @return the refGroupeService
	 */
	public RefGroupeService getRefGroupeService() {
		return refGroupeService;
	}

	/**
	 * [DossierInscriptionBB.refGroupeService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:51:20
	 * @param refGroupeService
	 *            the refGroupeService to set
	 */
	public void setRefGroupeService(RefGroupeService refGroupeService) {
		this.refGroupeService = refGroupeService;
	}

	/**
	 * [DossierInscriptionBB.refAffectationService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:52:11
	 * @return the refAffectationService
	 */
	public RefAffectationService getRefAffectationService() {
		return refAffectationService;
	}

	/**
	 * [DossierInscriptionBB.refAffectationService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:52:11
	 * @param refAffectationService
	 *            the refAffectationService to set
	 */
	public void setRefAffectationService(RefAffectationService refAffectationService) {
		this.refAffectationService = refAffectationService;
	}

	/**
	 * [DossierInscriptionBB.refFiliereLmdService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 09:35:52
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [DossierInscriptionBB.refFiliereLmdService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 09:35:52
	 * @param refFiliereLmdService
	 *            the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	/**
	 * [DossierInscriptionBB.refSpecialiteLmdService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 09:38:27
	 * @return the refSpecialiteLmdService
	 */
	public RefSpecialiteLmdService getRefSpecialiteLmdService() {
		return refSpecialiteLmdService;
	}

	/**
	 * [DossierInscriptionBB.refSpecialiteLmdService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 09:38:27
	 * @param refSpecialiteLmdService
	 *            the refSpecialiteLmdService to set
	 */
	public void setRefSpecialiteLmdService(RefSpecialiteLmdService refSpecialiteLmdService) {
		this.refSpecialiteLmdService = refSpecialiteLmdService;
	}

	/**
	 * [DossierInscriptionBB.refDomaineLMDService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 09:41:09
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [DossierInscriptionBB.refDomaineLMDService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 09:41:09
	 * @param refDomaineLMDService
	 *            the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	/**
	 * [DossierInscriptionBB.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 09:50:37
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [DossierInscriptionBB.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 09:50:37
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [DossierInscriptionBB.diaSearchDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 16:17:20
	 * @return the diaSearchDto
	 */
	public DossierInscriptionAdministrativeDto getDiaSearchDto() {
		return diaSearchDto;
	}

	/**
	 * [DossierInscriptionBB.diaSearchDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 16:17:20
	 * @param diaSearchDto
	 *            the diaSearchDto to set
	 */
	public void setDiaSearchDto(DossierInscriptionAdministrativeDto diaSearchDto) {
		this.diaSearchDto = diaSearchDto;
	}

	/**
	 * [DossierInscriptionBB.matriculeBac] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 déc. 2014 13:27:40
	 * @return the matriculeBac
	 */
	public String getMatriculeBac() {
		return matriculeBac;
	}

	/**
	 * [DossierInscriptionBB.matriculeBac] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 déc. 2014 13:27:40
	 * @param matriculeBac
	 *            the matriculeBac to set
	 */
	public void setMatriculeBac(String matriculeBac) {
		this.matriculeBac = matriculeBac;
	}

	/**
	 * [DossierInscriptionBB.showDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 déc. 2014 13:43:05
	 * @return the showDetail
	 */
	public boolean isShowDetail() {
		return showDetail;
	}

	/**
	 * [DossierInscriptionBB.showDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 déc. 2014 13:43:05
	 * @param showDetail
	 *            the showDetail to set
	 */
	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}

	/**
	 * [DossierInscriptionBB.refParametreEtabService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 déc. 2014 14:50:53
	 * @return the refParametreEtabService
	 */
	public RefParametreEtabService getRefParametreEtabService() {
		return refParametreEtabService;
	}

	/**
	 * [DossierInscriptionBB.refParametreEtabService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 déc. 2014 14:50:53
	 * @param refParametreEtabService
	 *            the refParametreEtabService to set
	 */
	public void setRefParametreEtabService(RefParametreEtabService refParametreEtabService) {
		this.refParametreEtabService = refParametreEtabService;
	}

	/**
	 * [DossierInscriptionBB.refGroupeCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 déc. 2014 14:54:19
	 * @return the refGroupeCode
	 */
	public String getRefGroupeCode() {
		return refGroupeCode;
	}

	/**
	 * [DossierInscriptionBB.refGroupeCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 déc. 2014 14:54:19
	 * @param refGroupeCode
	 *            the refGroupeCode to set
	 */
	public void setRefGroupeCode(String refGroupeCode) {
		this.refGroupeCode = refGroupeCode;
	}

	/**
	 * [DossierInscriptionBB.advancedSearch] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 déc. 2014 11:01:52
	 * @return the advancedSearch
	 */
	public boolean isAdvancedSearch() {
		return advancedSearch;
	}

	/**
	 * [DossierInscriptionBB.advancedSearch] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 déc. 2014 11:01:52
	 * @param advancedSearch
	 *            the advancedSearch to set
	 */
	public void setAdvancedSearch(boolean advancedSearch) {
		this.advancedSearch = advancedSearch;
	}

}
