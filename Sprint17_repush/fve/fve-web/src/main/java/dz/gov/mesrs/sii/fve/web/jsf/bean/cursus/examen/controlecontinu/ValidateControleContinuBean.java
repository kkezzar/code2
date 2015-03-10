/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.controlecontinu.ValidateControleContinuBean.java] 
 * @author MAKERRI Sofiane on : 13 oct. 2014  17:28:33
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.controlecontinu;

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

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.data.util.NoteMoyenneAp;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AffectationGroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanControleContinuDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.EvaluationControleContinuDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.NoteEvaluationControleContinuDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AtomePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AffectationGroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.cursus.AssociationGroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanControleContinuService;
import dz.gov.mesrs.sii.fve.business.service.examen.EvaluationControleContinuService;
import dz.gov.mesrs.sii.fve.business.service.examen.NoteEvaluationControleContinuService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AtomePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RepartitionUeService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UeMcModel;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;

/**
 * @author MAKERRI Sofiane on : 13 oct. 2014 17:28:33
 */
@ManagedBean(name = "validateControleContinuBean")
@ViewScoped
public class ValidateControleContinuBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 17:28:59
	 */
	private static final long serialVersionUID = 1L;
	private List<RattachementMcDto> mcList;
	@ManagedProperty(value = "#{rattachementMcService}")
	private RattachementMcService rattachementMcService;
	@ManagedProperty(value = "#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty(value = "#{repartitionUeService}")
	private RepartitionUeService repartitionUeService;
	@ManagedProperty(value = "#{noteEvaluationControleContinuService}")
	private NoteEvaluationControleContinuService noteEvaluationControleContinuService;
	@ManagedProperty(value = "#{affectationGroupePedagogiqueService}")
	private AffectationGroupePedagogiqueService affectationGroupePedagogiqueService;
	@ManagedProperty(value = "#{atomePedagogiqueService}")
	private AtomePedagogiqueService atomePedagogiqueService;
	@ManagedProperty("#{associationGroupePedagogiqueService}")
	private AssociationGroupePedagogiqueService associationGroupePedagogiqueService;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty(value = "#{evaluationControleContinuService}")
	private EvaluationControleContinuService evaluationControleContinuService;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	@ManagedProperty(value = "#{bilanControleContinuService}")
	private BilanControleContinuService bilanControleContinuService;
	private List<UeMcModel> ueMcModelList;
	private RattachementMcDto rattachementMcDto;
	private List<NoteMoyenneAp> noteMoyenneApList;
	private List<AtomePedagogiqueDto> apList;
	private List<NoteEvaluationControleContinuDto> noteApList;
	private List<SelectItem> matieres;
	List<MoyenneControleContinuModel> moyenneCCList;
	private boolean showTable;
	private Integer rattachementMcId;
	private Integer gpId;
	private Integer oofId;
	private Integer periodeId;
	private boolean editMode;
	private Integer anneeAcademiqueId;
	private List<SelectItem> ofList;
	private List<SelectItem> periodes;
	private List<SelectItem> groupeList;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	private List<SelectItem> anneeAcademiqueList;
	private List<NoteApGroupModel> selectedNotes;
	private String nomPrenomEtudiant;
	private final ResourceBundle evalBundle;
	private boolean validated;
	private SituationEntiteDto situationValid;

	/**
	 * [ValidateControleContinuBean.ValidateControleContinuBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 17:28:33
	 */
	public ValidateControleContinuBean() {
		super();
		rattachementMcDto = new RattachementMcDto();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		evalBundle = facesContext.getApplication().getResourceBundle(facesContext,
		        CursusConstants.EVAL_CONTROLE_CONTINU_BUNDLE_MSG_NAME);
		String viewId = facesContext.getViewRoot().getViewId();
		editMode = false;
		if (viewId != null && viewId.toLowerCase().contains("edit")) {
			editMode = true;
		}

	}

	@PostConstruct
	public void init() {
		showTable = false;
		if (!editMode) {
			loadAnneeAcademique();
		} 
		anneeAcademiqueId = sessionBean.getIdAnneeAcademique();
		loadOffreFormationOuverte();
		loadSituationValid();
	}

	/**
	 * [ValidateControleContinuBean.loadSituationValid] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 nov. 2014 17:18:30
	 */
	public void loadSituationValid() {
		situationValid = situationService.findBySituationEntiteByCodeSituation(
		        UtilConstants.ENTITE_EVAL_CONTROL_CONTINU_CODE, UtilConstants.SITUTAION_VALIDEE_CODE);

	}

	/**
	 * [ValidateControleContinuBean.anneeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:52:10
	 */
	public void anneeChanged() {
		loadOffreFormationOuverte();
		showTable = false;

	}

	/**
	 * [ValidateControleContinuBean.ofChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:52:17
	 */
	public void ofChanged() {
		showTable = false;
		periodeId = null;
		rattachementMcId = null;
		gpId = null;
		loadPeriodeByNiveau();
		periodeChanged();
		showTable = false;

	}

	/**
	 * [ValidateControleContinuBean.loadPeriodeByNiveau] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:52:32
	 */
	public void loadPeriodeByNiveau() {
		if (oofId == null) {
			return;
		}
		periodes = utilBean.loadPeriodeByNiveau(oofId);
	}

	/**
	 * [ValidateControleContinuBean.loadOffreFormationOuverte] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:49:03
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
	 * [ValidateControleContinuBean.periodeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:54:06
	 */
	public void periodeChanged() {
		showTable = false;
		rattachementMcId = null;
		gpId = null;
		if (periodeId != null && anneeAcademiqueId != null) {
			loadMcGrouped();
		}
		groupeChanged();
		showTable = false;
	}

	/**
	 * [ValidateControleContinuBean.groupeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:57:04
	 */
	public void groupeChanged() {
		if (anneeAcademiqueId != null && oofId != null && gpId != null && rattachementMcId != null) {
			validated = false;
			EvaluationControleContinuDto searchDto = new EvaluationControleContinuDto();
			searchDto.setAnneeAcademiqueId(anneeAcademiqueId);
			searchDto.setOofId(oofId);
			searchDto.setGroupePedagogiqueId(gpId);
			searchDto.setRattachementMcId(rattachementMcId);
			if (situationValid != null) {
				searchDto.setSituationEntiteId(situationValid.getId());
			}
			List<EvaluationControleContinuDto> evlList = evaluationControleContinuService.findAdvanced(searchDto);
			if (evlList != null && !evlList.isEmpty()) {
				validated = true;
			}

			findEval();
		}
	}

	/**
	 * [ValidateControleContinuBean.loadMcGrouped] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:54:21
	 */
	public void loadMcGrouped() {

		if (oofId == null || periodeId == null) {
			return;
		}
		matieres = utilBean.loadMcGroupedByUe(oofId, periodeId);
	}

	/**
	 * [ValidateControleContinuBean.mcChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:55:07
	 */
	public void mcChanged() {
		showTable = false;
		gpId = null;
		loadGroupePedagogique();
		groupeChanged();
	}

	/**
	 * [ValidateControleContinuBean.loadGroupePedagogique] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:55:31
	 */
	public void loadGroupePedagogique() {
		try {
			if (rattachementMcId != null) {
				List<GroupePedagogiqueDto> assocList = associationGroupePedagogiqueService.findByRattachementMcId(
				        oofId, periodeId, rattachementMcId);
				groupeList = new ArrayList<SelectItem>();
				if (assocList != null) {
					for (GroupePedagogiqueDto assoc : assocList) {
						SelectItem item = new SelectItem(assoc.getId(), assoc.getNomAffichage());
						groupeList.add(item);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [ValidateControleContinuBean.mcList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 17:42:23
	 * @return the mcList
	 */
	public List<RattachementMcDto> getMcList() {
		return mcList;
	}

	/**
	 * [ValidateControleContinuBean.mcList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 17:42:23
	 * @param mcList
	 *            the mcList to set
	 */
	public void setMcList(List<RattachementMcDto> mcList) {
		this.mcList = mcList;
	}

	/**
	 * [ValidateControleContinuBean.validateEval] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 17:31:37
	 */
	public void validateEval() {
		try {
			validated = false;
			if (changeSituationEval(UtilConstants.SITUTAION_VALIDEE_CODE))

			{
				Utility.showSuccessMessage(evalBundle.getString("eval_controle_continu_validation_success"));
				validated = true;
				if (validated) {
					saveBilanCC();
				}
				return;
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			return;
		}
	}

	/**
	 * [ValidateControleContinuBean.changeSituationEval] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 15:00:14
	 * @param situationCode
	 * @return
	 */
	public boolean changeSituationEval(String situationCode) {
		try {
			if (anneeAcademiqueId == null || oofId == null || gpId == null || rattachementMcId == null) {
				Utility.showErrorMessage("Impossible de valider les evaluation de controle continu");
				return false;
			}

			EvaluationControleContinuDto searchDto = new EvaluationControleContinuDto();
			searchDto.setAnneeAcademiqueId(anneeAcademiqueId);
			searchDto.setOofId(oofId);
			searchDto.setGroupePedagogiqueId(gpId);
			searchDto.setRattachementMcId(rattachementMcId);

			List<EvaluationControleContinuDto> evlList = evaluationControleContinuService.findAdvanced(searchDto);
			SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
			        UtilConstants.ENTITE_EVAL_CONTROL_CONTINU_CODE, situationCode);
			if (situation == null || situation.getId() == 0) {
				Utility.showErrorMessage(evalBundle
				        .getString("eval_controle_continu_erreur_situation_valide_inexistante"));
				return false;
			}

			if (evlList != null) {
				for (EvaluationControleContinuDto ecc : evlList) {
					if (ecc.getSituationEntiteId() == null || !ecc.getSituationEntiteId().equals(situation.getId())) {
						ecc.setSituationEntiteId(situation.getId());
						evaluationControleContinuService.insertOrUpdate(ecc);

					}
				}

				return true;
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			return false;
		}
		return false;
	}

	/**
	 * [ValidateControleContinuBean.saveBilanCC] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:33:48
	 */
	public void saveBilanCC() {
		if (moyenneCCList != null) {
			for (MoyenneControleContinuModel napg : moyenneCCList) {
				if (napg.getAffGroupePedagogique() != null && napg.getAffGroupePedagogique().getId() != null) {
					BilanControleContinuDto bilanControleContinuDto = bilanControleContinuService.findUnique(
					        anneeAcademiqueId, oofId, periodeId, napg.getAffGroupePedagogique().getId(),
					        rattachementMcId);

					if (bilanControleContinuDto == null) {
						bilanControleContinuDto = new BilanControleContinuDto();
					}
					bilanControleContinuDto.setPeriodeId(periodeId);
					bilanControleContinuDto.setAnneeAcademiqueId(anneeAcademiqueId);
					bilanControleContinuDto.setOofId(oofId);
					bilanControleContinuDto.setNote(Utility.double2position(napg.getMoyenneMc()));
					bilanControleContinuDto.setRattachementMcId(rattachementMcId);
					bilanControleContinuDto.setAffectationGroupePedagogiqueId(napg.getAffGroupePedagogique().getId());
					bilanControleContinuService.insertOrUpdate(bilanControleContinuDto);
				}
			}
		}

	}

	/**
	 * [ValidateControleContinuBean.findEval] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 12:49:33
	 */
	public void findEval() {
		calculMoyenneGenerale();
		calculNote();
		if (gpId != null) {
			List<AffectationGroupePedagogiqueDto> affList = affectationGroupePedagogiqueService
			        .findByGroupePedagogiqueId(gpId);
			if (affList != null) {
				moyenneCCList = new ArrayList<MoyenneControleContinuModel>();
				for (AffectationGroupePedagogiqueDto affGp : affList) {
					MoyenneControleContinuModel mccm = new MoyenneControleContinuModel();
					Double moyenneMc = 0.0;
					Double sumNoteMc = 0.0;
					Double sumCoefficientMc = 0.0;
					mccm.setAffGroupePedagogique(affGp);
					/***** set moyenne generale *****/
					// if (noteMoyenneApList != null) {
					// List<NoteMoyenneAp> moyenneByApList = new
					// ArrayList<NoteMoyenneAp>();
					// for (NoteMoyenneAp noteByAp : noteMoyenneApList) {
					// if (noteByAp.getAffGroupePedagogiqueId() != null
					// && affGp.getId()
					// .equals(noteByAp
					// .getAffGroupePedagogiqueId())) {
					//
					// moyenneByApList.add(noteByAp);
					//
					// }
					// }
					// mccm.setMoyenneEvalAp(moyenneByApList);
					// }
					/***** set note d�taill�s *****/
					if (noteApList != null) {

						if (apList != null) {
							// List<NoteEvaluationControleContinuDto>
							// moyenneByApList = new
							// ArrayList<NoteEvaluationControleContinuDto>();
							List<NoteApGroupModel> noteApGroupList = new ArrayList<NoteApGroupModel>();
							sumNoteMc = 0.0;
							sumCoefficientMc = 0.0;
							for (AtomePedagogiqueDto _atome : apList) {
								NoteApGroupModel noteApGroup = new NoteApGroupModel();
								NoteEvaluationControleContinuDto _moyenne = new NoteEvaluationControleContinuDto();
								_moyenne.setApId(_atome.getId());
								_moyenne.setApCode(_atome.getNcTypeApCode());
								Double sumNote = 0.0;
								Double sumCoefficient = 0.0;

								List<NoteEvaluationControleContinuDto> noteList = new ArrayList<NoteEvaluationControleContinuDto>();
								for (NoteEvaluationControleContinuDto note : noteApList) {
									if (note.getAffectationGpId() != null && note.getApId() == _atome.getId()
									        && affGp.getId().equals(note.getAffectationGpId())) {
										if (note.getNote() == null) {
											note.setNote(0.0);
											note.setFormatNote("00.00");
										}
										if (note.getComptablise() && !note.getAbsent()) {
											sumNote = sumNote + note.getNote() * note.getCoefficient();
										}
										if (_moyenne.getApCode().equals(note.getApCode())) {
											_moyenne.setCoefficient(note.getCoefficient());
											_moyenne.setFormatCoefficient(Utility.formatNote(Utility
											        .double2position(_moyenne.getCoefficient())));
											note.setFormatNote(Utility.formatNote(Utility.double2position(note
											        .getNote())));
											note.setFormatCoefficient(Utility.formatNote(Utility.double2position(note
											        .getCoefficient())));
											noteList.add(note);
											sumCoefficient = sumCoefficient + note.getCoefficient();
										}

									}
								}
								_moyenne.setSomNote(sumNote);
								_moyenne.setSomCoefficient(sumCoefficient);
								if (sumCoefficient != 0) {
									_moyenne.setMoyenneAp(sumNote / sumCoefficient);
									_moyenne.setFormatMoyenneAp(Utility.formatNote(Utility.double2position(_moyenne
									        .getMoyenneAp())));
									sumNoteMc = sumNoteMc + (_moyenne.getMoyenneAp() * _moyenne.getCoefficient());
									sumCoefficientMc = sumCoefficientMc + _moyenne.getCoefficient();
								}
								noteApGroup.setMoyenneAp(_moyenne);
								noteApGroup.setNoteAp(noteList);
								noteApGroupList.add(noteApGroup);
							}
							mccm.setNoteApList(noteApGroupList);
						}
						// mccm.setNoteApList(noteApGroupList);
					}
					// noteApList = noteEvaluationControleContinuService
					// .findAdvanced(serachDto);
					moyenneMc = moyenneMc + (sumNoteMc / sumCoefficientMc);
					mccm.setMoyenneMc(Utility.double2position(moyenneMc));
					mccm.setFormatMoyenneMc(Utility.formatNote(mccm.getMoyenneMc()));
					moyenneCCList.add(mccm);
				}
			} else {
				Utility.showWarningMessage(evalBundle
				        .getString("eval_controle_continu_data_table_seacrh_result_no_result"));
				return;
			}
		}

		showTable = true;
	}

	/**
	 * [ValidateControleContinuBean.invalidateEval] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 17:32:16
	 */
	public void invalidateEval() {
		try {
			if (changeSituationEval(UtilConstants.SITUTAION_CREEE_CODE))

			{
				Utility.showSuccessMessage(evalBundle.getString("eval_controle_continu_invalidation_success"));
				validated = false;
				return;
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			return;
		}
	}

	/**
	 * [ValidateControleContinuBean.loadAnneeAcademique] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:51:00
	 */
	public void loadAnneeAcademique() {
		try {
			anneeAcademiqueList = utilBean.loadAnneeAcademique();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [ValidateControleContinuBean.onRowSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 17:34:08
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		this.rattachementMcDto = ((RattachementMcDto) event.getObject());
		if (rattachementMcDto != null && rattachementMcDto.getId() != 0) {

			NoteEvaluationControleContinuDto serachDto = new NoteEvaluationControleContinuDto();
			serachDto.setAnneeAcademiqueId(anneeAcademiqueId);
			serachDto.setOofId(oofId);
			serachDto.setPeriodeId(periodeId);
			// serachDto.setComptablise(true);
			serachDto.setRattachementMcId(rattachementMcId);
			noteMoyenneApList = noteEvaluationControleContinuService.calculMoyenne(serachDto);
			noteApList = noteEvaluationControleContinuService.findAdvanced(serachDto);
			System.out.println(noteMoyenneApList);
		}
	}

	/**
	 * [ValidateControleContinuBean.calculMoyenne] Method
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 16:04:59
	 */
	public void calculMoyenneGenerale() {
		NoteEvaluationControleContinuDto serachDto = new NoteEvaluationControleContinuDto();
		serachDto.setAnneeAcademiqueId(anneeAcademiqueId);
		serachDto.setOofId(oofId);
		serachDto.setPeriodeId(periodeId);
		serachDto.setComptablise(true);
		serachDto.setGroupePedagogiqueId(gpId);
		serachDto.setRattachementMcId(rattachementMcId);
		apList = noteEvaluationControleContinuService.findApOfCC(serachDto);
	}

	/**
	 * [ValidateControleContinuBean.calculNote] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 14:20:22
	 */
	public void calculNote() {
		NoteEvaluationControleContinuDto serachDto = new NoteEvaluationControleContinuDto();
		serachDto.setAnneeAcademiqueId(anneeAcademiqueId);
		serachDto.setOofId(oofId);
		serachDto.setPeriodeId(periodeId);
		// serachDto.setComptablise(false);
		serachDto.setGroupePedagogiqueId(gpId);
		serachDto.setRattachementMcId(rattachementMcId);
		noteApList = noteEvaluationControleContinuService.findAdvanced(serachDto);

	}

	/**
	 * [ValidateControleContinuBean.rattachementMcService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 17:43:59
	 * @return the rattachementMcService
	 */
	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	/**
	 * [ValidateControleContinuBean.rattachementMcService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 17:43:59
	 * @param rattachementMcService
	 *            the rattachementMcService to set
	 */
	public void setRattachementMcService(RattachementMcService rattachementMcService) {
		this.rattachementMcService = rattachementMcService;
	}

	/**
	 * [ValidateControleContinuBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 17:49:02
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [ValidateControleContinuBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 17:49:02
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [ValidateControleContinuBean.ueMcModelList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 18:08:26
	 * @return the ueMcModelList
	 */
	public List<UeMcModel> getUeMcModelList() {
		return ueMcModelList;
	}

	/**
	 * [ValidateControleContinuBean.ueMcModelList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 18:08:26
	 * @param ueMcModelList
	 *            the ueMcModelList to set
	 */
	public void setUeMcModelList(List<UeMcModel> ueMcModelList) {
		this.ueMcModelList = ueMcModelList;
	}

	/**
	 * [ValidateControleContinuBean.repartitionUeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 18:10:26
	 * @return the repartitionUeService
	 */
	public RepartitionUeService getRepartitionUeService() {
		return repartitionUeService;
	}

	/**
	 * [ValidateControleContinuBean.repartitionUeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 oct. 2014 18:10:26
	 * @param repartitionUeService
	 *            the repartitionUeService to set
	 */
	public void setRepartitionUeService(RepartitionUeService repartitionUeService) {
		this.repartitionUeService = repartitionUeService;
	}

	/**
	 * [ValidateControleContinuBean.rattachementMcDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 09:09:18
	 * @return the rattachementMcDto
	 */
	public RattachementMcDto getRattachementMcDto() {
		return rattachementMcDto;
	}

	/**
	 * [ValidateControleContinuBean.rattachementMcDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 09:09:18
	 * @param rattachementMcDto
	 *            the rattachementMcDto to set
	 */
	public void setRattachementMcDto(RattachementMcDto rattachementMcDto) {
		this.rattachementMcDto = rattachementMcDto;
	}

	/**
	 * [ValidateControleContinuBean.noteEvaluationControleContinuService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 09:12:45
	 * @return the noteEvaluationControleContinuService
	 */
	public NoteEvaluationControleContinuService getNoteEvaluationControleContinuService() {
		return noteEvaluationControleContinuService;
	}

	/**
	 * [ValidateControleContinuBean.noteEvaluationControleContinuService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 09:12:45
	 * @param noteEvaluationControleContinuService
	 *            the noteEvaluationControleContinuService to set
	 */
	public void setNoteEvaluationControleContinuService(
	        NoteEvaluationControleContinuService noteEvaluationControleContinuService) {
		this.noteEvaluationControleContinuService = noteEvaluationControleContinuService;
	}

	/**
	 * [ValidateControleContinuBean.noteMoyenneApList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 12:34:57
	 * @return the noteMoyenneApList
	 */
	public List<NoteMoyenneAp> getNoteMoyenneApList() {
		return noteMoyenneApList;
	}

	/**
	 * [ValidateControleContinuBean.noteMoyenneApList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 12:34:57
	 * @param noteMoyenneApList
	 *            the noteMoyenneApList to set
	 */
	public void setNoteMoyenneApList(List<NoteMoyenneAp> noteMoyenneApList) {
		this.noteMoyenneApList = noteMoyenneApList;
	}

	/**
	 * [ValidateControleContinuBean.noteApList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 13:20:11
	 * @return the noteApList
	 */
	public List<NoteEvaluationControleContinuDto> getNoteApList() {
		return noteApList;
	}

	/**
	 * [ValidateControleContinuBean.noteApList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 13:20:11
	 * @param noteApList
	 *            the noteApList to set
	 */
	public void setNoteApList(List<NoteEvaluationControleContinuDto> noteApList) {
		this.noteApList = noteApList;
	}

	/**
	 * [ValidateControleContinuBean.affectationGroupePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 13:53:32
	 * @return the affectationGroupePedagogiqueService
	 */
	public AffectationGroupePedagogiqueService getAffectationGroupePedagogiqueService() {
		return affectationGroupePedagogiqueService;
	}

	/**
	 * [ValidateControleContinuBean.affectationGroupePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 13:53:32
	 * @param affectationGroupePedagogiqueService
	 *            the affectationGroupePedagogiqueService to set
	 */
	public void setAffectationGroupePedagogiqueService(
	        AffectationGroupePedagogiqueService affectationGroupePedagogiqueService) {
		this.affectationGroupePedagogiqueService = affectationGroupePedagogiqueService;
	}

	/**
	 * [ValidateControleContinuBean.matieres] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 14:13:06
	 * @return the matieres
	 */
	public List<SelectItem> getMatieres() {
		return matieres;
	}

	/**
	 * [ValidateControleContinuBean.matieres] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 14:13:06
	 * @param matieres
	 *            the matieres to set
	 */
	public void setMatieres(List<SelectItem> matieres) {
		this.matieres = matieres;
	}

	/**
	 * [ValidateControleContinuBean.moyenneCCList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 16:11:07
	 * @return the moyenneCCList
	 */
	public List<MoyenneControleContinuModel> getMoyenneCCList() {
		return moyenneCCList;
	}

	/**
	 * [ValidateControleContinuBean.moyenneCCList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 16:11:07
	 * @param moyenneCCList
	 *            the moyenneCCList to set
	 */
	public void setMoyenneCCList(List<MoyenneControleContinuModel> moyenneCCList) {
		this.moyenneCCList = moyenneCCList;
	}

	/**
	 * [ValidateControleContinuBean.showTable] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 16:51:43
	 * @return the showTable
	 */
	public boolean isShowTable() {
		return showTable;
	}

	/**
	 * [ValidateControleContinuBean.showTable] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 16:51:43
	 * @param showTable
	 *            the showTable to set
	 */
	public void setShowTable(boolean showTable) {
		this.showTable = showTable;
	}

	/**
	 * [ValidateControleContinuBean.atomePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 09:58:38
	 * @return the atomePedagogiqueService
	 */
	public AtomePedagogiqueService getAtomePedagogiqueService() {
		return atomePedagogiqueService;
	}

	/**
	 * [ValidateControleContinuBean.atomePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 09:58:38
	 * @param atomePedagogiqueService
	 *            the atomePedagogiqueService to set
	 */
	public void setAtomePedagogiqueService(AtomePedagogiqueService atomePedagogiqueService) {
		this.atomePedagogiqueService = atomePedagogiqueService;
	}

	/**
	 * [ValidateControleContinuBean.rattachementMcId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:21:01
	 * @return the rattachementMcId
	 */
	public Integer getRattachementMcId() {
		return rattachementMcId;
	}

	/**
	 * [ValidateControleContinuBean.rattachementMcId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:21:01
	 * @param rattachementMcId
	 *            the rattachementMcId to set
	 */
	public void setRattachementMcId(Integer rattachementMcId) {
		this.rattachementMcId = rattachementMcId;
	}

	/**
	 * [ValidateControleContinuBean.gpId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:21:01
	 * @return the gpId
	 */
	public Integer getGpId() {
		return gpId;
	}

	/**
	 * [ValidateControleContinuBean.gpId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:21:01
	 * @param gpId
	 *            the gpId to set
	 */
	public void setGpId(Integer gpId) {
		this.gpId = gpId;
	}

	/**
	 * [ValidateControleContinuBean.editMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:47:27
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [ValidateControleContinuBean.editMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:47:27
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * [ValidateControleContinuBean.ofList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:49:27
	 * @return the ofList
	 */
	public List<SelectItem> getOfList() {
		return ofList;
	}

	/**
	 * [ValidateControleContinuBean.ofList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:49:27
	 * @param ofList
	 *            the ofList to set
	 */
	public void setOfList(List<SelectItem> ofList) {
		this.ofList = ofList;
	}

	/**
	 * [ValidateControleContinuBean.anneeAcademiqueId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:49:53
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [ValidateControleContinuBean.anneeAcademiqueId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:49:53
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [ValidateControleContinuBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:49:53
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [ValidateControleContinuBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:49:53
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [ValidateControleContinuBean.anneeAcademiqueList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:50:31
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [ValidateControleContinuBean.anneeAcademiqueList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:50:31
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [ValidateControleContinuBean.oofId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:51:39
	 * @return the oofId
	 */
	public Integer getOofId() {
		return oofId;
	}

	/**
	 * [ValidateControleContinuBean.oofId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:51:39
	 * @param oofId
	 *            the oofId to set
	 */
	public void setOofId(Integer oofId) {
		this.oofId = oofId;
	}

	/**
	 * [ValidateControleContinuBean.periodes] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:52:55
	 * @return the periodes
	 */
	public List<SelectItem> getPeriodes() {
		return periodes;
	}

	/**
	 * [ValidateControleContinuBean.periodes] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:52:55
	 * @param periodes
	 *            the periodes to set
	 */
	public void setPeriodes(List<SelectItem> periodes) {
		this.periodes = periodes;
	}

	/**
	 * [ValidateControleContinuBean.periodeId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:53:26
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [ValidateControleContinuBean.periodeId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:53:26
	 * @param periodeId
	 *            the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [ValidateControleContinuBean.groupeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:56:07
	 * @return the groupeList
	 */
	public List<SelectItem> getGroupeList() {
		return groupeList;
	}

	/**
	 * [ValidateControleContinuBean.groupeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:56:07
	 * @param groupeList
	 *            the groupeList to set
	 */
	public void setGroupeList(List<SelectItem> groupeList) {
		this.groupeList = groupeList;
	}

	/**
	 * [ValidateControleContinuBean.associationGroupePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:56:32
	 * @return the associationGroupePedagogiqueService
	 */
	public AssociationGroupePedagogiqueService getAssociationGroupePedagogiqueService() {
		return associationGroupePedagogiqueService;
	}

	/**
	 * [ValidateControleContinuBean.associationGroupePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:56:32
	 * @param associationGroupePedagogiqueService
	 *            the associationGroupePedagogiqueService to set
	 */
	public void setAssociationGroupePedagogiqueService(
	        AssociationGroupePedagogiqueService associationGroupePedagogiqueService) {
		this.associationGroupePedagogiqueService = associationGroupePedagogiqueService;
	}

	/**
	 * [ValidateControleContinuBean.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:59:39
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [ValidateControleContinuBean.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 11:59:39
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [ValidateControleContinuBean.selectedNotes] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 14:50:54
	 * @return the selectedNotes
	 */
	public List<NoteApGroupModel> getSelectedNotes() {
		return selectedNotes;
	}

	/**
	 * [ValidateControleContinuBean.selectedNotes] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 14:50:54
	 * @param selectedNotes
	 *            the selectedNotes to set
	 */
	public void setSelectedNotes(List<NoteApGroupModel> selectedNotes) {
		this.selectedNotes = selectedNotes;
	}

	/**
	 * [ValidateControleContinuBean.nomPrenomEtudiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 16:10:40
	 * @return the nomPrenomEtudiant
	 */
	public String getNomPrenomEtudiant() {
		return nomPrenomEtudiant;
	}

	/**
	 * [ValidateControleContinuBean.nomPrenomEtudiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 16:10:40
	 * @param nomPrenomEtudiant
	 *            the nomPrenomEtudiant to set
	 */
	public void setNomPrenomEtudiant(String nomPrenomEtudiant) {
		this.nomPrenomEtudiant = nomPrenomEtudiant;
	}

	/**
	 * [ValidateControleContinuBean.apList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 18:14:30
	 * @return the apList
	 */
	public List<AtomePedagogiqueDto> getApList() {
		return apList;
	}

	/**
	 * [ValidateControleContinuBean.apList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 18:14:30
	 * @param apList
	 *            the apList to set
	 */
	public void setApList(List<AtomePedagogiqueDto> apList) {
		this.apList = apList;
	}

	/**
	 * [ValidateControleContinuBean.evaluationControleContinuService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 12:50:34
	 * @return the evaluationControleContinuService
	 */
	public EvaluationControleContinuService getEvaluationControleContinuService() {
		return evaluationControleContinuService;
	}

	/**
	 * [ValidateControleContinuBean.evaluationControleContinuService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 12:50:34
	 * @param evaluationControleContinuService
	 *            the evaluationControleContinuService to set
	 */
	public void setEvaluationControleContinuService(EvaluationControleContinuService evaluationControleContinuService) {
		this.evaluationControleContinuService = evaluationControleContinuService;
	}

	/**
	 * [ValidateControleContinuBean.situationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 13:59:44
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [ValidateControleContinuBean.situationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 13:59:44
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [ValidateControleContinuBean.bilanControleContinuService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:40:33
	 * @return the bilanControleContinuService
	 */
	public BilanControleContinuService getBilanControleContinuService() {
		return bilanControleContinuService;
	}

	/**
	 * [ValidateControleContinuBean.bilanControleContinuService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:40:33
	 * @param bilanControleContinuService
	 *            the bilanControleContinuService to set
	 */
	public void setBilanControleContinuService(BilanControleContinuService bilanControleContinuService) {
		this.bilanControleContinuService = bilanControleContinuService;
	}

	/**
	 * [ValidateControleContinuBean.validated] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:54:41
	 * @return the validated
	 */
	public boolean isValidated() {
		return validated;
	}

	/**
	 * [ValidateControleContinuBean.validated] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:54:41
	 * @param validated
	 *            the validated to set
	 */
	public void setValidated(boolean validated) {
		this.validated = validated;
	}

}
