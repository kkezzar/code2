/**
 * [dz.gov.mesrs.sii.recherche.web.jsf.bean.structure.RechercheStructureBB.java] 
 * @author rlaib on : 16 d�c. 2014  16:06:14
 */
package dz.gov.mesrs.sii.recherche.web.jsf.bean.projet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.SerializationUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.ProcessusDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.recherche.business.model.dto.ActiviteProjetDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.EtapeValidationDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.EvaluationIndicateurDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.EvaluationProjetDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.GroupeDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.IndEvaluationDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.MotcleDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.PartenaireDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ProgrammeDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ProjetDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ProjetPartenaireDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ThemeDto;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.RechercheConstantBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

/**
 * @author rlaib  on : 16 d�c. 2014  16:06:14
 */
@ManagedBean(name = "evaluerProjetMB")
@ViewScoped
public class EvaluerProjetMB extends BaseBean {
	
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 16 dec. 2014  16:08:20
	 */
	private static final long serialVersionUID = 1L;

	// ===================================================================  
	// Constructors
	// ===================================================================

	public EvaluerProjetMB() {
	}
	
	@PostConstruct
	public void init() {
		
		situationEntiteDto = this.serviceLocator.getSituationService().findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_PROJET_CODE,UtilConstant.SITUATION_CREEE_PROJET);
		refEtablissementDto=sessionBean.getRefEtablissementDto();
		List<EntiteDto >_entities = serviceLocator.getProcessusService().findEntityByCodeDomaine(RechercheConstantBean.RCH_RECHERCHE_DOMAINE_CODE);
		if(_entities != null && !_entities.isEmpty()) {
			currentEntities =  new ArrayList<SelectItem>();
			for (EntiteDto _entite : _entities) {
				currentEntities.add(new SelectItem(_entite.getId(), _entite.getLibelle()));
			}
		}
		currentTypeGroupe = serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_TYPE_GROUPE_NAME, 
				UtilConstant.NC_TYPE_GROUPE_RECHERCHE_CODE); 
		//initProcess();
		initProjet();
		initListTypePrograms();
		initListStructure();
		initListGroupes();
		initListTypeActivite();
		initListPartenaireProjet();
		initListMotcle();
		initListMotsCles();		
		initListindevaluation();
		initListTypeAppreciation();
		initListIndicateur();
		initListProjetPartenaire();
		initListCloture(); 
	}
	
	
	public void initListes() {
		initListGroupes();
		initListPartenaireProjet();
		
	}

	private void initProjet() {
		listprojet = serviceLocator.getRechercheProjetService().findByCodeProjet(RechercheConstantBean.RCH_RECHERCHE_DOMAINE_CODE,situationEntiteDto.getId());
			
		
	}
	
private void initListTypePrograms() {
	List<ProgrammeDto> listTypes = serviceLocator.getRechercheProgrammeService().findActifProgramme();
		if(listTypes  != null) {
		listProgramsTypes =  new ArrayList<SelectItem>();
			for (ProgrammeDto programmeDto : listTypes) {
				listProgramsTypes.add(new SelectItem(programmeDto.getId(),programmeDto.getIntituleFr()));
			}
		}
		
	}
	
