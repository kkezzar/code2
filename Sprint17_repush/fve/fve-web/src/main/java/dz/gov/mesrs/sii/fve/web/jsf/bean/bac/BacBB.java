/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.bac.BacBB.java] 
 * @author  Rafik LAIB on : 21 mai 2014  11:18:57
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.bac;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.UploadedFile;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.AffectationBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.DossierBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.ImportationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.TypeImportationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.FileReaderAFF;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.FileReaderDBC;
import dz.gov.mesrs.sii.referentiel.business.util.ConfigHolder;

/**
 * @author  Rafik LAIB  on : 21 mai 2014  11:18:57
 */
@ManagedBean(name = "bacBB")
@RequestScoped
public class BacBB {

	// ===================================================================  
	// Constructors
	// ===================================================================
	public BacBB() {
		
	}
	
	@PostConstruct
	public void init() {
		
		 try	{
			 		 	
			 	// Initializing import History
			 	this.listImports = bacService.getAllImports();
			 	this.selectedYear = sessionBean.getCodeAnneeAcademique();
			 	
			 	// Initializing Import Types
			 	this.listTypesImport =  getTypesList();
			 	if (paramImportId != null && !paramImportId.trim().equals("")) {
			 		
			 		this.currentImportDto = bacService.getImportById(Integer.parseInt(paramImportId));
			 		switch (this.paramImportTypeCode) {
			 		
					case UtilConstants.BAC_IMPORT_TYPE_BACHELOR_FILE_CODE:
						
						this.bachelorFilesToImport = bacService.getListBachelorsFilesByImportId(Integer.parseInt(paramImportId));
				 		this.lazyBachelorFilesModel = new LazyBachelorFileDataModel(this.bachelorFilesToImport);
				 
						break;

					case UtilConstants.BAC_IMPORT_TYPE_BACHELOR_AFFECTATION_CODE:
						
						this.bachelorAffectationsToImport= bacService.getListBachelorsAffectationsByImportId(Integer.parseInt(paramImportId));
				 		this.lazyBachelorAffectationsModel = new LazyBachelorAffectationDataModel(this.bachelorAffectationsToImport);
				 
						break;
						
					default:
						break;
					}
			 		
			 	}
			 
		 }
		 catch(Exception e){
			 
			 e.printStackTrace();
		 
		 }
	
	}
	
	// ===================================================================  
	// Beans Services 
	// ===================================================================
	
	@ManagedProperty(value="#{bacService}")
	private BacService bacService;
	
