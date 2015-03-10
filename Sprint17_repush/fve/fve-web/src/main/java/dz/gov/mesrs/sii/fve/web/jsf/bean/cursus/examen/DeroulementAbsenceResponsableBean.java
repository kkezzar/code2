package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.dozer.DozerBeanMapper;
import org.primefaces.event.RowEditEvent;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ResponsableExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.SalleExamenDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.ExamenSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.PlanningSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.ResponsableExamenService;
import dz.gov.mesrs.sii.fve.business.service.examen.SalleExamenService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;

/**
 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
 */
@ManagedBean(name = "deroulementAbsenceResponsableBean")
@ViewScoped
public class DeroulementAbsenceResponsableBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:10
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
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
	@ManagedProperty("#{responsableExamenService}")
	private ResponsableExamenService responsableExamenService;
	private List<SelectItem> examensList;
	// private ExamenSessionDto examenSessionDto;
	private List<ResponsableExamenDto> absenceResponsableList;
	private SalleExamenDto salleExamenDto;
	private List<SelectItem> salleExamenList;
	@ManagedProperty("#{salleExamenService}")
	private SalleExamenService salleExamenService;

	/**
	 * [DeroulementAbsenceResponsableBean.DeroulementAbsenceResponsableBean()]
	 * Constructor
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
	 */
	public DeroulementAbsenceResponsableBean() {
		super();

	}

	/**
	 * 
	 * [DeroulementAbsenceResponsableBean.init] Method
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:10:41
	 */
	@PostConstruct
	public void init() {
		// examenSessionDto = new ExamenSessionDto();
		salleExamenDto = new SalleExamenDto();
		// loadExamenList();
		// loadResponsableExamenList();
	}

	/**
	 * 
	 * [DeroulementAbsenceResponsableBean.loadExamenList] Method
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:43:30
	 * @param idPlanningSession
	 */
	public void loadExamenList(long idPlanningSession) {
		examensList = new ArrayList<SelectItem>();
		try {
			// List<ExamenSessionDto> _examens = planningSessionSaveBean
			// .getExamensSessionList();
			List<ExamenSessionDto> _examens = examenSessionService.findBySession(idPlanningSession);
			if (_examens != null && !_examens.isEmpty()) {
				for (ExamenSessionDto examenSessionDto : _examens) {
					SelectItem selectItem = new SelectItem(examenSessionDto.getId(), examenSessionDto.getMcLibelleFr()
					        + "(" + examenSessionDto.getRefTypeExamenLibelle() + ")");
					examensList.add(selectItem);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * [DeroulementAbsenceResponsableBean.examenChanged] Method
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
	 * [DeroulementAbsenceResponsableBean.loadSalleExamenList] Method
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 18:04:08
	 */
	private void loadSalleExamenList() {
		salleExamenList = new ArrayList<SelectItem>();
		try {
			if (salleExamenDto != null && salleExamenDto.getExamenSessionId() != null
			        && salleExamenDto.getExamenSessionId() != 0) {
				List<SalleExamenDto> _salles = salleExamenService.findByExamen(salleExamenDto.getExamenSessionId());
				if (_salles != null && !_salles.isEmpty()) {
					for (SalleExamenDto salleExamenDto : _salles) {
						SelectItem selectItem = new SelectItem(salleExamenDto.getId(),
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

	public void salleChanged() {
		loadResponsableExamenList();
	}

	/**
	 * 
	 * [DeroulementAbsenceResponsableBean.loadResponsableExamenList] Method
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:43:37
	 */
	public void loadResponsableExamenList() {

		try {

			absenceResponsableList = new ArrayList<ResponsableExamenDto>();
			if (salleExamenDto.getExamenSessionId() != null && salleExamenDto.getExamenSessionId() != 0) {
				// examenSessionDto =
				// examenSessionService.findById(examenSessionDto.getId());
				if (salleExamenDto.getId() != null && salleExamenDto.getId() != null && salleExamenDto.getId() != 0) {
					absenceResponsableList = responsableExamenService.findBySalleExamen(salleExamenDto.getId());
				} else {
					absenceResponsableList = responsableExamenService.findByExamen(salleExamenDto.getExamenSessionId());
				}

			}

		} catch (Exception e) {
			absenceResponsableList = new ArrayList<ResponsableExamenDto>();
			e.printStackTrace();
		}
	}

	/**
	 * [DeroulementAbsenceResponsableBean.save] Method
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 16:22:13
	 */
	public void save() {
		try {
			if (absenceResponsableList != null) {
				for (ResponsableExamenDto _absenceResponsableDto : absenceResponsableList) {
					responsableExamenService.insertOrUpdate(_absenceResponsableDto);
				}
			}
			Utility.showSuccessSaveMessage();

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());

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

	// Getter And Setter

	/**
	 * [DeroulementAbsenceResponsableBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.offreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.offreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.niveauService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.niveauService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param niveauService
	 *            the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.periodeService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.periodeService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param periodeService
	 *            the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.situationService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.situationService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.planningSessionService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the planningSessionService
	 */
	public PlanningSessionService getPlanningSessionService() {
		return planningSessionService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.planningSessionService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param planningSessionService
	 *            the planningSessionService to set
	 */
	public void setPlanningSessionService(PlanningSessionService planningSessionService) {
		this.planningSessionService = planningSessionService;
	}

	// /**
	// * [DeroulementAbsenceResponsableBean.planningSessionSaveBean] Getter
	// *
	// * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	// * @return the planningSessionSaveBean
	// */
	// public PlanningSessionSaveBean getPlanningSessionSaveBean() {
	// return planningSessionSaveBean;
	// }
	//
	// /**
	// * [DeroulementAbsenceResponsableBean.planningSessionSaveBean] Setter
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
	 * [DeroulementAbsenceResponsableBean.examenSessionService] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the examenSessionService
	 */
	public ExamenSessionService getExamenSessionService() {
		return examenSessionService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.examenSessionService] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param examenSessionService
	 *            the examenSessionService to set
	 */
	public void setExamenSessionService(ExamenSessionService examenSessionService) {
		this.examenSessionService = examenSessionService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.responsableExamenService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 09:25:59
	 * @return the responsableExamenService
	 */
	public ResponsableExamenService getResponsableExamenService() {
		return responsableExamenService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.responsableExamenService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 09:25:59
	 * @param responsableExamenService
	 *            the responsableExamenService to set
	 */
	public void setResponsableExamenService(ResponsableExamenService responsableExamenService) {
		this.responsableExamenService = responsableExamenService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.examensList] Getter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @return the examensList
	 */
	public List<SelectItem> getExamensList() {
		return examensList;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.examensList] Setter
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:09:20
	 * @param examensList
	 *            the examensList to set
	 */
	public void setExamensList(List<SelectItem> examensList) {
		this.examensList = examensList;
	}

	// /**
	// * [DeroulementAbsenceResponsableBean.examenSessionDto] Getter
	// *
	// * @author BELDI Jamel on : 24 sept. 2014 14:56:53
	// * @return the examenSessionDto
	// */
	// public ExamenSessionDto getExamenSessionDto() {
	// return examenSessionDto;
	// }
	//
	// /**
	// * [DeroulementAbsenceResponsableBean.examenSessionDto] Setter
	// *
	// * @author BELDI Jamel on : 24 sept. 2014 14:56:53
	// * @param examenSessionDto
	// * the examenSessionDto to set
	// */
	// public void setExamenSessionDto(ExamenSessionDto examenSessionDto) {
	// this.examenSessionDto = examenSessionDto;
	// }

	/**
	 * [DeroulementAbsenceResponsableBean.absenceResponsableList] Getter
	 * 
	 * @author BELDI Jamel on : 1 oct. 2014 17:40:05
	 * @return the absenceResponsableList
	 */
	public List<ResponsableExamenDto> getAbsenceResponsableList() {
		return absenceResponsableList;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.absenceResponsableList] Setter
	 * 
	 * @author BELDI Jamel on : 1 oct. 2014 17:40:05
	 * @param absenceResponsableList
	 *            the absenceResponsableList to set
	 */
	public void setAbsenceResponsableList(List<ResponsableExamenDto> absenceResponsableList) {
		this.absenceResponsableList = absenceResponsableList;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.salleExamenDto] Getter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 18:01:55
	 * @return the salleExamenDto
	 */
	public SalleExamenDto getSalleExamenDto() {
		return salleExamenDto;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.salleExamenDto] Setter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 18:01:55
	 * @param salleExamenDto
	 *            the salleExamenDto to set
	 */
	public void setSalleExamenDto(SalleExamenDto salleExamenDto) {
		this.salleExamenDto = salleExamenDto;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.salleExamenList] Getter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 18:01:55
	 * @return the salleExamenList
	 */
	public List<SelectItem> getSalleExamenList() {
		return salleExamenList;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.salleExamenList] Setter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 18:01:55
	 * @param salleExamenList
	 *            the salleExamenList to set
	 */
	public void setSalleExamenList(List<SelectItem> salleExamenList) {
		this.salleExamenList = salleExamenList;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.salleExamenService] Getter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 18:01:55
	 * @return the salleExamenService
	 */
	public SalleExamenService getSalleExamenService() {
		return salleExamenService;
	}

	/**
	 * [DeroulementAbsenceResponsableBean.salleExamenService] Setter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 18:01:55
	 * @param salleExamenService
	 *            the salleExamenService to set
	 */
	public void setSalleExamenService(SalleExamenService salleExamenService) {
		this.salleExamenService = salleExamenService;
	}

}
