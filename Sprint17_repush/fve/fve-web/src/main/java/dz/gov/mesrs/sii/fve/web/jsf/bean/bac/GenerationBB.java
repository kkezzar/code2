/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.bac.BacBB.java] 
 * @author  Rafik LAIB on : 21 mai 2014  11:18:57
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.bac;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import dz.gov.mesrs.sii.commons.data.model.fve.bac.DossierBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierEtudiant;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.AffectationBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.CorrespondanceDomaineDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.DossierBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.GenerationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.ImportationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.PieceConstitutiveDto;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.service.bac.CorrespondanceDomaineService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTypePieceConstitutiveDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
//import dz.gov.mesrs.sii.referentiel.ws.service.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefTypePieceConstitutiveService;

/**
 * @author  Rafik LAIB  on : 21 mai 2014  11:18:57
 */
@ManagedBean(name = "generationBB")
@ViewScoped
public class GenerationBB {

	// ===================================================================  
	// Constructors
	// ===================================================================
	public GenerationBB() {
		
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
						initDataToGenerate();
		 }
		 catch(Exception e){
			 
			 e.printStackTrace();
		 
		 }
	
	}
	
	/**
	 * [GenerationBB.getFilesWithoutCorrespondanceAffectationData] Method 
	 * @author rlaib  on : 23 sept. 2014  14:14:03
	 */
	private void getFilesWithoutCorrespondanceAffectationData() {

			try {
				
						searchDto = new DossierBachelierDto();
						searchDto.setRefCodeEtablissementAffectation(oldEtabCode);
						this.bachelorsFilesWithoutCorrespondanceAffectation = bacService.findAdvanced(searchDto, null, true, true, this.year,null, null);
			}
			catch(Exception e){
				 		e.printStackTrace();
		 	 }
	
	}

	/**
	 * [GenerationBB.initDataToGenerate] Method 
	 * @author rlaib  on : 25 juin 2014  16:24:56
	 */
	private void initDataToGenerate() {
		
		try {
					// Initializing Wrong Data Affectations
		 			DossierBachelierDto searchDto = new  DossierBachelierDto();
					searchDto.setRefCodeEtablissementAffectation(oldEtabCode);
					this.totalResult = this.totalBachelorsFiles = bacService.getFindAdvancedCount(searchDto, null, true,false, this.year );
		 			this.bachelorsFilesToGenerate = null;
					if(totalResult != null && totalResult > 0) {
						getFilesWithoutCorrespondanceAffectationData();
						if(this.bachelorsFilesWithoutCorrespondanceAffectation != null && this.bachelorsFilesWithoutCorrespondanceAffectation .size()>0) {
								CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
													OfConstants.BAC_GENERATION_DOSSIERS_MESSAGES_FILE_NAME, OfConstants.BAC_GENERATION_BUNDLE_KEY_MSG_INIT_DATA_AFFECTATIONS_ERRONEE)
				 						,CommonMessagesUtils.getStringValueFromBundleResource(OfConstants.BAC_GENERATION_DOSSIERS_MESSAGES_FILE_NAME,OfConstants.BAC_GENERATION_BUNDLE_KEY_MSG_INIT_DATA_AFFECTATIONS_ERRONEE)); 
								showErrorOutput = true;
							}
							else {
								showErrorOutput = false;
								activeGeneration = false;
							}
					}
					else {
						
						Object[] params = {this.year};
						CommonMessagesUtils.addWarnMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(
										OfConstants.BAC_GENERATION_DOSSIERS_MESSAGES_FILE_NAME,OfConstants.BAC_GENERATION_BUNDLE_KEY_MSG_INIT_DATA_AUCUN_DOSSIER)
								,CommonMessagesUtils.getStringValueFromBundleResourceWithParams(
										OfConstants.BAC_GENERATION_DOSSIERS_MESSAGES_FILE_NAME,OfConstants.BAC_GENERATION_BUNDLE_KEY_MSG_INIT_DATA_AUCUN_DOSSIER, params,"fr")); 
						activeGeneration = true;
					}
					this.listGenerations = bacService.findGenerationsByEtab(newEtabCode);
						
		}
		catch (Exception e) {
					e.printStackTrace();
		}
	}

	// ===================================================================  
	// Beans Services 
	// ===================================================================
	@ManagedProperty(value="#{bacService}")
	private BacService bacService;
	
	@ManagedProperty(value="#{correspondanceDomaineService}")
	private CorrespondanceDomaineService correspondanceDomaineService;
	
	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;
	
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuServiceImpl;
	
	@ManagedProperty(value = "#{refTypePieceConstitutiveServiceImpl}")
	private RefTypePieceConstitutiveService refTypePieceConstitutiveService;
	
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	
	// ===================================================================  
	// Parameters
	// ===================================================================
	private Integer totalResult;

	// ===================================================================  
	// Properties Model
	// ===================================================================
	private List<DossierBachelierDto> bachelorsFilesToGenerate;
	private List<DossierBachelierDto> bachelorsFilesWithoutCorrespondanceAffectation;
	private List<AffectationBachelierDto> AffectationsToGenerate;
	private DossierBachelierDto selectedRow;
	private DossierBachelierDto searchDto;
	private List<CorrespondanceDomaineDto> listFilieresBac;
	private String year;
	private List<SelectItem> listYears;
	private Integer totalGeneratedIndividus;
	private Integer totalExistingIndividus;
	private Integer totalGeneratedStudentsFiles;
	private Integer totalGeneratedStudentsInscriptionFiles;
	private Integer totalBachelorsFiles;
	private Integer idYear;
	private String oldEtabCode;
	private String newEtabCode;
	private String libelleEtab;
	private List<DossierEtudiantDto> generatedStudentsFiles;
	private List<DossierInscriptionAdministrativeDto> generatedStudentsInscriptionsFiles;
	private List<RefIndividuDto> generatedIndividus;
	private List<RefIndividuDto> existingIndividus;
	private boolean showResultOutput;
	private boolean showErrorOutput;
	private boolean activeGeneration;
	private ImportationDto importToGenerate;
	private List<GenerationDto> listGenerations;
	private GenerationDto currentGeneration;
	private List<DossierBachelier>  dossierBacheliers;
	private List<DossierEtudiant> dossierEtudiants;
	private List<DossierInscriptionAdministrative> dossiersInscriptionAdministratives;
	private List<RefIndividu> individusGeneres;
	private List<RefIndividu> individusExistants;
	
	
	/**
	 * [GenerationBB.dossierBacheliers] Getter 
	 * @author rlaib on : 9 nov. 2014  08:29:53
	 * @return the dossierBacheliers
	 */
	public List<DossierBachelier> getDossierBacheliers() {
		return dossierBacheliers;
	}

	/**
	 * [GenerationBB.dossierBacheliers] Setter 
	 * @author rlaib on : 9 nov. 2014  08:29:53
	 * @param dossierBacheliers the dossierBacheliers to set
	 */
	public void setDossierBacheliers(List<DossierBachelier> dossierBacheliers) {
		this.dossierBacheliers = dossierBacheliers;
	}

	/**
	 * [GenerationBB.dossierEtudiants] Getter 
	 * @author rlaib on : 9 nov. 2014  08:29:53
	 * @return the dossierEtudiants
	 */
	public List<DossierEtudiant> getDossierEtudiants() {
		return dossierEtudiants;
	}

	/**
	 * [GenerationBB.dossierEtudiants] Setter 
	 * @author rlaib on : 9 nov. 2014  08:29:53
	 * @param dossierEtudiants the dossierEtudiants to set
	 */
	public void setDossierEtudiants(List<DossierEtudiant> dossierEtudiants) {
		this.dossierEtudiants = dossierEtudiants;
	}

	/**
	 * [GenerationBB.dossiersInscriptionAdministratives] Getter 
	 * @author rlaib on : 9 nov. 2014  08:29:53
	 * @return the dossiersInscriptionAdministratives
	 */
	public List<DossierInscriptionAdministrative> getDossiersInscriptionAdministratives() {
		return dossiersInscriptionAdministratives;
	}

	/**
	 * [GenerationBB.dossiersInscriptionAdministratives] Setter 
	 * @author rlaib on : 9 nov. 2014  08:29:53
	 * @param dossiersInscriptionAdministratives the dossiersInscriptionAdministratives to set
	 */
	public void setDossiersInscriptionAdministratives(
			List<DossierInscriptionAdministrative> dossiersInscriptionAdministratives) {
		this.dossiersInscriptionAdministratives = dossiersInscriptionAdministratives;
	}

	/**
	 * [GenerationBB.individusGeneres] Getter 
	 * @author rlaib on : 9 nov. 2014  08:29:53
	 * @return the individusGeneres
	 */
	public List<RefIndividu> getIndividusGeneres() {
		return individusGeneres;
	}

	/**
	 * [GenerationBB.individusGeneres] Setter 
	 * @author rlaib on : 9 nov. 2014  08:29:53
	 * @param individusGeneres the individusGeneres to set
	 */
	public void setIndividusGeneres(List<RefIndividu> individusGeneres) {
		this.individusGeneres = individusGeneres;
	}

	/**
	 * [GenerationBB.individusExistants] Getter 
	 * @author rlaib on : 9 nov. 2014  08:29:53
	 * @return the individusExistants
	 */
	public List<RefIndividu> getIndividusExistants() {
		return individusExistants;
	}

	/**
	 * [GenerationBB.individusExistants] Setter 
	 * @author rlaib on : 9 nov. 2014  08:29:53
	 * @param individusExistants the individusExistants to set
	 */
	public void setIndividusExistants(List<RefIndividu> individusExistants) {
		this.individusExistants = individusExistants;
	}

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

	// ===================================================================  
	// Parameters Getters / Setters
	// ===================================================================
	
	// ===================================================================  
	// Properties Model Getters / Setters
	// ===================================================================
	
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
	 * [GenerationBB.bachelorsFilesToGenerate] Getter 
	 * @author rlaib on : 23 juin 2014  16:22:35
	 * @return the bachelorsFilesToGenerate
	 */
	public List<DossierBachelierDto> getBachelorsFilesToGenerate() {
		return bachelorsFilesToGenerate;
	}

	/**
	 * [GenerationBB.bachelorsFilesToGenerate] Setter 
	 * @author rlaib on : 23 juin 2014  16:22:36
	 * @param bachelorsFilesToGenerate the bachelorsFilesToGenerate to set
	 */
	public void setBachelorsFilesToGenerate(
			List<DossierBachelierDto> bachelorsFilesToGenerate) {
		this.bachelorsFilesToGenerate = bachelorsFilesToGenerate;
	}

	/**
	 * [GenerationBB.bachelorsFilesWithoutCorrespondanceAffectation] Getter 
	 * @author rlaib on : 18 sept. 2014  14:21:29
	 * @return the bachelorsFilesWithoutCorrespondanceAffectation
	 */
	public List<DossierBachelierDto> getBachelorsFilesWithoutCorrespondanceAffectation() {
		return bachelorsFilesWithoutCorrespondanceAffectation;
	}

	/**
	 * [GenerationBB.bachelorsFilesWithoutCorrespondanceAffectation] Setter 
	 * @author rlaib on : 18 sept. 2014  14:21:29
	 * @param bachelorsFilesWithoutCorrespondanceAffectation the bachelorsFilesWithoutCorrespondanceAffectation to set
	 */
	public void setBachelorsFilesWithoutCorrespondanceAffectation(
			List<DossierBachelierDto> bachelorsFilesWithoutCorrespondanceAffectation) {
		this.bachelorsFilesWithoutCorrespondanceAffectation = bachelorsFilesWithoutCorrespondanceAffectation;
	}

	/**
	 * [GenerationBB.affectationsToGenerate] Getter 
	 * @author rlaib on : 31 juil. 2014  12:43:16
	 * @return the affectationsToGenerate
	 */
	public List<AffectationBachelierDto> getAffectationsToGenerate() {
		return AffectationsToGenerate;
	}

	/**
	 * [GenerationBB.affectationsToGenerate] Setter 
	 * @author rlaib on : 31 juil. 2014  12:43:16
	 * @param affectationsToGenerate the affectationsToGenerate to set
	 */
	public void setAffectationsToGenerate(
			List<AffectationBachelierDto> affectationsToGenerate) {
		AffectationsToGenerate = affectationsToGenerate;
	}

	/**
	 * [GenerationBB.year] Getter 
	 * @author rlaib on : 23 juin 2014  12:46:22
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * [GenerationBB.year] Setter 
	 * @author rlaib on : 23 juin 2014  12:46:22
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	
	/**
	 * [GenerationBB.listYears] Getter 
	 * @author rlaib on : 23 sept. 2014  14:08:03
	 * @return the listYears
	 */
	public List<SelectItem> getListYears() {
		return listYears;
	}

	/**
	 * [GenerationBB.listYears] Setter 
	 * @author rlaib on : 23 sept. 2014  14:08:03
	 * @param listYears the listYears to set
	 */
	public void setListYears(List<SelectItem> listYears) {
		this.listYears = listYears;
	}

	/**
	 * [GenerationBB.totalGeneratedIndividus] Getter 
	 * @author rlaib on : 25 juin 2014  15:02:43
	 * @return the totalGeneratedIndividus
	 */
	public Integer getTotalGeneratedIndividus() {
		return totalGeneratedIndividus;
	}

	/**
	 * [GenerationBB.totalGeneratedIndividus] Setter 
	 * @author rlaib on : 25 juin 2014  15:02:43
	 * @param totalGeneratedIndividus the totalGeneratedIndividus to set
	 */
	public void setTotalGeneratedIndividus(Integer totalGeneratedIndividus) {
		this.totalGeneratedIndividus = totalGeneratedIndividus;
	}

	/**
	 * [GenerationBB.totalExistingIndividus] Getter 
	 * @author rlaib on : 25 juin 2014  15:02:43
	 * @return the totalExistingIndividus
	 */
	public Integer getTotalExistingIndividus() {
		return totalExistingIndividus;
	}

	/**
	 * [GenerationBB.totalExistingIndividus] Setter 
	 * @author rlaib on : 25 juin 2014  15:02:43
	 * @param totalExistingIndividus the totalExistingIndividus to set
	 */
	public void setTotalExistingIndividus(Integer totalExistingIndividus) {
		this.totalExistingIndividus = totalExistingIndividus;
	}

	/**
	 * [GenerationBB.totalGeneratedStudentsFiles] Getter 
	 * @author rlaib on : 25 juin 2014  15:02:43
	 * @return the totalGeneratedStudentsFiles
	 */
	public Integer getTotalGeneratedStudentsFiles() {
		return totalGeneratedStudentsFiles;
	}

	/**
	 * [GenerationBB.totalGeneratedStudentsFiles] Setter 
	 * @author rlaib on : 25 juin 2014  15:02:43
	 * @param totalGeneratedStudentsFiles the totalGeneratedStudentsFiles to set
	 */
	public void setTotalGeneratedStudentsFiles(Integer totalGeneratedStudentsFiles) {
		this.totalGeneratedStudentsFiles = totalGeneratedStudentsFiles;
	}
	
	
	/**
	 * [GenerationBB.totalGeneratedStudentsInscriptionFiles] Getter 
	 * @author rlaib on : 29 juin 2014  09:36:05
	 * @return the totalGeneratedStudentsInscriptionFiles
	 */
	public Integer getTotalGeneratedStudentsInscriptionFiles() {
		return totalGeneratedStudentsInscriptionFiles;
	}

	/**
	 * [GenerationBB.totalGeneratedStudentsInscriptionFiles] Setter 
	 * @author rlaib on : 29 juin 2014  09:36:05
	 * @param totalGeneratedStudentsInscriptionFiles the totalGeneratedStudentsInscriptionFiles to set
	 */
	public void setTotalGeneratedStudentsInscriptionFiles(
			Integer totalGeneratedStudentsInscriptionFiles) {
		this.totalGeneratedStudentsInscriptionFiles = totalGeneratedStudentsInscriptionFiles;
	}

	/**
	 * [GenerationBB.totalBachelorsFiles] Getter 
	 * @author rlaib on : 25 juin 2014  17:00:49
	 * @return the totalBachelorsFiles
	 */
	public Integer getTotalBachelorsFiles() {
		return totalBachelorsFiles;
	}

	/**
	 * [GenerationBB.totalBachelorsFiles] Setter 
	 * @author rlaib on : 25 juin 2014  17:00:49
	 * @param totalBachelorsFiles the totalBachelorsFiles to set
	 */
	public void setTotalBachelorsFiles(Integer totalBachelorsFiles) {
		this.totalBachelorsFiles = totalBachelorsFiles;
	}

	/**
	 * [GenerationBB.idYear] Getter 
	 * @author rlaib on : 24 juin 2014  17:46:26
	 * @return the idYear
	 */
	public Integer getIdYear() {
		return idYear;
	}

	/**
	 * [GenerationBB.idYear] Setter 
	 * @author rlaib on : 24 juin 2014  17:46:26
	 * @param idYear the idYear to set
	 */
	public void setIdYear(Integer idYear) {
		this.idYear = idYear;
	}

	/**
	 * [GenerationBB.generatedStudentsFiles] Getter 
	 * @author rlaib on : 24 juin 2014  17:46:26
	 * @return the generatedStudentsFiles
	 */
	public List<DossierEtudiantDto> getGeneratedStudentsFiles() {
		return generatedStudentsFiles;
	}

	/**
	 * [GenerationBB.generatedStudentsFiles] Setter 
	 * @author rlaib on : 24 juin 2014  17:46:26
	 * @param generatedStudentsFiles the generatedStudentsFiles to set
	 */
	public void setGeneratedStudentsFiles(
			List<DossierEtudiantDto> generatedStudentsFiles) {
		this.generatedStudentsFiles = generatedStudentsFiles;
	}

	/**
	 * [GenerationBB.generatedStudentsInscriptionsFiles] Getter 
	 * @author rlaib on : 24 juin 2014  17:46:26
	 * @return the generatedStudentsInscriptionsFiles
	 */
	public List<DossierInscriptionAdministrativeDto> getGeneratedStudentsInscriptionsFiles() {
		return generatedStudentsInscriptionsFiles;
	}

	/**
	 * [GenerationBB.generatedStudentsInscriptionsFiles] Setter 
	 * @author rlaib on : 24 juin 2014  17:46:26
	 * @param generatedStudentsInscriptionsFiles the generatedStudentsInscriptionsFiles to set
	 */
	public void setGeneratedStudentsInscriptionsFiles(
			List<DossierInscriptionAdministrativeDto> generatedStudentsInscriptionsFiles) {
		this.generatedStudentsInscriptionsFiles = generatedStudentsInscriptionsFiles;
	}

	/**
	 * [GenerationBB.oldEtabCode] Getter 
	 * @author rlaib on : 23 juin 2014  12:51:15
	 * @return the oldEtabCode
	 */
	public String getOldEtabCode() {
		return oldEtabCode;
	}

	/**
	 * [GenerationBB.oldEtabCode] Setter 
	 * @author rlaib on : 23 juin 2014  12:51:15
	 * @param oldEtabCode the oldEtabCode to set
	 */
	public void setOldEtabCode(String oldEtabCode) {
		this.oldEtabCode = oldEtabCode;
	}

	/**
	 * [GenerationBB.newEtabCode] Getter 
	 * @author rlaib on : 23 juin 2014  12:51:15
	 * @return the newEtabCode
	 */
	public String getNewEtabCode() {
		return newEtabCode;
	}

	/**
	 * [GenerationBB.newEtabCode] Setter 
	 * @author rlaib on : 23 juin 2014  12:51:15
	 * @param newEtabCode the newEtabCode to set
	 */
	public void setNewEtabCode(String newEtabCode) {
		this.newEtabCode = newEtabCode;
	}
	
	/**
	 * [GenerationBB.libelleEtab] Getter 
	 * @author rlaib on : 23 juin 2014  16:23:50
	 * @return the libelleEtab
	 */
	public String getLibelleEtab() {
		return libelleEtab;
	}

	/**
	 * [GenerationBB.libelleEtab] Setter 
	 * @author rlaib on : 23 juin 2014  16:23:50
	 * @param libelleEtab the libelleEtab to set
	 */
	public void setLibelleEtab(String libelleEtab) {
		this.libelleEtab = libelleEtab;
	}
	
	/**
	 * [GenerationBB.totalResult] Getter 
	 * @author rlaib on : 23 juin 2014  16:49:30
	 * @return the totalResult
	 */
	public Integer getTotalResult() {
		return totalResult;
	}

	/**
	 * [GenerationBB.totalResult] Setter 
	 * @author rlaib on : 23 juin 2014  16:49:30
	 * @param totalResult the totalResult to set
	 */
	public void setTotalResult(Integer totalResult) {
		this.totalResult = totalResult;
	}

	// ===================================================================  
	// Beans Services  Getters / Setters
	// ===================================================================
	
	/**
	 * [GenerationBB.sessionBean] Getter 
	 * @author rlaib on : 23 juin 2014  12:50:18
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		if(sessionBean == null)
			sessionBean = new SessionBean();
		return sessionBean;
	}

	/**
	 * [GenerationBB.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  14:52:33
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [GenerationBB.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  14:52:33
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [GenerationBB.sessionBean] Setter 
	 * @author rlaib on : 23 juin 2014  12:50:18
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
	
	/**
	 * [GenerationBB.refIndividuServiceImpl] Getter 
	 * @author rlaib on : 25 sept. 2014  10:54:58
	 * @return the refIndividuServiceImpl
	 */
	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}

	/**
	 * [GenerationBB.refIndividuServiceImpl] Setter 
	 * @author rlaib on : 25 sept. 2014  10:54:58
	 * @param refIndividuServiceImpl the refIndividuServiceImpl to set
	 */
	public void setRefIndividuServiceImpl(RefIndividuService refIndividuServiceImpl) {
		this.refIndividuServiceImpl = refIndividuServiceImpl;
	}

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
	 * [GenerationBB.searchDto] Getter 
	 * @author rlaib on : 18 sept. 2014  14:23:54
	 * @return the searchDto
	 */
	public DossierBachelierDto getSearchDto() {
		if(searchDto == null) 
			searchDto = new DossierBachelierDto();
		return searchDto;
	}

	/**
	 * [GenerationBB.searchDto] Setter 
	 * @author rlaib on : 18 sept. 2014  14:23:54
	 * @param searchDto the searchDto to set
	 */
	public void setSearchDto(DossierBachelierDto searchDto) {
		this.searchDto = searchDto;
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
	 * [GenerationBB.generatedIndividus] Getter 
	 * @author rlaib on : 25 juin 2014  12:22:28
	 * @return the generatedIndividus
	 */
	public List<RefIndividuDto> getGeneratedIndividus() {
		return generatedIndividus;
	}

	/**
	 * [GenerationBB.generatedIndividus] Setter 
	 * @author rlaib on : 25 juin 2014  12:22:28
	 * @param generatedIndividus the generatedIndividus to set
	 */
	public void setGeneratedIndividus(List<RefIndividuDto> generatedIndividus) {
		this.generatedIndividus = generatedIndividus;
	}

	/**
	 * [GenerationBB.existingIndividus] Getter 
	 * @author rlaib on : 25 juin 2014  12:22:28
	 * @return the existingIndividus
	 */
	public List<RefIndividuDto> getExistingIndividus() {
		return existingIndividus;
	}

	/**
	 * [GenerationBB.existingIndividus] Setter 
	 * @author rlaib on : 25 juin 2014  12:22:28
	 * @param existingIndividus the existingIndividus to set
	 */
	public void setExistingIndividus(List<RefIndividuDto> existingIndividus) {
		this.existingIndividus = existingIndividus;
	}

	
		/**
	 * [GenerationBB.showResultOutput] Getter 
	 * @author rlaib on : 25 juin 2014  14:55:42
	 * @return the showResultOutput
	 */
	public boolean isShowResultOutput() {
		return showResultOutput;
	}
	
	/**
		 * [GenerationBB.showErrorOutput] Getter 
		 * @author rlaib on : 18 sept. 2014  14:46:23
		 * @return the showErrorOutput
		 */
		public boolean isShowErrorOutput() {
			return showErrorOutput;
		}

		/**
		 * [GenerationBB.showErrorOutput] Setter 
		 * @author rlaib on : 18 sept. 2014  14:46:23
		 * @param showErrorOutput the showErrorOutput to set
		 */
		public void setShowErrorOutput(boolean showErrorOutput) {
			this.showErrorOutput = showErrorOutput;
		}

	/**
	 * [GenerationBB.showResultOutput] Setter 
	 * @author rlaib on : 25 juin 2014  14:55:42
	 * @param showResultOutput the showResultOutput to set
	 */
	public void setShowResultOutput(boolean showResultOutput) {
		this.showResultOutput = showResultOutput;
	}
	
		/**
	 * [GenerationBB.activeGeneration] Getter 
	 * @author rlaib on : 25 juin 2014  16:29:31
	 * @return the activeGeneration
	 */
	public boolean isActiveGeneration() {
	
		return activeGeneration;
	}

	/**
	 * [GenerationBB.activeGeneration] Setter 
	 * @author rlaib on : 25 juin 2014  16:29:31
	 * @param activeGeneration the activeGeneration to set
	 */
	public void setActiveGeneration(boolean activeGeneration) {
		this.activeGeneration = activeGeneration;
	}

		/**
	 * [GenerationBB.importToGenerate] Getter 
	 * @author rlaib on : 31 juil. 2014  12:05:14
	 * @return the importToGenerate
	 */
	public ImportationDto getImportToGenerate() {
		return importToGenerate;
	}

	/**
	 * [GenerationBB.importToGenerate] Setter 
	 * @author rlaib on : 31 juil. 2014  12:05:14
	 * @param importToGenerate the importToGenerate to set
	 */
	public void setImportToGenerate(ImportationDto importToGenerate) {
		this.importToGenerate = importToGenerate;
	}

		/**
	 * [GenerationBB.listGenerations] Getter 
	 * @author rlaib on : 2 ao�t 2014  11:07:48
	 * @return the listGenerations
	 */
	public List<GenerationDto> getListGenerations() {
		return listGenerations;
	}

	/**
	 * [GenerationBB.listGenerations] Setter 
	 * @author rlaib on : 2 ao�t 2014  11:07:48
	 * @param listGenerations the listGenerations to set
	 */
	public void setListGenerations(List<GenerationDto> listGenerations) {
		this.listGenerations = listGenerations;
	}
		
		/**
	 * [GenerationBB.currentGeneration] Getter 
	 * @author rlaib on : 27 sept. 2014  09:57:29
	 * @return the currentGeneration
	 */
	public GenerationDto getCurrentGeneration() {
		return currentGeneration;
	}

	/**
	 * [GenerationBB.currentGeneration] Setter 
	 * @author rlaib on : 27 sept. 2014  09:57:29
	 * @param currentGeneration the currentGeneration to set
	 */
	public void setCurrentGeneration(GenerationDto currentGeneration) {
		this.currentGeneration = currentGeneration;
	}
	
		/**
	 * [GenerationBB.refTypePieceConstitutiveService] Getter 
	 * @author rlaib on : 20 nov. 2014  14:51:06
	 * @return the refTypePieceConstitutiveService
	 */
	public RefTypePieceConstitutiveService getRefTypePieceConstitutiveService() {
		return refTypePieceConstitutiveService;
	}

	/**
	 * [GenerationBB.refTypePieceConstitutiveService] Setter 
	 * @author rlaib on : 20 nov. 2014  14:51:06
	 * @param refTypePieceConstitutiveService the refTypePieceConstitutiveService to set
	 */
	public void setRefTypePieceConstitutiveService(
			RefTypePieceConstitutiveService refTypePieceConstitutiveService) {
		this.refTypePieceConstitutiveService = refTypePieceConstitutiveService;
	}

	// ===================================================================  
	// Listeners
	// ===================================================================


		@SuppressWarnings("unchecked")
		public void generateBatch() {
			try {
							Map<String, Object> result = bacService.generateBach(this.year, this.oldEtabCode, this.newEtabCode, this.sessionBean.getIdEtablissement());
						   dossierBacheliers = (List<DossierBachelier>) result.get(DossierBachelier.class.getSimpleName());
						   dossierEtudiants =  (List<DossierEtudiant>) result.get(DossierEtudiant.class.getSimpleName());
						   dossiersInscriptionAdministratives =  (List<DossierInscriptionAdministrative>) result.get(DossierInscriptionAdministrative.class.getSimpleName());
						   individusGeneres =  (List<RefIndividu>) result.get(RefIndividu.class.getSimpleName());
						   individusExistants = (List<RefIndividu>) result.get(RefIndividu.class.getSimpleName() +"1");
						   totalBachelorsFiles = dossierBacheliers.size();
						   totalResult = dossierBacheliers.size();
						   totalGeneratedStudentsFiles = dossierEtudiants.size();
						   totalGeneratedStudentsInscriptionFiles = dossiersInscriptionAdministratives.size();
						   totalGeneratedIndividus = individusGeneres.size();
						   totalExistingIndividus = individusExistants.size();
							// Re chargement de l hisorique des generations
							this.listGenerations = bacService.findGenerationsByEtab(newEtabCode);
							this.showResultOutput = true;
							activeGeneration = true;
							// Generation resussie
							CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
									.getStringValueFromBundleResource(
											OfConstants.BAC_GENERATION_DOSSIERS_MESSAGES_FILE_NAME,
											OfConstants.BAC_GENERATION_BUNDLE_KEY_MSG_GENERATiON_SUCCESS),
									CommonMessagesUtils.getStringValueFromBundleResource(
											OfConstants.BAC_GENERATION_DOSSIERS_MESSAGES_FILE_NAME,
											OfConstants.BAC_GENERATION_BUNDLE_KEY_MSG_GENERATiON_SUCCESS));
							
			}
			catch (Exception e) {
				e.printStackTrace();
				CommonMessagesUtils.addErrorMessageWithoutKey(
						CommonMessagesUtils.getStringValueFromBundleResource(OfConstants.BAC_GENERATION_DOSSIERS_MESSAGES_FILE_NAME,
												OfConstants.BAC_GENERATION_BUNDLE_KEY_MSG_GENERATiON_FAILURE)
						,CommonMessagesUtils.getStringValueFromBundleResource(OfConstants.BAC_GENERATION_DOSSIERS_MESSAGES_FILE_NAME,
												OfConstants.BAC_GENERATION_BUNDLE_KEY_MSG_GENERATiON_FAILURE)); 
			}
		}
		
		public void generate() {
		try {
			}
			catch (Exception e) {
						e.printStackTrace();
			}
		}

		/**
		 * [GenerationBB.createGenerationOperation] Method 
		 * @author rlaib  on : 27 sept. 2014  09:48:56
		 * @return
		 */
		@SuppressWarnings("unused")
		private GenerationDto saveGenerationOperation() {
			
			try {
					if (this.currentGeneration != null && this.currentGeneration.getId() == 0) {
						currentGeneration.setAnneeAcademique(this.year);
						currentGeneration.setRefCodeEtablissement(this.newEtabCode);
						currentGeneration.setDateDebutGeneration(new Date());
						currentGeneration.setNbBacheliers(bachelorsFilesToGenerate.size());
						currentGeneration.setNbDossiersEtudiants(0);
						currentGeneration.setNbDossiersInscriptions(0);
						currentGeneration.setNbIndividusGeneres(0);
						currentGeneration.setNbIndividusExistants(0);
						currentGeneration.setSituation(UtilConstants.BAC_IMPORT_STATUS_LAUNCHED_CODE);
						
					}
					currentGeneration = bacService.saveGeneration(currentGeneration);
					return currentGeneration;
			}
			catch (Exception e) {
					e.printStackTrace();
					return null;
			}
		}

		/**
		 * [GenerationBB.getContitutivesPeaces] Method 
		 * @author rlaib  on : 27 sept. 2014  09:44:13
		 * @param listPieces
		 * @return
		 */
		@SuppressWarnings("unused")
		private List<PieceConstitutiveDto> getContitutivesPeaces() {
			try {
					List<PieceConstitutiveDto> listPieces = null;
					List<RefTypePieceConstitutiveDto> listRefTypePieceConstitutiveDto = refTypePieceConstitutiveService
			          .findByCodeTypeDossierDate(
			                     UtilConstants.CODE_TYPE_DOSSIER_INSCRIPTION_PREMIERE_ANNEE, new Date());
			               
					listPieces = new ArrayList<PieceConstitutiveDto>();
					for (RefTypePieceConstitutiveDto current : listRefTypePieceConstitutiveDto) {
							
							PieceConstitutiveDto pieceConstitutiveDto = new PieceConstitutiveDto();
							pieceConstitutiveDto.setFournie(false);
							pieceConstitutiveDto.setFournie(false);
							//pieceConstitutiveDto.setNcTypePieceId(current.getIdTypePiece());
							pieceConstitutiveDto.setRefTypePieceId(current.getId());
							listPieces.add(pieceConstitutiveDto);
					}
					return listPieces;
				} catch (Exception e) {
					
					e.printStackTrace();
					return null;
				}
		}

		/**
		 * [GenerationBB.preparePersonsToGenerate] Method 
		 * @author rlaib  on : 27 sept. 2014  09:33:25
		 * @param refIndividuDtos
		 * @throws Exception_Exception
		 */
		@SuppressWarnings("unused")
		private List<RefIndividuDto> preparePersonsToGenerate()
				throws Exception {
			
				try {
						List<RefIndividuDto> refIndividuDtos = new ArrayList<RefIndividuDto>();
						List<NomenclatureDto>  civiliteDtos = new ArrayList<NomenclatureDto>();
						civiliteDtos = nomenclatureService.findByName(OfConstants.NC_NAME_CIVILITE);
						NomenclatureDto nationaliteDto = new NomenclatureDto();
						nationaliteDto = nomenclatureService.findByCode(OfConstants.NC_NAME_NATIONALITE, OfConstants.NC_NATIONALITE_ALG);
					 	searchDto = new DossierBachelierDto();
						searchDto.setRefCodeEtablissementAffectation(oldEtabCode);
						this.bachelorsFilesToGenerate = bacService.findAdvanced(searchDto, null, true, false, this.year,null, null );
			
						for (DossierBachelierDto dossierBachelierDto : bachelorsFilesToGenerate) {
							RefIndividuDto refIndividuDto = new RefIndividuDto();
							refIndividuDto.setIdentifiant(dossierBachelierDto.getMatricule());
							refIndividuDto.setNomArabe(dossierBachelierDto.getNomAr());
							refIndividuDto.setPrenomArabe(dossierBachelierDto.getPrenomAr());
							refIndividuDto.setNomLatin(dossierBachelierDto.getNomFr());
							refIndividuDto.setPrenomLatin(dossierBachelierDto.getPrenomFr());
							refIndividuDto.setLieuNaissance(dossierBachelierDto.getLibelleVilleNaissance());
							refIndividuDto.setDateNaissance(dossierBachelierDto.getDateNaissance());
							refIndividuDto.setNomMereLatin(dossierBachelierDto.getNomPrenomMere());
							refIndividuDto.setPrenomPereLatin(dossierBachelierDto.getPrenomPere());
							refIndividuDto.setNationaliteId(nationaliteDto.getId());
							refIndividuDto.setNationaliteCode(nationaliteDto.getCode());
							
							if (dossierBachelierDto.getRefCodeSexe() != null) {
									switch (dossierBachelierDto.getRefCodeSexe()) {
									case UtilConstants.BAC_BACHELOR_MALE_CODE:
										refIndividuDto.setCiviliteCode(getNomenclatureByCode(OfConstants.NC_CIVILITE_MR, civiliteDtos).getCode());
										refIndividuDto.setCiviliteId(getNomenclatureByCode(OfConstants.NC_CIVILITE_MR, civiliteDtos).getId());
										break;
									case UtilConstants.BAC_BACHELOR_FEMALE_CODE:
										refIndividuDto.setCiviliteCode(getNomenclatureByCode(OfConstants.NC_CIVILITE_MME, civiliteDtos).getCode());
										refIndividuDto.setCiviliteId(getNomenclatureByCode(OfConstants.NC_CIVILITE_MME, civiliteDtos).getId());
										break;
									default:
										break;
									}
							}
							refIndividuDtos.add(refIndividuDto);
						}
						
						return refIndividuDtos;
				}
				catch (Exception e) {
					
							e.printStackTrace();
							return null;
				}
		}
		
		/**
		 * [GenerationBB.handleYearsChange] Method 
		 * @author rlaib  on : 27 sept. 2014  09:28:23
		 * @param event
		 */
		public void  handleYearsChange(AjaxBehaviorEvent event) {
				
			try {
							initDataToGenerate();
							this.showResultOutput = false;
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
		 * [GenerationBB.getGeneratedIndividus] Method 
		 * @author rlaib  on : 27 sept. 2014  09:25:10
		 * @param _refIndividuDtos
		 * @param _generatedIndividus
		 * @return
		 */
		@SuppressWarnings("unused")
		private List<RefIndividuDto> getGeneratedIndividus(
				List<RefIndividuDto> _refIndividuDtos,
				List<RefIndividuDto> _generatedIndividus) {
			
			List<RefIndividuDto> result = new ArrayList<RefIndividuDto>();
			for (RefIndividuDto _refIndividuDto : _refIndividuDtos) {
					
				Boolean exist = false;
				for (RefIndividuDto _refIndividuDto2 : _generatedIndividus) {
					if(!exist)
						if(_refIndividuDto2.getIdentifiant().trim().equals(_refIndividuDto.getIdentifiant().trim())) {
							exist = true;
						}
				}
				if(!exist)
					result.add(_refIndividuDto);
			}
			return result;
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
		 * [GenerationBB.getNomenclatureByCode] Method 
		 * @author rlaib  on : 27 sept. 2014  09:25:30
		 * @param code
		 * @param list
		 * @return
		 */
		private NomenclatureDto getNomenclatureByCode(String code, List<NomenclatureDto> list) {
			
			NomenclatureDto result = new NomenclatureDto();
			for (NomenclatureDto nomenclatureDto : list) {
				
				if(nomenclatureDto.getCode().equals(code)) {
					result = nomenclatureDto;
					break;
				}
				
			}
			return result;
		}

		/**
		 * [GenerationBB.setClientTimeout] Method 
		 * @author rlaib  on : 27 sept. 2014  09:25:50
		 * @param clientInterface
		 */
		public static void setClientTimeout(Object clientInterface) {
	
			final int TIMEOUT = 36000 * 1000;
	
			Client client = ClientProxy.getClient(clientInterface);
			HTTPConduit http = (HTTPConduit)client.getConduit();
			HTTPClientPolicy policy = new HTTPClientPolicy();
			policy.setConnectionTimeout(TIMEOUT);
			policy.setReceiveTimeout(TIMEOUT);
			policy.setAllowChunking(false);
			http.setClient(policy);
		}
	
		/**
		 * [GenerationBB.initSessionInfos] Method 
		 * @author rlaib  on : 27 sept. 2014  09:26:05
		 */
		private void initSessionInfos() {
			try {
						this.oldEtabCode= this.sessionBean.getAncienCodeEtablissement();
			 			this.libelleEtab=this.sessionBean.getLlLatinEtablissement();
			 			this.newEtabCode = this.sessionBean.getCodeEtablissement();
			 			this.year = this.sessionBean.getCodeAnneeAcademique();
			 			this.idYear = this.sessionBean.getIdAnneeAcademique();
			 }
			 catch(Exception e){
				 
				 e.printStackTrace();
			 
			 }
		}
}