	@ManagedProperty(value="#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	
	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;
	
	@ManagedProperty(value = "#{configHolder}")
	private ConfigHolder configHolder;
	// ===================================================================  
	// Parameters
	// ===================================================================
	@ManagedProperty("#{param.paramImportId}")
	private String paramImportId;
	
	@ManagedProperty("#{param.paramImportTypeCode}")
	private String paramImportTypeCode;
	
	@ManagedProperty("#{param.paramFileName}")
	private String paramFileName;
	
	// ===================================================================  
	// Properties Model
	// ===================================================================

	private LazyDataModel<DossierBachelierDto> lazyBachelorFilesModel;
	private LazyDataModel<AffectationBachelierDto> lazyBachelorAffectationsModel;
	private List<SelectItem> listYears;
	private List<SelectItem> listTypesImport;
	private List<DossierBachelierDto> bachelorFilesToImport;
	private List<DossierBachelierDto> bachelorFilesWithErrors;
	private List<AffectationBachelierDto> bachelorAffectationsToImport;
	private List<AffectationBachelierDto> bachelorAffectationsWithErrors;
	private ImportationDto currentImportDto;
	private Integer totalRows;
	private String selectedTypeImportCode;
	private String selectedYear;
    private UploadedFile fileToImport;
	private boolean showImportResultOutput;
	private boolean showDatatableBachelorsFiles;
	private boolean showDatatableBachelorsAffectations;
	private boolean replaceLastImport;
	private boolean activeImport;
	private String filePath;
	private List<ImportationDto> listImports;
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
	 * [BacBB.sessionBean] Getter 
	 * @author rlaib on : 23 juin 2014  14:25:09
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [BacBB.sessionBean] Setter 
	 * @author rlaib on : 23 juin 2014  14:25:09
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
	
	public void setConfigHolder(ConfigHolder configHolder) {
	    this.configHolder = configHolder;
	}
	
	public ConfigHolder getConfigHolder() {
	    return configHolder;
	}

	// ===================================================================  
	// Parameters Getters / Setters
	// ===================================================================
	/**
	 * [BacBB.paramImportId] Getter 
	 * @author  Rafik LAIB on : 2 juin 2014  11:07:01
	 * @return the paramImportId
	 */
	public String getParamImportId() {
		return paramImportId;
	}

	/**
	 * [BacBB.paramImportId] Setter 
	 * @author  Rafik LAIB on : 2 juin 2014  11:07:01
	 * @param paramImportId the paramImportId to set
	 */
	public void setParamImportId(String paramImportId) {
		this.paramImportId = paramImportId;
	}
	
	/**
	 * [BacBB.paramImportTypeCode] Getter 
	 * @author  Rafik LAIB on : 3 juin 2014  12:18:59
	 * @return the paramImportTypeCode
	 */
	public String getParamImportTypeCode() {
		return paramImportTypeCode;
	}

	/**
	 * [BacBB.paramImportTypeCode] Setter 
	 * @author  Rafik LAIB on : 3 juin 2014  12:18:59
	 * @param paramImportTypeCode the paramImportTypeCode to set
	 */
	public void setParamImportTypeCode(String paramImportTypeCode) {
		this.paramImportTypeCode = paramImportTypeCode;
	}

	/**
	 * [BacBB.paramFileName] Getter 
	 * @author rlaib on : 13 juil. 2014  14:32:41
	 * @return the paramFileName
	 */
	public String getParamFileName() {
		return paramFileName;
	}

	/**
	 * [BacBB.paramFileName] Setter 
	 * @author rlaib on : 13 juil. 2014  14:32:41
	 * @param paramFileName the paramFileName to set
	 */
	public void setParamFileName(String paramFileName) {
		this.paramFileName = paramFileName;
	}

	// ===================================================================  
	// Properties Model Getters / Setters
	// ===================================================================
	/**
	 * [BacBB.lazyBachelorFilesModel] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  15:39:53
	 * @return the lazyBachelorFilesModel
	 */
	public LazyDataModel<DossierBachelierDto> getLazyBachelorFilesModel() {
		return lazyBachelorFilesModel;
	}


	/**
	 * [BacBB.lazyBachelorFilesModel] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  15:39:53
	 * @param lazyBachelorFilesModel the lazyBachelorFilesModel to set
	 */
	public void setLazyBachelorFilesModel(
			LazyDataModel<DossierBachelierDto> lazyBachelorFilesModel) {
		this.lazyBachelorFilesModel = lazyBachelorFilesModel;
	}
	
	// ===================================================================  
	// Listeners
	// ===================================================================

	
	// ===================================================================  
	// ActionListener
	// ===================================================================
	
	/**
	 * [BacBB.lazyBachelorAffectationsModel] Getter 
	 * @author  Rafik LAIB on : 2 juin 2014  17:48:51
	 * @return the lazyBachelorAffectationsModel
	 */
	public LazyDataModel<AffectationBachelierDto> getLazyBachelorAffectationsModel() {
		return lazyBachelorAffectationsModel;
	}

	/**
	 * [BacBB.lazyBachelorAffectationsModel] Setter 
	 * @author  Rafik LAIB on : 2 juin 2014  17:48:51
	 * @param lazyBachelorAffectationsModel the lazyBachelorAffectationsModel to set
	 */
	public void setLazyBachelorAffectationsModel(
			LazyDataModel<AffectationBachelierDto> lazyBachelorAffectationsModel) {
		this.lazyBachelorAffectationsModel = lazyBachelorAffectationsModel;
	}

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
	 * [BacBB.listTypesImport] Getter 
	 * @author  Rafik LAIB on : 25 mai 2014  15:35:15
	 * @return the listTypesImport
	 */
	public List<SelectItem> getListTypesImport() {
		return listTypesImport;
	}

	/**
	 * [BacBB.listTypesImport] Setter 
	 * @author  Rafik LAIB on : 25 mai 2014  15:35:15
	 * @param listTypesImport the listTypesImport to set
	 */
	public void setListTypesImport(List<SelectItem> listTypesImport) {
		this.listTypesImport = listTypesImport;
	}

	/**
	 * [BacBB.dataToImport] Getter 
	 * @author  Rafik LAIB on : 25 mai 2014  15:35:15
	 * @return the dataToImport
	 */
	public List<DossierBachelierDto> getBachelorFilesToImport() {
		return bachelorFilesToImport;
	}

	/**
	 * [BacBB.dataToImport] Setter 
	 * @author  Rafik LAIB on : 25 mai 2014  15:35:15
	 * @param dataToImport the dataToImport to set
	 */
	public void setBachelorFilesToImport(List<DossierBachelierDto> dataToImport) {
		this.bachelorFilesToImport = dataToImport;
	}

	/**
	 * [BacBB.dataWithErrors] Getter 
	 * @author  Rafik LAIB on : 26 mai 2014  17:44:15
	 * @return the dataWithErrors
	 */
	public List<DossierBachelierDto> getBachelorFilesWithErrors() {
		return bachelorFilesWithErrors;
	}

	/**
	 * [BacBB.dataWithErrors] Setter 
	 * @author  Rafik LAIB on : 26 mai 2014  17:44:15
	 * @param dataWithErrors the dataWithErrors to set
	 */
	public void setBachelorFilesWithErrors(List<DossierBachelierDto> dataWithErrors) {
		this.bachelorFilesWithErrors = dataWithErrors;
	}

	/**
	 * [BacBB.bachelorAffectationsToImport] Getter 
	 * @author  Rafik LAIB on : 1 juin 2014  20:24:49
	 * @return the bachelorAffectationsToImport
	 */
	public List<AffectationBachelierDto> getBachelorAffectationsToImport() {
		return bachelorAffectationsToImport;
	}
	

	/**
	 * [BacBB.bachelorAffectationsToImport] Setter 
	 * @author  Rafik LAIB on : 1 juin 2014  20:24:49
	 * @param bachelorAffectationsToImport the bachelorAffectationsToImport to set
	 */
	public void setBachelorAffectationsToImport(
			List<AffectationBachelierDto> bachelorAffectationsToImport) {
		this.bachelorAffectationsToImport = bachelorAffectationsToImport;
	}
	

	/**
	 * [BacBB.bachelorAffectationsWithErrors] Getter 
	 * @author  Rafik LAIB on : 1 juin 2014  20:24:49
	 * @return the bachelorAffectationsWithErrors
	 */
	public List<AffectationBachelierDto> getBachelorAffectationsWithErrors() {
		return bachelorAffectationsWithErrors;
	}
	

	/**
	 * [BacBB.bachelorAffectationsWithErrors] Setter 
	 * @author  Rafik LAIB on : 1 juin 2014  20:24:49
	 * @param bachelorAffectationsWithErrors the bachelorAffectationsWithErrors to set
	 */
	public void setBachelorAffectationsWithErrors(
			List<AffectationBachelierDto> bachelorAffectationsWithErrors) {
		this.bachelorAffectationsWithErrors = bachelorAffectationsWithErrors;
	}

	/**
	 * [BacBB.currentImportDto] Getter 
	 * @author  Rafik LAIB on : 3 juin 2014  14:12:12
	 * @return the currentImportDto
	 */
	public ImportationDto getCurrentImportDto() {
		return currentImportDto;
	}

	/**
	 * [BacBB.currentImportDto] Setter 
	 * @author  Rafik LAIB on : 3 juin 2014  14:12:12
	 * @param currentImportDto the currentImportDto to set
	 */
	public void setCurrentImportDto(ImportationDto currentImportDto) {
		this.currentImportDto = currentImportDto;
	}

	/**
	 * [BacBB.totalRows] Getter 
	 * @author  Rafik LAIB on : 25 mai 2014  15:35:15
	 * @return the totalRows
	 */
	public Integer getTotalRows() {
		return totalRows;
	}

	/**
	 * [BacBB.totalRows] Setter 
	 * @author  Rafik LAIB on : 25 mai 2014  15:35:15
	 * @param totalRows the totalRows to set
	 */
	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}
	
