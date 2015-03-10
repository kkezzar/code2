/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation.BilanConsulBean.java] 
 * @author MAKERRI Sofiane on : 31 déc. 2014  09:51:53
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation;

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

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.business.util.CrossTab;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.DeliberationSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanSessionService;
import dz.gov.mesrs.sii.fve.business.service.examen.DeliberationSessionService;
import dz.gov.mesrs.sii.fve.business.service.impression.ImpressionService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SearchBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.impression.PrintMgrBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;

/**
 * @author MAKERRI Sofiane on : 31 déc. 2014 09:51:53
 */
/**
 * @author MAKERRI Sofiane on : 31 déc. 2014 11:50:03
 */
@ManagedBean(name = "bilanConsulBean")
@ViewScoped
public class BilanConsulBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 09:51:57
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{searchBean}")
	private SearchBean searchBean;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{bilanSessionService}")
	private BilanSessionService bilanSessionService;
	@ManagedProperty("#{situationService}")
	private SituationService situationService;
	@ManagedProperty("#{deliberationSessionService}")
	private DeliberationSessionService deliberationSessionService;
	@ManagedProperty("#{impressionService}")
	private ImpressionService impressionService;
	@ManagedProperty("#{printMgrBean}")
	private PrintMgrBean printMgrBean;
	private boolean showDetail;
	private ResourceBundle bilanBundle;
	private SituationEntiteDto situationClose;
	private List<DeliberationSessionDto> deliberationList;
	private List<BilanSessionDto> bilanList;
	private String fileNamePeriode;
	private String ofLibelleLongFr;
	private String periodeLibelleLongFr;
	private String niveauLibelleLongFr;
	private String anneeCode;
	private List<CrossTab> crossCollection;

	/**
	 * [BilanConsulBean.BilanConsulBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 09:51:53
	 */
	public BilanConsulBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bilanBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.BILAN_BUNDLE_MSG_NAME);
	}

	@PostConstruct
	public void init() {
		loadSituationClose();
	}

	/**
	 * [BilanConsulBean.periodeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:43:58
	 */
	public void periodeChanged() {
		if (searchBean.getPeriodeId() != null) {
			findBilanPeriode();
		}
	}

	/**
	 * [BilanConsulBean.loadituationClose] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 12:48:58
	 */
	private void loadSituationClose() {
		if (situationClose == null || situationClose.getId() == 0) {
			situationClose = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DELIBERATION_SESSION_CODE,
							UtilConstants.SITUTAION_CLOTUREE_CODE);
		}
	}

	/**
	 * [BilanConsulBean.niveauChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 12:44:13
	 */
	public void niveauChanged() {
		if (searchBean.getNiveauId() != null) {
			findBilanAnnuel();
		}
	}

	/**
	 * [BilanConsulBean.findBilanPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:31:02
	 */
	public void findBilanPeriode() {
		try {
			showDetail = false;
			if (!allSessionIsClosed()) {
				// Utility.showErrorMessage(bilanBundle
				// .getString("bilan_erreur_seance_deliberation_non_ferme"));
				// return;
			}

			if (searchBean.getOofId() != null
					&& searchBean.getPeriodeId() != null) {
				bilanList = bilanSessionService.findBilanPeriode(
						searchBean.getOofId(), searchBean.getPeriodeId());
			}
			if (bilanList == null || bilanList.isEmpty()) {
				Utility.showWarningMessage(bilanBundle
						.getString("bilan_data_table_seacrh_result_no_result"));
				return;
			}
			showDetail = true;

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanConsulBean.findAnnuelPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 12:35:01
	 */
	public void findBilanAnnuel() {
		try {
			showDetail = false;
			loadDeliberationList();
			DeliberationSessionDto _deliberation = getDeliberationWithBilanAnnuel();
			loadSituationClose();
			if (_deliberation != null
					&& _deliberation.getSituationId() != null
					&& situationClose != null
					&& situationClose.getId() != 0
					&& _deliberation.getSituationId().equals(
							situationClose.getId())) {

				Utility.showErrorMessage(bilanBundle
						.getString("bilan_erreur_seance_deliberation_non_ferme"));
				return;
			}

			if (searchBean.getOofId() != null
					&& searchBean.getNiveauId() != null) {
				bilanList = bilanSessionService.findBilanAnnuel(
						searchBean.getOofId(), searchBean.getNiveauId());
			}
			if (bilanList == null || bilanList.isEmpty()) {
				Utility.showWarningMessage(bilanBundle
						.getString("bilan_data_table_seacrh_result_no_result"));
				return;
			}

			showDetail = true;

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanConsulBean.getDeliberationWithBilanAnnuel] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 12:37:28
	 * @return
	 */
	public DeliberationSessionDto getDeliberationWithBilanAnnuel() {
		if (deliberationList != null) {
			for (DeliberationSessionDto del : deliberationList) {
				if (del.getBilanAnnuel()) {
					return del;
				}
			}
		}
		return null;
	}

	/**
	 * [BilanConsulBean.loadDeliberationList] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 12:38:06
	 */
	public void loadDeliberationList() {
		if (searchBean.getAnneeAcademiqueId() != null
				&& searchBean.getOofId() != null
				&& searchBean.getNiveauId() != null) {
			deliberationList = deliberationSessionService.findBilanPeriode(
					searchBean.getAnneeAcademiqueId(), searchBean.getOofId(),
					searchBean.getNiveauId());
		}

	}

	/**
	 * [BilanConsulBean.allSessionIsClosed] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:25:07
	 * @return
	 */
	public boolean allSessionIsClosed() {
		loadSituationClose();
		if (situationClose != null && situationClose.getId() != 0) {
			DeliberationSessionDto searchDto = new DeliberationSessionDto();
			searchDto.setOofId(searchBean.getOofId());
			searchDto.setPeriodeId(searchBean.getPeriodeId());
			searchDto.setAnneeAcademiqueId(searchBean.getAnneeAcademiqueId());
			deliberationList = deliberationSessionService
					.findAdvanced(searchDto);
			if (deliberationList != null) {
				for (DeliberationSessionDto del : deliberationList) {
					if (del.getSituationId() != null
							&& !del.getSituationId().equals(
									situationClose.getId())) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * [BilanConsulBean.imprimerBilanPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:56:45
	 */
	public void imprimerBilanPeriode() {
		fillCrossTable(bilanList);
		try {
			if (fileNamePeriode == null) {
				loadFileNamePeriode();
			}

			FacesContext facesContext = FacesContext.getCurrentInstance();
			String SUBREPORT_DIR = facesContext.getExternalContext()
					.getRealPath("/WEB-INF/classes")
					+ "/jasper/examen/deliberation/";
			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = SUBREPORT_DIR
					+ "bilan.jrxml";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.JASPER_PARAM_IMG_PAPS_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logopaps.png");
			params.put(Const.JASPER_PARAM_IMG_LOGO_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logo"
					+ sessionBean.getCodeEtablissement() + ".png");

			params.put(Const.JASPER_PARAM_OFFRE_FORMATION_KEY, ofLibelleLongFr);
			params.put(Const.JASPER_PARAM_ANNEE_ACADEMIQUE_KEY, anneeCode);
			params.put(Const.JASPER_PARAM_PERIODE_KEY, periodeLibelleLongFr);
			params.put(Const.JASPER_PARAM_NIVEAU_KEY, niveauLibelleLongFr);
			params.put(Const.JASPER_PARAM_TITLE_KEY, "BILAN PERIODE");
			params.put(Const.JASPER_PARAM_SUBREPORT_DIR_KEY, SUBREPORT_DIR);
			params.put(Const.JASPER_PARAM_ETABLISSEMENT_KEY,
					sessionBean.getLlLatinEtablissement());
			byte[] bytes = impressionService
					.viewPDFWithDataSource(RESOURCE_PATH_TO_INPUT_FILE_JASPER,
							params, crossCollection);
			printMgrBean.imprimer(bytes, fileNamePeriode, "pdf");

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [BilanConsulBean.fillCrossTable] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:58:11
	 * @param _bilanSessionList
	 */
	public void fillCrossTable(List<BilanSessionDto> _bilanSessionList) {
		if (_bilanSessionList == null) {
			return;
		}
		crossCollection = new ArrayList<CrossTab>();
		Integer i = 1;
		for (BilanSessionDto _bilan : _bilanSessionList) {
			CrossTab _cross = new CrossTab();
			_cross.setColumn(bilanBundle
					.getString("bilan_reporting_column_numero_inscription"));
			_cross.setRow(i.toString());
			_cross.setValue(_bilan.getNumeroInscriptionEtudiant());
			crossCollection.add(_cross);
			_cross = new CrossTab();
			_cross.setColumn(bilanBundle
					.getString("bilan_reporting_column_nom"));
			_cross.setRow(i.toString());
			_cross.setValue(_bilan.getNomLatinEtudiant());
			crossCollection.add(_cross);

			_cross = new CrossTab();
			_cross.setColumn(bilanBundle
					.getString("bilan_reporting_column_prenom"));
			_cross.setRow(i.toString());
			_cross.setValue(_bilan.getPrenomLatinEtudiant());
			crossCollection.add(_cross);

			_cross = new CrossTab();
			_cross.setColumn(bilanBundle
					.getString("bilan_reporting_column_date_naissance"));
			_cross.setRow(i.toString());
			if (_bilan.getDateNaissanceEtudiant() == null) {
				_cross.setValue("-");
			} else {
				_cross.setValue(Utility.formatDate(
						_bilan.getDateNaissanceEtudiant(), "dd/MM/yyyy"));
			}
			crossCollection.add(_cross);

			List<BilanSessionDto> sessions = _bilan.getBilanSessionDtos();
			System.out.println(sessions.size());
			if (sessions != null && sessions.size() > 1) {
				for (BilanSessionDto session : sessions) {
					if (session.getColumnIntitule() != null) {
						_cross = new CrossTab();
						_cross.setColumn(session.getColumnIntitule());
						_cross.setRow(i.toString());
						_cross.setValue(Utility.formatNote(session.getMoyenne()));
						crossCollection.add(_cross);
					}
				}
			}
			_cross = new CrossTab();
			_cross.setColumn(bilanBundle
					.getString("bilan_reporting_column_moyenne_generale"));
			_cross.setRow(i.toString());
			_cross.setValue(Utility.formatNote(_bilan.getMoyenneGenerale()));
			crossCollection.add(_cross);
			_cross = new CrossTab();
			_cross.setColumn(bilanBundle
					.getString("bilan_reporting_column_mention"));
			_cross.setRow(i.toString());
			_cross.setValue(_bilan.getMentionLibelle());
			crossCollection.add(_cross);
			if (_bilan.getTypeDecisionLibelleFr() != null) {
				_cross = new CrossTab();
				_cross.setColumn(bilanBundle
						.getString("bilan_reporting_column_decision"));
				_cross.setRow(i.toString());
				_cross.setValue(_bilan.getTypeDecisionLibelleFr());
				crossCollection.add(_cross);
			}

			i++;
		}

	}

	/**
	 * [BilanConsulBean.loadFileNamePeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:55:58
	 */
	public void loadFileNamePeriode() {
		String prefix = "";
		if (searchBean.getOofId() != null) {
			OuvertureOffreFormationDto _oof = utilBean.findOofById(searchBean
					.getOofId());
			if (_oof != null) {
				ofLibelleLongFr = _oof.getOfLibelleLongFr();
			}
		}
		if (searchBean.getPeriodeId() != null) {
			PeriodeDto _periode = utilBean.findPeriodeById(searchBean
					.getPeriodeId());
			if (_periode != null) {
				periodeLibelleLongFr = _periode.getLibelleLongLt();
				niveauLibelleLongFr = _periode.getLibelleLongFrNiveau();
				prefix = "Bilan_Période_";
			}
		}
		if (searchBean.getNiveauId() != null) {
			NiveauDto _niveau = utilBean.findNiveau(searchBean.getNiveauId());
			if (_niveau != null) {
				periodeLibelleLongFr = _niveau.getLibelleLongLt();
				prefix = "Bilan_Annuel_";
			}
		}
		if (searchBean.getAnneeAcademiqueId() != null) {

			AnneeAcademiqueDto _annee = utilBean
					.findAnneeAcademiqueById(searchBean.getAnneeAcademiqueId());
			if (_annee != null) {
				anneeCode = _annee.getCode();
			}
		}
		fileNamePeriode = prefix + ofLibelleLongFr + "_" + periodeLibelleLongFr
				+ "_" + anneeCode;
	}

	/**
	 * [BilanConsulBean.searchBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:23:21
	 * @return the searchBean
	 */
	public SearchBean getSearchBean() {
		return searchBean;
	}

	/**
	 * [BilanConsulBean.searchBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:23:21
	 * @param searchBean
	 *            the searchBean to set
	 */
	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}

	/**
	 * [BilanConsulBean.showDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:24:37
	 * @return the showDetail
	 */
	public boolean isShowDetail() {
		return showDetail;
	}

	/**
	 * [BilanConsulBean.showDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:24:37
	 * @param showDetail
	 *            the showDetail to set
	 */
	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}

	/**
	 * [BilanConsulBean.bilanSessionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:27:55
	 * @return the bilanSessionService
	 */
	public BilanSessionService getBilanSessionService() {
		return bilanSessionService;
	}

	/**
	 * [BilanConsulBean.bilanSessionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:27:55
	 * @param bilanSessionService
	 *            the bilanSessionService to set
	 */
	public void setBilanSessionService(BilanSessionService bilanSessionService) {
		this.bilanSessionService = bilanSessionService;
	}

	/**
	 * [BilanConsulBean.situationClose] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:28:16
	 * @return the situationClose
	 */
	public SituationEntiteDto getSituationClose() {
		return situationClose;
	}

	/**
	 * [BilanConsulBean.situationClose] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:28:16
	 * @param situationClose
	 *            the situationClose to set
	 */
	public void setSituationClose(SituationEntiteDto situationClose) {
		this.situationClose = situationClose;
	}

	/**
	 * [BilanConsulBean.situationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:28:37
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [BilanConsulBean.situationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:28:37
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [BilanConsulBean.deliberationSessionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:29:33
	 * @return the deliberationSessionService
	 */
	public DeliberationSessionService getDeliberationSessionService() {
		return deliberationSessionService;
	}

	/**
	 * [BilanConsulBean.deliberationSessionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:29:33
	 * @param deliberationSessionService
	 *            the deliberationSessionService to set
	 */
	public void setDeliberationSessionService(
			DeliberationSessionService deliberationSessionService) {
		this.deliberationSessionService = deliberationSessionService;
	}

	/**
	 * [BilanConsulBean.deliberationList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:30:54
	 * @return the deliberationList
	 */
	public List<DeliberationSessionDto> getDeliberationList() {
		return deliberationList;
	}

	/**
	 * [BilanConsulBean.deliberationList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 10:30:54
	 * @param deliberationList
	 *            the deliberationList to set
	 */
	public void setDeliberationList(
			List<DeliberationSessionDto> deliberationList) {
		this.deliberationList = deliberationList;
	}

	/**
	 * [BilanConsulBean.fileNamePeriode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:45:31
	 * @return the fileNamePeriode
	 */
	public String getFileNamePeriode() {
		if (fileNamePeriode == null) {
			loadFileNamePeriode();
		}
		return fileNamePeriode;
	}

	/**
	 * [BilanConsulBean.fileNamePeriode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:45:31
	 * @param fileNamePeriode
	 *            the fileNamePeriode to set
	 */
	public void setFileNamePeriode(String fileNamePeriode) {
		this.fileNamePeriode = fileNamePeriode;
	}

	/**
	 * [BilanConsulBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:48:27
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [BilanConsulBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:48:27
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [BilanConsulBean.bilanBundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:55:13
	 * @return the bilanBundle
	 */
	public ResourceBundle getBilanBundle() {
		return bilanBundle;
	}

	/**
	 * [BilanConsulBean.bilanBundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:55:13
	 * @param bilanBundle
	 *            the bilanBundle to set
	 */
	public void setBilanBundle(ResourceBundle bilanBundle) {
		this.bilanBundle = bilanBundle;
	}

	/**
	 * [BilanConsulBean.ofLibelleLongFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:55:13
	 * @return the ofLibelleLongFr
	 */
	public String getOfLibelleLongFr() {
		return ofLibelleLongFr;
	}

	/**
	 * [BilanConsulBean.ofLibelleLongFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:55:13
	 * @param ofLibelleLongFr
	 *            the ofLibelleLongFr to set
	 */
	public void setOfLibelleLongFr(String ofLibelleLongFr) {
		this.ofLibelleLongFr = ofLibelleLongFr;
	}

	/**
	 * [BilanConsulBean.periodeLibelleLongFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:55:13
	 * @return the periodeLibelleLongFr
	 */
	public String getPeriodeLibelleLongFr() {
		return periodeLibelleLongFr;
	}

	/**
	 * [BilanConsulBean.periodeLibelleLongFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:55:13
	 * @param periodeLibelleLongFr
	 *            the periodeLibelleLongFr to set
	 */
	public void setPeriodeLibelleLongFr(String periodeLibelleLongFr) {
		this.periodeLibelleLongFr = periodeLibelleLongFr;
	}

	/**
	 * [BilanConsulBean.anneeCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:55:13
	 * @return the anneeCode
	 */
	public String getAnneeCode() {
		return anneeCode;
	}

	/**
	 * [BilanConsulBean.anneeCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:55:13
	 * @param anneeCode
	 *            the anneeCode to set
	 */
	public void setAnneeCode(String anneeCode) {
		this.anneeCode = anneeCode;
	}

	/**
	 * [BilanConsulBean.crossCollection] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:58:38
	 * @return the crossCollection
	 */
	public List<CrossTab> getCrossCollection() {
		return crossCollection;
	}

	/**
	 * [BilanConsulBean.crossCollection] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:58:38
	 * @param crossCollection
	 *            the crossCollection to set
	 */
	public void setCrossCollection(List<CrossTab> crossCollection) {
		this.crossCollection = crossCollection;
	}

	/**
	 * [BilanConsulBean.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 12:00:23
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [BilanConsulBean.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 12:00:23
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [BilanConsulBean.impressionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 12:00:23
	 * @return the impressionService
	 */
	public ImpressionService getImpressionService() {
		return impressionService;
	}

	/**
	 * [BilanConsulBean.impressionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 12:00:23
	 * @param impressionService
	 *            the impressionService to set
	 */
	public void setImpressionService(ImpressionService impressionService) {
		this.impressionService = impressionService;
	}

	/**
	 * [BilanConsulBean.printMgrBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 12:00:45
	 * @return the printMgrBean
	 */
	public PrintMgrBean getPrintMgrBean() {
		return printMgrBean;
	}

	/**
	 * [BilanConsulBean.printMgrBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 12:00:45
	 * @param printMgrBean
	 *            the printMgrBean to set
	 */
	public void setPrintMgrBean(PrintMgrBean printMgrBean) {
		this.printMgrBean = printMgrBean;
	}

	/**
	 * [BilanConsulBean.bilanList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 12:54:24
	 * @return the bilanList
	 */
	public List<BilanSessionDto> getBilanList() {
		return bilanList;
	}

	/**
	 * [BilanConsulBean.bilanList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 12:54:24
	 * @param bilanList
	 *            the bilanList to set
	 */
	public void setBilanList(List<BilanSessionDto> bilanList) {
		this.bilanList = bilanList;
	}

	/**
	 * [BilanConsulBean.bilanSessionChild] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 15:04:35
	 * @param row
	 * @return
	 */
	public List<BilanSessionDto> bilanSessionChild(BilanSessionDto row) {
		if (row == null) {
			return null;
		}
		return row.getBilanSessionDtos();
	}

	/**
	 * [BilanConsulBean.niveauLibelleLongFr] Getter 
	 * @author MAKERRI Sofiane on : 11 févr. 2015  15:39:29
	 * @return the niveauLibelleLongFr
	 */
	public String getNiveauLibelleLongFr() {
		return niveauLibelleLongFr;
	}

	/**
	 * [BilanConsulBean.niveauLibelleLongFr] Setter 
	 * @author MAKERRI Sofiane on : 11 févr. 2015  15:39:29
	 * @param niveauLibelleLongFr the niveauLibelleLongFr to set
	 */
	public void setNiveauLibelleLongFr(String niveauLibelleLongFr) {
		this.niveauLibelleLongFr = niveauLibelleLongFr;
	}
	
	

}
