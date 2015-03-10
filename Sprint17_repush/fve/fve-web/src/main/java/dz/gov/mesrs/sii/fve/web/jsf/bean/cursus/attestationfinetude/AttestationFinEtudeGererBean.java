package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.attestationfinetude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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
import javax.faces.context.Flash;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.SelectEvent;
import org.springframework.orm.jpa.JpaSystemException;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AttestationFinEtudeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AttestationFinEtudeEditionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.MentionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.service.cursus.AttestationFinEtudeEditionService;
import dz.gov.mesrs.sii.fve.business.service.cursus.AttestationFinEtudeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierService;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanSessionService;
import dz.gov.mesrs.sii.fve.business.service.impression.ImpressionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.impression.PrintMgrBean;
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
 * @author BELBRIK Oualid on : 11 octob 2014 14:41:09
 */
@ManagedBean(name = "attestationFinEtudeGererBean")
@ViewScoped
public class AttestationFinEtudeGererBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:03:10
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(AttestationFinEtudeGererBean.class);

	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	private ResourceBundle bundleCommon;
	private ResourceBundle attestationFinEtudeBundle;
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
	@ManagedProperty("#{bilanSessionService}")
	private BilanSessionService bilanSessionService;
	@ManagedProperty("#{attestationFinEtudeService}")
	private AttestationFinEtudeService attestationFinEtudeService;
	private DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto;
	private AttestationFinEtudeDto attestationFinEtudeDto;
	private BilanSessionDto bilanSessionDto;
	private List<AttestationFinEtudeDto> attestationFinEtudeList;
	private List<BilanSessionDto> bilanSessionList;
	private AttestationFinEtudeDto searchDto;
	private BilanSessionDto searchBilanDto;
	private List<SelectItem> offreFormationList;
	private List<SelectItem> anneeAcademiqueList;
	private boolean entityChange;
	private boolean activeGeneration;
	private boolean validerDeivranceChanged;
	@ManagedProperty("#{attestationFinEtudeEditionService}")
	private AttestationFinEtudeEditionService attestationFinEtudeEditionService;
	private AttestationFinEtudeEditionDto attestationFinEtudeEditionDto;
	private List<AttestationFinEtudeEditionDto> attestationFinEtudeEditionList;
	private AttestationFinEtudeEditionDto searchEditionDto;
	private List<SelectItem> listTypesImpressionAttestation;
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

	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;

	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;

	@ManagedProperty(value = "#{refDomaineLMDServiceImpl}")
	private RefDomaineLMDService refDomaineLMDService;

	@ManagedProperty(value = "#{refFiliereLmdServiceImpl}")
	private RefFiliereLmdService refFiliereLmdService;

	@ManagedProperty(value = "#{refSpecialiteLmdServiceImpl}")
	private RefSpecialiteLmdService refSpecialiteLmdService;

	@ManagedProperty(value = "#{refEtablissementServiceImpl}")
	private RefEtablissementService refEtablissementService;

	/**
	 * [AttestationFinEtudeGererBean.refEtablissementService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:00:16
	 * @return the refEtablissementService
	 */
	public RefEtablissementService getRefEtablissementService() {
		return refEtablissementService;
	}

	/**
	 * [AttestationFinEtudeGererBean.refEtablissementService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:00:16
	 * @param refEtablissementService
	 *            the refEtablissementService to set
	 */
	public void setRefEtablissementService(
			RefEtablissementService refEtablissementService) {
		this.refEtablissementService = refEtablissementService;
	}

	/**
	 * [AttestationFinEtudeGererBean.refSpecialiteLmdService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:58:38
	 * @return the refSpecialiteLmdService
	 */
	public RefSpecialiteLmdService getRefSpecialiteLmdService() {
		return refSpecialiteLmdService;
	}

	/**
	 * [AttestationFinEtudeGererBean.refSpecialiteLmdService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:58:38
	 * @param refSpecialiteLmdService
	 *            the refSpecialiteLmdService to set
	 */
	public void setRefSpecialiteLmdService(
			RefSpecialiteLmdService refSpecialiteLmdService) {
		this.refSpecialiteLmdService = refSpecialiteLmdService;
	}

	/**
	 * [AttestationFinEtudeGererBean.refIndividuService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:57:42
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [AttestationFinEtudeGererBean.refIndividuService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:57:42
	 * @param refIndividuService
	 *            the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [AttestationFinEtudeGererBean.refDomaineLMDService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:57:42
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [AttestationFinEtudeGererBean.refDomaineLMDService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:57:42
	 * @param refDomaineLMDService
	 *            the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(
			RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	/**
	 * [AttestationFinEtudeGererBean.refFiliereLmdService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:57:42
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [AttestationFinEtudeGererBean.refFiliereLmdService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:57:42
	 * @param refFiliereLmdService
	 *            the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(
			RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	/**
	 * [AttestationFinEtudeBean.AttestationFinEtudeBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:03:05
	 */
	public AttestationFinEtudeGererBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		attestationFinEtudeBundle = facesContext.getApplication()
				.getResourceBundle(facesContext,
						CursusConstants.DIPLOME_FIN_ETUDE_BUNDLE_MSG_NAME);

	}

	@PostConstruct
	public void init() {

		initDetail();
		searchDto = new AttestationFinEtudeDto();
		// AttestationFinEtudeDto = new AttestationFinEtudeDto();
		loadAnneeAcademique();
		initDetail2();
		searchEditionDto = new AttestationFinEtudeEditionDto();
		attestationFinEtudeEditionDto = new AttestationFinEtudeEditionDto();

		searchBilanDto = new BilanSessionDto();
		anneeAcademiqueId = sessionBean.getIdAnneeAcademique();
		searchDto.setAnneeAcademiqueId(sessionBean.getIdAnneeAcademique());
		loadOffreFormationOuverte();
	}

	/**
	 * 
	 * [AttestationFinEtudeConsultBean.initDetail] Method
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 17:01:17
	 */
	private void initDetail() {

		dossierInscriptionAdministrativeDto = null;
		attestationFinEtudeDto = null;
		bilanSessionDto = null;
		activeGeneration = false;
	}

	/**
	 * 
	 * [attestationFinEtudeConsultBean.loadAnneeAcademique] Method
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 17:01:27
	 */
	public void loadAnneeAcademique() {
		try {
			anneeAcademiqueList = utilBean.loadAnneeAcademique();
		} catch (Exception e) {

		}
	}

	/**
	 * [attestationFinEtudeValidateBean.loadattestationFinEtudeToValidate]
	 * Method
	 * 
	 * @author BELBRIK Oualid on : 10 ao�t 2014 15:54:06
	 */