	/**
	 * [BacBB.selectedTypeImportCode] Getter 
	 * @author  Rafik LAIB on : 26 mai 2014  11:06:00
	 * @return the selectedTypeImportCode
	 */
	public String getSelectedTypeImportCode() {
		return selectedTypeImportCode;
	}

	/**
	 * [BacBB.selectedTypeImportCode] Setter 
	 * @author  Rafik LAIB on : 26 mai 2014  11:06:00
	 * @param selectedTypeImportCode the selectedTypeImportCode to set
	 */
	public void setSelectedTypeImportCode(String selectedTypeImportCode) {
		this.selectedTypeImportCode = selectedTypeImportCode;
	}

	/**
	 * [BacBB.selectedYear] Getter 
	 * @author  Rafik LAIB on : 26 mai 2014  11:31:04
	 * @return the selectedYear
	 */
	public String getSelectedYear() {
		return selectedYear;
	}

	/**
	 * [BacBB.selectedYear] Setter 
	 * @author  Rafik LAIB on : 26 mai 2014  11:31:04
	 * @param selectedYear the selectedYear to set
	 */
	public void setSelectedYear(String selectedYear) {
		this.selectedYear = selectedYear;
	}
	
	/**
	 * [BacBB.fileToImport] Getter 
	 * @author  Rafik LAIB on : 27 mai 2014  12:42:31
	 * @return the fileToImport
	 */
	public UploadedFile getFileToImport() {
		return fileToImport;
	}

