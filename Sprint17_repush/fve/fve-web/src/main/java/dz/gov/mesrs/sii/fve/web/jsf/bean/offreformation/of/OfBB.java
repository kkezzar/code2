/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.of.OfBB.java] 
 * @author rlaib on : 6 mars 2014  11:21:23
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.of;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UICommand;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;

import org.apache.commons.collections.ListUtils;
import org.primefaces.component.api.UIData;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationDetail;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationContratDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDetailContentDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationEquipeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationEquipeMembreDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationPartenaireDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationStructureDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.ParcoursTypeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.ParcoursTypeI18nDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RepartitionUeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.UniteEnseignementDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.CycleService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationContratService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationDetailContentService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationDetailService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationEquipeMembreService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationEquipeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationPartenaireService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.ParcoursTypeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RepartitionUeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.UniteEnseignementService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.MessageUtil;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.NcConverter;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPartenaireDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefContratService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPartenaireService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;

/**
 * @author rlaib  on : 6 mars 2014  11:21:23
 */

// ==========================================================================  
// 	Cette classe est un Managed Bean pour la Gestion des Offres de Formation :
//
//  	1- Descriptif,
//		2- Parcours Type (repartition UE)
//		3- Equipe Offre de formation, 
//		4- Partenariat, 
//		5- Le Complement d'information, 
//		6- L'Evaluation, 
//		7- La Mobilite,
//		8- La gestion de documents attaches a� une offre de formation.
// 	9- Historique de l'offre de formation
// ==========================================================================

@ManagedBean(name = "ofBB")
@ViewScoped
public class OfBB {

				// ===================================================================  
				// Constructors
				// ===================================================================
				
				// region CONSTRUCTORS
				public OfBB() {
			
				}
			
				@PostConstruct
				public void init() {
					
					 try{
						 	
						 	// Initialisation des parametres JSF
						 	initParams();
						 	// Initialisation des donnees Session
						 	initSessionInfos();
						 
 						 	// Test si l'objet offre de formation existe : 
						 	// Cas possibles :
						 	// 						- Edition depuis la page de recherche
						 	// 						- Consultation depuis la page de recherche
						 	// 						- Premiere creation (onglet descriptif)
					 		if (ofId != null && !ofId.trim().isEmpty() && !ofId.equals("null")) {
					 			
					 					// Chargement du detail objet offre formation 
					 					initOfDetails();
					 	
					 					// Mise a jour des valeurs de listes avec celles de l'objet
					 					selectedDomaineId = ofDto.getIdDomaine();
					 					selectedFiliereId = ofDto.getIdFiliere();
					 					selectedSpecialiteId = ofDto.getIdSpecialite();
					 					selectedCycleId = ofDto.getIdCycle();
					 					selectedTypeFormationId = ofDto.getIdTypeFormation();
					 					selectedVocationFormationId = ofDto.getIdVocation();
					 					if(ofDto.getIdStructureMere() != null) {
					 						selectedFaculteId = ofDto.getIdStructureMere();
					 						selectedDepartementId = ofDto.getIdStructure();
					 					}
					 					else {
					 						selectedFaculteId = ofDto.getIdStructure();
					 						ofDto.setLibelleStructureMere(ofDto.getLibelleStructure());
					 						ofDto.setLibelleStructure(null);
					 						selectedDepartementId = null;
					 					}
					 					// Test sur le mode d'edition du composant HXTML charge de la gestion d'un objet offre de formation
					 					// le mode d'edition peut etre :
					 					//					- "new" : mode creation d'une nouvelle offre de formation
					 					//					- "edit" : mode editon d'une offre de formation
					 					//					- "detail" : mode consultation d'une offre de formation
					 					
					 					if(editionMode != null) {
							 					switch (editionMode) {
												
							 								// Ceci est le mode Edition Offre de formation
										 					case OfConstants.OF_COMPONENT_EDIT_MODE:
																pageTitle = MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_PAGE_TITLE_EDIT) + "  : [ " + ofDto.getLibelleLongFr() + " ]" ;
																break;
																
															// Ceci est le mode Consultation Offre de formation	
										 					case OfConstants.OF_COMPONENT_DETAIL_MODE:
										 						pageTitle = MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_PAGE_TITLE_DETAIL) + "  : [ " + ofDto.getLibelleLongFr() + " ]" ;
										 						break;
					
															default:
																break;
												}
					 					}
					 					
					 					// Chargement des trees (arbres) avec leurs contenu pour l'objet offre de formation
					 					// Ceci concerne les onglets : Complement, Evaluation et Mobilite
										//processAllTreeByOfId(Integer.parseInt(ofId));
										
										// PARTENARIAT OF
										// Suite a la recherche de contrat via le web service et avoir clique sur ajouter le contrat, on recupere :
										// 		1. Le detail du contrat  par un web service dedie.
										// 		1. La liste des partenaires associes par un web service dedie.
										if (currentContractId != null && !currentContractId.trim().isEmpty() && !currentContractId.equals("null")) {
												selectedContract =  this.getContractByCode(currentContractId);
												selectedContractPartners = this.getPartnersByContractCode(currentContractId);
										}
										listOfPartners = offreFormationService.findPartnersByOfId(Integer.parseInt(ofId));
										
										// HISTORIQUE DES SITUATIONS
										this.ofHistory = situationService.getEntityOccurrenceHistory(UtilConstants.ENTITE_OFFRE_FORMATION_CODE, Integer.parseInt(ofId));
//										this.lazyOfHistory = new SituationDataModel(ofHistory);
					 		}
					 		else {
			 					this.editionMode = OfConstants.OF_COMPONENT_NEW_MODE;

					 			// Initialisation des trees (arbres) avec leurs contenu pour l'objet offre de formation
			 					// Ceci concerne les onglets : Complement, Evaluation et Mobilite (Mode creation).
					 			processAllNewTreesDetails();
							
					 			// Initialisation d'un nouvel objet offre de formation (OF) avec le titre de la page.
					 			this.ofDto = new OffreFormationDto();
					 			this.pageTitle = MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_PAGE_TITLE_NEW);
					 		}
							
						 	// Initialisation de l'ensemble des nomeclatures LMD via le web service Nomenclature WS.
						 	initLmdNomenclaturesListsFr();
					
						 	// Initialisation de la liste des domaines 
						 	initListDomainesLMD();
