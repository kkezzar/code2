/**
 * [dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto.java] 
 * @author rlaib on : 16 déc. 2014  13:24:10
 */
package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;
import java.util.Date;


public class DemandeBudgetDto implements Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:55:29
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date dateDemande;
	private short anneeDebut;
	private short anneeFin;
	private String description;
	private String connaissance;
	private String information;
	private String reseauRecherche;
	private String objectif;
	private String observation;
	
	// Programme
	private Long programmeId;
	private String programmeCode;
	private String programmeLibelle;
	
	// Situation Demande
	private Integer situationId;
	private String situationCode;
	private String situationLibelle;
	private String situationStyle;
	
	public DemandeBudgetDto() {
	}

	/**
	 * [DemandeBudgetDto.id] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * [DemandeBudgetDto.id] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [DemandeBudgetDto.dateDemande] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the dateDemande
	 */
	public Date getDateDemande() {
		return dateDemande;
	}

	/**
	 * [DemandeBudgetDto.dateDemande] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param dateDemande the dateDemande to set
	 */
	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	/**
	 * [DemandeBudgetDto.anneeDebut] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the anneeDebut
	 */
	public short getAnneeDebut() {
		return anneeDebut;
	}

	/**
	 * [DemandeBudgetDto.anneeDebut] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param anneeDebut the anneeDebut to set
	 */
	public void setAnneeDebut(short anneeDebut) {
		this.anneeDebut = anneeDebut;
	}

	/**
	 * [DemandeBudgetDto.anneeFin] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the anneeFin
	 */
	public short getAnneeFin() {
		return anneeFin;
	}

	/**
	 * [DemandeBudgetDto.anneeFin] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param anneeFin the anneeFin to set
	 */
	public void setAnneeFin(short anneeFin) {
		this.anneeFin = anneeFin;
	}

	/**
	 * [DemandeBudgetDto.description] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * [DemandeBudgetDto.description] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [DemandeBudgetDto.connaissance] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the connaissance
	 */
	public String getConnaissance() {
		return connaissance;
	}

	/**
	 * [DemandeBudgetDto.connaissance] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param connaissance the connaissance to set
	 */
	public void setConnaissance(String connaissance) {
		this.connaissance = connaissance;
	}

	/**
	 * [DemandeBudgetDto.information] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the information
	 */
	public String getInformation() {
		return information;
	}

	/**
	 * [DemandeBudgetDto.information] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param information the information to set
	 */
	public void setInformation(String information) {
		this.information = information;
	}

	/**
	 * [DemandeBudgetDto.reseauRecherche] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the reseauRecherche
	 */
	public String getReseauRecherche() {
		return reseauRecherche;
	}

	/**
	 * [DemandeBudgetDto.reseauRecherche] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param reseauRecherche the reseauRecherche to set
	 */
	public void setReseauRecherche(String reseauRecherche) {
		this.reseauRecherche = reseauRecherche;
	}

	/**
	 * [DemandeBudgetDto.objectif] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the objectif
	 */
	public String getObjectif() {
		return objectif;
	}

	/**
	 * [DemandeBudgetDto.objectif] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param objectif the objectif to set
	 */
	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	/**
	 * [DemandeBudgetDto.observation] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * [DemandeBudgetDto.observation] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * [DemandeBudgetDto.programmeId] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the programmeId
	 */
	public Long getProgrammeId() {
		return programmeId;
	}

	/**
	 * [DemandeBudgetDto.programmeId] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param programmeId the programmeId to set
	 */
	public void setProgrammeId(Long programmeId) {
		this.programmeId = programmeId;
	}

	/**
	 * [DemandeBudgetDto.programmeCode] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the programmeCode
	 */
	public String getProgrammeCode() {
		return programmeCode;
	}

	/**
	 * [DemandeBudgetDto.programmeCode] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param programmeCode the programmeCode to set
	 */
	public void setProgrammeCode(String programmeCode) {
		this.programmeCode = programmeCode;
	}

	/**
	 * [DemandeBudgetDto.programmeLibelle] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the programmeLibelle
	 */
	public String getProgrammeLibelle() {
		return programmeLibelle;
	}

	/**
	 * [DemandeBudgetDto.programmeLibelle] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param programmeLibelle the programmeLibelle to set
	 */
	public void setProgrammeLibelle(String programmeLibelle) {
		this.programmeLibelle = programmeLibelle;
	}

	/**
	 * [DemandeBudgetDto.situationId] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the situationId
	 */
	public Integer getSituationId() {
		return situationId;
	}

	/**
	 * [DemandeBudgetDto.situationId] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param situationId the situationId to set
	 */
	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}

	/**
	 * [DemandeBudgetDto.situationCode] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the situationCode
	 */
	public String getSituationCode() {
		return situationCode;
	}

	/**
	 * [DemandeBudgetDto.situationCode] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param situationCode the situationCode to set
	 */
	public void setSituationCode(String situationCode) {
		this.situationCode = situationCode;
	}

	/**
	 * [DemandeBudgetDto.situationLibelle] Getter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @return the situationLibelle
	 */
	public String getSituationLibelle() {
		return situationLibelle;
	}

	/**
	 * [DemandeBudgetDto.situationLibelle] Setter 
	 * @author rlaib on : 1 févr. 2015  15:18:39
	 * @param situationLibelle the situationLibelle to set
	 */
	public void setSituationLibelle(String situationLibelle) {
		this.situationLibelle = situationLibelle;
	}

	/**
	 * [DemandeBudgetDto.situationStyle] Getter 
	 * @author rlaib on : 1 févr. 2015  15:38:31
	 * @return the situationStyle
	 */
	public String getSituationStyle() {
		return situationStyle;
	}

	/**
	 * [DemandeBudgetDto.situationStyle] Setter 
	 * @author rlaib on : 1 févr. 2015  15:38:31
	 * @param situationStyle the situationStyle to set
	 */
	public void setSituationStyle(String situationStyle) {
		this.situationStyle = situationStyle;
	}


	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.DemandeBudgetDto.hashCode] Method 
	 * Overridden By : @author rlaib  on : 3 févr. 2015  16:56:05
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anneeDebut;
		result = prime * result + anneeFin;
		result = prime * result
				+ ((connaissance == null) ? 0 : connaissance.hashCode());
		result = prime * result
				+ ((dateDemande == null) ? 0 : dateDemande.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((information == null) ? 0 : information.hashCode());
		result = prime * result
				+ ((objectif == null) ? 0 : objectif.hashCode());
		result = prime * result
				+ ((observation == null) ? 0 : observation.hashCode());
		result = prime * result
				+ ((programmeId == null) ? 0 : programmeId.hashCode());
		result = prime * result
				+ ((reseauRecherche == null) ? 0 : reseauRecherche.hashCode());
		result = prime * result
				+ ((situationId == null) ? 0 : situationId.hashCode());
		return result;
	}

	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.DemandeBudgetDto.equals] Method 
	 * Overridden By : @author rlaib  on : 3 févr. 2015  16:56:05
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DemandeBudgetDto other = (DemandeBudgetDto) obj;
		if (anneeDebut != other.anneeDebut)
			return false;
		if (anneeFin != other.anneeFin)
			return false;
		if (connaissance == null) {
			if (other.connaissance != null)
				return false;
		} else if (!connaissance.equals(other.connaissance))
			return false;
		if (dateDemande == null) {
			if (other.dateDemande != null)
				return false;
		} else if (!dateDemande.equals(other.dateDemande))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (information == null) {
			if (other.information != null)
				return false;
		} else if (!information.equals(other.information))
			return false;
		if (objectif == null) {
			if (other.objectif != null)
				return false;
		} else if (!objectif.equals(other.objectif))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (programmeId == null) {
			if (other.programmeId != null)
				return false;
		} else if (!programmeId.equals(other.programmeId))
			return false;
		if (reseauRecherche == null) {
			if (other.reseauRecherche != null)
				return false;
		} else if (!reseauRecherche.equals(other.reseauRecherche))
			return false;
		if (situationId == null) {
			if (other.situationId != null)
				return false;
		} else if (!situationId.equals(other.situationId))
			return false;
		return true;
	}

	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.DemandeBudgetDto.toString] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  15:20:32
	 * @return
	 */
	@Override
	public String toString() {
		return "DemandeBudgetDto [id=" + id + ", dateDemande=" + dateDemande
				+ ", anneeDebut=" + anneeDebut + ", anneeFin=" + anneeFin
				+ ", programmeLibelle=" + programmeLibelle + "]";
	}

	
}
