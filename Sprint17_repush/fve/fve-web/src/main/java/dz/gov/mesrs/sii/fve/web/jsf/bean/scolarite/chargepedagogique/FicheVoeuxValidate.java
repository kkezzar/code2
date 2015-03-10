package dz.gov.mesrs.sii.fve.web.jsf.bean.scolarite.chargepedagogique;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantFicheVoeuxService;

@ManagedBean(name = "ficheVoeuxValidate")
@ViewScoped
public class FicheVoeuxValidate {


	// ===================================================================  
	// Constructors
	// ===================================================================
	public FicheVoeuxValidate() {
		
	}
	
	@PostConstruct
	public void init() {
		
			 try	{
				 		ficheVoeuxBB.initSessionInfos();
				 		ficheVoeuxBB.initNomenclatures();
				 		ficheVoeuxBB.initYearsList();
				 		ficheVoeuxBB.initOffresFormation(false);
				 		ficheVoeuxBB.setToSubmit(false);
						ficheVoeuxBB.setToEdit(true);
						ficheVoeuxBB.setToValidate(true);
				 		ficheVoeuxBB.loadFichesVoeuxToValidate();
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
	}


		// ===================================================================  
		// Beans Services 
		// ===================================================================
		@ManagedProperty(value = "#{ficheVoeuxBB}")
		private FicheVoeuxBB ficheVoeuxBB;
		
		@ManagedProperty(value="#{enseignantFicheVoeuxService}")
		private EnseignantFicheVoeuxService enseignantFicheVoeuxService;
		
		@ManagedProperty(value="#{situationService}")
		private SituationService situationService;
		
		// ===================================================================  
		// Beans Services  Getters / Setters
		// ===================================================================
		
		/**
		 * [FicheVoeuxConsult.ficheVoeuxBB] Getter 
		 * @author rlaib on : 19 oct. 2014  18:01:23
		 * @return the ficheVoeuxBB
		 */
		public FicheVoeuxBB getFicheVoeuxBB() {
			return ficheVoeuxBB;
		}

		/**
		 * [FicheVoeuxValidate.situationService] Getter 
		 * @author rlaib on : 20 oct. 2014  12:52:34
		 * @return the situationService
		 */
		public SituationService getSituationService() {
			return situationService;
		}

		/**
		 * [FicheVoeuxValidate.situationService] Setter 
		 * @author rlaib on : 20 oct. 2014  12:52:34
		 * @param situationService the situationService to set
		 */
		public void setSituationService(SituationService situationService) {
			this.situationService = situationService;
		}

		/**
		 * [FicheVoeuxValidate.enseignantFicheVoeuxService] Getter 
		 * @author rlaib on : 20 oct. 2014  12:51:43
		 * @return the enseignantFicheVoeuxService
		 */
		public EnseignantFicheVoeuxService getEnseignantFicheVoeuxService() {
			return enseignantFicheVoeuxService;
		}

		/**
		 * [FicheVoeuxValidate.enseignantFicheVoeuxService] Setter 
		 * @author rlaib on : 20 oct. 2014  12:51:43
		 * @param enseignantFicheVoeuxService the enseignantFicheVoeuxService to set
		 */
		public void setEnseignantFicheVoeuxService(
				EnseignantFicheVoeuxService enseignantFicheVoeuxService) {
			this.enseignantFicheVoeuxService = enseignantFicheVoeuxService;
		}

		/**
		 * [FicheVoeuxConsult.ficheVoeuxBB] Setter 
		 * @author rlaib on : 19 oct. 2014  18:01:23
		 * @param ficheVoeuxBB the ficheVoeuxBB to set
		 */
		public void setFicheVoeuxBB(FicheVoeuxBB ficheVoeuxBB) {
			this.ficheVoeuxBB = ficheVoeuxBB;
		}
		
		// ===================================================================  
		// Properties Model
		// ===================================================================
		private List<SelectItem> listPeriodicitesFichesValidate;
		private String selectedPeriodiciteFichesValidate;
		private Integer selectedPeriodeFichesValidate;
		private List<SelectItem> listPeriodesParPeriodiciteFichesValidate;
		private Integer selectedIdYearFichesValidate;
		private List<SelectItem> listYearsFichesValidate;
		  

	/**
		 * [FicheVoeuxValidate.listPeriodicitesFichesValidate] Getter 
		 * @author rlaib on : 5 nov. 2014  16:36:20
		 * @return the listPeriodicitesFichesValidate
		 */
		public List<SelectItem> getListPeriodicitesFichesValidate() {
			return listPeriodicitesFichesValidate;
		}

		/**
		 * [FicheVoeuxValidate.listPeriodicitesFichesValidate] Setter 
		 * @author rlaib on : 5 nov. 2014  16:36:20
		 * @param listPeriodicitesFichesValidate the listPeriodicitesFichesValidate to set
		 */
		public void setListPeriodicitesFichesValidate(
				List<SelectItem> listPeriodicitesFichesValidate) {
			this.listPeriodicitesFichesValidate = listPeriodicitesFichesValidate;
		}

		/**
		 * [FicheVoeuxValidate.selectedPeriodiciteFichesValidate] Getter 
		 * @author rlaib on : 5 nov. 2014  16:36:20
		 * @return the selectedPeriodiciteFichesValidate
		 */
		public String getSelectedPeriodiciteFichesValidate() {
			return selectedPeriodiciteFichesValidate;
		}

		/**
		 * [FicheVoeuxValidate.selectedPeriodiciteFichesValidate] Setter 
		 * @author rlaib on : 5 nov. 2014  16:36:20
		 * @param selectedPeriodiciteFichesValidate the selectedPeriodiciteFichesValidate to set
		 */
		public void setSelectedPeriodiciteFichesValidate(
				String selectedPeriodiciteFichesValidate) {
			this.selectedPeriodiciteFichesValidate = selectedPeriodiciteFichesValidate;
		}

		/**
		 * [FicheVoeuxValidate.selectedPeriodeFichesValidate] Getter 
		 * @author rlaib on : 5 nov. 2014  16:36:20
		 * @return the selectedPeriodeFichesValidate
		 */
		public Integer getSelectedPeriodeFichesValidate() {
			return selectedPeriodeFichesValidate;
		}

		/**
		 * [FicheVoeuxValidate.selectedPeriodeFichesValidate] Setter 
		 * @author rlaib on : 5 nov. 2014  16:36:20
		 * @param selectedPeriodeFichesValidate the selectedPeriodeFichesValidate to set
		 */
		public void setSelectedPeriodeFichesValidate(
				Integer selectedPeriodeFichesValidate) {
			this.selectedPeriodeFichesValidate = selectedPeriodeFichesValidate;
		}

		/**
		 * [FicheVoeuxValidate.listPeriodesParPeriodiciteFichesValidate] Getter 
		 * @author rlaib on : 5 nov. 2014  16:36:20
		 * @return the listPeriodesParPeriodiciteFichesValidate
		 */
		public List<SelectItem> getListPeriodesParPeriodiciteFichesValidate() {
			return listPeriodesParPeriodiciteFichesValidate;
		}

		/**
		 * [FicheVoeuxValidate.listPeriodesParPeriodiciteFichesValidate] Setter 
		 * @author rlaib on : 5 nov. 2014  16:36:20
		 * @param listPeriodesParPeriodiciteFichesValidate the listPeriodesParPeriodiciteFichesValidate to set
		 */
		public void setListPeriodesParPeriodiciteFichesValidate(
				List<SelectItem> listPeriodesParPeriodiciteFichesValidate) {
			this.listPeriodesParPeriodiciteFichesValidate = listPeriodesParPeriodiciteFichesValidate;
		}

		/**
		 * [FicheVoeuxValidate.selectedIdYearFichesValidate] Getter 
		 * @author rlaib on : 5 nov. 2014  16:36:20
		 * @return the selectedIdYearFichesValidate
		 */
		public Integer getSelectedIdYearFichesValidate() {
			return selectedIdYearFichesValidate;
		}

		/**
		 * [FicheVoeuxValidate.selectedIdYearFichesValidate] Setter 
		 * @author rlaib on : 5 nov. 2014  16:36:20
		 * @param selectedIdYearFichesValidate the selectedIdYearFichesValidate to set
		 */
		public void setSelectedIdYearFichesValidate(Integer selectedIdYearFichesValidate) {
			this.selectedIdYearFichesValidate = selectedIdYearFichesValidate;
		}

		/**
		 * [FicheVoeuxValidate.listYearsFichesValidate] Getter 
		 * @author rlaib on : 5 nov. 2014  16:36:20
		 * @return the listYearsFichesValidate
		 */
		public List<SelectItem> getListYearsFichesValidate() {
			return listYearsFichesValidate;
		}

		/**
		 * [FicheVoeuxValidate.listYearsFichesValidate] Setter 
		 * @author rlaib on : 5 nov. 2014  16:36:20
		 * @param listYearsFichesValidate the listYearsFichesValidate to set
		 */
		public void setListYearsFichesValidate(List<SelectItem> listYearsFichesValidate) {
			this.listYearsFichesValidate = listYearsFichesValidate;
		}

		
		// ===================================================================  
		// Session Infos
		// ===================================================================
		private Integer idEtablissement;
		private String codeEtablissement;
		private String newEtabCode;
		private String libelleEtab;
		private Integer idYear;
		private Integer userId;
		private String userFisrtName;
		private String userLastName;
		private String libelleYear;
			
		/**
		 * [FicheVoeuxValidate.idEtablissement] Getter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @return the idEtablissement
		 */
		public Integer getIdEtablissement() {
			return idEtablissement;
		}

		/**
		 * [FicheVoeuxValidate.idEtablissement] Setter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @param idEtablissement the idEtablissement to set
		 */
		public void setIdEtablissement(Integer idEtablissement) {
			this.idEtablissement = idEtablissement;
		}

		/**
		 * [FicheVoeuxValidate.codeEtablissement] Getter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @return the codeEtablissement
		 */
		public String getCodeEtablissement() {
			return codeEtablissement;
		}

		/**
		 * [FicheVoeuxValidate.codeEtablissement] Setter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @param codeEtablissement the codeEtablissement to set
		 */
		public void setCodeEtablissement(String codeEtablissement) {
			this.codeEtablissement = codeEtablissement;
		}

		/**
		 * [FicheVoeuxValidate.newEtabCode] Getter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @return the newEtabCode
		 */
		public String getNewEtabCode() {
			return newEtabCode;
		}

		/**
		 * [FicheVoeuxValidate.newEtabCode] Setter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @param newEtabCode the newEtabCode to set
		 */
		public void setNewEtabCode(String newEtabCode) {
			this.newEtabCode = newEtabCode;
		}

		/**
		 * [FicheVoeuxValidate.libelleEtab] Getter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @return the libelleEtab
		 */
		public String getLibelleEtab() {
			return libelleEtab;
		}

		/**
		 * [FicheVoeuxValidate.libelleEtab] Setter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @param libelleEtab the libelleEtab to set
		 */
		public void setLibelleEtab(String libelleEtab) {
			this.libelleEtab = libelleEtab;
		}

		/**
		 * [FicheVoeuxValidate.idYear] Getter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @return the idYear
		 */
		public Integer getIdYear() {
			return idYear;
		}

		/**
		 * [FicheVoeuxValidate.idYear] Setter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @param idYear the idYear to set
		 */
		public void setIdYear(Integer idYear) {
			this.idYear = idYear;
		}

		/**
		 * [FicheVoeuxValidate.userId] Getter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @return the userId
		 */
		public Integer getUserId() {
			return userId;
		}

		/**
		 * [FicheVoeuxValidate.userId] Setter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @param userId the userId to set
		 */
		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		/**
		 * [FicheVoeuxValidate.userFisrtName] Getter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @return the userFisrtName
		 */
		public String getUserFisrtName() {
			return userFisrtName;
		}

		/**
		 * [FicheVoeuxValidate.userFisrtName] Setter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @param userFisrtName the userFisrtName to set
		 */
		public void setUserFisrtName(String userFisrtName) {
			this.userFisrtName = userFisrtName;
		}

		/**
		 * [FicheVoeuxValidate.userLastName] Getter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @return the userLastName
		 */
		public String getUserLastName() {
			return userLastName;
		}

		/**
		 * [FicheVoeuxValidate.userLastName] Setter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @param userLastName the userLastName to set
		 */
		public void setUserLastName(String userLastName) {
			this.userLastName = userLastName;
		}

		/**
		 * [FicheVoeuxValidate.libelleYear] Getter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @return the libelleYear
		 */
		public String getLibelleYear() {
			return libelleYear;
		}

		/**
		 * [FicheVoeuxValidate.libelleYear] Setter 
		 * @author rlaib on : 5 nov. 2014  16:38:05
		 * @param libelleYear the libelleYear to set
		 */
		public void setLibelleYear(String libelleYear) {
			this.libelleYear = libelleYear;
		}

		public void onFichesVoeuxRowSelect(SelectEvent event) {  
			 
			 try {
				 
				 ficheVoeuxBB.setToSubmit(false);
				 ficheVoeuxBB.setToEdit(true);
				 ficheVoeuxBB.loadFicheVoeuxDetails();
	
			 }
			 catch (Exception e) {
				 
				 e.printStackTrace();
			 }
			 
		 }  
		
		// ===================================================================  
		// Listeners
		// ===================================================================

		// ===================================================================  
		// ActionListener
		// ===================================================================
		
		
		// ===================================================================  
		// Listeners
		// ===================================================================

		// ===================================================================  
		// Actions and Methods
		// ===================================================================
		
	
		// ===================================================================  
		// Functions Helper
		// ===================================================================
	
}