//						 	updateI18nLabels();
						 	
						 	// Initialisation de la repartition
						 	initOfRepartition();
						 	
						 	// Initialisation facultes/departement
						 		initOfFacultes(this.idEtab);
						 		initOfDepartements(this.idEtab, selectedFaculteId);
					
					 		// Test sur l'existence du parametre de recherche de membres (Individus via le service web)
					 		// dans la partie EQUIPE OF, pour recuperation du resultat de recherche afin de 
					 		// pouvoir paginer, trier la liste. Ceci est propre au mode RequestScope.
					 		if (keyWordsMember != null && !keyWordsMember.trim().equals("")) 
								searchMember();
					 		
					 		// Test sur l'existence du parametre de recherche de contrats (via le service web)
					 		// dans la partie PARTENARIAT OF, pour recuperation du  resultat de recherche
					 		// afin de  pouvoir paginer, trier la liste. Ceci est propre au mode RequestScope.
					 		if (keyWordsContract!= null && !keyWordsContract.trim().equals("")) 
					 			searchContract();
					 		
					 		//isSelectedPartner.setSelected(false);
					 }
					 catch(Exception e){
						 
						 e.printStackTrace();
					 
					 }
			
				}

				// endregion CONSTRUCTORS

				// ===================================================================  
				// Beans Services 
				// ===================================================================
				
				// region BEANS 

				@ManagedProperty(value="#{offreFormationService}")
				private OffreFormationService offreFormationService;
				
				@ManagedProperty(value="#{uniteEnseignementService}")
				private UniteEnseignementService uniteEnseignementService;
			
				@ManagedProperty(value="#{parcoursTypeService}")
				private ParcoursTypeService parcoursTypeService;
				
				@ManagedProperty(value="#{sessionBeanFve}")
				private SessionBean sessionBean;
			
				@ManagedProperty(value="#{nomenclatureConverter}")
				private NcConverter nomenclatureConverter;
				
				@ManagedProperty(value="#{offreFormationEquipeService}")
				private OffreFormationEquipeService offreFormationEquipeService;
				
				@ManagedProperty(value="#{offreFormationEquipeMembreService}")
				private OffreFormationEquipeMembreService offreFormationEquipeMembreService;

				@ManagedProperty(value="#{offreFormationDetailService}")
				private OffreFormationDetailService offreFormationDetailService;
				
				@ManagedProperty(value="#{offreFormationDetailContentService}")
				private OffreFormationDetailContentService offreFormationDetailContentService;
				
				@ManagedProperty(value="#{offreFormationContratService}")
				private OffreFormationContratService offreFormationContratService;
				
				@ManagedProperty(value="#{offreFormationPartenaireService}")
				private OffreFormationPartenaireService offreFormationPartenaireService;
			
				@ManagedProperty(value="#{situationService}")
				private SituationService situationService;
				
				@ManagedProperty(value="#{cycleService}")
				private CycleService cycleService;
				
				@ManagedProperty(value="#{niveauService}")
				private NiveauService niveauService;
				
				@ManagedProperty(value="#{periodeService}")
				private PeriodeService periodeService;
				
				@ManagedProperty(value="#{repartitionUeService}")
				private RepartitionUeService repartitionUeService;
				
					@ManagedProperty(value="#{refStructureServiceImpl}")
				private RefStructureService refStructureService;
				
				@ManagedProperty("#{nomenclatureServiceImpl}")
				private NomenclatureService nomenclatureService;

				@ManagedProperty("#{refIndividuServiceImpl}")
				private RefIndividuService refIndividuService;
				
				@ManagedProperty("#{refSpecialiteLmdServiceImpl}")
				private RefSpecialiteLmdService refSpecialiteLmdService;

				@ManagedProperty("#{refFiliereLmdServiceImpl}")
				private RefFiliereLmdService refFiliereLmdService;

				@ManagedProperty("#{refDomaineLMDServiceImpl}")
				private RefDomaineLMDService refDomaineLMDService;
				
				@ManagedProperty("#{refEtablissementServiceImpl}")
				private RefEtablissementService refEtablissementServiceImpl;
				
				@ManagedProperty("#{refParametreEtabServiceImpl}")
				private RefParametreEtabService refParametreEtabService;
				
				@ManagedProperty(value = "#{refContratServiceImpl}")
				private RefContratService refContratService;
				
				@ManagedProperty(value = "#{refPartenaireServiceImpl}")
				private RefPartenaireService refPartenaireService;
				
				// endregion BEANS
				
				// ===================================================================  
				// Parameters
				// ===================================================================
				
				// region PARAMS
				private String ofId;
				private String editionMode;
				private String outcomePage;
				private String currentContentComplement;
				private String currentContentEvaluation;
				private String currentContentMobilite;
				private String currentNodeComplement;
				private String currentNodeEvaluation;
				private String currentNodeMobilite;
				private String keyWordsMember;
				private Integer currentIndividuId;
				private Integer currentMemberId;
				private Integer currentTeamId;
				private String currentContractId;
				private String keyWordsContract;
				private String keyWords;
				private String searchMode;

				// endregion Parameters
				
				// ===================================================================  
				// Properties Model
				// ===================================================================
				
				// region PROPERTIES MODEL
				
				// Page
				private String pageTitle;
				
				// region properties Descriptif
				
				private List<SelectItem> listDomaines;
				private List<SelectItem> listFilieres;
				private List<SelectItem> listSpecialites;
				private List<SelectItem> listTypesFormation;
				private List<SelectItem> listCycles;
				private List<SelectItem> listVocations;
				private Integer selectedDomaineId;
				private Integer selectedFiliereId;
				private Integer selectedSpecialiteId;
				private Integer selectedTypeFormationId;
				private Integer selectedVocationFormationId;
				private Integer selectedCycleId;
				private boolean editableCycle;
				private boolean canSave;
				private CycleDto selectedCycleDto;
				private Integer selectedFaculteId;
				private Integer selectedDepartementId;
				
				// Session Infos
				private Integer idYear;
				private Integer idEtab;
				private String oldEtabCode;
				private String newEtabCode;
				private String libelleEtab;
				
				// Dto working Objects
				private OffreFormationDto ofDto;
				private OffreFormationDto ofDtoPrevious;
				private List<ParcoursTypeDto> parcoursTypes;
				private Map<String, ParcoursTypeI18nDto> parcoursDtoI18ns = new HashMap<String, ParcoursTypeI18nDto>();
				
				// endregion properties Descriptif
				
				// region properties Parcours
				private List<NiveauDto> listLevels;
				private Map<Integer, List<PeriodeDto>> periodesNiveaux;
				private Map<Integer, Map<Integer, List<RepartitionUeDto>>> repartitionsPeriodesNiveaux;
				private String selectedLevelId;
				private String selectedPeriodeId;
				private String selectedRepartitionId;
				private String selectedUeId;
				private String selectedParcoursId;
				private NiveauDto selectedLevel;
				private PeriodeDto selectedPeriod;
				private UniteEnseignementDto selectedUE;
				private RepartitionUeDto selectedRepartitionUe;
				private List<UniteEnseignementDto> availableUesPeriod;
				private Integer totalPeriodeCredits;
				private Map<Integer, Integer> totauxPeriodes;
				
				// endregion properties Descriptif
				
				// region properties Etablissement
				private List<SelectItem> etablissements;
				private List<SelectItem> facultes;
				private List<SelectItem> departements;
				private List<SelectItem> facultesOf;
				private List<SelectItem> departementsOf;
				private List<OffreFormationStructureDto> structuresOf;
				private OffreFormationStructureDto selectedStructureOf;
				private RefEtablissementDto selectedEtablissement;
				private RefStructureDto selectedFaculteOf;
				private RefStructureDto selectedDepartementOf;
			
				// endregion properties Etablissement
				
				// region properties Complement - Evaluation - Mobilite
				private List<OffreFormationDetailContentDto> listTreeComplement;
				private List<OffreFormationDetailContentDto> listTreeEvaluation;
				private List<OffreFormationDetailContentDto> listTreeMobilite;
			    private TreeNode rootComplement;
			    private TreeNode rootEvaluation;
			    private TreeNode rootMobilite;
			    private OffreFormationDetailContentDto selectedNodeComplement;
			    private OffreFormationDetailContentDto selectedNodeEvaluation;
			    private OffreFormationDetailContentDto selectedNodeMobilite;
			    private String editorContent;
				// endregion properties Complement - Evaluation - Mobilite
			    
			    // region properties Equipe
			    private List<SelectItem> listRoles;
			    private DataModel<RefIndividuDto> listSearchMembersLazy;
				private List<OffreFormationEquipeMembreDto> listOfMembers;
				private OffreFormationEquipeDto currentTeam;
				private OffreFormationEquipeMembreDto currentMember;
				private RefIndividuDto selectedIndividu;
				private String selectedRole;
				private String rowRoleColor;
				private boolean showTeamDetails;
			    // endregion properties Equipe
				
				 // region properties Partenariat
				private DataModel<RefContratDto> listSearchContractsLazy;
				private List<OffreFormationContratDto> listOfContracts;
				private List<OffreFormationPartenaireDto> listOfPartners;
				private RefContratDto selectedContract;
				private RefPartenaireDto selectedContractPartner;
				private List<RefPartenaireDto> selectedContractPartners;
				 // endregion properties Partenariat
				
				// Historique des situations
				private List<SituationEntiteOccurrenceDto> ofHistory;
				private DataModel<SituationEntiteOccurrenceDto> lazyOfHistory;
				
				// region Binding components
				private UICommand saveOfButton;
				private UIData membersDataTable;
				private UIData partnersDataTable;
				private UIData partnersOfDataTable;
				private SelectBooleanCheckbox isSelectedPartner;
				// endregion Binding components
				
				// endregion Properties Model
				
				// ===================================================================  
				// Beans Services  Getters / Setters
				// ===================================================================
				
				// region BEANS GETTERS / SETTERS
			
				/**
				 * [SearchOf.offreFormationService] Getter 
				 * @author Rafik on : 26 fevr. 2014  22:05:24
				 * @return the offreFormationService
				 */
				public OffreFormationService getOffreFormationService() {
					return offreFormationService;
				}
				/**
				 * [SearchOf.offreFormationService] Setter 
				 * @author Rafik on : 26 fevr. 2014  22:05:24
				 * @param offreFormationService the offreFormationService to set
				 */
				public void setOffreFormationService(OffreFormationService offreFormationService) {
					this.offreFormationService = offreFormationService;
				}
				
				
				/**
				 * [OfBB.refContratService] Getter 
				 * @author rlaib on : 20 nov. 2014  17:14:49
				 * @return the refContratService
				 */
				public RefContratService getRefContratService() {
					return refContratService;
				}

				/**
				 * [OfBB.refContratService] Setter 
				 * @author rlaib on : 20 nov. 2014  17:14:49
				 * @param refContratService the refContratService to set
				 */
				public void setRefContratService(RefContratService refContratService) {
					this.refContratService = refContratService;
				}

				/**
				 * [OfBB.refPartenaireService] Getter 
				 * @author rlaib on : 20 nov. 2014  17:14:49
				 * @return the refPartenaireService
				 */
				public RefPartenaireService getRefPartenaireService() {
					return refPartenaireService;
				}

				/**
				 * [OfBB.refPartenaireService] Setter 
				 * @author rlaib on : 20 nov. 2014  17:14:49
				 * @param refPartenaireService the refPartenaireService to set
				 */
				public void setRefPartenaireService(RefPartenaireService refPartenaireService) {
					this.refPartenaireService = refPartenaireService;
				}

				/**
				 * [OfBB.uniteEnseignementService] Getter 
				 * @author rlaib on : 12 sept. 2014  17:43:44
				 * @return the uniteEnseignementService
				 */
				public UniteEnseignementService getUniteEnseignementService() {
					return uniteEnseignementService;
				}

				/**
				 * [OfBB.uniteEnseignementService] Setter 
				 * @author rlaib on : 12 sept. 2014  17:43:44
				 * @param uniteEnseignementService the uniteEnseignementService to set
				 */
				public void setUniteEnseignementService(
						UniteEnseignementService uniteEnseignementService) {
					this.uniteEnseignementService = uniteEnseignementService;
				}

				/**
				 * [OfBB.parcoursTypeService] Getter 
				 * @author rlaib on : 12 sept. 2014  16:15:47
				 * @return the parcoursTypeService
				 */
				public ParcoursTypeService getParcoursTypeService() {
					return parcoursTypeService;
				}

				/**
				 * [OfBB.parcoursTypeService] Setter 
				 * @author rlaib on : 12 sept. 2014  16:15:47
				 * @param parcoursTypeService the parcoursTypeService to set
				 */
				public void setParcoursTypeService(ParcoursTypeService parcoursTypeService) {
					this.parcoursTypeService = parcoursTypeService;
				}

				/**
				 * [SearchOf.sessionBean] Getter 
				 * @author rlaib on : 4 mars 2014  10:21:44
				 * @return the sessionBean
				 */
				public SessionBean getSessionBean() {
					return sessionBean;
				}
				/**
				 * [SearchOf.sessionBean] Setter 
				 * @author rlaib on : 4 mars 2014  10:21:44
				 * @param sessionBean the sessionBean to set
				 */
				public void setSessionBean(SessionBean sessionBean) {
					this.sessionBean = sessionBean;
				}
				/**
				 * [OfBB.nomenclatureConverter] Getter 
				 * @author rlaib on : 10 mars 2014  10:57:59
				 * @return the nomenclatureConverter
				 */
				public NcConverter getNomenclatureConverter() {
					if (nomenclatureConverter == null)
						nomenclatureConverter = new NcConverter();
					return nomenclatureConverter;
				}
				/**
				 * [OfBB.nomenclatureConverter] Setter 
				 * @author rlaib on : 10 mars 2014  10:57:59
				 * @param nomenclatureConverter the nomenclatureConverter to set
				 */
				public void setNomenclatureConverter(NcConverter nomenclatureConverter) {
					this.nomenclatureConverter = nomenclatureConverter;
				}
				/**
				 * [OfBB.offreFormationEquipeService] Getter 
				 * @author Rafik on : 28 mars 2014  18:34:40
				 * @return the offreFormationEquipeService
				 */
				public OffreFormationEquipeService getOffreFormationEquipeService() {
					return offreFormationEquipeService;
				}
				/**
				 * [OfBB.offreFormationEquipeService] Setter 
				 * @author Rafik on : 28 mars 2014  18:34:40
				 * @param offreFormationEquipeService the offreFormationEquipeService to set
				 */
				public void setOffreFormationEquipeService(OffreFormationEquipeService offreFormationEquipeService) {
					this.offreFormationEquipeService = offreFormationEquipeService;
				}
				/**
				 * [OfBB.offreFormationEquipeMembreService] Getter 
				 * @author Rafik on : 28 mars 2014  18:34:40
				 * @return the offreFormationEquipeMembreService
				 */
				public OffreFormationEquipeMembreService getOffreFormationEquipeMembreService() {
					return offreFormationEquipeMembreService;
				}
				/**
				 * [OfBB.offreFormationEquipeMembreService] Setter 
				 * @author Rafik on : 28 mars 2014  18:34:40
				 * @param offreFormationEquipeMembreService the offreFormationEquipeMembreService to set
				 */
				public void setOffreFormationEquipeMembreService(OffreFormationEquipeMembreService offreFormationEquipeMembreService) {
					this.offreFormationEquipeMembreService = offreFormationEquipeMembreService;
				}
				/**
				 * [OfBB.offreFormationDetailService] Getter 
				 * @author rlaib on : 6 avr. 2014  17:49:31
				 * @return the offreFormationDetailService
				 */
				public OffreFormationDetailService getOffreFormationDetailService() {
					return offreFormationDetailService;
				}
				/**
				 * [OfBB.offreFormationDetailService] Setter 
				 * @author rlaib on : 6 avr. 2014  17:49:31
				 * @param offreFormationDetailService the offreFormationDetailService to set
				 */
				public void setOffreFormationDetailService(OffreFormationDetailService offreFormationDetailService) {
					this.offreFormationDetailService = offreFormationDetailService;
				}
				/**
				 * [OfBB.offreFormationDetailContentService] Getter 
				 * @author rlaib on : 6 avr. 2014  17:49:31
				 * @return the offreFormationDetailContentService
				 */
				public OffreFormationDetailContentService getOffreFormationDetailContentService() {
					return offreFormationDetailContentService;
				}
				/**
				 * [OfBB.offreFormationDetailContentService] Setter 
				 * @author rlaib on : 6 avr. 2014  17:49:31
				 * @param offreFormationDetailContentService the offreFormationDetailContentService to set
				 */
				public void setOffreFormationDetailContentService(OffreFormationDetailContentService offreFormationDetailContentService) {
					this.offreFormationDetailContentService = offreFormationDetailContentService;
				}
				/**
				 * [OfBB.offreFormationContratService] Getter 
				 * @author  Rafik LAIB on : 7 avr. 2014  22:07:01
				 * @return the offreFormationContratService
				 */
				public OffreFormationContratService getOffreFormationContratService() {
					return offreFormationContratService;
				}
				/**
				 * [OfBB.offreFormationContratService] Setter 
				 * @author  Rafik LAIB on : 7 avr. 2014  22:07:01
				 * @param offreFormationContratService the offreFormationContratService to set
				 */
				public void setOffreFormationContratService(OffreFormationContratService offreFormationContratService) {
					this.offreFormationContratService = offreFormationContratService;
				}
				/**
				 * [OfBB.offreFormationPartenaireService] Getter 
				 * @author  Rafik LAIB on : 7 avr. 2014  22:07:01
				 * @return the offreFormationPartenaireService
				 */
				public OffreFormationPartenaireService getOffreFormationPartenaireService() {
					return offreFormationPartenaireService;
				}
				/**
				 * [OfBB.offreFormationPartenaireService] Setter 
				 * @author  Rafik LAIB on : 7 avr. 2014  22:07:01
				 * @param offreFormationPartenaireService the offreFormationPartenaireService to set
				 */
				public void setOffreFormationPartenaireService(OffreFormationPartenaireService offreFormationPartenaireService) {
					this.offreFormationPartenaireService = offreFormationPartenaireService;
				}
				
				/**
				 * [OfBB.situationService] Getter 
				 * @author rlaib on : 5 mai 2014  18:39:21
				 * @return the situationService
				 */
				public SituationService getSituationService() {
					return situationService;
				}

				/**
				 * [OfBB.situationService] Setter 
				 * @author rlaib on : 5 mai 2014  18:39:21
				 * @param situationService the situationService to set
				 */
				public void setSituationService(SituationService situationService) {
					this.situationService = situationService;
				}
				
				/**
				 * [OfBB.cycleService] Getter 
				 * @author rlaib on : 12 ao�t 2014  17:55:58
				 * @return the cycleService
				 */
				public CycleService getCycleService() {
					return cycleService;
				}

				/**
				 * [OfBB.cycleService] Setter 
				 * @author rlaib on : 12 ao�t 2014  17:55:58
				 * @param cycleService the cycleService to set
				 */
				public void setCycleService(CycleService cycleService) {
					this.cycleService = cycleService;
				}

				/**
				 * [OfBB.niveauService] Getter 
				 * @author rlaib on : 11 sept. 2014  12:10:44
				 * @return the niveauService
				 */
				public NiveauService getNiveauService() {
					return niveauService;
				}

				/**
				 * [OfBB.niveauService] Setter 
				 * @author rlaib on : 11 sept. 2014  12:10:44
				 * @param niveauService the niveauService to set
				 */
				public void setNiveauService(NiveauService niveauService) {
					this.niveauService = niveauService;
				}
				
				/**
				 * [OfBB.periodeService] Getter 
				 * @author rlaib on : 11 sept. 2014  13:44:32
				 * @return the periodeService
				 */
				public PeriodeService getPeriodeService() {
					return periodeService;
				}

				/**
				 * [OfBB.periodeService] Setter 
				 * @author rlaib on : 11 sept. 2014  13:44:32
				 * @param periodeService the periodeService to set
				 */
				public void setPeriodeService(PeriodeService periodeService) {
					this.periodeService = periodeService;
				}
				
				/**
				 * [OfBB.repartitionUeService] Getter 
				 * @author rlaib on : 12 sept. 2014  15:53:21
				 * @return the repartitionUeService
				 */
				public RepartitionUeService getRepartitionUeService() {
					return repartitionUeService;
				}

				/**
				 * [OfBB.repartitionUeService] Setter 
				 * @author rlaib on : 12 sept. 2014  15:53:21
				 * @param repartitionUeService the repartitionUeService to set
				 */
				public void setRepartitionUeService(RepartitionUeService repartitionUeService) {
					this.repartitionUeService = repartitionUeService;
				}

				/**
				 * [OfBB.nomenclatureService] Getter 
				 * @author rlaib on : 1 oct. 2014  18:10:58
				 * @return the nomenclatureService
				 */
				public NomenclatureService getNomenclatureService() {
					return nomenclatureService;
				}

				/**
				 * [OfBB.nomenclatureService] Setter 
				 * @author rlaib on : 1 oct. 2014  18:10:58
				 * @param nomenclatureService the nomenclatureService to set
				 */
				public void setNomenclatureService(NomenclatureService nomenclatureService) {
					this.nomenclatureService = nomenclatureService;
				}
			
				/**
				 * [OfBB.refEtablissementServiceImpl] Getter 
				 * @author Rafik on : 27 nov. 2014  10:43:04
				 * @return the refEtablissementServiceImpl
				 */
				public RefEtablissementService getRefEtablissementServiceImpl() {
					return refEtablissementServiceImpl;
				}

				/**
				 * [OfBB.refEtablissementServiceImpl] Setter 
				 * @author Rafik on : 27 nov. 2014  10:43:04
				 * @param refEtablissementServiceImpl the refEtablissementServiceImpl to set
				 */
				public void setRefEtablissementServiceImpl(
						RefEtablissementService refEtablissementServiceImpl) {
					this.refEtablissementServiceImpl = refEtablissementServiceImpl;
				}

				/**
				 * [OfBB.refStructureService] Getter 
				 * @author rlaib on : 8 oct. 2014  18:09:49
				 * @return the refStructureService
				 */
				public RefStructureService getRefStructureService() {
					return refStructureService;
				}

				/**
				 * [OfBB.refStructureService] Setter 
				 * @author rlaib on : 8 oct. 2014  18:09:49
				 * @param refStructureService the refStructureService to set
				 */
				public void setRefStructureService(RefStructureService refStructureService) {
					this.refStructureService = refStructureService;
				}

				/**
				 * [OfBB.refIndividuService] Getter 
				 * @author rlaib on : 20 nov. 2014  17:12:51
				 * @return the refIndividuService
				 */
				public RefIndividuService getRefIndividuService() {
					return refIndividuService;
				}

				/**
				 * [OfBB.refIndividuService] Setter 
				 * @author rlaib on : 20 nov. 2014  17:12:51
				 * @param refIndividuService the refIndividuService to set
				 */
				public void setRefIndividuService(RefIndividuService refIndividuService) {
					this.refIndividuService = refIndividuService;
				}

				/**
				 * [OfBB.refSpecialiteLmdService] Getter 
				 * @author rlaib on : 20 nov. 2014  17:12:51
				 * @return the refSpecialiteLmdService
				 */
				public RefSpecialiteLmdService getRefSpecialiteLmdService() {
					return refSpecialiteLmdService;
				}

				/**
				 * [OfBB.refSpecialiteLmdService] Setter 
				 * @author rlaib on : 20 nov. 2014  17:12:51
				 * @param refSpecialiteLmdService the refSpecialiteLmdService to set
				 */
				public void setRefSpecialiteLmdService(
						RefSpecialiteLmdService refSpecialiteLmdService) {
					this.refSpecialiteLmdService = refSpecialiteLmdService;
				}

				/**
				 * [OfBB.refFiliereLmdService] Getter 
				 * @author rlaib on : 20 nov. 2014  17:12:51
				 * @return the refFiliereLmdService
				 */
				public RefFiliereLmdService getRefFiliereLmdService() {
					return refFiliereLmdService;
				}

				/**
				 * [OfBB.refFiliereLmdService] Setter 
				 * @author rlaib on : 20 nov. 2014  17:12:51
				 * @param refFiliereLmdService the refFiliereLmdService to set
				 */
				public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
					this.refFiliereLmdService = refFiliereLmdService;
				}

				/**
				 * [OfBB.refDomaineLMDService] Getter 
				 * @author rlaib on : 20 nov. 2014  17:12:51
				 * @return the refDomaineLMDService
				 */
				public RefDomaineLMDService getRefDomaineLMDService() {
					return refDomaineLMDService;
				}

				/**
				 * [OfBB.refDomaineLMDService] Setter 
				 * @author rlaib on : 20 nov. 2014  17:12:51
				 * @param refDomaineLMDService the refDomaineLMDService to set
				 */
				public void setRefDomaineLMDService(RefDomaineLMDService refDomaineLMDService) {
					this.refDomaineLMDService = refDomaineLMDService;
				}

				/**
				 * [OfBB.refParametreEtabService] Getter 
				 * @author rlaib on : 20 nov. 2014  17:12:51
				 * @return the refParametreEtabService
				 */
				public RefParametreEtabService getRefParametreEtabService() {
					return refParametreEtabService;
				}

				/**
				 * [OfBB.refParametreEtabService] Setter 
				 * @author rlaib on : 20 nov. 2014  17:12:51
				 * @param refParametreEtabService the refParametreEtabService to set
				 */
				public void setRefParametreEtabService(
						RefParametreEtabService refParametreEtabService) {
					this.refParametreEtabService = refParametreEtabService;
				}

				// endregion BEANS GETTERS / SETTERS
				
				// ===================================================================  
				// Parameters Getters / Setters
				// ===================================================================
	
				// region PARAMS GETTERS / SETTERS
				/**
				 * [SearchOf.ofId] Getter 
				 * @author rlaib on : 4 mars 2014  11:35:34
				 * @return the ofId
				 */
				public String getOfId() {
					return ofId;
				}
				/**
				 * [SearchOf.ofId] Setter 
				 * @author rlaib on : 4 mars 2014  11:35:34
				 * @param ofId the ofId to set
				 */
				public void setOfId(String ofId) {
					this.ofId = ofId;
				}
				/**
				 * [OfBB.editionMode] Getter 
				 * @author Rafik on : 6 mars 2014  21:45:55
				 * @return the editionMode
				 */
				public String getEditionMode() {
					return editionMode;
				}
				/**
				 * [OfBB.editionMode] Setter 
				 * @author Rafik on : 6 mars 2014  21:45:56
				 * @param editionMode the editionMode to set
				 */
				public void setEditionMode(String editionMode) {
					this.editionMode = editionMode;
				}
				/**
				 * [OfBB.outcomePage] Getter 
				 * @author Rafik on : 9 mars 2014  20:01:15
				 * @return the outcomePage
				 */
				public String getOutcomePage() {
					return outcomePage;
				}
				/**
				 * [OfBB.outcomePage] Setter 
				 * @author Rafik on : 9 mars 2014  20:01:15
				 * @param outcomePage the outcomePage to set
				 */
				public void setOutcomePage(String outcomePage) {
					this.outcomePage = outcomePage;
				}
				/**
				 * [OfBB.currentContent] Getter 
				 * @author rlaib on : 19 mars 2014  16:36:12
				 * @return the currentContent
				 */
				public String getCurrentContentComplement() {
					return currentContentComplement;
				}
				/**
				 * [OfBB.currentContent] Setter 
				 * @author rlaib on : 19 mars 2014  16:36:12
				 * @param currentContent the currentContent to set
				 */
				public void setCurrentContentComplement(String currentContent) {
					this.currentContentComplement = currentContent;
				}
				/**
				 * [OfBB.currentContentEvaluation] Getter 
				 * @author Rafik on : 22 mars 2014  22:39:31
				 * @return the currentContentEvaluation
				 */
				public String getCurrentContentEvaluation() {
					return currentContentEvaluation;
				}
				/**
				 * [OfBB.currentContentEvaluation] Setter 
				 * @author Rafik on : 22 mars 2014  22:39:31
				 * @param currentContentEvaluation the currentContentEvaluation to set
				 */
				public void setCurrentContentEvaluation(String currentContentEvaluation) {
					this.currentContentEvaluation = currentContentEvaluation;
				}
				/**
				 * [OfBB.currentContentMobilite] Getter 
				 * @author Rafik on : 22 mars 2014  23:04:00
				 * @return the currentContentMobilite
				 */
				public String getCurrentContentMobilite() {
					return currentContentMobilite;
				}
				/**
				 * [OfBB.currentContentMobilite] Setter 
				 * @author Rafik on : 22 mars 2014  23:04:00
				 * @param currentContentMobilite the currentContentMobilite to set
				 */
				public void setCurrentContentMobilite(String currentContentMobilite) {
					this.currentContentMobilite = currentContentMobilite;
				}
				/**
				 * [OfBB.currentNode] Getter 
				 * @author Rafik on : 19 mars 2014  21:33:49
				 * @return the currentNode
				 */
				public String getCurrentNodeComplement() {
					return currentNodeComplement;
				}
				/**
				 * [OfBB.currentNode] Setter 
				 * @author Rafik on : 19 mars 2014  21:33:49
				 * @param currentNode the currentNode to set
				 */
				public void setCurrentNodeComplement(String currentNode) {
					this.currentNodeComplement = currentNode;
				}
				/**
				 * [OfBB.currentNodeEvaluation] Getter 
				 * @author Rafik on : 22 mars 2014  22:36:12
				 * @return the currentNodeEvaluation
				 */
				public String getCurrentNodeEvaluation() {
					return currentNodeEvaluation;
				}
				/**
				 * [OfBB.currentNodeEvaluation] Setter 
				 * @author Rafik on : 22 mars 2014  22:36:12
				 * @param currentNodeEvaluation the currentNodeEvaluation to set
				 */
				public void setCurrentNodeEvaluation(String currentNodeEvaluation) {
					this.currentNodeEvaluation = currentNodeEvaluation;
				}
				/**
				 * [OfBB.currentNodeMobilite] Getter 
				 * @author Rafik on : 22 mars 2014  23:04:35
				 * @return the currentNodeMobilite
				 */
				public String getCurrentNodeMobilite() {
					return currentNodeMobilite;
				}
				/**
				 * [OfBB.currentNodeMobilite] Setter 
				 * @author Rafik on : 22 mars 2014  23:04:35
				 * @param currentNodeMobilite the currentNodeMobilite to set
				 */
				public void setCurrentNodeMobilite(String currentNodeMobilite) {
					this.currentNodeMobilite = currentNodeMobilite;
				}
				/**
				 * [OfBackingBean.pageTitle] Getter 
				 * @author rlaib on : 16 f�vr. 2014  15:03:29
				 * @return the pageTitle
				 */
				public String getPageTitle() {
					return pageTitle;
				}
				/**
				 * [OfBackingBean.pageTitle] Setter 
				 * @author rlaib on : 16 f�vr. 2014  15:03:29
				 * @param pageTitle the pageTitle to set
				 */
				public void setPageTitle(String pageTitle) {
					this.pageTitle = pageTitle;
				}
				/**
				 * [OfEquipeBB.keyWordsMember] Getter 
				 * @author rlaib on : 27 mars 2014  10:57:37
				 * @return the keyWordsMember
				 */
				public String getKeyWordsMember() {
					
					if (keyWordsMember == null)
						keyWordsMember = "";
					return keyWordsMember;
				}
				/**
				 * [OfEquipeBB.keyWordsMember] Setter 
				 * @author rlaib on : 27 mars 2014  10:57:37
				 * @param keyWordsMember the keyWordsMember to set
				 */
				public void setKeyWordsMember(String keyWordsMember) {
					this.keyWordsMember = keyWordsMember;
				}
				/**
				 * [OfBB.currentIndividuId] Getter 
				 * @author Rafik on : 28 mars 2014  18:16:05
				 * @return the currentIndividuId
				 */
				public Integer getCurrentIndividuId() {
					return currentIndividuId;
				}
				/**
				 * [OfBB.currentIndividuId] Setter 
				 * @author Rafik on : 28 mars 2014  18:16:05
				 * @param currentIndividuId the currentIndividuId to set
				 */
				public void setCurrentIndividuId(Integer currentIndividuId) {
					this.currentIndividuId = currentIndividuId;
				}
				/**
				 * [OfBB.currentMemberId] Getter 
				 * @author Rafik on : 28 mars 2014  18:16:05
				 * @return the currentMemberId
				 */
				public Integer getCurrentMemberId() {
					return currentMemberId;
				}
				/**
				 * [OfBB.currentMemberId] Setter 
				 * @author Rafik on : 28 mars 2014  18:16:05
				 * @param currentMemberId the currentMemberId to set
				 */
				public void setCurrentMemberId(Integer currentMemberId) {
					this.currentMemberId = currentMemberId;
				}
				/**
				 * [OfBB.currentTeamId] Getter 
				 * @author Rafik on : 28 mars 2014  19:34:46
				 * @return the currentTeamId
				 */
				public Integer getCurrentTeamId() {
					return currentTeamId;
				}
				/**
				 * [OfBB.currentTeamId] Setter 
				 * @author Rafik on : 28 mars 2014  19:34:46
				 * @param currentTeamId the currentTeamId to set
				 */
				public void setCurrentTeamId(Integer currentTeamId) {
					this.currentTeamId = currentTeamId;
				}
				/**
				 * [OfBB.selectedContractId] Getter 
				 * @author rlaib on : 7 avr. 2014  10:13:17
				 * @return the currentContractId
				 */
				public String getCurrentContractId() {
					return currentContractId;
				}
				/**
				 * [OfBB.currentContractId] Setter 
				 * @author rlaib on : 7 avr. 2014  10:13:17
				 * @param currentContractId the currentContractId to set
				 */
				public void setCurrentContractId(String currentContractId) {
					this.currentContractId = currentContractId;
				}
				/**
				 * [OfBB.selectedContractPartners] Getter 
				 * @author rlaib on : 7 avr. 2014  11:47:16
				 * @return the selectedContractPartners
				 */
				public List<RefPartenaireDto> getSelectedContractPartners() {
					return selectedContractPartners;
				}
				/**
				 * [OfBB.selectedContractPartners] Setter 
				 * @author rlaib on : 7 avr. 2014  11:47:16
				 * @param selectedContractPartners the selectedContractPartners to set
				 */
				public void setSelectedContractPartners(
						List<RefPartenaireDto> selectedContractPartners) {
					this.selectedContractPartners = selectedContractPartners;
				}
				/**
				 * [OfBB.ofHistory] Getter 
				 * @author rlaib on : 13 mai 2014  11:35:20
				 * @return the ofHistory
				 */
				public List<SituationEntiteOccurrenceDto> getOfHistory() {
					if(ofHistory == null)
						ofHistory = new ArrayList<SituationEntiteOccurrenceDto>();
					return ofHistory;
				}
				/**
				 * [OfBB.ofHistory] Setter 
				 * @author rlaib on : 13 mai 2014  11:35:20
				 * @param ofHistory the ofHistory to set
				 */
				public void setOfHistory(List<SituationEntiteOccurrenceDto> ofHistory) {
					this.ofHistory = ofHistory;
				}
				/**
				 * [OfBB.lazyOfHistory] Getter 
				 * @author rlaib on : 13 mai 2014  16:36:17
				 * @return the lazyOfHistory
				 */
				public DataModel<SituationEntiteOccurrenceDto> getLazyOfHistory() {
					return lazyOfHistory;
				}
				/**
				 * [OfBB.lazyOfHistory] Setter 
				 * @author rlaib on : 13 mai 2014  16:36:17
				 * @param lazyOfHistory the lazyOfHistory to set
				 */
				public void setLazyOfHistory(DataModel<SituationEntiteOccurrenceDto> lazyOfHistory) {
					this.lazyOfHistory = lazyOfHistory;
				}
				/**
				 * [OfBB.keyWordsContract] Getter 
				 * @author rlaib on : 7 avr. 2014  10:16:07
				 * @return the keyWordsContract
				 */
				public String getKeyWordsContract() {
					return keyWordsContract;
				}
				/**
				 * [OfBB.keyWordsContract] Setter 
				 * @author rlaib on : 7 avr. 2014  10:16:07
				 * @param keyWordsContract the keyWordsContract to set
				 */
				public void setKeyWordsContract(String keyWordsContract) {
					this.keyWordsContract = keyWordsContract;
				}
				/**
				 * [OfBB.keyWords] Getter 
				 * @author rlaib on : 4 mai 2014  16:16:19
				 * @return the keyWords
				 */
				public String getKeyWords() {
					return keyWords;
				}
				/**
				 * [OfBB.keyWords] Setter 
				 * @author rlaib on : 4 mai 2014  16:16:19
				 * @param keyWords the keyWords to set
				 */
				public void setKeyWords(String keyWords) {
					this.keyWords = keyWords;
				}
				/**
				 * [OfBB.searchMode] Getter 
				 * @author rlaib on : 4 mai 2014  16:35:42
				 * @return the searchMode
				 */
				public String getSearchMode() {
					return searchMode;
				}
				/**
				 * [OfBB.searchMode] Setter 
				 * @author rlaib on : 4 mai 2014  16:35:42
				 * @param searchMode the searchMode to set
				 */
				public void setSearchMode(String searchMode) {
					this.searchMode = searchMode;
				}
				// endregion PARAMS GETTERS / SETTERS
				
				// ===================================================================  
				// Properties Model Getters / Setters
				// ===================================================================
				
				// region PROPERTIES MODEL GETTERS/SETTERS
				/**
				 * [OfBB.editorContent] Getter 
				 * @author rlaib on : 17 mars 2014  16:35:45
				 * @return the editorContent
				 */
				public String getEditorContent() {
					return editorContent;
				}
				/**
				 * [OfBB.editorContent] Setter 
				 * @author rlaib on : 17 mars 2014  16:35:45
				 * @param editorContent the editorContent to set
				 */
				public void setEditorContent(String editorContent) {
					this.editorContent = editorContent;
				}
				/**
				 * [SearchOf.listDomaines] Getter 
				 * @author rlaib on : 27 fevr. 2014  17:48:07
				 * @return the listDomaines
				 */
				public List<SelectItem> getListDomaines() {
					return listDomaines;
				}
				/**
				 * [SearchOf.listDomaines] Setter 
				 * @author rlaib on : 27 fevr. 2014  17:48:07
				 * @param listDomaines the listDomaines to set
				 */
				public void setListDomaines(List<SelectItem> listDomaines) {
					this.listDomaines = listDomaines;
				}
				/**
				 * [SearchOf.listFilieres] Getter 
				 * @author rlaib on : 27 fevr. 2014  11:28:45
				 * @return the listFilieres
				 */
				public List<SelectItem> getListFilieres() {
					
					return listFilieres;
				}
				/**
				 * [SearchOf.listFilieres] Setter 
				 * @author rlaib on : 27 fevr. 2014  11:28:45
				 * @param listFilieres the listFilieres to set
				 */
				public void setListFilieres(List<SelectItem> listFilieres) {
					this.listFilieres = listFilieres;
				}
				/**
				 * [SearchOf.listSpecialites] Getter 
				 * @author rlaib on : 27 fevr. 2014  11:05:54
				 * @return the listSpecialites
				 */
				public List<SelectItem> getListSpecialites() {
					
					return listSpecialites;
				}
				/**
				 * [SearchOf.listSpecialites] Setter 
				 * @author rlaib on : 27 fevr. 2014  11:05:54
				 * @param listSpecialites the listSpecialites to set
				 */
				public void setListSpecialites(List<SelectItem> listSpecialites) {
					this.listSpecialites = listSpecialites;
				}
				/**
				 * [OfBB.listTypesFormation] Getter 
				 * @author rlaib on : 6 mars 2014  15:32:08
				 * @return the listTypesFormation
				 */
				public List<SelectItem> getListTypesFormation() {
					
					return listTypesFormation;
				}
				/**
				 * [OfBB.listTypesFormation] Setter 
				 * @author rlaib on : 6 mars 2014  15:32:08
				 * @param listTypesFormation the listTypesFormation to set
				 */
				public void setListTypesFormation(List<SelectItem> listTypesFormation) {
					this.listTypesFormation = listTypesFormation;
				}
				/**
				 * [OfBB.listCycles] Getter 
				 * @author rlaib on : 6 mars 2014  15:32:08
				 * @return the listCycles
				 */
				public List<SelectItem> getListCycles() {
					return listCycles;
				}
				/**
				 * [OfBB.listCycles] Setter 
				 * @author rlaib on : 6 mars 2014  15:32:08
				 * @param listCycles the listCycles to set
				 */
				public void setListCycles(List<SelectItem> listCycles) {
					this.listCycles = listCycles;
				}
				/**
				 * [OfBB.listVocations] Getter 
				 * @author rlaib on : 6 mars 2014  15:32:08
				 * @return the listVocations
				 */
				public List<SelectItem> getListVocations() {
					
					return listVocations;
				}
				/**
				 * [OfBB.listVocations] Setter 
				 * @author rlaib on : 6 mars 2014  15:32:08
				 * @param listVocations the listVocations to set
				 */
				public void setListVocations(List<SelectItem> listVocations) {
					this.listVocations = listVocations;
				}
				
				/**
				 * [OfBB.selectedTypeFormationId] Getter 
				 * @author rlaib on : 2 d�c. 2014  11:10:07
				 * @return the selectedTypeFormationId
				 */
				public Integer getSelectedTypeFormationId() {
					return selectedTypeFormationId;
				}

				/**
				 * [OfBB.selectedTypeFormationId] Setter 
				 * @author rlaib on : 2 d�c. 2014  11:10:07
				 * @param selectedTypeFormationId the selectedTypeFormationId to set
				 */
				public void setSelectedTypeFormationId(Integer selectedTypeFormationId) {
					this.selectedTypeFormationId = selectedTypeFormationId;
				}

				/**
				 * [OfBB.selectedVocationFormationId] Getter 
				 * @author rlaib on : 2 d�c. 2014  11:10:07
				 * @return the selectedVocationFormationId
				 */
				public Integer getSelectedVocationFormationId() {
					return selectedVocationFormationId;
				}

				/**
				 * [OfBB.selectedVocationFormationId] Setter 
				 * @author rlaib on : 2 d�c. 2014  11:10:07
				 * @param selectedVocationFormationId the selectedVocationFormationId to set
				 */
				public void setSelectedVocationFormationId(Integer selectedVocationFormationId) {
					this.selectedVocationFormationId = selectedVocationFormationId;
				}

				/**
				 * [OfBB.selectedDomaineId] Getter 
				 * @author rlaib on : 1 d�c. 2014  09:08:20
				 * @return the selectedDomaineId
				 */
				public Integer getSelectedDomaineId() {
					return selectedDomaineId;
				}
				/**
				 * [OfBB.selectedDomaineId] Setter 
				 * @author rlaib on : 1 d�c. 2014  09:08:20
				 * @param selectedDomaineId the selectedDomaineId to set
				 */
				public void setSelectedDomaineId(Integer selectedDomaineId) {
					this.selectedDomaineId = selectedDomaineId;
				}

				/**
				 * [OfBB.editableCycle] Getter 
				 * @author rlaib on : 14 sept. 2014  13:31:00
				 * @return the editableCycle
				 */
				public boolean getEditableCycle() {
					return editableCycle;
				}

				/**
				 * [OfBB.editableCycle] Setter 
				 * @author rlaib on : 14 sept. 2014  13:31:00
				 * @param editableCycle the editableCycle to set
				 */
				public void setEditableCycle(boolean editableCycle) {
					this.editableCycle = editableCycle;
				}
				
				/**
				 * [OfBB.canSave] Getter 
				 * @author rlaib on : 2 d�c. 2014  12:36:13
				 * @return the canSave
				 */
				public boolean isCanSave() {
					return canSave;
				}

				/**
				 * [OfBB.canSave] Setter 
				 * @author rlaib on : 2 d�c. 2014  12:36:13
				 * @param canSave the canSave to set
				 */
				public void setCanSave(boolean canSave) {
					this.canSave = canSave;
				}

				/**
				 * [OfBB.selectedCycleDto] Getter 
				 * @author rlaib on : 21 sept. 2014  16:08:15
				 * @return the selectedCycleDto
				 */
				public CycleDto getSelectedCycleDto() {
					return selectedCycleDto;
				}

				/**
				 * [OfBB.selectedCycleDto] Setter 
				 * @author rlaib on : 21 sept. 2014  16:08:15
				 * @param selectedCycleDto the selectedCycleDto to set
				 */
				public void setSelectedCycleDto(CycleDto selectedCycleDto) {
					this.selectedCycleDto = selectedCycleDto;
				}

				/**
				 * [OfBB.selectedFaculteId] Getter 
				 * @author Rafik on : 5 déc. 2014  21:38:59
				 * @return the selectedFaculteId
				 */
				public Integer getSelectedFaculteId() {
					return selectedFaculteId;
				}

				/**
				 * [OfBB.selectedFaculteId] Setter 
				 * @author Rafik on : 5 déc. 2014  21:38:59
				 * @param selectedFaculteId the selectedFaculteId to set
				 */
				public void setSelectedFaculteId(Integer selectedFaculteId) {
					this.selectedFaculteId = selectedFaculteId;
				}

				/**
				 * [OfBB.selectedDepartementId] Getter 
				 * @author Rafik on : 5 déc. 2014  21:38:59
				 * @return the selectedDepartementId
				 */
				public Integer getSelectedDepartementId() {
					return selectedDepartementId;
				}

				/**
				 * [OfBB.selectedDepartementId] Setter 
				 * @author Rafik on : 5 déc. 2014  21:38:59
				 * @param selectedDepartementId the selectedDepartementId to set
				 */
				public void setSelectedDepartementId(Integer selectedDepartementId) {
					this.selectedDepartementId = selectedDepartementId;
				}

				/**
				 * [OfBB.idYear] Getter 
				 * @author rlaib on : 14 ao�t 2014  16:59:12
				 * @return the idYear
				 */
				public Integer getIdYear() {
					return idYear;
				}

				/**
				 * [OfBB.idYear] Setter 
				 * @author rlaib on : 14 ao�t 2014  16:59:12
				 * @param idYear the idYear to set
				 */
				public void setIdYear(Integer idYear) {
					this.idYear = idYear;
				}

				/**
				 * [OfBB.idEtab] Getter 
				 * @author rlaib on : 14 ao�t 2014  16:59:12
				 * @return the idEtab
				 */
				public Integer getIdEtab() {
					return idEtab;
				}

				/**
				 * [OfBB.idEtab] Setter 
				 * @author rlaib on : 14 ao�t 2014  16:59:12
				 * @param idEtab the idEtab to set
				 */
				public void setIdEtab(Integer idEtab) {
					this.idEtab = idEtab;
				}

				/**
				 * [OfBB.oldEtabCode] Getter 
				 * @author rlaib on : 14 ao�t 2014  16:59:12
				 * @return the oldEtabCode
				 */
				public String getOldEtabCode() {
					return oldEtabCode;
				}

				/**
				 * [OfBB.oldEtabCode] Setter 
				 * @author rlaib on : 14 ao�t 2014  16:59:12
				 * @param oldEtabCode the oldEtabCode to set
				 */
				public void setOldEtabCode(String oldEtabCode) {
					this.oldEtabCode = oldEtabCode;
				}

				/**
				 * [OfBB.newEtabCode] Getter 
				 * @author rlaib on : 14 ao�t 2014  16:59:12
				 * @return the newEtabCode
				 */
				public String getNewEtabCode() {
					return newEtabCode;
				}

				/**
				 * [OfBB.newEtabCode] Setter 
				 * @author rlaib on : 14 ao�t 2014  16:59:12
				 * @param newEtabCode the newEtabCode to set
				 */
				public void setNewEtabCode(String newEtabCode) {
					this.newEtabCode = newEtabCode;
				}

				/**
				 * [OfBB.libelleEtab] Getter 
				 * @author rlaib on : 14 ao�t 2014  16:59:12
				 * @return the libelleEtab
				 */
				public String getLibelleEtab() {
					return libelleEtab;
				}

				/**
				 * [OfBB.libelleEtab] Setter 
				 * @author rlaib on : 14 ao�t 2014  16:59:12
				 * @param libelleEtab the libelleEtab to set
				 */
				public void setLibelleEtab(String libelleEtab) {
					this.libelleEtab = libelleEtab;
				}

				/**
				 * [OfBackingBean.ofDto] Getter 
				 * @author rlaib on : 2/02/ 2014  12:30:10
				 * @return the ofDto
				 */
				public OffreFormationDto getOfDto() {
					if (ofDto == null)
						ofDto = new OffreFormationDto();
					
					return ofDto;
				}
				/**
				 * [OfBackingBean.ofDto] Setter 
				 * @author rlaib on : 2/02/ 2014  12:30:10
				 * @param ofDto the ofDto to set
				 */
				public void setOfDto(OffreFormationDto ofDto) {
					this.ofDto = ofDto;
				}
				
				/**
				 * [OfBB.ofDtoPrevious] Getter 
				 * @author rlaib on : 2 d�c. 2014  12:37:36
				 * @return the ofDtoPrevious
				 */
				public OffreFormationDto getOfDtoPrevious() {
					return ofDtoPrevious;
				}

				/**
				 * [OfBB.ofDtoPrevious] Setter 
				 * @author rlaib on : 2 d�c. 2014  12:37:36
				 * @param ofDtoPrevious the ofDtoPrevious to set
				 */
				public void setOfDtoPrevious(OffreFormationDto ofDtoPrevious) {
					this.ofDtoPrevious = ofDtoPrevious;
				}

				/**
				 * [OfBB.parcoursTypes] Getter 
				 * @author rlaib on : 12 sept. 2014  16:13:17
				 * @return the parcoursTypes
				 */
				public List<ParcoursTypeDto> getParcoursTypes() {
					return parcoursTypes;
				}

				/**
				 * [OfBB.parcoursTypes] Setter 
				 * @author rlaib on : 12 sept. 2014  16:13:17
				 * @param parcoursTypes the parcoursTypes to set
				 */
				public void setParcoursTypes(List<ParcoursTypeDto> parcoursTypes) {
					this.parcoursTypes = parcoursTypes;
				}

				/**
				 * [OfBB.parcoursDtoI18ns] Getter 
				 * @author rlaib on : 1 oct. 2014  17:01:06
				 * @return the parcoursDtoI18ns
				 */
				public Map<String, ParcoursTypeI18nDto> getParcoursDtoI18ns() {
					return parcoursDtoI18ns;
				}

				/**
				 * [OfBB.parcoursDtoI18ns] Setter 
				 * @author rlaib on : 1 oct. 2014  17:01:06
				 * @param parcoursDtoI18ns the parcoursDtoI18ns to set
				 */
				public void setParcoursDtoI18ns(
						Map<String, ParcoursTypeI18nDto> parcoursDtoI18ns) {
					this.parcoursDtoI18ns = parcoursDtoI18ns;
				}

				/**
				 * [OfBB.listLevels] Getter 
				 * @author rlaib on : 11 sept. 2014  12:09:19
				 * @return the listLevels
				 */
				public List<NiveauDto> getListLevels() {
					return listLevels;
				}

				/**
				 * [OfBB.listLevels] Setter 
				 * @author rlaib on : 11 sept. 2014  12:09:19
				 * @param listLevels the listLevels to set
				 */
				public void setListLevels(List<NiveauDto> listLevels) {
					this.listLevels = listLevels;
				}
				
				/**
				 * [OfBB.periodesNiveaux] Getter 
				 * @author rlaib on : 11 sept. 2014  13:41:43
				 * @return the periodesNiveaux
				 */
				public Map<Integer, List<PeriodeDto>> getPeriodesNiveaux() {
					return periodesNiveaux;
				}

				/**
				 * [OfBB.periodesNiveaux] Setter 
				 * @author rlaib on : 11 sept. 2014  13:41:43
				 * @param periodesNiveaux the periodesNiveaux to set
				 */
				public void setPeriodesNiveaux(Map<Integer, List<PeriodeDto>> periodesNiveaux) {
					this.periodesNiveaux = periodesNiveaux;
				}
				
				/**
				 * [OfBB.repartitionsPeriodesNiveaux] Getter 
				 * @author rlaib on : 12 sept. 2014  15:39:18
				 * @return the repartitionsPeriodesNiveaux
				 */
				public Map<Integer, Map<Integer, List<RepartitionUeDto>>> getRepartitionsPeriodesNiveaux() {
					return repartitionsPeriodesNiveaux;
				}

				/**
				 * [OfBB.repartitionsPeriodesNiveaux] Setter 
				 * @author rlaib on : 12 sept. 2014  15:39:18
				 * @param repartitionsPeriodesNiveaux the repartitionsPeriodesNiveaux to set
				 */
				public void setRepartitionsPeriodesNiveaux(
						Map<Integer, Map<Integer, List<RepartitionUeDto>>> repartitionsPeriodesNiveaux) {
					this.repartitionsPeriodesNiveaux = repartitionsPeriodesNiveaux;
				}

				/**
				 * [OfBB.selectedLevelId] Getter 
				 * @author rlaib on : 11 sept. 2014  14:05:02
				 * @return the selectedLevelId
				 */
				public String getSelectedLevelId() {
					return selectedLevelId;
				}

				/**
				 * [OfBB.selectedLevelId] Setter 
				 * @author rlaib on : 11 sept. 2014  14:05:02
				 * @param selectedLevelId the selectedLevelId to set
				 */
				public void setSelectedLevelId(String selectedLevelId) {
					this.selectedLevelId = selectedLevelId;
				}

				/**
				 * [OfBB.selectedPeriodeId] Getter 
				 * @author rlaib on : 11 sept. 2014  14:05:02
				 * @return the selectedPeriodeId
				 */
				public String getSelectedPeriodeId() {
					return selectedPeriodeId;
				}

				/**
				 * [OfBB.selectedPeriodeId] Setter 
				 * @author rlaib on : 11 sept. 2014  14:05:02
				 * @param selectedPeriodeId the selectedPeriodeId to set
				 */
				public void setSelectedPeriodeId(String selectedPeriodeId) {
					this.selectedPeriodeId = selectedPeriodeId;
				}
				
				/**
				 * [OfBB.selectedRepartitionId] Getter 
				 * @author rlaib on : 12 sept. 2014  16:54:23
				 * @return the selectedRepartitionId
				 */
				public String getSelectedRepartitionId() {
					return selectedRepartitionId;
				}

				/**
				 * [OfBB.selectedRepartitionId] Setter 
				 * @author rlaib on : 12 sept. 2014  16:54:23
				 * @param selectedRepartitionId the selectedRepartitionId to set
				 */
				public void setSelectedRepartitionId(String selectedRepartitionId) {
					this.selectedRepartitionId = selectedRepartitionId;
				}

				/**
				 * [OfBB.selectedUeId] Getter 
				 * @author rlaib on : 12 sept. 2014  17:46:11
				 * @return the selectedUeId
				 */
				public String getSelectedUeId() {
					return selectedUeId;
				}

				/**
				 * [OfBB.selectedUeId] Setter 
				 * @author rlaib on : 12 sept. 2014  17:46:11
				 * @param selectedUeId the selectedUeId to set
				 */
				public void setSelectedUeId(String selectedUeId) {
					this.selectedUeId = selectedUeId;
				}

				/**
				 * [OfBB.selectedParcoursId] Getter 
				 * @author rlaib on : 12 sept. 2014  17:34:57
				 * @return the selectedParcoursId
				 */
				public String getSelectedParcoursId() {
					return selectedParcoursId;
				}

				/**
				 * [OfBB.selectedParcoursId] Setter 
				 * @author rlaib on : 12 sept. 2014  17:34:57
				 * @param selectedParcoursId the selectedParcoursId to set
				 */
				public void setSelectedParcoursId(String selectedParcoursId) {
					this.selectedParcoursId = selectedParcoursId;
				}

				/**
				 * [OfBB.listTreeComplement] Getter 
				 * @author Rafik on : 11 mars 2014  21:40:58
				 * @return the listTreeComplement
				 */
				
				public List<OffreFormationDetailContentDto> getListTreeComplement() {
					return listTreeComplement;
				}
				/**
				 * [OfBB.selectedLevel] Getter 
				 * @author rlaib on : 12 sept. 2014  13:39:03
				 * @return the selectedLevel
				 */
				public NiveauDto getSelectedLevel() {
					return selectedLevel;
				}

				/**
				 * [OfBB.selectedLevel] Setter 
				 * @author rlaib on : 12 sept. 2014  13:39:03
				 * @param selectedLevel the selectedLevel to set
				 */
				public void setSelectedLevel(NiveauDto selectedLevel) {
					this.selectedLevel = selectedLevel;
				}

				/**
				 * [OfBB.selectedperiod] Getter 
				 * @author rlaib on : 12 sept. 2014  13:39:03
				 * @return the selectedPeriod
				 */
				public PeriodeDto getSelectedPeriod() {
					return selectedPeriod;
				}
				
				/**
				 * [OfBB.selectedUE] Getter 
				 * @author rlaib on : 17 sept. 2014  11:58:20
				 * @return the selectedUE
				 */
				public UniteEnseignementDto getSelectedUE() {
					return selectedUE;
				}

				/**
				 * [OfBB.selectedUE] Setter 
				 * @author rlaib on : 17 sept. 2014  11:58:20
				 * @param selectedUE the selectedUE to set
				 */
				public void setSelectedUE(UniteEnseignementDto selectedUE) {
					this.selectedUE = selectedUE;
				}

				/**
				 * [OfBB.selectedRepartitionUe] Getter 
				 * @author rlaib on : 12 sept. 2014  15:28:10
				 * @return the selectedRepartitionUe
				 */
				public RepartitionUeDto getSelectedRepartitionUe() {
					return selectedRepartitionUe;
				}

				/**
				 * [OfBB.selectedRepartitionUe] Setter 
				 * @author rlaib on : 12 sept. 2014  15:28:10
				 * @param selectedRepartitionUe the selectedRepartitionUe to set
				 */
				public void setSelectedRepartitionUe(RepartitionUeDto selectedRepartitionUe) {
					this.selectedRepartitionUe = selectedRepartitionUe;
				}
				
				/**
				 * [OfBB.availableUesPeriod] Getter 
				 * @author rlaib on : 12 sept. 2014  17:27:37
				 * @return the availableUesPeriod
				 */
				public List<UniteEnseignementDto> getAvailableUesPeriod() {
					return availableUesPeriod;
				}

				/**
				 * [OfBB.availableUesPeriod] Setter 
				 * @author rlaib on : 12 sept. 2014  17:27:37
				 * @param availableUesPeriod the availableUesPeriod to set
				 */
				public void setAvailableUesPeriod(List<UniteEnseignementDto> availableUesPeriod) {
					this.availableUesPeriod = availableUesPeriod;
				}

				/**
				 * [OfBB.totalPeriodeCredits] Getter 
				 * @author rlaib on : 13 sept. 2014  12:10:06
				 * @return the totalPeriodeCredits
				 */
				public Integer getTotalPeriodeCredits() {
					return totalPeriodeCredits;
				}

				/**
				 * [OfBB.totalPeriodeCredits] Setter 
				 * @author rlaib on : 13 sept. 2014  12:10:06
				 * @param totalPeriodeCredits the totalPeriodeCredits to set
				 */
				public void setTotalPeriodeCredits(Integer totalPeriodeCredits) {
					this.totalPeriodeCredits = totalPeriodeCredits;
				}

				/**
				 * [OfBB.totauxPeriodes] Getter 
				 * @author rlaib on : 17 sept. 2014  11:27:45
				 * @return the totauxPeriodes
				 */
				public Map<Integer, Integer> getTotauxPeriodes() {
					return totauxPeriodes;
				}

				/**
				 * [OfBB.totauxPeriodes] Setter 
				 * @author rlaib on : 17 sept. 2014  11:27:45
				 * @param totauxPeriodes the totauxPeriodes to set
				 */
				public void setTotauxPeriodes(Map<Integer, Integer> totauxPeriodes) {
					this.totauxPeriodes = totauxPeriodes;
				}

				/**
				 * [OfBB.etablissements] Getter 
				 * @author rlaib on : 8 oct. 2014  18:33:57
				 * @return the etablissements
				 */
				public List<SelectItem> getEtablissements() {
					return etablissements;
				}

				/**
				 * [OfBB.etablissements] Setter 
				 * @author rlaib on : 8 oct. 2014  18:33:57
				 * @param etablissements the etablissements to set
				 */
				public void setEtablissements(List<SelectItem> etablissements) {
					this.etablissements = etablissements;
				}

				/**
				 * [OfBB.facultes] Getter 
				 * @author rlaib on : 8 oct. 2014  18:33:57
				 * @return the facultes
				 */
				public List<SelectItem> getFacultes() {
					return facultes;
				}

				/**
				 * [OfBB.facultes] Setter 
				 * @author rlaib on : 8 oct. 2014  18:33:57
				 * @param facultes the facultes to set
				 */
				public void setFacultes(List<SelectItem> facultes) {
					this.facultes = facultes;
				}

				/**
				 * [OfBB.departements] Getter 
				 * @author rlaib on : 8 oct. 2014  18:33:57
				 * @return the departements
				 */
				public List<SelectItem> getDepartements() {
					return departements;
				}

				/**
				 * [OfBB.departements] Setter 
				 * @author rlaib on : 8 oct. 2014  18:33:57
				 * @param departements the departements to set
				 */
				public void setDepartements(List<SelectItem> departements) {
					this.departements = departements;
				}

				/**
				 * [OfBB.structuresOf] Getter 
				 * @author rlaib on : 8 oct. 2014  18:33:57
				 * @return the structuresOf
				 */
				public List<OffreFormationStructureDto> getStructuresOf() {
					return structuresOf;
				}

				/**
				 * [OfBB.structuresOf] Setter 
				 * @author rlaib on : 8 oct. 2014  18:33:57
				 * @param structuresOf the structuresOf to set
				 */
				public void setStructuresOf(List<OffreFormationStructureDto> structuresOf) {
					this.structuresOf = structuresOf;
				}
			
				/**
				 * [OfBB.selectedEtablissement] Getter 
				 * @author rlaib on : 9 oct. 2014  09:27:38
				 * @return the selectedEtablissement
				 */
				public RefEtablissementDto getSelectedEtablissement() {
					return selectedEtablissement;
				}

				/**
				 * [OfBB.selectedEtablissement] Setter 
				 * @author rlaib on : 9 oct. 2014  09:27:38
				 * @param selectedEtablissement the selectedEtablissement to set
				 */
				public void setSelectedEtablissement(RefEtablissementDto selectedEtablissement) {
					this.selectedEtablissement = selectedEtablissement;
				}
			
				/**
				 * [OfBB.facultesOf] Getter 
				 * @author rlaib on : 9 oct. 2014  14:51:51
				 * @return the facultesOf
				 */
				public List<SelectItem> getFacultesOf() {
					return facultesOf;
				}

				/**
				 * [OfBB.facultesOf] Setter 
				 * @author rlaib on : 9 oct. 2014  14:51:51
				 * @param facultesOf the facultesOf to set
				 */
				public void setFacultesOf(List<SelectItem> facultesOf) {
					this.facultesOf = facultesOf;
				}

				/**
				 * [OfBB.departementsOf] Getter 
				 * @author rlaib on : 9 oct. 2014  14:51:51
				 * @return the departementsOf
				 */
				public List<SelectItem> getDepartementsOf() {
					return departementsOf;
				}

				/**
				 * [OfBB.departementsOf] Setter 
				 * @author rlaib on : 9 oct. 2014  14:51:51
				 * @param departementsOf the departementsOf to set
				 */
				public void setDepartementsOf(List<SelectItem> departementsOf) {
					this.departementsOf = departementsOf;
				}

				/**
				 * [OfBB.selectedStructureOf] Getter 
				 * @author rlaib on : 9 oct. 2014  14:51:51
				 * @return the selectedStructureOf
				 */
				public OffreFormationStructureDto getSelectedStructureOf() {
					
					if(selectedStructureOf == null)
						selectedStructureOf = new OffreFormationStructureDto();
					return selectedStructureOf;
				}

				/**
				 * [OfBB.selectedStructureOf] Setter 
				 * @author rlaib on : 9 oct. 2014  14:51:51
				 * @param selectedStructureOf the selectedStructureOf to set
				 */
				public void setSelectedStructureOf(
						OffreFormationStructureDto selectedStructureOf) {
					this.selectedStructureOf = selectedStructureOf;
				}

				/**
				 * [OfBB.selectedFaculteOf] Getter 
				 * @author rlaib on : 9 oct. 2014  14:51:51
				 * @return the selectedFaculteOf
				 */
				public RefStructureDto getSelectedFaculteOf() {
					
					if(selectedFaculteOf == null)
						selectedFaculteOf = new RefStructureDto();
					return selectedFaculteOf;
				}

				/**
				 * [OfBB.selectedFaculteOf] Setter 
				 * @author rlaib on : 9 oct. 2014  14:51:51
				 * @param selectedFaculteOf the selectedFaculteOf to set
				 */
				public void setSelectedFaculteOf(RefStructureDto selectedFaculteOf) {
					this.selectedFaculteOf = selectedFaculteOf;
				}

				/**
				 * [OfBB.selectedDepartementOf] Getter 
				 * @author rlaib on : 9 oct. 2014  14:51:51
				 * @return the selectedDepartementOf
				 */
				public RefStructureDto getSelectedDepartementOf() {
					
					if(selectedDepartementOf == null)
						selectedDepartementOf = new RefStructureDto();
					return selectedDepartementOf;
				}

				/**
				 * [OfBB.selectedDepartementOf] Setter 
				 * @author rlaib on : 9 oct. 2014  14:51:51
				 * @param selectedDepartementOf the selectedDepartementOf to set
				 */
				public void setSelectedDepartementOf(RefStructureDto selectedDepartementOf) {
					this.selectedDepartementOf = selectedDepartementOf;
				}

				/**
				 * [OfBB.selectedperiod] Setter 
				 * @author rlaib on : 12 sept. 2014  13:39:03
				 * @param selectedperiod the selectedPeriod to set
				 */
				public void setSelectedPeriod(PeriodeDto selectedPeriod) {
					this.selectedPeriod = selectedPeriod;
				}

				/**
				 * [OfBB.listTreeComplement] Setter 
				 * @author Rafik on : 11 mars 2014  21:40:58
				 * @param listTreeComplement the listTreeComplement to set
				 */
				public void setListTreeComplement(List<OffreFormationDetailContentDto> listTreeComplement) {
					this.listTreeComplement = listTreeComplement;
				}
				/**
				 * [OfBB.listTreeEvaluation] Getter 
				 * @author Rafik on : 11 mars 2014  21:40:58
				 * @return the listTreeEvaluation
				 */
				public List<OffreFormationDetailContentDto> getListTreeEvaluation() {
					return listTreeEvaluation;
				}
				/**
				 * [OfBB.listTreeEvaluation] Setter 
				 * @author Rafik on : 11 mars 2014  21:40:58
				 * @param listTreeEvaluation the listTreeEvaluation to set
				 */
				public void setListTreeEvaluation(List<OffreFormationDetailContentDto> listTreeEvaluation) {
					this.listTreeEvaluation = listTreeEvaluation;
				}
				/**
				 * [OfBB.listTreeMobilite] Getter 
				 * @author Rafik on : 11 mars 2014  21:40:58
				 * @return the listTreeMobilite
				 */
				public List<OffreFormationDetailContentDto> getListTreeMobilite() {
					return listTreeMobilite;
				}
				/**
				 * [OfBB.listTreeMobilite] Setter 
				 * @author Rafik on : 11 mars 2014  21:40:58
				 * @param listTreeMobilite the listTreeMobilite to set
				 */
				public void setListTreeMobilite(List<OffreFormationDetailContentDto> listTreeMobilite) {
					this.listTreeMobilite = listTreeMobilite;
				}
				/**
				 * [OfBB.rootComplement] Getter 
				 * @author Rafik on : 11 mars 2014  21:38:07
				 * @return the rootComplement
				 */
				public TreeNode getRootComplement() {
					
						return rootComplement;
				}
				/**
				 * [OfBB.rootComplement] Setter 
				 * @author Rafik on : 11 mars 2014  21:38:07
				 * @param rootComplement the rootComplement to set
				 */
				public void setRootComplement(TreeNode rootComplement) {
					this.rootComplement = rootComplement;
				}
				/**
				 * [OfBB.rootEvaluation] Getter 
				 * @author Rafik on : 11 mars 2014  21:38:07
				 * @return the rootEvaluation
				 */
				public TreeNode getRootEvaluation() {
					
//					if(rootEvaluation == null){
//						
//						listTreeEvaluation =  ofSupportBean.getTreesOfDetailsDataSources().get(new Integer(2));
//						listEvaluation = new ArrayList<OffreFormationDetailContent>();
//						for (OffreFormationDetail item : listTreeEvaluation) {
//							
//							OffreFormationDetailContent itemDetail =  new OffreFormationDetailContent();
//							itemDetail.setOffreFormationDetail(item);
//							listEvaluation.add(itemDetail);
//							
//				  	    }
//						rootEvaluation = processTree(listEvaluation);
//					}
					return rootEvaluation;
				}
				/**
				 * [OfBB.rootEvaluation] Setter 
				 * @author Rafik on : 11 mars 2014  21:38:07
				 * @param rootEvaluation the rootEvaluation to set
				 */
				public void setRootEvaluation(TreeNode rootEvaluation) {
					this.rootEvaluation = rootEvaluation;
				}
				/**
				 * [OfBB.rootMobilite] Getter 
				 * @author Rafik on : 11 mars 2014  21:38:07
				 * @return the rootMobilite
				 */
				public TreeNode getRootMobilite() {
					
//					if(rootMobilite == null) {
//						
//						listTreeMobilite =  ofSupportBean.getTreesOfDetailsDataSources().get(new Integer(3));
//						listMobilite = new ArrayList<OffreFormationDetailContent>();
//						
//						for (OffreFormationDetail item : listTreeMobilite) {
//							
//							OffreFormationDetailContent itemDetail =  new OffreFormationDetailContent();
//							itemDetail.setOffreFormationDetail(item);
//							listMobilite.add(itemDetail);
//							
//							
//							
//				  	    }
//						rootMobilite = processTree(listMobilite);
//					}
					return rootMobilite;
				}
				/**
				 * [OfBB.rootMobilite] Setter 
				 * @author Rafik on : 11 mars 2014  21:38:07
				 * @param rootMobilite the rootMobilite to set
				 */
				public void setRootMobilite(TreeNode rootMobilite) {
					this.rootMobilite = rootMobilite;
				}
				/**
				 * [OfBB.selectedNodeComplement] Getter 
				 * @author rlaib on : 20 mars 2014  15:40:41
				 * @return the selectedNodeComplement
				 */
				public OffreFormationDetailContentDto getSelectedNodeComplement() {
					return selectedNodeComplement;
				}
				/**
				 * [OfBB.selectedNodeComplement] Setter 
				 * @author rlaib on : 20 mars 2014  15:40:41
				 * @param selectedNodeComplement the selectedNodeComplement to set
				 */
				public void setSelectedNodeComplement(OffreFormationDetailContentDto selectedNodeComplement) {
					this.selectedNodeComplement = selectedNodeComplement;
				}
				/**
				 * [OfBB.selectedNodeEvaluation] Getter 
				 * @author Rafik on : 11 mars 2014  21:38:07
				 * @return the selectedNodeEvaluation
				 */
				public OffreFormationDetailContentDto getSelectedNodeEvaluation() {
					
					return selectedNodeEvaluation;
				}
				/**
				 * [OfBB.selectedNodeEvaluation] Setter 
				 * @author Rafik on : 11 mars 2014  21:38:07
				 * @param selectedNodeEvaluation the selectedNodeEvaluation to set
				 */
				public void setSelectedNodeEvaluation(OffreFormationDetailContentDto selectedNodeEvaluation) {
					this.selectedNodeEvaluation = selectedNodeEvaluation;
				}
				/**
				 * [OfBB.selectedNodeMobilite] Getter 
				 * @author Rafik on : 11 mars 2014  21:38:07
				 * @return the selectedNodeMobilite
				 */
				public OffreFormationDetailContentDto getSelectedNodeMobilite() {
					
						return selectedNodeMobilite;
				}
				/**
				 * [OfBB.selectedNodeMobilite] Setter 
				 * @author Rafik on : 11 mars 2014  21:38:07
				 * @param selectedNodeMobilite the selectedNodeMobilite to set
				 */
				public void setSelectedNodeMobilite(OffreFormationDetailContentDto selectedNodeMobilite) {
					this.selectedNodeMobilite = selectedNodeMobilite;
				}
				/**
				 * [OfEquipeBB.listRoles] Getter 
				 * @author rlaib on : 27 mars 2014  10:12:21
				 * @return the listRoles
				 */
				public List<SelectItem> getListRoles() {
					
						return listRoles;
				}
				/**
				 * [OfEquipeBB.listRoles] Setter 
				 * @author rlaib on : 27 mars 2014  10:12:21
				 * @param listRoles the listRoles to set
				 */
				public void setListRoles(List<SelectItem> listRoles) {
					this.listRoles = listRoles;
				}
				/**
				 * [OfBB.listSearchMembersLazy] Getter 
				 * @author Rafik on : 28 mars 2014  18:09:31
				 * @return the listSearchMembersLazy
				 */
				public DataModel<RefIndividuDto> getListSearchMembersLazy() {
					return listSearchMembersLazy;
				}
				/**
				 * [OfBB.listSearchMembersLazy] Setter 
				 * @author Rafik on : 28 mars 2014  18:09:31
				 * @param listSearchMembersLazy the listSearchMembersLazy to set
				 */
				public void setListSearchMembersLazy(DataModel<RefIndividuDto> listSearchMembersLazy) {
					this.listSearchMembersLazy = listSearchMembersLazy;
				}
				/**
				 * [OfBB.listOfMembers] Getter 
				 * @author Rafik on : 28 mars 2014  18:09:31
				 * @return the listOfMembers
				 */
				public List<OffreFormationEquipeMembreDto> getListOfMembers() {
					return listOfMembers;
				}
				/**
				 * [OfBB.listOfMembers] Setter 
				 * @author Rafik on : 28 mars 2014  18:09:31
				 * @param listOfMembers the listOfMembers to set
				 */
				public void setListOfMembers(List<OffreFormationEquipeMembreDto> listOfMembers) {
					this.listOfMembers = listOfMembers;
				}
				/**
				 * [OfBB.currentTeam] Getter 
				 * @author Rafik on : 28 mars 2014  19:35:36
				 * @return the currentTeam
				 */
				public OffreFormationEquipeDto getCurrentTeam() {
					
					if(currentTeam == null)
						currentTeam = new OffreFormationEquipeDto();
					return currentTeam;
				}
				/**
				 * [OfBB.currentTeam] Setter 
				 * @author Rafik on : 28 mars 2014  19:35:36
				 * @param currentTeam the currentTeam to set
				 */
				public void setCurrentTeam(OffreFormationEquipeDto currentTeam) {
					this.currentTeam = currentTeam;
				}
				/**
				 * [OfBB.currentMember] Getter 
				 * @author Rafik on : 28 mars 2014  18:19:05
				 * @return the currentMember
				 */
				public OffreFormationEquipeMembreDto getCurrentMember() {
					return currentMember;
				}
				/**
				 * [OfBB.currentMember] Setter 
				 * @author Rafik on : 28 mars 2014  18:19:05
				 * @param currentMember the currentMember to set
				 */
				public void setCurrentMember(OffreFormationEquipeMembreDto currentMember) {
					this.currentMember = currentMember;
				}
				/**
				 * [OfBB.selectedIndividu] Getter 
				 * @author Rafik on : 28 mars 2014  18:19:05
				 * @return the selectedIndividu
				 */
				public RefIndividuDto getSelectedIndividu() {
					return selectedIndividu;
				}
				/**
				 * [OfBB.selectedIndividu] Setter 
				 * @author Rafik on : 28 mars 2014  18:19:05
				 * @param selectedIndividu the selectedIndividu to set
				 */
				public void setSelectedIndividu(RefIndividuDto selectedIndividu) {
					this.selectedIndividu = selectedIndividu;
				}
				/**
				 * [OfBB.selectedRole] Getter 
				 * @author Rafik on : 28 mars 2014  18:19:05
				 * @return the selectedRole
				 */
				public String getSelectedRole() {
					
					return selectedRole;
				}
				/**
				 * [OfBB.selectedRole] Setter 
				 * @author Rafik on : 28 mars 2014  18:19:05
				 * @param selectedRole the selectedRole to set
				 */
				public void setSelectedRole(String selectedRole) {
					this.selectedRole = selectedRole;
				}
				/**
				 * [OfBB.showTeamDetails] Getter 
				 * @author rlaib on : 29 mars 2014  10:47:17
				 * @return the showTeamDetails
				 */
				public boolean isShowTeamDetails() {
					
					try {
								showTeamDetails = (currentTeamId != null && currentTeamId>0);
					}
					catch (Exception e) {
						
								showTeamDetails = false;
					}
					return showTeamDetails;
				}
				/**
				 * [OfBB.rowRoleColor] Getter 
				 * @author  Rafik LAIB on : 4 avr. 2014  12:36:14
				 * @return the rowRoleColor
				 */
				public String getRowRoleColor() {
					return rowRoleColor;
				}
				/**
				 * [OfBB.rowRoleColor] Setter 
				 * @author  Rafik LAIB on : 4 avr. 2014  12:36:14
				 * @param rowRoleColor the rowRoleColor to set
				 */
				public void setRowRoleColor(String rowRoleColor) {
					this.rowRoleColor = rowRoleColor;
				}
				/**
				 * [OfBB.showTeamDetails] Setter 
				 * @author rlaib on : 29 mars 2014  10:47:17
				 * @param showTeamDetails the showTeamDetails to set
				 */
				public void setShowTeamDetails(boolean showTeamDetails) {
					this.showTeamDetails = showTeamDetails;
				}
				/**
				 * [OfBB.listSearchContractsLazy] Getter 
				 * @author rlaib on : 7 avr. 2014  10:33:11
				 * @return the listSearchContractsLazy
				 */
				public DataModel<RefContratDto> getListSearchContractsLazy() {
					return listSearchContractsLazy;
				}
				/**
				 * [OfBB.listSearchContractsLazy] Setter 
				 * @author rlaib on : 7 avr. 2014  10:33:11
				 * @param listSearchContractsLazy the listSearchContractsLazy to set
				 */
				public void setListSearchContractsLazy(DataModel<RefContratDto> listSearchContractsLazy) {
					this.listSearchContractsLazy = listSearchContractsLazy;
				}
				/**
				 * [OfBB.listOfContracts] Getter 
				 * @author rlaib on : 7 avr. 2014  10:33:11
				 * @return the listOfContracts
				 */
				public List<OffreFormationContratDto> getListOfContracts() {
					return listOfContracts;
				}
				/**
				 * [OfBB.listOfContracts] Setter 
				 * @author rlaib on : 7 avr. 2014  10:33:11
				 * @param listOfContracts the listOfContracts to set
				 */
				public void setListOfContracts(List<OffreFormationContratDto> listOfContracts) {
					this.listOfContracts = listOfContracts;
				}
				/**
				 * [OfBB.listOfPartners] Getter 
				 * @author  Rafik LAIB on : 7 avr. 2014  23:51:23
				 * @return the listOfPartners
				 */
				public List<OffreFormationPartenaireDto> getListOfPartners() {
					return listOfPartners;
				}
				/**
				 * [OfBB.listOfPartners] Setter 
				 * @author  Rafik LAIB on : 7 avr. 2014  23:51:23
				 * @param listOfPartners the listOfPartners to set
				 */
				public void setListOfPartners(List<OffreFormationPartenaireDto> listOfPartners) {
					this.listOfPartners = listOfPartners;
				}
				/**
				 * [OfBB.selectedContract] Getter 
				 * @author rlaib on : 7 avr. 2014  10:33:11
				 * @return the selectedContract
				 */
				public RefContratDto getSelectedContract() {
					return selectedContract;
				}
				/**
				 * [OfBB.currentContract] Setter 
				 * @author rlaib on : 7 avr. 2014  10:33:11
				 * @param currentContract the currentContract to set
				 */
				public void setSelectedContract(RefContratDto selectedContract) {
					this.selectedContract = selectedContract;
				}
				/**
				 * [OfBB.selectedContractPartner] Getter 
				 * @author rlaib on : 7 avr. 2014  14:28:54
				 * @return the selectedContractPartner
				 */
				public RefPartenaireDto getSelectedContractPartner() {
					return selectedContractPartner;
				}
				/**
				 * [OfBB.selectedContractPartner] Setter 
				 * @author rlaib on : 7 avr. 2014  14:28:54
				 * @param selectedContractPartner the selectedContractPartner to set
				 */
				public void setSelectedContractPartner(RefPartenaireDto selectedContractPartner) {
					this.selectedContractPartner = selectedContractPartner;
				}
				/**
				 * [OfBB.saveOfButton] Getter 
				 * @author  Rafik LAIB on : 27 avr. 2014  09:57:23
				 * @return the saveOfButton
				 */
				public UICommand getSaveOfButton() {
					return saveOfButton;
				}
				/**
				 * [OfBB.saveOfButton] Setter 
				 * @author  Rafik LAIB on : 27 avr. 2014  09:57:23
				 * @param saveOfButton the saveOfButton to set
				 */
				public void setSaveOfButton(UICommand saveOfButton) {
					this.saveOfButton = saveOfButton;
				}
				/**
				 * [OfBB.membersDataTable] Getter 
				 * @author  Rafik LAIB on : 1 avr. 2014  21:35:34
				 * @return the membersDataTable
				 */
				public UIData getMembersDataTable() {
					return membersDataTable;
				}
				/**
				 * [OfBB.membersDataTable] Setter 
				 * @author  Rafik LAIB on : 1 avr. 2014  21:35:34
				 * @param membersDataTable the membersDataTable to set
				 */
				public void setMembersDataTable(UIData membersDataTable) {
					this.membersDataTable = membersDataTable;
				}
				/**
				 * [OfBB.partnersDataTable] Getter 
				 * @author rlaib on : 7 avr. 2014  16:24:44
				 * @return the partnersDataTable
				 */
				public UIData getPartnersDataTable() {
					return partnersDataTable;
				}
				/**
				 * [OfBB.partnersDataTable] Setter 
				 * @author rlaib on : 7 avr. 2014  16:24:44
				 * @param partnersDataTable the partnersDataTable to set
				 */
				public void setPartnersDataTable(UIData partnersDataTable) {
					this.partnersDataTable = partnersDataTable;
				}
				/**
				 * [OfBB.partnersOfDataTable] Getter 
				 * @author rlaib on : 8 avr. 2014  10:34:31
				 * @return the partnersOfDataTable
				 */
				public UIData getPartnersOfDataTable() {
					return partnersOfDataTable;
				}
				/**
				 * [OfBB.partnersOfDataTable] Setter 
				 * @author rlaib on : 8 avr. 2014  10:34:31
				 * @param partnersOfDataTable the partnersOfDataTable to set
				 */
				public void setPartnersOfDataTable(UIData partnersOfDataTable) {
					this.partnersOfDataTable = partnersOfDataTable;
				}
				
				
				/**
				 * [OfBB.selectedFiliereId] Getter 
				 * @author rlaib on : 1 d�c. 2014  09:24:46
				 * @return the selectedFiliereId
				 */
				public Integer getSelectedFiliereId() {
					return selectedFiliereId;
				}

				/**
				 * [OfBB.selectedFiliereId] Setter 
				 * @author rlaib on : 1 d�c. 2014  09:24:46
				 * @param selectedFiliereId the selectedFiliereId to set
				 */
				public void setSelectedFiliereId(Integer selectedFiliereId) {
					this.selectedFiliereId = selectedFiliereId;
				}

				/**
				 * [OfBB.selectedSpecialiteId] Getter 
				 * @author rlaib on : 1 d�c. 2014  09:24:46
				 * @return the selectedSpecialiteId
				 */
				public Integer getSelectedSpecialiteId() {
					return selectedSpecialiteId;
				}

				/**
				 * [OfBB.selectedSpecialiteId] Setter 
				 * @author rlaib on : 1 d�c. 2014  09:24:46
				 * @param selectedSpecialiteId the selectedSpecialiteId to set
				 */
				public void setSelectedSpecialiteId(Integer selectedSpecialiteId) {
					this.selectedSpecialiteId = selectedSpecialiteId;
				}

				/**
				 * [OfBB.selectedCycleId] Getter 
				 * @author rlaib on : 1 d�c. 2014  09:24:46
				 * @return the selectedCycleId
				 */
				public Integer getSelectedCycleId() {
					return selectedCycleId;
				}

				/**
				 * [OfBB.selectedCycleId] Setter 
				 * @author rlaib on : 1 d�c. 2014  09:24:46
				 * @param selectedCycleId the selectedCycleId to set
				 */
				public void setSelectedCycleId(Integer selectedCycleId) {
					this.selectedCycleId = selectedCycleId;
				}

				/**
				 * [OfBB.isSelectedPartner] Getter 
				 * @author rlaib on : 7 avr. 2014  16:34:35
				 * @return the isSelectedPartner
				 */
				public SelectBooleanCheckbox getIsSelectedPartner() {
					return isSelectedPartner;
				}
				/**
				 * [OfBB.isSelectedPartner] Setter 
				 * @author rlaib on : 7 avr. 2014  16:34:35
				 * @param isSelectedPartner the isSelectedPartner to set
				 */
				public void setIsSelectedPartner(SelectBooleanCheckbox isSelectedPartner) {
					this.isSelectedPartner = isSelectedPartner;
				}				
				// endregion PROPERTIES MODEL GETTERS/SETTERS
				
				// ===================================================================  
				// Listeners
				// ===================================================================
				
				// region LISTENERS
				
				/**
				 * [SearchOf.handleDomainesListChange] Method 
				 * @author rlaib  on : 6 mars 2014  10:24:51
				 * @param event
				 */
				public void  handleDomainesListChange(AjaxBehaviorEvent event) {
					initFilieresByDomaine(selectedDomaineId);
						
				}
				
				public void  handleFacultesListChange(AjaxBehaviorEvent event) {
					initOfDepartements(this.idEtab, selectedFaculteId);
						
				}
				
				/**
				 * [SearchOf.handleFilieresListChange] Method 
				 * @author rlaib  on : 6 mars 2014  10:24:58
				 * @param event
				 */
				public void  handleFilieresListChange(AjaxBehaviorEvent event) {
					
					initSpecialitesByFiliere(selectedFiliereId);
				}
				
				/**
				 * [OfBB.handleTypesFormationListChange] Method 
				 * @author rlaib  on : 8 sept. 2014  08:57:36
				 * @param event
				 */
				public void  handleTypesFormationListChange(AjaxBehaviorEvent event) {
					
					if(this.editableCycle) {
						MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB1_TITLE)
								, MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_CHANGE_TYPE_FORMATION_ERROR)); 
							selectedTypeFormationId = ofDto.getIdTypeFormation();
					}
					else
							initListCyclesByTypeFormation(selectedTypeFormationId);
				}
				
				/**
				 * [OfBB.handleCyclesListChange] Method 
				 * @author rlaib  on : 8 sept. 2014  08:57:45
				 * @param event
				 */
				public void  handleCyclesListChange(AjaxBehaviorEvent event) {
					
						if(this.editableCycle) {
							MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB1_TITLE)
									, MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_CHANGE_CYLE_ERROR)); 
								selectedCycleId = ofDto.getIdCycle();
						}
							
				}
				
				/**
				 * [OfBB.onNodeSelectComplement] Method 
				 * @author rlaib  on : 8 avr. 2014  08:35:33
				 * @param node
				 */
				public void onNodeSelectComplement(OffreFormationDetailContentDto node) {
							
						if(node.getOffreFormationDetail().getOffreFormationDetail() != null) {
						    	try {
						    				selectedNodeComplement = this.getNodeContentDataById(node.getId());
											currentContentComplement =selectedNodeComplement.getContentFr();
											currentNodeComplement = String.valueOf(selectedNodeComplement.getId());
											ofId = String.valueOf(selectedNodeComplement.getOffreFormation().getId());
											//MessageUtil.addInfoMessageWithoutKey(currentContent.getOffreFormationDetail().getDescriptionFr(),currentContent.getOffreFormationDetail().getLibelleFr());
									        
						    	}
						    	catch (Exception e) {
						    		
						    		e.printStackTrace();
						    	}
					    }
						else {
							
								currentNodeComplement = "";
						}
						
				}
				
				/**
				 * [OfBB.onNodeSelectEvaluation] Method 
				 * @author rlaib  on : 8 avr. 2014  08:35:39
				 * @param node
				 */
				public void onNodeSelectEvaluation(OffreFormationDetailContentDto node) {
					
					if(node.getOffreFormationDetail().getOffreFormationDetail() != null) {
						try {
							selectedNodeEvaluation= this.getNodeContentDataById(node.getId());
							currentContentEvaluation =selectedNodeEvaluation.getContentFr();
							currentNodeEvaluation = String.valueOf(selectedNodeEvaluation.getId());
							ofId = String.valueOf(selectedNodeEvaluation.getOffreFormation().getId());
							//MessageUtil.addInfoMessageWithoutKey(currentContent.getOffreFormationDetail().getDescriptionFr(),currentContent.getOffreFormationDetail().getLibelleFr());
							
						}
						catch (Exception e) {
							
							e.printStackTrace();
						}
					}
					else {
						
						currentNodeEvaluation = "";
					}
					
				}
				
				/**
				 * [OfBB.onNodeSelectMobilite] Method 
				 * @author rlaib  on : 8 avr. 2014  08:35:45
				 * @param node
				 */
				public void onNodeSelectMobilite(OffreFormationDetailContentDto node) {
					
					if(node.getOffreFormationDetail().getOffreFormationDetail() != null) {
						try {
							selectedNodeMobilite= this.getNodeContentDataById(node.getId());
							currentContentMobilite =selectedNodeMobilite.getContentFr();
							currentNodeMobilite= String.valueOf(selectedNodeMobilite.getId());
							ofId = String.valueOf(selectedNodeMobilite.getOffreFormation().getId());
							//MessageUtil.addInfoMessageWithoutKey(currentContent.getOffreFormationDetail().getDescriptionFr(),currentContent.getOffreFormationDetail().getLibelleFr());
							
						}
						catch (Exception e) {
							
							e.printStackTrace();
						}
					}
					else {
						
						currentNodeMobilite= "";
					}
					
				}
				
				/**
				 * [OfBB.handleAddPersonToTeam] Method 
				 * @author rlaib  on : 8 avr. 2014  08:35:51
				 * @param event
				 */
				public void  handleAddPersonToTeam(AjaxBehaviorEvent event) {
					
					String code = (String) ((UIOutput) event.getSource()).getValue();
				
					MessageUtil.addInfoMessageWithoutKey("EQUIPE OF ",code);
					
				}
				
				/**
				 * [OfBB.onRowSelectIndividu] Method 
				 * @author rlaib  on : 8 avr. 2014  08:35:56
				 * @param event
				 */
				public void  onRowSelectIndividu(SelectEvent event) {
					
							try {
									RefIndividuDto ind = (RefIndividuDto) event.getObject();
									currentIndividuId = ind.getId();
									//MessageUtil.addInfoMessageWithoutKey("Selection OF : ", selectedIndividu.getNomArabe());
							}
							catch (Exception e) {
								
								e.printStackTrace();
							}
					
				}
				
				/**
				 * [OfBB.onRowSelectContract] Method 
				 * @author rlaib  on : 8 avr. 2014  08:36:03
				 * @param event
				 */
				public void  onRowSelectContract(SelectEvent event) {
					
					try {
						
								//RefContratDto contract = (RefContratDto) event.getObject();
								currentContractId = selectedContract.getIdentifiant();
								selectedContractPartners = this.getPartnersByContractCode(currentContractId);
								
								
					}
					catch (Exception e) {
									
					           e.printStackTrace();
					}
					
				}
				
				/**
				 * [OfBB.handleRolesListChange] Method 
				 * @author rlaib  on : 8 avr. 2014  08:36:08
				 * @param event
				 */
				@SuppressWarnings("unused")
				public void  handleRolesListChange(AjaxBehaviorEvent event) {
				
					String code = (String) ((UIOutput) event.getSource()).getValue();
					
				}
				
				public void handleEtablissementListChange(AjaxBehaviorEvent event) {
					
				}
				// endregion LISTENERS
				
				// ===================================================================  
				// ActionListener
				// ===================================================================
				
				// region ACTIONLISTENERS
				/**
				 * [OfBB.saveTeam] Method 
				 * @author rlaib  on : 9 avr. 2014  10:20:26
				 */
				public void saveTeam() {
					
						try {
									currentTeam.setDateCreation(new java.util.Date());
									currentTeam.setOffreFormationId(ofDto.getId());
									currentTeam = offreFormationEquipeService.insertOrUpdate(currentTeam);
									currentTeamId = currentTeam.getId();
									Object[] params = {currentTeam.getLibelleFr()};
									MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,
											OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB3_TITLE)
																							,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,
											OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_SAVE_TEAM_SUCCESS, params,"fr"));
									
						}
						catch (Exception e) {
							
							Object[] params = {currentTeam.getLibelleFr()};
							e.printStackTrace();
							MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,
									OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB3_TITLE)
																							,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,
									OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_SAVE_TEAM_FAILURE, params,"fr")); 
							
						} 
				}
				
				/**
				 * [OfBB.searchMember] Method 
				 * @author rlaib  on : 9 avr. 2014  10:20:32
				 */
				public void searchMember() {
					
					try {
								List<RefIndividuDto> listSearchMembers = new ArrayList<RefIndividuDto>();
								if (keyWordsMember != null && !keyWordsMember.trim().equals("")) {
										listSearchMembers = this.searchPerson(keyWordsMember);
								}
								listSearchMembersLazy = new RefIndividuDataModel(listSearchMembers);
					}
					catch (Exception e) {
								e.printStackTrace();
					}
				}
				
				/**
				 * [OfBB.searchContract] Method 
				 * @author rlaib  on : 9 avr. 2014  10:20:38
				 */
				public void searchContract() {
					
					try {
								List<RefContratDto> listSearchContracts = new ArrayList<RefContratDto>();
								if (keyWordsContract!= null && !keyWordsContract.trim().equals("")) {
									listSearchContracts = this.searchContract(keyWordsContract);
								}
								listSearchContractsLazy = new RefContratDataModel(listSearchContracts);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				/**
				 * [OfBB.addMember] Method 
				 * @author rlaib  on : 9 avr. 2014  10:21:06
				 */
				public void addMember() {
					
					try {
								int couldBeAdded = canAddMembre();
								switch (couldBeAdded) {
								case 0:
									
									OffreFormationEquipeMembreDto memberToAdd = new OffreFormationEquipeMembreDto();
									selectedIndividu =  this.searchPerson(currentIndividuId.toString()).get(0);
									if(selectedIndividu == null)
										return;
									memberToAdd.setRefCodeMembre(selectedIndividu.getIdentifiant());
									memberToAdd.setLibelleFr(selectedIndividu.getCiviliteCode() + ".  " + selectedIndividu.getPrenomLatin() + " " + selectedIndividu.getNomLatin());
									memberToAdd.setLibelleAr(selectedIndividu.getNomArabe() + " " + selectedIndividu.getPrenomArabe()  + " " + selectedIndividu.getCiviliteLibelleCourtAr());
									memberToAdd.setDateCreation(new java.util.Date());
									memberToAdd.setRoleMembre(selectedRole);
									memberToAdd.setOffreFormationEquipeId(currentTeamId);
									offreFormationEquipeMembreService.insertOrUpdate(memberToAdd);
									listOfMembers = offreFormationEquipeMembreService.findMembersByTeamId(currentTeamId);
									String memberName = getNomeclatureByCode(OfConstants.NC_NAME_ROLE_EQUIPE_OF, selectedRole).getLibelleLongFr();
									
									//listOfMembers.add(memberToAdd);
									Object[] params = {memberName, selectedIndividu.getPrenomLatin() + " " +selectedIndividu.getNomLatin() };
									MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB3_TITLE)
													,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_ADD_TEAM_MEMBER_SUCCESS, params,"fr"));
			
									break;

								case 1:

									MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB3_TITLE)
											,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_ADD_TEAM_MEMBER_ALREADY_EXIST, null,"fr"));
	
									
									break;
								case 2:
									
									MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB3_TITLE)
											,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_ADD_TEAM_MEMBER_FAILURE, null,"fr"));
									break;
								default:
									break;
								}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				/**
				 * [OfBB.removeMember] Method 
				 * @author rlaib  on : 9 avr. 2014  10:21:14
				 * @param idMember
				 */
				public void removeMember(int idMember) {
					
						try {
									offreFormationEquipeMembreService.remove(idMember);
									listOfMembers = offreFormationEquipeMembreService.findMembersByTeamId(currentTeamId);
									MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB3_TITLE)
													,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_REMOVE_TEAM_MEMBER_SUCCESS, null,"fr"));
							
						}
						catch (Exception e) {
								e.printStackTrace();
								MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB3_TITLE)
										,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_REMOVE_TEAM_MEMBER_FAILURE, null,"fr"));
		
						}
				}
				
				/**
				 * [OfBB.removePartner] Method 
				 * @author rlaib  on : 9 avr. 2014  10:21:21
				 * @param idPartner
				 */
				public void removePartner(int idPartner) {
					
					try {
								offreFormationPartenaireService.remove(idPartner);
								listOfPartners = offreFormationService.findPartnersByOfId(Integer.parseInt(ofId));
								MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB4_TITLE)
										,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_ADD_PARTNERS_SUCCESS, null,"fr"));
						
					}
					catch (Exception e) {
						e.printStackTrace();
						MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB4_TITLE)
								,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_ADD_PARTNERS_FAILURE, null,"fr"));
					}
				}
				
				/**
				 * [OfBB.prepareMembre] Method 
				 * @author rlaib  on : 9 avr. 2014  10:21:27
				 * @param idMember
				 */
				public void prepareMembre(String idMember){
					
					try {
								selectedIndividu =  this.getPersonByNIN(idMember);
								currentIndividuId = selectedIndividu.getId();
					}
					catch (Exception e){
						
						e.printStackTrace();
					}
				}
				
				/**
				 * [OfBB.prepareContractDetail] Method 
				 * @author rlaib  on : 9 avr. 2014  10:21:34
				 * @param idContract
				 */
				public void prepareContractDetail(String codeContract){
					
					try {
								selectedContract =  this.getContractByCode(codeContract);
								currentContractId =  selectedContract.getIdentifiant();
								selectedContractPartners = this.getPartnersByContractCode(currentContractId);
								
					}
					catch (Exception e){
						
						e.printStackTrace();
					}
				}
				
				/**
				 * [OfBB.addPartnersToOf] Method 
				 * @author rlaib  on : 9 avr. 2014  10:21:51
				 */
				@SuppressWarnings("unchecked")
				public void addPartnersToOf() {
					
						try {
									List<OffreFormationPartenaireDto> partners =  getSelectedPartners();
										
									if(partners.size() > 0){
									
										
										List<OffreFormationPartenaireDto>  partnersOf= (List<OffreFormationPartenaireDto>) this.partnersOfDataTable.getValue();
								    	
										for (OffreFormationPartenaireDto offreFormationPartenaireDto : partners) {
												
													for (OffreFormationPartenaireDto offreFormationPartenaireDto2 : partnersOf) {
															
															if(offreFormationPartenaireDto.getRefCodePartenaire().equals(offreFormationPartenaireDto2.getRefCodePartenaire())){
																
																Object[] params = {offreFormationPartenaireDto.getLibelleFr()};
																MessageUtil.addWarnMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB4_TITLE)
																				,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_ADD_PARTNERS_ALREADY_EXIST, params,"fr"));
										
																return;	
																
															}
														
													}
										}
										
										OffreFormationContratDto contract = getCurrentOfContract(selectedContract);
				
										contract = offreFormationContratService.saveContract(contract, partners, ofDto);
									
										listOfPartners = offreFormationService.findPartnersByOfId(Integer.parseInt(ofId));
										
										MessageUtil.addInfoMessageWithoutKey(OfConstants.OF_COMPONENT_MSG_TITLE , OfConstants.OF_TEAM_SUCCESS_PARTNER_ADD);
									
									}
									else {
										
												MessageUtil.addWarnMessageWithoutKey(OfConstants.OF_COMPONENT_MSG_TITLE, OfConstants.OF_PARTENAIRE_NO_SELECTION);  
										
									}
						}
						catch (Exception e) {
						
								e.printStackTrace();
								MessageUtil.addErrorMessageWithoutKey(OfConstants.OF_COMPONENT_MSG_TITLE, OfConstants.OF_COMPONENT_SAVE_FAILURE); 
								
						}
					
				}	
				
				// endregion ACTIONLISTENERS
				
				// ===================================================================  
				// Actions and Methods
				// ===================================================================
				
				// region ACTIONS
				/**
				 * [OfBB.initOfDetails] Method 
				 * @author rlaib  on : 1 oct. 2014  16:54:55
				 */
				private void initOfDetails() {

					try {
								//ofDto  = offreFormationService.findById(Integer.parseInt(ofId));
								if(ofDto != null) {
									parcoursTypes = parcoursTypeService.findByOfId(Integer.parseInt(ofId));
									currentTeam = offreFormationEquipeService.findOfEquipeById(ofDto.getId());
									if(currentTeam != null) {
										currentTeamId = currentTeam.getId();
										listOfMembers = offreFormationEquipeMembreService.findMembersByTeamId(currentTeamId);
									}
								}
					}
					 catch(Exception e){
						 e.printStackTrace();
					 }
					
				}
				
				/**
				 * [OfBB.save] Method 
				 * @author rlaib  on : 9 avr. 2014  10:20:02
				 * @return
				 */
				@SuppressWarnings("unchecked")
				public void save() 
				{
					try{
								List<OffreFormationDetailContentDto>  ofDetails = new ArrayList<OffreFormationDetailContentDto>();
								SituationEntiteDto  seDto = new SituationEntiteDto();
								switch (editionMode) {
								case OfConstants.OF_COMPONENT_NEW_MODE:
									
									// Recuperer la situation de la demande a enregistrer
									seDto = situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_OFFRE_FORMATION_CODE, UtilConstants.OFFRE_FORMATION_SITUTAION_CREEE_CODE);
									ofDto.setDateCreation(new java.util.Date());
									RefDomaineLMDDto oneDomaine = refDomaineLMDService.findById(selectedDomaineId);
									if(oneDomaine == null)
										return;
									
									NomenclatureDto _vocation = nomenclatureService.findById(selectedVocationFormationId);
									String codeOf = getNextOfCode(this.idEtab,  this.newEtabCode, oneDomaine.getIdentifiant(), selectedCycleDto.getCode(), _vocation.getCode());
									ofDto.setCode(codeOf);
									ofDetails = ListUtils.union(ListUtils.union(listTreeComplement, listTreeEvaluation)	, listTreeMobilite);
									break;
									
								case OfConstants.OF_COMPONENT_EDIT_MODE:
									
									// Test si le Dto a bien subi des modifications
//									if(ofDtoPrevious != null) {
//										if(ofDtoPrevious.equals(ofDto)) {
//											MessageUtil.addWarnMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,
//																		OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB1_TITLE)
//													, MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,
//															OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_SAVE_DESCRIPTIF_NO_MODIFCATION_TO_SAVE));
//											return;
//										}
//									}
											
									seDto = situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_OFFRE_FORMATION_CODE, UtilConstants.OFFRE_FORMATION_SITUTAION_ENREGISTREE_PARTIELLEMENT_CODE);
									ofDto.setDateModification(new java.util.Date());
									ofDto.setId(Integer.parseInt(ofId));
									break;
		
								default:
									break;
								}
								// Structure
								if(selectedFaculteId != null &&  selectedFaculteId>0)
									ofDto.setIdStructure(selectedFaculteId);
								if(selectedDepartementId != null && selectedDepartementId>0)
									ofDto.setIdStructure(selectedDepartementId);
								
								ofDto.setIdSituation(seDto.getId());
								ofDto.setIdDomaine(selectedDomaineId);
								ofDto.setIdFiliere(selectedFiliereId);
								ofDto.setIdSpecialite(selectedSpecialiteId);
								ofDto.setIdCycle(selectedCycleId);
								ofDto.setIdTypeFormation(selectedTypeFormationId);
								ofDto.setIdVocation(selectedVocationFormationId);
								CycleDto cycle = cycleService.findById(selectedCycleId);
								ofDto.setIdCycle(cycle.getId());
								this.ofDto.setIdEtablissement(this.idEtab);
								
								this.ofDto = offreFormationService.insertOrUpdate(this.ofDto,ofDetails);
									
								if (this.ofDto.getId() <= 0) {
								
									if (this.ofDto.getId() == -1) {
									// Code national OF deja existant
									Object[] params = {this.ofDto.getCode()};
									MessageUtil.addWarnMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB1_TITLE)
											, MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_SAVE_DESCRIPTIF_ERROR_EXISTING_CODE, params,"fr"));
									}
									else
									{
										if (this.ofDto.getId() == 0)
											// Erreur lors de l enregistrement
											MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB1_TITLE)
													, MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_SAVE_DESCRIPTIF_FAILURE)); 
								
									}
								}
								else{
												// 	Enregistrement OF reussi
												// Sauvegarde de la situation de l'occurrence de la  demande
												SituationEntiteOccurrenceDto situation = new SituationEntiteOccurrenceDto();
												situation.setIdOccurrence(this.ofDto.getId());
												situation.setIdSituation(seDto.getId());
												situation.setDateSituation(new java.util.Date());
												situationService.saveSituationOccurrence(situation);
							 					this.editionMode = OfConstants.OF_COMPONENT_EDIT_MODE;
							 					ofDtoPrevious = ofDto;
												ofId  = String.valueOf(ofDto.getId());
												parcoursTypes = parcoursTypeService.findByOfId(Integer.parseInt(ofId));
							 					initOfRepartition();
												Object[] params = {this.ofDto.getCode()};
												MessageUtil.addInfoMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB1_TITLE)
																							,MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_SAVE_DESCRIPTIF_SUCCESS , params,"fr"));
								}
								
									
							}
							catch(Exception e){
									
								e.printStackTrace();
							}
				}
				
				
				/**
				 * [OfBB.prepareOfStructures] Method 
				 * @author rlaib  on : 11 oct. 2014  13:53:01
				 */
				private void prepareOfStructures() {
					
					try {
								if(selectedDepartementOf != null && selectedDepartementOf.getId()>0) {
									selectedStructureOf.setIdStructure(selectedDepartementOf.getId());
								}
								else
									if(selectedFaculteOf != null && selectedFaculteOf.getId()>0) {
										selectedStructureOf.setIdStructure(selectedFaculteOf.getId());
									}
								selectedStructureOf.setIdOffreFormation(ofDto.getId());
								selectedStructureOf.setIdEtablissement(this.idEtab);
							
					}
					catch(Exception e){
						
						e.printStackTrace();
					}
				}

				/**
				 * [OfBB.getNextOfCode] Method 
				 * @author rlaib  on : 21 sept. 2014  16:16:33
				 * @param newEtabCode2
				 * @param selectedDomaine2
				 * @param code
				 * @param selectedVocation2
				 * @return
				 */
				private String getNextOfCode(Integer idEtablissement,String codeEtab, String codeDomaine, String codeCycle, 	String codeVocation) {
					
					try {
							String result ="";
							int indexOf = offreFormationService.getLastOfIndex(idEtablissement) +1 ;
							String seq =  String.format("%03d", indexOf);
							if(idEtablissement == null || newEtabCode == null || codeDomaine == null ||codeCycle==null ||  codeVocation==null || seq == null)
								return result;
							result = codeEtab + codeDomaine + codeCycle + codeVocation + seq;
							return result;
						}
						catch(Exception e){
								
							e.printStackTrace();
							return null;
						}
				}

				/**
				 * [OfBB.saveOfComplement] Method 
				 * @author rlaib  on : 9 avr. 2014  10:19:55
				 */
				public void saveOfComplement(){
					
					try {
								selectedNodeComplement = this.getNodeContentDataById(Integer.parseInt(currentNodeComplement));
								selectedNodeComplement.setContentFr(currentContentComplement);
								ofId = String.valueOf(selectedNodeComplement.getOffreFormation().getId());
								selectedNodeComplement = this.updateNodeContentDataById(selectedNodeComplement);
								
					}
					catch (Exception e) {
						
						e.printStackTrace();
					}
					
				}
				
				/**
				 * [OfBB.saveOfEvaluation] Method 
				 * @author rlaib  on : 9 avr. 2014  10:19:50
				 */
				public void saveOfEvaluation(){
					
					try {
								selectedNodeEvaluation = this.getNodeContentDataById(Integer.parseInt(currentNodeEvaluation));
								selectedNodeEvaluation.setContentFr(currentContentEvaluation);
								ofId = String.valueOf(selectedNodeEvaluation.getOffreFormation().getId());
								selectedNodeEvaluation = this.updateNodeContentDataById(selectedNodeEvaluation);
						
					}
					catch (Exception e) {
						
								e.printStackTrace();
					}
					
				}
				
				/**
				 * [OfBB.saveOfMobilite] Method 
				 * @author rlaib  on : 9 avr. 2014  10:19:43
				 */
				public void saveOfMobilite(){
					
					try {
						selectedNodeMobilite= this.getNodeContentDataById(Integer.parseInt(currentNodeMobilite));
						selectedNodeMobilite.setContentFr(currentContentMobilite);
						ofId = String.valueOf(selectedNodeMobilite.getOffreFormation().getId());
						selectedNodeMobilite= this.updateNodeContentDataById(selectedNodeMobilite);
						
					}
					catch (Exception e) {
						
						e.printStackTrace();
					}
					
				}
				
				/**
				 * [OfBB.validate] Method 
				 * @author rlaib  on : 9 avr. 2014  10:19:38
				 * @return
				 */
				public void validateSave() {
					
					try {
								// Mise a� jour de la situation de l'offre de formation
								SituationEntiteDto seOfDto = situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_OFFRE_FORMATION_CODE
										, UtilConstants.OFFRE_FORMATION_SITUTAION_ENREGISTREE_DEFINITIVEMENT_CODE);
								this.ofDto = offreFormationService.findById(Integer.parseInt(this.ofId));
								this.ofDto.setIdSituation(seOfDto.getId());
								this.ofDto.setEstCompletee(true);
								this.ofDto.setDateModification(new java.util.Date());
								offreFormationService.insertOrUpdate(this.ofDto, null);
								// Sauvegarde de la situation de l'occurrence de la  demande
								SituationEntiteOccurrenceDto situation = new SituationEntiteOccurrenceDto();
								situation.setIdOccurrence(this.ofDto.getId());
								situation.setIdSituation(seOfDto.getId());
								situation.setDateSituation(new java.util.Date());
								situationService.saveSituationOccurrence(situation);
					}
					catch (Exception e) {
						
								e.printStackTrace();
					}
					
