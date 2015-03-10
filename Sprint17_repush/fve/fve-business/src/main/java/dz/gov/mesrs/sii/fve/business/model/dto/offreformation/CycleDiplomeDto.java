/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDto.java] 
 * @author rlaib on : 16 juil. 2014  09:51:36
 */
package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import java.io.Serializable;

/**
 * @author rlaib on : 16 juil. 2014 09:51:36
 */
public class CycleDiplomeDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 24 nov. 2014  13:52:24
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String sens;
	private Integer rang;
	
	// Cycle
	private Integer cycleId;
	private String cycleCode;
	private String cycleLibelle;

	// Diplome et Niveau de diplome
	private Integer diplomeId;
	private String diplomeCode;
	private String diplomeLibelle;
	private String diplomeLibelleAr;
	private Integer niveauDiplomeId;
	private String niveauDiplomeCode;
	private String niveauDiplomeLibelle;

	public CycleDiplomeDto() {
	}

	/**
	 * [CycleDiplomeDto.id] Getter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [CycleDiplomeDto.id] Setter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [CycleDiplomeDto.sens] Getter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @return the sens
	 */
	public String getSens() {
		return sens;
	}

	/**
	 * [CycleDiplomeDto.sens] Setter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @param sens the sens to set
	 */
	public void setSens(String sens) {
		this.sens = sens;
	}

	/**
	 * [CycleDiplomeDto.cycleId] Getter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @return the cycleId
	 */
	public Integer getCycleId() {
		return cycleId;
	}

	/**
	 * [CycleDiplomeDto.cycleId] Setter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @param cycleId the cycleId to set
	 */
	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	/**
	 * [CycleDiplomeDto.cycleCode] Getter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @return the cycleCode
	 */
	public String getCycleCode() {
		return cycleCode;
	}

	/**
	 * [CycleDiplomeDto.cycleCode] Setter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @param cycleCode the cycleCode to set
	 */
	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}

	/**
	 * [CycleDiplomeDto.cycleLibelle] Getter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @return the cycleLibelle
	 */
	public String getCycleLibelle() {
		return cycleLibelle;
	}

	/**
	 * [CycleDiplomeDto.cycleLibelle] Setter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @param cycleLibelle the cycleLibelle to set
	 */
	public void setCycleLibelle(String cycleLibelle) {
		this.cycleLibelle = cycleLibelle;
	}

	/**
	 * [CycleDiplomeDto.diplomeId] Getter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @return the diplomeId
	 */
	public Integer getDiplomeId() {
		return diplomeId;
	}

	/**
	 * [CycleDiplomeDto.diplomeId] Setter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @param diplomeId the diplomeId to set
	 */
	public void setDiplomeId(Integer diplomeId) {
		this.diplomeId = diplomeId;
	}

	/**
	 * [CycleDiplomeDto.diplomeCode] Getter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @return the diplomeCode
	 */
	public String getDiplomeCode() {
		return diplomeCode;
	}

	/**
	 * [CycleDiplomeDto.diplomeCode] Setter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @param diplomeCode the diplomeCode to set
	 */
	public void setDiplomeCode(String diplomeCode) {
		this.diplomeCode = diplomeCode;
	}

	/**
	 * [CycleDiplomeDto.diplomeLibelle] Getter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @return the diplomeLibelle
	 */
	public String getDiplomeLibelle() {
		return diplomeLibelle;
	}

	/**
	 * [CycleDiplomeDto.diplomeLibelle] Setter 
	 * @author rlaib on : 24 nov. 2014  13:52:16
	 * @param diplomeLibelle the diplomeLibelle to set
	 */
	public void setDiplomeLibelle(String diplomeLibelle) {
		this.diplomeLibelle = diplomeLibelle;
	}

	/**
	 * [CycleDiplomeDto.niveauDiplomeId] Getter 
	 * @author rlaib on : 25 nov. 2014  14:18:37
	 * @return the niveauDiplomeId
	 */
	public Integer getNiveauDiplomeId() {
		return niveauDiplomeId;
	}

	/**
	 * [CycleDiplomeDto.niveauDiplomeId] Setter 
	 * @author rlaib on : 25 nov. 2014  14:18:37
	 * @param niveauDiplomeId the niveauDiplomeId to set
	 */
	public void setNiveauDiplomeId(Integer niveauDiplomeId) {
		this.niveauDiplomeId = niveauDiplomeId;
	}

	/**
	 * [CycleDiplomeDto.niveauDiplomeCode] Getter 
	 * @author rlaib on : 25 nov. 2014  14:18:37
	 * @return the niveauDiplomeCode
	 */
	public String getNiveauDiplomeCode() {
		return niveauDiplomeCode;
	}

	/**
	 * [CycleDiplomeDto.niveauDiplomeCode] Setter 
	 * @author rlaib on : 25 nov. 2014  14:18:37
	 * @param niveauDiplomeCode the niveauDiplomeCode to set
	 */
	public void setNiveauDiplomeCode(String niveauDiplomeCode) {
		this.niveauDiplomeCode = niveauDiplomeCode;
	}

	/**
	 * [CycleDiplomeDto.niveauDiplomeLibelle] Getter 
	 * @author rlaib on : 25 nov. 2014  14:18:37
	 * @return the niveauDiplomeLibelle
	 */
	public String getNiveauDiplomeLibelle() {
		return niveauDiplomeLibelle;
	}

	/**
	 * [CycleDiplomeDto.niveauDiplomeLibelle] Setter 
	 * @author rlaib on : 25 nov. 2014  14:18:37
	 * @param niveauDiplomeLibelle the niveauDiplomeLibelle to set
	 */
	public void setNiveauDiplomeLibelle(String niveauDiplomeLibelle) {
		this.niveauDiplomeLibelle = niveauDiplomeLibelle;
	}

	/**
	 * [CycleDiplomeDto.diplomeLibelleAr] Getter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  14:56:12
	 * @return the diplomeLibelleAr
	 */
	public String getDiplomeLibelleAr() {
		return diplomeLibelleAr;
	}

	/**
	 * [CycleDiplomeDto.diplomeLibelleAr] Setter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  14:56:12
	 * @param diplomeLibelleAr the diplomeLibelleAr to set
	 */
	public void setDiplomeLibelleAr(String diplomeLibelleAr) {
		this.diplomeLibelleAr = diplomeLibelleAr;
	}

	/**
	 * [CycleDiplomeDto.rang] Getter 
	 * @author MAKERRI Sofiane on : 23 févr. 2015  15:19:37
	 * @return the rang
	 */
	public Integer getRang() {
		return rang;
	}

	/**
	 * [CycleDiplomeDto.rang] Setter 
	 * @author MAKERRI Sofiane on : 23 févr. 2015  15:19:37
	 * @param rang the rang to set
	 */
	public void setRang(Integer rang) {
		this.rang = rang;
	}

	
	
}
