/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation.ParamCoefficientBean.java] 
 * @author MAKERRI Sofiane on : 5 janv. 2015  11:14:46
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation;

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

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.CoefficientExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.service.examen.CoefficientExamenService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SearchBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;

/**
 * @author MAKERRI Sofiane on : 5 janv. 2015 11:14:46
 */
/**
 * @author MAKERRI Sofiane  on : 8 janv. 2015  12:34:23
 */
/**
 * @author MAKERRI Sofiane on : 8 janv. 2015 12:34:24
 */
@ManagedBean(name = "coefficientBean")
@ViewScoped
public class CoefficientBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 11:14:57
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{searchBean}")
	private SearchBean searchBean;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{coefficientExamenService}")
	private CoefficientExamenService coefficientExamenService;
	private boolean showTable;
	private CoefficientExamenDto coefficientExamenDto;
	private List<SelectItem> rattachementMcList;
	private List<RattachementMcDto> rattachementMcsList;
	private final ResourceBundle examenBundle;
	private String exportFileName;
	private List<CoefficientExamenDto> coefficientExamenDtos;
	private boolean editMode;
	private String ofLibelle;
	private String periodeLibelle;

	/**
	 * [ParamCoefficientBean.ParamCoefficientBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 11:14:46
	 */
	public CoefficientBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		examenBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.EXAMEN_BUNDLE_MSG_NAME);
		editMode = false;
		String viewId = facesContext.getViewRoot().getViewId();

		if (viewId != null && viewId.toLowerCase().contains("edit")) {
			editMode = true;
		}

	}

	@PostConstruct
	public void init() {
		coefficientExamenDto = new CoefficientExamenDto();

	}

	/**
	 * [CoefficientBean.loadRattachementMcsList] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 09:32:49
	 */
	private void loadRattachementMcsList() {

		rattachementMcsList = new ArrayList<RattachementMcDto>();
		try {

			if (searchBean.getPeriodeId() != null
					&& searchBean.getOofId() != null) {
				rattachementMcsList = utilBean.loadRattachementMc(
						searchBean.getOofId(), searchBean.getPeriodeId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [CoefficientBean.loadLibelles] Method
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 16:36:40
	 */
	private void loadLibelles() {
		if (ofLibelle == null || ofLibelle.isEmpty()) {
			if (coefficientExamenDtos != null
					|| !coefficientExamenDtos.isEmpty()) {
				CoefficientExamenDto _ce = coefficientExamenDtos.get(0);
				ofLibelle = _ce.getOfLibelleFr();
				periodeLibelle = _ce.getPeriodeLibelleFr();
			}
		}
	}

	/**
	 * [CoefficientBean.loadCoefficientMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 14:52:35
	 */
	public void loadCoefficientMc() {
		showTable = false;
		coefficientExamenDtos = coefficientExamenService.findByOofAndPeriode(
				searchBean.getOofId(), searchBean.getPeriodeId());
		if (rattachementMcsList != null && coefficientExamenDtos != null
				&& rattachementMcsList.size() != coefficientExamenDtos.size()) {
			for (RattachementMcDto _rmc : rattachementMcsList) {
				boolean exist = false;
				for (CoefficientExamenDto ced : coefficientExamenDtos) {
					if (ced.getRattachementMcId() != null
							&& ced.getRattachementMcId().equals(_rmc.getId())) {
						exist = true;
					}
				}
				if (!exist) {
					CoefficientExamenDto coeffExamen = new CoefficientExamenDto();
					coeffExamen.setRattachementMcId(_rmc.getId());
					coeffExamen.setOofId(searchBean.getOofId());
					coeffExamen.setPeriodeId(searchBean.getPeriodeId());
					coeffExamen.setMcLibelleFr(_rmc.getMcLibelleFr());
					coefficientExamenDtos.add(coeffExamen);
				}
			}
		}
		if (coefficientExamenDtos == null || coefficientExamenDtos.isEmpty()) {
			CommonMessagesUtils.showWarningMessage(examenBundle
					.getString("examen_coefficient_matiere_empty"));
			return;
		}
		loadLibelles();
		showTable = true;
	}

	/**
	 * [ParamCoefficientBean.periodeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 11:17:53
	 */
	public void periodeChanged() {
		showTable = false;
		if (searchBean.getPeriodeId() != null && searchBean.getOofId() != null) {
			loadRattachementMcsList();
			loadCoefficientMc();
		}
	}

	/**
	 * [CoefficientBean.findCoefficient] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 janv. 2015 12:34:27
	 */
	public void findCoefficient() {
		periodeChanged();
	}

	/**
	 * [CoefficientBean.addCoefficient] Method
	 * 
	 * @author MAKERRI Sofiane on : 14 janv. 2015 08:39:35
	 */
	public void addCoefficient() {
		coefficientExamenDto = new CoefficientExamenDto();
		showTable = true;
	}

	/**
	 * [ParamCoefficientBean.saveParam] Method
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 11:48:44
	 */
	public void saveCoefficientMc() {
		try {
			coefficientExamenDto.setOofId(searchBean.getOofId());
			coefficientExamenDto.setPeriodeId(searchBean.getPeriodeId());
			if (coefficientExamenDtos != null) {
				for (CoefficientExamenDto coefficientExamenDto : coefficientExamenDtos) {
					if (coefficientExamenDto.getOofId() == null) {
						CommonMessagesUtils.showErrorMessage(examenBundle
								.getString("examen_error_oof_required"));
						return;
					}
					if (coefficientExamenDto.getPeriodeId() == null) {
						CommonMessagesUtils.showErrorMessage(examenBundle
								.getString("examen_error_periode_required"));
						return;
					}
					if (coefficientExamenDto.getRattachementMcId() == null) {
						CommonMessagesUtils
								.showErrorMessage(examenBundle
										.getString("examen_error_rattachement_mc_required"));
						return;
					}
					if ((coefficientExamenDto.getCoefficientExamen()
							+ coefficientExamenDto
									.getCoefficientControleContinu() + coefficientExamenDto
								.getCoefficientControleIntermediaire()) != 1) {
						CommonMessagesUtils.showErrorMessage(examenBundle
								.getString("examen_error_somme_coefficient")
								+ "["
								+ coefficientExamenDto.getMcLibelleFr()
								+ "]");
						return;
					}
					// boolean save = coefficientExamenDto.getId() == 0;
					// if (save) {
					// CoefficientExamenDto _coefficientExamenDto =
					// coefficientExamenService
					// .findUnique(coefficientExamenDto.getOofId(),
					// coefficientExamenDto.getPeriodeId(),
					// coefficientExamenDto
					// .getRattachementMcId());
					// if (_coefficientExamenDto != null) {
					// CommonMessagesUtils
					// .showErrorMessage(examenBundle
					// .getString("examen_error_mc_coefficient_exist_deja"));
					// return;
					// }
					// }

					coefficientExamenDto = coefficientExamenService
							.insertOrUpdate(coefficientExamenDto);

				}
				CommonMessagesUtils.showSuccessSaveMessage();
				loadCoefficientMc();
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [ParamCoefficientBean.searchBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 11:16:57
	 * @return the searchBean
	 */
	public SearchBean getSearchBean() {
		return searchBean;
	}

	/**
	 * [ParamCoefficientBean.searchBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 11:16:57
	 * @param searchBean
	 *            the searchBean to set
	 */
	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}

	/**
	 * [CoefficientBean.coefficientExamenService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 14:52:08
	 * @return the coefficientExamenService
	 */
	public CoefficientExamenService getCoefficientExamenService() {
		return coefficientExamenService;
	}

	/**
	 * [CoefficientBean.coefficientExamenService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 14:52:08
	 * @param coefficientExamenService
	 *            the coefficientExamenService to set
	 */
	public void setCoefficientExamenService(
			CoefficientExamenService coefficientExamenService) {
		this.coefficientExamenService = coefficientExamenService;
	}

	/**
	 * [CoefficientBean.coefficientExamenDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 14:52:08
	 * @return the coefficientExamenDto
	 */
	public CoefficientExamenDto getCoefficientExamenDto() {
		return coefficientExamenDto;
	}

	/**
	 * [CoefficientBean.coefficientExamenDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 14:52:08
	 * @param coefficientExamenDto
	 *            the coefficientExamenDto to set
	 */
	public void setCoefficientExamenDto(
			CoefficientExamenDto coefficientExamenDto) {
		this.coefficientExamenDto = coefficientExamenDto;
	}

	/**
	 * [ParamCoefficientBean.rattachementMcList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 11:59:59
	 * @return the rattachementMcList
	 */
	public List<SelectItem> getRattachementMcList() {
		return rattachementMcList;
	}

	/**
	 * [ParamCoefficientBean.rattachementMcList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 11:59:59
	 * @param rattachementMcList
	 *            the rattachementMcList to set
	 */
	public void setRattachementMcList(List<SelectItem> rattachementMcList) {
		this.rattachementMcList = rattachementMcList;
	}

	/**
	 * [ParamCoefficientBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 12:02:17
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [ParamCoefficientBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 12:02:17
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [ParamCoefficientBean.exportFileName] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 12:51:01
	 * @return the exportFileName
	 */
	public String getExportFileName() {
		exportFileName = "Coefficients_examen_" + ofLibelle + "_"
				+ periodeLibelle;
		return exportFileName;
	}

	/**
	 * [ParamCoefficientBean.exportFileName] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 12:51:01
	 * @param exportFileName
	 *            the exportFileName to set
	 */
	public void setExportFileName(String exportFileName) {
		this.exportFileName = exportFileName;
	}

	/**
	 * [CoefficientBean.coefficientExamenDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 14:53:10
	 * @return the coefficientExamenDtos
	 */
	public List<CoefficientExamenDto> getCoefficientExamenDtos() {
		return coefficientExamenDtos;
	}

	/**
	 * [CoefficientBean.coefficientExamenDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 14:53:10
	 * @param coefficientExamenDtos
	 *            the coefficientExamenDtos to set
	 */
	public void setCoefficientExamenDtos(
			List<CoefficientExamenDto> coefficientExamenDtos) {
		this.coefficientExamenDtos = coefficientExamenDtos;
	}

	/**
	 * [CoefficientBean.showTable] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 15:14:06
	 * @return the showTable
	 */
	public boolean isShowTable() {
		return showTable;
	}

	/**
	 * [CoefficientBean.showTable] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 15:14:06
	 * @param showTable
	 *            the showTable to set
	 */
	public void setShowTable(boolean showTable) {
		this.showTable = showTable;
	}

	/**
	 * [CoefficientBean.editMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 15:14:06
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [CoefficientBean.editMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 15:14:06
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * [CoefficientBean.ofLibelle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 16:37:08
	 * @return the ofLibelle
	 */
	public String getOfLibelle() {
		return ofLibelle;
	}

	/**
	 * [CoefficientBean.ofLibelle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 16:37:08
	 * @param ofLibelle
	 *            the ofLibelle to set
	 */
	public void setOfLibelle(String ofLibelle) {
		this.ofLibelle = ofLibelle;
	}

	/**
	 * [CoefficientBean.periodeLibelle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 16:37:08
	 * @return the periodeLibelle
	 */
	public String getPeriodeLibelle() {
		return periodeLibelle;
	}

	/**
	 * [CoefficientBean.periodeLibelle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 16:37:08
	 * @param periodeLibelle
	 *            the periodeLibelle to set
	 */
	public void setPeriodeLibelle(String periodeLibelle) {
		this.periodeLibelle = periodeLibelle;
	}

	/**
	 * [CoefficientBean.rattachementMcsList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 09:31:41
	 * @return the rattachementMcsList
	 */
	public List<RattachementMcDto> getRattachementMcsList() {
		return rattachementMcsList;
	}

	/**
	 * [CoefficientBean.rattachementMcsList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 09:31:41
	 * @param rattachementMcsList
	 *            the rattachementMcsList to set
	 */
	public void setRattachementMcsList(
			List<RattachementMcDto> rattachementMcsList) {
		this.rattachementMcsList = rattachementMcsList;
	}

}
