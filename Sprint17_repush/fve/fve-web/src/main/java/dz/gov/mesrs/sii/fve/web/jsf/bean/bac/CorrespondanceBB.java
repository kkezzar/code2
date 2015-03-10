/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.bac.BacBB.java] 
 * @author  Rafik LAIB on : 21 mai 2014  11:18:57
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.bac;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import dz.gov.mesrs.sii.fve.business.model.dto.bac.CorrespondanceDomaineDto;
import dz.gov.mesrs.sii.fve.business.service.bac.CorrespondanceDomaineService;

/**
 * @author  Rafik LAIB  on : 21 mai 2014  11:18:57
 */
@ManagedBean(name = "correspondanceBB")
@RequestScoped
public class CorrespondanceBB {

	// ===================================================================  
	// Constructors
	// ===================================================================
	public CorrespondanceBB() {
		
	}
	
	@PostConstruct
	public void init() {
		
		 try	{
			 		 	
			 this.correspondanceDomaines = this.correspondanceDomaineService.getTableCorrespondanceDomaines();
			 
		 }
		 catch(Exception e){
			 
			 e.printStackTrace();
		 
		 }
	
	}
	
	// ===================================================================  
	// Beans Services 
	// ===================================================================
	
	@ManagedProperty(value="#{correspondanceDomaineService}")
	private CorrespondanceDomaineService correspondanceDomaineService;
	
	// ===================================================================  
	// Properties Model
	// ===================================================================

	private List<CorrespondanceDomaineDto> correspondanceDomaines;
	private List<CorrespondanceDomaineDto> filtredRows;
	
	
	// ===================================================================  
	// Beans Services  Getters / Setters
	// ===================================================================

	/**
	 * [CorrespondanceBB.correspondanceDomaineService] Getter 
	 * @author rlaib on : 18 juin 2014  15:30:50
	 * @return the correspondanceDomaineService
	 */
	public CorrespondanceDomaineService getCorrespondanceDomaineService() {
		return correspondanceDomaineService;
	}

	/**
	 * [CorrespondanceBB.correspondanceDomaineService] Setter 
	 * @author rlaib on : 18 juin 2014  15:30:50
	 * @param correspondanceDomaineService the correspondanceDomaineService to set
	 */
	public void setCorrespondanceDomaineService(
			CorrespondanceDomaineService correspondanceDomaineService) {
		this.correspondanceDomaineService = correspondanceDomaineService;
	}
	
	/**
	 * [CorrespondanceBB.filtredRows] Getter 
	 * @author rlaib on : 22 juin 2014  17:14:57
	 * @return the filtredRows
	 */
	public List<CorrespondanceDomaineDto> getFiltredRows() {
		return filtredRows;
	}

	/**
	 * [CorrespondanceBB.filtredRows] Setter 
	 * @author rlaib on : 22 juin 2014  17:14:57
	 * @param filtredRows the filtredRows to set
	 */
	public void setFiltredRows(List<CorrespondanceDomaineDto> filtredRows) {
		this.filtredRows = filtredRows;
	}

	// ===================================================================  
    // Properties Model Getters / Setters
	// ===================================================================

	/**
	 * [CorrespondanceBB.correspondanceDomaines] Getter 
	 * @author rlaib on : 18 juin 2014  15:30:50
	 * @return the correspondanceDomaines
	 */
	public List<CorrespondanceDomaineDto> getCorrespondanceDomaines() {
		return correspondanceDomaines;
	}

	/**
	 * [CorrespondanceBB.correspondanceDomaines] Setter 
	 * @author rlaib on : 18 juin 2014  15:30:50
	 * @param correspondanceDomaines the correspondanceDomaines to set
	 */
	public void setCorrespondanceDomaines(
			List<CorrespondanceDomaineDto> correspondanceDomaines) {
		this.correspondanceDomaines = correspondanceDomaines;
	}
	

	
	// ===================================================================  
	// Listeners
	// ===================================================================

	
	// ===================================================================  
	// ActionListener
	// ===================================================================
	

	// ===================================================================  
	// Methods
	// ===================================================================

	public String booleanToString(boolean trueOrFalse) {
			
		try {
					if(trueOrFalse)
							return "Oui";
					else
						return "Non";
			}
		catch (Exception ex)  {
			return "";
		}
	}
}
