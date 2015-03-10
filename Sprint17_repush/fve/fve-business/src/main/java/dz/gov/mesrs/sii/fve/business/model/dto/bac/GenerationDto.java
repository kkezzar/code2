package dz.gov.mesrs.sii.fve.business.model.dto.bac;

import java.util.Date;

public class GenerationDto implements java.io.Serializable {
	

	/**
	 * serialVersionUID 
	 * @author  Rafik LAIB  on : 21 mai 2014  14:01:46
	 */
	private static final long serialVersionUID = 5595401399463125298L;
	private int id;
	private String anneeAcademique;
	private String situation;
	private String refCodeEtablissement;
	private int nbBacheliers;
	private int nbDossiersEtudiants;
	private int nbDossiersInscriptions;
	private int nbIndividusGeneres;
	private int nbIndividusExistants;
	private Date dateDebutGeneration;
	private Date dateFinGeneration;
	public GenerationDto() {
	}

	/**
	 * [GenerationDto.id] Getter 
	 * @author rlaib on : 30 juin 2014  10:37:44
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [GenerationDto.id] Setter 
	 * @author rlaib on : 30 juin 2014  10:37:44
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [GenerationDto.anneeAcademique] Getter 
	 * @author rlaib on : 30 juin 2014  10:37:44
	 * @return the anneeAcademique
	 */
	public String getAnneeAcademique() {
		return anneeAcademique;
	}

	/**
	 * [GenerationDto.anneeAcademique] Setter 
	 * @author rlaib on : 30 juin 2014  10:37:44
	 * @param anneeAcademique the anneeAcademique to set
	 */
	public void setAnneeAcademique(String anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	/**
	 * [GenerationDto.situation] Getter 
	 * @author rlaib on : 30 juin 2014  10:37:44
	 * @return the situation
	 */
	public String getSituation() {
		return situation;
	}

	/**
	 * [GenerationDto.situation] Setter 
	 * @author rlaib on : 30 juin 2014  10:37:44
	 * @param situation the situation to set
	 */
	public void setSituation(String situation) {
		this.situation = situation;
	}

	/**
	 * [GenerationDto.refCodeEtablissement] Getter 
	 * @author rlaib on : 30 juin 2014  10:37:44
	 * @return the refCodeEtablissement
	 */
	public String getRefCodeEtablissement() {
		return refCodeEtablissement;
	}

	/**
	 * [GenerationDto.refCodeEtablissement] Setter 
	 * @author rlaib on : 30 juin 2014  10:37:44
	 * @param refCodeEtablissement the refCodeEtablissement to set
	 */
	public void setRefCodeEtablissement(String refCodeEtablissement) {
		this.refCodeEtablissement = refCodeEtablissement;
	}

	/**
	 * [GenerationDto.nbBacheliers] Getter 
	 * @author rlaib on : 30 juin 2014  12:13:34
	 * @return the nbBacheliers
	 */
	public int getNbBacheliers() {
		return nbBacheliers;
	}

	/**
	 * [GenerationDto.nbBacheliers] Setter 
	 * @author rlaib on : 30 juin 2014  12:13:34
	 * @param nbBacheliers the nbBacheliers to set
	 */
	public void setNbBacheliers(int nbBacheliers) {
		this.nbBacheliers = nbBacheliers;
	}

	/**
	 * [GenerationDto.nbDossiersEtudiants] Getter 
	 * @author rlaib on : 30 juin 2014  12:13:34
	 * @return the nbDossiersEtudiants
	 */
	public int getNbDossiersEtudiants() {
		return nbDossiersEtudiants;
	}

	/**
	 * [GenerationDto.nbDossiersEtudiants] Setter 
	 * @author rlaib on : 30 juin 2014  12:13:34
	 * @param nbDossiersEtudiants the nbDossiersEtudiants to set
	 */
	public void setNbDossiersEtudiants(int nbDossiersEtudiants) {
		this.nbDossiersEtudiants = nbDossiersEtudiants;
	}

	/**
	 * [GenerationDto.nbDossiersInscriptions] Getter 
	 * @author rlaib on : 30 juin 2014  12:13:34
	 * @return the nbDossiersInscriptions
	 */
	public int getNbDossiersInscriptions() {
		return nbDossiersInscriptions;
	}

	/**
	 * [GenerationDto.nbDossiersInscriptions] Setter 
	 * @author rlaib on : 30 juin 2014  12:13:34
	 * @param nbDossiersInscriptions the nbDossiersInscriptions to set
	 */
	public void setNbDossiersInscriptions(int nbDossiersInscriptions) {
		this.nbDossiersInscriptions = nbDossiersInscriptions;
	}

	/**
	 * [GenerationDto.nbIndividusGeneres] Getter 
	 * @author rlaib on : 30 juin 2014  12:13:34
	 * @return the nbIndividusGeneres
	 */
	public int getNbIndividusGeneres() {
		return nbIndividusGeneres;
	}

	/**
	 * [GenerationDto.nbIndividusGeneres] Setter 
	 * @author rlaib on : 30 juin 2014  12:13:34
	 * @param nbIndividusGeneres the nbIndividusGeneres to set
	 */
	public void setNbIndividusGeneres(int nbIndividusGeneres) {
		this.nbIndividusGeneres = nbIndividusGeneres;
	}

	/**
	 * [GenerationDto.nbIndividusExistants] Getter 
	 * @author rlaib on : 30 juin 2014  12:13:34
	 * @return the nbIndividusExistants
	 */
	public int getNbIndividusExistants() {
		return nbIndividusExistants;
	}

	/**
	 * [GenerationDto.nbIndividusExistants] Setter 
	 * @author rlaib on : 30 juin 2014  12:13:34
	 * @param nbIndividusExistants the nbIndividusExistants to set
	 */
	public void setNbIndividusExistants(int nbIndividusExistants) {
		this.nbIndividusExistants = nbIndividusExistants;
	}

	/**
	 * [GenerationDto.dateDebutGeneration] Getter 
	 * @author rlaib on : 2 août 2014  10:31:28
	 * @return the dateDebutGeneration
	 */
	public Date getDateDebutGeneration() {
		return dateDebutGeneration;
	}

	/**
	 * [GenerationDto.dateDebutGeneration] Setter 
	 * @author rlaib on : 2 août 2014  10:31:28
	 * @param dateDebutGeneration the dateDebutGeneration to set
	 */
	public void setDateDebutGeneration(Date dateDebutGeneration) {
		this.dateDebutGeneration = dateDebutGeneration;
	}

	/**
	 * [GenerationDto.dateFinGeneration] Getter 
	 * @author rlaib on : 2 août 2014  10:31:28
	 * @return the dateFinGeneration
	 */
	public Date getDateFinGeneration() {
		return dateFinGeneration;
	}

	/**
	 * [GenerationDto.dateFinGeneration] Setter 
	 * @author rlaib on : 2 août 2014  10:31:28
	 * @param dateFinGeneration the dateFinGeneration to set
	 */
	public void setDateFinGeneration(Date dateFinGeneration) {
		this.dateFinGeneration = dateFinGeneration;
	}

	
	
	
}
