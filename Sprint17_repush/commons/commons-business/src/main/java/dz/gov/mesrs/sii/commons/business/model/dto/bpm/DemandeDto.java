package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

import java.util.Date;



/**
 * @author BELDI Jamel  on : 20 avr. 2014  11:14:25
 */
public class DemandeDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 20 avr. 2014  11:14:18
	 */
	private static final long serialVersionUID = 1L;


	
	private int id;
	private String code;
	private Date dateDemande;
	private Integer typeDemandeId;
	private Integer offreFormationId;
	private Integer idSituation;
	private Date dateCreation;
	/**
	 * [DemandeDto.id] Getter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:36:43
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * [DemandeDto.id] Setter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:36:43
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * [DemandeDto.code] Getter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:36:43
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * [DemandeDto.code] Setter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:36:43
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * [DemandeDto.dateDemande] Getter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:36:43
	 * @return the dateDemande
	 */
	public Date getDateDemande() {
		return dateDemande;
	}
	/**
	 * [DemandeDto.dateDemande] Setter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:36:43
	 * @param dateDemande the dateDemande to set
	 */
	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}
	
	/**
	 * [DemandeDto.typeDemandeId] Getter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:49:34
	 * @return the typeDemandeId
	 */
	public Integer getTypeDemandeId() {
		return typeDemandeId;
	}
	/**
	 * [DemandeDto.typeDemandeId] Setter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:49:34
	 * @param typeDemandeId the typeDemandeId to set
	 */
	public void setTypeDemandeId(Integer typeDemandeId) {
		this.typeDemandeId = typeDemandeId;
	}
	/**
	 * [DemandeDto.offreFormationId] Getter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:36:43
	 * @return the offreFormationId
	 */
	public Integer getOffreFormationId() {
		return offreFormationId;
	}
	/**
	 * [DemandeDto.offreFormationId] Setter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:36:43
	 * @param offreFormationId the offreFormationId to set
	 */
	public void setOffreFormationId(Integer offreFormationId) {
		this.offreFormationId = offreFormationId;
	}
	/**
	 * [DemandeDto.idSituation] Getter 
	 * @author BELDI Jamel on : 24 avr. 2014  17:35:36
	 * @return the idSituation
	 */
	public Integer getIdSituation() {
		return idSituation;
	}
	/**
	 * [DemandeDto.idSituation] Setter 
	 * @author BELDI Jamel on : 24 avr. 2014  17:35:36
	 * @param idSituation the idSituation to set
	 */
	public void setIdSituation(Integer idSituation) {
		this.idSituation = idSituation;
	}
	/**
	 * [DemandeDto.dateCreation] Getter 
	 * @author BELDI Jamel on : 29 avr. 2014  11:01:36
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}
	/**
	 * [DemandeDto.dateCreation] Setter 
	 * @author BELDI Jamel on : 29 avr. 2014  11:01:36
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	

}
