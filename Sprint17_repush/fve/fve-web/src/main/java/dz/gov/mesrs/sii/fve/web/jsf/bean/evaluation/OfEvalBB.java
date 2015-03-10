/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.of.OfBB.java] 
 * @author rlaib on : 6 mars 2014  11:21:23
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.evaluation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.ActionI18nDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.DemandeDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.DemandeI18nDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeRoleDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.TacheDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.TypeDemandeDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.ActionService;
import dz.gov.mesrs.sii.commons.business.service.bpm.DemandeService;
import dz.gov.mesrs.sii.commons.business.service.bpm.EtapeActionService;
import dz.gov.mesrs.sii.commons.business.service.bpm.EtapeService;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.business.service.bpm.TacheService;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.of.OfDataModel;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.of.OfSupportBean;

/**
 * @author rlaib  on : 6 mars 2014  11:21:23
 */

// ==========================================================================  
// 	Cette classe est un Managed Bean qui est chargé de la gestion des Offres de formation :
//
//  	1- Descriptif,
//		2- Parcours Type, 
//		3- la gestion d'une équipe offre (OF) de formation, 
//		4- Le partenariat, 
//		5- Le Complément d'information, 
//		6- L'Evaluation, 
//		7- La Mobilité,
//		8- La gestion de documents attachés à une offre de formation.
// ==========================================================================

@ManagedBean(name = "ofEvalBB")
@RequestScoped
public class OfEvalBB {

				// ===================================================================  
				// Constructors
				// ===================================================================
				
				public OfEvalBB() {
			
				}
			
				@SuppressWarnings("unchecked")
				@PostConstruct
				public void init() {
					
					 try{
							if (currentOfId != null && !currentOfId.trim().isEmpty() && !currentOfId.equals("null")) {
			 						// Chargementl objet offre formation 
			 						this.selectedOf  = offreFormationService.findById(Integer.parseInt(currentOfId));
							}
							if (currentDemandId != null && !currentDemandId.trim().isEmpty() && (Integer.parseInt(currentDemandId) > 0)) {
								
								List<DemandeI18nDto> list = demandeService.findListDemandsByTypeBySituaion(UtilConstants.TYPE_DEMANDE_EVALUATION_CODE,
					 													UtilConstants.DEMANDE_SITUTAION_CREEE_CODE,  Integer.parseInt(currentDemandId));
								
								this.demandeDtoI18ns = new HashMap<String, DemandeI18nDto>();
								
								for (DemandeI18nDto demandeI18nDto : list) {
									if(demandeI18nDto.getLangue().equals("fr"))
										this.demandeDtoI18ns .put("fr", demandeI18nDto);
									
								}
								
								 
		 						this.selectedDemand = demandeService.findById(Integer.parseInt(currentDemandId));
							}
							else  {
								
									DemandeI18nDto  demandeI18nDtoFr = new DemandeI18nDto();
									this.demandeDtoI18ns.put("fr", demandeI18nDtoFr);
									this.selectedDemand = new DemandeDto();
							}
					
							// Chargement des offres de formation disponibles pour soumission de demande d''Evaluation 
							initListAvailablesOf();
							initListDemands();
							// Recuperation de l'etape process en cours
							//EtapeActionDto etapeActionDto = etapeActionService.findById(14);
							//this.currentActionList = actionService.findListActioni18nByEtape(etapeActionDto);

					 }
					 catch(Exception e){
						 
						 	e.printStackTrace();
					 }
			
				}
				
				/**
				 * [OfEvalBB.initListAvailablesOf] Method 
				 * @author rlaib  on : 5 mai 2014  18:31:55
				 */
				private void initListAvailablesOf() {
					 		try{
					 					this.result = offreFormationService.findByCodeSituation(UtilConstants.OFFRE_FORMATION_SITUTAION_ENREGISTREE_DEFINITIVEMENT_CODE);
					 					lazyResult = new OfDataModel(result);
					 		}
					 		catch(Exception e){
					 
					 					e.printStackTrace();
					 		}
					
				}
				
