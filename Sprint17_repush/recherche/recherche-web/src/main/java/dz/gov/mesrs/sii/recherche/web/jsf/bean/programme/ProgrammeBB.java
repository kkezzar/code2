/**
 * [dz.gov.mesrs.sii.recherche.web.jsf.bean.structure.RechercheStructureBB.java] 
 * @author rlaib on : 16 d�c. 2014  16:06:14
 * 
 */
package dz.gov.mesrs.sii.recherche.web.jsf.bean.programme;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.SerializationUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.ProcessusDto;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.recherche.business.model.dto.AxeDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.PartenaireDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ProgrammeDto;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.RechercheConstantBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPartenaireDto;

@ManagedBean(name = "programmeBB")
@ViewScoped
public class ProgrammeBB extends BaseBean {
	
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 16 dec. 2014  16:08:20
	 */
	private static final long serialVersionUID = 1L;

	// ===================================================================  
	// Constructors
	// ===================================================================

	public ProgrammeBB() {
		
	}
	
	@PostConstruct
	public void init() {

	}

	// ===================================================================  
	// Properties Model
	// ===================================================================
	private String searchComponentKeyWords;
	private List<ProgrammeDto> listPrograms; 
	private ProgrammeDto selectedProgram;
	private ProgrammeDto currentProgram;
	private boolean canShowProgramDetails = false;
	private boolean toEditProgram= false;
	private boolean toEditAxeDialog= false;
	private boolean canDeleteProgram = false;
	private boolean canAddAxe = false;
	private String titleDetailsProgram;
	private String titleDetailsAxeProgram;
	private List<SelectItem> listProgramsTypes;
	private List<SelectItem> listValidationProcess;
	private List<SelectItem> listNcAxesRecherche;
	private List<AxeDto> listProgramAxes;
	private AxeDto selectedAxe;
	private Long selectedAxeId;
	private List<PartenaireDto> listProgramPartners;
	private List<RefPartenaireDto> listProgramPartnersSelection;
	private PartenaireDto  selectedProgramPartner;
	private Integer  selectedProgramPartnerId;
	
	/**
	 * [ProgrammeBB.canAddAxe] Getter 
	 * @author rlaib on : 28 janv. 2015  14:19:25
	 * @return the canAddAxe
	 */
	public boolean isCanAddAxe() {
		return canAddAxe;
	}

	/**
	 * [ProgrammeBB.canAddAxe] Setter 
	 * @author rlaib on : 28 janv. 2015  14:19:25
	 * @param canAddAxe the canAddAxe to set
	 */
	public void setCanAddAxe(boolean canAddAxe) {
		this.canAddAxe = canAddAxe;
	}

	/**
	 * [ProgrammeBB.listProgramPartnersSelection] Getter 
	 * @author rlaib on : 28 janv. 2015  13:56:13
	 * @return the listProgramPartnersSelection
	 */
	public List<RefPartenaireDto> getListProgramPartnersSelection() {
		return listProgramPartnersSelection;
	}

	/**
	 * [ProgrammeBB.listProgramPartnersSelection] Setter 
	 * @author rlaib on : 28 janv. 2015  13:56:13
	 * @param listProgramPartnersSelection the listProgramPartnersSelection to set
	 */
	public void setListProgramPartnersSelection(
			List<RefPartenaireDto> listProgramPartnersSelection) {
		this.listProgramPartnersSelection = listProgramPartnersSelection;
	}

	/**
	 * [ProgrammeBB.listProgramPartners] Getter 
	 * @author rlaib on : 28 janv. 2015  13:33:10
	 * @return the listProgramPartners
	 */
	public List<PartenaireDto> getListProgramPartners() {
		return listProgramPartners;
	}

	/**
	 * [ProgrammeBB.listProgramPartners] Setter 
	 * @author rlaib on : 28 janv. 2015  13:33:10
	 * @param listProgramPartners the listProgramPartners to set
	 */
	public void setListProgramPartners(List<PartenaireDto> listProgramPartners) {
		this.listProgramPartners = listProgramPartners;
	}

	/**
	 * [ProgrammeBB.selectedProgramPartner] Getter 
	 * @author rlaib on : 28 janv. 2015  13:33:10
	 * @return the selectedProgramPartner
	 */
	public PartenaireDto getSelectedProgramPartner() {
		return selectedProgramPartner;
	}