private void initListStructure() {
	List<StructureDto> listTypes = serviceLocator.getRechercheStructureService().findByEtablicement(refEtablissementDto.getId());
	if(listTypes  != null) {
	listStructureTypes =  new ArrayList<SelectItem>();
		for (StructureDto structureDto : listTypes) {
			listStructureTypes.add(new SelectItem(structureDto.getId(),structureDto.getRefStructureLibelleLatin()));
		}
	}
	
}

	// ===================================================================  
	// Properties Model
	// ===================================================================
    private List<EvaluationIndicateurDto> listEvaluationIndicateur;
	private List<SelectItem> currentEntities;     
	private NomenclatureDto currentTypeGroupe;//supp
	private List<ProcessusDto> listProcessus;     //supp	
	private List<ProjetDto> listprojet;
	private List<ActiviteProjetDto> listActiviteProjet;	
	private List<EtapeValidationDto> listEtapeValidation;	
	private List<PartenaireDto> listPartenaireDto;	
	private List<MotcleDto> listMotcleDto;
	private List<IndEvaluationDto> listIndEvaluation;
	private List<ProcessusDto> listProcessusClone;//supp
	private List<ProjetPartenaireDto> listPartenaireProjet;
	private List<EtapeDto> listSteps;//supp	
	private ProjetDto selectedProjet;
	private EvaluationProjetDto evaluationProjetDto;
	private Integer selectedEntiteId, selectedStepId,selectedMotcleId,selectedIndicateurId,
	          selectedTypeAppreciationId,selectedProcessusId,selectedActiviteNcId,selectedClotureId;
	private Long selectedProjetId,evaluationIndicateurDtoId;//supp
	private EtapeDto selectedStep;//supp
	private ProcessusDto selectedProcessus;//supp
	private String searchComponentKeyWords;//supp
	private boolean canShowPjetDetails = false,toEditProjetDialog= false,toEditStepDialog= false, canDeleteProjet = false,canShowStepsDetails= false;//supp
	private List<SelectItem>  listThemes,listGroupes,listRoles,listMotcle,listindevaluation,listTypeAppreciation,
	                           listProgramsTypes, listNextSteps,listPreviousSteps, listTypeActivite, listTypePartenaireProjet,
	                           listStructureTypes,listCloture;
	private String titlePanelDetails;	
	private Long groupeId,structureId,themeId,typeProgrammeId,activiteId,selectedPartenaireProjetId,
	validationId,motcleId,indevaluationId;
	private GroupeDto groupe;
	private StructureDto structure;
	private ThemeDto theme;
	private ProgrammeDto programme;
	private ActiviteProjetDto activiteProjet;
	private ProjetPartenaireDto partenaireProjet;
	private EtapeValidationDto etapeValidationDto;
	private RefEtablissementDto refEtablissementDto;
	private MotcleDto motcleDto;
	private IndEvaluationDto indEvaluationDto;
	private EvaluationIndicateurDto evaluationIndicateurDto;
	private SituationEntiteDto situationEntiteDto;
	/**
	 * [CircuitValidationBB.titlePanelDetails] Getter 
	 * @author rlaib on : 26 janv. 2015  12:25:58
	 * @return the titlePanelDetails
	 */
	public String getTitlePanelDetails() {
		return titlePanelDetails;
	}

	/**
	 * [CircuitValidationBB.titlePanelDetails] Setter 
	 * @author rlaib on : 26 janv. 2015  12:25:58
	 * @param titlePanelDetails the titlePanelDetails to set
	 */
	public void setTitlePanelDetails(String titlePanelDetails) {
		this.titlePanelDetails = titlePanelDetails;
	}

	/**
	 * [CircuitValidationBB.canShowStepsDetails] Getter 
	 * @author rlaib on : 25 janv. 2015  15:03:13
	 * @return the canShowStepsDetails
	 */
	public boolean isCanShowStepsDetails() {
		return canShowStepsDetails;
	}

	/**
	 * [CircuitValidationBB.canShowStepsDetails] Setter 
	 * @author rlaib on : 25 janv. 2015  15:03:13
	 * @param canShowStepsDetails the canShowStepsDetails to set
	 */
	public void setCanShowStepsDetails(boolean canShowStepsDetails) {
		this.canShowStepsDetails = canShowStepsDetails;
	}

	/**
	 * [CircuitValidationBB.selectedProjetId] Getter 
	 * @author rlaib on : 21 janv. 2015  16:58:30
	 * @return the selectedProjetId
	 */
	public Long getselectedProjetId() {
		return selectedProjetId;
	}

	/**
	 * [CircuitValidationBB.selectedProjetId] Setter 
	 * @author rlaib on : 21 janv. 2015  16:58:30
	 * @param selectedProjetId the selectedProjetId to set
	 */
	public void setselectedProjetId(Long selectedProjetId) {
		this.selectedProjetId = selectedProjetId;
	}

	/**
	 * [CircuitValidationBB.canDeleteProjet] Getter 
	 * @author rlaib on : 21 janv. 2015  15:05:56
	 * @return the canDeleteProjet
	 */
	public boolean iscanDeleteProjet() {
		return canDeleteProjet;
	}

	/**
	 * [CircuitValidationBB.canDeleteProjet] Setter 
	 * @author rlaib on : 21 janv. 2015  15:05:56
	 * @param canDeleteProjet the canDeleteProjet to set
	 */
	public void setcanDeleteProjet(boolean canDeleteProjet) {
		this.canDeleteProjet = canDeleteProjet;
	}

	/**
	 * [CircuitValidationBB.listNextSteps] Getter 
	 * @author rlaib on : 20 janv. 2015  15:59:45
	 * @return the listNextSteps
	 */
	public List<SelectItem> getListNextSteps() {
		return listNextSteps;
	}

	/**
	 * [CircuitValidationBB.listNextSteps] Setter 
	 * @author rlaib on : 20 janv. 2015  15:59:45
	 * @param listNextSteps the listNextSteps to set
	 */
	public void setListNextSteps(List<SelectItem> listNextSteps) {
		this.listNextSteps = listNextSteps;
	}

	/**
	 * [CircuitValidationBB.listPreviousSteps] Getter 
	 * @author rlaib on : 20 janv. 2015  15:59:45
	 * @return the listPreviousSteps
	 */
	public List<SelectItem> getListPreviousSteps() {
		return listPreviousSteps;
	}

	/**
	 * [CircuitValidationBB.listPreviousSteps] Setter 
	 * @author rlaib on : 20 janv. 2015  15:59:45
	 * @param listPreviousSteps the listPreviousSteps to set
	 */
	public void setListPreviousSteps(List<SelectItem> listPreviousSteps) {
		this.listPreviousSteps = listPreviousSteps;
	}

	/**
	 * [CircuitValidationBB.currentTypeGroupe] Getter 
	 * @author rlaib on : 20 janv. 2015  15:31:17
	 * @return the currentTypeGroupe
	 */
	public NomenclatureDto getCurrentTypeGroupe() {
		return currentTypeGroupe;
	}

	/**
	 * [CircuitValidationBB.currentTypeGroupe] Setter 
	 * @author rlaib on : 20 janv. 2015  15:31:17
	 * @param currentTypeGroupe the currentTypeGroupe to set
	 */
	public void setCurrentTypeGroupe(NomenclatureDto currentTypeGroupe) {
		this.currentTypeGroupe = currentTypeGroupe;
	}

	/**
	 * [CircuitValidationBB.listGroupes] Getter 
	 * @author rlaib on : 20 janv. 2015  15:15:39
	 * @return the listGroupes
	 */
	public List<SelectItem> getListGroupes() {
		return listGroupes;
	}

	/**
	 * [CircuitValidationBB.listGroupes] Setter 
	 * @author rlaib on : 20 janv. 2015  15:15:39
	 * @param listGroupes the listGroupes to set
	 */
	public void setListGroupes(List<SelectItem> listGroupes) {
		this.listGroupes = listGroupes;
	}

	/**
	 * [CircuitValidationBB.listRoles] Getter 
	 * @author rlaib on : 20 janv. 2015  15:15:39
	 * @return the listRoles
	 */
	public List<SelectItem> getListRoles() {
		return listRoles;
	}

	/**
	 * [CircuitValidationBB.listRoles] Setter 
	 * @author rlaib on : 20 janv. 2015  15:15:39
	 * @param listRoles the listRoles to set
	 */
	public void setListRoles(List<SelectItem> listRoles) {
		this.listRoles = listRoles;
	}

	/**
	 * [CircuitValidationBB.toEditStepDialog] Getter 
	 * @author rlaib on : 20 janv. 2015  12:45:28
	 * @return the toEditStepDialog
	 */
	public boolean isToEditStepDialog() {
		return toEditStepDialog;
	}

	/**
	 * [CircuitValidationBB.toEditStepDialog] Setter 
	 * @author rlaib on : 20 janv. 2015  12:45:28
	 * @param toEditStepDialog the toEditStepDialog to set
	 */
	public void setToEditStepDialog(boolean toEditStepDialog) {
		this.toEditStepDialog = toEditStepDialog;
	}

	/**
	 * [CircuitValidationBB.listProcessusClone] Getter 
	 * @author rlaib on : 20 janv. 2015  11:59:58
	 * @return the listProcessusClone
	 */
	public List<ProcessusDto> getListProcessusClone() {
		return listProcessusClone;
	}

	/**
	 * [CircuitValidationBB.listProcessusClone] Setter 
	 * @author rlaib on : 20 janv. 2015  11:59:58
	 * @param listProcessusClone the listProcessusClone to set
	 */
	public void setListProcessusClone(List<ProcessusDto> listProcessusClone) {
		this.listProcessusClone = listProcessusClone;
	}

	/**
	 * [CircuitValidationBB.listSteps] Getter 
	 * @author rlaib on : 19 janv. 2015  16:53:26
	 * @return the listSteps
	 */
	public List<EtapeDto> getListSteps() {
		return listSteps;
	}

	/**
	 * [CircuitValidationBB.listSteps] Setter 
	 * @author rlaib on : 19 janv. 2015  16:53:26
	 * @param listSteps the listSteps to set
	 */
	public void setListSteps(List<EtapeDto> listSteps) {
		this.listSteps = listSteps;
	}

	/**
	 * [CircuitValidationBB.selectedStepId] Getter 
	 * @author rlaib on : 19 janv. 2015  16:53:26
	 * @return the selectedStepId
	 */
	public Integer getSelectedStepId() {
		return selectedStepId;
	}

	/**
	 * [CircuitValidationBB.selectedStepId] Setter 
	 * @author rlaib on : 19 janv. 2015  16:53:26
	 * @param selectedStepId the selectedStepId to set
	 */
	public void setSelectedStepId(Integer selectedStepId) {
		this.selectedStepId = selectedStepId;
	}

	/**
	 * [CircuitValidationBB.selectedStep] Getter 
	 * @author rlaib on : 19 janv. 2015  16:53:26
	 * @return the selectedStep
	 */
	public EtapeDto getSelectedStep() {
		return selectedStep;
	}

	/**
	 * [CircuitValidationBB.selectedStep] Setter 
	 * @author rlaib on : 19 janv. 2015  16:53:26
	 * @param selectedStep the selectedStep to set
	 */
	public void setSelectedStep(EtapeDto selectedStep) {
		this.selectedStep = selectedStep;
	}


	/**
	 * [CircuitValidationBB.currentEntities] Getter 
	 * @author rlaib on : 21 janv. 2015  16:27:04
	 * @return the currentEntities
	 */
	public List<SelectItem> getCurrentEntities() {
		return currentEntities;
	}

	/**
	 * [CircuitValidationBB.currentEntities] Setter 
	 * @author rlaib on : 21 janv. 2015  16:27:04
	 * @param currentEntities the currentEntities to set
	 */
	public void setCurrentEntities(List<SelectItem> currentEntities) {
		this.currentEntities = currentEntities;
	}

	/**
	 * [CircuitValidationBB.toEditProjetDialog] Getter 
	 * @author rlaib on : 19 janv. 2015  09:56:29
	 * @return the toEditProjetDialog
	 */
	public boolean istoEditProjetDialog() {
		return toEditProjetDialog;
	}

	/**
	 * [CircuitValidationBB.toEditProjetDialog] Setter 
	 * @author rlaib on : 19 janv. 2015  09:56:29
	 * @param toEditProjetDialog the toEditProjetDialog to set
	 */
	public void settoEditProjetDialog(boolean toEditProjetDialog) {
		this.toEditProjetDialog = toEditProjetDialog;
	}

	/**
	 * [CircuitValidationBB.canShowPjetDetails] Getter 
	 * @author rlaib on : 19 janv. 2015  09:26:04
	 * @return the canShowPjetDetails
	 */
	public boolean iscanShowPjetDetails() {
		return canShowPjetDetails;
	}

	/**
	 * [CircuitValidationBB.canShowPjetDetails] Setter 
	 * @author rlaib on : 19 janv. 2015  09:26:04
	 * @param canShowPjetDetails the canShowPjetDetails to set
	 */
	public void setcanShowPjetDetails(boolean canShowPjetDetails) {
		this.canShowPjetDetails = canShowPjetDetails;
	}

	/**
	 * [CircuitValidationBB.listProcessus] Getter 
	 * @author rlaib on : 19 janv. 2015  09:17:23
	 * @return the listProcessus
	 */
	public List<ProcessusDto> getListProcessus() {
		return listProcessus;
	}

	/**
	 * [CircuitValidationBB.listProcessus] Setter 
	 * @author rlaib on : 19 janv. 2015  09:17:23
	 * @param listProcessus the listProcessus to set
	 */
	public void setListProcessus(List<ProcessusDto> listProcessus) {
		this.listProcessus = listProcessus;
	}

	/**
	 * [CircuitValidationBB.searchComponentKeyWords] Getter 
	 * @author rlaib on : 19 janv. 2015  09:16:06
	 * @return the searchComponentKeyWords
	 */
	public String getSearchComponentKeyWords() {
		return searchComponentKeyWords;
	}

	/**
	 * [CircuitValidationBB.searchComponentKeyWords] Setter 
	 * @author rlaib on : 19 janv. 2015  09:16:06
	 * @param searchComponentKeyWords the searchComponentKeyWords to set
	 */
	public void setSearchComponentKeyWords(String searchComponentKeyWords) {
		this.searchComponentKeyWords = searchComponentKeyWords;
	}

	/**
	 * [CircuitValidationBB.selectedProcessusId] Getter 
	 * @author rlaib on : 19 janv. 2015  08:58:51
	 * @return the selectedProcessusId
	 */
	public Integer getSelectedProcessusId() {
		return selectedProcessusId;
	}

	/**
	 * [CircuitValidationBB.selectedProcessusId] Setter 
	 * @author rlaib on : 19 janv. 2015  08:58:51
	 * @param selectedProcessusId the selectedProcessusId to set
	 */
	public void setSelectedProcessusId(Integer selectedProcessusId) {
		this.selectedProcessusId = selectedProcessusId;
	}

	/**
	 * [CircuitValidationBB.selectedProcessus] Getter 
	 * @author rlaib on : 19 janv. 2015  08:58:51
	 * @return the selectedProcessus
	 */
	public ProcessusDto getSelectedProcessus() {
		return selectedProcessus;
	}

	/**
	 * [CircuitValidationBB.selectedProcessus] Setter 
	 * @author rlaib on : 19 janv. 2015  08:58:51
	 * @param selectedProcessus the selectedProcessus to set
	 */
	public void setSelectedProcessus(ProcessusDto selectedProcessus) {
		this.selectedProcessus = selectedProcessus;
	}
	
	// ===================================================================  
	// Actions and Methods
	// ===================================================================

	/**
	 * [CircuitValidationBB.searchProcess] Method 
	 * @author rlaib  on : 20 janv. 2015  12:48:15
	 */
