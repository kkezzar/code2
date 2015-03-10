/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.of.SearchOf.java] 
 * @author Rafik on : 26 févr. 2014  21:43:41
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.of;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;
import javax.sound.sampled.Line;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.MessageUtil;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.NcConverter;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.scolarite.chargepedagogique.FicheVoeuxBB;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;

/**
 * @author Rafik  on : 26 fevr. 2014  21:43:41
 */
@ManagedBean(name = "ofSearch")
@ViewScoped
public class OfSearch {
	
			// ===================================================================  
			// Constructors
			// ===================================================================
			// region CONTRUCTORS
			public OfSearch() {
				
			}
			
			@PostConstruct
			public void init() {
								initParams();
								initSessionInfos();
								initListDomainesLMD();
								showResultSearch = false;
								if(keyWords == null || keyWords.trim().equals("null"))
									keyWords = "";
								
								if(searchMode != null) {
								
									if(	searchMode.equals(OfConstants.OF_SEARCH_MODE_NORMAL)
											|| searchMode.equals(OfConstants.OF_SEARCH_MODE_ADVANCED) 
											|| searchMode.equals(OfConstants.OF_SEARCH_MODE_PAGING)) {
									 	search();
									}
				
								}
								showPanelSearchOf = true;
								showPanelDetailOf = false;
									
			}
			
			private void initParams() {

				Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			
				if(this.searchMode == null) 
					this.searchMode = params.get("searchMode");
				if(this.keyWords == null) 
					this.keyWords = params.get("keyWords");
				if(this.ofId == null) 
					this.ofId = params.get("ofId");
				
			}
			// endregion CONTRUCTORS
			
			// ===================================================================  
			// Parameters
			// ===================================================================
			// region PARAMS
//			@ManagedProperty("#{param.ofId}")
			private String ofId;
			
//			@ManagedProperty("#{param.keyWords}")
			private String keyWords;
			
//			@ManagedProperty("#{param.querySearch}")
			private String querySearch;
		
//			@ManagedProperty("#{param.searchMode}")
			private String searchMode;
			
//			@ManagedProperty("#{param.hiddenMode}")
			private String hiddenMode;
			// endregion PARAMS
			
			// ===================================================================  
			// Beans Services 
			// ===================================================================
			// region BEANS
			@ManagedProperty(value="#{offreFormationService}")
			private OffreFormationService offreFormationService;
			
			@ManagedProperty(value="#{sessionBeanFve}")
			private SessionBean sessionBean;
			
			@ManagedProperty(value="#{nomenclatureConverter}")
			private NcConverter nomenclatureConverter;
			
			@ManagedProperty(value="#{nomenclatureServiceImpl}")
			private NomenclatureService nomenclatureService;
			
			@ManagedProperty(value="#{refEtablissementServiceImpl}")
			private RefEtablissementService refEtablissementService;
			
			@ManagedProperty(value="#{refDomaineLMDServiceImpl}")
			private RefDomaineLMDService refDomaineLMDServiceImpl;
			
			@ManagedProperty(value="#{refFiliereLmdServiceImpl}")
			private RefFiliereLmdService refFiliereLmdServiceImpl;
			
			@ManagedProperty(value="#{refSpecialiteLmdServiceImpl}")
			private RefSpecialiteLmdService refSpecialiteLmdServiceImpl;
			
			@ManagedProperty(value="#{ofBB}")
			private OfBB ofBB;
			
			// endregion BEANS
			
			// ===================================================================  
			// Beans Services  Getters / Setters
			// ===================================================================
			//region BEANS SETTERS / GETTERS
			/**
			 * [OfSearch.refDomaineLMDServiceImpl] Getter 
			 * @author rlaib on : 17 nov. 2014  16:44:04
			 * @return the refDomaineLMDServiceImpl
			 */
			public RefDomaineLMDService getRefDomaineLMDServiceImpl() {
				return refDomaineLMDServiceImpl;
			}

			/**
			 * [OfSearch.refDomaineLMDServiceImpl] Setter 
			 * @author rlaib on : 17 nov. 2014  16:44:04
			 * @param refDomaineLMDServiceImpl the refDomaineLMDServiceImpl to set
			 */
			public void setRefDomaineLMDServiceImpl(
					RefDomaineLMDService refDomaineLMDServiceImpl) {
				this.refDomaineLMDServiceImpl = refDomaineLMDServiceImpl;
			}

			/**
			 * [OfSearch.refFiliereLmdServiceImpl] Getter 
			 * @author rlaib on : 17 nov. 2014  16:44:04
			 * @return the refFiliereLmdServiceImpl
			 */
			public RefFiliereLmdService getRefFiliereLmdServiceImpl() {
				return refFiliereLmdServiceImpl;
			}

			/**
			 * [OfSearch.refFiliereLmdServiceImpl] Setter 
			 * @author rlaib on : 17 nov. 2014  16:44:04
			 * @param refFiliereLmdServiceImpl the refFiliereLmdServiceImpl to set
			 */
			public void setRefFiliereLmdServiceImpl(
					RefFiliereLmdService refFiliereLmdServiceImpl) {
				this.refFiliereLmdServiceImpl = refFiliereLmdServiceImpl;
			}

