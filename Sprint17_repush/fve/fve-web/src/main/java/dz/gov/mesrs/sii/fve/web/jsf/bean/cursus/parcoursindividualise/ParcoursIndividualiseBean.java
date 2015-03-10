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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ParcoursIndividualiseDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ParcoursIndividualiseMatiereDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierService;
import dz.gov.mesrs.sii.fve.business.service.cursus.ParcoursIndividualiseService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.ParcoursTypeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.ReferentielHelper;
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
 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
 */
@ManagedBean(name = "parcoursIndividualiseBean")
@RequestScoped
public class ParcoursIndividualiseBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:10
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ParcoursIndividualiseBean.class);
	private int exception;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
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
	@ManagedProperty(value = "#{referentielHelper}")
	private ReferentielHelper referentielHelper;
	@ManagedProperty("#{parcoursTypeService}")
	private ParcoursTypeService parcoursTypeService;
	@ManagedProperty("#{parcoursIndividualiseService}")
	private ParcoursIndividualiseService parcoursIndividualiseService;
	@ManagedProperty("#{rattachementMcService}")
	private RattachementMcService rattachementMcService;
	private DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto;
	@ManagedProperty("#{param.nin}")
	private String nin;
	@ManagedProperty("#{param.credit}")
	private String credit;

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

	private String situation;

	private List<ParcoursIndividualiseMatiereDto> parcoursIndividualiseMatiereList;

	private int creditsDemande;
	private int creditsAtteint;
	private HashMap<String, Integer> mapUeCredits = new HashMap<String, Integer>();

	/**
	 * [ParcoursIndividualiseBean.ParcoursIndividualiseBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
	 */
	public ParcoursIndividualiseBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
				CursusConstants.COMMON_BUNDLE_MSG_NAME);
		parcoursIndividualiseBundle = facesContext.getApplication().getResourceBundle(facesContext,
				CursusConstants.PARCOURS_INDIVIDUALISE_BUNDLE_MSG_NAME);

	}

	@PostConstruct
	public void init() {
		FacesMessage msg = new FacesMessage();
		try {
			loadDossierInscription();
			if (dossierInscriptionAdministrativeDto != null
					&& dossierInscriptionAdministrativeDto.getOffreFormationId() != null
					&& dossierInscriptionAdministrativeDto.getOffreFormationId() != 0) {
				loadParcoursIndividualise(dossierInscriptionAdministrativeDto.getOffreFormationId(),
						dossierInscriptionAdministrativeDto.getId());

				calculCredits();

			}
		} catch (Exception e) {
			dossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto();
			exception = 1;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + exception + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
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
	private void loadParcoursIndividualise(Integer offreFormationId, int idInscriptionAdministrative) {

		if (idInscriptionAdministrative != 0) {
			// TODO
			ParcoursIndividualiseDto _parcoursIndividualiseDto = parcoursIndividualiseService
					.findByIdInscriptionAdministrative(idInscriptionAdministrative);
			if (_parcoursIndividualiseDto != null)
				parcoursIndividualiseMatiereList = _parcoursIndividualiseDto.getParcoursIndividualiseMatiereList();
			if (parcoursIndividualiseMatiereList == null || parcoursIndividualiseMatiereList.isEmpty()) {
				situation = "En cours de cr�ation";
				List<RattachementMcDto> _rattachements = rattachementMcService.findByOffreFormation(offreFormationId);
				if (_rattachements != null && !_rattachements.isEmpty()) {
					parcoursIndividualiseMatiereList = new ArrayList<ParcoursIndividualiseMatiereDto>();
					for (RattachementMcDto rattachementMcDto : _rattachements) {
						ParcoursIndividualiseMatiereDto parcoursIndividualiseMatiereDto = new ParcoursIndividualiseMatiereDto();
						mapper.map(rattachementMcDto, parcoursIndividualiseMatiereDto);
						parcoursIndividualiseMatiereDto
								.setDossierInscriptionAdministrativeId(idInscriptionAdministrative);
						parcoursIndividualiseMatiereList.add(parcoursIndividualiseMatiereDto);

					}
				}
			} else {
				// List<SituationI18nDto> situations=
				// situationService.findByIdSituationEntite(parcoursIndividualiseMatiereList.get(0).getSituationId());
				// for (SituationI18nDto situationI18nDto : situations) {
				// if(situationI18nDto.getLangue().equals("fr")){
				// situation = situationI18nDto.getLibelle();
				// break;
				// }
				// }
				situation = _parcoursIndividualiseDto.getSituationLibelleFr();
			}
		}

	}

	/**
	 * [ParcoursIndividualiseBean.loadDossierInscription] Method
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 14:15:28
	 * @throws Exception_Exception
	 */
	private void loadDossierInscription() throws Exception {

		List<DossierEtudiantDto> listDossierEtudiants = dossierEtudiantService.findByIdIndividu(this.sessionBean
				.getSessionBean().getUser().getId(), sessionBean.getIdEtablissement());
		if (listDossierEtudiants != null && !listDossierEtudiants.isEmpty()) {
			DossierEtudiantDto _dossierEtudiantDto = listDossierEtudiants.get(0);
			dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
					.findPresentDossierInscriptionForEtudiant(_dossierEtudiantDto.getId());

			// Initialisation de la sp�cialit�
			// dossierInscription.setRefCodeSpecialite(_of.getRefCodeSpecialite());
			if (dossierInscriptionAdministrativeDto.getRefCodeSpecialite() != null) {
				RefSpecialiteLmdDto _specialite = refSpecialiteLmdService
						.findByIdentifiant(dossierInscriptionAdministrativeDto.getRefCodeSpecialite());
				if (_specialite != null) {
					dossierInscriptionAdministrativeDto.setRefLibelleSpecialite(_specialite.getLlSpecialite());
					dossierInscriptionAdministrativeDto
							.setRefLibelleSpecialiteArabe(_specialite.getLlSpecialiteArabe());
				}
			}
			// Initialisation de la fili�re
			// dossierInscription.setRefCodeFiliere(_of.getRefCodeFiliere());
			if (dossierInscriptionAdministrativeDto.getRefCodeFiliere() != null) {
				RefFiliereLmdDto _filiere = refFiliereLmdService.findByIdentifiant(dossierInscriptionAdministrativeDto
						.getRefCodeFiliere());
				if (_filiere != null) {
					dossierInscriptionAdministrativeDto.setRefLibelleFiliere(_filiere.getLlFiliere());
					dossierInscriptionAdministrativeDto.setRefLibelleFiliereArabe(_filiere.getLlFiliereArabe());
				}
			}
			// Initialisation du domaine
			// dossierInscription.setRefCodeDomaine(_of.getRefCodeDomaine());
			if (dossierInscriptionAdministrativeDto.getRefCodeDomaine() != null) {
				RefDomaineLMDDto _domaine = refDomaineLMDService.findByIdentifiant(dossierInscriptionAdministrativeDto
						.getRefCodeDomaine());
				if (_domaine != null) {
					dossierInscriptionAdministrativeDto.setRefLibelleDomaine(_domaine.getLlDomaine());
					dossierInscriptionAdministrativeDto.setRefLibelleDomaineArabe(_domaine.getLlDomaineArabe());
				}
			}
			// Initialisation du cycle & niveau
			if (dossierInscriptionAdministrativeDto.getRefCodeCycle() != null) {
				NomenclatureDto _cycle = nomenclatureService.findByCode(Const.LMD_CYCLE,
						dossierInscriptionAdministrativeDto.getRefCodeCycle());
				if (_cycle != null) {
					dossierInscriptionAdministrativeDto.setRefLibelleCycle(_cycle.getLibelleLongFr());
				}
			}
			if (dossierInscriptionAdministrativeDto.getRefCodeNiveau() != null) {

				NomenclatureDto _niveau = nomenclatureService.findByCode(Const.LMD_PALIER,
						dossierInscriptionAdministrativeDto.getRefCodeNiveau());
				if (_niveau != null) {
					dossierInscriptionAdministrativeDto.setRefLibelleNiveau(_niveau.getLibelleLongFr());
					dossierInscriptionAdministrativeDto.setRefLibelleNiveauArabe(_niveau.getLibelleLongAr());
				}
			}
			// initialisation du type d'inscription
			if (dossierInscriptionAdministrativeDto.getRefCodeTypeInscription() != null) {
				NomenclatureDto _ncDto = referentielHelper.getNomenclatureItemValue(
						"Const.LMD_TYPE_DOSSIER_INSCRIPTION",
						dossierInscriptionAdministrativeDto.getRefCodeTypeInscription());

				if (_ncDto != null)
					dossierInscriptionAdministrativeDto.setRefLibelleTypeInscription(_ncDto.getLibelleLongFr());
			}
			if (dossierInscriptionAdministrativeDto.getRefEtablissementId() != null) {
				RefEtablissementDto _refEtablissementDto = refEtablissementService
						.findById(dossierInscriptionAdministrativeDto.getRefEtablissementId());
				if (_refEtablissementDto != null) {
					dossierInscriptionAdministrativeDto.setLlEtablissementArabe(_refEtablissementDto
							.getLlEtablissementArabe());
					dossierInscriptionAdministrativeDto.setLlEtablissementLatin(_refEtablissementDto
							.getLlEtablissementLatin());
				}
			}
			// if (dossierInscriptionAdministrativeDto.getNin() != null) {
			RefIndividuDto _refIndividuDto = refIndividuService.findByIdentifiant(dossierInscriptionAdministrativeDto
					.getNin());
			map(_refIndividuDto, dossierInscriptionAdministrativeDto);

		}
	}

	/**
	 * [ParcoursIndividualiseBean.map] Method
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 14:15:33
	 * @param refIndividuDto
	 * @param dossierInscriptionAdministrativeDto
	 */
	private void map(RefIndividuDto refIndividuDto,
			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto) {
		if (dossierInscriptionAdministrativeDto == null) {
			dossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto();
		}
		dossierInscriptionAdministrativeDto.setIndividuId(refIndividuDto.getId());
		dossierInscriptionAdministrativeDto.setNin(refIndividuDto.getIdentifiant());
		dossierInscriptionAdministrativeDto.setIndividuNomLatin(refIndividuDto.getNomLatin());
		dossierInscriptionAdministrativeDto.setIndividuNomArabe(refIndividuDto.getNomArabe());
		dossierInscriptionAdministrativeDto.setIndividuPrenomArabe(refIndividuDto.getPrenomArabe());
		dossierInscriptionAdministrativeDto.setIndividuPrenomLatin(refIndividuDto.getPrenomLatin());
		dossierInscriptionAdministrativeDto.setIndividuLieuNaissance(refIndividuDto.getLieuNaissance());
		if (refIndividuDto.getDateNaissance() != null) {
			dossierInscriptionAdministrativeDto.setIndividuDateNaissance(refIndividuDto.getDateNaissance());
		}

	}

	/**
	 * [ParcoursIndividualiseBean.calculCredits] Method
	 * 
	 * @author BELDI Jamel on : 20 juil. 2014 12:11:16
	 */
	private void calculCredits() {
		if (parcoursIndividualiseMatiereList != null && !parcoursIndividualiseMatiereList.isEmpty()) {

			for (ParcoursIndividualiseMatiereDto parcoursIndividualiseMatiereDto : parcoursIndividualiseMatiereList) {
				if (!mapUeCredits.containsKey(parcoursIndividualiseMatiereDto.getUeCode())) {
					mapUeCredits.put(parcoursIndividualiseMatiereDto.getUeCode(),
							parcoursIndividualiseMatiereDto.getUeCredits());
					creditsDemande = creditsDemande + parcoursIndividualiseMatiereDto.getUeCredits();
				}
				if ((credit == null || credit.trim().equals("null") || credit.trim().equals(""))
						&& (parcoursIndividualiseMatiereDto.getOptionnelle() == null
								|| parcoursIndividualiseMatiereDto.getOptionnelle() != true || parcoursIndividualiseMatiereDto
									.isChoix())) {
					creditsAtteint = creditsAtteint + parcoursIndividualiseMatiereDto.getMcCredits();

				}

			}
			credit = creditsAtteint + "";
		}

		if (credit == null || credit.trim().equals("null") || credit.trim().equals("")) {
			credit = "0";
			creditsAtteint = Integer.parseInt(credit);
		}
	}

	/**
	 * [ParcoursIndividualiseBean.saveParcoursIndividualise] Method
	 * 
	 * @author BELDI Jamel on : 17 juil. 2014 11:36:26
	 */
	public void saveParcoursIndividualise() {
		FacesMessage msg = new FacesMessage();
		try {
			if (parcoursIndividualiseMatiereList == null || parcoursIndividualiseMatiereList.isEmpty()) {
				return;
			}
			if (creditsDemande != Integer.parseInt(credit)) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundleCommon.getString("error_echec") + ": "
						+ parcoursIndividualiseBundle.getString("parcours_individualise_credits_atteint_msg"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			parcoursIndividualiseMatiereList.get(0).setSituationId(
					situationService.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_PARCOURS_INDIVIDUALISE_CODE, UtilConstants.SITUTAION_CREEE_CODE)
							.getId());
			parcoursIndividualiseService.insertOrUpdate(parcoursIndividualiseMatiereList);
			loadParcoursIndividualise(dossierInscriptionAdministrativeDto.getOffreFormationId(),
					dossierInscriptionAdministrativeDto.getId());
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			String summary = /*
							 * (dossierTransfertId == null || dossierTransfertId
							 * .trim().equals("null")) ?
							 */"msg_success_enregistrement"
			/* : "msg_success_modification" */;
			msg.setSummary(bundleCommon.getString("msg_success") + ": " + bundleCommon.getString(summary));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
	}

	/**
	 * [ParcoursIndividualiseBean.soumettreParcoursIndividualise] Method
	 * 
	 * @author BELDI Jamel on : 17 juil. 2014 11:36:32
	 */
	public void soumettreParcoursIndividualise() {
		FacesMessage msg = new FacesMessage();
		try {
			if (parcoursIndividualiseMatiereList == null || parcoursIndividualiseMatiereList.isEmpty()) {
				return;
			}
			// parcoursIndividualiseMatiereList.get(0).setSituationId(
			// situationService.findBySituationEntiteByCodeSituation(
			// UtilConstants.ENTITE_PARCOURS_INDIVIDUALISE_CODE,
			// UtilConstants.SITUTAION_SOUMISE_VALIDATION_CODE)
			// .getId());
			boolean _result = parcoursIndividualiseService.updateSituation(
					parcoursIndividualiseMatiereList.get(0).getParcoursIndividualiseId(),
					situationService.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_PARCOURS_INDIVIDUALISE_CODE,
							UtilConstants.SITUTAION_SOUMISE_VALIDATION_CODE).getId());
			if (_result) {
				loadParcoursIndividualise(dossierInscriptionAdministrativeDto.getOffreFormationId(),
						dossierInscriptionAdministrativeDto.getId());
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				String summary = "parcours_individualise_success_soumission_msg";
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ parcoursIndividualiseBundle.getString(summary));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleCommon.getString("error_echec") + ": "
						+ bundleCommon.getString("error_echec_inconnue")

				);
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
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
	public void setDossierEtudiantService(DossierEtudiantService dossierEtudiantService) {
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
	public void setOuvertureOffreFormationService(OuvertureOffreFormationService ouvertureOffreFormationService) {
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
	public void setOffreFormationService(OffreFormationService offreFormationService) {
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
	public void setAnneeAcademiqueService(AnneeAcademiqueService anneeAcademiqueService) {
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
	 * [ParcoursIndividualiseBean.referentielHelper] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 11:41:38
	 * @return the referentielHelper
	 */
	public ReferentielHelper getReferentielHelper() {
		return referentielHelper;
	}

	/**
	 * [ParcoursIndividualiseBean.referentielHelper] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 11:41:38
	 * @param referentielHelper
	 *            the referentielHelper to set
	 */
	public void setReferentielHelper(ReferentielHelper referentielHelper) {
		this.referentielHelper = referentielHelper;
	}

	/**
	 * [ParcoursIndividualiseBean.nin] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 11:41:38
	 * @return the nin
	 */
	public String getNin() {
		return nin;
	}

	/**
	 * [ParcoursIndividualiseBean.nin] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 11:41:38
	 * @param nin
	 *            the nin to set
	 */
	public void setNin(String nin) {
		this.nin = nin;
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
	public void setParcoursIndividualiseService(ParcoursIndividualiseService parcoursIndividualiseService) {
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
	public void setRattachementMcService(RattachementMcService rattachementMcService) {
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
	 * [ParcoursIndividualiseBean.onChoixChanged] Method
	 * 
	 * @author BELDI Jamel on : 17 juil. 2014 10:06:57
	 * @param event
	 */
	public void onChoixChanged(AjaxBehaviorEvent event) {
		SelectBooleanCheckbox permit = (SelectBooleanCheckbox) event.getComponent();
		ParcoursIndividualiseMatiereDto _selectedRecord = (ParcoursIndividualiseMatiereDto) permit.getAttributes().get(
				"selectedRecord");

		boolean _checked = (Boolean) permit.getValue();
		if (credit != null && !credit.trim().equals("null") && !credit.trim().equals("")) {

			creditsAtteint = Integer.parseInt(credit);
		}

		if (_checked) {
			creditsAtteint = creditsAtteint + _selectedRecord.getMcCredits();
			// creditsAtteint = creditsAtteint + _selectedRecord.getMcCredits();
		} else {
			creditsAtteint = creditsAtteint - _selectedRecord.getMcCredits();
			// creditsAtteint = creditsAtteint - _selectedRecord.getMcCredits();

		}
		credit = creditsAtteint + "";

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
	 * [ParcoursIndividualiseBean.credit] Getter
	 * 
	 * @author BELDI Jamel on : 17 juil. 2014 17:07:05
	 * @return the credit
	 */
	public String getCredit() {
		return credit;
	}

	/**
	 * [ParcoursIndividualiseBean.credit] Setter
	 * 
	 * @author BELDI Jamel on : 17 juil. 2014 17:07:05
	 * @param credit
	 *            the credit to set
	 */
	public void setCredit(String credit) {
		this.credit = credit;

	}

	/**
	 * [ParcoursIndividualiseBean.situation] Getter
	 * 
	 * @author BELDI Jamel on : 20 juil. 2014 14:12:16
	 * @return the situation
	 */
	public String getSituation() {
		return situation;
	}

	/**
	 * [ParcoursIndividualiseBean.situation] Setter
	 * 
	 * @author BELDI Jamel on : 20 juil. 2014 14:12:16
	 * @param situation
	 *            the situation to set
	 */
	public void setSituation(String situation) {
		this.situation = situation;
	}

	/**
	 * [ParcoursIndividualiseBean.loginBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 sept. 2014 17:52:36
	 * @return the loginBean
	 */
	public LoginBean getLoginBean() {
		return loginBean;
	}

	/**
	 * [ParcoursIndividualiseBean.loginBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 sept. 2014 17:52:36
	 * @param loginBean
	 *            the loginBean to set
	 */
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	/**
	 * [ParcoursIndividualiseBean.nomenclatureService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:04:10
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [ParcoursIndividualiseBean.nomenclatureService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:04:10
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [ParcoursIndividualiseBean.refIndividuService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:04:10
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [ParcoursIndividualiseBean.refIndividuService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:04:10
	 * @param refIndividuService
	 *            the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [ParcoursIndividualiseBean.refSpecialiteLmdService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:04:10
	 * @return the refSpecialiteLmdService
	 */
	public RefSpecialiteLmdService getRefSpecialiteLmdService() {
		return refSpecialiteLmdService;
	}

	/**
	 * [ParcoursIndividualiseBean.refSpecialiteLmdService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:04:10
	 * @param refSpecialiteLmdService
	 *            the refSpecialiteLmdService to set
	 */
	public void setRefSpecialiteLmdService(RefSpecialiteLmdService refSpecialiteLmdService) {
		this.refSpecialiteLmdService = refSpecialiteLmdService;
	}

	/**
	 * [ParcoursIndividualiseBean.refFiliereLmdService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:04:10
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [ParcoursIndividualiseBean.refFiliereLmdService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:04:10
	 * @param refFiliereLmdService
	 *            the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	/**
	 * [ParcoursIndividualiseBean.refDomaineLMDService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:04:10
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [ParcoursIndividualiseBean.refDomaineLMDService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:04:10
	 * @param refDomaineLMDService
	 *            the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	/**
	 * [ParcoursIndividualiseBean.refEtablissementService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:05:40
	 * @return the refEtablissementService
	 */
	public RefEtablissementService getRefEtablissementService() {
		return refEtablissementService;
	}

	/**
	 * [ParcoursIndividualiseBean.refEtablissementService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 16:05:40
	 * @param refEtablissementService
	 *            the refEtablissementService to set
	 */
	public void setRefEtablissementService(RefEtablissementService refEtablissementService) {
		this.refEtablissementService = refEtablissementService;
	}

}