				private void initListDemands() {
					try{
						
								// Liste des demandes a soumettre
					 			this.listDemandsToSubmit = demandeService.findListDemandsByTypeBySituaion(UtilConstants.TYPE_DEMANDE_EVALUATION_CODE,
					 													UtilConstants.DEMANDE_SITUTAION_CREEE_CODE, null);
					 			lazyListDemandsToSubmit = new DemandDataModel(this.listDemandsToSubmit);

								// Liste des demandes a valider
					 			this.listDemandsToValidate = demandeService.findListDemandsByTypeBySituaion(UtilConstants.TYPE_DEMANDE_EVALUATION_CODE,
					 					UtilConstants.DEMANDE_SITUTAION_SOUMISE_VALIDATION_CODE, null);
					 			lazyListDemandsToValidate = new DemandDataModel(this.listDemandsToValidate);
					 			
								// Liste de toutes demandes
					 			this.listAllDemands = demandeService.findListDemandsByTypeBySituaion(UtilConstants.TYPE_DEMANDE_EVALUATION_CODE,
					 					null, null);
					 			lazyListAllDemands = new DemandDataModel(this.listAllDemands);
					 			
					}
					catch(Exception e){
						
						e.printStackTrace();
					}
					
				}

				// ===================================================================  
				// Beans Services 
				// ===================================================================
				@ManagedProperty(value = "#{ofSupportBean}")
				private OfSupportBean ofSupportBean;
			
				@ManagedProperty(value="#{offreFormationService}")
				private OffreFormationService offreFormationService;
				
				@ManagedProperty(value="#{actionService}")
				private ActionService actionService;
				
				@ManagedProperty(value="#{etapeService}")
				private EtapeService etapeService;
			
				@ManagedProperty(value="#{situationService}")
				private SituationService situationService;
				
				@ManagedProperty(value="#{demandeService}")
				private DemandeService demandeService;
				
				@ManagedProperty(value="#{tacheService}")
				private TacheService tacheService;
				
				@ManagedProperty(value="#{etapeActionService}")
				private EtapeActionService etapeActionService;
								
				// ===================================================================  
				// Parameters
				// ===================================================================
				@ManagedProperty("#{param.ofId}")
				private String ofId;

				@ManagedProperty("#{param.outcomePage}")
				private String outcomePage;
				
				@ManagedProperty("#{param.currentDemandId}")
				private String currentDemandId;
				
				@ManagedProperty("#{param.currentOfId}")
				private String currentOfId;
				
				@ManagedProperty("#{param.currentMode}")
				private String currentMode;
				// ===================================================================  
				// Properties Model
				// ===================================================================
				private List<OffreFormationDto> result;
				private DataModel<OffreFormationDto> lazyResult;
				private OffreFormationDto selectedOf;
				private DemandeDto selectedDemand;
				private DemandeI18nDto selectedDemandI18n;
				private Map<String, DemandeI18nDto> demandeDtoI18ns = new HashMap<String, DemandeI18nDto>();
				private boolean showDemandDetails;

				private EtapeDto currentStep;
				private List<ActionI18nDto> currentActionList;
				private String selectAction;
				private List<DemandeI18nDto> listDemandsToSubmit;
				private DataModel<DemandeI18nDto> lazyListDemandsToSubmit;
				private List<DemandeI18nDto> listDemandsToValidate;
				private DataModel<DemandeI18nDto> lazyListDemandsToValidate;
				private List<DemandeI18nDto> listAllDemands;
				private DataModel<DemandeI18nDto> lazyListAllDemands;


				// ===================================================================  
				// Beans & Services Getters / Setters
				// ===================================================================
				/**
				 * [OfEvalBB.result] Getter 
				 * @author rlaib on : 4 mai 2014  13:34:56
				 * @return the result
				 */
				public List<OffreFormationDto> getResult() {
					return result;
				}

				/**
				 * [OfEvalBB.ofSupportBean] Getter 
				 * @author rlaib on : 4 mai 2014  13:35:53
				 * @return the ofSupportBean
				 */
				public OfSupportBean getOfSupportBean() {
					return ofSupportBean;
				}

				/**
				 * [OfEvalBB.ofSupportBean] Setter 
				 * @author rlaib on : 4 mai 2014  13:35:53
				 * @param ofSupportBean the ofSupportBean to set
				 */
				public void setOfSupportBean(OfSupportBean ofSupportBean) {
					this.ofSupportBean = ofSupportBean;
				}

				/**
				 * [OfEvalBB.offreFormationService] Getter 
				 * @author rlaib on : 4 mai 2014  13:35:53
				 * @return the offreFormationService
				 */
				public OffreFormationService getOffreFormationService() {
					return offreFormationService;
				}

