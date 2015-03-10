/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.parcoursindividualise.ParcoursIndividualiseBean.java] 
 * @author BELDI Jamel on : 15 juil. 2014  10:03:05
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.parcoursindividualise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ParcoursIndividualiseDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ParcoursIndividualiseMatiereDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierService;
import dz.gov.mesrs.sii.fve.business.service.cursus.ParcoursIndividualiseService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.ParcoursTypeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;


/**
 * @author BELDI Jamel  on : 11 ao�t 2014  14:41:09
 */
@ManagedBean(name = "parcoursIndividualiseConsultBean")
@ViewScoped
public class ParcoursIndividualiseConsultBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:10
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(ParcoursIndividualiseConsultBean.class);
	
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	private ResourceBundle bundleCommon;
	private ResourceBundle parcoursIndividualiseBundle;
	@ManagedProperty("#{dossierService}")
	private DossierService dossierService;
	@ManagedProperty("#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;
	@ManagedProperty("#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;
	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty("#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;
	@ManagedProperty("#{bacService}")
	private BacService bacService;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	@ManagedProperty("#{parcoursTypeService}")
	private ParcoursTypeService parcoursTypeService;
	@ManagedProperty("#{parcoursIndividualiseService}")
	private ParcoursIndividualiseService parcoursIndividualiseService;
	@ManagedProperty("#{rattachementMcService}")
	private RattachementMcService rattachementMcService;
	private DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto;	
	private String situation;
	private List<ParcoursIndividualiseMatiereDto> parcoursIndividualiseMatiereList;
	private List<ParcoursIndividualiseDto> parcoursIndividualiseList;
	private int creditsDemande;
	private int creditsAtteint;
	private HashMap<String, Integer> mapUeCredits = new HashMap<String, Integer>();
	private ParcoursIndividualiseDto searchDto;
	private List<SelectItem> offreFormationList;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> periodeList;
	private PeriodeDto periodeDto;
	@ManagedProperty("#{periodeService}")
	private PeriodeService periodeService;
	
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
	 * [ParcoursIndividualiseBean.ParcoursIndividualiseBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
	 */
	public ParcoursIndividualiseConsultBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		parcoursIndividualiseBundle = facesContext.getApplication()
				.getResourceBundle(facesContext,
						CursusConstants.PARCOURS_INDIVIDUALISE_BUNDLE_MSG_NAME);

	}

	@PostConstruct
	public void init() {
		
		initDetail();
		searchDto = new ParcoursIndividualiseDto();
		loadAnneeAcademique();
		//loadPeriodes();
	}
/**
 * 
 * [ParcoursIndividualiseConsultBean.initDetail] Method 
 * @author BELDI Jamel  on : 13 ao�t 2014  17:01:17
 */
	private void initDetail(){
		parcoursIndividualiseMatiereList = new ArrayList<ParcoursIndividualiseMatiereDto>();
		periodeDto = new PeriodeDto();
		mapUeCredits = new HashMap<String, Integer>();
		situation = null;
		creditsDemande = 0;
		creditsAtteint = 0;
		dossierInscriptionAdministrativeDto = null;	
	}
	/**
	 * 
	 * [ParcoursIndividualiseConsultBean.loadAnneeAcademique] Method 
	 * @author BELDI Jamel  on : 13 ao�t 2014  17:01:27
	 */
	public void loadAnneeAcademique() {
		try {
			List<AnneeAcademiqueDto> list = anneeAcademiqueService.findAll();
			anneeAcademiqueList = new ArrayList<SelectItem>();
			for (AnneeAcademiqueDto annee : list) {
				SelectItem item = new SelectItem(annee.getId(), annee.getCode());
				anneeAcademiqueList.add(item);
			}
		} catch (Exception e) {

		}
	}
	
	/**
	 * 
	 * [ParcoursIndividualiseConsultBean.loadAnneeAcademique] Method 
	 * @author BELDI Jamel  on : 8 sept. 2014  16:16:00
	 */
	public void loadPeriodes() {
//		try {
//			List<PeriodeDto> list = periodeService.findAll();
//			periodeList = new ArrayList<SelectItem>();
//			for (PeriodeDto periode : list) {
//				SelectItem item = new SelectItem(periode.getId(), periode.getLibelleLongLt());
//				periodeList.add(item);
//			}
//		} catch (Exception e) {
//
//		}
		try {
			periodeList = new ArrayList<SelectItem>();
			if(searchDto.getOuvertureOffreFormationId()==null){
				return;
			}
			OuvertureOffreFormationDto _oof = ouvertureOffreFormationService.findById(searchDto.getOuvertureOffreFormationId());
			List<PeriodeDto> list = periodeService.findByCycleId(_oof.getCycleId());
			
			for (PeriodeDto periode : list) {
				SelectItem item = new SelectItem(periode.getId(),
						periode.getLibelleLongLt());
				periodeList.add(item);
			}
		} catch (Exception e) {

		}
	}
	/**
	 * [ParcoursIndividualiseValidateBean.loadParcoursIndividualiseToValidate] Method 
	 * @author BELDI Jamel  on : 10 ao�t 2014  15:54:06
	 */
	public void search(){
		FacesMessage msg = new FacesMessage();
		try {
		parcoursIndividualiseList	= new ArrayList<ParcoursIndividualiseDto>();
		searchDto.setRefCodeEtablissement(sessionBean.getCodeEtablissement());
		initDetail();
		List<ParcoursIndividualiseDto> _parcoursIndividualiseList = parcoursIndividualiseService.findAdvanced(searchDto);
		if (_parcoursIndividualiseList!=null && !_parcoursIndividualiseList.isEmpty()){
			
			for (ParcoursIndividualiseDto parcoursIndividualiseDto : _parcoursIndividualiseList) {
				DossierInscriptionAdministrativeDto _dossierInscriptionDto = loadDossierInscription(parcoursIndividualiseDto.getDossierInscriptionAdministrativeId().intValue());
				mapper.map(_dossierInscriptionDto, parcoursIndividualiseDto);
				parcoursIndividualiseList.add(parcoursIndividualiseDto);
			}
		}
		} catch (Exception e) {
			parcoursIndividualiseList = new ArrayList<ParcoursIndividualiseDto>();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec")
					+ ": " + bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}
	
	/**
	 * [ParcoursIndividualiseBean.calculCredits] Method calculate Credits
	 * @author BELDI Jamel  on : 20 juil. 2014  12:11:16
	 */
	private void calculCredits() {
		if (parcoursIndividualiseMatiereList != null
				&& !parcoursIndividualiseMatiereList.isEmpty()) {
			creditsDemande = 0;
			creditsAtteint = 0;
			for (ParcoursIndividualiseMatiereDto parcoursIndividualiseMatiereDto : parcoursIndividualiseMatiereList) {
				if (!mapUeCredits.containsKey(parcoursIndividualiseMatiereDto
						.getUeCode())) {
					mapUeCredits.put(
							parcoursIndividualiseMatiereDto.getUeCode(),
							parcoursIndividualiseMatiereDto.getUeCredits());
					creditsDemande = creditsDemande + parcoursIndividualiseMatiereDto.getUeCredits();
				}
				if (  parcoursIndividualiseMatiereDto.getOptionnelle()==null || parcoursIndividualiseMatiereDto.getOptionnelle() != true
						|| parcoursIndividualiseMatiereDto.isChoix()) {
					creditsAtteint = creditsAtteint
							+ parcoursIndividualiseMatiereDto.getMcCredits();
				}
			}
			
		}
		
	}
	
	
	
	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage();
		ParcoursIndividualiseDto _parcours = ((ParcoursIndividualiseDto) event
				.getObject());
		initDetail();
		try{
		loadParcoursIndividualise(_parcours.getId().intValue())	;
		dossierInscriptionAdministrativeDto = loadDossierInscription(_parcours.getDossierInscriptionAdministrativeId().intValue());
		calculCredits();
		} catch (Exception e) {
			
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") 
					+ ": " + bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}
	
	/**
	 * [ParcoursIndividualiseBean.loadParcoursIndividualise] Method
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 14:15:40
	 * @param offreFormationId
	 * @param idInscriptionAdministrative
	 */
	private void loadParcoursIndividualise(int id) {
		//TODO
		ParcoursIndividualiseDto _parcoursIndividualiseDto = parcoursIndividualiseService.findById(id);
		if(_parcoursIndividualiseDto!=null) parcoursIndividualiseMatiereList =_parcoursIndividualiseDto.getParcoursIndividualiseMatiereList(); 
//			parcoursIndividualiseMatiereList = parcoursIndividualiseService
//					.findById(id);
//			ParcoursIndividualiseMatiereDto _parcours = parcoursIndividualiseMatiereList.get(0);
//			periodeDto.setId(_parcours.getIdPeriode());
//			periodeDto.setCode(_parcours.getCodePeriode());
//			periodeDto.setLibelleLongLt(_parcours.getLibellePeriode());
//				List<SituationI18nDto> situations= situationService.findByIdSituationEntite(_parcours.getSituationId());
//				for (SituationI18nDto situationI18nDto : situations) {
//					if(situationI18nDto.getLangue().equals("fr")){
//						situation = situationI18nDto.getLibelle();
//						break;
//					}
//				}
				
				
				periodeDto.setId(_parcoursIndividualiseDto.getIdPeriode());
				periodeDto.setCode(_parcoursIndividualiseDto.getCodePeriode());
				periodeDto.setLibelleLongLt(_parcoursIndividualiseDto.getLibellePeriode());
				situation = _parcoursIndividualiseDto.getSituationLibelleFr();
					
						

	}

	/**
	 * [ParcoursIndividualiseBean.loadDossierInscription] Method
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 14:15:28
	 * @throws Exception_Exception
	 */
	private DossierInscriptionAdministrativeDto loadDossierInscription(int id) throws Exception {
		DossierInscriptionAdministrativeDto _dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService.findById(id);
					

			// Initialisation de la sp�cialit�
			// dossierInscription.setRefCodeSpecialite(_of.getRefCodeSpecialite());
			if (_dossierInscriptionAdministrativeDto.getRefCodeSpecialite() != null) {
				RefSpecialiteLmdDto _specialite = refSpecialiteLmdService
						.findByIdentifiant(_dossierInscriptionAdministrativeDto
								.getRefCodeSpecialite());
				if (_specialite != null) {
					_dossierInscriptionAdministrativeDto
							.setRefLibelleSpecialite(_specialite
									.getLlSpecialite());
					_dossierInscriptionAdministrativeDto
							.setRefLibelleSpecialiteArabe(_specialite
									.getLlSpecialiteArabe());
				}
			}
			// Initialisation de la fili�re
			// dossierInscription.setRefCodeFiliere(_of.getRefCodeFiliere());
			if (_dossierInscriptionAdministrativeDto.getRefCodeFiliere() != null) {
				RefFiliereLmdDto _filiere = refFiliereLmdService
						.findByIdentifiant(_dossierInscriptionAdministrativeDto
								.getRefCodeFiliere());
				if (_filiere != null) {
					_dossierInscriptionAdministrativeDto
							.setRefLibelleFiliere(_filiere.getLlFiliere());
					_dossierInscriptionAdministrativeDto
							.setRefLibelleFiliereArabe(_filiere
									.getLlFiliereArabe());
				}
			}
			// Initialisation du domaine
			// dossierInscription.setRefCodeDomaine(_of.getRefCodeDomaine());
			if (_dossierInscriptionAdministrativeDto.getRefCodeDomaine() != null) {
				RefDomaineLMDDto _domaine = refDomaineLMDService
						.findByIdentifiant(_dossierInscriptionAdministrativeDto
								.getRefCodeDomaine());
				if (_domaine != null) {
					_dossierInscriptionAdministrativeDto
							.setRefLibelleDomaine(_domaine.getLlDomaine());
					_dossierInscriptionAdministrativeDto
							.setRefLibelleDomaineArabe(_domaine
									.getLlDomaineArabe());
				}
			}
			// Initialisation du cycle & niveau
			if (_dossierInscriptionAdministrativeDto.getRefCodeCycle() != null) {
				NomenclatureDto _cycle = nomenclatureService.findByCode(
						Const.LMD_CYCLE,
						_dossierInscriptionAdministrativeDto.getRefCodeCycle());
				if (_cycle != null) {
					_dossierInscriptionAdministrativeDto
							.setRefLibelleCycle(_cycle.getLibelleLongFr());
				}
			}
			if (_dossierInscriptionAdministrativeDto.getRefCodeNiveau() != null) {

				NomenclatureDto _niveau = nomenclatureService.findByCode(
						Const.LMD_PALIER,
						_dossierInscriptionAdministrativeDto.getRefCodeNiveau());
				if (_niveau != null) {
					_dossierInscriptionAdministrativeDto
							.setRefLibelleNiveau(_niveau.getLibelleLongFr());
					_dossierInscriptionAdministrativeDto
							.setRefLibelleNiveauArabe(_niveau
									.getLibelleLongAr());
				}
			}

			if (_dossierInscriptionAdministrativeDto.getRefEtablissementId() != null) {
				RefEtablissementDto _refEtablissementDto = refEtablissementService
						.findById(_dossierInscriptionAdministrativeDto
								.getRefEtablissementId());
				if (_refEtablissementDto != null) {
					_dossierInscriptionAdministrativeDto
							.setLlEtablissementArabe(_refEtablissementDto
									.getLlEtablissementArabe());
					_dossierInscriptionAdministrativeDto
							.setLlEtablissementLatin(_refEtablissementDto
									.getLlEtablissementLatin());
				}
			}
			// if (dossierInscriptionAdministrativeDto.getNin() != null) {
			RefIndividuDto _refIndividuDto = refIndividuService
					.findByIdentifiant(_dossierInscriptionAdministrativeDto
							.getNin());
			map(_refIndividuDto, _dossierInscriptionAdministrativeDto);
			return _dossierInscriptionAdministrativeDto;

		
	}

	/**
	 * [ParcoursIndividualiseBean.map] Method
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 14:15:33
	 * @param refIndividuDto
	 * @param dossierInscriptionAdministrativeDto
	 */
	private void map(
			RefIndividuDto refIndividuDto,
			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto) {
		if (dossierInscriptionAdministrativeDto == null) {
			dossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto();
		}
		dossierInscriptionAdministrativeDto.setIndividuId(refIndividuDto
				.getId());
		dossierInscriptionAdministrativeDto.setNin(refIndividuDto
				.getIdentifiant());
		dossierInscriptionAdministrativeDto.setIndividuNomLatin(refIndividuDto
				.getNomLatin());
		dossierInscriptionAdministrativeDto.setIndividuNomArabe(refIndividuDto
				.getNomArabe());
		dossierInscriptionAdministrativeDto
				.setIndividuPrenomArabe(refIndividuDto.getPrenomArabe());
		dossierInscriptionAdministrativeDto
				.setIndividuPrenomLatin(refIndividuDto.getPrenomLatin());
		dossierInscriptionAdministrativeDto
				.setIndividuLieuNaissance(refIndividuDto.getLieuNaissance());
		if (refIndividuDto.getDateNaissance() != null) {
			dossierInscriptionAdministrativeDto
					.setIndividuDateNaissance(refIndividuDto.getDateNaissance());
		}

	}


	
	/**
	 * 
	 * [ParcoursIndividualiseConsultBean.anneeChanged] Method 
	 * @author BELDI Jamel  on : 13 ao�t 2014  16:35:53
	 */
	public void anneeChanged() {
		loadOffreFormationOuverte();
		
	}
	/**
	 * 
	 * [ParcoursIndividualiseConsultBean.loadOffreFormationOuverte] Method 
	 * @author BELDI Jamel  on : 13 ao�t 2014  16:37:29
	 */
	public void loadOffreFormationOuverte() {
		offreFormationList = new ArrayList<SelectItem>();

		try {
			 
			
			if (searchDto.getAnneeAcademiqueId() == null) {
				return;
			}
			offreFormationList = new ArrayList<SelectItem>();
			
				OuvertureOffreFormationDto searchDto = new OuvertureOffreFormationDto();
				
					searchDto.setIdEtablissement(sessionBean.getIdEtablissement());
					searchDto.setAnneeAcademiqueId(searchDto.getAnneeAcademiqueId());
				searchDto.setEstOuverte(true);
				List<OuvertureOffreFormationDto> list = ouvertureOffreFormationService
						.findAdvanced(searchDto);
				for (OuvertureOffreFormationDto ouvertureOffreFormationDto : list) {
					OffreFormationDto offreFormationDto = offreFormationService
							.findById(ouvertureOffreFormationDto
									.getOffreFormationId());
							SelectItem selectItem = new SelectItem(
									ouvertureOffreFormationDto.getId(),offreFormationDto.getLibelleLongFr());							
							offreFormationList.add(selectItem);

					}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 
	 * [ParcoursIndividualiseConsultBean.findOfItem] Method 
	 * @author BELDI Jamel  on : 13 ao�t 2014  16:43:16
	 * @param label
	 * @return
	 */
	private SelectItem findOfItem(String label) {
		for (SelectItem _item : offreFormationList) {
			if (_item.getLabel().equals(label)) {
				return _item;
			}
		}
		return null;
	}

	/**
	 * [ParcoursIndividualiseBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [ParcoursIndividualiseBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [ParcoursIndividualiseBean.dossierService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the dossierService
	 */
	public DossierService getDossierService() {
		return dossierService;
	}

	/**
	 * [ParcoursIndividualiseBean.dossierService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param dossierService
	 *            the dossierService to set
	 */
	public void setDossierService(DossierService dossierService) {
		this.dossierService = dossierService;
	}

	/**
	 * [ParcoursIndividualiseBean.dossierEtudiantService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the dossierEtudiantService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [ParcoursIndividualiseBean.dossierEtudiantService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param dossierEtudiantService
	 *            the dossierEtudiantService to set
	 */
	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	/**
	 * [ParcoursIndividualiseBean.dossierInscriptionAdministrativeService]
	 * Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [ParcoursIndividualiseBean.dossierInscriptionAdministrativeService]
	 * Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param dossierInscriptionAdministrativeService
	 *            the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [ParcoursIndividualiseBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [ParcoursIndividualiseBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [ParcoursIndividualiseBean.offreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [ParcoursIndividualiseBean.offreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [ParcoursIndividualiseBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [ParcoursIndividualiseBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [ParcoursIndividualiseBean.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [ParcoursIndividualiseBean.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [ParcoursIndividualiseBean.bacService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the bacService
	 */
	public BacService getBacService() {
		return bacService;
	}

	/**
	 * [ParcoursIndividualiseBean.bacService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param bacService
	 *            the bacService to set
	 */
	public void setBacService(BacService bacService) {
		this.bacService = bacService;
	}

	/**
	 * [ParcoursIndividualiseBean.situationService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [ParcoursIndividualiseBean.situationService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [ParcoursIndividualiseBean.dossierInscriptionAdministrativeDto] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the dossierInscriptionAdministrativeDto
	 */
	public DossierInscriptionAdministrativeDto getDossierInscriptionAdministrativeDto() {
		return dossierInscriptionAdministrativeDto;
	}

	/**
	 * [ParcoursIndividualiseBean.dossierInscriptionAdministrativeDto] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param dossierInscriptionAdministrativeDto
	 *            the dossierInscriptionAdministrativeDto to set
	 */
	public void setDossierInscriptionAdministrativeDto(
			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto) {
		this.dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeDto;
	}

	/**
	 * [ParcoursIndividualiseBean.flash] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 11:41:38
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [ParcoursIndividualiseBean.flash] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 11:41:38
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [ParcoursIndividualiseBean.parcoursTypeService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 14:26:53
	 * @return the parcoursTypeService
	 */
	public ParcoursTypeService getParcoursTypeService() {
		return parcoursTypeService;
	}

	/**
	 * [ParcoursIndividualiseBean.parcoursTypeService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 14:26:53
	 * @param parcoursTypeService
	 *            the parcoursTypeService to set
	 */
	public void setParcoursTypeService(ParcoursTypeService parcoursTypeService) {
		this.parcoursTypeService = parcoursTypeService;
	}

	/**
	 * [ParcoursIndividualiseBean.parcoursIndividualiseMatiereList] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 14:26:53
	 * @return the parcoursIndividualiseMatiereList
	 */
	public List<ParcoursIndividualiseMatiereDto> getParcoursIndividualiseMatiereList() {
		return parcoursIndividualiseMatiereList;
	}

	/**
	 * [ParcoursIndividualiseBean.parcoursIndividualiseMatiereList] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 14:26:53
	 * @param parcoursIndividualiseMatiereList
	 *            the parcoursIndividualiseMatiereList to set
	 */
	public void setParcoursIndividualiseMatiereList(
			List<ParcoursIndividualiseMatiereDto> parcoursIndividualiseMatiereList) {
		this.parcoursIndividualiseMatiereList = parcoursIndividualiseMatiereList;
	}

	/**
	 * [ParcoursIndividualiseBean.parcoursIndividualiseService] Getter
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 11:04:45
	 * @return the parcoursIndividualiseService
	 */
	public ParcoursIndividualiseService getParcoursIndividualiseService() {
		return parcoursIndividualiseService;
	}

	/**
	 * [ParcoursIndividualiseBean.parcoursIndividualiseService] Setter
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 11:04:45
	 * @param parcoursIndividualiseService
	 *            the parcoursIndividualiseService to set
	 */
	public void setParcoursIndividualiseService(
			ParcoursIndividualiseService parcoursIndividualiseService) {
		this.parcoursIndividualiseService = parcoursIndividualiseService;
	}

	/**
	 * [ParcoursIndividualiseBean.rattachementMcService] Getter
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 12:37:36
	 * @return the rattachementMcService
	 */
	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	/**
	 * [ParcoursIndividualiseBean.rattachementMcService] Setter
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 12:37:36
	 * @param rattachementMcService
	 *            the rattachementMcService to set
	 */
	public void setRattachementMcService(
			RattachementMcService rattachementMcService) {
		this.rattachementMcService = rattachementMcService;
	}

	/**
	 * [ParcoursIndividualiseBean.creditsAtteint] Getter
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 16:17:19
	 * @return the creditsAtteint
	 */
	public int getCreditsAtteint() {
		
		return creditsAtteint;
	}

	/**
	 * [ParcoursIndividualiseBean.creditsAtteint] Setter
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 16:17:19
	 * @param creditsAtteint
	 *            the creditsAtteint to set
	 */
	public void setCreditsAtteint(int creditsAtteint) {
		this.creditsAtteint = creditsAtteint;
	}

	

	/**
	 * [ParcoursIndividualiseBean.creditsDemande] Getter
	 * 
	 * @author BELDI Jamel on : 17 juil. 2014 12:44:25
	 * @return the creditsDemande
	 */
	public int getCreditsDemande() {
		return creditsDemande;
	}

	/**
	 * [ParcoursIndividualiseBean.creditsDemande] Setter
	 * 
	 * @author BELDI Jamel on : 17 juil. 2014 12:44:25
	 * @param creditsDemande
	 *            the creditsDemande to set
	 */
	public void setCreditsDemande(int creditsDemande) {
		
		this.creditsDemande = creditsDemande;
	}



	/**
	 * [ParcoursIndividualiseBean.situation] Getter 
	 * @author BELDI Jamel on : 20 juil. 2014  14:12:16
	 * @return the situation
	 */
	public String getSituation() {
		return situation;
	}

	/**
	 * [ParcoursIndividualiseBean.situation] Setter 
	 * @author BELDI Jamel on : 20 juil. 2014  14:12:16
	 * @param situation the situation to set
	 */
	public void setSituation(String situation) {
		this.situation = situation;
	}

	/**
	 * [ParcoursIndividualiseValidateBean.parcoursIndividualiseList] Getter 
	 * @author BELDI Jamel on : 10 ao�t 2014  13:53:10
	 * @return the parcoursIndividualiseList
	 */
	public List<ParcoursIndividualiseDto> getParcoursIndividualiseList() {
		return parcoursIndividualiseList;
	}

	/**
	 * [ParcoursIndividualiseValidateBean.parcoursIndividualiseList] Setter 
	 * @author BELDI Jamel on : 10 ao�t 2014  13:53:10
	 * @param parcoursIndividualiseList the parcoursIndividualiseList to set
	 */
	public void setParcoursIndividualiseList(
			List<ParcoursIndividualiseDto> parcoursIndividualiseList) {
		this.parcoursIndividualiseList = parcoursIndividualiseList;
	}

	/**
	 * [ParcoursIndividualiseValidateBean.mapUeCredits] Getter 
	 * @author BELDI Jamel on : 11 ao�t 2014  11:03:15
	 * @return the mapUeCredits
	 */
	public HashMap<String, Integer> getMapUeCredits() {
		return mapUeCredits;
	}

	/**
	 * [ParcoursIndividualiseValidateBean.mapUeCredits] Setter 
	 * @author BELDI Jamel on : 11 ao�t 2014  11:03:15
	 * @param mapUeCredits the mapUeCredits to set
	 */
	public void setMapUeCredits(HashMap<String, Integer> mapUeCredits) {
		this.mapUeCredits = mapUeCredits;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.searchDto] Getter 
	 * @author BELDI Jamel on : 13 ao�t 2014  11:21:48
	 * @return the searchDto
	 */
	public ParcoursIndividualiseDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.searchDto] Setter 
	 * @author BELDI Jamel on : 13 ao�t 2014  11:21:48
	 * @param searchDto the searchDto to set
	 */
	public void setSearchDto(ParcoursIndividualiseDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.offreFormationList] Getter 
	 * @author BELDI Jamel on : 13 ao�t 2014  16:34:27
	 * @return the offreFormationList
	 */
	public List<SelectItem> getOffreFormationList() {
		return offreFormationList;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.offreFormationList] Setter 
	 * @author BELDI Jamel on : 13 ao�t 2014  16:34:27
	 * @param offreFormationList the offreFormationList to set
	 */
	public void setOffreFormationList(List<SelectItem> offreFormationList) {
		this.offreFormationList = offreFormationList;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.anneeAcademiqueList] Getter 
	 * @author BELDI Jamel on : 13 ao�t 2014  16:34:27
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.anneeAcademiqueList] Setter 
	 * @author BELDI Jamel on : 13 ao�t 2014  16:34:27
	 * @param anneeAcademiqueList the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.periodeList] Getter 
	 * @author BELDI Jamel on : 13 ao�t 2014  16:34:27
	 * @return the periodeList
	 */
	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.periodeList] Setter 
	 * @author BELDI Jamel on : 13 ao�t 2014  16:34:27
	 * @param periodeList the periodeList to set
	 */
	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	public PeriodeDto getPeriodeDto() {
		return periodeDto;
	}

	/**
	 * [ParcoursIndividualiseEtudiantConsultBean.periodeDto] Setter 
	 * @author BELDI Jamel on : 8 sept. 2014  11:26:59
	 * @param periodeDto the periodeDto to set
	 */
	public void setPeriodeDto(PeriodeDto periodeDto) {
		this.periodeDto = periodeDto;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.periodeService] Getter 
	 * @author BELDI Jamel on : 8 sept. 2014  16:15:29
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.periodeService] Setter 
	 * @author BELDI Jamel on : 8 sept. 2014  16:15:29
	 * @param periodeService the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}
	
	/**
	 * 
	 * [ParcoursIndividualiseStatisticBean.ofChanged] Method
	 * 
	 * @author BELDI Jamel on : 14 ao�t 2014 12:34:36
	 */
	public void ofChanged() {
		
		loadPeriodes();
		 searchDto.setIdPeriode(null);
	}

	/**
	 * [ParcoursIndividualiseConsultBean.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:09:07
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:09:07
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.refIndividuService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:09:07
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.refIndividuService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:09:07
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.refSpecialiteLmdService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:09:07
	 * @return the refSpecialiteLmdService
	 */
	public RefSpecialiteLmdService getRefSpecialiteLmdService() {
		return refSpecialiteLmdService;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.refSpecialiteLmdService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:09:07
	 * @param refSpecialiteLmdService the refSpecialiteLmdService to set
	 */
	public void setRefSpecialiteLmdService(
			RefSpecialiteLmdService refSpecialiteLmdService) {
		this.refSpecialiteLmdService = refSpecialiteLmdService;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.refFiliereLmdService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:09:07
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.refFiliereLmdService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:09:07
	 * @param refFiliereLmdService the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.refDomaineLMDService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:09:07
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.refDomaineLMDService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:09:07
	 * @param refDomaineLMDService the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.refEtablissementService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:10:25
	 * @return the refEtablissementService
	 */
	public RefEtablissementService getRefEtablissementService() {
		return refEtablissementService;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.refEtablissementService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:10:25
	 * @param refEtablissementService the refEtablissementService to set
	 */
	public void setRefEtablissementService(
			RefEtablissementService refEtablissementService) {
		this.refEtablissementService = refEtablissementService;
	}
	
	
}
