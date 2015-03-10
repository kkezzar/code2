/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.inscription.DossierInscriptionModel.java] 
 * @author MAKERRI Sofiane on : 31 juil. 2014  10:06:11
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.inscription;

import java.io.Serializable;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;

/**
 * @author MAKERRI Sofiane  on : 31 juil. 2014  10:06:11
 */
public class OffreFormationInscriptionModel implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 31 juil. 2014  10:06:17
	 */
	private static final long serialVersionUID = 1L;
	private String offreFormation;
	private List<DossierInscriptionAdministrativeDto> dossiersInscription;
	

	/**
	 * [DossierInscriptionModel.DossierInscriptionModel()] Constructor
	 * @author MAKERRI Sofiane  on : 31 juil. 2014  10:06:11	
	 */
	public OffreFormationInscriptionModel() {
		super();
	}


	/**
	 * [OffreFormationInscriptionModel.offreFormation] Getter 
	 * @author MAKERRI Sofiane on : 31 juil. 2014  10:08:46
	 * @return the offreFormation
	 */
	public String getOffreFormation() {
		return offreFormation;
	}


	/**
	 * [OffreFormationInscriptionModel.offreFormation] Setter 
	 * @author MAKERRI Sofiane on : 31 juil. 2014  10:08:46
	 * @param offreFormation the offreFormation to set
	 */
	public void setOffreFormation(String offreFormation) {
		this.offreFormation = offreFormation;
	}


	/**
	 * [OffreFormationInscriptionModel.dossiersInscription] Getter 
	 * @author MAKERRI Sofiane on : 31 juil. 2014  10:08:46
	 * @return the dossiersInscription
	 */
	public List<DossierInscriptionAdministrativeDto> getDossiersInscription() {
		return dossiersInscription;
	}


	/**
	 * [OffreFormationInscriptionModel.dossiersInscription] Setter 
	 * @author MAKERRI Sofiane on : 31 juil. 2014  10:08:46
	 * @param dossiersInscription the dossiersInscription to set
	 */
	public void setDossiersInscription(
			List<DossierInscriptionAdministrativeDto> dossiersInscription) {
		this.dossiersInscription = dossiersInscription;
	}
	
	

}
