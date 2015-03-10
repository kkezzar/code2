/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.habilitation.DemandeHabBean.java] 
 * @author BELDI Jamel on : 21 avr. 2014  08:56:50
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.habilitation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.ActionI18nDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.DemandeDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.DemandeI18nDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeActionDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeI18nDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.RoleDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.RoleI18nDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.TacheActionDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.TacheDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.ActionService;
import dz.gov.mesrs.sii.commons.business.service.bpm.DemandeService;
import dz.gov.mesrs.sii.commons.business.service.bpm.EtapeRoleService;
import dz.gov.mesrs.sii.commons.business.service.bpm.EtapeService;
import dz.gov.mesrs.sii.commons.business.service.bpm.RoleService;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.business.service.bpm.TacheActionService;
import dz.gov.mesrs.sii.commons.business.service.bpm.TacheService;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;

/**
 * @author BELDI Jamel on : 23 avr. 2014 10:07:03
 */
@ManagedBean(name = "demandeHabBean")
@RequestScoped
public class DemandeHabBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 09:09:43
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DemandeHabBean.class);
	private ResourceBundle bundleDmde;
	private ResourceBundle bundleCommon;
	// private static final String DEMANDE_HAB_MESSAGES_FILE_NAME =
	// "demandeHabilitationMsgs";
	// private static final String COMMON_MESSAGES_FILE_NAME = "commonmsgs";
	private DemandeDto demandeDto;
	@ManagedProperty("#{param.ofId}")
	private String ofId;
	@ManagedProperty("#{param.resultSize}")
	private String resultSize;
	// private OffreFormationDto ofSearchDto;
	private List<OffreFormationDto> ofResultSearch;

	@ManagedProperty(value = "#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty(value = "#{demandeService}")
	private DemandeService demandeService;
	@ManagedProperty(value = "#{tacheActionService}")
	private TacheActionService tacheActionService;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty(value = "#{actionService}")
	private ActionService actionService;
	@ManagedProperty(value = "#{tacheService}")
	private TacheService tacheService;
	@ManagedProperty(value = "#{etapeService}")
	private EtapeService etapeService;
	@ManagedProperty(value = "#{roleService}")
	private RoleService roleService;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	@ManagedProperty(value = "#{etapeRoleService}")
	private EtapeRoleService etapeRoleService;
	@ManagedProperty("#{param.demandeId}")
	private String demandeId;
	@ManagedProperty("#{param.etapeCode}")
	private String etapeCode;
	HashMap<String, DemandeI18nDto> mapDemandeI18n = new HashMap<String, DemandeI18nDto>();
	HashMap<String, RoleI18nDto> mapRoleI18n = new HashMap<String, RoleI18nDto>();
	HashMap<String, EtapeI18nDto> mapEtapeI18n = new HashMap<String, EtapeI18nDto>();
	private int exception;
	private TacheActionDto tacheActionDto;
	private List<SelectItem> listeActionByEtape;
	private TacheDto tacheDto;
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;

	/**
	 * [DemandeHabBean.listeActionByEtape] Getter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 16:00:09
	 * @return the listeActionByEtape
	 */
	public List<SelectItem> getListeActionByEtape() {
		return listeActionByEtape;
	}

	/**
	 * [DemandeHabBean.listeActionByEtape] Setter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 16:00:09
	 * @param listeActionByEtape
	 *            the listeActionByEtape to set
	 */
	public void setListeActionByEtape(List<SelectItem> listeActionByEtape) {
		this.listeActionByEtape = listeActionByEtape;
	}

	/**
	 * [DemandeHabBean.tacheActionDto] Getter
	 * 
	 * @author BELDI Jamel on : 30 avr. 2014 17:04:44
	 * @return the tacheActionDto
	 */
	public TacheActionDto getTacheActionDto() {
		return tacheActionDto;
	}

	/**
	 * [DemandeHabBean.tacheActionDto] Setter
	 * 
	 * @author BELDI Jamel on : 30 avr. 2014 17:04:44
	 * @param tacheActionDto
	 *            the tacheActionDto to set
	 */
	public void setTacheActionDto(TacheActionDto tacheActionDto) {
		this.tacheActionDto = tacheActionDto;
	}

	/**
	 * [DemandeHabBean.DemandeHabBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 08:56:50
	 */
	public DemandeHabBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleDmde = facesContext.getApplication().getResourceBundle(facesContext,
				OfConstants.DEMANDE_HAB_MESSAGES_FILE_NAME);
		bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
				OfConstants.COMMON_MESSAGES_FILE_NAME);
		demandeDto = new DemandeDto();
		tacheActionDto = new TacheActionDto();
		tacheDto = new TacheDto();
	}

	@PostConstruct
	public void init() {
		exception = 0;
		if (demandeId == null || demandeId.trim().equals("") || demandeId.trim().equals("null")) {
			demandeDto = new DemandeDto();
			mapDemandeI18n = new HashMap<String, DemandeI18nDto>();
			mapDemandeI18n.put("fr", new DemandeI18nDto());
			mapDemandeI18n.put("ar", new DemandeI18nDto());
		} else {
			loadDemande();
		}

		loadTacheDto();
		if (etapeCode != null && !etapeCode.trim().equals("") && !etapeCode.trim().equals("null")) {
			loadListAction();
		}

		findOffres();

	}

	private void loadTacheDto() {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("loadTacheDto");
			EtapeDto etapeDto = new EtapeDto();
			List<TacheDto> currentTasks = null;
			if (etapeCode != null && !etapeCode.trim().equals("") && !etapeCode.trim().equals("null")) {
				etapeDto = etapeService.findEtapeByCode(etapeCode);
				// etapeDto.setId(Integer.valueOf(etapeId));
			}
			currentTasks = tacheService.findCurrentTacheByEtape(etapeDto);
			if (currentTasks != null && !currentTasks.isEmpty()) {
				tacheDto = currentTasks.get(0);// Cas etape successive
				// recuperer l'etape I18n
				HashMap<String, EtapeI18nDto> listEtapeI18n = etapeService.findListEtapeI18nByEtape(etapeDto);
				mapEtapeI18n = new HashMap<String, EtapeI18nDto>();
				if (listEtapeI18n != null && !listEtapeI18n.isEmpty()) {
					if (listEtapeI18n.containsKey("fr")) {
						mapEtapeI18n.put("fr", listEtapeI18n.get("fr"));
					} else {
						mapEtapeI18n.put("fr", new EtapeI18nDto());
					}
					if (listEtapeI18n.containsKey("ar")) {
						mapEtapeI18n.put("ar", listEtapeI18n.get("ar"));
					} else {
						mapEtapeI18n.put("ar", new EtapeI18nDto());
					}
				} else {
					mapEtapeI18n.put("fr", new EtapeI18nDto());
					mapEtapeI18n.put("ar", new EtapeI18nDto());
				}

				// recuperer les roles I18n
				RoleDto roleDto = new RoleDto();
				roleDto.setId(tacheDto.getRoleId());

				HashMap<String, RoleI18nDto> listRoleI18n = roleService.findListRoleI18nByRole(roleDto);
				mapRoleI18n = new HashMap<String, RoleI18nDto>();
				if (listRoleI18n != null && !listRoleI18n.isEmpty()) {
					if (listRoleI18n.containsKey("fr")) {
						mapRoleI18n.put("fr", listRoleI18n.get("fr"));
					} else {
						mapRoleI18n.put("fr", new RoleI18nDto());
					}
					if (listEtapeI18n.containsKey("ar")) {
						mapRoleI18n.put("ar", listRoleI18n.get("ar"));
					} else {
						mapRoleI18n.put("ar", new RoleI18nDto());
					}
				} else {
					mapRoleI18n.put("fr", new RoleI18nDto());
					mapRoleI18n.put("ar", new RoleI18nDto());
				}

			} else {
				tacheDto = new TacheDto();
				tacheDto.setEtapeRoleId(etapeRoleService.findByCodeEtapeAndCodeRole(
						UtilConstants.HABILITATION_SOUMISSION_DEMANDE_CODE,
						UtilConstants.HABILITATION_ROLE_PORTEUR_PROJET).getId());// 1
																					// Soumission
																					// DH
																					// par
																					// le
																					// porteur
																					// du
				// projet
			}
		} catch (Exception e) {
			exception = 1;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + exception + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	/**
	 * [DemandeHabBean.demandeDto] Getter
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 09:26:31
	 * @return the demandeDto
	 */
	public DemandeDto getDemandeDto() {
		return demandeDto;
	}

	/**
	 * [DemandeHabBean.demandeDto] Setter
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 09:26:31
	 * @param demandeDto
	 *            the demandeDto to set
	 */
	public void setDemandeDto(DemandeDto demandeDto) {
		this.demandeDto = demandeDto;
	}

	/**
	 * [DemandeHabBean.ofId] Getter
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 09:26:31
	 * @return the ofId
	 */
	public String getOfId() {
		return ofId;
	}

	/**
	 * [DemandeHabBean.ofId] Setter
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 09:26:31
	 * @param ofId
	 *            the ofId to set
	 */
	public void setOfId(String ofId) {
		this.ofId = ofId;
	}

	/**
	 * [DemandeHabBean.resultSize] Getter
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 09:48:37
	 * @return the resultSize
	 */
	public String getResultSize() {
		return resultSize;
	}

	/**
	 * [DemandeHabBean.resultSize] Setter
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 09:48:37
	 * @param resultSize
	 *            the resultSize to set
	 */
	public void setResultSize(String resultSize) {
		this.resultSize = resultSize;
	}

	/**
	 * [DemandeHabBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 22 avr. 2014 11:18:54
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [DemandeHabBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 22 avr. 2014 11:18:54
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [DemandeHabBean.offreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 10:07:44
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [DemandeHabBean.offreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 10:07:44
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [DemandeHabBean.demandeService] Getter
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 15:47:52
	 * @return the demandeService
	 */
	public DemandeService getDemandeService() {
		return demandeService;
	}

	/**
	 * [DemandeHabBean.demandeService] Setter
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 15:47:52
	 * @param demandeService
	 *            the demandeService to set
	 */
	public void setDemandeService(DemandeService demandeService) {
		this.demandeService = demandeService;
	}

	/**
	 * [DemandeHabBean.ofResultSearch] Getter
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 10:13:30
	 * @return the ofResultSearch
	 */
	public List<OffreFormationDto> getOfResultSearch() {
		return ofResultSearch;
	}

	/**
	 * [DemandeHabBean.ofResultSearch] Setter
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 10:13:30
	 * @param ofResultSearch
	 *            the ofResultSearch to set
	 */
	public void setOfResultSearch(List<OffreFormationDto> ofResultSearch) {
		this.ofResultSearch = ofResultSearch;
	}

	/**
	 * [DemandeHabBean.findOffres] Method
	 * 
	 * @author BELDI Jamel on : 22 avr. 2014 11:30:20
	 */
	public void findOffres() {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("findOffres");
			ofResultSearch = offreFormationService.findOffreToHabilitation(new OffreFormationDto());

			if (ofResultSearch == null || ofResultSearch.isEmpty()) {

				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundleCommon.getString("warn_info") + ": "
						+ bundleCommon.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				log.info(ofResultSearch.size());
				setResultSize(String.valueOf(ofResultSearch.size()));
			}

		} catch (Exception e) {
			exception = 4;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + exception + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}

	}

	public void loadDemande() {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("loadDemande");
			demandeDto = demandeService.findById(Integer.valueOf(demandeId));
			if (demandeDto != null) {
				HashMap<String, DemandeI18nDto> listDemandeI18n = demandeService
						.findListDemandei18nByDemande(demandeDto);
				mapDemandeI18n = new HashMap<String, DemandeI18nDto>();
				if (listDemandeI18n != null && !listDemandeI18n.isEmpty()) {
					if (listDemandeI18n.containsKey("fr")) {
						mapDemandeI18n.put("fr", listDemandeI18n.get("fr"));
					} else {
						mapDemandeI18n.put("fr", new DemandeI18nDto());
					}
					if (listDemandeI18n.containsKey("ar")) {
						mapDemandeI18n.put("ar", listDemandeI18n.get("ar"));
					} else {
						mapDemandeI18n.put("ar", new DemandeI18nDto());
					}
				} else {
					mapDemandeI18n.put("fr", new DemandeI18nDto());
					mapDemandeI18n.put("ar", new DemandeI18nDto());
				}
			}

		} catch (Exception e) {
			exception = 1;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + exception + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}

	}

	/**
	 * [DemandeHabBean.toSubmit] Method to navigate to Submit DEMANDE
	 * HABILITATION Form
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 15:30:46
	 * @return outcome
	 */
	public String toSubmit() {
		if (exception == 0) {
			return "toSubmit";
		}
		{
			return null;
		}
	}

	/**
	 * [DemandeHabBean.toValidate] Method to navigate to Validate DEMANDE
	 * HABILITATION Form
	 * 
	 * @author BELDI Jamel on : 29 avr. 2014 09:48:39
	 * @return outcome
	 */
	public String toValidate() {
		if (exception == 0) {
			return "toValidate";
		}
		{
			return null;
		}
	}

	/**
	 * [DemandeHabBean.toView] Method to navigate to View DEMANDE HABILITATION
	 * Form
	 * 
	 * @author BELDI Jamel on : 29 avr. 2014 09:48:43
	 * @returnoutcome
	 */
	public String toView() {
		if (exception == 0) {
			return "toView";
		}
		{
			return null;
		}
	}

	/**
	 * [DemandeHabBean.goBack] Method to return
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 15:31:47
	 * @return outcome
	 */
	public String goBack() {
		// if (demandeDto.getId() != 0) {
		// return "toSearchOf";
		// } else {
		// return "toListDemande";
		// }
		return "toSearch";
	}

	/**
	 * [DemandeHabBean.submit] Method to Save or update Demande and start the
	 * process of HABILITATION
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 15:36:06
	 * @return outcome
	 */
	public String submit() {
		FacesMessage msg = new FacesMessage();
		try {
			demandeDto.setOffreFormationId(Integer.valueOf(ofId));
			demandeDto.setDateDemande(new Date());
			demandeDto.setTypeDemandeId(demandeService.findTypeDemandeByCode(
					UtilConstants.TYPE_DEMANDE_HABILITATION_CODE).getId());
			demandeDto
					.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DEMANDE_CODE, UtilConstants.DEMANDE_SITUTAION_SOUMISE_VALIDATION_CODE)
							.getId());// 2
			demandeDto = demandeService.saveOrUpdate(demandeDto, mapDemandeI18n);
			// Create Tache soumission
			tacheDto.setDateCreation(demandeDto.getDateDemande());
			tacheDto.setDateCloture(demandeDto.getDateDemande());
			tacheDto.setIdDemande(demandeDto.getId());
			tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_TERMINEE_CODE).getId());// 21
			tacheService.insertOrUpdate(tacheDto);
			// Create Tache Action Soumettre
			tacheActionDto.setUtilisateur(this.sessionBean.getSessionBean().getCompte().getNomUtilisateur());
			tacheActionDto.setDateAction(demandeDto.getDateDemande());
			tacheActionDto.setActionId(actionService.findByCode(UtilConstants.ACTION_SMTR).getId());// Action
																									// SMTR
			tacheActionDto.setTacheId(tacheDto.getId());
			tacheActionService.insertOrUpdate(tacheActionDto);
			// Create Tache Validation
			tacheDto = new TacheDto();
			tacheDto.setDateCreation(new Date());
			tacheDto.setIdDemande(demandeDto.getId());
			tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_EN_COURS_CODE).getId());// 20

			tacheDto.setEtapeRoleId(etapeRoleService.findByCodeEtapeAndCodeRole(
					UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
					UtilConstants.HABILITATION_ROLE_RESPONSABLE_DOMOMAINE).getId());// 2
			tacheService.insertOrUpdate(tacheDto);

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleDmde.getString("demande_habilitation_success_soumettre") + "\n"
					+ bundleDmde.getString("demande_habilitation_code") + ": " + demandeDto.getCode());
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			exception = 2;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + exception + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
		flash.setKeepMessages(true);
		return "DemandeHabSubmitSearch?faces-redirect=true";
	}

	/**
	 * [DemandeHabBean.submit] Method to Save or update Demande
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 15:36:06
	 * @return outcome
	 */
	public void save() {
		FacesMessage msg = new FacesMessage();
		try {
			// demandeDto.setDateDemande(new Date())
			demandeDto.setOffreFormationId(Integer.valueOf(ofId));
			demandeDto.setTypeDemandeId(demandeService.findTypeDemandeByCode(
					UtilConstants.TYPE_DEMANDE_HABILITATION_CODE).getId());
			demandeDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_DEMANDE_CODE, UtilConstants.DEMANDE_SITUTAION_CREEE_CODE).getId());// 1
																											// correspond
																											// à
																											// l'etat
																											// créee
			// d'une demande à gérer
			demandeDto = demandeService.saveOrUpdate(demandeDto, mapDemandeI18n);
			if (demandeId == null || demandeId.trim().equals("") || demandeId.trim().equals("null")) {
				demandeId = String.valueOf(demandeDto.getId());
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ bundleCommon.getString("msg_success_enregistrement") + "\n"
						+ bundleDmde.getString("demande_habilitation_code") + ": " + demandeDto.getCode());
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ bundleCommon.getString("msg_success_modification") + "\n"
						+ bundleDmde.getString("demande_habilitation_code") + ": " + demandeDto.getCode());
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			exception = 5;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + exception + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}

	}

	/**
	 * [DemandeHabBean.submit] Method to Save or update Demande and start the
	 * process of HABILITATION
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 15:36:06
	 * @return outcome
	 */
	public String execute() {
		FacesMessage msg = new FacesMessage();
		try {

			if (tacheActionDto.getActionId() != 0) {
				tacheActionDto.setUtilisateur(this.sessionBean.getSessionBean().getCompte().getNomUtilisateur());
				tacheActionDto.setDateAction(new Date());
				tacheActionDto.setTacheId(tacheDto.getId());
				tacheActionService.insertOrUpdate(tacheActionDto);

				if (tacheActionDto.getActionId() == actionService.findByCode(UtilConstants.ACTION_VLDR).getId()) {// valider
					if (tacheDto.getEtapeRoleId() == etapeRoleService.findByCodeEtapeAndCodeRole(
							UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
							UtilConstants.HABILITATION_ROLE_RESPONSABLE_DOMOMAINE).getId()) {// 2
						// Finir
						tacheDto.setDateCloture(new Date());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_TERMINEE_CODE).getId());// 21
						tacheService.insertOrUpdate(tacheDto);
						// Creer une autre
						tacheDto = new TacheDto();
						tacheDto.setDateCreation(new Date());
						tacheDto.setIdDemande(demandeDto.getId());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_EN_COURS_CODE).getId());// 20
						tacheDto.setEtapeRoleId(etapeRoleService.findByCodeEtapeAndCodeRole(
								UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
								UtilConstants.HABILITATION_ROLE_CHEF_DEPARTEMENT).getId());// 3
						tacheService.insertOrUpdate(tacheDto);
					} else if (tacheDto.getEtapeRoleId() == etapeRoleService.findByCodeEtapeAndCodeRole(
							UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
							UtilConstants.HABILITATION_ROLE_CHEF_DEPARTEMENT).getId()) {// 3
						// Finir
						tacheDto.setDateCloture(new Date());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_TERMINEE_CODE).getId());// 21
						tacheService.insertOrUpdate(tacheDto);
						// Creer une autre
						tacheDto = new TacheDto();
						tacheDto.setDateCreation(new Date());
						tacheDto.setIdDemande(demandeDto.getId());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_EN_COURS_CODE).getId());// 20
						tacheDto.setEtapeRoleId(etapeRoleService.findByCodeEtapeAndCodeRole(
								UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
								UtilConstants.HABILITATION_ROLE_COMMITE_SCIENTIFIQUE_DEPARTEMENT).getId());// 4
						tacheService.insertOrUpdate(tacheDto);
					} else if (tacheDto.getEtapeRoleId() == etapeRoleService.findByCodeEtapeAndCodeRole(
							UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
							UtilConstants.HABILITATION_ROLE_COMMITE_SCIENTIFIQUE_DEPARTEMENT).getId()) {// 4
						// Finir
						tacheDto.setDateCloture(new Date());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_TERMINEE_CODE).getId());// 21
						tacheService.insertOrUpdate(tacheDto);
						// Creer une autre
						tacheDto = new TacheDto();
						tacheDto.setDateCreation(new Date());
						tacheDto.setIdDemande(demandeDto.getId());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_EN_COURS_CODE).getId());// 20
						tacheDto.setEtapeRoleId(etapeRoleService.findByCodeEtapeAndCodeRole(
								UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
								UtilConstants.HABILITATION_ROLE_CONSEIL_SCIENTIFIQUE_FACULTE).getId());// 5
						tacheService.insertOrUpdate(tacheDto);
					} else if (tacheDto.getEtapeRoleId() == etapeRoleService.findByCodeEtapeAndCodeRole(
							UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
							UtilConstants.HABILITATION_ROLE_CONSEIL_SCIENTIFIQUE_FACULTE).getId()) {// 5
						// Finir
						tacheDto.setDateCloture(new Date());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_TERMINEE_CODE).getId());// 21
						tacheService.insertOrUpdate(tacheDto);
						// Creer une autre
						tacheDto = new TacheDto();
						tacheDto.setDateCreation(new Date());
						tacheDto.setIdDemande(demandeDto.getId());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_EN_COURS_CODE).getId());// 20
						tacheDto.setEtapeRoleId(etapeRoleService.findByCodeEtapeAndCodeRole(
								UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
								UtilConstants.HABILITATION_ROLE_DOYEN).getId());// 6
						tacheService.insertOrUpdate(tacheDto);
					} else if (tacheDto.getEtapeRoleId() == etapeRoleService.findByCodeEtapeAndCodeRole(
							UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE, UtilConstants.HABILITATION_ROLE_DOYEN)
							.getId()) {// 6
						// Finir
						tacheDto.setDateCloture(new Date());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_TERMINEE_CODE).getId());// 21
						tacheService.insertOrUpdate(tacheDto);
						// Creer une autre
						tacheDto = new TacheDto();
						tacheDto.setDateCreation(new Date());
						tacheDto.setIdDemande(demandeDto.getId());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_EN_COURS_CODE).getId());// 20
						tacheDto.setEtapeRoleId(etapeRoleService.findByCodeEtapeAndCodeRole(
								UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
								UtilConstants.HABILITATION_ROLE_CONS_ETABLISSEMENT).getId());// 7
						tacheService.insertOrUpdate(tacheDto);
					} else if (tacheDto.getEtapeRoleId() == etapeRoleService.findByCodeEtapeAndCodeRole(
							UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
							UtilConstants.HABILITATION_ROLE_CONS_ETABLISSEMENT).getId()) {// 7
						// Finir
						tacheDto.setDateCloture(new Date());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_TERMINEE_CODE).getId());// 21
						tacheService.insertOrUpdate(tacheDto);
						// Creer une autre
						tacheDto = new TacheDto();
						tacheDto.setDateCreation(new Date());
						tacheDto.setIdDemande(demandeDto.getId());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_EN_COURS_CODE).getId());// 20
						tacheDto.setEtapeRoleId(etapeRoleService.findByCodeEtapeAndCodeRole(
								UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
								UtilConstants.HABILITATION_ROLE_CHEF_ETABLISSEMENT).getId());// 8
						tacheService.insertOrUpdate(tacheDto);
					} else if (tacheDto.getEtapeRoleId() == etapeRoleService.findByCodeEtapeAndCodeRole(
							UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
							UtilConstants.HABILITATION_ROLE_CHEF_ETABLISSEMENT).getId()) {// 8
						// Finir
						tacheDto.setDateCloture(new Date());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_TERMINEE_CODE).getId());// 21
						tacheService.insertOrUpdate(tacheDto);
						// Creer une autre
						tacheDto = new TacheDto();
						tacheDto.setDateCreation(new Date());
						tacheDto.setIdDemande(demandeDto.getId());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_EN_COURS_CODE).getId());// 20
						tacheDto.setEtapeRoleId(etapeRoleService.findByCodeEtapeAndCodeRole(
								UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
								UtilConstants.HABILITATION_ROLE_VICE_RECTEUR).getId());// 9
						tacheService.insertOrUpdate(tacheDto);
					} else if (tacheDto.getEtapeRoleId() == etapeRoleService.findByCodeEtapeAndCodeRole(
							UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
							UtilConstants.HABILITATION_ROLE_VICE_RECTEUR).getId()) {// 9
						// Finir
						tacheDto.setDateCloture(new Date());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_TERMINEE_CODE).getId());// 21
						tacheService.insertOrUpdate(tacheDto);
						// Creer une autre
						tacheDto = new TacheDto();
						tacheDto.setDateCreation(new Date());
						tacheDto.setIdDemande(demandeDto.getId());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_EN_COURS_CODE).getId());// 20
						tacheDto.setEtapeRoleId(etapeRoleService.findByCodeEtapeAndCodeRole(
								UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
								UtilConstants.HABILITATION_ROLE_CONFERENCE_REGIONALE_UNIVERSITE).getId());// 10
						tacheService.insertOrUpdate(tacheDto);
					} else if (tacheDto.getEtapeRoleId() == etapeRoleService.findByCodeEtapeAndCodeRole(
							UtilConstants.HABILITATION_VALIDATION_DEMANDE_CODE,
							UtilConstants.HABILITATION_ROLE_CONFERENCE_REGIONALE_UNIVERSITE).getId()) {// 10
						// Finir
						tacheDto.setDateCloture(new Date());
						tacheDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_TERMINEE_CODE).getId());// 21
						tacheService.insertOrUpdate(tacheDto);
						// changer situation de la demande
						demandeDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_DEMANDE_CODE, UtilConstants.DEMANDE_SITUTAION_VALIDEE_CODE)
								.getId());// 22
						demandeDto = demandeService.saveOrUpdate(demandeDto, mapDemandeI18n);

					}

				} else if (tacheActionDto.getActionId() == actionService.findByCode(UtilConstants.ACTION_INVR).getId()) {// Invalider
					demandeDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DEMANDE_CODE, UtilConstants.DEMANDE_SITUTAION_INVALIDEE_CODE).getId());// 23
					demandeDto = demandeService.saveOrUpdate(demandeDto, mapDemandeI18n);
				}

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ bundleDmde.getString("demande_habilitation_success_execution"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			exception = 6;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + exception + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
		flash.setKeepMessages(true);
		return "DemandeHabValiderSearch?faces-redirect=true";
	}

	/**
	 * [DemandeHabBean.flash] Getter
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 16:40:05
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [DemandeHabBean.flash] Setter
	 * 
	 * @author BELDI Jamel on : 21 avr. 2014 16:40:05
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [DemandeHabBean.findNC] Method
	 * 
	 * @author BELDI Jamel on : 22 avr. 2014 11:28:48
	 * @param ncName
	 * @param code
	 * @return
	 */
	/*
	 * private NomenclatureDto findNC(String ncName, String code) {
	 * 
	 * try { List<NomenclatureDto> nomenclatures = nomenclatureWSClient
	 * .findByName(ncName); if (nomenclatures != null &&
	 * !nomenclatures.isEmpty()) { for (NomenclatureDto nomenclatureDto :
	 * nomenclatures) { if (code.equals(nomenclatureDto.getCode())) { return
	 * nomenclatureDto; } } } } catch (Exception_Exception e) { exception = 3;
	 * e.printStackTrace(); } return null; }
	 */

	/**
	 * [DemandeHabBean.mapDemandeI18n] Getter
	 * 
	 * @author BELDI Jamel on : 24 avr. 2014 10:48:22
	 * @return the mapDemandeI18n
	 */
	public HashMap<String, DemandeI18nDto> getMapDemandeI18n() {
		return mapDemandeI18n;
	}

	/**
	 * [DemandeHabBean.mapDemandeI18n] Setter
	 * 
	 * @author BELDI Jamel on : 24 avr. 2014 10:48:22
	 * @param mapDemandeI18n
	 *            the mapDemandeI18n to set
	 */
	public void setMapDemandeI18n(HashMap<String, DemandeI18nDto> mapDemandeI18n) {
		this.mapDemandeI18n = mapDemandeI18n;
	}

	/**
	 * [DemandeHabBean.demandeId] Getter
	 * 
	 * @author BELDI Jamel on : 24 avr. 2014 11:14:59
	 * @return the demandeId
	 */
	public String getDemandeId() {
		return demandeId;
	}

	/**
	 * [DemandeHabBean.demandeId] Setter
	 * 
	 * @author BELDI Jamel on : 24 avr. 2014 11:14:59
	 * @param demandeId
	 *            the demandeId to set
	 */
	public void setDemandeId(String demandeId) {
		this.demandeId = demandeId;
	}

	/**
	 * [DemandeHabBean.exception] Getter
	 * 
	 * @author BELDI Jamel on : 24 avr. 2014 12:20:12
	 * @return the exception
	 */
	public int getException() {
		return exception;
	}

	/**
	 * [DemandeHabBean.exception] Setter
	 * 
	 * @author BELDI Jamel on : 24 avr. 2014 12:20:12
	 * @param exception
	 *            the exception to set
	 */
	public void setException(int exception) {
		this.exception = exception;
	}

	/**
	 * [DemandeHabBean.tacheActionService] Getter
	 * 
	 * @author BELDI Jamel on : 30 avr. 2014 17:06:14
	 * @return the tacheActionService
	 */
	public TacheActionService getTacheActionService() {
		return tacheActionService;
	}

	/**
	 * [DemandeHabBean.tacheActionService] Setter
	 * 
	 * @author BELDI Jamel on : 30 avr. 2014 17:06:14
	 * @param tacheActionService
	 *            the tacheActionService to set
	 */
	public void setTacheActionService(TacheActionService tacheActionService) {
		this.tacheActionService = tacheActionService;
	}

	/**
	 * [DemandeHabBean.etapeCode] Getter
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 11:44:29
	 * @return the etapeCode
	 */
	public String getEtapeCode() {
		return etapeCode;
	}

	/**
	 * [DemandeHabBean.etapeCode] Setter
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 11:44:29
	 * @param etapeCode
	 *            the etapeCode to set
	 */
	public void setEtapeCode(String etapeCode) {
		this.etapeCode = etapeCode;
	}

	/**
	 * [DemandeHabBean.actionService] Getter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 16:05:59
	 * @return the actionService
	 */
	public ActionService getActionService() {
		return actionService;
	}

	/**
	 * [DemandeHabBean.actionService] Setter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 16:05:59
	 * @param actionService
	 *            the actionService to set
	 */
	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}

	public void loadListAction() {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("loadListAction");

			listeActionByEtape = new ArrayList<SelectItem>();
			EtapeActionDto etapeActionDto = new EtapeActionDto();
			etapeService.findEtapeByCode(etapeCode);
			etapeActionDto.setEtapeId(etapeService.findEtapeByCode(etapeCode).getId());
			List<ActionI18nDto> listActions = actionService.findListActioni18nByEtape(etapeActionDto);

			for (ActionI18nDto actionI18nDto : listActions) {
				if (actionI18nDto.getLangue().equals("fr")) {
					SelectItem selectItem = new SelectItem(actionI18nDto.getActionId(), actionI18nDto.getLibelle());
					listeActionByEtape.add(selectItem);
				}

			}

		} catch (Exception e) {
			exception = 6;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + exception + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}

	}

	/**
	 * [DemandeHabBean.tacheService] Getter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 18:34:42
	 * @return the tacheService
	 */
	public TacheService getTacheService() {
		return tacheService;
	}

	/**
	 * [DemandeHabBean.tacheService] Setter
	 * 
	 * @author BELDI Jamel on : 4 mai 2014 18:34:42
	 * @param tacheService
	 *            the tacheService to set
	 */
	public void setTacheService(TacheService tacheService) {
		this.tacheService = tacheService;
	}

	/**
	 * [DemandeHabBean.mapRoleI18n] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:14:00
	 * @return the mapRoleI18n
	 */
	public HashMap<String, RoleI18nDto> getMapRoleI18n() {
		return mapRoleI18n;
	}

	/**
	 * [DemandeHabBean.mapRoleI18n] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:14:00
	 * @param mapRoleI18n
	 *            the mapRoleI18n to set
	 */
	public void setMapRoleI18n(HashMap<String, RoleI18nDto> mapRoleI18n) {
		this.mapRoleI18n = mapRoleI18n;
	}

	/**
	 * [DemandeHabBean.mapEtapeI18n] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:14:00
	 * @return the mapEtapeI18n
	 */
	public HashMap<String, EtapeI18nDto> getMapEtapeI18n() {
		return mapEtapeI18n;
	}

	/**
	 * [DemandeHabBean.mapEtapeI18n] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:14:00
	 * @param mapEtapeI18n
	 *            the mapEtapeI18n to set
	 */
	public void setMapEtapeI18n(HashMap<String, EtapeI18nDto> mapEtapeI18n) {
		this.mapEtapeI18n = mapEtapeI18n;
	}

	/**
	 * [DemandeHabBean.tacheDto] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:14:00
	 * @return the tacheDto
	 */
	public TacheDto getTacheDto() {
		return tacheDto;
	}

	/**
	 * [DemandeHabBean.tacheDto] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:14:00
	 * @param tacheDto
	 *            the tacheDto to set
	 */
	public void setTacheDto(TacheDto tacheDto) {
		this.tacheDto = tacheDto;
	}

	/**
	 * [DemandeHabBean.etapeService] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:45:22
	 * @return the etapeService
	 */
	public EtapeService getEtapeService() {
		return etapeService;
	}

	/**
	 * [DemandeHabBean.etapeService] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:45:22
	 * @param etapeService
	 *            the etapeService to set
	 */
	public void setEtapeService(EtapeService etapeService) {
		this.etapeService = etapeService;
	}

	/**
	 * [DemandeHabBean.roleService] Getter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:45:22
	 * @return the roleService
	 */
	public RoleService getRoleService() {
		return roleService;
	}

	/**
	 * [DemandeHabBean.roleService] Setter
	 * 
	 * @author BELDI Jamel on : 5 mai 2014 11:45:22
	 * @param roleService
	 *            the roleService to set
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * [DemandeHabBean.situationService] Getter
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 10:03:39
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [DemandeHabBean.situationService] Setter
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 10:03:39
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [DemandeHabBean.etapeRoleService] Getter
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 10:44:51
	 * @return the etapeRoleService
	 */
	public EtapeRoleService getEtapeRoleService() {
		return etapeRoleService;
	}

	/**
	 * [DemandeHabBean.etapeRoleService] Setter
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 10:44:51
	 * @param etapeRoleService
	 *            the etapeRoleService to set
	 */
	public void setEtapeRoleService(EtapeRoleService etapeRoleService) {
		this.etapeRoleService = etapeRoleService;
	}

	/**
	 * [DemandeHabBean.loginBean] Getter
	 * 
	 * @author BELDI Jamel on : 8 juil. 2014 09:56:40
	 * @return the loginBean
	 */
	public LoginBean getLoginBean() {
		return loginBean;
	}

	/**
	 * [DemandeHabBean.loginBean] Setter
	 * 
	 * @author BELDI Jamel on : 8 juil. 2014 09:56:40
	 * @param loginBean
	 *            the loginBean to set
	 */
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	// /**
	// * [DemandeHabBean.listDomaines] Getter
	// * @author BELDI Jamel on : 22 avr. 2014 09:54:38
	// * @return the listDomaines
	// */
	// public List<SelectItem> getListDomaines() {
	// return listDomaines;
	// }
	// /**
	// * [DemandeHabBean.listDomaines] Setter
	// * @author BELDI Jamel on : 22 avr. 2014 09:54:38
	// * @param listDomaines the listDomaines to set
	// */
	// public void setListDomaines(List<SelectItem> listDomaines) {
	// this.listDomaines = listDomaines;
	// }
	// /**
	// * [DemandeHabBean.listFilieres] Getter
	// * @author BELDI Jamel on : 22 avr. 2014 09:54:38
	// * @return the listFilieres
	// */
	// public List<SelectItem> getListFilieres() {
	// return listFilieres;
	// }
	// /**
	// * [DemandeHabBean.listFilieres] Setter
	// * @author BELDI Jamel on : 22 avr. 2014 09:54:38
	// * @param listFilieres the listFilieres to set
	// */
	// public void setListFilieres(List<SelectItem> listFilieres) {
	// this.listFilieres = listFilieres;
	// }
	// /**
	// * [DemandeHabBean.listSpecialites] Getter
	// * @author BELDI Jamel on : 22 avr. 2014 09:54:38
	// * @return the listSpecialites
	// */
	// public List<SelectItem> getListSpecialites() {
	// return listSpecialites;
	// }
	// /**
	// * [DemandeHabBean.listSpecialites] Setter
	// * @author BELDI Jamel on : 22 avr. 2014 09:54:38
	// * @param listSpecialites the listSpecialites to set
	// */
	// public void setListSpecialites(List<SelectItem> listSpecialites) {
	// this.listSpecialites = listSpecialites;
	// }
	//
	// /**
	// * [DemandeHabBean.loadDomaines] Method loading DOMAINS
	// * @author BELDI Jamel on : 22 avr. 2014 11:20:53
	// */
	// public void loadDomaines(){
	// if (listDomaines == null || listDomaines.isEmpty()){
	// listDomaines = new ArrayList<SelectItem>();
	// try {
	// List<NomenclatureDto> domaines=
	// nomenclatureWSClient.findByName(OfConstants.NC_NAME_DOMAINES);
	// if (domaines!=null && !domaines.isEmpty()){
	// for (NomenclatureDto domain : domaines) {
	// SelectItem selectItem = new SelectItem(domain.getCode(),
	// domain.getLibelleLongFr());
	// listDomaines.add(selectItem);
	// }
	// }
	// } catch (Exception_Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// }
	//
	// /**
	// * [DemandeHabBean.domainChanged] Method Listener to load FILIERES
	// * @author BELDI Jamel on : 22 avr. 2014 11:20:36
	// */
	// public void domainChanged() {
	// listFilieres = new ArrayList<SelectItem>();
	// if (sessionBean.getOfSearchDto() != null &&
	// sessionBean.getOfSearchDto().getRefCodeDomaine()!=null ){
	// NomenclatureDto domaine= findNC(OfConstants.NC_NAME_DOMAINES,
	// sessionBean.getOfSearchDto().getRefCodeDomaine());
	// List<NomenclatureDto> filieres = new ArrayList<NomenclatureDto>();
	// try {
	// filieres =
	// nomenclatureWSClient.findByReference(OfConstants.NC_NAME_FILIERES,
	// domaine.getId());
	// } catch (Exception_Exception e) {
	// e.printStackTrace();
	// }
	// for (NomenclatureDto filiere : filieres) {
	// SelectItem selectItem = new SelectItem(filiere.getCode(),
	// filiere.getLibelleLongFr());
	// listFilieres.add(selectItem);
	// }
	// }
	// }
	//
	// /**
	// * [DemandeHabBean.filiereChanged] Method Listener to load SPECIALITES
	// * @author BELDI Jamel on : 22 avr. 2014 11:20:02
	// */
	// public void filiereChanged() {
	// domainChanged();
	// listSpecialites = new ArrayList<SelectItem>();
	// if (sessionBean.getOfSearchDto() != null &&
	// sessionBean.getOfSearchDto().getRefCodeFiliere()!=null ){
	// NomenclatureDto filiere= findNC(OfConstants.NC_NAME_FILIERES,
	// sessionBean.getOfSearchDto().getRefCodeFiliere());
	// List<NomenclatureDto> specialites = new ArrayList<NomenclatureDto>();
	// try {
	// specialites =
	// nomenclatureWSClient.findByReference(OfConstants.NC_NAME_SPECIALITES,
	// filiere.getId());
	// } catch (Exception_Exception e) {
	// e.printStackTrace();
	// }
	// for (NomenclatureDto specialite : specialites) {
	// SelectItem selectItem = new SelectItem(specialite.getCode(),
	// specialite.getLibelleLongFr());
	// listSpecialites.add(selectItem);
	// }
	// }
	// }

}