	/**
	 * [BacBB.fileToImport] Setter 
	 * @author  Rafik LAIB on : 27 mai 2014  12:42:31
	 * @param fileToImport the fileToImport to set
	 */
	public void setFileToImport(UploadedFile fileToImport) {
		this.fileToImport = fileToImport;
	}

	// ===================================================================  
	// Listeners
	// ===================================================================

	/**
	 * [BacBB.showImportResultOutput] Getter 
	 * @author  Rafik LAIB on : 2 juin 2014  12:00:27
	 * @return the showImportResultOutput
	 */
	public boolean isShowImportResultOutput() {
		
		try {
			showImportResultOutput = (paramImportId != null && !paramImportId.trim().isEmpty() && !paramImportId.equals("null") && (Integer.parseInt(paramImportId)>=0));
		}
		catch (Exception e) {
	
			showImportResultOutput = false;
		}
		return showImportResultOutput;
	}
	

	/**
	 * [BacBB.showImportResultOutput] Setter 
	 * @author  Rafik LAIB on : 2 juin 2014  12:00:27
	 * @param showImportResultOutput the showImportResultOutput to set
	 */
	public void setShowImportResultOutput(boolean showImportResultOutput) {
		this.showImportResultOutput = showImportResultOutput;
	}
	
	/**
	 * [BacBB.showDatatableBachelorsFiles] Getter 
	 * @author  Rafik LAIB on : 3 juin 2014  10:30:22
	 * @return the showDatatableBachelorsFiles
	 */
	public boolean isShowDatatableBachelorsFiles() {
		
		try {
			showDatatableBachelorsFiles = (this.selectedTypeImportCode.equals(UtilConstants.BAC_IMPORT_TYPE_BACHELOR_FILE_CODE));
			
		}
		catch (Exception e) {
	
			showDatatableBachelorsFiles = false;
		}
		return showDatatableBachelorsFiles;
	}

