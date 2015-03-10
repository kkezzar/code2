package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.diplomefinetude;

import java.io.Serializable;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
import org.springframework.orm.jpa.JpaSystemException;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AttestationFinEtudeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DiplomeFinEtudeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DiplomeFinEtudeEditionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.MentionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SignatureDiplomeFinEtudeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDiplomeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AttestationFinEtudeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DiplomeFinEtudeEditionService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DiplomeFinEtudeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierService;
import dz.gov.mesrs.sii.fve.business.service.cursus.SignatureDiplomeFinEtudeService;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanSessionService;
import dz.gov.mesrs.sii.fve.business.service.impression.ImpressionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.CycleService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique.Etudiant;
import dz.gov.mesrs.sii.fve.web.jsf.bean.impression.PrintMgrBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

/**
 * @author BELBRIK Oualid on : 11 octob 2014 14:41:09
 */
@ManagedBean(name = "diplomeFinEtudeGererBean")
@ViewScoped
public class DiplomeFinEtudeGererBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:03:10
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(DiplomeFinEtudeGererBean.class);

	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	private ResourceBundle diplomeFinEtudeBundle;
	@ManagedProperty("#{dossierService}")
	private DossierService dossierService;
	@ManagedProperty("#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty("#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	@ManagedProperty("#{bilanSessionService}")
	private BilanSessionService bilanSessionService;
	@ManagedProperty("#{diplomeFinEtudeService}")
	private DiplomeFinEtudeService diplomeFinEtudeService;
	private DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto;
	private DiplomeFinEtudeDto diplomeFinEtudeDto;
	private BilanSessionDto bilanSessionDto;
	private List<DiplomeFinEtudeDto> diplomeFinEtudeList;
	private List<DiplomeFinEtudeDto> selectedDiplomeFinEtudeList;
	private List<DiplomeFinEtudeDto> filtredDiplomeFinEtudeList;
	private List<DiplomeFinEtudeDto> diplomeFinEtudeListNonValide;
	private List<BilanSessionDto> bilanSessionList;
	// private DiplomeFinEtudeDto searchDto;
	private BilanSessionDto searchBilanDto;
	private List<SelectItem> offreFormationList;
	private List<SelectItem> anneeAcademiqueList;
	private boolean entityChange;
	private boolean activeGeneration;
	private boolean validerDeivranceChanged;
	@ManagedProperty("#{diplomeFinEtudeEditionService}")
	private DiplomeFinEtudeEditionService diplomeFinEtudeEditionService;
	@ManagedProperty("#{signatureDiplomeFinEtudeService}")
	private SignatureDiplomeFinEtudeService signatureDiplomeFinEtudeService;
	private DiplomeFinEtudeEditionDto diplomeFinEtudeEditionDto;
	private List<DiplomeFinEtudeEditionDto> diplomeFinEtudeEditionList;
	private DiplomeFinEtudeEditionDto searchEditionDto;
	private List<SelectItem> listTypesImpressionDiplome;
	@ManagedProperty("#{impressionService}")
	private ImpressionService impressionService;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	@ManagedProperty("#{printMgrBean}")
	private PrintMgrBean printMgrBean;
	private Integer anneeAcademiqueId;
	private Integer oofId;
	private List<MentionDto> mentionList;
	private AttestationFinEtudeDto attestationFinEtudeDto;
	@ManagedProperty("#{attestationFinEtudeService}")
	private AttestationFinEtudeService attestationFinEtudeService;
	@ManagedProperty("#{cycleService}")
	private CycleService cycleService;

	@ManagedProperty("#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	private List<CycleDiplomeDto> cycleDiplomeDtos;
	private DualListModel<Etudiant> etudiants;
	private List<Etudiant> source;
	private List<Etudiant> target;
	private boolean showDetail;
	private OuvertureOffreFormationDto ouvertureOffreFormationDto;
	private boolean showDiplomeList;
	private Diplome diplome;
	private List<String> diplomes;
	private List<String> selectedDiplomes;
	private boolean checkAll;
	private boolean attestation;
	private List<SignatureDiplomeFinEtudeDto> signatureDiplomeFinEtudeDtos;
	private List<SignatureDiplomeFinEtudeDto> selectedSignatureDiplomeFinEtudeDtos;
	private String diplomeLibelle;

	/**
	 * [diplomeFinEtudeBean.diplomeFinEtudeBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:03:05
	 */
	public DiplomeFinEtudeGererBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		diplomeFinEtudeBundle = facesContext.getApplication()
				.getResourceBundle(facesContext,
						CursusConstants.DIPLOME_FIN_ETUDE_BUNDLE_MSG_NAME);
		showDetail = false;
		String viewId = facesContext.getViewRoot().getViewId();
		if (viewId != null && viewId.toLowerCase().contains("attestation")) {
			attestation = true;
		}

	}

	@PostConstruct
	public void init() {
		showDetail = false;
		diplome = new Diplome();
		// earchDto = new DiplomeFinEtudeDto();
		loadAnneeAcademique();
		searchEditionDto = new DiplomeFinEtudeEditionDto();
		diplomeFinEtudeEditionDto = new DiplomeFinEtudeEditionDto();

		searchBilanDto = new BilanSessionDto();
		anneeAcademiqueId = sessionBean.getIdAnneeAcademique();
		// searchDto.setAnneeAcademiqueId(sessionBean.getIdAnneeAcademique());
		loadOffreFormationOuverte();
		searchNonValide();
	}

	/**
	 * 
	 * [diplomeFinEtudeConsultBean.loadAnneeAcademique] Method
	 * 
	 * @author BELBRIK Oualid on : 13 aoï¿½t 2014 17:01:27
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
	 * [diplomeFinEtudeValidateBean.loaddiplomeFinEtudeToValidate] Method
	 * 
	 * @author BELBRIK Oualid on : 10 aoï¿½t 2014 15:54:06
	 */
	public void findValidateDiplomes() {
		showDetail = false;
		try {
			diplomeFinEtudeList = new ArrayList<DiplomeFinEtudeDto>();

			if (oofId != null) {
				diplomeFinEtudeList = diplomeFinEtudeService
						.findValidateDiplome(oofId);
				if (diplomeFinEtudeList == null
						|| diplomeFinEtudeList.isEmpty()) {
					CommonMessagesUtils
							.showWarningMessage(diplomeFinEtudeBundle
									.getString("diplome_fin_etude_datable_list_diplome_fin_etude_no_result"));
					return;
				}
			}
			showDetail = true;
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());

		}
	}

	/**
	 * [DiplomeFinEtudeGererBean.findSignature] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 07:41:55
	 */
	public void findDiplomeSignature() {
		findEtudiant();
		findGroupedSignature();
	}

	/**
	 * [DiplomeFinEtudeGererBean.findGroupedSignature] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 07:42:56
	 */
	public void findGroupedSignature() {
		List<NomenclatureDto> ncSignatureDiplomes = utilBean
				.loadNcSignatureDiplome();
		if (ncSignatureDiplomes != null) {
			signatureDiplomeFinEtudeDtos = new ArrayList<SignatureDiplomeFinEtudeDto>();
			for (NomenclatureDto nc : ncSignatureDiplomes) {
				SignatureDiplomeFinEtudeDto signatureDiplomeFinEtudeDto = new SignatureDiplomeFinEtudeDto();
				signatureDiplomeFinEtudeDto.setNcSignatureDiplomeId(nc.getId());
				signatureDiplomeFinEtudeDto.setNcSignatureDiplomeCode(nc
						.getCode());
				signatureDiplomeFinEtudeDto
						.setNcSignatureDiplomeLibelleLongFr(nc
								.getLibelleLongFr());
				signatureDiplomeFinEtudeDto.setDateSignature(new Date());
				signatureDiplomeFinEtudeDto.setAdded(true);
				signatureDiplomeFinEtudeDtos.add(signatureDiplomeFinEtudeDto);
			}
		}
	}

	/**
	 * [DiplomeFinEtudeGererBean.findSignature] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 17:00:45
	 */
	public void findSignature() {
		List<NomenclatureDto> ncSignatureDiplomes = utilBean
				.loadNcSignatureDiplome();
		if (ncSignatureDiplomes != null) {
			signatureDiplomeFinEtudeDtos = new ArrayList<SignatureDiplomeFinEtudeDto>();
			selectedSignatureDiplomeFinEtudeDtos = new ArrayList<SignatureDiplomeFinEtudeDto>();
			for (NomenclatureDto nc : ncSignatureDiplomes) {
				SignatureDiplomeFinEtudeDto signatureDiplomeFinEtudeDto = new SignatureDiplomeFinEtudeDto();
				signatureDiplomeFinEtudeDto.setNcSignatureDiplomeId(nc.getId());
				signatureDiplomeFinEtudeDto.setNcSignatureDiplomeCode(nc
						.getCode());
				SignatureDiplomeFinEtudeDto signExist = signatureDiplomeFinEtudeService
						.findUnique(signatureDiplomeFinEtudeDto
								.getNcSignatureDiplomeId(), diplomeFinEtudeDto
								.getId(), attestation);
				if (signExist != null) {
					signatureDiplomeFinEtudeDtos.add(signExist);
					selectedSignatureDiplomeFinEtudeDtos.add(signExist);
				} else {
					signatureDiplomeFinEtudeDto.setDateSignature(new Date());
					signatureDiplomeFinEtudeDto.setAttestation(attestation);
					signatureDiplomeFinEtudeDto
							.setNcSignatureDiplomeLibelleLongFr(nc
									.getLibelleLongFr());
					signatureDiplomeFinEtudeDtos
							.add(signatureDiplomeFinEtudeDto);
				}
			}
		}
	}

	/**
	 * [DiplomeFinEtudeGererBean.findEtudiant] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 07:40:46
	 */
	public void findEtudiant() {
		showDetail = false;
		try {
			diplomeFinEtudeList = new ArrayList<DiplomeFinEtudeDto>();

			if (oofId != null) {
				diplomeFinEtudeList = diplomeFinEtudeService
						.findValidateDiplome(oofId);
				if (diplomeFinEtudeList == null
						|| diplomeFinEtudeList.isEmpty()) {
					CommonMessagesUtils
							.showWarningMessage(diplomeFinEtudeBundle
									.getString("diplome_fin_etude_datable_list_diplome_fin_etude_no_result"));
					return;
				}
				source = new ArrayList<Etudiant>();
				target = new ArrayList<Etudiant>();
				etudiants = new DualListModel<>(source, target);
				for (DiplomeFinEtudeDto diplomeFinEtudeDto : diplomeFinEtudeList) {
					Etudiant etudiant = new Etudiant();
					etudiant.setNumeroInscription(diplomeFinEtudeDto
							.getNumeroInscription());
					etudiant.setNom(diplomeFinEtudeDto.getEtudiantNomLatin());
					etudiant.setPrenom(diplomeFinEtudeDto
							.getEtudiantPrenomLatin());
					etudiant.setDiplomeFinEtudeId(diplomeFinEtudeDto.getId());
					source.add(etudiant);
				}
			}
			showDetail = true;
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());

		}
	}

	/**
	 * [DiplomeFinEtudeGererBean.loadCycleDiplomes] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2015 13:43:50
	 * @param cycleId
	 */
	public void loadCycleDiplomes() {
		cycleDiplomeDtos = new ArrayList<CycleDiplomeDto>();
		try {
			if (oofId != null) {
				ouvertureOffreFormationDto = utilBean.findOofById(oofId);
				if (ouvertureOffreFormationDto != null
						&& ouvertureOffreFormationDto.getCycleId() != null) {
					List<CycleDiplomeDto> _cycleDiplomeDtos = cycleService
							.findDiplomesByCycle(ouvertureOffreFormationDto
									.getCycleId());
					if (_cycleDiplomeDtos != null) {
						for (CycleDiplomeDto cycleDiplomeDto : _cycleDiplomeDtos) {
							if (cycleDiplomeDto.getSens() != null
									&& cycleDiplomeDto.getSens().equals("OUT")) {
								cycleDiplomeDtos.add(cycleDiplomeDto);
							}
						}
					}
				}

			}
		} catch (Exception e) {

		}
	}

	/**
	 * [diplomeFinEtudeValidateBean.loaddiplomeFinEtudeToValidate] Method
	 * 
	 * @author BELBRIK Oualid on : 11 Nov 2014 15:54:06
	 */
	public void searchNonValide() {
		try {
			// diplomeFinEtudeListNonValide = new
			// ArrayList<DiplomeFinEtudeDto>();
			// searchDto.setIdEtablissement(sessionBean.getIdEtablissement());
			// List<DiplomeFinEtudeDto> _diplomeFinEtudeListNonValide =
			// diplomeFinEtudeService
			// .findAdvanced(searchDto);
			// if (_diplomeFinEtudeListNonValide != null
			// && !_diplomeFinEtudeListNonValide.isEmpty()) {
			//
			// for (DiplomeFinEtudeDto diplomeFinEtudeDto :
			// _diplomeFinEtudeListNonValide) {
			// if (diplomeFinEtudeDto.getGenerationValide() != true) {
			// diplomeFinEtudeListNonValide.add(diplomeFinEtudeDto);
			// }
			// }
			// }
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());

		}
	}

	/**
	 * [diplomeFinEtudeValidateBean.loaddiplomeFinEtudeToValidate] Method
	 * 
	 * @author BELBRIK Oualid on : 10 aoï¿½t 2014 15:54:06
	 */
	public void searchDiplomesSignes() {
		try {
			// diplomeFinEtudeList = new ArrayList<DiplomeFinEtudeDto>();
			// searchDto.setIdEtablissement(sessionBean.getIdEtablissement());
			// List<DiplomeFinEtudeDto> _diplomeFinEtudeList =
			// diplomeFinEtudeService
			// .findAdvanced(searchDto);
			// if (_diplomeFinEtudeList != null &&
			// !_diplomeFinEtudeList.isEmpty()) {
			//
			// for (DiplomeFinEtudeDto diplomeFinEtudeDto :
			// _diplomeFinEtudeList) {
			// if (diplomeFinEtudeDto.getEstValideEtab() != false
			// && diplomeFinEtudeDto.getEstValideFac() != false
			// && diplomeFinEtudeDto.getEstValide() != false) {
			// diplomeFinEtudeList.add(diplomeFinEtudeDto);
			//
			// }
			// }
			// }
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());

		}
	}

	/**
	 * [DiplomeFinEtudeGererBean.searchBilan] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 févr. 2015 10:54:47
	 */
	public void searchBilan() {
		try {
			showDetail = false;
			// bilanSessionList = new ArrayList<BilanSessionDto>();
			// initDetail();
			if (oofId != null) {
				OuvertureOffreFormationDto oofDto = utilBean.findOof(oofId);
				if (oofDto != null && oofDto.getCycleId() != null) {
					diplomeFinEtudeList = diplomeFinEtudeService
							.findByOofAndCycle(oofId, oofDto.getCycleId());
				}
			}

			if (diplomeFinEtudeList == null || diplomeFinEtudeList.isEmpty()) {
				CommonMessagesUtils
						.showWarningMessage(diplomeFinEtudeBundle
								.getString("diplome_fin_etude_datable_list_diplome_fin_etude_no_result"));
			}
			showDetail = true;
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
		activeGeneration = true;
		searchNonValide();

	}

	/**
	 * 
	 * [diplomeFinEtudeConsultBean.anneeChanged] Method
	 * 
	 * @author BELBRIK Oualid on : 13 aoï¿½t 2014 16:35:53
	 */
	public void anneeChanged() {
		loadOffreFormationOuverte();

	}

	/**
	 * 
	 * [diplomeFinEtudeConsultBean.loadOffreFormationOuverte] Method
	 * 
	 * @author BELBRIK Oualid on : 13 aoï¿½t 2014 16:37:29
	 */
	public void loadOffreFormationOuverte() {
		offreFormationList = new ArrayList<SelectItem>();
		try {
			if (anneeAcademiqueId == null) {
				return;
			}
			offreFormationList = utilBean
					.loadOffreFormationOuverte(anneeAcademiqueId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [DiplomeFinEtudeGererBean.diplomeCheckAll] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 16:59:26
	 */
	public void diplomeCheckAll() {
		if (cycleDiplomeDtos != null) {
			selectedDiplomes = new ArrayList<String>();
			if (checkAll) {
				for (CycleDiplomeDto entry : cycleDiplomeDtos) {
					selectedDiplomes.add(entry.getDiplomeLibelle());
				}
			}
		}
	}

	/**
	 * [diplomeFinEtudeBean.sessionBean] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [diplomeFinEtudeBean.sessionBean] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [diplomeFinEtudeBean.dossierService] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @return the dossierService
	 */
	public DossierService getDossierService() {
		return dossierService;
	}

	/**
	 * [diplomeFinEtudeBean.dossierService] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @param dossierService
	 *            the dossierService to set
	 */
	public void setDossierService(DossierService dossierService) {
		this.dossierService = dossierService;
	}

	/**
	 * [diplomeFinEtudeBean.offreFormationService] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [diplomeFinEtudeBean.offreFormationService] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [diplomeFinEtudeBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [diplomeFinEtudeBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [diplomeFinEtudeBean.mapper] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [diplomeFinEtudeBean.mapper] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [diplomeFinEtudeBean.situationService] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [diplomeFinEtudeBean.situationService] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [diplomeFinEtudeBean.dossierInscriptionAdministrativeDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @return the dossierInscriptionAdministrativeDto
	 */
	public DossierInscriptionAdministrativeDto getDossierInscriptionAdministrativeDto() {
		return dossierInscriptionAdministrativeDto;
	}

	/**
	 * [diplomeFinEtudeBean.dossierInscriptionAdministrativeDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @param dossierInscriptionAdministrativeDto
	 *            the dossierInscriptionAdministrativeDto to set
	 */
	public void setDossierInscriptionAdministrativeDto(
			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto) {
		this.dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeDto;
	}

	/**
	 * [diplomeFinEtudeBean.diplomeFinEtudeService] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 octob. 2014 11:04:45
	 * @return the diplomeFinEtudeService
	 */
	public DiplomeFinEtudeService getDiplomeFinEtudeService() {
		return diplomeFinEtudeService;
	}

	/**
	 * [diplomeFinEtudeBean.diplomeFinEtudeService] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 octob. 2014 11:04:45
	 * @param diplomeFinEtudeService
	 *            the diplomeFinEtudeService to set
	 */
	public void setDiplomeFinEtudeService(
			DiplomeFinEtudeService diplomeFinEtudeService) {
		this.diplomeFinEtudeService = diplomeFinEtudeService;
	}

	/**
	 * [diplomeFinEtudeValidateBean.diplomeFinEtudeList] Getter
	 * 
	 * @author BELBRIK Oualid on : 10 aoï¿½t 2014 13:53:10
	 * @return the diplomeFinEtudeList
	 */
	public List<DiplomeFinEtudeDto> getDiplomeFinEtudeList() {
		return diplomeFinEtudeList;
	}

	/**
	 * [diplomeFinEtudeValidateBean.diplomeFinEtudeList] Setter
	 * 
	 * @author BELBRIK Oualid on : 10 aoï¿½t 2014 13:53:10
	 * @param diplomeFinEtudeList
	 *            the diplomeFinEtudeList to set
	 */
	public void setDiplomeFinEtudeList(
			List<DiplomeFinEtudeDto> diplomeFinEtudeList) {
		this.diplomeFinEtudeList = diplomeFinEtudeList;
	}

	/**
	 * [diplomeFinEtudeConsultBean.searchDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 aoï¿½t 2014 11:21:48
	 * @return the searchDto
	 */
	// public DiplomeFinEtudeDto getSearchDto() {
	// return searchDto;
	// }

	/**
	 * [diplomeFinEtudeConsultBean.searchDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 aoï¿½t 2014 11:21:48
	 * @param searchDto
	 *            the searchDto to set
	 */
	// public void setSearchDto(DiplomeFinEtudeDto searchDto) {
	// this.searchDto = searchDto;
	// }

	/**
	 * [diplomeFinEtudeConsultBean.offreFormationList] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 aoï¿½t 2014 16:34:27
	 * @return the offreFormationList
	 */
	public List<SelectItem> getOffreFormationList() {
		return offreFormationList;
	}

	/**
	 * [diplomeFinEtudeConsultBean.offreFormationList] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 aoï¿½t 2014 16:34:27
	 * @param offreFormationList
	 *            the offreFormationList to set
	 */
	public void setOffreFormationList(List<SelectItem> offreFormationList) {
		this.offreFormationList = offreFormationList;
	}

	/**
	 * [diplomeFinEtudeConsultBean.anneeAcademiqueList] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 aoï¿½t 2014 16:34:27
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [diplomeFinEtudeConsultBean.anneeAcademiqueList] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 aoï¿½t 2014 16:34:27
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * 
	 * [diplomeFinEtudeStatisticBean.ofChanged] Method
	 * 
	 * @author BELBRIK Oualid on : 14 aoï¿½t 2014 12:34:36
	 */
	public void ofChanged() {
		searchBilan();

	}

	/**
	 * [DiplomeFinEtudeGererBean.printOfChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2015 14:14:33
	 */
	public void printOfChanged() {
		loadCycleDiplomes();
		findValidateDiplomes();
	}

	/**
	 * [DiplomeFinEtudeGererBean.etudiantOfChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 févr. 2015 17:14:14
	 */
	public void etudiantOfChanged() {
		loadCycleDiplomes();
		findEtudiant();
		loadDiplomeLibelle();
	}

	/**
	 * [DiplomeFinEtudeGererBean.etudiantsOfChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 16:46:43
	 */
	public void etudiantsOfChanged() {
		findDiplomeSignature();
		loadDiplomeLibelle();
	}

	/**
	 * [DiplomeFinEtudeGererBean.loadDiplomeLibelle] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 16:45:31
	 */
	private void loadDiplomeLibelle() {
		if (cycleDiplomeDtos != null) {
			int i = 0;
			for (CycleDiplomeDto cycleDiplomeDto : cycleDiplomeDtos) {
				diplomeLibelle = cycleDiplomeDto.getDiplomeLibelle();
				if (i == 0 && cycleDiplomeDtos.size() > 1) {
					diplomeLibelle = diplomeLibelle + " - ";
				}
				i++;
			}
		}
	}

	/**
	 * [DiplomeFinEtudeGererBean.isInSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 09:03:26
	 * @param current
	 * @return
	 */
	public boolean isInSelect(SignatureDiplomeFinEtudeDto current) {
		if (current != null && selectedSignatureDiplomeFinEtudeDtos != null) {
			for (SignatureDiplomeFinEtudeDto sign : selectedSignatureDiplomeFinEtudeDtos) {
				if (sign.getId().equals(current.getId())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * [DiplomeFinEtudeGererBean.diplomeFinEtudeDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 oct. 2014 16:02:43
	 * @return the diplomeFinEtudeDto
	 */
	public DiplomeFinEtudeDto getDiplomeFinEtudeDto() {
		return diplomeFinEtudeDto;
	}

	/**
	 * [DiplomeFinEtudeGererBean.diplomeFinEtudeDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 oct. 2014 16:02:43
	 * @param diplomeFinEtudeDto
	 *            the diplomeFinEtudeDto to set
	 */
	public void setDiplomeFinEtudeDto(DiplomeFinEtudeDto diplomeFinEtudeDto) {
		this.diplomeFinEtudeDto = diplomeFinEtudeDto;
	}

	public void entityChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			entityChange = true;
		}
	}

	public void validerDeivranceChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			validerDeivranceChanged = true;
		}
	}

	/**
	 * [DossierEtudiantBean.entityChange] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 15:47:58
	 * @return the entityChange
	 */
	public boolean isValiderDeivranceChanged() {
		return validerDeivranceChanged;
	}

	public void setValiderDeivranceChanged(boolean validerDeivranceChanged) {
		this.validerDeivranceChanged = validerDeivranceChanged;
	}

	public boolean isEntityChange() {
		return entityChange;
	}

	public void setEntityChange(boolean entityChange) {
		this.entityChange = entityChange;
	}

	/**
	 * [DiplomeFinEtudeGererBean.saveSignature] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 09:06:42
	 */
	public void saveGroupedSignature() {
		try {
			if (etudiants != null) {

				if (selectedSignatureDiplomeFinEtudeDtos == null
						|| selectedSignatureDiplomeFinEtudeDtos.isEmpty()) {
					CommonMessagesUtils
							.showErrorMessage(diplomeFinEtudeBundle
									.getString("diplome_fin_etude_diplome_signature_selection_signataire_vide"));
					return;
				}
				for (SignatureDiplomeFinEtudeDto signatureDiplomeFinEtudeDto : selectedSignatureDiplomeFinEtudeDtos) {
					if (signatureDiplomeFinEtudeDto.getDateSignature() == null) {
						CommonMessagesUtils
								.showErrorMessage(diplomeFinEtudeBundle
										.getString("diplome_fin_etude_signature_date_signature_required"));
						return;
					}
					if (signatureDiplomeFinEtudeDto.getReference() == null
							|| signatureDiplomeFinEtudeDto.getReference()
									.trim().isEmpty()) {
						CommonMessagesUtils
								.showErrorMessage(diplomeFinEtudeBundle
										.getString("diplome_fin_etude_signature_reference_required"));
						return;
					}
					if (signatureDiplomeFinEtudeDto.getSignataire() == null
							|| signatureDiplomeFinEtudeDto.getSignataire()
									.trim().isEmpty()) {
						CommonMessagesUtils
								.showErrorMessage(diplomeFinEtudeBundle
										.getString("diplome_fin_etude_signature_par_required"));
						return;
					}
				}
				List<Etudiant> etudiantList = etudiants.getTarget();
				if (etudiantList == null || etudiantList.isEmpty()) {
					CommonMessagesUtils
							.showErrorMessage(diplomeFinEtudeBundle
									.getString("diplome_fin_etude_diplome_signature_selection_etudiant_vide"));
					return;
				}

				List<SignatureDiplomeFinEtudeDto> signatureDiplomeFinEtudeDtos = new ArrayList<SignatureDiplomeFinEtudeDto>();
				for (Etudiant etudiant : etudiantList) {
					for (SignatureDiplomeFinEtudeDto signatureDiplomeFinEtudeDto : selectedSignatureDiplomeFinEtudeDtos) {
						SignatureDiplomeFinEtudeDto newSignatureDiplomeFinEtudeDto = new SignatureDiplomeFinEtudeDto(
								signatureDiplomeFinEtudeDto);
						SignatureDiplomeFinEtudeDto signExist = signatureDiplomeFinEtudeService
								.findUnique(signatureDiplomeFinEtudeDto
										.getNcSignatureDiplomeId(), etudiant
										.getDiplomeFinEtudeId(), attestation);
						if (signExist != null) {
							newSignatureDiplomeFinEtudeDto.setId(signExist
									.getId());
						}

						newSignatureDiplomeFinEtudeDto
								.setDiplomeFinEtudeId(etudiant
										.getDiplomeFinEtudeId());
						signatureDiplomeFinEtudeDtos
								.add(newSignatureDiplomeFinEtudeDto);
						signatureDiplomeFinEtudeService
								.insertOrUpdate(newSignatureDiplomeFinEtudeDto);

					}
				}
				CommonMessagesUtils.showSuccessSaveMessage();
			}
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [DiplomeFinEtudeGererBean.saveSignature] Method 
	 * @author MAKERRI Sofiane  on : 25 févr. 2015  17:16:12
	 */
	public void saveSignature() {
		try {

			if (selectedSignatureDiplomeFinEtudeDtos == null
					|| selectedSignatureDiplomeFinEtudeDtos.isEmpty()) {
				CommonMessagesUtils
						.showErrorMessage(diplomeFinEtudeBundle
								.getString("diplome_fin_etude_diplome_signature_selection_signataire_vide"));
				return;
			}
			for (SignatureDiplomeFinEtudeDto signatureDiplomeFinEtudeDto : selectedSignatureDiplomeFinEtudeDtos) {
				if (signatureDiplomeFinEtudeDto.getDateSignature() == null) {
					CommonMessagesUtils
							.showErrorMessage(diplomeFinEtudeBundle
									.getString("diplome_fin_etude_signature_date_signature_required"));
					return;
				}
				if (signatureDiplomeFinEtudeDto.getReference() == null
						|| signatureDiplomeFinEtudeDto.getReference().trim()
								.isEmpty()) {
					CommonMessagesUtils
							.showErrorMessage(diplomeFinEtudeBundle
									.getString("diplome_fin_etude_signature_reference_required"));
					return;
				}
				if (signatureDiplomeFinEtudeDto.getSignataire() == null
						|| signatureDiplomeFinEtudeDto.getSignataire().trim()
								.isEmpty()) {
					CommonMessagesUtils
							.showErrorMessage(diplomeFinEtudeBundle
									.getString("diplome_fin_etude_signature_par_required"));
					return;
				}
			}
			for (SignatureDiplomeFinEtudeDto signatureDiplomeFinEtudeDto : selectedSignatureDiplomeFinEtudeDtos) {
				signatureDiplomeFinEtudeService
						.insertOrUpdate(signatureDiplomeFinEtudeDto);
			}
			CommonMessagesUtils.showSuccessSaveMessage();

		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
	}

	public void saveDiplome() {

		try {

			this.diplomeFinEtudeDto = diplomeFinEtudeService
					.insertOrUpdate(this.diplomeFinEtudeDto);
			CommonMessagesUtils.showSuccessSaveMessage();

		} catch (JpaSystemException e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();

			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}

	}

	public void onRowSelect2(SelectEvent event) {

		DiplomeFinEtudeEditionDto _edition = ((DiplomeFinEtudeEditionDto) event
				.getObject());
		loadListTypesImpressionDiplome();
		try {

			diplomeFinEtudeEditionDto = diplomeFinEtudeEditionService
					.findById(_edition.getId());

		} catch (Exception e) {

			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
	}

	public void onRowSelect(SelectEvent event) {

		diplomeFinEtudeDto = ((DiplomeFinEtudeDto) event.getObject());
		findSignature();
		try {
			// search2();

		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [DiplomeFinEtudeGererBean.validate] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2015 11:17:10
	 */
	public void validate() {
		try {
			if (diplomeFinEtudeList != null && !diplomeFinEtudeList.isEmpty()) {
				for (DiplomeFinEtudeDto _diplomeFinEtudeDto : diplomeFinEtudeList) {
					if (!_diplomeFinEtudeDto.getEstValide()) {
						_diplomeFinEtudeDto.setEstValide(true);
						diplomeFinEtudeService
								.insertOrUpdate(_diplomeFinEtudeDto);
					}
				}
				CommonMessagesUtils.showSuccessValidationMessage();
			}
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [diplomeFinEtudeEditionBean.diplomeFinEtudeEditionService] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 octob. 2014 11:04:45
	 * @return the diplomeFinEtudeEditionService
	 */
	public DiplomeFinEtudeEditionService getDiplomeFinEtudeEditionService() {
		return diplomeFinEtudeEditionService;
	}

	/**
	 * [diplomeFinEtudeEditionBean.diplomeFinEtudeEditionService] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 octob. 2014 11:04:45
	 * @param diplomeFinEtudeEditionService
	 *            the diplomeFinEtudeEditionService to set
	 */
	public void setDiplomeFinEtudeEditionService(
			DiplomeFinEtudeEditionService diplomeFinEtudeEditionService) {
		this.diplomeFinEtudeEditionService = diplomeFinEtudeEditionService;
	}

	/**
	 * [diplomeFinEtudeEditionValidateBean.diplomeFinEtudeEditionList] Getter
	 * 
	 * @author BELBRIK Oualid on : 10 aoï¿½t 2014 13:53:10
	 * @return the diplomeFinEtudeEditionList
	 */
	public List<DiplomeFinEtudeEditionDto> getDiplomeFinEtudeEditionList() {
		return diplomeFinEtudeEditionList;
	}

	/**
	 * [diplomeFinEtudeEditionValidateBean.diplomeFinEtudeEditionList] Setter
	 * 
	 * @author BELBRIK Oualid on : 10 aoï¿½t 2014 13:53:10
	 * @param diplomeFinEtudeEditionList
	 *            the diplomeFinEtudeEditionList to set
	 */
	public void setDiplomeFinEtudeEditionList(
			List<DiplomeFinEtudeEditionDto> diplomeFinEtudeEditionList) {
		this.diplomeFinEtudeEditionList = diplomeFinEtudeEditionList;
	}

	/**
	 * [diplomeFinEtudeEditionConsultBean.searchEditionDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 aoï¿½t 2014 11:21:48
	 * @return the searchDto
	 */
	public DiplomeFinEtudeEditionDto getSearchEditionDto() {
		return searchEditionDto;
	}

	/**
	 * [diplomeFinEtudeEditionConsultBean.searchEditionDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 aoï¿½t 2014 11:21:48
	 * @param searchEditionDto
	 *            the searchEditionDto to set
	 */
	public void setSearchEditionDto(DiplomeFinEtudeEditionDto searchEditionDto) {
		this.searchEditionDto = searchEditionDto;
	}

	/**
	 * [DiplomeFinEtudeEditionGererBean.diplomeFinEtudeEditionDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 oct. 2014 16:02:43
	 * @return the diplomeFinEtudeEditionDto
	 */
	public DiplomeFinEtudeEditionDto getDiplomeFinEtudeEditionDto() {
		return diplomeFinEtudeEditionDto;
	}

	/**
	 * [DiplomeFinEtudeEditionGererBean.diplomeFinEtudeEditionDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 oct. 2014 16:02:43
	 * @param diplomeFinEtudeEditionDto
	 *            the diplomeFinEtudeEditionDto to set
	 */
	public void setDiplomeFinEtudeEditionDto(
			DiplomeFinEtudeEditionDto diplomeFinEtudeEditionDto) {
		this.diplomeFinEtudeEditionDto = diplomeFinEtudeEditionDto;
	}

	public void saveDelivrance() {

		try {

			if (diplomeFinEtudeEditionDto.getDateEdition().before(
					diplomeFinEtudeDto.getDateObtention())
					|| diplomeFinEtudeEditionDto.getDateEdition().before(
							diplomeFinEtudeDto.getDateSignatureMinistere())
					|| diplomeFinEtudeEditionDto.getDateEdition().before(
							diplomeFinEtudeDto.getDateSignatureEtablissement())
					|| diplomeFinEtudeEditionDto.getDateEdition().before(
							diplomeFinEtudeDto.getDateSignatureFaculte())) {
				CommonMessagesUtils.showErrorMessage(diplomeFinEtudeBundle
						.getString("date_superieur"));
			} else {

				diplomeFinEtudeEditionDto.setAgent(loginBean.getUserName());
				diplomeFinEtudeEditionDto.setService(sessionBean
						.getLlLatinEtablissement());
				this.diplomeFinEtudeEditionDto = diplomeFinEtudeEditionService
						.insertOrUpdate(this.diplomeFinEtudeEditionDto);

				diplomeFinEtudeDto.setEstDelivre(true);
				diplomeFinEtudeDto.setDateDerniereEdition(new Date());
				diplomeFinEtudeService.insertOrUpdate(diplomeFinEtudeDto);
				CommonMessagesUtils.showSuccessSaveMessage();

			}

		} catch (JpaSystemException e) {
			e.printStackTrace();
			if (e.getCause().getCause().getCause() instanceof ConstraintViolationException) {
				CommonMessagesUtils.showErrorMessage(e.getMessage());
				return;
			}
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();

			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
	}

	public void suppDelivrance(
			DiplomeFinEtudeEditionDto selectedDiplomeFinEtudeEditionDto) {
		try {

			diplomeFinEtudeEditionService
					.remove(selectedDiplomeFinEtudeEditionDto);

			// rafraichir la liste des DiplomeFinEtudeEdition
			CommonMessagesUtils.showSuccessDeleteMessage();

		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}

	}

	public void loadListTypesImpressionDiplome() {
		try {
			List<dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto> list = nomenclatureService
					.findByName(CursusConstants.NC_TYPE_DIPLOME_IMPRESSION);
			listTypesImpressionDiplome = new ArrayList<SelectItem>();
			for (dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto nc : list) {
				SelectItem item = new SelectItem(nc.getId(),
						nc.getLibelleLongFr());
				listTypesImpressionDiplome.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [DiplomeFinEtudeGererBean.listTypesImpressionDiplome] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 nov. 2014 11:00:13
	 * @return the listTypesImpressionDiplome
	 */
	public List<SelectItem> getListTypesImpressionDiplome() {
		return listTypesImpressionDiplome;
	}

	/**
	 * [DiplomeFinEtudeGererBean.listTypesImpressionDiplome] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 nov. 2014 11:00:13
	 * @param listTypesImpressionDiplome
	 *            the listTypesImpressionDiplome to set
	 */
	public void setListTypesImpressionDiplome(
			List<SelectItem> listTypesImpressionDiplome) {
		this.listTypesImpressionDiplome = listTypesImpressionDiplome;
	}

	/**
	 * [DiplomeFinEtudeGererBean.nomenclatureService] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 nov. 2014 11:00:13
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [DiplomeFinEtudeGererBean.nomenclatureService] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 nov. 2014 11:00:13
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [DiplomeFinEtudeGererBean.impressionService] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 oct. 2014 13:07:22
	 * @return the impressionService
	 */
	public ImpressionService getImpressionService() {
		return impressionService;
	}

	/**
	 * [DiplomeFinEtudeGererBean.impressionService] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 oct. 2014 13:07:22
	 * @param impressionService
	 *            the impressionService to set
	 */
	public void setImpressionService(ImpressionService impressionService) {
		this.impressionService = impressionService;
	}

	/**
	 * [DiplomeFinEtudeGererBean.printMgrBean] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 oct. 2014 13:07:22
	 * @return the printMgrBean
	 */
	public PrintMgrBean getPrintMgrBean() {
		return printMgrBean;
	}

	/**
	 * [DiplomeFinEtudeGererBean.printMgrBean] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 oct. 2014 13:07:22
	 * @param printMgrBean
	 *            the printMgrBean to set
	 */
	public void setPrintMgrBean(PrintMgrBean printMgrBean) {
		this.printMgrBean = printMgrBean;
	}

	/**
	 * [DiplomeFinEtudeGererBean.printSeparateDiplome] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 08:50:22
	 */
	public void printSeparateDiplome() {
		// List<Diplome> diplomeList = new ArrayList<Diplome>();// =
		// diplomes.getTarget();
		if (selectedDiplomes == null || selectedDiplomes.isEmpty()) {
			CommonMessagesUtils.showErrorMessage(diplomeFinEtudeBundle
					.getString("diplome_fin_etude_diplome_selection_vide"));
			return;
		}
		if (selectedDiplomeFinEtudeList == null
				|| selectedDiplomeFinEtudeList.isEmpty()) {
			CommonMessagesUtils.showErrorMessage(diplomeFinEtudeBundle
					.getString("diplome_fin_etude_etudiant_selection_vide"));
			return;
		}
		if (selectedDiplomes != null && !selectedDiplomes.isEmpty()) {

			List<DiplomeFinEtudeDto> diplomeFinEtudeDtos = new ArrayList<DiplomeFinEtudeDto>();
			int size = selectedDiplomeFinEtudeList.size();
			int pos = 0;
			for (int i = 0; i < size; i++) {
				DiplomeFinEtudeDto _diplomeFinEtudeDto = selectedDiplomeFinEtudeList
						.get(i);
				CycleDiplomeDto _cycleDiplomeDto = findCycleDiplome(selectedDiplomes
						.get(0));
				if (_cycleDiplomeDto != null) {
					_diplomeFinEtudeDto
							.setLibelleDiplomePrincipalArabe(_cycleDiplomeDto
									.getDiplomeLibelleAr());
					_diplomeFinEtudeDto
							.setLibelleDiplomePrincipalLatin(_cycleDiplomeDto
									.getDiplomeLibelle());
					_diplomeFinEtudeDto.setLibelleDiplomeSecondaireArabe(null);
					_diplomeFinEtudeDto.setLibelleDiplomeSecondaireLatin(null);
					diplomeFinEtudeDtos.add(_diplomeFinEtudeDto);
				}
				if (selectedDiplomes.size() > 1) {
					DiplomeFinEtudeDto newDiplomeFinEtudeDto = new DiplomeFinEtudeDto(
							_diplomeFinEtudeDto);
					_cycleDiplomeDto = findCycleDiplome(selectedDiplomes.get(1));
					if (_cycleDiplomeDto != null) {
						newDiplomeFinEtudeDto
								.setLibelleDiplomePrincipalArabe(_cycleDiplomeDto
										.getDiplomeLibelleAr());
						newDiplomeFinEtudeDto
								.setLibelleDiplomePrincipalLatin(_cycleDiplomeDto
										.getDiplomeLibelle());
						newDiplomeFinEtudeDto
								.setLibelleDiplomeSecondaireArabe(null);
						newDiplomeFinEtudeDto
								.setLibelleDiplomeSecondaireLatin(null);
						pos++;
						// diplomeFinEtudeList.add(i + pos,
						// newDiplomeFinEtudeDto);
						diplomeFinEtudeDtos.add(i + pos, newDiplomeFinEtudeDto);
					}
				}

			}

			print(diplomeFinEtudeDtos);

		}
	}

	/**
	 * [DiplomeFinEtudeGererBean.printOneDiplome] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 févr. 2015 15:01:54
	 */
	public void printOneDiplome() {
		if (selectedDiplomeFinEtudeList == null
				|| selectedDiplomeFinEtudeList.isEmpty()) {
			CommonMessagesUtils.showErrorMessage(diplomeFinEtudeBundle
					.getString("diplome_fin_etude_etudiant_selection_vide"));
			return;
		}
		if (diplome != null) {
			for (DiplomeFinEtudeDto diplomeFinEtudeDto : selectedDiplomeFinEtudeList) {
				diplomeFinEtudeDto.setLibelleDiplomePrincipalArabe(diplome
						.getLibelleAr());
				diplomeFinEtudeDto.setLibelleDiplomePrincipalLatin(diplome
						.getLibelleFr());
				diplomeFinEtudeDto.setLibelleDiplomeSecondaireArabe(null);
				diplomeFinEtudeDto.setLibelleDiplomeSecondaireLatin(null);
			}
			print(selectedDiplomeFinEtudeList);
		}
	}

	/**
	 * Diplome ï¿½tudiant print
	 * 
	 * @author Oualid.BELBRIK on : 22 oct. 2014 10:24:09
	 */
	public void printDiplome() {
		try {
			// List<Diplome> diplomeList = new ArrayList<Diplome>();//
			// diplomes.getTarget();
			if (selectedDiplomes == null || selectedDiplomes.size() < 2) {
				CommonMessagesUtils
						.showErrorMessage(diplomeFinEtudeBundle
								.getString("diplome_fin_etude_diplome_selection_groupe_non_atteint"));
				return;
			}
			if (selectedDiplomeFinEtudeList == null
					|| selectedDiplomeFinEtudeList.isEmpty()) {
				CommonMessagesUtils
						.showErrorMessage(diplomeFinEtudeBundle
								.getString("diplome_fin_etude_etudiant_selection_vide"));
				return;
			}

			if (selectedDiplomes != null && !selectedDiplomes.isEmpty()) {

				for (DiplomeFinEtudeDto _diplomeFinEtudeDto : selectedDiplomeFinEtudeList) {
					CycleDiplomeDto _cycleDiplomeDto = findCycleDiplome(selectedDiplomes
							.get(0));
					if (_cycleDiplomeDto != null) {
						_diplomeFinEtudeDto
								.setLibelleDiplomePrincipalArabe(_cycleDiplomeDto
										.getDiplomeLibelleAr());
						_diplomeFinEtudeDto
								.setLibelleDiplomePrincipalLatin(_cycleDiplomeDto
										.getDiplomeLibelle());
					}

					if (selectedDiplomes.size() > 1) {
						_cycleDiplomeDto = findCycleDiplome(selectedDiplomes
								.get(1));
						if (_cycleDiplomeDto != null) {
							_diplomeFinEtudeDto
									.setLibelleDiplomeSecondaireArabe(_cycleDiplomeDto
											.getDiplomeLibelleAr());
							_diplomeFinEtudeDto
									.setLibelleDiplomeSecondaireLatin(_cycleDiplomeDto
											.getDiplomeLibelle());
						}
					}
				}

				print(selectedDiplomeFinEtudeList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			// TODO: handle exception
		}
	}

	/**
	 * [DiplomeFinEtudeGererBean.print] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 17:18:31
	 * @param diplomeFinEtudeDtos
	 */
	public void print(List<DiplomeFinEtudeDto> diplomeFinEtudeDtos) {
		try {
			if (ouvertureOffreFormationDto != null) {
				String japserFileName = "diplome.jrxml";
				String prefixFileName = "diplome_";
				if (attestation) {
					japserFileName = "attestation_fin_etude.jrxml";
					prefixFileName = "attestation_";
				}

				String name = prefixFileName
						+ ouvertureOffreFormationDto.getOfLibelleLongFr()
						+ ouvertureOffreFormationDto.getCycleLibelleLongLt();

				FacesContext facesContext = FacesContext.getCurrentInstance();
				String SUBREPORT_DIR = facesContext.getExternalContext()
						.getRealPath("/WEB-INF/classes") + "/jasper/diplome/";

				String RESOURCE_PATH_TO_INPUT_FILE_JASPER = SUBREPORT_DIR
						+ japserFileName;
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(Const.JASPER_PARAM_IMG_PAPS_KEY, facesContext
						.getExternalContext().getRealPath("images")
						+ "/logopaps.png");
				params.put(Const.JASPER_PARAM_IMG_LOGO_KEY, facesContext
						.getExternalContext().getRealPath("images")
						+ "/logo"
						+ sessionBean.getCodeEtablissement() + ".png");
				params.put(Const.JASPER_PARAM_OFFRE_FORMATION_KEY,
						ouvertureOffreFormationDto.getOfLibelleLongFr());
				params.put(Const.JASPER_PARAM_OFFRE_FORMATION_ARABE_KEY,
						ouvertureOffreFormationDto.getOfLibelleLongAr());
				params.put(Const.JASPER_PARAM_SPECIALITE_KEY,
						ouvertureOffreFormationDto.getLibelleSpecialite());
				params.put(Const.JASPER_PARAM_SPECIALITE_ARABE_KEY,
						ouvertureOffreFormationDto.getLibelleSpecialiteAr());
				byte[] bytes = impressionService.viewPDFWithDataSource(
						RESOURCE_PATH_TO_INPUT_FILE_JASPER, params,
						diplomeFinEtudeDtos);

				printMgrBean.imprimer(bytes, name, "pdf");
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			// TODO: handle exception
		}
	}

	/**
	 * [DiplomeFinEtudeGererBean.findCycleDiplome] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 17:19:58
	 * @param libelle
	 * @return
	 */
	private CycleDiplomeDto findCycleDiplome(String libelle) {
		if (libelle != null && cycleDiplomeDtos != null) {
			for (CycleDiplomeDto cycleDiplomeDto : cycleDiplomeDtos) {
				if (cycleDiplomeDto.getDiplomeLibelle() != null
						&& cycleDiplomeDto.getDiplomeLibelle().equals(libelle)) {
					return cycleDiplomeDto;
				}
			}
		}
		return null;
	}

	public void printAccuse() {
		try {

			Collection<DiplomeFinEtudeDto> etudiants = new ArrayList<>();

			etudiants.add(diplomeFinEtudeDto);

			String name = "accusereceptiondiplome_"
					+ diplomeFinEtudeDto.getNumeroMatricule()
					+ diplomeFinEtudeDto.getEtudiantNomLatin()
					+ diplomeFinEtudeDto.getEtudiantPrenomLatin();

			FacesContext facesContext = FacesContext.getCurrentInstance();

			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext
					.getExternalContext().getRealPath("/WEB-INF/classes")
					+ "/jasper/accusediplome.jrxml";

			Map<String, Object> params = new HashMap<String, Object>();

			byte[] bytes = impressionService.viewPDFWithDataSource(
					RESOURCE_PATH_TO_INPUT_FILE_JASPER, params, etudiants);

			printMgrBean.imprimer(bytes, name, "pdf");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	/**
	 * [DiplomeFinEtudeGererBean.searchBilanDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 27 oct. 2014 15:30:27
	 * @return the searchBilanDto
	 */
	public BilanSessionDto getSearchBilanDto() {
		return searchBilanDto;
	}

	/**
	 * [DiplomeFinEtudeGererBean.searchBilanDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 27 oct. 2014 15:30:27
	 * @param searchBilanDto
	 *            the searchBilanDto to set
	 */
	public void setSearchBilanDto(BilanSessionDto searchBilanDto) {
		this.searchBilanDto = searchBilanDto;
	}

	/**
	 * [DiplomeFinEtudeGererBean.bilanSessionDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 27 oct. 2014 15:33:10
	 * @return the bilanSessionDto
	 */
	public BilanSessionDto getBilanSessionDto() {
		return bilanSessionDto;
	}

	/**
	 * [DiplomeFinEtudeGererBean.bilanSessionDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 27 oct. 2014 15:33:10
	 * @param bilanSessionDto
	 *            the bilanSessionDto to set
	 */
	public void setBilanSessionDto(BilanSessionDto bilanSessionDto) {
		this.bilanSessionDto = bilanSessionDto;
	}

	/**
	 * [DiplomeFinEtudeGererBean.bilanSessionList] Getter
	 * 
	 * @author BELBRIK Oualid on : 27 oct. 2014 15:38:34
	 * @return the bilanSessionList
	 */
	public List<BilanSessionDto> getBilanSessionList() {
		return bilanSessionList;
	}

	/**
	 * [DiplomeFinEtudeGererBean.bilanSessionList] Setter
	 * 
	 * @author BELBRIK Oualid on : 27 oct. 2014 15:38:34
	 * @param bilanSessionList
	 *            the bilanSessionList to set
	 */
	public void setBilanSessionList(List<BilanSessionDto> bilanSessionList) {
		this.bilanSessionList = bilanSessionList;
	}

	/**
	 * [DiplomeFinEtudeGererBean.bilanSessionService] Getter
	 * 
	 * @author BELBRIK Oualid on : 27 oct. 2014 16:05:24
	 * @return the bilanSessionService
	 */
	public BilanSessionService getBilanSessionService() {
		return bilanSessionService;
	}

	/**
	 * [DiplomeFinEtudeGererBean.bilanSessionService] Setter
	 * 
	 * @author BELBRIK Oualid on : 27 oct. 2014 16:05:24
	 * @param bilanSessionService
	 *            the bilanSessionService to set
	 */
	public void setBilanSessionService(BilanSessionService bilanSessionService) {
		this.bilanSessionService = bilanSessionService;
	}

	/**
	 * [DiplomeFinEtudeGererBean.activeGeneration] Getter
	 * 
	 * @author BELBRIK Oualid on : 27 oct. 2014 17:52:42
	 * @return the activeGeneration
	 */
	public boolean isActiveGeneration() {
		return activeGeneration;
	}

	/**
	 * [DiplomeFinEtudeGererBean.activeGeneration] Setter
	 * 
	 * @author BELBRIK Oualid on : 27 oct. 2014 17:52:42
	 * @param activeGeneration
	 *            the activeGeneration to set
	 */
	public void setActiveGeneration(boolean activeGeneration) {
		this.activeGeneration = activeGeneration;
	}

	public void printDiplomeParLot() {
		log.info("printDiplomesParLot....");

		try {
			Collection<DiplomeFinEtudeDto> diplomes = new ArrayList<>();

			if (diplomeFinEtudeList != null && !diplomeFinEtudeList.isEmpty()) {
				for (DiplomeFinEtudeDto diplome : diplomeFinEtudeList) {
					if (diplome.getAnneeAcademiqueCode() == null
							|| diplome.getEtudiantLieuNaissance() == null
							|| diplome.getEtudiantNomArabe() == null
							|| diplome.getEtudiantPrenomArabe() == null
							|| (diplome.getRefLibelleFiliereArabe() == null && diplome
									.getRefLibelleDomaineArabe() == null)
							|| diplome.getRefLibelleNiveauArabe() == null) {
						diplomes.add(diplome);
					}

				}
			}

			diplomes = diplomeFinEtudeList;
			printMgrBean
					.viewDiplomePDF(diplomes, "attestationdiplome_"
							+ sessionBean.getCodeEtablissement()
							+ generateDateString());
			// }

		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
	}

	private String generateDateString() {
		Calendar c = Calendar.getInstance();
		return String.valueOf(c.get(Calendar.YEAR))
				+ String.valueOf(c.get(Calendar.MONTH) + 1)
				+ String.valueOf(c.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * [DiplomeFinEtudeGererBean.anneeAcademiqueId] Getter
	 * 
	 * @author BELBRIK Oualid on : 30 oct. 2014 11:59:48
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [DiplomeFinEtudeGererBean.anneeAcademiqueId] Setter
	 * 
	 * @author BELBRIK Oualid on : 30 oct. 2014 11:59:48
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [DiplomeFinEtudeGererBean.oofId] Getter
	 * 
	 * @author BELBRIK Oualid on : 30 oct. 2014 11:59:48
	 * @return the oofId
	 */
	public Integer getOofId() {
		return oofId;
	}

	/**
	 * [DiplomeFinEtudeGererBean.oofId] Setter
	 * 
	 * @author BELBRIK Oualid on : 30 oct. 2014 11:59:48
	 * @param oofId
	 *            the oofId to set
	 */
	public void setOofId(Integer oofId) {
		this.oofId = oofId;
	}

	@SuppressWarnings("deprecation")
	public void generationDiplme() {

		try {

			OffreFormationDto offreFormationDto = offreFormationService
					.findById(oofId);
			if (bilanSessionList != null && !bilanSessionList.isEmpty()) {

				for (BilanSessionDto bilanSessionDto : bilanSessionList) {

					diplomeFinEtudeDto = new DiplomeFinEtudeDto();
					diplomeFinEtudeDto.setDateCreation(new Date());

					diplomeFinEtudeDto.setDateObtention(bilanSessionDto
							.getDateDeliberation());
					diplomeFinEtudeDto.setAnneeAcademiqueId(anneeAcademiqueId);
					diplomeFinEtudeDto.setDateObtention(bilanSessionDto
							.getDateDeliberation());
					diplomeFinEtudeDto.setIdEtablissement(sessionBean
							.getIdEtablissement());
					diplomeFinEtudeDto
							.setDossierInscriptionAdministrativeId(bilanSessionDto
									.getDossierInscriptionAdministrativeId());
					diplomeFinEtudeDto.setBilanSessionId(bilanSessionDto
							.getId());
					diplomeFinEtudeDto.setMentionId(bilanSessionDto
							.getMentionId());
					diplomeFinEtudeDto.setEstValide(false);
					diplomeFinEtudeDto.setEstValideEtab(false);
					diplomeFinEtudeDto.setEstValideFac(false);
					diplomeFinEtudeDto.setGenerationValide(false);
					diplomeFinEtudeDto.setIdentifiant(bilanSessionDto
							.getDateDeliberation().getYear()
							+ offreFormationDto.getCode()
							+ bilanSessionDto.getId());

					this.diplomeFinEtudeDto = diplomeFinEtudeService
							.insertOrUpdate(this.diplomeFinEtudeDto);

					attestationFinEtudeDto = new AttestationFinEtudeDto();
					attestationFinEtudeDto.setDateCreation(new Date());
					attestationFinEtudeDto
							.setRefCodeAttestation("Licence en Sciences Biologiques");
					attestationFinEtudeDto.setDateObtention(bilanSessionDto
							.getDateDeliberation());
					attestationFinEtudeDto
							.setAnneeAcademiqueId(anneeAcademiqueId);
					attestationFinEtudeDto.setDateObtention(bilanSessionDto
							.getDateDeliberation());
					attestationFinEtudeDto.setIdEtablissement(sessionBean
							.getIdEtablissement());
					attestationFinEtudeDto
							.setDossierInscriptionAdministrativeId(bilanSessionDto
									.getDossierInscriptionAdministrativeId());
					attestationFinEtudeDto.setBilanSessionId(bilanSessionDto
							.getId());
					attestationFinEtudeDto.setMentionId(bilanSessionDto
							.getMentionId());
					attestationFinEtudeDto.setEstValide(false);
					attestationFinEtudeDto.setEstValideEtab(false);
					attestationFinEtudeDto.setEstValideFac(false);
					attestationFinEtudeDto.setGenerationValide(false);
					attestationFinEtudeDto.setIdentifiant(bilanSessionDto
							.getDateDeliberation().getYear()
							+ offreFormationDto.getCode()
							+ bilanSessionDto.getId());

					this.attestationFinEtudeDto = attestationFinEtudeService
							.insertOrUpdate(this.attestationFinEtudeDto);

				}
				System.err
						.println("Fin Generation des diplï¿½mes et attestations : Total genere = "
								+ String.valueOf(bilanSessionList.size()));
				CommonMessagesUtils.showSuccessMessage(diplomeFinEtudeBundle
						.getString("Génération_diplome_succes"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
		// search();
		searchNonValide();
	}

	public void validationGenerationDiplme() {

		try {

			if (diplomeFinEtudeListNonValide != null
					&& !diplomeFinEtudeListNonValide.isEmpty()) {
				for (DiplomeFinEtudeDto diplomeFinEtudeDto : diplomeFinEtudeListNonValide) {

					diplomeFinEtudeDto.setGenerationValide(true);
					diplomeFinEtudeService.insertOrUpdate(diplomeFinEtudeDto);
				}
				searchNonValide();
				CommonMessagesUtils.showSuccessMessage(diplomeFinEtudeBundle
						.getString("Validation_Génération_diplome_succes"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
		searchNonValide();

	}

	/**
	 * [DiplomeFinEtudeGererBean.loginBean] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 nov. 2014 11:22:55
	 * @return the loginBean
	 */
	public LoginBean getLoginBean() {
		return loginBean;
	}

	/**
	 * [DiplomeFinEtudeGererBean.loginBean] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 nov. 2014 11:22:55
	 * @param loginBean
	 *            the loginBean to set
	 */
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	/**
	 * [DiplomeFinEtudeGererBean.getMention] Method
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 10:16:30
	 * @param note
	 * @return
	 */
	public String getMention(Double note) {
		if (mentionList != null) {
			for (MentionDto mention : mentionList) {
				if (note != null && mention.getNoteMin() <= note
						&& mention.getNoteMax() > note) {
					return mention.getNcMentionCode();
				}
			}
		}
		return "-";
	}

	/**
	 * [DiplomeFinEtudeGererBean.mentionList] Getter
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 10:16:26
	 * @return the mentionList
	 */
	public List<MentionDto> getMentionList() {
		return mentionList;
	}

	/**
	 * [DiplomeFinEtudeGererBean.mentionList] Setter
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 10:16:26
	 * @param mentionList
	 *            the mentionList to set
	 */
	public void setMentionList(List<MentionDto> mentionList) {
		this.mentionList = mentionList;
	}

	/**
	 * [DiplomeFinEtudeGererBean.utilBean] Getter
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 10:17:40
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [DiplomeFinEtudeGererBean.utilBean] Setter
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 10:17:40
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [DiplomeFinEtudeGererBean.attestationFinEtudeDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 4 nov. 2014 14:32:55
	 * @return the attestationFinEtudeDto
	 */
	public AttestationFinEtudeDto getAttestationFinEtudeDto() {
		return attestationFinEtudeDto;
	}

	/**
	 * [DiplomeFinEtudeGererBean.attestationFinEtudeDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 4 nov. 2014 14:32:55
	 * @param attestationFinEtudeDto
	 *            the attestationFinEtudeDto to set
	 */
	public void setAttestationFinEtudeDto(
			AttestationFinEtudeDto attestationFinEtudeDto) {
		this.attestationFinEtudeDto = attestationFinEtudeDto;
	}

	/**
	 * [DiplomeFinEtudeGererBean.attestationFinEtudeService] Getter
	 * 
	 * @author BELBRIK Oualid on : 4 nov. 2014 14:32:55
	 * @return the attestationFinEtudeService
	 */
	public AttestationFinEtudeService getAttestationFinEtudeService() {
		return attestationFinEtudeService;
	}

	/**
	 * [DiplomeFinEtudeGererBean.attestationFinEtudeService] Setter
	 * 
	 * @author BELBRIK Oualid on : 4 nov. 2014 14:32:55
	 * @param attestationFinEtudeService
	 *            the attestationFinEtudeService to set
	 */
	public void setAttestationFinEtudeService(
			AttestationFinEtudeService attestationFinEtudeService) {
		this.attestationFinEtudeService = attestationFinEtudeService;
	}

	public void nouvelDelivrance() {
		this.diplomeFinEtudeEditionDto = null;
		diplomeFinEtudeEditionDto = new DiplomeFinEtudeEditionDto();
		diplomeFinEtudeEditionDto.setDiplomeFinEtudeId(diplomeFinEtudeDto
				.getId());
		loadListTypesImpressionDiplome();

	}

	/**
	 * [DiplomeFinEtudeGererBean.diplomeFinEtudeListNonValide] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 nov. 2014 11:33:01
	 * @return the diplomeFinEtudeListNonValide
	 */
	public List<DiplomeFinEtudeDto> getDiplomeFinEtudeListNonValide() {
		return diplomeFinEtudeListNonValide;
	}

	/**
	 * [DiplomeFinEtudeGererBean.diplomeFinEtudeListNonValide] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 nov. 2014 11:33:01
	 * @param diplomeFinEtudeListNonValide
	 *            the diplomeFinEtudeListNonValide to set
	 */
	public void setDiplomeFinEtudeListNonValide(
			List<DiplomeFinEtudeDto> diplomeFinEtudeListNonValide) {
		this.diplomeFinEtudeListNonValide = diplomeFinEtudeListNonValide;
	}

	/**
	 * [DiplomeFinEtudeGererBean.showDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 févr. 2015 11:07:32
	 * @return the showDetail
	 */
	public boolean isShowDetail() {
		return showDetail;
	}

	/**
	 * [DiplomeFinEtudeGererBean.showDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 févr. 2015 11:07:32
	 * @param showDetail
	 *            the showDetail to set
	 */
	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}

	/**
	 * [DiplomeFinEtudeGererBean.cycleDiplomeDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2015 13:41:20
	 * @return the cycleDiplomeDtos
	 */
	public List<CycleDiplomeDto> getCycleDiplomeDtos() {
		return cycleDiplomeDtos;
	}

	/**
	 * [DiplomeFinEtudeGererBean.cycleDiplomeDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2015 13:41:20
	 * @param cycleDiplomeDtos
	 *            the cycleDiplomeDtos to set
	 */
	public void setCycleDiplomeDtos(List<CycleDiplomeDto> cycleDiplomeDtos) {
		this.cycleDiplomeDtos = cycleDiplomeDtos;
	}

	/**
	 * [DiplomeFinEtudeGererBean.cycleService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2015 13:17:00
	 * @return the cycleService
	 */
	public CycleService getCycleService() {
		return cycleService;
	}

	/**
	 * [DiplomeFinEtudeGererBean.cycleService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2015 13:17:00
	 * @param cycleService
	 *            the cycleService to set
	 */
	public void setCycleService(CycleService cycleService) {
		this.cycleService = cycleService;
	}

	/**
	 * [DiplomeFinEtudeGererBean.ouvertureOffreFormationDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2015 15:31:20
	 * @return the ouvertureOffreFormationDto
	 */
	public OuvertureOffreFormationDto getOuvertureOffreFormationDto() {
		return ouvertureOffreFormationDto;
	}

	/**
	 * [DiplomeFinEtudeGererBean.ouvertureOffreFormationDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2015 15:31:20
	 * @param ouvertureOffreFormationDto
	 *            the ouvertureOffreFormationDto to set
	 */
	public void setOuvertureOffreFormationDto(
			OuvertureOffreFormationDto ouvertureOffreFormationDto) {
		this.ouvertureOffreFormationDto = ouvertureOffreFormationDto;
	}

	/**
	 * [DiplomeFinEtudeGererBean.diplomes] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 16:53:14
	 * @return the diplomes
	 */
	public List<String> getDiplomes() {
		return diplomes;
	}

	/**
	 * [DiplomeFinEtudeGererBean.diplomes] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 16:53:14
	 * @param diplomes
	 *            the diplomes to set
	 */
	public void setDiplomes(List<String> diplomes) {
		this.diplomes = diplomes;
	}

	/**
	 * [DiplomeFinEtudeGererBean.selectedDiplomes] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 16:53:14
	 * @return the selectedDiplomes
	 */
	public List<String> getSelectedDiplomes() {
		return selectedDiplomes;
	}

	/**
	 * [DiplomeFinEtudeGererBean.selectedDiplomes] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 16:53:14
	 * @param selectedDiplomes
	 *            the selectedDiplomes to set
	 */
	public void setSelectedDiplomes(List<String> selectedDiplomes) {
		this.selectedDiplomes = selectedDiplomes;
	}

	/**
	 * [DiplomeFinEtudeGererBean.source] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 févr. 2015 17:06:17
	 * @return the source
	 */
	public List<Etudiant> getSource() {
		return source;
	}

	/**
	 * [DiplomeFinEtudeGererBean.source] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 févr. 2015 17:06:17
	 * @param source
	 *            the source to set
	 */
	public void setSource(List<Etudiant> source) {
		this.source = source;
	}

	/**
	 * [DiplomeFinEtudeGererBean.target] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 févr. 2015 17:06:17
	 * @return the target
	 */
	public List<Etudiant> getTarget() {
		return target;
	}

	/**
	 * [DiplomeFinEtudeGererBean.target] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 févr. 2015 17:06:17
	 * @param target
	 *            the target to set
	 */
	public void setTarget(List<Etudiant> target) {
		this.target = target;
	}

	/**
	 * [DiplomeFinEtudeGererBean.showDiplomeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 10:42:30
	 * @return the showDiplomeList
	 */
	public boolean isShowDiplomeList() {
		return showDiplomeList;
	}

	/**
	 * [DiplomeFinEtudeGererBean.showDiplomeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 10:42:30
	 * @param showDiplomeList
	 *            the showDiplomeList to set
	 */
	public void setShowDiplomeList(boolean showDiplomeList) {
		this.showDiplomeList = showDiplomeList;
	}

	/**
	 * [DiplomeFinEtudeGererBean.diplome] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 10:56:29
	 * @return the diplome
	 */
	public Diplome getDiplome() {
		return diplome;
	}

	/**
	 * [DiplomeFinEtudeGererBean.diplome] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 10:56:29
	 * @param diplome
	 *            the diplome to set
	 */
	public void setDiplome(Diplome diplome) {
		this.diplome = diplome;
	}

	/**
	 * [DiplomeFinEtudeGererBean.filtredDiplomeFinEtudeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 11:26:06
	 * @return the filtredDiplomeFinEtudeList
	 */
	public List<DiplomeFinEtudeDto> getFiltredDiplomeFinEtudeList() {
		return filtredDiplomeFinEtudeList;
	}

	/**
	 * [DiplomeFinEtudeGererBean.filtredDiplomeFinEtudeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 11:26:06
	 * @param filtredDiplomeFinEtudeList
	 *            the filtredDiplomeFinEtudeList to set
	 */
	public void setFiltredDiplomeFinEtudeList(
			List<DiplomeFinEtudeDto> filtredDiplomeFinEtudeList) {
		this.filtredDiplomeFinEtudeList = filtredDiplomeFinEtudeList;
	}

	/**
	 * [DiplomeFinEtudeGererBean.selectedDiplomeFinEtudeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 11:46:23
	 * @return the selectedDiplomeFinEtudeList
	 */
	public List<DiplomeFinEtudeDto> getSelectedDiplomeFinEtudeList() {
		return selectedDiplomeFinEtudeList;
	}

	/**
	 * [DiplomeFinEtudeGererBean.selectedDiplomeFinEtudeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 11:46:23
	 * @param selectedDiplomeFinEtudeList
	 *            the selectedDiplomeFinEtudeList to set
	 */
	public void setSelectedDiplomeFinEtudeList(
			List<DiplomeFinEtudeDto> selectedDiplomeFinEtudeList) {
		this.selectedDiplomeFinEtudeList = selectedDiplomeFinEtudeList;
	}

	/**
	 * [DiplomeFinEtudeGererBean.checkAll] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 16:56:57
	 * @return the checkAll
	 */
	public boolean isCheckAll() {
		return checkAll;
	}

	/**
	 * [DiplomeFinEtudeGererBean.checkAll] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 16:56:57
	 * @param checkAll
	 *            the checkAll to set
	 */
	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	/**
	 * [DiplomeFinEtudeGererBean.attestation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 févr. 2015 12:18:31
	 * @return the attestation
	 */
	public boolean isAttestation() {
		return attestation;
	}

	/**
	 * [DiplomeFinEtudeGererBean.attestation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 févr. 2015 12:18:31
	 * @param attestation
	 *            the attestation to set
	 */
	public void setAttestation(boolean attestation) {
		this.attestation = attestation;
	}

	/**
	 * [DiplomeFinEtudeGererBean.etudiants] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 févr. 2015 16:56:40
	 * @return the etudiants
	 */
	public DualListModel<Etudiant> getEtudiants() {
		return etudiants;
	}

	/**
	 * [DiplomeFinEtudeGererBean.etudiants] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 févr. 2015 16:56:40
	 * @param etudiants
	 *            the etudiants to set
	 */
	public void setEtudiants(DualListModel<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	/**
	 * [DiplomeFinEtudeGererBean.signatureDiplomeFinEtudeDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 08:04:30
	 * @return the signatureDiplomeFinEtudeDtos
	 */
	public List<SignatureDiplomeFinEtudeDto> getSignatureDiplomeFinEtudeDtos() {
		return signatureDiplomeFinEtudeDtos;
	}

	/**
	 * [DiplomeFinEtudeGererBean.signatureDiplomeFinEtudeDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 08:04:30
	 * @param signatureDiplomeFinEtudeDtos
	 *            the signatureDiplomeFinEtudeDtos to set
	 */
	public void setSignatureDiplomeFinEtudeDtos(
			List<SignatureDiplomeFinEtudeDto> signatureDiplomeFinEtudeDtos) {
		this.signatureDiplomeFinEtudeDtos = signatureDiplomeFinEtudeDtos;
	}

	/**
	 * [DiplomeFinEtudeGererBean.selectedSignatureDiplomeFinEtudeDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 08:32:57
	 * @return the selectedSignatureDiplomeFinEtudeDtos
	 */
	public List<SignatureDiplomeFinEtudeDto> getSelectedSignatureDiplomeFinEtudeDtos() {
		return selectedSignatureDiplomeFinEtudeDtos;
	}

	/**
	 * [DiplomeFinEtudeGererBean.selectedSignatureDiplomeFinEtudeDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 08:32:57
	 * @param selectedSignatureDiplomeFinEtudeDtos
	 *            the selectedSignatureDiplomeFinEtudeDtos to set
	 */
	public void setSelectedSignatureDiplomeFinEtudeDtos(
			List<SignatureDiplomeFinEtudeDto> selectedSignatureDiplomeFinEtudeDtos) {
		this.selectedSignatureDiplomeFinEtudeDtos = selectedSignatureDiplomeFinEtudeDtos;
	}

	/**
	 * [DiplomeFinEtudeGererBean.signatureDiplomeFinEtudeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 09:23:25
	 * @return the signatureDiplomeFinEtudeService
	 */
	public SignatureDiplomeFinEtudeService getSignatureDiplomeFinEtudeService() {
		return signatureDiplomeFinEtudeService;
	}

	/**
	 * [DiplomeFinEtudeGererBean.signatureDiplomeFinEtudeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 09:23:25
	 * @param signatureDiplomeFinEtudeService
	 *            the signatureDiplomeFinEtudeService to set
	 */
	public void setSignatureDiplomeFinEtudeService(
			SignatureDiplomeFinEtudeService signatureDiplomeFinEtudeService) {
		this.signatureDiplomeFinEtudeService = signatureDiplomeFinEtudeService;
	}

	/**
	 * [DiplomeFinEtudeGererBean.diplomeLibelle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 16:41:35
	 * @return the diplomeLibelle
	 */
	public String getDiplomeLibelle() {
		return diplomeLibelle;
	}

	/**
	 * [DiplomeFinEtudeGererBean.diplomeLibelle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2015 16:41:35
	 * @param diplomeLibelle
	 *            the diplomeLibelle to set
	 */
	public void setDiplomeLibelle(String diplomeLibelle) {
		this.diplomeLibelle = diplomeLibelle;
	}

}
