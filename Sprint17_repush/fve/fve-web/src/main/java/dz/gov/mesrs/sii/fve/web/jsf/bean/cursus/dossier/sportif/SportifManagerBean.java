/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.sportif.SportifManagerBean.java] 
 * @author MAKERRI Sofiane on : 29 mai 2014  15:40:00
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.sportif;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SituationSportiveDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.SituationSportiveService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.DossierEtudiantBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;


/**
 * @author MAKERRI Sofiane  on : 29 mai 2014  15:40:00
 */
/**
 * @author MAKERRI Sofiane  on : 29 mai 2014  15:44:33
 */
@ManagedBean(name = "sportifManagerBean")
@RequestScoped
public class SportifManagerBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 29 mai 2014  15:40:27
	 */
	private static final long serialVersionUID = 1L;
	private ResourceBundle bundleCommon;
	private ResourceBundle bundleSportif;
	@ManagedProperty("#{param.dossierEtudiantId}")
	private String dossierEtudiantId;
	private Integer id_DossierEtudiant;
	@ManagedProperty("#{param.situationId}")
	private String situationId;
	private Integer id_Situation;
	private static final Log log = LogFactory.getLog(SportifManagerBean.class);
	private SituationSportiveDto situationSportiveDto;
	private List<SituationSportiveDto> listSituationSportiveDto;
	@ManagedProperty("#{situationSportiveService}")
	private SituationSportiveService situationSportiveService;
	private SituationSportiveDto selectedSituation;
	private List<SelectItem> listDisciplineSportive;
	private boolean entityChange;
	@ManagedProperty("#{dossierEtudiantBean}")
	private DossierEtudiantBean dossierEtudiantBean;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	
	/**
	 * [SportifManagerBean.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  15:21:16
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [SportifManagerBean.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  15:21:16
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [SportifManagerBean.SportifManagerBean()] Constructor
	 * @author MAKERRI Sofiane  on : 29 mai 2014  15:40:00	
	 */
	public SportifManagerBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		bundleSportif = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.SITUATION_SPORTIVE_BUNDLE_MSG_NAME);
	}
	
	@PostConstruct
	public void init() {
		try {
			loadSituationSportive();
			List<NomenclatureDto> list = nomenclatureService
					.findByName(CursusConstants.NC_DISCIPLINE_SPORTIVE);
			listDisciplineSportive = new ArrayList<SelectItem>();
			for (NomenclatureDto nomenclatureDto : list) {
				SelectItem selectItem = new SelectItem(
						nomenclatureDto.getCode(),
						nomenclatureDto.getLibelleLongFr());
				listDisciplineSportive.add(selectItem);
			}
			selectedSituation = new SituationSportiveDto();
			selectedSituation.setIdDossierEtudiant(id_DossierEtudiant);

		} catch (Exception e) {

		}
	}
	
	
	/**
	 * [SportifManagerBean.loadSituationSportive] Method 
	 * @author MAKERRI Sofiane  on : 29 mai 2014  15:50:39
	 */
	public void loadSituationSportive() {
		try {
			if (id_DossierEtudiant != null) {
				listSituationSportiveDto = situationSportiveService
						.findByIdDossier(id_DossierEtudiant);
				for (SituationSportiveDto current : listSituationSportiveDto) {

					NomenclatureDto disciplineSportive = nomenclatureService
							.findByCode(CursusConstants.NC_DISCIPLINE_SPORTIVE,
									current.getRefCodeDisciplineSportive());
					if (disciplineSportive != null) {
						current.setRefLlLatinDisciplineSportive(disciplineSportive
								.getLibelleLongFr());
						current.setRefLlArabeDisciplineSportive(disciplineSportive
								.getLibelleLongAr());
					}

				}
			}
			if (situationSportiveDto == null) {
				situationSportiveDto = new SituationSportiveDto();
			}

		} catch (Exception e) {

		}
	}

	/**
	 * [SportifManagerBean.dossierEtudiantId] Getter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:42:01
	 * @return the dossierEtudiantId
	 */
	public String getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [SportifManagerBean.dossierEtudiantId] Setter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:42:01
	 * @param dossierEtudiantId the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(String dossierEtudiantId) {
		if (dossierEtudiantId != null && dossierEtudiantId.equals("null")) {
			this.dossierEtudiantId = null;
		} else {
			this.dossierEtudiantId = dossierEtudiantId;
			try {
				setId_DossierEtudiant(Integer.parseInt(dossierEtudiantId));
			} catch (Exception e) {

			}
		}
	}

	/**
	 * [SportifManagerBean.id_DossierEtudiant] Getter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:42:01
	 * @return the id_DossierEtudiant
	 */
	public Integer getId_DossierEtudiant() {
		return id_DossierEtudiant;
	}

	/**
	 * [SportifManagerBean.id_DossierEtudiant] Setter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:42:01
	 * @param id_DossierEtudiant the id_DossierEtudiant to set
	 */
	public void setId_DossierEtudiant(Integer id_DossierEtudiant) {
		this.id_DossierEtudiant = id_DossierEtudiant;
	}

	/**
	 * [SportifManagerBean.situationId] Getter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:42:01
	 * @return the situationId
	 */
	public String getSituationId() {
		return situationId;
	}

	/**
	 * [SportifManagerBean.situationId] Setter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:42:01
	 * @param situationId the situationId to set
	 */
	public void setSituationId(String situationId) {
		if (situationId != null && situationId.equals("null")) {
			this.situationId = null;
		} else {
			this.situationId = situationId;
			try {
				setId_Situation(Integer.parseInt(situationId));
			} catch (Exception e) {

			}
		}
	}

	/**
	 * [SportifManagerBean.id_Situation] Getter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:42:01
	 * @return the id_Situation
	 */
	public Integer getId_Situation() {
		return id_Situation;
	}

	/**
	 * [SportifManagerBean.id_Situation] Setter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:42:01
	 * @param id_Situation the id_Situation to set
	 */
	public void setId_Situation(Integer id_Situation) {
		this.id_Situation = id_Situation;
	}

	/**
	 * [SportifManagerBean.entityChange] Getter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:42:01
	 * @return the entityChange
	 */
	public boolean isEntityChange() {
		return entityChange;
	}

	/**
	 * [SportifManagerBean.entityChange] Setter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:42:01
	 * @param entityChange the entityChange to set
	 */
	public void setEntityChange(boolean entityChange) {
		this.entityChange = entityChange;
	}
	
	/**
	 * [SportifManagerBean.editSituation] Method 
	 * @author MAKERRI Sofiane  on : 29 mai 2014  15:43:39
	 * @param current
	 */
	public void editSituation(SituationSportiveDto current) {
		selectedSituation = current;
		entityChange = false;
		setSituationId(selectedSituation.getId().toString());
	}
	
	
	
	/**
	 * [SportifManagerBean.removeSituation] Method 
	 * @author MAKERRI Sofiane  on : 29 mai 2014  15:43:55
	 * @param current
	 */
	public void removeSituation(SituationSportiveDto current) {
		FacesMessage msg = new FacesMessage();
		try {
			if (current == null) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundleCommon.getString("warn_info") + ": "
						+ bundleSportif.getString("handicap_select_situation"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			situationSportiveService.remove(current);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_suppression"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			loadSituationSportive();
			setEntityChange(false);
			setId_Situation(null);

		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
	}
	
	
	/**
	 * [SportifManagerBean.entityChanged] Method 
	 * @author MAKERRI Sofiane  on : 29 mai 2014  15:44:11
	 * @param event
	 */
	public void entityChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			entityChange = true;
		}
	}
	
	
	
	
	/**
	 * [SportifManagerBean.save] Method 
	 * @author MAKERRI Sofiane  on : 29 mai 2014  15:52:06
	 */
	public void save() {
		FacesMessage msg = new FacesMessage();
		try {
			if (!entityChange) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundleCommon.getString("warn_info") + ": "
						+ bundleCommon.getString("warn_info_update_none"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			if (id_Situation == null) {
				SituationSportiveDto _SituationSportiveDto = situationSportiveService
						.findSituationSportive(id_DossierEtudiant,
								selectedSituation.getDateDebut(),
								selectedSituation.getDateFin(),
								selectedSituation.getRefCodeDisciplineSportive());
				if (_SituationSportiveDto != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundleCommon.getString("error_echec")
							+ ": "
							+ bundleSportif
									.getString("sportif_error_situation_existe_deja"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
			}
			String summary = (id_Situation == null) ? "msg_success_enregistrement"
					: "msg_success_modification";
			selectedSituation.setIdDossierEtudiant(id_DossierEtudiant);
			selectedSituation.setId(id_Situation);
			situationSportiveService.insertOrUpdate(selectedSituation);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString(summary));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			setSituationId(selectedSituation.getId().toString());
			loadSituationSportive();
			setEntityChange(false);

		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}

	}

	/**
	 * [SportifManagerBean.situationSportiveDto] Getter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:46:42
	 * @return the situationSportiveDto
	 */
	public SituationSportiveDto getSituationSportiveDto() {
		return situationSportiveDto;
	}

	/**
	 * [SportifManagerBean.situationSportiveDto] Setter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:46:42
	 * @param situationSportiveDto the situationSportiveDto to set
	 */
	public void setSituationSportiveDto(SituationSportiveDto situationSportiveDto) {
		this.situationSportiveDto = situationSportiveDto;
	}

	/**
	 * [SportifManagerBean.listSituationSportiveDto] Getter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:46:42
	 * @return the listSituationSportiveDto
	 */
	public List<SituationSportiveDto> getListSituationSportiveDto() {
		return listSituationSportiveDto;
	}

	/**
	 * [SportifManagerBean.listSituationSportiveDto] Setter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:46:42
	 * @param listSituationSportiveDto the listSituationSportiveDto to set
	 */
	public void setListSituationSportiveDto(
			List<SituationSportiveDto> listSituationSportiveDto) {
		this.listSituationSportiveDto = listSituationSportiveDto;
	}

	/**
	 * [SportifManagerBean.situationSportiveService] Getter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:46:42
	 * @return the situationSportiveService
	 */
	public SituationSportiveService getSituationSportiveService() {
		return situationSportiveService;
	}

	/**
	 * [SportifManagerBean.situationSportiveService] Setter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:46:42
	 * @param situationSportiveService the situationSportiveService to set
	 */
	public void setSituationSportiveService(
			SituationSportiveService situationSportiveService) {
		this.situationSportiveService = situationSportiveService;
	}

	/**
	 * [SportifManagerBean.selectedSituation] Getter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:46:42
	 * @return the selectedSituation
	 */
	public SituationSportiveDto getSelectedSituation() {
		return selectedSituation;
	}

	/**
	 * [SportifManagerBean.selectedSituation] Setter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:46:42
	 * @param selectedSituation the selectedSituation to set
	 */
	public void setSelectedSituation(SituationSportiveDto selectedSituation) {
		this.selectedSituation = selectedSituation;
	}

	

	/**
	 * [SportifManagerBean.listDisciplineSportive] Getter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  16:44:44
	 * @return the listDisciplineSportive
	 */
	public List<SelectItem> getListDisciplineSportive() {
		return listDisciplineSportive;
	}

	/**
	 * [SportifManagerBean.listDisciplineSportive] Setter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  16:44:44
	 * @param listDisciplineSportive the listDisciplineSportive to set
	 */
	public void setListDisciplineSportive(List<SelectItem> listDisciplineSportive) {
		this.listDisciplineSportive = listDisciplineSportive;
	}

	/**
	 * [SportifManagerBean.addSituation] Method 
	 * @author MAKERRI Sofiane  on : 29 mai 2014  16:22:26
	 */
	public void addSituation() {
		selectedSituation = new SituationSportiveDto();
	}

	/**
	 * [SportifManagerBean.dossierEtudiantBean] Getter 
	 * @author MAKERRI Sofiane on : 2 juin 2014  13:29:43
	 * @return the dossierEtudiantBean
	 */
	public DossierEtudiantBean getDossierEtudiantBean() {
		return dossierEtudiantBean;
	}

	/**
	 * [SportifManagerBean.dossierEtudiantBean] Setter 
	 * @author MAKERRI Sofiane on : 2 juin 2014  13:29:43
	 * @param dossierEtudiantBean the dossierEtudiantBean to set
	 */
	public void setDossierEtudiantBean(DossierEtudiantBean dossierEtudiantBean) {
		this.dossierEtudiantBean = dossierEtudiantBean;
		if (dossierEtudiantBean != null && dossierEtudiantId == null) {
			setDossierEtudiantId(dossierEtudiantBean.getDossierEtudiantId());
		}
	}
	
	

}