public void searchProjets(){		
	listprojet = serviceLocator.getRechercheProjetService().findByKeyWords(searchComponentKeyWords);		
	listProjetBySituation();
	canShowPjetDetails = false;
	selectedProcessus = null;
}

public void listProjetBySituation(){
 List<ProjetDto> listprojetint = new ArrayList<ProjetDto>();
if(listprojet!=null)if(!listprojet.isEmpty()){
for(ProjetDto dto:listprojet){
	if(dto.getSituationDto()!=null)if(dto.getSituationDto().getId()==situationEntiteDto.getId())listprojetint.add(dto);
}
}
listprojet=listprojetint;
}
	
	/**
	 * [CircuitValidationBB.loadProcessusDetail] Method 
	 * @author rlaib  on : 19 janv. 2015  09:26:21
	 */
	private void loadProjetDetail() {	
		selectedProjetId = selectedProjet.getId();		
			canDeleteProjet = true;		
	}
	
	/**
	 * [CircuitValidationBB.prepareAddProcess] Method 
	 * @author rlaib  on : 20 janv. 2015  12:48:10
	 */
	public void prepareAddProjet() {
		selectedProjet = new ProjetDto();
		toEditProjetDialog= false;
		canShowStepsDetails = false;
		canShowPjetDetails = true;
		canDeleteProjet = false;
		titlePanelDetails = CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME, 
				RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_PANEL_DETAILS_TITLE_ADD);
	}
	
	/**
	 * [CircuitValidationBB.prepareAddOneStep] Method 
	 * @author rlaib  on : 20 janv. 2015  12:48:05
	 */
	public void prepareAddOneStep() {
		selectedStep = new EtapeDto();
		toEditStepDialog= false;
		if(listGroupes == null) {
			initListGroupes();
			initListRoles();
		}
		initListPreviousSteps();
	}
	
	/**
	 * [CircuitValidationBB.initListPreviousSteps] Method 
	 * @author rlaib  on : 20 janv. 2015  17:55:20
	 */
	private void initListPreviousSteps() {
		
		if(listSteps != null && !listSteps.isEmpty()) {
			
			listPreviousSteps = new ArrayList<SelectItem>();
			if (!toEditStepDialog) {
					// Mode ajout --> la liste des etape precedentes est simplement la dernire etape ajoutee.
					listPreviousSteps.add(new SelectItem(listSteps.get(listSteps.size()-1).getId(),listSteps.get(listSteps.size()-1).getLibelleFr()));
			}
			else {
				// Mode edition --> On recupere simplement l etape precedente de l etape a editer.
				for (EtapeDto _step : listSteps) {
					if(selectedStep.getEtapePrecedenteId() != null) {
						if(_step.getId() == selectedStep.getEtapePrecedenteId()) {
							listPreviousSteps.add(new SelectItem(_step.getId(),_step.getLibelleFr()));
						}
					}
				}
				
			}
		}
		else
			listPreviousSteps = null;
			
		
	}

	/**
	 * [CircuitValidationBB.initListRoles] Method 
	 * @author rlaib  on : 20 janv. 2015  15:32:52
	 */
	private void initListRoles() {
		
		if(currentTypeGroupe !=null) {
			Integer idTypeGroupeRecherche = currentTypeGroupe.getId();
			if(idTypeGroupeRecherche != null) {
					List<NomenclatureDto> listRolesReferentiel = serviceLocator.getNomenclatureService().findNcCompositeList(idTypeGroupeRecherche, UtilConstant.NC_ROLE_NAME);
					listRoles =  new ArrayList<SelectItem>();
					for (NomenclatureDto _nomenclatureDto : listRolesReferentiel) {
						listRoles.add(new SelectItem(_nomenclatureDto.getId(),_nomenclatureDto.getLibelleLongFr()));
					}
					
			}
		}
		
	}
	
	
	private void initListCloture() {
		List<NomenclatureDto> listMotcleReferentiel = serviceLocator.getNomenclatureService().findByName(UtilConstant.NC_RECHERCHE_CLOTURE);
		listCloture =  new ArrayList<SelectItem>();
		for (NomenclatureDto _nomenclatureDto : listMotcleReferentiel) {
			listCloture.add(new SelectItem(_nomenclatureDto.getId(),_nomenclatureDto.getLibelleLongFr()));
}

}
	
	
private void initListMotcle() {
					List<NomenclatureDto> listMotcleReferentiel = serviceLocator.getNomenclatureService().findByName(UtilConstant.NC_RECHERCHE_MOTS_CLES);
					listMotcle =  new ArrayList<SelectItem>();
					for (NomenclatureDto _nomenclatureDto : listMotcleReferentiel) {
						listMotcle.add(new SelectItem(_nomenclatureDto.getId(),_nomenclatureDto.getLibelleLongFr()));
		}
		
	}
 
     
