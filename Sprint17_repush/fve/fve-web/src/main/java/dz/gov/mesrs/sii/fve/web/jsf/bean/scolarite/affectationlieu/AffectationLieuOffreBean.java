package dz.gov.mesrs.sii.fve.web.jsf.bean.scolarite.affectationlieu;

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

import org.primefaces.event.RowEditEvent;

import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.AffectationLieuStructureDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.PlageHoraireDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.AffectationLieuStructureService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.CelluleHoraireService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.PlageHoraireService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.JourDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.service.JourService;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefLieuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;

/**
 * 
 * @author BELDI Jamel on : 8 oct. 2014 15:45:40
 */
@ManagedBean(name = "affectationLieuOffreBean")
@ViewScoped
public class AffectationLieuOffreBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	private ResourceBundle bundleCommon;
	@ManagedProperty("#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{affectationLieuStructureService}")
	private AffectationLieuStructureService affectationLieuStructureService;
	@ManagedProperty("#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	@ManagedProperty("#{jourService}")
	private JourService jourService;
	@ManagedProperty("#{plageHoraireService}")
	private PlageHoraireService plageHoraireService;
	@ManagedProperty("#{refStructureServiceImpl}")
	private RefStructureService refStructureService;
	@ManagedProperty("#{refLieuServiceImpl}")
	private RefLieuService refLieuService;
	@ManagedProperty("#{celluleHoraireService}")
	private CelluleHoraireService celluleHoraireService;
	@ManagedProperty("#{refAffectationServiceImpl}")
	private RefAffectationService refAffectationService;
	private AffectationLieuStructureDto affectationLieuStructureDto;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> periodiciteList;
	private List<SelectItem> periodeList;
	private List<SelectItem> jourList;
	private List<SelectItem> plageHoraireList;
	private List<SelectItem> ouvertureOfList;
	private List<AffectationLieuStructureDto> affectationLieuStructureList;
	private AffectationLieuStructureDto searchDto;
	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	private List<SelectItem> structureList;

	/**
	 * 
	 * [AffectationLieuStructureBean.AffectationLieuStructureBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 8 oct. 2014 15:45:54
	 */
	public AffectationLieuOffreBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
				CursusConstants.COMMON_BUNDLE_MSG_NAME);

	}

	/**
	 * 
	 * [PlanningSessionSaveBean.init] Method
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:56
	 */
	@PostConstruct
	public void init() {
		searchDto = new AffectationLieuStructureDto();
		affectationLieuStructureList = new ArrayList<AffectationLieuStructureDto>();
		loadAnneeAcademique();
		loadPeriodiciteList();
		loadJourList();
		loadPlageHoraireList();
		loadOuvertureOfList();
		loadStructuresList();

	}

	/**
	 * 
	 * [AffectationLieuStructureBean.loadAnneeAcademique] Method
	 * 
	 * @author BELDI Jamel on : 8 oct. 2014 18:19:52
	 */
	public void loadAnneeAcademique() {
		try {
			anneeAcademiqueList = new ArrayList<SelectItem>();
			List<AnneeAcademiqueDto> list = anneeAcademiqueService.findAll();
			for (AnneeAcademiqueDto annee : list) {
				SelectItem item = new SelectItem(annee.getId(), annee.getCode());
				anneeAcademiqueList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [AffectationLieuStructureBean.loadPeriodiciteList] Method
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 18:05:03
	 */
	private void loadPeriodiciteList() {
		try {
			periodiciteList = new ArrayList<SelectItem>();
			List<NomenclatureDto> list = nomenclatureService.findByName(CursusConstants.NC_PERIODICITE);
			for (NomenclatureDto periodicite : list) {
				SelectItem item = new SelectItem(periodicite.getId(), periodicite.getLibelleLongFr());
				periodiciteList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * [AffectationLieuStructureBean.loadJourList] Method
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 10:25:49
	 */
	private void loadJourList() {
		try {
			jourList = new ArrayList<SelectItem>();
			List<JourDto> list = jourService.findAll();
			for (JourDto jour : list) {
				SelectItem item = new SelectItem(jour.getId(), jour.getLibelleFr());
				jourList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [AffectationLieuStructureBean.loadPlageHoraireList] Method
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 10:25:53
	 */
	private void loadPlageHoraireList() {
		try {
			plageHoraireList = new ArrayList<SelectItem>();
			List<PlageHoraireDto> list = plageHoraireService.findAll();
			for (PlageHoraireDto plageHoraire : list) {
				SelectItem item = new SelectItem(plageHoraire.getId(), plageHoraire.getLibelleFr());
				plageHoraireList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [AffectationLieuStructureBean.loadPeriodeByPeriodicite] Method
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 09:14:38
	 */
	private void loadPeriodeByPeriodicite(Integer periodiciteId) {
		try {
			List<NomenclatureDto> list = nomenclatureService.findByReference(
					CursusConstants.NC_PERIODE_PAR_PERIODICITE, periodiciteId);
			periodeList = new ArrayList<SelectItem>();
			for (NomenclatureDto periode : list) {
				SelectItem item = new SelectItem(periode.getId(), periode.getLibelleLongFr());
				periodeList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [AffectationLieuOffreBean.loadOuvertureOfList] Method
	 * 
	 * @author BELDI Jamel on : 13 oct. 2014 17:53:12
	 */
	private void loadOuvertureOfList() {
		try {
			ouvertureOfList = new ArrayList<SelectItem>();
			OuvertureOffreFormationDto _searchDto = new OuvertureOffreFormationDto();
			_searchDto.setAnneeAcademiqueId(sessionBean.getIdAnneeAcademique());
			_searchDto.setIdEtablissement(sessionBean.getIdEtablissement());
			_searchDto.setEstOuverte(true);
			List<OuvertureOffreFormationDto> offresOuverts = ouvertureOffreFormationService.findAdvanced(_searchDto);
			for (OuvertureOffreFormationDto oof : offresOuverts) {
				SelectItem item = new SelectItem(oof.getId(), oof.getOfLibelleLongFr());
				ouvertureOfList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [AffectationLieuOffreBean.loadStructuresList] Method
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 09:51:55
	 */
	private void loadStructuresList() {
		try {
			structureList = new ArrayList<SelectItem>();

			// TODO utilisation sessionBean.getSessionBean() ?
			List<RefAffectationDto> affectations = refAffectationService.findStructuresByIndividuAndRole(
					sessionBean.getSessionBean().getUser().getId(),
					nomenclatureService.findByCode(CursusConstants.NC_ROLE_NAME,
							CursusConstants.ROLE_GESTIONNAIRE_SALLE_CODE).getId());
			for (RefAffectationDto aff : affectations) {
				SelectItem item = new SelectItem(aff.getIdStructure(), aff.getLlStructureLatin());
				structureList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [AffectationLieuStructureBean.periodiciteChanged] Method
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 09:16:03
	 */
	public void periodiciteChanged() {
		loadPeriodeByPeriodicite(affectationLieuStructureDto.getPeriodiciteId());
	}

	public void periodiciteSChanged() {
		loadPeriodeByPeriodicite(searchDto.getPeriodiciteId());
	}

	/**
	 * 
	 * [AffectationLieuStructureBean.searchAffectationLieuStructureList] Method
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 15:33:59
	 */
	public void searchAffectationLieuStructureList() {
		FacesMessage msg = new FacesMessage();

		try {
			affectationLieuStructureDto = null;
			if (searchDto == null) {
				return;
			}

			affectationLieuStructureList = affectationLieuStructureService.findAdvanced(searchDto);

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
	 * [AffectationLieuOffreBean.onRowEdit] Method
	 * 
	 * @author BELDI Jamel on : 13 oct. 2014 16:50:30
	 * @param event
	 */
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage();
		String summary = "msg_success_modification";
		try {
			AffectationLieuStructureDto element = ((AffectationLieuStructureDto) event.getObject());
			affectationLieuStructureService.insertOrUpdate(element);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);

			msg.setSummary(bundleCommon.getString("msg_success") + ": " + bundleCommon.getString(summary));
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
	 * [AffectationLieuOffreBean.onRowCancel] Method
	 * 
	 * @author BELDI Jamel on : 13 oct. 2014 16:50:36
	 * @param event
	 */
	public void onRowCancel(RowEditEvent event) {

	}

	// getter et setter

	/**
	 * [AffectationLieuStructureBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 8 oct. 2014 15:47:56
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [AffectationLieuStructureBean.periodiciteList] Getter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @return the periodiciteList
	 */
	public List<SelectItem> getPeriodiciteList() {
		return periodiciteList;
	}

	/**
	 * [AffectationLieuStructureBean.periodiciteList] Setter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @param periodiciteList
	 *            the periodiciteList to set
	 */
	public void setPeriodiciteList(List<SelectItem> periodiciteList) {
		this.periodiciteList = periodiciteList;
	}

	/**
	 * [AffectationLieuStructureBean.periodeList] Getter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @return the periodeList
	 */
	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	/**
	 * [AffectationLieuStructureBean.periodeList] Setter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @param periodeList
	 *            the periodeList to set
	 */
	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	/**
	 * [AffectationLieuStructureBean.jourList] Getter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @return the jourList
	 */
	public List<SelectItem> getJourList() {
		return jourList;
	}

	/**
	 * [AffectationLieuStructureBean.jourList] Setter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @param jourList
	 *            the jourList to set
	 */
	public void setJourList(List<SelectItem> jourList) {
		this.jourList = jourList;
	}

	/**
	 * [AffectationLieuStructureBean.plageHoraireList] Getter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @return the plageHoraireList
	 */
	public List<SelectItem> getPlageHoraireList() {
		return plageHoraireList;
	}

	/**
	 * [AffectationLieuStructureBean.plageHoraireList] Setter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @param plageHoraireList
	 *            the plageHoraireList to set
	 */
	public void setPlageHoraireList(List<SelectItem> plageHoraireList) {
		this.plageHoraireList = plageHoraireList;
	}

	/**
	 * [AffectationLieuStructureBean.affectationLieuStructureDto] Getter
	 * 
	 * @author BELDI Jamel on : 8 oct. 2014 17:55:57
	 * @return the affectationLieuStructureDto
	 */
	public AffectationLieuStructureDto getAffectationLieuStructureDto() {
		return affectationLieuStructureDto;
	}

	/**
	 * [AffectationLieuStructureBean.affectationLieuStructureDto] Setter
	 * 
	 * @author BELDI Jamel on : 8 oct. 2014 17:55:57
	 * @param affectationLieuStructureDto
	 *            the affectationLieuStructureDto to set
	 */
	public void setAffectationLieuStructureDto(AffectationLieuStructureDto affectationLieuStructureDto) {
		this.affectationLieuStructureDto = affectationLieuStructureDto;
	}

	/**
	 * [AffectationLieuStructureBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 8 oct. 2014 15:47:56
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [AffectationLieuStructureBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 8 oct. 2014 15:47:56
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [AffectationLieuStructureBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 8 oct. 2014 15:47:56
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [AffectationLieuStructureBean.anneeAcademiqueList] Getter
	 * 
	 * @author BELDI Jamel on : 8 oct. 2014 18:12:49
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [AffectationLieuStructureBean.anneeAcademiqueList] Setter
	 * 
	 * @author BELDI Jamel on : 8 oct. 2014 18:12:49
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [AffectationLieuStructureBean.affectationLieuStructureService] Getter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:45:33
	 * @return the affectationLieuStructureService
	 */
	public AffectationLieuStructureService getAffectationLieuStructureService() {
		return affectationLieuStructureService;
	}

	/**
	 * [AffectationLieuStructureBean.affectationLieuStructureService] Setter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:45:33
	 * @param affectationLieuStructureService
	 *            the affectationLieuStructureService to set
	 */
	public void setAffectationLieuStructureService(AffectationLieuStructureService affectationLieuStructureService) {
		this.affectationLieuStructureService = affectationLieuStructureService;
	}

	/**
	 * [AffectationLieuStructureBean.nomenclatureService] Getter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 18:07:27
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [AffectationLieuStructureBean.nomenclatureService] Setter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 18:07:27
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [AffectationLieuStructureBean.jourService] Getter
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 09:39:56
	 * @return the jourService
	 */
	public JourService getJourService() {
		return jourService;
	}

	/**
	 * [AffectationLieuStructureBean.jourService] Setter
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 09:39:56
	 * @param jourService
	 *            the jourService to set
	 */
	public void setJourService(JourService jourService) {
		this.jourService = jourService;
	}

	/**
	 * [AffectationLieuStructureBean.plageHoraireService] Getter
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 09:49:07
	 * @return the plageHoraireService
	 */
	public PlageHoraireService getPlageHoraireService() {
		return plageHoraireService;
	}

	/**
	 * [AffectationLieuStructureBean.plageHoraireService] Setter
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 09:49:07
	 * @param plageHoraireService
	 *            the plageHoraireService to set
	 */
	public void setPlageHoraireService(PlageHoraireService plageHoraireService) {
		this.plageHoraireService = plageHoraireService;
	}

	/**
	 * [AffectationLieuStructureBean.refStructureService] Getter
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 11:11:27
	 * @return the refStructureService
	 */
	public RefStructureService getRefStructureService() {
		return refStructureService;
	}

	/**
	 * [AffectationLieuStructureBean.refStructureService] Setter
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 11:11:27
	 * @param refStructureService
	 *            the refStructureService to set
	 */
	public void setRefStructureService(RefStructureService refStructureService) {
		this.refStructureService = refStructureService;
	}

	/**
	 * [AffectationLieuStructureBean.refLieuService] Getter
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 11:11:27
	 * @return the refLieuService
	 */
	public RefLieuService getRefLieuService() {
		return refLieuService;
	}

	/**
	 * [AffectationLieuStructureBean.refLieuService] Setter
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 11:11:27
	 * @param refLieuService
	 *            the refLieuService to set
	 */
	public void setRefLieuService(RefLieuService refLieuService) {
		this.refLieuService = refLieuService;
	}

	/**
	 * [AffectationLieuStructureBean.celluleHoraireService] Getter
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 11:48:23
	 * @return the celluleHoraireService
	 */
	public CelluleHoraireService getCelluleHoraireService() {
		return celluleHoraireService;
	}

	/**
	 * [AffectationLieuStructureBean.celluleHoraireService] Setter
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 11:48:23
	 * @param celluleHoraireService
	 *            the celluleHoraireService to set
	 */
	public void setCelluleHoraireService(CelluleHoraireService celluleHoraireService) {
		this.celluleHoraireService = celluleHoraireService;
	}

	/**
	 * [AffectationLieuStructureBean.affectationLieuStructureList] Getter
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 15:25:17
	 * @return the affectationLieuStructureList
	 */
	public List<AffectationLieuStructureDto> getAffectationLieuStructureList() {
		return affectationLieuStructureList;
	}

	/**
	 * [AffectationLieuStructureBean.affectationLieuStructureList] Setter
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 15:25:17
	 * @param affectationLieuStructureList
	 *            the affectationLieuStructureList to set
	 */
	public void setAffectationLieuStructureList(List<AffectationLieuStructureDto> affectationLieuStructureList) {
		this.affectationLieuStructureList = affectationLieuStructureList;
	}

	/**
	 * [AffectationLieuStructureBean.searchDto] Getter
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 15:27:15
	 * @return the searchDto
	 */
	public AffectationLieuStructureDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [AffectationLieuStructureBean.searchDto] Setter
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 15:27:15
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(AffectationLieuStructureDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * [AffectationLieuOffreBean.ouvertureOfList] Getter
	 * 
	 * @author BELDI Jamel on : 13 oct. 2014 17:54:41
	 * @return the ouvertureOfList
	 */
	public List<SelectItem> getOuvertureOfList() {
		return ouvertureOfList;
	}

	/**
	 * [AffectationLieuOffreBean.ouvertureOfList] Setter
	 * 
	 * @author BELDI Jamel on : 13 oct. 2014 17:54:41
	 * @param ouvertureOfList
	 *            the ouvertureOfList to set
	 */
	public void setOuvertureOfList(List<SelectItem> ouvertureOfList) {
		this.ouvertureOfList = ouvertureOfList;
	}

	/**
	 * [AffectationLieuOffreBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 13 oct. 2014 17:54:41
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [AffectationLieuOffreBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 13 oct. 2014 17:54:41
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [AffectationLieuOffreBean.structureList] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 09:51:15
	 * @return the structureList
	 */
	public List<SelectItem> getStructureList() {
		return structureList;
	}

	/**
	 * [AffectationLieuOffreBean.structureList] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 09:51:15
	 * @param structureList
	 *            the structureList to set
	 */
	public void setStructureList(List<SelectItem> structureList) {
		this.structureList = structureList;
	}

	/**
	 * [AffectationLieuOffreBean.loginBean] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 10:32:26
	 * @return the loginBean
	 */
	public LoginBean getLoginBean() {
		return loginBean;
	}

	/**
	 * [AffectationLieuOffreBean.loginBean] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 10:32:26
	 * @param loginBean
	 *            the loginBean to set
	 */
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	/**
	 * [AffectationLieuOffreBean.refAffectationService] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 10:32:35
	 * @return the refAffectationService
	 */
	public RefAffectationService getRefAffectationService() {
		return refAffectationService;
	}

	/**
	 * [AffectationLieuOffreBean.refAffectationService] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 10:32:35
	 * @param refAffectationService
	 *            the refAffectationService to set
	 */
	public void setRefAffectationService(RefAffectationService refAffectationService) {
		this.refAffectationService = refAffectationService;
	}

}