				/**
				 * [OfEvalBB.offreFormationService] Setter 
				 * @author rlaib on : 4 mai 2014  13:35:53
				 * @param offreFormationService the offreFormationService to set
				 */
				public void setOffreFormationService(OffreFormationService offreFormationService) {
					this.offreFormationService = offreFormationService;
				}

				
				/**
				 * [OfEvalBB.actionService] Getter 
				 * @author rlaib on : 5 mai 2014  12:49:50
				 * @return the actionService
				 */
				public ActionService getActionService() {
					return actionService;
				}

				/**
				 * [OfEvalBB.actionService] Setter 
				 * @author rlaib on : 5 mai 2014  12:49:50
				 * @param actionService the actionService to set
				 */
				public void setActionService(ActionService actionService) {
					this.actionService = actionService;
				}

				/**
				 * [OfEvalBB.etapeService] Getter 
				 * @author rlaib on : 5 mai 2014  12:49:50
				 * @return the etapeService
				 */
				public EtapeService getEtapeService() {
					return etapeService;
				}

				/**
				 * [OfEvalBB.etapeService] Setter 
				 * @author rlaib on : 5 mai 2014  12:49:50
				 * @param etapeService the etapeService to set
				 */
				public void setEtapeService(EtapeService etapeService) {
					this.etapeService = etapeService;
				}
				
				
				/**
				 * [OfEvalBB.situationService] Getter 
				 * @author rlaib on : 5 mai 2014  16:11:59
				 * @return the situationService
				 */
				public SituationService getSituationService() {
					return situationService;
				}

				/**
				 * [OfEvalBB.situationService] Setter 
				 * @author rlaib on : 5 mai 2014  16:11:59
				 * @param situationService the situationService to set
				 */
				public void setSituationService(SituationService situationService) {
					this.situationService = situationService;
				}

				/**
				 * [OfEvalBB.demandeService] Getter 
				 * @author rlaib on : 5 mai 2014  16:11:59
				 * @return the demandeService
				 */
				public DemandeService getDemandeService() {
					return demandeService;
				}

				/**
				 * [OfEvalBB.demandeService] Setter 
				 * @author rlaib on : 5 mai 2014  16:11:59
				 * @param demandeService the demandeService to set
				 */
				public void setDemandeService(DemandeService demandeService) {
					this.demandeService = demandeService;
				}

				/**
				 * [OfEvalBB.tacheService] Getter 
				 * @author rlaib on : 6 mai 2014  15:26:14
				 * @return the tacheService
				 */
				public TacheService getTacheService() {
					return tacheService;
				}

				/**
				 * [OfEvalBB.tacheService] Setter 
				 * @author rlaib on : 6 mai 2014  15:26:14
				 * @param tacheService the tacheService to set
				 */
				public void setTacheService(TacheService tacheService) {
					this.tacheService = tacheService;
				}
				
				/**
				 * [OfEvalBB.etapeActionService] Getter 
				 * @author rlaib on : 6 mai 2014  17:21:21
				 * @return the etapeActionService
				 */
				public EtapeActionService getEtapeActionService() {
					return etapeActionService;
				}

				/**
				 * [OfEvalBB.etapeActionService] Setter 
				 * @author rlaib on : 6 mai 2014  17:21:21
				 * @param etapeActionService the etapeActionService to set
				 */
				public void setEtapeActionService(EtapeActionService etapeActionService) {
					this.etapeActionService = etapeActionService;
				}

				// ===================================================================  
				// Parameters Getters / Setters
				// ===================================================================
				/**
				 * [OfEvalBB.ofId] Getter 
				 * @author rlaib on : 4 mai 2014  13:35:18
				 * @return the ofId
				 */
				public String getOfId() {
					return ofId;
				}

				/**
				 * [OfEvalBB.ofId] Setter 
				 * @author rlaib on : 4 mai 2014  13:35:18
				 * @param ofId the ofId to set
				 */
				public void setOfId(String ofId) {
					this.ofId = ofId;
				}
				
				/**
				 * [OfEvalBB.outcomePage] Getter 
				 * @author rlaib on : 4 mai 2014  16:54:50
				 * @return the outcomePage
				 */
				public String getOutcomePage() {
					return outcomePage;
				}