			/**
			 * [OfSearch.refSpecialiteLmdServiceImpl] Getter 
			 * @author rlaib on : 17 nov. 2014  16:44:04
			 * @return the refSpecialiteLmdServiceImpl
			 */
			public RefSpecialiteLmdService getRefSpecialiteLmdServiceImpl() {
				return refSpecialiteLmdServiceImpl;
			}

			/**
			 * [OfSearch.refSpecialiteLmdServiceImpl] Setter 
			 * @author rlaib on : 17 nov. 2014  16:44:04
			 * @param refSpecialiteLmdServiceImpl the refSpecialiteLmdServiceImpl to set
			 */
			public void setRefSpecialiteLmdServiceImpl(
					RefSpecialiteLmdService refSpecialiteLmdServiceImpl) {
				this.refSpecialiteLmdServiceImpl = refSpecialiteLmdServiceImpl;
			}

			/**
			 * [SearchOf.offreFormationService] Getter 
			 * @author Rafik on : 26 févr. 2014  22:05:24
			 * @return the offreFormationService
			 */
			public OffreFormationService getOffreFormationService() {
				return offreFormationService;
			}

			/**
			 * [SearchOf.offreFormationService] Setter 
			 * @author Rafik on : 26 févr. 2014  22:05:24
			 * @param offreFormationService the offreFormationService to set
			 */
			public void setOffreFormationService(OffreFormationService offreFormationService) {
				this.offreFormationService = offreFormationService;
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
			 * [OfSearch.refEtablissementService] Getter 
			 * @author rlaib on : 11 oct. 2014  17:35:29
			 * @return the refEtablissementService
			 */
			public RefEtablissementService getRefEtablissementService() {
				return refEtablissementService;
			}

			/**
			 * [OfSearch.refEtablissementService] Setter 
			 * @author rlaib on : 11 oct. 2014  17:35:29
			 * @param refEtablissementService the refEtablissementService to set
			 */
			public void setRefEtablissementService(
					RefEtablissementService refEtablissementService) {
				this.refEtablissementService = refEtablissementService;
			}

			/**
			 * [SearchOf.nomenclatureConverter] Getter 
			 * @author Rafik on : 10 mars 2014  21:31:59
			 * @return the nomenclatureConverter
			 */
			public NcConverter getNomenclatureConverter() {
				if (nomenclatureConverter == null)
					nomenclatureConverter = new NcConverter();
				return nomenclatureConverter;
			}

			/**
			 * [SearchOf.nomenclatureConverter] Setter 
			 * @author Rafik on : 10 mars 2014  21:31:59
			 * @param nomenclatureConverter the nomenclatureConverter to set
			 */
			public void setNomenclatureConverter(NcConverter nomenclatureConverter) {
				this.nomenclatureConverter = nomenclatureConverter;
			}
			
			/**
			 * [OfSearch.ofBB] Getter 
			 * @author Rafik on : 4 déc. 2014  08:59:48
			 * @return the ofBB
			 */
			public OfBB getOfBB() {
				return ofBB;
			}

			/**
			 * [OfSearch.ofBB] Setter 
			 * @author Rafik on : 4 déc. 2014  08:59:48
			 * @param ofBB the ofBB to set
			 */
			public void setOfBB(OfBB ofBB) {
				this.ofBB = ofBB;
			}

			/**
			 * [OfSearch.nomenclatureService] Getter 
			 * @author rlaib on : 13 oct. 2014  09:54:32
			 * @return the nomenclatureService
			 */
			public NomenclatureService getNomenclatureService() {
				return nomenclatureService;
			}

			/**
			 * [OfSearch.nomenclatureService] Setter 
			 * @author rlaib on : 13 oct. 2014  09:54:32
			 * @param nomenclatureService the nomenclatureService to set
			 */
			public void setNomenclatureService(NomenclatureService nomenclatureService) {
				this.nomenclatureService = nomenclatureService;
			}
			//endregion BEANS SETTERS / GETTERS
			
			// ===================================================================  
			// Properties Model
			// ===================================================================
			// region PROPERTIES MODEL
			private List<OffreFormationDto> result;
			private DataModel<OffreFormationDto> lazyResult;
			private OffreFormationDto selectedOf;
			private OffreFormationDto dtoSearch;
			private List<SelectItem> listDomaines;
			private List<SelectItem> listFilieres;
			private List<SelectItem> listSpecialites;
			private List<SelectItem> listDomainesAr;
			private List<SelectItem> listFilieresAr;
			private List<SelectItem> listSpecialitesAr;
			private String selectedDomaine;
			private String selectedFiliere;
			private String selectedSpecialite;
			private boolean showExportDataButtons;
			private boolean showResultSearch = false; 
			private boolean showPanelSearchOf;
			private boolean showPanelDetailOf;
			// Session Infos
			private Integer idYear;
			private Integer idEtab;
			private String oldEtabCode;
			private String newEtabCode;
			private String libelleEtab;
			// Prefs Utilisateur
			private Integer fontSize;
			
			// endregion PROPERTIES MODEL
			
			// ===================================================================  
			// Parameter's Getters / Setters
			// ===================================================================
			// region PARMS GETTERS / SETTERS
			/**
			 * [SearchOf.keyWords] Getter 
			 * @author Rafik on : 26 févr. 2014  21:53:17
			 * @return the keyWords
			 */
			public String getKeyWords() {
			
				if(keyWords == null)
					keyWords = "";
				return keyWords;
			}

			/**
			 * [SearchOf.keyWords] Setter 
			 * @author Rafik on : 26 févr. 2014  21:53:17
			 * @param keyWords the keyWords to set
			 */
			public void setKeyWords(String keyWords) {
				this.keyWords = keyWords;
			}

			/**
			 * [SearchOf.querySearch] Getter 
			 * @author rlaib on : 3 mars 2014  10:18:17
			 * @return the querySearch
			 */
			public String getQuerySearch() {
				return querySearch;
			}

			/**
			 * [SearchOf.querySearch] Setter 
			 * @author rlaib on : 3 mars 2014  10:18:17
			 * @param querySearch the querySearch to set
			 */
			public void setQuerySearch(String querySearch) {
				this.querySearch = querySearch;
			}

			/**
			 * [SearchOf.searchMode] Getter 
			 * @author rlaib on : 3 mars 2014  14:21:21
			 * @return the searchMode
			 */
			public String getSearchMode() {
				
				if(searchMode == null)
					searchMode = "";
				return searchMode;
			}

			/**
			 * [SearchOf.searchMode] Setter 
			 * @author rlaib on : 3 mars 2014  14:21:21
			 * @param searchMode the searchMode to set
			 */
			public void setSearchMode(String searchMode) {
				this.searchMode = searchMode;
			}

			/**
			 * [SearchOf.hiddenMode] Getter 
			 * @author rlaib on : 3 mars 2014  16:47:24
			 * @return the hiddenMode
			 */
			public String getHiddenMode() {
				return hiddenMode;
			}

			/**
			 * [SearchOf.hiddenMode] Setter 
			 * @author rlaib on : 3 mars 2014  16:47:24
			 * @param hiddenMode the hiddenMode to set
			 */
			public void setHiddenMode(String hiddenMode) {
				this.hiddenMode = hiddenMode;
			}
			
			/**
			 * [SearchOf.ofId] Getter 
			 * @author rlaib on : 23 mars 2014  10:19:05
			 * @return the ofId
			 */
			public String getOfId() {
				return ofId;
			}

			/**
			 * [SearchOf.ofId] Setter 
			 * @author rlaib on : 23 mars 2014  10:19:05
			 * @param ofId the ofId to set
			 */
			public void setOfId(String ofId) {
				this.ofId = ofId;
			}
			// endregion PARMS GETTERS / SETTERS
			
			// ===================================================================  
			// Properties Model Getters / Setters
			// ===================================================================
			// region PROPERTIES MODEL GETTERS / SETTTERS
			
			/**
			 * [SearchOf.result] Getter 
			 * @author Rafik on : 26 févr. 2014  21:53:48
			 * @return the result
			 */
			public List<OffreFormationDto> getResult() {
				return result;
			}

			/**
			 * [OfSearch.fontSize] Getter 
			 * @author Rafik on : 6 déc. 2014  17:51:05
			 * @return the fontSize
			 */
			public Integer getFontSize() {
				return fontSize;
			}

			/**
			 * [OfSearch.fontSize] Setter 
			 * @author Rafik on : 6 déc. 2014  17:51:05
			 * @param fontSize the fontSize to set
			 */
			public void setFontSize(Integer fontSize) {
				this.fontSize = fontSize;
			}

			/**
			 * [SearchOf.result] Setter 
			 * @author Rafik on : 26 févr. 2014  21:53:48
			 * @param result the result to set
			 */
			public void setResult(List<OffreFormationDto> result) {
				this.result = result;
			}
			
			/**
			 * [SearchOf.lazyResult] Getter 
			 * @author rlaib on : 4 mars 2014  10:10:16
			 * @return the lazyResult
			 */
			public DataModel<OffreFormationDto> getLazyResult() {
				return lazyResult;
			}

			/**
			 * [SearchOf.lazyResult] Setter 
			 * @author rlaib on : 4 mars 2014  10:10:16
			 * @param lazyResult the lazyResult to set
			 */
			public void setLazyResult(DataModel<OffreFormationDto> lazyResult) {
				this.lazyResult = lazyResult;
			}

			/**
			 * [SearchOf.selectedOf] Getter 
			 * @author rlaib on : 4 mars 2014  16:43:06
			 * @return the selectedOf
			 */
			public OffreFormationDto getSelectedOf() {
				
				if(selectedOf ==  null)
					selectedOf  = new  OffreFormationDto();
				return selectedOf;
			}

			/**
			 * [SearchOf.selectedOf] Setter 
			 * @author rlaib on : 4 mars 2014  16:43:06
			 * @param selectedOf the selectedOf to set
			 */
			public void setSelectedOf(OffreFormationDto selectedOf) {
				this.selectedOf = selectedOf;
			}

			/**
			 * [SearchOf.dtoSearch] Getter 
			 * @author Rafik on : 26 févr. 2014  22:19:24
			 * @return the dtoSearch
			 */
			public OffreFormationDto getDtoSearch() {
				
				if(dtoSearch == null)
					dtoSearch = new OffreFormationDto();
				return dtoSearch;
			}

			/**
			 * [SearchOf.dtoSearch] Setter 
			 * @author Rafik on : 26 févr. 2014  22:19:24
			 * @param dtoSearch the dtoSearch to set
			 */
			public void setDtoSearch(OffreFormationDto dtoSearch) {
				this.dtoSearch = dtoSearch;
			}

			/**
			 * [SearchOf.listDomaines] Getter 
			 * @author rlaib on : 27 f�vr. 2014  17:48:07
			 * @return the listDomaines
			 */
			public List<SelectItem> getListDomaines() {
				
				return listDomaines;
			}

			/**
			 * [SearchOf.listDomaines] Setter 
			 * @author rlaib on : 27 f�vr. 2014  17:48:07
			 * @param listDomaines the listDomaines to set
			 */
			public void setListDomaines(List<SelectItem> listDomaines) {
				this.listDomaines = listDomaines;
			}

			/**
			 * [SearchOf.listFilieres] Getter 
			 * @author rlaib on : 27 f�vr. 2014  11:28:45
			 * @return the listFilieres
			 */
			public List<SelectItem> getListFilieres() {
				
				return listFilieres;
			}
		
			/**
			 * [SearchOf.listFilieres] Setter 
			 * @author rlaib on : 27 f�vr. 2014  11:28:45
			 * @param listFilieres the listFilieres to set
			 */
			public void setListFilieres(List<SelectItem> listFilieres) {
				this.listFilieres = listFilieres;
			}

			/**
			 * [SearchOf.listSpecialites] Getter 
			 * @author rlaib on : 27 f�vr. 2014  11:05:54
			 * @return the listSpecialites
			 */
			public List<SelectItem> getListSpecialites() {
				
				return listSpecialites;
			}

			/**
			 * [SearchOf.listSpecialites] Setter 
			 * @author rlaib on : 27 f�vr. 2014  11:05:54
			 * @param listSpecialites the listSpecialites to set
			 */
			public void setListSpecialites(List<SelectItem> listSpecialites) {
				this.listSpecialites = listSpecialites;
			}
			
			/**
			 * [SearchOf.listDomainesAr] Getter 
			 * @author  Rafik LAIB on : 4 avr. 2014  19:56:43
			 * @return the listDomainesAr
			 */
			public List<SelectItem> getListDomainesAr() {
				return listDomainesAr;
			}

			/**
			 * [SearchOf.listDomainesAr] Setter 
			 * @author  Rafik LAIB on : 4 avr. 2014  19:56:43
			 * @param listDomainesAr the listDomainesAr to set
			 */
			public void setListDomainesAr(List<SelectItem> listDomainesAr) {
				this.listDomainesAr = listDomainesAr;
			}

			/**
			 * [SearchOf.listFilieresAr] Getter 
			 * @author  Rafik LAIB on : 4 avr. 2014  19:56:43
			 * @return the listFilieresAr
			 */
			public List<SelectItem> getListFilieresAr() {
				return listFilieresAr;
			}

			/**
			 * [SearchOf.listFilieresAr] Setter 
			 * @author  Rafik LAIB on : 4 avr. 2014  19:56:43
			 * @param listFilieresAr the listFilieresAr to set
			 */
			public void setListFilieresAr(List<SelectItem> listFilieresAr) {
				this.listFilieresAr = listFilieresAr;
			}

			/**
			 * [SearchOf.listSpecialitesAr] Getter 
			 * @author  Rafik LAIB on : 4 avr. 2014  19:56:43
			 * @return the listSpecialitesAr
			 */
			public List<SelectItem> getListSpecialitesAr() {
				return listSpecialitesAr;
			}

			/**
			 * [SearchOf.listSpecialitesAr] Setter 
			 * @author  Rafik LAIB on : 4 avr. 2014  19:56:43
			 * @param listSpecialitesAr the listSpecialitesAr to set
			 */
			public void setListSpecialitesAr(List<SelectItem> listSpecialitesAr) {
				this.listSpecialitesAr = listSpecialitesAr;
			}

			/**
			 * [SearchOf.selectedDomaine] Getter 
			 * @author rlaib on : 27 f�vr. 2014  17:48:27
			 * @return the selectedDomaine
			 */
			public String getSelectedDomaine() {
				
				System.err.println(String.valueOf("[R2-SII-MESRS] : getSelectedDomaine selectedDomaine  = " + this.selectedDomaine));
				return selectedDomaine;
			}

			/**
			 * [SearchOf.selectedDomaine] Setter 
			 * @author rlaib on : 27 f�vr. 2014  17:48:27
			 * @param selectedDomaine the selectedDomaine to set
			 */
			public void setSelectedDomaine(String selectedDomaine) {
				this.selectedDomaine = selectedDomaine;
				System.err.println(String.valueOf("[R2-SII-MESRS] : setSelectedDomaine selectedDomaine  = " + this.selectedDomaine));
			}

			/**
			 * [SearchOf.selectedFiliere] Getter 
			 * @author rlaib on : 27 f�vr. 2014  11:28:59
			 * @return the selectedFiliere
			 */
			public String getSelectedFiliere() {
				
				System.err.println(String.valueOf("[R2-SII-MESRS] : getSelectedFiliere selectedFiliere  = " + this.selectedFiliere));
				return selectedFiliere;
			}
		
			/**
			 * [SearchOf.selectedFiliere] Setter 
			 * @author rlaib on : 27 f�vr. 2014  11:28:59
			 * @param selectedFiliere the selectedFiliere to set
			 */
			public void setSelectedFiliere(String selectedFiliere) {
			
				this.selectedFiliere = selectedFiliere;
				System.err.println(String.valueOf("[R2-SII-MESRS] : setSelectedFiliere selectedFiliere  = " + this.selectedFiliere));
			
			}

			/**
			 * [SearchOf.selectedSpecialite] Getter 
			 * @author rlaib on : 27 f�vr. 2014  11:05:54
			 * @return the selectedSpecialite
			 */
			public String getSelectedSpecialite() {
				
				System.err.println(String.valueOf("[R2-SII-MESRS] : getSelectedSpecialite selectedSpecialite  = " + this.selectedSpecialite));
				return selectedSpecialite;
			}

			/**
			 * [SearchOf.selectedSpecialite] Setter 
			 * @author rlaib on : 27 f�vr. 2014  11:05:54
			 * @param selectedSpecialite the selectedSpecialite to set
			 */
			public void setSelectedSpecialite(String selectedSpecialite) {
				
				this.selectedSpecialite = selectedSpecialite;
				System.err.println(String.valueOf("[R2-SII-MESRS] : setSelectedSpecialite selectedSpecialite  = " + this.selectedSpecialite));
				
			}
			
			
			/**
			 * [OfSearch.showResultSearch] Getter 
			 * @author rlaib on : 16 nov. 2014  17:41:09
			 * @return the showResultSearch
			 */
			public boolean isShowResultSearch() {
				return showResultSearch;
			}

			/**
			 * [OfSearch.showResultSearch] Setter 
			 * @author rlaib on : 16 nov. 2014  17:41:09
			 * @param showResultSearch the showResultSearch to set
			 */
			public void setShowResultSearch(boolean showResultSearch) {
				this.showResultSearch = showResultSearch;
			}

			/**
			 * [OfSearch.showPanelSearchOf] Getter 
			 * @author Rafik on : 3 déc. 2014  22:26:46
			 * @return the showPanelSearchOf
			 */
			public boolean isShowPanelSearchOf() {
				return showPanelSearchOf;
			}

			/**
			 * [OfSearch.showPanelSearchOf] Setter 
			 * @author Rafik on : 3 déc. 2014  22:26:46
			 * @param showPanelSearchOf the showPanelSearchOf to set
			 */
			public void setShowPanelSearchOf(boolean showPanelSearchOf) {
				this.showPanelSearchOf = showPanelSearchOf;
			}

			/**
			 * [OfSearch.showPanelDetailOf] Getter 
			 * @author Rafik on : 3 déc. 2014  22:26:46
			 * @return the showPanelDetailOf
			 */
			public boolean isShowPanelDetailOf() {
				return showPanelDetailOf;
			}

			/**
			 * [OfSearch.showPanelDetailOf] Setter 
			 * @author Rafik on : 3 déc. 2014  22:26:46
			 * @param showPanelDetailOf the showPanelDetailOf to set
			 */
			public void setShowPanelDetailOf(boolean showPanelDetailOf) {
				this.showPanelDetailOf = showPanelDetailOf;
			}

			/**
			 * [SearchOf.showExportDataButtons] Getter 
			 * @author Rafik on : 15 mars 2014  20:56:51
			 * @return the showExportDataButtons
			 */
			@SuppressWarnings("unchecked")
			public boolean isShowExportDataButtons() {
				
				try {
					int sizeResult = ((List<OffreFormationDto>) lazyResult.getWrappedData()).size() ;
					showExportDataButtons = (sizeResult > 0);
					
				}
				catch (Exception e) {
					
					showExportDataButtons = false;
				}
				return showExportDataButtons;
			}
			
			/**
			 * [SearchOf.showExportDataButtons] Setter 
			 * @author rlaib on : 16 mars 2014  16:57:05
			 * @param showExportDataButtons the showExportDataButtons to set
			 */
			public void setShowExportDataButtons(boolean showExportDataButtons) {
				this.showExportDataButtons = showExportDataButtons;
			}
			
			/**
			 * [OfSearch.idYear] Getter 
			 * @author rlaib on : 10 sept. 2014  16:51:52
			 * @return the idYear
			 */
			public Integer getIdYear() {
				return idYear;
			}

			/**
			 * [OfSearch.idYear] Setter 
			 * @author rlaib on : 10 sept. 2014  16:51:52
			 * @param idYear the idYear to set
			 */
			public void setIdYear(Integer idYear) {
				this.idYear = idYear;
			}

			/**
			 * [OfSearch.idEtab] Getter 
			 * @author rlaib on : 10 sept. 2014  16:51:52
			 * @return the idEtab
			 */
			public Integer getIdEtab() {
				return idEtab;
			}

			/**
			 * [OfSearch.idEtab] Setter 
			 * @author rlaib on : 10 sept. 2014  16:51:52
			 * @param idEtab the idEtab to set
			 */
			public void setIdEtab(Integer idEtab) {
				this.idEtab = idEtab;
			}

			/**
			 * [OfSearch.oldEtabCode] Getter 
			 * @author rlaib on : 10 sept. 2014  16:51:52
			 * @return the oldEtabCode
			 */
			public String getOldEtabCode() {
				return oldEtabCode;
			}

			/**
			 * [OfSearch.oldEtabCode] Setter 
			 * @author rlaib on : 10 sept. 2014  16:51:52
			 * @param oldEtabCode the oldEtabCode to set
			 */
			public void setOldEtabCode(String oldEtabCode) {
				this.oldEtabCode = oldEtabCode;
			}

			/**
			 * [OfSearch.newEtabCode] Getter 
			 * @author rlaib on : 10 sept. 2014  16:51:52
			 * @return the newEtabCode
			 */
			public String getNewEtabCode() {
				return newEtabCode;
			}

			/**
			 * [OfSearch.newEtabCode] Setter 
			 * @author rlaib on : 10 sept. 2014  16:51:52
			 * @param newEtabCode the newEtabCode to set
			 */
			public void setNewEtabCode(String newEtabCode) {
				this.newEtabCode = newEtabCode;
			}

			/**
			 * [OfSearch.libelleEtab] Getter 
			 * @author rlaib on : 10 sept. 2014  16:51:52
			 * @return the libelleEtab
			 */
			public String getLibelleEtab() {
				return libelleEtab;
			}

			/**
			 * [OfSearch.libelleEtab] Setter 
			 * @author rlaib on : 10 sept. 2014  16:51:52
			 * @param libelleEtab the libelleEtab to set
			 */
			public void setLibelleEtab(String libelleEtab) {
				this.libelleEtab = libelleEtab;
			}
			// endregion PROPERTIES MODEL GETTERS / SETTTERS
			
			// ===================================================================  
			// ActionListeners
			// ===================================================================
			// region ACTIONLISTENERS
			/**
			 * [SearchOf.search] Method 
			 * @author rlaib  on : 6 mars 2014  10:24:19
			 */
			public void search() {
					
					try {
									if (keyWords != null && !keyWords.trim().equals("")) {
											result = offreFormationService.findGeneric(keyWords.trim(), this.idEtab); 
											showResultSearch = true;
											// DataTable Lazy Model
											lazyResult = new OfDataModel(result);
											hiddenMode = searchMode;
										}
 									
					}
					catch (Exception e) {
						
								e.printStackTrace();
					}
				
			}
			public void searchOfAdvanced() {
				
				try {
							if(dtoSearch != null) {
								result = offreFormationService.findAdvanced(dtoSearch, keyWords.trim(), this.idEtab); 
								showResultSearch = true;
								// DataTable Lazy Model
								lazyResult = new OfDataModel(result);
								hiddenMode = searchMode;
							}
									
				}
				catch (Exception e) {
					
							e.printStackTrace();
				}
			
		}

			/**
			 * [OfSearch.updateAllOfs] Method 
			 * @author rlaib  on : 11 oct. 2014  18:16:30
			 */
			@SuppressWarnings("unused")
			private void updateAllOfs() {
				List<OffreFormationDto> allOfs  = offreFormationService.findAll();
				long index = 0;
				for (OffreFormationDto offreFormationDto : allOfs) {
					
					RefEtablissementDto refEtablissementDto = refEtablissementService.findById(offreFormationDto.getIdEtablissement());
//									OffreFormationI18nDto offreFormationI18nDtoFr = offreFormationService.findI18nByOfId(offreFormationDto.getId(), "fr");
//									OffreFormationI18nDto offreFormationI18nDtoAr = offreFormationService.findI18nByOfId(offreFormationDto.getId(), "ar");
					offreFormationDto.setIdEtablissement(this.idEtab);
					if(refEtablissementDto != null) {
						offreFormationDto.setIdEtablissement(refEtablissementDto.getId());
					}
//									if(offreFormationI18nDtoFr != null) {
//											offreFormationDto.setLibelleLongFr(offreFormationI18nDtoFr.getLibelle());
//											offreFormationDto.setLibelleCourtFr(offreFormationI18nDtoFr.getLibelleCourt());
//											offreFormationDto.setDescription(offreFormationI18nDtoFr.getDescription());
//									}
//									else {
//										offreFormationDto.setLibelleLongFr("");
//										offreFormationDto.setLibelleCourtFr("");
//										offreFormationDto.setDescription("");
//									}
//									if(offreFormationI18nDtoFr != null) {
//											offreFormationDto.setLibelleLongAr(offreFormationI18nDtoAr.getLibelle());
//											offreFormationDto.setLibelleCourtAr(offreFormationI18nDtoAr.getLibelleCourt());
//									}
//									else {
//										offreFormationDto.setLibelleLongAr("");
//										offreFormationDto.setLibelleCourtAr("");
//									}
					index ++;
					offreFormationService.insertOrUpdate(offreFormationDto,null);
					System.err.println("OF mise a jour : " + String.valueOf(index));
				}
			}
		
			// endregion ACTIONLISTENERS
			
			// ===================================================================  
			// Listeners
			// ===================================================================
			// region LISTENERS
			/**
			 * [SearchOf.handleDomainesListChange] Method 
			 * @author rlaib  on : 6 mars 2014  10:24:51
			 * @param event
			 */
			public 	void  handleDomainesListChange(AjaxBehaviorEvent event) {
				
				initFilieresByDomaine(dtoSearch.getIdDomaine());
			}
			
			/**
			 * [SearchOf.handleFilieresListChange] Method 
			 * @author rlaib  on : 6 mars 2014  10:24:58
			 * @param event
			 */
			public 	void  handleFilieresListChange(AjaxBehaviorEvent event) {
				initSpecialitesByFiliere(dtoSearch.getIdFiliere());					
			}
			
			/**
			 * [SearchOf.onRowSelect] Method 
			 * @author rlaib  on : 6 mars 2014  10:25:44
			 * @param event
			 */
			public 	void  onRowSelect(SelectEvent event) {
				
				OffreFormationDto Of = (OffreFormationDto) event.getObject();
				ofId =  String.valueOf(Of.getId());
				MessageUtil.addInfoMessageWithoutKey("Selection OF : ", selectedOf.getCode());
				
			}
			
			public void onOfRowSelect(SelectEvent event) {

//				ConfigurableNavigationHandler nh = (ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
//				nh.performNavigation("OfEdit?faces-redirect=true");
				ofBB.setOfDto(selectedOf);
				ofBB.setEditionMode(OfConstants.OF_COMPONENT_EDIT_MODE);
				ofBB.setOfId(String.valueOf(selectedOf.getId()));
				ofBB.init();
				showPanelDetailOf = true;
				showPanelSearchOf = false;
			}
			// endregion LISTENERS
			
			// ===================================================================  
			// Actions
			// ===================================================================
			// region ACTIONS
			/**
			 * [SearchOf.newOf] Method 
			 * @author rlaib  on : 6 mars 2014  10:25:03
			 * @return
			 */
			public void newOf() {
				
				selectedOf = new OffreFormationDto();
				ofBB.setOfDto(selectedOf);
				ofBB.setEditionMode(OfConstants.OF_COMPONENT_NEW_MODE);
				ofBB.setOfId(null);
				ofBB.init();
				showPanelDetailOf = true;
				showPanelSearchOf = false;
			}
			
			/**
			 * [SearchOf.goEdit] Method 
			 * @author rlaib  on : 6 mars 2014  10:25:08
			 * @return
			 */
			public String goEdit() {
				return "ofEdit";
			}
			
			/**
			 * [SearchOf.goDetail] Method 
			 * @author rlaib  on : 9 avr. 2014  14:15:36
			 * @return
			 */
			public String goDetail() {
				
				return "ofDetail";
			}
			
			public void goBack() {
				
				search();
				showPanelDetailOf = false;
				showPanelSearchOf = true;
			}
			
			// endregion ACTIONS
			
			// ===================================================================  
			// Functions Helper
			// ===================================================================
			// region FUNCTIONS HELPER
			
		    /**
		     * [SearchOf.renderComponentOf] Method 
		     * @author rlaib  on : 9 avr. 2014  14:16:25
		     * @return
		     */
		    public boolean renderComponentOf() {
		    	
		    	return (ofId != null && !ofId.trim().isEmpty() && !ofId.equals("null"));
		    }
			
		    /**
		     * [OfSearch.initListDomainesLMD] Method 
		     * @author rlaib  on : 17 nov. 2014  18:16:52
		     */
		    private void initListDomainesLMD() {
				
				List<RefDomaineLMDDto> _list = refDomaineLMDServiceImpl.findDomainesByEtablissement(idEtab);
		    	try {
		    				listDomaines = new ArrayList<SelectItem>();
		    				listDomainesAr =  new ArrayList<SelectItem>();
		    				if(_list != null && _list.size()>0) {
		    					for (RefDomaineLMDDto item : _list) {
	    							listDomaines.add(new SelectItem(item.getId(),item.getLlDomaine() + " ("+item.getIdentifiant()+ ")"));
	    							String libelleAr =item.getLlDomaineArabe() ;
									if(libelleAr == null || libelleAr.trim().equals("null"))
										libelleAr = "";
									listDomainesAr.add(new SelectItem(item.getId(), libelleAr + " ("+item.getIdentifiant()+ ")"));
		    					}
				 				if(listDomaines != null && listDomaines.size()>0) {
				 					initFilieresByDomaine(_list.get(0).getId());
				 				}
		    				}
		    				else {
			 					listFilieres = null;
			 					listSpecialites = null;
			 				}
		    	}
		    	catch (Exception e) {
    				e.printStackTrace();
		    	}
			}
		    
		    /**
		     * [OfSearch.initFilieresByDomaine] Method 
		     * @author rlaib  on : 17 nov. 2014  18:16:43
		     * @param codeDomaine
		     */
		    private void initFilieresByDomaine(Integer idDomaine) {
				List<RefFiliereLmdDto> _list = refFiliereLmdServiceImpl.findByIdDomainelmd(idDomaine);
				try {
					listFilieres = new ArrayList<SelectItem>();
					listFilieresAr = new ArrayList<SelectItem>();
					if(_list != null && _list.size()>0) {
//    					SelectItem defaultSelection = new SelectItem("","	Selectionnez une filiere parmi : " + String.valueOf(_list.size()),"",false,false,true);
//    					listFilieres.add(0, defaultSelection);
//    					listFilieresAr.add(0, defaultSelection);
    					for (RefFiliereLmdDto refFiliereLmdDto : _list) {
							listFilieres.add(new SelectItem(refFiliereLmdDto.getId(), refFiliereLmdDto.getLlFiliere() + " ("+refFiliereLmdDto.getIdentifiant() + ")"));
							String libelleAr =refFiliereLmdDto.getLlFiliereArabe() ;
							if(libelleAr == null || libelleAr.trim().equals("null"))
								libelleAr = "";
							listFilieresAr.add(new SelectItem(refFiliereLmdDto.getId(), libelleAr + " ("+refFiliereLmdDto.getIdentifiant() + ")"));
						}
    					if(listFilieres != null && listFilieres.size()>0) {
    						initSpecialitesByFiliere(_list.get(0).getId());
    					}
					}
					else {
	 					listSpecialites = null;
	 				}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		    
		    /**
		     * [OfSearch.initSpecialitesByFiliere] Method 
		     * @author rlaib  on : 17 nov. 2014  18:16:08
		     * @param codeFiliere
		     */
		    private void initSpecialitesByFiliere(Integer idFiliere) {
				
				List<RefSpecialiteLmdDto> _list = refSpecialiteLmdServiceImpl.findByIdFilierelmd(idFiliere);
				try {
					listSpecialites = new ArrayList<SelectItem>();
					listSpecialitesAr = new ArrayList<SelectItem>();
					if(_list != null && _list.size()>0) {
//    					SelectItem defaultSelection = new SelectItem("","	Selectionnez une specialite parmi : " + String.valueOf(_list.size()),"",false,false,true);
//    					listSpecialites.add(0, defaultSelection);
//    					listSpecialitesAr.add(0, defaultSelection);
    					for (RefSpecialiteLmdDto refSpecialiteLmdDto : _list) {
							listSpecialites.add(new SelectItem(refSpecialiteLmdDto.getId(),refSpecialiteLmdDto.getLlSpecialite()+ " ("+refSpecialiteLmdDto.getIdentifiant() + ")"));
							String libelleAr =refSpecialiteLmdDto.getLlFiliereArabe() ;
							if(libelleAr == null || libelleAr.trim().equals("null"))
								libelleAr = "";
							listSpecialitesAr.add(new SelectItem(refSpecialiteLmdDto.getId(),libelleAr+ " ("+refSpecialiteLmdDto.getIdentifiant() + ")"));
						}
    				}
					else
						listSpecialites = null;
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		    
			/**
			 * [OfSearch.initSessionInfos] Method 
			 * @author rlaib  on : 17 nov. 2014  18:16:15
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
			 * [OfSearch.getDomainesLMDByEtab] Method 
			 * @author rlaib  on : 17 nov. 2014  18:16:27
			 * @param idEtab
			 * @return
			 */
			public List<RefDomaineLMDDto> getDomainesLMDByEtab(Integer idEtab)  {
				
				List<RefDomaineLMDDto> result = new ArrayList<RefDomaineLMDDto>();
				try {
					result = refDomaineLMDServiceImpl.findDomainesByEtablissement(idEtab);
				}
				
				catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}
			
			public void zoomIn() {
				
				if(fontSize != null && fontSize < 16) {
					fontSize = fontSize +1;
					}
			}
			public void zoomOut() {
				
				if(fontSize != null && fontSize > 7) {
					fontSize = fontSize - 1;
					}
			}
			// endregion FUNCTIONS HELPER
		    
		    // ===================================================================  
			// Export Data  postProcessor
			// ===================================================================
			
			// region POST PROCESSOR
		    public void postProcessXLS(Object document) {  
		    	
		        HSSFWorkbook wb = (HSSFWorkbook) document;  
		        HSSFSheet sheet = wb.getSheetAt(0);  
		        HSSFRow header = sheet.getRow(0);  
		          
		        HSSFCellStyle cellStyle = wb.createCellStyle();    
		        cellStyle.setFillBackgroundColor(HSSFColor.GREEN.index);  
		        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
		          
		        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {  
		            HSSFCell cell = header.getCell(i);  
		              
		            cell.setCellStyle(cellStyle);  
		        }  
		    }

				    
			// endregion POST PROCESSOR
		    
		    
}