private void initListindevaluation() {
			List<NomenclatureDto> listMotcleReferentiel = serviceLocator.getNomenclatureService().findByName(UtilConstant.NC_RECHERCHE_IND_EVALUATION);
			listindevaluation =  new ArrayList<SelectItem>();
			for (NomenclatureDto _nomenclatureDto : listMotcleReferentiel) {
				listindevaluation.add(new SelectItem(_nomenclatureDto.getId(),_nomenclatureDto.getLibelleLongFr()));
}

}

private void initListTypeAppreciation() {
	List<NomenclatureDto> listMotcleReferentiel = serviceLocator.getNomenclatureService().findByName(UtilConstant.NC_RECHERCHE_TYPE_APPRECIATION_EVALUATION);
	listTypeAppreciation =  new ArrayList<SelectItem>();
	for (NomenclatureDto _nomenclatureDto : listMotcleReferentiel) {
		listTypeAppreciation.add(new SelectItem(_nomenclatureDto.getId(),_nomenclatureDto.getLibelleLongFr()));
}

}


	/**
	 * [CircuitValidationBB.initListGroupes] Method 
	 * @author rlaib  on : 20 janv. 2015  15:16:10
	 */
	public void initListGroupes() {
		if(structureId != null){
			List<GroupeDto> listGroupesdto = serviceLocator.getRechercheGroupeService().findStructureRechercheGroupes(structureId);	
			listGroupes = new ArrayList<SelectItem>();
			for (GroupeDto groupeDto : listGroupesdto) {
				listGroupes.add(new SelectItem(groupeDto.getId(),groupeDto.getRefGroupeLibelleFr()));
			}			
		}	
	}
	
	
	public void initListMotsCles() {
		if(selectedProjet != null){
			listMotcleDto = serviceLocator.getRechercheMotcleService().findListMotsClesByIdProjet(selectedProjet.getId());	
					
		}	
	}
	
	public void initListIndicateur() {
		if(selectedProjet != null){
			listIndEvaluation = serviceLocator.getRechercheIndEvaluationService().findListIndicateurByIdProjet(selectedProjet.getId());	
					
		}	
	}
	public void initListProjetPartenaire() {
		if(selectedProjet != null){
			listPartenaireProjet= serviceLocator.getRechercheProjetPartenaireService().findListPartenaireByIdProjet(selectedProjet.getId());	
					
		}	
	}
	
	
	
	public void  initListPartenaireProjet(){
		if(structureId != null){
			List<PartenaireDto> listProjPartenairedto = serviceLocator.getRecherchePartenaireService().findStructureRecherchePartenaires(structureId);	
			listTypePartenaireProjet = new ArrayList<SelectItem>();
			for (PartenaireDto partenaireDto : listProjPartenairedto) {
				listTypePartenaireProjet.add(new SelectItem(partenaireDto.getId(),partenaireDto.getContratReference()));
			}			
		}	
		
		
	}
	
	public void initListThemes() {
		if(structureId != null){
			List<ThemeDto> listThemesdto = serviceLocator.getRechercheThemeService().findThemesByStructureGroup(structureId,groupeId);	
			listThemes = new ArrayList<SelectItem>();
			for (ThemeDto themeDto : listThemesdto) {
				listThemes.add(new SelectItem(themeDto.getId(),themeDto.getNcThemeLibelleLatin()));
			}			
		}	
	}
	

	/**
	 * [CircuitValidationBB.prepareEditProcess] Method 
	 * @author rlaib  on : 20 janv. 2015  12:47:58
	 */
	public void prepareEditProcess() {
		if (selectedProcessusId != null) {
			toEditProjetDialog = true;
			selectedProcessus= serviceLocator.getProcessusService().findById(selectedProcessusId);
		}
	}
	
	/**
	 * [CircuitValidationBB.prepareEditOneStep] Method 
	 * @author rlaib  on : 20 janv. 2015  12:48:01
	 */
	public void prepareEditOneStep() {
		if (selectedStepId != null) {
			toEditStepDialog = true;
			selectedStep = serviceLocator.getProcessusService().findStepById(selectedStepId);
			if(listGroupes == null) {
				initListGroupes();
				initListRoles();
			}
			initListPreviousSteps();
		}
	}
	
	/**
	 * [CircuitValidationBB.saveProcess] Method 
	 * @author rlaib  on : 20 janv. 2015  12:48:24
	 */
	public void saveProjet() {
		
		RequestContext context = RequestContext.getCurrentInstance();
		if(selectedProjet == null) return;
		
			String structStr="";			
			selectedProjet = serviceLocator.getRechercheProjetService().saveProjet(selectedProjet,"",structStr);			
			initProjet();		
		
		context.addCallbackParam("isValid", true);		
	}
	