	/**
	 * [BacBB.showDatatableBachelorsFiles] Setter 
	 * @author  Rafik LAIB on : 3 juin 2014  10:30:22
	 * @param showDatatableBachelorsFiles the showDatatableBachelorsFiles to set
	 */
	public void setShowDatatableBachelorsFiles(boolean showDatatableBachelorsFiles) {
		this.showDatatableBachelorsFiles = showDatatableBachelorsFiles;
	}
	
	/**
	 * [BacBB.showDatatableBachelorsAffectations] Getter 
	 * @author  Rafik LAIB on : 3 juin 2014  10:58:59
	 * @return the showDatatableBachelorsAffectations
	 */
	public boolean isShowDatatableBachelorsAffectations() {
		try {
			showDatatableBachelorsAffectations = (this.selectedTypeImportCode.equals(UtilConstants.BAC_IMPORT_TYPE_BACHELOR_AFFECTATION_CODE));
			
		}
		catch (Exception e) {
	
			showDatatableBachelorsAffectations = false;
		}
		return showDatatableBachelorsAffectations;
	}

	/**
	 * [BacBB.showDatatableBachelorsAffectations] Setter 
	 * @author  Rafik LAIB on : 3 juin 2014  10:58:59
	 * @param showDatatableBachelorsAffectations the showDatatableBachelorsAffectations to set
	 */
	public void setShowDatatableBachelorsAffectations(
			boolean showDatatableBachelorsAffectations) {
		this.showDatatableBachelorsAffectations = showDatatableBachelorsAffectations;
	}

	/**
	 * [BacBB.replaceLastImport] Getter 
	 * @author  Rafik LAIB on : 2 juin 2014  14:21:33
	 * @return the replaceLastImport
	 */
	public boolean isReplaceLastImport() {
		return replaceLastImport;
	}

	/**
	 * [BacBB.replaceLastImport] Setter 
	 * @author  Rafik LAIB on : 2 juin 2014  14:21:33
	 * @param replaceLastImport the replaceLastImport to set
	 */
	public void setReplaceLastImport(boolean replaceLastImport) {
		this.replaceLastImport = replaceLastImport;
	}
	
	/**
	 * [BacBB.activeImport] Getter 
	 * @author rlaib on : 13 juil. 2014  16:15:51
	 * @return the activeImport
	 */
	public boolean isActiveImport() {
		
		try {
				activeImport = (!(paramFileName != null && !paramFileName.trim().isEmpty() && !paramFileName.equals("null")));
		}
		catch (Exception e) {
	
				activeImport = false;
		}
		return activeImport;
	}

	/**
	 * [BacBB.activeImport] Setter 
	 * @author rlaib on : 13 juil. 2014  16:15:51
	 * @param activeImport the activeImport to set
	 */
	public void setActiveImport(boolean activeImport) {
		this.activeImport = activeImport;
	}
	
	/**
	 * [BacBB.filePath] Getter 
	 * @author rlaib on : 30 juin 2014  16:07:49
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * [BacBB.filePath] Setter 
	 * @author rlaib on : 30 juin 2014  16:07:49
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * [BacBB.listImports] Getter 
	 * @author  Rafik LAIB on : 1 ao�t 2014  12:59:01
	 * @return the listImports
	 */
	public List<ImportationDto> getListImports() {
		return listImports;
	}

	/**
	 * [BacBB.listImports] Setter 
	 * @author  Rafik LAIB on : 1 ao�t 2014  12:59:01
	 * @param listImports the listImports to set
	 */
	public void setListImports(List<ImportationDto> listImports) {
		this.listImports = listImports;
	}

