/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation.NoteEliminatoireBean.java] 
 * @author MAKERRI Sofiane on : 18 janv. 2015  11:49:56
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.NoteEliminatoireDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.PlanningSessionDto;
import dz.gov.mesrs.sii.fve.business.service.examen.NoteEliminatoireService;
import dz.gov.mesrs.sii.fve.business.service.examen.PlanningSessionService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SearchPlanningBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;

/**
 * @author MAKERRI Sofiane on : 18 janv. 2015 11:49:56
 */
@ManagedBean(name = "noteEliminatoireBean")
@ViewScoped
public class NoteEliminatoireBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 11:50:28
	 */
	private static final long serialVersionUID = 1L;
	private boolean editMode;
	private boolean showDetail;
	private Integer anneeAcademiqueId;
	private List<NoteEliminatoireDto> noteEliminatoireList;
	@ManagedProperty("#{noteEliminatoireService}")
	private NoteEliminatoireService noteEliminatoireService;
	@ManagedProperty("#{searchPlanningBean}")
	private SearchPlanningBean searchPlanningBean;
	@ManagedProperty("#{planningSessionService}")
	private PlanningSessionService planningSessionService;
	private PlanningSessionDto planningSessionDto;
	private ResourceBundle notebundle;
	private String exportFileName;

	/**
	 * [NoteEliminatoireBean.NoteEliminatoireBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 11:49:56
	 */
	public NoteEliminatoireBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		notebundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.NOTE_EXAMEN_BUNDLE_MSG_NAME);
	}
	
	@PostConstruct
	public void init() {
		showDetail = false;
	}

	/**
	 * [NoteEliminatoireBean.findNoteEliminatoire] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:55:04
	 */
	public void findNoteEliminatoire() {
		planningChanged();
	}

	/**
	 * [NoteEliminatoireBean.planningChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:55:07
	 */
	public void planningChanged() {
		if (searchPlanningBean.getAnneeAcademiqueId() != null
				&& searchPlanningBean.getOofId() != null
				&& searchPlanningBean.getPeriodeId() != null
				&& searchPlanningBean.getPlanningSessionId() != null) {
			planningSessionDto = planningSessionService
					.findById(searchPlanningBean.getPlanningSessionId());
			loadNoteEliminatoireList();
		}
	}

	/**
	 * [NoteEliminatoireBean.loadNoteEliminatoireList] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 11:37:15
	 */
	private void loadNoteEliminatoireList() {
		NoteEliminatoireDto searchDto = new NoteEliminatoireDto(
				searchPlanningBean.getOofId(),
				searchPlanningBean.getPeriodeId(), null,
				searchPlanningBean.getPlanningSessionId());
		noteEliminatoireList = noteEliminatoireService.findAdvanced(searchDto);
		if (noteEliminatoireList == null || noteEliminatoireList.isEmpty()) {
			CommonMessagesUtils.showWarningMessage(notebundle
					.getString("note_eliminatoire_data_table_list_result"));
			return;
		}
		showDetail = true;
	}

	/**
	 * [NoteEliminatoireBean.save] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 12:05:37
	 */
	public void save() {
		if (noteEliminatoireList != null && !noteEliminatoireList.isEmpty()) {
			for (NoteEliminatoireDto noteEliminatoireDto : noteEliminatoireList) {
				if (noteEliminatoireDto.getNoteEliminatoireAjuste() == null
						|| noteEliminatoireDto.getNoteEliminatoireAjuste() == 0) {
					noteEliminatoireDto
							.setNoteEliminatoireAjuste(noteEliminatoireDto
									.getNoteEliminatoire());
				}
				noteEliminatoireService.insertOrUpdate(noteEliminatoireDto);
			}
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}

	/**
	 * [NoteEliminatoireBean.editMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 11:51:15
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [NoteEliminatoireBean.editMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 11:51:15
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * [NoteEliminatoireBean.anneeAcademiqueId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 11:51:37
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [NoteEliminatoireBean.anneeAcademiqueId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 11:51:37
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [NoteEliminatoireBean.noteEliminatoireList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:56:03
	 * @return the noteEliminatoireList
	 */
	public List<NoteEliminatoireDto> getNoteEliminatoireList() {
		return noteEliminatoireList;
	}

	/**
	 * [NoteEliminatoireBean.noteEliminatoireList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:56:03
	 * @param noteEliminatoireList
	 *            the noteEliminatoireList to set
	 */
	public void setNoteEliminatoireList(
			List<NoteEliminatoireDto> noteEliminatoireList) {
		this.noteEliminatoireList = noteEliminatoireList;
	}

	/**
	 * [NoteEliminatoireBean.showDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:56:15
	 * @return the showDetail
	 */
	public boolean isShowDetail() {
		if (searchPlanningBean.getAnneeAcademiqueId() == null
				|| searchPlanningBean.getOofId() == null
				|| searchPlanningBean.getPeriodeId() == null
				|| searchPlanningBean.getPlanningSessionId() == null) {
			showDetail = false;
		}
		return showDetail;
	}

	/**
	 * [NoteEliminatoireBean.showDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:56:15
	 * @param showDetail
	 *            the showDetail to set
	 */
	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}

	/**
	 * [NoteEliminatoireBean.noteEliminatoireService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:57:05
	 * @return the noteEliminatoireService
	 */
	public NoteEliminatoireService getNoteEliminatoireService() {
		return noteEliminatoireService;
	}

	/**
	 * [NoteEliminatoireBean.noteEliminatoireService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:57:05
	 * @param noteEliminatoireService
	 *            the noteEliminatoireService to set
	 */
	public void setNoteEliminatoireService(
			NoteEliminatoireService noteEliminatoireService) {
		this.noteEliminatoireService = noteEliminatoireService;
	}

	/**
	 * [NoteEliminatoireBean.searchPlanningBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 08:03:09
	 * @return the searchPlanningBean
	 */
	public SearchPlanningBean getSearchPlanningBean() {
		return searchPlanningBean;
	}

	/**
	 * [NoteEliminatoireBean.searchPlanningBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 08:03:09
	 * @param searchPlanningBean
	 *            the searchPlanningBean to set
	 */
	public void setSearchPlanningBean(SearchPlanningBean searchPlanningBean) {
		this.searchPlanningBean = searchPlanningBean;
	}

	/**
	 * [NoteEliminatoireBean.planningSessionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 08:54:39
	 * @return the planningSessionService
	 */
	public PlanningSessionService getPlanningSessionService() {
		return planningSessionService;
	}

	/**
	 * [NoteEliminatoireBean.planningSessionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 08:54:39
	 * @param planningSessionService
	 *            the planningSessionService to set
	 */
	public void setPlanningSessionService(
			PlanningSessionService planningSessionService) {
		this.planningSessionService = planningSessionService;
	}

	/**
	 * [NoteEliminatoireBean.planningSessionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 08:55:08
	 * @return the planningSessionDto
	 */
	public PlanningSessionDto getPlanningSessionDto() {
		return planningSessionDto;
	}

	/**
	 * [NoteEliminatoireBean.planningSessionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 08:55:08
	 * @param planningSessionDto
	 *            the planningSessionDto to set
	 */
	public void setPlanningSessionDto(PlanningSessionDto planningSessionDto) {
		this.planningSessionDto = planningSessionDto;
	}

	/**
	 * [NoteEliminatoireBean.exportFileName] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 12:28:12
	 * @return the exportFileName
	 */
	public String getExportFileName() {
		exportFileName = "Note_eliminatoire_"
				+ planningSessionDto.getOffreFormationLibelleFr() + "_"
				+ planningSessionDto.getLibellePeriode() + "_"
				+ planningSessionDto.getAnneeAcademiqueCode();
		return exportFileName;
	}

	/**
	 * [NoteEliminatoireBean.exportFileName] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 12:28:12
	 * @param exportFileName
	 *            the exportFileName to set
	 */
	public void setExportFileName(String exportFileName) {
		this.exportFileName = exportFileName;
	}

}