public void saveProjetCloture() {
	
		RequestContext context = RequestContext.getCurrentInstance();		
		if(selectedProjet == null)		return;		
		if(selectedProjet.getDateIdentification().compareTo(selectedProjet.getDateCloture())>=0) {
			// La date fin doit etre superieure a la date debut
				CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
						.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_PROJET_RESSOURCE_MESSAGE_FILE_NAME,
										RechercheConstantBean.RECHERCHE_PROJET_RESSOURCE_MESSAGE_KEY_PROJET_DATE_CLOTURE_DIALOG_CODE_CONTROL ),
				CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_PROJET_RESSOURCE_MESSAGE_FILE_NAME,
					RechercheConstantBean.RECHERCHE_PROJET_RESSOURCE_MESSAGE_KEY_PROJET_DATE_CLOTURE_DIALOG_CODE_CONTROL ));
				context.addCallbackParam("isValid", false);
				return;
			}
		
		NomenclatureDto nomenclatureDto =null;
		if(selectedClotureId!=null)nomenclatureDto =serviceLocator.getNomenclatureService().findById(selectedClotureId);
		if(nomenclatureDto==null) {context.addCallbackParam("isValid", false);
			return ;}
		selectedProjet.setTypeClotureDto(nomenclatureDto);
		SituationEntiteDto situationEntiteDtoCLTR = this.serviceLocator.getSituationService().findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_PROJET_CODE,UtilConstant.SITUATION_CLOTURE_PROJET);
			selectedProjet.setSituationDto(situationEntiteDtoCLTR);
			selectedProjet = serviceLocator.getRechercheProjetService().saveProjet(selectedProjet,"","");
			initProjet();		
			toEditProjetDialog=true;
		context.addCallbackParam("isValid", true);		
	}
	
	
	
	
	
	
	
	
	
public void saveEvaluationGlobal() {		
		RequestContext context = RequestContext.getCurrentInstance();
		@SuppressWarnings("unused")
		boolean nouvelleEvaluation= false;
		if(selectedProjet == null)	return;
		else {			
			if(evaluationProjetDto != null){if(evaluationProjetDto.getId() == null){
				evaluationProjetDto.setProjetDto(selectedProjet);
				nouvelleEvaluation= true;
			}
			
		////controle de la date
					if(selectedProjet.getDateIdentification().compareTo(evaluationProjetDto.getDateEvaluation())>0) {
						// La date fin doit etre superieure a la date debut
							CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
									.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_PROJET_RESSOURCE_MESSAGE_FILE_NAME,
													RechercheConstantBean.RECHERCHE_PROJET_RESSOURCE_MESSAGE_KEY_PROJET_DATE_EVALUATION_CODE_CONTROL ),
							CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_PROJET_RESSOURCE_MESSAGE_FILE_NAME,
								RechercheConstantBean.RECHERCHE_PROJET_RESSOURCE_MESSAGE_KEY_PROJET_DATE_EVALUATION_CODE_CONTROL ));
							context.addCallbackParam("isValid", false);
							return;
						}
			
			if(selectedProjet.getDateIdentification().compareTo(evaluationProjetDto.getDateFinPeriode())>0) {
				// La date fin doit etre superieure a la date debut
					CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
							.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_PROJET_RESSOURCE_MESSAGE_FILE_NAME,
											RechercheConstantBean.RECHERCHE_PROJET_RESSOURCE_MESSAGE_KEY_PROJET_DATE_FIN_PERIODE_DIALOG_CODE_CONTROL ),
					CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_PROJET_RESSOURCE_MESSAGE_FILE_NAME,
						RechercheConstantBean.RECHERCHE_PROJET_RESSOURCE_MESSAGE_KEY_PROJET_DATE_FIN_PERIODE_DIALOG_CODE_CONTROL ));
					context.addCallbackParam("isValid", false);
					return;
				}
					////////////
					
			evaluationProjetDto=serviceLocator.getRechercheEvaluationProjetService().saveEvaluationProjet(evaluationProjetDto);
			toEditProjetDialog= true;	
			}
			
		}
		
		
		
		if(nouvelleEvaluation) loadlistEvaluationIndicateur();
		context.addCallbackParam("isValid", true);
		
	}
