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

import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.InscriptionExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ResponsableExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.SalleExamenDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.ExamenSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.InscriptionExamenService;
import dz.gov.mesrs.sii.fve.business.service.examen.ResponsableExamenService;
import dz.gov.mesrs.sii.fve.business.service.examen.SalleExamenService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefLieuService;

/**
 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
 */
@ManagedBean(name = "salleExamenBean")
@ViewScoped
public class SalleExamenBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:10
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;
	@ManagedProperty("#{salleExamenService}")
	private SalleExamenService salleExamenService;
	@ManagedProperty("#{refLieuServiceImpl}")
	private RefLieuService refLieuService;
	private List<SelectItem> examensList;
	private SalleExamenDto salleExamenDto;
	private ExamenSessionDto examenSessionDto;
	private DualListModel<SalleExamenDto> salleExamenDualList;
	@ManagedProperty("#{examenSessionService}")
	private ExamenSessionService examenSessionService;
	@ManagedProperty("#{inscriptionExamenBean}")
	private InscriptionExamenBean inscriptionExamenBean;
	@ManagedProperty("#{responsableExamenBean}")
	private ResponsableExamenBean responsableExamenBean;
	@ManagedProperty("#{inscriptionExamenService}")
	private InscriptionExamenService inscriptionExamenService;
	@ManagedProperty("#{responsableExamenService}")
	private ResponsableExamenService responsableExamenService;
	private final ResourceBundle examenBundle;

	/**
	 * [SalleExamenBean.SalleExamenBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
	 */
	public SalleExamenBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		examenBundle = facesContext.getApplication().getResourceBundle(facesContext,
		        CursusConstants.EXAMEN_BUNDLE_MSG_NAME);

	}

	/**
	 * 
	 * [SalleExamenBean.init] Method
	 * 
	 * @author BELDI Jamel on : 22 sept. 2014 11:10:41
	 */
	@PostConstruct
	public void init() {
		examenSessionDto = new ExamenSessionDto();
		List<SalleExamenDto> sallesSource = new ArrayList<SalleExamenDto>();
		List<SalleExamenDto> sallesTarget = new ArrayList<SalleExamenDto>();
		salleExamenDualList = new DualListModel<SalleExamenDto>(sallesSource, sallesTarget);
	}

	/**
	 * 
	 * [SalleExamenBean.loadExamenList] Method
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 14:12:15
	 */
	public void loadExamenList(long idPlanningSession) {
		examensList = new ArrayList<SelectItem>();
		try {

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
	 * [SalleExamenBean.examenChanged] Method
	 * 
	 * @author BELDI Jamel on : 24 sept. 2014 15:24:34
	 */
	public void examenChanged() {
		try {
			// salleExamenDto = null;
			loadSalleExamenDualList();
		} catch (Exception e) {

		}
	}

	/**
	 * 
	 * [SalleExamenBean.loadSalleExamenDualList] Method
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 12:03:52
	 */
	public void loadSalleExamenDualList() {

		try {
			List<SalleExamenDto> sallesSource = new ArrayList<SalleExamenDto>();
			List<SalleExamenDto> sallesTarget = new ArrayList<SalleExamenDto>();
			salleExamenDualList = new DualListModel<SalleExamenDto>(sallesSource, sallesTarget);

			if (examenSessionDto.getId() != 0) {
				sallesSource = loadSallesSource();
				sallesTarget = loadSallesTarget();
				salleExamenDualList = new DualListModel<SalleExamenDto>(sallesSource, sallesTarget);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [SalleExamenBean.loadSallesSource] Method
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 12:03:48
	 * @return
	 */
	private List<SalleExamenDto> loadSallesSource() {
		List<SalleExamenDto> sallesSource = new ArrayList<SalleExamenDto>();
		try {
			sallesSource = salleExamenService.findSalleNotaffectedToExamen(examenSessionDto.getId(),
			        sessionBean.getIdEtablissement());

		} catch (Exception e) {
			e.printStackTrace();
			sallesSource = new ArrayList<SalleExamenDto>();
		}
		return sallesSource;
	}

	private List<SalleExamenDto> loadSallesTarget() {
		List<SalleExamenDto> sallesTarget = new ArrayList<SalleExamenDto>();
		try {
			if (examenSessionDto.getId() != 0) {
				sallesTarget = salleExamenService.findByExamen(examenSessionDto.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			sallesTarget = new ArrayList<SalleExamenDto>();
		}
		return sallesTarget;
	}

	/**
	 * 
	 * [SalleExamenBean.onTransfer] Method
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 12:03:44
	 * @param event
	 */
	public void onTransfer(TransferEvent event) {
		try {
			if (event.isAdd()) {
				List<SalleExamenDto> items = (List<SalleExamenDto>) event.getItems();
				for (SalleExamenDto salleExamenDto : items) {
					List<SalleExamenDto> target = salleExamenDualList.getTarget();
					int index = target.indexOf(salleExamenDto);
					salleExamenDto.setExamenSessionId(examenSessionDto.getId());
					target.set(index, salleExamenDto);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 
	 * [SalleExamenBean.save] Method
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 12:03:59
	 */
	public void save() {
		try {

			if (salleExamenDualList == null) {
				return;
			} else {
				List<SalleExamenDto> sallesTarget = salleExamenDualList.getTarget();

				List<SalleExamenDto> sallesSource = salleExamenDualList.getSource();
				if (sallesTarget != null && !sallesTarget.isEmpty()) {
					for (SalleExamenDto salleExamenDto : sallesTarget) {
						if (salleExamenDto.getId() == null || salleExamenDto.getId() == 0) {
							salleExamenService.insertOrUpdate(salleExamenDto);
							//salleExamenDto = salleExamenService.findById(salleExamenDto.getId()) ; // refresh
						}
					}

				}
				if (sallesSource != null && !sallesSource.isEmpty()) {
					for (SalleExamenDto salleExamenDto : sallesSource) {
						if (salleExamenDto.getId() != null && salleExamenDto.getId() != 0) {
							List<InscriptionExamenDto> _listInscrit = inscriptionExamenService
							        .findByExamen(examenSessionDto.getId());
							if (_listInscrit != null && !_listInscrit.isEmpty()) {
								Utility.showErrorMessage(examenBundle
								        .getString("examen_error_suppression_inscription_affecte"));
								return;
							}

							List<ResponsableExamenDto> _listResp = responsableExamenService
							        .findByExamen(examenSessionDto.getId());
							if (_listResp != null && !_listResp.isEmpty()) {
								Utility.showErrorMessage(examenBundle
								        .getString("examen_error_suppression_responsable_affecte"));
								return;
							}
							salleExamenService.remove(salleExamenDto);
						}
					}
				}
				if (examenSessionDto != null && examenSessionDto.getId() != 0) {
					if (inscriptionExamenBean.getSalleExamenDto() != null) {
						inscriptionExamenBean.getSalleExamenDto().setExamenSessionId(examenSessionDto.getId());
						inscriptionExamenBean.examenChanged();
					}
					if (responsableExamenBean.getSalleExamenDto() != null) {
						responsableExamenBean.getSalleExamenDto().setExamenSessionId(examenSessionDto.getId());
						responsableExamenBean.examenChanged();
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
	 * [SalleExamenBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 16:31:10
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [SalleExamenBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 16:31:10
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [SalleExamenBean.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 16:31:10
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [SalleExamenBean.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 16:31:10
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [SalleExamenBean.salleExamenService] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 16:31:10
	 * @return the salleExamenService
	 */
	public SalleExamenService getSalleExamenService() {
		return salleExamenService;
	}

	/**
	 * [SalleExamenBean.salleExamenService] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 16:31:10
	 * @param salleExamenService
	 *            the salleExamenService to set
	 */
	public void setSalleExamenService(SalleExamenService salleExamenService) {
		this.salleExamenService = salleExamenService;
	}

	/**
	 * [SalleExamenBean.examensList] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 16:31:10
	 * @return the examensList
	 */
	public List<SelectItem> getExamensList() {
		return examensList;
	}

	/**
	 * [SalleExamenBean.examensList] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 16:31:10
	 * @param examensList
	 *            the examensList to set
	 */
	public void setExamensList(List<SelectItem> examensList) {
		this.examensList = examensList;
	}

	/**
	 * [SalleExamenBean.salleExamenDto] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 16:31:10
	 * @return the salleExamenDto
	 */
	public SalleExamenDto getSalleExamenDto() {
		return salleExamenDto;
	}

	/**
	 * [SalleExamenBean.salleExamenDto] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 16:31:10
	 * @param salleExamenDto
	 *            the salleExamenDto to set
	 */
	public void setSalleExamenDto(SalleExamenDto salleExamenDto) {
		this.salleExamenDto = salleExamenDto;
	}

	/**
	 * [SalleExamenBean.examenSessionDto] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 17:42:32
	 * @return the examenSessionDto
	 */
	public ExamenSessionDto getExamenSessionDto() {
		return examenSessionDto;
	}

	/**
	 * [SalleExamenBean.examenSessionDto] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 17:42:32
	 * @param examenSessionDto
	 *            the examenSessionDto to set
	 */
	public void setExamenSessionDto(ExamenSessionDto examenSessionDto) {
		this.examenSessionDto = examenSessionDto;
	}

	/**
	 * [SalleExamenBean.nomenclatureService] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 18:16:37
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [SalleExamenBean.nomenclatureService] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 18:16:37
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [SalleExamenBean.refLieuService] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 18:21:21
	 * @return the refLieuService
	 */
	public RefLieuService getRefLieuService() {
		return refLieuService;
	}

	/**
	 * [SalleExamenBean.refLieuService] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 18:21:21
	 * @param refLieuService
	 *            the refLieuService to set
	 */
	public void setRefLieuService(RefLieuService refLieuService) {
		this.refLieuService = refLieuService;
	}

	/**
	 * [SalleExamenBean.salleExamenDualList] Getter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 10:34:08
	 * @return the salleExamenDualList
	 */
	public DualListModel<SalleExamenDto> getSalleExamenDualList() {
		return salleExamenDualList;
	}

	/**
	 * [SalleExamenBean.salleExamenDualList] Setter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 10:34:08
	 * @param salleExamenDualList
	 *            the salleExamenDualList to set
	 */
	public void setSalleExamenDualList(DualListModel<SalleExamenDto> salleExamenDualList) {
		this.salleExamenDualList = salleExamenDualList;
	}

	/**
	 * [SalleExamenBean.examenSessionService] Getter
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:05:27
	 * @return the examenSessionService
	 */
	public ExamenSessionService getExamenSessionService() {
		return examenSessionService;
	}

	/**
	 * [SalleExamenBean.examenSessionService] Setter
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 14:05:27
	 * @param examenSessionService
	 *            the examenSessionService to set
	 */
	public void setExamenSessionService(ExamenSessionService examenSessionService) {
		this.examenSessionService = examenSessionService;
	}

	/**
	 * [SalleExamenBean.inscriptionExamenBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 11:18:39
	 * @return the inscriptionExamenBean
	 */
	public InscriptionExamenBean getInscriptionExamenBean() {
		return inscriptionExamenBean;
	}

	/**
	 * [SalleExamenBean.inscriptionExamenBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 11:18:39
	 * @param inscriptionExamenBean
	 *            the inscriptionExamenBean to set
	 */
	public void setInscriptionExamenBean(InscriptionExamenBean inscriptionExamenBean) {
		this.inscriptionExamenBean = inscriptionExamenBean;
	}

	/**
	 * [SalleExamenBean.responsableExamenBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 11:46:27
	 * @return the responsableExamenBean
	 */
	public ResponsableExamenBean getResponsableExamenBean() {
		return responsableExamenBean;
	}

	/**
	 * [SalleExamenBean.responsableExamenBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 11:46:27
	 * @param responsableExamenBean
	 *            the responsableExamenBean to set
	 */
	public void setResponsableExamenBean(ResponsableExamenBean responsableExamenBean) {
		this.responsableExamenBean = responsableExamenBean;
	}

	/**
	 * [SalleExamenBean.inscriptionExamenService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 11:34:10
	 * @return the inscriptionExamenService
	 */
	public InscriptionExamenService getInscriptionExamenService() {
		return inscriptionExamenService;
	}

	/**
	 * [SalleExamenBean.inscriptionExamenService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 11:34:10
	 * @param inscriptionExamenService
	 *            the inscriptionExamenService to set
	 */
	public void setInscriptionExamenService(InscriptionExamenService inscriptionExamenService) {
		this.inscriptionExamenService = inscriptionExamenService;
	}

	/**
	 * [SalleExamenBean.responsableExamenService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 09:29:58
	 * @return the responsableExamenService
	 */
	public ResponsableExamenService getResponsableExamenService() {
		return responsableExamenService;
	}

	/**
	 * [SalleExamenBean.responsableExamenService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 09:29:58
	 * @param responsableExamenService
	 *            the responsableExamenService to set
	 */
	public void setResponsableExamenService(ResponsableExamenService responsableExamenService) {
		this.responsableExamenService = responsableExamenService;
	}

}