	/**
	 * [ProgrammeBB.selectedProgramPartner] Setter 
	 * @author rlaib on : 28 janv. 2015  13:33:10
	 * @param selectedProgramPartner the selectedProgramPartner to set
	 */
	public void setSelectedProgramPartner(PartenaireDto selectedProgramPartner) {
		this.selectedProgramPartner = selectedProgramPartner;
	}

	/**
	 * [ProgrammeBB.selectedProgramPartnerId] Getter 
	 * @author rlaib on : 28 janv. 2015  13:33:10
	 * @return the selectedProgramPartnerId
	 */
	public Integer getSelectedProgramPartnerId() {
		return selectedProgramPartnerId;
	}

	/**
	 * [ProgrammeBB.selectedProgramPartnerId] Setter 
	 * @author rlaib on : 28 janv. 2015  13:33:10
	 * @param selectedProgramPartnerId the selectedProgramPartnerId to set
	 */
	public void setSelectedProgramPartnerId(Integer selectedProgramPartnerId) {
		this.selectedProgramPartnerId = selectedProgramPartnerId;
	}

	/**
	 * [ProgrammeBB.selectedProgram] Getter 
	 * @author rlaib on : 28 janv. 2015  10:33:03
	 * @return the selectedProgram
	 */
	public ProgrammeDto getSelectedProgram() {
		return selectedProgram;
	}

	/**
	 * [ProgrammeBB.selectedProgram] Setter 
	 * @author rlaib on : 28 janv. 2015  10:33:03
	 * @param selectedProgram the selectedProgram to set
	 */
	public void setSelectedProgram(ProgrammeDto selectedProgram) {
		this.selectedProgram = selectedProgram;
	}

	/**
	 * [ProgrammeBB.currentProgram] Getter 
	 * @author rlaib on : 28 janv. 2015  10:32:02
	 * @return the currentProgram
	 */
	public ProgrammeDto getCurrentProgram() {
		return currentProgram;
	}

	/**
	 * [ProgrammeBB.currentProgram] Setter 
	 * @author rlaib on : 28 janv. 2015  10:32:02
	 * @param currentProgram the currentProgram to set
	 */
	public void setCurrentProgram(ProgrammeDto currentProgram) {
		this.currentProgram = currentProgram;
	}

	/**
	 * [ProgrammeBB.selectedAxeId] Getter 
	 * @author rlaib on : 28 janv. 2015  09:31:49
	 * @return the selectedAxeId
	 */
	public Long getSelectedAxeId() {
		return selectedAxeId;
	}

	/**
	 * [ProgrammeBB.selectedAxeId] Setter 
	 * @author rlaib on : 28 janv. 2015  09:31:49
	 * @param selectedAxeId the selectedAxeId to set
	 */
	public void setSelectedAxeId(Long selectedAxeId) {
		this.selectedAxeId = selectedAxeId;
	}

	/**
	 * [ProgrammeBB.toEditAxeDialog] Getter 
	 * @author rlaib on : 27 janv. 2015  15:32:23
	 * @return the toEditAxeDialog
	 */
	public boolean isToEditAxeDialog() {
		return toEditAxeDialog;
	}

	/**
	 * [ProgrammeBB.toEditAxeDialog] Setter 
	 * @author rlaib on : 27 janv. 2015  15:32:23
	 * @param toEditAxeDialog the toEditAxeDialog to set
	 */
	public void setToEditAxeDialog(boolean toEditAxeDialog) {
		this.toEditAxeDialog = toEditAxeDialog;
	}

	/**
	 * [ProgrammeBB.listNcAxesRecherche] Getter 
	 * @author rlaib on : 27 janv. 2015  15:14:23
	 * @return the listNcAxesRecherche
	 */
	public List<SelectItem> getListNcAxesRecherche() {
		return listNcAxesRecherche;
	}

	/**
	 * [ProgrammeBB.listNcAxesRecherche] Setter 
	 * @author rlaib on : 27 janv. 2015  15:14:23
	 * @param listNcAxesRecherche the listNcAxesRecherche to set
	 */
	public void setListNcAxesRecherche(List<SelectItem> listNcAxesRecherche) {
		this.listNcAxesRecherche = listNcAxesRecherche;
	}

	/**
	 * [ProgrammeBB.titleDetailsAxeProgram] Getter 
	 * @author rlaib on : 27 janv. 2015  14:55:15
	 * @return the titleDetailsAxeProgram
	 */
	public String getTitleDetailsAxeProgram() {
		return titleDetailsAxeProgram;
	}