				/**
				 * [OfEvalBB.outcomePage] Setter 
				 * @author rlaib on : 4 mai 2014  16:54:50
				 * @param outcomePage the outcomePage to set
				 */
				public void setOutcomePage(String outcomePage) {
					this.outcomePage = outcomePage;
				}
				
				/**
				 * [OfEvalBB.currentDemandId] Getter 
				 * @author rlaib on : 5 mai 2014  08:42:43
				 * @return the currentDemandId
				 */
				public String getCurrentDemandId() {
					return currentDemandId;
				}

				/**
				 * [OfEvalBB.currentDemandId] Setter 
				 * @author rlaib on : 5 mai 2014  08:42:43
				 * @param currentDemandId the currentDemandId to set
				 */
				public void setCurrentDemandId(String currentDemandId) {
					this.currentDemandId = currentDemandId;
				}

				/**
				 * [OfEvalBB.currentOfId] Getter 
				 * @author rlaib on : 5 mai 2014  08:43:55
				 * @return the currentOfId
				 */
				public String getCurrentOfId() {
					return currentOfId;
				}

				/**
				 * [OfEvalBB.currentOfId] Setter 
				 * @author rlaib on : 5 mai 2014  08:43:55
				 * @param currentOfId the currentOfId to set
				 */
				public void setCurrentOfId(String currentOfId) {
					this.currentOfId = currentOfId;
				}

				/**
				 * [OfEvalBB.currentMode] Getter 
				 * @author rlaib on : 6 mai 2014  16:52:14
				 * @return the currentMode
				 */
				public String getCurrentMode() {
					return currentMode;
				}

				/**
				 * [OfEvalBB.currentMode] Setter 
				 * @author rlaib on : 6 mai 2014  16:52:14
				 * @param currentMode the currentMode to set
				 */
				public void setCurrentMode(String currentMode) {
					this.currentMode = currentMode;
				}

				// ===================================================================  
				// Properties Model Getters / Setters
				// ===================================================================
				/**
				 * [OfEvalBB.result] Setter 
				 * @author rlaib on : 4 mai 2014  13:34:56
				 * @param result the result to set
				 */
				public void setResult(List<OffreFormationDto> result) {
					this.result = result;
				}

				/**
				 * [OfEvalBB.lazyResult] Getter 
				 * @author rlaib on : 4 mai 2014  13:34:56
				 * @return the lazyResult
				 */
				public DataModel<OffreFormationDto> getLazyResult() {
					return lazyResult;
				}

				/**
				 * [OfEvalBB.lazyResult] Setter 
				 * @author rlaib on : 4 mai 2014  13:34:56
				 * @param lazyResult the lazyResult to set
				 */
				public void setLazyResult(DataModel<OffreFormationDto> lazyResult) {
					this.lazyResult = lazyResult;
				}

				/**
				 * [OfEvalBB.selectedOf] Getter 
				 * @author rlaib on : 4 mai 2014  15:06:06
				 * @return the selectedOf
				 */
				public OffreFormationDto getSelectedOf() {
					return selectedOf;
				}

				/**
				 * [OfEvalBB.selectedOf] Setter 
				 * @author rlaib on : 4 mai 2014  15:06:06
				 * @param selectedOf the selectedOf to set
				 */
				public void setSelectedOf(OffreFormationDto selectedOf) {
					this.selectedOf = selectedOf;
				}
				
				
				/**
				 * [OfEvalBB.selectedDemand] Getter 
				 * @author rlaib on : 5 mai 2014  09:25:10
				 * @return the selectedDemand
				 */
				public DemandeDto getSelectedDemand() {
					
					if (selectedDemand == null)
						selectedDemand =  new DemandeDto();
					return selectedDemand;
				}

				/**
				 * [OfEvalBB.selectedDemand] Setter 
				 * @author rlaib on : 5 mai 2014  09:25:10
				 * @param selectedDemand the selectedDemand to set
				 */
				public void setSelectedDemand(DemandeDto selectedDemand) {
					this.selectedDemand = selectedDemand;
				}

				
				/**
				 * [OfEvalBB.selectedDemandI18n] Getter 
				 * @author rlaib on : 6 mai 2014  10:57:27
				 * @return the selectedDemandI18n
				 */
				public DemandeI18nDto getSelectedDemandI18n() {
					return selectedDemandI18n;
				}

