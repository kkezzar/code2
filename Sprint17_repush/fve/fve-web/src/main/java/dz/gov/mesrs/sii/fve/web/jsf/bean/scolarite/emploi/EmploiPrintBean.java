package dz.gov.mesrs.sii.fve.web.jsf.bean.scolarite.emploi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.dozer.DozerBeanMapper;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EmploiModelDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.SeanceEmploiByPlageHoraireDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.SeanceEmploiDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.GroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.impression.ImpressionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.SeanceEmploiService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.impression.PrintMgrBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

/**
 * 
 * @author BELDI Jamel on : 19 oct. 2014 10:04:54
 */
@ManagedBean(name = "emploiPrintBean")
@ViewScoped
public class EmploiPrintBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private ResourceBundle bundleCommon;
	private ResourceBundle emploiBundle;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{printMgrBean}")
	private PrintMgrBean printMgrBean;
	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;
	@ManagedProperty("#{impressionService}")
	private ImpressionService impressionService;
	@ManagedProperty("#{seanceEmploiService}")
	private SeanceEmploiService seanceEmploiService;
	@ManagedProperty("#{periodeService}")
	private PeriodeService periodeService;
	@ManagedProperty("#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	@ManagedProperty("#{groupePedagogiqueService}")
	private GroupePedagogiqueService groupePedagogiqueService;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> offreFormationList;
	private List<SelectItem> periodiciteList;
	private List<SelectItem> periodeList;
	private List<SelectItem> niveauList;
	private List<SelectItem> groupeList;
	private SeanceEmploiDto searchDto;
	private List<SeanceEmploiByPlageHoraireDto> seanceEmploiByPlageHoraireDtos;
	Integer periodeId;

	/**
	 * 
	 * [EmploiPrintBean.EmploiPrintBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:23
	 */
	public EmploiPrintBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		emploiBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.EMPLOI_BUNDLE_MSG_NAME);

	}

	/**
	 * 
	 * [EmploiPrintBean.init] Method
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:29
	 */
	@PostConstruct
	public void init() {
		seanceEmploiByPlageHoraireDtos = new ArrayList<SeanceEmploiByPlageHoraireDto>();
		searchDto = new SeanceEmploiDto();
		initAnnee();
		loadAnneeAcademique();
		loadOuvertureOfList();
		loadPeriodiciteList();
		

	}
/**
 * 
 * [EmploiPrintBean.initAnnee] Method 
 * @author BELDI Jamel  on : 5 nov. 2014  19:20:02
 */
	private void initAnnee(){
		 searchDto.setAnneeAcademiqueId(sessionBean.getIdAnneeAcademique());
		 AnneeAcademiqueDto annee= utilBean.findAnneeAcademique(searchDto.getAnneeAcademiqueId());
		 searchDto.setAnneeAcademiqueCode(annee==null?null:annee.getCode());	
	}
	/**
	 * 
	 * [EmploiPrintBean.loadAnneeAcademique] Method
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:26
	 */
	private void loadAnneeAcademique() {
		anneeAcademiqueList = utilBean.loadAnneeAcademique();

	}

	/**
	 * 
	 * [EmploiPrintBean.loadPeriodiciteList] Method
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:33
	 */
	private void loadPeriodiciteList() {
		periodiciteList = utilBean.loadPeriodiciteList();
	}

	 private void loadOuvertureOfList() {
	 offreFormationList = utilBean.loadOffreFormationOuverte(searchDto
	 .getAnneeAcademiqueId());
	 }
	
	/**
	 * 
	 * [EmploiPrintBean.loadPeriodeByPeriodicite] Method 
	 * @author BELDI Jamel  on : 6 nov. 2014  12:19:53
	 * @param periodiciteId
	 */
	 private void loadPeriodeByPeriodicite(Integer periodiciteId) {
	 periodeList = utilBean.loadPeriodeByPeriodicite(periodiciteId);
	
	 }
	
	/**
	 * 
	 * [EmploiPrintBean.anneeChanged] Method 
	 * @author BELDI Jamel  on : 6 nov. 2014  12:19:51
	 */
	 public void anneeChanged() {
	 searchDto.setOuvertureOffreFormationId(null);
	 AnneeAcademiqueDto annee= utilBean.findAnneeAcademique(searchDto.getAnneeAcademiqueId());
	 searchDto.setAnneeAcademiqueCode(annee==null?null:annee.getCode());
	 loadOuvertureOfList();
	 ofChanged();
	
	 }
	/**
	 * 
	 * [EmploiPrintBean.ofChanged] Method 
	 * @author BELDI Jamel  on : 6 nov. 2014  12:19:48
	 */
	 public void ofChanged() {
	 searchDto.setNiveauId(null);
	 OuvertureOffreFormationDto oof= utilBean.findOof(searchDto.getOuvertureOffreFormationId());
	 searchDto.setOffreFormationLibelleFr(oof==null?null:oof.getOfLibelleLongFr());
	 loadNiveaux();
	 niveauChanged();
	
	 }
	
	/**
	 * 
	 * [EmploiPrintBean.loadNiveaux] Method 
	 * @author BELDI Jamel  on : 6 nov. 2014  12:19:44
	 */
	 private void loadNiveaux() {
	 niveauList = utilBean.loadNiveaux(searchDto.getOuvertureOffreFormationId());
	
	 }
