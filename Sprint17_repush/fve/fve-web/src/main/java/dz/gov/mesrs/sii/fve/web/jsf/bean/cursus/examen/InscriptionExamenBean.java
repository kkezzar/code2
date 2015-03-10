package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.dozer.DozerBeanMapper;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.InscriptionExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.PlanningSessionDto;
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
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

/**
 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
 */
@ManagedBean(name = "inscriptionExamenBean")
@ViewScoped
public class InscriptionExamenBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:10
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
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
	@ManagedProperty("#{salleExamenService}")
	private SalleExamenService salleExamenService;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	private List<SelectItem> examensList;
	private List<SelectItem> salleExamenList;
	private SalleExamenDto salleExamenDto;
	private DualListModel<InscriptionExamenDto> inscriptionDualList;
	private Integer capacite;
	private Integer nbreInscrits;
	private Integer reste;
	private Integer nbreEtudiants;
	private Integer rattachementMcId;
	private PlanningSessionDto planningSessionDto;

	/**
	 * [InscriptionExamenBean.InscriptionExamenBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
	 */
	public InscriptionExamenBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		examenBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.EXAMEN_BUNDLE_MSG_NAME);

	}

	/**
	 * 
	 * [InscriptionExamenBean.init] Method
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:10:41
	 */
	@PostConstruct
	public void init() {
		salleExamenDto = new SalleExamenDto();
		List<InscriptionExamenDto> inscriptionsSource = new ArrayList<InscriptionExamenDto>();
		List<InscriptionExamenDto> inscriptionsTarget = new ArrayList<InscriptionExamenDto>();
		inscriptionDualList = new DualListModel<InscriptionExamenDto>(
				inscriptionsSource, inscriptionsTarget);
		capacite = null;
		nbreInscrits = null;
		reste = null;
		nbreEtudiants = null;
		if (planningSessionDto == null) {
			planningSessionDto = new PlanningSessionDto();
		}
	}

	/**
	 * 
	 * [InscriptionExamenBean.loadExamenList] Method
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:21:45
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
	 * [InscriptionExamenBean.examenChanged] Method
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 15:24:34
	 */
	public void examenChanged() {
		try {
			loadSalleExamenList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
					if (_salles.size() == 1) {
						salleExamenDto = _salles.get(0);
						rattachementMcId = salleExamenDto.getRattachementMcId();
					}
				}
			}
			// salleExamenDto.setId(null);
			salleChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * [InscriptionExamenBean.salleChanged] Method
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 18:08:03
	 */
	public void salleChanged() {
		loadInscriptionDualList();
	}

	/**
	 * 
	 * [InscriptionExamenBean.loadInscrits] Method
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 16:55:24
	 */
	public void loadInscriptionDualList() {

		try {
			capacite = null;
			nbreInscrits = null;
			reste = null;
			nbreEtudiants = null;
			List<InscriptionExamenDto> inscriptionsSource = new ArrayList<InscriptionExamenDto>();
			List<InscriptionExamenDto> inscriptionsTarget = new ArrayList<InscriptionExamenDto>();
			inscriptionDualList = new DualListModel<InscriptionExamenDto>(
					inscriptionsSource, inscriptionsTarget);

			if (salleExamenDto.getId() != null && salleExamenDto.getId() != 0) {
				salleExamenDto = salleExamenService.findById(salleExamenDto
						.getId());
				capacite = salleExamenDto.getRefLieuCapacite() == null ? null
						: salleExamenDto.getRefLieuCapacite().intValue();
				inscriptionsSource = loadInscriptionsSource();// Non inscrit
				nbreEtudiants = inscriptionsSource.size();
				inscriptionsTarget = loadInscriptionsTarget();// inscrits
				nbreInscrits = inscriptionsTarget.size();
				if (capacite != null && nbreInscrits != null) {
					reste = capacite - nbreInscrits;
				} else {
					reste = capacite;
				}

				inscriptionDualList = new DualListModel<InscriptionExamenDto>(
						inscriptionsSource, inscriptionsTarget);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * * [InscriptionExamenBean.loadInscriptionsSource] Method
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 17:27:49
	 * @return
	 */
	private List<InscriptionExamenDto> loadInscriptionsSource() {
		List<InscriptionExamenDto> inscriptionsSource = new ArrayList<InscriptionExamenDto>();
		try {
			List<InscriptionExamenDto> _inscriptionListe = null;
			NomenclatureDto _nctypeSessionRemplacement = utilBean
					.loadNcTypeSessionRemplacement();
			if (_nctypeSessionRemplacement != null
					&& salleExamenDto.getNcTypeSessionId() != null
					&& salleExamenDto.getNcTypeSessionId().equals(
							_nctypeSessionRemplacement.getId())) {
				/*****
				 * Session de remplacement : charger les etdudiant qui etaient
				 * absent avec justification
				 *****/
				_inscriptionListe = inscriptionExamenService
						.findAbsentByPlanningAndRattachementMc(
								salleExamenDto.getSessionToReplaceId(),
								rattachementMcId, true);

			} else {
				DossierInscriptionAdministrativeDto searchDto = new DossierInscriptionAdministrativeDto();
				searchDto.setOuvertureOffreFormationId(planningSessionDto
						.getOuvertureOffreFormationId());
				searchDto.setNiveauId(planningSessionDto.getNiveauId());
				SituationEntiteDto _situatioinValid = utilBean
						.loadDiaSituationValidate();
				if (_situatioinValid != null) {
					searchDto.setSituationId(_situatioinValid.getId());
				}
				_inscriptionListe = inscriptionExamenService
						.findAdvancedNotSubscribedOnExamen(
								salleExamenDto.getExamenSessionId(), searchDto);
			}

			if (_inscriptionListe != null && !_inscriptionListe.isEmpty()) {
				for (InscriptionExamenDto inscriptionExamenDto : _inscriptionListe) {
					// Individu service
					RefIndividuDto _refIndividuDto = refIndividuService
							.findByIdentifiant(inscriptionExamenDto.getNin());// TODO
																				// change
																				// physique
																				// model
					// replace with service
					if (_refIndividuDto != null) {
						inscriptionExamenDto.setSalleExamenId(salleExamenDto
								.getId());
						map(_refIndividuDto, inscriptionExamenDto);
						inscriptionsSource.add(inscriptionExamenDto);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			inscriptionsSource = new ArrayList<InscriptionExamenDto>();
		}
		return inscriptionsSource;
	}

	/**
	 * 
	 * [InscriptionExamenBean.loadInscriptionsTarget] Method
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 17:44:15
	 * @return
	 */
	private List<InscriptionExamenDto> loadInscriptionsTarget() {
		List<InscriptionExamenDto> inscriptionsTarget = new ArrayList<InscriptionExamenDto>();
		try {
			List<InscriptionExamenDto> _inscriptionListe = inscriptionExamenService
					.findBySalleExamen(salleExamenDto.getId());
			if (_inscriptionListe != null && !_inscriptionListe.isEmpty()) {
				for (InscriptionExamenDto inscriptionExamenDto : _inscriptionListe) {
					// Individu service
					RefIndividuDto _refIndividuDto = refIndividuService
							.findByIdentifiant(inscriptionExamenDto.getNin());
					if (_refIndividuDto != null) {
						map(_refIndividuDto, inscriptionExamenDto);
						inscriptionsTarget.add(inscriptionExamenDto);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			inscriptionsTarget = new ArrayList<InscriptionExamenDto>();
		}
		return inscriptionsTarget;
	}

	/**
	 * 
	 * [InscriptionExamenBean.onTransfer] Method
	 * 
	 * @author BELDI Jamel on : 25 sept. 2014 15:59:40
	 * @param event
	 */
	public void onTransfer(TransferEvent event) {
		try {
			List<InscriptionExamenDto> items = (List<InscriptionExamenDto>) event
					.getItems();
			if (event.isAdd()) {
				int nbreElements = items.size();
				if (reste != null && nbreElements <= reste) {
					for (InscriptionExamenDto inscriptionExamenDto : items) {
						List<InscriptionExamenDto> target = inscriptionDualList
								.getTarget();
						int index = target.indexOf(inscriptionExamenDto);
						mapper.map(salleExamenDto, inscriptionExamenDto);
						target.set(index, inscriptionExamenDto);
					}
					reste = reste - nbreElements;
					nbreEtudiants = nbreEtudiants - nbreElements;
					nbreInscrits = nbreInscrits + nbreElements;
				} else {
					List<InscriptionExamenDto> target = inscriptionDualList
							.getTarget();
					target.removeAll(items);
					List<InscriptionExamenDto> sources = inscriptionDualList
							.getSource();
					sources.addAll(items);
					Utility.showErrorMessage(examenBundle
							.getString("examen_error_capcite_salle"));
				}
			}
			if (event.isRemove() && reste != null) {
				int nbreElements = items.size();
				reste = reste + nbreElements;
				nbreEtudiants = nbreEtudiants + nbreElements;
				nbreInscrits = nbreInscrits - nbreElements;

			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 
	 * [InscriptionExamenBean.save] Method
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:27:59
	 */
	public void save() {
		try {

			if (inscriptionDualList == null) {
				return;
			} else {
				List<InscriptionExamenDto> inscriptionsTarget = inscriptionDualList
						.getTarget();

				List<InscriptionExamenDto> inscriptionsSource = inscriptionDualList
						.getSource();
				if (inscriptionsTarget != null && !inscriptionsTarget.isEmpty()) {
					for (InscriptionExamenDto inscriptionExamenDto : inscriptionsTarget) {
						if (inscriptionExamenDto.getId() == 0) {
							inscriptionExamenService
									.insertOrUpdate(inscriptionExamenDto);
						}

					}
				}
				if (inscriptionsSource != null && !inscriptionsSource.isEmpty()) {
					for (InscriptionExamenDto inscriptionExamenDto : inscriptionsSource) {
						if (inscriptionExamenDto.getId() != 0) {
							inscriptionExamenService
									.remove(inscriptionExamenDto);
						}
					}
				}
			}
			Utility.showSuccessSaveMessage();

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());

		}
	}

	/**
	 * 
	 * [InscriptionExamenBean.map] Method
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
	 * [InscriptionExamenBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [InscriptionExamenBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [InscriptionExamenBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [InscriptionExamenBean.ouvertureOffreFormationService] Setter
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
	 * [InscriptionExamenBean.offreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [InscriptionExamenBean.offreFormationService] Setter
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
	 * [InscriptionExamenBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [InscriptionExamenBean.anneeAcademiqueService] Setter
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
	 * [InscriptionExamenBean.niveauService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [InscriptionExamenBean.niveauService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param niveauService
	 *            the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [InscriptionExamenBean.periodeService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [InscriptionExamenBean.periodeService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param periodeService
	 *            the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [InscriptionExamenBean.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [InscriptionExamenBean.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [InscriptionExamenBean.situationService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [InscriptionExamenBean.situationService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [InscriptionExamenBean.planningSessionService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the planningSessionService
	 */
	public PlanningSessionService getPlanningSessionService() {
		return planningSessionService;
	}

	/**
	 * [InscriptionExamenBean.planningSessionService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param planningSessionService
	 *            the planningSessionService to set
	 */
	public void setPlanningSessionService(
			PlanningSessionService planningSessionService) {
		this.planningSessionService = planningSessionService;
	}

	/**
	 * [InscriptionExamenBean.examenSessionService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the examenSessionService
	 */
	public ExamenSessionService getExamenSessionService() {
		return examenSessionService;
	}

	/**
	 * [InscriptionExamenBean.examenSessionService] Setter
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
	 * [InscriptionExamenBean.inscriptionExamenService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the inscriptionExamenService
	 */
	public InscriptionExamenService getInscriptionExamenService() {
		return inscriptionExamenService;
	}

	/**
	 * [InscriptionExamenBean.inscriptionExamenService] Setter
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
	 * [InscriptionExamenBean.repartitionUeService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the repartitionUeService
	 */
	public RepartitionUeService getRepartitionUeService() {
		return repartitionUeService;
	}

	/**
	 * [InscriptionExamenBean.repartitionUeService] Setter
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
	 * [InscriptionExamenBean.rattachementMcService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the rattachementMcService
	 */
	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	/**
	 * [InscriptionExamenBean.rattachementMcService] Setter
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
	 * [InscriptionExamenBean.examensList] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the examensList
	 */
	public List<SelectItem> getExamensList() {
		return examensList;
	}

	/**
	 * [InscriptionExamenBean.examensList] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param examensList
	 *            the examensList to set
	 */
	public void setExamensList(List<SelectItem> examensList) {
		this.examensList = examensList;
	}

	/**
	 * [InscriptionExamenBean.inscriptionDualList] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 16:17:08
	 * @return the inscriptionDualList
	 */
	public DualListModel<InscriptionExamenDto> getInscriptionDualList() {
		return inscriptionDualList;
	}

	/**
	 * [InscriptionExamenBean.inscriptionDualList] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 16:17:08
	 * @param inscriptionDualList
	 *            the inscriptionDualList to set
	 */
	public void setInscriptionDualList(
			DualListModel<InscriptionExamenDto> inscriptionDualList) {
		this.inscriptionDualList = inscriptionDualList;
	}

	/**
	 * [InscriptionExamenBean.parcoursIndividualiseService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 18:28:26
	 * @return the parcoursIndividualiseService
	 */
	public ParcoursIndividualiseService getParcoursIndividualiseService() {
		return parcoursIndividualiseService;
	}

	/**
	 * [InscriptionExamenBean.parcoursIndividualiseService] Setter
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
	 * [InscriptionExamenBean.salleExamenList] Getter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:40:38
	 * @return the salleExamenList
	 */
	public List<SelectItem> getSalleExamenList() {
		return salleExamenList;
	}

	/**
	 * [InscriptionExamenBean.salleExamenList] Setter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:40:38
	 * @param salleExamenList
	 *            the salleExamenList to set
	 */
	public void setSalleExamenList(List<SelectItem> salleExamenList) {
		this.salleExamenList = salleExamenList;
	}

	/**
	 * [InscriptionExamenBean.salleExamenDto] Getter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:42:51
	 * @return the salleExamenDto
	 */
	public SalleExamenDto getSalleExamenDto() {
		return salleExamenDto;
	}

	/**
	 * [InscriptionExamenBean.salleExamenDto] Setter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:42:51
	 * @param salleExamenDto
	 *            the salleExamenDto to set
	 */
	public void setSalleExamenDto(SalleExamenDto salleExamenDto) {
		this.salleExamenDto = salleExamenDto;
	}

	/**
	 * [InscriptionExamenBean.salleExamenService] Getter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:45:04
	 * @return the salleExamenService
	 */
	public SalleExamenService getSalleExamenService() {
		return salleExamenService;
	}

	/**
	 * [InscriptionExamenBean.salleExamenService] Setter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 17:45:04
	 * @param salleExamenService
	 *            the salleExamenService to set
	 */
	public void setSalleExamenService(SalleExamenService salleExamenService) {
		this.salleExamenService = salleExamenService;
	}

	/**
	 * [InscriptionExamenBean.capacite] Getter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 11:03:08
	 * @return the capacite
	 */
	public Integer getCapacite() {
		return capacite;
	}

	/**
	 * [InscriptionExamenBean.capacite] Setter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 11:03:08
	 * @param capacite
	 *            the capacite to set
	 */
	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}

	/**
	 * [InscriptionExamenBean.nbreInscrits] Getter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 11:03:08
	 * @return the nbreInscrits
	 */
	public Integer getNbreInscrits() {
		return nbreInscrits;
	}

	/**
	 * [InscriptionExamenBean.nbreInscrits] Setter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 11:03:08
	 * @param nbreInscrits
	 *            the nbreInscrits to set
	 */
	public void setNbreInscrits(Integer nbreInscrits) {
		this.nbreInscrits = nbreInscrits;
	}

	/**
	 * [InscriptionExamenBean.reste] Getter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 11:03:08
	 * @return the reste
	 */
	public Integer getReste() {
		return reste;
	}

	/**
	 * [InscriptionExamenBean.reste] Setter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 11:03:08
	 * @param reste
	 *            the reste to set
	 */
	public void setReste(Integer reste) {
		this.reste = reste;
	}

	/**
	 * [InscriptionExamenBean.nbreEtudiants] Getter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 11:10:31
	 * @return the nbreEtudiants
	 */
	public Integer getNbreEtudiants() {
		return nbreEtudiants;
	}

	/**
	 * [InscriptionExamenBean.nbreEtudiants] Setter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 11:10:31
	 * @param nbreEtudiants
	 *            the nbreEtudiants to set
	 */
	public void setNbreEtudiants(Integer nbreEtudiants) {
		this.nbreEtudiants = nbreEtudiants;
	}

	/**
	 * [InscriptionExamenBean.refIndividuService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 08:18:30
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [InscriptionExamenBean.refIndividuService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 08:18:30
	 * @param refIndividuService
	 *            the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [InscriptionExamenBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 09:48:57
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [InscriptionExamenBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 09:48:57
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [InscriptionExamenBean.rattachementMcId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 14:54:29
	 * @return the rattachementMcId
	 */
	public Integer getRattachementMcId() {
		return rattachementMcId;
	}

	/**
	 * [InscriptionExamenBean.rattachementMcId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 14:54:29
	 * @param rattachementMcId
	 *            the rattachementMcId to set
	 */
	public void setRattachementMcId(Integer rattachementMcId) {
		this.rattachementMcId = rattachementMcId;
	}

	/**
	 * [InscriptionExamenBean.planningSessionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 déc. 2014 16:04:54
	 * @return the planningSessionDto
	 */
	public PlanningSessionDto getPlanningSessionDto() {
		return planningSessionDto;
	}

	/**
	 * [InscriptionExamenBean.planningSessionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 déc. 2014 16:04:54
	 * @param planningSessionDto
	 *            the planningSessionDto to set
	 */
	public void setPlanningSessionDto(PlanningSessionDto planningSessionDto) {
		this.planningSessionDto = planningSessionDto;
	}

}
