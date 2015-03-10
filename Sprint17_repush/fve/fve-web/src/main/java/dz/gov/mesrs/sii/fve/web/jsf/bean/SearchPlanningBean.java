/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.SearchPlanningBean.java] 
 * @author MAKERRI Sofiane on : 18 janv. 2015  12:01:19
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.PlanningSessionDto;

/**
 * @author MAKERRI Sofiane on : 18 janv. 2015 12:01:19
 */
@ManagedBean(name = "searchPlanningBean")
@ViewScoped
public class SearchPlanningBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:01:23
	 */
	private static final long serialVersionUID = 1L;
	private Integer anneeAcademiqueId;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> ofList;
	private List<SelectItem> planningSessionList;
	private List<SelectItem> periodeList;
	private Integer oofId;
	private Long planningSessionId;
	private Integer periodeId;
	private boolean editMode;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;

	/**
	 * [SearchPlanningBean.SearchPlanningBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:01:19
	 */
	public SearchPlanningBean() {
		super();
	}

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String viewId = facesContext.getViewRoot().getViewId();
		if (viewId != null && viewId.toLowerCase().contains("gerer")) {
			editMode = true;
		}
		if (viewId != null && viewId.toLowerCase().contains("edit")) {
			editMode = true;
		}
		if (editMode) {
			anneeAcademiqueId = sessionBean.getIdAnneeAcademique();
			loadOffreFormationOuverte();
		} else {
			loadAnneeAcademique();
		}
	}

	/**
	 * [SearchPlanningBean.loadAnneeAcademique] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:03:14
	 */
	public void loadAnneeAcademique() {
		try {
			anneeAcademiqueList = utilBean.loadAnneeAcademique();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [SearchPlanningBean.anneeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:03:31
	 */
	public void anneeChanged() {
		loadOffreFormationOuverte();
		oofId = null;
		periodeId = null;
		planningSessionId = null;
		ofChanged();
	}

	/**
	 * [SearchPlanningBean.ofChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:03:44
	 */
	public void ofChanged() {
		periodeId = null;
		planningSessionId = null;
		loadPeriodes();
		periodeChanged();
	}

	/**
	 * [SearchPlanningBean.loadPeriodes] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:04:26
	 */
	public void loadPeriodes() {
		try {
			planningSessionId = null;
			periodeList = new ArrayList<SelectItem>();
			if (oofId != null) {
				periodeList = utilBean.loadPeriodeByNiveau(oofId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [SearchPlanningBean.periodeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:04:51
	 */
	public void periodeChanged() {
		loadPlanningSession();
	}

	/**
	 * [SearchPlanningBean.loadPlanningSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:06:41
	 */
	public void loadPlanningSession() {
		try {
			planningSessionList = new ArrayList<SelectItem>();
			if (oofId != null && periodeId != null && anneeAcademiqueId != null) {
				PlanningSessionDto searchDto = new PlanningSessionDto();
				searchDto.setOuvertureOffreFormationId(oofId);
				searchDto.setIdPeriode(periodeId);
				searchDto.setAnneeAcademiqueId(anneeAcademiqueId);
				planningSessionList = utilBean.loadPlanningSession(searchDto);
//				if (planningSessionList != null
//						&& planningSessionList.size() == 1) {
//					planningSessionId = (Long) planningSessionList.get(0)
//							.getValue();
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [SearchPlanningBean.loadOffreFormationOuverte] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:03:00
	 */
	public void loadOffreFormationOuverte() {
		ofList = new ArrayList<SelectItem>();
		try {
			if (anneeAcademiqueId == null) {
				return;
			}
			ofList = utilBean.loadOffreFormationOuverte(anneeAcademiqueId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [SearchPlanningBean.anneeAcademiqueId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [SearchPlanningBean.anneeAcademiqueId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [SearchPlanningBean.anneeAcademiqueList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [SearchPlanningBean.anneeAcademiqueList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [SearchPlanningBean.ofList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @return the ofList
	 */
	public List<SelectItem> getOfList() {
		return ofList;
	}

	/**
	 * [SearchPlanningBean.ofList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @param ofList
	 *            the ofList to set
	 */
	public void setOfList(List<SelectItem> ofList) {
		this.ofList = ofList;
	}

	/**
	 * [SearchPlanningBean.planningSessionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @return the planningSessionList
	 */
	public List<SelectItem> getPlanningSessionList() {
		return planningSessionList;
	}

	/**
	 * [SearchPlanningBean.planningSessionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @param planningSessionList
	 *            the planningSessionList to set
	 */
	public void setPlanningSessionList(List<SelectItem> planningSessionList) {
		this.planningSessionList = planningSessionList;
	}

	/**
	 * [SearchPlanningBean.periodeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @return the periodeList
	 */
	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	/**
	 * [SearchPlanningBean.periodeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @param periodeList
	 *            the periodeList to set
	 */
	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	/**
	 * [SearchPlanningBean.oofId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @return the oofId
	 */
	public Integer getOofId() {
		return oofId;
	}

	/**
	 * [SearchPlanningBean.oofId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @param oofId
	 *            the oofId to set
	 */
	public void setOofId(Integer oofId) {
		this.oofId = oofId;
	}

	/**
	 * [SearchPlanningBean.planningSessionId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @return the planningSessionId
	 */
	public Long getPlanningSessionId() {
		return planningSessionId;
	}

	/**
	 * [SearchPlanningBean.planningSessionId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @param planningSessionId
	 *            the planningSessionId to set
	 */
	public void setPlanningSessionId(Long planningSessionId) {
		this.planningSessionId = planningSessionId;
	}

	/**
	 * [SearchPlanningBean.periodeId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [SearchPlanningBean.periodeId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @param periodeId
	 *            the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [SearchPlanningBean.editMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [SearchPlanningBean.editMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * [SearchPlanningBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [SearchPlanningBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [SearchPlanningBean.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [SearchPlanningBean.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:02:18
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

}
