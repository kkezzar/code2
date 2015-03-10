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

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.AffectationLieuStructureDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.CelluleHoraireDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.PlageHoraireDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.AffectationLieuStructureService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.CelluleHoraireService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.PlageHoraireService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.JourDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefLieuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;
import dz.gov.mesrs.sii.referentiel.business.service.JourService;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefLieuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;

/**
 * 
 * @author BELDI Jamel on : 8 oct. 2014 15:45:40
 */
@ManagedBean(name = "affectationLieuStructureBean")
@ViewScoped
public class AffectationLieuStructureBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
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
	private AffectationLieuStructureDto affectationLieuStructureDto;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> periodiciteList;
	private List<SelectItem> periodeList;
	private List<SelectItem> jourList;
	private List<SelectItem> plageHoraireList;
	private List<SelectItem> typeLieuList;
	private List<SelectItem> lieuList;
	private List<SelectItem> typeStructureList;
	private List<SelectItem> structureList;
	private List<AffectationLieuStructureDto> affectationLieuStructureList;
	private AffectationLieuStructureDto searchDto;
	private boolean hideDetail;

	/**
	 * 
	 * [AffectationLieuStructureBean.AffectationLieuStructureBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 8 oct. 2014 15:45:54
	 */
	public AffectationLieuStructureBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);

	}

	/**
	 * 
	 * [PlanningSessionSaveBean.init] Method
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:53:56
	 */
	@PostConstruct
	public void init() {
		//affectationLieuStructureDto = new AffectationLieuStructureDto();
		searchDto = new AffectationLieuStructureDto();
		searchDto.setRefCodeEtablissement(sessionBean.getCodeEtablissement());
		affectationLieuStructureList = new ArrayList<AffectationLieuStructureDto>();
		loadAnneeAcademique();
		loadPeriodiciteList();
		loadJourList();
		loadPlageHoraireList();
		loadTypeLieuList();
		loadTypeStructureList();
		
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
			List<NomenclatureDto> list = nomenclatureService
					.findByName(CursusConstants.NC_PERIODICITE);
			for (NomenclatureDto periodicite : list) {
				SelectItem item = new SelectItem(periodicite.getId(),
						periodicite.getLibelleLongFr());
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
				SelectItem item = new SelectItem(jour.getId(),
						jour.getLibelleFr());
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
				SelectItem item = new SelectItem(plageHoraire.getId(),
						plageHoraire.getLibelleFr());
				plageHoraireList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [AffectationLieuStructureBean.loadTypeLieuList] Method
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 10:37:53
	 */
	private void loadTypeLieuList() {
		try {
			typeLieuList = new ArrayList<SelectItem>();
			List<NomenclatureDto> list = nomenclatureService
					.findByName(CursusConstants.NC_TYPE_LIEU_NAME);
			for (NomenclatureDto typeLieu : list) {
				SelectItem item = new SelectItem(typeLieu.getId(),
						typeLieu.getLibelleLongFr());
				typeLieuList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * [AffectationLieuStructureBean.loadTypeStructureList] Method
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 10:37:56
	 */
	private void loadTypeStructureList() {
		try {
			typeStructureList = new ArrayList<SelectItem>();
			List<NomenclatureDto> list = nomenclatureService
					.findByName(CursusConstants.NC_TYPE_STUCTURE_NAME);
			for (NomenclatureDto typeStructure : list) {
				SelectItem item = new SelectItem(typeStructure.getId(),
						typeStructure.getLibelleLongFr());
				typeStructureList.add(item);
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
					CursusConstants.NC_PERIODE_PAR_PERIODICITE,
					periodiciteId);
			periodeList = new ArrayList<SelectItem>();
			for (NomenclatureDto periode : list) {
				SelectItem item = new SelectItem(periode.getId(),
						periode.getLibelleLongFr());
				periodeList.add(item);
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
	 * [AffectationLieuStructureBean.TypeLieuChanged] Method
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 10:54:09
	 */
	public void typeLieuChanged() {
		loadLieuList();
	}

	/**
	 * 
	 * [AffectationLieuStructureBean.typeStructureChanged] Method
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 11:01:02
	 */
	public void typeStructureChanged() {
		loadStructureList();
	}

	/**
	 * 
	 * [AffectationLieuStructureBean.loadLieuList] Method
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 11:07:54
	 */
	private void loadLieuList() {
		try {
			lieuList = new ArrayList<SelectItem>();
			RefLieuDto _search = new RefLieuDto();
			_search.setTypelieuId(affectationLieuStructureDto
					.getRefLieuTypeId());
			List<RefLieuDto> list = refLieuService.findAdvanced(
					sessionBean.getIdEtablissement(), _search);
			for (RefLieuDto lieu : list) {
				SelectItem item = new SelectItem(lieu.getId(),
						lieu.getDesignation());
				lieuList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [AffectationLieuStructureBean.loadStructureList] Method
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 11:08:13
	 */
	private void loadStructureList() {
		try {
			structureList = new ArrayList<SelectItem>();
			RefStructureDto _search = new RefStructureDto();
			_search.setTypeId(affectationLieuStructureDto
					.getRefStructureTypeId());
			List<RefStructureDto> list = refStructureService.findAdvanced(
					sessionBean.getIdEtablissement(), _search);
			for (RefStructureDto structure : list) {
				SelectItem item = new SelectItem(structure.getId(),
						structure.getLlStructureLatin());
				structureList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * [AffectationLieuStructureBean.addAffectationLieuStructure] Method
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:42:28
	 */
	public void addAffectationLieuStructure() {
		affectationLieuStructureDto = new AffectationLieuStructureDto();
		hideDetail=false;

	}
	
	public void annuler() {
		affectationLieuStructureDto = null;
		

	}
	/**
	 * 
	 * [AffectationLieuStructureBean.saveAffectationLieuStructure] Method
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:42:51
	 */
	public void saveAffectationLieuStructure() {
		FacesMessage msg = new FacesMessage();

		String summary = "msg_success_enregistrement";
		boolean nouveau = false;
		try {
			if (affectationLieuStructureDto == null) {
				return;
			}

			CelluleHoraireDto _celluleHoraireDto = celluleHoraireService
					.findByJourAndPlageHoraire(
							affectationLieuStructureDto.getJourId(),
							affectationLieuStructureDto.getPlageHoraireId());
			if (_celluleHoraireDto == null) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleCommon.getString("error_echec")
						+ ": "
						+ " la combinaison Jour et Plage Horaire ne correspond pas à une cellule Horaire");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			affectationLieuStructureDto.setCelluleHoraireId(_celluleHoraireDto
					.getId());
			
		
			if (affectationLieuStructureDto.getId() == 0) {
				AffectationLieuStructureDto _searchCritaria = new AffectationLieuStructureDto();
				_searchCritaria.setAnneeAcademiqueId(affectationLieuStructureDto.getAnneeAcademiqueId());
				_searchCritaria.setPeriodeId(affectationLieuStructureDto.getPeriodeId());
				_searchCritaria.setJourId(affectationLieuStructureDto.getJourId());
				_searchCritaria.setRefLieuId(affectationLieuStructureDto.getRefLieuId());
				_searchCritaria.setCelluleHoraireId(affectationLieuStructureDto.getCelluleHoraireId());
				if(!verifyDisponibility(_searchCritaria)){
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundleCommon.getString("error_echec")
							+ ": "
							+ " Ce lieu est reservé pour la même plage Horaire pour une autre structure");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
				// affectationLieuStructureDto.setDateCreation(new Date());
				nouveau = true;

			} else {
				summary = "msg_success_modification";

			}

			affectationLieuStructureDto = affectationLieuStructureService
					.insertOrUpdate(affectationLieuStructureDto);
           searchAffectationLieuStructureList();
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
	 * [AffectationLieuStructureBean.deleteAffectationLieuStructure] Method
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:42:58
	 */
	public void deleteAffectationLieuStructure() {
		FacesMessage msg = new FacesMessage();

		try {
			if (affectationLieuStructureDto == null) {
				return;
			}

			if (affectationLieuStructureDto.getId() != 0) {
				affectationLieuStructureService
						.remove(affectationLieuStructureDto);
				affectationLieuStructureDto = null;
				searchAffectationLieuStructureList();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);

				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ bundleCommon.getString("msg_success_suppression"));
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

	/**
	 * 
	 * [AffectationLieuStructureBean.searchAffectationLieuStructureList] Method
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 15:33:59
	 */
	public void searchAffectationLieuStructureList() {
		FacesMessage msg = new FacesMessage();

		try {
			affectationLieuStructureDto	 = null;
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
 * [AffectationLieuStructureBean.verifyDisponibility] Method 
 * @author BELDI Jamel  on : 13 oct. 2014  11:48:41
 * @param searchDto
 * @return
 */
private boolean verifyDisponibility(AffectationLieuStructureDto searchCritaria){
	List list = affectationLieuStructureService.findAdvanced(searchCritaria);
	if(list!=null && !list.isEmpty()){
		return false;
	}
	
	return true;
}
	
	/**
	 * 
	 * [AffectationLieuStructureBean.onRowSelect] Method
	 * 
	 * @author BELDI Jamel on : 12 oct. 2014 15:37:26
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		affectationLieuStructureDto = ((AffectationLieuStructureDto) event
				.getObject());
		loadPeriodeByPeriodicite(affectationLieuStructureDto.getPeriodiciteId());
		loadLieuList();
		loadStructureList();

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
	 * [AffectationLieuStructureBean.typeLieuList] Getter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @return the typeLieuList
	 */
	public List<SelectItem> getTypeLieuList() {
		return typeLieuList;
	}

	/**
	 * [AffectationLieuStructureBean.typeLieuList] Setter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @param typeLieuList
	 *            the typeLieuList to set
	 */
	public void setTypeLieuList(List<SelectItem> typeLieuList) {
		this.typeLieuList = typeLieuList;
	}

	/**
	 * [AffectationLieuStructureBean.lieuList] Getter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @return the lieuList
	 */
	public List<SelectItem> getLieuList() {
		return lieuList;
	}

	/**
	 * [AffectationLieuStructureBean.lieuList] Setter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @param lieuList
	 *            the lieuList to set
	 */
	public void setLieuList(List<SelectItem> lieuList) {
		this.lieuList = lieuList;
	}

	/**
	 * [AffectationLieuStructureBean.typeStructureList] Getter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @return the typeStructureList
	 */
	public List<SelectItem> getTypeStructureList() {
		return typeStructureList;
	}

	/**
	 * [AffectationLieuStructureBean.typeStructureList] Setter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @param typeStructureList
	 *            the typeStructureList to set
	 */
	public void setTypeStructureList(List<SelectItem> typeStructureList) {
		this.typeStructureList = typeStructureList;
	}

	/**
	 * [AffectationLieuStructureBean.structureList] Getter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @return the structureList
	 */
	public List<SelectItem> getStructureList() {
		return structureList;
	}

	/**
	 * [AffectationLieuStructureBean.structureList] Setter
	 * 
	 * @author BELDI Jamel on : 9 oct. 2014 17:18:34
	 * @param structureList
	 *            the structureList to set
	 */
	public void setStructureList(List<SelectItem> structureList) {
		this.structureList = structureList;
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
	public void setAffectationLieuStructureDto(
			AffectationLieuStructureDto affectationLieuStructureDto) {
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
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
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
	public void setAffectationLieuStructureService(
			AffectationLieuStructureService affectationLieuStructureService) {
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
	public void setCelluleHoraireService(
			CelluleHoraireService celluleHoraireService) {
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
	public void setAffectationLieuStructureList(
			List<AffectationLieuStructureDto> affectationLieuStructureList) {
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
	 * [AffectationLieuStructureBean.hideDetail] Getter 
	 * @author BELDI Jamel on : 12 oct. 2014  17:13:07
	 * @return the hideDetail
	 */
	public boolean isHideDetail() {
		return hideDetail;
	}

	/**
	 * [AffectationLieuStructureBean.hideDetail] Setter 
	 * @author BELDI Jamel on : 12 oct. 2014  17:13:07
	 * @param hideDetail the hideDetail to set
	 */
	public void setHideDetail(boolean hideDetail) {
		this.hideDetail = hideDetail;
	}


	
	
	
}