public void loadlistEvaluationIndicateur(){	
	if(listIndEvaluation!=null)	if(!listIndEvaluation.isEmpty()){
		for(IndEvaluationDto indevaluation:listIndEvaluation){
			EvaluationIndicateurDto evaluationIndicateurDto=new  EvaluationIndicateurDto();
			evaluationIndicateurDto.setEvaluationProjetDto(evaluationProjetDto);
			evaluationIndicateurDto.setIndEvaluationDto(indevaluation);			
			evaluationIndicateurDto=	serviceLocator.getRechercheEvaluationIndicateurService().saveEvaluationIndicateur(evaluationIndicateurDto);			
			}
	}
	initEvaluationIndicateur();
	
}
	
	public void validerProjet() {
	if(listEtapeValidation != null)	if(!listEtapeValidation .isEmpty()){
		for(@SuppressWarnings("unused") EtapeValidationDto etapeValidationDao:listEtapeValidation){
			
		}
	}
		
	}
	
	public List<EtapeDto> initListEtapeDto(){
		if(programme != null) {			
			return serviceLocator.getRechercheEtapeValidationService().findListEtapesByIdProcesus(programme.getCircuitValidationId());	
		}else
		return null;
		
		
	}
	
	/**
	 * [CircuitValidationBB.saveOneStep] Method 
	 * @author rlaib  on : 20 janv. 2015  12:47:50
	 */
	public void saveOneStep() {
		
		RequestContext context = RequestContext.getCurrentInstance();
		if(selectedStep== null)
			return;
		else {
			if(!toEditStepDialog && listSteps != null) {
				// Test for already existing Step
						for (EtapeDto _etapeDto : listSteps) {
							if(_etapeDto.getGroupeId().equals(selectedStep.getGroupeId()) && _etapeDto.getRoleId().equals(selectedStep.getRoleId())) {
								CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
										.getStringValueFromBundleResource(
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_STEP_EXISTING_GROUPE_ROLE),
												CommonMessagesUtils.getStringValueFromBundleResource(
														RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
														RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_STEP_EXISTING_GROUPE_ROLE));
								return;
							}
							if(_etapeDto.getLibelleFr().trim().equalsIgnoreCase(selectedStep.getLibelleFr().trim())) {
								CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
										.getStringValueFromBundleResource(
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_STEP_EXISTING_LABEL_FR),
								CommonMessagesUtils.getStringValueFromBundleResource(
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_STEP_EXISTING_LABEL_FR));
								return;
							}
						}
			}
		
		}
	
		selectedStep.setProcessusId(selectedProcessus.getId());
		selectedStep.setProcessusEntiteCode(selectedProcessus.getEntiteCode());
		selectedStep.setEstMultiple(false);
		selectedStep.setMultiplicite(1);
		selectedStep = serviceLocator.getProcessusService().saveOneStepValidation(selectedStep);
		loadProjetDetail();
		
		// Ajout groupe reussi
		if(!toEditStepDialog) {
				CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
							.getStringValueFromBundleResource(
										RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
										RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_STEP_SUCCESS),
				CommonMessagesUtils.getStringValueFromBundleResource(
						RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
						RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_ADD_STEP_SUCCESS));
		}
		else {
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
					.getStringValueFromBundleResource(
								RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
								RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_UPDATE_STEP_SUCCESS),
		CommonMessagesUtils.getStringValueFromBundleResource(
				RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
				RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_UPDATE_STEP_SUCCESS));
		}
	
		context.addCallbackParam("isValid", true);
			
		
	}

	/**
	 * [CircuitValidationBB.initProcess] Method 
	 * @author rlaib  on : 19 janv. 2015  13:25:14
	 */
	private void initProcess() {

		listProcessus = serviceLocator.getProcessusService().findByCodeDomaine(RechercheConstantBean.RCH_RECHERCHE_DOMAINE_CODE);
		listProcessusClone = new ArrayList<ProcessusDto>(listProcessus.size());
		for (ProcessusDto _item : listProcessus) {
			listProcessusClone.add(SerializationUtils.clone(_item));
		}
	}

	/**
	 * [CircuitValidationBB.removeProcess] Method 
	 * @author rlaib  on : 20 janv. 2015  12:48:45
	 */
	public void removeProcess() {

		if(selectedProcessus== null)
				return;
		selectedProcessusId = selectedProcessus.getId();
		serviceLocator.getProcessusService().remove(selectedProcessusId);
		initProcess();
		selectedProcessus = null;
		canShowPjetDetails = false;
		// Suppression Process reussie
		CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
					RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
					RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_REMOVE_PROCESS_SUCCESS),
		CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
					RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_REMOVE_PROCESS_SUCCESS));
	}
	public void removeOneStep() {
		
		if(selectedStepId== null)
			return;
		serviceLocator.getProcessusService().removeOneStep(selectedStepId);
		loadProjetDetail();
		// Suppression Process reussie
		CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
				RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
				RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_REMOVE_STEP_SUCCESS),
		CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_FILE_NAME,
						RechercheConstantBean.RECHERCHE_CIRCUIT_VALIDATION_RESSOURCE_MESSAGE_KEY_REMOVE_STEP_SUCCESS));
	}
	
	// ===================================================================  
	// Listeners
	// ===================================================================

	/**
	 * [CircuitValidationBB.onProcessusRowSelect] Method 
	 * @author rlaib  on : 20 janv. 2015  12:48:49
	 * @param event
	 */
	
	
	public void onProjetRowSelect(SelectEvent event) {  
		
		
		toEditProjetDialog= false;
		canShowPjetDetails= true;
		canShowStepsDetails = true;
		loadProjetDetail();	
		if( selectedProjet != null){
			initListStructure();
			if( selectedProjet.getStructureDto() != null){structureId=selectedProjet.getStructureDto().getId();}else structureId = 0L;
			initListGroupes();
			if( selectedProjet.getGroupeDto()!= null){groupeId=selectedProjet.getGroupeDto().getId();}else groupeId = 0L;
			initListThemes();
			if( selectedProjet.getThemeDto() != null){themeId=selectedProjet.getThemeDto().getId();}else themeId = 0L;
			initListTypePrograms();
			if( selectedProjet.getProgrameDto() != null)typeProgrammeId=selectedProjet.getProgrameDto().getId();else typeProgrammeId = 0L;
			initListActivite();
			initListPartenaireProjet();
			initListValidation();
			initListIndicateur();
			initListMotsCles();
			initListProjetPartenaire();
			initEvaluationProjet();
			initEvaluationIndicateur();
			
			
		}
		 
		
	}
public void initEvaluationIndicateur(){
	if(selectedProjet != null){
		listEvaluationIndicateur= serviceLocator.getRechercheEvaluationIndicateurService().findListEvaluationIndicateurByIdProjet(selectedProjet.getId());	
	}
		
	}
	
	
	public void initEvaluationProjet(){
		
		if(selectedProjet != null){			
			evaluationProjetDto=serviceLocator.getRechercheEvaluationProjetService().findEvaluationProjetByIdProjet(selectedProjet.getId());
			if(evaluationProjetDto== null) evaluationProjetDto = new EvaluationProjetDto();
			if(evaluationProjetDto.getId()!= null)toEditProjetDialog= true;				
					
		}	
		
		
	}
	
	public void saveActivite(){	
		RequestContext context = RequestContext.getCurrentInstance();
		if(activiteProjet !=  null){
			activiteProjet=	serviceLocator.getRechercheActiviteProjetService().saveActivite(activiteProjet);
			initListActivite();
		}
		context.addCallbackParam("isValid", true);
		
	}
	
	
	public void savePartenaire(){
		try{
		if(partenaireProjet !=  null){			
			partenaireProjet.setProjetDto(selectedProjet);
			partenaireProjet=serviceLocator.getRechercheProjetPartenaireService().savePartenaire(partenaireProjet);
			initListProjetPartenaire() ;		}
		
		}catch(Exception e){}
		
	}
	
	public void saveMotcle(){	
		if(motcleDto !=  null){			
			motcleDto.setProjetDto(selectedProjet);
			if(selectedMotcleId !=  null){
				NomenclatureDto nc=serviceLocator.getNomenclatureService().findById(selectedMotcleId);
				motcleDto.setMotCleNomenclatureDto(nc);
			}
			motcleDto=serviceLocator.getRechercheMotcleService().saveMotcle(motcleDto);
			initListMotsCles();		}
		
	}
	
	public void saveIndevaluation(){	
		if(indEvaluationDto !=  null){			
			indEvaluationDto.setProjetDto(selectedProjet);
			if(selectedIndicateurId !=  null){
				NomenclatureDto nc=serviceLocator.getNomenclatureService().findById(selectedIndicateurId);
				indEvaluationDto.setNcIndicateurDto(nc);
				
				
			}
			if(selectedTypeAppreciationId !=  null){
				NomenclatureDto nc=serviceLocator.getNomenclatureService().findById(selectedTypeAppreciationId);
				indEvaluationDto.setTypeAppreciationDto(nc);
			}
			
			indEvaluationDto=serviceLocator.getRechercheIndEvaluationService().saveIndEvaluation(indEvaluationDto);
			initListIndicateur();			
		
		}		
		
	}
	
	
	public void saveEvaluationIndicateur(){	
		
		RequestContext context = RequestContext.getCurrentInstance();
		
		
		if(evaluationIndicateurDto !=  null){
			
			if(selectedTypeAppreciationId !=  null){
				NomenclatureDto nc=serviceLocator.getNomenclatureService().findById(selectedTypeAppreciationId);
				evaluationIndicateurDto.setTypeAppreciationDto(nc);
			}			
			evaluationIndicateurDto=serviceLocator.getRechercheEvaluationIndicateurService().saveIndEvaluation(evaluationIndicateurDto);
			initEvaluationIndicateur();	
			context.addCallbackParam("isValid", true);
		}
		
		
	}
	
	
	
	
	public void saveValidation(){		
		if(etapeValidationDto !=  null){
			etapeValidationDto=	serviceLocator.getRechercheEtapeValidationService().saveValidation(etapeValidationDto);
			initListValidation();
		}
		
	}
	
	
	public void updateindevaluation() {		
		if(indevaluationId == null)	return;	
		indEvaluationDto=serviceLocator.getRechercheIndEvaluationService().findById(indevaluationId);				
}
	
	public void updateEvaluationIndicateur() {		
		if(evaluationIndicateurDtoId == null)	return;	
		evaluationIndicateurDto=serviceLocator.getRechercheEvaluationIndicateurService().findById(evaluationIndicateurDtoId);				
}


	public void removeindevaluation() {		
		if(indevaluationId== null)	return;	
		serviceLocator.getRechercheIndEvaluationService().removeIndEvaluation(indevaluationId);
		initListIndicateur();	
}
		

	
	
	
	public void removeMotcle() {		
		if(motcleId!= null)	serviceLocator.getRechercheMotcleService().removeMotcle(motcleId);
		initListMotsCles();			
}
	
	
	public void updateMotcle() {		
		if(motcleId== null)	return;	
		motcleDto=serviceLocator.getRechercheMotcleService().findById(motcleId);
		initListMotsCles();	
}
	
	
	
	
	public void removeActivite() {		
			if(activiteId!= null) serviceLocator.getRechercheActiviteProjetService().removeActiviteProjet(activiteId);
			initListActivite();			
	}
	
	public void updateActivite() {		
		if(activiteId== null)	return;	
		activiteProjet=serviceLocator.getRechercheActiviteProjetService().findById(activiteId);				
}
	
	public void updateValidation() {		
		if(validationId== null)	return;	
		etapeValidationDto=serviceLocator.getRechercheEtapeValidationService().findById(validationId);				
}

	public void initListActivite(){		
		if( selectedProjet != null){			
			listActiviteProjet = serviceLocator.getRechercheActiviteProjetService().finListActiviteByProjet(selectedProjet.getId());
			if(listActiviteProjet==null) listActiviteProjet = new ArrayList<ActiviteProjetDto>();
		}
	}
	
	public void initListValidation(){		
		if( selectedProjet != null){			
			listEtapeValidation = serviceLocator.getRechercheEtapeValidationService().findListEtapeValidationByProjet(selectedProjet.getId());
			if(listEtapeValidation==null) listEtapeValidation = new ArrayList<EtapeValidationDto>();
		}
	}
	
