/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.cursus.BilanControleContinuDto.java] 
 * @author MAKERRI Sofiane on : 16 oct. 2014  17:15:31
 */
package dz.gov.mesrs.sii.fve.business.model.dto.examen;

import java.io.Serializable;

/**
 * @author MAKERRI Sofiane  on : 16 oct. 2014  17:15:31
 */
public class BilanControleContinuDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 16 oct. 2014  17:15:36
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Integer affectationGroupePedagogiqueId;
	private Integer dossierInscriptionAdministrativeId;
	private Integer oofId;
	private Integer periodeId;
	private Integer rattachementMcId;
	private Integer anneeAcademiqueId;
	private Double note;

	/**
	 * [BilanControleContinuDto.BilanControleContinuDto()] Constructor
	 * @author MAKERRI Sofiane  on : 16 oct. 2014  17:15:31	
	 */
	public BilanControleContinuDto() {
		super();
	}

	/**
	 * [BilanControleContinuDto.id] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:16:25
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * [BilanControleContinuDto.id] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:16:25
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [BilanControleContinuDto.affectationGroupePedagogiqueId] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:16:25
	 * @return the affectationGroupePedagogiqueId
	 */
	public Integer getAffectationGroupePedagogiqueId() {
		return affectationGroupePedagogiqueId;
	}

	/**
	 * [BilanControleContinuDto.affectationGroupePedagogiqueId] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:16:25
	 * @param affectationGroupePedagogiqueId the affectationGroupePedagogiqueId to set
	 */
	public void setAffectationGroupePedagogiqueId(
			Integer affectationGroupePedagogiqueId) {
		this.affectationGroupePedagogiqueId = affectationGroupePedagogiqueId;
	}

	/**
	 * [BilanControleContinuDto.oofId] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:16:25
	 * @return the oofId
	 */
	public Integer getOofId() {
		return oofId;
	}

	/**
	 * [BilanControleContinuDto.oofId] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:16:25
	 * @param oofId the oofId to set
	 */
	public void setOofId(Integer oofId) {
		this.oofId = oofId;
	}

	/**
	 * [BilanControleContinuDto.periodeId] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:16:25
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [BilanControleContinuDto.periodeId] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:16:25
	 * @param periodeId the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [BilanControleContinuDto.rattachementMcId] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:16:25
	 * @return the rattachementMcId
	 */
	public Integer getRattachementMcId() {
		return rattachementMcId;
	}

	/**
	 * [BilanControleContinuDto.rattachementMcId] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:16:25
	 * @param rattachementMcId the rattachementMcId to set
	 */
	public void setRattachementMcId(Integer rattachementMcId) {
		this.rattachementMcId = rattachementMcId;
	}

	/**
	 * [BilanControleContinuDto.note] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:16:25
	 * @return the note
	 */
	public Double getNote() {
		return note;
	}

	/**
	 * [BilanControleContinuDto.note] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:16:25
	 * @param note the note to set
	 */
	public void setNote(Double note) {
		this.note = note;
	}

	/**
	 * [BilanControleContinuDto.anneeAcademiqueId] Getter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  08:54:08
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [BilanControleContinuDto.anneeAcademiqueId] Setter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  08:54:08
	 * @param anneeAcademiqueId the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [BilanControleContinuDto.dossierInscriptionAdministrativeId] Getter 
	 * @author MAKERRI Sofiane on : 11 janv. 2015  12:37:57
	 * @return the dossierInscriptionAdministrativeId
	 */
	public Integer getDossierInscriptionAdministrativeId() {
		return dossierInscriptionAdministrativeId;
	}

	/**
	 * [BilanControleContinuDto.dossierInscriptionAdministrativeId] Setter 
	 * @author MAKERRI Sofiane on : 11 janv. 2015  12:37:57
	 * @param dossierInscriptionAdministrativeId the dossierInscriptionAdministrativeId to set
	 */
	public void setDossierInscriptionAdministrativeId(
			Integer dossierInscriptionAdministrativeId) {
		this.dossierInscriptionAdministrativeId = dossierInscriptionAdministrativeId;
	}

	
	
	

}