	/**
	 * [ProgrammeBB.titleDetailsAxeProgram] Setter 
	 * @author rlaib on : 27 janv. 2015  14:55:15
	 * @param titleDetailsAxeProgram the titleDetailsAxeProgram to set
	 */
	public void setTitleDetailsAxeProgram(String titleDetailsAxeProgram) {
		this.titleDetailsAxeProgram = titleDetailsAxeProgram;
	}

	/**
	 * [ProgrammeBB.listProgramAxes] Getter 
	 * @author rlaib on : 27 janv. 2015  14:09:12
	 * @return the listProgramAxes
	 */
	public List<AxeDto> getListProgramAxes() {
		return listProgramAxes;
	}

	/**
	 * [ProgrammeBB.listProgramAxes] Setter 
	 * @author rlaib on : 27 janv. 2015  14:09:12
	 * @param listProgramAxes the listProgramAxes to set
	 */
	public void setListProgramAxes(List<AxeDto> listProgramAxes) {
		this.listProgramAxes = listProgramAxes;
	}

	/**
	 * [ProgrammeBB.selectedAxe] Getter 
	 * @author rlaib on : 27 janv. 2015  14:09:12
	 * @return the selectedAxe
	 */
	public AxeDto getSelectedAxe() {
		return selectedAxe;
	}

	/**
	 * [ProgrammeBB.selectedAxe] Setter 
	 * @author rlaib on : 27 janv. 2015  14:09:12
	 * @param selectedAxe the selectedAxe to set
	 */
	public void setSelectedAxe(AxeDto selectedAxe) {
		this.selectedAxe = selectedAxe;
	}

	public List<SelectItem> getListProgramsTypes() {
		return listProgramsTypes;
	}

	public void setListProgramsTypes(List<SelectItem> listProgramsTypes) {
		this.listProgramsTypes = listProgramsTypes;
	}

	public List<SelectItem> getListValidationProcess() {
		return listValidationProcess;
	}

	public void setListValidationProcess(List<SelectItem> listValidationProcess) {
		this.listValidationProcess = listValidationProcess;
	}

	public String getTitleDetailsProgram() {
		return titleDetailsProgram;
	}

	public void setTitleDetailsProgram(String titleDetailsProgram) {
		this.titleDetailsProgram = titleDetailsProgram;
	}

	/**
	 * [ProgrammeBB.searchComponentKeyWords] Getter 
	 * @author rlaib on : 25 janv. 2015  14:54:06
	 * @return the searchComponentKeyWords
	 */
	public String getSearchComponentKeyWords() {
		return searchComponentKeyWords;
	}

	/**
	 * [ProgrammeBB.searchComponentKeyWords] Setter 
	 * @author rlaib on : 25 janv. 2015  14:54:06
	 * @param searchComponentKeyWords the searchComponentKeyWords to set
	 */
	public void setSearchComponentKeyWords(String searchComponentKeyWords) {
		this.searchComponentKeyWords = searchComponentKeyWords;
	}

	/**
	 * [ProgrammeBB.listPrograms] Getter 
	 * @author rlaib on : 25 janv. 2015  14:54:06
	 * @return the listPrograms
	 */
	public List<ProgrammeDto> getListPrograms() {
		return listPrograms;
	}

	/**
	 * [ProgrammeBB.listPrograms] Setter 
	 * @author rlaib on : 25 janv. 2015  14:54:06
	 * @param listPrograms the listPrograms to set
	 */
	public void setListPrograms(List<ProgrammeDto> listPrograms) {
		this.listPrograms = listPrograms;
	}

	/**
	 * [ProgrammeBB.canShowProgramDetails] Getter 
	 * @author rlaib on : 25 janv. 2015  14:54:06
	 * @return the canShowProgramDetails
	 */
	public boolean isCanShowProgramDetails() {
		return canShowProgramDetails;
	}

	/**
	 * [ProgrammeBB.canShowProgramDetails] Setter 
	 * @author rlaib on : 25 janv. 2015  14:54:06
	 * @param canShowProgramDetails the canShowProgramDetails to set
	 */
	public void setCanShowProgramDetails(boolean canShowProgramDetails) {
		this.canShowProgramDetails = canShowProgramDetails;
	}

	public boolean isToEditProgram() {
		return toEditProgram;
	}

	public void setToEditProgram(boolean toEditProgram) {
		this.toEditProgram = toEditProgram;
	}

	/**
	 * [ProgrammeBB.canDeleteProgram] Getter 
	 * @author rlaib on : 25 janv. 2015  14:54:06
	 * @return the canDeleteProgram
	 */
	public boolean isCanDeleteProgram() {
		return canDeleteProgram;
	}