private void initListTypeActivite() {		
		List<NomenclatureDto> listTypes = serviceLocator.getNomenclatureService().findByName(UtilConstant.NC_RCH_TYPE_ACTIVITES_RECHERCHE);
		if(listTypes  != null) {
			listTypeActivite =  new ArrayList<SelectItem>();
			for (NomenclatureDto _nomenclatureDto : listTypes) {
				listTypeActivite.add(new SelectItem(_nomenclatureDto.getId(),_nomenclatureDto.getLibelleLongFr()));
			}
		}
		
	}
	
	
	
	public void addActiviteProjet(){
		activiteProjet = new ActiviteProjetDto();	
	}
	public void addMotcle(){
		motcleDto = new MotcleDto();	
	}
	public void addindEvaluation(){
		indEvaluationDto = new IndEvaluationDto();	
	}
	
	
	public void addEtapeValidation(){
		etapeValidationDto = new EtapeValidationDto();	
	}
	
	public void addPartenaireProjet(){
		partenaireProjet = new ProjetPartenaireDto();	
	}
	

	public List<ProjetDto> getListprojet() {
		return listprojet;
	}

	public void setListprojet(List<ProjetDto> listprojet) {
		this.listprojet = listprojet;
	}

	public ProjetDto getSelectedProjet() {
		return selectedProjet;
	}

	public void setSelectedProjet(ProjetDto selectedProjet) {
		this.selectedProjet = selectedProjet;
	}

	public Integer getSelectedEntiteId() {
		return selectedEntiteId;
	}

	public void setSelectedEntiteId(Integer selectedEntiteId) {
		this.selectedEntiteId = selectedEntiteId;
	}

	public Long getSelectedProjetId() {
		return selectedProjetId;
	}

	public void setSelectedProjetId(Long selectedProjetId) {
		this.selectedProjetId = selectedProjetId;
	}

	public boolean isCanShowPjetDetails() {
		return canShowPjetDetails;
	}

	public void setCanShowPjetDetails(boolean canShowPjetDetails) {
		this.canShowPjetDetails = canShowPjetDetails;
	}

	public boolean isToEditProjetDialog() {
		return toEditProjetDialog;
	}

	public void setToEditProjetDialog(boolean toEditProjetDialog) {
		this.toEditProjetDialog = toEditProjetDialog;
	}

	public boolean isCanDeleteProjet() {
		return canDeleteProjet;
	}

	public void setCanDeleteProjet(boolean canDeleteProjet) {
		this.canDeleteProjet = canDeleteProjet;
	}

	public List<SelectItem> getListProgramsTypes() {
		return listProgramsTypes;
	}

	public void setListProgramsTypes(List<SelectItem> listProgramsTypes) {
		this.listProgramsTypes = listProgramsTypes;
	}

	

	public Long getTypeProgrammeId() {
		return typeProgrammeId;
	}

	public void setTypeProgrammeId(Long typeProgrammeId) {
		this.typeProgrammeId = typeProgrammeId;
	}

	public Long getGroupeId() {
		return groupeId;
	}

	public void setGroupeId(Long groupeId) {
		this.groupeId = groupeId;
	}

	public List<ActiviteProjetDto> getListActiviteProjet() {
		return listActiviteProjet;
	}

	public void setListActiviteProjet(List<ActiviteProjetDto> listActiviteProjet) {
		this.listActiviteProjet = listActiviteProjet;
	}

	public List<PartenaireDto> getListPartenaireDto() {
		return listPartenaireDto;
	}

	public void setListPartenaireDto(List<PartenaireDto> listPartenaireDto) {
		this.listPartenaireDto = listPartenaireDto;
	}

	public List<MotcleDto> getListMotcleDto() {
		return listMotcleDto;
	}

	public void setListMotcleDto(List<MotcleDto> listMotcleDto) {
		this.listMotcleDto = listMotcleDto;
	}

	public List<IndEvaluationDto> getListIndEvaluation() {
		return listIndEvaluation;
	}

	public void setListIndEvaluation(List<IndEvaluationDto> listIndEvaluation) {
		this.listIndEvaluation = listIndEvaluation;
	}

	public List<SelectItem> getListStructureTypes() {
		return listStructureTypes;
	}

	public void setListStructureTypes(List<SelectItem> listStructureTypes) {
		this.listStructureTypes = listStructureTypes;
	}

	public RefEtablissementDto getRefEtablissementDto() {
		return refEtablissementDto;
	}

	public void setRefEtablissementDto(RefEtablissementDto refEtablissementDto) {
		this.refEtablissementDto = refEtablissementDto;
	}

	public Long getStructureId() {
		return structureId;
	}

	public void setStructureId(Long structureId) {
		this.structureId = structureId;
	}

	public Long getThemeId() {
		return themeId;
	}

	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}

	public List<SelectItem> getListThemes() {
		return listThemes;
	}

	public void setListThemes(List<SelectItem> listThemes) {
		this.listThemes = listThemes;
	}

	public ActiviteProjetDto getActiviteProjet() {
		return activiteProjet;
	}

	public void setActiviteProjet(ActiviteProjetDto activiteProjet) {
		this.activiteProjet = activiteProjet;
	}

	public Long getActiviteId() {
		return activiteId;
	}

	public void setActiviteId(Long activiteId) {
		this.activiteId = activiteId;
	}

	public List<SelectItem> getListTypeActivite() {
		return listTypeActivite;
	}

	public void setListTypeActivite(List<SelectItem> listTypeActivite) {
		this.listTypeActivite = listTypeActivite;
	}

	public Integer getSelectedActiviteNcId() {
		return selectedActiviteNcId;
	}

	public void setSelectedActiviteNcId(Integer selectedActiviteNcId) {
		this.selectedActiviteNcId = selectedActiviteNcId;
	}

	public ProjetPartenaireDto getPartenaireProjet() {
		return partenaireProjet;
	}

	public void setPartenaireProjet(ProjetPartenaireDto partenaireProjet) {
		this.partenaireProjet = partenaireProjet;
	}

	public List<ProjetPartenaireDto> getListPartenaireProjet() {
		return listPartenaireProjet;
	}

	public void setListPartenaireProjet(
			List<ProjetPartenaireDto> listPartenaireProjet) {
		this.listPartenaireProjet = listPartenaireProjet;
	}

	public Long getSelectedPartenaireProjetId() {
		return selectedPartenaireProjetId;
	}

	public void setSelectedPartenaireProjetId(Long selectedPartenaireProjetId) {
		this.selectedPartenaireProjetId = selectedPartenaireProjetId;
	}

	public List<SelectItem> getListTypePartenaireProjet() {
		return listTypePartenaireProjet;
	}

	public void setListTypePartenaireProjet(
			List<SelectItem> listTypePartenaireProjet) {
		this.listTypePartenaireProjet = listTypePartenaireProjet;
	}

	public List<EtapeValidationDto> getListEtapeValidation() {
		return listEtapeValidation;
	}

	public void setListEtapeValidation(List<EtapeValidationDto> listEtapeValidation) {
		this.listEtapeValidation = listEtapeValidation;
	}

	public EtapeValidationDto getEtapeValidationDto() {
		return etapeValidationDto;
	}

	public void setEtapeValidationDto(EtapeValidationDto etapeValidationDto) {
		this.etapeValidationDto = etapeValidationDto;
	}

	public Long getValidationId() {
		return validationId;
	}

	public void setValidationId(Long validationId) {
		this.validationId = validationId;
	}

	public List<SelectItem> getListMotcle() {
		return listMotcle;
	}
	
	

	public void setListMotcle(List<SelectItem> listMotcle) {
		this.listMotcle = listMotcle;
	}

	

	public Integer getSelectedMotcleId() {
		return selectedMotcleId;
	}

	public void setSelectedMotcleId(Integer selectedMotcleId) {
		this.selectedMotcleId = selectedMotcleId;
	}

	public MotcleDto getMotcleDto() {
		return motcleDto;
	}

	public void setMotcleDto(MotcleDto motcleDto) {
		this.motcleDto = motcleDto;
	}

	public Long getMotcleId() {
		return motcleId;
	}

	public void setMotcleId(Long motcleId) {
		this.motcleId = motcleId;
	}

	public List<SelectItem> getListindevaluation() {
		return listindevaluation;
	}

	public void setListindevaluation(List<SelectItem> listindevaluation) {
		this.listindevaluation = listindevaluation;
	}

	public List<SelectItem> getListTypeAppreciation() {
		return listTypeAppreciation;
	}

	public void setListTypeAppreciation(List<SelectItem> listTypeAppreciation) {
		this.listTypeAppreciation = listTypeAppreciation;
	}

	public Integer getSelectedIndicateurId() {
		return selectedIndicateurId;
	}

	public void setSelectedIndicateurId(Integer selectedIndicateurId) {
		this.selectedIndicateurId = selectedIndicateurId;
	}

	public Integer getSelectedTypeAppreciationId() {
		return selectedTypeAppreciationId;
	}

	public void setSelectedTypeAppreciationId(Integer selectedTypeAppreciationId) {
		this.selectedTypeAppreciationId = selectedTypeAppreciationId;
	}

	public IndEvaluationDto getIndEvaluationDto() {
		return indEvaluationDto;
	}

	public void setIndEvaluationDto(IndEvaluationDto indEvaluationDto) {
		this.indEvaluationDto = indEvaluationDto;
	}

	public Long getIndevaluationId() {
		return indevaluationId;
	}

	public void setIndevaluationId(Long indevaluationId) {
		this.indevaluationId = indevaluationId;
	}

	public EvaluationProjetDto getEvaluationProjetDto() {
		return evaluationProjetDto;
	}

	public void setEvaluationProjetDto(EvaluationProjetDto evaluationProjetDto) {
		this.evaluationProjetDto = evaluationProjetDto;
	}

	public List<EvaluationIndicateurDto> getListEvaluationIndicateur() {
		return listEvaluationIndicateur;
	}

	public void setListEvaluationIndicateur(
			List<EvaluationIndicateurDto> listEvaluationIndicateur) {
		this.listEvaluationIndicateur = listEvaluationIndicateur;
	}

	public Long getEvaluationIndicateurDtoId() {
		return evaluationIndicateurDtoId;
	}

	public void setEvaluationIndicateurDtoId(Long evaluationIndicateurDtoId) {
		this.evaluationIndicateurDtoId = evaluationIndicateurDtoId;
	}

	public EvaluationIndicateurDto getEvaluationIndicateurDto() {
		return evaluationIndicateurDto;
	}

	public void setEvaluationIndicateurDto(
			EvaluationIndicateurDto evaluationIndicateurDto) {
		this.evaluationIndicateurDto = evaluationIndicateurDto;
	}

	public SituationEntiteDto getSituationEntiteDto() {
		return situationEntiteDto;
	}

	public void setSituationEntiteDto(SituationEntiteDto situationEntiteDto) {
		this.situationEntiteDto = situationEntiteDto;
	}

	public List<SelectItem> getListCloture() {
		return listCloture;
	}

	public void setListCloture(List<SelectItem> listCloture) {
		this.listCloture = listCloture;
	}

	public Integer getSelectedClotureId() {
		return selectedClotureId;
	}

	public void setSelectedClotureId(Integer selectedClotureId) {
		this.selectedClotureId = selectedClotureId;
	}

	
	
	
	

	
	
	
	
	
	
}
