/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.SearchBean.java] 
 * @author MAKERRI Sofiane on : 31 déc. 2014  09:57:35
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

/**
 * @author MAKERRI Sofiane on : 31 déc. 2014 09:57:35
 */
/**
 * @author MAKERRI Sofiane on : 22 janv. 2015 08:28:28
 */
@ManagedBean(name = "searchBean")
@ViewScoped
public class SearchBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 09:57:52
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer anneeAcademiqueId;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> ofList;
	private List<SelectItem> niveauList;
	private List<SelectItem> periodeList;
	private List<SelectItem> groupePedagogiqueList;
	private Integer oofId;
	private Integer niveauId;
	private Integer periodeId;
	private Integer groupePedagogiqueId;
	private boolean editMode;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;

	public SearchBean() {
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
			anneeAcademiqueId = sessionBean.getIdAnneeAcademique();
			anneeChanged();
		}
	}

	/**
	 * [SearchBean.loadAnneeAcademique] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:16:26
	 */
	public void loadAnneeAcademique() {
		try {
			anneeAcademiqueList = utilBean.loadAnneeAcademique();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [SearchBean.anneeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 09:59:38
	 */
	public void anneeChanged() {
		oofId = null;
		periodeId = null;
		loadOffreFormationOuverte();
		ofChanged();
	}

	/**
	 * [SearchBean.loadOffreFormationOuverte] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 09:59:52
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
	 * [SearchBean.ofChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:00:26
	 */
	public void ofChanged() {
		periodeId = null;
		niveauId = null;
		loadNiveau();
		niveauChanged();
	}

	/**
	 * [SearchBean.loadNiveau] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:00:44
	 */
	public void loadNiveau() {
		if (oofId != null) {
			niveauList = utilBean.loadNiveaux(oofId);
		}
	}

	/**
	 * [SearchBean.niveauChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:13:59
	 */
	public void niveauChanged() {
		loadPeriodes();
	}

	/**
	 * [SearchBean.loadPeriodeList] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:03:21
	 */
	public void loadPeriodes() {
		try {
			periodeList = new ArrayList<SelectItem>();
			if (oofId != null) {
				periodeList = utilBean.loadPeriodeByNiveau(oofId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [SearchBean.periodeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 08:29:41
	 */
	public void periodeChanged() {
		loadGroupePedagogique();
	}

	/**
	 * [SearchBean.loadGroupePedagogique] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 08:28:32
	 */
	public void loadGroupePedagogique() {
		try {
			groupePedagogiqueList = new ArrayList<SelectItem>();
			if (oofId != null && periodeId != null) {
				groupePedagogiqueList = utilBean.loadSectionItem(oofId, periodeId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [SearchBean.anneeAcademiqueId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:01:19
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [SearchBean.anneeAcademiqueId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:01:19
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [SearchBean.anneeAcademiqueList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:01:19
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [SearchBean.anneeAcademiqueList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:01:19
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [SearchBean.ofList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:01:19
	 * @return the ofList
	 */
	public List<SelectItem> getOfList() {
		return ofList;
	}

	/**
	 * [SearchBean.ofList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:01:19
	 * @param ofList
	 *            the ofList to set
	 */
	public void setOfList(List<SelectItem> ofList) {
		this.ofList = ofList;
	}

	/**
	 * [SearchBean.niveauList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:01:19
	 * @return the niveauList
	 */
	public List<SelectItem> getNiveauList() {
		return niveauList;
	}

	/**
	 * [SearchBean.niveauList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:01:19
	 * @param niveauList
	 *            the niveauList to set
	 */
	public void setNiveauList(List<SelectItem> niveauList) {
		this.niveauList = niveauList;
	}

	/**
	 * [SearchBean.periodeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:01:19
	 * @return the periodeList
	 */
	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	/**
	 * [SearchBean.periodeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:01:19
	 * @param periodeList
	 *            the periodeList to set
	 */
	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	/**
	 * [SearchBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:01:19
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [SearchBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:01:19
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [SearchBean.oofId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:04:35
	 * @return the oofId
	 */
	public Integer getOofId() {
		return oofId;
	}

	/**
	 * [SearchBean.oofId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:04:35
	 * @param oofId
	 *            the oofId to set
	 */
	public void setOofId(Integer oofId) {
		this.oofId = oofId;
	}

	/**
	 * [SearchBean.niveauId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:04:35
	 * @return the niveauId
	 */
	public Integer getNiveauId() {
		return niveauId;
	}

	/**
	 * [SearchBean.niveauId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:04:35
	 * @param niveauId
	 *            the niveauId to set
	 */
	public void setNiveauId(Integer niveauId) {
		this.niveauId = niveauId;
	}

	/**
	 * [SearchBean.periodeId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:04:35
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [SearchBean.periodeId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:04:35
	 * @param periodeId
	 *            the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [SearchBean.editMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:15:34
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [SearchBean.editMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:15:34
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * [SearchBean.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:15:59
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [SearchBean.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:15:59
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [SearchBean.groupePedagogiqueId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 08:18:32
	 * @return the groupePedagogiqueId
	 */
	public Integer getGroupePedagogiqueId() {
		return groupePedagogiqueId;
	}

	/**
	 * [SearchBean.groupePedagogiqueId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 08:18:32
	 * @param groupePedagogiqueId
	 *            the groupePedagogiqueId to set
	 */
	public void setGroupePedagogiqueId(Integer groupePedagogiqueId) {
		this.groupePedagogiqueId = groupePedagogiqueId;
	}

	/**
	 * [SearchBean.groupePedagogiqueList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 08:26:44
	 * @return the groupePedagogiqueList
	 */
	public List<SelectItem> getGroupePedagogiqueList() {
		return groupePedagogiqueList;
	}

	/**
	 * [SearchBean.groupePedagogiqueList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 08:26:44
	 * @param groupePedagogiqueList
	 *            the groupePedagogiqueList to set
	 */
	public void setGroupePedagogiqueList(List<SelectItem> groupePedagogiqueList) {
		this.groupePedagogiqueList = groupePedagogiqueList;
	}

}