	/**
	 * [BacBB.handleFileUpload] Method 
	 * @author  Rafik LAIB  on : 2 juin 2014  11:03:26
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
			
			 if (event.getFile().equals(null)) {
				 
				 FacesContext context = FacesContext.getCurrentInstance();
				 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aucun fichier selectionne !", null));
			 }
			 try {
				 		fileToImport = event.getFile();
				 		paramFileName = event.getFile().getFileName();
				 		uploadFile();
			 } catch (Exception e) {
				 		FacesContext context = FacesContext.getCurrentInstance();
				 		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors du chargement du fichier." + e, null));
			 }
	 		
	 }
	
	/**
	 * [BacBB.upload] Method 
	 * @author  Rafik LAIB  on : 2 juin 2014  11:04:18
	 */
	@SuppressWarnings("unchecked")
	public void importFile() {
		
			//String filePath = "src/test/resources/DBC_265000.xlsx";
		try {
			
				if(this.paramFileName != null && (!this.paramFileName.trim().equals(""))) {
				
						List<ImportationDto> existingImports = new ArrayList<ImportationDto>();
						if(!this.replaceLastImport)
								existingImports = bacService.getImportByCodeTypeByYear(selectedTypeImportCode, selectedYear);
						if(((existingImports != null)  && (existingImports.size() == 0) && (!this.replaceLastImport)) || (this.replaceLastImport)){
						try {
							
							FacesContext context;
							this.filePath = getUploadFolderPath() + paramFileName;
							this.paramImportTypeCode = this.selectedTypeImportCode;
							 
					 			switch (this.selectedTypeImportCode) {
					 			case UtilConstants.BAC_IMPORT_TYPE_BACHELOR_FILE_CODE:
					 						// Importation dossiers bacheliers
					 						FileReaderDBC fr = new FileReaderDBC();
											fr.processOneSheet(filePath);
											this.bachelorFilesToImport = FileReaderDBC.dossierBachelierDtos;
											if(bachelorFilesToImport.size() == this.totalRows) {
												
													ImportationDto importationDto = null;
													TypeImportationDto typeImportationDto = bacService. getImportTypeByCode(this.selectedTypeImportCode);
													importationDto = new ImportationDto();
													importationDto.setAnneeBac(this.selectedYear);
													importationDto.setIdTypeImportation(typeImportationDto.getId());
													importationDto.setCodeTypeImportation(typeImportationDto.getCode());
													importationDto.setLibelleTypeImportation(typeImportationDto.getLibelleFr());
													importationDto.setDateDebutImportation(new java.util.Date());
													importationDto.setSituation(UtilConstants.BAC_IMPORT_STATUS_LAUNCHED_CODE);
													importationDto = bacService.saveImport(importationDto);
													importationDto = bacService.saveListBachelorsFiles(importationDto, this.bachelorFilesToImport, this.replaceLastImport);
													if(importationDto != null) {
														this.lazyBachelorFilesModel = new LazyBachelorFileDataModel(this.bachelorFilesToImport);
														this.paramImportId = String.valueOf(importationDto.getId());
													}
													importationDto.setDateFinImportation(new java.util.Date());
													importationDto.setSituation(UtilConstants.BAC_IMPORT_STATUS_TERMINATED_CODE);
													importationDto = bacService.saveImport(importationDto);
													this.currentImportDto = importationDto;
												 	this.listImports = bacService.getAllImports();

													context = FacesContext.getCurrentInstance();
													context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Importation Dossiers Bacheliers : ", this.paramFileName + " import� avec succes."));
											}
											else {
												context = FacesContext.getCurrentInstance();
												context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Importation Dossiers Bacheliers : ", "Total des lignes saisi est diff�rent du nombre de lignes dans le fichier � importer."));
											}
								break;
								
					 			case UtilConstants.BAC_IMPORT_TYPE_BACHELOR_AFFECTATION_CODE:
					 				
					 						// Importation Affectations Bacheliers
					 						List<ImportationDto> existingConditionalImports =bacService.getImportByCodeTypeByYear(UtilConstants.BAC_IMPORT_TYPE_BACHELOR_FILE_CODE, selectedYear);
					 						if((existingConditionalImports == null) || (existingConditionalImports.size() == 0)) {
					 								// Les affectations a importer doivent avoir des dossiers bacheliers deja importes
					 								context = FacesContext.getCurrentInstance();
					 								context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Importation Affectations Bacheliers : ", "Les dossiers bacheliers doivent �tre import�s avant leurs affectations."));
					 								return;
					 						}
					 						
					 						// recuperer l importation des dossiers bacheliers correspondante.
					 						ImportationDto bachelorFilesImportation = existingConditionalImports.get(0);
					 						FileReaderAFF fra = new FileReaderAFF();
					 						fra.processOneSheet(filePath);
					 						this.bachelorAffectationsToImport = FileReaderAFF.affectationBachelierDtos;
					 						if(bachelorAffectationsToImport.size() == this.totalRows) {
					 							
							 							ImportationDto importationDto = null;
														TypeImportationDto typeImportationDto = bacService. getImportTypeByCode(this.selectedTypeImportCode);
														importationDto = new ImportationDto();
														importationDto.setAnneeBac(this.selectedYear);
														importationDto.setIdTypeImportation(typeImportationDto.getId());
														importationDto.setCodeTypeImportation(typeImportationDto.getCode());
														importationDto.setLibelleTypeImportation(typeImportationDto.getLibelleFr());
														importationDto.setDateDebutImportation(new java.util.Date());
														importationDto.setSituation(UtilConstants.BAC_IMPORT_STATUS_LAUNCHED_CODE);
														importationDto = bacService.saveImport(importationDto);
								 						importationDto = bacService.saveListBachelorsAffectations(bachelorFilesImportation, importationDto,bachelorAffectationsToImport, this.replaceLastImport);
								 						if(importationDto != null) {
								 							this.lazyBachelorAffectationsModel = new LazyBachelorAffectationDataModel(bachelorAffectationsToImport);
								 							this.paramImportId = String.valueOf(importationDto.getId());
								 						}
								 						
								 						importationDto.setDateFinImportation(new java.util.Date());
														importationDto.setSituation(UtilConstants.BAC_IMPORT_STATUS_TERMINATED_CODE);
														importationDto = bacService.saveImport(importationDto);
								 						this.currentImportDto = importationDto;
								 					 	this.listImports = bacService.getAllImports();

								 						context = FacesContext.getCurrentInstance();
								 						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Importation Affectations Bacheliers : ", this.paramFileName + " import� avec succes."));
					 						}
					 						else {
					 							context = FacesContext.getCurrentInstance();
						 						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Importation Affectations Bacheliers : ", "Total des lignes saisi est diff�rent du nombre de lignes dans le fichier � importer."));
					 						}
								break;
								
					 			default:
								break;
							}
			 
						} catch (Exception e) {
				 
							FacesContext context = FacesContext.getCurrentInstance();
							context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors du chargement du fichier.", null));
							this.paramImportId = null;
							return;
						}
				}
				else
				{
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Importation Dossiers Bacheliers : ", " L'importation choisie existe d�j�."));
					this.paramImportId = null;
					return;
				}
			}
			else {
				
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Importation Dossiers Bacheliers : ", " Aucun fichier selectionn�."));
				this.paramImportId = null;
				return;
		
			}
		}
		catch(Exception e){
			 e.printStackTrace();
		 }
    }
	