	/**
	 * [ProgrammeBB.canDeleteProgram] Setter 
	 * @author rlaib on : 25 janv. 2015  14:54:06
	 * @param canDeleteProgram the canDeleteProgram to set
	 */
	public void setCanDeleteProgram(boolean canDeleteProgram) {
		this.canDeleteProgram = canDeleteProgram;
	}

	// ===================================================================  
	// Actions and Methods
	// ===================================================================
	public void searchPrograms() {
		listPrograms= serviceLocator.getRechercheProgrammeService().findByKeyWords(searchComponentKeyWords);
		canShowProgramDetails= false;
	}
	public void prepareAddProgram() {
		currentProgram = new ProgrammeDto();
		listProgramAxes = null;
		toEditProgram = false;
		canShowProgramDetails= true;
		canDeleteProgram= false;
		
		canAddAxe = false;
		titleDetailsProgram= CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME, 
				RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_PANEL_DETAILS_TITLE_ADD);
		
		if(listProgramsTypes == null) {
			initListTypePrograms();
		}
		
		if(listValidationProcess== null) {
			initListValidationProcess();
		}
	}
	private void initListValidationProcess() {
		
		List<ProcessusDto> _listProcess = serviceLocator.getProcessusService().findByCodeEntite(UtilConstant.ENTITE_RCH_PROGRAMME);
		if(_listProcess != null) {
			listValidationProcess = new ArrayList<SelectItem>();
			for (ProcessusDto _process : _listProcess) {
				listValidationProcess.add(new SelectItem(_process.getId(),_process.getLibelleFr()));
			}
		}
		
	}
	private void initListTypePrograms() {
		
		List<NomenclatureDto> listTypes = serviceLocator.getNomenclatureService().findByName(UtilConstant.NC_RCH_TYPE_PROGRAMME);
		if(listTypes  != null) {
		listProgramsTypes =  new ArrayList<SelectItem>();
			for (NomenclatureDto _nomenclatureDto : listTypes) {
				listProgramsTypes.add(new SelectItem(_nomenclatureDto.getId(),_nomenclatureDto.getLibelleLongFr()));
			}
		}
		
	}
	public void saveProgram() {
		
		if(currentProgram== null)
			return;
		else {
			if(!currentProgram.equals(selectedProgram)) {
					currentProgram = serviceLocator.getRechercheProgrammeService().insertOrUpdate(currentProgram);
					selectedProgram = SerializationUtils.clone(currentProgram);
//					if(toEditProgram) {
//							searchPrograms();
//							canShowProgramDetails = true;
//					}
//					else {
//						if(listPrograms == null)
//							listPrograms = new ArrayList<ProgrammeDto>();
//						listPrograms.add(currentProgram);
//					}
					CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
							.getStringValueFromBundleResource(
										RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME,
										RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_SAVE_PROGRAM_SUCCESS),
					CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_SAVE_PROGRAM_SUCCESS));
			}
			else {
					CommonMessagesUtils.addWarnMessageWithoutKey(CommonMessagesUtils
						.getStringValueFromBundleResource(
									RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME,
									RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_SAVE_PROGRAM_NO_CHANGES),
					CommonMessagesUtils.getStringValueFromBundleResource(
						RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME,
						RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_SAVE_PROGRAM_NO_CHANGES));
			}
			canAddAxe = true;
			toEditProgram = true;
			
		}
	}
	public void saveOneAxe() {
		
		RequestContext context = RequestContext.getCurrentInstance();
		if(selectedAxe== null)
			return;
		else {
			
			selectedAxe.setProgrammeId(currentProgram.getId());
			if(!toEditAxeDialog)  {
					// Test for existing Axe
					for (AxeDto _axe : listProgramAxes) {
						if(selectedAxe.getAxeNomenclatureId().equals(_axe.getAxeNomenclatureId())) {
							CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
									.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME,
													RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_SAVE_AXE_EXISTING_CONTROL),
							CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME,
								RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_SAVE_AXE_EXISTING_CONTROL));
							context.addCallbackParam("isValid", false);
							return;
						}
					}
			}
			else {
				// Test for existing Axe
				for (AxeDto _axe : listProgramAxes) {
					if(selectedAxe.getAxeNomenclatureId().equals(_axe.getAxeNomenclatureId()) && selectedAxe.getId()!=_axe.getId()) {
						CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
								.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_SAVE_AXE_EXISTING_CONTROL),
						CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_SAVE_AXE_EXISTING_CONTROL));
						context.addCallbackParam("isValid", false);
						return;
					}
				}
			}
			selectedAxe = serviceLocator.getRechercheProgrammeService().saveOneAxe(selectedAxe);
			listProgramAxes = serviceLocator.getRechercheProgrammeService().findAxesOfProgram(currentProgram.getId());
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
					.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_SAVE_AXE_SUCCESS),
					CommonMessagesUtils.getStringValueFromBundleResource(
									RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME,
									RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_SAVE_AXE_SUCCESS));
			context.addCallbackParam("isValid", true);
		}
	}
	public void prepareAddAxe() {
		
		selectedAxe = new AxeDto();
		if(listProgramAxes == null)
			listProgramAxes  = new ArrayList<AxeDto>();
		titleDetailsAxeProgram= CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME, 
				RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_PANEL_DETAILS_AXE_TITLE_ADD);
		if(listNcAxesRecherche == null) {
			initListNcAxes();
		}
	
	}
	public void prepareEditOneAxe() {
		
		if (selectedAxeId != null) {
			if(listNcAxesRecherche == null) {
				initListNcAxes();
			}
			toEditAxeDialog= true;
			titleDetailsAxeProgram= CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME, 
					RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_PANEL_DETAILS_AXE_TITLE_EDIT);
			selectedAxe = serviceLocator.getRechercheProgrammeService().findOneAxeById(selectedAxeId);
		}
	}
	public void removeOneAxe() {

		if(selectedAxeId== null)
				return;
			serviceLocator.getRechercheProgrammeService().removeOneAxe(selectedAxeId);
			listProgramAxes = serviceLocator.getRechercheProgrammeService().findAxesOfProgram(currentProgram.getId());
			
			// Suppression Axe reussie
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_REMOVE_AXE_SUCCESS),
					CommonMessagesUtils.getStringValueFromBundleResource(
							RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_REMOVE_AXE_SUCCESS));
	}
	public void removeOnePartner() {
	
	}
	public void prepareAddProgramPartners() {
		
		listProgramPartnersSelection = new ArrayList<RefPartenaireDto>();
		searchPartners();
	
	}
	private void searchPartners() {

//		if(sessionBean.getIdEtablissement() != null) {
//			listPartnairesSearch= serviceLocator.getRefPartenaireService().findPartenairesStructure(selectedStructureRecherche.getRefStructureId());
//			if(listPartnairesSearch != null && !listPartnairesSearch.isEmpty()) {
//				canAddPartners = true;
//			}
//			else {
//				canAddPartners = false;
//			}
//		}
	}

	// ===================================================================  
	// Listeners
	// ===================================================================
	public void onProgramRowSelect(SelectEvent event) {  
		
		if(selectedProgram != null) {
			// Ceci est pour ne pas faire un raffraichissement depuis la BDD quand clique sur la ligne deja selectionnee
			if(currentProgram == null || selectedProgram.getId()!=currentProgram.getId()) {
						canShowProgramDetails= true;
						toEditProgram = true;
						currentProgram = SerializationUtils.clone(selectedProgram);
						loadProgramDetail();
						titleDetailsProgram= CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_PROGRAMMES_RESSOURCE_MESSAGE_FILE_NAME, 
								RechercheConstantBean.RECHERCHE_PROGRAMME_RESSOURCE_MESSAGE_PANEL_DETAILS_TITLE_EDIT);
						
						if(listProgramsTypes == null) {
							initListTypePrograms();
						}
						
						if(listValidationProcess== null) {
							initListValidationProcess();
						}
					
			}
		}
	}
	private void loadProgramDetail() {

		listProgramAxes = serviceLocator.getRechercheProgrammeService().findAxesOfProgram(currentProgram.getId());
		canAddAxe = true;
	}

	/**
	 * [ProgrammeBB.initListNcAxes] Method 
	 * @author rlaib  on : 27 janv. 2015  15:15:03
	 */
	private void initListNcAxes() {
		
		List<NomenclatureDto> _listAxes = serviceLocator.getNomenclatureService().findByName(UtilConstant.NC_RCH_AXE_RECHERCHE);
		if(_listAxes != null) {
		listNcAxesRecherche=  new ArrayList<SelectItem>();
			for (NomenclatureDto _nomenclatureDto : _listAxes) {
				listNcAxesRecherche.add(new SelectItem(_nomenclatureDto.getId(),_nomenclatureDto.getLibelleLongFr()));
			}
		}
	}
	
}
