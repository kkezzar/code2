/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.bac.BacBB.java] 
 * @author  Rafik LAIB on : 21 mai 2014  11:18:57
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.bac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.api.UIData;
import org.primefaces.component.datagrid.DataGrid;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.LazyDataModel;

import dz.gov.mesrs.sii.fve.business.model.dto.bac.CorrespondanceDomaineDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.DossierBachelierDto;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.service.bac.CorrespondanceDomaineService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

/**
 * @author  Rafik LAIB  on : 21 mai 2014  11:18:57
 */
@ManagedBean(name = "bacSearch")
@ViewScoped
public class BacSearch {

	// ===================================================================  
	// Constructors
	// ===================================================================
	public BacSearch() {
		
	}
	
	@PostConstruct
	public void init() {
		
		 try	{
			 		// Initializing Session Infos 
					initSessionInfos();
					
				 	// Initializing Years
				 	this.listYears =  getYearsList();
				 	if(listYears != null && !listYears.isEmpty()) {
				 		year = listYears.get(0).getValue().toString();
				 	}
		 			// Initializing Filieres Bac List
					initCriteriaSearch();
					showResultSearch=false;
					
					// Init param paging
					startRow = 0;
					currentPage = 0;
					pageSize = 4;
		
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	
	}

	/**
	 * [BacSearch.initCriteriaSearch] Method 
	 * @author rlaib  on : 11 sept. 2014  16:37:04
	 */
	private void initCriteriaSearch() {
		try {
				this.listFilieresBac = this.correspondanceDomaineService.getFilieresByEtab(codeEtablissement,year);
				DossierBachelierDto searchDto = new  DossierBachelierDto();
				searchDto.setRefCodeEtablissementAffectation(codeEtablissement);
				this.totalResult = bacService.getFindAdvancedCount(searchDto, null, false,false, this.year);
		}
		 catch(Exception e){
			 e.printStackTrace();
		 }
	}
	
	// ===================================================================  
	// Beans Services 
	// ===================================================================
	
	/**
	 * [BacSearch.initOneBachelor] Method 
	 * @author  Rafik LAIB  on : 28 juil. 2014  02:30:54
	 * @param idBachelorFolder2
	 */
	private void initOneBachelor(int id) {
		
		selectedRow = bacService.findBachelorFileById(id);
		if(selectedRow!= null){
			Map<String, SelectItem> _listWilaya = getNomenclaturesWilayas();
			String _codeWilaya = null;
			String _libelleWilaya = null;
			if(_listWilaya != null) {
						_codeWilaya = selectedRow.getRefCodeWilayaBac();
						if(_codeWilaya != null) {
							if(Integer.parseInt(_codeWilaya)<10)
								_codeWilaya = "0"+String.valueOf(Integer.parseInt(_codeWilaya));
							if( _listWilaya.get(_codeWilaya)!=null)
								_libelleWilaya = _listWilaya.get(_codeWilaya).getLabel() + " ("+_codeWilaya+")";
							if(_libelleWilaya != null)
								selectedRow.setRefCodeWilayaBac(_libelleWilaya);
						}
						_codeWilaya = selectedRow.getRefCodeWilayaNaissance();
						if(_codeWilaya != null) {
							if(Integer.parseInt(_codeWilaya)<10)
								_codeWilaya = "0"+_codeWilaya;
							if( _listWilaya.get(_codeWilaya)!=null)
								_libelleWilaya = _listWilaya.get(_codeWilaya).getLabel() + " ("+_codeWilaya+")";
					
							if(_libelleWilaya != null)
								selectedRow.setRefCodeWilayaNaissance(_libelleWilaya);
						}
						_codeWilaya = selectedRow.getRefCodeWilayaResidence();
						if(_codeWilaya != null) {
							if(Integer.parseInt(_codeWilaya)<10)
								_codeWilaya = "0"+_codeWilaya;
							if( _listWilaya.get(_codeWilaya)!=null)
								_libelleWilaya = _listWilaya.get(_codeWilaya).getLabel() + " ("+_codeWilaya+")";
					
							if(_libelleWilaya != null)
								selectedRow.setRefCodeWilayaResidence(_libelleWilaya);
						}
			}
		}

		
	}

	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;
	
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;

	@ManagedProperty(value="#{bacService}")
	private BacService bacService;
	
	@ManagedProperty(value="#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	
	@ManagedProperty(value="#{correspondanceDomaineService}")
	private CorrespondanceDomaineService correspondanceDomaineService;
	
	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;
	// ===================================================================  
	// Parameters
	// ===================================================================
	private String bachelorFileId;
	private String keyWords;
	private String year;
	private String filiereCode;
	private Integer idBachelorFolder;
	private String codeEtablissement;
	
	// ===================================================================  
	// Properties Model
	// ===================================================================
	private String newEtabCode;
	private String libelleEtab;
	private LazyDataModel<DossierBachelierDto> lazyResult;
	private List<SelectItem> listYears;
	private List<DossierBachelierDto> result;
	private DossierBachelierDto selectedRow;
	private List<CorrespondanceDomaineDto> listFilieresBac;
	private Integer currentPage;
	private Integer startRow;
	private Integer pageSize;
	private Integer totalResult;
	private Integer totalSearchResult;
	private boolean canSearch; 
	private boolean showResultSearch = false; 
	private UIData gridData;
	private DossierBachelierDto searchDto ;
	
	// ===================================================================  
	// Beans Services  Getters / Setters
	// ===================================================================

	
	/**
	 * [BacBB.bacService] Getter 
	 * @author  Rafik LAIB on : 25 mai 2014  17:38:46
	 * @return the bacService
	 */
	public BacService getBacService() {
		return bacService;
	}

	/**
	 * [BacSearch.searchDto] Getter 
	 * @author Rafik on : 10 déc. 2014  22:20:58
	 * @return the searchDto
	 */
	public DossierBachelierDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [BacSearch.searchDto] Setter 
	 * @author Rafik on : 10 déc. 2014  22:20:58
	 * @param searchDto the searchDto to set
	 */
	public void setSearchDto(DossierBachelierDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * [BacSearch.gridData] Getter 
	 * @author Rafik on : 10 déc. 2014  20:16:18
	 * @return the gridData
	 */
	public UIData getGridData() {
		return gridData;
	}

	/**
	 * [BacSearch.gridData] Setter 
	 * @author Rafik on : 10 déc. 2014  20:16:19
	 * @param gridData the gridData to set
	 */
	public void setGridData(UIData gridData) {
		this.gridData = gridData;
	}

	/**
	 * [BacSearch.refIndividuService] Getter 
	 * @author rlaib on : 20 nov. 2014  14:41:02
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [BacSearch.refIndividuService] Setter 
	 * @author rlaib on : 20 nov. 2014  14:41:02
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [BacSearch.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  14:41:02
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [BacSearch.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  14:41:02
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [BacBB.bacService] Setter 
	 * @author  Rafik LAIB on : 25 mai 2014  17:38:46
	 * @param bacService the bacService to set
	 */
	public void setBacService(BacService bacService) {
		this.bacService = bacService;
	}
	
	/**
	 * [BacBB.anneeAcademiqueService] Getter 
	 * @author  Rafik LAIB on : 25 mai 2014  17:59:09
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [BacBB.anneeAcademiqueService] Setter 
	 * @author  Rafik LAIB on : 25 mai 2014  17:59:09
	 * @param anneeAcademiqueService the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}
	
	/**
	 * [BacSearch.correspondanceDomaineService] Getter 
	 * @author rlaib on : 22 juin 2014  16:50:54
	 * @return the correspondanceDomaineService
	 */
	public CorrespondanceDomaineService getCorrespondanceDomaineService() {
		return correspondanceDomaineService;
	}

	/**
	 * [BacSearch.correspondanceDomaineService] Setter 
	 * @author rlaib on : 22 juin 2014  16:50:54
	 * @param correspondanceDomaineService the correspondanceDomaineService to set
	 */
	public void setCorrespondanceDomaineService(
			CorrespondanceDomaineService correspondanceDomaineService) {
		this.correspondanceDomaineService = correspondanceDomaineService;
	}

	/**
	 * [BacSearch.sessionBean] Getter 
	 * @author rlaib on : 24 juil. 2014  10:50:33
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [BacSearch.sessionBean] Setter 
	 * @author rlaib on : 24 juil. 2014  10:50:33
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [BacSearch.dossierEtudiantService] Getter 
	 * @author rlaib on : 10 ao�t 2014  10:26:03
	 * @return the dossierEtudiantService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [BacSearch.dossierEtudiantService] Setter 
	 * @author rlaib on : 10 ao�t 2014  10:26:03
	 * @param dossierEtudiantService the dossierEtudiantService to set
	 */
	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	// ===================================================================  
	// Parameters Getters / Setters
	// ===================================================================
	/**
	 * [BacSearch.bachelorFileId] Getter 
	 * @author  Rafik LAIB on : 3 juin 2014  15:21:22
	 * @return the bachelorFileId
	 */
	public String getBachelorFileId() {
		return bachelorFileId;
	}

	/**
	 * [BacSearch.bachelorFileId] Setter 
	 * @author  Rafik LAIB on : 3 juin 2014  15:21:22
	 * @param bachelorFileId the bachelorFileId to set
	 */
	public void setBachelorFileId(String bachelorFileId) {
		this.bachelorFileId = bachelorFileId;
	}

	/**
	 * [BacSearch.keyWords] Getter 
	 * @author  Rafik LAIB on : 3 juin 2014  15:21:22
	 * @return the keyWords
	 */
	public String getKeyWords() {
		return keyWords;
	}

	/**
	 * [BacSearch.keyWords] Setter 
	 * @author  Rafik LAIB on : 3 juin 2014  15:21:22
	 * @param keyWords the keyWords to set
	 */
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	
	/**
	 * [BacSearch.filiereCode] Getter 
	 * @author rlaib on : 22 juin 2014  16:47:02
	 * @return the filiereCode
	 */
	public String getFiliereCode() {
		return filiereCode;
	}

	/**
	 * [BacSearch.filiereCode] Setter 
	 * @author rlaib on : 22 juin 2014  16:47:02
	 * @param filiereCode the filiereCode to set
	 */
	public void setFiliereCode(String filiereCode) {
		this.filiereCode = filiereCode;
	}
	
	/**
	 * [BacSearch.idBachelorFolder] Getter 
	 * @author  Rafik LAIB on : 16 juil. 2014  23:44:00
	 * @return the idBachelorFolder
	 */
	public Integer getIdBachelorFolder() {
		return idBachelorFolder;
	}

	/**
	 * [BacSearch.idBachelorFolder] Setter 
	 * @author  Rafik LAIB on : 16 juil. 2014  23:44:00
	 * @param idBachelorFolder the idBachelorFolder to set
	 */
	public void setIdBachelorFolder(Integer idBachelorFolder) {
		this.idBachelorFolder = idBachelorFolder;
	}

	/**
	 * [BacSearch.codeEtablissement] Getter 
	 * @author rlaib on : 24 juil. 2014  10:48:21
	 * @return the codeEtablissement
	 */
	public String getCodeEtablissement() {
		return codeEtablissement;
	}

	/**
	 * [BacSearch.codeEtablissement] Setter 
	 * @author rlaib on : 24 juil. 2014  10:48:21
	 * @param codeEtablissement the codeEtablissement to set
	 */
	public void setCodeEtablissement(String codeEtablissement) {
		this.codeEtablissement = codeEtablissement;
	}
	

	/**
	 * [BacSearch.newEtabCode] Getter 
	 * @author rlaib on : 31 juil. 2014  11:02:17
	 * @return the newEtabCode
	 */
	public String getNewEtabCode() {
		return newEtabCode;
	}

	/**
	 * [BacSearch.newEtabCode] Setter 
	 * @author rlaib on : 31 juil. 2014  11:02:17
	 * @param newEtabCode the newEtabCode to set
	 */
	public void setNewEtabCode(String newEtabCode) {
		this.newEtabCode = newEtabCode;
	}

	/**
	 * [BacSearch.libelleEtab] Getter 
	 * @author rlaib on : 31 juil. 2014  11:02:31
	 * @return the libelleEtab
	 */
	public String getLibelleEtab() {
		return libelleEtab;
	}

	/**
	 * [BacSearch.libelleEtab] Setter 
	 * @author rlaib on : 31 juil. 2014  11:02:31
	 * @param libelleEtab the libelleEtab to set
	 */
	public void setLibelleEtab(String libelleEtab) {
		this.libelleEtab = libelleEtab;
	}

	/**
	 * [BacSearch.year] Getter 
	 * @author rlaib on : 22 juin 2014  15:31:24
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * [BacSearch.year] Setter 
	 * @author rlaib on : 22 juin 2014  15:31:24
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	
	// ===================================================================  
	// Properties Model Getters / Setters
	// ===================================================================

	/**
	 * [BacBB.listYears] Getter 
	 * @author  Rafik LAIB on : 25 mai 2014  15:35:15
	 * @return the listYears
	 */
	public List<SelectItem> getListYears() {
		return listYears;
	}

	

	/**
	 * [BacBB.listYears] Setter 
	 * @author  Rafik LAIB on : 25 mai 2014  15:35:15
	 * @param listYears the listYears to set
	 */
	public void setListYears(List<SelectItem> listYears) {
		this.listYears = listYears;
	}
		
		/**
	 * [BacSearch.lazyResult] Getter 
	 * @author  Rafik LAIB on : 3 juin 2014  15:20:04
	 * @return the lazyResult
	 */
	public LazyDataModel<DossierBachelierDto> getLazyResult() {
		return lazyResult;
	}

	/**
	 * [BacSearch.lazyResult] Setter 
	 * @author  Rafik LAIB on : 3 juin 2014  15:20:04
	 * @param lazyResult the lazyResult to set
	 */
	public void setLazyResult(LazyDataModel<DossierBachelierDto> lazyResult) {
		this.lazyResult = lazyResult;
	}

	/**
	 * [BacSearch.result] Getter 
	 * @author  Rafik LAIB on : 3 juin 2014  15:20:04
	 * @return the result
	 */
	public List<DossierBachelierDto> getResult() {
		return result;
	}

	/**
	 * [BacSearch.result] Setter 
	 * @author  Rafik LAIB on : 3 juin 2014  15:20:04
	 * @param result the result to set
	 */
	public void setResult(List<DossierBachelierDto> result) {
		this.result = result;
	}
	
	/**
	 * [BacSearch.listFilieresBac] Getter 
	 * @author rlaib on : 22 juin 2014  16:47:19
	 * @return the listFilieresBac
	 */
	public List<CorrespondanceDomaineDto> getListFilieresBac() {
		return listFilieresBac;
	}

	/**
	 * [BacSearch.listFilieresBac] Setter 
	 * @author rlaib on : 22 juin 2014  16:47:19
	 * @param listFilieresBac the listFilieresBac to set
	 */
	public void setListFilieresBac(List<CorrespondanceDomaineDto> listFilieresBac) {
		this.listFilieresBac = listFilieresBac;
	}
	
	/**
	 * [BacSearch.currentPage] Getter 
	 * @author  Rafik LAIB on : 16 juil. 2014  02:07:09
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * [BacSearch.currentPage] Setter 
	 * @author  Rafik LAIB on : 16 juil. 2014  02:07:10
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	
	/**
	 * [BacSearch.pageSize] Getter 
	 * @author Rafik on : 10 déc. 2014  20:56:03
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * [BacSearch.pageSize] Setter 
	 * @author Rafik on : 10 déc. 2014  20:56:03
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	

	/**
	 * [BacSearch.startRow] Getter 
	 * @author Rafik on : 10 déc. 2014  21:45:29
	 * @return the startRow
	 */
	public Integer getStartRow() {
		return startRow;
	}

	/**
	 * [BacSearch.startRow] Setter 
	 * @author Rafik on : 10 déc. 2014  21:45:29
	 * @param startRow the startRow to set
	 */
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	/**
	 * [BacSearch.totalResult] Getter 
	 * @author rlaib on : 3 ao�t 2014  17:13:58
	 * @return the totalResult
	 */
	public Integer getTotalResult() {
		return totalResult;
	}

	/**
	 * [BacSearch.totalResult] Setter 
	 * @author rlaib on : 3 ao�t 2014  17:13:58
	 * @param totalResult the totalResult to set
	 */
	public void setTotalResult(Integer totalResult) {
		this.totalResult = totalResult;
	}
	

	/**
	 * [BacSearch.totalSearchResult] Getter 
	 * @author Rafik on : 10 déc. 2014  21:24:05
	 * @return the totalSearchResult
	 */
	public Integer getTotalSearchResult() {
		return totalSearchResult;
	}

	/**
	 * [BacSearch.totalSearchResult] Setter 
	 * @author Rafik on : 10 déc. 2014  21:24:05
	 * @param totalSearchResult the totalSearchResult to set
	 */
	public void setTotalSearchResult(Integer totalSearchResult) {
		this.totalSearchResult = totalSearchResult;
	}

	/**
	 * [BacSearch.canSearch] Getter 
	 * @author rlaib on : 2 oct. 2014  15:20:19
	 * @return the canSearch
	 */
	public boolean isCanSearch() {
		
		try {
			canSearch = !(year != null && !year.trim().isEmpty() && !year.equals("null") && (codeEtablissement  != null && !codeEtablissement.trim().isEmpty() && !codeEtablissement.equals("null")));
		}
		catch (Exception e) {
	
			canSearch = false;
		}
		return canSearch;
	}

	/**
	 * [BacSearch.canSearch] Setter 
	 * @author rlaib on : 2 oct. 2014  15:20:19
	 * @param canSearch the canSearch to set
	 */
	public void setCanSearch(boolean canSearch) {
		this.canSearch = canSearch;
	}

	/**
	 * [BacSearch.showResultSearch] Getter 
	 * @author rlaib on : 16 nov. 2014  16:44:38
	 * @return the showResultSearch
	 */
	public boolean isShowResultSearch() {
		return showResultSearch;
	}

	/**
	 * [BacSearch.showResultSearch] Setter 
	 * @author rlaib on : 16 nov. 2014  16:44:38
	 * @param showResultSearch the showResultSearch to set
	 */
	public void setShowResultSearch(boolean showResultSearch) {
		this.showResultSearch = showResultSearch;
	}

	// ===================================================================  
	// ActionListener
	// ===================================================================
	public void search() {
		
	try {
				if (!(filiereCode != null && !filiereCode.trim().isEmpty() && !filiereCode.equals("null"))) 
					filiereCode = null;
				searchDto = new  DossierBachelierDto();
				searchDto.setRefCodeFiliereAffectation(filiereCode);
				searchDto.setRefCodeEtablissementAffectation(codeEtablissement);
				totalSearchResult = bacService.getFindAdvancedCount(searchDto, keyWords.trim(), false,false, this.year);
				startRow = 0;
				currentPage = 0;
				pageSize = 4;
				startRow = currentPage * pageSize;
				result = bacService.findAdvanced(searchDto, keyWords.trim(), false,false, this.year,startRow, pageSize);
				lazyResult = new  LazyBachelorFileDataModel(result);
				lazyResult.setRowCount(totalSearchResult);
				lazyResult.setPageSize(pageSize);
				showResultSearch = true;
		}
		catch (Exception e) {
			
					e.printStackTrace();
		}
	
	}
	
	public void prepareBachelorFileDetails() {
		
		try {
					if(idBachelorFolder != null) {
							initOneBachelor(idBachelorFolder);
					}
			}
			catch (Exception e) {
				
						e.printStackTrace();
			}
		
	}

		
	// ===================================================================  
	// Listeners
	// ===================================================================
		public void onPageChange(PageEvent event) {
			
			DataGrid dg = (DataGrid) event.getSource();
//			currentPage = ((DataGrid) event.getSource()).getFirst();
			currentPage = (Integer) event.getPage();
			pageSize = dg.getRows();
//			int numberOfpages = (int) StrictMath.ceil((rowsNumber/pageSize));
			startRow = currentPage * pageSize;
			result = bacService.findAdvanced(searchDto, keyWords.trim(), false,false, this.year,startRow, pageSize);
			lazyResult = new  LazyBachelorFileDataModel(result);
			lazyResult.setRowCount(totalSearchResult);
			lazyResult.setPageSize(pageSize);
	     // RequestContext.getCurrentInstance().addPartialUpdateTarget("form:showme"); // change this to the right id
	   }
	
		public void  handleYearsChange(AjaxBehaviorEvent event) {
			
			try {
				this.listFilieresBac = this.correspondanceDomaineService.getFilieresByEtab(codeEtablissement,year);
				DossierBachelierDto searchDto = new  DossierBachelierDto();
				searchDto.setRefCodeEtablissementAffectation(codeEtablissement);
				this.totalResult = bacService.getFindAdvancedCount(searchDto, null, false,false, this.year);
			}
			catch(Exception e){
					e.printStackTrace();
			}
		}
		
	// ===================================================================  
	// Actions and Methods
	// ===================================================================

	
	// ===================================================================  
	// Functions Helper
	// ===================================================================
	
	/**
	 * [BacSearch.selectedRow] Getter 
	 * @author  Rafik LAIB on : 3 juin 2014  15:17:07
	 * @return the selectedRow
	 */
	public DossierBachelierDto getSelectedRow() {
		if(selectedRow == null)
			selectedRow = new DossierBachelierDto();
		return selectedRow;
	}
	
	/**
	 * [BacSearch.selectedRow] Setter 
	 * @author  Rafik LAIB on : 3 juin 2014  15:17:07
	 * @param selectedRow the selectedRow to set
	 */
	public void setSelectedRow(DossierBachelierDto selectedRow) {
		this.selectedRow = selectedRow;
	}

	/**
	 * [BacBB.getYearsList] Method 
	 * @author  Rafik LAIB  on : 26 mai 2014  11:09:26
	 * @return
	 */
	private List<SelectItem> getYearsList() {
		
		try	{
			 
			List<String> _list = bacService.getAllYears();
			List<SelectItem> result = new ArrayList<SelectItem>();
			for (String item : _list) {
				result.add(new SelectItem(item, item));
			}
			return result;
		 
		}
		catch(Exception e){
		 
			e.printStackTrace();
			return new ArrayList<SelectItem>();
		}
		
	}
	
	/**
	 * [BacSearch.getNomenclaturesWilayas] Method 
	 * @author Rafik  on : 12 déc. 2014  18:31:13
	 * @return
	 */
	private Map<String, SelectItem> getNomenclaturesWilayas() {
		
		Map<String, SelectItem> _result = null;
		Map<Integer, List<SelectItem>> result = new HashMap<Integer, List<SelectItem>>();
		try {
					List<NomenclatureDto> ncList = null;
					try {
						ncList = nomenclatureService.findByName(OfConstants.NC_NAME_WILAYA);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					List<SelectItem> resultFr = new ArrayList<SelectItem>();
					List<SelectItem> resultAr = new ArrayList<SelectItem>();
			 		for (NomenclatureDto item : ncList) {
			 			resultFr.add(new SelectItem(item.getCode(), item.getLibelleLongFr()));
			 			resultAr.add(new SelectItem(item.getCode(), item.getLibelleLongAr()));
					}
			 		// Liste Nomeclature FR
			 		result.put(new Integer(1), resultFr);
			 		//Liste Nomeclature AR
			 		result.put(new Integer(2), resultAr);
			 		
					List<SelectItem> _list = result.get(1);
					_result = new HashMap<String, SelectItem>();
					for (SelectItem _selectItem : _list) {
						if(_selectItem.getValue()!= null)
						_result.put(_selectItem.getValue().toString(), _selectItem);
					}
					return _result;
		}
		catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * [BacSearch.initSessionInfos] Method 
	 * @author Rafik  on : 12 déc. 2014  18:31:18
	 */
	private void initSessionInfos() {
		try {
			
			 	this.codeEtablissement= this.sessionBean.getAncienCodeEtablissement();
	 			this.libelleEtab=this.sessionBean.getLlLatinEtablissement();
	 			this.newEtabCode = this.sessionBean.getCodeEtablissement();
	 			this.year = this.sessionBean.getCodeAnneeAcademique();
		 }
		 catch(Exception e){
			 
			 e.printStackTrace();
		 
		 }
	}
}
