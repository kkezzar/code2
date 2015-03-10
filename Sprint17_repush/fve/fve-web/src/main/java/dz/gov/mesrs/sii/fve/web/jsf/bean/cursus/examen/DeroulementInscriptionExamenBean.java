package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen;

import java.io.Serializable;
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

import org.dozer.DozerBeanMapper;
import org.primefaces.event.RowEditEvent;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.InscriptionExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.SalleExamenDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.ExamenSessionService;
import dz.gov.mesrs.sii.fve.business.service.cursus.ParcoursIndividualiseService;
import dz.gov.mesrs.sii.fve.business.service.examen.InscriptionExamenService;
import dz.gov.mesrs.sii.fve.business.service.examen.PlanningSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.SalleExamenService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RepartitionUeService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

/**
 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
 */
@ManagedBean(name = "deroulementInscriptionExamenBean")
@ViewScoped
public class DeroulementInscriptionExamenBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:10
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	private ResourceBundle bundleCommon;
	private ResourceBundle examenBundle;
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;
	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty("#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{niveauService}")
	private NiveauService niveauService;
	@ManagedProperty("#{periodeService}")
	private PeriodeService periodeService;
	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	@ManagedProperty("#{planningSessionService}")
	private PlanningSessionService planningSessionService;
	// @ManagedProperty("#{planningSessionSaveBean}")
	// private PlanningSessionSaveBean planningSessionSaveBean;
	@ManagedProperty("#{examenSessionService}")
	private ExamenSessionService examenSessionService;
	@ManagedProperty("#{inscriptionExamenService}")
	private InscriptionExamenService inscriptionExamenService;
	@ManagedProperty("#{repartitionUeService}")
	private RepartitionUeService repartitionUeService;
	@ManagedProperty("#{rattachementMcService}")
	private RattachementMcService rattachementMcService;
	@ManagedProperty("#{parcoursIndividualiseService}")
	private ParcoursIndividualiseService parcoursIndividualiseService;
	private List<SelectItem> examensList;
	// private ExamenSessionDto examenSessionDto;
	private List<InscriptionExamenDto> inscriptionExamenList;
	private SalleExamenDto salleExamenDto;
	private List<SelectItem> salleExamenList;
	@ManagedProperty("#{salleExamenService}")
	private SalleExamenService salleExamenService;

	/**
	 * [DeroulementInscriptionExamenBean.DeroulementInscriptionExamenBean()]
	 * Constructor
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
	 */
	public DeroulementInscriptionExamenBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		examenBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.EXAMEN_BUNDLE_MSG_NAME);

	}

	/**
	 * 
	 * [DeroulementInscriptionExamenBean.init] Method
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:10:41
	 */
	@PostConstruct
	public void init() {
		// examenSessionDto = new ExamenSessionDto();
		salleExamenDto = new SalleExamenDto();
		// loadExamenList();
		loadinscriptionExamenList();
	}

	/**
	 * 
	 * [DeroulementInscriptionExamenBean.loadExamenList] Method
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:39:03
	 * @param idPlanningSession
	 */
	public void loadExamenList(long idPlanningSession) {
		examensList = new ArrayList<SelectItem>();
		try {
			// List<ExamenSessionDto> _examens = planningSessionSaveBean
			// .getExamensSessionList();
			List<ExamenSessionDto> _examens = examenSessionService
					.findBySession(idPlanningSession);
			if (_examens != null && !_examens.isEmpty()) {
				for (ExamenSessionDto examenSessionDto : _examens) {
					SelectItem selectItem = new SelectItem(
							examenSessionDto.getId(),
							examenSessionDto.getMcLibelleFr()
									+ "("
									+ examenSessionDto
											.getRefTypeExamenLibelle() + ")");
					examensList.add(selectItem);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * [DeroulementInscriptionExamenBean.examenChanged] Method
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 15:24:34
	 */
	public void examenChanged() {
		try {
			loadSalleExamenList();
		} catch (Exception e) {

		}
	}

	/**
	 * 
	 * [DeroulementInscriptionExamenBean.loadSalleExamenList] Method
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 17:29:30
	 */
	private void loadSalleExamenList() {
		salleExamenList = new ArrayList<SelectItem>();
		try {
			if (salleExamenDto != null
					&& salleExamenDto.getExamenSessionId() != null
					&& salleExamenDto.getExamenSessionId() != 0) {
				List<SalleExamenDto> _salles = salleExamenService
						.findByExamen(salleExamenDto.getExamenSessionId());
				if (_salles != null && !_salles.isEmpty()) {
					for (SalleExamenDto salleExamenDto : _salles) {
						SelectItem selectItem = new SelectItem(
								salleExamenDto.getId(),

								salleExamenDto.getRefLieuLibelle());
						salleExamenList.add(selectItem);
					}
					if(_salles.size() == 1) {
						salleExamenDto = _salles.get(0);
						salleChanged();
					}
				}
			}
//			salleExamenDto.setId(null);
//			salleChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * [DeroulementInscriptionExamenBean.salleChanged] Method
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 17:29:25
	 */
	public void salleChanged() {
		loadinscriptionExamenList();
	}

	/**
	 * 
	 * [DeroulementInscriptionExamenBean.loadinscriptionExamenList] Method
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 16:55:24
	 */
	public void loadinscriptionExamenList() {

		try {

			inscriptionExamenList = new ArrayList<InscriptionExamenDto>();
			if (salleExamenDto.getExamenSessionId() != null
					&& salleExamenDto.getExamenSessionId() != 0
					&& salleExamenDto.getId() != null) {
				// examenSessionDto =
				// examenSessionService.findById(examenSessionDto.getId());
				List<InscriptionExamenDto> _inscriptionListe;
				if (salleExamenDto.getId() != null
						&& salleExamenDto.getId() != 0) {
					_inscriptionListe = inscriptionExamenService
							.findBySalleExamen(salleExamenDto.getId());
				} else {
					_inscriptionListe = inscriptionExamenService
							.findByExamen(new Long(salleExamenDto
									.getExamenSessionId()));
				}

				if (_inscriptionListe != null && !_inscriptionListe.isEmpty()) {
					for (InscriptionExamenDto inscriptionExamenDto : _inscriptionListe) {
						// Individu service
						RefIndividuDto _refIndividuDto = refIndividuService
								.findByIdentifiant(inscriptionExamenDto
										.getNin());
						if (_refIndividuDto != null) {
							map(_refIndividuDto, inscriptionExamenDto);
							inscriptionExamenList.add(inscriptionExamenDto);
						}

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [ExamenSessionBean.onRowEdit] Method
	 * 
	 * @author BELDI Jamel on : 30 sept. 2014 17:10:22
	 * @param event
	 */
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage();
		String detailMsg = "msg_success_modification";
		try {
			InscriptionExamenDto element = ((InscriptionExamenDto) event
					.getObject());
			if (element.getCopieNonRemise() && element.getAbsent()) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundleCommon.getString("error_echec")
						+ ": "
						+ examenBundle
								.getString("examen_warn_absence_copie_non_remise"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			inscriptionExamenService.insertOrUpdate(element);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success"));
			msg.setDetail(detailMsg);
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec"));
			msg.setDetail(bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	/**
	 * 
	 * [ExamenSessionBean.onRowCancel] Method
	 * 
	 * @author BELDI Jamel on : 30 sept. 2014 17:10:26
	 * @param event
	 */
	public void onRowCancel(RowEditEvent event) {

	}

	/**
	 * 
	 * [DeroulementInscriptionExamenBean.map] Method
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 17:50:01
	 * @param refIndividuDto
	 * @param dossierInscriptionAdministrativeDto
	 */
	private void map(RefIndividuDto refIndividuDto,
			InscriptionExamenDto inscriptionExamenDto) {
		if (inscriptionExamenDto == null) {
			inscriptionExamenDto = new InscriptionExamenDto();
		}
		inscriptionExamenDto.setNin(refIndividuDto.getIdentifiant());
		inscriptionExamenDto.setIndividuNomLatin(refIndividuDto.getNomLatin());
		inscriptionExamenDto.setIndividuNomArabe(refIndividuDto.getNomArabe());
		inscriptionExamenDto.setIndividuPrenomArabe(refIndividuDto
				.getPrenomArabe());
		inscriptionExamenDto.setIndividuPrenomLatin(refIndividuDto
				.getPrenomLatin());

		if (refIndividuDto.getDateNaissance() != null) {
			inscriptionExamenDto.setIndividuDateNaissance(refIndividuDto
					.getDateNaissance());
		}

	}

	// Getter And Setter

	/**
	 * [DeroulementInscriptionExamenBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [DeroulementInscriptionExamenBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [DeroulementInscriptionExamenBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.offreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.offreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.niveauService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.niveauService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param niveauService
	 *            the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.periodeService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.periodeService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param periodeService
	 *            the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [DeroulementInscriptionExamenBean.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [DeroulementInscriptionExamenBean.situationService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.situationService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.planningSessionService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the planningSessionService
	 */
	public PlanningSessionService getPlanningSessionService() {
		return planningSessionService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.planningSessionService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param planningSessionService
	 *            the planningSessionService to set
	 */
	public void setPlanningSessionService(
			PlanningSessionService planningSessionService) {
		this.planningSessionService = planningSessionService;
	}

	// /**
	// * [DeroulementInscriptionExamenBean.planningSessionSaveBean] Getter
	// *
	// * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	// * @return the planningSessionSaveBean
	// */
	// public PlanningSessionSaveBean getPlanningSessionSaveBean() {
	// return planningSessionSaveBean;
	// }
	//
	// /**
	// * [DeroulementInscriptionExamenBean.planningSessionSaveBean] Setter
	// *
	// * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	// * @param planningSessionSaveBean
	// * the planningSessionSaveBean to set
	// */
	// public void setPlanningSessionSaveBean(
	// PlanningSessionSaveBean planningSessionSaveBean) {
	// this.planningSessionSaveBean = planningSessionSaveBean;
	// }

	/**
	 * [DeroulementInscriptionExamenBean.examenSessionService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the examenSessionService
	 */
	public ExamenSessionService getExamenSessionService() {
		return examenSessionService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.examenSessionService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param examenSessionService
	 *            the examenSessionService to set
	 */
	public void setExamenSessionService(
			ExamenSessionService examenSessionService) {
		this.examenSessionService = examenSessionService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.inscriptionExamenService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the inscriptionExamenService
	 */
	public InscriptionExamenService getInscriptionExamenService() {
		return inscriptionExamenService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.inscriptionExamenService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param inscriptionExamenService
	 *            the inscriptionExamenService to set
	 */
	public void setInscriptionExamenService(
			InscriptionExamenService inscriptionExamenService) {
		this.inscriptionExamenService = inscriptionExamenService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.repartitionUeService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the repartitionUeService
	 */
	public RepartitionUeService getRepartitionUeService() {
		return repartitionUeService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.repartitionUeService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param repartitionUeService
	 *            the repartitionUeService to set
	 */
	public void setRepartitionUeService(
			RepartitionUeService repartitionUeService) {
		this.repartitionUeService = repartitionUeService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.rattachementMcService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the rattachementMcService
	 */
	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.rattachementMcService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param rattachementMcService
	 *            the rattachementMcService to set
	 */
	public void setRattachementMcService(
			RattachementMcService rattachementMcService) {
		this.rattachementMcService = rattachementMcService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.examensList] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the examensList
	 */
	public List<SelectItem> getExamensList() {
		return examensList;
	}

	/**
	 * [DeroulementInscriptionExamenBean.examensList] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param examensList
	 *            the examensList to set
	 */
	public void setExamensList(List<SelectItem> examensList) {
		this.examensList = examensList;
	}

	// /**
	// * [DeroulementInscriptionExamenBean.examenSessionDto] Getter
	// *
	// * @author BELDI Jamel on : 24 sept. 2014 14:56:53
	// * @return the examenSessionDto
	// */
	// public ExamenSessionDto getExamenSessionDto() {
	// return examenSessionDto;
	// }
	//
	// /**
	// * [DeroulementInscriptionExamenBean.examenSessionDto] Setter
	// *
	// * @author BELDI Jamel on : 24 sept. 2014 14:56:53
	// * @param examenSessionDto
	// * the examenSessionDto to set
	// */
	// public void setExamenSessionDto(ExamenSessionDto examenSessionDto) {
	// this.examenSessionDto = examenSessionDto;
	// }

	/**
	 * [DeroulementInscriptionExamenBean.parcoursIndividualiseService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 18:28:26
	 * @return the parcoursIndividualiseService
	 */
	public ParcoursIndividualiseService getParcoursIndividualiseService() {
		return parcoursIndividualiseService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.parcoursIndividualiseService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 18:28:26
	 * @param parcoursIndividualiseService
	 *            the parcoursIndividualiseService to set
	 */
	public void setParcoursIndividualiseService(
			ParcoursIndividualiseService parcoursIndividualiseService) {
		this.parcoursIndividualiseService = parcoursIndividualiseService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.inscriptionExamenList] Getter
	 * 
	 * @author BELDI Jamel on : 1 oct. 2014 10:06:12
	 * @return the inscriptionExamenList
	 */
	public List<InscriptionExamenDto> getInscriptionExamenList() {
		return inscriptionExamenList;
	}

	/**
	 * [DeroulementInscriptionExamenBean.inscriptionExamenList] Setter
	 * 
	 * @author BELDI Jamel on : 1 oct. 2014 10:06:12
	 * @param inscriptionExamenList
	 *            the inscriptionExamenList to set
	 */
	public void setInscriptionExamenList(
			List<InscriptionExamenDto> inscriptionExamenList) {
		this.inscriptionExamenList = inscriptionExamenList;
	}

	/**
	 * [DeroulementInscriptionExamenBean.salleExamenDto] Getter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 17:14:01
	 * @return the salleExamenDto
	 */
	public SalleExamenDto getSalleExamenDto() {
		return salleExamenDto;
	}

	/**
	 * [DeroulementInscriptionExamenBean.salleExamenDto] Setter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 17:14:01
	 * @param salleExamenDto
	 *            the salleExamenDto to set
	 */
	public void setSalleExamenDto(SalleExamenDto salleExamenDto) {
		this.salleExamenDto = salleExamenDto;
	}

	/**
	 * [DeroulementInscriptionExamenBean.salleExamenList] Getter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 17:14:42
	 * @return the salleExamenList
	 */
	public List<SelectItem> getSalleExamenList() {
		return salleExamenList;
	}

	/**
	 * [DeroulementInscriptionExamenBean.salleExamenList] Setter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 17:14:42
	 * @param salleExamenList
	 *            the salleExamenList to set
	 */
	public void setSalleExamenList(List<SelectItem> salleExamenList) {
		this.salleExamenList = salleExamenList;
	}

	/**
	 * [DeroulementInscriptionExamenBean.salleExamenService] Getter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 17:15:29
	 * @return the salleExamenService
	 */
	public SalleExamenService getSalleExamenService() {
		return salleExamenService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.salleExamenService] Setter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 17:15:29
	 * @param salleExamenService
	 *            the salleExamenService to set
	 */
	public void setSalleExamenService(SalleExamenService salleExamenService) {
		this.salleExamenService = salleExamenService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.refIndividuService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 08:22:12
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.refIndividuService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 08:22:12
	 * @param refIndividuService
	 *            the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [DeroulementInscriptionExamenBean.save] Method
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 16:08:24
	 */
	public void save() {
		try {
			if (inscriptionExamenList != null) {
				for (InscriptionExamenDto _inscriptionExamenDto : inscriptionExamenList) {
					if (_inscriptionExamenDto.getCopieNonRemise()
							&& _inscriptionExamenDto.getAbsent()) {
						Utility.showErrorMessage(examenBundle
								.getString("examen_warn_absence_copie_non_remise"));
						return;
					}
					inscriptionExamenService
							.insertOrUpdate(_inscriptionExamenDto);

				}
			}
			Utility.showSuccessSaveMessage();

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());

		}
	}
}
