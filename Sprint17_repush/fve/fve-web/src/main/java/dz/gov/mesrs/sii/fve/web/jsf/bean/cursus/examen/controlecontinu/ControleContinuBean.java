/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.controlecontinu.EvalControleContinuBean.java] 
 * @author MAKERRI Sofiane on : 29 sept. 2014  18:27:06
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.controlecontinu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AffectationGroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.EvaluationControleContinuDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.NoteEvaluationControleContinuDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeParamDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AffectationGroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.cursus.AssociationGroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.cursus.GroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.examen.EvaluationControleContinuService;
import dz.gov.mesrs.sii.fve.business.service.examen.NoteEvaluationControleContinuService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AtomePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RepartitionUeService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SearchBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation.ImportNoteEtudiantModel;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.FileReaderExamenNotes;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.util.ConfigHolder;

/**
 * @author MAKERRI Sofiane on : 29 sept. 2014 18:27:06
 */
/**
 * @author MAKERRI Sofiane  on : 14 oct. 2014  15:00:09
 */
/**
 * @author MAKERRI Sofiane on : 14 oct. 2014 15:00:13
 */
@ManagedBean(name = "controleContinuBean")
@ViewScoped
public class ControleContinuBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 18:27:12
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(ControleContinuBean.class);
	private EvaluationControleContinuDto evalControleContinuDto;
	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty("#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{niveauService}")
	private NiveauService niveauService;
	@ManagedProperty("#{periodeService}")
	private PeriodeService periodeService;
	@ManagedProperty("#{atomePedagogiqueService}")
	private AtomePedagogiqueService atomePedagogiqueService;
	@ManagedProperty("#{groupePedagogiqueService}")
	private GroupePedagogiqueService groupePedagogiqueService;
	@ManagedProperty("#{associationGroupePedagogiqueService}")
	private AssociationGroupePedagogiqueService associationGroupePedagogiqueService;
	@ManagedProperty("#{affectationGroupePedagogiqueService}")
	private AffectationGroupePedagogiqueService affectationGroupePedagogiqueService;
	@ManagedProperty(value = "#{repartitionUeService}")
	private RepartitionUeService repartitionUeService;
	@ManagedProperty(value = "#{rattachementMcService}")
	private RattachementMcService rattachementMcService;
	@ManagedProperty(value = "#{evaluationControleContinuService}")
	private EvaluationControleContinuService evaluationControleContinuService;
	@ManagedProperty(value = "#{noteEvaluationControleContinuService}")
	private NoteEvaluationControleContinuService noteEvaluationControleContinuService;
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{searchBean}")
	private SearchBean searchBean;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	@ManagedProperty(value = "#{configHolder}")
	private ConfigHolder configHolder;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> ofList;
	private List<SelectItem> niveauList;
	private List<SelectItem> periodeList;
	private List<SelectItem> groupeList;
	private List<SelectItem> ncTypeEvalList;
	private Integer assocGpId;
	private boolean showDetail;
	private boolean showTable;
	private List<EvaluationControleContinuDto> evalControleContinuList;
	private ResourceBundle evalBundle;
	private List<NoteEvaluationControleContinuDto> noteEvalList;
	private List<NoteEvaluationControleContinuDto> oldNoteEvalList;
	private List<ImportNoteEtudiantModel> importNoteEtudiantModelList;
	private List<NoteEvaluationControleContinuDto> filtredNoteEval;
	private String exportFileName;
	private Double noteMax;
	private DozerBeanMapper mapper;
	private UploadedFile fileToImport;
	private String fileName;
	private String filePath;
	private boolean noteImported;
	private String periodeDateDebut;
	private String periodeDateFin;
	private boolean editMode;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	private List<SelectItem> matieres;
	private List<SelectItem> groupes;
	private List<SelectItem> periodes;
	private SituationEntiteDto situationValidate;
	private SituationEntiteDto situationCreate;
	private boolean coefficientExist;

	/**
	 * [EvalControleContinuBean.EvalControleContinuBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 18:27:06
	 */
	public ControleContinuBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		evalBundle = facesContext.getApplication().getResourceBundle(
				facesContext,
				CursusConstants.EVAL_CONTROLE_CONTINU_BUNDLE_MSG_NAME);
		String viewId = facesContext.getViewRoot().getViewId();
		editMode = false;
		if (viewId != null && viewId.toLowerCase().contains("edit")) {
			editMode = true;
		}
	}

	@PostConstruct
	public void init() {
		evalControleContinuDto = new EvaluationControleContinuDto();
		situationValidate = situationService
				.findBySituationEntiteByCodeSituation(
						UtilConstants.ENTITE_EVAL_CONTROL_CONTINU_CODE,
						UtilConstants.SITUTAION_VALIDEE_CODE);

		situationCreate = situationService
				.findBySituationEntiteByCodeSituation(
						UtilConstants.ENTITE_EVAL_CONTROL_CONTINU_CODE,
						UtilConstants.SITUTAION_CREEE_CODE);

		loadMcGrouped();

		mapper = new DozerBeanMapper();
		coefficientExist = false;
	}

	/**
	 * [EvalControleContinuBean.loading] Method
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 11:07:09
	 */
	public void loading() {
		loadMcGrouped();
		loadGroupeByAp();

	}

	/**
	 * [EvalControleContinuBean.initalization] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 17:11:34
	 */
	public void initalization() {
		// evalControleContinuList = null;
		noteImported = false;
		fileToImport = null;
		fileName = null;
		noteEvalList = null;
	}

	/**
	 * [EvalControleContinuBean.loadingEtudiant] Method
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 15:13:57
	 */
	public void loadEtudiant() {
		try {
			noteEvalList = new ArrayList<NoteEvaluationControleContinuDto>();
			if (evalControleContinuDto.getGroupePedagogiqueId() == null) {
				return;
			}
			List<AffectationGroupePedagogiqueDto> _affList = affectationGroupePedagogiqueService
					.findByGroupePedagogiqueId(evalControleContinuDto
							.getGroupePedagogiqueId());

			if (_affList != null) {
				for (AffectationGroupePedagogiqueDto _agp : _affList) {
					NoteEvaluationControleContinuDto _ncc = noteEvaluationControleContinuService
							.findByEvalIdAndAffGp(
									evalControleContinuDto.getId(),
									_agp.getId());
					if (_ncc == null) {

						_ncc = new NoteEvaluationControleContinuDto();
						mapper.map(_agp, _ncc);
						_ncc.setAffectationGpId(_agp.getId());
						_ncc.setEvaluationControleContinuId(evalControleContinuDto
								.getId());
						_ncc.setAbsent(false);
						_ncc.setId(0);
						RefIndividuDto individu = refIndividuService
								.findByIdentifiant(_ncc
										.getIndividuIdentifiant());
						if (individu != null) {
							_ncc.setEtudiantNomLatin(individu.getNomLatin());
							_ncc.setEtudiantPrenomLatin(individu
									.getPrenomLatin());
							_ncc.setIndividuId(individu.getId());
							_ncc.setEtudiantDateNaissance(individu
									.getDateNaissance());
						}

						noteEvaluationControleContinuService
								.insertOrUpdate(_ncc);
					}
					noteEvalList.add(_ncc);
				}
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [EvalControleContinuBean.loadNcTypeEval] Method
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 09:15:15
	 */
	public void loadNcTypeEval() {
		try {
			List<NomenclatureDto> list = nomenclatureService
					.findByName(Const.NC_LMD_TYPE_EVALUATION_CONTROLE_CONTINU);
			ncTypeEvalList = new ArrayList<SelectItem>();
			for (NomenclatureDto nc : list) {
				SelectItem item = new SelectItem(nc.getId(),
						nc.getLibelleLongFr());
				ncTypeEvalList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [EvalControleContinuBean.evlControleContinuDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:09:50
	 * @return the evlControleContinuDto
	 */
	public EvaluationControleContinuDto getEvalControleContinuDto() {
		return evalControleContinuDto;
	}

	/**
	 * [EvalControleContinuBean.evlControleContinuDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:09:50
	 * @param evlControleContinuDto
	 *            the evlControleContinuDto to set
	 */
	public void setEvalControleContinuDto(
			EvaluationControleContinuDto evalControleContinuDto) {
		this.evalControleContinuDto = evalControleContinuDto;
	}

	/**
	 * [EvalControleContinuBean.anneeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:10:48
	 */
	public void anneeChanged() {
		loadOffreFormationOuverte();
		ofChanged();
	}

	/**
	 * [EvalControleContinuBean.apChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:44:11
	 */
	public void groupeChanged() {
		if (searchBean.getAnneeAcademiqueId() != null
				&& searchBean.getOofId() != null
				&& evalControleContinuDto.getRattachementMcId() != null
				&& searchBean.getPeriodeId() != null
				&& evalControleContinuDto.getAssociationGroupePedagogiqueId() != null) {
			coefficientExist = false;
			EvaluationControleContinuDto searchDto = new EvaluationControleContinuDto(
					searchBean.getAnneeAcademiqueId(), searchBean.getOofId(),
					searchBean.getPeriodeId());
			searchDto.setAssociationGroupePedagogiqueId(evalControleContinuDto
					.getAssociationGroupePedagogiqueId());
			searchDto.setRattachementMcId(evalControleContinuDto
					.getRattachementMcId());
			List<EvaluationControleContinuDto> list = evalControleContinuList = evaluationControleContinuService
					.findAdvanced(searchDto);
			if (list != null && !list.isEmpty()) {
				evalControleContinuDto.setCoefficient(list.get(0)
						.getCoefficient());
				coefficientExist = true;
			}
		}
	}

	/**
	 * [EvalControleContinuBean.loadPeriodeByNiveau] Method
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 15:42:12
	 */
	public void loadPeriodeByNiveau() {
		if (searchBean.getOofId() == null) {
			return;
		}
		periodes = utilBean.loadPeriodeByNiveau(searchBean.getOofId());
	}

	/**
	 * [EvalControleContinuBean.loadOffreFormationOuverte] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:11:04
	 */
	public void loadOffreFormationOuverte() {
		ofList = new ArrayList<SelectItem>();
		try {
			if (searchBean.getAnneeAcademiqueId() == null) {
				return;
			}
			ofList = utilBean.loadOffreFormationOuverte(searchBean
					.getAnneeAcademiqueId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [EvalControleContinuBean.ofChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:12:24
	 */
	public void ofChanged() {
		// loadNiveaux();
		// loadPeriodes();
		loadPeriodeByNiveau();
		periodeChanged();

	}

	/**
	 * [EvalControleContinuBean.periodeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:12:50
	 */
	public void periodeChanged() {
		if (searchBean.getPeriodeId() != null
				&& searchBean.getAnneeAcademiqueId() != null) {
			PeriodeParamDto dateDebut = periodeService
					.findParamByCodeByPeriodByYear(
							UtilConstants.PERIODE_PARAM_CODE_DATE_DEBUT,
							searchBean.getAnneeAcademiqueId(),
							searchBean.getPeriodeId());
			if (dateDebut != null) {
				periodeDateDebut = (String) dateDebut.getValeur();
			}
			PeriodeParamDto dateFin = periodeService
					.findParamByCodeByPeriodByYear(
							UtilConstants.PERIODE_PARAM_CODE_DATE_FIN,
							searchBean.getAnneeAcademiqueId(),
							searchBean.getPeriodeId());
			if (dateFin != null) {
				periodeDateFin = (String) dateFin.getValeur();
			}
			loadMcGrouped();
			findEvalControleContinu();
		}
	}

	/**
	 * [EvalControleContinuBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [EvalControleContinuBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [EvalControleContinuBean.offreFormationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [EvalControleContinuBean.offreFormationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [EvalControleContinuBean.anneeAcademiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [EvalControleContinuBean.anneeAcademiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [EvalControleContinuBean.niveauService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [EvalControleContinuBean.niveauService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @param niveauService
	 *            the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [EvalControleContinuBean.periodeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [EvalControleContinuBean.periodeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @param periodeService
	 *            the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [EvalControleContinuBean.anneeAcademiqueList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [EvalControleContinuBean.anneeAcademiqueList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [EvalControleContinuBean.ofList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @return the ofList
	 */
	public List<SelectItem> getOfList() {
		return ofList;
	}

	/**
	 * [EvalControleContinuBean.ofList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @param ofList
	 *            the ofList to set
	 */
	public void setOfList(List<SelectItem> ofList) {
		this.ofList = ofList;
	}

	/**
	 * [EvalControleContinuBean.niveauList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @return the niveauList
	 */
	public List<SelectItem> getNiveauList() {
		return niveauList;
	}

	/**
	 * [EvalControleContinuBean.niveauList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @param niveauList
	 *            the niveauList to set
	 */
	public void setNiveauList(List<SelectItem> niveauList) {
		this.niveauList = niveauList;
	}

	/**
	 * [EvalControleContinuBean.periodeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @return the periodeList
	 */
	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	/**
	 * [EvalControleContinuBean.periodeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:13:47
	 * @param periodeList
	 *            the periodeList to set
	 */
	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	/**
	 * [EvalControleContinuBean.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:16:57
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [EvalControleContinuBean.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:16:57
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [EvalControleContinuBean.atomePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:22:01
	 * @return the atomePedagogiqueService
	 */
	public AtomePedagogiqueService getAtomePedagogiqueService() {
		return atomePedagogiqueService;
	}

	/**
	 * [EvalControleContinuBean.atomePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:22:01
	 * @param atomePedagogiqueService
	 *            the atomePedagogiqueService to set
	 */
	public void setAtomePedagogiqueService(
			AtomePedagogiqueService atomePedagogiqueService) {
		this.atomePedagogiqueService = atomePedagogiqueService;
	}

	/**
	 * [EvalControleContinuBean.findEvalControleContinu] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:24:52
	 */
	public void findEvalControleContinu() {
		try {

			loadEvalControleContinu();
			if (evalControleContinuList != null
					&& !evalControleContinuList.isEmpty()) {
				setShowTable(true);
				if (evalControleContinuList.size() == 1) {
					this.evalControleContinuDto = evalControleContinuList
							.get(0);
					loading();
					loadEtudiant();
					setShowDetail(true);
				}
				loadNcTypeEval();
			} else {
				Utility.showWarningMessage(evalBundle
						.getString("eval_controle_continu_data_table_seacrh_result_no_result"));
				setShowTable(false);
				return;
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [EvalControleContinuBean.loadEvalControleContinu] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 nov. 2014 16:08:29
	 */
	public void loadEvalControleContinu() {
		setShowDetail(false);
		setShowTable(false);
		EvaluationControleContinuDto searchDto = new EvaluationControleContinuDto(
				searchBean.getAnneeAcademiqueId(), searchBean.getOofId(),
				searchBean.getPeriodeId());
		// if (situationCreate != null) {
		// searchDto.setSituationEntiteId(situationCreate.getId());
		// }
		evalControleContinuList = evaluationControleContinuService
				.findAdvanced(searchDto);
	}

	/**
	 * [EvalControleContinuBean.showDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:25:23
	 * @return the showDetail
	 */
	public boolean isShowDetail() {
		return showDetail;
	}

	/**
	 * [EvalControleContinuBean.showDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:25:23
	 * @param showDetail
	 *            the showDetail to set
	 */
	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}

	/**
	 * [EvalControleContinuBean.evalControleContinuList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:26:19
	 * @return the evalControleContinuList
	 */
	public List<EvaluationControleContinuDto> getEvalControleContinuList() {
		return evalControleContinuList;
	}

	/**
	 * [EvalControleContinuBean.evalControleContinuList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:26:19
	 * @param evalControleContinuList
	 *            the evalControleContinuList to set
	 */
	public void setEvalControleContinuList(
			List<EvaluationControleContinuDto> evalControleContinuList) {
		this.evalControleContinuList = evalControleContinuList;
	}

	/**
	 * [EvalControleContinuBean.groupeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:44:33
	 * @return the groupeList
	 */
	public List<SelectItem> getGroupeList() {
		return groupeList;
	}

	/**
	 * [EvalControleContinuBean.groupeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:44:33
	 * @param groupeList
	 *            the groupeList to set
	 */
	public void setGroupeList(List<SelectItem> groupeList) {
		this.groupeList = groupeList;
	}

	/**
	 * [EvalControleContinuBean.groupePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:46:12
	 * @return the groupePedagogiqueService
	 */
	public GroupePedagogiqueService getGroupePedagogiqueService() {
		return groupePedagogiqueService;
	}

	/**
	 * [EvalControleContinuBean.groupePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 11:46:12
	 * @param groupePedagogiqueService
	 *            the groupePedagogiqueService to set
	 */
	public void setGroupePedagogiqueService(
			GroupePedagogiqueService groupePedagogiqueService) {
		this.groupePedagogiqueService = groupePedagogiqueService;
	}

	/**
	 * [EvalControleContinuBean.repartitionUeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 12:32:40
	 * @return the repartitionUeService
	 */
	public RepartitionUeService getRepartitionUeService() {
		return repartitionUeService;
	}

	/**
	 * [EvalControleContinuBean.repartitionUeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 12:32:40
	 * @param repartitionUeService
	 *            the repartitionUeService to set
	 */
	public void setRepartitionUeService(
			RepartitionUeService repartitionUeService) {
		this.repartitionUeService = repartitionUeService;
	}

	/**
	 * [EvalControleContinuBean.rattachementMcService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 12:32:40
	 * @return the rattachementMcService
	 */
	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	/**
	 * [EvalControleContinuBean.rattachementMcService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 12:32:40
	 * @param rattachementMcService
	 *            the rattachementMcService to set
	 */
	public void setRattachementMcService(
			RattachementMcService rattachementMcService) {
		this.rattachementMcService = rattachementMcService;
	}

	/**
	 * [EvalControleContinuBean.mcChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 12:35:43
	 */
	public void mcChanged() {
		loadGroupeByAp();
	}

	/**
	 * [EvalControleContinuBean.evaluationControleContinuService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 15:53:14
	 * @return the evaluationControleContinuService
	 */
	public EvaluationControleContinuService getEvaluationControleContinuService() {
		return evaluationControleContinuService;
	}

	/**
	 * [EvalControleContinuBean.evaluationControleContinuService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 15:53:14
	 * @param evaluationControleContinuService
	 *            the evaluationControleContinuService to set
	 */
	public void setEvaluationControleContinuService(
			EvaluationControleContinuService evaluationControleContinuService) {
		this.evaluationControleContinuService = evaluationControleContinuService;
	}

	/**
	 * [EvalControleContinuBean.newEval] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 16:21:04
	 */
	public void newEval() {
		// Integer aaId = evalControleContinuDto.getAnneeAcademiqueId();
		evalControleContinuDto = new EvaluationControleContinuDto();
		evalControleContinuDto.setAnneeAcademiqueId(searchBean
				.getAnneeAcademiqueId());
		evalControleContinuDto.setOofId(searchBean.getOofId());
		evalControleContinuDto.setPeriodeId(searchBean.getPeriodeId());
		evalControleContinuDto.setDateEvaluation(new Date());
		evalControleContinuDto.setComptablise(true);
		initalization();
		loading();
		loadNcTypeEval();
		// loadingEtudiant();
		setShowDetail(true);
		// setShowTable(true);
	}

	/**
	 * [EvalControleContinuBean.saveEval] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 16:25:15
	 */
	public void saveEval() {
		try {
			// *** verifier si l'evaluation est dejï¿½ enregistrer pour le
			// groupe
			// ****/
			if (evalControleContinuDto.getAssociationGroupePedagogiqueId() == null) {
				Utility.showErrorMessage(evalBundle
						.getString("eval_controle_continu_erreur_groupe_vide"));
				return;
			}
			/***
			 * vï¿½rifier si la date d'evaluation est entre date debut date fin
			 * de la periode TODO
			 ***/
			if (periodeDateDebut != null && periodeDateFin != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date _periodeDateDebut = sdf.parse(periodeDateDebut);
				Date _periodeDateFin = sdf.parse(periodeDateFin);
				if (evalControleContinuDto.getDateEvaluation() != null
						&& periodeDateDebut != null
						&& periodeDateFin != null
						&& evalControleContinuDto.getDateEvaluation().before(
								_periodeDateDebut)
						|| evalControleContinuDto.getDateEvaluation().after(
								_periodeDateFin)) {
					Utility.showErrorMessage(evalBundle
							.getString("eval_controle_continu_erreur_date_evaluation"));
					return;

				}
			}
			// if (dateEvaluationAlreadyExist()) {
			// Utility.showErrorMessage(evalBundle
			// .getString("eval_controle_continu_erreur_eval_existe"));
			// return;
			// }
			if (evalControleContinuDto.getId() == null) {
				SituationEntiteDto situationCree = situationService
						.findBySituationEntiteByCodeSituation(
								UtilConstants.ENTITE_EVAL_CONTROL_CONTINU_CODE,
								UtilConstants.SITUTAION_CREEE_CODE);
				if (situationCree == null || situationCree.getId() == 0) {
					Utility.showErrorMessage(evalBundle
							.getString("eval_controle_continu_erreur_situation_cree_inexistante"));
					return;
				}
				evalControleContinuDto.setSituationLibelle("Enregistré");
				evalControleContinuDto.setSituationEntiteId(situationCree
						.getId());

			}
			evaluationControleContinuService
					.insertOrUpdate(evalControleContinuDto);

			boolean status = saveNote();
			loadEvalControleContinu();
			loadEtudiant();
			setShowTable(true);
			if (status) {
				if (evalControleContinuDto.getId() == null) {
					Utility.showSuccessSaveMessage();
				} else {
					Utility.showSuccessUpdateMessage();
				}
			}
			setShowDetail(true);

		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [EvalControleContinuBean.dateEvaluationAlreadyExist] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 11:10:51
	 * @return
	 */
	public boolean dateEvaluationAlreadyExist() {
		try {
			List<EvaluationControleContinuDto> __evalList = evaluationControleContinuService
					.findByGpAssociation(evalControleContinuDto.getId(),
							evalControleContinuDto
									.getAssociationGroupePedagogiqueId());
			if (__evalList != null) {
				for (EvaluationControleContinuDto __eval : __evalList) {
					if (__eval.getDateEvaluation() != null
							&& evalControleContinuDto.getDateEvaluation() != null
							&& Utility.formatDate(__eval.getDateEvaluation(),
									Const.DATE_FORMAT_STANDARD).equals(
									Utility.formatDate(evalControleContinuDto
											.getDateEvaluation(),
											Const.DATE_FORMAT_STANDARD))) {
						return true;

					}
				}
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
		return false;
	}

	/**
	 * [EvalControleContinuBean.deleteEval] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 12:42:34
	 */
	public void deleteEval() {
		try {
			if (evalControleContinuDto.getId() != null) {
				List<NoteEvaluationControleContinuDto> _list = noteEvaluationControleContinuService
						.findByEvalId(evalControleContinuDto.getId());
				for (NoteEvaluationControleContinuDto note : _list) {
					noteEvaluationControleContinuService.remove(note);
				}
			}
			evaluationControleContinuService.remove(evalControleContinuDto);
			Utility.showSuccessDeleteMessage();
			loadEvalControleContinu();
			// if (evalControleContinuList != null
			// && evalControleContinuList.size() > 1) {
			// loadEtudiant();
			// }
		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [EvalControleContinuBean.ncTypeEvalList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 18:17:38
	 * @return the ncTypeEvalList
	 */
	public List<SelectItem> getNcTypeEvalList() {
		return ncTypeEvalList;
	}

	/**
	 * [EvalControleContinuBean.ncTypeEvalList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 18:17:38
	 * @param ncTypeEvalList
	 *            the ncTypeEvalList to set
	 */
	public void setNcTypeEvalList(List<SelectItem> ncTypeEvalList) {
		this.ncTypeEvalList = ncTypeEvalList;
	}

	/**
	 * [EvalControleContinuBean.nomenclatureService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 18:22:35
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [EvalControleContinuBean.nomenclatureService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 sept. 2014 18:22:35
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [EvalControleContinuBean.onRowSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 10:12:21
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		setShowDetail(false);
		this.evalControleContinuDto = ((EvaluationControleContinuDto) event
				.getObject());
		evalControleContinuDto.setSituationLibelle("Enregistré");
		if (situationValidate != null
				&& evalControleContinuDto.getSituationEntiteId() != null
				&& evalControleContinuDto.getSituationEntiteId().equals(
						situationValidate.getId())) {
			if (editMode) {
				Utility.showErrorMessage(evalBundle
						.getString("eval_controle_continu_erreur_modification_evaluation_valide"));
				return;
			}
			evalControleContinuDto.setSituationLibelle("ValidÃ©e");
		}
		setShowDetail(true);
		initalization();
		loading();
		loadEtudiant();

	}

	/**
	 * [EvalControleContinuBean.showTable] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 10:41:24
	 * @return the showTable
	 */
	public boolean isShowTable() {
		return showTable;
	}

	/**
	 * [EvalControleContinuBean.showTable] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 10:41:24
	 * @param showTable
	 *            the showTable to set
	 */
	public void setShowTable(boolean showTable) {
		this.showTable = showTable;
	}

	/**
	 * [EvalControleContinuBean.noteEvalList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 14:57:48
	 * @return the noteEvalList
	 */
	public List<NoteEvaluationControleContinuDto> getNoteEvalList() {
		return noteEvalList;
	}

	/**
	 * [EvalControleContinuBean.noteEvalList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 14:57:48
	 * @param noteEvalList
	 *            the noteEvalList to set
	 */
	public void setNoteEvalList(
			List<NoteEvaluationControleContinuDto> noteEvalList) {
		this.noteEvalList = noteEvalList;
	}

	/**
	 * [EvalControleContinuBean.filtredNoteEval] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 14:58:40
	 * @return the filtredNoteEval
	 */
	public List<NoteEvaluationControleContinuDto> getFiltredNoteEval() {
		return filtredNoteEval;
	}

	/**
	 * [EvalControleContinuBean.filtredNoteEval] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 14:58:40
	 * @param filtredNoteEval
	 *            the filtredNoteEval to set
	 */
	public void setFiltredNoteEval(
			List<NoteEvaluationControleContinuDto> filtredNoteEval) {
		this.filtredNoteEval = filtredNoteEval;
	}

	/**
	 * [EvalControleContinuBean.exportFileName] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 15:05:50
	 * @return the exportFileName
	 */
	public String getExportFileName() {
		exportFileName = " eval_controle_continu_"
				+ evalControleContinuDto.getGpNomAffichage() + "_"
				+ evalControleContinuDto.getRefCodeTypeAp();
		return exportFileName;
	}

	/**
	 * [EvalControleContinuBean.exportFileName] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 15:05:50
	 * @param exportFileName
	 *            the exportFileName to set
	 */
	public void setExportFileName(String exportFileName) {
		this.exportFileName = exportFileName;
	}

	/**
	 * [EvalControleContinuBean.noteMax] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 15:10:54
	 * @return the noteMax
	 */
	public Double getNoteMax() {
		noteMax = 20.0;
		if (evalControleContinuDto != null
				&& evalControleContinuDto.getRattachementMcNoteDeBase() != null) {
			noteMax = evalControleContinuDto.getRattachementMcNoteDeBase();
		}
		return noteMax;
	}

	/**
	 * [EvalControleContinuBean.noteMax] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 15:10:54
	 * @param noteMax
	 *            the noteMax to set
	 */
	public void setNoteMax(Double noteMax) {
		this.noteMax = noteMax;
	}

	/**
	 * [EvalControleContinuBean.affectationGroupePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 15:15:43
	 * @return the affectationGroupePedagogiqueService
	 */
	public AffectationGroupePedagogiqueService getAffectationGroupePedagogiqueService() {
		return affectationGroupePedagogiqueService;
	}

	/**
	 * [EvalControleContinuBean.affectationGroupePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 15:15:43
	 * @param affectationGroupePedagogiqueService
	 *            the affectationGroupePedagogiqueService to set
	 */
	public void setAffectationGroupePedagogiqueService(
			AffectationGroupePedagogiqueService affectationGroupePedagogiqueService) {
		this.affectationGroupePedagogiqueService = affectationGroupePedagogiqueService;
	}

	/**
	 * [EvalControleContinuBean.noteEvaluationControleContinuService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 15:35:11
	 * @return the noteEvaluationControleContinuService
	 */
	public NoteEvaluationControleContinuService getNoteEvaluationControleContinuService() {
		return noteEvaluationControleContinuService;
	}

	/**
	 * [EvalControleContinuBean.noteEvaluationControleContinuService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 15:35:11
	 * @param noteEvaluationControleContinuService
	 *            the noteEvaluationControleContinuService to set
	 */
	public void setNoteEvaluationControleContinuService(
			NoteEvaluationControleContinuService noteEvaluationControleContinuService) {
		this.noteEvaluationControleContinuService = noteEvaluationControleContinuService;
	}

	/**
	 * [EvalControleContinuBean.saveNote] Method
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 16:34:26
	 */
	public boolean saveNote() {
		try {
			if (noteEvalList != null) {
				for (NoteEvaluationControleContinuDto _necc : noteEvalList) {
					if (_necc.getAbsent()
							&& (_necc.getNote() != null && !_necc.getNote()
									.equals(0.0))) {
						Utility.showErrorMessage(evalBundle
								.getString("eval_controle_continu_erreur_etudiant_basent")
								+ " ["
								+ _necc.getEtudiantNomLatin()
								+ " "
								+ _necc.getEtudiantPrenomLatin() + "]");
						return false;

					}

					if (_necc.getNote() == null && !_necc.getAbsent()) {
						Utility.showErrorMessage(evalBundle
								.getString("eval_controle_continu_erreur_note_session_vide")
								+ " ["
								+ _necc.getEtudiantNomLatin()
								+ " "
								+ _necc.getEtudiantPrenomLatin() + "]");
						return false;
					}
					if (_necc.getAbsent()) {
						_necc.setNote(0.0);
						_necc.setFormatNote("00.00");
					}
					noteEvaluationControleContinuService.insertOrUpdate(_necc);

				}
				// Utility.showSuccessUpdateMessage();
				// return;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			Utility.showErrorMessage(e.getMessage());
		}
		return true;
	}

	/**
	 * [EvalControleContinuBean.associationGroupePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 11:41:21
	 * @return the associationGroupePedagogiqueService
	 */
	public AssociationGroupePedagogiqueService getAssociationGroupePedagogiqueService() {
		return associationGroupePedagogiqueService;
	}

	/**
	 * [EvalControleContinuBean.associationGroupePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 11:41:21
	 * @param associationGroupePedagogiqueService
	 *            the associationGroupePedagogiqueService to set
	 */
	public void setAssociationGroupePedagogiqueService(
			AssociationGroupePedagogiqueService associationGroupePedagogiqueService) {
		this.associationGroupePedagogiqueService = associationGroupePedagogiqueService;
	}

	/**
	 * [EvalControleContinuBean.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 14:06:57
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [EvalControleContinuBean.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 14:06:57
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [EvalControleContinuBean.refIndividuService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 14:09:01
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [EvalControleContinuBean.refIndividuService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 14:09:01
	 * @param refIndividuService
	 *            the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [EvalControleContinuBean.handleFileUpload] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:10:55
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {

		if (event.getFile().equals(null)) {
			Utility.showErrorMessage("Aucun fichier selectionne !");
		}
		try {
			fileToImport = event.getFile();
			fileName = event.getFile().getFileName();
			uploadFile();
		} catch (Exception e) {
			Utility.showErrorMessage("Erreur lors du chargement du fichier.");
		}

	}

	/**
	 * [EvalControleContinuBean.uploadFile] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:11:25
	 */
	private void uploadFile() {

		InputStream inputStream = null;
		try {
			this.filePath = getUploadFolderPath() + fileName;
			inputStream = fileToImport.getInputstream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("url----------" + filePath);

		OutputStream out = null;
		try {
			out = new FileOutputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int read = 0;
		byte[] bytes = new byte[4096];

		try {
			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			inputStream.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * [EvalControleContinuBean.getUploadFolderPath] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:14:04
	 * @return
	 */
	private String getUploadFolderPath() {
		return configHolder.getDocumentTempFolder() + "/";
	}

	/**
	 * [EvalControleContinuBean.fileToImport] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:10:47
	 * @return the fileToImport
	 */
	public UploadedFile getFileToImport() {
		return fileToImport;
	}

	/**
	 * [EvalControleContinuBean.fileToImport] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:10:47
	 * @param fileToImport
	 *            the fileToImport to set
	 */
	public void setFileToImport(UploadedFile fileToImport) {
		this.fileToImport = fileToImport;
	}

	/**
	 * [EvalControleContinuBean.fileName] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:10:47
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * [EvalControleContinuBean.fileName] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:10:47
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * [EvalControleContinuBean.filePath] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:10:47
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * [EvalControleContinuBean.filePath] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:10:47
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public ConfigHolder getConfigHolder() {
		return configHolder;
	}

	/**
	 * [EvalControleContinuBean.importFile] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:17:31
	 */
	public void importFile() {
		try {
			if (this.fileName != null && (!this.fileName.trim().equals(""))) {
				List<NoteEvaluationControleContinuDto> _tmp = noteEvalList;
				this.filePath = getUploadFolderPath() + fileName;
				FileReaderExamenNotes fr = new FileReaderExamenNotes();
				fr.processOneSheet(filePath);
				importNoteEtudiantModelList = FileReaderExamenNotes.importNoteEtudiantModelList;
				if (importNoteEtudiantModelList != null && _tmp != null) {
					if (importNoteEtudiantModelList.size() != noteEvalList
							.size()) {
						Utility.showErrorMessage(evalBundle
								.getString("eval_controle_continu_erreur_taille_non_identique"));
						return;
					}
					oldNoteEvalList = new ArrayList<NoteEvaluationControleContinuDto>();

					for (ImportNoteEtudiantModel _import : importNoteEtudiantModelList) {
						for (NoteEvaluationControleContinuDto _actuel : _tmp) {
							noteImported = true;
							if (_import.getNumeroMatricule() != null
									&& _import.getNumeroMatricule().equals(
											_actuel.getNumeroMatricule())) {
								oldNoteEvalList
										.add(new NoteEvaluationControleContinuDto(
												_actuel));
								/****
								 * verifier si la note est superieur a la note
								 * de base
								 ****/
								if (_import.getNote() != null) {
									if (_import.getNote() != 0
											&& _actuel.getAbsent()) {
										/***
										 * on a donner une note a un etudiant
										 * absent
										 ****/
										Utility.showErrorMessage(evalBundle
												.getString("eval_controle_continu_erreur_etudiant_basent")
												+ " ["
												+ _actuel.getEtudiantNomLatin()
												+ " "
												+ _actuel
														.getEtudiantPrenomLatin()
												+ "]");
										return;
									}

									if (_import.getNote() > evalControleContinuDto
											.getRattachementMcNoteDeBase()) {
										Utility.showErrorMessage(evalBundle
												.getString("eval_controle_continu_erreur_note_superieur_note_base"));
										return;
									}
									_actuel.setNote(_import.getNote());
									_actuel.setFormatNote(Utility
											.formatNote(_import.getNote()));
								}

							}
						}
					}
					noteEvalList = _tmp;

					Utility.showSuccessMessage(evalBundle
							.getString("eval_controle_continu_import_success"));
				}

			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * [EvalControleContinuBean.importNoteEtudiantModelList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:49:19
	 * @return the importNoteEtudiantModelList
	 */
	public List<ImportNoteEtudiantModel> getImportNoteEtudiantModelList() {
		return importNoteEtudiantModelList;
	}

	/**
	 * [EvalControleContinuBean.importNoteEtudiantModelList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:49:19
	 * @param importNoteEtudiantModelList
	 *            the importNoteEtudiantModelList to set
	 */
	public void setImportNoteEtudiantModelList(
			List<ImportNoteEtudiantModel> importNoteEtudiantModelList) {
		this.importNoteEtudiantModelList = importNoteEtudiantModelList;
	}

	/**
	 * [EvalControleContinuBean.cancelImportation] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 16:08:23
	 */
	public void cancelImportation() {
		noteEvalList = oldNoteEvalList;
		noteImported = false;
	}

	/**
	 * [EvalControleContinuBean.oldNoteEvalList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 16:07:39
	 * @return the oldNoteEvalList
	 */
	public List<NoteEvaluationControleContinuDto> getOldNoteEvalList() {
		return oldNoteEvalList;
	}

	/**
	 * [EvalControleContinuBean.oldNoteEvalList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 16:07:40
	 * @param oldNoteEvalList
	 *            the oldNoteEvalList to set
	 */
	public void setOldNoteEvalList(
			List<NoteEvaluationControleContinuDto> oldNoteEvalList) {
		this.oldNoteEvalList = oldNoteEvalList;
		noteImported = false;
	}

	/**
	 * [EvalControleContinuBean.noteImported] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 16:34:44
	 * @return the noteImported
	 */
	public boolean isNoteImported() {
		return noteImported;
	}

	/**
	 * [EvalControleContinuBean.noteImported] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 16:34:44
	 * @param noteImported
	 *            the noteImported to set
	 */
	public void setNoteImported(boolean noteImported) {
		this.noteImported = noteImported;
	}

	/**
	 * [EvalControleContinuBean.periodeDateDebut] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 12:02:20
	 * @return the periodeDateDebut
	 */
	public String getPeriodeDateDebut() {
		return periodeDateDebut;
	}

	/**
	 * [EvalControleContinuBean.periodeDateDebut] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 12:02:20
	 * @param periodeDateDebut
	 *            the periodeDateDebut to set
	 */
	public void setPeriodeDateDebut(String periodeDateDebut) {
		this.periodeDateDebut = periodeDateDebut;
	}

	/**
	 * [EvalControleContinuBean.periodeDateFin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 12:02:20
	 * @return the periodeDateFin
	 */
	public String getPeriodeDateFin() {
		return periodeDateFin;
	}

	/**
	 * [EvalControleContinuBean.periodeDateFin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 12:02:20
	 * @param periodeDateFin
	 *            the periodeDateFin to set
	 */
	public void setPeriodeDateFin(String periodeDateFin) {
		this.periodeDateFin = periodeDateFin;
	}

	/**
	 * [EvalControleContinuBean.editMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 14:23:00
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [EvalControleContinuBean.editMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 14:23:00
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * [EvalControleContinuBean.situationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 14:58:32
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [EvalControleContinuBean.situationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 14:58:32
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [EvalControleContinuBean.loadMcGrouped] Method
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 15:00:19
	 */
	public void loadMcGrouped() {

		if (searchBean.getOofId() == null || searchBean.getPeriodeId() == null) {
			return;
		}
		matieres = utilBean.loadMcGroupedByUe(searchBean.getOofId(),
				searchBean.getPeriodeId());
	}

	/**
	 * [EvalControleContinuBean.loadGroupeByAp] Method
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 15:32:41
	 */
	public void loadGroupeByAp() {
		groupes = utilBean.loadGroupePedagogiqueByAp(searchBean.getOofId(),
				searchBean.getPeriodeId(),
				evalControleContinuDto.getRattachementMcId());
	}

	/**
	 * [EvalControleContinuBean.matieres] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 14:59:21
	 * @return the matieres
	 */
	public List<SelectItem> getMatieres() {
		return matieres;
	}

	/**
	 * [EvalControleContinuBean.matieres] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 14:59:21
	 * @param matieres
	 *            the matieres to set
	 */
	public void setMatieres(List<SelectItem> matieres) {
		this.matieres = matieres;
	}

	/**
	 * [EvalControleContinuBean.groupes] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 15:26:15
	 * @return the groupes
	 */
	public List<SelectItem> getGroupes() {
		return groupes;
	}

	/**
	 * [EvalControleContinuBean.groupes] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 15:26:15
	 * @param groupes
	 *            the groupes to set
	 */
	public void setGroupes(List<SelectItem> groupes) {
		this.groupes = groupes;
	}

	/**
	 * [EvalControleContinuBean.periodes] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 15:37:49
	 * @return the periodes
	 */
	public List<SelectItem> getPeriodes() {
		return periodes;
	}

	/**
	 * [EvalControleContinuBean.periodes] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 15:37:49
	 * @param periodes
	 *            the periodes to set
	 */
	public void setPeriodes(List<SelectItem> periodes) {
		this.periodes = periodes;
	}

	/**
	 * [EvalControleContinuBean.assocGpId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 16:44:40
	 * @return the assocGpId
	 */
	public Integer getAssocGpId() {
		return assocGpId;
	}

	/**
	 * [EvalControleContinuBean.assocGpId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 16:44:40
	 * @param assocGpId
	 *            the assocGpId to set
	 */
	public void setAssocGpId(Integer assocGpId) {
		this.assocGpId = assocGpId;
	}

	/**
	 * [EvalControleContinuBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:43:36
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [EvalControleContinuBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:43:36
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [EvalControleContinuBean.situationValidate] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 15:13:48
	 * @return the situationValidate
	 */
	public SituationEntiteDto getSituationValidate() {
		return situationValidate;
	}

	/**
	 * [EvalControleContinuBean.situationValidate] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 15:13:48
	 * @param situationValidate
	 *            the situationValidate to set
	 */
	public void setSituationValidate(SituationEntiteDto situationValidate) {
		this.situationValidate = situationValidate;
	}

	/**
	 * [EvalControleContinuBean.situationCreate] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 nov. 2014 12:08:10
	 * @return the situationCreate
	 */
	public SituationEntiteDto getSituationCreate() {
		return situationCreate;
	}

	/**
	 * [EvalControleContinuBean.situationCreate] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 nov. 2014 12:08:10
	 * @param situationCreate
	 *            the situationCreate to set
	 */
	public void setSituationCreate(SituationEntiteDto situationCreate) {
		this.situationCreate = situationCreate;
	}

	/**
	 * [EvalControleContinuBean.coefficientExist] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 nov. 2014 16:32:11
	 * @return the coefficientExist
	 */
	public boolean isCoefficientExist() {
		return coefficientExist;
	}

	/**
	 * [EvalControleContinuBean.coefficientExist] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 nov. 2014 16:32:11
	 * @param coefficientExist
	 *            the coefficientExist to set
	 */
	public void setCoefficientExist(boolean coefficientExist) {
		this.coefficientExist = coefficientExist;
	}

	public void setConfigHolder(ConfigHolder configHolder) {
		this.configHolder = configHolder;
	}

	/**
	 * [EvalControleContinuBean.searchBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 janv. 2015 11:12:15
	 * @return the searchBean
	 */
	public SearchBean getSearchBean() {
		return searchBean;
	}

	/**
	 * [EvalControleContinuBean.searchBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 janv. 2015 11:12:15
	 * @param searchBean
	 *            the searchBean to set
	 */
	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}

}