	public void parse() {
		
		uploadFile();

//		SimpleXLSXWorkbook workbook= new SimpleXLSXWorkbook(new File(this.filePath));
//
//        XSSFWorkbook hsfWorkbook = new XSSFWorkbook();
//
//        org.apache.poi.ss.usermodel.Sheet hsfSheet = hsfWorkbook.createSheet();
//
//        Sheet sheetToRead = workbook.getSheet(0, false);
//
//        SheetRowReader reader = sheetToRead.newReader();
//        com.incesoft.tools.excel.xlsx.Cell[] row;
//        int rowPos = 0;
//        
//        while ((row =  reader.readRow()) != null) {
//        	
//            org.apache.poi.ss.usermodel.Row hfsRow = hsfSheet.createRow(rowPos);
//            int cellPos = 0;
//            for (com.incesoft.tools.excel.xlsx.Cell cell : row) {
//            	
//                if(cell != null){
//                    org.apache.poi.ss.usermodel.Cell hfsCell = hfsRow.createCell(cellPos);
//                    hfsCell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
//                    hfsCell.setCellValue(cell.getValue());
//                }
//                cellPos++;
//            }
//            rowPos++;
//        }
//        
//        List<ImportationDto> existingConditionalImports =bacService.getImportByCodeTypeByYear(UtilConstants.BAC_IMPORT_TYPE_BACHELOR_FILE_CODE, selectedYear);
//		if((existingConditionalImports == null) || (existingConditionalImports.size() == 0)) {
//			
//			// Les affectations a importer doivent avoir des dossiers bacheliers deja importes
//			FacesContext context = FacesContext.getCurrentInstance();
//				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Importation Affectations Bacheliers : ", "Les dossiers bacheliers doivent �tre import�s avant leurs affectations."));
//			return;
//		}
//		
//		// recuperer l importation des dossiers bacheliers correspondante.
//		ImportationDto bachelorFilesImportation = existingConditionalImports.get(0); 
//        ImportationDto importationDto = getDtosFromSheetXlsx(hsfSheet);
//        if(importationDto != null) {
//        	importationDto = bacService.saveListBachelorsFiles(importationDto, bachelorFilesToImport, this.replaceLastImport);
//        }
	}

