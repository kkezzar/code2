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

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ResponsableExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.SalleExamenDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.ExamenSessionService;
import dz.gov.mesrs.sii.fve.business.service.cursus.ParcoursIndividualiseService;
import dz.gov.mesrs.sii.fve.business.service.examen.PlanningSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.ResponsableExamenService;
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
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;

/**
 * 
 * @author BELDI Jamel on : 16 oct. 2014 15:30:45
 */
@ManagedBean(name = "responsableExamenBean")
@ViewScoped
public class ResponsableExamenBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:10
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
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
	@ManagedProperty("#{responsableExamenService}")
	private ResponsableExamenService responsableExamenService;
	@ManagedProperty("#{repartitionUeService}")
	private RepartitionUeService repartitionUeService;
	@ManagedProperty("#{rattachementMcService}")
	private RattachementMcService rattachementMcService;
	@ManagedProperty("#{parcoursIndividualiseService}")
	private ParcoursIndividualiseService parcoursIndividualiseService;
	@ManagedProperty("#{salleExamenService}")
	private SalleExamenService salleExamenService;
	private List<SelectItem> examensList;
	private List<SelectItem> salleExamenList;
	private SalleExamenDto salleExamenDto;
	private DualListModel<ResponsableExamenDto> responsableDualList;


	/**
	 * 
	 * [ResponsableExamenBean.ResponsableExamenBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 15:30:52
	 */
	public ResponsableExamenBean() {
		super();
	}

	/**
	 * 
	 * [ResponsableExamenBean.init] Method
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:15:26
	 */
	@PostConstruct
	public void init() {
		salleExamenDto = new SalleExamenDto();
		List<ResponsableExamenDto> responsablesSource = new ArrayList<ResponsableExamenDto>();
		List<ResponsableExamenDto> responsablesTarget = new ArrayList<ResponsableExamenDto>();
		responsableDualList = new DualListModel<ResponsableExamenDto>(
				responsablesSource, responsablesTarget);
	}

	/**
	 * 
	 * [ResponsableExamenBean.loadExamenList] Method
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:26:17
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
	 * [ResponsableExamenBean.examenChanged] Method
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:15:37
	 */
	public void examenChanged() {
		try {
			loadSalleExamenList();
		} catch (Exception e) {

		}
	}

	/**
	 * 
	 * [ResponsableExamenBean.loadSalleExamenList] Method
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:15:41
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
					if (_salles.size() == 1) {
						salleExamenDto = _salles.get(0);
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
	 * [ResponsableExamenBean.salleChanged] Method
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:15:46
	 */
	public void salleChanged() {
		loadResponsableDualList();
	}

	/**
	 * 
	 * [ResponsableExamenBean.loadResponsableDualList] Method
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:15:50
	 */
	public void loadResponsableDualList() {

		try {
			List<ResponsableExamenDto> responsablesSource = new ArrayList<ResponsableExamenDto>();
			List<ResponsableExamenDto> responsablesTarget = new ArrayList<ResponsableExamenDto>();
			responsableDualList = new DualListModel<ResponsableExamenDto>(
					responsablesSource, responsablesTarget);

			if (salleExamenDto.getId() != null && salleExamenDto.getId() != 0) {
				salleExamenDto = salleExamenService.findById(salleExamenDto
						.getId());
				responsablesSource = loadResponsablesSource();
				responsablesTarget = loadResponsablesTarget();
				responsableDualList = new DualListModel<ResponsableExamenDto>(
						responsablesSource, responsablesTarget);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [ResponsableExamenBean.loadResponsablesSource] Method
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:15:55
	 * @return
	 */
	private List<ResponsableExamenDto> loadResponsablesSource() {
		List<ResponsableExamenDto> responsablesSource = new ArrayList<ResponsableExamenDto>();
		try {
			NomenclatureDto _ncRole =  utilBean.findNcRoleEnseignant();
			if(_ncRole == null) {

				return null;
			}
			responsablesSource = responsableExamenService
					.findNotSubscribedOnExamen(
							salleExamenDto.getExamenSessionId(),
							sessionBean.getIdEtablissement(), _ncRole.getId());

		} catch (Exception e) {
			e.printStackTrace();
			responsablesSource = new ArrayList<ResponsableExamenDto>();
		}
		return responsablesSource;
	}

	/**
	 * 
	 * [ResponsableExamenBean.loadResponsablesTarget] Method
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:15:59
	 * @return
	 */
	private List<ResponsableExamenDto> loadResponsablesTarget() {
		List<ResponsableExamenDto> responsableTarget = new ArrayList<ResponsableExamenDto>();
		try {
			responsableTarget = responsableExamenService
					.findBySalleExamen(salleExamenDto.getId());

		} catch (Exception e) {
			e.printStackTrace();
			responsableTarget = new ArrayList<ResponsableExamenDto>();
		}
		return responsableTarget;
	}

	/**
	 * 
	 * [ResponsableExamenBean.onTransfer] Method
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:05
	 * @param event
	 */
	public void onTransfer(TransferEvent event) {
		try {
			if (event.isAdd()) {
				List<ResponsableExamenDto> items = (List<ResponsableExamenDto>) event
						.getItems();
				for (ResponsableExamenDto responsableExamenDto : items) {
					List target = responsableDualList.getTarget();

					int index = target.indexOf(responsableExamenDto);
					mapper.map(salleExamenDto, responsableExamenDto);
					target.set(index, responsableExamenDto);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 
	 * [ResponsableExamenBean.save] Method
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:09
	 */
	public void save() {
		try {

			if (responsableDualList == null) {
				return;
			} else {
				List<ResponsableExamenDto> responsablesTarget = responsableDualList
						.getTarget();

				List<ResponsableExamenDto> responsablesSource = responsableDualList
						.getSource();
				if (responsablesTarget != null && !responsablesTarget.isEmpty()) {
					for (ResponsableExamenDto responsableExamenDto : responsablesTarget) {
						if (responsableExamenDto.getId() == 0) {
							responsableExamenService
									.insertOrUpdate(responsableExamenDto);
						}

					}
				}
				if (responsablesSource != null && !responsablesSource.isEmpty()) {
					for (ResponsableExamenDto responsableExamenDto : responsablesSource) {
						if (responsableExamenDto.getId() != 0) {
							responsableExamenService
									.remove(responsableExamenDto);
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

	// Getter And Setter
	/**
	 * [ResponsableExamenBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [ResponsableExamenBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [ResponsableExamenBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [ResponsableExamenBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [ResponsableExamenBean.offreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [ResponsableExamenBean.offreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [ResponsableExamenBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [ResponsableExamenBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [ResponsableExamenBean.niveauService] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [ResponsableExamenBean.niveauService] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param niveauService
	 *            the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [ResponsableExamenBean.periodeService] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [ResponsableExamenBean.periodeService] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param periodeService
	 *            the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [ResponsableExamenBean.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [ResponsableExamenBean.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [ResponsableExamenBean.situationService] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [ResponsableExamenBean.situationService] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [ResponsableExamenBean.planningSessionService] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the planningSessionService
	 */
	public PlanningSessionService getPlanningSessionService() {
		return planningSessionService;
	}

	/**
	 * [ResponsableExamenBean.planningSessionService] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param planningSessionService
	 *            the planningSessionService to set
	 */
	public void setPlanningSessionService(
			PlanningSessionService planningSessionService) {
		this.planningSessionService = planningSessionService;
	}

	// /**
	// * [ResponsableExamenBean.planningSessionSaveBean] Getter
	// * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	// * @return the planningSessionSaveBean
	// */
	// public PlanningSessionSaveBean getPlanningSessionSaveBean() {
	// return planningSessionSaveBean;
	// }
	//
	// /**
	// * [ResponsableExamenBean.planningSessionSaveBean] Setter
	// * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	// * @param planningSessionSaveBean the planningSessionSaveBean to set
	// */
	// public void setPlanningSessionSaveBean(
	// PlanningSessionSaveBean planningSessionSaveBean) {
	// this.planningSessionSaveBean = planningSessionSaveBean;
	// }

	/**
	 * [ResponsableExamenBean.examenSessionService] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the examenSessionService
	 */
	public ExamenSessionService getExamenSessionService() {
		return examenSessionService;
	}

	/**
	 * [ResponsableExamenBean.examenSessionService] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param examenSessionService
	 *            the examenSessionService to set
	 */
	public void setExamenSessionService(
			ExamenSessionService examenSessionService) {
		this.examenSessionService = examenSessionService;
	}

	/**
	 * [ResponsableExamenBean.responsableExamenService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 09:25:17
	 * @return the responsableExamenService
	 */
	public ResponsableExamenService getResponsableExamenService() {
		return responsableExamenService;
	}

	/**
	 * [ResponsableExamenBean.responsableExamenService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 09:25:17
	 * @param responsableExamenService
	 *            the responsableExamenService to set
	 */
	public void setResponsableExamenService(
			ResponsableExamenService responsableExamenService) {
		this.responsableExamenService = responsableExamenService;
	}

	/**
	 * [ResponsableExamenBean.repartitionUeService] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the repartitionUeService
	 */
	public RepartitionUeService getRepartitionUeService() {
		return repartitionUeService;
	}

	/**
	 * [ResponsableExamenBean.repartitionUeService] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param repartitionUeService
	 *            the repartitionUeService to set
	 */
	public void setRepartitionUeService(
			RepartitionUeService repartitionUeService) {
		this.repartitionUeService = repartitionUeService;
	}

	/**
	 * [ResponsableExamenBean.rattachementMcService] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the rattachementMcService
	 */
	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	/**
	 * [ResponsableExamenBean.rattachementMcService] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param rattachementMcService
	 *            the rattachementMcService to set
	 */
	public void setRattachementMcService(
			RattachementMcService rattachementMcService) {
		this.rattachementMcService = rattachementMcService;
	}

	/**
	 * [ResponsableExamenBean.parcoursIndividualiseService] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the parcoursIndividualiseService
	 */
	public ParcoursIndividualiseService getParcoursIndividualiseService() {
		return parcoursIndividualiseService;
	}

	/**
	 * [ResponsableExamenBean.parcoursIndividualiseService] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param parcoursIndividualiseService
	 *            the parcoursIndividualiseService to set
	 */
	public void setParcoursIndividualiseService(
			ParcoursIndividualiseService parcoursIndividualiseService) {
		this.parcoursIndividualiseService = parcoursIndividualiseService;
	}

	/**
	 * [ResponsableExamenBean.salleExamenService] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the salleExamenService
	 */
	public SalleExamenService getSalleExamenService() {
		return salleExamenService;
	}

	/**
	 * [ResponsableExamenBean.salleExamenService] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param salleExamenService
	 *            the salleExamenService to set
	 */
	public void setSalleExamenService(SalleExamenService salleExamenService) {
		this.salleExamenService = salleExamenService;
	}

	/**
	 * [ResponsableExamenBean.examensList] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the examensList
	 */
	public List<SelectItem> getExamensList() {
		return examensList;
	}

	/**
	 * [ResponsableExamenBean.examensList] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param examensList
	 *            the examensList to set
	 */
	public void setExamensList(List<SelectItem> examensList) {
		this.examensList = examensList;
	}

	/**
	 * [ResponsableExamenBean.salleExamenList] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the salleExamenList
	 */
	public List<SelectItem> getSalleExamenList() {
		return salleExamenList;
	}

	/**
	 * [ResponsableExamenBean.salleExamenList] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param salleExamenList
	 *            the salleExamenList to set
	 */
	public void setSalleExamenList(List<SelectItem> salleExamenList) {
		this.salleExamenList = salleExamenList;
	}

	/**
	 * [ResponsableExamenBean.salleExamenDto] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the salleExamenDto
	 */
	public SalleExamenDto getSalleExamenDto() {
		return salleExamenDto;
	}

	/**
	 * [ResponsableExamenBean.salleExamenDto] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param salleExamenDto
	 *            the salleExamenDto to set
	 */
	public void setSalleExamenDto(SalleExamenDto salleExamenDto) {
		this.salleExamenDto = salleExamenDto;
	}

	/**
	 * [ResponsableExamenBean.responsableDualList] Getter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @return the responsableDualList
	 */
	public DualListModel<ResponsableExamenDto> getResponsableDualList() {
		return responsableDualList;
	}

	/**
	 * [ResponsableExamenBean.responsableDualList] Setter
	 * 
	 * @author BELDI Jamel on : 16 oct. 2014 16:16:47
	 * @param responsableDualList
	 *            the responsableDualList to set
	 */
	public void setResponsableDualList(
			DualListModel<ResponsableExamenDto> responsableDualList) {
		this.responsableDualList = responsableDualList;
	}



	/**
	 * [ResponsableExamenBean.utilBean] Getter 
	 * @author MAKERRI Sofiane on : 1 févr. 2015  11:26:11
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [ResponsableExamenBean.utilBean] Setter 
	 * @author MAKERRI Sofiane on : 1 févr. 2015  11:26:11
	 * @param utilBean the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}
	
	

}
