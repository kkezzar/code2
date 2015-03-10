/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author obelbrik
 *
 */
public class RefEtatEquipementDto implements Serializable{

	/**
	 * serialVersionUID 
	 * @author BELBRIK Oualid  on : 16 mars. 2014  11:13:19
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dateEtat;
	private String observation;

	/***** Equipement *****/
	private Integer idEquipement;
	private String designationEquipement;
	
	//private Nomenclature nomenclature;
	private Integer etatEquipementId;	
	private String etatEquipementLibelleLongFr;
	private String etatEquipementLibelleLongAr;
	private String etatEquipementLibelleCourtFr;
	private String etatEquipementLibelleCourtAr;
	private String etatEquipementCode;

	/**
	 * 
	 */
	public RefEtatEquipementDto() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * [RefEtatEquipementDto.id] Getter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * [RefEtatEquipementDto.id] Setter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * [RefEtatEquipementDto.dateEtat] Getter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @return the dateEtat
	 */
	public Date getDateEtat() {
		return dateEtat;
	}


	/**
	 * [RefEtatEquipementDto.dateEtat] Setter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @param dateEtat the dateEtat to set
	 */
	public void setDateEtat(Date dateEtat) {
		this.dateEtat = dateEtat;
	}


	/**
	 * [RefEtatEquipementDto.idEquipement] Getter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @return the idEquipement
	 */
	public Integer getIdEquipement() {
		return idEquipement;
	}


	/**
	 * [RefEtatEquipementDto.idEquipement] Setter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @param idEquipement the idEquipement to set
	 */
	public void setIdEquipement(Integer idEquipement) {
		this.idEquipement = idEquipement;
	}


	/**
	 * [RefEtatEquipementDto.observation] Getter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}


	/**
	 * [RefEtatEquipementDto.observation] Setter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}



	/**
	 * [RefEtatEquipementDto.etatEquipementId] Getter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @return the etatEquipementId
	 */
	public Integer getEtatEquipementId() {
		return etatEquipementId;
	}


	/**
	 * [RefEtatEquipementDto.etatEquipementId] Setter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @param etatEquipementId the etatEquipementId to set
	 */
	public void setEtatEquipementId(Integer etatEquipementId) {
		this.etatEquipementId = etatEquipementId;
	}


	/**
	 * [RefEtatEquipementDto.etatEquipementLibelleLongFr] Getter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @return the etatEquipementLibelleLongFr
	 */
	public String getEtatEquipementLibelleLongFr() {
		return etatEquipementLibelleLongFr;
	}


	/**
	 * [RefEtatEquipementDto.etatEquipementLibelleLongFr] Setter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @param etatEquipementLibelleLongFr the etatEquipementLibelleLongFr to set
	 */
	public void setEtatEquipementLibelleLongFr(String etatEquipementLibelleLongFr) {
		this.etatEquipementLibelleLongFr = etatEquipementLibelleLongFr;
	}


	/**
	 * [RefEtatEquipementDto.etatEquipementLibelleLongAr] Getter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @return the etatEquipementLibelleLongAr
	 */
	public String getEtatEquipementLibelleLongAr() {
		return etatEquipementLibelleLongAr;
	}


	/**
	 * [RefEtatEquipementDto.etatEquipementLibelleLongAr] Setter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @param etatEquipementLibelleLongAr the etatEquipementLibelleLongAr to set
	 */
	public void setEtatEquipementLibelleLongAr(String etatEquipementLibelleLongAr) {
		this.etatEquipementLibelleLongAr = etatEquipementLibelleLongAr;
	}


	/**
	 * [RefEtatEquipementDto.etatEquipementLibelleCourtFr] Getter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @return the etatEquipementLibelleCourtFr
	 */
	public String getEtatEquipementLibelleCourtFr() {
		return etatEquipementLibelleCourtFr;
	}


	/**
	 * [RefEtatEquipementDto.etatEquipementLibelleCourtFr] Setter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @param etatEquipementLibelleCourtFr the etatEquipementLibelleCourtFr to set
	 */
	public void setEtatEquipementLibelleCourtFr(String etatEquipementLibelleCourtFr) {
		this.etatEquipementLibelleCourtFr = etatEquipementLibelleCourtFr;
	}


	/**
	 * [RefEtatEquipementDto.etatEquipementLibelleCourtAr] Getter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @return the etatEquipementLibelleCourtAr
	 */
	public String getEtatEquipementLibelleCourtAr() {
		return etatEquipementLibelleCourtAr;
	}


	/**
	 * [RefEtatEquipementDto.etatEquipementLibelleCourtAr] Setter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @param etatEquipementLibelleCourtAr the etatEquipementLibelleCourtAr to set
	 */
	public void setEtatEquipementLibelleCourtAr(String etatEquipementLibelleCourtAr) {
		this.etatEquipementLibelleCourtAr = etatEquipementLibelleCourtAr;
	}


	/**
	 * [RefEtatEquipementDto.etatEquipementCode] Getter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:45
	 * @return the etatEquipementCode
	 */
	public String getEtatEquipementCode() {
		return etatEquipementCode;
	}


	/**
	 * [RefEtatEquipementDto.etatEquipementCode] Setter 
	 * @author BELBRIK Oualid on : 16 mars 2014  11:15:46
	 * @param etatEquipementCode the etatEquipementCode to set
	 */
	public void setEtatEquipementCode(String etatEquipementCode) {
		this.etatEquipementCode = etatEquipementCode;
	}


	
	/**
	 * [RefEtatEquipementDto.designationEquipement] Getter 
	 * @author BELBRIK Oualid on : 22 mars 2014  16:47:27
	 * @return the designationEquipement
	 */
	public String getDesignationEquipement() {
		return designationEquipement;
	}


	/**
	 * [RefEtatEquipementDto.designationEquipement] Setter 
	 * @author BELBRIK Oualid on : 22 mars 2014  16:47:27
	 * @param designationEquipement the designationEquipement to set
	 */
	public void setDesignationEquipement(String designationEquipement) {
		this.designationEquipement = designationEquipement;
	}



}