	/**
	 * [BacBB.uploadFile] Method 
	 * @author rlaib  on : 5 juil. 2014  12:04:31
	 */
	private void uploadFile() {
		
		InputStream inputStream = null;
		try {
			this.filePath =  getUploadFolderPath() + paramFileName;
			inputStream = fileToImport.getInputstream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// generer un nom de fichier unique
//		String fileUrl = FileUtility.getUidFileName(event.getFile()
//				.getFileName());

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
	 * [BacBB.getUploadFolderPath] Method 
	 * @author rlaib  on : 13 juil. 2014  14:37:47
	 */
	private String getUploadFolderPath() {
	    return  configHolder.getDocumentTempFolder() + "/";
	}
	
	public void  handleImportTypesChange(AjaxBehaviorEvent event) {
		paramImportId = null;
		paramFileName = null;
	}
	// ===================================================================  
	// Actions and Methods
	// ===================================================================

	
	// ===================================================================  
	// Functions Helper
	// ===================================================================

	/**
	 * [BacBB.initTypesList] Method 
	 * @author  Rafik LAIB  on : 25 mai 2014  17:40:46
	 */
	private List<SelectItem> getTypesList() {
		
		try	{
			 
				List<TypeImportationDto> _list = bacService.getAllTypesImport();
				List<SelectItem> result = new ArrayList<SelectItem>();
				for (TypeImportationDto typeImportationDto : _list) {
					result.add(new SelectItem(typeImportationDto.getCode(), typeImportationDto.getLibelleFr()));
				}
				return result;
			 
		 }
		 catch(Exception e){
			 
			 e.printStackTrace();
			 return new ArrayList<SelectItem>();
		 
		 }
		
	}
	
	/**
	 * [BacBB.getYearsList] Method 
	 * @author  Rafik LAIB  on : 26 mai 2014  11:09:26
	 * @return
	 */
	private List<SelectItem> getYearsList() {
		
		try	{
			 
			List<AnneeAcademiqueDto> _list = anneeAcademiqueService.findAll();
			List<SelectItem> result = new ArrayList<SelectItem>();
			for (AnneeAcademiqueDto anneeAcademiqueDto : _list) {
				result.add(new SelectItem(anneeAcademiqueDto.getDeuxiemeAnnee(), anneeAcademiqueDto.getPremiereAnnee() + " / " + anneeAcademiqueDto.getDeuxiemeAnnee()));
			}
			return result;
		 
		}
		catch(Exception e){
		 
			e.printStackTrace();
			return new ArrayList<SelectItem>();
		}
		
	}


}