public void findAttestation() {
		FacesMessage msg = new FacesMessage();
		try {
			attestationFinEtudeList = new ArrayList<AttestationFinEtudeDto>();
			searchDto.setIdEtablissement(sessionBean.getIdEtablissement());
			initDetail();
			List<AttestationFinEtudeDto> _attestationFinEtudeList = attestationFinEtudeService
					.findAdvanced(searchDto);
			if (_attestationFinEtudeList != null
					&& !_attestationFinEtudeList.isEmpty()) {

				for (AttestationFinEtudeDto attestationFinEtudeDto : _attestationFinEtudeList) {
					DossierInscriptionAdministrativeDto _dossierInscriptionDto = loadDossierInscription(attestationFinEtudeDto
							.getDossierInscriptionAdministrativeId().intValue());
					mapper.map(_dossierInscriptionDto, attestationFinEtudeDto);
					attestationFinEtudeList.add(attestationFinEtudeDto);
				}
			}
		} catch (Exception e) {
			attestationFinEtudeList = new ArrayList<AttestationFinEtudeDto>();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	/**
	 * [AttestationFinEtudeValidateBean.loaddiplomeFinEtudeToValidate] Method
	 * 
	 * @author BELBRIK Oualid on : 10 ao�t 2014 15:54:06
	 */
	public void searchDiplomNonSigneMinstr() {
		FacesMessage msg = new FacesMessage();
		try {
			attestationFinEtudeList = new ArrayList<AttestationFinEtudeDto>();
			searchDto.setIdEtablissement(sessionBean.getIdEtablissement());
			initDetail();
			List<AttestationFinEtudeDto> _attestationFinEtudeList = attestationFinEtudeService
					.findAdvanced(searchDto);
			if (_attestationFinEtudeList != null
					&& !_attestationFinEtudeList.isEmpty()) {

				for (AttestationFinEtudeDto attestationFinEtudeDto : _attestationFinEtudeList) {

					DossierInscriptionAdministrativeDto _dossierInscriptionDto = loadDossierInscription(attestationFinEtudeDto
							.getDossierInscriptionAdministrativeId().intValue());
					mapper.map(_dossierInscriptionDto, attestationFinEtudeDto);
					// if (diplomeFinEtudeDto.getEstValide()!=true){
					attestationFinEtudeList.add(attestationFinEtudeDto);
					// }
				}
			}
		} catch (Exception e) {
			attestationFinEtudeList = new ArrayList<AttestationFinEtudeDto>();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage();
		initDetail();
		attestationFinEtudeDto = ((AttestationFinEtudeDto) event.getObject());
		try {
			dossierInscriptionAdministrativeDto = loadDossierInscription(attestationFinEtudeDto
					.getDossierInscriptionAdministrativeId().intValue());
			search2();

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	/**
	 * [diplomeFinEtudeBean.loaddiplomeFinEtude] Method
	 * 
	 * @author BELBRIK Oualid on : 16 octob. 2014 14:15:40
	 * @param offreFormationId
	 * @param idInscriptionAdministrative
	 */
	private void loadAttestationFinEtude(int id) {
		// TODO
		AttestationFinEtudeDto _attestationFinEtudeDto = attestationFinEtudeService
				.findById(id);

	}

	/**
	 * [diplomeFinEtudeBean.loadDossierInscription] Method
	 * 
	 * @author BELBRIK Oualid on : 16 octob. 2014 14:15:28
	 * @throws Exception_Exception
	 */
	private DossierInscriptionAdministrativeDto loadDossierInscription(int id)
			throws Exception {
		DossierInscriptionAdministrativeDto _dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
				.findById(id);

		if (_dossierInscriptionAdministrativeDto.getRefCodeSpecialite() != null) {
			RefSpecialiteLmdDto _specialite = refSpecialiteLmdService
					.findByIdentifiant(_dossierInscriptionAdministrativeDto
							.getRefCodeSpecialite());
			if (_specialite != null) {
				_dossierInscriptionAdministrativeDto
						.setRefLibelleSpecialite(_specialite.getLlSpecialite());
				_dossierInscriptionAdministrativeDto
						.setRefLibelleSpecialiteArabe(_specialite
								.getLlSpecialiteArabe());
			}
		}
		if (_dossierInscriptionAdministrativeDto.getRefCodeFiliere() != null) {
			RefFiliereLmdDto _filiere = refFiliereLmdService
					.findByIdentifiant(_dossierInscriptionAdministrativeDto
							.getRefCodeFiliere());
			if (_filiere != null) {
				_dossierInscriptionAdministrativeDto
						.setRefLibelleFiliere(_filiere.getLlFiliere());
				_dossierInscriptionAdministrativeDto
						.setRefLibelleFiliereArabe(_filiere.getLlFiliereArabe());
			}
		}

		if (_dossierInscriptionAdministrativeDto.getRefCodeDomaine() != null) {
			RefDomaineLMDDto _domaine = refDomaineLMDService
					.findByIdentifiant(_dossierInscriptionAdministrativeDto
							.getRefCodeDomaine());
			if (_domaine != null) {
				_dossierInscriptionAdministrativeDto
						.setRefLibelleDomaine(_domaine.getLlDomaine());
				_dossierInscriptionAdministrativeDto
						.setRefLibelleDomaineArabe(_domaine.getLlDomaineArabe());
			}
		}
		// Initialisation du cycle & niveau
		if (_dossierInscriptionAdministrativeDto.getRefCodeCycle() != null) {
			NomenclatureDto _cycle = nomenclatureService.findByCode(
					Const.LMD_CYCLE,
					_dossierInscriptionAdministrativeDto.getRefCodeCycle());
			if (_cycle != null) {
				_dossierInscriptionAdministrativeDto.setRefLibelleCycle(_cycle
						.getLibelleLongFr());
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
						.setRefLibelleNiveauArabe(_niveau.getLibelleLongAr());
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
	 * [diplomeFinEtudeBean.map] Method
	 * 
	 * @author BELBRIK Oualid on : 16 octob. 2014 14:15:33
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
	 * [diplomeFinEtudeConsultBean.anneeChanged] Method
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 16:35:53
	 */
	public void anneeChanged() {
		loadOffreFormationOuverte();

	}

	/**
	 * 
	 * [diplomeFinEtudeConsultBean.loadOffreFormationOuverte] Method
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 16:37:29
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
	 * 
	 * [diplomeFinEtudeConsultBean.findOfItem] Method
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 16:43:16
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
	 * [AttestationFinEtudeGererBean.nomenclatureService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:54:45
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [AttestationFinEtudeGererBean.nomenclatureService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:54:45
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
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
	 * [diplomeFinEtudeBean.dossierEtudiantService] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @return the dossierEtudiantService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [diplomeFinEtudeBean.dossierEtudiantService] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @param dossierEtudiantService
	 *            the dossierEtudiantService to set
	 */
	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	/**
	 * [diplomeFinEtudeBean.dossierInscriptionAdministrativeService] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [diplomeFinEtudeBean.dossierInscriptionAdministrativeService] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @param dossierInscriptionAdministrativeService
	 *            the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [diplomeFinEtudeBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [diplomeFinEtudeBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
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
	 * [diplomeFinEtudeBean.bacService] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @return the bacService
	 */
	public BacService getBacService() {
		return bacService;
	}

	/**
	 * [diplomeFinEtudeBean.bacService] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:10:09
	 * @param bacService
	 *            the bacService to set
	 */
	public void setBacService(BacService bacService) {
		this.bacService = bacService;
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
	public AttestationFinEtudeService getAttestationFinEtudeService() {
		return attestationFinEtudeService;
	}

	/**
	 * [diplomeFinEtudeBean.diplomeFinEtudeService] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 octob. 2014 11:04:45
	 * @param diplomeFinEtudeService
	 *            the diplomeFinEtudeService to set
	 */
	public void setAttestationFinEtudeService(
			AttestationFinEtudeService attestationFinEtudeService) {
		this.attestationFinEtudeService = attestationFinEtudeService;
	}

	/**
	 * [diplomeFinEtudeValidateBean.diplomeFinEtudeList] Getter
	 * 
	 * @author BELBRIK Oualid on : 10 ao�t 2014 13:53:10
	 * @return the diplomeFinEtudeList
	 */
	public List<AttestationFinEtudeDto> getAttestationFinEtudeList() {
		return attestationFinEtudeList;
	}

	/**
	 * [diplomeFinEtudeValidateBean.diplomeFinEtudeList] Setter
	 * 
	 * @author BELBRIK Oualid on : 10 ao�t 2014 13:53:10
	 * @param diplomeFinEtudeList
	 *            the diplomeFinEtudeList to set
	 */
	public void setAttestationFinEtudeList(
			List<AttestationFinEtudeDto> attestationFinEtudeList) {
		this.attestationFinEtudeList = attestationFinEtudeList;
	}

	/**
	 * [diplomeFinEtudeConsultBean.searchDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 11:21:48
	 * @return the searchDto
	 */
	public AttestationFinEtudeDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [diplomeFinEtudeConsultBean.searchDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 11:21:48
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(AttestationFinEtudeDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * [diplomeFinEtudeConsultBean.offreFormationList] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 16:34:27
	 * @return the offreFormationList
	 */
	public List<SelectItem> getOffreFormationList() {
		return offreFormationList;
	}

	/**
	 * [diplomeFinEtudeConsultBean.offreFormationList] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 16:34:27
	 * @param offreFormationList
	 *            the offreFormationList to set
	 */
	public void setOffreFormationList(List<SelectItem> offreFormationList) {
		this.offreFormationList = offreFormationList;
	}

	/**
	 * [diplomeFinEtudeConsultBean.anneeAcademiqueList] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 16:34:27
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [diplomeFinEtudeConsultBean.anneeAcademiqueList] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 16:34:27
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
	 * @author BELBRIK Oualid on : 14 ao�t 2014 12:34:36
	 */
	public void ofChanged() {

	}

	/**
	 * [DiplomeFinEtudeGererBean.diplomeFinEtudeDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 oct. 2014 16:02:43
	 * @return the diplomeFinEtudeDto
	 */
	public AttestationFinEtudeDto getAttestationFinEtudeDto() {
		return attestationFinEtudeDto;
	}

	/**
	 * [DiplomeFinEtudeGererBean.diplomeFinEtudeDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 oct. 2014 16:02:43
	 * @param diplomeFinEtudeDto
	 *            the diplomeFinEtudeDto to set
	 */
	public void setAttestationFinEtudeDto(
			AttestationFinEtudeDto attestationFinEtudeDto) {
		this.attestationFinEtudeDto = attestationFinEtudeDto;
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

	public void saveAttestation() {
		FacesMessage msg = new FacesMessage();
		try {

			this.attestationFinEtudeDto = attestationFinEtudeService
					.insertOrUpdate(this.attestationFinEtudeDto);

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_enregistrement"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (JpaSystemException e) {
			e.printStackTrace();
			if (e.getCause().getCause().getCause() instanceof ConstraintViolationException) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);

				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
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
	 * [diplomeFinEtudeEditionConsultBean.initDetail] Method
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 17:01:17
	 */
	private void initDetail2() {

		dossierInscriptionAdministrativeDto = null;
		attestationFinEtudeEditionDto = null;
	}

	/**
	 * [diplomeFinEtudeEditionValidateBean.loaddiplomeFinEtudeEditionToValidate]
	 * Method
	 * 
	 * @author BELBRIK Oualid on : 10 ao�t 2014 15:54:06
	 */
	public void search2() {
		FacesMessage msg = new FacesMessage();
		try {
			attestationFinEtudeEditionList = new ArrayList<AttestationFinEtudeEditionDto>();
			List<AttestationFinEtudeEditionDto> _attestationFinEtudeEditionList = attestationFinEtudeEditionService
					.findEditionByIdAttestation(attestationFinEtudeDto.getId());
			if (_attestationFinEtudeEditionList != null
					&& !_attestationFinEtudeEditionList.isEmpty()) {

				for (AttestationFinEtudeEditionDto attestationFinEtudeEditionDto : _attestationFinEtudeEditionList) {
					attestationFinEtudeEditionList
							.add(attestationFinEtudeEditionDto);
				}
			}
		} catch (Exception e) {
			attestationFinEtudeEditionList = new ArrayList<AttestationFinEtudeEditionDto>();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	public void onRowSelect2(SelectEvent event) {
		FacesMessage msg = new FacesMessage();

		AttestationFinEtudeEditionDto _edition = ((AttestationFinEtudeEditionDto) event
				.getObject());
		// initDetail2();
		try {

			dossierInscriptionAdministrativeDto = loadDossierInscription(_edition
					.getattestationFinEtudeId().intValue());
			attestationFinEtudeEditionDto = attestationFinEtudeEditionService
					.findById(_edition.getId());

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	/**
	 * [diplomeFinEtudeEditionBean.loaddiplomeFinEtudeEdition] Method
	 * 
	 * @author BELBRIK Oualid on : 16 octob. 2014 14:15:40
	 * @param offreFormationId
	 * @param idInscriptionAdministrative
	 */
	private void loadAttestationFinEtudeEdition(int id) {
		// TODO
		AttestationFinEtudeEditionDto _attestationFinEtudeEditionDto = attestationFinEtudeEditionService
				.findById(id);

	}

	/**
	 * [diplomeFinEtudeEditionBean.diplomeFinEtudeEditionService] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 octob. 2014 11:04:45
	 * @return the diplomeFinEtudeEditionService
	 */
	public AttestationFinEtudeEditionService getAttestationFinEtudeEditionService() {
		return attestationFinEtudeEditionService;
	}

	/**
	 * [diplomeFinEtudeEditionBean.diplomeFinEtudeEditionService] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 octob. 2014 11:04:45
	 * @param diplomeFinEtudeEditionService
	 *            the diplomeFinEtudeEditionService to set
	 */
	public void setAttestationFinEtudeEditionService(
			AttestationFinEtudeEditionService attestationFinEtudeEditionService) {
		this.attestationFinEtudeEditionService = attestationFinEtudeEditionService;
	}

	/**
	 * [diplomeFinEtudeEditionValidateBean.diplomeFinEtudeEditionList] Getter
	 * 
	 * @author BELBRIK Oualid on : 10 ao�t 2014 13:53:10
	 * @return the diplomeFinEtudeEditionList
	 */
	public List<AttestationFinEtudeEditionDto> getAttestationFinEtudeEditionList() {
		return attestationFinEtudeEditionList;
	}

	/**
	 * [diplomeFinEtudeEditionValidateBean.diplomeFinEtudeEditionList] Setter
	 * 
	 * @author BELBRIK Oualid on : 10 ao�t 2014 13:53:10
	 * @param diplomeFinEtudeEditionList
	 *            the diplomeFinEtudeEditionList to set
	 */
	public void setAttestationFinEtudeEditionList(
			List<AttestationFinEtudeEditionDto> attestationFinEtudeEditionList) {
		this.attestationFinEtudeEditionList = attestationFinEtudeEditionList;
	}

	/**
	 * [diplomeFinEtudeEditionConsultBean.searchEditionDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 11:21:48
	 * @return the searchDto
	 */
	public AttestationFinEtudeEditionDto getSearchEditionDto() {
		return searchEditionDto;
	}

	/**
	 * [diplomeFinEtudeEditionConsultBean.searchEditionDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 11:21:48
	 * @param searchEditionDto
	 *            the searchEditionDto to set
	 */
	public void setSearchEditionDto(
			AttestationFinEtudeEditionDto searchEditionDto) {
		this.searchEditionDto = searchEditionDto;
	}

	/**
	 * [DiplomeFinEtudeEditionGererBean.diplomeFinEtudeEditionDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 oct. 2014 16:02:43
	 * @return the diplomeFinEtudeEditionDto
	 */
	public AttestationFinEtudeEditionDto getAttestationFinEtudeEditionDto() {
		return attestationFinEtudeEditionDto;
	}

	/**
	 * [DiplomeFinEtudeEditionGererBean.diplomeFinEtudeEditionDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 oct. 2014 16:02:43
	 * @param diplomeFinEtudeEditionDto
	 *            the diplomeFinEtudeEditionDto to set
	 */
	public void setAttestationFinEtudeEditionDto(
			AttestationFinEtudeEditionDto attestationFinEtudeEditionDto) {
		this.attestationFinEtudeEditionDto = attestationFinEtudeEditionDto;
	}

	public void saveDelivrance() {
		FacesMessage msg = new FacesMessage();
		try {

			attestationFinEtudeEditionDto.setAgent(loginBean.getUserName());
			attestationFinEtudeEditionDto.setService(sessionBean
					.getLlLatinEtablissement());
			this.attestationFinEtudeEditionDto = attestationFinEtudeEditionService
					.insertOrUpdate(this.attestationFinEtudeEditionDto);

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_enregistrement"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			search2();

		} catch (JpaSystemException e) {
			e.printStackTrace();
			if (e.getCause().getCause().getCause() instanceof ConstraintViolationException) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);

				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void suppDelivrance(
			AttestationFinEtudeEditionDto selectedAttestationFinEtudeEditionDto) {
		FacesMessage msg = new FacesMessage();

		try {

			attestationFinEtudeEditionService
					.remove(selectedAttestationFinEtudeEditionDto);

			// rafraichir la liste des AttestationFinEtudeEdition

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(attestationFinEtudeBundle
					.getString("delivrance_success_delete"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			search2();

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(attestationFinEtudeBundle
					.getString("delivrance_error_delete_delivrance"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * [DelivranceDiplomeFinEtudeGererBean.listTypesImpressionDiplome] Getter
	 * 
	 * @author BELBRIK Oualid on : 20 oct. 2014 17:30:51
	 * @return the listTypesImpressionDiplome
	 * @throws Exception_Exception
	 */
	public List<SelectItem> getListTypesImpressionAttestation()
			throws Exception {
		listTypesImpressionAttestation = new ArrayList<>();
		List<NomenclatureDto> _motifTrsf = nomenclatureService
				.findByName(CursusConstants.NC_TYPE_ATTESTATION_IMPRESSION);
		for (NomenclatureDto item : _motifTrsf) {
			listTypesImpressionAttestation.add(new SelectItem(item.getCode(),
					item.getLibelleLongFr()));
		}
		return listTypesImpressionAttestation;
	}

	/**
	 * [DelivranceDiplomeFinEtudeGererBean.listTypesImpressionDiplome] Setter
	 * 
	 * @author BELBRIK Oualid on : 20 oct. 2014 17:30:51
	 * @param listTypesImpressionDiplome
	 *            the listTypesImpressionDiplome to set
	 */
	public void setListTypesImpressionAttestation(
			List<SelectItem> listTypesImpressionAttestation) {
		this.listTypesImpressionAttestation = listTypesImpressionAttestation;
	}

	public void nouvelDelivrance() {
		this.attestationFinEtudeEditionDto = null;
		attestationFinEtudeEditionDto = new AttestationFinEtudeEditionDto();
		attestationFinEtudeEditionDto
				.setAttestationFinEtudeId(attestationFinEtudeDto.getId());
		// setShowDetail(true);

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
	 * Diplome �tudiant print
	 * 
	 * @author Oualid.BELBRIK on : 22 oct. 2014 10:24:09
	 */
	public void printAttestation() {
		try {

			Collection<AttestationFinEtudeDto> etudiants = new ArrayList<>();
			// AttestationFinEtudeDto.
			etudiants.add(attestationFinEtudeDto);

			String name = "attestation_"
					+ attestationFinEtudeDto.getNumeroMatricule()
					+ attestationFinEtudeDto.getIndividuNomLatin()
					+ attestationFinEtudeDto.getIndividuPrenomLatin();

			FacesContext facesContext = FacesContext.getCurrentInstance();

			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext
					.getExternalContext().getRealPath("/WEB-INF/classes")
					+ "/jasper/attestation_fin_etude.jrxml";

			Map<String, Object> params = new HashMap<String, Object>();

			byte[] bytes = impressionService.viewPDFWithDataSource(
					RESOURCE_PATH_TO_INPUT_FILE_JASPER, params, etudiants);

			printMgrBean.imprimer(bytes, name, "pdf");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public void printAccuse() {
		try {

			Collection<AttestationFinEtudeDto> etudiants = new ArrayList<>();

			etudiants.add(attestationFinEtudeDto);

			String name = "accusereceptionattestation_"
					+ attestationFinEtudeDto.getNumeroMatricule()
					+ attestationFinEtudeDto.getIndividuNomLatin()
					+ attestationFinEtudeDto.getIndividuPrenomLatin();

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

	public void printAttestationParLot() {
		log.info("printAttestationsParLot....");
		FacesMessage msg = new FacesMessage();
		try {
			Collection<AttestationFinEtudeDto> attestations = new ArrayList<>();

			if (attestationFinEtudeList != null
					&& !attestationFinEtudeList.isEmpty()) {
				for (AttestationFinEtudeDto attestation : attestationFinEtudeList) {
					if (attestation.getAnneeAcademiqueCode() == null
							|| attestation.getIndividuLieuNaissance() == null
							|| attestation.getIndividuNomArabe() == null
							|| attestation.getIndividuPrenomArabe() == null
							|| (attestation.getRefLibelleFiliereArabe() == null && attestation
									.getRefLibelleDomaineArabe() == null)
							|| attestation.getRefLibelleNiveauArabe() == null) {
						attestations.add(attestation);
					}

				}
			}

			attestations = attestationFinEtudeList;
			printMgrBean
					.viewAttesSucPDF(attestations, "attestationdiplome_"
							+ sessionBean.getCodeEtablissement()
							+ generateDateString());
			// }

		} catch (Exception e) {
			attestationFinEtudeList = new ArrayList<AttestationFinEtudeDto>();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ":"
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
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
	 * [DiplomeFinEtudeGererBean.loadMention] Method
	 * 
	 * @author BELBRIK Oualid on : 3 nov. 2014 10:16:51
	 */
	private void loadMention() {
		if (mentionList == null || mentionList.isEmpty()) {
			mentionList = utilBean.loadAllMention(anneeAcademiqueId);
		}
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

}
