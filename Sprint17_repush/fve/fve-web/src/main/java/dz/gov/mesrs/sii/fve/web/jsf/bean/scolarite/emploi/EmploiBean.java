package dz.gov.mesrs.sii.fve.web.jsf.bean.scolarite.emploi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.AffectationLieuStructureDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EmploiDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.SeanceEmploiByPlageHoraireDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.SeanceEmploiDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.AffectationLieuStructureService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EmploiService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.SeanceEmploiService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;

/**
 * 
 * @author BELDI Jamel on : 19 oct. 2014 10:04:54
 */
@ManagedBean(name = "emploiBean")
@ViewScoped
public class EmploiBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private ResourceBundle bundleCommon;
	private ResourceBundle emploiBundle;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> offreFormationList;
	private List<SelectItem> periodiciteList;
	private List<SelectItem> periodeList;
	private List<SelectItem> niveauList;
	private EmploiDto emploiDto;
	@ManagedProperty("#{emploiService}")
	private EmploiService emploiService;
	@ManagedProperty("#{emploiSearchBean}")
	private EmploiSearchBean emploiSearchBean;
	@ManagedProperty("#{seanceEmploiService}")
	private SeanceEmploiService seanceEmploiService;
	@ManagedProperty("#{affectationLieuStructureService}")
	private AffectationLieuStructureService affectationLieuStructureService;
	private List<SeanceEmploiByPlageHoraireDto> seanceEmploiByPlageHoraireDtos;
	private SeanceEmploiDto seanceEmploiDto;
	private List<SelectItem> matieres;
	private List<SelectItem> groupes;
	private List<SelectItem> affectationLieuStructures;
	@ManagedProperty("#{periodeService}")
	private PeriodeService periodeService;
	Integer periodeId;
	
	
	/**
	 * 
	 * [EmploiBean.EmploiBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:33:47
	 */
	public EmploiBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		emploiBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.EMPLOI_BUNDLE_MSG_NAME);

	}

	@PostConstruct
	public void init() {
		emploiDto =null;
		seanceEmploiDto = new SeanceEmploiDto();
		seanceEmploiByPlageHoraireDtos = new ArrayList<SeanceEmploiByPlageHoraireDto>();
		loadAnneeAcademique();
		loadPeriodiciteList();
		//loadSeances();
	}

	/**
	 * 
	 * [EmploiSearchBean.loadAnneeAcademique] Method
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:08:27
	 */
	private void loadAnneeAcademique() {
		anneeAcademiqueList = utilBean.loadAnneeAcademique();

	}

	/**
	 * 
	 * [EmploiBean.loadPeriodiciteList] Method
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 18:02:44
	 */
	private void loadPeriodiciteList() {
		periodiciteList = utilBean.loadPeriodiciteList();
	}

	private void loadOuvertureOfList() {
		offreFormationList = utilBean.loadOffreFormationOuverte(emploiDto
				.getAnneeAcademiqueId());
	}

	/**
	 * 
	 * [EmploiBean.loadPeriodeByPeriodicite] Method
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 18:03:09
	 * @param periodiciteId
	 */
	private void loadPeriodeByPeriodicite(Integer periodiciteId) {
		periodeList = utilBean.loadPeriodeByPeriodicite(periodiciteId);

	}

	/**
	 * 
	 * [EmploiSearchBean.anneeChanged] Method
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:08:30
	 */
	public void anneeChanged() {
		emploiDto.setOuvertureOffreFormationId(null);
		loadOuvertureOfList();
		ofChanged();

	}

	/**
	 * 
	 * [EmploiSearchBean.ofChanged] Method
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:08:33
	 */
	public void ofChanged() {
		emploiDto.setNiveauId(null);
		loadNiveaux();
		niveauChanged();

	}

	/***
	 * 
	 * [EmploiBean.loadNiveaux] Method
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 18:50:49
	 */
	private void loadNiveaux() {
		niveauList = utilBean.loadNiveaux(emploiDto
				.getOuvertureOffreFormationId());
		niveauChanged();
	}

	/**
	 * 
	 * [EmploiBean.niveauChanged] Method
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 18:51:02
	 */
	public void niveauChanged() {
		//loadEmploi();
	}

	/**
	 * 
	 * [EmploiBean.periodiciteChanged] Method
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 18:54:03
	 */
	public void periodiciteChanged() {
		emploiDto.setPeriodeId(null);
		loadPeriodeByPeriodicite(emploiDto.getPeriodiciteId());
		if (periodeList != null && periodeList.size() == 1) {
			emploiDto.setPeriodeId(Integer.valueOf(periodiciteList.get(0)
					.getValue().toString()));
		}
		periodeChanged();
	}

	/**
	 * 
	 * [EmploiBean.periodiciteChanged] Method
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 18:54:03
	 */
	public void periodeChanged() {
		//loadEmploi();
	}

	/**
	 * 
	 * [EmploiBean.loadMcGrouped] Method 
	 * @author BELDI Jamel  on : 26 oct. 2014  10:47:30
	 */
	public void loadMcGrouped() {
		try{
		if (emploiDto == null || emploiDto.getId() == 0) {
			matieres = new ArrayList<SelectItem>();
			return;
		}		
		
		periodeId= periodeService.findByLevelAndYearPeriod(emploiDto.getNiveauId(), emploiDto.getPeriodeId()).getId();
		matieres = utilBean.loadMcGroupedByUe(emploiDto.getOuvertureOffreFormationId(), periodeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * [EmploiBean.mcChanged] Method 
	 * @author BELDI Jamel  on : 26 oct. 2014  10:55:54
	 */
	public void mcChanged() {
		loadGroupeByAp();
	}
	
	/**
	 * 
	 * [EmploiBean.loadGroupeByAp] Method 
	 * @author BELDI Jamel  on : 26 oct. 2014  10:56:46
	 */
	public void loadGroupeByAp() {
		groupes = utilBean.loadGroupePedagogiqueByAp(emploiDto.getOuvertureOffreFormationId(), periodeId,
				seanceEmploiDto.getRattachementMcId());
	}
	
	/**
	 * 
	 * [EmploiBean.loadAffectationLieuStructures] Method 
	 * @author BELDI Jamel  on : 26 oct. 2014  12:05:45
	 */
	public void loadAffectationLieuStructures(){
		affectationLieuStructures = new ArrayList<SelectItem>();
		try {
		if (emploiDto == null || emploiDto.getId() == 0) {
			return;
		}
		AffectationLieuStructureDto searchDto = new AffectationLieuStructureDto();
		searchDto.setAnneeAcademiqueId(emploiDto.getAnneeAcademiqueId());
		searchDto.setPeriodeId(emploiDto.getPeriodeId());
		searchDto.setPeriodiciteId(emploiDto.getPeriodiciteId());
		searchDto.setCelluleHoraireId(seanceEmploiDto.getCelluleHoraireId());
		searchDto.setOuvertureOffreFormationId(emploiDto.getOuvertureOffreFormationId());
		List<AffectationLieuStructureDto> list = affectationLieuStructureService.findAdvanced(searchDto);
		if (list==null || list.isEmpty()){
			return;
			
		}
		for (AffectationLieuStructureDto affectationLieuStructureDto : list) {
			SelectItem item = new SelectItem(affectationLieuStructureDto.getId(),
					affectationLieuStructureDto.getRefLieuTypeLibelleLongFr()+" "+ affectationLieuStructureDto.getRefLieuDesignation());
			affectationLieuStructures.add(item);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * 
	 * [EmploiBean.onEmploiSelect] Method
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 16:12:53
	 * @param event
	 */
	public void onEmploiSelect(SelectEvent event) {
		emploiDto = ((EmploiDto) event.getObject());
		loadPeriodeByPeriodicite(emploiDto.getPeriodiciteId());
		loadOuvertureOfList();
		loadNiveaux();

	}

	/**
	 * 
	 * [EmploiBean.addEmploi] Method
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 17:01:41
	 */
	public void addEmploi() {
		init();
		emploiDto =new EmploiDto();
		emploiDto.setAnneeAcademiqueId(sessionBean.getIdAnneeAcademique());
		emploiDto.setRefCodeEtablissement(sessionBean.getCodeEtablissement());
		loadPeriodeByPeriodicite(emploiDto.getPeriodiciteId());
		loadOuvertureOfList();
		loadNiveaux();
		loadSeances();

	}
	/**
	 * 
	 * [EmploiBean.annuler] Method 
	 * @author BELDI Jamel  on : 27 oct. 2014  10:32:59
	 */
	public void annuler() {
		emploiDto =null;
		seanceEmploiDto = new SeanceEmploiDto();
		seanceEmploiByPlageHoraireDtos = new ArrayList<SeanceEmploiByPlageHoraireDto>();
		periodeList = new ArrayList<SelectItem>();
		niveauList = new ArrayList<SelectItem>();
		offreFormationList = new ArrayList<SelectItem>();
	
	

	}
	/**
	 * 
	 * [EmploiBean.loadSeances] Method
	 * 
	 * @author BELDI Jamel on : 25 oct. 2014 12:28:35
	 */
	private void loadSeances() {
		seanceEmploiByPlageHoraireDtos = seanceEmploiService
				.findByEmploiId(emploiDto);
	}

	/**
	 * 
	 * [EmploiBean.toEdit] Method
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 20:04:01
	 */
	public void toEdit() {
//		loadPeriodeByPeriodicite(emploiDto.getPeriodiciteId());
//		loadOuvertureOfList();
//		loadNiveaux();
		loadSeances();
		loadMcGrouped() ;
	}
	
	

	/**
	 * 
	 * [EmploiBean.saveEmploi] Method
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 16:13:05
	 */
	public void saveEmploi() {
		FacesMessage msg = new FacesMessage();
		
		try {
          
             List<EmploiDto> list = emploiService.findAdvanced(emploiDto);	
			if(list!=null && !list.isEmpty()){
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleCommon.getString("error_echec") + ": "
						+ emploiBundle.getString("emploi_emploi_exist_msg"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return ;
			}

			emploiDto = emploiService.insertOrUpdate(emploiDto);
		
			loadSeances();
			loadMcGrouped() ;
			
			emploiSearchBean.searchEmploiList();
			msg.setSeverity(FacesMessage.SEVERITY_INFO);

			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_enregistrement"));
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
	 * [EmploiBean.fenceEmploi] Method 
	 * @author BELDI Jamel  on : 27 oct. 2014  10:44:27
	 */
	public void fenceEmploi() {
		FacesMessage msg = new FacesMessage();
		
		try {
           emploiDto.setEstVerrouille(true);
           emploiDto.setDateVerrouillage(new Date());
		   emploiService.insertOrUpdate(emploiDto);
		   emploiSearchBean.searchEmploiList();
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_modification"));
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
	 * [EmploiBean.deleteEmploi] Method 
	 * @author BELDI Jamel  on : 27 oct. 2014  10:44:46
	 */
	public void deleteEmploi() {
		FacesMessage msg = new FacesMessage();
		
		try {
			emploiService.remove(emploiDto);
			annuler();
			emploiSearchBean.searchEmploiList();
			msg.setSeverity(FacesMessage.SEVERITY_INFO);

			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_suppression"));
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
	 * [EmploiBean.editSeance] Method 
	 * @author BELDI Jamel  on : 27 oct. 2014  10:02:42
	 */
	public void editSeance(){
		//RequestContext.getCurrentInstance().openDialog("seanceDialog");
		loadAffectationLieuStructures();
		loadGroupeByAp();
	}
	
	/**
	 * 
	 * [EmploiBean.saveSeance] Method 
	 * @author BELDI Jamel  on : 26 oct. 2014  12:32:11
	 */
	public void saveSeance() {
		FacesMessage msg = new FacesMessage();
		String summary;
		try {
           
			if (seanceEmploiDto.getId() == 0) {
				summary = "msg_success_enregistrement";
				
			} else {
				summary = "msg_success_modification";
			}

			seanceEmploiDto = seanceEmploiService.insertOrUpdate(seanceEmploiDto);
			
			loadSeances();			
			msg.setSeverity(FacesMessage.SEVERITY_INFO);

			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString(summary));
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
	 * [EmploiBean.deleteSeance] Method 
	 * @author BELDI Jamel  on : 2 nov. 2014  17:34:22
	 */
	public void deleteSeance() {
		FacesMessage msg = new FacesMessage();
		
		try {
           
			if (seanceEmploiDto.getId() == 0) {
				return;
				
			} else {
			seanceEmploiService.remove(seanceEmploiDto);
			loadSeances();			
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") );
					msg.setDetail( bundleCommon.getString("msg_success_suppression"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}
	// getter and Setter

	/**
	 * [EmploiBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [EmploiBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [EmploiBean.utilBean] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [EmploiBean.utilBean] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [EmploiBean.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [EmploiBean.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [EmploiBean.anneeAcademiqueList] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [EmploiBean.anneeAcademiqueList] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [EmploiBean.offreFormationList] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @return the offreFormationList
	 */
	public List<SelectItem> getOffreFormationList() {
		return offreFormationList;
	}

	/**
	 * [EmploiBean.offreFormationList] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @param offreFormationList
	 *            the offreFormationList to set
	 */
	public void setOffreFormationList(List<SelectItem> offreFormationList) {
		this.offreFormationList = offreFormationList;
	}

	/**
	 * [EmploiBean.periodiciteList] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @return the periodiciteList
	 */
	public List<SelectItem> getPeriodiciteList() {
		return periodiciteList;
	}

	/**
	 * [EmploiBean.periodiciteList] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @param periodiciteList
	 *            the periodiciteList to set
	 */
	public void setPeriodiciteList(List<SelectItem> periodiciteList) {
		this.periodiciteList = periodiciteList;
	}

	/**
	 * [EmploiBean.periodeList] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @return the periodeList
	 */
	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	/**
	 * [EmploiBean.periodeList] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @param periodeList
	 *            the periodeList to set
	 */
	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	/**
	 * [EmploiBean.emploiDto] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @return the emploiDto
	 */
	public EmploiDto getEmploiDto() {
		return emploiDto;
	}

	/**
	 * [EmploiBean.emploiDto] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @param emploiDto
	 *            the emploiDto to set
	 */
	public void setEmploiDto(EmploiDto emploiDto) {
		this.emploiDto = emploiDto;
	}

	/**
	 * [EmploiBean.emploiService] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @return the emploiService
	 */
	public EmploiService getEmploiService() {
		return emploiService;
	}

	/**
	 * [EmploiBean.emploiService] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 17:49:45
	 * @param emploiService
	 *            the emploiService to set
	 */
	public void setEmploiService(EmploiService emploiService) {
		this.emploiService = emploiService;
	}

	/**
	 * [EmploiBean.niveauList] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 18:42:14
	 * @return the niveauList
	 */
	public List<SelectItem> getNiveauList() {
		return niveauList;
	}

	/**
	 * [EmploiBean.niveauList] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 18:42:14
	 * @param niveauList
	 *            the niveauList to set
	 */
	public void setNiveauList(List<SelectItem> niveauList) {
		this.niveauList = niveauList;
	}

	/**
	 * [EmploiBean.emploiSearchBean] Getter
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 16:43:16
	 * @return the emploiSearchBean
	 */
	public EmploiSearchBean getEmploiSearchBean() {
		return emploiSearchBean;
	}

	/**
	 * [EmploiBean.emploiSearchBean] Setter
	 * 
	 * @author BELDI Jamel on : 23 oct. 2014 16:43:16
	 * @param emploiSearchBean
	 *            the emploiSearchBean to set
	 */
	public void setEmploiSearchBean(EmploiSearchBean emploiSearchBean) {
		this.emploiSearchBean = emploiSearchBean;
	}

	/**
	 * [EmploiBean.seanceEmploiService] Getter
	 * 
	 * @author BELDI Jamel on : 25 oct. 2014 12:26:00
	 * @return the seanceEmploiService
	 */
	public SeanceEmploiService getSeanceEmploiService() {
		return seanceEmploiService;
	}

	/**
	 * [EmploiBean.seanceEmploiService] Setter
	 * 
	 * @author BELDI Jamel on : 25 oct. 2014 12:26:00
	 * @param seanceEmploiService
	 *            the seanceEmploiService to set
	 */
	public void setSeanceEmploiService(SeanceEmploiService seanceEmploiService) {
		this.seanceEmploiService = seanceEmploiService;
	}

	/**
	 * [EmploiBean.seanceEmploiByPlageHoraireDtos] Getter
	 * 
	 * @author BELDI Jamel on : 25 oct. 2014 12:27:34
	 * @return the seanceEmploiByPlageHoraireDtos
	 */
	public List<SeanceEmploiByPlageHoraireDto> getSeanceEmploiByPlageHoraireDtos() {
		return seanceEmploiByPlageHoraireDtos;
	}

	/**
	 * [EmploiBean.seanceEmploiByPlageHoraireDtos] Setter
	 * 
	 * @author BELDI Jamel on : 25 oct. 2014 12:27:34
	 * @param seanceEmploiByPlageHoraireDtos
	 *            the seanceEmploiByPlageHoraireDtos to set
	 */
	public void setSeanceEmploiByPlageHoraireDtos(
			List<SeanceEmploiByPlageHoraireDto> seanceEmploiByPlageHoraireDtos) {
		this.seanceEmploiByPlageHoraireDtos = seanceEmploiByPlageHoraireDtos;
	}

	/**
	 * [EmploiBean.seanceEmploiDto] Getter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:48:35
	 * @return the seanceEmploiDto
	 */
	public SeanceEmploiDto getSeanceEmploiDto() {
		return seanceEmploiDto;
	}

	/**
	 * [EmploiBean.seanceEmploiDto] Setter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:48:35
	 * @param seanceEmploiDto the seanceEmploiDto to set
	 */
	public void setSeanceEmploiDto(SeanceEmploiDto seanceEmploiDto) {
		this.seanceEmploiDto = seanceEmploiDto;
	}

	/**
	 * [EmploiBean.matieres] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:48:46
	 * @return the matieres
	 */
	public List<SelectItem> getMatieres() {
		return matieres;
	}

	/**
	 * [EmploiBean.matieres] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:48:46
	 * @param matieres the matieres to set
	 */
	public void setMatieres(List<SelectItem> matieres) {
		this.matieres = matieres;
	}

	/**
	 * [EmploiBean.groupes] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:48:46
	 * @return the groupes
	 */
	public List<SelectItem> getGroupes() {
		return groupes;
	}

	/**
	 * [EmploiBean.groupes] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:48:46
	 * @param groupes the groupes to set
	 */
	public void setGroupes(List<SelectItem> groupes) {
		this.groupes = groupes;
	}

	/**
	 * [EmploiBean.periodeId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:59:18
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [EmploiBean.periodeId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:59:18
	 * @param periodeId the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [EmploiBean.affectationLieuStructures] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  12:03:49
	 * @return the affectationLieuStructures
	 */
	public List<SelectItem> getAffectationLieuStructures() {
		return affectationLieuStructures;
	}

	/**
	 * [EmploiBean.affectationLieuStructures] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  12:03:49
	 * @param affectationLieuStructures the affectationLieuStructures to set
	 */
	public void setAffectationLieuStructures(
			List<SelectItem> affectationLieuStructures) {
		this.affectationLieuStructures = affectationLieuStructures;
	}

	/**
	 * [EmploiBean.affectationLieuStructureService] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  12:08:05
	 * @return the affectationLieuStructureService
	 */
	public AffectationLieuStructureService getAffectationLieuStructureService() {
		return affectationLieuStructureService;
	}

	/**
	 * [EmploiBean.affectationLieuStructureService] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  12:08:05
	 * @param affectationLieuStructureService the affectationLieuStructureService to set
	 */
	public void setAffectationLieuStructureService(
			AffectationLieuStructureService affectationLieuStructureService) {
		this.affectationLieuStructureService = affectationLieuStructureService;
	}

	/**
	 * [EmploiBean.periodeService] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  16:13:52
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [EmploiBean.periodeService] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  16:13:52
	 * @param periodeService the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	
	
	
	

}