				/**
				 * [OfEvalBB.selectedDemandI18n] Setter 
				 * @author rlaib on : 6 mai 2014  10:57:27
				 * @param selectedDemandI18n the selectedDemandI18n to set
				 */
				public void setSelectedDemandI18n(DemandeI18nDto selectedDemandI18n) {
					this.selectedDemandI18n = selectedDemandI18n;
				}

				/**
				 * [OfEvalBB.demandeDtoI18ns] Getter 
				 * @author rlaib on : 5 mai 2014  09:53:26
				 * @return the demandeDtoI18ns
				 */
				public Map<String, DemandeI18nDto> getDemandeDtoI18ns() {
					if(demandeDtoI18ns == null)
						this.demandeDtoI18ns = new HashMap<String, DemandeI18nDto>();
					return demandeDtoI18ns;
				}

				/**
				 * [OfEvalBB.demandeDtoI18ns] Setter 
				 * @author rlaib on : 5 mai 2014  09:53:26
				 * @param demandeDtoI18ns the demandeDtoI18ns to set
				 */
				public void setDemandeDtoI18ns(Map<String, DemandeI18nDto> demandeDtoI18ns) {
					this.demandeDtoI18ns = demandeDtoI18ns;
				}
				

				/**
				 * [OfEvalBB.showDemandDetails] Getter 
				 * @author rlaib on : 5 mai 2014  10:40:40
				 * @return the showDemandDetails
				 */
				public boolean isShowDemandDetails() {
					
					try {
						showDemandDetails = (currentDemandId != null && !currentDemandId.trim().isEmpty() && !currentDemandId.equals("null") && (Integer.parseInt(currentDemandId)>=0));
					}
					catch (Exception e) {
				
						showDemandDetails = false;
					}
					return showDemandDetails;
				}

				/**
				 * [OfEvalBB.showDemandDetails] Setter 
				 * @author rlaib on : 5 mai 2014  10:40:40
				 * @param showDemandDetails the showDemandDetails to set
				 */
				public void setShowDemandDetails(boolean showDemandDetails) {
					this.showDemandDetails = showDemandDetails;
				}

				
				/**
				 * [OfEvalBB.currentStep] Getter 
				 * @author rlaib on : 5 mai 2014  12:51:23
				 * @return the currentStep
				 */
				public EtapeDto getCurrentStep() {
					return currentStep;
				}

				/**
				 * [OfEvalBB.currentStep] Setter 
				 * @author rlaib on : 5 mai 2014  12:51:23
				 * @param currentStep the currentStep to set
				 */
				public void setCurrentStep(EtapeDto currentStep) {
					this.currentStep = currentStep;
				}

				/**
				 * [OfEvalBB.currentActionList] Getter 
				 * @author rlaib on : 5 mai 2014  12:51:23
				 * @return the currentActionList
				 */
				public List<ActionI18nDto> getCurrentActionList() {
					return currentActionList;
				}

				/**
				 * [OfEvalBB.currentActionList] Setter 
				 * @author rlaib on : 5 mai 2014  12:51:23
				 * @param currentActionList the currentActionList to set
				 */
				public void setCurrentActionList(List<ActionI18nDto> currentActionList) {
					this.currentActionList = currentActionList;
				}

				
				/**
				 * [OfEvalBB.selectAction] Getter 
				 * @author rlaib on : 5 mai 2014  13:42:43
				 * @return the selectAction
				 */
				public String getSelectAction() {
					if(selectAction == null)
						return "";
					return selectAction;
				}

				/**
				 * [OfEvalBB.selectAction] Setter 
				 * @author rlaib on : 5 mai 2014  13:42:43
				 * @param selectAction the selectAction to set
				 */
				public void setSelectAction(String selectAction) {
					this.selectAction = selectAction;
				}
				
				/**
				 * [OfEvalBB.listDemandsToSubmit] Getter 
				 * @author rlaib on : 6 mai 2014  09:26:30
				 * @return the listDemandsToSubmit
				 */
				public List<DemandeI18nDto> getListDemandsToSubmit() {
					return listDemandsToSubmit;
				}

				/**
				 * [OfEvalBB.listDemandsToSubmit] Setter 
				 * @author rlaib on : 6 mai 2014  09:26:30
				 * @param listDemandsToSubmit the listDemandsToSubmit to set
				 */
				public void setListDemandsToSubmit(List<DemandeI18nDto> listDemandsToSubmit) {
					this.listDemandsToSubmit = listDemandsToSubmit;
				}