//					return this.outcomePage;
					
				}
				
				/**
				 * [OfBB.toRender] Method 
				 * @author rlaib  on : 9 avr. 2014  10:19:31
				 * @return
				 */
				public boolean toRender() {
					
					try {
								return (this.editionMode.equals(OfConstants.OF_COMPONENT_EDIT_MODE));
					}
					catch (Exception e) {
						
								return false;
						
					}
					
				}
				
				/**
				 * [OfBB.goBack] Method 
				 * @author  Rafik LAIB  on : 31 mars 2014  15:51:42
				 * @return
				 */
				public String goBack() {
					
						return this.outcomePage;
//						return "ofSearch";
				}
				
				/**

				 * [OfBB.renderTabOf] Method 
				 * Show or Hide Tab
				 * @author  Rafik LAIB  on : 31 mars 2014  15:51:01
				 * @return
				 */
				public boolean renderTabOf() {
			    	
			    			return !(ofId != null && !ofId.trim().isEmpty() && !ofId.equals("null"));
				}
				
				/**
				 * [OfBB.prepareRepartitionUe] Method 
				 * @author rlaib  on : 17 sept. 2014  09:54:43
				 */
				public void prepareRepartitionUe(){
					
					try {
								this.selectedLevel = niveauService.findById(Integer.parseInt(selectedLevelId));
								this.selectedPeriod = periodeService.findById(Integer.parseInt(selectedPeriodeId));
								this.availableUesPeriod = new ArrayList<UniteEnseignementDto>();
								
								if(this.selectedRepartitionId != null && !this.selectedRepartitionId.equals("null")) {
									this.selectedRepartitionUe = repartitionUeService.findById(Integer.parseInt(selectedRepartitionId));
									this.availableUesPeriod.add(uniteEnseignementService.findById(selectedRepartitionUe.getUniteEnseignementId()));
								}
								else {
												this.selectedRepartitionUe = new RepartitionUeDto();
												selectedRepartitionUe.setCoefficient(1.00d);
								}
								this.availableUesPeriod.addAll(repartitionUeService.getAvailableUesPeriod(Integer.parseInt(this.selectedParcoursId), Integer.parseInt(this.selectedPeriodeId)));
						
							}
							catch (Exception e) {
								
								e.printStackTrace();
							}
				}
				
				/**
				 * [OfBB.saveRepartitionUe] Method 
				 * @author rlaib  on : 17 sept. 2014  09:54:58
				 */
				public void saveRepartitionUe(){
					
					try {
								RequestContext context = RequestContext.getCurrentInstance();
								if (this.selectedUeId!= null) {
									selectedUE = uniteEnseignementService.findById(Integer.parseInt(selectedUeId));
								}
								double currentTotal = 0;
								if(selectedPeriodeId == null)
									return;
								else
									currentTotal = currentTotal +  this.totauxPeriodes.get(Integer.parseInt(this.selectedPeriodeId));
								if(selectedUE == null || selectedUE.getCredits() == null)
									return;
								else
									currentTotal = currentTotal + selectedUE.getCredits() ;
								
								if(currentTotal > selectedPeriod.getCredit()) {
									
									Object[] params = {this.selectedPeriod.getLibelleLongLt()};
									MessageUtil.addErrorMessageWithoutKey(MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB1_TITLE)
											, MessageUtil.getStringValueFromBundleResourceWithParams(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_REMOVE_REPARTITION_UE_TOTAL, params,"fr"));
							        
									context.addCallbackParam("isValid", false);
									return;
								}
								
								if(this.selectedRepartitionUe != null) {
										this.selectedRepartitionUe.setIdPeriode(Integer.parseInt(this.selectedPeriodeId));
										this.selectedRepartitionUe.setParcoursTypeId(Integer.parseInt(this.selectedParcoursId));
										this.selectedRepartitionUe.setUniteEnseignementId(Integer.parseInt(this.selectedUeId));
										repartitionUeService.insertOrUpdate(selectedRepartitionUe);
										initOfRepartition();
										context.addCallbackParam("isValid", true);
								}
				}
					catch (Exception e) {
						
						e.printStackTrace();
					}
					
				}
				
				/**
				 * [OfBB.removeRepartitionUe] Method 
				 * @author rlaib  on : 17 sept. 2014  09:55:09
				 */
				public void removeRepartitionUe(){
					
					try {
						if(this.selectedRepartitionId != null) {
								// Supprimer une repartition UE
									repartitionUeService.remove(Integer.parseInt(selectedRepartitionId));
									initOfRepartition();
						}
					}
					catch (Exception e) {
						
						e.printStackTrace();
					}
					
				}
				// endregion ACTIONS
				
				// ===================================================================  
				// Functions Helper
				// ===================================================================
				
				// region FUNTIONS HELPERS
			    
			    /**
			     * [OfBackingBean.processTree] Method 
			     * @author Rafik LAIB (mac)  on : 7 fevr. 2014  21:36:10
			     * @param list
			     */
			    @SuppressWarnings("unused")
				private TreeNode processTree(List<OffreFormationDetailContentDto> list) {

			    	TreeNode root = new DefaultTreeNode("Root", null);
			    	
			    	for (OffreFormationDetailContentDto item : list) {
			    				
						    		if (item.getOffreFormationDetail().getOffreFormationDetail() == null) {
						    			
						    			item.getOffreFormationDetail().setLibelleFr(item.getOffreFormationDetail().getLibelleFr().toUpperCase());
						    			
						    			TreeNode node1 = new DefaultTreeNode(item, root); 
						    			node1.setSelectable(false);
						    									    			
						    			for (OffreFormationDetailContentDto item1 : list) {
						    				
						    				OffreFormationDetail parent = item1.getOffreFormationDetail().getOffreFormationDetail();
						    				
						    				if(parent != null ){
								    					if(parent.getId() == item.getOffreFormationDetail().getId()){
								    						
								    						TreeNode node2 = new DefaultTreeNode(item1, node1); 
								    						
								    					}
						    				}
						    				node1.setExpanded(true);
										}
						    		}
					}
			    
			    	return root;

			    }
			    
			    /**
			     * [OfBB.processAllTreeByOfId] Method 
			     * @author rlaib  on : 9 avr. 2014  10:15:29
			     * @param objectId
			     */
				private void processAllTreeByOfId(int objectId) {
			    	
			    	try {
								List<OffreFormationDetailContentDto> detailOfDataSources =  offreFormationDetailContentService.findOfContentTreeById(objectId);
								listTreeComplement = new ArrayList<OffreFormationDetailContentDto>();
								listTreeEvaluation = new ArrayList<OffreFormationDetailContentDto>();
								listTreeMobilite = new ArrayList<OffreFormationDetailContentDto>();
								
							   for (OffreFormationDetailContentDto det : detailOfDataSources) {
									
											switch (det.getOffreFormationDetail().getTypeDetail()) {
											
														case 1:
															
															// This is a detail for the OFFRE FROMATION COMPLEMENT
															listTreeComplement.add(det);
															
															break;
														case 2:
															
															// This is a detail for the OFFRE FROMATION EVALUATION
															listTreeEvaluation.add(det);
															
															break;
														case 3:
															
															// This is a detail for the OFFRE FROMATION MOBILITE
															listTreeMobilite.add(det);
														
															break;
						
														default:
															break;
											}
								}
							   rootComplement = processTree(listTreeComplement);
							   rootEvaluation = processTree(listTreeEvaluation);
							   rootMobilite = processTree(listTreeMobilite);
							   
			    	}
			    	
			    	catch (Exception e) {
						
						e.printStackTrace();
					}
			    	
			    }
			
			    /**
			     * [OfBB.processAllNewTreesDetails] Method 
			     * @author rlaib  on : 9 avr. 2014  10:15:55
			     */
			    public void processAllNewTreesDetails()
				{
					
					try {
									
									List<OffreFormationDetail> detailOfDataSources =  offreFormationDetailService.findAll();
									listTreeComplement = new ArrayList<OffreFormationDetailContentDto>();
									listTreeEvaluation = new ArrayList<OffreFormationDetailContentDto>();
									listTreeMobilite = new ArrayList<OffreFormationDetailContentDto>();
									
					
									for (OffreFormationDetail det : detailOfDataSources) {
										
												OffreFormationDetailContentDto newItem = new OffreFormationDetailContentDto();
												newItem.setOffreFormationDetail(det);
										
												switch (det.getTypeDetail()) {
												
												case 1:
													
													// This is a detail for the OFFRE FROMATION COMPLEMENT
													listTreeComplement.add(newItem);
													
													break;
												case 2:
													
													// This is a detail for the OFFRE FROMATION EVALUATION
													listTreeEvaluation.add(newItem);
													
													break;
												case 3:
													
													// This is a detail for the OFFRE FROMATION MOBILITE
													listTreeMobilite.add(newItem);
													
													break;
													
												default:
													break;
												}
									}
									rootComplement = processTree(listTreeComplement);
									rootEvaluation = processTree(listTreeEvaluation);
									rootMobilite = processTree(listTreeMobilite);
							
						
					}
					catch (Exception e) {
						
									e.printStackTrace();
					}
					
				}
			   
			    /**
			     * [OfBB.getNomeclatureByCode] Method 
			     * @author rlaib  on : 9 avr. 2014  10:16:04
			     * @param ncName
			     * @param code
			     * @return
			     */
			    public NomenclatureDto getNomeclatureByCode(String ncName, String code){
			    	return this.getNomenclatureObjectByCodeByType(ncName, code);
			    	
			    }
			
			    /**
			     * [OfBB.canAddMembre] Method 
			     * @author rlaib  on : 9 avr. 2014  10:16:11
			     * @return
			     */
			    @SuppressWarnings("unchecked")
				private int canAddMembre(){
			    	
							List<OffreFormationEquipeMembreDto>  data= (List<OffreFormationEquipeMembreDto>) this.membersDataTable.getValue();
							
							for (OffreFormationEquipeMembreDto item : data) {
								if(item.getRefCodeMembre().equals(currentIndividuId))
									return 1;
							}
			
							for (OffreFormationEquipeMembreDto item : data) {
								
								if(item.getRoleMembre().equals(OfConstants.OF_TEAM_MEMBER_ROLE_RESPONSIBLE_CODE) && (selectedRole.equals(OfConstants.OF_TEAM_MEMBER_ROLE_RESPONSIBLE_CODE))){
									return 2;
								}
							}
							return 0;
			    	
			    }
			    
			    /**
			     * [OfBB.getRowColor] Method 
			     * @author rlaib  on : 9 avr. 2014  10:18:33
			     * @param code
			     * @return
			     */
			    public String getRowColor(String code){
			    			
			    	if(code != null)
			    			if(selectedRole.equals(OfConstants.OF_TEAM_MEMBER_ROLE_MEMBER_CODE)) this.rowRoleColor = OfConstants.ROW_ROLE_COLOR_MEMBER;
					else
						this.rowRoleColor = OfConstants.ROW_ROLE_COLOR_RESPONSIBLE;
			    	
			    	return this.rowRoleColor;
			    }
			  
			    /**
			     * [OfBB.processLmdNomenclaturesListsFr] Method 
			     * @author rlaib  on : 9 avr. 2014  10:18:44
			     * @param list
			     */
				private void initLmdNomenclaturesListsFr() {
				    	
//						List<NomenclatureDto> list = this.getAllLmdNomenclatures();
						List<NomenclatureDto> list = nomenclatureService.findByDomaine(OfConstants.NC_NAME_LMD);
						if(list == null || list.size() == 0)
							return;
			    		listTypesFormation	= new ArrayList<SelectItem>();
			    		listVocations	= new ArrayList<SelectItem>();
			    		listRoles	= new ArrayList<SelectItem>();
				    	
			    		try{
								for (NomenclatureDto nc : list) {
										SelectItem item = new SelectItem(nc.getId(), nc.getLibelleLongFr());
										switch (nc.getNcName()) {
													
												// Types de formation	
												case OfConstants.NC_NAME_TYPE_FORMATION:
													listTypesFormation.add(item);
													break;
												
												// Vocations de formation	
												case OfConstants.NC_NAME_VOCATION:
													listVocations.add(item);
													break;
													
													// Roles membre equipe de formation	
												case OfConstants.NC_NAME_ROLE_EQUIPE_OF:
													listRoles.add(item);
													break;
													
												default:
													break;
										}
								
							}
								if(editionMode != null) {
		    						Integer _idTypeFormation = null;
				 					switch (editionMode) {
				 								// Edition Offre de formation
							 					case OfConstants.OF_COMPONENT_EDIT_MODE:
								 						if(ofDto != null && ofDto.getIdDomaine() != null)
								 							_idTypeFormation = ofDto.getIdTypeFormation();
							 							initListCyclesByTypeFormation(_idTypeFormation);
							 							selectedCycleId = ofDto.getIdCycle();
													break;
													// Consultation Offre de formation
							 					case OfConstants.OF_COMPONENT_DETAIL_MODE:
							 						if(ofDto != null && ofDto.getIdDomaine() != null)
							 							_idTypeFormation = ofDto.getIdTypeFormation();
							 						initListCyclesByTypeFormation(_idTypeFormation);
							 						selectedCycleId = ofDto.getIdCycle();
							 						break;
													
												// Nouvelle Offre de formation	
							 					case OfConstants.OF_COMPONENT_NEW_MODE:
								 						if(selectedCycleId == null)
								 							_idTypeFormation = Integer.parseInt(listTypesFormation.get(0).getValue().toString());
								 						else
								 							_idTypeFormation = selectedTypeFormationId;
								 						
							 							initListCyclesByTypeFormation(_idTypeFormation);
							 							selectedCycleId = Integer.parseInt(listCycles.get(0).getValue().toString());
							 						break;
												default:
													break;
									}
				 					if(selectedCycleId != null)
				 						selectedCycleDto = cycleService.findById(selectedCycleId);
				 					
		    					}
							
						}
						catch(Exception e) {
						
							 e.printStackTrace();
						}
			    	
			    }

			    /**
			     * [OfBB.getSelectedPartners] Method 
			     * @author rlaib  on : 9 avr. 2014  10:18:55
			     * @return
			     */
			    @SuppressWarnings({"unchecked" })
				private List<OffreFormationPartenaireDto>  getSelectedPartners() {
			    	
			    	List<OffreFormationPartenaireDto> selectedObjects = new ArrayList<OffreFormationPartenaireDto>();
			    	
			    	try {
								
			    				int size = this.partnersDataTable.getRowCount();
			    				List<RefPartenaireDto>  data= (List<RefPartenaireDto>) this.partnersDataTable.getValue();
			    				
			    				for (int i = 0; i < size; i++) {
			    					
				    					this.partnersDataTable.setRowIndex(i);
				    					
				    					if(this.isSelectedPartner.isSelected()){
				    						
				    								RefPartenaireDto refItem = data.get(i);
				    								OffreFormationPartenaireDto item = new OffreFormationPartenaireDto();
				    								
				    								item.setDateCreation(new java.util.Date());
				    								String libelleFr ="";
				    								if(refItem.getLlGroupe() != null) 
				    										libelleFr += refItem.getLlGroupe();
				    								
				    								if(refItem.getLlStructureLatin() != null) 
				    										libelleFr += refItem.getLlStructureLatin();
				    								
				    								if(refItem.getLlStructureLatin() != null) 
				    										libelleFr += refItem.getLlStructureLatin();
				    								
				    								if(refItem.getPrenomLatin() != null) 
				    									libelleFr += refItem.getPrenomLatin();
				    								
				    								if(refItem.getNomLatin() != null) 
				    									libelleFr += refItem.getNomLatin();
				    								
				    								String refCode ="";
				    								
				    								if(refItem.getIdIndividu() != null) 
				    									refCode += refItem.getIdIndividu();
			    							
				    								if(refItem.getIdGroupe() != null) 
				    									refCode += refItem.getIdGroupe();
				    								
				    								if(refItem.getIdStructure() != null) 
				    									refCode += refItem.getIdStructure();
				    								
				    								item.setLibelleFr(libelleFr);
				    								item.setRefCodePartenaire(refCode);
				    								item.setTypePartenaire(refItem.getCodeRole());
				    								
				    								selectedObjects.add(item);
				    					}
			    				}
			    	}
			    	catch (Exception e) {
			
							e.printStackTrace();
					}
			    	
			    	return selectedObjects;
			    }
			    
			    /**
			     * [OfBB.getCurrentOfContract] Method 
			     * @author rlaib  on : 9 avr. 2014  10:19:03
			     * @param refContratDto
			     * @return
			     */
			    private OffreFormationContratDto getCurrentOfContract(RefContratDto refContratDto){
			    	
			    		OffreFormationContratDto contract = new OffreFormationContratDto();
			    		
			    		try {
			    					
			    			contract.setRefCodeContrat(refContratDto.getIdentifiant());
			    			contract.setReference_contrat(refContratDto.getReferenceDocumentaire());
			    			contract.setLibelleFr(refContratDto.getObjetContratFr());
			    			contract.setLibelleAr(refContratDto.getObjetContratAr());
			    			contract.setDateCreation(new java.util.Date());
			    		
			    		}
			    		catch (Exception e) {
						
							e.printStackTrace();
			    		}
			    		
			    		return contract;
			    }
			    
			    /**
			     * [OfBB.processOfPartners] Method 
			     * @author rlaib  on : 9 avr. 2014  10:19:08
			     * @param of
			     * @return
			     */
			    @SuppressWarnings("unused")
				private List<OffreFormationPartenaireDto> processOfPartners(OffreFormationDto of){
			    		
			    			List<OffreFormationPartenaireDto> result = new ArrayList<OffreFormationPartenaireDto>();
//			    			for (OffreFormationContratDto offreFormationContratDto : of.getOffreFormationContratDtos()) {
//			    				
//			    				for (OffreFormationPartenaireDto offreFormationPartenaireDto : offreFormationContratDto.getOffreFormationPartenaireDtos()) 
//			    							result.add(offreFormationPartenaireDto);
//								
//							}
			    			
			    			return result;
			    }
			    
		
			    private String getNomenclatureLabelByCode(String code, List<SelectItem> list) {
			    	
			    	if (code== null || list == null)
			    	
			    		return "";
			    	String result = "";
			    	try {
			    		
			    				for (SelectItem selectItem : list) {
			    					if(selectItem.getValue().toString().equals(code))
			    						result = selectItem.getLabel();
								}
			    	}
			    	catch (Exception e) {
	    				e.printStackTrace();
			    	}
			    	
			    	return result;
			    	
			    }
			    
			    /**
				 * [OfBB.initListDomainesLMD] Method 
				 * @author rlaib  on : 12 ao�t 2014  10:19:14
				 */
				private void initListDomainesLMD() {
					
					List<RefAffectDomLmdEtabDto> _list = this.getDomainesLMDByEtab(idEtab);
			    	try {
			    				listDomaines = new ArrayList<SelectItem>();
			    				if(_list != null && _list.size()>0) {
			    					for (RefAffectDomLmdEtabDto item : _list) {
		    							listDomaines.add(new SelectItem(item.getIdDomaineLMD(),item.getLlDomaine() + " ("+item.getIdentifiantDomaineLMD()+ ")"));
			    					}
			    					if(editionMode != null) {
			    						Integer _idDomaine = null;
					 					switch (editionMode) {
					 								// Edition Offre de formation
								 					case OfConstants.OF_COMPONENT_EDIT_MODE:
								 						if(ofDto != null && ofDto.getIdDomaine() != null)
								 							_idDomaine = ofDto.getIdDomaine();
														break;
														// Consultation Offre de formation
								 					case OfConstants.OF_COMPONENT_DETAIL_MODE:
								 						if(ofDto != null && ofDto.getIdDomaine() != null)
								 							_idDomaine = ofDto.getIdDomaine();
								 						break;
														
													// Nouvelle Offre de formation	
								 					case OfConstants.OF_COMPONENT_NEW_MODE:
								 						_idDomaine = _list.get(0).getIdDomaineLMD();
								 						break;
													default:
														break;
										}
					 					if(_idDomaine!= null)
					 						initFilieresByDomaine(_idDomaine);
					 					else {
					 						_idDomaine = Integer.parseInt(listDomaines.get(0).getValue().toString());
					 						initFilieresByDomaine(_idDomaine);
					 					}
			    					}
			    					
			    				}
			    	}
			    	catch (Exception e) {
	    				e.printStackTrace();
			    	}
				}
				
						
				/**
				 * [OfBB.initFilieresByDomaine] Method 
				 * @author rlaib  on : 8 sept. 2014  08:58:20
				 * @param codeDomaine
				 */
				private void initFilieresByDomaine(Integer idDomaine) {
					
					try {
							List<RefFiliereLmdDto> _list = refFiliereLmdService.findByIdDomainelmd(idDomaine);
							listFilieres = new ArrayList<SelectItem>();
							if(_list != null && _list.size()>0) {
								
	    						for (RefFiliereLmdDto refFiliereLmdDto : _list) {
	    								listFilieres.add(new SelectItem(refFiliereLmdDto.getId(),refFiliereLmdDto.getLlFiliere() + " ("+refFiliereLmdDto.getIdentifiant() + ")"));
	    						}
	    						if(editionMode != null) {
	    								Integer _idFiliere = null;
	    								switch (editionMode) {
			 								// Edition Offre de formation
						 					case OfConstants.OF_COMPONENT_EDIT_MODE:
						 						if(ofDto != null && ofDto.getIdFiliere() != null)
						 							_idFiliere = ofDto.getIdFiliere();
												break;
												
											// Nouvelle Offre de formation	
						 					case OfConstants.OF_COMPONENT_NEW_MODE:
						 						_idFiliere = _list.get(0).getId();
						 						break;
											default:
												break;
	    								}
	    								if(_idFiliere!= null )
	    										initSpecialitesByFiliere(_idFiliere);
	    								else {
	    									_idFiliere = Integer.parseInt(listFilieres.get(0).getValue().toString());
	    									initSpecialitesByFiliere(_idFiliere);
	    								}
	    						}
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				/**
				 * [OfBB.initSpecialitesByFiliere] Method 
				 * @author rlaib  on : 8 sept. 2014  08:59:19
				 * @param codeFiliere
				 */
				private void initSpecialitesByFiliere(Integer idFiliere) {
					
					try {
								List<RefSpecialiteLmdDto> _list = refSpecialiteLmdService.findByIdFilierelmd(idFiliere);
								listSpecialites = new ArrayList<SelectItem>();
										if(_list != null && _list.size()>0) {
												for (RefSpecialiteLmdDto refSpecialiteLmdDto : _list) {
														listSpecialites.add(new SelectItem(refSpecialiteLmdDto.getId(),refSpecialiteLmdDto.getLlSpecialite()+ " ("+refSpecialiteLmdDto.getIdentifiant() + ")"));
												}
										}
								}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				/**
				 * [OfBB.initListCycles] Method 
				 * @author rlaib  on : 8 sept. 2014  08:59:30
				 */
				@SuppressWarnings("unused")
				private void initListCycles() {
					
					List<CycleDto> _list = cycleService.findAll();
					try {
						listCycles = new ArrayList<SelectItem>();
						for (CycleDto cycleDto : _list) {
							listCycles.add(new SelectItem(cycleDto.getId(),cycleDto.getLibelleLongLt()));
						}

					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				/**
				 * [OfBB.initListCyclesByTypeFormation] Method 
				 * @author rlaib  on : 8 sept. 2014  08:59:42
				 * @param codeTypeFormation
				 */
				private void initListCyclesByTypeFormation(Integer idTypeFormation) {
					
					NomenclatureDto _typeFormation = nomenclatureService.findById(idTypeFormation);
					List<CycleDto> _list = cycleService.findByTypeFormationCode(_typeFormation.getCode());
					try {
						listCycles = new ArrayList<SelectItem>();
						for (CycleDto cycleDto : _list) {
							listCycles.add(new SelectItem(cycleDto.getId(),cycleDto.getLibelleLongLt()));
						}
						
						if(_list != null && _list.size()>0)
							selectedCycleId = _list.get(0).getId();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				public List<RefAffectDomLmdEtabDto> getDomainesLMDByEtab(Integer idEtab)  {
					
					List<RefAffectDomLmdEtabDto> result = new ArrayList<RefAffectDomLmdEtabDto>();
					try {
						
						result = refDomaineLMDService.findDomainesLMDByEtablissement(idEtab);
					}
					
					catch (Exception e) {
						e.printStackTrace();
					}
					
					return result;
					
				}
				
				public OffreFormationDetailContentDto getNodeContentDataById(int id) {
					
					try {
								return offreFormationDetailContentService.findById(id);
						
					}
					catch (Exception e) {
						
						return null;
					}
					
				}
				
				public NomenclatureDto getNomenclatureObjectByCodeByType(String ncName, String objectCode){
					
					NomenclatureDto objectToReturn = new NomenclatureDto();
					try  {
								List<NomenclatureDto> ncList = nomenclatureService.findByName(ncName);
								for (NomenclatureDto item : ncList) {
									if(item.getCode().equals(objectCode))
										return item;
								}
								
						
					}
					catch (Exception e) {
						
						return null;
					}
					return objectToReturn;
				}
				
				public RefContratDto getContractByCode(String codeContract)  {
					
					RefContratDto result = new RefContratDto();
					try {
							result =  refContratService.findByCode(codeContract);
					}
					catch (Exception e){
						
								e.printStackTrace();
					}
					return result;
		}
				
				public List<RefPartenaireDto> getPartnersByContractCode(String codeContract)  {
					
					List<RefPartenaireDto> result = new ArrayList<RefPartenaireDto>();
					
					try {
								result =  refPartenaireService.findByCodeContrat(codeContract);
					}
					catch (Exception e){
						
								e.printStackTrace();
					}
					return result;
		}
				
				public List<RefIndividuDto> searchPerson(String text)  {
					
					 List<RefIndividuDto> result = new ArrayList<RefIndividuDto>();
					 
					 try {
						 		result =  refIndividuService.findGeneric(text);
					 }
					 catch (Exception e){
						 	
						 	   e.printStackTrace();
					 }
					return result;
		}
				
				public List<RefContratDto> searchContract(String text)  {
					
					List<RefContratDto> result = new ArrayList<RefContratDto>();
					
					try {
								result =  refContratService.findGeneric(text,false);
					}
					catch (Exception e){
						
								e.printStackTrace();
					}
					return result;
		}
				
				public RefIndividuDto getPersonByNIN(String ninPerson)  {
					
					RefIndividuDto result = new RefIndividuDto();
					
					try {
								result =  refIndividuService.findByIdentifiant(ninPerson);
								
					}
					catch (Exception e){
						
								e.printStackTrace();
					}
					return result;
		}
				
				public OffreFormationDetailContentDto updateNodeContentDataById(OffreFormationDetailContentDto obj) {
					
					try {
								return offreFormationDetailContentService.insertOrUpdate(obj);
						
					}
					catch (Exception e) {
						
						return new OffreFormationDetailContentDto();
					}
					
				}
				
				public List<RefFiliereLmdDto> getAllFilieresLMD()  {
					
					List<RefFiliereLmdDto> result = new ArrayList<RefFiliereLmdDto>();
					try {
						
						result = refFiliereLmdService.findAll();
					}
					
					catch (Exception e) {
						e.printStackTrace();
					}
					
					return result;
					
				}
				
				public List<RefFiliereLmdDto> getFilieresLMDByDomaine(String codeDomaine)  {
					
					List<RefFiliereLmdDto> result = new ArrayList<RefFiliereLmdDto>();
					try {
						
						result = refFiliereLmdService.findByCodeDomainelmd(codeDomaine);
					}
					
					catch (Exception e) {
						e.printStackTrace();
					}
					
					return result;
					
				}
				
				public List<RefSpecialiteLmdDto> getSpecialitesByFiliere(String codeFiliere)  {
					
					List<RefSpecialiteLmdDto> result = new ArrayList<RefSpecialiteLmdDto>();
					try {
						
						result = refSpecialiteLmdService.findByCodeFilierelmd(codeFiliere);
					}
					
					catch (Exception e) {
						e.printStackTrace();
					}
					
					return result;
					
				}
				
				/**
				 * [OfBB.initOfRepartition] Method 
				 * @author rlaib  on : 11 sept. 2014  12:11:59
				 */
				private void initOfRepartition() {

					try {
							if(this.parcoursTypes != null && this.parcoursTypes.size()>0) {
								
									this.selectedParcoursId = String.valueOf(this.parcoursTypes.get(0).getId());
						 			if (selectedCycleId != null)  {
						 				
										this.listLevels = niveauService.findByCycleId(selectedCycleId);
										periodesNiveaux = new HashMap<Integer, List<PeriodeDto>>();
										repartitionsPeriodesNiveaux = new HashMap<Integer, Map<Integer,List<RepartitionUeDto>>>();
										totauxPeriodes = new HashMap<Integer, Integer>();
										int nbRepartition = 0;
										this.editableCycle = false;
										
										for (NiveauDto item : listLevels) {
											periodesNiveaux.put(item.getId(), periodeService.findByLevelId(item.getId()));
											List<PeriodeDto> _listPeriodes = periodeService.findByLevelId(item.getId());
											Map<Integer, List<RepartitionUeDto>> _repartitionsPeriodes = new HashMap<Integer, List<RepartitionUeDto>>();
											
											for (PeriodeDto periodeDto : _listPeriodes) {
													List<RepartitionUeDto> _repartition = repartitionUeService.findByParcoursAndPeriode(Integer.parseInt(selectedParcoursId), periodeDto.getId());
													_repartitionsPeriodes.put(periodeDto.getId(), _repartition);
													int totalCreditsPeriode= 0;
													if(_repartition != null) {
															nbRepartition = nbRepartition + _repartition.size();
															for (RepartitionUeDto repartitionUeDto : _repartition) {
																	if(repartitionUeDto.getUniteEnseignementCredits() != null)
																		totalCreditsPeriode = totalCreditsPeriode + repartitionUeDto.getUniteEnseignementCredits();
															}
													}
													totauxPeriodes.put(periodeDto.getId(), totalCreditsPeriode);
											}
											repartitionsPeriodesNiveaux.put(item.getId(), _repartitionsPeriodes);
										}
										if (nbRepartition > 0)
											this.editableCycle = true;
						 			}
							}
						}
						catch (Exception e) {
								e.printStackTrace();
						}
					
				}

				/**
				 * [OfBB.initSessionInfos] Method 
				 * @author rlaib  on : 10 sept. 2014  16:49:49
				 */
				private void initSessionInfos() {
					try {
							this.idEtab = sessionBean.getIdEtablissement();
						 	this.oldEtabCode= this.sessionBean.getAncienCodeEtablissement();
				 			this.libelleEtab=this.sessionBean.getLlLatinEtablissement();
				 			this.newEtabCode = this.sessionBean.getCodeEtablissement();
				 			this.idYear = this.sessionBean.getIdAnneeAcademique();
					 }
					 catch(Exception e){
						 
						 e.printStackTrace();
					 
					 }
				}

				/**
				 * [OfBB.initParams] Method 
				 * @author rlaib  on : 10 sept. 2014  15:35:49
				 */
				private void initParams() {

					try {
								Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
								if(this.outcomePage == null) 
										this.outcomePage = params.get("outcomePage");
								if(this.editionMode == null) 
									this.editionMode = params.get("editionMode");
								if(this.searchMode == null) 
									this.searchMode = params.get("searchMode");
								
						 		if (searchMode != null && !searchMode.trim().isEmpty() && !searchMode.equals("null"))
						 			searchMode = OfConstants.OF_SEARCH_MODE_NORMAL;
			
								
								if(this.keyWords == null) 
									this.keyWords = params.get("keyWords");
								if(this.ofId == null) 
									this.ofId = params.get("ofId");
				 }
				 catch(Exception e){
					 
					 e.printStackTrace();
				 
				 }
					
				}

				
				/**
				 * [OfBB.initOfFacultes] Method 
				 * @author rlaib  on : 9 oct. 2014  15:44:49
				 */
				private void initOfFacultes(Integer _idEtablissement) {

						try {
									NomenclatureDto typeStrcutureFaculte = nomenclatureService.findByCode(OfConstants.NC_NAME_TYPE_STRUCTURE, 
													OfConstants.NC_TYPE_STRUCTURE_FACULTE_CODE);
									if(typeStrcutureFaculte == null)
										return;
									List<RefStructureDto> _listFacultes = refStructureService.findStructuresByTypeByEtab(_idEtablissement,typeStrcutureFaculte.getId());
									facultesOf = new ArrayList<SelectItem>();
									
									if(_listFacultes == null)
										return;
									SelectItem defaultSelection = null;
									if(_listFacultes != null && _listFacultes.size()>0) {
										defaultSelection =new SelectItem(0, MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, 
													OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_LIST_FACULTES_DEFAULT)
																		, null, false, false, true);
											for (RefStructureDto refStructureDto : _listFacultes) {
												facultesOf.add(new SelectItem(refStructureDto.getId(), refStructureDto.getLlStructureLatin()));
											}
//											selectedFaculteId = _listFacultes.get(0).getId();
									}
									else
										defaultSelection = new SelectItem(0, MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,
													OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_LIST_FACULTES_EMPTY)
												, null, false, false, true);
										
									facultesOf.add(0, defaultSelection);
									if(selectedFaculteId != null)
										initOfDepartements(idEtab,selectedFaculteId);
						}
						catch(Exception e){
								e.printStackTrace();
						}
				}
				
				/**
				 * [OfBB.initOfDepartement] Method 
				 * @author rlaib  on : 11 oct. 2014  13:51:01
				 */
				private void initOfDepartements(Integer _idEtablissement, Integer _idFaculte) {
					
					try {
						
						List<RefStructureDto> _listDepartements = refStructureService.findStructuresOfMasterStructureByEtab(_idEtablissement,_idFaculte);
						departementsOf = new ArrayList<SelectItem>();
						
						if(_listDepartements == null)
							return;
						
						SelectItem defaultSelection = null;
						if(_listDepartements != null && _listDepartements.size()>0) {
							defaultSelection =new SelectItem(0, MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME, 
										OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_LIST_DEPARTEMENTS_DEFAULT)
									, null, false, false, true);
							for (RefStructureDto refStructureDto : _listDepartements) {
								departementsOf.add(new SelectItem(refStructureDto.getId(), refStructureDto.getLlStructureLatin()));
							}
						}
						else
							defaultSelection = new SelectItem(0, MessageUtil.getStringValueFromBundleResource(OfConstants.OF_EDIT_BUNDLE_MSG_NAME,
									OfConstants.OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_LIST_DEPARTEMENTS_EMPTY)
									, null, false, false, true);
						
						departementsOf.add(0, defaultSelection);
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
				
				// endregion FUNTIONS HELPERS

}




