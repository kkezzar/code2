/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.handicap.HandicapManagerBean.java] 
 * @author MAKERRI Sofiane on : 27 mai 2014  10:55:57
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.handicap;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SituationHandicapDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.SituationHandicapService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.DossierEtudiantBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;


/**
 * @author MAKERRI Sofiane on : 27 mai 2014 10:55:57
 */
@ManagedBean(name = "handicapManagerBean")
@ViewScoped
public class HandicapManagerBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 10:56:21
	 */
	private static final long serialVersionUID = 1L;
	private ResourceBundle bundleCommon;
	private ResourceBundle bundleHandicap;
	private String dossierEtudiantId;
	private Integer id_DossierEtudiant;
	private String situationId;
	private Integer id_Situation;
	private SituationHandicapDto situationHandicapDto;
	private List<SituationHandicapDto> listSituationHandicapDto;
	@ManagedProperty("#{situationHandicapService}")
	private SituationHandicapService situationHandicapService;
	private SituationHandicapDto selectedHandicap;
	private List<SelectItem> listTypeHandicap;
	private boolean entityChange;
	private static final Log log = LogFactory.getLog(HandicapManagerBean.class);
	@ManagedProperty("#{dossierEtudiantBean}")
	private DossierEtudiantBean dossierEtudiantBean;
	@ManagedProperty(value = "#{utilBean}")
	private UtilBean utilBean;
	/**
	 * [HandicapManagerBean.HandicapManagerBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 10:55:57
	 */
	public HandicapManagerBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		bundleHandicap = facesContext.getApplication().getResourceBundle(
				facesContext,
				CursusConstants.SITUATION_HANDICAP_BUNDLE_MSG_NAME);

	}

	@PostConstruct
	public void init() {
		try {
			loadSituationHandicap();
			listTypeHandicap = utilBean.loadNomenclatureLibelleItem(CursusConstants.NC_TYPE_HANDICAP);
			selectedHandicap = new SituationHandicapDto();
			selectedHandicap.setIdDossierEtudiant(id_DossierEtudiant);

		} catch (Exception e) {

		}
	}

	/**
	 * [HandicapManagerBean.loadSituationHandicap] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 14:11:21
	 */
	public void loadSituationHandicap() {
		try {
			if (id_DossierEtudiant != null) {
				listSituationHandicapDto = situationHandicapService
						.findByIdDossier(id_DossierEtudiant);
				for (SituationHandicapDto current : listSituationHandicapDto) {
					NomenclatureDto typeHandicap = utilBean.getNomenclatureItemValue(CursusConstants.NC_TYPE_HANDICAP,
									current.getRefCodeTypeHandicap());
					if (typeHandicap != null) {
						current.setRefLlLatinTypeHandicap(typeHandicap
								.getLibelleLongFr());
						current.setRefLlArabeTypeHandicap(typeHandicap
								.getLibelleLongAr());
					}

				}
			}
			if (situationHandicapDto != null) {
				situationHandicapDto = new SituationHandicapDto();
			}

		} catch (Exception e) {

		}
	}


	/**
	 * [HandicapManagerBean.dossierEtudiantId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 15:31:18
	 * @return the dossierEtudiantId
	 */
	public String getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [HandicapManagerBean.dossierEtudiantId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 15:31:18
	 * @param dossierEtudiantId
	 *            the dossierEtudiantId to set
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
	 * [HandicapManagerBean.id_DossierEtudiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 10:58:31
	 * @return the id_DossierEtudiant
	 */
	public Integer getId_DossierEtudiant() {
		return id_DossierEtudiant;
	}

	/**
	 * [HandicapManagerBean.id_DossierEtudiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 10:58:31
	 * @param id_DossierEtudiant
	 *            the id_DossierEtudiant to set
	 */
	public void setId_DossierEtudiant(Integer id_DossierEtudiant) {
		this.id_DossierEtudiant = id_DossierEtudiant;
	}

	/**
	 * [HandicapManagerBean.situationHandicapDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 11:00:11
	 * @return the situationHandicapDto
	 */
	public SituationHandicapDto getSituationHandicapDto() {
		return situationHandicapDto;
	}

	/**
	 * [HandicapManagerBean.situationHandicapDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 11:00:11
	 * @param situationHandicapDto
	 *            the situationHandicapDto to set
	 */
	public void setSituationHandicapDto(
			SituationHandicapDto situationHandicapDto) {
		this.situationHandicapDto = situationHandicapDto;
	}

	/**
	 * [HandicapManagerBean.situationHandicapService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 11:00:11
	 * @return the situationHandicapService
	 */
	public SituationHandicapService getSituationHandicapService() {
		return situationHandicapService;
	}

	/**
	 * [HandicapManagerBean.situationHandicapService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 11:00:11
	 * @param situationHandicapService
	 *            the situationHandicapService to set
	 */
	public void setSituationHandicapService(
			SituationHandicapService situationHandicapService) {
		this.situationHandicapService = situationHandicapService;
	}

	/**
	 * [HandicapManagerBean.listSituationHandicapDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 11:03:56
	 * @return the listSituationHandicapDto
	 */
	public List<SituationHandicapDto> getListSituationHandicapDto() {
		return listSituationHandicapDto;
	}

	/**
	 * [HandicapManagerBean.listSituationHandicapDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 11:03:56
	 * @param listSituationHandicapDto
	 *            the listSituationHandicapDto to set
	 */
	public void setListSituationHandicapDto(
			List<SituationHandicapDto> listSituationHandicapDto) {
		this.listSituationHandicapDto = listSituationHandicapDto;
	}

	/**
	 * [HandicapManagerBean.save] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 11:08:54
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
				SituationHandicapDto _situationHandicapDto = situationHandicapService
						.findSituationHandicap(id_DossierEtudiant,
								selectedHandicap.getDateDebut(),
								selectedHandicap.getDateFin(),
								selectedHandicap.getRefCodeTypeHandicap());
				if (_situationHandicapDto != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundleCommon.getString("error_echec")
							+ ": "
							+ bundleHandicap
									.getString("handicap_error_situation_existe_deja"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
			}
			String summary = (id_Situation == null) ? "msg_success_enregistrement"
					: "msg_success_modification";
			selectedHandicap.setIdDossierEtudiant(id_DossierEtudiant);
			selectedHandicap.setId(id_Situation);
			situationHandicapService.insertOrUpdate(selectedHandicap);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString(summary));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			setSituationId(selectedHandicap.getId().toString());
			loadSituationHandicap();
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
	 * [HandicapManagerBean.selectedHandicap] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 14:07:40
	 * @return the selectedHandicap
	 */
	public SituationHandicapDto getSelectedHandicap() {
		return selectedHandicap;
	}

	/**
	 * [HandicapManagerBean.selectedHandicap] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 14:07:40
	 * @param selectedHandicap
	 *            the selectedHandicap to set
	 */
	public void setSelectedHandicap(SituationHandicapDto selectedHandicap) {
		this.selectedHandicap = selectedHandicap;
	}

	/**
	 * [HandicapManagerBean.listTypeHandicap] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 11:34:09
	 * @return the listTypeHandicap
	 */
	public List<SelectItem> getListTypeHandicap() {
		return listTypeHandicap;
	}

	/**
	 * [HandicapManagerBean.listTypeHandicap] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 11:34:09
	 * @param listTypeHandicap
	 *            the listTypeHandicap to set
	 */
	public void setListTypeHandicap(List<SelectItem> listTypeHandicap) {
		this.listTypeHandicap = listTypeHandicap;
	}

	/**
	 * [HandicapManagerBean.onRowSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 15:50:24
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		selectedHandicap = (SituationHandicapDto) event.getObject();
		setSituationId(selectedHandicap.getId().toString());
	}

	/**
	 * [HandicapManagerBean.addSituation] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 11:56:00
	 */
	public void addSituation() {
		selectedHandicap = new SituationHandicapDto();
	}

	/**
	 * [HandicapManagerBean.entityChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 11:56:06
	 * @param event
	 */
	public void entityChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			entityChange = true;
		}
	}

	/**
	 * [HandicapManagerBean.entityChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 11:56:38
	 * @return the entityChange
	 */
	public boolean isEntityChange() {
		return entityChange;
	}

	/**
	 * [HandicapManagerBean.entityChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 11:56:38
	 * @param entityChange
	 *            the entityChange to set
	 */
	public void setEntityChange(boolean entityChange) {
		this.entityChange = entityChange;
	}

	/**
	 * [HandicapManagerBean.situationId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 15:32:01
	 * @return the situationId
	 */
	public String getSituationId() {
		return situationId;
	}

	/**
	 * [HandicapManagerBean.situationId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 15:32:01
	 * @param situationId
	 *            the situationId to set
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
	 * [HandicapManagerBean.id_Situation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 14:26:29
	 * @return the id_Situation
	 */
	public Integer getId_Situation() {
		return id_Situation;
	}

	/**
	 * [HandicapManagerBean.id_Situation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 14:26:29
	 * @param id_Situation
	 *            the id_Situation to set
	 */
	public void setId_Situation(Integer id_Situation) {
		this.id_Situation = id_Situation;
	}

	/**
	 * [HandicapManagerBean.removeSituation] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 14:50:50
	 */
	public void removeSituation(SituationHandicapDto current) {
		FacesMessage msg = new FacesMessage();
		try {
			if (current == null) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundleCommon.getString("warn_info") + ": "
						+ bundleHandicap.getString("handicap_select_situation"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			situationHandicapService.remove(current);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_suppression"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			loadSituationHandicap();
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
	 * [HandicapManagerBean.editSituation] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 17:16:01
	 * @param current
	 */
	public void editSituation(SituationHandicapDto current) {
		selectedHandicap = current;
		entityChange = false;
		setSituationId(selectedHandicap.getId().toString());
	}

	/**
	 * [HandicapManagerBean.dossierEtudiantBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 juin 2014 12:07:10
	 * @return the dossierEtudiantBean
	 */
	public DossierEtudiantBean getDossierEtudiantBean() {
		return dossierEtudiantBean;
	}

	/**
	 * [HandicapManagerBean.dossierEtudiantBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 juin 2014 12:07:10
	 * @param dossierEtudiantBean
	 *            the dossierEtudiantBean to set
	 */
	public void setDossierEtudiantBean(DossierEtudiantBean dossierEtudiantBean) {
		this.dossierEtudiantBean = dossierEtudiantBean;
		if (dossierEtudiantBean != null && dossierEtudiantId == null) {
			setDossierEtudiantId(dossierEtudiantBean.getDossierEtudiantId());
		}
	}

	/**
	 * [HandicapManagerBean.utilBean] Getter 
	 * @author MAKERRI Sofiane on : 9 déc. 2014  14:22:25
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [HandicapManagerBean.utilBean] Setter 
	 * @author MAKERRI Sofiane on : 9 déc. 2014  14:22:25
	 * @param utilBean the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}
	
	

}
