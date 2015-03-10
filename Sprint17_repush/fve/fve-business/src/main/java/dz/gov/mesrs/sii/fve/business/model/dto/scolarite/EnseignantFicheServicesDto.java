/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.scolarite.EnseignantFicheServicesDto.java] 
 * @author rlaib on : 12 oct. 2014  14:03:13
 */
package dz.gov.mesrs.sii.fve.business.model.dto.scolarite;


/**
 * @author rlaib  on : 12 oct. 2014  14:03:13
 */

public class EnseignantFicheServicesDto implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 oct. 2014  16:09:25
	 */
		private static final long serialVersionUID = 1L;
		private int id;
		private Integer idSituation;
		
		// Fiche de voeux
		private Integer ficheVoeuxId;
		private String styleCssSituation;
		private String libelleSituation;
		
		private boolean estGeneree;
		private boolean estSoumise;
		private boolean validee;
	
		// Fiche Voeux Dto
		private Long ficheVoeuxEnseignantId;
		private Integer ficheVoeuxAnneeId;
		private String ficheVoeuxPremiereAnnee;
		private String ficheVoeuxDeuxiemeAnnee;
		private Integer ficheVoeuxPeriodeId;
		private String ficheVoeuxPeriodeCode;
		private String ficheVoeuxPeriodeLibelle;
		private String ficheVoeuxNomEnseignant;
		private String ficheVoeuxPrenomEnseignant;
		
	
	public EnseignantFicheServicesDto() {
		
	}

	/**
	 * [EnseignantFicheServicesDto.id] Getter 
	 * @author rlaib on : 22 oct. 2014  12:16:05
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [EnseignantFicheServicesDto.id] Setter 
	 * @author rlaib on : 22 oct. 2014  12:16:05
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxId] Getter 
	 * @author rlaib on : 22 oct. 2014  12:16:05
	 * @return the ficheVoeuxId
	 */
	public Integer getFicheVoeuxId() {
		return ficheVoeuxId;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxId] Setter 
	 * @author rlaib on : 22 oct. 2014  12:16:05
	 * @param ficheVoeuxId the ficheVoeuxId to set
	 */
	public void setFicheVoeuxId(Integer ficheVoeuxId) {
		this.ficheVoeuxId = ficheVoeuxId;
	}

	

	/**
	 * [EnseignantFicheServicesDto.idSituation] Getter 
	 * @author rlaib on : 22 oct. 2014  12:16:05
	 * @return the idSituation
	 */
	public Integer getIdSituation() {
		return idSituation;
	}

	/**
	 * [EnseignantFicheServicesDto.idSituation] Setter 
	 * @author rlaib on : 22 oct. 2014  12:16:05
	 * @param idSituation the idSituation to set
	 */
	public void setIdSituation(Integer idSituation) {
		this.idSituation = idSituation;
	}

	/**
	 * [EnseignantFicheServicesDto.styleCssSituation] Getter 
	 * @author rlaib on : 25 oct. 2014  14:09:47
	 * @return the styleCssSituation
	 */
	public String getStyleCssSituation() {
		return styleCssSituation;
	}

	/**
	 * [EnseignantFicheServicesDto.styleCssSituation] Setter 
	 * @author rlaib on : 25 oct. 2014  14:09:47
	 * @param styleCssSituation the styleCssSituation to set
	 */
	public void setStyleCssSituation(String styleCssSituation) {
		this.styleCssSituation = styleCssSituation;
	}

	/**
	 * [EnseignantFicheServicesDto.libelleSituation] Getter 
	 * @author rlaib on : 25 oct. 2014  14:10:47
	 * @return the libelleSituation
	 */
	public String getLibelleSituation() {
		return libelleSituation;
	}

	/**
	 * [EnseignantFicheServicesDto.libelleSituation] Setter 
	 * @author rlaib on : 25 oct. 2014  14:10:47
	 * @param libelleSituation the libelleSituation to set
	 */
	public void setLibelleSituation(String libelleSituation) {
		this.libelleSituation = libelleSituation;
	}

	/**
	 * [EnseignantFicheServicesDto.estSoumise] Getter 
	 * @author rlaib on : 25 oct. 2014  14:11:31
	 * @return the estSoumise
	 */
	public boolean isEstSoumise() {
		return estSoumise;
	}

	/**
	 * [EnseignantFicheServicesDto.estSoumise] Setter 
	 * @author rlaib on : 25 oct. 2014  14:11:31
	 * @param estSoumise the estSoumise to set
	 */
	public void setEstSoumise(boolean estSoumise) {
		this.estSoumise = estSoumise;
	}


	/**
	 * [EnseignantFicheServicesDto.validee] Getter 
	 * @author rlaib on : 27 oct. 2014  15:12:29
	 * @return the validee
	 */
	public boolean isValidee() {
		return validee;
	}

	/**
	 * [EnseignantFicheServicesDto.validee] Setter 
	 * @author rlaib on : 27 oct. 2014  15:12:29
	 * @param validee the validee to set
	 */
	public void setValidee(boolean validee) {
		this.validee = validee;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxEnseignantId] Getter 
	 * @author rlaib on : 26 oct. 2014  09:22:11
	 * @return the ficheVoeuxEnseignantId
	 */
	public Long getFicheVoeuxEnseignantId() {
		return ficheVoeuxEnseignantId;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxEnseignantId] Setter 
	 * @author rlaib on : 26 oct. 2014  09:22:11
	 * @param ficheVoeuxEnseignantId the ficheVoeuxEnseignantId to set
	 */
	public void setFicheVoeuxEnseignantId(Long ficheVoeuxEnseignantId) {
		this.ficheVoeuxEnseignantId = ficheVoeuxEnseignantId;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxAnneeId] Getter 
	 * @author rlaib on : 26 oct. 2014  09:22:11
	 * @return the ficheVoeuxAnneeId
	 */
	public Integer getFicheVoeuxAnneeId() {
		return ficheVoeuxAnneeId;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxAnneeId] Setter 
	 * @author rlaib on : 26 oct. 2014  09:22:11
	 * @param ficheVoeuxAnneeId the ficheVoeuxAnneeId to set
	 */
	public void setFicheVoeuxAnneeId(Integer ficheVoeuxAnneeId) {
		this.ficheVoeuxAnneeId = ficheVoeuxAnneeId;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxPremiereAnnee] Getter 
	 * @author rlaib on : 26 oct. 2014  09:22:11
	 * @return the ficheVoeuxPremiereAnnee
	 */
	public String getFicheVoeuxPremiereAnnee() {
		return ficheVoeuxPremiereAnnee;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxPremiereAnnee] Setter 
	 * @author rlaib on : 26 oct. 2014  09:22:11
	 * @param ficheVoeuxPremiereAnnee the ficheVoeuxPremiereAnnee to set
	 */
	public void setFicheVoeuxPremiereAnnee(String ficheVoeuxPremiereAnnee) {
		this.ficheVoeuxPremiereAnnee = ficheVoeuxPremiereAnnee;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxDeuxiemeAnnee] Getter 
	 * @author rlaib on : 26 oct. 2014  09:22:11
	 * @return the ficheVoeuxDeuxiemeAnnee
	 */
	public String getFicheVoeuxDeuxiemeAnnee() {
		return ficheVoeuxDeuxiemeAnnee;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxDeuxiemeAnnee] Setter 
	 * @author rlaib on : 26 oct. 2014  09:22:11
	 * @param ficheVoeuxDeuxiemeAnnee the ficheVoeuxDeuxiemeAnnee to set
	 */
	public void setFicheVoeuxDeuxiemeAnnee(String ficheVoeuxDeuxiemeAnnee) {
		this.ficheVoeuxDeuxiemeAnnee = ficheVoeuxDeuxiemeAnnee;
	}


	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxPeriodeId] Getter 
	 * @author rlaib on : 26 oct. 2014  09:22:11
	 * @return the ficheVoeuxPeriodeId
	 */
	public Integer getFicheVoeuxPeriodeId() {
		return ficheVoeuxPeriodeId;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxPeriodeId] Setter 
	 * @author rlaib on : 26 oct. 2014  09:22:11
	 * @param ficheVoeuxPeriodeId the ficheVoeuxPeriodeId to set
	 */
	public void setFicheVoeuxPeriodeId(Integer ficheVoeuxPeriodeId) {
		this.ficheVoeuxPeriodeId = ficheVoeuxPeriodeId;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxPeriodeCode] Getter 
	 * @author rlaib on : 26 oct. 2014  09:22:11
	 * @return the ficheVoeuxPeriodeCode
	 */
	public String getFicheVoeuxPeriodeCode() {
		return ficheVoeuxPeriodeCode;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxPeriodeCode] Setter 
	 * @author rlaib on : 26 oct. 2014  09:22:11
	 * @param ficheVoeuxPeriodeCode the ficheVoeuxPeriodeCode to set
	 */
	public void setFicheVoeuxPeriodeCode(String ficheVoeuxPeriodeCode) {
		this.ficheVoeuxPeriodeCode = ficheVoeuxPeriodeCode;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxPeriodeLibelle] Getter 
	 * @author rlaib on : 26 oct. 2014  09:22:11
	 * @return the ficheVoeuxPeriodeLibelle
	 */
	public String getFicheVoeuxPeriodeLibelle() {
		return ficheVoeuxPeriodeLibelle;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxPeriodeLibelle] Setter 
	 * @author rlaib on : 26 oct. 2014  09:22:11
	 * @param ficheVoeuxPeriodeLibelle the ficheVoeuxPeriodeLibelle to set
	 */
	public void setFicheVoeuxPeriodeLibelle(String ficheVoeuxPeriodeLibelle) {
		this.ficheVoeuxPeriodeLibelle = ficheVoeuxPeriodeLibelle;
	}

	/**
	 * [EnseignantFicheServicesDto.estGeneree] Getter 
	 * @author rlaib on : 27 oct. 2014  10:31:35
	 * @return the estGeneree
	 */
	public boolean isEstGeneree() {
		return estGeneree;
	}

	/**
	 * [EnseignantFicheServicesDto.estGeneree] Setter 
	 * @author rlaib on : 27 oct. 2014  10:31:35
	 * @param estGeneree the estGeneree to set
	 */
	public void setEstGeneree(boolean estGeneree) {
		this.estGeneree = estGeneree;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxNomEnseignant] Getter 
	 * @author rlaib on : 4 nov. 2014  08:34:09
	 * @return the ficheVoeuxNomEnseignant
	 */
	public String getFicheVoeuxNomEnseignant() {
		return ficheVoeuxNomEnseignant;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxNomEnseignant] Setter 
	 * @author rlaib on : 4 nov. 2014  08:34:09
	 * @param ficheVoeuxNomEnseignant the ficheVoeuxNomEnseignant to set
	 */
	public void setFicheVoeuxNomEnseignant(String ficheVoeuxNomEnseignant) {
		this.ficheVoeuxNomEnseignant = ficheVoeuxNomEnseignant;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxPrenomEnseignant] Getter 
	 * @author rlaib on : 4 nov. 2014  08:34:09
	 * @return the ficheVoeuxPrenomEnseignant
	 */
	public String getFicheVoeuxPrenomEnseignant() {
		return ficheVoeuxPrenomEnseignant;
	}

	/**
	 * [EnseignantFicheServicesDto.ficheVoeuxPrenomEnseignant] Setter 
	 * @author rlaib on : 4 nov. 2014  08:34:09
	 * @param ficheVoeuxPrenomEnseignant the ficheVoeuxPrenomEnseignant to set
	 */
	public void setFicheVoeuxPrenomEnseignant(String ficheVoeuxPrenomEnseignant) {
		this.ficheVoeuxPrenomEnseignant = ficheVoeuxPrenomEnseignant;
	}


	
}