				/**
				 * [OfEvalBB.lazyListDemandsToSubmit] Getter 
				 * @author rlaib on : 6 mai 2014  09:55:33
				 * @return the lazyListDemandsToSubmit
				 */
				public DataModel<DemandeI18nDto> getLazyListDemandsToSubmit() {
					return lazyListDemandsToSubmit;
				}

				/**
				 * [OfEvalBB.lazyListDemandsToSubmit] Setter 
				 * @author rlaib on : 6 mai 2014  09:55:33
				 * @param lazyListDemandsToSubmit the lazyListDemandsToSubmit to set
				 */
				public void setLazyListDemandsToSubmit(
						DataModel<DemandeI18nDto> lazyListDemandsToSubmit) {
					this.lazyListDemandsToSubmit = lazyListDemandsToSubmit;
				}

				/**
				 * [OfEvalBB.listDemandsToValidate] Getter 
				 * @author rlaib on : 6 mai 2014  16:56:17
				 * @return the listDemandsToValidate
				 */
				public List<DemandeI18nDto> getListDemandsToValidate() {
					return listDemandsToValidate;
				}

				/**
				 * [OfEvalBB.listDemandsToValidate] Setter 
				 * @author rlaib on : 6 mai 2014  16:56:17
				 * @param listDemandsToValidate the listDemandsToValidate to set
				 */
				public void setListDemandsToValidate(List<DemandeI18nDto> listDemandsToValidate) {
					this.listDemandsToValidate = listDemandsToValidate;
				}

				/**
				 * [OfEvalBB.lazyListDemandsToValidate] Getter 
				 * @author rlaib on : 6 mai 2014  16:56:17
				 * @return the lazyListDemandsToValidate
				 */
				public DataModel<DemandeI18nDto> getLazyListDemandsToValidate() {
					return lazyListDemandsToValidate;
				}

				/**
				 * [OfEvalBB.lazyListDemandsToValidate] Setter 
				 * @author rlaib on : 6 mai 2014  16:56:17
				 * @param lazyListDemandsToValidate the lazyListDemandsToValidate to set
				 */
				public void setLazyListDemandsToValidate(
						DataModel<DemandeI18nDto> lazyListDemandsToValidate) {
					this.lazyListDemandsToValidate = lazyListDemandsToValidate;
				}

				
				/**
				 * [OfEvalBB.listAllDemands] Getter 
				 * @author rlaib on : 12 mai 2014  16:07:57
				 * @return the listAllDemands
				 */
				public List<DemandeI18nDto> getListAllDemands() {
					return listAllDemands;
				}

				/**
				 * [OfEvalBB.listAllDemands] Setter 
				 * @author rlaib on : 12 mai 2014  16:07:57
				 * @param listAllDemands the listAllDemands to set
				 */
				public void setListAllDemands(List<DemandeI18nDto> listAllDemands) {
					this.listAllDemands = listAllDemands;
				}

				/**
				 * [OfEvalBB.lazyListAllDemands] Getter 
				 * @author rlaib on : 12 mai 2014  16:07:57
				 * @return the lazyListAllDemands
				 */
				public DataModel<DemandeI18nDto> getLazyListAllDemands() {
					return lazyListAllDemands;
				}

				/**
				 * [OfEvalBB.lazyListAllDemands] Setter 
				 * @author rlaib on : 12 mai 2014  16:07:57
				 * @param lazyListAllDemands the lazyListAllDemands to set
				 */
				public void setLazyListAllDemands(DataModel<DemandeI18nDto> lazyListAllDemands) {
					this.lazyListAllDemands = lazyListAllDemands;
				}