/**
 * 
 * [EmploiPrintBean.niveauChanged] Method 
 * @author BELDI Jamel  on : 6 nov. 2014  12:19:42
 */
	public void niveauChanged() {
	 NiveauDto niveau =  utilBean.findNiveau(searchDto.getNiveauId());
		searchDto.setNiveauLibelleLongLt(niveau==null?null:niveau.getLibelleLongLt());
		loadGroupe();
		seanceEmploiByPlageHoraireDtos = new ArrayList<SeanceEmploiByPlageHoraireDto>();
	}
/**
 * 
 * [EmploiPrintBean.loadGroupe] Method 
 * @author BELDI Jamel  on : 6 nov. 2014  12:19:38
 */
	private void loadGroupe() {
		try{
		searchDto.setGroupePedagogiqueId(null);
		if(searchDto.getNiveauId()!=null && searchDto.getPeriodeId()!=null){
		periodeId= periodeService.findByLevelAndYearPeriod(searchDto.getNiveauId(), searchDto.getPeriodeId()).getId();
		groupeList = utilBean.loadGroupePedagogiqueByOofAndPeriode(searchDto.getOuvertureOffreFormationId(), periodeId);
		}else{
		groupeList = new ArrayList<SelectItem>();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
/**
 * 
 * [EmploiPrintBean.periodiciteChanged] Method 
 * @author BELDI Jamel  on : 6 nov. 2014  12:19:36
 */
	public void periodiciteChanged() {
		searchDto.setPeriodeId(null);
		 loadPeriodeByPeriodicite(searchDto.getPeriodiciteId());
		 if (periodeList != null && periodeList.size() == 1) {
			 searchDto.setPeriodeId(Integer.valueOf(periodiciteList.get(0)
		 .getValue().toString()));
		 }
    periodeChanged();
	}
/**
 * 
 * [EmploiPrintBean.periodeChanged] Method 
 * @author BELDI Jamel  on : 6 nov. 2014  12:19:10
 */
	public void periodeChanged() {
		if(searchDto.getPeriodeId()!=null){
		NomenclatureDto periode = nomenclatureService.findById(searchDto.getPeriodeId());
		searchDto.setPeriodeLibelleLongFr(periode==null?null:periode.getLibelleLongFr());
		}
		loadGroupe(); 
		seanceEmploiByPlageHoraireDtos = new ArrayList<SeanceEmploiByPlageHoraireDto>();
	}
	/**
	 * 
	 * [EmploiPrintBean.groupeChanged] Method 
	 * @author BELDI Jamel  on : 6 nov. 2014  12:19:07
	 */
	public void groupeChanged() {
		GroupePedagogiqueDto groupe = groupePedagogiqueService.findById(searchDto.getGroupePedagogiqueId());
		searchDto.setGroupePedagogiqueNom(groupe==null?null:groupe.getNomAffichage());
		seanceEmploiByPlageHoraireDtos = new ArrayList<SeanceEmploiByPlageHoraireDto>();
	}
	
	/**
	 * 
	 * [EmploiPrintBean.searchSeances] Method 
	 * @author BELDI Jamel  on : 6 nov. 2014  12:19:03
	 */
	public void searchSeances() {
		try {
			seanceEmploiByPlageHoraireDtos = new ArrayList<SeanceEmploiByPlageHoraireDto>();
			seanceEmploiByPlageHoraireDtos = seanceEmploiService
					.findbyGroupe(searchDto);
		} catch (Exception e) {
			seanceEmploiByPlageHoraireDtos = new ArrayList<SeanceEmploiByPlageHoraireDto>();
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}

	}

	/**
	 * 
	 * [EmploiPrintBean.printEmploiByGroupePdf] Method
	 * 
	 * @author BELDI Jamel on : 5 nov. 2014 09:10:40
	 */
	public void printEmploiByGroupePdf() {
		try {
			byte[] bytes = buildEmploiDuTemps();
			if(bytes!=null){
			printMgrBean.imprimer(bytes, "emploi_du_temps_groupe", "pdf");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}
	/**
	 * 
	 * [EmploiPrintBean.printEmploiByGroupeExcel] Method 
	 * @author BELDI Jamel  on : 6 nov. 2014  12:19:14
	 */
	public void printEmploiByGroupeExcel() {
		try {
			byte[] bytes = buildEmploiDuTemps();
			if(bytes!=null){
			printMgrBean.imprimer(bytes, "emploi_du_temps_groupe", "xlsx");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}
	/**
	 * 
	 * [EmploiPrintBean.buildEmploiDuTemps] Method 
	 * @author BELDI Jamel  on : 6 nov. 2014  12:19:18
	 * @return
	 */
	private byte[] buildEmploiDuTemps(){
		try {
			List<EmploiModelDto> seances = new ArrayList<EmploiModelDto>();
			seances = seanceEmploiService.convertToEmploiModel(seanceEmploiByPlageHoraireDtos);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = facesContext
					.getExternalContext()
					.getRealPath(
							"/WEB-INF/classes/jasper/emploi/emploi_temps.jrxml");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("IMG_PAPS", facesContext.getExternalContext()
					.getRealPath("images") + "/logopaps.png");
			params.put("IMG_LOGO",
					facesContext.getExternalContext().getRealPath("images")
							+ "/logo" + sessionBean.getCodeEtablissement()
							+ ".png");

			params.put("OFFRE_FORMATION", searchDto.getOffreFormationLibelleFr());

			params.put("ANNEE_ACADEMIQUE", searchDto.getAnneeAcademiqueCode());

			params.put("PERIODE", searchDto.getPeriodeLibelleLongFr());

			params.put("NIVEAU", searchDto.getNiveauLibelleLongLt());
			params.put("GROUPE_PEDAGOGIQUE", searchDto.getGroupePedagogiqueNom());
			byte[] bytes = impressionService.viewPDFWithDataSource(
					RESOURCE_PATH_TO_INPUT_FILE_JASPER, params, seances);
			return bytes;

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
		return null;
	}

	/**
	 * [EmploiPrintBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [EmploiPrintBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [EmploiPrintBean.utilBean] Getter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [EmploiPrintBean.utilBean] Setter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [EmploiPrintBean.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [EmploiPrintBean.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [EmploiPrintBean.anneeAcademiqueList] Getter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [EmploiPrintBean.anneeAcademiqueList] Setter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [EmploiPrintBean.offreFormationList] Getter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @return the offreFormationList
	 */
	public List<SelectItem> getOffreFormationList() {
		return offreFormationList;
	}

	/**
	 * [EmploiPrintBean.offreFormationList] Setter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @param offreFormationList
	 *            the offreFormationList to set
	 */
	public void setOffreFormationList(List<SelectItem> offreFormationList) {
		this.offreFormationList = offreFormationList;
	}

	/**
	 * [EmploiPrintBean.periodiciteList] Getter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @return the periodiciteList
	 */
	public List<SelectItem> getPeriodiciteList() {
		return periodiciteList;
	}

	/**
	 * [EmploiPrintBean.periodiciteList] Setter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @param periodiciteList
	 *            the periodiciteList to set
	 */
	public void setPeriodiciteList(List<SelectItem> periodiciteList) {
		this.periodiciteList = periodiciteList;
	}

	/**
	 * [EmploiPrintBean.periodeList] Getter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @return the periodeList
	 */
	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	/**
	 * [EmploiPrintBean.periodeList] Setter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @param periodeList
	 *            the periodeList to set
	 */
	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	/**
	 * [EmploiPrintBean.niveauList] Getter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @return the niveauList
	 */
	public List<SelectItem> getNiveauList() {
		return niveauList;
	}

	/**
	 * [EmploiPrintBean.niveauList] Setter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @param niveauList
	 *            the niveauList to set
	 */
	public void setNiveauList(List<SelectItem> niveauList) {
		this.niveauList = niveauList;
	}

	
	
	
	/**
	 * [EmploiPrintBean.groupeList] Getter 
	 * @author BELDI Jamel on : 5 nov. 2014  17:23:17
	 * @return the groupeList
	 */
	public List<SelectItem> getGroupeList() {
		return groupeList;
	}

	/**
	 * [EmploiPrintBean.groupeList] Setter 
	 * @author BELDI Jamel on : 5 nov. 2014  17:23:17
	 * @param groupeList the groupeList to set
	 */
	public void setGroupeList(List<SelectItem> groupeList) {
		this.groupeList = groupeList;
	}

	/**
	 * [EmploiPrintBean.seanceEmploiByPlageHoraireDtos] Getter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @return the seanceEmploiByPlageHoraireDtos
	 */
	public List<SeanceEmploiByPlageHoraireDto> getSeanceEmploiByPlageHoraireDtos() {
		return seanceEmploiByPlageHoraireDtos;
	}

	/**
	 * [EmploiPrintBean.seanceEmploiByPlageHoraireDtos] Setter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:21:10
	 * @param seanceEmploiByPlageHoraireDtos
	 *            the seanceEmploiByPlageHoraireDtos to set
	 */
	public void setSeanceEmploiByPlageHoraireDtos(
			List<SeanceEmploiByPlageHoraireDto> seanceEmploiByPlageHoraireDtos) {
		this.seanceEmploiByPlageHoraireDtos = seanceEmploiByPlageHoraireDtos;
	}

	/**
	 * [EmploiPrintBean.printMgrBean] Getter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:53:18
	 * @return the printMgrBean
	 */
	public PrintMgrBean getPrintMgrBean() {
		return printMgrBean;
	}

	/**
	 * [EmploiPrintBean.printMgrBean] Setter
	 * 
	 * @author BELDI Jamel on : 4 nov. 2014 17:53:18
	 * @param printMgrBean
	 *            the printMgrBean to set
	 */
	public void setPrintMgrBean(PrintMgrBean printMgrBean) {
		this.printMgrBean = printMgrBean;
	}

	/**
	 * [EmploiPrintBean.impressionService] Getter
	 * 
	 * @author BELDI Jamel on : 5 nov. 2014 09:08:09
	 * @return the impressionService
	 */
	public ImpressionService getImpressionService() {
		return impressionService;
	}

	/**
	 * [EmploiPrintBean.impressionService] Setter
	 * 
	 * @author BELDI Jamel on : 5 nov. 2014 09:08:09
	 * @param impressionService
	 *            the impressionService to set
	 */
	public void setImpressionService(ImpressionService impressionService) {
		this.impressionService = impressionService;
	}

	/**
	 * [EmploiPrintBean.searchDto] Getter
	 * 
	 * @author BELDI Jamel on : 5 nov. 2014 17:03:53
	 * @return the searchDto
	 */
	public SeanceEmploiDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [EmploiPrintBean.searchDto] Setter
	 * 
	 * @author BELDI Jamel on : 5 nov. 2014 17:03:53
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(SeanceEmploiDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * [EmploiPrintBean.seanceEmploiService] Getter
	 * 
	 * @author BELDI Jamel on : 5 nov. 2014 17:06:28
	 * @return the seanceEmploiService
	 */
	public SeanceEmploiService getSeanceEmploiService() {
		return seanceEmploiService;
	}

	/**
	 * [EmploiPrintBean.seanceEmploiService] Setter
	 * 
	 * @author BELDI Jamel on : 5 nov. 2014 17:06:28
	 * @param seanceEmploiService
	 *            the seanceEmploiService to set
	 */
	public void setSeanceEmploiService(SeanceEmploiService seanceEmploiService) {
		this.seanceEmploiService = seanceEmploiService;
	}

	/**
	 * [EmploiPrintBean.periodeId] Getter 
	 * @author BELDI Jamel on : 5 nov. 2014  17:48:27
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [EmploiPrintBean.periodeId] Setter 
	 * @author BELDI Jamel on : 5 nov. 2014  17:48:27
	 * @param periodeId the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [EmploiPrintBean.periodeService] Getter 
	 * @author BELDI Jamel on : 5 nov. 2014  17:49:13
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [EmploiPrintBean.periodeService] Setter 
	 * @author BELDI Jamel on : 5 nov. 2014  17:49:13
	 * @param periodeService the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [EmploiPrintBean.nomenclatureService] Getter 
	 * @author BELDI Jamel on : 5 nov. 2014  19:02:03
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [EmploiPrintBean.nomenclatureService] Setter 
	 * @author BELDI Jamel on : 5 nov. 2014  19:02:03
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [EmploiPrintBean.groupePedagogiqueService] Getter 
	 * @author BELDI Jamel on : 5 nov. 2014  19:04:55
	 * @return the groupePedagogiqueService
	 */
	public GroupePedagogiqueService getGroupePedagogiqueService() {
		return groupePedagogiqueService;
	}

	/**
	 * [EmploiPrintBean.groupePedagogiqueService] Setter 
	 * @author BELDI Jamel on : 5 nov. 2014  19:04:55
	 * @param groupePedagogiqueService the groupePedagogiqueService to set
	 */
	public void setGroupePedagogiqueService(
			GroupePedagogiqueService groupePedagogiqueService) {
		this.groupePedagogiqueService = groupePedagogiqueService;
	}

	
	
}