				// ===================================================================  
				// Listeners
				// ===================================================================
				 public void onOfRowSelect(SelectEvent event) {  
				    
					 	try {
						 		this.currentOfId = String.valueOf(selectedOf.getId());
						 		this.selectedDemand = new DemandeDto();
						 		this.currentDemandId = String.valueOf(selectedDemand.getId());
					 	}
					 	catch (Exception e) {
							
							e.printStackTrace();
						}
					 
				    }  
				 public void onDemandRowSelect(SelectEvent event) {  
					 
					 try {
						 		this.currentOfId = String.valueOf(selectedDemandI18n.getOffreFormationId());
						 		this.currentDemandId = String.valueOf(selectedDemandI18n.getDemandeId());
						 		this.demandeDtoI18ns = new HashMap<String, DemandeI18nDto>();
						 		this.demandeDtoI18ns.put("fr", this.selectedDemandI18n);
		 						this.selectedDemand = demandeService.findById(Integer.parseInt(currentDemandId));

		 						this.selectedOf  = offreFormationService.findById(Integer.parseInt(currentOfId));

					 }
					 catch (Exception e) {
						 
						 e.printStackTrace();
					 }
					 
				 }  
				 public void onTabChange(TabChangeEvent event) {
					 
//					 this.currentOfId = null;
//					 this.currentDemandId = null;

				    }
				// ===================================================================  
				// ActionListener
				// ===================================================================
				 public void saveDemand() {
						
						try {
									// Recuperer la situation de la demande a enregistrer
									SituationEntiteDto  seDemandeDto = situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_DEMANDE_CODE, UtilConstants.DEMANDE_SITUTAION_CREEE_CODE);
									// Recuperer le type de la demande
									TypeDemandeDto tdDto = demandeService.findTypeDemandeByCode(UtilConstants.TYPE_DEMANDE_EVALUATION_CODE);
									if((seDemandeDto != null) && (tdDto != null)) {
										
											this.selectedDemand.setIdSituation(seDemandeDto.getId());		
											this.selectedDemand.setTypeDemandeId(tdDto.getId());
											this.selectedDemand.setOffreFormationId(Integer.parseInt(currentOfId));
											this.selectedDemand = demandeService.saveOrUpdate(this.selectedDemand, new HashMap<>(this.demandeDtoI18ns));
											
											// Mise à jour de la situation de l'offre de formation
											SituationEntiteDto seOfDto = situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_OFFRE_FORMATION_CODE, UtilConstants.OFFRE_FORMATION_SITUTAION_DEMANDE_EN_INSTANCE_CODE);
											this.selectedOf.setIdSituation(seOfDto.getId());
											offreFormationService.insertOrUpdate(this.selectedOf, null);
											
											// Sauvegarde de la situation de l'occurrence de la  demande
											SituationEntiteOccurrenceDto situation = new SituationEntiteOccurrenceDto();
											situation.setIdOccurrence(this.selectedDemand.getId());
											situation.setIdSituation(seOfDto.getId());
											situation.setDateSituation(new java.util.Date());
											situationService.saveSituationOccurrence(situation);
											
											initListAvailablesOf();
											this.currentDemandId = this.currentOfId = null;
											
									}
									
							
						}
						catch (Exception e) {
							
							e.printStackTrace();
							
						} 
				}
				 
				 public void submitDemand() {
						
						try {
									
							// Recuperer la situation de la demande a enregistrer
							SituationEntiteDto  seDemandeDto = situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_DEMANDE_CODE, UtilConstants.DEMANDE_SITUTAION_SOUMISE_VALIDATION_CODE);
							// Recuperer le type de la demande
							TypeDemandeDto tdDto = demandeService.findTypeDemandeByCode(UtilConstants.TYPE_DEMANDE_EVALUATION_CODE);
					
							if((seDemandeDto != null) && (tdDto != null)) {
								
								this.selectedDemand.setIdSituation(seDemandeDto.getId());		
								this.selectedDemand.setTypeDemandeId(tdDto.getId());
								this.selectedDemand.setOffreFormationId(Integer.parseInt(currentOfId));
								this.selectedDemand = demandeService.saveOrUpdate(this.selectedDemand, new HashMap<>(this.demandeDtoI18ns));
								
								// Mise a� jour de la situation de l'offre de formation
								SituationEntiteDto seOfDto = situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_OFFRE_FORMATION_CODE, UtilConstants.OFFRE_FORMATION_SITUTAION_SOUMISE_HABILITATION_CODE);
								this.selectedOf.setIdSituation(seOfDto.getId());
								offreFormationService.insertOrUpdate(this.selectedOf, null);
								
								
								// Sauvegarde de la situation de l'occurrence de la  demande
								SituationEntiteOccurrenceDto situation = new SituationEntiteOccurrenceDto();
								situation.setIdOccurrence(this.selectedDemand.getId());
								situation.setIdSituation(seOfDto.getId());
								situation.setDateSituation(new java.util.Date());
								situationService.saveSituationOccurrence(situation);
								
								// Creation de la tache pour la prochaine etape
								
								// Etape Role de la tache
								EtapeRoleDto  etapeRoleDto = etapeService.findByCodeEtapeByCodeRole(UtilConstants.EVALUATION_ETAPE_VALIDATION_DEMANDE_CODE, UtilConstants.EVALUATION_ROLE_VALIDATION_DEMANDE_CODE_1 );
								// Situation de la tache
								SituationEntiteDto  seTacheDto = situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_EN_COURS_CODE);
								
								TacheDto tache = new TacheDto();
								tache.setEtapeRoleId(etapeRoleDto.getId());
								tache.setIdSituation(seTacheDto.getId());
								tache.setDateCreation(new java.util.Date());
								tache.setIdOffreFormation(Integer.parseInt(currentOfId));
								tache.setIdDemande(this.selectedDemand.getId());
								tacheService.insertOrUpdate(tache);
								
								initListAvailablesOf();
								this.currentDemandId = this.currentOfId = null;
								
						}
									
						}
						catch (Exception e) {
							
							e.printStackTrace();
							
						} 
				}
				 
				 public void validateDemand() {
					 
					 try {
						 
						// Recuperer la situation de la demande a enregistrer
							SituationEntiteDto  seDemandeDto = situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_DEMANDE_CODE, UtilConstants.DEMANDE_SITUTAION_VALIDEE_CODE);
							// Recuperer le type de la demande
							TypeDemandeDto tdDto = demandeService.findTypeDemandeByCode(UtilConstants.TYPE_DEMANDE_EVALUATION_CODE);
					
							if((seDemandeDto != null) && (tdDto != null)) {
								
								this.selectedDemand.setIdSituation(seDemandeDto.getId());		
								this.selectedDemand.setTypeDemandeId(tdDto.getId());
								this.selectedDemand.setOffreFormationId(Integer.parseInt(currentOfId));
								this.selectedDemand = demandeService.saveOrUpdate(this.selectedDemand, new HashMap<>(this.demandeDtoI18ns));
								
								// Mise a� jour de la situation de l'offre de formation
								SituationEntiteDto seOfDto = situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_OFFRE_FORMATION_CODE, UtilConstants.OFFRE_FORMATION_SITUTAION_SOUMISE_HABILITATION_CODE);
								this.selectedOf.setIdSituation(seOfDto.getId());
								this.selectedOf.setEstValidee(true);
								offreFormationService.insertOrUpdate(this.selectedOf, null);
								
								
								// Sauvegarde de la situation de l'occurrence de la  demande
								SituationEntiteOccurrenceDto situation = new SituationEntiteOccurrenceDto();
								situation.setIdOccurrence(this.selectedDemand.getId());
								situation.setIdSituation(seOfDto.getId());
								situation.setDateSituation(new java.util.Date());
								situationService.saveSituationOccurrence(situation);
								
								// Creation de la tache pour la prochaine etape
								
								// Etape Role de la tache
								//EtapeRoleDto  etapeRoleDto = etapeService.findByCodeEtapeByCodeRole(UtilConstants.EVALUATION_ETAPE_VALIDATION_DEMANDE_CODE, UtilConstants.EVALUATION_ROLE_VALIDATION_DEMANDE_CODE_1 );
								// Situation de la tache
//								SituationEntiteDto  seTacheDto = situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_TACHE_CODE, UtilConstants.TACHE_SITUTAION_EN_COURS_CODE);
//								
//								TacheDto tache = new TacheDto();
//								tache.setEtapeRoleId(etapeRoleDto.getId());
//								tache.setIdSituation(seTacheDto.getId());
//								tache.setDateCreation(new java.util.Date());
//								tache.setIdOffreFormation(Integer.parseInt(currentOfId));
//								tache.setIdDemande(this.selectedDemand.getId());
//								tacheService.insertOrUpdate(tache);
								
								initListAvailablesOf();
								initListDemands();
								this.currentDemandId = this.currentOfId = null;
							}
						 
					 }
					 catch (Exception e) {
						 
						 e.printStackTrace();
						 
					 } 
				 }
				
				
				// ===================================================================  
				// Actions and Methods
				// ===================================================================
					public String goOfDetail() {
						
						return "ofDetail";
					}
				
				// ===================================================================  
				// Functions Helper
				// ===================================================================
			

			    
}




